package assignmentThree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginChecker {
	
	private static final int NUMBER_OF_COMBOS = 3;
	
	public static void readPasswordandUsernameFromFile(ArrayList<String> UserNames,
			 ArrayList<String> Passwords)
			 throws IOException{
		
		FileInputStream fileByteStream = null;
		Scanner inFS = null;
		String UserName = null;
		String passWord;
		
		
		fileByteStream = new FileInputStream("confidentialInfo.txt");
	
	inFS = new Scanner(fileByteStream);
	

	int numberOfcombos = inFS.nextInt();

	for(int i = 0; i < numberOfcombos; ++i) {

		UserName = inFS.next();
		passWord = inFS.next();
	
		UserNames.add(UserName);
		Passwords.add(passWord);
		}
	
	fileByteStream.close();
	inFS.close();
	
	}
	
	private static boolean containsSpecialCharacter(String password) {
	    return password.contains("*") || password.contains("_") || password.contains("$");
	}

	public static boolean validateUsername(String userName) {
		return userName.length() >= 6 && userName.length() <= 8;
	}
	
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
	

	public static void main(String[] args) throws IOException {
		

	Scanner s = new Scanner(System.in);
	FileInputStream fileByteStream = null;
		
		String password;	// a string representing the password
		String userName;	// a string representing the username
		ArrayList<String> userNames = new ArrayList<String>();	// an arraylist that stores usernames
		ArrayList<String> passwords = new ArrayList<String>();	// an arraylist that stores passwords
	
		
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
	
	outFS.println(NUMBER_OF_COMBOS);
	outFS.println("3");
	outFS.println("Unicorns");
	outFS.println("Unicorns****");
	outFS.println("LikeaMi");
	outFS.println("RemiWolfe**");
	outFS.println("NaNaNa");
	outFS.println("AnthonyKiedis*");
	outFS.close();

	
	readPasswordandUsernameFromFile(userNames,passwords);

	fileByteStream = new FileInputStream("confidentialInfo.txt");	//a 'FileInputStream' object used to read data from the "confidentialInfo.txt" file
	Scanner inFS = new Scanner(fileByteStream);	//a 'Scanner' object used to read data from the 'fileByteStream'
	
	boolean filecontainsUserName = false;
	boolean filecontainsPassword = false;

			
  String currWord1 = inFS.next();
  

while(inFS.hasNext()) {
	if(currWord1.equals(userName)) {
		System.out.println("Username is in the system.");
		filecontainsUserName = true;
  }
	if(currWord1.equals(password)) {
		System.out.println("Password is in the system.");
		filecontainsPassword = true;
	}
			

}
	
	fileByteStream.close();
	inFS.close();
}
	}









