import java.awt.*;

public abstract class Figure {
    protected int x, y;
    protected int size;
    protected Color color;

    Figure(int xInitial, int yInitial, int size, Color color) {
        this.x = xInitial;
        this.y = yInitial;
        this.size = size;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }

    abstract void draw(Graphics G);
}