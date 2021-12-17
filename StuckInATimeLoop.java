import java.util.Scanner;

public class StuckInATimeLoop {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()) {
      int num = sc.nextInt();
      for (int i = 1; i < num + 1; i++) {
        System.out.println(i + " Abracadabra");
      }
    }  
  }
}