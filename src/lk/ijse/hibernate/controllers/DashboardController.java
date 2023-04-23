package lk.ijse.hibernate.controllers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hibernate.bo.BOFactory;
import lk.ijse.hibernate.bo.BOTypes;
import lk.ijse.hibernate.bo.custom.RoomBO;
import lk.ijse.hibernate.bo.custom.StudentBO;

import java.io.IOException;

public class DashboardController {
    public AnchorPane dashboardPane;
    public Label lblStudent;
    public Label lblRooms;
    public Label lblAllbook;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOTypes.ROOM);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOTypes.STUDENT);


    public void initialize(){
        loadStudentCount();
        loadRoomCount();
    }

    private void loadStudentCount(){
        int size=0;
        try {
             size = studentBO.getAllStudentIds().size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lblStudent.setText(0+String.valueOf(size));

    }

    private void loadRoomCount(){
        int rooms=0;
        try {
            rooms = roomBO.getAllRoomIds().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lblRooms.setText(0+String.valueOf(rooms));
    }
}
