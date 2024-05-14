package com.maheshd.designpatterns.proxy;

public class Client {
    public static void main(String[] args) {
        VIPVehicleProvider vipVehicleProvider = new ProxyVIPVehicleProvider();

        System.out.println(vipVehicleProvider.getVIPVehicle(60.0));
    }
}
