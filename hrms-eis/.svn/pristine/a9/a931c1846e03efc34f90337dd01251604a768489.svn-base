/** This class is a helper class of generate Bill Service. this class handles the Non government deduction calculation
 *  of an employee at the Bill Generation time.
 * 
 */

package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.NonGovDeducDAO;
import com.tcs.sgv.eis.dao.NonGovDeducDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.EmpNonGovVO;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class GenerateBillNonGovCalc {
	Log logger = LogFactory.getLog( getClass() );
	static int SUCCESS = 0;

	static int ERROR = 1;

	/**
	 * this method is called from GenerateBillService for calculation Non government
	 * deduction of an employee while generation of Pay bill.
	 * Input parameter is Map which should contain serviceLocator,Employee ID, Paybill Month, Paybill Year, PaybillVO of HrPayPaybill Type
	 * as Paybill ID is foreign key in Non Gov table, and Date which will be Paybill month's end date for approval date in Non 
	 * Gov Table.
	 * @param Map 
	 * @return integer code (success=0 or failure=1)
	 */
	public int insertPaybillNonGovData(Map objectArgs) {
		logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData Start freeMemory " + Runtime.getRuntime().freeMemory());
		try {
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			Session currSession =(Session)objectArgs.get("currentSession");
			Boolean fromPayBillScheduler =objectArgs.get("fromPayBillScheduler") != null ? (Boolean)objectArgs.get("fromPayBillScheduler") : false;
			
			long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long empId = objectArgs.get("empId") != null ? Long.parseLong(objectArgs.get("empId").toString()) : 0;
			HrPayPaybill payBillVO = (HrPayPaybill) (objectArgs.get("paybillVO") != null ? objectArgs.get("paybillVO") : null);

			int paybill_Month = objectArgs.get("paybill_Month") != null ? Integer.parseInt(objectArgs.get("paybill_Month").toString()) : 0;
			int paybill_Year = objectArgs.get("paybill_Year") != null ? Integer.parseInt(objectArgs.get("paybill_Year").toString()) : 0;
			Date approvalDate = (Date) (objectArgs.get("approvalDate") != null ? objectArgs.get("approvalDate") : null);
			//NonGovDeducDAO nonGovDeducDAO = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class, serv.getSessionFactory());
			//List nonGovDeducList = nonGovDeducDAO.getNonGovDeducDataByEmpId(empId);
			logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData Start 1 freeMemory " + Runtime.getRuntime().freeMemory());
			Log logger = LogFactory.getLog(getClass());
			ResourceBundle rsPayroll = ResourceBundle.getBundle("resources.Payroll");
			long lic = Long.parseLong(rsPayroll.getString("lic").toString());
			long coopbank = Long.parseLong(rsPayroll.getString("coopbank").toString());
			long mrtCoOpSoc = Long.parseLong(rsPayroll.getString("mrtCoOpSoc").toString());
			long recurrdeposit = Long.parseLong(rsPayroll.getString("recurrdeposit").toString());
			long misc = Long.parseLong(rsPayroll.getString("misc").toString());
			long otherRecovery = Long.parseLong(rsPayroll.getString("otherRecovery").toString());
			long otherDeduction = Long.parseLong(rsPayroll.getString("otherDeduction").toString());
			long mantralayabank = Long.parseLong(rsPayroll.getString("mantralayabank").toString());
			long mis = Long.parseLong(rsPayroll.getString("mis").toString());
			long creditsoc = Long.parseLong(rsPayroll.getString("creditsoc").toString());
			long constore = Long.parseLong(rsPayroll.getString("constore").toString());
			logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData Start 2 freeMemory " + Runtime.getRuntime().freeMemory());
			Map<Long,Map<Integer,EmpNonGovVO>> nonGovMap  = (Map<Long,Map<Integer,EmpNonGovVO>>) (objectArgs.get("empNonGovMap") != null ? objectArgs.get("empNonGovMap") :new HashMap());
			Map<Integer,EmpNonGovVO> empNonGovMap = nonGovMap.containsKey(empId) ? nonGovMap.get(empId):new HashMap();
			
			nonGovMap = null;
			Map<Long,Map<Integer,Long>> nonGovPayslipMap = (HashMap) (objectArgs.get("nonGovPayslipMap")!=null?objectArgs.get("nonGovPayslipMap"):new HashMap());
			Map<Integer,Long> empNonGovPayslipMap = nonGovPayslipMap.containsKey(empId) ? nonGovPayslipMap.get(empId):new HashMap();
			
			nonGovPayslipMap = null;
			
			IdGenerator idGen = new IdGenerator();
			//GenericDaoHibernateImpl nonGovtPayslipDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
			//nonGovtPayslipDao.setSessionFactory(serv.getSessionFactory());
			// End.
			// Initialization of the Non Government Details.
			long totNonGovDedcAmt = 0;
			HrPayPayslipNonGovt nonGovtPayslip = null;
			String methodString = rsPayroll.getString("NonGovMethodMap").toString();
			//String[] nonGovMethodArray = methodString.split(",");
			//Class hrPayPayslipNonGovtClass = HrPayPayslipNonGovt.class;
			//String nonGovMethod = "";
			//Method deducMthd = null;
			logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData Start 3 freeMemory " + Runtime.getRuntime().freeMemory());
			if (empNonGovMap != null && empNonGovMap.size() > 0) {
				logger.info("the size of nonGovtDeducList is " + empNonGovMap.size() + " for the emp " + empId);
				Iterator keyIterator = empNonGovMap.keySet().iterator();
				List<HrPayPayslipNonGovt> payslipNonGovtsList = new ArrayList<HrPayPayslipNonGovt>();
				logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData Start 4 freeMemory " + Runtime.getRuntime().freeMemory());
				while(keyIterator.hasNext()) {
					EmpNonGovVO empNonGovVO  = null;
					int key = Integer.valueOf(keyIterator.next().toString());
					if(empNonGovMap.containsKey(key))
					   empNonGovVO = (EmpNonGovVO) empNonGovMap.get(key);
					
					if(empNonGovVO != null){
						int deducCode = empNonGovVO.getDeducCode();
						logger.info("Deduc Code to check in Payslip Map"+deducCode);
						if(!empNonGovPayslipMap.containsKey(deducCode)){
							nonGovtPayslip = new HrPayPayslipNonGovt();
							nonGovtPayslip.setDeducCode(empNonGovVO.getDeducCode());
							nonGovtPayslip.setDeducAmount(empNonGovVO.getAmount());
							nonGovtPayslip.setPaybillID(payBillVO);
							nonGovtPayslip.setOrgPostMstByCreatedByPost(orgPostMst);
							nonGovtPayslip.setOrgUserMstByCreatedBy(orgUserMst);
							//Added By roshan for NGR Paybill
							nonGovtPayslip.setNonGovType(empNonGovVO.getOtherRecType());
							//Ended By Roshan for NGR Paybill
							nonGovtPayslip.setCreatedDate(new Date());
							long nonGovtId = idGen.PKGenerator("HR_PAY_PAYSLIP_NON_GOVT", objectArgs);
							logger.info("The id is:-" + nonGovtId);
							nonGovtPayslip.setNonGovtId(nonGovtId);
							payslipNonGovtsList.add(nonGovtPayslip);
								//nonGovtPayslipDao.create(nonGovtPayslip);
							/*if(fromPayBillScheduler)
								currSession.save(nonGovtPayslip);
							else
								nonGovtPayslipDao.create(nonGovtPayslip);
								*/
							
							logger.info("Created for Deduction"+deducCode);
						}
					}	
					logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData Start 5 freeMemory " + Runtime.getRuntime().freeMemory());
					totNonGovDedcAmt = totNonGovDedcAmt + empNonGovVO.getAmount();
					
					/*for(int j1 = 0; j1 < nonGovMethodArray.length; j1+=2)
					{
						if(nonGovMethodArray[j1].equalsIgnoreCase(""+empNonGovVO.getDeducCode()))
						{
							long lValue = empNonGovVO.getAmount();
							String methodName = nonGovMethodArray[j1+1];
							nonGovMethod = "set"+methodName;
							logger.info("the "+methodName+" is " + lValue);
							deducMthd = hrPayPayslipNonGovtClass.getMethod(nonGovMethod, long.class);
							deducMthd.invoke(nonGovtPayslip, lValue);
						}
					}*/
					
					/*if (empNonGovVO!=null && empNonGovVO.getDeducCode() == 91) {
						nonGovtPayslip.setLic(empNonGovVO.getAmount());
						logger.info("the lic is " + empNonGovVO.getAmount());
					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == coopbank) {
						nonGovtPayslip.setCoopbank(empNonGovVO.getAmount());
						logger.info("the coopbank is " + empNonGovVO.getAmount());
					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == mrtCoOpSoc) {
						nonGovtPayslip.setMrtCoOpSoc(empNonGovVO.getAmount());
						logger.info("the mrtCoOpSoc is " + empNonGovVO.getAmount());
					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == recurrdeposit) {
						nonGovtPayslip.setRecurrdeposit(empNonGovVO.getAmount());
						logger.info("the recurrdeposit is " + empNonGovVO.getAmount());
					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == misc) {
						nonGovtPayslip.setMisc(empNonGovVO.getAmount());
						logger.info("the misc is " + empNonGovVO.getAmount());

					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == otherRecovery) {
						nonGovtPayslip.setOtherRecovery(empNonGovVO.getAmount());
						logger.info("the otherRecovery is " + empNonGovVO.getAmount());

					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == otherDeduction) {
						nonGovtPayslip.setOtherDeduction(empNonGovVO.getAmount());
						logger.info("the otherDeduction is " + empNonGovVO.getAmount());

					}
					//Added by Kishan
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == mantralayabank) {
						nonGovtPayslip.setMantralaya_bank(empNonGovVO.getAmount());
						logger.info("the mantralayabank is " + empNonGovVO.getAmount());

					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == mis) {
						nonGovtPayslip.setMis(empNonGovVO.getAmount());
						logger.info("the mis is " + empNonGovVO.getAmount());

					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == creditsoc) {
						nonGovtPayslip.setCredit_soc(empNonGovVO.getAmount());
						logger.info("the creditsoc is " + empNonGovVO.getAmount() );

					}
					if (empNonGovVO!=null && empNonGovVO.getDeducCode() == constore) {
						nonGovtPayslip.setCon_store(empNonGovVO.getAmount());
						logger.info("the constore is " + empNonGovVO.getAmount());

					}*/
					
				}
				keyIterator = null;
				objectArgs.put("payslipNonGovtsList_Batch", payslipNonGovtsList);
			}
			
			empNonGovPayslipMap = null;
			empNonGovMap = null;
			/*for(int j1 = 0; j1 < nonGovMethodArray.length; j1+=2)
			{
					nonGovMethod = "get"+nonGovMethodArray[j1+1];
					deducMthd = hrPayPayslipNonGovtClass.getMethod(nonGovMethod);
					totNonGovDedcAmt = totNonGovDedcAmt + (Long) deducMthd.invoke(nonGovtPayslip);
			}*/
			/*totNonGovDedcAmt = nonGovtPayslip.getLic() + nonGovtPayslip.getCoopbank() + nonGovtPayslip.getMrtCoOpSoc()
					+ nonGovtPayslip.getRecurrdeposit() + nonGovtPayslip.getMisc() + nonGovtPayslip.getOtherRecovery()
					+ nonGovtPayslip.getOtherDeduction() + nonGovtPayslip.getMantralaya_bank() + nonGovtPayslip.getMis()
					+ nonGovtPayslip.getCredit_soc() + nonGovtPayslip.getCon_store();*/
			//nonGovtPayslip.setTotalNonGovtDeduc(totNonGovDedcAmt);

			logger.info("the totNonGovDedcAmt is " + totNonGovDedcAmt);
			//logger.info("for chking duplicate the non govt Map is " + empNonGovPayslipMap);
			// paySlipVO = new HrPayPayslip();

			

			// List nongOvtList =
			// nonGovDeducDAO.chkForNonGovtWithPaybill(hrEisMst, monthGiven,
			// yearGiven);
			// commented by ravysh for multiple month suppl. bill
			// List nonGovtList =
			// nonGovDeducDAO.chkNonGovtForPayslip(hrEisMst.getEmpId(),
			// monthGiven, yearGiven);
			//List nonGovtList = nonGovDeducDAO.chkNonGovtForPayslip(empId, paybill_Month, paybill_Year);
			
			// .chkForNonGovtWithPaybill(payBillVO.getHrEisEmpMst(),monthGiven,yearGiven);

			

			/*if (empPayslipNonGovMap != null && empPayslipNonGovMap.size() > 0 && empPayslipNonGovMap.containsKey(empId)) {
				logger.info(" list is not null and size is not zero");
				long nonGovtIdMap = empPayslipNonGovMap.get(empId);
				HrPayPayslipNonGovt newNonGovtPayslip = new HrPayPayslipNonGovt();
				newNonGovtPayslip.setNonGovtId(nonGovtIdMap);
				newNonGovtPayslip.setLic(nonGovtPayslip.getLic());
				newNonGovtPayslip.setCoopbank(nonGovtPayslip.getCoopbank());
				newNonGovtPayslip.setMrtCoOpSoc(nonGovtPayslip.getMrtCoOpSoc());
				newNonGovtPayslip.setRecurrdeposit(nonGovtPayslip.getRecurrdeposit());
				newNonGovtPayslip.setMisc(nonGovtPayslip.getMisc());
				newNonGovtPayslip.setOtherRecovery(nonGovtPayslip.getOtherRecovery());
				newNonGovtPayslip.setOtherDeduction(nonGovtPayslip.getOtherDeduction());
				newNonGovtPayslip.setMantralaya_bank(nonGovtPayslip.getMantralaya_bank());
				newNonGovtPayslip.setMis(nonGovtPayslip.getMis());
				newNonGovtPayslip.setCredit_soc(nonGovtPayslip.getCredit_soc());
				newNonGovtPayslip.setCon_store(nonGovtPayslip.getCon_store());						
				newNonGovtPayslip.setTotalNonGovtDeduc(nonGovtPayslip.getTotalNonGovtDeduc());
				newNonGovtPayslip.setPaybillID(nonGovtPayslip.getPaybillID());
				newNonGovtPayslip.setUpdatedDate(new Date());

				logger.info("going to update the record " + newNonGovtPayslip.getNonGovtId() + " for paybill id "
						+ newNonGovtPayslip.getPaybillID().getId());
				nonGovtPayslipDao.update(newNonGovtPayslip);
			} else {
				logger.info(" list is null and size is zero");
				logger.info("going to create the record " + nonGovtPayslip.getNonGovtId() + " for paybill id "
						+ nonGovtPayslip.getPaybillID().getId());
				nonGovtPayslipDao.create(nonGovtPayslip);

			}*/
			logger.error("GenerateBillNonGovCalc :: insertPaybillNonGovData End freeMemory " + Runtime.getRuntime().freeMemory());
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			return ERROR;			
		}
		
	}
}
