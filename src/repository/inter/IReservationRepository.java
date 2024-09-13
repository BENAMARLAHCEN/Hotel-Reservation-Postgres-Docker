package repository.inter;

import model.Reservation;
import model.Room;
import model.RoomType;

import java.time.LocalDate;
import java.util.List;

public interface IReservationRepository {

    Reservation saveReservation(Reservation reservation);

    int getLastReservationId();

    Reservation getReservationById(int reservationId);

    List<Reservation> getAllReservations();

    Reservation cancelReservation(int reservationId);

    boolean updateReservation(Reservation reservation);

    boolean deleteReservation(int reservationId);

    List<Reservation> getReservationsByCustomerId(int customerId);

    List<Reservation> getReservationByCustomerId(int id);

    List<Reservation> getRoomInDateRange(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType);

    List<Room> getAvailableRoomsInDateRange(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType, String location);
}
