package util;

import model.Customer;
import model.Hotel;
import model.Reservation;
import model.Room;

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
}

