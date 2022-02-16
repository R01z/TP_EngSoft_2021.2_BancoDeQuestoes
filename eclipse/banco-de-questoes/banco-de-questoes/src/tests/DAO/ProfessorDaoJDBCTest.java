import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProfessorDaoJDBCTest extends TestCase{
	private Professor professor  = new Professor("Guilherme", null);
	private Professor professorResultado = new Professor();
	private ProfessorDao dao = DaoFactory.createProfessorDao();
	
	@Test //Precisa ser executado primeiro
	public void testInsert() {
		dao.insert(professor);
	}
	
	@Test
	public void testCheckByNomeUsr() {
		Boolean retorno = dao.checkByNomeUsr("Guilherme");
		assertTrue(retorno == Boolean.TRUE);
	}
	
	@Test
	public void testFindById() {
		professorResultado = dao.findById(professor.getId());
		assertTrue(professorResultado.getNome() == "Guilherme");
	}
}
