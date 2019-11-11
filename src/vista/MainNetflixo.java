package vista;

import java.io.IOException;
import modelo.SerieJDBC;

public class MainNetflixo {

	public static void main(String[] args) throws IOException {
		SerieJDBC serie = new SerieJDBC ();
		
		System.out.println(serie.buscarSeriesPorNombre("peli", 0, 2));
		
	}

}
