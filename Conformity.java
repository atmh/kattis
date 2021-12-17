// Alvin Tay Ming Hwee
// A0218390E

import java.util.HashMap;
import java.util.HashSet;

public class Conformity {

  public static void main(String[] args) {
    FastReader sc = new FastReader();
    int student = sc.nextInt();
    HashMap<HashSet<Integer>, Integer> map = new HashMap<>();
    for (int i = 0; i < student; i++) {
      HashSet<Integer> temp = new HashSet<>();
      for (int j = 0; j < 5; j++) {
        temp.add(sc.nextInt());
      }
      map.compute(temp, (key, val) -> val == null ? 1 : val + 1);
    }

    int max = 0;
    int occurance = 0;

    for (Integer value : map.values()) {
      if (value > max) {
        max = value;
        occurance = 1;
      } else if (value == max){
        occurance++;
      }
    }
    System.out.println(max * occurance);
  }  
}