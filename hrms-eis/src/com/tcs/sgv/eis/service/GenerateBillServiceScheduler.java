package com.tcs.sgv.eis.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.dao.SchedulerBillDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybillSchdlrResult;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class GenerateBillServiceScheduler extends  ServiceImpl {
	/*static ServiceLocator serv=null; */


	public static Log logger=LogFactory.getLog(GenerateBillServiceScheduler.class);

	public ResultObject getMethod(Map objectArgs)
	{	
		ResultObject resultObject=new ResultObject(ErrorConstants.SUCCESS); 	
		Session hibSession= null;
		//ResultObject resultObjecttemp=new ResultObject(ErrorConstants.SUCCESS); 	
		try{
			logger.info("inside scedhular code");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			SchedulerBillDAOImpl schedulerBillDAO = new SchedulerBillDAOImpl(HrPayPaybillScheduler.class, serv.getSessionFactory());
			Date currDate= new Date();
			//int month=4;
			//int year=2012;
			Map BaseLoginMap=(Map)objectArgs.get("baseLoginMap");
			Map BaseLoginMapDDO=(Map)objectArgs.get("baseLoginMap");


			String threadId = (String)objectArgs.get("threadId");

			long ddoCode = 0;			//input variable
			long billNo= 0;
			List  lstPayBillNo = new ArrayList();
			List<HrPayPaybillScheduler>  lst = schedulerBillDAO.getAllData(currDate.getDate(),threadId);
			long locId=0;
			long month=0;
			long year=0;
			if(!lst.isEmpty())
			{

				for(HrPayPaybillScheduler paybillScheduler : lst)
				{
					lstPayBillNo = new ArrayList();
					Session hibSession1 = serv.getSessionFactory().openSession();
					hibSession1.getTransaction().begin();

					ddoCode = paybillScheduler.getDdoCode();
					billNo = paybillScheduler.getBillNo();
					locId = paybillScheduler.getLocId();
					month = paybillScheduler.getMonth() != 0  ? paybillScheduler.getMonth() : (currDate.getMonth()+1);
					year = paybillScheduler.getYear() != 0 ? paybillScheduler.getYear() : (currDate.getYear() + 1900);

					if(billNo!=0)
						lstPayBillNo.add(billNo);
					else
						lstPayBillNo= schedulerBillDAO.getPayBillNo(ddoCode);
					//Activate Flag 1 means schedular is running for Paybill Generation
					if(paybillScheduler.getActivateflag() == 1){
						DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
						Long postId = lObjDcpsCommonDAO.getDDOAsstPostIdForDDO(String.valueOf(ddoCode).trim());
						Long userId = lObjDcpsCommonDAO.getUserIdForPostId(postId);
						Long postIdDDO = lObjDcpsCommonDAO.getDDOPostIdForDDO(String.valueOf(ddoCode).trim());
						Long userIdDDO = lObjDcpsCommonDAO.getUserIdForPostId(postIdDDO);
						boolean ddoPayBillActivateFlag = true;
						int size = lstPayBillNo.size();
						for(int j=0;j<size;j++)
						{
							ddoPayBillActivateFlag = true;
							long PayBillNo= lstPayBillNo.get(j)!= null ? Long.valueOf(lstPayBillNo.get(j).toString()):0;
							logger.error("Generating payBillNo : "+PayBillNo +" of ddo code :"+ddoCode + " By Scheduler");
							if(!schedulerBillDAO.checkPaybill(Long.valueOf(month).intValue(),Long.valueOf(year).intValue(),PayBillNo))
							{
								objectArgs.put("paybillNo",PayBillNo);

								List billStructLst = schedulerBillDAO.getBillStructure(PayBillNo);
								Object[] rowDetail=(Object[])billStructLst.get(0);
								logger.info("Bill Structure::::::::::::::::::::::::::::" + rowDetail.length);

								String demandNo=rowDetail[0] != null ? rowDetail[0].toString():null;
								String mjrHead= rowDetail[1] != null ? rowDetail[1].toString():null;
								String subMjrHead= rowDetail[2] != null ? rowDetail[2].toString():null;
								String mnrHead= rowDetail[3] != null ? rowDetail[3].toString():null;
								String subHead= rowDetail[4] != null ? rowDetail[4].toString():null;
								String schemeCode= rowDetail[5] != null ? rowDetail[5].toString():null;

								Character successFlag= 'N';
								String errorMessage="";

								if(demandNo != null && mjrHead!= null && subMjrHead != null && mnrHead!=null && subHead!= null && schemeCode !=null){ 
									objectArgs.put("demandNo",demandNo);
									objectArgs.put("mjrHead",mjrHead);
									objectArgs.put("subMjrHead",subMjrHead);
									objectArgs.put("mnrHead",mnrHead);
									objectArgs.put("subHead",subHead);
									objectArgs.put("from_Month",month);
									objectArgs.put("from_Year",year); 
									objectArgs.put("month",month);
									objectArgs.put("year",year);
									objectArgs.put("schemeCodeForBG",schemeCode);
									objectArgs.put("billTypeCmb","paybill");

									//Details of DDO Asst
									BaseLoginMap.put("locationId",locId);
									BaseLoginMap.put("locationCode",locId);
									BaseLoginMap.put("userId",userId);
									BaseLoginMap.put("postId",postId);
									BaseLoginMap.put("CREATED_BY_USER",userId);
									BaseLoginMap.put("CREATED_BY_POST",postId);
									objectArgs.put("baseLoginMap",BaseLoginMap);
									objectArgs.put("CREATED_BY_USER",userId);
									objectArgs.put("CREATED_BY_POST",postId);
									objectArgs.put("DdoAsstPostId", postId);

									OrgPostMst orgPostMst = new OrgPostMst();
									orgPostMst.setPostId(postId);
									OrgUserMst orgUserMst = new OrgUserMst();
									orgUserMst.setUserId(userId);
									CmnLocationMst cmnLocationMst = new CmnLocationMst();
									cmnLocationMst.setLocationCode(String.valueOf(locId));
									cmnLocationMst.setLocId(locId);
									logger.info("Location Id is "+locId);
									LoginDetails baseLoginVO = new LoginDetails();
									baseLoginVO.setDbId(99L);
									baseLoginVO.setLoggedInPost(orgPostMst);
									baseLoginVO.setLangId(1L);
									baseLoginVO.setUser(orgUserMst);
									baseLoginVO.setLocation(cmnLocationMst);
									objectArgs.put("baseLoginVO",baseLoginVO);
									//Details of DDO Asst End


									hibSession=serv.getSessionFactory().openSession();
									Transaction transaction = hibSession.getTransaction();
									transaction.begin();

									logger.info("hibSession  is  ----- "  + hibSession);

									objectArgs.put("currentSession",hibSession);
									objectArgs.put("fromPayBillScheduler",true);
									objectArgs.put("ddoCode", ddoCode);
									objectArgs.put("serviceLocator", serv);

									resultObject=new GenerateBillService().generatePaybill(objectArgs);
									objectArgs = (Map)resultObject.getResultValue();

									hibSession = (Session)objectArgs.get("currentSession");
									errorMessage = objectArgs.get("messageForPaybillSch") != null ? objectArgs.get("messageForPaybillSch").toString():"";
									objectArgs.remove("messageForPaybillSch");
									if(resultObject.getResultCode() == ErrorConstants.ERROR ){
										ddoPayBillActivateFlag = false;
										hibSession.getTransaction().rollback();
									}else{
										hibSession.flush();
										hibSession.getTransaction().commit();
										successFlag= 'Y';
									}
									hibSession.close();
								}else{
									successFlag= 'N';
									ddoPayBillActivateFlag = false;
									errorMessage= "value for one of the head structure component is null";
								}



								HrPayPaybillSchdlrResult hrPayBillSchdlrRslt = new HrPayPaybillSchdlrResult();
								try{
									hibSession=serv.getSessionFactory().openSession();
									hibSession.getTransaction().begin();

									long id = IDGenerateDelegate.getNextId("hr_pay_paybillschdlr_result", objectArgs);
									hrPayBillSchdlrRslt.setId(id);
									hrPayBillSchdlrRslt.setBillNo(PayBillNo);
									hrPayBillSchdlrRslt.setLocid(locId);
									hrPayBillSchdlrRslt.setDdoCode(ddoCode);
									hrPayBillSchdlrRslt.setSuccessFlag(successFlag);
									hrPayBillSchdlrRslt.setThreadId(Long.valueOf(threadId));
									hrPayBillSchdlrRslt.setCreatedDate(new Date());
									hrPayBillSchdlrRslt.setMonth(month);
									hrPayBillSchdlrRslt.setYear(year);
									hrPayBillSchdlrRslt.setErrorMessage(errorMessage);

									hibSession.save(hrPayBillSchdlrRslt);
									hibSession.getTransaction().commit();
								}
								catch(Exception e)
								{
									hibSession.getTransaction().rollback();
									logger.error("Exception occured while saving data of hr_pay_paybillschdlr_result",e);
								}
								finally{
									hibSession.close();
								}
								resultObject.setResultCode(ErrorConstants.SUCCESS);
								resultObject.setResultValue(objectArgs);
							}
							else
							{
								logger.info("Bill is already generated for bill No :"+PayBillNo + "of ddoCode "+ddoCode);
							}
						}
						if(ddoPayBillActivateFlag){
							paybillScheduler.setActivateflag(0);
							hibSession1.update(paybillScheduler);
						}
						hibSession1.getTransaction().commit();
						hibSession1.close();
						//lstPayBillNo.clear();
					}
					//Activate Flag 2 means Scheduler's running for Bill Approval
					if(paybillScheduler.getActivateflag() == 2){
						DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
						Long postId = lObjDcpsCommonDAO.getDDOAsstPostIdForDDO(String.valueOf(ddoCode).trim());
						Long userId = lObjDcpsCommonDAO.getUserIdForPostId(postId);
						boolean ddoPayBillActivateFlag = true;
						int size = lstPayBillNo.size();
						for(int j=0;j<size;j++)
						{
							ddoPayBillActivateFlag = true;
							long PayBillNo= lstPayBillNo.get(j)!= null ? Long.valueOf(lstPayBillNo.get(j).toString()):0;
							logger.error("Approving payBillNo : "+PayBillNo +" of ddo code :"+ddoCode + " By Scheduler");
							if(schedulerBillDAO.checkPaybillApprovedOrNotGenerated(Long.valueOf(month).intValue(),Long.valueOf(year).intValue(),PayBillNo))
							{
								objectArgs.put("paybillNo",PayBillNo);
								objectArgs.put("billNo",PayBillNo);

								List billStructLst = schedulerBillDAO.getBillStructure(PayBillNo);
								Object[] rowDetail=(Object[])billStructLst.get(0);
								logger.info("Bill Structure::::::::::::::::::::::::::::" + rowDetail.length);

								String demandNo=rowDetail[0] != null ? rowDetail[0].toString():null;
								String mjrHead= rowDetail[1] != null ? rowDetail[1].toString():null;
								String subMjrHead= rowDetail[2] != null ? rowDetail[2].toString():null;
								String mnrHead= rowDetail[3] != null ? rowDetail[3].toString():null;
								String subHead= rowDetail[4] != null ? rowDetail[4].toString():null;
								String schemeCode= rowDetail[5] != null ? rowDetail[5].toString():null;

								Character successFlag= 'N';
								String errorMessage="";

								objectArgs.put("demandNo",demandNo);
								objectArgs.put("mjrHead",mjrHead);
								objectArgs.put("subMjrHead",subMjrHead);
								objectArgs.put("mnrHead",mnrHead);
								objectArgs.put("subHead",subHead);
								objectArgs.put("from_Month",month);
								objectArgs.put("from_Year",year); 
								objectArgs.put("month",month);
								objectArgs.put("year",year);
								objectArgs.put("schemeCodeForBG",schemeCode);
								objectArgs.put("billTypeCmb","paybill");

								//Details of DDO Asst
								BaseLoginMap.put("locationId",locId);
								BaseLoginMap.put("locationCode",locId);
								BaseLoginMap.put("userId",userId);
								BaseLoginMap.put("postId",postId);
								BaseLoginMap.put("CREATED_BY_USER",userId);
								BaseLoginMap.put("CREATED_BY_POST",postId);
								objectArgs.put("baseLoginMap",BaseLoginMap);
								objectArgs.put("CREATED_BY_USER",userId);
								objectArgs.put("CREATED_BY_POST",postId);
								objectArgs.put("DdoAsstPostId", postId);

								OrgPostMst orgPostMst = new OrgPostMst();
								orgPostMst.setPostId(postId);
								OrgUserMst orgUserMst = new OrgUserMst();
								orgUserMst.setUserId(userId);
								CmnLocationMst cmnLocationMst = new CmnLocationMst();
								cmnLocationMst.setLocationCode(String.valueOf(locId));
								cmnLocationMst.setLocId(locId);
								logger.info("Location Id is "+locId);
								LoginDetails baseLoginVO = new LoginDetails();
								baseLoginVO.setDbId(99L);
								baseLoginVO.setLoggedInPost(orgPostMst);
								baseLoginVO.setLangId(1L);
								baseLoginVO.setUser(orgUserMst);
								baseLoginVO.setLocation(cmnLocationMst);
								objectArgs.put("baseLoginVO",baseLoginVO);
								//Details of DDO Asst End


								hibSession=serv.getSessionFactory().openSession();
								Transaction transaction = hibSession.getTransaction();
								transaction.begin();

								logger.info("hibSession  is  ----- "  + hibSession);

								objectArgs.put("currentSession",hibSession);
								objectArgs.put("fromPayBillScheduler",true);
								objectArgs.put("ddoCode", ddoCode);
								objectArgs.put("serviceLocator", serv);

								resultObject=new GeneratePaySlipService().approvePayBillThroughScheduler(objectArgs);
								objectArgs = (Map)resultObject.getResultValue();

								hibSession = (Session)objectArgs.get("currentSession");
								errorMessage = objectArgs.get("messageForPaybillSch") != null ? objectArgs.get("messageForPaybillSch").toString():"";
								objectArgs.remove("messageForPaybillSch");
								if(resultObject.getResultCode() == ErrorConstants.ERROR ){
									ddoPayBillActivateFlag = false;
									hibSession.getTransaction().rollback();
								}else{
									hibSession.flush();
									hibSession.getTransaction().commit();
									successFlag= 'Y';
								}
								hibSession.close();



								HrPayPaybillSchdlrResult hrPayBillSchdlrRslt = new HrPayPaybillSchdlrResult();
								try{
									hibSession=serv.getSessionFactory().openSession();
									hibSession.getTransaction().begin();
									long id = IDGenerateDelegate.getNextId("hr_pay_paybillschdlr_result", objectArgs);
									hrPayBillSchdlrRslt.setId(id);
									hrPayBillSchdlrRslt.setBillNo(PayBillNo);
									hrPayBillSchdlrRslt.setLocid(locId);
									hrPayBillSchdlrRslt.setDdoCode(ddoCode);
									hrPayBillSchdlrRslt.setSuccessFlag(successFlag);
									hrPayBillSchdlrRslt.setThreadId(Long.valueOf(threadId));
									hrPayBillSchdlrRslt.setCreatedDate(new Date());
									hrPayBillSchdlrRslt.setMonth(month);
									hrPayBillSchdlrRslt.setYear(year);
									hrPayBillSchdlrRslt.setErrorMessage(errorMessage);

									hibSession.save(hrPayBillSchdlrRslt);
									hibSession.getTransaction().commit();
								}
								catch(Exception e)
								{
									hibSession.getTransaction().rollback();
									logger.error("Exception occured while saving data of hr_pay_paybillschdlr_result",e);
								}
								finally{
									hibSession.close();
								}
								resultObject.setResultCode(ErrorConstants.SUCCESS);
								resultObject.setResultValue(objectArgs);
							}
							else
							{
								logger.info("Bill is already Approved or Not Generated only for bill No :"+PayBillNo + "of ddoCode "+ddoCode);
							}
							if(ddoPayBillActivateFlag){
								paybillScheduler.setActivateflag(-2);
								hibSession1.update(paybillScheduler);
							}
							hibSession1.getTransaction().commit();
							hibSession1.close();
						}
						
						
					}
				
				}
				logger.error("lst for threadId " + threadId + " Has been Finished : Varshil ");
			}

		}

		/*}


	}*/
		catch(Exception e)
		{
			//System.out.println("Exception occured in getMethod of GenerateBillServiceScheduler class");
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}
}

