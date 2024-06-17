// Name: Zhiwei Cao
// Email: zcao72@wisc.edu
// Team: JB
// Role: <Data Wranglers 1>
// TA: Harper
// Lecturer: Gary Dahl
public class EMapCity {
    private String county;
    private String city;
    private int km_section;
    private int km_cuml;
    private String connection;

    public EMapCity(String county, String city, int km_section, int km_cuml, String connection) {
        this.county = county;
        this.city = city;
        this.km_section = km_section;
        this.km_cuml = km_cuml;
        this.connection = connection;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getKm_section() {
        return km_section;
    }

    public void setKm_section(int km_section) {
        this.km_section = km_section;
    }

    public int getKm_cuml() {
        return km_cuml;
    }

    public void setKm_cuml(int km_cuml) {
        this.km_cuml = km_cuml;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}
