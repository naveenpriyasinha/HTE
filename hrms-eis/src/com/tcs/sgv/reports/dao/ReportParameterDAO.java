package com.tcs.sgv.reports.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valuebeans.reports.DefaultComboItem;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
@SuppressWarnings({"unchecked"})
public class ReportParameterDAO {	
	
	/**
	 * Method Description:- This Method will fetch all Resources names in the combo box
	 * while generating report.
	 * Made By: Urvin shah.
	 */
	
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/Payroll");
	public final long BILL_TYPE_ID=Integer.parseInt(resourceBundle.getString("bill_type_id"));
	public final long OnlypayBIll_BILL_TYPE_ID=Integer.parseInt(resourceBundle.getString("OnlyPaybill_bill_type_id"));
	public final int FIN_YEAR_ID=Integer.parseInt(constantsBundle.getString("FIN_YEAR_ID")); //Financial Year for Budget Heads.
	public ArrayList getEmployeeName(String lstrLangId, String lstrLocId){
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select emp.emp_id as EmployeeId,org.emp_fname || ' '||org.emp_mname||' ' || org.emp_lname EmployeeName ");
        	lSb.append("from hr_eis_emp_mst emp,org_emp_mst org where org.emp_id = emp.emp_mpg_id order by org.emp_fname,org.emp_mname,org.emp_lname");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("EmployeeId"),lRs.getString("EmployeeName")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getEmployeeNamefromDept(String locid,Hashtable adtnlArgs,String lstrLangId, String lstrLocId){
		 Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locationId=(Long)loginDetails.get("locationId");
		
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
       	if(locid!=null)
       		locationId=Long.parseLong(locid);
       		

		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	
        	lSb.append(" select e.emp_id as EmployeeId, ");
        	lSb.append(" concat(concat(concat(concat(o.emp_fname,' '),o.emp_mname),' '), o.emp_lname) ");
        	lSb.append(" EmployeeName ");
        	lSb.append(" from org_emp_mst          o, ");
        	lSb.append(" org_post_details_rlt p, ");
        	lSb.append(" org_userpost_rlt     up, ");
        	lSb.append(" hr_eis_emp_mst       e ");
        	lSb.append(" where o.user_id = up.user_id and up.post_id = p.post_id and ");
        	lSb.append(" p.loc_id = "+locationId+" and e.emp_mpg_id = o.emp_id and o.lang_id = 1 and ");
        	lSb.append(" p.lang_id = 1  order by o.emp_fname,o.emp_mname,o.emp_lname  ");     	
        	
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("EmployeeId"),lRs.getString("EmployeeName")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getReportType(String lstrLangId, String lstrLocId)
	{
		
			String LoanRecoverType=constantsBundle.getString("LoanRecoverType");
			ArrayList lCmbList=new ArrayList();		
			Connection lCon = null;
			Logger logger = Logger.getLogger(ReportParameterDAO.class);
			StringBuffer lSb = new StringBuffer();
	        PreparedStatement lPStmt = null;
	        ResultSet lRs = null;
	        //ArrayList lCmbList = new ArrayList();		
			try {	           
	            lCon = DBConnection.getConnection();
	        }
	        catch(Exception e) {
	            logger.error("Exception in Connecting to database " + e);
	        }
	        try {          
	        	lSb.append("select cm.lookup_id,cm.lookup_desc from cmn_lookup_mst cm where cm.parent_lookup_id="+LoanRecoverType);
	            lPStmt = lCon.prepareStatement(lSb.toString());
	            logger.info(" The Executed Query is === "+lSb.toString());
	            lRs = lPStmt.executeQuery();
	            while(lRs.next()) {
	            	
	            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("lookup_id")+"~"+lRs.getString("lookup_desc"),lRs.getString("lookup_desc")); 
	            	lCmbList.add(ci1);           
	            }          
	        }
	        catch(SQLException se)
	        {
	            logger.error("Error is: "+ se.getMessage());
	            logger.error("Combo Details Not Found " + se);
	        }
	        finally {
	            try {
	                //close the resultset if not null
	                if (lRs != null) {
	                	lRs.close();
	                }

	                //close the statement if not null 
	                if (lPStmt != null) {
	                	lPStmt.close();
	                }

	                //close the connection if not null  
	                if (lCon != null) {
	                	lCon.close();
	                }
	            } catch (Exception e) {
	                logger.error("Error in closing connection " + e);
	            }
	        }
	    return lCmbList;	       
	  }	
	//ResourceBundle resourceBundle = constantsBundle.getBundle("resources.Payroll");
	
	public ArrayList getLoanType(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();	
		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( constantsBundle.getString("hbaForLand"),"HBA For Land"); 
    	lCmbList.add(ci1); 
    	ci1 = new DefaultComboItem( constantsBundle.getString("hba"),"HBA House"); 
    	lCmbList.add(ci1); 
	    ci1 = new DefaultComboItem(constantsBundle.getString("mca"),"MCA"); 
    	lCmbList.add(ci1); 	
		/*ci1 = new DefaultComboItem( constantsBundle.getString("car"),"Car"); 
	    	lCmbList.add(ci1);
	    ci1 = new DefaultComboItem( constantsBundle.getString("scooter"),"Scooter"); 
	    	lCmbList.add(ci1); 
		    ci1 = new DefaultComboItem(constantsBundle.getString("moped"),"Moped"); 
	    	lCmbList.add(ci1); 	    	
		    ci1 = new DefaultComboItem(constantsBundle.getString("moped"),"Moped"); 
	    	lCmbList.add(ci1); */	    	
		    ci1 = new DefaultComboItem(constantsBundle.getString("festival"),"Festival Adv"); 
	    	lCmbList.add(ci1); 	    	
		   /* ci1 = new DefaultComboItem(constantsBundle.getString("foodGrain"),"Food Adv"); 
	    	lCmbList.add(ci1); */	    	
	    return lCmbList;
	  }	
	
	public ArrayList getAdvanceType(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem("",""); 
		ci1 = new DefaultComboItem( constantsBundle.getString("festival"),"Festival"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( constantsBundle.getString("foodGrain"),"FoodGrain"); 
	    	lCmbList.add(ci1);
	    return lCmbList;
	  }	
	
	public ArrayList getGrades(String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select grade.grade_id, grade.grade_name ");
        	lSb.append("  from org_grade_mst grade ");
        	lSb.append(" where grade.lang_id = '1' ");
        	lSb.append("  order by grade.grade_id ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("grade_id"),lRs.getString("grade_name")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getScales(String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select a.scale_id, ");
        	lSb.append("  concat(concat(concat(concat(a.scale_start_amt,'-'),a.scale_incr_amt),'-'),a.scale_end_amt) scale,concat(concat(concat('-',scale_higher_incr_amt),'-'),scale_higher_end_amt) higher_scale");
        	lSb.append("  from hr_eis_scale_mst a  ");
        	lSb.append("  order by a.scale_start_amt ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("scale_id"),lRs.getString("scale")+((lRs.getString("higher_scale")!=null && !lRs.getString("higher_scale").equals("-0-0"))?lRs.getString("higher_scale"):"")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }
	
	public ArrayList getDepartments(Hashtable adtnlArgs,String lstrLangId, String lstrLocId)
	{

		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locId=(Long)loginDetails.get("locationId");
		logger.info("locId::"+locId);		
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select dept.loc_id, ");
        	lSb.append("  dept.loc_name ");
        	lSb.append("  from cmn_location_mst dept  where dept.lang_id = 1 and dept.loc_id="+locId);
        	lSb.append("  order by dept.loc_name ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("loc_id"),lRs.getString("loc_name"));
            	logger.info("Inside parameter DAO setting dept name and id");
            	logger.info("Dept name is " + lRs.getString("loc_name") + " , id is " + lRs.getString("loc_id"));
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	public ArrayList getYear(String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select b.lookup_name ");
        	lSb.append(" from cmn_lookup_mst a, cmn_lookup_mst b ");
        	lSb.append(" where a.lookup_name = 'Year' and a.lookup_id = b.parent_lookup_id and ");
        	lSb.append(" a.lang_id = 1 and b.lang_id = 1 ");
        	lSb.append(" order by b.order_no");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("lookup_name"),lRs.getString("lookup_name")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	}
	public ArrayList getMonth(String lstrLangId, String lstrLocId)
	{
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "1","January"); 
    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "2","February"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "3","March"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "4","April"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "5","May"); 
		 	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "6","June"); 
		 	lCmbList.add(ci1);          
		 ci1 = new DefaultComboItem( "7","July"); 
			lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "8","August"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "9","September"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "10","October"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "11","November"); 
		 	lCmbList.add(ci1);           
	 	 ci1 = new DefaultComboItem( "12","December"); 
	 		lCmbList.add(ci1);           
     return lCmbList;	       

	}
	public ArrayList GroupBy(String lstrLangId, String lstrLocId)
	{
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "Class","Class"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "Designation","Designation"); 
	    	lCmbList.add(ci1);           
		 ci1 = new DefaultComboItem( "Scale","Scale"); 
	    	lCmbList.add(ci1);           
	     return lCmbList;	       

	}
	public ArrayList getDesignations(String locId,Hashtable adtnlArgs,String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" SELECT desig.DSGN_ID,desig.DSGN_NAME FROM ORG_DESIGNATION_MST desig,HR_EIS_GD_MPG gd where  ");
        	lSb.append("    gd.SGD_DESIG_ID = desig.DSGN_ID  and gd.LOC_ID = ?  and desig.LANG_ID = 1 order by desig.DSGN_NAME ");
        	/*lSb.append("    where desig.lang_id = 1 and ");
        	lSb.append("  desig.dsgn_id in	(select gd.sgd_desig_id  from hr_eis_gd_mpg gd )");
        	lSb.append("   order by desig.dsgn_name ");*/
        	logger.info("Location id in reporVO"+locId);
            lPStmt = lCon.prepareStatement(lSb.toString());
            lPStmt.setLong(1, Long.parseLong(locId));
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("DSGN_ID"),lRs.getString("DSGN_NAME")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getbank(String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select b.bank_id,b.bank_name  ");
        	lSb.append("    from hr_eis_bank_mst b ");
        	lSb.append("     where b.lang_id = 1 ");
        	lSb.append("    order by b.bank_name ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("bank_id"),lRs.getString("bank_name"));  
            	lCmbList.add(ci1);           
            } 
/*            DefaultComboItem ci1 = new DefaultComboItem( constantsBundle.getString("nagrikBank"),"Nagrik Bank"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("karmcharyBank"),"Karmchary Bank"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("societyOld"),"Society Old"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("society"),"Society"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("LIC"),"LIC"); 
            lCmbList.add(ci1);*/
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }
	
// Added by Rajan for edit Non Government deduction report on 29 aug
	
	public ArrayList getNonGovBankDetails(String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
       /* 	lSb.append(" select b.bank_id,b.bank_name  ");
        	lSb.append("    from hr_eis_bank_mst b ");
        	lSb.append("     where b.lang_id = 1 ");
        	lSb.append("    order by b.bank_name ");*/
        	lSb.append(" select d.deduc_name ");
        	lSb.append(" from hr_pay_deduc_type_mst d");
        	lSb.append("  where d.deduc_type=300160 and d.deduc_code < 68");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            //System.out.println("QUERY---"+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("deduc_name"),lRs.getString("deduc_name"));  
            	lCmbList.add(ci1);           
            } 
     /*       DefaultComboItem ci1 = new DefaultComboItem( constantsBundle.getString("nagrikBank"),"Nagrik Bank"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("karmcharyBank"),"Karmchary Bank"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("societyOld"),"Society Old"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("society"),"Society"); 
            lCmbList.add(ci1);
            ci1 = new DefaultComboItem( constantsBundle.getString("LIC"),"LIC"); 
            lCmbList.add(ci1);*/
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }
	
	public ArrayList getBranch(String bankId,String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append("select branch_id,micr_code,branch_name,bm.bank_name from hr_eis_branch_mst br, hr_eis_bank_mst bm where br.branch_bank_id=bm.bank_id and bm.bank_id="+bankId+" order by branch_name ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("branch_id")+"~"+lRs.getString("micr_code")+"~"+lRs.getString("branch_name")+"~"+lRs.getString("bank_name"),lRs.getString("branch_name"));  
            	lCmbList.add(ci1);           
            } 
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getReportTypes(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "PayRec","Pay Of Recovery details"); 
    	lCmbList.add(ci1);          
		ci1 = new DefaultComboItem( "Misc","Miscelleneous Recovery details"); 
    	lCmbList.add(ci1); 
		ci1 = new DefaultComboItem( "Insurance","New Insurance scheme 1981 deduction"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "PT","PT deduction report"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "Deduction","Deduction Report"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "IT","Income Tax deduction report"); 
		//Added By Mrugesh
		lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "SIS1979","SIS-GIS 1979"); 
		//Ended By Mrugesh

    	lCmbList.add(ci1);           
	    return lCmbList;	       
	  }	
	
	public ArrayList getquarters(String lstrLangId, String lstrLocId)
	{
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select b.quater_id,b.qua_type  ");
        	lSb.append("    from hr_quater_type_mst b ");
        	lSb.append("     where b.lang_id = 1 ");
        	lSb.append("    order by b.quater_id ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is \n\t\t "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("quater_id"),lRs.getString("qua_type")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getPrintType(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "Allowances","Allowances"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem("Deductions","Deductions"); 
	    	lCmbList.add(ci1);
	    return lCmbList;
	  }	
	
	public ArrayList getDemandNoByLocId(String LocId,String lstrLangId, String lstrLocId) 
	{			
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer query = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        lstrLangId="en_US";//hardcoded
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
			query.append("select a.demand_id,a.demand_code from sgva_buddemand_mst a where a.bpncode in ");
	        query.append("(select g.bpncode from sgva_budbpn_mapping g where g.dept_id in ");
	        query.append("(select t.department_id from rlt_location_department t where t.loc_code='");
	        query.append(LocId).append( "')) and ");   
	        query.append("a.lang_Id = '").append(lstrLangId).append("' and a.fin_yr_id=" + FIN_YEAR_ID+" order by a.demand_code ");
	        logger.info(" The Executed Query is \n"+query.toString());
	        lPStmt = lCon.prepareStatement(query.toString());
	        lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("demand_code"),lRs.getString("demand_code")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	}

	public ArrayList getMjrHeadByDemandNo(String demandNo,String lstrLangId, String lstrLocId) 
	{			
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer query = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        lstrLangId="en_US";//hardcoded
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
	        query.append("select b.budmjrhd_id,b.budmjrhd_code from sgva_budmjrhd_mst b where b.demand_code='").append(demandNo); 
	        query.append("' and b.lang_id='").append(lstrLangId).append("' and b.fin_yr_id=").append(FIN_YEAR_ID);
	        logger.info(" The Executed Query is \n "+query.toString());
	        lPStmt = lCon.prepareStatement(query.toString());
	        lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( demandNo+"~"+lRs.getString("budmjrhd_code"),lRs.getString("budmjrhd_code")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	}


	public ArrayList getSubMjrHeadByMjrHead(String demandNoandmjrHead_Code,String lstrLangId, String lstrLocId) 
	{			
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer query = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        lstrLangId="en_US";//hardcoded
        ArrayList lCmbList = new ArrayList();	
        String demandNo="";
        String mjrHead_Code="";
        StringTokenizer st=new StringTokenizer(demandNoandmjrHead_Code,"~");
        int i=0;
        while(st.hasMoreTokens())
        {
        	if(i==0)
        	demandNo=st.nextToken();
        	else
        	mjrHead_Code=st.nextToken();	
         i++;
        } 
       		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
			query.append("select q.budsubmjrhd_id,q.budsubmjrhd_code from sgva_budsubmjrhd_mst q where q.demand_code='").append(demandNo); 
			query.append("' and q.budmjrhd_code='").append(mjrHead_Code).append("' and q.lang_id='").append(lstrLangId).append("' and q.fin_yr_id=").append(FIN_YEAR_ID);
	        logger.info(" The Executed Query is \n "+query.toString());
	        lPStmt = lCon.prepareStatement(query.toString());
	        lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( demandNoandmjrHead_Code+"~"+lRs.getString("budsubmjrhd_code"),lRs.getString("budsubmjrhd_code")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	}

	public ArrayList getMnrHeadByMjrHead(String demNomjrsubnmjrHeadCode,String lstrLangId, String lstrLocId) 
	{			
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer query = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        lstrLangId="en_US";//hardcoded
        ArrayList lCmbList = new ArrayList();	
        String demandNo="";
        String mjrHead_Code="";
        String subMjrHeadCode="";
        StringTokenizer st=new StringTokenizer(demNomjrsubnmjrHeadCode,"~");
        int i=0;
        while(st.hasMoreTokens())
        {
        	if(i==0)
        	demandNo=st.nextToken();
        	else if(i==1)
        	mjrHead_Code=st.nextToken();
        	else
        	subMjrHeadCode=st.nextToken();	
         i++;
        } 
       		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
			query.append("select h.budminhd_id,h.budminhd_code from sgva_budminhd_mst h where  h.demand_code='").append(demandNo).append("' and ");
			query.append(" h.budmjrhd_code='").append(mjrHead_Code).append("' and " );
	        query.append("h.budsubmjrhd_code='").append(subMjrHeadCode).append("' and h.lang_id='").append(lstrLangId).append("' and h.fin_yr_id=").append(FIN_YEAR_ID);
	        logger.info(" The Executed Query is \n "+query.toString());
	        lPStmt = lCon.prepareStatement(query.toString());
	        lRs = lPStmt.executeQuery();
	        while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem(demNomjrsubnmjrHeadCode+"~"+ lRs.getString("budminhd_code"),lRs.getString("budminhd_code")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	}

	public ArrayList getSubHeadByMnrHead(String demNomjrsubnmjrmnrHeadCode, String lstrLangId, String lstrLocId) 
	{			
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer query = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        lstrLangId="en_US";//hardcoded
        ArrayList lCmbList = new ArrayList();	
        String demandNo="";
        String mjrHead_Code="";
        String subMjrHeadCode="";
        String mnrHeadCode="";
        StringTokenizer st=new StringTokenizer(demNomjrsubnmjrmnrHeadCode,"~");
        int i=0;
        while(st.hasMoreTokens())
        {
        	if(i==0)
        	demandNo=st.nextToken();
        	else if(i==1)
        	mjrHead_Code=st.nextToken();
        	else if(i==2)
        	subMjrHeadCode=st.nextToken();
        	else
        	mnrHeadCode=st.nextToken();
         i++;
        } 
       		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
			query.append("select a.budsubhd_id ,a.budsubhd_code from sgva_budsubhd_mst a where a.demand_code='").append(demandNo); 
			query.append("' and a.budmjrhd_code='").append(mjrHead_Code).append("' and a.budsubmjrhd_code='").append(subMjrHeadCode).append("' ");
	        query.append(" and a.budminhd_code='").append(mnrHeadCode).append("' and a.lang_id='").append(lstrLangId).append("' and a.fin_yr_id=").append(FIN_YEAR_ID);
	        logger.info(" The Executed Query is \n "+query.toString());
	        lPStmt = lCon.prepareStatement(query.toString());
	        lRs = lPStmt.executeQuery();
	        while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem(demNomjrsubnmjrmnrHeadCode+"~"+ lRs.getString("budsubhd_code"),lRs.getString("budsubhd_code")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	}

	
	public ArrayList getCategory(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "AIS","IAS/AIS"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem("Gadgeted","Gadgeted"); 
    	lCmbList.add(ci1);
		ci1 = new DefaultComboItem("Non-Gadgeted","Non-Gadgeted"); 
    	lCmbList.add(ci1);
		ci1 = new DefaultComboItem("Custom","Custom"); 
    	lCmbList.add(ci1);
	    return lCmbList;
	  }	
	
	public ArrayList getClasssIVType(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "ClassIV","Class IV"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem("otherThanIV","Other Than Class IV"); 
    	lCmbList.add(ci1);
		//ci1 = new DefaultComboItem("IAS","IAS"); 
    	//lCmbList.add(ci1);

	    return lCmbList;
	  }	

	
	
	public ArrayList getFormbType(String lstrLangId, String lstrLocId)
	{
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "FA","FA"); 
    	lCmbList.add(ci1);   
    	
		ci1 = new DefaultComboItem("TA","TA"); 
    	lCmbList.add(ci1);
    	
		ci1 = new DefaultComboItem("PA","PA"); 
    	lCmbList.add(ci1);

	    return lCmbList;
	  }
	
	
	
	//This method is GAD sepecific and so is the table
	/*public ArrayList getBillNos(String locid,Hashtable adtnlArgs,String lstrLangId, String lstrLocId){
		//System.out.println("in bill  no method"+locid);
		
		//System.out.println("in bill  no method");
		//added By samir Joshi for location Specific
		 Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locationId=(Long)loginDetails.get("locationId");
		//ended
		ArrayList paraList=new ArrayList();		
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {
        	if(locid!=null)
            	lSb.append(" select bill.bill_no,bill.subhead_code,bill.CLASS_ID,bill.DSGN_ID,bill.post_type from hr_pay_bill_subhead_mpg bill where bill.loc_id='"+ locid+"'  order by  bill.bill_no");
        	else
            	lSb.append(" select bill.bill_no,bill.subhead_code,bill.CLASS_ID,bill.DSGN_ID,bill.post_type from hr_pay_bill_subhead_mpg bill where bill.loc_id='"+locationId+"'  order by  bill.bill_no");

            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("subhead_code")+"~"+lRs.getString("CLASS_ID")+"~"+lRs.getString("DSGN_ID")+"~"+(lRs.getString("post_type")!=null?lRs.getString("post_type"):" ")+"~"+lRs.getString("bill_no"),lRs.getString("bill_no")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	*/
	
 
	/*public ArrayList getBillNos(String locid,Hashtable adtnlArgs,String lstrLangId, String lstrLocId){
		//System.out.println("in bill  no method"+locid);
		
		//System.out.println("in bill  no method");
		//added By samir Joshi for location Specific
		 Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locationId=(Long)loginDetails.get("locationId");
		//ended
		ArrayList paraList=new ArrayList();		
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {

        	if(locid!=null)
            	lSb.append(" select bill.BILL_GROUP_ID,bill.DDO_CODE,bill.DESCRIPTION,bill.SCHEME_NAME,bill.SCHEME_CODE from mst_dcps_bill_group bill where bill.loc_id='"+ locid+"'  order by  bill.BILL_GROUP_ID");
        	else
            	lSb.append(" select bill.BILL_GROUP_ID,bill.DDO_CODE,bill.DESCRIPTION,bill.SCHEME_NAME,bill.SCHEME_CODE from mst_dcps_bill_group bill where bill.loc_id='"+locationId+"'  order by  bill.BILL_GROUP_ID");

            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("DDO_CODE")+"~"+lRs.getString("DESCRIPTION")+"~"+lRs.getString("SCHEME_NAME")+"~"+(lRs.getString("SCHEME_CODE")!=null?lRs.getString("SCHEME_CODE"):" ")+"~"+lRs.getString("BILL_GROUP_ID"),lRs.getString("DESCRIPTION")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
        */
	public ArrayList getBillNos(String locid,Hashtable adtnlArgs,String lstrLangId, String lstrLocId){
		//System.out.println("in bill  no method"+locid);
		
		//System.out.println("in bill  no method");
		//added By samir Joshi for location Specific
		 Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locationId=(Long)loginDetails.get("locationId");
		//ended
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {

        	if(locid!=null)
            	lSb.append(" select bill.BILL_GROUP_ID,bill.DDO_CODE,bill.DESCRIPTION,bill.SCHEME_NAME,bill.SCHEME_CODE from mst_dcps_bill_group bill where bill.loc_id='"+ locid+"'  order by  bill.DESCRIPTION ASC");
        	else
            	lSb.append(" select bill.BILL_GROUP_ID,bill.DDO_CODE,bill.DESCRIPTION,bill.SCHEME_NAME,bill.SCHEME_CODE from mst_dcps_bill_group bill where bill.loc_id='"+locationId+"'  order by  bill.DESCRIPTION ASC");

            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("DDO_CODE")+"~"+lRs.getString("DESCRIPTION")+"~"+
            			//lRs.getString("SCHEME_NAME")+"~"+(lRs.getString("SCHEME_CODE")!=null?lRs.getString("SCHEME_CODE"):" ")
            			(lRs.getString("SCHEME_CODE")!=null?lRs.getString("SCHEME_CODE"):" ")+"~"+(lRs.getString("SCHEME_CODE")!=null?lRs.getString("SCHEME_CODE"):" ")
            			+"~"+lRs.getString("BILL_GROUP_ID"),lRs.getString("DESCRIPTION")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }	
	
	public ArrayList getCity(String lstrLangId, String lstrLocId){
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select c.city_id,c.city_name from cmn_city_mst c where c.lang_id = 1 ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("city_id"),lRs.getString("city_name")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	       
	  }
	
	public ArrayList getBillType(String lstrLangId, String lstrLocId)
	{		
		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		ArrayList lCmbList = new ArrayList();		
		try {	           
			lCon = DBConnection.getConnection();
		}
		catch(Exception e) {
			logger.error("Exception in Connecting to database " + e);
		}
		try {        
					
			//lSb.append(" select lup.lookupId,lup.lookupDesc from CmnLookupMst lup where lup.parentLookupId='"+BILL_TYPE_ID+"'");
			lSb.append(" select lup.lookup_id,lup.lookup_desc from cmn_lookup_mst lup where lup.parent_lookup_id='"+OnlypayBIll_BILL_TYPE_ID+"'");
			lPStmt = lCon.prepareStatement(lSb.toString());
			logger.info(" The Executed Query is === "+lSb.toString());
			lRs = lPStmt.executeQuery();
			while(lRs.next()) {

				DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("lookup_Id"),lRs.getString("lookup_Desc")); 
				lCmbList.add(ci1);           
			}          
		}
		catch(SQLException se)
		{
			logger.error("Error is: "+ se.getMessage());
			logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
		return lCmbList;	       
	}	
	public ArrayList isApproved(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "yes","yes"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "no","no"); 
	    	lCmbList.add(ci1);           
	    return lCmbList;	       
	  }	

	public ArrayList showIFSFFlag(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "y","Display seperate IF & SF"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "n","Hide seperate IF & SF"); 
	    	lCmbList.add(ci1);           
	    return lCmbList;	       
	  }	

	public ArrayList getOrderByAbstract(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "Created Date","Created Date"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "Bill No.","Bill No."); 
	    	lCmbList.add(ci1);           
	    return lCmbList;	       
	  }	

	/* Returns Month's Name.
	 * 
	 */
	public static String getMonthName(int month)
	{
			Map monthMap = new java.util.HashMap();
				monthMap.put(1,"January");
				monthMap.put(2,"February");
				monthMap.put(3,"March");
				monthMap.put(4,"April");
				monthMap.put(5,"May");
				monthMap.put(6,"June");
				monthMap.put(7,"July");
				monthMap.put(8,"August");
				monthMap.put(9,"September");
				monthMap.put(10,"October");
				monthMap.put(11,"November");
				monthMap.put(12,"December");

		return monthMap.get(month).toString();
	}//end method
	

	public ArrayList getArrearList(String locid,Hashtable adtnlArgs,String lstrLangId, String lstrLocId){
		Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locationId=(Long)loginDetails.get("locationId");

		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		StringBuffer lSb = new StringBuffer();
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;
		ArrayList lCmbList = new ArrayList();		
		if(locid!=null)
			locationId=Long.parseLong(locid);


		try {	           
			lCon = DBConnection.getConnection();
		}
		catch(Exception e) {
			logger.error("Exception in Connecting to database " + e);
		}
		try {          

			lSb.append(" select s.sal_rev_id, r.edp_short_name, s.rev_pay_out_date ");
			lSb.append(" FROm hr_pay_sal_rev_mst s, rlt_bill_type_edp r ");
			lSb.append(" where s.loc_id = "+locationId+" and s.rev_type = r.type_edp_id ");
			lSb.append(" order by s.sal_rev_id desc	 ");

			lPStmt = lCon.prepareStatement(lSb.toString());
			logger.info(" The Executed getArrearList Query is === "+lSb.toString());
			lRs = lPStmt.executeQuery();
			String payOutDate="";
			
			DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
		    SimpleDateFormat sdf = sbConf.GetDateFormat();
		    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			
			while(lRs.next()) {
                payOutDate = sdf.format(sdformat.parse(lRs.getString("rev_pay_out_date")));
				DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("sal_rev_id"),(lRs.getString("edp_short_name")!=null?lRs.getString("edp_short_name"):"")+(payOutDate.equals("")?"":("("+payOutDate+")"))); 
				lCmbList.add(ci1);           
			}          
		}
		catch(Exception se)
		{
			logger.error("Error is: "+ se.getMessage());
			logger.error("Combo Details Not Found " + se);
		}
		finally {
			try {
				//close the resultset if not null
				if (lRs != null) {
					lRs.close();
				}

				//close the statement if not null 
				if (lPStmt != null) {
					lPStmt.close();
				}

				//close the connection if not null  
				if (lCon != null) {
					lCon.close();
				}
			} catch (Exception e) {
				logger.error("Error in closing connection " + e);
			}
		}
		return lCmbList;	       
	}	

	public ArrayList showSignature(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "Yes","Yes"); 
		lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "No","No"); 
	    	lCmbList.add(ci1);           
	    return lCmbList;	       
	  }	
	
	public ArrayList getAIS_IF_SF_Flag(String lstrLangId, String lstrLocId)
	{
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "No"," Select "); 
		ci1 = new DefaultComboItem( "Yes","Yes"); 
    		lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "No","No"); 
	  	  lCmbList.add(ci1);
		return lCmbList;	      
	}
	//added by manish
	public ArrayList getOrderBy(String lstrLangId, String lstrLocId)
	{

		/*ArrayList getOrderBy=new ArrayList();		
		StringBuffer lSb = new StringBuffer();
        ArrayList lCmbList = new ArrayList();	
        DefaultComboItem ci1 = new DefaultComboItem( "Name","Name"); 
    	lCmbList.add(ci1);   
        //lCmbList.add("Name");
		return lCmbList;	*/
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "NAME","NAME"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "Designation","Designation"); 
	    lCmbList.add(ci1);   
	    ci1 = new DefaultComboItem("GPF", "GPF"); 
	    lCmbList.add(ci1);     
	    ci1 = new DefaultComboItem( "PAN","PAN"); 
	    lCmbList.add(ci1);     
	    ci1 = new DefaultComboItem( "BANK","BANK"); 
	    lCmbList.add(ci1);     
	   // ci1 = new DefaultComboItem( "BANK/BRANCH NAME","BANK/BRANCH NAME"); 
	    //lCmbList.add(ci1);     
	    return lCmbList;	  

	
	}
	public ArrayList getVacant(String lstrLangId, String lstrLocId)
	{

		/*ArrayList getOrderBy=new ArrayList();		
		StringBuffer lSb = new StringBuffer();
        ArrayList lCmbList = new ArrayList();	
        DefaultComboItem ci1 = new DefaultComboItem( "Name","Name"); 
    	lCmbList.add(ci1);   
        //lCmbList.add("Name");
		return lCmbList;	*/
		ArrayList lCmbList=new ArrayList();		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( "WITH VACANT","WITH VACANT"); 
    	lCmbList.add(ci1);           
		ci1 = new DefaultComboItem( "WITHOUT VACANT","WITHOUT VACANT"); 
	    lCmbList.add(ci1);   
	    
	   // ci1 = new DefaultComboItem( "BANK/BRANCH NAME","BANK/BRANCH NAME"); 
	    //lCmbList.add(ci1);     
	    return lCmbList;	  

	
	}
	
//added by manish
	
	public ArrayList getCAHBA(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();	
		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( constantsBundle.getString("hbaHouse"),"HBA For Construction"); 
    	lCmbList.add(ci1); 
	  
		ci1 = new DefaultComboItem(constantsBundle.getString("MCA"),"M.C.A"); 
	    lCmbList.add(ci1); 	  
	    
		ci1 = new DefaultComboItem(constantsBundle.getString("FestivalAdvance"),"FA"); 
	    lCmbList.add(ci1); 	
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("PayAdvance"),"PA"); 
	    lCmbList.add(ci1); 	
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("TravelAdvance"),"TA"); 
	    lCmbList.add(ci1); 	
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("ExcesPaymRc"),"Exc.PayRc");
	    lCmbList.add(ci1);
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("hbaForLandInt"),"HBA For Land");
	    lCmbList.add(ci1);
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("othVehAdvInt"),"Oth. Veh.Adv");
	    lCmbList.add(ci1);
	    
	    return lCmbList;
	  }	
	//Manish Added
	public ArrayList getOffice(long locId)
	{

		Connection lCon = null;
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		
     
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database "+e);
            
        }
        try {          
        	lSb.append(" SELECT off_name FROM mst_dcps_ddo_office where LOC_ID="+locId+" and DDO_OFFICE='Yes'  order By Off_Name ");
        	lPStmt = lCon.prepareStatement(lSb.toString());
        	
        	//System.out.println("My query is "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("off_name"),lRs.getString("off_name")); 
            	lCmbList.add(ci1);           
            }
        }catch(Exception e)
            {
            	logger.info("Exception occure while retrieving Office");
            }
            
        return lCmbList;
	}

	public ArrayList getPrintLoanList(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();	
		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( constantsBundle.getString("caloanID"),"Computer Adv."); 
    	lCmbList.add(ci1); 
	  
		ci1 = new DefaultComboItem(constantsBundle.getString("HBALANDID"),"HBA Land"); 
	    lCmbList.add(ci1); 	  
	    
		ci1 = new DefaultComboItem(constantsBundle.getString("MCAPRinID"),"MCA"); 
	    lCmbList.add(ci1); 	
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("HBAHouse"),"HBA For Construction"); 
	    lCmbList.add(ci1); 
	    
	    ci1 = new DefaultComboItem(constantsBundle.getString("othVehAdv"),"Oth. Veh. Adv."); 
	    lCmbList.add(ci1); 
	    
	    return lCmbList;
	  }	


	
//Added by Abhilash
	public ArrayList getPFSeriesList(String billNo,Hashtable adtnlArgs,String lstrLangId, String lstrLocId)
	{
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		logger.info("Bill No"+billNo);
		
		  String billNoArr[] = billNo.split("~");
		
		Connection lCon = null;
		
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();	
        
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select distinct gpf.pf_series as Series ");
        	lSb.append("from hr_pay_gpf_details gpf,ORG_USERPOST_RLT up,ORG_POST_DETAILS_RLT post,Hr_pay_post_psr_mpg psr where gpf.pf_series	is not null ");
        	lSb.append(" and  psr.post_Id = post.POST_ID and post.POST_ID = up.POST_ID and up.ACTIVATE_FLAG = 1 and up.USER_ID = gpf.USER_ID and psr.bill_no = ?");
            lPStmt = lCon.prepareStatement(lSb.toString());
            lPStmt.setString(1, billNoArr[4]);
            logger.info(" The Executed getPFSeriesList Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("Series"),lRs.getString("Series")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	
		
		
	}
//Ended by Abhilash
	

	public ArrayList getListOfLoan(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();	
		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
		ci1 = new DefaultComboItem( constantsBundle.getString("PRINCEPLE"),"Principle"); 
    	lCmbList.add(ci1); 
	  
		ci1 = new DefaultComboItem(constantsBundle.getString("INTEREST"),"Interest"); 
	    lCmbList.add(ci1); 	  
	    
	  
	    return lCmbList;
	  }	
	public ArrayList getCustodialList(Hashtable adtnlArgs,String lstrLangId, String lstrLocId)
	{
		
		 
		Map loginDetails =(Map) adtnlArgs.get("loginDetailsMap");
		long locationId=(Long)loginDetails.get("locationId");
		
		
		
		Logger logger = Logger.getLogger(ReportParameterDAO.class);
		
		
		logger.info("locationId is************"+locationId);
		
		Connection lCon = null;
		
		StringBuffer lSb = new StringBuffer();
        PreparedStatement lPStmt = null;
        ResultSet lRs = null;
        ArrayList lCmbList = new ArrayList();	

		
		try {	           
            lCon = DBConnection.getConnection();
        }
        catch(Exception e) {
            logger.error("Exception in Connecting to database " + e);
        }
        try {          
        	lSb.append(" select distinct cust.CUSTODIAN_ID as custodianId,cust.CUSTODIAN_DESC as CustodianDesc  ");
        	lSb.append("FROM org_ddo_mst ddo,RLT_DDO_ORG rlt,HR_CUSTODIAN_TYPE_MST cust WHERE ddo.LOCATION_CODE="+locationId+" and ddo.DDO_CODE=rlt.DDO_CODE and rlt.LOCATION_CODE=cust.DIVISION_CODE   ");
            lPStmt = lCon.prepareStatement(lSb.toString());
            logger.info(" The Executed getCustodialList Query is === "+lSb.toString());
            logger.info("The Executed getCustodialList Query is === "+lSb.toString());
            lRs = lPStmt.executeQuery();
            while(lRs.next()) {
            	
            	DefaultComboItem ci1 = new DefaultComboItem( lRs.getString("custodianId"),lRs.getString("CustodianDesc")); 
            	lCmbList.add(ci1);           
            }          
        }
        catch(SQLException se)
        {
            logger.error("Error is: "+ se.getMessage());
            logger.error("Combo Details Not Found " + se);
        }
        finally {
            try {
                //close the resultset if not null
                if (lRs != null) {
                	lRs.close();
                }

                //close the statement if not null 
                if (lPStmt != null) {
                	lPStmt.close();
                }

                //close the connection if not null  
                if (lCon != null) {
                	lCon.close();
                }
            } catch (Exception e) {
                logger.error("Error in closing connection " + e);
            }
        }
        return lCmbList;	
		
		
	}
	
	public ArrayList getAISLoanList(String lstrLangId, String lstrLocId){
		ArrayList lCmbList=new ArrayList();	
		
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 
	
		ci1 = new DefaultComboItem( constantsBundle.getString("hbaAisId"),"HBA"); 
    	lCmbList.add(ci1); 
    	
    	ci1 = new DefaultComboItem( constantsBundle.getString("hbaAisIntId"),"HBA Int"); 
    	lCmbList.add(ci1); 
    	
    	ci1 = new DefaultComboItem( constantsBundle.getString("mcaaAisId"),"MCA"); 
    	lCmbList.add(ci1); 
    	
    	ci1 = new DefaultComboItem( constantsBundle.getString("mcaAisIntId"),"MCA Int"); 
    	lCmbList.add(ci1); 
	    
	  
	    return lCmbList;
	  }	
	
}

