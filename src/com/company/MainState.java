package com.company;

public interface MainState {
    public void setState(String type, String key);

    public State getActiveState(String subsys);

    public void doTheThing();
}
