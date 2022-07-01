package View;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Import class Pengguna dari Package Model
import Model.Pengguna;

public class LoginController implements Initializable {

    XStream xstream = new XStream(new StaxDriver());

    LinkedList<Pengguna> dataRegistration = bukaXML();

    LinkedList<Pengguna> bukaXML() {
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

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Label warning;

    @FXML
    private Button btLogin;

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
        String email = tfEmail.getText();
        String pass = tfPassword.getText();

        boolean validitasData = false; //untuk menandai kebenaran data
        boolean isAdmin = false; //untuk membedakan jenis akun admin dengan yg lainnya
        boolean isTourist = false; //untuk membedakan jenis akun wisatawan/pengunjung dengan yg lainnya

        for (int i = 0; i < dataRegistration.size(); i++) {
            //Untuk mencocokan kesesuaian data login yg diinput dengan data registrasi yg telah didaftarkan
            if (email.equals(dataRegistration.get(i).getEmail()) && pass.equals(dataRegistration.get(i).getPassword())) {
                dataRegistration.get(i).setStatusOnline(true);
                validitasData = true;
                break;
            } else if (email.equals("admin") && pass.equals("123")) {
                validitasData = true;
                isAdmin = true;
                dataRegistration.get(i).setAdmin(true);
                dataRegistration.get(i).setStatusOnline(true);
            }
        }

        if (validitasData && !isAdmin) { //Akun Pengunjung
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDashboard.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

            // larik double diubah menjadi string dengan format XML
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

        } else if (validitasData && isAdmin) { //Akun Admin
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

            // larik double diubah menjadi string dengan format XML
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
        } else if (validitasData == false) {
            warning.setText("Email/Password anda salah");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
    }

}
