package com.company;

public class DiskDriveWaiting extends State{
    public DiskDriveWaiting(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void fileRequest() {
        System.out.println("Drive waiting");
    }

    @Override
    public void printCurrentState() {
        System.out.println("Drive: Waiting");
    }

    @Override
    public void activateState() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.parent.setState("drive","idle");

        });
        t.start();
    }
    @Override
    public void cancelFile() {
        Main.currentMemory+=4;
    }
}
