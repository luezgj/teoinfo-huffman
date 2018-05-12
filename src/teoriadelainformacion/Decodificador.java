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
//Esta clase solo puede decodificar un mensaje por vez
public class Decodificador {

    public static int nrobitsLeidos = 0;
    public static int nroCharSig = 0;
    public static String mensajeDecodificando;
    public static char caracterActual;

    //El mensaje codificado entra como cadena de chars (No 0/1)
    public static <T extends Comparable<T>> List<T> decodificar(String mensajeCodificado, int cantSimbolos, DistProbSimple<T> probabilidades) {
        List<T> decodificado = new LinkedList();
        mensajeDecodificando = mensajeCodificado;

        Nodo<T> arbolCodigo = CodificacionHuffman.generarArbol(probabilidades);

        int cantSimbDecodificados = 0;
        Nodo<T> auxNodo = arbolCodigo;
        char c = leerBit();

        while (c != (char) 0xFFFF) {  //Caracter 111..1

            if (c == (char) 0x8000) { //Caracter 100..0
                auxNodo = auxNodo.getIzq();
            } else {
                auxNodo = auxNodo.getDer();
            }
            if (auxNodo.esHoja()) {
                decodificado.add(auxNodo.getSimbolo());
                auxNodo = arbolCodigo;
                if (++cantSimbDecodificados == cantSimbolos) {
                    return decodificado;
                }
            }

            c = leerBit();
        }
        return decodificado;
    }

    //Devuelve un caracter que tiene el bit leido en la parte alta y 0s en los demás bits (bit1=0x8000 ; bit0=0x0000)
    //Si no hay mas caracteres devuelve (char)0xFFFF
    private static char leerBit() {

        if ((nrobitsLeidos % 16) != 0) { //Cuando termino de leer un caracter paso al otro
            nrobitsLeidos = 0;
            if (nroCharSig < mensajeDecodificando.length()) {
                caracterActual = mensajeDecodificando.charAt(nroCharSig);
                nroCharSig++;
            } else {
                return (char) 0xFFFF;
            }
        }

        char caracterRetorno = (char) (caracterActual & (char) 0x8000); //0x8000=100..0
        caracterActual = (char) (caracterActual << 1);

        nrobitsLeidos++;

        return caracterRetorno;

    }

}
