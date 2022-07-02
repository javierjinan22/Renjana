package plesiranoke;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

//import class DaftarToko
import Model.DaftarToko;
import Model.ArrayList;

public class TambahTokoController implements Initializable {

    ArrayList<DaftarToko> daftarToko = new ArrayList<DaftarToko>();

    @FXML
    private TextField tfNamaToko;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNoTelfon;

    XStream xstream = new XStream(new StaxDriver());

    void openTabel() {
        FileInputStream berkasMasuk;
        try {
            berkasMasuk = new FileInputStream("berkas2.xml");
            int isi;
            char c;
            String s = "";
            while ((isi = berkasMasuk.read()) != - 1) {
                c = (char) isi;
                s = s + c;
            }
            daftarToko = (ArrayList<DaftarToko>) xstream.fromXML(s);
            berkasMasuk.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    void simpanData() {
        String xml = xstream.toXML(daftarToko);
        FileOutputStream berkasKeluar;

        try {
            byte[] info = xml.getBytes("UTF-8");
            berkasKeluar = new FileOutputStream("berkas2.xml");
            berkasKeluar.write(info);
            berkasKeluar.close();

        } catch (Exception io) {
            System.out.println("Terjadi kesalahan : " + io.getMessage());
        }
    }

    @FXML
    private void unggah(ActionEvent event) {
        XStream xstream = new XStream(new StaxDriver());
        String namaToko = tfNamaToko.getText();
        String noTelfon = tfNoTelfon.getText();
        String alamat = tfAlamat.getText();
        String email = tfEmail.getText();

        openTabel();

        daftarToko.add(new DaftarToko(namaToko, alamat, email, noTelfon));
        
        simpanData();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openTabel();
    }
}
