/**
* Class Purpose: StockOutliers takes in a file 
* containing a list of stock records and finds 
* the outliers in the file within a certain date 
* range
*
* @author Anthony Panisales
*/

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class StockOutliers {
	
	public static void main(String args[]) {

		Scanner scan = new Scanner(System.in);
		
		boolean fileExists = false;
		while (!fileExists) {
			try {
				System.out.print("Stock Symbol: ");
				String symbol = scan.next();
				String filename = symbol + ".csv";
				Scanner filescan = new Scanner(new File(filename));
				System.out.print("Starting Date (Use year-month-day format): ");
				String date = scan.next();
				SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
				sdformat.setLenient(false);
				Date startDate = sdformat.parse(date);
				Calendar endCal = Calendar.getInstance();
				endCal.setTime(startDate);
				endCal.add(Calendar.YEAR, 1);
				Date endDate = endCal.getTime();
				LinkedList allRecords = new LinkedList();
				filescan.nextLine(); //Makes sure first line isn't used
				filescan.useDelimiter(Pattern.compile(",|;"));
				
				//Needed to find variance/mean
				int recordsInRange = 0;
				double sumAdjClose = 0;
				
				while (filescan.hasNextLine()) {
					StockData data = new StockData();
					String tempDate = filescan.next().replace("\n", "").replace("\r", "");
					if (tempDate.equals("")) { //For last line in file
						break;
					} else {
						data.setDate(tempDate);
					}
					Date recordDate = sdformat.parse(data.getDate());
					data.setOpen(filescan.next());
					data.setHigh(filescan.next());
					data.setLow(filescan.next());
					data.setClose(filescan.next());
					data.setAdjClose(filescan.next());
					data.setVolume(filescan.next());
					allRecords.add(data);
					//If record is within the range
					if ((recordDate.equals(startDate) || recordDate.after(startDate))
							&& recordDate.before(endDate)) {
						recordsInRange++; //Needed for variance/mean
						data.setInRange(true);
						sumAdjClose += Double.parseDouble(data.getAdjClose());
					}
				}
				filescan.close();
				
				if (recordsInRange < 2) {
					System.out.println("There is insufficient data for this period." + "\n");
					continue;
				} else {
					//Make outliers linked List
					LinkedList outliers = new LinkedList();
					LinkedList.ListIterator it = allRecords.iterator();
					double mean = sumAdjClose/recordsInRange;
					double sumCloseMinusMean = 0;

					//To help calculate the variance
					while (it.hasNext()) {
						StockData record = (StockData) it.next();
						Double adjClose = Double.parseDouble(record.getAdjClose()); 
						if (record.getInRange()) {
							sumCloseMinusMean += Math.pow(adjClose-mean, 2);
						}
					}

					//Fill outliers linked list
					OutlierDetector detector = new OutlierDetector(recordsInRange, sumCloseMinusMean, mean);
					it = allRecords.iterator();
					while (it.hasNext()) {
						StockData record = (StockData) it.next();
						Double adjClose = Double.parseDouble(record.getAdjClose()); 
						if (record.getInRange() && detector.isOutlier(adjClose)) {
							outliers.add(record);
						}
					}
					
					//Prints linked list of outliers
					System.out.println("Outliers from " + sdformat.format(startDate) + 
										" to " + sdformat.format(endDate) + ":");
					if (outliers.size() == 0) {
						System.out.println("No outliers found");
					} else {
						it = outliers.iterator();
						DecimalFormat closeFormat = new DecimalFormat(".##");
						while (it.hasNext()) {
							StockData outlier = (StockData) it.next();
							Double adjClose = Double.parseDouble(outlier.getAdjClose()); 
							System.out.println(outlier.getDate() + ": " + closeFormat.format(adjClose));
						}
					}
					break;
				}
			} catch (FileNotFoundException e) {
				System.out.println("File of this stock not found" + "\n");
				continue;
			} catch (ParseException e) {
				System.out.println("Invalid date" + "\n");
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				break;
			}
		}
		scan.close();
	}
}
