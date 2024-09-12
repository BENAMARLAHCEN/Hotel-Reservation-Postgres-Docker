package service;

import model.*;
import repository.ReservationRepository;
import util.DataPrinter;
import util.DateValidator;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository = new ReservationRepository();

    public void createReservation(Customer customer,Room room,Hotel hotel, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!DateValidator.isCheckInBeforeCheckOut(checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date.");
        }
        if (!DateValidator.isFutureDate(checkInDate) || !DateValidator.isFutureDate(checkOutDate)) {
            throw new IllegalArgumentException("Date must be in the future.");
        }
        double totalCost = new PricingService().calculateTotalCost(room.getPrice(), checkInDate, checkOutDate);
        Reservation reservation = new Reservation(0,customer, room, hotel, checkInDate, checkOutDate, PaymentStatus.PAID, totalCost);
        Reservation savedReservation = reservationRepository.saveReservation(reservation);
        if (savedReservation == null) {
            throw new IllegalArgumentException("Reservation could not be created.");
        }else {
            System.out.println("Reservation created successfully.");
            DataPrinter.printReservationDetails(savedReservation);
        }
    }

    public List<Reservation> getRoomInDateRange(LocalDate checkInDate, LocalDate checkOutDate,RoomType roomType) {
        return reservationRepository.getRoomInDateRange(checkInDate, checkOutDate,roomType);
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

    public boolean deleteReservation(int id) {
        return reservationRepository.deleteReservation(id);
    }

    public List<Reservation> getReservationByCustomerId(int id) {
        return reservationRepository.getReservationByCustomerId(id);
    }

    public List<Room> getAvailableRoomsInDateRange(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType, String location) {
        return reservationRepository.getAvailableRoomsInDateRange(checkInDate, checkOutDate, roomType, location);
    }

    public Customer getCustomerById(int customerID) {
        return (new CustomerService()).getCustomerById(customerID);
    }

    public Hotel getHotelByRoomId(int roomID) {
        return (new HotelService()).getHotelByRoomId(roomID);
    }

    public Room getRoomById(int roomID) {
        return (new RoomService()).getRoomById(roomID);
    }
}
