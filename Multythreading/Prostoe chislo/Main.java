package multy1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 03.05.2017.
 */
public class Main {
  public static int number = 109297;
  public static int i = 2;
  public static boolean flag = true;


  public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);
    int potoki = sc.nextInt();
    List<Checker> threads = new ArrayList<>();
    int kolvoDelitelei = (int) Math.sqrt(number);
    int temp = kolvoDelitelei / potoki;
    int firstInd = 2;

    for (int i = 0; i < potoki; i++) {
      int lastInd = firstInd + temp;
      if(i== potoki-1){
        lastInd = (int)Math.sqrt(number);
      }
      Checker ch = new Checker(firstInd, lastInd);
      threads.add(ch);
      ch.start();
      firstInd = lastInd;
    }
    for (Checker ch : threads) {
      ch.join();
    }
    System.out.println(flag);
  }

  static class Checker extends Thread {
    private int firstInd;
    private int lastInd;

    Checker(int firstInd, int lastInd) {
      this.firstInd = firstInd;
      this.lastInd = lastInd;
    }

    public void run() {

     for(int i = firstInd; i <= lastInd && flag; i++) {
        System.out.println(Thread.currentThread().getName() + " is checking number " + i);
        if (number % i == 0) {
          flag = false;
        }
      }


    }

  }
}

