// Alvin Tay Ming Hwee
// A0218390E

import java.util.Scanner;

public class T9Spelling {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] map = {"2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999", "0"};
    int n = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      StringBuilder str = new StringBuilder();
      String cur = sc.nextLine();
      for (int j = 0; j < cur.length(); j++) {
        char ele = cur.charAt(j);
        String mappedEle = "";
        if (ele == ' ') {
          mappedEle = map[26];
        } else {
          mappedEle = map[((int)ele) - 97];
        }
        if (str.length() == 0) {
        } else if (str.charAt(str.length() - 1) == mappedEle.charAt(0)) {
            str.append(" ");
        }
        str.append(mappedEle);
      }
      System.out.printf("Case #%d: %s\n", i+1, str);
    }
    sc.close();
  }

  
}