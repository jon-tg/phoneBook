package Exercise2; 
import Exercise1.PhoneBookEntry;

import java.util.Scanner;

public class PhoneBookDirectory {
    
    private PhoneBookEntry[] entries;
    static Scanner input = new Scanner(System.in);

    public PhoneBookDirectory() {
        entries = new PhoneBookEntry[6];
        for (int i = 0; i < entries.length; i++) {
            entries[i] = new PhoneBookEntry();
        }
    }

    public int lowestEmptyIndex() {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i].getZipCode() == 0 && entries[i].getPhoneNumber() == 0)
                return i;
        }
        return -1; 
    }
    
    public int addEntry(PhoneBookEntry entry) {
        int lowestIndex = lowestEmptyIndex();
        
        if (lowestIndex == -1) {
            return 0;
        }
        
        entries[lowestIndex] = entry;
        return 1;
    }
    
    
    public PhoneBookEntry SearchbyIdBinarySearch(int id) {
        BubbleSortById();
        int low = 0;
        int high = entries.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midId = entries[mid].getId();
            if (midId == id && entries[mid].getZipCode() != 0 && entries[mid].getPhoneNumber() != 0) {
                entries[mid].printBookEntry();
                return entries[mid];
            } else if (midId < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new PhoneBookEntry();
    }
    
    public int LinearSearchByPhoneNumber(String PhoneNumber) {
        int phoneNumber = Integer.parseInt(PhoneNumber); 
        boolean found=false;
        for (int i=0; i<entries.length; i++) {
            if (entries[i].getPhoneNumber()==phoneNumber && entries[i].getZipCode() != 0 && entries[i].getPhoneNumber() != 0) {
                entries[i].printBookEntry();
                found=true;
                return 1;
            }
        }
        if (!found) {
            return 0;
        }
        return 0;
    }
    
    public void BubbleSortById() {
        for (int i = 0; i < entries.length - i - 1; i++) {
            for (int j = 0; j < entries.length - i - 1; j++) {
                if (entries[j].getId() > entries[j + 1].getId()) {
                    PhoneBookEntry temp = entries[j];
                    entries[j] = entries[j + 1];
                    entries[j + 1] = temp;
                }
            }
        }
        
        for (int i=0; i<entries.length; i++)
        {
        	if (entries[i].getZipCode()!=0 && entries[i].getPhoneNumber()!=0 && entries[i].getId()!=-1)
        	entries[i].printBookEntry();
        }
    }
    
    public int Edit(String firstName, String lastName) {
        boolean found=false;
        for (int i=0; i<entries.length; i++) {
            if (entries[i].getFname().equalsIgnoreCase(firstName) && entries[i].getLname().equalsIgnoreCase(lastName)) {
            	System.out.print("Enter an updated first name: ");
                String Fname = input.next();
                System.out.print("Enter an updated last name: ");
                String Lname = input.next();
                System.out.print("Enter an updated phone number: ");
                int PhoneNumber = input.nextInt();
                System.out.print("Enter an updated email: ");
                String email = input.next();
                System.out.print("Enter an updated zipcode: ");
                int zipCode = input.nextInt();
                System.out.print("Enter an updated ID: ");
                int Id = input.nextInt();
                entries[i] = new PhoneBookEntry(Id, Fname, Lname, email, zipCode, PhoneNumber);
                found=true;
                
            }
        }
        if (!found) {
            return 0;
        }
        return 1;
    }
    
    public int DeleteEntry(int ID) {
        boolean found=false;
        for (int i=0; i<entries.length; i++) {
            if (entries[i].getId()==ID) {
                if (entries[i].getZipCode() != 0 && entries[i].getPhoneNumber() != 0) {
                    entries[i] = new PhoneBookEntry();    
                    found=true;
                }
            }
        }
        if (!found) {
            return 1;
        }
        return 0;
    }
    
    public int DeleteEntry(String firstname, String lastname) {
        boolean found=false;
        for (int i=0; i<entries.length; i++) {
            if (entries[i].getFname().equalsIgnoreCase(firstname) && entries[i].getLname().equalsIgnoreCase(lastname)) {
                if (entries[i].getZipCode() != 0 && entries[i].getPhoneNumber() != 0) {
                    entries[i] = new PhoneBookEntry();   
                    found=true;
                }
            }
        }
        if (!found) {
            return 1;
        }
        return 0;
    }

    
}

