package teoriadelainformacion;


public class mainTeoInf {

    public static void main(String[] args) {
        Imagen img = new Imagen("Imagenes/Will(Original).bmp");
        
        System.out.println(ManejadorArchivos.leerArchivo("imagen.txt"));
        //HashMap<String,String[]> codificacion = CodificacionHuffman.getCodificacion(img);
        
        //System.out.println("a");
        
    }
    
}
