/**
 * 
 */
package com.tcs.sgv.billproc.audit.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.billproc.audit.dao.AuditorObjectionDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.RltBillObjection;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;

/** AuditorObjectionVOGeneratorImpl
 *  This class is used to generate the VO for objections that are raised by user.
 *  User can raise the objections when bill is open in editable mode, as per rights given to him.
 *   
 * 	Date of Creation : 10th July 2007
 *  Author : Hiral Shah 
 *  
 *  Revision History 
 *  =====================
 *   Hiral    23-Oct-2007   For making changes for code formating
 *
 */
public class AuditorObjectionVOGeneratorImpl extends ServiceImpl implements
		VOGeneratorService {
	Log logger = LogFactory.getLog(getClass());
	ResourceBundle bundleConst = ResourceBundle
	.getBundle("resources/billproc/BillprocConstants");

	/**
	 * Method to generate Value Object for 'rlt_bill_objection'
	 * 
	 * @param Map :
	 *            p_objServiceArgs
	 * 
	 * @return ResultObject
	 */
	public ResultObject generateMap(Map p_objServiceArgs) {
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);

		try {
			int i;
			logger.info("Inside AuditorObjectionVOGeneratorImpl");
			ServiceLocator serv = (ServiceLocator) p_objServiceArgs
					.get("serviceLocator");
			AuditorObjectionDAOImpl lObjAdtObjDAOImpl = new AuditorObjectionDAOImpl(
					RltBillObjection.class, serv.getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) p_objServiceArgs
					.get("requestObj");

			Long DbId = Long.parseLong(SessionHelper.getDbId(p_objServiceArgs)
					.toString());
			String lStrLocCode = SessionHelper
					.getLocationCode(p_objServiceArgs);
			Long UserId = Long.parseLong(SessionHelper.getUserId(request)
					.toString());
			Long postId = Long.parseLong(SessionHelper.getPostId(
					p_objServiceArgs).toString());
			Long lLngBillNo = null;

			if (request.getParameter("BillNo") != null) {
				lLngBillNo = Long.parseLong(request.getParameter("BillNo"));
			} else if (p_objServiceArgs.get("BillNo") != null) {
				lLngBillNo = Long.parseLong(String.valueOf(p_objServiceArgs
						.get("BillNo")));
			}

			logger.info("Value of Bill No :: " + lLngBillNo);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			java.util.Date lDtCurDate = new java.util.Date();
			logger.info("Current Date Time : " + dateFormat.format(lDtCurDate));

			List resList = new ArrayList();

			String lStrObjDtls = request.getParameter("chkbox");
			logger.info("Inside auditor objection VOGEN, LIST MAP SIZE : "
					+ lStrObjDtls);

			if (lStrObjDtls != null && lStrObjDtls.length() > 0) {
				String[] objDetails = lStrObjDtls.split("~");
				//System.out.println("Value of String after splitting : "						+ objDetails);
				//System.out.println("Length of String after splitting : "+ objDetails.length);

				if ((objDetails != null) && (objDetails.length > 0)) {
					for (i = 1; i < objDetails.length; i++) {
						logger
								.info("OBJECITON DETAILSFASDF : "
										+ objDetails[i]);
						Integer lineItemNo = lObjAdtObjDAOImpl.getNextLineItem(
								lLngBillNo.toString(), UserId.toString());
						String objectionId = objDetails[i];
						RltBillObjection lObjRltBillObjVO = new RltBillObjection();
						lObjRltBillObjVO.setObjectionCode(objectionId);
						lObjRltBillObjVO.setBillNo(lLngBillNo);
						lObjRltBillObjVO.setLineItemNo(Long
								.parseLong(lineItemNo.toString()));
						lObjRltBillObjVO.setUserId(UserId);
						lObjRltBillObjVO.setPostId(postId);
						lObjRltBillObjVO.setCreatedUserId(UserId);
						lObjRltBillObjVO.setCreatedPostId(postId);
						lObjRltBillObjVO.setCreatedDate(lDtCurDate);
						lObjRltBillObjVO.setUpdatedUserId(UserId);
						lObjRltBillObjVO.setUpdatedPostId(postId);
						lObjRltBillObjVO.setUpdatedDate(lDtCurDate);
						lObjRltBillObjVO.setLocationCode(lStrLocCode);
						lObjRltBillObjVO.setDbId(DbId);
						lObjRltBillObjVO.setObjFlag(bundleConst.getString("CMN.MyObjections"));

						resList.add(lObjRltBillObjVO);
					}

					p_objServiceArgs.put("auditObjList", resList);
				}
			}
			p_objServiceArgs.put("result", p_objServiceArgs);
			retObj.setResultValue(p_objServiceArgs);
		} catch (Exception e) {
			retObj.setResultValue(null);
			retObj.setThrowable(e);
			retObj.setResultCode(ErrorConstants.ERROR);
			retObj.setViewName("errorPage");
			e.printStackTrace();
			logger.error(" Error in generateMap  " + e, e);
		}
		return retObj;
	}
}