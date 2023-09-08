package com.example.hibernate.controller;

import com.example.hibernate.bo.BOFactory;
import com.example.hibernate.bo.custom.ReservationBO;
import com.example.hibernate.dto.ReservationDTO;
import com.example.hibernate.dto.RoomDTO;
import com.example.hibernate.dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Reservation_controller implements Initializable {

    @FXML
    private TextField btnDetails;

    @FXML
    private TableView<?> tabel_reservation;

    @FXML
    private TableColumn<?, ?> col_reser_id;

    @FXML
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<?, ?> col_stu_id;

    @FXML
    private TableColumn<?, ?> col_room_type_id;

    @FXML
    private TableColumn<?, ?> col_status;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtName;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtContact;

    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtRDate;

    @FXML
    private TextField txtRID;

    @FXML
    private ComboBox<String> cmbRType;

    @FXML
    private ComboBox<String> cmbstatus;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loadType();
            loadStatus();
            loadGender();
            setDate();
            txtRID.setText(reservationBO.setReservationID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDate() {
        java.util.Date Date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(Date);
        txtRDate.setValue(LocalDate.parse(dateString));
    }

    private void loadStatus() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Paid",
                    "Pending"
            );
            cmbstatus.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {

        boolean isValid = check();

        if (isValid){
            try {
                StudentDTO student = new StudentDTO(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtDOB.getValue(),
                        genderCombo.getValue(),
                        new ArrayList<>()
                );

                RoomDTO room = getRoom(cmbRType.getValue());

                if (room != null) {
                    ReservationDTO reservation = new ReservationDTO(
                            txtRID.getText(),
                            txtRDate.getValue(),
                            cmbstatus.getValue(),
                            student,
                            room
                    );

                    student.getReservations().add(reservation);
                    room.setReservations(new ArrayList<>());
                    room.getReservations().add(reservation);

                    boolean isRegistered = reservationBO.UpdateStudent(reservation);
                    if (isRegistered) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Registration Completed!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Registration Failed!!!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Selected room is invalid or not found!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops..Something Went Wrong...").show();
            }
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {

        boolean isValid = check();
        if (isValid){
            try {
                StudentDTO student = new StudentDTO(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContact.getText(),
                        txtDOB.getValue(),
                        genderCombo.getValue(),
                        new ArrayList<>()
                );

                RoomDTO room = getRoom(cmbRType.getValue());

                if (room != null) {
                    ReservationDTO reservation = new ReservationDTO(
                            txtRID.getText(),
                            txtRDate.getValue(),
                            cmbstatus.getValue(),
                            student,
                            room
                    );

                    student.getReservations().add(reservation);
                    room.setReservations(new ArrayList<>());
                    room.getReservations().add(reservation);

                    boolean isRegistered = reservationBO.UpdateStudent(reservation);
                    if (isRegistered) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Registration Completed!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Registration Failed!!!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Selected room is invalid or not found!").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Oops..Something Went Wrong...").show();
            }
        }

    }

    private RoomDTO getRoom(String value) {
        try {

            List<RoomDTO> allRooms = reservationBO.getAllRooms();
            for (RoomDTO room : allRooms) {
                if (value.equals(room.getType())){
                    return room;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadType() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Non-AC",
                    "Non-AC / Food",
                    "AC",
                    "AC / Food"
            );
            cmbRType.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean check() {

        if (!txtName.getText().matches("^[A-Za-zÀ-ÖØ-öø-ÿ-]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ-]+)+$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid User Name").show();
            return false;
        }
        if (!txtContact.getText().matches("^\\+?\\d{8,}$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid Contact number").show();
            return false;
        }
        if (!txtAddress.getText().matches("^.+$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid Address").show();
            return false;
        }

        return true;
    }

    private void loadGender() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Male",
                    "Female",
                    "Other"
            );
            genderCombo.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
