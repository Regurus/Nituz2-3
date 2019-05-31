package com.company;

public class WatchingIdle extends State {
    WatchingIdle(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void watchCurrent() {
        if(Main.currentDownload<8){
            parent.setState("watching","active");
        }
        else
            System.out.println("Download less than 20%");
    }

    @Override
    public void cancelWatching() {
        System.out.println("No playback active");
    }

    @Override
    public void restartWatching() {
        System.out.println("Nothing to restart");
    }

    @Override
    public void printCurrentState() {
        System.out.println("Watching: Idle");
    }
}
