package com.tcs.sgv.eis.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class DBsysdateConfiguration {
    private static ResourceBundle constants = ResourceBundle.getBundle("resources/reports/SGVConstantsReports");
	
	
	private static String dbPropertyFile = constants.getString("DBPROPERTYFILE");
    private static ResourceBundle dbPropFileRB =  ResourceBundle.getBundle(dbPropertyFile);
	private static String dbTypeKEY = constants.getString("DBTYPE_KEY");
	private static String dbType = dbPropFileRB.getString(dbTypeKEY);

	public String getSysdate()
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = "sysdate()";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = "sysdate";
			return dateQuery;
	}
	public String getNCHAR()
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = "''";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = "to_nchar('')";
			return dateQuery;
	}

	public String getNVL()
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = "IFNULL";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = "NVL";
			return dateQuery;
	}
	public SimpleDateFormat GetDateFormat()
	{
		    SimpleDateFormat sdf = new SimpleDateFormat();
			if((dbType.trim()).equalsIgnoreCase("mysql"))			sdf = new SimpleDateFormat("yyyy-MM-dd");
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	sdf = new SimpleDateFormat("dd/MMM/yyyy");;
			return sdf;
	}
	public String to_char(String date)
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = " date_format(" + date + " , '%d/%m/%y') ";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = "to_char(" + date + ", 'dd/mm/yyyy')";
			return dateQuery;
	}
	public String to_nchar(String str)
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = "'" + str + "'";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = "to_nchar('" + str + "')";
			return dateQuery;
	}
	
	
	public String to_getmonth(String date)
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = "date_format(" + date + " , '%M') ";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = "to_char(to_date(" + date + ", 'dd/MM/yyyy'), 'MONTH')";
			return dateQuery;
	}
	
	public boolean isMySQL()
	{
			boolean isMySQL = false;
		    if((dbType.trim()).equalsIgnoreCase("mysql"))		
		    	isMySQL = true;
		    
		    return isMySQL;
	}
	
	public String getRowNum()
	{
		String rowNum="";
		if((dbType.trim()).equalsIgnoreCase("mysql"))			rowNum = " limit ";
		else if((dbType.trim()).equalsIgnoreCase("oracle")) 	rowNum = " and rownum = ";// "and" not allowed in mysql for limit, so kept  here dont use "and" in dao
		return rowNum;
	}
	public String getFirstDateOfCurrentMonth()
	{
		SimpleDateFormat sdf = GetDateFormat();
		Calendar cal = Calendar.getInstance();
		//getting first day of month
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDay=cal.getTime();//First day of month
		
		return sdf.format(firstDay); // FORMAT OF RETURNING DATE IS DD/MMM/YYYY
	}
	
	public String getLastDateOfCurrentMonth()
	{
		SimpleDateFormat sdf = GetDateFormat();
		Calendar cal = Calendar.getInstance();
		 //getting last day of month
        cal.add(Calendar.MONTH,1); 
        cal.set(Calendar.DAY_OF_MONTH,1); 
        cal.add(Calendar.DATE,-1);   // last day of month. 
        Date lastDay=cal.getTime();
		
		return sdf.format(lastDay);  // FORMAT OF RETURNING DATE IS DD/MMM/YYYY
	}
	public String getNullValue(String  name)
	{
			String dateQuery = "";
			if((dbType.trim()).equalsIgnoreCase("mysql"))			dateQuery = "ifnull(" + name + ",'')";
			else if((dbType.trim()).equalsIgnoreCase("oracle")) 	dateQuery = name;
			return dateQuery;
	}
	
}
