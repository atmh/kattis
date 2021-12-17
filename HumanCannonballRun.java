// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class HumanCannonballRun {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    String[] start = br.readLine().split(" ");
    String[] end = br.readLine().split(" ");
    int n = Integer.parseInt(br.readLine());
    Pos[] pos = new Pos[n+2];

    pos[0] = new Pos(Double.parseDouble(start[0]), Double.parseDouble(start[1]));
    pos[n+1] = new Pos(Double.parseDouble(end[0]), Double.parseDouble(end[1]));
    for (int i = 1; i <= n; i++) {
      String[] cur = br.readLine().split(" ");
      pos[i] = new Pos(Double.parseDouble(cur[0]), Double.parseDouble(cur[1]));
    }

    double[][] adjMatrix = new double[n+2][n+2];
    for (int i = 0; i < adjMatrix.length; i++) {
      for (int j = 0; j < adjMatrix.length; j++) {
        if (i == j) adjMatrix[i][j] = 0.0;
        else {
          double dist = dist(pos, i, j);
          double walk = dist/5;
          if (i == 0) {
            adjMatrix[i][j] = walk;
          } else {
            double cannon = (Math.abs(dist-50))/5 + 2;
            adjMatrix[i][j] = Math.min(walk, cannon);
          }
        }
      }
    }

    PriorityQueue<Pos> pq = new PriorityQueue<>();
    double[] d = new double[n+2];
    for (int i = 0; i < d.length; i++) {
      d[i] = Double.MAX_VALUE;
    }
    d[0] = 0;
    pq.add(new Pos(0.0, 0.0));
    while (!pq.isEmpty()) {
      Pos cur = pq.poll();
      int u = (int) cur.y;
      if (cur.x == d[u]) {
        for (int v = 0; v < adjMatrix[u].length; v++) {
          if (v == u) {
          } else if (d[v] > d[u] + adjMatrix[u][v]) {
            d[v] = d[u] + adjMatrix[u][v];
            pq.add(new Pos(d[v], (double)v));
          }
        }
      }
    }

    pw.println(d[n+1]);

    
    pw.close();
    br.close();
  }

  public static double dist(Pos[] pos, int x, int y) {
    Pos xp = pos[x];
    Pos yp = pos[y];
    return Math.sqrt(Math.pow((xp.x - yp.x),2) + Math.pow((xp.y - yp.y),2));
  }
}

class Pos implements Comparable<Pos>{
  double x;
  double y;
  
  public Pos(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public int compareTo(Pos other) {
    return (int)this.x - (int)other.x;
  }
}