package com.tcs.sgv.lcm.reports;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.lcm.valueobject.LcDivisionInformationVO;
import com.tcs.sgv.lcm.valueobject.TrnLcBudgetPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcChequePosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDeductionPosting;
import com.tcs.sgv.lcm.valueobject.TrnLcDtlPosting;


public class LCReportQueryDAOImpl 
extends GenericDaoHibernateImpl<TrnLcDeductionPosting, Integer> implements LCReportQueryDAO 
{
	//private static final Logger glogger=Logger.getLogger(LCReportQueryDAOImpl.class);	
	 Log glogger = LogFactory.getLog(getClass());
	 ResourceBundle bundleConst = ResourceBundle.getBundle("resources/lcm/LcmConstants");
	 ResourceBundle bundleEnUS = ResourceBundle.getBundle("resources/lcm/lcexp_en_US");
	public LCReportQueryDAOImpl(Class<TrnLcDeductionPosting> type, SessionFactory sessionFactory) 
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public LCReportQueryDAOImpl() 
	{
		super(TrnLcDeductionPosting.class);
	}

	public ArrayList getMonths(String lStrLangId, String lstrLocId) 
    {
		glogger.info("-------INSIDE getMonths of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of locId is "+lstrLocId);
		
		ArrayList lArrMonthDtls = new ArrayList();
		ComboValuesVO comboVo=null;
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrMonthCode=null;
        String lStrMonthName=null;
        try
        {
        	glogger.info("------------getMonths before Query--------");
        	lCon = DBConnection.getConnection();
            StringBuffer lsb = new StringBuffer();
            lsb = new StringBuffer("select month_code,month_name from sgva_month_mst where lang_id='"+lStrLangId+"'");
            glogger.info("------------QUERY FOR GET getMonths -------"+lsb.toString());		
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getMonths after Query--------");
            while(lRs.next())
            {
            	comboVo= new ComboValuesVO();
            	lStrMonthCode = lRs.getString("month_code");
            	lStrMonthName = lRs.getString("month_name");
                
            	comboVo.setId(lStrMonthCode);
            	comboVo.setDesc(lStrMonthName);
                
            	lArrMonthDtls.add(comboVo);
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
        
        return lArrMonthDtls; 
    
    }
	
	public ArrayList getBanks(String lStrLangId, String lstrLocId) 
    {
		glogger.info("-------Inside getBanks of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the locId is "+lstrLocId);			
		
		ArrayList lArrBankDtls = new ArrayList();
		
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.SBI.CODE"));
		comboVo.setDesc(bundleEnUS.getString("LC.SBI.NAME"));
		lArrBankDtls.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.SBS.CODE"));
		comboVo.setDesc(bundleEnUS.getString("LC.SBS.NAME"));		
		lArrBankDtls.add(comboVo);
       
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.BOB.CODE"));
		comboVo.setDesc(bundleEnUS.getString("LC.BOB.NAME"));		
		lArrBankDtls.add(comboVo);
        
        return lArrBankDtls;     
		
		/*long lLngLangId= (long)getLongLangId(lStrLangId);
		glogger.info("-----LANG ID---------"+lLngLangId);
		
		ArrayList lArrBankDtls = new ArrayList();
		
		
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrBankCode=null;
        String lStrBankName=null;
        try
        {
        	glogger.info("------------getBanks before Query--------");
        	lCon = DBConnection.getConnection();
            StringBuffer lsb = new StringBuffer();
            lsb = new StringBuffer("select bank_code,bank_name from mst_bank  where lang_id="+lLngLangId);
            glogger.info("------------QUERY FOR GET getBanks-------"+lsb.toString());		
            lStmt = lCon.prepareStatement( lsb.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getBanks after Query--------");
            while(lRs.next())
            {
            	ComboValuesVO comboVo= new ComboValuesVO();
            	lStrBankCode = lRs.getString("bank_code");
            	lStrBankName = lRs.getString("bank_name");
                
            	comboVo.setId(lStrBankCode);
            	comboVo.setDesc(lStrBankName);
                
                lArrBankDtls.add(comboVo);
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
        
       glogger.info("problemmmm--------");
        return lArrBankDtls;*/
 
    }
	
	public ArrayList getAdviceStatus(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getAdviceStatus of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
			
		ArrayList lArrApprovedLst = new ArrayList();
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.ADVICE_STATUS_APPROVED_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.ADVICE_STATUS_APPROVED"));
		lArrApprovedLst.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.ADVICE_STATUS_PENDING_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.ADVICE_STATUS_PENDING"));
		
		lArrApprovedLst.add(comboVo);
       
        
        return lArrApprovedLst;     
    }
	
	//METHOD TO GET LANG ID IN LONG FORMAT LIKE '1' IN PLACE OF 'en_US'
	public long getLongLangId(String lStrLangId)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        glogger.info("------COMING STR LANG ID-------"+lStrLangId);
		long lLngLangId=0;
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lsb = null;
		    lsb = new StringBuffer("select lang_id from cmn_language_mst where lang_short_name='"+lStrLangId+"'");
		    lStmt = lCon.prepareStatement( lsb.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
            	lLngLangId = Long.parseLong(lRs.getString("lang_id"));
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
		glogger.info("---------LONG LANG ID IN CONV MTHD---------"+lLngLangId);
		return lLngLangId;
	}
	
	public String getStrLangId(long lILangId) 
	{
		Session hibSession = getSession();
		String lStrLangId = "";
		String lStrLanguageQuery = "select lang_short_name from cmn_language_mst where lang_id="
				+ lILangId;
		glogger
				.info("---------Query for getting language  in getStrLangId of  LcAdviceReceiptDAOImpl is ::"
						+ lStrLanguageQuery);


		Query query = hibSession.createSQLQuery(lStrLanguageQuery);
	
		List resList = query.list();
	
		Iterator it = resList.iterator();
	
		while (it.hasNext()) {
		
			Object tuple = (Object) it.next();
			
			lStrLangId = (tuple.toString());
			
		}
		glogger.
				info("Returning from getStrLangId() of  LcAdviceReceiptDAOImpl :: lStrLangId is ::  "
						+ lStrLangId);
		return lStrLangId;
	}
	
	//METHOD TO GENERATE LC EXPENDITURE REPORT 150005
	public ArrayList getLcExpenditureReport(String lStrFinYr,int lIAdvNo,String lStrMonthCode,String lStrBankCode,String lStrLcValidFrmDt,String lStrLcValidToDt,String lStrEntryDtFrmDt,String lStrEntryDtToDt,long lLngLangId,String lStrLoginLocCode,String lStrAdvStatus)
	{
	    glogger.info("------ Inside getLcExpenditureReport() ---------- ");
		
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;			
		
		glogger.info("-------LOGIN LANG ID IN EXP REPORT QUERY---------"+lLngLangId);
		glogger.info("-------LOGIN LOC CODE IN EXP REPORT QUERY---------"+lStrLoginLocCode);
		String lStrDivisionCode=lStrLoginLocCode; //LOGIN LOC ID
		//int lIDivisionId=100561; //<<<<---------need to replace..by login division Id
		
		String lStrLangId=getStrLangId(lLngLangId);
		
		long lILcExpId=0;
		int lIAdviceNo=0;
		String lStrEntryDate="";
		String lStrDistrictName="";
		String lStrDeptName="";
		String lStrDivisionName="";
		String lStrBankName="";
		double lDblTotExpAmt=0.0;
		double lDblTotDedAmt=0.0;
		String lStrMonth="";
		String lStrDelete="";
		String lStrAdvApproved="";
		
		int lISrNo=0;
		ArrayList lArrOuterLst=new ArrayList();
		ArrayList lArrInnerLst=null;
		
		StringBuffer lSBuff=new StringBuffer();		
		
		lSBuff.append("SELECT DTL.DIVISION_CODE,DTL.LC_EXP_ID EXPID, DTL.ADVICE_NO ADVNO,DTL.ADVICE_APPROVED APPROVED, TO_CHAR(DTL.CREATED_DATE,'DD/MM/YYYY') ENTRY_DATE, ");
		lSBuff.append("(SELECT DIST.DISTRICT_NAME FROM CMN_DISTRICT_MST DIST WHERE DIST.DISTRICT_ID=( ");
		lSBuff.append("SELECT LOC.LOC_DISTRICT_ID FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+")) DISTRICT, ");
		lSBuff.append("(SELECT LOOK.LOOKUP_DESC FROM CMN_LOOKUP_MST LOOK WHERE LOOK.LOOKUP_ID=( ");
		lSBuff.append("SELECT LOC.TYPE_LOOKUP_ID FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE = DTL.DIVISION_CODE AND LOC.LANG_ID="+lLngLangId+")) DEPT, ");
		lSBuff.append("(SELECT LOC.LOC_NAME FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE = DTL.DIVISION_CODE AND LOC.LANG_ID="+lLngLangId+") DIVISION, ");
		lSBuff.append("(SELECT BANK.BANK_NAME FROM MST_BANK BANK WHERE BANK.ACTIVATE_FLAG='1' AND BANK.LANG_ID="+lLngLangId+" AND BANK.BANK_CODE=( ");
		lSBuff.append("SELECT DTLS.BANK_CODE FROM TRN_LC_DTL_POSTING DTLS WHERE DTLS.LOC_CODE='"+lStrDivisionCode+"' AND DTLS.LC_EXP_ID=DTL.LC_EXP_ID AND DTL.ACTIVE='0')) BANK, ");
		lSBuff.append("(SELECT NVL(SUM(BUD.EXP_AMT),0) FROM TRN_LC_BUDGET_POSTING BUD WHERE BUD.LC_EXP_ID=DTL.LC_EXP_ID AND BUD.ACTIVE='0') EXP_AMT, ");
		lSBuff.append("(SELECT NVL(SUM(DED.AMOUNT),0) FROM TRN_LC_DEDUCTION_POSTING DED WHERE DED.LC_EXP_ID=DTL.LC_EXP_ID AND DED.ACTIVE='0') DED_AMT, ");
		lSBuff.append("(SELECT MON.MONTH_NAME FROM SGVA_MONTH_MST MON WHERE MON.MONTH_CODE=( ");
		lSBuff.append("SELECT DTLS.MONTH_CODE FROM TRN_LC_DTL_POSTING DTLS WHERE DTLS.LC_EXP_ID=DTL.LC_EXP_ID AND DTL.LOC_CODE='"+lStrDivisionCode+"') AND MON.LANG_ID='"+lStrLangId+"') MONTH_NM ");
		
		lSBuff.append("FROM TRN_LC_DTL_POSTING DTL ");
		lSBuff.append("WHERE DTL.LOC_CODE ='"+lStrDivisionCode+"' AND DTL.ACTIVE='0' ");
		
		//QUERY ON CONDITION BASIS----------------------------------
		if(lStrFinYr != null && !lStrFinYr.equals(""))
		{	
		     lSBuff.append("AND DTL.FIN_YEAR_ID='"+lStrFinYr+"'");
		     glogger.info("---------PARAMETER lStrFinYr------------"+lStrFinYr);
		}
		if(lIAdvNo != 0 )
		{	
		     lSBuff.append("AND DTL.ADVICE_NO="+lIAdvNo);
		     glogger.info("---------PARAMETER lIAdvNo------------"+lIAdvNo);
		}
		if(lStrMonthCode != null && !lStrMonthCode.equals(""))
		{	
		     lSBuff.append("AND DTL.MONTH_CODE='"+lStrMonthCode+"'");
		     glogger.info("---------PARAMETER lStrMonthCode------------"+lStrMonthCode);
		}
		if(lStrBankCode != null && !lStrBankCode.equals(""))
		{	
		     lSBuff.append("AND DTL.BANK_CODE='"+lStrBankCode+"'");
		     glogger.info("---------PARAMETER lStrBankCode------------"+lStrBankCode);
		}
		if(lStrAdvStatus != null && !lStrAdvStatus.equals(""))
		{	
		     lSBuff.append("AND DTL.ADVICE_APPROVED='"+lStrAdvStatus+"'");
		     glogger.info("---------PARAMETER lStrAdvStatus------------"+lStrAdvStatus);
		}
		/*if((lStrLcValidFrmDt != null && !lStrLcValidFrmDt.equals("")) && (lStrLcValidToDt == null || lStrLcValidToDt.equals("")) )
		{	
		     lSBuff.append("AND DTL.LC_VALID_FRM >= TO_DATE('"+lStrLcValidFrmDt+"','DD/MM/YYYY') AND DTL.LC_VALID_FRM <= SYSDATE");
		     glogger.info("---------PARAMETER lStrLcValidFrmDt------------"+lStrLcValidFrmDt);
		}*/
		if((lStrLcValidToDt != null && !lStrLcValidToDt.equals("")) && (lStrLcValidFrmDt == null || lStrLcValidFrmDt.equals("")))
		{	
		    lSBuff.append("AND DTL.LC_VALID_FRM >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lStrFinYr+"') "); 
			lSBuff.append("AND DTL.LC_VALID_FRM <= TO_DATE('"+lStrLcValidToDt+"','DD/MM/YYYY')");
		    glogger.info("---------PARAMETER lStrLcValidToDt 66------------"+lStrLcValidToDt);
		}
		if(lStrLcValidFrmDt != null && !lStrLcValidFrmDt.equals("")  && (lStrLcValidToDt != null && !lStrLcValidToDt.equals("")))
		{	
			lSBuff.append("AND DTL.LC_VALID_FRM >= TO_DATE('"+lStrLcValidFrmDt+"','DD/MM/YYYY')");
			lSBuff.append("AND DTL.LC_VALID_FRM <= TO_DATE('"+lStrLcValidToDt+"','DD/MM/YYYY')");
			glogger.info("---------PARAMETER 1 lStrLcValidFrmDt------------"+lStrLcValidFrmDt);
		    glogger.info("---------PARAMETER 2 lStrLcValidToDt------------"+lStrLcValidToDt);
		}
		
		/*if((lStrEntryDtFrmDt != null && !lStrEntryDtFrmDt.equals("")) && (lStrEntryDtToDt == null || lStrEntryDtToDt.equals("")) )
		{	
		     lSBuff.append("AND DTL.CREATED_DATE >= TO_DATE('"+lStrEntryDtFrmDt+"','DD/MM/YYYY') AND DTL.CREATED_DATE <= SYSDATE");
		     glogger.info("---------PARAMETER lStrEntryDtFrmDt------------"+lStrEntryDtFrmDt);
		}
		if((lStrEntryDtToDt != null && !lStrEntryDtToDt.equals("")) && (lStrEntryDtFrmDt == null || lStrEntryDtFrmDt.equals("")))
		{	
		    lSBuff.append("AND DTL.CREATED_DATE >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lStrFinYr+"')"); 
			lSBuff.append("AND DTL.CREATED_DATE <= TO_DATE('"+lStrEntryDtToDt+"','DD/MM/YYYY')");
		    glogger.info("---------PARAMETER lStrEntryDtToDt------------"+lStrEntryDtToDt);
		}*/
		if(lStrEntryDtFrmDt != null && !lStrEntryDtFrmDt.equals("")  && (lStrEntryDtToDt != null && !lStrEntryDtToDt.equals("")))
		{	
			lSBuff.append("AND DTL.CREATED_DATE >= TO_DATE('"+lStrEntryDtFrmDt+"','DD/MM/YYYY')");
			lSBuff.append("AND DTL.CREATED_DATE <= TO_DATE('"+lStrEntryDtToDt+"','DD/MM/YYYY')");
			glogger.info("---------PARAMETER 1 lStrEntryDtFrmDt------------"+lStrEntryDtFrmDt);
		    glogger.info("---------PARAMETER 2 lStrEntryDtToDt------------"+lStrEntryDtToDt);
		}
		
		lSBuff.append(" ORDER BY DTL.ADVICE_NO ");
		
		try
		{
			glogger.info("-----In try of getLcExpenditureReport---------");
			lCon=getSession().connection();			
			lStmt=lCon.createStatement();
			glogger.info("-----QUERY FOR GET getLcExpenditureReport--------"+lSBuff.toString());
			rs=lStmt.executeQuery(lSBuff.toString());
			glogger.info("----------Query Executed-----------------------");
			
			while(rs.next())
			{
				lISrNo++;
				lILcExpId=rs.getLong("EXPID");
				lIAdviceNo=rs.getInt("ADVNO");
				lStrEntryDate=rs.getString("ENTRY_DATE");
				lStrDistrictName=rs.getString("DISTRICT");
				lStrDeptName=rs.getString("DEPT");
				lStrDivisionName=rs.getString("DIVISION");
				lStrBankName=rs.getString("BANK");
				lDblTotExpAmt=rs.getDouble("EXP_AMT");
				lDblTotDedAmt=rs.getDouble("DED_AMT");
				lStrMonth=rs.getString("MONTH_NM");
				lStrDelete="Delete";
				lStrAdvApproved=rs.getString("APPROVED");
				
				glogger.info("--------lILcExpId----------"+lILcExpId);
				glogger.info("--------lIAdviceNo----------"+lIAdviceNo);
				glogger.info("--------lStrEntryDate----------"+lStrEntryDate);
				glogger.info("--------lStrDistrictName----------"+lStrDistrictName);
				glogger.info("--------lStrDeptName----------"+lStrDeptName);
				glogger.info("--------lStrDivisionName----------"+lStrDivisionName);
				glogger.info("--------lStrBankName----------"+lStrBankName);
				glogger.info("--------lDblTotExpAmt----------"+lDblTotExpAmt);
				glogger.info("--------lDblTotDedAmt----------"+lDblTotDedAmt);
				glogger.info("--------lStrMonth----------"+lStrMonth);
				glogger.info("--------lStrAdvApproved----------"+lStrAdvApproved);
				
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNumTotExpAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotExpAmt));
				Number lNumTotDedAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotDedAmt));
				
				glogger.info("------------AMT IN QUERY DAO------------"+lNumTotExpAmt);
				glogger.info("------------AMT IN QUERY DAO------------"+lNumTotDedAmt);
				lArrInnerLst=new ArrayList();
				
				lArrInnerLst.add(lISrNo);
				lArrInnerLst.add(lIAdviceNo);
				lArrInnerLst.add(lStrEntryDate);
				lArrInnerLst.add(lStrDistrictName);
				lArrInnerLst.add(lStrDeptName);
				lArrInnerLst.add(lStrDivisionName);
				lArrInnerLst.add(lStrBankName);
				lArrInnerLst.add(lNumTotExpAmt);
				lArrInnerLst.add(lNumTotDedAmt);
				lArrInnerLst.add(lStrMonth);
				if(lStrAdvApproved.equals("0"))
				    lArrInnerLst.add("Approved");				
				else
					lArrInnerLst.add("Pending");
				
				lArrInnerLst.add(lILcExpId);				
				lArrInnerLst.add(lStrFinYr);				
				lArrInnerLst.add(lStrMonthCode);
				lArrInnerLst.add(lStrBankCode);
				lArrInnerLst.add(lIAdvNo);
				lArrInnerLst.add(lStrLcValidFrmDt);
				lArrInnerLst.add(lStrLcValidToDt);
				lArrInnerLst.add(lStrEntryDtFrmDt);
				lArrInnerLst.add(lStrEntryDtToDt);
				lArrInnerLst.add(lStrAdvStatus);
				
				if(lStrAdvApproved.equals("0"))
				    lArrInnerLst.add("");				
				else
					lArrInnerLst.add(lStrDelete);
				
				
				lArrOuterLst.add(lArrInnerLst);
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lArrOuterLst;
		
	}
	
	
	public TrnLcDtlPosting getDtlPostingDtlsForUpdate(String lStrLcExpId) 
    {
		glogger.info("-------Inside getDtlPostingDtlsForUpdate of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lStrLcExpId);			
		
		TrnLcDtlPosting lcDtlPostingVo=null;
		StringBuffer lStrBuff = new StringBuffer();
		
		long lILcExpId=0;
		if(!lStrLcExpId.equals(""))
		   lILcExpId=Long.parseLong(lStrLcExpId);
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDivCode="";        
        Date lDtAdviceDate=null;
        int lIAdviceNo=0;
        String lStrMonthCode="";
        Date lDtLcValidFrom=null;
        double lDblOpeningLcAmt=0;
        String lStrDivOff="";
        String lStrDrwOff="";
        String lStrTsryOff="";
        double lDblClosingLcAmt=0;
        String lStrBankCode="";
        String lStrAdvApproved="";
        try
        {
        	glogger.info("------------getDtlPostingDtlsForUpdate before Query--------");
        	lCon = DBConnection.getConnection();
            
        	lStrBuff.append("SELECT DIVISION_CODE,ADVICE_NO,ADVICE_DT,MONTH_CODE,LC_VALID_FRM,DIV_OFF,DRW_OFF,TSRY_OFF,CLOSING_LC,BANK_CODE,ADVICE_APPROVED, ");
        	lStrBuff.append("(select dst.lc_available_amt OPENING_LC from trn_lc_distribution dst where ");
        	lStrBuff.append("dst.line_cntr=(select max(dst.line_cntr) from trn_lc_distribution dst where dst.division_code=( ");
        	lStrBuff.append("select dtl.division_code from trn_lc_dtl_posting dtl where dtl.lc_exp_id='"+lStrLcExpId+"' and dtl.active='0' ))) OPENING_LC ");
        	
        	lStrBuff.append("FROM TRN_LC_DTL_POSTING WHERE ACTIVE='0' AND LC_EXP_ID='"+lStrLcExpId+"'");
        	
            glogger.info("-----QUERY FOR GET getDtlPostingDtlsForUpdate--------"+lStrBuff.toString());	
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getDtlPostingDtlsForUpdate after Query--------");
            while(lRs.next())
            {
            	lcDtlPostingVo= new TrnLcDtlPosting();            	
            	
            	if(lRs.getObject("DIVISION_CODE") != null)            	
            		lStrDivCode = lRs.getString("DIVISION_CODE");  
            	if(lRs.getObject("ADVICE_NO") != null)
            		lIAdviceNo = lRs.getInt("ADVICE_NO");
            	if(lRs.getObject("ADVICE_DT") != null)
            		lDtAdviceDate = lRs.getDate("ADVICE_DT");
            	if(lRs.getObject("MONTH_CODE") != null)
            		lStrMonthCode = lRs.getString("MONTH_CODE");
            	if(lRs.getObject("OPENING_LC") != null)
            		lDblOpeningLcAmt = lRs.getDouble("OPENING_LC");
            	if(lRs.getObject("LC_VALID_FRM") != null)
            		lDtLcValidFrom = lRs.getDate("LC_VALID_FRM");            	
            	if(lRs.getObject("DIV_OFF") != null)
            		lStrDivOff = lRs.getString("DIV_OFF");   
            	if(lRs.getObject("DRW_OFF") != null)
            		lStrDrwOff = lRs.getString("DRW_OFF"); 
            	if(lRs.getObject("TSRY_OFF") != null)
            		lStrTsryOff = lRs.getString("TSRY_OFF"); 
            	if(lRs.getObject("CLOSING_LC") != null)
            		lDblClosingLcAmt = lRs.getDouble("CLOSING_LC");
            	if(lRs.getObject("BANK_CODE") != null)
            		lStrBankCode = lRs.getString("BANK_CODE");
            	if(lRs.getObject("ADVICE_APPROVED") != null)
            		lStrAdvApproved = lRs.getString("ADVICE_APPROVED"); 
            	
            	glogger.info("-----------ADVICE DATE IN DAO IMPL--------------"+lDtAdviceDate);
            	glogger.info("-----------ADVICE APPROVED IN DAO IMPL--------------"+lStrAdvApproved.toString());
            	
            	char cha[] = (char[])lStrAdvApproved.toCharArray();
            	
            	NumberFormat lnfLong=NumberFormat.getNumberInstance();
            	//lnfLong.format(lDblClosingLcAmt);
    	        lnfLong.setGroupingUsed(false);
    	        lnfLong.setMaximumFractionDigits(2);
    	        lnfLong.setMinimumFractionDigits(2); 
            	
            	
            	lcDtlPostingVo.setLcExpId(new BigDecimal(lILcExpId));
            	lcDtlPostingVo.setDivisionCode(lStrDivCode);
            	lcDtlPostingVo.setAdviceNo(new BigDecimal(lIAdviceNo));
            	lcDtlPostingVo.setAdviceDt(lDtAdviceDate);
            	lcDtlPostingVo.setMonthCode(lStrMonthCode);
            	lcDtlPostingVo.setOpeningLc(new BigDecimal(lnfLong.format(lDblOpeningLcAmt)));
            	lcDtlPostingVo.setLcValidFrm(lDtLcValidFrom);
            	lcDtlPostingVo.setDivOff(lStrDivOff);
            	lcDtlPostingVo.setDrwOff(lStrDrwOff);
            	lcDtlPostingVo.setTsryOff(lStrTsryOff);
            	lcDtlPostingVo.setClosingLc(new BigDecimal(lnfLong.format(lDblClosingLcAmt)));
            	lcDtlPostingVo.setBankCode(lStrBankCode); 
            	lcDtlPostingVo.setAdviceApproved(cha[0]);
            	
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
        
        return lcDtlPostingVo; 
    
    }
	
	//Method to get Division Dtls like Division Name,Dept Name etc
	public LcDivisionInformationVO getDivisionDtlsForUpdate(String lStrLocCode,String lStrLangId) 
    {
		glogger.info("-------Inside getDivisionDtlsForUpdate of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrDivCode is "+lStrLocCode);			
		
		LcDivisionInformationVO divInformationVO = new LcDivisionInformationVO();	
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDivCode="";
        String lStrShrtNm="";
        String lStrDivName="";
        String lStrDeptName="";
        String lStrDistName="";
        String lStrDistId="";
        String lStrDeptCode="";
        
        try
        {
        	glogger.info("------------getDivisionDtlsForUpdate before Query--------");
        	lCon = DBConnection.getConnection();
            
        	/*lStrBuff.append("SELECT LOC.LOCATION_CODE DIVCODE,LOC.LOC_NAME DIVNAME,DIST.DISTRICT_ID,L.LOOKUP_ID");
        	lStrBuff.append("(SELECT D.DISTRICT_NAME FROM CMN_DISTRICT_MST D WHERE D.DISTRICT_ID=LOC.LOC_DISTRICT_ID AND D.LANG_ID=LOC.LANG_ID) DIST, ");
        	lStrBuff.append("(SELECT L.LOOKUP_DESC FROM CMN_LOOKUP_MST L WHERE L.LOOKUP_ID=LOC.TYPE_LOOKUP_ID AND L.LANG_ID=LOC.LANG_ID) DEPT ");
        	lStrBuff.append("FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrLocCode+"'");*/
        	
        	String lStrMnthDtlQuery = "select d.lc_order_id,d.division_code DIVCODE,cmn.loc_short_name divShrtNm, d.lc_valid_frm, d.lc_available_amt,"
				+ "cmn.loc_name DIVNAME,lkp.lookup_desc DEPT,dst.district_name DIST,dst.district_code,lkp.lookup_name"
				+ " from trn_lc_distribution d,cmn_location_mst cmn,cmn_lookup_mst lkp,cmn_district_mst dst "
				+ " where d.division_code=(select loc.location_code from cmn_location_mst loc  "
				+ " where loc.location_code = '"
				+ lStrLocCode
				+ "' and loc.lang_id= '"
				+ lStrLangId
				+ "') and"
				+ " d.division_code= cmn.location_code and cmn.type_lookup_id = lkp.lookup_id and "
				+ " cmn.lang_id = lkp.lang_id and cmn.loc_district_id = dst.district_id and "
				+ " cmn.lang_id = dst.lang_id and d.line_cntr="
				+ " (select max(dis.line_cntr) from trn_lc_distribution dis where dis.division_code= d.division_code) ";

        	
        	glogger.info("------QUERY FOR GET getDivisionDtlsForUpdate-----"+lStrMnthDtlQuery);
            lStmt = lCon.prepareStatement( lStrMnthDtlQuery );            
            lRs = lStmt.executeQuery();
            glogger.info("------------getDivisionDtlsForUpdate after Query--------");
            while(lRs.next())
            {   
            	            	
            	if(lRs.getObject("DIVCODE") != null)
            		lStrDivCode = lRs.getString("DIVCODE");   
            	if(lRs.getObject("divShrtNm") != null)
            		lStrShrtNm = lRs.getString("divShrtNm");   
            	
            	if(lRs.getObject("DIVNAME") != null)
            		lStrDivName = lRs.getString("DIVNAME"); 
            	if(lRs.getObject("DIST") != null)
            		lStrDistName = lRs.getString("DIST"); 
            	if(lRs.getObject("DEPT") != null)
            		lStrDeptName = lRs.getString("DEPT"); 
            	if(lRs.getObject("district_code") != null)
            		lStrDistId = lRs.getString("district_code"); 
            	if(lRs.getObject("lookup_name") != null)
            		lStrDeptCode = lRs.getString("lookup_name"); 
            	
            	divInformationVO.setDivisionId(lStrDivCode);
            	divInformationVO.setDivision_name(lStrDivName);
            	divInformationVO.setDepartment_name(lStrDeptName);
            	divInformationVO.setDistrict_name(lStrDistName);
            	divInformationVO.setDistrictCode(lStrDistId);
            	divInformationVO.setDepartmentCode(lStrDeptCode);
            	divInformationVO.setLc_order_id(lStrShrtNm);
            	
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
        
        return divInformationVO; 
    
    }
	
	
	public ArrayList getChqPostingDtlsForUpdate(String lStrLcExpId) 
    {
		glogger.info("-------Inside getChqPostingDtlsForUpdate of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lStrLcExpId);		
		
		ArrayList lArrChqPosting = new ArrayList();
		TrnLcChequePosting lcChqPostingVo=null;
		StringBuffer lStrBuff = null;
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        long lIChqIdPK=0;        
        Date lDtChqDate=null;
        long lIChqNo=0;
        double lDblChqAmt=0;
        String lStrChqParty="";
        Date lDtChqCancelDt=null;
        
        try
        {
        	glogger.info("------------getChqPostingDtlsForUpdate before Query--------");
        	lCon = DBConnection.getConnection();
            
        	lStrBuff = new StringBuffer("SELECT LC_CHQ_ID,CHEQUE_DT,CHEQUE_NO,CHEQUE_AMT,PARTY_NAME,chq_cancel_dt FROM TRN_LC_CHEQUE_POSTING WHERE ACTIVE='0' AND LC_EXP_ID='"+lStrLcExpId+"'  ORDER BY LC_CHQ_ID");
        	
        	glogger.info("------QUERY FOR GET getChqPostingDtlsForUpdate-----"+lStrBuff.toString());
        	glogger.info("------QUERY FOR GET getChqPostingDtlsForUpdate-----"+lStrBuff.toString());
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getChqPostingDtlsForUpdate after Query--------");
            while(lRs.next())
            {
            	lcChqPostingVo= new TrnLcChequePosting();
            	
            	lIChqIdPK = lRs.getLong("LC_CHQ_ID");
            	if(lRs.getObject("CHEQUE_DT") != null)            	
            		lDtChqDate = lRs.getDate("CHEQUE_DT");  
            	if(lRs.getObject("CHEQUE_NO") != null)
            	    lIChqNo = lRs.getLong("CHEQUE_NO");
            	if(lRs.getObject("CHEQUE_AMT") != null)
            	   lDblChqAmt = lRs.getDouble("CHEQUE_AMT");
            	if(lRs.getObject("PARTY_NAME") != null)
            	   lStrChqParty = lRs.getString("PARTY_NAME");
            	if(lRs.getObject("chq_cancel_dt") != null)            	
            		lDtChqCancelDt = lRs.getDate("chq_cancel_dt");  
            	
            	glogger.info("---------CHEQUE AMT----------"+lDblChqAmt);
            	glogger.info("----------CANCEL DATE------------"+lDtChqCancelDt);
            	NumberFormat lnfLong=NumberFormat.getNumberInstance();
            	//lnfLong.format(lDblChqAmt);
    	        lnfLong.setGroupingUsed(false);
    	        lnfLong.setMaximumFractionDigits(2);
    	        lnfLong.setMinimumFractionDigits(2); 
            	
            	lcChqPostingVo.setLcChqId(new BigDecimal(lIChqIdPK));
            	lcChqPostingVo.setChequeDt(lDtChqDate);
            	lcChqPostingVo.setChequeNo(new BigDecimal(lIChqNo));
            	lcChqPostingVo.setChequeAmt(new BigDecimal(lnfLong.format(lDblChqAmt)));
            	lcChqPostingVo.setPartyName(lStrChqParty);
            	lcChqPostingVo.setChqCancelDt(lDtChqCancelDt);
            	lArrChqPosting.add(lcChqPostingVo);
            	
            	lDtChqCancelDt=null;
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
        
        return lArrChqPosting; 
    
    }
	
	public ArrayList getBudgetPostingDtlsForUpdate(String lStrLcExpId) 
    {
		glogger.info("-------Inside getBudgetPostingDtlsForUpdate of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lStrLcExpId);		
		
		ArrayList lArrBudPosting = new ArrayList();
		TrnLcBudgetPosting lcBudPostingVo=null;
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        long lIBudIdPK=0;        
        String lStrClassOfExp="";
        String lStrFund="";
        double lDblChqAmt=0;
        String lStrDrwOff="";
        String lStrDemandNo="";
        String lStrBudType="";
        int lISchemeNo=0;
        String lStrMjrHd="";
        String lStrSubMjrHd="";
        String lStrMinHd="";
        String lStrSubHd="";
        String lStrDtlHd="";
        String lStrObjHd="";
        double lDblExpAmt=0.0;
        
        try
        {
        	glogger.info("------------getBudgetPostingDtlsForUpdate before Query--------");
        	lCon = DBConnection.getConnection();
            
        	lStrBuff.append("SELECT LC_BUD_ID,CLASS_OF_EXP,FUND,DRW_OFF,DEMAND_NO,BUDGET_TYPE,SCHEME_NO,MJR_HD,SUB_MJR_HD,MIN_HD,SUB_HD,DTL_HD,OBJ_HD,EXP_AMT ");
        	lStrBuff.append("FROM TRN_LC_BUDGET_POSTING WHERE ACTIVE='0' AND LC_EXP_ID='"+lStrLcExpId+"' ORDER BY LC_BUD_ID");
        	
        	glogger.info("------QUERY FOR GET getBudgetPostingDtlsForUpdate-----"+lStrBuff.toString());		
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getBudgetPostingDtlsForUpdate after Query--------");
            while(lRs.next())
            {
            	lcBudPostingVo= new TrnLcBudgetPosting();
            	
            	lIBudIdPK = lRs.getLong("LC_BUD_ID");
            	if(lRs.getObject("CLASS_OF_EXP") != null)            	
            		lStrClassOfExp = lRs.getString("CLASS_OF_EXP");            		     
            	if(lRs.getObject("FUND") != null)
            		lStrFund = lRs.getString("FUND");            	
            	if(lRs.getObject("DRW_OFF") != null)
            		lStrDrwOff = lRs.getString("DRW_OFF");
            	if(lRs.getObject("DEMAND_NO") != null)
            		lStrDemandNo = lRs.getString("DEMAND_NO");
            	if(lRs.getObject("BUDGET_TYPE") != null)
            		lStrBudType = lRs.getString("BUDGET_TYPE");
            	if(lRs.getObject("SCHEME_NO") != null)
            		lISchemeNo = lRs.getInt("SCHEME_NO");
            	if(lRs.getObject("MJR_HD") != null)
            		lStrMjrHd = lRs.getString("MJR_HD");
            	if(lRs.getObject("SUB_MJR_HD") != null)
            		lStrSubMjrHd = lRs.getString("SUB_MJR_HD");
            	if(lRs.getObject("MIN_HD") != null)
            		lStrMinHd = lRs.getString("MIN_HD");
            	if(lRs.getObject("SUB_HD") != null)
            		lStrSubHd = lRs.getString("SUB_HD");
            	if(lRs.getObject("DTL_HD") != null)
            		lStrDtlHd = lRs.getString("DTL_HD");
            	if(lRs.getObject("OBJ_HD") != null)
            		lStrObjHd = lRs.getString("OBJ_HD");
            	if(lRs.getObject("EXP_AMT") != null)
            		lDblExpAmt = lRs.getDouble("EXP_AMT");
            	
            	NumberFormat lnfLong=NumberFormat.getNumberInstance();
            	//lnfLong.format(lDblExpAmt);
    	        lnfLong.setGroupingUsed(false);
    	        lnfLong.setMaximumFractionDigits(2);
    	        lnfLong.setMinimumFractionDigits(2);             	
            	
            	lcBudPostingVo.setLcBudId(new BigDecimal(lIBudIdPK));
            	lcBudPostingVo.setClassOfExp(lStrClassOfExp);
            	lcBudPostingVo.setFund(lStrFund);
            	lcBudPostingVo.setDrwOff(lStrDrwOff);
            	lcBudPostingVo.setDemandNo(lStrDemandNo);
            	lcBudPostingVo.setBudgetType(lStrBudType);
            	lcBudPostingVo.setSchemeNo(lISchemeNo);
            	lcBudPostingVo.setMjrHd(lStrMjrHd);
            	lcBudPostingVo.setSubMjrHd(lStrSubMjrHd);
            	lcBudPostingVo.setMinHd(lStrMinHd);
            	lcBudPostingVo.setSubHd(lStrSubHd);
            	lcBudPostingVo.setDtlHd(lStrDtlHd);
            	lcBudPostingVo.setObjHd(lStrObjHd);
            	lcBudPostingVo.setExpAmt(new BigDecimal(lnfLong.format(lDblExpAmt)));
            	
            	lArrBudPosting.add(lcBudPostingVo);
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
        
        return lArrBudPosting; 
    
    }
	
	public ArrayList getDeductionPostingDtlsForUpdate(String lStrLcExpId) 
    {
		glogger.info("-------Inside getDeductionPostingDtlsForUpdate of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lStrLcExpId);		
		
		ArrayList lArrDedPosting = new ArrayList();
		TrnLcDeductionPosting lcDedPostingVo=null;
		StringBuffer lStrBuff = null;
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        long lIDedIdPK=0;
        double lDblDedAmt=0;
        String lStrEdpCode="";
        
        try
        {
        	glogger.info("------------getDeductionPostingDtlsForUpdate before Query--------");
        	lCon = DBConnection.getConnection();
            
        	lStrBuff = new StringBuffer("SELECT LC_DED_ID,EDP_CODE,AMOUNT FROM TRN_LC_DEDUCTION_POSTING WHERE LC_EXP_ID='"+lStrLcExpId+"' AND ACTIVE='0' ");
            
        	glogger.info("------QUERY FOR GET getDeductionPostingDtlsForUpdate-----"+lStrBuff.toString());
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getDeductionPostingDtlsForUpdate after Query--------");
            while(lRs.next())
            {
            	lcDedPostingVo= new TrnLcDeductionPosting();
            	
            	lIDedIdPK = lRs.getLong("LC_DED_ID");
            	if(lRs.getObject("EDP_CODE") != null)            	
            		lStrEdpCode = lRs.getString("EDP_CODE");
            	if(lRs.getObject("AMOUNT") != null)
            		lDblDedAmt = lRs.getDouble("AMOUNT");
            	
            	NumberFormat lnfLong=NumberFormat.getNumberInstance();
            	//lnfLong.format(lDblDedAmt);
    	        lnfLong.setGroupingUsed(false);
    	        lnfLong.setMaximumFractionDigits(2);
    	        lnfLong.setMinimumFractionDigits(2); 
            	
            	lcDedPostingVo.setLcDedId(new BigDecimal(lIDedIdPK));
            	lcDedPostingVo.setEdpCode(lStrEdpCode);
            	lcDedPostingVo.setAmount(new BigDecimal(lnfLong.format(lDblDedAmt)));
            	
            	lArrDedPosting.add(lcDedPostingVo);
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
        
        return lArrDedPosting; 
    
    }
	
	public boolean deleteDtlPosting(String lStrLcExpId) 
    {
		glogger.info("-------Inside deleteDtlPosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lStrLcExpId);	
		
		StringBuffer lStrBuff = null;
		long lLngLcExpId=Long.parseLong(lStrLcExpId);
		
		glogger.info("the value of the lStrLcExpId AFTER CONV is "+lLngLcExpId);	
		
        int lINoOfRowUpd=0;
        boolean deleteResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------deleteDtlPosting before Query--------");        	
            
        	lStrBuff = new StringBuffer("UPDATE TRN_LC_DTL_POSTING SET ACTIVE='1' WHERE LC_EXP_ID="+lLngLcExpId);
            		
        	glogger.info("------QUERY FOR DELETE deleteDtlPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------deleteDtlPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	deleteResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return deleteResult; 
    
    }
	
	public boolean deleteChequePosting(long lILcExpId,long lIChqId) 
    {
		glogger.info("-------Inside deleteDtlPosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lILcExpId);	
		
		StringBuffer lStrBuff = null;
		
        int lINoOfRowUpd=0;
        boolean deleteResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------deleteChqPosting before Query--------");        	
            
        	if(lIChqId==0)
        	   lStrBuff = new StringBuffer("UPDATE TRN_LC_CHEQUE_POSTING SET ACTIVE='1' WHERE LC_EXP_ID="+lILcExpId);
        	else
        	   lStrBuff = new StringBuffer("UPDATE TRN_LC_CHEQUE_POSTING SET ACTIVE='1' WHERE LC_EXP_ID="+lILcExpId+" AND LC_CHQ_ID="+lIChqId);
        	
        	glogger.info("------QUERY FOR DELETE deleteChqPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------deleteChqPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	deleteResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return deleteResult; 
    
    }
	
	public boolean deleteBudgetPosting(long lILcExpId,long lIBudId) 
    {
		glogger.info("-------Inside deleteDtlPosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lILcExpId);	
		
		StringBuffer lStrBuff = null;
		
        int lINoOfRowUpd=0;
        boolean deleteResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------deleteBudgetPosting before Query--------");        	
            
        	if(lIBudId==0)
        		lStrBuff = new StringBuffer("UPDATE TRN_LC_BUDGET_POSTING SET ACTIVE='1' WHERE LC_EXP_ID="+lILcExpId);
        	else
        		lStrBuff = new StringBuffer("UPDATE TRN_LC_BUDGET_POSTING SET ACTIVE='1' WHERE LC_EXP_ID="+lILcExpId+" AND LC_BUD_ID="+lIBudId);
        	
        	glogger.info("------QUERY FOR DELETE deleteBudgetPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------deleteBudgetPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	deleteResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return deleteResult; 
    
    }
	
	public boolean deleteDeductionPosting(String lStrLcExpId) 
    {
		glogger.info("-------Inside deleteDeductionPosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lStrLcExpId);	
		
		StringBuffer lStrBuff = null;
		long lLngLcExpId=Long.parseLong(lStrLcExpId);
		
		glogger.info("the value of the lStrLcExpId AFTER CONV is "+lLngLcExpId);	
		
        int lINoOfRowUpd=0;
        boolean deleteResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------deleteDeductionPosting before Query--------");        	
            
        	lStrBuff = new StringBuffer("UPDATE TRN_LC_DEDUCTION_POSTING SET ACTIVE='1' WHERE LC_EXP_ID="+lLngLcExpId);
            		
        	glogger.info("------QUERY FOR DELETE deleteDeductionPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------deleteDeductionPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	deleteResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return deleteResult; 
    
    }
	
	public ArrayList getTreasuryLcUsageReport(long lLngLoginLangId) 
	{
		glogger.info("-------Inside getTreasuryLcUsageReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcUsageTsry = null;	
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        int lISrNo=0;        
        String lStrTsryName="";
        String lStrLstAccessDate="";
        int lIDistCode=0;
        
        try
        {
        	glogger.info("------------getTreasuryLcUsageReport before Query--------");
        	lCon = DBConnection.getConnection();
            
        	lStrBuff.append("select dist.district_name distName, (select to_char(max(dtl.created_date),'dd/mm/yyyy') from trn_lc_dtl_posting dtl ");
        	lStrBuff.append("where dtl.division_code in (select loc.location_code from cmn_location_mst loc where loc.loc_district_id = dist.district_id and ");
        	lStrBuff.append("loc.lang_id ="+lLngLoginLangId+")) lstDate, dist.district_id distCode  from cmn_district_mst dist ");
        	lStrBuff.append("where dist.district_code is not null and dist.lang_id ="+lLngLoginLangId+" and dist.district_id not in(1) order by dist.district_name");
        	
        	glogger.info("------QUERY FOR GET getTreasuryLcUsageReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getTreasuryLcUsageReport after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcUsageTsry=new ArrayList();
            	
            	lISrNo++;
            	
            	if(lRs.getObject("distName") != null)            	
            		lStrTsryName = lRs.getString("distName");
            	
            	if(lRs.getObject("lstDate") != null)
            		lStrLstAccessDate = lRs.getString("lstDate");
            	else
            		lStrLstAccessDate = "--";
            	
            	if(lRs.getObject("distCode") != null)
            		lIDistCode = lRs.getInt("distCode");
            	
            	glogger.info("---"+lISrNo+"---"+lStrTsryName+"----"+lStrLstAccessDate+"----"+lIDistCode);
            	lArrLcUsageTsry.add(lISrNo);
            	lArrLcUsageTsry.add(lStrTsryName);
            	lArrLcUsageTsry.add(lStrLstAccessDate);
            	lArrLcUsageTsry.add(lIDistCode);
            	
            	lArrOuterLst.add(lArrLcUsageTsry);
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
        
        return lArrOuterLst; 
	}
	
	public ArrayList getDivisionLcUsageReport(long liDistCode,long lLngLoginLangId) 
	{
		glogger.info("-------Inside getDivisionLcUsageReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcUsageDiv = null;	
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		glogger.info("--------DistCode in getDivisionLcUsageReport----- "+liDistCode);
       
		//String lStrLcDivision="LC_DIV";
		String lStrLcDivision = bundleConst.getString("LC_DIV");
		glogger.info("---------get LC_DIV----------------"+lStrLcDivision);
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        int lISrNo=0;        
        String lStrDivName="";
        String lStrLstAccessDate="";
       
        
        try
        {
        	glogger.info("------------getDivisionLcUsageReport before Query--------");
        	lCon = DBConnection.getConnection();
            
        	lStrBuff.append("select div.loc_name divName, (select to_char(max(dtl.created_date),'dd/mm/yyyy') from trn_lc_dtl_posting dtl where dtl.division_code = div.location_code) lstDate ");
        	lStrBuff.append("from (select loc.location_code,loc.loc_name from cmn_location_mst loc ");
        	lStrBuff.append("where loc.department_id = (select dept.department_id from org_department_mst dept where dept.dep_short_name='"+lStrLcDivision+"') ");
        	lStrBuff.append("and loc.loc_district_id =");
        	lStrBuff.append("(select dist.district_id from cmn_district_mst dist where dist.district_id="+liDistCode+" and dist.lang_id="+lLngLoginLangId+")) div");
        	
        	glogger.info("------QUERY FOR GET getDivisionLcUsageReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getDivisionLcUsageReport after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcUsageDiv=new ArrayList();
            	
            	lISrNo++;
            	
            	if(lRs.getObject("divName") != null)            	
            		lStrDivName = lRs.getString("divName");
            	
            	if(lRs.getObject("lstDate") != null)
            		lStrLstAccessDate = lRs.getString("lstDate");
            	else
            		lStrLstAccessDate = "--";            	
            	
            	
            	glogger.info("---"+lISrNo+"---"+lStrDivName+"----"+lStrLstAccessDate);
            	lArrLcUsageDiv.add(lISrNo);
            	lArrLcUsageDiv.add(lStrDivName);
            	lArrLcUsageDiv.add(lStrLstAccessDate);
            	
            	
            	lArrOuterLst.add(lArrLcUsageDiv);
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
        
        return lArrOuterLst; 
	}
	
	public boolean updateDtlPosting(TrnLcDtlPosting lcDtlPostingVo) 
    {
		glogger.info("-------Inside updateDtlPosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lcDtlPostingVo.getLcExpId());	
		
		String lStrAdvDate=lcDtlPostingVo.getAdviceDt().toString();
		String lStrLcValidFrm=lcDtlPostingVo.getLcValidFrm().toString();
		
		Date dt=lcDtlPostingVo.getUpdatedDate();
		java.sql.Date sqldt=new java.sql.Date(dt.getTime());
		String lStrUpdDate=sqldt.toString();		
		
		lStrAdvDate=changeDateFormat(lStrAdvDate);
		lStrLcValidFrm=changeDateFormat(lStrLcValidFrm);
		lStrUpdDate=changeDateFormat(lStrUpdDate);
		
		StringBuffer lStrBuff = new StringBuffer();	
		
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------updateDtlPosting before Query--------");        	
            
        	lStrBuff.append("UPDATE TRN_LC_DTL_POSTING DTL SET DTL.DIVISION_CODE='"+lcDtlPostingVo.getDivisionCode()+"',");
        	lStrBuff.append("DTL.ADVICE_NO="+lcDtlPostingVo.getAdviceNo()+",DTL.ADVICE_DT=TO_DATE('"+lStrAdvDate+"','dd/MM/yyyy'),");
        	lStrBuff.append("DTL.MONTH_CODE='"+lcDtlPostingVo.getMonthCode()+"',DTL.OPENING_LC="+lcDtlPostingVo.getOpeningLc()+",");
        	lStrBuff.append("DTL.LC_VALID_FRM=TO_DATE('"+lStrLcValidFrm+"','dd/MM/yyyy'),DTL.DIV_OFF='"+lcDtlPostingVo.getDivOff()+"',");
        	lStrBuff.append("DTL.DRW_OFF='"+lcDtlPostingVo.getDrwOff()+"',DTL.TSRY_OFF='"+lcDtlPostingVo.getTsryOff()+"',");
        	lStrBuff.append("DTL.CLOSING_LC="+lcDtlPostingVo.getClosingLc()+",DTL.BANK_CODE='"+lcDtlPostingVo.getBankCode()+"',");
        	lStrBuff.append("DTL.UPDATED_USER_ID="+lcDtlPostingVo.getUpdatedUserId()+",DTL.UPDATED_DATE=TO_DATE('"+lStrUpdDate+"','dd/MM/yyyy'),");
        	lStrBuff.append("DTL.UPDATED_POST_ID="+lcDtlPostingVo.getUpdatedPostId());
        	
        	lStrBuff.append(" WHERE DTL.LC_EXP_ID="+lcDtlPostingVo.getLcExpId());
            		
        	glogger.info("------QUERY FOR UPDATE updateDtlPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------updateDtlPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	public boolean updateChequePosting(TrnLcChequePosting lcChqPostingVo) 
    {
		glogger.info("-------Inside updateChequePosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lcChqPostingVo.getLcExpId());	
		
		String lStrChqDate=lcChqPostingVo.getChequeDt().toString();
		
		Date dt=lcChqPostingVo.getUpdatedDate();
		java.sql.Date sqldt=new java.sql.Date(dt.getTime());
		String lStrUpdDate=sqldt.toString();
		
		lStrChqDate=changeDateFormat(lStrChqDate);
		lStrUpdDate=changeDateFormat(lStrUpdDate);
		
		StringBuffer lStrBuff = new StringBuffer();	
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------updateChequePosting before Query--------");        	
            
        	lStrBuff.append("UPDATE TRN_LC_CHEQUE_POSTING CHQ SET CHQ.CHEQUE_NO="+lcChqPostingVo.getChequeNo()+",");
        	lStrBuff.append("CHQ.CHEQUE_DT=TO_DATE('"+lStrChqDate+"','dd/MM/yyyy'),CHQ.CHEQUE_AMT="+lcChqPostingVo.getChequeAmt()+",");
        	lStrBuff.append("CHQ.PARTY_NAME='"+lcChqPostingVo.getPartyName()+"',CHQ.UPDATED_USER_ID="+lcChqPostingVo.getUpdatedUserId()+",");
        	lStrBuff.append("CHQ.UPDATED_DATE=TO_DATE('"+lStrUpdDate+"','dd/MM/yyyy'),CHQ.UPDATED_POST_ID="+lcChqPostingVo.getUpdatedPostId());
        	
        	lStrBuff.append("WHERE CHQ.LC_CHQ_ID="+lcChqPostingVo.getLcChqId()+"AND CHQ.LC_EXP_ID="+lcChqPostingVo.getLcExpId());
        	lStrBuff.append(" AND CHQ.CHQ_CANCEL_DT IS NULL ");
        	
        	glogger.info("------QUERY FOR UPDATE updateChequePosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------updateChequePosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	public boolean updateBudgetPosting(TrnLcBudgetPosting lcBudPostingVo) 
    {
		glogger.info("-------Inside updateChequePosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lcBudPostingVo.getLcExpId());	
		
		Date dt=lcBudPostingVo.getUpdatedDate();
		java.sql.Date sqldt=new java.sql.Date(dt.getTime());
		String lStrUpdDate=sqldt.toString();
		
		lStrUpdDate=changeDateFormat(lStrUpdDate);
		
		StringBuffer lStrBuff = new StringBuffer();	
		//String lStrUpdDate=lcBudPostingVo.getLcExpId().toString();
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------updateBudgetPosting before Query--------");        	
            
        	lStrBuff.append("UPDATE TRN_LC_BUDGET_POSTING BUD SET BUD.CLASS_OF_EXP='"+lcBudPostingVo.getClassOfExp()+"',BUD.FUND='"+lcBudPostingVo.getFund()+"',");
        	lStrBuff.append("BUD.DRW_OFF='"+lcBudPostingVo.getDrwOff()+"',BUD.DEMAND_NO='"+lcBudPostingVo.getDemandNo()+"',");
        	lStrBuff.append("BUD.BUDGET_TYPE='"+lcBudPostingVo.getBudgetType()+"',BUD.SCHEME_NO="+lcBudPostingVo.getSchemeNo()+",");
        	lStrBuff.append("BUD.MJR_HD='"+lcBudPostingVo.getMjrHd()+"',BUD.SUB_MJR_HD='"+lcBudPostingVo.getSubMjrHd()+"',");
        	lStrBuff.append("BUD.MIN_HD='"+lcBudPostingVo.getMinHd()+"',BUD.SUB_HD='"+lcBudPostingVo.getSubHd()+"',");
        	lStrBuff.append("BUD.DTL_HD='"+lcBudPostingVo.getDtlHd()+"',BUD.OBJ_HD='"+lcBudPostingVo.getObjHd()+"',");
        	lStrBuff.append("BUD.EXP_AMT="+lcBudPostingVo.getExpAmt()+",");
        	lStrBuff.append("BUD.UPDATED_USER_ID="+lcBudPostingVo.getUpdatedUserId()+",BUD.UPDATED_DATE=TO_DATE('"+lStrUpdDate+"','dd/MM/yyyy'),");
        	lStrBuff.append("BUD.UPDATED_POST_ID="+lcBudPostingVo.getUpdatedPostId());
        	
        	lStrBuff.append("WHERE BUD.LC_BUD_ID="+lcBudPostingVo.getLcBudId()+" AND BUD.LC_EXP_ID="+lcBudPostingVo.getLcExpId());
            		
        	glogger.info("------QUERY FOR UPDATE updateBudgetPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------updateBudgetPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	public boolean updateDeductionPosting(TrnLcDeductionPosting lcDedPostingVo) 
    {
		glogger.info("-------Inside updateDeductionPosting of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lcDedPostingVo.getLcExpId());	
		
		Date dt=lcDedPostingVo.getUpdatedDate();
		java.sql.Date sqldt=new java.sql.Date(dt.getTime());
		String lStrUpdDate=sqldt.toString();
		
		lStrUpdDate=changeDateFormat(lStrUpdDate);
		
		StringBuffer lStrBuff = new StringBuffer();	
		
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------updateDeductionPosting before Query--------");        	
            
        	lStrBuff.append("UPDATE TRN_LC_DEDUCTION_POSTING DED SET DED.AMOUNT="+lcDedPostingVo.getAmount()+",DED.UPDATED_USER_ID="+lcDedPostingVo.getUpdatedUserId()+",DED.UPDATED_DATE=TO_DATE('"+lStrUpdDate+"','dd/MM/yyyy'),");
        	lStrBuff.append("DED.UPDATED_POST_ID="+lcDedPostingVo.getUpdatedPostId());
        	lStrBuff.append("WHERE DED.LC_EXP_ID="+lcDedPostingVo.getLcExpId()+" AND DED.EDP_CODE='"+lcDedPostingVo.getEdpCode()+"'");
        	
        	glogger.info("------QUERY FOR UPDATE updateDeductionPosting-----"+lStrBuff.toString());		
            Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------updateDeductionPosting after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	//Method to change Date format from yyyy-mm-dd to dd/mm/yyyy
	public String changeDateFormat(String lStrDtFrm)
	{
        StringTokenizer strToken =null;
		
		strToken=new StringTokenizer(lStrDtFrm,"-");
		String yyyy=strToken.nextToken();
		String mm=strToken.nextToken();
		String dd=strToken.nextToken();
		lStrDtFrm=dd+"/"+mm+"/"+yyyy; 
		
		return lStrDtFrm;
	}
	
	//Method to get Loc Id by incoming Logged in Post Id
	public long getLocIdByPostId(long lLngPostId,long lLngLoginLangId)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
		long lLngLocId =0;
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lsb = new StringBuffer();
			
		    lsb.append("SELECT LOC.LOCATION_CODE LOC FROM CMN_LOCATION_MST LOC WHERE LOC.LANG_ID="+lLngLoginLangId+" and LOC.LOCATION_CODE=( ");
		    lsb.append("SELECT PST.LOCATION_CODE LOC FROM ORG_POST_MST PST WHERE PST.POST_ID="+lLngPostId+" )");
		    
		    glogger.info("--------QUERY FOR GET LOC ID BY POST ID--------"+lsb.toString());
		    lStmt = lCon.prepareStatement( lsb.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lLngLocId = Long.parseLong(lRs.getString("LOC"));
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
		glogger.info("---------LOGIN LOC ID---------"+lLngLocId);
		return lLngLocId;
	}	
	
	//-------METHOD FOR LC TREASURY REPORT FOR VARIFICATION
	
	public ArrayList getDivisionsOfLoginTsry(Hashtable otherArgs,String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getDivisionsOfLoginTsry of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
		Hashtable sessionKeys = (Hashtable) ((Hashtable) otherArgs).get(IReportConstants.SESSION_KEYS);

		Map loginMap = (Map)sessionKeys.get("loginDetailsMap");

		long lLngPostId=0;
		lLngPostId =Long.parseLong(loginMap.get("primaryPostId").toString());
        glogger.info("--------PRIMARY POST ID---------"+lLngPostId);
		
		//lLngPostId=100102;//---------->Need to Comment 
		glogger.info("--------PRIMARY POST ID---------"+lLngPostId);
		
		//String lStrDeptShrtName="LC_DIV"; //------>come from Constant
		String lStrDeptShrtName = bundleConst.getString("LC_DIV");
		glogger.info("---------get LC_DIV----------------"+lStrDeptShrtName);
		
		long lLngLangId=(long)getLongLangId(lStrLangId);
		glogger.info("-----LANG ID---------"+lLngLangId);
		
		ArrayList lArrDivLst = new ArrayList();
		ComboValuesVO comboVo=null;
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDivId=null;
        String lStrDivCode=null;
        try
        {        	
        	lCon = DBConnection.getConnection();
            StringBuffer lStrBuff = new StringBuffer();
            
            lStrBuff.append("select loc.location_code locCode, loc.loc_short_name locShrtNm from cmn_location_mst loc where loc.activate_flag='1' and loc.loc_district_id= ");
            lStrBuff.append("(select loc.loc_district_id from cmn_location_mst loc where loc.activate_flag='1' and loc.lang_id="+lLngLangId+" and loc.location_code= ");
            lStrBuff.append("(select pst.location_code from org_post_mst pst where pst.post_id="+lLngPostId+" )) and ");
            lStrBuff.append("loc.department_id= (select dept.department_id from org_department_mst dept where dept.dep_short_name='"+lStrDeptShrtName+"' and dept.lang_id="+lLngLangId+")");
            lStrBuff.append(" order by loc.loc_short_name ");
            
            glogger.info("------------QUERY FOR GET getDivisionsOfLoginTsry -------"+lStrBuff.toString());		
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            
            while(lRs.next())
            {
            	comboVo= new ComboValuesVO();
            	if(lRs.getObject("locCode") != null )
            		lStrDivId = lRs.getString("locCode");
            	if(lRs.getObject("locShrtNm") != null )
            		lStrDivCode = lRs.getString("locShrtNm");
                
            	comboVo.setId(lStrDivId);
            	comboVo.setDesc(lStrDivCode);
                
            	lArrDivLst.add(comboVo);
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
        
        return lArrDivLst; 
    
    }
	
	public ArrayList getDivisionsOfLoginTsry(String lStrLangId, long lLngPostId) 
    {
		glogger.info("-------Inside getDivisionsOfLoginTsry of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lLngPostId);
		
		//Hashtable sessionKeys = (Hashtable) ((Hashtable) otherArgs).get(IReportConstants.SESSION_KEYS);

		//Map loginMap = (Map)sessionKeys.get("loginDetailsMap");

		//long lLngPostId=0;
		//lLngPostId =Long.parseLong(loginMap.get("primaryPostId").toString());
        //glogger.info("--------PRIMARY POST ID---------"+lLngPostId);
		
		//lLngPostId=100102;//---------->Need to Comment 
		//glogger.info("--------PRIMARY POST ID---------"+lLngPostId);
		
		//String lStrDeptShrtName="LC_DIV"; //------>come from Constant
		String lStrDeptShrtName = bundleConst.getString("LC_DIV");
		glogger.info("---------get LC_DIV----------------"+lStrDeptShrtName);
		
		long lLngLangId=(long)getLongLangId(lStrLangId);
		glogger.info("-----LANG ID---------"+lLngLangId);
		
		ArrayList lArrDivLst = new ArrayList();
		ComboValuesVO comboVo=null;
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDivId=null;
        String lStrDivCode=null;
        try
        {        	
        	lCon = DBConnection.getConnection();
            StringBuffer lStrBuff = new StringBuffer();
            
            lStrBuff.append("select loc.location_code locCode, loc.loc_short_name locShrtNm from cmn_location_mst loc where loc.activate_flag='1' and loc.loc_district_id= ");
            lStrBuff.append("(select loc.loc_district_id from cmn_location_mst loc where loc.activate_flag='1' and loc.lang_id="+lStrLangId+" and loc.location_code= ");
            lStrBuff.append("(select pst.location_code from org_post_mst pst where pst.post_id="+lLngPostId+" )) and ");
            lStrBuff.append("loc.department_id= (select dept.department_id from org_department_mst dept where dept.dep_short_name='"+lStrDeptShrtName+"' and dept.lang_id="+lStrLangId+")");
            
            glogger.info("------------QUERY FOR GET getDivisionsOfLoginTsry -------"+lStrBuff.toString());		
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            
            while(lRs.next())
            {
            	comboVo= new ComboValuesVO();
            	if(lRs.getObject("locCode") != null )
            		lStrDivId = lRs.getString("locCode");
            	if(lRs.getObject("locShrtNm") != null )
            		lStrDivCode = lRs.getString("locShrtNm");
                
            	comboVo.setId(lStrDivId);
            	comboVo.setDesc(lStrDivCode);
                
            	lArrDivLst.add(comboVo);
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
        
        return lArrDivLst; 
    
    }

	public ArrayList getDepartment(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getDepartment of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
				
		ArrayList lArrDeptLst = new ArrayList();
		ComboValuesVO comboVo=null;
		
		//String lStrDeptLkpId='150004';//---->Constant file
		String lStrDeptLkpId=bundleConst.getString("LC_DEPT_LOOKUP_ID");
		
		long lLngLangId= (long)getLongLangId(lStrLangId);
		glogger.info("-----LANG ID---------"+lLngLangId);
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDeptId=null;
        String lStrDeptNm=null;
        try
        {        	
        	lCon = DBConnection.getConnection();
            StringBuffer lStrBuff = new StringBuffer();
            
            lStrBuff.append("select lkp.lookup_id lookupId,lkp.lookup_desc lookupName from cmn_lookup_mst lkp where lkp.parent_lookup_id='"+lStrDeptLkpId+"' and lkp.lang_id="+lLngLangId);
            
            glogger.info("------------QUERY FOR GET getDepartment -------"+lStrBuff.toString());		
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            
            while(lRs.next())
            {
            	comboVo= new ComboValuesVO();
            	lStrDeptId = lRs.getString("lookupId");
            	lStrDeptNm = lRs.getString("lookupName");
                
            	comboVo.setId(lStrDeptId);
            	comboVo.setDesc(lStrDeptNm);
                
            	lArrDeptLst.add(comboVo);
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
        
        return lArrDeptLst; 
    
    }
	
	public ArrayList getApproved(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getDivisionsOfLoginTsry of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
			
		ArrayList lArrApprovedLst = new ArrayList();
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.APPROVED_YES_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.APPROVED_YES"));
		lArrApprovedLst.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.APPROVED_NO_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.APPROVED_NO"));
		
		lArrApprovedLst.add(comboVo);
       
        
        return lArrApprovedLst;     
    }
	
	public ArrayList getLcTsryReportForVerification(String lStrDivisionCode,int lIAdvNo,String lStrMonthCode,String lStrBankCode,String lStrDeptCode,String lStrApprovedCode,String lStrFrmDt,String lStrToDt,long lLngLangId,long lLngLoginLocId,long lIFinYrId)
	{
	    glogger.info("------ Inside getLcTsryReportForVerification() ---------- ");
		glogger.info("--------PARAMETER----lStrDivCode---------"+lStrDivisionCode);
		glogger.info("--------PARAMETER----lStrDeptCode---------"+lStrDeptCode);
		glogger.info("--------PARAMETER----lStrApprovedCode---------"+lStrApprovedCode);
		Connection lCon=null;
		Statement lStmt=null;
		ResultSet rs=null;			
		
		glogger.info("-------LOGIN LANG ID IN EXP REPORT QUERY---------"+lLngLangId);
		glogger.info("-------LOGIN LOC ID IN EXP REPORT QUERY---------"+lLngLoginLocId);
		//lIDivisionId=lLngLoginLocId; //LOGIN LOC ID
		//lIDivisionId=100561; //<<<<---------need to replace..by login division Id
		
		String lStrLangId=getStrLangId(lLngLangId);
		
		long lILcExpId=0;
		String lStrDivCode="";
		String lStrLocShrtNm="";
		int lIAdviceNo=0;
		String lStrApproved="";
		String lStrEntryDate="";
		String lStrDistrictName="";
		String lStrDeptName="";
		String lStrDivisionName="";
		String lStrBankName="";
		double lDblTotExpAmt=0.0;
		double lDblTotDedAmt=0.0;
		String lStrMonth="";
		String lStrDelete="";
		int lISrNo=0;
		ArrayList lArrOuterLst=new ArrayList();
		ArrayList lArrInnerLst=null;
		
		StringBuffer lSBuff=new StringBuffer();		
		
		lSBuff.append("SELECT (SELECT LOC.LOCATION_CODE FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+") DIVCODE, ");
		lSBuff.append("(SELECT LOC.LOC_SHORT_NAME FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+") LOC_SHORT_NAME, ");
		lSBuff.append(" DTL.LC_EXP_ID EXPID, DTL.ADVICE_NO ADVNO,DTL.ADVICE_APPROVED APPROVED, TO_CHAR(DTL.CREATED_DATE,'DD/MM/YYYY') ENTRY_DATE, ");
		lSBuff.append("(SELECT DIST.DISTRICT_NAME FROM CMN_DISTRICT_MST DIST WHERE DIST.DISTRICT_ID=( ");
		lSBuff.append("SELECT LOC.LOC_DISTRICT_ID FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+")) DISTRICT, ");
		lSBuff.append("(SELECT LOOK.LOOKUP_DESC FROM CMN_LOOKUP_MST LOOK WHERE LOOK.LOOKUP_ID=( ");
		lSBuff.append("SELECT LOC.TYPE_LOOKUP_ID FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+")) DEPT, ");
		lSBuff.append("(SELECT LOC.LOC_NAME FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+") DIVISION, ");
		lSBuff.append("(SELECT BANK.BANK_NAME FROM MST_BANK BANK WHERE BANK.ACTIVATE_FLAG='1' AND BANK.LANG_ID="+lLngLangId+" AND BANK.BANK_CODE=( ");
		lSBuff.append("SELECT DTLS.BANK_CODE FROM TRN_LC_DTL_POSTING DTLS WHERE DTLS.DIVISION_CODE='"+lStrDivisionCode+"' AND DTLS.LC_EXP_ID=DTL.LC_EXP_ID AND DTL.ACTIVE='0')) BANK, ");
		lSBuff.append("(SELECT NVL(SUM(BUD.EXP_AMT),0) FROM TRN_LC_BUDGET_POSTING BUD WHERE BUD.LC_EXP_ID=DTL.LC_EXP_ID AND BUD.ACTIVE='0') EXP_AMT, ");
		lSBuff.append("(SELECT NVL(SUM(DED.AMOUNT),0) FROM TRN_LC_DEDUCTION_POSTING DED WHERE DED.LC_EXP_ID=DTL.LC_EXP_ID AND DED.ACTIVE='0') DED_AMT, ");
		lSBuff.append("(SELECT MON.MONTH_NAME FROM SGVA_MONTH_MST MON WHERE MON.MONTH_CODE=( ");
		lSBuff.append("SELECT DTLS.MONTH_CODE FROM TRN_LC_DTL_POSTING DTLS WHERE DTLS.LC_EXP_ID=DTL.LC_EXP_ID AND DTL.DIVISION_CODE='"+lStrDivisionCode+"') AND MON.LANG_ID='"+lStrLangId+"') MONTH_NM ");
		
		lSBuff.append("FROM TRN_LC_DTL_POSTING DTL ");
		lSBuff.append("WHERE DTL.DIVISION_CODE ='"+lStrDivisionCode+"' AND DTL.ACTIVE='0' ");
		
		//QUERY ON CONDITION BASIS----------------------------------
		
		if(lIAdvNo != 0 )
		{	
		     lSBuff.append("AND DTL.ADVICE_NO="+lIAdvNo);
		     glogger.info("---------PARAMETER lIAdvNo------------"+lIAdvNo);
		}
		if(lStrMonthCode != null && !lStrMonthCode.equals("") && !lStrMonthCode.equals("-1"))
		{	
		     lSBuff.append("AND DTL.MONTH_CODE='"+lStrMonthCode+"'");
		     glogger.info("---------PARAMETER lStrMonthCode------------"+lStrMonthCode);
		}
		if(lStrBankCode != null && !lStrBankCode.equals("") && !lStrBankCode.equals("-1"))
		{	
		     lSBuff.append("AND DTL.BANK_CODE='"+lStrBankCode+"'");
		     glogger.info("---------PARAMETER lStrBankCode------------"+lStrBankCode);
		}
		if(lStrDeptCode != null && !lStrDeptCode.equals("") && !lStrDeptCode.equals("-1"))
		{	
		     lSBuff.append("AND '"+lStrDeptCode+"'= ");
		     lSBuff.append("(SELECT LOOK.LOOKUP_ID FROM CMN_LOOKUP_MST LOOK WHERE LOOK.LOOKUP_ID=(  ");
		     lSBuff.append("SELECT LOC.TYPE_LOOKUP_ID FROM CMN_LOCATION_MST LOC WHERE LOC.LOCATION_CODE='"+lStrDivisionCode+"' AND LOC.LANG_ID="+lLngLangId+")) ");
		     glogger.info("---------PARAMETER lStrDeptCode------------"+lStrDeptCode);
		}
		if(lStrApprovedCode != null && !lStrApprovedCode.equals("") && !lStrApprovedCode.equals("-1"))
		{	
		     lSBuff.append("AND DTL.ADVICE_APPROVED='"+lStrApprovedCode+"'");		    
		     glogger.info("---------PARAMETER lStrDeptCode------------"+lStrApprovedCode);
		}
		if((lStrFrmDt != null && !lStrFrmDt.equals("")) && (lStrToDt == null || lStrToDt.equals("")) )
		{	
		     lSBuff.append("AND DTL.CREATED_DATE >= TO_DATE('"+lStrFrmDt+"','DD/MM/YYYY') AND DTL.CREATED_DATE <= SYSDATE");
		     glogger.info("---------PARAMETER lStrEntryDtFrmDt------------"+lStrFrmDt);
		}
		if((lStrToDt != null && !lStrToDt.equals("")) && (lStrFrmDt == null || lStrFrmDt.equals("")))
		{	
		    lSBuff.append("AND DTL.CREATED_DATE >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id="+lIFinYrId+")"); 
			lSBuff.append("AND DTL.CREATED_DATE <= TO_DATE('"+lStrToDt+"','DD/MM/YYYY')");
		    glogger.info("---------PARAMETER lStrEntryDtToDt------------"+lStrToDt);
		}
		if(lStrFrmDt != null && !lStrFrmDt.equals("")  && (lStrToDt != null && !lStrToDt.equals("")))
		{	
			lSBuff.append("AND DTL.CREATED_DATE >= TO_DATE('"+lStrFrmDt+"','DD/MM/YYYY')");
			lSBuff.append("AND DTL.CREATED_DATE <= TO_DATE('"+lStrToDt+"','DD/MM/YYYY')");
			glogger.info("---------PARAMETER 1 lStrEntryDtFrmDt------------"+lStrFrmDt);
		    glogger.info("---------PARAMETER 2 lStrEntryDtToDt------------"+lStrToDt);
		}
		
		lSBuff.append(" ORDER BY DTL.CREATED_DATE ,DTL.ADVICE_NO  ");
		
		
		//------------QUERY END--------------------------------------------------------------------
		try
		{
			glogger.info("-----In try of getLcTsryReportForVerification---------");
			lCon=getSession().connection();			
			lStmt=lCon.createStatement();
			glogger.info("-----QUERY FOR GET getLcTsryReportForVerification--------"+lSBuff.toString());
			rs=lStmt.executeQuery(lSBuff.toString());
			glogger.info("----------Query Executed-----------------------");
			
			while(rs.next())
			{
				lISrNo++;
				if(rs.getObject("EXPID")!= null)
				    lILcExpId=rs.getLong("EXPID");
				if(rs.getObject("ADVNO")!= null)
				    lIAdviceNo=rs.getInt("ADVNO");
				if(rs.getObject("DIVCODE")!= null)
					lStrDivCode=rs.getString("DIVCODE");
				if(rs.getObject("LOC_SHORT_NAME")!= null)
					lStrLocShrtNm=rs.getString("LOC_SHORT_NAME");
				if(rs.getObject("APPROVED")!= null)
					lStrApproved=rs.getString("APPROVED");
				if(rs.getObject("ENTRY_DATE")!= null)
					lStrEntryDate=rs.getString("ENTRY_DATE");
				if(rs.getObject("DISTRICT")!= null)
				    lStrDistrictName=rs.getString("DISTRICT");
				if(rs.getObject("DEPT")!= null)
				    lStrDeptName=rs.getString("DEPT");
				if(rs.getObject("DIVISION")!= null)
				    lStrDivisionName=rs.getString("DIVISION");
				if(rs.getObject("BANK")!= null)
				    lStrBankName=rs.getString("BANK");
				if(rs.getObject("EXP_AMT")!= null)
				    lDblTotExpAmt=rs.getDouble("EXP_AMT");
				if(rs.getObject("DED_AMT")!= null)
				    lDblTotDedAmt=rs.getDouble("DED_AMT");
				if(rs.getObject("MONTH_NM")!= null)
				    lStrMonth=rs.getString("MONTH_NM");
				
				lStrDelete="Delete";
				
				glogger.info("--------lISrNo----------"+lISrNo);
				glogger.info("--------lStrDivCode----------"+lStrDivCode);
				glogger.info("--------lStrLocShrtNm----------"+lStrLocShrtNm);
				glogger.info("--------lIAdviceNo----------"+lIAdviceNo);
				glogger.info("--------lStrEntryDate----------"+lStrEntryDate);
				glogger.info("--------lStrDistrictName----------"+lStrDistrictName);
				glogger.info("--------lStrDeptName----------"+lStrDeptName);
				glogger.info("--------lStrDivisionName----------"+lStrDivisionName);
				glogger.info("--------lStrBankName----------"+lStrBankName);
				glogger.info("--------lDblTotExpAmt----------"+lDblTotExpAmt);
				glogger.info("--------lDblTotDedAmt----------"+lDblTotDedAmt);
				glogger.info("--------lStrMonth----------"+lStrMonth);
				glogger.info("--------lStrApproved----------"+lStrApproved);
				
				lArrInnerLst=new ArrayList();
				NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNumTotExpAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotExpAmt));
				Number lNumTotDedAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotDedAmt));
				
				glogger.info("------------AMT IN QUERY DAO------------"+lNumTotExpAmt);
				glogger.info("------------AMT IN QUERY DAO------------"+lNumTotDedAmt);
				
				
				lArrInnerLst.add(lISrNo);
				lArrInnerLst.add(lStrLocShrtNm);
				lArrInnerLst.add(lIAdviceNo);
				lArrInnerLst.add(lStrEntryDate);
				lArrInnerLst.add(lStrDistrictName);
				lArrInnerLst.add(lStrDeptName);
				lArrInnerLst.add(lStrDivisionName);
				lArrInnerLst.add(lStrBankName);
				lArrInnerLst.add(lNumTotExpAmt);
				lArrInnerLst.add(lNumTotDedAmt);
				lArrInnerLst.add(lStrMonth);
				
				if(lStrApproved.equals("1"))
				{	
				   lArrInnerLst.add("No");
				   lArrInnerLst.add("Approve");
				}
				else
				{
				   lArrInnerLst.add("Yes");
				   lArrInnerLst.add("");
				}
				
				lArrInnerLst.add(lILcExpId);
				lArrInnerLst.add(lStrDivCode);
				lArrInnerLst.add(lIAdvNo);
				lArrInnerLst.add(lStrMonthCode);
				lArrInnerLst.add(lStrBankCode);
				lArrInnerLst.add(lStrDeptCode);
				lArrInnerLst.add(lStrApprovedCode);
				lArrInnerLst.add(lStrFrmDt);
				lArrInnerLst.add(lStrToDt);				
				   
				
				lArrOuterLst.add(lArrInnerLst);
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				lStmt.close();
				lCon.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return lArrOuterLst;
		
	}
	
	public boolean approveAdviceReceived(long lILcExpId) 
    {
		glogger.info("-------Inside approveAdviceReceived of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lILcExpId);	
				
		StringBuffer lStrBuff = new StringBuffer();	
		
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------approveAdviceReceived before Query--------");        	
            
        	lStrBuff.append("UPDATE TRN_LC_DTL_POSTING DTL SET DTL.ADVICE_APPROVED='0',DTL.ADVICE_APPROVED_DT=SYSDATE ");        	
        	lStrBuff.append(" WHERE DTL.LC_EXP_ID="+lILcExpId);
            		
        	glogger.info("------QUERY FOR UPDATE updateDtlPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------approveAdviceReceived after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	public boolean updateApprovedLcAmount(long lILcExpId,String lStrSign) 
    {
		glogger.info("-------Inside updateApprovedLcAmount of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrLcExpId is "+lILcExpId);	
		glogger.info("the value of the lStrSign is "+lStrSign);
		
		StringBuffer lStrBuff = new StringBuffer();	
		
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------updateApprovedLcAmount before Query--------");        	
            
        	lStrBuff.append("update trn_lc_distribution dst set dst.lc_available_amt=( select ");
        	lStrBuff.append("(select dst.lc_available_amt from trn_lc_distribution dst where ");
        	lStrBuff.append("dst.line_cntr=(select max(dst.line_cntr) from trn_lc_distribution dst where dst.division_code=( ");
        	lStrBuff.append(" select dtl.division_code from trn_lc_dtl_posting dtl where dtl.lc_exp_id="+lILcExpId+" and dtl.active='0' ))) ");
        	lStrBuff.append(lStrSign+" ");
        	lStrBuff.append("(select sum(bud.exp_amt) from trn_lc_budget_posting bud where bud.lc_exp_id="+lILcExpId+" and bud.active='0') ");
        	lStrBuff.append("from dual ) ");
        	
        	lStrBuff.append("where dst.line_cntr=(select max(dst.line_cntr) from trn_lc_distribution dst where dst.division_code=( ");
        	lStrBuff.append("select dtl.division_code from trn_lc_dtl_posting dtl where dtl.lc_exp_id="+lILcExpId+"))");
            		
        	glogger.info("------QUERY FOR UPDATE updateDtlPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------approveAdviceReceived after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	public boolean updateApprovedLcAmount(String lStrLcDivCode,double lDblExpTotal,String lStrSign) //...........OVERLOADED...........
    {
		glogger.info("-------Inside OVERLODED updateApprovedLcAmount  of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lILcDivisionId is "+lStrLcDivCode);	
				
		StringBuffer lStrBuff = new StringBuffer();	
		
        int lINoOfRowUpd=0;
        boolean updateResult=false;
        
        Session hibSession = getSession();
        
        try
        {
        	glogger.info("------------updateApprovedLcAmount before Query--------");        	
            
        	lStrBuff.append("update trn_lc_distribution dst set dst.lc_available_amt=( select ");
        	lStrBuff.append("(select dst.lc_available_amt from trn_lc_distribution dst where ");
        	lStrBuff.append("dst.line_cntr=(select max(dst.line_cntr) from trn_lc_distribution dst where dst.division_code='"+lStrLcDivCode+"')) ");
        	
        	lStrBuff.append(lStrSign+" "+lDblExpTotal);
        	
        	lStrBuff.append(" from dual ) ");
        	
        	lStrBuff.append("where dst.line_cntr=(select max(dst.line_cntr) from trn_lc_distribution dst where dst.division_code='"+lStrLcDivCode+"')");
        	            		
        	glogger.info("------QUERY FOR UPDATE updateDtlPosting-----"+lStrBuff.toString());
        	Query lSQLQuery=hibSession.createSQLQuery(lStrBuff.toString());           
            lINoOfRowUpd=lSQLQuery.executeUpdate();            
           
            glogger.info("------------approveAdviceReceived after Query--------"+lINoOfRowUpd);
            
            if(lINoOfRowUpd > 0)
            	updateResult=true;
        }
       
        catch( Exception e )
        {
            e.printStackTrace();
        	glogger.error( "Exception::"+e.getMessage(), e );
        }
        
        return updateResult; 
    
    }
	
	public double getLcExpAmount(long lIBudId,long lILcExpId)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        glogger.info("------COMING LC BUD ID-------"+lIBudId);
        glogger.info("------COMING LC EXP ID-------"+lILcExpId);
		double lDblExpAmt=0.0;
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lStrBuff = new StringBuffer();
			
			lStrBuff.append("select bud.exp_amt expAmt from trn_lc_budget_posting bud ");
			lStrBuff.append("where bud.lc_bud_id="+lIBudId+" and bud.lc_exp_id="+lILcExpId+" and active='0'");
			
		    lStmt = lCon.prepareStatement( lStrBuff.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lDblExpAmt = Double.parseDouble(lRs.getString("expAmt"));
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
		
		return lDblExpAmt;
	}
	
	public String getLcAdviceApprovedStatus(long lILcExpId)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        glogger.info("------COMING LC EXP ID-------"+lILcExpId);
		String lStrApprovedStatus="";
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lStrBuff = new StringBuffer();
			
			lStrBuff.append("select d.advice_approved app from trn_lc_dtl_posting d where d.lc_exp_id="+lILcExpId+" and d.active='0'");
			
			
		    lStmt = lCon.prepareStatement( lStrBuff.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lStrApprovedStatus =(lRs.getString("app"));
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
		
		return lStrApprovedStatus;
	}
	
	public ArrayList getLcBudIdByLcExpId(long lILcExpId)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        glogger.info("------COMING LC EXP ID-------"+lILcExpId);
		String lStrLcBudId="";
		ArrayList lArrBudIdLst=new ArrayList();
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lStrBuff = new StringBuffer();
			
			lStrBuff.append("select lc_bud_id from trn_lc_budget_posting where lc_exp_id="+lILcExpId+" and active='0'");
			
			
		    lStmt = lCon.prepareStatement( lStrBuff.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lStrLcBudId =(lRs.getString("lc_bud_id"));
	        	lArrBudIdLst.add(lStrLcBudId);
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
		
		return lArrBudIdLst;
	}
	
	public ArrayList getLcDedIdByLcExpId(long lILcExpId)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        glogger.info("------COMING LC EXP ID-------"+lILcExpId);
		String lStrLcBudId="";
		ArrayList lArrDedIdLst=new ArrayList();
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lStrBuff = new StringBuffer();
			
			lStrBuff.append("select lc_ded_id from trn_lc_deduction_posting where lc_exp_id="+lILcExpId+" and active='0'");
			
			
		    lStmt = lCon.prepareStatement( lStrBuff.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lStrLcBudId =(lRs.getString("lc_ded_id"));
	        	lArrDedIdLst.add(lStrLcBudId);
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
		
		return lArrDedIdLst;
	}
	
	public String getTsryDeptIdByLocationCode(String lStrLocCode)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        glogger.info("------COMING LC lLLocCode-------"+lStrLocCode);
		String lStrTsryId="";
		ArrayList lArrDedIdLst=new ArrayList();
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lStrBuff = new StringBuffer();
			
			lStrBuff.append("select loc.department_id  from cmn_location_mst loc where loc.location_code='"+lStrLocCode+"'");
			
			
		    lStmt = lCon.prepareStatement( lStrBuff.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lStrTsryId =(lRs.getString("department_id"));	        	
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
		
		return lStrTsryId;
	}
	//------ END----LC TREASURY REPORT FOR VARIFICATION----
	
	
	//================GET DIVISION WISE PAID CHEQUE REPORT 150009=======================================
	public ArrayList getDivisionWisePaidChequesReport(String lStrDivisionCode[],String lStrFrmDt,String lStrToDt,long lLngLangId,long lIFinYrId,String lStrLoginLocCode) 
	{
		glogger.info("-------Inside getDivisionWisePaidChequesReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcPaidChqLst = null;	
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
		String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
		String lStrDivDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        
        String lStrDivCode="";
        String lStrDivShrtNm="";
        String lStrDivName="";
        long lLngTotPaidChq=0;
        double lDblTotAmt=0;
        
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select dtl.division_code, ");
        	lStrBuff.append("(select loc.loc_short_name from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code=dtl.division_code) divShrtNm, ");
        	lStrBuff.append("(select loc.loc_name from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code=dtl.division_code) divName, ");
        	lStrBuff.append(" count(*) totClrChq,sum(chq.cheque_amt) totAmt ");
        	
        	lStrBuff.append("from trn_lc_cheque_posting chq,trn_lc_dtl_posting dtl ");
        	
        	lStrBuff.append("where chq.active='0' and chq.chq_cancel_dt is null and chq.chq_clr_dt is not null ");
        	lStrBuff.append("and dtl.lc_exp_id=chq.lc_exp_id  ");
        	
        	if(!lStrDivisionCode.equals("") && !lStrDivisionCode[0].equals("-1"))
        	{
        		glogger.info("---------INSIDE IF QUERY--------------");
        		lStrBuff.append("and chq.lc_exp_id in ");
        		//lStrBuff.append("(select dtl.lc_exp_id from trn_lc_dtl_posting dtl where dtl.active='0' and dtl.division_code='"+lStrDivisionCode+"')  ");
        		lStrBuff.append("(select dtl.lc_exp_id from trn_lc_dtl_posting dtl where dtl.active='0' ");
            }
        	else
        	{
        		glogger.info("---------INSIDE ELSE QUERY--------------");
        		lStrBuff.append("and chq.lc_exp_id in ");
        		lStrBuff.append("(select dtl.lc_exp_id from trn_lc_dtl_posting dtl where dtl.active='0' and dtl.division_code in( ");
        		lStrBuff.append("select loc.location_code from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.department_id in ('"+lStrTsryDeptId+"','"+lStrDivDeptId+"') ");
        		lStrBuff.append("and loc.loc_district_id=( ");
        		lStrBuff.append("select loc.loc_district_id from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoginLocCode+"'))) ");
        		
        	}
        	
        	if(lStrDivisionCode.length > 0)
			{
				if(lStrDivisionCode.length == 1 && !lStrDivisionCode[0].equalsIgnoreCase("-1"))
				{
					lStrBuff.append(" and dtl.division_code in ('" +lStrDivisionCode[0]+ "'))");
				}
				else if(lStrDivisionCode.length >= 2)
				{
					lStrBuff.append(" and dtl.division_code in (");
					for (int i=0; i < lStrDivisionCode.length; i++)
						if(!lStrDivisionCode[i].equalsIgnoreCase("-1"))
						{
							if(i!=(lStrDivisionCode.length - 1))
							{
								lStrBuff.append("'"+lStrDivisionCode[i]+"',");
							}
							else
							{
								lStrBuff.append("'"+lStrDivisionCode[i]+"'");
							}
						}
					lStrBuff.append("))");
				}
			}
        	
        	if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and chq.chq_clr_dt <=to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	/*if(!lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and chq.chq_clr_dt <= sysdate ");        		
        	}
        	if(lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') ");
        		lStrBuff.append("and chq.chq_clr_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	if(lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt = sysdate ");        		      		
        	}*/
        	
        	lStrBuff.append("group by dtl.division_code ");
        	lStrBuff.append("order by totClrChq ");
        	
        	
        	glogger.info("------QUERY FOR GET getDivisionWisePaidChequesReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getDivisionWisePaidChequesReport after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcPaidChqLst=new ArrayList();
            	
            	if(lRs.getObject("division_code") != null)            	
            		lStrDivCode = lRs.getString("division_code");    
            	if(lRs.getObject("divShrtNm") != null)            	
            		lStrDivShrtNm = lRs.getString("divShrtNm");   
            	if(lRs.getObject("divName") != null)
            		lStrDivName = lRs.getString("divName");            	            	
            	if(lRs.getObject("totClrChq") != null)
            		lLngTotPaidChq = lRs.getLong("totClrChq");
            	if(lRs.getObject("totAmt") != null)
            		lDblTotAmt = lRs.getDouble("totAmt");
            	
            	glogger.info("---"+lStrDivCode+"---"+lStrDivName+"----"+lLngTotPaidChq+"----"+lDblTotAmt+"----"+lStrFrmDt+"-----"+lStrToDt);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNmlDblTotAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotAmt));
            	
            	
            	lArrLcPaidChqLst.add(lStrDivShrtNm);
            	lArrLcPaidChqLst.add(lStrDivName);
            	lArrLcPaidChqLst.add(lLngTotPaidChq);
            	lArrLcPaidChqLst.add(lNmlDblTotAmt);
            	
            	lArrLcPaidChqLst.add(lStrFrmDt);
            	lArrLcPaidChqLst.add(lStrToDt);
            	lArrLcPaidChqLst.add(lStrDivCode);
            	
            	lArrOuterLst.add(lArrLcPaidChqLst);
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
        
        return lArrOuterLst; 
	}
	
	
	public ArrayList getDivisionWisePaidChequesReportDivisionDtls(String lStrDivCode,String lStrFrmDt,String lStrToDt,long lLngLangId,long lIFinYrId) 
	{
		glogger.info("-------Inside getDivisionWisePaidChequesReportDivisionDtls of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcPaidChqLst = null;	
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrClrDt="";
        long lLngTotPaidChq=0;
        double lDblTotAmt=0;
        
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select to_char(chq.chq_clr_dt,'dd/MM/yyyy') chq_clr_dt,count(*) totClrChq,sum(chq.cheque_amt) totAmt ");
        	lStrBuff.append("from trn_lc_cheque_posting chq ");
        	
        	lStrBuff.append("where chq.active='0' and chq.chq_cancel_dt is null and chq.chq_clr_dt is not null ");
        	lStrBuff.append("and chq.lc_exp_id in ");
        	lStrBuff.append("(select dtl.lc_exp_id from trn_lc_dtl_posting dtl where dtl.active='0' and dtl.division_code='"+lStrDivCode+"') ");
        	
        	if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and chq.chq_clr_dt <=to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	/*if(!lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and chq.chq_clr_dt <= sysdate ");        		
        	}
        	if(lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') ");
        		lStrBuff.append("and chq.chq_clr_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	if(lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt = sysdate ");        		      		
        	}*/
        	
        	lStrBuff.append("group by chq.chq_clr_dt ");
        	lStrBuff.append("order by chq.chq_clr_dt");
        	
        	
        	glogger.info("------QUERY FOR GET getDivisionWisePaidChequesReportDivisionDtls-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getDivisionWisePaidChequesReportDivisionDtls after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcPaidChqLst=new ArrayList();
            	
            	          	
            	if(lRs.getObject("chq_clr_dt") != null)
            		lStrClrDt = lRs.getString("chq_clr_dt");            	            	
            	if(lRs.getObject("totClrChq") != null)
            		lLngTotPaidChq = lRs.getLong("totClrChq");
            	if(lRs.getObject("totAmt") != null)
            		lDblTotAmt = lRs.getDouble("totAmt");
            	
            	glogger.info("---"+lStrClrDt+"----"+lLngTotPaidChq+"----"+lDblTotAmt);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNmlDblTotAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotAmt));
            	
            	lArrLcPaidChqLst.add(lStrClrDt);
            	lArrLcPaidChqLst.add(lLngTotPaidChq);
            	lArrLcPaidChqLst.add(lNmlDblTotAmt);
            	
            	lArrOuterLst.add(lArrLcPaidChqLst);
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
        
        return lArrOuterLst; 
	}
	
	//===================================================================================
	
   //================GET DIVISION WISE LC PAYMENT LEDGER REPORT 150011=======================================
	public ArrayList getDivisionWiseLCPaymentLedgerReport(String lStrDivisionCode,String lStrFrmDt,String lStrToDt,String lStrAccDt,long lLngLangId,long lIFinYrId) 
	{
		glogger.info("-------Inside getDivisionWiseLCPaymentLedgerReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcPaidChqLst = null;	
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrClrDt="";
        String lStrChqDt="";        
        String lStrChqNo="";
        double lDblTotAmt=0;
        String lStrDivName="";
        
        Date lDtFinStrt=null;
        Date lDtChq=null;
        String lStrClrDtRpt="";
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select to_char(chq.chq_clr_dt,'dd/MM/yyyy') chq_clr_dt, ");
        	lStrBuff.append("chq.cheque_no,chq.cheque_dt cheque_dt,chq.cheque_amt, ");
        	lStrBuff.append("(select loc.loc_name from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrDivisionCode+"') divName, ");
        	lStrBuff.append("(select f.from_date finStartDt from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') finStartDt ");
        	
        	lStrBuff.append("from trn_lc_cheque_posting chq ");
        	
        	lStrBuff.append("where chq.active='0' and chq.chq_cancel_dt is null and chq.chq_clr_dt is not null ");
        	lStrBuff.append("and chq.lc_exp_id in ");
        	lStrBuff.append("(select dtl.lc_exp_id from trn_lc_dtl_posting dtl where dtl.active='0' and dtl.division_code='"+lStrDivisionCode+"') ");
        	
        	if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and chq.chq_clr_dt <=to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	/*if(!lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and chq.chq_clr_dt <= sysdate ");        		
        	}
        	if(lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') ");
        		lStrBuff.append("and chq.chq_clr_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	} 
        	if(lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and chq.chq_clr_dt = sysdate ");        		      		
        	}*/
        	
        	if(!lStrAccDt.equals(""))
        	{
        		//lStrBuff.append("and chq.chq_clr_dt >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') ");
        		lStrBuff.append("and chq.chq_clr_dt = to_date('"+lStrAccDt+"','dd/MM/yyyy') ");        		
        	}  
        	
        	lStrBuff.append("order by chq.chq_clr_dt");
        	
        	
        	glogger.info("------QUERY FOR GET getDivisionWiseLCPaymentLedgerReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getDivisionWiseLCPaymentLedgerReport after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcPaidChqLst=new ArrayList();
            	
            	          	
            	if(lRs.getObject("chq_clr_dt") != null)
            		lStrClrDt = lRs.getString("chq_clr_dt");            	            	
            	if(lRs.getObject("cheque_no") != null)
            		lStrChqNo = lRs.getString("cheque_no");
            	if(lRs.getObject("cheque_dt") != null)
            		lDtChq = lRs.getDate("cheque_dt");   
            	if(lRs.getObject("cheque_amt") != null)
            		lDblTotAmt = lRs.getDouble("cheque_amt");
            	
            	if(lRs.getObject("divName") != null)
            		lStrDivName = lRs.getString("divName");  
            	if(lRs.getObject("finStartDt") != null)
            		 lDtFinStrt = lRs.getDate("finStartDt");
            	
            	glogger.info("---"+lStrClrDt+"----"+lStrChqDt+"-------"+lStrChqNo+"----"+lDblTotAmt);
            	glogger.info("--------FIN YEAR STRT DATE----------"+lDtFinStrt);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNmlDblTotAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotAmt));
            	
            	if(lStrClrDt.equalsIgnoreCase(lStrClrDtRpt))
            	{
            		lArrLcPaidChqLst.add("");
            	}
            	else
            	{
            		lArrLcPaidChqLst.add(lStrClrDt);
            	}            	
            	lStrClrDtRpt=lStrClrDt;
            	
            	lArrLcPaidChqLst.add(lDtChq);
            	lArrLcPaidChqLst.add(lStrChqNo);
            	
            	if(lDtChq.before(lDtFinStrt))
            	{
            	    lArrLcPaidChqLst.add(lNmlDblTotAmt);
            	    lArrLcPaidChqLst.add("");
            	
            	}else if(lDtChq.after(lDtFinStrt))
            	{
            		lArrLcPaidChqLst.add("");
            		lArrLcPaidChqLst.add(lNmlDblTotAmt);
            	}
            	lArrLcPaidChqLst.add(lNmlDblTotAmt); 
            	lArrLcPaidChqLst.add(lStrDivName); 
            	
            	lArrOuterLst.add(lArrLcPaidChqLst);
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
        
        return lArrOuterLst; 
	}
	
	public ArrayList getDivShrtNmAndDivNm(String lStrDivisionCode, long lLngLangId) 
    {
		glogger.info("-------Inside getDivShrtNmAndDivNm of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lStrDivisionCode is "+lStrDivisionCode);
		glogger.info("the value of the lLngLangId is  "+lLngLangId);
				
		ArrayList lArrDivisionLst = new ArrayList();
		ComboValuesVO comboVo=null;
		
		//String lStrDeptLkpId='100008';//---->Constant file
		String lStrDeptLkpId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		
		
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDivCode="";
        String lStrDivNm="";
        try
        {        	
        	lCon = DBConnection.getConnection();
            StringBuffer lStrBuff = new StringBuffer();
            
            lStrBuff.append("select loc.loc_short_name,loc.loc_name from cmn_location_mst loc where loc.lang_id="+lLngLangId );
            lStrBuff.append(" and loc.department_id="+lStrDeptLkpId+" and loc.location_code='"+lStrDivisionCode+"'");
            
            glogger.info("------------QUERY FOR GET getAllDivisions -------"+lStrBuff.toString());		
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            
            while(lRs.next())
            {
            	comboVo= new ComboValuesVO();
            	if(lRs.getObject("loc_short_name") != null)
            		lStrDivCode = lRs.getString("loc_short_name");
            	if(lRs.getObject("loc_name") != null)
            		lStrDivNm = lRs.getString("loc_name");
                
            	comboVo.setId(lStrDivCode);
            	comboVo.setDesc(lStrDivNm);
                
            	lArrDivisionLst.add(comboVo);
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
        
        return lArrDivisionLst; 
    
    }
	//================================================================================================
	
    //================LC PAYMENT APPROVAL REPORT - 150012 =======================================
	public ArrayList getLCPaymentApprovalReport(String lStrDivisionCode,String lStrFrmDt,String lStrToDt,String lStrAdviceNo,long lLngLangId,long lIFinYrId,String LstrLoginLocCode) 
	{
		glogger.info("-------Inside getLCPaymentApprovalReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcAdviceLst1 = null;	
		ArrayList lArrLcAdviceLst2 = null;	
		ArrayList lArrLcAdviceLst3 = null;	
		ArrayList lArrLcAdviceLst4 = null;	
		
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
		String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
		String lStrDivDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrAdviceDt="";
        String lStrAdviceNum="";        
        String lStrChqNo="";
        String lStrDivCode="";
        double lDblTotAmt=0;
        double lDblOpenLc=0;
        double lDblCloseLc=0;
        
        String lStrDivName="";
        
        Date lDtFinStrt=null;
        Date lDtChq=null;
        String lStrClrDtRpt="";
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select to_char(dtl.advice_dt,'dd/MM/yyyy') advice_dt ,dtl.advice_no, dtl.division_code ,dtl.opening_lc,dtl.closing_lc, ");
        	lStrBuff.append(  "(select count(*) from trn_lc_cheque_posting chq where chq.lc_exp_id=dtl.lc_exp_id and chq.active='0' and chq.chq_cancel_dt is null ) totChq, ");
        	lStrBuff.append(  "(select sum(bud.exp_amt) from trn_lc_budget_posting bud where bud.lc_exp_id=dtl.lc_exp_id and bud.active='0' ) totExpAmt ");
        	
        	lStrBuff.append("from trn_lc_dtl_posting dtl ");
        	
        	lStrBuff.append("where dtl.active='0' and dtl.advice_approved='0'  ");
        	
        	if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and dtl.advice_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	/*if(!lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and dtl.advice_dt <= sysdate ");        		
        	}
        	if(lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') ");
        		lStrBuff.append("and dtl.advice_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	} 
        	if(lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt = sysdate ");        		      		
        	}*/
        	
        	if(!lStrAdviceNo.equals("") )
        	{
        		lStrBuff.append("and dtl.advice_no ='"+lStrAdviceNo+"' ");        		      		
        	}
        	
        	if(!lStrDivisionCode.equals("") )
        	{
        		glogger.info("---------INSIDE IF QUERY--------------"); 
        		lStrBuff.append("and dtl.division_code ='"+lStrDivisionCode+"' ");        		      		
        	}
        	else
        	{
        		glogger.info("---------INSIDE ELSE QUERY--------------");        		
        		lStrBuff.append("and dtl.division_code in( ");
        		lStrBuff.append("select loc.location_code from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.department_id in ('"+lStrTsryDeptId+"','"+lStrDivDeptId+"') ");
        		lStrBuff.append("and loc.loc_district_id=( ");
        		lStrBuff.append("select loc.loc_district_id from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+LstrLoginLocCode+"')) ");
        	}
        	
        	lStrBuff.append("order by dtl.advice_dt,dtl.trn_cntr ");
        	
        	glogger.info("------QUERY FOR GET getLCPaymentApprovalReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCPaymentApprovalReport after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcAdviceLst1=new ArrayList();
            	lArrLcAdviceLst2=new ArrayList();
            	lArrLcAdviceLst3=new ArrayList();
            	lArrLcAdviceLst4=new ArrayList();
            	
            	if(lRs.getObject("advice_dt") != null)
            		lStrAdviceDt = lRs.getString("advice_dt");            	            	
            	if(lRs.getObject("advice_no") != null)
            		lStrAdviceNum = lRs.getString("advice_no");
            	if(lRs.getObject("division_code") != null)
            		lStrDivCode = lRs.getString("division_code");
            	if(lRs.getObject("totChq") != null)
            		lStrChqNo = lRs.getString("totChq");   
            	
            	if(lRs.getObject("opening_lc") != null)
            		lDblOpenLc = lRs.getDouble("opening_lc");
            	if(lRs.getObject("closing_lc") != null)
            		lDblCloseLc = lRs.getDouble("closing_lc");
            	if(lRs.getObject("totExpAmt") != null)
            		lDblTotAmt = lRs.getDouble("totExpAmt");            	
            	
            	glogger.info("---"+lStrAdviceDt+"----"+lStrAdviceNum+"-------"+lStrChqNo+"----"+lDblTotAmt);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNmOpenLc= testNumberFormat.parse(testNumberFormat.format(lDblOpenLc));
				Number lNmCloseLc= testNumberFormat.parse(testNumberFormat.format(lDblCloseLc));
				Number lNmTotAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotAmt));
				Number lNmBalLc= testNumberFormat.parse(testNumberFormat.format(lDblOpenLc-lDblTotAmt));
				
				//---------------
				ArrayList lArrDivCodeAndNm=null;
				lArrDivCodeAndNm=getDivShrtNmAndDivNm(lStrDivCode, lLngLangId);
				ComboValuesVO comboVo=null;
				comboVo=(ComboValuesVO)lArrDivCodeAndNm.get(0);
				
				String lStrDivShrtName="";
				String lStrDivName1="";
				
				lStrDivShrtName=comboVo.getId();
				lStrDivName1=comboVo.getDesc();
				glogger.info("------DIVISION CODE 4 SHOW-------------"+lStrDivShrtName);
				glogger.info("------DIVISION NAME 4 SHOW-------------"+lStrDivName1);
				
				lStrDivShrtName="Division Code :     "+lStrDivShrtName+"<br>"+
								"Division Name :     "+lStrDivName1;
				glogger.info("-------DIV NAME------"+lStrDivShrtName);
				//--------------
				
            	lArrLcAdviceLst1.add("");
            	lArrLcAdviceLst1.add("");
            	lArrLcAdviceLst1.add("");
            	lArrLcAdviceLst1.add("Opening Balance....");
            	lArrLcAdviceLst1.add(lNmOpenLc);
            	lArrLcAdviceLst1.add(lStrDivShrtName);
            	
            	
            	lArrLcAdviceLst2.add(lStrAdviceDt);
            	lArrLcAdviceLst2.add(lStrAdviceNum);
            	lArrLcAdviceLst2.add(lStrChqNo);
            	lArrLcAdviceLst2.add(lNmTotAmt);
            	lArrLcAdviceLst2.add(lNmBalLc);
            	lArrLcAdviceLst2.add(lStrDivShrtName);
            	
            	
            	lArrLcAdviceLst3.add("");
            	lArrLcAdviceLst3.add("");
            	lArrLcAdviceLst3.add("");
            	lArrLcAdviceLst3.add("Closing Balance....");
            	lArrLcAdviceLst3.add(lNmCloseLc);
            	lArrLcAdviceLst3.add(lStrDivShrtName);
            	
            	
            	/*lArrLcAdviceLst4.add("");
            	lArrLcAdviceLst4.add("");
            	lArrLcAdviceLst4.add("");
            	lArrLcAdviceLst4.add("");
            	lArrLcAdviceLst4.add("");
            	lArrLcAdviceLst4.add(lStrDivShrtName);*/
            	
            	lArrOuterLst.add(lArrLcAdviceLst1);
            	lArrOuterLst.add(lArrLcAdviceLst2);
            	lArrOuterLst.add(lArrLcAdviceLst3);
            	//lArrOuterLst.add(lArrLcAdviceLst4);
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
        
        return lArrOuterLst; 
	}
	
	//=============================================================================================
	
	//==============LC CHEQUES STATUS REPORT - 150013 =============================================
	public ArrayList getAllDivisions(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getAllDivisions of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
				
		ArrayList lArrDivisionLst = new ArrayList();
		ComboValuesVO comboVo=null;
		
		//String lStrDeptLkpId='150004';//---->Constant file
		String lStrDeptLkpId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		
		long lLngLangId= (long)getLongLangId(lStrLangId);
		glogger.info("-----LANG ID---------"+lLngLangId);
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;
        
        String lStrDivCode=null;
        String lStrDivNm=null;
        try
        {        	
        	lCon = DBConnection.getConnection();
            StringBuffer lStrBuff = new StringBuffer();
            
            lStrBuff.append("select loc.location_code ,loc.loc_name  from cmn_location_mst loc where loc.department_id='"+lStrDeptLkpId+"' and loc.lang_id="+lLngLangId);
            
            glogger.info("------------QUERY FOR GET getAllDivisions -------"+lStrBuff.toString());		
            lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            
            while(lRs.next())
            {
            	comboVo= new ComboValuesVO();
            	lStrDivCode = lRs.getString("location_code");
            	lStrDivNm = lRs.getString("loc_name");
                
            	comboVo.setId(lStrDivCode);
            	comboVo.setDesc(lStrDivNm);
                
            	lArrDivisionLst.add(comboVo);
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
        
        return lArrDivisionLst; 
    
    }
	
	public ArrayList getPaidUnpaidChq(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getDivisionsOfLoginTsry of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
			
		ArrayList lArrPaidUnpaidChqLst = new ArrayList();
		
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.PAID_CHQ_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.PAID_CHQ"));
		lArrPaidUnpaidChqLst.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.UNPAID_CHQ_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.UNPAID_CHQ"));		
		lArrPaidUnpaidChqLst.add(comboVo);
        
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.CANCELL_CHQ_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.CANCELL_CHQ"));		
		lArrPaidUnpaidChqLst.add(comboVo);
        
        return lArrPaidUnpaidChqLst;     
    }
	
	public ArrayList getLCChequeStatusReport(String lStrDivisionCode,String lStrDeptCode,String lStrPaidUnpaid,String lStrFrmDt,String lStrToDt,String lStrChqNo,long lLngLangId,long lIFinYrId,String LstrLoginLocCode) 
	{
		glogger.info("-------Inside getLCChequeStatusReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcChqStatusLst = null;
		
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrChequeNo="";
        double lDblChqAmt=0;
        String lStrChqDt="";
        String lStrChqCancelDt="";
        String lStrDivNm="";
        String lStrDeptNm="";
        String lStrTsryNm="";
        String lStrChqClrDt="";
        
        long lLngSrNo=0;
       
       
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select chq.cheque_no,chq.cheque_amt,to_char(chq.cheque_dt,'dd/MM/yyyy') cheque_dt,chq.chq_cancel_dt,loc.loc_name,lkp.lookup_name, ");
        	lStrBuff.append(       "(select dst.district_name from cmn_district_mst dst where dst.lang_id=1 and dst.district_id=loc.loc_district_id) tsry, ");
        	lStrBuff.append(       "chq.chq_clr_dt,dtl.advice_dt,lkp.lookup_id,loc.location_code,loc.loc_district_id  ");
        	
        	lStrBuff.append("from trn_lc_cheque_posting chq,mst_lc_division_account acc,trn_lc_dtl_posting dtl,");
        	lStrBuff.append(      "cmn_location_mst loc,cmn_lookup_mst lkp  ");
        	
        	lStrBuff.append("where chq.active='0' ");
        	lStrBuff.append(       "and chq.lc_acc_no=acc.lc_acc_no ");
        	lStrBuff.append(       "and acc.division_code=loc.location_code ");
        	lStrBuff.append(       "and loc.type_lookup_id=lkp.lookup_id ");
        	lStrBuff.append(       "and dtl.lc_exp_id=chq.lc_exp_id ");
        	lStrBuff.append(       "and loc.lang_id=lkp.lang_id  ");
        	
        	
        	if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and dtl.advice_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	/*if(!lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and dtl.advice_dt <= sysdate ");        		
        	}
        	if(lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= (select f.from_date from sgvc_fin_year_mst f where f.fin_year_id='"+lIFinYrId+"') ");
        		lStrBuff.append("and dtl.advice_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	} 
        	if(lStrFrmDt.equals("") && lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt = sysdate ");        		      		
        	}*/
        	
        	if(!lStrDivisionCode.equals(""))
        	{
        		lStrBuff.append("and   loc.location_code='"+lStrDivisionCode+"' ");
        	}
        	if(!lStrDeptCode.equals(""))
        	{
        		lStrBuff.append("and   lkp.lookup_id='"+lStrDeptCode+"' ");
        	}
        	if(!lStrChqNo.equals(""))
        	{
        		lStrBuff.append("and   chq.cheque_no='"+lStrChqNo+"' ");
        	}
        	if(lStrPaidUnpaid.equals("0"))
        	{
        		lStrBuff.append("and   chq.chq_clr_dt is not null and chq.chq_cancel_dt is null ");
        	}
        	if(lStrPaidUnpaid.equals("1"))
        	{
        		lStrBuff.append("and   chq.chq_clr_dt is null and chq.chq_cancel_dt is null ");
        	}
        	if(lStrPaidUnpaid.equals("2"))
        	{
        		lStrBuff.append("and   chq.chq_cancel_dt is not null ");
        	}
        	
        	lStrBuff.append(" order by dtl.advice_dt ");
        	
        	glogger.info("------QUERY FOR GET getLCChequeStatusReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCChequeStatusReport after Query--------");
          
            while(lRs.next())
            {            	
            	lArrLcChqStatusLst=new ArrayList();
            	
            	lLngSrNo++;
            	
            	if(lRs.getObject("cheque_no") != null)
            		lStrChequeNo = lRs.getString("cheque_no");  
            	if(lRs.getObject("cheque_amt") != null)
            		lDblChqAmt = lRs.getDouble("cheque_amt");    
            	if(lRs.getObject("cheque_dt") != null)
            		lStrChqDt = lRs.getString("cheque_dt");
            	if(lRs.getObject("chq_cancel_dt") != null)
            		lStrChqCancelDt = lRs.getString("chq_cancel_dt");
            	if(lRs.getObject("loc_name") != null)
            		lStrDivNm = lRs.getString("loc_name");   
            	if(lRs.getObject("lookup_name") != null)
            		lStrDeptNm = lRs.getString("lookup_name");            	            	
            	if(lRs.getObject("tsry") != null)
            		lStrTsryNm = lRs.getString("tsry");
            	if(lRs.getObject("chq_clr_dt") != null)
            		lStrChqClrDt = lRs.getString("chq_clr_dt"); 
            	
            	
            	glogger.info("---"+lStrChequeNo+"----"+lDblChqAmt+"-------"+lStrChqDt+"-------"+lStrTsryNm+"----"+lStrDeptNm+"---"+lStrDivNm+"---"+lStrChqClrDt);
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNmChqAmt= testNumberFormat.parse(testNumberFormat.format(lDblChqAmt));
            	
            	
            	lArrLcChqStatusLst.add(lLngSrNo);
            	lArrLcChqStatusLst.add(lStrChequeNo);
            	lArrLcChqStatusLst.add(lNmChqAmt);
            	lArrLcChqStatusLst.add(lStrChqDt);
            	lArrLcChqStatusLst.add(lStrTsryNm);
            	lArrLcChqStatusLst.add(lStrDeptNm);
            	lArrLcChqStatusLst.add(lStrDivNm);
            	if(lStrChqCancelDt.equals(""))
            	{
	            	if(lStrChqClrDt.equals(""))
	            	    lArrLcChqStatusLst.add("Unpaid");
	            	else
	            		lArrLcChqStatusLst.add("Paid");
            	}
            	else
            		lArrLcChqStatusLst.add("Cancel");
            	lStrChqClrDt="";
            	
            	lArrOuterLst.add(lArrLcChqStatusLst); 
            	
            	lStrChqCancelDt="";
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
        
        return lArrOuterLst; 
	}
	//=============================================================================================
	
	//============ LC EXPENDITURE TRACKING (DAT)===================================================
	public ArrayList getPlanNonPlan(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getPlanNonPlan of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
			
		ArrayList lArrPlanNonPlanLst = new ArrayList();
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.PLAN_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.PLAN"));
		lArrPlanNonPlanLst.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.NON_PLAN_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.NON_PLAN"));
		
		lArrPlanNonPlanLst.add(comboVo);
       
        
        return lArrPlanNonPlanLst;     
    }
	
	public ArrayList getRevenueCapital(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getRevenueCapital of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
			
		ArrayList lArrRevenueCapitalLst = new ArrayList();
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.REVENUE_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.REVENUE"));
		lArrRevenueCapitalLst.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.CAPITAL_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.CAPITAL"));
		
		lArrRevenueCapitalLst.add(comboVo);
       
        
        return lArrRevenueCapitalLst;     
    }
	
	public ArrayList getDeptWiseTsryWise(String lStrLangId, String lStrLocId) 
    {
		glogger.info("-------Inside getDeptWiseTsryWise of LCReportQueryDAOImpl------------");
		glogger.info("the value of the lang id is "+lStrLangId);
		glogger.info("the value of the loc Id is  "+lStrLocId);
		
			
		ArrayList lArrRevenueCapitalLst = new ArrayList();
		ComboValuesVO comboVo=new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.DEPT_WISE_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.DEPT_WISE"));
		lArrRevenueCapitalLst.add(comboVo);
		
		comboVo= new ComboValuesVO();
		comboVo.setId(bundleConst.getString("LC.TSRY_WISE_VALUE"));
		comboVo.setDesc(bundleConst.getString("LC.TSRY_WISE"));
		
		lArrRevenueCapitalLst.add(comboVo);
       
        
        return lArrRevenueCapitalLst;     
    }
	
	
	public ArrayList getLCExpenditureTrackingDATReportDeptWise(String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode)
	{
		glogger.info("-------Inside getLCExpenditureTrackingDATReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcChqStatusLst = null;
		
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrDeptId="";
        String lStrDeptNm="";
        
        double lDblPlanRvn=0;
        double lDblPlanCap=0;
        double lDblPlanTot=0;
        double lDblNPlanRvn=0;
        double lDblNPlanCap=0;
        double lDblNPlanTot=0;
        double lDblTotal=0;
       
        double lDblPlanRvnTot=0;
        double lDblPlanCapTot=0;
        double lDblPlanTotTot=0;
        double lDblNPlanRvnTot=0;
        double lDblNPlanCapTot=0;
        double lDblNPlanTotTot=0;
        double lDblTotalTot=0;
        
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select lkp.lookup_id, lkp.lookup_name,  ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_c, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_c ");
        	
        	lStrBuff.append("from   trn_lc_dtl_posting dtl, ");
        	lStrBuff.append("cmn_location_mst loc ,cmn_lookup_mst lkp  ");        	
        	
        	lStrBuff.append(" where dtl.division_code=loc.location_code ");
        	lStrBuff.append("and lkp.lookup_id=loc.type_lookup_id ");
        	lStrBuff.append("and loc.lang_id=lkp.lang_id ");
        	
        	/*if(lStrPlanNonPlan.equals("0"))
        	{
        		lStrBuff.append("and   bud.budget_type >= 6 ");
        	}
        	if(lStrPlanNonPlan.equals("1"))
        	{
        		lStrBuff.append("and   bud.budget_type < 6 ");
        	}
        	if(lStrRevenueCapital.equals("0"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 2000 and bud.mjr_hd <= 3999 ");
        	}
        	if(lStrRevenueCapital.equals("1"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 4000 and bud.mjr_hd <= 5999 ");
        	}*/
        	if(!lStrDeptCode.equals(""))
        	{
        		lStrBuff.append("and  lkp.lookup_id='"+lStrDeptCode+"' ");
        	}
        	if(!lStrFrmDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        	}
        	if(!lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");
        	}
        	
        	lStrBuff.append("group by lkp.lookup_id,lkp.lookup_name  ");
        	
        	
        	glogger.info("------QUERY FOR GET getLCPaymentApprovalReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCPaymentApprovalReport after Query--------");
            
            String lNmNPlanRvn="";
            String lNmNPlanCap="";
            String lNmNPlanTot="";
			
            String lNmPlanRvn="";
            String lNmPlanCap="";
            String lNmPlanTot="";
            
            String lNmTotal="";
            
            while(lRs.next())
            {            	
            	lArrLcChqStatusLst=new ArrayList();
            	
            	if(lRs.getObject("lookup_id") != null)
            		lStrDeptId = lRs.getString("lookup_id"); 
            	if(lRs.getObject("lookup_name") != null)
            		lStrDeptNm = lRs.getString("lookup_name"); 
            	
            	if(lRs.getObject("p_r") != null)
            		lDblPlanRvn = lRs.getDouble("p_r");
            	if(lRs.getObject("p_c") != null)
            		lDblPlanCap = lRs.getDouble("p_c");   
            	if(lRs.getObject("np_r") != null)
            		lDblNPlanRvn = lRs.getDouble("np_r");            	            	
            	if(lRs.getObject("np_c") != null)
            		lDblNPlanCap = lRs.getDouble("np_c");
            	
            	lDblPlanTot=lDblPlanRvn+lDblPlanCap;
            	lDblNPlanTot=lDblNPlanRvn+lDblNPlanCap;
            	lDblTotal=lDblPlanTot+lDblNPlanTot;
            	
            	glogger.info("---"+lStrDeptNm+"----"+lDblPlanRvn+"-------"+lDblPlanCap+"--"+lDblPlanTot+"--"+lDblNPlanRvn+"---"+lDblNPlanCap+"---"+lDblNPlanTot);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
            	
				 lNmPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				 lNmPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				 lNmPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblPlanCap)).toString();
				
				 lNmNPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				 lNmNPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				 lNmNPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn+lDblNPlanCap)).toString();
				
				 lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblTotal)).toString();
            	
				glogger.info("----------------------------"+lStrPlanNonPlan);
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------PLAN----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------PLAN + REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------PLAN + CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------NON PLAN----------------");
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------ REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------ CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap+lDblNPlanCap)).toString();
				}
				
				
            	lArrLcChqStatusLst.add(lStrDeptNm);
            	
            	lArrLcChqStatusLst.add(lNmTotal);
            	if(!lNmTotal.equals("-"))
            		lDblTotalTot += Double.parseDouble(lNmTotal);
            	
            	lArrLcChqStatusLst.add(lNmPlanRvn);  
            	if(!lNmPlanRvn.equals("-"))
            		lDblPlanRvnTot += Double.parseDouble(lNmPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmPlanCap);
            	if(!lNmPlanCap.equals("-"))
            		lDblPlanCapTot += Double.parseDouble(lNmPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmPlanTot);            	
            	if(!lNmPlanTot.equals("-"))
            		lDblPlanTotTot += Double.parseDouble(lNmPlanTot);
            	
            	lArrLcChqStatusLst.add(lNmNPlanRvn); 
            	if(!lNmNPlanRvn.equals("-"))
            		lDblNPlanRvnTot += Double.parseDouble(lNmNPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmNPlanCap);  
            	if(!lNmNPlanCap.equals("-"))
            		lDblNPlanCapTot += Double.parseDouble(lNmNPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmNPlanTot); 
            	if(!lNmNPlanTot.equals("-"))
            		lDblNPlanTotTot += Double.parseDouble(lNmNPlanTot);
            	
            	lArrLcChqStatusLst.add(lStrDeptId); 
            	
            	lArrLcChqStatusLst.add(lStrPlanNonPlan);            	
            	lArrLcChqStatusLst.add(lStrRevenueCapital);             	
            	lArrLcChqStatusLst.add(lStrFrmDt); 
            	lArrLcChqStatusLst.add(lStrToDt);				
            	
            	lArrOuterLst.add(lArrLcChqStatusLst);             	
            	
                lDblPlanTot=0;                
                lDblNPlanTot=0;
                lDblTotal=0;
            }
            //--------BLANK ROW----------------
            lArrLcChqStatusLst=new ArrayList();
            
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
            //-----------------------------------
            
            lArrLcChqStatusLst=new ArrayList();
            
            NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
        	
			String lNmTotalTot= testNumberFormat.parse(testNumberFormat.format(lDblTotalTot)).toString();
			
			String lNmPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvnTot)).toString();
			String lNmPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanCapTot)).toString();
			String lNmPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanTotTot)).toString();
			
			String lNmNPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvnTot)).toString();
			String lNmNPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCapTot)).toString();
			String lNmNPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTotTot)).toString();
			
            lArrLcChqStatusLst.add(""); 
            if(!lNmTotal.equals("-"))
            	lArrLcChqStatusLst.add(lNmTotalTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanCapTot);
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanTotTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanCapTot); 
            else
            	lArrLcChqStatusLst.add("-");
            
            if(!lNmNPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanTotTot);
            else
            	lArrLcChqStatusLst.add("-");
             
            
            lArrLcChqStatusLst.add(lStrDeptId); 
        	
        	lArrLcChqStatusLst.add(lStrPlanNonPlan);            	
        	lArrLcChqStatusLst.add(lStrRevenueCapital);             	
        	lArrLcChqStatusLst.add(lStrFrmDt); 
        	lArrLcChqStatusLst.add(lStrToDt);	
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
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
        
        return lArrOuterLst; 
	}
	
	public ArrayList getLCExpenditureTrackingDATReportDeptDtls(String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode)
	{
		glogger.info("-------Inside getLCExpenditureTrackingDATReportDeptDtls of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcChqStatusLst = null;
		
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrLocCode="";
        String lStrLocNm="";
        
        double lDblPlanRvn=0;
        double lDblPlanCap=0;
        double lDblPlanTot=0;
        double lDblNPlanRvn=0;
        double lDblNPlanCap=0;
        double lDblNPlanTot=0;
        double lDblTotal=0;
       
        double lDblPlanRvnTot=0;
        double lDblPlanCapTot=0;
        double lDblPlanTotTot=0;
        double lDblNPlanRvnTot=0;
        double lDblNPlanCapTot=0;
        double lDblNPlanTotTot=0;
        double lDblTotalTot=0;
        
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select loc.location_code, loc.loc_name,  ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_c, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_c ");
        	
        	lStrBuff.append("from  trn_lc_dtl_posting dtl, ");
        	lStrBuff.append("cmn_location_mst loc ,cmn_lookup_mst lkp  ");        	
        	
        	lStrBuff.append(" where dtl.division_code=loc.location_code ");
        	lStrBuff.append("and lkp.lookup_id=loc.type_lookup_id ");
        	lStrBuff.append("and loc.lang_id=lkp.lang_id ");
        	lStrBuff.append("and loc.type_lookup_id='"+lStrDeptCode+"' ");
        	
        	/*if(lStrPlanNonPlan.equals("0"))
        	{
        		lStrBuff.append("and   bud.budget_type >= 6 ");
        	}
        	if(lStrPlanNonPlan.equals("1"))
        	{
        		lStrBuff.append("and   bud.budget_type < 6 ");
        	}
        	if(lStrRevenueCapital.equals("0"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 2000 and bud.mjr_hd <= 3999 ");
        	}
        	if(lStrRevenueCapital.equals("1"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 4000 and bud.mjr_hd <= 5999 ");
        	}    */    	
        	if(!lStrFrmDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        	}
        	if(!lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");
        	}
        	
        	lStrBuff.append("group by loc.location_code,loc.loc_name  ");
        	
        	
        	glogger.info("------QUERY FOR GET getLCExpenditureTrackingDATReportDeptDtls-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCExpenditureTrackingDATReportDeptDtls after Query--------");
            
            String lNmNPlanRvn="";
            String lNmNPlanCap="";
            String lNmNPlanTot="";
			
            String lNmPlanRvn="";
            String lNmPlanCap="";
            String lNmPlanTot="";
            
            String lNmTotal="";
            
            while(lRs.next())
            {            	
            	lArrLcChqStatusLst=new ArrayList();
            	
            	if(lRs.getObject("location_code") != null)
            		lStrLocCode = lRs.getString("location_code"); 
            	if(lRs.getObject("loc_name") != null)
            		lStrLocNm = lRs.getString("loc_name"); 
            	
            	if(lRs.getObject("p_r") != null)
            		lDblPlanRvn = lRs.getDouble("p_r");
            	if(lRs.getObject("p_c") != null)
            		lDblPlanCap = lRs.getDouble("p_c");   
            	if(lRs.getObject("np_r") != null)
            		lDblNPlanRvn = lRs.getDouble("np_r");            	            	
            	if(lRs.getObject("np_c") != null)
            		lDblNPlanCap = lRs.getDouble("np_c");
            	
            	lDblPlanTot=lDblPlanRvn+lDblPlanCap;
            	lDblNPlanTot=lDblNPlanRvn+lDblNPlanCap;
            	lDblTotal=lDblPlanTot+lDblNPlanTot;
            	
            	glogger.info("---"+lStrLocNm+"----"+lDblPlanRvn+"-------"+lDblPlanCap+"--"+lDblPlanTot+"--"+lDblNPlanRvn+"---"+lDblNPlanCap+"---"+lDblNPlanTot);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
            	
				 lNmPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				 lNmPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				 lNmPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblPlanCap)).toString();
				
				 lNmNPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				 lNmNPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				 lNmNPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn+lDblNPlanCap)).toString();
				
				 lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblTotal)).toString();
            	
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------PLAN----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------PLAN + REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------PLAN + CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------NON PLAN----------------");
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------ REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------ CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap+lDblNPlanCap)).toString();
				}
				
            	lArrLcChqStatusLst.add(lStrLocNm);
            	
            	lArrLcChqStatusLst.add(lNmTotal);
            	if(!lNmTotal.equals("-"))
            		lDblTotalTot += Double.parseDouble(lNmTotal);
            	
            	lArrLcChqStatusLst.add(lNmPlanRvn);  
            	if(!lNmPlanRvn.equals("-"))
            		lDblPlanRvnTot += Double.parseDouble(lNmPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmPlanCap);
            	if(!lNmPlanCap.equals("-"))
            		lDblPlanCapTot += Double.parseDouble(lNmPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmPlanTot);            	
            	if(!lNmPlanTot.equals("-"))
            		lDblPlanTotTot += Double.parseDouble(lNmPlanTot);
            	
            	lArrLcChqStatusLst.add(lNmNPlanRvn); 
            	if(!lNmNPlanRvn.equals("-"))
            		lDblNPlanRvnTot += Double.parseDouble(lNmNPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmNPlanCap);  
            	if(!lNmNPlanCap.equals("-"))
            		lDblNPlanCapTot += Double.parseDouble(lNmNPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmNPlanTot); 
            	if(!lNmNPlanTot.equals("-"))
            		lDblNPlanTotTot += Double.parseDouble(lNmNPlanTot);
            	
            	lArrOuterLst.add(lArrLcChqStatusLst); 
            	
            	
                lDblPlanTot=0;                
                lDblNPlanTot=0;
                lDblTotal=0;
            }
            //--------BLANK ROW----------------
            lArrLcChqStatusLst=new ArrayList();
            
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
           
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
            //-----------------------------------
            
            lArrLcChqStatusLst=new ArrayList();
            
            NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
        	
			String lNmTotalTot= testNumberFormat.parse(testNumberFormat.format(lDblTotalTot)).toString();
			
			String lNmPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvnTot)).toString();
			String lNmPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanCapTot)).toString();
			String lNmPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanTotTot)).toString();
			
			String lNmNPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvnTot)).toString();
			String lNmNPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCapTot)).toString();
			String lNmNPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTotTot)).toString();
			
            lArrLcChqStatusLst.add("Total"); 
            if(!lNmTotal.equals("-"))
            	lArrLcChqStatusLst.add(lNmTotalTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanCapTot);
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanTotTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanCapTot); 
            else
            	lArrLcChqStatusLst.add("-");
            
            if(!lNmNPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanTotTot);
            else
            	lArrLcChqStatusLst.add("-");
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
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
        
        return lArrOuterLst; 
	}
	
	public ArrayList getLCExpenditureTrackingDATReportTsryWise(String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode)
	{
		glogger.info("-------Inside getLCExpenditureTrackingDATReportTsryWise of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcChqStatusLst = null;
		ArrayList lArrLcChqStatusLstDup=null;
		ArrayList lArrLcChqStatusLstBlank=null;
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		glogger.info("------------------------------DEPT CODE-------------------"+lStrDeptCode);
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrDistId="";
        String lStrDistNm="";
        String lStrDeptId="";
        String lStrDeptNm="";
        
        double lDblPlanRvn=0;
        double lDblPlanCap=0;
        double lDblPlanTot=0;
        double lDblNPlanRvn=0;
        double lDblNPlanCap=0;
        double lDblNPlanTot=0;
        double lDblTotal=0;
       
        double lDblPlanRvnTot=0;
        double lDblPlanCapTot=0;
        double lDblPlanTotTot=0;
        double lDblNPlanRvnTot=0;
        double lDblNPlanCapTot=0;
        double lDblNPlanTotTot=0;
        double lDblTotalTot=0;
        
        String lStrDistNmDup="";
        
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select dst.district_id, dst.district_name, lkp.lookup_id, lkp.lookup_name,  ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_c, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_c ");
        	
        	lStrBuff.append("from   trn_lc_dtl_posting dtl, ");
        	lStrBuff.append("cmn_location_mst loc ,cmn_lookup_mst lkp, cmn_district_mst dst    ");        	
        	
        	lStrBuff.append(" where dtl.division_code=loc.location_code ");
        	lStrBuff.append("and lkp.lookup_id=loc.type_lookup_id ");
        	lStrBuff.append("and loc.lang_id=lkp.lang_id and loc.loc_district_id=dst.district_id ");
        	
        	/*if(lStrPlanNonPlan.equals("0"))
        	{
        		lStrBuff.append("and   bud.budget_type >= 6 ");
        	}
        	if(lStrPlanNonPlan.equals("1"))
        	{
        		lStrBuff.append("and   bud.budget_type < 6 ");
        	}
        	if(lStrRevenueCapital.equals("0"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 2000 and bud.mjr_hd <= 3999 ");
        	}
        	if(lStrRevenueCapital.equals("1"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 4000 and bud.mjr_hd <= 5999 ");
        	}*/
        	if(!lStrDeptCode.equals(""))
        	{
        		lStrBuff.append("and  lkp.lookup_id='"+lStrDeptCode+"' ");
        	}
        	if(!lStrFrmDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        	}
        	if(!lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");
        	}
        	
        	lStrBuff.append("group by dst.district_id,dst.district_name,lkp.lookup_id,lkp.lookup_name  ");
        	
        	
        	glogger.info("------QUERY FOR GET getLCExpenditureTrackingDATReportTsryWise-----"+lStrBuff.toString());
        	glogger.info("------QUERY FOR GET getLCExpenditureTrackingDATReportTsryWise-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCExpenditureTrackingDATReportTsryWise after Query--------");
            
            String lNmNPlanRvn="";
            String lNmNPlanCap="";
            String lNmNPlanTot="";
			
            String lNmPlanRvn="";
            String lNmPlanCap="";
            String lNmPlanTot="";
            
            String lNmTotal="";
            String lStrFlag="0";
            int lineCntr=0;
            
            while(lRs.next())
            {            	
            	lArrLcChqStatusLst=new ArrayList();
            	
            	if(lRs.getObject("district_id") != null)
            		lStrDistId = lRs.getString("district_id"); 
            	if(lRs.getObject("district_name") != null)
            		lStrDistNm = lRs.getString("district_name"); 
            	if(lRs.getObject("lookup_id") != null)
            		lStrDeptId = lRs.getString("lookup_id"); 
            	if(lRs.getObject("lookup_name") != null)
            		lStrDeptNm = lRs.getString("lookup_name"); 
            	
            	if(lRs.getObject("p_r") != null)
            		lDblPlanRvn = lRs.getDouble("p_r");
            	if(lRs.getObject("p_c") != null)
            		lDblPlanCap = lRs.getDouble("p_c");   
            	if(lRs.getObject("np_r") != null)
            		lDblNPlanRvn = lRs.getDouble("np_r");            	            	
            	if(lRs.getObject("np_c") != null)
            		lDblNPlanCap = lRs.getDouble("np_c");
            	
            	lDblPlanTot=lDblPlanRvn+lDblPlanCap;
            	lDblNPlanTot=lDblNPlanRvn+lDblNPlanCap;
            	lDblTotal=lDblPlanTot+lDblNPlanTot;
            	
            	glogger.info("---"+lStrDeptNm+"----"+lDblPlanRvn+"-------"+lDblPlanCap+"--"+lDblPlanTot+"--"+lDblNPlanRvn+"---"+lDblNPlanCap+"---"+lDblNPlanTot);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
            	
				 lNmPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				 lNmPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				 lNmPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblPlanCap)).toString();
				
				 lNmNPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				 lNmNPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				 lNmNPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn+lDblNPlanCap)).toString();
				
				 lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblTotal)).toString();
            	
				glogger.info("----------------------------"+lStrPlanNonPlan);
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------PLAN----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------PLAN + REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------PLAN + CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------NON PLAN----------------");
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------ REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------ CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap+lDblNPlanCap)).toString();
				}
				
				
				
				if(lStrDistNmDup.equals(lStrDistNm))
            	    lArrLcChqStatusLst.add("");
				else
				{					
					if(lineCntr != 0)
						lStrFlag="1";
					
					lArrLcChqStatusLst.add(lStrDistNm);
				}
            	 lStrDistNmDup=lStrDistNm;
            	 
            	if(lStrFlag.equals("1"))
             	{
             		glogger.info("--------lineCntr------------"+lineCntr);
             		lArrLcChqStatusLstDup=new ArrayList();
                    
                    testNumberFormat = NumberFormat.getNumberInstance();
                	
        			String lNmTotalTot= testNumberFormat.parse(testNumberFormat.format(lDblTotalTot)).toString();
        			
        			String lNmPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvnTot)).toString();
        			String lNmPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanCapTot)).toString();
        			String lNmPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanTotTot)).toString();
        			
        			String lNmNPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvnTot)).toString();
        			String lNmNPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCapTot)).toString();
        			String lNmNPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTotTot)).toString();
        			
        			lArrLcChqStatusLstDup.add(""); 
        			lArrLcChqStatusLstDup.add("Total"); 
                    if(!lNmTotal.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmTotalTot); 
                    else
                    	lArrLcChqStatusLstDup.add("-"); 
                    
                    if(!lNmPlanRvn.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmPlanRvnTot);  
                    else
                    	lArrLcChqStatusLstDup.add("-"); 
                    
                    if(!lNmPlanCap.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmPlanCapTot);
                    else
                    	lArrLcChqStatusLstDup.add("-"); 
                    
                    if(!lNmPlanTot.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmPlanTotTot); 
                    else
                    	lArrLcChqStatusLstDup.add("-"); 
                    
                    if(!lNmNPlanRvn.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmNPlanRvnTot);  
                    else
                    	lArrLcChqStatusLstDup.add("-"); 
                    
                    if(!lNmNPlanCap.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmNPlanCapTot); 
                    else
                    	lArrLcChqStatusLstDup.add("-");
                    
                    if(!lNmNPlanTot.equals("-"))
                    	lArrLcChqStatusLstDup.add(lNmNPlanTotTot);
                    else
                    	lArrLcChqStatusLstDup.add("-");
                     
                    
                    lArrLcChqStatusLstDup.add(lStrDeptId); 
                	
                    lArrLcChqStatusLstDup.add(lStrPlanNonPlan);            	
                    lArrLcChqStatusLstDup.add(lStrRevenueCapital);             	
                    lArrLcChqStatusLstDup.add(lStrFrmDt); 
                    lArrLcChqStatusLstDup.add(lStrToDt);	
                    lArrLcChqStatusLstDup.add(lStrDeptCode);	
                    
                	lArrOuterLst.add(lArrLcChqStatusLstDup); 
 					
 					lDblTotalTot=0;
 					lDblPlanRvnTot=0;
 					lDblPlanCapTot=0;
 					lDblPlanTotTot=0;
 					lDblNPlanRvnTot=0;
 					lDblNPlanCapTot=0;
 					lDblNPlanTotTot=0;
 					
 					lStrFlag="0";
 					
 					lArrLcChqStatusLstBlank=new ArrayList();
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					lArrLcChqStatusLstBlank.add("");
 					
 					lArrOuterLst.add(lArrLcChqStatusLstBlank); 
             	} 
            	
				//lArrLcChqStatusLst.add(lStrDistNm);
            	lArrLcChqStatusLst.add(lStrDeptNm);
            	
            	lArrLcChqStatusLst.add(lNmTotal);
            	if(!lNmTotal.equals("-"))
            		lDblTotalTot += Double.parseDouble(lNmTotal);
            	
            	lArrLcChqStatusLst.add(lNmPlanRvn);  
            	if(!lNmPlanRvn.equals("-"))
            		lDblPlanRvnTot += Double.parseDouble(lNmPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmPlanCap);
            	if(!lNmPlanCap.equals("-"))
            		lDblPlanCapTot += Double.parseDouble(lNmPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmPlanTot);            	
            	if(!lNmPlanTot.equals("-"))
            		lDblPlanTotTot += Double.parseDouble(lNmPlanTot);
            	
            	lArrLcChqStatusLst.add(lNmNPlanRvn); 
            	if(!lNmNPlanRvn.equals("-"))
            		lDblNPlanRvnTot += Double.parseDouble(lNmNPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmNPlanCap);  
            	if(!lNmNPlanCap.equals("-"))
            		lDblNPlanCapTot += Double.parseDouble(lNmNPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmNPlanTot); 
            	if(!lNmNPlanTot.equals("-"))
            		lDblNPlanTotTot += Double.parseDouble(lNmNPlanTot);
            	
            	lArrLcChqStatusLst.add(lStrDistId); 
            	
            	lArrLcChqStatusLst.add(lStrPlanNonPlan);            	
            	lArrLcChqStatusLst.add(lStrRevenueCapital);             	
            	lArrLcChqStatusLst.add(lStrFrmDt); 
            	lArrLcChqStatusLst.add(lStrToDt);				
            	lArrLcChqStatusLst.add(lStrDeptCode);
            	
            	lArrOuterLst.add(lArrLcChqStatusLst);             	
            	
                lDblPlanTot=0;                
                lDblNPlanTot=0;
                lDblTotal=0;
                
                lineCntr++;
            }
           
            
            lArrLcChqStatusLst=new ArrayList();
            
            NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
        	
			String lNmTotalTot= testNumberFormat.parse(testNumberFormat.format(lDblTotalTot)).toString();
			
			String lNmPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvnTot)).toString();
			String lNmPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanCapTot)).toString();
			String lNmPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanTotTot)).toString();
			
			String lNmNPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvnTot)).toString();
			String lNmNPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCapTot)).toString();
			String lNmNPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTotTot)).toString();
			
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add("Total"); 
            if(!lNmTotal.equals("-"))
            	lArrLcChqStatusLst.add(lNmTotalTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanCapTot);
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanTotTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanCapTot); 
            else
            	lArrLcChqStatusLst.add("-");
            
            if(!lNmNPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanTotTot);
            else
            	lArrLcChqStatusLst.add("-");
             
            
            lArrLcChqStatusLst.add(lStrDeptId); 
        	
        	lArrLcChqStatusLst.add(lStrPlanNonPlan);            	
        	lArrLcChqStatusLst.add(lStrRevenueCapital);             	
        	lArrLcChqStatusLst.add(lStrFrmDt); 
        	lArrLcChqStatusLst.add(lStrToDt);
        	lArrLcChqStatusLst.add(lStrDeptCode);
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
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
        
        return lArrOuterLst; 
	}
	
	public ArrayList getLCExpenditureTrackingDATReportTsryDtls(String lStrTsryCode,String lStrDeptCode,String lStrPlanNonPlan,String lStrRevenueCapital,String lStrFrmDt,String lStrToDt,long lLngLoginLangId,long lIFinYrId,String LstrLoginLocCode)
	{
		glogger.info("-------Inside getLCExpenditureTrackingDATReportTsryWise of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcChqStatusLst = null;
		ArrayList lArrLcChqStatusLstDup=null;
		ArrayList lArrLcChqStatusLstBlank=null;
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrDivId="";
        String lStrDivNm="";
        String lStrDeptId="";
        String lStrDeptNm="";
        
        double lDblPlanRvn=0;
        double lDblPlanCap=0;
        double lDblPlanTot=0;
        double lDblNPlanRvn=0;
        double lDblNPlanCap=0;
        double lDblNPlanTot=0;
        double lDblTotal=0;
       
        double lDblPlanRvnTot=0;
        double lDblPlanCapTot=0;
        double lDblPlanTotTot=0;
        double lDblNPlanRvnTot=0;
        double lDblNPlanCapTot=0;
        double lDblNPlanTotTot=0;
        double lDblTotalTot=0;
        
        String lStrDeptNmDup="";
        String lStrFlag="0";
        int lineCntr=0;
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select lkp.lookup_id, lkp.lookup_name,loc.location_code,loc.loc_name,  ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type >= 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) p_c, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 2000 and 3999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_r, ");
        	lStrBuff.append("sum((select NVL(sum(bud.exp_amt),0) from trn_lc_budget_posting bud where bud.budget_type < 6 and bud.mjr_hd between 4000 and 5999 and dtl.lc_exp_id=bud.lc_exp_id and bud.active='0')) np_c ");
        	
        	lStrBuff.append("from   trn_lc_dtl_posting dtl, ");
        	lStrBuff.append("cmn_location_mst loc ,cmn_lookup_mst lkp, cmn_district_mst dst    ");        	
        	
        	lStrBuff.append(" where dtl.division_code=loc.location_code ");
        	lStrBuff.append("and lkp.lookup_id=loc.type_lookup_id ");
        	lStrBuff.append("and loc.lang_id=lkp.lang_id and loc.loc_district_id=dst.district_id ");
        	
        	/*if(lStrPlanNonPlan.equals("0"))
        	{
        		lStrBuff.append("and   bud.budget_type >= 6 ");
        	}
        	if(lStrPlanNonPlan.equals("1"))
        	{
        		lStrBuff.append("and   bud.budget_type < 6 ");
        	}
        	if(lStrRevenueCapital.equals("0"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 2000 and bud.mjr_hd <= 3999 ");
        	}
        	if(lStrRevenueCapital.equals("1"))
        	{
        		lStrBuff.append("and   bud.mjr_hd >= 4000 and bud.mjr_hd <= 5999 ");
        	}*/
        	if(!lStrTsryCode.equals(""))
        	{
        		lStrBuff.append("and dst.district_id='"+lStrTsryCode+"' ");
        	}
        	if(!lStrDeptCode.equals(""))
        	{
        		lStrBuff.append("and lkp.lookup_id='"+lStrDeptCode+"' ");
        	}
        	if(!lStrFrmDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        	}
        	if(!lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.created_date <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");
        	}
        	
        	lStrBuff.append("group by lkp.lookup_id,lkp.lookup_name,loc.location_code,loc.loc_name  ");
        	
        	
        	glogger.info("------QUERY FOR GET getLCExpenditureTrackingDATReportTsryDtls-----"+lStrBuff.toString());
        	glogger.info("------QUERY FOR GET getLCExpenditureTrackingDATReportTsryDtls-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCExpenditureTrackingDATReportTsryDtls after Query--------");
            
            String lNmNPlanRvn="";
            String lNmNPlanCap="";
            String lNmNPlanTot="";
			
            String lNmPlanRvn="";
            String lNmPlanCap="";
            String lNmPlanTot="";
            
            String lNmTotal="";
           
            while(lRs.next())
            {            	
            	
            	
            	
            	if(lRs.getObject("location_code") != null)
            		lStrDivId = lRs.getString("location_code"); 
            	if(lRs.getObject("loc_name") != null)
            		lStrDivNm = lRs.getString("loc_name"); 
            	if(lRs.getObject("lookup_id") != null)
            		lStrDeptId = lRs.getString("lookup_id"); 
            	if(lRs.getObject("lookup_name") != null)
            		lStrDeptNm = lRs.getString("lookup_name"); 
            	
            	if(lRs.getObject("p_r") != null)
            		lDblPlanRvn = lRs.getDouble("p_r");
            	if(lRs.getObject("p_c") != null)
            		lDblPlanCap = lRs.getDouble("p_c");   
            	if(lRs.getObject("np_r") != null)
            		lDblNPlanRvn = lRs.getDouble("np_r");            	            	
            	if(lRs.getObject("np_c") != null)
            		lDblNPlanCap = lRs.getDouble("np_c");
            	
            	lDblPlanTot=lDblPlanRvn+lDblPlanCap;
            	lDblNPlanTot=lDblNPlanRvn+lDblNPlanCap;
            	lDblTotal=lDblPlanTot+lDblNPlanTot;
            	
            	glogger.info("---"+lStrDeptNm+"----"+lDblPlanRvn+"-------"+lDblPlanCap+"--"+lDblPlanTot+"--"+lDblNPlanRvn+"---"+lDblNPlanCap+"---"+lDblNPlanTot);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
            	
				 lNmPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				 lNmPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				 lNmPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblPlanCap)).toString();
				
				 lNmNPlanRvn= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				 lNmNPlanCap= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				 lNmNPlanTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn+lDblNPlanCap)).toString();
				
				 lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblTotal)).toString();
            	
				glogger.info("----------------------------"+lStrPlanNonPlan);
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------PLAN----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------PLAN + REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("0") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------PLAN + CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanCap="-";
					lNmNPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals(""))
				{
					glogger.info("------------NON PLAN----------------");
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTot)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("1") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------NON PLAN + REVENUE----------------");
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmPlanRvn="-";
					lNmPlanCap="-";
					lNmPlanTot="-";
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCap)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("0"))
				{
					glogger.info("------------ REVENUE----------------");
					
					lNmPlanCap="-";
					lNmPlanTot=lNmPlanRvn;
					
					lNmNPlanCap="-";
					lNmNPlanTot=lNmNPlanRvn;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvn+lDblNPlanRvn)).toString();
				}
				if(lStrPlanNonPlan.equals("") && lStrRevenueCapital.equals("1"))
				{
					glogger.info("------------ CAPITAL----------------");
					
					lNmPlanRvn="-";
					lNmPlanTot=lNmPlanCap;
					
					lNmNPlanRvn="-";
					lNmNPlanTot=lNmNPlanCap;
					
					lNmTotal= testNumberFormat.parse(testNumberFormat.format(lDblPlanCap+lDblNPlanCap)).toString();
				}
				
				if(!lStrDeptNmDup.equals(lStrDeptNm) && lineCntr != 0)
				{					
						lStrFlag="1";						
				}				
			   
				
				if(lStrFlag.equals("1"))
				{
					    lArrLcChqStatusLst=new ArrayList();
			            
			            lArrLcChqStatusLst.add(lStrDeptNmDup); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            lArrLcChqStatusLst.add(""); 
			            
			        	
			        	lArrOuterLst.add(lArrLcChqStatusLst); 
			            //-----------------------------------
			            
			            lArrLcChqStatusLst=new ArrayList();
			            
			            testNumberFormat = NumberFormat.getNumberInstance();
			        	
						String lNmTotalTot= testNumberFormat.parse(testNumberFormat.format(lDblTotalTot)).toString();
						
						String lNmPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvnTot)).toString();
						String lNmPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanCapTot)).toString();
						String lNmPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanTotTot)).toString();
						
						String lNmNPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvnTot)).toString();
						String lNmNPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCapTot)).toString();
						String lNmNPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTotTot)).toString();
						
			            lArrLcChqStatusLst.add(lStrDeptNmDup); 
			            lArrLcChqStatusLst.add("Total"); 
			            if(!lNmTotal.equals("-"))
			            	lArrLcChqStatusLst.add(lNmTotalTot); 
			            else
			            	lArrLcChqStatusLst.add("-"); 
			            
			            if(!lNmPlanRvn.equals("-"))
			            	lArrLcChqStatusLst.add(lNmPlanRvnTot);  
			            else
			            	lArrLcChqStatusLst.add("-"); 
			            
			            if(!lNmPlanCap.equals("-"))
			            	lArrLcChqStatusLst.add(lNmPlanCapTot);
			            else
			            	lArrLcChqStatusLst.add("-"); 
			            
			            if(!lNmPlanTot.equals("-"))
			            	lArrLcChqStatusLst.add(lNmPlanTotTot); 
			            else
			            	lArrLcChqStatusLst.add("-"); 
			            
			            if(!lNmNPlanRvn.equals("-"))
			            	lArrLcChqStatusLst.add(lNmNPlanRvnTot);  
			            else
			            	lArrLcChqStatusLst.add("-"); 
			            
			            if(!lNmNPlanCap.equals("-"))
			            	lArrLcChqStatusLst.add(lNmNPlanCapTot); 
			            else
			            	lArrLcChqStatusLst.add("-");
			            
			            if(!lNmNPlanTot.equals("-"))
			            	lArrLcChqStatusLst.add(lNmNPlanTotTot);
			            else
			            	lArrLcChqStatusLst.add("-");			            
			        	
			        	lArrOuterLst.add(lArrLcChqStatusLst); 
			        	
			        	lDblTotalTot=0;
	 					lDblPlanRvnTot=0;
	 					lDblPlanCapTot=0;
	 					lDblPlanTotTot=0;
	 					lDblNPlanRvnTot=0;
	 					lDblNPlanCapTot=0;
	 					lDblNPlanTotTot=0;
	 					
	 					lStrFlag="0";
	 					
	 					
				}
				lStrDeptNmDup=lStrDeptNm;
				
				lArrLcChqStatusLst=new ArrayList();
				
				lArrLcChqStatusLst.add(lStrDeptNm);
            	lArrLcChqStatusLst.add(lStrDivNm);
            	
            	lArrLcChqStatusLst.add(lNmTotal);
            	if(!lNmTotal.equals("-"))
            		lDblTotalTot += Double.parseDouble(lNmTotal);
            	
            	lArrLcChqStatusLst.add(lNmPlanRvn);  
            	if(!lNmPlanRvn.equals("-"))
            		lDblPlanRvnTot += Double.parseDouble(lNmPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmPlanCap);
            	if(!lNmPlanCap.equals("-"))
            		lDblPlanCapTot += Double.parseDouble(lNmPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmPlanTot);            	
            	if(!lNmPlanTot.equals("-"))
            		lDblPlanTotTot += Double.parseDouble(lNmPlanTot);
            	
            	lArrLcChqStatusLst.add(lNmNPlanRvn); 
            	if(!lNmNPlanRvn.equals("-"))
            		lDblNPlanRvnTot += Double.parseDouble(lNmNPlanRvn);
            	
            	lArrLcChqStatusLst.add(lNmNPlanCap);  
            	if(!lNmNPlanCap.equals("-"))
            		lDblNPlanCapTot += Double.parseDouble(lNmNPlanCap);
            	
            	lArrLcChqStatusLst.add(lNmNPlanTot); 
            	if(!lNmNPlanTot.equals("-"))
            		lDblNPlanTotTot += Double.parseDouble(lNmNPlanTot);
            	
            	lArrOuterLst.add(lArrLcChqStatusLst);             	
            	
                lDblPlanTot=0;                
                lDblNPlanTot=0;
                lDblTotal=0;
                
                lineCntr++;
            }
            //--------BLANK ROW----------------
            lArrLcChqStatusLst=new ArrayList();
            
            lArrLcChqStatusLst.add(lStrDeptNm); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            lArrLcChqStatusLst.add(""); 
            
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
            //-----------------------------------
            
            lArrLcChqStatusLst=new ArrayList();
            
            NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
        	
			String lNmTotalTot= testNumberFormat.parse(testNumberFormat.format(lDblTotalTot)).toString();
			
			String lNmPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanRvnTot)).toString();
			String lNmPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanCapTot)).toString();
			String lNmPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblPlanTotTot)).toString();
			
			String lNmNPlanRvnTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanRvnTot)).toString();
			String lNmNPlanCapTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanCapTot)).toString();
			String lNmNPlanTotTot= testNumberFormat.parse(testNumberFormat.format(lDblNPlanTotTot)).toString();
			
            lArrLcChqStatusLst.add(lStrDeptNm); 
            lArrLcChqStatusLst.add("Total"); 
            if(!lNmTotal.equals("-"))
            	lArrLcChqStatusLst.add(lNmTotalTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanCapTot);
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmPlanTotTot); 
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanRvn.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanRvnTot);  
            else
            	lArrLcChqStatusLst.add("-"); 
            
            if(!lNmNPlanCap.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanCapTot); 
            else
            	lArrLcChqStatusLst.add("-");
            
            if(!lNmNPlanTot.equals("-"))
            	lArrLcChqStatusLst.add(lNmNPlanTotTot);
            else
            	lArrLcChqStatusLst.add("-");
            
        	
        	lArrOuterLst.add(lArrLcChqStatusLst); 
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
        
        return lArrOuterLst; 
	}
	//=============================================================================================
	
	//===== LC Form B - 150001===================================================================
	
	public ArrayList getAllDivisonUnderPost(String[] lStrDivisionCode , String lStrLcNo , String lStrFromDate , String lStrToDate,String lStrLoginLocCode,long lLngLangId)
	{
		HashMap<String,String> lHashData = new HashMap<String,String>();
		ArrayList lReturnArray = new ArrayList();
		Session hibSession = getSession();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date currDt = new Date();
		String lStrCurDt = sdf1.format(currDt);
		
		StringBuffer lStrQuery = new StringBuffer();
		glogger.info("----------value of divcode-----"+lStrDivisionCode);
		glogger.info("----------lcno-----"+lStrLcNo);
		glogger.info("----------todate-----"+lStrToDate);
		glogger.info("----------fromdate-----"+lStrFromDate);
		//glogger.info("----------postid-----"+lStrPostId);
		
		String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
		String lStrDivDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		if(lStrToDate.equals("") || lStrToDate==null)
		{
			lStrToDate=lStrCurDt;
		}
		if(lStrFromDate.equals("") || lStrFromDate==null)
		{
			lStrFromDate=lStrCurDt;
		}
		
		lStrQuery.append(" select t.lc_order_no,a.under_code||':'||a.under_code_desc,t.party_ref_no,t.party_ref_dt,t.inward_dt,t.inward_no,");
		lStrQuery.append(" t.division_code,t.lc_issue_dt,loc.loc_short_name,t.lc_amt  from trn_lc_distribution t,mst_lc_division_account a,");
		lStrQuery.append(" cmn_location_mst loc where t.active='0' ");
		glogger.info(" first element is :: "+ lStrDivisionCode[0].toString()+" and size is :: "+lStrDivisionCode.length);
		if(lStrDivisionCode[0].toString().equals("-1"))
		{
			lStrQuery.append("and t.division_code in( ");
			lStrQuery.append("select loc.location_code from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.department_id in ('"+lStrTsryDeptId+"','"+lStrDivDeptId+"') ");
			lStrQuery.append("and loc.loc_district_id=( ");
			lStrQuery.append("select loc.loc_district_id from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoginLocCode+"')) ");
		}
		else
		{
			if(lStrDivisionCode.length == 1 && !lStrDivisionCode[0].equalsIgnoreCase("-1"))
			{
				lStrQuery.append(" and t.division_code in ('" +lStrDivisionCode[0]+ "')");
			}
			else if(lStrDivisionCode.length >= 2)
			{
				lStrQuery.append(" and t.division_code in (");
				for (int i=0; i < lStrDivisionCode.length; i++)
					if(!lStrDivisionCode[i].equalsIgnoreCase("-1"))
					{
						if(i!=(lStrDivisionCode.length - 1))
						{
							lStrQuery.append("'"+lStrDivisionCode[i]+"',");
						}
						else
						{
							lStrQuery.append("'"+lStrDivisionCode[i]+"'");
						}
					}
				lStrQuery.append(")");
			}
			
		}
		glogger.info("============LC No is :: "+lStrLcNo);
		if((!(lStrLcNo.equals(""))) && (lStrLcNo != null) && (!(lStrLcNo.equals(" "))) )
		{
			lStrQuery.append(" and t.lc_order_no='"+lStrLcNo+"' ");
		}
		
		lStrQuery.append(" and t.inward_dt >= to_date('"+lStrFromDate+"','dd/MM/yyyy') ");
		lStrQuery.append(" and t.inward_dt <= to_date('"+lStrToDate+"','dd/MM/yyyy') ");
		lStrQuery.append("  and t.division_code = a.division_code and t.division_code = loc.location_code");
		lStrQuery.append(" order by t.lc_order_no ");
		
		glogger.info("Query for getAllDivisonUnderPost is : " + lStrQuery.toString());
		    
		Query sqlQuery = hibSession.createSQLQuery(lStrQuery.toString());
		    
		    
		glogger.info("Date is : " + lStrToDate + " and Date is :" + lStrFromDate);
		glogger.info("Query is now going to be generated");
	    Iterator iterator = sqlQuery.list().iterator();
	    Object lObj[];
	
		while (iterator.hasNext()) {
			lObj = (Object[]) iterator.next();
			if (lObj != null)
			{	
			try
			 {
				HashMap hashmap = new HashMap();
				hashmap.put("LcNo", lObj[0].toString());
				
				hashmap.put("SenderCode", lObj[1].toString());
				hashmap.put("Party Reference No.", lObj[2].toString());
				
				String lStrRefDate = lObj[3].toString();				
				Date dt1=sdf2.parse(lStrRefDate);				
				String lStrDispRefDate = sdf1.format(dt1);
				hashmap.put("Party Reference Date", lStrDispRefDate);
				
				String lStrDBDate = lObj[4].toString();				
				Date dt=sdf2.parse(lStrDBDate);				
				String lStrDispDate = sdf1.format(dt);				
				hashmap.put("InwardDate",lStrDispDate );
				
				hashmap.put("Inward No.", lObj[5].toString());
				hashmap.put("Code", lObj[8].toString());
				String lStrDBDate1 = lObj[7].toString();				
				Date d1t=sdf2.parse(lStrDBDate1);				
				String lStrDispDate1 = sdf1.format(dt);
				hashmap.put("IssueDate", lStrDispDate1);
				
				hashmap.put("Amount", lObj[9].toString());
				//hashmap.put("postid", lObj[4].toString());
				lReturnArray.add(hashmap);
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
			
		}
		
		return lReturnArray;
	}
	
	public HashMap LcFormBLetterOfCredit(String lStrLcNo)
	{
		glogger.info("Inside LcFormBLetterOfCredit of LcReportsQueryDAO ");
		HashMap lHashadd=new HashMap();
		LcDivisionInformationVO divInformationVO = new LcDivisionInformationVO();
		
		Session hibSession = getSession();
		StringBuffer lStrQuery = new StringBuffer();
		lStrQuery.append("select a.lc_valid_frm, 	");
		lStrQuery.append("		 a.lc_valid_to,  	");
		lStrQuery.append("		 a.lc_amt, 		 	");	
		lStrQuery.append("		 a.lc_available_amt,");
		lStrQuery.append("		 t.loc_name, ");
		lStrQuery.append("		 t.loc_addr_1,		");
		lStrQuery.append("		 t.loc_addr_2, a.entry_type_code, dis.district_name,t.loc_short_name ");
		lStrQuery.append(" from trn_lc_distribution a,cmn_location_mst t,cmn_district_mst dis ");
		lStrQuery.append(" where a.lc_order_no = '"+lStrLcNo+"'");
		lStrQuery.append(" and t.location_code=a.division_code ");
		lStrQuery.append(" and a.active='0' and t.loc_district_id=dis.district_id ");
		
		Query sqlQuery = hibSession.createSQLQuery(lStrQuery.toString());
		
		glogger.info(" Query for LC Form B is  :: "+sqlQuery.toString());
		
	    Iterator iterator = sqlQuery.list().iterator();
	    Object lObj[];
	
		while (iterator.hasNext()) {
			lObj = (Object[]) iterator.next();
			glogger.info("---------Inside While---");
		
		divInformationVO.setLc_order_id(lStrLcNo);
		divInformationVO.setLc_valid_from(lObj[0].toString());//valid from date
		divInformationVO.setDepartment_name(lObj[1].toString());//valid to date
		divInformationVO.setDepartmentCode(lObj[2].toString());//LC Amount
		divInformationVO.setOpening_lc(lObj[3].toString());//LC Available Amount
		divInformationVO.setDivision_name(lObj[4].toString());//Division Name
		
		if(lObj[5] != null)
		{
			divInformationVO.setDistrict_name(lObj[5].toString());//Location Address 1
		}
		else
		{
			divInformationVO.setDistrict_name("");//Location Address 1
		}
		if(lObj[6] != null)
		{
			divInformationVO.setDivisionId(lObj[6].toString());//Lacation Address 2
		}
		else
		{
			divInformationVO.setDivisionId("");//Location Address 2
		}
		
		if(lObj[7] != null)
		{
			divInformationVO.setDistrictCode(lObj[7].toString());//Distribution Type
		}
		else
		{
			divInformationVO.setDistrictCode("");//Distribution Type 
		}
		lHashadd.put("divInformationVO", divInformationVO);
		
		if(lObj[8] != null)
		{
			lHashadd.put("District Name",lObj[8].toString());//District name
		}
		else
		{
			lHashadd.put("District Name","");//District name 
		}
		
		if(lObj[9] != null)
		{
			lHashadd.put("LocShortName",lObj[9].toString());//District name
		}
		else
		{
			lHashadd.put("LocShortName","");//District name 
		}
	
		}
		glogger.info("------Before Retunning from LcFormBLetterOfCredit ");
		return lHashadd;
	}
	
	
	
	public List getLCLedgerAccountsReport(ArrayList lArrDivList,String lStrACDate,String lStrFromDate,String lStrToDate,String lStrChqNo)
	{
		
		glogger.info("====== Inside getLCLedgerAccountsReport method=====================");
		ArrayList lArrReturn = new ArrayList();
		ArrayList lArrInner = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		NumberFormat lnfLong=NumberFormat.getNumberInstance();
		lnfLong.setGroupingUsed(false);
		lnfLong.setMaximumFractionDigits(2);
		lnfLong.setMinimumFractionDigits(2);
		Double lDbOpBal=new Double(0.0);

		
		glogger.info("============ div list size :: "+lArrDivList.size());
		glogger.info("============ AC date :: "+lStrACDate);
		glogger.info("============ From date :: "+lStrFromDate);
		glogger.info("============ to date :: "+lStrToDate);
		glogger.info("============ ch no :: "+lStrChqNo);
		
		
		Session hibSession = getSession();
	try
	{	
		Double lDbDateTotDb = new Double(0.0);
		Double lDbDateTotCr = new Double(0.0);
		Double lDbDateTotWi = new Double(0.0);
		String lStrPrevDt = null;
		for( int lIdivCnt=0;lIdivCnt<lArrDivList.size();lIdivCnt++)
		{
		
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				Double lDblCurrBal= new Double(0.0);
				Double lDblDebitTot=new Double(0.0);
				//Double lDblClosingBal=new Double(0.0);
				
				String lStrDt = "";
				String lStrDivShrtNm="";
				//Long lLngCrdtTot=new Long(0);
				//Long lLngDebTot=new Long (0);
			    boolean lBOpeningBal=true;//To Add opening balance row only once in report.						
			
				ArrayList lArrDist = new ArrayList();
				
				sb = new StringBuffer();
				
				sb.append("select dtl.division_code, ");
				sb.append("loc.loc_short_name ||' : '||loc.loc_name, ");
			    sb.append("   dtl.advice_dt, ");
			    sb.append("   ch.cheque_dt, ");
			    sb.append("   ch.cheque_no, ");
			    sb.append("   ch.cheque_amt,dtl.opening_lc,dtl.trn_cntr ");
			    sb.append(" from trn_lc_cheque_posting ch, ");
			    sb.append("   trn_lc_dtl_posting    dtl, ");
			    sb.append("   cmn_location_mst      loc ");
			    sb.append(" where ch.chq_cancel_dt is null and dtl.division_code = loc.location_code and  ");

			    if(lStrACDate.equals(""))
			    	sb.append(" dtl.advice_dt >= to_date('"+lStrFromDate+"', 'dd/MM/yyyy') and  dtl.advice_dt <= to_date('"+lStrToDate+"', 'dd/MM/yyyy') and ");
			    else
			    	sb.append(" dtl.advice_dt = to_date('"+lStrACDate+"', 'dd/MM/yyyy') and  ");

			    sb.append("   ch.lc_exp_id = dtl.lc_exp_id and dtl.division_code = '"+lArrDivList.get(lIdivCnt)+"' ");
			    sb.append(" order by dtl.trn_cntr  ");
			    
			    Query sqlQuery = hibSession.createSQLQuery(sb.toString());
				glogger.info("------------------- Query for details is  :: "+sb.toString());
				
				Iterator iterator = sqlQuery.list().iterator();//Chq details and opening balance query

				
				ArrayList lArrChqList = new ArrayList();
				ArrayList lArrChqInner = null;
				while (iterator.hasNext()  ) 
				{
					lArrChqInner = new ArrayList();
					Object[] lObj = (Object[]) iterator.next();
					
					if(lObj!= null && lObj[1] != null  && lObj[2] != null && lObj[3] != null )
						
					{
						
						lArrChqInner.add(lObj[1].toString());//Div Shrt Nm  --0
						Date dt = sdf2.parse(lObj[2].toString());
						lStrPrevDt=sdf1.format(dt);
						lArrChqInner.add(lStrPrevDt);//Adv Date  --1
						Date dt1 = sdf2.parse(lObj[3].toString());
						String lStrChDt=sdf1.format(dt);
						lArrChqInner.add(lStrChDt);//chq dt  --2
						lArrChqInner.add(lObj[4].toString());//chq no  --3
						lArrChqInner.add(Double.parseDouble(lObj[5].toString()));//chq amt  --4
						lArrChqInner.add(Double.parseDouble(lObj[6].toString()));//opening bal  --5
						lArrChqInner.add(lObj[7].toString());//trn cntr  --6
						
						lArrChqList.add(lArrChqInner);
						
					}
				}
	
				glogger.info("---------------------- Size of debit chq array is :: "+ lArrChqList.size());
				
				
				sb2.append("select t.division_code, ");
				sb2.append("	loc.loc_short_name ||' : '||loc.loc_name, ");
				sb2.append(" 	t.inward_dt, ");
				sb2.append("	t.lc_amt, ");
				sb2.append("	t.entry_type_code,t.lc_available_amt,t.trn_cntr ");
				sb2.append("	from trn_lc_distribution t, cmn_location_mst loc ");
				sb2.append(" where t.division_code ='"+lArrDivList.get(lIdivCnt)+"' and ");
				
				if(lStrACDate.equals(""))
					sb2.append(" t.inward_dt >= to_date('"+lStrFromDate+"', 'dd/MM/yyyy') and  t.inward_dt <= to_date('"+lStrToDate+"', 'dd/MM/yyyy') and ");
				else
			    	sb2.append(" t.inward_dt = to_date('"+lStrACDate+"', 'dd/MM/yyyy') and  ");

				sb2.append("  t.division_code = loc.location_code ");
				sb2.append(" order by t.trn_cntr ");
				
				sqlQuery = hibSession.createSQLQuery(sb2.toString());
				glogger.info("-------------------- Query for credit and debit  :: "+sb2.toString());
				
				Iterator iterator2 = sqlQuery.list().iterator();//Chq details and opening balance query
				while(iterator2.hasNext())
				{
					Object[] lObj2 = (Object[]) iterator2.next();
					ArrayList lArrInnDist = new ArrayList();
					
					lArrInnDist.add(lObj2[1]);//div shrt nm --0
					Date dt = sdf2.parse(lObj2[2].toString());
					lStrPrevDt=sdf1.format(dt);
					lArrInnDist.add(lStrPrevDt);//acc date --1 
					lArrInnDist.add(lObj2[3].toString());//lc amt --2
					lArrInnDist.add(lObj2[4].toString());//entry type --3
					lArrInnDist.add(lObj2[5].toString());//available --4
					lArrInnDist.add(lObj2[6].toString());//trn cntr --5
					
					lArrDist.add(lArrInnDist);
					
				}
				glogger.info("---------------------- Size of debit credit array is :: "+ lArrDist.size());
					if(lArrDist.size()>0 || lArrChqList.size()>0)	
					{
						if(lBOpeningBal)
						{
							if(lArrChqList.size()==0)
							{
								lArrInner = new ArrayList();
								ArrayList openList = (ArrayList)lArrDist.get(0);
								lArrInner.add(openList.get(0).toString());
								glogger.info("Inside if of opening @1 cond....");
								lStrPrevDt=openList.get(1).toString();
								lArrInner.add(lStrPrevDt);
								lArrInner.add(" Opening Balance ...... ");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lDbOpBal = Double.parseDouble(openList.get(4).toString())-
								Double.parseDouble(openList.get(2).toString());
								lArrInner.add(lnfLong.format(lDbOpBal).toString());
								lArrInner.add(lnfLong.format(lDbOpBal).toString());
								lArrReturn.add(lArrInner);
								lDblCurrBal=lDbOpBal;
								lBOpeningBal=false;	
							}
							else if(lArrDist.size()==0)
							{
								lArrInner = new ArrayList();
								ArrayList lArrChqOpList = (ArrayList)lArrChqList.get(0);
								lStrPrevDt=lArrChqOpList.get(1).toString();
								glogger.info("Inside if of opening @2 cond....");
								lArrInner.add(lArrChqOpList.get(0).toString());
								lArrInner.add(lStrPrevDt);
								lArrInner.add(" Opening Balance ...... ");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lDbOpBal = Double.parseDouble(lArrChqOpList.get(5).toString());
								lArrInner.add(lnfLong.format(lDbOpBal));
								lArrInner.add(lnfLong.format(lDbOpBal));
								lArrReturn.add(lArrInner);
								lDblCurrBal=lDbOpBal;
								lBOpeningBal=false;
							}
							else if((new Long(((ArrayList)lArrChqList.get(0)).get(6).toString()))< (new Long(((ArrayList)lArrDist.get(0)).get(5).toString())))
							{
								lArrInner = new ArrayList();
								ArrayList openList = (ArrayList)lArrDist.get(0);
								lArrInner.add(openList.get(0).toString());
								glogger.info("Inside if of opening @3 cond....");
								lStrPrevDt=openList.get(1).toString();
								lArrInner.add(lStrPrevDt);
								lArrInner.add(" Opening Balance ...... ");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lDbOpBal = Double.parseDouble(openList.get(4).toString());
								glogger.info("This is being coverted ======55=== :: "+lDbOpBal);
								lArrInner.add(lnfLong.format(lDbOpBal).toString());
								lArrInner.add(lnfLong.format(lDbOpBal).toString());
								lArrReturn.add(lArrInner);
								lDblCurrBal=lDbOpBal;
								lBOpeningBal=false;
							}
							else
							{
								lArrInner = new ArrayList();
								ArrayList lArrChqOpList = (ArrayList)lArrChqList.get(0);
								lStrPrevDt=lArrChqOpList.get(1).toString();
								glogger.info("Inside if of opening @4 cond....");
								lArrInner.add(lArrChqOpList.get(0).toString());
								lArrInner.add(lStrPrevDt);
								lArrInner.add(" Opening Balance ...... ");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lArrInner.add("");
								lDbOpBal = Double.parseDouble(lArrChqOpList.get(5).toString());
								glogger.info("This is being coverted ======44=== :: "+lDbOpBal);
								lArrInner.add(lnfLong.format(lDbOpBal));
								lArrInner.add(lnfLong.format(lDbOpBal));
								lArrReturn.add(lArrInner);
								lDblCurrBal=lDbOpBal;
								lBOpeningBal=false;
							}
						glogger.info("---------- Current Balance @ Opening balance row is :: "+lDblCurrBal);
						}
						
						glogger.info("---------- Prev date after opening balance is :: "+lStrPrevDt);
						
						ArrayList lArrFinalList = new ArrayList();
						
						
						lDbDateTotDb = new Double(0.0);
						lDbDateTotCr = new Double(0.0);
						lDbDateTotWi = new Double(0.0);
						
						String typeFlag="";
						if(lArrChqList.size()==0)
						{
							for(int i=0;i<lArrDist.size();i++)
							{
								String divShrtNm = "";
								String accDate = "";
								String chqDate = "";
								String chqNo = "";
								String debit = "";
								String credit= "";
								String withdraw = "";
								String currentBal = "";
								String PrevBal = "";
								ArrayList lArrInnerRows=null;
								glogger.info("----------size of dist array in detailed table is  :: "+lArrDist.size());
								ArrayList lArrFinInn=(ArrayList)lArrDist.get(i);
								
								String lStrAccDt = (String)lArrFinInn.get(1);
								glogger.info("-----------------Preve date @ 1 is ::  "+lStrPrevDt+"  and  acc date is   ::  "+lStrAccDt);
								if(lStrAccDt.equals(lStrPrevDt))
								{
									glogger.info("--------------going into equals date @1--------");
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									String lcType=(String)lArrFinInn.get(3);
									if(lcType.equals("150006"))
									{
										credit=(String)lArrFinInn.get(2);
										lDblCurrBal=lDblCurrBal+Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotCr=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									else
									{
										withdraw=(String)lArrFinInn.get(2);
										lDblCurrBal=lDblCurrBal-Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotWi=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									currentBal=lnfLong.format(Double.parseDouble(lArrFinInn.get(4).toString()));
									
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(debit);
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									glogger.info("----------credit @ 1 is :: "+credit);
									glogger.info("This is being coverted ====1===== :: "+lDblCurrBal);
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
								}
								else//if dates are not equal
								{
									glogger.info("--------------going into not equals date @1--------");
									
									lArrInner=new ArrayList();
									lArrInner.add((String)lArrFinInn.get(0));
									glogger.info("----------div shrt name at 1st is :: "+lStrDivShrtNm);
									lArrInner.add("");
									
									lArrInner.add(" Date Total.....");
									lArrInner.add("");
									lArrInner.add("");
									lArrInner.add(lnfLong.format(lDbDateTotCr));
									lArrInner.add(lnfLong.format(lDbDateTotWi));
									lArrInner.add("-");
									lArrInner.add("-");
									lArrReturn.add(lArrInner);
									lStrPrevDt=lStrAccDt;
									
									lDbDateTotCr=new Double(0.0);
									lDbDateTotWi=new Double(0.0);
									lDbDateTotDb=new Double(0.0);
									
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									String lcType=(String)lArrFinInn.get(3);
									if(lcType.equals("150006"))
									{
										credit=(String)lArrFinInn.get(2);
										lDblCurrBal=lDblCurrBal+Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotCr=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									else
									{
										withdraw=(String)lArrFinInn.get(2);
										lDblCurrBal=lDblCurrBal-Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotWi=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									currentBal=lnfLong.format(Double.parseDouble(lArrFinInn.get(4).toString()));
									
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(debit);
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									glogger.info("This is being coverted =======77== :: "+lDblCurrBal);
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
									
									
								}
							}
							
						}
						else if(lArrDist.size()==0)
						{
							Double lDbCur=new Double(0.0);
							for(int i=0;i<lArrChqList.size();i++)
							{
								String divShrtNm = "";
								String accDate = "";
								String chqDate = "";
								String chqNo = "";
								String debit = "";
								String credit= "";
								String withdraw = "";
								String currentBal = "";
								String PrevBal = "";
								ArrayList lArrInnerRows=null;
								ArrayList lArrFinInn=(ArrayList)lArrChqList.get(i);
								String lStrAccDt = (String)lArrFinInn.get(1);
								//Double lDbCur=(Double)lArrFinInn.get(5)-(Double)lArrFinInn.get(4);
								
								glogger.info("-----------------Preve date @ 2 is ::  "+lStrPrevDt+"  and  acc date is   ::  "+lStrAccDt);
								if(lStrAccDt.equals(lStrPrevDt))
								{
									glogger.info("--------------going into equals date @2--------");
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									chqDate=(String)lArrFinInn.get(2);
									chqNo=(String)lArrFinInn.get(3);
									//debit=(String)lArrFinInn.get(4);//////
									//currentBal=(String)lArrFinInn.get(5);
									lDblCurrBal=lDblCurrBal-(Double)lArrFinInn.get(4);
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(lnfLong.format(lArrFinInn.get(4)));
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									glogger.info("This is being coverted =======88== :: "+lDblCurrBal);
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
								}
								else//if dates are not equal
								{
									glogger.info("--------------going into not equals date @2--------");
									lArrInner=new ArrayList();
									lArrInner.add((String)lArrFinInn.get(0));
									glogger.info("----------div shrt name at 2nd is :: "+lStrDivShrtNm);
									lArrInner.add("");
									lArrInner.add(" Date Total.....");
									lArrInner.add("");
									lArrInner.add(lnfLong.format(lDblDebitTot).toString());
									lArrInner.add("");
									lArrInner.add("");
									lArrInner.add("");
									lArrInner.add("");
									lArrReturn.add(lArrInner);
									lStrPrevDt=lStrAccDt;
									
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									chqDate=(String)lArrFinInn.get(2);
									chqNo=(String)lArrFinInn.get(3);
									//debit=(String)lArrFinInn.get(4);//////
									//currentBal=(String)lArrFinInn.get(5);
									lDblCurrBal=lDblCurrBal-(Double)lArrFinInn.get(4);
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									glogger.info("This is being coverted ======00=== :: "+lArrFinInn.get(4));
									glogger.info("This is being coverted ======99=== :: "+lDblCurrBal);
									lArrInnerRows.add(lnfLong.format(lArrFinInn.get(4)));
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
								}
							}
						}
						else
						{
						
						
						int j=0,k=0;
						glogger.info("------------------------------------------------------------------------------------------------------------------");
						glogger.info("-----------size of ch array is :: "+lArrChqList.size()+"    and dist array is :: "+lArrDist.size());
						for (int i=0;i<(lArrChqList.size()+lArrDist.size());i++)
						{
							
							glogger.info("----------------------------------counter is :: "+i);
							glogger.info("--------------------------------------- J is :: "+j);
							glogger.info("----------------------------------------K is :: "+k);
							
							if(j==lArrChqList.size())
							{
								typeFlag="CHQ";
								break;
							}
							if(k==lArrDist.size())
							{
								typeFlag="DIST";
								break;
							}
								ArrayList lArrInnerChLst = (ArrayList)lArrChqList.get(j);
								ArrayList lArrInnerDistLst = (ArrayList)lArrDist.get(k);
								
								glogger.info("-------------------------------value of K is :: "+lArrInnerChLst.get(6).toString());
								glogger.info("-------------------------------value of K is :: "+lArrInnerDistLst.get(5).toString());
								glogger.info("---------------------------------------------------------------");
								
								if(   new Long(lArrInnerChLst.get(6).toString())< (new Long(lArrInnerDistLst.get(5).toString()))  )
								{
									lArrFinalList.add(lArrInnerChLst);
									
									j++;
									
								}
								else
								{
									lArrFinalList.add(lArrInnerDistLst);
									k++;
									
								}
							
							
						}
						
						
						
						
						if(typeFlag.equals("CHQ"))
						{
							for(int i=k;i<lArrDist.size();i++)
							{
								lArrFinalList.add(lArrDist.get(i));
							}
						}
						else
						{
							for(int i=j;i<lArrChqList.size();i++)
							{
								lArrFinalList.add(lArrChqList.get(i));
							}
						}
						
						
						
						for(int i=0;i<lArrFinalList.size();i++)
						{
							String divShrtNm = "";///////////from here
							String accDate = "";
							String chqDate = "";
							String chqNo = "";
							String debit = "";
							String credit= "";
							String withdraw = "";
							String currentBal = "";
							String PrevBal = "";
							ArrayList lArrInnerRows=null;
							ArrayList lArrFinInn=(ArrayList)lArrFinalList.get(i);
							String lStrAccDt = (String)lArrFinInn.get(1);
							glogger.info("-----------------Preve date @ 3 is ::  "+lStrPrevDt+"  and  acc date is   ::  "+lStrAccDt);
							if(lStrAccDt.equals(lStrPrevDt))
							{
								glogger.info("--------------going into equals date @3--------");
								if(lArrFinInn.size()==6)
								{
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									String lcType=(String)lArrFinInn.get(3);
									if(lcType.equals("150006"))
									{
										
										credit=lnfLong.format(Double.parseDouble(lArrFinInn.get(2).toString()));
										lDblCurrBal=lDblCurrBal+Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotCr=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									else
									{
										withdraw=lnfLong.format(Double.parseDouble(lArrFinInn.get(2).toString()));
										lDblCurrBal=lDblCurrBal-Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotWi=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									currentBal=lnfLong.format(Double.parseDouble(lArrFinInn.get(4).toString()));
									
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(debit);
									glogger.info("------------ credit here @ 555 is :: "+credit);
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
									

								}
								else
								{
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									chqDate=(String)lArrFinInn.get(2);
									chqNo=(String)lArrFinInn.get(3);
									//debit=(String)lArrFinInn.get(4);//////
									//currentBal=(String)lArrFinInn.get(5);
									lDblCurrBal=lDblCurrBal-Double.parseDouble(lArrFinInn.get(4).toString());
									lDbDateTotDb=lDbDateTotDb+Double.parseDouble(lArrFinInn.get(4).toString());
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(lnfLong.format(lArrFinInn.get(4)));
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
								}
									
							}
							else//if dates are not equal
							{
								//lDbDateTotCr=lDbDateTotCr+(Double)lArrFinInn.get(2);
								glogger.info("--------------going into not equals date @3--------");
								lArrInner=new ArrayList();
								lArrInner.add((String)lArrFinInn.get(0));
								glogger.info("----------div shrt name at 3rd is :: "+lStrDivShrtNm);
								lArrInner.add("");
								lArrInner.add(" Date Total.....");
								lArrInner.add("");
								lArrInner.add(lnfLong.format(lDbDateTotDb));
								lArrInner.add(lnfLong.format(lDbDateTotCr));
								lArrInner.add(lnfLong.format(lDbDateTotWi));
								lArrInner.add("-");
								lArrInner.add("-");
								lArrReturn.add(lArrInner);
								lStrPrevDt=lStrAccDt;
								
								lDbDateTotCr=new Double(0.0);
								lDbDateTotWi=new Double(0.0);
								lDbDateTotDb=new Double(0.0);
							
								
								
								if(lArrFinInn.size()==6)
								{
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									String lcType=(String)lArrFinInn.get(3);
									if(lcType.equals("150006"))
									{
										credit=lnfLong.format(Double.parseDouble(lArrFinInn.get(2).toString()));
										lDblCurrBal=lDblCurrBal+Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotCr=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									else
									{
										withdraw=lnfLong.format(Double.parseDouble(lArrFinInn.get(2).toString()));
										lDblCurrBal=lDblCurrBal-Double.parseDouble(lArrFinInn.get(2).toString());
										lDbDateTotWi=lDbDateTotCr+Double.parseDouble(lArrFinInn.get(2).toString());
									}
									currentBal=lnfLong.format(Double.parseDouble(lArrFinInn.get(4).toString()));
									
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(debit);
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
									

								}
								else
								{
									lArrInnerRows=new ArrayList();
									divShrtNm=(String)lArrFinInn.get(0);
									accDate=(String)lArrFinInn.get(1);
									chqDate=(String)lArrFinInn.get(2);
									chqNo=(String)lArrFinInn.get(3);
									//debit=(String)lArrFinInn.get(4);//////
									//currentBal=(String)lArrFinInn.get(5);
									lDblCurrBal=lDblCurrBal-Double.parseDouble(lArrFinInn.get(4).toString());
									lDbDateTotDb=lDbDateTotDb+Double.parseDouble(lArrFinInn.get(4).toString());
									lArrInnerRows.add(divShrtNm);
									lArrInnerRows.add(accDate);
									lArrInnerRows.add(chqDate);
									lArrInnerRows.add(chqNo);
									lArrInnerRows.add(lnfLong.format(lArrFinInn.get(4)));
									lArrInnerRows.add(credit);
									lArrInnerRows.add(withdraw);
									
									lArrInnerRows.add(lnfLong.format(lDblCurrBal));
									lArrInnerRows.add(PrevBal);
									lArrReturn.add(lArrInnerRows);
									lStrPrevDt=lStrAccDt;
								}
								
							}
							lStrPrevDt=lStrAccDt;
						}
					}	
				}//both array size check if ends
					
					
			
			}//division for loop
		int ARRAY_SZ = lArrReturn.size();
		ArrayList lArrLastRec = (ArrayList)lArrReturn.get(ARRAY_SZ-1);
		ArrayList lArrInnerRows=new ArrayList();
		lArrInnerRows.add(lArrLastRec.get(0));
		lArrInnerRows.add("");
		lArrInnerRows.add(" Date Total.....");
		lArrInnerRows.add("");
		lArrInnerRows.add(lnfLong.format(lDbDateTotDb));
		lArrInnerRows.add(lnfLong.format(lDbDateTotCr));
		lArrInnerRows.add(lnfLong.format(lDbDateTotWi));
		lArrInnerRows.add("");
		lArrInnerRows.add("");
		lArrReturn.add(lArrInnerRows);
		
		lArrInnerRows=new ArrayList();
		lArrInnerRows.add(lArrLastRec.get(0));
		lArrInnerRows.add("");
		lArrInnerRows.add("");
		lArrInnerRows.add("");
		lArrInnerRows.add("");
		lArrInnerRows.add("");
		lArrInnerRows.add(" Closing Balance....");
		lArrInnerRows.add(lArrLastRec.get(7));
		lArrInnerRows.add(lArrLastRec.get(7));
		lArrReturn.add(lArrInnerRows);
		
	}//try
		catch(Exception e)
		{
			e.printStackTrace();
		}
		glogger.info("--------------------------------------------------------------------------------");
		for(int i=0;i<lArrReturn.size();i++)
		{
			for(int j=0;j<((ArrayList)lArrReturn.get(i)).size();j++)
			{
				glogger.info(((ArrayList)lArrReturn.get(i)).get(j));
			}
			glogger.info("--------------------------------------------------------------------------------");
		}
		return lArrReturn;
  }

	//=========================================================================================
	
	//***************************DIVISION DASHBOARD REPORT - 150019 ********************************
	public ArrayList getLCDivisionDashboardReport(String lStrDivCode,String lStrFrmDt, String lStrToDt, long lLngLoginLangId,long lIFinYrId,String lStrLoginLocCode) 
	{
		glogger.info("-------Inside getLCDivisionDashboardReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcDivDashboardLst = null;
		
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null; 
        
        String lStrDivisionCode="";  
        String lStrDivShrtNm="";
        String lStrDivNmDup="";
        
        String lStrMjrHd="";
        double lDblLcAmt=0;
        double lDblLcApprovAmt=0;
        double lDblLcPendingAmt=0;
        double lDblLcReconcileAmt=0;
        double lDblLcAvlAmt=0;
        
        double lDblTotLcAmt=0;
        double lDblTotLcApprovAmt=0;
        double lDblTotLcPendingAmt=0;
        double lDblTotLcReconcileAmt=0;
        double lDblTotLcAvlAmt=0;
        
        int lineCntr=0;
        Number lNumRecDup=null;
        
        Number lNumTotLcAmt= null;
        Number lNumTotLcApprovAmt= null;
		Number lNumTotLcPendingAmt= null;     				
		Number lNumTotAvlAmt= null;
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select (select loc.loc_short_name from cmn_location_mst loc where loc.location_code=d.division_code) divShrtNm, ");
        	lStrBuff.append("d.division_code,db.mjr_hd,nvl(sum(db.amount),0) LC_Amt, ");
        	lStrBuff.append("   (select nvl(sum(bud.exp_amt),0) from trn_lc_dtl_posting dtl,trn_lc_budget_posting bud  ");
        	lStrBuff.append("      where dtl.fin_year_id="+lIFinYrId+" and bud.fin_year_id="+lIFinYrId+" and dtl.advice_approved='0' and bud.lc_exp_id=dtl.lc_exp_id ");
        	lStrBuff.append("      and d.division_code=dtl.division_code and db.mjr_hd=bud.mjr_hd  ) Approved_Amt, ");
        	lStrBuff.append("   (select nvl(sum(bud.exp_amt),0) from trn_lc_dtl_posting dtl,trn_lc_budget_posting bud ");
        	lStrBuff.append("      where dtl.fin_year_id="+lIFinYrId+" and bud.fin_year_id="+lIFinYrId+" and dtl.advice_approved='1' and bud.lc_exp_id=dtl.lc_exp_id  ");
        	lStrBuff.append("      and d.division_code=dtl.division_code and db.mjr_hd=bud.mjr_hd  ) Pending_Amt, ");
        	lStrBuff.append("   (select nvl(sum(chq.cheque_amt),0) from trn_lc_dtl_posting pst ,trn_lc_cheque_posting chq ");
        	lStrBuff.append("      where pst.fin_year_id="+lIFinYrId+" and chq.fin_year_id="+lIFinYrId+" and pst.lc_exp_id = chq.lc_exp_id and chq.chq_clr_dt is not null ");
        	lStrBuff.append("      and pst.division_code=d.division_code ) Reconciled_Amt ");
        	
        	lStrBuff.append("from trn_lc_distribution d , trn_lc_distribution_bud_hd db ");
        	lStrBuff.append("where d.lc_order_id = db.lc_order_id ");        	    	        	
        	
        	
        	lStrBuff.append("and d.fin_year_id="+lIFinYrId+" and db.fin_year_id="+lIFinYrId+" ");
        		
        	
        	        	
        	if(!lStrDivCode.equals(""))
        	{
        		lStrBuff.append("and  d.division_code='"+lStrDivCode+"' ");
        	}
        	
        	lStrBuff.append(" group by d.division_code,db.mjr_hd ");    
        	
        	
        	glogger.info("------QUERY FOR GET getLCDivisionDashboardReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCDivisionDashboardReport after Query--------");
          
            while(lRs.next())
            {            	
            	lineCntr++;
            	lArrLcDivDashboardLst=new ArrayList();
            	
            	if(lRs.getObject("divShrtNm") != null)
            		lStrDivShrtNm = lRs.getString("divShrtNm");  
            	if(lRs.getObject("division_code") != null)
            		lStrDivisionCode = lRs.getString("division_code");  
            	if(lRs.getObject("mjr_hd") != null)
            		lStrMjrHd = lRs.getString("mjr_hd");    
            	if(lRs.getObject("LC_Amt") != null)
            		lDblLcAmt = lRs.getDouble("LC_Amt");
            	if(lRs.getObject("Approved_Amt") != null)
            		lDblLcApprovAmt = lRs.getDouble("Approved_Amt");
            	if(lRs.getObject("Pending_Amt") != null)
            		lDblLcPendingAmt = lRs.getDouble("Pending_Amt");   
            	if(lRs.getObject("Reconciled_Amt") != null)
            		lDblLcReconcileAmt = lRs.getDouble("Reconciled_Amt");            	            	
            	            	
            	
            	glogger.info("---"+lStrDivisionCode+"----"+lStrMjrHd+"-------"+lDblLcAmt+"-------"+lDblLcApprovAmt+"----"+lDblLcPendingAmt+"---"+lDblLcReconcileAmt+"---");
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNumLcAmt= testNumberFormat.parse(testNumberFormat.format(lDblLcAmt));
				Number lNumLcApprovAmt= testNumberFormat.parse(testNumberFormat.format(lDblLcApprovAmt));
				Number lNumLcPendingAmt= testNumberFormat.parse(testNumberFormat.format(lDblLcPendingAmt));
				Number lNumLcReconcileAmt= testNumberFormat.parse(testNumberFormat.format(lDblLcReconcileAmt));
				Number lNumAvlAmt= testNumberFormat.parse(testNumberFormat.format(lDblLcAmt-lDblLcApprovAmt));
            	
				lArrLcDivDashboardLst.add(lStrDivShrtNm);
				lArrLcDivDashboardLst.add(lStrMjrHd);
				lArrLcDivDashboardLst.add(lNumLcAmt);
				lArrLcDivDashboardLst.add(lNumLcApprovAmt);
				lArrLcDivDashboardLst.add(lNumLcPendingAmt);
				lArrLcDivDashboardLst.add("-");
				lArrLcDivDashboardLst.add(lNumAvlAmt);
            	            	
            	lArrOuterLst.add(lArrLcDivDashboardLst); 
            	
            	/*if(lStrDivShrtNm.equals(lStrDivNmDup) || lineCntr == 1)
            	{*/
            		 
            	/*}*/
            	glogger.info("----"+lineCntr+"----"+lStrDivNmDup+"----"+lStrDivShrtNm);
            	if(!lStrDivShrtNm.equals(lStrDivNmDup) && lineCntr != 1)
            	{
            		glogger.info("------inside if----");
            		lArrLcDivDashboardLst=new ArrayList();
            		
            		lArrLcDivDashboardLst.add(lStrDivNmDup);
    				lArrLcDivDashboardLst.add("Total");
    				lArrLcDivDashboardLst.add(lNumTotLcAmt);
    				lArrLcDivDashboardLst.add(lNumTotLcApprovAmt);
    				lArrLcDivDashboardLst.add(lNumTotLcPendingAmt);
    				lArrLcDivDashboardLst.add(lNumRecDup);
    				lArrLcDivDashboardLst.add(lNumTotAvlAmt);
            		
    				lArrOuterLst.add(lArrLcDivDashboardLst); 
    				
    				lDblTotLcAmt=0;
    				lDblTotLcApprovAmt=0;
    				lDblTotLcPendingAmt=0;
    				lDblTotLcAvlAmt=0;
            	}
            	
            	lDblTotLcAmt+=lDblLcAmt;
                lDblTotLcApprovAmt+=lDblLcApprovAmt;
                lDblTotLcPendingAmt+=lDblLcPendingAmt;                    
                lDblTotLcAvlAmt+=(lDblLcAmt-lDblLcApprovAmt);
                
                lNumTotLcAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotLcAmt));
                lNumTotLcApprovAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotLcApprovAmt));
				lNumTotLcPendingAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotLcPendingAmt));     				
				lNumTotAvlAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotLcAvlAmt));
            	
				//Set the Value for totat row
            	lStrDivNmDup=lStrDivShrtNm;
            	lNumRecDup=lNumLcReconcileAmt;
            }
            glogger.info("------last row----");
    		lArrLcDivDashboardLst=new ArrayList();
    		
    		lArrLcDivDashboardLst.add(lStrDivNmDup);
			lArrLcDivDashboardLst.add("Total");
			lArrLcDivDashboardLst.add(lNumTotLcAmt);
			lArrLcDivDashboardLst.add(lNumTotLcApprovAmt);
			lArrLcDivDashboardLst.add(lNumTotLcPendingAmt);
			lArrLcDivDashboardLst.add(lNumRecDup);
			lArrLcDivDashboardLst.add(lNumTotAvlAmt);
    		
			lArrOuterLst.add(lArrLcDivDashboardLst); 
            
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
        
        return lArrOuterLst; 
	}
	//=============================================================================================
	
	//**************************** LC BALANCE APPROVAL REPORT -- 150021 ****************************
	public ArrayList getLCBalanceApprovalReport(String lStrDivisionCode[],String lStrFrmDt, String lStrToDt, long lLngLangId,long lIFinYrId,String lStrLoginLocCode) 
	{
		glogger.info("-------Inside getLCBalanceApprovalReport of LCReportQueryDAOImpl------------");		
		
		ArrayList lArrLcBalApprovalLst = null;	
		ArrayList lArrOuterLst= new ArrayList();
		StringBuffer lStrBuff = new StringBuffer();
		
		String lStrTsryDeptId=bundleConst.getString("LC.TSRY_DEPT_ID");
		String lStrDivDeptId=bundleConst.getString("LC.DIVISION_DEPT_ID");
		
        Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        
        String lStrDivCode="";
        String lStrDivShrtNm="";
        String lStrTotChq="";
        double lDblTotChqAmt=0;
        double lDblTotAvlAmt=0;
        
        int lineCntr=0;
        
        try
        {        	
        	lCon = DBConnection.getConnection();
        	
        	lStrBuff.append("select k.divcode,k.locShrtName,k.totChq,k.totChqAmt,d.closing_lc,k.cntr ");
        	
        	lStrBuff.append("from trn_lc_dtl_posting d, "); 
        	
        	// Inner Query Start which is playing role as a table 'k'
        	lStrBuff.append("(select dtl.division_code divcode, count(chq.cheque_no) totChq,sum(chq.cheque_amt) totChqAmt, ");
        	lStrBuff.append(         " max(dtl.trn_cntr) cntr, ");
        	lStrBuff.append(         "(select loc.loc_short_name from cmn_location_mst loc where loc.location_code=dtl.division_code) locShrtName ");
        	lStrBuff.append("from trn_lc_dtl_posting dtl, trn_lc_cheque_posting chq ");
        	lStrBuff.append("where dtl.lc_exp_id=chq.lc_exp_id ");
        	lStrBuff.append(	   "and dtl.advice_approved='0' and dtl.active='0' and dtl.fin_year_id="+lIFinYrId+" ");
        	lStrBuff.append(	   "and chq.active='0' and chq.chq_cancel_dt is null and chq.fin_year_id="+lIFinYrId+" ");
        	      	
        	
        	if(lStrDivisionCode.equals("") || lStrDivisionCode[0].equals("-1"))
        	{
        		glogger.info("---------INSIDE IF QUERY--------------");
        		
        		lStrBuff.append("  and dtl.division_code in( ");
        		lStrBuff.append("select loc.location_code from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.department_id in ('"+lStrTsryDeptId+"','"+lStrDivDeptId+"') ");
        		lStrBuff.append("and loc.loc_district_id=( ");
        		lStrBuff.append("select loc.loc_district_id from cmn_location_mst loc where loc.lang_id="+lLngLangId+" and loc.location_code='"+lStrLoginLocCode+"')) ");
        		
        	}
        	
        	if(lStrDivisionCode.length > 0)
			{
				if(lStrDivisionCode.length == 1 && !lStrDivisionCode[0].equalsIgnoreCase("-1"))
				{
					lStrBuff.append(" and dtl.division_code in ('" +lStrDivisionCode[0]+ "')");
				}
				else if(lStrDivisionCode.length >= 2)
				{
					lStrBuff.append(" and dtl.division_code in (");
					for (int i=0; i < lStrDivisionCode.length; i++)
						if(!lStrDivisionCode[i].equalsIgnoreCase("-1"))
						{
							if(i!=(lStrDivisionCode.length - 1))
							{
								lStrBuff.append("'"+lStrDivisionCode[i]+"',");
							}
							else
							{
								lStrBuff.append("'"+lStrDivisionCode[i]+"'");
							}
						}
					lStrBuff.append(")");
				}
			}
        	
        	if(!lStrFrmDt.equals("") && !lStrToDt.equals(""))
        	{
        		lStrBuff.append("and dtl.advice_dt >= to_date('"+lStrFrmDt+"','dd/MM/yyyy') ");
        		lStrBuff.append("and dtl.advice_dt <= to_date('"+lStrToDt+"','dd/MM/yyyy') ");        		
        	}
        	        	
        	lStrBuff.append("group by dtl.division_code) k "); 
        	//Inner Query End
        	
        	lStrBuff.append("where d.trn_cntr=k.cntr "); 
        	lStrBuff.append("order by k.divcode ");
        	//Outer Query End
        	
        	glogger.info("------QUERY FOR GET getLCBalanceApprovalReport-----"+lStrBuff.toString());
        	lStmt = lCon.prepareStatement( lStrBuff.toString() );
            lRs = lStmt.executeQuery();
            glogger.info("------------getLCBalanceApprovalReport after Query--------");
          
            while(lRs.next())
            {            	
            	lineCntr++;
            	
            	lArrLcBalApprovalLst=new ArrayList();
            	
            	if(lRs.getObject("divcode") != null)            	
            		lStrDivCode = lRs.getString("divcode");    
            	if(lRs.getObject("locShrtName") != null)            	
            		lStrDivShrtNm = lRs.getString("locShrtName");   
            	if(lRs.getObject("totChq") != null)
            		lStrTotChq = lRs.getString("totChq");            	            	
            	if(lRs.getObject("totChqAmt") != null)
            		lDblTotChqAmt = lRs.getDouble("totChqAmt");
            	if(lRs.getObject("closing_lc") != null)
            		lDblTotAvlAmt = lRs.getDouble("closing_lc");
            	
            	glogger.info("---"+lStrDivCode+"---"+lStrDivShrtNm+"----"+lStrTotChq+"----"+lDblTotChqAmt+"----"+lDblTotAvlAmt);
            	
            	NumberFormat testNumberFormat = NumberFormat.getNumberInstance();
				Number lNumTotChqAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotChqAmt));
				Number lNumTotAvlAmt= testNumberFormat.parse(testNumberFormat.format(lDblTotAvlAmt));
            	
            	
            	lArrLcBalApprovalLst.add(lineCntr);
            	lArrLcBalApprovalLst.add(lStrDivShrtNm);
            	lArrLcBalApprovalLst.add(lStrTotChq);
            	lArrLcBalApprovalLst.add(lNumTotChqAmt);            	
            	lArrLcBalApprovalLst.add(lNumTotAvlAmt); 
            	
            	lArrOuterLst.add(lArrLcBalApprovalLst);
            	
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
        
        return lArrOuterLst; 
	}
	
	public String getTsryNameByLoggedInTsryCode(String lStrLocCode)
	{
		Connection lCon = null;
        PreparedStatement lStmt = null;
        ResultSet lRs = null;        
        glogger.info("------COMING LC lLLocCode-------"+lStrLocCode);
		String lStrTsryNm="";
		ArrayList lArrDedIdLst=new ArrayList();
		
		try
		{
			lCon = DBConnection.getConnection();
			StringBuffer lStrBuff = new StringBuffer();
			
			lStrBuff.append("select loc.loc_name from cmn_location_mst loc where loc.location_code='"+lStrLocCode+"'");
			
			
		    lStmt = lCon.prepareStatement( lStrBuff.toString() );
	        lRs = lStmt.executeQuery();
	        while(lRs.next())
            {            	
	        	lStrTsryNm =(lRs.getString("loc_name"));	        	
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
		
		return lStrTsryNm;
	}
	//********************************************************************************************************
	
	public HashMap getChequeCertiData(String lStrMon,String lStrYear,String lStrDivCode)
	{
		HashMap lMpChqData =  new HashMap();
		
		try
		{
			
		}
		catch(Exception e)
		{
			glogger.error("Error in Cheque Cerificate method in Report Query Dao :: ", e);
			e.printStackTrace();
		}
		
		
		return lMpChqData;
		
	}
}






