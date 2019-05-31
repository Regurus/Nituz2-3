package com.company;

public class DownloadingActive extends State{
    private boolean abort = false;
    DownloadingActive(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void activateState() {

        Thread t = new Thread(() -> {
            try {
                if(Main.currentDownload<=0)
                    Main.currentDownload = 10;
                while (Main.currentDownload>0){
                    Thread.sleep(100);
                    Main.currentDownload-=parent.getActiveState("status").getSpeed()*0.1;
                    if(parent.getActiveState("internet") instanceof InternetOff){
                        parent.setState("download","pause");
                        return;
                    }
                    if(Main.downloadError){
                        parent.setState("download","error");
                        return;
                    }
                    if(abort){
                        this.parent.getActiveState("drive").cancelFile();
                        Main.currentDownload=0;
                        abort=false;
                        this.parent.setState("download","idle");
                        return;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.statusPoints++;
            this.parent.setBlocked("download",false);
            this.parent.setState("download","idle");

        });
        t.start();
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
    public void abortDownload() {
        Main.statusPoints--;
        this.abort=true;
    }
    @Override
    public void printCurrentState() {
        System.out.println("Download:Active");
    }
}
