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
import lk.ijse.hibernate.bo.custom.ReservationDetailBO;
import lk.ijse.hibernate.bo.custom.impl.ReservationDetailBOImpl;
import lk.ijse.hibernate.dto.ReservationDetailDTO;
import lk.ijse.hibernate.views.tm.ReservationDetailTM;
import lk.ijse.hibernate.views.tm.StudentTM;

import java.io.IOException;
import java.util.ArrayList;

public class ReservationDetailsController {
    public AnchorPane reservationDetailPane;
    public TextField txtResId;
    public TextField txtsName;
    public TextField txtRoomType;
    public TextField txtkeyMoney;
    public TextField txtpayAmount;

    public TableView<ReservationDetailTM> tblRes;
    public TableColumn colresId;
    public TableColumn colSid;
    public TableColumn colSName;
    public TableColumn comRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colPayAmonynt;

    public TableColumn colStatus;
    public ComboBox cmbstatus;
    public TextField txtSId;
    public TextField txtRoomId;

    ReservationDetailBO reservationDetailBO = new ReservationDetailBOImpl();

    public void initialize(){
        loadAllData();
        setValueFactory();
        loadPaymentType();
        AddListener();
    }




    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Home");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) reservationDetailPane.getScene().getWindow();
        currentStage.hide();
        stage.show();
    }







    public void addStudentOnAction(ActionEvent actionEvent) {}

    public void updateReservationDetails(){
        ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO(
                txtResId.getText(),
                txtSId.getText(),
                txtsName.getText(),
                txtRoomId.getText(),
                txtRoomType.getText(),
                Double.parseDouble(txtkeyMoney.getText()),
                Double.parseDouble(txtpayAmount.getText()),
                String.valueOf(cmbstatus.getValue())

        );

        boolean isUpdated = false;
        try {
            isUpdated = reservationDetailBO.updateReservationDetailData(reservationDetailDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Updated").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Update Failed").show();
        }

    }



    private void loadAllData(){
        ObservableList<ReservationDetailTM> allRes = FXCollections.observableArrayList();
        try {
            ArrayList<ReservationDetailDTO> allData = reservationDetailBO.getAllData();
            for (ReservationDetailDTO rd:allData) {
                ReservationDetailTM resTM = new ReservationDetailTM(
                        rd.getResId(),
                        rd.getSId(),
                        rd.getSName(),
                        rd.getRoomId(),
                        rd.getRoomType(),
                        rd.getKeyMoney(),
                        rd.getPayAmount(),
                        rd.getStatus()
                );
                allRes.add(resTM);
            }
            tblRes.setItems(allRes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setValueFactory(){
        colresId.setCellValueFactory(new PropertyValueFactory<StudentTM,String>("resId"));
        colSid.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("sId"));
        colSName.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("sName"));
        comRoomId.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("keyMoney"));
        colPayAmonynt.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("payAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("status"));
    }

    public void AddListener() {
        tblRes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTableDataToTextField(newValue);
            }
        });
    }

    private void setTableDataToTextField(ReservationDetailTM red) {
        txtResId.setText(red.getResId());
        txtSId.setText(red.getSId());
        txtsName.setText(red.getSName());
        txtRoomId.setText(red.getRoomId());
        txtRoomType.setText(red.getRoomType());
        txtkeyMoney.setText(String.valueOf(red.getKeyMoney()));
        txtpayAmount.setText(String.valueOf(red.getPayAmount()));
        cmbstatus.setValue(red.getStatus());

    }

    private void loadPaymentType() {
        ObservableList<String> fuelList = FXCollections.observableArrayList("Paid", "Unpaid");
        cmbstatus.setItems(fuelList);
    }

    public void addFinishOnActon(ActionEvent actionEvent) {
        updateReservationDetails();
        loadAllData();
    }
}
