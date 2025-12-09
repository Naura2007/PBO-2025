import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JavaCRUD {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3307/perpustakaan";
    static final String USER = "root"; 
    static final String PASS = ""; // default XAMPP password kosong

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (true) {
                showMenu();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showMenu() {
        System.out.println("\n===== MENU UTAMA =====");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.print("PILIHAN> ");

        try {
            int p = Integer.parseInt(input.readLine());

            switch (p) {
                case 0: System.exit(0);
                case 1: insertBuku(); break;
                case 2: showData(); break;
                case 3: updateBuku(); break;
                case 4: deleteBuku(); break;
                default: System.out.println("Pilihan salah!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void showData() {
        try {
            rs = stmt.executeQuery("SELECT * FROM buku");

            System.out.println("\n=== DATA BUKU ===");
            while (rs.next()) {
                System.out.println(rs.getInt("id_buku") + ". " +
                                   rs.getString("judul") + " (" +
                                   rs.getString("pengarang") + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertBuku() {
        try {
            System.out.print("Judul: ");
            String judul = input.readLine();
            System.out.print("Pengarang: ");
            String pengarang = input.readLine();

            String sql = "INSERT INTO buku (judul, pengarang) VALUES ('%s', '%s')";
            stmt.execute(String.format(sql, judul, pengarang));

            System.out.println("Data berhasil ditambahkan!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateBuku() {
        try {
            System.out.print("ID buku yang ingin diubah: ");
            int id = Integer.parseInt(input.readLine());

            System.out.print("Judul baru: ");
            String judul = input.readLine();
            System.out.print("Pengarang baru: ");
            String pengarang = input.readLine();

            String sql = "UPDATE buku SET judul='%s', pengarang='%s' WHERE id_buku=%d";
            stmt.execute(String.format(sql, judul, pengarang, id));

            System.out.println("Data berhasil diperbarui!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteBuku() {
        try {
            System.out.print("ID buku yang ingin dihapus: ");
            int id = Integer.parseInt(input.readLine());

            String sql = "DELETE FROM buku WHERE id_buku=%d";
            stmt.execute(String.format(sql, id));

            System.out.println("Data berhasil dihapus!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
