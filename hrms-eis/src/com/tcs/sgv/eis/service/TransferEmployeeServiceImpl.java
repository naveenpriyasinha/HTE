package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

/**
 * @author Manish khunt
 * Date 20/12/2011	Tuesday	12:41 PM
 * This class contains mehods used  for transfer of an employee 
 */
public class TransferEmployeeServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	

	/**
	 * author@manish khunt
	 * This method is used to transfer an employee from one DDO to onother DDO
	 * @param objectArgs
	 */
	public ResultObject  doTransfer(Map objectArgs) throws Exception
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside doTransfer method of TransferEmployeeServiceImpl class");		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		OtherDetailServiceHelper detailServiceHelper = new OtherDetailServiceHelper(serv);
		

		GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(OrgEmpMst.class);
		genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
		
		Long billNo= null;
		try{
			
		boolean isTransfer  = true; 
			
		if(objectArgs.get("NewPostArray")!=null && objectArgs.get("EmpIdArray")!=null && objectArgs.get("BillGroupArray") != null)
		{	
			PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
			ArrayList empIdLst =(ArrayList)objectArgs.get("EmpIdArray");
			ArrayList postIdLst =(ArrayList)objectArgs.get("NewPostArray");
			ArrayList billGrpLst = (ArrayList)objectArgs.get("BillGroupArray");
			
			logger.info("size of EmpIdLst is "+empIdLst.size() + "size of Post Id List is "+postIdLst.size()+" size of bill Id list is"+billGrpLst.size());
			if(empIdLst.size() == postIdLst.size() && postIdLst.size() == billGrpLst.size())
			{
			for(int i =0;i<empIdLst.size();i++)
			{
				logger.info("inside for loop with ith value is"+i +"Post id is "+postIdLst.get(i)
						+"empId is "+empIdLst.get(i) + "billId is "+billGrpLst.get(i));
				//to Update Bill Group
				    billNo = Long.valueOf(billGrpLst.get(i).toString());
				    HrPayPsrPostMpg payPsrPostMpg  =   psrPostMpgDAOImpl.getHrPayPostPsrMpg(Long.valueOf(postIdLst.get(i).toString()));
					
				  /*  HrPayPsrPostMpg  psr = new HrPayPsrPostMpg(); 
					psr.setLoc_id(payPsrPostMpg.getLoc_id());
					psr.setPostId(payPsrPostMpg.getPostId());
					psr.setPsrId(payPsrPostMpg.getPsrId());
					psr.setPsrPostId(payPsrPostMpg.getPsrPostId());
					psr.setBillNo(billNo);*/
				    payPsrPostMpg.setBillNo(billNo);
				    psrPostMpgDAOImpl.update(payPsrPostMpg);
					
				
				//Ended by manish khunt
				long userPostRltPk =  IDGenerateDelegate.getNextId("ORG_USERPOST_RLT", objectArgs);
				logger.info("userpostRlt pk is "+userPostRltPk);
				

				OrgEmpMst  orgEmpMstLoggerIn  =(OrgEmpMst)genericDaoHibernateImpl.read(Long.valueOf(empIdLst.get(i).toString()));
				detailServiceHelper.tranferEmployee(Long.valueOf(postIdLst.get(i).toString()), Long.valueOf(empIdLst.get(i).toString()),userPostRltPk,isTransfer,new Date(),orgEmpMstLoggerIn);
			}
		}
		}
	}catch (Exception e) {
		// TODO: handle exception
		logger.error("Error is: "+ e.getMessage());
		resultObject.setThrowable(e);
		resultObject.setResultCode(-1);
		logger.info("Exception Occurred in TransferEmployeeServiceImpl.doTransfer method "+e.getMessage());
		throw e;
		
		
	}
	
	return resultObject;
	}
	
	public ResultObject deSelectEmployee(Map inputMap) throws Exception {

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		logger
				.info("Inside  deSelectEmployee method of TransferEmployeeServiceImpl Class");

		try {
			OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(
					HrEisOtherDtls.class, serv.getSessionFactory());
			ArrayList empIdLst = (ArrayList) inputMap.get("EmpIdArray");
			GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(
					OrgUserpostRlt.class);
			gen.setSessionFactory(serv.getSessionFactory());
			long orgEmpId = 0;
			if (empIdLst != null && empIdLst.size() > 0) {
				logger.info("No of employees yo be deselected is"
						+ empIdLst.size());

				for (int i = 0; i < empIdLst.size(); i++) {
					orgEmpId = Long.valueOf(empIdLst.get(i).toString());

					Date sysDate = new Date();
				/*	Calendar d = Calendar.getInstance();
					if (sysDate.getMonth() == 3 || sysDate.getMonth() == 5
							|| sysDate.getMonth() == 8
							|| sysDate.getMonth() == 10)
						d.set(Calendar.DATE, 30);
					else if (sysDate.getMonth() == 1)
						d.set(Calendar.DATE, 28);
					else
						d.set(Calendar.DATE, 31);

					if (sysDate.getMonth() == 0) {
						d.set(Calendar.YEAR, sysDate.getYear() - 1 + 1900);
						d.set(Calendar.MONTH, 11);
					} else {
						d.set(Calendar.YEAR, sysDate.getYear() + 1900);
						d.set(Calendar.MONTH, sysDate.getMonth() - 1);
					}
					
					
					logger.info("modified time is  for end  date is "
							+ d.getTime());*/
					 Calendar d = Calendar.getInstance();
					  if(sysDate.getMonth()==3 || sysDate.getMonth()==5 || sysDate.getMonth()==8 || sysDate.getMonth()==10)
						  d.set(Calendar.DATE, 31);
					  else if(sysDate.getMonth() == 2)
					  { if(sysDate.getYear()%4==0)
						  	d.set(Calendar.DATE, 29);
					  	else
					  		d.set(Calendar.DATE, 28);
					  }
					  else
						  d.set(Calendar.DATE, 30);
					 
					  	 
					  if(sysDate.getMonth()==0){
						  d.set(Calendar.YEAR,sysDate.getYear()-1+1900);
						  d.set(Calendar.MONTH,11);
					  }
					  else{
						  d.set(Calendar.YEAR,sysDate.getYear()+1900);
						  d.set(Calendar.MONTH,sysDate.getMonth()-1);
					  }
					  logger.info("modified time is  for end  date is "+d.getTime());

					if (orgEmpId != 0) {
						OrgUserpostRlt orgUserpostRlt = otherDetailDAOImpl.getEmplConfiguration(orgEmpId,false);
						if (orgUserpostRlt != null) {
							orgUserpostRlt.setActivateFlag(0);
							orgUserpostRlt.setEndDate(d.getTime());
							gen.update(orgUserpostRlt); // to make old post
														// vacant
							logger.info("PK of source orguserpostRlt is "
									+ orgUserpostRlt.getEmpPostId());
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error is: "+ e.getMessage());
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			logger
					.info("Exception Occurred in TransferEmployeeServiceImpl.deselectEmployee method "
							+ e.getMessage());
			throw e;

		}
		return resultObject;
	}
}
