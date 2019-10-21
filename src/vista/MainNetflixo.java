package vista;

import java.io.IOException;
import java.util.Scanner;

import modelo.SerieJDBC;

public class MainNetflixo {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (System.in);
		
		SerieJDBC serie1 = new SerieJDBC ();
		
		System.out.println(serie1.obtenerSerie(1).toString());
	}

}
