import java.util.Scanner;

public class TakeTwoStones {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextInt()) {
      int num = sc.nextInt();
      if (num % 2 != 0) {
        System.out.println("Alice");
      } else {
        System.out.println("Bob");
      }
    }
  }

}