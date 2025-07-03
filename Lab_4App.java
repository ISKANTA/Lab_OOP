package lab4;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

public class Lab_4App {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Lab_4App window = new Lab_4App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Lab_4App() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Lab_4");
        frame.setBounds(100, 100, 592, 451);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        MyPanel panel = new MyPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(10, 10, 373, 394);
        frame.getContentPane().add(panel);
        
        JButton btnNewButton = new JButton("Домик");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.setfigure(1);; 
            }
        });
        btnNewButton.setBounds(393, 71, 175, 37);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Узор");
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel.setfigure(2);
        	}
        });
        btnNewButton_1.setBounds(393, 136, 175, 37);
        frame.getContentPane().add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Выход");
        btnNewButton_2.setBackground(new Color(255, 255, 255));
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
        btnNewButton_2.setForeground(new Color(0, 0, 0));
        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton_2.setBounds(393, 334, 175, 37);
        frame.getContentPane().add(btnNewButton_2);
  
    }
}