package data;

import java.util.Comparator;

import domain.Person;

public class DataComperator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		
		return p1.getName().compareTo(p2.getName());
	}
	
	

}
