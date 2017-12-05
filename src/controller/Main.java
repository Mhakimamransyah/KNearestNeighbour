/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import evaluation.Evaluation;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import knearest.KNN;
import model.DataLatih;
import model.DataUji;
import model.Dataset;
import view.Home;
import view.Log;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Main {
   
    public static void main(String[] args) {
      Home ui;
      ui = new Home();
      ui.setVisible(true);
    }
    
    public String doLearnKNN(DefaultTableModel uji,DefaultTableModel latih,int knearest,int knntype){
       KNN knn = new KNN(new DataLatih(latih).getData(),new DataUji(uji).getData(),knntype);
       Evaluation eval = new Evaluation(knn,uji,knearest,0);
       DecimalFormat numberFormat = new DecimalFormat("#.00");
       return numberFormat.format(eval.getAkurasi()).toString();
    }
    
     public String doLearnFKNN(DefaultTableModel uji,DefaultTableModel latih,int knearest,int knntype,int weight){
        KNN fknn = new KNN(new DataLatih(latih).getData(),new DataUji(uji).getData(),knntype);
        Evaluation eval = new Evaluation(fknn,uji,knearest,weight);
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return numberFormat.format(eval.getAkurasi()).toString();
    }
    
    public void doLogKNN(DefaultTableModel uji,DefaultTableModel latih,int knearest,int knntype,int weight){
        KNN knn = new KNN(new DataLatih(latih).getData(),new DataUji(uji).getData(),knntype);
        Dataset data = new Dataset(knn.doLearn(knearest,weight),uji);
        Log ui = new Log(data.getTable_result());
        ui.setDescResult("KNN type : "+knntype+" weight : "+weight+" Knearest : "+knearest);
        ui.setVisible(true);
    }
    
  
    
}
