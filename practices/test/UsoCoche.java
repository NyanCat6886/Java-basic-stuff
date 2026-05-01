package test;
import javax.swing.*;
import java.util.*;

public class UsoCoche {
public static void main(String[] args) {
	
	Coche miCoche = new Coche();
	
	miCoche.setColor(JOptionPane.showInputDialog("Mete color: "));
	
	System.out.println(miCoche.dime_datos_generales());
	
	System.out.println(miCoche.dime_color());
	
	miCoche.configura_asientos(JOptionPane.showInputDialog("Mete asientos: "));
	
	System.out.println(miCoche.dime_asientos());
	
	miCoche.configura_navegador(JOptionPane.showInputDialog("¿Tiene navegador?"));
	
	System.out.println(miCoche.dime_navegador());
	
	System.out.println(miCoche.dimePeso());
	
	System.out.println("El precio final del coche es: "+ miCoche.precioCoche());
	
	
	/*
	
	//coches creados:
	Coche coche1 = new Coche();
	Coche coche2 = new Coche();
	
	//propiedades y estados del objeto coche:
	coche1.getAlto();
	coche2.setRuedas(3);
	coche1.arrancar();
	coche2.girar();
	coche1.setColor("GREEN");
	
	//impresión de datos por consola:
	System.out.println("El coche 2 tiene: "+ coche2.getRuedas()+ " ruedas.");
	System.out.println("El coche 1 tiene: "+ coche1.getRuedas()+ " ruedas.");
	System.out.println("El coche 2 tiene color: "+coche2.getColor());
	System.out.println("El coche 1 tiene color: "+coche1.getColor());

	//prueba (NO USAR)
	/*Scanner teclado = new Scanner (System.in);
System.out.println("Hola");
String entrada = teclado.next();

if (entrada.equals("hola")) {
    System.out.println("hola, guapo");
} else {
    System.out.println("pa tu casa");
}

*/
}
}
