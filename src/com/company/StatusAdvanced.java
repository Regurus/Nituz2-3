package com.company;

    public class StatusAdvanced extends State{
        public StatusAdvanced(MainState parent) {
            this.parent = parent;
        }

        @Override
        public void printCurrentState() {
            System.out.println("Current statusPoints: Advanced");
        }

        @Override
        public void refreshStatus() {
            if(Main.statusPoints >7){
                parent.setState("statusPoints","professional");
            }
            else if(Main.statusPoints <=4){
                parent.setState("statusPoints","starter");
            }
        }

        @Override
        public double getSpeed() {
            return 1.2;
        }
    }