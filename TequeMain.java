// Alvin Tay Ming Hwee
// A0218390E

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class TequeMain {
  public static void main(String[] args) {
    FastReader sc = new FastReader();

    try(PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
      int n = sc.nextInt();
      Teque teque = new Teque();
      while (n > 0) {
        String op = sc.next();
        int item = sc.nextInt();
        if (op.equals("push_back")) {
          teque.push_back(item);
        } else if (op.equals("push_front")) {
          teque.push_front(item);
        } else if (op.equals("get")) {
          out.println(teque.get(item));
        } else {
          teque.push_middle(item);
        }
        n--;
      }
    }
  }  
}