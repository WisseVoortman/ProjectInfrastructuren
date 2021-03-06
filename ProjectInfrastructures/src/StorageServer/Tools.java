package StorageServer;

import java.util.HashMap;

/**
 * Class used for simple functions
 */
public class Tools {
    public static short readShort(byte[] data, int offset) {
        return (short) (((data[offset] << 8)) | ((data[offset + 1] & 0xff)));
    }

    public static byte[] shortToByteArray(short s) {
        return new byte[] { (byte) ((s & 0xFF00) >> 8), (byte) (s & 0x00FF) };
    }

    /**
     * Stores information about all known columns
     */
    public static HashMap<String, ColumnInfo> FIELD_LIST;
    static {
        FIELD_LIST = new HashMap<>();
        FIELD_LIST.put("timestamp",
                new ColumnInfo("timestamp", 0, 4, 1, TYPES.Integer, false));

        FIELD_LIST.put("temperature",
                new ColumnInfo("temperature", 4, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("dewpoint",
                new ColumnInfo("dewpoint", 6, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("airpressureatstation",
                new ColumnInfo("airpressureatstation", 8, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("airpressureatsea",
                new ColumnInfo("airpressureatsea", 10, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("visibility",
                new ColumnInfo("visibility", 12, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("windspeed",
                new ColumnInfo("windspeed", 14, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("precipitation",
                new ColumnInfo("precipitation", 16, 2, 100, TYPES.Short, false));

        FIELD_LIST.put("snowfallen",
                new ColumnInfo("snowfallen", 18, 2, 10, TYPES.Short, false));

        FIELD_LIST.put("special",
                new ColumnInfo("special", 20, 1, 1, TYPES.Byte, false));

        FIELD_LIST.put("cloudiness",
                new ColumnInfo("cloudiness", 21, 2, 10, TYPES.Short, true));

        FIELD_LIST.put("winddirection",
                new ColumnInfo("winddirection", 23, 2, 1, TYPES.Short, false));
    }
}

