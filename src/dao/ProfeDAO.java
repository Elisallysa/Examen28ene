package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Alumno;
import models.Profesor;
import models.Usuario;

public class ProfeDAO extends AbstractDAO{
	
	public ArrayList<Profesor> getAll() {
		final String QUERY = "SELECT id, nombre "
				+ "FROM profesores";
		var profesores = new ArrayList<Profesor>();
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				var id = rs.getInt("id");
				var nombre = rs.getString("nombre");
				Profesor p = new Profesor(id, nombre);
				profesores.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profesores;
	}
	
	/**
	 * Busca el Profesor por su ID y si no lo encuentra devuelve NULL.
	 * @param idProfe
	 * @return
	 */
	public Profesor get(int idProfe) {
		final String QUERY = "SELECT id, nombre "
				+ "FROM profesores where id = " + idProfe;
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				var id = rs.getInt("id");
				var nombre = rs.getString("nombre");
				
				
				
				Profesor p = new Profesor(id, nombre);
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(Profesor p) {
		final String INSERT = "INSERT INTO profesores(`id`, `nombre`)\r\n" + "VALUES("+p.getId()+", '" + p.getNombre() + "')";
		try {
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isCreated(Profesor p) {
		final String QUERY = "SELECT nombre FROM profesores "+
							"where nombre = '" + p.getNombre()+ "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
