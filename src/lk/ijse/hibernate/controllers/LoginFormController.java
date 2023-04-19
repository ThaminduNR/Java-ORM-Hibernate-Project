package lk.ijse.hibernate.controllers;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOTypes;
import lk.ijse.hibernate.bo.custom.UserBo;
import lk.ijse.hibernate.bo.custom.impl.UserBOImpl;
import lk.ijse.hibernate.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public TextField txtUser;
    public PasswordField txtPws;
    public AnchorPane pane;
    public JFXToggleButton tglbtn;
    public TextField txtpwsText;
    public CheckBox onCheck;

    UserBo userBo = (UserBo) BOFactory.getBoFactory().getBO(BOTypes.USER);

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String user = txtUser.getText();
        String pws = txtPws.getText();

        ArrayList<UserDTO> allUser = userBo.getAllUser();

        /*if (user.equals("")&&pws.equals("")){
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setTitle("Home");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml"))));
            stage.centerOnScreen();
        }else {
            wrongUsername();
        }*/
        for (UserDTO userDTO:allUser) {
            if (user.equals(userDTO.getUserId())&&pws.equals(userDTO.getPws())){
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setTitle("Home");
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/MainForm.fxml"))));
                stage.centerOnScreen();
            }else {
                wrongUsername();
            }
        }

    }

    private void wrongUsername() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Wrong Username or Password");
        alert.showAndWait();
    }

    public void oncheck(ActionEvent actionEvent) {
        if (onCheck.isSelected()){
            onCheck.setText("Hide Password");
            txtpwsText.setText(txtPws.getText());
            txtPws.setVisible(false);
            txtpwsText.setVisible(true);

        }else {
            onCheck.setText("Show Password");
            txtpwsText.setText(txtPws.getText());
            txtPws.setVisible(true);
            txtpwsText.setVisible(false);
        }

    }

   /* private void toggleButton(){

    }*/

}
