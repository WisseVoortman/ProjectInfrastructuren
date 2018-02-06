package StorageServer;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Documentation
@SuppressWarnings("Duplicates")
public class QueryExecutorSingle{
    private ServerMain model;
    private QueryClient client;
    private String stationNumber;
    private String[] query;

    public QueryExecutorSingle(ServerMain model, QueryClient client, String stationnumber, String[] query) {
        this.model = model;
        this.client = client;
        this.stationNumber = stationnumber;
        this.query = query;
    }

    public void executeQuery() {
        try {


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
