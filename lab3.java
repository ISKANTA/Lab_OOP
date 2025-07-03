package lab3;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class lab3 {

	private JFrame frame;

	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
	        e.printStackTrace();
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lab3 window = new lab3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public lab3() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Lab_3");
		frame.getContentPane().setBackground(new Color(128, 128, 192));
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 646, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 394, 335);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0,0));
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textArea.setBackground(new Color(255, 255, 255));
		panel.add(textArea, BorderLayout.CENTER);
		
		JSpinner SizeSpinner = new JSpinner();
		SizeSpinner.setBackground(new Color(0, 255, 204));
		SizeSpinner.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		SizeSpinner.setBounds(414, 39, 158, 20);
		frame.getContentPane().add(SizeSpinner);
		
		JLabel lblNewLabel = new JLabel("Число N");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(414, 11, 158, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton Figure1Button = new JButton("Фигура №1");
		Figure1Button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Figure1Button.setBackground(new Color(0, 128, 255));
		Figure1Button.setForeground(new Color(0, 0, 0));
		Figure1Button.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				int n = (int)SizeSpinner.getValue();
				int i = 1;
				String figure="";
				
				while(i <= n) {
					int j = 0;
					
					while(j < n) {
						
						figure += (i + j * 2) + " ";
						j++;
					
								
					}figure+="\n";
					i++;
					textArea.setText(figure);
				}
		}
			});
		Figure1Button.setBounds(414, 79, 158, 41);
		frame.getContentPane().add(Figure1Button);
		
		JButton Figure2Button = new JButton("Фигура №2");
		Figure2Button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Figure2Button.setBackground(new Color(0, 128, 255));
		Figure2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = (int)SizeSpinner.getValue();
				int i = 1;
				String figure="";
				
				while(i <= n) {
					int j = 1;
					while(j <= i) {	
					figure += j + " ";
						j++;
								
					}
					figure += "\n";
					i++;
					textArea.setText(figure);
				}
				
			}
		});
		Figure2Button.setBounds(414, 130, 158, 41);
		frame.getContentPane().add(Figure2Button);
		
		
		JButton Figure3Button = new JButton("Фигура №3");
		Figure3Button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Figure3Button.setBackground(new Color(0, 128, 255));
		Figure3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = (int)SizeSpinner.getValue();
				String figure="";
				int i=n;
				do {
					int j = 1;
					do {
						figure += j + " ";
						j++;
					}while (j <= i);
					figure += "\n";
					i--;
					textArea.setText(figure);
				} while (i > 0);
			}
		});
		Figure3Button.setBounds(414, 181, 158, 37);
		frame.getContentPane().add(Figure3Button);
		
		JButton Figure4Button = new JButton("Фигура №4");
		Figure4Button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Figure4Button.setBackground(new Color(0, 128, 255));
		Figure4Button.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				int n = (int)SizeSpinner.getValue();
				
				int i = 1;
				String figure = "";
				do {
					int j = n;
					do {
						figure += j + " ";
						j--;
					} while (j >= i);
					figure += "\n";
					i++;
					textArea.setText(figure);
				} while (i < n + 1);
				
		}});
		Figure4Button.setBounds(414, 228, 158, 37);
		frame.getContentPane().add(Figure4Button);
		
		 JButton btnNewButton = new JButton("Закрыть");
		 btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		 btnNewButton.setBackground(new Color(255, 0, 0));
		 btnNewButton.setForeground(new Color(0, 0, 0));
		    btnNewButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            frame.dispose(); // Закрывает текущее окно
		        }
		    });
		    btnNewButton.setBounds(414, 289, 158, 41);
		    frame.getContentPane().add(btnNewButton);	
	}
}