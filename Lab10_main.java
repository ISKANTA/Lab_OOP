package lab_10;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class Lab10_main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JTextField inputField;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Lab10_main frame = new Lab10_main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Lab10_main() {
        setBackground(new Color(128, 128, 255));
        setTitle("Lab_10");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 661, 585);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        inputField = new JTextField();
        inputField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        inputField.setBounds(10, 10, 627, 30);
        contentPane.add(inputField);
        inputField.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 50, 387, 488);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textArea.setBackground(new Color(255, 255, 255));

        JButton Sort = new JButton("Сортировка");
        Sort.setBackground(new Color(128, 255, 0));
        Sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                if (!inputText.isEmpty()) {
                    // Разбиваем строку на массив символов
                    String[] chars = inputText.split(" ");
                    textArea.setText("Исходный массив: " + arrayToString(chars) + "\n\n");
                    
                    new Thread(() -> {
                        try {
                            bubbleSortWithSteps(chars);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    }).start();
                } else {
                    textArea.setText("Введите строку для сортировки.");
                }
            }
        });
        Sort.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        Sort.setBounds(407, 50, 212, 69);
        contentPane.add(Sort);
    }

    private void bubbleSortWithSteps(String[] chars) throws InterruptedException {
        int n = chars.length;
        boolean swapped;
        int step = 0;
        Thread.sleep(1000);
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Показываем сравнение элементов
                final String compareMessage = "Шаг " + (++step) + ":";
                
                SwingUtilities.invokeLater(() -> {
                    textArea.append(compareMessage + "\n");
                });
                Thread.sleep(500);   
                if (chars[j].compareTo(chars[j + 1]) < 0) {     
                    Thread.sleep(500);
                    // Выполняем обмен
                    String temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                    swapped = true;
                    
                    // Показываем текущее состояние массива
                    SwingUtilities.invokeLater(() -> {
                        textArea.append("  Текущий массив: " + arrayToString(chars) + "\n");
                    });
                    
                    
                }
                else {
               
                        textArea.append("  Перестановка не нужна\n");
                  
                }
            }
            if (!swapped) { 
            	break;
            }
        }
        
        SwingUtilities.invokeLater(() -> {
            textArea.append("\nСортировка завершена!\n" +
                           "Отсортированный массив: " + arrayToString(chars));
        });
    }

    private String arrayToString(String[] array) {
        return "[" + String.join(", ", array) + "]";
    }
}