package Pengelola;

import View.PostinganController;
import View.TokoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;

//Import class dari package lain
import plesiranoke.OpenScene;
import plesiranoke.TambahTokoController;
import plesiranoke.TambahWisataController;
import View.PostinganController;
import View.TabelPostinganController;
import View.TokoController;
import Model.DataIndex;
import Model.Pengguna;
import View.HalamanAwalController;

//XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;


public class DashboardPengelolaController implements Initializable {

    OpenScene bukaScene = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label namaUser;

    @FXML
    private Label jenisUser;

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
    void tambahPostingan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TambahWisataController.class.getResource("TambahWisata.fxml"));

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Tambah Postingan");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

    @FXML
    void tambahToko(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TambahTokoController.class.getResource("tambahToko.fxml"));

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Tambah Toko");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

    @FXML
    void lihatPostingan(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(PostinganController.class.getResource("Postingan.fxml"));
//        Pane halaman = bukaScene.getPane("/View/Postingan");
        mainPane.setCenter(pane);
        System.out.println("Button lihat postingan's Clicked");
    }

    @FXML
    void lihatToko(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(TokoController.class.getResource("Toko.fxml"));
//        Pane halaman = bukaScene.getPane("/View/Postingan");
        mainPane.setCenter(pane);
        System.out.println("Button lihat toko's Clicked");
    }

    @FXML
    void dashboard(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(OnlyDashBoardPengelolaController.class.getResource("OnlyDashBoardPengelola.fxml"));
//        Pane halaman = bukaScene.getPane("/View/Postingan");
        mainPane.setCenter(pane);
        System.out.println("Button lihat toko's Clicked");
    }
    
    @FXML
    void logOut(ActionEvent event) throws IOException {
        boolean status = false;
        for (int i = 0; i < dataRegistration.size(); i++) {
            if (dataRegistration.get(i).getStatusOnline() == true) {
                dataRegistration.get(i).setStatusOnline(false);
            }
        }
        simpanData();
        
        Parent tableViewParent = FXMLLoader.load(HalamanAwalController.class.getResource("halamanAwal.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
        simpanData();
        //untuk menampilkan siapa user yang sedang login
        DataIndex di = new DataIndex();
        namaUser.setText(dataRegistration.get(di.getData()).getNama());
        if (dataRegistration.get(di.getData()).getAdmin() == true) {
            jenisUser.setText("Admin");
        } if (dataRegistration.get(di.getData()).getManager() == true) {
            jenisUser.setText("Pengelola");
        } else {
            jenisUser.setText("Pengunjung");
        }
    }
}
