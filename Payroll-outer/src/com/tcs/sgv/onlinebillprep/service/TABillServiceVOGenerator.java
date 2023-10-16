/**
 * package : com.tcs.sgv.onlinebillprep.service
 * 
 * @verion : 1.0
 * @author : Keyur Shah, Nirav Bumia *
 */
package com.tcs.sgv.onlinebillprep.service;

import static com.tcs.sgv.onlinebillprep.util.OnlineBillUtil.requestArraySetter;
import static com.tcs.sgv.onlinebillprep.util.OnlineBillUtil.requestSetter;

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

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.onlinebillprep.dao.TrnTablAmtDtlsDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnTablAmtDtlsDAOImpl;
import com.tcs.sgv.onlinebillprep.dao.TrnTablHdrDAO;
import com.tcs.sgv.onlinebillprep.dao.TrnTablHdrDAOImpl;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablAmtDtls;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablHdr;
import com.tcs.sgv.onlinebillprep.valueobject.TrnTablTrvlDtls;

/**
 * TA Bill Specific VO Generator.
 * 
 * @author Keyur Shah, Nirav Bumia
 * @version 1.0
 */
public class TABillServiceVOGenerator extends ServiceImpl implements VOGeneratorService
{
	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;
    
    /* Global Variable for Current Date */
    Date gDtCurrDt = null;

    /* Global Variable for Logger Class */
    Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for ResourceBundle */
    private static ResourceBundle bundleConst = ResourceBundle
            .getBundle("resources/onlinebillprep/OnlineBillConstants");
    
    /**
     * Generates VO for insertion of TA Bill Data
     * 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject generateMap(Map lMapInput)
    {
        gLogger.info("Inside generateMap() method of TABillServiceVOGenerator Class ");
        
        ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        ServiceLocator srvcLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        TrnTablHdr lObjTrnTablHdr = null;
        TrnTablAmtDtls lObjTrnTablAmtDtls = null;
        List<TrnTablTrvlDtls> lLstTrnTablTrvlDtls = new ArrayList<TrnTablTrvlDtls>();
        
        try
        {
            HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
            
            setSessionInfo(lMapInput);
            
            lObjTrnTablHdr = generateTablHdrVO(lMapInput);
            gLogger.info("Value of VOGEN TrnTablHdr  = " + lObjTrnTablHdr.getEmpName());
            lMapInput.put("TrnTablHdrId", lObjTrnTablHdr.getTrnTablHdrId());
            
            lObjTrnTablAmtDtls = generateTablAmtDtlsVO(lMapInput);
            
            lLstTrnTablTrvlDtls = generateTablTrvlDtlsVOList(lMapInput);
            
            lMapInput.put("TrnTablHdr" , lObjTrnTablHdr);
            lMapInput.put("TrnTablAmtDtls" , lObjTrnTablAmtDtls);
            lMapInput.put("TrnTablTrvlDtlsLst" , lLstTrnTablTrvlDtls);
        }
        catch(Exception e)
        {   
            gLogger.error("Exception occurred in generateMap() method of TABillServiceVOGenerator Class " + e,e);           
        }
        
        objRes.setResultValue(lMapInput);
        
        gLogger.info("Outof generateMap() method of TABillServiceVOGenerator Class ");
        return objRes;
    }
    
    
    /**
     * Generates TablHdr VO. 
     * 
     * @param Map:inputMap
     * @return TrnTablHdr
     */
    private TrnTablHdr generateTablHdrVO(Map lMapInput)
    {        
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        
        TrnTablHdr lObjTrnTablHdr = new TrnTablHdr();
        TrnTablHdrDAO lObjTrnTablHdrDao = new TrnTablHdrDAOImpl(TrnTablHdr.class, servLoc.getSessionFactory());
        
        String empPay = requestSetter("empPay",request);
        String empPta = requestSetter("empPta",request);
        String empCa = requestSetter("empCa",request);
        String cmbForMonth = requestSetter("cmbForMonth", request);
        String empDsg = requestSetter("txtEmpDsg", request);
        String empName = requestSetter("txtEmpName", request);
        String hdQtr = requestSetter("headQtr", request);
        String deptName = requestSetter("txtEmpEstblsmnt", request);
        String lStrBillNo = requestSetter("hidBillNo", request);
        Long lLngNewBillNo = (Long)lMapInput.get("billNo");
        String lStrTrnTablHdrId = requestSetter("hidTrnTablHdrId", request);
        Long lLngTrnTablHdrId = null;
        
        try
        {
        
        if (lStrBillNo.trim().length() > 0)     //update
        {
            lLngTrnTablHdrId = Long.parseLong(lStrTrnTablHdrId);
            lObjTrnTablHdr = lObjTrnTablHdrDao.read(lLngTrnTablHdrId);
            lObjTrnTablHdr.setBillNo(Long.parseLong(lStrBillNo));
            lObjTrnTablHdr.setUpdatedDate(gDtCurrDt);
            lObjTrnTablHdr.setUpdatedPostId(gLngPostId);
            lObjTrnTablHdr.setUpdatedUserId(gLngUserId);
        }
        else                                    //create
        {
            lObjTrnTablHdr.setCreatedUserId(gLngUserId);
            lObjTrnTablHdr.setCreatedPostId(gLngPostId);
            lObjTrnTablHdr.setCreatedDate(gDtCurrDt);
            lObjTrnTablHdr.setBillNo(lLngNewBillNo);
            lObjTrnTablHdr.setTrnCounter(1); 
        }
        
        lObjTrnTablHdr.setTrnTablHdrId(lLngTrnTablHdrId);
                
        if(empPay.trim().length() > 0)
            lObjTrnTablHdr.setEmpPay(new BigDecimal(empPay));
        
        if(empPta.trim().length() > 0)
            lObjTrnTablHdr.setEmpPta(new BigDecimal(empPta));
        
        if(empCa.trim().length() > 0)
            lObjTrnTablHdr.setEmpCa(new BigDecimal(empCa));
        
        lObjTrnTablHdr.setMonthCode(cmbForMonth);        
        lObjTrnTablHdr.setLocationCode(gStrLocationCode);
        lObjTrnTablHdr.setDbId(gLngDBId);        
        lObjTrnTablHdr.setEmpDesgn(empDsg);
        lObjTrnTablHdr.setEmpName(empName);
        lObjTrnTablHdr.setHeadQtr(hdQtr);
        lObjTrnTablHdr.setDeptName(deptName);
       }
       catch(Exception e)
       {           
           gLogger.error("Error occurred in method generateTablHdrVO of Class TABillServiceVOGenerator: " + e + "\n");
       }
        return lObjTrnTablHdr;        
    }
    
    
    /**
     * Generates TablAmtDtls VO.
     * 
     * @param Map:inputMap
     * @return TrnTablAmtDtls
     */
    private TrnTablAmtDtls generateTablAmtDtlsVO(Map lMapInput)
    {
        ServiceLocator servLoc = (ServiceLocator) lMapInput.get("serviceLocator");
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        
        TrnTablAmtDtls lObjTrnTablAmtDtls = new TrnTablAmtDtls();        
        TrnTablAmtDtlsDAO lObjTrnTablAmtDtlsDao = new TrnTablAmtDtlsDAOImpl(TrnTablAmtDtls.class, servLoc.getSessionFactory());
        
        String lStrBillNo = requestSetter("hidBillNo", request);
        Long lLngTrnTablAmtDtlsId = null;
        String incdntlAmt = requestSetter("incExp", request);
        String grossTotal = requestSetter("grossTotal", request);
        String passedAmt = requestSetter("passedAmt", request);
        String netAmt = requestSetter("netAmt", request);
        String rlwStmFare = requestSetter("rlwStmFare", request);
        String rdTrvlChrgs = requestSetter("rdTrvlChrgs", request);
        String ttlDA = requestSetter("daysClaimed", request);
        String sancAmt = requestSetter("sancAmt", request);
        String taAdv = requestSetter("taAdv", request);
        String ptaDays = requestSetter("ptaDays", request);
        String pta = requestSetter("pta", request);
        String lStrTrnTablAmtDtlsId = requestSetter("hidTrnTablAmtDtlsId", request);
        
        String lStrPassedDate = requestSetter("txtFrmDt",request);
        
        try
        {
            if (lStrBillNo.trim().length() > 0)
            {
                lLngTrnTablAmtDtlsId = Long.parseLong(lStrTrnTablAmtDtlsId);
                lObjTrnTablAmtDtls = lObjTrnTablAmtDtlsDao.read(lLngTrnTablAmtDtlsId);
                lObjTrnTablAmtDtls.setUpdatedDate(gDtCurrDt);
                lObjTrnTablAmtDtls.setUpdatedPostId(gLngPostId);
                lObjTrnTablAmtDtls.setUpdatedUserId(gLngUserId);
            }
            else
            {
                lObjTrnTablAmtDtls.setCreatedUserId(gLngUserId);
                lObjTrnTablAmtDtls.setCreatedPostId(gLngPostId);
                lObjTrnTablAmtDtls.setCreatedDate(gDtCurrDt);
                lObjTrnTablAmtDtls.setTrnCounter(1);
            }  
            
        lObjTrnTablAmtDtls.setTrnTablAmtDtlsId(lLngTrnTablAmtDtlsId);
        lObjTrnTablAmtDtls.setAttachId(1L);
        
        //lObjTrnTablAmtDtls.setBillPassedDate(gDtCurrDt);
        
        try 
        {
            if(lStrPassedDate.length() > 0)
            {
                lObjTrnTablAmtDtls.setBillPassedDate(new SimpleDateFormat("dd/MM/yyyy").parse(lStrPassedDate));
            } 
            else 
            {
                lObjTrnTablAmtDtls.setBillPassedDate(gDtCurrDt);
            }            
        } 
        catch(Exception ex)
        {
            gLogger.error("Exception occurred in generateTablTrvlDtlsVOList() method of TABillServiceVOGenerator Class " +
                    " while setting Bill Passed Date" + ex);
        }
        lObjTrnTablAmtDtls.setChqOrder(requestSetter("txtChqOrder", request));
        //lObjTrnTablAmtDtls.setRemarks(requestSetter("txtareaRemarks", request));
        
        if(incdntlAmt.length() > 0)
            lObjTrnTablAmtDtls.setIncdntlExp(new BigDecimal(incdntlAmt));
        
        if(grossTotal.length() > 0)
            lObjTrnTablAmtDtls.setGrossTotal(new BigDecimal(grossTotal));
        
        if(passedAmt.length() > 0)
            lObjTrnTablAmtDtls.setBillPassedAmt(new BigDecimal(passedAmt));
        
        if(netAmt.length() > 0)
            lObjTrnTablAmtDtls.setNetClaimed(new BigDecimal(netAmt));
        
        if(rlwStmFare.length() > 0)
            lObjTrnTablAmtDtls.setRlwStmFare(new BigDecimal(rlwStmFare));
        
        lObjTrnTablAmtDtls.setRdChrgsPsperkm(getRdTrvlChrgs(request));
        
        if(rdTrvlChrgs.length() > 0)
            lObjTrnTablAmtDtls.setRdTrlFare(new BigDecimal(rdTrvlChrgs));
        
        if(ttlDA.length() > 0)
            lObjTrnTablAmtDtls.setTtlDaClmed(new BigDecimal(ttlDA));
        
        if(sancAmt.length() > 0)
            lObjTrnTablAmtDtls.setSnctndAmt(new BigDecimal(sancAmt));
        
        if(taAdv.length() > 0)
            lObjTrnTablAmtDtls.setDedTaAdv(new BigDecimal(taAdv));
        
        if(ptaDays.length() > 0)
            lObjTrnTablAmtDtls.setDedPtaDays(Long.valueOf(ptaDays));
        
        if(pta.length() > 0)
            lObjTrnTablAmtDtls.setDedPta(new BigDecimal(pta));
        
        lObjTrnTablAmtDtls.setLocationCode(gStrLocationCode);
        lObjTrnTablAmtDtls.setDbId(gLngDBId);
        }
        catch(Exception e)
        {
            gLogger.error("Error occurred in method generateTablAmtDtlsVO of Class TABillServiceVOGenerator:" + e + "\n");
        }
        return lObjTrnTablAmtDtls;
    }
    
    
    /**
     * Generates List of TablTrvlDtlsVOList VOs.
     * 
     * @param Map:inputMap
     * @return List<TrnTablTrvlDtls>
     */
    private List<TrnTablTrvlDtls> generateTablTrvlDtlsVOList(Map lMapInput)
    {
        
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        String lStrBillNo = requestSetter("hidBillNo", request);
        
        List<TrnTablTrvlDtls> lLstTrnTablTrvlDtls = new ArrayList<TrnTablTrvlDtls>();
        
        TrnTablTrvlDtls lObjTablTrvlDtls = null;        
        String[] lStrdepStation = null;
        String lStrDepDate = null;
        String[] lStrDepHour = null;        
        String[] lStrarrStation = null;
        String lStrArrDate = null;
        String[] lStrArrHour = null;        
        String[] lStrtrvlMode = null;        
        String[] lStrFareClass = null;
        String[] lStrFareNumber = null;
        String[] lStrFareAmt = null;        
        String[] lStrAdmsblOrdRate = null;
        String[] lStrAdmsblOthRate = null;
        String[] lStrAdmsblDa = null;
        String[] lStrNumOfDaysClmed = null;        
        String[] lStrPurpose = null;
        String[] lStrTotalAmt = null;
        String[] lStrRemarks = null;
        short orderCount = 0;
        String [] lStrTrnTablTrvlDtlsId = null;
        
        try
        {           
            lStrdepStation = requestArraySetter("depStation",request);
            //lStrDepDate = requestArraySetter("depDate",request);
            lStrDepHour = requestArraySetter("depHour",request);
            lStrarrStation = requestArraySetter("arrStation",request);
            //lStrArrDate = requestArraySetter("arrDate",request);
            lStrArrHour = requestArraySetter("arrHour",request);
            lStrtrvlMode = requestArraySetter("trvlMode",request);
            lStrFareClass = requestArraySetter("fareClass",request);
            lStrFareNumber = requestArraySetter("fareNumber",request);
            lStrFareAmt = requestArraySetter("fareAmt",request);
            lStrAdmsblOrdRate = requestArraySetter("admsblOrdRate",request);
            lStrAdmsblOthRate = requestArraySetter("admsblOthRate",request);
            lStrAdmsblDa = requestArraySetter("admsblDa",request);
            lStrNumOfDaysClmed = requestArraySetter("numOfDaysClmed",request);
            lStrPurpose = requestArraySetter("purpose",request);
            lStrTotalAmt = requestArraySetter("totalAmt",request);
            lStrRemarks = requestArraySetter("remarks",request);
                       
            for(int i=0; i<lStrdepStation.length; i++)
            {	 
                lObjTablTrvlDtls = new TrnTablTrvlDtls();
               
                // Date Conversion added by Sagar... Start
                
                try {
                	lStrDepDate = requestSetter("depDate["+(i+1)+"]",request);
                	lObjTablTrvlDtls.setDepDate(new SimpleDateFormat("dd/MM/yyyy").parse(lStrDepDate));
                } 
                catch(Exception ex){
                	gLogger.error("Exception occurred in generateTablTrvlDtlsVOList() method of TABillServiceVOGenerator Class " +
                            " while setting Departure Date" + ex);
                }
                
                try {
                	lStrArrDate = requestSetter("arrDate["+(i+1)+"]",request);
                	lObjTablTrvlDtls.setArrDate(new SimpleDateFormat("dd/MM/yyyy").parse(lStrArrDate));
                } 
                catch(Exception ex){
                	gLogger.error("Exception occurred in generateTablTrvlDtlsVOList() method of TABillServiceVOGenerator Class " +
                            " while setting Arrival Date" + ex);
                }
                
                // Date Conversion added by Sagar... End
                
                lObjTablTrvlDtls.setOrderNo(orderCount);                
                lObjTablTrvlDtls.setDepStation((lStrdepStation[i]));
                lObjTablTrvlDtls.setDepHour(lStrDepHour[i]);                
                lObjTablTrvlDtls.setArrStation(lStrarrStation[i]);
                lObjTablTrvlDtls.setArrHour(lStrArrHour[i]);                
                lObjTablTrvlDtls.setTrvlMode((lStrtrvlMode[i]));
                lObjTablTrvlDtls.setFareClass(lStrFareClass[i]);
                
                if(lStrFareNumber[i].length() > 0)
                    lObjTablTrvlDtls.setFareNumber(Long.parseLong(lStrFareNumber[i]));
                
                if(lStrFareAmt[i].length() > 0)
                {
                    lObjTablTrvlDtls.setFareAmt(new BigDecimal(lStrFareAmt[i])); 
                }
                else
                {
                    lObjTablTrvlDtls.setFareAmt(new BigDecimal(0.0));
                }                    
                
                if(lStrAdmsblOrdRate[i].length() > 0)
                {
                    lObjTablTrvlDtls.setAdmsblOrdRate(new BigDecimal(lStrAdmsblOrdRate[i]));
                }                    
                else
                {
                    lObjTablTrvlDtls.setAdmsblOrdRate(new BigDecimal(0.0));
                }
                
                if(lStrAdmsblOthRate[i].length() > 0)
                {
                    lObjTablTrvlDtls.setAdmsblOthRate(new BigDecimal(lStrAdmsblOthRate[i]));
                }                    
                else
                {
                    lObjTablTrvlDtls.setAdmsblOthRate(new BigDecimal(0.0));
                }
                
                if(lStrAdmsblDa[i].length() > 0)
                {                    
                    lObjTablTrvlDtls.setAdmsblDa(new BigDecimal(lStrAdmsblDa[i])); 
                }                    
                else
                {
                    lObjTablTrvlDtls.setAdmsblDa(new BigDecimal(0.0));
                }
                
                if(lStrNumOfDaysClmed[i].length() > 0)
                    lObjTablTrvlDtls.setNumOfDaysClmed(Long.valueOf(lStrNumOfDaysClmed[i]));   
                
                lObjTablTrvlDtls.setPurpose(lStrPurpose[i]);
                
                if(lStrTotalAmt[i].length() > 0)
                    lObjTablTrvlDtls.setTotalAmt(new BigDecimal(lStrTotalAmt[i]));
                lObjTablTrvlDtls.setRemarks(lStrRemarks[i]);
                
                lObjTablTrvlDtls.setLocationCode(gStrLocationCode);
                lObjTablTrvlDtls.setDbId(gLngDBId);
                
                lObjTablTrvlDtls.setCreatedUserId(gLngUserId);
                lObjTablTrvlDtls.setCreatedPostId(gLngPostId);
                lObjTablTrvlDtls.setCreatedDate(gDtCurrDt);
                lObjTablTrvlDtls.setTrnCounter(1);
               
                lLstTrnTablTrvlDtls.add(lObjTablTrvlDtls);
            }
        }
        catch(Exception e)
        {
            gLogger.error("Error occurred in method generateTablRqstVOList of Class TABillServiceVOGenerator:" + e + "\n");
        }
        
        return lLstTrnTablTrvlDtls;
    }
    

    private void setSessionInfo(Map lMapInput)
    {
        HttpServletRequest request = (HttpServletRequest) lMapInput.get("requestObj");
        gLngLangId = SessionHelper.getLangId(lMapInput);
        gLngEmpId = SessionHelper.getEmpId(lMapInput);
        gLngPostId = SessionHelper.getPostId(lMapInput);
        gLngUserId = SessionHelper.getUserId(request);
        gStrLocationCode = SessionHelper.getLocationCode(lMapInput);
        gLngDBId = SessionHelper.getDbId(lMapInput);
        gDtCurrDt = new Date();
    }
    
    /**
     * Gets Travel Charges.
     * 
     * @param HttpServletRequest request.
     * @return BigDecimal
     */
    private BigDecimal getRdTrvlChrgs(HttpServletRequest request)
    {
        String lStrRdTrvlChrgsPs = requestSetter("rdTrvlChrgsPs", request);
        String lStrRdTrvlChrgsKm = requestSetter("rdTrvlChrgsKm", request);
        BigDecimal lBDRdTrvlChrgsPs = null;
        BigDecimal lBDRdTrvlChrgsKm = null;
        BigDecimal lBDPsPerKm = null;
        BigDecimal lBDZero = new BigDecimal(0.0);
        
        if(lStrRdTrvlChrgsPs.length() > 0)
        {
            lBDRdTrvlChrgsPs = new BigDecimal(lStrRdTrvlChrgsPs);
        }
        
        if(lStrRdTrvlChrgsKm.length() > 0)
        {
            lBDRdTrvlChrgsKm = new BigDecimal(lStrRdTrvlChrgsKm);
        }
        
        if(lBDRdTrvlChrgsPs != null && lBDRdTrvlChrgsKm != null && lBDRdTrvlChrgsKm != lBDZero)
        {
            lBDPsPerKm = lBDRdTrvlChrgsPs.divide(lBDRdTrvlChrgsKm);
        }
        return lBDPsPerKm;
    }
}
