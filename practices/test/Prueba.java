package test;

public class Prueba {
public static void main(String[] args) {
	
	Clase obj = new Clase();
	int num =5;
	obj.duplicar(num);
	System.out.println("Valor de num: "+ num);
	
}} 

class Clase{
	void duplicar(int x) {
		x = x*2;
		System.out.println("Valor de x: "+x);
	}
}
	

