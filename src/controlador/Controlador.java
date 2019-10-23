package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import modelo.GeneroDAO;
import modelo.GeneroJDBC;
import modelo.SerieDAO;
import modelo.SerieJDBC;

public class Controlador {
	SerieDAO serieControlador = new SerieJDBC ();
	GeneroDAO generoControlador = new GeneroJDBC ();
	
	public SerieJDBC obtenerSerie (long idSerie) throws IOException{
		return serieControlador.obtenerSerie(idSerie);
	};
	
	public SerieJDBC crearSerie (String nombreSerie, String descripcionSerie, long idGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.crearSerie(nombreSerie, descripcionSerie, idGenero);
		
	};
	
	public boolean eliminarSerie (long idSerie) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.eliminarSerie(idSerie);
	};
	
	public ArrayList <SerieJDBC> obtenerCatalogoSeries () throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.obtenerCatalogoSeries();
		
	};
	
	public int obtenerNumeroSeries () throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.obtenerNumeroSeries();
	};
	
	public ArrayList <SerieJDBC> buscarSeriesPorNombre (String nombre, int index, int limit) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.buscarSeriesPorNombre(nombre, index, limit);
		
	};
	
	public ArrayList <SerieJDBC> obtenerSeriesPorGenero (long idGenero, int index, int count) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.obtenerSeriesPorGenero(idGenero, index, count);
	};
	
	public SerieJDBC editarSerie (SerieJDBC serie) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return serieControlador.editarSerie(serie);
	};
	
	public GeneroJDBC obtenerGenero (long idGenero) throws IOException {
		return generoControlador.obtenerGenero(idGenero);
	};
	
	public GeneroJDBC editarGenero (GeneroJDBC generoPelicula) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return generoControlador.editarGenero(generoPelicula);
	};
	
	public ArrayList<GeneroJDBC> obtenerGeneros () throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return generoControlador.obtenerGeneros();
	};
	
	public GeneroJDBC crearGenero (String nombreGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return generoControlador.crearGenero(nombreGenero);
		
	};
	
	public boolean eliminarGenero (long idGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		return generoControlador.eliminarGenero(idGenero);
	};
}
