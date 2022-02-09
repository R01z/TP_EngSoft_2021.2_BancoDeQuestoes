package com.bancodequestoes.controllers;

import com.bancodequestoes.application.LoginApplication;
import com.bancodequestoes.application.MenuAlunoApplication;
import com.bancodequestoes.application.MenuProfessorApplication;
import com.bancodequestoes.config.util.DaoUtil;
import com.bancodequestoes.model.entities.Aluno;
import com.bancodequestoes.model.entities.Professor;
import com.bancodequestoes.model.services.AlunoServices;
import com.bancodequestoes.model.services.ProfessorServices;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

  public TextField login;
  public TextField nome;
  public TextField txtSenha;
  public PasswordField senha;
  public Text txtNomeDisponivel;
  public Text txtNomeIndisponivel;
  public ToggleGroup nivel;

  public Boolean isAluno;

  private final AlunoServices alunoServices = new AlunoServices();
  private final ProfessorServices professorServices = new ProfessorServices();

  public void mostrarSenha(MouseEvent mouseEvent) {
    if (senha.isVisible()) {
      changeVisibleFields(senha, txtSenha);
    } else {
      changeVisibleFields(txtSenha, senha);
    }
  }

  private void changeVisibleFields(TextField visibleField, TextField invisibleField) {
    invisibleField.setText(visibleField.getText());
    visibleField.setVisible(false);
    invisibleField.setVisible(true);
  }

  public void cadastrar() throws Exception {
    if (fieldsAreOk()) {
      Application application = null;
      if (Boolean.TRUE.equals(isAluno)) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome.getText());
        aluno.setNomeUsr(login.getText());
        aluno.setSenha(gerarSenha());
        alunoServices.insereAluno(aluno);
        application = new MenuAlunoApplication();
      } else {
        Professor professor = new Professor();
        professor.setNome(nome.getText());
        professor.setNomeUsr(login.getText());
        professor.setSenha(gerarSenha());
        professorServices.insereProfessor(professor);
        application = new MenuProfessorApplication();
      }
      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setContentText("Olá, " + nome.getText() + ". Você foi cadastrado com sucesso!");
      a.show();
      changeScene(application);
    } else {
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setContentText("Há campos não preenchidos ou nome indisponivel!");
      a.show();
    }
  }

  private boolean fieldsAreOk() {
    return !(login.getText().isEmpty()
        || nome.getText().isEmpty()
        || senha.getText().isEmpty()
        || txtNomeIndisponivel.isVisible());
  }

  private String gerarSenha() {
    String senhaText = senha.getText();
    String txtSenhaText = txtSenha.getText();
    String finalSenha = senhaText.length() > txtSenhaText.length() ? senhaText : txtSenhaText;
    return DaoUtil.encryptPass(finalSenha);
  }

  private void changeScene(Application application) throws Exception {
    Stage stage = (Stage) login.getScene().getWindow();
    application.start(stage);
  }

  public void setAsVisibleNomeIndisponivel(String loginText) {
    boolean nomeIndisponivel =
        isAluno
            ? alunoServices.checaNomeUsuario(loginText)
            : professorServices.checaNomeUsuario(loginText);
    if (nomeIndisponivel) {
      txtNomeDisponivel.setVisible(false);
      txtNomeIndisponivel.setVisible(true);

    } else {
      txtNomeIndisponivel.setVisible(false);
      txtNomeDisponivel.setVisible(true);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    isAluno = "Aluno".equals(((RadioButton) nivel.getSelectedToggle()).getText());

    nivel
        .selectedToggleProperty()
        .addListener(
            (ov, t, t1) ->
                isAluno =
                    "Aluno"
                        .equals(((RadioButton) t1.getToggleGroup().getSelectedToggle()).getText()));

    login
        .focusedProperty()
        .addListener(
            (ov, oldV, newV) -> {
              if (!newV) {
                String loginText = login.getText();
                if (!loginText.isEmpty()) {
                  setAsVisibleNomeIndisponivel(loginText);
                } else {
                  txtNomeIndisponivel.setVisible(false);
                  txtNomeDisponivel.setVisible(false);
                }
              }
            });
  }

  public void voltar() throws Exception {
    changeScene(new LoginApplication());
  }
}
