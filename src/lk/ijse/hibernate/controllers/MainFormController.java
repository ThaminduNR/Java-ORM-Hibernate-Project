package lk.ijse.hibernate.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.util.Navigation;
import lk.ijse.hibernate.util.Routes;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainStage;
    public AnchorPane mainBoard;
    public AnchorPane dashboardPane;

    public void initialize(){
        loadHome();
    }

    public void studentManageOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePane(Routes.STUDENT,mainBoard);
    }

    public void roomManageOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePane(Routes.ROOM,mainBoard);
    }

    public void reservationFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePane(Routes.RESERVATION,mainBoard);
    }

    public void viewDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePane(Routes.RESERVATIONDETAILS,mainBoard);
    }

    public void UserOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePane(Routes.USER,mainBoard);

    }

    public void logOutOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setContentText("Are you sure want to log out?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) mainStage.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Try Again");
        }

    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePane(Routes.DASHBOARD,mainBoard);
    }

    public void loadHome(){
        try {
            Navigation.navigatePane(Routes.DASHBOARD,mainBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
