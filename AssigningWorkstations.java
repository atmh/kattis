import java.util.Arrays;
import java.util.PriorityQueue;

// Alvin Tay Ming Hwee
// A0218390E

class Researcher implements Comparable<Researcher> {
  int arrival;
  int end;

  public Researcher(int arrival, int duration) {
    this.arrival = arrival;
    this.end = arrival + duration;
  }

  public int compareTo(Researcher b) {
    return this.arrival - b.arrival;
  }
}


public class AssigningWorkstations {
  public static void main(String[] args) {
    FastReader sc = new FastReader();
    int n = sc.nextInt();
    int m = sc.nextInt();
    
    Researcher[] arr = new Researcher[n];
    int count = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    
    for (int i = 0; i < n; i++) {
      arr[i] = new Researcher(sc.nextInt(), sc.nextInt());
    }

    Arrays.sort(arr);
    
    for (int i = 0; i < n; i++) {
      Researcher cur = arr[i];
      if (pq.isEmpty()) {
        pq.add(cur.end);
      } else if (cur.arrival < pq.peek()) {
        pq.add(cur.end);
      } else if (cur.arrival <= pq.peek() + m) {
        pq.poll();
        pq.add(cur.end);
        count++;
      } else {
        pq.poll();
        i--;
      }
    }
    System.out.println(count);
  }
}