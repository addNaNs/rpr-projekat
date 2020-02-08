package sample;

import java.util.ArrayList;

public class Customer extends Person {

    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Customer(int id, String firstName, String lastName, String email, String profileImagePath, Gender sex) {
        super(id, firstName, lastName, email, profileImagePath, sex);
    }

    public Customer(int id, String firstName, String lastName, String email, String profileImagePath, Gender sex, ArrayList<Vehicle> vehicles) {
        super(id, firstName, lastName, email, profileImagePath, sex);
        this.vehicles = new ArrayList<>(vehicles);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
