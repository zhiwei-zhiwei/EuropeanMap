// --== CS400 File Header Information ==--
// Name: Jiahe Jin
// Email: jjin82@wisc.edu
// Team: JB
// Role: Data Wrangler 2
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: none
import java.io.*;
import java.util.ArrayList;

/**
 * This class intends to load data from website or has already being scraped and stored in the cache.
 */
public class DataReading {
    static final String WebReadingCache = "./.web_reading_cache.e_road_map_system_app_cache";
    protected ArrayList cityUnits;
    protected ArrayList cityNames;
    protected ArrayList roadNames;

    /**
     * The default constructor assign the cityUnits, cityNames, and roadNames ArrayList Object which
     * includes all of relatively important data being used in this application.
     *
     * @throws IOException when failing to load webReading.
     */
    public DataReading() throws IOException {
        WebReading webContent = this.loadWebReading();
        this.cityUnits = webContent.getTwoCityUnits();
        this.cityNames = webContent.getCityNames();
        this.roadNames = webContent.getRoadNames();
    }

    /**
     * write an object to a file using serialization.
     *
     * @param obj      the object written
     * @param filename path of the file that object write to
     */
    static void writeObject(Serializable obj, String filename) {
        try {
            FileOutputStream fo = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(obj);
            out.close();
            fo.close();
        } catch (IOException e) {
            System.out.println("error when write the cache: " + filename);
            e.printStackTrace();
        }
    }

    /**
     * read an object from a file
     *
     * @param filename path of the file read from
     * @return the object
     */
    static Object readObject(String filename) {
        Object ret = null;
        try {
            FileInputStream fi = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fi);
            ret = in.readObject();
            in.close();
            fi.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return ret;
    }

    /**
     * delete a file
     *
     * @param filename the file name
     */
    static void deleteFile(String filename) {
        File file = new File(filename);
        file.delete();
    }

    /**
     * load WeatherTree from cache
     *
     * @return the WeatherTree
     * @throws IOException when failing to creating the WebReading Object
     */
    static WebReading loadWebReading() throws IOException {
        WebReading webReading;
        webReading = (WebReading) readObject(WebReadingCache);
        if (webReading == null) {
            webReading = new WebReading();
            writeObject(webReading, WebReadingCache);
        }
        return webReading;
    }

    /**
     * The tested class used to prove the success of Data Wrangler 2 parts.
     */
    public static void main(String[] args) throws IOException {
        DataReading data = new DataReading();
        System.out.println("The total numbers of cities: " + data.cityNames.size());
        System.out.println("The total numbers of roads: " + data.roadNames.size());
        System.out.println("The total pairs of two cities Unit: " + data.cityUnits.size());
    }

}