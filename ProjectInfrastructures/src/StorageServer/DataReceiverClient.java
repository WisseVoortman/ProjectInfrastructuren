package StorageServer;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
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
            BufferedWriter bw = null;
            FileWriter fw = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            Date date = sdf.parse( m.date + " " + m.time);

            // Byte buffer
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            baos.write( ByteBuffer.allocate(4).putInt((int)(date.getTime() /1000L )).array() );
            baos.write( Tools.shortToByteArray( Short.parseShort(Integer.toString(Math.round(m.temperature*10))) ));
            baos.write( Tools.shortToByteArray( Short.parseShort(Integer.toString(Math.round(m.dewpoint*10))) ) );
            baos.write( Tools.shortToByteArray( (short) (m.airpresurestationlevel * 10) ));
            baos.write( Tools.shortToByteArray( (short) (m.airpresuresealevel * 10) ) );
            baos.write( Tools.shortToByteArray( (short) (m.visability * 10) ) );
            baos.write( Tools.shortToByteArray( (short) (m.windspeed * 10) ) );
            baos.write( Tools.shortToByteArray( (short) (m.perception * 100) ) );
            baos.write( Tools.shortToByteArray( (short) (m.snowfallen * 10) ) );

            String bitMask = Integer.toString(m.specialcircumstances);
            int MASK1 = Integer.parseInt(bitMask, 2);
            int MASK2 = Integer.parseInt("00111111", 2);
            baos.write( ByteBuffer.allocate(1).put((byte)  (MASK1 & MASK2)).array());

            baos.write( Tools.shortToByteArray( (short) (m.cloudiness * 10) ) );
            baos.write( Tools.shortToByteArray( (short) m.winddirection) );

            // Write buffer to array
            byte[] dataList = baos.toByteArray();

            // Make sure the directory exists
            File dir = new File(this.model.CUR_PATH + m.stationnumber + "\\");
            dir.mkdirs();

            try {
                File file = new File(this.model.CUR_PATH + m.stationnumber + "\\" + m.stationnumber + "_" + m.date + ".dat");

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    boolean t = file.createNewFile();
                }

                try (FileOutputStream output = new FileOutputStream(file, true)) {
                    baos.writeTo(output);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null)
                        bw.close();
                    if (fw != null)
                        fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
