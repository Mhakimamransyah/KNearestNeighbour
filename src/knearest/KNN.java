/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knearest;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author M.Hakim Amransyah
 */
public class KNN {
    
    ArrayList<Double[]> data_latih;
    ArrayList<Double[]> data_uji;
    int knntype;

    public KNN(ArrayList<Double[]> dl,ArrayList<Double[]> du,int knntype){
        this.data_latih = dl;
        this.data_uji = du;
        this.knntype = knntype;
    }
    
    public ArrayList<Double[]> doLearn(int knearest,int weight){
        ArrayList<Double[]> result = null;
        switch (this.knntype) {
            case 1:    
                       System.out.println("---------- KNN(K-NEAREST NEIGHBOUR) ---------");
                       result =  this.doLearnKNN(knearest,"KNN",0);
                       System.out.println("---------- KNN ---------\n\n\n\n\n");
                break;
            case 2:    System.out.println("---------- FKNN(FUZZY K-NEAREST NEIGHBOUR) ---------");
                       result = this.doLearnKNN(knearest,"FKNN", weight);
                       System.out.println("---------- FKNN ---------\n\n\n\n\n");
                break;
            case 3:   
                      System.out.println("---------- FKNNC(FUZZY K-NEAREST NEIGHBOUR IN CLASS) ---------");
                      result = this.doLearnKNN(knearest,"FKNNC", weight);
                      System.out.println("---------- FKNNC ---------\n\n\n\n\n");
                break;
            default:
                break;
        }      
        return result;
    }
    
    
    
    private ArrayList<Double[]> doLearnKNN(int knearest,String knntype,int weight){
        ArrayList<Double[]> result = new ArrayList<Double[]>();
        ArrayList<Double[]> temp;
        Double[] attr_temp;
        for(int i=0;i<this.data_uji.size();i++){
            temp = new ArrayList<Double[]>();
            for(int j=0;j<this.data_latih.size();j++){
                attr_temp = new Double[this.data_latih.get(0).length+1];
//                BUTUH GENERALISASI
                 for(int x=0;x<attr_temp.length;x++){
                     if(x == attr_temp.length-1){
                       attr_temp[x] = this.euclideanDistance(this.data_uji.get(i),this.data_latih.get(j));       
                     }
                     else{
                       attr_temp[x] = this.data_latih.get(j)[x];  
                     }
                 }
                temp.add(attr_temp);
            }
            if(knntype.equalsIgnoreCase("KNN")){
              this.sortDistance(temp);
              System.out.println("1. tabel jarak data");
              this.debugData(temp);
              System.out.println("\n");
              
              Double[] attr = new Double[this.data_uji.get(0).length+1];
              for(int x=0;x<attr.length;x++){
                  if(x == (attr.length-1)){
                    attr[x] = this.getLabelResult(knearest, temp);
                  }
                  else{
                    attr[x] = this.data_uji.get(i)[x];   
                  }
              }
              result.add(attr);
              System.out.println("2. Hasil Klasifikasi : ");
              this.debugData(result);
              System.out.println("");   
            }
            else if(knntype.equalsIgnoreCase("FKNN")){
                this.sortDistance(temp);
                System.out.println("\n---tabel jarak data---");
                this.debugData(temp);
                FKNN fknn = new FKNN(temp,knearest,weight);
                Double[] attr = new Double[this.data_uji.get(0).length+1];
                for(int x=0;x<attr.length;x++){
                  if(x == (attr.length-1)){
                    attr[x] = fknn.getFKNNclassification();
                  }
                  else{
                    attr[x] = this.data_uji.get(i)[x];   
                  }
               }
              result.add(attr);
                
            }
            else if(knntype.equalsIgnoreCase("FKNNC")){
               this.sortDistance(temp);
               System.out.println("1. tabel jarak data");
               this.debugData(temp);
               System.out.println("\n");
               FKNNC  fknnc = new FKNNC(this.getAllLabel(temp),temp,knearest,weight);
               Double[] attr = new Double[this.data_uji.get(0).length+1];
                for(int x=0;x<attr.length;x++){
                  if(x == (attr.length-1)){
                    attr[x] = fknnc.getFKNNCClassification();
                  }
                  else{
                    attr[x] = this.data_uji.get(i)[x];   
                  }
               }
              result.add(attr);
            }
        }     
        return result;
    }
    
    
    private Double euclideanDistance(Double[] x1,Double[] x2 ){
        Double res = 0.0;
        for(int i=0;i<x1.length;i++){
            res = res + Math.pow(x1[i]-x2[i],2);
        }
        return Math.sqrt(res);
    }
    
    private void sortDistance(ArrayList<Double[]> data){
        //BUBLE SORT
        int distance_index = data.get(0).length-1;
        for(int i=data.size()-1;i>=0;i--){
            for(int j=0;j<i;j++){
               if(j!=i){
                  if(data.get(j)[distance_index] > data.get(j+1)[distance_index]){
                    Double[] temp = data.get(j);
                    data.set(j,data.get(j+1));
                    data.set(j+1, temp);
                  }
               }
            }
        }
    }
    
    private void debugData(ArrayList<Double[]> data){
        for(int i=0;i<data.size();i++){
            for(int j=0;j<data.get(0).length;j++){
                System.out.print(data.get(i)[j]+" ");
            }
            System.out.println("");
        }
    }
    
    public double getLabelResult(int knearest,ArrayList<Double[]> data){
        double label = 0.0;
        ArrayList<Double[]> label_data;
        ArrayList<Double[]> tempdata = new ArrayList<Double[]>();
        for(int i=0;i<knearest;i++){
            tempdata.add(data.get(i));
        }
        if(knearest == 1){
            label = tempdata.get(0)[data.get(0).length-2];
        }else{
//           2 LABEL
            label_data = this.getAllLabel(tempdata);
            if(label_data.size() == 1){
               label = label_data.get(0)[0];
            }
            else{
                for(int j=0;j<knearest;j++){
                    for(int x=0;x<label_data.size();x++){
                        if((tempdata.get(j)[data.get(0).length-2] - label_data.get(x)[0]) == 0){
                            label_data.get(x)[1] = label_data.get(x)[1]+1; 
                        }
                    }
                }
            }
            
            int max = 0;
            for(int q=0;q<label_data.size();q++){
                if(q != label_data.size()-1){
                   if(label_data.get(q)[1] < label_data.get(q+1)[1]){
                       max = q+1;
                   }   
                }
            }
            
            return label_data.get(max)[0];
//            double x1 = 0;
//            double x2 = 0;
//            for(int j=0;j<knearest;j++){
//                if(tempdata.get(j)[data.get(0).length-2] == 1.0){
//                    x1++;
//                }
//                else if(tempdata.get(j)[data.get(0).length-2] == 0.0){
//                    x2++;
//                }
//            }           
//            if(x1>x2){
//                label = 1.0;
//            }
//            else{
//                label = 0.0;
//            }
        }
        
        return label;
    }
    
    private ArrayList<Double[]> getAllLabel(ArrayList<Double[]> data){
//        TODO : mengambil seluruh label 
       ArrayList<Double[]> label = new ArrayList<Double[]>();
       int isi = 0;
       double data_label;
       for(int i=0;i<data.size();i++){
           if(label.size() == 0){
               Double[] temp = new Double[2];
               temp[0] = data.get(i)[data.get(0).length-2];
               temp[1] = 0.0;
               label.add(temp);
           }
           else{
              data_label = 0.0;
              isi = 0;
              for(int j=0;j<label.size();j++){
                   if((label.get(j)[0]-data.get(i)[data.get(0).length-2])!= 0){
//                         label.add(data.get(i)[data.get(0).length-2]);
                       data_label = data.get(i)[data.get(0).length-2];
                       isi++;
                   }
                   else if((label.get(j)[0]-data.get(i)[data.get(0).length-2])== 0){
//                      if(isi == 0){
//                          isi = 0;
//                      }else{ isi--; }
                      isi = 0;
                      break;
                   }
              }
              if(isi > 0){
                  Double[] temp = new Double[2];
                  temp[0] = data_label;
                  temp[1] = 0.0;
                  label.add(temp);
              }
           }
       }
       
        return label;
    }
    
    
}
