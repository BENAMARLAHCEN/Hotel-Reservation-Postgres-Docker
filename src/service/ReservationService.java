package service;

import model.*;
import repository.CustomerRepository;
import repository.HotelRepository;
import repository.ReservationRepository;
import repository.RoomRepository;
import util.DateValidator;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository = new ReservationRepository();

    public void createReservation(Customer customer,Room room,Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!DateValidator.isCheckInBeforeCheckOut(checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }

        Reservation reservation = new Reservation(0,customer, room, hotel, checkInDate, checkOutDate, PaymentStatus.PENDING);
        reservationRepository.saveReservation(reservation);
        System.out.println("Reservation created successfully!");
    }

    public Reservation getReservationById(int id) {
        return reservationRepository.getReservationById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    public boolean cancelReservation(int id) {
        return reservationRepository.cancelReservation(id);
    }
}
