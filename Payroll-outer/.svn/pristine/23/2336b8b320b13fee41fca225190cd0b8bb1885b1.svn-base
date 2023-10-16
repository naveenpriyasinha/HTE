package com.tcs.sgv.lcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcDistribution;
import com.tcs.sgv.lcm.valueobject.TrnLcDistributionBudHd;

public class LcDistributionMstDAOImpl 
       extends GenericDaoHibernateImpl<TrnLcDistributionBudHd,Long> 
       implements LcDistributionMstDAO 
{
	public LcDistributionMstDAOImpl(Class<TrnLcDistributionBudHd> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory); 
    }
	
	CommonVO lCmnVO = null ;
	final Logger glogger=Logger.getLogger(LcDivisionAccMstDAOImpl.class);
	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	
	//DAO Method for get all Distribution Type from cmn_lookup_mst Table 
	//input   :  distribution Id, Lang_id
	//output  :  List which contain all distribution Type
	public List getDistributionType(long lILangId) 
	{
        Session hibSession = getSession();
		
		ArrayList lArrCmnVOList = new ArrayList();			
		String lStrDistributionTypetQry = new String();
		//int lIDistributionId=150005; //<<------from Constant
		String lStrDistributionId=bundleConst.getString("LC_DISTRIBUTION_TYPE_ID");
		
		lStrDistributionTypetQry = "select c.lookup_id,c.lookup_name from cmn_lookup_mst c where c.parent_lookup_id='"+lStrDistributionId+"' and c.lang_id="+lILangId;
		
		glogger.info("Query For getDistributionType  is :::: "+ lStrDistributionTypetQry);
		Query query = hibSession.createSQLQuery(lStrDistributionTypetQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{			
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			String lStrLkpID = (tuple[0].toString());
			String lStrLkpName = (tuple[1].toString());			
			lCmnVO.setCommonId(lStrLkpID);
			lCmnVO.setCommonDesc(lStrLkpName);
			lArrCmnVOList.add(lCmnVO);
			
		}
	
		return lArrCmnVOList;
	}
	
    //DAO Method for get Division Name,Id and Latest Balance Amt of DivisionAccount from trn_lc_distribution table 
	//input   :  division_code, Lang_id
	//output  :  List which contain all distribution Type
	public CommonVO getDivisionNmAndBalAmt(String lStrDivCode, long lILangId,long lLngFinYrId) 
	{
		Session hibSession = getSession();				
		String lStrDivNmAmtQry = new String();
		
		/*lStrDivNmAmtQry = "select d.lc_available_amt, locmst.loc_id, locmst.loc_name from trn_lc_distribution d,cmn_location_mst locmst"+ 
						  " where d.division_id =(select l.loc_id from cmn_location_mst l where l.loc_short_name ='"+lStrDivCode+"'"+
						  " and l.lang_id="+lILangId+") and locmst.loc_id= d.division_id and "+
						  " d.lc_order_id = (select max(lc_order_id) from trn_lc_distribution dis where dis.division_id=d.division_id) ";*/
		
		lStrDivNmAmtQry = "select loc.location_code,loc.loc_name, "+
		                  "(select d.lc_available_amt from trn_lc_distribution d "+
		                  "where d.line_cntr=(select max(d.line_cntr) from trn_lc_distribution d where d.division_code= "+
		                  "(select loc.location_code from cmn_location_mst loc where loc.activate_flag='1' and loc.location_code='"+lStrDivCode+"' and loc.lang_id="+lILangId+")) and d.fin_year_id="+lLngFinYrId+") amount "+
		                  "from cmn_location_mst loc "+
		                  "where loc.activate_flag='1' and loc.location_code='"+lStrDivCode+"' and loc.lang_id="+lILangId+" and "+
		                  "loc.location_code in (select ac.division_code from mst_lc_division_account ac ) ";
		
	    glogger.info("Query For getDivisionNmAndBalAmt  is :::: "+ lStrDivNmAmtQry);
		Query query = hibSession.createSQLQuery(lStrDivNmAmtQry);
		List resList = query.list();
		Iterator it = resList.iterator();
		
		String lStrLocAvlAMt="0";
		String lStrLcDivNm="--";
		if(it.hasNext())
		{			
			lCmnVO = new CommonVO();
			Object[] tuple = (Object[])it.next();
			if(tuple[0]!= null || tuple[1] !=null)
				lStrLcDivNm = (tuple[0].toString()+"@"+tuple[1].toString());	
			if(tuple[2]!=null)
				lStrLocAvlAMt = (tuple[2].toString());	
			
			lCmnVO.setCommonId(lStrLocAvlAMt);
			lCmnVO.setCommonDesc(lStrLcDivNm);
		}
		else
		{
			lCmnVO = new CommonVO();
			lCmnVO.setCommonId(lStrLocAvlAMt);
			lCmnVO.setCommonDesc("-@-");
		}
	
		return lCmnVO;
	}
	
	public String getMaximumLineCntr() 
	{
        Session hibSession = getSession();
        String lStrQuery="";
		String lStrMaxCntr="";
		
        lStrQuery = "SELECT MAX(D.LINE_CNTR) FROM TRN_LC_DISTRIBUTION D";
		
		glogger.info("Query For getMaximumLineCntr  is :::: "+ lStrQuery);
		Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
			   lStrMaxCntr = (tuple.toString());
		}
	
		return lStrMaxCntr;
	}
	
	public String getTsryShortName(String lStrLoggedLocCode,long lLngLangId) 
	{
        /*Session hibSession = getSession();
        String lStrQuery="";
		String lStrTsryShrtNm="";
		
        lStrQuery = "select loc.loc_short_name from cmn_location_mst loc where loc.activate_flag='1' and loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoggedTsryLocCode+"'";
		
		glogger.info("Query For getTsryShortName  is :::: "+ lStrQuery);
		Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
				lStrTsryShrtNm = (tuple.toString());
		}
	
		return lStrTsryShrtNm;*/
		
		glogger.info("---------lStrLoggedLocCode CODE IN DAO----------------"+lStrLoggedLocCode);
		 
		 Connection lCon = null;
	     PreparedStatement lStmt = null;
	     ResultSet lRs = null;
	     
	     String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
	     
	     String lStrDeptId="";
	     String lStrTsryShrtNm="";
	    
	     try
	     {
	     	 lCon = DBConnection.getConnection();
	         StringBuffer lSBuff = new StringBuffer();
	         
	         lSBuff.append("select loc.department_id ");	        
	         lSBuff.append("from cmn_location_mst loc ");
	         lSBuff.append("where loc.location_code='"+lStrLoggedLocCode+"' and loc.lang_id="+lLngLangId);         
	         
	         glogger.info("---------QUERY 1----------------"+lSBuff.toString());
	         
	         lStmt = lCon.prepareStatement( lSBuff.toString() );
	         lRs = lStmt.executeQuery();
	        
	         if(lRs.next())
	         {         	
	        	 if(lRs.getObject("department_id") != null)
	        		 lStrDeptId = lRs.getString("department_id");	        	
	         }
	         
	         lSBuff = new StringBuffer();
	         glogger.info("---------lStrDeptId----------------"+lStrDeptId);
	         if(lStrDeptId.equals(bundleConst.getString("LC.TSRY_DEPT_ID")))
	         {
	        	 glogger.info("---------IN IF --------------");
	        	 lSBuff.append("select loc.loc_short_name from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoggedLocCode+"' ");
	        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
	         }
	         else if(lStrDeptId.equals(bundleConst.getString("LC.DIVISION_DEPT_ID")))
	         {
	        	 glogger.info("---------IN ELSE IF --------------");
	        	 lSBuff.append("select loc.loc_short_name from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.department_id='"+lStrTsryDeptId+"' and loc.loc_district_id=(");
	        	 lSBuff.append("select loc.loc_district_id from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoggedLocCode+"')");
	        	 glogger.info("---------QUERY 2----------------"+lSBuff.toString());
	         }
	         
	         lStmt = lCon.prepareStatement( lSBuff.toString() );
             lRs = lStmt.executeQuery(); 
           	
            if(lRs.next())
            {         	
           	 if(lRs.getObject("loc_short_name") != null)
           		lStrTsryShrtNm = lRs.getString("loc_short_name");
           	
            } 
	     }
	     catch( SQLException se )
	     {
	         se.printStackTrace();
	     	glogger.error( "SQLException::"+se.getMessage(), se );
	        
	     }
	     catch( Exception e )
	     {
	         e.printStackTrace();
	     	glogger.error( "Exception::"+e.getMessage(), e );
	     }
	    glogger.info("---------LOGGED IN TSRY lStrTsryShrtNm --------------"+lStrTsryShrtNm);
		return lStrTsryShrtNm;
	}
	
	public String getMaxLcNoTsryWise(String lStrLcNoFormat,long lIFinYrId) 
	{
        Session hibSession = getSession();
        String lStrQuery="";
		String lStrMaxLcNoTsryWise="";
		
        lStrQuery = "select d.lc_order_no from trn_lc_distribution d where d.line_cntr=( "+
        			" select max(d1.line_cntr) from trn_lc_distribution d1 where d1.lc_order_no like '"+lStrLcNoFormat+"' and d1.fin_year_id="+lIFinYrId+") ";
		
		glogger.info("Query For getMaxLcNoTsryWise  is :::: "+ lStrQuery);
		Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
				lStrMaxLcNoTsryWise = (tuple.toString());
		}
	
		return lStrMaxLcNoTsryWise;
	}
	
	public String getLocationCodeByTsryCodeAndDivCode(String lStrLoggedTsryLocCode,String lStrDivCode,long lLngLangId) 
	{
        Session hibSession = getSession();
        String lStrQuery="";
		String lStrLocCode="";
		
		String lStrDivDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		
        lStrQuery = "select loc.location_code from cmn_location_mst loc "+
					"where loc.activate_flag='1' and loc.lang_id="+lLngLangId+" and loc.loc_short_name='"+lStrDivCode+"' and loc.department_id='"+lStrDivDeptId+"' "+
					"and loc.loc_district_id= "+
					"(select loc.loc_district_id from cmn_location_mst loc where loc.activate_flag='1' and loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoggedTsryLocCode+"') ";
        
		glogger.info("Query For getLocationCodeByTsryCodeAndDivCode  is :::: "+ lStrQuery);
		Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
				lStrLocCode = (tuple.toString());
		}
	
		return lStrLocCode;
	}
	
	public String getUnderCodeDescription(String lStrLocCode,String lStrUndCode,long lLangId) 
	{
        Session hibSession = getSession();
        String lStrQuery="";
		String lStrUnderCodeDsc="";
		
        lStrQuery = "select acc.under_code_desc from mst_lc_division_account acc where acc.under_code='"+lStrUndCode+"' "+
        			" and acc.division_code='"+lStrLocCode+"' and acc.lang_id="+lLangId;
        
		glogger.info("Query For getUnderCodeDescription  is :::: "+ lStrQuery);
		Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
				lStrUnderCodeDsc = (tuple.toString());
		}
	
		return lStrUnderCodeDsc;
	}
	
	//code for save data in LcDistribution Table
	//input   : LcDistributionVO
	//output  : boolean which contain true/false
	public boolean saveLcDistributionMst(TrnLcDistribution distVo)
	{
		try
		{
		Session s= getSession();
		s.save(distVo);
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public Long getTrnCntr()
	{
		Session hibSession = getSession();
		Long lLngDistCntr=new Long(0);
		Long lLngCntr=new Long(1);
		Long lLngDtlCntr = new Long(0);
		
		String lStrQuery="select max(trn_cntr) from trn_lc_distribution";
        
        Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
				lLngDistCntr = new Long(tuple.toString());
		}
		
		lStrQuery="select max(trn_cntr) from trn_lc_dtl_posting";
		query = hibSession.createSQLQuery(lStrQuery);
		resList = query.list();
		it = resList.iterator();
		
		while(it.hasNext())
		{
			Object tuple = (Object)it.next();
			if(tuple != null)
				lLngDtlCntr = new Long(tuple.toString());
		}
		if(lLngDistCntr<=lLngDtlCntr)
		{
			lLngCntr=lLngDtlCntr;
		}
		else if(lLngDistCntr>lLngDtlCntr)
		{
			lLngCntr=lLngDistCntr;
		}
		lLngCntr++;
		return lLngCntr;
	}
	
	public LcDivisionInformationVO getDivisionAccountHdStructure(String lStrLcDivCode) 
	{
        glogger.info("-------INSIDE DIV ACC HEAD STRUCTURE-------------");
		Session hibSession = getSession();
        String lStrQuery="";
        
        LcDivisionInformationVO divV0=new LcDivisionInformationVO();
        
		String lStrMjrHd="";
		String lStrSubMjrHd="";
		String lStrMinHd="";
		String lStrSubHd="";
		String lStrDtlHd="";
		String lStrObjHd="";		
		
        lStrQuery = "select a.major_hd,a.submjr_hd,a.min_hd,a.sub_hd,a.dtl_hd,a.obj_hd "+
        			"from mst_lc_division_account a where a.division_code='"+lStrLcDivCode+"' ";
		
		glogger.info("Query For getDivisionAccountHdStructure  is :::: "+ lStrQuery);
		Query query = hibSession.createSQLQuery(lStrQuery);
		List resList = query.list();
		Iterator it = resList.iterator();
		while(it.hasNext())
		{
			Object tuple[] = (Object[])it.next();
			if(tuple[0] != null)
				lStrMjrHd = (tuple[0].toString());
			if(tuple[1] != null)
				lStrSubMjrHd = (tuple[1].toString());
			if(tuple[2] != null)
				lStrMinHd = (tuple[2].toString());
			if(tuple[3] != null)
				lStrSubHd = (tuple[3].toString());
			if(tuple[4] != null)
				lStrDtlHd = (tuple[4].toString());
			if(tuple[5] != null)
				lStrObjHd = (tuple[5].toString());
			
			glogger.info("---------lStrMjrHd-------------"+lStrMjrHd);
			glogger.info("---------lStrSubMjrHd-------------"+lStrSubMjrHd);
			glogger.info("---------lStrMinHd-------------"+lStrMinHd);
			glogger.info("---------lStrSubHd-------------"+lStrSubHd);
			glogger.info("---------lStrDtlHd-------------"+lStrDtlHd);
			glogger.info("---------lStrObjHd-------------"+lStrObjHd);
			
			divV0.setDepartmentCode(lStrMjrHd);//lStrMjrHd
			divV0.setDepartment_name(lStrSubMjrHd);//lStrSubMjrHd
			divV0.setDistrictCode(lStrMinHd);//lStrMinHd
			divV0.setDistrict_name(lStrSubHd);//lStrSubHd
			divV0.setDivisionId(lStrDtlHd);//lStrDtlHd
			divV0.setDivision_name(lStrObjHd);//lStrObjHd
			
		}
	
		return divV0;
	}
	
	
	//----------END OF LC DISTRIBUTION DAO IMPL-------------------------
}



