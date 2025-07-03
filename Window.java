package lab5_Project;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Window extends JPanel {
    private DrawStar drawStar; // экземпляр класса DrawStar
    private int depth; // Глубина рекурсии для звезды
    private boolean drawStarFlag; // Флаг, указывающий, нужно ли рисовать звезду
    private JPanel drawingPanel; // Панель для рисования
    private JTextField inputField; // Поле для ввода значения x
    private JTextArea resultArea; // Текстовая область для отображения результата

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("My Application");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(650, 600); // Установка размера окна

                Window panel = new Window(frame);
                frame.getContentPane().add(panel); // Добавляем панель в окно

                frame.setVisible(true); // Делаем окно видимым
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private JFrame frame; // Ссылка на JFrame

    public Window(JFrame frame) {
        this.frame = frame; // Сохраняем ссылку на JFrame
        setBackground(new Color(128, 128, 192));
        setLayout(null); // абсолютное позиционирование для компонентов на панели.

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (drawStarFlag) { // Проверяем флаг перед рисованием звезды
                    drawStar.draw(g, getWidth() / 2, getHeight() / 2, 200, depth); // Рисуем звезду в центре панели
                }
            }
        };
        drawingPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        drawingPanel.setBackground(new Color(255, 255, 255));
        drawingPanel.setBounds(10, 10, 417, 533);
        add(drawingPanel);

        drawStar = new DrawStar(); // Инициализация класса для рисования звезды
        drawStarFlag = false; // Изначально флаг не установлен

        JButton btnStar = new JButton("Звезда");
        btnStar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnStar.setBounds(437, 107, 183, 38);
        add(btnStar);

        JButton btnFunction = new JButton("Функция");
        btnFunction.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnFunction.setBounds(437, 180, 183, 38);
        add(btnFunction);

        JButton btnExit = new JButton("Выход");
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnExit.setBounds(437, 505, 183, 38);
        add(btnExit);
        
        JSpinner spinner = new JSpinner();
        spinner.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        spinner.setBounds(437, 46, 183, 32);
        add(spinner);
        
        JLabel lblNewLabel = new JLabel("Глубина рекурсии");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel.setBounds(437, 10, 183, 26);
        add(lblNewLabel);

        JLabel lblInput = new JLabel("Введите x (-1 <= x <= 1):");
        lblInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblInput.setBounds(437, 230, 183, 26);
        add(lblInput);

        inputField = new JTextField();
        inputField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        inputField.setBounds(437, 260, 183, 32);
        add(inputField);

        // Текстовая область для отображения результата
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBounds(437, 300, 183, 100);
        add(resultArea);

        // Добавляем ActionListener для кнопки "Звезда"
        btnStar.addActionListener(e -> {
            depth = (int) spinner.getValue();
            drawStarFlag = true; // Устанавливаем флаг для рисования звезды
            drawingPanel.repaint(); // Перерисовываем панель для отображения звезды
        });

        // Добавляем ActionListener для кнопки "Функция"
        btnFunction.addActionListener(e -> {
            try {
                String input = inputField.getText().replace(',', '.'); // Заменяем запятую на точку
                double x = Double.parseDouble(input);
                if (x < -1 || x > 1) {
                    showResult("Введите x в диапазоне [-1; 1]");
                } else {
                    double result = arctgRecursive(x, 1, 1, depth); // Вычисляем arctg(x)
                    showResult("Результат arctg(" + x + ") = " + result);
                }
            } catch (NumberFormatException ex) {
                showResult("Введите корректное значение x");
            }
        });

        btnExit.addActionListener(e -> frame.dispose()); // Закрываем окно
    }

    // Рекурсивная функция для вычисления arctg(x)
    private double arctgRecursive(double x, int m, double sign, int maxDepth) {
        if (m > maxDepth) { // Проверяем, достигли ли максимальной глубины
            return 0; // Если достигли, возвращаем 0
        }
        double term = sign * Math.pow(x, 2 * m - 1) / (2 * m - 1); // Вычисление текущего члена ряда
        return term + arctgRecursive(x, m + 1, -sign, maxDepth); // Рекурсивный вызов с изменением знака
    }

    // Метод для отображения результата в текстовой области
    private void showResult(String message) {
        resultArea.setText(message); // Устанавливаем текст результата
    }
}