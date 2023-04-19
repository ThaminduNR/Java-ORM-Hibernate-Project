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

    StudentBO studentBO = new StudentBOImpl();

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

    public void updateOnAction(ActionEvent actionEvent) {
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

    public void DeleteOnaAction(ActionEvent actionEvent) {
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
