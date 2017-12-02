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
public class DataUji extends Data{
    
    
    
    public DataUji(DefaultTableModel table){
        this.setNewData(table);
        System.out.println("Data Uji");
        super.debugData();    
    }
    
    private void setNewData(DefaultTableModel table){
       // TODO change data to new type        
        Double[] attr;
        for(int i=0;i<table.getRowCount();i++){
            
            int j=1;
            int index=0;
            attr = new Double[table.getColumnCount()-2];
            while(j<table.getColumnCount()-1){
                attr[index] = Double.parseDouble(table.getValueAt(i,j).toString());
//                System.out.print("debug"+table.getValueAt(i,j).toString());
                index++;
                j++;
            }
            data.add(attr);
//            System.out.println("");
        }
    }  
}
