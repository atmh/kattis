// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Islands {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] dimension = br.readLine().split(" ");
    int r = Integer.parseInt(dimension[0]);
    int c = Integer.parseInt(dimension[1]);

    char[][] grid = new char[r][c];
    boolean[][] visited = new boolean[r][c];

    for (int i = 0; i < r; i++) {
      String row = br.readLine();
      grid[i] = row.toCharArray();
    }

    int cc = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (!visited[i][j] && (grid[i][j] == 'L')) {
          cc++;
          dfs(i, j, grid, visited);
        }
      }
    }
    System.out.println(cc);
    br.close();
  }

  public static void dfs(int x, int y, char[][] grid, boolean[][] visited) {
    visited[x][y] = true;
    if (x-1>-1 && !visited[x-1][y] && (grid[x-1][y] == 'L' || grid[x-1][y] == 'C')) {
      dfs(x-1,y,grid,visited);
    } 
    if (x+1<grid.length && !visited[x+1][y] && (grid[x+1][y] == 'L' || grid[x+1][y] == 'C')) {
      dfs(x+1,y,grid,visited);
    } 
    if (y-1>-1 && !visited[x][y-1] && (grid[x][y-1] == 'L' || grid[x][y-1] == 'C')) {
      dfs(x,y-1,grid,visited);
    } 
    if (y+1<grid[0].length && !visited[x][y+1] && (grid[x][y+1] == 'L' || grid[x][y+1] == 'C')) {
      dfs(x,y+1,grid,visited);
    }
  }
}