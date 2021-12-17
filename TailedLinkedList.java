class TailedLinkedList {
  // attributes
  public ListNode head;
  public ListNode tail;
  public int num_nodes;

  // Return true if list is empty; otherwise return false.
  public boolean isEmpty() { return (num_nodes == 0); }

  // Return number of items in list
  public int size() { return num_nodes; }

  // get item at index
  public String getItemAtIndex(int index) {
    ListNode cur = head;

    if (index < 0 || index > size()-1) {
      System.out.println("invalid index");
      System.exit(1);
    }    
    if (index == size()-1)
      return tail.getItem();
    else {
      for (int counter = 0; counter != index; counter++)
        cur = cur.getNext();
      return cur.getItem();
    }
  }

  // Return first item
  public String getFirst() { return getItemAtIndex(0); }

  // Return last item
  public String getLast() { return getItemAtIndex(size()-1); }

  // add item at position index, shifting all current items from index onwards to the right by 1 
  // pre: 0 <= index <= size()
  public void  addAtIndex(int index, ListNode item) {
    ListNode cur;

    if (index >= 0 && index <= size()) {
      if (index == 0) // insert in front
        insert(null, item);
      else if (index == size()) // insert at the back, don't have to move all the way to the back
        insert(tail, item);
      else {
        cur = getNodeAtIndex(index-1); // access node at index-1
        insert(cur, item);
      }
    }
    else { // index out of bounds
      System.out.println("invalid index");
      System.exit(1);
    }
  } 

  // Add item to front of list
  public void addFront(ListNode item) { addAtIndex(0,item); }

  // Add item to back of list
  public void addBack(ListNode item) { addAtIndex(size(), item); }

  public void addNum(TailedLinkedList item) {
    this.num_nodes = this.num_nodes + item.size() - 1;
  }

  // Print values of nodes in list.
  public void print() {
    if (head == null)
      System.out.println("Nothing to print...");
    else {
      ListNode cur = head;
      for (int i=0; i < num_nodes; i++) {
       System.out.print(cur.getItem());
       cur = cur.getNext();
      }
    }
  }

  // non-interface helper methods
  public ListNode getHead() { return head; }
  public ListNode getTail() { return tail; }

  /* return the ListNode at index */
 public ListNode getNodeAtIndex(int index) {
    ListNode cur = head;

    if (index < 0 || index > size()-1) {
      System.out.println("invalid index");
      System.exit(1);
    }    
    for (int counter = 0; counter != index; counter++)
      cur = cur.getNext();

    return cur;
  }

  // insert newNode after the node referenced by cur 
  public void insert(ListNode cur, ListNode n) {
    // insert in front
    if (cur == null) {
      n.setNext(head);
      head = n; // update head
      if (tail == null) // update tail if list originally empty
        tail = head;
    }
    else { // insert anywhere else
      cur.setNext(n);
      if (cur == tail) // update tail if inserted new last item
        tail = tail.getNext();
    }
    num_nodes++;
  }
}
