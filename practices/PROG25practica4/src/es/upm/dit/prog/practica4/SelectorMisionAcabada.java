package es.upm.dit.prog.practica4;
public class SelectorMisionAcabada implements SelectorMision {
    @Override
	 public boolean seleccionar(Mision m) {
        return m != null && m.acabado();
    }
}