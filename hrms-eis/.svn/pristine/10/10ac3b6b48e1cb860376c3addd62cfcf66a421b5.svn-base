package com.tcs.sgv.eis.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibm.icu.util.Calendar;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAO;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayChangedRecords;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

@SuppressWarnings("unchecked")
public class GenerateBillServiceHelper
{
	Log logger = LogFactory.getLog(getClass());

	long postIdTemp = 0;

	OrgPostDetailsRlt postDtlRlt = new OrgPostDetailsRlt();

	HrEisGdMpg gdRecord = new HrEisGdMpg();

	OrgPostMst orgPostMst = new OrgPostMst();

	OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();

	OrgEmpMst orgEmpMst = new OrgEmpMst();

	HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();

	HrEisOtherDtls otherDtls = new HrEisOtherDtls();

	List desigByGradeList = new ArrayList();

	List userIdListUserPostRlt = new ArrayList();

	List userIdListUserMst = new ArrayList();

	List vacantPostIdList = new ArrayList();

	List postIdList = new ArrayList();

	List hrEmpList = null;

	List<HrEisOtherDtls> hrEisOtherDtls = new ArrayList<HrEisOtherDtls>();

	long postDesigId = 0;

	long chkForGradeId = 0;

	int gradeCnt = 0;

	Date empRetireDate = new Date();

	boolean isBillGenerated = true;

	Iterator hrEisEmpIt = null;

	/**
	 * Method will return all the eligible post list by given
	 * month, year and bill number.
	 * @param objectArgs
	 * @return Map
	 */

	public Map getEligibleEmployeesByBillNo(Map objectArgs)
	{

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		long locId = objectArgs.get("locId") != null ? Long.parseLong(objectArgs.get("locId").toString()) : 0;
		long billNo = objectArgs.get("billNo") != null ? Long.parseLong(objectArgs.get("billNo").toString()) : 0;
		int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
		int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
		//long subHeadId = objectArgs.get("subHeadId") != null ? Long.parseLong(objectArgs.get("subHeadId").toString()) : 0;
		//CmnLanguageMst cmnLanguageMst = (CmnLanguageMst) (objectArgs.get("cmnLanguageMst") != null ? objectArgs.get("cmnLanguageMst") : null);
		/*long langId = 0;
		if (cmnLanguageMst != null)
			langId = cmnLanguageMst.getLangId();*/

		PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

		long orgEmpId;
		long eisEmpId;
		long basicAmt;
		long incomeTax;
		long desigId;
		long gradeId;
		long scaleId;
		long scaleStartAmt;
		long scaleEndAmt;
		String dcpsOrGPF;
		long postId;
		long userId;
		long payCommissionId;
		long empType;
		long gradeCode;
		int isAvailedHRA;
		Date empDOB;
		Date empSrvcExp;
		Date empDOJ;
		String isPhyHandicapped;
		long gradePay;
		long empCity;
		long qtrRentAmt = 0;
		long postPSRNo;
		long otherDtlsId;
		String empLname;
		String accNo;
		String ifscCode;
		String empClassgroup;

		//Map resultMap = objectArgs;
		Calendar calGiven = Calendar.getInstance();
		calGiven.set(Calendar.YEAR, yearGiven);
		calGiven.set(Calendar.MONTH, (monthGiven - 1));
		calGiven.set(Calendar.DAY_OF_MONTH, 1);
		Date givenDate = calGiven.getTime();

		List<EmpPaybillVO> generateEmpList = new ArrayList<EmpPaybillVO>();
		List empObjectList = payDao.getEligibleEmployeesBillWise(locId, billNo, givenDate);
		if (empObjectList != null && empObjectList.size() > 0)
			for (int i = 0; i < empObjectList.size(); i++)
			{
				Object[] row = (Object[]) empObjectList.get(i);
				if (row != null)
				{
					orgEmpId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
					eisEmpId = row[1] != null ? Long.valueOf(row[1].toString()) : 0;
					basicAmt = row[2] != null ? Long.valueOf(row[2].toString()) : 0;
					incomeTax = row[3] != null ? Long.valueOf(row[3].toString()) : 0;
					gradeId = row[4] != null ? Long.valueOf(row[4].toString()) : 0;
					desigId = row[5] != null ? Long.valueOf(row[5].toString()) : 0;
					scaleId = row[6] != null ? Long.valueOf(row[6].toString()) : 0;
					scaleStartAmt = row[7] != null ? Long.valueOf(row[7].toString()) : 0;
					scaleEndAmt = row[8] != null ? Long.valueOf(row[8].toString()) : 0;
					dcpsOrGPF = row[9] != null ? row[9].toString() : "";
					postId = row[10] != null ? Long.valueOf(row[10].toString()) : 0;
					userId = row[11] != null ? Long.valueOf(row[11].toString()) : 0;
					payCommissionId = row[12] != null ? Long.valueOf(row[12].toString()) : 0;
					empType = row[13] != null ? Long.valueOf(row[13].toString()) : 0;
					gradeCode = row[14] != null ? Long.valueOf(row[14].toString()) : 0;
					isAvailedHRA = row[15] != null ? Integer.valueOf(row[15].toString()) : 0;
					empDOB = row[16] != null ? (Date) row[16] : null;
					empSrvcExp = row[17] != null ? (Date) row[17] : null;
					empDOJ = row[18] != null ? (Date) row[18] : null;
					isPhyHandicapped = row[19] != null ? row[19].toString() : "";
					gradePay = row[20] != null ? Long.valueOf(row[20].toString()) : 0;
					empCity = row[21] != null ? Long.valueOf(row[21].toString()) : 0;
					//qtrRentAmt =  row[22]!=null ? Long.valueOf(row[22].toString()):0;
					postPSRNo = row[22] != null ? Long.valueOf(row[22].toString()) : 0;
					otherDtlsId = row[23] != null ? Long.valueOf(row[23].toString()) : 0;
					empLname = row[24] != null ? String.valueOf(row[24]) : "";
					//aDDED BY ROSHAN FOR sbi/cmp
					accNo = row[25] != null ? String.valueOf(row[25]) : "";
					ifscCode = row[26] != null ? String.valueOf(row[26]) : "";
					//ENDED BY Roshan for SBI/CMP
					empClassgroup=row[27]!=null ? row[27].toString():"";
					if (orgEmpId != 0 && eisEmpId != 0 && desigId != 0 && gradeId != 0 && scaleId != 0 && postId != 0 && userId != 0)
					{
						EmpPaybillVO empPaybill = new EmpPaybillVO();
						empPaybill.setOrgEmpId(orgEmpId);
						empPaybill.setEisEmpId(eisEmpId);
						empPaybill.setBasicAmt(basicAmt);
						empPaybill.setDesigId(desigId);
						empPaybill.setGradeId(gradeId);
						empPaybill.setScaleId(scaleId);
						empPaybill.setScaleStartAmt(scaleStartAmt);
						empPaybill.setScaleEndAmt(scaleEndAmt);
						empPaybill.setDcpsOrGPF(dcpsOrGPF);
						empPaybill.setPostId(postId);
						empPaybill.setIncomeTax(incomeTax);
						empPaybill.setUserId(userId);
						empPaybill.setPayCommissionId(payCommissionId);
						empPaybill.setEmpType(empType);
						empPaybill.setGradeCode(gradeCode);
						empPaybill.setIsAvailedHRA(isAvailedHRA);
						empPaybill.setEmpDOB(empDOB);
						empPaybill.setEmpDOJ(empDOJ);
						empPaybill.setEmpSrvcExp(empSrvcExp);
						empPaybill.setIsPhyHandicapped(isPhyHandicapped);
						empPaybill.setGradePay(gradePay);
						empPaybill.setEmpCity(empCity);
						empPaybill.setQtrRentAmt(qtrRentAmt);
						empPaybill.setPostPSRNo(postPSRNo);
						empPaybill.setOtherDtlsId(otherDtlsId);
						empPaybill.setEmpLname(empLname);
						//Added By roshan for SBI/CMP
						empPaybill.setAccNo(accNo);
						empPaybill.setIfscCode(ifscCode);
						//Ended BY SBI/CMp
						generateEmpList.add(empPaybill);
						//Added BY Naveen for GAP
						empPaybill.setEmpClassGroup(empClassgroup);
						empPaybill = null;
					}
					else
						logger.error("OrgEmpId is zero, or hreisempid is 0 or desig or grade or scale or post id is 0");
				}
				row = null;
			}

		List vacantPostIdListObj = payDao.getVacantPostsBillWise(locId, billNo, givenDate);
		for (int k = 0; k < vacantPostIdListObj.size(); k++)
		{
			Object[] row = (Object[]) vacantPostIdListObj.get(k);
			long vacantPostId = row[0] != null ? Long.valueOf(row[0].toString()) : 0;
			if (vacantPostId != 0)
				vacantPostIdList.add(vacantPostId);

			row = null;
		}

		/*List postList = payDao.getPostByBillNo(locId, billNo);

		for (int k = 0; k < postList.size(); k++) {
			HrPayPsrPostMpg hrPayPsrPostMpg = (HrPayPsrPostMpg) postList.get(k);
			postIdTemp = hrPayPsrPostMpg.getPostId();				
			logger.info("The Post Id is:-" + postIdTemp + " and PSR No is:-" + hrPayPsrPostMpg.getPsrId());
			postDtlRlt = postDtlDAO.getPostDetailsRltByPostIdAndLangId(postIdTemp, langId);			
			postDesigId = postDtlRlt.getOrgDesignationMst().getDsgnId();
			if(postDtlRlt!=null)
				orgPostMst = postDtlRlt.getOrgPostMst();

				// for 10-20 days issue by manoj
				if (!postIdList.contains(orgPostMst.getPostId())) {
					postIdList.add(orgPostMst.getPostId());
				}
				// end by manoj for 10-20 days issues

				// fetching users from User_Post_rlt table belongs to Particular
				// Post.
				userIdListUserPostRlt = userPostDAOImpl.getListByColumnAndValue("orgPostMstByPostId", orgPostMst);
				logger.info("userIdListUserPostRlt.size() " + userIdListUserPostRlt.size());

				if (userIdListUserPostRlt != null && userIdListUserPostRlt.size() != 0) {
					for (Iterator it = userIdListUserPostRlt.iterator(); it.hasNext();) {

						orgUserpostRlt = (OrgUserpostRlt) it.next();

						// fetching UserId from Org_User_mst for particular
						// employee
						// by manoj for 10-20 day issue of same post double
						// employee
						Date endDate = orgUserpostRlt.getEndDate();
						SimpleDateFormat sdf = new SimpleDateFormat("MM");
						int month = 0;
						int year = 0;

						if (endDate != null) {
							month = monthofDate(endDate);
							year = yearofDate(endDate);
						}

						// end by manoj for 10-20 day issue of same post double
						// employee

						logger.info("the activate flag is " + orgUserpostRlt.getActivateFlag());
						logger
								.info("the end date is " + orgUserpostRlt.getEndDate() + " given month is " + monthGiven + " and the month is "
										+ month);
						if (orgUserpostRlt.getActivateFlag() == 1
								|| (orgUserpostRlt.getActivateFlag() == 0 && month == monthGiven && year == yearGiven)) {
							userIdListUserMst = orgEmpMstDAO.getListByColumnAndValue("orgUserMst", orgUserpostRlt.getOrgUserMst());

							if (userIdListUserMst != null && userIdListUserMst.size() != 0) {
								for (Iterator userIDIt = userIdListUserMst.iterator(); userIDIt.hasNext();) {
									orgEmpMst = (OrgEmpMst) userIDIt.next();
									// fetching EmpId from Org_emp_mst for
									// particualr UserId
									// hrEmpList =
									// hrEisEmpMstDAO.getListByColumnAndValue("orgEmpMst",
									// orgEmpMst);

									// by manoj for retirement issue
									if (orgEmpMst.getEmpSrvcExp() != null && orgEmpMst.getCmnLanguageMst().getLangId() == langId) {

										empRetireDate = orgEmpMst.getEmpSrvcExp();
										int retireMonth = monthofDate(empRetireDate);
										int retireYear = yearofDate(empRetireDate);
										logger.info(empRetireDate + "*********************retirement date test+" + retireMonth + "-" + retireYear);
										if (retireYear > currYear || (retireYear == currYear && retireMonth >= currMonth)) {
											logger.info(empRetireDate + "retirement date is after the first date ");
											// end by manoj for retirement issue

												hrEmpList = hrEisEmpMstDAO.getEmpDataByGrade(orgEmpMst.getEmpId(),  cmnLanguageMst);

											if (hrEmpList != null && hrEmpList.size() > 0) {
												for (hrEisEmpIt = hrEmpList.iterator(); hrEisEmpIt.hasNext();) {
													hrEisEmpMst = (HrEisEmpMst) hrEisEmpIt.next();
													long empId = hrEisEmpMst.getEmpId();
													long orgEmpId = hrEisEmpMst.getOrgEmpMst().getEmpId();													
													logger.info(empId + "Emp List size from hr_ris_emp_list " + hrEmpList.size() + " orgEmpId "
															+ orgEmpId);
													otherDtls = otherDtlsDAOImpl.getOtherData(orgEmpId);
													// for change of 10-20 days
													// by manoj
													if (otherDtls != null && otherDtls.getOtherId() != 0) {
														if (!hrEisOtherDtls.contains(otherDtls)) {
															// Added By Mrugesh
															// for supplimentary
															// bill issue
															isBillGenerated = payDao.checkPaybillData(monthGiven, yearGiven, 
																	String.valueOf(hrPayPsrPostMpg.getPostId()), empId);
															if (!isBillGenerated) {
																if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt
																		.getStartDate()) == yearGiven)
																		|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
																	hrEisOtherDtls.add(otherDtls);
																logger.info("Object added in Other Detials");
															}
															// Ended Bhy Mrugesh
														}
													}
													// end by manoj for 10-20
													// days
													// by manoj for vacant post
													// issue
													else
													{
														isBillGenerated = payDao.checkPaybillData(monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
																.getPostId()), 0);
														if (!isBillGenerated) 
														{
															if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt
																	.getStartDate()) == yearGiven)
																	|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
																vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
															logger.info("other dtls not found Object added in vacant post list"
																	+ hrPayPsrPostMpg.getPostId());
														}
													}
													// end by manoj for vacant
													// post issue
													// }
												}
												logger.info("Emp List size from hr_ris_emp_list " + hrEmpList.size());
											}
											// by manoj for vacant post issue
											else 
											{
												if (chkForGradeId != 0) {
													isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
															.getPostId()), 0);
													if (!isBillGenerated) 
													{
														if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt
																.getStartDate()) == yearGiven)
																|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
															vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
														logger.info("hrempmst not found Object added in vacant post list" + hrPayPsrPostMpg.getPostId());
													}
												}
											}
											// end by manoj for vacant post
											// issue
										}// end of if block for checking
										// retirement date with current date
										else 
										{
											isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
													.getPostId()), 0);
											if (!isBillGenerated) 
											{
												if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt.getStartDate()) == yearGiven)
														|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
													vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
												logger.info("emp got retired. Object added in vacant post list" + hrPayPsrPostMpg.getPostId());
											}
										}
									}// end of if for chking retirement date not
									// nullby manoj for retirement issue
									else if (orgEmpMst.getCmnLanguageMst().getLangId() == langId) {

										logger.info(empRetireDate + "retirement date is in else if ");																														
											hrEmpList = hrEisEmpMstDAO.getEmpDataByGrade(orgEmpMst.getEmpId(), cmnLanguageMst);										
										if (hrEmpList != null && hrEmpList.size() > 0) {
											for (hrEisEmpIt = hrEmpList.iterator(); hrEisEmpIt.hasNext();) {
												hrEisEmpMst = (HrEisEmpMst) hrEisEmpIt.next();
												long empId = hrEisEmpMst.getEmpId();
												long orgEmpId = hrEisEmpMst.getOrgEmpMst().getEmpId();												
												logger.info(empId + "Emp List size from hr_ris_emp_list " + hrEmpList.size() + " long orgEmpId "
														+ orgEmpId);
												otherDtls = otherDtlsDAOImpl.getOtherData(orgEmpId);
												// for change of 10-20 days by
												// manoj
												if (otherDtls != null && otherDtls.getOtherId() != 0) {
													if (!hrEisOtherDtls.contains(otherDtls)) {
														if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt
																.getStartDate()) == yearGiven)
																|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
															hrEisOtherDtls.add(otherDtls);
														logger.info("Object added in Other Detials");
													}
												}
												// end by manoj for 10-20 days
												// by manoj for vacant post
												// issue
												else 
												{
													isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
															.getPostId()), 0);
													if (!isBillGenerated) 
													{
														if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt
																.getStartDate()) == yearGiven)
																|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
															vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
														logger.info("other dtls not found Object added in vacant post list"
																		+ hrPayPsrPostMpg.getPostId());

													}
												}
												// end by manoj for vacant post
												// issue
												// }
											}
											logger.info("Emp List size from hr_ris_emp_list " + hrEmpList.size());
										}
										// by manoj for vacant post issue
										else {
											if (chkForGradeId != 0) 
											{
												isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
														.getPostId()), 0);
												if (!isBillGenerated) 
												{
													if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt
															.getStartDate()) == yearGiven)
															|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
														vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
													logger.info("hrempmst not found Object added in vacant post list" + hrPayPsrPostMpg.getPostId());
												}
											}
										}
										// end by manoj for vacant post issue

									}// end by manoj for retirement issue

								}

							}
							// by manoj for vacant post issue
							else 
							{
								isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
										.getPostId()), 0);
								if (!isBillGenerated) 
								{
									if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt.getStartDate()) == yearGiven)
											|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
										vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
									logger.info("org emp mst not found Object added in vacant post list" + hrPayPsrPostMpg.getPostId());
								}
							}
							// end by manoj for vacant post issue
							logger.info("userIdListUserMst.size() is " + userIdListUserMst.size());
						}// end of if block checking the activate flag in
						// userpost rlt
						// by manoj for vacant post issue
						else {
							String colUser[] = { "orgPostMstByPostId", "activateFlag" };
							Object valUser[] = { orgUserpostRlt.getOrgPostMstByPostId(), new Long(1) };
							logger.info("going to chk active user for deactive post");
							List activeUserPostList = userPostDAOImpl.getListByColumnAndValue(colUser, valUser);

							if (activeUserPostList == null || activeUserPostList.size() < 1) {
								isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven,  String.valueOf(hrPayPsrPostMpg
										.getPostId()), 0);
								if (!isBillGenerated) {
									if ((monthofDate(orgUserpostRlt.getStartDate()) <= monthGiven && yearofDate(orgUserpostRlt.getStartDate()) == yearGiven)
											|| (yearofDate(orgUserpostRlt.getStartDate()) < yearGiven))
										vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
								}
								logger.info("active user found for double entry chk of post id Object added in vacant post list"
										+ hrPayPsrPostMpg.getPostId());
							} else {
								logger.info("active post is there for the deactive post with pk " + orgUserpostRlt.getEmpPostId());
							}

						}
						// end by manoj for vacant post issue
					}
				}
				// by manoj for vacant post issue
				else {
					isBillGenerated = payDao.checkPaybillData( monthGiven, yearGiven, 
							String.valueOf(hrPayPsrPostMpg.getPostId()), 0);
					if (!isBillGenerated) {
						if ((monthofDate(orgPostMst.getStartDate()) <= monthGiven && yearofDate(orgPostMst.getStartDate()) == yearGiven)
								|| (yearofDate(orgPostMst.getStartDate()) < yearGiven))
							vacantPostIdList.add(hrPayPsrPostMpg.getPostId());
						logger.info("user post not found Object added in vacant post list" + hrPayPsrPostMpg.getPostId());
					}

				}
				// end by manoj for vacant post issue
				// commented By Mrugesh }
				logger.info("Post ID added into List is " + hrPayPsrPostMpg.getPostId());
				logger.info("size of hrEisOtherDtls is " + hrEisOtherDtls.size());
				logger.info("userIdListUserPostRlt.size() is " + userIdListUserPostRlt.size());

			//}
		}*/
		/*
		 * } }
		 */
		logger.info("Before returning from getEligibleEmployeeByBillNo in GenerateBillServiceHelper " + hrEisOtherDtls.size());
		objectArgs.put("generateEmpList", generateEmpList);
		objectArgs.put("vacantPostIdList", vacantPostIdList);

		//Null Assignment - Start
		calGiven = null;
		givenDate = null;
		generateEmpList = null;
		empObjectList = null;
		vacantPostIdListObj = null;
		vacantPostIdList = null;
		//Null Assignment - End

		return objectArgs;
	}

	/**
	 * Method will return all the eligible post list by given
	 * month and year.
	 * @param objectArgs
	 * @return Map
	 */
	public Map getEligibleEmployeesWithoutBillNo(Map objectArgs)
	{

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
		int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
		CmnLanguageMst cmnLanguageMst = (CmnLanguageMst) (objectArgs.get("cmnLanguageMst") != null ? objectArgs.get("cmnLanguageMst") : null);
		long langId = 0;
		if (cmnLanguageMst != null)
			langId = cmnLanguageMst.getLangId();

		int currYear = yearGiven;
		int currMonth = monthGiven;

		PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		OrgPostDetailsRltDaoImpl postDtlDAO = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
		//getGradDesgMapDAO gradeDesigDao = new getGradDesgMapDAO(HrEisGdMpg.class, serv.getSessionFactory());
		UserPostDaoImpl userPostDAOImpl = new UserPostDaoImpl(OrgUserpostRlt.class, serv.getSessionFactory());
		EmpInfoDAOImpl hrEisEmpMstDAO = new EmpInfoDAOImpl(HrEisEmpMst.class, serv.getSessionFactory());
		OtherDetailDAOImpl otherDtlsDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
		EmpDAOImpl orgEmpMstDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
		List<HrPayOrderHeadPostMpg> orderNoList = (List<HrPayOrderHeadPostMpg>) (objectArgs.get("orderNoList") != null ? objectArgs.get("orderNoList") : null);
		if (orderNoList.size() > 0 && orderNoList != null)
		{
			logger.info("inside if loop " + System.currentTimeMillis());
			// Object[] postIdRow=null;

			//int cnt = 0;
			for (int k = 0; k < orderNoList.size(); k++)
			{
				String posIdStr = "";
				postIdTemp = orderNoList.get(k).getPostId();
				posIdStr = postIdTemp + "";
				// Added By Paurav for Taking Post Details for comparing with
				// Designation
				postDtlRlt = postDtlDAO.getPostDetailsRltByPostIdAndLangId(postIdTemp, langId);

				postDesigId = postDtlRlt.getOrgDesignationMst().getDsgnId();

				// OrgPostMst orgPostMst = new OrgPostMst();
				// Changed By Paurav for taking complete Post Master Object
				// and not just using Id
				orgPostMst = postDtlRlt.getOrgPostMst();
				// orgPostMst.setPostId(Long.parseLong(posIdStr));

				// for 10-20 days issue by manoj
				if (!postIdList.contains(orgPostMst.getPostId()))
				{
					postIdList.add(orgPostMst.getPostId());
				}
				// end by manoj for 10-20 days issues

				// fetching users from User_Post_rlt table belongs to
				// Particular Post.
				userIdListUserPostRlt = userPostDAOImpl.getListByColumnAndValue("orgPostMstByPostId", orgPostMst);
				logger.info("userIdListUserPostRlt.size() " + userIdListUserPostRlt.size());

				// Commented By Mrugesh
				// isBillGenerated = payDao.checkPaybillData(subHeadId,
				// monthGiven, yearGiven,gradeIds,posIdStr);
				logger.info("isBillGenerated " + isBillGenerated + " after CheckData function");
				// fetching record for that EmpId from Other_dtls.
				// Commented By Mrugesh
				/*
				 * if(!isBillGenerated) {
				 */
				if (userIdListUserPostRlt != null && userIdListUserPostRlt.size() != 0)
				{
					for (Iterator it = userIdListUserPostRlt.iterator(); it.hasNext();)
					{
						orgUserpostRlt = (OrgUserpostRlt) it.next();

						// fetching UserId from Org_User_mst for particular
						// employee
						// by manoj for 10-20 day issue of same post double
						// employee
						Date endDate = orgUserpostRlt.getEndDate();
						//SimpleDateFormat sdf = new SimpleDateFormat("MM");
						int month = 0;
						int year = 0;

						if (endDate != null)
						{
							month = monthofDate(endDate);
							year = yearofDate(endDate);
						}

						// end by manoj for 10-20 day issue of same post
						// double employee

						logger.info("the activate flag is " + orgUserpostRlt.getActivateFlag());
						logger.info("the end date is " + orgUserpostRlt.getEndDate() + " given month is " + monthGiven + " and the month is " + month);
						if (orgUserpostRlt.getActivateFlag() == 1 || (orgUserpostRlt.getActivateFlag() == 0 && month == monthGiven && year == yearGiven))
						{
							userIdListUserMst = orgEmpMstDAO.getListByColumnAndValue("orgUserMst", orgUserpostRlt.getOrgUserMst());

							if (userIdListUserMst != null && userIdListUserMst.size() != 0)
							{
								for (Iterator userIDIt = userIdListUserMst.iterator(); userIDIt.hasNext();)
								{
									orgEmpMst = (OrgEmpMst) userIDIt.next();
									// fetching EmpId from Org_emp_mst for
									// particualr UserId
									// hrEmpList =
									// hrEisEmpMstDAO.getListByColumnAndValue("orgEmpMst",
									// orgEmpMst);
									chkForGradeId = 0;
									// by manoj for retirement issue
									if (orgEmpMst.getEmpSrvcExp() != null && orgEmpMst.getCmnLanguageMst().getLangId() == langId)
									{

										empRetireDate = orgEmpMst.getEmpSrvcExp();
										int retireMonth = monthofDate(empRetireDate);
										int retireYear = yearofDate(empRetireDate);
										logger.info(empRetireDate + "*********************retirement date test+" + retireMonth + "-" + retireYear);
										if (retireYear > currYear || (retireYear == currYear && retireMonth >= currMonth))
										{
											logger.info(empRetireDate + "retirement date is after the first date ");
											// end by manoj for retirement
											// issue

											logger.info("chkForGradeId" + chkForGradeId);

											if (chkForGradeId > 0)
											{
												hrEmpList = hrEisEmpMstDAO.getEmpDataByGrade(orgEmpMst.getEmpId(), cmnLanguageMst);
											}
											if (hrEmpList != null && hrEmpList.size() > 0)
											{
												for (hrEisEmpIt = hrEmpList.iterator(); hrEisEmpIt.hasNext();)
												{
													hrEisEmpMst = (HrEisEmpMst) hrEisEmpIt.next();
													long empId = hrEisEmpMst.getEmpId();
													long orgEmpId = hrEisEmpMst.getOrgEmpMst().getEmpId();

													logger.info(empId + "Emp List size from hr_ris_emp_list " + hrEmpList.size() + " orgEmpId " + orgEmpId);
													otherDtls = otherDtlsDAOImpl.getOtherData(orgEmpId);
													// for change of 10-20
													// days by manoj
													if (otherDtls != null && otherDtls.getOtherId() != 0)
													{
														if (!hrEisOtherDtls.contains(otherDtls))
														{// Added
															// By
															// Mrugesh
															// for
															// supplimentary
															// bill
															// issue
															isBillGenerated = payDao.checkPaybillData(monthGiven, yearGiven, posIdStr, empId);
															if (!isBillGenerated)
															{
																hrEisOtherDtls.add(otherDtls);
																logger.info("Object added in Other Detials");
															}
															// Ended By
															// Mrugesh
														}
													}
													// end by manoj for
													// 10-20 days
													// by manoj for vacant
													// post issue
													else
													{
														vacantPostIdList.add(posIdStr);
														logger.info("other dtls not found Object added in vacant post list" + posIdStr);

													}
													// end by manoj for
													// vacant post issue
													// }
												}
												logger.info("Emp List size from hr_ris_emp_list " + hrEmpList.size());
											}
											// by manoj for vacant post
											// issue
											else
											{
												if (chkForGradeId != 0)
												{
													vacantPostIdList.add(posIdStr);
													logger.info("hrempmst not found Object added in vacant post list" + posIdStr);
												}
											}
											// end by manoj for vacant post
											// issue
										}// end of if block for checking
										// retirement date with current
										// date
										else
										{
											vacantPostIdList.add(posIdStr);
											logger.info("emp got retired. Object added in vacant post list" + posIdStr);
										}
									}// end of if for chking retirement date
									// not nullby manoj for retirement
									// issue
									else if (orgEmpMst.getCmnLanguageMst().getLangId() == langId)
									{

										logger.info(empRetireDate + "retirement date is in else if ");
										logger.info("chkForGradeId" + chkForGradeId);

										if (chkForGradeId > 0)
										{
											hrEmpList = hrEisEmpMstDAO.getEmpDataByGrade(orgEmpMst.getEmpId(), cmnLanguageMst);
										}

										if (hrEmpList != null && hrEmpList.size() > 0)
										{
											for (hrEisEmpIt = hrEmpList.iterator(); hrEisEmpIt.hasNext();)
											{
												hrEisEmpMst = (HrEisEmpMst) hrEisEmpIt.next();
												long empId = hrEisEmpMst.getEmpId();
												long orgEmpId = hrEisEmpMst.getOrgEmpMst().getEmpId();
												logger.info(empId + "Emp List size from hr_ris_emp_list " + hrEmpList.size() + " orgEmpId " + orgEmpId);

												otherDtls = otherDtlsDAOImpl.getOtherData(orgEmpId);
												// for change of 10-20 days
												// by manoj
												if (otherDtls != null && otherDtls.getOtherId() != 0)
												{
													if (!hrEisOtherDtls.contains(otherDtls))
													{
														hrEisOtherDtls.add(otherDtls);
														logger.info("Object added in Other Detials");
													}
												}
												// end by manoj for 10-20
												// days
												// by manoj for vacant post
												// issue
												else
												{
													vacantPostIdList.add(posIdStr);
													logger.info("other dtls not found Object added in vacant post list" + posIdStr);

												}
												// end by manoj for vacant
												// post issue
												// }
											}
											logger.info("Emp List size from hr_ris_emp_list " + hrEmpList.size());
										}
										// by manoj for vacant post issue
										else
										{
											if (chkForGradeId != 0)
											{
												vacantPostIdList.add(posIdStr);
												logger.info("hrempmst not found Object added in vacant post list" + posIdStr);
											}
										}
										// end by manoj for vacant post
										// issue

									}// end by manoj for retirement issue

								}

							}
							// by manoj for vacant post issue
							else
							{
								vacantPostIdList.add(posIdStr);
								logger.info("org emp mst not found Object added in vacant post list" + posIdStr);
							}
							// end by manoj for vacant post issue
							logger.info("userIdListUserMst.size() is " + userIdListUserMst.size());
						}// end of if block checking the activate flag in
						// userpost rlt
						// by manoj for vacant post issue
						else
						{

							String colUser[] = { "orgPostMstByPostId", "activateFlag" };
							Object valUser[] = { orgUserpostRlt.getOrgPostMstByPostId(), new Long(1) };
							logger.info("going to chk active user for deactive post");
							List activeUserPostList = userPostDAOImpl.getListByColumnAndValue(colUser, valUser);

							if (activeUserPostList == null || activeUserPostList.size() < 1)
							{
								vacantPostIdList.add(posIdStr);
								logger.info("hello active user not found for double entry chk of post id Object added in vacant post list" + posIdStr);
							}
							else
							{
								logger.info("active post is there for the deactive post with pk " + orgUserpostRlt.getEmpPostId());
							}

						}
						// end by manoj for vacant post issue
					}
				}
				// by manoj for vacant post issue
				else
				{
					isBillGenerated = payDao.checkPaybillData(monthGiven, yearGiven, String.valueOf(posIdStr), 0);
					if (!isBillGenerated)
					{
						vacantPostIdList.add(posIdStr);
						logger.info("user post not found Object added in vacant post list" + posIdStr);
					}
				}
				// end by manoj for vacant post issue
				// Commented By Mrugesh }
				logger.info("Post ID added into List is " + posIdStr);
				logger.info("size of hrEisOtherDtls is " + hrEisOtherDtls.size());
				logger.info("userIdListUserPostRlt.size() is " + userIdListUserPostRlt.size());

			}
			/*
			 * } }
			 */
		}
		Map resultMap = objectArgs;
		resultMap.put("hrEisOtherDtls", hrEisOtherDtls);
		resultMap.put("vacantPostIdList", vacantPostIdList);
		return resultMap;

	}

	/**
	 * Method will fetch data from hr_pay_edp_mst table
	 * which contains mapping of EDP code and Pay bill column.
	 * Based on this, Outer will be generated.
	 * @param objectArgs
	 * @return Map - Key = EDP Code and Value = EDP Code value
	 */
	public Map getOuterValues(Map objectArgs)
	{
		Map outerValueMap = objectArgs;
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		long billNo = objectArgs.get("paybillNo") != null ? Long.parseLong(objectArgs.get("paybillNo").toString()) : -1;
		int month = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString()) : -1;
		int year = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
		PayBillDAO paybillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		List lstEdpPaybillMpg = paybillDAO.getPaybillEdpMpgList();
		if (lstEdpPaybillMpg != null && lstEdpPaybillMpg.size() > 0)
		{
			for (int index = 0; index < lstEdpPaybillMpg.size(); index++)
			{
				Object[] rowObject = (Object[]) lstEdpPaybillMpg.get(index);
				String expRecRcp = rowObject[0] != null ? rowObject[0].toString() : "";
				String paybillcolumn = rowObject[1] != null ? rowObject[1].toString() : "";
				String edpCode = rowObject[2] != null ? rowObject[2].toString() : "";
				String condtionValue = rowObject[3] != null ? rowObject[3].toString() : "";
				List headTotalValue = paybillDAO.getTotalHeadValue(billNo, month, year, paybillcolumn, condtionValue);
				if (headTotalValue != null && headTotalValue.size() > 0)
				{
					Object rowObjectValue = headTotalValue.get(0);
					//double temp =  rowObjectValue!=null?Double.parseDouble(rowObjectValue.toString()):0;
					//temp = Math.round(temp);
					String longVal = rowObjectValue != null && rowObjectValue != "" ? rowObjectValue.toString() : "0";
					//int inx = longVal.indexOf('.'); 
					////System.out.println("index is "+inx);
					////System.out.println(longVal.substring(0,inx));
					long totalHeadValue = Long.parseLong(longVal);
					String mapKeyName = edpCode + "~" + expRecRcp.toUpperCase();
					if (outerValueMap.containsKey(mapKeyName))
					{
						logger.info("Key already present in putermap " + mapKeyName);
						logger.info("previous value " + outerValueMap.get(mapKeyName).toString());
						long keyValue = Long.valueOf(outerValueMap.get(mapKeyName).toString()) + totalHeadValue;
						logger.info("New value got is " + totalHeadValue + " New value to be put in " + keyValue);
						outerValueMap.put(mapKeyName, keyValue);
					}
					else
						outerValueMap.put(mapKeyName, totalHeadValue);

				}
			}
		}
		logger.info("Outer Map value is " + outerValueMap);
		return outerValueMap;
	}

	public Map getMaxDaysofPost(Map postMap)
	{

		OrgUserpostRlt orgUPRlt = (OrgUserpostRlt) (postMap.get("orgUPRlt") != null ? postMap.get("orgUPRlt") : null);
		if (orgUPRlt != null)
		{

		}
		return postMap;
	}

	public int monthofDate(Date date)
	{
		int monthofDate = 0;
		SimpleDateFormat sdfObj = new SimpleDateFormat("MM");
		String days = sdfObj.format(date);
		monthofDate = Integer.parseInt(days);
		return monthofDate;
	}

	public int yearofDate(Date date)
	{
		int yearofDate = 0;
		SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
		String days = sdfObj.format(date);
		yearofDate = Integer.parseInt(days);
		return yearofDate;
	}

	/**
	 * Method which deep copy the given object and return new object with the 
	 * same values as given object.
	 * @param <E> 
	 * @param object - Any <b> standard POJO </b> Object
	 * @return object of given type with deep copy values
	 * @throws Exception
	 */
	public <E> Object copy(E object) throws Exception
	{
		E copyObject = (E) object.getClass().newInstance();
		Method[] methods = object.getClass().getDeclaredMethods();
		for (Method method : methods)
		{
			if (method.getName().startsWith("set"))
			{
				Class[] paraType = method.getParameterTypes();
				String setterMethodName = method.getName();
				String getterPrefix = setterMethodName.substring(0, 3).replace("s", "g");
				String getterField = setterMethodName.substring(3);
				String gettrMethod = getterPrefix + getterField;

				try
				{
					Method getterMethod = object.getClass().getMethod(gettrMethod, new Class[] {});
					Object value = getterMethod.invoke(object, null);
					Method toBeSetMethod = copyObject.getClass().getMethod(setterMethodName, paraType);
					toBeSetMethod.invoke(copyObject, new Object[] { value });
				}
				catch (Exception e)
				{
					logger.error("Error is: " + e.getMessage());
				}
			}
		}
		return copyObject;
	}

	/**
	 * Method will insert record in hr_pay_changed_records table, if record 
	 * doesn't exist for given month and year. No duplicate records will be there
	 * for same post Id, month and year.
	 * @param objectArgs - Map contains - serviceLocator
	 * 					   changedPostId - Mandatory -  Post ID
	 * 					   OrgUserMst - Mandatory - Logged in User Object
	 * 					   OrgPostMst - Mandatory - Logged in Post Object
	 * 					   changedEmpId - Not Mandatory
	 * @return - long - Primary key if record inserted, else 0.
	 * @throws Exception
	 */
	public long insertChangedRecord(Map objectArgs) throws Exception
	{
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Calendar cal = Calendar.getInstance();
		int changedMonth = cal.get(Calendar.MONTH) + 1;
		int changedYear = cal.get(Calendar.YEAR);
		//long locId=LoobjectArgs.get("locId");

		long locId = objectArgs.get("locId") != null ? Long.valueOf(objectArgs.get("locId").toString()) : 0;
		long postId = objectArgs.get("changedPostId") != null ? Long.valueOf(objectArgs.get("changedPostId").toString()) : 0;
		long empId = objectArgs.get("changedEmpId") != null ? Long.valueOf(objectArgs.get("changedEmpId").toString()) : 0;
		CmnDatabaseMst cmnDatabaseMst = (CmnDatabaseMst) (objectArgs.get("cmnDatabaseMst") != null ? objectArgs.get("cmnDatabaseMst") : null);
		OrgUserMst orgUserMst = (OrgUserMst) (objectArgs.get("OrgUserMst") != null ? objectArgs.get("OrgUserMst") : null);
		OrgPostMst orgPostMst = (OrgPostMst) (objectArgs.get("OrgPostMst") != null ? objectArgs.get("OrgPostMst") : null);

		long changedRecordPK = 0;
		IdGenerator idGen = new IdGenerator();

		logger.info("postId ;;;;;;;;;;;;;;" + postId);
		logger.info("empId;;;;;;;;;;;;;;;;;;;;;;;;;;" + empId);
		logger.info("changedYear" + changedYear);
		logger.info("changedMonth" + changedMonth);
		GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(OrgPostMst.class);
		genDAO.setSessionFactory(serv.getSessionFactory());

		OrgPostMst orgPostMstChanged = (OrgPostMst) genDAO.read(postId);
		//System.out.println("orgPostMst::::"+orgPostMstChanged);
		if (orgPostMstChanged == null)
			throw new Exception("OrgPostMst not found for Post Id " + postId);
		else
		{

			genDAO = new GenericDaoHibernateImpl(HrPayChangedRecords.class);
			genDAO.setSessionFactory(serv.getSessionFactory());//added by me
			//String[] columns = new String[]{"changedMonth,changedYear,orgPostMst"};
			//Object[] vals = new Object[]{changedMonth,changedYear,orgPostMst};

			logger.info("going to execute query");
			//List existingChkList = genDAO.getListByColumnAndValue(columns, vals);

			PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			List existingChkList = billDAOImpl.getChangedRecords(changedMonth, changedYear, postId, locId);
			logger.info(" query executed successfully");
			if (existingChkList != null && existingChkList.size() > 0)
			{
				logger.info("existingChkList;;;;;;;;;;; is " + existingChkList.size());

				logger.info("Records already exist for month -year - postId " + changedMonth + " " + changedYear + " " + postId);
			}
			else
			{
				HrPayChangedRecords hrPayChangedRecord = new HrPayChangedRecords();
				hrPayChangedRecord.setChangedMonth(changedMonth);
				hrPayChangedRecord.setChangedYear(changedYear);
				hrPayChangedRecord.setEmpId(empId);
				hrPayChangedRecord.setLocId((Long) objectArgs.get("locId"));
				hrPayChangedRecord.setOrgPostMst(orgPostMstChanged);
				hrPayChangedRecord.setCmnDatabaseMst(cmnDatabaseMst);
				hrPayChangedRecord.setCreatedDate(cal.getTime());
				hrPayChangedRecord.setUpdatedDate(cal.getTime());
				hrPayChangedRecord.setOrgUserMstByCreatedBy(orgUserMst);
				hrPayChangedRecord.setOrgUserMstByUpdatedBy(orgUserMst);
				hrPayChangedRecord.setOrgPostMstByCreatedByPost(orgPostMst);
				hrPayChangedRecord.setOrgPostMstByUpdatedByPost(orgPostMst);

				synchronized (idGen)
				{
					changedRecordPK = idGen.PKGenerator("HR_PAY_CHANGED_RECORDS", objectArgs);
					//changedRecordPK= idGen.PKGeneratorWebService("HR_EIS_EMP_COMPONENT_GRP_MST",serv,orgUserMst.getUserId(),1,locId);
				}
				hrPayChangedRecord.setRecordId(changedRecordPK);
				genDAO.create(hrPayChangedRecord);
			}
		}
		return changedRecordPK;
	}
}
