package StorageServer;

/**
 * Used for identifying data types and how to handle them.
 */
enum TYPES { Integer, Short, Byte }

/**
 * Simple class used to store useful information about columns
 */
public class ColumnInfo {
    public final String columnName;
    public final int offset, length, multiplier;
    public final TYPES type;
    public final boolean average;

    public ColumnInfo(String columnName, int offset, int length, int multiplier, TYPES type, boolean average) {
        this.columnName = columnName;
        this.offset = offset;
        this.length = length;
        this.multiplier = multiplier;
        this.type = type;
        this.average = average;
    }
}