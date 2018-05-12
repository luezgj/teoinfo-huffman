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
    public static <T extends Comparable<T>> List<T> decodificar(String mensajeCodificado,int cantSimbolos ,DistProbSimple<T> probabilidades){
        List<T> decodificado= new LinkedList();
        
        Nodo<T> arbolCodigo = CodificacionHuffman.generarArbol(probabilidades);
        int cantSimbDecodificados=0;
        for(int i=0;i<mensajeCodificado.length();i++){ //Por cada bit
            Nodo<T> auxNodo = arbolCodigo;
            char c = mensajeCodificado.charAt(i);
            if(c=='1'){
                auxNodo=auxNodo.getIzq();
            }else{
                auxNodo=auxNodo.getDer();
            }
            if(auxNodo.esHoja()){
                decodificado.add(auxNodo.getSimbolo());
                auxNodo=arbolCodigo;
                if(++cantSimbDecodificados==cantSimbolos){
                    return decodificado;
                }
            }        
        }
        return decodificado;
    }
}
