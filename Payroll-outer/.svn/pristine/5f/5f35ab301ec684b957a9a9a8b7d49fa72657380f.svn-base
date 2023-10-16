package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.common.service.BillProcConstants;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAO;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRecoveryDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionRecoveryDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRecoveryDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

	/**
	 * CVP Bill Specific Service Impl.
	 * 
	 * @author Soumya Adhikari
	 * @version 1.0
	 */
    public class CVPBillServiceImpl extends ServiceImpl implements CVPBillService
    {
    	/* Global Variable for PostId */
    	private Long gLngPostId = null;

        /* Global Variable for UserId */
    	private Long gLngUserId = null;
    	
        /* Glonal Variable for Location Code */
    	private String gStrLocCode = null;
        
        /* Global Variable for Logger Class */
    	private final Log gLogger = LogFactory.getLog(getClass());
        
        /* Global Variable for Current Date */
    	private Date gDtCurrDt = null;
    	
    	private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");         
        
        /**
         * Shows CVP Bill Data in bill
         * @param Map:lMapInput
         * @return ResultObject
         */
    	public ResultObject getCVPBillData(Map inputMap)
        { 
            ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
            ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            HttpServletRequest request = null;
            TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
            MstPensionerHdr lObjMstPensionerHdrVO = null;
            TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = null;
            List lLstObjTrnPensionRecoveryDtls = null;
            Map lMapCVPData = new HashMap();
            String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
            String lStrCaseStatus = bundleConst.getString("CMN.NEW");
            String lStrRcvryFlag = bundleConst.getString("RECOVERY.CVP");
            String lStrPPONo = null;
            String lStrPnsnerName = null;
            String lStrPnsrCode = null;
            String lStrOfficeAddr = null;
            String lStrDesignation = null;
            String lStrDesgDesc = null;
            String lStrFirstname = null;
            String lStrMiddlename = "";
            String lStrLastname = null;
            BigDecimal lCVPAmt = null;
            BigDecimal lNetAmt = null;
            BigDecimal lRecAmt = new BigDecimal(0);     
            String[] lRecoveryAmt = new String[10];
            String[] lStrEDPCode = new String[10];
            String[] lStrMjrHdCode = new String[10];
            String[] lStrSubmjrhdCode = new String[10];
            String[] lStrMinhdCode = new String[10];
            String[] lStrSubhdCode = new String[10];
            
            String lHeadcode = null;
            String lScheme = null;
            
            TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
            MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory()); 
            TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class,srvcLoc.getSessionFactory());
            TrnPensionRecoveryDtlsDAO lObjTrnPensionRecoveryDtlsDAO = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class,srvcLoc.getSessionFactory());
            
            int lIntListSize = 0;
            long lIntNetAmt = 0;
            
            try
            {
                setSessionInfo(inputMap);
                request = (HttpServletRequest) inputMap.get("requestObj");
            	lStrPPONo = inputMap.get("PPONo").toString();
            	if(request.getParameter("auditFlag") != null && request.getParameter("auditFlag").toString().equalsIgnoreCase("Y") )
            	{
            		TrnPensionRqstHdr lObjAprvdRqstHdr = new TrnPensionRqstHdr();
            		TrnPensionRqstHdr lObjChngdRqstHdr = new TrnPensionRqstHdr();
            		lObjChngdRqstHdr = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPPONo(lStrPPONo, lStrStatus, "CHANGED");
            		lObjAprvdRqstHdr = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPPONo(lStrPPONo, lStrStatus, "APPROVED");
            		if(lObjAprvdRqstHdr != null && lObjChngdRqstHdr != null )
            		{
            			lCVPAmt = lObjAprvdRqstHdr.getCvpAmount().subtract(lObjChngdRqstHdr.getCvpAmount());
            			lStrPnsrCode = lObjAprvdRqstHdr.getPensionerCode();
                		lHeadcode = lObjAprvdRqstHdr.getHeadCode().toString();
                		lScheme = lObjAprvdRqstHdr.getSchemeType();
            		}
            	}
            	else if(lStrPPONo != null && lStrPPONo.length() > 0 ) 
            	{
            		// Getting the VO of  TrnPensionRqstHdr
            		lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPPONo(lStrPPONo, lStrStatus, lStrCaseStatus);
            		if(lObjTrnPensionRqstHdrVO != null)
                	{
                		// Getting the Value from TrnPensionRqstHdr... Start...
                		lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
                		lCVPAmt = lObjTrnPensionRqstHdrVO.getCvpAmount();
                		lHeadcode = lObjTrnPensionRqstHdrVO.getHeadCode().toString();
                		lScheme = lObjTrnPensionRqstHdrVO.getSchemeType();
                		// Getting the Value from TrnPensionRqstHdr... End...
                	}
            	}
            	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0)
            	{
            		// Getting the ObjectVo of  MstPensionerHdr... Start...
            		lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsrCode);
            		lStrFirstname = lObjMstPensionerHdrVO.getFirstName(); 
            		lStrMiddlename = (lObjMstPensionerHdrVO.getMiddleName()!=null) ? lObjMstPensionerHdrVO.getMiddleName() + " " :"";
					lStrLastname = lObjMstPensionerHdrVO.getLastName();
	        		lStrPnsnerName = lStrFirstname + ' ' + lStrMiddlename + lStrLastname;
            		lStrOfficeAddr = lObjMstPensionerHdrVO.getOfficeAddr();
            		lStrDesignation = lObjMstPensionerHdrVO.getDesignation();
            		lStrDesgDesc = lObjTrnPensionBillDtlsDAO.getDesgDesc(lStrDesignation);
    	        	//	Getting the ObjectVo of  MstPensionerHdr... End...
    	        	//	Getting the ObjectVo of  TrnPensionRecoveryDtls... Start...
            		lLstObjTrnPensionRecoveryDtls = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtls(lStrPnsrCode, lStrRcvryFlag);
            		lIntListSize = lLstObjTrnPensionRecoveryDtls.size();
            		for(int i=0;i<lLstObjTrnPensionRecoveryDtls.size();i++)
	        		{	
            			lObjTrnPensionRecoveryDtls = (TrnPensionRecoveryDtls) lLstObjTrnPensionRecoveryDtls.get(i);
	        			if(lObjTrnPensionRecoveryDtls != null)
	        			{
	        				if((lObjTrnPensionRecoveryDtls.getAmount())==null)
	        				{
	        					lRecoveryAmt[i]= "0";
	        				}
	        				else
	        				{
	        					lRecoveryAmt[i] = lObjTrnPensionRecoveryDtls.getAmount().toString();
	        				}
	        				if((lObjTrnPensionRecoveryDtls.getEdpCode())==null)
	        				{
	        					lStrEDPCode[i]= "0";
	        				}
	        				else
	        				{
	        					lStrEDPCode[i] = lObjTrnPensionRecoveryDtls.getEdpCode().toString();
	        				}
	        				
	        				if((lObjTrnPensionRecoveryDtls.getMjrhdCode())==null)
	        				{
	        					lStrMjrHdCode[i]= "0";
	        				}
	        				else
	        				{
	        					lStrMjrHdCode[i] = lObjTrnPensionRecoveryDtls.getMjrhdCode().toString();
	        				}
	        				
	        				if((lObjTrnPensionRecoveryDtls.getSubmjrhdCode())==null)
	        				{
	        					lStrSubmjrhdCode[i]= "0";
	        				}
	        				else
	        				{
	        					lStrSubmjrhdCode[i] = lObjTrnPensionRecoveryDtls.getSubmjrhdCode().toString();
	        				}
	        					
	        				if((lObjTrnPensionRecoveryDtls.getMinhdCode())==null)
	        				{
	        					lStrMinhdCode[i]="0";
	        				}
	        				else
	        				{
	        					lStrMinhdCode[i] = lObjTrnPensionRecoveryDtls.getMinhdCode().toString();
	        				}
	        				if((lObjTrnPensionRecoveryDtls.getSubhdCode())==null)
	        				{
	        					lStrSubhdCode[i]= "0";
	        				}
	        				else
	        				{
	        					lStrSubhdCode[i] = lObjTrnPensionRecoveryDtls.getSubhdCode().toString();
	        				}	        					
	        				lMapCVPData.put("EDP", lStrEDPCode[i]);
	                        lMapCVPData.put("MajorHead", lStrMjrHdCode[i]);
	                        lMapCVPData.put("SubMajorHead", lStrSubmjrhdCode[i]);
	                        lMapCVPData.put("MinorHead", lStrMinhdCode[i]);
	                        lMapCVPData.put("SubHead", lStrSubhdCode[i]);	                        	                        
	        			}
	        		}
            		// Getting the ObjectVo of  TrnPensionRecoveryDtls... End...            		
            	}
            	// Recovery Amount and Net Amount Calculation.
            	lNetAmt = lCVPAmt;
            	lRecAmt = new BigDecimal(0);
            	for(int i=0;i<lIntListSize;i++)
            	{
            		lRecAmt = lRecAmt.add(new BigDecimal(lRecoveryAmt[i]));
            		lNetAmt = lNetAmt.subtract(new BigDecimal(lRecoveryAmt[i]));
            	}         
                lIntNetAmt = lNetAmt.longValue();
            	inputMap.put("PPONo", lStrPPONo);
            	inputMap.put("Name", lStrPnsnerName);
            	inputMap.put("Designation", lStrDesgDesc);
            	inputMap.put("OfficeAddr", lStrOfficeAddr);
            	inputMap.put("CVPAmount", lCVPAmt);
            	inputMap.put("RecoveryAmount", lRecAmt);
            	inputMap.put("NetAmount", lIntNetAmt);
            	inputMap.put("BillDate",gDtCurrDt);
            	inputMap.put("MapCVPData", lMapCVPData); 
            	inputMap.put("PnsnrCode", lStrPnsrCode);
            	inputMap.put("Headcode", lHeadcode);
            	inputMap.put("Scheme", lScheme);
            	resObj.setResultValue(inputMap); 
            }
            catch (Exception e)
            {
            	gLogger.error(" Error is : " + e, e);
            	resObj.setResultValue(null);
            	resObj.setThrowable(e);
            	resObj.setResultCode(ErrorConstants.ERROR);
            	resObj.setViewName("errorPage");
            }         		
    		return resObj;
        }
    	
        /**
         * Saves CVP specific Bill Data in  TRN_PENSION_BILL_DTLS and RLT_PENSIONCASE_BILL
         * @param Map:lMapInput
         * @return ResultObject
         */    	
    	public ResultObject saveCVPBill(Map inputMap)
        {
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            
            long lLngRltPensioncaseBillId=0;
            String lStrBillNo = (String) inputMap.get("billNo");
            String lStrBillCntrlNo = (String) inputMap.get("PnsnBillCntrlNo");
            Long lPnsnTokenNo = (Long) inputMap.get("PnsnTokenNo");
            String lStrBillType=bundleConst.getString("RECOVERY.CVP");
            
            TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,serv.getSessionFactory());
        	TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());
        	RltPensioncaseBillDAO lObjRltPensioncaseBillDAO = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,serv.getSessionFactory());
        	TrnPensionRecoveryDtlsDAO lObjTrnRecoveryDtls = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class,serv.getSessionFactory());
        	
            TrnPensionBillHdr lObjTrnPensionBillHdr = null;
            TrnPensionBillDtls lObjTrnPensionBillDtls = null;
            RltPensioncaseBill lObjRltPensioncaseBill = null;
            try
            {  
            	setSessionInfo(inputMap);
            	//Getting VO Object from inputMap with the key "CVPTrnPensionBillDtlsVO"
            	lObjTrnPensionBillHdr = (TrnPensionBillHdr) inputMap.get("CVPTrnPensionBillHdrVO");
            	lObjTrnPensionBillDtls = (TrnPensionBillDtls) inputMap.get("CVPTrnPensionBillDtlsVO");
            	// Insert date into TrnPensuionBillHdr.
            	lObjTrnPensionBillHdr.setBillType(lStrBillType);
            	lObjTrnPensionBillHdr.setBillNo(lStrBillNo);
                
                if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                {
                    lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
                }
            	
                lObjTrnPensionBillHdr.setTrnPensionBillHdrId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", inputMap));
            	lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
            	
                //Inserts new CVP Bill specific data into TRN_PENSION_BILL_DTLS
            	//sets PK of TrnPensionBillDtls...
	            lObjTrnPensionBillDtls.setTrnPensionBillDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap));    	        
	            lObjTrnPensionBillDtls.setTrnPensionBillHdrId(lObjTrnPensionBillHdr.getTrnPensionBillHdrId());
	            lObjTrnPensionBillDtlsDAO.create(lObjTrnPensionBillDtls);
            	//Getting PK of RltPensioncaseBill from inputMap with the key "CVPRltPensioncaseBillId"
            	lLngRltPensioncaseBillId = Long.parseLong(inputMap.get("CVPRltPensioncaseBillId").toString()); 
    	        if(lLngRltPensioncaseBillId !=0 && lStrBillNo.length() > 0)
    	        {         
    	        	//Updates CVP Bill specific data in RLT_PENSIONCASE_BILL
	    	        lObjRltPensioncaseBill = lObjRltPensioncaseBillDAO.read(lLngRltPensioncaseBillId);
	    	        //sets updated data...
	    	        lObjRltPensioncaseBill.setBillNo(new BigDecimal(lStrBillNo));
	    	        lObjRltPensioncaseBill.setBillCntrlNo(lStrBillCntrlNo);
	    	        
                    if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                    {
                        lObjRltPensioncaseBill.setTokenNo(lPnsnTokenNo);
                    }
	                lObjRltPensioncaseBillDAO.update(lObjRltPensioncaseBill);
    	        }
    	        
                if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                {
        	        OrgTokenStatusDAOImpl lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
                	Long lLngPnsnBillNo = Long.parseLong(lStrBillNo);
                	//lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
                }
                
                inputMap.put("CVPPensionBillDtlsVO", lObjTrnPensionBillDtls);                
                inputMap.put("CVPRltPensioncaseBillVO", lObjRltPensioncaseBill);
                
                //insert bill no in recovery table
                String lStrPnsnrCode = inputMap.get("lStrPnsnrCode").toString();
                List<TrnPensionRecoveryDtls> lLstObjRecovery = lObjTrnRecoveryDtls.getTrnPensionRecoveryDtls(lStrPnsnrCode, lStrBillType);
                for(int i=0;i<lLstObjRecovery.size();i++)
                {
                	TrnPensionRecoveryDtls lObjRecovery = new TrnPensionRecoveryDtls();
                	lObjRecovery = lLstObjRecovery.get(i);
                	lObjRecovery.setBillNo(lStrBillNo);
                	lObjTrnRecoveryDtls.update(lObjRecovery);
                }
                //end of inserting bill no in recovery table
                
                objRes.setResultValue(inputMap);                
            }
            catch (Exception e)
            {
            	gLogger.error(" Error is : " + e, e);
            	objRes.setResultValue(null);
            	objRes.setThrowable(e);
            	objRes.setResultCode(ErrorConstants.ERROR);
            	objRes.setViewName("errorPage");
            }
            return objRes;
        }    	
    	/**
         * View CVP Bill data after saving the bill
         * 
         * @param inputMap
         * @return objRes ResultObject
         */
    	public ResultObject viewCVPBillData(Map inputMap)
        {            
            ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
            ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
            MstPensionerHdr lObjMstPensionerHdrVO = null;
            TrnPensionBillHdr lObjTrnPensionBillHdr = null;
            TrnPensionBillDtls lObjTrnPensionBillDtlsVO = null;
            String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
            String lStrCaseStatus = bundleConst.getString("CMN.NEW");
            String lStrBillType=bundleConst.getString("RECOVERY.CVP");
            Map lMapCVPData = new HashMap();
            String lStrPPONo = null;
            String lStrPnsnerName = null;
            String lStrPnsrCode = null;
            String lStrOfficeAddr = null;
            String lStrDesignation = null;
            String lStrTokenNo = null;
            String lStrDesgDesc = null;
            String lStrFirstname = null;
            String lStrMiddlename = "";
            String lStrLastname = null;
            Double lCVPAmt = 0.0;
            Double lReducedAmt = 0.0;
            Double lRecAmt = 0.00;
            //String lBillType = null;
            Date lStrBillDate = null;
            long lTrnPensionBillHdrPK = 0;
            
            TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
            TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, srvcLoc.getSessionFactory());
            TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
            MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory()); 
                        
            try
            {
                setSessionInfo(inputMap);
                String lStrBillNo = inputMap.get("billNo").toString();
            	if(lStrBillNo != null && lStrBillNo.length() > 0 ) 
            	{
            		// Getting the ObjectVo of  TrnPensionBillDtlsVO
            		lObjTrnPensionBillHdr = (TrnPensionBillHdr) lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo,lStrBillType);
            		lStrBillDate = lObjTrnPensionBillHdr.getBillDate();
            		lStrTokenNo = lObjTrnPensionBillHdr.getTokenNo();
            		lTrnPensionBillHdrPK = lObjTrnPensionBillHdr.getTrnPensionBillHdrId();
            		lObjTrnPensionBillDtlsVO = lObjTrnPensionBillDtlsDAO.getTrnPensionBillDtls(lTrnPensionBillHdrPK);
            	}
            	if(lObjTrnPensionBillDtlsVO != null)
            	{
            		// Getting the Value from TrnPensionBillDtls... Start...
            		lStrPnsrCode = lObjTrnPensionBillDtlsVO.getPensionerCode();
            		lReducedAmt = Double.parseDouble(lObjTrnPensionBillDtlsVO.getReducedPension().toString());
            		if(lObjTrnPensionBillDtlsVO.getRecoveryAmount() != null)
            		{
            			lRecAmt = Double.parseDouble(lObjTrnPensionBillDtlsVO.getRecoveryAmount().toString());
            		}
            	}
            	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0 ) 
            	{
            		// Getting the ObjectVo of  TrnPensionRqstHdr
            		lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPnsnerCode(lStrPnsrCode, lStrStatus, lStrCaseStatus);
        			if(lObjTrnPensionRqstHdrVO.getCvpAmount() != null)
            		{
            			lCVPAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getCvpAmount().toString());
            		}
            		if(lObjTrnPensionRqstHdrVO.getPpoNo() == null)
            		{
            			lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPnsnerCode(lStrPnsrCode, lStrStatus, "APPROVED");
            			lCVPAmt = lReducedAmt - lRecAmt;
            		}
               	}
            	if(lObjTrnPensionRqstHdrVO != null)
            	{
            		// Getting the Value from TrnPensionRqstHdr... Start...
            		if(lObjTrnPensionRqstHdrVO.getPpoNo() != null)
            		{
            			lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
            		}
            		// Getting the Value from TrnPensionRqstHdr... End...
            	}
            	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0)
            	{
            		// Getting the ObjectVo of  MstPensionerHdr... Start...
            		lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsrCode);
            		
            		lStrFirstname = lObjMstPensionerHdrVO.getFirstName(); 
            		lStrMiddlename = (lObjMstPensionerHdrVO.getMiddleName()!=null) ? lObjMstPensionerHdrVO.getMiddleName() + " " :"";
					lStrLastname = lObjMstPensionerHdrVO.getLastName();
	        		lStrPnsnerName = lStrFirstname + ' ' + lStrMiddlename + lStrLastname;
            		
            		lStrOfficeAddr = lObjMstPensionerHdrVO.getOfficeAddr();
            		
            		lStrDesignation = lObjMstPensionerHdrVO.getDesignation();
            		lStrDesgDesc = lObjTrnPensionBillDtlsDAO.getDesgDesc(lStrDesignation);
            		
    	        	//	Getting the ObjectVo of  MstPensionerHdr... End...
    	        }
            	inputMap.put("PPONo", lStrPPONo);
            	inputMap.put("PnsnBillTokenNo", lStrTokenNo);
            	inputMap.put("Name", lStrPnsnerName);
            	inputMap.put("Designation", lStrDesgDesc);
            	inputMap.put("OfficeAddr", lStrOfficeAddr);
            	inputMap.put("CVPAmount", lCVPAmt);
            	inputMap.put("RecoveryAmount", lRecAmt);
            	inputMap.put("NetAmount", lReducedAmt);
            	inputMap.put("BillDate",lStrBillDate);
            	inputMap.put("MapCVPData", lMapCVPData);  
            	inputMap.put("PnsnrCode", lStrPnsrCode);
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
            gLngPostId = SessionHelper.getPostId(inputMap);
            gLngUserId = SessionHelper.getUserId(inputMap);
            gStrLocCode = SessionHelper.getLocationCode(inputMap);
            gDtCurrDt = DBUtility.getCurrentDateFromDB();
        }
  }
