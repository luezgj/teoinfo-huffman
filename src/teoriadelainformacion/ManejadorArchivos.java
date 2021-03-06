/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadelainformacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucho
 */
public class ManejadorArchivos {
    public static <T extends Comparable <T>> void guardarEnArchivo(String dir, InformacionArchivo<T> info){
        Charset utf8 = StandardCharsets.UTF_8;
        
        String widthYH=String.valueOf(info.getWidth())+"-"+String.valueOf(info.getHeight());
        
        String probabilidades="";
        Map<T,Integer> frecuencias=info.getDistribucion().getFrecuencia();
        for (Map.Entry<T, Integer> entry : frecuencias.entrySet()) {
            if(entry.getValue()!=0){
                probabilidades+="-"+entry.getKey().toString()+","+entry.getValue().toString();
            }
        }
        
        List<String> lines = Arrays.asList(widthYH, probabilidades, info.getMensaje());
        try {
            Files.write(Paths.get(dir), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static InformacionArchivo leerArchivo(String dir){
        try {
            File file = new File(dir);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            int width=0, height=0;
            
            Integer[] etiquetas= new Integer[Imagen.NRO_DE_COLORES];
            for (int i = 0; i < etiquetas.length; i++) {
                etiquetas[i]= i;
            }
            
            DistProbSimple<Integer> distribucion= new DistProbSimple(Imagen.NRO_DE_COLORES,etiquetas);
            String mensaje="";
            
            String auxLine=reader.readLine();

            //Lectura de width y height
            if (auxLine != null) {
                String[] wyh=auxLine.split("-");
                width=Integer.parseInt(wyh[0]);
                height=Integer.parseInt(wyh[1]);
            }
            
            //Lectura de probabilidades
            auxLine=reader.readLine();
            if (auxLine != null){
                String[] probabilidades=auxLine.split("-");
                for (int i = 0; i < probabilidades.length; i++) {
                    String[] prob = probabilidades[i].split(",");
                    int evento=Integer.parseInt(prob[0]);
                    int frecuencia=Integer.parseInt(prob[1]);
                    distribucion.addOcurrencia(evento, frecuencia);
                }
            }
            
            //Lectura de mensaje
            mensaje=reader.readLine();
            
            reader.close();
            
            InformacionArchivo<Integer> infoOut= new InformacionArchivo(width,height,distribucion,mensaje);
            return infoOut;
            
        } catch (IOException ex) {
            Logger.getLogger(CodificacionHuffman.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
