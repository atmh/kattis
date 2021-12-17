// Alvin Tay Ming Hwee
// A0218390E

import java.util.Scanner;

public class peaPancake {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println(peaAndPancakes(sc));    
    sc.close();
  }

  public static String peaAndPancakes(Scanner sc) {
    int numOfRes = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < numOfRes; i++) {
      int numOfMenu = sc.nextInt();
      sc.nextLine();
      String name = sc.nextLine();
      boolean pea_soup = false;
      boolean pancakes = false;
      for (int j = 0; j < numOfMenu; j++) {
        String item = sc.nextLine();
        if (item.equals("pea soup")) {
          pea_soup = true;
        }
        if (item.equals("pancakes")) {
          pancakes = true;
        }
        if (pea_soup && pancakes) {
          return name;
        }
      }
    }
    return "Anywhere is fine I guess";
  }
}