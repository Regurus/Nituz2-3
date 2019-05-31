package com.company;

public class DownloadingIdle extends State{
    public DownloadingIdle(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void fileRequest() {
        parent.getActiveState("drive").fileRequest();
    }
    @Override
    public void acceptFile() {
        if(this.parent.getActiveState("internet") instanceof InternetOn)
            this.parent.setState("download","downloading");
        else
            System.out.println("No internet connection!");
    }

    @Override
    public void abortDownload() {
        System.out.println("Nothing to abort!");
    }
}
