package lab_7;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Lab7App {

    private JFrame frame;
    private Game_Panel gamePanel; // Добавляем GamePanel
    private int size_field = 80; // Размер клетки
    private String[] levelFiles = {"lvl_1.txt", "lvl_2.txt", "lvl_3.txt"}; // Массив с именами файлов уровней

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Lab7App window = new Lab7App();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Lab7App() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Lab 7 Game");
        frame.setBounds(0, 0, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Создаем и добавляем панель игры
        gamePanel = new Game_Panel(levelFiles, size_field);
        gamePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        gamePanel.setBounds(0, 0, 600, 600);
        frame.getContentPane().add(gamePanel);

        // Добавляем обработчик событий для клавиатуры
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        gamePanel.movePlayer(-1, 0); // Перемещение влево
                        break;
                    case KeyEvent.VK_RIGHT:
                        gamePanel.movePlayer(1, 0); // Перемещение вправо
                        break;
                    case KeyEvent.VK_UP:
                        gamePanel.movePlayer(0, -1); // Перемещение вверх
                        break;
                    case KeyEvent.VK_DOWN:
                        gamePanel.movePlayer(0, 1); // Перемещение вниз
                        break;
                }
            }
        });
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
    }
}