package comparators;

import Main.PersonModel;
import java.util.Comparator;

public class CityComparator implements Comparator<PersonModel> {
	@Override
	public int compare(PersonModel fperson, PersonModel sperson){
		int i = 0;
		char[] firstperson = fperson.getCity().toCharArray();
		char[] secondperson = sperson.getCity().toCharArray();
		while(i != firstperson.length && i != secondperson.length){
			if(Character.toLowerCase(firstperson[i]) < Character.toLowerCase(secondperson[i]))
				return - 1;
			else if(Character.toLowerCase(firstperson[i]) > Character.toLowerCase(secondperson[i]))
				return 1;
			else
				i++;
		}
		return 0;
	}
}
