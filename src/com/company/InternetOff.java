package com.company;

public class InternetOff extends State {
    public InternetOff(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void printCurrentState() {
        System.out.println("Internet is OFF.");
    }

    @Override
    public void turnInternetOff() {
        System.out.println("Internet is already OFF.");
    }

    @Override
    public void turnInternetOn() {
        parent.setState("internet","ON");
    }

}
