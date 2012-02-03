/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.io;

import java.util.Map;

/**
 * Class processing the cleaning of a {@link Map}.
 * 
 * @author bastien
 */
public class MapCleaner {
    
    /**
     * Removes a row from a given {@link Map}.
     * @param map The given {@link Map} to clean.
     * @param rowIndex The index of the row to remove.
     */
    public static void removeRow(Map<Integer,Map<Integer,String>> map, int rowIndex){
        if(map.containsKey(rowIndex)){
            map.remove(rowIndex);
        }
    }
    
    /**
     * Remove a column from a given {@link Map}.
     * @param map A given {@link Map} to clean.
     * @param colIndex The index of the column to remove.
     */
    public static void removeCol(Map<Integer,Map<Integer,String>> map, int colIndex){
        for(Integer rowIndex : map.keySet()){
            Map<Integer,String> row = map.get(rowIndex);
            if(row.containsKey(colIndex)){
                row.remove(colIndex);
            }
        }
    }
}
