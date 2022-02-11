package com.bancodequestoes.controllers;

import com.bancodequestoes.application.MenuAlunoApplication;
import com.bancodequestoes.application.MenuProfessorApplication;
import com.bancodequestoes.model.entities.Questao;
import com.bancodequestoes.model.entities.Relatorio;
import com.bancodequestoes.model.entities.Tema;
import com.bancodequestoes.model.services.BancoDeQuestoes;
import com.bancodequestoes.model.services.TemaServices;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CadastroQuestaoController implements Initializable {
  public TextArea enunciado;
  public TextArea resposta;
  public ComboBox<String> temas;
  public ToggleGroup acesso;
  public Button btCadastrar;

  private BancoDeQuestoes bancoDeQuestoes = new BancoDeQuestoes();

  private TemaServices temaServices = new TemaServices();

  public void cadastrar() {
    if (checkFields()) {
      Questao questao = new Questao();
      questao.setEnunciado(enunciado.getText());
      questao.setResposta(resposta.getText());
      questao.setPublica("Pública".equals(((RadioButton) acesso.getSelectedToggle()).getText()));
      Tema tema = temaServices.pesquisaTemaNome(temas.getSelectionModel().getSelectedItem());
      questao.addTema(tema);
      questao.setRelatorio(new Relatorio());
      bancoDeQuestoes.insereQuestao(questao);
      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setContentText("Questão cadastrada com sucesso!");
      a.show();
      cleanFields();
    } else {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Há campos não preenchidos!");
      a.show();
    }
  }

  private void cleanFields() {
    enunciado.setText("");
    resposta.setText("");
    temas.setSelectionModel(null);
  }

  private boolean checkFields() {
    return !(enunciado.getText().isEmpty()
        || resposta.getText().isEmpty()
        || temas.getSelectionModel().getSelectedItem() == null);
  }

  public void cadastrarTema() {
    TextInputDialog td = new TextInputDialog();
    td.setTitle("Cadastrar tema");
    td.setHeaderText("Digite o nome do tema:");
    td.showAndWait();
    String editorText = td.getEditor().getText();
    if (!editorText.isEmpty()) {
      if (temas.getItems().stream()
          .map(String::toLowerCase)
          .anyMatch(tema -> editorText.toLowerCase().equals(tema))) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Tema já existe!");
        a.show();
      } else {
        Tema tema = new Tema();
        tema.setNome(editorText);
        temaServices.insereTema(tema);
      }
    } else {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Nome do tema não preenchido!");
      a.show();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    temas.setItems(
        FXCollections.observableArrayList(
            temaServices.todosTemas().stream()
                .map(Tema::getNome)
                .filter(nome -> !nome.isEmpty())
                .collect(Collectors.toList())));
  }

  public void voltar() throws Exception {
    Stage stage = (Stage) btCadastrar.getScene().getWindow();
    if (Boolean.TRUE.equals(stage.getUserData())) {
      changeScene(stage, new MenuAlunoApplication());
    }
    else{
      changeScene(stage, new MenuProfessorApplication());
    }
  }

  private void changeScene(Stage stage, Application application) throws Exception {
    application.start(stage);
  }
}
