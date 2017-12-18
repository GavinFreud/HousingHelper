public class AptUI{

	public void displayMenu(){
		System.out.println("\nWelcome! What would you like to do?");
		System.out.println("1) Add an Apartment");
		System.out.println("2) Update the price of an Apartment");
		System.out.println("3) Remove an Apartment from Consideration");
		System.out.println("4) Retrieve the lowest Priced Apartment");
		System.out.println("5) Retrieve the largest Apartment");
		System.out.println("6) Retrieve the lowest priced Apartment by City");
		System.out.println("7) Retrive the largest Apartment by City");
		System.out.println("8) Exit the Program");
	}

	public void displayAdd(){
		System.out.println("\n---Adding an Apartment---");
	}

	public void displayUpdate(){
		System.out.println("\n---Updating an Apartment---");
	}

	public void displayRemove(){
		System.out.println("\n---Removing an Apartment---");
	}

	public void displayLowRetrieve(){
		System.out.println("\n---Retrieving Lowest Price---");
	}

	public void displayLowRetrieveCity(){
		System.out.println("\n---Retrieving Lowest Price by City---");
	}

	public void displayLargeRetrieve(){
		System.out.println("\n---Retrieving Largest Space---");
	}

	public void displayLargeRetrieveCity(){
		System.out.println("\n---Retrieving Largest Space By City---");
	}

}