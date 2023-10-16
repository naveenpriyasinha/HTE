package com.tcs.sgv.eis.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;



public class QueryExecuterServiceImpl extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());	
    int msg;
  //  ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationDB");
    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationDB");
    
    public ResultObject loadQueryExecuter(Map objectArgs) throws Exception
    {
    	logger.info("Inside Query Executer Service");
    	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
    	
    	msg = 0;
    	try{
    		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
    		Map loginMap = (Map) objectArgs.get("baseLoginMap");
    		
    		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
    		logger.info("locId in loadQueryExecuter is: "+locId);
    		
    		String lStrQueryString = "";
    		List lQueryResultList = new ArrayList();
    		List<String> lColumnNameList = new ArrayList<String>();
    		int lLongTotalColumns = 0; 
    		Class.forName( "com.ibm.db2.jcc.DB2Driver" ); 
    		String lStrURL = resourceBundle.getString("connectionUrl");
    		String lStrUserName = "";
    		String lStrPassword = "";
    		
    		/*if(lStrURL.toUpperCase().indexOf("STAG") != -1){
    			lStrUserName = resourceBundle.getString("rosusername");
    			lStrPassword = resourceBundle.getString("rospassword");
    		}
    		else{
    			lStrUserName =resourceBundle.getString("rousername");
        		lStrPassword =resourceBundle.getString("ropassword");
    		}*/
    		if(lStrURL != null && lStrURL.length()>0){
    			lStrUserName = "ifmsuser";
    			lStrPassword = "ifms123";
    		}
    		    			
    		ResultSet rs = null;
    		boolean isValidateToExecute = false;
    		
    		logger.info("Connection Creation is Set");
    		
    		if(StringUtility.getParameter("hiddenQuery",request)!=null && StringUtility.getParameter("hiddenQuery",request)!="" )
    		{
    			lStrQueryString = (StringUtility.getParameter("hiddenQuery",request)!=null&&!(StringUtility.getParameter("hiddenQuery",request).equals(""))?(StringUtility.getParameter("hiddenQuery",request)):"");
    			logger.info("Query Entered is -->"+lStrQueryString);
    		}
			
    		
    		if(lStrQueryString != null && lStrQueryString.length() > 0 && lStrQueryString != ""){  	
    			if(lStrQueryString.trim().toUpperCase().startsWith(("SELECT")))
    				isValidateToExecute = true;
    		}
    		
    		if(isValidateToExecute){
    			Connection con = DriverManager.getConnection( lStrURL, lStrUserName, lStrPassword ); 
    			Statement statement = con.createStatement( ); 
    			logger.info("Statement Created And Executing Query");
    			statement.setMaxRows(500);
    			rs = statement.executeQuery((lStrQueryString));
    			logger.info("Query Executed");
    			ResultSetMetaData rsm = rs.getMetaData();
    			lLongTotalColumns = rsm.getColumnCount();
    			logger.info("Total Number of Columns Fetched-->"+lLongTotalColumns);
    			for(int lLongRSMIndex=1;lLongRSMIndex <= lLongTotalColumns;lLongRSMIndex++)
    				lColumnNameList.add(rsm.getColumnName(lLongRSMIndex));
    		 
    			//	Preparing a List from RecordSet
    			while(rs.next()){
    				for(int lColumnIndex = 1 ;lColumnIndex <= lLongTotalColumns;lColumnIndex++){
    					if(rs.getString(lColumnIndex) != null && !rs.getString(lColumnIndex).equalsIgnoreCase("") && !rs.getString(lColumnIndex).equalsIgnoreCase(" "))
    						lQueryResultList.add(rs.getString(lColumnIndex));
    					else
    						lQueryResultList.add("< null >");
    				}    				
    			}
    			
    			/*		Display Purpose
    			 * String lStrTempColumnValue = "";
    			for(int i = 0; i < lQueryResultList.size();){
        			for(int j=0;j<lLongTotalColumns;j++){
        				if(lQueryResultList.get(j) != null && !lQueryResultList.get(j).equals("")){
        					lStrTempColumnValue = lQueryResultList.get(j).toString();
        					System.out.println("Result is-->"+lStrTempColumnValue);
        				}
        				else
        				{
        					System.out.println("Result is--><null>");
        				}
        				i++;
        			}
        			System.out.println("\n");
        		}*/
    			objectArgs.put("totalRows", (lQueryResultList.size()/lLongTotalColumns));
    			objectArgs.put("columnNameList", lColumnNameList);
        		objectArgs.put("resultList", lQueryResultList);
        		objectArgs.put("totalColumns", lLongTotalColumns);
        		statement.close();
    			con.close();
    			objectArgs.put("msg", "1");
    		}
    		else
    			objectArgs.put("msg", "0");
    		
    	   	
    		
    		
    		objectArgs.put("queryString", lStrQueryString);
    		resultObject.setResultCode(ErrorConstants.SUCCESS);
    		resultObject.setResultValue(objectArgs);//put in result object
    		resultObject.setViewName("QueryExecuter");//set view name
    		logger.info("Successfully Leaving Query Executer Service");
    	}
    	catch(Exception e){
    		resultObject = new ResultObject(ErrorConstants.ERROR);
    		resultObject.setResultCode(-1);
    		objectArgs.put("msg", "0");
			resultObject.setViewName("errorPage");
			logger.error(e);			
    	}
    	return resultObject;
    }

}
