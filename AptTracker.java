//Sriram Iyer
//CS1501 Project 3

import java.util.*;
import java.io.*;

public class AptTracker{

	private static Scanner s = new Scanner(System.in);
	private static AptUI ui = new AptUI();
	private static AptPlacer placer = new AptPlacer();
	private static AptHashTable aptHash = new AptHashTable();



	public static void main(String[] args){
		
		FileInputStream dictStream = new FileInputStream(System.getProperty("user.dir") + "/.txt");
        BufferedReader dictReader= new BufferedReader(new InputStreamReader(dictStream));
        
        File F = new File(System.getProperty("user.dir") + "/user_apartments.txt");
        if(F.isFile()){
            FileInputStream userStream = new FileInputStream(System.getProperty("user.dir") + "/user_apartments.txt");
            BufferedReader uH = new BufferedReader(new InputStreamReader(userStream));
            while ((strLine = uH.readLine()) != null)   {
                String[] values = strLine.split(" ");
                String address = values[0]+" "+values[1]+" "+values[2];
                int number = Integer.toInt(values[3]);
                String city = values[4];
                int zip = Integer.toInt(values[5]);
                double price = Double.toDouble(values[6]);
                int sq_ft = Integer.toInt(values[7]);
                Apt apt = new Apt(address,number, city, zip, price, sq_ft);
                placer.insert(apt);
                aptHash.insert(apt);
            }
            uH.close();
		}
		
		s = new Scanner(System.in);
		while(true){
			ui.displayMenu();
			int option = Integer.parseInt(s.nextLine());
			switch(option){
				case 1:
					add();
					break;
				case 2:
					update();
					break;
				case 3:
					remove();
					break;
				case 4:
					retrieveLow();
					break;
				case 5: 
					retrieveLarge();
					break;
				case 6:
					retrieveLowCity();
					break;
				case 7:
					retrieveLargeCity();
					break;
				case 8:
					aptHash.write();
					System.exit(0);

			}
		}
			
		
	}

	public static void add(){
		ui.displayAdd();
		System.out.println("Please enter an Address (No Apartment Number, City or ZIP please, just Street Address): ");
		String address = s.nextLine();
		System.out.println("Please enter an Apartment Number: ");
		int number = Integer.parseInt(s.nextLine());
		System.out.println("Please enter a City: ");
		String city = s.nextLine();
		System.out.println("Please enter a Zip Code: ");
		int zip = Integer.parseInt(s.nextLine());
		System.out.println("Please enter a price per month (no $): ");
		double price = Double.parseDouble(s.nextLine());
		System.out.println("Please enter a square footage space (only enter the size as an integer, no sq/ft or anything at the end please): ");
		int space = Integer.parseInt(s.nextLine());
		Apt apartment = new Apt(address, number, city, zip, price, space);
		aptHash.insert(apartment);
		placer.insert(apartment);	
	}

	public static void retrieveLow(){
		ui.displayLowRetrieve();
		Apt apartment = placer.getLowestPrice();
		apartment.printInfo();
		System.out.println("This is the lowest priced apartment");
	}

	public static void retrieveLarge(){
		ui.displayLargeRetrieve();
		Apt apartment = placer.getLargestSpace();
		apartment.printInfo();
		System.out.println("This is the largest apartment");
	}

	public static void update(){
		ui.displayUpdate();
		System.out.println("Please enter an Address (No Apartment Number, City or ZIP please, just Street Address): ");
		String address = s.nextLine();
		System.out.println("Please enter an Apartment Number: ");
		int number = Integer.parseInt(s.nextLine());
		System.out.println("Please enter a Zip Code: ");
		int zip = Integer.parseInt(s.nextLine());
		Apt apartment = aptHash.getApt(address, number, zip);
		apartment.printInfo();
		System.out.println("Is this the apartment you want to update? Type Yes or No:");
		String response = s.nextLine().toLowerCase();
		if(response.equals("yes")){
			System.out.println("Please type the new price per month(no $): ");
			double newPrice = Double.parseDouble(s.nextLine());
			aptHash.updateApt(apartment, newPrice);
			placer.update(apartment);
		} else {
			return;
		}
	}

	public static void remove(){
		ui.displayRemove();
		System.out.println("Please enter an Address (No Apartment Number, City or ZIP please, just Street Address): ");
		String address = s.nextLine();
		System.out.println("Please enter an Apartment Number: ");
		int number = Integer.parseInt(s.nextLine());
		System.out.println("Please enter a Zip Code: ");
		int zip = Integer.parseInt(s.nextLine());
		Apt apartment = aptHash.getApt(address, number, zip);
		apartment.printInfo();
		System.out.println("Is this the apartment you want to remove? Type Yes or No:");
		String answer = s.nextLine().toLowerCase();
		if(answer.equals("yes")){
			aptHash.remove(apartment);
			placer.remove(apartment);
		} else {
			return;
		}

	}

	public static void retrieveLowCity(){
		ui.displayLowRetrieveCity();
		System.out.println("Please enter a City");
		String city = s.nextLine();
		Apt apartment = placer.getCityLowestPrice(city);
		apartment.printInfo();
		System.out.println("This is the lowest priced apartment for " + city);
	}

	public static void retrieveLargeCity(){
		ui.displayLargeRetrieveCity();
		System.out.println("Please enter a City");
		String city = s.nextLine();
		Apt apartment = placer.getCityLargestSpace(city);
		apartment.printInfo();
		System.out.println("This is the largest apartment for " + city);
	}

}
