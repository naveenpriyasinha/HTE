package com.tcs.sgv.common.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.ObjectionDAOImpl;
import com.tcs.sgv.common.valueobject.MstObjection;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;



/** ObjectionService
 *  This class is used to get Objection Details for the objections raised by end-user.
 *  This interface is implemented in ObjectionServiceImpl
 *  
 * 	Date of Creation : 13th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  Hiral    23-Oct-2007   For making changes for code formating
 */
public class ObjectionServiceImpl extends ServiceImpl 
	implements ObjectionService
{
	Log logger = LogFactory.getLog(getClass());	
	
	public ResultObject getObjDetails(Map inputMap)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);		
		try
		{					
			if(objRes != null)
			{
				ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
				ObjectionDAOImpl lObjObjectionDAOImpl = new ObjectionDAOImpl(MstObjection.class, serv.getSessionFactory());				
								
				List lLstObjection = lObjObjectionDAOImpl.getBilldetails(inputMap);
				inputMap.put("ObjectionList",lLstObjection);
				objRes.setResultValue(inputMap);
				objRes.setViewName("showObjectionDetails");
			}
		}
		catch(Exception e)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();	
		}
		
		return objRes;
	}
}
