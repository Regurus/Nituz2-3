package com.company;

public class DownloadingIdle extends State{
    DownloadingIdle(MainState parent) {
        this.parent = parent;
    }

    @Override
    public void fileRequest() {
        parent.getActiveState("drive").fileRequest();
    }
    @Override
    public void acceptFile() {
        q.add(new Object());
        if(!parent.getBlocked("download")){
            this.processRequest();
        }
    }
    private void processRequest(){
        q.poll();
        parent.getActiveState("status").refreshStatus();
        if(this.parent.getActiveState("internet") instanceof InternetOn){
            this.parent.setState("download","downloading");
            this.parent.setBlocked("download",true);
        }
        else
            System.out.println("No internet connection!");
    }

    @Override
    public void abortDownload() {
        System.out.println("Nothing to abort!");
    }

    @Override
    public void activateState() {
        if(q.peek()!=null){
            this.processRequest();
        }

    }

    @Override
    public void printCurrentState() {
        System.out.println("Download:Idle");
    }
}
