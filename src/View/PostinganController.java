package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;

//XStream Package
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//Import class dari package lain
import Model.DaftarPostingan;
import Postingan.ItemPostingan;
import plesiranoke.MyListener;

public class PostinganController implements Initializable {

    XStream xstream = new XStream(new StaxDriver());

    /*Jika ArrayList memanggil dari package Model, nanti tidak akan bisa memanggil method, seperti pada line 162 (.... .getJudul())
      Seharusnya mengimport java.util*/
    ArrayList<DaftarPostingan> dataPostingan = getDataPostingan();

    int column, row;

    @FXML
    private GridPane grid;

//    @FXML
//    private Image gambar;
    @FXML
    private Label postTitle;

    @FXML
    private Label postDescription;

    @FXML
    private ScrollPane scroll;

    ArrayList<DaftarPostingan> getDataPostingan() {
        FileInputStream berkasMasuk = null;
        try {
            berkasMasuk = new FileInputStream("dataPostingan.xml");
            int isi;
            char c;
            // isi file dikembalikan menjadi string
            String s = "";
            while ((isi = berkasMasuk.read()) != - 1) {
                c = (char) isi;
                s = s + c;
            }
            // string isi file dikembalikan menjadi larik double
            return (ArrayList<DaftarPostingan>) xstream.fromXML(s);
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (berkasMasuk != null) {
                try {
                    berkasMasuk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    void simpanPosts() {
        String xml = xstream.toXML(posts);

        FileOutputStream berkasKeluar = null;

        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            berkasKeluar = new FileOutputStream("dataListPostingan.xml");

            // mengubah karakter penyusun string xml sebagai 
            // bytes (berbentuk nomor2 kode ASCII
            byte[] bytes = xml.getBytes("UTF-8");

            // menyimpan file dari bytes
            berkasKeluar.write(bytes);
        } catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        } finally {
            if (berkasKeluar != null) {
                try {
                    berkasKeluar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void bukaXML() {
        FileInputStream berkasMasuk = null;
        try {
            berkasMasuk = new FileInputStream("dataListPostingan.xml");
            // harus diingat objek apa yang dahulu disimpan di file 
            // program untuk membaca harus sinkron dengan program
            // yang dahulu digunakan untuk menyimpannya
            int isi;
            char c;
            String s = "";
            while ((isi = berkasMasuk.read()) != - 1) {
                c = (char) isi;
                s = s + c;
            }
            posts = (ArrayList<DaftarPostingan>) xstream.fromXML(s);
            berkasMasuk.close();
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (berkasMasuk != null) {
                try {
                    berkasMasuk.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Handlerr >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Declaring post============================================================
    private ArrayList<DaftarPostingan> posts = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    void setPostinganDefault() {
        DaftarPostingan post;
        post = new DaftarPostingan();
        post.setJudul("Keindahan Puncak Sosok di Kala Senja");
        post.setDeskripsi("Jangan lewatkan indahnya pemandangan Puncak Sosok di kala senja");
        post.setImgSrc("/gambar/puncak-sosok-foto-dari-@suryojdb.jpg");
        posts.add(post);

        post = new DaftarPostingan();
        post.setJudul("Oleh-Oleh Khas Yogyakarta");
        post.setDeskripsi("Liburan ke Jogja terasa kurang lengkap jikalau belum membeli gudeg Jogja");
        post.setImgSrc("/gambar/gudeg1 1.png");
        posts.add(post);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //menambah list postingan(di scrollPane) dari dataPostingan ke posts(DaftarPostingan)
        if (!dataPostingan.isEmpty()) {
            DaftarPostingan post;
            for (int i = 0; i < dataPostingan.size(); i++) {
                post = new DaftarPostingan();
                post.setJudul(dataPostingan.get(i).getJudul());
                if (dataPostingan.get(i).getImgSrc().equals("")) {
                    post.setOri(true);
                    post.setImgSrc("/gambar/pexels-aron-visuals-1694621 1.png");
                } else {
                    post.setImgSrc(dataPostingan.get(i).getImgSrc());
                    post.setOri(false);
                }
                post.setDeskripsi(dataPostingan.get(i).getDeskripsi());
                posts.add(post);
                simpanPosts();
            }
        }

        column = 0;
        row = 1;
        try {
            for (int i = 0; i < posts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ItemPostingan.class.getResource("ItemPostingan.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                System.out.println(posts.get(i).getImgSrc());
                System.out.println(posts.get(i).getOri());
                ItemPostingan itemPostingan = fxmlLoader.getController();
                itemPostingan.setData(posts.get(i), myListener);

                if (column == 0) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column, row);
                
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Tambah Postingan
        setPostinganDefault();

        bukaXML();
        simpanPosts();
    }
}
