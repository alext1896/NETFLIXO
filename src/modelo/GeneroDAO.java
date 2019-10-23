package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

public interface GeneroDAO {
	
	public GeneroJDBC obtenerGenero (long idGenero) throws IOException;
	
	public GeneroJDBC editarGenero (GeneroJDBC generoPelicula) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public ArrayList<GeneroJDBC> obtenerGeneros () throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public GeneroJDBC crearGenero (String nombreGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
	
	public boolean eliminarGenero (long idGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException;
}
