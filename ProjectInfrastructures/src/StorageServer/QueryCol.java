package StorageServer;

/**
 * Class used for temporary storage of queried data
 */
class QueryCol {
    public final ColumnInfo column;
    public final Object val;
    public final int timestamp;

    public QueryCol(ColumnInfo column, Object val, int timestamp) {
        this.column = column;
        this.val = val;
        this.timestamp = timestamp;
    }
}