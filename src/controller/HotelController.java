package controller;

import service.HotelService;
import util.DataPrinter;

import java.util.Scanner;

public class HotelController {
    private final HotelService hotelService = new HotelService();
    public Scanner scanner = new Scanner(System.in);

    public void createHotel() {
        System.out.println("Enter hotel name:");
        String name = scanner.nextLine();
        System.out.println("Enter hotel location:");
        String location = scanner.nextLine();
        hotelService.createHotel(name, location);
    }

    public void getHotelById() {
        System.out.println("Enter hotel id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        DataPrinter.printHotelDetails(hotelService.getHotelById(id));
    }

    public void getAllHotels() {
        DataPrinter.printAllHotels(hotelService.getAllHotels());
    }

    public void deleteHotel() {
        System.out.println("Enter hotel id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (hotelService.deleteHotel(id)) {
            System.out.println("Hotel deleted successfully!");
        } else {
            System.out.println("Hotel not found!");
        }
    }

    public void updateHotel() {
        System.out.println("Enter hotel id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new hotel name:");
        String name = scanner.nextLine();
        System.out.println("Enter new hotel location:");
        String location = scanner.nextLine();
        if (hotelService.updateHotel(id, name, location)) {
            System.out.println("Hotel updated successfully!");
        } else {
            System.out.println("Hotel not found!");
        }
    }

    public void showMenu() {
        System.out.println("1. Create hotel");
        System.out.println("2. Get hotel by id");
        System.out.println("3. Get all hotels");
        System.out.println("4. Delete hotel");
        System.out.println("5. Update hotel");
        System.out.println("0. Exit");

        boolean flag = true;
        while (flag) {
            System.out.println("Choose option:");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    createHotel();
                    break;
                case 2:
                    getHotelById();
                    break;
                case 3:
                    getAllHotels();
                    break;
                case 4:
                    deleteHotel();
                    break;
                case 5:
                    updateHotel();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }
}
