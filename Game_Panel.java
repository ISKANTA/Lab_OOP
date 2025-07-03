package lab_7;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game_Panel extends JPanel {
    private int[][] matrix;
    private int playerX;
    private int playerY;
    private int size_field;
    private int size_player;
    private int endX; // Позиция конечной точки по X
    private int endY; // Позиция конечной точки по Y
    private String[] levelFiles; // Массив с именами файлов уровней
    private int currentLevel; // Индекс текущего уровня

    public Game_Panel(String[] levelFiles, int size_field) {
        this.levelFiles = levelFiles;
        this.size_field = size_field;
        this.size_player = (int) (size_field * 0.5);
        this.currentLevel = 0; // Начинаем с первого уровня
        loadLevel(currentLevel); // Загружаем первый уровень
    }

    private void loadLevel(int levelIndex) {
        try {
            this.matrix = Read_Array.Read_Array(levelFiles[levelIndex]);
            this.playerX = 0; // Сброс позиции игрока
            this.playerY = 0; // Сброс позиции игрока
            this.endX = matrix[0].length - 1; // Устанавливаем конечную точку
            this.endY = matrix.length - 1; // Устанавливаем конечную точку
            repaint(); // Перерисовываем панель
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    g.setColor(Color.WHITE); // Проходимые клетки
                } else {
                    g.setColor(Color.DARK_GRAY); // Непроходимые клетки
                }
                g.fillRect(j * size_field, i * size_field, size_field, size_field);
                g.setColor(Color.BLACK);
                g.drawRect(j * size_field, i * size_field, size_field, size_field);
            }
        }

        // Рисуем игрока
        g.setColor(Color.RED);
        int playerXPosition = playerX * size_field + (size_field - size_player) / 2;
        int playerYPosition = playerY * size_field + (size_field - size_player) / 2;
        g.fillRect(playerXPosition, playerYPosition, size_player, size_player);

        // Рисуем конечную точку
        g.setColor(Color.GREEN);
        g.fillRect(endX * size_field, endY * size_field, size_field, size_field); // Конечная точка
    }

    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        // Проверка на границы и проходимость
        if (newX >= 0 && newX < matrix[0].length && newY >= 0 && newY < matrix.length && matrix[newY][newX] == 0) {
            playerX = newX;
            playerY = newY;

            // Проверка на достижение конечной точки
            if (playerX == endX && playerY == endY) {
                loadNextLevel(); // Загружаем следующий уровень
            }

            repaint(); // Перерисовываем панель
        }
    }

    private void loadNextLevel() {
        currentLevel++;
        if (currentLevel < levelFiles.length) {
            loadLevel(currentLevel); // Загружаем следующий уровень
        } else {
            endGame(); // Завершаем игру, если уровни закончились
        }
    }

    private void endGame() {
        // Показать сообщение о завершении игры
        JOptionPane.showMessageDialog(this, "Поздравляем! Вы прошли все уровни!", "Игра завершена", JOptionPane.INFORMATION_MESSAGE);
        
        // Закрытие приложения
        System.exit(0); // Завершает приложение
    }
}