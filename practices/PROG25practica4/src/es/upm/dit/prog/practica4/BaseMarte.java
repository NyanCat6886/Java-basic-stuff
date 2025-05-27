package es.upm.dit.prog.practica4;

import java.util.Arrays;

public class BaseMarte {
    public static final int N = 10; 
    private Rover[] rovers;         
    private Mision[] misiones;      

    
    public BaseMarte() {
        this.rovers = new Rover[N]; 
        this.misiones = new Mision[N]; 
    }

        @Override
    public String toString() {
        return "BaseMarte [rovers=" + Arrays.toString(rovers) + ", misiones=" + Arrays.toString(misiones) + "]";
    }

    
    public void addRover(Rover r) {
        if (r == null) {
        	return;
        }
        for (int i = 0; i < rovers.length; i++) {
            if (rovers[i] == null) {
                rovers[i] = r; 
                return; 
            }
        }
        for (int i = 0; i < rovers.length - 1; i++) {
            rovers[i] = rovers[i + 1];
        }
        rovers[rovers.length - 1] = r; 
    }

    public Rover[] getRovers() {
        int contador = 0;
        for (Rover rover : rovers) {
            if (rover != null) {
                contador++;
            }
        }

        Rover[] result = new Rover[contador];
        int n = 0;
        for (Rover rover : rovers) {
            if (rover != null) {
                result[n++] = rover;
            }
        }

        return result;
    }
    public void addMision(Mision m) {
        if (m == null) return; 
        for (int i = 0; i < misiones.length; i++) {
            if (misiones[i] == null) {
                misiones[i] = m; 
                return; 
            }
        }

        for (int i = 0; i < misiones.length - 1; i++) {
            misiones[i] = misiones[i + 1]; 
        }
        misiones[misiones.length - 1] = m;
    }
    public Mision[] getMisiones(SelectorMision sm) {
        if (sm == null) {
            sm = new SelectorMisionTrue();
        }

        int count = 0;
        for (Mision m : this.misiones) {
            if (m != null && sm.seleccionar(m)) {
                count++;
            }
        }

        Mision[] resultado = new Mision[count];
        int index = 0;
        for (Mision m : this.misiones) {
            if (m != null && sm.seleccionar(m)) {
                resultado[index++] = m;
            }
        }

        return resultado;
    }

    public void update(long t) {
        for (Rover rover : rovers) {
            if (rover != null) {
                rover.mover(t); 
            }
        }
        for (Mision mision : misiones) {
            if (mision != null) {
                mision.update(t); 
            }
        }
    }
}
