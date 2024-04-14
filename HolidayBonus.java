

public class HolidayBonus extends TwoDimRaggedArrayUtility{
	
	private final static int HIGHEST_BONUS = 5000;
	private final static int LOWEST_BONUS = 1000;
	private final static int ALL_OTHER_STORES = 2000;
	
	
	public HolidayBonus() {
		
	}
	
	public static double[] calculateHolidayBonus(double[][] data) {
		int numStores = data.length;
	    double[] bonuses = new double[numStores];

	    for (int i = 0; i < numStores; i++) {
	        double totalSales = 0.0;
	        for (int j = 0; j < data[i].length; j++) {
	            totalSales += data[i][j];
	        }

	        double highestSales = getHighestInArray(data);
	        double lowestSales = getLowestInArray(data);
	        
	        if (totalSales == highestSales) {
	            bonuses[i] = HIGHEST_BONUS;
	        } else if (totalSales == lowestSales) {
	            bonuses[i] = LOWEST_BONUS;
	        } else {
	            bonuses[i] = ALL_OTHER_STORES;
	        }
	    }

	    return bonuses;
	}
	
	public static double calculateTotalHolidayBonus(double[][] data) {
	    double[] bonuses = calculateHolidayBonus(data);
	    double totalHolidayBonus = 0.0;
	    
	    for (double bonus : bonuses) {
	        totalHolidayBonus += bonus;
	    }

	    return totalHolidayBonus;
	}

}
