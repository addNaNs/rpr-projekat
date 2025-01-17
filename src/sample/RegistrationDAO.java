package sample;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationDAO {
    private static RegistrationDAO instance;
    private Connection conn;

    private PreparedStatement glavniGradUpit, dajDrzavuUpit, obrisiDrzavuUpit, obrisiGradoveZaDrzavuUpit, nadjiDrzavuUpit,
            dajGradoveUpit, dodajGradUpit, odrediIdGradaUpit, dodajDrzavuUpit, odrediIdDrzaveUpit, promijeniGradUpit, dajGradUpit,
            nadjiGradUpit, obrisiGradUpit, dajDrzaveUpit;

    public static RegistrationDAO getInstance() {
        if (instance == null) instance = new RegistrationDAO();
        return instance;
    }

    private RegistrationDAO() {
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");

            Statement statement = null;
            try{
                statement = conn.createStatement();
                statement.execute("select id from employee");

            }catch (Exception e){
                try{
                    statement.execute("create table employee(id integer primary key, first_name varchar(40)," +
                            " last_name varchar(40), email varchar(100), profile_image_path varchar(1000)," +
                            " gender varcahr(10))");

                    statement.execute("create table customer(id integer primary key, first_name varchar(40)," +
                            " last_name varchar(40), email varchar(100), profile_image_path varchar(1000)," +
                            " gender varcahr(10))");

                    statement.execute("create table vehicle(plates varchar(15) primary key, model varchar(100)," +
                            " owner_id varchar(40) references customer(id), category varchar(1))");

                    statement.execute("create table workshop(id integer primary key)");

                    statement.execute("create table examinable(id integer primary key," +
                            " workshop_id integer references workshop(id), category varchar(1))");

                    statement.execute("create table checkup(id integer primary key," +
                            " assignee_id integer references employee(id), vehicle_plates varchar(15) references vehicle(plates)," +
                            " workshop_id integer references workshop(id), passedBrakeTest integer, passedSteeringTest integer," +
                            " passedLightingTest integer, passedEngineTest integer, passedElectricalTest integer, date date)");

                    statement.execute("insert into employee values (1,'imenko','prezimenkovic','bla@bla','images/007.png','male')");
                    statement.execute("insert into employee values (2,'imenko2','prezimenkovic2','bla@bla2','images/121.png','male')");
                    statement.execute("insert into employee values (3,'imenko3','prezimenkovic3','bla@bla3','images/129.png','male')");

                    statement.execute("insert into customer values (1,'imenko4','prezimenkovic4','bla@bla4','images/172.png','male')");
                    statement.execute("insert into customer values (2,'imenko5','prezimenkovic5','bla@bla5','images/257.png','male')");
                    statement.execute("insert into customer values (3,'imenko6','prezimenkovic6','bla@bla6','images/304.png','male')");

                    statement.execute("insert into vehicle values ('123','Golf',1, 'B')");
                    statement.execute("insert into vehicle values ('124','Gol2',1, 'B')");
                    statement.execute("insert into vehicle values ('125','Kawasaki',2, 'A')");
                    statement.execute("insert into vehicle values ('126','Cat',3, 'E')");

                    statement.execute("insert into workshop values (1)");
                    statement.execute("insert into workshop values (2)");

                    statement.execute("insert into examinable values(1, 1, 'A')");
                    statement.execute("insert into examinable values(2, 2, 'B')");
                    statement.execute("insert into examinable values(3, 2, 'C')");
                    statement.execute("insert into examinable values(4, 2, 'D')");
                    statement.execute("insert into examinable values(5, 2, 'E')");

                    statement.execute("insert into checkup values (1,1,'123',2, 1, 1, 1, 1, 1, current_date)");
                    statement.execute("insert into checkup values (2,2,'124',2, 1, 1, 1, 1, 1, current_date)");

                }catch (Exception ex){

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sortCharsInString(String s){
        char[] arrayList = s.toCharArray();
        int n = s.length();
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-1; j++){
                if(arrayList[j] > arrayList[j+1]){
                    char tmp = arrayList[j];
                    arrayList[j] = arrayList[j+1];
                    arrayList[j+1] = tmp;
                }
            }
        }
    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> employees(){
        ArrayList<Employee> employees = new ArrayList<>();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while(resultSet.next()){
                Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6).toLowerCase().equals("male") ? Person.Gender.Male : Person.Gender.Female);
                employees.add(employee);
            }

            return employees;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Vehicle> vehicles(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicle");
            while(resultSet.next()){
                Vehicle vehicle = new Vehicle(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4).charAt(0));
                vehicles.add(vehicle);
            }

            return vehicles;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Customer> customers(){
        ArrayList<Customer> customers = new ArrayList<>();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer");
            while(resultSet.next()){
                Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6).toLowerCase().equals("male") ? Person.Gender.Male : Person.Gender.Female);

                customers.add(customer);
            }

            return customers;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Workshop> workshops(){
        ArrayList<Workshop> workshops = new ArrayList<>();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from workshop");
            while(resultSet.next()){
                String examinable = "";
                Statement statement1 = conn.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("select * from examinable where workshop_id="+resultSet.getInt(1));
                while (resultSet1.next()){
                    examinable += resultSet1.getString(3);
                }
                sortCharsInString(examinable);

                try {
                    Workshop workshop = new Workshop(resultSet.getInt(1), examinable);
                    workshops.add(workshop);
                }catch (IllegalCategoryException ex){
                    ex.printStackTrace();
                }
            }


            return workshops;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Checkup> checkups(){
        ArrayList<Checkup> checkups = new ArrayList<>();

        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from checkup");
            while(resultSet.next()){
                Statement statement1 = conn.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("select * from employee where id="+resultSet.getInt(2));
                Employee assignee = null;
                while (resultSet1.next()){
                    assignee = new Employee(resultSet1.getInt(1), resultSet1.getString(2),
                            resultSet1.getString(3), resultSet1.getString(4), resultSet1.getString(5),
                            resultSet1.getString(6).toLowerCase().equals("male") ? Person.Gender.Male : Person.Gender.Female);
                }

                resultSet1 = statement1.executeQuery("select * from vehicle where plates="+resultSet.getInt(3));
                Vehicle vehicle = null;
                while (resultSet1.next()){
                    vehicle = new Vehicle(resultSet1.getString(1), resultSet1.getString(2),
                            resultSet1.getInt(3), resultSet1.getString(4).charAt(0));
                }

                resultSet1 = statement1.executeQuery("select * from workshop where id="+resultSet.getInt(4));
                Workshop workshop = null;
                while (resultSet1.next()){
                    String examinable = "";
                    Statement statement2 = conn.createStatement();
                    ResultSet resultSet2 = statement2.executeQuery("select * from examinable where workshop_id="+resultSet1.getInt(1));
                    while (resultSet2.next()){
                        examinable += resultSet2.getString(3);
                    }
                    sortCharsInString(examinable);

                    try {
                        workshop = new Workshop(resultSet.getInt(1), examinable);
                    } catch (IllegalCategoryException e) {
                        e.printStackTrace();
                    }
                }

                String sDate = resultSet.getString(10);
                Date date = new SimpleDateFormat("dd-mm-yyyy").parse(sDate);


                Checkup checkup = new Checkup(assignee,vehicle, workshop, date, resultSet.getBoolean(5), resultSet.getBoolean(6), resultSet.getBoolean(7), resultSet.getBoolean(8), resultSet.getBoolean(9));
                checkups.add(checkup);
            }


            return checkups;

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateEmployee(Employee employee){
        try {
            Statement statement = conn.createStatement();
            statement.execute("update employee set " +
                    "first_name='" + employee.getFirstName() +"', " +
                    "last_name='" + employee.getLastName() + "', " +
                    "email='" + employee.getEmail() + "', " +
                    "profile_image_path='" + employee.getProfileImagePath() + "'," +
                    "gender='" + (employee.getSex()==Person.Gender.Male ? "male" : "female") + "'" +
                    "where id=" + employee.getId()+";"
                   );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee){
        Statement statement = null;
        try {
            int maxId = 1;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from employee");
            while (resultSet.next()) maxId = resultSet.getInt(1) + 1;
            statement.execute("insert into employee values(" + maxId +", '" + employee.getFirstName() +"', '"
                    + employee.getLastName() + "', '" + employee.getEmail() + "', '" + employee.getProfileImagePath()
                    + "', '" + (employee.getSex() == Person.Gender.Male ? "male" : "female") + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployee(Employee employee){
        try {
            Statement statement = conn.createStatement();
            statement.execute("delete from employee where id=" + employee.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addWorkshop(Workshop workshop) {
        Statement statement = null;
        try {
            int maxId = 1;
            int maxId1 = 1;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from workshop");
            while (resultSet.next()) maxId = resultSet.getInt(1) + 1;

            resultSet = statement.executeQuery("select  max(id) from examinable");
            while (resultSet.next()) maxId1 = resultSet.getInt(1) + 1;

            statement.execute("insert into workshop values(" + maxId + ");");
            for (char c : workshop.getExaminableCategories().toCharArray()) {
                statement.execute("insert into examinable values(" + (maxId1++) + "," + maxId + ",'" + c + "');");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWorkshop(Workshop workshop){
        try {
            Statement statement = conn.createStatement();
            statement.execute("delete from examinable where workshop_id=" + workshop.getId());
            statement.execute("delete from workshop where id=" + workshop.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWorkshop(Workshop workshop){
        try {
            int maxId=1;
            Statement statement = conn.createStatement();
            statement.execute("delete from examinable where workshop_id="+workshop.getId());
            ResultSet resultSet = statement.executeQuery("select  max(id) from examinable");
            while (resultSet.next()) maxId = resultSet.getInt(1) + 1;
            for (char c : workshop.getExaminableCategories().toCharArray()) {
                statement.execute("insert into examinable values(" + (maxId++) + "," + workshop.getId() + ",'" + c + "')");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer customer){
        Statement statement = null;
        try {
            int maxId = 1;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from customer");
            while (resultSet.next()) maxId = resultSet.getInt(1) + 1;
            statement.execute("insert into customer values(" + maxId +", '" + customer.getFirstName() +"', '"
                    + customer.getLastName() + "', '" + customer.getEmail() + "', '" + customer.getProfileImagePath()
                    + "', '" + (customer.getSex() == Person.Gender.Male ? "male" : "female") + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addVehicle(Vehicle vehicle){
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.execute("insert into vehicle values('" + vehicle.getPlates() +"', '"
                    + vehicle.getModel() + "', " + vehicle.getOwnerId() + ", '" + vehicle.getCategory()
                    + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCheckup(Checkup checkup){
        Statement statement = null;
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        String useless = dateFormat.format(checkup.getDate());
        try {
            int maxId = 1;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from checkup");
            while (resultSet.next()) maxId = resultSet.getInt(1) + 1;

            statement.execute("insert into checkup values(" + maxId +", " + checkup.getAssignee().getId() +", '"
                    + checkup.getVehicle().getPlates() + "', " + checkup.getWorkshop().getId() + ", " + (checkup.isPassedBrakeTest()?1:0)
                    + ", " + (checkup.isPassedSteeringTest()?1:0) + ", " + (checkup.isPassedLightingTest()?1:0) + "," + (checkup.isPassedEngineTest()?1:0)
                    + "," + (checkup.isPassedElectricalTest()?1:0) + ", '" + dateFormat.format(checkup.getDate()) +  "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void saveFile(File file) {
        try {
            if(file == null) return;

            PrintWriter writer = new PrintWriter(file);

            Statement statement = conn.createStatement();
            ResultSet r = statement.executeQuery(
                    "select v.plates, v.model, cus.first_name || ' ' || cus.last_name, e.first_name || ' ' || e.last_name, c.date,\n" +
                            "c.passedBrakeTest*c.passedSteeringTest*c.passedLightingTest*c.passedEngineTest*c.passedElectricalTest\n" +
                            "from checkup c, employee e, vehicle v, customer cus, workshop w\n" +
                            "where c.assignee_id=e.id and v.plates=c.vehicle_plates and cus.id=v.owner_id and c.workshop_id=w.id;");
            writer.println("vehicle_plates, vehicle_model, customer_name, employee_name, date, passed");
            while(r.next()){
                writer.println(r.getString(1)+","+r.getString(2)+","+r.getString(3)+","+r.getString(4)+","+r.getString(5)+","+r.getString(6));
            }

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
