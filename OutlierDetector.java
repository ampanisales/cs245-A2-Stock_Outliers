/**
* OutlierDetector assists StockOutliers in
* finding stock records that are outliers,
* and it uses the mean and standard
* deviation method for finding outliers
*
* @author Anthony Panisales
*/


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
		double variance = calculateVariance();
		double stdev = Math.sqrt(variance);
		double lowerLimit = mean - 2*stdev;
		double upperLimit = mean + 2*stdev;
		if (lowerLimit > adjClose || upperLimit < adjClose) {
			return true;
		} else {
			return false;
		}
	}
	
}
