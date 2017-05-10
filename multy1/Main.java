package multy1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 03.05.2017.
 */
public class Main {
  public static int number = 15877;
  public static int i = 2;
  public static boolean flag = true;
  public static Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);
    int potoki = sc.nextInt();
    List<Checker> threads = new ArrayList<>();
    while (potoki != 0) {
      Checker ch = new Checker();
      threads.add(ch);
      ch.start();
      potoki--;
    }
    for (Checker ch : threads) {
      ch.join();
    }
    System.out.println(flag);
  }

  static class Checker extends Thread {
    public void run() {

        while (i * i <= number && flag) {
          System.out.println(Thread.currentThread().getName() + " is checking number " + i);
          if (number % i == 0 ) {
            flag = false;
          }
          synchronized (lock) {
          i++;
        }
      }


    }

  }
}

