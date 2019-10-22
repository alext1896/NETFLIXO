package vista;

import java.io.IOException;
import java.util.Scanner;

import modelo.SerieJDBC;

public class MainNetflixo {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner (System.in);
		
		SerieJDBC serie1 = new SerieJDBC (2, "Nombre Nuevo", "Descripcion nueva", 2);
		
		serie1.buscarSeriesPorNombre("peli 1", 0, 2);
	}

}
