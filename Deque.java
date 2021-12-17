class Deque {
    public int [] arr; 
    public int front, back; 
    public int maxSize; 
    // public final int INITSIZE = 524288;
    public final int INITSIZE = 1024;
    public int length;
     
    public Deque() {
      arr = new int[INITSIZE];
      front = 0;
      back = 0;
      this.maxSize = INITSIZE;
      this.length = 0;
    }
  
    // public boolean empty() { 
    //   return (front == back);
    // }

    public static int fastModulo(int dividend, int divisor) {
      return dividend & (divisor - 1);
    }

    public void push_front(Integer item) {
      if (fastModulo(front - 1, maxSize) == back) enlargeArr(); 
      int cur = front = fastModulo(front - 1, maxSize);
      arr[cur] = item; 
      if (front == 0 && back == 0) {
        back = fastModulo(back + 1, maxSize);
      } else {
        front = cur;
      }
      length++;
    }

    public Integer remove_front() { 
      // if (empty()) return null; 
      Integer item = arr[front]; 
      front = fastModulo(front + 1, maxSize); 
      length--;
      return item; 
    }

    public void push_back(Integer item) { 
      if (fastModulo(back + 1, maxSize) == front) enlargeArr(); 
      arr[back] = item; 
      back = fastModulo(back + 1, maxSize);
      length++;
    }

    public Integer remove_back() { // check
      // if (empty()) return null; 
      int cur = fastModulo(back - 1, maxSize);
      Integer item = arr[cur]; 
      back = cur;
      length--;
      return item; 
    }

    public void enlargeArr() {
      int newSize = maxSize * 2;
      int[] temp = new int[newSize];
      for (int j=0; j < maxSize; j++) {
        temp[j] = arr[(front+j) % maxSize];
      }
      front = 0;
      back = maxSize - 1;
      arr = temp;
      maxSize = newSize;
    }

    public void balanceSize(Deque rear) {
      if (this.length - rear.length > 1) {
        rear.push_front(this.remove_back());
      } else if (this.length < rear.length) {
        this.push_back(rear.remove_front());
      }
    }

    public int get(int i) {
      return this.arr[i];
    }

}