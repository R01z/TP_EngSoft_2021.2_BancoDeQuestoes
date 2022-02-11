package com.bancodequestoes.controllers;

import com.bancodequestoes.application.MenuAlunoApplication;
import com.bancodequestoes.application.MenuProfessorApplication;
import com.bancodequestoes.model.entities.Questao;
import com.bancodequestoes.model.entities.Tema;
import com.bancodequestoes.model.services.BancoDeQuestoes;
import com.bancodequestoes.model.services.TemaServices;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConsultaQuestaoController implements Initializable {

  public Text enunciado;
  public Text resposta;
  public Button antQuestao;
  public Button proxQuestao;
  public Button voltar;

  public Pane pane;
  public Text txtProcurar;
  public Button btSim;
  public Text txtLoading;

  public Tema tema;
  public List<Questao> questoes;
  public GridPane gridPane;
  public CheckBox chkBox;
  public Text lblEnunciado;
  public Text lblResposta;

  private int questaoAtual = 0;
  private TemaServices temaServices = new TemaServices();

  private BancoDeQuestoes bancoDeQuestoes = new BancoDeQuestoes();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    List<Tema> temas = temaServices.todosTemas();
    List<String> temaNomes = temas.stream().map(Tema::getNome).collect(Collectors.toList());
    ChoiceDialog<String> cd = new ChoiceDialog<>(temaNomes.get(0), temaNomes);
    cd.setHeaderText("Escolha um tema:");
    cd.showAndWait();
    String temaNomeSelecionado = cd.getSelectedItem();
    this.tema =
        temas.stream().filter(t -> temaNomeSelecionado.equals(t.getNome())).findFirst().get();
    txtProcurar.setText("Procurar questões relacionadas ao tema "+tema.getNome()+"?");
    txtProcurar.setTextAlignment(TextAlignment.CENTER);
  }

  public boolean isAluno() {
    Boolean isAluno = (Boolean) ((Stage) pane.getScene().getWindow()).getUserData();
    return isAluno;
  }

  public void verResposta() {
    if (((GaussianBlur) resposta.getEffect()).getRadius() != 0.0) {
      resposta.setEffect(new GaussianBlur(0.0));
    } else {
      resposta.setEffect(new GaussianBlur());
    }
  }

  public void antQuestao() {
    questaoAtual--;
    enunciado.setText(questoes.get(questaoAtual).getEnunciado());
    resposta.setText(questoes.get(questaoAtual).getResposta());
    proxQuestao.setDisable(false);
    proxQuestao.setVisible(true);
    if (questaoAtual == 0) {
      antQuestao.setDisable(true);
      antQuestao.setVisible(false);
    }
  }

  public void proxQuestao() {
    questaoAtual++;
    enunciado.setText(questoes.get(questaoAtual).getEnunciado());
    resposta.setText(questoes.get(questaoAtual).getResposta());
    antQuestao.setDisable(false);
    antQuestao.setVisible(true);
    if (questaoAtual == questoes.size() - 1) {
      proxQuestao.setDisable(true);
      proxQuestao.setVisible(false);
    }
  }

  public void voltar() throws Exception {
    Stage stage = (Stage) enunciado.getScene().getWindow();
    if (Boolean.TRUE.equals(stage.getUserData())) {
      changeScene(stage, new MenuAlunoApplication());
    }
    else{
      changeScene(stage, new MenuProfessorApplication());
    }
  }

  private void changeScene(Application application) throws Exception {
    Stage stage = (Stage) enunciado.getScene().getWindow();
    application.start(stage);
  }

  private void changeScene(Stage stage, Application application) throws Exception {
    application.start(stage);
  }
  public void run() {
    txtProcurar.setVisible(false);
    btSim.setVisible(false);
    lblResposta.setVisible(true);
    lblEnunciado.setVisible(true);
    txtLoading.setVisible(true);
    boolean isAluno = true;
    try {
      isAluno = (Boolean) voltar.getScene().getWindow().getUserData();
    } catch (NullPointerException ignored) {

    }
    questoes = bancoDeQuestoes.pesquisaQuestao(tema, isAluno);
    txtLoading.setVisible(false);

    chkBox.setVisible(true);
    gridPane.setVisible(true);
    GaussianBlur blur = new GaussianBlur();
    resposta.setEffect(blur);
    if (!questoes.isEmpty()) {

      if (questoes.size() > 1) {
        proxQuestao.setDisable(false);
        proxQuestao.setVisible(true);
      }
      enunciado.setText(questoes.get(questaoAtual).getEnunciado());
      resposta.setText(questoes.get(questaoAtual).getResposta());
    } else {
      if (isAluno) {
        try {
          changeScene(new MenuAlunoApplication());
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        try {
          changeScene(new MenuProfessorApplication());
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.setHeaderText("Não há questoes relacionadas ao tema!");
      a.show();
    }
  }
}
