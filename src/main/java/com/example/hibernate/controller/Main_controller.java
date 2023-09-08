package com.example.hibernate.controller;

import com.example.hibernate.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class Main_controller {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane root1;
    @FXML
    private AnchorPane dashbord;
    private EventObject actionEvent;

    @FXML
    void dashboardOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_controller.class.getResource("/view/main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void registerOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/student_registor_form.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(anchorPane);

    }

    @FXML
    void reserveOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/reservation_form.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(anchorPane);

    }

    @FXML
    void roomOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/room_registor_form.fxml"));
        root1.getChildren().clear();
        root1.getChildren().add(anchorPane);

    }

    public void setUser(UserDTO userDTO) {


    }
}
