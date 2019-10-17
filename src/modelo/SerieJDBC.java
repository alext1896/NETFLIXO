package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utilidades.Utilidades;


public class SerieJDBC {
	private long idSerie , idGenero;
	private String nombreSerie, descripcionSerie;
	
	public SerieJDBC(long idSerie, String nombreSerie, String descripcionSerie, long idGenero) {
		this.idSerie = idSerie;
		this.nombreSerie = nombreSerie;
		this.descripcionSerie = descripcionSerie;
		this.idGenero = idGenero;
	}

	public SerieJDBC() {

	}

	SerieJDBC obtenerSerie (long idSerie) throws IOException {
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
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombre");
				descripcionSerie = rs.getString("descripcion");
				idGenero = rs.getLong("idgenero");
				
				serie = new SerieJDBC (idSerie, nombreSerie, descripcionSerie, idGenero);
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

	
	public SerieJDBC crearSerie (String nombreSerie, long idGenero, String descripcionSerie) {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		SerieJDBC serie = null;
		String query = "insert into series values ('" +nombreSerie + "', " + idGenero + ", '"+ descripcionSerie +"', " + idGenero + ")";
		
		try {
			
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			stmt.execute(query);
			
			serie = new SerieJDBC (idSerie, nombreSerie,  descripcionSerie, idGenero);
			
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
	
	public boolean eliminarSerie (long idSerie) {
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
	
	
	public ArrayList <SerieJDBC> obtenerCatalogoSeries (){
		
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		SerieJDBC serie = null;
		String query = "SELECT * FROM series";
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeQuery(query);
			
			
			// Recuperacion de los datos del ResultSet
			ArrayList <SerieJDBC> listaSeries = new ArrayList<SerieJDBC> ();
			while (rs.next()) {
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombre");
				descripcionSerie = rs.getString("descripcion");
				idGenero = rs.getLong("idgenero");
				
				serie = new SerieJDBC (idSerie, nombreSerie, descripcionSerie, idGenero);
				
				listaSeries.add(serie);
			} 
			
			return listaSeries;
			
			/*for (int i = 0; i < listaSeries.size(); i++) {
				System.out.println(listaSeries.get(i).toString());
				System.out.println("-------------------------------------------------------------");
			}*/
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
	

	public int obtenerNumeroSeries () {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;

		String query = "SELECT COUNT(idSerie) 'NUMERO' FROM series";
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeQuery(query);
			
			
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
		return 0;
	}
	
	/*NO ENTIENDO COMO SACAR LAS SERIES POR NOMBRE, PORQUE NO SE DEBERIAN DE REPETIR LOS NOMBRES DE LAS PELICULAS
	 * EN TODO CASO, SE MOSTRARIAN LAS PELICULAS QUE CONTENGAN LA PALABRA CLAVE INTRODUCIDA. POR EJEMPLO:
	 * SE INTRODUCE JOKER, PUES MOSTRAR JOKER, JOKER 2, JOKER 3
	 * PREGUNTAR AL PROFESOR... XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
	 */
	
	public ArrayList <SerieJDBC> buscarSeriesPorNombre (String nombre, int index, int count) {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;

		String query = "SELECT * FROM series WHERE nombre = '" + nombre + "'";
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeQuery(query);
			
			ArrayList <SerieJDBC> listaSeries = new ArrayList <SerieJDBC> ();
			
			SerieJDBC serie = null;
			
			int contador = 0;
			
			while (rs.next() && contador < count) {
				idSerie = rs.getLong("idSerie");
				idGenero = rs.getLong("idGenero");
				nombreSerie = rs.getString("nombre");
				descripcionSerie = rs.getString("descripcion");
				
				serie = new SerieJDBC (idSerie, nombreSerie, descripcionSerie, idGenero);
				
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

	
	public ArrayList <SerieJDBC> obtenerSeriesPorGenero (long idGenero, int index, int count) {
		
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		String query = "SELECT * FROM series WHERE idGenero = " +idGenero;
		SerieJDBC serie = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un ResultSet
			rs = stmt.executeQuery(query);
			
			ArrayList <SerieJDBC> listaSeries = new ArrayList<SerieJDBC> ();
			int contador = 0;
			
			while (rs.next() && contador < count) {
				idSerie = rs.getLong("idSerie");
				nombreSerie = rs.getString("nombre");
				descripcionSerie = rs.getString("descripcion");
				idGenero = rs.getLong("idgenero");
				
				serie = new SerieJDBC (idSerie, nombreSerie, descripcionSerie, idGenero);
				
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
	
	public SerieJDBC editarSerie (SerieJDBC serie) {
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		int rs;
		SerieJDBC serieEditado= serie;
		String query = "UPDATE series SET idSerie = " + serieEditado.getIdSerie() + ", nombre = '" + serieEditado.getNombreSerie() + 
				"', descripcion = '" + serieEditado.getDescripcionSerie() + "', idGenero = " + serieEditado.getIdGenero() + ";";
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeUpdate(query);
			
			while (rs > 0) {
				return serieEditado;
			}
			return null;

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
	
	
	
	
	
	public String toString () {
		String toString = "\n IdSerie: " + idSerie + "\n Nombre: " + nombreSerie + "\n Descripcion: " + descripcionSerie + "\n idGenero: " + idGenero;
		
		return toString;
	}
	
	public long getIdSerie () {
		return idSerie;
	}
	
	public long getIdGenero () {
		return idGenero;
	}
	
	public String getNombreSerie () {
		return nombreSerie;
	}
	
	public String getDescripcionSerie () {
		return descripcionSerie;
	}
	

	

}

