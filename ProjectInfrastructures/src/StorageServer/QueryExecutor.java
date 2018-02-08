package StorageServer;

import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class that will actually execute the query
 */
public class QueryExecutor {
    private ServerMain model;
    private QueryClient client;
    private String stationNumber;
    private String[] query;
    private PrintWriter out;

    public QueryExecutor(ServerMain model, QueryClient client, String stationnumber, String[] query, PrintWriter out) {
        this.model = model;
        this.client = client;
        this.stationNumber = stationnumber;
        this.query = query;
        this.out = out;
    }

    /**
     * Main method for query handling
     */
    public void executeQuery() {
        try {
            int timeFrom = Integer.parseInt(query[6]);
            int timeTo = Integer.parseInt(query[8]);
            byte[] Short = new byte[2];
            byte[] Int = new byte[4];
            byte[] Byte = new byte[1];

            // Are the dates valid?
            if(timeFrom > timeTo) {
                QueryResult tempQR = new QueryResult();
                tempQR.setStatus(STATE.ERROR);
                tempQR.setErrorMessage("Start date is later than end date.");
                tempQR.writeToStream(out);

                return;
            }
            // Does the station exist?
            if(!new File(this.model.CUR_PATH + "/data/" + this.stationNumber + "/").exists()) {
                new QueryResult().writeError(out, "No station with ID: " + this.stationNumber + " exists.");
                return;
            }
            // Does the station have any files?
            //TODO:FIx nullpointer
            if(Objects.requireNonNull(new File(this.model.CUR_PATH + "/data/" + this.stationNumber + "/")
                    .listFiles((dir1, name) -> name.endsWith(".dat"))).length < 1) {
                new QueryResult().writeError(out, "Station with ID: " + this.stationNumber + " has no data.");
                return;
            }

            // Get DateObjects from time strings
            Date dateFrom = new Date(timeFrom * 1000L);
            LocalDate dateStart = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date dateTo = new Date(timeTo * 1000L);
            LocalDate dateEnd = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Generate filenames based on dates
            List<LocalDate> dateList = IntStream.iterate(0, i -> i + 1)
                    .limit(ChronoUnit.DAYS.between(dateStart.minusDays(1), dateEnd))
                    .mapToObj(i -> dateStart.plusDays(i))
                    .collect(Collectors.toList());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            ArrayList<String> fileNameList = new ArrayList<>();

            for(LocalDate Date : dateList) {
                fileNameList.add(this.model.CUR_PATH + "/data/" + this.stationNumber + "/" + this.stationNumber + "_" + Date.format(formatter) + ".dat");
            }
            // Remove possible duplicates
            LinkedHashSet<String> lhs = new LinkedHashSet<String>(fileNameList);
            fileNameList.clear();
            fileNameList.addAll(lhs);
            Collections.sort(fileNameList);

            // Do we have any data in our list
            if(fileNameList.size() < 1) {
                new QueryResult().writeError(out, "No data has been found for this query.");
                return;
            }

            // Get a list of all the files
            File[] files = new File[fileNameList.size()];
            for(int i = 0; i < fileNameList.size(); i++) {
                files[i] = new File(fileNameList.get(i));
            }
            // Resolve per
            String per = "SEC";
            if(query.length >= 9)
                per = query[9].toUpperCase();
            if(!per.equals("SEC") && !per.equals("MINUTE") && !per.equals("HOUR"))
                per = "SEC";

            // Start initial
            //TODO: Initializer
            QueryResult result = new QueryResult(Integer.parseInt(this.stationNumber));
            result.setStatus(STATE.OK);

            // Go through all files
            for(int i = 0; i < files.length; i++) {
                long fileSize = files[i].length();
                RandomAccessFile raf = new RandomAccessFile(files[i].getAbsolutePath(), "r");
                HashMap<String, ArrayList<Object>> tempDataSeconds = new HashMap<>();
                HashMap<String, ArrayList<Object>> tempDataMinutes = new HashMap<>();
                for(ColumnInfo col : Tools.FIELD_LIST.values()) {
                    tempDataSeconds.put(col.columnName, new ArrayList<Object>());
                    tempDataMinutes.put(col.columnName, new ArrayList<Object>());
                }

                for(long index = 0; index < fileSize; index += 25) {
                    boolean send = false;
                    // Wait till we find the right timestamp
                    //TODO: Fix wrong time
                    raf.seek(index);
                    int time = raf.readInt();
                    if(time >= timeFrom && time <= timeTo) {
                        if(per.equalsIgnoreCase("sec")) {
                            for(String column : query[2].split("\\,")) {
                                switch(Tools.FIELD_LIST.get(column).type) {
                                    case Byte:
                                        raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                                        result.addResult( new QueryCol(Tools.FIELD_LIST.get(column), raf.readByte(), time) );
                                        break;

                                    case Short:
                                        raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                                        result.addResult( new QueryCol(Tools.FIELD_LIST.get(column), raf.readShort(), time) );
                                        break;

                                    case Integer:
                                        raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                                        result.addResult( new QueryCol(Tools.FIELD_LIST.get(column), raf.readInt(), time) );
                                        break;
                                }
                            }
                            result.writeToStream(out);
                            // Send data right away
                        }else {
                            // Accumulate data
                            for(String column : query[2].split("\\,")) {
                                switch(Tools.FIELD_LIST.get(column).type) {
                                    case Byte:
                                        raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                                        tempDataSeconds.get(column).add(raf.readByte());
                                        break;

                                    case Short:
                                        raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                                        tempDataSeconds.get(column).add(raf.readShort());
                                        break;

                                    case Integer:
                                        raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                                        tempDataSeconds.get(column).add(raf.readInt());
                                        break;
                                }
                            }
                            if(per.equalsIgnoreCase("minute") || per.equalsIgnoreCase("hour")){
                                // Get average if we have reached 60 seconds
                                for(String column : query[2].split("\\,")) {
                                    if(tempDataSeconds.get(column).size() == 60) {
                                        if(per.equalsIgnoreCase("minute"))
                                            send = true;
                                        if(Tools.FIELD_LIST.get(column).average) {
                                            Double avg = tempDataSeconds.get(column).stream()
                                                    .mapToInt(a -> (int) a)
                                                    .average()
                                                    .orElse(0);
                                            if(per.equalsIgnoreCase("minute"))
                                                result.addResult(new QueryCol(Tools.FIELD_LIST.get(column), avg, time));
                                            else
                                                tempDataMinutes.get(column).add(avg);
                                        }else{
                                            if(per.equalsIgnoreCase("minute"))
                                                result.addResult(new QueryCol(Tools.FIELD_LIST.get(column),
                                                        tempDataSeconds.get(column)
                                                        .get(tempDataSeconds.get(column).size()-1), time));
                                            else
                                                tempDataMinutes.get(column).add(tempDataSeconds.get(column)
                                                        .get(tempDataSeconds.get(column).size()-1));
                                        }
                                        tempDataSeconds.get(column).clear();
                                    }
                                }
                                if(per.equalsIgnoreCase("hour")) {
                                    // Get average if we have reached 60 minutes
                                    for(String column : query[2].split("\\,")) {
                                        if(tempDataMinutes.get(column).size() == 60) {
                                            if(per.equalsIgnoreCase("hour"))
                                                send = true;
                                            if(Tools.FIELD_LIST.get(column).average) {
                                                Double avg = tempDataMinutes.get(column).stream()
                                                        .mapToInt(a -> (int) a)
                                                        .average()
                                                        .orElse(0);
                                                if(per.equalsIgnoreCase("hour"))
                                                    result.addResult(new QueryCol(Tools.FIELD_LIST.get(column), avg, time));
                                            }else{
                                                if(per.equalsIgnoreCase("hour"))
                                                    result.addResult(new QueryCol(Tools.FIELD_LIST.get(column),
                                                            tempDataMinutes.get(column)
                                                                    .get(tempDataMinutes.get(column).size()-1), time));
                                            }
                                            tempDataMinutes.get(column).clear();
                                        }
                                    }
                                }

                                if(send) {
                                    // Send the data
                                    result.writeToStream(out);
                                }
                            }
                        }
                        // If end of file, send data regardless or timeTo has been reached
                        if(time > timeTo || index >= fileSize - 25) {
                            if(per.equalsIgnoreCase("sec"))
                                break;

                            if(per.equalsIgnoreCase("minute") || per.equalsIgnoreCase("hour")){
                                // Get average if we have reached 60 seconds
                                for(String column : query[2].split("\\,")) {
                                    if(tempDataSeconds.get(column).size() > 0) {
                                        if(per.equalsIgnoreCase("minute"))
                                            send = true;
                                        if(Tools.FIELD_LIST.get(column).average) {
                                            Double avg = tempDataSeconds.get(column).stream()
                                                    .mapToInt(a -> (int) a)
                                                    .average()
                                                    .orElse(0);
                                            if(per.equalsIgnoreCase("minute"))
                                                result.addResult(new QueryCol(Tools.FIELD_LIST.get(column), avg, time));
                                            else
                                                tempDataMinutes.get(column).add(avg);
                                        }else{
                                            if(per.equalsIgnoreCase("minute"))
                                                result.addResult(new QueryCol(Tools.FIELD_LIST.get(column),
                                                        tempDataSeconds.get(column)
                                                                .get(tempDataSeconds.get(column).size()-1), time));
                                            else
                                                tempDataMinutes.get(column).add(tempDataSeconds.get(column)
                                                        .get(tempDataSeconds.get(column).size()-1));
                                        }
                                        tempDataSeconds.get(column).clear();
                                    }
                                }
                                if(per.equalsIgnoreCase("hour")) {
                                    // Get average if we have reached 60 minutes
                                    for(String column : query[2].split("\\,")) {
                                        if(tempDataMinutes.get(column).size() > 0) {
                                            if(per.equalsIgnoreCase("hour"))
                                                send = true;
                                            if(Tools.FIELD_LIST.get(column).average) {
                                                Double avg = tempDataMinutes.get(column).stream()
                                                        .mapToInt(a -> (int) a)
                                                        .average()
                                                        .orElse(0);
                                                if(per.equalsIgnoreCase("hour"))
                                                    result.addResult(new QueryCol(Tools.FIELD_LIST.get(column), avg, time));
                                            }else{
                                                if(per.equalsIgnoreCase("hour"))
                                                    result.addResult(new QueryCol(Tools.FIELD_LIST.get(column),
                                                            tempDataMinutes.get(column)
                                                                    .get(tempDataMinutes.get(column).size()-1), time));
                                            }
                                            tempDataMinutes.get(column).clear();
                                        }
                                    }
                                }

                                if(send) {
                                    // Send the data
                                    result.writeToStream(out);
                                }
                            }

                            break; // We've got all our data
                        }
                    }
                }
            }

            // End
            //TODO: End
            out.println();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
