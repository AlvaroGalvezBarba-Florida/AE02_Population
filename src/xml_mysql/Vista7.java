package xml_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista7 {

	private JFrame frame;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JCheckBox chckbxNewCheckBox_6;
	private JCheckBox chckbxNewCheckBox_7;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista7 window = new Vista7();
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
	public Vista7() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 651, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciona los campos que quieres ver");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(146, 11, 343, 25);
		frame.getContentPane().add(lblNewLabel);
		
		chckbxNewCheckBox = new JCheckBox("Pais");
		chckbxNewCheckBox.setBounds(146, 67, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox);

		chckbxNewCheckBox_1 = new JCheckBox("Poblacion");
		chckbxNewCheckBox_1.setBounds(355, 67, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("Densidad");
		chckbxNewCheckBox_2.setBounds(146, 122, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		chckbxNewCheckBox_3 = new JCheckBox("Area");
		chckbxNewCheckBox_3.setBounds(355, 122, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("Fertilidad");
		chckbxNewCheckBox_4.setBounds(355, 174, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_4);
		
		chckbxNewCheckBox_5 = new JCheckBox("Año");
		chckbxNewCheckBox_5.setBounds(146, 174, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_5);
		
		chckbxNewCheckBox_6 = new JCheckBox("Urbano");
		chckbxNewCheckBox_6.setBounds(355, 235, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_6);
		
		chckbxNewCheckBox_7 = new JCheckBox("Compartido");
		chckbxNewCheckBox_7.setBounds(146, 235, 134, 23);
		frame.getContentPane().add(chckbxNewCheckBox_7);
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(269, 265, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBounds(269, 299, 97, 23);
		frame.getContentPane().add(btnNewButton_1);
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

	public JCheckBox getChckbxNewCheckBox_3() {
		return chckbxNewCheckBox_3;
	}

	public JCheckBox getChckbxNewCheckBox_4() {
		return chckbxNewCheckBox_4;
	}

	public JCheckBox getChckbxNewCheckBox_5() {
		return chckbxNewCheckBox_5;
	}

	public JCheckBox getChckbxNewCheckBox_6() {
		return chckbxNewCheckBox_6;
	}

	public JCheckBox getChckbxNewCheckBox_7() {
		return chckbxNewCheckBox_7;
	}
	
	public void mostrar() {
        frame.setVisible(true);
    }

    public void ocultar() {
        frame.setVisible(false);
    }

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}
}
