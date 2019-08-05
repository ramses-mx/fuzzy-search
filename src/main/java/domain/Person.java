package domain;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import util.Search;

public class Person {
	
	private String name;
	

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Person [name=%s]", name);
	}
	
	public static Person jsonToObject(String in) {
		Person person =null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {

			person = objectMapper.readValue(in, Person.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return person;
	}
	

}
