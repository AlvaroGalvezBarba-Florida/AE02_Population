package xml_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Vista4 {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_6;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JButton btnAceptar;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista4 window = new Vista4();
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
	public Vista4() {
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
		lblNewLabel.setBounds(263, 33, 107, 37);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pais");
		lblNewLabel_1.setBounds(150, 81, 141, 14);
		frame.getContentPane().add(lblNewLabel_1);

		
		
		JLabel lblNewLabel_2 = new JLabel("Poblacion");
		lblNewLabel_2.setBounds(326, 81, 141, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 106, 141, 22);
		frame.getContentPane().add(textField_1);

		JLabel lblNewLabel_3 = new JLabel("Densidad");
		lblNewLabel_3.setBounds(150, 137, 141, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(326, 106, 141, 22);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 164, 141, 22);
		frame.getContentPane().add(textField_3);

		JLabel lblNewLabel_4 = new JLabel("Area");
		lblNewLabel_4.setBounds(326, 137, 141, 14);
		frame.getContentPane().add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(326, 164, 141, 22);
		frame.getContentPane().add(textField_4);

		JLabel lblNewLabel_5 = new JLabel("Fertilidad");
		lblNewLabel_5.setBounds(150, 195, 141, 14);
		frame.getContentPane().add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(150, 222, 141, 22);
		frame.getContentPane().add(textField_5);

		JLabel lblNewLabel_6 = new JLabel("Año");
		lblNewLabel_6.setBounds(326, 195, 141, 14);
		frame.getContentPane().add(lblNewLabel_6);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(326, 222, 141, 22);
		frame.getContentPane().add(textField_6);
		
		JLabel lblNewLabel_7 = new JLabel("Urbano");
		lblNewLabel_7.setBounds(150, 253, 141, 14);
		frame.getContentPane().add(lblNewLabel_7);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(150, 281, 141, 22);
		frame.getContentPane().add(textField_7);

		JLabel lblNewLabel_8 = new JLabel("Compratido");
		lblNewLabel_8.setBounds(326, 253, 141, 14);
		frame.getContentPane().add(lblNewLabel_8);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(326, 281, 141, 22);
		frame.getContentPane().add(textField_8);
		
		btnAceptar = new JButton("Enviar");
		btnAceptar.setBounds(270, 314, 100, 22);
		frame.getContentPane().add(btnAceptar);
		
		btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(270, 347, 100, 22);
		frame.getContentPane().add(btnNewButton);
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_6() {
		return textField_6;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public JTextField getTextField_4() {
		return textField_4;
	}

	public JTextField getTextField_5() {
		return textField_5;
	}

	public JTextField getTextField_7() {
		return textField_7;
	}

	public JTextField getTextField_8() {
		return textField_8;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void mostrar() {
		frame.setVisible(true);
	}

	public void ocultar() {
		frame.setVisible(false);
	}
}
