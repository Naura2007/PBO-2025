import java.awt.*;

public class Paddle {
    int x, y;
    int width = 15;
    int height = 80;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void moveUp() {
        if (y > 0) y -= 15;
    }

    public void moveDown() {
        if (y < 320) y += 15;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
