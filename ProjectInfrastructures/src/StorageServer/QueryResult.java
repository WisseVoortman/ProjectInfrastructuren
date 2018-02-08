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

    public QueryResult(int stationNumber){ this.stationNumber = stationNumber; init();}

    private void init() {
        this.status = STATE.UNMODIFIED; // Unmodified state
        if(this.stationNumber == 0) // No ID has been provided
            this.stationNumber = -1;
        this.error = ""; // Initialize
        this.results = new ArrayList<>();
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
        String output = "";
        output += status + "|" + this.error + "|" + this.stationNumber + "|" + this.results.size() + "|";
        for(QueryCol col : results) {
            switch(col.column.type)  {
                case Byte:
                    output += col.column.columnName + ":" + (((byte)col.val) / col.column.multiplier) + "|";
                    break;

                case Short:
                    output += col.column.columnName + ":" + (((short)col.val) / col.column.multiplier) + "|";
                    break;

                case Integer:
                    output += col.column.columnName + ":" + (((int)col.val) / col.column.multiplier) + "|";
                    break;
            }
        }
        results.clear();
        System.out.println(output);
        out.print(output);
    }

    public synchronized void writeToStream(PrintWriter out, ArrayList<String> sL) {
        String sN = this.status.getValue() + "|" + this.error + "|" + sL.size() + "|";
        for(String s : sL)
            sN += s+";";
        out.println(sN);
        //out.println( + this.results.size() + "\r\n");
    }

}
