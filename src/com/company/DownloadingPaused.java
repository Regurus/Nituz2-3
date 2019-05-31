package com.company;

public class DownloadingPaused extends State{
    DownloadingPaused(MainState parent) {
        this.parent = parent;
    }
    @Override
    public void acceptFile() {
        q.add(new Object());
    }
    @Override
    public void fileRequest() {
        parent.getActiveState("drive").fileRequest();
    }
    @Override
    public void activateState() {
        Thread t = new Thread(() -> {
            try {
                while (this.parent.getActiveState("internet")instanceof InternetOff){
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.parent.setState("download","downloading");
        });
        t.start();
    }

    @Override
    public void abortDownload() {
        Main.currentDownload=0;
        Main.statusPoints--;
        this.parent.getActiveState("drive").cancelFile();
        parent.setState("download","idle");
    }
    @Override
    public void printCurrentState() {
        System.out.println("Download:Paused");
    }
}
