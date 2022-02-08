package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import model.entities.Aluno;
import model.entities.Aluno;
import model.dao.AlunoDao;

public class AlunoDaoJDBC implements AlunoDao{
	
	private Connection conn;
	
	public AlunoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Aluno obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Aluno "
					+ "(nomeUsr, matricula, nome) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNomeUsr());
			st.setLong(2, obj.getMatricula());
			st.setString(3, obj.getNome());
			
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
			st = conn.prepareStatement("DELETE FROM Aluno WHERE idUsr = ?");
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
	public Aluno findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Aluno.* "
					+ "FROM Aluno "
					+ "WHERE Aluno.idUsr = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setIdUsr(rs.getInt("idUsr"));
				aluno.setNomeUsr(rs.getString("nomeUst"));
				aluno.setMatricula(rs.getLong("matricula"));
				aluno.setNome(rs.getString("nome"));
				return aluno;
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
	public Aluno findByMatricula(long matricula) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Aluno.* "
					+ "FROM Aluno "
					+ "WHERE Aluno.matricula = ?");
			st.setLong(1, matricula);
			rs = st.executeQuery();
			if(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setIdUsr(rs.getInt("id"));
				aluno.setNomeUsr(rs.getString("nomeUsr"));
				aluno.setMatricula(rs.getLong("matricula"));
				aluno.setNome(rs.getString("nome"));
				return aluno;
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
	public Aluno findByUsr(String usr) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Aluno.* "
					+ "FROM Aluno "
					+ "WHERE Aluno.nomeUsr = ?");
			st.setString(1, usr);
			rs = st.executeQuery();
			if(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setIdUsr(rs.getInt("idUsr"));
				aluno.setNomeUsr(rs.getString("nomeUsr"));
				aluno.setNome(rs.getString("nome"));
				return aluno;
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
