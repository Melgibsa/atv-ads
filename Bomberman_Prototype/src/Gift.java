import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Gift extends Figure{
    public Gift(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
}
