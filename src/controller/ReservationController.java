package controller;

import model.Customer;
import model.Hotel;
import model.Reservation;
import model.Room;
import service.ReservationService;
import util.DataPrinter;
import util.DateValidator;
import util.ReportGenerator;

import java.time.LocalDate;
import java.util.List;

public class ReservationController {
    private final ReservationService reservationService = new ReservationService();
    private final ReportGenerator reportGenerator = new ReportGenerator();
    private final DataPrinter dataPrinter = new DataPrinter();
    public void createReservation(String customerName, String email, String phoneNumber,
                                  String hotelName, String location, Room room,
                                  LocalDate checkInDate, LocalDate checkOutDate) {
        if (!DateValidator.isValidDateRange(checkInDate, checkOutDate)) {
            System.out.println("Invalid date range!");
            return;
        }
        Customer customer = new Customer(0, customerName, email, phoneNumber);
        Hotel hotel = new Hotel(0, hotelName, location);
        reservationService.createReservation(customer, room, hotel, checkInDate, checkOutDate);
    }

//    public void generateReports() {
//        List<Reservation> allReservations = reservationService.getAllReservations();
//
//        String occupancyReport = ReportGenerator.generateOccupancyReport(allReservations);
//        DataPrinter.printReport("Occupancy", occupancyReport);
//
//        String revenueReport = ReportGenerator.generateRevenueReport(allReservations);
//        DataPrinter.printReport("Revenue", revenueReport);
//
//        String cancellationReport = ReportGenerator.generateCancellationReport(allReservations);
//        DataPrinter.printReport("Cancellations", cancellationReport);
//    }

}
