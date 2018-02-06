package StorageServer;

import java.io.PrintWriter;
import java.util.ArrayList;

//TODO: documentation
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
//TODO: Documentation
public class QueryResult {
    private STATE status;
    private int stationNumber;
    private String error;
    private ArrayList<QueryCol> results;

    public QueryResult(){init();}

    public QueryResult(int stationNumber){ this.setStationNumber(stationNumber); init();}

    private void init() {
        this.status = STATE.UNMODIFIED; // Unmodified state
        if(stationNumber == 0) // No ID has been provided
            this.stationNumber = -1;
        this.error = ""; // Initialize
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public void setStatus(STATE status) {
        this.status = status;
    }

    public void setErrorMessage(String message) {
        this.error = message;
    }

    public synchronized void addResult(QueryCol qCol) {
        this.results.add(qCol);
    }

    public synchronized void writeToStream(PrintWriter out) {

    }

}
