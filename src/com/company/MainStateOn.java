package com.company;

import java.util.HashMap;
import java.util.regex.Pattern;

public class MainStateOn implements MainState {
    private HashMap<String,HashMap> states;
    private HashMap<String,State> active;
    private HashMap<String,Boolean> blocked;

    @Override
    public void enter() {
        this.initInternetSubsys();
        this.initStatusSubsys();
        this.initDriveSubsys();
        this.initWatchingSubsys();
        this.initDownloadSubsys();
    }

    @Override
    public void exit() {
        for(State s: this.active.values()){
            System.out.println("exit " + s.getClass().getName().split(Pattern.quote("."))[2]+" state");
        }
        this.active = new HashMap<>();
    }

    MainStateOn() {
        this.states = new HashMap<>();
        this.active = new HashMap<>();
        this.blocked = new HashMap<>();
        //internet init

    }
    @Override
    public void setState(String type, String key) {
        HashMap subsys = this.states.get(type);
        State newState =(State)subsys.get(key);
        if(newState!=this.active.get(type)){
            if(this.active.get(type)!=null){
                System.out.println("exit " + this.active.get(type).getClass().getName().split(Pattern.quote("."))[2]+" state");
                this.active.replace(type,newState);
            }
            else
                this.active.put(type,newState);
            if(this.active.get(type)!=null)
            System.out.println("enter " + newState.getClass().getName().split(Pattern.quote("."))[2]+" state");
            newState.activateState();
        }
    }
    public boolean getBlocked(String subsys){
        return this.blocked.get(subsys);
    }
    public void setBlocked(String subsys,boolean val){
        this.blocked.replace(subsys,val);
    }
    private void initInternetSubsys(){
        HashMap internet = new HashMap<String,State>();
        this.states.put("internet",internet);
        State intOn = new InternetOn(this);
        internet.put("ON",intOn);
        State intOff = new InternetOff(this);
        internet.put("OFF",intOff);
        this.setState("internet","OFF");
        this.blocked.put("internet",false);
    }
    private void initStatusSubsys(){
        HashMap status = new HashMap<String,State>();
        this.states.put("status",status);
        State str = new StatusStarter(this);
        status.put("starter",str);
        State adv = new StatusAdvanced(this);
        status.put("advanced",adv);
        State pro = new StatusProfessional(this);
        status.put("professional",pro);
        this.setState("status","starter");
        this.blocked.put("status",false);
    }
    private void initDriveSubsys(){
        HashMap drive = new HashMap<String,State>();
        this.states.put("drive",drive);
        State idle = new DiskDriveIdle(this);
        drive.put("idle",idle);
        State working = new DiskDriveWaiting(this);
        drive.put("waiting",working);
        this.setState("drive","idle");
        this.blocked.put("drive",false);
    }
    private void initWatchingSubsys(){
        HashMap watching = new HashMap<String,State>();
        this.states.put("watching",watching);
        State idle = new WatchingIdle(this);
        watching.put("idle",idle);
        State active = new WatchingActive(this);
        watching.put("active",active);
        State hold = new WatchingHold(this);
        watching.put("hold",hold);
        State wait = new WatchingWait(this);
        watching.put("wait",wait);
        this.setState("watching","idle");
        this.blocked.put("watching",false);

    }
    private void initDownloadSubsys(){
        HashMap download = new HashMap<String,State>();
        this.states.put("download",download);
        State idle = new DownloadingIdle(this);
        download.put("idle",idle);
        State downloading = new DownloadingActive(this);
        download.put("downloading",downloading);
        State pause = new DownloadingPaused(this);
        download.put("pause",pause);
        State error = new DownloadingError(this);
        download.put("error",error);
        this.setState("download","idle");
        this.blocked.put("download",false);
    }
    @Override
    public State getActiveState(String subsys) {
        return this.active.get(subsys);
    }

    @Override
    public void doTheThing() {
        System.out.println("--------------------------");
        System.out.println("System is ON");
        for(State s: this.active.values()){
            s.printCurrentState();
        }
        System.out.println("--------------------------");
    }
}
