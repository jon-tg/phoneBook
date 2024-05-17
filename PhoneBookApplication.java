package Exercise2;
import java.io.*;

import java.util.Scanner;

public class PhoneBookApplication {
	
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args){

		PhoneBookAdmin admin = createAdminFromInputFile("src/Exercise2/admin_data.txt");
		NormalUser user = createNormalUserFromInputFile("src/Exercise2/user_data.txt");
		int login=login();

		while (login!=0 && login!=1)
		{
			login=login();
		}

		if (login==1)
		{
			admin.PrintUserInfo();
			System.out.println("Welcome, admin.");
			handleAdmin(admin);

		}
		else if (login==0)
		{
			user.PrintUserInfo();
			System.out.println("Welcome, user.");
			handleUser(user);
		}
		
		input.close();

	}

	public static void handleUser(NormalUser user)
	{
		int option;

		do
		{
			option=userMenu();

			if (option==1)
			user.addContact();

			else if (option==2)
			user.editContact();
			
			else if (option==3)
			user.Sort();
			
			else if (option==4)
			user.linearSearch();
			
			else if (option==5)
			user.PrintUserInfo();
			
			else if (option==6)
			System.out.print("Goodbye!");	
			
			else System.out.println("Please select a valid option");
			
		} while (option!=6);
		
	}

	public static void handleAdmin(PhoneBookAdmin admin)
	{
		int option;
		
		do
		{
			option=adminMenu();

			if (option==1)
			admin.addContact();

			else if (option==2)
			admin.editContact();
			
			else if (option==3)
			admin.deleteContact();
		
			else if (option==4)
			admin.Sort();
			
			else if (option==5)
			admin.linearSearch();
			
			else if (option==6)
			admin.binarySearch();
			
			else if (option==7)
			admin.PrintUserInfo();
			
			else if (option==8)
			{
				System.out.println("What would you like to change your username to?: ");
				admin.changeUsername(input.next());
			}
			else if (option==9)
			{
				System.out.println("What would you like to change your password to?: ");
				admin.changePassword(input.next());
			}
			
			else if (option==10)
			System.out.print("Goodbye!");
			
			else System.out.println("Please select a valid option");
			
			
		} while (option!=10);

	}

	public static PhoneBookAdmin createAdminFromInputFile(String filename) {
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			fileScanner.useDelimiter(",\\s*");
			String username = fileScanner.next();
			String password = fileScanner.next();
			String email = fileScanner.next();
			fileScanner.close();
			return new PhoneBookAdmin(username, password, email);
		} catch (FileNotFoundException e) {
			return null; 
		}
	}



	public static NormalUser createNormalUserFromInputFile(String filename) {
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			fileScanner.useDelimiter(",\\s*");
			int id=fileScanner.nextInt();
			String username = fileScanner.next();
			String password = fileScanner.next();
			fileScanner.close();
			return new NormalUser(id, username, password);
		} catch (FileNotFoundException e) {
			return null; 
		}
	}

	public static int login()
	{
		System.out.print("Enter '1' for admin login, '0' for regular user: ");
		int login=input.nextInt();
		return login;
	}

	public static int userMenu()
	{
		System.out.println("----------------------");
		System.out.println("Options ");
		System.out.println("1. Add a phone entry ");
		System.out.println("2. Edit a phone entry ");
		System.out.println("3. Sort the Phone Book Directory ");
		System.out.println("4. Search Phone Book using Linear Search ");
		System.out.println("5. Print User's Info ");
		System.out.println("6. Exit ");
		System.out.println("----------------------");


		int options=input.nextInt();
		return options;

	}
	
	public static int adminMenu()
	{
		System.out.println("----------------------");
		System.out.println("Options: ");
		System.out.println("1. Add a phone entry ");
		System.out.println("2. Edit a phone entry ");
		System.out.println("3. Delete a phone entry ");
		System.out.println("4. Sort the Phone Book Directory ");
		System.out.println("5. Search Phone Book using Linear Search ");
		System.out.println("6. Search Phone Book using Binary Search ");
		System.out.println("7. Print Admin's Info ");
		System.out.println("8. Change Username ");
		System.out.println("9. Change Password ");
		System.out.println("10. Exit ");
		System.out.println("----------------------");
		int options=input.nextInt();
		return options;

	}
	
}