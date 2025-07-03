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

public class Lab_6 extends JPanel {
    private static final long serialVersionUID = 1L;
    private int[][] matrix1; // Матрица до обработки
    private int[][] matrix2; // Матрица после обработки
    private JPanel displayPanel; // Основная панель для отображения
    private JPanel matrix1Panel; // Панель для первой матрицы
    private JPanel matrix2Panel; // Панель для второй матрицы

    public Lab_6() {
        setBackground(new Color(0, 128, 192));
        setLayout(null);

        // Инициализация основной панели для отображения матриц
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.WHITE);
        displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        displayPanel.setBounds(10, 10, 416, 573);
        add(displayPanel);
        displayPanel.setLayout(null);

        // Панель для матрицы до обработки
        matrix1Panel = new JPanel();
        matrix1Panel.setBackground(Color.WHITE);
        matrix1Panel.setBounds(0, 0, 416, 250); // Верхняя половина
        matrix1Panel.setLayout(null);
        displayPanel.add(matrix1Panel);

        // Панель для матрицы после обработки
        matrix2Panel = new JPanel();
        matrix2Panel.setBackground(Color.WHITE);
        matrix2Panel.setBounds(0, 260, 416, 250); // Нижняя половина
        matrix2Panel.setLayout(null);
        displayPanel.add(matrix2Panel);

        // Кнопка "Матрица до обработки"
        JButton btnMatrixBefore = new JButton("Матрица до обработки");
        btnMatrixBefore.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnMatrixBefore.setBackground(new Color(0, 255, 128));
        btnMatrixBefore.setBounds(436, 10, 180, 71);
        add(btnMatrixBefore);

        // Кнопка "Матрица после обработки"
        JButton btnMatrixAfter = new JButton("Матрица после обработки");
        btnMatrixAfter.setBackground(new Color(0, 255, 128));
        btnMatrixAfter.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnMatrixAfter.setBounds(437, 112, 179, 78);
        add(btnMatrixAfter);

        // Кнопка "Выход"
        JButton btnExit = new JButton("Выход");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        btnExit.setBounds(436, 528, 179, 55);
        add(btnExit);

        // Загрузка матрицы из файла
        loadMatrix();

        // Обработчики событий для кнопок
        btnMatrixBefore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMatrix(matrix1, matrix1Panel, "Матрица до обработки");
            }
        });

        btnMatrixAfter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayMatrix(matrix2, matrix2Panel, "Матрица после обработки");
            }
        });
    }

    private void loadMatrix() {
        try {
            matrix1 = MatrixLoader.loadMatrix("matrix.txt");
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

        // Находим индексы столбцов с минимальной и максимальной суммой
        int minIndex = 0;
        int maxIndex = 0;
        for (int j = 1; j < columnSums.length; j++) {
            if (columnSums[j] < columnSums[minIndex]) {
                minIndex = j;
            }
            if (columnSums[j] > columnSums[maxIndex]) {
                maxIndex = j;
            }
        }

        // Создаем новую матрицу для хранения результатов
        int[][] processedMatrix = new int[matrix.length][matrix[0].length];

        // Копируем значения, меняя местами столбцы с минимальной и максимальной суммой
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == minIndex) {
                    processedMatrix[i][j] = matrix[i][maxIndex]; // Замена с max
                } else if (j == maxIndex) {
                    processedMatrix[i][j] = matrix[i][minIndex]; // Замена с min
                } else {
                    processedMatrix[i][j] = matrix[i][j]; // Копируем остальные значения
                }
            }
        }

        return processedMatrix; // Возвращаем новую матрицу
    }

    private void displayMatrix(int[][] matrix, JPanel panel, String title) {
        panel.removeAll(); // Удаляем все компоненты только с указанной панели
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        titleLabel.setBounds(10, 10, 200, 30);
        panel.add(titleLabel);

        int cellSize = 50; 
        int startY = 40; 
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                JPanel cell = new JPanel();
                cell.setBounds(j * cellSize, startY + i * cellSize, cellSize, cellSize);
                
                // Проверяем значение и задаем цвет фона
                if (matrix[i][j] < 0 || matrix[i][j] > 9) {
                    cell.setBackground(Color.RED);
                } else {
                    cell.setBackground(getColor(matrix[i][j]));
                }
                
                cell.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
                panel.add(cell);
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    private Color getColor(int value) {
        if (value < 0 || value > 9) {
            return Color.RED;
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Application");
        frame.setContentPane(new Lab_6());
        frame.setSize(650, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}