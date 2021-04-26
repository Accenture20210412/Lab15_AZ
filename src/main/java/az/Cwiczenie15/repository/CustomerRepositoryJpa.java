package az.Cwiczenie15.repository;

import az.Cwiczenie15.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepositoryJpa extends CrudRepository<Customer, Long> {

    List<Customer> findAll();
    Customer save(Customer customer);
}
