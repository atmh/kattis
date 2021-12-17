// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;

public class MillionaireMadness {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] dimension = br.readLine().split(" ");
    int m = Integer.parseInt(dimension[0]);
    int n = Integer.parseInt(dimension[1]);
    int[][] grid = new int[m][n];
    boolean[][] visited = new boolean[m][n];
    PriorityQueue<Pos> pq = new PriorityQueue<>();

    for (int i = 0; i < m; i++) { // initialize the grid
      String[] row = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        grid[i][j] = Integer.parseInt(row[j]);
      }
    }

    pq.add(new Pos(0,0,0));
    int max = 0;

    int[] xdir = {1, -1, 0, 0};
    int[] ydir = {0, 0, 1, -1};

    while (!pq.isEmpty()) {
      Pos cur = pq.poll();
      if (cur.dist > max) max = cur.dist;
      if (cur.x == m - 1 && cur.y == n - 1) break; // reached bottom right
      if (!visited[cur.x][cur.y]) {
        visited[cur.x][cur.y] = true;
        for (int i = 0; i < 4; i++) {
          int x = cur.x + xdir[i];
          int y = cur.y + ydir[i];
          if (x > -1 && x < m && y > -1 && y < n) {
            int diff = grid[x][y] - grid[cur.x][cur.y];
            if (diff > 0) {
              pq.add(new Pos(x,y,diff));
            } else {
              pq.add(new Pos(x,y,0)); 
            }
          }
        }
      }
    }
    System.out.println(max);
    br.close();
  }
}

class Pos implements Comparable<Pos> {
  int x;
  int y;
  int dist;

  public Pos(int x, int y, int dist) {
    this.x = x;
    this.y = y;
    this.dist = dist;
  }

  public int compareTo(Pos other) {
    return this.dist - other.dist;
  }
}