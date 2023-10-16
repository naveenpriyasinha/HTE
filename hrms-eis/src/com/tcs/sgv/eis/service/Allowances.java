package com.tcs.sgv.eis.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.AllowArgMpgDAO;
import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.dao.ComponentMstDAOImpl;
import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
import com.tcs.sgv.allowance.service.Parser;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.valueobject.HrAllowArgumentMpg;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.history.dao.MaintainHistoryDaoImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.dao.DeducArgMpgDAOImpl;
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducArgumentMpg;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.HrEmpTraMpgDaoImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAO;
import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAOImpl;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.dao.VoluntryPFDAOImpl;
import com.tcs.sgv.eis.util.RevisedHrrAndHra;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpg;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpgHst;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpgHst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.eis.valueobject.PayrollCustomVO;
import com.tcs.sgv.eis.valueobject.ScaleCommissionMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class Allowances extends ServiceImpl{
	
	Log logger = LogFactory.getLog( getClass() );
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	
	
	public HrPayEmpallowMpg insertTechnicalAllowance(long techAllowId, long techAllowValue,long empId,Map objectArgs) throws Exception {
		//added by Ankit for Maha - Technical Allowance update/insert
		List allowList=new ArrayList();
		logger.info("inside insertTechnicalAllowance ");
		logger.info("techallowId "+techAllowId);
		logger.info("techAllowValue "+techAllowValue);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		EmpAllwMapDAOImpl empAllwMapDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
		allowList=empAllwMapDAO.getEmpallowMpg(empId, techAllowId, 1);
		logger.info("the allowList for Tech AllowId "+allowList);
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long dbId=Long.parseLong(loginMap.get("dbId").toString());
        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
		
		long locId=Long.parseLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
           	            			
		long postId=Long.parseLong(loginMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
		OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	
	    
	    long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
		
		long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
		HrPayEmpallowMpg hrPayEmpallowMpg=null;
		if(allowList.size()==0)
		{
			hrPayEmpallowMpg= new HrPayEmpallowMpg();
			IdGenerator IdGen = new IdGenerator();
			Long allowCode =IdGen.PKGenerator("HR_PAY_EMPALLOW_MPG", objectArgs);
			hrPayEmpallowMpg.setAllowCode(allowCode);
    		logger.info("value of id is "+hrPayEmpallowMpg.getAllowCode());
			
    		hrPayEmpallowMpg.setEmpAllowAmount(techAllowValue);
    		hrPayEmpallowMpg.setCreatedDate(new java.util.Date());						
    		hrPayEmpallowMpg.setCmnDatabaseMst(cmnDatabaseMst);
    		hrPayEmpallowMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
    		hrPayEmpallowMpg.setOrgPostMstByCreatedByPost(orgPostMst);
    		hrPayEmpallowMpg.setCmnLocationMst(cmnLocationMst);		
    		hrPayEmpallowMpg.setOrgUserMstByCreatedBy(orgUserMst);
    		hrPayEmpallowMpg.setOrgUserMstByUpdatedBy(orgUserMst);
    		hrPayEmpallowMpg.setUpdatedDate(new java.util.Date());
    		hrPayEmpallowMpg.setTrnCounter(new Integer(1));
			logger.info("INSIDE CREATE");
			empAllwMapDAO.create(hrPayEmpallowMpg);
			objectArgs.put("msg", "Record Inserted Successfully");
			
			logger.info(" Technical Allowance has been inserted ");
			
		}	
		else
		{
			hrPayEmpallowMpg =  (HrPayEmpallowMpg)allowList.get(0);
			 logger.info("INSIDE UPDATE");
			
			 hrPayEmpallowMpg.setEmpAllowAmount(techAllowValue);				
			 hrPayEmpallowMpg.setOrgPostMstByUpdatedByPost(orgPostMst);
			 hrPayEmpallowMpg.setOrgUserMstByUpdatedBy(orgUserMst);																	
			 hrPayEmpallowMpg.setUpdatedDate(new java.util.Date());
			 
			empAllwMapDAO.update(hrPayEmpallowMpg);
			
			logger.info(" Technical Allowance has been updated. ");
		}
		return hrPayEmpallowMpg;
		//ended by Ankit
	}

}
