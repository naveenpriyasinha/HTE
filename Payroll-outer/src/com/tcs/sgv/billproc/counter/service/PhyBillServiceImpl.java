/**
 * 
 */
package com.tcs.sgv.billproc.counter.service;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.apps.dao.budget.BudHdDAOImpl;
import com.tcs.sgv.apps.valuebeans.budget.BudExpEstBPNCodeVO;
import com.tcs.sgv.billproc.audit.dao.AuditorObjectionDAOImpl;
import com.tcs.sgv.billproc.cheque.service.TrnChequeServiceImpl;
import com.tcs.sgv.billproc.common.service.ShowBillServiceImpl;
import com.tcs.sgv.billproc.common.valueobject.TrnBillRemarks;
import com.tcs.sgv.billproc.counter.dao.OrgTokenStatusDAOImpl;
import com.tcs.sgv.billproc.counter.dao.PhyBillDAOImpl;
import com.tcs.sgv.billproc.counter.valueobject.BillVO;
import com.tcs.sgv.billproc.counter.valueobject.NewBillVO;
import com.tcs.sgv.billproc.counter.valueobject.OrgTokenStatus;
import com.tcs.sgv.common.dao.BillMovementDAOImpl;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.dao.LocationDAOImpl;
import com.tcs.sgv.common.dao.PostConfigurationDAOImpl;
import com.tcs.sgv.common.dao.WorkFlowDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BillMovementServiceImpl;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.BudgetHdDtlsServiceImpl;
import com.tcs.sgv.common.service.BudgetHdServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.RltBillObjection;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.common.valueobject.TrnBillBudheadDtls;
import com.tcs.sgv.common.valueobject.TrnBillMvmnt;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.common.valueobject.TrnReceiptDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.FrmServiceMstDAOImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.FrmServiceMst;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dss.service.DssDataService;
import com.tcs.sgv.dss.service.DssDataServiceImpl;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;
import com.tcs.sgv.onlinebillprep.valueobject.RltBillChallan;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.appwfinterface.WorkFlowInterfaceImpl;
import com.tcs.sgv.wf.exception.AlternateHierarchyNotfoundException;
import com.tcs.sgv.wf.exception.LowerPostNotFoundException;
import com.tcs.sgv.wf.exception.LowerRoleNotFoundException;
import com.tcs.sgv.wf.exception.UpperPostNotFoundException;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WfJobMstVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;


/** PhyBillServiceImpl
 *  This class is used to open inwarded bills onlien as well as physical, show saved bills, intimation ,
 *  getting Hyrarchy User
 * 	Date of Creation : 7th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  Bhavik        23-Oct-2007   For making changes for code formating
 *  Hiral Shah	  25-Oct-2007  Changes for filtering auditor on bill type.
 *  Hiral Shah	  26-Oct-2007	Changes for Bill Type Code
 */
public class PhyBillServiceImpl extends ServiceImpl implements PhyBillService {

	String recFlag = null;

	String postId = null;

	String userId = null;

	Log logger = LogFactory.getLog(getClass());

	ResourceBundle bundleConst = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to receive the online Bills by counter
	 * 
	 * @param Map :
	 *            objectArgs
	 * @return ResultObject
	 * @author bhavik
	 */
	public ResultObject getDigiSig(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			resObj.setViewName("digiSigVerify");
			resObj.setResultValue(objectArgs);
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in getDigiSig " + e, e);
		}
		return resObj;
	}

	/**
	 * Method to receive the online Bills by counter
	 * 
	 * @param Map :
	 *            objectArgs
	 * @return ResultObject
	 * @author bhavik
	 */
	public ResultObject receiveOnlineBill(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			OrgTokenStatusDAOImpl tokenDAO = new OrgTokenStatusDAOImpl(
					OrgTokenStatus.class, serv.getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			String lStrBillArray[] = (String[]) request
					.getParameterValues("chkbox");
			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BillMovementDAOImpl billMvmntDAO = new BillMovementDAOImpl(
					TrnBillMvmnt.class, serv.getSessionFactory());
			String lStrCurrStatus = (String) request.getParameter("statusFlag");
			String lStrUpdStatus = (String) request
					.getParameter("updStatusFlag");
			String fromStat = (String) request.getParameter("fromStat");
			postId = SessionHelper.getPostId(objectArgs).toString();
			userId = SessionHelper.getUserId(request).toString();
			boolean lBoolValidTok = true;
			StringBuffer sb = new StringBuffer();
			String lStrTokNum = "";
			//System.out.println("lStrUpdStatus is ::::::::::::::"	+ lStrUpdStatus);
			//System.out.println("\n\n##########################" + fromStat);
			String[] lStrBillArr = lStrBillArray;
			if (!lStrUpdStatus.equals(bundleConst.getString("STATUS.CounterOnlineBill"))
					&& (fromStat == null || fromStat.equals(""))) // if update state is not BSNT_TO
			{
				sb.append("Invalid Token List : ");
				for (int i = 0; i < lStrBillArray.length; i++) {
					lStrTokNum = request.getParameter("tokens_"
							+ lStrBillArray[i]);
					if (lStrTokNum != null && !lStrTokNum.equals("")) {
						boolean lBoolTok = tokenDAO.isValidateToken(Long
								.parseLong(lStrTokNum), SessionHelper
								.getLocationCode(objectArgs));
						if (!lBoolTok) {
							sb.append(lStrTokNum);
							if (i != lStrBillArray.length - 1) {
								sb.append(",");
							}
							if (lBoolValidTok) {
								lBoolValidTok = false;
							}
						}
					}
				}
			}
			if ((fromStat != null && !fromStat.equals(""))
					&& lStrBillArr.length > 0) {
				for (int a = 0; a < lStrBillArr.length; a++) {
					String parts[] = lStrBillArr[a].split("~");
					lStrBillArray[a] = parts[0];
				}
			}
			if (lBoolValidTok) {
				for (int i = 0; i < lStrBillArray.length; i++) {
					TrnBillRegister trnBillReg = pbDaoImpl.read(Long
							.parseLong(lStrBillArray[i]));

					if (!lStrUpdStatus.equals(bundleConst.getString("STATUS.CounterOnlineBill"))) // if update state
															// is not BSNT_TO
															// for peer sending
					{
						Long AudPostId = Long.parseLong((String) request
								.getParameter("cmb_" + lStrBillArray[i]));
						if (fromStat == null || fromStat.equals("")) {
							lStrTokNum = request.getParameter("tokens_"
									+ lStrBillArray[i]);
							tokenDAO.updateTokenStatus(Long
									.parseLong(lStrTokNum), SessionHelper
									.getLocationCode(objectArgs), Long
									.parseLong(lStrBillArray[i]), SessionHelper
									.getUserId(request), SessionHelper
									.getPostId(objectArgs));
							trnBillReg.setTokenNum(Long.parseLong(lStrTokNum));
							trnBillReg.setRefNum(lObjCmnSrvcDAOImpl
									.getMaxRefNum(SessionHelper
											.getLocationCode(objectArgs)));
						}
						if (!((String) request.getParameter("cmb_"
								+ lStrBillArray[i])).equals("-1")) {
							trnBillReg.setAudPostId(AudPostId);
							trnBillReg.setAudUserId(Long
									.parseLong(lObjCmnSrvcDAOImpl
											.getUserIdFromPost(AudPostId
													.toString(), SessionHelper
													.getLangId(objectArgs))));
						}
						trnBillReg.setCurrBillStatusDate(new java.util.Date());
						trnBillReg.setCurrBillStatus(lStrUpdStatus);
						pbDaoImpl.update(trnBillReg);
						pbDaoImpl.updateRptExpDtls(objectArgs, Long
								.parseLong(lStrBillArray[i]), trnBillReg);
					}

					TrnBillMvmnt lObjBillMvmntVO = new TrnBillMvmnt();
					Long billMvmntID = BptmCommonServiceImpl.getNextSeqNum(
							"trn_bill_mvmnt", objectArgs);
					//System.out.println("------------- frsti time id ------- "				+ billMvmntID);

					lObjBillMvmntVO
							.setMovemntId(billMvmntDAO.getmaxMovementId(Long
									.parseLong(lStrBillArray[i])));
					lObjBillMvmntVO.setBillNo(Long.parseLong(lStrBillArray[i]));
					lObjBillMvmntVO.setCreatedDate(new java.util.Date());
					lObjBillMvmntVO.setCreatedPostId(SessionHelper
							.getPostId(objectArgs));
					lObjBillMvmntVO.setCreatedUserId(SessionHelper
							.getUserId(request));
					lObjBillMvmntVO.setDbId(SessionHelper.getDbId(objectArgs));
					lObjBillMvmntVO.setLocationCode(SessionHelper
							.getLocationCode(objectArgs));
					if (lStrUpdStatus.equals(bundleConst.getString("STATUS.BillFwdCardex"))) {
						lObjBillMvmntVO.setMvmntStatus(bundleConst.getString("STATUS.BillInward"));
					} else {
						lObjBillMvmntVO.setMvmntStatus(lStrUpdStatus);
					}
					lObjBillMvmntVO.setReceivedFlag((short) 1);
					lObjBillMvmntVO.setReceivingUserId(SessionHelper
							.getUserId(request));
					lObjBillMvmntVO.setReceivedDate(new java.util.Date());
					lObjBillMvmntVO.setUpdatedDate(new java.util.Date());
					lObjBillMvmntVO.setUpdatedPostId(SessionHelper
							.getPostId(objectArgs));
					lObjBillMvmntVO.setUpdatedUserId(SessionHelper
							.getUserId(request));
					lObjBillMvmntVO.setStatusUpdtUserid(SessionHelper
							.getUserId(request));
					lObjBillMvmntVO.setStatusUpdtPostid(SessionHelper
							.getPostId(objectArgs));
					lObjBillMvmntVO.setStatusUpdtDate(new java.util.Date());
					//System.out.println(" movemnt id is ::::::::::::::::::"							+ lObjBillMvmntVO.getBillMvmtId());
					if (!lStrUpdStatus.equals(bundleConst.getString("STATUS.CounterOnlineBill"))
							&& (fromStat == null || fromStat.equals(""))) // if
																			// update
																			// state
																			// is
																			// not
																			// BSNT_TO
																			// for
																			// peer
																			// sending
					{
						BillMovementServiceImpl lObjBillMvmntSrvcImpl = new BillMovementServiceImpl();
						TrnBillMvmnt lObjBillNewMvmntVO = new TrnBillMvmnt();
						lObjBillNewMvmntVO = lObjBillMvmntSrvcImpl
								.getMovementInstance(lObjBillMvmntVO,
										objectArgs);
						lObjBillNewMvmntVO.setMvmntStatus(bundleConst.getString("STATUS.BillInward"));
						//System.out.println("movmt id is  :::::::::::;; "								+ lObjBillMvmntVO.getMovemntId());
						lObjBillNewMvmntVO.setMovemntId(lObjBillMvmntVO
								.getMovemntId());
						lObjBillNewMvmntVO.setBillMvmtId(billMvmntID);
						billMvmntDAO.create(lObjBillNewMvmntVO);
					}

					// if(lStrUpdStatus.equals("BCRDX") ||
					// lStrUpdStatus.equals("BSNT_TO"))
					// add by bee 4 online inwd
					if (lStrUpdStatus.equals(bundleConst.getString("STATUS.BillFwdCardex"))
							|| lStrUpdStatus.equals(bundleConst.getString("STATUS.CounterOnlineBill"))
							|| lStrUpdStatus.equals(bundleConst.getString("STATUS.BillFwdAuditor"))) {
						// lObjBillMvmntVO.setBillMvmtId(2+BptmCommonServiceImpl.getNextSeqNum("trn_bill_mvmnt",
						// objectArgs));
						// if(lStrUpdStatus.equals("BCRDX"))
						if (lStrUpdStatus.equals(bundleConst.getString("STATUS.BillFwdAuditor"))) // 4 online inwd
						{
							lObjBillMvmntVO.setMovemntId(1 + billMvmntDAO
									.getmaxMovementId(Long
											.parseLong(lStrBillArray[i])));
						} else {
							lObjBillMvmntVO.setMovemntId(billMvmntDAO
									.getmaxMovementId(Long
											.parseLong(lStrBillArray[i])));
						}
						objectArgs.put("BillMovementVO", lObjBillMvmntVO);
						objectArgs.put("jobId", lStrBillArray[i]);
						String audPostId = (String) request.getParameter("cmb_"
								+ lStrBillArray[i]);
						objectArgs.put("toPost", audPostId);
						forwardBillFromWF(objectArgs);

					}
					if (fromStat == null || fromStat.equals("")) {
						/* intimation */
						TrnChequeServiceImpl tcsi = new TrnChequeServiceImpl();
						List listBillNumbers = new ArrayList();
						listBillNumbers.add(Long.parseLong(lStrBillArray[i]));
						objectArgs
								.put("message", "Online bill received by "
										+ SessionHelper.getUserName(request)
										+ " with assigned token Number : "
										+ lStrTokNum);
						objectArgs.put("desc",
								"Once User Get Bill intimation is send");
						objectArgs.put("catagory", "cat1");
						objectArgs.put("bills", listBillNumbers);
						objectArgs.put("toPostId", trnBillReg
								.getCreatedPostId().toString());
						objectArgs.put("toEmpId", trnBillReg.getCreatedUserId()
								.toString());
						tcsi.sendIntimation(objectArgs);
					}
				}
				resObj = getSavedBills(objectArgs);
			} else {
				resObj = getSavedBills(objectArgs);
				Map resultMap = (Map) resObj.getResultValue();
				resultMap.put("validFlag", "1");
				resultMap.put("inValidList", sb.toString());
				resObj.setResultValue(resultMap);
			}

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in receiveOnlineBill " + e, e);
		}
		return resObj;
	}

	/**
	 * Method to get all User wise Intimation
	 * 
	 * @param Map :
	 *            objectArgs
	 * @return ResultObject
	 */
	public ResultObject getMyIntimation(Map objectArgs) {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

		try {
			List lObjIntimationList = BptmCommonServiceImpl
					.getMyIntimation(objectArgs);
			Iterator it = lObjIntimationList.iterator();
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			// List BillNo = new ArrayList();
			List BillContNo = new ArrayList();
			List lStrUserName = new ArrayList();
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			while (it.hasNext()) {
				Map mapTemp = (HashMap) it.next();				
				// BillNo.add(mapTemp.get("NTF_REF_ID").toString());
				if (mapTemp != null && !mapTemp.isEmpty()) {
					BillContNo.add(lObjCmnSrvcDAOImpl
							.getBillCntrlNoFromBillNo(mapTemp.get("NTF_REF_ID")
									.toString()));
					lStrUserName.add(lObjCmnSrvcDAOImpl.getEmpNameFrmUserId(
							mapTemp.get("NTF_FROM_EMP_ID").toString(),
							SessionHelper.getLangId(objectArgs)));
				}
			}

			objectArgs.put("intimation", lObjIntimationList);
			objectArgs.put("BillContNo", BillContNo);
			objectArgs.put("lStrUserName", lStrUserName);
			String lStrViewName = "intimation";
			resObj.setViewName(lStrViewName);
			resObj.setResultValue(objectArgs);
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
			logger.error(" Error in getMyIntimation " + e, e);
		}
		return resObj;
	}

	/**
	 * Method to get all Users coming enext in Hierarchy
	 * 
	 * @param Map :
	 *            objectArgs
	 * 
	 * @return ResultObject
	 * @author Bhavik
	 
	 */	
	public ResultObject getHyrarchyUser(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try {
			String lStrOnlineSubj = bundleConst.getString("CMN.OnlineBillSubj");
			String lStrPhyRegular = bundleConst.getString("CMN.PhyRegular");
			String lStrPhyTCNill = bundleConst.getString("CMN.PhyTCNill");
			String lStrSubject = "";
			String lStrExpSubject = "";

			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			HttpSession hs = request.getSession();
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			String lStrBillNo = (String) request.getParameter("BillNo");
			String lStrRejFlag = (String) request.getParameter("actionVal");
			String lStrStatusFlag = (String) request.getParameter("statusFlag");
			String lStrHPFlag = "H";
			Long lStrHeirRefId = null;
			long lDocId = 0;

			if (request.getParameter("sendTo") != null) {
				lStrHPFlag = (String) request.getParameter("sendTo");
			}
			if (BptmCommonServiceImpl.isPhyBill(Long.parseLong(lStrBillNo),
					objectArgs)) {
				lStrSubject = lStrPhyRegular;
				lStrExpSubject = lStrPhyTCNill;
			} else if (lStrStatusFlag != null && !lStrStatusFlag.equals("")) {
				lStrSubject = lStrPhyRegular;
			} else {
				lStrSubject = lStrOnlineSubj;
				lStrExpSubject = lStrOnlineSubj;
			}
			userId = hs.getAttribute("userId").toString();
			postId = SessionHelper.getPostId(objectArgs).toString();
			Long langId = SessionHelper.getLangId(objectArgs);

			String lStrPostId = postId; // from session;

			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();
			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setAppMap(objectArgs);
			workFlowVO.setConnection(conn);

			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);		

			WorkFlowInterfaceImpl workFlowInterface = new WorkFlowInterfaceImpl(
					workFlowVO);
			int llFromLevelId = 0;
			List resultList1 = null;
			List resultList = null;
			Map resultMap = null;
			if (lStrBillNo != null && lStrBillNo.equals("-1")) {

				resultMap = orgUtil.getHierarchyByPostIDAndDescription(postId,
						lStrSubject);
				resultList = (List) resultMap.get("Result");
				lStrHeirRefId = Long.parseLong(resultList.get(0).toString());				
				llFromLevelId = workFlowInterface.getLevelFromPostMpg(
						lStrPostId, lStrHeirRefId);

			} // end of if
			else {
				String bilCat = (String) request.getParameter("BillCat");
				String billCtgry = null;
				PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(
						TrnBillRegister.class, serv.getSessionFactory());
				if (bilCat != null && !bilCat.equals("")) {
					billCtgry = lObjCmnSrvcDAOImpl.getLookUpText((bilCat
							.toString()), langId);
				} else {
					/*
					 * Long bilCate =
					 * lObjPhyBillDAOImpl.read(Long.parseLong(lStrBillNo)).getTcBill();
					 * billCtgry=
					 * lObjCmnSrvcDAOImpl.getLookUpText(bilCate.intValue()
					 * ,langId);
					 */billCtgry = lObjPhyBillDAOImpl.read(
							Long.parseLong(lStrBillNo)).getTcBill();

				}

				if (billCtgry != null && !billCtgry.equals("Regular")) {
					if (BptmCommonServiceImpl.isPhyBill(Long
							.parseLong(lStrBillNo), objectArgs)) {
						lStrSubject = lStrPhyTCNill;
					} else {
						lStrSubject = lStrOnlineSubj;
					}					
				}
				resultMap = orgUtil.getHierarchyByPostIDAndDescription(postId,
						lStrSubject);

				resultList = (List) resultMap.get("Result");
				lStrHeirRefId = Long.parseLong(resultList.get(0).toString());				
				if (lObjCmnSrvcDAOImpl.isPhyBill(Long.parseLong(lStrBillNo))) {
					lDocId = Long.parseLong(bundleConst
							.getString("CMN.PhyBillDocId"));
				} else {
					lDocId = Long.parseLong(bundleConst
							.getString("CMN.OnlineBillDocId"));
				}
				WfJobMstVO jobMst = orgUtil.getJobMstByJobRefIDAndFromPost(
						lStrBillNo, postId, lDocId);
				lStrHeirRefId = jobMst.getHierachyRefId();				
				llFromLevelId = jobMst.getLevelId();
			}
			if (lStrHPFlag != null && lStrHPFlag.equals("H")) {
				if (lStrRejFlag != null && lStrRejFlag.equals("REJECT")) {
					resultList1 = orgUtil.getToNodeListFromAlterHir(lStrPostId,
							lStrHeirRefId, "Reject");
				} else if (lStrRejFlag != null && lStrRejFlag.equals("APPROVE")) {//						
					resultList1 = orgUtil.getToNodeListFromAlterHir(lStrPostId,
							lStrHeirRefId, "Approve");
				} else {
					Map resultMap1 = orgUtil.getUpperPost(lStrPostId,
							lStrHeirRefId, llFromLevelId);
					resultList1 = (List) resultMap1.get("Result");

					// bee adds
					
					if (lStrStatusFlag != null && !BptmCommonServiceImpl.isPhyBill(Long
							.parseLong(lStrBillNo), objectArgs)
							&& lStrStatusFlag.equals(bundleConst.getString("STATUS.BillFwdCardex"))) {
						//System.out.println("UPDATE FLAG IS ::::::    "							+ lStrStatusFlag);
						String postString[] = new String[resultList1.size()];
						String levelString[] = new String[resultList1.size()];
						for (int i = 0; i < resultList1.size(); i++) {
							Object[] lStrNextPost = (Object[]) resultList1
									.get(i);
							postString[i] = lStrNextPost[0].toString();
							levelString[i] = lStrNextPost[1].toString();
						}						
						resultMap1 = orgUtil
								.getUpperPost(postString[0], lStrHeirRefId,
										Integer.parseInt(levelString[0]));
						resultList1 = (List) resultMap1.get("Result");
					}
					// bee ends
				}
			} else if (lStrHPFlag != null && lStrHPFlag.equals("P")) {
				WorkFlowDAOImpl wfDAO = new WorkFlowDAOImpl();
				resultList1 = wfDAO.getSameLevelUsers(String
						.valueOf(lStrHeirRefId), llFromLevelId, Long
						.parseLong(lStrPostId), serv.getSessionFactory());
			}

			String postString[] = new String[resultList1.size()];
			String levelString[] = new String[resultList1.size()];
			for (int i = 0; i < resultList1.size(); i++) {
				Object[] lStrNextPost = (Object[]) resultList1.get(i);
				postString[i] = lStrNextPost[0].toString();
				levelString[i] = lStrNextPost[1].toString();
			}

			List listSameLvlUser = null;
			if (resultList1.size() > 0) {
				listSameLvlUser = lObjCmnSrvcDAOImpl.getUserFromPost(
						postString, levelString, langId);
			}

			if (request.getParameterValues("BillArr") != null) {
				TrnChequeServiceImpl trnChequeServiceImpl = new TrnChequeServiceImpl();
				if (!(trnChequeServiceImpl.isCheckBillChkCombi(objectArgs))) {
					objectArgs.put("Msg", "0");
				} else {
					objectArgs.put("Msg", "1");
				}
			}
			if (request.getParameterValues("ChequeArr") != null) {
				TrnChequeServiceImpl trnChequeServiceImpl = new TrnChequeServiceImpl();
				String chequeArr[] = request.getParameter("ChequeArr").split(
						",");
				String chequeList[] = new String[chequeArr.length];
				String groupList[] = new String[chequeArr.length];
				for (int i = 0; i < chequeArr.length; i++) {
					String[] parts = chequeArr[i].split("~");
					chequeList[i] = parts[0];
					groupList[i] = parts[1];
				}
				if (trnChequeServiceImpl.isCheckBillChequeCombination(
						chequeList, groupList, serv.getSessionFactory())) {
					objectArgs.put("Msg", "1");
				} else {
					objectArgs.put("Msg", "0");
				}
			}

			objectArgs.put("userList11", listSameLvlUser);
			objectArgs.put("currentUserPost", SessionHelper.getPostId(
					objectArgs).toString());
			objRes.setViewName("cmnSelectForward");
			objRes.setResultValue(objectArgs);

		} catch (AlternateHierarchyNotfoundException e) {
			logger
					.error("****************************************AlternateHierarchyNotfoundException****************************************");
			objRes.setViewName("cmnSelectForward");
			objRes.setResultValue(objectArgs);
			logger.error(" Error in getHeirarchuUsers " + e, e);
		} catch (UpperPostNotFoundException e) {
			logger
					.error("********************************************UpperPostNotFoundException****************************************");
			objRes.setViewName("cmnSelectForward");
			objRes.setResultValue(objectArgs);
			logger.error(" Error in getHeirarchuUsers " + e, e);
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getHeirarchuUsers " + e, e);
		}
		return objRes;
	}

	/**
	 * Method to get update for All Approved bills
	 * 
	 * @param Map :
	 *            objectArgs
	 * 
	 * @return ResultObject
	 * @author Bhavik
	 */

	public ResultObject updateApproveBills(Map objectArgs) // use for updateing
															// & forwarding the
															// data
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			//System.out.println("inside updateApproveBills hi sets");
			logger.info("inside UPDATE APPROVED BILLS");
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			HttpSession hs = request.getSession();
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			userId = hs.getAttribute("userId").toString();
			postId = SessionHelper.getPostId(objectArgs).toString();
			Long langId = SessionHelper.getLangId(objectArgs);
			String lStrSendTo = request.getParameter("SendTo");
			String lStrStatusFlag = request.getParameter("statusFlag");
			String lStrUpdStatusFlag = request.getParameter("updStatusFlag");
			recFlag = request.getParameter("recFlag");
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			String apprvRejecFlag = (String) request.getParameter("actionVal");
			String lStrReturnCase = null;
			if (request.getParameter("returnCase") != null
					&& request.getParameter("returnCase").length() > 0) {
				lStrReturnCase = (String) request.getParameter("returnCase");
				lStrUpdStatusFlag = bundleConst
						.getString("STATUS.BillExceptional");
			}
			if (apprvRejecFlag != null && apprvRejecFlag.length() > 0) {
				if (apprvRejecFlag.equalsIgnoreCase("Approve")) {
					lStrUpdStatusFlag = bundleConst
							.getString("STATUS.BillApproved");
				} else if (apprvRejecFlag.equalsIgnoreCase("Reject")) {
					lStrUpdStatusFlag = bundleConst
							.getString("STATUS.BillRejected");
				}
			}
			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BillMovementDAOImpl billMvmntDAO = new BillMovementDAOImpl(
					TrnBillMvmnt.class, serv.getSessionFactory());

			List billList = null;
			TrnBillRegister billList1 = null;
			String[] billsNo = null;

			String toPost = null;
			String toLevel = null;
			hs.setAttribute("RECFLAG", "0");

			if (request.getParameter("toPost") != null) {
				String[] lArray = request.getParameter("toPost").toString()
						.split("~");
				// toPost=request.getParameter("toPost").toString();
				toPost = lArray[0];

				if (lArray.length == 1) {
					toLevel = "-1";
					logger.info(" To post is :::: " + toPost
							+ " To level is  ::: " + toLevel);
				} else {
					toLevel = lArray[1];
					logger.info(" To post is :::: " + toPost
							+ " To level is  ::: " + toLevel);
				}
			}

			if (request.getParameterValues("chkbox") != null) {
				billsNo = request.getParameterValues("chkbox");
			} else {
				billsNo = new String[1];
				billsNo[0] = "-1~-1~-1";
			}

			String[] lStrBillNo = new String[billsNo.length];
			String[] lStrTokenNo = new String[billsNo.length];
			String[] lStrBudMjrHd = new String[billsNo.length];
			for (int i = 0; i < billsNo.length; i++) {
				StringTokenizer st = new StringTokenizer(billsNo[i], "~");
				lStrBillNo[i] = st.nextToken();
				lStrTokenNo[i] = st.nextToken();
				lStrBudMjrHd[i] = st.nextToken();
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date lDtCurDate = new java.util.Date(System
					.currentTimeMillis());

			// Common code
			Locale locale = new Locale(SessionHelper.getLocale(request));
			ResourceBundle bundle = ResourceBundle.getBundle(
					"resources/billproc/billproc", locale);

			// if(page.equals("savedBillsUpdate") || page.equals("cardexFwd") ||
			// page.equals("audFwd") || page.equals("accFwd") ||
			// page.equals("atoFwd") || page.equals("toFwd"))
			if (recFlag != null && recFlag.equals("1")) {
				String combos[] = new String[billsNo.length];
				String tcRegBill[] = new String[billsNo.length];
				for (int i = 0; i < billsNo.length; i++) {
					TrnBillMvmnt lObjBillMvmntVO = new TrnBillMvmnt();
					lObjBillMvmntVO.setBillNo(Long.parseLong(lStrBillNo[i]));
					lObjBillMvmntVO.setCreatedDate(lDtCurDate);
					lObjBillMvmntVO.setCreatedPostId(Long.parseLong(postId));
					lObjBillMvmntVO.setCreatedUserId(Long.parseLong(userId));
					lObjBillMvmntVO.setDbId(SessionHelper.getDbId(objectArgs));
					lObjBillMvmntVO.setLocationCode(SessionHelper
							.getLocationCode(objectArgs));
					lObjBillMvmntVO.setMvmntStatus(lStrUpdStatusFlag);
					// //bee adds
					if (!BptmCommonServiceImpl.isPhyBill(Long
							.parseLong(lStrBillNo[i]), objectArgs)
							&& lStrUpdStatusFlag.equals(bundleConst.getString("STATUS.BillFwdCardex"))) {
						lObjBillMvmntVO.setMvmntStatus(bundleConst.getString("STATUS.BillFwdAuditor"));
					}
					lObjBillMvmntVO.setReceivedFlag((short) 0);
					lObjBillMvmntVO.setReceivingUserId(SessionHelper
							.getUserId(request));
					// lObjBillMvmntVO.setReceivedDate(lDtCurDate);
					lObjBillMvmntVO.setStatusUpdtDate(lDtCurDate);

					if (!toPost.equals("-1")) {
						logger
								.info("Value of toPost inside if(! toPost.equals(-1)) : "
										+ toPost);
						lObjBillMvmntVO.setStatusUpdtPostid(Long
								.parseLong(toPost)); // next user's post id
						lObjBillMvmntVO.setStatusUpdtUserid(Long
								.parseLong(lObjCmnSrvcDAOImpl
										.getUserIdFromPost(toPost, langId)));
					} else if (request.getParameter("cmb_" + lStrBillNo[i]) != null
							&& request.getParameter("cmb_" + lStrBillNo[i])
									.length() > 0) {
						String toPostForCardex = request.getParameter("cmb_"
								+ lStrBillNo[i]);
						logger
								.info("Value of toPostForCardex inside else (! toPost.equals(-1)) : "
										+ toPostForCardex);
						lObjBillMvmntVO.setStatusUpdtPostid(Long
								.parseLong(toPostForCardex));
						lObjBillMvmntVO.setStatusUpdtUserid(Long
								.parseLong(lObjCmnSrvcDAOImpl
										.getUserIdFromPost(toPostForCardex,
												langId)));
					}

					if (!lStrStatusFlag.equals(bundleConst
							.getString("STATUS.BillFwdCardex"))) {
						objectArgs.put("toPost", toPost);
						objectArgs.put("toLevel", toLevel);
					} else {
						String toPostForCardex = request.getParameter("cmb_"
								+ lStrBillNo[i]);
						objectArgs.put("toPost", toPostForCardex);
					}

					TrnBillRegister trnBillReg = pbDaoImpl.read(Long
							.parseLong(lStrBillNo[i]));
					// Check is status is BRCDX then make an entry for cardex
					// verficcation flag in movement table
					if (lStrStatusFlag.equals(bundleConst
							.getString("STATUS.BillFwdCardex"))) {
						String lStrCardexFlag = request
								.getParameter("CmbCdxVerify_" + lStrBillNo[i]);
						TrnBillMvmnt newMovementVO = new BillMovementServiceImpl()
								.getMovementInstance(lObjBillMvmntVO,
										objectArgs);
						newMovementVO.setStatusUpdtPostid(SessionHelper
								.getPostId(objectArgs));
						newMovementVO.setStatusUpdtUserid(SessionHelper
								.getUserId(request));
						newMovementVO.setMvmntStatus(lStrCardexFlag);
						newMovementVO.setReceivedFlag((short) 1);
						newMovementVO.setBillMvmtId(BptmCommonServiceImpl
								.getNextSeqNum("trn_bill_mvmnt", objectArgs));
						newMovementVO
								.setMovemntId(billMvmntDAO
										.getmaxMovementId(Long
												.parseLong(lStrBillNo[i])));
						billMvmntDAO.create(newMovementVO);
						lObjBillMvmntVO
								.setMovemntId(billMvmntDAO
										.getmaxMovementId(Long
												.parseLong(lStrBillNo[i])) + 1);

						/* Inserting objection for Cardex Not verified -- START */
						if (lStrCardexFlag.equals(bundleConst
								.getString("STATUS.CardexNotVerified"))) {
							RltBillObjection rltBillObj = new RltBillObjection();
							rltBillObj.setBillObjcId(BptmCommonServiceImpl
									.getNextSeqNum("rlt_bill_objection",
											objectArgs));
							rltBillObj.setBillNo(Long.parseLong(lStrBillNo[i]));
							rltBillObj.setObjectionCode(bundleConst
									.getString("CMN.ObjCode"));
							rltBillObj.setCreatedDate(new Date(System
									.currentTimeMillis()));
							rltBillObj.setCreatedPostId(SessionHelper
									.getPostId(objectArgs));
							rltBillObj.setCreatedUserId(SessionHelper
									.getUserId(request));
							rltBillObj.setDbId(SessionHelper
									.getDbId(objectArgs));
							rltBillObj.setLineItemNo((long) 1);
							rltBillObj.setLocationCode(SessionHelper
									.getLocationCode(objectArgs));
							rltBillObj.setObjFlag("PO");
							rltBillObj.setPostId(SessionHelper
									.getPostId(objectArgs));
							rltBillObj.setUserId(SessionHelper
									.getUserId(request));
							AuditorObjectionDAOImpl auditObj = new AuditorObjectionDAOImpl(
									RltBillObjection.class, serv
											.getSessionFactory());
							auditObj.create(rltBillObj);
						}
						/* Inserting objection for Cardex Not verified -- END */
						/*
						 * TrnChequeServiceImpl tcsi = new
						 * TrnChequeServiceImpl(); List listBillNumbers = new
						 * ArrayList();
						 * listBillNumbers.add(Long.parseLong(lStrBillNo[i]));
						 * objectArgs.put("message","Bill Received By "+
						 * SessionHelper.getUserName(request));
						 * objectArgs.put("desc","Once User Get Bill intimation
						 * is send"); objectArgs.put("catagory","cat1");
						 * objectArgs.put("bills", listBillNumbers);
						 * objectArgs.put("toPostId",trnBillReg.getCreatedPostId().toString());
						 * objectArgs.put("toEmpId",trnBillReg.getCreatedUserId().toString());
						 * tcsi.sendIntimation(objectArgs);
						 */
					}

					if (lStrUpdStatusFlag.equals(bundleConst
							.getString("STATUS.BillApproved"))) {
						// Long billType = trnBillReg.getTcBill();
						String billCtgry = trnBillReg.getTcBill();
						PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(
								TrnBillRegister.class, serv.getSessionFactory());
						// String billCtgry=
						// lObjCmnSrvcDAOImpl.getLookUpText(Integer.parseInt(billType.toString()),langId);

						if (billCtgry != null && !billCtgry.equals("Regular")) {
							lObjBillMvmntVO.setReceivedFlag((short) 1);
						}
					}
					if (lStrSendTo != null && lStrSendTo.equals("P")) {
						lStrUpdStatusFlag = lStrStatusFlag;
					}

					objectArgs.put("BillMovementVO", lObjBillMvmntVO);
					StringTokenizer st = new StringTokenizer(billsNo[i], "~");
					String lStrBillNum = st.nextToken().toString();
					combos[i] = request.getParameter("cmb_" + lStrBillNum);
					objectArgs.put("jobId", lStrBillNum);
					objectArgs.put("postId", combos[i]);

					// if(lStrUpdStatusFlag.equals(bundleConst.getString("STATUS.BillExceptional")))
					if (lStrReturnCase != null && lStrReturnCase.equals("1")) {
						objectArgs = (Map) returnBillFromWF(objectArgs)
								.getResultValue();
					} else {
						forwardBillFromWF(objectArgs);
					}

					if (lStrUpdStatusFlag.equals(bundleConst
							.getString("STATUS.BillRejected"))) {
						String lStrTokNo = st.nextToken().toString();
						OrgTokenStatusDAOImpl tokenDAO = new OrgTokenStatusDAOImpl(
								OrgTokenStatus.class, serv.getSessionFactory());
						tokenDAO.updateTokenRedeem(Long.parseLong(lStrTokNo),
								SessionHelper.getLocationCode(objectArgs),
								SessionHelper.getUserId(request), SessionHelper
										.getPostId(objectArgs));
						// chequeDAO.releaseToken(Long.parseLong(lStrBillNum));
					}
					if (lStrUpdStatusFlag.equals(bundleConst
							.getString("STATUS.BillApproved"))) {
						// Long billType = trnBillReg.getTcBill();
						PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(
								TrnBillRegister.class, serv.getSessionFactory());
						// String billCtgry=
						// lObjCmnSrvcDAOImpl.getLookUpText(Integer.parseInt(billType.toString()),langId);
						String billCtgry = trnBillReg.getTcBill();
						if (billCtgry != null && !billCtgry.equals("Regular")) {
							TrnBillMvmnt newMovementVO = new BillMovementServiceImpl()
									.getMovementInstance(lObjBillMvmntVO,
											objectArgs);
							newMovementVO.setMvmntStatus(bundleConst
									.getString("STATUS.CheqDispToCnt"));
							newMovementVO.setReceivedFlag((short) 0);
							newMovementVO
									.setBillMvmtId(BptmCommonServiceImpl
											.getNextSeqNum("trn_bill_mvmnt",
													objectArgs));
							newMovementVO.setMovemntId(billMvmntDAO
									.getmaxMovementId(Long
											.parseLong(lStrBillNo[i])) + 1);
							billMvmntDAO.create(newMovementVO);
							lStrUpdStatusFlag = bundleConst
									.getString("STATUS.CheqDispToCnt");
						}
					}
					// Updating TRN bill Register for current status

					trnBillReg.setCurrBillStatus(lStrUpdStatusFlag);
					// //bee adds
					if (!BptmCommonServiceImpl.isPhyBill(Long
							.parseLong(lStrBillNo[i]), objectArgs)
							&& lStrUpdStatusFlag.equals(bundleConst.getString("STATUS.BillFwdCardex"))) {
						trnBillReg.setCurrBillStatus(bundleConst.getString("STATUS.BillFwdAuditor"));
					}
					// bee ends
					trnBillReg.setCurrBillStatusDate(lDtCurDate);
					trnBillReg.setAudPostId(Long.parseLong(combos[i]));
					trnBillReg.setUpdatedUserId(SessionHelper
							.getUserId(request));
					trnBillReg.setUpdatedPostId(SessionHelper
							.getPostId(objectArgs));
					trnBillReg.setUpdatedDate(lDtCurDate);
					pbDaoImpl.update(trnBillReg);
					pbDaoImpl.updateRptExpDtls(objectArgs, Long
							.parseLong(lStrBillNo[i]), trnBillReg);

					// Long lLngBillType = trnBillReg.getTcBill();
					// String lStrBillCtgry=
					// lObjCmnSrvcDAOImpl.getLookUpText(Integer.parseInt(lLngBillType.toString()),langId);
					String lStrBillCtgry = trnBillReg.getTcBill();
					if (lStrBillCtgry.equalsIgnoreCase(bundleConst
							.getString("CMN.TCBill"))) {
						//System.out.println("Inside TC Bill Loop, Receipt Id : "							+ trnBillReg.getReceiptId());
						PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();
						objectArgs.put("lObjTrnBillRegisterVO", trnBillReg);
						List lListTrnRcptVO = lObjCmnSrvcDAOImpl
								.getTrnRcptFromBill(trnBillReg.getBillNo(),
										objectArgs);
						//System.out.println("Value of TrnRcptDtlsVO Size in updateApprove Bills : "	+ lListTrnRcptVO.size());
						for (int k = 0; k < lListTrnRcptVO.size(); k++) {
							TrnReceiptDetails lObjTrnRcptDtsl = new TrnReceiptDetails();
							lObjTrnRcptDtsl = (TrnReceiptDetails) lListTrnRcptVO
									.get(k);
							logger
									.info("Value of lObjTrnRcptDtsl.getTrnReceiptID in updateApproveBills "
											+ k
											+ " : "
											+ lObjTrnRcptDtsl
													.getReceiptDetailId());
							logger
									.info("Value of lStrBillNo in updateApproveBills "
											+ k
											+ " : "
											+ Long.parseLong(lStrBillNo[i]));
							logger
									.info("Value of objectArgs in updateApproveBills "
											+ k + " : " + objectArgs);
							logger
									.info("Value of trnBillReg in updateApproveBills "
											+ k + " : " + trnBillReg);
							objectArgs.put("TrnReceiptDetailsVO",
									lObjTrnRcptDtsl);
							pbDaoImpl.updateRptRcptDtls(objectArgs, Long
									.parseLong(lStrBillNo[i]), lObjTrnRcptDtsl
									.getReceiptDetailId(), trnBillReg);
						}
					}
				}
			} else {
				TrnChequeServiceImpl tcsi = new TrnChequeServiceImpl();
				List listBillNumbers = new ArrayList();
				String lStrTempStatus = "";

				// if(lStrUpdStatusFlag.equals(bundleConst.getString("STATUS.BillExceptional")))
				if (lStrReturnCase != null && lStrReturnCase.equals("1")) {
					lStrTempStatus = lStrUpdStatusFlag;
				} else {
					lStrTempStatus = lStrStatusFlag;
				}
				// List listBillNumbers = new ArrayList();
				for (int i = 0; i < billsNo.length; i++) {
					listBillNumbers = new ArrayList();
					StringTokenizer st = new StringTokenizer(billsNo[i], "~");
					String lStrIntiBillNum = st.nextToken();

					TrnBillMvmnt trnBillMvmnt = billMvmntDAO
							.getMvmntVOByStatus(lStrStatusFlag, SessionHelper
									.getUserId(request), lStrIntiBillNum);
					trnBillMvmnt.setReceivedFlag((short) 1);
					trnBillMvmnt.setReceivingUserId(SessionHelper
							.getUserId(request));
					trnBillMvmnt.setReceivedDate(lDtCurDate);
					billMvmntDAO.update(trnBillMvmnt);

					// /////////////// For intimation
					listBillNumbers.add(Long.parseLong(lStrIntiBillNum));
					objectArgs.put("message", "Bill received by "
							+ SessionHelper.getUserName(request));
					objectArgs.put("desc",
							"Once User Get Bill intimation is send");
					objectArgs.put("catagory", "cat1");
					objectArgs.put("bills", listBillNumbers);
					objectArgs.put("toPostId", trnBillMvmnt.getCreatedPostId()
							.toString());
					objectArgs.put("toEmpId", trnBillMvmnt.getCreatedUserId()
							.toString());
					tcsi.sendIntimation(objectArgs);
					// ////////////// intimation ends

				}
			}
			String lStrPage = null;
			List lBillList = getMyBills(objectArgs);
			billList = pbDaoImpl.getMyBills(lStrStatusFlag, recFlag, lBillList,
					userId, langId, objectArgs);

			if (billList != null) {
				Iterator it = billList.iterator();
				PostConfigurationDAOImpl postConfDaoImpl = new PostConfigurationDAOImpl(
						ConfigAudSelection.class, serv.getSessionFactory());
				// Long officeId = SessionHelper.getLocationId(objectArgs);
				String officeId = SessionHelper.getLocationCode(objectArgs);
				String lStrBranchCode = null;							
				while (it.hasNext()) {
					BillVO billVO = (BillVO) it.next();
/*	Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */					
					String lStrBillType = String.valueOf(billVO.getSubjectId());
					logger.info("Value of BillType from BillVO ------------- : " +lStrBillType);
					if(lStrBillType.equals("12") || lStrBillType.equals("23") || lStrBillType.equals("24"))
						lStrBranchCode = bundleConst.getString("CMN.BillTypeBranchId");						
					else
						lStrBranchCode = bundleConst.getString("CMN.BranchId");
					
					logger.info("Value of BranchCode----------Subject Id - Bill Type : " +lStrBranchCode);
/*	Ends : Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */					
					Map map = new HashMap();
					map.put("majorHead", billVO.getBudmjrHd().toString());
					
					billVO.setAudList(postConfDaoImpl.getEmpsList(officeId,
							lStrBranchCode));
					billVO.setEmpPostVO(postConfDaoImpl.getSelectedEmp(
							officeId, lStrBranchCode, map));
				}

				objectArgs.put("billList", billList);
				objectArgs.put("postId", postId); // //Static: Change Post id
													// Here
				objectArgs.put("recFlag", recFlag);

				String lStrViewName = "viewCardexVerify";
				//System.out.println("************** lStrStatusFlag ********* "				+ lStrStatusFlag);
				//System.out.println("************** recFlag ********* "		+ recFlag);
				if (lStrStatusFlag.equals(bundleConst
						.getString("STATUS.BillFwdCardex"))) {
					objectArgs.put("HeaderName", bundle
							.getString("VIEW.CDXVER"));
				} else if (lStrStatusFlag.equals(bundleConst
						.getString("STATUS.BillFwdAuditor"))) {
					if (recFlag != null && recFlag.equals("0")) {
						objectArgs.put("HeaderName", bundle
								.getString("VIEW.ACPTBILL"));
					} else {
						objectArgs.put("HeaderName", bundle
								.getString("VIEW.AUDITEDBILL"));
					}
				} else if (lStrStatusFlag.equals(bundleConst
						.getString("STATUS.BillApproved"))) {
					if (recFlag.equals("0")) {

						objectArgs.put("HeaderName", bundle
								.getString("VIEW.ACPTAPPBILL"));
					} else {
						objectArgs.put("HeaderName", bundle
								.getString("VIEW.CHQPREP"));
					}
					lStrViewName = "viewAcptApprBills";
				} else if (lStrStatusFlag.equals(bundleConst
						.getString("STATUS.BillInward"))) {
					lStrViewName = "viewSavedBills";
				} else if (lStrStatusFlag.equals(bundleConst
						.getString("STATUS.BillRejected"))) {
					if (recFlag.equals("0")) {

						objectArgs.put("HeaderName", bundle
								.getString("VIEW.ACPTREJBILL"));
					} else {
						objectArgs.put("HeaderName", bundle
								.getString("VIEW.DISPREJBILL"));
					}
				}

				//System.out.println("************** View Name ***************"					+ lStrViewName);
				//System.out.println("************** name ***************"					+ objectArgs.get("HeaderName"));
				objRes.setViewName(lStrViewName);
				objRes.setResultValue(objectArgs);
			} else {
				objRes.setViewName("errorPage");
			}

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in updateApprovedBills " + e, e);
		}

		logger.info("VIEWNAME :" + objRes.getViewName());
		return objRes;
	}

	/**
	 * Method to get all Saved bills
	 * 
	 * @param Map :
	 *            objectArgs
	 * 
	 * @return ResultObject
	 * @author Bhavik
	 */
	public ResultObject getSavedBills(Map objectArgs) { // getting Bills For
														// user
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			HttpSession hs = request.getSession();
			Locale locale = new Locale(SessionHelper.getLocale(request));
			ResourceBundle bundle = ResourceBundle.getBundle(
					"resources/billproc/billproc", locale);
			ResourceBundle bundleConst = ResourceBundle
					.getBundle("resources/billproc/BillprocConstants");
			// Long officeId = SessionHelper.getLocationId(objectArgs);
			String officeId = SessionHelper.getLocationCode(objectArgs);

			String lStrTxtSearch = null;
			String lStrCmbSearch = null;
			if (request.getParameter("txtSearch") != null
					&& request.getParameter("cmbSearch") != null) {
				lStrTxtSearch = request.getParameter("txtSearch").toString();
				lStrCmbSearch = request.getParameter("cmbSearch").toString();
			}

			userId = hs.getAttribute("userId").toString();
			postId = SessionHelper.getPostId(objectArgs).toString();
			Long langId = SessionHelper.getLangId(objectArgs);
			logger.info("POST is from session helper ::: " + postId);

			String lStrStatusFlag = request.getParameter("statusFlag");
			recFlag = request.getParameter("recFlag");
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			List billList = null;
			TrnBillRegister billList1 = null;

			//System.out.println(" lStrTxtSearch  " + lStrTxtSearch				+ " lStrCmbSearch " + lStrCmbSearch);

			objectArgs.put("lStrTxtSearch", lStrTxtSearch);
			objectArgs.put("lStrCmbSearch", lStrCmbSearch);

			if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.CounterOnlineBill"))) // For online
																// bill
			{
				objectArgs.put("lStrOnlineBill", "0");
			}

			List lMyBillList = getMyBills(objectArgs);
			logger.info("My bill is list" + lMyBillList);
			if (lMyBillList != null && lMyBillList.size() > 0) {
				logger.info("My bill is list" + lMyBillList.size());
				// billList =
				// pbDaoImpl.getAllBills(page,recFlag,lMyBillList,userId,
				// langId);
				billList = pbDaoImpl.getMyBills(lStrStatusFlag, recFlag,
						lMyBillList, userId, langId, objectArgs);

			}

			if (billList != null) {
				Iterator it = billList.iterator();
				PostConfigurationDAOImpl postConfDaoImpl = new PostConfigurationDAOImpl(
						ConfigAudSelection.class, serv.getSessionFactory());
				String lStrBranchCode = null;
				while (it.hasNext()) {

					BillVO billVO = (BillVO) it.next();
					Map map = new HashMap();
/*	Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */										
					String lStrBillType = String.valueOf(billVO.getSubjectId());
					logger.info("Value of BillType from BillVO ------------- : " +lStrBillType);
					if(lStrBillType.equals("12") || lStrBillType.equals("23") || lStrBillType.equals("24"))
						lStrBranchCode = bundleConst.getString("CMN.BillTypeBranchId");						
					else
						lStrBranchCode = bundleConst.getString("CMN.BranchId");
					
					logger.info("Value of BranchCode222222222222222----------Subject Id : " +lStrBranchCode);
/*	Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */										
					map.put("majorHead", billVO.getBudmjrHd().toString());
					billVO.setAudList(postConfDaoImpl.getEmpsList(officeId,
							lStrBranchCode));
					logger.info("Mjr head is ::: "
							+ billVO.getBudmjrHd().toString());
					billVO.setEmpPostVO(postConfDaoImpl.getSelectedEmp(
							officeId, lStrBranchCode, map));
				}
			}

			Map result = new HashMap();
			result.put("billList", billList);
			result.put("postId", postId); // //Static: Change Post id Here
			result.put("recFlag", recFlag);

			String lStrResultPage = "viewCardexVerify";
			//System.out.println("********** lStrStatusFlag ********* "				+ lStrStatusFlag);
			if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.BillInward"))) {
				lStrResultPage = "viewSavedBills";
			} else if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.BillFwdCardex"))) {
				result.put("HeaderName", bundle.getString("VIEW.CRDXVER"));
				lStrResultPage = "viewCardexVerify";
			} else if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.BillFwdAuditor"))) {
				if (recFlag.equals("0")) {
					result.put("HeaderName", bundle.getString("VIEW.ACPTBILL"));
				} else {
					result.put("HeaderName", bundle
							.getString("VIEW.AUDITEDBILL"));
				}
			} else if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.BillApproved"))) {
				if (recFlag.equals("0")) {

					result.put("HeaderName", bundle
							.getString("VIEW.ACPTAPPBILL"));
				} else {
					result.put("HeaderName", bundle.getString("VIEW.CHQPREP"));
				}
				lStrResultPage = "viewAcptApprBills";
			} else if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.BillRejected"))) {
				if (recFlag.equals("0")) {

					result.put("HeaderName", bundle
							.getString("VIEW.ACPTREJBILL"));
				} else {
					result.put("HeaderName", bundle
							.getString("VIEW.DISPREJBILL"));
				}
			} else if (lStrStatusFlag.equals(bundleConst
					.getString("STATUS.CounterOnlineBill"))) {
				if (recFlag.equals("0")) {
					result.put("HeaderName", bundle
							.getString("VIEW.ACPTAPPBILL"));
				} else {
					result.put("HeaderName", bundle
							.getString("VIEW.PHYONLINEBILL"));
				}
				lStrResultPage = "cntrSavedBillOnline";
			}

			//System.out.println("********** View Name ********* "					+ lStrResultPage);
			//System.out.println("**********  Name ********* "					+ result.get("HeaderName"));
			objRes.setViewName(lStrResultPage);
			objRes.setResultValue(result);

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getSavedBills " + e, e);
		}
		logger.info("Viewname : " + objRes.getViewName());

		return objRes;
	}

	/**
	 * Method to insert Details of Physical Bill in related tables.
	 * 
	 * @param Map :
	 *            objectArgs
	 * 
	 * @return Map
	 * @author HIRAL
	 */

	public Map insertPhyBillDetails(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		WorkFlowVO wfVO = (WorkFlowVO) objectArgs.get("WorkFlowVO");
		Map servicemap = wfVO.getAppMap();
		try {
			HttpServletRequest request = (HttpServletRequest) servicemap
					.get("requestObj");
			HttpSession session = request.getSession();
			PhyBillVOGeneratorImpl lObjPhyBillVoGen = new PhyBillVOGeneratorImpl();

			/* Getting Value Objects */
			TrnBillRemarks lObjTrnBillRemarksVO = (TrnBillRemarks) servicemap
					.get("TrnBillRemarksVO");
			TrnBillMvmnt lObjBillMvmntVO = (TrnBillMvmnt) servicemap
					.get("BillMovementVO");
			TrnBillBudheadDtls lObjBudHeadVo = (TrnBillBudheadDtls) servicemap
					.get("BudgetHeadVO");
			// TrnReceiptDetails lObjTrnRcptDtlsVO= (TrnReceiptDetails)
			// servicemap.get("ReceiptDetailsVO");
			List lListTrnRcptDtlsVO = (List) servicemap.get("ReceiptDetailsVO");
			TrnBillRegister lObjTrnBillRegisterVO = (TrnBillRegister) servicemap
					.get("lObjTrnBillRegisterVO");
			Integer lIntRowCount = StringUtility.getParameter("rowCount",
					request) != null ? Integer.parseInt(StringUtility
					.getParameter("rowCount", request)) : null;
			//System.out.println("Value of Integer Row Count in PhyBillServiceImpl : "					+ lIntRowCount);
			// getting Workflow VO for set of bill no
			ServiceLocator serv = (ServiceLocator) servicemap
					.get("serviceLocator");
			Long lLngBillNo = null;
			String lStrLocCode = SessionHelper.getLocationCode(servicemap);
			Long lLngCurUserId = SessionHelper.getUserId(request);
			Long lLngCurPostId = SessionHelper.getPostId(servicemap);
			Long lLngLangId = SessionHelper.getLangId(servicemap);
			Long lLngTokenNo = Long.parseLong(request
					.getParameter("txtTokenNo").toString());
			Map result;
			Iterator it;

			/* Getting Instances of DAO */
			FinancialYearDAOImpl lObjFinYearDAOImpl = new FinancialYearDAOImpl(
					SgvcFinYearMst.class, serv.getSessionFactory());
			FrmServiceMstDAOImpl servDetailsImpl = new FrmServiceMstDAOImpl(
					FrmServiceMst.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			FrmServiceMst servDetails = servDetailsImpl
					.readService("GENERATE_ID_SRVC");
			ResultObject resultObj = serv.executeService(servDetails,
					servicemap);
			BudgetHdServiceImpl lObjBudHdSrvc = new BudgetHdServiceImpl();
			BudgetHdDtlsServiceImpl lObjBudHdDtlsSrvcImpl = new BudgetHdDtlsServiceImpl();
			OrgTokenStatusDAOImpl lObjTknStatusDAOImpl = new OrgTokenStatusDAOImpl(
					OrgTokenStatus.class, serv.getSessionFactory());
			boolean tokenFlag = lObjTknStatusDAOImpl.isValidateToken(
					lLngTokenNo, lStrLocCode);

			String prevBillCtrlNo = (StringUtility.getParameter(
					"txtPrevBillNo", request).length() > 0) ? StringUtility
					.getParameter("txtPrevBillNo", request) : null;
			String tcName = (StringUtility.getParameter("cmbTCCtgry", request)
					.length() > 0) ? StringUtility.getParameter("cmbTCCtgry",
					request) : null;

			// String lStrRcptNo = request.getParameter("txtChallanNo");
			Long lLngFinYearId = Long.valueOf(String.valueOf(lObjFinYearDAOImpl
					.getFinYearIdByCurDate()));
			logger.info("Previous Bill No====" + prevBillCtrlNo);

			long prevBillFlag = 0;
			long challanFlag = 0;
			boolean lHeadsFlag = false;
			boolean lChlnMjrHdFlag = true;
			boolean lBillTypeFlag = true;
			int k = 0;
			if (prevBillCtrlNo != null) {
				prevBillFlag = lObjCmnSrvcDAOImpl
						.checkPreBillNo(prevBillCtrlNo);
			}

			lHeadsFlag = lObjBudHdSrvc.validateHeads(servicemap);
			logger.info("Value of Heads Flag in Physical Bill Service : "
					+ lHeadsFlag);
			logger.info("\n\nPrevious Bill Flag=======" + prevBillFlag);
			logger.info("Value of Bill Type in PhyBillSErvice : " +lObjTrnBillRegisterVO.getSubjectId());
/*	Validating bill type code 	*/			
			Long lLngSubId = lObjTrnBillRegisterVO.getSubjectId();
			if(lLngSubId!=null)
			{
				if(lLngSubId<=0)
				{
					lBillTypeFlag = false;
				}
			}
/*	Validating bill type code 	*/			
			if (prevBillFlag != 0)
				lObjTrnBillRegisterVO.setPrevBillNo(prevBillFlag);
			if (tcName.equalsIgnoreCase("TC")) {
				//System.out.println("Size of TrnReceipt List : "						+ lListTrnRcptDtlsVO.size());
				for (k = 0; k < lListTrnRcptDtlsVO.size(); k++) {
					//System.out.println("Inside for....");
					TrnReceiptDetails lObjTrnRcptDtls = (TrnReceiptDetails) lListTrnRcptDtlsVO
							.get(k);
					lChlnMjrHdFlag = lObjBudHdDtlsSrvcImpl.validateBudgetHeads(
							serv, lLngFinYearId, "", lObjTrnRcptDtls
									.getMajorHead(), "", "", "", "00",
							lLngLangId, "R");
					logger.info("Value of Challan Major head flag : "
							+ lChlnMjrHdFlag);
					if (lChlnMjrHdFlag == false)
						break;
					String lStrRcptNo = (StringUtility.getParameter(
							"txtChallanNo" + (k + 1), request).length() > 0) ? StringUtility
							.getParameter("txtChallanNo" + (k + 1), request)
							: null;
					if (lStrRcptNo != null)// && lStrRcptNo.length()>0)
					{
						challanFlag = lObjCmnSrvcDAOImpl
								.checkReceiptNo(lStrRcptNo);
						if (challanFlag == -1)
							break;
						logger.info("Value of ChallanFlag in loop : "
								+ challanFlag);
					}
				}
			}
			if (tokenFlag == true && prevBillFlag != -1 && challanFlag != -1
					&& lHeadsFlag == true && lChlnMjrHdFlag == true && lBillTypeFlag==true) {
				logger.info("Value of Attachment ID from SEsion"
						+ session.getAttribute("AttachID"));
				if (session.getAttribute("AttachID") != null) {
					Long lLngAttachId = Long.parseLong(session.getAttribute(
							"AttachID").toString());
					logger.info("Value of Long ATTAch ID in physical bill :: "
							+ lLngAttachId);
					lObjTrnBillRegisterVO.setAttachmentId(lLngAttachId);
					session.removeAttribute("AttachID");
				}
				lObjTrnBillRegisterVO.setRefNum(lObjCmnSrvcDAOImpl
						.getMaxRefNum(lStrLocCode));
				BptmCommonServiceImpl.insertBillRegister(lObjTrnBillRegisterVO,
						servicemap);
				wfVO.setJobRefId(String.valueOf(lObjTrnBillRegisterVO
						.getBillNo()));
				lLngBillNo = lObjTrnBillRegisterVO.getBillNo();
				logger
						.info("Physical Bill Data Inserted Successfully with Bill No not TC: "
								+ lLngBillNo);
				Map lMapNew = new HashMap();
				if (tcName.equalsIgnoreCase("TC")) {
					//System.out.println("Size of TrnReceipt List : "						+ lListTrnRcptDtlsVO.size());
					for (k = 0; k < lIntRowCount; k++) {
						//System.out.println("Inside for....");
						TrnReceiptDetails lObjTrnRcptDtls = (TrnReceiptDetails) lListTrnRcptDtlsVO
								.get(k);
						lChlnMjrHdFlag = lObjBudHdDtlsSrvcImpl
								.validateBudgetHeads(serv, lLngFinYearId, "",
										lObjTrnRcptDtls.getMajorHead(), "", "",
										"", "00", lLngLangId, "R");
						// if(lStrRcptNo!=null)// && lStrRcptNo.length()>0)
						{
							// challanFlag =
							// lObjCmnSrvcDAOImpl.checkReceiptNo(lStrRcptNo);
						}
						/* Inserting Receipt details in 'trn_receipt_detail' */
						BptmCommonServiceImpl.insertReceiptDetails(
								lObjTrnRcptDtls, servicemap);
						RltBillChallan lObjBillChallan = new RltBillChallan();
						lObjBillChallan.setBillNo(lLngBillNo);
						lObjBillChallan.setChallanNo(lObjTrnRcptDtls
								.getReceiptDetailId());
						lObjBillChallan.setTrnCounter(new Integer(1));
						lObjBillChallan.setCreatedDate(new java.util.Date());
						lObjBillChallan.setCreatedPostId(SessionHelper
								.getPostId(servicemap));
						lObjBillChallan.setCreatedUserId(SessionHelper
								.getUserId(request));
						lObjBillChallan.setDbId(SessionHelper
								.getDbId(servicemap));
						lObjBillChallan.setLocationCode(SessionHelper
								.getLocationCode(servicemap));
						BptmCommonServiceImpl.insertRltBillChallan(
								lObjBillChallan, servicemap);
					}
					/* End : Inserting Receipt details in 'trn_receipt_detail' */
					// Long lLngRcptDtls =
					// lObjTrnRcptDtlsVO.getReceiptDetailId();
					// lObjTrnBillRegisterVO.setReceiptId(lLngRcptDtls.toString());
				}

				/* Block for Bill BudgetHead Service. */
				String locationId = bundleConst.getString("CMN.Loc1");
				String locale = bundleConst.getString("CMN.LangId");
				BudHdDAOImpl budHdDAO = new BudHdDAOImpl(serv
						.getSessionFactory());
				ArrayList lArrBPNNo = budHdDAO.getAllBPNByDemand(lObjBudHeadVo
						.getDmndNo(), locale, locationId);
				String BPNCode = null;
				if (lArrBPNNo != null && lArrBPNNo.size() > 0) {
					it = lArrBPNNo.iterator();
					while (it.hasNext()) {
						BudExpEstBPNCodeVO bpnCodeVO = (BudExpEstBPNCodeVO) it
								.next();
						BPNCode = bpnCodeVO.getBPNCode();
					}
				}
				lObjBudHeadVo.setBpnNo(BPNCode);
				lObjBudHeadVo.setBillNo(lLngBillNo);
				BptmCommonServiceImpl.insertBudHeadDtls(lObjBudHeadVo,
						servicemap);
				/* End : Block for Bill BudgetHead Service. */

				/* Block for Token Usage Service. */
				lObjTknStatusDAOImpl.updateTokenStatus(lLngTokenNo,
						lStrLocCode, lLngBillNo, lLngCurUserId, lLngCurPostId);
				/* End : Block for Token Usage Service. */

				/* Block for Rpt Expenditure Details */
				String lStrDemandNo = lObjTrnBillRegisterVO.getDemandCode();
				//System.out						.println("Value of Demand No from TrnBillRegiste in Service : "								+ lStrDemandNo);
				RptExpenditureDtls lObjRptExpDtls = null;
				if (!lStrDemandNo.equals("999")) {
					servicemap.put("lObjTrnBillRegisterVO",
							lObjTrnBillRegisterVO);
					lObjRptExpDtls = lObjPhyBillVoGen
							.generateTrnExpVO(servicemap);
					servicemap.put("RptExpenditureVO", lObjRptExpDtls);
					//System.out.println("Value of Bill nO for TrnExpVo : "							+ lLngBillNo);
					lObjRptExpDtls
							.setExpNo(new java.math.BigDecimal(lLngBillNo));

					Map returnDSS = lObjRptExpDtls.validateData();
					Boolean returnStatus = (Boolean) returnDSS.get("flag");
					//System.out.println("Value of lObjRptExpDtls.validate() : "							+ returnStatus);
					if (returnStatus.booleanValue()) {
						DssDataService lObjDssDataSrvc = new DssDataServiceImpl();

						if (!tcName.equalsIgnoreCase("TC")) {
							Map lMapPass = new HashMap();

							lMapPass.put("RptExpenditureVO", lObjRptExpDtls);
							lMapPass.put("map", servicemap);
							//System.out.println("Inside not equal to TC Bill");
							lMapNew = lObjDssDataSrvc.insertExpData(lMapPass);
						}
						//System.out								.println("Value after insertion of RptExpenditure : "										+ lObjRptExpDtls.getExpId());
						//System.out								.println("Map returned from  insertExpData : "										+ lMapNew.toString());
					}
				}
				/* End : Block for Rpt Expenditure Details */

				/* Block for Bill Movement Service. */
				lObjBillMvmntVO.setBillNo(lLngBillNo);
				lObjBillMvmntVO.setMvmntStatus(bundleConst.getString(
						"STATUS.BillInward").toString());
				BptmCommonServiceImpl.insertMovement(lObjBillMvmntVO,
						servicemap);
				/* Block for Bill Movement Service. */

				/* Block for Bill Remarks Service. */
				lObjTrnBillRemarksVO.setBillNo(lLngBillNo);
				lObjTrnBillRemarksVO.setBillMvmntId(lObjBillMvmntVO
						.getBillMvmtId());
				BptmCommonServiceImpl.insertRemarks(lObjTrnBillRemarksVO,
						servicemap);
				/* End : Block for Bill Remarks Service. */

				/* Block for Rpt Receipt Details */
				DssDataService lObjDssDataSrvc = new DssDataServiceImpl();
				servicemap.put("lObjTrnBillRegisterVO", lObjTrnBillRegisterVO);

				if (tcName.equalsIgnoreCase("TC")
						&& (!lStrDemandNo.equals("999"))) {
					Map lMapNewPass = new HashMap();
					List lLstRptRcptVo = new ArrayList();
					for (k = 0; k < lIntRowCount; k++) {
						servicemap.remove("RptReceiptVOArrLst");
						servicemap.remove("TrnReceiptDetailsVO");

						TrnReceiptDetails lObjRcptDtls = new TrnReceiptDetails();
						lObjRcptDtls = (TrnReceiptDetails) lListTrnRcptDtlsVO
								.get(k);
						servicemap.put("TrnReceiptDetailsVO", lObjRcptDtls);
						//System.out								.println("Value of Challan ID in PhyBillServiceImpl : "										+ lObjRcptDtls.getReceiptDetailId());
						RptReceiptDtls lObjRptRcptDtls = lObjPhyBillVoGen
								.generateRptRcptVO(servicemap);
						// lLstRptRcptVo.add(lObjRptRcptDtls);
						lObjRptRcptDtls.setRcptNo(new java.math.BigDecimal(
								lLngBillNo));
						lObjRptRcptDtls
								.setTrnReceiptId(new java.math.BigDecimal(
										lObjRcptDtls.getReceiptDetailId()));

						//System.out								.println("Value of lObjRptRcptDtls[0].validate() : "										+ lObjRptRcptDtls.validateData());
						Map returnDSS1 = lObjRptRcptDtls.validateData();
						Boolean returnStatus1 = (Boolean) returnDSS1
								.get("flag");
						//System.out								.println("Value of lObjRptRcptDtls[0].validate() : "										+ returnStatus1);

						if (returnStatus1.booleanValue()) {

							lMapNewPass.remove("RptExpenditureVO");
							String lStrExpTypeCode = BptmCommonServiceImpl
									.getExpType(servicemap, bundleConst
											.getString("CMN.Bill"));
							System.out
									.println("Value of lStrExpTypeCode in insertPhyBillDetails : "
											+ lStrExpTypeCode);
							//System.out.println("expTypeCode : "									+ lStrExpTypeCode);
							//System.out.println("expNo : " + lLngBillNo);							lMapNewPass.put("expNo", lLngBillNo);
							lMapNewPass.put("expTypeCode", lStrExpTypeCode);
							RptExpenditureDtls lObjRptExpVO = new RptExpenditureDtls();
							lObjRptExpVO = rptExpCopyVo(lObjRptExpDtls,
									servicemap);
							lMapNewPass.put("RptExpenditureVO", lObjRptExpVO);

							lLstRptRcptVo.add(lObjRptRcptDtls);

						}
					}
					lMapNewPass.put("RptReceiptVOArrLst", lLstRptRcptVo);
					servicemap.put("RptReceiptVOArrLst", lLstRptRcptVo);

					lMapNewPass.put("map", servicemap);
					lMapNew = lObjDssDataSrvc.insertExpData(lMapNewPass);
				}

				if (lStrDemandNo.equals("999"))// !tcName.equalsIgnoreCase("TC")
												// && )
				{
					List lLstRptRcptVo = new ArrayList();
					Map lMapNewPass = new HashMap();
					// for(k=0;k<lIntRowCount;k++)
					// {
					servicemap.remove("RptReceiptVOArrLst");
					servicemap.remove("TrnReceiptDetailsVO");

					TrnReceiptDetails lObjRcptDtls = new TrnReceiptDetails();

					if (lListTrnRcptDtlsVO != null
							&& lListTrnRcptDtlsVO.size() > 0)// &&
																// lListTrnRcptDtlsVO.get(0)!=null)
					{
						lObjRcptDtls = (TrnReceiptDetails) lListTrnRcptDtlsVO
								.get(0);
						servicemap.put("TrnReceiptDetailsVO", lObjRcptDtls);
					}
					RptReceiptDtls lObjRptRcptDtls = lObjPhyBillVoGen
							.generateRptRcptVO(servicemap);

					lObjRptRcptDtls.setRcptNo(new java.math.BigDecimal(-1));
					lObjRptRcptDtls.setTrnReceiptId(new java.math.BigDecimal(
							lLngBillNo));
					lObjRptRcptDtls.setDisbursementAmt(lObjTrnBillRegisterVO
							.getBillGrossAmount());
					/*
					 * } else { lObjRptRcptDtls.setRcptNo(new
					 * java.math.BigDecimal(-1));
					 * lObjRptRcptDtls.setTrnReceiptId(new
					 * java.math.BigDecimal(lLngBillNo)); }
					 */System.out
							.println("Value of lObjRptRcptDtls[0].validate() : "
									+ lObjRptRcptDtls.validateData());
					Map returnDSS1 = lObjRptRcptDtls.validateData();
					Boolean returnStatus1 = (Boolean) returnDSS1.get("flag");
					System.out
							.println("Value of lObjRptRcptDtls[0].validate() : "
									+ returnStatus1);
					if (returnStatus1.booleanValue()) {
						lLstRptRcptVo.add(lObjRptRcptDtls);
					}
					lMapNewPass.put("RptReceiptVOArrLst", lLstRptRcptVo);
					servicemap.put("RptReceiptVOArrLst", lLstRptRcptVo);

					lMapNewPass.put("map", servicemap);
					//System.out.println("Inside Demand is equal to 999");
					lMapNew = lObjDssDataSrvc.insertReceiptData(lMapNewPass);
					// }
				}
				/*
				 * if(tcName.equalsIgnoreCase("TC") ||
				 * (lStrDemandNo.equals("999"))) {
				 * 
				 * List lLstRptRcptVo =
				 * lObjPhyBillVoGen.generateRptRcptVO(servicemap);
				 * RptReceiptDtls lObjRptRcptDtls =
				 * (RptReceiptDtls)lLstRptRcptVo.get(0);
				 * 
				 * if(!lStrDemandNo.equals("999")) {
				 * lObjRptRcptDtls.setRcptNo(new
				 * java.math.BigDecimal(lLngBillNo));
				 * lObjRptRcptDtls.setTrnReceiptId(new
				 * java.math.BigDecimal(lObjTrnRcptDtlsVO.getReceiptDetailId())); }
				 * else { lObjRptRcptDtls.setRcptNo(new
				 * java.math.BigDecimal(-1));
				 * lObjRptRcptDtls.setTrnReceiptId(new
				 * java.math.BigDecimal(lLngBillNo)); }
				 * 
				 * //System.out.println("Value of lObjRptRcptDtls[0].validate() : "
				 * +lObjRptRcptDtls.validateData()); Map returnDSS1 =
				 * lObjRptRcptDtls.validateData(); Boolean returnStatus1 =
				 * (Boolean)returnDSS1.get("flag"); //System.out.println("Value of
				 * lObjRptRcptDtls[0].validate() : " +returnStatus1);
				 * 
				 * if(returnStatus1.booleanValue()) { Map lMapNewPass = new
				 * HashMap();
				 * 
				 * if(!lStrDemandNo.equals("999") &&
				 * tcName.equalsIgnoreCase("TC")) { String lStrExpTypeCode =
				 * BptmCommonServiceImpl.getExpType(servicemap,
				 * bundleConst.getString("CMN.Bill")); //System.out.println("Value
				 * of lStrExpTypeCode in insertPhyBillDetails : "
				 * +lStrExpTypeCode); //System.out.println("expTypeCode : "
				 * +lStrExpTypeCode); //System.out.println("expNo : "
				 * +lLngBillNo); lMapNewPass.put("expNo",lLngBillNo);
				 * lMapNewPass.put("expTypeCode",lStrExpTypeCode);
				 * lMapNewPass.put("RptExpenditureVO", lObjRptExpDtls); }
				 * 
				 * lLstRptRcptVo.add(lObjRptRcptDtls);
				 * 
				 * lMapNewPass.put("RptReceiptVOArrLst", lLstRptRcptVo);
				 * servicemap.put("RptReceiptVOArrLst", lLstRptRcptVo);
				 * 
				 * lMapNewPass.put("map", servicemap);
				 * if(!lStrDemandNo.equals("999")) { //System.out.println("Inside
				 * Demand not equal to 999"); lMapNew =
				 * lObjDssDataSrvc.insertExpData(lMapNewPass); } else {
				 * //System.out.println("Inside Demand is equal to 999"); lMapNew =
				 * lObjDssDataSrvc.insertReceiptData(lMapNewPass); }
				 * //System.out.println("Map returned from insertReceiptData : "
				 * +lMapNew.toString());
				 */
				if (tcName.equalsIgnoreCase("TC") && lStrDemandNo.equals("999")) {
					Map lMapDss1 = new HashMap();
					Map lMapReturn = new HashMap();
					List lLstRptRcptVo1 = new ArrayList();
					for (k = 0; k < lIntRowCount; k++) {
						servicemap.remove("RptReceiptVOArrLst");
						servicemap.remove("TrnReceiptDetailsVO");
						TrnReceiptDetails lObjRcptDtls = new TrnReceiptDetails();
						lObjRcptDtls = (TrnReceiptDetails) lListTrnRcptDtlsVO
								.get(k);
						servicemap.put("TrnReceiptDetailsVO", lObjRcptDtls);

						RptReceiptDtls lObjRptRcptDtls1 = lObjPhyBillVoGen
								.generateRptRcptVO(servicemap);

						lObjRptRcptDtls1.setRcptNo(new java.math.BigDecimal(
								lLngBillNo));
						lObjRptRcptDtls1
								.setTrnReceiptId(new java.math.BigDecimal(
										lObjRcptDtls.getReceiptDetailId()));
						Map lMapStatus = lObjRptRcptDtls1.validateData();
						Boolean lBFlag = (Boolean) lMapStatus.get("flag");
						System.out
								.println("Value of lObjRptRcptDtls[0].validate() in demand 9999 for TC : "
										+ lBFlag);
						if (lBFlag.booleanValue()) {
							lLstRptRcptVo1.add(lObjRptRcptDtls1);
						}
					}
					lMapDss1.put("RptReceiptVOArrLst", lLstRptRcptVo1);
					servicemap.put("RptReceiptVOArrLst", lLstRptRcptVo1);

					lMapDss1.put("map", servicemap);

					System.out
							.println("Before inserting data for TC bill with demand 9999");
					lMapReturn = lObjDssDataSrvc.insertReceiptData(lMapDss1);

					System.out
							.println("Value of Map returned from insertReceiptData : "
									+ lMapReturn);
				}
				/* End : Block for Rpt Receipt Details */

				servicemap.put("jobId", (lLngBillNo.toString()));
				servicemap.put("postId", lLngCurPostId.toString());
				logger.info("Value of Job id and Post id is SET :: ");
				servicemap.put("BillMovementVO", lObjBillMvmntVO);

				result = (Map) resultObj.getResultValue();
				result.put("result", "Record Successfully Inserted......");
				objRes.setResultCode(ErrorConstants.SUCCESS);
				result.put("status", "Bill Inwarded Successfully");
				if (request.getParameter("actionBtn") != null
						&& !request.getParameter("actionBtn").equals("")) {
					String act = request.getParameter("actionBtn");
					logger.info("Value of Action Button : " + act);
					if (act.equalsIgnoreCase("forward")) {
						// PhyBillDAOImpl lObjPhyBillDAOImpl = new
						// PhyBillDAOImpl(TrnBillRegister.class,serv.getSessionFactory());
						// lObjPhyBillDAOImpl.updateRptExpDtls(servicemap,
						// lLngBillNo, lObjTrnBillRegisterVO);
						logger.info("-----------------Inside Bill forward");
						servicemap.put("$ref$", lObjTrnBillRegisterVO
								.getRefNum());
						servicemap.put("MESSAGECODE", (long) 1038);
					} else if (act.equalsIgnoreCase("save")
							|| act.equalsIgnoreCase("SaveNew")) {
						logger.info("-----------------Inside Bill inwarded");
						servicemap.put("StateMsg",
								"Bill Saved Successfully with Reference Number:"
										+ lObjTrnBillRegisterVO.getRefNum());
						servicemap.put("$ref$", lObjTrnBillRegisterVO
								.getRefNum());
						servicemap.put("MESSAGECODE", (long) 1043);
					}
				}
				servicemap.put("WorkFlowVO", wfVO);
				servicemap.put("tokenFlag", true);
				objRes.setResultValue(servicemap);
				objRes.setViewName("ajaxData");
			} else {
				result = (Map) resultObj.getResultValue();
				servicemap.put("result", "Record Not Inserted......");

				objRes.setResultCode(ErrorConstants.SUCCESS);
				if (prevBillFlag == -1) {
					logger
							.info("-----------------Inside Invalid Previous Bill No.");
					servicemap.put("status", "Invalid Previous Bill No.");
					servicemap.put("MESSAGECODE", (long) 1042);
					servicemap.put("StateMsg", "Invalid Previous Bill No.");
					servicemap.put("tokenFlag", false);
				}
				if (tokenFlag == false) {
					logger.info("-----------------Inside Invalid Token No.");
					servicemap.put("MESSAGECODE", (long) 1041);
					servicemap.put("StateMsg", "Invalid Token No.");
					servicemap.put("tokenFlag", false);
				}
				if (challanFlag == -1) {
					logger.info("-----------------Inside Invalid Challan No.");
					servicemap.put("MESSAGECODE", (long) 1039);
					servicemap.put("StateMsg", "Receipt No. already exists");
					servicemap.put("tokenFlag", false);
				}
				if (lHeadsFlag == false) {
					logger
							.info("-----------------Inside Invalid Head Structure.");
					servicemap.put("MESSAGECODE", (long) 1044);
					servicemap.put("StateMsg", "Invalid Head Structure");
					servicemap.put("tokenFlag", false);
				}
				if (lChlnMjrHdFlag == false) {
					logger
							.info("------------Inside Invalid Challan Major Head");
					servicemap.put("MESSAGECODE", (long) 1049);
					servicemap.put("StateMsg", "Invalid Challan Major Head ");
					servicemap.put("tokenFlag", false);
				}
/*	Validating bill type code */				
				if(lBillTypeFlag==false)
				{
					logger.info("--------------Inside Invalid Bill Type Code.");
					servicemap.put("MESSAGECODE", (long) 2048);
					servicemap.put("StateMsg", "Invalid Code for Bill Type");
					servicemap.put("tokenFlag", false);
				}
/*	Ends : Validating bill type code */				
				if (tokenFlag == true && prevBillFlag != -1
						&& challanFlag != -1 && lHeadsFlag == true
						&& lChlnMjrHdFlag == true && lBillTypeFlag==true) {
					logger
							.info("-----------------Inside Bill inserted Successfully");
					servicemap.put("MESSAGECODE", (long) 1043);
					servicemap.put("StateMsg",
							"Bill Saved Successfully with Reference Number:"
									+ lObjTrnBillRegisterVO.getRefNum());
					servicemap.put("tokenFlag", true);
				}
				wfVO.setAppMap(servicemap);
				servicemap.put("WorkFlowVO", wfVO);
			}
			logger
					.info("Value of MESSAGECODE in insert Physical Bill Details 111111111  :: "
							+ servicemap.get("StateMsg"));
			objRes.setResultValue(servicemap);
			objRes.setViewName("ajaxData");
			logger
					.info("Value of MESSAGECODE in insert Physical Bill Details :: "
							+ servicemap.get("StateMsg"));
			logger.info("View SEt to : " + objRes.getViewName());
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in insertPhyBillServiceImpl " + e, e);
		}
		return servicemap;
	}

	/**
	 * Method to Create job_ref(bills) in workflow tables.
	 * 
	 * @param Map :
	 *            inputMap
	 * 
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject CreateBillFromWF(Map inputMap) {
		/* Workflow Code starts here */

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			WorkFlowVO workFlowVO = new WorkFlowVO();
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			HttpSession session = request.getSession();
			Long langId = SessionHelper.getLangId(inputMap);
			/* Doing for TC bill Hierarchy */
			PhyBillDAOImpl lObjPhyBillDAOImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			String subjectName = "Bill Processing";
			/*
			 * Long
			 * billCategory=Long.parseLong(StringUtility.getParameter("cmbTCCtgry",
			 * request)); String billCtgry =
			 * lObjCmnSrvcDAOImpl.getLookUpText((billCategory.toString()),langId);
			 */String billCtgry = StringUtility.getParameter("cmbTCCtgry",
					request);
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
			workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
			workFlowVO
					.setFromPost(SessionHelper.getPostId(inputMap).toString());
			workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
			workFlowVO.setConnection(conn);
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);

			if (billCtgry != null && !(billCtgry.equalsIgnoreCase("Regular"))) {
				subjectName = "TC Bill Processing";
			}
			Map resultMap = orgUtil.getHierarchyByPostIDAndDescription(
					SessionHelper.getPostId(inputMap).toString(), subjectName);

			/* Workflow Code ends here */

			List resultList = (List) resultMap.get("Result");
			Long lStrHeirRefId = new Long(0);
			if (resultList != null && resultList.size() > 0) {
				lStrHeirRefId = Long.parseLong(resultList.get(0).toString());
			}

			logger.info(" Heirarchy found is........... " + lStrHeirRefId);
			workFlowVO.setHirRefId(lStrHeirRefId);
			WorkFlowUtility wfUtility = new WorkFlowUtility();

			workFlowVO.setActId(Long.parseLong(bundleConst
					.getString("CMN.CreateActId")));
			workFlowVO.setDocId(Long.parseLong(bundleConst
					.getString("CMN.PhyBillDocId")));
			workFlowVO.setJobRefId("1");
			workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
			workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
			logger.info(" Workflow VO set ====" + workFlowVO);
			Map returnMap = wfUtility.invokeWorkFlow(workFlowVO);
			logger.info("\n\n AFter ~~~~~~~~~~~~~~~~~~~~ workflow");
			WorkFlowVO wrkFlw = (WorkFlowVO) returnMap.get("WorkFlowVO");
			String actionBtn = (String) request.getParameter("actionBtn");
			logger.info("Action Button value in Create Bill from WorkFlow :: "
					+ actionBtn);
			Map mp = wrkFlw.getAppMap();
			boolean state = true;
			if (mp.get("tokenFlag") != null
					&& mp.get("tokenFlag").toString().length() > 0) {
				state = ((Boolean) mp.get("tokenFlag")).booleanValue();
				logger.info("Value of state : " + state);
				if (state == true) {
					logger
							.info("*********inside if(state==true), value of actionBtn : "
									+ actionBtn);
					if (actionBtn != null && !actionBtn.equals("")) {
						logger.info("Value of state inside if(state==true)");
						if (actionBtn.equalsIgnoreCase("forward")) {
							logger.info("Value of map before forward: "
									+ wrkFlw.getAppMap());
							logger
									.info("Value of Document in Cerate bill form WF ::: "
											+ mp.get("jobId"));
							if (request.getParameter("toPost") != null) {
								postId = request.getParameter("toPost");
								String posts[] = postId.split("~");
								mp.put("toPost", posts[0]);
								mp.put("toLevel", posts[1]);
							}
							userId = SessionHelper.getUserId(request)
									.toString(); // from session
							forwardBillFromWF(mp);
							logger.info("Value of map after forward: "
									+ wrkFlw.getAppMap());
							inputMap.put("MESSAGECODE", (long) 1038);
							objRes.setViewName("ajaxData");
						}
					}
				}
			}

			logger.info("Value of Application in CreateBill from WorkFlow  : "
					+ mp + "size : " + mp.size());
			logger.info("Value of MESSAGE CODE IN CREATEBILL FROM WORKFLOW  : "
					+ mp.get("MESSAGECODE"));
			logger.info("\n\n************* Should come here");
			if (mp.get("MESSAGECODE") != null) {
				logger.info("Long message code : " + mp.get("MESSAGECODE"));
				inputMap.put("MESSAGECODE", mp.get("MESSAGECODE"));
			}
			logger.info("VVVVVVVVVVVValue of Actin Buttona :::" + actionBtn);
			objRes.setViewName("ajaxData");
			if (actionBtn != null && actionBtn.equals("SaveNew")) {
				Long lLngTmpBill = null;
				TrnBillRegister lObjTrnBillRegister = (TrnBillRegister) mp
						.get("lObjTrnBillRegisterVO");
				System.out
						.println("Value of Bill No in Create bill From WorkFlow : "
								+ lObjTrnBillRegister.getBillNo());
				// lObjTrnBillRegister.setBillNo(lLngTmpBill);
				inputMap.put("requestObj", mp.get("requestObj"));
				logger.info(".................Value of State Message :: "
						+ mp.get("StateMsg"));
				inputMap.put("StatusMsg", mp.get("StateMsg"));
				logger
						.info("Value of token Flag in create Bill from WF inside SaveAndNew :: "
								+ state);

				NewBillVOGen lObjNewBillVOGen = new NewBillVOGen();
				NewBillVO newBillVO = lObjNewBillVOGen.voGenerator(inputMap);
				inputMap.put("NewBillVO", newBillVO);
				inputMap.put("DDOCode", newBillVO.getDdoCode());
				// if(mp.get("tokenFlag").equals("false"))
				if (state == false) {
					logger
							.info("Value of Gross Amount from Request in CreateBill From WorkFlow : "
									+ request.getParameter("txtGrossAmt"));
					logger
							.info("Value of Net Amount from Request  in CreateBill From WorkFlow : "
									+ request.getParameter("txtNetAmt"));

					if (request.getParameter("txtGrossAmt") != null)
						newBillVO.setBillGrossAmount(request
								.getParameter("txtGrossAmt"));
					if (request.getParameter("txtNetAmt") != null)
						newBillVO.setBillNetAmount(request
								.getParameter("txtNetAmt"));
					if (request.getParameter("txtTokenNo") != null)
						newBillVO.setTokenNum(request
								.getParameter("txtTokenNo"));
				}
				objRes = loadInwPhyBillScreen(inputMap);
			}
			objRes.setResultValue(inputMap);
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in createBillFromWF " + e, e);
		}
		logger.info("BILL SUCCESSFULLY CREATED FROM WORKFLOW");

		return objRes;
	}

	/**
	 * Method to enter details of job_reference(bill) in workflow tables if it
	 * is forwarded
	 * 
	 * @param Map :
	 *            inputMap
	 * 
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject forwardBillFromWF(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			String lStrBillNum = (String) inputMap.get("jobId");
			String lStrPostId = (String) inputMap.get("postId");
			TrnBillMvmnt lObjBillMvmntVO = new TrnBillMvmnt();
			BillMovementServiceImpl lObjBillMvmntSrvcImpl = new BillMovementServiceImpl();

			TrnBillMvmnt lObjBillMvmntVO1 = (TrnBillMvmnt) inputMap
					.get("BillMovementVO");
			lObjBillMvmntVO = lObjBillMvmntSrvcImpl.getMovementInstance(
					lObjBillMvmntVO1, inputMap);
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			Long langId = SessionHelper.getLangId(inputMap);
			logger
					.info(" \n************************ inside forward ******* Postid is  \n "
							+ lStrPostId);
			logger
					.info(" \n************************ inside forward ******* Job id is  \n "
							+ lStrBillNum);
			logger.info("\n login user post is " + postId);

			/* Workflow Code starts here */
			WorkFlowVO workFlowVO = new WorkFlowVO();
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			PhyBillDAOImpl phyDAO = new PhyBillDAOImpl(TrnBillRegister.class,
					serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			String curPostId = SessionHelper.getPostId(inputMap).toString();
			long lDocId = 0;
			if (lObjCmnSrvcDAOImpl.isPhyBill(Long.parseLong(lStrBillNum))) {
				lDocId = Long.parseLong(bundleConst
						.getString("CMN.PhyBillDocId"));
			} else {
				lDocId = Long.parseLong(bundleConst
						.getString("CMN.OnlineBillDocId"));
			}
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(userId); // change here for current empid
			workFlowVO.setCrtPost(postId); // change here for current post id
			workFlowVO.setCrtUsr(userId); // change here for current empid
			logger.info("Value of GLOBAL Post id in forward bill from workflow"
					+ postId);
			;
			logger.info("Value of Current Post id in forwardbillfrom workflow"
					+ curPostId);
			;
			workFlowVO.setFromPost(curPostId); // change here for current post
												// id
			workFlowVO.setConnection(conn);
			/*
			 * if(lObjBillMvmntVO.getMvmntStatus().equals("BAUD")) {
			 * logger.info("Sended from cardex to auditor "+ lStrPostId);
			 * workFlowVO.setToPost(lStrPostId);
			 * lObjBillMvmntVO.setStatusUpdtPostid(Long.parseLong(lStrPostId));
			 * lObjBillMvmntVO.setStatusUpdtUserid(Long.parseLong(phyDAO.getUserIdFromPost(lStrPostId,langId))); }
			 */
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			WorkFlowInterfaceImpl wfImpl = new WorkFlowInterfaceImpl(workFlowVO);
			if (inputMap.get("toPost") != null
					&& (!inputMap.get("toPost").equals("-1"))) {
				String toPostId = (String) inputMap.get("toPost");
				String toLevelId = (String) inputMap.get("toLevel");
				logger.info("Sended  to post is  " + toPostId
						+ " To level is :::: " + toLevelId);
				workFlowVO.setToPost(toPostId);
				lObjBillMvmntVO.setStatusUpdtPostid(Long.parseLong(toPostId));
				lObjBillMvmntVO.setStatusUpdtUserid(Long
						.parseLong(lObjCmnSrvcDAOImpl.getUserIdFromPost(
								toPostId, langId)));
				lObjBillMvmntVO.setReceivedFlag((short) 0);
				if (toLevelId != null && !toLevelId.equals("")) {
					workFlowVO.setToLevelId(Integer.parseInt(toLevelId));
				} else {
					// first of all get curent users level id from current job
					// mst.
					// then make a query of our own for getting next level from
					// the level obtained above
					//System.out.println("========= lStrBillNum ========= "							+ lStrBillNum);
					//System.out.println("========= lDocId ========= " + lDocId);
					List jobMstList = orgUtil.getJobMstByJobRefID(lStrBillNum,lDocId,0);
					if (jobMstList != null && jobMstList.size() > 0) {
						WfJobMstVO jobMstVO = (WfJobMstVO) jobMstList.get(0);
						Map upperUserMap = orgUtil.getUpperPost(postId,
								jobMstVO.getHierachyRefId(), jobMstVO
										.getLevelId());
						if (upperUserMap != null) {
							List resultList = (List) upperUserMap.get("Result");
							Object[] obj = (Object[]) resultList.get(0);
							toLevelId = obj[1].toString();
							System.out
									.println("------------------------------- tolevel id in case of cardex to auditor"
											+ toLevelId);
							workFlowVO
									.setToLevelId(Integer.parseInt(toLevelId));
						}
					}
				}
			}
			inputMap.put("BillMovementVO", lObjBillMvmntVO);
			workFlowVO.setAppMap(inputMap);

			WorkFlowUtility wfUtility = new WorkFlowUtility();
			workFlowVO.setActId(Long.parseLong(bundleConst
					.getString("CMN.ForwardActId")));

			// lDocId =
			// Long.parseLong(bundleConst.getString("CMN.PhyBillDocId"));
			workFlowVO.setDocId(lDocId);

			// workFlowVO.setDocId(Long.parseLong(bundleConst.getString("CMN.PhyBillDocId")));
			logger.info("\n\n =======lStrBillNum=== " + lStrBillNum);
			workFlowVO.setJobRefId(lStrBillNum);
			workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
			workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
			workFlowVO.setHierarchyFlag(1);
			logger.info("\n Workflow VO set ====" + workFlowVO.toString());

			wfUtility.invokeWorkFlow(workFlowVO);
			logger.info(" After Forward flow ------- ");
			inputMap.put("MESSAGECODE", (long) 1038);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in forwardBillFromWF " + e, e);
		}

		return objRes;
	}

	/**
	 * Method to set all the initial values that are loaded in inward physical
	 * bill screen at initial time
	 * 
	 * @param Map :
	 *            inputMap
	 * 
	 * @return ResultObject
	 * @author hiral
	 */
	public ResultObject loadInwPhyBillScreen(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");

			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BudHdDAOImpl budDao = new BudHdDAOImpl(serv.getSessionFactory());
			LocationDAOImpl lObjLocDAOImpl = new LocationDAOImpl(
					CmnLocationMst.class, serv.getSessionFactory());
			ResourceBundle lObjRsrcBndle = ResourceBundle
					.getBundle("resources/billproc/BillprocConstants");

			Long lLngLangId = SessionHelper.getLangId(inputMap);
			String lStrLocale = SessionHelper.getLocale(request);

			List lLstDept = BptmCommonServiceImpl.getDeptList(inputMap);
			inputMap.put("DeptList", lLstDept);

			List lLstBillCode = BptmCommonServiceImpl.getLookupValues(
					lObjRsrcBndle.getObject("CMN.BillCode").toString(),
					lLngLangId, inputMap);
			Object[] lObjTemp1 = lLstBillCode.toArray();
			Object[] lObjBillCode = new Object[lObjTemp1.length];
			for (Object lObj : lObjTemp1) {
				CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
				lObjBillCode[Integer.parseInt(String.valueOf(lObjComVO
						.getLookupShortName())) - 1] = (Object) lObjComVO;
			}
			lObjTemp1 = null;

			inputMap.put("BillCodeList", lObjBillCode);

			List lLstTcBill = BptmCommonServiceImpl.getLookupValues(
					lObjRsrcBndle.getObject("CMN.TcBillType").toString(),
					lLngLangId, inputMap);
			inputMap.put("TcBillList", lLstTcBill);

			List lLstGoNgo = BptmCommonServiceImpl.getLookupValues(
					lObjRsrcBndle.getObject("CMN.GoNgo").toString(),
					lLngLangId, inputMap);
			inputMap.put("goNgoList", lLstGoNgo);

			List lLstBudType = BptmCommonServiceImpl.getLookupValues(
					lObjRsrcBndle.getObject("CMN.BudgetType").toString(),
					lLngLangId, inputMap);
			Object[] lObjTemp = lLstBudType.toArray();
			Object[] lObjBudjetType = new Object[lObjTemp.length];
			for (Object lObj : lObjTemp) {
				CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
				lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO
						.getLookupShortName())) - 1] = (Object) lObjComVO;
			}
			lObjTemp = null;

			inputMap.put("planList", lObjBudjetType);

			List lLstBillType = lObjCmnSrvcDAOImpl.getBillType(lLngLangId);
			/*
			 * List lLstReturn = new ArrayList(); Iterator lItr =
			 * lLstBillType.iterator();
			 * 
			 * CommonVO lObjCmnVO = null; Object[] lObj = null;
			 * 
			 * while (lItr.hasNext()) { lObj = (Object[]) lItr.next(); lObjCmnVO =
			 * new CommonVO();
			 * 
			 * lObjCmnVO.setCommonId(lObj[0].toString()); if(lObj[1] == null) {
			 * lObjCmnVO.setCommonDesc(String.valueOf(lObj[2])); } else {
			 * lObjCmnVO.setCommonDesc(String.valueOf(lObj[2]) + " (" +
			 * String.valueOf(lObj[1]) + ")"); }
			 * 
			 * lLstReturn.add(lObjCmnVO); } //System.out.println("List returned
			 * from load sCreend : " +lLstReturn.toString());
			 */
			inputMap.put("BillType", lLstBillType);

			String lStrBillCtrlNo = BptmCommonServiceImpl
					.getBillControlNumber(inputMap);
			inputMap.put("BillCtrlNo", lStrBillCtrlNo);

			if (request.getParameter("actionBtn") != null
					&& request.getParameter("actionBtn").length() > 0) {
				if (request.getParameter("actionBtn").equals("SaveNew")) {
					logger
							.info("??????????????????????Value of Action Button in loadValues :: "
									+ request.getParameter("actionBtn"));
					ShowBillServiceImpl lObjShowBillServ = new ShowBillServiceImpl();
					lObjShowBillServ.loadValues(inputMap);
				}
			}
			List demandcode = budDao.getAllDemand(lStrLocale, bundleConst
					.getString("CMN.Loc1"));
			inputMap.put("DemandList", demandcode);
			TrnBillRegister lObjTrnBillRegisterVO = (TrnBillRegister) inputMap
					.get("lObjTrnBillRegisterVO");
			objRes.setResultValue(inputMap);
			objRes.setViewName("cntrInwPhyBills");
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in loadInwardPhyBill " + e, e);
		}
		return objRes;
	}

	/**
	 * Method to get All the Bills with its details as per condition.
	 * 
	 * @param Map :
	 *            inputMap
	 * 
	 * @return List
	 * @author Bhavik
	 */
	public List getMyBills(Map inputMap) {
		logger
				.info(" \n************************ in Get My Bills for post: *******  \n "
						+ postId);
		/* Workflow Code starts here */
		WorkFlowVO workFlowVO = new WorkFlowVO();
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		Connection conn = serv.getSessionFactory().getCurrentSession()
				.connection();
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		logger.info("================================= "
				+ SessionHelper.getUserName(request));

		List list = new ArrayList();
		List resultLst = null;
		try {
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
			workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
			workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
			workFlowVO.setConnection(conn);
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);

			workFlowVO.setActId(Long.parseLong(bundleConst
					.getString("CMN.CreateActId")));
			workFlowVO.setDocId(Long.parseLong(bundleConst
					.getString("CMN.PhyBillDocId")));

			workFlowVO.setJobRefId("1");
			workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
			workFlowVO.setDbId(SessionHelper.getDbId(inputMap));
			logger.info(" Workflow VO set ====" + workFlowVO);

			// list.add("3"); //adding the posts
			list.add(postId);
			// 5 -> for auditor
			// 4 -> for cardex
			// 3 -> for counter

			resultLst = orgUtil.getDocList(list);
			logger.info("get My Bills size: " + resultLst);
			logger.info(" get My Bills size: " + resultLst);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error in getMyBills " + e, e);
		}

		return resultLst;
	}

	/**
	 * Method to saev attachment in attachment tables.
	 * 
	 * @param Map :
	 *            objMap
	 * 
	 * @return ResultObject
	 * @author hiral
	 */
	public ResultObject saveAttachment(Map objMap) {
		ResultObject resultObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			logger.info("Inside Save Attachment");
			ServiceLocator serv = (ServiceLocator) objMap.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objMap
					.get("requestObj");
			HttpSession session = request.getSession();

			resultObj = serv.executeService("FILE_UPLOAD_VOGEN", objMap);
			logger
					.info("Value String length of resultObject from file_upload VOGEN : "
							+ resultObj.toString().length());
			logger
					.info("Value String length of resultObject from file_upload VOGEN : "
							+ resultObj.toString());
			logger.info("Value of Attachment Map :: "
					+ resultObj.getResultValue().toString());
			Map resultMap = (Map) resultObj.getResultValue();
			CmnAttachmentMst cmnAttach = (CmnAttachmentMst) resultMap
					.get("attachmentVO");
			logger.info("Value of resultMap from file_upload VOGEN : "
					+ resultMap.toString());

			resultObj = serv.executeService("FILE_UPLOAD_SRVC", objMap);
			Map attachMap = (Map) resultObj.getResultValue();
			logger.info("Size of Attachment Map :: " + attachMap.toString());
			Long lLngAttachId = Long.parseLong((attachMap
					.get("AttachmentId_attachment").toString()));
			logger
					.info("Value of Attachment ID in PhyBillSERviceIMPl, saveAttachment: "
							+ lLngAttachId);
			if (session.getAttribute("AttachID") != null) {
				logger
						.info("Beforeee Value of AttachId in saveAttachment if session!=null : "
								+ session.getAttribute("AttachID"));
				session.removeAttribute("AttachID");
				logger
						.info("After  Value of AttachId in saveAttachment if session!=null : "
								+ session.getAttribute("AttachID"));
			}
			session.setAttribute("AttachID", lLngAttachId);
			logger
					.info("After setting attachId in session in saveAttachment : "
							+ session.getAttribute("AttachID"));
			attachMap.put("CloseFlag", "1");
			resultObj.setResultValue(attachMap);
			resultObj.setViewName("cmnAttachment");
		} catch (Exception e) {
			resultObj.setResultValue(null);
			resultObj.setThrowable(e);
			resultObj.setResultCode(ErrorConstants.ERROR);
			resultObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in saveAttachment " + e, e);
		}
		return resultObj;
	}

	/**
	 * Method for returing the bills.
	 * 
	 * @param Map :
	 *            objMap
	 * 
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject returnBillFromWF(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);

		try {
			logger.info("Inside   returnBillFromWF");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
			HttpSession session = request.getSession();

			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			WorkFlowUtility wrkFlwUtil = new WorkFlowUtility();
			String lStrBillNum = (String) inputMap.get("jobId");

			BptmCommonServicesDAOImpl bptmDAO = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());

			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
			workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
			workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());
			workFlowVO.setConnection(conn);
			workFlowVO.setActId(Long.parseLong(bundleConst
					.getString("CMN.ReturnActId")));
			long lDocId = 0;
			if (bptmDAO.isPhyBill(Long.parseLong(lStrBillNum))) {
				lDocId = Long.parseLong(bundleConst
						.getString("CMN.PhyBillDocId"));
			} else {
				lDocId = Long.parseLong(bundleConst
						.getString("CMN.OnlineBillDocId"));
			}
			workFlowVO.setDocId(lDocId);
			workFlowVO.setJobRefId(lStrBillNum);
			workFlowVO.setLocID(SessionHelper.getLocationCode(inputMap));
			workFlowVO.setDbId(SessionHelper.getDbId(inputMap));

			logger.info("---------------------- Before Invoking ------");
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			String toPost = orgUtil.getReturnPost(workFlowVO);
			TrnBillMvmnt billMovement = (TrnBillMvmnt) inputMap
					.get("BillMovementVO");
			billMovement.setStatusUpdtPostid(Long.parseLong(toPost));
			String toUsr = bptmDAO.getUserIdFromPost(toPost, SessionHelper
					.getLangId(inputMap));
			billMovement.setStatusUpdtUserid(Long.parseLong(toUsr));
			billMovement.setReceivedFlag((short) 0);
			inputMap.put("BillMovementVO", billMovement);
			workFlowVO.setAppMap(inputMap);
			wrkFlwUtil.invokeWorkFlow(workFlowVO);
			logger.info("---------------------- After Invoking ------");
			objRes.setResultValue(inputMap);
		} catch (LowerPostNotFoundException e) {
			inputMap.put("NoLower", "Lower Person not found");
			//System.out.println("\n\n came for LowerPostNotFoundException");
		} catch (LowerRoleNotFoundException e) {
			inputMap.put("NoLower", "Lower Person not found");
			//System.out.println("\n\n came for LowerRoleNotFoundException");
		} catch (Exception e) {
			inputMap.put("NoLower", "Lower Person not found");
			//System.out.println("\n\n came for Exception");
			logger.error(" Error in Return ing bill " + e, e);

			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();

		}
		return objRes;
	}	
	/**
	 * Method for relese the token
	 * 
	 * @param Map :objMap
	 * 
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject releseToken(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			logger.info("inside the rel tok:::");
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");

			Locale locale = new Locale(SessionHelper.getLocale(request));
			/*
			 * ResourceBundle bundle
			 * =ResourceBundle.getBundle("resources/billproc/billproc",locale);
			 * ResourceBundle bundleConst
			 * =ResourceBundle.getBundle("resources/billproc/BillprocConstants");
			 * Map result = new HashMap();
			 */
			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();

			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setAppMap(inputMap);
			workFlowVO.setCrtEmpId(SessionHelper.getEmpId(inputMap).toString());
			workFlowVO
					.setFromPost(SessionHelper.getPostId(inputMap).toString());
			workFlowVO.setCrtPost(SessionHelper.getPostId(inputMap).toString());
			workFlowVO.setCrtUsr(SessionHelper.getUserId(request).toString());

			workFlowVO.setConnection(conn);

			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			String[] lStrBills = (String[]) request
					.getParameterValues("chkbox");
			OrgTokenStatusDAOImpl tokenDAO = new OrgTokenStatusDAOImpl(
					OrgTokenStatus.class, serv.getSessionFactory());
			for (int i = 0; i < lStrBills.length; i++) {
				String lStrTokNo[] = lStrBills[i].split("~");
				logger.info(" Token relese are :::: " + lStrTokNo[1]);
				tokenDAO.updateTokenRedeem(Long.parseLong(lStrTokNo[1]),
						SessionHelper.getLocationCode(inputMap), SessionHelper
								.getUserId(request), SessionHelper
								.getPostId(inputMap));
				if (BptmCommonServiceImpl.isPhyBill(Long
						.parseLong(lStrTokNo[0]), inputMap)) {
					/*orgUtil.updateJobStatus((SessionHelper.getPostId(inputMap))
							.toString(), lStrTokNo[0], "DeActive");*/
				} else {
					Long docId = Long.parseLong(bundleConst
							.getString("CMN.OnlineBillDocId"));
					List jobMst = orgUtil.getJobMstByJobRefID(lStrTokNo[0],docId,0);
					if (jobMst != null && jobMst.size() > 0) {
						WfJobMstVO jobMstVO = (WfJobMstVO) jobMst.get(0);
						List prntUsers = pbDaoImpl.nextDDOOfficeUsers(orgUtil
								.getParentHierarchyList(jobMstVO
										.getHierachyRefId()), Long
								.parseLong(lStrTokNo[0]));
						if (prntUsers != null && prntUsers.size() > 0) {
							Object[] obj = (Object[]) prntUsers.get(0);
							workFlowVO.setActId(Long.parseLong(bundleConst
									.getString("CMN.ForwardActId")));
							workFlowVO.setDocId(Long.parseLong(bundleConst
									.getString("CMN.OnlineBillDocId")));
							workFlowVO.setToPost(obj[0].toString());
							workFlowVO.setHirRefId(jobMstVO.getHierachyRefId());
							workFlowVO.setToLevelId(Integer.parseInt(obj[1]
									.toString()));
							workFlowVO.setToHirRefId(Long.parseLong(obj[2]
									.toString()));
							workFlowVO.setJobRefId(lStrTokNo[0]);
							System.out
									.println("------ workFlowVO.setToHirRefId  -------- "
											+ workFlowVO.getToHirRefId());
							new WorkFlowUtility().invokeWorkFlow(workFlowVO);
						}
					}
				}
			}
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in releaseToken " + e, e);
		}

		/*
		 * result.put("HeaderName", bundleConst.getString("VIEW.DISPREJBILL"));
		 * objRes.setViewName("viewCardexVerify");
		 * objRes.setResultValue(result);
		 */

		objRes = getSavedBills(inputMap);
		return objRes;
	}
	/**
	 * Method for geting Treasury Users
	 * 
	 * @param Map :objMap
	 * 
	 * @return ResultObject
	 * @author hiral
	 */
	public ResultObject getTreasuryUsers(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("Inside the getTreasuryUsers..............");
		try {
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			HttpSession hs = request.getSession();
			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");
			PhyBillDAOImpl pbDaoImpl = new PhyBillDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			String lStrBillNo = (String) request.getParameter("BillNo");
			String officeId = (String) request.getParameter("TsryOfficeId");
			String lCurrHeirId = (String) request
					.getParameter("CurrentHierarchy");
			String lStrReqFlg = (String) request.getParameter("ReqFlg"); // Added by Keyur for AJAX Response
			// Long lBillCat =
			// pbDaoImpl.read(Long.parseLong(lStrBillNo)).getTcBill();
			// String lStrTcBill =
			// lObjCmnSrvcDAOImpl.getLookUpText(lBillCat.intValue(),
			// SessionHelper.getLangId(objectArgs));
			String lStrTcBill = pbDaoImpl.read(Long.parseLong(lStrBillNo))
					.getTcBill();
			String lStrBillCat = "Bill Processing";
			if (lStrTcBill != null && !lStrTcBill.equals("Regular")) {
				lStrBillCat = "TC Bill Processing";
			}

			List resultList1 = null;
			Long langId = SessionHelper.getLangId(objectArgs);
			String lStrPostId = String.valueOf(SessionHelper
					.getPostId(objectArgs));

			Connection conn = serv.getSessionFactory().getCurrentSession()
					.connection();
			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setAppMap(objectArgs);
			workFlowVO.setConnection(conn);

			OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
			// getting heirarchy reference Id from office id selected.
			// lCurrHeirId ="110001";
			List hierarchyList = orgUtil.getUpperChildHierarchy(Long
					.parseLong(lCurrHeirId));
			// officeId = "1000";
			resultList1 = pbDaoImpl.nextTsryUsers(hierarchyList, Long
					.parseLong(officeId), lStrBillCat);
			String postString[] = new String[resultList1.size()];
			String levelString[] = new String[resultList1.size()];
			String toHeirarchyRefId = "";
			for (int i = 0; i < resultList1.size(); i++) {
				Object[] lStrNextPost = (Object[]) resultList1.get(i);
				postString[i] = lStrNextPost[0].toString();
				levelString[i] = lStrNextPost[1].toString();
				toHeirarchyRefId = lStrNextPost[2].toString();
			}

			List listSameLvlUser = null;
			if (resultList1.size() > 0) {
				listSameLvlUser = lObjCmnSrvcDAOImpl.getUserFromPost(
						postString, levelString, langId);
			}

			if (Integer.parseInt(lStrReqFlg) == 1) // Added by Keyur for AJAX
													// response
			{
				StringBuilder lStrUsrs = new StringBuilder();
				lStrUsrs.append("<TrsryUsers>");
				Object[] lObjVal = null;

				Iterator<Object[]> lItrUsr = listSameLvlUser.iterator();

				while (lItrUsr.hasNext()) {
					lObjVal = lItrUsr.next();

					lStrUsrs.append("<UsrList>");
					lStrUsrs.append("<Item1>" + lObjVal[0] + "</Item1>");
					lStrUsrs.append("<Item2>" + lObjVal[1] + "</Item2>");
					lStrUsrs.append("<Item3>" + lObjVal[2] + "</Item3>");
					lStrUsrs.append("</UsrList>");
				}

				lStrUsrs.append("<HierarchyRefId>" + toHeirarchyRefId
						+ "</HierarchyRefId>");
				lStrUsrs.append("<CurrentUserPost>"
						+ SessionHelper.getPostId(objectArgs).toString()
						+ "</CurrentUserPost>");
				lStrUsrs.append("</TrsryUsers>");

				String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
						lStrUsrs.toString()).toString();
				objectArgs.put("ajaxKey", lStrResult);

				objRes.setViewName("ajaxData");
			} else {
				objectArgs.put("userList11", listSameLvlUser);
				objectArgs.put("ToHeirarchyRefID", toHeirarchyRefId);
				objectArgs.put("currentUserPost", SessionHelper.getPostId(
						objectArgs).toString());
				objRes.setViewName("cmnSelectForward");
			}

			objRes.setResultValue(objectArgs);
		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getTreasuryUsers " + e, e);
		}
		return objRes;
	}

	/**
	 * This method creates the copy of RptExpenditureDtls(to solve the problem of PK duplication in hibernate)
	 * 
	 * @param        RptExpenditureDtls :lObjRptExpDtls
	 * @param        Map : inputMap
	 * 
	 * @return		 RptExpenditureDtls
	 */
	public RptExpenditureDtls rptExpCopyVo(RptExpenditureDtls lObjRptExpDtls,
			Map inputMap) {
		RptExpenditureDtls lObjRptExpDtls1 = new RptExpenditureDtls();
		lObjRptExpDtls1.setBillGrpCode(lObjRptExpDtls.getBillGrpCode());
		lObjRptExpDtls1.setBudTypeCode(lObjRptExpDtls.getBudTypeCode());
		lObjRptExpDtls1.setClsExpCode(lObjRptExpDtls.getClsExpCode());
		lObjRptExpDtls1.setCrtDt(lObjRptExpDtls.getCrtDt());
		lObjRptExpDtls1.setCrtUsr(lObjRptExpDtls.getCrtUsr());
		lObjRptExpDtls1.setDdoCode(lObjRptExpDtls.getDdoCode());
		lObjRptExpDtls1.setDeduction(lObjRptExpDtls.getDeduction());
		lObjRptExpDtls1.setDemandNo(lObjRptExpDtls.getDemandNo());
		lObjRptExpDtls1.setDeptCode(lObjRptExpDtls.getDeptCode());
		lObjRptExpDtls1.setDistrictCode(lObjRptExpDtls.getDistrictCode());
		lObjRptExpDtls1.setDtlHd(lObjRptExpDtls.getDtlHd());
		lObjRptExpDtls1.setExpAmt(lObjRptExpDtls.getExpAmt());
		lObjRptExpDtls1.setExpCrtDt(lObjRptExpDtls.getExpCrtDt());
		lObjRptExpDtls1.setExpDt(lObjRptExpDtls.getExpDt());
		lObjRptExpDtls1.setExpNo(lObjRptExpDtls.getExpNo());
		lObjRptExpDtls1.setExpStatusCode(lObjRptExpDtls.getExpStatusCode());
		lObjRptExpDtls1.setExpStatusDt(lObjRptExpDtls.getExpStatusDt());
		lObjRptExpDtls1.setExpTypeCode(lObjRptExpDtls.getExpTypeCode());
		lObjRptExpDtls1.setFinYrId(lObjRptExpDtls.getFinYrId());
		lObjRptExpDtls1.setFundTypeCode(lObjRptExpDtls.getFundTypeCode());
		lObjRptExpDtls1.setGrossAmnt(lObjRptExpDtls.getGrossAmnt());
		lObjRptExpDtls1.setMinHd(lObjRptExpDtls.getMinHd());
		lObjRptExpDtls1.setMjrHd(lObjRptExpDtls.getMjrHd());
		lObjRptExpDtls1.setNetAmt(lObjRptExpDtls.getNetAmt());
		lObjRptExpDtls1.setOfficeCode(lObjRptExpDtls.getOfficeCode());
		lObjRptExpDtls1.setScheme(lObjRptExpDtls.getScheme());
		lObjRptExpDtls1.setSubHd(lObjRptExpDtls.getSubHd());
		lObjRptExpDtls1.setSubMjrHd(lObjRptExpDtls.getSubMjrHd());
		lObjRptExpDtls1.setTsryCode(lObjRptExpDtls.getTsryCode());
		lObjRptExpDtls1.setActive(lObjRptExpDtls.getActive());
		lObjRptExpDtls1.setAgApprvl(lObjRptExpDtls.getAgApprvl());
		return lObjRptExpDtls1;
	}
}
