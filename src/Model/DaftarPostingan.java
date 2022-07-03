package Model;

public class DaftarPostingan {

    private String judul, deskripsi;
    private String imgSrc = "";
    
    private boolean ori = true;

    public DaftarPostingan() {
    }

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

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public boolean getOri() {
        return ori;
    }

    public void setOri(boolean ori) {
        this.ori = ori;
    }
    
    

}
