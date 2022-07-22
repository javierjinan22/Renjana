package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

//Import class dari package lain
import Model.DaftarPostingan;

public class ItemPostingan {

    @FXML
    private Label judul;

    @FXML
    private Label deskripsi;

    @FXML
    private ImageView gambar;

    private DaftarPostingan postingan;
        
    public void setData(DaftarPostingan postingan) {
//        Untuk mengakses data judul, deskripsi, dan juga gambar dari objek postingan (parameter) agar dapat diterapkan ke
//          dalam template postingan
        this.postingan = postingan;
        judul.setText(postingan.getJudul());
        deskripsi.setText(postingan.getDeskripsi());

        if (postingan.getImgSrc().equals("")) {
        } else {
            Image image;
            if (postingan.getOri()) {
                image = new Image(getClass().getResourceAsStream(postingan.getImgSrc()));
            } else {
                image = new Image(postingan.getImgSrc());
            }
            gambar.setImage(image);
        }
    }
}
