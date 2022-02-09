package com.bancodequestoes.model.dao.impl;

import com.bancodequestoes.config.db.DB;
import com.bancodequestoes.config.db.DbException;
import com.bancodequestoes.model.dao.ProfessorDao;
import com.bancodequestoes.model.entities.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfessorDaoJDBC implements ProfessorDao {

	private static final String SELECT_PROFESSOR = "SELECT Professor.* FROM Professor WHERE Professor.";
	private static final String ID_USR = "idUsr";
	private static final String NOME_USR = "nomeUsr";
	private static final String SENHA = "senha";

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
							+ "(nomeUsr, nome, senha) "
											+ "VALUES "
											+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNomeUsr());
			st.setString(2, obj.getNome());
			st.setString(3, obj.getSenha());

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
	public Professor findByNomeUsrSenha(String nomeUsr, String senha) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(SELECT_PROFESSOR + NOME_USR + " = ? AND "+SENHA+" = ?");
			st.setString(1, nomeUsr);
			st.setString(2, senha);
			rs = st.executeQuery();
			if (rs.next()) {
				Professor prof = new Professor();
				prof.setIdUsr(rs.getInt("idUsr"));
				prof.setNomeUsr(rs.getString("nomeUsr"));
				return prof;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	@Override
	public Boolean checkByNomeUsr(String nomeUsr) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
							"SELECT COUNT(1) "
											+ "FROM Professor "
											+ "WHERE Professor.nomeUsr = ?");
			st.setString(1, nomeUsr);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 1;
			}
			return false;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}


}
