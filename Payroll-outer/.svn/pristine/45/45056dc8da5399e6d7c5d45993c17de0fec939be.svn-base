/**
 * 
 */
package com.tcs.sgv.billproc.counter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.counter.valueobject.NewBillVO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.PostConfigurationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.DDOInfoServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;


/** NewBillVOGen
 *  This class generates VO for NewBillVO based on the values provided by end-user.
 *  This VO is then used to populate the physical bill UI 
 *  after end-user has inwarded the bill from 'Save and New' Utility.
 *   
 * 	Date of Creation : 16th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *  Hiral Shah    23-Oct-2007   For making changes for code formating
 *	Hiral Shah    26-Oct-2007	For getting List of Auditors
 */
public class NewBillVOGen extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	ResourceBundle lObjRsrcBndle = ResourceBundle
			.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to generate VO for 'trn_bill_register' table.
	 * 
	 * @param :
	 *            Map : p_objServiceArgs
	 * 
	 * @return : ResultObject : retObj
	 */
	public NewBillVO voGenerator(Map p_objServiceArgs) {
		logger
				.info("------------------Inside NewBillVOGeneratorImpl------------------");
		String billCode = null;
		int i = 0;
		NewBillVO lObjNewBillVO = new NewBillVO();
		ServiceLocator serv = (ServiceLocator) p_objServiceArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) p_objServiceArgs
				.get("requestObj");
		
		PostConfigurationDAOImpl lObjPostConfigDao = new PostConfigurationDAOImpl(ConfigAudSelection.class,serv.getSessionFactory());
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(
				TrnBillRegister.class, serv.getSessionFactory());
		DDOInfoServiceImpl lObjDDOInfoSrvcImpl = new DDOInfoServiceImpl();

		HttpSession session = request.getSession();
		Long lLngLangId = SessionHelper.getLangId(p_objServiceArgs);
		Long lLngDBId = SessionHelper.getDbId(p_objServiceArgs);
		String lStrLocCode = SessionHelper.getLocationCode(p_objServiceArgs);
		List lLstDept = null;

		logger.info("Value of request in New Bill VO ::: "
				+ request.getParameterMap().toString());
		try {
			String lStrDept = request.getParameter("txtDepartment") != null ? request
					.getParameter("txtDepartment")
					: null;
			String lStrGoNgo = request.getParameter("cmbGONGO") != null ? request
					.getParameter("cmbGONGO")
					: null;
			String lStrSbMjrHd = request.getParameter("cmbSubMajorHead") != null ? request
					.getParameter("cmbSubMajorHead")
					: null;

			String lStrMnrHd = request.getParameter("cmbMinorHead") != null ? request
					.getParameter("cmbMinorHead")
					: null;
			String lStrSubHd = request.getParameter("cmbSubHead") != null ? request
					.getParameter("cmbSubHead")
					: null;
			String lStrDetailHead = request.getParameter("cmbDetailHead") != null ? request
					.getParameter("cmbDetailHead")
					: null;

			String lStrBudType = request.getParameter("cmbPlan") != null ? request
					.getParameter("cmbPlan")
					: null;
			String lStrBudTypeDesc = request.getParameter("txtBudDesc") != null ? request
					.getParameter("txtBudDesc")
					: null;
			String lStrGrantAmt = request.getParameter("txtGrantAmt") != null ? request
					.getParameter("txtGrantAmt")
					: null;
			String lStrPrevBillNo = request.getParameter("txtPrevBillNo") != null ? request
					.getParameter("txtPrevBillNo")
					: null;

			String lStrSchemeCode = request.getParameter("txtSchemeCode") != null ? request
					.getParameter("txtSchemeCode")
					: null;
			String lStrRemarks = request.getParameter("txtareaRemarks") != null ? request
					.getParameter("txtareaRemarks")
					: null;
			String lStrAudPost = request.getParameter("cmbAuditorName") != null ? request
					.getParameter("cmbAuditorName")
					: null;

			String lStrCardexNo = request.getParameter("txtCardexNo") != null ? request
					.getParameter("txtCardexNo")
					: null;
			String lStrDdoNo = request.getParameter("txtDDONo") != null ? request
					.getParameter("txtDDONo")
					: null;
			String lStrDdoCode = request.getParameter("DDOCode") != null ? request
					.getParameter("DDOCode")
					: null;
			String lStrDdoName = request.getParameter("txtDDOName") != null ? request
					.getParameter("txtDDOName")
					: null;

			String lStrBillDate = request.getParameter("txtBillDate") != null ? request
					.getParameter("txtBillDate")
					: null;
			String lStrBillType = request.getParameter("cmbBillType") != null ? request
					.getParameter("cmbBillType")
					: null;
			String lStrTcctgry = request.getParameter("cmbTCCtgry") != null ? request
					.getParameter("cmbTCCtgry")
					: null;

			String lStrExempted = request.getParameter("radExempted") != null ? request
					.getParameter("radExempted")
					: null;
			String lStrDemandNo = request.getParameter("cmbDemand") != null ? request
					.getParameter("cmbDemand")
					: null;
			String lStrMajorHd = request.getParameter("cmbMajorHead") != null ? request
					.getParameter("cmbMajorHead")
					: null;

			lObjNewBillVO.setCardexNo(lStrCardexNo);
			lObjNewBillVO.setDdoNo(lStrDdoNo);
			lObjNewBillVO.setDdoCode(lStrDdoCode);
			lObjNewBillVO.setDdoName(lStrDdoName);
			lObjNewBillVO.setBudDetailHd(lStrDetailHead);
			lObjNewBillVO.setPrevBillNo(lStrPrevBillNo);

			//System.out.println("Value of Department in NewBill VO : "				+ lStrDept);

			if (lStrDept != null)
				lObjNewBillVO.setDepartment(lStrDept);

			lObjNewBillVO.setBillDate(lStrBillDate);
			lObjNewBillVO.setBillType(lStrBillType);

			lObjNewBillVO.setBillGrossAmount("0");
			lObjNewBillVO.setBillNetAmount("0");
			lObjNewBillVO.setGoNgo(lStrGoNgo);

			lObjNewBillVO.setTcBill(lStrTcctgry);
			lObjNewBillVO.setExempted(lStrExempted);
			if (request.getParameter("radExempted").equalsIgnoreCase(
					lObjRsrcBndle.getString("CMN.Yes"))) {
				lObjNewBillVO.setBillCode(request.getParameter("cmbBillCode"));
			}

			lObjNewBillVO.setDmndNo(lStrDemandNo);
			lObjNewBillVO.setBudmjrHd(lStrMajorHd);
			lObjNewBillVO.setBudSubMjrHd(lStrSbMjrHd);
			lObjNewBillVO.setBudMinHd(lStrMnrHd);
			lObjNewBillVO.setBudSubHd(lStrSubHd);

			lObjNewBillVO.setBudType(lStrBudType);
			lObjNewBillVO.setBudTypeDesc(lStrBudTypeDesc);
			lObjNewBillVO.setGrantAmt(lStrGrantAmt);
			lObjNewBillVO.setSchemeCode(lStrSchemeCode);
			lObjNewBillVO.setDeptId(lStrDept);

			String billCtgry = lObjCmnSrvcDAOImpl.getLookUpText((lObjNewBillVO
					.getTcBill()), lLngLangId);
			if (billCtgry.equalsIgnoreCase("TC")) {
				Integer lIntRowCount = StringUtility.getParameter("rowCount",
						request) != null ? Integer.parseInt(StringUtility
						.getParameter("rowCount", request)) : null;
				String[] lStrChallanNo = new String[lIntRowCount];
				String[] lStrChallanDate = new String[lIntRowCount];
				String[] lStrChallanMjrHd = new String[lIntRowCount];
				String[] lStrChallanAmt = new String[lIntRowCount];

				List lListTrnRcptVO = (List) p_objServiceArgs
						.get("ReceiptDetailsVO");
				logger.info("Value of lListTrnRcptVO inside newBillVOGEN : "
						+ lListTrnRcptVO);
				if (lListTrnRcptVO != null) {
					logger.info("Size of lListTrnRcptVO inside newBillVOGEN : "
							+ lListTrnRcptVO.size());
					lObjNewBillVO.setListReceipt(lListTrnRcptVO);
				}
				/*
				 * for(int k=0;k<lListTrnRcptVO.size();k++) { lStrChallanNo[k] =
				 * request.getParameter("txtChallanNo"+(k+1))!=null ?
				 * request.getParameter("txtChallanNo"+(k+1)):null;
				 * lStrChallanDate[k] =
				 * request.getParameter("txtChallanDate"+(k+1))!=null ?
				 * request.getParameter("txtChallanDate"+(k+1)):null;
				 * lStrChallanMjrHd[k] =
				 * request.getParameter("txtChallanMjrHead"+(k+1))!=null ?
				 * request.getParameter("txtChallanMjrHead"+(k+1)):null;
				 * lStrChallanAmt[k] =
				 * request.getParameter("txtChallanAmt"+(k+1))!=null ?
				 * request.getParameter("txtChallanAmt"+(k+1)):null;
				 * 
				 * //System.out.println("Challan Number " +i +" : "
				 * +lStrChallanNo[k]); //System.out.println("Challan Date " +i +" : "
				 * +lStrChallanDate[k]); //System.out.println("Challan Major Head "
				 * +i +" : " +lStrChallanMjrHd[k]); //System.out.println("Challan
				 * Amount " +i +" : " +lStrChallanAmt[k]);
				 * 
				 * //System.out.println("Value of Challan Amount in New Bill VO : "
				 * +request.getParameter("txtChallanAmt")); }
				 * lObjNewBillVO.setChallanNo(lStrChallanNo);
				 * lObjNewBillVO.setChallanDate(lStrChallanDate);
				 * lObjNewBillVO.setChallanMjrHd(lStrChallanMjrHd);
				 * lObjNewBillVO.setChallanAmt(lStrChallanAmt);
				 */
			}
			lLstDept = lObjDDOInfoSrvcImpl.getBillOfficeForDDO(serv, request
					.getParameter("DDOCode").toString(), lLngLangId, lLngDBId);
			CommonVO lCmnVo = new CommonVO();
			lCmnVo.setCommonDesc("--Select--");
			lCmnVo.setCommonId("-1");
			List lLstTemp = new ArrayList();
			lLstTemp.addAll(lLstDept);
			lLstDept.add(0, lCmnVo);
			for (i = 1; i < lLstTemp.size() - 1; i++) {
				lLstDept.add(i, lLstTemp.get(i - 1));
			}

			if (lLstDept != null && lLstDept.size() > 0)
				p_objServiceArgs.put("dept", lLstDept);

			logger.info("Value of Auditor Post iID in VOGEN :  " + lStrAudPost);
			if (lStrAudPost != null) {
				if (Integer.parseInt(lStrAudPost) != -1) {
					lObjNewBillVO.setAuditorPostId(lStrAudPost);
				}
			}
			logger.info("Value of Auditor Post ID from NewBillVO in VOGEN :  "
					+ lObjNewBillVO.getAuditorPostId());
			lObjNewBillVO.setRemarks(lStrRemarks);
			if (session.getAttribute("AttachID") != null
					&& session.getAttribute("AttachID").toString().length() > 0) {
				lObjNewBillVO.setAttachId(session.getAttribute("AttachID")
						.toString());
				logger
						.info("??????????????Value of Attachment ID in new Bill VO: "
								+ lObjNewBillVO.getAttachId());
				session.removeAttribute("AttachID");
			}
			logger
					.info("=====================Value object of New Bill VO set :: "
							+ lObjNewBillVO.toString());
			p_objServiceArgs.put("NewBillVO", lObjNewBillVO);
			ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
			retObj.setResultValue(p_objServiceArgs);
/*	Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */
			String branchCode = null;
			if(lStrBillType.equals("12") || lStrBillType.equals("23") || lStrBillType.equals("24"))				
				branchCode = lObjRsrcBndle.getString("CMN.BillTypeBranchId"); 	// Branch Id : 100021
			else
				branchCode = lObjRsrcBndle.getString("CMN.BranchId");			// Branch Id : 100004
			List lListAudList = lObjPostConfigDao.getEmpsList(lStrLocCode, branchCode);
			logger.info("List in NewBill VO for getting all Auditorsssssssssssss : " +lListAudList);
			lObjNewBillVO.setAudList(lListAudList);
/*	Ends : Changes for filtering auditor on bill type(Subject Id : 12,23,24)  */

			
//			lObjPostConfigSrc.getAuditorNames(p_objServiceArgs);
			retObj.setViewName("cntrInwPhyBills");
		} catch (Exception e) {
			logger.error(" Error in VOGenerator " + e, e);
		}
		return lObjNewBillVO;
	}
}