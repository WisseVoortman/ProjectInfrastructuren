package StorageServer;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Stores query state
 */
enum STATE {
    UNMODIFIED(0), OK(1), ERROR(2), ERROR_WITH_DATA(3);
    private final int value;
    STATE(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

/**
 * Class that contains the result of a query and transmits it
 */
public class QueryResult {
    private STATE status;
    private int stationNumber;
    private String error;
    private ArrayList<QueryCol> results;

    public QueryResult(){init();}

    /**
     * @param stationNumber Stationnumber that this query belongs to
     */
    public QueryResult(int stationNumber){ this.stationNumber = stationNumber; init();}

    private void init() {
        this.status = STATE.UNMODIFIED; // Unmodified state
        if(this.stationNumber == 0) // No ID has been provided
            this.stationNumber = -1;
        this.error = ""; // Initialize
        this.results = new ArrayList<>();
    }

    /**
     * Used for setting the station number
     * @param stationNumber The station number you want to assign to this result
     */
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    /**
     * Used for assigning a state to this query
     * @param status The state you want to assign to this result
     */
    public void setStatus(STATE status) {
        this.status = status;
    }

    /**
     * Used for setting an error message
     * @param message The message you want to set
     */
    public void setErrorMessage(String message) {
        this.error = message;
    }

    /**
     * Used for adding results to this query
     * @param qCol The column you want to add to the results
     */
    public synchronized void addResult(QueryCol qCol) {
        this.results.add(qCol);
    }

    /**
     * The method that sends the results to the client
     * @param out PrintWriter stream belonging to this client
     */
    public synchronized void writeToStream(PrintWriter out, boolean addComma) {
        String output = "{";
        int i = 0;
        for(QueryCol col : results) {
            i++;
            switch(col.column.type)  {
                case Byte:
                    output += "\"" + col.column.columnName + "\":" + (byte)col.val + (i == results.size() ? "" : ", ");
                    break;

                case Short:
                    output += "\"" + col.column.columnName + "\":" + Float.parseFloat(col.val.toString()) / col.column.multiplier + (i == results.size() ? "" : ", ");
                    break;

                case Integer:
                    output += "\"" + col.column.columnName + "\":" + (int)col.val + (i == results.size() ? "" : ", ");
                    break;
            }
        }
        output += "}" + (addComma ? "," : "");
        results.clear();
        System.out.println(output);
        out.print(output);
    }

    /**
     * Method used to write an error to the client
     * @param out PrintWriter stream used for writing
     * @param message Message to send to the client
     */
    public synchronized void writeError(PrintWriter out, String message) {
        out.println("[ERROR]" + message);
    }
}
