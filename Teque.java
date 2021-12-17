// Alvin Tay Ming Hwee
// A0218390E


public class Teque  {
  
  public Deque front;
  public Deque back;

  public Teque() {
    this.front = new Deque();
    this.back = new Deque();
  }  
    
  public void push_front(int item) {
    front.push_front(item);
    front.balanceSize(back);
  }
   
  public void push_back(int item) {
    back.push_back(item);
    front.balanceSize(back);
  }

  public void push_middle(int item) {
      if (front.length <= back.length) {
        front.push_back(item);
      } else {
        back.push_front(item);
      }
  }

  public static int fastModulo(int dividend, int divisor) {
    return dividend & (divisor - 1);
  }

  public int get(int i) {
    if (i < front.length) {
      return front.get(fastModulo(front.front + i, front.maxSize));
    }
    return back.get(fastModulo(back.front + i - front.length, back.maxSize));
  }
  
}