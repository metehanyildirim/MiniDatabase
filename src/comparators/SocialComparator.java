package comparators;

import Main.PersonModel;
import java.util.Comparator;

public class SocialComparator implements Comparator<PersonModel> {
	
	@Override
	public int compare(PersonModel fperson, PersonModel sperson){
		if(fperson.getSocSec() == sperson.getSocSec()){
			return 0;
		} else if(fperson.getSocSec() < sperson.getSocSec()){
			return -1;
		} else {
			return 1;
		}
	}
}