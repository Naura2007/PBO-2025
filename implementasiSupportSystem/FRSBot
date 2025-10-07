/**
 * Write a description of class FRSBot here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Map;  
import java.util.HashMap;  
import java.util.Random;  

public class FRSBot
{
    private Map<String, String> responses;
    private Random random;
    /**
     * Constructor for objects of class FRSBot
     */
    public FRSBot()
    {
        responses = new HashMap<>();
        random = new Random();
        
        responses.put("daftar", "Untuk mendaftar mata kuliah, silakan buka menu 'Tambah KRS'.");
        responses.put("hapus", "Untuk menghapus mata kuliah, masuk ke menu 'KRS Saya' dan pilih hapus.");
        responses.put("jadwal", "Jadwal kuliah dapat dilihat di menu 'Jadwal Perkuliahan'.");
        responses.put("nim", "Pastikan NIM Anda benar.");
        responses.put("kuota", "Jika kuota penuh, coba kelas paralel atau hubungi dosen pengampu.");
        responses.put("validasi", "Validasi KRS dilakukan oleh dosen PA.");
        responses.put("isi", "Pengisian FRS dapat dilakukan sesuai jadwal kampus.");
        responses.put("login", "Jika tidak bisa login, coba reset password atau hubungi akademik.");
        responses.put("batal", "Untuk membatalkan FRS, hubungi admin akademik.");
        responses.put("tambah", "Gunakan menu tambah KRS untuk menambahkan mata kuliah.");
    }

    public String getResponse(String Input)
    {
        String[] words = Input.toLowerCase().split("\\s+");
        for (String w : words) {
            if (responses.containsKey(w)) {
                return responses.get(w);
            }
        }
        String[] generic = {
            "Bisa dijelaskan lebih detail terkait pengisian FRS?",
            "Saya kurang mengerti, apakah Anda sedang mengalami kendala tertentu?",
            "Silakan perjelas pertanyaannya, apakah terkait KRS/FRS, jadwal, atau login?"
        };
        return generic[random.nextInt(generic.length)];
    }
}
