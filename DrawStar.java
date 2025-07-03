package lab5_Project;

import java.awt.Color;
import java.awt.Graphics;

public class DrawStar {
    // Рекурсивный метод для рисования звезды
    public void draw(Graphics g, int x, int y, int size, int depth) {
        if (depth == 0) return; // Базовый случай для рекурсии

        // Рисуем лучи звезды
        int x1 = x - size / 2;
        int y1 = y - size / 2;
        int x2 = x + size / 2;
        int y2 = y + size / 2;

        g.setColor(Color.BLACK);
        g.drawLine(x, y, x1, y1); // Верхний левый луч
        g.drawLine(x, y, x2, y1); // Верхний правый луч
        g.drawLine(x, y, x1, y2); // Нижний левый луч
        g.drawLine(x, y, x2, y2); // Нижний правый луч

        // Рекурсивно рисуем звезду на каждом из концов лучей
        draw(g, x1, y1, size / 2, depth - 1);
        draw(g, x2, y1, size / 2, depth - 1);
        draw(g, x1, y2, size / 2, depth - 1);
        draw(g, x2, y2, size / 2, depth - 1);
    }
}