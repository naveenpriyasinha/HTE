package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.common.service.BillProcConstants;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAO;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MRBillDAO;
import com.tcs.sgv.pension.dao.MRBillDAOImpl;
import com.tcs.sgv.pension.dao.MRCaseDAO;
import com.tcs.sgv.pension.dao.MRCaseDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAO;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAOImpl;
import com.tcs.sgv.pension.valueobject.MonthlyPensionBillVO;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;


/**
 * Medical Reimbursement Implementation.
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class MRBillServiceImpl extends ServiceImpl implements MRBillService
{
    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;

    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());

    /* Global Variable for Current Date */
    Date gDtCurrDt = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    /* Global Variable for FinancialyearId */
    long gLngFinYearId = 0;
    
    public ResultObject createMRBill(Map inputMap)
    {
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(serv.getSessionFactory());
        
        BigDecimal[] AuthoriserPostId = new BigDecimal[10000];
        String[] AuthoriserName = new String[10000];
        
        List<String> AuthoriserList = new ArrayList<String>();

        try
        {
        	setSessionInfo(inputMap);
            MRBillDAO MRBillDAO = new MRBillDAOImpl(serv.getSessionFactory());
        	
        	String  lStrFromDate = StringUtility.getParameter("fromInwdDate", request);
        	String  lStrToDate = StringUtility.getParameter("toInwdDate", request);
        	String  lStrBranchCode = StringUtility.getParameter("cmbBranchCode", request);
        	String  lStrBankCode = StringUtility.getParameter("cmbBankCode", request);
        	String  lStrHeadCode = StringUtility.getParameter("cmbHeadCode", request);
        	
        	BigDecimal totalGrantAmnt = new BigDecimal(0);
        	
        	List MRBillDetailsList = MRBillDAO.getMRBillData(lStrFromDate,lStrToDate,lStrBranchCode,new BigDecimal(lStrHeadCode));
        	
        	if (MRBillDetailsList !=null && ! MRBillDetailsList.isEmpty()) 
        	{
	        	for(int i=0;i<MRBillDetailsList.size();i++)
	            {
	                Object[] tuple = (Object[])MRBillDetailsList.get(i);           	
	                
	                if(!(tuple[4].toString()).equals(""))
	                {
	                	totalGrantAmnt = totalGrantAmnt.add(new BigDecimal(tuple[4].toString()));
	                }
	            }
        	}
        	inputMap.put("MRBillTokenNo","");
        	inputMap.put("Bank",lStrBankCode);
        	inputMap.put("Branch",lStrBranchCode);
        	inputMap.put("HeadCode",lStrHeadCode);
        	inputMap.put("TotalGrantAmount",totalGrantAmnt);
        	
        	inputMap.put("MRBillDetailsList",MRBillDetailsList);
        }
        catch (Exception e)
        {
            gLogger.error("Error is;" + e, e);
        }
        
        resObj.setResultValue(inputMap);
        //resObj.setViewName("MRBills");
        return resObj;
    }
    
    /**
     * Saves Reimbursement Bill Data in  TRN_PENSION_BILL_HDR and TRN_PENSION_MED_REMBRSMNT
     * @param Map:lMapInput
     * @return ResultObject
     */    	
    public ResultObject saveMRBill(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        List<TrnPensionBillDtls> lLstPensionBillDtls = null;
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,serv.getSessionFactory());
        //Long lLngPnsnTokenNo = 0L;
        MRCaseServiceImpl lObjCaseServiceImpl = new MRCaseServiceImpl();
        
        String lStrBillNo = null;
        
    	lStrBillNo = (String) inputMap.get("billNo");
    	Long lPnsnTokenNo = (Long) inputMap.get("PnsnTokenNo");
        
        String lStrBillType="MR";

        try
        {
        	setSessionInfo(inputMap);
        	lObjTrnPensionBillHdr = (TrnPensionBillHdr) inputMap.get("MRTrnPensionBillHdrVO");
        	
        	// Insert date into TrnPensuionBillHdr.
        	lObjTrnPensionBillHdr.setBillType(lStrBillType);
        	lObjTrnPensionBillHdr.setBillNo(lStrBillNo);
            
            if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
            {
                lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
            }
            
        	lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
        	
        	//insert multiple records in trn_pension_bill_dtls
        	lLstPensionBillDtls = (List<TrnPensionBillDtls>) inputMap.get("MRTrnPensionBillDtlsVO");
        	TrnPensionBillDtls lObjPensionBillDtls = null;
            Iterator<TrnPensionBillDtls> lItrMR = lLstPensionBillDtls.iterator();
            while (lItrMR.hasNext())
            {
            	lObjPensionBillDtls = lItrMR.next();
            	saveMRDtls(lObjPensionBillDtls, inputMap);
            }
            inputMap.put("MRTrnPensionBillDtlsVO", lLstPensionBillDtls);
            inputMap.put("PensionType", "MR");
            
            String lOBPMBillNo = lObjTrnPensionBillHdr.getBillNo();
            //lPnsnTokenNo = Long.parseLong(lObjTrnPensionBillHdr.getTokenNo());
        	Long lLngPnsnBillNo = Long.parseLong(lOBPMBillNo);
        	if(lPnsnTokenNo != null)
        	{
        		OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
               	//lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocationCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
        	}
        	objRes.setResultValue(inputMap);
        }
        catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
        
        //lObjCaseServiceImpl.getSavedMRCases(inputMap);
        return objRes;
    } 
	private void saveMRDtls(TrnPensionBillDtls lObjPensionBillDtls, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	
    	try {
    		TrnPensionBillDtlsDAO lObjPensionDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());
    		lObjPensionBillDtls.setTrnPensionBillDtlsId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap)));
    		lObjPensionDtlsDAO.create(lObjPensionBillDtls);
		} 
    	catch (Exception e) {
			gLogger.error(" Error is : " + e, e); 
			throw(e);
		}
    }
	
	//Method to view saved MR Bill
	public ResultObject viewMRBillData(Map inputMap)
	{
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(srvcLoc.getSessionFactory());
        MRBillDAO MRBillDAO = new MRBillDAOImpl(srvcLoc.getSessionFactory());
        
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionBillDtls lObjTrnPensionBillDtlsVO = null;
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;

        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
        TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, srvcLoc.getSessionFactory());
        TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,srvcLoc.getSessionFactory());
        
        String lStrBillType = "MR";
        String lStrCaseStatus ="APPROVED";
        String lStrTokenNo = null;
        Date lStrBillDate = null;
        Long lTrnPensionBillHdrPK = 0L;
        String lStrPPONo = null;
        String lStrPnsnerName = null;
        String lStrInwdNo = null;
        BigDecimal BDReimbrseAmnt = null;
        BigDecimal BDGrantedAmnt = null;
        BigDecimal BDAuthPostId = null;
        String lStrAuthName = null;
        Integer lIntSerialNo = 0;
        BigDecimal totalGrantAmnt = new BigDecimal(0);
        String lStrBankCode = null;
        String lStrBranchCode = null;
        String lStrHeadCode = null;

		try
		{
            setSessionInfo(inputMap);
            String lStrBillNo = inputMap.get("billNo").toString();
            List lLstTrnPensionMedRembrsmnt = null;

            if(lStrBillNo != null && lStrBillNo.length() > 0 ) 
        	{
        		// Getting the ObjectVo of  TrnPensionBillHdrVO
        		lObjTrnPensionBillHdr = (TrnPensionBillHdr) lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo,lStrBillType);
        		lStrBillDate = lObjTrnPensionBillHdr.getBillDate();
        		lStrTokenNo = lObjTrnPensionBillHdr.getTokenNo();
        		lTrnPensionBillHdrPK = lObjTrnPensionBillHdr.getTrnPensionBillHdrId();
        		lLstTrnPensionMedRembrsmnt = MRBillDAO.getTrnPensionMedRembrsmnt(lTrnPensionBillHdrPK);
        	}
            if(lLstTrnPensionMedRembrsmnt != null && !lLstTrnPensionMedRembrsmnt.isEmpty())
        	{
            	for(int i=0;i<lLstTrnPensionMedRembrsmnt.size();i++)
        		{
	                Object[] tuple = (Object[])lLstTrnPensionMedRembrsmnt.get(i);           	
	                
	                if(!(tuple[4].toString()).equals(""))
	                {
	                	totalGrantAmnt = totalGrantAmnt.add(new BigDecimal(tuple[4].toString()));
	                }
        		}
        	}
            List lLstBankBrnchHdCode = MRBillDAO.getBnkBrnchHdcodeList(lTrnPensionBillHdrPK);
            if(lLstBankBrnchHdCode != null && !lLstBankBrnchHdCode.isEmpty())
        	{
            	for(int i=0;i<lLstBankBrnchHdCode.size();i++)
        		{
	                Object[] tuple = (Object[])lLstBankBrnchHdCode.get(i);           	
	                lStrBankCode = tuple[0].toString();
	                lStrBranchCode = tuple[1].toString();
	                lStrHeadCode = tuple[2].toString();
        		}
        	}
            inputMap.put("MRBillTokenNo",lStrTokenNo);
            inputMap.put("Bank",lStrBankCode);
        	inputMap.put("Branch",lStrBranchCode);
        	inputMap.put("HeadCode",lStrHeadCode);
        	inputMap.put("TotalGrantAmount",totalGrantAmnt);
        	inputMap.put("MRBillDetailsList",lLstTrnPensionMedRembrsmnt);
		}
        catch (Exception e)
        {
        	gLogger.error(" Error is : " + e, e);
        	resObj.setResultValue(null);
        	resObj.setThrowable(e);
        	resObj.setResultCode(ErrorConstants.ERROR);
        	resObj.setViewName("errorPage");
        }
        resObj.setResultValue(inputMap);
		return resObj;
	}
    private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        //gLngFinYearId = SessionHelper.getFinYrId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
}
