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

    public void returnCar(Car car){
        Rental rentalToRemove = null; 
        for(Rental rental : rentals) {
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }
        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
            car.returnCar();
            System.out.println("Car returned successfully");
        } else{
            System.out.println("Car was not rented from this system.");
        }
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("==== Car Rental System ====");
            System.out.println("1. Add Car");
            System.out.println("2. Return Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character

            if(choice == 1){
                System.out.print("\n === Rent a Car === \n");
                System.out.println("Enter your name: ");
                String customerName = sc.nextLine();

                System.out.println("\n Available cars: ");
                for(Car car: cars){
                    if(car.isAvailable()){
                        System.out.println("Car ID: " + car.getCarID() + ", Brand: " + car.getBrand() + ", Model: " + car.getModel() + ", Base Price per Day: " + car.calculatePrice(1));
                    }
                }
            }

            if(choice == 2){
                System.out.print("\n === Return a Car === \n");
                System.out.println("Enter Car ID to return: ");
                String carId = sc.nextLine();
                Car carToReturn = null;

                for(Car car : cars) {
                    if(car.getCarID().equals(carId)) {
                        carToReturn = car;
                        break;
                    }
                }

                if(carToReturn != null) {
                    returnCar(carToReturn);
                } else {
                    System.out.println("Car with ID " + carId + " not found.");
                }
            }
        }
    }
}