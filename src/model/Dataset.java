/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M.Hakim Amransyah
 */
public class Dataset{

    ArrayList<Double[]> table_result;
    
    public Dataset(ArrayList<Double[]> knn,DefaultTableModel table){
           ArrayList<Double[]> data = this.getOriginalDataUji(table);
           this.table_result = this.getDataResultTable(knn, data);
           System.out.println("--debug tabel--");
           this.debugData(this.getDataResultTable(knn, data));      
    }
    
    public ArrayList<Double[]> getTable_result() {
        return table_result;
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
    
    public ArrayList<Double[]> getDataResultTable(ArrayList<Double[]> knn,ArrayList<Double[]> data){
        ArrayList<Double[]> result = new ArrayList<Double[]>();
        Double[] attr;
        for(int i=0;i<knn.size();i++){
            attr = new Double[knn.get(0).length+1];
            for(int j=0;j<attr.length;j++){
                if(j==attr.length-1){
                    attr[j] = knn.get(i)[data.get(i).length-1];
                }
                else{
                  attr[j] = data.get(i)[j];   
                }
            }
            result.add(attr);
        }
        return result;
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
}
