package test;
import java.util.*;
import java.math.*;
public class Coche {
	
	
	//atributos
private int alto;

private double peso;

private int peso_plataforma;

private int peso_total;

private int ancho;

private String color;

private int ruedas;

private double pesoBase;

private boolean navegador, asientos_cuero;

private double precioBase;

private double precioFinal;

private int largo;

private int motor;


//método constructor
public Coche() {
	ruedas =4;
	color = "GREY";
	peso_plataforma = 500;
	motor = 1600;
	ancho = 300;
	largo =2000;
	
}

//getters y setters 

public double getPesoBase() {
	return pesoBase;
}
public void setPesoBase(double pesoBase) {
	this.pesoBase = pesoBase;
}


public String isNavegador() {
	if (navegador ==true) {
		return "El coche tiene navegador";
	}else {
		return "El coche tiene radio";
	}
}


public void setNavegador(String navegador) {
	if(navegador.equalsIgnoreCase("sí")){
	this.navegador=true;	
	}else {
			this.navegador=false;
	}
	
}
public boolean isAsientosCuero() {
	return asientos_cuero;
}
public void setAsientosCuero(boolean asientos_cuero) {
	this.asientos_cuero = asientos_cuero;
}
public double getPrecioBase() {
	return precioBase;
}
public void setPrecioBase(double precioBase) {
	this.precioBase = precioBase;
}
public double getPrecioFinal() {
	return precioFinal;
}
public void setPrecioFinal(double precioFinal) {
	this.precioFinal = precioFinal;
}


public double getAlto() {
	return alto;
}
public void setAlto(int alto) {
	this.alto = alto;
}
public double getPeso() {
	return peso;
}
public void setPeso(int peso) {
	this.peso = peso;
}
public double getAncho() {
	return ancho;
}
public void setAncho(int ancho) {
	this.ancho = ancho;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}

public int getRuedas() {
	return ruedas;
}
public void setRuedas(int r) {
if (r<3 || r > 4) {
	System.out.println("Pues no puede ser.");
}else {
	ruedas= r;
}
}


public String dimePeso() { //setter y getter simultáneo
	int pesoCarroceria =500;
	peso_total=peso_plataforma+pesoCarroceria;
	if (asientos_cuero ==true) {
		peso_total=peso_total+50;
	}
	if (navegador ==true) {
		peso_total = peso_total+20;
	}
	return "El peso del coche es: " + peso_total;
	
}

public int precioCoche() {//getter
	int precioFinal = 10000;
	
	if (asientos_cuero == true) {
		precioFinal+=2000;
	}
	
	if (navegador==true) {
		
		precioFinal+=1500;
	}
	return precioFinal;
}


public String dime_datos_generales() {//getter
	return "La plataforrma del vehículo tiene "+ ruedas+ " ruedas."+
	"Mide: "
	+ largo/1000+ " metros, con un ancho de: "+ ancho+ 
	" cm, y un peso de plataforma de: "+ peso_plataforma+ " kg.";
}

public void establece_color(String color_coche) {//setter
	color =color_coche;
}

public String dime_color() {
	return "El color del coche es: "+color;
}

public void configura_asientos(String asientos_cuero) { //setter
	
if (asientos_cuero.equals("si") || asientos_cuero.equalsIgnoreCase("sí")) {
	this.asientos_cuero = true;
}else {
	this.asientos_cuero = false;
}
}



public void configura_navegador(String navegador) { //setter
	
if (navegador.equals("sí") || navegador.equalsIgnoreCase("sí") ) {
	this.navegador = true;
}else {
	this.navegador = false;
}
}


public String dime_navegador() {//getter
	if (navegador == true) {
		return "El coche tiene navegador";
	}
	return "El coche no tiene navegador, tiene radio";
	
}

public String dime_asientos() {//getter
	if (asientos_cuero == true) {
		return "El coche tiene asientos de cuero";
	}
	return "El coche trae asientos de serie";
}




//otros métodos

void arrancar() {
	
}
public void girar() {

}


}
