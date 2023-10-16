package com.tcs.sgv.common.payroll.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class PayrollCommonDAO{
	
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
	long allowanceComponent = Long.parseLong(rsPayroll.getString("allowanceComponent").toString());
	
	
	SessionFactory sessionFactory = null;
	public PayrollCommonDAO(SessionFactory factory)
	{
		sessionFactory = factory;
	}
	
	public List getPaybillColumnHeads()
	{
		List columnList = new ArrayList();
		String line = System.getProperty("line.separator");
		try
		{
			Session hibSession = sessionFactory.getCurrentSession();
			
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String sqlQuery = "select mpg.column_no colNo, mpg.COLUMN_ORDER_NUMBER officer,r.edp_code edp_code, r.edp_short_name sname,mpg.PAYBILL_COL from rlt_edp_code_mpg mpg,RLT_BILL_TYPE_EDP r where r.type_edp_id=mpg.type_edp_id order by mpg.column_no,mpg.COLUMN_ORDER_NUMBER";
            logger.info("the query for the column is "+sqlQuery);
            ResultSet lRs1 = lStmt.executeQuery(sqlQuery);
            
            int prevCol=2;
            int curCol;
            
            columnList.add("Psr No.");
            columnList.add("Employee Name"+line+"Designation"+line+"Pay Scale");
            
            while(lRs1.next())
            {
            	StringBuffer sbVal=new StringBuffer(  );
            	curCol=Integer.parseInt(lRs1.getString("colNo"));
            			            		
            		//System.out.println("inside if and prev value is "+prevCol+" and cur value is"+curCol+" Pay Bill Col is " + lRs1.getString("PAYBILL_COL"));
            		
            		sbVal.append(lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
            		//rowList.add(lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
            		prevCol=curCol;
            		if(lRs1.next())
            		{	
	            		curCol=Integer.parseInt(lRs1.getString("colNo"));
	            		if(curCol ==prevCol)
		            	{
	            			sbVal.append(System.getProperty("line.separator")+lRs1.getString("edp_code")+System.getProperty("line.separator")+lRs1.getString("sname"));
	            			prevCol=curCol;
		            	}
	            		else
	            		{
	            				lRs1.previous();
	            		}
            		}
            		//rowList.add(sbVal.toString());
                    
                    //Added by Paurav to dynamically set Column Header
                    //rptCol[i].setColumnHeader(sbVal.toString());
                    columnList.add(sbVal.toString());
                    //Ended By Paurav
            	//i++; k++;
            }
            
			
			
		}catch(Exception exp)
		{
			logger.error("Exception in getPaybillColumnHeads method " + exp);
			exp.printStackTrace();
		}
		finally
		{
			try
			{
				
			}catch(Exception exp)
			{
				
			}
		}
		
		return columnList;
	}
	
	
	public Map getEdpAllwMap(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = sessionFactory.getCurrentSession();
		try{
		 
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            logger.info("Inside Deduction DAO");
            String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID ="+allowanceComponent;
            //System.out.println(queryStr);
            //Query query= hibSession.createQuery(queryStr);
            ResultSet lRs1 =lStmt.executeQuery(queryStr);
            int count=0;
            while(lRs1.next())
            {
            	/*Object[] arr=(Object[])deducList.get(0);*/
            	map.put(lRs1.getString(2).toString(),lRs1.getString(1).toString());
            	count++;
            }
             
           // System.out.println("count is"+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
          
        return map;
    }
	
	
	public Map getEdpDeducMap(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = sessionFactory.getCurrentSession();
		try{
		 
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            logger.info("Inside Deduction DAO");
            String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500135";
            //System.out.println(queryStr);
            //Query query= hibSession.createQuery(queryStr);
            ResultSet lRs1 =lStmt.executeQuery(queryStr);
            int count=0;
            while(lRs1.next())
            {
            	/*Object[] arr=(Object[])deducList.get(0);*/
            	map.put(lRs1.getString(2).toString(),lRs1.getString(1).toString());
            	count++;
            }
             
            //System.out.println("count is for deduction "+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
          
        return map;
    }

	public Map getAllwEdpMap(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = sessionFactory.getCurrentSession();
		try{
		 
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            logger.info("Inside Deduction DAO");
            String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500134";
            //System.out.println(queryStr);
            //Query query= hibSession.createQuery(queryStr);
            ResultSet lRs1 =lStmt.executeQuery(queryStr);
            int count=0;
            while(lRs1.next())
            {
            	/*Object[] arr=(Object[])deducList.get(0);*/
            	if(lRs1.getString(1)!=null && lRs1.getString(2)!=null)
            	 map.put(lRs1.getString(1).toString(),lRs1.getString(2).toString());
            	count++;
            }
             
           // System.out.println("count is"+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
          
        return map;
    }
	
	
	public Map getDeducEdpMap(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = sessionFactory.getCurrentSession();
		try{
		 
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            logger.info("Inside Deduction DAO");
            String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500135";
            //System.out.println(queryStr);
            //Query query= hibSession.createQuery(queryStr);
            ResultSet lRs1 =lStmt.executeQuery(queryStr);
            int count=0;
            while(lRs1.next())
            {
            	/*Object[] arr=(Object[])deducList.get(0);*/
            	map.put(lRs1.getString(1).toString(),lRs1.getString(2).toString());
            	count++;
            }
             
            //System.out.println("count is for deduction "+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
          
        return map;
    }
	public Map getAdvanceEdpMap(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = sessionFactory.getCurrentSession();
		try{
		 
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            logger.info("Inside Deduction DAO");
            String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500136";
            //System.out.println(queryStr);
            //Query query= hibSession.createQuery(queryStr);
            ResultSet lRs1 =lStmt.executeQuery(queryStr);
            int count=0;
            while(lRs1.next())
            {
            	/*Object[] arr=(Object[])deducList.get(0);*/
            	map.put(lRs1.getString(1).toString(),lRs1.getString(2).toString());
            	count++;
            }
             
            //System.out.println("count is for deduction "+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
          
        return map;
    }
	public Map getLoanEdpMap(long payCommissionId,long locId)
    {
		List deducList=new ArrayList();
		Map map =new HashMap();
		Session hibSession = sessionFactory.getCurrentSession();
		try{
		 
			Connection conn = hibSession.connection();
			Statement lStmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            logger.info("Inside Deduction DAO");
            String queryStr="SELECT rlt.EDP_CODE, mpg.TYPE_ID FROM RLT_BILL_TYPE_EDP rlt, HR_PAY_EDP_COMPO_MPG mpg where rlt.TYPE_EDP_ID= mpg.TYPE_EDP_ID and mpg.CMN_LOOKUP_ID = 2500137";
            //System.out.println(queryStr);
            //Query query= hibSession.createQuery(queryStr);
            ResultSet lRs1 =lStmt.executeQuery(queryStr);
            int count=0;
            while(lRs1.next())
            {
            	/*Object[] arr=(Object[])deducList.get(0);*/
            	map.put(lRs1.getString(1).toString(),lRs1.getString(2).toString());
            	count++;
            }
             
            //System.out.println("count is for deduction "+count);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
          
        return map;
    }

	// Added by Paurav for getting Financial Year Info
	public SgvcFinYearMst getFinYrInfo(Date lDate, Long lLangId) {
		StringBuffer lSBQuery = new StringBuffer();
		SgvcFinYearMst lObjFinYrVO = null;
		Session ghibSession = sessionFactory.getCurrentSession();

		try {
			lSBQuery.append(" FROM SgvcFinYearMst A WHERE ");
			lSBQuery.append(" :date BETWEEN A.fromDate AND A.toDate ");
			lSBQuery.append(" AND A.langId = :langId ");

			
			Query lQuery = ghibSession.createQuery(lSBQuery.toString());

			SimpleDateFormat lDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			lQuery.setParameter("date", lDateFormat.parse(lDateFormat
					.format(lDate)));
			if(lLangId==1)
			lQuery.setParameter("langId", "en_US");
			else
				lQuery.setParameter("langId", "gu");	

			logger.info("Query for getFinYrInfo : " + lQuery.toString());
			logger.info("And Parameter is " + lDate + " " + lLangId);

			List lList = lQuery.list();

			if (lList != null && lList.size() > 0) {
				lObjFinYrVO = (SgvcFinYearMst) lList.get(0);
			}
		} catch (Exception e) {
			logger.error("Error in getFinYrInfo is : " + e, e);
		}

		return lObjFinYrVO;
	}
}
