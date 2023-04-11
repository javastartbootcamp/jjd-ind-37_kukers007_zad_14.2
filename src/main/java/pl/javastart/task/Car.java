package pl.javastart.task;

import java.io.Serializable;

public class Car implements Serializable {
    private String type;
    private String producer;
    private String model;
    private int year;
    private int mileage;
    private String vin;

    public Car(String type, String producer, String model, int year, int mileage, String vin) {
        this.type = type;
        this.producer = producer;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.vin = vin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return type + " " + producer + " "
                + " " + model + " " + year
                + " " + mileage + " " + vin;
    }
}
