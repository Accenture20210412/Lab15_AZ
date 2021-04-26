package az.Cwiczenie15.controller;

import az.Cwiczenie15.model.Customer;
import az.Cwiczenie15.repository.TravelOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
//@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private TravelOffice dao;

    @GetMapping("/customers")
    public List<Customer> getAll(){
        return dao.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathParam("id") int id){
        return dao.getCustomer(id);
    }

    @GetMapping(value = "/customers",params = {"name"})
    public Customer getCustomerByName(@RequestParam(name = "name", required = true) String name){
        return dao.getCustomer(name);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) {
        dao.addCustomer(customer);
    }

    @PutMapping("/customers")
    public void updateCustomer(@RequestBody Customer customer){
        dao.updateCustomer(customer);
    }

    @DeleteMapping("/customers")
    public void deleteCustomer(@RequestParam(name = "id", required = true) int id){
        dao.deleteCustomer(id);
    }
}
