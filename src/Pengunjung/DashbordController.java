package Pengunjung;

import View.PostinganController;
import View.TokoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

//Import Class dari Package lain
import plesiranoke.OpenScene;
import View.PostinganController;
import View.TokoController;

public class DashbordController implements Initializable {
    OpenScene bukaScene = new OpenScene();
    
    @FXML
    private BorderPane mainPane;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
