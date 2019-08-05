package data;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import domain.Person;

public interface DataJson {
	public void read() ;
	public Boolean add(String in) throws IOException;
	public String list() throws JsonProcessingException;
	public void fuzySearch(String search);
	
}
