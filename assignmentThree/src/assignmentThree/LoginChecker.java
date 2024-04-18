package assignmentThree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing a user name and password checker.
 * Otherwise known as a login checker.
 * @author Kadynce DeFrancesco
 * @version 1.0
 */
public class LoginChecker {
	
/**
 * This method reads the user names and passwords from the file "confidentialInfo.txt"
 * file and stores them in two ArrayLists. It takes 
 * two ArrayLists as parameters and returns the last 
 * username read from the file as a String
 * 
 * @param UserNames  an ArrayList of strings that stores user names
 * @param Passwords  an ArrayList of strings that stores passwords
 * @return  a string representing the last username read from the file
 * @throws IOException  if there is an error reading from the file
 */
	//Replace Temp with Query
	private static boolean containsSpecialCharacter(String password) {
	    return password.contains("*") || password.contains("_") || password.contains("$");
	}

	//Extraction method to validate the username
	public static boolean validateUsername(String userName) {
		return userName.length() >= 6 && userName.length() <= 8;
	}
	
	//Extraction method to validate the password
	public static boolean validatePassword(String password) {
		boolean containsSpecialChar = containsSpecialCharacter(password);
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		
		for(int i =0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				hasUpperCase = true;
			}
			if(Character.isLowerCase(password.charAt(i))) {
				hasLowerCase = true;
			}
		}
		return password.length() >= 8 && containsSpecialChar && hasUpperCase && hasLowerCase;
	}
	
	
	public static String readingInfo(ArrayList<String> UserNames,
			 ArrayList<String> Passwords)
			 throws IOException{
		
		FileInputStream fileByteStream = null;
		Scanner inFS = null;
		String UserName = null;
		String passWord;
		
		// opens the file "confidentialInfo.txt" for reading
		fileByteStream = new FileInputStream("confidentialInfo.txt");
	
	inFS = new Scanner(fileByteStream);
	
	// reads the number of username/password combinations from the file
	int numberOfcombos = inFS.nextInt();
	
	// reads each number of username/password combination from the file and adds them to the arrays
	for(int i = 0; i < numberOfcombos; ++i) {

		UserName = inFS.next();
		passWord = inFS.next();
	
		UserNames.add(UserName);
		Passwords.add(passWord);
		}
	
	// closes the file and the scanner
	fileByteStream.close();
	inFS.close();
	
	return UserName;
	}

	/**
	 * This method is the main entry point for the program. It prompts the
	 * user to enter their username and password, validates the input, and
	 * writes the new user name and password to the file "confidentialInfo.txt" file.
	 * 
	 * @param args  an array of strings representing command line arguments
	 * @throws IOException if there is an error writing to the file
	 */

	public static void main(String[] args) throws IOException {
		

	Scanner s = new Scanner(System.in);
	FileInputStream fileByteStream = null;
		
		String password;	// a string representing the password
		String userName;	// a string representing the username
		String currWord;
		ArrayList<String> userNames = new ArrayList<String>();	// an arraylist that stores usernames
		ArrayList<String> passwords = new ArrayList<String>();	// an arraylist that stores passwords
		int numberOfcombos;	// an integer representing the number of username/password combinations in the file
		boolean valid = false;	// a boolean value indicating whether or not the password and user name entered by the user are valid
		boolean usernamevalid = false;	// a boolean value indicating whether or not the user name entered by the user is valid
		int i = 0;	// an integer used as a loop counter
		
		
		do {
			System.out.print("Enter your username: ");
			userName = s.nextLine();
		}while (!validateUsername(userName));
		
		do {
			System.out.print("Enter your password: ");
			password = s.nextLine();
		}while (!validatePassword(password));
		
		
		FileOutputStream fileStream = null;
		PrintWriter outFS = null;
		
	fileStream = new FileOutputStream("confidentialInfo.txt");
	outFS = new PrintWriter(fileStream);
	
	outFS.println("3");
	outFS.println("Unicorns");
	outFS.println("Unicorns****");
	outFS.println("LikeaMi");
	outFS.println("RemiWolfe**");
	outFS.println("NaNaNa");
	outFS.println("AnthonyKiedis*");
	outFS.close();

		

	

	ArrayList<String> UserNames = new ArrayList<String>();
	ArrayList<String> Passwords = new ArrayList<String>();
	String comboList;
	
	
	comboList = readingInfo(userNames,passwords);
	fileStream.close();

	fileByteStream = new FileInputStream("confidentialInfo.txt");	//a 'FileInputStream' object used to read data from the "confidentialInfo.txt" file
	Scanner inFS = new Scanner(fileByteStream);	//a 'Scanner' object used to read data from the 'fileByteStream'
	
	boolean filecontainsUserName = true;
	boolean filecontainsPassword = true;

	do {
			
  String currWord1 = inFS.next();
  

if(currWord1.equals(userName)) {
		System.out.println("Username is in the system.");
		filecontainsUserName = true;
  }
else {
	filecontainsUserName  = false;
}
			
if(currWord1.equals(password)) {
			  System.out.println("Password is in the system.");
			  filecontainsPassword = true;

}
else {
	 filecontainsPassword = false;
}
	} while(inFS.hasNext());
	
  
/**
 * Closes the file confidentialInfo.txt
 * Closes the inFS scanner
 * @throws IOException if the file stream or scanner could not be closed
 */
	fileByteStream.close();
	inFS.close();
}
	}









