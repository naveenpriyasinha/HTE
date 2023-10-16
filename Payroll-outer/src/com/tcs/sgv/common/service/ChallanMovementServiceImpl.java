package com.tcs.sgv.common.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.dao.ChallanMovementDAOImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.exprcpt.valueobject.TrnRcptMvmnt;

public class ChallanMovementServiceImpl 
	extends ServiceImpl 
	implements ChallanMovementService 
{	
	public ResultObject insertChallanMovement(Map inputMap)
	{
		HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
		Enumeration enu = request.getAttributeNames();
		
		while(enu.hasMoreElements())
		{
			//System.out.println("Inside challan MovementSErvice,REquest name : " +enu.nextElement().toString());
		}
		Enumeration enudm =  request.getSession(false).getAttributeNames();
		while(enudm.hasMoreElements())
		{
			//System.out.println("\n-- " + enudm.nextElement().toString());
		}
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		
		try
		{
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(FrmServiceMst.class, serv.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl.readService("GENERATE_ID_SRVC");
			String s1="trn_rcpt_mvmnt";
			inputMap.put("tablename",s1);
			inputMap.put("serviceLocator", serv);
			ResultObject resultObj = serv.executeService(servDetails,inputMap);
			int receivedcode = resultObj.getResultCode();
			if(receivedcode == -1)
			{
				//System.out.println("Error in exection of previoucservice");
				return resultObj;
			}
			Map resultMap =(Map) resultObj.getResultValue();
			String val=resultMap.get("newID").toString();
			//System.out.println(" ------------  " + val);
			Long lLngCaseID = Long.valueOf(val); 
			
			//System.out.println(" \n\n --------- In New Id is --- " + lLngCaseID +"\n\n ");
			ChallanMovementDAOImpl mvmntDAO = new ChallanMovementDAOImpl(TrnRcptMvmnt.class,serv.getSessionFactory());
			TrnRcptMvmnt tnrMvmtnVO = (TrnRcptMvmnt)inputMap.get("receiptMovementVo");
			
//			tnrMvmtnVO.setMovemntId(mvmntDAO.getmaxMovementId(lLngRcptNo));
			//System.out.println(" after movement...... ******* ");
			tnrMvmtnVO.setRcptMvmntId(lLngCaseID);
			mvmntDAO.create(tnrMvmtnVO);
//			//System.out.println(" \n\n......... after movement ---  "  + tnrMvmtnVO.getTrnRcptMvmntId());
			Map result = new HashMap();
			result.put("MESSAGECODE", 1038);
			objRes.setResultValue(result);
			objRes.setViewName("ajaxData");
		}
		catch(Exception e)
		{
			objRes.setThrowable(e);
			objRes.setResultCode(-1);
			objRes.setViewName("errorPage");
			e.printStackTrace();
		}
		return objRes;
	}	
}
