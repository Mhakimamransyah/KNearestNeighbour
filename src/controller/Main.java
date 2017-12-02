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
    
    public String doLearn(DefaultTableModel uji,DefaultTableModel latih,int knearest,int knntype){
       KNN knn = new KNN(new DataLatih(latih).getData(),new DataUji(uji).getData(),knntype);
       Evaluation eval = new Evaluation(knn,uji,knearest);
       DecimalFormat numberFormat = new DecimalFormat("#.00");
       return numberFormat.format(eval.getAkurasi()).toString();
    }
    
    public void doLog(DefaultTableModel uji,DefaultTableModel latih,int knearest,int knntype){
        KNN knn = new KNN(new DataLatih(latih).getData(),new DataUji(uji).getData(),knntype);
        Dataset data = new Dataset(knn.doLearn(knearest),uji);
        Log ui = new Log(data.getTable_result());
        ui.setVisible(true);
    }
    
}
