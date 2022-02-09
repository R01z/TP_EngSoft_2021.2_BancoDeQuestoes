package com.bancodequestoes.controllers;

import com.bancodequestoes.application.CadastroQuestaoApplication;
import com.bancodequestoes.application.ConsultaQuestaoApplication;
import com.bancodequestoes.application.LoginApplication;
import com.bancodequestoes.model.entities.Tema;
import com.bancodequestoes.model.services.TemaServices;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class MenuProfessorController {
  public Pane pnCadQuestao;
  public Pane pnConsQuestao;
  public Pane pnCadTema;

  private TemaServices temaServices = new TemaServices();

  public void cadastrarTema() {
    String textInput = textInputDialog();
    if (!textInput.isEmpty()) {
      List<String> temas =
          temaServices.todosTemas().stream()
              .map(tema -> tema.getNome().toLowerCase())
              .collect(Collectors.toList());
      if (temas.stream().anyMatch(tema -> textInput.toLowerCase().equals(tema))) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Tema já existe!");
        a.show();
      } else {
        Tema tema = new Tema();
        tema.setNome(textInput);
        temaServices.insereTema(tema);
      }
    } else {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Nome do tema não preenchido!");
      a.show();
    }
  }

  private String textInputDialog() {
    TextInputDialog td = new TextInputDialog();
    td.setTitle("Cadastrar tema");
    td.setHeaderText("Digite o nome do tema:");
    td.showAndWait();
    return td.getEditor().getText();
  }

  private void changeScene(Application application) throws Exception {
    Stage stage = (Stage) pnConsQuestao.getScene().getWindow();
    application.start(stage);
  }

  public void cadastroQuestao() throws Exception {
    changeScene(new CadastroQuestaoApplication());
  }


  public void sair() throws Exception {
    changeScene(new LoginApplication());
  }

  public void consultaQuestao() throws Exception {
    changeScene(new ConsultaQuestaoApplication());
  }
}
