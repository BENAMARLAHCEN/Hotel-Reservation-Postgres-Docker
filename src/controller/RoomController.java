package controller;

import model.RoomType;
import service.RoomService;
import util.DataPrinter;

import java.util.Date;
import java.util.Scanner;

public class RoomController {

    private final RoomService roomService = new RoomService();
    public Scanner scanner = new Scanner(System.in);


    public void createRoom() {
        System.out.println("Enter room hotel id:");
        int hotelId = scanner.nextInt();
        System.out.println("Enter room type:");
        RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Enter price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        roomService.createRoom(roomType, price, hotelId);
    }

    public void getRoomById() {
        System.out.println("Enter room id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        DataPrinter.printRoomDetails(roomService.getRoomById(id));
    }

    public void getAllRooms() {
        DataPrinter.printAllRooms(roomService.getAllRooms());
    }

    public void deleteRoom() {
        System.out.println("Enter room id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (roomService.deleteRoom(id)) {
            System.out.println("Room deleted successfully!");
        } else {
            System.out.println("Room not found!");
        }
    }

    public void updateRoom() {
        System.out.println("Enter room id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new room type:");
        RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Enter new price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        int hotelId = new RoomService().getRoomById(id).getHotelId();
        if (roomService.updateRoom(id, roomType, price, hotelId)) {
            System.out.println("Room updated successfully!");
        } else {
            System.out.println("Room not found!");
        }
    }

    public void showMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Create room");
            System.out.println("2. Get room by id");
            System.out.println("3. Get all rooms");
            System.out.println("4. Delete room");
            System.out.println("5. Update room");
            System.out.println("0. Back to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    getRoomById();
                    break;
                case 3:
                    getAllRooms();
                    break;
                case 4:
                    deleteRoom();
                    break;
                case 5:
                    updateRoom();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

}
