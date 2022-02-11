package com.bancodequestoes.application;

import com.bancodequestoes.WelcomeApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuProfessorApplication extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    Boolean isAluno = false;
    Parent root = FXMLLoader.load(Objects.requireNonNull(WelcomeApplication.class.getResource("view/menu-professor.fxml")));
    Scene scene = new Scene(root);
    stage.setUserData(isAluno);
    stage.setScene(scene);
    stage.show();
  }
}
