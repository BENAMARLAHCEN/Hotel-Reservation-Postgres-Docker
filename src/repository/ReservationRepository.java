package repository;

import dao.ReservationDAO;
import model.Reservation;

import java.util.List;

public class ReservationRepository {
    private final ReservationDAO reservationDAO = new ReservationDAO();

    public void saveReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    public boolean cancelReservation(int id) {
        return reservationDAO.cancelReservation(id);
    }
}
