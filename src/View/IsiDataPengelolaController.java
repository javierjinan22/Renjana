package View;

import Model.Pengguna;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

//Untuk membuka data XML yang disimpan
import java.io.FileInputStream;
import java.io.IOException;

public class IsiDataPengelolaController implements Initializable {

    @FXML
    private ChoiceBox cbProvinsi;

    @FXML
    private ChoiceBox cbjenisWisata;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfKabupaten;

    @FXML
    private TextField tfTelfon;

    LinkedList<Pengguna> dataRegistration = new LinkedList<>(); //inisialisasi LinkedList untuk  menyimpan data Pengelola Wisata yang dibutuhkan saat login
    //setiap node memiliki masing-masing Pengguna

    LinkedList<Pengguna> getLoginData() {
        return dataRegistration;
    }

    Pengguna getUserOnline() {
        for (int i = 0; i < dataRegistration.size(); i++) {
            if (dataRegistration.get(i).getStatusOnline()) {
                return dataRegistration.get(i);
            }
        }
        return null;
    }

    XStream xstream = new XStream(new StaxDriver());

    void bukaXML() {
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
    }

    @FXML
    private void simpanData(ActionEvent Event) {
        String jenisWisata, provinsi;

        String nama = tfNama.getText();
        String kabupaten = tfKabupaten.getText();
        String alamat = tfAlamat.getText();
        String telfon = tfTelfon.getText();

        //        Menentukan provinsi melalui Choice Bar
        if (cbProvinsi.getValue().equals("D.I.Yogyakarta")) {
            provinsi = "D.I.Yogyakarta";
        } else if (cbProvinsi.getValue().equals("Jawa Tengah")) {
            provinsi = "Jawa Tengah";
        } else if (cbProvinsi.getValue().equals("Jawa Barat")) {
            provinsi = "Jawa Barat";
        } else if (cbProvinsi.getValue().equals("Jawa Timur")) {
            provinsi = "Jawa Timur";
        } else {
            provinsi = "D.K.I.Jakarta";
        }
        
        //        Menentukan Jenis Wisata melalui Choice Bar
        if (cbProvinsi.getValue().equals("D.I.Yogyakarta")) {
            provinsi = "D.I.Yogyakarta";
        } else if (cbProvinsi.getValue().equals("Jawa Tengah")) {
            provinsi = "Jawa Tengah";
        } else if (cbProvinsi.getValue().equals("Jawa Barat")) {
            provinsi = "Jawa Barat";
        } else if (cbProvinsi.getValue().equals("Jawa Timur")) {
            provinsi = "Jawa Timur";
        } else {
            provinsi = "D.K.I.Jakarta";
        }
        
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
        cbProvinsi.getItems().addAll("D.I.Yogyakarta", "Jawa Tengah", "Jawa Barat", "Jawa Timur", "D.K.I.Jakarta");
    }

}
