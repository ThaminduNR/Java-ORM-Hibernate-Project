package lk.ijse.hibernate.bo;

import lk.ijse.hibernate.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case RESERVATIONDETAIL:
                return new ReservationDetailBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;

        }
    }
}
