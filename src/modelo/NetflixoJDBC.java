package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Utilidades;

public class NetflixoJDBC {
	
	// Consultas a realizar en BD
	private static final String SELECT_CAFES_QUERY = "select CAF_NOMBRE, PROV_ID, PRECIO, VENTAS, TOTAL from CAFES";
	// En una consulta parametrizada ponemos interrogaciones en los valores que aun desconocemos
	private static final String UPDATE_VENTAS_CAFE = "update CAFES set VENTAS = ? where CAF_NOMBRE = ?";
	
	
	public void verTabla() throws IOException {

		
		/* Conexion a la Base de Datos */
		Connection con = null;
		/* Sentencia sql */
		Statement stmt = null;
		/* Conjunto de Resultados a obtener de la sentencia sql */
		ResultSet rs = null;
		
		try {
			con = new Utilidades().getConnection();
			// Creacion de la sentencia
			stmt = con.createStatement();
			// Ejecucion de la consulta y obtencion de resultados en un
			// ResultSet
			rs = stmt.executeQuery(SELECT_CAFES_QUERY);

			// Recuperacion de los datos del ResultSet
			while (rs.next()) {
				String coffeeName = rs.getString("CAF_NOMBRE");
				int supplierID = rs.getInt("PROV_ID");
				float PRECIO = rs.getFloat("PRECIO");
				int VENTAS = rs.getInt("VENTAS");
				int total = rs.getInt("TOTAL");
				System.out.println(coffeeName + ", " + supplierID + ", "+ PRECIO + ", " + VENTAS + ", " + total);
			}
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
		
	}

	/**
	 * M�todo que actualiza las ventas de un cafe con un PreparedStatement
	 * 
	 * @param cafe
	 * @param ventas
	 */
	public void actualizarVentasCafe(String cafe, int ventas) throws IOException {

		/* Conexi�n a la Base de Datos */
		Connection con = null;
		/* Sentencia Preparada sql */
		PreparedStatement stmt = null;
		
		try {
			con = new Utilidades().getConnection();

			// Creamos un objeto PreparedStatement pasondole nuestra consulta
			// parametrizada
			stmt = con.prepareStatement(UPDATE_VENTAS_CAFE);
			// Le damos valoes a los parametros de la consulta, indicando n�mero
			// de par�metro
			// Y utilizando un m�todo adecuado segun el tipo de datos
			stmt.setFloat(1, ventas);
			stmt.setString(2, cafe);

			// m�todo executeUpdate para ejecutar inserciones, actualizaciones y
			// borrado de datos
			stmt.executeUpdate();
			

		} catch (SQLException sqle) {
			// En una aplicacion real, escribo en el log y delego
			System.err.println(sqle.getMessage());
			
		} finally {
			try {
				// Liberamos todos los recursos pase lo que pase

				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					Utilidades.closeConnection(con);
				}
			} catch (SQLException sqle) {
				// En una aplicacion real, escribo en el log, no delego porque
				// es error al liberar recursos
			}
		}
	}	
}
