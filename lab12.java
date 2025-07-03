package Lab_12;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

public class lab12 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField inputField;
    private JTextField deleteField;
    private JTextArea displayArea;
    private LinkedList<String> list;
    private JButton searchButton;
    private JTextField searchField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    lab12 frame = new lab12();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public lab12() {
        list = new LinkedList<>();
        
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 633, 599);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 128, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        displayArea.setBounds(10, 10, 394, 542);
        displayArea.setEditable(false);
        displayArea.setBackground(Color.WHITE);
        contentPane.add(displayArea);
        
        JButton forwardButton = new JButton("В прямом направлении");
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayForward();
            }
        });
        forwardButton.setBackground(new Color(128, 255, 128));
        forwardButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        forwardButton.setBounds(414, 10, 195, 36);
        contentPane.add(forwardButton);
        
        JButton backwardButton = new JButton("В обратном направлении");
        backwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBackward();
            }
        });
        backwardButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        backwardButton.setBackground(new Color(128, 255, 128));
        backwardButton.setBounds(414, 70, 195, 36);
        contentPane.add(backwardButton);
        
        JButton addButton = new JButton("Добавление элемента");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addElement();
            }
        });
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        addButton.setBackground(new Color(128, 255, 128));
        addButton.setBounds(414, 193, 195, 36);
        contentPane.add(addButton);
        
        JButton deleteButton = new JButton("Удаление по значению");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteElement();
            }
        });
        deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        deleteButton.setBackground(new Color(128, 255, 128));
        deleteButton.setBounds(414, 317, 195, 36);
        contentPane.add(deleteButton);
        
        deleteField = new JTextField();
        deleteField.setBounds(414, 365, 195, 32);
        contentPane.add(deleteField);
        deleteField.setColumns(10);
        
        inputField = new JTextField();
        inputField.setBounds(414, 239, 195, 36);
        contentPane.add(inputField);
        inputField.setColumns(10);
        
        searchButton = new JButton("Поиск элемента");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchElement();
            }
        });
        searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        searchButton.setBackground(new Color(128, 255, 128));
        searchButton.setBounds(414, 420, 195, 36);
        contentPane.add(searchButton);
        
        searchField = new JTextField();
        searchField.setColumns(10);
        searchField.setBounds(414, 466, 195, 32);
        contentPane.add(searchField);
    }
    
    private void displayForward() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append("\n");
        }
        displayArea.setText(sb.toString());
    }
    
    private void displayBackward() {
        StringBuilder sb = new StringBuilder();
        ListIterator<String> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            sb.append(iterator.previous()).append("\n");
        }
        displayArea.setText(sb.toString());
    }
    
    private void addElement() {
        String element = inputField.getText().trim();
        if (!element.isEmpty()) {
            list.add(element);
            inputField.setText("");
            displayForward();
        }
    }
    
    private void deleteElement() {
        String element = deleteField.getText().trim();
        if (!element.isEmpty()) {
            if (list.remove(element)) {
            	JOptionPane.showMessageDialog(null, "Элемент '" + element + "' удален\n");
                displayForward();
            } else {
            	JOptionPane.showMessageDialog(null, "Элемент '" + element + "' не найден\n");
            }
            deleteField.setText("");
        }
    }
    
    private void searchElement() {
        String element = searchField.getText().trim();
        if (!element.isEmpty()) {
            ArrayList<Integer> indices = new ArrayList<>(); 
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(element)) {
                    indices.add(i + 1); 
                }
            }
            
            if (!indices.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Элемент '" + element + "' найден на позициях: " + indices + "\n");
            } 
            else {
                JOptionPane.showMessageDialog(null, "Элемент '" + element + "' не найден\n");
            }
            searchField.setText("");
        }
    }
}