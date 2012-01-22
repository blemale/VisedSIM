/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import java.util.Map;

/**
 *
 * @author bastien
 */
public class MapCleaner {
    
    public static void removeRow(Map<Integer,Map<Integer,String>> map, int rowIndex){
        if(map.containsKey(rowIndex)){
            map.remove(rowIndex);
        }
    }
    
    public static void removeCol(Map<Integer,Map<Integer,String>> map, int colIndex){
        for(Integer rowIndex : map.keySet()){
            Map<Integer,String> row = map.get(rowIndex);
            if(row.containsKey(colIndex)){
                row.remove(colIndex);
            }
        }
    }
}
