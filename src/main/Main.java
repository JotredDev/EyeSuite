package main;

public class Main {
	
	public static void main (String[] args) {
		
		System.out.println("hello");
		
		int[] test = new int[] {12, 24, 54, 60, 102, 120};
		TextConverter textConverter = new TextConverter(4);
		System.out.println(textConverter.rawNumberFormat(test, 3));
		
		System.out.println(textConverter.rawNumberFormat(test));
	}
}
