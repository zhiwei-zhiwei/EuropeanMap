// Name: Zhiwei Cao
// Email: zcao72@wisc.edu
// Team: JB
// Role: <Data Wranglers 1>
// TA: Harper
// Lecturer: Gary Dahl
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

    private EMapCity[] eMapCities;
    private static String[] line;

    public Data() {
        this.eMapCities = new EMapCity[1454];
    }

    public void update() throws IOException {
        BufferedReader content = new BufferedReader(new FileReader("eMapData.txt"));
        String reader = "";
        int index = 0;
        while ((reader = content.readLine()) != null) {
            line = reader.split(",");
            String country = line[0];
            String city = line[1];
            int km_section = Integer.parseInt(line[2]);
            int km_cuml = Integer.parseInt(line[3]);
            String connection = line[4];
            EMapCity current =
                    new EMapCity(country, city, km_section, km_cuml, connection);
            eMapCities[index] = current;
            index++;
        }
        content.close();
    }

    public EMapCity get(int index) {
        EMapCity something = eMapCities[index];
        return something;
    }

    public EMapCity[] getMapList() {
        return eMapCities;
    }

    public static void main(String[] args) {
        Data data = new Data();
        System.out.println(data.getMapList().length);
    }
}
