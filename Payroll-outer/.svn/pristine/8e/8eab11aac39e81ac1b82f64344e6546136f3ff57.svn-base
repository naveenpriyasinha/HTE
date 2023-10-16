package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MRCaseDAO;
import com.tcs.sgv.pension.dao.MRCaseDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAO;
import com.tcs.sgv.pension.dao.RltPensioncaseBillDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAO;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.RltPensioncaseBill;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;


public class MRCaseVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;

    /* Global Variable for Current Date */
	private Date gDtCurrDt = null;
	
	/* Global Variable for Location Code */
    String gStrLocationCode = null;	

    /* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());   
    
	private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");  
    
    /**
     * Generates VO for insertion of MR Bill Data 
     * @param Map:objArgs
     * @return ResultObject
     */    
    public ResultObject generateMap(Map objArgs)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) objArgs.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) objArgs.get("serviceLocator");
          
        try
        {	
        	setSessionInfo(objArgs);
            String lStrPPONo = StringUtility.getParameter("txtPPONo", request).trim();
            String lStrBillStatusFlag = StringUtility.getParameter("hidBillStatus", request).trim(); 
            
        	if((lStrPPONo != null) && !(lStrPPONo.equals(""))) //for case
        	{
                TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
                                            	
            	lObjTrnPensionMedRembrsmnt = generateTrnPensionMedRembrsmntCase(objArgs);  
            	
        		objArgs.put("lObjTrnPensionMedRembrsmnt", lObjTrnPensionMedRembrsmnt);
        	}
        	else if((StringUtility.getParameter("fromInwdDate", request).trim() != null) && (lStrBillStatusFlag.equals("NewMRBill")))//for bill
        	{
        		TrnPensionBillHdr lObjPensionBillHdr = generateTrnPensionBillHdrVO(objArgs);
        		objArgs.put("TrnPensionBillHdrId", lObjPensionBillHdr.getTrnPensionBillHdrId());
        		
        		List<TrnPensionBillDtls> lLstPensionBillDtls = generateTrnPensionBillDtls(objArgs);
                TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
                                            	
            	lObjTrnPensionMedRembrsmnt = generateTrnPensionMedRembrsmnt(objArgs);  
            	
        		objArgs.put("MRTrnPensionBillHdrVO", lObjPensionBillHdr);
        		objArgs.put("MRTrnPensionBillDtlsVO", lLstPensionBillDtls);
           	}
        	else if((StringUtility.getParameter("fromInwdDate", request).trim() != null) && (lStrBillStatusFlag.equals("SavedMRBill")))
        	{
        		Long lPnsnTokenNo = (Long) objArgs.get("PnsnTokenNo");
        		
                TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
                TrnPensionBillHdr lObjTrnPensionBillHdr = null;                
                
            	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,servLoc.getSessionFactory());
            	TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,servLoc.getSessionFactory());

            	String lStrPKTrnMedRembrsmnt = StringUtility.getParameter("hidMedRemId", request);            	
        		String lStrLngPk = lStrPKTrnMedRembrsmnt.trim();
        		Long lLngPk = Long.valueOf(lStrLngPk);
        		lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(lLngPk);
        		
        		Long lLngBillHdrId = lObjTrnPensionMedRembrsmnt.getBillHdrId();
        		lObjTrnPensionBillHdr = lObjTrnPensionBillHdrDAO.read(lLngBillHdrId);
        		Long lLngPnsnBillNo = Long.parseLong(lObjTrnPensionBillHdr.getBillNo());
        		
                if(lPnsnTokenNo != null && lPnsnTokenNo.toString().length() > 0) // If FP Auditor Not Enter Token No.
                {
                    lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
                }

                lObjTrnPensionBillHdrDAO.update(lObjTrnPensionBillHdr); 
            	if(lPnsnTokenNo != null)
            	{
            		OrgTokenStatusDAO lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,servLoc.getSessionFactory());
                   //	lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocationCode, lLngPnsnBillNo, gLngUserId, gLngPostId,BillProcConstants.INWARD_TYPE_BILL);
            	}
        	}
	        objRes.setResultValue(objArgs);	       
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
    private void setSessionInfo(Map inputMap)
    {
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
    }
   // FOR CASE-------------------------------------------------------------------------------------------->start
    private TrnPensionMedRembrsmnt generateTrnPensionMedRembrsmntCase(Map lMapInput) throws Exception
    {
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
        MRCaseDAO MRCaseDAO = new MRCaseDAOImpl(servLoc.getSessionFactory());
        
        String lStrFrmmon = "";
        String lStrFrmyear = "";
        String lStrTomon = "";
        String lStrToyear = "";
        
        String FromMonth = null;
        String ToMonth = null;
        String lStrPostId = "";
        String lStrFinYr = "";
        String lStrFinYrId = "";
        String lStrInwrdNo = "";
        String lStrInwdDate = "";
        String lStrRefNo = "";
        String lStrBranchName = "";
        String lStrBankCode = "";
        String lStrScheme = "";
        String lStrHeadCode = "";
        String lStrPatientName = "";
        String lStrRelation = "";
        String lStrAge = "";
        String lStrHospital = "";
        String lStrDedAmnt = "";
        String lStrPermAmnt = "";
        String lStrReason = "";
        String lStrLocCode = "";
        String lStrPPoNo = "";
        String lStrRemAmnt = "";
        String lStrGrantedAmnt = "";
        String lStrMAAmnt = "";
        String lStrPKTrnMedRembrsmnt = ""; 
        long lLngPKTrnMedRembrsmnt = 0;
        String lStrPensionername = "";
        Integer lIntStatus = 5;
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String currDate = sdf.format(gDtCurrDt);
            
        	Date current_date = sdf.parse(currDate);
        	int refNo = MRCaseDAO.getEntriesCount(current_date);
        	
        	lStrFinYr = StringUtility.getParameter("txtFinYr", request);
        	lStrFinYrId =  StringUtility.getParameter("hidFinYrId", request);
        	lStrInwrdNo =  StringUtility.getParameter("txtInwrdNo", request);
        	lStrInwdDate =  StringUtility.getParameter("txtInwrdDate", request);
        	lStrPPoNo = StringUtility.getParameter("txtPPONo", request);  
        	lStrMAAmnt = StringUtility.getParameter("txtMaAmnt", request);
        	lStrRefNo = StringUtility.getParameter("", request);
        	lStrBankCode = StringUtility.getParameter("txtBankCode", request);
        	lStrScheme = StringUtility.getParameter("txtScheme", request);
        	lStrHeadCode = StringUtility.getParameter("txtHeadCode", request);
        	
        	lStrFrmmon =StringUtility.getParameter("txtFromMM", request);
        	lStrFrmyear = StringUtility.getParameter("txtFromYYYY", request);
        	lStrTomon = StringUtility.getParameter("txtToMM", request);
        	lStrToyear = StringUtility.getParameter("txtToYYYY", request);
        	lStrRemAmnt = StringUtility.getParameter("txtReimbrseAmnt", request);
        	lStrGrantedAmnt = StringUtility.getParameter("txtGrantAmnt", request);
        	lStrPatientName = StringUtility.getParameter("txtPatientName", request);
        	lStrRelation = StringUtility.getParameter("cmbRelation", request);
        	lStrAge = StringUtility.getParameter("txtAge", request);
        	lStrHospital = StringUtility.getParameter("txtHospitalName", request);
        	lStrDedAmnt = StringUtility.getParameter("txtDedctnAmnt", request);
        	lStrPermAmnt = StringUtility.getParameter("txtPermissibleAmnt", request);
        	lStrReason = StringUtility.getParameter("txtReason", request);        	
        	lStrLocCode = gStrLocationCode;
        	lStrPKTrnMedRembrsmnt = StringUtility.getParameter("hidMedRemId", request);
        	lStrPensionername = StringUtility.getParameter("txtName", request);
        	
        	if(lStrPKTrnMedRembrsmnt != "")
        	{
        		lLngPKTrnMedRembrsmnt = Long.parseLong(lStrPKTrnMedRembrsmnt);
        	}
        	FromMonth = lStrFrmyear + lStrFrmmon;
        	ToMonth  = lStrToyear + lStrTomon;

        	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,servLoc.getSessionFactory());        
        	if(lLngPKTrnMedRembrsmnt != 0)
        	{
            	lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(lLngPKTrnMedRembrsmnt);
        	}
        	else
        	{
        		lObjTrnPensionMedRembrsmnt = new TrnPensionMedRembrsmnt();
        	}
        	
        	if(FromMonth != null && FromMonth.trim().length()>0)
        	{
        		lObjTrnPensionMedRembrsmnt.setFromMonth(Integer.parseInt(FromMonth));
        	}
        	if(ToMonth != null && ToMonth.trim().length()>0)
        	{
        		lObjTrnPensionMedRembrsmnt.setToMonth(Integer.parseInt(ToMonth));
        	}
        	if(lStrPPoNo.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setPpoNo(lStrPPoNo);
        	}
        	if(lStrMAAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setMaAmnt(new BigDecimal(lStrMAAmnt));
        	}
        	if(lStrGrantedAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setGrantAmnt(new BigDecimal(lStrGrantedAmnt));
        	}
        	if(lStrRemAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setRemAmnt(new BigDecimal(lStrRemAmnt));
        	}
        	if(lStrFinYrId.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setFinYearId(Integer.parseInt(lStrFinYrId));
        	}
        	if(lStrInwrdNo.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setInwdNo(lStrInwrdNo);
        	}
        	if(lStrInwdDate.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setInwdDate(lObjSmplDtFmt.parse(lStrInwdDate));
        	}
        	if(lStrRefNo.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setRefNum(new BigDecimal(lStrRefNo));
        	}
        	if(lStrBankCode.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setBranchCode(lStrBankCode);
        	}
        	if(lStrScheme.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setScheme(lStrScheme);
        	}
        	if(lStrHeadCode.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setHeadCode(new BigDecimal(lStrHeadCode));
        	}
        	if(lStrPatientName.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setPatientName(lStrPatientName);
        	}
        	if(lStrRelation.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setRelationship(lStrRelation);
        	}
        	if(lStrAge.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setAge(Short.parseShort(lStrAge));
        	}
        	if(lStrHospital.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setHospitalName(lStrHospital);
        	}
        	if(lStrDedAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setDeductionAmnt(new BigDecimal(lStrDedAmnt));
        	}
        	if(lStrPermAmnt.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setPermissibleAmnt(new BigDecimal(lStrPermAmnt));
        	}
        	if(lStrReason.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setReason(lStrReason);
        	}
        	if(lStrLocCode.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setLocationCode(lStrLocCode);
        	}
        	if(lStrInwrdNo.length() > 0)
        	{
        		lObjTrnPensionMedRembrsmnt.setPensionerName(lStrPensionername);
        	}  
        	BigDecimal BDAuditorPostId = MRCaseDAO.getAuditorPostId(lStrBankCode,lStrScheme);
        	if(BDAuditorPostId != null)
        	{
        		lObjTrnPensionMedRembrsmnt.setAuditorPostId(BDAuditorPostId);
        	}    
        	        	
        	if(lObjTrnPensionMedRembrsmnt.getMedRembrsmntId() != null )
        	{        		
        		lObjTrnPensionMedRembrsmnt.setUpdatedDate(gDtCurrDt);
	        	lObjTrnPensionMedRembrsmnt.setUpdatedPostId(new BigDecimal(gLngPostId));
	        	lObjTrnPensionMedRembrsmnt.setUpdatedUserId(new BigDecimal(gLngUserId));
        	}
        	else
        	{
        		//set status for new case inwarded
        		lObjTrnPensionMedRembrsmnt.setStatus(5);
        		
	        	lObjTrnPensionMedRembrsmnt.setCreatedDate(gDtCurrDt);
	        	lObjTrnPensionMedRembrsmnt.setCreatedPostId(new BigDecimal(gLngPostId));
	        	lObjTrnPensionMedRembrsmnt.setCreatedUserId(new BigDecimal(gLngUserId));
	        	if(refNo == 0)
	        	{
	        		lObjTrnPensionMedRembrsmnt.setRefNum(new BigDecimal(1));
	        	}
	        	else
	        	{
	        		refNo = refNo + 1;
	        		lObjTrnPensionMedRembrsmnt.setRefNum(new BigDecimal(refNo));
	        	}
        	}
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lObjTrnPensionMedRembrsmnt;
    }
    
    //  FOR CASE-------------------------------------------------------------------------------------------->end
    
    //FOR BILL-------------------------------------------------------------------------------------------->start
    private TrnPensionBillHdr generateTrnPensionBillHdrVO(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        TrnPensionBillHdr lObjPensionBillHdr =new TrnPensionBillHdr();          
        String lStrTokenNo = null;
        
        try
        { 
    		String lStrBillType="MR";
    		
     	    lObjPensionBillHdr.setTrnPensionBillHdrId(new Long(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", lMapInput)));
            //lObjPensionBillHdr.setBillType(lStrBillType);

            lObjPensionBillHdr.setBillDate(gDtCurrDt);
            lObjPensionBillHdr.setCreatedUserId(new BigDecimal(gLngUserId));
            lObjPensionBillHdr.setCreatedPostId(new BigDecimal(gLngPostId));
            lObjPensionBillHdr.setCreatedDate(gDtCurrDt);
           	if(StringUtility.getParameter("hidBankCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setBankCode(StringUtility.getParameter("hidBankCode", request).trim());
        	}
           	if(StringUtility.getParameter("hidBranchCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setBranchCode(StringUtility.getParameter("hidBranchCode", request).trim());
        	}
        	if(StringUtility.getParameter("hidHeadCode", request).trim() != "")
        	{
        		lObjPensionBillHdr.setHeadCode(new BigDecimal(StringUtility.getParameter("hidHeadCode", request).trim()));
        	}
        	
        	lObjPensionBillHdr.setForMonth(Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gDtCurrDt)));
        	if(StringUtility.getParameter("txtPnsnTokenNo", request).trim() != "")
        	{
        		lObjPensionBillHdr.setTokenNo(StringUtility.getParameter("txtPnsnTokenNo", request).trim());
        	}
        }
        catch (Exception e)
        {
        	gLogger.error("Error is : " + e,e); 
        	throw(e);
        }
        return lObjPensionBillHdr;
    }

    private List<TrnPensionBillDtls> generateTrnPensionBillDtls(Map lMapInput) throws Exception
    {
        List<TrnPensionBillDtls> lLstTrnPensionBillDtls = new ArrayList<TrnPensionBillDtls>();
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants"); 
        MstPensionerDtlsDAOImpl mstPnsnrDtlsDAOimpl = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class, servLoc
                .getSessionFactory());
        
        String[] lStrPPONo = null;
        String[] lStrPnsnrName = null;
        String lStrAccNo = null;
        String[] lStrNetAmt = null;
                
        TrnPensionBillDtls lObjPensionBillDtls = null;
        
        String lTrnPensionBillHdrId = lMapInput.get("TrnPensionBillHdrId").toString();
        String lStrStatus = "APPROVED";
        
        try
        {
        	List lLstPensionCodeId=null; 
        	
        	lStrPPONo = StringUtility.getParameterValues("hidppoNo", request);
        	lStrPnsnrName = StringUtility.getParameterValues("hidpensionerName", request);
        	lStrNetAmt = StringUtility.getParameterValues("hidgrantAmount", request); 
        	
            String lStrPnsnrCode = null;
            Object[] lObjData = null;
            for (Integer x = 0; x < (lStrPPONo.length) ; x++)
            {
            	lObjPensionBillDtls = new TrnPensionBillDtls();
            	
            	TrnPensionRqstHdrDAO lObjPensionBillDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class, servLoc.getSessionFactory());
            	lLstPensionCodeId = lObjPensionBillDAO.getPksForTrnPensionRqstHdr(lStrStatus,lStrPPONo[x].trim());
 	            
 	            if(lLstPensionCodeId != null && !lLstPensionCodeId.isEmpty())
 	            {
 	            	lObjData = (Object[]) lLstPensionCodeId.get(0);	            	
 	            	lStrPnsnrCode = lObjData[1].toString();	  
 	            	lStrAccNo = mstPnsnrDtlsDAOimpl.getACCNo(lStrPnsnrCode);
 	            }
            	
 	           //lObjPensionBillDtls.setPensionRequestId(new Long(lLngPensionReqId));
 	           lObjPensionBillDtls.setPpoNo(lStrPPONo[x].trim());
 	           lObjPensionBillDtls.setPensionerCode(lStrPnsnrCode);
 	           lObjPensionBillDtls.setReducedPension(new BigDecimal(lStrNetAmt[x].trim()));
 	           lObjPensionBillDtls.setAccountNo(lStrAccNo.trim());
 	           lObjPensionBillDtls.setTrnPensionBillHdrId(new Long(lTrnPensionBillHdrId));
 	           lObjPensionBillDtls.setPensionerName(lStrPnsnrName[x].trim());
               lObjPensionBillDtls.setTrnCounter(1);
               
	           lLstTrnPensionBillDtls.add(lObjPensionBillDtls);       	
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }
        return lLstTrnPensionBillDtls;
    }
    private TrnPensionMedRembrsmnt generateTrnPensionMedRembrsmnt(Map lMapInput) throws Exception
    {
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
    	TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,servLoc.getSessionFactory());

        String lTrnPensionBillHdrId = lMapInput.get("TrnPensionBillHdrId").toString();
        Long lLngPk = 0L;
        try
        {
        	String[] lStrPKTrnMedRembrsmnt = StringUtility.getParameterValues("hidMedRemId", request);
        	for(int i=0;i<lStrPKTrnMedRembrsmnt.length;i++)
        	{
        		String lStrLngPk = lStrPKTrnMedRembrsmnt[i].trim();
        		lLngPk = Long.valueOf(lStrLngPk);
        		lObjTrnPensionMedRembrsmnt = lObjTrnPensionMedRembrsmntDAO.read(lLngPk);
        		lObjTrnPensionMedRembrsmnt.setBillHdrId(Long.parseLong(lTrnPensionBillHdrId));
        		lObjTrnPensionMedRembrsmntDAO.update(lObjTrnPensionMedRembrsmnt);
        	}
        	
        }
        catch (Exception e)
        {
            gLogger.error("Error is : " + e, e);
            throw (e);
        }
        return lObjTrnPensionMedRembrsmnt;
    }
//  FOR BILL-------------------------------------------------------------------------------------------->end

}
