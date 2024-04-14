/*
 * Class: CMSC203 
 * Instructor: Farnaz Eivazi
 * Description: 2-D ragged array utility class
 * Due: 04/14/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Michael Chance
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TwoDimRaggedArrayUtility {

	
	public TwoDimRaggedArrayUtility() {
		
	}
	
	public static double[][] readFile(File file) throws FileNotFoundException {
        // Initialize max rows and columns
        final int MAX_ROWS = 10;
        final int MAX_COLUMNS = 10;

        // Initialize temporary array to store strings
        String[][] tempArray = new String[MAX_ROWS][MAX_COLUMNS];

        // Initialize row and column counters
        int numRows = 0;
        int numColumns = 0;

        // Read from file
        try (Scanner scanner = new Scanner(file)) {
            // Read each line
            while (scanner.hasNextLine()) {
                // Read line and split into tokens
                String line = scanner.nextLine();
                String[] tokens = line.split(" ");

                // Check if maximum rows exceeded
                if (numRows >= MAX_ROWS) {
                    break;
                }

                // Update number of columns for this row
                numColumns = Math.max(numColumns, tokens.length);

                // Copy tokens to temporary array
                for (int j = 0; j < tokens.length; j++) {
                    tempArray[numRows][j] = tokens[j];
                }

                // Move to the next row
                numRows++;
            }
        }

        // Check if file is empty
        if (numRows == 0) {
            return null;
        }

        // Create ragged array based on number of rows
        double[][] array = new double[numRows][];

        // Create rows and convert values from strings to doubles
        for (int i = 0; i < numRows; i++) {
            array[i] = new double[numColumns];
            for (int j = 0; j < numColumns; j++) {
                if (tempArray[i][j] != null) {
                    array[i][j] = Double.parseDouble(tempArray[i][j]);
                }
            }
        }

        return array;
    }
	
	
    public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            // Iterate over each row of the ragged array
            for (double[] row : data) {
                // Check if the row is not null
                if (row != null) {
                    // Iterate over each double in the row
                    for (double value : row) {
                        // Write the double followed by a space
                        writer.print(value + " ");
                    }
                    // Write a newline character to separate rows
                    writer.println();
                }
            }
        }
    }
    
	
    public static double getTotal(double[][] data) {
        double total = 0.0;

        // Iterate over each row of the two-dimensional array
        for (double[] row : data) {
            // Check if the row is not null
            if (row != null) {
                // Iterate over each element in the row and add it to the total
                for (double value : row) {
                    total += value;
                }
            }
        }

        return total;
    }

    
    public static double getAverage(double[][] data) {
        double total = 0.0;
        int count = 0;

        // Iterate over each row of the two-dimensional array
        for (double[] row : data) {
            // Check if the row is not null
            if (row != null) {
                // Iterate over each element in the row and add it to the total
                for (double value : row) {
                    total += value;
                    count++;
                }
            }
        }

        // Calculate the average
        if (count > 0) {
            return total / count;
        } else {
            return 0.0; // Return 0 if there are no elements in the array
        }
    }
     
    public static double getRowTotal(double[][] data, int row) {
        double total = 0.0;

        // Check if the row index is valid and the row is not null
        if (row >= 0 && row < data.length && data[row] != null) {
            // Iterate over each element in the selected row and add it to the total
            for (double value : data[row]) {
                total += value;
            }
        }

        return total;
    }

    
    public static double getColumnTotal(double[][] data, int col) {
        double total = 0.0;

        // Check if the column index is valid
        if (col >= 0 && data.length > 0 && col < data[0].length) {
            // Iterate over each row in the array
            for (double[] row : data) {
                // Check if the current row has the selected column index
                if (col < row.length) {
                    // Add the value of the selected column to the total
                    total += row[col];
                }
            }
        }

        return total;
    }

    public static double getHighestInRow(double[][] data, int row) {
        // Check if the row index is valid
        if (row >= 0 && row < data.length) {
            double highest = data[row][0]; // Initialize with the first element of the row

            // Iterate over the elements of the row to find the highest element
            for (int col = 1; col < data[row].length; col++) {
                if (data[row][col] > highest) {
                    highest = data[row][col];
                }
            }

            return highest;
        } else {
            // If the row index is invalid, return a default value (e.g., NaN)
            return Double.NaN;
        }
    }

    
    public static int getHighestInRowIndex(double[][] data, int row) {
        // Check if the row index is valid
        if (row >= 0 && row < data.length && data[row].length > 0) {
            int highestIndex = 0; // Initialize with the index of the first element
            double highest = data[row][0]; // Initialize with the first element

            // Iterate over the elements of the row to find the highest element and its index
            for (int col = 1; col < data[row].length; col++) {
                if (data[row][col] > highest) {
                    highest = data[row][col];
                    highestIndex = col;
                }
            }

            return highestIndex;
        } else {
            // If the row index is invalid or the row is empty, return -1
            return -1;
        }
    }

    public static double getLowestInRow(double[][] data, int row) {
        // Check if the row index is valid
        if (row >= 0 && row < data.length && data[row].length > 0) {
            double lowest = data[row][0]; // Initialize with the first element

            // Iterate over the elements of the row to find the lowest element
            for (int col = 1; col < data[row].length; col++) {
                if (data[row][col] < lowest) {
                    lowest = data[row][col];
                }
            }

            return lowest;
        } else {
            // If the row index is invalid or the row is empty, return 0.0
            return 0.0;
        }
    }

    public static int getLowestInRowIndex(double[][] data, int row) {
        // Check if the row index is valid
        if (row >= 0 && row < data.length && data[row].length > 0) {
            double lowest = data[row][0]; // Initialize with the first element
            int lowestIndex = 0; // Initialize the index of the lowest element

            // Iterate over the elements of the row to find the lowest element and its index
            for (int col = 1; col < data[row].length; col++) {
                if (data[row][col] < lowest) {
                    lowest = data[row][col];
                    lowestIndex = col;
                }
            }

            return lowestIndex;
        } else {
            // If the row index is invalid or the row is empty, return -1
            return -1;
        }
    }

    public static double getHighestInColumn(double[][] data, int col) {
        // Check if the column index is valid
        if (col >= 0 && data.length > 0 && col < data[0].length) {
            double highest = data[0][col]; // Initialize with the first element of the column

            // Iterate over the rows to find the highest element in the column
            for (int row = 1; row < data.length; row++) {
                // Check if the current row has the column index
                if (col < data[row].length && data[row][col] > highest) {
                    highest = data[row][col];
                }
            }

            return highest;
        } else {
            // If the column index is invalid or the array is empty, return Double.MIN_VALUE
            return Double.MIN_VALUE;
        }
    }

    public static int getHighestInColumnIndex(double[][] data, int col) {
        // Check if the column index is valid
        if (col >= 0 && data.length > 0 && col < data[0].length) {
            double highest = Double.MIN_VALUE; // Initialize with the smallest possible value
            int highestRowIndex = -1; // Initialize with an invalid index

            // Iterate over the rows to find the highest element in the column
            for (int row = 0; row < data.length; row++) {
                // Check if the current row has the column index
                if (col < data[row].length && data[row][col] > highest) {
                    highest = data[row][col];
                    highestRowIndex = row;
                }
            }

            return highestRowIndex;
        } else {
            // If the column index is invalid or the array is empty, return -1
            return -1;
        }
    }

    public static double getLowestInColumn(double[][] data, int col) {
        // Check if the column index is valid
        if (col >= 0 && data.length > 0 && col < data[0].length) {
            double lowest = Double.MAX_VALUE; // Initialize with the largest possible value

            // Iterate over the rows to find the lowest element in the column
            for (double[] row : data) {
                // Check if the current row has the column index
                if (col < row.length && row[col] < lowest) {
                    lowest = row[col];
                }
            }

            return lowest;
        } else {
            // If the column index is invalid or the array is empty, return NaN
            return Double.NaN;
        }
    }

    public static int getLowestInColumnIndex(double[][] data, int col) {
        // Check if the column index is valid
        if (col >= 0 && data.length > 0 && col < data[0].length) {
            double lowest = Double.MAX_VALUE; // Initialize with the largest possible value
            int lowestIndex = -1; // Initialize the index of the lowest element

            // Iterate over the rows to find the lowest element in the column
            for (int i = 0; i < data.length; i++) {
                // Check if the current row has the column index
                if (col < data[i].length && data[i][col] < lowest) {
                    lowest = data[i][col];
                    lowestIndex = i;
                }
            }

            return lowestIndex;
        } else {
            // If the column index is invalid or the array is empty, return -1
            return -1;
        }
    }

    public static double getHighestInArray(double[][] data) {
        // Check if the array is not empty
        if (data.length > 0) {
            double highest = Double.MIN_VALUE; // Initialize with the smallest possible value

            // Iterate over the array to find the highest element
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] > highest) {
                        highest = data[i][j];
                    }
                }
            }

            return highest;
        } else {
            // If the array is empty, return NaN
            return Double.NaN;
        }
    }

    public static double getLowestInArray(double[][] data) {
        // Check if the array is not empty
        if (data.length > 0) {
            double lowest = Double.MAX_VALUE; // Initialize with the largest possible value

            // Iterate over the array to find the lowest element
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] < lowest) {
                        lowest = data[i][j];
                    }
                }
            }

            return lowest;
        } else {
            // If the array is empty, return NaN
            return Double.NaN;
        }
    }

    
    
    
    
    
}
