package az.Cwiczenie15.controller;

import az.Cwiczenie15.model.Customer;
import az.Cwiczenie15.model.Trip;
import az.Cwiczenie15.repository.CustomerRepositoryJpa;
import az.Cwiczenie15.repository.TripRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepositoryJpa customerDao;

    @Autowired
    private TripRepositoryJpa tripDao;

    @GetMapping("/customers")
    public List<Customer> findAll(){
        return customerDao.findAll();
    }

    @GetMapping(value = "/customers",params = {"id"})
    public Customer getCustomerById(@RequestParam(name = "id", required = true) Long id){
        return customerDao.getCustomerById(id);
    }

    @GetMapping(value = "/customers",params = {"name"})
    public List<Customer> getCustomersByName(@RequestParam(name = "name", required = true) String name){
        return customerDao.getCustomersByName(name);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
        customerDao.save(customer);
    }

    @PostMapping("/customers/{customerId}/trips/{tripId}")
    public void addTripToCustomerByTripId(@PathVariable(name = "customerId") Long customerId, @PathVariable(name = "tripId") Long tripId){
        Customer customer = customerDao.getCustomerById(customerId);
        Trip trip = tripDao.getTripById(tripId);
        customer.setTrip(trip);
        customerDao.save(customer);
    }

    @PostMapping("/customers/{customerId}/trips")
    public void addTripToCustomer(@PathVariable(name = "customerId") Long customerId, @RequestBody Trip trip){
        Trip savedTrip = tripDao.save(trip);
        Customer customer = customerDao.getCustomerById(customerId);
        customer.setTrip(savedTrip);
        customerDao.save(customer);
    }

    @PutMapping("/customers")
    public void updateCustomer(@RequestBody Customer customer){
        customerDao.save(customer);
    }

    @DeleteMapping("/customers")
    public void deleteCustomer(@RequestParam(name = "id", required = true) Long id){
        customerDao.deleteCustomerById(id);
    }

//    @Autowired
//    private TravelOffice dao;
//
//    @GetMapping("/customers")
//    public List<Customer> getAll(){
//        return dao.getCustomers();
//    }
//
//    @GetMapping("/customers/{id}")
//    public Customer getCustomerById(@PathParam("id") Long id){
//        return dao.getCustomer(id);
//    }
//
//    @GetMapping(value = "/customers",params = {"name"})
//    public Customer getCustomerByName(@RequestParam(name = "name", required = true) String name){
//        return dao.getCustomer(name);
//    }
//
//    @PostMapping("/customers")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addCustomer(@RequestBody Customer customer) {
//        dao.addCustomer(customer);
//    }
//
//    @PutMapping("/customers")
//    public void updateCustomer(@RequestBody Customer customer){
//        dao.updateCustomer(customer);
//    }
//
//    @DeleteMapping("/customers")
//    public void deleteCustomer(@RequestParam(name = "id", required = true) Long id){
//        dao.deleteCustomer(id);
//    }
}
