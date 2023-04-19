package lk.ijse.hibernate.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.hibernate.bo.custom.StudentBO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.SuperDTO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public boolean addStudent(StudentDTO studentDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student(
                studentDTO.getSId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender()
        );

        try{
            studentDAO.add(student,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = new Student(
                studentDTO.getSId(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDob(),
                studentDTO.getGender()
        );

        try{
            studentDAO.update(student,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<StudentDTO> list = new ArrayList<>();
        ArrayList<Student> all = null;
        try {
            all = studentDAO.getAll(session);
            for (Student student:all) {
                list.add(new StudentDTO(
                        student.getSId(),
                        student.getName(),
                        student.getAddress(),
                        student.getContact(),
                        student.getDob(),
                        student.getGender()
                ));
            }
            transaction.commit();
            return list;

        } catch (IOException e) {
            e.printStackTrace();
            transaction.rollback();
            return list;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            studentDAO.delete(id,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }


    }

    @Override
    public ObservableList<String> getAllStudentIds() throws IOException {
        return studentDAO.getStudentIds();
    }

    @Override
    public ArrayList<StudentDTO> changeStateStudentId(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        ArrayList<Student> students = studentDAO.changeStateStudentId(id);

        ArrayList<StudentDTO> sList = new ArrayList<>();

        for (Student s:students) {
            sList.add(new StudentDTO(
                    s.getSId(),
                    s.getName(),
                    s.getAddress(),
                    s.getContact(),
                    s.getDob(),
                    s.getGender()
            ));
        }
        return sList;
    }


}
