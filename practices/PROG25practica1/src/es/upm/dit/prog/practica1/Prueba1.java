package es.upm.dit.prog.practica1;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.junit.Timeout;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public final class Prueba1 {

	//private double delta = 0.1;
	
	@Before
	public void setUp() throws Exception {
 	}

	@After
	public void tearDown() throws Exception {
	}

	   @Test(timeout=500)
	    public void testConstructorAndAccessors() {
	        try {
	            Vector v = new Vector(3, 4);
	            assertEquals("Error en getX: se esperaba 3, pero se obtuvo otro valor.", 3, v.getX(), 0.0001);
	            assertEquals("Error en getY: se esperaba 4, pero se obtuvo otro valor.", 4, v.getY(), 0.0001);

	            v.setX(5);
	            v.setY(6);
	            assertEquals("Error en setX o getX: se esperaba 5, pero se obtuvo otro valor.", 5, v.getX(), 0.0001);
	            assertEquals("Error en setY o getY: se esperaba 6, pero se obtuvo otro valor.", 6, v.getY(), 0.0001);
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de constructor y accesores: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testModuloArgSetPolars() {
	        try {
	            Vector v = new Vector(3, 4);

	            assertEquals("Error en modulo: se esperaba 5 para el vector (3, 4).", 5, v.modulo(), 0.0001);
	            assertEquals("Error en arg: el ángulo calculado no es correcto.", Math.atan2(4, 3), v.arg(), 0.0001);

	            v.setPolars(10, Math.PI / 4);
	            assertEquals("Error en setPolars: la coordenada x no coincide con el módulo y el ángulo.", 
	                10 * Math.cos(Math.PI / 4), v.getX(), 0.0001);
	            assertEquals("Error en setPolars: la coordenada y no coincide con el módulo y el ángulo.", 
	                10 * Math.sin(Math.PI / 4), v.getY(), 0.0001);
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de modulo, arg y setPolars: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testSumaAndDistancia() {
	        try {
	            Vector v1 = new Vector(1, 2);
	            Vector v2 = new Vector(3, 4);

	            double expectedDistance = Math.sqrt(8); // √((3-1)² + (4-2)²)
	            assertEquals("Error en distancia: se esperaba " + expectedDistance + " pero se obtuvo otro valor.", 
	                expectedDistance, v1.distancia(v2), 0.0001);

	            v1.suma(v2);
	            assertEquals("Error en suma: la coordenada x no es correcta después de la suma.", 4, v1.getX(), 0.0001);
	            assertEquals("Error en suma: la coordenada y no es correcta después de la suma.", 6, v1.getY(), 0.0001);
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de suma y distancia: " + e.getMessage());
	        }
	    }
	    

	   @Test(timeout=500)
	    public void testRoverConstructorAndAccessors() {
	        try {
	            Vector pos = new Vector(0, 0);
	            Vector vel = new Vector(0.01, 0);
	            Rover rover = new Rover("R1", pos, 0, vel, 100);

	            assertEquals("Error en getId: se esperaba 'R1'.", "R1", rover.getId());
	            assertEquals("Error en getPos: se esperaba el vector (0,0).", pos, rover.getPos());
	            assertEquals("Error en getT: se esperaba 0.", 0, rover.getT());
	            assertEquals("Error en getVel: se esperaba el vector (0.01, 0).", vel, rover.getVel());
	            assertEquals("Error en getPower: se esperaba 100.", 100, rover.getPower(), 0.0001);

	            rover.setId("R2");
	            assertEquals("Error en setId: no se actualizó correctamente.", "R2", rover.getId());
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba del constructor y accesores: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testPeligro() {
	        try {
	            Rover rover1 = new Rover("R1", new Vector(0, 0), 0, new Vector(0, 0), 100);
	            Rover rover2 = new Rover("R2", new Vector(1, 1), 0, new Vector(0, 0), 100);
	            Rover rover3 = new Rover("R3", new Vector(3, 3), 0, new Vector(0, 0), 100);

	            assertTrue("Error en peligro: no detectó una distancia peligrosa.", rover1.peligro(rover2));
	            assertFalse("Error en peligro: detectó una distancia peligrosa donde no la había.", rover1.peligro(rover3));
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de peligro: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testPowerEstimation() {
	        try {
	            Rover rover = new Rover("R1", new Vector(0, 0), 0, new Vector(0, 0), 100);
	            Vector origin = new Vector(0, 0);
	            Vector destino = new Vector(3, 4);

	            double expectedPower = 5 * Rover.BATTERY_PER_METER; // Distancia 5
	            assertEquals("Error en powerEstimation: cálculo incorrecto de consumo.", 
	                expectedPower, rover.powerEstimation(origin, destino), 0.0001);
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de powerEstimation: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testIsActivo() {
	        try {
	            Rover rover = new Rover("R1", new Vector(0, 0), 0, new Vector(0, 0), Rover.MAX_BATTERY);
	            assertTrue("Error en isActivo: el rover debería estar activo con batería máxima.", rover.isActivo());

	            rover.setPower(Rover.SAFE_BATTERY_LEVEL - 1);
	            assertFalse("Error en isActivo: el rover no debería estar activo con batería baja.", rover.isActivo());
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de isActivo: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testMoverWithTLessThanCurrentT() {
	        try {
	            Rover rover = new Rover("R1", new Vector(0, 0), 10, new Vector(1, 0), 100);
	            rover.mover(5);
	            assertEquals("Error en mover: no debería haber cambios cuando t < t actual.", 
	                new Vector(0, 0), rover.getPos());
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de mover con t menor que t actual: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testMoverStopsWhenBatteryLow() {
	        try {
	            Rover rover = new Rover("R1", new Vector(0, 0), 0, new Vector(0.05, 0), Rover.SAFE_BATTERY_LEVEL + 0.1);
	            rover.mover(200);
	            assertEquals("Error en mover: el rover debería haberse detenido por batería baja.", 
	                new Vector(0, 0), rover.getVel());
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de mover con batería baja: " + e.getMessage());
	        }
	    }

	   @Test(timeout=500)
	    public void testMoverWorksWithSufficientBattery() {
	        try {
	            Rover rover = new Rover("R1", new Vector(0, 0), 0, new Vector(0.05, 0), 100);
	            rover.mover(10);

	            double expectedX = 0.05 * 10; // Velocidad * tiempo
	            assertEquals("Error en mover: posición x incorrecta tras el movimiento.", expectedX, rover.getPos().getX(), 0.0001);
	        } catch (Exception e) {
	            fail("Excepción inesperada durante la prueba de mover con batería suficiente: " + e.getMessage());
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


		} catch(Throwable t) {
			t.printStackTrace(System.out);
			fail("Error en las cabeceras de métodos de Vector o Rover");
		}
	}
	
	private static Result result;
	  
    private static String runJUnit() {
	    String fails = "";
		try {
			JUnitCore juc=new JUnitCore();
			Request request = Request.aClass(Prueba1.class);
			System.out.println("Se ejecutan " + (request.getRunner().testCount() -1) + " pruebas");
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