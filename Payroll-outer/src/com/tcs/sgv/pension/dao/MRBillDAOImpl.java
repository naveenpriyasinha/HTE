package com.tcs.sgv.pension.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.tcs.sgv.common.constant.Constants;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.pension.valueobject.MRCasesVO;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;

public class MRBillDAOImpl implements MRBillDAO
{
    /* Global Variable for Logger Class */
    private Log logger = LogFactory.getLog(getClass());
    
    /* Global Variable for Session Class */
	private Session ghibSession = null;

    ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pension/PensionConstants");

    private SessionFactory sessionFactory = null;
    
    public MRBillDAOImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() 
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
    
    public List getMRBillData(String fromDate,String toDate,String lStrBranchCode,BigDecimal BDHeadCode) throws Exception
    {
    	List resList = null;
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			Session ghibSession = getSession();
			
			SimpleDateFormat lDateFormatOut = new SimpleDateFormat("MM/dd/yyyy");
			//---
			SimpleDateFormat lDateFormatIn = new SimpleDateFormat("dd/MM/yyyy");			
			//----				
			Date lDtFrom = lDateFormatIn.parse(fromDate);
			Date lDtTo = lDateFormatIn.parse(toDate);
			
			lSBQuery.append(" SELECT r.inwd_no,r.ppo_no,r.pensioner_name,r.rem_amnt,r.grant_amnt,oem.emp_fname||''||oem.emp_mname||''||oem.emp_lname,r.med_rembrsmnt_id ");
			lSBQuery.append(" FROM trn_pension_med_rembrsmnt r,org_emp_mst oem ,org_userpost_rlt t ");
			lSBQuery.append(" WHERE  r.branch_code ="+ lStrBranchCode + " AND r.head_code ="+ BDHeadCode+" AND r.status = 60 AND oem.user_id = t.user_id and t.post_id = r.authoriser_post_id AND r.bill_hdr_id IS NULL ");
			lSBQuery.append(" AND r.inwd_Date BETWEEN to_date('"+ lDateFormatOut.format(lDtFrom).toString() +"','MM/dd/yyyy') " + " AND to_date('"+ lDateFormatOut.format(lDtTo).toString()+"','MM/dd/yyyy')");
			
			
			Query Query=ghibSession.createSQLQuery(lSBQuery.toString());
			
			resList=Query.list();
		}
    	catch(Exception e)
    	{
    		logger.error(e);
    		e.printStackTrace();
    	}
    	return resList;

    }
	public List getTrnPensionMedRembrsmnt(Long lTrnPensionBillHdrPK) throws Exception
	{
		List resList = null;
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
			lSBQuery.append(" SELECT r.inwd_no,r.ppo_no,r.pensioner_name,r.rem_amnt,r.grant_amnt,oem.emp_fname||''||oem.emp_mname||''||oem.emp_lname,r.med_rembrsmnt_id ");
			lSBQuery.append(" FROM trn_pension_med_rembrsmnt r,org_emp_mst oem ,org_userpost_rlt t ");
			lSBQuery.append(" WHERE r.bill_Hdr_Id ="+lTrnPensionBillHdrPK+" and oem.user_id = t.user_id and t.post_id = r.authoriser_post_id ");
	        
	        Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
	        
	        resList=lQuery.list();
	          
	   }
	   catch(Exception e)
	   {
			  logger.error(" Error is : " + e, e);
			  throw(e);
	   }
		return resList;
		
	}
	public List getBnkBrnchHdcodeList(Long lTrnPensionBillHdrPK) throws Exception
	{
		List resList = null;
		
		try
		{
			StringBuilder lSBQuery = new StringBuilder();
			ghibSession = getSession();
			
			lSBQuery.append(" SELECT rbb.bankCode,r.branchCode,r.headCode ");
			lSBQuery.append(" FROM TrnPensionMedRembrsmnt r,RltBankBranch rbb ");
			lSBQuery.append(" WHERE r.billHdrId ="+lTrnPensionBillHdrPK+" AND r.branchCode = rbb.branchCode ");
	        
	        Query lQuery = ghibSession.createQuery(lSBQuery.toString());
	        
	        resList=lQuery.list();
	          
	   }
	   catch(Exception e)
	   {
			  logger.error(" Error is : " + e, e);
			  throw(e);
	   }
		return resList;		
	}
}
