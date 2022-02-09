package com.bancodequestoes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WelcomeApplication extends Application {

    public static void main() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeApplication.class.getResource("view/welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setScene(scene);
        stage.show();
    }
}

