package com.company;


import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static boolean exit = false;
    private static MainState current;
    private static MainState offState;
    private static MainState onState;
    public static boolean downloadError = false;
    public static int statusPoints = 0;
    public static int currentMemory = 100;
    public static double currentDownload = 0;

    private enum Command{EXIT,TURNON,TURNOFF,INTERNETON,INTERNETOFF,FILEREQUEST,DOWNLOADABORTED,DOWNLOADERROR,
        ERRORFIXED,MOVIEON,RESTARTMOVIE,HOLDMOVIE,MOVIEOFF,RESUME,STATUS};//always in upper case for parsing
    public static void main(String[] args) {
        offState = new MainStateOff();
        onState = new MainStateOn();
        current = offState;
        System.out.println("enter " + current.getClass().getName().split(Pattern.quote("."))[2]+" state");
        while (!exit){
            String command;
            Scanner sc = new Scanner(System. in);
            System. out. println("Enter a command");
            command = sc. nextLine();
            processCommand(command);
        }
    }

    private static void processCommand(String input){
        Command command;
        try{
            command = Command.valueOf(input.toUpperCase());
        }
        catch (Exception e){
            System.out.println("Command does not exist.");
            return;
        }
        switch (command){
            case EXIT:
                exit = true;
                break;
            case STATUS:
                current.doTheThing();
                break;
            case TURNON:
                if(current!=onState){
                    System.out.println("exit " + offState.getClass().getName().split(Pattern.quote("."))[2]+" state");
                    current = onState;
                    System.out.println("enter " + current.getClass().getName().split(Pattern.quote("."))[2]+" state");

                }
                break;
            case TURNOFF:
                if(current!=offState){
                    System.out.println("exit " + onState.getClass().getName().split(Pattern.quote("."))[2]+" state");
                    current = offState;
                    System.out.println("enter " + current.getClass().getName().split(Pattern.quote("."))[2]+" state");
                }
                break;
            case INTERNETOFF:
                current.getActiveState("internet").turnInternetOff();
                break;
            case INTERNETON:
                current.getActiveState("internet").turnInternetOn();
                break;
            case RESUME:
                break;
            case MOVIEON:
                break;
            case MOVIEOFF:
                break;
            case HOLDMOVIE:
                break;
            case ERRORFIXED:
                downloadError = true;
                break;
            case FILEREQUEST:
                current.getActiveState("download").fileRequest();
                break;
            case RESTARTMOVIE:
                break;
            case DOWNLOADERROR:
                downloadError = true;
                break;
            case DOWNLOADABORTED:
                current.getActiveState("download").abortDownload();
                break;
        }
    }
}
