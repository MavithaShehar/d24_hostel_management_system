package com.example.hibernate.controller;

import com.example.hibernate.bo.BOFactory;
import com.example.hibernate.bo.custom.StudentBO;
import com.example.hibernate.dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Student_controller implements Initializable {

    @FXML
    private AnchorPane root1;

    @FXML
    private TextField studentID;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField contact;

    @FXML
    private DatePicker bod;

    @FXML
    private TableView<?> table_student;

    @FXML
    private TableColumn<?, ?> col_stu_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_contact;

    @FXML
    private TableColumn<?, ?> col_bod;

    @FXML
    private TableColumn<?, ?> col_gender;

    @FXML
    private ComboBox<String> gender;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectGender();
    }

    private void selectGender(){

            ObservableList<String> options = FXCollections.observableArrayList(
                    "Male",
                    "Female"
            );
            gender.setItems(options);

    }

    @FXML
    public void saveOnAction(ActionEvent event) throws Exception {

            StudentDTO student = new StudentDTO(
                    studentID.getText(),
                    name.getText(),
                    address.getText(),
                    contact.getText(),
                    bod.getValue(),
                    gender.getValue()
            );

                boolean isAdded = studentBO.save(student);

                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Added!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Error while Adding Student :(").show();
                }
    }


    @FXML
    public void deleteOnAction(ActionEvent event) {

        try{
            boolean idDeleted= studentBO.delete(studentID.getText());
            if(idDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Student Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Error while Deleting Student :(").show();
        }

    }



    @FXML
    public void serchOnAction(ActionEvent event) throws Exception {

        String studentId = studentID.getText();
        StudentDTO student =  studentBO.searchStudent(studentId);
        if (student != null){

        }else {
            new Alert(Alert.AlertType.WARNING,"error");
        }

    }

    @FXML
    public void updateOnAction(ActionEvent event) throws Exception {

        StudentDTO student = new StudentDTO(
                studentID.getText(),
                name.getText(),
                address.getText(),
                contact.getText(),
                bod.getValue(),
                gender.getValue()
        );

        boolean isAdded = studentBO.save(student);

        if (isAdded){
            new Alert(Alert.AlertType.CONFIRMATION, "Student Added!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Error while Adding Student :(").show();
        }

    }

}
