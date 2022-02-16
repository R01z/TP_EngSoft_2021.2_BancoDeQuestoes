import static org.junit.Assert.*;

import org.junit.Test;
import com.bancodequestoes.model.etites.Aluno;
import com.bancodequestoes.model.dao.AlunoDaoJDBC;

public class AlunoDaoJDBCTest extends TestCase{
	
	private Aluno aluno  = new Aluno("Igor", null, 123456789);
	private Aluno alunoResultado = new Aluno();
	private AlunoDao dao = DaoFactory.createAlunoDao();
	
	@Test //Precisa ser executado primeiro
	public void testInsert() {
		dao.insert(aluno);
	}
	
	@Test
	public void testCheckByNomeUsr() {
		Boolean retorno = dao.checkByNomeUsr("Igor");
		assertTrue(retorno == Boolean.TRUE);
	}
	
	@Test
	public void testFindByMatricula() {
		alunoResultado = dao.findByMatricula(12345678);
		assertTrue(alunoResultado.getNome() == "Igor");
	}
	
	@Test
	public void testFindById() {
		alunoResultado = dao.findById(aluno.getId());
		assertTrue(alunoResultado.getNome() == "Igor");
	}
}
