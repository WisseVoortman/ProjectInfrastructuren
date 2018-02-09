package StorageServer;

import java.io.*;
import java.net.Socket;
/**
 * Threaded class used for processing incoming data.
 */
public class QueryClient implements Runnable {
    private ServerMain model;
    private Socket clientSocket;

    /**
     * @param model Main model reference
     * @param clientSocket Active client socket
     */
    QueryClient(ServerMain model, Socket clientSocket) {
        this.model = model;
        this.clientSocket = clientSocket;
        System.out.println("Called constructor");
    }

    @Override
    public void run() {
        System.out.println("Called run");
        try{
            // Open stream
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            // Read query
            String query = in.readLine();
            System.out.println("Query: " + query);

            // Split query into individual pieces
            String[] qry = query.trim().split("\\s");

            // Make sure query is has enough segments
            if(qry.length < 7) {
                new QueryResult().writeError(new PrintWriter(this.clientSocket.getOutputStream(), true), "Query is incomplete.");
            }

            // Handle authentication
            int[] stationList = handleAuthentication(qry[0]);
            if(stationList.length == 1 && stationList[0] == -1) {
                new QueryResult().writeError(new PrintWriter(this.clientSocket.getOutputStream(), true), "No user found with this ID.");
            }

            // Handle the query
            executeQuery(qry, stationList);
        } catch (IOException e) {
            System.out.println("Failed to open input stream.");
        } catch (Exception e) {
            System.out.println("Error while handling query.");
            e.printStackTrace();
        }
    }

    /**
     * Handles authentication for client applications
     * @param AuthID Authentication ID provided to the client
     * @return List of stations that the client is allowed to query
     */
    private int[] handleAuthentication(String AuthID) {
        //TODO: Add actual authentication
        File f = new File(this.model.CUR_PATH + "/clients/" + AuthID);
        if(!f.exists())
            return new int[] { -1 };
        try {
            RandomAccessFile raf = new RandomAccessFile(f.getAbsolutePath(), "r");
            raf.seek(0L);
            String temp = raf.readLine();
            String[] list = temp.split("\\,");
            int[] stations = new int[list.length];
            for(int i = 0; i < list.length; i++) {
                stations[i] = Integer.parseInt(list[i]);
            }
            return stations;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[] { -1 };
    }

    /**
     * This method will execute the actual query
     * @param query Sliced user query
     * @param stationList List of stations the user is allowed access to
     */
    private void executeQuery(String[] query, int[] stationList) {
        PrintWriter out = null;
        try {
            String s = "";
            for(String q : query)
                s+= q + " ";
            System.out.println(s);
            // Create a PrintWriter
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            switch (query[1].toLowerCase()) {
                case "select":
                    // Validate all user input
                    String[] fields = query[2].split("\\,");
                    if (fields.length < 1) {
                        new QueryResult().writeError(out, "No fields were requested.");
                        break;
                    }
                    if (!verifyFields(fields)) {
                        new QueryResult().writeError(out, "Invalid columns requested.");
                        break;
                    }
                    if (!query[3].toLowerCase().equals("from")) {
                        new QueryResult().writeError(out, "Invalid syntax at segment 3.");
                        break;
                    }
                    String[] stations = query[4].split("\\,");
                    if (stations.length < 1 || !verifyStations(stations, stationList)) {
                        new QueryResult().writeError(out, "No permissions to access requested stations.");
                        break;
                    }
                    if(!query[5].toLowerCase().equals("between")) {
                        new QueryResult().writeError(out, "Invalid syntax at segment 5.");
                        break;
                    }
                    s = "";
                    for(String q : query)
                        s+= q + " ";
                    System.out.println(s);
                    // Execute the query
                    for(int i = 0; i < stations.length; i++) {
                        //out.print("{");
                        new QueryExecutor(this.model, this, stations[i], query, out).executeFake();
                        /*out.print("{\"station\":" + stations[i] + ",\"info\":[");
                        new QueryExecutor(this.model, this, stations[i], query, out).executeQuery();
                        if(i < (stations.length - 1)) {
                            System.out.println("\tNoLine");
                            out.print("};");
                        }else{
                            System.out.println("New line");
                            out.println("}");
                        }*/
                    }
                    //for(String station : stations)
                    //    new QueryExecutor(this.model, this, station, query, out).executeQuery();
                    break;

                default:
                    new QueryResult().writeError(out, "Invalid syntax at segment 1.");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies whether the requested columns actually exist
     * @param fields list of all fields requested
     * @return whether all fields are available
     */
    private boolean verifyFields(String[] fields) {
        for (String field : fields) {
            if (!Tools.FIELD_LIST.containsKey(field))
                return false;
        }
        return true;
    }

    /**
     * Verifies whether the user is allowed to query the requested stations
     * @param reqStations list of stations the query wants to access
     * @param allowedStations list of stations the user is allowed to access
     * @return whether the user is allowed to query all stations requested
     */
    private boolean verifyStations(String[] reqStations, int[] allowedStations) {
        for( String station : reqStations ) {
            boolean f = false;
            for( int stationC : allowedStations ) {
                if( stationC == Integer.parseInt(station) )
                    f = true;
            }
            if( !f )
                return false;
        }
        return true;
    }
}
