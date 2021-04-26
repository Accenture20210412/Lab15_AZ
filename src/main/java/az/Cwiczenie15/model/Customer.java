package az.Cwiczenie15.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "customer_name", length = 20)
    private String name;
    @Column(name = "customer_surname", length = 40)
    private String surname;
    @Column(name = "customer_address", length = 100)
    private String address;
    @Transient
    private Trip trip;

    public Customer(){

    }

    public Customer(int id, String name, String surname, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Customer(int id, String name, String surname, String address, Trip trip) {
        this(id, name, surname, address);
        this.trip = trip;
    }

    @Override
    public String toString() {
        if(trip != null) {
            return "Customer: Name: " + getName()
                    + " Surname: " + getSurname()
                    + " Address: " + getAddress()
                    + " Trip: " + getTrip();
        }
        else {
            return "Customer: Name: " + getName()
                    + " Surname: " + getSurname()
                    + " Address: " + getAddress();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getId() {return id;}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Trip getTrip() {
        return trip;
    }
}
