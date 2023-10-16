package com.tcs.sgv.hr.payincrement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.valueobject.WfDocMst;
import com.tcs.sgv.hr.payincrement.dao.PayIncDAO;
import com.tcs.sgv.hr.payincrement.dao.PayIncDAOImpl;
import com.tcs.sgv.hr.payincrement.dao.PayIncrementDao;
import com.tcs.sgv.hr.payincrement.dao.PayIncrementDaoImp;
import com.tcs.sgv.hr.payincrement.dao.PayincGeneralDao;
import com.tcs.sgv.hr.payincrement.dao.PayincGeneralDaoImp;
import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
import com.tcs.sgv.hr.payincrement.valueobject.HrPayincTxn;
import com.tcs.sgv.hr.payincrement.valueobject.HstHrEisPayInc;
import com.tcs.sgv.hr.payincrement.valueobject.PayincGeneralEmpInfoVO;

/**
 * @author 218914 business logic for the application
 */
public class PayIncrementServiceImpl extends ServiceImpl implements PayIncrementService {
	Log logger = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#Month(java.util.Map)
	 */
	public ResultObject Month(Map objectArgs)/*
												 * method to get dropdown menu
												 * for first page
												 */
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		try {

			long langId = StringUtility.convertToLong(loginDetailsMap.get(
					"langId").toString());

			

			CmnLookupMstDAO cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			// List<CmnLookupMst> religionList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Religion",1);
			List<CmnLookupMst> MonthList = cmnLookupMstDAOImpl
					.getAllChildrenByLookUpNameAndLang("Month", langId);

			/*
			 * PayIncDAO payIncDAO = new PayIncDAO(CmnLookupMst.class,
			 * serv.getSessionFactory()); List MonthList =payIncDAO.MonthList();
			 */

			objectArgs.put("MonthList", MonthList);

			CmnLookupMstDAO cmnLookupMstDAOImpl1 = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			// List<CmnLookupMst> religionList=
			// (List<CmnLookupMst>)cmnLookupMstDAOImpl.getAllChildrenByLookUpNameAndLang("Religion",1);
			List<CmnLookupMst> YearList = cmnLookupMstDAOImpl1
					.getAllChildrenByLookUpNameAndLang("Year", langId);

			objectArgs.put("YearList", YearList);

			OrgDesignationMst desigMstVO = (OrgDesignationMst) objectArgs
					.get("desigMstVO");
			PayIncDAO desigDao = new PayIncDAOImpl(OrgDesignationMst.class, serv
					.getSessionFactory());
		

			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("PayInc");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultObject;
	}

	/*
	 * method to search the conditions basic search method with all other
	 * methods being called
	 */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#SearchDesig(java.util.Map)
	 */
	public ResultObject SearchDesig(Map objectArgs) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	
		CmnLookupMst CmnLookupMst = (CmnLookupMst) objectArgs
				.get("CmnLookupMst");
		
		List actionList = new ArrayList();
		
		List leavelist = new ArrayList();
		PayincGeneralEmpInfoVO actionList2;
		long millisec = 0;
		double Effective_Lwp = 0;
		 Map   loginMap = (Map) objectArgs.get("baseLoginMap");
		
		 long langId = Long.parseLong(loginMap.get("langId").toString());

		int i = 0;
		try {
			if (objRes != null && objectArgs != null) {
				ServiceLocator serv = (ServiceLocator) objectArgs
						.get("serviceLocator");
				

				{

					PayIncDAO desigmasterDao = new PayIncDAOImpl(HrEisPayInc.class,
							serv.getSessionFactory());

					String datee = CmnLookupMst.getLookupName();
					String datee1 = CmnLookupMst.getLookupDesc();
                    
					/* without designation search */
					actionList = desigmasterDao.displaydetails4(datee, datee1);
					ArrayList newActionList = new ArrayList();
			
					for (i = 0; i < actionList.size(); i++) {
						HrEisPayInc eishrpayinc = (HrEisPayInc) actionList
								.get(i);

						long uerid = eishrpayinc.getUserId();

						PayincGeneralDao empInfoDaoImpl = new PayincGeneralDaoImp(
								HrPayincTxn.class, serv.getSessionFactory());
						actionList2 = empInfoDaoImpl.findByEmpIDOtherDetail(uerid,langId);

						PayincGeneralEmpInfoVO empInfoVO = new PayincGeneralEmpInfoVO();

						empInfoVO.setLwp(eishrpayinc.getLwp());
						// empInfoVO.setReqpayid(eishrpayinc.getReqPayId());

						empInfoVO.setEmpName(actionList2.getEmpName());
						empInfoVO.setDesignation(actionList2.getDesignation());
						empInfoVO.setUserid(uerid);

						empInfoVO.setActualincdate(eishrpayinc.getNextIncDt());
						empInfoVO.setPresentpayscale(actionList2.getSalary());
						empInfoVO.setSalary(actionList2.getSalary());
						empInfoVO.setLastincDate(eishrpayinc.getLastIncDt());

						/* calculating lwp */
						/*LeaveDao leaveDAOImpl = new LeaveDAOImpl(
								HrEssLeaveMainTxn.class, serv.getSessionFactory());*/
						leavelist = null;//leaveDAOImpl.findLeavesByUserId(eishrpayinc.getUserId(), eishrpayinc								.getLastIncDt(), eishrpayinc.getNextIncDt(), 13);
						Iterator itr = leavelist.iterator();
						double noofsanction_days=0.0;
						while(itr.hasNext()){
						Map leaveDtl=(HashMap)itr.next();
						noofsanction_days=((Double)leaveDtl.get("approvedDays")).doubleValue();
						}
					
						double total_lwp = 0.0;
                       
						if (noofsanction_days > 0.0) {
							

							
							

								total_lwp =  noofsanction_days;
								// millisec= 86400000 * total_lwp;
								if ((eishrpayinc.getDeffdFlag()).equals("D")
										|| (eishrpayinc.getDeffdFlag())
												.equals("d")) {

									Effective_Lwp = total_lwp
											- eishrpayinc.getLwp();

									 millisec = (long)(86400000 * Effective_Lwp);
								}

								else {

									millisec = (long)(86400000 * total_lwp);
								}
						
						}

						long millisec1 = eishrpayinc.getNextIncDt().getTime();

						
						Date d = new Date(millisec1 + millisec);
						

						empInfoVO.setLwp((long)((total_lwp)));
						int datenextinx = d.getDate();

						if (datenextinx > 15) {
							int totaldays = 32 - new Date(d.getYear(), d
									.getMonth(), 32).getDate();
							int diffdays = totaldays - d.getDate() + 1;
							long totaltiem = d.getTime() + diffdays * 86400000;
							Date effecdate = new Date(totaltiem);
							int montth = d.getMonth() + 2;
							int yearr = d.getYear() + 1900;
							String efecdate = 01 + "/" + montth + "/" + yearr;
							Date efecdatee = StringUtility
									.convertStringToDate(efecdate);
							empInfoVO.setEffectiveincdate(efecdatee);
						} else {

							int montth = d.getMonth() + 1;
							int yearr = d.getYear() + 1900;
							String efecdate = 01 + "/" + montth + "/" + yearr;
							Date efecdatee = StringUtility
									.convertStringToDate(efecdate);
							empInfoVO.setEffectiveincdate(efecdatee);

						}						// empInfoVO.setSalary(actionList2.getSalary());

						newActionList.add(empInfoVO);

					}
					objectArgs.put("actionList", newActionList);

					objRes.setResultCode(ErrorConstants.SUCCESS);
					objRes.setResultValue(objectArgs);
					// objectArgs.put("abcd",map);
					objRes.setViewName("diplay");

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("\n Error occur in insertion of Leave details");
			Map result = new HashMap();
			objRes.setResultValue(result);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("searcherror");

		}

		return objRes;
	}

	/* transaction table data storage */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#transactiondata(java.util.Map)
	 */
	public ResultObject transactiondata(Map objectArgs) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		HrEisPayInc eisPayInc = (HrEisPayInc) objectArgs.get("HrEisPayInc");
		List actionList = new ArrayList();
		
		List actionList3 = new ArrayList();
		List leavelist = new ArrayList();
		Long millisec;
		PayincGeneralEmpInfoVO actionList4;
		double Effective_Lwp = 0;
		Date sysdate = new Date();
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long userID = Long.parseLong(loginMap.get("userId")
				.toString());
		long postId = Long.parseLong(loginMap.get("loggedInPost")
				.toString());
		long dbId = Long.parseLong(loginMap.get("dbId").toString());
		long locId = Long.parseLong(loginMap.get("locationId")
				.toString());
		long langId = Long.parseLong(loginMap.get("langId")
				.toString());
		
		
		try {
			if (objRes != null && objectArgs != null) {
				ServiceLocator serv = (ServiceLocator) objectArgs
						.get("serviceLocator");
				
				{

					PayIncDAOImpl desigmasterDao = new PayIncDAOImpl(HrEisPayInc.class,
							serv.getSessionFactory());
					long usrid = eisPayInc.getUserId();
					
					actionList = desigmasterDao.displaydetails6(usrid);

					HrEisPayInc eishrpayinc = (HrEisPayInc) actionList.get(0);
					HrPayincTxn eisPayincTran = new HrPayincTxn();
					OrgPostDetailsRltDaoImpl orgPostMstDaoImpl = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
					OrgPostDetailsRlt orgpostMst=orgPostMstDaoImpl.getPostDetailsRltByPostIdAndLangId(postId, langId);
					long branchid=orgpostMst.getCmnBranchMst().getBranchId();
					objectArgs.put("Subject","300008");//doc id
					 objectArgs.put("Description","PayIncrementFile File");
					 objectArgs.put("section",branchid+"");//branch id
					 objectArgs.put("letterno","INC");//Tri code
					 objectArgs.put("SubCode","10");
					 PayIncDAO payfixdao = new PayIncDAOImpl(WfDocMst.class, serv.getSessionFactory());
		    			long nxtpostId=payfixdao.getHigherHierachyPostId(postId+"",300008);
		    			if(nxtpostId!=0)
		    			{
		    			objectArgs.put("postIdForwardTo",nxtpostId);
		    			objectArgs.put("fileForwardFlag","yes");
		     			 
		    			}
					 
					
					 ResultObject objres1 = serv.executeService("CREATEFMSFILEVO", objectArgs);
					 Map resultMap = (Map) objres1.getResultValue();
					 String fileId=resultMap.get("fileId").toString();
					 
				
					 long reqidt=Long.parseLong(fileId);
					PayIncDAOImpl desigmasterDao1 = new PayIncDAOImpl(
							HrModEmpRlt.class, serv.getSessionFactory());

					PayincGeneralDao empInfoDaoImpl = new PayincGeneralDaoImp(
				
							HrPayincTxn.class, serv.getSessionFactory());
				
					actionList4 = empInfoDaoImpl.findByEmpIDOtherDetail(usrid,langId);
					
					actionList3 = desigmasterDao1.displaydetails8(actionList4.getEmpid());
					HrEisOtherDtls eisOtherDtls = (HrEisOtherDtls) actionList3
							.get(0);

					List actionList1 = desigmasterDao.Payscale(eisOtherDtls
							.getHrEisSgdMpg().getHrEisScaleMst().getScaleId());
					
					

					

					eisPayincTran.setReqTranId(reqidt);
					OrgUserMst orgUserMst=new OrgUserMst();
					orgUserMst.setUserId(eisPayInc.getUserId());
					eisPayincTran.setOrgUserMst(orgUserMst);
					eisPayincTran.setLastIncDate(eishrpayinc.getLastIncDt());
					eisPayincTran.setActIncDate(eishrpayinc.getNextIncDt());

					/* method to calculate effective LWP */
					/*LeaveDao leaveDAOImpl = new LeaveDAOImpl(
							HrEssLeaveMainTxn.class, serv.getSessionFactory());*/
					leavelist = null;//leaveDAOImpl.findLeavesByUserId(eishrpayinc.getUserId(), eishrpayinc							.getLastIncDt(), eishrpayinc.getNextIncDt(), 13);
					Iterator itr = leavelist.iterator();
					double noofsanction_days=0.0;
					while(itr.hasNext()){
					Map leaveDtl=(HashMap)itr.next();
					noofsanction_days=((Double)leaveDtl.get("approvedDays")).doubleValue();
					}
				
					double total_lwp = 0.0;
                  
					if (noofsanction_days > 0.0) {
						

						
						

							total_lwp =  noofsanction_days;
							// millisec= 86400000 * total_lwp;
							if ((eishrpayinc.getDeffdFlag()).equals("D")
									|| (eishrpayinc.getDeffdFlag())
											.equals("d")) {

								Effective_Lwp = total_lwp
										- eishrpayinc.getLwp();

								 millisec = (long)(86400000 * Effective_Lwp);
							}

							else {

								millisec = (long)(86400000 * total_lwp);
							}
					
					}


					eisPayincTran.setApprovalFlag("F");
					eisPayincTran.setLwp((long)(Effective_Lwp));
					eisPayincTran.setCompFlag("SysComp");
					/* login code */
					CmnDatabaseMst cmnDatabaseMst=new CmnDatabaseMst();
					cmnDatabaseMst.setDbId(dbId);
					eisPayincTran.setCmnDatabaseMst(cmnDatabaseMst);
					OrgUserMst userMst=new OrgUserMst();
					userMst.setUserId(userID);
					OrgPostMst postMst=new OrgPostMst();
					postMst.setPostId(postId);
					eisPayincTran.setOrgPostMstCreatedByPost(postMst);
					CmnLocationMst cmnLocationMst=new CmnLocationMst();
					cmnLocationMst.setLocId(locId);
					eisPayincTran.setCmnLocationMst(cmnLocationMst);
					eisPayincTran.setOrgUserMstCreatedBy(userMst);

					Date createddate = new Date();
					eisPayincTran.setCreatedDate(createddate);
					eisPayincTran.setUpdatedDate(null);
				

					
					
					
					
					
					
					long efeective_Lwp = (long)(Effective_Lwp);
					millisec = 86400000 * efeective_Lwp;
					long millisec1 = eisPayincTran.getActIncDate().getTime();

				
					Date d = new Date(millisec1 + millisec);
			

					Date efectivedate;
					int datenextinx = d.getDate();

					

					if (datenextinx > 15) {
						int totaldays = 32 - new Date(d.getYear(),
								d.getMonth(), 32).getDate();
						int diffdays = totaldays - d.getDate() + 1;
						long totaltiem = d.getTime() + diffdays * 86400000;
						Date effecdate = new Date(totaltiem);
						int montth = eisPayincTran.getActIncDate().getMonth() + 2;
						int yearr = eisPayincTran.getActIncDate().getYear() + 1900;
						String efecdatee = 01 + "/" + montth + "/" + yearr;
						
						efectivedate = StringUtility
								.convertStringToDate(efecdatee);
						
						// eisPayincTran.setUpdatedDate(efectivedate);]
						
					} else {

						int montth = eisPayincTran.getActIncDate().getMonth() + 1;
						int yearr = eisPayincTran.getActIncDate().getYear() + 1900;
						String efecdatee = 01 + "/" + montth + "/" + yearr;
					
						efectivedate = StringUtility
								.convertStringToDate(efecdatee);
						
					}

					/* calculating neXt increment date */
					int nextincmonth = efectivedate.getMonth() + 1;
					int nextincyear = efectivedate.getYear() + 1901;
					Date nextincdate = StringUtility.convertStringToDate(01
							+ "/" + nextincmonth + "/" + nextincyear);
					eisPayincTran.setSysComNxtincDate(nextincdate);
				

					long userid = eisPayincTran.getOrgUserMst().getUserId();
		
					
					eisPayincTran.setSysComSalary(actionList4.getSalary()+eisOtherDtls.getHrEisSgdMpg()
					.getHrEisScaleMst().getScaleIncrAmt());
					
					desigmasterDao.create(eisPayincTran);
					
					
					
					/* method for modrlt table insertion */

					HrModEmpRlt empRlt = new HrModEmpRlt();

					empRlt.setReqId(reqidt);

					empRlt.setBasicSal(eisOtherDtls.getotherCurrentBasic());

					empRlt.setUserId(eishrpayinc.getUserId());

					HrModMst hrModMstNew = new HrModMst(new Long(8730)
							.longValue());

					empRlt.setHrModMst(hrModMstNew);

					/*-----------------------desigid--------*/
					PayincGeneralDao empInfoDaoImpl1 = new PayincGeneralDaoImp(
							OrgDesignationMst.class, serv.getSessionFactory());
					PayincGeneralEmpInfoVO actionList2 = empInfoDaoImpl1
							.findByEmpIDOtherDetail(eishrpayinc.getUserId(),langId);
					OrgDesignationMst desigMst = new OrgDesignationMst();
					desigMst.setDsgnId(actionList2.getDesigid());
					//empRlt.setOrgDesignationMst((OrgDesignationMst) desigMst);
					empRlt.setDsgnId(desigMst);
					/*-----------------------desigid--------*/

					empRlt.setReqId(reqidt);

					HrEisScaleMst scaleMSt = new HrEisScaleMst();

					scaleMSt.setScaleId(eisOtherDtls.getHrEisSgdMpg()
							.getHrEisScaleMst().getScaleId());

					empRlt.setScaleId(scaleMSt);

					empRlt.setCreatedBy(userID);
					empRlt.setCreatedDate(sysdate);
					empRlt.setCreatedByPost(postId);
					desigmasterDao1.create(empRlt);

					loginMap = (Map) objectArgs.get("baseLoginMap");
					langId = StringUtility.convertToLong(loginMap.get("langId")
							.toString());
					
				
				}

				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setResultValue(objectArgs);
			//	objRes.setViewName("LTCMsgPage");
			objRes.setViewName("submit");
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("\n Error occur in insertion of Leave details");
			Map result = new HashMap();
			objRes.setResultValue(result);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			objRes.setViewName("errorPage");
		}

		return objRes;
	}

	/* final display page view method */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#displayDtls(java.util.Map)
	 */
	public ResultObject displayDtls(Map objectArgs) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		List actionlist = new ArrayList();
		List actionlist1 = new ArrayList();
		List actionlist2=new ArrayList();
		
		
		long millisec = 0;
		
		Date efectivedate;
		String Flag =null;
		String comflag =null;
		 Map   loginMap = (Map) objectArgs.get("baseLoginMap");
		 
		 long langId = Long.parseLong(loginMap.get("langId").toString());
		 PayincGeneralEmpInfoVO actionList;
		try {
			if (objRes != null && objectArgs != null) {
			
				ServiceLocator serv = (ServiceLocator) objectArgs
						.get("serviceLocator");
				
					Long requestid = Long.parseLong(objectArgs.get("fileId").toString());
					
					
                    PayIncDAO payIncDAO =new PayIncDAOImpl(HrPayincTxn.class, serv.getSessionFactory());
                    HrPayincTxn    hrEisPayincTran    = payIncDAO.status(requestid);
                    comflag= hrEisPayincTran.getCompFlag();
                    Flag=hrEisPayincTran.getApprovalFlag();
                    PayIncrementDao vacancyDaoImp = new PayIncrementDaoImp(
                    		HrPayincTxn.class, serv.getSessionFactory());
					actionlist = vacancyDaoImp.displaydetails9(requestid);
					actionlist1 = vacancyDaoImp.displaydetails10(requestid);
					HrPayincTxn eisPayincTran = (HrPayincTxn) actionlist.get(0);
					
					
					
					HrModEmpRlt hrModEmpRlt = (HrModEmpRlt) actionlist1.get(0);
				    hrModEmpRlt.getScaleId().getScaleEndAmt();
					hrModEmpRlt.getScaleId().getScaleIncrAmt();
					hrModEmpRlt.getScaleId().getScaleStartAmt();
					String Payscale = hrModEmpRlt.getScaleId()
							.getScaleStartAmt()
							+ "-"
							+ hrModEmpRlt.getScaleId().getScaleIncrAmt()
							+ "-" + hrModEmpRlt.getScaleId().getScaleEndAmt();

					PayincGeneralEmpInfoVO empInfoVO = new PayincGeneralEmpInfoVO();

					// int effectiveincdatem=
					// eisPayincTran.getActIncDate().getMonth()+1;

					/* calculating effective increment date */

					HrEisPayInc eishrpayinc = new HrEisPayInc();
				//	PayincLeaveDAO leaveDAOImpl = new PayincLeaveDAOImpl(
						//	HrLeaveMain.class, serv.getSessionFactory());
					//leavelist = leaveDAOImpl.findLeavesByEmpId(eisPayincTran
							//.getUserId(), eisPayincTran.getLastIncDate(),
							//eisPayincTran.getActIncDate(), 9);

					

					long efeective_Lwp = eisPayincTran.getLwp();
					millisec = 86400000 * efeective_Lwp;
					long millisec1 = eisPayincTran.getActIncDate().getTime();

				
					Date d = new Date(millisec1 + millisec);
			

					empInfoVO.setLwp(efeective_Lwp);
					empInfoVO.setStatus(Flag);
					empInfoVO.setComflag(comflag);
					empInfoVO.setPayscale(Payscale);
					int datenextinx = d.getDate();

					

					if (datenextinx > 15) {
						int totaldays = 32 - new Date(d.getYear(),
								d.getMonth(), 32).getDate();
						int diffdays = totaldays - d.getDate() + 1;
						long totaltiem = d.getTime() + diffdays * 86400000;
						Date effecdate = new Date(totaltiem);
						int montth = eisPayincTran.getActIncDate().getMonth() + 2;
						int yearr = eisPayincTran.getActIncDate().getYear() + 1900;
						String efecdatee = 01 + "/" + montth + "/" + yearr;
						empInfoVO.setFormatedeffDate(efecdatee);
						efectivedate = StringUtility
								.convertStringToDate(efecdatee);
						
						// eisPayincTran.setUpdatedDate(efectivedate);]
						empInfoVO.setEffectiveincdate(effecdate);
						
					} else {

						int montth = eisPayincTran.getActIncDate().getMonth() + 1;
						int yearr = eisPayincTran.getActIncDate().getYear() + 1900;
						String efecdatee = 01 + "/" + montth + "/" + yearr;
						empInfoVO.setFormatedeffDate(efecdatee);
						efectivedate = StringUtility
								.convertStringToDate(efecdatee);
						empInfoVO.setEffectiveincdate(efectivedate);
						
					}

					/* calculating neXt increment date */
					int nextincmonth = efectivedate.getMonth() + 1;
					int nextincyear = efectivedate.getYear() + 1901;
					Date nextincdate = StringUtility.convertStringToDate(01
							+ "/" + nextincmonth + "/" + nextincyear);
					empInfoVO.setNxtincdate(nextincdate);

					 
					long userid = eisPayincTran.getOrgUserMst().getUserId();
					PayincGeneralDao empInfoDaoImpl = new PayincGeneralDaoImp(
							HrPayincTxn.class, serv.getSessionFactory());
					actionList = empInfoDaoImpl.findByEmpIDOtherDetail(userid,langId);
					actionList.getDesignation();
					actionList.getDesignation();
					actionList.getEmpid();
					actionList.getDoj();
					actionList.getSalary();
					actionList.getDor();
					actionList.getEmpName();

				
					empInfoVO.setSalary(actionList.getSalary()
							+ hrModEmpRlt.getScaleId().getScaleIncrAmt());
					PayIncrementDao payIncrementDaoImp = new PayIncrementDaoImp(
							HrEisPayInc.class, serv.getSessionFactory());
					
					
					actionlist2=payIncrementDaoImp.actincdate(userid);
					HrEisPayInc hrEisPayInc =(HrEisPayInc)actionlist2.get(0);
					
					objectArgs.put("actionList", actionList);
					objectArgs.put("eisPayincTran", eisPayincTran);
					objectArgs.put("empInfoVO", empInfoVO);
					
					objectArgs.put("hrEisPayInc", hrEisPayInc);
					objectArgs.put("hrEisPayInccc", hrEisPayInc.getBasicSal());
					
					
				   }
				   
		
			
			
			objRes.setViewName("PayIncDsply");
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
		} 
		
		
		catch (Exception e)
		
		
		{

			e.printStackTrace();
			logger.info("\n Error occur in insertion of Leave details");
			//objRes.setViewName("errorsearch");
		}

		return objRes;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#UpdateMstt(java.util.Map)
	 */
	public ResultObject ApprovePayIncrement(Map objectArgs) {
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	//	HrEisPayInc HrEisPayInc = (HrEisPayInc) objectArgs.get("HrEisPayInc");
	//	HrEisPayincTran HrEisPayincTran = (HrEisPayincTran) objectArgs
			//	.get("HrEisPayincTran");

		
		try {
			if (objRes != null && objectArgs != null) {
				ServiceLocator serv = (ServiceLocator) objectArgs
						.get("serviceLocator");
				// HttpServletRequest request = (HttpServletRequest)
				// objectArgs.get("requestObj");
				PayIncDAOImpl payIncDAOtrans= new PayIncDAOImpl(HrPayincTxn.class, serv
						.getSessionFactory());
				PayIncDAOImpl payIncDAO = new PayIncDAOImpl(HrEisPayInc.class, serv
						.getSessionFactory());
				String fileId=objectArgs.get("fileId").toString();
				HrPayincTxn hrEisPayincTran=(HrPayincTxn)payIncDAOtrans.read(Long.parseLong(fileId));
				HrEisPayInc hreispyin = (HrEisPayInc) payIncDAO
						.read(hrEisPayincTran.getOrgUserMst().getUserId());
			
				long reqid =Long.parseLong(fileId);
				Map loginMap = (Map) objectArgs.get("baseLoginMap");
				long UserID = Long.parseLong(loginMap.get("userId").toString());
				long postId = Long.parseLong(loginMap.get("loggedInPost")
						.toString());
				long dbId = Long.parseLong(loginMap.get("dbId").toString());
				long locId = Long.parseLong(loginMap.get("locationId")
						.toString());
				long langId = Long.parseLong(loginMap.get("langId").toString());
				
				hreispyin.setUpdatedBy(UserID);
				hreispyin.setDbId(dbId);
				hreispyin.setLocId(locId);
				hreispyin.setUpdatedByPost(postId);
				hreispyin.setCreatedBy(UserID);
				Date nwadte = new Date();
				hreispyin.setCreatedDate(nwadte);
				hreispyin.setUpdatedDate(nwadte);
				CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
				cmnLanguageMst.setLangId(langId);
				//payIncDAO.UpdateTran(HrEisPayincTran);
			
				if ((hrEisPayincTran.getCompFlag().equals("Deferred"))){

					hreispyin.setNextIncDt(hrEisPayincTran.getDefferedDate());
					hreispyin.setRemarks(hrEisPayincTran.getRemarks());
					hreispyin.setDeffdFlag("D");
					//HrEisPayInc.getUserId();
					
					hreispyin.setLwp(hrEisPayincTran.getLwp());
					long incamt=0;
					hreispyin.setIncAmt(incamt);
					// hreispyin.setTrnCounter(1);
					try {

						payIncDAO.update(hreispyin);

					} catch (Exception e) {

						e.printStackTrace();
					}
				}

				else {
					
					if (hrEisPayincTran.getCompFlag().equals("UserComp")) {

						hreispyin.setNextIncDt(hrEisPayincTran.getUserComNxtincDate());
						hreispyin.setRemarks(hrEisPayincTran.getRemarks());
						hreispyin.setBasicSal(hrEisPayincTran.getUserComSalary());
						hreispyin.setLastIncDt(hrEisPayincTran.getLastIncDate());
						hreispyin.setDeffdFlag("");
                     	hreispyin.setLwp(hrEisPayincTran.getLwp());
						
						// hreispyin.setTrnCounter(1);
					//	long incAmt = payIncDAO.incamt(reqid);
						hreispyin.setIncAmt(null);

						try {

							payIncDAO.update(hreispyin);

						} catch (Exception e) {

							e.printStackTrace();
						}

					}

					else {

						hreispyin.setNextIncDt(hrEisPayincTran.getSysComNxtincDate());
						hreispyin.setBasicSal(hrEisPayincTran.getSysComSalary());
						
						hreispyin.setLastIncDt(hrEisPayincTran.getLastIncDate());
						long incAmt = payIncDAO.incamt(reqid);
						hreispyin.setIncAmt(incAmt);
						hreispyin.setDeffdFlag("");
						hreispyin.setRemarks("");
						// hreispyin.setTrnCounter(0);

						try {

							payIncDAO.update(hreispyin);

						} catch (Exception e) {

							e.printStackTrace();
						}

					}

				}
				
				if(hrEisPayincTran.getApprovalFlag().equals("DF"))
				{
					hrEisPayincTran.setApprovalFlag("D");
				}
				if(hrEisPayincTran.getApprovalFlag().equals("F"))
				{
					hrEisPayincTran.setApprovalFlag("A");
				}
				// payIncDAO.UpdateMst(HrEisPayInc);
				
			
				payIncDAOtrans.update(hrEisPayincTran);

				
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setResultValue(objectArgs);
				

				
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("\n Error occur while pay increment approval");
			Map result = new HashMap();
			objRes.setResultValue(result);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			///objRes.setViewName("searcherror");

		}
		return objRes;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#UpdateMstf(java.util.Map)
	 */
	public ResultObject UpdateMstf(Map objectArgs) {
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		HrEisPayInc HrEisPayInc = (HrEisPayInc) objectArgs.get("HrEisPayInc");
		HrPayincTxn HrEisPayincTran = (HrPayincTxn) objectArgs
				.get("HrEisPayincTran");

		
		
		try {
			if (objRes != null && objectArgs != null) {
				ServiceLocator serv = (ServiceLocator) objectArgs
						.get("serviceLocator");
				// HttpServletRequest request = (HttpServletRequest)
				// objectArgs.get("requestObj");
				long reqid =HrEisPayincTran.getReqTranId();
				Map loginMap = (Map) objectArgs.get("baseLoginMap");
				long EmpID = Long.parseLong(loginMap.get("userId").toString());
				long postId = Long.parseLong(loginMap.get("loggedInPost")
						.toString());
				long dbId = Long.parseLong(loginMap.get("dbId").toString());
				long locId = Long.parseLong(loginMap.get("locationId")
						.toString());
				long langId = Long.parseLong(loginMap.get("langId").toString());

				PayIncDAOImpl payIncDAO = new PayIncDAOImpl(HrEisPayInc.class, serv
						.getSessionFactory());

				HrEisPayInc hreispyin = (HrEisPayInc) payIncDAO
						.read(HrEisPayInc.getUserId());
				
				
			
			
				if ((HrEisPayInc.getDeffdFlag()).equalsIgnoreCase("D")) {

					
					try {

				
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

				else {

					if (HrEisPayInc.getDeffdFlag().equalsIgnoreCase("N")) {

						
						long incAmt = payIncDAO.incamt(reqid);
						hreispyin.setIncAmt(incAmt);

						try {

						//	payIncDAO.update(hreispyin);  changes made to check some errors

						} catch (Exception e) {

							e.printStackTrace();
						}

					}

					else {

						

						try {

						//	payIncDAO.update(hreispyin);

						} 
						
						catch (Exception e) {

							e.printStackTrace();
						}

					}

				}
				

				// payIncDAO.UpdateMst(HrEisPayInc);
				
				
				payIncDAO.UpdateTran(HrEisPayincTran);


				loginMap = (Map) objectArgs.get("baseLoginMap");
				langId = StringUtility.convertToLong(loginMap.get("langId")
						.toString());
				
			
                
			Map redirectMap = new HashMap();
			redirectMap.put("actionFlag", "displaydetails");				
			redirectMap.put("fileId", HrEisPayincTran.getReqTranId());
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objectArgs.put("redirectMap", redirectMap);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("redirect view");
				
			}

		} catch (Exception e) {

			e.printStackTrace();
			logger.info("\n Error occur while saving pay increment details ");
			Map result = new HashMap();
			objRes.setResultValue(result);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setThrowable(e);
			///objRes.setViewName("searcherror");

		}
		return objRes;

	}
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.service.PayIncrementService#History(java.util.Map)
	 */
	public ResultObject History(Map objectArgs) {

	   	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
	   List actionlist=new ArrayList();
	   List newactionlist=new ArrayList();
	   int  i =0;
	   
	   
	    /* login code */
	        Map   loginMap = (Map) objectArgs.get("baseLoginMap");
			 long EmpID = Long.parseLong(loginMap.get("userId").toString());
			 /*long postId = Long.parseLong(loginMap.get("loggedInPost").toString());
			 long dbId = Long.parseLong(loginMap.get("dbId").toString());
			 long locId =  Long.parseLong(loginMap.get("locationId").toString());
			 long langId = Long.parseLong(loginMap.get("langId").toString());*/

	   	try
	    {
	        if (objRes != null && objectArgs != null)
	        {
	        	ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
	          
	        	PayIncDAO payIncDAO = new PayIncDAOImpl(HstHrEisPayInc.class, serv
						.getSessionFactory());
 
	                actionlist=	payIncDAO.history(EmpID);
	               
	                for (i = 0; i < actionlist.size(); i++) {
	                	HstHrEisPayInc hsteishrpayinc = (HstHrEisPayInc) actionlist
								.get(i);
	                	PayincGeneralEmpInfoVO empInfoVO = new PayincGeneralEmpInfoVO();
	                	
	                	empInfoVO.setPayscale(hsteishrpayinc.getBasicSal()+"");
	                	
	                	empInfoVO.setPresentpayscale(StringUtility.convertToLong(hsteishrpayinc.getPresentPayScale()))	;
	                	empInfoVO.setLastincDate(hsteishrpayinc.getLastIncDt()) ;
	                	empInfoVO.setNxtincdate(hsteishrpayinc.getNextIncDt())	;
	                	empInfoVO.setIncamount(hsteishrpayinc.getIncAmt());
	                	empInfoVO.setLwp(hsteishrpayinc.getLwp());
	                	empInfoVO.setAddress(hsteishrpayinc.getRemarks())	;
	                	newactionlist.add(empInfoVO);
	        	}
	                 
	            	    objectArgs.put("newactionlist", newactionlist);
	                   objRes.setResultCode(ErrorConstants.SUCCESS);
	                 //	objectArgs.put("EmpDet", EmpDetailsVO);
		                logger.info("The objectArgs is : "+objectArgs);
		                objRes.setResultValue(objectArgs);
		                objRes.setViewName("historyinfo");
	                 
	            }
	        
	    }
		      		  
		      		  catch (Exception e)
			            {
			                
			            	e.printStackTrace();
		      			  logger.info("\n Error occur in insertion of pay increment details");
			                 Map result = new HashMap();
			                 objRes.setResultValue(result);
			                 objRes.setThrowable(e);
			                 objRes.setResultCode(ErrorConstants.ERROR);
			                 objRes.setThrowable(e);
			                 objRes.setViewName("errorPage");
			            }

		      		return objRes;
		     	  
	            }	


		     	  
	            }	



