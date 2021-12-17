class ListNode {
	/* attributes */
	public String item;
	public ListNode next;

	/* constructors */
	public ListNode(String val) { this(val, null); }

	public ListNode(String val, ListNode n) { 
		item = val; 
		next = n; 
	}

	/* get the next ListNode */
	public ListNode getNext() { return next; }

	/* get the item of the ListNode */
	public String getItem() { return item; }

  /* set the item of the ListNode */
  public void setItem(String val) { item = val; }

	/* set the next reference */
	public void setNext(ListNode n) { next = n; }
}
