// File: ImageViewer.java
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewer extends JFrame {
    private final ImagePanel imagePanel = new ImagePanel();
    private final JLabel lblInfo = new JLabel("No folder loaded");
    private final JButton btnPrev = new JButton("Previous");
    private final JButton btnNext = new JButton("Next");
    private final JButton btnOpen = new JButton("Open Folder");

    private ImageFileManager manager;

    public ImageViewer() {
        super("Simple Image Viewer");
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(6,6));

        // Top panel (controls)
        JPanel top = new JPanel(new BorderLayout());
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(btnOpen);
        controls.add(btnPrev);
        controls.add(btnNext);
        top.add(controls, BorderLayout.WEST);
        lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        top.add(lblInfo, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);

        // listeners
        btnOpen.addActionListener(e -> onOpenFolder());
        btnPrev.addActionListener(e -> onPrevious());
        btnNext.addActionListener(e -> onNext());

        // keyboard navigation
        imagePanel.setFocusable(true);
        imagePanel.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) onPrevious();
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) onNext();
            }
        });

        setSize(800,600);
        setLocationRelativeTo(null);
    }

    private void onOpenFolder() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = chooser.showOpenDialog(this);
        if (res != JFileChooser.APPROVE_OPTION) return;
        File dir = chooser.getSelectedFile();
        manager = new ImageFileManager(dir);
        if (!manager.hasImages()) {
            JOptionPane.showMessageDialog(this, "Folder tidak berisi gambar yang dikenali.", "Info", JOptionPane.INFORMATION_MESSAGE);
            lblInfo.setText("No images in: " + dir.getName());
            imagePanel.clearImage();
            return;
        }
        loadAndShowCurrent();
    }

    private void loadAndShowCurrent() {
        if (manager == null || !manager.hasImages()) return;
        OFImage of = manager.getCurrent();
        try {
            BufferedImage img = ImageIO.read(of.getFile());
            if (img == null) throw new IOException("Unsupported image format");
            imagePanel.setImage(img);
            updateInfoLabel();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat gambar: " + of.getName(), "Error", JOptionPane.ERROR_MESSAGE);
            // coba skip ke next
            manager.next();
            if (manager.hasImages()) loadAndShowCurrent();
            else imagePanel.clearImage();
        }
    }

    private void updateInfoLabel() {
        if (manager == null || !manager.hasImages()) {
            lblInfo.setText("No images loaded");
        } else {
            String name = manager.getCurrent().getName();
            int idx = manager.getCurrentIndex() + 1;
            int tot = manager.size();
            lblInfo.setText(String.format("%d / %d   %s", idx, tot, name));
        }
    }

    private void onPrevious() {
        if (manager == null || !manager.hasImages()) return;
        manager.previous();
        loadAndShowCurrent();
    }

    private void onNext() {
        if (manager == null || !manager.hasImages()) return;
        manager.next();
        loadAndShowCurrent();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageViewer v = new ImageViewer();
            v.setVisible(true);
        });
    }
}
