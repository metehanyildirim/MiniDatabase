package data;

import Main.PersonModel;

public class SocialList {
	private static SocialList sorteddata = null;
	
	public final PersonModel[] socialSorted;
	
	public static SocialList getInstance(PersonModel[] people){
		if(sorteddata == null)
			sorteddata = new SocialList(people);
		return sorteddata;
	}
	
	private SocialList(PersonModel[] people){
		socialSorted = people;
	}
}
