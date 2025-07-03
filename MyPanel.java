package lab4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
    
	private int drawfigure = 0;
	
	public void setfigure(int drawfigure) {
		this.drawfigure = drawfigure;
		repaint();
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawfigure == 1) {
            int x = 100, y = 200, width = 100, height = 50;

            g.setColor(Color.BLUE);
            
            g.drawRect(x, y, width + 50, height + 100);
            g.drawLine(x, y, x + 80, y - 60); // крыша
            g.drawLine(x + 80, y - 60, x + 150, y);
            
            g.drawLine(x + 115, y - 30, x + 115, y - 50); // дымоход
            g.drawLine(x + 115, y - 50, x + 130, y - 50);
            g.drawLine(x + 130, y - 50, x + 130, y - 17);
            
            g.drawOval(167,160,25,25);
            g.drawRect(156, 250, 40, 40);
        }
        if (drawfigure == 2) {
        	super.paintComponent(g);
        	g.setColor(Color.GREEN);

        	int x1 = 10;
        	int y1 = 50;
        	int x2 = 200;
        	int y2 = 50;

        	while (y2 < 150) {
        		g.drawLine(x1, y1, x2, y2);
        		y2 = y2 + 10;
        		y1 = y1 - 5;
        	}
        }
    }
}