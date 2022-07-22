package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Plesiranoke extends Application {
    
//    Anggota Kelompok  :                  NIM
//    1. Javier Jinan Mukti Firjatullah  21523019
//    2. Bima Panjalu Mukti              21523131
//    3. Maretta Endah Prameswari        21523075
//    4. Wildan Syaifudin Ahmad          21523222
    
//    Akun Login    :
//    1. Sebagai Admin
//    email : admin
//    pass  : 123
    
//    2. Sebagai Pengelola Wisata
//    email : YuDjumUyy
//    pass  : qwerty
    
//    3. Sebagai Pengunjung
//    email : SanCross
//    pass  : 354313
    
/*    Dapat juga membuat akun sendiri karena class daftar sudah tersambung 
dengan XML sehingga data otomatis tersimpan dan digunakan untuk login*/
    

    public Plesiranoke() {
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/halamanAwal.fxml"));
        stage.getIcons().add(new Image("gambar/logples.png"));
        stage.setTitle("Plesiran");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
