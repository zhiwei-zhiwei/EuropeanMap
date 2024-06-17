// --== CS400 File Header Information ==--
// Name: Jiahe Jin
// Email: jjin82@wisc.edu
// Team: JB
// Role: Data Wrangler 2
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: none
import java.util.ArrayList;

/**
 * This class is the node of a city which includes the name of a city, and other roads connected to
 * this city. It also implements the Serializable to store the data for it.
 */
public class City implements java.io.Serializable {
    protected String name;
    protected ArrayList connection;
    protected int distanceToPrevious;

    /**
     * The non-default constructor set up the city name and its road connection for this city.
     *
     * @param name       the city name
     * @param connection the E-roads connected to this city
     */
    public City(String name, ArrayList connection, int distanceToPrevious) {
        this.name = name;
        this.connection = connection;
        this.distanceToPrevious = distanceToPrevious;
    }
}