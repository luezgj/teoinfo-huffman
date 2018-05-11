package teoriadelainformacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CodificacionHuffman <T> {
	
	public HashMap<T,String[]> obtenerCodigo(LinkedHashMap<T,Integer> Distribucion){
		
		Nodo raiz = generarArbol(Distribucion);
		
		HashMap<String,String[]> codificacion = getHashMap(raiz);
		
		return codificacion;
	}
	
	static private () {
		
	}
	
	//DE PRUEBA
	/*
	static public HashMap<String,String[]> getCodificacionMensaje(String mensaje){
		int[] frecuencia = getFrecuencia(mensaje);
		
		Nodo raiz = generarArbol(frecuencia);
		
		HashMap<String,String[]> codificacion = getHashMap(raiz);
		
		return codificacion;
	}
	
	static private int[] getFrecuencia(String mensaje) {
		
		int []frecuencia = new int[256];
		
		for (int i = 0; i < mensaje.length (); i++) 
			frecuencia[mensaje.charAt(i)]++;
		
		return frecuencia;
	}*/
	
	static private HashMap<String,String[]> getHashMap(Nodo raiz){
		
		HashMap<String,String[]> codificacion = new HashMap<>();
		
		completarHashMap(raiz, "", codificacion);
		
		return codificacion;
	}
	
	static private void completarHashMap(Nodo nodo, String cod,HashMap<String,String[]> codificacion){
		
		if (!nodo.esHoja()) {
			completarHashMap(nodo.izq, cod + '0',codificacion);
			completarHashMap(nodo.der, cod + '1',codificacion);
		} else {
			String[] codArr = new String[cod.length()];
			for (int i = 0; i < cod.length (); i++) 
				codArr[i]=Character.toString(cod.charAt(i));
			codificacion.put(Character.toString(nodo.pixel),codArr);
		}
	}
	
	public Nodo generarArbol(Map<T,Integer> Distribucion) {
		PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
		

		for (Map.Entry<T, String> entry : Distribucion.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}


		for(char i=0 ; i < frecuencia.length ; i++) {
			if (frecuencia[i] != 0) {
				colaPrioridad.add(new Nodo(i,frecuencia[i],null,null));
			}
		}
		
		if ( colaPrioridad.size() == 1)
			colaPrioridad.add(new Nodo(1,null,null));
		
		while (colaPrioridad.size() > 1) {
			Nodo nodoIzq = colaPrioridad.poll();
			Nodo nodoDer = colaPrioridad.poll();
			Nodo padre = new Nodo(nodoIzq.frecuencia + nodoDer.frecuencia,nodoIzq,nodoDer);
			colaPrioridad.add(padre);
		}
		
		return colaPrioridad.poll();
	}
	
        
        
    private static void leerArchivo(String dir) {
        try{
        File file = new File(dir);
        BufferedReader reader = reader = new BufferedReader(new FileReader(file));

        reader.close();
        String firstLine = reader.readLine();
        } catch(IOException ex){
            Logger.getLogger(CodificacionHuffman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public static Imagen decodificarImagen(String dir){
            try {
                
                
                Imagen out= new Imagen();
                Nodo arbolHuffman= generarArbol();
                
                
            } catch (IOException ex) {
                
            }
            
        }
        
        
        
        
        
        
        
        
        
        
        
	static public class Nodo <T extends Comparable<T>> implements Comparable<Nodo<T>>{
		
		T simbolo;
		int frecuencia;
		Nodo izq;
		Nodo der;
		
		public Nodo(T simbolo, int frecuencia, Nodo izq, Nodo der) {
			this.simbolo = simbolo;
			this.frecuencia = frecuencia;
			this.izq = izq;
			this.der = der;
		}
		public Nodo(int frecuencia, Nodo izq, Nodo der) {
			this.frecuencia = frecuencia;
			this.izq = izq;
			this.der = der;
		}
		
		public boolean esHoja() {
			return izq == null && der == null;
		}

		@Override
		public int compareTo(Nodo nodo) {
			
			if (this.frecuencia > nodo.frecuencia) {
				return 1;
			} else if (this.frecuencia < nodo.frecuencia) {
				return -1;
			} 
			
			return this.simbolo.compareTo(nodo.simbolo);
		}
	}
}
