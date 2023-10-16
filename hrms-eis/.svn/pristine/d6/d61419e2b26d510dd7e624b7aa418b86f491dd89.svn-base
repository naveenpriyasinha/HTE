package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDao;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.payroll.valueobject.HrPayCommissionMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstOffice;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDao;
import com.tcs.sgv.ess.dao.OrgDepartmentMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgGradeMstDao;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;

import com.tcs.sgv.eis.dao.HrEisGdDAOImpl;
import com.tcs.sgv.eis.dao.HrEisScaleMstDaoImpl;
import com.tcs.sgv.eis.dao.HrEisScaleMstDao;
/*import com.tcs.sgv.payroll.admin.dao.ClassDesignationMpgDAO;
import com.tcs.sgv.payroll.admin.dao.ClassDesignationMpgDAOImpl;
import com.tcs.sgv.payroll.admin.dao.HrEisScaleMstDao;

import com.tcs.sgv.payroll.admin.vo.HrPayGradeDesigRlt;
import com.tcs.sgv.payroll.txn.dao.EmployeesSalaryDAO;
import com.tcs.sgv.payroll.txn.dao.EmployeesSalaryDAOImpl;
import com.tcs.sgv.payroll.txn.service.EmployeesSalaryServiceImpl;
import com.tcs.sgv.payroll.txn.vo.HrPayEmpSalaryTxn;
import com.tcs.sgv.payroll.util.PayrollConstants;

/**
 * This class consists of methods to fetch and receive integration components
 * 
 * @class name : PayrollInboundIntegrationServiceImpl
 * @author : 229271
 * @version : 1
 */

public class PayrollInboundIntegrationServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	public PayrollInboundIntegrationServiceImpl() {

	}
	@SuppressWarnings("unchecked")
	public ResultObject retrieveDeptList(Map<String, Object> objectArgs) {
			
			logger.info("PayrollInboundIntegrationServiceImpl--->retrieveDeptList method called");
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			try {
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			//	OrgDepartmentMstDao deptMstDAO = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class, serv.getSessionFactory());
				GenericDaoHibernateImpl daoHibernateImpl = new GenericDaoHibernateImpl(OrgDdoMst.class);
				daoHibernateImpl.setSessionFactory(serv.getSessionFactory());
				
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				long langId = Integer.parseInt(loginMap.get("langId").toString());
				//Long langId = commonUserVO.getLangId();
				
				//System.out.println("===> PayrollInboundIntegrationServiceImpl langId :: "+langId);
				
				//List<OrgDdoMst> deptMstList = daoHibernateImpl.getListByColumnAndValue("cmnLanguageMst.langId", langId);
				List<OrgDdoMst> deptMstList = daoHibernateImpl.getListByColumnAndValue("langId", langId);
				objectArgs.put("deptMstList",deptMstList.subList(0, 10));
				
				//System.out.println("===> PayrollInboundIntegrationServiceImpl deptMstList :: "+deptMstList.size());
				
				resObj.setResultCode(ErrorConstants.SUCCESS);
				resObj.setResultValue(objectArgs);
			} catch (Exception ex) {
				ex.printStackTrace();
				resObj.setThrowable(ex);
				logger.error("Error in PayrollInboundIntegrationServiceImpl--->retrieveDeptList", ex);
				resObj.setResultCode(ErrorConstants.ERROR);
			}
			return resObj;
		}
	
	@SuppressWarnings("unchecked")
	public ResultObject retrieveGradeList(Map<String, Object> objectArgs) {
			
			logger.info("PayrollInboundIntegrationServiceImpl--->retrieveGradeList method called");
			ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
			try {
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				OrgGradeMstDao gradeMstDAO = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
				Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
				//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");
				//Long langId = commonUserVO.getLangId();
				long langId = Integer.parseInt(loginMap.get("langId").toString());
				
				List<OrgGradeMst> gradeMstList = gradeMstDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
				
				objectArgs.put("gradeMstList",gradeMstList);
				
				resObj.setResultCode(ErrorConstants.SUCCESS);
				resObj.setResultValue(objectArgs);
			} catch (Exception ex) {
				ex.printStackTrace();
				resObj.setThrowable(ex);
				logger.error("Error in PayrollInboundIntegrationServiceImpl--->retrieveGradeList", ex);
				resObj.setResultCode(ErrorConstants.ERROR);
			}
			return resObj;
		}
	@SuppressWarnings("unchecked")
	public ResultObject retrieveLocListFrmDept(Map<String, Object> objectArgs) {
		
		logger.info("PayrollInboundIntegrationServiceImpl--->retrieveLocListFrmDept method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			CmnLocationMstDao locMstDao = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			//CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");
			//Long langId = commonUserVO.getLangId();
			long langId = Integer.parseInt(loginMap.get("langId").toString());
			String ddoCode=objectArgs.get("deptCode").toString();
			logger.info("PayrollInboundIntegrationServiceImpl--->retrieveLocListFrmDept------>ddoCod= "+ddoCode);
			
			GenericDaoHibernateImpl daoHibernateImpl = new GenericDaoHibernateImpl(DdoOffice.class);
			daoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			
			//String[] deptCmprCol = {"cmnLanguageMst.langId","ddoCode"};
			//Object[] deptColValues = {langId,ddoCode};
			String deptCmprCol = "dcpsDdoCode";
			String  deptColValues = ddoCode;
			
		//	OrgDepartmentMstDao deptMstDAO = new OrgDepartmentMstDaoImpl(OrgDepartmentMst.class, serv.getSessionFactory());
			List<DdoOffice> officeList = daoHibernateImpl.getListByColumnAndValue(deptCmprCol, deptColValues);
			/*long deptId = deptMstList.get(0).getDepartmentId();
			
			
			String[] cmprCol = {"cmnLanguageMst.langId","departmentId"};
			Object[] colValues = {langId,deptId};
			List<CmnLocationMst> locMstList = locMstDao.getListByColumnAndValue(cmprCol, colValues);
			objectArgs.put("locMstList",locMstList);objectArgs.put("locMstList",locMstList);
			*/
			objectArgs.put("locMstList",officeList);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Error in PayrollInboundIntegrationServiceImpl--->retrieveLocListFrmDept", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
	@SuppressWarnings("unchecked")
	public ResultObject retrieveDesgnListFrmGrade(Map<String, Object> objectArgs) {
		
		logger.info("PayrollInboundIntegrationServiceImpl--->retrieveDesgnListFrmGrade method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			//HrEisGDMpgDao gdMpgDao = new HrEisGDMpgDaoImpl(HrEisGdMpg.class, serv.getSessionFactory());
			//ClassDesignationMpgDAO gdMpgDao = new  ClassDesignationMpgDAOImpl(HrPayGradeDesigRlt.class,serv.getSessionFactory());
			HrEisGdDAOImpl  eisGdDAOImpl = new HrEisGdDAOImpl(HrEisGdMpg.class,serv.getSessionFactory());
			
			
			Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
			///CommonUserVO commonUserVO = (CommonUserVO)loginMap.get("commonUserVO");
			Long langId = Long.valueOf(loginMap.get("langId").toString());
			long locId=Long.valueOf(loginMap.get("locationId").toString());
			String gradeCode=objectArgs.get("gradeCode").toString();
			logger.info("PayrollInboundIntegrationServiceImpl--->retrieveDesgnListFrmGrade------>gradeCode"+gradeCode);
			
			String[] gradeCmprCol = {"cmnLanguageMst.langId","gradeCode"};
			Object[] gradeColValues = {langId,gradeCode};
			OrgGradeMstDao gradeMstDAO = new OrgGradeMstDaoImpl(OrgGradeMst.class, serv.getSessionFactory());
			List<OrgGradeMst> gradeMstList = gradeMstDAO.getListByColumnAndValue(gradeCmprCol, gradeColValues);
			long gradeId = gradeMstList.get(0).getGradeId();
			
			//System.out.println("==> gradeId :: "+gradeId);
			//String[] cmprCol = {"cmnLanguageMst.langId","departmentId"};
			//Object[] colValues = {langId,deptId};
			//HrPayGradeDesigRlt
			//List<HrEisGdMpg> gdMpgList = eisGdDAOImpl.getListByColumnAndValue("orgGradeMst.gradeId", gradeId);
			List<HrEisGdMpg> gdMpgList = null;// eisGdDAOImpl.getdesigsfromgradesAndLoc(gradeId,locId);
			List<OrgDesignationMst> desgnMstList = new ArrayList();
			OrgDesignationMst desgnMstVO = null;
			for(int i=0;i<gdMpgList.size();i++)
			{
				desgnMstVO = new OrgDesignationMst();
				desgnMstVO = gdMpgList.get(i).getOrgDesignationMst();
				desgnMstList.add(desgnMstVO);
			}
			
			objectArgs.put("desgnMstList",desgnMstList);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Error in PayrollInboundIntegrationServiceImpl--->retrieveDesgnListFrmGrade", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
public ResultObject retrieveScaleListFrmPayComsn(Map<String, Object> objectArgs) {
		
		logger.info("PayrollInboundIntegrationServiceImpl--->retrieveScaleListFrmPayComsn method called");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			
			long payCommissionId=Long.parseLong(objectArgs.get("payCommissionId").toString());
			logger.info("PayrollInboundIntegrationServiceImpl--->retrieveScaleListFrmPayComsn------>payCommissionId"+payCommissionId);
			
			GenericDaoHibernateImpl daoHibernateImpl = new GenericDaoHibernateImpl(HrPayCommissionMst.class);
			daoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			HrPayCommissionMst commissionMst =(HrPayCommissionMst)daoHibernateImpl.read(payCommissionId);
			
			//String[] scaleCmprCol = {"cmnLanguageMst.langId","commissionId"};
			String[] scaleCmprCol = {"cmnLanguageMst.langId","hrPayCommissionMst"};
			Object[] scaleColValues = {1L,commissionMst};
			String[] scaleOrderCol = {"scaleStartAmt","scaleGradePay","scaleIncrAmt"};
			HrEisScaleMstDao scaleMstDAO = new HrEisScaleMstDaoImpl(HrEisScaleMst.class, serv.getSessionFactory());
			List<HrEisScaleMst> scaleMstList = scaleMstDAO.getListByColumnAndValue(scaleCmprCol, scaleColValues,scaleOrderCol);
			
			
			objectArgs.put("scaleMstList",scaleMstList);
			
			resObj.setResultCode(ErrorConstants.SUCCESS);
			resObj.setResultValue(objectArgs);
		} catch (Exception ex) {
			ex.printStackTrace();
			resObj.setThrowable(ex);
			logger.error("Error in PayrollInboundIntegrationServiceImpl--->retrieveScaleListFrmPayComsn", ex);
			resObj.setResultCode(ErrorConstants.ERROR);
		}
		return resObj;
	}
public ResultObject receiveExistingEmployeeDetails(Map<String, Object> objectArgs) {
	
	logger.info("PayrollInboundIntegrationServiceImpl--->receiveExistingEmployeeDetails method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
		// execute HRMS service here -- "payrollEmployeeCode" is key
		
		
		receiveEmployeeDetails(objectArgs);
		objectArgs.put("allPayComponentsCalculationFlag","YES");
		
		resObj.setResultCode(ErrorConstants.SUCCESS);
		resObj.setResultValue(objectArgs);
	} catch (Exception ex) {
		ex.printStackTrace();
		resObj.setThrowable(ex);
		logger.error("Error in PayrollInboundIntegrationServiceImpl--->receiveExistingEmployeeDetails", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
private ResultObject receiveEmployeeDetails(Map<String, Object> objectArgs) {
	
	logger.info("PayrollInboundIntegrationServiceImpl--->receiveEmployeeDetails method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
		
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
		Map loginMap = (Map) (Map) objectArgs.get("baseLoginMap");
		long langId = Long.parseLong((loginMap.get("langId").toString()));
		
		long reqdSalariedOrgEmpId=Long.parseLong(objectArgs.get("payrollEmployeeCode")!=null?objectArgs.get("payrollEmployeeCode").toString():"0");
		String locId="";
		
		logger.info("PayrollInboundIntegrationServiceImpl--->receiveEmployeeDetails------>reqdSalariedOrgEmpId::"+reqdSalariedOrgEmpId);
		
		//EmployeesSalaryDAO empSalaryDao = new EmployeesSalaryDAOImpl(HrPayEmpSalaryTxn.class,serv.getSessionFactory());
		
		String empDeptCode=objectArgs.get("payrollEmpDeptCode")!=null?objectArgs.get("payrollEmpDeptCode").toString():"";
		String empOfficeCode=objectArgs.get("payrollEmpLocationCode")!=null?objectArgs.get("payrollEmpLocationCode").toString():"";
		String empClassCode=objectArgs.get("payrollEmpClassCode")!=null?objectArgs.get("payrollEmpClassCode").toString():"";
		//String empPayrollEmpCtgryCode=objectArgs.get("payrollEmpPayrollEmpCtgryCode")!=null?objectArgs.get("payrollEmpPayrollEmpCtgryCode").toString():"";
		String empGender=objectArgs.get("payrollEmpGender")!=null?objectArgs.get("payrollEmpGender").toString():"M";//get "M" for male and "F" for female
		String empDesgnCode=objectArgs.get("payrollEmpDesgnCode")!=null?objectArgs.get("payrollEmpDesgnCode").toString():"";
		String empPostType=objectArgs.get("payrollEmpPostType")!=null?objectArgs.get("payrollEmpPostType").toString():"P";//get "P" for permanent and "T" for temporary
		String empPhyChallengedFlag=objectArgs.get("payrollEmpPhyChallengedFlag")!=null?objectArgs.get("payrollEmpPhyChallengedFlag").toString():"N";// get "Y" for yes and "N" for no
		String empCityCode=objectArgs.get("payrollEmpCityCode")!=null?objectArgs.get("payrollEmpCityCode").toString():"";
		String empQuarterTypeCode=objectArgs.get("payrollEmpQuarterTypeCode")!=null?objectArgs.get("payrollEmpQuarterTypeCode").toString():"";
		String empBasicPay=objectArgs.get("payrollEmpBasicPay")!=null?objectArgs.get("payrollEmpBasicPay").toString():"0";
		String empGradePay=objectArgs.get("payrollEmpGradePay")!=null?objectArgs.get("payrollEmpGradePay").toString():"0";
		String empPayScaleCode=objectArgs.get("payrollEmpPayScaleCode")!=null?objectArgs.get("payrollEmpPayScaleCode").toString():"";
		String empPayCommission=objectArgs.get("payrollEmpPayCommission")!=null?objectArgs.get("payrollEmpPayCommission").toString():"6";//get "6" for sixth and "5" for fifth
		String empGPFAccntNo=objectArgs.get("payrollEmpGPFAccntNo")!=null?objectArgs.get("payrollEmpGPFAccntNo").toString():"";
		Date empDateOfRetirement=null;
		Date empDateOfBirth=null;
		
		List basicDetailsEmp = null;//empSalaryDao.getEmployeeBasciDetailsForParameter(reqdSalariedOrgEmpId, langId);
		
		
		//List<PayrollEmployeeCustomVO> empCustomVOList = new ArrayList();
		//PayrollEmployeeCustomVO empCustomVO = null;
		for(int empCount=0;empCount<basicDetailsEmp.size();empCount++)
		{
			//empCustomVO = new PayrollEmployeeCustomVO();
			Object[] rowList = (Object[]) basicDetailsEmp.get(empCount);
			
			if(rowList[0] != null)
			{
				reqdSalariedOrgEmpId = ((Long)(rowList[0])).longValue();
			}
			if(rowList[1] != null)
			{
				empGPFAccntNo = rowList[1].toString().trim();
			}
			if(rowList[2] != null)
			{
				empDeptCode = rowList[2].toString().trim();
			}
			
			if(rowList[3] != null)
			{
				locId = rowList[3].toString().trim();
			}
			if(rowList[4] != null)
			{
				empClassCode = rowList[4].toString().trim();
			}
			if(rowList[5] != null)
			{
				empGender = rowList[5].toString().trim();
			}
			if(rowList[6] != null)
			{
				empDesgnCode = rowList[6].toString().trim();
			}
			if(rowList[7] != null)
			{
				empPhyChallengedFlag = rowList[7].toString().trim();
			}
			if(rowList[8] != null)
			{
				empCityCode = rowList[8].toString().trim();
			}
			if(rowList[9] != null)
			{
				empGradePay = rowList[9].toString().trim();
			}
			if(rowList[10] != null)
			{
				empPayScaleCode = rowList[10].toString().trim();
			}
			if(rowList[11] != null)
			{
				empPayCommission = rowList[11].toString().trim();
			}
			
		}
		/*PayrollEmployeeInBoundDtlVO empDtl =  new PayrollEmployeeInBoundDtlVO();
		
		if(objectArgs.get("payrollEmpDtlVO")!=null)
			empDtl = (PayrollEmployeeInBoundDtlVO) objectArgs.get("payrollEmpDtlVO");
		*/
		//un-comment this part when integration with HRMS to fetch employee detail is done
		
//		String empDeptCode=empDtl.getDeptCode();
//		String empOfficeCode=empDtl.getLocationCode();
//		String empClassCode=empDtl.getGradeCode();
//		String empPayrollEmpCtgryCode=empDtl.getPayrollEmpCtgryCode();
//		int empGender=empDtl.getGender();//get "M" for male and "F" for female
//		String empDesgnCode=empDtl.getDesgnCode();
//		int empPostType=empDtl.getPostType();//get "P" for permanent and "T" for temporary
//		int empPhyChallengedFlag=empDtl.getPhysicallyChallengedFlag();// get "Y" for yes and "N" for no
//		String empCityCode=empDtl.getCityCode();
//		String empQuarterTypeCode=empDtl.getQuarterTypeCode();
//		long empBasicPay=empDtl.getBasicPay();
//		long empGradePay=empDtl.getGradePay();
//		String empPayScaleCode=empDtl.getPayScaleCode();
//		int empPayCommission=empDtl.getPayCommission();//get "6" for sixth and "5" for fifth
//		String empGPFAccntNo=empDtl.getGpfDpfPranAccntNo();
//		Date empDateOfRetirement=null;
//		Date empDateOfBirth=null;
//		
//		if(empDtl.getRetirementDate()!=null)
//			empDateOfRetirement=empDtl.getRetirementDate();
//		
//		if(empDtl.getDateOfBirth()!=null)
//			empDateOfBirth=empDtl.getDateOfBirth();
	
		//long reqdEmployeeCode = empDtl.getEmployeeCode();
		
		
		
		if(objectArgs.get("payrollEmpDateOfRetirement")!=null)
			empDateOfRetirement=(Date) objectArgs.get("payrollEmpDateOfRetirement");
		
		if(objectArgs.get("payrollEmpDateOfBirth")!=null)
			empDateOfBirth=(Date) objectArgs.get("payrollEmpDateOfBirth");
		
		if(empGender.equalsIgnoreCase("F"))
			empGender="2";
		else
			empGender="1";
		
		if(empPhyChallengedFlag.equalsIgnoreCase("Y"))
			empPhyChallengedFlag="1";
		else
			empPhyChallengedFlag="0";
		
		if(empPostType.equalsIgnoreCase("T"))
			empPostType="2";
		else
			empPostType="1";
		
		
		if(empPayCommission.equalsIgnoreCase("341286"))
			empPayCommission="5";
		else
			empPayCommission="6";
		
		
		//temp code
		EmpDAO empdao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
		OrgEmpMst empMst = new OrgEmpMst();
		//empGPFAccntNo="PH/8585"; //Need 
	
		//temporary test code*****************
		if(reqdSalariedOrgEmpId==1337)
		{
			//System.out.println("===> Coming in paramvalue in temp test code");
		//reqdEmployeeCode=120070;
		//reqdEmployeeCode=120073;
		empDeptCode="1";
		empOfficeCode="1";
		//empClassCode="100110013";
		//empClassCode="100110011";
		empClassCode="100064";
		//empPayrollEmpCtgryCode="1";
		empGender="1";				  //Need 
		//empDesgnCode="30";
		empDesgnCode="100026";
		empPostType="1";
		empPhyChallengedFlag="0";
		empCityCode="1";
		empQuarterTypeCode="";
		empBasicPay="15600";//employee basic pay should not include grade pay. If included, subtract grade pay from basic received and set again
		empGradePay="7800";
		empPayScaleCode="300004";
		empPayCommission="6";
		
		empMst =  empdao.read(1337L);
		empDateOfRetirement = empMst.getEmpSrvcExp();  //Need 
		empDateOfBirth = empMst.getEmpDob(); //Need 
		
		}
		else //if(reqdSalariedOrgEmpId==120070)
		{
			//System.out.println("==> in else if........... ");
			empDeptCode="1";
			empOfficeCode="1";
			empClassCode="100110015";
			//empPayrollEmpCtgryCode="1";
			empGender="1";
			//empDesgnCode="30";
			empDesgnCode="22";
			empPostType="1";
			empPhyChallengedFlag="0";
			empCityCode="1";
			empQuarterTypeCode="";
			empBasicPay="20000";
			empGradePay="5400";
			empPayScaleCode="300005";
			empPayCommission="6"; // need as it is
			
			empMst =  empdao.read(reqdSalariedOrgEmpId);
			empDateOfRetirement = empMst.getEmpSrvcExp();
			empDateOfBirth = empMst.getEmpDob();
			
		}
		//temporary test code ends **********************
		
		//System.out.println("===> Coming in paramvalue "+reqdSalariedOrgEmpId);
		//System.out.println("==> empGPFAccntNo :: "+empGPFAccntNo);
		//System.out.println("==> empDeptCode :: "+empDeptCode);
		//System.out.println("==> locId :: "+locId);
		//System.out.println("==> empClassCode :: "+empClassCode);
		//System.out.println("==> empGender :: "+empGender);
		//System.out.println("==> empDesgnCode :: "+empDesgnCode);
		//System.out.println("==> empPhyChallengedFlag :: "+empPhyChallengedFlag);
		//System.out.println("==> empCityCode :: "+empCityCode);
		//System.out.println("==> empBasicPay :: "+empBasicPay);
		//System.out.println("==> empGradePay :: "+empGradePay);
		//System.out.println("==> empPayScaleCode :: "+empPayScaleCode);
		//System.out.println("==> empPayCommission :: "+empPayCommission);
		
		
		Map<Integer, Object> paramValueMap = new HashMap<Integer, Object>();
		
		/*paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DEPT, empDeptCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_OFFICE, empOfficeCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GRADE, empClassCode);
		//paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYROLLEMPCTGRY, empPayrollEmpCtgryCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GENDER, empGender);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_DESGN, empDesgnCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_POSTTYPE, empPostType);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PHYCHALLENGED, empPhyChallengedFlag);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_CITY, empCityCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_QUARTERTYPE, empQuarterTypeCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_BASICPAY, empBasicPay);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_GRADEPAY, empGradePay);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYSCALE, empPayScaleCode);
		paramValueMap.put(PayrollConstants.PAYROLL_PARAMID_PAYCOMSN, empPayCommission);
		*/
		objectArgs.put("paramValueMap",paramValueMap);
		objectArgs.put("reqdEmployeeCode",reqdSalariedOrgEmpId);
		objectArgs.put("employeeGender",empGender);
		objectArgs.put("empDateOfRetirement",empDateOfRetirement);
		objectArgs.put("empGPFAccntNo",empGPFAccntNo);
		objectArgs.put("empDateOfBirth",empDateOfBirth);
		
		//objectArgs.put("allPayComponentsCalculationFlag","YES");// to be set in individual calling methods like for GPF and basic change
		
		resObj.setResultCode(ErrorConstants.SUCCESS);
		resObj.setResultValue(objectArgs);
	} catch (Exception ex) {
		ex.printStackTrace();
		resObj.setThrowable(ex);
		logger.error("Error in PayrollInboundIntegrationServiceImpl--->receiveEmployeeDetails", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
 }
	public ResultObject receiveNewRecruitmentDetails(Map<String, Object> objectArgs) {
	
	logger.info("PayrollInboundIntegrationServiceImpl--->receiveNewRecruitmentDetails method called");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
	try {
		// this service to be called after recruitment in HRMS -- "PAYROLL_NEW_RECR_SERV"
		/*EmployeesSalaryServiceImpl empSalService = new  EmployeesSalaryServiceImpl();
		empSalService.insertNewEmployeeRuleBasedPayComp(objectArgs);*/
		
		resObj.setResultCode(ErrorConstants.SUCCESS);
		resObj.setResultValue(objectArgs);
	} catch (Exception ex) {
		logger.error(ex.getMessage(),ex);
		resObj.setThrowable(ex);
		logger.error("Error in PayrollInboundIntegrationServiceImpl--->receiveNewRecruitmentDetails", ex);
		resObj.setResultCode(ErrorConstants.ERROR);
	}
	return resObj;
  }
}

