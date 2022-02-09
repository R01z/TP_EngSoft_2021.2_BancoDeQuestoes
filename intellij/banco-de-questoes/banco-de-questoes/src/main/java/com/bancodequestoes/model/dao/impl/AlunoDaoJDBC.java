package com.bancodequestoes.model.dao.impl;

import com.bancodequestoes.config.db.DB;
import com.bancodequestoes.config.db.DbException;
import com.bancodequestoes.model.dao.AlunoDao;
import com.bancodequestoes.model.entities.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoDaoJDBC implements AlunoDao {

  private Connection conn;

  public AlunoDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  private static final String SELECT_ALUNO = "SELECT Aluno.* FROM Aluno WHERE Aluno.";
  private static final String MATRICULA = "matricula";
  private static final String ID_USR = "idUsr";
  private static final String NOME_USR = "nomeUsr";
  private static final String SENHA = "senha";

  private Aluno alunoFromResultSet(ResultSet rs) throws SQLException {
    Aluno aluno = new Aluno();
    aluno.setIdUsr(rs.getInt(ID_USR));
    aluno.setNomeUsr(rs.getString(NOME_USR));
    aluno.setMatricula(rs.getLong(MATRICULA));
    return aluno;
  }

  @Override
  public int insert(Aluno obj) {
    PreparedStatement st = null;
    int rowsAffected = 0;
    try {
      st = conn.prepareStatement(
              "INSERT INTO Aluno "
                      + "(nomeUsr, matricula, nome, senha) "
                      + "VALUES "
                      + "(?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);
      st.setString(1, obj.getNomeUsr());
      st.setLong(2, obj.getMatricula());
      st.setString(3, obj.getNome());
      st.setString(4, obj.getSenha());
      rowsAffected = st.executeUpdate();
      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
          int id = rs.getInt(1);
          obj.setIdUsr(id);
        }
        DB.closeResultSet(rs);
      } else {
        throw new DbException("ERRO! NENHUMA LINHA AFETADA!");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
    return rowsAffected;
  }

  @Override
  public void deleteById(Integer id) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement("DELETE FROM Aluno WHERE idUsr = ?");
      st.setInt(1, id);
      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public Aluno findById(Integer id) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              SELECT_ALUNO + ID_USR + " = ?");
      st.setInt(1, id);
      rs = st.executeQuery();
      if (rs.next()) {
        return alunoFromResultSet(rs);
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
  public Aluno findByNomeUsrSenha(String nomeUsr, String senha) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(SELECT_ALUNO + NOME_USR + " = ? AND "+SENHA+" = ?");
      st.setString(1, nomeUsr);
      st.setString(2, senha);
      rs = st.executeQuery();
      if (rs.next()) {
        return alunoFromResultSet(rs);
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
  public Aluno findByMatricula(long matricula) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              SELECT_ALUNO + MATRICULA + " = ?");
      st.setLong(1, matricula);
      rs = st.executeQuery();
      if (rs.next()) {
        return alunoFromResultSet(rs);
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
                      + "FROM Aluno "
                      + "WHERE Aluno.nomeUsr = ?");
      st.setString(1, nomeUsr);
      rs = st.executeQuery();
      if (rs.next()) {
        return rs.getInt(1) == 1;
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
    return false;
  }
}
