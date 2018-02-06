package StorageServer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
//TODO: documentation
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
    }

    @Override
    public void run() {
        try{
            // Open stream
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));

            // Read query
            String query = "id SELECT stationid+timestamp+temperature+downfall+snowfall FROM 12345+12346 BETWEEN T1 AND T2";//in.readLine();
            System.out.println("Query: " + query);

            // Split query into individual pieces
            String[] qry = query.trim().split("\\s");

            // Handle authentication
            int[] stationList = handleAuthentication(qry[0]);
            if(stationList.length == 1 && stationList[0] == -1)
                throw new Exception("Invalid user");

            // Handle the query
            executeQuery(qry, stationList);

            // Close stream and connection
            in.close(); out.close(); clientSocket.close();

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
        return new int[] {
                22300,
                39550
        };
        //return new int[] { -1 };
    }

    /**
     *
     * @param query
     * @param stationList
     * @return
     */
    private void executeQuery(String[] query, int[] stationList) {
        /*
        If date not in range return error and first possible result
         */
        //TODO: Comments
        //TODO: Add an error on error
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
        try {
            PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream());
            switch (query[1].toLowerCase()) {
                case "select":
                    // Get a list of all the fields
                    String[] fields = query[2].split("\\+");
                    if (fields.length < 1) {
                        System.out.println("No fields requested.");
                        QueryResult tempQR = new QueryResult();
                        tempQR.setStatus(STATE.ERROR);
                        tempQR.setErrorMessage("No fields requested.");
                        tempQR.writeToStream(out);
                        break; // Break when there's no actual requested field
                    }
                    if (!verifyFields(fields)) {
                        System.out.println("Invalid columns requested.");
                        QueryResult tempQR = new QueryResult();
                        tempQR.setStatus(STATE.ERROR);
                        tempQR.setErrorMessage("Invalid columns requested.");
                        tempQR.writeToStream(out);
                        break;
                    }
                    if (!query[3].toLowerCase().equals("from")) {
                        System.out.println("Invalid syntax at segment 3.");
                        QueryResult tempQR = new QueryResult();
                        tempQR.setStatus(STATE.ERROR);
                        tempQR.setErrorMessage("Invalid syntax at segment 3.");
                        tempQR.writeToStream(out);
                        break;
                    }
                    String[] stations = query[4].split("\\+");
                    if (stations.length < 1 || !verifyStations(stations, stationList)) {
                        System.out.println("No permissions to access requested stations.");
                        QueryResult tempQR = new QueryResult();
                        tempQR.setStatus(STATE.ERROR);
                        tempQR.setErrorMessage("No permissions to access requested stations.");
                        tempQR.writeToStream(out);
                        break;
                    }
                    if (query[5].toLowerCase().equals("at") || query[5].toLowerCase().equals("between")) {
                        //TODO: Implement dynamic threading
                        boolean multiRun = false;
                        if (query[5].toLowerCase().equals("at")) {
                            // Calculate probable data usage
                            if ((query[2].length() * query[4].length() * 60) < 8000) {
                                multiRun = true;
                                ArrayList<QueryResult> results = new ArrayList<>();
                                for (String station : stations)
                                    this.model.getQueryPool().execute(new QueryExecutor(
                                            this.model, this, station, query, threadMode.MULTI, results
                                    ));

                                while (results.size() != stations.length)
                                    Thread.yield();

                                for (QueryResult result : results)
                                    result.writeToStream(out);
                            }
                        }
                        if (query[5].toLowerCase().equals("between") || !multiRun) {

                        }

                    } else {
                        System.out.println("Invalid syntax at segment 5.");
                        QueryResult tempQR = new QueryResult();
                        tempQR.setStatus(STATE.ERROR);
                        tempQR.setErrorMessage("Invalid syntax at segment 5.");
                        tempQR.writeToStream(out);
                        break;
                    }
                    break;

                default:
                    System.out.println("Invalid syntax at segment 1.");
                    QueryResult tempQR = new QueryResult();
                    tempQR.setStatus(STATE.ERROR);
                    tempQR.setErrorMessage("Invalid syntax at segment 1.");
                    tempQR.writeToStream(out);
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
        for (String field : fields)
            if (!Tools.FIELD_LIST.containsKey(field))
                return false;
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
