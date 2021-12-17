// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WeakVertices {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int v = Integer.parseInt(br.readLine());
    while (v != -1) {
      int[][] adjMatrix = new int[v][v];
      for (int i = 0; i < v; i++) {
        String[] line = br.readLine().split(" ");
        for (int j = 0; j < v; j++) {
            adjMatrix[i][j] = Integer.parseInt(line[j]);
        }
      }

      for (int i = 0; i < v; i++) {
        boolean valid = false;
        for (int j = 0; j < v; j++) {
          for (int k = 0; k < v; k++) {
            if (adjMatrix[i][j] == 1 && adjMatrix[i][k] == 1 && adjMatrix[j][k] == 1) {
              valid = true;
              break;
            }
          }
          if (valid) break;
        }
        if (!valid) pw.print(i + " ");
      }
      pw.println();
      v = Integer.parseInt(br.readLine());
    }
    pw.close();
    br.close();
  }
}