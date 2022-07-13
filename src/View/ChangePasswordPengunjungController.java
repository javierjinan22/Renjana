package View;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//Import class lain
import Model.DataIndex;
import Model.Pengguna;

//XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ChangePasswordPengunjungController implements Initializable {

    @FXML
    private Label warning;
    
    @FXML
    private Button medal;

    @FXML
    private PasswordField passLama;

    @FXML
    private PasswordField passBaru;

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

    void simpanData() {
        XStream xstream = new XStream(new StaxDriver());
        String xml = xstream.toXML(dataRegistration);
        FileOutputStream berkasBaru = null;
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            berkasBaru = new FileOutputStream("dataRegistration.xml");

            // mengubah karakter penyusun string xml sebagai 
            // bytes (berbentuk nomor2 kode ASCII
            byte[] bytes = xml.getBytes("UTF-8");

            //Menyimpan file dari bytes
            berkasBaru.write(bytes);
        } catch (Exception e) {
            System.err.println("Perhatian : " + e.getMessage());
        } finally {
            if (berkasBaru != null) {
                try {
                    berkasBaru.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    DataIndex di;

    @FXML
    private void changePass(ActionEvent Event) {
        //Pass
        String newPassword = passBaru.getText();
        String oldPassword = passLama.getText();
        
        //Other Components
        String nama = dataRegistration.get(di.getData()).getNama();
        String email = dataRegistration.get(di.getData()).getEmail();
        boolean manager = false;
        boolean admin = false;
        
        if (dataRegistration.get(di.getData()).getPassword().equals(oldPassword)) {
            dataRegistration.add(di.getData(), new Pengguna(nama, email, newPassword, manager, admin));
            simpanData();
            Stage page = (Stage) medal.getScene().getWindow();
            page.close();
        } else {
            warning.setText("Password lama anda tidak valid");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        di = new DataIndex();
        bukaXML();
    }

}
