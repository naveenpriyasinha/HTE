package com.tcs.sgv.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.PostConfigurationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.EmpPostVO;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * @author 157045
 * @see This is used to get auditor details for all TOs according to configuration 
 * 
 * Date of Creation : 2nd July 2007
 * 
 * Revision History 
 * =================
 * Vidhya Mashru  23-Oct-2007  To make changes for code formatting
 * Hiral Shah	  25-Oct-2007  Changes for filtering auditor on bill type.
 *  	
 */

public class PostConfigServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle lObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * This method will return auditor list and selected auditor based on majorhead, bill type, 
	 * cardex number, department code and go ngo
	 * @param inputMap
	 * @return
	 */
	public ResultObject getAuditorNames(Map inputMap) {
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			HttpServletRequest request = (HttpServletRequest) inputMap
					.get("requestObj");
/*			  Request Parameter names changed	*/			
			logger.info("Value of Request in PostConfigServiceImpl : " +request);
			String lStrMjrHead = (String) request.getParameter("cmbMajorHead");
			String lStrGoNgo = (String) request.getParameter("cmbGONGO");
			String lStrBillType = (String) request.getParameter("cmbBillType");			
			String lStrCardex = (String) request.getParameter("txtCardexNo");
			String lStrOfficeCode = (String) request.getParameter("txtDepartment");
/*	Ends :   Request Parameter names changed	*/
			logger.info(" ^6666666666666666 ^ Major Head " + lStrMjrHead);
			logger.info(" ^6 ^ GO NGO  " + lStrGoNgo);
			logger.info(" ^6666666666666666 ^ lStrBillType " + lStrBillType);
			logger.info(" ^6666666666666666 ^ lStrCardex " + lStrCardex);
			logger.info(" ^6^6^^^66^6^66^^^^ ^ OFFICE CODE :  "
					+ lStrOfficeCode);
			
			Long loc_id = SessionHelper.getLocationId(inputMap);
			String locationCode = SessionHelper.getLocationCode(inputMap);			

/*	Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */
			String branchCode = null;
			if(lStrBillType != null && (lStrBillType.equals("12") || lStrBillType.equals("23") || lStrBillType.equals("24")))				
				branchCode = lObjRsrcBndle.getString("CMN.BillTypeBranchId"); 	// Branch Id : 100021
			else
				branchCode = lObjRsrcBndle.getString("CMN.BranchId");			// Branch Id : 100004
/*	Ends : Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */
			
			String officeCode = locationCode;
			logger.info(" Offie is ...................... " + officeCode);
			logger.info(" Branch Code  is ...................... " + branchCode);
			// long branchId = 100004;
			ServiceLocator serv = (ServiceLocator) inputMap
					.get("serviceLocator");
			PostConfigurationDAOImpl postConfigDAO = new PostConfigurationDAOImpl(
					ConfigAudSelection.class, serv.getSessionFactory());
			if (lStrMjrHead != null) {
				inputMap.put("majorHead", lStrMjrHead);
			}
			if (lStrGoNgo != null) {
				inputMap.put("goNgo", lStrGoNgo);
			}
			if (lStrBillType != null) {
				inputMap.put("billType", lStrBillType);
			}
			if (lStrCardex != null) {
				inputMap.put("cardexNo", lStrCardex);
			}

			List audList = postConfigDAO.getEmpsList(officeCode, branchCode);
			logger
					.info("\n%%%%%%%%%%  Auditor list -------  "
							+ audList.size());
			EmpPostVO empPostVO = postConfigDAO.getSelectedEmp(officeCode,
					branchCode, inputMap);

			String lStrAjaxString = new AjaxXmlBuilder().addItems(audList,
					"fullName", "postId").toString();

			if (empPostVO != null) {
				logger.info("\n%%%%%%%%%%  Auditor Selected -------  "
						+ empPostVO.getPostId());
				logger.info("\n%%%%%%%%%%  Auditor Selected Name-------  "
						+ empPostVO.getFullName());
				int j = 1;
				List audLst = new ArrayList();
				for (int i = 0; i < audList.size(); i++) {
					EmpPostVO empPostVO1 = (EmpPostVO) audList.get(i);
					logger.info("Before, Post id from empPostVO1 LIst :: ["
							+ empPostVO1.getPostId()
							+ "], Post id from empPostVO2Selected :: ["
							+ empPostVO.getPostId() + "].");
					if (empPostVO1.getPostId() == empPostVO.getPostId()) {
						logger.info("Inside if of equal postID");
						audLst.add(0, empPostVO1);
						audList.remove(i);
						break;
					}

				}
				if (audLst != null && audLst.size() > 0) {
					for (int i = 0; i < audList.size(); i++) {
						audLst.add(j, audList.get(i));
						j++;
					}
				} else {
					for (int i = 0; i < audList.size(); i++) {
						audLst.add(audList.get(i));
					}
				}

				logger
						.info("\n%%%%%%%%%%  Auditor list after shuffling-------  "
								+ audLst.size());
				lStrAjaxString = new AjaxXmlBuilder().addItems(audLst,
						"fullName", "postId").toString();
			} else {
				List audLst = new ArrayList();
				EmpPostVO empPostVO1 = new EmpPostVO();
				empPostVO1.setEmpFname("--------");
				empPostVO1.setEmpMname("--Select---------");
				empPostVO1.setEmpId((long) -1);
				empPostVO1.setEmpLname("----");
				empPostVO1.setEmpPrefix("---");
				empPostVO1.setFullName("-----Select-----");
				empPostVO1.setPostId((long) -1);
				empPostVO1.setUserId((long) -1);

				audLst.add(0, empPostVO1);
				for (int i = 0; i < audList.size(); i++) {
					audLst.add(i + 1, (EmpPostVO) audList.get(i));
				}

				lStrAjaxString = new AjaxXmlBuilder().addItems(audLst,
						"fullName", "postId").toString();
			}
			// inputMap.put("AudList", audList);
			inputMap.put("ajaxKey", lStrAjaxString);
			inputMap.put("AudSelect", empPostVO);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in getAuditorNames : " + e,e);
		}
		return objRes;
	}
}
