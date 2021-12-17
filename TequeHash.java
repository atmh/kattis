// Alvin Tay Ming Hwee
// A0218390E

import java.io.PrintWriter;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class TequeHash {
  public static void main(String[] args) {
    FastReader sc = new FastReader();

    try(PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
      int n = sc.nextInt();
      HashMap<Integer, Integer> front = new HashMap<>();
      HashMap<Integer, Integer> back = new HashMap<>();
      int frontHead = -1;
      int frontTail = 0;
      int backHead = -1;
      int backTail = 0;

      while (n > 0) {
        String op = sc.next();
        int item = sc.nextInt();
        if (op.equals("push_back")) {
          back.put(backTail++, item);          
        } else if (op.equals("push_front")) {
          front.put(frontHead--, item);       
        } else if (op.equals("get")) {
          if (item < front.size()) {
            out.println(front.get(item + frontHead + 1));
          } else {
            out.println(back.get(item - front.size() + backHead + 1));          
          }
        } else {
          front.put(frontTail++, item);
        }

        if (front.size() < back.size()) {
          front.put(frontTail++, back.get(++backHead));
          back.remove(backHead);
        } else if (front.size() - back.size() > 1) {
          back.put(backHead--, front.get(--frontTail));
          front.remove(frontTail);
        }
        n--;
      }
    }
  }  
}