import java.awt.Color;
import java.awt.Graphics;

public class Bomberman extends Figure{
    private final int step;

    public Bomberman(int x, int y, Color color, int size, int step) {
        super(x, y, size, color);
        this.step = 20;
    }

    public void moveUp() {
        y -= step;
    }

    public void moveDown() {
        y += step;
    }

    public void moveLeft() {
        x -= step;
    }

    public void moveRight() {
        x += step;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
    public int getStep() {
        return step;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
}

