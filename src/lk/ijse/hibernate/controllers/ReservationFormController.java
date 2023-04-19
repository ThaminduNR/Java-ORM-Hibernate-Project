package lk.ijse.hibernate.controllers;

import com.jfoenix.controls.JFXDatePicker;
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
import lk.ijse.hibernate.bo.custom.ReservationBO;
import lk.ijse.hibernate.bo.custom.ReservationDetailBO;
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hibernate.bo.custom.impl.ReservationDetailBOImpl;
import lk.ijse.hibernate.bo.custom.impl.RoomBOImpl;
import lk.ijse.hibernate.bo.custom.impl.StudentBOImpl;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.ReservationDetailDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.views.tm.CartTM;
import lk.ijse.hibernate.views.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ReservationFormController {
    public TextField txtResId;
    public TextField txtsName;
    public ComboBox<String> cmbsId;
    public ComboBox cmbRoomId;
    public TextField txtRoomType;
    public TextField txtavailable;
    public TextField txtkeyMoney;
    public TextField txtpayAmount;


    public TableColumn colresId;
    public TableColumn colSid;
    public TableColumn colRoomTypeId;
    public TableColumn comRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colPayAmount;

    public AnchorPane reservationPane;
    public ComboBox<String> cmbstatus;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtDob;
    public TextField txtGender;
    public TableColumn colDate;
    public TableColumn colStatus;
    public JFXDatePicker txtDate;
    public TableColumn colAction;
    public ObservableList<CartTM> cartList = FXCollections.observableArrayList();
    public TableView tblCart;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOTypes.ROOM);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOTypes.RESERVATION);
    ReservationDetailBO reservationDetailBO = (ReservationDetailBO) BOFactory.getBoFactory().getBO(BOTypes.RESERVATIONDETAIL);

    public void initialize(){
        loadPaymentType();
        getAllStudentIds();
        getAllRoomIds();
        changeListenerRoom();
        changeListenerStudent();
        setValueFactory();
        generateReservationId();

    }

    private void generateReservationId() {
        String id = "";

        try {
            id = reservationBO.getLastReservationId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int newId = Integer.parseInt(id.replace("RID-", "")) + 1;
        txtResId.setText(String.format("RID-%03d", newId));
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Home");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) reservationPane.getScene().getWindow();
        currentStage.hide();
        stage.show();
    }

    //add to cart button
    public void addCartOnActon(ActionEvent actionEvent) {


        if( txtResId.getText().equals("")||cmbsId.getValue()==null|| cmbRoomId.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Fill in the Blanks").show();

        }else if(txtRoomType.getText().equals("")|| txtpayAmount.getText().equals("")||txtDate.getValue()==null||
                cmbstatus.getValue()==null||txtkeyMoney.getText().equals("")){

            new Alert(Alert.AlertType.ERROR,"Fill in the Blanks").show();

        }else {
            /*String resId=txtResId.getText();
            String sId = cmbsId.getValue();
            String rId = String.valueOf(cmbRoomId.getValue());
            String roomType = txtRoomType.getText();
            double keyMoney = Double.parseDouble(txtkeyMoney.getText());
            double payAmount = Double.parseDouble(txtpayAmount.getText());
            String date = String.valueOf(txtDate.getValue());
            String status = cmbstatus.getValue();*/
            Button button = new Button("Remove");

            CartTM cartTM = new CartTM(txtResId.getText(),cmbsId.getValue(),String.valueOf(cmbRoomId.getValue()),
                    txtRoomType.getText(),Double.parseDouble(txtkeyMoney.getText()),Double.parseDouble(txtpayAmount.getText())
                    ,String.valueOf(txtDate.getValue()),cmbstatus.getValue(),button);

            cartList.add(cartTM);
            tblCart.setItems(cartList);

            button.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Are You sure", ButtonType.YES, ButtonType.CANCEL);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    for (CartTM tm : cartList) {
                        cartList.remove(tm);
                        tblCart.refresh();
                        clear();
                        return;


                    }
                }
            });
        }

    }

    public void setValueFactory(){
        colresId.setCellValueFactory(new PropertyValueFactory<StudentTM,String>("resNo"));
        colSid.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("sId"));
        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("rId"));
        comRoomType.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("roomType"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("keyMoney"));
        colPayAmount.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("payAmount"));
        colDate.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("status"));
        colAction.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("btn"));
    }

    public void addStudentOnAction(ActionEvent actionEvent) {
    }

    public void RoomReserveOnAction(ActionEvent actionEvent) {

        if (txtResId.getText().equals("")||cmbsId.getValue()==null|| cmbRoomId.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Cart is Empty").show();
        }else if(txtsName.getText().equals("")||txtAddress.getText().equals("")||txtContact.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Cart is Empty").show();
        }else if(txtDob.getText().equals("")||txtGender.getText().equals("")||txtavailable.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Cart is Empty").show();
        }else if(txtRoomType.getText().equals("")|| txtpayAmount.getText().equals("")||txtDate.getValue()==null){
            new Alert(Alert.AlertType.ERROR,"Cart is Empty").show();
        }else if (cmbstatus.getValue()==null||txtkeyMoney.getText().equals("")){
            new Alert(Alert.AlertType.ERROR,"Cart is Empty").show();
        }
        else {
            Student student = new Student();
            student.setSId(cmbsId.getValue());
            student.setName(txtsName.getText());
            student.setAddress(txtAddress.getText());
            student.setContact(txtContact.getText());
            student.setDob(txtDob.getText());
            student.setGender(txtGender.getText());

            Room room = new Room();
            room.setRoomId(String.valueOf(cmbRoomId.getValue()));
            room.setRoomType(txtRoomType.getText());
            room.setKeyMoney(Double.parseDouble(txtkeyMoney.getText()));
            room.setQty(Integer.parseInt(txtavailable.getText()));

            String date = String.valueOf(txtDate.getValue());

            ReservationDTO res = new ReservationDTO(
                    txtResId.getText(),
                    date,
                    student,
                    room,
                    cmbstatus.getValue()
            );

            boolean isAdded = false;
            try {
                isAdded = reservationBO.addReservation(res);
                addTOReservationDetailData();
                clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isAdded){
                new Alert(Alert.AlertType.INFORMATION,"Added Successful").show();
                updateQty(txtResId.getText());
                cartList.clear();
//
            }else {
                new Alert(Alert.AlertType.ERROR,"Added Failed").show();
            }
        }

    }

    public void getAllStudentIds() {
        try {
            ObservableList<String> allStudentIds = studentBO.getAllStudentIds();
            cmbsId.setItems(allStudentIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPaymentType() {
        ObservableList<String> fuelList = FXCollections.observableArrayList("Paid", "Unpaid");
        cmbstatus.setItems(fuelList);
    }

    public void getAllRoomIds()  {
        ObservableList<String> allRoomIds = null;
        try {
            allRoomIds = roomBO.getAllRoomIds();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cmbRoomId.setItems(allRoomIds);
    }

    private void changeListenerRoom() {
        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setRoomData();
            }
        });
    }

    private void setRoomData(){
        String id = (String) cmbRoomId.getValue();
        ArrayList<RoomDTO> roomDTOS = null;
        try {
            roomDTOS = roomBO.changeStateRoomId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (RoomDTO d:roomDTOS) {
            txtRoomType.setText(d.getRoomType());
            txtkeyMoney.setText(String.valueOf(d.getKeyMoney()));
            txtavailable.setText(String.valueOf(d.getQty()));

        }
    }

    private void changeListenerStudent() {
        cmbsId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setStudentData();
            }
        });
    }

    private void setStudentData(){
        String id = cmbsId.getValue();
        ArrayList<StudentDTO> studentDTOS = null;
        try {
            studentDTOS = studentBO.changeStateStudentId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (StudentDTO s:studentDTOS) {
           txtsName.setText(s.getName());
           txtAddress.setText(s.getAddress());
           txtContact.setText(s.getContact());
           txtDob.setText(s.getDob());
           txtGender.setText(s.getGender());
        }

    }

    private void updateQty(String id){
        try {
            roomBO.updateRoomQty(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void addTOReservationDetailData(){
        String resId=txtResId.getText();
        String sId = cmbsId.getValue();
        String sName=txtsName.getText();
        String roomId= (String) cmbRoomId.getValue();
        String roomType = txtRoomType.getText();
        double keyMoney = Double.parseDouble(txtkeyMoney.getText());
        double payAmount = Double.parseDouble(txtpayAmount.getText());
        String status = cmbstatus.getValue();

        ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO(resId, sId, sName, roomId, roomType, keyMoney, payAmount, status);

        boolean b = false;
        try {
            b = reservationDetailBO.addTOReservationDetailData(reservationDetailDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (b){
            System.out.println("addTOReservationDetailData Added");
        }else {
            System.out.println("Error");
        }


    }

    private void clear(){
        txtResId.clear();
        cmbsId.getSelectionModel().clearSelection();
        txtsName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDob.clear();
        txtGender.clear();

        cmbRoomId.getSelectionModel().clearSelection();
        txtRoomType.clear();
        txtavailable.clear();
        txtkeyMoney.clear();
        txtpayAmount.clear();
        txtDate.setValue(null);
        cmbstatus.getSelectionModel().clearSelection();
    }

}
