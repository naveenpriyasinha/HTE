package com.tcs.sgv.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IWDMSDBConnection
{
    private static ResourceBundle constants = ResourceBundle.getBundle("resources/onlinebillprep/SGVConstantsIWDMS");
    private static Log logger = LogFactory.getLog(DBConnection.class);
    private static String driver = constants.getString("JDBCDriver");
    private static String dburl = constants.getString("DBUrl");
    private static String dbuser = constants.getString("DBUser");
    private static String password = constants.getString("Password");
    
    public IWDMSDBConnection()
    {
        
    }
    
    public static Connection getConnection()
    {
        Connection lCon = null;
        
        try
        {
            Class.forName(driver);
            lCon = DriverManager.getConnection(dburl, dbuser, password);
        }
        catch(Throwable ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        return lCon;
    }
}
