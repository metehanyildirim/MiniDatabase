package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import data.AddressList;
import data.CityList;
import data.IDList;
import data.NameList;
import data.SocialList;
import data.SurnameList;
import searchandsort.Search;
import searchandsort.Sort;
import comparators.*;

public class Command {
	
	public ArrayList<String> select = new ArrayList<String>();
	public boolean isvalid = true;
	ArrayList<PersonModel[]> searched = new ArrayList<PersonModel[]>();
	long time;
	public Command(String input){
		parser(input);
	}
	
	public void parser(String input){
		StringTokenizer st = new StringTokenizer(input);
		if(st.nextToken().equals("SELECT")){
			String selected = st.nextToken();
			StringTokenizer st1 = new StringTokenizer(selected, ",");
			while(st1.hasMoreTokens()){
				select.add(st1.nextToken());
			}
			if(st.nextToken().equals("WHERE")){
				while(st.hasMoreTokens()){
					String where = st.nextToken();
					whereParser(where);
					if(st.hasMoreTokens())
						st.nextToken(); // "AND"
				}
			}
		}
	}
	
	public void whereParser(String input){
		char[] ourinput = input.toCharArray();
		String select = null;
		char operator = 0;
		StringBuilder sb = new StringBuilder();
		for(char c : ourinput){
			if(c == '<' || c == '>' || c == '~' || c == '='){
				select = sb.toString();
				sb.setLength(0);
				operator = c;
			} else{
			sb.append(c);
			}
		}
		Comparator<PersonModel> comparator;
		String wanted = sb.toString();
		if(select.equals("CID")){
			comparator = new CIDComparator();
			PersonModel[] idlist = addToListNum(comparator, wanted, operator);
			searched.add(idlist);
		} else if(select.equals("FirstName")){
			falseop(operator);
			comparator = new NameComparator();
			PersonModel[] namelist = addToListString(comparator, wanted);
			searched.add(namelist);
		} else if(select.equals("LastName")){
			falseop(operator);
			comparator = new SurnameComparator();
			PersonModel[] surlist = addToListString(comparator, wanted);
			if(surlist != null)
				searched.add(surlist);
		} else if(select.equals("City")){
			falseop(operator);
			comparator = new CityComparator();
			PersonModel[] citylist = addToListString(comparator, wanted);
			if(citylist != null)
				searched.add(citylist);
		} else if(select.equals("AddressLine1")){
			falseop(operator);
			comparator = new AddressComparator();
			PersonModel[] addresslist = addToListString(comparator, wanted);
			if(addresslist != null)
				searched.add(addresslist);
		} else if(select.equals("SocialSecurityNumber")){
			comparator = new SocialComparator();
			PersonModel[] soclist = addToListNum(comparator, wanted, operator);
			searched.add(soclist);
		} else {
			System.out.println("The thing to select is non-existent");
			return;
		}
	}
	
	private PersonModel[] addToListString(Comparator<PersonModel> comparator, String wanted){
		PersonModel[] list = null;
		if(comparator instanceof NameComparator){
			list = NameList.getInstance(null).nameSorted;
		} else if(comparator instanceof AddressComparator){
			list = AddressList.getInstance(null).addressSorted;
		} else if(comparator instanceof CityComparator){
			list = CityList.getInstance(null).citySorted;
		} else if(comparator instanceof SurnameComparator){
			list = SurnameList.getInstance(null).surnameSorted;
		}
		
		int startindex = Search.binaryStringSearch(list , wanted , comparator);
		int endindex = Search.binaryStringendSearch(list , wanted , comparator);
		PersonModel[] people = new PersonModel[endindex - startindex + 1];
		for(int i = startindex; i <= endindex; i++){
			people[i - startindex] = list[i];
		}
		return people;
	}
	
	private PersonModel[] addToListNum(Comparator<PersonModel> comparator,
							  String wanted, char operator){
		
		PersonModel[] list = null;
		if(comparator instanceof CIDComparator){
			list = IDList.getInstance(null).idSorted;
		} else if(comparator instanceof SocialComparator){
			list = SocialList.getInstance(null).socialSorted;
		}
		
		int index = Search.numSearch(list, 
				 Long.parseLong(wanted),
				 comparator, operator);
		PersonModel[] people = null;
		if(index == -1){
			isvalid = false;
			return null;
		}
		if(operator == '<'){
			people = new PersonModel[index + 1];
			for(int i = 0; i <= index; i++){
				people[i] = list[i]; 
			}
		} else if(operator == '>'){
			people = new PersonModel[list.length - index];
			for(int i = index; i < list.length; i++){
				people[i - index] = list[i];
			}
		} else if(operator == '='){
			people = new PersonModel[1];
			people[0] = list[index];
		}
		return people;
	}
	
	private void falseop(char operator){
		if(operator != '~'){
			isvalid = false;
		}
	}
	
	public ArrayList<PersonModel> merger(){
		int counter = 1;
		Comparator<PersonModel> comparator = new CIDComparator();
		ArrayList<PersonModel> merged = new ArrayList<PersonModel>();
		for(int i = 0; i < searched.size() && searched.get(i) != null; i++){
			merged.addAll(Arrays.asList(searched.get(i)));
		}
		PersonModel[] mergedarr = merged.toArray(new PersonModel[merged.size()]);
		Sort.quickSort(mergedarr, comparator);
		ArrayList<PersonModel> result = new ArrayList<PersonModel>();
		for(int i = 0; i < mergedarr.length; i++){
			if(searched.size() == counter){
				result.add(mergedarr[i]);
				counter = 1;
			}
			else if(i + 1 != mergedarr.length && mergedarr[i].equals(mergedarr[i + 1])){
				counter++;
			}
			else{
				counter = 1;
			}
		}
		return result;
	}
}
