package com.bancodequestoes.model.dao.impl;

import com.bancodequestoes.config.db.DB;
import com.bancodequestoes.config.db.DbException;
import com.bancodequestoes.model.dao.TemaDao;
import com.bancodequestoes.model.entities.Tema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TemaDaoJDBC implements TemaDao {

	private Connection conn;
	
	public TemaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Tema obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Tema "
					+ "(nome) "
					+ "VALUES "
					+ "(?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdTema(id);
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
			st = conn.prepareStatement("DELETE FROM Tema WHERE idTema = ?");
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
	public Tema findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Tema.* "
					+ "FROM Tema "
					+ "WHERE Tema.idTema = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Tema tema = new Tema();
				tema.setIdTema(rs.getInt("id"));
				tema.setNome(rs.getString("nome"));
				return tema;
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
	public List<Tema> findTemas(){
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Tema> temas = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT Tema.* FROM Tema");
			rs = st.executeQuery();
			while(rs.next()) {
				Tema tema = new Tema(rs.getString("nome"), rs.getInt("idTema"));
				temas.add(tema);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		return temas;
	}

	@Override
	public Tema findByNome(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT Tema.* "
											+ "FROM Tema "
											+ "WHERE Tema.nome = ?");
			st.setString(1, nome);
			rs = st.executeQuery();
			if(rs.next()) {
				Tema tema = new Tema();
				tema.setIdTema(rs.getInt("idTema"));
				tema.setNome(rs.getString("nome"));
				return tema;
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
