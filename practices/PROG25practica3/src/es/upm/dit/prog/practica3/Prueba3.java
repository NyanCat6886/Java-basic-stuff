package es.upm.dit.prog.practica3;
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

public final class Prueba3 {

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
		BaseMarte cc = new BaseMarte();
		String s = cc.toString();
		assertTrue(s != null);
	}	

	//testBaseMarte2.addRover vacio
	@Test (timeout=500)
	public void testBaseMarte2(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover[] explorers0 = new Rover[10];
		for (int i = 0; i < explorers0.length; i++)
		  explorers0[i] = new Rover("e" + i, pos, 0, vel, Rover.MAX_BATTERY);
		Rover[] explorers1 = new Rover[] {explorers0[0]};
		BaseMarte cc = new BaseMarte();
		cc.addRover(null);
		assertArrayEquals("BaseMarte.addRover null", new Rover[] {}, cc.getRovers());
		cc.addRover(explorers0[0]);
		assertArrayEquals("BaseMarte.addRover con explorers vacío", explorers1, cc.getRovers());
	}	

	//testBaseMarte3.addRover a medias
	@Test (timeout=500)
	public void testBaseMarte3(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover[] explorers0 = new Rover[10];
		for (int i = 0; i < explorers0.length; i++)
		  explorers0[i] = new Rover("e" + i, pos, 0, vel, Rover.MAX_BATTERY);
		BaseMarte cc = new BaseMarte();
		cc.addRover(explorers0[0]);
		Rover[] explorers2 = new Rover[] {explorers0[0], explorers0[1], explorers0[2]};
		cc.addRover(explorers0[1]);
		cc.addRover(explorers0[2]);
		assertArrayEquals("BaseMarte.addRover con explorers a medias", explorers2, cc.getRovers());
	}	

	//testBaseMarte4.addRover  lleno
	@Test (timeout=500)
	public void testBaseMarte4(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover d = new Rover("NO", pos, 0, vel, Rover.MAX_BATTERY);
		Rover[] explorers0 = new Rover[10];
		for (int i = 0; i < explorers0.length; i++)
		  explorers0[i] = new Rover("e" + i, pos, 0, vel, Rover.MAX_BATTERY);
		BaseMarte cc = new BaseMarte();
		cc.addRover(d);
		assertArrayEquals("BaseMarte.addRover con explorers vacío", new Rover[] {d}, cc.getRovers());
		for (int i = 0; i < explorers0.length; i++)
			  cc.addRover(explorers0[i]);
		assertArrayEquals("BaseMarte.addRover con más de 10 explorers", explorers0, cc.getRovers());
	}	

	//testBaseMarte5.addMision vacío
	@Test (timeout=500)
	public void testBaseMarte5(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover d = new Rover("e0", pos, 0, vel, Rover.MAX_BATTERY);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 5);
		Mision[] misiones1 = new Mision[] {misiones0[0]};
		BaseMarte cc = new BaseMarte();
		cc.addMision(null);
		assertArrayEquals("BaseMarte.addMision null", new Mision[] {}, cc.getMisiones());
		cc.addMision(misiones0[0]);
		assertArrayEquals("BaseMarte.addMision con misiones vacío", misiones1, cc.getMisiones());
	}	

	//testBaseMarte6.addMision a medias
	@Test (timeout=500)
	public void testBaseMarte6(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover d = new Rover("e0", pos, 0, vel, Rover.MAX_BATTERY);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 5);
		BaseMarte cc = new BaseMarte();
		cc.addMision(misiones0[0]);
		Mision[] misiones2 = new Mision[] {misiones0[0], misiones0[1], misiones0[2]};
		cc.addMision(misiones0[1]);
		cc.addMision(misiones0[2]);
		assertArrayEquals("BaseMarte.addMision con misiones a medias", misiones2, cc.getMisiones());
	}	

	
	//testBaseMarte7.addMision  lleno
	@Test (timeout=500)
	public void testBaseMarte7(){
		Vector pos = new Vector(0,0);
		Vector vel = new Vector(0,0);
		Rover d = new Rover("e0", pos, 0, vel, Rover.MAX_BATTERY);
		Mision m = new Mision("mNO", d, 6);
		Mision[] misiones0 = new Mision[10];
		for (int i = 0; i < misiones0.length; i++)
			misiones0[i] = new Mision("m" + i, d, 6);
		BaseMarte cc = new BaseMarte();
		cc.addMision(m);
		for (int i = 0; i < misiones0.length; i++)
			  cc.addMision(misiones0[i]);
		assertArrayEquals("BaseMarte.addMision con más de 10 explorers", misiones0, cc.getMisiones());
	}	

	//testBaseMarte8.update 
	@Test (timeout=500)
	public void testBaseMarte8(){
		int n = 10;
		Rover[] explorers = new Rover[n];
		Mision[] misiones = new Mision[n];
		BaseMarte cc = new BaseMarte();
		for (int i = 0; i < n; i++) {
			Vector p = new Vector(10*i, 0);
			explorers[i] = new Rover("e" + i, p, 0, new Vector(0,0),
					(i < 5) ? Rover.MAX_BATTERY : (Rover.MAX_BATTERY/2));
			cc.addRover(explorers[i]);	
			misiones[i] = new Mision ("m"+i, explorers[i], 5);
			for (int j = 0; j < 5; j++)
				misiones[i].addPunto(new Vector(10*i, (200*j)));
			cc.addMision(misiones[i]);
		}
		for (int j = 0; j < 5; j++) {
			long t = j*200*20;
			cc.update(t);
			for (int i =0 ; i < 5; i++) {
				assertTrue("BaseMarte update: explorer" + misiones[i].getRover().toString() + " avanzan mal ",
						new Vector(10*i, 200*j).distancia(
						misiones[i].getRover().getPos()) < 0.1);
			}
			for (int i = 5; i < misiones.length; i++) {
				if (t <= 4000)
					assertTrue("BaseMarte update: " + misiones[i] + " avanzan mal ",
							new Vector(10*i, 200*j).distancia(
							misiones[i].getRover().getPos()) < 0.1);
				else {
					assertTrue("BaseMarte update: " + misiones[i] + " avanzan mal ",
							misiones[i].acabado());
				}

			}
		}
	}	
	
	@Test (timeout=500)
	//public static String 
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
			BaseMarte.class.getMethod("getMisiones");
			BaseMarte.class.getMethod("update", Long.TYPE);
			
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de BaseMarte, Vector, Dron o Mision");
			//return "Error al cargar las clases Vector o Dron o algunos de sus métodos. Repasar identificadores y firmas de métodos";
		}
		//return null;
	}
	
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(Prueba3.class);
			System.out.println("Se ejecutan " + (request.getRunner().testCount() - 1)  + " pruebas");
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
