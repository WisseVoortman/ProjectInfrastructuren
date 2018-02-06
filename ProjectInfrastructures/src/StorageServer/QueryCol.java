package StorageServer;

class QueryCol {
    private ColumnInfo column;
    private Object val;
    private int timestamp;

    public QueryCol(ColumnInfo column, Object val, int timestamp) {
        this.column = column;
        this.val = val;
        this.timestamp = timestamp;
    }
}