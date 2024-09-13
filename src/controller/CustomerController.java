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
        System.out.println("Enter customer amount due:");
        String amountDueStr = scanner.nextLine();
        double amountDue = Double.parseDouble(amountDueStr.replace(",", "."));
        customerService.createCustomer(name, email, phoneNumber, amountDue);
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
        System.out.println("6. Pay amount due");
        System.out.println("7. Add amount due");
        System.out.println("0. Back");
        boolean exit = false;
        do {
            System.out.println("Enter choice:");
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
                System.out.println("Enter customer id:");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter amount to pay:");
                String amountDueStr = scanner.nextLine();
                double amount = Double.parseDouble(amountDueStr.replace(",", "."));
                customerService.payAmountDue(id, amount);
                break;
            case 7:
                System.out.println("Enter customer id:");
                int id2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter amount to add:");
                String amountDueStr2 = scanner.nextLine();
                double amount2 = Double.parseDouble(amountDueStr2.replace(",", "."));
                customerService.addAmountDue(id2, amount2);
                break;
            case 0:
                exit = true;
                break;
            default:
                System.out.println("Invalid choice!");
        }
        } while (!exit);
    }
}
