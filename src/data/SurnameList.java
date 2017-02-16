package data;

import Main.PersonModel;

public class SurnameList {
	private static SurnameList sorteddata = null;
	
	public final PersonModel[] surnameSorted;
	
	public static SurnameList getInstance(PersonModel[] people){
		if(sorteddata == null)
			sorteddata = new SurnameList(people);
		return sorteddata;
	}
	
	private SurnameList(PersonModel[] people){
		surnameSorted = people;
	}
}
