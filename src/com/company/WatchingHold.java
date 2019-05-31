package com.company;

public class WatchingHold extends State {
    WatchingHold(MainState parent) {
        this.parent = parent;
    }
    private boolean canceled = false;
    @Override
    public void activateState() {
        Thread t = new Thread(() -> {
            try {
                while (true){
                    Thread.sleep(100);
                    if(!Main.paused){
                        parent.setState("watching","active");
                    }
                    if(this.canceled){
                        this.canceled=false;
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }});
        t.start();
    }

    @Override
    public void cancelWatching() {
        this.parent.setState("watching","active");
        this.parent.getActiveState("watching").cancelWatching();
    }

    @Override
    public void restartWatching() {
        this.parent.setState("watching","active");
        this.parent.getActiveState("watching").restartWatching();
    }

    @Override
    public void printCurrentState() {
        System.out.println("Watching: On Hold");
    }
}
