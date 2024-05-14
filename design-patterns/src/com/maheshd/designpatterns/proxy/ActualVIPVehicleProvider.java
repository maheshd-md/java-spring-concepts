package com.maheshd.designpatterns.proxy;

public class ActualVIPVehicleProvider implements VIPVehicleProvider {

    @Override
    public String getVIPVehicle(Double amount) {
        System.out.println("Amount: " +amount);
        if(amount < 20.0)
            return "BMW";
        else if (amount < 30)
            return  "Mercedes";
        else if (amount < 50)
            return "Volvo";
        return "Rolls-Royce";
    }
}
