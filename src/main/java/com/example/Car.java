package com.example;

public class Car {
    private String lisenseNum;
    private String color;
    private String model;
    private String size;
    public String location;
    private boolean isHandicap; 

    public Car(String lisenseNum, String color, String model, String size, boolean isHandicap){
        this.lisenseNum = lisenseNum;
        this.color = color;
        this.model = model;
        this.size = size;
        this.isHandicap = isHandicap;
    }

    public String getLisenseNum(){
        return lisenseNum;
    }

    public String getColor(){
        return color;
    }

    public String getModel(){
        return model;
    }

     public String getSize(){
        return size;
    }    

    public String getLocation(){
        return location;
    }

    public boolean isHandicap() {
        return isHandicap;
    }

    public void setHandicap(boolean isHandicap) {
        this.isHandicap = isHandicap;
    }

}
