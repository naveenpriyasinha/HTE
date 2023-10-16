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
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.AllowArgMpgDAO;
import com.tcs.sgv.allowance.dao.AllowTypeMasterDAOImpl;
import com.tcs.sgv.allowance.dao.ComponentMstDAOImpl;
import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
import com.tcs.sgv.allowance.service.Parser;
import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
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
import com.tcs.sgv.eis.dao.BatchITGpfDetailsUpdateDaoImpl;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.HrEmpTraMpgDaoImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAO;
import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAOImpl;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.dao.VoluntryPFDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
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
public class Deduction {

	
	Log logger = LogFactory.getLog( getClass() );
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
	
	
	public HrPayDeductionDtls insertNonComputationalDeduction(long deducId, long deducValue,long empId,Map objectArgs) throws Exception {
	{
		
		
		HrPayDeductionDtls hrPayDeducDtls=null;
		List<HrPayDeductionDtls> deducList=new ArrayList<HrPayDeductionDtls>();
		HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		
		
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
		
		
		
		
		EmpInfoDAOImpl empInfoDao= new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		List<HrEisEmpMst> hrEisEmpMstList = empInfoDao.getListByColumnAndValue("orgEmpMst.empId",empId);
		logger.info("Emmp Id is:-"+empId);
		HrEisEmpMst hrEisEmpMst=  new HrEisEmpMst();
		 
		if(hrEisEmpMstList!=null && hrEisEmpMstList.size()>0)
		{
			hrEisEmpMst = hrEisEmpMstList.get(0);
		}
		
		
		
		DeducTypeMasterDAOImpl deducMstDao = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
		
		DeductionDtlsDAOImpl deducDao = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
		//deducList=deducDao.getDeductionDtls(empId, deducId); //72=GPF_GRP_ABC
		
		OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		 otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		logger.info("The info empid is:-"+empId);
		hrEisOtherDtls=otherdtlsDao.getOtherData(empId);
	
		long payCommonissionId = 2500340;//By default the commission id is 5th pay commission(For fixed and contractual employee)
		if(hrEisOtherDtls.getHrEisSgdMpg()!=null)
			payCommonissionId = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId();
		
		if(deducList.size()==0)
		{
			hrPayDeducDtls= new HrPayDeductionDtls();
			IdGenerator IdGen = new IdGenerator();
			Long deducCode =IdGen.PKGenerator("hr_pay_deduction_dtls", objectArgs);
    		hrPayDeducDtls.setDeducDtlsCode(deducCode);
    		
    		hrPayDeducDtls.setHrEisEmpMst(hrEisEmpMst);
    		hrPayDeducDtls.setEmpDeducAmount(deducValue);
    	
    		hrPayDeducDtls.setHrPayDeducTypeMst(deducMstDao.read(deducId));
    		
    		hrPayDeducDtls.setEmpCurrentStatus(1);
    		hrPayDeducDtls.setCreatedDate(new java.util.Date());						
    		hrPayDeducDtls.setCmnDatabaseMst(cmnDatabaseMst);
    		hrPayDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
    		hrPayDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);
    		hrPayDeducDtls.setUpdatedDate(new java.util.Date());
    		hrPayDeducDtls.setOrgPostMstByCreatedByPost(orgPostMst);
    		hrPayDeducDtls.setCmnLocationMst(cmnLocationMst);		
    		hrPayDeducDtls.setOrgUserMstByCreatedBy(orgUserMst);
    		hrPayDeducDtls.setTrnCounter(new Integer(1));
			logger.info("INSIDE CREATE");
			deducDao.create(hrPayDeducDtls);	
			objectArgs.put("msg", "Record Inserted Successfully");
		
			logger.info(" deduction detail insert for GPF_GRP_D amount ");
			
		}	
		else
		{
			hrPayDeducDtls=new HrPayDeductionDtls();
			hrPayDeducDtls = deducList.get(0);
			
			hrPayDeducDtls.setEmpDeducAmount(deducValue);
			
			hrPayDeducDtls.setUpdatedDate(new java.util.Date());
			hrPayDeducDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			hrPayDeducDtls.setOrgUserMstByUpdatedBy(orgUserMst);
			
			logger.info("going to update for hrPayDeducDtls ");
			deducDao.update(hrPayDeducDtls);
			
			//For Basic Detail Screen
		/*	hrEisOtherDtls.setIncomeTax(It_Val);
			hrEisOtherDtls.setUpdatedDate(new java.util.Date());
			hrEisOtherDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
			hrEisOtherDtls.setOrgUserMstByUpdatedBy(orgUserMst);*/
			//Edded for basic detail screen
			logger.info(" deduction detail update for hrPayDeducDtls amount - GPF_GRP_D");
			
		
		}
		return hrPayDeducDtls;
	}
}
}
