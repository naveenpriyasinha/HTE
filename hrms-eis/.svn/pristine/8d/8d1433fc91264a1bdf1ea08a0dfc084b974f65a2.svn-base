package com.tcs.sgv.eis.util;

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

import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.QuaterMstDAOImpl;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEisQtrMstHst;

public class RevisedHrrAndHra {

	public RevisedHrrAndHra() {
		// TODO Auto-generated constructor stub
	}
	
	Log logger = LogFactory.getLog( getClass() );
	
	public Map calculateHrrAndHra(Map objectArgs)
	{
		Map resultMap = objectArgs;
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		int monthGiven=Integer.parseInt(objectArgs.get("month").toString());
		int yearGiven=Integer.parseInt(objectArgs.get("year").toString());
		
		Calendar calc = Calendar.getInstance();
		calc.set(Calendar.MONTH, monthGiven-1);
		calc.set(Calendar.YEAR, yearGiven);
		
		Date givenDate = calc.getTime();
		int maxDay = calc.getActualMaximum(Calendar.DAY_OF_MONTH);
		long userIdForQtr = Long.parseLong(objectArgs.get("userIdForQtr").toString());
		
		HrEisQtrMst hrEssQuaterMst = new HrEisQtrMst(); 
		HrEisQtrMstHst hrEssQuaterHst = new HrEisQtrMstHst(); 
		QuaterMstDAOImpl hrEssQuaterDao = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());					
		List quaterList = new ArrayList();
		List quaterHstList = new ArrayList();
		
		long daysofQauter=0;
		long daysofQauterHst=0;
		List quaterIdList = new ArrayList();
		List quaterDaysList = new ArrayList();
		List lstQuarterRate = new ArrayList();
		
		int quater_id=0;
		int quater_id_hst=0;
		// Added By Urvin Shah
		long quarterRateType=0;   // Rate Type of the Quarter.
		long quarterRateTypeHst=0;   // Rate Type of the Quarter.
		// Ended.
		
		/*if(maxDaysFlag==1)
		{*/
		
		
		
			logger.info(" going to calculate hrr for user id "+userIdForQtr);
			quaterList = hrEssQuaterDao.getQuarterDtls(userIdForQtr);
			if(quaterList!=null && quaterList.size()>0)
			{
				int quaterStartDay = 0;
				int quaterStartMonth = 0;
				int quaterStartYear = 0;
				
				int quaterEndDay = 0;
				int quaterEndMonth = 0;
				int quaterEndYear = 0; 
				
				int quaterHstStartDay = 0;
				int quaterHstStartMonth = 0;
				int quaterHstStartYear = 0; 
				
				int quaterHstEndDay = 0;
				int quaterHstEndMonth = 0;
				int quaterHstEndYear = 0;	
				logger.info("ther size of quaterList.size() "+quaterList.size());
				for(int count=0;count<quaterList.size();count++)
				{
					hrEssQuaterMst =(HrEisQtrMst)quaterList.get(count);
					if(hrEssQuaterMst!=null && hrEssQuaterMst.getHrEisQuaterTypeMst()!=null)
					   quater_id=(int)hrEssQuaterMst.getHrEisQuaterTypeMst().getQuaId();
					daysofQauterHst=0;
					if(hrEssQuaterMst!=null && hrEssQuaterMst.getRateTypeLookup()!=null)
					     quarterRateType = (long)hrEssQuaterMst.getRateTypeLookup().getLookupId();

					// Modified By Urvin Shah.
					/*quaterStartDay = dayofDate(hrEssQuaterMst.getAllocationStartDate());
					quaterStartMonth = monthofDate(hrEssQuaterMst.getAllocationStartDate());
					quaterStartYear = yearofDate(hrEssQuaterMst.getAllocationStartDate());
					*/
					if(hrEssQuaterMst.getPossessionDate()!=null) {
					  quaterStartDay = dayofDate(hrEssQuaterMst.getPossessionDate());					
					quaterStartMonth = monthofDate(hrEssQuaterMst.getPossessionDate());					
					quaterStartYear = yearofDate(hrEssQuaterMst.getPossessionDate());
					}
					
					// End By Urvin.
					logger.info(quaterStartMonth +" == "+ monthGiven +" && "+ quaterStartYear +" == "+ yearGiven);
					
					if(quaterStartMonth == monthGiven && quaterStartYear == yearGiven)
					{//only need to read history if start date falls in current month and current year
						logger.info("quater is availed in current month ");
						quaterHstList=hrEssQuaterDao.getQuarterDtlsFromHst(userIdForQtr,quater_id);
						
						if(quaterHstList!=null && quaterHstList.size()>0)
						{
							quaterHstStartDay = 0;
							quaterHstStartMonth = 0;
							quaterHstStartYear = 0; 
							
							quaterHstEndDay = 0;
							quaterHstEndMonth = 0;
							quaterHstEndYear = 0;	
							
							logger.info("ther size of quaterHstList.size() "+quaterHstList.size());
							for(int countHst=0;countHst<quaterHstList.size();countHst++)
							{
								hrEssQuaterHst =(HrEisQtrMstHst)quaterHstList.get(countHst);
								
								quater_id_hst=(int)hrEssQuaterHst.getHrQuaterTypeMst().getQuaId();
								logger.info("ther hrEssQuaterHst "+hrEssQuaterHst);
								/*if(quaterHstList.size()>1 )
								{*/
								quarterRateTypeHst = (long)hrEssQuaterHst.getRateTypeLookup().getLookupId();
								logger.info("Quarter Rate Type is 0 :-"+quarterRateTypeHst);
								
								//Modified By Urvin Shah.
									/*quaterHstStartDay = dayofDate(hrEssQuaterHst.getAllocationStartDate());
									quaterHstStartMonth = monthofDate(hrEssQuaterHst.getAllocationStartDate());
									quaterHstStartYear = yearofDate(hrEssQuaterHst.getAllocationStartDate());*/
									
								quaterHstStartDay = dayofDate(hrEssQuaterHst.getPossessionDate());
								quaterHstStartMonth = monthofDate(hrEssQuaterHst.getPossessionDate());
								quaterHstStartYear = yearofDate(hrEssQuaterHst.getPossessionDate());
								
								// End By Urvin.
									logger.info(hrEssQuaterHst.getId().getQuarterId()+" the value of quaterHstStartDay "+quaterHstStartDay+" quaterHstStartMonth "+quaterHstStartMonth+" quaterHstStartYear "+quaterHstStartYear);
									logger.info("the hst error chking is "+hrEssQuaterHst.getAllocationEndDate());
									if(hrEssQuaterHst.getAllocationEndDate()!=null)
									{
										quaterHstEndDay= dayofDate(hrEssQuaterHst.getAllocationEndDate());
										quaterHstEndMonth =monthofDate(hrEssQuaterHst.getAllocationEndDate());
										quaterHstEndYear = yearofDate(hrEssQuaterHst.getAllocationEndDate());	
										logger.info("the value of quaterHstEndDay "+quaterHstEndDay+" and quaterHstStartDay is "+quaterHstStartDay);
										
										if(quaterHstEndMonth==monthGiven  && quaterHstEndYear == yearGiven)	// End date in current month and year.
										{
											if(quaterHstStartMonth==monthGiven && quaterHstStartYear==yearGiven)  // Start and end date both are in the same month and year.
											{
												daysofQauterHst=quaterHstEndDay-(quaterHstStartDay-1);
												logger.info("Total daysofQauterHst:-"+daysofQauterHst);
											}
											else // start date is not in current month but end date is in current month.
										    {
												daysofQauterHst = quaterHstEndDay;
												logger.info("Total No. daysofQauterHst:-"+daysofQauterHst);
										    }
										}
										else //	End date is not in current month and year. 
										{
											if(quaterHstStartMonth==monthGiven && quaterHstStartYear == yearGiven)	// if startDate is in current month.
											{
												daysofQauterHst=maxDay-(quaterHstStartDay-1);
											}
											//else if(givenDate.after(hrEssQuaterHst.getAllocationStartDate()) && givenDate.before(hrEssQuaterHst.getAllocationEndDate())) // if whole current month is between the start and end date.
											else if(givenDate.after(hrEssQuaterHst.getPossessionDate()) && givenDate.before(hrEssQuaterHst.getAllocationEndDate())) // if whole current month is between the start and end date.
												
											{
												daysofQauterHst=maxDay;
												logger.info("daysofQauterHst for hst when start date is less than current date "+daysofQauterHst);
											}
											else	// if whole current month is not between the start and end date.
										    {
												continue;
										    }
										}
									}
									quaterIdList.add(quater_id_hst);
									quaterDaysList.add(daysofQauterHst);
									lstQuarterRate.add(quarterRateTypeHst);
								//}//end of allocation if block
																			
							}//end of for QauterHst loop
															
						}//end of quaterHstList chk for null 
					}
					logger.info("the mst error chking is "+hrEssQuaterMst.getAllocationEndDate());
					//if(hrEssQuaterMst.getAllocationEndDate()==null)
					if(hrEssQuaterMst.getAllocationEndDate()!=null)
					{
						quaterEndDay= dayofDate(hrEssQuaterMst.getAllocationEndDate());
						quaterEndMonth =monthofDate(hrEssQuaterMst.getAllocationEndDate());
						quaterEndYear = yearofDate(hrEssQuaterMst.getAllocationEndDate());	
						logger.info("the value of quaterEndDay "+quaterEndDay+" and quaterStartDay is "+quaterStartDay);
						
						if(quaterEndMonth==monthGiven  && quaterEndYear == yearGiven)	// End date in current month and year.
						{
							if(quaterStartMonth==monthGiven && quaterStartYear==yearGiven)  // Start and end date both are in the same month and year.
							{
								daysofQauter=quaterEndDay-(quaterStartDay-1);
								logger.info("Total daysofQauter:-"+daysofQauter);
							}
							else // start date is not in current month but end date is in current month.
						    {
								daysofQauter = quaterEndDay;
								logger.info("Total No. daysofQauter:-"+daysofQauter);
						    }
						}
						else //	End date is not in current month and year. 
						{
							if(quaterStartMonth==monthGiven && quaterStartYear == yearGiven)	// if startDate is in current month.
							{
								daysofQauter=maxDay-(quaterStartDay-1);
							}
							//else if(givenDate.after(hrEssQuaterMst.getAllocationStartDate()) && givenDate.before(hrEssQuaterMst.getAllocationEndDate())) // if whole current month is between the start and end date.
							else if(givenDate!=null && hrEssQuaterMst.getPossessionDate()!=null && hrEssQuaterMst.getAllocationEndDate()!=null &&
									givenDate.after(hrEssQuaterMst.getPossessionDate()) && givenDate.before(hrEssQuaterMst.getAllocationEndDate())) // if whole current month is between the start and end date.
							{
								daysofQauterHst=maxDay;
								logger.info("daysofQauter  when start date is less than current date "+daysofQauter);
							}
							else	// if whole current month is not between the start and end date.
						    {
								continue;
						    }
						}
					}
					else
					{	
						if(quaterStartMonth==monthGiven && quaterStartYear == yearGiven)	// if startDate is in current month.
						{
							daysofQauter=maxDay-(quaterStartDay-1);
						}
						//else if (givenDate.after(hrEssQuaterMst.getAllocationStartDate()))	// if startdate is less then current date.						
						else if (hrEssQuaterMst.getPossessionDate()!=null && givenDate.after(hrEssQuaterMst.getPossessionDate()))	// if startdate is less then current date.
					    {
							daysofQauter=maxDay;
					    }
						else	// if start date is greater then current date.
							continue;
						
						
						logger.info("daysofQauter "+daysofQauter );
						
						
						
					}
					
					quaterIdList.add(quater_id);
					quaterDaysList.add(daysofQauter);
					lstQuarterRate.add(quarterRateType);
				}//end of for loop quaterList master table
			}
			else//if quaterList is null master table
			{
				quater_id=0;
				daysofQauter=0;
				
				quaterIdList.add(quater_id);
				quaterDaysList.add(daysofQauter);
				lstQuarterRate.add(quarterRateType);								
			}
		
		
			resultMap.put("quaterIdList",quaterIdList );	
			resultMap.put("quaterDaysList",quaterDaysList );
			resultMap.put("lstQuarterRate",lstQuarterRate );
			
		return resultMap;
	}

	public Map calculateRevisedHraAndHra(Map inputMap)
	{
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		ResourceBundle rsBundle = ResourceBundle.getBundle("resources.Payroll");
		Map resultMap = new HashMap();
		QuaterMstDAOImpl hrEssQuaterDao = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());
		int monthGiven=Integer.parseInt(inputMap.get("month").toString());
		int yearGiven=Integer.parseInt(inputMap.get("year").toString());
		long  userIdForQtr = Long.parseLong(inputMap.get("userIdForQtr").toString());
		
		logger.info("inside calculateRevisedHraAndHra month given " + monthGiven);
		logger.info("inside calculateRevisedHraAndHra year given " + yearGiven);
		logger.info("inside calculateRevisedHraAndHra useRId given " + userIdForQtr);
		
		
		HrEisQtrMst hrEisQtrMst = hrEssQuaterDao.getHrEisQtrMst(userIdForQtr);
		Calendar calc = Calendar.getInstance();
		
		calc.set(Calendar.MONTH, monthGiven-1);
		calc.set(Calendar.YEAR, yearGiven);
		int maxDay = calc.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date qtrStartDate= null;
		long qtrRent=0;
		int hraDays=0;
		logger.info("max day is before considering quarter is" + maxDay);
		if((hrEisQtrMst == null) || (hrEisQtrMst != null && hrEisQtrMst.getAllocationEndDate() != null && hrEisQtrMst.getAllocationEndDate().getMonth() <=(new Date().getMonth())) ){
			/*qtrStartDate = hrEisQtrMst.getAllocationStartDate();
			hraDays=maxDay-qtrStartDate.getDate();
			qtrRent=hrEisQtrMst.getQuarterRent();*/
			hraDays = maxDay;
		}else
			qtrRent = hrEisQtrMst.getQuarterRent();
		logger.info("hraDays is after considering quarter is" + hraDays);
		
		
		
		
		//SalaryRules salRules = new SalaryRules();
		
		long qtrRateType;
		long totalhrrDays=0;
		
			
		
		
		EmpPaybillVO empPaybillVO = (EmpPaybillVO)inputMap.get("otherDtlsVO");
		
       //  Updated By Urvin shah.
		
		long fifthPayCommissionId=Long.parseLong(rsBundle.getString("commissionFiveId"));
		long commissionSevenId =  Long.parseLong(rsBundle.getString("commissionSevenId"));
		long commissionThreeId =  Long.parseLong(rsBundle.getString("commissionThreeId"));
		long payCommissionId= fifthPayCommissionId;
		
		payCommissionId = empPaybillVO.getPayCommissionId();
		
		
			SalaryRules salRules = new SalaryRules();
		
		
			//SalaryRules_6thPay sixPaySalRules = new SalaryRules_6thPay();
			
		
		// End.
		  
		  long empType=empPaybillVO.getEmpType();		// Employee Type
		  int isAvailedHRA = empPaybillVO.getIsAvailedHRA(); 			// FLAG: has this emp given application for HRA?
		  logger.info("isAvailedHRA is : "+isAvailedHRA);		  		 		  

			double oldHra=0;
			double hraFromRuleEngine=0;
			
			//calculate HRA only if employee has given application for HRA

				logger.info("Employee has applied for HRA");
				
				
				/*if(cityCatVo!=null && cityCatVo.getCategoryId()!=null)
				 inputMap.put("city",cityCatVo.getCategoryId());
				else
					inputMap.put("city","");*/
				
				//inputMap.put("cityId",otherDtlsVO.getCity());
				String tempCity="A";
				if(inputMap.get("city").toString()!=null)
					tempCity=inputMap.get("city").toString();
				
				logger.info("*****City fetched from inputMAP of coreLogic is***-->"+tempCity.toString());
				inputMap.put("city",tempCity);
				
				inputMap.put("empType",empPaybillVO.getEmpType());
				inputMap.put("isAvailedHRA",empPaybillVO.getIsAvailedHRA());
				logger.info("revBasic value is "+inputMap.get("revBasic"));
				if(payCommissionId==fifthPayCommissionId || payCommissionId == commissionSevenId){
					logger.info("calculating HRA as per 5th pay commission");										
					hraFromRuleEngine=salRules.calculateHRA(inputMap);
				}
				else{
					SalaryRules_6thPay salaryRulesSixPay= new SalaryRules_6thPay();
					logger.info("calculating HRA as per 6th pay commission");					
					hraFromRuleEngine = salaryRulesSixPay.calculateHRA(inputMap);
				}
			
			//oldHra = Math.round(hraFromRuleEngine*(otherDtlsVO.getotherCurrentBasic()+dpValue)/100);
			
			 logger.info(" oldhra in Rule Engine in new method " + hraFromRuleEngine);
			
			oldHra=hraFromRuleEngine;
			if(payCommissionId == commissionSevenId || payCommissionId == commissionThreeId)
				oldHra = 0;
			//System.out.println("final value of old hra is "+(hraDays/maxDay)*oldHra);
			logger.info("final value of old hra is "+(hraDays/maxDay)*oldHra);
			if(hraDays!= 0)
				resultMap.put("oldHra",(hraDays/maxDay)*oldHra);
			else{
				oldHra = 0;
				resultMap.put("oldHra",oldHra);
			}
			resultMap.put("qtrRent",qtrRent);	
		return resultMap;
	}
	
	
	public int dayofDate(Date date)
	{
	    int dayofDate=0;
	    SimpleDateFormat sdfObj = new SimpleDateFormat("dd");
	    String days = sdfObj.format(date);
	    dayofDate=Integer.parseInt(days);
	    return dayofDate;
	}
	public int monthofDate(Date date)
	{		
	    int monthofDate=0;
	    SimpleDateFormat sdfObj = new SimpleDateFormat("MM");
	    String days = sdfObj.format(date);
	    monthofDate=Integer.parseInt(days);
	    return monthofDate;
	}
	public int yearofDate(Date date)
	{
	    int yearofDate=0;
	    SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
	    String days = sdfObj.format(date);
	    yearofDate=Integer.parseInt(days);
	    return yearofDate;
	}
	
}
