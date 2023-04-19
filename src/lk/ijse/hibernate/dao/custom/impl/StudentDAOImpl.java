package lk.ijse.hibernate.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll(Session session) {
        List from_student = session.createQuery("FROM Student").list();
        return (ArrayList<Student>) from_student;
    }

    @Override
    public void add(Student student, Session session) {
        session.save(student);

    }

    @Override
    public void update(Student student, Session session) {
        session.update(student);

    }

    @Override
    public void delete(String id, Session session) {
        Student student = session.get(Student.class, id);
        session.delete(student);

    }

    @Override
    public ObservableList<String> getStudentIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ObservableList<String> studentIdList = FXCollections.observableArrayList();
        String hql="SELECT sId FROM Student";
        List<String> list = session.createQuery(hql).list();

        studentIdList.addAll(list);

        transaction.commit();
        session.close();
        return studentIdList;
    }

    @Override
    public ArrayList<Student> changeStateStudentId(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Student> sList = new ArrayList<>();
        String hql="FROM Student WHERE sId=:sId";
        Query query = session.createQuery(hql);
        query.setParameter("sId",id);
        List<Student> list = query.list();

        for (Student s:list) {
            sList.add(new Student(
                    s.getSId(),
                    s.getName(),
                    s.getAddress(),
                    s.getContact(),
                    s.getDob(),
                    s.getGender()
            ));
        }

        transaction.commit();
        session.close();
        return sList;
    }

    /*@Override
    public ObservableList<String> getStudentIds() throws IOException {
        return null;
    }

    @Override
    public ArrayList<Student> changeStateStudentId(String id) throws IOException {
        return null;
    }*/



   /* @Override
    public boolean add(Student student) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Student> getAll() throws IOException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Student> studentList = new ArrayList<>();

        String hql ="FROM Student";
        List <Student> list = session.createQuery(hql).list();

        for (Student s:list) {
            studentList.add(new Student(
                s.getSId(),
                s.getName(),
                s.getAddress(),
                s.getContact(),
                s.getDob(),
                s.getGender()
        ));

        }

        for (Student student:list) {
            System.out.println(student.getSId()+"-"+student.getName());
        }

        transaction.commit();
        session.close();

        return studentList;
    }

    @Override
    public boolean update(Student student) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);
        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<String> getStudentIds() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ObservableList<String> studentIdList = FXCollections.observableArrayList();
        String hql="SELECT sId FROM Student";
        List<String> list = session.createQuery(hql).list();

        studentIdList.addAll(list);

        transaction.commit();
        session.close();
        return studentIdList;
    }

    @Override
    public ArrayList<Student> changeStateStudentId(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Student> sList = new ArrayList<>();
        String hql="FROM Student WHERE sId=:sId";
        Query query = session.createQuery(hql);
        query.setParameter("sId",id);
        List<Student> list = query.list();

        for (Student s:list) {
            sList.add(new Student(
                    s.getSId(),
                    s.getName(),
                    s.getAddress(),
                    s.getContact(),
                    s.getDob(),
                    s.getGender()
            ));
        }

        transaction.commit();
        session.close();
        return sList;
    }*/

}
