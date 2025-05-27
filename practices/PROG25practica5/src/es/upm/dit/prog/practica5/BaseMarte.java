package es.upm.dit.prog.practica5;

import java.util.ArrayList;
import java.util.List;

public class BaseMarte {
    private List<Rover> rovers;
    private List<Mision> misiones;

    public BaseMarte() {
        this.rovers = new ArrayList<>();
        this.misiones = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "BaseMarte [rovers=" + rovers + ", misiones=" + misiones + "]";
    }

    public void addRover(Rover d) {
        if (d != null) {
            this.rovers.add(d);
        }
    }
    public void addMision(Mision m) {
        if (m != null) {
            this.misiones.add(m);
        }
    }
    public List<Rover> getRovers() {
        return new ArrayList<>(this.rovers);
    }
    public List<Mision> getMisiones(SelectorMision sm) {
        if (sm == null) {
            sm = new SelectorMisionTrue();
        }

        List<Mision> resultado = new ArrayList<>();
        for (Mision m : this.misiones) {
            if (m != null && sm.seleccionar(m)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    public void update(long t) {
        for (Rover r : rovers) {
            r.mover(t);
        }
        for (Mision m : misiones) {
            m.update(t);
        }
    }
}

