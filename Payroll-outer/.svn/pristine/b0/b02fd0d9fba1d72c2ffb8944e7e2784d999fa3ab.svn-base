package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;

/**
 * Pension Bill Specific VO Generator.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class ArrearDtlsVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	private Long gLngPostId = null;

	/* Global Variable for UserId */
	private Long gLngUserId = null;
    
    /* Global Variable for Current Date */
	private Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());
    
    /**
     * Generates VO for insertion of Pension Bill Data
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject generateMap(Map lMapInput)
    { 
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

        List<TrnPensionArrearDtls> lstTrnPensionArrearDtls = null;
        
        try{
        	
        	setSessionInfo(lMapInput);
            
            lstTrnPensionArrearDtls = generateTrnPensionArrearDtlsVO(lMapInput);
        	
            lMapInput.put("TrnPensionArrearDtlsVOLst", lstTrnPensionArrearDtls);
            
        	objRes.setResultValue(lMapInput);
        }
        catch(Exception e)
        {   
        	gLogger.error("Error is : ", e);
        	objRes.setResultValue(null);
        	objRes.setThrowable(e);
        	objRes.setResultCode(ErrorConstants.ERROR);
        	objRes.setViewName("errorPage");           
        }
        return objRes;
    }
    
    /**
     * Generate TrnPensionArrearDtlsVO from JSP Data
     * @param lMapInput
     * @return TrnPensionArrearDtls
     */
    public List<TrnPensionArrearDtls> generateTrnPensionArrearDtlsVO(Map lMapInput) throws Exception
    {
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        
        List<TrnPensionArrearDtls> lstTrnPensionArrearDtlsVO = new ArrayList<TrnPensionArrearDtls>(); 
        TrnPensionArrearDtls lObjPensionArrearDtlsVO = null;
        
        String[] lStrFieldType = null;
        String[] lStrOldValue = null;
        String[] lStrNewValue = null;
        String[] lStrWEFYYYY = null;
        String[] lStrWEFMM = null;
        String[] lStrWETYYYY = null;
        String[] lStrWETMM = null;
        String[] lStrDifferenceValue = null;
        String[] lStrTotDifferenceAmt = null;
        String[] lStrPAYFYYYY = null;
        String[] lStrPAYFMM = null;
        String[] lStrPAYTYYYY = null;
        String[] lStrPAYTMM = null;
        String[] lStrBillNo = null;
        
        String lStrPnsnCode = null;
        String lStrRqstID = null;
        String lStrArrearFlg = null;
        
        lStrPnsnCode = StringUtility.getParameter("hidPnsnerCode",request);
        lStrRqstID = StringUtility.getParameter("hidTrnPensionRqstID", request);
        lStrArrearFlg = StringUtility.getParameter("hidArrearFlg", request);
        lStrBillNo = StringUtility.getParameterValues("hidBillNo", request);
        
        lStrFieldType = StringUtility.getParameterValues("hidFieldType", request);
        lStrOldValue = StringUtility.getParameterValues("txtOldValue", request);
        lStrNewValue = StringUtility.getParameterValues("txtNewValue", request);        
        lStrWEFYYYY = StringUtility.getParameterValues("cmbWEFYYYY", request);
        lStrWEFMM = StringUtility.getParameterValues("cmbWEFMM", request);
        lStrWETYYYY = StringUtility.getParameterValues("cmbWETYYYY", request);
        lStrWETMM = StringUtility.getParameterValues("cmbWETMM", request);
        lStrDifferenceValue = StringUtility.getParameterValues("txtDiffValue", request);
        lStrTotDifferenceAmt = StringUtility.getParameterValues("hidTotalDiffValue", request);
        lStrPAYFYYYY = StringUtility.getParameterValues("cmbPAYFYYYY", request);
        lStrPAYFMM = StringUtility.getParameterValues("cmbPAYFMM", request);
        lStrPAYTYYYY = StringUtility.getParameterValues("cmbPAYTYYYY", request);
        lStrPAYTMM = StringUtility.getParameterValues("cmbPAYTMM", request);
        
        
        try 
        {	
        	setSessionInfo(lMapInput);
        	//	lObjTrnPensionBillDtls.setPensionAmount(new BigDecimal(lStrBasicPension.length() != 0 ? lStrBasicPension : "0"));
            
            // Delete All Previous Entrys.
            if(lStrArrearFlg.equalsIgnoreCase("SAVED"))
            {
                TrnPensionArrearDtlsDAO lObjPensionArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,srvcLoc.getSessionFactory());
                List<TrnPensionArrearDtls> lPensionArrearDtlsVoLst = null;
                TrnPensionArrearDtls lObjPensionArrearDtlsVo = null;

                lPensionArrearDtlsVoLst = lObjPensionArrearDtlsDAO.getTrnPensionArrearDtlsVo(lStrPnsnCode);
                
                if(lPensionArrearDtlsVoLst != null)
                {
                    for(int i = 0 ; i < lPensionArrearDtlsVoLst.size() ; i++)
                    {
                        lObjPensionArrearDtlsVo = null;
                        lObjPensionArrearDtlsVo = lObjPensionArrearDtlsDAO.read(lPensionArrearDtlsVoLst.get(i).getPensionArrearDtlsId());
                        lObjPensionArrearDtlsDAO.delete(lObjPensionArrearDtlsVo);
                    }
                }
            }
            
            for(int i = 0; i < lStrFieldType.length ; i++)
            {
                if(lStrOldValue[i].length() > 0 && lStrNewValue[i].length() > 0)
                {
                    lObjPensionArrearDtlsVO = new TrnPensionArrearDtls();
                    
                    if(lStrWEFMM[i].length() == 1) {
                        lStrWEFMM[i] = "0" + lStrWEFMM[i];
                    }
                    
                    if(lStrWETMM[i].length() == 1) {
                        lStrWETMM[i] = "0" + lStrWETMM[i];
                    }
                    
                    if(lStrPAYFMM[i].length() == 1) {
                        lStrPAYFMM[i] = "0" + lStrPAYFMM[i];
                    }
                    
                    if(lStrPAYTMM[i].length() == 1) {
                        lStrPAYTMM[i] = "0" + lStrPAYTMM[i];
                    }
                    
                    String WEFYYYYMM = lStrWEFYYYY[i] + lStrWEFMM[i];
                    String WETYYYYMM = lStrWETYYYY[i] + lStrWETMM[i];
                    String PAYFYYYYMM = lStrPAYFYYYY[i] + lStrPAYFMM[i];
                    String PAYTYYYYMM = lStrPAYTYYYY[i] + lStrPAYTMM[i];
                    
                    lObjPensionArrearDtlsVO.setPensionRequestId(Long.parseLong(lStrRqstID));
                    lObjPensionArrearDtlsVO.setPensionerCode(lStrPnsnCode);
                    lObjPensionArrearDtlsVO.setArrearFieldType(lStrFieldType[i]);
                    
                    lObjPensionArrearDtlsVO.setOldAmountPercentage(new BigDecimal(lStrOldValue[i].length() != 0 ? lStrOldValue[i] : "0.00"));
                    lObjPensionArrearDtlsVO.setNewAmountPercentage(new BigDecimal(lStrNewValue[i].length() != 0 ? lStrNewValue[i] : "0.00"));
                                        
                    lObjPensionArrearDtlsVO.setEffectedFromYyyymm(Integer.parseInt(WEFYYYYMM));
                    lObjPensionArrearDtlsVO.setEffectedToYyyymm(Integer.parseInt(WETYYYYMM));
                    
                    lObjPensionArrearDtlsVO.setDifferenceAmount(new BigDecimal(lStrDifferenceValue[i].length() != 0 ? lStrDifferenceValue[i] : "0.00"));
                    lObjPensionArrearDtlsVO.setTotalDifferenceAmt(new BigDecimal(lStrTotDifferenceAmt[i].length() != 0 ? lStrTotDifferenceAmt[i] : "0.00"));
                    
                    lObjPensionArrearDtlsVO.setBillNo(lStrBillNo[i]);
                    
                    lObjPensionArrearDtlsVO.setPaymentFromYyyymm(Integer.parseInt(PAYFYYYYMM));
                    lObjPensionArrearDtlsVO.setPaymentToYyyymm(Integer.parseInt(PAYTYYYYMM));
                    
                                        
                    lObjPensionArrearDtlsVO.setCreatedUserId(new BigDecimal(gLngUserId));
                    lObjPensionArrearDtlsVO.setCreatedPostId(new BigDecimal(gLngPostId));
                    lObjPensionArrearDtlsVO.setCreatedDate(gDtCurrDt);
                    lObjPensionArrearDtlsVO.setTrnCounter(1);
                    
                    lstTrnPensionArrearDtlsVO.add(lObjPensionArrearDtlsVO);
                }
            }
        	        	
		} 
        catch (Exception e) 
		{
        	gLogger.error("Error in" + e,e);
            throw(e);
		}
    	
    	return lstTrnPensionArrearDtlsVO;
    }
        
    private void setSessionInfo(Map lMapInput)
    {
        gLngPostId = SessionHelper.getPostId(lMapInput);
        gLngUserId = SessionHelper.getUserId(lMapInput);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
    
}