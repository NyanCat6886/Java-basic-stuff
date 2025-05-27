package es.upm.dit.prog.practica5;

//import java.util.Arrays;
import java.util.Scanner;

import javax.swing.SwingUtilities;


//import java.util.Arrays;

public class PruebaInteractiva5 {
	
	private String[] cmds = new String[] {
			"hello \n saludo",
			"help \n lista de órdenes",
			"exit \n salir",
			"status \n valores de variables",
			"clear \n reset de variables",
			"pos x y \n crea this.pos que es un vector posicion con x:double y:double",
			"vel x y \n crea this.vel que es un vector velocidad con x:double y:double ",
			"rover id t pow \n crea this.Rover1 nuevo con id:String this.pos t:long pow:double this.vel y guarda el anterior en this.Rover2",
			"addvel \n añade vel a rover1",
			"mover1 t \n mueve this.Rover1 con t:long",
			"mover2 t \n mueve this.Rover2 con t:long",
			"peligro \n comprueba si el this.Rover1 y this.Rover2 están en peligro",
			//practica2
			"mision id n \n crea una misión con id:String this.Rover1 n posiciones",
			"addpos \n añade this.pos a this.mision",
			"update t \n actualiza this.mision con t:long",
			"show \n muestra un diagrama con la situación actual de this.mision y this.Rover1",
			// practica3
			"addrover \n añade this.rover1 a this.bm",
			"getrovers \n obtiene rovers no null de this.bm",
			"addmision \n añade this.mision1 a this.bm",
			"getmisiones \n obtiene misiones no null de this.bm",
			"updatebm t \n actualiza todos los elementos de this.bm con t:long",
			"sim tini tfin \n muestra una simulación del movimiento de los satelites de la estacion con: tini:long tfin:long",
			//practica4 modificados peligro y alarma
			"seltrue \n devuelve todas las misiones",
			"selactivas \n devuelve las misiones activas",
			"selalejadas d\n devuelve las misiones con exploreres a mayor distancia de d:double de this.pos",
			"selaterrizando \n muestra las misiones con exploreres aterrizando",
			"seldespegando \n muestra las misiones con exploreres despegando",
			"selenpeligro \n muestra las misiones con exploreres en peligro"


	};
	
	private Vector vel;
	private Vector pos;
	private Rover rover1;
	private Rover rover2;
	private Mision mision;
	private BaseMarte bm;
	private SelectorMision sel;
	
	public PruebaInteractiva5() {
		this.init();
	}

	public void init() {
		this.vel = new Vector(0,0);
		this.pos = new Vector(0,0);
		this.rover1 = new Rover("d1", this.pos, 0, this.vel, 100);//, true);
		this.rover2 = new Rover("d2", this.pos, 0, this.vel, 100);//, true);
		this.mision = new Mision("m0", this.rover1, 6); //, false);
		this.bm = new BaseMarte();
		this.bm.addRover(this.rover1);
		this.sel = new SelectorMisionTrue();

	}
	
	public String run (String c) {
		c = c.toLowerCase();
		String[] fs = c.trim().split(" +");
		
		// practica1
		int i = 0;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) //"hello"
			return c;
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { //"help"
			String r = "";
			for (String ci: cmds)
				r += ci + "\n";
			return r;
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { //"exit"
			System.out.println("bye");
			System.exit(0);
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { //"status"
			return "this.vel=" + this.vel.toString() + "\n"
					+ "this.pos=" + this.pos.toString() + "\n"
					+ "this.Rover1=" + this.rover1.toString() + "\n"
					+ "this.Rover2=" + this.rover2.toString() + "\n"
					+ "this.bm=" + this.bm.toString() + "\n";
		}
		i++;		
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "clear"
			this.init();
			return "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "pos"
			try {
				this.pos = new Vector(Double.parseDouble(fs[1]), Double.parseDouble(fs[2]));
				return this.pos + "\n";				
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "vel"
			try {
				this.vel = new Vector(Double.parseDouble(fs[1]), Double.parseDouble(fs[2]));
				return this.vel + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "Rover"
			try {
				Rover nuevo = new Rover (fs[1], this.pos, Long.parseLong (fs[2]), this.vel, Double.parseDouble(fs[3])); 
				this.rover2 = this.rover1;
				this.rover1 = nuevo;				
				return this.rover1.toString() +"\n"; 
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addVel"
			this.rover1.setVel(this.vel);
			return this.rover1.toString() + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "mover1"
			try {
				this.rover1.mover(Long.parseLong(fs[1]));
				return this.rover1.toString()+ "\n"; 
			} catch (Exception e) {
				return cmds[i]+ "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "mover2"
			try {
				this.rover2.mover(Long.parseLong(fs[1]));
				return this.rover2.toString()+ "\n"; 
			} catch (Exception e) {
				return cmds[i]+ "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "peligro"
			return "peligro= " + this.rover1.peligro(this.rover2); 
		}
		
		//practica2

		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "mision"
			try {
				this.mision = new Mision (fs[1], this.rover1, Integer.parseInt(fs[2])); //, false);
				return this.mision.toString() + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addpos"
			try {
				this.mision.addPunto(this.pos); //, Long.parseLong(fs[1]));
				return this.mision.toString() + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "update t"
			try {
				this.mision.update(Long.parseLong(fs[1]));
				return this.mision.toString() + "\n";
			} catch (Exception e) {
				return cmds[i] + "\n";
			}
		}
		
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "show"
			System.out.println("Launching...");
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MapaPanel panel = new MapaPanel();
	            	panel.init(bm, mision, 0, 0, 0); 
	            	panel.createAndShowGUI();
	            }
	        });
			return "viewer launched";
		}

		// practica3
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addrover"
			this.bm.addRover(this.rover1);
			return this.bm.toString() + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "getrovers"
			String r = "";
			for (Rover d: this.bm.getRovers())
				r += d.toString();
			return r + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "addmision"
			this.bm.addMision(this.mision);
			return this.bm.toString() + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "getmisiones"
			String r = "";
			for (Mision m: this.bm.getMisiones(this.sel))
				r += m.toString();
			return r + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "updatebm"
			try {
				this.bm.update(Long.parseLong(fs[1]));
				return this.bm.toString()+ "\n"; 
			} catch (Exception e) {
				return cmds[i]+ "\n";
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "sim"
			try {
				long tini = Long.parseLong (fs[1]);
				long tfin = Long.parseLong (fs[2]);
			System.out.println("Launching sim from " + tini + " to " + tfin + " stepping at " + 1);
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MapaPanel panel = new MapaPanel();
	                panel.init (bm, mision, tini, tfin, 1); 
	                panel.createAndShowGUI();
	            }
	        });
			return "viewer launched";
			} catch(Exception e) {
				return cmds[i];
			}
		}
		//practica4 
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "seltrue"
			String r = "";
			SelectorMision sm = new SelectorMisionTrue();
			for (Mision m: this.bm.getMisiones(sm))
				r += m.toString() + "\n";
			return r + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "selacabadas"
			String r = "";
			SelectorMision sm = new SelectorMisionAcabada();
			for (Mision m: this.bm.getMisiones(sm))
				r += m.toString()+ "\n";
			return r + "\n";
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "selabortadas"
			try {
				SelectorMision sm = new SelectorMisionAbortada();
				String r = "";
				for (Mision m: this.bm.getMisiones(sm))
					r += m.toString()+ "\n";
				return r + "\n";
			} catch (Exception e) {
				return cmds[i];
			}
		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "selalejadas"
			try {
				String r = "";
				SelectorMision sm = new SelectorMisionRoverAlejado(this.pos, Double.parseDouble(fs[1])); 
				for (Mision m: this.bm.getMisiones(sm))
					r += m.toString()+ "\n";
				return r + "\n";
			} catch(Exception e) {
				return cmds[i];
			}

		}
		i++;
		if (c.indexOf(cmds[i].split(" ")[0]) == 0) { // "selenpeligro"
			String r = "";
			//Object[] objectArray = this.bm.getRovers().toArray();
			//Rover[] roverArray = Arrays.copyOf(objectArray, objectArray.length, Rover[].class);
			SelectorMision sm = new SelectorMisionRoverEnPeligro((Rover[])this.bm.getRovers().toArray() );
			for (Mision m: this.bm.getMisiones(sm))
				r += m.toString()+ "\n";
			return r + "\n";
		}
	
	
		
		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva5 m = new PruebaInteractiva5();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

