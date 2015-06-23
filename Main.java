import java.io.FileNotFoundException;

package com.company;


public class Main {

    public static void main(String[] args) {

        Container cont1 = new Container();

        try {
            cont1.wczytajDane(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        cont1.sumTimes();
        cont1.sortByPrior();

        Container permutacja = new Container();

        permutacja.getTaskList().add(cont1.copyTask(cont1, 0));

        for(int i = 1; i < cont1.getTaskList().size(); i++) {
            permutacja = new Container(permutacja.algorytm(permutacja, cont1.copyTask(cont1, i)));
        }
    }

}
