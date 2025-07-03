package lab8;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.util.ArrayList;

public class lab_8_window extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private ArrayList<String> fileLines; // Для хранения считанных строк
    private ArrayList<String> processedLines; // Для хранения обработанных строк

    public lab_8_window() {
        setTitle("Lab_8");
        getContentPane().setBackground(new Color(0, 128, 192));
        getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        getContentPane().setLayout(null);
        
        textArea = new JTextArea();
        textArea.setBounds(10, 10, 433, 343);
        getContentPane().add(textArea);
        
        setSize(650, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton inputfile = new JButton("Исходный файл");
        inputfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReadFile fileReader = new ReadFile();
                String filePath = "input_file.txt"; 
                String fileContent = fileReader.read(filePath);
                textArea.setText(fileContent); // Устанавливаем содержимое файла в textArea
                
                // Сохраняем строки в список для дальнейшей обработки
                fileLines = new ArrayList<>();
                for (String line : fileContent.split("\n")) {
                    fileLines.add(line);
                }
            }
        });
        inputfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        inputfile.setBackground(Color.WHITE);
        inputfile.setBounds(474, 10, 152, 48);
        getContentPane().add(inputfile);
        
        JButton obrabotka = new JButton("Обработка");
        obrabotka.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileProcessor processor = new FileProcessor();
                processedLines = processor.processLines(fileLines); // Обрабатываем строки, сохраняем результат
            }
        });
        obrabotka.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        obrabotka.setBackground(Color.WHITE);
        obrabotka.setBounds(474, 78, 152, 48);
        getContentPane().add(obrabotka);
        
        JButton outputfile = new JButton("Обработанный файл");
        outputfile.setBackground(new Color(255, 255, 255));
        outputfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        outputfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileProcessor processor = new FileProcessor();
                String outputFilePath = "output_file.txt"; // Путь к выходному файлу
                processor.writeToFile(processedLines, outputFilePath); // Записываем обработанные строки в файл
                
                // Отображаем обработанные строки в textArea
                textArea.setText(String.join("\n", processedLines));
                System.out.print("Обработка прошла успешно: строка записана в " + outputFilePath);; // Добавляем сообщение
            }
        });
        outputfile.setBounds(474, 152, 152, 48);
        getContentPane().add(outputfile);
    }

    public static void main(String[] args) {
        lab_8_window window = new lab_8_window();
        window.setVisible(true);
    }
}