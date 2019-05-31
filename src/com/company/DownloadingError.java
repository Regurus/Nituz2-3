package com.company;

public class DownloadingError extends State{
    public DownloadingError(MainState parent) {
        this.parent = parent;
    }
    @Override
    public void acceptFile() {
        System.out.println("Download error!");
    }

    @Override
    public void activateState() {
        try {
            int count = 30;
            while (count>0){
                Thread.sleep(100);
                if(!Main.downloadError){
                    parent.setState("download","downloading");
                }
                count--;
            }
            Main.currentDownload=0;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void abortDownload() {
        Main.currentDownload=0;
        this.parent.getActiveState("drive").cancelFile();
        parent.setState("download","idle");
    }
}
