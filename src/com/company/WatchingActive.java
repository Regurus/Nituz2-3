package com.company;

public class WatchingActive extends State {
    WatchingActive(MainState parent) {
        this.parent = parent;
    }
    private double time = 60;
    private boolean go;
    @Override
    public void activateState() {
        Thread t = new Thread(() -> {
            try {
                System.out.println("Movie playing");
                if(time==0)
                    this.time = 60;
                while (time>0){
                    Thread.sleep(100);
                    if(Main.downloadError||parent.getActiveState("internet") instanceof InternetOff){
                        parent.setState("watching","wait");
                        return;
                    }
                    if(Main.paused&&!(parent.getActiveState("watching")instanceof WatchingWait)){
                        parent.setState("watching","hold");
                        return;
                    }
                }
                System.out.println("Movie done playing");
                parent.setState("watching","idle");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    @Override
    public void restartWatching() {
        this.time=60;
        System.out.println("Playback restarted");
    }

    @Override
    public void cancelWatching() {
        this.time=0;
        this.parent.setState("watching","idle");
    }

    @Override
    public void printCurrentState() {
        System.out.println("Watching: Active");
    }
}
