package multy2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 06.05.2017.
 */
public class Main {
  private static int n = 3;
  private static int m = 4;
  private static int k = 5;
  private static int[][] matrix1 = new int[n][m];
  private static int[][] matrix2 = new int[m][k];
  private static int[][] result = new int[n][k];

  private static void pointer() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix1[i][j] = (int) (Math.random() * 100);
        System.out.print(matrix1[i][j] + " ");
      }
      System.out.println();
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < k; j++) {
        matrix2[i][j] = (int) (Math.random() * 100);
        System.out.print(matrix2[i][j] + " ");
      }
      System.out.println();
    }
  }


  public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);
    int potoki = sc.nextInt();
    pointer();
    List<MyThread> list = new ArrayList<>();
    int temp = (n * k) / potoki;
    int firstInd = 0;

    for (int i = 0; i < potoki; i++) {
      int lastInd = temp + firstInd;
      if (i == potoki - 1) {
        lastInd = n * k;
      }
      MyThread thread = new MyThread(firstInd, lastInd);
      thread.start();
      list.add(thread);
      firstInd = lastInd;
    }
    for (MyThread t : list) {
      t.join();
    }
    resultPointer();


  }

  public static class MyThread extends Thread {
    int firstInd;
    int lastInd;

    MyThread(int firstInd, int lastInd) {
      this.firstInd = firstInd;
      this.lastInd = lastInd;
    }

    public void run() {
      System.out.println("Thread " + getName() + " started. Calculating cells from " + firstInd + " to " + lastInd + "...");
      for (int index = firstInd; index < lastInd; ++index)
        calculating(index / k, index % k);
      System.out.println("Thread " + getName() + " finished.");
    }

    private void calculating(int row, int column) {
      for (int i = 0; i < m; i++) {
        result[row][column] = result[row][column] + matrix1[row][i] * matrix2[i][column];
      }
    }

  }

  public static void resultPointer() {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < k; j++) {
        System.out.print(result[i][j] + " ");
      }
      System.out.println();
    }
  }

}




