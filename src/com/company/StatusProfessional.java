package com.company;

public class StatusProfessional extends State{
    public StatusProfessional(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void printCurrentState() {
        System.out.println("Current statusPoints: Professional");
    }

    @Override
    public void refreshStatus() {
        if(Main.statusPoints <=7){
            parent.setState("statusPoints","advanced");
        }
    }

    @Override
    public double getSpeed() {
        return 1.5;
    }
}