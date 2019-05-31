package com.company;

public class WatchingWait extends State {
    WatchingWait(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void cancelWatching() {
        this.parent.setState("watching","active");
        this.parent.getActiveState("watching").cancelWatching();
    }

    @Override
    public void printCurrentState() {
        System.out.println("Watching: Waiting");
    }
}
