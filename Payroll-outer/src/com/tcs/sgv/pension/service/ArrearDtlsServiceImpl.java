package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAO;
import com.tcs.sgv.pension.dao.MstPensionerHdrDAOImpl;
import com.tcs.sgv.pension.dao.NewPensionBillDAO;
import com.tcs.sgv.pension.dao.NewPensionBillDAOImpl;
import com.tcs.sgv.pension.dao.RevisionDAO;
import com.tcs.sgv.pension.dao.RevisionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionArrearDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.MstPensionerHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionArrearDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionerRivisionDtls;

/**
 * Arrear Details Srvice Impl.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class ArrearDtlsServiceImpl extends ServiceImpl implements ArrearDtlsService 
{
    /* Global Variable for PostId */
    private Long gLngPostId = null;

    /* Global Variable for UserId */
    private Long gLngUserId = null;

    /* Global Variable for LangId */
    private Long gLngLangId = null;    

    /* Global Variable for Logger Class */
    private final Log gLogger = LogFactory.getLog(getClass());

    /* Global Variable for Current Date */
    private Date gDtCurrDt = null;
    
    /* Global Variable for FinancialyearId */
    long gLngFinYearId = 0;
    
    private ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
    
    /**
     * Getting Arrear Details.
     * 
     * @param inputMap
     * @return ResultObject
     */
    public ResultObject getArrearDtls(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        TrnPensionArrearDtlsDAO lObjPensionArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,servLoc.getSessionFactory());
        List<TrnPensionArrearDtls> lPensionArrearDtlsVoLst = null;
        
        TrnPensionRqstHdrDAO lObjRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        TrnPensionRqstHdr lPensionRqstHdrVo = null;
       
        CommonPensionDAO lObjPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());
        TrnPensionArrearDtls lPensionArrearDtlsVO = null;
          
        String lStrPnsnCode = null;
        String lStrRqstID = null;
        String lStrArrearFlg = bundleConst.getString("CMN.NEW");
        
        List<Integer> WEFrommonth = new ArrayList<Integer>();
        List<Integer> WEFromyear = new ArrayList<Integer>();
        List<Integer> WETomonth = new ArrayList<Integer>();
        List<Integer> WEToyear = new ArrayList<Integer>();
        
        List<Integer> PAYFrommonth = new ArrayList<Integer>();
        List<Integer> PAYFromyear = new ArrayList<Integer>();
        List<Integer> PAYTomonth = new ArrayList<Integer>();
        List<Integer> PAYToyear = new ArrayList<Integer>();
        
        Integer lLastPayedMonth = 0;
        
        try
        {
            setSessionInfo(inputMap);
            
            lStrPnsnCode = StringUtility.getParameter("pnsnarrear_PnsnCode",request);
            lStrRqstID = StringUtility.getParameter("pnsnarrear_reqstId", request);
            
            lPensionRqstHdrVo = lObjRqstHdrDAO.read(Long.parseLong(lStrRqstID));
            inputMap.put("PensionRqstHdrVo", lPensionRqstHdrVo);
            
            lPensionArrearDtlsVoLst = lObjPensionArrearDtlsDAO.getTrnPensionArrearDtlsVo(lStrPnsnCode);
            
            lLastPayedMonth = lObjPensionDAO.getLastBillCreatedMonth(lStrPnsnCode);
            
            if(lPensionArrearDtlsVoLst != null)
            {
                lStrArrearFlg  = bundleConst.getString("CMN.SAVED");
                for(int i = 0 ; i < lPensionArrearDtlsVoLst.size() ; i++)
                {   
                    lObjPensionArrearDtlsDAO = null;
                    
                    lPensionArrearDtlsVO = (TrnPensionArrearDtls) lPensionArrearDtlsVoLst.get(i);
                    String lStrFld = lPensionArrearDtlsVO.getArrearFieldType().trim();
                    
                    WEFromyear.add(Integer.parseInt(lPensionArrearDtlsVO.getEffectedFromYyyymm().toString().substring(0,4)));
                    WEToyear.add(Integer.parseInt(lPensionArrearDtlsVO.getEffectedToYyyymm().toString().substring(0,4)));
                    if(lPensionArrearDtlsVO.getEffectedFromYyyymm().toString().length() == 5)
                    {
                        WEFrommonth.add(Integer.parseInt(lPensionArrearDtlsVO.getEffectedFromYyyymm().toString().substring(4,5)));
                    }
                    else{
                        WEFrommonth.add(Integer.parseInt(lPensionArrearDtlsVO.getEffectedFromYyyymm().toString().substring(4,6)));
                    }                    
                    
                    if(lPensionArrearDtlsVO.getEffectedToYyyymm().toString().length() == 5)
                    {
                        WETomonth.add(Integer.parseInt(lPensionArrearDtlsVO.getEffectedToYyyymm().toString().substring(4,5)));
                    }
                    else
                    {
                        WETomonth.add(Integer.parseInt(lPensionArrearDtlsVO.getEffectedToYyyymm().toString().substring(4,6)));
                        
                    }
                    
                    PAYFromyear.add(Integer.parseInt(lPensionArrearDtlsVO.getPaymentFromYyyymm().toString().substring(0,4)));
                    if(lPensionArrearDtlsVO.getPaymentFromYyyymm().toString().length() == 5)
                    {
                        PAYFrommonth.add(Integer.parseInt(lPensionArrearDtlsVO.getPaymentFromYyyymm().toString().substring(4,5)));
                    }
                    else
                    {
                        PAYFrommonth.add(Integer.parseInt(lPensionArrearDtlsVO.getPaymentFromYyyymm().toString().substring(4,6)));
                    }
                    PAYToyear.add(Integer.parseInt(lPensionArrearDtlsVO.getPaymentToYyyymm().toString().substring(0,4)));
                    if(lPensionArrearDtlsVO.getPaymentToYyyymm().toString().length() == 5)
                    {
                        PAYTomonth.add(Integer.parseInt(lPensionArrearDtlsVO.getPaymentToYyyymm().toString().substring(4,5)));
                    }
                    else
                    {
                        PAYTomonth.add(Integer.parseInt(lPensionArrearDtlsVO.getPaymentToYyyymm().toString().substring(4,6)));
                    }
                            
                    inputMap.put(""+lStrFld+"WEFromMonth", WEFrommonth);
                    inputMap.put(""+lStrFld+"WEFromYear", WEFromyear);
                    inputMap.put(""+lStrFld+"WEToMonth", WETomonth);
                    inputMap.put(""+lStrFld+"WEToYear", WEToyear);
                    
                    inputMap.put(""+lStrFld+"PAYFromMonth", PAYFrommonth);
                    inputMap.put(""+lStrFld+"PAYFromYear", PAYFromyear);
                    inputMap.put(""+lStrFld+"PAYToMonth", PAYTomonth);
                    inputMap.put(""+lStrFld+"PAYToYear", PAYToyear);

                }
            }
            
            lStrPnsnCode = StringUtility.getParameter("pnsnarrear_PnsnCode",request);
            lStrRqstID = StringUtility.getParameter("pnsnarrear_reqstId", request);
            
            inputMap.put("ArrearPnsnerCode", lStrPnsnCode);
            inputMap.put("ArrearReqstId", lStrRqstID);
            inputMap.put("ArrearFlg", lStrArrearFlg);
            
            inputMap.put("RqstHdrVo", lPensionRqstHdrVo);
            
            inputMap.put("TrnPensionArrearDtlsVoLst", lPensionArrearDtlsVoLst);
            
            inputMap.put("CurrentMonth", new SimpleDateFormat("MM").format(gDtCurrDt));
            inputMap.put("CurrentYear", new SimpleDateFormat("yyyy").format(gDtCurrDt));
            
            if(lLastPayedMonth != 0)
            {
                lLastPayedMonth += ((Integer.parseInt((lLastPayedMonth.toString().substring(4,6)))) == 12 ) ? 89 : 1;
                if(lLastPayedMonth.toString().length() == 5) {
                    inputMap.put("LastPayedMonth", (lLastPayedMonth.toString().substring(4, 5)));
                }
                else{
                    inputMap.put("LastPayedMonth", (lLastPayedMonth.toString().substring(4, 6)));
                }
                inputMap.put("LastPayedYear", (lLastPayedMonth.toString().substring(0, 4)));
            }
            else
            {
                inputMap.put("LastPayedMonth", null);
                inputMap.put("LastPayedYear", null);
            }
            
            // To populate month combo....
            List lObjSgvaMonthMst = new ArrayList();

            CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());

            // Returning VO array...
            lObjSgvaMonthMst = lObjCommonPensionDAO.getSgvaMonthMstVO(gLngLangId.toString());

            if (lObjSgvaMonthMst != null)
            {
                inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
            }

            // To populate year combo....
            List lObjSgvcFinYearMst = new ArrayList();

            // Returning VO array...
            lObjSgvcFinYearMst = lObjCommonPensionDAO.getSgvcFinYearMstVO(gLngLangId.toString());

            if (lObjSgvcFinYearMst != null)
            {
                inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
            }
            
            resObj.setResultValue(inputMap);
            resObj.setViewName("viewArrearDtls");
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
     * Save Arrear Details.
     * 
     * @param inputMap
     * @return ResultObject
     */
    public ResultObject savePensionArrearDtls(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        
        List<TrnPensionArrearDtls> lstTrnPensionArrearDtlsVo = null;
        TrnPensionArrearDtls lObjPensionArrearDtlsVo = null;
        
        TrnPensionArrearDtlsDAO lObjPensionArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,servLoc.getSessionFactory());
            
        StringBuilder lStrGrant = new StringBuilder();
        
        try
        {
            setSessionInfo(inputMap);
            
            lstTrnPensionArrearDtlsVo = (List<TrnPensionArrearDtls>) inputMap.get("TrnPensionArrearDtlsVOLst");
         
            for(int i=0; i < lstTrnPensionArrearDtlsVo.size() ; i++ )
            {
                lObjPensionArrearDtlsVo = null;
                lObjPensionArrearDtlsVo = lstTrnPensionArrearDtlsVo.get(i);
                
                lObjPensionArrearDtlsVo.setPensionArrearDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls",inputMap));
                
                lObjPensionArrearDtlsDAO.create(lObjPensionArrearDtlsVo);
            }
            
            lStrGrant.append(" <ARREARINFO> ");
            lStrGrant.append("      <MSGINFO> ");
            lStrGrant.append("Arrear Details Saved Successfully.");
            lStrGrant.append("      </MSGINFO> ");
            lStrGrant.append(" </ARREARINFO> ");
            
            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
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
    
    /**
     * Calculte TI Amount For Differnced TI Percentage.
     * 
     * @param inputMap
     * @return ResultObject
     */
    public ResultObject getEffectedTIArrearAmt(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");

        String lStrPPCut = bundleConst.getString("CUT.PP");
        String lStrPTCut = bundleConst.getString("CUT.PT");
        String lStrITCut = bundleConst.getString("CUT.IT");
        
        String lStrPnsnCode = null;
        String lStrRqstID = null;
        int WEFYYYYMM = 0;
        int WETYYYYMM = 0;
        int DODYYYYMM = 0;
        int lCommyyyyMM = 0;
        float TIDiff = 0;
        
        int lDaysOfDOD=0;
        int lDaysOfComm = 0;
        int lDaysOfMonth = 0;
        
        int lDPPer = 0;
        Double lDPAmt = 0D;
        Double lPnsnCut = 0D;
        Double lbasicPension = 0D;
        Double lTIDiffAmt = 0D;
        Double lTotalTIDiffAmt = 0D;
        
        Date lDateOfDeath = null;
        Date lCommDate = null;
        
        ArrayList<TrnPensionItCutDtls> lObjPnsnCutVOList = null;
        TrnPensionItCutDtls lObjCutDtlsVo = null;
        
        TrnPensionRqstHdrDAO lObjRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
        
        MstPensionerHdr lObjPensionerHdrVo = null;
        
        StringBuilder lStrGrant = new StringBuilder();

        try
        {
            lStrPnsnCode = StringUtility.getParameter("pnsnarrear_PnsnCode",request);
            lStrRqstID = StringUtility.getParameter("pnsnarrear_reqstId", request);
            WEFYYYYMM = Integer.parseInt(StringUtility.getParameter("WEFYYYYMM", request));
            WETYYYYMM = Integer.parseInt(StringUtility.getParameter("WETYYYYMM", request));
            TIDiff = Float.valueOf(StringUtility.getParameter("TIDiff", request));

            lObjTrnPensionRqstHdrVO =  lObjRqstHdrDAO.read(Long.parseLong(lStrRqstID));
            
            lObjPensionerHdrVo = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,servLoc.getSessionFactory()).getMstPensionerHdrDtls(lStrPnsnCode);
            
            lDateOfDeath = lObjPensionerHdrVo.getDateOfDeath();
            
            lCommDate = lObjTrnPensionRqstHdrVO.getCommensionDate();
            lDaysOfComm = Integer.valueOf(new SimpleDateFormat("dd").format(lCommDate));
            lCommyyyyMM = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lCommDate));
            
            lObjPnsnCutVOList = new NewPensionBillDAOImpl(servLoc.getSessionFactory()).getTrnPensionItCutDtls(lStrPnsnCode);
            
            if(WEFYYYYMM >= lCommyyyyMM)
            {
                for(Integer i=WEFYYYYMM;i<=WETYYYYMM;)
                {
                    lPnsnCut = 0D;
                    
                    for(int j=0;j<lObjPnsnCutVOList.size();j++)
                    {
                        lObjCutDtlsVo = null;
                        lObjCutDtlsVo = lObjPnsnCutVOList.get(j);
                        if(!(lObjCutDtlsVo.getTypeFlag().equalsIgnoreCase(lStrITCut)))
                        {
                            if(lObjCutDtlsVo.getTypeFlag().equalsIgnoreCase(lStrPPCut))
                            {
                                lPnsnCut += Double.valueOf(lObjCutDtlsVo.getAmount().toString());
                            }
                            if(lObjCutDtlsVo.getTypeFlag().equalsIgnoreCase(lStrPTCut))
                            {
                                int lCutFrom = lObjCutDtlsVo.getFromMonth();
                                int lCutTo = lObjCutDtlsVo.getToMonth();
                                
                                if(lCutFrom <= i && i <= lCutTo )
                                {
                                    lPnsnCut += Double.valueOf(lObjCutDtlsVo.getAmount().toString());
                                }                            
                            }
                        }
                    }
                    
                    /// Pension Basic Calculation For Calculate Respected TIAmount For that month.
                    lbasicPension = Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
                    lDPPer = Integer.parseInt(lObjTrnPensionRqstHdrVO.getDpPercent().toString());
                    lDaysOfMonth = getDaysOfMonth(i);
                    
                    if(lDateOfDeath != null && lDateOfDeath.after(lCommDate))
                    {
                        DODYYYYMM = Integer.valueOf(new SimpleDateFormat("yyyyMM").format(lDateOfDeath));
                        lDaysOfDOD = Integer.valueOf(new SimpleDateFormat("dd").format(lDateOfDeath));
                        
                        if(i == DODYYYYMM && i == lCommyyyyMM)
                        {
                            Double lPnsnBasic = Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
                            Double lFPBasic = getFamilyPensionBasic(lObjTrnPensionRqstHdrVO, lObjPensionerHdrVo);
                            
                            lPnsnBasic = ((lDaysOfDOD-(lDaysOfComm-1)) * (lPnsnBasic / lDaysOfMonth)); 
                            lFPBasic = ((lDaysOfMonth-lDaysOfDOD) * (lFPBasic / lDaysOfMonth));
                            
                            lbasicPension = lPnsnBasic + lFPBasic;
                        }
                        if(i == DODYYYYMM && i != lCommyyyyMM)
                        {
                            Double lPnsnBasic = Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
                            Double lFPBasic = getFamilyPensionBasic(lObjTrnPensionRqstHdrVO, lObjPensionerHdrVo);
                            
                            lPnsnBasic = ((lDaysOfDOD) * (lPnsnBasic / lDaysOfMonth)); 
                            lFPBasic = ((lDaysOfMonth-lDaysOfDOD) * (lFPBasic / lDaysOfMonth));
                                                    
                            lbasicPension = lPnsnBasic + lFPBasic;
                        }
                        if(i != DODYYYYMM && i != lCommyyyyMM)
                        {
                            if(i < DODYYYYMM){
                                lbasicPension = Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
                            }
                            else if(i > DODYYYYMM){
                                lbasicPension = getFamilyPensionBasic(lObjTrnPensionRqstHdrVO, lObjPensionerHdrVo);
                            }                        
                        }
                        if(i != DODYYYYMM && i == lCommyyyyMM)
                        {
                            Double lPnsnBasic = Double.valueOf(lObjTrnPensionRqstHdrVO.getBasicPensionAmount().toString());
                            lbasicPension = ((lDaysOfMonth-(lDaysOfComm-1)) * (lPnsnBasic / lDaysOfMonth)); 
                        }
                    }
                    else if(lDateOfDeath != null && lDateOfDeath.before(lCommDate))
                    {
                        if(i == lCommyyyyMM)
                        {
                            Double lFPBasic = getFamilyPensionBasic(lObjTrnPensionRqstHdrVO, lObjPensionerHdrVo);
                            lbasicPension = ((lDaysOfMonth-(lDaysOfComm-1)) * (lFPBasic / lDaysOfMonth));
                        }else{
                            lbasicPension = getFamilyPensionBasic(lObjTrnPensionRqstHdrVO, lObjPensionerHdrVo);
                        }
                    }
                    else
                    {
                        if(i == lCommyyyyMM)
                        {
                            lbasicPension = ((lDaysOfMonth-(lDaysOfComm-1)) * (lbasicPension / lDaysOfMonth));
                        }
                    }
                    
                    lDPAmt = ((lbasicPension - lPnsnCut) * lDPPer) / 100;
                    lTIDiffAmt = (((lbasicPension - lPnsnCut) + Math.round(lDPAmt)) * TIDiff) / 100;
                    
                    lTotalTIDiffAmt += Math.round(lTIDiffAmt);
                    i += ((Integer.parseInt((i.toString().substring(4,6)))) == 12 ) ? 89 : 1;
                }
            }
            
            lStrGrant.append(" <TIARREARINFO> ");
            lStrGrant.append("      <TIDIFFAMT> ");
            lStrGrant.append(Math.round(lTotalTIDiffAmt));
            lStrGrant.append("      </TIDIFFAMT> ");
            lStrGrant.append(" </TIARREARINFO> ");

            String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrGrant.toString()).toString();
            inputMap.put("ajaxKey", lStrResult);
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
    
    private Integer getDaysOfMonth(Integer lYYYYMM) throws Exception
    {
        Integer days=0;
        try
        {
            Integer YYYY = Integer.parseInt(lYYYYMM.toString().substring(0,4));
            Integer MM = Integer.parseInt(lYYYYMM.toString().substring(4,6));
            Calendar cal = new GregorianCalendar(YYYY, (MM-1), 1);
            days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            throw(e);
        } 
        return days;
    }
    
    private Double getFamilyPensionBasic(TrnPensionRqstHdr lTRqstHdr,MstPensionerHdr lMPensionerHdr) throws Exception
    {
        Double lFPBasic = 0D;        
        try
        {
            Date lDateOfDeath = lMPensionerHdr.getDateOfDeath();
            Date lFP1Date = lTRqstHdr.getFp1Date();
            Date lFP2Date = lTRqstHdr.getFp2Date();
                            
            if( lDateOfDeath != null && lDateOfDeath.toString().length() > 0 )
            {
                if(lDateOfDeath.before(lFP1Date) || lDateOfDeath.equals(lFP1Date)){
                    lFPBasic = Double.parseDouble(lTRqstHdr.getFp1Amount().toString());
                }
                else if(lDateOfDeath.after(lFP2Date) || lDateOfDeath.equals(lFP2Date)){
                    lFPBasic = Double.parseDouble(lTRqstHdr.getFp2Amount().toString());
                }
            }
            
        }
        catch (Exception e)
        {
            gLogger.error(" Error is : " + e, e);
            throw(e);
        }       
        return lFPBasic;
    }
    
    /**
     * Calculate And Insert Arrear Details For Reviced Case.
     * 
     * @param inputMap
     * @return ResultObject
     */
    public ResultObject addArrearDtlsForRevisedCase(Map inputMap)
    {
        ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
        ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
        HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        String lStrPPONo = null;
        String lStrPnsnerCode = null;
        String lStrNEWStatus = bundleConst.getString("CMN.NEW");
        String lStrApprovedStatus = bundleConst.getString("STATUS.APPROVED");
        String lStrPnsnFlg = bundleConst.getString("ARREAR.PENSION");
        String lStrFP1Flg = bundleConst.getString("ARREAR.FP1");
        String lStrFP2Flg = bundleConst.getString("ARREAR.FP2");
        
        float lOldDPPer = 0F;
        float lNewDPPer = 0F;
        
        Double lOldBasicPension = 0D;
        Double lNewBasicPension = 0D;
        Double lOldFP1Pension = 0D;
        Double lNewFP1Pension = 0D;
        Double lOldFP2Pension = 0D;
        Double lNewFP2Pension = 0D;
        Double lOldCVPMonthly = 0D;
        Double lNewCVPMonthly = 0D;
        
        float lDiffDPPer =0F;
        Double lDiffBasicPension =0D;
        Double lDiffFP1Amt =0D;
        Double lDiffFP2Amt =0D;
        
        Double lTotalPnsnCut = 0D;
     
        Double lTempPensionAmt = 0D;
        Double lTempFPAmt = 0D;
                
        Double lTotPensionAmt = 0D;
        Double lTotFP1Amt = 0D;
        Double lTotFP2Amt = 0D;
        
        Double lArrearCVPMonthlyAmt = 0D;
        Double lTotRcvryCVPMonthlyAmt = 0D;
        
        Date lCommDate = null;
        Date lRevisedDate = null;
        Date lPnsnerDOD = null;
        Date lFP1Date = null;
        Date lFP2Date = null;
        
        int lCommYYYYMM = 0;
        int lRevisedYYYYMM = 0;
        int lPnsnerDODYYYYMM =0;
        int lDaysOfDOD =0;
        int lDaysOfComm = 0;
        int lDaysOfMonth = 0;
        Integer lLastPayedMonth = 0;
        
        List<TrnPensionRqstHdr> lTrnPensionRqstHdrLst = null;
        TrnPensionRqstHdr lOLDRqstHdrVO = null;
        TrnPensionRqstHdr lNEWRqstHdrVO = null;
        TrnPensionArrearDtls lPensionArrearDtlsVO = new TrnPensionArrearDtls();
        MstPensionerHdr lPensionerHdrVO = null;
        TrnPensionArrearDtls lAddArrearDtlsVo = null;
        
        CommonPensionDAO lObjCommonPensionDAO = new CommonPensionDAOImpl(servLoc.getSessionFactory());
        NewPensionBillDAO lObjPensionBillDAO = new NewPensionBillDAOImpl(servLoc.getSessionFactory());
        MstPensionerHdrDAO lObjMstPensionerHdrDAO = new MstPensionerHdrDAOImpl(MstPensionerHdr.class,servLoc.getSessionFactory());
        RevisionDAO lObjRevisionDAO = new RevisionDAOImpl(TrnPensionerRivisionDtls.class,servLoc.getSessionFactory());
        TrnPensionArrearDtlsDAO lObjArrearDtlsDAO = new TrnPensionArrearDtlsDAOImpl(TrnPensionArrearDtls.class,servLoc.getSessionFactory());
        TrnPensionRqstHdrDAO lObjRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
        
        try
        {
             setSessionInfo(inputMap);
             lStrPPONo = StringUtility.getParameter("ppoNo", request);
             
             lTrnPensionRqstHdrLst = lObjCommonPensionDAO.getPensionerDtlsDiffFromPpoNo(lStrPPONo);
             
             if(lTrnPensionRqstHdrLst != null && lTrnPensionRqstHdrLst.size() > 0)
             {
                 lStrPnsnerCode = lTrnPensionRqstHdrLst.get(0).getPensionerCode();
                 
                 for(int i=0;i<lTrnPensionRqstHdrLst.size();i++)
                 {
                     if(lTrnPensionRqstHdrLst.get(i).getCaseStatus().equalsIgnoreCase(lStrApprovedStatus))
                     {
                         lOLDRqstHdrVO = lTrnPensionRqstHdrLst.get(i);
                     }
                     else if(lTrnPensionRqstHdrLst.get(i).getCaseStatus().equalsIgnoreCase(lStrNEWStatus))
                     {
                         lNEWRqstHdrVO = lTrnPensionRqstHdrLst.get(i);
                     }
                 }
                 
                 lPensionerHdrVO = lObjMstPensionerHdrDAO.getMstPensionerHdrDtls(lStrPnsnerCode);
                 
                 if(lPensionerHdrVO != null)
                 {
                     lPnsnerDOD = lPensionerHdrVO.getDateOfDeath();
                     if(lPnsnerDOD != null)
                     {
                         lFP1Date = lOLDRqstHdrVO.getFp1Date();
                         lFP2Date = lOLDRqstHdrVO.getFp2Date();
                         lPnsnerDODYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lPnsnerDOD));
                         lDaysOfDOD = Integer.valueOf(new SimpleDateFormat("dd").format(lPnsnerDOD));
                     }
                 }
             }
             
             if(lOLDRqstHdrVO != null)
             {
                 lOldDPPer = Float.parseFloat(lOLDRqstHdrVO.getDpPercent().toString());
                 lOldBasicPension = Double.valueOf(lOLDRqstHdrVO.getBasicPensionAmount().toString());
                 lOldFP1Pension = Double.valueOf(lOLDRqstHdrVO.getFp1Amount().toString());
                 lOldFP2Pension = Double.valueOf(lOLDRqstHdrVO.getFp2Amount().toString());
                 lOldCVPMonthly = Double.valueOf(lOLDRqstHdrVO.getCvpMonthlyAmount().toString());
                 
                 lCommDate = lOLDRqstHdrVO.getCommensionDate();
                 lCommYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lCommDate));
                 lDaysOfComm = Integer.valueOf(new SimpleDateFormat("dd").format(lCommDate));
                 
             }
             
             if(lNEWRqstHdrVO != null)
             {
                 lNewDPPer = Float.parseFloat(lNEWRqstHdrVO.getDpPercent().toString());
                 lNewBasicPension = Double.valueOf(lNEWRqstHdrVO.getBasicPensionAmount().toString());
                 lNewFP1Pension = Double.valueOf(lNEWRqstHdrVO.getFp1Amount().toString());
                 lNewFP2Pension = Double.valueOf(lNEWRqstHdrVO.getFp2Amount().toString());
                 lNewCVPMonthly = Double.valueOf(lNEWRqstHdrVO.getCvpMonthlyAmount().toString());
             }
             
             if(lOldDPPer < lNewDPPer)
             {
                 lDiffDPPer = lNewDPPer - lOldDPPer;
             }
             if(lOldBasicPension < lNewBasicPension)
             {
                 lDiffBasicPension = lNewBasicPension - lOldBasicPension;
             }
             if(lOldFP1Pension < lNewFP1Pension)
             {
                 lDiffFP1Amt = lNewFP1Pension - lOldFP1Pension;
             }
             if(lOldFP2Pension < lNewFP2Pension)
             {
                 lDiffFP2Amt = lNewFP2Pension - lOldFP2Pension;
             }
             
             lLastPayedMonth = lObjCommonPensionDAO.getLastBillCreatedMonth(lStrPnsnerCode);
                          
             lRevisedDate = lObjRevisionDAO.getLastRevisionDate(lStrPnsnerCode);
             lRevisedYYYYMM = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lRevisedDate));

             if(lOldCVPMonthly != 0 && lNewCVPMonthly != 0 && lOldCVPMonthly > lNewCVPMonthly)
             {
                 lArrearCVPMonthlyAmt = lNewCVPMonthly - lOldCVPMonthly;
             }
             
             if(lDiffDPPer > 0 || lDiffBasicPension > 0 || lDiffFP1Amt > 0 || lDiffFP2Amt > 0)
             {
                 for(Integer i = lCommYYYYMM;i<=lRevisedYYYYMM;)
                 {
                     lTotalPnsnCut = lObjPensionBillDAO.getTotalPensionCutForMonth(lStrPnsnerCode, i);
                     lDaysOfMonth = getDaysOfMonth(i); 
                     lTempPensionAmt = 0D;
                     lTempFPAmt = 0D;
                     
                     if(lPnsnerDOD != null && lPnsnerDOD.before(lRevisedDate) && lPnsnerDOD.after(lCommDate))
                     {
                         if(lPnsnerDODYYYYMM == i && lPnsnerDODYYYYMM == lCommYYYYMM)
                         {
                             // Basic Pension
                             lTempPensionAmt = ((lOldBasicPension - lTotalPnsnCut) * lDiffDPPer / 100);
                             lTempPensionAmt += lDiffBasicPension;
                             lTempPensionAmt += lArrearCVPMonthlyAmt;
                             
                             lTotPensionAmt += ((lDaysOfDOD-(lDaysOfComm-1)) * (lTempPensionAmt / lDaysOfMonth));
                             
                             // Family Pension
                             if(lPnsnerDOD.before(lFP1Date) || lPnsnerDOD.equals(lFP1Date))
                             {
                                 lTempFPAmt = ((lOldFP1Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP1Amt;
                                 
                                 lTotFP1Amt += ((lDaysOfMonth-lDaysOfDOD) * (lTempFPAmt / lDaysOfMonth));                              
                             }
                             else if(lPnsnerDOD.before(lFP2Date) || lPnsnerDOD.equals(lFP2Date))
                             {
                                 lTempFPAmt = ((lOldFP2Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP2Amt;
                                 
                                 lTotFP2Amt += ((lDaysOfMonth-lDaysOfDOD) * (lTempFPAmt / lDaysOfMonth));   
                             }
                         }
                         else if(lPnsnerDODYYYYMM == i && lPnsnerDODYYYYMM != lCommYYYYMM)
                         {
                             // Basic Pension
                             lTempPensionAmt = ((lOldBasicPension - lTotalPnsnCut) * lDiffDPPer / 100);
                             lTempPensionAmt += lDiffBasicPension;
                             lTempPensionAmt += lArrearCVPMonthlyAmt;
                             
                             lTotPensionAmt += ((lDaysOfDOD) * (lTempPensionAmt / lDaysOfMonth)); 
                             
                             // Family Pension
                             if(lPnsnerDOD.before(lFP1Date) || lPnsnerDOD.equals(lFP1Date))
                             {
                                 lTempFPAmt = ((lOldFP1Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP1Amt;
                                 
                                 lTotFP1Amt += ((lDaysOfMonth-lDaysOfDOD) * (lTempFPAmt / lDaysOfMonth));                              
                             }
                             else if(lPnsnerDOD.before(lFP2Date) || lPnsnerDOD.equals(lFP2Date))
                             {
                                 lTempFPAmt = ((lOldFP2Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP2Amt;
                                 
                                 lTotFP2Amt += ((lDaysOfMonth-lDaysOfDOD) * (lTempFPAmt / lDaysOfMonth));  
                             }
                         }
                         else if(lPnsnerDODYYYYMM != i && lPnsnerDODYYYYMM != lCommYYYYMM)
                         {
                             if(i < lPnsnerDODYYYYMM)
                             {
                                 // Basic Pension
                                 lTempPensionAmt = ((lOldBasicPension - lTotalPnsnCut) * lDiffDPPer / 100);
                                 lTempPensionAmt += lDiffBasicPension;
                                 lTempPensionAmt += lArrearCVPMonthlyAmt;
                                 
                                 lTotPensionAmt += lTempPensionAmt; 
                             }
                             else if(i > lPnsnerDODYYYYMM)
                             {
                                 // Family Pension
                                 if(lPnsnerDOD.before(lFP1Date) || lPnsnerDOD.equals(lFP1Date))
                                 {
                                     lTempFPAmt = ((lOldFP1Pension) * lDiffDPPer / 100);
                                     lTempFPAmt += lDiffFP1Amt;
                                     
                                     lTotFP1Amt += lTempFPAmt;                              
                                 }
                                 else if(lPnsnerDOD.before(lFP2Date) || lPnsnerDOD.equals(lFP2Date))
                                 {
                                     lTempFPAmt = ((lOldFP2Pension) * lDiffDPPer / 100);
                                     lTempFPAmt += lDiffFP2Amt;
                                     
                                     lTotFP2Amt += lTempFPAmt;  
                                 }
                             }
                         }
                         else if(lPnsnerDODYYYYMM != i && i == lCommYYYYMM)
                         {
                             // Basic Pension
                             lTempPensionAmt = ((lOldBasicPension - lTotalPnsnCut) * lDiffDPPer / 100);
                             lTempPensionAmt += lDiffBasicPension;
                             lTempPensionAmt += lArrearCVPMonthlyAmt;
                             
                             lTotPensionAmt += ((lDaysOfMonth-(lDaysOfComm-1)) * (lTempPensionAmt / lDaysOfMonth));
                         }
                     }
                     else if(lPnsnerDOD != null && lPnsnerDOD.before(lCommDate))
                     {
                         if(i==lCommYYYYMM)
                         {
                             // Family Pension
                             if(lPnsnerDOD.before(lFP1Date) || lPnsnerDOD.equals(lFP1Date))
                             {
                                 lTempFPAmt = ((lOldFP1Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP1Amt;
                                 
                                 lTotFP1Amt += ((lDaysOfMonth-(lDaysOfComm-1)) * (lTempFPAmt / lDaysOfMonth));;                              
                             }
                             else if(lPnsnerDOD.before(lFP2Date) || lPnsnerDOD.equals(lFP2Date))
                             {
                                 lTempFPAmt = ((lOldFP2Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP2Amt;
                                 
                                 lTotFP2Amt += ((lDaysOfMonth-(lDaysOfComm-1)) * (lTempFPAmt / lDaysOfMonth));;  
                             }
                         }
                         else
                         {
                             // Family Pension
                             if(lPnsnerDOD.before(lFP1Date) || lPnsnerDOD.equals(lFP1Date))
                             {
                                 lTempFPAmt = ((lOldFP1Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP1Amt;
                                 
                                 lTotFP1Amt += lTempFPAmt;                              
                             }
                             else if(lPnsnerDOD.before(lFP2Date) || lPnsnerDOD.equals(lFP2Date))
                             {
                                 lTempFPAmt = ((lOldFP2Pension) * lDiffDPPer / 100);
                                 lTempFPAmt += lDiffFP2Amt;
                                 
                                 lTotFP2Amt += lTempFPAmt;  
                             }
                         }
                     }
                     else
                     {
                         if(i==lCommYYYYMM)
                         {
                             // Basic Pension
                             lTempPensionAmt = ((lOldBasicPension - lTotalPnsnCut) * lDiffDPPer / 100);
                             lTempPensionAmt += lDiffBasicPension;
                             lTempPensionAmt += lArrearCVPMonthlyAmt;
                             
                             lTotPensionAmt += ((lDaysOfMonth -  (lDaysOfComm-1)) * (lTempPensionAmt / lDaysOfMonth));
                         }
                         else
                         {
                             // Basic Pension
                             lTempPensionAmt = ((lOldBasicPension - lTotalPnsnCut) * lDiffDPPer / 100);
                             lTempPensionAmt += lDiffBasicPension;
                             lTempPensionAmt += lArrearCVPMonthlyAmt;
                             
                             lTotPensionAmt += lTempPensionAmt;
                         }
                     }
                     
                     if(lOldCVPMonthly != 0 && lNewCVPMonthly != 0 && lOldCVPMonthly < lNewCVPMonthly)
                     {
                         lTotRcvryCVPMonthlyAmt = lOldCVPMonthly - lNewCVPMonthly;
                     }
                     
                     i += ((Integer.parseInt((i.toString().substring(4,6)))) == 12 ) ? 89 : 1;
                 }
             }
             
             lLastPayedMonth += ((Integer.parseInt((lLastPayedMonth.toString().substring(4,6)))) == 12 ) ? 89 : 1;
             
             lPensionArrearDtlsVO.setPensionRequestId(lNEWRqstHdrVO.getPensionRequestId());
             lPensionArrearDtlsVO.setPensionerCode(lStrPnsnerCode);
             lPensionArrearDtlsVO.setEffectedFromYyyymm(lCommYYYYMM);
             lPensionArrearDtlsVO.setEffectedToYyyymm(lRevisedYYYYMM);
             lPensionArrearDtlsVO.setPaymentFromYyyymm(lLastPayedMonth);
             lPensionArrearDtlsVO.setPaymentToYyyymm(lLastPayedMonth);
             lPensionArrearDtlsVO.setCreatedDate(gDtCurrDt);
             lPensionArrearDtlsVO.setCreatedPostId(new BigDecimal(gLngPostId));
             lPensionArrearDtlsVO.setCreatedUserId(new BigDecimal(gLngUserId));
             
             if(lTotPensionAmt != 0)
             {
                 lAddArrearDtlsVo = new TrnPensionArrearDtls();
                 lAddArrearDtlsVo = lPensionArrearDtlsVO;
                 
                 lAddArrearDtlsVo.setArrearFieldType(lStrPnsnFlg);
                 lAddArrearDtlsVo.setOldAmountPercentage(new BigDecimal(lOldBasicPension));
                 lAddArrearDtlsVo.setNewAmountPercentage(new BigDecimal(lNewBasicPension));
                 lAddArrearDtlsVo.setTotalDifferenceAmt(new BigDecimal(Math.round(lTotPensionAmt)));
                 lAddArrearDtlsVo.setDifferenceAmount(new BigDecimal(Math.round(lTotPensionAmt)));
                 
                 lAddArrearDtlsVo.setPensionArrearDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls",inputMap));
                 lObjArrearDtlsDAO.create(lAddArrearDtlsVo);                 
             }
             
             if(lTotFP1Amt != 0)
             {
                 lAddArrearDtlsVo = new TrnPensionArrearDtls();
                 lAddArrearDtlsVo = lPensionArrearDtlsVO;
                 
                 lAddArrearDtlsVo.setArrearFieldType(lStrFP1Flg);
                 lAddArrearDtlsVo.setOldAmountPercentage(new BigDecimal(lOldFP1Pension));
                 lAddArrearDtlsVo.setNewAmountPercentage(new BigDecimal(lNewFP1Pension));
                 lAddArrearDtlsVo.setTotalDifferenceAmt(new BigDecimal(Math.round(lTotFP1Amt)));
                 lAddArrearDtlsVo.setDifferenceAmount(new BigDecimal(Math.round(lTotFP1Amt)));
                 
                 lAddArrearDtlsVo.setPensionArrearDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls",inputMap));
                 lObjArrearDtlsDAO.create(lAddArrearDtlsVo);                 
             }
             
             if(lTotFP2Amt != 0)
             {
                 lAddArrearDtlsVo = new TrnPensionArrearDtls();
                 lAddArrearDtlsVo = lPensionArrearDtlsVO;
                 
                 lAddArrearDtlsVo.setArrearFieldType(lStrFP2Flg);
                 lAddArrearDtlsVo.setOldAmountPercentage(new BigDecimal(lOldFP2Pension));
                 lAddArrearDtlsVo.setNewAmountPercentage(new BigDecimal(lNewFP2Pension));
                 lAddArrearDtlsVo.setTotalDifferenceAmt(new BigDecimal(Math.round(lTotFP2Amt)));
                 lAddArrearDtlsVo.setDifferenceAmount(new BigDecimal(Math.round(lTotFP2Amt)));
                 
                 lAddArrearDtlsVo.setPensionArrearDtlsId(IFMSCommonServiceImpl.getNextSeqNum("trn_pension_arrear_dtls",inputMap));
                 lObjArrearDtlsDAO.create(lAddArrearDtlsVo);                 
             }            
             
             if(lTotRcvryCVPMonthlyAmt != 0)
             {
                 lNEWRqstHdrVO.setTotalRecovery(new BigDecimal(Math.round(lTotRcvryCVPMonthlyAmt)));
                 lObjRqstHdrDAO.update(lNEWRqstHdrVO);
             }
             
             resObj.setResultValue(inputMap);
             resObj.setResultCode(ErrorConstants.SUCCESS);
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
    
    private void setSessionInfo(Map inputMap)
    {
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
        
        gLngLangId = SessionHelper.getLangId(inputMap);        
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(request);
        //gLngFinYearId = SessionHelper.getFinYrId(inputMap);
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
}
