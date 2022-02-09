package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.entities.Questao;
import model.services.bancoDeQuestoes;

public class CadastroDeQuestoesController implements Initializable{

	private Questao questao;
	
	private bancoDeQuestoes banco;
	
	@FXML
	private TextField txtEnunciado;
	
	@FXML
	private TextField txtResposta;
	
	@FXML
	private RadioButton rBtSim;
	
	@FXML
	private RadioButton rBtNao;
	
	@FXML
	private Label labelErrorEnunciado;
	
	@FXML
	private Label labelErrorResposta;
	
	@FXML
	private Label labelErrorPublica;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCancelar;
	
	
	
	@FXML
	public void onBtSalvarAction() {
		questao = getFormData();
		
		banco.insereQuestao(questao);
	}
	
	private Questao getFormData() {
		Questao obj = new Questao();
		
		obj.setEnunciado(txtEnunciado.getText());
		obj.setResposta(txtResposta.getText());
		if(rBtSim.isSelected()) obj.setPublica(Boolean.TRUE);
		else if(rBtNao.isSelected()) obj.setPublica(Boolean.FALSE);
		return obj;
	}

	@FXML
	public void onBtCancelarAction() {
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
