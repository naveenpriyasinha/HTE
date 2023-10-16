package com.tcs.sgv.billproc.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.dao.BillVitoDAOImpl;
import com.tcs.sgv.common.dao.ChequeVitoDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.valueobject.BillVitoRegister;
import com.tcs.sgv.common.valueobject.ChequeVitoRegister;
import com.tcs.sgv.common.valueobject.DDODetailsVO;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * com.tcs.sgv.billproc.reprot.ReportServiceImpl
 * 
 * This is Class for service VITO reports to be generated
 * 
 * Date of Creation : 10th July 2007
 * 
 * Author : Vidhya Mashru
 * 
 * Revision History
 * ===================== 
 * Vidhya M 23-Oct-2007 Made changes for code formatting
 */
public class ReportServiceImpl extends ServiceImpl implements ReportService {
	Log logger = LogFactory.getLog(getClass());

	// For Auditor Wise Daily Vito
	/**
	 * Method For Auditor Wise Daily Vito
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject getAuditorsList(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			Long langID = SessionHelper.getLangId(objectArgs);
			String pageName = request.getParameter("pageName").toString();
			//System.out.println("Page Name is :-" + pageName);
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List auditorList = reportImpl.getAuditorList(userId, LocId, langID,
					strLocationCode);
			objectArgs.put("auditorList", auditorList);
			objRes.setResultValue(objectArgs);
			if (pageName.equalsIgnoreCase("audWiseVito")) {
				objRes.setViewName("auditorListInput");
			} else if (pageName.equalsIgnoreCase("forAudit")) {
				objRes.setViewName("billTranRegForAudInp");
			} else if (pageName.equalsIgnoreCase("afterAudit")) {
				objRes.setViewName("billTranRegAfterAudInp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Auditor Wise Daily Vito
	/**
	 * Method For Auditor Wise Daily Vito 
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject genAudWiseDailyVito(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		HttpSession session = request.getSession();
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			Long langID = SessionHelper.getLangId(objectArgs);
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			String vitoType = request.getParameter("vitoType").toString();
			String strReportStatus = null;
			String strReportType = null;
			try {
				strReportStatus = request.getParameter("PrintReport")
						.toString();
				strReportType = request.getParameter("ReportType").toString();
			} catch (Exception Ex) {

			}

			String audPostId[] = null;
			audPostId = (String[]) request.getParameterValues("cmbAuditors");
			if (strReportStatus == null) {
				session.setAttribute("AuditorList", audPostId);
			} else if (strReportStatus != null) {
				audPostId = (String[]) session.getAttribute("AuditorList");
			}
			// //System.out.println("Aud User Is of 0 is:-"+audUserId[0]);
			// //System.out.println("Aud User Is of 0 is:-"+audUserId[0]);
			if (audPostId == null
					|| (audPostId.length == 1 && audPostId[0]
							.equalsIgnoreCase("-1"))) {
				List auditorList = reportImpl.getAuditorList(userId, LocId,
						langID, strLocationCode);
				Iterator it = auditorList.iterator();
				int count = 0;
				audPostId = new String[auditorList.size()];
				while (it.hasNext()) {
					ComboValuesVO vo = (ComboValuesVO) it.next();
					//System.out.println("id is :-" + vo.getId());
					audPostId[count++] = vo.getId();
				}
			}

			List vitoStringList = new ArrayList();
			List vitoList = reportImpl.genAudWiseDailyVito(userId, LocId,
					audPostId, vitoType, vitoStringList, strLocationCode,
					langID);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objectArgs.put("vitoStringList", vitoStringList);
			objRes.setResultValue(objectArgs);
			if (strReportStatus != null && strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails(
						"Auditor Wise Daily Vito Details", 80);
				headerDetails.add("Sr.No");
				headerWidth.add("-5");
				headerDetails.add("Auditor Name");
				headerWidth.add("-40");
				headerDetails.add("No. Of Bills");
				headerWidth.add("+15");
				headerDetails.add("Total Amount");
				headerWidth.add("+20");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strReportStatus != null
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails(
						"Auditor Wise Daily Vito Details", 132);
				headerDetails.add("Sr.No");
				headerWidth.add("-25");
				headerDetails.add("Auditor Name");
				headerWidth.add("-60");
				headerDetails.add("No. Of Bills");
				headerWidth.add("+16");
				headerDetails.add("Total Amount");
				headerWidth.add("+30");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("audWiseDailyRpt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Auditor Wise Daily Vito
	/**
	 * Method For Auditor Wise Daily Vito insertVito
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insertVitoDtls(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			Long locId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			String vitoType = request.getParameter("vitoType").toString();
			String audPostId[] = (String[]) request
					.getParameterValues("audUserId");
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			//System.out.println("aud is is length:-" + audPostId.length);
			for (int i = 0; i < audPostId.length; i++) {
				Long postId = Long.parseLong(audPostId[i]);
				List billList = reportImpl.getBillListByDailyVito(postId,
						vitoType, locId, strLocationCode);
				if (billList != null && billList.size() > 0) {
					Iterator it = billList.iterator();
					while (it.hasNext()) {
						TrnBillRegister billRegVo = (TrnBillRegister) it.next();
						Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
								"bill_vito_register", objectArgs);
						BillVitoRegister vitoVO = new BillVitoRegister();
						vitoVO.setVitoId(vitoId);
						vitoVO.setVitoCode(vitoCode);
						vitoVO.setVitoType(vitoType);
						vitoVO.setLocationCode(billRegVo.getLocationCode());
						vitoVO.setBillNo(billRegVo.getBillNo());
						vitoVO.setCreatedDate(new Date(System
								.currentTimeMillis()));
						vitoVO.setCreatedUserId(SessionHelper
								.getUserId(request));
						vitoVO.setCreatedPostId(SessionHelper
								.getPostId(objectArgs));
						vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
						vitoDaoImpl.create(vitoVO);
						//System.out.println("setBillNo is is :-"+ billRegVo.getBillNo());
					}
					//System.out.println("aud is is :-" + audPostId[i]);
				}
			}
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("audWiseDailyRpt");
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Cheque Advice Vito
	/**
	 * Method For Cheque Advice Vito
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject getChqAdviceDtls(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ChequeVitoDAOImpl vitoDaoImpl = new ChequeVitoDAOImpl(
				ChequeVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String strPrintStatus = null;
			String strReportType = null;
			try {
				strPrintStatus = request.getParameter("PrintReport").toString();
				strReportType = request.getParameter("ReportType").toString();
				//System.out.println("PrintStatus=" + strPrintStatus);
			} catch (Exception Ex) {
			}
			List vitoStringList = new ArrayList();
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List vitoList = reportImpl.getChqAdviceDtls(userId, LocId,
					vitoStringList, strLocationCode);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode("72");
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objRes.setResultValue(objectArgs);
			objectArgs.put("vitoStringList", vitoStringList);
			if (strPrintStatus != null && strPrintStatus.equals("YES") == true
					&& strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails("Cheque Advice Vito", 80);
				headerDetails.add("Sr.No");
				headerWidth.add("-5");
				headerDetails.add("Advice No");
				headerWidth.add("-10");
				headerDetails.add("Cheque No");
				headerWidth.add("+10");
				headerDetails.add("Payee Name");
				headerWidth.add("-35");
				headerDetails.add("Total Amount");
				headerWidth.add("+20");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strPrintStatus != null
					&& strPrintStatus.equals("YES") == true
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails("Cheque Advice Vito", 132);
				headerDetails.add("Sr.No");
				headerWidth.add("-7");
				headerDetails.add("Advice No");
				headerWidth.add("-25");
				headerDetails.add("Cheque No");
				headerWidth.add("+25");
				headerDetails.add("Payee Name");
				headerWidth.add("-45");
				headerDetails.add("Total Amount");
				headerWidth.add("+30");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("chqAdviceRpt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Cheque Advice Vito
	/**
	 * Method For Cheque Advice Vito
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insertChqAdviceDtls(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ChequeVitoDAOImpl vitoDaoImpl = new ChequeVitoDAOImpl(
				ChequeVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String vitoType = request.getParameter("vitoType").toString();

			String chequeNo[] = (String[]) request
					.getParameterValues("chequeNo");
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			//System.out.println("Array Of Cheque No is :-" + chequeNo);
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			for (int i = 0; i < chequeNo.length; i++) {

				Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
						"cheque_vito_register", objectArgs);
				ChequeVitoRegister vitoVO = new ChequeVitoRegister();
				vitoVO.setVitoId(vitoId);
				vitoVO.setVitoCode(vitoCode);
				vitoVO.setVitoType(vitoType);
				vitoVO.setLocationCode(strLocationCode);
				vitoVO.setChequeNo(Long.parseLong(chequeNo[i].toString()));
				vitoVO.setCreatedDate(new Date(System.currentTimeMillis()));
				vitoVO.setCreatedUserId(SessionHelper.getUserId(request));
				vitoVO.setCreatedPostId(SessionHelper.getPostId(objectArgs));
				vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
				vitoDaoImpl.create(vitoVO);
			}
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("chqAdviceRpt");
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Auditor Wise Bill Type Report
	/**
	 * Method for Auditor Wise Bill Type Report
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject getAudWiseBillType(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Long userId = null;
		Long LocId = null;
		String locale = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			locale = SessionHelper.getLocale(request);
			Long langID = SessionHelper.getLangId(objectArgs);
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List auditorList = reportImpl.getAuditorList(userId, LocId, langID,
					strLocationCode);
			List billTypeList = reportImpl.getBillTypeList(userId, LocId,
					locale, strLocationCode, langID);
			objectArgs.put("auditorList", auditorList);
			objectArgs.put("billTypeList", billTypeList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("audWiseBillTypeInp");
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Auditor Wise Bill Type Report
	/**
	 * Method for Auditor Wise Bill Type Report
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject genAudWiseBillTypeVito(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
				DDODetailsVO.class, serv.getSessionFactory());
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		HttpSession session = request.getSession();
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			Long langID = SessionHelper.getLangId(objectArgs);
			session.setAttribute("LocId", LocId);
			session.setAttribute("UserID", userId);
			String audPostId[] = null;
			String strPrintStatus = null;
			String strReportType = null;
			try {
				strPrintStatus = request.getParameter("PrintReport").toString();
				strReportType = request.getParameter("ReportType").toString();
			} catch (Exception Ex) {
			}
			audPostId = (String[]) request.getParameterValues("cmbAuditors");
			if (strPrintStatus == null) {
				session.setAttribute("AuditorCombo", audPostId);
			} else if (strPrintStatus != null) {
				audPostId = (String[]) session.getAttribute("AuditorCombo");
			}

			String vitoType = request.getParameter("vitoType").toString();
			if (strPrintStatus == null) {
				session.setAttribute("VitoType", vitoType);
			} else if (strPrintStatus != null) {
				vitoType = (String) session.getAttribute("VitoType");
			}

			if (audPostId == null
					|| (audPostId.length == 1 && audPostId[0]
							.equalsIgnoreCase("-1"))) {
				List auditorList = reportImpl.getAuditorList(userId, LocId,
						langID, strLocationCode);
				Iterator it = auditorList.iterator();
				int count = 0;
				audPostId = new String[auditorList.size()];
				while (it.hasNext()) {
					ComboValuesVO vo = (ComboValuesVO) it.next();
					audPostId[count++] = vo.getId();
				}
			}
			if (strPrintStatus == null) {
				session.setAttribute("AuditorCombo", audPostId);
			} else if (strPrintStatus != null) {
				audPostId = (String[]) session.getAttribute("AuditorCombo");
			}
			List vitoList = new ArrayList();
			List vitoStringList = new ArrayList();

			Long audPost = null;
			for (int iCounter = 0; iCounter < audPostId.length; iCounter++) {
				//System.out.println("Inside with audpostid" + audPostId.length);
				if (!audPostId[iCounter].equalsIgnoreCase("-1")) {
					try {
						audPost = Long
								.parseLong(audPostId[iCounter].toString());
					} catch (Exception ex) {
						audPost = Long.parseLong("100003");
					}
					Long billType = null;
					try {
						try {
							billType = Long.parseLong(request.getParameter("cmbBillType"));
						} catch (Exception ex) {
						}
						if (strPrintStatus != null) {
							billType = Long.parseLong(session.getAttribute(
									"BillType").toString());
						} else if (strPrintStatus == null) {
							session.setAttribute("BillType", billType);
						}
					} catch (Exception ex) {
						billType = Long.parseLong("-1");
					}
					List audList = reportImpl.genAudWiseBillTypeVito(userId,
							LocId, billType, audPost, vitoType, vitoStringList,
							strLocationCode, langID);
					if (audList != null) {
						vitoList.add(audList);
					}
				}
			}
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objectArgs.put("vitoStringList", vitoStringList);
			//System.out.println("Size of aud  bill type Vito List is:-"+ vitoList.size());
			objectArgs.put("vitoListSize", vitoList.size());
			objRes.setResultValue(objectArgs);
			if (strPrintStatus != null && strPrintStatus.equals("YES") == true
					&& strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails(
						"Auditor Wise Bill Type Vito", 80);
				headerDetails.add("Sr.No");
				headerWidth.add("-5");
				headerDetails.add("Bill Type");
				headerWidth.add("-40");
				headerDetails.add("No. Of Bills");
				headerWidth.add("+10");
				headerDetails.add("Total Amount");
				headerWidth.add("+25");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strPrintStatus != null
					&& strPrintStatus.equals("YES") == true
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails(
						"Auditor Wise Bill Type Vito", 132);
				headerDetails.add("Sr.No");
				headerWidth.add("-12");
				headerDetails.add("Bill Type");
				headerWidth.add("-60");
				headerDetails.add("No. Of Bills");
				headerWidth.add("+20");
				headerDetails.add("Total Amount");
				headerWidth.add("+40");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("audWisebillTypeRpt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Auditor Wise Bill Type Report
	/**
	 * Method for Auditor Wise Bill Type Report
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insAudWiseBillTypeDtls(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String vitoType = request.getParameter("vitoType").toString();
			String audPostId[] = (String[]) request
					.getParameterValues("audUserId");
			Long billType = Long.parseLong(request.getParameter("cmbBillType"));
			//System.out.println("Bill Type Is :-----------" + billType);
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			//System.out.println("aud is is length:-" + audPostId.length);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			for (int i = 0; i < audPostId.length; i++) {
				Long audId = Long.parseLong(audPostId[i]);
				List billList = reportImpl.getBillListByBillType(audId,
						vitoType, billType, LocId, strLocationCode);
				//if (billList != null && billList.size() > 0) {
					Iterator it = billList.iterator();
					while (it.hasNext()) {
						TrnBillRegister billRegVo = (TrnBillRegister) it.next();
						Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
								"bill_vito_register", objectArgs);
						BillVitoRegister vitoVO = new BillVitoRegister();
						vitoVO.setVitoId(vitoId);
						vitoVO.setVitoCode(vitoCode);
						vitoVO.setVitoType(vitoType);
						vitoVO.setLocationCode(billRegVo.getLocationCode());
						vitoVO.setBillNo(billRegVo.getBillNo());
						vitoVO.setCreatedDate(new Date(System
								.currentTimeMillis()));
						vitoVO.setCreatedUserId(SessionHelper
								.getUserId(request));
						vitoVO.setCreatedPostId(SessionHelper
								.getPostId(objectArgs));
						vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
						vitoDaoImpl.create(vitoVO);
						//System.out.println("setBillNo is is :-"+ billRegVo.getBillNo());
					}
					//System.out.println("aud is is :-" + audPostId[i]);
				}
		
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("audWisebillTypeRpt");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in VoucherServiceImpl.getVouchers # \n"+ e);
		}
		return objRes;
	}

	// For Bill Transit Register for audit
	/**
	 * Method For Bill Transit Register for audit
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject genBillTranRegForAudit(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
				DDODetailsVO.class, serv.getSessionFactory());
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		HttpSession session = request.getSession();
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			Long langID = SessionHelper.getLangId(objectArgs);
			String audPostId[] = null;
			audPostId = (String[]) request.getParameterValues("cmbAuditors");
			String strPrintStatus = null;
			String strReportType = null;
			try {
				strPrintStatus = request.getParameter("PrintReport").toString();
				strReportType = request.getParameter("ReportType").toString();
				//System.out.println("PrintStatus=" + strPrintStatus);
			} catch (Exception Ex) {
			}
			if (strPrintStatus == null) {
				session.setAttribute("AuditorList", audPostId);
			} else if (strPrintStatus != null) {
				audPostId = (String[]) session.getAttribute("AuditorList");
			}

			List vitoStringList = new ArrayList();

			String vitoType = request.getParameter("vitoType").toString();
			if (strPrintStatus == null) {
				session.setAttribute("VitoType", vitoType);
			} else if (strPrintStatus != null) {
				vitoType = (String) session.getAttribute("VitoType");
			}

			if (audPostId == null
					|| (audPostId.length == 1 && audPostId[0]
							.equalsIgnoreCase("-1"))) {
				//System.out.println("Inside Catch the auditors:");
				List auditorList = reportImpl.getAuditorList(userId, LocId,
						langID, strLocationCode);
				Iterator it = auditorList.iterator();
				int count = 0;
				audPostId = new String[auditorList.size()];
				while (it.hasNext()) {
					ComboValuesVO vo = (ComboValuesVO) it.next();
					//System.out.println("id is :-" + vo.getId());
					audPostId[count++] = vo.getId();
				}
			}
			//System.out.println("Length Of audUser Id is after catch:-"+ audPostId.length);
			List vitoList = new ArrayList();
			Long audPost = null;
			for (int iCounter = 0; iCounter < audPostId.length; iCounter++) {
				if (!audPostId[iCounter].equalsIgnoreCase("-1")) {
					//System.out.println("Testing by Milind"+ audPostId[iCounter].toString());
					try {
						audPost = Long
								.parseLong(audPostId[iCounter].toString());
					} catch (Exception Ex) {
						audPost = Long.parseLong("100003");
					}
					List audList = reportImpl.genBillTranRegForAudit(userId,
							LocId, audPost, vitoType, vitoStringList,
							strLocationCode, langID);
					if (audList != null) {
						vitoList.add(audList);
					}
				}
			}

			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objectArgs.put("vitoListSize", vitoList.size());
			objectArgs.put("vitoStringList", vitoStringList);
			objRes.setResultValue(objectArgs);
			if (strPrintStatus != null && strPrintStatus.equals("YES") == true
					&& strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails(
						"Bill Transit Register for audit", 80);
				headerDetails.add("Bill Reference Number");
				headerWidth.add("-10");
				headerDetails.add("Bill Control No. ");
				headerWidth.add("-18");
				headerDetails.add("Token No");
				headerWidth.add("-7");
				headerDetails.add("Major Head");
				headerWidth.add("+8");
				headerDetails.add("Bill Type");
				headerWidth.add("+18");
				headerDetails.add("Cardex No.");
				headerWidth.add("+5");
				headerDetails.add(" DDO Name");
				headerWidth.add("+8");
				headerDetails.add("Total Amount");
				headerWidth.add("+12");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strPrintStatus != null
					&& strPrintStatus.equals("YES") == true
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails(
						"Bill Transit Register for audit", 132);
				headerDetails.add("Bill Reference Number");
				headerWidth.add("-15");
				headerDetails.add("Bill Control No. ");
				headerWidth.add("-20");
				headerDetails.add("Token No");
				headerWidth.add("-8");
				headerDetails.add("Major Head");
				headerWidth.add("+8");
				headerDetails.add("Bill Type");
				headerWidth.add("+26");
				headerDetails.add("Cardex No.");
				headerWidth.add("+10");
				headerDetails.add(" DDO Name");
				headerWidth.add("-40");
				headerDetails.add("Total Amount");
				headerWidth.add("+20");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("billTranRegForAudRpt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For bill transit register for audit
	/**
	 * Method For bill transit register for audit
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insBillTranRegForAudit(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			Long locId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			String vitoType = request.getParameter("vitoType").toString();
			String audPostId[] = (String[]) request
					.getParameterValues("audUserId");
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			//System.out.println("aud is is length:-" + audPostId.length);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			for (int i = 0; i < audPostId.length; i++) {
				Long audId = Long.parseLong(audPostId[i]);
				List billList = reportImpl.getBillListByAuditor(audId,
						vitoType, locId, strLocationCode);
				if (billList != null && billList.size() > 0) {
					Iterator it = billList.iterator();
					while (it.hasNext()) {
						TrnBillRegister billRegVo = (TrnBillRegister) it.next();
						Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
								"bill_vito_register", objectArgs);
						BillVitoRegister vitoVO = new BillVitoRegister();
						vitoVO.setVitoId(vitoId);
						vitoVO.setVitoCode(vitoCode);
						vitoVO.setVitoType(vitoType);
						vitoVO.setLocationCode(billRegVo.getLocationCode());
						vitoVO.setBillNo(billRegVo.getBillNo());
						vitoVO.setCreatedDate(new Date(System
								.currentTimeMillis()));
						vitoVO.setCreatedUserId(SessionHelper
								.getUserId(request));
						vitoVO.setCreatedPostId(SessionHelper
								.getPostId(objectArgs));
						vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
						vitoDaoImpl.create(vitoVO);
						//System.out.println("setBillNo is is :-"+ billRegVo.getBillNo());
					}
					//System.out.println("aud is is :-" + audPostId[i]);
				}
			}
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("billTranRegForAudRpt");
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For Bill Transit Register after audit
	 /**
	 * Method For Bill Transit Register after audit
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject genBillTranRegAfterAudit(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
				DDODetailsVO.class, serv.getSessionFactory());
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		HttpSession session = request.getSession();
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			Long langID = SessionHelper.getLangId(objectArgs);

			String audPostId[] = null;
			audPostId = (String[]) request.getParameterValues("cmbAuditors");
			if (audPostId != null) {
				session.setAttribute("audPostID", audPostId);
			}
			String strPrintStatus = null;
			String strReportType = null;
			try {
				strPrintStatus = request.getParameter("PrintReport").toString();
				strReportType = request.getParameter("ReportType").toString();
				//System.out.println("PrintStatus=" + strPrintStatus);
			} catch (Exception Ex) {
			}
			List vitoStringList = new ArrayList();
			String status = null;
			try {
				status = request.getParameter("cmbStatus");
				if (status == null) {
					status = session.getAttribute("Status").toString();
				} else {
					session.setAttribute("Status", status);
				}
			} catch (Exception ex) {
				status = "-1";
			}
			//System.out.println("Status is by shah " + status);
			String vitoType = request.getParameter("vitoType").toString();
			if (audPostId == null) {
				audPostId = (String[]) session.getAttribute("audPostID");
			}
			if (audPostId == null
					|| (audPostId.length == 1 && audPostId[0]
							.equalsIgnoreCase("-1"))) {
				//System.out.println("Inside Catch the auditors:");
				List auditorList = reportImpl.getAuditorList(userId, LocId,
						langID, strLocationCode);
				Iterator it = auditorList.iterator();
				int count = 0;
				audPostId = new String[auditorList.size()];
				while (it.hasNext()) {
					ComboValuesVO vo = (ComboValuesVO) it.next();
					//System.out.println("id is :-" + vo.getId());
					audPostId[count++] = vo.getId();
				}
			}
			//System.out.println("Length Of audUser Id is after catch:-"+ audPostId.length);
			List vitoList = new ArrayList();
			Long audPost = null;
			for (int iCounter = 0; iCounter < audPostId.length; iCounter++) {
				if (!audPostId[iCounter].equalsIgnoreCase("-1")) {
					try {
						audPost = Long
								.parseLong(audPostId[iCounter].toString());
						//System.out.println("Aud post by milind vijay shah"+ audPost);
					} catch (Exception Ex) {
						audPost = Long.parseLong("100003");
					}

					List audList = reportImpl.genBillTranRegAfterAudit(userId,
							LocId, audPost, status, vitoType, vitoStringList,
							strLocationCode, langID);
					if (audList != null) {
						vitoList.add(audList);
					}
				}
			}
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objectArgs.put("vitoListSize", vitoList.size());
			objectArgs.put("vitoStringList", vitoStringList);
			objRes.setResultValue(objectArgs);
			if (strPrintStatus != null && strPrintStatus.equals("YES") == true
					&& strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails(
						"Bill Transit Register after audit", 80);
				headerDetails.add("Bill Control No. ");
				headerWidth.add("-18");
				headerDetails.add("Token No");
				headerWidth.add("-5");
				headerDetails.add("Major Head");
				headerWidth.add("+5");
				headerDetails.add("Bill Type");
				headerWidth.add("+16");
				headerDetails.add("Cardex No.");
				headerWidth.add("+5");
				headerDetails.add(" DDO Name");
				headerWidth.add("-9");
				headerDetails.add("Total Amount");
				headerWidth.add("+12");
				headerDetails.add("Status");
				headerWidth.add("+10");
				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strPrintStatus != null
					&& strPrintStatus.equals("YES") == true
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails(
						"Bill Transit Register after audit", 132);
				headerDetails.add("Bill Control No. ");
				headerWidth.add("-24");
				headerDetails.add("Token No");
				headerWidth.add("-7");
				headerDetails.add("Major Head");
				headerWidth.add("+7");
				headerDetails.add("Bill Type");
				headerWidth.add("+26");
				headerDetails.add("Cardex No.");
				headerWidth.add("+8");
				headerDetails.add(" DDO Name");
				headerWidth.add("-30");
				headerDetails.add("Total Amount");
				headerWidth.add("+20");
				headerDetails.add("Status");
				headerWidth.add("+10");
				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("billTranRegAfterAudRpt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// For bill transit register After audit
	 /**
	 * For bill transit register After audit
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insBillTranRegAfterAudit(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		BillVitoDAOImpl vitoDaoImpl = new BillVitoDAOImpl(
				BillVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String vitoType = request.getParameter("vitoType").toString();
			String audPostId[] = (String[]) request
					.getParameterValues("audUserId");
			String status = request.getParameter("cmbStatus");
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			//System.out.println("aud is is length:-" + audPostId.length);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			for (int i = 0; i < audPostId.length; i++) {
				Long audId = Long.parseLong(audPostId[i]);
				List billList = reportImpl.getBillLstByStatus(audId, vitoType,
						status, LocId, strLocationCode);
				if (billList != null && billList.size() > 0) {
					Iterator it = billList.iterator();
					while (it.hasNext()) {
						TrnBillRegister billRegVo = (TrnBillRegister) it.next();
						Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
								"bill_vito_register", objectArgs);
						BillVitoRegister vitoVO = new BillVitoRegister();
						vitoVO.setVitoId(vitoId);
						vitoVO.setVitoCode(vitoCode);
						vitoVO.setVitoType(vitoType);
						vitoVO.setLocationCode(billRegVo.getLocationCode());
						vitoVO.setBillNo(billRegVo.getBillNo());
						vitoVO.setCreatedDate(new Date(System
								.currentTimeMillis()));
						vitoVO.setCreatedUserId(SessionHelper
								.getUserId(request));
						vitoVO.setCreatedPostId(SessionHelper
								.getPostId(objectArgs));
						vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
						vitoDaoImpl.create(vitoVO);
						//System.out.println("setBillNo is is :-"+ billRegVo.getBillNo());
					}
					//System.out.println("aud is is :-" + audPostId[i]);
				}
			}
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("billTranRegAfterAudRpt");
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}

	// for Cheque Drawn Register Vito
	 /**
	 * Method for Cheque Drawn Register Vito
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject getChqDrawnReg(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ChequeVitoDAOImpl vitoDaoImpl = new ChequeVitoDAOImpl(
				ChequeVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String strPrintStatus = null;
			String strReportType = null;
			try {
				strPrintStatus = request.getParameter("PrintReport").toString();
				strReportType = request.getParameter("ReportType").toString();
				//System.out.println("PrintStatus=" + strPrintStatus);
			} catch (Exception Ex) {
			}
			List vitoStringList = new ArrayList();
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List vitoList = reportImpl.getChqDrawnRegRpt(userId, LocId,
					vitoStringList, strLocationCode);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode("76");
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objectArgs.put("vitoStringList", vitoStringList);
			objRes.setResultValue(objectArgs);
			if (strPrintStatus != null && strPrintStatus.equals("YES") == true
					&& strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails(
						"Cheque Drawn Register Vito", 80);
				headerDetails.add("Sr.No");
				headerWidth.add("-5");
				headerDetails.add("Cheque No");
				headerWidth.add("-10");
				headerDetails.add("Token No");
				headerWidth.add("+9");
				headerDetails.add("Print Date");
				headerWidth.add("+11");
				headerDetails.add("Payee Name");
				headerWidth.add("-15");
				headerDetails.add("Bill Amount");
				headerWidth.add("+15");
				headerDetails.add("Cheque Amount");
				headerWidth.add("+15");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strPrintStatus != null
					&& strPrintStatus.equals("YES") == true
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails(
						"Cheque Drawn Register Vito", 132);
				headerDetails.add("Sr.No");
				headerWidth.add("-7");
				headerDetails.add("Cheque No");
				headerWidth.add("-10");
				headerDetails.add("Token No");
				headerWidth.add("+10");
				headerDetails.add("Print Date");
				headerWidth.add("+10");
				headerDetails.add("Payee Name");
				headerWidth.add("-45");
				headerDetails.add("Bill Amount");
				headerWidth.add("+25");
				headerDetails.add("Cheque Amount");
				headerWidth.add("+25");

				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("chqDrawnRegRpt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in # \n" + e);
		}
		return objRes;
	}

	// for Cheque Drawn Register Vito (Insert)
	 /**
	 * Method for Cheque Drawn Register Vito (Insert)
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insertChqDrawnReg(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ChequeVitoDAOImpl vitoDaoImpl = new ChequeVitoDAOImpl(
				ChequeVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String vitoType = request.getParameter("vitoType").toString();
			String chequeNo[] = (String[]) request
					.getParameterValues("chequeNo");
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			for (int i = 0; i < chequeNo.length; i++) {

				Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
						"cheque_vito_register", objectArgs);
				ChequeVitoRegister vitoVO = new ChequeVitoRegister();
				vitoVO.setVitoId(vitoId);
				vitoVO.setVitoCode(vitoCode);
				vitoVO.setVitoType(vitoType);
				vitoVO.setLocationCode(strLocationCode);
				vitoVO.setChequeNo(Long.parseLong(chequeNo[i].toString()));
				vitoVO.setCreatedDate(new Date(System.currentTimeMillis()));
				vitoVO.setCreatedUserId(SessionHelper.getUserId(request));
				vitoVO.setCreatedPostId(SessionHelper.getPostId(objectArgs));
				vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
				vitoDaoImpl.create(vitoVO);
			}
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("chqDrawnRegRpt");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in  # \n" + e);
		}
		return objRes;
	}

	// Cheque Delivery Account Report
	 /**
	 * Method to getting Major Head detail
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject getMajorHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			Long langID = SessionHelper.getLangId(objectArgs);
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List majorHeadList = reportImpl.getExpMajorHead(userId, LocId,
					strLocationCode, langID);
			objectArgs.put("majorHeadList", majorHeadList);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("chqDelAccRptInp");
		} catch (Exception e) {
			e.printStackTrace();
			logger
					.error("Exception occured in VoucherServiceImpl.getVouchers # \n"
							+ e);
		}
		return objRes;
	}
	 /**
	 * Method to getting the Del Access Detail
	 * 
	 * @param  Map : objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	
	public ResultObject getDelAccDtls(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpSession session = request.getSession();
		Long userId = null;
		Long LocId = null;
		String strHeaderDetails = null;
		List vitoStringList = new ArrayList();
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			//System.out.println("Loc id= " + LocId);
			//System.out.println("User id= " + userId);
			session.setAttribute("LocId", LocId);
			session.setAttribute("UserID", userId);
			String strPrintStatus = null;
			String strReportType = null;
			try {
				strPrintStatus = request.getParameter("PrintReport").toString();
				strReportType = request.getParameter("ReportType").toString();
				//System.out.println("PrintStatus=" + strPrintStatus);
			} catch (Exception Ex) {
			}
			String majorhead[] = null;
			majorhead = (String[]) request.getParameterValues("cmblist");
			//System.out.println("Major head by shah :" + majorhead);
			if (majorhead != null) {
				session.setAttribute("majorhead", majorhead);
				//System.out.println("I am in session");
			}

			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List vitoList = null;
			if (majorhead == null) {
				majorhead = (String[]) session.getAttribute("majorhead");
				session.removeAttribute("majorhead");
				//System.out.println("I am getting back");

			}
			vitoList = reportImpl.getDeliAccDtls(userId, LocId, majorhead,
					vitoStringList, strLocationCode);
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoStringList", vitoStringList);
			objRes.setResultValue(objectArgs);
			if (strPrintStatus != null && strPrintStatus.equals("YES") == true
					&& strReportType.equals("80") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "80";
				strHeaderDetails = setHeaderDetails(
						"Cheque Delivery Account Vito", 80);
				headerDetails.add("Sr.No");
				headerWidth.add("-8");
				headerDetails.add("Major Head");
				headerWidth.add("-12");
				headerDetails.add("No.Of Voucher");
				headerWidth.add("+20");
				headerDetails.add("No.Of Cheques");
				headerWidth.add("+20");
				headerDetails.add("Total Amout");
				headerWidth.add("+20");
				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else if (strPrintStatus != null
					&& strPrintStatus.equals("YES") == true
					&& strReportType.equals("132") == true) {
				List headerDetails = new ArrayList();
				List headerWidth = new ArrayList();
				String strLineSize = "132";
				strHeaderDetails = setHeaderDetails(
						"Cheque Delivery Account Vito", 132);
				headerDetails.add("Sr.No");
				headerWidth.add("-10");
				headerDetails.add("Major Head");
				headerWidth.add("-22");
				headerDetails.add("No.Of Voucher");
				headerWidth.add("+30");
				headerDetails.add("No.Of Cheques");
				headerWidth.add("+30");
				headerDetails.add("Total Amout");
				headerWidth.add("+40");
				objectArgs.put("HeaderDetails", headerDetails);
				objectArgs.put("HeaderWidth", headerWidth);
				objectArgs.put("LineSize", strLineSize);
				objectArgs.put("reportHeader", strHeaderDetails);
				objRes.setViewName("printAuditorWiseBillTypeRpt");
			} else {
				objRes.setViewName("chqDeliveryaccDtls");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in # \n" + e);
		}
		return objRes;
	}
	 /**
	 * Method to getting the Track Details By Major Head
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject getTrackDtlsByMjrHead(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ChequeVitoDAOImpl vitoDaoImpl = new ChequeVitoDAOImpl(
				ChequeVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			Long langID = SessionHelper.getLangId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String majorhead = (String) request.getParameter("majorhead");
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			List vitoList = reportImpl.getMajorDtls(userId, LocId, majorhead,
					strLocationCode, langID);
			Long vitoCode = vitoDaoImpl.getMaxVitoCode("77");
			objectArgs.put("vitoList", vitoList);
			objectArgs.put("vitoCode", vitoCode);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("detailsMajorhead");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in # \n" + e);
		}
		return objRes;
	}
	 /**
	 * Method to insert Chque Del Acc Register
	 * 
	 * @param  Map objectArgs
	 * @return ResultObject
	 * @author vidhya
	 */
	public ResultObject insertChqDelAccReg(Map objectArgs) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ChequeVitoDAOImpl vitoDaoImpl = new ChequeVitoDAOImpl(
				ChequeVitoRegister.class, serv.getSessionFactory());
		Long userId = null;
		Long LocId = null;
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}
			LocId = SessionHelper.getLocationId(objectArgs);
			String strLocationCode = SessionHelper.getLocationCode(objectArgs);
			userId = SessionHelper.getUserId(request);
			String vitoType = request.getParameter("vitoType").toString();
			String chequeNo[] = (String[]) request
					.getParameterValues("chequeNo");
			ReportQueryDAOImpl reportImpl = new ReportQueryDAOImpl(
					DDODetailsVO.class, serv.getSessionFactory());
			Long vitoCode = vitoDaoImpl.getMaxVitoCode(vitoType);
			for (int i = 0; i < chequeNo.length; i++) {

				Long vitoId = BptmCommonServiceImpl.getNextSeqNum(
						"cheque_vito_register", objectArgs);
				ChequeVitoRegister vitoVO = new ChequeVitoRegister();
				vitoVO.setVitoId(vitoId);
				vitoVO.setVitoCode(vitoCode);
				vitoVO.setVitoType(vitoType);
				vitoVO.setLocationCode(strLocationCode);
				vitoVO.setChequeNo(Long.parseLong(chequeNo[i].toString()));
				vitoVO.setCreatedDate(new Date(System.currentTimeMillis()));
				vitoVO.setCreatedUserId(SessionHelper.getUserId(request));
				vitoVO.setCreatedPostId(SessionHelper.getPostId(objectArgs));
				vitoVO.setDbId(SessionHelper.getDbId(objectArgs));
				vitoDaoImpl.create(vitoVO);
			}
			String msg = null;
			msg = "Vito Genereted Successfully";
			objectArgs.put("statusMsg", msg);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("detailsMajorhead");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in  # \n" + e);
		}
		return objRes;
	}
	 /**
	 * Method to getting the Header Details
	 * 
	 * @param  String strTitle, int width
	 * @return String
	 * @author vidhya
	 */
	private String setHeaderDetails(String strTitle, int width) {
		String strFinalString = "";
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("d/M/y");
		Date currentTime_1 = new Date();
		String dateString = "Date: " + formatter.format(currentTime_1);

		String strSpace = "";
		int space = 0;
		if (width == 80) {
			space = 20;
		} else {
			space = 45;
		}
		for (int count = 0; count < space; count++) {
			strSpace = strSpace + " ";
		}
		strFinalString = dateString + " " + strSpace + " " + strTitle;
		return strFinalString;
	}
	/*
	 * //Cheque Delivery Account Report public ResultObject genChqDelAccVito(Map
	 * objectArgs) { ResultObject objRes = new
	 * ResultObject(ErrorConstants.SUCCESS, "FAIL"); HttpServletRequest request =
	 * (HttpServletRequest) objectArgs.get("requestObj"); ServiceLocator serv =
	 * (ServiceLocator)objectArgs.get("serviceLocator"); Long userId=null; Long
	 * LocId=null; try { if (objRes==null || objectArgs == null ) {
	 * objRes.setResultCode(-1); return objRes; }
	 * LocId=SessionHelper.getLocationId(request);
	 * userId=SessionHelper.getUserId(request);
	 * 
	 * ReportQueryDAOImpl reportImpl = new
	 * ReportQueryDAOImpl(DDODetailsVO.class,serv.getSessionFactory()); List
	 * vitoList = reportImpl.genChqDelAccVito(userId, LocId);
	 * objectArgs.put("vitoList",vitoList); objRes.setResultValue(objectArgs);
	 * objRes.setViewName("chqAdviceRpt"); } catch(Exception e) {
	 * e.printStackTrace(); logger.error("Exception occured in
	 * VoucherServiceImpl.getVouchers # \n"+e); } return objRes; }
	 * 
	 * public static void main(String args[]) { //String dateTime = new
	 * SimpleDateFormat("Month").format(Calendar.getInstance().getTime().getMonth());
	 * ////System.out.println("dateTime"+dateTime); ////System.out.println("dateTime
	 * 9999999999-------"+Calendar.getInstance().getTime().getMonth()); }
	 */
}
