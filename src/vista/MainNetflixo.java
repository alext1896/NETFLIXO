package vista;

import java.io.IOException;
import java.util.ArrayList;

import controlador.LifterController;
import modelo.Genero;
import modelo.Serie;
import modelo.SerieJDBC;

public class MainNetflixo {

	public static void main(String[] args) throws IOException {
		SerieJDBC serie = new SerieJDBC ();
		
		System.out.println(serie.obtenerSerie(1).toString());
		
	}

}
