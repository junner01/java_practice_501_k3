package ex_241023_cha11.homework;
import java.awt.Color;
import java.awt.Graphics;

public class Cgw0910Shape {
    public int type; // 1 = Line, 2 = Circle
    public int x1, y1, x2, y2;
    public Color color;

    public Cgw0910Shape(int type, int x1, int y1, int x2, int y2, Color color) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        switch (type) {
            case 1: // Line
                g.drawLine(x1, y1, x2, y2);
                break;
            case 2: // Circle
                int diameter = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
                g.drawOval(Math.min(x1, x2), Math.min(y1, y2), diameter, diameter);
                break;
            case 3: // Circle
            	g.drawRect(x1,y1,Math.abs(x2 -x1),Math.abs(y2 -y1));
            	System.out.println(x1 + "," + y1 + "," + x2 + "," + y2);
                break;
        }
    }
}