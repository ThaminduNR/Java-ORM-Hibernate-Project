package lk.ijse.hibernate.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Student;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student,String> {

    ObservableList<String> getStudentIds() throws IOException;

    ArrayList<Student> changeStateStudentId(String id) throws IOException;
}
