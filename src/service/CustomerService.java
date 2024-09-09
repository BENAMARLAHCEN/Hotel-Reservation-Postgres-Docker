package service;

import model.Customer;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void createCustomer(String name, String email, String phoneNumber) {
        Customer customer = new Customer(0,name, email, phoneNumber);
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
}
