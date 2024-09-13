package service;

import model.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void createCustomer(String name, String email, String phoneNumber, double amountDue) {
        Customer customer = new Customer(0,name, email, phoneNumber, amountDue);
        customerRepository.saveCustomer(customer);
        System.out.println("Customer created successfully!");
    }

    public Customer getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public boolean deleteCustomer(int id) {
        return customerRepository.deleteCustomer(id);
    }

    public boolean updateCustomer(int id, String name, String email, String phoneNumber) {
        Customer customer = new Customer(id, name, email, phoneNumber);
        return customerRepository.updateCustomer(customer);
    }

    public void payAmountDue(int id, double amount) {
        if (getCustomerById(id) == null) {
            System.out.println("Customer not found!");
        }
        if (customerRepository.payAmountDue(id, amount)) {
            System.out.println("Amount paid successfully!");
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void addAmountDue(int id2, double amount2) {
        if (getCustomerById(id2) == null) {
            System.out.println("Customer not found!");
        }
        if (customerRepository.addAmountDue(id2, amount2)) {
            System.out.println("Amount added successfully!");
        } else {
            System.out.println("Customer not found!");
        }
    }
}
