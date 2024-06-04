import classes.Car;
import classes.CarRentalSystem;
import classes.Customer;
import classes.Rental;

import java.util.ArrayList;
import java.util.List;
class A{
    public int mul(int a, int b, int c){
        return (int)a*b*c;
    }
}
class HelloWorld extends A{

    public int mul(int a, int b){
        return a*b;
    }

    public static void main(String[] args) {
        HelloWorld h = new HelloWorld();
        System.out.println(h.mul(1,2,3));
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Car Rental System ");
        CarRentalSystem carRentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);

        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);

        carRentalSystem.menu();
    }
}