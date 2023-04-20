package lk.ijse.hibernate.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOTypes;
import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.bo.custom.impl.StudentBOImpl;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.views.tm.StudentTM;

import java.io.IOException;
import java.util.ArrayList;

public class StudentManageFormController {
    public AnchorPane studentPage;
    public TextField txtsId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtNumber;
    public TextField txtDate;

    
    public TableView<StudentTM> tblStudent;
    
    public TableColumn<StudentTM, String> colsId;
    public TableColumn<StudentTM, String> colName;
    public TableColumn<StudentTM, String> colAddress;
    public TableColumn<StudentTM, String> colContact;
    public TableColumn<StudentTM, String>colDate;
    public TableColumn<StudentTM, String> colGender;
    public ComboBox cmbGender;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);

    public void initialize(){
        loadAllStudent();
        setValueFactory();
        AddListener();
        loadGenderType();
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

    public void addStudentOnAction(ActionEvent actionEvent) throws IOException {

        if(txtsId.getText().isEmpty() || !txtsId.getText().matches("^(M)([0-9]{5,}$)")) {
            new Alert(Alert.AlertType.ERROR, "Student ID is invalid or empty", ButtonType.OK).show();
            txtsId.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            txtsId.requestFocus();
            return;

        } else if (txtName.getText().isEmpty() || !txtName.getText().matches("([\\w ]{1,})")) {
            new Alert(Alert.AlertType.ERROR, "Name text invalid or empty", ButtonType.OK).show();
            txtName.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            txtName.requestFocus();
            return;

        }else if (txtAddress.getText().isEmpty() || !txtAddress.getText().matches("^[0-9a-zA-Z]{2,}")) {
            new Alert(Alert.AlertType.ERROR, "Address text invalid or empty",ButtonType.OK).show();
            txtAddress.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            txtAddress.requestFocus();
            return;

        }else if (txtNumber.getText().isEmpty() || !txtNumber.getText().matches("^(075|077|071|074|078|076|070|072)([0-9]{7})$")){
            new Alert(Alert.AlertType.ERROR, "Contact text invalid or empty", ButtonType.OK).show();
            txtNumber.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            txtNumber.requestFocus();
            return;

        }else if (txtDate.getText().isEmpty() || !txtDate.getText().matches("^([0-9]{4})(-)([0-9]{2})(-)([0-9]{2}$)")){
            new Alert(Alert.AlertType.ERROR, "Date of birth text empty or format must be like (ex : 2000-02-02)", ButtonType.OK).show();
            txtDate.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            txtDate.requestFocus();
            return;

        }else if (cmbGender.getSelectionModel().isSelected(-1)){
            new Alert(Alert.AlertType.ERROR, "Please select the gender !",ButtonType.OK).show();
            cmbGender.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            cmbGender.requestFocus();
            return;

        }else {
            String id= txtsId.getText();
            String  name = txtName.getText();
            String address = txtAddress.getText();
            String num = txtNumber.getText();
            String date = txtDate.getText();
            String gender = (String) cmbGender.getValue();

            StudentDTO studentDTO = new StudentDTO(id,name,address,num,date,gender);
            boolean isAdded = studentBO.addStudent(studentDTO);

            if (isAdded){
                new Alert(Alert.AlertType.INFORMATION, "Added Success").show();
                loadAllStudent();
            }else {
                new Alert(Alert.AlertType.ERROR, "Added Failed").show();
            }
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {

        if (txtsId.getText().isEmpty()||txtName.getText().isEmpty()||txtAddress.getText().isEmpty()||txtNumber.getText().isEmpty()||
        txtDate.getText().isEmpty()||cmbGender.getValue()==null){
            new Alert(Alert.AlertType.ERROR, "Select the Field !",ButtonType.OK).show();

        }else {
            String id= txtsId.getText();
            String name = txtName.getText();
            String address=txtAddress.getText();
            String num = txtNumber.getText();
            String date = txtDate.getText();
            String gender = (String) cmbGender.getValue();

            StudentDTO studentDTO = new StudentDTO(id,name,address,num,date,gender);
            boolean isUpdated = false;
            try {
                isUpdated = studentBO.updateStudent(studentDTO);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Update Success...").show();
                loadAllStudent();
            }else{
                new Alert(Alert.AlertType.ERROR, "Update Failed").show();
            }
        }
    }

    public void DeleteOnaAction(ActionEvent actionEvent) {
        if (txtsId.getText().isEmpty()||txtName.getText().isEmpty()||txtAddress.getText().isEmpty()||txtNumber.getText().isEmpty()||
                txtDate.getText().isEmpty()||cmbGender.getValue()==null){
            new Alert(Alert.AlertType.ERROR, "Select the Field !",ButtonType.OK).show();

        }else {

            String id = txtsId.getText();
            try {
                boolean isDeleted = studentBO.deleteStudent(id);
                if (isDeleted){
                    new Alert(Alert.AlertType.INFORMATION,"Delete Success...").show();
                    loadAllStudent();
                }else{
                    new Alert(Alert.AlertType.ERROR, "Delete Failed").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void loadAllStudent(){
        ObservableList<StudentTM> allStudent = FXCollections.observableArrayList();

        try {
            ArrayList<StudentDTO> allStudentList = studentBO.getAllStudent();
            for (StudentDTO s :allStudentList) {
                StudentTM studentTM = new StudentTM(
                        s.getSId(),
                        s.getName(),
                        s.getAddress(),
                        s.getContact(),
                        s.getDob(),
                        s.getGender()
                );
                allStudent.add(studentTM);
            }
            tblStudent.setItems(allStudent);
            tblStudent.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setValueFactory(){
        colsId.setCellValueFactory(new PropertyValueFactory<StudentTM,String>("sId"));
        colName.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("contact"));
        colDate.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<StudentTM, String>("gender"));
    }

    public void AddListener() {
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setTableDataToTextField(newValue);
            }
        });
    }

    private void setTableDataToTextField(StudentTM stm) {
        txtsId.setText(stm.getSId());
        txtName.setText(stm.getName());
        txtAddress.setText(stm.getAddress());
        txtNumber.setText(stm.getContact());
        txtDate.setText(stm.getDob());
        cmbGender.setValue(stm.getGender());

    }

    private void loadGenderType() {
        ObservableList<String> fuelList = FXCollections.observableArrayList("Male", "Female","Others");
        cmbGender.setItems(fuelList);
    }
}
