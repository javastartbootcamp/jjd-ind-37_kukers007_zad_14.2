package pl.javastart.task;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static Queue<Car> carsQueue = new LinkedList<>();
    private static final String FILE_NAME = "cars.txt";

    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:FallThrough"})
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (
                var fis = new FileInputStream(FILE_NAME);
                var ois = new ObjectInputStream(fis);
        ) {
            carsQueue = (Queue<Car>) ois.readObject();
            System.out.println("Read from file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Read from file failed");
        }

        int option = -1;
        while (option != 0) {
            System.out.println("Menu :");
            System.out.println("0 - EXIT");
            System.out.println("1 - Add new car to queue");
            System.out.println("2 - Print info about next car in queue.");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0:
                    if (carsQueue.isEmpty()) {
                        scanner.close();
                    } else {
                        try (
                                var fis = new FileOutputStream(FILE_NAME);
                                var oos = new ObjectOutputStream(fis);
                        ) {
                            oos.writeObject(carsQueue);
                            System.out.println("Queue saved to file.");
                        } catch (IOException e) {
                            System.out.println("Error, queue not save into file.");
                        }
                    }
                    break;
                case 1:
                    System.out.println("Enter car type:");
                    String type = scanner.nextLine();
                    System.out.println("Enter car brand:");
                    String brand = scanner.nextLine();
                    System.out.println("Enter car model:");
                    String model = scanner.nextLine();
                    System.out.println("Enter year:");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter car mileage:");
                    int mileage = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter VIN number:");
                    String vin = scanner.nextLine();
                    Car car = new Car(type, brand, model, year, mileage, vin);
                    carsQueue.add(car);
                    System.out.println("Car successful added to queue.");
                    break;
                case 2:
                    if (carsQueue.isEmpty()) {
                        System.out.println("Brak pojazdów do przeglądu.");
                    } else {
                        Car nextCar = carsQueue.poll();
                        System.out.println("Typ pojazdu: " + nextCar);
                    }
                    break;
                default:
                    System.out.println("Błędna opcja");
            }
        }
    }
}