import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int DELAY = 150;

    private LinkedList<Position> snake;
    private Position food;
    private char direction = 'R'; 
    private boolean running = false;
    private Timer timer;
    private Random random;

    public GamePanel() {
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        snake = new LinkedList<>();
        snake.add(new Position(0, 0)); 
        spawnFood();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            drawGrid(g);

            g.setColor(new Color(255, 0, 0));
            g.fillOval(food.x, food.y, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(new Color(34, 139, 34));
                } else {
                    g.setColor(new Color(50, 205, 50));
                }
                Position p = snake.get(i);
                g.fillRoundRect(p.x, p.y, UNIT_SIZE, UNIT_SIZE, 10, 10);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + (snake.size() - 1), (SCREEN_WIDTH - metrics.stringWidth("Score: " + (snake.size() - 1))) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void drawGrid(Graphics g) {
        g.setColor(new Color(40, 40, 40));
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
    }

    public void spawnFood() {
        int x = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        food = new Position(x, y);
    }

    public void move() {
        Position head = snake.getFirst();
        Position newHead = new Position(head.x, head.y);

        switch (direction) {
            case 'U':
                newHead.y -= UNIT_SIZE;
                break;
            case 'D':
                newHead.y += UNIT_SIZE;
                break;
            case 'L':
                newHead.x -= UNIT_SIZE;
                break;
            case 'R':
                newHead.x += UNIT_SIZE;
                break;
        }

        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            spawnFood();
        } else {
            snake.removeLast();
        }
    }

    public void checkCollisions() {
        Position head = snake.getFirst();

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
            }
        }

        if (head.x < 0 || head.x >= SCREEN_WIDTH || head.y < 0 || head.y >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + (snake.size() - 1), (SCREEN_WIDTH - metrics1.stringWidth("Score: " + (snake.size() - 1))) / 2, g.getFont().getSize());

        g.setColor(Color.RED);
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        g.setFont(new Font("SansSerif", Font.PLAIN, 25));
        g.setColor(Color.YELLOW);
        g.drawString("Press ENTER to Restart", (SCREEN_WIDTH - metrics2.stringWidth("Press ENTER to Restart")) / 2, SCREEN_HEIGHT / 2 + 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D';
                    break;
                case KeyEvent.VK_ENTER:
                    if (!running) startGame();
                    break;
            }
        }
    }
}