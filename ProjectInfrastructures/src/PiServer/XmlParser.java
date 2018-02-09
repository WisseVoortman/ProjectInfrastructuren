package PiServer;

import ServerApp.Measurement;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser implements Runnable {
    private PiMain model;

    public XmlParser(PiMain model) {
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
                        int stationNumber = Integer.parseInt(node.selectSingleNode("STN").getText());
                        String date = correctData(node.selectSingleNode("DATE").getText(), "date", DTYPE.String, stationNumber);
                        String time = correctData(node.selectSingleNode("TIME").getText(), "time", DTYPE.String, stationNumber);
                        float temperature = Float.parseFloat(correctData(node.selectSingleNode("TEMP").getText(), "temperature", DTYPE.Float, stationNumber));
                        float dewpoint = Float.parseFloat(correctData(node.selectSingleNode("DEWP").getText(), "dewpoint", DTYPE.Float, stationNumber));
                        float airpressurestationlevel = Float.parseFloat(correctData(node.selectSingleNode("STP").getText(), "airpressurestationlevel", DTYPE.Float, stationNumber));
                        float airpressuresealevel = Float.parseFloat(correctData(node.selectSingleNode("SLP").getText(), "airpressuresealevel", DTYPE.Float, stationNumber));
                        float visibility = Float.parseFloat(correctData(node.selectSingleNode("VISIB").getText(), "visibility", DTYPE.Float, stationNumber));
                        float windspeed = Float.parseFloat(correctData(node.selectSingleNode("WDSP").getText(), "windspeed", DTYPE.Float, stationNumber));
                        float precipitation = Float.parseFloat(correctData(node.selectSingleNode("PRCP").getText(), "precipitation", DTYPE.Float, stationNumber));
                        float snowfallen = Float.parseFloat(correctData(node.selectSingleNode("SNDP").getText(), "snowfallen", DTYPE.Float, stationNumber));
                        int specialcircumstances = Integer.parseInt(correctData(node.selectSingleNode("FRSHTT").getText(), "specialcircumstances", DTYPE.Integer, stationNumber));
                        float cloudiness = Float.parseFloat(correctData(node.selectSingleNode("CLDC").getText(), "cloudiness", DTYPE.Float, stationNumber));
                        int winddirection = Integer.parseInt(correctData(node.selectSingleNode("WNDDIR").getText(), "winddirection", DTYPE.Integer, stationNumber));

                        Measurement m = new Measurement( stationNumber, date, time, temperature, dewpoint,
                                airpressurestationlevel, airpressuresealevel, visibility, windspeed, precipitation,
                                snowfallen, specialcircumstances, cloudiness, winddirection);
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

    private String correctData(String val, String column, DTYPE dataType, int station) {
        //System.out.println(val + "|" + column );
        //return val.isEmpty() ? "0" : val;

        if(column.equalsIgnoreCase("date") || column
                .equalsIgnoreCase("time") || column.equalsIgnoreCase("stationnumber"))
            return val;
        ArrayList<Measurement> measurements = this.model.popCompareBuffer(station);
        float avg, maxDiv, value;

        switch(column) {
            case "temperature":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.temperature;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "dewpoint":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.dewpoint;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "airpressurestationlevel":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.airpresurestationlevel;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "airpreassuresealevel":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.airpresuresealevel;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "visibility":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.visability;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "windspeed":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.windspeed;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "precipitation":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.perception;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "snowfallen":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.snowfallen;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "specialcircumstances":
                return val.isEmpty() ? "0" : val;
            case "cloudiness":
                // Get average
                avg = 0.0f;
                for(Measurement m : measurements)
                    avg += m.cloudiness;
                avg = avg / measurements.size();

                // Compare
                maxDiv = avg * 0.2f; // 20% difference
                if(val.isEmpty())
                    val = String.valueOf(avg);
                value = Float.parseFloat(val);

                if(value > avg+maxDiv && maxDiv > 1.5f) {
                    return String.valueOf(avg+maxDiv);
                }else if(value < avg-maxDiv && maxDiv > 1.5f){
                    return String.valueOf((avg-maxDiv));
                }else{
                    return String.valueOf(value);
                }
            case "winddirection":
                return val.isEmpty() ? "0" : val;

            default:
                return val.isEmpty() ? "0" : val;
        }
    }
}
