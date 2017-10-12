import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.File;

public class StockOutliers {
	
	public static void main(String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		//DO INPUT VALIDATION
		try {
			System.out.print("Stock Symbol: ");
			String symbol = scan.next();
			//Check if startDate is in right format?
			System.out.print("Starting Date: ");
			String date = scan.next();
			String format = "yyyy-MM-dd";
			Date startDate = new SimpleDateFormat(format).parse(date);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(startDate);
			endCal.add(Calendar.YEAR, 1);
			Date endDate = endCal.getTime();
			String filename = symbol + ".csv";
			Scanner filescan = new Scanner(new File(filename));
			//LinkedList outliers = new LinkedList();
			ArrayList<StockData> outliers = new ArrayList<StockData>();
			filescan.useDelimiter(",|\\n");
			while (filescan.hasNextLine()) {
				StockData data = new StockData();
				data.setDate(scan.next());
				Date recordDate = new SimpleDateFormat(format).parse(data.getDate());
				//Checks if date of record is before the end date
				if (recordDate.before(endDate)) {
					data.setOpen(scan.next());
					data.setHigh(scan.next());
					data.setLow(scan.next());
					data.setClose(scan.next());
					data.setVolume(scan.next());
//					if (Closing price is an outlier) {
//						outliers.add(data);
//					}
					outliers.add(data);
				}
			}
			filescan.close();
			
			//Prints linked list of outliers
			if (outliers.size() < 2) {
				System.out.println("There is insufficient data for this period.");
			} else {
//				LinkedList.ListIterator it = outliers.iterator();
//				while (it.hasNext()) {
//					StockData outlier = (StockData) it.next();
//					System.out.println(outlier.getDate() + ": " + outlier.getClose());
//				}
				for (int i = 0; i < outliers.size(); i++) {
					if (i % 3 == 0) {
						System.out.println(outliers.get(i).getDate() + ": " + outliers.get(i).getClose());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		scan.close();
	}
}
