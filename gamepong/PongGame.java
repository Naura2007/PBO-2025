import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PongGame extends JPanel implements KeyListener, ActionListener {

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;

    private Timer timer;

    public PongGame() {
        leftPaddle = new Paddle(30, 150);
        rightPaddle = new Paddle(540, 150);
        ball = new Ball(300, 200);

        timer = new Timer(10, this);
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 400);

        // paddles & ball
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        ball.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();

        // pantulan dinding
        if (ball.y <= 0 || ball.y >= 380) {
            ball.reverseY();
        }

        // pantulan paddle kiri
        if (ball.getRect().intersects(leftPaddle.getRect())) {
            ball.reverseX();
        }

        // pantulan paddle kanan
        if (ball.getRect().intersects(rightPaddle.getRect())) {
            ball.reverseX();
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        // Paddle kiri (W & S)
        if (k == KeyEvent.VK_W) leftPaddle.moveUp();
        if (k == KeyEvent.VK_S) leftPaddle.moveDown();

        // Paddle kanan (Up & Down arrow)
        if (k == KeyEvent.VK_UP) rightPaddle.moveUp();
        if (k == KeyEvent.VK_DOWN) rightPaddle.moveDown();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game - BlueJ");
        PongGame game = new PongGame();

        frame.add(game);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
