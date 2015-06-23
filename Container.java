import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

package com.company;

import static sun.swing.MenuItemLayoutHelper.max;

public class Container {

	public Container algorytm(Container permutacja, Task task1) {
    	Container optimalPermutation = new Container();
        int[][] timeArray = new int[permutacja.getTaskList().size() + 1][permutacja.getTask(0).getSizeArray()];
        int size = permutacja.getTaskList().size();

        for(int k = 0; k <= size; k++) {
        	Container pomocnicza = new Container(permutacja.getTaskList());
            pomocnicza.getTaskList().add(k, task1);
            timeArray[0][0] = pomocnicza.getTask(0).getTime(0);

            for(int i = 1; i < permutacja.getTask(0).getSizeArray(); i++) {
                System.out.println(timeArray.length);
                timeArray[0][i] = timeArray[0][i - 1] + pomocnicza.getTask(0).getTime(i);
            }

            for (int j = 1; j < pomocnicza.getTaskList().size(); j++) {
                timeArray[j][0] = timeArray[j - 1][0] + pomocnicza.getTask(j).getTime(0);

                for (int i = 1; i < permutacja.getTask(0).getSizeArray(); i++) {
                    timeArray[j][i] = max(pomocnicza.getTask(j - 1).getTime(i), timeArray[j][i - 1]) + pomocnicza.getTask(j).getTime(i);
                }
            }

            pomocnicza.cMax = timeArray[taskList.size() - 1][permutacja.getTask(0).getSizeArray() - 1];

            if(pomocnicza.cMax < optimalPermutation.cMax) {
            	optimalPermutation = new Container(pomocnicza);
            }
        }
        permutacja = new Container(optimalPermutation);
        permutacja.showPermutations();
        return permutacja;
    }


    private ArrayList<Task> taskList;
    private int cMax;

    public Container() {
        taskList = new ArrayList<Task>();
        cMax = Integer.MAX_VALUE;
    }

    public Container(ArrayList<Task> cont2){
        taskList = new ArrayList<Task>(cont2);
    }

    public Container(Container copied) {
        taskList = new ArrayList<Task>(copied.taskList);
        cMax = copied.cMax;
    }

    public void powieksz(int howManyMch) {
        Task task1 = new Task(howManyMch);
        taskList.add(task1);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task copyTask(Container container, int index) {
        Task zad = new Task(container.getTask(index));
        return zad;
    }

    public int getCmax() {
        return cMax;
    }



    public void wczytajDane(String nazwaPliku) throws FileNotFoundException {
        String line;
        int qZadan, qParam;

        File plik = new File(nazwaPliku);
        Scanner odczyt = new Scanner(plik);

        line=odczyt.nextLine();            // Pobranie danych o pliku z 1 linii - ilosc wierszy (zadan) i kolumn (parametrow-maszyn)
        String[] liczby = line.split("\\s+"); // Podzial pobranej linii na slowa
        qZadan=Integer.parseInt(liczby[0]);
        qParam=Integer.parseInt(liczby[1]);



        for(int i = 0; i < qZadan; i++) {      // Inicjalizacja pól tablicy
            powieksz(qParam);
        }

        for(int i = 0; i < qZadan; i++) {      // Tworzenie wymaganej ilosci obiektow
            String[] dane = new String[qZadan];
            dane[i] = odczyt.nextLine();
            String [] parametry = dane[i].split("\\s+");
            for(int j=0; j<qParam; j++) {
                taskList.get(i).fillTimesMch(parametry,qParam);
            }
            taskList.get(i).setNumber(i + 1);
        }
    }

    public void sumTimes() {
        for(int i = 0; i < taskList.size(); i++) {
            int sum = 0;
            for (int j = 0; j < taskList.get(0).getSizeArray(); j++) {
                sum = sum + taskList.get(i).getTimesMch()[j];
            }
            taskList.get(i).setPrior(sum);
        }
    }

    public void sortByPrior(){
        Collections.sort(taskList,new Compare());
    }

    public void showPermutations() {
        System.out.print("Zadania po kolei: ");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.print(taskList.get(i).getNumber() + " ");
        }
        System.out.println();
    }




}
