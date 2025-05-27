package es.upm.dit.prog.practica4;

public class SelectorMisionRoverEnPeligro implements SelectorMision {
    private Rover[] peligrosos;

    public SelectorMisionRoverEnPeligro(Rover[] peligrosos) {
        this.peligrosos = peligrosos;
    }
    
    @Override
    public boolean seleccionar(Mision m) {
        if (m == null) return false;
        Rover rover = m.getRover();
        for (Rover otro : peligrosos) {
            if (otro != null && !rover.equals(otro) &&
                rover.getPos().distancia(otro.getPos()) < Rover.SAFETY_DISTANCE) {
                return true;
            }
        }
        return false;
    }
}
