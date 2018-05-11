/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teoriadelainformacion;


/**
 *
 * @author lucho
 */
public class DistProbSimple extends DistProb{
    private int nEventos;
    private int[] ocurrencias;
    private int totalOcurrencias;
    private boolean desvioChanged;
    private float desvio=-1;
    

    public DistProbSimple(int nEventos) {
        this.nEventos = nEventos;
        ocurrencias= new int[nEventos];
    }
    
    public boolean addOcurrencia(int evento, int cantOcurrencias){
        if ( evento>=0 && evento<nEventos) {
            ocurrencias[evento]+=cantOcurrencias;
            totalOcurrencias+=cantOcurrencias;
            mediaChanged=true;
            desvioChanged=true;
        }
        return false;
    }
    
    public float getProb(int evento){
        if (evento>=0 && evento<nEventos){
            return (float)ocurrencias[evento]/(float) totalOcurrencias;
        }
        return -1;
    }
    
    public int[] getFrecuencia() {
    	
    	return ocurrencias;
    }
    
    public int getNEventos(){
        return nEventos;
    }
    
    @Override
    protected float getMediaSpecific(){
        int suma = 0;
        for (int i = 0; i < ocurrencias.length; i++) {
            suma += i * ocurrencias[i];
        }
        return (float) suma / (float) totalOcurrencias;
    }
    
    public float getDesvio() {
        if (desvio == -1 || desvioChanged) {
            float desvioOut = 0;
            for (int i = 0; i < ocurrencias.length; i++) {
                desvioOut += Math.pow(i - getMedia(), 2) * ((float) ocurrencias[i] / (float) totalOcurrencias);
            }
            desvio = (float) Math.pow(desvioOut, 0.5);
            desvioChanged = false;
        }
        return desvio;
    }
    
    public double[] getArrayDistribution(){
        double[] outArray= new double[nEventos];
        for(int i=0;i<ocurrencias.length;i++){
            outArray[i]=(double)ocurrencias[i]/(double)totalOcurrencias;
        }
        return outArray;
    }
    
}