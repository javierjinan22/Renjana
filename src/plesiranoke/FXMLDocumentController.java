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

//import class dari package lain
import View.PostinganController;
import View.TokoController;

public class FXMLDocumentController implements Initializable {

    OpenScene bukaScene = new OpenScene();
    
    @FXML
    private BarChart bcDiagram;

    @FXML
    private ChoiceBox<String> cbDiagram;

    @FXML
    private BorderPane mainPane;

    private String[] kunjungan = {"toko", "wisata"};
    XYChart.Series databc = new XYChart.Series<>();

    @FXML
    void addMarket(ActionEvent event) {
        Pane halaman = bukaScene.getPane("TambahWisata");
        mainPane.setCenter(halaman);
        System.out.println("Button home's Clicked");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
