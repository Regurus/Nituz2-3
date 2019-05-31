package com.company;

import java.util.LinkedList;

public abstract class State {
    protected MainState parent;
    static LinkedList q = new LinkedList();

    public void turnInternetOff(){
        System.out.println("Action is impossible");
    }
    public void turnInternetOn(){
        System.out.println("Action is impossible");
    }
    public void printCurrentState(){
        System.out.println("Action is impossible");
    }
    public void refreshStatus(){
        System.out.println("Action is impossible");
    }
    public double getSpeed(){
        System.out.println("Action is impossible");
        return 0;
    }
    public void fileRequest(){
        System.out.println("Action is impossible");
    }
    public void activateState(){
        //do nothing by default
    }
    public void acceptFile(){
        System.out.println("Action is impossible");
    }
    public void cancelFile(){
        System.out.println("Action is impossible");
    }
    public void abortDownload(){ System.out.println("Action is impossible"); }
    public void watchCurrent(){ System.out.println("Action is impossible"); }
    public void cancelWatching(){ System.out.println("Action is impossible"); }
    public void restartWatching(){System.out.println("Action is impossible");}
}
