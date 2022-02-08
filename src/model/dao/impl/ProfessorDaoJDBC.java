package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.dao.ProfessorDao;
import model.entities.Professor;

public class ProfessorDaoJDBC implements ProfessorDao{
	
	private Connection conn;
	
	public ProfessorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Professor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Professor "
					+ "(nomeUsr, nome) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNomeUsr());
			st.setString(2, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdUsr(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("ERRO! NENHUMA LINHA AFETADA!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Professor WHERE idUsr = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Professor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Professor.* "
					+ "FROM Professor "
					+ "WHERE Professor.idUsr = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Professor prof = new Professor();
				prof.setIdUsr(rs.getInt("idUsr"));
				prof.setNomeUsr(rs.getString("nomeUsr"));
				prof.setNome(rs.getString("nome"));
				return prof;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public Professor findByUsr(String usr) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Professor.* "
					+ "FROM Professor "
					+ "WHERE Professor.nomeUsr = ?");
			st.setString(1, usr);
			rs = st.executeQuery();
			if(rs.next()) {
				Professor prof = new Professor();
				prof.setIdUsr(rs.getInt("idUsr"));
				prof.setNomeUsr(rs.getString("nomeUsr"));
				prof.setNome(rs.getString("nome"));
				return prof;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
