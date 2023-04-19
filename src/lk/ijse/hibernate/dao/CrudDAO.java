package lk.ijse.hibernate.dao;

import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{

//    boolean add(T t) throws IOException;
    void add(T t, Session session);

    void update(T t, Session session);

    void delete(ID id, Session session);

    ArrayList<T> getAll(Session session) throws IOException;

//    boolean update(T t) throws IOException;
//
//    boolean delete(ID id) throws IOException;


}
