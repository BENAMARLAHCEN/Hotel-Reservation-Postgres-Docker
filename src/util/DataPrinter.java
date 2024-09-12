package util;

import model.Customer;
import model.Hotel;
import model.Reservation;
import model.Room;
import service.PricingService;

import java.time.LocalDate;
import java.util.List;

public class DataPrinter {

    // Print Customer details in a structured format
    public static void printCustomerDetails(Customer customer) {
        System.out.println("Customer Details:");
        System.out.println("ID: " + customer.getCustomerId());
        System.out.println("Name: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Phone: " + customer.getPhoneNumber());
        System.out.println("-------------------------------------------------");
    }

    // Print Hotel details in a structured format
    public static void printHotelDetails(Hotel hotel) {
        System.out.println("Hotel Details:");
        System.out.println("ID: " + hotel.getHotelId());
        System.out.println("Name: " + hotel.getHotelName());
        System.out.println("Location: " + hotel.getLocation());
        System.out.println("-------------------------------------------------");
    }

    // Print Reservation details in a structured format
    public static void printReservationDetails(Reservation reservation) {
        System.out.println("Reservation Details:");
        System.out.println("Reservation ID: " + reservation.getReservationId());
        printCustomerDetails(reservation.getCustomer());
        printHotelDetails(reservation.getHotel());
        System.out.println("Room Type: " + reservation.getRoom().getRoomType());
        System.out.println("Check-in Date: " + reservation.getCheckInDate());
        System.out.println("Check-out Date: " + reservation.getCheckOutDate());
        System.out.println("Payment Status: " + reservation.getPaymentStatus());
        System.out.println("Total Cost: " + reservation.getTotalCost());
        System.out.println("-------------------------------------------------");
    }

    // Print Room details in a structured format
    public static void printRoomDetails(Room room) {
        System.out.println("Room Details:");
        System.out.println("Room ID: " + room.getRoomId());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Price: " + room.getPrice());
        System.out.println("-------------------------------------------------");
    }

    public static void printAllRooms(List<Room> allRooms) {
        System.out.println("All Rooms:");
        for (Room room : allRooms) {
            printRoomDetails(room);
        }
    }

    public static void printAllHotels(List<Hotel> allHotels) {
        System.out.println("All Hotels:");
        for (Hotel hotel : allHotels) {
            printHotelDetails(hotel);
        }
    }

    public static void printReport(String occupancy, String occupancyReport) {
        System.out.println(occupancy + " Report:");
        System.out.println(occupancyReport);
        System.out.println("-------------------------------------------------");
    }

    public static void printAllReservations(List<Reservation> allReservations) {
        System.out.println("All Reservations:");
        for (Reservation reservation : allReservations) {
            printReservationDetails(reservation);
            if (allReservations.indexOf(reservation) != allReservations.size() - 1) {
                System.out.println("=================================================");
            }
        }
    }

    public static void printAllCustomers(List<Customer> allCustomers) {
        System.out.println("All Customers:");
        for (Customer customer : allCustomers) {
            printCustomerDetails(customer);
        }
    }

    public static void printAvailableRooms(List<Room> availableRooms, LocalDate checkInDate, LocalDate checkOutDate) {
        System.out.println("Available Rooms from " + checkInDate + " to " + checkOutDate + ":");
        for (Room room : availableRooms) {
            System.out.println("Room Details:");
            System.out.println("Room ID: " + room.getRoomId());
            System.out.println("Room Type: " + room.getRoomType());
            System.out.println("Price: " + new PricingService().calculateTotalCost(room.getPrice(), checkInDate, checkOutDate));
            System.out.println("-------------------------------------------------");
        }
    }
}

