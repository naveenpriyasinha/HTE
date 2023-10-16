
package com.tcs.sgv.pension.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.SgvaMonthMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionBillHdrDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionItCutDtlsDAO;
import com.tcs.sgv.pension.dao.TrnPensionItCutDtlsDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAO;
import com.tcs.sgv.pension.dao.TrnPensionRqstHdrDAOImpl;
import com.tcs.sgv.pension.valueobject.TrnPensionBillHdr;
import com.tcs.sgv.pension.valueobject.TrnPensionItCutDtls;
import com.tcs.sgv.pension.valueobject.TrnPensionRqstHdr;

/**
 * Cut Service Implementation
 * 
 * @author Soumya Adhikari
 * @version 1.0
 */
public class CutServiceImpl extends ServiceImpl implements CutService
{  
    /* Global Variable for Logger Class */
    private final Log gLogger = LogFactory.getLog(getClass());
    
    /* Global Variable for Current Date */
    Date gDtCurrDt = null;
    
    /**
     * Shows Specific Cut Data 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject getCutDtls(Map inputMap) 
	{
    	ServiceLocator servLoc = (ServiceLocator) inputMap.get("serviceLocator");
    	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");        
    	HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
    	String lStrLangId=SessionHelper.getLangId(inputMap).toString();    	
    	int lIntMaxForMonth = 0;
		setSessionInfo(inputMap);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currDate = sdf.format(gDtCurrDt);		
		
		String currentMonth = currDate.substring(5, 7);
		String currentYear = currDate.substring(0,4);	
		
		inputMap.put("CurrentMonth", currentMonth);
		inputMap.put("CurrentYear", currentYear);
		
		ArrayList<Integer> frommonth =  new ArrayList<Integer>(); 
		ArrayList<Integer> fromyear =  new ArrayList<Integer>(); 
		ArrayList<Integer> tomonth = new ArrayList<Integer>();
		ArrayList<Integer> toyear = new ArrayList<Integer>();
		TrnPensionItCutDtls lObjTrnPenItCutDtl = new TrnPensionItCutDtls();
		
		try
        {
			//To populate Cut specific fields from table TrnPensionItCutDtls....
            String lStrCutType=StringUtility.getParameter("buttonType",request).trim();      
            String lStrPenCode=StringUtility.getParameter("pensioner_code",request).trim();
            long lBDPenReqId= Long.parseLong(StringUtility.getParameter("pension_request_id",request).trim());
            
            List<TrnPensionItCutDtls> lObjTrnPensionItCutDtls = new ArrayList<TrnPensionItCutDtls>();			           
			
            TrnPensionRqstHdrDAO lObjTrnPensionRqstHdrDAO = new TrnPensionRqstHdrDAOImpl(TrnPensionRqstHdr.class,servLoc.getSessionFactory());
            TrnPensionItCutDtlsDAO lObjTrnPensionItCutDtlsDAO = new TrnPensionItCutDtlsDAOImpl(TrnPensionItCutDtls.class,servLoc.getSessionFactory());
            TrnPensionBillHdrDAO lObjTrnPensionBillHdrDAO = new TrnPensionBillHdrDAOImpl(TrnPensionBillHdr.class,servLoc.getSessionFactory());
            
			TrnPensionRqstHdr lObjTrnPensionRqstHdr = new TrnPensionRqstHdr();
			lObjTrnPensionRqstHdr = lObjTrnPensionRqstHdrDAO.getTrnPensionRqstHdrVO(lBDPenReqId,lStrPenCode);
			
			if(lObjTrnPensionRqstHdr != null)
			{	
				if("BILLCRTD".equals(lObjTrnPensionRqstHdr.getApproveStatus()))
				{
					inputMap.put("Disable","true");
				}
				else
				{
					inputMap.put("Disable","false");
				}
			}
            //Returning VO array...
            lObjTrnPensionItCutDtls = lObjTrnPensionItCutDtlsDAO.getTrnPensionItCutDtlsVO(lStrCutType,lStrLangId,lBDPenReqId,lStrPenCode);                        
            if (lObjTrnPensionItCutDtls != null)
            {
            	inputMap.put("TrnPensionItCutDtlsVOArray", lObjTrnPensionItCutDtls);
            	inputMap.put("DBStatus", "Full");
            }
            if (lObjTrnPensionItCutDtls.isEmpty())
            {
            	inputMap.put("DBStatus", "Empty");
            }             
			for(int i=0; i < lObjTrnPensionItCutDtls.size();i++)
			{	
				lObjTrnPenItCutDtl = (TrnPensionItCutDtls) lObjTrnPensionItCutDtls.get(i);
								
				lIntMaxForMonth = lObjTrnPensionBillHdrDAO.getBillGenerationMonth(lStrPenCode);
					
				inputMap.put("MaxForMonth", lIntMaxForMonth);
				
				if((lObjTrnPenItCutDtl.getFromMonth())==0)
				{
					fromyear.add(0);
					frommonth.add(0);
				}
				else
				{
					fromyear.add(Integer.parseInt(lObjTrnPenItCutDtl.getFromMonth().toString().substring(0,4)));
					frommonth.add(Integer.parseInt(lObjTrnPenItCutDtl.getFromMonth().toString().substring(4,6)));
				}
				if((lObjTrnPenItCutDtl.getToMonth())==0)
				{
					toyear.add(0);
					tomonth.add(0);
				}
				else
				{
					toyear.add(Integer.parseInt(lObjTrnPenItCutDtl.getToMonth().toString().substring(0,4)));
					tomonth.add(Integer.parseInt(lObjTrnPenItCutDtl.getToMonth().toString().substring(4,6)));					
				}				
			}			
			inputMap.put("FromMonth",frommonth);
			inputMap.put("FromYear",fromyear);
			inputMap.put("ToMonth",tomonth);
			inputMap.put("ToYear",toyear);
            
            //To populate month combo....
            List<SgvaMonthMst> lObjSgvaMonthMst=null;
            
            CommonPensionDAO  lObjCommonPensionDAO  = new CommonPensionDAOImpl(servLoc.getSessionFactory());            
            
            //Returning VO array...
            lObjSgvaMonthMst=lObjCommonPensionDAO .getSgvaMonthMstVO(lStrLangId);            
            
            if(lObjSgvaMonthMst != null)
            {
            	inputMap.put("SgvaMonthMstVOArray", lObjSgvaMonthMst);
            }
            
        	//To populate year combo....
            List<SgvcFinYearMst> lObjSgvcFinYearMst=null;
            
            //Returning VO array...
            lObjSgvcFinYearMst=lObjCommonPensionDAO .getSgvcFinYearMstVO(lStrLangId);
            
            if(lObjSgvcFinYearMst != null)
            {
            	inputMap.put("SgvcFinYearMstVOArray", lObjSgvcFinYearMst);
            }
            resObj.setResultValue(inputMap);
            resObj.setViewName("openCutDetailsWindow");
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
     * Inserts Cut Dtls 
     * @param Map:inputMap
     * @return ResultObject
     */
    public ResultObject saveCutDtls(Map inputMap) 
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");    	       
        TrnPensionItCutDtls lObjTrnPensionItCutDtls = new TrnPensionItCutDtls(); 
        List<TrnPensionItCutDtls> lLstTrnPensionItCutDtls = null;
        long lBDPensionRequestId = 0;
        long lBDTrnPensionItCutDtlsPK = 0;
        String lStrPensionerCode = "";
        String lStrTypeFlag = "";
        TrnPensionItCutDtlsDAO lObjTrnPensionItCutDtlsDAO = new TrnPensionItCutDtlsDAOImpl(TrnPensionItCutDtls.class,serv.getSessionFactory());
                
        try
        {        	
        	lBDPensionRequestId = Long.parseLong((inputMap.get("PensionRequestId")).toString());
        	lStrPensionerCode = (inputMap.get("PensionerCode")).toString();
        	lStrTypeFlag = (inputMap.get("TypeFlag")).toString();
        	lLstTrnPensionItCutDtls = (List<TrnPensionItCutDtls>) inputMap.get("TrnPensionItCutDtlsVOList");
		    
        	/* Deleting the previously inserted data.... First get all the pks of existing records ,
		     * Read the records with their pks Delete those records...    */            
        	
            //Getting PK s of existing records....for a particular PensionRequestId and PensionerCode ....
        	List<Long> lLstTrnPensionItCutDtlsPK = lObjTrnPensionItCutDtlsDAO.getPKForTableTrnPensionItCutDtls(lBDPensionRequestId,lStrPensionerCode,lStrTypeFlag);
            
        	if(lLstTrnPensionItCutDtlsPK != null &&! lLstTrnPensionItCutDtlsPK.isEmpty())
            {            	
            	for(int i = 0; i < lLstTrnPensionItCutDtlsPK.size(); ++i)
            	{            		
            		lBDTrnPensionItCutDtlsPK = lLstTrnPensionItCutDtlsPK.get(i);
            		
            		lObjTrnPensionItCutDtls = lObjTrnPensionItCutDtlsDAO.read(lBDTrnPensionItCutDtlsPK);
            		lObjTrnPensionItCutDtlsDAO.delete(lObjTrnPensionItCutDtls);
            	}
            }
        	/* Now Time for fresh insert...*/
        	lObjTrnPensionItCutDtls = new TrnPensionItCutDtls(); 
            Iterator<TrnPensionItCutDtls> lItrDtl = lLstTrnPensionItCutDtls.iterator();
            
            while (lItrDtl.hasNext())
            {
            	lObjTrnPensionItCutDtls = lItrDtl.next();
                saveCutData(lObjTrnPensionItCutDtls, inputMap);
            }
            inputMap.put("ajaxKey", "");
            objRes.setViewName("ajaxData");
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
     * Save the Cut Details of TrnPensionItCutDtls VO.
     * 
     * @param TrnPensionItCutDtls lObjTrnPensionItCutDtls, Map inputMap           
     * @return void
     */ 
    private void saveCutData(TrnPensionItCutDtls lObjTrnPensionItCutDtls, Map inputMap) throws Exception
    {
    	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
    	TrnPensionItCutDtlsDAO lObjTrnPensionItCutDtlsDAO = new TrnPensionItCutDtlsDAOImpl(TrnPensionItCutDtls.class,serv.getSessionFactory());
    	long lBDPensionItCutDtlsId = 0;
    	try
    	{
	    	//setting PK ....    	
	    	lBDPensionItCutDtlsId = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_it_cut_dtls", inputMap);
	    	lObjTrnPensionItCutDtls.setPensionItCutDtlsId(lBDPensionItCutDtlsId);   	
	    	lObjTrnPensionItCutDtlsDAO.create(lObjTrnPensionItCutDtls);
    	}
    	catch(Exception e)
    	{
    		gLogger.info("Error is : "+e,e);
    		throw(e);
    	}
    }
    private void setSessionInfo(Map inputMap)
    {      
        gDtCurrDt = DBUtility.getCurrentDateFromDB();
    }
}