package com.tcs.sgv.eis.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.allowance.dao.ComponentMstDAOImpl;
import com.tcs.sgv.allowance.dao.ExpressionMasterDAOImpl;
import com.tcs.sgv.allowance.service.Parser;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.allowance.valueobject.HrPayComponentMst;
import com.tcs.sgv.allowance.valueobject.HrPayExpressionMst;
import com.tcs.sgv.allowance.valueobject.PaybillInputVO;
import com.tcs.sgv.allowance.valueobject.PaybillOutputVO;
import com.tcs.sgv.common.util.HttpServletRequestProxy;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.deduction.dao.DeducExpMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducExpMst;
import com.tcs.sgv.eis.dao.CityCatMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
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
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpgHst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtlsHst;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.ScaleCommissionMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

public class GenerateArrearsBillCoreLogic {
	
	Log logger = LogFactory.getLog(getClass());
	
	public Map executeCoreLogic(Map inputMap)
	{
		Map resultMap = inputMap;
		if(resultMap.containsKey("payBillVO"))
			resultMap.remove("payBillVO");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		
		
		long allowLookupId = Long.parseLong(resourceBundle.getString("allowLookupId"));
		long deducLookupId = Long.parseLong(resourceBundle.getString("deducLookupId"));
		long dpLookupId = Long.parseLong(resourceBundle.getString("dpLookupId"));
		long basicLookupId = Long.parseLong(resourceBundle.getString("basicLookupId"));
		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId")); 
		
		int contractEmpType = Integer.parseInt(resourceBundle.getString("contract"));
		int fixedEmpType = Integer.parseInt(resourceBundle.getString("fixed"));
		
		
		long DaId = Long.parseLong(resourceBundle.getString("daCode"));
		long GradeCode3=Long.parseLong(constantsBundle.getString("GradeCode3"));
        long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));
       
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
		
		int monthGiven = Integer.parseInt(inputMap.get("monthGiven").toString());
		int yearGiven = Integer.parseInt(inputMap.get("yearGiven").toString());
		
		InputVO.setMonth(monthGiven);
		InputVO.setYear(yearGiven);
		
		OrgUserpostRlt orgUPRlt = (OrgUserpostRlt)inputMap.get("orgUPRlt");
		HrEisOtherDtlsHst hrEisOtherDtlsHst = (HrEisOtherDtlsHst)inputMap.get("hrEisOtherDtlsHst");
		
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		
		CityCatMpgDAOImpl cityCatDAO= new CityCatMpgDAOImpl(CityCatMpg.class,serv.getSessionFactory());
		List<CityCatMpg> cityCatVO = new ArrayList();
		
		long empCurrentStatus=1;//hardcoded for getting the active allowance of the emp
		EmpAllwMapDAOImpl empAllowDAO = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
		List<HrPayEmpallowMpgHst> empAllowVOList = new ArrayList();
		
			
		List<HrPayEdpCompoMpg> edpList= new ArrayList();
		
		VoluntryPFDAOImpl empVPFDao = new VoluntryPFDAOImpl(HrPayVpfDtls.class, serv.getSessionFactory());
		List<HrPayVpfDtlsHst> hrPayVpfDtlsList = new ArrayList();
		HrPayVpfDtlsHst hrPayVpfDtlsHst = new HrPayVpfDtlsHst();
		
		
		List postIdList = (List)inputMap.get("postIdList");
		
		long spID = Long.parseLong(inputMap.get("spID").toString());
		long ppID = Long.parseLong(inputMap.get("ppID").toString());
		
		maxDaysUserPostRltId = Long.parseLong(inputMap.get("maxDaysUserPostRltId").toString());
				
		//HttpServletRequest request = (HttpServletRequest)inputMap.get("requestObj");
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
					}
					else // start date is not in current month but end date is in current month.
				    {
						daysOfPost = endDays;
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
					}
					else	// if whole current month is not between the start and end date.
				    {
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

		if(maxDaysUserPostRltId==orgUPRlt.getEmpPostId())
		{
			maxDaysFlag=1;
		}
		logger.info("The day diff is "+daysOfPost+" the maxDaysFlag "+maxDaysFlag);
		//end by manoj form 10-20 day issue
		
		long specialPay=0;
		long vpf=0;
		long CurrBasic=0;
		long cityId=0;
		String city="";
		int grade=0;
		int Designation=0;
		long scale_start_amt=0;
		long old_scale_start_amt=0;
		int quater_id=0;
		//Added By Urvin Shah. for Quarter Rate Type.
		// Ended By Urvin shah.
		
		boolean isHandicapped=true;
		//by manoj for gpf Account detail
		int gpfAcctFlag=0;
		//end by manoj for gpf Account detail
		
        long incomeTax=0;
		//long gpf_adv=0;
		long perpay=0;
		//Added by Paurav for Medical Allowance and Uniform Availed
	//Ended by Paurav
		
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
		}
		else if(payCommissionId!=0){			// For Contractual,Fix Pay SGD is null
			ScaleCommissionMpgDAO scaleCommMpgDao = new ScaleCommissionMpgDAOImpl(ScaleCommissionMpg.class,serv.getSessionFactory());
			ScaleCommissionMpg scaleCommMpg =  scaleCommMpgDao.getNewScaleFromOldScale(hrEisOtherDtlsHst.getHrEisSgdMpg().getHrEisScaleMst().getScaleId());
			gradePay = scaleCommMpg.getCommissionSix().getScaleGradePay();
			InputVO.setGradePay(gradePay);
		}
		else 			// SGD is null so gradePay=0
		{
			InputVO.setGradePay(0);
		}
		
		
		
		//end by manoj for sis issue
		
		
		List empInfoHstList = empInfoDao.getAllEmpDataFromHst(empid,locId,monthGiven-1,yearGiven);
		
		HrEisEmpMstHst hrEisEmpMstHst = (HrEisEmpMstHst)empInfoHstList.get(0);
		
		HrEisOtherDtlsHst otherDtlsVOHst = hrEisOtherDtlsHst;
		
		CurrBasic=hrEisOtherDtlsHst.getotherCurrentBasic();
		
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
			scale_start_amt = hrEisOtherDtlsHst.getotherCurrentBasic();
		}
		
		//by manoj for tra update
		
		
		Map vehiAvailedMap = billService.calculateTraDays(empid,serv,monthGiven,yearGiven);
		
		double taDistance = Double.parseDouble(vehiAvailedMap.get("taDistance").toString());
		Boolean isVehiAvailed = new Boolean(vehiAvailedMap.get("isVehiAvailed").toString());
		long totalVehicalAvailDays=Long.parseLong( vehiAvailedMap.get("totalVehicalAvailDays").toString());
		
		logger.info("is vehical availed is "+isVehiAvailed);
		//end by manoj for tra update
		InputVO.setVechAvailed(isVehiAvailed);
		InputVO.setTaDistance(taDistance);
		
		//end by manoj for HrEmpTraMpg related issues
		
		//added by manoj for quarter related issues
		
		List quaterIdList = new ArrayList();
		List quaterDaysList = new ArrayList();
		List lstQuarterRate = new ArrayList();
		
		RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
		long userIdForQtr = otherDtlsVOHst.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
		
		inputMap.put("userIdForQtr",userIdForQtr);
		
		inputMap.put("monthGiven",monthGiven);
		inputMap.put("yearGiven",yearGiven);
		Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(inputMap);
		
		quaterIdList = (List)hrrAndHraMap.get("quaterIdList");
		quaterDaysList = (List)hrrAndHraMap.get("quaterDaysList");
		lstQuarterRate = (List)hrrAndHraMap.get("lstQuarterRate");
		
		
		InputVO.setQuater_id(quater_id);
		InputVO.setQuaterDaysList(quaterDaysList);
		InputVO.setQuaterIdList(quaterIdList);
		InputVO.setLstQuarterRate(lstQuarterRate);
		
		long totalHrrDays=0;
		
		for(int count=0;count<quaterDaysList.size();count++)
		{
			totalHrrDays+=(Long)quaterDaysList.get(count);
		}
		
		logger.info("totalHrrDays "+totalHrrDays);
		
		logger.info("the quater id from hrEssquarterMst is "+quater_id+" the list size is "+quaterIdList.size());

		isHandicapped = Boolean.parseBoolean(hrEisOtherDtlsHst.getPhyChallenged().toLowerCase());
		incomeTax=Math.round(hrEisOtherDtlsHst.getIncomeTax());
		
		
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
		long HPL=Long.parseLong(constantsBundle.getString("HPL"));
		double splExcess =Double.parseDouble(empLeaveMap.get("splExcess").toString());
		double persExcess = Double.parseDouble(empLeaveMap.get("persExcess").toString());
		double LWPInmonth = Double.parseDouble(empLeaveMap.get("LWPInmonth").toString());
		double LWPExcess = Double.parseDouble(empLeaveMap.get("LWPExcess").toString());
		int LWPFlag = Integer.parseInt(empLeaveMap.get("LWPFlag").toString());
		
		logger.info("from core logic the LWPExcess "+LWPExcess);
		logger.info("from core logic the LWPInmonth "+LWPInmonth);
		
		InputVO.setLWPExcess(LWPExcess);
		InputVO.setLWPFlag(LWPFlag);
		InputVO.setLWPInMonth(LWPInmonth);
		
		
		//edit ended by Ankit Bhatt.
		//Ended by Paurav
		

		
		//for special pay
		empAllowVOList = empAllowDAO.getEmpallowMpgFromHst(empid, spID, empCurrentStatus,monthGiven-1,yearGiven);						
		if(empAllowVOList.size()>0)
		{					
	
			specialPay=(long)empAllowVOList.get(0).getEmpAllowAmount();
			
			// Added By Urvin shah.
			//specialPay = Math.round(((double)specialPay * daysOfPost) / noOfDays);	// Calculating the Special Pay according to the days on post.
			// Ended By Urvin shah.
		}
		
		//for personal pay
		empAllowVOList=empAllowDAO.getEmpallowMpgFromHst(empid, ppID, empCurrentStatus,monthGiven-1,yearGiven);
		
		if(!empAllowVOList.equals(null) && empAllowVOList.size()>0)
		{
			perpay=(long)empAllowVOList.get(0).getEmpAllowAmount();							
		//	perpay = Math.round(((double)perpay * daysOfPost )/ noOfDays);  //Calculating the Personal Pay according to the days on post.
			
		}
		
		hrPayVpfDtlsList= empVPFDao.getHrPayVpfByCode(empid,monthGiven-1,yearGiven);
		hrPayVpfDtlsHst = new HrPayVpfDtlsHst();
		if(hrPayVpfDtlsList!=null && hrPayVpfDtlsList.size()>0)
		{
			hrPayVpfDtlsHst = hrPayVpfDtlsList.get(0);
			vpf=(long)hrPayVpfDtlsHst.getVpfAmt();
			ZerovpfMonths=hrPayVpfDtlsHst.getZerovpfMonths();
		}
		
		
						
		/*logger.info("the calculate gpf_adv is "+gpf_adv);*/
		logger.info("the city "+city);
		
		logger.info("the isHandicapped "+isHandicapped);
		logger.info("the scale_start_amt "+scale_start_amt);
		
		if(leaveExcess<0)
			leaveExcess=0;
		
		
		InputVO.setEmpType(empType);
		//Ended by Paurav
		
		
		
		OrgEmpMst orgEmpMst = empInfoDao.read(empid).getOrgEmpMst();
		logger.info(ZerovpfMonths+"zerovpfMonths orgEmpMst.getEmpSrvcExp()" + orgEmpMst.getEmpSrvcExp());
			InputVO.setZerovpfMonths(ZerovpfMonths);
			InputVO.setDoj(orgEmpMst.getEmpDoj());
			//InputVO.setSis1979Flag(hrEisOtherDtlsHst.getSis1979Flag()!=null?hrEisOtherDtlsHst.getSis1979Flag():"n");
			
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
			//by manoj for gpf_accont details
			InputVO.setGpfAccountFlag(gpfAcctFlag);
			//end by manoj for gpf_accont details
			InputVO.setScale_start_amt(scale_start_amt);
			//by manoj for sis issue
			InputVO.setOld_scale_start_amt(old_scale_start_amt);
			//end by manoj for sis issue
			//Added by Paurav for Medical Allowance and Uniform Availed 
			InputVO.setMedAllowance(hrEisOtherDtlsHst.getMedAllowance());
			InputVO.setUniformAvailed(hrEisOtherDtlsHst.getUniformAvailed());
		//Ended By Paurav
		
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
				logger.info("Exception in arrear bill generation for generate gross pay"+e);
			}
		
		
		if(LWPFlag!=1)
		{
			payBillOut.setAllow0101(perpay);//temporary
			payBillOut.setAllow0102(specialPay);//temporary
			payBillOut.setGradePay(gradePay);
		}
			
			
		//		leave logic for HPL start
		if(leaveType==HPL)
		{	
			double Perpay=payBillOut.getAllow0101();
			double SpecialPay=payBillOut.getAllow0102();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, yearGiven);
			cal.set(Calendar.MONTH,(monthGiven-1));
			int getMaxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

			Perpay=Math.round(Perpay-Perpay*persExcess/getMaxDays);	
			SpecialPay=Math.round(SpecialPay-SpecialPay*splExcess/getMaxDays);
		    payBillOut.setAllow0101(Perpay);
		    payBillOut.setAllow0102(SpecialPay);
		}	
		//leave logic for HPL end
		//by manoj for jeep rent as deduc
		
		double totAllow = 0,totDeduc = 0,totAdv = 0,totLoan=0,leaveSalary=0;
		double revisedBasic=Math.round(payBillOut.getRevisedBasic());
		
		leaveSalary = Math.round(payBillOut.getLeaveSalary());
		
		HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
		
		edpList = hrEdp.getAllData();
		
			
		HrPayEdpCompoMpg edpComp;
		Parser parExpression = new Parser();
		ExpressionMasterDAOImpl allowExpDAO = new ExpressionMasterDAOImpl(HrPayExpressionMst.class,serv.getSessionFactory());
		List<HrPayExpressionMst> allowExpList = new ArrayList();
		
		DeducExpMasterDAOImpl deducExpDAO = new DeducExpMasterDAOImpl(HrDeducExpMst.class,serv.getSessionFactory());
		List<HrDeducExpMst> deducExpList = new ArrayList();
		
		
		//for pay of officer and pay of establishment by manoj
		List<HrPayEdpCompoMpg> basicList = hrEdp.getListByColumnAndValue("cmnLookupId",new Long(basicLookupId));
		
		ComponentMstDAOImpl componentDAO = new ComponentMstDAOImpl(HrPayComponentMst.class,serv.getSessionFactory());
		List<HrPayComponentMst> componentList =componentDAO.getListByColumnAndValue("componentDesc", "DP");
		
		// Added By Urvin Shah For Rounding Figure.
		hrEdp.getListByColumnAndValue("cmnLookupId",new Long(dpLookupId));
		String dpExpr = componentList.get(0).getExpression();
		String hradpExpr = componentList.get(0).getExpression();
		//added by Ankit Bhatt - Used in Deduction calculation for calculating DA for whole Month in case of 10-20 days issues.
		String dpExprWholeMonth = componentList.get(0).getExpression();
		//ended by Ankit Bhatt.
		
		if(LWPFlag==1)
		{
			if(payCommissionId==sixthPayCommId)
			{
				revisedBasic = otherdetail.getotherCurrentBasic();
			}
			else
			{
				revisedBasic = otherDtlsVOHst.getotherCurrentBasic();
			}
		}
		if(payCommissionId==sixthPayCommId)
		{
			revisedBasic = otherdetail.getotherCurrentBasic();
		}
     	revisedBasic = Math.round((revisedBasic/maxDay) * daysOfPost);
        
        logger.info("the new revisedBasic after 10-20 issue is "+revisedBasic);
		
		dpExpr = dpExpr.replaceAll("BASIC", String.valueOf(revisedBasic));
		dpValue = Math.round(parExpression.stringParse(dpExpr));
	
		
		// End by Urvin Shah	
		if(basicList!=null && basicList.size()>0)
		{
			
			for(int cntBasic=0;cntBasic<basicList.size();cntBasic++)
			{
				try
				{
				edpComp = new HrPayEdpCompoMpg();
				
				edpComp = basicList.get(cntBasic);
				
				String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
				String edpAddDedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
				long typeEdpId = edpComp.getRltBillTypeEdp().getTypeEdpId();
				String expRcpRec = edpComp.getRltBillTypeEdp().getExpRcpRec();
				
				Double  value;
				double basic;
				String mthdName = "getBasic"+edpCode;
				String payBillMthdName = "setBasic"+edpCode;
				Class pay = payBillOut.getClass();
				Method payMthd = pay.getMethod(mthdName, null);
				value = (Double)payMthd.invoke(payBillOut, null);
				basic = Math.round(value);
				//value=basic;
				value = (double)Math.round((basic/maxDay)*daysOfPost);
				//value= (double)Math.round(value);
				
				
				Class payBill = payBillVO.getClass();
				Object[] objArgs = new Object[]{new Double(value)};
				Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[]{double.class});
				payBillMthd.invoke(payBillVO, objArgs);
				logger.info("the value for "+payBillMthdName+" is "+value);
				
				if(paramMap.containsKey(typeEdpId))
				{
					EdpDtlsVO edpDtlsVO = (EdpDtlsVO)paramMap.get(typeEdpId);
					double existValue = edpDtlsVO.getAmount();
					existValue+=value;
					edpDtlsVO.setEdpCode(edpCode);
					edpDtlsVO.setAmount(existValue);
					edpDtlsVO.setAddDedFlag(edpAddDedFlag);
					edpDtlsVO.setExpRcpRec(expRcpRec);
					//paramMap.put(edpCode,existValue);
					paramMap.put(typeEdpId,edpDtlsVO);
				}
				else
				{
					EdpDtlsVO edpDtlsVO = new EdpDtlsVO();
					edpDtlsVO.setEdpCode(edpCode);
					if((GradeCode3==orgEmpMst.getOrgGradeMst().getGradeId() || GradeCode4==orgEmpMst.getOrgGradeMst().getGradeId())) {
						if(edpCode.equals("0102"))
							edpDtlsVO.setAmount(value+leaveSalary);
						else
							edpDtlsVO.setAmount(value);
					}
					else
					{
						if(edpCode.equals("0101"))
							edpDtlsVO.setAmount(value+leaveSalary);
						else
							edpDtlsVO.setAmount(value);
					}
					edpDtlsVO.setAddDedFlag(edpAddDedFlag);
					edpDtlsVO.setExpRcpRec(expRcpRec);
					paramMap.put(typeEdpId,edpDtlsVO);
				  //paramMap.put(edpCode,value);
				}

				}
				catch(Exception e)
				{
					logger.error("Error is: "+ e.getMessage());
				}
			}
		
		}
		
		//end for pay of officer and pay of establishment by manoj
//		end for pay of officer and pay of establishment by manoj
		double dpValueForHraAndCla=dpValue;	
		double gpValueForHraAndCla=0;
		double revisedBasicForHraAndCla= revisedBasic;
		
		long isOneTime = 0;//for 10-20 days issues by manoj
		for(int i=0; i< edpList.size(); i++)
		{
			try
			{
			edpComp = new HrPayEdpCompoMpg();
			EdpDtlsVO  edpDtlsVO = new EdpDtlsVO(); //custom VO which contains Edp-Code, Amount and Flag for Allowance/Deduction
			edpComp = edpList.get(i);
			
			//for 10-20 days issues by manoj
			isOneTime = edpComp.getIsOneTime();//1 means not oneTime and 0 means one time
			
			String edpAddDedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
			String edpCode = String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode());
			long compoType = edpComp.getCmnLookupId();
			String compoCode = edpComp.getTypeId();
			
            
//			added by Ankit Bhatt for Outer Integration
			String typeEdpId = String.valueOf(edpComp.getRltBillTypeEdp().getTypeEdpId());
			String addedFlag = edpComp.getRltBillTypeEdp().getAddDedFlag();
			String edpCat = edpComp.getRltBillTypeEdp().getEdpCategory();
			String exp_rec_rec = edpComp.getRltBillTypeEdp().getExpRcpRec();
			String expRcpRec = edpComp.getRltBillTypeEdp().getExpRcpRec();
			logger.info("Edp code and category is " + edpCode + "--" + edpCat + "--" + addedFlag);
			
						
			Double  value;
			
			if(compoType==allowLookupId || compoType==dpLookupId)//for Allowances or for basic components
			{
				String mthdName = "getAllow"+edpCode;
				String payBillMthdName = "setAllow"+edpCode;
				Class pay = payBillOut.getClass();
				Method payMthd = pay.getMethod(mthdName, null);
				value = (Double)payMthd.invoke(payBillOut, null);
				
				double allowance = 0;
				String resultvalue="";
				
				allowExpList =allowExpDAO.getExpfromAllowCodes(","+compoCode,payCommissionId);
				String expr = "";
				
				if(compoType==allowLookupId && allowExpList.size()>0) 
				{
					expr = allowExpList.get(0).getRuleExpression();
					resultvalue=String.valueOf(allowExpList.get(0).getRuleValue());
				}
				if(compoType==dpLookupId)
				{					
					if(empType == contractEmpType || empType == fixedEmpType)
					{
						expr = "";
						resultvalue = "";
						value = 0.0;
					}
					else
						if(LWPFlag==1)
						{
							value=0.0;
						}
						else
						{
							value=dpValue;
						}
						//Ended By Urvin shah
				}
				dpValueForHraAndCla=dpValue;
				revisedBasicForHraAndCla= revisedBasic;
				//if(LWPFlag==1 && (edpCode.equals("0111") || edpCode.equals("0110")))
				if((edpCode.equals("0111") || edpCode.equals("0110")))
				{
					revisedBasicForHraAndCla = otherDtlsVOHst.getotherCurrentBasic();
					//Added by Mrugesh for HRA amt problem in 10 - 20 days
					if(isOneTime == 0)
						revisedBasicForHraAndCla =Math.round((revisedBasicForHraAndCla /maxDay) *daysOfPost);
					//Ended by Mrugesh
					hradpExpr = hradpExpr.replaceAll("BASIC", String.valueOf(revisedBasicForHraAndCla));
					logger.info("dpExpr is for HRA " + hradpExpr);
					dpValueForHraAndCla = Math.round(parExpression.stringParse(hradpExpr));
				}
				logger.info("from corelogic LWPFlag after setting from otherdetail  "+LWPFlag+" revisedBasic "+revisedBasicForHraAndCla);
				
				if(expr!=null && !expr.equals(""))//if expr is not null or not empty 
				{
					//Added By Urvin Shah For Rounding Figure.
					
					logger.info("DP Value is in allowances:-"+dpValueForHraAndCla);
					logger.info("GP Value is in allowances:-"+gpValueForHraAndCla);	
					expr = expr.replaceAll("DP", String.valueOf(dpValueForHraAndCla));
					expr = expr.replaceAll("GP", String.valueOf(gpValueForHraAndCla));
					// Ended By Urvin Shah
					
//					expr = expr.replaceAll("DP", componentList.get(0).getExpression());
					expr = expr.replaceAll("BASIC", String.valueOf(revisedBasicForHraAndCla));
					expr = expr.replaceAll("x", value.toString());
					allowance = Math.round(parExpression.stringParse(expr));
					
				}
				else if(resultvalue!=null && !resultvalue.equals(""))//if rule value is not null
				{
					allowance = Math.round(Double.parseDouble(resultvalue));
					
				}
				else //else the value is the allowance
				{
					allowance = Math.round(value);
				}
				logger.info("the expr for allowance is "+expr+"the calculated Allowance is "+allowance + " for the compoCode is "+compoCode);
				
				
				//for 10-20 days issues by manoj
				
				if(isOneTime == 0)	
				{
											
					
					if(compoType!=dpLookupId)
					{
						logger.info("The Allowance is:-"+Math.round( (allowance /maxDay) *daysOfPost));
						allowance =Math.round((allowance /maxDay) *daysOfPost); 
					}
				}
				logger.info("LWP hra is  "+payBillOut.getAllow0110()+" and cla is "+payBillOut.getAllow0111());
				if(LWPFlag==1 && !(edpCode.equals("0111") || edpCode.equals("0110")))
				{
					logger.info("LWP gonig to set allowance zero for "+edpCode);
					allowance=0;
				}
				
				
				totAllow = totAllow+allowance;

				logger.info("allowance and dp ********* the param value for edpCode "+edpCode + " is "+allowance);
				if(paramMap.containsKey(typeEdpId))
				{
					edpDtlsVO = (EdpDtlsVO)paramMap.get(typeEdpId);
					double existValue = edpDtlsVO.getAmount();
					existValue+=allowance;
					
					edpDtlsVO.setEdpCode(edpCode);
					edpDtlsVO.setAmount(existValue);
					edpDtlsVO.setAddDedFlag(edpAddDedFlag);
					edpDtlsVO.setExpRcpRec(expRcpRec);
					paramMap.put(typeEdpId,edpDtlsVO);
					
					//paramMap.put(edpCode,existValue);
				}
				else
				{
					edpDtlsVO.setEdpCode(edpCode);
					edpDtlsVO.setAmount(allowance);
					edpDtlsVO.setAddDedFlag(edpAddDedFlag);
					edpDtlsVO.setExpRcpRec(expRcpRec);
					paramMap.put(typeEdpId,edpDtlsVO);
					
				  //paramMap.put(edpCode,allowance);
				}
				
				//ended by Ankit Bhatt.
				
				logger.info("the expr for allowance is "+expr+"the calculated Allowance is "+allowance + " for the compoCode is "+compoCode);
				
				
				Class payBill = payBillVO.getClass();
				Object[] objArgs = new Object[]{new Double(allowance)};
				Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[]{double.class});
				payBillMthd.invoke(payBillVO, objArgs);
			}
			
			else if(compoType==deducLookupId)//for Deductions
			{
				String mthdName = "getDeduc"+edpCode;
				String payBillMthdName = "setDeduc"+edpCode;
				Class pay = payBillOut.getClass();
				Method payMthd = pay.getMethod(mthdName, null);
				value = (Double)payMthd.invoke(payBillOut, null);
				double deduction = 0;
				deducExpList = deducExpDAO.getExpfromDeducCodes(","+compoCode, payCommissionId);
				
				String expr = "";
				long deducValue=0;
				if(deducExpList.size()>0) 
				{
					expr = deducExpList.get(0).getDeducRuleExp();
					deducValue=deducExpList.get(0).getDeducRuleValue();
				}
				
				
				
				 long daValue = 0;
				 long dpValueWholeMonth=0;
				
				
					
				List daExprList  = allowExpDAO.getExpfromAllowCodes(","+DaId,payCommissionId);
				if(daExprList!=null && daExprList.size()>0)
				{
				String daExprValue = ((HrPayExpressionMst)daExprList.get(0)).getRuleExpression();
				Long daRuleExprValue = ((HrPayExpressionMst)daExprList.get(0)).getRuleValue();
				
//				added by Ankit Bhatt - Used in Deduction calculation for calculating DA for whole Month in case of 10-20 days issues.
				dpExprWholeMonth = dpExprWholeMonth.replaceAll("BASIC", String.valueOf(otherDtlsVOHst.getotherCurrentBasic()));
				logger.info("dpExpr for whole month DP is  " + dpExprWholeMonth + "Other Dtls Basic is " + otherDtlsVOHst.getotherCurrentBasic());
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
					
//					added by Ankit Bhatt - Used in Deduction calculation for calculating DA for whole Month in case of 10-20 days issues.
					dpExprWholeMonth = dpExprWholeMonth.replaceAll("BASIC", String.valueOf(otherDtlsVOHst.getotherCurrentBasic()));
					logger.info("dpExpr for whole month DP is  " + dpExprWholeMonth + "Other Dtls Basic is " + otherDtlsVOHst.getotherCurrentBasic());
					dpValueWholeMonth = Math.round(parExpression.stringParse(dpExprWholeMonth));
					//ended by Ankit Bhatt
					
					daExprValue = daExprValue.replaceAll("x", String.valueOf(daCalculatedValue));
					daExprValue = daExprValue.replaceAll("DP", String.valueOf(dpValueWholeMonth));
					daExprValue = daExprValue.replaceAll("GP", String.valueOf(0));			// For Six-Pay (GP is put but not considered so put value 0).
					
					daExprValue = daExprValue.replaceAll("BASIC",String.valueOf(otherDtlsVOHst.getotherCurrentBasic()));
					
					daValue= Math.round(parExpression.stringParse(daExprValue));
					
					
				}
				else if(daRuleExprValue!=null)
				{
					daValue=Math.round(daRuleExprValue);
				}
					
				}
				
						
				
				if(expr!=null && !expr.equals(""))
				{
					//Added By Urvin Shah For Rounding Figure.
					
					//expr = expr.replaceAll("DP", String.valueOf(dpValue)); 
					expr = expr.replaceAll("DP", String.valueOf(dpValueWholeMonth));
					expr = expr.replaceAll("GP", String.valueOf(0));	// For Six-Pay (Converting GP with 0 because we are considering the GP =0)	
					//Ended By Urvin Shah
//					expr = expr.replaceAll("DP", componentList.get(0).getExpression());
					expr = expr.replaceAll("BASIC", String.valueOf(otherDtlsVOHst.getotherCurrentBasic()));
					expr = expr.replaceAll("DA",String.valueOf(daValue)); 
					
					expr = expr.replaceAll("x", value.toString());
					deduction = Math.round(parExpression.stringParse(expr));
				}
				else if(deducValue!=0)
				{
					deduction = Math.round(deducValue);
				}
				else
				{
					deduction=Math.round(value);
				}
				
				
				
				if(maxDaysFlag!=1)
				{
					deduction =0;
				}
				//end by manoj for calculating deduction for maxdays of post.
				
				if(LWPFlag==1)
				{
					deduction=0;
				}


				
				logger.info("the expr for allowance is "+expr+" the calculated deduction is "+deduction+" with dedution code is "+compoCode);
				if(!edpCode.equals("9570"))//for not adding the PT value in the totalDeduc 
					//as PT will be calculated later in this service
				{
					if(!"0101".equalsIgnoreCase(edpCode))
					{
						totDeduc = totDeduc + deduction;
					}
				
				}
					
					else
					deduction=0;
			

				if(paramMap.containsKey(typeEdpId))
				{
					edpDtlsVO = (EdpDtlsVO)paramMap.get(typeEdpId);
					double existValue = edpDtlsVO.getAmount();
					
					existValue+=deduction;
					
					edpDtlsVO.setEdpCode(edpCode);
					edpDtlsVO.setAmount(existValue);
					edpDtlsVO.setAddDedFlag(edpAddDedFlag);
					edpDtlsVO.setExpRcpRec(expRcpRec);
					paramMap.put(typeEdpId,edpDtlsVO);
					
					//paramMap.put(edpCode,existValue);
				}
				else
				{
					edpDtlsVO.setEdpCode(edpCode);
					edpDtlsVO.setAmount(deduction);
					edpDtlsVO.setAddDedFlag(edpAddDedFlag);
					edpDtlsVO.setExpRcpRec(expRcpRec);
					paramMap.put(typeEdpId,edpDtlsVO);
					
				  //paramMap.put(edpCode,deduction);
				}
				
				//ended by Ankit Bhatt.
				
				Class payBill = payBillVO.getClass();
				Object[] objArgs = new Object[]{new Double(deduction)};
				Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[]{double.class});
				payBillMthd.invoke(payBillVO, objArgs);
			}
			
		
		}
		catch(Exception e)
		{
			logger.info("error in edp list loop");
			logger.error("Error is: "+ e.getMessage());
		}
		}	
		
		
		//for 10-20 days issues by manoj
		if(maxDaysFlag!=1)
		{
			incomeTax=0;
		}
		//end for 10-20 days issues by manoj
		if(LWPFlag==1)
		{
			incomeTax=0;
		}
		
		logger.info("the calculated it is "+incomeTax);
		
		
		//payBillVO.setPo(revisedBasic-leaveSalary);//basic salary
       // payBillVO.setLs(leaveSalary);//leave salary

        if(LWPFlag == 1)
        	revisedBasic=0;
        if(empType!=contractEmpType && empType!=fixedEmpType)
			totAllow -= dpValue;
        if(LWPFlag == 1)
        	totAllow = 0;
       
        
		double grossPay = (revisedBasic + totAllow );//hard coded
		double pt = 0;
		try{
			
		
		
		if(maxDaysFlag==1) {//PT is not deducted for physically handicapped
			// Added By Urvin Shah for the Six-Pay
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
		
			
		}
		}
		catch(Exception e)
		{
			logger.info("Exception from arrearbill service core logic for pt calculation "+e);
			
		}
		//grossPay = Math.round((grossPay/maxDay) * daysOfPost);		//	calculation of the gross pay according to the days on the post.
		//by manoj for the issue of total recovery in 10-20 issues
		grossPay= grossPay-totAdv;
		
		//end by manoj for the issue of total recovery in 10-20 issues
		
		logger.info("the leaveSalary is "+leaveSalary);
		logger.info("the perpay is "+perpay);
		logger.info("the specialPay is "+specialPay);
		logger.info("totAllow is " + totAllow);
		logger.info("totAdv is "+totAdv);
		logger.info("Gross Pay is " + grossPay);
		
		//by manoj for hrEmpTraMpg issue
		logger.info("ta in service allow0113 " + payBillVO.getAllow0113());
		
		double  nonVehicalDays = 0;
		
		double revisedTa=0;
		double oldTa=payBillVO.getAllow0113();				
		oldTa=0;
		if(payCommissionId!=sixthPayCommId){
			oldTa=payBillVO.getAllow0113();
		}
		else{
			oldTa=payBillVO.getAllow0113();
			SalaryRules_6thPay objSixthSalRuleEngine = new SalaryRules_6thPay();
			//oldTa=objSixthSalRuleEngine.calculateTA(InputVO.getBasic(), cityId, InputVO.isHandicapped(), InputVO.getEmpType(), InputVO.isVechAvailed(), InputVO.getTaDistance(), InputVO.getGradePay());
			logger.info("The TA of an employee as per the Sixth Pay who has opted for the Fifth Pay:-"+oldTa+" and days in month "+maxDay +" and daysOfPost "+daysOfPost);
			
			
			
			oldTa=Math.round((oldTa /maxDay) *daysOfPost); 
			
		}
		
		if(totalVehicalAvailDays<maxDay)
		{
			
			logger.info("for testing the no of month"+maxDay+" totalVehicalAvailDays "+totalVehicalAvailDays);
			
			nonVehicalDays = ((Long)(maxDay  - totalVehicalAvailDays)).doubleValue();
			revisedTa=Math.round((oldTa/maxDay) * nonVehicalDays) ;
		}
		payBillVO.setAllow0113(revisedTa);
		
		logger.info(revisedTa+" revisedTa in service allow0113 " + payBillVO.getAllow0113());
		
		grossPay= grossPay-oldTa+revisedTa;
		
		 
		//end by manoj for hrEmpTraMpg issue
		
		//added by Ankit Bhatt
        EdpDtlsVO travelAllowVO = new EdpDtlsVO();
        travelAllowVO.setEdpCode("0113");
        travelAllowVO.setAmount(revisedTa);
        travelAllowVO.setAddDedFlag("A");
        travelAllowVO.setExpRcpRec("EXP");
		paramMap.put(8L,travelAllowVO);
        //paramMap.put("0113", revisedTa); //Setting T.A value in Outer
		//ended by Ankit Bhatt

		
		//by manoj for quater issue and setting the revised hra
		
		
		double  remDays = 0;
		//by manoj for the issue of hrr and hra issue in 10-20 issues
		double revisedHra=0;
		double oldHra=payBillVO.getAllow0110();				
		//end by manoj for the issue of hrr and hra issue in 10-20 issues
		
		if(totalHrrDays<maxDay)
		{
			remDays = ((Long)(maxDay  - totalHrrDays)).doubleValue();
			//changed by manoj for the issue of hrr and hra issue in 10-20 issues
			//revisedHra=Math.round((revisedHra/maxDay) * remDays) ;
			revisedHra=Math.round((oldHra/maxDay) * remDays) ;
		}
		payBillVO.setAllow0110(revisedHra);
		//by manoj for the issue of hrr and hra issue in 10-20 issues
		grossPay= grossPay-oldHra+revisedHra;
		
		//end by manoj for the issue of hrr and hra issue in 10-20 issues
		
		
		//by manoj for outer inner hra miss match
		//added by Ankit Bhatt for integration with Outer.
		Class thisClass = this.getClass();
		String edpCode = "0110";
		String edpAddDedFlag = "A"; //hard coded for HRA
		long typeEdpId = 5L;

		logger.info("revised hra ********* the param value for edpCode "+edpCode+" is "+(revisedHra));
		if(paramMap.containsKey(typeEdpId))
		{
			EdpDtlsVO edpDtlsVO = (EdpDtlsVO)paramMap.get(typeEdpId);
			double existValue = edpDtlsVO.getAmount();			
			existValue+=(revisedHra-oldHra);
			
			edpDtlsVO.setEdpCode(edpCode);
			edpDtlsVO.setAmount(existValue);
			edpDtlsVO.setAddDedFlag(edpAddDedFlag);
			edpDtlsVO.setExpRcpRec("EXP");
			paramMap.put(typeEdpId,edpDtlsVO);
			
			//paramMap.put(edpCode,existValue);
		}
		else
		{
			EdpDtlsVO edpDtlsVO = new EdpDtlsVO();
			edpDtlsVO.setEdpCode(edpCode);
			edpDtlsVO.setAmount(revisedHra-oldHra);
			edpDtlsVO.setAddDedFlag(edpAddDedFlag);
			edpDtlsVO.setExpRcpRec("EXP");
			paramMap.put(typeEdpId,edpDtlsVO);
			
		  //paramMap.put(edpCode,(revisedHra-oldHra));
		}
		 
		//ended by Ankit Bhatt.
		//end by manoj for outer inner hra miss match
		
		
		
		payBillVO.setGrossAmt(grossPay);
		logger.info("getMaxDays  in Rule Engine " +maxDay+" totalhrrDays "+totalHrrDays+" remDays "+remDays );
		logger.info("revised hra in Rule Engine " + revisedHra);
		

		/*logger.info("the gpf_adv is "+gpf_adv);*/
		logger.info("the payBillVO.getDeduc9531() class IV is "+payBillVO.getDeduc9531());
		

		totDeduc = totDeduc + pt + incomeTax + totLoan /*+ gpf_adv*/;//hard coded
		
		payBillVO.setDeduc9570(pt);
		
		logger.info("pt ********* the param value for edpCode 9570 is "+pt);
		//added by Ankit Bhatt
			EdpDtlsVO ptVO = new EdpDtlsVO();
			ptVO.setEdpCode("9570");
			ptVO.setAmount(pt);
			ptVO.setAddDedFlag("-(d)");
			ptVO.setExpRcpRec("RCP");
			paramMap.put(28L,ptVO);
			
			 //paramMap.put("9570", pt);
			
			EdpDtlsVO leaveSalaryVO = new EdpDtlsVO();
			leaveSalaryVO.setEdpCode("0109");
			leaveSalaryVO.setAmount(0);
			leaveSalaryVO.setAddDedFlag("A");
			leaveSalaryVO.setExpRcpRec("EXP");
			paramMap.put(3L,leaveSalaryVO);
			 //paramMap.put("0109", leaveSalary);
			
		
		
		
		double netAmt=grossPay-totDeduc;
		logger.info("the net amount is "+netAmt);
        
		
		
		//for emp mst 
		HrEisEmpMst hrEisObj=new HrEisEmpMst();            
		GenericDaoHibernateImpl gImpl = new GenericDaoHibernateImpl(HrEisEmpMst.class);
        gImpl.setSessionFactory(serv.getSessionFactory());
        hrEisObj = (HrEisEmpMst) gImpl.read(empid);
				        
        payBillVO.setHrEisEmpMst(hrEisObj);
        
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
        	//Modified by Mrugesh
        	//payBillVO.setMonth(monthGiven);
	        //payBillVO.setYear(yearGiven);
        	paybillHeadMpg.setMonth(monthGiven);
        	paybillHeadMpg.setYear(yearGiven);
	        //Ended by Mrugesh
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
	        
	        //Modified by Mrugesh
	       /* payBillVO.setMonth(Integer.parseInt(month));
	        payBillVO.setYear(Integer.parseInt(year));*/
	        paybillHeadMpg.setMonth(Integer.parseInt(month));
	        paybillHeadMpg.setYear(Integer.parseInt(year));
	        //Ended by Mrugesh
        }
        //edit ended by Ankit Bhatt.
        
        // Added By Urvin shah
        payBillVO.setIt(incomeTax);//income tax
        // Ended By Urvin shah.
        
        leaveSalary =(double)Math.round(leaveSalary);
        payBillVO.setTotalDed(totDeduc);
        payBillVO.setNetTotal(netAmt);
        
        
		
		//Added by Mrugesh for Trn Counter
		//Ended by Mrugesh
		
		//for SIS1979
		//payBillVO.setDeduc9580(payBillOut.getDeduc9580());
		
		 EdpDtlsVO bonusVO = new EdpDtlsVO();
        // to be removed later for DP of various classes
		 		 
		if(grade==GradeCode3 || grade==GradeCode4)//NGO
		{	
		/*payBillVO.setAllow0120(payBillVO.getAllow0119());*/
		payBillVO.setAllow0119(0);
		
		bonusVO = new EdpDtlsVO();
        bonusVO.setEdpCode("0119");
        bonusVO.setAmount(0);
        bonusVO.setAddDedFlag("A");
        bonusVO.setExpRcpRec("EXP");
		paramMap.put(55L,bonusVO);
		
		
		}
		else //GO
		{
			payBillVO.setAllow0120(0);
			
			bonusVO = new EdpDtlsVO();
	        bonusVO.setEdpCode("0120");
	        bonusVO.setAmount(0);
	        bonusVO.setAddDedFlag("A");
	        bonusVO.setExpRcpRec("EXP");
			paramMap.put(56L,bonusVO);
			
		}
		
		
		
		
		 logger.info("***********************************paramMap is "+paramMap);
		resultMap.put("payBillVO",payBillVO);
		resultMap.put("paramMap",paramMap);	
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
