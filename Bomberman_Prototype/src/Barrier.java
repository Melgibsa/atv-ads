import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Barrier extends Figure{
    int heightTop;
    int widthRight;
    int heightBottom;
    int widthLeft;

    public Barrier(int centerX, int centerY, int size, Color color, int height, int width,
                   int heightTop, int widthRight, int heightBottom, int widthLeft) {
        super(centerX, centerY, size, color);
        this.heightTop = 5;
        this.heightBottom = 5;
        this.widthRight = 5;
        this.widthLeft = 5;
    }

    //generate barriers on the frame
    public static ArrayList<Barrier> getBarriers(int size, JFrame frame) {
        ArrayList<Barrier> barriers = new ArrayList<>();

       for(int i = 0; i < 40; i++){
            int x = (i % 8) * 200 + 50;
            int y = (i / 8) * 200 + 50;

                barriers.add(new Barrier(x, y, size, Color.DARK_GRAY, size, size, size, size, size, size));
        }
        return barriers;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
}
