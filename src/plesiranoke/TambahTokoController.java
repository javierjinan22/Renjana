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
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TambahTokoController implements Initializable {

    ArrayList<DaftarToko> daftarToko = new ArrayList<DaftarToko>();

    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Button btnGambar;
    
    @FXML
    private TextField tfNamaToko;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNoTelfon;
    
    @FXML
    private Button mlebet;

    XStream xstream = new XStream(new StaxDriver());
    

    void openTabel() {
        FileInputStream berkasMasuk;
        try {
            berkasMasuk = new FileInputStream("listToko.xml");
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
            berkasKeluar = new FileOutputStream("listToko.xml");
            berkasKeluar.write(info);
            berkasKeluar.close();

        } catch (Exception io) {
            System.out.println("Terjadi kesalahan : " + io.getMessage());
        }
    }
    
    @FXML
    private void handleButtonImg(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        Stage stage = (Stage) anchor.getScene().getWindow();
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Dir: " + file.getAbsolutePath());
            btnGambar.setText(file.toURI().toString());
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
        Stage page = (Stage) mlebet.getScene().getWindow();
        page.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openTabel();
    }
}
