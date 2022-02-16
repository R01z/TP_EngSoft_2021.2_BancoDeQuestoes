import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import com.bancodequestoes.model.etites.Tema;
import com.bancodequestoes.model.dao.TemaDaoJDBC;

public class TamaDaoJDBCTest extends TestCase{
	private Tema tema  = new Tema("Java", null);
	private Tema temaResultado = new Tema();
	private TemaDao dao = DaoFactory.createTemaDao();
	
	@Test //Precisa ser executado primeiro
	public void testInsert() {
		dao.insert(tema);
	}
	
	public void testFindByNome() {
		temaResultado = dao.findByNome("Java");
		assertEquals(temaResultado.getNome(), "Java");
	}
	
	public void testFindTemas() {
		List<Tema> temas = new ArrayList<>();
		temas = dao.findTemas();
		assertTrue(temas.size()>0);
	}
}
