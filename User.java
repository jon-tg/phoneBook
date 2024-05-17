package Exercise2;

import java.util.Scanner;
import java.io.*;
import Exercise1.PhoneBookEntry;

public class User {
	
	protected String username;
	protected String password;
	protected PhoneBookDirectory entries;
	static Scanner input= new Scanner(System.in);
	
	public User()
	{
		username="";
		password="";
		entries=new PhoneBookDirectory();
	}
	
	public User(String username, String password)
	{
		this.username=username;
		this.password=password;
		entries=new PhoneBookDirectory();
	}
	
	

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public PhoneBookDirectory getEntries() {
		return entries;
	}
	public void setEntries(PhoneBookDirectory entries) {
		this.entries = entries;
	}
	
	public void PrintUserInfo()
	{
		System.out.println("-------------------");
		System.out.println("Username: " + this.username);
		System.out.println("Password: " + this.password);
		System.out.println("-------------------");

		
	}
	
	public void addContact()
	{
		System.out.print("Enter your first name: ");
        String Fname = input.next();
        System.out.print("Enter your last name: ");
        String Lname = input.next();
        System.out.print("Enter your phone number: ");
        int PhoneNumber = input.nextInt();
        System.out.print("Enter your email: ");
        String email = input.next();
        System.out.print("Enter your zipcode: ");
        int zipCode = input.nextInt();
        System.out.print("Enter an ID: ");
        int Id = input.nextInt();
        PhoneBookEntry contact=new PhoneBookEntry(Id, Fname, Lname, email, zipCode, PhoneNumber);
        int added=entries.addEntry(contact);
        
        if (added==1)
        {
        	System.out.println("Successfully added entry.");
        	
        }
        else if (added==0)
        {
            System.out.println("Phone book is full. Cannot add more contacts");

        }
        
	}
	
	public void editContact()
	{
		System.out.print("Enter a first name: ");
        String Fname = input.next();
        System.out.print("Enter a last name: ");
        String Lname = input.next();
        int edited=entries.Edit(Fname, Lname);
        
        if (edited==1)
        System.out.println("Successfully edited");
        
        else 
        System.out.println("No entry with matching information exists");

        
		
	}
	
	public void Sort()
	{
		entries.BubbleSortById();
        System.out.println("Successfully sorted");

	}
	
	public void linearSearch()
	{
		System.out.print("Enter the phone number you would like to search for: ");
        String phoneNumber = input.next();
		
		int searched=entries.LinearSearchByPhoneNumber(phoneNumber);
		
		if (searched==1)
		System.out.println("Successfully located entry");
		
		else if (searched==0)
        System.out.println("No entry with matching information exists");

	}

}

class NormalUser extends User implements NormalUserInterface {

	private int id;
	
	public NormalUser()
	{
		super();
		this.id=0;
		
	}
	
	public NormalUser(int id, String username, String password)
	{
		super(username, password);	
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void PrintUserInfo()
	{
		System.out.println("-------------------");
		System.out.println("Username: " + this.username);
		System.out.println("Password: " + this.password);
		System.out.println("ID: " + this.id);
		System.out.println("-------------------");


	}
	
	
}

class PhoneBookAdmin extends User implements AdminUserInterface {
	
	private String email;

	public PhoneBookAdmin()
	{
		super();	
		email="";
	}
	
	public PhoneBookAdmin(String username, String password, String email)
	{
		super(username, password);	
		this.email=email;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void changeUsername(String username) {
        String filename = "src/Exercise2/admin_data.txt";
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            fileScanner.useDelimiter(",\\s*");
            fileScanner.next();
            String oldPassword = fileScanner.next();
            String oldEmail = fileScanner.next();
            fileScanner.close();

            this.username = username;

            FileWriter fileWriter = new FileWriter(new File(filename));
            fileWriter.write(username + ", " + oldPassword + ", " + oldEmail);
            fileWriter.close();
            System.out.println("Successfully changed username");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    public void changePassword(String password) {
        String filename = "src/Exercise2/admin_data.txt";
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            fileScanner.useDelimiter(",\\s*");
            String oldUsername = fileScanner.next();
            fileScanner.next();
            String oldEmail = fileScanner.next();
            fileScanner.close();

            this.password = password;

            FileWriter fileWriter = new FileWriter(new File(filename));
            fileWriter.write(oldUsername + ", " + password + ", " + oldEmail);
            fileWriter.close();
            System.out.println("Successfully changed password");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void deleteContact()
	{
		System.out.print("Enter your first name: ");
        String Fname = input.next();
        System.out.print("Enter your last name: ");
        String Lname = input.next();
        int deleted=entries.DeleteEntry(Fname, Lname);
        if (deleted==0)
        System.out.println("Successfully deleted entry");

        
        else if (deleted==1)
        System.out.println("No entry with matching information exists");
      
       
        
	}
	
	public void binarySearch()
	{
		System.out.print("Enter the ID number you would like to search for: ");
        int ID = input.nextInt();
		
		PhoneBookEntry matchingID=entries.SearchbyIdBinarySearch(ID);
		if (matchingID.getPhoneNumber()==0 && matchingID.getZipCode()==0 && matchingID.getId()==-1)
		{
			System.out.println("No entry with matching information exists");
		}
		
		else
		{
			System.out.println("Successfully located entry");
			matchingID.printBookEntry();
		}
		
		
	}
	
	public void PrintUserInfo()
	{
		System.out.println("-------------------");
		System.out.println("Username: " + this.username);
		System.out.println("Password: " + this.password);
		System.out.println("Email: " + this.email);
		System.out.println("-------------------");


	}
	
	
}

