// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class KattisQuest {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int n = Integer.parseInt(br.readLine());
    TreeSet<Quest> bbst = new TreeSet<Quest>();
    for (int i = 0; i < n; i++) {
      String[] line = br.readLine().split(" ");
      String command = line[0];
      if (command.equals("add")) {
        Quest quest = new Quest(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
        bbst.add(quest);
      } else {
        long gold = 0;
        int x = Integer.parseInt(line[1]);
        while (true) {
          Quest cur = bbst.lower(new Quest(x, Quest.max, Quest.max));
          if (cur == null) {
            pw.println(gold);
            break;
          }
          x -= cur.energy;
          gold += cur.gold;
          bbst.remove(cur);
        }
      }
    }
    pw.close();
    br.close();
  }
}

class Quest implements Comparable<Quest> {
  int energy;
  int gold;
  int id;
  final static int max = 1000000;
  static int count = 0;

  public Quest(int energy, int gold) {
    this.energy = energy;
    this.gold = gold;
    count++;
    id = count;
  }

  public Quest(int energy, int gold, int id) {
    this.energy = energy;
    this.gold = gold;
    this.id = id;
  }

  public int compareTo(Quest other) {
    if (this.energy > other.energy) return 1;
    else if (this.energy < other.energy) return -1;
    if (this.gold > other.gold) return 1;
    else if (this.gold < other.gold) return -1;
    return this.id - other.id; 
  }
}