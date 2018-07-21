/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package caixeiroviajante;

public class CaixeiroViajante {
   
    
    /** melhor solucao */
    private Solucao best = null;
    /** instancia do problema */
    private final Instancia inst;
    /** Numero de interações */
    protected static int itera;
    
    public CaixeiroViajante(Instancia inst){
        this.inst=inst;
    }    
    public Solucao otimiza(){        
        int []v = new int[inst.size()];
        // cria vetor para permutacaoes
        for(int i=0;i<v.length;i++){
            v[i]=i;
        }
        testaPermutacoes(0,v);
        return best;
    }
    
    void troca(int []v,int i, int j){
        int aux=v[i];
        v[i]=v[j];
        v[j]=aux;
    }
    
    void testaPermutacoes(int pos, int v[]){
        int n = v.length;
        if(pos==n-1){
            CaixeiroViajante.itera ++;
            Solucao S = new Solucao(inst, v);
            
            if(best==null||S.custo()<best.custo()){
                best=S.clonar();
            }
        }else{
            for(int i=pos;i<n;i++){
                troca(v,pos,i);
                testaPermutacoes(pos+1, v);
                troca(v,pos,i);
            }
        }
    }
    
    public static void main(String[] args) {
    	/*Inicia com um tamanho de 6*/
        Instancia I = new Instancia(6,100);
        I.geraAl();
        CaixeiroViajante cx = new CaixeiroViajante(I);
        Solucao S = cx.otimiza();
        System.out.println(S);
        System.out.println("Iteraçõs feitas:"+itera);
    }
}