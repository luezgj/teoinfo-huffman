package teoriadelainformacion;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CodificacionHuffman <T extends Comparable<T>> {
	
	public HashMap<T,String[]> obtenerCodigo(DistProbSimple<T> distribucion){
		
		Nodo<T> raiz = generarArbol(distribucion.getFrecuencia());
		
		HashMap<T,String[]> codificacion = getHashMap(raiz);
		
		return codificacion;
	}
	
        private HashMap<T,String[]> getHashMap(Nodo<T> raiz){
		
		HashMap<T,String[]> codificacion = new HashMap<>();
		
		completarHashMap(raiz, "", codificacion);
		
		return codificacion;
	}
	
	private void completarHashMap(Nodo<T> nodo, String cod,HashMap<T,String[]> codificacion){
		
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
	
	public Nodo<T> generarArbol(Map<T,Integer> Distribucion) {
		PriorityQueue<Nodo<T>> colaPrioridad = new PriorityQueue<Nodo<T>>();
		
		for (Map.Entry<T, Integer> pair : Distribucion.entrySet()) {
			if (pair.getValue() != 0)
				colaPrioridad.add(new Nodo<T>(pair.getKey(),pair.getValue(),null,null));
		}
		
		if ( colaPrioridad.size() == 1)
			colaPrioridad.add(new Nodo<T>(1,null,null));
		
		while (colaPrioridad.size() > 1) {
			Nodo<T> nodoIzq = colaPrioridad.poll();
			Nodo<T> nodoDer = colaPrioridad.poll();
			Nodo<T> padre = new Nodo<T>(nodoIzq.frecuencia + nodoDer.frecuencia,nodoIzq,nodoDer);
			colaPrioridad.add(padre);
		}
		
		return colaPrioridad.poll();
	}
}
