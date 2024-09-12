package controller;

import model.*;
import service.ReservationService;
import util.DataPrinter;
import util.DateValidator;
import util.ReportGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationController {
    private final ReservationService reservationService = new ReservationService();
    public Scanner scanner = new Scanner(System.in);

    public void createReservation(int customerID,int roomID, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!DateValidator.isValidDateRange(checkInDate, checkOutDate)) {
            System.out.println("Invalid date range!");
            return;
        }
            Customer customer = reservationService.getCustomerById(customerID);
            Hotel hotel = reservationService.getHotelByRoomId(roomID);
            Room room = reservationService.getRoomById(roomID);
            if (room == null) {
                System.out.println("Invalid room ID!");
            } else if (customer == null) {
                System.out.println("Invalid customer ID!");
            } else {
                reservationService.createReservation(customer, room, hotel, checkInDate, checkOutDate);
            }
    }

    public void getAvailableRooms(String location, RoomType roomType, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> availableRooms = reservationService.getAvailableRoomsInDateRange(checkInDate, checkOutDate, roomType,location);
        DataPrinter.printAvailableRooms(availableRooms);
    }

    public void getReservationById() {
        System.out.println("Enter reservation id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        DataPrinter.printReservationDetails(reservationService.getReservationById(id));
    }

    public void getAllReservations() {
        DataPrinter.printAllReservations(reservationService.getAllReservations());
    }

    public void getReservationByCustomerId() {
        System.out.println("Enter customer id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        DataPrinter.printAllReservations(reservationService.getReservationByCustomerId(id));
    }

    public void deleteReservation() {
        System.out.println("Enter reservation id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (reservationService.deleteReservation(id)) {
            System.out.println("Reservation deleted successfully!");
        } else {
            System.out.println("Reservation not found!");
        }
    }

    public void cancelReservation() {
        System.out.println("Enter reservation id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (reservationService.cancelReservation(id)) {
            System.out.println("Reservation cancelled successfully!");
        } else {
            System.out.println("Reservation not found!");
        }
    }

    public void generateReports() {
        List<Reservation> allReservations = reservationService.getAllReservations();

        String occupancyReport = ReportGenerator.generateOccupancyReport(allReservations);
        DataPrinter.printReport("Occupancy", occupancyReport);

        String revenueReport = ReportGenerator.generateRevenueReport(allReservations);
        DataPrinter.printReport("Revenue", revenueReport);

        String cancellationReport = ReportGenerator.generateCancellationReport(allReservations);
        DataPrinter.printReport("Cancellations", cancellationReport);
    }

    public void showMenu() {
        System.out.println("1. Create reservation");
        System.out.println("2. Get reservation by id");
        System.out.println("3. Get all reservations");
        System.out.println("4. Get reservation by customer id");
        System.out.println("5. Delete reservation");
        System.out.println("6. Cancel reservation");
        System.out.println("7. Generate reports");
        System.out.println("0. Exit");

        boolean flag = true;
        while (flag) {
            System.out.println("Choose option:");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter location:");
                    String location = scanner.nextLine();
                    System.out.println("Enter room type:");
                    RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());
                    System.out.println(roomType);
                    System.out.println("Enter check-in date (yyyy-MM-dd):");
                    LocalDate checkInDate = LocalDate.parse(scanner.nextLine());

                    System.out.println("Enter check-out date (yyyy-MM-dd):");
                    LocalDate checkOutDate = LocalDate.parse(scanner.nextLine());
                    getAvailableRooms(location, roomType, checkInDate, checkOutDate);
                    System.out.println("Enter room id:");
                    int roomId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter customer ID:");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    createReservation(customerId,roomId, checkInDate, checkOutDate);
                    break;
                case 2:
                    getReservationById();
                    break;
                case 3:
                    getAllReservations();
                    break;
                case 4:
                    getReservationByCustomerId();
                    break;
                case 5:
                    deleteReservation();
                    break;
                case 6:
                    cancelReservation();
                    break;
                case 7:
                    generateReports();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
