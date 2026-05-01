package test;
import javax.swing.*;
public class CheckMail {
public static void main(String[] args) {
	boolean arroba = false;
	String mail =JOptionPane.showInputDialog("Introduce el email: ");
for (int i=0; i<mail.length(); i++) {
	if (mail.charAt(i)=='@') {
		arroba=true;
	}
} if (arroba = true) {
	System.out.println("El mail tiene arroba");
}else {
	System.out.println("Tu mail no tiene arroba, introduce un mail válido, por favor.");
}

	System.out.println("La longitud de la dirección introducida es de: "
			+ ""+mail.length()+" caracteres");

}
}
