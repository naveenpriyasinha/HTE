package com.tcs.sgv.eis.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.BatchITGpfDetailsUpdateDaoImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;

import com.tcs.sgv.eis.valueobject.BatchITGpfDetailsCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.eis.util.BatchITGpfDetailsUpdateServiceImplHelper;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;



public class BatchITGpfDetailsUpdateServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog( getClass() );
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");

	public ResultObject showITGpfDetails(Map objectArgs) // throws NamingException, IllegalStateException, SecurityException, SystemException 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		//		Context initCtx = new InitialContext();
		//		UserTransaction ut = (UserTransaction) initCtx.lookup("java:comp/UserTransaction");
		try 
		{
			String msg = "";
			// ut.begin();

			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			BatchITGpfDetailsUpdateDaoImpl BatchITGpfDetailsUpdateDao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			BatchITGpfDetailsCustomVO customVO = new BatchITGpfDetailsCustomVO();
			String billNo = "";
			if(voToService != null)
			{
				if(voToService.get("billNo")!=null)
					billNo=(String)voToService.get("billNo").toString();
			}
			logger.info(":::>>>>In service class  Bill Number = " + billNo);

			String empName = "";
			long psrNo = 0;
			String dsgn = "";
			long IT = 0;
			long Gpf = 0;
			long itPk = 0;
			long gpfPk = 0;
			long empId = 0;
			long empType = 0;
			List DataList = new ArrayList();
			if(voToService != null)
			{
				if(voToService.get("billNo")!=null)
					DataList = BatchITGpfDetailsUpdateDao.getITGpfDataforDisplay(billNo, locId);
			}
			List listedData = new ArrayList();	
			if( DataList!=null )
			{
				for (int i = 0; i < DataList.size(); i++) 
				{
					customVO = new BatchITGpfDetailsCustomVO();
					Object rowList[] = (Object[]) DataList.get(i);

					if(rowList[0] != null && !(rowList[0].toString().trim()).equals(""))
					{
						empName = rowList[0].toString();
						customVO.setEmpName(empName);
					}
					if(rowList[7] != null && !(rowList[7].toString().trim()).equals(""))
					{
						psrNo = Long.parseLong(rowList[7].toString());
						customVO.setPsrNo(psrNo);
					}
					if(rowList[2] != null && !(rowList[2].toString().trim()).equals(""))
					{
						dsgn = rowList[2].toString();
						customVO.setDsgn(dsgn);
					}
					if(rowList[3] != null && !(rowList[3].toString().trim()).equals(""))
					{
						IT = Long.parseLong(rowList[3].toString());
						customVO.setIT(IT);
					}
					if(rowList[4] != null && !(rowList[4].toString().trim()).equals(""))
					{
						Gpf = Long.parseLong(rowList[4].toString());
						customVO.setGpf(Gpf);
					}
					if(rowList[8] != null && !(rowList[8].toString().trim()).equals(""))
					{
						itPk = Long.parseLong(rowList[8].toString());
						customVO.setItPk(itPk);
					}
					if(rowList[9] != null && !(rowList[9].toString().trim()).equals(""))
					{
						gpfPk = Long.parseLong(rowList[9].toString());
						customVO.setGpfPk(gpfPk);
					}
					if(rowList[6] != null && !(rowList[6].toString().trim()).equals(""))
					{
						empId = Long.parseLong(rowList[6].toString());
						customVO.setEmpId(empId);
					}
					if(rowList[10] != null && !(rowList[10].toString().trim()).equals(""))
					{
						empType = Long.parseLong(rowList[10].toString());
						customVO.setEmpType(empType);
					}
					listedData.add(customVO);
				}//end for
			}//end if
			List billList = new ArrayList(); 
			List<HrPayBillHeadMpg> BillList = new ArrayList();		
			billList = BatchITGpfDetailsUpdateDao.getBillListForDisplay(locId);
			for(Iterator itr=billList.iterator();itr.hasNext();)
			{    			
				Object[] row = (Object[])itr.next();
				HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();					
				hrPayBillHeadMpg.setBillId(Long.parseLong(row[0].toString()));
				hrPayBillHeadMpg.setBillHeadId(Long.parseLong(row[1].toString()));		 	
				BillList.add(hrPayBillHeadMpg);
			}   

			logger.info(" :::::::::::::::::::::: bill number from service in view :" + billNo);

			objectArgs.put("BillList", BillList);
			objectArgs.put("DataList", listedData);
			objectArgs.put("curbill", billNo);
			resObj.setViewName("viewBatchITGpfDetailsUpdate");
			resObj.setResultCode(0);

			resObj.setResultValue(objectArgs);

		}
		catch (Exception ex) {
			//ut.rollback();
			resObj.setThrowable(ex);
			logger.error("Update IT-GPF  Screen Showing Error ---------- 5 ------->>>>>>> ");
			logger.info("Exception in Read File --------->" +ex.getMessage());
			resObj.setResultCode(-1);
		}
		return resObj;
	}
	public ResultObject UpdateBatchITGpfDetails(Map objectArgs) // throws NamingException, IllegalStateException, SecurityException, SystemException 
	{
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
		
		try {
			
			
			
			
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			List pkITids = (List) objectArgs.get("pkITids");
			List pkGpfids = (List) objectArgs.get("pkGpfids");
			List ITlist = (List) objectArgs.get("ITlist");
			List Gpflist =(List) objectArgs.get("Gpflist");
			List empIds =(List) objectArgs.get("empIds");
			long billNo = Long.parseLong(objectArgs.get("billNo").toString());
			String BillNo = objectArgs.get("billNo").toString();
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString()).longValue();
			long loggedInUser = StringUtility.convertToLong(loginMap.get("userId").toString());
			long loggedInpostId = Long.parseLong(loginMap.get("loggedInPost").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());// For Language independent
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			
			long IncomeTax = Long.parseLong(rb.getString("IncomeTax").toString());// Constant Income Tax Type Code  for IT-GPF UPDATE Screen
			long dbId = Long.parseLong(rb.getString("dbId").toString());// Constant DB ID Code  for IT-GPF UPDATE Screen
			
			GenericDaoHibernateImpl hrPayPayBillDao = new GenericDaoHibernateImpl(HrPayPaybill.class);
			hrPayPayBillDao.setSessionFactory(serv.getSessionFactory());
			
			
			BatchITGpfDetailsUpdateDaoImpl BatchITDetailsUpdateDao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			CmnLocationMstDaoImpl cmnLocationMstDAOImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory()); 
			EmpInfoDAOImpl empDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDao = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDao.read(dbId);
			GenericDaoHibernateImpl BatchGpfDetailsUpdateDao = new GenericDaoHibernateImpl(HrPayVpfDtls.class);
			GenericDaoHibernateImpl hrPayDeducTypeMstDaoImpl = new GenericDaoHibernateImpl(HrPayDeducTypeMst.class);
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst loggedInOrgUserMst=orgUserMstDaoImpl.read(loggedInUser);
			BatchGpfDetailsUpdateDao.setSessionFactory(serv.getSessionFactory());
			hrPayDeducTypeMstDaoImpl.setSessionFactory(serv.getSessionFactory());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst loggedInOrgPostMst = orgPostMstDaoImpl.read(loggedInpostId);
			
			
			
			IdGenerator idgen=new IdGenerator();
			Date sysDate = new Date();
			
			long deductionDetailCode = 0;
			long payVpfId = 0;
			int tempIT = ITlist.size();
			int tempGpf = Gpflist.size();
			String msg = "";
			HrPayDeductionDtls  hrPayDeductionDtls = new HrPayDeductionDtls();
			HrPayVpfDtls hrPayVpfDtls = new HrPayVpfDtls();
			CmnLocationMst cmnLocationMst = cmnLocationMstDAOImpl.read(locId);
			
			
			BatchITGpfDetailsUpdateServiceImplHelper helper = new BatchITGpfDetailsUpdateServiceImplHelper(serv);
			//added by manish
			
			
			
			//ended by manish
			
			
			// IT Detail Updating ........
			if(ITlist.size() == pkITids.size() && ITlist.size() > 0) 
			{
				for(int i = 0; i < ITlist.size(); i++)
				{
					HrEisEmpMst hrEisEmpMst = empDao.read(Long.parseLong(empIds.get(i).toString()));
					if((pkITids.get(i))!=null && !(pkITids.get(i).equals("")) && Long.parseLong(pkITids.get(i).toString()) > 0)
					{
						helper.updateBatchITDetails(pkITids, deductionDetailCode, i, ITlist, loggedInUser, loggedInpostId);
						tempIT = tempIT - 1;
					
					
					}
					else if(Long.parseLong(ITlist.get(i).toString()) > 0 )
					{
						helper.insertBatchITDetails(serv, cmnLocationMst, i, ITlist, hrEisEmpMst, loggedInUser, loggedInpostId, deductionDetailCode, dbId, hrPayDeductionDtls, userId, langId, locId, IncomeTax);
						tempIT = tempIT - 1;
					}
					
				
					
					
					helper.updateInHrEisOtherDtls(ITlist, i, hrEisEmpMst);
					////new code starts
					//added by manish
					
					helper.insertChangedRecord(  hrEisEmpMst,  BillNo,   locId,  dbId,  userId,  langId);
										/////new code 
					
					
				}
			}
			
			// GPF Detail Updating ........
			if(Gpflist.size() == pkGpfids.size()) 
			{
				for(int i = 0; i < pkGpfids.size(); i++)
				{	
					HrEisEmpMst eisEmpMst = empDao.read(Long.parseLong(empIds.get(i).toString()));
					if(Long.parseLong(pkGpfids.get(i).toString()) > 0)
					{
						logger.info("updategpfdtls in serviceimpl 12121212121212121212");
						helper.updateGPFDtls(serv, pkGpfids, i, Gpflist, payVpfId, loggedInUser, loggedInpostId);
						tempGpf = tempGpf - 1;
						//objectArgs.put("msg", "Record Updated Successfully");
						//objectArgs.get("msg");
						logger.info("deductionDetailCode : " + payVpfId + " IT deducted    : " + (Gpflist.get(i).toString()));
					}
					else if(Long.parseLong(Gpflist.get(i).toString()) > 0 )
					{
						
						helper.insertGPFDtls(serv, userId, langId, locId, empIds, i, hrPayVpfDtls, Gpflist, cmnLocationMst, loggedInUser, loggedInpostId);
						tempGpf = tempGpf - 1;
						//objectArgs.put("msg", "Record Inserted Successfully");
					}
					//start 
					//added by manish
					 helper.insertChangedRecordGPF(eisEmpMst, BillNo, locId, dbId, userId, langId);
					//end 
				}
			}
			
			
			//if(tempIT == 0 && ITlist.size() > 0 || tempGpf == 0 && Gpflist.size() > 0)
			if(tempIT != 0 && ITlist.size() > 0 || tempGpf != 0 && Gpflist.size() > 0)
				msg = "Record(s) Successfully Completed";
			else
				msg = " No Record Updated ";

			List BillList = helper.getBillList(serv, locId);

			objectArgs.put("BillList", BillList);
			objectArgs.put("msg", msg);
			objectArgs.put("curbill", billNo);
			resObj.setViewName("viewBatchITGpfDetailsUpdate");
			resObj.setResultCode(0);
			resObj.setResultValue(objectArgs);
		}
		catch (Exception ex) 
		{
			resObj.setThrowable(ex);
			logger.error("Batch IT Gpf Details Update Screen Showing all Post Error", ex);
			resObj.setResultCode(-1);
		}
		return resObj;
	}
	
	
	
	
}