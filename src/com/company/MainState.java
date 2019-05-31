package com.company;

public interface MainState {
    void setState(String type, String key);
    State getActiveState(String subsys);
    void doTheThing();
    boolean getBlocked(String subsys);
    void setBlocked(String subsys,boolean val);
    void enter();
    void exit();
}
