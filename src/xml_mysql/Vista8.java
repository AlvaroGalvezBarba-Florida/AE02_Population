package xml_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista8 {

	private JFrame frame;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JCheckBox chckbxNewCheckBox_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista8 window = new Vista8();
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
	public Vista8() {
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
		
		JLabel lblNewLabel = new JLabel("Selecciona los campos que quieres ver");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(145, 50, 343, 25);
		frame.getContentPane().add(lblNewLabel);
		
		chckbxNewCheckBox = new JCheckBox("Id");
		chckbxNewCheckBox.setBounds(145, 86, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("User");
		chckbxNewCheckBox_1.setBounds(354, 86, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("Password");
		chckbxNewCheckBox_2.setBounds(145, 137, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("Type");
		chckbxNewCheckBox_3.setBounds(354, 137, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_3);

		btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(268, 284, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBounds(268, 318, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
	}

	public JCheckBox getChckbxNewCheckBox_3() {
		return chckbxNewCheckBox_3;
	}

	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public JCheckBox getChckbxNewCheckBox_1() {
		return chckbxNewCheckBox_1;
	}

	public JCheckBox getChckbxNewCheckBox_2() {
		return chckbxNewCheckBox_2;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
	
	public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }

}
