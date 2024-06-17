// --== CS400 File Header Information ==--
// Name: Tianwei Bao
// Email: tbao7@wisc.edu
// Team: JB
// Role: Test Engineer 1
// TA: Harper(Tingjia Cao)
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.Test;

public class TestSuite {

    /**
     * This test is used to see if the the number of e-road
     * scraped and stored in cache is correct
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void DataTest1() throws IOException {
        DataReading data = new DataReading();
        if(data.cityUnits.size()!=1245)
            fail();
    }

    /**
     * Choose a TwoCitiesUnit in e-road E05 as an example to check if the data of
     * TwoCitiesUnit is correct
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void DataTest2() throws IOException {
        DataReading data = new DataReading();
        TwoCitiesUnit unit = (TwoCitiesUnit) data.cityUnits.get(0);
        assertTrue(unit.city1.name.equals("Greenock"));
        assertTrue(unit.city2.name.equals("Glasgow"));
        assertTrue(unit.distance==40);
        assertTrue(unit.roadNumber.equals("E05"));
        assertTrue(unit.city1.distanceToPrevious==0);
        assertTrue(unit.city2.distanceToPrevious==40);
        assertTrue(unit.city1.connection.size()==0);
        assertTrue(unit.city2.connection.size()==1);
    }

    /**
     * Choose a TwoCitiesUnit in e-road E35 as an example to check if the data of
     * TwoCitiesUnit is correct
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void DataTest3() throws IOException {
        DataReading data = new DataReading();
        TwoCitiesUnit unit = (TwoCitiesUnit) data.cityUnits.get(70);
        assertTrue(unit.city1.name.equals("Arnhem"));
        assertTrue(unit.city2.name.equals("Emmerich"));
        assertTrue(unit.distance==34);
        assertTrue(unit.roadNumber.equals("E35"));
        assertTrue(unit.city1.distanceToPrevious==70);
        assertTrue(unit.city2.distanceToPrevious==34);
        assertTrue(unit.city1.connection.size()==0);
        assertTrue(unit.city2.connection.size()==0);
    }

    /**
     * Choose a TwoCitiesUnit in e-road E80 as an example to check if the data of
     * TwoCitiesUnit is correct
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void DataTest4() throws IOException {
        DataReading data = new DataReading();
        TwoCitiesUnit unit = (TwoCitiesUnit) data.cityUnits.get(500);
        assertTrue(unit.city1.name.equals("Burgos"));
        assertTrue(unit.city2.name.equals("San Sebastián"));
        assertTrue(unit.distance==246);
        assertTrue(unit.roadNumber.equals("E80"));
        assertTrue(unit.city1.distanceToPrevious==239);
        assertTrue(unit.city2.distanceToPrevious==246);
        assertTrue(unit.city1.connection.size()==1);
        assertTrue(unit.city2.connection.size()==1);
    }

    /**
     * Choose a TwoCitiesUnit in e-road E802 as an example to check if the data of
     * TwoCitiesUnit is correct
     * @throws IOException if the data is not correctly loaded
     */
    public void DataTest5() throws IOException {
        DataReading data = new DataReading();
        TwoCitiesUnit unit = (TwoCitiesUnit) data.cityUnits.get(1200);
        assertTrue(unit.city1.name.equals("Beja"));
        assertTrue(unit.city2.name.equals("Ourique"));
        assertTrue(unit.distance==65);
        assertTrue(unit.roadNumber.equals("E802"));
        assertTrue(unit.city1.distanceToPrevious==77);
        assertTrue(unit.city2.distanceToPrevious==65);
        assertTrue(unit.city1.connection.size()==0);
        assertTrue(unit.city2.connection.size()==0);
    }

    /**
     * This test is used to check the functionality of method findPath
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void testFindPath() throws IOException {
        RoadSystem rs = new RoadSystem();
        String path1 = rs.findPath("Lisboa", "Santarem");
        if(!path1.contains("E80")) {
            fail();
        }
        String path2 = rs.findPath("Roma","Pescara");
        if(!path2.contains("E80")) {
            fail();
        }
        String path3 = rs.findPath("Cherbourg", "Nantes");
        if(!path3.contains("E03")) {
            fail();
        }
        String path4 = rs.findPath("Wien", "Bratislava");
        if(!path4.contains("E58")) {
            fail();
        }
        String path5 = rs.findPath("Klaipéda", "Kaunas");
        if(!path5.contains("E271")) {
            fail();
        }
    }

    /**
     * This test is used to test the functionality of the method Contain
     * which return true if the city is in the graph
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void testContain1() throws IOException {
        RoadSystem rs = new RoadSystem();
        //Check the cities that exist in the road system
        if(!rs.contain("Roma")) {
            fail();
        }
        if(!rs.contain("Minsk")) {
            fail();
        }
        if(!rs.contain("Vilnius")) {
            fail();
        }
        if(!rs.contain("Gomel'")) {
            fail();
        }
        if(!rs.contain("Praha")) {
            fail();
        }
        //Check the cities that don't exist in the road system
        if(rs.contain("Madison")) {
            fail();
        }
        if(rs.contain("Seattle")) {
            fail();
        }
        if(rs.contain("Boston")) {
            fail();
        }
        if(rs.contain("Beijing")) {
            fail();
        }
        if(rs.contain("Tokyo")) {
            fail();
        }
    }

    /**
     * This test is used to test the functionality of the method Contain
     * which returns true if there is road between two given cities in
     * the road system
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void testContain2() throws IOException {
        RoadSystem rs = new RoadSystem();
        //Check the cities that exist in the road system
        if(!rs.contain("Perth","Edinburgh")) {
            fail();
        }
        if(!rs.contain("London","Doncaster")) {
            fail();
        }
        if(!rs.contain("Orange","Narbonne")) {
            fail();
        }
        if(!rs.contain("Barcelona","Tarragona")) {
            fail();
        }
        if(!rs.contain("Murcia","Algeciras")) {
            fail();
        }
        //Check the cities that don't exist in the road system
        if(rs.contain("Madison","Vancouver")) {
            fail();
        }
        if(rs.contain("Seattle","Vancouver")) {
            fail();
        }
        if(rs.contain("Boston","New York")) {
            fail();
        }
        if(rs.contain("Beijing","Moscow")) {
            fail();
        }
        if(rs.contain("Tokyo","Shanghai")) {
            fail();
        }
    }

    /**
     * This test is used to check the functionality of the method reset
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void testReset() throws IOException {
        RoadSystem rs = new RoadSystem();
        rs.reset();
        //Check if the graph or map is empty
        if(rs.graph.isEmpty() || rs.map.isEmpty()) {
            fail();
        }
    }

    /**
     * This test is used to check the functionality of the
     * method setRoad
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void testSetRoad() throws IOException {
        RoadSystem rs = new RoadSystem();
        rs.setRoad("Edinburgh", "London",200, "E60");
        if(!rs.contain("Edinburgh","London")){
            fail();
        }
        rs.setRoad("London", "Paris", 300, "E16");
        if(!rs.contain("London", "Paris")) {
            fail();
        }
        rs.setRoad("Glasgow", "Tours", 900, "E01");
        if(!rs.contain("Glasgow", "Tours")) {
            fail();
        }
    }

    /**
     * This test is used to check the functionality of the method
     * removeRoad
     * @throws IOException if the data is not correctly loaded
     */
    @Test
    public void testRemoveRoad() throws IOException {
        RoadSystem rs = new RoadSystem();
        rs.removeRoad("Greenock", "Glasgow");
        if(rs.contain("Greenock", "Glasgow")) {
            fail();
        }
        rs.removeRoad("Tours", "Poitiers");
        if(rs.contain("Tours", "Poitiers")) {
            fail();
        }
        rs.removeRoad("Burgos", "Madrid");
        if(rs.contain("Burgos", "Madrid")) {
            fail();
        }
    }
}
