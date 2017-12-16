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
public class FKNNC {
    
     ArrayList<Double[]> label;
     ArrayList<Double[]> distance_tabel;
     int knearest,weight;
     
     public FKNNC(ArrayList<Double[]> label,ArrayList<Double[]> nDistancetable,int knearest,int weight){
        this.label = label;
        this.distance_tabel = nDistancetable;
        this.knearest = knearest;
        this.weight = weight;
       
//         this.getFKNNCClassification();
     }
     
     public double getFKNNCClassification(){
         this.setSumLabelValue();
         Double sum = 0.0;
         System.out.println("4. Jumlah Kelas yang Bersesuaian");
         this.debugData(label);
         System.out.println("\n");
         for(int i=0;i<label.size();i++){
             label.get(i)[1] = this.doPemangkatan(label.get(i)[1]);
         }
         for(int i=0;i<label.size();i++){
             sum = sum + label.get(i)[1];
         }
          System.out.println("5. Nilai S untuk masing-masing kelas untuk w= "+this.weight);
         this.debugData(label);
         System.out.println("\n");
         this.setNilaiKeanggotaan(sum);
         System.out.println("6. Jumlah nilai S untuk seluruh kelas : "+sum+"\n");
         System.out.println("7. Nilai keangotaan untuk masing-masing kelas");
         this.debugData(label);
         System.out.println("\n");
         System.out.println("8. Hasil klasifikasi : "+this.getConclusion()+"\n");
         System.out.println("--------------------------------------------------------------------------");
//         return this.getConclusion();
         return this.getConclusion();
     }
     
     private double getConclusion(){
//         TODO : ambil hasil klasifikiasi
       double res = 0.0;
       int max_index = 0;
        
       for(int i=0;i<label.size();i++){
           if(label.get(max_index)[1] < label.get(i)[1]){
               max_index = i;
           }
       }
       res = label.get(max_index)[0];
       return res;
     }
     
     private void setNilaiKeanggotaan(Double D){
         for(int i=0;i<label.size();i++){
             this.label.get(i)[1] = this.label.get(i)[1]/D; 
         }
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
     
     
     private void setSumLabelValue(){
         int j,iterKnearest;
         System.out.println("2. Kelas Data");
         this.debugData(this.label);
         System.out.println("\n");
         System.out.println("3. Data untuk masing-masing kelas untuk K= "+this.knearest);
         for(int i=0;i<label.size();i++){
             j=0;
             iterKnearest = 0;
             while(j < distance_tabel.size()){
                 if((label.get(i)[0] - this.distance_tabel.get(j)[this.distance_tabel.get(i).length-2]) == 0){
                     System.out.println(label.get(i)[0]+" "+this.distance_tabel.get(j)[0]+" "+this.distance_tabel.get(j)[1]+" "+this.distance_tabel.get(j)[3]);
                     label.get(i)[1] = label.get(i)[1] + distance_tabel.get(j)[this.distance_tabel.get(i).length-1];
                     iterKnearest++;
                 }
                 
                 if(iterKnearest == this.knearest){
                     break;
                 }
                 j++;
             }
            
        }
         System.out.println("\n");
     }
     
     
}
