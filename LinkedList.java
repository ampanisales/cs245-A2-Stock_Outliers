/**
* Class Purpose: LinkedList is an 
* implementation of the Linked List data 
* structure and it is used by StockOutliers 
* to contain all of the StockData objects 
* that created from the CSV file, and to 
* contain the StockData objects that are 
* outliers
*
* @author Anthony Panisales
*/

public class LinkedList {

	Link head;
	int size;

	class Link {

		Object data;
		Link next;
		
		public Link(Object newData) {
			data = newData;
			next = null;
		}
		
		public Object getData() {
			return data;
		}
		
		public Link getNext() {
			return next;
		}
		
		public void setData(Object newData) {
			data = newData;
		}
		
		public void setNext(Link newNext) {
			next = newNext;
		}
		
	}
	
	public class ListIterator {

		private Link link;
		
		public ListIterator() {
			//Makes dummy head
			link = new Link(null);
			link.setNext(head);
		}
		
		/**
		* Function Purpose: returns true if there is an Object 
		* after the current location
		*/
		public boolean hasNext() {
			return link.getNext() != null;
		}
		
		/**
		* Function Purpose: advances to the next Object instance
		* and returns a copy of that Object
		*/
		public Object next() {
			try {
				link = link.getNext();
				return link.getData();
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	public LinkedList() {
		size = 0;
	}
	
	/**
	* Function Purpose: adds data to the end of the LinkedList
	*/
	public void add(Object data) {
		Link currLink = head;
		if (currLink == null) {
			head = new Link(data);
		} else {
			while (currLink.getNext() != null) {
				currLink = currLink.getNext();
			}
			currLink.setNext(new Link(data));
		}
		size++;
	}

	/**
	* Function Purpose: adds the data parameter to the position in
	* the LinkedList, with the first position being 0
	*/
	public void add(int pos, Object data) throws Exception {
		/* 1. Error check: Is the position valid?
		2. Create a Link instance. Populate with argument Object
		3. Find (curr) pos-1
		4. Adjust pointers
			link.next = curr.next
			curr.next = link	
		5. Update size	*/
		
		if (pos < 0 || pos > size) {
			throw new Exception();
		} else {
			Link newLink = new Link(data);
			if (pos == 0) { //Replace head;
				newLink.setNext(head);
				head = newLink;
			} else {
				Link currLink = head;
				int counter = 0;
				while (counter < pos-1) {
					currLink = currLink.getNext();
					counter++;
				}
				newLink.setNext(currLink.getNext());
				currLink.setNext(newLink);
			}
			size++;
		}
	}
		
	/**
	* Function Purpose: removes the Object stored at position pos
	*/
	public void remove(int pos) throws Exception {
		/*1. Error check: Is position valid?
		2. Special cases? First or last element
		3. Loop to Link at pos-1
		4. Store data to return
		5. Manipulate pointers
			link.next = link.next.next
		6. Update size
		7. Return data*/
		
		if (pos < 0 || pos > size) {
			throw new Exception();
		} else {
			if (pos == 0) {
				head = head.getNext();
			} else {
				Link currLink = head;
				int counter = 0;
				while (counter < pos-1) {
					currLink = currLink.getNext();
					counter++;
				}
				currLink.setNext(currLink.getNext().getNext());
			}
			size--;
		}
	}
	
	/**
	* Function Purpose: returns the Object stored at position pos
	*/
	public Object get(int pos) throws Exception {
		if (pos < 0 || pos > size) {
			throw new Exception();
		} else {
			if (pos == 0) {
				return head.getData();
			} else {
				Link currLink = head;
				int counter = 0;
				while (counter < pos) {
					currLink = currLink.getNext();
					counter++;
				}
				return currLink.getData();
			}
		}	
	}
	
	/**
	* Function Purpose: returns the size (number of Object instances)
	* of the LinkedList 
	*/
	public int size() {
		return size;
	}
	
	/**
	* Function Purpose: returns a ListIterator instance
	*/
	public ListIterator iterator() {
		return new ListIterator();
	}

}
