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
import model.dao.QuestaoDao;
import model.entities.Questao;
import model.entities.Relatorio;
import model.entities.Tema;

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
					+ "(enunciado, resposta, publica, idRel) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getEnunciado());
			st.setString(2, obj.getResposta());
			st.setBoolean(3, obj.getPublica());
			st.setInt(4, obj.getRelatorio().getIdRel());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdQuestao(id);
					insertTemaxQuestao(obj);
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
	
	//Recebe uma questão e insere seu relatorio no sistema
	//Usar apenas se o relatorio não tiver sido criado ainda
	public void insertRelatorio(Questao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Relatorio "
					+ "(semestre, turma, atividade, notaMedia) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getRelatorio().getSemestre());
			st.setString(2, obj.getRelatorio().getTurma());
			st.setString(3, obj.getRelatorio().getAtividade());
			st.setDouble(4, obj.getRelatorio().getNotaMedia());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.getRelatorio().setIdRel(id);
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

	public void insertTemaxQuestao(Questao obj) {
		PreparedStatement st = null;
		try {
			for(int i=0;i<obj.getTemas().size();i++) {
				st = conn.prepareStatement(
						"INSERT INTO QuestaoxTema "
						+ "(idQuestao, idTema) "
						+ "VALUES "
						+ "(?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, obj.getIdQuestao());
				st.setInt(2, obj.getTemas().get(i).getIdTema());
				
				int rowsAffected = st.executeUpdate();
				
				if(rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
					DB.closeResultSet(rs);
				}
				else {
					throw new DbException("ERRO! NENHUMA LINHA AFETADA!");
				}
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
		instanciaRelatorio(obj);
		return obj;
	}
	
	//Recebe uma Questão, procura os temas dessa questão e instancia a lista de temas da questao
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
	
	//Recebe uma questão, procura o relatorio dessa questão no BD e instancia o relatorio na questao
	private void instanciaRelatorio(Questao obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Relatorio.*"
					+ " FROM Relatorio"
					+ " JOIN Questao"
					+ " WHERE Questao.idRel = Relatorio.idRel");
			rs = st.executeQuery();
			
			while(rs.next()) {
				Relatorio relatorio = new Relatorio();
				relatorio.setIdRel(rs.getInt("idRel"));
				relatorio.setSemestre(rs.getString("semestre"));
				relatorio.setTurma(rs.getString("turma"));
				relatorio.setAtividade(rs.getString("atividade"));
				relatorio.setNotaMedia(rs.getDouble("notaMedia"));
				obj.setRelatorio(relatorio);
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
	
	//Função que busca questões no BD, recebendo um tema e 1 para recuperar apenas questões públicas e 0 para todas
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
	
	//Questão que atualiza no BD o relatorio de uma questao
	public void updateRelatorio(Questao obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Relatorio "
					+ "SET semestre = ?, turma= ?, atividade = ?, notaMedia = ? "
					+ "WHERE Relatorio.idRel = ?");
			st.setString(1, obj.getRelatorio().getSemestre());
			st.setString(2, obj.getRelatorio().getTurma());
			st.setString(3, obj.getRelatorio().getAtividade());
			st.setDouble(4, obj.getRelatorio().getNotaMedia());
			st.setInt(5, obj.getRelatorio().getIdRel());
			
			st.executeUpdate();
		}
		catch(SQLException e) {throw new DbException(e.getMessage());}
		finally {
			DB.closeStatement(st);
		}
	}

}
