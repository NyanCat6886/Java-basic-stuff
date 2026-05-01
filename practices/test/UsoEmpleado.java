package test;
import java.util.*;
public class UsoEmpleado {

	public static void main(String[] args) {

		/*
		Empleado empleado1 = new Empleado("Gerardo", 45000,2017,10,25 );
		Empleado empleado2 = new Empleado ("Rick", 50000,2023,6,12);
		Empleado empleado3 = new Empleado ("Gema", 80000, 2006, 04,29);
		
		empleado1.subeSueldo(20);
		empleado2.subeSueldo(30);
		empleado3.subeSueldo(30);
		
		System.out.println("Nombre: "+ empleado1.dameNombre()+ 
				", Sueldo: "+ empleado1.dameSueldo()+ " euros, Fecha de alta: "+empleado1.dameFechaContrato());
		System.out.println("Nombre: "+ empleado2.dameNombre()+ 
				", Sueldo: "+ empleado2.dameSueldo()+ " euros, Fecha de alta: "+empleado2.dameFechaContrato());
		System.out.println("Nombre: "+ empleado3.dameNombre()+ 
				", Sueldo: "+ empleado3.dameSueldo()+ " euros, Fecha de alta: "+empleado3.dameFechaContrato());
		*/
		
		
		//con arrays;
		
		Empleado [] misEmpleados = new Empleado[3];
		misEmpleados[0]= new Empleado("Gerardo",45000,2017,10,25);
		misEmpleados[1] = new Empleado ("Rick", 50000,2023,6,12 );
		misEmpleados[2] = new Empleado ("Gema", 80000, 2006, 04,29 );
		
		for (int i=0; i<3; i++) {
			misEmpleados[0].subeSueldo(20);
		}
		for (int i=0; i<3; i++) {
			misEmpleados[1].subeSueldo(30);
		}
		for (int i=0; i<3; i++) {
			misEmpleados[2].subeSueldo(30);
		}
		
		
		for (int i=0; i<3; i++) {
			System.out.println("Nombre: "+misEmpleados[i].dameNombre()
					+ ", Sueldo: "+misEmpleados[i].dameSueldo()+ " EUR , Fecha de alta: "+ 
					misEmpleados[i].dameFechaContrato());
		}
	}

}

class Empleado{

	public Empleado(String nom, double sueldo, int anyo, int mes, int dia) {
		nombre =nom;
		this.sueldo=sueldo;
		GregorianCalendar calendario = new GregorianCalendar(anyo, mes-1,dia);
		altaContrato = calendario.getTime();
	}
	//getters y setters
	
	public String dameNombre() {//getter
		return nombre;
	}
	
	public double dameSueldo() {//getter
		return sueldo;
	}
	
	public Date dameFechaContrato() {//getter
		return altaContrato;
	}
	
	public void subeSueldo(double porcentaje) {//setter
		double aumento = sueldo*(porcentaje/100);
		sueldo += aumento;
	}
	
	
	//atribs
	private String nombre;
	private double sueldo;
	private Date altaContrato;
	
}
