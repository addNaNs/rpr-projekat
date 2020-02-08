package sample;

import java.util.Date;

public class Checkup {

    Employee assignee;
    Vehicle vehicle;
    Date date;

    public Checkup(Employee assignee, Vehicle vehicle, Date date) {
        this.assignee = assignee;
        this.vehicle = vehicle;
        this.date = new Date(date.getTime());
    }

    public Checkup(Employee assignee, Vehicle vehicle) {
        this.assignee = assignee;
        this.vehicle = vehicle;
        this.date = new Date();
    }

    public Employee getAssignee() {
        return assignee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getDate() {
        return date;
    }
}
