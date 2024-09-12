import controller.CustomerController;
import controller.HotelController;
import controller.ReservationController;
import controller.RoomController;
import model.Room;
import model.RoomType;
import repository.RoomRepository;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Lancement de l'application
        // Menu de navigation pour gérer les réservations et les chambres

        HotelController hotelController = new HotelController();
        RoomController roomController = new RoomController();
        ReservationController reservationController = new ReservationController();
        CustomerController customerController = new CustomerController();

        Scanner scanner = new Scanner(System.in);

        // Menu principal
        System.out.println("Welcome to the Hotel Reservation System!");
        System.out.println("1. Manage hotels");
        System.out.println("2. Manage rooms");
        System.out.println("3. Manage reservations");
        System.out.println("4. Manage customers");
        System.out.println("5. Exit");

        int choice;
        do {
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    hotelController.showMenu();
                    break;
                case 2:
                    roomController.showMenu();
                    break;
                case 3:
                    reservationController.showMenu();
                    break;
                case 4:
                    customerController.showMenu();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }
}