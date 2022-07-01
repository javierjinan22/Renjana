package Model;

public class DaftarPostingan {
    String judul, deskripsi;
    
    public DaftarPostingan() {}

    public DaftarPostingan(String judul, String deskripsi) {
        this.judul = judul;
        this.deskripsi = deskripsi;
    }
    
    public void setData(String judul, String deskripsi) {
        this.judul = judul;
        this.deskripsi = deskripsi;
    }
 
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    
}
