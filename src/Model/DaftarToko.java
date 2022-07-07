package Model;

public class DaftarToko {

    private String namaToko;
    private String alamat;
    private String email;
    private String noTelfon;
    private String imgSrc = "";
    
    private boolean ori = true;

    public DaftarToko(String namaToko, String alamat, String email, String noTelfon, String imgSrc, Boolean ori) {
        this.namaToko = namaToko;
        this.alamat = alamat;
        this.email = email;
        this.noTelfon = noTelfon;
        this.imgSrc = imgSrc;
        this.ori = ori;
    }
    
    public DaftarToko() {}

    //Setter
    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoTelfon(String noTelfon) {
        this.noTelfon = noTelfon;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setOri(boolean ori) {
        this.ori = ori;
    }
    
    //Getter
    public String getNamaToko() {
        return namaToko;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getNoTelfon() {
        return noTelfon;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public boolean getOri() {
        return ori;
    }
}
