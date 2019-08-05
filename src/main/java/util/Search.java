package util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Search {
	
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return String.format("Search [search=%s]", search);
	}
	
	public static Search jsonToObject(String in) {
		Search search=null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {

			search = objectMapper.readValue(in, Search.class);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error en la conversion");
			e.printStackTrace();
		}
		
		return search;
	}
	

}
