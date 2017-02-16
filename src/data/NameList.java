package data;

import Main.PersonModel;

public class NameList {
	private static NameList sorteddata = null;
	
	public final PersonModel[] nameSorted;
	
	public static NameList getInstance(PersonModel[] people){
		if(sorteddata == null)
			sorteddata = new NameList(people);
		return sorteddata;
	}
	
	private NameList(PersonModel[] people){
		nameSorted = people;
	}
}
