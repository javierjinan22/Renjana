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
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.ArrayList;

import Model.DaftarPostingan;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class TambahWisataController implements Initializable {

    ArrayList<DaftarPostingan> dataPostingan = new ArrayList<DaftarPostingan>();

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField tfJudul;

    @FXML
    private TextField tfDeskripsi;

    @FXML
    private Button btnGambar;

    void openTabel() {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream berkasMasuk;
        try {
            berkasMasuk = new FileInputStream("dataPostingan.xml");
            // harus diingat objek apa yang dahulu disimpan di file 
            // program untuk membaca harus sinkron dengan program
            // yang dahulu digunakan untuk menyimpannya
            int isi;
            char c;
            String s = "";
            while ((isi = berkasMasuk.read()) != - 1) {
                c = (char) isi;
                s = s + c;
            }
            dataPostingan = (ArrayList<DaftarPostingan>) xstream.fromXML(s);
            berkasMasuk.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
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
        String judul = tfJudul.getText();
        String deskripsi = tfDeskripsi.getText();
        String imgSrc = btnGambar.getText();

        DaftarPostingan post = new DaftarPostingan();
        post.setJudul(judul);
        post.setDeskripsi(deskripsi);
        post.setImgSrc(imgSrc);
        post.setOri(false);

        openTabel();

        dataPostingan.add(post);

        tfJudul.setText("");
        tfDeskripsi.setText("");

        String xml = xstream.toXML(dataPostingan);
        FileOutputStream berkasKeluar;

        try {
            byte[] info = xml.getBytes("UTF-8");
            berkasKeluar = new FileOutputStream("dataPostingan.xml");
            berkasKeluar.write(info);
            berkasKeluar.close();

        } catch (Exception io) {
            System.out.println("Terjadi kesalahan : " + io.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openTabel();
    }

}
