
public class StockData {

	private String date; 
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	private String volume;
	private boolean inRange;
	private int dayInRange;
	
	//Constructor
	public StockData() {
		inRange = false;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getOpen() {
		return open;
	}
	
	public String getHigh() {
		return high;
	}
	
	public String getLow() {
		return low;
	}
	
	public String getClose() {
		return close;
	}
	
	public String getAdjClose() {
		return adjClose;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public boolean getInRange() {
		return inRange;
	}
	
	public int getDayInRange() {
		return dayInRange;
	}
	
	public void setDate(String newDate) {
		date = newDate;
	}
	
	public void setOpen(String newOpen) {
		open = newOpen;
	}
	
	public void setHigh(String newHigh) {
		high = newHigh;
	}
	
	public void setLow(String newLow) {
		low = newLow;
	}
	
	public void setClose(String newClose) {
		close = newClose;
	}
	
	public void setAdjClose(String newAdjClose) {
		adjClose = newAdjClose;
	}
	
	public void setVolume(String newVolume) {
		volume = newVolume;
	}
	
	public void setInRange(boolean newInRange) {
		inRange = newInRange;
	}
	
	public void setDayInRange(int newDayInRange) {
		dayInRange = newDayInRange;
	}
	
}
