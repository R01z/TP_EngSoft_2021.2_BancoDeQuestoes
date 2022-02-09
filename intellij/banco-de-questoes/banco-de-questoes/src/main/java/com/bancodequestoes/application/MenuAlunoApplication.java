package com.bancodequestoes.application;

import com.bancodequestoes.WelcomeApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuAlunoApplication extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Boolean isAluno = true;
    Parent root = FXMLLoader.load(Objects.requireNonNull(WelcomeApplication.class.getResource("view/menu-aluno.fxml")));
    Scene scene = new Scene(root);
    stage.setUserData(isAluno);
    stage.setScene(scene);
    stage.show();
  }
}
