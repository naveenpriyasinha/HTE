package com.tcs.sgv.pension.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pension.dao.CommonPensionDAO;
import com.tcs.sgv.pension.dao.CommonPensionDAOImpl;
import com.tcs.sgv.pension.dao.TrnPensionRevalidationDtlsDao;
import com.tcs.sgv.pension.valueobject.TrnPensionRevalidationDtls;
 
public class RevalidationServiceImpl extends ServiceImpl
{
	Log gLogger = LogFactory.getLog(getClass());
	
	public ResultObject loadRevalidationDtls(Map inputMap)
	{

		List lLstCmbSanAuth = null;
		  ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		  HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		  String lStrPPONo = null;
		  ArrayList<TrnPensionRevalidationDtls> lLstCaseRevalid = new ArrayList<TrnPensionRevalidationDtls>();
		  try
		  {
			  ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
			  CommonPensionDAO CommonPensionDAO = new CommonPensionDAOImpl(serv.getSessionFactory());
			  lLstCmbSanAuth = CommonPensionDAO.getSanctionAuthPrefix(SessionHelper.getLangId(inputMap));
			  inputMap.put("RevadAuth", lLstCmbSanAuth);
				if(StringUtility.getParameter("hidPPONo", request) != null && StringUtility.getParameter("hidPPONo", request).length() > 0)
				{
					lStrPPONo = StringUtility.getParameter("hidPPONo", request);
				}
			  inputMap.put("PPONO", StringUtility.getParameter("hidPPONo", request));
			  if(lStrPPONo != null );
			  {
				  TrnPensionRevalidationDtlsDao  lObjRevalidDao = new TrnPensionRevalidationDtlsDao(TrnPensionRevalidationDtls.class,serv.getSessionFactory());
				  lLstCaseRevalid = lObjRevalidDao.getRevalidationVosByRqstId(lStrPPONo);	
	            	if(lLstCaseRevalid != null && lLstCaseRevalid.size() > 0)
	            	{
	            		ArrayList<TrnPensionRevalidationDtls> lLstRevalidDisableVo = new ArrayList<TrnPensionRevalidationDtls>();
	            		for(int i = 0; i<lLstCaseRevalid.size()-1;i++)
	            		{
	            			lLstRevalidDisableVo.add(lLstCaseRevalid.get(i));
	            		}
	            		if(lLstRevalidDisableVo.size() > 0)
	            		{
	            			inputMap.put("TrnPensionRevalidationDtlsList", lLstRevalidDisableVo);
	            		}
	            		if(lLstCaseRevalid.size()-1 >= 0)
	            		{
	            			inputMap.put("TrnPensionRevalidationDtls", lLstCaseRevalid.get(lLstCaseRevalid.size()-1));
	            			inputMap.put("lasRevalidDate", lLstCaseRevalid.get(lLstCaseRevalid.size()-1).getRevalidatonDate());
	            		}
	            		inputMap.put("VoListSize", lLstCaseRevalid.size());
	            	}
			  }
			  resObj.setResultValue(inputMap);
			  resObj.setViewName("RevalidPopup");
		  }
		  catch(Exception e)
		  {
			  gLogger.error("Error is "+e,e);
		  }
		return resObj;
	}
	public ResultObject saveRevalidDtls(Map orgsMap)
	{
		 ServiceLocator serv = null;
		 ArrayList<TrnPensionRevalidationDtls> lLstCaseRevalid = new ArrayList<TrnPensionRevalidationDtls>();
		 String lStrPPONo = null;
		 ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			serv = (ServiceLocator) orgsMap.get("serviceLocator"); 
			HttpServletRequest request = (HttpServletRequest) orgsMap.get("requestObj");
			TrnPensionRevalidationDtlsDao  lObjRevalidDao = new TrnPensionRevalidationDtlsDao(TrnPensionRevalidationDtls.class,serv.getSessionFactory());
			if(StringUtility.getParameter("hidPPONo", request)  != null && StringUtility.getParameter("hidPPONo", request).length() >  0)
			{
				lStrPPONo = StringUtility.getParameter("hidPPONo", request);	
			}
			if(lStrPPONo != null)
			{
				lLstCaseRevalid = lObjRevalidDao.getRevalidationVosByRqstId(lStrPPONo);	
			}
					
			 TrnPensionRevalidationDtls lObjCaseRevalid = null;
	            if(lLstCaseRevalid != null && lLstCaseRevalid.size() > 0)
	            {
	                for(int i=0;i<lLstCaseRevalid.size();i++)
	                {
	                	lObjCaseRevalid = lLstCaseRevalid.get(i);
	                	lObjRevalidDao.delete(lObjCaseRevalid);
	                }
	            }
            	lLstCaseRevalid = (ArrayList<TrnPensionRevalidationDtls>) orgsMap.get("RevalidVoList");
            	
                if(lLstCaseRevalid != null && lLstCaseRevalid.size() > 0)
                {
                    for(int i = 0;i<lLstCaseRevalid.size();i++)
                    {
                    	lObjCaseRevalid = new TrnPensionRevalidationDtls();
                    	lObjCaseRevalid = lLstCaseRevalid.get(i);
                    	if(lStrPPONo != null)
                    	{
                    		lObjCaseRevalid.setPpoNo(lStrPPONo);
                    	}
                    	Long lLngRevalid = IFMSCommonServiceImpl.getNextSeqNum("trn_pension_revalidation_dtls", orgsMap);
                        lObjCaseRevalid.setPnsnRevalidId(lLngRevalid);
                        lObjRevalidDao.create(lObjCaseRevalid);
                    }
                }
                if(lLstCaseRevalid.size()-1>=0)
                {
                	orgsMap.put("RevalidDate", lLstCaseRevalid.get(lLstCaseRevalid.size()-1).getRevalidatonDate()) ;
                }
                StringBuilder lStrBldXML = getResponseXMLDoc(orgsMap);
                String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lStrBldXML.toString()).toString();
                orgsMap.put("ajaxKey", lStrResult);
                resObj.setViewName("ajaxData");
                resObj.setResultValue(orgsMap);
		}
		catch(Exception e)
		{
			gLogger.error("Error is"+e,e);
		}
		return resObj;
	}
	private StringBuilder getResponseXMLDoc(Map lMapInput)
	{
		StringBuilder lStrHidPKs = new StringBuilder();
		SimpleDateFormat lObjSmplDtFmt = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			lStrHidPKs.append("<XMLDOC>");
			lStrHidPKs.append("<Msg>");
			lStrHidPKs.append("Revalidation Details Saved Successfully");
			lStrHidPKs.append("</Msg>");
			if(lMapInput.get("RevalidDate") != null)
			{
				lStrHidPKs.append("<RevalidDate>");
				lStrHidPKs.append(lObjSmplDtFmt.format(lMapInput.get("RevalidDate")));
				lStrHidPKs.append("</RevalidDate>");
			}
			lStrHidPKs.append("</XMLDOC>");
		}
		catch(Exception e)
		{
			gLogger.error("Error is"+e,e);
		}
		return lStrHidPKs;
	}
}
