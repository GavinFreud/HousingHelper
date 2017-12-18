public class Apt{
	private String address = "";
	private int apt_number = 0;
	private String city = "";
	private int zip = 0;
	private double price = 0.00;
	private int sq_footage = 0; 
	private int PricesIndex = -1; 
    private int SquareFootageIndex = -1; 
    private int CityPriceIndex = -1; 
    private int CitySquareFootageIndex = -1; 



	public Apt(String address_string, int number, String city_loc, int zipCode, double house_price, int square_ft ){
		address = address_string;
		apt_number = number;
		city = city_loc;
		zip = zipCode;
		price = house_price;
		sq_footage = square_ft;
	}

	public String getAddress(){
		return address;
	}

	public int getApt_number(){
		return apt_number;
	}

	public String getCity(){
		return city;
	}

	public int getZip(){
		return zip;
	}

	public double getPrice(){
		return price;
	}

	public int getSq_footage(){
		return sq_footage;
	}

	public int getPricesIndex(){
		return PricesIndex;
	}

	public int getSquareFootageIndex(){
		return SquareFootageIndex;
	}

	public int getCityPriceIndex(){
		return CityPriceIndex;
	}

	public int getCitySquareFootageIndex(){
		return CitySquareFootageIndex;
	}

	public void setAddress(String newAddress){
		address = newAddress;
	}

	public void setApt_number(int newAptNumber){
		apt_number = newAptNumber;
	}

	public void setCity(String newCity){
		city = newCity;
	}

	public void setZip(int newZip){
		zip = newZip;
	}

	public void setPrice(double newPrice){
		price = newPrice;
	}

	public void setSq_footage(int newSqFt){
		sq_footage = newSqFt;
	}

	public void setPricesIndex(int index){
		PricesIndex = index;
	}

	public void setSquareFootageIndex(int index){
		SquareFootageIndex = index;
	}

	public void setCityPriceIndex(int index){
		CityPriceIndex = index;
	}

	public void setCitySquareFootageIndex(int index){
		CitySquareFootageIndex = index;
	}

	public void printInfo(){
		System.out.println("\nApartment Info:");
		System.out.println("Address: " + address);
		System.out.println("Apartment Number: " + apt_number);
		System.out.println("City: " + city);
		System.out.println("Zip Code: " + zip);
		System.out.println("Price: $" + price);
		System.out.println("Size: " + sq_footage + " ft squared");
	}


}