package edu.txstate.cas388.rentalcarapp;

import org.json.JSONObject;

public class Car {

    private int carID;
    private String carName;
    private String carBrand;
    private double costPerDay;
    private int carImage;
    private String carColor;

    public Car() {
    }

    public Car(JSONObject object) {

        try {
            this.carID = object.getInt("Id");
            this.carName = object.getString("Name");
            this.carBrand = object.getString("Brand");
            this.costPerDay = object.getDouble("Cost");
            this.carColor = object.getString("Color");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Car(int carID, String carName, String carBrand, double costPerDay, int carImage) {
        this.carID = carID;
        this.carName = carName;
        this.carBrand = carBrand;
        this.costPerDay = costPerDay;
        this.carImage = carImage;
    }

    public Car(int carID, String carName, String carBrand, double costPerDay, int carImage, String carUrl) {
        this.carID = carID;
        this.carName = carName;
        this.carBrand = carBrand;
        this.costPerDay = costPerDay;
        this.carImage = carImage;
        this.carColor = carUrl;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public int getCarImage() {
        return carImage;
    }

    public void setCarImage(int carImage) {
        this.carImage = carImage;
    }

    @Override
    public String toString() {
        return this.carName + ", " + this.carID;
    }
}
