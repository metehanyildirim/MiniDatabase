package data;

import Main.PersonModel;

public class CityList {
	private static CityList sorteddata = null;
	
	public final PersonModel[] citySorted;
	
	public static CityList getInstance(PersonModel[] people){
		if(sorteddata == null)
			sorteddata = new CityList(people);
		return sorteddata;
	}
	
	private CityList(PersonModel[] people){
		citySorted = people;
	}
}
