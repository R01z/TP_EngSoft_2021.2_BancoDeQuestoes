package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Questao;
import model.entities.Tema;
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
					+ "(enunciado, resposta, publica) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getEnunciado());
			st.setString(2, obj.getResposta());
			st.setBoolean(3, obj.getPublica());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdQuestao(id);
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
		obj.setResposta(rs.getString("Resposta"));
		obj.setPublica(rs.getBoolean("publica"));
		instanciaTemas(obj);
		return obj;
	}
	
	private void instanciaTemas(Questao obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Tema.*"
					+ " FROM Tema"
					+ " JOIN questaoxtema"
					+ " WHERE questaoxtema.idQuestao = ? AND questaoxtema.idTema = Tema.idTema");
			st.setInt(1, obj.getIdQuestao());
			rs = st.executeQuery();
			
			while(rs.next()) {
				Tema tema = new Tema();
				tema.setIdTema(rs.getInt("idTema"));
				tema.setNome(rs.getString("nome"));
				obj.addTema(tema);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	//Função que busca apenas questões públicas no sistema
	@Override
	public List<Questao> findByTemas(Tema temas, Boolean publica) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			if(publica) {
				st = conn.prepareStatement(
						"SELECT Questao.*"
						+ " FROM Questao"
						+ " JOIN questaoxtema"
						+ " WHERE Questao.publica = 1 AND questaoxtema.idTema = ? AND questaoxtema.idQuestao = Questao.idQuestao");
				st.setInt(1, temas.getIdTema());
				rs = st.executeQuery();
			}
			else {
				st = conn.prepareStatement(
						"SELECT Questao.*"
						+ " FROM Questao"
						+ " JOIN questaoxtema"
						+ " WHERE questaoxtema.idTema = ? AND questaoxtema.idQuestao = Questao.idQuestao");
				st.setInt(1, temas.getIdTema());
				rs = st.executeQuery();
			}
			
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
