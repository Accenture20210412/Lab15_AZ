package az.Cwiczenie15.repository;

import az.Cwiczenie15.model.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripRepositoryJpa extends CrudRepository<Trip, Long> {
    List<Trip> findAll();
    Trip save(Trip trip);
}
