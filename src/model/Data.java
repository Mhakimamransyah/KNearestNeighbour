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
public abstract class Data {
   
    ArrayList<Double[]> data;
    
    public Data(){
        data = new ArrayList<Double[]>();
    }
    
    public ArrayList<Double[]> getData() {
        return data;
    }

    public void setData(ArrayList<Double[]> data) {
        this.data = data;
    }
    
    protected void debugData(){
        for(int i=0;i<data.size();i++){
            int index = 0;
            while(index < data.get(0).length){
                System.out.print(data.get(i)[index]+" ");
                index++;
            }
            System.out.println("");
        }
    }
}
