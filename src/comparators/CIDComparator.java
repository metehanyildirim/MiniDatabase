package comparators;

import Main.PersonModel;
import java.util.Comparator;

public class CIDComparator implements Comparator<PersonModel> {
	
	@Override
	public int compare(PersonModel fperson, PersonModel sperson){
		if(fperson.getcID() == sperson.getcID()){
			return 0;
		} else if(fperson.getcID() < sperson.getcID()){
			return -1;
		} else {
			return 1;
		}
	}
}
