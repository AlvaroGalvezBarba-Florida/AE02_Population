package xml_mysql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador {
	private Vista vista;
	private Vista2 vista2;
	private Vista3 vista3;
	private Vista4 vista4;
	private Vista5 vista5;
	private Vista6 vista6;
	private Vista7 vista7;
	private Vista8 vista8;
	private Vista9 vista9;
	private Modelo modelo;
	private ActionListener actionListenerEnviar;
	private int contadorUser = 0;
	private int contadorAdmin = 0;
	private List<JButton> buttons = new ArrayList<>();

	public Controlador(Vista vista, Vista2 vista2, Vista3 vista3, Vista4 vista4, Vista5 vista5, Vista6 vista6,
			Vista7 vista7, Vista8 vista8, Vista9 vista9, Modelo modelo) {
		this.vista = vista;
		this.vista2 = vista2;
		this.vista3 = vista3;
		this.vista4 = vista4;
		this.vista5 = vista5;
		this.vista6 = vista6;
		this.vista7 = vista7;
		this.vista8 = vista8;
		this.vista9 = vista9;
		this.modelo = modelo;
		this.modelo.setControlador(this);
		initEventHandlers();
	}

	public void initEventHandlers() {
		this.vista.setVisible(true);
		getButtons().add(vista.getBtnAceptar());
		getButtons().add(vista.getBtnNewButton_2());
		getButtons().add(vista2.getBtnAceptar());
		getButtons().add(vista2.getBtnNewButton());
		getButtons().add(vista3.getBtnAceptar());
		getButtons().add(vista3.getBtnNewButton());
		getButtons().add(vista4.getBtnAceptar());
		getButtons().add(vista4.getBtnNewButton());
		getButtons().add(vista5.getBtnNewButton());
		getButtons().add(vista5.getBtnNewButton_1());
		getButtons().add(vista5.getBtnNewButton_2());
		getButtons().add(vista6.getBtnNewButton());
		getButtons().add(vista6.getBtnNewButton_1());
		getButtons().add(vista7.getBtnNewButton());
		getButtons().add(vista7.getBtnNewButton_1());
		getButtons().add(vista8.getBtnNewButton());
		getButtons().add(vista8.getBtnNewButton_1());
		getButtons().add(vista9.getBtnNewButton());
		getButtons().add(vista9.getBtnAceptar());
		modelo.clearActionListeners(getButtons());
		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String password = hashWithMD5(vista.getPasswordField().getPassword());
				String name = vista.getUsuario().getText();
				modelo.conexion(name, password, vista, vista2, vista3, vista4, vista5, vista6, vista7, vista8, vista9);
			}
		};
		vista.getBtnAceptar().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista.dispose();
			}
		};
		vista.getBtnNewButton_2().addActionListener(actionListenerEnviar);
	}

	public void cargarDatosEnTabla(List<String[]> datos, List<String[]> nombreColumna, Vista7 vista7) {
		vista7.ocultar();
		vista5.mostrar();
		modelo.clearActionListeners(getButtons());
		agregarColumna(nombreColumna);
		try {
			DefaultTableModel tableModel = (DefaultTableModel) vista5.getTable().getModel();
			tableModel.setRowCount(0);
			for (String[] fila : datos) {
				tableModel.addRow(fila);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
		}

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista5.ocultar();
				modelo.vistaUser(vista, vista2, vista3, vista4, vista7, vista9);
				deleteAllTable();
			}
		};
		vista5.getBtnNewButton().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String archivo = "output_User_" + contadorUser + ".csv";
				contadorUser++;
				modelo.escribirCSV(archivo, nombreColumna, datos);
			}
		};
		vista5.getBtnNewButton_1().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				deleteAllTable();
				vista5.ocultar();
				initEventHandlers();
			}
		};
		vista5.getBtnNewButton_2().addActionListener(actionListenerEnviar);

	}

	public void cargarDatosEnTablaAdmin(List<String[]> datos, List<String[]> nombreColumna, Vista8 vista8) {
		vista8.ocultar();
		vista5.mostrar();
		modelo.clearActionListeners(getButtons());
		agregarColumna(nombreColumna);
		try {
			DefaultTableModel tableModel = (DefaultTableModel) vista5.getTable().getModel();
			tableModel.setRowCount(0);
			for (String[] fila : datos) {
				tableModel.addRow(fila);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
		}

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista5.ocultar();
				modelo.vistaAdmin(vista, vista3, vista7, vista8, vista9);
				deleteAllTable();
			}
		};
		vista5.getBtnNewButton().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String archivo = "output_Admin_" + contadorAdmin + ".csv";
				contadorAdmin++;
				modelo.escribirCSV(archivo, nombreColumna, datos);
			}
		};
		vista5.getBtnNewButton_1().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				deleteAllTable();
				vista5.ocultar();
				initEventHandlers();
			}
		};
		vista5.getBtnNewButton_2().addActionListener(actionListenerEnviar);

	}
	
	private void agregarColumna(List<String[]> nombreColumna) {
		for (String[] fila : nombreColumna) {
			for (String columna : fila) {
				vista5.getTableModel().addColumn(columna);
			}
		}
	}

	public void deleteAllTable() {
		vista5.getTableModel().setRowCount(0);
		vista5.getTableModel().setColumnCount(0);
	}

	public static String hashWithMD5(char[] input) {
		try {
		    // Crear instancia de MD5
		    MessageDigest md = MessageDigest.getInstance("MD5");

		    // Convertir char[] a byte[]
		    byte[] inputBytes = new String(input).getBytes(StandardCharsets.UTF_8);

		    // Calcular el hash
		    byte[] hashBytes = md.digest(inputBytes);

		    // Convertir el array de bytes en formato hexadecimal
		    StringBuilder hexString = new StringBuilder();
		    for (byte b : hashBytes) {
		        String hex = Integer.toHexString(0xff & b);
		        if (hex.length() == 1) {
		            hexString.append('0');
		        }
		        hexString.append(hex);
		    }
		    return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		    return null;
		} finally {
			// Limpiar datos sensibles
		    Arrays.fill(input, '\0');
		}
	}

	public List<JButton> getButtons() {
		return buttons;
	}
}