
public class Link {

	private Object data;
	private Link next;
	
	public Link(Object newData) {
		data = newData;
		next = null;
	}
	
	public Link(Object newData, Link newNext) {
		data = newData;
		next = newNext;
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
