import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel() {
        setBackground(Color.DARK_GRAY);
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        repaint();
    }

    public void clearImage() {
        this.image = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;
        int panelW = getWidth();
        int panelH = getHeight();
        int imgW = image.getWidth();
        int imgH = image.getHeight();

        // Hitung scale agar mempertahankan aspect ratio
        double scale = Math.min((double)panelW / imgW, (double)panelH / imgH);
        int drawW = (int)(imgW * scale);
        int drawH = (int)(imgH * scale);

        int x = (panelW - drawW) / 2;
        int y = (panelH - drawH) / 2;

        g.drawImage(image, x, y, drawW, drawH, this);
    }
}
