package com.company;

public class DiskDriveIdle extends State {
    public DiskDriveIdle(MainState parent) {
        this.parent = parent;
    }
    @Override
    public void fileRequest() {
        if(Main.currentMemory>10){
            parent.getActiveState("download").acceptFile();
            System.out.println("New download accepted");
            Main.currentMemory-=10;
            return;
        }
        parent.setState("drive","working");
    }
    @Override
    public void printCurrentState() {
        System.out.println("Drive: Idle");
    }
    @Override
    public void cancelFile() {
        Main.currentMemory+=10;
    }
}
