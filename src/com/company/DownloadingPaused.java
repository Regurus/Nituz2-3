package com.company;

public class DownloadingPaused extends State{
    public DownloadingPaused(MainState parent) {
        this.parent = parent;
    }
    @Override
    public void acceptFile() {
        System.out.println("Current download paused!");
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
        this.parent.getActiveState("drive").cancelFile();
        parent.setState("download","idle");
    }
}
