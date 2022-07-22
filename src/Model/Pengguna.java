package Model;

public class Pengguna {

    String nama, email, password, namaWisata, jenisWisata, provinsi, kabupaten, alamat, noTelfon;

    boolean statusOnline;
    private boolean admin;
    private boolean manager;

    public Pengguna() {
    }

    public Pengguna(String nama, String email, String password) {
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public Pengguna(String nama, String email, String password, boolean manager, boolean admin) { //Pengunjung
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.manager = manager;
        this.admin = admin;
    }

    public Pengguna(String nama, String email, String password, String namaWisata, String jenisWisata, String provinsi, String kabupaten, String alamat, String noTelfon, boolean manager) {
        //Pengelola Wisata
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.namaWisata = namaWisata;
        this.jenisWisata = jenisWisata;
        this.provinsi = provinsi;
        this.kabupaten = kabupaten;
        this.alamat = alamat;
        this.noTelfon = noTelfon;
        this.manager = manager;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJenisWisata() {
        return this.jenisWisata;
    }

    public void setJenisWisata(String jenisWisata) {
        this.jenisWisata = jenisWisata;
    }

    public String getProvinsi() {
        return this.provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKabupaten() {
        return this.kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelfon() {
        return this.noTelfon;
    }

    public void setNoTelfon(String noTelfon) {
        this.noTelfon = noTelfon;
    }

    public boolean getStatusOnline() {
        return statusOnline;
    }

    public void setStatusOnline(boolean statusOnline) {
        this.statusOnline = statusOnline;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }
    
    
}
