import java.util.Scanner;

public class Autori {

  public static String autori(String str) {
    String[] temp = str.split("-");
    String result = "";
    for(int i = 0; i < temp.length; i++) {
      result += temp[i].charAt(0);
    }
    return result;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      String str = sc.nextLine();
      System.out.println(autori(str));
    }
    sc.close();
  }
}