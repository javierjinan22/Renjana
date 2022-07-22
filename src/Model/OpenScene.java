package Model;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import Main.Plesiranoke;

public class OpenScene {

    private Pane halaman;

    public Pane getPane(String namaFile) {
        try {
            URL fileHalaman = Plesiranoke.class.getResource(namaFile + ".fxml");
            if (fileHalaman == null) {
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }

            halaman = FXMLLoader.load(fileHalaman);
        } catch (Exception e) {
            System.out.println("Tidak ditemukan halaman tersebut");
        }
        return halaman;
    }
}
