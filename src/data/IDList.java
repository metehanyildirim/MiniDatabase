package data;
import Main.*;

public class IDList {
	
	private static IDList sorteddata = null;
	
	public final PersonModel[] idSorted;
	
	public static IDList getInstance(PersonModel[] people){
		if(sorteddata == null)
			sorteddata = new IDList(people);
		return sorteddata;
	}
	
	private IDList(PersonModel[] people){
		idSorted = people;
	}
}
