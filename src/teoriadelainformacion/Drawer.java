/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadelainformacion;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

/**
 *
 * @author lucho
 */
public class Drawer {
    
    public static void dibujarDistribucion(String nombre,String etiquetaX, String etiquetaY,Imagen img,JPanel panel){
        HistogramDataset datos=new HistogramDataset();
        datos.setType(HistogramType.FREQUENCY);
        double [] arre = img.getDPixeles();
        datos.addSeries("Numero de pixels", arre, Imagen.NRO_DE_COLORES);
        JFreeChart chart= ChartFactory.createHistogram(nombre, etiquetaX, etiquetaY, datos, PlotOrientation.VERTICAL, true, true, false);
        panel.removeAll();
        panel.repaint();
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(new ChartPanel(chart));
        panel.validate();  
    }
}
