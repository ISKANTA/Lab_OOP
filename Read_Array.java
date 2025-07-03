package lab_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read_Array {

    public static int[][] Read_Array(String filename) throws IOException {
        // Создаем BufferedReader для чтения из указанного файла
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        // Считываем первую строку для получения размеров
        line = reader.readLine(); 
        String[] dimensions = line.split(" "); // Разбиваем строку на части (количество строк и столбцов)
        int rows = Integer.parseInt(dimensions[0]); // Получаем количество строк
        int cols = Integer.parseInt(dimensions[1]); // Получаем количество столбцов
        
        // Создаем матрицу нужного размера
        int[][] matrix = new int[rows][cols];

        // Считываем оставшиеся строки
        for (int i = 0; i < rows; i++) { // Проходим по всем строкам
            line = reader.readLine(); // Читаем следующую строку
            String[] numbers = line.split(" "); // Разбиваем строку на числа
            for (int j = 0; j < cols; j++) { // Проходим по всем столбцам
                matrix[i][j] = Integer.parseInt(numbers[j]); // Заполняем матрицу
            }
        }
        reader.close();

        return matrix; // Возвращаем заполненную матрицу
    }
}