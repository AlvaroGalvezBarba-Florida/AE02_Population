package xml_mysql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Vista frame = new Vista();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	private JTextArea usuario;
	private JButton btnAceptar;
	private JPasswordField passwordField;
	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	private JButton btnNewButton_2;

	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 470);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(246, 118, 141, 14);
		contentPane.add(lblNewLabel);

		usuario = new JTextArea();
		usuario.setBounds(246, 143, 141, 22);
		usuario.setColumns(10);
		usuario.setTabSize(0);
		contentPane.add(usuario);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(246, 177, 141, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("LOGIN");
		lblNewLabel_2.setBounds(246, 53, 141, 48);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		contentPane.add(lblNewLabel_2);

		btnAceptar = new JButton("Enviar");
		btnAceptar.setBounds(267, 235, 100, 22);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnAceptar);

		passwordField = new JPasswordField();
		passwordField.setBounds(246, 202, 141, 22);
		passwordField.setBorder(null);
		contentPane.add(passwordField);
		
		btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setBounds(270, 268, 97, 23);
		contentPane.add(btnNewButton_2);

		setVisible(true);
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
