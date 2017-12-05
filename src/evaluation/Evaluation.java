/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import knearest.KNN;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Evaluation {
    
    KNN knn;
    Double akurasi;
    int knearest;
    DefaultTableModel tableresult;
    int weight  = 0;
    

    public Evaluation(KNN nKnn,DefaultTableModel dataUji,int k,int weight){
        this.knn = nKnn;
        this.knearest = k;
        this.weight = weight;
        this.setAkurasi(dataUji);
        this.tableresult = new DefaultTableModel();
    }
    
    
    private void setAkurasi(DefaultTableModel data){
        
        double n=0.0;
        int accurate = 0;
                
        System.out.println("WEIGHT : "+this.weight);
        ArrayList<Double[]> result = this.knn.doLearn(this.knearest,this.weight);
        ArrayList<Double[]> dataUji = this.getOriginalDataUji(data);
        
        
        n = dataUji.size();
        int label = dataUji.get(0).length-1;
        for(int i=0;i<dataUji.size();i++){
           if((dataUji.get(i)[label] - result.get(i)[label])== 0){
               accurate++; 
           }  
        }
        this.akurasi = (accurate/n)*100;      
    }
    
    
    private void debugData(ArrayList<Double[]> data){
        for(int i=0;i<data.size();i++){
            int row = data.get(i).length;
            for(int j=0;j<row;j++){
                System.out.print(data.get(i)[j]+" ");
            }
            System.out.println("");
        }
    }
    
    public Double getAkurasi() {    
        return akurasi;
    }
    
    private ArrayList<Double[]> getOriginalDataUji(DefaultTableModel table){
        ArrayList<Double[]> temp = new ArrayList<Double[]>();
        Double[] attr;
        for(int i=0;i<table.getRowCount();i++){
            
            int j=1;
            int index=0;
            attr = new Double[table.getColumnCount()-1];
            while(j<table.getColumnCount()){
                attr[index] = Double.parseDouble(table.getValueAt(i,j).toString());
//                System.out.print("debug"+table.getValueAt(i,j).toString());
                index++;
                j++;
            }
            temp.add(attr);
//            System.out.println("");
        }      
        return temp;
    }
    
    
    
    
    
    
    
    
    
    
}
