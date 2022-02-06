package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Usuario;

public class UsuarioDAO extends AbstractDAO{	

	public boolean consulta(Usuario usuario) {
		final String QUERY = "SELECT username FROM users "+
				"where username = '" + usuario.getUsername() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean login(Usuario usuario) {
		final String QUERY = "SELECT username, password FROM users "+
							"where username = '" + usuario.getUsername() + "' and "+
							"password = '" + usuario.getPassword() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void register(Usuario usuario) {
		final String INSERT = "INSERT INTO users (username, password)"
				+ " VALUES ('"+ usuario.getUsername() + "', '"+ usuario.getPassword() +"');";
		try {
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
