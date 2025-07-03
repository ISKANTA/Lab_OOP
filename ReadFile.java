package lab9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
public String read(String filePath) {
    	
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Добавляем строки с переносом
            }
        } 
        catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок ввода-вывода
        } 
        finally {
            try {
                if (reader != null) {
                    reader.close(); 
                }
            } 
            catch (IOException e) {
                e.printStackTrace(); // Обработка ошибок при закрытии
            }
        }
        return content.toString(); // Возвращаем содержимое файла
    }
}