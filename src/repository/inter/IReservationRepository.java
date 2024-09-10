package repository.inter;

import model.Reservation;
import model.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository {

    void saveReservation(Reservation reservation);

    Reservation getReservationById(int reservationId);

    List<Reservation> getAllReservations();

    boolean cancelReservation(int reservationId);

    boolean updateReservation(Reservation reservation);

    boolean deleteReservation(int reservationId);

    List<Reservation> getReservationsByCustomerId(int customerId);

    List<Reservation> getRoomInDateRange(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType);
}
