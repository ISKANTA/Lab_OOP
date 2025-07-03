package lab9;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class lab9_main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;
    private int count_A;
    private FileProcessor processor;
    
    private ArrayList<String> fileLines; // Для хранения считанных строк
    private ArrayList<String> processedLines; // Для хранения обработанных строк

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    lab9_main frame = new lab9_main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public lab9_main() {
        setBackground(new Color(128, 128, 255));
        setTitle("Lab_9");
        
        getContentPane().setBackground(new Color(0, 128, 192));
        getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        getContentPane().setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 638, 508);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 255));
        contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textArea.setBackground(new Color(255, 255, 255));
        textArea.setBounds(10, 10, 460, 451);
        textArea.setLineWrap(true); // Включаем перенос строк
        textArea.setWrapStyleWord(true); // Переносим слова целиком
        contentPane.add(textArea);
        
        processor = new FileProcessor();
        
        JButton input_file = new JButton("Исходный файл");
        input_file.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReadFile fileReader = new ReadFile();
                String filePath = "input_file.txt"; 
                String fileContent = fileReader.read(filePath);
                
                // Отладочное сообщение
                System.out.println("Чтение файла прошла успешно!");
                
                if (fileContent.isEmpty()) {
                    textArea.setText("Файл пустой.");
                } else {
                    textArea.setText(fileContent);
                }
                
                fileLines = new ArrayList<>();
                for (String line : fileContent.split("\n")) {
                    fileLines.add(line);
                }
            }
        });
        input_file.setBackground(new Color(128, 255, 128));
        input_file.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        input_file.setBounds(480, 25, 134, 50);
        contentPane.add(input_file);
        
        JButton process = new JButton("Обработка");
        process.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                count_A = processor.count(fileLines); // Получаем количество слов с "А"

                // Отображаем обработанные строки в textArea
                processedLines = processor.getProcessedLines(); // Получаем обработанные строки
                textArea.setText(String.join("\n", processedLines)); // Это может быть просто сохранение строк, если нужно
                System.out.println("Обработка прошла успешно! Найдено слов, начинающихся с 'А': " + count_A);
            }
        });
        process.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        process.setBackground(new Color(128, 255, 128));
        process.setBounds(480, 107, 134, 50);
        contentPane.add(process);
        
        JButton output_file = new JButton("Запись в HTML");
        output_file.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String outputFilePath = "output.html";

                if (processedLines != null && !processedLines.isEmpty()) {
                    // Вызываем метод записи в файл
                    processor.write(processedLines, count_A, outputFilePath, "input_file.txt");
                    System.out.println("Файл успешно записан: " + outputFilePath);
                } else {
                    System.out.println("Обработанные строки пусты. Убедитесь, что файл был загружен и обработан.");
                }
            }
        });
        output_file.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        output_file.setBackground(new Color(128, 255, 128));
        output_file.setBounds(480, 190, 134, 50);
        contentPane.add(output_file);
    }
}