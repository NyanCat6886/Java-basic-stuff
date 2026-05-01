package test;
import java.util.*;

public class UsoArrays {
public static void main(String[] args) {
	Random rand = new Random();
	int[][] meme = new int[4][4];

	for (int i = 0; i < meme.length; i++) {
	    for (int j = 0; j < meme[i].length; j++) {
	        meme[i][j] = rand.nextInt(2); // 0–1
	    }
	}
	 for (int i = 0; i < meme.length; i++) {
         for (int j = 0; j < meme[i].length; j++) {
             System.out.print(meme[i][j] + "\t"); // tabulación para formato
         }
         System.out.println(); // salto de línea por fila
	
}
}}
