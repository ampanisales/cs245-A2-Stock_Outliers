
public class OutlierDetector {

	/*independent variable (x) being the number of business days 
	since the starting date (cf. Requirement 2) and the dependent 
	variable (y) being the CLOSING price of the stock*/
	
	private int sumX;
	private double sumY;
	private int n;
	private double sumXY;
	private int sumXsquared;
	
	
	public OutlierDetector(int newSumX, double newSumY, int newN, double newSumXY,
							int newSumXsquared) {
		sumX = newSumX;
		sumY = newSumY;
		n = newN;
		sumXY = newSumXY;
		sumXsquared = newSumXsquared;
	}
	
	public double calculateA() {
		return (sumY*sumXsquared - sumX*sumXY)/ (n*sumXsquared - Math.pow(sumX, 2));
	}
		
	public double calculateB() {
		return (n*sumXY - sumX*sumY)/(n*sumXsquared - Math.pow(sumX, 2));
	}

	//EMAIL PROFESSOR ABOUT VARIANCE FORMULA
	public double calculateVariance(double sumPriceMinusMean) { 
		return sumPriceMinusMean/(n-1);
	}
	
	public boolean isOutlier(int day, double adjClose, double mean, double variance) {
		double a = calculateA();
		double b = calculateB();
		int x = day;
		double y = adjClose;
		double stdev = Math.sqrt(variance);
		double lowerLimit = mean - stdev;
		double upperLimit = mean + stdev;
		//System.out.println("a+b*x: " + (a+b*x) + " y: " + y + " variance: " + variance);
		//ASK WHAT IS THE CRITERIA FOR AN OUTLIER
		if (Math.abs((a+b*x) - y) > Math.abs((a+b*x) - variance)) {
			return true;
		}
		/*if (lowerLimit > y || upperLimit < y) {
			return true;
		}*/ else {
			return false;
		}
	}
	
}
