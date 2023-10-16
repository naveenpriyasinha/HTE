package com.tcs.sgv.eis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


/*IMPORTING COM CLASSES*/



public class DBConnection
{
	//private static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static ResourceBundle constants = ResourceBundle.getBundle("resources/reports/SGVConstantsReports");
	private static Log logger = LogFactory.getLog(DBConnection.class);

	
	private static String dbPropertyFile = constants.getString("DBPROPERTYFILE");
    private static ResourceBundle dbPropFileRB =  ResourceBundle.getBundle(dbPropertyFile);
    
    // Getting Keys from SGVConstantsReports...
	private static String driverKey = constants.getString("JDBC_DRIVER_KEY");
	private static String dburlKey = constants.getString("DBURL_KEY");
	private static String dbuserKey = constants.getString("DBUSER_KEY");
	private static String passwordKey = constants.getString("PASSWORD_KEY");
	private static String dbTypeKEY = constants.getString("DBTYPE_KEY");
	private static String mysqlDBdateQueryKEY = constants.getString("MYSQL.DBDATE_QUERY_KEY");
	private static String oracleDBdateQueryKEY = constants.getString("ORACLE.DBDATE_QUERY_KEY");
	
	// Getting values from ApplicationDB...
	private static String driver = dbPropFileRB.getString(driverKey);
	private static String dburl = dbPropFileRB.getString(dburlKey); 
	private static String dbuser = dbPropFileRB.getString(dbuserKey);
	private static String password = dbPropFileRB.getString(passwordKey);
	private static String dbType = dbPropFileRB.getString(dbTypeKEY);
	private static String mysqlDBdateQuery = dbPropFileRB.getString(mysqlDBdateQueryKEY);
	private static String oracleDBdateQuery = dbPropFileRB.getString(oracleDBdateQueryKEY);
	
	private static String dateQuery = "";
	
	static
	{	
		if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = mysqlDBdateQuery;
		else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = oracleDBdateQuery;
	}
    
	public static Connection getConnection()
	{
		Connection lCon =null;
		try {
            // Create the SessionFactory from hibernate.cfg.xml
//            sessionFactory = new Configuration().configure().buildSessionFactory();
            
            Class.forName(driver.trim());
            lCon = DriverManager.getConnection(dburl.trim(), dbuser.trim(), password.trim());            
            
            //Session session = sessionFactory.openSession();
            //lCon = session.connection();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        }

			
		return lCon;
	}
	
    public static void closeConnection(Connection con)
    {
        try
        {
            if(con != null && !con.isClosed())
            {
                con.close();
                con = null;
            }
        }
        catch(SQLException sqlex)
        {
            logger.debug("Sql exception While Closing the connection  ", sqlex);
        }
        catch(Exception genExp)
        {
            logger.debug("General exception Not able to close connection  ", genExp);
        }
    }

    public static void closeStatement(Statement stmt)
    {
        try
        {
            if(stmt != null)
            {
                stmt.close();
                stmt = null;
            }
        }
        catch(SQLException sqlex)
        {
            logger.debug("Sql exception While Statement the Statement  ", sqlex);
        }
        catch(Exception genExp)
        {
            logger.debug("General exception Not able to close Statement  ", genExp);
        }
    }

    public static void closeResultSet(ResultSet rs)
    {
        try
        {
            if(rs != null)
            {
                rs.close();
                rs = null;
            }
        }
        catch(SQLException sqlex)
        {
            logger.debug("Sql exception While Closing the resultset  ", sqlex);
        }
        catch(Exception genExp)
        {
            logger.debug("General exception Not able to close resultset  ", genExp);
        }
    }
   


/* change for cmo specific connectivity start */

/*
    public static Connection getCMODriverManagerConnection()
    {
        String sDriver = "";
        String sJdbcUrl = "";
        String sUserName = "";
        String sPassword = "";
        try
        {
            ResourceBundle bundle = ResourceBundle.getBundle("SGVConstants");
            sDriver = "oracle.jdbc.driver.OracleDriver";
            oslogger.info("Getting connection from driver manager");
            sJdbcUrl = bundle.getString("CMOJDBC_URL");
            sUserName = bundle.getString("CMOUSER");
            sPassword = bundle.getString("CMOPASSWORD");
            Class cClass = Class.forName(sDriver);
            oslogger.debug(" User name : " + sUserName + "  Password : " + sPassword + " Url : " + sJdbcUrl);
            if(!sDriver.equals(bundle.getString("DRIVER")))
                DriverManager.registerDriver((Driver)cClass.newInstance());
            conml = DriverManager.getConnection(sJdbcUrl, sUserName, sPassword);
        }
        catch(IllegalAccessException exception)
        {
            oslogger.error("Error in JDBC connection:" + exception.getMessage());
        }
        catch(ClassNotFoundException exception)
        {
            oslogger.error("Error in JDBC connection:" + exception.getMessage());
        }
        catch(InstantiationException exception)
        {
            oslogger.error("Error in JDBC connection:" + exception.getMessage());
        }
        catch(SQLException exception)
        {
            oslogger.error("Error in JDBC connection:" + exception.getMessage());
        }
        catch(Exception exception)
        {
            oslogger.error("Error in JDBC connection:" + exception.getMessage());
        }
        return conml;
    }

*/
   

}