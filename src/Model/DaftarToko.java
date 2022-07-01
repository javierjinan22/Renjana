package Model;

public class DaftarToko {

    String namaToko;
    String alamat;
    String email;
    String noTelfon;

    public DaftarToko(String namaToko, String alamat, String email, String noTelfon) {
        this.namaToko = namaToko;
        this.alamat = alamat;
        this.email = email;
        this.noTelfon = noTelfon;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelfon() {
        return noTelfon;
    }

    public void setNoTelfon(String noTelfon) {
        this.noTelfon = noTelfon;
    }

}
