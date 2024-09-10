package repository.inter;

import model.Customer;

import java.util.List;

public interface ICustomerRepository {
    void saveCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    boolean deleteCustomer(int customerId);

    boolean updateCustomer(Customer customer);

}
