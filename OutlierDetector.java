
public class OutlierDetector {

	int n; //Number of records in range
	double mean;
	double sumCloseMinusMean;
	
	public OutlierDetector(int newN, double newSumCloseMinusMean, double newMean) {
		n = newN;
		sumCloseMinusMean = newSumCloseMinusMean;
		mean = newMean;
	}

	public double calculateVariance() { 
		return sumCloseMinusMean/(n-1);
	}
	
	public boolean isOutlier(double adjClose) {
		double y = adjClose;
		double variance = calculateVariance();
		double stdev = Math.sqrt(variance);
		double lowerLimit = mean - 2*stdev;
		double upperLimit = mean + 2*stdev;
		if (lowerLimit > y || upperLimit < y) {
			return true;
		} else {
			return false;
		}
	}
	
}
