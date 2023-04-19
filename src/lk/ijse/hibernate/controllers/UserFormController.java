package lk.ijse.hibernate.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.custom.UserBo;
import lk.ijse.hibernate.bo.custom.impl.UserBOImpl;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;

public class UserFormController {

    public AnchorPane UserPage;
    public TextField txtUid;
    public TextField txtName;
    public TextField txtpws;
    public TableView tblUse;
    public TableColumn coluId;
    public TableColumn colName;
    public TableColumn colPws;

    UserBo userBo = new UserBOImpl();

    public void initialize(){

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Home");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) UserPage.getScene().getWindow();
        currentStage.hide();
        stage.show();

    }

    public void addOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = new UserDTO(txtUid.getText(), txtName.getText(), txtpws.getText());

        try {
            boolean isAdded = userBo.addUser(userDTO);
            if (isAdded){
                new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Added Failed").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = new UserDTO(txtUid.getText(), txtName.getText(), txtpws.getText());

        try {
            boolean isAdded = userBo.updateUser(userDTO);
            if (isAdded){
                new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            }else{
                new Alert(Alert.AlertType.INFORMATION,"Update Failed").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnaAction(ActionEvent actionEvent) {

        try {
            boolean b = userBo.deleteUse(txtUid.getText());
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Delete Success").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Delete Failed").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
