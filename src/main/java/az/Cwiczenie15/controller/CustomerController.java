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
    private CustomerRepositoryJpa daoCustomer;

    @Autowired
    private TripRepositoryJpa daoTrip;

    @GetMapping("/customers")
    public List<Customer> findAll(){
        return daoCustomer.findAll();
    }

    @GetMapping(value = "/customers",params = {"id"})
    public Customer getCustomerById(@RequestParam(name = "id", required = true) Long id){
        return daoCustomer.getCustomerById(id);
    }

    @GetMapping(value = "/customers",params = {"name"})
    public List<Customer> getCustomersByName(@RequestParam(name = "name", required = true) String name){
        return daoCustomer.getCustomersByName(name);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
        daoCustomer.save(customer);
    }

    @PostMapping("/customers/{customerId}/trips")
    public void addTripToCustomer(@PathVariable(name = "customerId") Long customerId, @RequestParam(name = "tripId") Long tripId){
        Customer customer = daoCustomer.getCustomerById(customerId);
        Trip trip = daoTrip.getTripById(tripId);
        customer.setTrip(trip);
        daoCustomer.save(customer);
    }

    @PutMapping("/customers")
    public void updateCustomer(@RequestBody Customer customer){
        daoCustomer.save(customer);
    }

    @DeleteMapping("/customers")
    public void deleteCustomer(@RequestParam(name = "id", required = true) Long id){
        daoCustomer.deleteCustomerById(id);
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
