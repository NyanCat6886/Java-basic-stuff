package test;
import java.util.*;
public class EvaluaEdad {
public static void main(String[] args) {
	Scanner teclado = new Scanner(System.in);
	
	System.out.println("Introduce tu edad: ");
	
	int edad= teclado.nextInt();
	
	/*if (edad>=18) {
		System.out.println("Muy bien, campeón");
		
	}else {
		System.out.println("Te me cuidas");
	}*/
	
	if (edad<18) {
		System.out.println("Eres un pringao");
	} else if(edad<40) {
		System.out.println("Eres un jovenzuelo");
	} else if(edad<65) {
		System.out.println("Eres nicolás maduro");
		
	}else {
		System.out.println("Hasta la próxima");
	}
	
}
}
