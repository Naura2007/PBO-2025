import java.awt.*;

public class Ball {
    int x, y;
    int size = 20;
    int speedX = 3;
    int speedY = 3;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public void reverseX() {
        speedX = -speedX;
    }

    public void reverseY() {
        speedY = -speedY;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }
}
