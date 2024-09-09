package repository;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerRepository {
    private final CustomerDAO customerDAO = new CustomerDAO();

    public void saveCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public boolean deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }
}

