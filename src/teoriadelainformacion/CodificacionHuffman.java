package teoriadelainformacion;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CodificacionHuffman {
	
	public static <T extends Comparable<T>> HashMap<T,String[]> obtenerCodigo(DistProbSimple<T> distribucion){
		
		Nodo<T> raiz = generarArbol(distribucion);
		
		HashMap<T,String[]> codificacion = getHashMap(raiz);
		
		return codificacion;
	}
	
        private static <T extends Comparable<T>> HashMap<T,String[]> getHashMap(Nodo<T> raiz){
		
		HashMap<T,String[]> codificacion = new HashMap<>();
		
		completarHashMap(raiz, "", codificacion);
		
		return codificacion;
	}
	
	private static <T extends Comparable<T>> void completarHashMap(Nodo<T> nodo, String cod,HashMap<T,String[]> codificacion){
		
		if (!nodo.esHoja()) {
			completarHashMap(nodo.izq, cod + '1',codificacion);
			completarHashMap(nodo.der, cod + '0',codificacion);
		} else {
			String[] codArr = new String[cod.length()];
			for (int i = 0; i < cod.length (); i++) 
				codArr[i]=Character.toString(cod.charAt(i));
			codificacion.put(nodo.getSimbolo(),codArr);
		}
	}
	
	public static <T extends Comparable<T>> Nodo<T> generarArbol(DistProbSimple<T> distribucion) {
		PriorityQueue<Nodo<T>> colaPrioridad = new PriorityQueue();
		
		for (Map.Entry<T, Integer> pair : distribucion.getFrecuencia().entrySet()) {
			if (pair.getValue() != 0)
				colaPrioridad.add(new Nodo<T>(pair.getKey(),pair.getValue(),null,null));
		}
		
		if ( colaPrioridad.size() == 1)
			colaPrioridad.add(new Nodo<T>(1,null,null));
		
		while (colaPrioridad.size() > 1) {
			Nodo<T> nodoIzq = colaPrioridad.poll();
			Nodo<T> nodoDer = colaPrioridad.poll();
			Nodo<T> padre = new Nodo(nodoIzq.frecuencia + nodoDer.frecuencia,nodoIzq,nodoDer);
			colaPrioridad.add(padre);
		}
		
		return colaPrioridad.poll();
	}
}
