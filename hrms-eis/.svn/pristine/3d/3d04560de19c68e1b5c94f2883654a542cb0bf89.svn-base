package com.tcs.sgv.eis.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.allowance.service.SalaryRules_6thPay;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.service.GenerateBillService;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;

public class GenerateBillCalculateRules {

	
	/**
	 * Method will calculate HRR based on given HrEisOtherDtls object
	 * for given month and year.
	 * @param Map - contains otherDtlsVO
	 * @return Map
	 */
	public Map calculateHRR(Map hrrMap) {
		
						
		RevisedHrrAndHra revisedHrrAndHra = new RevisedHrrAndHra();
		/*Map hrrAndHraMap = revisedHrrAndHra.calculateHrrAndHra(objServiceArgs);							
		hrrAndHraMap.put("otherDtlsVO", otherDtlsVO);
		hrrAndHraMap.put("dpValue", hrrMap.get("dpValue"));
		hrrAndHraMap.put("revBasic", hrrMap.get("revBasic"));*/
				
		return revisedHrrAndHra.calculateRevisedHraAndHra(hrrMap);			

	}
	
	/*
	 * Method will calculate TA amount based on Given input.
	 * Input required : HrEisOtherDtls object, String City ID, 
	 * Month, Year, Days of Post for this employee and Service Locator
	 * return : Map contains revisedTA and oldTA amount
	 */
	public Map calculateTA(Map taMap) {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		long sixthPayCommId = Long.parseLong(resourceBundle.getString("commissionSixId"));
		HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls)(taMap.get("hrEisOtherDtls")!=null?taMap.get("hrEisOtherDtls"):null);
		 	
		 
		long payCommissionId = (hrEisOtherDtls.getHrEisSgdMpg()!=null)?hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId().longValue():0;
		 
		 
		 
		
		
		 
		
		 
			
		 
		double oldTa = 0;
		double  nonVehicalDays = 0;		
		double revisedTa=0;
		Map inputMap = taMap;
		 
		//Map passMap = new  GenerateBillServiceCoreLogic().generatePassMap(inputMap);
		
		if(payCommissionId!=sixthPayCommId)
		{
			SalaryRules  objFifthSalRuleEngine = new SalaryRules();
			oldTa = objFifthSalRuleEngine.calculateTA(taMap);			
		}
		else{
			SalaryRules_6thPay objSixthSalRuleEngine = new SalaryRules_6thPay();	
			oldTa= objSixthSalRuleEngine.calculateTA(taMap);			
			 				
		}	
		
		 
		
		Map resultMap = new HashMap();
		resultMap.put("revisedTA", revisedTa);
		resultMap.put("oldTA", oldTa);
		return taMap;
	}
}
