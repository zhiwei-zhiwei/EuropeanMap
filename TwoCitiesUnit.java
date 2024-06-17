// --== CS400 File Header Information ==--
// Name: Jiahe Jin
// Email: jjin82@wisc.edu
// Team: JB
// Role: Data Wrangler 2
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: none


/**
 * This class is the unit to contain information between two cities. It also implements the Serializable
 * to store the data for it.
 */
public class TwoCitiesUnit implements java.io.Serializable {
    protected City city1;
    protected City city2;
    protected int distance;
    protected String roadNumber;

    /**
     * The non-default constructor sets up two cities and the distance between them.
     *
     * @param city1 the City node Class of the first city
     * @param city2 the City node Class of the second city
     */
    public TwoCitiesUnit(City city1, City city2, String roadNumber) {
        this.city1 = city1;
        this.city2 = city2;
        this.roadNumber = roadNumber;
        this.distance = city2.distanceToPrevious;
    }

}