package PiServer;

import ServerApp.Measurement;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.List;

public class XmlParser implements Runnable {
    private ServerMain model;

    public XmlParser(ServerMain model) {
        this.model = model;
    }
    @Override
    public void run() {
        String xml;
        while(true) {
            try {
                xml = this.model.popParseQueue();
                if(xml != null && !xml.isEmpty()) {

                    SAXReader reader = new SAXReader();
                    org.dom4j.Document document = reader.read(new StringReader(xml)); // create a document from the input this.buffer

                    List<org.dom4j.Node> nodes = document.selectNodes("/WEATHERDATA/MEASUREMENT" ); // add all /weatherdata/measurment nodes to the a list

                    for (Node node : nodes) {
                        int stationNumber = Integer.parseInt(correctData(node.selectSingleNode("STN").getText(), "stationnumber", DTYPE.Integer));
                        String date = correctData(node.selectSingleNode("DATE").getText(), "date", DTYPE.String);
                        String time = correctData(node.selectSingleNode("TIME").getText(), "time", DTYPE.String);
                        float temperature = Float.parseFloat(correctData(node.selectSingleNode("TEMP").getText(), "temperature", DTYPE.Float));
                        float dewpoint = Float.parseFloat(correctData(node.selectSingleNode("DEWP").getText(), "dewpoint", DTYPE.Float));
                        float airpressurestationlevel = Float.parseFloat(correctData(node.selectSingleNode("STP").getText(), "airpressurestationlevel", DTYPE.Float));
                        float airpressuresealevel = Float.parseFloat(correctData(node.selectSingleNode("SLP").getText(), "airpressuresealevel", DTYPE.Float));
                        float visibility = Float.parseFloat(correctData(node.selectSingleNode("VISIB").getText(), "visibility", DTYPE.Float));
                        float windspeed = Float.parseFloat(correctData(node.selectSingleNode("WDSP").getText(), "windspeed", DTYPE.Float));
                        float precipitation = Float.parseFloat(correctData(node.selectSingleNode("PRCP").getText(), "precipitation", DTYPE.Float));
                        float snowfallen = Float.parseFloat(correctData(node.selectSingleNode("SNDP").getText(), "snowfallen", DTYPE.Float));
                        int specialcircumstances = Integer.parseInt(correctData(node.selectSingleNode("FRSHTT").getText(), "specialcircumstances", DTYPE.Integer));
                        float cloudiness = Float.parseFloat(correctData(node.selectSingleNode("CLDC").getText(), "cloudiness", DTYPE.Float));
                        int winddirection = Integer.parseInt(correctData(node.selectSingleNode("WNDDIR").getText(), "winddirection", DTYPE.Integer));

                        Measurement m = new Measurement(
                          stationNumber, date, time, temperature,
                                dewpoint, airpressurestationlevel,
                                airpressuresealevel, visibility, windspeed,
                                precipitation, snowfallen, specialcircumstances,
                                cloudiness, winddirection
                        );

                        this.model.pushTransmitQueue(m);
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String correctData(String val, String column, DTYPE dataType) {
        //System.out.println(val + "|" + column );
        return val.isEmpty() ? "0" : val;
    }
}
