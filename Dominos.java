// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Dominos {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out) ));
    int testCase = Integer.parseInt(br.readLine());
    for (int cases = 0; cases < testCase; cases++) {
      String[] tiles = br.readLine().split(" ");
      int num = Integer.parseInt(tiles[0]);
      int lines = Integer.parseInt(tiles[1]);

      boolean[] visited = new boolean[num];
      
      ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
      ArrayList<ArrayList<Integer>> adjListRev = new ArrayList<>();
      for (int i = 0; i < num; i++) {
        adjList.add(new ArrayList<Integer>());
        adjListRev.add(new ArrayList<Integer>());
      }

      // init
      for (int i = 0; i < lines; i++) {
        String[] domino = br.readLine().split(" ");
        int x = Integer.parseInt(domino[0])-1;
        int y = Integer.parseInt(domino[1])-1;
        adjList.get(x).add(y);
      }
      
      // dfs topo
      ArrayList<Integer> topo = new ArrayList<>();
      for (int v = 0; v < num; v++) { 
        if (!visited[v]) {
          dfsTopo(v, visited, adjList, topo);
        }
      }

      // transpose
      visited = new boolean[num];
      for (int i = 0; i < adjList.size(); i++) {
        ArrayList<Integer> cur = adjList.get(i);
        for (int j = 0; j < cur.size(); j++) {
          adjListRev.get(cur.get(j)).add(i);
        }
      }

      // scc
      int scc = 0;
      for (int v = topo.size()-1; v > -1; v--) {
        int cur = topo.get(v);
        if (!visited[cur]) {
          ArrayList<Integer> cycle = new ArrayList<>();
          dfsScc(cur, visited, adjListRev, cycle);
          if (cycle.size() == 1) {
            if (adjListRev.get(cur).isEmpty()) scc++;
            dfs(cur, visited, adjList);
          } else {
            scc++;
            for (int i : cycle) {
              dfs(i, visited, adjList);
            }
          }
        }
      }
      pw.println(scc);
    }
    br.close();
    pw.close();
  }

  public static void dfsTopo(Integer u, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> topo) {
    visited[u] = true;
    for (Integer v : adjList.get(u)) {
      if (!visited[v]) {
        dfsTopo(v, visited, adjList, topo);
      }
    }
    topo.add(u);
  }

  public static void dfsScc(Integer u, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> cycle) {
    visited[u] = true;
    cycle.add(u);
    for (Integer v : adjList.get(u)) {
      if (!visited[v]) {
        dfsScc(v, visited, adjList, cycle);
      }
    }
  }

  public static void dfs(Integer u, boolean[] visited, ArrayList<ArrayList<Integer>> adjList) {
    visited[u] = true;
    for (Integer v : adjList.get(u)) {
      if (!visited[v]) {
        dfs(v, visited, adjList);
      }
    }
  }
}