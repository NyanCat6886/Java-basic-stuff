package es.upm.dit.prog.practica1;

import java.util.Objects;

public class Rover {
 private String id;
 private Vector pos;
 private long t;
 private Vector vel;
 private double power;
 
 public static final double MAX_VEL = 0.05;
 public static final double MAX_BATTERY = 200;
 public static final double BATTERY_PER_METER =0.1;
 public static final double SAFE_BATTERY_LEVEL = MAX_BATTERY*0.3;
 private static final long SAFETY_DISTANCE =2;
 
 public Rover(String id, Vector pos, long t, Vector vel, double power) {
	 this.id=id;
	 this.pos=pos;
	 this.t=t;
	 this.vel=vel;
	 this.power=power;
 }
 public String getId() {
	 return id;
 }
 public Vector getPos() {
	 return pos;
 }
 public long getT() {
	return t;
 }
 public Vector getVel() {
	 return vel;
 }
 public double getPower() {
	 return power;
 }
 
 public void setId(String id) {
	 this.id=id;
 }
 
 public void setPos(Vector pos) {
	 this.pos=pos;
 }
 public void setT(long t) {
	 this.t=t;
 }
 public void setVel(Vector vel) {
	 this.vel=vel;
 }
 public void setPower(double power) {
	 this.power=power;
 }


@Override
public int hashCode() {
	return Objects.hash(id);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Rover other = (Rover) obj;
	return Objects.equals(id, other.id);
}
@Override
public String toString() {
	return "Rover [id=" + id + ", pos=" + pos + ", t=" + t + ", vel=" + vel + ", power=" + power + "]";
}
 public double powerEstimation(Vector p1, Vector p2) {
	 return p1.distancia(p2)*BATTERY_PER_METER;
 }
 public boolean isActivo() {
	 return this.power > SAFE_BATTERY_LEVEL;
 }
 public void mover(long t) {
	 if (t<=this.t) {
		 return;
	 }
	long timeDif =t-this.t;
	double seconds = timeDif;
	
	if (isActivo() && vel.modulo()>0) {
		double dist_max = MAX_VEL*seconds;
		double time_max = vel.modulo()*seconds;
		double dis_bat_max= (power-SAFE_BATTERY_LEVEL)/BATTERY_PER_METER;
		double distRec = Math.min(dist_max, (Math.min(time_max, dis_bat_max)));
		if (distRec > 0) {
	
		
		Vector desplazamiento = new Vector (0,0);
		desplazamiento.setPolars(distRec, vel.arg());
		pos.suma(desplazamiento);
		
		power -= distRec * BATTERY_PER_METER;
		power = Math.max(0, power);
		
		if (power<SAFE_BATTERY_LEVEL) {
			vel.setX(0);
			vel.setY(0);
		} else { 
			vel.setX(0);
			vel.setY(0);
		}
	}}
	this.t=t;

 }
 public boolean peligro (Rover otro) {
	 if (otro == null || this.equals(otro)) return false;
	 double distancia =this.pos.distancia(otro.pos);
	 return distancia < SAFETY_DISTANCE;
 }
}
