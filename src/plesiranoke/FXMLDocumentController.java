package plesiranoke;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import View.TabelTokoController;
import java.util.LinkedList;
import java.util.ArrayList;

//import class dari package lain
import View.PostinganController;
import View.TabelPostinganController;
import View.TokoController;
import Admin.OnlyDashboardAdminController;
import Model.DaftarPostingan;
import Model.DataIndex;
import Model.Pengguna;
import View.HalamanAwalController;
import View.TabelUserController;

//File XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FXMLDocumentController implements Initializable {

    OpenScene bukaScene = new OpenScene();

    @FXML
    private BarChart bcDiagram;

    @FXML
    private ChoiceBox<String> cbDiagram;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label jenisUser;

    @FXML
    private Label namaUser;

    private String[] kunjungan = {"toko", "wisata"};

    XYChart.Series databc = new XYChart.Series<>();
    
//    Ganti Scene
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
        Pane pane = FXMLLoader.load(OnlyDashboardAdminController.class.getResource("OnlyDashboardAdmin.fxml"));
//        Pane halaman = bukaScene.getPane("/View/Postingan");
        mainPane.setCenter(pane);
        System.out.println("Button Dashboard's Clicked");
    }

    @FXML
    void diagram(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(DiagramController.class.getResource("Diagram.fxml"));
//        Pane halaman = bukaScene.getPane("/View/Postingan");
        mainPane.setCenter(pane);
        System.out.println("Button Diagram's Clicked");
    }

//    New Windows
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
    void dataPost(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TabelPostinganController.class.getResource("TabelPostingan.fxml"));

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Data Postingan");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

    @FXML
    void dataToko(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TabelTokoController.class.getResource("TabelToko.fxml"));

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Data Toko");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }
    
    @FXML
    void dataUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TabelUserController.class.getResource("TabelUser.fxml"));

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Data User");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        // Show the dialog and wait until the user closes it 
        dialogStage.showAndWait();
    }

//    Tombol Log Out
    @FXML
    void logOut(ActionEvent event) throws IOException {
//        Langsung ganti windows tanpa set status online karena akun admin dideklarasi langsung
        Parent tableViewParent = FXMLLoader.load(HalamanAwalController.class.getResource("halamanAwal.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Untuk label siapa user yang sedang login
        namaUser.setText("JAVIER");
        jenisUser.setText("ADMIN");

        cbDiagram.setValue("toko");
        cbDiagram.getItems().addAll(kunjungan);

        databc.getData().add(new XYChart.Data("Jan", 570));
        databc.getData().add(new XYChart.Data("Feb", 620));
        databc.getData().add(new XYChart.Data("Mar", 300));
        databc.getData().add(new XYChart.Data("Apr", 640));
        databc.getData().add(new XYChart.Data("Mei", 325));
        databc.getData().add(new XYChart.Data("Jun", 900));
        databc.getData().add(new XYChart.Data("Jul", 870));
        databc.getData().add(new XYChart.Data("Agu", 580));
        databc.getData().add(new XYChart.Data("Sep", 610));
        databc.getData().add(new XYChart.Data("Okt", 300));
        databc.getData().add(new XYChart.Data("Nov", 310));
        databc.getData().add(new XYChart.Data("Des", 296));
        bcDiagram.getData().addAll(databc);

        cbDiagram.setOnAction(this::kunjunganAction);
    }

    public void kunjunganAction(ActionEvent event) {

        if (cbDiagram.getValue().equals("wisata")) {
//            System.out.println("Mingguan");
            databc.getData().remove(0, 12);
            databc.getData().add(new XYChart.Data("Jan", 29));
            databc.getData().add(new XYChart.Data("Feb", 50));
            databc.getData().add(new XYChart.Data("Mar", 30));
            databc.getData().add(new XYChart.Data("Apr", 19));
            databc.getData().add(new XYChart.Data("Mei", 26));
            databc.getData().add(new XYChart.Data("Jun", 21));
            databc.getData().add(new XYChart.Data("Jul", 15));
            databc.getData().add(new XYChart.Data("Agu", 23));
            databc.getData().add(new XYChart.Data("Sep", 15));
            databc.getData().add(new XYChart.Data("Okt", 31));
            databc.getData().add(new XYChart.Data("Nov", 35));
            databc.getData().add(new XYChart.Data("Des", 27));
        } else if (cbDiagram.getValue().equals("toko")) {
//            System.out.println("Bulanan");
            databc.getData().remove(0, 12);
            databc.getData().add(new XYChart.Data("Jan", 570));
            databc.getData().add(new XYChart.Data("Feb", 620));
            databc.getData().add(new XYChart.Data("Mar", 300));
            databc.getData().add(new XYChart.Data("Apr", 640));
            databc.getData().add(new XYChart.Data("Mei", 325));
            databc.getData().add(new XYChart.Data("Jun", 900));
            databc.getData().add(new XYChart.Data("Jul", 870));
            databc.getData().add(new XYChart.Data("Agu", 580));
            databc.getData().add(new XYChart.Data("Sep", 610));
            databc.getData().add(new XYChart.Data("Okt", 300));
            databc.getData().add(new XYChart.Data("Nov", 310));
            databc.getData().add(new XYChart.Data("Des", 296));
        }

    }
}
