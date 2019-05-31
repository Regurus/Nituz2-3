package com.company;

public class StatusStarter extends State{
    StatusStarter(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void printCurrentState() {
        System.out.println("Current statusPoints: Starter");
    }

    @Override
    public void refreshStatus() {
        if(Main.statusPoints >4){
            parent.setState("status","advanced");
        }
    }
    @Override
    public double getSpeed() {
        return 1;
    }
}
