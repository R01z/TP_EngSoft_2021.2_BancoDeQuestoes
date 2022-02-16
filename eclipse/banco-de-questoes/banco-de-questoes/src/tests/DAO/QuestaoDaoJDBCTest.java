import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import com.bancodequestoes.model.etites.Tema;
import com.bancodequestoes.model.etites.Questao;
import com.bancodequestoes.model.etites.Relatorio;
import com.bancodequestoes.model.dao.QuestaoDaoJDBC;

public class QuestaoDaoJDBCTest extends TestCase{
	private Tema tema  = new Tema("Java", null);
	private Questao questao  = new Questao(null, "Um enunciado", "Uma resposta", Boolean.TRUE, tema);
	private Questao questaoResultado = new Questao();
	private QuestaoDao dao = DaoFactory.createQuestaoDao();
	private Relatorio relatorio = new Relatorio(null, "2021/2", "DCC333","Prova", 7.5);
	
	
	@Test
	public void testInsert() {
		dao.insert(questao);
	}
	
	@Test
	public void testInsertRelatorio() {
		questao.setRelatorio(relatorio);
		dao.insertRelatorio(questao);
	}
	
	@Test
	public void testInsertTemaxQuestao() {
		dao.insertTemaxQuestao(questao);
	}
	
	@Test
	public void testFindByTemas() {
		List<Questao> questoes = new ArrayList<>();
		questoes = dao.findByTemas("Java", Boolean.TRUE);
		assertTrue(questoes.size()>0);
		assertTrue(questoes.getRelatorio().getSemestre() == "2021/2");
		assertTrue(questoes.getTema().getNome() == "Java");
		
		questoes.clear();
		
		questoes = dao.findByTemas("Java", Boolean.FALSE);
		assertTrue(questoes.size()==0);
	}
}
