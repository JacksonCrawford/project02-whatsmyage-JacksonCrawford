package project02;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class WhatsMyAge {
	private static Properties reader = new Properties();
	private static ArrayList<String> list = new ArrayList<String>();
	
	// Method to read files and calculate likely age based on name 
	public static int calculate(String filename, String stateCode, String target) {
		try {
			// Read directory from properties file
			InputStream inStream = new FileInputStream(new File(filename));
			reader.load(inStream);
			String directory = reader.getProperty("Directory");
			
			// Open file of argued state
			File stateFile = new File(directory + stateCode + ".TXT");
			Scanner infile = new Scanner(stateFile);
			
			infile.useDelimiter("\n");
			
			String line;
			String[] splitData;
			String name;
			int highest = 0;
			int occurences;
			
			// Read the file and put relevant values in an ArrayList
			while(infile.hasNext()) {
				line = infile.nextLine();
				splitData = line.split(",");
				name = splitData[3].trim();
				occurences = Integer.parseInt(splitData[4].trim());
				// Add to ArrayList if the name is the argued target
				if(name.trim().toLowerCase().equals(target.trim().toLowerCase())) {
					list.add(line);
					if(occurences > highest) {
						highest = occurences;
					}
				}
				try {
				} catch(Exception err) {
					err.printStackTrace();
				}
			}
			infile.close();
			return list.getLikelyYear();
			
		} catch(FileNotFoundException err) {
			err.printStackTrace();
		} catch(IOException err) {
			err.printStackTrace();
		}
		return -1;
	}
	
	// Method to calculate age
	public static int calculateAge(int currYear, int birthYear) {
		int age = currYear - birthYear;
		if(age == currYear) { age = 0; }
		return age;
	}
	
	// Main method
	public static void main(String[] args) {
		// Initialize scanner to get user input
		Scanner input = new Scanner(System.in);
		
		// Get name
		System.out.println("Name of the person (or EXIT to quit): ");
		String name = input.nextLine();
		if(name.equals("EXIT")) { System.exit(0); }
		
		// Get gender
		System.out.println("Gender (M/F): "); String gender = input.nextLine();
		while(!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")) {
			System.out.println("Please enter a valid gender (M/F): ");
			gender = input.nextLine();
		}
		
		// get state
		System.out.println("State of birth (two-letter state code): ");
		String stateCode = input.next().toUpperCase();
		
		// Print result! :)
		System.out.println(name + ", born in " + stateCode + " is most likely " + 
				calculateAge(2021, calculate("properties.txt", stateCode, name)));
		
		input.close();
	}

}
