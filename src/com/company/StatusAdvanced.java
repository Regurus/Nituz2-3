package com.company;

    public class StatusAdvanced extends State{
        StatusAdvanced(MainState parent) {
            this.parent = parent;
        }

        @Override
        public void printCurrentState() {
            System.out.println("Current statusPoints: Advanced");
        }

        @Override
        public void refreshStatus() {
            if(Main.statusPoints >7){
                parent.setState("status","professional");
            }
            else if(Main.statusPoints <=4){
                parent.setState("status","starter");
            }
        }

        @Override
        public double getSpeed() {
            return 1.2;
        }
    }