package lk.ijse.hibernate.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;


public interface StudentBO extends SuperBO {

    boolean addStudent(StudentDTO studentDTO) throws IOException;

    boolean updateStudent(StudentDTO studentDTO) throws IOException;

    ArrayList<StudentDTO> getAllStudent() throws IOException;

    boolean deleteStudent(String id) throws IOException;

    ObservableList<String> getAllStudentIds() throws IOException;

    ArrayList<StudentDTO> changeStateStudentId(String id) throws IOException;


}
