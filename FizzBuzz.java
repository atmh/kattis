// Alvin Tay Ming Hwee
// A0218390E

import java.util.Scanner;

public class FizzBuzz {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    fizzAndBuzz(sc);    
    sc.close();
  }

  public static void fizzAndBuzz (Scanner sc) {
    int x = sc.nextInt();
    int y = sc.nextInt();
    int n = sc.nextInt();
    for (int i = 1; i <= n; i++) {
      if (i % x == 0 && i % y == 0) {
        System.out.println("FizzBuzz");
      } else if (i % x == 0) {
        System.out.println("Fizz");
      } else if (i % y == 0) {
        System.out.println("Buzz");
      } else {
        System.out.println(i);
      }
    }
  }
}