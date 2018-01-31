package StorageServer;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math;

import ServerApp.Measurement;

/**
 * Threaded class used for processing incoming data.
 */
public class DataReceiverClient implements Runnable {
    private ServerMain model;
    private Socket clientSocket;

    /**
     * @param model Main model reference
     * @param clientSocket Active client socket
     */
    DataReceiverClient(ServerMain model, Socket clientSocket) {
        this.model = model;
        this.clientSocket = clientSocket;
    }

    public void run() {
        try{
            // Open a stream
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // Serialize object from stream
            Measurement measurement = (Measurement)in.readObject();
            ++this.model.tVar;

            // Close stream and connection
            in.close(); clientSocket.close();

            // Write data to disk
            saveToDisk(measurement);
        } catch (IOException e) {
            System.out.println("Failed to open input stream.");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to find class in stream.");
        }
    }

    /**
     * Used for writing all data from an object to storage
     * @param m Measurement class holding all data that needs to be saved
     */
    private void saveToDisk(Measurement m) {
        try {
            int stationID = m.stationnumber;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            Date date = sdf.parse( m.date + " " + m.time);

            // Byte buffer
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            baos.write( (int)   date.getTime() );
            baos.write( (byte)  Math.floor(m.temperature));
            baos.write( (byte)  (Math.round((m.temperature % 1) * 100)*-1));
            baos.write( (byte)  Math.floor(m.dewpoint));
            baos.write( (byte)  (Math.round((m.dewpoint % 1) * 100)*-1));
            baos.write( (short) (m.airpresurestationlevel * 10));
            baos.write( (short) (m.airpresuresealevel * 10));
            baos.write( (short) (m.visability * 10));
            baos.write( (short) (m.windspeed * 10));
            baos.write( (short) (m.perception * 10));
            baos.write( (short) (m.snowfallen * 10));

            String bitMask = Integer.toString(m.specialcircumstances);
            int MASK1 = Integer.parseInt(bitMask, 2);
            int MASK2 = Integer.parseInt("00111111", 2);
            baos.write( (byte)  (MASK1 & MASK2));

            baos.write( (short) (m.cloudiness * 10));
            baos.write( (short) m.winddirection);

            // Write buffer to array
            byte[] dataList = baos.toByteArray();

            System.out.println(
                    "Timestamp\\Expected: " + date.getTime() / 1000 + "\tSaved as: " + (int)(date.getTime()/1000)
            );
            System.out.println(
                    "Temperature1\\Expected: " + m.temperature + "\tSaved as: " + (byte)(Math.ceil(m.temperature))
            );
            System.out.println(
                    "Temperature2\\Expected: " + m.temperature + "\tSaved as: " + (byte)(Math.round((m.temperature % 1) * 100)*-1)
            );
            System.out.println(
                    "Pressure\\Expected: " + m.airpresurestationlevel + "\tSaved as: " + (short)((m.airpresurestationlevel * 10))
            );
            System.out.println(
                    "Winddir\\Expected; " + m.winddirection + "\tSaved as: " + (short) m.winddirection
            );
            System.out.println(
                    "Special\\Received: " + m.specialcircumstances + "\tBitmask: " + bitMask + "\tMK1: " + MASK1 + "\tMK2: " + MASK2 + "\tFinished: " + (MASK1&MASK2) + "\tSaved: " + (byte)(MASK1&MASK2)
            );

            //TODO: Check if file exists
            //TODO: Try to open file
            //TODO: Write to file

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
