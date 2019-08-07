import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;

import data.DataJson;
import data.DataJsonImpl;
//java -cp app.jar com.somepackage.SomeClass
public class Application {

	public static void main(String[] args) {
		
		StringBuilder parametro = new StringBuilder();
		DataJson jsonData = new DataJsonImpl();
	
		if(args.length >= 1) {

					
			switch (args[0]) {
			case "add":
				for(int i=1;i<args.length;i++) {
					parametro.append(args[i]).append(" ");
					agregaCommillas(parametro);

					
				}
				
				

				try {
					jsonData.add(parametro.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "list":
				try {
					jsonData.list();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "fuzzy-search":
				for(int i=1;i<args.length;i++) {
					parametro.append(args[i]).append(" ");
					agregaCommillas(parametro);
					
				}

				jsonData.fuzySearch(parametro.toString());
				break;
				
			default:
				System.out.println("Parametro no valido, solo exite add, list y fuzy-search");
				break;
			}
		}else {
			System.out.println("Debe indicar add, list, fuzy-list");
		}
			
	}

	private static void agregaCommillas(StringBuilder parametro) {
		if(parametro.indexOf("{")!=-1) {
			parametro.insert(parametro.indexOf("{")+1, "\"");
		}
		if(parametro.indexOf(":")!=-1) {
			parametro.insert(parametro.indexOf(":"), "\"");
			parametro.insert(parametro.indexOf(":")+1, "\"");
		}
		if(parametro.indexOf("}")!=-1) {
			parametro.insert(parametro.indexOf("}"), "\"");
		}
	}
}
