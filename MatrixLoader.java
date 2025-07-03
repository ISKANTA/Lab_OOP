package lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixLoader {
    public static int[][] loadMatrix(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        // Считываем первую строку для получения размеров
        line = reader.readLine();
        String[] dimensions = line.split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        
        // Создаем матрицу нужного размера
        int[][] matrix = new int[rows][cols];

        // Считываем оставшиеся строки
        for (int i = 0; i < rows; i++) {
            line = reader.readLine();
            String[] numbers = line.split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        
        reader.close();
        return matrix;
    }
}