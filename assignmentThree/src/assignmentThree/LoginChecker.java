package assignmentThree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginChecker {

    private static final int NUMBER_OF_COMBOS = 3;

    public static void readPasswordandUsernameFromFile(ArrayList<String> UserNames,
                                                        ArrayList<String> Passwords)
            throws IOException {

        FileInputStream fileByteStream = null;
        Scanner inFS = null;

        try {
            fileByteStream = new FileInputStream("confidentialInfo.txt");
            inFS = new Scanner(fileByteStream);

            int numberOfCombos = inFS.nextInt();

            for (int i = 0; i < numberOfCombos; ++i) {
                String userName = inFS.next();
                String passWord = inFS.next();
                UserNames.add(userName);
                Passwords.add(passWord);
            }
        } finally {
            if (fileByteStream != null) {
                fileByteStream.close();
            }
            if (inFS != null) {
                inFS.close();
            }
        }
    }

    private static class PasswordValidator {
        public static boolean isValid(String password) {
            boolean containsSpecialChar = containsSpecialCharacter(password);
            boolean hasUpperCase = false;
            boolean hasLowerCase = false;

            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    hasUpperCase = true;
                }
                if (Character.isLowerCase(password.charAt(i))) {
                    hasLowerCase = true;
                }
            }
            return password.length() >= 8 && containsSpecialChar && hasUpperCase && hasLowerCase;
        }

        private static boolean containsSpecialCharacter(String password) {
            return password.contains("*") || password.contains("_") || password.contains("$");
        }
    }

    public static boolean validateUsername(String userName) {
        return userName.length() >= 6 && userName.length() <= 8;
    }

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);

        ArrayList<String> userNames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        String userName;
        do {
            System.out.print("Enter your username: ");
            userName = s.nextLine();
        } while (!validateUsername(userName));

        String password;
        do {
            System.out.print("Enter your password: ");
            password = s.nextLine();
        } while (!PasswordValidator.isValid(password));

        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        try {
            fileStream = new FileOutputStream("confidentialInfo.txt");
            outFS = new PrintWriter(fileStream);

            outFS.println(NUMBER_OF_COMBOS);
            outFS.println("Unicorns");
            outFS.println("Unicorns****");
            outFS.println("LikeaMi");
            outFS.println("RemiWolfe**");
            outFS.println("NaNaNa");
            outFS.println("AnthonyKiedis*");
        } finally {
            if (outFS != null) {
                outFS.close();
            }
            if (fileStream != null) {
                fileStream.close();
            }
        }

        readPasswordandUsernameFromFile(userNames, passwords);

        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        try {
            fileByteStream = new FileInputStream("confidentialInfo.txt");
            inFS = new Scanner(fileByteStream);

            boolean fileContainsUserName = false;
            boolean fileContainsPassword = false;

            // Skip the number of combos
            inFS.nextInt();
            inFS.nextLine();

            while (inFS.hasNext()) {
                String currWord = inFS.next();

                if (currWord.equals(userName)) {
                    System.out.println("Username is in the system.");
                    fileContainsUserName = true;
                }

                if (currWord.equals(password)) {
                    System.out.println("Password is in the system.");
                    fileContainsPassword = true;
                }
            }
        } finally {
            if (inFS != null) {
                inFS.close();
            }
            if (fileByteStream != null) {
                fileByteStream.close();
            }
        }
    }
}
