package xml_mysql;

public class AE02 {

	public static void main(String[] args) {
		Vista vista = new Vista();
		Vista2 vista2 = new Vista2();
		Vista3 vista3 = new Vista3();
		Vista4 vista4 = new Vista4();
		Vista5 vista5 = new Vista5();
		Vista6 vista6 = new Vista6();
		Vista7 vista7 = new Vista7();
		Vista8 vista8 = new Vista8();
		Vista9 vista9 = new Vista9();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(vista, vista2, vista3, vista4, vista5, vista6, vista7, vista8, vista9, modelo);
	}
}
