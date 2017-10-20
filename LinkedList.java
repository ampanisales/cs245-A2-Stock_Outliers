
public class LinkedList {

	Link head;
	int size;
	
	public class ListIterator {

		private Link link;
		
		public ListIterator() {
			//Makes dummy head
			link = new Link(null);
			link.setNext(head);
		}
		
		public boolean hasNext() {
			return link.getNext() != null;
		}
		
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
	
	//Add to end of list
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
	
	public int size() {
		return size;
	}
	
	public ListIterator iterator() {
		return new ListIterator();
	}

}
