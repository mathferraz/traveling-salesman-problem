/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caixeiroviajante;

public class Solucao {
    private Instancia I;
    private int []v;
    private int n;    
    
    public Solucao(Instancia I, int []v){
        this.v=v;
        this.I=I;
        this.n=I.size(); 
    }
    
    public int custo(){ 
        int valor=0;
        for(int i=0;i<n;i++){
            valor+=I.d(v[i], v[(i+1)%n]);
        }
        
        return valor;
    }
    
    public String toString(){
        String ret = "Solucao\n";       
        ret += "Custo "+this.custo()+"\n";
        for(int i=0;i<n;i++){
            ret+= v[i]+"-("+I.d(v[i],v[(i+1)%n])+")->";
        }
        ret+=v[0]+"\n";
        return ret;
    }

    public Solucao clonar() {
        int []aux = new int[n];
        for(int i=0;i<n;i++){
            aux[i]=v[i];
        }
        Solucao x = new Solucao(I, aux);
        return x;
    }
}
