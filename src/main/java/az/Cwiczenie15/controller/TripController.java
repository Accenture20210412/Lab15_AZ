package az.Cwiczenie15.controller;

import az.Cwiczenie15.model.AbroadTrip;
import az.Cwiczenie15.model.DomesticTrip;
import az.Cwiczenie15.model.Trip;
import az.Cwiczenie15.repository.TravelOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TravelOffice dao;

    @GetMapping("/trips")
    public List<Trip> getAll(){
        return dao.getTrips();
    }

    @GetMapping("/trips/domesticTrips")
    public List<Trip> getAllDomesticTrips(){
        return dao.getTrips().stream()
                .filter(t -> t instanceof DomesticTrip)
                .collect(Collectors.toList());
    }

    @GetMapping("/trips/abroadTrips")
    public List<Trip> getAllAbroadTrips(){
        return dao.getTrips().stream()
                .filter(t -> t instanceof AbroadTrip)
                .collect(Collectors.toList());
    }

    @GetMapping("/trips/{id}")
    public Trip getTripById(@PathParam("id") int id){
        return dao.getTrip(id);
    }

    @GetMapping(value = "/trips", params = "name")
    public Trip getTripByName(@RequestParam(name = "name", required = true) String name){
        return dao.getTrip(name);
    }

    @PostMapping("/trips")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrip(@RequestBody Trip trip) {
        dao.addTrip(trip);
    }

    @PutMapping("/trips")
    public void updateTrip(@RequestBody Trip trip){
        dao.updateTrip(trip);
    }

    @DeleteMapping("/trips")
    public void deleteTrip(@RequestParam(name = "id", required = true) int id){
        dao.deleteTrip(id);
    }
}
