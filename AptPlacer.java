class AptPlacer{

	private static AptMinPQ price;
	private static AptMaxPQ sqFootage;
	private static MinPQDLB cityPrice;
	private static MaxPQDLB citySqFootage;

	public AptPlacer(){
		price = new AptMinPQ(false);
		sqFootage = new AptMaxPQ(false);
		cityPrice = new MinPQDLB();
		citySqFootage = new MaxPQDLB();
	}

	public void insert(Apt apartment){
		price.insert(apartment);
		sqFootage.insert(apartment);

		AptMaxPQ citySpace = citySqFootage.getSpacePQ(apartment.getCity());
		AptMinPQ cityCost = cityPrice.getPricePQ(apartment.getCity());
		
		if(citySpace == null){
			AptMaxPQ newMaxPQ = new AptMaxPQ(true);
			citySqFootage.insert(apartment.getCity(), newMaxPQ);
			citySpace = newMaxPQ;
		}
		if(cityCost== null){
			AptMinPQ newMinPQ = new AptMinPQ(true);
			cityPrice.insert(apartment.getCity(), newMinPQ);
			cityCost = newMinPQ;
		}

		cityCost.insert(apartment);
		citySpace.insert(apartment);
		
	}

	public Apt getLowestPrice(){
		return price.min();
	}

	public Apt getLargestSpace(){
		return sqFootage.max();
	}

	public Apt getCityLowestPrice(String city){
		AptMinPQ prices = cityPrice.getPricePQ(city);
		if(prices == null){
			return null;
		} else {
			return prices.min();
		}

	}

	public Apt getCityLargestSpace(String city){
		AptMaxPQ spaces = citySqFootage.getSpacePQ(city);
		if(spaces == null){
			return null;
		} else {
			return spaces.max();
		}
	}

	public void update(Apt apartment){ //0=Price, 1=Space
		
			int priceIndex = apartment.getPricesIndex();
			int cityPriceIndex = apartment.getCityPriceIndex();
			price.updateValue(priceIndex);
			AptMinPQ cityPrices = cityPrice.getPricePQ(apartment.getCity());
			cityPrices.updateValue(cityPriceIndex);
		} 
	

	public void remove(Apt apartment){
		price.del(apartment.getPricesIndex());
		sqFootage.del(apartment.getSquareFootageIndex());
		
		AptMaxPQ spaces = citySqFootage.getSpacePQ(apartment.getCity());
		AptMinPQ costs = cityPrice.getPricePQ(apartment.getCity());

		if(spaces != null){
			spaces.del(apartment.getCitySquareFootageIndex());
		}

		if(costs != null){
			costs.del(apartment.getCityPriceIndex());
		}
		
	}
}