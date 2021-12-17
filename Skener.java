// Alvin Tay Ming Hwee
// A0218390E

import java.util.Scanner;

public class Skener {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    scannerMatrix(sc);    
    sc.close();
  }

  public static void scannerMatrix (Scanner sc) {
    int arrSize = sc.nextInt();
    int strLen = sc.nextInt();
    int row = sc.nextInt();
    int col = sc.nextInt();
    sc.nextLine();
    String[] arr = new String[arrSize];
    for (int i = 0; i < arrSize; i++) {
      String cur = sc.nextLine();
      String temp = "";
      for (int j = 0; j < strLen; j++) {
        for (int k = 0; k < col; k++) {
          temp += cur.charAt(j);
        }
      }
      arr[i] = temp;
    }
    for (int i = 0; i < arrSize; i++) {
      for (int j = 0; j < row; j++) {
        System.out.println(arr[i]);
      }
    }
  }
}