import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Enemies extends Bomberman {
    private int radius;

    public Enemies(int x, int y, int size, Color color, int step, int radius) {
        super(x, y, color, size, step);
        this.radius = radius;
    }

    //insert enemy
    public static ArrayList<Enemies> insertEnemies() {
        ArrayList<Enemies> enemies = new ArrayList<>();

        enemies.add(new Enemies(600, 350, 60, Color.BLACK, 20, 35));
        enemies.add(new Enemies(600, 550, 60, Color.BLACK, 20, 35));

        /*Enemies I = new Enemies(600, 350, 60, Color.BLACK, 20, 35);
        Enemies II = new Enemies(600, 550, 60, Color.BLACK, 20, 35);*/

        return enemies;
    }

    @Override
    public void moveUp() {
        y -= getStep();
    }
    @Override
    public void moveDown() {
        y += getStep();
    }
    @Override
    public void moveLeft() {
        x -= getStep();
    }
    @Override
    public void moveRight() {
        x += getStep();
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
