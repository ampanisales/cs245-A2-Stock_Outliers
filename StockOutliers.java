import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;

public class StockOutliers {
	
	public static void main(String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		//DO INPUT VALIDATION
		boolean fileExists = false;
		while (!fileExists) {
			try {
				System.out.print("Stock Symbol: ");
				String symbol = scan.next();
				String filename = symbol + ".csv";
				Scanner filescan = new Scanner(new File(filename));
				//Check if startDate is in right format? Check if startDate is a valid date?
				System.out.print("Starting Date (Use year-month-day format): ");
				String date = scan.next();
				scan.close();
				String format = "yyyy-MM-dd";
				Date startDate = new SimpleDateFormat(format).parse(date);
				Calendar endCal = Calendar.getInstance();
				endCal.setTime(startDate);
				endCal.add(Calendar.YEAR, 1);
				Date endDate = endCal.getTime();
				LinkedList allRecords = new LinkedList();
				
				filescan.nextLine(); //Makes sure first line isn't used
				filescan.useDelimiter(Pattern.compile(",|;"));
				
				int recordsInRange = 0;
				double sumAdjClose = 0;
				double sumXY = 0;
				int sumXsquared = 0;
				
				while (filescan.hasNextLine()) {
					StockData data = new StockData();
					String tempDate = filescan.next().replace("\n", "").replace("\r", "");
					if (tempDate.equals("")) { //For last line in file
						break;
					} else {
						data.setDate(tempDate);
					}
					Date recordDate = new SimpleDateFormat(format).parse(data.getDate());
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
						recordsInRange++; //This is n and sumX
						data.setInRange(true);
						data.setDayInRange(recordsInRange);
						sumXY += recordsInRange*Double.parseDouble(data.getAdjClose());
						sumXsquared += recordsInRange*recordsInRange;
						sumAdjClose += Double.parseDouble(data.getAdjClose()); 
					}
				}
				filescan.close();
				
				if (recordsInRange < 2) {
					System.out.println("There is insufficient data for this period.");
				} else {
					//Make outliers linked List
					LinkedList outliers = new LinkedList();
					OutlierDetector detector = new OutlierDetector(recordsInRange, sumAdjClose,
							recordsInRange, sumXY, sumXsquared);
					LinkedList.ListIterator it = allRecords.iterator();
					double mean = sumAdjClose/recordsInRange;
					double sumCloseMinusMean = 0;
					//double variance = sum[(price - mean)^2]/n

					//To help calculate the variance
					while (it.hasNext()) {
						StockData record = (StockData) it.next();
						Double adjClose = Double.parseDouble(record.getAdjClose()); 
						if (record.getInRange()) {
							sumCloseMinusMean += Math.pow(adjClose-mean, 2);
						}
					}

					//Fill outliers linked list
					double variance = detector.calculateVariance(sumCloseMinusMean);
					it = allRecords.iterator();
					while (it.hasNext()) {
						StockData record = (StockData) it.next();
						Double adjClose = Double.parseDouble(record.getAdjClose()); 
						if (record.getInRange() && detector.isOutlier(record.getDayInRange(), adjClose, variance)) {
							outliers.add(record);
						}
					}
					
					//Prints linked list of outliers
					System.out.println("Outliers from " + new SimpleDateFormat(format).format(startDate) + 
										" to " + new SimpleDateFormat(format).format(endDate) + ":");
					it = outliers.iterator();
					DecimalFormat closeFormat = new DecimalFormat(".##");
					while (it.hasNext()) {
						StockData outlier = (StockData) it.next();
						Double adjClose = Double.parseDouble(outlier.getAdjClose()); 
						System.out.println(outlier.getDate() + ": " + closeFormat.format(adjClose));
					}
					break;
				}
			} catch (FileNotFoundException e) {
				System.out.println("File of this stock not found");
				continue;
			} catch (ParseException e) {
				System.out.println("Invalid date");
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
				continue;
			}
		}
	}
}
