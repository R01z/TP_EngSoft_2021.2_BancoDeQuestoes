package com.bancodequestoes.controllers;

import com.bancodequestoes.application.CadastroApplication;
import com.bancodequestoes.application.MenuAlunoApplication;
import com.bancodequestoes.application.MenuProfessorApplication;
import com.bancodequestoes.config.util.DaoUtil;
import com.bancodequestoes.model.entities.Aluno;
import com.bancodequestoes.model.entities.Professor;
import com.bancodequestoes.model.services.AlunoServices;
import com.bancodequestoes.model.services.ProfessorServices;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
  public TextField login;
  public PasswordField senha;
  public ToggleGroup nivel;

  private AlunoServices alunoServices = new AlunoServices();
  private ProfessorServices professorServices = new ProfessorServices();

  @FXML private Hyperlink linkCadastro;

  public void cadastrar(MouseEvent mouseEvent) throws Exception {
    Stage stage = (Stage) linkCadastro.getScene().getWindow();
    CadastroApplication cadastroApplication = new CadastroApplication();
    cadastroApplication.start(stage);
  }

  public void entrar(MouseEvent mouseEvent) throws Exception {
    String senhaText = senha.getText();
    String loginText = login.getText();
    if (!(senhaText.isEmpty() || loginText.isEmpty())) {
      if ("Aluno".equals(((RadioButton) nivel.getSelectedToggle()).getText())) {
        Aluno aluno =
            alunoServices.pesquisaAlunoNomeUsrSenha(loginText, DaoUtil.encryptPass(senhaText));
        if (aluno != null) {
          alertLogadoComSucesso(aluno.getNome());
          changeScene(new MenuAlunoApplication());
        } else {
          alertErrorNomeUsrSenha();
        }
      } else {
        Professor professor =
            professorServices.pesquisaProfessorNomeUsrSenha(
                loginText, DaoUtil.encryptPass(senhaText));
        if (professor != null) {
          alertLogadoComSucesso(professor.getNome());
          changeScene(new MenuProfessorApplication());
        } else {
          alertErrorNomeUsrSenha();
        }
      }

    } else {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Há campos não preenchidos!");
      a.show();
    }
  }

  public void alertErrorNomeUsrSenha() {
    Alert a = new Alert(Alert.AlertType.ERROR);
    a.setHeaderText("Nome de usuario e/ou senha invalida!");
    a.show();
  }

  public void alertLogadoComSucesso(String nome) {
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    a.setHeaderText("Seja bem vindo(a), " + nome + "!");
    a.show();
  }

  private void changeScene(Application application) throws Exception {
    Stage stage = (Stage) login.getScene().getWindow();
    application.start(stage);
  }
}
