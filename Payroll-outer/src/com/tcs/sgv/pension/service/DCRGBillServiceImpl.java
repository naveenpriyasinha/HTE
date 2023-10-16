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
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAO;
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
 * DCRG Bill Specific Service Impl.
 * 
 * @author Aparna Kansara
 * @version 1.0
 */
    public class DCRGBillServiceImpl extends ServiceImpl implements DCRGBillService {

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
         * Get DCRG Bill data for displaying in bill
         * 
         * @param inputMap
         * @return objRes ResultObject
         */
    	public ResultObject getDCRGBillData(Map inputMap)
        {         
            ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
            ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            HttpServletRequest request = null;
            TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
            MstPensionerHdr lObjMstPensionerHdrVO = null;
            TrnPensionRecoveryDtls lObjTrnPensionRecoveryDtls = null;
            List lLstObjTrnPensionRecoveryDtls = null;
            String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
            String lStrCaseStatus = bundleConst.getString("CMN.NEW");
            String lStrRcvryFlag = bundleConst.getString("RECOVERY.DCRG");
            Map lMapDCRGData = new HashMap();
            String lStrPPONo = null;
            String lStrPnsnerName = null;
            String lStrPnsrCode = null;
            String lStrOfficeAddr = null;
            String lStrDesignation = null;
            String lStrDesgDesc = null;
            String lStrFirstname = null;
            String lStrMiddlename = "";
            String lStrLastname = null;
            Double lDCRGAmt = 0.0;
            Double lNetAmt = 0.0;
            Double lRecAmt = 0.0;
            Double lTemp = 0.0;
            String[] lRecoveryAmt = new String[10];
            long lIntNetAmt = 0L;
            int lIntListSize = 0;
            String lHeadcode = null;
            String lScheme = null;
            
            TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
            MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory()); 
            TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class,srvcLoc.getSessionFactory());
            TrnPensionRecoveryDtlsDAO lObjTrnPensionRecoveryDtlsDAO = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class,srvcLoc.getSessionFactory());
            try
            {
                setSessionInfo(inputMap);
                request = (HttpServletRequest) inputMap.get("requestObj");
            	lStrPPONo = inputMap.get("PPONo").toString();
            	if(lStrPPONo != null && lStrPPONo.length() > 0 ) 
            	{
            		// Getting the ObjectVo of  TrnPensionRqstHdr
            		lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPPONo(lStrPPONo, lStrStatus, lStrCaseStatus);
            	}
            	if(lObjTrnPensionRqstHdrVO != null)
            	{
            		// Getting the Value from TrnPensionRqstHdr... Start...
            		
            		if(request.getParameter("auditFlag") != null && "Y".equalsIgnoreCase(request.getParameter("auditFlag").toString()))
            		{
            			lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPPONo(lStrPPONo, "Continue", "APPROVED");
            			if(request.getParameter("DCRGAmt") != null && request.getParameter("DCRGAmt").length() > 0)
            			{
            				lDCRGAmt = Double.parseDouble(request.getParameter("DCRGAmt").toString());
            			}
            		}
            		else
            		{
            			lDCRGAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getDcrgAmount().toString());
            		}
            		lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
            		lHeadcode = lObjTrnPensionRqstHdrVO.getHeadCode().toString();
            		lScheme = lObjTrnPensionRqstHdrVO.getSchemeType();
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
    	        	//	Getting the ObjectVo of  TrnPensionRecoveryDtls... Start...
            		lLstObjTrnPensionRecoveryDtls = lObjTrnPensionRecoveryDtlsDAO.getTrnPensionRecoveryDtls(lStrPnsrCode, lStrRcvryFlag);
            		lIntListSize = lLstObjTrnPensionRecoveryDtls.size();
            		for(int i=0;i<lLstObjTrnPensionRecoveryDtls.size();i++)
	        		{	
            			lObjTrnPensionRecoveryDtls = (TrnPensionRecoveryDtls) lLstObjTrnPensionRecoveryDtls.get(i);
	        			if(lObjTrnPensionRecoveryDtls != null)
	        			{
	        				lTemp = Double.parseDouble((lObjTrnPensionRecoveryDtls.getAmount()).toString());
	        				if(lTemp==null)
	        				{
	        					lRecoveryAmt[i]= "0";
	        				}
	        				else
	        				{
	        					lRecoveryAmt[i] = lTemp.toString();
	        				}
	        			}
	        		}
            		// Getting the ObjectVo of  TrnPensionRecoveryDtls... End...
            	}
            	// Recovery Amount and Net Amount Calculation.
            	lNetAmt = lDCRGAmt;
            	lRecAmt = 0.0;
            	for(int i=0;i<lIntListSize;i++)
            	{
            		lRecAmt = lRecAmt + Double.parseDouble(lRecoveryAmt[i]);
            		lNetAmt = lNetAmt - Double.parseDouble(lRecoveryAmt[i]);
            	}
                lIntNetAmt = Math.round(lNetAmt);
            	inputMap.put("PPONo", lStrPPONo);
            	inputMap.put("Name", lStrPnsnerName);
            	inputMap.put("Designation", lStrDesgDesc);
            	inputMap.put("OfficeAddr", lStrOfficeAddr);
            	inputMap.put("DCRGAmount", lDCRGAmt);
            	inputMap.put("RecoveryAmount", lRecAmt);
            	inputMap.put("NetAmount", lIntNetAmt);
            	inputMap.put("BillDate",gDtCurrDt);
            	inputMap.put("MapDCRGData", lMapDCRGData);
            	inputMap.put("PnsnrCode", lStrPnsrCode);
            	inputMap.put("Headcode", lHeadcode);
            	inputMap.put("Scheme", lScheme);
            	resObj.setResultValue(inputMap);
            }
            catch (Exception e)
            {
                gLogger.error("Error is : " + e, e);
                resObj.setThrowable(e);
                resObj.setResultValue(null);
                resObj.setResultCode(ErrorConstants.ERROR);
                resObj.setViewName("errorPage");
            }
    		return resObj;
        }
    
        /**
         * Saves DCRG specific Bill Data in  TRN_PENSION_BILL_DTLS and RLT_PENSIONCASE_BILL
         * 
         * @param Map:lMapInput
         * @return ResultObject
         */    	
    	public ResultObject saveDCRGBill(Map inputMap)
        {
            ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
            TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class, serv.getSessionFactory());
            TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());
            RltPensioncaseBillDAO lObjRltPensioncaseBillDAO = new RltPensioncaseBillDAOImpl(RltPensioncaseBill.class,serv.getSessionFactory());
            TrnPensionRecoveryDtlsDAO lObjTrnRecoveryDtls = new TrnPensionRecoveryDtlsDAOImpl(TrnPensionRecoveryDtls.class,serv.getSessionFactory());
            TrnPensionBillHdr lObjTrnPensionBillHdr = null;
            TrnPensionBillDtls lObjTrnPensionBillDtls = null;
            RltPensioncaseBill lObjRltPensioncaseBill = null;
            Long lLngRltPensioncaseBillId=null;
            String lStrBillNo = inputMap.get("billNo").toString();   
            String lStrBillCntrlNo = (String) inputMap.get("PnsnBillCntrlNo");
            Long lPnsnTokenNo = (Long) inputMap.get("PnsnTokenNo");
            String lStrBillType=bundleConst.getString("RECOVERY.DCRG");
            try
            {  
            	setSessionInfo(inputMap);
            	
            	//Getting VO Object from inputMap with the key "DCRGTrnPensionBillDtlsVO"
            	lObjTrnPensionBillHdr = (TrnPensionBillHdr) inputMap.get("DCRGTrnPensionBillHdrVO");
            	lObjTrnPensionBillDtls = (TrnPensionBillDtls) inputMap.get("DCRGTrnPensionBillDtlsVO");
            	if(lStrBillNo != null && lStrBillNo.length() > 0)
            	{
            		// insert Data into TrnPensionBillHdr.
            		lObjTrnPensionBillHdr.setBillType(lStrBillType);
            		lObjTrnPensionBillHdr.setBillNo(lStrBillNo);
            		
                    if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                    {
                        lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
                    }                    
            		lObjTrnPensionBillHdr.setTrnPensionBillHdrId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", inputMap)));
            		lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
	            	//Inserts new DCRG Bill specific data into TRN_PENSION_BILL_DTLS
            		//sets PK of TrnPensionBillDtls...
	            	lObjTrnPensionBillDtls.setTrnPensionBillDtlsId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap)));    	        
	            	lObjTrnPensionBillDtls.setTrnPensionBillHdrId(lObjTrnPensionBillHdr.getTrnPensionBillHdrId());
	            	lObjTrnPensionBillDtlsDAO.create(lObjTrnPensionBillDtls);
            	}
            	//Getting PK of RltPensioncaseBill from inputMap with the key "DCRGRltPensioncaseBillId"
            	lLngRltPensioncaseBillId = (Long) inputMap.get("DCRGRltPensioncaseBillId");    	        
    	        
    	        if(lLngRltPensioncaseBillId !=null && lStrBillNo.length() > 0)
    	        {         
    	        	//Updates DCRG Bill specific data in RLT_PENSIONCASE_BILL
	    	        lObjRltPensioncaseBill = lObjRltPensioncaseBillDAO.read(lLngRltPensioncaseBillId);
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
        	        OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
                	Long lLngPnsnBillNo = Long.parseLong(lStrBillNo);
                	//lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
                }
                
                inputMap.put("DCRGPensionBillDtlsVO", lObjTrnPensionBillDtls);                
                inputMap.put("DCRGRltPensioncaseBillVO", lObjRltPensioncaseBill);
                
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
                gLogger.error("Error is : " + e, e);
                objRes.setThrowable(e);
                objRes.setResultValue(null);
                objRes.setResultCode(ErrorConstants.ERROR);
                objRes.setViewName("errorPage");
            }
            return objRes;
        }
    	/**
         * View DCRG Bill data after saving the bill
         * 
         * @param inputMap
         * @return objRes ResultObject
         */
    	public ResultObject viewDCRGBillData(Map inputMap)
        {            
            ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
            ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
            TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
            MstPensionerHdr lObjMstPensionerHdrVO = null;
            TrnPensionBillHdr lObjTrnPensionBillHdr = null;
            TrnPensionBillDtls lObjTrnPensionBillDtlsVO = null;
            String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
            String lStrCaseStatus = bundleConst.getString("CMN.NEW");
            String lStrRcvryFlag = bundleConst.getString("RECOVERY.DCRG");
            Map lMapDCRGData = new HashMap();
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
            Double lDCRGAmt = 0.0;
            Double lReducedAmt = 0.0;
            Double lRecAmt = 0.00;
            Date lStrBillDate = null;
            Long lTrnPensionBillHdrPK = null;
            
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
            		lObjTrnPensionBillHdr = (TrnPensionBillHdr) lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo,lStrRcvryFlag);
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
            		// Getting the Value from TrnPensionBillDtls... End...
            	}
            	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0 ) 
            	{
            		// Getting the ObjectVo of  TrnPensionRqstHdr
            		lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPnsnerCode(lStrPnsrCode, lStrStatus, lStrCaseStatus);
            		if(lObjTrnPensionRqstHdrVO != null)
                	{
	            		if(lObjTrnPensionRqstHdrVO.getDcrgAmount() != null)
	            		{
	            			lDCRGAmt = Double.parseDouble(lObjTrnPensionRqstHdrVO.getDcrgAmount().toString());
	            		}
                	}
            		if(lObjTrnPensionRqstHdrVO.getPpoNo() == null)
            		{
            			lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrDtlsFromPnsnerCode(lStrPnsrCode, lStrStatus, "APPROVED");
            			lDCRGAmt = lReducedAmt - lRecAmt;
            		}
               	}
            	if(lObjTrnPensionRqstHdrVO != null)
            	{
            		// Getting the Value from TrnPensionRqstHdr... Start...
            		lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
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
            	inputMap.put("DCRGAmount", lDCRGAmt);
            	inputMap.put("RecoveryAmount", lRecAmt);
            	inputMap.put("NetAmount", lReducedAmt);
            	inputMap.put("BillDate",lStrBillDate);
            	inputMap.put("MapDCRGData", lMapDCRGData);
            	inputMap.put("PnsnrCode", lStrPnsrCode);
            	resObj.setResultValue(inputMap);
            }
            catch (Exception e)
            {
                gLogger.error("Error is : " + e, e);
                resObj.setThrowable(e);
                resObj.setResultValue(null);
                resObj.setResultCode(ErrorConstants.ERROR);
                resObj.setViewName("errorPage");
            }
           return resObj;
        }

    	private void setSessionInfo(Map inputMap) throws Exception
    	{
    		try{
    	        gLngPostId = SessionHelper.getPostId(inputMap);
    	        gLngUserId = SessionHelper.getUserId(inputMap);
    	        gStrLocCode = SessionHelper.getLocationCode(inputMap);
    	        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    		}
    		catch(Exception e){
    			gLogger.error("Error is : " + e, e);
    			throw(e);
    		}
	    	
	    }
}    