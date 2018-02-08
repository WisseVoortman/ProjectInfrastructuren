package StorageServer;

import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Documentation
@SuppressWarnings("Duplicates")
public class QueryExecutor {
    private ServerMain model;
    private QueryClient client;
    private String stationNumber;
    private String[] query;
    private PrintWriter out;

    public QueryExecutor(ServerMain model, QueryClient client, String stationnumber, String[] query, PrintWriter out) {
        this.model = model;
        this.client = client;
        this.stationNumber = stationnumber;
        this.query = query;
        this.out = out;
    }

    public void executeQuery() {
        try {
            int timeFrom = Integer.parseInt(query[6]);
            int timeTo = Integer.parseInt(query[8]);
            byte[] Short = new byte[2];
            byte[] Int = new byte[4];
            byte[] Byte = new byte[1];

            // Get DateObjects from time strings
            Date dateFrom = new Date(timeFrom * 1000L);
            Date dateTo = new Date(timeTo * 1000L);

            // Generate filenames based on dates


            // Get a list of all necessary files

        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    //TODO: Add per clause

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

            /*
                            if(query[5].toLowerCase().equals("at")) {

                }else if(query[5].toLowerCase().equals("between")) {

                }else{
                    System.out.println("Invalid syntax at segment 5.");
                    break;
                }
             */
}
