package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

//Import class dari package lain
import Model.DataIndex;
import Model.Pengguna;

//File XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

//New Window for change password
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfilPengunjungController implements Initializable {

    @FXML
    private Label email;

    @FXML
    private Label nama;

    @FXML
    private Label password;

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

    @FXML
    private void changePass(ActionEvent Event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ChangePasswordPengunjungController.class.getResource("/View/ChangePasswordPengunjung.fxml"));

        //        Memblokir jendela lain yang dibuka oleh aplikasi JavaFX
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Change Password");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
        simpanData();
        //untuk menampilkan siapa user yang sedang login
        DataIndex di = new DataIndex();
        nama.setText(dataRegistration.get(di.getData()).getNama());
        email.setText(dataRegistration.get(di.getData()).getEmail());
        password.setText(dataRegistration.get(di.getData()).getPassword());
        
    }

}
