package sample;

import java.util.Date;

public class Checkup {

    Employee assignee;
    Vehicle vehicle;
    Workshop workshop;
    Date date;
    boolean passedBrakeTest;
    boolean passedSteeringTest;
    boolean passedLightingTest;
    boolean passedEngineTest;
    boolean passedElectricalTest;

    public Checkup(Employee assignee, Vehicle vehicle, Workshop workshop, Date date, boolean passedBrakeTest, boolean passedSteeringTest,
                   boolean passedLightingTest, boolean passedEngineTest, boolean passedElectricalTest) {
        this.assignee = assignee;
        this.vehicle = vehicle;
        this.workshop = workshop;
        this.date = new Date(date.getTime());
        this.passedBrakeTest = passedBrakeTest;
        this.passedSteeringTest = passedSteeringTest;
        this.passedLightingTest = passedLightingTest;
        this.passedEngineTest = passedEngineTest;
        this.passedElectricalTest = passedElectricalTest;
    }

    public Checkup(Employee assignee, Vehicle vehicle, Workshop workshop, boolean passedBrakeTest, boolean passedSteeringTest,
                   boolean passedLightingTest, boolean passedEngineTest, boolean passedElectricalTest) {
        this.assignee = assignee;
        this.vehicle = vehicle;
        this.workshop = workshop;
        this.date = new Date();
        this.passedBrakeTest = passedBrakeTest;
        this.passedSteeringTest = passedSteeringTest;
        this.passedLightingTest = passedLightingTest;
        this.passedEngineTest = passedEngineTest;
        this.passedElectricalTest = passedElectricalTest;
    }

    public Employee getAssignee() {
        return assignee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public Date getDate() {
        return date;
    }

    public boolean isPassedBrakeTest() {
        return passedBrakeTest;
    }

    public boolean isPassedSteeringTest() {
        return passedSteeringTest;
    }

    public boolean isPassedLightingTest() {
        return passedLightingTest;
    }

    public boolean isPassedEngineTest() {
        return passedEngineTest;
    }

    public boolean isPassedElectricalTest() {
        return passedElectricalTest;
    }
}
