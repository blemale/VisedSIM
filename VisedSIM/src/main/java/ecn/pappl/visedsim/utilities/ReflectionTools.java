/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecn.pappl.visedsim.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class providing tools for reflection.
 * 
 * @author bastien
 */
public final class ReflectionTools {
    
    private ReflectionTools(){
    }
    
    /**
     * Launches a method of a given {@link Class} using reflection. 
     * @param obj A given {@link Object}.
     * @param args The arguments of the method.
     * @param nomMethode The name of the method to launch.
     * @return An {@link Object}
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    public static Object launchMethod(Object obj, Object[] args, String nomMethode) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class[] paramTypes = null;
        if (args != null) {
            paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; ++i) {
                paramTypes[i] = args[i].getClass();
            }
        }
        Method m = obj.getClass().getMethod(nomMethode, paramTypes);
        return m.invoke(obj, args);
    }
}
