package test;
import java.util.*;
import javax.swing.*;
public class AdivinaNumero {
	public static void main(String[] args) {

		int aleatorio = (int) (Math.random()*100);	
		
		Scanner teclado = new Scanner(System.in);
		
		int numero =0;
		int intentos =0;
		
		do {
			intentos++;
			System.out.println("Introduce un número");
			
			numero = teclado.nextInt();	
			
			if (aleatorio<numero) {
				System.out.println("más bajo");
			} else if (aleatorio>numero) {
				System.out.println("Más alto");
			}
			
		
		} while(numero != aleatorio);
		System.out.println("Correcto, lo has conseguido en: "+ intentos+" intentos.");
		
		
	}

}
