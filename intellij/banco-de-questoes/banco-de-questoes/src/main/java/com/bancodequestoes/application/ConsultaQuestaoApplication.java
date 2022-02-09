package com.bancodequestoes.application;

import com.bancodequestoes.WelcomeApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ConsultaQuestaoApplication extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root =
        FXMLLoader.load(
            Objects.requireNonNull(
                WelcomeApplication.class.getResource("view/consulta-questao.fxml")));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
