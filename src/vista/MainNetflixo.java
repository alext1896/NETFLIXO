package vista;

import java.io.IOException;

import modelo.SerieJDBC;

public class MainNetflixo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SerieJDBC series = new SerieJDBC ();
		
		try {
			series.obtenerSerie(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
