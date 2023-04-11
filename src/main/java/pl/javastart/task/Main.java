package pl.javastart.task;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private static Queue<Car> carsQueue = new LinkedList<>();
    private static final String FILE_NAME = "cars.txt";
    private static final int EXIT = 0;
    private static final int ADD_NEW_CAR = 1;
    private static final int PRINT_QUEUE = 2;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        readQueueFromFile(FILE_NAME);

        int option = -1;
        while (option != 0) {
            option = getOption(scanner);
            switch (option) {
                case EXIT ->
                    writeToFile(scanner);
                case ADD_NEW_CAR -> addNewCarToQueue(scanner);
                case PRINT_QUEUE -> printCarFromQueue();
                default -> System.out.println("Incorrect value.");
            }
        }
    }

    private static void printCarFromQueue() {
        if (carsQueue.isEmpty()) {
            System.out.println("No more cars for inspection.");
        } else {
            Car nextCar = carsQueue.poll();
            System.out.println("Car type: " + nextCar);
        }
    }

    private static void addNewCarToQueue(Scanner scanner) {
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
    }

    private static void writeToFile(Scanner scanner) {
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

    private static int getOption(Scanner scanner) {
        int option;
        System.out.println("Menu:");
        System.out.println("0 - EXIT");
        System.out.println("1 - Add new car to queue");
        System.out.println("2 - Print info about next car in queue.");
        option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private static void readQueueFromFile(String fileName) {
        try (
                var fis = new FileInputStream(fileName);
                var ois = new ObjectInputStream(fis);
        ) {
            carsQueue = (Queue<Car>) ois.readObject();
            System.out.println("Read from file");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Read from file failed");
        }
    }
}