package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

//import class dari package lain
import Model.Pengguna;

//Berkaitan dengan XML
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

//Untuk Tabel
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelUserController implements Initializable {

    LinkedList<Pengguna> dataRegistration = new LinkedList<>();

    ObservableList listTabelUser = observableArrayList();

    @FXML
    private TableView<Pengguna> table;

    @FXML
    private TableColumn<Pengguna, String> tcNama;

    @FXML
    private TableColumn<Pengguna, String> tcEmail;

    @FXML
    private TableColumn<Pengguna, String> tcPass;

    void openTabel() {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream berkasMasuk;
        try {
            berkasMasuk = new FileInputStream("dataRegistration.xml");
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
            dataRegistration = (LinkedList<Pengguna>) xstream.fromXML(s);
            berkasMasuk.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    void simpanData() {
        XStream xstream = new XStream(new StaxDriver());
        String xml = xstream.toXML(dataRegistration);
        FileOutputStream berkasBaru = null;
        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            berkasBaru = new FileOutputStream("dataRegistration.xml");

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
            dataRegistration.remove(i);
            listTabelUser.remove(i);
            simpanData();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPass.setCellValueFactory(new PropertyValueFactory<>("password"));

        openTabel();
        simpanData();

        for (int i = 0; i < dataRegistration.size(); i++) {
            listTabelUser.add(dataRegistration.get(i));
        }
        table.setItems(listTabelUser);
    }
}
