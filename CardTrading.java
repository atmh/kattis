// Alvin Tay Ming Hwee
// A0218390E

import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

// Kattio from https://github.com/kattis/kattio
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
      super(new BufferedOutputStream(System.out));
      r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
	    super(new BufferedOutputStream(o));
	    r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
	    return peekToken() != null;
    }

    public int getInt() {
	    return Integer.parseInt(nextToken());
    }

    public double getDouble() { 
	    return Double.parseDouble(nextToken());
    }

    public long getLong() {
	    return Long.parseLong(nextToken());
    }

    public String getWord() {
	    return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
    if (token == null) 
        try {
          while (st == null || !st.hasMoreTokens()) {
              line = r.readLine();
              if (line == null) return null;
              st = new StringTokenizer(line);
          }
          token = st.nextToken();
        } catch (IOException e) { }
          return token;
    }

    private String nextToken() {
	  String ans = peekToken();
	  token = null;
	  return ans;
  }
}

class Card implements Comparable<Card> {
  int buyPrice;
  int sellPrice;
  int sum;

  public Card(int buyPrice, int sellPrice) {
    this.buyPrice = buyPrice;
    this.sellPrice = sellPrice;
    this.sum = buyPrice + sellPrice;
  }

  public int compareTo(Card b) {
    return this.sum - b.sum;
  }
}

public class CardTrading {

  public static void main(String[] args) {
    try (Kattio io = new Kattio(System.in, System.out)) {
      int deckNum = io.getInt();
      int types = io.getInt();
      int exactCombo = io.getInt();
      int[] deck = new int[deckNum];

      for (int i = 0; i < deckNum; i++) {
        deck[i] = io.getInt();
      }

      Arrays.sort(deck);
      Card[] cards = new Card[types];
      int index = 0;

      for (int i = 0; index < types; i++) {   // array of card types, with respective buy sell price based on num of cards
        if (i >= deckNum || index + 1 != deck[i]) {
          cards[index] = new Card(io.getInt()*2, 0);
          io.getInt();
          index++;
          i--;
        } else {
          if (i + 1 < deckNum && deck[i] == deck[i + 1]) {
              io.getInt();
              cards[index] = new Card(0, io.getInt()*2);
              index++;
              i++;
          } else {
            cards[index] = new Card(io.getInt(), io.getInt());
            index++;
          }
        }
      }

      Arrays.sort(cards); // sort according to buy + sell price
      long bestPrice = 0;
      for (int i = 0; i < types; i++) {
        if (i < exactCombo) bestPrice -= cards[i].buyPrice;
        else bestPrice += cards[i].sellPrice;
      }
      
      io.println(bestPrice);
    }
  }
}

