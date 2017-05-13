package multy3;

import java.util.concurrent.Semaphore;

/**
 * Created by Admin on 06.05.2017.
 */
public class Main {

  private static Semaphore sem = new Semaphore(2);


  public static void main(String[] args) {
    for (int i = 1; i < 6; i++) {
      (new Philosopher(i)).start();
    }
  }

  public static class Philosopher extends Thread {

    private int id;

    Philosopher(int id) {
      this.id = id;
    }

    public void run() {
      try {
        while (true) {
          sem.acquire();
          System.out.println("Философ " + id + " сел за стол");
          sleep((int) (Math.random() * 10000));
          System.out.println("Философ " + id + " покушал");
          sem.release();
          sleep((int) (Math.random() * 10000));

        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }


    }


  }


}
