package teoriadelainformacion;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class mainTeoInf {

    public static void main(String[] args) {
        Imagen img = new Imagen("Imagenes/Will(Original).bmp");
        
        HashMap<String,String[]> codificacion = CodificacionHuffman.getCodificacion(img);
        
        //System.out.println("a");
        
    }
    
}
