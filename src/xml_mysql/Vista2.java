package xml_mysql;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Dimension;

public class Vista2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista2 window = new Vista2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Vista2() {
		initialize();
	}
	
	private JTextArea usuario;
	private JButton btnAceptar;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(246, 124, 141, 14);
		frame.getContentPane().add(lblNewLabel);
		
		usuario = new JTextArea();
		usuario.setBounds(246, 149, 141, 22);
		usuario.setTabSize(0);
		usuario.setColumns(10);
		frame.getContentPane().add(usuario);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(246, 182, 141, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("REGISTER");
		lblNewLabel_2.setBounds(246, 76, 141, 37);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frame.getContentPane().add(lblNewLabel_2);
		
		btnAceptar = new JButton("Enviar");
		btnAceptar.setBounds(269, 298, 100, 22);
		frame.getContentPane().add(btnAceptar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(246, 207, 141, 22);
		passwordField.setBorder(null);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBorder(null);
		passwordField_1.setBounds(246, 265, 141, 22);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Confirmar contraseña");
		lblNewLabel_1_1.setBounds(246, 240, 141, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(272, 331, 97, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}

	public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }
	
	public JTextArea getUsuario() {
		return usuario;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
