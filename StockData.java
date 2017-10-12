
public class StockData {

	private String date; 
	private String open;
	private String high;
	private String low;
	private String close;
	private String volume;
	
	//Constructor
	public StockData() {}
	
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
	
	public String getVolume() {
		return volume;
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
	
	public void setVolume(String newVolume) {
		volume = newVolume;
	}
	
}
