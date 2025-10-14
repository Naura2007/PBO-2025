/**
 * Write a description of class admin here.
 *
 * @author Naura Rossa Azalia
 * @version 14-10-2025
 */
public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }
}
