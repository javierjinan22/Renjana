package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

//Import class dari package lain
import Model.DaftarToko;

//File XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//Tabel
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//Untuk Hapus Tabel
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelTokoController implements Initializable {

    ArrayList<DaftarToko> daftarToko = new ArrayList<DaftarToko>();

    ObservableList listTabelToko = observableArrayList();

    @FXML
    private TableView<DaftarToko> table;
    
    @FXML
    private TableColumn<DaftarToko, String> tcNamaToko;

    @FXML
    private TableColumn<DaftarToko, String> tcAlamat;

    @FXML
    private TableColumn<DaftarToko, String> tcNoTelfon;

    void openTabel() {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream berkasMasuk;
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
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    void simpanData() {
        XStream xstream = new XStream(new StaxDriver());
        String xml = xstream.toXML(daftarToko);
        FileOutputStream berkasBaru = null;
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            berkasBaru = new FileOutputStream("listToko.xml");

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
            daftarToko.remove(i);
            listTabelToko.remove(i);

            simpanData();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcNamaToko.setCellValueFactory(new PropertyValueFactory<>("namaToko"));
        tcAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        tcNoTelfon.setCellValueFactory(new PropertyValueFactory<>("noTelfon"));

        openTabel();
        simpanData();

        for (int i = 0; i < daftarToko.size(); i++) {
            listTabelToko.add(daftarToko.get(i));
        }
        table.setItems(listTabelToko);
    }

}
