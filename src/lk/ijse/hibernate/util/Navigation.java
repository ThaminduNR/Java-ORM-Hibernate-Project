package lk.ijse.hibernate.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static void setUI(String location) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(Navigation.class.getResource("../views/"+location))));
        stage.show();
        stage.centerOnScreen();

    }

    public static void navigatePane(Routes route, AnchorPane pane) throws IOException {
        switch (route) {
            case DASHBOARD:
                initUI("Dashboard.fxml", pane);
                break;

            case STUDENT:

                initUI("StudentManageForm.fxml", pane);
                break;

            case ROOM:

                initUI("RoomManageForm.fxml", pane);
                break;
            case RESERVATION:

                initUI("ReservationForm.fxml", pane);
                break;
            case RESERVATIONDETAILS:

                initUI("ReservationDetails.fxml", pane);
                break;

            case USER:

                initUI("UserForm.fxml", pane);
                break;

        }
    }

    private static void initUI(String location, AnchorPane pane) throws IOException,NullPointerException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hibernate/views/" + location)));

    }
}
