package test;
import java.util.*;
import javax.swing.*;
public class AccesoApp {
	public static void main(String[] args) {
		String clave = "1234";
		
		String pass = "";
		
		while (clave.equals(pass)==false) {
			pass = JOptionPane.showInputDialog("Introduce la contraseña: ");
			
			if (clave.equals(pass)== false) {
				
				System.out.println("Contraseña incorrecta.");
			} else {
				System.out.println("Correcto :)");
				System.out.print("    ________o8A888888o_\r\n"
						+ "_o888888888888K_]888888o\r\n"
						+ "          ~~~+8888888888o\r\n"
						+ "              ~8888888888 \r\n"
						+ "              o88888888888\r\n"
						+ "             o8888888888888\r\n"
						+ "           _8888888888888888\r\n"
						+ "          o888888888888888888_\r\n"
						+ "         o88888888888888888888_\r\n"
						+ "        _8888888888888888888888_\r\n"
						+ "        888888888888888888888888_\r\n"
						+ "        8888888888888888888888888\r\n"
						+ "        88888888888888888888888888\r\n"
						+ "        88888888888888888888888888\r\n"
						+ "        888888888888888888888888888\r\n"
						+ "        ~88888888888888888888888888_\r\n"
						+ "         (88888888888888888888888888\r\n"
						+ "          888888888888888888888888888\r\n"
						+ "           888888888888888888888888888_\r\n"
						+ "           ~8888888888888888888888888888\r\n"
						+ "             +88888888888888888888~~~~~\r\n"
						+ "              ~=888888888888888888o\r\n"
						+ "       _=oooooooo888888888888888888\r\n"
						+ "        _o88=8888==~88888888===8888_\r\n"
						+ "        ~   =~~ _o88888888=      ~~~\r\n"
						+ "                ~ o8=~88=~\r\n"
						+ "                  ~    ~");
			}
		}
	}
}
