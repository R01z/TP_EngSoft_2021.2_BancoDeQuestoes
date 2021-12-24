package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.Questao;
import model.dao.QuestaoDao;

public class QuestaoDaoJDBC implements QuestaoDao{

	private Connection conn;
	
	public QuestaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Questao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Questao "
					+ "(enunciado, temas, resposta, publica) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getEnunciado());
			st.setString(2, obj.getTemas());
			st.setString(3, obj.getResposta());
			st.setBoolean(4, obj.getPublica());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
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
			st = conn.prepareStatement("DELETE FROM Questao WHERE Id = ?");
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

	private Questao instanciaQuestao(ResultSet rs) throws SQLException{
		Questao obj = new Questao();
		obj.setIdQuestao(rs.getInt("Id"));
		obj.setEnunciado(rs.getString("enunciado"));
		obj.setTemas(rs.getString("temas"));
		obj.setResposta(rs.getString("Resposta"));
		obj.setPublica(rs.getBoolean("publica"));
		return obj;
	}
	
	@Override
	public List<Questao> findByTemas(enum temas, bool publica) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Questao.* "
					+ "FROM Questao "
					+ "WHERE Questao.temas = ? AND Questao.publica = ?");
			st.setString(1, temas);
			st.setBoolean(2, publica);
			rs = st.executeQuery();
			
			List<Questao> lista = new ArrayList<>();
			
			while(rs.next()) {
				Questao obj = instanciaQuestao(rs);
				lista.add(obj);
			}
			return lista;
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
