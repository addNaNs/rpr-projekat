package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
                            " assignee_id integer references employee(id), vehicle_plates integer references vehicle(plates)," +
                            " workshop_id integer references workshop(id), date date)");

                    statement.execute("insert into employee values (1,'imenko','prezimenkovic','bla@bla','nema','male')");
                    statement.execute("insert into employee values (2,'imenko2','prezimenkovic2','bla@bla2','nema','male')");
                    statement.execute("insert into employee values (3,'imenko3','prezimenkovic3','bla@bla3','nema','male')");

                    statement.execute("insert into customer values (1,'imenko4','prezimenkovic4','bla@bla4','nema','male')");
                    statement.execute("insert into customer values (2,'imenko5','prezimenkovic5','bla@bla5','nema','male')");
                    statement.execute("insert into customer values (3,'imenko6','prezimenkovic6','bla@bla6','nema','male')");

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

                    statement.execute("insert into checkup values (1,1,'123',2,SYSDATE)");

                }catch (Exception ex){

                }
            }

        } catch (SQLException e) {
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


}
