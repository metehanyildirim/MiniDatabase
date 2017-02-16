package searchandsort;

import Main.PersonModel;
import comparators.CIDComparator;
import comparators.SocialComparator;
import java.util.Comparator;
import java.util.ArrayList;

public class Search {
	
	/* Binary search for numbers */
	public static int binaryNumSearch(PersonModel[] people,long value, Comparator<PersonModel> comparator){
		PersonModel holder = null; // This is for holding the "value" variable since
		if(comparator instanceof CIDComparator){ // comparator is based on objects.
			holder = new PersonModel(value , null, null ,null ,null, 0);// so we can
		} else if(comparator instanceof SocialComparator) {// compare the objects later.
			holder = new PersonModel(0 , null, null ,null ,null, value);
		}
		int lo = 0;
		int hi = people.length - 1;
		int mid = 0;
		while(lo <= hi){
			mid = lo + (hi - lo) / 2;
			if(comparator.compare(holder, people[mid]) == -1)
				hi = mid - 1;
			else if(comparator.compare(holder, people[mid]) == 1)
				lo = mid + 1;
			else{
				return mid;
			}
		}
		while(comparator.compare(holder, people[mid]) == 1){ // While smaller go until it is bigger.
			mid++;
		}
		while(comparator.compare(holder, people[mid]) == -1){ // While bigger go until it is smaller.
			mid--;
		}
		return mid;
	}
	
	/* This is for string binary search */
	public static int binaryStringSearch(PersonModel[] people , String value , Comparator<PersonModel> comparator){
		PersonModel holder = new PersonModel(0 , value , value , value , value, 0);
		int lo = 0;
		int hi = people.length - 1;
		int mid = 0;
		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;
			if(comparator.compare(holder, people[mid]) == -1)
				hi = mid - 1;
			else if(comparator.compare(holder, people[mid]) == 1)
				lo = mid + 1;
			else
				break;
		}
		
		while(mid - 1 != -1 && comparator.compare(holder, people[mid - 1]) == 0)
			mid--;
		
		return mid;
	}
	public static int binaryStringendSearch(PersonModel[] people , String value , Comparator<PersonModel> comparator){
		PersonModel holder = new PersonModel(0 , value , value , value , value, 0);
		int lo = 0;
		int hi = people.length - 1;
		int mid = 0;
		while(lo <= hi) {
			mid = lo + (hi - lo) / 2;
			if(comparator.compare(holder, people[mid]) == -1)
				hi = mid - 1;
			else if(comparator.compare(holder, people[mid]) == 1)
				lo = mid + 1;
			else
				break;
		}
		
		while(mid + 1 <= people.length - 1 && comparator.compare(holder, people[mid + 1]) == 0)
			mid++;
		
		return mid;
	}
	
	/*This function is for comparing numbers with operators. When an element isn't
	 *found with binary search it goes to the biggest element smaller than the
	 *searched element. If the element is found it is on the value itself for
	 *making it smaller than or bigger than we iterate it forward or backward.*/
	public static int numSearch(PersonModel[] people,long value, Comparator<PersonModel> comparator, char operator){
		int index = binaryNumSearch(people, value, comparator);
		if(operator == '<'){
			if(comparator instanceof CIDComparator){ // If equal we iterate it backwards.
				if(people[index].getcID() == value)
					index--;
			} else if(comparator instanceof SocialComparator){
				if(people[index].getSocSec() == value)
					index--;
			}
		} else if(operator == '>'){ // Doesnt matter if equal or not we iterate forward.
			index++;
		} else if(operator == '='){
			
		} else {
			System.out.println("One of your operators are invalid");
			index = -1;
		}
		return index;
	}
	
	
	
}
