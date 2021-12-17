// Alvin Tay Ming Hwee
// A0218390E

import java.util.ArrayList;
import java.util.List;

public class sortOfSorting {

  public static void main(String[] args) {
    FastReader sc = new FastReader();
    while(true) {
      int n = sc.nextInt();
      if (n == 0) break;
      List<String> arr = new ArrayList<String>(n);
      for (int i = 0; i < n; i++) {
        arr.add(sc.nextLine());
      }
      arr.sort((a, b) -> (a.substring(0, 2).compareTo(b.substring(0, 2))));
      
      arr.forEach(x -> System.out.println(x));
      System.out.println("");
    }
  }  
}