package xml_mysql;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista5 {

    public JTable getTable() {
		return table;
	}

	private JFrame frame;
    private JTable table;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;
	private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Vista5 window = new Vista5();
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
    public Vista5() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);

        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 634, 388);
        frame.getContentPane().add(scrollPane);
        
        btnNewButton = new JButton("Volver");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton.setBounds(545, 388, 89, 23);
        frame.getContentPane().add(btnNewButton);
        
        btnNewButton_1 = new JButton("Exportar CSV");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_1.setBounds(411, 388, 124, 23);
        frame.getContentPane().add(btnNewButton_1);
        
        btnNewButton_2 = new JButton("Logout");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnNewButton_2.setBounds(0, 388, 97, 23);
        frame.getContentPane().add(btnNewButton_2);
    }
    
    public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
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
}

