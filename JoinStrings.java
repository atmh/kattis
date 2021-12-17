// Alvin Tay Ming Hwee
// A0218390E

import java.util.ArrayList;

public class JoinStrings {
  public static void main(String[] args) {
    FastReader sc = new FastReader();
    int n = sc.nextInt();
    ArrayList<TailedLinkedList> arr = new ArrayList<TailedLinkedList>(n);
    for (int i = 0; i < n; i++) {
      TailedLinkedList list = new TailedLinkedList();
      list.addFront(new ListNode(sc.nextLine()));
      arr.add(list);
    }
    TailedLinkedList last = arr.get(0);
    for (int i = 0; i < n-1; i++) {
      int front = sc.nextInt() - 1;
      int back = sc.nextInt() - 1;
      TailedLinkedList cur = arr.get(front);
      TailedLinkedList next = arr.get(back);
      cur.addBack(next.getHead());
      cur.tail = next.getTail();
      cur.addNum(next);
      last = cur;
    }
    last.print();
  }  
}