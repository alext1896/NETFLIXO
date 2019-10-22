package utilidades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Utilidades {
	public String dbms = "mysql";
	public String dbName =  "alex";
	public String userName = "alex";
	public String password = "Mercedes1896.";
	public String urlString;
	private static final String PROPERTIES_FILE="/datos/usuarios/alumnos/jose.guapache/Escritorio/AccesoDatos/transparencias/jdbc/mysql-properties.xml";
	private Properties prop;
	private String driver;
	private String serverName;
	private int portNumber;
	
	public Utilidades()
			throws FileNotFoundException, IOException,
			InvalidPropertiesFormatException {
		super();
		this.setProperties(PROPERTIES_FILE);
	}
	
	private void setProperties(String fileName) throws IOException, InvalidPropertiesFormatException {
		this.prop = new Properties();
	    prop.loadFromXML(Files.newInputStream(Paths.get(fileName)));

		this.dbms = this.prop.getProperty("dbms");
		this.driver = this.prop.getProperty("driver");
		this.dbName = this.prop.getProperty("database_name");
		this.userName = this.prop.getProperty("user_name");
		this.password = this.prop.getProperty("password");
		this.serverName = this.prop.getProperty("server_name");
		this.portNumber = Integer
				.parseInt(this.prop.getProperty("port_number"));
		
	}
	
	public Connection getConnection() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		if (this.dbms.equals("mysql")) {
			/* Solicito a DriverManager una conexion con la base de datos */
			/*
			 * Para identificar el controldador a usar se le proporciona una
			 * URL, si no lo encuentra lanza SQLException
			 */
			/* formato de URL: jdbc:[host][:port]/[database] */
			/*
			 * La URL varia segun el gestor de BD,
			 * jdbc:mysql://127.0.0.1:3306/libros,
			 * jdbc:oracle:thin:@192.168.239.142:1521:libros
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/netflixo?serverTimezone=UTC", "alex", "Mercedes1896.");
		} else if (this.dbms.equals("derby")) {
			conn = DriverManager.getConnection("jdbc:" + this.dbms + ":"
					+ this.dbName + ";create=true", connectionProps);
		}
		System.out.println("Connectado a BD");
		System.out.println("-------------------------------------------------------------");
		return conn;
	}
	
	public static void closeConnection(Connection connArg) {
		try {
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}

}
