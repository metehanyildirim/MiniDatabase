package Main;

import java.util.ArrayList;

public class PersonModel {
	private final long cID;
	private final String name;
	private final String surname;
	private final String city;
	private final String address;
	private final long socSec;
	
	
	public PersonModel(long cID, String name, String surname, String city, String address, long socSec) {
		super();
		this.cID = cID;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.address = address;
		this.socSec = socSec;
	}


	public long getcID() {
		return cID;
	}


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getCity() {
		return city;
	}


	public String getAddress() {
		return address;
	}


	public long getSocSec() {
		return socSec;
	}
	
	@Override
	public String toString(){
		return cID + " " + name + " " + surname + " " + city + " " + address + " " + socSec;
	}
	
	
	public boolean equals(PersonModel pm){
		if(this.cID == pm.cID)
			return true;
		else
			return false;
	}
	
	public String toString(ArrayList<String> ourstring){
		String result = "";
		for(int i = 0; i < ourstring.size(); i++){
			if(ourstring.get(i).equals("CID")){
				result += "\t" + cID;
					
			}
			else if(ourstring.get(i).equals("FirstName")){
				result += "\t" + name;
			}
			else if(ourstring.get(i).equals("LastName")){
				result += "\t" + surname;
			}
			else if(ourstring.get(i).equals("City")){
				result += "\t" + city;
			}
			else if(ourstring.get(i).equals("AddressLine1")){
				result += "\t" + address;
			}
			else if(ourstring.get(i).equals("SocialSecurityNumber")){
				result += "\t" + socSec;
			}
		}
		result = result.substring(1);
		return result;
	}
	
}
