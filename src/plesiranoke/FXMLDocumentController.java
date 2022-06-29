/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package plesiranoke;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Asus ROG
 */
public class FXMLDocumentController implements Initializable {
    
    OpenScene bukaScene = new OpenScene();
    
    @FXML
    private BorderPane mainPane;
    

    @FXML
    void addMarket(ActionEvent event) {
        Pane halaman = bukaScene.getPane("TambahWisata");
        mainPane.setCenter(halaman);
        System.out.println("Button home's Clicked");

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
