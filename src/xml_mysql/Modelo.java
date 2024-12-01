package xml_mysql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Modelo {
	private Connection con;
	private ActionListener actionListenerEnviar;
	private Controlador controlador;

	public void conexion(String name, String password, Vista vista, Vista2 vista2, Vista3 vista3, Vista4 vista4,
			Vista5 vista5, Vista6 vista6, Vista7 vista7, Vista8 vista8, Vista9 vista9) {
		try {
			// Cargar el driver JDBC para MySQL
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/population", name, password);

		    if (name.equals("admin")) {
		        vistaSeleccionadaAdmin(vista, vista2, vista3, vista4, vista5, vista6, vista7, vista8, vista9);
		    } else {
		        vistaUser(vista, vista2, vista3, vista4, vista7, vista9);
		    }

		    vista.getUsuario().setText("");
		    vista.getPasswordField().setText("");
		} catch (Exception e) {
		    System.out.println(e);
		    JOptionPane.showMessageDialog(vista, "Este usuario no existe inserta otro", "Mensaje",
		            JOptionPane.INFORMATION_MESSAGE);
		    vista.getUsuario().setText("");
		    vista.getPasswordField().setText("");
		}

	}

	private void vistaSeleccionadaAdmin(Vista vista, Vista2 vista2, Vista3 vista3, Vista4 vista4, Vista5 vista5,
			Vista6 vista6, Vista7 vista7, Vista8 vista8, Vista9 vista9) {
		vista.dispose();
		vista3.mostrar();
		clearActionListeners(controlador.getButtons());

		actionListenerEnviar = e -> {
			String selectedOption = vista3.getChoice().getSelectedItem();
			if (selectedOption.equals("Selecciona una opción...")) {
				JOptionPane.showMessageDialog(vista, "Selecciona una opción", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (selectedOption.equals("Registrar nuevo usuario")) {
				insertNewUserSQL(vista, vista2, vista3, vista4, vista7, vista9);
			} else if (selectedOption.equals("Añadir CSV")) {
				adminFunctions(vista, vista3, vista6);
			} else if(selectedOption.equals("Hacer SELECT tabla population")) {
				vistaUser(vista, vista2, vista3, vista4, vista7, vista9);
			} else {
				vistaAdmin(vista, vista3, vista7, vista8, vista9);
			}
		};
		vista3.getBtnAceptar().addActionListener(actionListenerEnviar);

		actionListenerEnviar = e -> {
			vista3.ocultar();
			controlador.initEventHandlers();
		};
		vista3.getBtnNewButton().addActionListener(actionListenerEnviar);
	}

	private void insertNewUserSQL(Vista vista, Vista2 vista2, Vista3 vista3, Vista4 vista4, Vista7 vista7, Vista9 vista9) {
		vista3.ocultar();
		vista2.mostrar();
		clearActionListeners(controlador.getButtons());

		actionListenerEnviar = e -> {
			if (!vista2.getUsuario().getText().isEmpty() && vista2.getPasswordField().getPassword().length > 0 && Arrays
					.equals(vista2.getPasswordField_1().getPassword(), vista2.getPasswordField().getPassword())) {
				String newUser = vista2.getUsuario().getText();
				String newPassword = controlador.hashWithMD5(vista2.getPasswordField().getPassword());
				try {
					List<String> existingUsers = new ArrayList<>();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT User, Host FROM mysql.user");
					while (rs.next()) {
						existingUsers.add(rs.getString("User"));
					}

					if (existingUsers.contains(newUser)) {
						JOptionPane.showMessageDialog(vista, "El usuario ya existe inserta otro", "Mensaje",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						String createUserQuery = "CREATE USER '" + newUser + "' IDENTIFIED BY '" + newPassword + "'";
						String grantQuery = "GRANT SELECT on population.population TO '" + newUser + "'";
						stmt.executeUpdate(createUserQuery);
						stmt.executeUpdate(grantQuery);
						insertNewUserDataBase(newUser, newPassword);
						vistaUser(vista, vista2, vista3, vista4, vista7, vista9);
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			} else if (!Arrays.equals(vista2.getPasswordField_1().getPassword(),
					vista2.getPasswordField().getPassword())) {
				JOptionPane.showMessageDialog(vista, "La contraseña no coincide con la confirmación", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(vista, "Necesitas rellenar todos los campos", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
			}
		};
		vista2.getBtnAceptar().addActionListener(actionListenerEnviar);

		actionListenerEnviar = e -> {
			vista2.ocultar();
			controlador.initEventHandlers();
		};
		vista2.getBtnNewButton().addActionListener(actionListenerEnviar);
	}

	private void insertNewUserDataBase(String newUser, String newPassword) {
		try {
			PreparedStatement psInsertar = con
					.prepareStatement("INSERT INTO users (login,password,type) VALUES (?,?,?)");
			psInsertar.setString(1, newUser);
			psInsertar.setString(2, newPassword);
			psInsertar.setString(3, "client");
			psInsertar.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void vistaUser(Vista vista, Vista2 vista2, Vista3 vista3, Vista4 vista4, Vista7 vista7, Vista9 vista9) {
		List<String[]> datos = new ArrayList<>();
		vista.dispose();
		vista2.ocultar();
		vista3.ocultar();
		vista4.mostrar();
		datos.clear();
		clearActionListeners(controlador.getButtons());
		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					String country = vista4.getTextField_1().getText();
					String population = vista4.getTextField_2().getText();
					String density = vista4.getTextField_3().getText();
					String area = vista4.getTextField_4().getText();
					String fertility = vista4.getTextField_5().getText();
					String age = vista4.getTextField_6().getText();
					String urban = vista4.getTextField_7().getText();
					String share = vista4.getTextField_8().getText();
					String sentenceWhere = "";
					Statement stmt = con.createStatement();

					if (country.isEmpty()) {
						sentenceWhere += "where country like '" + country + "%'";
					} else {
						sentenceWhere += "where country like '" + country + "%'";
					}
					if (!population.isEmpty()) {
						sentenceWhere += " AND population like '" + population + "%'";
					}
					if (!density.isEmpty()) {
						sentenceWhere += " AND density like '" + density + "%'";
					}
					if (!area.isEmpty()) {
						sentenceWhere += " AND area like '" + area + "%'";
					}
					if (!fertility.isEmpty()) {
						sentenceWhere += " AND fertility like '" + fertility + "%'";
					}
					if (!age.isEmpty()) {
						sentenceWhere += " AND age like '" + age + "%'";
					}
					if (!urban.isEmpty()) {
						sentenceWhere += " AND urban like '" + urban + "%'";
					}
					if (!share.isEmpty()) {
						sentenceWhere += " AND share like '" + share + "%'";
					}

					ResultSet rs = stmt.executeQuery("Select * from population.population " + sentenceWhere);
					while (rs.next()) {
						datos.add(new String[] { rs.getString("country"), rs.getString("population"),
								rs.getString("density"), rs.getString("area"), rs.getString("fertility"),
								rs.getString("age"), rs.getString("urban"), rs.getString("share") });
					}
					seleccionarEncabezado(vista4, vista7, datos);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		};

		vista4.getBtnAceptar().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista4.ocultar();
				controlador.initEventHandlers();
			}
		};
		vista4.getBtnNewButton().addActionListener(actionListenerEnviar);
	}

	public void vistaAdmin(Vista vista, Vista3 vista3, Vista7 vista7, Vista8 vista8, Vista9 vista9) {
		List<String[]> datos = new ArrayList<>();
		vista3.ocultar();
		vista9.mostrar();
		vista.dispose();
		datos.clear();
		clearActionListeners(controlador.getButtons());
		actionListenerEnviar = e -> {
			try {
				String id = vista9.getTextField().getText();
				String user = vista9.getTextField_1().getText();
				String type = vista9.getTextField_3().getText();
				String sentenceWhere = "";
				Statement stmt = con.createStatement();

				if (id.isEmpty()) {
					sentenceWhere += "where id like '" + id + "%'";
				} else {
					sentenceWhere += "where id like '" + id + "%'";
				}
				if (!user.isEmpty()) {
					sentenceWhere += " AND login like '" + user + "%'";
				}
				if (!type.isEmpty()) {
					sentenceWhere += " AND type like '" + type + "%'";
				}
				
				ResultSet rs = stmt.executeQuery("Select * from population.users " + sentenceWhere);
				while (rs.next()) {
					datos.add(new String[] { rs.getString("id"), rs.getString("login"), 
							rs.getString("password"), rs.getString("type")});
				}
				seleccionarEncabezadoAdmin(vista8, vista9, datos);
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			
		};
		vista9.getBtnAceptar().addActionListener(actionListenerEnviar);

		actionListenerEnviar = e -> {
			vista9.ocultar();
			controlador.initEventHandlers();
		};
		vista9.getBtnNewButton().addActionListener(actionListenerEnviar);
	}
	
	public void adminFunctions(Vista vista, Vista3 vista3, Vista6 vista6) {
		vista.dispose();
		vista3.ocultar();
		vista6.mostrar();
		clearActionListeners(controlador.getButtons());
		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				createTable(vista, vista3, vista6);
				createXML(vista, vista3, vista6);
				insertData(vista, vista3, vista6);
			}
		};
		vista6.getBtnNewButton().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista6.ocultar();
				controlador.initEventHandlers();
			}
		};
		vista6.getBtnNewButton_1().addActionListener(actionListenerEnviar);
	}

	private void createTable(Vista vista, Vista3 vista3, Vista6 vista6) {
		String csvFilePath = vista6.getTextField().getText();

		try {
			List<String[]> rows = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] columns = line.split(";");
					rows.add(columns);
				}
			}

			if (rows.isEmpty()) {
				JOptionPane.showMessageDialog(vista, "El archivo CSV está vacío.", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
			}

			String[] headers = rows.get(0);

			try (Statement stmt = con.createStatement()) {
				String query = "DROP TABLE IF EXISTS population.population";
				stmt.executeUpdate(query);
			}

			StringBuilder query = new StringBuilder("CREATE TABLE population.population (");
			for (String column : headers) {
				query.append(column).append(" VARCHAR(30), ");
			}
			query.setLength(query.length() - 2);
			query.append(")");

			try (Statement stmt = con.createStatement()) {
				stmt.executeUpdate(query.toString());
			}
		} catch (IOException | SQLException e) {
			System.out.println(e);
		}
	}

	private void createXML(Vista vista, Vista3 vista3, Vista6 vista6) {
		if (!vista6.getTextField().getText().isEmpty()) { 
		    // Verificar que el campo de texto no esté vacío
		    String csvFile = vista6.getTextField().getText(); // Obtener la ruta del archivo CSV desde el campo de texto
		    File xmlDir = new File("xml"); // Crear un directorio para almacenar los archivos XML

		    // Si el directorio "xml" no existe, crearlo
		    if (!xmlDir.exists()) {
		        xmlDir.mkdir();
		    }

		    // StringBuilder para concatenar el contenido de todos los archivos XML
		    StringBuilder concatenatedXMLContent = new StringBuilder();

		    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
		        String line;

		        // Leer cada línea del archivo CSV
		        while ((line = br.readLine()) != null) {
		            // Dividir la línea en campos separados por ";"
		            String[] fields = line.split(";");

		            // Asignar cada campo a una variable
		            String country = fields[0];
		            String population = fields[1];
		            String density = fields[2];
		            String area = fields[3];
		            String fertility = fields[4];
		            String age = fields[5];
		            String urban = fields[6];
		            String share = fields[7];

		            // Crear un documento XML
		            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder builder = factory.newDocumentBuilder();
		            Document doc = builder.newDocument();

		            // Crear el elemento raíz del documento ("Country")
		            Element rootElement = doc.createElement("Country");
		            doc.appendChild(rootElement);

		            // Crear y agregar cada campo como un elemento XML
		            Element name = doc.createElement("Name");
		            name.appendChild(doc.createTextNode(country));
		            rootElement.appendChild(name);

		            Element field = doc.createElement("Population");
		            field.appendChild(doc.createTextNode(population));
		            rootElement.appendChild(field);

		            Element field1 = doc.createElement("Density");
		            field1.appendChild(doc.createTextNode(density));
		            rootElement.appendChild(field1);

		            Element field2 = doc.createElement("Area");
		            field2.appendChild(doc.createTextNode(area));
		            rootElement.appendChild(field2);

		            Element field3 = doc.createElement("Fertility");
		            field3.appendChild(doc.createTextNode(fertility));
		            rootElement.appendChild(field3);

		            Element field4 = doc.createElement("Age");
		            field4.appendChild(doc.createTextNode(age));
		            rootElement.appendChild(field4);

		            Element field5 = doc.createElement("Urban");
		            field5.appendChild(doc.createTextNode(urban));
		            rootElement.appendChild(field5);

		            Element field6 = doc.createElement("Share");
		            field6.appendChild(doc.createTextNode(share));
		            rootElement.appendChild(field6);

		            // Preparar para escribir el archivo XML
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		            DOMSource source = new DOMSource(doc);

		            // Crear el archivo XML en el directorio "xml" con el nombre del país
		            File xmlFile = new File(xmlDir, country + ".xml");
		            StreamResult result = new StreamResult(xmlFile);
		            transformer.transform(source, result);

		            // Leer el contenido del archivo XML generado y concatenarlo
		            concatenatedXMLContent.append("Contenido de ").append(country).append(": \n");
		            try (BufferedReader xmlReader = new BufferedReader(new FileReader(xmlFile))) {
		                String xmlLine;
		                while ((xmlLine = xmlReader.readLine()) != null) {
		                    concatenatedXMLContent.append(xmlLine).append("\n");
		                }
		            }
		        }
		    } catch (Exception e) {
		        // Manejo de excepciones: imprimir el error en caso de fallo
		        System.out.println(e);
		    }
		} else {
		    // Mostrar un mensaje si el campo de texto está vacío
		    JOptionPane.showMessageDialog(vista, "Escribe la ruta del csv", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void insertData(Vista vista, Vista3 vista3, Vista6 vista6) {
		File xmlFolder = new File("xml");

		// Verificar si la carpeta "xml" existe y es un directorio
		if (!xmlFolder.exists() || !xmlFolder.isDirectory()) {
		    // Mostrar un mensaje de error si no existe o no es válida
		    JOptionPane.showMessageDialog(vista, "La carpeta 'xml' no existe o no es un directorio válido.", "Mensaje",
		            JOptionPane.INFORMATION_MESSAGE);
		}

		try {
		    // Obtener la lista de archivos XML en la carpeta "xml"
		    File[] files = xmlFolder.listFiles((dir, name) -> name.endsWith(".xml"));

		    // Verificar si no hay archivos XML en la carpeta
		    if (files == null || files.length == 0) {
		        // Mostrar un mensaje de error si no se encuentran archivos XML
		        JOptionPane.showMessageDialog(vista, "No se encontraron archivos XML en la carpeta.", "Mensaje",
		                JOptionPane.INFORMATION_MESSAGE);
		    }

		    // Consulta de inserción SQL para la tabla "population"
		    String insertQuery = "INSERT INTO population.population (country, population, density, area, fertility, age, urban, share) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		    // Preparar el statement para ejecutar la consulta de manera segura
		    try (PreparedStatement pstmt = con.prepareStatement(insertQuery)) {
		        // Recorrer todos los archivos XML encontrados
		        for (File xmlFile : files) {
		            try {
		                // Crear un DocumentBuilder para leer los archivos XML
		                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		                DocumentBuilder builder = factory.newDocumentBuilder();

		                // Parsear el archivo XML actual
		                Document doc = builder.parse(new FileInputStream(xmlFile));
		                doc.getDocumentElement().normalize(); // Normalizar la estructura XML

		                // Obtener todos los nodos "Country" del archivo XML
		                NodeList nodeList = doc.getElementsByTagName("Country");

		                // Recorrer cada nodo "Country"
		                for (int i = 0; i < nodeList.getLength(); i++) {
		                    Node countryNode = nodeList.item(i);

		                    // Verificar si el nodo es un elemento válido
		                    if (countryNode.getNodeType() == Node.ELEMENT_NODE) {
		                        // Extraer los valores de cada campo del nodo
		                        String name = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Name").item(0)
		                                .getTextContent().trim();
		                        String population = ((org.w3c.dom.Element) countryNode)
		                                .getElementsByTagName("Population").item(0).getTextContent().trim();
		                        String density = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Density")
		                                .item(0).getTextContent().trim();
		                        String area = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Area").item(0)
		                                .getTextContent().trim();
		                        String fertility = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Fertility")
		                                .item(0).getTextContent().trim();
		                        String age = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Age").item(0)
		                                .getTextContent().trim();
		                        String urban = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Urban").item(0)
		                                .getTextContent().trim();
		                        String share = ((org.w3c.dom.Element) countryNode).getElementsByTagName("Share").item(0)
		                                .getTextContent().trim();

		                        // Asignar los valores a los parámetros de la consulta SQL
		                        pstmt.setString(1, name);
		                        pstmt.setString(2, population);
		                        pstmt.setString(3, density);
		                        pstmt.setString(4, area);
		                        pstmt.setString(5, fertility);
		                        pstmt.setString(6, age);
		                        pstmt.setString(7, urban);
		                        pstmt.setString(8, share);

		                        // Ejecutar la inserción en la base de datos
		                        pstmt.executeUpdate();
		                    }
		                }
		            } catch (Exception e) {
		                // Manejar excepciones específicas para cada archivo XML
		                System.out.println(e);
		            }
		        }
		    }
		} catch (Exception e) {
		    // Manejar excepciones generales en el proceso
		    System.out.println(e);
		}

	}

	private void seleccionarEncabezado(Vista4 vista4, Vista7 vista7, List<String[]> datos) {
		vista4.ocultar();
		vista7.mostrar();
		List<String[]> encabezado = new ArrayList<>();
		actionListenerEnviar = new ActionListener() {
			List<String[]> newData = new ArrayList<>();

			public void actionPerformed(ActionEvent actionEvent) {
				List<Integer> columnasAEliminar = new ArrayList<>();

				if (!vista7.getChckbxNewCheckBox().isSelected())
					columnasAEliminar.add(0);
				if (!vista7.getChckbxNewCheckBox_1().isSelected())
					columnasAEliminar.add(1);
				if (!vista7.getChckbxNewCheckBox_2().isSelected())
					columnasAEliminar.add(2);
				if (!vista7.getChckbxNewCheckBox_3().isSelected())
					columnasAEliminar.add(3);
				if (!vista7.getChckbxNewCheckBox_4().isSelected())
					columnasAEliminar.add(4);
				if (!vista7.getChckbxNewCheckBox_5().isSelected())
					columnasAEliminar.add(5);
				if (!vista7.getChckbxNewCheckBox_6().isSelected())
					columnasAEliminar.add(6);
				if (!vista7.getChckbxNewCheckBox_7().isSelected())
					columnasAEliminar.add(7);

				// Procesar los datos utilizando Streams para eliminar columnas específicas
				newData = datos.stream().map(fila -> {
				    // Crear una nueva lista para almacenar la fila modificada
				    List<String> nuevaFila = new ArrayList<>();				    
				    // Recorrer cada elemento de la fila original
				    for (int i = 0; i < fila.length; i++) {
				        // Si el índice de la columna no está en la lista de columnas a eliminar
				        if (!columnasAEliminar.contains(i)) {
				            // Agregar el valor de la columna a la nueva fila
				            nuevaFila.add(fila[i]);
				        }
				    }				    
				    // Convertir la lista de nuevaFila a un array de String y devolverlo
				    return nuevaFila.toArray(new String[0]);				    
				// Recopilar todas las filas procesadas en una nueva lista
				}).collect(Collectors.toList());

				if (vista7.getChckbxNewCheckBox().isSelected())
					encabezado.add(new String[] { "Pais" });
				if (vista7.getChckbxNewCheckBox_1().isSelected())
					encabezado.add(new String[] { "Poblacion" });
				if (vista7.getChckbxNewCheckBox_2().isSelected())
					encabezado.add(new String[] { "Densidad" });
				if (vista7.getChckbxNewCheckBox_3().isSelected())
					encabezado.add(new String[] { "Area" });
				if (vista7.getChckbxNewCheckBox_4().isSelected())
					encabezado.add(new String[] { "Fertilidad" });
				if (vista7.getChckbxNewCheckBox_5().isSelected())
					encabezado.add(new String[] { "Año" });
				if (vista7.getChckbxNewCheckBox_6().isSelected())
					encabezado.add(new String[] { "Urbano" });
				if (vista7.getChckbxNewCheckBox_7().isSelected())
					encabezado.add(new String[] { "Compartido" });

				controlador.cargarDatosEnTabla(newData, encabezado, vista7);

				vista7.getChckbxNewCheckBox().setSelected(false);
				vista7.getChckbxNewCheckBox_1().setSelected(false);
				vista7.getChckbxNewCheckBox_2().setSelected(false);
				vista7.getChckbxNewCheckBox_3().setSelected(false);
				vista7.getChckbxNewCheckBox_4().setSelected(false);
				vista7.getChckbxNewCheckBox_5().setSelected(false);
				vista7.getChckbxNewCheckBox_6().setSelected(false);
				vista7.getChckbxNewCheckBox_7().setSelected(false);
			}
		};
		vista7.getBtnNewButton().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista7.ocultar();
				controlador.initEventHandlers();
			}
		};
		vista7.getBtnNewButton_1().addActionListener(actionListenerEnviar);
	}

	private void seleccionarEncabezadoAdmin(Vista8 vista8, Vista9 vista9, List<String[]> datos) {
		vista9.ocultar();
		vista8.mostrar();
		List<String[]> encabezado = new ArrayList<>();
		actionListenerEnviar = new ActionListener() {
			List<String[]> newData = new ArrayList<>();

			public void actionPerformed(ActionEvent actionEvent) {
				List<Integer> columnasAEliminar = new ArrayList<>();

				if (!vista8.getChckbxNewCheckBox().isSelected())
					columnasAEliminar.add(0);
				if (!vista8.getChckbxNewCheckBox_1().isSelected())
					columnasAEliminar.add(1);
				if (!vista8.getChckbxNewCheckBox_2().isSelected())
					columnasAEliminar.add(2);
				if (!vista8.getChckbxNewCheckBox_3().isSelected())
					columnasAEliminar.add(3);
				
				// Procesar los datos utilizando Streams para eliminar columnas específicas
				newData = datos.stream().map(fila -> {
				    // Crear una nueva lista para almacenar la fila modificada
				    List<String> nuevaFila = new ArrayList<>();				    
				    // Recorrer cada elemento de la fila original
				    for (int i = 0; i < fila.length; i++) {
				        // Si el índice de la columna no está en la lista de columnas a eliminar
				        if (!columnasAEliminar.contains(i)) {
				            // Agregar el valor de la columna a la nueva fila
				            nuevaFila.add(fila[i]);
				        }
				    }				    
				    // Convertir la lista de nuevaFila a un array de String y devolverlo
				    return nuevaFila.toArray(new String[0]);				    
				// Recopilar todas las filas procesadas en una nueva lista
				}).collect(Collectors.toList());

				if (vista8.getChckbxNewCheckBox().isSelected())
					encabezado.add(new String[] { "Id" });
				if (vista8.getChckbxNewCheckBox_1().isSelected())
					encabezado.add(new String[] { "User" });
				if (vista8.getChckbxNewCheckBox_2().isSelected())
					encabezado.add(new String[] { "Password" });
				if (vista8.getChckbxNewCheckBox_3().isSelected())
					encabezado.add(new String[] { "Type" });
				
				controlador.cargarDatosEnTablaAdmin(newData, encabezado, vista8);

				vista8.getChckbxNewCheckBox().setSelected(false);
				vista8.getChckbxNewCheckBox_1().setSelected(false);
				vista8.getChckbxNewCheckBox_2().setSelected(false);
				vista8.getChckbxNewCheckBox_3().setSelected(false);
			}
		};
		vista8.getBtnNewButton().addActionListener(actionListenerEnviar);

		actionListenerEnviar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				vista8.ocultar();
				controlador.initEventHandlers();
			}
		};
		vista8.getBtnNewButton_1().addActionListener(actionListenerEnviar);
	}

	public static void escribirCSV(String archivo, List<String[]> nombreColumna, List<String[]> datos) {
		try {
		    // Crear un objeto File para el archivo CSV especificado
		    File archivoCSV = new File("data/" + archivo);

		    // Obtener el directorio padre del archivo y crearlo si no existe
		    File directorio = archivoCSV.getParentFile();
		    if (directorio != null && !directorio.exists()) {
		        directorio.mkdirs(); // Crear el directorio y sus subdirectorios si es necesario
		    }

		    // Crear un FileWriter para escribir en el archivo CSV
		    FileWriter writer = new FileWriter(archivoCSV);

		    // Si el archivo está vacío (longitud 0), escribir los nombres de las columnas como encabezado
		    if (archivoCSV.length() == 0) {
		        StringBuilder sb = new StringBuilder();

		        // Construir el encabezado concatenando los nombres de las columnas, separados por ";"
		        for (String[] fila : nombreColumna) {
		            sb.append(String.join(";", fila) + ";"); // Agregar un ";" después de cada columna
		        }

		        // Convertir el StringBuilder a una cadena y eliminar el último ";"
		        String sbString = sb.toString();
		        String resultado = sbString.substring(0, sbString.length() - 1);

		        // Escribir el encabezado en el archivo
		        writer.append(resultado);
		        writer.append("\n"); // Añadir un salto de línea
		    }

		    // Escribir los datos fila por fila en el archivo CSV
		    for (String[] fila : datos) {
		        writer.append(String.join(";", fila)); // Concatenar los valores de cada fila con ";"
		        writer.append("\n"); // Añadir un salto de línea después de cada fila
		    }

		    // Asegurarse de que todo el contenido se escribe en el archivo y cerrar el writer
		    writer.flush();
		    writer.close();

		} catch (IOException e) {
		    // Manejo de excepciones: imprimir el error en caso de que ocurra un problema al escribir el archivo
		    System.out.println(e);
		}
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void clearActionListeners(List<JButton> buttons) {
		for (JButton button : buttons) {
			for (ActionListener listener : button.getActionListeners()) {
				button.removeActionListener(listener);
			}
		}
	}
}
