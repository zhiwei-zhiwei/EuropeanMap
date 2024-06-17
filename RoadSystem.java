// --== CS400 File Header Information ==--
// Name: Yunzhao Liu
// Email: liu995@wisc.edu
// Team: JB
// Role: Back End Developer 1
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: none

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Objects;

class RoadSystem extends DataReading {
    static class Road {
        Road(String city1, String city2) {
            if (city1.compareTo(city2) < 0) {
                this.city1 = city1;
                this.city2 = city2;
            } else {
                this.city2 = city1;
                this.city1 = city2;
            }
        }

        String city1;
        String city2;

        @Override
        public int hashCode() {
            return city1.hashCode() + city2.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Road road = (Road) obj;
            return (Objects.equals(city1, road.city1) && Objects.equals(city2, road.city2));
        }
    }

    GraphADT<String> graph;
    HashMap<Road, String> map;

    RoadSystem() throws IOException {
        graph = new CS400Graph<>();
        map = new HashMap<>();
        reset();
    }

    /**
     * This method returns the information of shortest path between two cities.
     * 
     * @param cityFrom city to begin
     * @param cityTo   city to end
     * @return a string descript the shortest path
     */
    public String findPath(String cityFrom, String cityTo) throws IllegalArgumentException {
        List<String> list;
        if (!contain(cityFrom)) {
            System.out.println("city: " + cityFrom + " isn't in the system");
            throw new IllegalArgumentException();
        }
        if (!contain(cityTo)) {
            System.out.println("city: " + cityTo + " isn't in the system");
            throw new IllegalArgumentException();
        }
        try {
            list = graph.shortestPath(cityFrom, cityTo);
        } catch (NoSuchElementException e) {
            return "no path from " + cityFrom + " to " + cityTo + '\n';
        }
        StringBuilder sb = new StringBuilder();
        String prevRoad = new String();
        for (int i = 0; i < list.size() - 1; i++) {
            String currentRoad = map.get(new Road(list.get(i), list.get(i + 1)));
            if (!Objects.equals(prevRoad, currentRoad)) {
                sb.append(list.get(i) + " " + currentRoad + " ");
                prevRoad = currentRoad;
            }
        }
        sb.append(list.get(list.size() - 1));
        return sb.toString();
    }

    /**
     * This method adds an additional edge into the graph.
     * 
     * @param city1    city
     * @param city2    another city
     * @param distance the distance betwween cityes
     * @param roadName the name of the road
     */
    public void setRoad(String city1, String city2, int distance, String roadName) {
        if (!contain(city1)) {
            graph.insertVertex(city1);
        }
        if (!contain(city2)) {
            graph.insertVertex(city2);
        }
        if (!contain(city1, city2) || distance <= graph.getWeight(city1, city2)) {
            graph.insertEdge(city1, city2, distance);
            graph.insertEdge(city2, city1, distance);
            map.put(new Road(city1, city2), roadName);
        }
    }

    /**
     * his method removes an edge in the graph.
     * 
     * @param city1 one vertex of the edge
     * @param city2 another vertex of the edge
     * @return return true if the road could be removed
     */
    public boolean removeRoad(String city1, String city2) {
        try {
            graph.removeEdge(city1, city2);
            graph.removeEdge(city2, city1);
            map.remove(new Road(city1, city2));
        } catch (IllegalArgumentException e) {
            if (!contain(city1)) {
                System.out.println("city: " + city1 + " isn't in the system");
            } else if (!contain(city2)) {
                System.out.println("city: " + city2 + " isn't in the system");
            } else if (!contain(city1, city2)) {
                System.out.println("no road between " + city1 + " and " + city2);
            }
            return false;
        }
        return true;
    }

    /**
     * This method reset the graph to default.
     */
    public void reset() {
        map.clear();
        graph = new CS400Graph<>();

        for (TwoCitiesUnit item : (ArrayList<TwoCitiesUnit>) cityUnits) {
            setRoad(item.city1.name, item.city2.name, item.distance, item.roadNumber);
        }
        // add original data from original data to the map and graph
    }

    /**
     * This method return true if the city is in the graph
     * 
     * @param city the city
     * @return return true if the city is in the graph
     */
    public boolean contain(String city) {
        return graph.containsVertex(city);
    }

    /**
     * This method returns true the edge between two cities exists in the graph.
     * 
     * @param city1 one of the vertex in the edge
     * @param city2 another vertex in the edge
     * @return returns true the edge between two cities exists in the graph
     */
    public boolean contain(String city1, String city2) {
        return graph.containsEdge(city1, city2);
    }

    /**
     * get the shortest distance between two cities
     * 
     * @param cityFrom start city
     * @param cityTo   end city
     * @return shortest distance
     * @throws NoSuchElementException throw if no available routine from cityFrom to
     *                                cityTo
     */
    public int distance(String cityFrom, String cityTo) throws NoSuchElementException {
        try {
            return graph.getPathCost(cityFrom, cityTo);
        } catch (NoSuchElementException e) {
            System.out.println("No available path from " + cityFrom + " to " + cityTo);
            throw e;
        }
    }

    /**
     * find all the city names with a prefix in E-Road network
     * 
     * @param prefix the prefix
     * @return list of city names with the prefix
     */
    public List<String> findNameWithPrefix(String prefix) {
        ArrayList<String> list = new ArrayList<>();
        for (String city : (ArrayList<String>) cityNames) {
            if (city.startsWith(prefix)) {
                list.add(city);
            }
        }
        return list;
    }

    /**
     * only for testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        RoadSystem rs;
        try {
            rs = new RoadSystem();
            try {
                System.out.println(rs.findPath("Paris", "Tours"));
                System.out.println(rs.graph.getWeight("Orléans", "Tours"));
            } catch (IllegalArgumentException e) {

            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}