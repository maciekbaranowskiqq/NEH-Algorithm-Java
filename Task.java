package com.company;

public class Task {

    private int number;
    private int prior;
    private int[] timesMch;
    private int sizeArray;

    public Task(int numOfMch)
    {
    	timesMch = new int [numOfMch];
    	number = 0;
    }

    public Task (Task tmp) {
        timesMch = new int[tmp.timesMch.length];
        for(int i = 0; i < timesMch.length; i++) {
            timesMch[i] = tmp.timesMch[i];
        }
        number = tmp.number;
        sizeArray = tmp.sizeArray;
        prior = tmp.prior;
    }

    public int getTime(int index) {
        return timesMch[index];
    }

    public void fillTimesMch(String[] parametry, int howManyMch){
        for (int i = 0; i < howManyMch; i++){
            timesMch[i] = Integer.parseInt(parametry[i]);
        }
        this.sizeArray = howManyMch;
    }

    public void copyTask(Task task1) {
        this.prior = task1.prior;
        this.sizeArray = task1.sizeArray;
        this.number = task1.number;

        for(int i = 0; i < task1.sizeArray; i++) {
            this.timesMch[i] = task1.timesMch[i];
        }
    }

    public int[] getTimesMch(){
        return timesMch;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int numberVal) {
        number = numberVal;
    }

    public void setPrior(int priorVal) {
        this.prior = priorVal;
    }

    public int getPrior() {
        return prior;
    }

    public int getSizeArray() {
        return sizeArray;
    }



}

