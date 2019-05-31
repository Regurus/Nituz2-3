package com.company;

public class StatusStarter extends State{
    public StatusStarter(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void printCurrentState() {
        System.out.println("Current statusPoints: Starter");
    }

    @Override
    public void refreshStatus() {
        if(Main.statusPoints >4){
            parent.setState("state","advanced");
        }
    }
    @Override
    public double getSpeed() {
        return 1;
    }
}
