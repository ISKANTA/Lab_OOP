package lab9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileProcessor {

	private ArrayList<String> processedLines = new ArrayList<>();
	
	public int count(ArrayList<String> lines) {
	    processedLines.clear(); 
	    int count_A = 0;

	    for (String line : lines) {
	        StringTokenizer st = new StringTokenizer(line, " \t\n\r,.");
	       
	        while (st.hasMoreTokens()) {
	            String word = st.nextToken();
	            if (word.length() > 0) {
	                char lastChar = word.charAt(word.length() - 1);
	                if (lastChar == 'A' || lastChar == 'a' || lastChar == 'А' || lastChar == 'а') {
	                    count_A++;
	                }
	            }
	        }

	        processedLines.add(line);
	    }

	    // Записываем результат в HTML-файл
	    write(processedLines, count_A, "output.html", "input_file.txt");
	    
	    return count_A; // Возвращаем количество слов
	}
	
	public ArrayList<String> getProcessedLines() {
	    return processedLines;
	}

    // Метод для записи обработанных строк в HTML-файл
    void write(ArrayList<String> lines, int countWords, String outputFilePath, String inputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Записываем заголовок
            writer.write("<html><head><title>Лабораторная работа №9</title></head><body>");
            writer.write("<h1>Лабораторная работа №9</h1>");
            writer.write("<h2>Вариант A</h2>");
            writer.write("<p>Выполнил студент группы ИВТИИбд-11 <b>Гейбатов И. М.</b></p>");
            writer.write("<p>Файл: " + "<b>" + inputFileName + "</b>" + "</p>");
            writer.write("<p>Количество найденных слов: " + "<b>" + countWords + "</b>" + "</p>");
            writer.write("<h3>Обработанные строки:</h3>");
            writer.write("<pre>");

            // Записываем строки
            for (String line : lines) {
                writer.write(line);
                writer.newLine(); // Добавляем перенос строки
            }

            writer.write("</pre></body></html>");
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок ввода-вывода
        }
    }
}