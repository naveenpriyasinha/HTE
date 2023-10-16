/**
 * 
 */
package com.tcs.sgv.eis.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.wsdl.ServiceImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsDesignation;
import com.tcs.sgv.eis.dao.PayrollDesignationDAOImpl;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;

/**
 * @author 379674
 *
 */
public class PayrollDesignationMstServiceImpl  extends com.tcs.sgv.core.service.ServiceImpl{
	
	Log logger = LogFactory.getLog( getClass() );
	
	/**
	 * author@manish khunt
	 * To keep MstPayrollDesignation and MstDcpsDesignation table in sinc. 
	 * @param objectArgs
	 * @return
	 */
	public ResultObject insertPayrollDesignation(Map objectArgs) {
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		
		try{
		    PayrollDesignationDAOImpl payrollDesignationDAOImpl = new PayrollDesignationDAOImpl(MstPayrollDesignationMst.class,serv.getSessionFactory());
			
		     MstDcpsDesignation mstDcpsDesignation = objectArgs.get("mstDcpsDesignation")!= null?(MstDcpsDesignation)objectArgs.get("mstDcpsDesignation"):null;
		    if(mstDcpsDesignation != null)
		    {
			MstPayrollDesignationMst mstPayrollDesignationMst = new MstPayrollDesignationMst();
			long payrollDesigPk = IDGenerateDelegate.getNextId("MST_PAYROLL_DESIGNATION", objectArgs);
			mstPayrollDesignationMst.setDesigId(payrollDesigPk);
			mstPayrollDesignationMst.setCadreTypeId(mstDcpsDesignation.getCadreTypeId());
			mstPayrollDesignationMst.setPayComsnId(mstDcpsDesignation.getPayComsnId());
			mstPayrollDesignationMst.setCreatedDate(mstDcpsDesignation.getCreatedDate());
			mstPayrollDesignationMst.setCreatedPostId(mstDcpsDesignation.getCreatedPostId());
			mstPayrollDesignationMst.setCreatedUserId(mstDcpsDesignation.getCreatedUserId());
			mstPayrollDesignationMst.setDbId(mstDcpsDesignation.getDbId());
			mstPayrollDesignationMst.setDesigDesc(mstDcpsDesignation.getDesigDesc());
			mstPayrollDesignationMst.setOrgDesignationId(mstDcpsDesignation.getOrgDesignationId());
			mstPayrollDesignationMst.setFieldDeptId(mstDcpsDesignation.getFieldDeptId());
			mstPayrollDesignationMst.setLangId(mstDcpsDesignation.getLangId());
			mstPayrollDesignationMst.setLocationCode(mstDcpsDesignation.getLocationCode());
			mstPayrollDesignationMst.setUpdatedDate(mstDcpsDesignation.getUpdatedDate());
			mstPayrollDesignationMst.setUpdatedPostId(mstDcpsDesignation.getUpdatedPostId());
			mstPayrollDesignationMst.setUpdatedUserId(mstDcpsDesignation.getUpdatedUserId());
			mstPayrollDesignationMst.setDesigCode(mstDcpsDesignation.getDesigCode());
			mstPayrollDesignationMst.setDsgnCode(mstDcpsDesignation.getDesigCode()); //most important frm inner's perspective
			
			long count = payrollDesignationDAOImpl.create(mstPayrollDesignationMst);
			logger.info("Entries made in MstPayrollDesignation table with Pk values is"+payrollDesigPk + " and count returned is "+count);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			
		    }
		    else
		    	logger.info("mstDcpsDesignation is null ::Sorry" );
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
			logger.info("Error occured in insertPayrollDesignation method of PayrollDesignationMstServiceImpl class"+e.getMessage());
		}
		return resultObject;
	}

}
