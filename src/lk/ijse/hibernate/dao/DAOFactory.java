package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDAOFactory(){
        if (daoFactory == null){
                daoFactory=new DAOFactory();
        }
        return daoFactory;
    }


    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case RESERVATIONDETAIL:
                return new ReservationDetailDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;

        }
    }

}
