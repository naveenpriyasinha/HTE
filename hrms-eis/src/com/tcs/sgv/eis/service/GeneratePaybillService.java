
package com.tcs.sgv.eis.service;

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
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.payroll.dao.PayrollCommonDAO;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpCompMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpLeaveDAOImpl;
import com.tcs.sgv.eis.dao.EmpLoanDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.HrEmpTraMpgDaoImpl;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.NonGovDeducDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.QuaterMstDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillCalculateRules;
import com.tcs.sgv.eis.util.GenerateBillLoanAndAdvanceHelper;
import com.tcs.sgv.eis.util.GenerateBillNonGovCalc;
import com.tcs.sgv.eis.util.GenerateBillServiceCoreLogic;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpg;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;
import com.tcs.sgv.eis.valueobject.HrLoanEmpDtls;
import com.tcs.sgv.eis.valueobject.HrPayChangedRecords;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayNonGovDeduction;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;


/**
 * This class is used to Generate PAYBILL for given month and year.
 * Class has method named generatePaybill which processes only
 * changed records and loan, leave, TA, Quarter Availed employees 
 * and update Pay bill values directly.
 * @author Ankit Bhatt
 */
public class GeneratePaybillService {
	Log logger = LogFactory.getLog(getClass());

	/**
	 * Method will be specific to Pay bill generation only.
	 * It will fetch different lists like Changed employee List,
	 * Vehicle availed list, employee loan list etc. And process 
	 * those lists to generate PAY BILL only.
	 * @param objectArgs
	 * @return Map
	 */
	public synchronized Map generatePaybill(Map<Object,Object> objectArgs) throws Exception {
		logger.info("Inside generate Paybill service");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		Map resultMap = objectArgs;
		
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");	
			long dcpsId = Long.parseLong(resourceBundle.getString("dcpsId"));
			long dedycType =  Long.parseLong(resourceBundle.getString("deducLookupId"));
			long loanLookupId = Long.parseLong(resourceBundle.getString("loanLookupId"));
			long advLookupId = Long.parseLong(resourceBundle.getString("advLookupId"));
			
			List<HrPayChangedRecords> lstChangedRecords = null;
			List<Long> empLeaveRecords = new ArrayList<Long>();
			List<Long> empTraRecords = new ArrayList<Long>();
			List<Long> empLoanRecords = new ArrayList<Long>();
			List<Long> empQuarterRecords = new ArrayList<Long>();
			
			//List contains pay bill records for Given month and year
			//which will be updated according to the logic
			List<Long> postPaybillRecords = new ArrayList<Long>();
			
			//List contains post IDs which is change for specific transaction
			//like TA, HRA etc, so that particular value should be updated
			List<Long> toBeProcessedPost = new ArrayList<Long>();
			
			//List contains post IDs which are fetched from Changed records table.
			List<Long> lstGenerateBillPosts = new ArrayList<Long>();
			
			//List contains post IDs which are changed from Changed records table.
			List<Long> lstChangedPosts = new ArrayList<Long>();
			
			//List with all eligible post Ids from GenerateBillService
			List<Long> eligiblePosts = new ArrayList<Long>();
			
			List<Long> lstClonePaybillPosts = null;
			
			//final List contains all OtherDtls object for which whole bill generation
			//should be done.
			List<HrEisOtherDtls> hrEisOtherDtls = new ArrayList<HrEisOtherDtls>();
			List<Object[]> empNonGovList = new ArrayList<Object[]>();
			
			//Map with all eligible posts and OtherDtls Object, get from GenerateBillService
			Map<Long,HrEisOtherDtls> hrEisOtherDtlsMap = new HashMap<Long,HrEisOtherDtls>();
			//Map which will contain only changed post id and corresponding  OtherDtls Object
			Map<Long,HrEisOtherDtls> generateBillPosts = new HashMap<Long,HrEisOtherDtls>();
			
			List leaveRecords = null;
			List traRecords = null;
			List loanRecords = null;
			List quarterRecords = null;
			List<Object[]> paybillNonVacantRecords = null;
			List<Object[]> paybillVacantRecords = null;
			IdGenerator idGenerator = new IdGenerator();
			

			long psrNo = 0;
			long paybillGrpId = 0;
			OrgPostMst orgPostMst = new OrgPostMst();

			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

			long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);

			PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			EmpLoanDAOImpl loanDAOImpl = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
			EmpLeaveDAOImpl leaveDAOImpl = new EmpLeaveDAOImpl(HrLeaveEmpDtls.class,serv.getSessionFactory());
			HrEmpTraMpgDaoImpl traDAOImpl = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class,serv.getSessionFactory());
			NonGovDeducDAOImpl nonGovDao = new NonGovDeducDAOImpl(HrPayNonGovDeduction.class,serv.getSessionFactory());
			
			int paybillMonth = objectArgs.get("from_Month") != null ? Integer.parseInt(objectArgs.get("from_Month").toString()) : -1;
			int paybillYear = objectArgs.get("from_Year") != null ? Integer.parseInt(objectArgs.get("from_Year").toString()) : -1;
			long billNo = 0;
			if (objectArgs.get("paybillNo") != null) {
				billNo = Long.parseLong(objectArgs.get("paybillNo").toString());
			}
			logger.info("Bill no from Generate bill Service is " + billNo);
			
			eligiblePosts = (List<Long>)objectArgs.get("eligiblePosts");
			hrEisOtherDtlsMap = (Map<Long,HrEisOtherDtls>)objectArgs.get("hrEisOtherDtlsMap");
			List<Long> eligibleVacantPostIdList = (List<Long>)(objectArgs.get("eligibleVacantPostIdList")!=null ?
					objectArgs.get("eligibleVacantPostIdList"):null);
			List<Long> eligibleNonVacantPostIdList = (List<Long>)(objectArgs.get("eligibleNonVacantPostIdList")!=null ?
					objectArgs.get("eligibleNonVacantPostIdList"):null);
			
			List<Long> paybillVacantPost = new ArrayList<Long>();
			List<Long> paybillNonVacantPost = new ArrayList<Long>();
			
			
			logger.info("eligiblePosts size is " + eligiblePosts.size());
			logger.info("hrEisOtherDtlsMap size is " + hrEisOtherDtlsMap.size());
			logger.info("eligibleVacantPostIdList is " + eligibleVacantPostIdList.size());
			logger.info("eligible NON VacantPostIdList is " + eligibleNonVacantPostIdList.size());
			
			/**
			 * 1. Fetch Current month pay bill records for Non vacant posts whose approve flag = 4
			 */
			//Fetch Pay bill records for given month, then we will update/delete 
			//records directly in Pay bill table
			boolean onlyVacantPosts = false;
			Map<Long,Long> nonVacantPaybillRecords = new HashMap<Long,Long>();
			paybillNonVacantRecords = paybillDAOImpl.getPaybillDataByLoc(paybillMonth, paybillYear, locId, billNo,onlyVacantPosts,4);
			logger.info("paybillRecords fethced for " + paybillMonth + "," + paybillYear);
			logger.info("size of paybill records " + paybillNonVacantRecords.size());
			if(paybillNonVacantRecords!=null)
			for(Object[] paybillObject : paybillNonVacantRecords) {
				Object[] paybillRow = (Object[])paybillObject;
				long paybillPostId = paybillRow!=null?Long.parseLong(paybillRow[0].toString()):0;
				long paybillId = paybillRow!=null?Long.parseLong(paybillRow[1].toString()):0;
				paybillGrpId = paybillRow!=null?Long.parseLong(paybillRow[2].toString()):0;
				logger.info("paybillPostId, paybillId " + paybillPostId + "," + paybillGrpId);
				if(paybillPostId!=0){					
					postPaybillRecords.add(paybillPostId);
					paybillNonVacantPost.add(paybillPostId);
					nonVacantPaybillRecords.put(paybillPostId, paybillId);		
				}
			////for DCPS>>>> Integration...
				GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrPayPaybill.class);
				genDao.setSessionFactory(serv.getSessionFactory());				
				HrPayPaybill paybillVo = (HrPayPaybill)genDao.read(paybillId);
				if(paybillVo!=null)
				{
					DeductionDtlsDAOImpl empDuducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
					paybillVo.setTotalDed(paybillVo.getTotalDed()-paybillVo.getDeduc9534());
					paybillVo.setNetTotal(paybillVo.getNetTotal()+paybillVo.getDeduc9534());
					
					 
					double amt = 0;
					EmpCompMpgDAOImpl empCompoMpg = new EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,serv.getSessionFactory());
					List<HrEisEmpCompMpg> newList = empCompoMpg.getSpecificEmpActiveList(""+dcpsId,dedycType,  locId);
					
					PayrollCommonDAO commonDAO = new PayrollCommonDAO(serv.getSessionFactory());
					SgvcFinYearMst sgvcFinYearMst = commonDAO.getFinYrInfo(new Date(),langId);
					
					long deducEmpId=paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpId();
					if(newList!=null && newList.size()>0)
					{
						List<HrPayDeductionDtls> deduclstDcps = empDuducDtlsDAO.getDeductionDtls(deducEmpId, dcpsId,-1,-1);
						if(deduclstDcps!=null && deduclstDcps.size()>0)
						{
							HrPayDeductionDtls HrPayDe = deduclstDcps.get(0);
							amt = empCompoMpg.getDCPSValue(  deducEmpId,paybillMonth ,  sgvcFinYearMst.getFinYearId());
							HrPayDe.setEmpDeducAmount(amt);
							empDuducDtlsDAO.update(HrPayDe);
							
						}
						
					}
					
					
					paybillVo.setDeduc9534(amt);
					paybillVo.setTotalDed(paybillVo.getTotalDed()+paybillVo.getDeduc9534());
					paybillVo.setNetTotal(paybillVo.getNetTotal()-paybillVo.getDeduc9534());
					genDao.update(paybillVo);
				////Code By Japen Ends Here
				
				
				}
			}
			//end
			
			/**
			 * 2. Fetch Current month pay bill records for vacant posts whose approve flag = 4
			 */
			onlyVacantPosts = true;
			Map<Long,Long> vacantPaybillRecords = new HashMap<Long,Long>();			
			paybillVacantRecords = paybillDAOImpl.getPaybillDataByLoc(paybillMonth, paybillYear, locId, billNo,onlyVacantPosts,4);
			logger.info("paybillRecords for vacant post fethced for " + paybillMonth + "," + paybillYear);
			logger.info("size of paybill records for vacant post " + paybillVacantRecords.size());
			if(paybillVacantRecords!=null)
			for(Object[] paybillObject : paybillVacantRecords) {
				Object[] paybillRow = (Object[])paybillObject;
				long paybillPostId = paybillRow!=null?Long.parseLong(paybillRow[0].toString()):0;
				long paybillId = paybillRow!=null?Long.parseLong(paybillRow[1].toString()):0;
				paybillGrpId = paybillRow!=null?Long.parseLong(paybillRow[2].toString()):0;
				logger.info("paybillPostId, paybillId " + paybillPostId + "," + paybillGrpId);
				if(paybillPostId!=0){					
					postPaybillRecords.add(paybillPostId);
					paybillVacantPost.add(paybillPostId);
					vacantPaybillRecords.put(paybillPostId, paybillId);					
				}
			}
			logger.info("postPaybillRecords size " + postPaybillRecords.size());
			
			
			/**
			 * 3. Remove different records from both collection.
			 * so that we can delete from HR_PAY_PAYBILL for 1 collection and
			 * we will insert records in HR_PAY_PAYBILL for another collection.
			 */
			logger.info("eligibleVacantPostIdList before performing remove operation " + eligibleVacantPostIdList.size());
			List tempVacantPostLst = new ArrayList(paybillVacantPost);
			//removing the same records, so that we have only those vacant post
			//records, which are there in HR_PAY_PAYBILL table, so that we can delete
			//those records from HR_PAY_PAYBILL.
			logger.info("tempVacantPostLst before performing remove operation " + tempVacantPostLst.size());
			logger.info("paybillVacantPost before performing remove operation " + paybillVacantPost.size());
			tempVacantPostLst.removeAll(eligibleVacantPostIdList); //delete from pay bill - all records for this collection.
			logger.info("tempVacantPostLst after performing remove operation " + tempVacantPostLst.size());
			logger.info("paybillVacantPost after performing remove operation " + paybillVacantPost.size());
			logger.info("eligibleVacantPostIdList b4 removing " + eligibleVacantPostIdList);
			logger.info("paybillVacantPost b4 removing " + paybillVacantPost);
			//removing same record from eligible vacant post list, so we will
			//generate bill for remaining vacant posts.
			eligibleVacantPostIdList.removeAll(paybillVacantPost); //generate bill for all this 
			logger.info("eligibleVacantPostIdList after performing remove operation " + eligibleVacantPostIdList.size());
			
			
			logger.info("eligible Non VacantPostIdList before performing remove operation " + eligibleNonVacantPostIdList.size());
			List tempNonVacantPostLst = new ArrayList(paybillNonVacantPost);
			//removing the same records, so that we have only those non-vacant post
			//records, which are there in HR_PAY_PAYBILL table, so that we can delete
			//those records from HR_PAY_PAYBILL.			
	
			tempNonVacantPostLst.removeAll(eligibleNonVacantPostIdList); //delete from pay bill - all records for this collection.
			
			//removing same record from eligible vacant post list, so we will
			//generate bill for remaining non-vacant posts.
			eligibleNonVacantPostIdList.removeAll(paybillNonVacantPost); //generate bill for all this
			logger.info("eligible Non VacantPostIdList after performing remove operation " + eligibleNonVacantPostIdList.size());
			
			//merge two collection, for which we have to delete records from pay bill table
			tempNonVacantPostLst.addAll(tempNonVacantPostLst);
			lstClonePaybillPosts = new ArrayList<Long>(tempNonVacantPostLst);
			logger.info("Delete records size " + tempNonVacantPostLst.size());						
			
			//delete different records from HR_PAY_PAYBILL table as they are
			//not required.(Not in eligible post records)
			if(lstClonePaybillPosts!=null && lstClonePaybillPosts.size()>0){
				logger.info("More elements are fond in Paybill table, removing those Post records");
				for(Long postId : lstClonePaybillPosts) {
					deletePaybillRecord(postId,paybillMonth, paybillYear,serv);										
				}
			}
			
			
			/**
			 * 4. Fetch Changed records for given month and year.
			 */
			lstChangedRecords = paybillDAOImpl.getChangedRecords(billNo, paybillMonth, paybillYear);
			logger.info("lstChangedRecords size " + lstChangedRecords.size());
			if(lstChangedRecords!=null &&  lstChangedRecords.size()>0)
				for(HrPayChangedRecords changedRecord : lstChangedRecords){
					long changePostId = changedRecord.getOrgPostMst().getPostId();
					logger.info("Inside change records loop, emp id and post id " + changePostId);
					if(eligiblePosts.contains(changePostId)) {
						
						if(!lstGenerateBillPosts.contains(changePostId))
						{
							lstGenerateBillPosts.add(changePostId);
							lstChangedPosts.add(changePostId);
						}
						//deleting existing record from HR_PAY_PAYBILL as we will
						//re generate whole pay bill for this month
						deletePaybillRecord(changePostId,paybillMonth, paybillYear,serv);
					}
				}
			
			
			/**
			 * 5. Fetch Employee's Loan records
			 */
			// Fetch Employees who have taken Loan for particular Bill No
			/*If bill number is not present then pass 0 for bill No, so it 
			will fetch all the data based on location code*/
			
			loanRecords = loanDAOImpl.getEmployeeByLocationId(locId, billNo,langId);
			logger.info("loanRecords size " + loanRecords.size());
			if(loanRecords!=null)
			for(Object loanObject : loanRecords) {
				Object[] loanRow = (Object[])loanObject;
				long loanEmpId = loanRow!=null?Long.parseLong(loanRow[0].toString()):0;
				long postId = loanRow!=null?Long.parseLong(loanRow[1].toString()):0;
				logger.info("Inside loan loop, emp id and post id " + loanEmpId + "," + postId);
				logger.info("Other Dtls Object found for post ID " + hrEisOtherDtlsMap.containsKey(postId));
				logger.info("eligiblePosts.contains(postId) " + eligiblePosts.contains(postId));
				logger.info("paybillNonVacantPost.contains(postId) " + paybillNonVacantPost.contains(postId));
				if(loanEmpId!=0){					
					empLoanRecords.add(loanEmpId);
					if(postId!=0 && hrEisOtherDtlsMap.containsKey(postId) && 
							eligiblePosts.contains(postId) && paybillNonVacantPost.contains(postId)
							&& !lstChangedPosts.contains(postId)) {
						logger.info("Other Dtls Object found for post ID " + postId);
						logger.info("this post " + postId + " is eligible for bill generation, and paybill record exist");
						objectArgs.put("hrEisOtherDtls", hrEisOtherDtlsMap.get(postId));
						objectArgs.put("loanLookupId", loanLookupId);
						objectArgs.put("paybillMonth", paybillMonth);
						objectArgs.put("paybillYear", paybillYear);
						objectArgs.put("advLookupId", advLookupId);
						logger.info("going to call processs Loan method");
						Map<String,Map<Long,HrPayPaybill>> loanProcessedMap = processLoans(objectArgs);
						logger.info("Process Loan completes.");
						Map<Long,HrPayPaybill> paybillVOMap= loanProcessedMap!=null && 
												loanProcessedMap.containsKey("postPaybillMap") ? 
												loanProcessedMap.get("postPaybillMap"):null;
						logger.info("paybillVOMap got from processLoans is " + paybillVOMap);
						HrPayPaybill paybillVO = paybillVOMap!=null && 
												paybillVOMap.containsKey(postId) ? 
												paybillVOMap.get(postId):null;
						logger.info("paybillVO is " + paybillVO);
						if(paybillVO!=null){
						   updatePaybill(paybillVO,serv);
						   if(lstGenerateBillPosts.contains(postId))
						       lstGenerateBillPosts.remove(postId);
						}
						//if PaybillVO null then something goes wrong, so add this postID in Generate Bill list
						//so, whole bill will be generated for that post.
						else if(postId!=0 && eligiblePosts.contains(postId) && !lstGenerateBillPosts.contains(postId)){
							lstGenerateBillPosts.add(postId);
							//deleting existing record from HR_PAY_PAYBILL as we will
							//re generate whole pay bill for this month
							deletePaybillRecord(postId,paybillMonth, paybillYear,serv);
						}							
					}
					else if(postId!=0 && eligiblePosts.contains(postId) && !lstGenerateBillPosts.contains(postId)){
						lstGenerateBillPosts.add(postId);
						//deleting existing record from HR_PAY_PAYBILL as we will
						//re generate whole pay bill for this month
						deletePaybillRecord(postId,paybillMonth, paybillYear,serv);
					}
				}
				
			}			
			//Loan list ready			
			
			/**
			 * 6. Fetch Employee Leave Records.
			 */
			//Fetch Employees list who has taken Leaves and Generate Pay bill			
			leaveRecords = leaveDAOImpl.getLeaveDataByDept(locId,billNo,langId); //need to change by Bill no also
			logger.info("leaveRecords size " + leaveRecords.size());
			if(leaveRecords!=null)
			for(Object leaveObject : leaveRecords) {
				Object[] leaveRow = (Object[])leaveObject;
				long leaveEmpId = leaveRow!=null?Long.parseLong(leaveRow[0].toString()):0;
				long postId = leaveRow!=null?Long.parseLong(leaveRow[1].toString()):0;
				logger.info("Inside leave records loop, emp id and post id " + leaveEmpId + "," + postId);
				if(leaveEmpId!=0){					
					empLeaveRecords.add(leaveEmpId);
				}
				if(postId!=0 && eligiblePosts.contains(postId) && !lstGenerateBillPosts.contains(postId)){					
					lstGenerateBillPosts.add(postId);
					//deleting existing record from HR_PAY_PAYBILL as we will
					//re generate whole pay bill for this month
					deletePaybillRecord(postId,paybillMonth, paybillYear,serv);
				}
			}
			//Leave List ready with Employee IDs who have taken Leaves.
			
			
			/**
			 * 7. Fetch Employee Quarter Records.
			 */
			//Fetch Employees list who has taken Leaves and Generate Pay bill
			QuaterMstDAOImpl hrEssQuaterDao = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());		
			quarterRecords = hrEssQuaterDao.getQuarterRecordsByLoc(billNo,locId,langId); //need to change by Bill no also
			logger.info("Quarter availed records size " + quarterRecords.size());
			if(quarterRecords!=null)
			for(Object quarterObject : quarterRecords) {
				Object[] quarterRow = (Object[])quarterObject;
				long quarterEmpId = quarterRow!=null?Long.parseLong(quarterRow[0].toString()):0;
				long postId = quarterRow!=null?Long.parseLong(quarterRow[1].toString()):0;
				logger.info("Inside leave records loop, emp id and post id " + quarterEmpId + "," + postId);
				if(quarterEmpId!=0){					
					empQuarterRecords.add(quarterEmpId);
				}
				//calculate HRR and HRA
				
				//hrrMap = new GenerateBillServiceCoreLogic().generatePassMap(inputMap);
				
				
				if(postId!=0 && hrEisOtherDtlsMap.containsKey(postId) && 
						eligiblePosts.contains(postId) && paybillNonVacantPost.contains(postId)
						&& !lstChangedPosts.contains(postId)) {
					double dpValue = 0;
					Map hrrMap = objectArgs;	
					HrEisOtherDtls otherDtlsVo = hrEisOtherDtlsMap.get(postId);
					Set orguserPostrlt = otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
					Map maxDaysMap = new GenerateBillService().checkMaxDayOfPostRecord(orguserPostrlt, paybillMonth, paybillYear);
					long maxDaysOfPost = Long.parseLong(maxDaysMap.get("maxDaysOfPost").toString());
					long maxDaysUserPostRltId = Long.parseLong(maxDaysMap.get("maxDaysUserPostRltId").toString());
					long maxDaysUserId = Long.parseLong(maxDaysMap.get("maxDaysUserId").toString());			
					long daysOfPost = maxDaysMap.get("daysOfPost")!=null?Long.parseLong(maxDaysMap.get("daysOfPost").toString()):0;
					
					Calendar cal = Calendar.getInstance();
			        cal.set(Calendar.YEAR, paybillYear);
			        cal.set(Calendar.MONTH, paybillMonth);
			        cal.set(Calendar.DATE, 1);
			        int  maxDay=cal.getActualMaximum(5);
					long revBasic = (otherDtlsVo.getotherCurrentBasic()*daysOfPost)/maxDay;
					
					hrrMap.put("otherDtlsVO", hrEisOtherDtlsMap.get(postId));
					hrrMap.put("monthGiven", paybillMonth);
					hrrMap.put("yearGiven", paybillYear);
					hrrMap.put("dpValue", dpValue);
					hrrMap.put("revBasic", revBasic);
					
					long empPostId= Long.valueOf(paybillDAOImpl.getPostId(otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId()).get(0).toString());
					HrPayOfficePostMpgDAOImpl hrPayOfficePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class,serv.getSessionFactory());
					HrPayOfficepostMpg hrPayOfficepostMpg = hrPayOfficePostMpgDAOImpl.getHrPayOfficepostMpg(postId);
					String city=hrPayOfficepostMpg.getDdoOffice().getDcpsDdoOfficeCityClass().toString();
					hrrMap.put("city", city.substring(10,city.length())!=null?city.substring(10,city.length()):"A");
					
					Map resultHrrAndHra = new GenerateBillCalculateRules().calculateHRR(hrrMap);
					double hrr =(Double)resultHrrAndHra.get("hrr");
					double oldHra=(Double)resultHrrAndHra.get("oldHra");
					double revisedHra=(Double)resultHrrAndHra.get("revisedHra");
					int approvalFlag = 4;
					HrPayPaybill payBillVO = readPaybillObjet(postId, paybillMonth, paybillYear, approvalFlag, serv);
					if(payBillVO!=null) {						
						payBillVO.setAllow0110(revisedHra);
						payBillVO.setGrossAmt(payBillVO.getGrossAmt()-oldHra+revisedHra);
						payBillVO.setDeduc9550(Math.round(hrr));
						 updatePaybill(payBillVO,serv);
						 if(lstGenerateBillPosts.contains(postId))
						       lstGenerateBillPosts.remove(postId);
					}
					//if PaybillVO null then something goes wrong, so add this postID in Generate Bill list
					//so, whole bill will be generated for that post.
					else if(postId!=0 && eligiblePosts.contains(postId) && !lstGenerateBillPosts.contains(postId)){
						lstGenerateBillPosts.add(postId);
						//deleting existing record from HR_PAY_PAYBILL as we will
						//re generate whole pay bill for this month
						deletePaybillRecord(postId,paybillMonth, paybillYear,serv);
					}			
				}				
				else if(postId!=0 && eligiblePosts.contains(postId) && !lstGenerateBillPosts.contains(postId)){					
					lstGenerateBillPosts.add(postId);
					//deleting existing record from HR_PAY_PAYBILL as we will
					//re generate whole pay bill for this month
					deletePaybillRecord(postId,paybillMonth, paybillYear,serv);
				}
			}
			
			
			/**
			 * 8. Fetch Employees who has availed the Vehicle
			 */
			//Fetch Employees list who has availed Vehicle and calculate TA			
			traRecords = traDAOImpl.getEmpListByLocation(locId,billNo,langId);
			logger.info("traRecords size " + traRecords.size());
			if(traRecords!=null)
			for(Object traObject : traRecords) {
				Object[] traRow = (Object[])traObject;
				long traEmpId = traRow!=null?Long.parseLong(traRow[0].toString()):0;
				long postId = traRow!=null?Long.parseLong(traRow[1].toString()):0;
				logger.info("Inside tra records loop, emp id and post id " + traEmpId + "," + postId);
				if(traEmpId!=0){					
					empTraRecords.add(traEmpId);
				}
				if(postId!=0 && eligiblePosts.contains(postId) && !lstGenerateBillPosts.contains(postId)){
					lstGenerateBillPosts.add(postId);
					//deleting existing record from HR_PAY_PAYBILL as we will
					//re generate whole pay bill for this month
					deletePaybillRecord(postId,paybillMonth, paybillYear,serv);
				}
			}
			//Vehicle availed Employee list ready
			
			/**
			 * 9. Fetch Employee Non government list. 
			 */
			//fetching emp non gov list
			empNonGovList = nonGovDao.getListByLocId(locId, billNo);
			Map<Long,Long> nonGovMap = new HashMap<Long, Long>();
			if(empNonGovList!=null && empNonGovList.size()>0) {
				logger.info("empNonGovList size for bill number " + billNo + " is " + empNonGovList.size());
				for(Object[] nonGovObjects : empNonGovList) {
					Object[] nonGovRow = (Object[])nonGovObjects;
					long nonGovEmpId = nonGovRow!=null?Long.parseLong(nonGovRow[1].toString()):0;
					long nonGovPostId = nonGovRow!=null?Long.parseLong(nonGovRow[1].toString()):0;					
					if(nonGovEmpId!=0 && nonGovPostId!=0  && eligiblePosts.contains(nonGovPostId))
						nonGovMap.put(nonGovPostId, nonGovEmpId);
				}
			}
			//ended
			
			for(long postId : eligibleNonVacantPostIdList) {
				if(!lstGenerateBillPosts.contains(postId))
					lstGenerateBillPosts.add(postId);
			}
			
			/**
			 * 10. Prepare hrEisOtherDtls List to generate Bill. It will be
			 * passed to GenerateBillService for whole Bill Generatation.
			 */
			//Check all the changed records post IDs are there in OtherDtls Map
			//which is fetched from GenerateBillService.
			logger.info("eligible post for which we have to generate bill is " + lstGenerateBillPosts.size());
			if(lstGenerateBillPosts!=null && lstGenerateBillPosts.size()>0)
			for(Long postId : lstGenerateBillPosts) {
				if(hrEisOtherDtlsMap.containsKey(postId)) {
					logger.info("found other dtls object for post id " + postId);
					generateBillPosts.put(postId, hrEisOtherDtlsMap.get(postId));
					//remove from Non Government Map also, as these post will be
					//regenerated, and it includes Non government logic also.
					if(nonGovMap.containsKey(postId))
						  nonGovMap.remove(postId);
					//adding in final List for which we generate whole bill
					logger.info("adding in hreisotherdtls list");
					hrEisOtherDtls.add(hrEisOtherDtlsMap.get(postId));
				}
			}
			logger.info("hreisotherdtls list size is " + hrEisOtherDtls.size());
			
			/**
			 * 11. Process Non Gov data, insert into hr_pay_payslip_nongov table.
			 */
			if(nonGovMap!=null && nonGovMap.size()>0) {
				Calendar calc = Calendar.getInstance();
				calc.set(Calendar.MONTH, paybillMonth - 1);
				calc.set(Calendar.YEAR, paybillYear);
				java.util.Date approvalDate = calc.getTime();
				Iterator postIterator = nonGovMap.keySet().iterator();
				objectArgs.put("paybill_Month", paybillMonth);
				objectArgs.put("paybill_Year", paybillYear);
				objectArgs.put("approvalDate", approvalDate);
				while(postIterator.hasNext()) {
					Object nonGovPostIdObject = postIterator.next();
					long nonGovPostId=0;
					long empId = 0;
					HrPayPaybill paybillVO = null;
					logger.info("fetching non gov post and emp id");
					if(nonGovPostIdObject!=null)
						nonGovPostId = Long.valueOf(nonGovPostIdObject.toString());
					if(nonGovPostId!=0 && nonGovMap.containsKey(nonGovPostId))
						empId = nonGovMap.get(nonGovPostId);
					logger.info("non gov post and emp id is " + nonGovPostId + "   " + empId);
					objectArgs.put("empId", empId);					
					if(nonVacantPaybillRecords.containsKey(nonGovPostId)) {
						logger.info("paybill records contains record for this post id");
						long paybillId = nonVacantPaybillRecords.get(nonGovPostId);
						paybillVO = paybillDAOImpl.read(paybillId);
						logger.info("paybill record found");
					}
					if(paybillVO!=null) {
						objectArgs.put("paybillVO", paybillVO);
						int replyCode = new GenerateBillNonGovCalc().insertPaybillNonGovData(objectArgs);
						logger.info("Reply code from Non Gov should be 0");
						logger.info("Reply code from Non Gov is " + replyCode);
						if (replyCode != 0) {
							throw new Exception("Non Gov Error Occured..!");
						}
					}
					else if(eligiblePosts.contains(nonGovPostId)) {
						if(hrEisOtherDtlsMap.containsKey(nonGovPostId)) {
						   hrEisOtherDtls.add(hrEisOtherDtlsMap.get(nonGovPostId));			
						}
					}
				}
			}								
			
			/**
			 * 12. Insert vacant posts list in hr_pay_paybill table.
			 */
			if(eligibleVacantPostIdList!=null && eligibleVacantPostIdList.size()>0) {
				objectArgs.put("paybillGrpId", paybillGrpId);		
				objectArgs.put("vacantPostIdList",eligibleVacantPostIdList);	
				objectArgs.put("paybillMonth",paybillMonth);
				objectArgs.put("paybillYear",paybillYear);
				
				Map vacantResultMap = insertVacantPostData(objectArgs);
				long paybillGenerated  = 0;
				if(vacantResultMap!=null) {
					if(vacantResultMap.containsKey("paybillGenerated"))
					paybillGenerated =  Long.valueOf(vacantResultMap.get("paybillGenerated").toString());
					if(vacantResultMap.containsKey("paybillGrpId"))
						paybillGrpId = Long.valueOf(vacantResultMap.get("paybillGrpId").toString());
				}
				logger.info("Vacant post generated are " + paybillGenerated);
				
				//comment ended
				eligibleVacantPostIdList.clear();
			}
			
			/**
			 * 13. If no employee found to generate whole month pay bill, then update
			 * paybill_head_mpg tables's approve flag to 0 = Generated bill.
			 */
			if((hrEisOtherDtls!=null && hrEisOtherDtls.size()==0) && 
					(paybillNonVacantRecords!=null || paybillVacantRecords!=null) && (paybillNonVacantRecords.size()!=0 || 
							paybillVacantRecords.size()!=0)) {
				logger.info("hrEisOtherDtls size is 0 and paybill current month records are there");
				logger.info("so we are updating approve flag of paybill_head_mpg to 0");
				
				//dumping records for next month
				/*logger.info("going to dump records from GeneratePaybillService");
				Map dumpRecordsParaMap = objectArgs;
				
				dumpRecordsParaMap.put("month", paybillMonth);
				dumpRecordsParaMap.put("year", paybillYear);
				dumpRecordsParaMap.put("billNo", billNo);
				dumpRecordsParaMap.put("approveFlag", 4); //next month record, so approve flag=4
				dumpRecordsParaMap.put("locId", locId);
				Map paybillDumpMap = copyPaybillForNextMonth(objectArgs);
				if(paybillDumpMap!=null && paybillDumpMap.size()>0) {
					long nextMonthPaybillGrpId = paybillDumpMap.get("paybillGrpId")!=null?
							Long.valueOf(paybillDumpMap.get("paybillGrpId").toString()):0;
					long nextMonthPaybillHeadId = paybillDumpMap.get("paybillHeadId")!=null?
									Long.valueOf(paybillDumpMap.get("paybillHeadId").toString()):0;
					logger.info("Next month data has been dumped in paybill table with group id " + nextMonthPaybillGrpId);
					logger.info("Next month data has been dumped in paybill head mpg" +
							" table with group id " + nextMonthPaybillHeadId);
				}*/
				//dump record code end
				
				int updatedRowsBillHead = updatePaybillHeadMpg(paybillGrpId, serv);
				if(updatedRowsBillHead<=0) {
					logger.info("No record has been updated for paybill group id " + paybillGrpId + " in paybill_head_mpg table");
					throw new Exception("pabyill group id not found in paybill_head_mpg table");
				}
					
			}
			
			logger.info("Final Other Details List to be passed in GenerateBillService");
			for(HrEisOtherDtls hrEisOtherDtlsTemp : hrEisOtherDtls) {
				logger.info("Other Details id is " + hrEisOtherDtlsTemp.getOtherId());
				logger.info("Employee id of Org Emp Mst is " + hrEisOtherDtlsTemp.getHrEisEmpMst().getOrgEmpMst().getEmpId());
			}
			logger.info("before passing - hreisotherdtls list size is " + hrEisOtherDtls.size());
			objectArgs.put("hrEisOtherDtls", hrEisOtherDtls);
			objectArgs.put("vacantPostIdList", eligibleVacantPostIdList);			
			objectArgs.put("paybillGrpId", paybillGrpId);
						
			
		
		return resultMap;
	}
	
	/**
	 * Method will dump pay bill records for next month with approve flag=4.
 	 * Data will be inserted in HR_PAY_PAYBILL and paybill_head_mpg table.
	 * Two methods will be called from this - dumpPaybillRecords and dumpPaybillHeadMpgRecords
	 * @param objectArgs - which contains month, year, locId, billNo, approveFlag
	 * @return Map - contains paybillGrpId and paybillHeadId
	 * @throws Exception
	 */
	public Map copyPaybillForNextMonth(Map objectArgs) throws Exception{
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		int month = objectArgs.get("month")!=null?Integer.parseInt(objectArgs.get("month").toString()):0;
		int year = objectArgs.get("year")!=null?Integer.parseInt(objectArgs.get("year").toString()):0;
		long locId = objectArgs.get("locId")!=null?Long.parseLong(objectArgs.get("locId").toString()):0;
		long billNo = objectArgs.get("billNo")!=null?Long.parseLong(objectArgs.get("billNo").toString()):0;
		int approveFlag = objectArgs.get("approveFlag")!=null?Integer.parseInt(objectArgs.get("approveFlag").toString()):0;
		long paybillGrpId = dumpPaybillRecords(month, year, locId, billNo, serv, approveFlag, objectArgs);
		logger.info("Passing Month and year as " + month + " " + year);
		long paybillHeadId = dumpPaybillHeadMpgRecords(month, year, locId, billNo, serv, approveFlag, paybillGrpId, objectArgs);
		Map copyBillMap = new HashMap();
		copyBillMap.put("paybillGrpId", paybillGrpId);
		copyBillMap.put("paybillHeadId", paybillHeadId);
		return copyBillMap;
	}
	
	/**
	 * Method is used to dump Paybill records for next month with approve flag=4.
	 * @param month - Month for which records should be dumped.
	 * @param year - Year for which Records should be dumped.
	 * @param locId - Location ID
	 * @param billNo - Bill number for which records should be dumped.
	 * @param serv - Service Locator.
	 * @param approveFlag - Approve flag to be inserted.
	 * @param objectArgs - Map is required for ID generator Facility
	 * @return long - Paybill Group ID.
	 */
	public long dumpPaybillRecords(int month, int year,long locId,long billNo,ServiceLocator serv,int approveFlag,Map objectArgs)
	   throws Exception{
		logger.info("Inside dump Record function");
		List<HrPayPaybill> paybillRecords = null;
		IdGenerator idGen = new IdGenerator();
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		paybillRecords = paybillDAOImpl.getAllPaybillDataByLoc(month, year, locId, billNo,approveFlag);
		logger.info("Inside dump Record function - Pabyll records size for month " + month + " is " + paybillRecords.size());
		long payBillId = 0;
		long paybillGrpId=0;
		int nextMonth = month;
		int nextYear = year;
		if(month==12) {
			nextMonth=1;
			nextYear++;
		}
		else {
			nextMonth++;
		}
		for(HrPayPaybill hrPayPaybill : paybillRecords) {
			HrPayPaybill paybillClone = null;			
			Object clonePaybillObject = new GenerateBillServiceHelper().copy(hrPayPaybill);
			if(clonePaybillObject!=null) {
				paybillClone = (HrPayPaybill)clonePaybillObject;
				payBillId = idGen.PKGenerator("HR_PAY_PAYBILL", objectArgs);
				if(paybillGrpId==0)
					paybillGrpId=payBillId;
				paybillClone.setId(payBillId);
				paybillClone.setPaybillGrpId(paybillGrpId);
				paybillClone.setMonth(nextMonth);
				paybillClone.setYear(nextYear);
				paybillDAOImpl.create(paybillClone);
			}
		}
		return paybillGrpId;
	}
	
	
	/**
	 * Method is used to dump Pay bill records for next month with approve flag=4 in 
	 * paybill_head_mpg table.
	 * @param month - Month for which records should be dumped.
	 * @param year - Year for which Records should be dumped.
	 * @param locId - Location ID
	 * @param billNo - Bill number for which records should be dumped.
	 * @param serv - Service Locator.
	 * @param approveFlag - Approve flag to be inserted.
	 * @param objectArgs - Map is required for ID generator Facility
	 * @return long - Pay bill Group ID.
	 */
	public long dumpPaybillHeadMpgRecords(int month, int year,long locId,long billNo,ServiceLocator serv,int approveFlag,
			long paybillGrpId,Map objectArgs)
	 throws Exception {
		logger.info("Inside dump Record function");
		List<PaybillHeadMpg> paybillHeadMpgRecords = null;
		IdGenerator idGen = new IdGenerator();
		PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
		paybillHeadMpgRecords = paybillHeadMpgDAOImpl.getDatabyMonthYear(month, year, locId, billNo,2500337,approveFlag);
		logger.info("Inside dump Record function - paybillHeadMpgRecords size for month " + month + " is " + paybillHeadMpgRecords.size());
		long payBillHeadId = 0;			
		
		int nextMonth = month;
		int nextYear = year;
		if(month==12) {
			nextMonth=1;
			nextYear++;
		}
		else {
			nextMonth++;
		}
		
		//Added by abhilash
		
		long voucherNo=0;
		Date voucherdate;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(objectArgs.get("voucherNo")!=null && !objectArgs.get("voucherNo").equals(""))
			voucherNo=Long.parseLong(objectArgs.get("voucherNo").toString());
				
			logger.info("voucherNo***********"+voucherNo);
		voucherdate=(Date)(objectArgs.get("voucherDate")!=null&&!(objectArgs.get("voucherDate").equals(""))?(objectArgs.get("voucherDate")):" ");
		
		
		//Ended by abhilash

		
		logger.info("Going to set month and year as " + nextMonth + " " + nextYear);
		for(PaybillHeadMpg paybillHeadMpg : paybillHeadMpgRecords) {			
			
			PaybillHeadMpg paybillHeadMpgClone = null;
			Object cloneObject = new GenerateBillServiceHelper().copy(paybillHeadMpg);
			if(cloneObject!=null) {
				paybillHeadMpgClone = (PaybillHeadMpg)cloneObject;
			//payBillHeadId = idGen.PKGenerator("paybill_head_mpg", objectArgs);
				payBillHeadId = idGen.PKGenerator("paybill_head_mpg", objectArgs);
				paybillHeadMpgClone.setId(payBillHeadId);
				paybillHeadMpgClone.setHrPayPaybill(paybillGrpId);
				paybillHeadMpgClone.setMonth(nextMonth);
				paybillHeadMpgClone.setYear(nextYear);
				paybillHeadMpgClone.setApproveFlag(4); //next month records, so setting approve flag=4
				
				//Added by abhilash
				paybillHeadMpgClone.setVoucherNumber(voucherNo);
				paybillHeadMpgClone.setVoucherDate(voucherdate);
				//Ended by abhilash
				
				paybillHeadMpgDAOImpl.create(paybillHeadMpgClone);
			}
			
		}
		logger.info("Last paybillHeadId before returning from dump record " + payBillHeadId);
		return payBillHeadId;
	}
	
	
	/**
	 * Method will delete record from HR_PAY_PAYBILL table
	 * based on given post ID, month and year.
	 * @param postId
	 * @param month
	 * @param year
	 * @param serv
	 * @throws Exception
	 */
	public void deletePaybillRecord(long postId,int month, int year, ServiceLocator serv) throws Exception {		
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		int rowsDeleted = paybillDAOImpl.deletePaybillRecord(postId, month, year);
		logger.info("2 records SHOULD BE deleted for postId " + postId);
		logger.info(rowsDeleted + " records ARE deleted for PostId " + postId);		
	}
	
	/**
	 * Method will delete record from HR_PAY_PAYBILL table
	 * based on given post ID, month and year.
	 * @param postId
	 * @param month
	 * @param year
	 * @param serv
	 * @throws Exception
	 */
	public void deletePaybillLoanRecord(long postId,long paybillId, String loanTypeIds,ServiceLocator serv) throws Exception {		
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		int rowsDeleted = paybillDAOImpl.deletePaybillLoanRecord(paybillId, loanTypeIds);		
		logger.info(rowsDeleted + " records ARE deleted from Paybill loan tables for PostId " + postId);
		logger.info(rowsDeleted + " records ARE deleted from Paybill loan tables for laontype id " + loanTypeIds);
	}
			
	/**
	 * Method will update the paybill_head_mpg table's
	 * approve_flag to 0, i.e generated bill status.
	 * @param paybill group ID
	 * @param serv
	 * @return integer number of rows updated.
	 * @throws Exception
	 */
	public int updatePaybillHeadMpg(long paybillGrpId, ServiceLocator serv) throws Exception {
		logger.info("Inside update of paybill_head_mpg's approve flag");
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		int rowsUpdated = paybillDAOImpl.updatePaybillHeadMpg(paybillGrpId);		
		logger.info(rowsUpdated + " records ARE updated for PaybillGrp Id " + paybillGrpId);
		return rowsUpdated;
	}
	
	/**
	 * Method will read the Pay bill Table for given month year, post Id
	 * and approve flag of pay bill.
	 * @param paybill group ID
	 * @param serv
	 * @return HrPayPaybill object if found, else null
	 * @throws Exception
	 */
	public HrPayPaybill readPaybillObjet(long postId, int month, int year, int approvalFlag,ServiceLocator serv)
	throws Exception {
		logger.info("Inside readPaybillObjet for month ");
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		HrPayPaybill paybill = paybillDAOImpl.getPaybillDataByPost(postId, approvalFlag, month, year);		
		return paybill;
	}
	
	/**
	 * Method will update Pay bill Table - HR_PAY_PAYBILL with given HrPayPaybill Object
	 * @param HrPayPaybill object
	 * @param serv
	 * @return none (void)
	 * @throws Exception
	 */
	public void updatePaybill(HrPayPaybill paybillVO,ServiceLocator serv)
	throws Exception {
		logger.info("Inside updatePaybill ");
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		paybillDAOImpl.update(paybillVO);		
	}
	
	/**
	 * Method will insert Vacant post list records in
	 * HR_PAY_PAYBILL table.
	 * @param objectArgs - Contains List of PostIDs to be inserted
	 * 								Pay bill Group ID - Not Mandatory
	 * @return Map - contains number of records inserted as paybillGenerated
	 * 				 and pay bill group id as paybillGrpId 
	 */
	public Map insertVacantPostData(Map objectArgs) throws Exception {
		List<Long> vacantPostIdList = (List<Long>)(objectArgs.get("vacantPostIdList")!=null ?
				objectArgs.get("vacantPostIdList"):null);
		long paybillGrpId = objectArgs.get("paybillGrpId")!=null ? Long.valueOf(objectArgs.get("paybillGrpId").toString()):0;
		HrPayPaybill hrVacantPaybill = null;
		IdGenerator idGen = new IdGenerator();
		
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
		//added by manish 
		
		int month=Integer.valueOf(objectArgs.get("paybillMonth").toString());
		int year=Integer.valueOf(objectArgs.get("paybillYear").toString());
		
		//ended  by manish
		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

		long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

		long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
		
		long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
				
		PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		long psrNo=0;
		long paybillGenerated=0;
		if (vacantPostIdList != null && vacantPostIdList.size() > 0) {

			logger.info("going to generate paybill for vacant post ");
			for (int vacantCnt = 0; vacantCnt < vacantPostIdList.size(); vacantCnt++) {
				hrVacantPaybill = new HrPayPaybill();

				// Also capturing postId
				long PostId = Long.parseLong(vacantPostIdList.get(vacantCnt).toString());
				OrgPostMst PayBillPostMst = orgPostMstDaoImpl.read(PostId);
				hrVacantPaybill.setOrgPostMst(PayBillPostMst);
				psrNo = paybillDAOImpl.getPsrNoFromPostId(PayBillPostMst.getPostId());
				hrVacantPaybill.setPsrNo(psrNo);
				hrVacantPaybill.setCmnDatabaseMst(cmnDatabaseMst);
				hrVacantPaybill.setCmnLocationMst(cmnLocationMst);
				hrVacantPaybill.setCreatedDate(new Date());
				hrVacantPaybill.setMonth(month);
				hrVacantPaybill.setYear(year);
				hrVacantPaybill.setOrgUserMstByCreatedBy(orgUserMst);
				hrVacantPaybill.setOrgPostMstByCreatedByPost(orgPostMst);
				hrVacantPaybill.setHrEisEmpMst(null);
				long vacantPayBillId = 0;
				
				synchronized (idGen) {
					vacantPayBillId = idGen.PKGenerator("HR_PAY_PAYBILL", objectArgs);
				}

				hrVacantPaybill.setId(vacantPayBillId);				

				if (paybillGrpId == 0) {
					paybillGrpId = vacantPayBillId;

				}
				logger.info("paybillGrpId in vacant is " + paybillGrpId);
				hrVacantPaybill.setPaybillGrpId(paybillGrpId);
				hrVacantPaybill.setTrnCounter(Integer.valueOf(1));
				paybillDAOImpl.create(hrVacantPaybill);
				
				paybillGenerated++;
			}
		}
		objectArgs.put("paybillGenerated", paybillGenerated);
		objectArgs.put("paybillGrpId", paybillGrpId);
		return objectArgs;
	}
	
	/**
	 * Method will calculate Loans/Advances for given employee and update
	 * hr_pay_paybill_loan_dtls table, it will also return paybillVO object
	 * with updated EMI values, and it will be updated in HR_PAY_PAYBILL table.
	 * @param objectArgs
	 * @return Map
	 * @throws Exception
	 */
	public Map<String,Map<Long,HrPayPaybill>> processLoans(Map objectArgs) throws Exception {	
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

		long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

		long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
		
		long primaryPostId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
		OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		OrgPostMst orgPostMst = orgPostMstDaoImpl.read(primaryPostId);
		
		PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		long loanLookupId = objectArgs.get("loanLookupId") != null ? Long.parseLong(objectArgs.get("loanLookupId").toString()) : -1;
		long advLookupId = objectArgs.get("advLookupId") != null ? Long.parseLong(objectArgs.get("advLookupId").toString()) : -1;
		String lookupIDs = loanLookupId+","+advLookupId;
		logger.info("Lookup ID for Fetching EDP Code and Type IDs are " + lookupIDs);
		HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());		
		List<HrPayEdpCompoMpg> edpList = hrEdp.getDataByLookupIDs(lookupIDs);
		if(edpList!=null && edpList.size()>0)
			logger.info("Edp List size is " + edpList.size());
		HrPayEdpCompoMpg edpComp = null;
		int approveFlag=4; 
		
		GenerateBillService generateBillServ = new GenerateBillService();
		int monthGiven = objectArgs.get("paybillMonth") != null ? Integer.parseInt(objectArgs.get("paybillMonth").toString()) : -1;
		int yearGiven = objectArgs.get("paybillYear") != null ? Integer.parseInt(objectArgs.get("paybillYear").toString()) : -1;		
		HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls)(objectArgs.get("hrEisOtherDtls")!=null?
				objectArgs.get("hrEisOtherDtls"):null);
		int grade=0;
		if(hrEisOtherDtls!=null && hrEisOtherDtls.getHrEisEmpMst()!=null && hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst()!=null 
				&& hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getOrgGradeMst()!=null)
			grade = (int)hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getOrgGradeMst().getGradeId();
		long maxDaysFlag=0;
		
		Set orguserPostrlt = hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
		/*Map maxDaysMap = generateBillServ.checkMaxDayOfPostRecord(orguserPostrlt, monthGiven, yearGiven);
		long maxDaysOfPost = Long.parseLong(maxDaysMap.get("maxDaysOfPost").toString());
		long maxDaysUserPostRltId = Long.parseLong(maxDaysMap.get("maxDaysUserPostRltId").toString());
		long maxDaysUserId = Long.parseLong(maxDaysMap.get("maxDaysUserId").toString());			
		long daysOfPost = maxDaysMap.get("daysOfPost")!=null?Long.parseLong(maxDaysMap.get("daysOfPost").toString()):0;*/
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		
		Map postPaybillMap = new HashMap();
		
		for (Iterator upIt = orguserPostrlt.iterator(); upIt.hasNext();) {			
			OrgUserpostRlt orgUPRlt = (OrgUserpostRlt) upIt.next();
			long postId = orgUPRlt!=null?orgUPRlt.getOrgPostMstByPostId().getPostId():0;
			Map inputMap = objectArgs;
			HrPayPaybill payBillVO = readPaybillObjet(postId, monthGiven, yearGiven, approveFlag, serv);
			List lstHrPayLoanEmpDtls = new ArrayList();
			if ((orgUPRlt.getEndDate() == null
					|| (generateBillServ.monthofDate(orgUPRlt.getEndDate()) >= monthGiven && generateBillServ.yearofDate(orgUPRlt
							.getEndDate()) == yearGiven) || (generateBillServ.yearofDate(orgUPRlt.getEndDate()) > yearGiven))) {
				
				/*if(orguserPostrlt.size() > 1) {					 						 
						 Map OrgUserpostRlt = inputMap;
						 OrgUserpostRlt.put("orgUPRlt",orgUPRlt);
						 OrgUserpostRlt.put("month",monthGiven);
						 OrgUserpostRlt.put("year",yearGiven);						 
						 daysOfPost = new GenerateBillCalculation().daysOfPost(OrgUserpostRlt);					 				
				 }
				 logger.info("The maxDaysUserPostRltId is "+maxDaysUserPostRltId+" the orgUPRlt.getEmpPostId() "+orgUPRlt.getEmpPostId());
				if(maxDaysUserPostRltId==orgUPRlt.getEmpPostId() )
				{
					//by manoj for 10-20 days issue of one record start
					if(orguserPostrlt.size()==1 && daysOfPost<15)
					{
						maxDaysFlag=0;
					}
					else
					{
						//end by manoj for 10-20 days issue of one record
						maxDaysFlag=1;
					}//by manoj for 10-20 days issue of one record
					
				}*/
				long empId = 0;
				if(hrEisOtherDtls!=null && hrEisOtherDtls.getHrEisEmpMst()!=null)
				 empId = hrEisOtherDtls.getHrEisEmpMst().getEmpId();
				/*Map empLeaveMap = generateBillServ.empLeaveDtls(empId,serv,monthGiven,yearGiven);
				int LWPFlag = Integer.parseInt(empLeaveMap.get("LWPFlag").toString());*/
				double totLoan = 0;
				double totAdv=0,advEMI=0,gorssDedAdv=0;
				for(int i=0; i< edpList.size(); i++)
				{			
					
					edpComp = new HrPayEdpCompoMpg();									
					edpComp = edpList.get(i);					
					//for 10-20 days issues by manoj
					long isOneTime = edpComp!=null ? edpComp.getIsOneTime():0;//1 means not oneTime and 0 means one time				
					String edpCode = edpComp!=null ? String.valueOf(edpComp.getRltBillTypeEdp().getEdpCode()):"";					
					long compoType = edpComp!=null ?  edpComp.getCmnLookupId():0;
					String compoCode = edpComp!=null?edpComp.getTypeId():"";
					
					OrgEmpMst orgEmpMst = hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst();
					long orgEmpId=0;
					if(orgEmpMst!=null)
					 orgEmpId = orgEmpMst.getEmpId();
					logger.info("You are Entered in the Loan Part:-");
					
					if(compoType == loanLookupId) {
						String payBillMthdName = "setLoan"+edpCode;
						String payBillIntMthdName = "setLoanInt"+edpCode;
						logger.info("The Component Code is :-"+compoCode);
					EmpLoanDAOImpl hrLoanEmpDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
					List<HrLoanEmpDtls> empLoanList = hrLoanEmpDao.getEmpLoanDetail(orgEmpId, compoCode);//compocode is loan type id
					if(empLoanList!=null && empLoanList.size() > 0) {
						Map loanMap = objectArgs;
						loanMap.put("compoCode", compoCode);
						loanMap.put("month", monthGiven);
						loanMap.put("year", yearGiven);
						loanMap.put("empLoanList", empLoanList);
						if(payBillVO!=null) 
							loanMap.put("paybillVO", payBillVO);
						
						Map loanResultMap = new GenerateBillLoanAndAdvanceHelper().calculateLoans(loanMap);
						long loanEmi = loanResultMap.get("loanEmi")!=null?Long.valueOf(loanResultMap.get("loanEmi").toString()):0;
						long loanIntEmi = loanResultMap.get("loanIntEmi")!=null?Long.valueOf(loanResultMap.get("loanIntEmi").toString()):0;
						lstHrPayLoanEmpDtls = (List)(loanResultMap.get("lstHrPayLoanEmpDtls")!=null?loanResultMap.get("lstHrPayLoanEmpDtls"):null);
						Map deactivateLoanRecords = (Map)(loanResultMap.get("deactivateLoanRecords")!=null?loanResultMap.get("deactivateLoanRecords"):null);						
						logger.info("Deactive employee loan map size is " + deactivateLoanRecords.size());
						logger.info("Deactive employee loan map is " + deactivateLoanRecords);
						
						if(deactivateLoanRecords!=null && deactivateLoanRecords.size()>0) {
							int changedMonth = 0;
							int changedYear = 0;
							Calendar calChangedRec = Calendar.getInstance();
							if((calChangedRec.get(Calendar.MONTH)+1) >=12 ) {
								changedMonth = 1; 
								changedYear = calChangedRec.get(Calendar.YEAR)+1; //next year
								logger.info("last month of year, so setting next month as 1 and next year");
								logger.info("Next month and year is " + changedMonth + " " + changedYear);
							}
							else {
								changedMonth = calChangedRec.get(Calendar.MONTH)+1;
								changedYear = calChangedRec.get(Calendar.YEAR);
								logger.info("Not last month, so setting next month and next year");
								logger.info("Next month and year is " + changedMonth + " " + changedYear);
							}
							inputMap.put("changedMonth", changedMonth);
							inputMap.put("changedYear", changedYear);
							inputMap.put("changedPostId", payBillVO.getOrgPostMst().getPostId());
							inputMap.put("cmnDatabaseMst",cmnDatabaseMst);
							inputMap.put("orgUserMst",orgUserMst);
							inputMap.put("orgPostMst",orgPostMst);
							long changedRecPK = new GenerateBillServiceHelper().insertChangedRecord(inputMap);														
							logger.info("Changed Records are inserted for employees whose loans are deactivated");
							logger.info("Changed Records are inserted - PK is " + changedRecPK);												
						}
						
						logger.info("Changed Records are inserted for employees whose loans are deactivated");
						
						logger.info("List size for loan insertion is " + lstHrPayLoanEmpDtls.size());													
						
	
						totLoan = totLoan+loanEmi+loanIntEmi;												
						logger.info("loan ********* the param value for edpCode "+edpCode + " is "+(loanEmi+loanIntEmi));
						if(payBillVO!=null) {
							Class payBill = payBillVO.getClass();
							Object[] objArgs = new Object[]{new Double(loanEmi)};
							Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[]{double.class});
							payBillMthd.invoke(payBillVO, objArgs);
							
							
							Object[] objArgsInt = new Object[]{new Double(loanIntEmi)};
							Method payBillIntMthd = payBill.getMethod(payBillIntMthdName, new Class[]{double.class});
							payBillIntMthd.invoke(payBillVO, objArgsInt);
							
							if(postId!=0)
							 postPaybillMap.put(postId, payBillVO);
							logger.info("Map in processLoan  - Loans - for loop is " + postPaybillMap);							
						
						
						GenericDaoHibernateImpl paybillLoanDtlsDao = new GenericDaoHibernateImpl(HrPayPaybillLoanDtls.class);
						paybillLoanDtlsDao.setSessionFactory(serv.getSessionFactory());
						if (lstHrPayLoanEmpDtls != null)
							for (int loanCnt = 0; loanCnt < lstHrPayLoanEmpDtls.size(); loanCnt++) {
								HrPayPaybillLoanDtls hrPayPaybillLoanDtls = (HrPayPaybillLoanDtls) lstHrPayLoanEmpDtls.get(loanCnt);
								hrPayPaybillLoanDtls.setPaybillId(payBillVO);
								logger.info("From GeneratePaybillService - going to insert Loan record");
								paybillLoanDtlsDao.getSession().saveOrUpdate(hrPayPaybillLoanDtls);
							}
						}
					}
					//if loan records not found then set value to 0, as we are dumping
					//old record, so if last month has EMI value 100 Rs. and this month
					//loan records are not found, then we have to set 0.
					else {
						logger.info("EMI not found for this month, so setting EMI to 0 in Paybill table");
						if(payBillVO!=null) {
							Class payBill = payBillVO.getClass();
							Object[] objArgs = new Object[]{new Double(0)};
							Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[]{double.class});
							payBillMthd.invoke(payBillVO, objArgs);
							
							Object[] objArgsInt = new Object[]{new Double(0)};
							Method payBillIntMthd = payBill.getMethod(payBillIntMthdName, new Class[]{double.class});
							payBillIntMthd.invoke(payBillVO, objArgsInt);
							if(postId!=0)
							 postPaybillMap.put(postId, payBillVO);
							logger.info("Map in processLoan  - Loans - for loop is " + postPaybillMap);							
						}
					}
										
				}
					else if(compoType == advLookupId)//for advances
					{		
						
						long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));
						logger.info("Enter in the Advances");
						EmpLoanDAOImpl hrLoanEmpDao = new EmpLoanDAOImpl(HrLoanEmpDtls.class, serv.getSessionFactory());
						List<HrLoanEmpDtls> empLoanList = new ArrayList<HrLoanEmpDtls>();
						empLoanList = hrLoanEmpDao.getEmpLoanDetail(orgEmpId, compoCode);
						if(empLoanList!=null && empLoanList.size()>0) {
							Map advanceMap = inputMap;
							advanceMap.put("orgEmpId", orgEmpId);
							advanceMap.put("compoCode", compoCode);
							advanceMap.put("month", monthGiven);
							advanceMap.put("year", yearGiven);
							advanceMap.put("empLoanList",empLoanList);
							//This EMI will be for whole month, so need to calculate for particular days.
							 Map advResultMap = new GenerateBillLoanAndAdvanceHelper().calculateAdances(advanceMap);
							 lstHrPayLoanEmpDtls = (List)(advResultMap.get("lstHrPayLoanEmpDtls")!=null?advResultMap.get("lstHrPayLoanEmpDtls"):null);
							 Map deactivateAdvRecords = (Map)(advResultMap.get("deactivateLoanRecords")!=null?
									 advResultMap.get("deactivateLoanRecords"):null);
							 if(deactivateAdvRecords!=null && deactivateAdvRecords.size()>0) {
								 int changedMonth = 0;
									int changedYear = 0;
									Calendar calChangedRec = Calendar.getInstance();
									if((calChangedRec.get(Calendar.MONTH)+1) >=12 ) {
										changedMonth = 1; 
										changedYear = calChangedRec.get(Calendar.YEAR)+1; //next year
										logger.info("last month of year, so setting next month as 1 and next year");
										logger.info("Next month and year is " + changedMonth + " " + changedYear);
									}
									else {
										changedMonth = calChangedRec.get(Calendar.MONTH)+1;
										changedYear = calChangedRec.get(Calendar.YEAR);
										logger.info("Not last month, so setting next month and next year");
										logger.info("Next month and year is " + changedMonth + " " + changedYear);
									}
									inputMap.put("changedMonth", changedMonth);
									inputMap.put("changedYear", changedYear);
									inputMap.put("changedPostId", payBillVO.getOrgPostMst().getPostId());
									inputMap.put("cmnDatabaseMst",cmnDatabaseMst);
									inputMap.put("orgUserMst",orgUserMst);
									inputMap.put("orgPostMst",orgPostMst);
									long changedRecPK = new GenerateBillServiceHelper().insertChangedRecord(inputMap);														
									logger.info("Changed Records are inserted for employees whose loans are deactivated");
									logger.info("Changed Records are inserted - PK is " + changedRecPK);
									logger.info("List size for loan insertion is " + lstHrPayLoanEmpDtls.size());
							 }
							 advEMI = advResultMap.get("advEMI")!=null?Long.valueOf(advResultMap.get("advEMI").toString()):0;
							 
							if(edpCode.equals("5059")||edpCode.equals("5053")||edpCode.equals("5052"))
							{
								gorssDedAdv+=advEMI;
							}
							else
							{
								totAdv+=advEMI;
							}
							 
							 GenericDaoHibernateImpl paybillLoanDtlsDao = new GenericDaoHibernateImpl(HrPayPaybillLoanDtls.class);
							paybillLoanDtlsDao.setSessionFactory(serv.getSessionFactory());
							if (lstHrPayLoanEmpDtls != null)
							for (int loanCnt = 0; loanCnt < lstHrPayLoanEmpDtls.size(); loanCnt++)
							{
									HrPayPaybillLoanDtls hrPayPaybillLoanDtls = (HrPayPaybillLoanDtls) lstHrPayLoanEmpDtls.get(loanCnt);
									hrPayPaybillLoanDtls.setPaybillId(payBillVO);
									logger.info("From GeneratePaybillService - going to insert Loan record");
									paybillLoanDtlsDao.getSession().saveOrUpdate(hrPayPaybillLoanDtls);
							}
							
							
							/*if(payBillVO!=null) {
								logger.info("paybill VO not null in advance and Gross Amt befor deductiong Adv amt " + payBillVO.getGrossAmt());
								payBillVO.setGrossAmt(payBillVO.getGrossAmt() -totAdv);
								if(postId!=0)
									 postPaybillMap.put(postId, payBillVO);
								logger.info("paybill VO not null in advance and Gross Amt after deductiong Adv amt " + payBillVO.getGrossAmt());
							}*/
						}
						//if loan records not found then set value to 0, as we are dumping
						//old record, so if last month has EMI value 100 Rs. and this month
						//loan records are not found, then we have to set 0.
						else {
							logger.info("EMI not found for this month, so setting EMI to 0 in Paybill table");
							if(payBillVO!=null) {
								
									String payBillMthdName = "setAdv"+edpCode;
									Class payBill = payBillVO.getClass();
									Object[] objArgs = new Object[]{new Double(0)};
									Method payBillMthd = payBill.getMethod(payBillMthdName, new Class[]{double.class});
									payBillMthd.invoke(payBillVO, objArgs);	
									if(postId!=0)
									  postPaybillMap.put(postId, payBillVO);
								
								
								logger.info("Map in processLoan  - Loans - for loop is " + postPaybillMap);
							}
						}
					}
				
			  }	
				
				//for loop for loan and advance  ends here
				if(payBillVO!=null)
				{
					payBillVO.setGrossAmt(payBillVO.getGrossAmt()-gorssDedAdv);
					payBillVO.setTotalDed(payBillVO.getTotalDed()+totLoan+totAdv);
					payBillVO.setNetTotal(payBillVO.getNetTotal()-gorssDedAdv-totAdv-totLoan);
					if(postId!=0)
						  postPaybillMap.put(postId, payBillVO);
				}
				
			}
			//if ends here 
		}
		///userpost rlt iterator ends here 
		objectArgs.put("postPaybillMap", postPaybillMap);
		return objectArgs;
	}
	
	public long insertDeactivateLoanRec(Map objectArgs) throws Exception {
        Map loginMap = (Map) objectArgs.get("baseLoginMap");
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator"); 		
        long changedRecPK = 0;
        OrgPostMst orgPostMst = new OrgPostMst();
		long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
		OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
		OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

		long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
		CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv.getSessionFactory());
		CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
		CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
		CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

		long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
		CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
		Map deactivateLoanRecords = (HashMap)(objectArgs.get("deactivateLoanRecords")!=null?objectArgs.get("deactivateLoanRecords"):null);
		if(deactivateLoanRecords!=null && deactivateLoanRecords.size()>0) {
		Iterator postIterator = deactivateLoanRecords.keySet().iterator();
		GenerateBillServiceHelper billHelper = new GenerateBillServiceHelper();
		while(postIterator.hasNext()) {
			long changedPostId = 0;
			Map insertChangedRecordMap = objectArgs;							
			String strChangedPostId = postIterator.next().toString();
			if(strChangedPostId!=null && !strChangedPostId.trim().equals(""))
				changedPostId = Long.valueOf(strChangedPostId);
			if(changedPostId!=0) {
				int changedMonth = 0;
				int changedYear = 0;
				Calendar cal = Calendar.getInstance();
				if((cal.get(Calendar.MONTH)+1) >=12 ) {
					changedMonth = 1; 
					changedYear = cal.get(Calendar.YEAR)+1; //next year
					logger.info("last month of year, so setting next month as 1 and next year");
					logger.info("Next month and year is " + changedMonth + " " + changedYear);
				}
				else {
					changedMonth = cal.get(Calendar.MONTH)+1;
					changedYear = cal.get(Calendar.YEAR);
					logger.info("Not last month, so setting next month and next year");
					logger.info("Next month and year is " + changedMonth + " " + changedYear);
				}
				insertChangedRecordMap.put("changedMonth", changedMonth);
				insertChangedRecordMap.put("changedYear", changedYear);
				insertChangedRecordMap.put("changedPostId", changedPostId);
				insertChangedRecordMap.put("cmnDatabaseMst",cmnDatabaseMst);
				insertChangedRecordMap.put("orgUserMst",orgUserMst);
				insertChangedRecordMap.put("orgPostMst",orgPostMst);
				logger.info("Going to inser in change records table");
				changedRecPK = billHelper.insertChangedRecord(insertChangedRecordMap);
				logger.info("Inserted in change records");
			}
		}
		}
		return changedRecPK;
	}
	
	
	
	
	

	
}
