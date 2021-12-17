// Alvin Tay Ming Hwee
// A0218390E

import java.util.Scanner;

public class Apaxians {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println(RemoveDup(sc));    
    sc.close();
  }

  public static String RemoveDup (Scanner sc) {
    String name = sc.nextLine();
    String shorten = "";
    char prev = '.';
    for (int i = 0; i < name.length(); i++) {
      char cur = name.charAt(i);
      if (cur != prev) {
        shorten += cur;
        prev = cur;
      }
    }
    return shorten;
  }
}