/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M.Hakim Amransyah
 */
public class DataLatih extends Data{
    
    public DataLatih(DefaultTableModel table){
        this.setNewData(table);
        System.out.println("Data Latih");
        super.debugData();
    }
    
    private void setNewData(DefaultTableModel table){
        // TODO change data to new type        
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
            data.add(attr);
//            System.out.println("");
        }
    }
}
