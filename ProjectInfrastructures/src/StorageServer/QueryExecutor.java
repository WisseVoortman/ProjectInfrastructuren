package StorageServer;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Documentation
enum threadMode { SINGLE, MULTI }
public class QueryExecutor implements Runnable{
    private ServerMain model;
    private QueryClient client;
    private String stationNumber;
    private String[] query;
    private threadMode mode;
    private ArrayList<QueryResult> results;

    public QueryExecutor(ServerMain model, QueryClient client, String stationnumber, String[] query, threadMode mode, ArrayList<QueryResult> results) {
        this.model = model;
        this.client = client;
        this.stationNumber = stationnumber;
        this.query = query;
        this.mode = mode;
        this.results = results;
    }

    @Override
    public void run() {
        // Execute the query
        switch(this.mode) {
            case SINGLE:
                this.executeQuerySingleThread();
                break;

            case MULTI:
                this.executeQueryMultiThread(this.results);
                break;

            default:
                break;
        }
    }

    private void executeQueryMultiThread(ArrayList<QueryResult> reseults) {
        try {
            // Get a date object
            Date dateObj = new Date(Long.parseLong(query[6]) * 1000L);

            // Convert to string
            Format formatter = new SimpleDateFormat("uuuu-MM-dd");
            String dateString = formatter.format(dateObj);

            // Get the filename
            String fileName = stationNumber + "_" + dateString + ".dat";
            String directory = this.model.CUR_PATH + "\\data\\" + stationNumber + "\\";
            File file = new File(this.model.CUR_PATH + "\\data\\" + stationNumber + "\\" + fileName);
            if(!file.exists()) {// Try to find the closest file
                // Get filelist
                File[] files = new File(directory).listFiles((dir1, name) -> name.endsWith(".dat"));
                // Make sure there are files
                if(files.length < 1) {
                    QueryResult tempQR = new QueryResult();
                    tempQR.setStationNumber(Integer.parseInt(this.stationNumber));
                    tempQR.setStatus(STATE.ERROR);
                    tempQR.setErrorMessage("No data for this station is available.");
                    results.add(tempQR);
                    return;
                }
                // Create a pattern
                Pattern datePattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
                File closestFile = files[0];

                Matcher match;
                int difference = 999;

                LocalDateTime dateTimeOne = LocalDateTime.parse((dateString),
                        DateTimeFormatter.ofPattern("uuuu-MM-dd"));

                for(File f : files) {
                    match = datePattern.matcher(f.getName());
                    String date = match.group();
                    LocalDateTime dateTimeTwo = LocalDateTime.parse((date),
                            DateTimeFormatter.ofPattern("uuuu-MM-dd"));

                    long days = ChronoUnit.DAYS.between(dateTimeOne, dateTimeTwo );
                    if( days < difference ) {
                        closestFile = f;
                        difference = (int)days;
                    }
                }
                file = closestFile;
            }

            // Get the actual data
            RandomAccessFile  raf = new RandomAccessFile(file.getAbsolutePath(), "r");
            int foundDate = raf.readInt();
            int index = 0;
            int reqDate = Integer.parseInt(query[6]);
            if(foundDate >= reqDate) {
                // Apparently we don't have this time. Giving the closest possible time.
                QueryResult result = new QueryResult();
                result.setStationNumber(Integer.parseInt(this.stationNumber));
                result.setStatus(STATE.ERROR_WITH_DATA);
                result.setErrorMessage("Failed to get exact time. Returning closest time.");

                // Build a result
                for(String column : query[2].split("\\+")) {
                    switch(Tools.FIELD_LIST.get(column).type) {
                        case Byte:
                            raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                            result.addResult( new QueryCol(Tools.FIELD_LIST.get(column), raf.readByte(), foundDate) );
                            break;

                        case Short:
                            raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                            result.addResult( new QueryCol(Tools.FIELD_LIST.get(column), raf.readShort(), foundDate) );
                            break;

                        case Integer:
                            raf.seek(index + Tools.FIELD_LIST.get(column).offset);
                            result.addResult( new QueryCol(Tools.FIELD_LIST.get(column), raf.readInt(), foundDate) );
                            break;
                    }
                }
                // Add it to the list
                results.add(result);

            }



        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void executeQuerySingleThread() {
        // First determine whether we need to query a single time or multiple
        try {
            if(query[5].toLowerCase().equals("at")) {
                // Get a date object
                Date dateObj = new Date(Long.parseLong(query[6]) * 1000L);

                // Convert to string
                Format formatter = new SimpleDateFormat("uuuu-MM-dd");
                String dateString = formatter.format(dateObj);

                // Get the filename
                String fileName = stationNumber + "_" + dateString + ".dat";
                String directory = this.model.CUR_PATH + "\\data\\" + stationNumber + "\\";
                File file = new File(this.model.CUR_PATH + "\\data\\" + stationNumber + "\\" + fileName);
                if(!file.exists()) {// Try to find the closest file
                    // Get filelist
                    File[] files = new File(directory).listFiles((dir1, name) -> name.endsWith(".dat"));


                }


            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    //TODO: Add per clause

            /*
         *0 AUTHENTICATION_ID
         *1 SELECT
         *2 fields+to+select+separated+by+a+plus+sign
         *3 FROM
         *4 stations+to+query+separated+by+plus+sign
         *5 BETWEEN | AT
         *6(7) T1>DATA<T2 | TIMESTAMP
         *7/8 PER
         *8/9 SEC/MIN/HOUR
         */

            /*
                            if(query[5].toLowerCase().equals("at")) {

                }else if(query[5].toLowerCase().equals("between")) {

                }else{
                    System.out.println("Invalid syntax at segment 5.");
                    break;
                }
             */
}
