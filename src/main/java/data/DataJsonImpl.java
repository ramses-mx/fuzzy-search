package data;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import domain.Person;

import util.Levenshtein;
import util.Search;

public class DataJsonImpl implements DataJson {

	private static String ARCHIVO_IN = "fuzzy-search.txt";

	private List<Person> people = new LinkedList<>();
	ObjectMapper objectMapper = new ObjectMapper();

	public DataJsonImpl() {

		this.read();
	}

	@Override
	public void read() {
		try {
			File file = new File(ARCHIVO_IN);
			if (!file.exists()) {
				file.createNewFile();
			} else {
				people = objectMapper.readValue(new File(ARCHIVO_IN), new TypeReference<List<Person>>() {
				});
			}
		} catch(MismatchedInputException e) {
			//No hay datos, no muestro error, porque no hay error
			return;
			
		}catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Boolean add(String oJSon) throws IOException {

		Person person =  Person.jsonToObject(oJSon);

		
		if (person == null) {
			System.out.println("No pudo convertir el parametro!!!!");
			return false;
		}
		people.add(person);
		
		Collections.sort(people, new DataComperator());
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARCHIVO_IN), people);
		System.out.println("Usuario Agregado");
		return true;
	}

	public String list() throws JsonProcessingException {

		System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(people));

		return null;
	}

	public void fuzySearch(String search) {
		
		Search personB  = Search.jsonToObject(search);
		StringTokenizer tokensBuscar;
		StringTokenizer tokensComparar;
		String valorBuscar;
		String valorComparar;
		Boolean seguirBuscando;
		Boolean encontrado=false;

		for (Person person : people) {
			seguirBuscando=true;
			
			tokensComparar= new StringTokenizer(person.getName());
			tokensBuscar= new StringTokenizer(personB.getSearch());
			while(seguirBuscando && tokensBuscar.hasMoreTokens()) {
				valorBuscar = tokensBuscar.nextToken();
				if(tokensComparar.hasMoreTokens()) {
					valorComparar=tokensComparar.nextToken();
					if(Levenshtein.ld(valorBuscar, valorComparar)<=3  ) {
						try {
							if(!tokensBuscar.hasMoreTokens()) {
								encontrado=true;
								System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person));
							}
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						seguirBuscando=false;
					}
				}					
			}
		}
		if(!encontrado) {
			System.out.println("Sin coincidencias");
		}
		
	}

	
}
