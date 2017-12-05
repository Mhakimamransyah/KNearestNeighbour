/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knearest;

import java.util.ArrayList;

/**
 *
 * @author M.Hakim Amransyah
 */
public class FKNN {
    
    ArrayList<Double[]> distance_table;
    int knearest,weight;
    
    public FKNN(ArrayList<Double[]> nDistancetable,int knearest,int weight){
         this.distance_table = nDistancetable;
         this.knearest = knearest;
         this.weight = weight;
    }
    
    public ArrayList<Double[]> doFKNN(){
//        TODO : lakukan FKNN
        ArrayList<Double[]> temp = new ArrayList<Double[]>();
        Double attr[];
        int attr_size = this.distance_table.get(0).length;
        for(int i=0;i<this.knearest;i++){
            attr = new Double[attr_size+1];
            for(int j=0;j<attr.length;j++){
                if(j == attr.length-1){
                    attr[j] = this.doPemangkatan(this.distance_table.get(i)[attr_size-1]);
                }
                else{
                    attr[j] = this.distance_table.get(i)[j];
                }
            }
            temp.add(attr);
        }
        System.out.println("---DEBUG FKNN---");
        this.debugData(temp);
        return temp;
    }
    
    public double getFKNNclassification(){
//        TODO : ambil hasil prediksi fknn
        ArrayList<Double[]> temp = this.doFKNN();
        ArrayList<Double[]> label = this.getAllLabel(temp);
        double sum = this.getSum(temp);
        int attr_size = temp.get(0).length;
        
        for(int i=0;i<temp.size();i++){
             for(int j =0;j<label.size();j++){
                 if((label.get(j)[0] - temp.get(i)[attr_size-3]) == 0 ){
                     label.get(j)[1] = label.get(j)[1] + temp.get(i)[attr_size-1];
                 }
             }
        }
        
        for(int x = 0;x<label.size();x++){
            System.out.println(" sum : "+label.get(x)[1]);
            label.get(x)[1] = label.get(x)[1]/sum; 
        }
       
        System.out.println(" debug baris : "+label.get(0)[1]);
        System.out.println("--HASIL PREDIKSI--");
        this.debugData(label);
        System.out.println("conclusion : "+this.getConclusion(label));
        return this.getConclusion(label);
    }
    
    private double getConclusion(ArrayList<Double[]> label){
       int max_index;
       
       max_index = 0;
       for(int i=0;i<label.size();i++){
              if(label.get(max_index)[1] < label.get(i)[1]){
                  max_index = i;
              }   
       }
       return label.get(max_index)[0];
    }
    
    private double getSum(ArrayList<Double[]> temp){
         double sum = 0.0;
         int attr_size = temp.get(0).length;
         for(int i=0;i<temp.size();i++){
             sum+=temp.get(i)[attr_size-1];
         }
         return sum;
    }
    
    private Double doPemangkatan(double x){
//        TODO d^-2/m-1
       Double result = 0.0;
       Double d = (-2.0/(this.weight-1));
       result = Math.pow(x,d);
       return result;
    }
    
    
    private void debugData(ArrayList<Double[]> data){
        for(int i=0;i<data.size();i++){
            for(int j=0;j<data.get(0).length;j++){
                System.out.print(data.get(i)[j]+" ");
            }
            System.out.println("");
        }
    }
    
    private ArrayList<Double[]> getAllLabel(ArrayList<Double[]> data){
//        TODO : mengambil seluruh label 
       ArrayList<Double[]> label = new ArrayList<Double[]>();
       int isi = 0;
       double data_label;
       for(int i=0;i<data.size();i++){
           if(label.size() == 0){
               Double[] temp = new Double[2];
               temp[0] = data.get(i)[data.get(0).length-3];
               temp[1] = 0.0;
               label.add(temp);
           }
           else{
              data_label = 0.0;
              isi = 0;
              for(int j=0;j<label.size();j++){
                   if((label.get(j)[0]-data.get(i)[data.get(0).length-3])!= 0){
//                         label.add(data.get(i)[data.get(0).length-2]);
                       data_label = data.get(i)[data.get(0).length-3];
                       isi++;
                   }
                   else if((label.get(j)[0]-data.get(i)[data.get(0).length-3])== 0){
                      if(isi == 0){
                          isi = 0;
                      }else{ isi--; }
                      break;
                   }
              }
              if(isi == 1){
                  Double[] temp = new Double[2];
                  temp[0] = data_label;
                  temp[1] = 0.0;
                  label.add(temp);
                  System.out.println("true "+i);
              }
           }
       }
       
        return label;
    }
    
}
