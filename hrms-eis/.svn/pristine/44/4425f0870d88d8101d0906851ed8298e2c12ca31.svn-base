package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

public class DDOSchemeVOGenImpl extends ServiceImpl
{

	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);	


	public ResultObject insertAndUpdateDDOSchemeDtls(Map objectArgs) 
	{
		try
		{
			logger.info("DDOInfoVOGenImpl getDataForDDOInformationDtls Called");		
			//System.out.println("DDOInfoVOGenImpl getDataForDDOInformationDtls Called");	
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");							
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls ***********");

			String SchemeCode = "";
			String SchemeName = "";

			String DemandNo = "";
			String MajorNo = "";
			String SubMajorNo = "";
			String MinorNo = "";
			String SubMinorNo = "";

			String Active =StringUtility.getParameter("ActiveType", request);
		

			if(StringUtility.getParameter("schemeCode", request)!=null && !StringUtility.getParameter("schemeCode", request).equals(""))
			{
				SchemeCode = StringUtility.getParameter("schemeCode", request);

			}

			if(StringUtility.getParameter("schemeName", request)!=null && !StringUtility.getParameter("schemeName", request).equals(""))
			{
				SchemeName = StringUtility.getParameter("schemeName", request);

			}

			if(StringUtility.getParameter("cmbDemand", request)!=null && !StringUtility.getParameter("cmbDemand", request).equals(""))
			{
				DemandNo = StringUtility.getParameter("cmbDemand", request);

			}

			if(StringUtility.getParameter("cmbMjrHead", request)!=null && !StringUtility.getParameter("cmbMjrHead", request).equals(""))
			{
				MajorNo = StringUtility.getParameter("cmbMjrHead", request);

			}

			if(StringUtility.getParameter("cmbSubMjrHead", request)!=null && !StringUtility.getParameter("cmbSubMjrHead", request).equals(""))
			{
				SubMajorNo = StringUtility.getParameter("cmbSubMjrHead", request);

			}

			if(StringUtility.getParameter("cmbMnrHead", request)!=null && !StringUtility.getParameter("cmbMnrHead", request).equals(""))
			{
				MinorNo = StringUtility.getParameter("cmbMnrHead", request);

			}

			if(StringUtility.getParameter("head", request)!=null && !StringUtility.getParameter("head", request).equals(""))
			{
				SubMinorNo = StringUtility.getParameter("head", request);

			}
			
			if(StringUtility.getParameter("ActiveType", request)!=null && !StringUtility.getParameter("ActiveType", request).equals(""))
			{
				Active = StringUtility.getParameter("ActiveType", request);

			}

			
			String editMode = StringUtility.getParameter("edit",request);
			if(StringUtility.getParameter("edit", request)!=null && !StringUtility.getParameter("edit", request).equals(""))
			{
				editMode = StringUtility.getParameter("edit", request);

			}
			
			
			
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls SchemeCode***********" + SchemeCode);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls SchemeName***********" + SchemeName);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls DemandNo***********" + DemandNo);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls MajorNo***********" + MajorNo);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls SubMajorNo***********" + SubMajorNo);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls MinorNo***********" + MinorNo);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls SubMinorNo***********" + SubMinorNo);
			//System.out.println("ENTER INTO DDOSchemeVOGenImpl insertAndUpdateDDOSchemeDtls Active***********" + Active);
			
			
			
			List<String> SchemeList = new ArrayList<String>();

			SchemeList.add(SchemeCode);
			SchemeList.add(SchemeName);
			SchemeList.add(DemandNo);
			SchemeList.add(MajorNo);
			SchemeList.add(SubMajorNo);
			SchemeList.add(MinorNo);
			SchemeList.add(SubMinorNo);

			objectArgs.put("SchemeList", SchemeList);
			
			objectArgs.put("SchemeCode", SchemeCode);
			objectArgs.put("SchemeName", SchemeName);
			objectArgs.put("DemandNo", DemandNo);
			objectArgs.put("MajorNo", MajorNo);
			objectArgs.put("SubMajorNo", SubMajorNo);
			objectArgs.put("MinorNo", MinorNo);
			objectArgs.put("SubMinorNo", SubMinorNo);
			objectArgs.put("Active", Active);
			objectArgs.put("editMode", editMode);

			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			return retObj;
		}
		catch(PropertyValueException pe)
		{
			logger.info("Exception in generateMapForInsertBankMaster-----"+pe);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(pe);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			pe.printStackTrace();

		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			logger.info("Exception in generateMapForInsertBankMaster-----"+e);
			logger.info("setViewName-->errorPage");
			Map result = new HashMap();
			result.put("MESSAGECODE",300004);
			retObj.setResultValue(result);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			e.printStackTrace();			

		}		
		return retObj;
	}


}
