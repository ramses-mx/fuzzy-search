# fuzzy-search
Un pequeño proyecto donde se realiza una busqueda difusa utilizando el algoritmo de Levenshtein


En la busqueda tiene un parametro a buscar el cual se divide en tokens o separa las palabras usando como delimitador el espacio en blanco para hacer una busqueda difusa parecida cuando estamos en un combo, en el algorito de Levenshtein se uso como parametro de desviacion de 3 para que no muestre muchos falsos positivos, 

Se usa el formato JSON para recivir los parametros 

add {"name":"Pedro"}

para poder usar el formato JSOn de una forma agradable se utiliza se utiliza ObjectMapper de
com.fasterxml.jackson.core 

para el manejo de las cadenas StringBuilder

y para tener en memoria la informacion LinkedList



