package com.example;

public class Car {
    private String lisenseNum;
    private String color;
    private String model;
    private String size;
    public String location;

    public Car(String lisenseNum, String color, String model, String size){
        this.lisenseNum = lisenseNum;
        this.color = color;
        this.model = model;
        this.size = size;
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

}
