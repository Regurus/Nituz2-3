package com.company;

public class DownloadingActive extends State{
    private boolean abort = false;
    public DownloadingActive(MainState parent) {
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
                    }
                    if(abort){
                        this.parent.getActiveState("drive").cancelFile();
                        Main.currentDownload=0;
                        abort=false;
                        break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.parent.setState("download","idle");

        });
        t.start();
    }

    @Override
    public void acceptFile() {
        super.acceptFile();
    }

    @Override
    public void abortDownload() {
        this.abort=true;
    }
}
