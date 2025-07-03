package lab6;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.IOException;
import javax.swing.JInternalFrame;

public class paintArray extends JPanel {
    private static final long serialVersionUID = 1L;
    private int[][] matrix1; // Матрица до обработки
    private int[][] matrix2; // Матрица после обработки
    private JPanel displayPanel; // Панель для отображения матрицы

    public paintArray() {
        setBackground(new Color(100, 149, 237));
        setLayout(null);
       
        // Инициализация панели для отображения матрицы
        displayPanel = new JPanel();
        displayPanel.setBackground(new Color(230, 230, 250));
        displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        displayPanel.setBounds(10, 11, 416, 385);
        add(displayPanel);
        displayPanel.setLayout(null);

        // Кнопка "Матрица до обработки"
        JButton btnMatrixBefore = new JButton("Матрица до обработки");
        btnMatrixBefore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnMatrixBefore.setBackground(new Color(0, 255, 0));
        btnMatrixBefore.setBounds(448, 11, 171, 118);
        add(btnMatrixBefore);

        // Кнопка "Матрица после обработки"
        JButton btnMatrixAfter = new JButton("Матрица после обработки");
        btnMatrixAfter.setBackground(new Color(0, 255, 0));
        btnMatrixAfter.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnMatrixAfter.setBounds(448, 140, 171, 125);
        add(btnMatrixAfter);

        // Кнопка "Выход"
        JButton btnExit = new JButton("Выход");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnExit.setBounds(448, 289, 171, 103);
        add(btnExit);

        // Загрузка матрицы из файла
        loadMatrix();

        // Обработчики событий для кнопок
        btnMatrixBefore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMatrix(matrix1, "Матрица до обработки");
            }
        });

        btnMatrixAfter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMatrix(matrix2, "Матрица после обработки"); // Отображаем обработанную матрицу
            }
        });
    }

    private void loadMatrix() {
        try {
            matrix1 = scan.loadMatrix("matrix.txt");
            matrix2 = processMatrix(matrix1); // Обработка матрицы
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] processMatrix(int[][] matrix) {
        // Сумма по столбцам
        int[] columnSums = new int[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                columnSums[j] += matrix[i][j];
            }
        }

        // Находим индекс столбца с минимальной суммой
        int minIndex = 0;
        for (int j = 1; j < columnSums.length; j++) {
            if (columnSums[j] < columnSums[minIndex]) {
                minIndex = j;
            }
        }

        // Создаем новую матрицу без столбца с минимальной суммой
        int[][] processedMatrix = new int[matrix.length][matrix[0].length - 1];

        // Копируем значения в новую матрицу, исключая столбец с минимальной суммой
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0, newCol = 0; j < matrix[i].length; j++) {
                if (j != minIndex) { // Удаляем столбец с минимальной суммой
                    processedMatrix[i][newCol++] = matrix[i][j];
                }
            }
        }

        return processedMatrix;
    }

    private void displayMatrix(int[][] matrix, String title) {
        displayPanel.removeAll(); // Удаляем все компоненты панели
        displayPanel.revalidate();
        displayPanel.repaint();

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        titleLabel.setBounds(10, 10, 200, 30);
        displayPanel.add(titleLabel);

        int cellSize = 50; 
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                JPanel cell = new JPanel();
                cell.setBounds(j * cellSize, (i + 1) * cellSize, cellSize, cellSize);
                
                // Проверяем значение и задаем цвет фона
                if (matrix[i][j] < 0 || matrix[i][j] > 9) {
                    cell.setBackground(Color.RED);
                } else {
                    cell.setBackground(getColor(matrix[i][j]));
                }
                
                cell.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
                displayPanel.add(cell);
            }
        }

        displayPanel.revalidate();
        displayPanel.repaint();
    }

    private Color getColor(int value) {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Application");
        frame.setContentPane(new paintArray());
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}