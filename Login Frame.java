import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private final String CORRECT_USERNAME = "james3302";
    private final String CORRECT_PASSWORD = "pass";

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JLabel lblStatus;

    public LoginFrame() {
        super("Login Demo");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));
        JPanel center = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUser = new JLabel("Username:");
        JLabel lblPass = new JLabel("Password:");

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        // Row 0: username
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        center.add(lblUser, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        center.add(txtUsername, gbc);

        // Row 1: password
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        center.add(lblPass, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        center.add(txtPassword, gbc);

        // Buttons panel
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
        buttons.add(btnCancel);
        buttons.add(btnLogin);

        // status label
        lblStatus = new JLabel(" ");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);

        add(center, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        add(lblStatus, BorderLayout.NORTH);

        // Listeners
        btnLogin.addActionListener(e -> onLogin());
        btnCancel.addActionListener(e -> onCancel());

        // Enter on password triggers login
        txtPassword.addActionListener(e -> onLogin());

        pack();
        setLocationRelativeTo(null); // center on screen
    }

    private void onLogin() {
        String user = txtUsername.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (user.equals(CORRECT_USERNAME) && pass.equals(CORRECT_PASSWORD)) {
            lblStatus.setText("Login Granted!");
            JOptionPane.showMessageDialog(this, "Login Granted!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            lblStatus.setText("Login Denied");
            JOptionPane.showMessageDialog(this, "Login Denied", "Error", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocusInWindow();
        }
    }

    private void onCancel() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocusInWindow();
        lblStatus.setText(" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame f = new LoginFrame();
            f.setVisible(true);
        });
    }
}
