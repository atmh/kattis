// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Ladice {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out) ));
    String[] temp = br.readLine().split(" ");
    int N = Integer.parseInt(temp[0]);
    int L = Integer.parseInt(temp[1]);
    UFDS ufds = new UFDS(L);
    for (int i = 0; i < N; i++) {
      String[] drawers = br.readLine().split(" ");
      int x = Integer.parseInt(drawers[0]) - 1;
      int y = Integer.parseInt(drawers[1]) - 1;
      ufds.unionSet(x, y);
      int root = ufds.findSet(x);
      if (ufds.size[root] > ufds.items[root]) {
        pw.println("LADICA");
        ufds.items[root]++;
      } else {
        pw.println("SMECE");
      }
    }

    
    pw.close();
    br.close();
  }
}

class UFDS {                                
  public int[] p;
  public int[] rank;
  public int[] items;
  public int[] size;

  public UFDS(int N) {
    p = new int[N];
    rank = new int[N];
    items = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
      items[i] = 0;
      size[i] = 1;
    }
  }

  public int findSet(int i) { 
    if (p[i] == i) return i;
    else {
      p[i] = findSet(p[i]);
      return p[i]; 
    } 
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) { 
      int x = findSet(i), y = findSet(j);
      if (rank[x] > rank[y]) {
        p[y] = x;
        size[x] += size[y];
        items[x] += items[y];
      } else { 
        p[x] = y;
        if (rank[x] == rank[y]) rank[y] = rank[y]+1;
        size[y] += size[x];
        items[y] += items[x];
      } 
    } 
  }
}