package com.example.hibernate.controller;

import com.example.hibernate.bo.BOFactory;
import com.example.hibernate.bo.custom.RoomBO;
import com.example.hibernate.dto.RoomDTO;
import com.example.hibernate.dto.tm.RoomTM;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Room_controller implements Initializable {

    @FXML
    private AnchorPane root1;

    @FXML
    private TextField room_type_ID;

    @FXML
    private TextField key_money;

    @FXML
    private TextField room_qty;

    @FXML
    private JFXButton saveOnAction;

    @FXML
    private JFXButton updateOnAction;

    @FXML
    private JFXButton deleteOnAction;

    @FXML
    private TableView<RoomTM> tabel_room;

    @FXML
    private TableColumn<?, ?> col_room_type_id;

    @FXML
    private TableColumn<?, ?> col_type;

    @FXML
    private TableColumn<?, ?> col_key_money;

    @FXML
    private TableColumn<?, ?> col_room_qty;

    @FXML
    private ComboBox<String> type;

    private RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    private ObservableList<RoomTM> roomTMS = FXCollections.observableArrayList();


    @FXML
    void deleteOnAction(ActionEvent event) throws Exception {

        try{
            boolean idDeleted= roomBO.delete(room_type_ID.getText());
            if(idDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Removed!").show();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Error while Deleting Room :(").show();
        }
        loadTableRooms();

    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {

        try{
            RoomDTO room = new RoomDTO(
                    room_type_ID.getText(),
                    type.getValue(),
                    key_money.getText(),
                    Integer.parseInt(room_qty.getText()),
                    new ArrayList<>()
            );
            try {
                boolean isUpdated = roomBO.update(room);
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Error while Updating Room :(").show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        loadTableRooms();

    }

    @FXML
    void updateOnAction(ActionEvent event) throws Exception {

        boolean isValid = check();
        if (isValid){
            try{
                RoomDTO room = new RoomDTO(
                        room_type_ID.getText(),
                        type.getValue(),
                        key_money.getText(),
                        Integer.parseInt(room_qty.getText()),
                        new ArrayList<>()
                );
                try {
                    boolean isAdded = roomBO.save(room);
                    if (isAdded){
                        new Alert(Alert.AlertType.CONFIRMATION, "Room Added!").show();
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Error while Adding Room :(").show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            loadTableRooms();
        }

    }

    private boolean check() {

        if (!key_money.getText().matches("^-?\\d+(\\.\\d+)?$")){
            new Alert(Alert.AlertType.WARNING , "Please enter a valid KeyMoney value").show();
            return false;
        }
        if (!room_qty.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid QTY").show();
            return false;
        }

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            setCellValueFactories();
            loadTableRooms();
            loadType();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadType() {
        try {
            ObservableList<String> options = FXCollections.observableArrayList(
                    "Non-AC",
                    "Non-AC / Food",
                    "AC",
                    "AC / Food"
            );
            type.setItems(options);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadTableRooms() throws Exception {

        roomTMS= FXCollections.observableArrayList();
        List<RoomDTO> rooms = roomBO.getAll();

        for (RoomDTO room: rooms) {
            roomTMS.add(new RoomTM(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));

        }
        tabel_room.setItems(roomTMS);

    }

    private void setCellValueFactories() {

        col_room_type_id.setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_key_money.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        col_room_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }
}