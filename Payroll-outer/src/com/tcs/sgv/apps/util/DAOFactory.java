/**
 * PACKAGE      : com.tcs.sgv.apps.util
 * FILENAME     : DAOFactory.java
 * @VERSION     : 1.0
 * @AUTHOR      : Keyur
 * DATE         : 2/12/2005
 * REV.HISTORY  : 
 * --------------------------------------------------------------
 *    DATE          AUTHOR          DESCRIPTION
 *    
 * --------------------------------------------------------------
 */

/**
 * com.tcs.sgv.apps.util.DAOFactory class is used to get the Object of the Class based on the passed parameter.
 * 
 */

package com.tcs.sgv.apps.util;

public class DAOFactory 
{
    public static Object Create( String strName )
    {
        Class obj = null;
        Object objClass = null;

        try
        {
            obj = Class.forName(strName);
            objClass = obj.newInstance();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return objClass;
    }
}