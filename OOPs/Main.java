import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Car{
    private String carID;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carID = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true; // Cars are available by default
    }

    public String getCarID() {
        return carID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay + rentalDays;
    }

    public void returnCar(){
        isAvailable = true;
    }

    public boolean isAvailable(){
        return isAvailable;
    }   

    public void rent() {
        if (isAvailable) {
            isAvailable = false; // Mark the car as rented
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

}

class Customer{
    private String CustomerName;
    private String CustomerID;

    public Customer(String CustomerName, String CustomerID){
        this.CustomerName = CustomerName;
        this.CustomerID = CustomerID;    
    }

    public String getCustomerName(){
        return CustomerName;
    }

    public String getCustomerID(){
        return CustomerID;
    }
}

class Rental{
    private Car car;
    private Customer customer;
    private int rentalDays;

    public Rental(Car car, Customer customer, int rentalDays){
        this.car = car;
        this.customer = customer;
    }

    public Car getCar(){
        return car;
    }

    public Customer getCustomer(){
        return customer;
    }

    public int getRentalDays(){
        return rentalDays;
    }
}

class CarRentalSystem{
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int rentalDays){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car,customer,rentalDays));
        } else{
            System.out.println("Car is not available for rent.");
        }
    }
}