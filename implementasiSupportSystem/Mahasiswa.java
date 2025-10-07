import java.util.ArrayList;
import java.util.List;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String password;
    private List<MataKuliah> frs;

    public Mahasiswa(String nim, String nama, String password) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
        this.frs = new ArrayList<>();
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getPassword() { return password; }
    public List<MataKuliah> getKRS() { return frs; }

    public void tambahMatkul(MataKuliah mk) {
        frs.add(mk);
    }

    public void hapusMatkul(String kode) {
        frs.removeIf(mk -> mk.getKode().equalsIgnoreCase(kode));
    }
}
