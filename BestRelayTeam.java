// Alvin Tay Ming Hwee
// A0218390E

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Runner implements Comparable<Runner> {
  String name;
  double ini;
  double flying;

  public Runner(String name, double ini, double flying) {
    this.name = name;
    this.ini = ini;
    this.flying = flying;
  }

  public int compareTo(Runner b) {
    if (this.flying < b.flying) {
      return -1;
    } if (this.flying > b.flying) {
      return 1;
    }
    return 0;
  }
}

public class BestRelayTeam {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int arrSize = sc.nextInt();
    Runner[] arr = new Runner[arrSize];
    sc.nextLine();
    for (int i = 0; i < arrSize; i++) {
      String name = sc.next();
      double ini = sc.nextDouble();
      double flying = sc.nextDouble();
      arr[i] = new Runner(name, ini, flying);
    }

    Arrays.sort(arr);

    double fastest = 100;
    ArrayList<String> runners = null;

    for (int i = 0; i < arrSize; i++) {
      ArrayList<String> temp = new ArrayList<String>();
      double time = arr[i].ini;
      temp.add(arr[i].name);
      for (int j = 0; temp.size() < 4; j++) {
        if (i == j) continue;
        time += arr[j].flying;
        temp.add(arr[j].name);
      }
      if (time < fastest) {
        fastest = time;
        runners = temp;
      }
    }
    System.out.printf("%.2f\n", fastest);
    for (int i = 0; i < 4; i++) {
      System.out.println(runners.get(i));
    }   
    sc.close();
  }
}

