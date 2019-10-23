package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import utilidades.Utilidades;

public class GeneroJDBC implements GeneroDAO {
	private long idGenero;
	private String nombreGenero;
	
	private static final String SELECT_GENERO_IDGENERO = "select * from genero where idGenero = ?";
	private static final String CREAR_GENERO = "insert into genero (nombreGenero) values (?)";
	private static final String UPDATE_GENERO = "update genero set nombreGenero = ? where idGenero = ?";
	private static final String SELECT_GENEROS = "select * from genero";
	private static final String DELETE_GENERO = "delete from genero where idGenero = ?";

	
	//CONSTRUCTORES
	public GeneroJDBC (long idGenero, String nombreGenero) {
		this.idGenero = idGenero;
		this.nombreGenero = nombreGenero;
	}
	
	public GeneroJDBC () {
		
	}
	
	//MÉTODOS
	
	public GeneroJDBC obtenerGenero (long idGenero) throws IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		GeneroJDBC genero = null;
		
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(SELECT_GENERO_IDGENERO);
			// Ejecucion de la consulta y obtencion de resultados en un ResultSet
			
			stmt.setLong(1, idGenero);
			
			rs = stmt.executeQuery();
			
			// Recuperacion de los datos del ResultSet
			while (rs.next() == true) {
				idGenero = rs.getLong("idGenero");
				nombreGenero = rs.getString("nombreGenero");
				
				genero = new GeneroJDBC (idGenero, nombreGenero);
			} 
			
			return genero;
			
		} catch (SQLException sqle) {
			// En una aplicacion real, escribo en el log y delego
			System.err.println(sqle.getMessage());
		} finally {
			try {
				// Liberamos todos los recursos pase lo que pase
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					Utilidades.closeConnection(con);
				}
			} catch (SQLException sqle) {
				// En una aplicacon real, escribo en el log, no delego porque
				// es error al liberar recursos
			}
		}
			return null;
	}
	
	public GeneroJDBC editarGenero (GeneroJDBC generoPelicula) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		
	
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(UPDATE_GENERO);
			// Ejecucion de la consulta
			
			stmt.setString(1, generoPelicula.getNombreGenero());
			stmt.setLong(2, generoPelicula.getIdGenero());
			stmt.executeUpdate();
			
			GeneroJDBC genero = new GeneroJDBC (generoPelicula.getIdGenero(), generoPelicula.getNombreGenero());
			
			return genero;

		}catch (SQLException sqle) {
			// En una aplicacion real, escribo en el log y delego
			System.err.println(sqle.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					Utilidades.closeConnection(con);
				}
			} catch (SQLException sqle) {
				// En una aplicacon real, escribo en el log, no delego porque es error al liberar recursos
			}
		}
		return null;
	}
	
	public ArrayList<GeneroJDBC> obtenerGeneros () throws FileNotFoundException, InvalidPropertiesFormatException, IOException {

		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		GeneroJDBC genero = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(SELECT_GENEROS);
			
			rs = stmt.executeQuery();
			
			// Recuperacion de los datos del ResultSet
			ArrayList <GeneroJDBC> listaGeneros = new ArrayList<GeneroJDBC> ();
			
			while (rs.next()) {
				idGenero = rs.getLong("idGenero");
				nombreGenero = rs.getString("nombreGenero");
				
				genero = new GeneroJDBC (idGenero, nombreGenero);
				
				listaGeneros.add(genero);
			} 
			
			return listaGeneros;
			
			//ESTO ES PARA COMPROBAR SI ESTÁ BIEN HECHO
			/*for (int i = 0; i < listaGeneros.size(); i++) {
				System.out.println(listaGeneros.get(i).toString());
				System.out.println("-------------------------------------------------------------");
			}*/
		}catch (SQLException sqle) {

			System.err.println(sqle.getMessage());
		} finally {
			try {
				// Liberamos todos los recursos pase lo que pase
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					Utilidades.closeConnection(con);
				}
			} catch (SQLException sqle) {
				// En una aplicacon real, escribo en el log, no delego porque es error al liberar recursos
			}
		}
		return null;
		
		
	}
	
	public GeneroJDBC crearGenero (String nombreGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		
		try {
			con = new Utilidades().getConnection();
			
			stmt = con.prepareStatement(CREAR_GENERO, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1 , nombreGenero );
			
			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
			 throw new SQLException("No se pudo guardar");
			}
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
			  idGenero = generatedKeys.getInt(1);
			};
			
			GeneroJDBC genero = new GeneroJDBC (idGenero, nombreGenero);
			
			return genero;
			
		}catch (SQLException sqle) {
			// En una aplicacion real, escribo en el log y delego
			System.err.println(sqle.getMessage());
		} finally {
			try {
				// Liberamos todos los recursos pase lo que pase
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					Utilidades.closeConnection(con);
				}
			} catch (SQLException sqle) {
				// En una aplicacon real, escribo en el log, no delego porque
				// es error al liberar recursos
			}
		}
		return null;
	}
	
	public boolean eliminarGenero (long idGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(DELETE_GENERO);
			stmt.setLong(1, idGenero);
			
			stmt.executeUpdate();
			
			return true;
			
		}catch (SQLException sqle) {
			// En una aplicacion real, escribo en el log y delego
			System.err.println(sqle.getMessage());
		} finally {
			try {
				// Liberamos todos los recursos pase lo que pase
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					Utilidades.closeConnection(con);
				}
			} catch (SQLException sqle) {
				// En una aplicacon real, escribo en el log, no delego porque
				// es error al liberar recursos
			}
		}
		return false;
	}
	
	public String toString () {
		
		String toString = "\n ID Genero: " + idGenero + "\n Genero: " + nombreGenero;
		
		return toString;
	}
	public long getIdGenero () {
		return idGenero;
		
	}
	
	public String getNombreGenero () {
		return nombreGenero;
		
	}
	
	public void setNombreGenero (String nombreGenero) {
		this.nombreGenero = nombreGenero;
		
	}
}



























