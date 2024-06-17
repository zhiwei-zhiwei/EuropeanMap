// Name: Zhiwei Cao
// Email: zcao72@wisc.edu
// Team: JB
// Role: <Data Wranglers 1>
// TA: Harper
// Lecturer: Gary Dahl
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebReader {
    private ArrayList arrayList_road; // // to store five main road info
    private ArrayList arrayList_conn; // to store the connection info and for reading each web and get data
    private ArrayList arrayList_web; // use the arrayList_conn to get website and scrape data from website
    private ArrayList arrayList; // all information necessary store in this arratlist

//    static final Array[] = {"E05"}



    // constructor
    public WebReader() {
        this.arrayList_road = new ArrayList();
        this.arrayList_conn = new ArrayList();
        this.arrayList_web = new ArrayList();
        this.arrayList = new ArrayList();
    }

    public Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // the fist method is going to get the all information form http://www.elbruz.org/eroads/AGR_2.htm
    // and then get the arraylist-road, and remove these data from whole info, which let me to get the arraylist_conn
    public ArrayList getAllSection(){
        ArrayList arr_connection = new ArrayList();
        ArrayList arr_road = new ArrayList();
        Document a = this.getDocument("http://www.elbruz.org/eroads/AGR_2.htm");
//        System.out.println(a);
        Elements elements = a.select("FONT[FACE=Arial]");
        Elements elements1 = elements.select("A");
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).text().startsWith("E"))
            {
                arrayList_conn.add(elements.get(i).text());
//                System.out.println(elements.get(i).text());
            } else if (elements.get(i).text().contains("road")){
                arrayList_road.add(elements.get(i).text());
            }
        }
        return arrayList_conn;
    }

    public ArrayList getAllRoad(){
        ArrayList arr_connection = new ArrayList();
        ArrayList arr_road = new ArrayList();
        Document a = this.getDocument("http://www.elbruz.org/eroads/AGR_2.htm");
//        System.out.println(a);
        Elements elements = a.select("FONT[FACE=Arial]");
        Elements elements1 = elements.select("A");
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).text().startsWith("E"))
            {
                arrayList_conn.add(elements.get(i).text());
//                System.out.println(elements.get(i).text());
            } else if (elements.get(i).text().contains("road")){
                arrayList_road.add(elements.get(i).text());
            }
        }
        return arrayList_road;
    }

    private ArrayList roadReading(String road){
        ArrayList array = new ArrayList();
//        String[]

        return array;
    }

    public ArrayList getAllWeb(){
        WebReader web = new WebReader();
        ArrayList arr = web.getAllSection();
        String url = "";
        try {
//            System.out.println(arr);
            for (int i = 0; i < arr.size(); i++) {
//                System.out.println(arr.get(i));
                url = "http://www.elbruz.org/eroads/" + arr.get(i) +".htm";
//                System.out.println(url);
                arrayList_web.add(url);
            }
//            System.out.println(arrayList_web);
        }catch (Exception e){
            System.out.println("fall!");
        }
        return arrayList_web;
    }

    // the method is use the arraylist_conn to connect each website, and then get all these website info
    // into a new arraylist
    public ArrayList printData(){
        try {
            WebReader web = new WebReader();
            ArrayList arr = web.getAllWeb();
//            System.out.println(arr.size());
            arrayList.add(" ");
            for (int i = 0; i < arr.size(); i++) {
//                System.out.println(arr.get(i));
                Document doc = web.getDocument(arr.get(i).toString());
                Elements elements = doc.select("TD");
                for (int j = 5; j < elements.size(); j++) {
//                    if (elements.get(j).text())
                    arrayList.add(elements.get(j).text());
//
                }
                System.out.println("Loading "+arr.get(i)+"...");
            }
        } catch (Exception e){
            System.out.println("get Data fail!");
        }
//        System.out.println(arrayList);
        return arrayList;
    }

    // using the data from the website and store them into a txt file which let us use these data
    public void saveData() throws IOException {
        WebReader WebReader = new WebReader();
        ArrayList<String> arr_data = WebReader.printData();
//        System.out.println(arr_data);
        BufferedWriter bw = new BufferedWriter(new FileWriter("eMapData.txt"));
        for (int i = 0; i < arr_data.size(); i++) {
            String str = arr_data.get(i);
            bw.write(str);
            if (i % 5 == 0) {
                bw.newLine();
            } else {
                bw.write(",");
            }
            bw.flush();
        }
        bw.close();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("PROCESSING...");
        WebReader t = new WebReader();
        t.saveData();
        for (int i = 0; i < 5; i++) {
            System.out.println(".");
        }
        System.out.println("DONE, data from website already be stored in eMapData.txt");
    }

}
