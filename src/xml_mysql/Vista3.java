package xml_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextField;

import java.awt.Choice;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class Vista3 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista3 window = new Vista3();
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
	public Vista3() {
		initialize();
	}
	
	private JButton btnAceptar;
	private Choice choice;
	private JButton btnNewButton;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		choice = new Choice();
		choice.setBounds(205, 175, 224, 20);
		choice.add("Selecciona una opción...");
		choice.add("Registrar nuevo usuario");
		choice.add("Añadir CSV");
		choice.add("Hacer SELECT tabla users");
		choice.add("Hacer SELECT tabla population");
		frame.getContentPane().add(choice);

		JLabel lblNewLabel_2 = new JLabel("OPTION");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(246, 105, 141, 37);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnAceptar = new JButton("Enviar");
		btnAceptar.setBounds(267, 224, 100, 22);
		frame.getContentPane().add(btnAceptar);
		
		btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(270, 257, 97, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public Choice getChoice() {
		return choice;
	}

	public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }

	public JButton getBtnAceptar() {
		// TODO Auto-generated method stub
		return btnAceptar;
	}
}
