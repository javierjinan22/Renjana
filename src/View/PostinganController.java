package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;

//XStream Package
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;

//Import class dari package lain
import Model.DaftarPostingan;
import Model.ArrayList;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PostinganController implements Initializable {

    XStream xstream = new XStream(new StaxDriver());

    int column, row;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private Image gambar;
    
    @FXML
    private Label postTitle;

    @FXML
    private Label postDescription;

    ArrayList<DaftarPostingan> dataPostingan = getDataPostingan();

    ArrayList<DaftarPostingan> getDataPostingan() {
        FileInputStream berkasMasuk = null;
        try {
            berkasMasuk = new FileInputStream("dataPostingan.xml");
            int isi;
            char c;
            // isi file dikembalikan menjadi string
            String s = "";
            while ((isi = berkasMasuk.read()) != - 1) {
                c = (char) isi;
                s = s + c;
            }
            // string isi file dikembalikan menjadi larik double
            return dataPostingan = (ArrayList<DaftarPostingan>) xstream.fromXML(s);
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (berkasMasuk != null) {
                try {
                    berkasMasuk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    //Handlerr >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Declaring post============================================================
    private ArrayList<DaftarPostingan> posts = new ArrayList<>();
    private Image image;
    
    void setPostinganDefault() {
        DaftarPostingan post;
        post = new DaftarPostingan();
        post.setJudul("Keindahan Puncak Sosok di Kala Senja");
        post.setDeskripsi("Jangan lewatkan indahnya pemandangan Puncak Sosok di kala senja");
//        post.setImgSrc(imgSrc);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
