package es.upm.dit.prog.practica5;

public class SelectorMisionAbortada implements SelectorMision {

@Override
	public boolean seleccionar(Mision m) {
		 return m != null && m.isAbort();
	}

}
