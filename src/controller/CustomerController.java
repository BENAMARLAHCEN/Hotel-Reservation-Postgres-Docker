package controller;

import service.CustomerService;
import util.DataPrinter;

import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService = new CustomerService();
    public Scanner scanner = new Scanner(System.in);

    public void createCustomer() {
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer email:");
        String email = scanner.nextLine();
        System.out.println("Enter customer phone number:");
        String phoneNumber = scanner.nextLine();
        customerService.createCustomer(name, email, phoneNumber);
    }

    public void getCustomerById() {
        System.out.println("Enter customer id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        DataPrinter.printCustomerDetails(customerService.getCustomerById(id));
    }

    public void getAllCustomers() {
        DataPrinter.printAllCustomers(customerService.getAllCustomers());
    }

    public void deleteCustomer() {
        System.out.println("Enter customer id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (customerService.deleteCustomer(id)) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void updateCustomer() {
        System.out.println("Enter customer id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter new customer email:");
        String email = scanner.nextLine();
        System.out.println("Enter new customer phone number:");
        String phoneNumber = scanner.nextLine();
        if (customerService.updateCustomer(id, name, email, phoneNumber)) {
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void showMenu() {
        System.out.println("1. Create customer");
        System.out.println("2. Get customer by id");
        System.out.println("3. Get all customers");
        System.out.println("4. Delete customer");
        System.out.println("5. Update customer");
        System.out.println("6. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                createCustomer();
                break;
            case 2:
                getCustomerById();
                break;
            case 3:
                getAllCustomers();
                break;
            case 4:
                deleteCustomer();
                break;
            case 5:
                updateCustomer();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
