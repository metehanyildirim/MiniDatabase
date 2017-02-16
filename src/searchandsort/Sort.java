package searchandsort;
import java.util.Comparator;
import java.util.StringTokenizer;

import Main.*;
import comparators.*;
import data.*;
public class Sort {
	public static void quickSort(PersonModel[] people, Comparator<PersonModel> comparator){
		sort(people, 0, people.length - 1, comparator);
	}
	
	private static void sort(PersonModel[] people, int low, int high, Comparator<PersonModel> comparator){
		if( low < high){
			int j = partition(people, low, high, comparator);
			
			sort(people, low, j - 1, comparator);
			sort(people, j + 1, high, comparator);
		}
	}
	
	private static int partition(PersonModel[] people, int low, int high, Comparator<PersonModel> comparator){
		PersonModel pivot = people[high];
		int i = low - 1;
		
		for(int j = low; j <= high - 1; j++){
			if(comparator.compare(people[j], pivot) == -1 || comparator.compare(people[j], pivot) == 0){
				i++;
				
				PersonModel temp = people[i];
				people[i] = people[j];
				people[j] = temp;
			}
		}
		
		PersonModel temp = people[i + 1];
		people[i + 1] = people[high];
		people[high] = temp;
		
		return i + 1;
	}
	
	private static PersonModel[] createObjects(String[] input){
		int size = input.length - 1;
		PersonModel[] people = new PersonModel[size];
		for(int i = 1; i < input.length; i++){
			people[i - 1] = parseData(input[i]);
		}
		return people;
	}
	
	public static void instantiateSorted(String[] input){
		PersonModel[] people = createObjects(input);
		PersonModel[] idlist = people.clone();
		Comparator<PersonModel> comp = new CIDComparator();
		Sort.quickSort(idlist, comp);
		IDList ids = IDList.getInstance(idlist);
		PersonModel[] addresslist = people.clone();
		comp = new AddressComparator();
		Sort.quickSort(addresslist, comp);
		AddressList address = AddressList.getInstance(addresslist);
		PersonModel[] citylist = people.clone();
		comp = new CityComparator();
		Sort.quickSort(citylist, comp);
		CityList cities = CityList.getInstance(citylist);
		PersonModel[] namelist = people.clone();
		comp = new NameComparator();
		Sort.quickSort(namelist, comp);
		NameList nl = NameList.getInstance(namelist);
		PersonModel[] soclist = people.clone();
		comp = new SocialComparator();
		Sort.quickSort(soclist, comp);
		SocialList sl = SocialList.getInstance(soclist);
		PersonModel[] surlist = people.clone();
		comp = new SurnameComparator();
		Sort.quickSort(surlist, comp);
		SurnameList surl = SurnameList.getInstance(surlist);
	}
	
	private static PersonModel parseData(String input){
		StringTokenizer st = new StringTokenizer(input, "\\|");
		long id = Long.parseLong(st.nextToken());
		String name = st.nextToken();
		String surname = st.nextToken();
		String city = st.nextToken();
		String address = st.nextToken();
		long socsec = Long.parseLong(st.nextToken());
		return new PersonModel(id, name, surname, city, address, socsec);
	}
}
