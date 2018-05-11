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
public class DistProbConjuntaIndep extends DistProb{
    DistProbSimple d1;
    DistProbSimple d2;

    public DistProbConjuntaIndep(DistProbSimple dist1, DistProbSimple dist2) {
        this.d1 = dist1;
        this.d2 = dist2;
    }
    
    @Override
    protected float getMediaSpecific(){
        float mediaOut=0;
        for(int i=1;i<=d1.getNEventos();i++){
            for(int j=1;j<=d2.getNEventos();j++){
                mediaOut+=i*j*getProb(i,j);
            }
        }
        return mediaOut;
    }
    
    public float getCovarianza(){
        return getMedia()-(d1.getDesvio()*d2.getDesvio());
    }
    
    public float getFactorCorrelacion(){
        return getCovarianza()/(d1.getDesvio()*d2.getDesvio());
    }
    
    public float getProb(int eventoA,int eventoB){
        return d1.getProb(eventoA)*d2.getProb(eventoB);
    }
}
