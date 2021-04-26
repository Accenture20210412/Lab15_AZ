package az.Cwiczenie15.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Trip {
    @Id
    @Column(name = "trip_id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "trip_name", length = 30)
    private String name;
    @Column(name = "trip_start")
    private LocalDate start;
    @Column(name = "trip_finish")
    private LocalDate finish;
    @Column(name = "trip_destination", length = 20)
    private String destination;
    @Column(name = "trip_price")
    private int price;

    public Trip() {
    }

    public Trip(Long id, String name, LocalDate start, LocalDate finish, String destination, int price) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.destination = destination;
        this.price = price;
    }

    @Override
    public String toString() {
        String info = "Trip: Start: " + getStart().toString()
                +" Finish: " + getFinish()
                +" Destination: " + getDestination()
                +" Price: " + getPrice();
        return info;
    }

    public Long getId() {return id;}
    public String getName() { return name; }
    public LocalDate getStart() {
        return start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) { this.name = name; }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
