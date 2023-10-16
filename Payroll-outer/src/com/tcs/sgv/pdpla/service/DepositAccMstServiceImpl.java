/**
 * package : com.tcs.sgv.pdpla.service 
 * @author Sandeep Choudhary.
 */

package com.tcs.sgv.pdpla.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.DepositAccMstDAOImpl;
import com.tcs.sgv.pdpla.valueobject.MstPdAccount;

public class DepositAccMstServiceImpl extends ServiceImpl implements DepositAccMstService
{
	Log gLogger = LogFactory.getLog(getClass());
	
	public ResultObject addPDAccMst(Map orgsMap)
	{
		
		ServiceLocator serv = (ServiceLocator) orgsMap.get("serviceLocator");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
				
		try
		{
			MstPdAccount lMstPdVO = new MstPdAccount();
			lMstPdVO = (MstPdAccount) orgsMap.get("MstPdAccount");
			
			DepositAccMstDAOImpl lobjDAOImpl = new DepositAccMstDAOImpl(MstPdAccount.class, serv.getSessionFactory());
			long laccountId = BptmCommonServiceImpl.getNextSeqNum("mst_pd_account", orgsMap);
			//System.out.println("account No is :-"+laccountId);
			lMstPdVO.setAccountId(laccountId);
			lobjDAOImpl.create(lMstPdVO);
					objRes.setResultValue(orgsMap);
			objRes.setViewName("successful");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//System.out.println("############ SERVICE - before returning result object ###########");
		return objRes;
	}

	
}
