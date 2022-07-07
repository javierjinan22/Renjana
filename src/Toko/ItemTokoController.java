package Toko;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

//Import class dari package lain
import Model.DaftarToko;
import plesiranoke.MyListener;

public class ItemTokoController {

    @FXML
    private Label namaToko;

    @FXML
    private Label alamat;

    @FXML
    private Label noTelfon;

    @FXML
    private ImageView gambar;

    private DaftarToko market;

//    @FXML
//    private void click(MouseEvent mouseEvent) {
//        MyListener.onClickListener(toko);
//    }

    private DaftarToko toko;
    private MyListener MyListener;

    public void setData(DaftarToko market, MyListener MyListener) {
        this.market = market;
        this.MyListener = MyListener;
        namaToko.setText(market.getNamaToko());
        alamat.setText(market.getAlamat());
        noTelfon.setText(market.getNoTelfon());

        if (market.getImgSrc().equals("")) {
        } else {
            Image image;
            if (market.getOri()) {
                image = new Image(getClass().getResourceAsStream(market.getImgSrc()));
            } else {
                image = new Image(market.getImgSrc());
            }
            gambar.setImage(image);
        }
    }
}
