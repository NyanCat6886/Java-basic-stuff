package es.upm.dit.prog.practica5;

public class SelectorMisionRoverAlejado implements SelectorMision {
    private Vector p;
    private double d;

    public SelectorMisionRoverAlejado(Vector p, double d) {
        this.p = p;
        this.d = d;
    }

    @Override
    public boolean seleccionar(Mision m) {
        return m != null && m.getRover().getPos().distancia(p) > d;
    }
}
