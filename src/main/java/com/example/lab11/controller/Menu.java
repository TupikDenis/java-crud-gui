package com.example.lab11.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    @FXML
    private Text head;

    @FXML
    private Button town;

    @FXML
    private Button faculet;

    @FXML
    private Button person;

    @FXML
    private Button closeApp;

    @FXML
    private Button type;

    @FXML
    private Button educationalInstitution;

    @FXML
    void educationalInstitutionWindow(ActionEvent event) throws IOException{
        openNewWindow("educationalInstitution.fxml");
    }

    @FXML
    void facultetWindow(ActionEvent event) throws IOException{
        openNewWindow("facultet.fxml");
    }

    @FXML
    void personWindow(ActionEvent event) throws IOException{
        openNewWindow("person.fxml");
    }

    @FXML
    void townWindow(ActionEvent event) throws IOException{
        openNewWindow("town.fxml");
    }

    @FXML
    void typeWindow(ActionEvent event) throws IOException {
        openNewWindow("type.fxml");
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) this.closeApp.getScene().getWindow();
        stage.close();
    }

    private void openNewWindow(String fileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("/com/example/lab11/" + fileName));
        Parent root1 = (Parent)fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

}

