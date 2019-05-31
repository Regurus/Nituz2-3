package com.company;

public class DownloadingError extends State{
    DownloadingError(MainState parent) {
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
            parent.setBlocked("download",true);
            int count = 30;
            while (count>0){
                Thread.sleep(100);
                if(!Main.downloadError){
                    parent.setState("download","downloading");
                }
                count--;
            }
            parent.setBlocked("download",false);
            Main.currentDownload=0;
            Main.statusPoints--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }});
        t.start();
    }

    @Override
    public void restartWatching() {
        System.out.println("Error occurred, restart possible after fix");
    }

    @Override
    public void abortDownload() {
        Main.statusPoints--;
        Main.currentDownload=0;
        this.parent.getActiveState("drive").cancelFile();
        parent.setState("download","idle");
    }
    @Override
    public void printCurrentState() {
        System.out.println("Download:Error");
    }
}
