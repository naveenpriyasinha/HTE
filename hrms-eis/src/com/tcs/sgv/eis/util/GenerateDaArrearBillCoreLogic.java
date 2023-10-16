package com.tcs.sgv.eis.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.ComponentMstDAOImpl;
import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
import com.tcs.sgv.allowance.service.Parser;
import com.tcs.sgv.allowance.valueobject.HrPayComponentMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.allowance.valueobject.PaybillInputVO;
import com.tcs.sgv.allowance.valueobject.PaybillOutputVO;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.util.HttpServletRequestProxy;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.eis.dao.CityCatMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAO;
import com.tcs.sgv.eis.dao.ScaleCommissionMpgDAOImpl;
import com.tcs.sgv.eis.dao.VoluntryPFDAOImpl;
import com.tcs.sgv.eis.service.GenerateBillService;
import com.tcs.sgv.eis.valueobject.CityCatMpg;
import com.tcs.sgv.eis.valueobject.EdpDtlsVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMstHst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtlsHst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtlsHst;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.ScaleCommissionMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class GenerateDaArrearBillCoreLogic {
	
	Log logger = LogFactory.getLog(getClass());
	
	public Map executeCoreLogic(Map inputMap)
	{
		Map resultMap = inputMap;
		if(resultMap.containsKey("payBillVO"))
			resultMap.remove("payBillVO");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		
		
		long dpLookupId = Long.parseLong(resourceBundle.getString("dpLookupId"));

		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId")); 
		
		int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
		int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));
		
		
		long DaId = Long.parseLong(resourceBundle.getString("daCode"));
		long cpfId = Long.parseLong(resourceBundle.getString("cpfId"));
       
        double gradePay=0;			//GradePay Allowance
        
        
		long maxDaysFlag=0;
		
		long maxDaysUserPostRltId=0;
		
		int startDays ;
		int startMonth;
		int startYear ;
		 
		int endMonth=0;
		int endYear=0;
		int endDays=0;
		 
		long maxDay=0;
		long daysOfPost =0;
		double dpValue=0.0;
		
		 
		Calendar calGiven = Calendar.getInstance();
		Map paramMap = new HashMap();
			
		PaybillInputVO InputVO = new PaybillInputVO(); 
		
		long psrNo = 0;
		
		HrPayPaybill oldPaybill = (HrPayPaybill)inputMap.get("oldPaybill");
		
		int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());
		
		InputVO.setMonth(monthGiven);
		InputVO.setYear(yearGiven);
		
		OrgUserpostRlt orgUPRlt = (OrgUserpostRlt)inputMap.get("orgUPRlt");
		HrEisOtherDtlsHst hrEisOtherDtlsHst = (HrEisOtherDtlsHst)inputMap.get("hrEisOtherDtlsHst");
		
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		
		CityCatMpgDAOImpl cityCatDAO= new CityCatMpgDAOImpl(CityCatMpg.class,serv.getSessionFactory());
		List<CityCatMpg> cityCatVO = new ArrayList();

		List<HrPayEdpCompoMpg> edpList= new ArrayList();
		
		VoluntryPFDAOImpl empVPFDao = new VoluntryPFDAOImpl(HrPayVpfDtls.class, serv.getSessionFactory());
		List<HrPayVpfDtlsHst> hrPayVpfDtlsList = new ArrayList();
		HrPayVpfDtlsHst hrPayVpfDtlsHst = new HrPayVpfDtlsHst();
		
		
		List postIdList = (List)inputMap.get("postIdList");
				
		maxDaysUserPostRltId = Long.parseLong(inputMap.get("maxDaysUserPostRltId").toString());
				
		HttpServletRequestProxy proxyReq = (HttpServletRequestProxy)inputMap.get("proxyReq");
		
		logger.info("the post id is "+orgUPRlt.getOrgPostMstByPostId().getPostId());
		
		if(postIdList.contains(orgUPRlt.getOrgPostMstByPostId().getPostId()))
		{
			GenerateBillService billService = new GenerateBillService(); 
			 
			 startDays =billService.dayofDate((orgUPRlt.getStartDate()));
			 startMonth =billService.monthofDate((orgUPRlt.getStartDate()));
			 startYear =billService.yearofDate((orgUPRlt.getStartDate()));
			 
			 endMonth=0;
			 endYear=0;
			 endDays=0;
			 
			 maxDay=0;
			 daysOfPost =0;
			 
			
			 calGiven.set(Calendar.YEAR, yearGiven);
			 calGiven.set(Calendar.MONTH,(monthGiven-1));
			 calGiven.set(Calendar.DAY_OF_MONTH, 1);
				
			 maxDay=calGiven.getActualMaximum(5);
			 Date givenDate = calGiven.getTime();
			 logger.info("the value of maxDay "+maxDay+" and startDays is "+startDays);
			 
			 if(orgUPRlt.getEndDate()!=null)
			 {
				endDays =billService.dayofDate(orgUPRlt.getEndDate());
				endMonth =billService.monthofDate((orgUPRlt.getEndDate()));
				endYear =billService.yearofDate((orgUPRlt.getEndDate()));
				
				logger.info("the value of endDays "+endDays+" and startDays is "+startDays);
				if(endMonth==monthGiven  && endYear == yearGiven)	// End date in current month and year.
				{
					if(startMonth==monthGiven && startYear==yearGiven)  // Start and end date both are in the same month and year.
					{
						daysOfPost=endDays-(startDays-1);
						logger.info("Total Days:-"+daysOfPost);
					}
					else // start date is not in current month but end date is in current month.
				    {
						daysOfPost = endDays;
						logger.info("Total No. Days:-"+daysOfPost);
				    }
				}
				else //	End date is not in current month and year. 
				{
					if(startMonth==monthGiven && startYear == yearGiven)	// if startDate is in current month.
					{
						daysOfPost=maxDay-(startDays-1);
					}
					else if(givenDate.after(orgUPRlt.getStartDate()) && givenDate.before(orgUPRlt.getEndDate())) // if whole current month is between the start and end date.
					{
						daysOfPost=maxDay;
						logger.info("when start date is less than current date "+daysOfPost);
					}
					else	// if whole current month is not between the start and end date.
				    {
						logger.info("no of post is not set");
				    }
				}
			 }
			 else	// if End Date is null.
			 {
				 
				if(startMonth==monthGiven && startYear == yearGiven)	// if startDate is in current month.
				{
					daysOfPost=maxDay-(startDays-1);
				}
				else if (givenDate.after(orgUPRlt.getStartDate()))	// if startdate is less then current date.
			    {
					daysOfPost=maxDay;
			    }
				else	// if start date is greater then current date.
					logger.info("from end date is null .no of post is not set");
			 }
			 logger.info("The maxDaysUserPostRltId is "+maxDaysUserPostRltId+" the orgUPRlt.getEmpPostId() "+orgUPRlt.getEmpPostId());
		if(maxDaysUserPostRltId==orgUPRlt.getEmpPostId())
		{
			maxDaysFlag=1;
		}
		logger.info("The day diff is "+daysOfPost+" the maxDaysFlag "+maxDaysFlag);
		//end by manoj form 10-20 day issue
		
		long vpf=0;
		long CurrBasic=0;
		long cityId=0;
		String city="";
		int grade=0;
		int Designation=0;
		long scale_start_amt=0;
		long old_scale_start_amt=0;
		int quater_id=0;

		boolean isHandicapped=true;
		//by manoj for gpf Account detail
		int gpfAcctFlag=0;
		//end by manoj for gpf Account detail
		
        long incomeTax=0;
		
		Integer ZerovpfMonths=3;
		
		
		HrPayPaybill payBillVO = new HrPayPaybill();
		
		long empid=hrEisOtherDtlsHst.getHrEisEmpMst().getEmpId();
		
		EmpInfoDAOImpl empInfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		
		//by manoj for sis issue 
		
		OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		
		
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, yearGiven);
        cal2.set(Calendar.MONTH, monthGiven);
        cal2.set(Calendar.DATE, 1);
        
        
		long locId=0;
		try
		{
			
		
		Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
		locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
		CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);	
		
		
		}
		catch(Exception e)
		{
			logger.info("error while getting location id from base login map");
			logger.error("Error is: "+ e.getMessage());
		}
		
		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		 				 
		long oldScaleId=0;

        OtherDetailDAOImpl otherdtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		
        HrEisOtherDtls otherdetail = otherdtlsDao.read(hrEisOtherDtlsHst.getId().getOtherId());
		
        //System.out.println("   other id  in arrera       "+hrEisOtherDtlsHst.getId().getOtherId());
        //System.out.println("   otherdetail.getHrEisSgdMpg()       "+otherdetail.getHrEisSgdMpg());
        
		long payCommissionId = (otherdetail.getHrEisSgdMpg()!=null)?otherdetail.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId().longValue():0;
		
		logger.info("the pay commision for the emp from generateArrearBillService is "+payCommissionId);
		
		
		if(payCommissionId==sixthPayCommId){
			
			
			gradePay=otherdetail.getHrEisSgdMpg().getHrEisScaleMst().getScaleGradePay();
			InputVO.setGradePay(gradePay);
			InputVO.setCityId(hrEisOtherDtlsHst.getCity());
			oldScaleId=hrEisOtherDtlsHst.getHrEisSgdMpg().getHrEisScaleMst().getScaleId();
			HrEisScaleMst eisScale =  otherDtlsDao.getOldScaleFromEmpid(empid);
			scale_start_amt = eisScale.getScaleStartAmt();
			InputVO.setScale_start_amt(scale_start_amt);
			logger.info("in sixth pay grade pay is "+gradePay);
		}
		else if(payCommissionId!=0){			// For Contractual,Fix Pay SGD is null
			ScaleCommissionMpgDAO scaleCommMpgDao = new ScaleCommissionMpgDAOImpl(ScaleCommissionMpg.class,serv.getSessionFactory());
			ScaleCommissionMpg scaleCommMpg =  scaleCommMpgDao.getNewScaleFromOldScale(hrEisOtherDtlsHst.getHrEisSgdMpg().getHrEisScaleMst().getScaleId());
			gradePay = scaleCommMpg.getCommissionSix().getScaleGradePay();
			InputVO.setGradePay(gradePay);
			logger.info("in fifth pay grade pay is "+gradePay);
		}
		else 			// SGD is null so gradePay=0
		{
			InputVO.setGradePay(0);
			logger.info("in no pay commission grade pay is "+gradePay);
		}
		
		
		
		//end by manoj for sis issue
		
		
		List empInfoHstList = empInfoDao.getAllEmpDataFromHst(empid,locId,monthGiven-1,yearGiven);
		
		HrEisEmpMstHst hrEisEmpMstHst = (HrEisEmpMstHst)empInfoHstList.get(0);
		
		HrEisOtherDtlsHst otherDtlsVOHst = hrEisOtherDtlsHst;
		//empid=40;
		logger.info("**********************************************************the emp id is "+empid);
		
		
		
		
		
		
		//CurrBasic=hrEisOtherDtlsHst.getotherCurrentBasic();
		
		//by manoj used for getting city category from city_id of other detail
		cityId=hrEisOtherDtlsHst.getCity();
		
		
		//CityCatMpg cityCatVO = cityCatDAO.getCityCat(cityId);
		cityCatVO = cityCatDAO.getListByColumnAndValue("cityId", cityId);
		if(cityCatVO!=null && cityCatVO.size()>0)
		{
			city=cityCatVO.get(0).getCategoryId();
		}
		//end of city
		
		long empType = hrEisEmpMstHst.getEmpType();
		//grade=(int)hrEisOtherDtls.get(cnt).getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeId();
		//if(empType!=300018 && empType!=300225)
		if(empType!=contractEmpType && empType!=fixedEmpType)
		{
			grade = (int)hrEisOtherDtlsHst.getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeId();
			Designation=(int)hrEisOtherDtlsHst.getHrEisSgdMpg().getHrEisGdMpg().getOrgDesignationMst().getDsgnId();
			scale_start_amt=hrEisOtherDtlsHst.getHrEisSgdMpg().getHrEisScaleMst().getScaleStartAmt();
		}
		else
		{
			grade = 0;
			Designation = 0;
			scale_start_amt = hrEisOtherDtlsHst.getotherCurrentBasic();
		}
		
		//by manoj for tra update
		
		
		
		/*if(hrEisOtherDtls.get(cnt).getHrQuaterTypeMst() != null)
			quater_id=(int)hrEisOtherDtls.get(cnt).getHrQuaterTypeMst().getQuaId();
		else
			quater_id=0;*/
		//end by manoj for quarter related issues
		isHandicapped = Boolean.parseBoolean(hrEisOtherDtlsHst.getPhyChallenged().toLowerCase());
		incomeTax=Math.round(hrEisOtherDtlsHst.getIncomeTax());
		
		
		//by manoj for gpf_accont details
		
		long gpfGrade = 0;
		//by manoj for gpf grade no
		 long userIdGpf = otherDtlsVOHst.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
			EmpGpfDtlsDAOImpl gpfDtlsDAO = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
			List gpfGradeList = gpfDtlsDAO.getAllGpfDetailsbyUserId(userIdGpf);
			if(gpfGradeList!=null && gpfGradeList.size()>0)
			{
				Iterator itr = gpfGradeList.iterator();  
				Object[] rowList = (Object[]) itr.next();
				if(rowList[3]!=null)
				gpfGrade=Long.parseLong(rowList[3].toString());
				logger.info("GPF Class for employee is " + gpfGrade);
			}
		//end by manoj for gpf_accont details
		
		
		Map empLeaveMap = billService.empLeaveDtls(empid,serv,monthGiven,yearGiven);
		
		double CLLeaveBal = Double.parseDouble(empLeaveMap.get("CLLeaveBal").toString());
		double CLLeaveTaken = Double.parseDouble(empLeaveMap.get("CLLeaveTaken").toString());
		double leaveExcess = Double.parseDouble(empLeaveMap.get("leaveExcess").toString());
		double TRAExcess = Double.parseDouble(empLeaveMap.get("TRAExcess").toString());
		double HRAExcess = Double.parseDouble(empLeaveMap.get("HRAExcess").toString());
		double CLAExcess = Double.parseDouble(empLeaveMap.get("CLAExcess").toString());
		double MAExcess =Double.parseDouble(empLeaveMap.get("MAExcess").toString());
		double WAExcess = Double.parseDouble(empLeaveMap.get("WAExcess").toString());
		long leaveType = Long.parseLong(empLeaveMap.get("leaveType").toString());
		double LWPInmonth = Double.parseDouble(empLeaveMap.get("LWPInmonth").toString());
		double LWPExcess = Double.parseDouble(empLeaveMap.get("LWPExcess").toString());
		int LWPFlag = Integer.parseInt(empLeaveMap.get("LWPFlag").toString());
		
		logger.info("from core logic the LWPExcess "+LWPExcess);
		logger.info("from core logic the LWPInmonth "+LWPInmonth);
		logger.info("from core logic the LWPFlag "+LWPFlag);
		
		InputVO.setLWPExcess(LWPExcess);
		InputVO.setLWPFlag(LWPFlag);
		InputVO.setLWPInMonth(LWPInmonth);
		
		
		if(LWPFlag==1)
		{
				CurrBasic = 0;
		}
		else
		{
			if(payCommissionId==sixthPayCommId)
			{
				CurrBasic = otherdetail.getotherCurrentBasic();
			}
			else
			{
				CurrBasic = otherDtlsVOHst.getotherCurrentBasic();
			}
		}
		
		
		logger.info("the currBasic "+CurrBasic);
		logger.info("the city "+city);
		
		logger.info("the grade  "+grade);
		logger.info("the isHandicapped "+isHandicapped);
		logger.info("the quater_id "+quater_id);
		logger.info("the scale_start_amt "+scale_start_amt);
		logger.info("the vpf "+vpf);
		
		if(leaveExcess<0)
			leaveExcess=0;
			
		//Added by Paurav for processing Fixed Pay Employee
		logger.info("Employee Type for emp Id " + empid + " is " + empType);
		
		InputVO.setEmpType(empType);
		//Ended by Paurav
		
		hrPayVpfDtlsList= empVPFDao.getHrPayVpfByCode(empid,monthGiven-1,yearGiven);
		hrPayVpfDtlsHst = new HrPayVpfDtlsHst();
		if(hrPayVpfDtlsList!=null && hrPayVpfDtlsList.size()>0)
		{
			hrPayVpfDtlsHst = hrPayVpfDtlsList.get(0);
			vpf=(long)hrPayVpfDtlsHst.getVpfAmt();
			ZerovpfMonths=hrPayVpfDtlsHst.getZerovpfMonths();
		}
		
		
		OrgEmpMst orgEmpMst = empInfoDao.read(empid).getOrgEmpMst();
		logger.info(ZerovpfMonths+"zerovpfMonths orgEmpMst.getEmpSrvcExp()" + orgEmpMst.getEmpSrvcExp());
			InputVO.setZerovpfMonths(ZerovpfMonths);
			InputVO.setDoj(orgEmpMst.getEmpDoj());
			//InputVO.setSis1979Flag(hrEisOtherDtlsHst.getSis1979Flag()!=null?hrEisOtherDtlsHst.getSis1979Flag():"n");
			InputVO.setGpfGradeValue(gpfGrade);
			InputVO.setEmpSrvcExp(orgEmpMst.getEmpSrvcExp());
		    InputVO.setVpf(vpf);
			
			InputVO.setBasic(CurrBasic);
			InputVO.setCity(city);
			InputVO.setCLLeaveBal(CLLeaveBal);/////////NoTE: CLLeaveBal doenot come in processing till now
			InputVO.setCLLeaveTaken(CLLeaveTaken);
			InputVO.setLeaveExcess(leaveExcess);
			InputVO.setTRAExcess(TRAExcess);
			InputVO.setHRAExcess(HRAExcess);
			InputVO.setCLAExcess(CLAExcess);
			InputVO.setMAExcess(MAExcess);
			InputVO.setWAExcess(WAExcess);
			InputVO.setLeaveType(leaveType);
			InputVO.setGrade(grade);
			InputVO.setDesignation(Designation);
			InputVO.setIsHandicapped(isHandicapped);
			InputVO.setGpfAccountFlag(gpfAcctFlag);
			InputVO.setScale_start_amt(scale_start_amt);
			//by manoj for sis issue
			InputVO.setOld_scale_start_amt(old_scale_start_amt);
			//end by manoj for sis issue
			//Added by Paurav for Medical Allowance and Uniform Availed 
			InputVO.setMedAllowance(hrEisOtherDtlsHst.getMedAllowance());
			InputVO.setUniformAvailed(hrEisOtherDtlsHst.getUniformAvailed());
		//Ended By Paurav
		

			 long daValue = 0;
			 long dpValueWholeMonth=0;
			 long cpfValue = 0;
				double pt = 0;
//			 Modify by Urvin Shah For Six-Pay
			String salaryRuleClassPath ="com.tcs.sgv.allowance.service.SalaryRules";
			if(hrEisOtherDtlsHst.getHrEisSgdMpg()!=null){
				salaryRuleClassPath = otherdetail.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getRuleEngineClassPath();
			}
			PaybillOutputVO payBillOut  = new PaybillOutputVO();
			Class salRules = null;
			Constructor constructor =	null;
			Object invoker =null; 
			
			String methodName="generateGrossPay";
			try
			{
			salRules = Class.forName(salaryRuleClassPath);
			constructor =	salRules.getConstructor(null);
			invoker =constructor.newInstance(null); 
			Method methodGenGrossPay = salRules.getMethod(methodName, new Class[]{PaybillInputVO.class});
			Object[] objInputVO = new Object[1];
			objInputVO[0] =InputVO;
			payBillOut= (PaybillOutputVO) methodGenGrossPay.invoke(invoker,objInputVO);
			}
			catch(Exception e)
			{
				logger.info("Exception from arrearbill service core logic for generateGrossPay calculation "+e);
				
			}
		
		double revisedBasic=Math.round(payBillOut.getRevisedBasic());
		
		HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
		
		edpList = hrEdp.getAllData();
		
			
		HrPayEdpCompoMpg edpComp;
		Parser parExpression = new Parser();
		ExpressionMasterDAOImpl allowExpDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
		
		DeducExpMasterDAOImpl deducExpDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
		
		ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(HrPayComponentMst.class,serv.getSessionFactory());
		List<HrPayComponentMst> componentList =componentDAO.getListByColumnAndValue("componentDesc", "DP");
		
		// Added By Urvin Shah For Rounding Figure.
		hrEdp.getListByColumnAndValue("cmnLookupId",new Long(dpLookupId));
		String dpExpr = componentList.get(0).getExpression();
		//added by Ankit Bhatt - Used in Deduction calculation for calculating DA for whole Month in case of 10-20 days issues.
		String dpExprWholeMonth = componentList.get(0).getExpression();
		//ended by Ankit Bhatt.
		
	
		
        
        logger.info("the new revisedBasic after 10-20 issue is "+revisedBasic);
		
		dpExpr = dpExpr.replaceAll("BASIC", String.valueOf(revisedBasic));
		logger.info("dpExpr is  " + dpExpr);
		dpValue = Math.round(parExpression.stringParse(dpExpr));
	
		
		logger.info("dpvalue is "+dpValue);
		// End by Urvin Shah	
		
		
		for(int i=0; i< edpList.size(); i++)
		{
			try
			{
			edpComp = new HrPayEdpCompoMpg();
			edpComp = edpList.get(i);
			
			String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
			
            
//			added by Ankit Bhatt for Outer Integration
			String typeEdpId = String.valueOf(edpComp.getRltBillTypeEdp().getTypeEdpId());
			String addedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
			String edpCat = edpComp.getRltBillTypeEdp().getEdpCategory();
			String exp_rec_rec = edpComp.getRltBillTypeEdp().getExpRcpRec();
			logger.info("Edp code and category is " + edpCode + "--" + edpCat + "--" + addedFlag);
			
			
			
			if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("EXP"))
			{
			  proxyReq.removeParameter("txtAmt" + edpCode);
			  proxyReq.setParameter("hdPayEdpId", typeEdpId);
			  proxyReq.setParameter("hdPayAddDed", addedFlag);
			  proxyReq.setParameter("hdPayEdpCate", edpCat);
			  proxyReq.setParameter("hdPayEdpCode", edpCode);
			  logger.info("Inside the EXP loop of GeneratePaybill service");
			}
			else if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("RCP"))
			{				
				proxyReq.removeParameter("txtAmt" + edpCode);
			  proxyReq.setParameter("hdRcptEdpId", typeEdpId);
			  proxyReq.setParameter("hdRcptEdpCate", edpCat);
			  proxyReq.setParameter("hdRcptEdpCode", edpCode);
			  logger.info("Inside the RCP loop of GeneratePaybill service");
			}
			else if(exp_rec_rec!=null && !exp_rec_rec.equals("") && exp_rec_rec.equals("REC"))
			{		
			  proxyReq.removeParameter("txtRecAmt" + edpCode);
			  proxyReq.setParameter("hdRecEdpId", typeEdpId);
			  proxyReq.setParameter("hdRecAddDed", addedFlag);
			  proxyReq.setParameter("hdRecEdpCate", edpCat);
			  proxyReq.setParameter("hdRecEdpCode", edpCode);	
			}
			
			}
			catch(Exception e)
			{
				logger.info("this is error "+e);
			}
		}
		
		
		try{
		
			
		logger.info("the da id is "+DaId);
		//List daExprList  = allowExpDAO.getExpfromAllowCodes(","+DaId);
		List daExprList  = allowExpDAO.getExpfromAllowCodes(","+DaId,payCommissionId);
		if(daExprList!=null && daExprList.size()>0)
		{
		String daExprValue = ((HrPayExpressionMst)daExprList.get(0)).getRuleExpression();
		Long daRuleExprValue = ((HrPayExpressionMst)daExprList.get(0)).getRuleValue();
		
		
		
		
		
		
		logger.info(" ******************************************************** daExprList "+daExprValue+ " and rule value is "+daRuleExprValue);
//		added by Ankit Bhatt - Used in Deduction calculation for calculating DA for whole Month in case of 10-20 days issues.
		dpExprWholeMonth = dpExprWholeMonth.replaceAll("BASIC", String.valueOf(revisedBasic));
		logger.info("dpExpr for whole month DP is  " + dpExprWholeMonth + "revisedBasic Basic is " + revisedBasic);
		dpValueWholeMonth = Math.round(parExpression.stringParse(dpExprWholeMonth));
		//ended by Ankit Bhatt
		
		if(daExprValue!=null && !daExprValue.trim().equals(""))
		{
			
			//Double daCalculatedValue = salRules.calculateDA(empType);
			
			methodName="calculateDA";
			Method methodCalDA = salRules.getMethod(methodName,new Class[] {long.class});
			Object[] objArgs = new Object[1];
			objArgs[0] = empType;
			Double daCalculatedValue = (Double)methodCalDA.invoke(invoker, objArgs);
			
			daExprValue = daExprValue.replaceAll("x", String.valueOf(daCalculatedValue));
			daExprValue = daExprValue.replaceAll("DP", String.valueOf(dpValueWholeMonth));
			daExprValue = daExprValue.replaceAll("GP", String.valueOf(0));			// For Six-Pay (GP is put but not considered so put value 0).
			
			daExprValue = daExprValue.replaceAll("BASIC",String.valueOf(revisedBasic));
			logger.info("daExprValue for whole month DP is  " + daExprValue + "revisedBasic " + revisedBasic);
			logger.info("Employee Type for emp Id " + empid + " is " + empType);
			daValue= Math.round(parExpression.stringParse(daExprValue));
			
			
		}
		else if(daRuleExprValue!=null)
		{
			daValue=Math.round(daRuleExprValue);
		}
			
		
		
		}
		logger.info("the da value is before divide by maxday "+daValue+" and max day "+maxDay+" and days of post "+daysOfPost);	
		double daPerDay = Double.parseDouble(Long.toString(daValue)) /maxDay;
		logger.info("the daPerDay is before divide by maxday "+daPerDay);	
		
		daValue =Math.round( daPerDay*daysOfPost);
		
		if(LWPFlag==1)
		{
			daValue=0;
		}
		else
		{
			logger.info("LWP not changing allowance to zero for ");
		}
    
		
		logger.info("the da value is "+daValue);		
		
		payBillVO.setAllow0103(daValue)	;
			
		}
		catch(Exception e)
		{
			logger.info("Exception from arrearbill service core logic for calculateDA calculation "+e);
			
		}	
		
		
		try{
			
			
			
			
		logger.info("the cpf id is "+cpfId);
		//List daExprList  = allowExpDAO.getExpfromAllowCodes(","+DaId);
		List cpfExprList  = deducExpDAO.getExpfromDeducCodes(","+cpfId,payCommissionId);
		logger.info(" ******************************************************** cpfExprList "+cpfExprList);
		
		if(cpfExprList!=null && cpfExprList.size()>0)
		{
			String cpfExprValue = ((HrDeducExpMst)cpfExprList.get(0)).getDeducRuleExp();
			Long cpfRuleExprValue = ((HrDeducExpMst)cpfExprList.get(0)).getDeducRuleValue();
			logger.info(" ******************************************************** cpfExprList "+cpfExprList+ " and rule value is "+cpfRuleExprValue);
	//		added by Ankit Bhatt - Used in Deduction calculation for calculating DA for whole Month in case of 10-20 days issues.
			dpExprWholeMonth = dpExprWholeMonth.replaceAll("BASIC", String.valueOf(revisedBasic));
			logger.info("dpExpr for whole month DP is  " + dpExprWholeMonth + "revisedBasic Basic is " + revisedBasic);
			dpValueWholeMonth = Math.round(parExpression.stringParse(dpExprWholeMonth));
			//ended by Ankit Bhatt
			
				if(cpfExprValue!=null && !cpfExprValue.trim().equals(""))
				{
					
					//Double daCalculatedValue = salRules.calculateDA(empType);
					
					methodName="calculateCPF";
					Method methodCalDA = salRules.getMethod(methodName,new Class[] {long.class,Date.class});
					Object[] objArgs = new Object[2];
					objArgs[0] = empType;
					objArgs[1] = orgEmpMst.getEmpDoj();
					Double daCalculatedValue = (Double)methodCalDA.invoke(invoker, objArgs);
					
					cpfExprValue = cpfExprValue.replaceAll("x", String.valueOf(daCalculatedValue));
					cpfExprValue = cpfExprValue.replaceAll("DP", String.valueOf(dpValueWholeMonth));
					cpfExprValue = cpfExprValue.replaceAll("GP", String.valueOf(0));	// For Six-Pay (GP is put but not considered so put value 0).
					cpfExprValue = cpfExprValue.replaceAll("DA", String.valueOf(payBillVO.getAllow0103()));
					
					cpfExprValue = cpfExprValue.replaceAll("BASIC",String.valueOf(revisedBasic));
					logger.info("daExprValue for whole month DP is  " + cpfExprValue + "revisedBasic is " + revisedBasic);
					cpfValue= Math.round(parExpression.stringParse(cpfExprValue));
					
					
				}
				else if(cpfRuleExprValue!=null)
				{
					cpfValue=Math.round(cpfRuleExprValue);
				}
				
			
			
		}
		logger.info("the cpfValue value is before divide by maxday "+cpfValue+" and max day "+maxDay+" and days of post "+daysOfPost);	
		double cpfPerDay = Double.parseDouble(Long.toString(cpfValue)) /maxDay;
		logger.info("the cpfValueperday is before divide by maxday "+cpfPerDay);	
		
		cpfValue =Math.round( cpfPerDay*daysOfPost);
		
		if(LWPFlag==1)
		{
			cpfValue=0;
		}
		else
		{
			logger.info("LWP not changing allowance to zero for ");
		}
    
				
		payBillVO.setDeduc9534(cpfValue);
			
		}
		catch(Exception e)
		{
			logger.info("Exception from arrearbill service core logic for calculateCPF calculation "+e);
			
		}	
			
       try{
        
    	   logger.info("before calculating pt the daValue is "+daValue +" and oldPaybill.getAllow0103() is "+oldPaybill.getAllow0103());
    	   double grossPay = 0;
    	   //(oldPaybill.getGrossAmt() + (daValue -oldPaybill.getAllow0103())+(oldPaybill.getAdv7057()+oldPaybill.getAdv7058()+oldPaybill.getDeduc0101()) );//hard coded
	
		
			logger.info("before calculating pt the gross pay is "+grossPay +" and maxDaysFlag is "+maxDaysFlag);
			logger.info("before going to calculate pt ");
			methodName="calculatePT";
			Method methodcalculatePT = salRules.getMethod(methodName, new Class[]{double.class,long.class,boolean.class});
			Object[] objArgs = new Object[3];
			objArgs[0] =grossPay;
			objArgs[1] =0;
			objArgs[2] =isHandicapped;
			//PaybillOutputVO payBillOut = (PaybillOutputVO) methodGenGrossPay.invoke(invoker,objInputVO);
			pt = Math.round((Double)methodcalculatePT.invoke(invoker, objArgs));//(grossPay,0,isHandicapped));//not generic method 0 is empType as the pt will be deducted for all type of emp
			//pt = Math.round(salRules.calculatePT(grossPay,0,isHandicapped));//not generic method 0 is empType as the pt will be deducted for all type of emp
			
		//not generic method 0 is empType as the pt will be deducted for all type of emp
			logger.info("after going to calculate pt "+pt);
			
	//	}
		
		/**
		***************************************/
			}
		catch(Exception e)
		{
			logger.info("Exception from arrearbill service core logic for pt calculation "+e);
			
		}
				
	
		logger.info("pt ********* the param value for edpCode 9570 is "+pt);
	
		payBillVO.setDeduc9570(pt);
		
		EdpDtlsVO ptVO = new EdpDtlsVO();
			ptVO.setEdpCode("9570");
			ptVO.setAmount(pt);
			ptVO.setAddDedFlag("-(d)");
			ptVO.setExpRcpRec("RCP");
			paramMap.put(28L,ptVO);
			
			 //paramMap.put("9570", pt);
        
		
		
		//for emp mst 
		HrEisEmpMst hrEisObj=new HrEisEmpMst();            
		GenericDaoHibernateImpl gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
        gImpl.setSessionFactory(serv.getSessionFactory());
        hrEisObj = (HrEisEmpMst) gImpl.read(empid);
				        
        payBillVO.setHrEisEmpMst(hrEisObj);
        
        logger.info("post is to be set is "+orgUPRlt.getOrgPostMstByPostId().getPostId());
        payBillVO.setOrgPostMst(orgUPRlt.getOrgPostMstByPostId());

       

//      Added By Mrugesh
		PaybillHeadMpg paybillHeadMpg = new PaybillHeadMpg();

        
		//if(locId == 300022)
        {
	        psrNo = paybillDAO.getPsrNoFromPostId(orgUPRlt.getOrgPostMstByPostId().getPostId());
	        logger.info("psr to be set is "+psrNo);
	        payBillVO.setPsrNo(psrNo);
        }
        //for setting the current month and year
        //edited by Ankit Bhatt for setting given month & year
        if(yearGiven!=-1 && monthGiven!=-1 )
        {
        	paybillHeadMpg.setMonth(monthGiven);
        	paybillHeadMpg.setYear(yearGiven);
        }
        else
        {
	        SimpleDateFormat sdf = new SimpleDateFormat("MM");
	        Date date = new Date();
	        String month= sdf.format(date);
	        
	        if(month.charAt(0)=='0')
	        {
	        	month=month.substring(1,month.length());
	        }
	        
	        
	        sdf = new SimpleDateFormat("yyyy");
	        String year= sdf.format(date);
	        
	        paybillHeadMpg.setMonth(Integer.parseInt(month));
	        paybillHeadMpg.setYear(Integer.parseInt(year));
        }
        //edit ended by Ankit Bhatt.
        
        // Added By Urvin shah
        payBillVO.setIt(incomeTax);//income tax
        // Ended By Urvin shah.
        
			
		
		
		 logger.info("***********************************paramMap is "+paramMap);
		resultMap.put("payBillVO",payBillVO);
		resultMap.put("gpfGrade",gpfGrade);
		resultMap.put("doj",orgEmpMst.getEmpDoj());
		//resultMap.put("paramMap",paramMap);	
		resultMap.put("proxyReq",proxyReq);
		//Added by Mrugesh
		resultMap.put("paybillHeadMpgVO",paybillHeadMpg);
		//Ended by Mrugesh
		}
		else
		{
			resultMap.put("postId",orgUPRlt.getOrgPostMstByPostId().getPostId());
			logger.info("Vacant Post in Core Logic ");
			
		}
		return resultMap;		
		}
}
