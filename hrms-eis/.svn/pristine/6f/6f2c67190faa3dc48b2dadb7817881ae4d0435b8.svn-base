package com.tcs.sgv.eis.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.util.HttpServletRequestProxy;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.HrEdpComponentMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceCoreLogic;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

@SuppressWarnings("unchecked")
public class ViewTemporaryPayBill extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());

	public ResultObject getTemporaryPayBill(Map objectArgs)
	{

		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try
		{

			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long allowLookupId = Long.parseLong(resourceBundle.getString("allowLookupId"));
			long deducLookupId = Long.parseLong(resourceBundle.getString("deducLookupId"));
			long loanLookupId = Long.parseLong(resourceBundle.getString("loanLookupId"));
			long advLookupId = Long.parseLong(resourceBundle.getString("advLookupId"));
//			long dpLookupId = Long.parseLong(resourceBundle.getString("dpLookupId"));
//			long basicLookupId = Long.parseLong(resourceBundle.getString("basicLookupId"));
			long miscLookupId = Long.parseLong(resourceBundle.getString("miscLookupId"));
//			long gpay0101TypeID = Long.parseLong(resourceBundle.getString("GPAY_0101_ID").toString());
			long gpay0101EdpCmpTypeId = Long.parseLong(resourceBundle.getString("GPAY_0101_EDP_CMP_TYPE_ID").toString());
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
			long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());

			long empid = 0;
			int paybillMonth = 0;
			int paybillYear = 0;
//			String updatePaybillFlg = "n";
			logger.info("test paybill month1" + objectArgs.get("paybillMonth"));

			if (voToService != null && voToService.get("empId") != null && !voToService.get("empId").equals(""))
			{

				empid = Long.parseLong(voToService.get("empId").toString());
			}
			if (objectArgs.get("paybillMonth") != null && !objectArgs.get("paybillMonth").equals(""))
			{
				objectArgs.put("searchData", "y");
//				updatePaybillFlg = "y";
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

			//System.out.println("jdfoghfijhipfthjotyktyotykl"+empid);

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
			{
				otherDtlsVo = otherDao.getOtherData(empid);
				orgUserPostSet = otherDtlsVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getOrgUserpostRlts();
			}

			HrEisEmpMst hrEisMst = otherDtlsVo.getHrEisEmpMst();

			Map inputMap = objectArgs;

			HrEdpComponentMpgDAOImpl hrEdp = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
//			List<HrPayEdpCompoMpg> allowList = hrEdp.getListByColumnAndValue("cmnLookupId", new Long(allowLookupId));

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

//			String advCodeStr = "";
//			long advCode = 0;
//			long edpCodeId = 0;

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
			HttpServletRequestProxy proxyReq = new HttpServletRequestProxy(request);

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
						inputMap.put("proxyReq", proxyReq);

						//inputMap.put("fromView", "1");

						GenerateBillServiceCoreLogic coreLogic = new GenerateBillServiceCoreLogic();

						Map resultMap = coreLogic.executeCoreLogic(inputMap);

						payBillVO = (HrPayPaybill) resultMap.get("payBillVO");

						/*resultPaybillVo.setAllow0101(resultPaybillVo.getAllow0101()+payBillVO.getAllow0101());//special pay
						resultPaybillVo.setAllow0102(resultPaybillVo.getAllow0102()+payBillVO.getAllow0102());//personal pay
						resultPaybillVo.setBasic0101(resultPaybillVo.getBasic0101()+payBillVO.getBasic0101());//po
						resultPaybillVo.setBasic0102(resultPaybillVo.getBasic0102()+payBillVO.getBasic0102());//pe
						resultPaybillVo.setLs(resultPaybillVo.getLs()+payBillVO.getLs());//ls
						resultPaybillVo.setLe(resultPaybillVo.getLe()+payBillVO.getLe());//le
						resultPaybillVo.setAllow0119(resultPaybillVo.getAllow0119()+payBillVO.getAllow0119());//dp
						resultPaybillVo.setAllow0120(resultPaybillVo.getAllow0120()+payBillVO.getAllow0120());//dp gazzetted
						resultPaybillVo.setAllow0103(resultPaybillVo.getAllow0103()+payBillVO.getAllow0103());//da
						resultPaybillVo.setAllow0110(resultPaybillVo.getAllow0110()+payBillVO.getAllow0110());//hra
						resultPaybillVo.setAllow0105(resultPaybillVo.getAllow0105()+payBillVO.getAllow0105());//ltc
						resultPaybillVo.setAllow0111(resultPaybillVo.getAllow0111()+payBillVO.getAllow0111());//cla
						resultPaybillVo.setAllow0104(resultPaybillVo.getAllow0104()+payBillVO.getAllow0104());//other allowance
						resultPaybillVo.setAllow0107(resultPaybillVo.getAllow0107()+payBillVO.getAllow0107());//ma
						resultPaybillVo.setAllow0108(resultPaybillVo.getAllow0108()+payBillVO.getAllow0108());//bonus
						resultPaybillVo.setAllow1301(resultPaybillVo.getAllow1301()+payBillVO.getAllow1301());//wa
						resultPaybillVo.setAllow5006(resultPaybillVo.getAllow5006()+payBillVO.getAllow5006());//other Charges
						resultPaybillVo.setAllow0113(resultPaybillVo.getAllow0113()+payBillVO.getAllow0113());//Tra
						resultPaybillVo.setAdv5701(resultPaybillVo.getAdv5701()+payBillVO.getAdv5701());//fes advance
						resultPaybillVo.setAdv5801(resultPaybillVo.getAdv5801()+payBillVO.getAdv5801());//food advance
						
						//added by Ankit Bhatt
						resultPaybillVo.setAdv7057(resultPaybillVo.getAdv7057()+payBillVO.getAdv7057());//fes advance
						resultPaybillVo.setAdv7058(resultPaybillVo.getAdv7058()+payBillVO.getAdv7058());//food advance
						//ended
						resultPaybillVo.setDeduc0101(resultPaybillVo.getDeduc0101()+payBillVO.getDeduc0101());//payrecovery
						resultPaybillVo.setAdv9670(resultPaybillVo.getAdv9670()+payBillVO.getAdv9670());//gpf advance
						resultPaybillVo.setGrossAmt(resultPaybillVo.getGrossAmt()+payBillVO.getGrossAmt());//gross amount
						resultPaybillVo.setSlo(resultPaybillVo.getSlo()+payBillVO.getSlo());//slo
						resultPaybillVo.setIt(resultPaybillVo.getIt()+payBillVO.getIt());//It
						resultPaybillVo.setSurcharge(resultPaybillVo.getSurcharge()+payBillVO.getSurcharge());//Surcharge
						resultPaybillVo.setDeduc9550(resultPaybillVo.getDeduc9550()+payBillVO.getDeduc9550());//Hrr
						resultPaybillVo.setDeduc9560(resultPaybillVo.getDeduc9560()+payBillVO.getDeduc9560());//rent of building
						resultPaybillVo.setDeduc9530(resultPaybillVo.getDeduc9530()+payBillVO.getDeduc9530());//pli
						resultPaybillVo.setDeduc9540(resultPaybillVo.getDeduc9540()+payBillVO.getDeduc9540());//bli
						resultPaybillVo.setDeduc9570(resultPaybillVo.getDeduc9570()+payBillVO.getDeduc9570());//pt
						resultPaybillVo.setDeduc9580(resultPaybillVo.getDeduc9580()+payBillVO.getDeduc9580());//SIS_GIS
						resultPaybillVo.setDeduc9581(resultPaybillVo.getDeduc9581()+payBillVO.getDeduc9581());//SisIF
						resultPaybillVo.setDeduc9582(resultPaybillVo.getDeduc9582()+payBillVO.getDeduc9582());//SisSF
						resultPaybillVo.setDeduc9583(resultPaybillVo.getDeduc9583()+payBillVO.getDeduc9583());//AISIF
						resultPaybillVo.setDeduc9584(resultPaybillVo.getDeduc9584()+payBillVO.getDeduc9584());//AisSF
						resultPaybillVo.setDeduc9670(resultPaybillVo.getDeduc9670()+payBillVO.getDeduc9670());//GPF
						resultPaybillVo.setDeduc9780(resultPaybillVo.getDeduc9780()+payBillVO.getDeduc9780());//jeep rent
						resultPaybillVo.setDeduc9910(resultPaybillVo.getDeduc9910()+payBillVO.getDeduc9910());//misc recovery
						
						resultPaybillVo.setDeduc9534(resultPaybillVo.getDeduc9534()+payBillVO.getDeduc9534());//CPF
						resultPaybillVo.setDeduc9620(resultPaybillVo.getDeduc9620()+payBillVO.getDeduc9620());//AIS PF
						
						resultPaybillVo.setDeduc9531(resultPaybillVo.getDeduc9531()+payBillVO.getDeduc9531());//gpf for class iv
						resultPaybillVo.setTotalDed(resultPaybillVo.getTotalDed()+payBillVO.getTotalDed());//total deduction
						resultPaybillVo.setNetTotal(resultPaybillVo.getNetTotal()+payBillVO.getNetTotal());//net amount
						
						resultPaybillVo.setLoan9592(resultPaybillVo.getLoan9592()+payBillVO.getLoan9592());//mca loan
						resultPaybillVo.setLoan9740(resultPaybillVo.getLoan9740()+payBillVO.getLoan9740());//OCA loan
						resultPaybillVo.setLoan9591(resultPaybillVo.getLoan9591()+payBillVO.getLoan9591());//Hba loan
						resultPaybillVo.setLoan9720(resultPaybillVo.getLoan9720()+payBillVO.getLoan9720());//Fan loan
						
						resultPaybillVo.setLoanInt9592(resultPaybillVo.getLoanInt9592()+payBillVO.getLoanInt9592());//mca loan int
						resultPaybillVo.setLoanInt9740(resultPaybillVo.getLoanInt9740()+payBillVO.getLoanInt9740());//oca loan int
						resultPaybillVo.setLoanInt9591(resultPaybillVo.getLoanInt9591()+payBillVO.getLoanInt9591());//hba loan int
						resultPaybillVo.setLoanInt9720(resultPaybillVo.getLoanInt9720()+payBillVO.getLoanInt9720());//FAn loan int
						resultPaybillVo.setAdvIV9670(resultPaybillVo.getAdvIV9670()+payBillVO.getAdvIV9670());//gpf advance
						
						resultPaybillVo.setDeduc9999(resultPaybillVo.getDeduc9999()+payBillVO.getDeduc9999());
						resultPaybillVo.setDeduc9998(resultPaybillVo.getDeduc9998()+payBillVO.getDeduc9998());
						*/
					}

				}
			}
			resultPaybillVo.setHrEisEmpMst(hrEisMst);
			String empAllRec = "";
			String otherId = "";
			if (voToService.get("empAllRec") != null && !voToService.get("empAllRec").equals(""))
			{

				empAllRec = voToService.get("empAllRec").toString();
			}

			if (voToService.get("otherId") != null && !voToService.get("otherId").equals(""))
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
				/* For dialog view of preview bill in basic dtls screen*/
				//resultObject.setViewName("tempPaybillView");
				resultObject.setViewName("tempPaybillViewEmpAllRec");
				empAllRec = "false";
			}
			objectArgs.put("empAllRec", empAllRec);
			resultObject.setResultValue(objectArgs);

		}
		catch (Exception e)
		{
			logger.info("Error in getTemporaryPayBill of ViewTemporaryPayBill.");
			objectArgs.put("msg", "There is some problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorInsert");
			e.printStackTrace();
		}

		return resultObject;
	}
}
