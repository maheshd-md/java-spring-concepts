package com.maheshd.designpatterns.proxy;

import java.util.Arrays;
import java.util.List;

public class ProxyVIPVehicleProvider implements VIPVehicleProvider {

    private VIPVehicleProvider actualVIPVehicleProvider = new ActualVIPVehicleProvider();

    @Override
    public String getVIPVehicle(Double amount) {
        if(amount >= 10) {
            return actualVIPVehicleProvider.getVIPVehicle(amount);
        } else {
            return "Not available!";
        }
    }
}
