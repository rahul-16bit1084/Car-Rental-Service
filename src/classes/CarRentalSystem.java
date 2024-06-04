package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> carList;
    private List<Customer> customerList;
    private List<Rental> rentalList;

    public CarRentalSystem(){
        carList = new ArrayList<>();
        customerList = new ArrayList<>();
        rentalList = new ArrayList<>();
    }
    public void addCar(Car car){
        carList.add(car);
    }
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
    public void rentCar(Car car, Customer customer, int days){
        if(car.isAvailable()){
            car.rent();
            rentalList.add(new Rental(car,customer,days));
        }
        else System.out.println("Car available nahi hai ji , sorry");
    }
    public void returnCar(Car car){
        car.returnCar();  // it means car is available now
        Car carToRemove = null;
        for (Rental r : rentalList){
            if(r.getCar() == car){
                carToRemove = r.getCar();
                break;
            }
        }
        if (carToRemove != null){
            rentalList.remove(carToRemove);
            System.out.println("Car returned Success ");
        }
        else System.out.println("N0t rented ");
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\n=== Rent a Car ===\n");
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();

                for (Car car : carList) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel() + " " + car.getBasePricePerDay());
                    }
                }

                System.out.print("\nEnter the car ID you want to rent: ");
                String carId = sc.nextLine();
                System.out.print("Enter the number of days for rental: ");
                int rentalDays = sc.nextInt();
                sc.nextLine(); // Consume newline

                Customer  newCustomer = new Customer("CUS"+customerList.size(),customerName);
                customerList.add(newCustomer);

                // car selection for customer
                Car selectedCar = null;
                for (Car car : carList){
                    if(car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar = car; break;
                    }
                }

                if(selectedCar == null){
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
                else{
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = sc.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    }
                    else{
                        System.out.println("\nRental canceled.");
                    }
                }
            }
            else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");
                String carId = sc.nextLine();

                Car rentedCar = null;
                for (Car car : carList) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        rentedCar = car;
                        break;
                    }
                }

                if (rentedCar == null) {
                    System.out.println("Invalid car ID or car is not rented.");
                } else {
                    Customer customer = null;
                    for (Rental rental : rentalList) {
                        if (rental.getCar() == rentedCar) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(rentedCar);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                }
            }
            else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\nThank you for using the Car Rental System!");
    }
}
