package Controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import java.util.ArrayList;

//Import package dr class lain
import Model.Pengguna;

//XML
import java.io.FileInputStream;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import javafx.scene.chart.XYChart;

public class DiagramController implements Initializable {

    @FXML
    private BarChart<String, Integer> bcPengguna;

    LinkedList<Pengguna> dataRegistration = bukaXML();

    LinkedList<Pengguna> bukaXML() {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream buka = null;
        try {
            // nama file yang akan dibuka (termasuk folder jika perlu
            buka = new FileInputStream("dataRegistration.xml");
            // harus diingat objek apa yang dahulu disimpan di file 
            // program untuk membaca harus sinkron dengan program
            // yang dahulu digunakan untuk menyimpannya
            int isi;
            char c;
            // isi file dikembalikan menjadi string
            String s = "";

            while ((isi = buka.read()) != -1) {
                c = (char) isi;
                s = s + c;
            }

            // string isi file dikembalikan menjadi larik double
            dataRegistration = (LinkedList<Pengguna>) xstream.fromXML(s);
        } catch (Exception e) {
            System.err.println("test : " + e.getMessage());
        } finally {
            if (buka != null) {
                try {
                    buka.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
//        Bar Chart
//      Membuat 2 ArrayList untuk menyimpan 3 data pengguna, guna ditampilkan di bcPengguna
        ArrayList<Boolean> jmlhPengunjung = new ArrayList<Boolean>();
        ArrayList<Boolean> jmlhPengelola = new ArrayList<Boolean>();

        for (int i = 0; i < dataRegistration.size(); i++) {
            if (dataRegistration.get(i).getAdmin() == false && dataRegistration.get(i).getManager() == false) {
                jmlhPengunjung.add(true);
            } else if (dataRegistration.get(i).getManager() == true) {
                jmlhPengelola.add(dataRegistration.get(i).getManager());
            }
        }

//        Untuk menyimpan data dalam chart
        XYChart.Series<String, Integer> bcPenggunaS = new XYChart.Series();
        bcPenggunaS.getData().add(new XYChart.Data<>("Pengelola Wisata", jmlhPengelola.size()));
        bcPenggunaS.getData().add(new XYChart.Data<>("Pengunjung", jmlhPengunjung.size()));
        bcPengguna.getData().addAll(bcPenggunaS);
    }
}
