package vista;

import java.io.IOException;
import java.util.Scanner;

import modelo.SerieJDBC;

public class MainNetflixo {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (System.in);
		// TODO Auto-generated method stub
		
		SerieJDBC series = new SerieJDBC ();
		
		System.out.println("numero de genero");
		int genero = sc.nextInt();
		
		series.obtenerSeriesPorGenero(genero, 0, 5);
	}

}
