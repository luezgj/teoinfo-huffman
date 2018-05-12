package teoriadelainformacion;


public class mainTeoInf {

    public static void main(String[] args) {
        Imagen img = new Imagen("Imagenes/Will(Original).bmp");
        
        System.out.println(ManejadorArchivos.leerArchivo("imagen.txt"));
        
        byte bytePruebas=0x2F;
        bytePruebas=(byte)(bytePruebas&(0x01));
        
        char charPrueba='a';
        charPrueba=(char)(charPrueba&(char)0x0000);
        charPrueba=(char)(charPrueba|(char)0x8000);
        
        String hola="hola";
        
        System.out.println(hola.charAt(0));
        //HashMap<String,String[]> codificacion = CodificacionHuffman.getCodificacion(img);
        
        //System.out.println("a");
        
    }
    
}
