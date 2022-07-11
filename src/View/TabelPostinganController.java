package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

//import class dari package lain
import Model.DaftarPostingan;

//Berkaitan dengan XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//Untuk Tabel
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TabelPostinganController implements Initializable {
    
    ArrayList<DaftarPostingan> dataPostingan = new ArrayList<DaftarPostingan>();
    
    ObservableList listTabelPost = observableArrayList();
    
    @FXML
    private TableView<DaftarPostingan> table;

    @FXML
    private TableColumn<DaftarPostingan, String> tcJudul;

    @FXML
    private TableColumn<DaftarPostingan, String> tcDeskripsi;
        
    void openTabel() {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream berkasMasuk;
        try {
            berkasMasuk = new FileInputStream("dataPostingan.xml");
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
            dataPostingan = (ArrayList<DaftarPostingan>) xstream.fromXML(s);
            berkasMasuk.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
    
    void simpanData() {
        XStream xstream = new XStream(new StaxDriver());
        String xml = xstream.toXML(dataPostingan);
            FileOutputStream berkasBaru = null;
            try {
                // membuat nama file & folder tempat menyimpan jika perlu
                berkasBaru = new FileOutputStream("dataPostingan.xml");

                // mengubah karakter penyusun string xml sebagai 
                // bytes (berbentuk nomor2 kode ASCII
                byte[] bytes = xml.getBytes("UTF-8");

                //Menyimpan file dari bytes
                berkasBaru.write(bytes);
            } catch (Exception e) {
                System.err.println("Perhatian : " + e.getMessage());
            } finally {
                if (berkasBaru != null) {
                    try {
                        berkasBaru.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
    
    @FXML
    private void removeBthAction(ActionEvent event) {
        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        int i = selectionModel.getSelectedIndex();

        if (i >= 0) {
            dataPostingan.remove(i);
            listTabelPost.remove(i);

            XStream xstream = new XStream(new StaxDriver());
            String xml = xstream.toXML(dataPostingan);
            FileOutputStream berkasKeluar;

            try {
                byte[] info = xml.getBytes("UTF-8");
                berkasKeluar = new FileOutputStream("dataPostingan.xml");
                berkasKeluar.write(info);
                berkasKeluar.close();

            } catch (Exception io) {
                System.out.println("Terjadi kesalahan : " + io.getMessage());
            }

        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        tcDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));

        openTabel();
        simpanData();

        for (int i = 0; i < dataPostingan.size(); i++) {
            listTabelPost.add(dataPostingan.get(i));
        }
        table.setItems(listTabelPost);
    }    
    
}
