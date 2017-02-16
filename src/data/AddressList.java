package data;

import Main.PersonModel;

public class AddressList {

	private static AddressList sorteddata = null;
	
	public final PersonModel[] addressSorted;
	
	public static AddressList getInstance(PersonModel[] people){
		if(sorteddata == null)
			sorteddata = new AddressList(people);
		return sorteddata;
	}
	
	private AddressList(PersonModel[] people){
		addressSorted = people;
	}
}
