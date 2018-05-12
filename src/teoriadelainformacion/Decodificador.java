/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadelainformacion;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lucho
 */
public class Decodificador {
    public static <T extends Comparable<T>> List<T> decodificar(List<String> mensajeCodificado, DistProbSimple<T> probabilidades){
        List<T> decodificado= new LinkedList();
        
        Nodo arbolCodigo= CodificacionHuffman.generarArbol(probabilidades);
        
        for(String codigo: mensajeCodificado){
            
        }
        
        return decodificado;
    }
}
