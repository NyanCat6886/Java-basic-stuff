package es.upm.dit.prog.practica5;
import static org.junit.Assert.*;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public final class Prueba5 {

	//private double delta = 0.1;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// testBaseMarte1: ctor 
	@Test (timeout=500)
	public void testBaseMarte1(){
		BaseMarte bm = new BaseMarte();
		String s = bm.toString();
		assertTrue(s != null);
	}	

	// SelectorMisionTrue
	@Test (timeout=500)
	public void testSelectores1(){
		Vector p = new Vector(0,0);
		Vector v = new Vector(0,0);
		Rover d = new Rover ("d1", p, 0, v, Rover.MAX_BATTERY);
		Mision m = new Mision("m1", d, 1);
		SelectorMision sm = new SelectorMisionTrue();
		assertTrue("SelectorMisionTrue", sm.seleccionar(m));
		assertFalse("SelectorMisionTrue", sm.seleccionar(null));
	}	

	// SelectorMisionAcabada
	@Test (timeout=500)
	public void testSelectores2(){
		Vector p = new Vector(0,0);
		Vector v = new Vector(0,0);
		Rover d = new Rover ("d1", p, 0, v, Rover.MAX_BATTERY);
		Mision m = new Mision("m1", d, 2);
		m.addPunto(p);
		//m.addPunto(p);
		BaseMarte cc = new BaseMarte();
		cc.addRover(d);
		cc.addMision(m);
		SelectorMision sm = new SelectorMisionAcabada();
		assertFalse("SelectorMisionAcabada", sm.seleccionar(null));
		assertFalse("SelectorMisionAcabada", sm.seleccionar(m));
		cc.update(0);
		assertTrue("SelectorMisionAcabada", sm.seleccionar(m));
		cc.update(10);
		assertTrue("SelectorMisionAcabada", sm.seleccionar(m));
	}	
	
	// SelectorMisionRoverAlejado
	@Test (timeout=500)
	public void testSelectores5(){
		Vector p = new Vector(0,0);
		Vector v = new Vector(0,0);
		Rover d = new Rover ("d1", p, 0, v, Rover.MAX_BATTERY);
		Mision m = new Mision("m1", d, 1);
		SelectorMision sm = new SelectorMisionTrue(); //RoverAlejado(new Vector(10,0,0), 1);
		assertFalse("SelectorMisionRoverAlejado", sm.seleccionar(null));
		assertTrue("SelectorMisionRoverAlejado", sm.seleccionar(m));
		sm = new SelectorMisionRoverAlejado(new Vector(0,0), 1);
		assertFalse("SelectorMisionRoverAlejado", sm.seleccionar(m));
	}	

	// SelectorMisionEnPeligro
	@Test (timeout=500)
	public void testSelectores6(){
		Vector p1 = new Vector(0,0);
		Vector v1 = new Vector(0,0);
		Rover d1 = new Rover ("d1", p1, 0, v1, Rover.MAX_BATTERY);
		Mision m1 = new Mision("m1", d1, 3);
		BaseMarte bm = new BaseMarte();
		bm.addRover(d1);
		bm.addMision(m1);
		
		Vector p2 = new Vector(0,0);
		Vector v2 = new Vector(0,0);
		Rover d2 = new Rover ("d2", p2, 0, v2, Rover.MAX_BATTERY);
		Mision m2 = new Mision("m2", d2, 3);
		bm.addRover(d2);
		bm.addMision(m2);
		
		Vector p3 = new Vector(10,10);
		Rover d3 = new Rover ("d1", p2, 0, v2, Rover.MAX_BATTERY);
		Mision m3 = new Mision("m3", d3, 3);
		bm.addRover(d3);
		bm.addMision(m3);
		
		Rover d4 = new Rover("d4", p3, 0, v2, Rover.MAX_BATTERY);
		Mision m4 = new Mision("m3", d4, 3);
		bm.addRover(d4);
		bm.addMision(m4);
		
		Rover[] rovers = new Rover[bm.getRovers().size()];
		int i = 0;
		for (Rover r: bm.getRovers()) {
			rovers[i] = r;
			i++;
		}
			
		//Rover[] rovers = (Rover[]) bm.getRovers().toArray();
		SelectorMision sm = new SelectorMisionRoverEnPeligro(rovers);
		Mision[] misionesEnPeligro = new Mision[] {m1, m2, m3};
		assertArrayEquals("SelectorMisionRoverEnPeligro", misionesEnPeligro, bm.getMisiones(sm).toArray());
	}	

	// getMisiones
	@Test (timeout=500)
	public void testSelectores7(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover d = new Rover("d0", pos, 0, vel, Rover.MAX_BATTERY);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 5);
		BaseMarte bm = new BaseMarte();
		bm.addMision(misiones0[0]);
		Mision[] misiones2 = new Mision[] {misiones0[0], misiones0[1], misiones0[2]};
		bm.addMision(misiones0[1]);
		bm.addMision(misiones0[2]);
		assertArrayEquals("BaseMarte.addMision con misiones a medias", misiones2, bm.getMisiones(new SelectorMisionTrue()).toArray());
	}	

	// 
	@Test (timeout=500)
	public void testListaGrande(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover d = new Rover("d0", pos, 0, vel, Rover.MAX_BATTERY);
		Mision[] misiones0 = new Mision[1000];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 5);
		BaseMarte bm = new BaseMarte();
		for (int i = 0; i < misiones0.length; i++)
			bm.addMision(misiones0[i]);
		assertArrayEquals("BaseMarte.addMision con misiones a medias", misiones0, bm.getMisiones(new SelectorMisionTrue()).toArray());
	}	

	
	
	
	
	
	@Test (timeout=500)
	public void checkDependencies() {
		try {
			Vector.class.getClassLoader().loadClass(Vector.class.getCanonicalName());
			Vector.class.getConstructor(Double.TYPE,Double.TYPE);
			Vector.class.getMethod("getX");
			Vector.class.getMethod("getY");
			Vector.class.getMethod("setX", Double.TYPE);
			Vector.class.getMethod("setY", Double.TYPE);
			Vector.class.getMethod("equals", Object.class);
			Vector.class.getMethod("toString");
			Vector.class.getMethod("modulo");
			Vector.class.getMethod("arg");
			Vector.class.getMethod("setPolars", Double.TYPE, Double.TYPE);
			Vector.class.getMethod("distancia", Vector.class);
			Vector.class.getMethod("suma", Vector.class);

			Rover.class.getClassLoader().loadClass(Rover.class.getCanonicalName());
			Rover.class.getConstructor(String.class,Vector.class,Long.TYPE,Vector.class, Double.TYPE);
			Rover.class.getMethod("getId");
			Rover.class.getMethod("getPos");
			Rover.class.getMethod("getT");
			Rover.class.getMethod("getVel");
			Rover.class.getMethod("getPower");
			Rover.class.getMethod("setId", String.class);
			Rover.class.getMethod("setPos",Vector.class);
			Rover.class.getMethod("setT", Long.TYPE);
			Rover.class.getMethod("setVel",Vector.class);
			Rover.class.getMethod("setVel",Vector.class);
			Rover.class.getMethod("equals", Object.class);
			Rover.class.getMethod("toString");
			Rover.class.getMethod("mover", Long.TYPE);
			Rover.class.getMethod("peligro", Rover.class);
			Rover.class.getMethod("isActivo");
			Rover.class.getMethod("powerEstimation",Vector.class,Vector.class);
			
			Mision.class.getClassLoader().loadClass(Mision.class.getCanonicalName());
			Mision.class.getConstructor(String.class, Rover.class, Integer.TYPE);
			Mision.class.getMethod("getId");
			Mision.class.getMethod("getRover");
			Mision.class.getMethod("getPuntos");
			Mision.class.getMethod("getN"); 
			Mision.class.getMethod("isAbort");
			Mision.class.getMethod("setAbort",Boolean.TYPE); 
			Mision.class.getMethod("equals", Object.class);
			Mision.class.getMethod("toString");
			Mision.class.getMethod("addPunto", Vector.class);
			Mision.class.getMethod("update", Long.TYPE);
			Mision.class.getMethod("acabado");
			
			BaseMarte.class.getClassLoader().loadClass(BaseMarte.class.getCanonicalName());
			BaseMarte.class.getConstructor();
			BaseMarte.class.getMethod("toString");
			BaseMarte.class.getMethod("addRover", Rover.class);
			BaseMarte.class.getMethod("addMision", Mision.class);
			BaseMarte.class.getMethod("getRovers");
			BaseMarte.class.getMethod("getMisiones", SelectorMision.class);
			BaseMarte.class.getMethod("update", Long.TYPE);
			
			SelectorMision.class.getClassLoader().loadClass(SelectorMision.class.getCanonicalName());
			SelectorMisionTrue.class.getClassLoader().loadClass(SelectorMisionTrue.class.getCanonicalName());
			SelectorMisionTrue.class.getMethod("seleccionar",Mision.class);
			SelectorMisionAcabada.class.getClassLoader().loadClass(SelectorMisionAcabada.class.getCanonicalName());
			SelectorMisionAcabada.class.getMethod("seleccionar",Mision.class);
			SelectorMisionAcabada.class.getClassLoader().loadClass(SelectorMisionAbortada.class.getCanonicalName());
			SelectorMisionAcabada.class.getMethod("seleccionar",Mision.class);
			SelectorMisionRoverAlejado.class.getClassLoader().loadClass(SelectorMisionRoverAlejado.class.getCanonicalName());
			SelectorMisionRoverAlejado.class.getConstructor(Vector.class,Double.TYPE);
			SelectorMisionRoverAlejado.class.getMethod("seleccionar",Mision.class);
			SelectorMisionRoverEnPeligro.class.getClassLoader().loadClass(SelectorMisionRoverEnPeligro.class.getCanonicalName());
			// constructor Rover[]
			SelectorMisionRoverEnPeligro.class.getMethod("seleccionar",Mision.class);
/*			
			SelectorMisionRoverAterrizando.class.getClassLoader().loadClass(SelectorMisionRoverAterrizando.class.getCanonicalName());
			SelectorMisionRoverAterrizando.class.getMethod("seleccionar",Mision.class);
			
			SelectorMisionRoverDespegando.class.getClassLoader().loadClass(SelectorMisionRoverDespegando.class.getCanonicalName());
			SelectorMisionRoverDespegando.class.getMethod("seleccionar",Mision.class);
			
			*/
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de Selectores, BaseMarte, Vector, Rover o Mision");
			//return "Error al cargar las clases Vector o Rover o algunos de sus métodos. Repasar identificadores y firmas de métodos";
		}
		//return null;
	}
	
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(Prueba5.class);
			System.out.println("Se ejecutan " + (request.getRunner().testCount() - 1) + " pruebas");
			juc.addListener(new RunListener() {
				@Override
			    public void testStarted(Description description) throws Exception {
					System.out.print("*");
			    }
			});
			result = juc.run(request);
		} catch (Throwable t) {
			System.out.println("Error al cargar el evaluador. La evaluación no se puede hacer con errores de compilación, o si identificadores y signaturas de la práctcia no se ajustan al enunciado.");
			System.exit(-1);
		} 
	    if(result == null || result.getFailureCount()==result.getRunCount() && result.getFailures().get(0).getMessage()==null){
	        fails = "No se han podido realizar pruebas a su entrega: Compruebe su clase y el paquete tienen nombres correctos y que los constructores de su clase funcionan de forma correcta";
	    }
	    else {
	    	  for (Failure failure : result.getFailures()) {
	              String cadena = failure.getMessage();
	              if(cadena!=null && cadena.contains("timed out")){
	                      String metodo = failure.getTestHeader().split("_")[0];
	                      cadena = "<p>Error detectado al probar el metodo "+metodo+": Ha excedido el tiempo de prueba. Compruebe que no tiene bucles infinitos en su codigo.</p> ";
	              }
	              fails += "\n"+cadena+".";
	    	  }
		}
	    return fails;
    }
        
    public static final void main(String[] args) {
    	System.out.println("Ejecutando las pruebas");
	    String fails=runJUnit();
	    if (fails.length() > 0) {
			System.out.println("Se han encontrado fallos en la ejecución:");
			System.out.println(fails);
		}
	    String grade=""+10.0*(result.getRunCount()-result.getFailureCount())/result.getRunCount();
	    System.out.println();
		System.out.println("La nota calculada ha sido: "+grade);
		System.out.println("Esta calificación es simplemente una orientación.");
		System.out.println("La evaluación definitiva se realizará cuando acabe el plazo de entrega de esta práctica.");
    }
}
