package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

public interface SerieDAO {
	
	public SerieJDBC obtenerSerie (long idSerie) throws IOException;
	
	public SerieJDBC crearSerie (String nombreSerie, String descripcionSerie, long idGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException ;
	
	public boolean eliminarSerie (long idSerie) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public ArrayList <SerieJDBC> obtenerCatalogoSeries () throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public int obtenerNumeroSeries () throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public ArrayList <SerieJDBC> buscarSeriesPorNombre (String nombre, int index, int limit) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public ArrayList <SerieJDBC> obtenerSeriesPorGenero (long idGenero, int index, int count) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public SerieJDBC editarSerie (SerieJDBC serie) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
}
