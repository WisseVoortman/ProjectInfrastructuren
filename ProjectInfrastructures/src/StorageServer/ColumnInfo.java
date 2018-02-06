package StorageServer;

//TODO: Documentation
enum TYPES { Integer, Short, Byte }
public class ColumnInfo {
    public final String columnName;
    public final int offset, length, multiplier;
    public final TYPES type;

    public ColumnInfo(String columnName, int offset, int length, int multiplier, TYPES type) {
        this.columnName = columnName;
        this.offset = offset;
        this.length = length;
        this.multiplier = multiplier;
        this.type = type;
    }
}