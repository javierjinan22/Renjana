package Postingan;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

//Import class dari package lain
import Model.DaftarPostingan;
import plesiranoke.MyListener;

public class ItemPostingan {

    @FXML
    private Label judul;

    @FXML
    private Label deskripsi;

    @FXML
    private ImageView gambar;

    private DaftarPostingan postingan;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(post);
    }
    
    private DaftarPostingan post;
    private MyListener myListener;
    
    public void setData(DaftarPostingan postingan, MyListener myListener) {
        this.postingan = postingan;
        this.myListener = myListener;
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
