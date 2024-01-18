package main;

public class TrigramHandler {
	
	public TrigramHandler () {
	
	}
	
	/*
	 * This function returns the value of the given int[], interpreted as a base5 number
	 */
	public int standardTrigramDecode (int[] trigram) {
		
		return 25 * trigram[0] + 5 * trigram[1] + trigram[2];
	}
	
	/*
	 * This function returns an array of the values of all trigrams in the given int[][], interpreted as a base5 number
	 */
	public int[] standardTrigramDecode (int[][] trigrams) {
		
		int[] base5 = new int[trigrams.length];
		
		for (int i = 0; i < trigrams.length; i++) {
			
			base5[i] = standardTrigramDecode(trigrams[i]);
		}
		
		return base5;
	}
	
	/*
	 * This function returns the trigram interpreted as the base5 of the given int
	 */
	public int[] standardTrigramEncode (int base5) {
		
		int[] trigram = new int[3];
		
		trigram[2] = base5 % 5;
		trigram[1] = ((base5 - trigram[2]) % 25) / 5;
		trigram[0] = (base5 - trigram[2] - trigram[1]) / 25;
		
		return trigram;
	}
	
	/*
	 * This function returns an array of the trigrams interpreted as the base5 of the integers in the given int[]
	 */
	public int[][] standardTrigramEncode (int[] base5) {
		
		int[][] trigrams = new int[base5.length][];
		
		for (int i = 0; i < base5.length; i++) {
			
			trigrams[i] = standardTrigramEncode(base5[i]);
		}
		
		return trigrams;
	}
	
	/*
	 * This function returns a trigrams base5 form as normal base10.
	 * Only useful for converting to text
	 */
	public int flattenTrigram (int[] trigram) {
		
		return 100 * trigram[0] + 10 * trigram[1] + trigram[2];
	}
	
	/*
	 * This function returns a trigrams base5 form as normal base10.
	 * Only useful for converting to text
	 */
	public int[] flattenTrigrams (int[][] trigrams) {
		
		int[] flattenedTrigrams = new int[trigrams.length];
		
		for (int i = 0; i < trigrams.length; i++) {
			
			flattenedTrigrams[i] = flattenTrigram(trigrams[i]);
		}
		
		return flattenedTrigrams;
	}
}
