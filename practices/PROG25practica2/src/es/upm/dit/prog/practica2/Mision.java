package es.upm.dit.prog.practica2;

import java.util.Arrays;
import java.util.Objects;

public class Mision {
    private String id;
    private Rover rover;
    private Vector[] puntos;
    private int nPuntos;
    private int n;
    private boolean abort;
    

    public Mision(String id, Rover rover, int maxPuntos) {
        this.id = id;
        this.rover = rover;
        this.puntos = new Vector[maxPuntos];
        this.nPuntos = 0;
        this.n = 0;
        this.abort = false;
    }

    public String getId() {
        return id;
    }

    public Rover getRover() {
        return rover;
    }

    public Vector[] getPuntos() {
        return puntos;
    }

    public int getnPuntos() {
        return nPuntos;
    }

    public int getN() {
        return n;
    }

    public boolean isAbort() {
        return abort;
    }

    public void setAbort(boolean abort) {
        this.abort = abort;
    }

    public void addPunto(Vector pos) {
        if (pos != null && nPuntos < puntos.length) {
            puntos[nPuntos] = pos;
            nPuntos++;
        }
    }

    public boolean acabado() {
        return abort || rover == null || !rover.isActivo() || n >= nPuntos || (nPuntos > 0 && puntos[n] == null);
    }

    private static final double MIN_DISTANCE = 0.1;
    
    public void update(long t) {
        if (rover.getT()!=t) {
        	return;
        }

       if (acabado()) {
    	   return;
        }
       if (n<nPuntos && puntos[n] != null && rover.getPos().distancia(puntos[n])<= MIN_DISTANCE) {
    	   n++;
       }

       if (n==nPuntos) {
    	   rover.setVel(new Vector (0,0));
    	   return;
       }
       if (rover.getPower()<=0) { 
    	    rover.setVel(new Vector(0, 0));
    	    setAbort(true);
    	    return;
    	}
       double prox= rover.getPos().distancia(puntos[n]);
	    double level= prox*Rover.BATTERY_PER_METER;
	    if ((rover.getPower() - level) < Rover.SAFE_BATTERY_LEVEL) {
	        rover.setVel(new Vector(0, 0));
	        setAbort(true);
	        return;
	    }
    
      if (n<nPuntos) {
    	  Vector dest = puntos[n];
    	  Vector actual = rover.getPos();
    	  double angulo = Math.atan2(dest.getY()-actual.getY(),dest.getX()-actual.getX() );
    	  Vector velocidad = new Vector (0,0);
    	  velocidad.setPolars(Rover.MAX_VEL, angulo);
    	  rover.setVel(velocidad);
      }
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mision mision = (Mision) o;
        return Objects.equals(id, mision.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mision{" +
                "id='" + id + '\'' +
                ", rover=" + rover +
                ", puntos=" + Arrays.toString(puntos) +
                ", nPuntos=" + nPuntos +
                ", n=" + n +
                ", abort=" + abort +
                '}';
    }
}

