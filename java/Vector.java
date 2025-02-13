package es.upm.dit.prog.practica1;

import java.util.Objects;

public class Vector {
private double x;
private double y;
public Vector (double x,double y){
	this.x=x;
	this.y=y;
}
public double getX() {
	return x;
}
public double getY() {
	return y;
}
public void setX(double x){
	this.x=x;
}
public void setY(double y) {
	this.y=y;
}

@Override
public int hashCode() {
	return Objects.hash(x, y);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Vector other = (Vector) obj;
	return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
			&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
}

@Override
public String toString() {
	return "Vector [x=" + x + ", y=" + y + "]";
}
public double modulo() {
	return Math.sqrt((Math.pow(x, 2))+Math.pow(y, 2));

}
public double arg() {
	return Math.atan2(y, x);
}
public double distancia(Vector pos) {
	return Math.sqrt((Math.pow(this.x-pos.x, 2)+(Math.pow(this.y-pos.y, 2))));
}
public void setPolars(double m, double a) {
	if (m<0) {
		
	}
	this.x=m*Math.cos(a);
	this.y=m*Math.sin(a);
}
public void suma(Vector v) {
	this.x += v.x;
	this.y += v.y;
	
}
}


