// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LostMap {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int n = Integer.parseInt(br.readLine());
    int[][] adjMatrix = new int[n][n];
    for (int i = 0; i < n; i++) {
      String[] row = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        adjMatrix[i][j] = Integer.parseInt(row[j]);
      }
    }

    ArrayList<Edge> edgeList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        edgeList.add(new Edge(i, j, adjMatrix[i][j]));
      }
    }
    Collections.sort(edgeList);
    
    UFDS ufds = new UFDS(n);
    for (int i = 0; i < edgeList.size(); i++) {
      Edge cur = edgeList.get(i);
      if (!ufds.isSameSet(cur.u, cur.v)) {
        ufds.unionSet(cur.u, cur.v);
        pw.printf("%d %d\n", cur.u+1, cur.v+1);
      }
    }
    pw.close();
    br.close();
  }
}

class Edge implements Comparable<Edge> {
  int u;
  int v;
  int dist;

  public Edge(int u, int v, int dist) {
    this.u = u;
    this.v = v;
    this.dist = dist;
  }

  public int compareTo(Edge other) {
    return this.dist - other.dist;
  }
}

class UFDS {                                
  public int[] p;
  public int[] rank;

  public UFDS(int N) {
    p = new int[N];
    rank = new int[N];

    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
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
      } else { 
        p[x] = y;
        if (rank[x] == rank[y]) rank[y] = rank[y]+1;
      } 
    } 
  }
}