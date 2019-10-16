package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Utilidades;


public class SerieJDBC {
	private double idSerie , idGenero;
	private String nombreSerie, descripcionSerie;
	
	public SerieJDBC(double idSerie, String nombreSerie, String descripcionSerie) {
		this.idSerie = idSerie;
		this.nombreSerie = nombreSerie;
		this.descripcionSerie = descripcionSerie;
	}

	public SerieJDBC() {

	}

	
	public SerieJDBC obtenerSerie (double idSerie) throws IOException {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		SerieJDBC serie = null;
		String query = "SELECT * FROM series WHERE idSerie = " + idSerie;
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeQuery(query);
			
			
			// Recuperacion de los datos del ResultSet
			while (rs.next() == true) {
				idSerie = rs.getInt("idSerie");
				nombreSerie = rs.getString("nombre");
				descripcionSerie = rs.getString("descripcion");
					
				//serie = new SerieJDBC (queryIdSerie, nombre, descripcion);
				serie = new SerieJDBC (idSerie, nombreSerie, descripcionSerie);
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
			return serie = null;
	}

	
	public SerieJDBC crearSerie (String nombreSerie, double idGenero, String descripcionSerie) {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		SerieJDBC serie = null;
		String query = "insert into series values ('"+nombreSerie+"', '"+idGenero+ "', '"+descripcionSerie+"')";
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			stmt.execute(query);
			
			return serie = new SerieJDBC (idSerie, nombreSerie,  descripcionSerie);
			
			
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
	
	public boolean eliminarSerie (double idSerie) {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		String query = "delete from series where idSerie = " + idSerie;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			stmt.executeUpdate(query);
			
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
	
}

