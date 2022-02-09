package com.bancodequestoes.controllers;

import com.bancodequestoes.application.CadastroApplication;
import com.bancodequestoes.application.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private Button btLogin;

    @FXML
    private Hyperlink linkCadastro;

    @FXML
    protected void onLoginButtonClick() throws Exception {
        Stage stage = (Stage) btLogin.getScene().getWindow();

        (new LoginApplication()).start(stage);
    }

  public void onLinkCadastroClick() throws Exception {
        Stage stage = (Stage) linkCadastro.getScene().getWindow();

        (new CadastroApplication()).start(stage);
    }
}