package com.example.hibernate.controller;


import com.example.hibernate.bo.BOFactory;
import com.example.hibernate.bo.custom.LoginBO;
import com.example.hibernate.dto.UserDTO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login_controller implements Initializable {

    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXTextField tfPassword;

    @FXML
    private JFXPasswordField pfPassword;

    @FXML
    private JFXCheckBox cbShowPassword;

    @FXML
    private JFXButton btnSignIn;
    private LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldsOnActions();
    }

    private void fieldsOnActions() {
        tfUsername.setOnAction((e)-> {
            pfPassword.requestFocus();
        });

        tfPassword.setOnAction((e) -> {
            btnSignIn.fire();
        });

        pfPassword.setOnAction((e) -> {
            btnSignIn.fire();
        });

        pfPassword.textProperty().bindBidirectional(tfPassword.textProperty());
    }


//    public void loginOnAction(ActionEvent actionEvent) throws IOException {
//
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main-view.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.centerOnScreen();
//
//    }


    public void cbShowPasswordOnAction(ActionEvent actionEvent) {

        if (cbShowPassword.isSelected()){
            pfPassword.setVisible(false);
        }else {
            pfPassword.setVisible(true);
        }

    }

    public void btnSignInOnAction(ActionEvent actionEvent) {

        boolean isValid = check();
        if (isValid){
            try {
                boolean isVerifiedUser = loginBO.userVerify(tfUsername.getText(), tfPassword.getText());
                if (isVerifiedUser){
                    loadMainWindow();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Login informations are wrong...!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Opss.. Something went wrong!!!").show();
            }
            tfUsername.setText(null);
            tfPassword.setText(null);
        }

    }

    private void loadMainWindow() throws IOException {

        Stage window = (Stage) btnSignIn.getScene().getWindow();
        window.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));

        Main_controller dashboard_form_controller =fxmlLoader.getController();
//        dashboard_form_controller.setUser(new UserDTO(null, tfUsername.getText(), tfPassword.getText()));
        stage.setTitle("D24 Hostel System");
        stage.show();

    }

    public void linkNewAccountOnAction(ActionEvent actionEvent) {


    }

    private boolean check() {
        if (!pfPassword.getText().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid Password!").show();
            return false;
        }
        if (!tfUsername.getText().matches("^[A-Za-z]+$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid User Name").show();
            return false;
        }
        return true;
    }

}
