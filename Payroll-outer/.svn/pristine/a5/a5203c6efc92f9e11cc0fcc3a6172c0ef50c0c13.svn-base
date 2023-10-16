package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.RevisionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

public class RevisionVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;

	/* Global Variable for Location Code */
	private String gStrLocationCode = null;

	/* Global Variable for DB Id */
	private Long gLngDBId = null;
	
	 /* Global Variable for Current Date */
    private Date gDate = null;

    /* Global Variable for Logger Class */
	private Log gLogger = LogFactory.getLog(getClass());


    /**
     * It generates Pension Bill Specific VOs(TrnPensionerRivisionDtls).
     *  
     * @param objectArgs
     *            ServiceMap
     * @return objRes ResultObject
     * @author Aparna
     */
    public ResultObject generateMap(Map objArgs)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	
        try{
        	setSessionInfo(objArgs);
            
        	Map inputMap =  generateTrnPensionerRivisionDtls(objArgs);
            TrnPensionerRivisionDtls lObjPensionerRivisionDtls = (TrnPensionerRivisionDtls) inputMap.get("lObjPensionerRivisionDtls");
            TrnPensionerRivisionDtls lObjNewRecord = (TrnPensionerRivisionDtls) inputMap.get("NewRecord");
            TrnPensionRqstHdr lObjPensionRqstHdr = generateRqstHdr(objArgs);
            
            objArgs.put("TrnPensionerRivisionDtls", lObjPensionerRivisionDtls);
            objArgs.put("NewRecord", lObjNewRecord);
            objArgs.put("TrnPensionRqstHdr", lObjPensionRqstHdr);
            objRes.setResultValue(objArgs);
        }
        catch (Exception e) {
			gLogger.error("Error is :" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
        return objRes;
    }
    
    /**
     * It generates Pension Bill Specific TrnPensionerRivisionDtls VO.
     *  
     * @param lMapInput
     *            InputMap.
     * @return lLstTrnPensionerRivisionDtls List<TrnPensionerRivisionDtls>
     * @author Aparna
     */
    private Map generateTrnPensionerRivisionDtls(Map lMapInput) throws Exception
    {        
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");

        String[] lStrPenRqstId = null;
        String[] lStrPnsnrCode = null;        
        String lStrRevDate = null;
        String[] lStrBasic = null;
        String[] lStrDPPer = null;
        String[] lStrDAPer = null;
        String[] lStrCVP = null;
        String[] lStrCVPMonthly = null;
        String[] lStrDCRG = null;
        String[] lStrFP1 = null;
        String[] lStrFP2 = null;
        String[] lStrORGBF56 = null;        
        String[] lStrREDBF56 = null;        
        String[] lStrORGAF56 = null;        
        String[] lStrREDAF56 = null;        
        String[] lStrORGAF60 = null;        
        String[] lStrREDAF60 = null;        
        String[] lStrCrtdUserId = null;
        String[] lStrCrtdPostId = null;
        String[] lStrCrtdDate = null;
                
        TrnPensionerRivisionDtls lObjPensionerRivisionDtls = null;
        TrnPensionerRivisionDtls lObjNewRecord = null;
        TrnPensionRqstHdr lObjPensionRqstHdr = null;

        try
        {
        	lStrPenRqstId = StringUtility.getParameterValues("hidPenRqstId", request);
        	lStrPnsnrCode = StringUtility.getParameterValues("hidPnsnrCode", request);
        	lStrBasic = StringUtility.getParameterValues("txtBasicAmt", request);
        	lStrDPPer = StringUtility.getParameterValues("txtDPPer", request);
        	lStrDAPer = StringUtility.getParameterValues("txtDAPer", request);
        	lStrCVP = StringUtility.getParameterValues("txtCVPAmt", request);
        	lStrCVPMonthly = StringUtility.getParameterValues("txtCVPMonthlyAmt", request);
        	lStrDCRG = StringUtility.getParameterValues("txtDCRGAmt", request);
        	lStrFP1 = StringUtility.getParameterValues("txtFP1Amt", request);
        	lStrFP2 = StringUtility.getParameterValues("txtFP2Amt", request);
        	lStrORGBF56 = StringUtility.getParameterValues("txtOrgBF56", request);
        	lStrREDBF56 = StringUtility.getParameterValues("txtRedBF56", request);
        	lStrORGAF56 = StringUtility.getParameterValues("txtOrgAF56", request);
        	lStrREDAF56 = StringUtility.getParameterValues("txtRedAF56", request);
        	lStrORGAF60 = StringUtility.getParameterValues("txtOrgAF60", request);
        	lStrREDAF60 = StringUtility.getParameterValues("txtRedAF60", request);
        	lStrCrtdUserId = StringUtility.getParameterValues("hidCrtdUserId", request);
        	lStrCrtdPostId = StringUtility.getParameterValues("hidCrtdPostId", request);
        	lStrCrtdDate = StringUtility.getParameterValues("hidCrtdDate", request);

        	Integer lIntCnt = lStrPnsnrCode.length - 2;
           	lObjPensionerRivisionDtls = new TrnPensionerRivisionDtls();
            	
        	// Date Conversion 
            
            try {
            	
            	lStrRevDate = StringUtility.getParameter("txtRevDate["+lIntCnt+"]",request);
            	
            	if(lStrRevDate != null)
            	{
            		lObjPensionerRivisionDtls.setRivisionDate(new SimpleDateFormat("dd/MM/yyyy").parse(lStrRevDate));
            	}
            	
            	lObjPensionerRivisionDtls.setRevisionCounter(Byte.parseByte(lIntCnt.toString()));
            	
            } 
            catch(Exception ex)
            {
            	gLogger.error("Exception occurred in generateTrnPensionerRivisionDtls() method of RevisionVOGenerator Class " +
                        " while setting Date" + ex);
            }
            
            // Date Conversion End
            if(!lStrPenRqstId[lIntCnt].equals("") || lStrPenRqstId[lIntCnt].length() > 0)
        	{
            	lObjPensionerRivisionDtls.setPensionRequestId(new Long(lStrPenRqstId[lIntCnt]));
        	}
            lObjPensionerRivisionDtls.setCreatedUserId(new BigDecimal(lStrCrtdUserId[lIntCnt]));
            lObjPensionerRivisionDtls.setCreatedPostId(new BigDecimal(lStrCrtdPostId[lIntCnt]));
            if(lStrCrtdDate[lIntCnt] != null)
        	{
        		lObjPensionerRivisionDtls.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy").parse(lStrCrtdDate[lIntCnt]));
        	}
        	lObjPensionerRivisionDtls.setUpdatedUserId(new BigDecimal(gLngUserId));
        	lObjPensionerRivisionDtls.setUpdatedPostId(new BigDecimal(gLngPostId));
        	lObjPensionerRivisionDtls.setUpdatedDate(gDate);	
        	lObjPensionerRivisionDtls.setPensionerCode(lStrPnsnrCode[lIntCnt]);
        	
        	if(lStrBasic[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setBasicPension(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setBasicPension(new BigDecimal(lStrBasic[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setBasicPension(new BigDecimal(lStrBasic[lIntCnt]));
        	}
        	
        	if(lStrDPPer[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setDpPercent(new BigDecimal(0));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setDpPercent(new BigDecimal(lStrDPPer[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setDpPercent(new BigDecimal(lStrDPPer[lIntCnt]));
        	}
        	
        	if(lStrDAPer[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setDaPercent(new BigDecimal(0));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setDaPercent(new BigDecimal(lStrDAPer[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setDaPercent(new BigDecimal(lStrDAPer[lIntCnt]));
        	}
        	
        	if(lStrCVP[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setCvpAmount(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setCvpAmount(new BigDecimal(lStrCVP[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setCvpAmount(new BigDecimal(lStrCVP[lIntCnt]));
        	}
        	
        	if(lStrCVPMonthly[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setCvpMonthlyAmount(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setCvpMonthlyAmount(new BigDecimal(lStrCVPMonthly[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setCvpMonthlyAmount(new BigDecimal(lStrCVPMonthly[lIntCnt]));
        	}
        	
        	if(lStrDCRG[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setDcrgAmount(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setDcrgAmount(new BigDecimal(lStrDCRG[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setDcrgAmount(new BigDecimal(lStrDCRG[lIntCnt]));
        	}
        	
        	if(lStrFP1[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setFp1Amount(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setFp1Amount(new BigDecimal(lStrFP1[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setFp1Amount(new BigDecimal(lStrFP1[lIntCnt]));
        	}
        	
        	if(lStrFP2[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setFp2Amount(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setFp2Amount(new BigDecimal(lStrFP2[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setFp2Amount(new BigDecimal(lStrFP2[lIntCnt]));
        	}
        	
        	//-----------------------------------------------------------newly added fields start
        	if(lStrORGBF56[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setOrgBf11156(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setOrgBf11156(new BigDecimal(lStrORGBF56[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setOrgBf11156(new BigDecimal(lStrORGBF56[lIntCnt]));
        	}

        	if(lStrREDBF56[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setRedBf11156(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setRedBf11156(new BigDecimal(lStrREDBF56[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setRedBf11156(new BigDecimal(lStrREDBF56[lIntCnt]));
        	}
        	if(lStrORGAF56[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setOrgAf11156(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setOrgAf11156(new BigDecimal(lStrORGAF56[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setOrgAf11156(new BigDecimal(lStrORGAF56[lIntCnt]));
        	}
        	if(lStrREDAF56[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setRedAf11156(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setRedAf11156(new BigDecimal(lStrREDAF56[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setRedAf11156(new BigDecimal(lStrREDAF56[lIntCnt]));
        	}
        	if(lStrORGAF60[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setOrgAf10560(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setOrgAf10560(new BigDecimal(lStrORGAF60[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setOrgAf10560(new BigDecimal(lStrORGAF60[lIntCnt]));
        	}
        	if(lStrREDAF60[lIntCnt].equals(""))
        	{	
        		if(lIntCnt == 0)
        		{
        			lObjPensionerRivisionDtls.setRedAf10560(new BigDecimal("0"));
        		}
        		else
        		{
        			lObjPensionerRivisionDtls.setRedAf10560(new BigDecimal(lStrREDAF60[lIntCnt - 1]));
        		}
        	}
        	else
        	{
        		lObjPensionerRivisionDtls.setRedAf10560(new BigDecimal(lStrREDAF60[lIntCnt]));
        	}
        	
        	//-----------------------------------------------------------newly added fields end
        	lObjPensionerRivisionDtls.setActiveFlag("N");
        	lObjPensionerRivisionDtls.setLocationCode(gStrLocationCode);
        	lObjPensionerRivisionDtls.setDbId(new BigDecimal(gLngDBId));

            //for the last record i.e the new record
        	lIntCnt = lIntCnt + 1;
            
            lObjNewRecord = new TrnPensionerRivisionDtls();
            lObjPensionRqstHdr = new TrnPensionRqstHdr();
        	
        	// Date Conversion 
            
            try {
            	lStrRevDate = StringUtility.getParameter("txtNewRevDate",request);
            	
            	if(lStrRevDate != null)
            	{
            		lObjNewRecord.setRivisionDate(new SimpleDateFormat("dd/MM/yyyy").parse(lStrRevDate));
            	}
            	lObjNewRecord.setRevisionCounter(Byte.parseByte(lIntCnt.toString()));
            	
            } 
            catch(Exception ex){
            	gLogger.error("Exception occurred in generateTrnPensionerRivisionDtls() method of RevisionVOGenerator Class " +
                        " while setting Date" + ex);
            }
            
                           
            // Date Conversion End
        	
        	lObjNewRecord.setCreatedUserId(new BigDecimal(gLngUserId));
        	lObjNewRecord.setCreatedPostId(new BigDecimal(gLngPostId));
        	lObjNewRecord.setCreatedDate(gDate);	
        	lObjNewRecord.setPensionerCode(lStrPnsnrCode[lIntCnt - 1]);
        	
        	if(lStrBasic[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setBasicPension(new BigDecimal(lStrBasic[lIntCnt - 1]));
        		lObjPensionRqstHdr.setBasicPensionAmount(new BigDecimal(lStrBasic[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setBasicPension(new BigDecimal(lStrBasic[lIntCnt]));
        		lObjPensionRqstHdr.setBasicPensionAmount(new BigDecimal(lStrBasic[lIntCnt]));
        	}
        	
        	if(lStrDPPer[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setDpPercent(new BigDecimal(lStrDPPer[lIntCnt - 1]));
        		lObjPensionRqstHdr.setDpPercent(new BigDecimal(lStrDPPer[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setDpPercent(new BigDecimal(lStrDPPer[lIntCnt]));
        		lObjPensionRqstHdr.setDpPercent(new BigDecimal(lStrDPPer[lIntCnt]));
        	}
        	
        	if(lStrDAPer[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setDaPercent(new BigDecimal(lStrDAPer[lIntCnt - 1]));
        		lObjPensionRqstHdr.setDaPercent(new BigDecimal(lStrDAPer[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setDaPercent(new BigDecimal(lStrDAPer[lIntCnt]));
        		lObjPensionRqstHdr.setDaPercent(new BigDecimal(lStrDAPer[lIntCnt]));
        	}
        	
        	if(lStrCVP[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setCvpAmount(new BigDecimal(lStrCVP[lIntCnt - 1]));
        		lObjPensionRqstHdr.setCvpAmount(new BigDecimal(lStrCVP[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setCvpAmount(new BigDecimal(lStrCVP[lIntCnt]));
        		lObjPensionRqstHdr.setCvpAmount(new BigDecimal(lStrCVP[lIntCnt]));
        	}
        	
        	if(lStrCVPMonthly[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setCvpMonthlyAmount(new BigDecimal(lStrCVPMonthly[lIntCnt - 1]));
        		lObjPensionRqstHdr.setCvpMonthlyAmount(new BigDecimal(lStrCVPMonthly[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setCvpMonthlyAmount(new BigDecimal(lStrCVPMonthly[lIntCnt]));
        		lObjPensionRqstHdr.setCvpMonthlyAmount(new BigDecimal(lStrCVPMonthly[lIntCnt]));
        	}
        	
        	if(lStrDCRG[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setDcrgAmount(new BigDecimal(lStrDCRG[lIntCnt - 1]));
        		lObjPensionRqstHdr.setDcrgAmount(new BigDecimal(lStrDCRG[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setDcrgAmount(new BigDecimal(lStrDCRG[lIntCnt]));
        		lObjPensionRqstHdr.setDcrgAmount(new BigDecimal(lStrDCRG[lIntCnt]));
        	}
        	
        	if(lStrFP1[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setFp1Amount(new BigDecimal(lStrFP1[lIntCnt - 1]));
        		lObjPensionRqstHdr.setFp1Amount(new BigDecimal(lStrFP1[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setFp1Amount(new BigDecimal(lStrFP1[lIntCnt]));
        		lObjPensionRqstHdr.setFp1Amount(new BigDecimal(lStrFP1[lIntCnt]));
        	}
        	
        	if(lStrFP2[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setFp2Amount(new BigDecimal(lStrFP2[lIntCnt - 1]));
        		lObjPensionRqstHdr.setFp2Amount(new BigDecimal(lStrFP2[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setFp2Amount(new BigDecimal(lStrFP2[lIntCnt]));
        		lObjPensionRqstHdr.setFp2Amount(new BigDecimal(lStrFP2[lIntCnt]));
        	}
//----------------------------------------new fields start---------------------------
        	if(lStrORGBF56[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setOrgBf11156(new BigDecimal(lStrORGBF56[lIntCnt - 1]));
        		lObjPensionRqstHdr.setOrgBf11156(new BigDecimal(lStrORGBF56[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setOrgBf11156(new BigDecimal(lStrORGBF56[lIntCnt]));
        		lObjPensionRqstHdr.setOrgBf11156(new BigDecimal(lStrORGBF56[lIntCnt]));
        	}
        	if(lStrREDBF56[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setRedBf11156(new BigDecimal(lStrREDBF56[lIntCnt - 1]));
        		lObjPensionRqstHdr.setRedBf11156(new BigDecimal(lStrREDBF56[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setRedBf11156(new BigDecimal(lStrREDBF56[lIntCnt]));
        		lObjPensionRqstHdr.setRedBf11156(new BigDecimal(lStrREDBF56[lIntCnt]));
        	}
        	if(lStrORGAF56[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setOrgAf11156(new BigDecimal(lStrORGAF56[lIntCnt - 1]));
        		lObjPensionRqstHdr.setOrgAf11156(new BigDecimal(lStrORGAF56[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setOrgAf11156(new BigDecimal(lStrORGAF56[lIntCnt]));
        		lObjPensionRqstHdr.setOrgAf11156(new BigDecimal(lStrORGAF56[lIntCnt]));
        	}
        	if(lStrREDAF56[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setRedAf11156(new BigDecimal(lStrREDAF56[lIntCnt - 1]));
        		lObjPensionRqstHdr.setRedAf11156(new BigDecimal(lStrREDAF56[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setRedAf11156(new BigDecimal(lStrREDAF56[lIntCnt]));
        		lObjPensionRqstHdr.setRedAf11156(new BigDecimal(lStrREDAF56[lIntCnt]));
        	}
        	if(lStrORGAF60[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setOrgAf10560(new BigDecimal(lStrORGAF60[lIntCnt - 1]));
        		lObjPensionRqstHdr.setOrgAf10560(new BigDecimal(lStrORGAF60[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setOrgAf10560(new BigDecimal(lStrORGAF60[lIntCnt]));
        		lObjPensionRqstHdr.setOrgAf10560(new BigDecimal(lStrORGAF60[lIntCnt]));
        	}
        	if(lStrREDAF60[lIntCnt].equals(""))
        	{	
        		lObjNewRecord.setRedAf10560(new BigDecimal(lStrREDAF60[lIntCnt - 1]));
        		lObjPensionRqstHdr.setRedAf10560(new BigDecimal(lStrREDAF60[lIntCnt - 1]));
        	}
        	else
        	{
        		lObjNewRecord.setRedAf10560(new BigDecimal(lStrREDAF60[lIntCnt]));
        		lObjPensionRqstHdr.setRedAf10560(new BigDecimal(lStrREDAF60[lIntCnt]));
        	}
       // 	----------------------------------------new fields end---------------------------        	
        	lObjNewRecord.setActiveFlag("Y");
        	lObjNewRecord.setLocationCode(gStrLocationCode);
        	lObjNewRecord.setDbId(new BigDecimal(gLngDBId));

            lMapInput.put("lObjPensionerRivisionDtls", lObjPensionerRivisionDtls);
            lMapInput.put("lObjPensionRqstHdr", lObjPensionRqstHdr);
            lMapInput.put("NewRecord", lObjNewRecord);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }

        return lMapInput;
    }

    private TrnPensionRqstHdr generateRqstHdr(Map lMapInput) throws Exception
    {
    	HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
    	ServiceLocator serv = (ServiceLocator) lMapInput.get("serviceLocator");
    	ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
        String lStrNewStatus = bundleConst.getString("CMN.NEW");
        String lStrAppStatus = bundleConst.getString("STATUS.APPROVED");
        TrnPensionRqstHdr lObjPensionRqstHdr = new TrnPensionRqstHdr();
    	TrnPensionRqstHdr lObjTrnPensionRqstHdr = new TrnPensionRqstHdr();
    	RevisionDAOImpl lObjRevisionDAO = new RevisionDAOImpl(TrnPensionerRivisionDtls.class,serv.getSessionFactory());
    	try
    	{
    		String lPPONo = StringUtility.getParameter("txtPPONo", request);
    		TrnPensionRqstHdrDAO lObjRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,serv.getSessionFactory());
        	lObjTrnPensionRqstHdr = (TrnPensionRqstHdr) lMapInput.get("lObjPensionRqstHdr");
        	
        	
        	Long rqstId = lObjRevisionDAO.getCaseIdfromPpoNo(lPPONo, lStrNewStatus);
        	if(rqstId == 0L)
        	{
        		rqstId = lObjRevisionDAO.getCaseIdfromPpoNo(lPPONo, lStrAppStatus);
        		//insert a new record
        		TrnPensionRqstHdr lObjPensionRqstHdrApp = lObjRqstHdrDAO.read(rqstId);
        		lObjPensionRqstHdr = new TrnPensionRqstHdr();
        		lObjPensionRqstHdr.setApproveStatus(lObjPensionRqstHdrApp.getApproveStatus());
        		lObjPensionRqstHdr.setAuditorFlag(lObjPensionRqstHdrApp.getAuditorFlag());
        		lObjPensionRqstHdr.setCaseOwner(lObjPensionRqstHdrApp.getCaseOwner());
        		lObjPensionRqstHdr.setCommensionDate(lObjPensionRqstHdrApp.getCommensionDate());
        		lObjPensionRqstHdr.setCvpDate(lObjPensionRqstHdrApp.getCvpDate());
        		lObjPensionRqstHdr.setCvpOrderNo(lObjPensionRqstHdrApp.getCvpOrderNo());
        		lObjPensionRqstHdr.setCvpPaidFlag(lObjPensionRqstHdrApp.getCvpPaidFlag());
        		lObjPensionRqstHdr.setCvpRestorationDate(lObjPensionRqstHdrApp.getCvpRestorationDate());
        		lObjPensionRqstHdr.setDbId(lObjPensionRqstHdrApp.getDbId());
        		lObjPensionRqstHdr.setDcrgDate(lObjPensionRqstHdrApp.getDcrgDate());
        		lObjPensionRqstHdr.setDcrgOrderNo(lObjPensionRqstHdrApp.getDcrgOrderNo());
        		lObjPensionRqstHdr.setDcrgPaidFlag(lObjPensionRqstHdrApp.getDcrgPaidFlag());
        		lObjPensionRqstHdr.setDeptOrdNo(lObjPensionRqstHdrApp.getDeptOrdNo());
        		lObjPensionRqstHdr.setDppfAmount(lObjPensionRqstHdrApp.getDppfAmount());
        		lObjPensionRqstHdr.setFp1Date(lObjPensionRqstHdrApp.getFp1Date());
        		lObjPensionRqstHdr.setFp2Date(lObjPensionRqstHdrApp.getFp2Date());
        		lObjPensionRqstHdr.setHeadCode(lObjPensionRqstHdrApp.getHeadCode());
        		lObjPensionRqstHdr.setInwardMode(lObjPensionRqstHdrApp.getInwardMode());
        		lObjPensionRqstHdr.setLocationCode(lObjPensionRqstHdrApp.getLocationCode());
        		lObjPensionRqstHdr.setMedicalAllowenceAmount(lObjPensionRqstHdrApp.getMedicalAllowenceAmount());
        		lObjPensionRqstHdr.setPaidDate(lObjPensionRqstHdrApp.getPaidDate());
        		lObjPensionRqstHdr.setPendingRecovery(lObjPensionRqstHdrApp.getPendingRecovery());
        		lObjPensionRqstHdr.setPensionableAmount(lObjPensionRqstHdrApp.getPensionableAmount());
        		lObjPensionRqstHdr.setPensionType(lObjPensionRqstHdrApp.getPensionType());
        		lObjPensionRqstHdr.setPpoDate(lObjPensionRqstHdrApp.getPpoDate());
        		lObjPensionRqstHdr.setPpoInwardDate(lObjPensionRqstHdrApp.getPpoInwardDate());
        		lObjPensionRqstHdr.setPpoNo(lObjPensionRqstHdrApp.getPpoNo());
        		lObjPensionRqstHdr.setRemark(lObjPensionRqstHdrApp.getRemark());
        		lObjPensionRqstHdr.setSchemeType(lObjPensionRqstHdrApp.getSchemeType());
        		lObjPensionRqstHdr.setStatus(lObjPensionRqstHdrApp.getStatus());
        		lObjPensionRqstHdr.setTiPercent(lObjPensionRqstHdrApp.getTiPercent());
        		lObjPensionRqstHdr.setTotalRecovery(lObjPensionRqstHdrApp.getTotalRecovery());
        		lObjPensionRqstHdr.setTotalSrvc(lObjPensionRqstHdrApp.getTotalSrvc());
        		lObjPensionRqstHdr.setTrnCounter(lObjPensionRqstHdrApp.getTrnCounter());
        		lObjPensionRqstHdr.setTypeFlag(lObjPensionRqstHdrApp.getTypeFlag());
        		lObjPensionRqstHdr.setPensionerCode(lObjPensionRqstHdrApp.getPensionerCode());
        		lObjPensionRqstHdr.setBasicPensionAmount(lObjTrnPensionRqstHdr.getBasicPensionAmount());
        		lObjPensionRqstHdr.setDpPercent(lObjTrnPensionRqstHdr.getDpPercent());
        		lObjPensionRqstHdr.setDaPercent(lObjTrnPensionRqstHdr.getDaPercent());
        		lObjPensionRqstHdr.setCvpAmount(lObjTrnPensionRqstHdr.getCvpAmount());
        		lObjPensionRqstHdr.setCvpMonthlyAmount(lObjTrnPensionRqstHdr.getCvpMonthlyAmount());
        		lObjPensionRqstHdr.setDcrgAmount(lObjTrnPensionRqstHdr.getDcrgAmount());
        		lObjPensionRqstHdr.setFp1Amount(lObjTrnPensionRqstHdr.getFp1Amount());
        		lObjPensionRqstHdr.setFp2Amount(lObjTrnPensionRqstHdr.getFp2Amount());
        		lObjPensionRqstHdr.setOrgBf11156(lObjTrnPensionRqstHdr.getOrgBf11156());
        		lObjPensionRqstHdr.setRedBf11156(lObjTrnPensionRqstHdr.getRedBf11156());
        		lObjPensionRqstHdr.setOrgAf11156(lObjTrnPensionRqstHdr.getOrgAf11156());
        		lObjPensionRqstHdr.setRedAf11156(lObjTrnPensionRqstHdr.getRedAf11156());
        		lObjPensionRqstHdr.setOrgAf10560(lObjTrnPensionRqstHdr.getOrgAf10560());
        		lObjPensionRqstHdr.setRedAf10560(lObjTrnPensionRqstHdr.getRedAf10560());
        		lObjPensionRqstHdr.setCreatedDate(gDate);
        		lObjPensionRqstHdr.setCreatedPostId(new BigDecimal(gLngPostId));
        		lObjPensionRqstHdr.setCreatedUserId(new BigDecimal(gLngUserId));
        		lObjPensionRqstHdr.setCaseStatus(lStrNewStatus);
        	}
        	else
        	{
        		//update record
            	lObjPensionRqstHdr = lObjRqstHdrDAO.read(rqstId);
            	
            	if(lObjTrnPensionRqstHdr != null)
            	{
            		lObjPensionRqstHdr.setBasicPensionAmount(lObjTrnPensionRqstHdr.getBasicPensionAmount());
            		lObjPensionRqstHdr.setDpPercent(lObjTrnPensionRqstHdr.getDpPercent());
            		lObjPensionRqstHdr.setDaPercent(lObjTrnPensionRqstHdr.getDaPercent());
            		lObjPensionRqstHdr.setCvpAmount(lObjTrnPensionRqstHdr.getCvpAmount());
            		lObjPensionRqstHdr.setCvpMonthlyAmount(lObjTrnPensionRqstHdr.getCvpMonthlyAmount());
            		lObjPensionRqstHdr.setDcrgAmount(lObjTrnPensionRqstHdr.getDcrgAmount());
            		lObjPensionRqstHdr.setFp1Amount(lObjTrnPensionRqstHdr.getFp1Amount());
            		lObjPensionRqstHdr.setFp2Amount(lObjTrnPensionRqstHdr.getFp2Amount());
            		lObjPensionRqstHdr.setOrgBf11156(lObjTrnPensionRqstHdr.getOrgBf11156());
            		lObjPensionRqstHdr.setRedBf11156(lObjTrnPensionRqstHdr.getRedBf11156());
            		lObjPensionRqstHdr.setOrgAf11156(lObjTrnPensionRqstHdr.getOrgAf11156());
            		lObjPensionRqstHdr.setRedAf11156(lObjTrnPensionRqstHdr.getRedAf11156());
            		lObjPensionRqstHdr.setOrgAf10560(lObjTrnPensionRqstHdr.getOrgAf10560());
            		lObjPensionRqstHdr.setRedAf10560(lObjTrnPensionRqstHdr.getRedAf10560());
            		lObjPensionRqstHdr.setUpdatedDate(gDate);
            		lObjPensionRqstHdr.setUpdatedPostId(new BigDecimal(gLngPostId));
            		lObjPensionRqstHdr.setUpdatedUserId(new BigDecimal(gLngUserId));
            		//lObjRqstHdrDAO.update(lObjPensionRqstHdr);
            	}
        	}
    	}
    	catch (Exception e)
        {
             gLogger.error(" Error is : " + e, e);
             throw(e);
        }
    	return lObjPensionRqstHdr;
    }

    private void setSessionInfo(Map inputMap) throws Exception
    {
    	try{    		
            gLngPostId = SessionHelper.getPostId(inputMap);
            gLngUserId = SessionHelper.getUserId(inputMap);
            gStrLocationCode = SessionHelper.getLocationCode(inputMap);
            gLngDBId = SessionHelper.getDbId(inputMap);
            gDate = DBUtility.getCurrentDateFromDB();
    	}
    	 catch (Exception e) {
 			gLogger.error("Error is :" + e, e);
 			throw (e);
 		}
        
    }
}
