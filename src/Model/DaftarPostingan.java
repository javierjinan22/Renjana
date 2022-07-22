package Model;

public class DaftarPostingan {

    private String judul, deskripsi;
    private String imgSrc = "";

    private boolean ori = true; //Kosong tydack ada gambar

    public DaftarPostingan() {
    }

    public DaftarPostingan(String judul, String deskripsi, String imgSrc) {
        this.imgSrc = imgSrc;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    //Setter
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setOri(boolean ori) {
        this.ori = ori;
    }

    //Getter
    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public boolean getOri() {
        return ori;
    }
}
