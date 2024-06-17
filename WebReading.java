// --== CS400 File Header Information ==--
// Name: Jiahe Jin
// Email: jjin82@wisc.edu
// Team: JB
// Role: Data Wrangler 2
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: none

import java.io.IOException;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * This class is going to scrape data directly from the website. It also implements the Serializable
 * to store the data.
 */
public class WebReading implements java.io.Serializable {
        private static final long serialVersionUID = -8579870131102876214L;
    private ArrayList roadNames;
    private ArrayList cityNames;
    private ArrayList twoCityUnits;

    /**
     * The default constructor comes to assign the roadNames by loadingRoads method.
     *
     * @throws IOException when failing to update.
     */
    public WebReading() throws IOException {
        this.roadNames = new ArrayList();
        this.cityNames = new ArrayList();
        this.twoCityUnits = new ArrayList();
        this.update();
    }

    /**
     * The twoCityUnits accessor method
     *
     * @return the ArrayList of roadNames
     */
    public ArrayList getTwoCityUnits() {
        return twoCityUnits;
    }

    /**
     * The RoadNames accessor method
     *
     * @return the ArrayList of roadNames
     */
    public ArrayList getRoadNames() {
        return roadNames;
    }

    /**
     * The cityNames accessor method
     *
     * @return the ArrayList of cityNames
     */
    public ArrayList getCityNames() {
        return cityNames;
    }

    /**
     * This method will load the pageContent in two format: only text, and asXml.
     *
     * @param ifText true when pageContent only contains text; false if the pageContent contains xml.
     * @param url    the html url pathway for the expected loading page
     * @return the pageContent.
     * @throws IOException when failing to scrape the content from the webpage.
     */
    private String pageContent(boolean ifText, String url) throws IOException {
        String baseUrl = url;
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = webClient.getPage(baseUrl);
        if (ifText) {
            return page.asText();
        } else {
            return page.asXml();
        }
    }

    /**
     * This method helps to load all E-roads into the ArrayList.
     *
     * @return the ArrayList of E-roads.
     * @throws IOException when failing to use method this.pageContent().
     */
    private ArrayList loadingRoads() throws IOException {
        String[] roadNames =
            this.pageContent(true, "http://www.elbruz.org/eroads/AGR_2.htm").split("\n");
        for (int i = 0; i < roadNames.length; i++) {
            if (roadNames[i].strip().startsWith("E")) {
                this.roadNames.add(roadNames[i].strip());
            }
        }
        return this.roadNames;
    }

    /**
     * This method intends to read the specific road (e.g. E05) by calling its name.
     *
     * @param road the name of the road.
     * @return the ArrayList of City Object.
     * @throws IOException when failing to use method this.pageContent().
     */
    private ArrayList roadReading(String road) throws IOException {
        ArrayList cityList = new ArrayList();
        String[] cityInformationList =
            this.pageContent(true, "http://www.elbruz.org/eroads/" + road + ".htm").split("\n");
        for (int i = 0; i < cityInformationList.length; i++) { // each line of city information
            String[] cityInformation = cityInformationList[i].split("\\s{2,}");
            if (cityInformation[0].equals(cityInformation[0].toUpperCase()) && !cityInformation[0]
                .contains("0") && !cityInformation[0].contains("1") && !cityInformation[0]
                .contains("2") && !cityInformation[0].contains("3") && !cityInformation[0]
                .contains("4") && !cityInformation[0].contains("5") && !cityInformation[0]
                .contains("6") && !cityInformation[0].contains("7") && !cityInformation[0]
                .contains("8") && !cityInformation[0].contains("9")) {
                ArrayList connection = new ArrayList();
                if (cityInformation.length > 4) {
                    for (int j = 4; j < cityInformation.length; j++) {
                        connection.add(cityInformation[j].split(",")[0]);
                    }
                }
                City city =
                    new City(cityInformation[1], connection, Integer.valueOf(cityInformation[2]));
                cityList.add(city);

                //Adding the name of the city in the ArrayList.
                if (!cityNames.contains(city.name)) { // Not Repeat
                    cityNames.add(city.name);
                }
            }
        }
        return cityList;
    }


    /**
     * This method will upload all relative data about cities, distance, and roads into Two cities
     * Units which will be stored in the ArrayList.
     *
     * @throws IOException when failing to load roads.
     */
    public void update() throws IOException {
        this.roadNames = this.loadingRoads();
        for (Object roadName : roadNames) {
            ArrayList cityList = this.roadReading((String) roadName);
            for (int i = 0; i < cityList.size(); i++) {
                if (i + 1 < cityList.size()) {
                    this.twoCityUnits.add(
                        new TwoCitiesUnit((City) cityList.get(i), (City) cityList.get(i + 1),
                            (String) roadName));
                }

            }
            System.out.println("The " + roadName + " is successfully loaded.");
        }
    }
}