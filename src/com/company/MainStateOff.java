package com.company;

public class MainStateOff implements MainState {

    @Override
    public void setState(String type, String key) {
        System.out.println("System is off");
    }

    @Override
    public State getActiveState(String subsys) {
        System.out.println("System is off");
        return new ErrorState();
    }

    @Override
    public void doTheThing() {
        System.out.println("--------------------------");
        System.out.println("System is OFF");
        System.out.println("--------------------------");
    }
}
