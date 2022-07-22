package Controller;

import Model.DaftarPostingan;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;

//XStream Package
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//Import class dari package lain
import Model.DaftarToko;
import java.io.IOException;

//Atribut yang diperlukan pada FXML
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class TokoController implements Initializable {
    
    XStream xstream = new XStream(new StaxDriver());
    
    ArrayList<DaftarToko> daftarToko = getDaftarToko();
    
    int column, row;
    
    @FXML
    private GridPane grid;
    
    @FXML
    private Label namaToko;
    
    @FXML
    private Label alamatToko;
    
    @FXML
    private Label noTelfonToko;
    
    @FXML
    private ScrollPane scroll;
    
    ArrayList<DaftarToko> getDaftarToko() {
        FileInputStream berkasMasuk = null;
        try {
            berkasMasuk = new FileInputStream("listToko.xml");
            int isi;
            char c;
            // isi file dikembalikan menjadi string
            String s = "";
            while ((isi = berkasMasuk.read()) != - 1) {
                c = (char) isi;
                s = s + c;
            }
            // string isi file dikembalikan menjadi larik double
            return (ArrayList<DaftarToko>) xstream.fromXML(s);
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
    
    void bukaXML() {
        FileInputStream berkasMasuk = null;
        try {
            berkasMasuk = new FileInputStream("listToko.xml");
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
            daftarToko = (ArrayList<DaftarToko>) xstream.fromXML(s);
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
    
    void simpanXML() {
        String xml = xstream.toXML(daftarToko);
        
        FileOutputStream berkasKeluar = null;
        
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            berkasKeluar = new FileOutputStream("listToko.xml");

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

    //Handlerr >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Declaring post============================================================
    private ArrayList<DaftarToko> markets = new ArrayList<>();
    private Image image;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //menambah list toko(di scrollPane) dari daftarToko ke markets
        if (!daftarToko.isEmpty()) {
            DaftarToko market;
            for (int i = 0; i < daftarToko.size(); i++) {
                market = new DaftarToko();
                market.setNamaToko(daftarToko.get(i).getNamaToko());
                if (daftarToko.get(i).getImgSrc().equals("")) {
                    market.setOri(true);
                    market.setImgSrc("/gambar/pexels-aron-visuals-1694621 1.png");
                } else {
                    market.setImgSrc(daftarToko.get(i).getImgSrc());
                    market.setOri(false);
                }
                market.setAlamat(daftarToko.get(i).getAlamat());
                market.setNoTelfon(daftarToko.get(i).getNoTelfon());
                market.setNoTelfon(daftarToko.get(i).getNoTelfon());
                markets.add(market);
                simpanXML();
            }
        }
        
        column = 0;
        row = 1;
        try {
            for (int i = 0; i < markets.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(ItemTokoController.class.getResource("/View/itemToko.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                System.out.println(markets.get(i).getImgSrc());
                System.out.println(markets.get(i).getOri());
                ItemTokoController itemToko = fxmlLoader.getController();
                itemToko.setData(markets.get(i));
                
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
        
        bukaXML();
        simpanXML();
    }
    
}
