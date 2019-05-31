package com.company;

public class InternetOn extends State {

    public InternetOn(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void printCurrentState() {
        System.out.println("Internet is ON.");
    }

    @Override
    public void turnInternetOff() {
        parent.setState("internet","OFF");
    }

    @Override
    public void turnInternetOn() {
        System.out.println("Internet is already ON.");
    }
}
