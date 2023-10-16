package com.tcs.sgv.pension.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.TrnPnsncaseRopRltDAO;
import com.tcs.sgv.pension.valueobject.TrnPnsncaseRopRlt;

public class ROPServiceImpl extends ServiceImpl
{
	
   /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for LangId */
    Long gLngLangId = null;

    /* Global Variable for EmpId */
    Long gLngEmpId = null;

    /* Global Variable for Location Id */
    String gStrLocId = null;

    /* Global Variable for DB Id */
    Long gLngDBId = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    Log gLogger = LogFactory.getLog(getClass());
    
	public ResultObject getRopDtlsPopup(Map orgsMap)
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		List lLstRop = new ArrayList();
		List<TrnPnsncaseRopRlt> lLstRopVo = new ArrayList<TrnPnsncaseRopRlt>();
		try
		{
			  setSessionInfo(orgsMap);
			  HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
	          ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
	          CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
	          lLstRop = (List) cmnLookupMstObj.getAllChildrenByLookUpNameAndLang("ROPPPO", gLngLangId);
	          orgsMap.put("ROPList", lLstRop);
	          orgsMap.put("PPONO",StringUtility.getParameter("hidPPONo", request));
	          if(StringUtility.getParameter("hidPPONo", request).length() > 0)
	          {
	        	  TrnPnsncaseRopRltDAO lObjROPDao = new TrnPnsncaseRopRltDAO(TrnPnsncaseRopRlt.class,serv.getSessionFactory());
	        	  List lLStROPPks = lObjROPDao.getROPDtlsFromPpoNo(StringUtility.getParameter("hidPPONo", request));
	        	  for(int i=0;i<lLStROPPks.size();i++)
	        	  {
	        		  TrnPnsncaseRopRlt lObjVo = new TrnPnsncaseRopRlt();
	        		  lObjVo = lObjROPDao.read((Long) lLStROPPks.get(i));
	        		  lLstRopVo.add(lObjVo);
	        	  }
	          }
	          orgsMap.put("SavedRop", lLstRopVo);
	          resObj.setResultValue(orgsMap);
	          resObj.setViewName("ROPPopup");
		}
		catch(Exception e)
		{
			gLogger.error("Error is"+e,e);
		}
		return resObj;
	}
	private void setSessionInfo(Map inputMap)
    {
        gLngLangId = SessionHelper.getLangId(inputMap);
        gLngEmpId = SessionHelper.getEmpId(inputMap);
        gLngPostId = SessionHelper.getPostId(inputMap);
        gLngUserId = SessionHelper.getUserId(inputMap);
        gStrLocId = SessionHelper.getLocationCode(inputMap);
        gLngDBId = SessionHelper.getDbId(inputMap);
        gStrLocationCode = SessionHelper.getLocationCode(inputMap);
    }
    public ResultObject saveROPDtls(Map orgsMap)
    {
    	 ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
         try
         {
             ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
             HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
             List<TrnPnsncaseRopRlt> lLstRopVo = new ArrayList<TrnPnsncaseRopRlt>();
         	 String lStrPPONo = StringUtility.getParameter("hidPPONo", request);
             TrnPnsncaseRopRltDAO lObjROPDao = new TrnPnsncaseRopRltDAO(TrnPnsncaseRopRlt.class,serv.getSessionFactory());
             List lLStROPPks = lObjROPDao.getROPDtlsFromPpoNo(lStrPPONo);
             for(int i=0;i<lLStROPPks.size();i++)
             {
            	 TrnPnsncaseRopRlt lObjVo = new TrnPnsncaseRopRlt();
            	 Long lLngRopId = (Long) lLStROPPks.get(i);
            	 lObjVo = lObjROPDao.read(lLngRopId);
            	 lObjROPDao.delete(lObjVo);
             }
             lLstRopVo = (List<TrnPnsncaseRopRlt>) orgsMap.get("ROPList");
             for(int i=0;i<lLstRopVo.size();i++)
             {
            	 TrnPnsncaseRopRlt lObjVo = lLstRopVo.get(i);
            	 lObjVo.setPpoNo(lStrPPONo);
            	 insertRopDtls(lObjVo,orgsMap);
             }
             resObj = getRopDtlsPopup(orgsMap);
             resObj.setViewName("ROPPopup");
             resObj.setResultValue(orgsMap);
         }
         catch(Exception e)
         {
        	 gLogger.error("Error is :" + e, e);
         }
    	return resObj;
    }
	public void insertRopDtls(TrnPnsncaseRopRlt PnsncaseRopRltVO, Map inputMap) throws Exception
	{
	    Long RopDtlsId = null;
	    ServiceLocator serv = null;
	    try
	    {
	    	RopDtlsId = IDGenerateDelegate.getNextId("trn_pnsncase_rop_rlt", inputMap);
	        serv = (ServiceLocator) inputMap.get("serviceLocator");
	        TrnPnsncaseRopRltDAO mstPnsnrFMDtlsDAOImpl = new TrnPnsncaseRopRltDAO(
	        		TrnPnsncaseRopRlt.class, serv.getSessionFactory());
	        if (RopDtlsId != null)
	        {
	        	PnsncaseRopRltVO.setPnsncaseRopRltId(RopDtlsId);
	        	mstPnsnrFMDtlsDAOImpl.create(PnsncaseRopRltVO);
	        }
	    }
	    catch (Exception e)
	    {
	        gLogger.error("Error is :" + e, e);
	        throw e;
	    }
	}
}