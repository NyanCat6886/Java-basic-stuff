package es.upm.dit.prog.practica2;
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



public final class Prueba2 {

	//private double delta = 0.1;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	// Mision
	// ctor, get, set
	// addPunto vacío, con sitio, lleno
	// update2: inactivo, no tiempo
	// update2: error de t, vacío, último con vel = 0
	// update2: batería de sobra, en punto
	// update2: batería de sobre, lejos de punto
	// update2: batería de sobra, último punto
	// update2: falta batería, en punto, último punto
	// update2: falta batería, lejos de punto
	
	@Test(timeout = 500)
    public void testConstructorAccessorsModifiers() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 0, new Vector(0, 0), 100);
            Mision mision = new Mision("Mision1", rover, 5);
            assertEquals("Error en constructor de Mision", "Mision1", mision.getId());
            assertEquals("Error en constructor de Mision",rover, mision.getRover());
            assertEquals("Error en constructor de Mision",5, mision.getPuntos().length);
            assertEquals("Error en constructor de Mision",0, mision.getnPuntos());
            assertEquals("Error en constructor de Mision",0, mision.getN());
        } catch (Exception e) {
            fail("Excepcion en constructor de Mision: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void testAddPunto() {
        try {
            Mision mision = new Mision("Mision1", new Rover("Rover1", new Vector(0, 0), 0, new Vector(0, 0), 100), 2);
            Vector punto1 = new Vector(1, 1);
            Vector punto2 = new Vector(2, 2);
            Vector punto3 = new Vector(3, 3);

            mision.addPunto(punto1);
            assertEquals("Error en Mision.addPunto", punto1, mision.getPuntos()[0]);
            assertEquals("Error en Mision.addPunto", 1, mision.getnPuntos());

            mision.addPunto(punto2);
            assertEquals("Error en Mision.addPunto", punto2, mision.getPuntos()[1]);
            assertEquals("Error en Mision.addPunto", 2, mision.getnPuntos());

            mision.addPunto(punto3);
            assertEquals("Error en Mision.addPunto", 2, mision.getnPuntos()); // No se añade, ya está lleno
        } catch (Exception e) {
            fail("Error en Mision.addPunto: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void testUpdateInactiveOrPastTime() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 5, new Vector(0, 0), 50);
            Mision mision = new Mision("Mision1", rover, 2);

            rover.setPower(0); // Rover inactivo
            mision.update(10);
            assertEquals("Error en Mision.update con rover inactivo:", new Vector(0, 0), rover.getPos());

            rover.setPower(100); // Rover activo
            mision.update(4); // Tiempo pasado
            assertEquals("Error en Mision.update con tiempo pasado:", new Vector(0, 0), rover.getPos());
        } catch (Exception e) {
            fail("Error en Mision.update: " + e.getMessage());
        }
    }


    @Test(timeout = 500)
    public void testUpdateInsufficientBatteryNearPoint() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 5, new Vector(0, 0), 50);
            Mision mision = new Mision("Mision1", rover, 2);
            mision.addPunto(new Vector(0.1, 0.1));
            mision.update(5);
            assertEquals("Error en Mision.update sin batería cerca de punto: ",0, mision.getN());
            assertEquals("Error en Mision.update sin batería cerca de punto: ",new Vector(0, 0), rover.getVel());
        } catch (Exception e) {
            fail("Error en Mision.update sin batería: " + e.getMessage());
        }
    }

    //@Test(timeout = 500)
    public void testUpdateInsufficientBatteryFarPoint() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 5, new Vector(0, 0), 50);
            Mision mision = new Mision("Mision1", rover, 2);
            mision.addPunto(new Vector(5, 5));
            mision.update(5);
            assertEquals("Error en Mision.update sin batería lejos de punto: ",new Vector(0, 0), rover.getVel());
            assertEquals("Error en Mision.update sin batería lejos de punto: ",0, mision.getN());
        } catch (Exception e) {
            fail("Error en Mision.update sin batería cerca de punto: " + e.getMessage());
        }
    }	

    @Test(timeout = 500)
    public void testUpdateOutOfBoundsOrLastPoint() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 0, new Vector(0.05, 0), 200);
            Mision mision = new Mision("Mision1", rover, 2);
            mision.update(0);
            assertEquals("Error en Mision.update último punto: ",new Vector(0, 0), rover.getPos());

            assertEquals("Error en Mision.update último punto: ",0, mision.getnPuntos());

            mision.addPunto(new Vector(0.5, 0));
            mision.addPunto(new Vector(1.0, 0));
            rover.mover(10); 
            mision.update(10);
            rover.mover(20);
            mision.update(20);
            assertEquals("Error en Mision.update último punto: ",2, mision.getN());
            assertEquals("Error en Mision.update último punto: ",new Vector(0, 0), rover.getVel());
        } catch (Exception e) {
            fail("Error en Mision.update último punto: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void testUpdateSufficientBatteryIntermediatePoint() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 5, new Vector(0.05, 0), 200);
            Mision mision = new Mision("Mision1", rover, 2);
            mision.addPunto(new Vector(1, 0));
            mision.addPunto(new Vector(2, 0));
            
            rover.mover(10); 
            mision.update(10);
            assertEquals("Error en Mision.update: ",0, mision.getN());
            assertNotEquals("Error en Mision.update: ", 0, rover.getVel().modulo(), 0.01);
        } catch (Exception e) {
            fail("Error en Mision.update: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void testUpdateSufficientBatteryFarPoint() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 0, new Vector(0.02, 0), 200);
            Mision mision = new Mision("Mision1", rover, 2);
            mision.addPunto(new Vector(10, 10));
            
            rover.mover(10);
            mision.update(10);
            assertNotEquals("Error en Mision.update batería suficiente lejos de punto: ",0, rover.getVel().modulo(), 0.01);
            assertEquals("Error en Mision.update batería suficiente lejos de punto: ",0, mision.getN());
        } catch (Exception e) {
            fail("Error en Mision.update batería suficiente lejos de punto: " + e.getMessage());
        }
    }

    @Test(timeout = 500)
    public void testUpdateSufficientBatteryLastPoint() {
        try {
            Rover rover = new Rover("Rover1", new Vector(0, 0), 0, new Vector(0.05, 0), 200);
            Mision mision = new Mision("Mision1", rover, 1);
            mision.addPunto(new Vector(0.5, 0));

            rover.mover(10);
            mision.update(10);
            assertEquals("Error en Mision.update batería suficiente ultimo punto: ",1, mision.getN());
            assertEquals("Error en Mision.update batería suficiente ultimo punto: ",new Vector(0, 0), rover.getVel());
        } catch (Exception e) {
            fail("Error en Mision.update batería suficiente ultimo punto: " + e.getMessage());
        }
    }
	
	@Test(timeout=500)
	public void testComplexScenarioPowerEnough() {
		try {
            Rover rover = new Rover("RoverComplex", new Vector(0, 0), 0, new Vector(0, 0), 200);
            Mision mision = new Mision("Mision1", rover, 5);
            mision.addPunto(new Vector(0, 0));
            mision.addPunto(new Vector(0, 100));
            mision.addPunto(new Vector(500, 100));
            mision.addPunto(new Vector(500, 0));
            mision.addPunto(new Vector(0, 0));
            rover.mover(0);
            mision.update(0);
            assertFalse("Error en Mision.update batería suficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería suficiente: ", 1, mision.getN());
            rover.mover(2000);
            mision.update(2000);
            assertFalse("Error en Mision.update batería suficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería suficiente: ", 2, mision.getN());
            rover.mover(12000);
            mision.update(12000);
            assertFalse("Error en Mision.update batería suficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería suficiente: ", 3, mision.getN());
            rover.mover(14000);
            mision.update(14000);
            assertFalse("Error en Mision.update batería suficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería suficiente: ", 4, mision.getN());
            rover.mover(24000);
            mision.update(24000);
            assertEquals("Error en Mision.update batería suficiente: ", 5, mision.getN());
            assertFalse("Error en Mision.update batería suficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería suficiente: ", new Vector(0, 0), rover.getVel());
		}
		catch(Exception e) {
			fail("Error en Mision.update batería suficiente: " + e.getMessage());
		}
	}
	
	@Test(timeout=500)
	public void testComplexScenarioPowerNotEnough() {
		try {
            Rover rover = new Rover("RoverComplex", new Vector(0, 0), 0, new Vector(0, 0), 100);
            Mision mision = new Mision("Mision1", rover, 5);
            mision.addPunto(new Vector(0, 0));
            mision.addPunto(new Vector(0, 100));
            mision.addPunto(new Vector(500, 100));
            mision.addPunto(new Vector(500, 0));
            mision.addPunto(new Vector(0, 0));
            rover.mover(0);
            mision.update(0);
            assertFalse("Error en Mision.update batería insuficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería insuficiente: ", 1, mision.getN());
            rover.mover(2000);
            mision.update(2000);
            assertTrue("Error en Mision.update batería insuficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería insuficiente: ", new Vector(0, 0), rover.getVel());
            assertEquals("Error en Mision.update batería insuficiente: ", 2, mision.getN());
            rover.mover(12000);
            mision.update(12000);
            assertTrue("Error en Mision.update batería insuficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería insuficiente: ", new Vector(0, 0), rover.getVel());
            assertEquals("Error en Mision.update batería insuficiente: ", 2, mision.getN());
            rover.mover(14000);
            mision.update(14000);
            assertTrue("Error en Mision.update batería insuficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería insuficiente: ", new Vector(0, 0), rover.getVel());
            assertEquals("Error en Mision.update batería insuficiente: ", 2, mision.getN());
            rover.mover(24000);
            mision.update(24000);
            assertEquals("Error en Mision.update batería insuficiente: ", 2, mision.getN());
            assertTrue("Error en Mision.update batería insuficiente: ", mision.isAbort());
            assertEquals("Error en Mision.update batería insuficiente: ", new Vector(0, 0), rover.getVel());
            assertEquals("Error en Mision.update batería insuficiente: ", new Vector(0, 0), rover.getVel());
		}
		catch(Exception e) {
			fail("Error en Mision.update batería insuficiente: " + e.getMessage());
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
			
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en alguna cabecera de método de Vector, Rover o Mision");
			//return "Error al cargar las clases Vector o Rover o algunos de sus métodos. Repasar identificadores y firmas de métodos";
		}
		//return null;
	}
	
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(Prueba2.class);
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
