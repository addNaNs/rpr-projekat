package sample;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Customer extends Person {


    public Customer(int id, String firstName, String lastName, String email, String profileImagePath, Gender sex) {
        super(id, firstName, lastName, email, profileImagePath, sex);
    }

    public ArrayList<Vehicle> getVehicles() {
        ArrayList<Vehicle> vehicles = RegistrationDAO.getInstance().vehicles();
        return (ArrayList<Vehicle>) vehicles.stream().filter(v -> v.getOwnerId()==this.getId()).collect(Collectors.toList());
    }
}
