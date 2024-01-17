package main;

public class TextConverter {
	
	// lineSize dictates how many items will be printed per line, i.e. numbers or ascii characters
	private int lineSize;
	
	public TextConverter (int lineSize) {
		this.lineSize = lineSize;
	}
	
	public void setLineSize (int lineSize) {
		this.lineSize = lineSize;
	}
	
	
	/*
	 * This function will return a formatted String of the numbers of the given array with the fixed width numberSize,
	 * breaking each line after lineSize entries of rawNumbers
	 *
	 * If a number is smaller than numberSize, it will be padded out with zeros up to numberSize digits
	 * If a number is greater than numberSize, it will be truncated down to the last numberSize digits
	 */
	public String rawNumberFormat (int[] rawNumbers, int numberSize) {
		
		StringBuilder outputString = new StringBuilder();
		String curNumber;
		int numberFill;
		
		for (int i = 0; i < rawNumbers.length; i++) {
		
			// Save the current number as a String, to easily get how many zeros have to be appended at the front
			curNumber = String.valueOf(rawNumbers[i]);
			numberFill = numberSize - curNumber.length();
			
			// Adding padding zeros as required
			outputString.append("0".repeat(Math.max(0, numberFill)));
			
			// Adding the number to the String, along with a space to visually split up the numbers
			if (numberFill < 0) {
				outputString.append(curNumber.substring(curNumber.length() + numberFill - 1));
			}
			else {
				outputString.append(curNumber);
			}
			outputString.append(" ");
			
			// If there have been lineSize many numbers added to the String, add a line break
			if ((i + 1) % lineSize == 0) {
				
				outputString.append("\n");
			}
		}
		
		// Return the String that was built up in the loop
		return outputString.toString();
	}
	
	/*
	 * This is just a shorthand for printing numbers with fixed width 2.
	 * Useful if you are working with the basic 0-82 eye set
	 */
	public String rawNumberFormat (int[] rawNumbers) {
		return rawNumberFormat(rawNumbers, 2);
	}
	
	
}
