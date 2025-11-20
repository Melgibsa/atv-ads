import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.ArrayList;

public class GameWindow extends JFrame implements KeyListener {
    private GameMovements gameMovements;
    private Bomberman bomberman;
    private GameController gameController;
    private ArrayList <Barrier> barriers;
    private ArrayList <Enemies> enemies;
    private ArrayList<Bomb> bomb = new ArrayList<>();
    private ArrayList<Fire> fire = new ArrayList<>();
    private ArrayList<Gift> gift = new ArrayList<>();

    public GameWindow() {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.BorderLayout());
        setBackground(Color.WHITE);

        int centerX = (800 - 30) / 2;
        int centerY = (800 - 30) / 2;
        this.bomberman = new Bomberman(centerX, centerY, Color.BLUE, 60, 20);//insert bomberman
        this.barriers = Barrier.getBarriers(60, this);//insert barriers
        this.enemies = new ArrayList<>();
        this.enemies.add(new Enemies(800, 200, 25, Color.YELLOW, 20, 35));
        this.enemies.add(new Enemies(800, 600, 25, Color.YELLOW, 20, 35));
        this.bomb = new ArrayList<>();
        this.fire = new ArrayList<>();
        this.gift.add(new Gift(400, 700, 50, Color.ORANGE));

        //call methods
        this.gameMovements = new GameMovements(bomberman, gameController, this, barriers, enemies,
                bomb, fire, gift);//the movement
        this.gameController = new GameController();//the logic

        addKeyListener(this);
        setFocusable(true);
    }

    //GAME-OVER STATEMENT
    public void gameOver(GameWindow gameWindow) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.MAGENTA);

        JLabel label = new JLabel("GAME OVER!", JLabel.CENTER);
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);

        gameWindow.getContentPane().add(panel);
        gameWindow.revalidate();
        gameWindow.repaint();

        Timer timer = new Timer(2000, e -> System.exit(0));
        timer.setRepeats(false);
        timer.start();
    }

    //draw graphic elements
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        bomberman.draw(g);
        for(Gift gifts : gift) {
            gifts.draw(g);
        }
        for(Barrier barrier : barriers) {
            barrier.draw(g);
        }
        for(Enemies enemy : enemies) {
            enemy.draw(g);
        }
        for(Bomb bombs: bomb) {
            bombs.draw(g);
        }
        for(Fire fires : fire) {
            fires.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameMovements.handleInput(e.getKeyCode());
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
