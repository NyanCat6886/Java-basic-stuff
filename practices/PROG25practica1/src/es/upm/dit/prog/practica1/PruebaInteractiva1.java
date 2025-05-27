package es.upm.dit.prog.practica1;

import java.util.Scanner;

//import javax.swing.SwingUtilities;


//import java.util.Arrays;

public class PruebaInteractiva1 {
	
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
			"peligro \n comprueba si el this.Rover1 y this.Rover2 están en peligro"
	};
	
	private Vector vel;
	private Vector pos;
	private Rover rover1;
	private Rover rover2;
	
	public PruebaInteractiva1() {
		this.init();
	}

	public void init() {
		this.vel = new Vector(0,0);
		this.pos = new Vector(0,0);
		this.rover1 = new Rover("d1", this.pos, 0, this.vel, 100);//, true);
		this.rover2 = new Rover("d2", this.pos, 0, this.vel, 100);//, true);
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
					+ "this.Rover2=" + this.rover2.toString() + "\n";
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
		


		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva1 m = new PruebaInteractiva1();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

