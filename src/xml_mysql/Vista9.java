package xml_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Vista9 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JButton btnAceptar;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista9 window = new Vista9();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vista9() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SELECT");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(263, 77, 107, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(162, 125, 141, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(162, 150, 141, 22);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_3 = new JLabel("User");
		lblNewLabel_3.setBounds(338, 125, 141, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(338, 150, 141, 22);
		frame.getContentPane().add(textField_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(162, 210, 141, 22);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(162, 185, 141, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(338, 210, 141, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setBounds(338, 185, 141, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnAceptar = new JButton("Enviar");
		btnAceptar.setBounds(267, 243, 100, 22);
		frame.getContentPane().add(btnAceptar);
		
		btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(267, 276, 100, 22);
		frame.getContentPane().add(btnNewButton);
	}
	
	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void mostrar() {
		frame.setVisible(true);
	}

	public void ocultar() {
		frame.setVisible(false);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}
}
