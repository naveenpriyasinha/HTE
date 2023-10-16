package com.tcs.sgv.hr.payfixation.service;

import java.util.Map;

import com.tcs.sgv.core.valueobject.ResultObject;

public interface PayFixationService {

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#FindDetail(java.util.Map)
	 */
	public  ResultObject FindDetail(Map objectArgs);

	public  ResultObject Employeeacceptance(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Month(java.util.Map)
	 */
	public  ResultObject Month(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#SearchPayFix(java.util.Map)
	 */
	public  ResultObject SearchPayFix(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Payfix(java.util.Map)
	 */
	public  ResultObject Payfix(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#PayfixByIncr(java.util.Map)
	 */
	public  ResultObject PayfixByIncr(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#PayfixAtPayFixDate(java.util.Map)
	 */
	public  ResultObject PayfixAtPayFixDate(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#InsertDetail(java.util.Map)
	 */
	public  ResultObject InsertDetail(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#FixationDetail(java.util.Map)
	 */
	public  ResultObject FixationDetail(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#Employeeselecteddate(java.util.Map)
	 */
	public  ResultObject Employeeselecteddate(Map objectArgs);

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.service.PayFixationService#InsertDetailinMst(java.util.Map)
	 */
	public  ResultObject ApprovePayFix(Map objectArgs);

	public  ResultObject Reject(Map objectArgs);

	public  ResultObject SubmitPayfixation(Map objectArgs);

}