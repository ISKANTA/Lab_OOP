package Lab_11;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

// Класс узла двусвязного списка
class Node {
    String data;
    Node prev;
    Node next;

    Node(String data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// Класс двусвязного списка
class DoublyLinkedList {
    private Node head = null;
    private Node tail = null;

    // Внутренний класс узла
    class Node {
        String data;
        Node prev;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }
    
    public boolean insertAt(int index, String data) {
        if (index < 0) return false;
        if (data == null || data.trim().isEmpty()) return false;
        Node newNode = new Node(data);
        if (head == null && index == 0) {
            head = tail = newNode;
            return true;
        }
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
            return true;
        }
        Node current = head;
        int i = 0;
        while (current != null && i < index) {
            current = current.next;
            i++;
        }
        if (current == null && i == index) {
            // вставка в конец
            add(data);
            return true;
        } else if (current != null) {
            Node prevNode = current.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = current;
            current.prev = newNode;
            return true;
        }
        return false; // индекс за пределами
    }

    // Добавление в конец
    public void add(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } 
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Удаление по значению (удаляет первый найденный)
    public boolean removeByValue(String value) {
        if (value == null || value.trim().isEmpty()) return false;
        Node current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                // Удаляем текущий узел
                if (current == head) {
                    head = current.next;
                    if (head != null) head.prev = null;
                    else tail = null; // список стал пустым
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return true; // Удалили первый подходящий
            }
            current = current.next;
        }
        return false; // Не нашли элемент
    }

    // Удаление по индексу
    public boolean removeAt(int index) {
        if (index < 0) return false;
        Node current = head;
        int i = 0;
        while (current != null && i < index) {
            current = current.next;
            i++;
        }
        if (current == null) return false; // индекс вне границ
        // Удаляем текущий узел
        if (current == head) {
            head = current.next;
            if (head != null) head.prev = null;
            else tail = null; // список стал пустым
        } else if (current == tail) {
            tail = current.prev;
            if (tail != null) tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        return true;
    }

    // Для теста: вывод списка
    public String display(boolean forward) {
        StringBuilder sb = new StringBuilder();
        if (forward) {
            Node current = head;
            while (current != null) {
                sb.append(current.data).append("\n");
                current = current.next;
            }
        } else {
            Node current = tail;
            while (current != null) {
                sb.append(current.data).append("\n");
                current = current.prev;
            }
        }
        return sb.toString();
    }
}

// Основной класс окна
public class lab_11_frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JScrollPane scrollPane;
    private JPanel panel;
    private DoublyLinkedList list = new DoublyLinkedList();
    private boolean forwardDisplay = true; // направление отображения

    // Для выбора индекса
    private JSpinner spinner;
    // Для удаления по индексу
    private JSpinner spinner_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                lab_11_frame frame = new lab_11_frame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public lab_11_frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 0, 160));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Панель для отображения списка
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 385, 534);
        contentPane.add(scrollPane);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        scrollPane.setViewportView(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Кнопки и поля
        JButton btnForward = new JButton("Прямое направление");
        btnForward.setBounds(405, 23, 201, 42);
        btnForward.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnForward.setBackground(new Color(0, 255, 128));
        contentPane.add(btnForward);

        JButton btnBackward = new JButton("Обратное направление");
        btnBackward.setBounds(405, 93, 201, 42);
        btnBackward.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnBackward.setBackground(new Color(0, 255, 128));
        contentPane.add(btnBackward);

        JButton btnAdd = new JButton("Добавление элемента");
        btnAdd.setBounds(405, 160, 201, 42);
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnAdd.setBackground(new Color(0, 255, 128));
        contentPane.add(btnAdd);

        JButton btnRemoveValue = new JButton("Удалить элемент по значению");
        btnRemoveValue.setBounds(405, 264, 201, 42);
        btnRemoveValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnRemoveValue.setBackground(new Color(0, 255, 128));
        contentPane.add(btnRemoveValue);

        JButton btnRemoveIndex = new JButton("Удалить элемент по индексу");
        btnRemoveIndex.setBounds(405, 380, 201, 42);
        btnRemoveIndex.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnRemoveIndex.setBackground(new Color(0, 255, 128));
        contentPane.add(btnRemoveIndex);

        // Поле для текста
        textField = new JTextField();
        textField.setBounds(405, 212, 201, 35);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(textField);
        textField.setColumns(10);

        // Спиннер для индекса при добавлении
        spinner = new JSpinner();
        spinner.setBounds(405, 316, 201, 35);
        spinner.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(spinner);

        // Спиннер для удаления по индексу
        spinner_1 = new JSpinner();
        spinner_1.setBounds(405, 432, 201, 35);
        spinner_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        contentPane.add(spinner_1);

        // Обработчики кнопок
        btnForward.addActionListener(e -> {
            forwardDisplay = true;
            refreshDisplay();
        });

        btnBackward.addActionListener(e -> {
            forwardDisplay = false;
            refreshDisplay();
        });

        btnAdd.addActionListener(e -> {
            String text = textField.getText();
            int index = (Integer) spinner.getValue();
            list.insertAt(index, text);
            textField.setText("");
            refreshDisplay();
        });

        btnRemoveValue.addActionListener(e -> {
            String value = String.valueOf(spinner.getValue()); // берет значение из спиннера
            boolean removed = list.removeByValue(value);
            if (removed) {
                JOptionPane.showMessageDialog(null, "Элемент со значением " + value + " успешно удален.");
            } else {
                JOptionPane.showMessageDialog(null, "Элемент со значением " + value + " не найден.");
            }
            refreshDisplay();
        });

        btnRemoveIndex.addActionListener(e -> {
            int index = (Integer) spinner_1.getValue();
            boolean removed = list.removeAt(index);
            if (removed) {
                JOptionPane.showMessageDialog(null, "Элемент по индексу успешно удален.");
            } else {
                JOptionPane.showMessageDialog(null, "Некорректный индекс.");
            }
            refreshDisplay();
        });

        // Первичное отображение
        refreshDisplay();
    }

 // Метод для обновления отображения списка
    private void refreshDisplay() {
        panel.removeAll();
        String content = list.display(forwardDisplay);
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                JLabel label = new JLabel(line);
                label.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                panel.add(label);
            }
            
        }
        panel.revalidate();
        panel.repaint();
    }
}