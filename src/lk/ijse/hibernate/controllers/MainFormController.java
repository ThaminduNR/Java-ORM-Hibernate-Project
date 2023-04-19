package lk.ijse.hibernate.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainStage;

    public void studentManageOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Student Manage");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/StudentManageForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) mainStage.getScene().getWindow();
        currentStage.hide();
        stage.show();

    }

    public void roomManageOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Rooms Manage");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/RoomManageForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) mainStage.getScene().getWindow();
        currentStage.hide();
        stage.show();
    }

    public void reservationFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Reservation Manage");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/ReservationForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) mainStage.getScene().getWindow();
        currentStage.hide();
        stage.show();
    }

    public void viewDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Details");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/ReservationDetails.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) mainStage.getScene().getWindow();
        currentStage.hide();
        stage.show();
    }

    public void UserOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("User Manage");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../views/UserForm.fxml")));
        stage.setScene(scene);
        Stage currentStage = (Stage) mainStage.getScene().getWindow();
        currentStage.hide();
        stage.show();

    }
}
