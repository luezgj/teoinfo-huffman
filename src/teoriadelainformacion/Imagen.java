package teoriadelainformacion;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagen {
    final static int NRO_DE_COLORES=256;
    BufferedImage img;
    
    public Imagen(String dir){
        try {
            img = ImageIO.read(new File(dir));
            System.out.println(img.getColorModel());
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Imagen(int width, int height){
        img= new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    }
    
    public int getSizeX(){
        return img.getWidth();
    }
    
    public int getSizeY(){
        return img.getHeight();
    }
    
    public int[] getPixeles(){
        int[] pixelsArray= img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        for(int i=0;i<pixelsArray.length;i++){
            pixelsArray[i]=(pixelsArray[i] & 0xFF);
        }
        return pixelsArray;
    }
    
    public double[] getDPixeles(){
        double[] outArray=new double[img.getWidth()*img.getHeight()];
        int[] pixelsArray=getPixeles();
        for (int i = 0; i < pixelsArray.length; i++) {
            outArray[i] = pixelsArray[i];
        }
        return outArray;
    }
    
    public DistProbSimple<Integer> getDistribucion(){
        int [] pixeles=getPixeles();
        Integer [] etiquetas = new Integer[256];
        for (int i = 0; i < etiquetas.length; i++) {
            etiquetas[i]=i;
        }
        DistProbSimple<Integer> salida = new DistProbSimple(NRO_DE_COLORES,etiquetas);
        for (int i=0; i < pixeles.length; i++){
            salida.addOcurrencia(pixeles[i], 1);
        }
        return salida;
    }
}
