package com.tcs.sgv.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.HrDeclarationFlagMstDAO;
import com.tcs.sgv.common.dao.HrDeclarationFlagMstDAOImpl;
import com.tcs.sgv.common.valueobject.HrDeclarationFlagMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

public class HrDeclarationServImpl extends ServiceImpl implements HrDeclarationServ 
{
	Log logger = LogFactory.getLog(getClass());
	public ResultObject showDeclarationData(Map objectArgs,long appId) 
	{
		ResultObject resObject = new ResultObject();
		try 
		{
			logger.info("Show Declaration Data App ID : " + appId );
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			Map loginMap = (Map) objectArgs.get("baseLoginMap");			
			long langId= Long.parseLong(loginMap.get("langId").toString());	
			
			/*Collecting A Declaration*/
			HrDeclarationFlagMstDAO declarationFlagMstDAO = new HrDeclarationFlagMstDAOImpl(HrDeclarationFlagMst.class,serv.getSessionFactory()); 
			List declarationLstObj = new ArrayList();  
			declarationLstObj=declarationFlagMstDAO.getAllDeclarationOnApplicationIdAndLangId(appId,langId);
			objectArgs.put("decLab",declarationLstObj);
			/*End Of Collectiong a Declaration*/
			
			/*Declaration Comopoenet Type*/
			List declarationCompLstObj = new ArrayList();
			ListIterator li= declarationLstObj.listIterator();
			while(li.hasNext())
			{
				HrDeclarationFlagMst declarationFlagMst = null;
				declarationFlagMst = (HrDeclarationFlagMst) li.next();
				if(declarationFlagMst.getDeclarationId()!=0)
				{
					List tempListObj = declarationFlagMstDAO.getDeclarationComponentLabelOnDeclarationIdAndLangId(declarationFlagMst.getDeclarationId(), langId);
					if(tempListObj.isEmpty()==false)
					{
						ListIterator tempLi = tempListObj.listIterator();
						while(tempLi.hasNext())
						{
							declarationCompLstObj.add(tempLi.next());
						}
					}
				}
			}				
			objectArgs.put("decComp",declarationCompLstObj);
			resObject.setResultCode(ErrorConstants.SUCCESS);
			resObject.setResultValue(objectArgs);			
			/*End of Declaration Compponent Type*/
		}catch(Exception e) 
		{
			e.printStackTrace();
			resObject.setResultCode(ErrorConstants.ERROR);
			resObject.setResultValue(objectArgs);
			logger.error("Error while getting a Declaration App ID : " + appId, e);
		}
		return resObject;
	}
}
