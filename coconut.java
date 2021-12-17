// Alvin Tay Ming Hwee
// A0218390E

import java.util.ArrayList;
import java.util.Scanner;

class Player {
  int pos;
  int state;

  public Player(int pos, int state) {
    this.pos = pos;
    this.state = state;
  }
}

public class coconut {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int syl = sc.nextInt();
    int players = sc.nextInt();
    ArrayList<Player> arr = new ArrayList<Player>();
    for (int i = 0; i < players; i++) {
      arr.add(new Player(i, 1));
    }
    int index = 0;
    while (arr.size() > 1) {
      index = (index + syl - 1) % arr.size();
      Player cur = arr.get(index);
      if (cur.state == 1) {
        cur.state = 2;
        arr.add(index, new Player(cur.pos, 2));
      } else if (cur.state == 2) {
        cur.state = 3;
        index++;
      } else {
        arr.remove(index);
      }
    }
    System.out.println(arr.get(0).pos + 1);

    sc.close();  
  }
}