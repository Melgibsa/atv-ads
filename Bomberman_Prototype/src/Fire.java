import java.awt.Color;
import java.awt.Graphics;

public class Fire extends Bomberman{
    private static int length = 70;
    public Fire(int x, int y, int size, Color color, int step, int length) {
        super(x, y, color, size, step);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        g.fillRect(x, y, size, size);

        g.fillRect(x, y - length, size, length);

        g.fillRect(x, y + size, size, length);

        g.fillRect(x - length, y, length, size);

        g.fillRect(x + size, y, length, size);
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int newLength) {
        length = newLength;
    }
}
