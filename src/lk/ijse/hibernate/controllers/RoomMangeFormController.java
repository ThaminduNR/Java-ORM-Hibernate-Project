package lk.ijse.hibernate.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOTypes;
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.bo.custom.impl.RoomBOImpl;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.views.tm.RoomTM;

import java.io.IOException;
import java.util.ArrayList;

public class RoomMangeFormController {
    public AnchorPane studentPage;

   
    public TextField txtKeyMoney;
    public TextField txtQty;
    public TextField txtRoomId;
    public ComboBox<String> cmbRoomType;

    public TableView tblRoom;
    public TableColumn<RoomTM, String> colRoomId;
    public TableColumn<RoomTM, String> colRoomType;
    public TableColumn<RoomTM, String> colKeyMoney;
    public TableColumn<RoomTM, String> colQty;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOTypes.ROOM);

    public void initialize(){
        loadRoomType();
        loadAllRooms();
        setValueFactory();
        AddListener();
    }


    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Home");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) studentPage.getScene().getWindow();
        currentStage.hide();
        stage.show();
    }

    public void addRoomOnAction(ActionEvent actionEvent) {
        String id= txtRoomId.getText();
        String type = cmbRoomType.getValue();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());

        RoomDTO roomDTO = new RoomDTO(id,type,keyMoney,qty);
        boolean added = false;
        try {
            added = roomBO.addRoom(roomDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (added){
            new Alert(Alert.AlertType.INFORMATION,"Added Successful").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Added failed").show();
        }

    }

    public void updateRoomOnAction(ActionEvent actionEvent) {
        String id= txtRoomId.getText();
        String type = cmbRoomType.getValue();
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtQty.getText());

        RoomDTO roomDTO = new RoomDTO(id,type,keyMoney,qty);
        boolean updated = false;
        try {
            updated = roomBO.updateRoom(roomDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (updated){
            new Alert(Alert.AlertType.INFORMATION,"Update Successful").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Update failed").show();
        }
    }

    public void DeleteRoomOnaAction(ActionEvent actionEvent) {
        String id= txtRoomId.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure want to Delete?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                boolean deleted = roomBO.deleteRoom(id);
                if (deleted){
                    new Alert(Alert.AlertType.INFORMATION,"Delete Successful").show();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Delete failed").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Try Again");
        }
    }

    public  void loadAllRooms(){
        ObservableList<RoomTM> allRooms = FXCollections.observableArrayList();
        try {
            ArrayList<RoomDTO> roomList = roomBO.getAllRooms();
            for (RoomDTO rm:roomList) {
                RoomTM tm = new RoomTM(
                        rm.getRoomId(),
                        rm.getRoomType(),
                        rm.getKeyMoney(),
                        rm.getQty()
                );
                allRooms.add(tm);
            }
            tblRoom.setItems(allRooms);
            tblRoom.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setValueFactory(){
        colRoomId.setCellValueFactory(new PropertyValueFactory<RoomTM,String>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<RoomTM,String>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<RoomTM,String >("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<RoomTM,String>("qty"));
    }

    public void AddListener() {
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTableDataToTextField((RoomTM) newValue);
            }
        });
    }

    private void setTableDataToTextField(RoomTM tm ) {
        txtRoomId.setText(tm.getRoomId());
        cmbRoomType.setValue(tm.getRoomType());
        txtKeyMoney.setText(String.valueOf(tm.getKeyMoney()));
        txtQty.setText(String.valueOf(tm.getQty()));
    }

    private void loadRoomType() {
        ObservableList<String> fuelList = FXCollections.observableArrayList("Non-AC", "Non-AC / Food","AC","AC / Food ");
        cmbRoomType.setItems(fuelList);
    }
}
