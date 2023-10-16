package com.tcs.sgv.pension.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.BillEdpVO;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.BudgetDtlsDAO;
import com.tcs.sgv.pension.dao.BudgetDtlsDAOImpl;
import com.tcs.sgv.pension.valueobject.RltPensionHeadcodeChargable;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * Pension Bills Specific Budget Dtls Srvice Impl.
 * 
 * @author Sagar Patel
 * @version 1.0
 */
public class BudgetDtlsServiceImpl extends ServiceImpl implements BudgetDtlsService {

	   
    /* Global Variable for Logger Class */
    private final Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for Date */
     private Date gCurDate = null;
    /**
     * 
     * @param objectArgs
     * @return ResultObject
     */
    public ResultObject getEdpDtlsByBillType(Map inputMap) 
    {
      ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
      ServiceLocator srvcLoc = (ServiceLocator)inputMap.get("serviceLocator");
      ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pension/PensionConstants");
      TrnPensionRqstHdr lObjTrnPensionRqstHdrVO = null;
      RltPensionHeadcodeChargable lObjRltPensionHeadcodeChargable = null;
      String lStrsubjectId = null;           
      String lStrPPONo =null;
      String lStrPnsrCode = null;
      String lHeadCode = null;
      String lStrPensionFlag = null;
      
      Date lCommDate = null;
            
      String lStrDmndNo = null;
      String lStrbudMjrHd = null;
      String lStrbudSubmjrHd = null;
      String lStrbudMinHd = null;
      String lStrbudSubHd = null;
      String lStrbudDtlHd = null;
      String lStrExpEdpCode = null;
      String lStrTypeFlag = null;
      String lStrDate = null;
      String lStrPnsnCode = null;
      String lStrStatus = bundleConst.getString("STATUS.CONTINUE");
      String lStrPnsnBillType = bundleConst.getString("BILLTYPE.PENSION");
      String lStrCVPBillType = bundleConst.getString("BILLTYPE.CVP");
      String lStrDCRGBillType = bundleConst.getString("BILLTYPE.DCRG");   
      String lStrMonthlyType = bundleConst.getString("RECOVERY.MONTHLY"); 
      String lStrHBACode = bundleConst.getString("RECO.HBAEDPCODE");
      String lStrMCACode = bundleConst.getString("RECO.MCAEDPCODE");
      
      try
      {
    	lStrsubjectId = inputMap.get("subjectId").toString();
    	if(inputMap.containsKey("PensionType"))
    	{
    		lStrTypeFlag = inputMap.get("PensionType").toString();
    	}
    	
    	if("Monthly".equals(lStrTypeFlag) || "MR".equals(lStrTypeFlag))
      	{
    		lHeadCode = inputMap.get("PnsnHeadCode").toString();
      	}
    	else
    	{
    		lStrPPONo = inputMap.get("PPONo").toString();        	
      	}
       	if(lStrsubjectId != null && lStrsubjectId.equalsIgnoreCase(lStrPnsnBillType))
       	{
       		lStrPensionFlag = bundleConst.getString("BUDGT.PENSION");
       	} else if(lStrsubjectId != null && lStrsubjectId.equalsIgnoreCase(lStrDCRGBillType)){
       			lStrPensionFlag = bundleConst.getString("BUDGT.DCRG");
       	} else if(lStrsubjectId != null && lStrsubjectId.equalsIgnoreCase(lStrCVPBillType)){
       			lStrPensionFlag = bundleConst.getString("BUDGT.CVP");
       	} else if(lStrsubjectId != null && lStrsubjectId.equalsIgnoreCase("43")){
       			lStrPensionFlag = bundleConst.getString("BUDGT.MR");
       	}
       	
      	
      	BudgetDtlsDAO lObjBudgetDtlsDAO = new BudgetDtlsDAOImpl(srvcLoc.getSessionFactory());
      	if(lStrPPONo != null && lStrPPONo.length() > 0 ) 
      	{
      		// Getting the ObjectVo of  TrnPensionRqstHdr
      		lObjTrnPensionRqstHdrVO = lObjBudgetDtlsDAO.getTrnPensionRqstHdrDtls(lStrPPONo, lStrStatus);
      	}
      	if(lObjTrnPensionRqstHdrVO != null)
      	{
      		// Getting the Value from TrnPensionRqstHdr... Start...
      		lStrPnsrCode = lObjTrnPensionRqstHdrVO.getPensionerCode();
      		lHeadCode = lObjTrnPensionRqstHdrVO.getHeadCode().toString();
            lCommDate = lObjTrnPensionRqstHdrVO.getCommensionDate();
      		// Getting the Value from TrnPensionRqstHdr... End...
      	}
      	// Getting Head Structure Mapped with a HeadCode...
      	if(lHeadCode != null)
      	{
      	    lObjRltPensionHeadcodeChargable = lObjBudgetDtlsDAO.getMstPensionHeadcodeDtls(lHeadCode, lStrPensionFlag);
      		lStrDmndNo = lObjRltPensionHeadcodeChargable.getDemandNo();
      		lStrbudMjrHd = lObjRltPensionHeadcodeChargable.getMjrhdCode();
      		lStrbudSubmjrHd = lObjRltPensionHeadcodeChargable.getSubmjrhdCode();
      		lStrbudMinHd = lObjRltPensionHeadcodeChargable.getMinhdCode();
      		lStrbudSubHd = lObjRltPensionHeadcodeChargable.getSubhdCode();
      		lStrbudDtlHd = lObjRltPensionHeadcodeChargable.getDtlhdCode();
      		lStrExpEdpCode = lObjRltPensionHeadcodeChargable.getEdpCode();

            Map MapTrnBillRegister = new HashMap();
            MapTrnBillRegister.put("schemeNo", "000000");
            MapTrnBillRegister.put("demandCode", lStrDmndNo);
            MapTrnBillRegister.put("budmjrHd", lStrbudMjrHd);
            MapTrnBillRegister.put("budSubmjrHd", lStrbudSubmjrHd);
            MapTrnBillRegister.put("budMinHd", lStrbudMinHd);
            MapTrnBillRegister.put("budSubHd", lStrbudSubHd);
            MapTrnBillRegister.put("budDtlHd", lStrbudDtlHd);        		
      		String lStrHeadChrg = lStrbudMjrHd + lStrbudSubmjrHd + lStrbudMinHd + lStrbudSubHd + lStrbudDtlHd;
            MapTrnBillRegister.put("headChrg", lStrHeadChrg);
      		inputMap.put("TrnBillRegister", MapTrnBillRegister);
      	}
      	/// Get A List of EDP Details ....
      	List expEdpList = lObjBudgetDtlsDAO.getExpEdpDtl(lStrPPONo,lStrExpEdpCode);
      	inputMap.put("expEdpList", expEdpList);
      	inputMap.put("expObjHds", expEdpList);
        
        if(lStrsubjectId != null && (lStrsubjectId.equalsIgnoreCase(lStrDCRGBillType) || lStrsubjectId.equalsIgnoreCase(lStrCVPBillType)))
        {
            List rcptObjHdsList = lObjBudgetDtlsDAO.getRcptEdpDtlByBillType(lStrPnsrCode,lStrPensionFlag);
            inputMap.put("rcptObjHds",rcptObjHdsList);   
        }else
        {
          	if("Monthly".equals(lStrTypeFlag))
          	{
          		//method to get receipt details for monthly pension
          		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
                
          		String lStrMonth = StringUtility.getParameter("cmbForMonth",request).trim();
          		String lStrYear = StringUtility.getParameter("cmbForYear",request).trim();
          		
          		if(Integer.parseInt(lStrMonth) < 10)
            	{
            		lStrDate = lStrYear+"0"+lStrMonth;
            	}
            	else
            	{
            		lStrDate = lStrYear+lStrMonth;
            	}
          		if(lStrDate == null || lStrDate.length() == 0 )
          		{
          			lStrDate = inputMap.get("Date").toString();
          		}
          		List rcptObjHdsList = null;
          		//String lForMonth = inputMap.get("Date").toString();
          		if(inputMap.containsKey("StrLstPnsnCode"))
          		{
          			lStrPnsnCode = inputMap.get("StrLstPnsnCode").toString();
          		}
          		rcptObjHdsList = lObjBudgetDtlsDAO.getRcptEdpDtlForMnthly(lStrDate,lStrTypeFlag,lStrPnsnCode);
          		inputMap.put("rcptObjHds",rcptObjHdsList);
          	}
          	else if( !"MR".equals(lStrTypeFlag))
          	{
          	    gCurDate = DBUtility.getCurrentDateFromDB(); 
                Integer lCurMonth = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(gCurDate));
                Integer lCommMonth = Integer.parseInt(new SimpleDateFormat("yyyyMM").format(lCommDate));
    
                List<BillEdpVO> rcptObjHdsList = new ArrayList<BillEdpVO>();
                List<BillEdpVO> tmprcptObjHdsList = null;
                List<BillEdpVO> HBA_RcptHdsLst = new ArrayList<BillEdpVO>();
                List<BillEdpVO> MCA_RcptHdsLst = new ArrayList<BillEdpVO>();
                List<BillEdpVO> OTHR_RcptHdsLst = new ArrayList<BillEdpVO>();
                
                if(lCurMonth.intValue() == lCommMonth.intValue())
                {
                    // For Pension Recovery.
                    tmprcptObjHdsList = lObjBudgetDtlsDAO.getRcptEdpDtlByBillType(lStrPnsrCode,lStrPensionFlag);
                    
                    for(int i=0;i<tmprcptObjHdsList.size();i++)
                    {
                        if(tmprcptObjHdsList.get(i).getEdpCode().equalsIgnoreCase(lStrHBACode))
                        {
                            if(HBA_RcptHdsLst.size() == 0)
                            {
                                HBA_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(HBA_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                HBA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }
                            
                            continue;
                        }
                        else if(tmprcptObjHdsList.get(i).getEdpCode().equalsIgnoreCase(lStrMCACode))
                        {
                            if(MCA_RcptHdsLst.size() == 0)
                            {
                                MCA_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(MCA_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                MCA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }
                            continue;
                        }
                        else
                        {
                            OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            /*if(OTHR_RcptHdsLst.size() == 0)
                            {
                                OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(OTHR_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                OTHR_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }*/
                        }
                    }
                    
                    // For Other than Pension Recovery into same month.
                    tmprcptObjHdsList = null;
                    tmprcptObjHdsList = lObjBudgetDtlsDAO.getRcptEdpDtlForMnthly(lCurMonth.toString(),lStrMonthlyType,lStrPnsrCode);
                    
                    for(int i=0;i<tmprcptObjHdsList.size();i++)
                    {
                        if(tmprcptObjHdsList.get(i).getEdpCode().equalsIgnoreCase(lStrHBACode))
                        {
                            if(HBA_RcptHdsLst.size() == 0)
                            {
                                HBA_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(HBA_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                HBA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }
                        }
                        else if(tmprcptObjHdsList.get(i).getEdpCode().equalsIgnoreCase(lStrMCACode))
                        {
                            if(MCA_RcptHdsLst.size() == 0)
                            {
                                MCA_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(MCA_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                MCA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }
                        }
                        else
                        {
                            OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            /*if(OTHR_RcptHdsLst.size() == 0)
                            {
                                OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(OTHR_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                OTHR_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }*/
                        }
                    }
                    
                    if(HBA_RcptHdsLst.size() > 0)
                    {
                        rcptObjHdsList.add(HBA_RcptHdsLst.get(0));
                    }
                    if(MCA_RcptHdsLst.size() > 0)
                    {
                        rcptObjHdsList.add(MCA_RcptHdsLst.get(0));
                    }                
                    if(OTHR_RcptHdsLst.size() > 0)
                    {
                        rcptObjHdsList.add(OTHR_RcptHdsLst.get(0));
                    }
                    
                }
                else
                {
                    // From Commancement to Current Month
                    for(Integer i = lCommMonth;i<=lCurMonth;)
                    {
                        // For Other than Pension Recovery into same month.
                        tmprcptObjHdsList = null;
                        tmprcptObjHdsList = lObjBudgetDtlsDAO.getRcptEdpDtlForMnthly(i.toString(),lStrMonthlyType,lStrPnsrCode);
                        
                        for(int j=0;j<tmprcptObjHdsList.size();j++)
                        {
                            if(tmprcptObjHdsList.get(j).getEdpCode().equalsIgnoreCase(lStrHBACode))
                            {
                                if(HBA_RcptHdsLst.size() == 0)
                                {
                                    HBA_RcptHdsLst.add(tmprcptObjHdsList.get(j));
                                }
                                else
                                {
                                    Double OldAmt = Double.valueOf(HBA_RcptHdsLst.get(0).getEdpAmt().toString());
                                    Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(j).getEdpAmt().toString());
                                    HBA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                                }
                            }
                            else if(tmprcptObjHdsList.get(j).getEdpCode().equalsIgnoreCase(lStrMCACode))
                            {
                                if(MCA_RcptHdsLst.size() == 0)
                                {
                                    MCA_RcptHdsLst.add(tmprcptObjHdsList.get(j));
                                }
                                else
                                {
                                    Double OldAmt = Double.valueOf(MCA_RcptHdsLst.get(0).getEdpAmt().toString());
                                    Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(j).getEdpAmt().toString());
                                    MCA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                                }
                            }
                            else
                            {
                                OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(j));
                                /*if(OTHR_RcptHdsLst.size() == 0)
                                {
                                    OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(j));
                                }
                                else
                                {
                                    Double OldAmt = Double.valueOf(OTHR_RcptHdsLst.get(0).getEdpAmt().toString());
                                    Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(j).getEdpAmt().toString());
                                    OTHR_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                                }*/
                            }
                        }                    
                        
                        i += ((Integer.parseInt((i.toString().substring(4,6)))) == 12 ) ? 89 : 1;
                    }
                    
                    //  For Pension Recovery.
                    tmprcptObjHdsList = null;
                    tmprcptObjHdsList = lObjBudgetDtlsDAO.getRcptEdpDtlByBillType(lStrPnsrCode,lStrPensionFlag);
                    
                    for(int i=0;i<tmprcptObjHdsList.size();i++)
                    {
                        if(tmprcptObjHdsList.get(i).getEdpCode().equalsIgnoreCase(lStrHBACode))
                        {
                            if(HBA_RcptHdsLst.size() == 0)
                            {
                                HBA_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(HBA_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                HBA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }
                            
                            continue;
                        }
                        else if(tmprcptObjHdsList.get(i).getEdpCode().equalsIgnoreCase(lStrMCACode))
                        {
                            if(MCA_RcptHdsLst.size() == 0)
                            {
                                MCA_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(MCA_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                MCA_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }
                            continue;
                        }
                        else
                        {
                            OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            /*if(OTHR_RcptHdsLst.size() == 0)
                            {
                                OTHR_RcptHdsLst.add(tmprcptObjHdsList.get(i));
                            }
                            else
                            {
                                Double OldAmt = Double.valueOf(OTHR_RcptHdsLst.get(0).getEdpAmt().toString());
                                Double NewAmt = Double.valueOf(tmprcptObjHdsList.get(i).getEdpAmt().toString());
                                OTHR_RcptHdsLst.get(0).setEdpAmt(new BigDecimal(OldAmt+NewAmt));
                            }*/
                        }
                    }
                    
                    if(HBA_RcptHdsLst.size() > 0)
                    {
                        rcptObjHdsList.add(HBA_RcptHdsLst.get(0));
                    }
                    if(MCA_RcptHdsLst.size() > 0)
                    {
                        rcptObjHdsList.add(MCA_RcptHdsLst.get(0));
                    }                
                    if(OTHR_RcptHdsLst.size() > 0)
                    {
                        rcptObjHdsList.add(OTHR_RcptHdsLst.get(0));
                    }                
                }	      	
                
                inputMap.put("rcptObjHds",rcptObjHdsList);
          	}
        }
    	objRes.setResultValue(inputMap);			
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
}
