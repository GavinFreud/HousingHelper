public class AptHashTable{

	private Apt[] apartments;

	public AptHashTable(){
		apartments = new Apt[997]; //large prime number
	}

	public void insert(Apt apartment){
		int hash = hash(apartment.getAddress(), apartment.getApt_number(), apartment.getZip());
		apartments[hash] = apartment;
	}

	public void remove(Apt apartment){
		int hash = hash(apartment.getAddress(), apartment.getApt_number(), apartment.getZip());
		apartments[hash] = null;
	}

	public Apt getApt(String address, int number, int zip){
		int hashInt = hash(address, number, zip);

		return apartments[hashInt];
	}

	public void updateApt(Apt apartment, double newPrice){
		int index = hash(apartment.getAddress(), apartment.getApt_number(), apartment.getZip());
		Apt updatedPrice = apartments[index];
		updatedPrice.setPrice(newPrice);
		apartments[index] = updatedPrice;
	}




	public int hash(String address, int number, int zip){
		String hash = address;
		hash = hash.concat(Integer.toString(number));
		hash = hash.concat(Integer.toString(zip));

		

		long hashValue = 0;

		for(int x = 0; x < hash.length(); x++){
			int asciiVal = (int) hash.charAt(x);
			hashValue *= 256;
			hashValue += asciiVal;
		}

		int hashInt = (int)(hashValue % 997);
		
		return hashInt;
	}

	public void write(){
		PrintWriter writer = new PrintWriter(new FileOutputStream("user_history.txt", false));
        for(int x = 0; x<apartments.size(); x++){
            if(apartments[x] != null){
            	String line;
            	Apt apt = apartments[x];
            	line = apt.getAddress() + " " + apt.getApt_number() + " " + apt.getCity()+ " " +apt.getZip()+ " " +apt.getPrice()+ " " +apt.getSq_footage();
            	writer.println(line);
            }
        }
        writer.close();
	}





}