package com.tcs.sgv.eis.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.payroll.dao.PayrollCommonDAO;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceCoreLogic;
import com.tcs.sgv.eis.valueobject.AdvanceValCustomVo;
import com.tcs.sgv.eis.valueobject.AllwValCustomVO;
import com.tcs.sgv.eis.valueobject.DeducValCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.LoanValCustomVo;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

@SuppressWarnings("unchecked")
public class ViewTemporaryPayBillMerged extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	
	public ResultObject getTemporaryPayBill(Map objectArgs)
	{

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{
			//String previewBill=(objectArgs.get("PreviewBill")!=null?objectArgs.get("PreviewBill").toString():"");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			String previewBill = (objectArgs.get("PreviewBill") != null ? objectArgs.get("PreviewBill").toString() : "");
			if (objectArgs.get("PreviewBill") == null)
			{
				previewBill = (voToService.get("PreviewBill") != null ? voToService.get("PreviewBill").toString() : "");
			}

			if (previewBill.equals("YES"))
			{
				ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
				long allowLookupId = Long.parseLong(resourceBundle.getString("allowLookupId"));
				long deducLookupId = Long.parseLong(resourceBundle.getString("deducLookupId"));
				long loanLookupId = Long.parseLong(resourceBundle.getString("loanLookupId"));
				long advLookupId = Long.parseLong(resourceBundle.getString("advLookupId"));
//				long dpLookupId = Long.parseLong(resourceBundle.getString("dpLookupId"));
//				long basicLookupId = Long.parseLong(resourceBundle.getString("basicLookupId"));
				long miscLookupId = Long.parseLong(resourceBundle.getString("miscLookupId"));
//				long gpay0101TypeID = Long.parseLong(resourceBundle.getString("GPAY_0101_ID").toString());
				long gpay0101EdpCmpTypeId = Long.parseLong(resourceBundle.getString("GPAY_0101_EDP_CMP_TYPE_ID").toString());
				//Map voToService = (Map)objectArgs.get("voToServiceMap");
				Map loginMap = (Map) objectArgs.get("baseLoginMap");
				long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
				//long locId=10001;

				long empid = 0;
				int paybillMonth = 0;
				int paybillYear = 0;
//				String updatePaybillFlg = "n";
				logger.info("test paybill month1" + objectArgs.get("paybillMonth"));

				if (voToService != null && voToService.get("empId") != null && !voToService.get("empId").equals(""))
				{

					empid = Long.parseLong(voToService.get("empId").toString());
				}

				else if (objectArgs.get("EmpID") != null && !objectArgs.get("EmpID").equals(""))
				{
					empid = Long.parseLong(objectArgs.get("EmpID").toString());
				}

				if (objectArgs.get("paybillMonth") != null && !objectArgs.get("paybillMonth").equals("") && !objectArgs.get("paybillMonth").toString().equals("0"))
				{

					objectArgs.put("searchData", "y");
//					updatePaybillFlg = "y";
					empid = objectArgs.get("empId") != null ? Long.parseLong(objectArgs.get("empId").toString()) : 0;
					objectArgs.put("updatePaybillEmpId", empid);

					logger.info("test paybill month" + objectArgs.get("paybillYear"));

					paybillMonth = Integer.parseInt(objectArgs.get("paybillMonth").toString());

					if (objectArgs.get("paybillYear") != null && !objectArgs.get("paybillYear").equals(""))
					{
						paybillYear = Integer.parseInt(objectArgs.get("paybillYear").toString());
						objectArgs.put("paybillYear", paybillYear);

						UpdatePaybillDAOImpl paybillUpdataDAO = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
						List<HrPayPaybill> paybillList = paybillUpdataDAO.getEmpDataByMonthAndYear(empid, paybillMonth, paybillYear);
						objectArgs.put("oldPaybillVO", paybillList.get(0));
					}
					objectArgs.put("paybillMonth", paybillMonth);
				}

				OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());

				HrEisOtherDtls otherDtlsVo = new HrEisOtherDtls();//

				Set orgUserPostSet;

				/*if(updatePaybillFlg.equals("y"))
				{
				UpdatePaybillDAOImpl paybillUpdataDAO = new UpdatePaybillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				long otherDtlPK = paybillUpdataDAO.getOtherDetailPKFromEmployeeID(empid);
				//HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
				otherDtlsVo = otherDao.read(otherDtlPK);
				orgUserPostSet = otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
				}
				else*/

				otherDtlsVo = otherDao.getOtherData(empid);
				orgUserPostSet = otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();

				HrEisEmpMst hrEisMst = otherDtlsVo.getHrEisEmpMst();

				Map inputMap = objectArgs;

				HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
//				List<HrPayEdpCompoMpg> allowList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(allowLookupId));

				List<HrPayEdpCompoMpg> hrAdvList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(advLookupId));//for advances
				List<HrPayEdpCompoMpg> hrAllowList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(allowLookupId));//for allowance
				List<HrPayEdpCompoMpg> hrDeducList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(deducLookupId));//for deduction
				List<HrPayEdpCompoMpg> hrLoanList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(loanLookupId));//for loan
				/*List<HrPayEdpCompoMpg> hrBasicList=hrEdp.getListByColumnAndValue("cmnLookupId",new Long(dpLookupId));//for dp
				List<HrPayEdpCompoMpg> hrSalaryList=hrEdp.getListByColumnAndValue("cmnLookupId",new Long(basicLookupId));//for salary
				*/List<HrPayEdpCompoMpg> hrMiscRecoveryList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(miscLookupId));//for misc recovery
				List newHrAdvCode = new ArrayList();
				List<HrPayEdpCompoMpg> newHrAdvList = new ArrayList<HrPayEdpCompoMpg>();
				HrPayEdpCompoMpg hrpayedp = new HrPayEdpCompoMpg();
				for (Iterator it = hrAdvList.iterator(); it.hasNext();)
				{
					hrpayedp = (HrPayEdpCompoMpg) it.next();
					if (!newHrAdvCode.contains(hrpayedp.getRltBillTypeEdp().getEdpCode()))
					{
						newHrAdvList.add(hrpayedp);
						newHrAdvCode.add(hrpayedp.getRltBillTypeEdp().getEdpCode());

					}

				}
				logger.info("the size of newHrAdvList is " + newHrAdvList.size());
				logger.info("hrAllowList.size:::" + hrAllowList.size());
				//Allowence Grade PAY does not need to display in basic details screen.  
				//so removing object with "0101 " and typeEdpId 76 from the  hrAllowList .
				// removing GPAY "0101 " object code starts			
				HrPayEdpCompoMpg hrpayedpGPAY = new HrPayEdpCompoMpg();
				hrpayedpGPAY = hrEdp.read(gpay0101EdpCmpTypeId); //Getting GPAY "0101 " object from HrPayEdpCompoMpg table refering PK
				hrAllowList.remove(hrpayedpGPAY);//removing GPAY "0101 " object from hrAllowList table
				// removing GPAY "0101 " object code ends
				logger.info("hrAllowList.size after removing:::" + hrAllowList.size());
				for (Iterator it = hrAllowList.iterator(); it.hasNext();)
				{
					hrpayedp = (HrPayEdpCompoMpg) it.next();
					logger.info("the edp code is " + hrpayedp.getRltBillTypeEdp().getEdpCode());
					logger.info("the edp name is " + hrpayedp.getRltBillTypeEdp().getEdpShortName());
				}
				for (Iterator it = hrDeducList.iterator(); it.hasNext();)
				{
					hrpayedp = (HrPayEdpCompoMpg) it.next();
					logger.info("the edp code is " + hrpayedp.getRltBillTypeEdp().getEdpCode());
					logger.info("the edp name is " + hrpayedp.getRltBillTypeEdp().getEdpShortName());
				}
				for (Iterator it = hrLoanList.iterator(); it.hasNext();)
				{
					hrpayedp = (HrPayEdpCompoMpg) it.next();
					logger.info("the edp code is " + hrpayedp.getRltBillTypeEdp().getEdpCode());
					logger.info("the edp name is " + hrpayedp.getRltBillTypeEdp().getEdpShortName());
				}

				for (Iterator it = hrMiscRecoveryList.iterator(); it.hasNext();)
				{
					hrpayedp = (HrPayEdpCompoMpg) it.next();
					logger.info("the edp code is " + hrpayedp.getRltBillTypeEdp().getEdpCode());
					logger.info("the edp name is " + hrpayedp.getRltBillTypeEdp().getEdpShortName());
				}

				objectArgs.put("hrAdvList", newHrAdvList);
				objectArgs.put("hrAllowList", hrAllowList);
				objectArgs.put("hrDeducList", hrDeducList);
				objectArgs.put("hrLoanList", hrLoanList);
				/*objectArgs.put("hrBasicList", hrBasicList);
				objectArgs.put("hrSalaryList", hrSalaryList);
				*/

				objectArgs.put("hrMiscRecoveryList", hrMiscRecoveryList);

//				String advCodeStr = "";
//				long advCode = 0;
//				long edpCodeId = 0;

				Calendar currCal = Calendar.getInstance();
				int monthGiven = currCal.get(Calendar.MONTH);
				int yearGiven = currCal.get(Calendar.YEAR);
				if (paybillMonth > 0)
					monthGiven = paybillMonth - 1;
				if (paybillYear > 0)
					yearGiven = paybillYear;

				logger.info(paybillMonth + "from view temp bill, monthGiven " + monthGiven + " and yearGiven is " + yearGiven);

				inputMap.put("monthGiven", monthGiven + 1);
				inputMap.put("yearGiven", yearGiven);

				//			added by Ankit Bhatt
				PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				long spID = 0;
				long ppID = 0;
				String fest_adv_id = " ";
				String food_adv_id = " ";
				String gpf_adv_id = " ";
				String pay_recovery_id = " ";
				long bonusId = 0;

				Map mapMasterDataId = paybillDAO.getMasterDataIds();

				if (mapMasterDataId != null)
				{
					fest_adv_id = mapMasterDataId.get("fest_adv_id") != null ? mapMasterDataId.get("fest_adv_id").toString() : " ";
					food_adv_id = mapMasterDataId.get("food_adv_id") != null ? mapMasterDataId.get("food_adv_id").toString() : " ";
					spID = mapMasterDataId.get("spID") != null ? Long.parseLong(mapMasterDataId.get("spID").toString()) : 0;
					ppID = mapMasterDataId.get("ppID") != null ? Long.parseLong(mapMasterDataId.get("ppID").toString()) : 0;
					gpf_adv_id = mapMasterDataId.get("gpf_adv_id") != null ? mapMasterDataId.get("gpf_adv_id").toString() : " ";
					pay_recovery_id = mapMasterDataId.get("pay_recovery_id") != null ? mapMasterDataId.get("pay_recovery_id").toString() : " ";
					bonusId = mapMasterDataId.get("bonusId") != null ? Long.parseLong(mapMasterDataId.get("bonusId").toString()) : 0;
				}
				logger.info("Master IDs in Temp Paybill View are " + fest_adv_id + " " + food_adv_id + " " + spID + " " + ppID + " " + gpf_adv_id + " " + pay_recovery_id + " " + bonusId);
				//ended by Ankit Bhatt

				GenerateBillService billService = new GenerateBillService();

				List postIdList = new ArrayList();

				for (Iterator it = orgUserPostSet.iterator(); it.hasNext();)
				{
					OrgUserpostRlt orgUserPostRlt = (OrgUserpostRlt) it.next();

					if (orgUserPostRlt.getOrgPostMstByPostId().getOrgPostDetailsRlt() != null)
					{
						Set postDetailSet = orgUserPostRlt.getOrgPostMstByPostId().getOrgPostDetailsRlt();

						for (Iterator it1 = postDetailSet.iterator(); it1.hasNext();)
						{
							OrgPostDetailsRlt postDetailVo = (OrgPostDetailsRlt) it1.next();

							if (postDetailVo != null && postDetailVo.getCmnLocationMst().getLocId() == locId)
							{
								postIdList.add(orgUserPostRlt.getOrgPostMstByPostId().getPostId());
							}
						}

					}

				}
				Map maxDaysMap = billService.checkMaxDayOfPostRecord(orgUserPostSet, monthGiven + 1, yearGiven);

				long maxDaysOfPost = Long.parseLong(maxDaysMap.get("maxDaysOfPost").toString());
				long maxDaysUserPostRltId = Long.parseLong(maxDaysMap.get("maxDaysUserPostRltId").toString());
				long maxDaysUserId = Long.parseLong(maxDaysMap.get("maxDaysUserId").toString());

				//			by manoj for 10-20 days issue of one record
				logger.info("from view paybill the size is maxDaysUserFlag " + orgUserPostSet.size());
				inputMap.put("maxDaysUserFlag", orgUserPostSet.size());

				//end by manoj for 10-20 days issue of one record

				inputMap.put("maxDaysOfPost", maxDaysOfPost);
				inputMap.put("maxDaysUserPostRltId", maxDaysUserPostRltId);
				inputMap.put("maxDaysUserId", maxDaysUserId);

				inputMap.put("fest_adv_id", fest_adv_id);
				inputMap.put("food_adv_id", food_adv_id);
				inputMap.put("spID", spID);
				inputMap.put("ppID", ppID);
				inputMap.put("gpf_adv_id", gpf_adv_id);
				inputMap.put("pay_recovery_id", pay_recovery_id);
				inputMap.put("bonusId", bonusId);

				HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
				//HttpServletRequestProxy proxyReq = new HttpServletRequestProxy(request);

				HrPayPaybill payBillVO = new HrPayPaybill();
				HrPayPaybill resultPaybillVo = new HrPayPaybill();
				if (orgUserPostSet != null)
				{
					logger.info("the size of userpost set is " + orgUserPostSet.size());
					for (Iterator it = orgUserPostSet.iterator(); it.hasNext();)
					{
						OrgUserpostRlt orgUPRlt = (OrgUserpostRlt) it.next();

						logger.info("the post id from list is " + postIdList + " and orgPst id is " + orgUPRlt.getOrgPostMstByPostId().getPostId());

						long activateFlag = orgUPRlt.getActivateFlag();

						Date endDate = orgUPRlt.getEndDate();

						int month = 0;
						int year = 0;

						if (endDate != null)
						{
							month = billService.monthofDate(endDate);
							year = billService.yearofDate(endDate);
						}
						logger.info("the activate flag is " + activateFlag + "  month " + month + " and monthGiven " + monthGiven);
						logger.info("the activate flag is " + activateFlag + "  year " + year + " and yearGiven " + yearGiven);
						if ((activateFlag == 1 || (activateFlag == 0 && month == monthGiven + 1 && year == yearGiven)) && postIdList.contains(orgUPRlt.getOrgPostMstByPostId().getPostId()))
						{

							payBillVO = new HrPayPaybill();

							inputMap.put("orgUPRlt", orgUPRlt);
							inputMap.put("postIdList", postIdList);
							inputMap.put("hrEisOtherDtls", otherDtlsVo);

							inputMap.put("requestObj", request);
							inputMap.put("proxyReq", request);

							//inputMap.put("fromView", "1");

							GenerateBillServiceCoreLogic coreLogic = new GenerateBillServiceCoreLogic();

							Map resultMap = coreLogic.executeCoreLogic(inputMap);

							payBillVO = (HrPayPaybill) resultMap.get("payBillVO");

							resultPaybillVo.setAllow0101(resultPaybillVo.getAllow0101() + payBillVO.getAllow0101());//special pay
							resultPaybillVo.setAllow0102(resultPaybillVo.getAllow0102() + payBillVO.getAllow0102());//personal pay
							resultPaybillVo.setBasic0101(resultPaybillVo.getBasic0101() + payBillVO.getBasic0101());//po
							resultPaybillVo.setBasic0102(resultPaybillVo.getBasic0102() + payBillVO.getBasic0102());//pe
							resultPaybillVo.setAllow0119(resultPaybillVo.getAllow0119() + payBillVO.getAllow0119());//dp
							resultPaybillVo.setAllow0120(resultPaybillVo.getAllow0120() + payBillVO.getAllow0120());//dp gazzetted
							resultPaybillVo.setAllow0103(resultPaybillVo.getAllow0103() + payBillVO.getAllow0103());//da
							resultPaybillVo.setAllow0110(resultPaybillVo.getAllow0110() + payBillVO.getAllow0110());//hra
							resultPaybillVo.setAllow0105(resultPaybillVo.getAllow0105() + payBillVO.getAllow0105());//ltc

							//added by Ankit for Maha
							resultPaybillVo.setAllow1003(resultPaybillVo.getAllow1003() + payBillVO.getAllow1003());//Tech allow
							//ended
							//added by manish
							resultPaybillVo.setAllow1006(resultPaybillVo.getAllow1006() + payBillVO.getAllow1006());//arm allow

							resultPaybillVo.setAllow1007(resultPaybillVo.getAllow1007() + payBillVO.getAllow1007());//arm allow
							resultPaybillVo.setAllow1008(resultPaybillVo.getAllow1008() + payBillVO.getAllow1008());//arm allow
							resultPaybillVo.setAllow1009(resultPaybillVo.getAllow1009() + payBillVO.getAllow1009());//arm allow
							resultPaybillVo.setAllow1010(resultPaybillVo.getAllow1010() + payBillVO.getAllow1010());//arm allow
							resultPaybillVo.setAllow1011(resultPaybillVo.getAllow1011() + payBillVO.getAllow1011());//arm allow

							resultPaybillVo.setAllow1012(resultPaybillVo.getAllow1012() + payBillVO.getAllow1012());
							resultPaybillVo.setAllow1013(resultPaybillVo.getAllow1013() + payBillVO.getAllow1013());
							resultPaybillVo.setAllow1014(resultPaybillVo.getAllow1014() + payBillVO.getAllow1014());
							resultPaybillVo.setAllow1015(resultPaybillVo.getAllow1015() + payBillVO.getAllow1015());

							resultPaybillVo.setAllow1016(resultPaybillVo.getAllow1016() + payBillVO.getAllow1016());
							resultPaybillVo.setAllow1017(resultPaybillVo.getAllow1017() + payBillVO.getAllow1017());
							resultPaybillVo.setAllow1018(resultPaybillVo.getAllow1018() + payBillVO.getAllow1018());
							resultPaybillVo.setAllow1019(resultPaybillVo.getAllow1019() + payBillVO.getAllow1019());

							resultPaybillVo.setAllow1020(resultPaybillVo.getAllow1020() + payBillVO.getAllow1020());
							resultPaybillVo.setAllow1021(resultPaybillVo.getAllow1021() + payBillVO.getAllow1021());
							resultPaybillVo.setAllow1022(resultPaybillVo.getAllow1022() + payBillVO.getAllow1022());

							resultPaybillVo.setAllow1023(resultPaybillVo.getAllow1023() + payBillVO.getAllow1023());
							resultPaybillVo.setAllow1024(resultPaybillVo.getAllow1024() + payBillVO.getAllow1024());

							resultPaybillVo.setAllow1025(resultPaybillVo.getAllow1025() + payBillVO.getAllow1025());
							resultPaybillVo.setAllow1026(resultPaybillVo.getAllow1026() + payBillVO.getAllow1026());

							resultPaybillVo.setAllow1027(resultPaybillVo.getAllow1027() + payBillVO.getAllow1027());
							resultPaybillVo.setAllow1028(resultPaybillVo.getAllow1028() + payBillVO.getAllow1028());
							//manish ended
							resultPaybillVo.setAllow0111(resultPaybillVo.getAllow0111() + payBillVO.getAllow0111());//cla
							resultPaybillVo.setAllow0104(resultPaybillVo.getAllow0104() + payBillVO.getAllow0104());//other allowance
							resultPaybillVo.setAllow0107(resultPaybillVo.getAllow0107() + payBillVO.getAllow0107());//ma
							resultPaybillVo.setAllow0108(resultPaybillVo.getAllow0108() + payBillVO.getAllow0108());//bonus
							resultPaybillVo.setAllow1301(resultPaybillVo.getAllow1301() + payBillVO.getAllow1301());//wa

							resultPaybillVo.setAllow0113(resultPaybillVo.getAllow0113() + payBillVO.getAllow0113());//Tra

							logger.info("Actual value in View Temporary PB is" + payBillVO.getAllow0113());
							logger.info("Ta value sttting in viewTempoaray Pb is " + resultPaybillVo.getAllow0113());

							resultPaybillVo.setAdv5701(resultPaybillVo.getAdv5701() + payBillVO.getAdv5701());//fes advance
							resultPaybillVo.setAdv5801(resultPaybillVo.getAdv5801() + payBillVO.getAdv5801());//food advance

							resultPaybillVo.setDeduc0101(resultPaybillVo.getDeduc0101() + payBillVO.getDeduc0101());//payrecovery
							resultPaybillVo.setAdv9670(resultPaybillVo.getAdv9670() + payBillVO.getAdv9670());//gpf advance
							resultPaybillVo.setGrossAmt(resultPaybillVo.getGrossAmt() + payBillVO.getGrossAmt());//gross amount

							resultPaybillVo.setIt(resultPaybillVo.getIt() + payBillVO.getIt());//It
							resultPaybillVo.setDeduc9510(resultPaybillVo.getDeduc9510() + payBillVO.getDeduc9510());//It

							logger.info("it in view temporary paybill merged is " + resultPaybillVo.getIt());
							resultPaybillVo.setSurcharge(resultPaybillVo.getSurcharge() + payBillVO.getSurcharge());//Surcharge
							resultPaybillVo.setDeduc9550(resultPaybillVo.getDeduc9550() + payBillVO.getDeduc9550());//Hrr
							resultPaybillVo.setDeduc9560(resultPaybillVo.getDeduc9560() + payBillVO.getDeduc9560());//rent of building

							resultPaybillVo.setDeduc9570(resultPaybillVo.getDeduc9570() + payBillVO.getDeduc9570());//pt

							resultPaybillVo.setDeduc9583(resultPaybillVo.getDeduc9583() + payBillVO.getDeduc9583());//AISIF
							resultPaybillVo.setDeduc9780(resultPaybillVo.getDeduc9780() + payBillVO.getDeduc9780());//jeep rent
							resultPaybillVo.setDeduc9910(resultPaybillVo.getDeduc9910() + payBillVO.getDeduc9910());//misc recovery

							resultPaybillVo.setDeduc9534(resultPaybillVo.getDeduc9534() + payBillVO.getDeduc9534());//CPF

							resultPaybillVo.setDeduc9531(resultPaybillVo.getDeduc9531() + payBillVO.getDeduc9531());//gpf for class iv
							resultPaybillVo.setTotalDed(resultPaybillVo.getTotalDed() + payBillVO.getTotalDed());//total deduction
							resultPaybillVo.setNetTotal(resultPaybillVo.getNetTotal() + payBillVO.getNetTotal());//net amount

							resultPaybillVo.setLoan9591(resultPaybillVo.getLoan9591() + payBillVO.getLoan9591());//Hba loan
							resultPaybillVo.setLoan9720(resultPaybillVo.getLoan9720() + payBillVO.getLoan9720());//Fan loan

							resultPaybillVo.setAdvIV9670(resultPaybillVo.getAdvIV9670() + payBillVO.getAdvIV9670());//gpf advance

							resultPaybillVo.setDeduc9999(resultPaybillVo.getDeduc9999() + payBillVO.getDeduc9999());
							resultPaybillVo.setDeduc9998(resultPaybillVo.getDeduc9998() + payBillVO.getDeduc9998());

							//manish
							resultPaybillVo.setDeduc1037(resultPaybillVo.getDeduc1037() + payBillVO.getDeduc1037());
							resultPaybillVo.setDeduc1038(resultPaybillVo.getDeduc1038() + payBillVO.getDeduc1038());
							resultPaybillVo.setDeduc1039(resultPaybillVo.getDeduc1039() + payBillVO.getDeduc1039());
							resultPaybillVo.setDeduc1040(resultPaybillVo.getDeduc1040() + payBillVO.getDeduc1040());
							resultPaybillVo.setDeduc1041(resultPaybillVo.getDeduc1041() + payBillVO.getDeduc1041());
							resultPaybillVo.setDeduc1042(resultPaybillVo.getDeduc1042() + payBillVO.getDeduc1042());
							//resultPaybillVo.setDeduc1043(resultPaybillVo.getDeduc1043()+payBillVO.getDeduc1043());
							//manish
							resultPaybillVo.setDeduc1000(resultPaybillVo.getDeduc1000() + payBillVO.getDeduc1000());//gps(ips)
							resultPaybillVo.setDeduc1001(resultPaybillVo.getDeduc1001() + payBillVO.getDeduc1001());//gIs(iAs)
							resultPaybillVo.setDeduc1002(resultPaybillVo.getDeduc1002() + payBillVO.getDeduc1002());//gIs(iFs)

							resultPaybillVo.setDeduc1004(resultPaybillVo.getDeduc1004() + payBillVO.getDeduc1004());//gIs(iAs)
							resultPaybillVo.setDeduc1005(resultPaybillVo.getDeduc1005() + payBillVO.getDeduc1005());//gIs(iFs)
							//japen
							resultPaybillVo.setAllow1029(resultPaybillVo.getAllow1029() + payBillVO.getAllow1029());
							resultPaybillVo.setAllow1030(resultPaybillVo.getAllow1030() + payBillVO.getAllow1030());
							resultPaybillVo.setAllow1031(resultPaybillVo.getAllow1031() + payBillVO.getAllow1031());
							resultPaybillVo.setAllow1032(resultPaybillVo.getAllow1032() + payBillVO.getAllow1032());
							logger.info("ats 30 ::" + payBillVO.getAllow1033() + "  ats 50::: " + payBillVO.getAllow1034() + " force 100 " + payBillVO.getAllow1035() + " force 25 " + payBillVO.getAllow1036());

							resultPaybillVo.setAllow1033(resultPaybillVo.getAllow1033() + payBillVO.getAllow1033());
							resultPaybillVo.setAllow1034(resultPaybillVo.getAllow1034() + payBillVO.getAllow1034());
							resultPaybillVo.setAllow1035(resultPaybillVo.getAllow1035() + payBillVO.getAllow1035());
							resultPaybillVo.setAllow1036(resultPaybillVo.getAllow1036() + payBillVO.getAllow1036());
							//japen
							//logger.info("gps(ips) val in viewtemporary paybill is "+ resultPaybillVo.getDeduc10000()+payBillVO.getDeduc10000());
							//logger.info("gps(ips) val in viewtemporary paybill is "+ resultPaybillVo.getDeduc10000()+payBillVO.getDeduc10000());
							//logger.info("gps(ips) val in viewtemporary paybill is from core logic "+ payBillVO.getDeduc10000());

							//end
						}

					}
				}
				resultPaybillVo.setHrEisEmpMst(hrEisMst);
				String empAllRec = "";
				String otherId = "";
				if (voToService != null && voToService.get("empAllRec") != null && !voToService.get("empAllRec").equals(""))
				{

					empAllRec = voToService.get("empAllRec").toString();
				}

				if (voToService != null && voToService.get("otherId") != null && !voToService.get("otherId").equals(""))
				{

					otherId = voToService.get("otherId").toString();
				}
				logger.info("Bonus value is " + resultPaybillVo.getAllow0108() + "\noriginally value was " + payBillVO.getAllow0108());
				objectArgs.put("otherId", otherId);
				objectArgs.put("resultPaybillVo", resultPaybillVo);
				resultObject.setResultCode(1);
				if (empAllRec.equalsIgnoreCase("true") == true)
				{
					resultObject.setViewName("tempPaybillViewEmpAllRec");
					empAllRec = "true";
				}
				else
				{
					resultObject.setViewName("OtherEditListMerged");

					empAllRec = "false";
				}
				objectArgs.put("empAllRec", empAllRec);
				resultObject.setResultValue(objectArgs);

			}
			else
			{
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("OtherViewListMerged");

			}

		}
		catch (Exception e)
		{
			logger.info("Error in getTemporaryPayBill of ViewTemporaryPayBill.");
			logger.error("Error in getTemporaryPayBill of ViewTemporaryPayBill.", e);
			objectArgs.put("msg", "There is some problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: " + e.getMessage());
		}

		return resultObject;
	}

	@SuppressWarnings("deprecation")
	public ResultObject getPreviousBillData(Map objectArgs) throws Exception
	{

		logger.info("inside getPreviousBillData");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Log logger = LogFactory.getLog(getClass());
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		logger.info("vo to service map" + voToService);
		logger.info("vo to objectargs map" + objectArgs);
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		logger.info("inside getPreviousBillData1111111111111111");
		long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());

//		long langId = StringUtility.convertToLong(loginMap.get("langId").toString());

		logger.info("inside getPreviousBillData111111111111111122222222222222222");

		DeptCompMPGDAOImpl deptCompMPGDAOImpl = new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());
		logger.info("inside getPreviousBillData3333333333333333333333");

		List<Long> allwActiveCompoList = deptCompMPGDAOImpl.getActiveAllComponents(locId);
		List<Long> deducActiveCompoList = deptCompMPGDAOImpl.getActiveDeducComponents(locId);
		List<Long> loanActiveCompoList = deptCompMPGDAOImpl.getActiveLoanComponents(locId);
		List<Long> advanceActiveCompoList = deptCompMPGDAOImpl.getActiveAdvanceComponents(locId);
		logger.info("inside getPreviousBillData4444444444444444444");

		String previewBill = (objectArgs.get("PreviewBill") != null ? objectArgs.get("PreviewBill").toString() : "");
		logger.info("inside previoyus bill data PreviewBill is " + previewBill);
		try
		{
			if (objectArgs.get("PreviewBill") == null)
			{
				previewBill = (voToService.get("PreviewBill") != null ? voToService.get("PreviewBill").toString() : "");
			}
			HrEisOtherDtls otherDtlsVo = new HrEisOtherDtls();
//			Set orgUserPostSet;
			OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());

			logger.info("inside the getPreviouss data method");
			logger.info("Inside get Previous data");
			logger.info("Preview Bill is " + previewBill);
			if (previewBill.equals("YES"))
			{
				long empId = 0;
//				int month = 0;
//				int year = 0;
				List lst = null;
//				HrPayPaybill prevResultPaybillVO;
				HrPayPaybill resultPaybillVo = new HrPayPaybill();
				List allCustomList = new ArrayList();
				List deducCustomList = new ArrayList();
				List loanCustomList = new ArrayList();
				List advanceCustomList = new ArrayList();
//				List newActiveComponentList = new ArrayList();

				empId = (Long.valueOf(objectArgs.get("empId").toString()) != null) ? Long.valueOf(objectArgs.get("empId").toString()) : 0;
				logger.info("emp id in getPrevious data is " + empId);

				otherDtlsVo = otherDao.getOtherDataByHrEis(empId);

//				if (otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts() != null)
//					orgUserPostSet = otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();

//				HrEisEmpMst hrEisMst = otherDtlsVo.getHrEisEmpMst();

				Date date = new Date();
				int prevMonth = date.getMonth() + 1;
				int prevYear = date.getYear() + 1900;
				if (prevMonth == 1)
				{
					prevMonth = 12;
					prevYear = prevYear - 1;
				}
				else
					prevMonth = prevMonth - 1;

				PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

				Map edpCompo = new HashMap();

				edpCompo = billDAOImpl.getEdpCompoMapForHrPayPaybill(locId);
				List allColumList = (List) edpCompo.get("allColumList");
				List deducColumList = (List) edpCompo.get("deducColumList");
				List advColumnList = (List) edpCompo.get("advColumnList");
				List loanColumnList = (List) edpCompo.get("loanColumnList");

				long val = 0;
				String edpVal = null;

				Class class1 = HrPayPaybill.class;
				Method method = null;
				PayrollCommonDAO payrollCommonDAO = new PayrollCommonDAO(serv.getSessionFactory());
				HrEisOtherDtls hrEisOtherDtls = otherDtlsVo;

				long payCommissionId = (hrEisOtherDtls.getHrEisSgdMpg() != null) ? hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getHrPayCommissionMst().getId().longValue() : 0;
				Map lastAllwMap = new HashMap();

				Map allowEdpMap = payrollCommonDAO.getAllwEdpMap(payCommissionId, locId);
				Map deducEdpMap = payrollCommonDAO.getDeducEdpMap(payCommissionId, locId);
				Map loanEdpMap = payrollCommonDAO.getLoanEdpMap(payCommissionId, locId);
				Map advancecEdpMap = payrollCommonDAO.getAdvanceEdpMap(payCommissionId, locId);

				logger.info("map is " + allowEdpMap);

				double totalAllw = 0;

				logger.info("allwActiveCompoList is " + allwActiveCompoList);

				for (int i = 0; i < allColumList.size(); i++)
				{
					String col = allColumList.get(i).toString();
					logger.info("column value is " + col);
					lst = billDAOImpl.getPreviousBillDataForSingleColumn(empId, prevMonth, prevYear, locId, col);
					if (lst != null && lst.size() > 0)
					{
						Object abc = (Object) lst.get(0);
						if (abc != null)
							val = Long.valueOf(abc.toString());
						else
							val = 0;
					}
					long id = 0;
					edpVal = edpCompo.get(col).toString();

					if (edpVal != null && allowEdpMap.get(edpVal) != null)
					{
						logger.info("allw id  " + allowEdpMap.get(edpVal) + "val " + val);
						lastAllwMap.put(allowEdpMap.get(edpVal), val);
						id = Long.valueOf(allowEdpMap.get(edpVal).toString());

						if (allwActiveCompoList != null && allwActiveCompoList.contains(new Long(id)))
						{
							logger.info("inside allow vo");
							AllwValCustomVO allwValCustomVO = new AllwValCustomVO();
							allwValCustomVO.setAllwID(id);
							allwValCustomVO.setAllowanceVal(val);
							logger.info("edp is " + edpVal + "compo id " + id + "value is " + val);
							allCustomList.add(allwValCustomVO);
							totalAllw += val;
						}
					}
					String methdName = "setAllow";
					if (col.equals("PO"))
					{
						methdName = "setBasic";
					}
					methdName += edpVal;

					method = class1.getMethod(methdName, double.class);

					method.invoke(resultPaybillVo, val);

				}
				double totalDeduc = 0;
				for (int i = 0; i < deducColumList.size(); i++)
				{
					String col = deducColumList.get(i).toString();
					//logger.info("column value is "+col);
					lst = billDAOImpl.getPreviousBillDataForSingleColumn(empId, prevMonth, prevYear, locId, col);
					if (lst != null && lst.size() > 0)
					{
						Object abc = (Object) lst.get(0);
						if (abc != null)
							val = Long.valueOf(abc.toString());
						else
							val = 0;
					}

					edpVal = edpCompo.get(col).toString();
					long dedId = 0;
					if (edpVal != null && deducEdpMap.get(edpVal) != null)
					{
						logger.info("deduc id  " + deducEdpMap.get(edpVal) + "val " + val);
						dedId = Long.valueOf(deducEdpMap.get(edpVal).toString());

						if (deducActiveCompoList != null && deducActiveCompoList.contains(dedId))
						{
							logger.info("inside deduc vo ");
							DeducValCustomVO deducValCustomVO = new DeducValCustomVO();
							deducValCustomVO.setDeducId(dedId);
							deducValCustomVO.setDeducVal(val);
							logger.info("edp is " + edpVal + "compo id " + dedId + "value is " + val);
							deducCustomList.add(deducValCustomVO);
							totalDeduc += val;
						}
					}

					String methdName = "setDeduc";
					methdName += edpVal;

					//logger.info("final name of method is for deduction "+methdName);
					method = class1.getMethod(methdName, double.class);
					//logger.info("val to be set in paybill is for deduction "+val);
					method.invoke(resultPaybillVo, val);

				}
				//for loan

//				double totalLoan = 0;
				for (int i = 0; i < loanColumnList.size(); i++)
				{
					String col = loanColumnList.get(i).toString();

					lst = billDAOImpl.getPreviousBillDataForSingleColumn(empId, prevMonth, prevYear, locId, col);
					if (lst != null && lst.size() > 0)
					{
						Object abc = (Object) lst.get(0);
						if (abc != null)
							val = Long.valueOf(abc.toString());
						else
							val = 0;
					}
					/*StringTokenizer st1=new StringTokenizer(col,"+");
					col=st1.nextToken();
					logger.info("special sop for loan column is "+col);*/
					edpVal = edpCompo.get(col).toString();
					long loanId = 0;
					if (edpVal != null && loanEdpMap.get(edpVal) != null)
					{
						logger.info("loan id  " + loanEdpMap.get(edpVal) + "val " + val);
						loanId = Long.valueOf(loanEdpMap.get(edpVal).toString());

						if (loanActiveCompoList != null && loanActiveCompoList.contains(loanId))
						{
							logger.info("inside loan vo ");
							LoanValCustomVo loanValCustomVo = new LoanValCustomVo();
							loanValCustomVo.setLoanId(loanId);
							loanValCustomVo.setLoanValue(val);
							logger.info("edp is " + edpVal + "compo id " + loanId + "value is " + val);
							loanCustomList.add(loanValCustomVo);
							totalDeduc += val;
						}
					}

					String methdName = "setLoan";
					methdName += edpVal;

					//logger.info("final name of method is for deduction "+methdName);
					method = class1.getMethod(methdName, double.class);
					//logger.info("val to be set in paybill is for deduction "+val);
					method.invoke(resultPaybillVo, val);

				}
				//ended for loan

				//for advance

				double totalAdv = 0;
				for (int i = 0; i < advColumnList.size(); i++)
				{
					String col = advColumnList.get(i).toString();

					lst = billDAOImpl.getPreviousBillDataForSingleColumn(empId, prevMonth, prevYear, locId, col);
					if (lst != null && lst.size() > 0)
					{
						Object abc = (Object) lst.get(0);
						if (abc != null)
							val = Long.valueOf(abc.toString());
						else
							val = 0;
					}

					edpVal = edpCompo.get(col).toString();
					long advId = 0;
					if (edpVal != null && advancecEdpMap.get(edpVal) != null)
					{
						logger.info("advance id  " + advancecEdpMap.get(edpVal) + "val " + val);
						advId = Long.valueOf(advancecEdpMap.get(edpVal).toString());

						if (advanceActiveCompoList != null && advanceActiveCompoList.contains(advId))
						{
							logger.info("inside advanceValCustomVo vo ");
							AdvanceValCustomVo advanceValCustomVo = new AdvanceValCustomVo();
							advanceValCustomVo.setAdvanceId(advId);
							advanceValCustomVo.setAdvanceVal(val);
							logger.info("edp is " + edpVal + "compo id " + advId + "value is " + val);
							advanceCustomList.add(advanceValCustomVo);
							totalAdv += val;
						}
					}

					String methdName = "setAdv";
					methdName += edpVal;

					//logger.info("final name of method is for deduction "+methdName);
					method = class1.getMethod(methdName, double.class);
					//logger.info("val to be set in paybill is for deduction "+val);
					method.invoke(resultPaybillVo, val);

				}
				//ended
				resultPaybillVo.setNetTotal(totalAllw - totalDeduc);
				resultPaybillVo.setTotalDed(totalDeduc);
				resultPaybillVo.setGrossAmt(totalAllw);
				//objectArgs.put("payBillVO",payBillVO);
				String empAllRec = "";
				String otherId = "";
				if (voToService != null && voToService.get("empAllRec") != null && !voToService.get("empAllRec").equals(""))
				{

					empAllRec = voToService.get("empAllRec").toString();
				}

				if (voToService != null && voToService.get("otherId") != null && !voToService.get("otherId").equals(""))
				{

					otherId = voToService.get("otherId").toString();
				}
				logger.info("Bonus value is " + resultPaybillVo.getAllow0108() + "\noriginally value was " + resultPaybillVo.getAllow0108());

				objectArgs.put("otherId", otherId);

				logger.info("value is for the manish is is  " + resultPaybillVo.getAllow0111());
				objectArgs.put("resultPaybillVo", resultPaybillVo);

				resultObject.setResultCode(1);
				if (empAllRec.equalsIgnoreCase("true") == true)
				{
					resultObject.setViewName("tempPaybillViewEmpAllRec");
					empAllRec = "true";
				}
				else
				{
					resultObject.setViewName("OtherEditListMerged");

					empAllRec = "false";
				}
				objectArgs.put("empAllRec", empAllRec);
				objectArgs.put("lastAllwMap", lastAllwMap);
				logger.info("size of allowance custom list is " + allCustomList.size());
				objectArgs.put("allCustomList", allCustomList);
				logger.info("size of deductionList is " + deducCustomList.size());
				objectArgs.put("deducCustomList", deducCustomList);
				logger.info("size of loanCustomList is " + loanCustomList.size());
				objectArgs.put("loanCustomList", loanCustomList);
				logger.info("size of advanceCustomList is " + advanceCustomList.size());
				objectArgs.put("advanceCustomList", advanceCustomList);

				resultObject.setResultValue(objectArgs);
			}
			else
			{
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("OtherViewListMerged");

			}

			PayBillDAOImpl pbDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			String officeName = pbDao.getOffice(locId);
			objectArgs.put("officeName", officeName);

		}
		catch (Exception e)
		{
			logger.info("Error in getTemporaryPayBill of ViewTemporaryPayBill.");
			logger.error("Error in getTemporaryPayBill of ViewTemporaryPayBill.", e);
			objectArgs.put("msg", "There is some problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: " + e.getMessage());
		}
		logger.info("view name is in previous bill data" + resultObject.getViewName());
		logger.info("Manish here to complete the previous bill data Method");
		return resultObject;
	}

}
