package metodos;

import java.sql.*;

public class ConectarCarrito{

	 private Connection con = null;

	    public Connection getCon() throws SQLException {

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (Exception e) {
	            throw new SQLException("Driver de BD no disponible en classpath");
	        }

	        if (con == null || con.isClosed()) {
	            con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/proyectofinal?autoReconnect=true&" +
	                            "user=root&password=");
//	            System.out.println("Conexion exitosa");
	        }

	        return con;
	    }

	    public void cerrar() {

	        if (con != null) {
	            try {
	                con.close();
	            } catch (Exception ignored) {
	                // aqui solo ignoramos, ya que no nos importa si da error al momento de cerrar la conexion
	            }
	        }
	    }


}
