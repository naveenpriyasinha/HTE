package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.RltBankBranch;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAO;
import com.tcs.sgv.pension.dao.MstPensionerDtlsDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAO;
import com.tcs.sgv.pension.dao.TrnPensionMedRembrsmntDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerDtls;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionBillDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionMedRembrsmnt;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;


/**
 * Medical Reimbursement Implementation.
 * 
 * @author Rupsa Mukherjee
 * @version 1.0
 */

public class MedReimburseServiceImpl extends ServiceImpl implements MedReimburseService
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

    // For AJAX use for getting pensioner info for MR...

    public ResultObject getPensionerMRInfo(Map inputMap)
    {

        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,servLoc.getSessionFactory());
        
        List lLstMRDetails = null;
        
        try
        {
        	String lStrName = null;
        	String lStrScheme = null;
        	String lStrDesgnation = null;
        	String lStrOfficeAddress = null;
        	String lStrPensionerAddress = null;
        	String lStrMjrHead = null;
        	String lStrSubMjrHead = null;
        	String lStrMinorHead = null;
        	String lStrSubHead = null;
        	String lStrDetailHead = null;
        	String lStrDemandNo = null;
        	String lStrHeadChargeable = null;
        	BigDecimal BDAmount = new BigDecimal(0);
			String lStrSchemeNo = "000000";    	
			String lStrEdpCode = null;
			String lStrEdpDesc = null;
			String lStrBudCode = null;
			String lStrAcnNo = null;
        	long lLngDesgnationId = 0;
        	String lStrBankCode = null;
        	String lStrBranchCode = null;
        	String lStrBranchName = null;
        	BigDecimal BDHeadCode = null;
        	
            setSessionInfo(inputMap);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currDate = sdf.format(gDtCurrDt);
           
            String lStrPPONO = request.getParameter("PPONO");
            String lStrCaseStatus = request.getParameter("CASESTATUS");
            //lLstMRDetails  = lObjMstPensionerHdrDAO.getMRRelatedInfo(lStrPPONO,lStrCaseStatus);
            
            if (lLstMRDetails!=null && !lLstMRDetails.isEmpty()) 
			{	
				Iterator it = lLstMRDetails.iterator();
				while(it.hasNext()) 
				{
					Object[] tuple = (Object[])it.next();
					if( tuple[2] != null)
					{
						lStrName = tuple[2].toString();
					}
					else
					{
						lStrName = "";
					}
					if( tuple[0] != null)
					{
						lStrScheme = tuple[0].toString();
					}
					else
					{
						lStrScheme = "";
					}
					if( tuple[1] != null)
					{
						BDAmount = new BigDecimal(tuple[1].toString()).setScale(2,BigDecimal.ROUND_HALF_UP);
					}
					else
					{
						BDAmount = new BigDecimal(0);
					}
					if( tuple[3] != null)
					{
						lLngDesgnationId = Long.parseLong(tuple[3].toString());
						lStrDesgnation = lObjMstPensionerHdrDAO.getDesigName(lLngDesgnationId);
					}
					else
					{
						lStrDesgnation = "";
					}
					if( tuple[4] != null)
					{
						lStrOfficeAddress = tuple[4].toString();
					}
					else
					{
						lStrOfficeAddress = "";
					}
					if( tuple[5] != null)
					{
						lStrPensionerAddress = tuple[5].toString();
					}
					else
					{
						lStrPensionerAddress = "";
					}
					if( tuple[6] != null)
					{
						lStrDemandNo = tuple[6].toString();
					}
					else
					{
						lStrDemandNo = "";
					}
					if( tuple[7] != null)
					{
						lStrMjrHead = tuple[7].toString();
					}
					else
					{
						lStrMjrHead = "";
					}
					if( tuple[8] != null)
					{
						lStrSubMjrHead = tuple[8].toString();
					}
					else
					{
						lStrSubMjrHead = "";
					}
					if( tuple[9] != null)
					{
						lStrMinorHead = tuple[9].toString();
					}
					else
					{
						lStrMinorHead = "";
					}
					if( tuple[10] != null)
					{
						lStrSubHead = tuple[10].toString();
					}
					else
					{
						lStrSubHead = "";
					}
					if( tuple[11] != null)
					{
						lStrDetailHead = tuple[11].toString();
					}
					else
					{
						lStrDetailHead = "";
					}
					if( tuple[12] != null)
					{
						lStrHeadChargeable = tuple[12].toString();
					}
					else
					{
						lStrHeadChargeable = "";
					}
					if( tuple[13] != null)
					{
						lStrAcnNo = tuple[13].toString();
					}
					else
					{
						lStrAcnNo = "";
					}		
					if( tuple[14] != null)
					{
						lStrEdpCode = tuple[14].toString();
					}
					else
					{
						lStrEdpCode = "";
					}
					if( tuple[15] != null)
					{
						lStrEdpDesc = tuple[15].toString();
					}
					else
					{
						lStrEdpDesc = "";
					}
					if( tuple[16] != null)
					{
						lStrBudCode = tuple[16].toString();
					}
					else
					{
						lStrBudCode = "";
					}
					lStrBankCode = tuple[17].toString();
					lStrBranchCode = tuple[18].toString();
					BDHeadCode = new BigDecimal(tuple[19].toString());
					lStrBranchName = tuple[20].toString();
				}	
			}
            
            StringBuilder lStrGrant = new StringBuilder();
            
            if (lLstMRDetails != null)
            {
                lStrGrant.append(" <PENSIONERMRINFO> ");
                lStrGrant.append(" 		<NAME> ");
                lStrGrant.append(lStrName);
                lStrGrant.append(" 		</NAME> ");
                lStrGrant.append(" 		<SCHEME> ");
                lStrGrant.append(lStrScheme);
                lStrGrant.append(" 		</SCHEME> ");
                lStrGrant.append(" 		<MAAMOUNT> ");
                lStrGrant.append(BDAmount);
                lStrGrant.append(" 		</MAAMOUNT> ");
                lStrGrant.append(" 		<DATE> ");
                lStrGrant.append(currDate);
                lStrGrant.append(" 		</DATE> ");
                lStrGrant.append(" 		<DESIGNATION> ");
                lStrGrant.append(lStrDesgnation);
                lStrGrant.append(" 		</DESIGNATION> ");
                lStrGrant.append(" 		<OFFICEADDRESS> ");
                lStrGrant.append(lStrOfficeAddress);
                lStrGrant.append(" 		</OFFICEADDRESS> ");
                lStrGrant.append(" 		<PENSIONERADDRESS> ");
                lStrGrant.append(lStrPensionerAddress);
                lStrGrant.append(" 		</PENSIONERADDRESS> ");
                lStrGrant.append(" 		<DEMANDNO> ");
                lStrGrant.append(lStrDemandNo);
                lStrGrant.append(" 		</DEMANDNO> ");
                lStrGrant.append(" 		<MJRHDCODE> ");
                lStrGrant.append(lStrMjrHead);
                lStrGrant.append(" 		</MJRHDCODE> ");
                lStrGrant.append(" 		<SUBMJRHDCODE> ");
                lStrGrant.append(lStrSubMjrHead);
                lStrGrant.append(" 		</SUBMJRHDCODE> ");
                lStrGrant.append(" 		<MINORHDCODE> ");
                lStrGrant.append(lStrMinorHead);
                lStrGrant.append(" 		</MINORHDCODE> ");
                lStrGrant.append(" 		<SUBHDCODE> ");
                lStrGrant.append(lStrSubHead);
                lStrGrant.append(" 		</SUBHDCODE> ");
                lStrGrant.append(" 		<DTLHDCODE> ");
                lStrGrant.append(lStrDetailHead);
                lStrGrant.append(" 		</DTLHDCODE> ");
                lStrGrant.append(" 		<HEADCODECHARGEABLE> ");
                lStrGrant.append(lStrHeadChargeable);
                lStrGrant.append(" 		</HEADCODECHARGEABLE> ");
                lStrGrant.append(" 		<SCHEMENO> ");
                lStrGrant.append(lStrSchemeNo);
                lStrGrant.append(" 		</SCHEMENO> ");
                lStrGrant.append(" 		<ACNNO> ");
                lStrGrant.append(lStrAcnNo);
                lStrGrant.append(" 		</ACNNO> ");
                lStrGrant.append(" 		<EDPCODE> ");
                lStrGrant.append(lStrEdpCode);
                lStrGrant.append(" 		</EDPCODE> ");
                lStrGrant.append(" 		<EDPDESC> ");
                lStrGrant.append(lStrEdpDesc);
                lStrGrant.append(" 		</EDPDESC> ");
                lStrGrant.append(" 		<BUDCODE> ");
                lStrGrant.append(lStrBudCode);
                lStrGrant.append(" 		</BUDCODE> ");
                lStrGrant.append(" 		<BANKCODE> ");
                lStrGrant.append(lStrBankCode);
                lStrGrant.append(" 		</BANKCODE> ");
                lStrGrant.append(" 		<BRANCHCODE> ");
                lStrGrant.append(lStrBranchCode);
                lStrGrant.append(" 		</BRANCHCODE> ");
                lStrGrant.append(" 		<HEADCODE> ");
                lStrGrant.append(BDHeadCode);
                lStrGrant.append(" 		</HEADCODE> ");
                lStrGrant.append(" 		<BRANCHNAME> ");
                lStrGrant.append(lStrBranchName);
                lStrGrant.append(" 		</BRANCHNAME> ");
                lStrGrant.append(" </PENSIONERMRINFO> ");
            }

            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
           // inputMap.put("CurrentDate", currDate);
            resObj.setViewName("ajaxData");
            
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
 //==========================================================================================================
    /* *//**
     * Shows Medical Reimbrsmnt Bill Data after saving bill
     * @param Map:lMapInput
     * @return ResultObject
     */

	public ResultObject viewMRBillData(Map inputMap)
    {            
        ServiceLocator srvcLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        MstPensionerHdr lObjMstPensionerHdrVO = null;
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmntVO = null;
        
        String lStrPPONo = null;
        String lStrPnsnerName = null;
        String lStrPnsrCode = null;
        String lStrOfficeAddr = null;
        String lStrDesignation = null;
        String lStrTokenNo = null;
        String lStrScheme = null;
        String lStrNamePrefix = null;
        String lStrFirstname = null;
        String lStrMiddlename = "";
        String lStrLastname = null;
        String lStrDesigName = null;
        String frmmonth = null;
        String frmyear = null;
        String tomonth = null;
        String toyear = null;
        BigDecimal BDMAAmnt = null;
        BigDecimal BDReimbrseAmnt = null;
        BigDecimal BDGrantedAmnt = null;
        Integer frommon = 0;
        Integer tomon = 0;
        //String lBillType = null;
        Date lStrBillDate = null;
        long lTrnPensionBillHdrPK = 0;
        long lLngMedId = 0;
        
        String lStrbankCode = null;
        String lStrbranchCode = null;
        String lStrAcnNo = null;
        String lStrBranchName = null;
        BigDecimal lBDheadcode = null;
        int lIntformonth = 0;
        
        String lStrBillType = "MR";
        String lStrCaseStatus ="APPROVED";
        
        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,srvcLoc.getSessionFactory());
        TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,srvcLoc.getSessionFactory());
        MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,srvcLoc.getSessionFactory()); 
        TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,srvcLoc.getSessionFactory());     
        MstPensionerDtlsDAO lObjMstPensionerDtlsDAO = new MstPensionerDtlsDAOImpl(MstPensionerDtls.class,srvcLoc.getSessionFactory());
        
        try
        {
            setSessionInfo(inputMap);
            String lStrBillNo = inputMap.get("billNo").toString();
        	if(lStrBillNo != null && lStrBillNo.length() > 0 ) 
        	{
        		// Getting the ObjectVo of  TrnPensionBillHdrVO
        		lObjTrnPensionBillHdr = (TrnPensionBillHdr) lObjTrnPensionBillHdrDAO.getTrnPensionBillHdr(lStrBillNo,lStrBillType);
        		lStrBillDate = lObjTrnPensionBillHdr.getBillDate();
        		//lBillType = lObjTrnPensionBillHdr.getBillType();
        		lStrTokenNo = lObjTrnPensionBillHdr.getTokenNo();
        		
        		if(lObjTrnPensionBillHdr.getBankCode() != null)
        		{
        			lStrbankCode = lObjTrnPensionBillHdr.getBankCode();
        		}
        		else
        		{
        			lStrbankCode = "";
        		}
        		if(lObjTrnPensionBillHdr.getBranchCode() != null)
        		{
        			lStrbranchCode = lObjTrnPensionBillHdr.getBranchCode();
        		}
        		else
        		{
        			lStrbranchCode = "";
        		}
        		if(lObjTrnPensionBillHdr.getHeadCode() != null)
        		{
        			lBDheadcode = lObjTrnPensionBillHdr.getHeadCode();
        		}
        		else
        		{
        			lBDheadcode = new BigDecimal(0);
        		}
        		if(lObjTrnPensionBillHdr.getForMonth() != null)
        		{
        			lIntformonth = lObjTrnPensionBillHdr.getForMonth();
        		}
        		else
        		{
        			lIntformonth = 0;
        		}
        		
        		lTrnPensionBillHdrPK = lObjTrnPensionBillHdr.getTrnPensionBillHdrId();
        		
        		lObjTrnPensionMedRembrsmntVO = lObjTrnPensionMedRembrsmntDAO.getTrnPensionMedRembrsmntVO(lTrnPensionBillHdrPK);
        		
        	}
        	if(lObjTrnPensionMedRembrsmntVO != null)
        	{
        		// Getting the PPO No from TrnPensionMedRembrsmnt start...
        		lLngMedId = lObjTrnPensionMedRembrsmntVO.getMedRembrsmntId();
        		lStrPPONo = lObjTrnPensionMedRembrsmntVO.getPpoNo();
        		
        		BDReimbrseAmnt = lObjTrnPensionMedRembrsmntVO.getRemAmnt();
        		BDGrantedAmnt = lObjTrnPensionMedRembrsmntVO.getGrantAmnt();
        		BDMAAmnt = lObjTrnPensionMedRembrsmntVO.getMaAmnt();
        		//frommon = lObjTrnPensionMedRembrsmntVO.getFrmmonth();
        		//tomon = lObjTrnPensionMedRembrsmntVO.getTomonth();
        		
        		if(frommon != null)
        		{
        			frmmonth = frommon.toString().substring(4,6);
        		}
        		else
        		{
        			frmmonth = "";
        		}
        		if(frommon != null)
        		{
        			frmyear = frommon.toString().substring(0,4);
        		}
        		else
        		{
        			frmyear = "";
        		}
        		if(tomon != null)
        		{
        			tomonth = tomon.toString().substring(4,6);
        		}
        		else
        		{
        			tomonth = "";
        		}
        		if(tomon != null)
        		{
        			toyear = tomon.toString().substring(0, 4);
        		}
        		else
        		{
        			toyear = "";
        		}
//        		 Getting the PPO No from TrnPensionMedRembrsmnt end
        	}
        	if(lStrPPONo != null && lStrPPONo.length() > 0 ) 
        	{
        		// Getting the ObjectVo of  TrnPensionRqstHdr
        		lObjTrnPensionRqstHdrVO = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrVOforApproved(lStrPPONo,lStrCaseStatus);
           	}
        	if(lObjTrnPensionRqstHdrVO != null)
        	{
        		// Getting the Value from TrnPensionRqstHdr... Start...
        		if(lObjTrnPensionRqstHdrVO.getPpoNo() != null)
        		{
        			lStrPPONo = lObjTrnPensionRqstHdrVO.getPpoNo();
        		}
        		if(lObjTrnPensionRqstHdrVO.getSchemeType() != null)
        		{
        			lStrScheme = lObjTrnPensionRqstHdrVO.getSchemeType();
        		}
        		lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
        		
        		// Getting the Value from TrnPensionRqstHdr... End...
        	}
        	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0)
        	{
        		// Getting the ObjectVo of  MstPensionerHdr... Start...
        		lObjMstPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsrCode);
        		
        		lStrNamePrefix = lObjMstPensionerHdrVO.getPnsnrPrefix();
        		lStrFirstname = lObjMstPensionerHdrVO.getFirstName(); 
        		lStrMiddlename = (lObjMstPensionerHdrVO.getMiddleName()!=null) ? lObjMstPensionerHdrVO.getMiddleName() + " " :"";
				lStrLastname = lObjMstPensionerHdrVO.getLastName();
        		lStrPnsnerName = lStrNamePrefix + ' ' + lStrFirstname + ' ' + lStrMiddlename + lStrLastname;
        		
        		lStrOfficeAddr = lObjMstPensionerHdrVO.getOfficeAddr();
        		lStrDesignation = lObjMstPensionerHdrVO.getDesignation();
        		long Desig_id = Long.parseLong(lStrDesignation);
        		
        		lStrDesigName = lObjMstPensionerHdrDAO.getDesigName(Desig_id);
        		
	        	//	Getting the ObjectVo of  MstPensionerHdr... End...
	        }
        	if(lStrPnsrCode != null && lStrPnsrCode.length() > 0)
        	{
        		//Getting acnno from MstPensionerDtls ...Start...
        		lStrAcnNo = lObjMstPensionerDtlsDAO.getACCNo(lStrPnsrCode);
//        		Getting acnno from MstPensionerDtls ...End...
        	}
        	
        	//getting branch name
        	if(lStrbankCode != null && lStrbranchCode != null)
        	{
        		lStrBranchName = lObjMstPensionerDtlsDAO.getBranchName(lStrbranchCode,lStrbankCode);
        	}
        	
        	inputMap.put("MedRemId", lLngMedId);
        	inputMap.put("BillHdrId", lTrnPensionBillHdrPK);
        	inputMap.put("PPONo", lStrPPONo);
        	inputMap.put("PnsnBillTokenNo", lStrTokenNo);
        	inputMap.put("Name", lStrPnsnerName);
        	inputMap.put("Designation", lStrDesigName);
        	inputMap.put("OfficeAddr", lStrOfficeAddr);
        	inputMap.put("MAAmount", BDMAAmnt);
        	inputMap.put("Scheme", lStrScheme);
        	inputMap.put("BillDate",lStrBillDate);
        	inputMap.put("PnsnrCode", lStrPnsrCode);
        	inputMap.put("RmbrsAmnt", BDReimbrseAmnt);
        	inputMap.put("GrantedAmnt", BDGrantedAmnt);
        	inputMap.put("FrmMon", frmmonth);
        	inputMap.put("FrmYr", frmyear);
        	inputMap.put("ToMon", tomonth);
        	inputMap.put("ToYr", toyear);
        	
        	inputMap.put("BankCode", lStrbankCode);
        	inputMap.put("BranchCode", lStrbranchCode);
        	inputMap.put("HeadCode", lBDheadcode);
        	inputMap.put("ForMonth", lIntformonth);
        	
        	inputMap.put("AcnNo", lStrAcnNo);
        	inputMap.put("BranchName", lStrBranchName);
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

  //=========================================================================================================  
    /**
     * Saves Reimbursement Bill Data in  TRN_PENSION_BILL_HDR and TRN_PENSION_MED_REMBRSMNT
     * @param Map:lMapInput
     * @return ResultObject
     */    	
	public ResultObject saveMRBill(Map inputMap)
    {
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
        String lStrBillNo = null;
        Long lPnsnTokenNo = null;
        
    	lStrBillNo = (String) inputMap.get("billNo");
    	lPnsnTokenNo = (Long) inputMap.get("PnsnTokenNo");
        
        String lStrBillType="MR";
        
        TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,serv.getSessionFactory());
    	TrnPensionBillDtlsDAO lObjTrnPensionBillDtlsDAO = new TrnPensionBillDtlsDAOImpl(TrnPensionBillDtls.class, serv.getSessionFactory());       
        TrnPensionMedRembrsmntDAO lObjTrnPensionMedRembrsmntDAO = new TrnPensionMedRembrsmntDAOImpl(TrnPensionMedRembrsmnt.class,serv.getSessionFactory());
    	
        TrnPensionBillHdr lObjTrnPensionBillHdr = null;
        TrnPensionBillDtls lObjTrnPensionBillDtls = null;
        TrnPensionMedRembrsmnt lObjTrnPensionMedRembrsmnt = null;
        
        long lLngBillHdrId = 0;
        
        try
        {  
        	setSessionInfo(inputMap);
        	lObjTrnPensionBillHdr = (TrnPensionBillHdr) inputMap.get("MRTrnPensionBillHdrVO");
        	lObjTrnPensionBillDtls = (TrnPensionBillDtls) inputMap.get("MRTrnPensionBillDtlsVO");
        	lObjTrnPensionMedRembrsmnt = (TrnPensionMedRembrsmnt) inputMap.get("lObjTrnPensionMedRembrsmnt");
        	
        	if(lObjTrnPensionMedRembrsmnt.getMedRembrsmntId() == 0)
        	{
	        	// Insert data into TrnPensionBillHdr.
	        	lObjTrnPensionBillHdr.setBillType(lStrBillType);
	        	lObjTrnPensionBillHdr.setBillNo(lStrBillNo.toString());
	        	lObjTrnPensionBillHdr.setTokenNo(lPnsnTokenNo.toString());
	        		
	        	lObjTrnPensionBillHdr.setTrnPensionBillHdrId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_hdr", inputMap));
	        	lObjTrnPensionBillHdrDAO.create(lObjTrnPensionBillHdr);
		        
	            //Inserts MR Bill specific data into TRN_PENSION_BILL_DTLS
	        	//sets PK of TrnPensionBillDtls...
	            lObjTrnPensionBillDtls.setTrnPensionBillDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_bill_dtls", inputMap));    	        
	            lObjTrnPensionBillDtls.setTrnPensionBillHdrId(lObjTrnPensionBillHdr.getTrnPensionBillHdrId());
	            lObjTrnPensionBillDtlsDAO.create(lObjTrnPensionBillDtls);
	        	
		        OrgTokenStatusDAOImpl lObjTokenStatusDAO = new OrgTokenStatusDAOImpl(OrgTokenStatus.class,serv.getSessionFactory());
		        
		       
		        Long lLngPnsnBillNo = Long.parseLong(lStrBillNo);
	        	lObjTokenStatusDAO.updateTokenStatus(lPnsnTokenNo, gStrLocationCode, lLngPnsnBillNo, gLngUserId, gLngPostId);
	
	        	lLngBillHdrId = lObjTrnPensionBillHdr.getTrnPensionBillHdrId();
        	}
        	
        	//For updating table trn_pension_med_rembrsmnt if user changes data in bill tab Start...
	        if(lObjTrnPensionMedRembrsmnt.getMedRembrsmntId() !=0 && lStrBillNo.length() > 0)
	        {         
    	        lObjTrnPensionMedRembrsmntDAO.update(lObjTrnPensionMedRembrsmnt);
	        }
	        //For updating table trn_pension_med_rembrsmnt if user changes data in bill tab End 
	        //insert data into trn_pension_med_rembrsmnt
	        else
	        {
	        	lObjTrnPensionMedRembrsmnt.setBillHdrId(lLngBillHdrId);
	        	lObjTrnPensionMedRembrsmnt.setMedRembrsmntId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_med_rembrsmnt", inputMap));
	        	lObjTrnPensionMedRembrsmntDAO.create(lObjTrnPensionMedRembrsmnt);
	        }
	        //end of inserting data no in trn_pension_med_rembrsmnt
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
	
	//-------------------------------------------------------------------------------------------------------
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
