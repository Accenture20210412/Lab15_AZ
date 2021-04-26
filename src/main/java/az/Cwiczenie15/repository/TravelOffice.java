package az.Cwiczenie15.repository;

import az.Cwiczenie15.model.AbroadTrip;
import az.Cwiczenie15.model.Customer;
import az.Cwiczenie15.model.DomesticTrip;
import az.Cwiczenie15.model.Trip;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TravelOffice {
    private List<Customer> customers;
    private List<Trip> trips;

    public TravelOffice() {
        customers = new ArrayList<>();
        trips = new ArrayList<>();

        AbroadTrip abroadTrip1 = new AbroadTrip(1L,"FloridaTrip",LocalDate.of(2020, 11, 12), LocalDate.of(2020, 12, 12), "Florida", 11000, 500 );
        AbroadTrip abroadTrip2 = new AbroadTrip(2L,"Warsaw Dream", LocalDate.of(2020, 3, 10), LocalDate.of(2020, 3, 15), "Warszawa", 1000, 300);
        DomesticTrip domesticTrip = new DomesticTrip(3L,"Way to Katowice", LocalDate.of(2021, 9, 12), LocalDate.of(2021, 9, 20), "Katowice", 500, 100);
        Customer c1 = new Customer(1L,"Anna", "Kowalska","Naleczowska 10", abroadTrip1);
        Customer c2 = new Customer(2L, "Katarzyna", "Walesa", "Piotrkowska 3", abroadTrip2);
        Customer c3 = new Customer(3L, "Jakub", "Groch", "al. Jerozolimskie 110", domesticTrip);

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        trips.add(abroadTrip1);
        trips.add(abroadTrip2);
        trips.add(domesticTrip);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Customer getCustomer(Long id){
        Optional<Customer> customerById = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        if(customerById.isEmpty()){
            return null;
        }
        return customerById.get();
    }

    public Customer getCustomer(String name){
        Optional<Customer> customerById = customers.stream()
                .filter(c -> c.getName() == name)
                .findFirst();
        if(customerById.isEmpty()){
            return null;
        }
        return customerById.get();
    }

    public Trip getTrip(Long id){
        Optional<Trip> tripById = trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
        if(tripById.isEmpty()){
            return null;
        }
        return tripById.get();
    }

    public Trip getTrip(String name){
        Optional<Trip> tripByName = trips.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst();
        if(tripByName.isEmpty()){
            return null;
        }
        return tripByName.get();
    }

    public void addCustomer(Customer newCustomer) {
        customers.add(newCustomer);
    }

    public void addCustomer(Long id, String name, String surname, String address) {
        customers.add(new Customer(id, name, surname, address));
    }

    public void addCustomer(Long id, String name, String surname, String address, Trip trip) {
        customers.add(new Customer(id, name, surname, address, trip));
    }

    public int deleteCustomersByName(String name){
        int customersBeforeDeleting = customers.size();

        customers = customers.stream()
                        .filter(c -> !c.getName().contains(name))
                        .collect(Collectors.toList());

        return customersBeforeDeleting - customers.size();
    }

    public int deleteCustomersBySurname(String surname){
        int customersBeforeDeleting = customers.size();

        customers = customers.stream()
                        .filter(c -> !c.getSurname().contains(surname))
                        .collect(Collectors.toList());

        return customersBeforeDeleting - customers.size();
    }

    public void deleteCustomer(Long id){
        customers = customers.stream()
                .filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());
    }

    public void updateCustomer(Customer customer){
        deleteCustomer(customer.getId());
        customers.add(customer);
    }
    public void addTripToCustomer(Customer customer, Trip trip){
        customer.setTrip(trip);
    }

    public void addTrip(Trip trip){
        trips.add(trip);
    }

    public void deleteTrip(Long id){
        trips = trips.stream()
                .filter(t -> !t.getId().equals(id))
                .collect(Collectors.toList());
    }

    public void updateTrip(Trip trip){
        deleteTrip(trip.getId());
        trips.add(trip);
    }

    public Trip findTripByName(String tripName) {
        Optional<Trip> searchedTrip = trips.stream()
                .filter(t -> t.getName() == tripName)
                .findFirst();
        return searchedTrip.isPresent() ? searchedTrip.get() : null;
    }

    public List<Trip> findTripsAfter(LocalDate localDate){
        List<Trip> searchedTrips = trips.stream()
                .filter(t -> t.getStart().isAfter(localDate))
                .collect(Collectors.toList());
        return searchedTrips;
    }

    public List<Trip> findTripsByDestination(String destination) {
        List<Trip> searchedTrips = trips.stream()
                .filter(t -> t.getDestination().contains(destination))
                .collect(Collectors.toList());
        return searchedTrips;
    }

    public String getCustomersInfo() {
        String text="";
        for(Customer customer : customers){
            if(customer!=null) {
                text += customer.toString();
            }
        }
        return text;
    }
}
