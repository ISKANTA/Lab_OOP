package lab6;

import javax.swing.*;
import java.awt.*;

public class paintread extends JPanel {
    private int[][] matrix;

    public paintread(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 50; 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                g.setColor(getColor(matrix[i][j]));
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    private Color getColor(int value) {
        if (value < 0 || value > 9) {
            return Color.RED; // Закрашиваем красным, если значение вне диапазона
        }
        switch (value) {
            case 0: return Color.WHITE;
            case 1: return Color.RED;
            case 2: return Color.GREEN;
            case 3: return Color.BLUE;
            case 4: return Color.YELLOW;
            case 5: return Color.CYAN;
            case 6: return Color.MAGENTA;
            case 7: return Color.ORANGE;
            case 8: return Color.PINK;
            case 9: return Color.GRAY;
            default: return Color.BLACK;
        }
    }
}