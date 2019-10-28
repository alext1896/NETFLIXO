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


public class SerieJDBC implements SerieDAO {
	private long idSerie, idGenero;
	private String nombreSerie, descripcionSerie; 
	private static final String SELECT_SERIE_IDSERIE = "select * from series where idSerie = ?";
	private static final String CREAR_SERIE = "insert into series (nombreSerie, descripcionSerie, idGenero) values (?, ?, ?)";
	private static final String UPDATE_SERIE = "update series set nombreSerie = ?, descripcionSerie = ?, idGenero = ? where idSerie = ?";
	private static final String DELETE_SERIE = "delete from series where idSerie = ?";
	private static final String SELECT_SERIES = "select * from series";
	private static final String CONTAR_SERIES = "select count(idSerie) 'numero' from series";
	private static final String SELECT_SERIES_GENERO = "select * from series where idGenero = ?";
	private static final String SELECT_SERIES_NOMBRE = "select * from series where nombreSerie like ? limit ?, ?";

	Serie serie = null;
	
	//MÉTODOS

	public Serie obtenerSerie (long idSerie) throws IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(SELECT_SERIE_IDSERIE);
			// Ejecucion de la consulta y obtencion de resultados en un ResultSet
			
			stmt.setLong(1, idSerie);
			
			rs = stmt.executeQuery();
			
			// Recuperacion de los datos del ResultSet
			while (rs.next() == true) {
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombreSerie");
				descripcionSerie = rs.getString("descripcionSerie");
				idGenero = rs.getLong("idGenero");
				
				serie = new Serie (idSerie, nombreSerie, descripcionSerie, idGenero);
			} 
			
			return serie;
			
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

	public Serie crearSerie (String nombreSerie, String descripcionSerie, long idGenero) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {

		Connection con = null;
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
			con = new Utilidades().getConnection();
			
			stmt = con.prepareStatement(CREAR_SERIE, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1 , nombreSerie );
			stmt.setString(2, descripcionSerie);
			stmt.setLong(3, idGenero);
			
			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
			 throw new SQLException("No se pudo guardar");
			}
			
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
			  idSerie = generatedKeys.getInt(1);
			};
			
			serie = new Serie (idSerie, nombreSerie, descripcionSerie, idGenero);
			
			return serie;
			
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
		return serie = null;
	}
	
	public boolean eliminarSerie (long idSerie) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(DELETE_SERIE);
			stmt.setLong(1, idSerie);
			
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
	
	
	public ArrayList <Serie> obtenerCatalogoSeries () throws FileNotFoundException, InvalidPropertiesFormatException, IOException{
		
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(SELECT_SERIES);
			
			rs = stmt.executeQuery();
			
			// Recuperacion de los datos del ResultSet
			ArrayList <Serie> listaSeries = new ArrayList<Serie> ();
			
			while (rs.next()) {
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombreSerie");
				descripcionSerie = rs.getString("descripcionSerie");
				idGenero = rs.getLong("idGenero");
				
				serie = new Serie (idSerie, nombreSerie, descripcionSerie, idGenero);
				
				listaSeries.add(serie);
			} 
			
			return listaSeries;
			
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
				// En una aplicacon real, escribo en el log, no delego porque es error al liberar recursos
			}
		}
		return null;
	}
	

	public int obtenerNumeroSeries () throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;

		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(CONTAR_SERIES);
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeQuery();
			
			// Recuperacion de los datos del ResultSet
			while (rs.next()) {
				int totalSerie = rs.getInt("NUMERO");
				
				return totalSerie;
			} 
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
		return -1;
	}
	
	public ArrayList <Serie> buscarSeriesPorNombre (String nombre, int index, int limit) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.prepareStatement(SELECT_SERIES_NOMBRE);	
			stmt.setString (1, nombre + '%');
			stmt.setLong(2, index);
			stmt.setLong(3, limit);
			
			rs = stmt.executeQuery();
			
			ArrayList <Serie> listaSeries = new ArrayList <Serie> ();
			
			
			
			while (rs.next()) {
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombreSerie");
				descripcionSerie = rs.getString("descripcionSerie");
				idGenero = rs.getLong("idGenero");
				
				serie = new Serie (idSerie, nombreSerie, descripcionSerie, idGenero);
				
				listaSeries.add(serie);
			} 
			
			return listaSeries;
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

	
	public ArrayList <Serie> obtenerSeriesPorGenero (long idGenero, int index, int count) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		try {
			con = new Utilidades().getConnection();
			stmt = con.prepareStatement(SELECT_SERIES_GENERO);
			stmt.setLong(1, idGenero);
			rs = stmt.executeQuery();
			
			ArrayList <Serie> listaSeries = new ArrayList<Serie> ();
			
			int contador = 0;
			
			while (rs.next() && contador < count) {
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombreSerie");
				descripcionSerie = rs.getString("descripcionSerie");
				idGenero = rs.getLong("idGenero");
				
				serie = new Serie (idSerie, nombreSerie, descripcionSerie, idGenero);
				
				listaSeries.add(serie);
				contador ++;
			} 
			
			return listaSeries;
			
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
				// En una aplicacon real, escribo en el log, no delego porque es error al liberar recursos
			}
		}
		return null;
	}
	
	public Serie editarSerie (Serie serie) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		PreparedStatement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		
		try {
			con = new Utilidades().getConnection();
			
			// Creacion de la sentencia
			stmt = con.prepareStatement(UPDATE_SERIE);
			
			// Ejecucion de la consulta
			stmt.setString(1, serie.getNombreSerie());
			stmt.setString(2, serie.getDescripcionSerie());
			stmt.setLong(3, serie.getIdGenero());
			stmt.setLong(4, serie.getIdSerie());
			
			stmt.executeUpdate();
			
			serie = new Serie (serie.getIdSerie(), serie.getNombreSerie(), serie.getDescripcionSerie(), serie.getIdGenero());
			

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
	
	


	
}

