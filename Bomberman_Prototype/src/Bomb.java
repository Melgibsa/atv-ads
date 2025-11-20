import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends Figure{
    private int radius;

    public Bomb(int centerX, int centerY, int size, Color color, int radius) {
        super(centerX, centerY, size, color);
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

    public void draw(Graphics g) {
      g.setColor(color);
      g.fillOval(x - 25, y - 25, 50, 50);
    }
}
