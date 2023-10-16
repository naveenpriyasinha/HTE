package com.tcs.sgv.eis.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.allowance.service.IdGenerator;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.UpdatePaybillDAOImpl;
import com.tcs.sgv.eis.valueobject.EdpDtlsVO;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillUpdateLog;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.eis.valueobject.RltBillTypeEdp;

@SuppressWarnings("unchecked")
public class UpdatePaybillService extends ServiceImpl
{
	Log logger = LogFactory.getLog(getClass());
	int msg = 0;

	public synchronized ResultObject fillPaybillData(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		long empType = 0;

		try
		{
			logger.info("************Inside fillPaybillData*****************");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

			UpdatePaybillDAOImpl paybillUpdataDAO = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			long empId = 0;
			int month = 0, year = 0;
			double basicPay = 0;
			String searchFlag = null;
			List<EdpDtlsVO> edpDtlsAllowanceList = new ArrayList<EdpDtlsVO>();
			List<EdpDtlsVO> edpDtlsDeductionList = new ArrayList<EdpDtlsVO>();
			List<EdpDtlsVO> edpDtlsLoansList = new ArrayList<EdpDtlsVO>();

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);

			Map voToService = (Map) objectArgs.get("voToServiceMap");
			long billNo = 0;
			String billName = "";
			String gpfNo = "";
			//Start the updation part of paybill when submit from basic detail screen

			//			if (objectArgs.get("resultPaybillVo") != null)
			//			{
			//				logger.info("************Calling UpdatePaybillFromBasic*****************");
			//				updatePaybillfromBasic(objectArgs);
			//			}

			//			if (objectArgs.get("resultPaybillVo") != null)
			//				objectArgs.put("status", "1");
			//			else
			//				objectArgs.put("status", "0");
			//Ended the updation part of paybill when submit from basic detail screen.

			//Map voToService = (Map)objectArgs.get("voToServiceMap");

			if (voToService.get("searchData") != null)
				searchFlag = voToService.get("searchData").toString();
			else
				searchFlag = (voToService.get("searchData") != null && !voToService.get("searchData").toString().equals("")) ? voToService.get("searchData").toString() : "N";

			logger.info("************Value of Search Flag*****************" + searchFlag);
			List paybillList = null;

			if (searchFlag.equalsIgnoreCase("y"))
			{
				logger.info("************Search Data is Null*****************");
				month = Integer.parseInt((voToService.get("paybillMonth") != null && !voToService.get("paybillMonth").equals("")) ? voToService.get("paybillMonth").toString() : "0");
				year = Integer.parseInt((voToService.get("paybillYear") != null && !voToService.get("paybillYear").equals("")) ? voToService.get("paybillYear").toString() : "0");
				empId = Long.parseLong((voToService.get("updatePaybillEmpId") != null && !voToService.get("updatePaybillEmpId").equals("")) ? voToService.get("updatePaybillEmpId").toString() : "0");
			}

			paybillList = paybillUpdataDAO.getEmpDataByMonthAndYear(empId, month, year);
			HrPayPaybill hrPayPaybill = null;
			PaybillHeadMpg headMpg = null;
			if (paybillList != null && !paybillList.isEmpty())
			{
				Object[] paybillListdata = (Object[]) paybillList.get(0);
				hrPayPaybill = (HrPayPaybill) paybillListdata[0];
				headMpg = (PaybillHeadMpg) paybillListdata[1];
				billName = paybillListdata[2].toString();
				billNo = headMpg.getBillNo().getDcpsDdoBillGroupId();
			}

			//logger.info("The size of paybillList from fillPaybillData  is------>>>" + paybillList.size());

			if (hrPayPaybill != null)
			{
				basicPay = hrPayPaybill.getBasic0101();
			}

			//Added By Amish
			//Allowance
			Map<String, Object> mappedAllowance = new HashMap<String, Object>();
			Map<String, Object> mappedDeduction = new HashMap<String, Object>();
			UpdatePaybillDAOImpl updatePaybillDAOImpl = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			if (hrPayPaybill != null)
			{
				Class pay = hrPayPaybill.getClass();

				String edpCode = null;
				String edpShortName = null;
				String tempValue = null;
				RltBillTypeEdp rltBillTypeEdp = null;
				long type = 0;
				EdpDtlsVO edpDtlsVO = null;
				type = 2500134;
				List allowanceEdpCodeList = updatePaybillDAOImpl.getEdpCodeForEmpForGeneratedPaybill(month, year, empId, type, billNo);
				int allowanceEdpCodeListSize = 0;
				if (allowanceEdpCodeList != null && !allowanceEdpCodeList.isEmpty())
					allowanceEdpCodeListSize = allowanceEdpCodeList.size();
				logger.info("Allowance EDP Object:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + allowanceEdpCodeListSize);
				if (allowanceEdpCodeList != null && allowanceEdpCodeListSize > 0)
				{
					for (int i = 0; i < allowanceEdpCodeListSize; i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) allowanceEdpCodeList.get(i);
						edpShortName = rltBillTypeEdp.getEdpShortName();
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						String payBillMthdName = "getAllow" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();

						edpDtlsVO = new EdpDtlsVO();
						edpDtlsVO.setEdpCode(edpCode);
						edpDtlsVO.setDisplayName(edpShortName);
						edpDtlsVO.setAmount(new Double(tempValue).longValue());
						edpDtlsVO.setAddDedFlag("A");
						edpDtlsVO.setExpRcpRec("EXP");
						mappedAllowance.put(edpCode + "~EXP", edpDtlsVO);
						edpDtlsAllowanceList.add(edpDtlsVO);
					}
				}

				//Deductions
				type = 2500135;
				List deductionEdpCodeList = updatePaybillDAOImpl.getEdpCodeForEmpForGeneratedPaybill(month, year, empId, type, billNo);
				int deductionEdpCodeListSize = 0;
				if (deductionEdpCodeList != null && !deductionEdpCodeList.isEmpty())
					deductionEdpCodeListSize = deductionEdpCodeList.size();

				logger.info("Deduction EDP Object List:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + deductionEdpCodeListSize);
				if (deductionEdpCodeList != null && deductionEdpCodeListSize > 0)
				{
					for (int i = 0; i < deductionEdpCodeListSize; i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) deductionEdpCodeList.get(i);
						edpShortName = rltBillTypeEdp.getEdpShortName();
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						String payBillMthdName = "getDeduc" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();

						edpDtlsVO = new EdpDtlsVO();
						edpDtlsVO.setEdpCode(edpCode);
						edpDtlsVO.setDisplayName(edpShortName);
						edpDtlsVO.setAmount(new Double(tempValue).longValue());
						edpDtlsVO.setAddDedFlag("-(d)l");
						edpDtlsVO.setExpRcpRec("RCP");
						mappedDeduction.put(edpCode + "~RCP", edpDtlsVO);
						edpDtlsDeductionList.add(edpDtlsVO);
					}
				}

				//Loans
				/*type = 2500137;
				List loanEdpCodeList = updatePaybillDAOImpl.getEdpCodeForLoanAdv(empId, type);
				int loanEdpCodeListSize = loanEdpCodeList.size();
				logger.info("Loan EDP Object List:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" +loanEdpCodeListSize);
				
				if (loanEdpCodeList != null && loanEdpCodeListSize > 0)
				{
					for (int i = 0; i < loanEdpCodeListSize; i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) loanEdpCodeList.get(i);
						edpShortName = rltBillTypeEdp.getEdpShortName();
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						String payBillMthdName = "getLoan" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();

						edpDtlsVO = new EdpDtlsVO();
						edpDtlsVO.setEdpCode(edpCode);
						edpDtlsVO.setDisplayName(edpShortName);
						edpDtlsVO.setAmount(new Double(tempValue).longValue());
						edpDtlsVO.setAddDedFlag("-(d)");
						edpDtlsVO.setExpRcpRec("RECP");
						edpDtlsLoansList.add(edpDtlsVO);
					}
					for (int i = 0; i < loanEdpCodeListSize; i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) loanEdpCodeList.get(i);
						edpShortName = rltBillTypeEdp.getEdpShortName();
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						String payBillMthdName = "getLoanInt" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();

						edpDtlsVO = new EdpDtlsVO();
						edpDtlsVO.setEdpCode(edpCode);
						edpDtlsVO.setDisplayName(edpShortName);
						edpDtlsVO.setAmount(new Double(tempValue).longValue());
						edpDtlsVO.setAddDedFlag("-(d)");
						edpDtlsVO.setExpRcpRec("RECI");
						edpDtlsLoansList.add(edpDtlsVO);
					}
				}

				//Advances
				type = 2500136;
				List advEdpCodeList = updatePaybillDAOImpl.getEdpCodeForLoanAdv(empId, type);
				int advEdpCodeListSize = advEdpCodeList.size();
				logger.info("Advance EDP Object List:::getEdpCodeForEmpForGeneratedPaybill(month,year,empId,type)" + advEdpCodeListSize);
				if (advEdpCodeList != null &&advEdpCodeListSize > 0)
				{
					for (int i = 0; i <advEdpCodeListSize; i++)
					{
						rltBillTypeEdp = (RltBillTypeEdp) advEdpCodeList.get(i);
						edpShortName = rltBillTypeEdp.getEdpShortName();
						edpCode = rltBillTypeEdp.getEdpCode().toString();
						String payBillMthdName = "getAdv" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();

						edpDtlsVO = new EdpDtlsVO();
						edpDtlsVO.setEdpCode(edpCode);
						edpDtlsVO.setDisplayName(edpShortName);
						edpDtlsVO.setAmount(new Double(tempValue).longValue());
						edpDtlsVO.setAddDedFlag("-(d)");
						edpDtlsVO.setExpRcpRec("RECA");
						edpDtlsLoansList.add(edpDtlsVO);
					}
				}*/
				/*List allAllowanceEdp = updatePaybillDAOImpl.getAllAllowanceEdp();
				int allAllowanceEdpSize = allAllowanceEdp.size();
				for(int i = 0 ; i<allAllowanceEdpSize ; i++)
				{
					Object[] data = (Object[]) allAllowanceEdp.get(i);
					edpCode = data[0].toString();
					edpShortName = data[2].toString();
					if(!mappedAllowance.containsKey(edpCode+"~EXP"))
					{
						String payBillMthdName = "getAllow" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();
						
						if(!tempValue.equals("0"))
						{
							edpDtlsVO = new EdpDtlsVO();
							edpDtlsVO.setEdpCode(edpCode);
							edpDtlsVO.setDisplayName(edpShortName);
							edpDtlsVO.setAmount(new Double(tempValue).longValue());
							edpDtlsVO.setAddDedFlag("A");
							edpDtlsVO.setExpRcpRec("EXP");
							//mappedAllowance.put(edpCode+"~EXP",edpDtlsVO);
							edpDtlsAllowanceList.add(edpDtlsVO);
						}
					}
					
				}
				
				List allDeductEdp = updatePaybillDAOImpl.getAllDeductEdp();
				int allDeductEdpSize = allDeductEdp.size();
				tempValue = null;
				edpDtlsVO = null;
				pay = hrPayPaybill.getClass();
				for(int i = 0 ; i<allDeductEdpSize ; i++)
				{
					Object[] data = (Object[]) allDeductEdp.get(i);
					edpCode = data[0].toString();
					edpShortName = data[2].toString();
					if(!mappedDeduction.containsKey(edpCode+"~RCP"))
					{
						String payBillMthdName = "getDeduc" + edpCode;
						Method payMthd = pay.getMethod(payBillMthdName);
						tempValue = payMthd.invoke(hrPayPaybill).toString();

						if(!tempValue.equals("0"))
						{
							edpDtlsVO = new EdpDtlsVO();
							edpDtlsVO.setEdpCode(edpCode);
							edpDtlsVO.setDisplayName(edpShortName);
							edpDtlsVO.setAmount(new Double(tempValue).longValue());
							edpDtlsVO.setAddDedFlag("-(d)l");
							edpDtlsVO.setExpRcpRec("RCP");
							//mappedAllowance.put(edpCode+"~EXP",edpDtlsVO);
							edpDtlsDeductionList.add(edpDtlsVO);
						}
					}
				}*/

			}
			else if (searchFlag.equalsIgnoreCase("y"))
			{
				objectArgs.put("msg", "Records Not Found For Selected Month and Year");
			}

			//End By Amish

			objectArgs.put("paybillList", hrPayPaybill);
			objectArgs.put("edpDtlsAllowanceList", edpDtlsAllowanceList);
			objectArgs.put("edpDtlsDeductionList", edpDtlsDeductionList);
			objectArgs.put("edpDtlsLoansList", edpDtlsLoansList);
			objectArgs.put("AllowanceListSize", edpDtlsAllowanceList.size());
			objectArgs.put("DeductionListSize", edpDtlsDeductionList.size());
			objectArgs.put("LoansListSize", edpDtlsLoansList.size());

			int maxSize = Math.max(edpDtlsAllowanceList.size(), Math.max(edpDtlsDeductionList.size(), edpDtlsLoansList.size()));
			objectArgs.put("maxSize", maxSize);

			long gradeID = 0;

			String dsgnName = "";
			String scale = "";
			long gradePay = 0;

			if (paybillList != null && paybillList.size() == 1 && hrPayPaybill.getHrEisEmpMst() != null)
			{
				logger.info("************Paybill List fetched from HrPayPaybill is not null*****************" + paybillList.size());
				empType = hrPayPaybill.getHrEisEmpMst().getEmpType();

				OtherDetailDAOImpl otherDetailDAO = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
				HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
				long otherDtlPK = paybillUpdataDAO.getOtherDetailPKFromEmployeeID(hrPayPaybill.getHrEisEmpMst().getEmpId());
				hrEisOtherDtls = otherDetailDAO.read(otherDtlPK);
				if (hrEisOtherDtls.getHrEisSgdMpg() != null)
				{
					gradeID = hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeId();
					dsgnName = hrEisOtherDtls.getHrEisSgdMpg().getHrEisGdMpg().getOrgDesignationMst().getDsgnName();

					if (hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherEndAmt() > 0)
						scale = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleStartAmt() + "-" + hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleIncrAmt() + "-" + hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleEndAmt() + "-" + hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherIncrAmt() + "-" + hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleHigherEndAmt();
					else
						scale = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleStartAmt() + "-" + hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleIncrAmt() + "-" + hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleEndAmt();
					gradePay = hrEisOtherDtls.getHrEisSgdMpg().getHrEisScaleMst().getScaleGradePay();

				}

				/*HrPayUpdatebillDtlsDAO updatebillDtlsDAO = new HrPayUpdatebillDtlsDAO(HrPayUpdatebillDtls.class, serv.getSessionFactory());
				HrPayUpdatebillDtls updatebillDtls = new HrPayUpdatebillDtls();
				GenericDaoHibernateImpl hrPayUpdatebillDtls = new GenericDaoHibernateImpl(HrPayUpdatebillDtls.class);
				Object valsLoanEmpDtls[] = { paybillList.get(0).getHrEisEmpMst() };
				String colsLoanEmpDtls[] = { "hrEisEmpMst" };
				hrPayUpdatebillDtls.setSessionFactory(serv.getSessionFactory());
				List<HrPayUpdatebillDtls> billDtlsList;
				billDtlsList = hrPayUpdatebillDtls.getListByColumnAndValue(colsLoanEmpDtls, valsLoanEmpDtls);

				long attachmentId = 0;

				if (billDtlsList != null && billDtlsList.size() == 1)
				{
					attachmentId = billDtlsList.get(0).getAttachmentId();

					System.out.println("*******************************id*******************" + billDtlsList.get(0).getId());
					System.out.println("*******************************attachmentId*******************" + attachmentId);
				}
				CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
				CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attachmentId);
				objectArgs.put("orderId", cmnAttachmentMst);*/

			}
			else
				objectArgs.put("leaveEnc", 0);

			List basicDetailList = paybillUpdataDAO.getBasicDetail(empId);
			if (!basicDetailList.isEmpty())
			{
				Object[] basicData = (Object[]) basicDetailList.get(0);
				String pfSeries = basicData[0] != null ? basicData[0].toString() : "";
				String acNo = basicData[1] != null ? basicData[1].toString() : "";
				gpfNo = pfSeries + "/" + acNo;
			}

			objectArgs.put("paybillMonth", month);
			objectArgs.put("paybillYear", year);
			objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			objectArgs.put("empType", empType);
			objectArgs.put("gradeID", gradeID);
			objectArgs.put("dsgnName", dsgnName);
			objectArgs.put("scale", scale);
			objectArgs.put("basicPay", basicPay);
			objectArgs.put("gradePay", gradePay);
			objectArgs.put("billNo", billNo);
			objectArgs.put("billName", billName);
			objectArgs.put("gpfNo", gpfNo);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("************Successful Exit from fillPaybillData*****************");
			resultObject.setViewName("updatePaybill");

		}
		catch (Exception ex)
		{
			logger.info("There is some problem in fillPaybillData service******" + ex.toString());
			logger.error("Error is: " + ex.getMessage());
			logger.error(ex);

		}
		return resultObject;
	}

	//Added By Kishan
	public synchronized ResultObject updateGeneratedBill(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);

		try
		{
			logger.info("************Inside fillPaybillData*****************");
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map voToService = (Map) objectArgs.get("voToServiceMap");
			UpdatePaybillDAOImpl paybillUpdataDAO = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			PaybillHeadMpgDAOImpl headMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());

			int selectedAction = voToService.get("selectedAction") != null ? Integer.parseInt(voToService.get("selectedAction").toString()) : -1;

			if (selectedAction == 1) //voucher entry
			{
				int month = voToService.get("month") != null ? Integer.parseInt(voToService.get("month").toString()) : 0;
				int year = voToService.get("year") != null ? Integer.parseInt(voToService.get("year").toString()) : 0;
				long billNo = voToService.get("hdnBillNo") != null ? Long.valueOf(voToService.get("hdnBillNo").toString()) : 0;
				long voucherNo = voToService.get("txtVoucherNo") != null ? Long.valueOf(voToService.get("txtVoucherNo").toString()) : 0;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String voucherDateStr = voToService.get("voucherDate") != null ? sdf.format(sdf.parse(voToService.get("voucherDate").toString())) : "";
				Date voucherDate = sdf.parse(voucherDateStr);

				paybillUpdataDAO.updateVoucherDate(month, year, billNo, voucherNo, voucherDate);

				Map inputMap = objectArgs;
				inputMap.put("cmbMonth", month);
				inputMap.put("cmbYear", year);
				//	  inputMap.put("cmbDDOCode", value);
				inputMap.put("cmbBillGroup", billNo);
				inputMap.put("txtVoucherNo", voucherNo);
				inputMap.put("txtVoucherDt", voucherDateStr);
				inputMap.put("FreezeFlag", 1);
				resultObject = (ResultObject) serv.executeService("saveVoucherDtlsForContri", inputMap);

			}
			else if (selectedAction == 2) //salary update
			{
				int allowSize = voToService.get("allowSize") != null ? Integer.parseInt(voToService.get("allowSize").toString()) : 0;
				int deductSize = voToService.get("deductSize") != null ? Integer.parseInt(voToService.get("deductSize").toString()) : 0;
				//			int loanSize = voToService.get("loanSize")!=null ? Integer.parseInt(voToService.get("loanSize").toString()) : 0;
				int month = voToService.get("month") != null ? Integer.parseInt(voToService.get("month").toString()) : 0;
				int year = voToService.get("year") != null ? Integer.parseInt(voToService.get("year").toString()) : 0;
				long employeeId = voToService.get("employeeId") != null ? Long.valueOf(voToService.get("employeeId").toString()) : 0;
				long billNo = voToService.get("hdnBillNo") != null ? Long.valueOf(voToService.get("hdnBillNo").toString()) : 0;
				String remarks = voToService.get("txtRemark") != null ? voToService.get("txtRemark").toString() : "";

				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
				long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());

				Map<String, String> payslipColMap = paybillUpdataDAO.getPaySlipCol();
				List payslipDataList = new ArrayList();

				List dataList = (List) paybillUpdataDAO.getEmpDataByMonthAndYear(employeeId, month, year);
				HrPayPaybill payBillVO = new HrPayPaybill();
				if (!dataList.isEmpty())
				{
					Object[] data = (Object[]) dataList.get(0);
					payBillVO = (HrPayPaybill) data[0];
				}

				Class paybillClass = payBillVO.getClass();
				Method method = null;
				//			int maxSize = Math.max(allowSize, Math.max(deductSize, loanSize));
				int maxSize = Math.max(allowSize, deductSize);
				double allownaceTotal = payBillVO.getBasic0101();

				long tempValue = 0;
				long oldDeductTotal = 0;
				double oldAllowanceTotal = payBillVO.getBasic0101();
				long newDeductTotal = 0;
				for (int i = 1; i <= deductSize; i++)
				{
					String deductEdp = voToService.get("deduct" + i) != null ? voToService.get("deduct" + i).toString() : "";
					//String deductValue = voToService.get("deductValue"+i) != null ?voToService.get("deductValue"+i).toString() : "" ;
					String data[] = deductEdp.split("~");
					String getMtdName = "getDeduc" + data[0].toString();
					Method payMthd = paybillClass.getMethod(getMtdName);
					tempValue = new Double(payMthd.invoke(payBillVO).toString()).longValue();
					oldDeductTotal += tempValue;
				}

				for (int i = 1; i <= allowSize; i++)
				{
					String allowEdp = voToService.get("allowEdp" + i) != null ? voToService.get("allowEdp" + i).toString() : "";
					//String deductValue = voToService.get("deductValue"+i) != null ?voToService.get("deductValue"+i).toString() : "" ;
					String data[] = allowEdp.split("~");
					String getMtdName = "getAllow" + data[0].toString();
					Method payMthd = paybillClass.getMethod(getMtdName);
					tempValue = new Double(payMthd.invoke(payBillVO).toString()).longValue();
					oldAllowanceTotal += tempValue;
				}

				for (int i = 1; i <= maxSize; i++)
				{
					String allowEdp = voToService.get("allowEdp" + i) != null ? voToService.get("allowEdp" + i).toString() : "";
					String allowValue = voToService.get("allowValue" + i) != null ? voToService.get("allowValue" + i).toString() : "";

					if (!allowEdp.equals("") && !allowValue.equals(""))
					{
						String data[] = allowEdp.split("~");
						if (data[1].toString().equalsIgnoreCase("EXP"))
						{
							//long edpCode = Long.valueOf(data[0].toString());
							String mthdName = "setAllow" + data[0].toString();
							method = paybillClass.getMethod(mthdName, double.class);
							double allowanceValue = new BigDecimal(allowValue).doubleValue();
							method.invoke(payBillVO, allowanceValue);
							allownaceTotal += allowanceValue;
							if (payslipColMap != null && payslipColMap.containsKey(data[0].toString()))
							{
								Object[] dataPut = { payslipColMap.get(data[0].toString()), allowanceValue };
								payslipDataList.add(dataPut);
							}
						}
					}

					String deductEdp = voToService.get("deduct" + i) != null ? voToService.get("deduct" + i).toString() : "";
					String deductValue = voToService.get("deductValue" + i) != null ? voToService.get("deductValue" + i).toString() : "";

					if (!deductEdp.equals("") && !deductValue.equals(""))
					{
						String data[] = deductEdp.split("~");
						if (data[1].toString().equalsIgnoreCase("RCP"))
						{
							//							long edpCode = Long.valueOf(data[0].toString());
							String mthdName = "setDeduc" + data[0].toString();
							method = paybillClass.getMethod(mthdName, double.class);
							double deductVal = new BigDecimal(deductValue).doubleValue();
							method.invoke(payBillVO, deductVal);
							newDeductTotal += new Double(deductValue).longValue();

							if (payslipColMap != null && payslipColMap.containsKey(data[0].toString()))
							{
								Object[] dataPut = { payslipColMap.get(data[0].toString()), deductVal };
								payslipDataList.add(dataPut);
							}
						}
					}

					/*String loanEdp = voToService.get("loan"+i) != null ?voToService.get("loan"+i).toString() : "" ;
					String loanValue = voToService.get("loanValue"+i) != null ?voToService.get("loanValue"+i).toString() : "" ;
					
					if(!loanEdp.equals("") && !loanValue.equals(""))
					{
						String data[] = loanEdp.split("~");
						if(data[1].toString().equalsIgnoreCase("RCP"))
						{
							String mthdName = "setLoan"+data[0].toString();
							method = paybillClass.getMethod(mthdName, double.class);
							method.invoke(payBillVO, new BigDecimal(loanValue).doubleValue());
						}
						else if (data[1].toString().equalsIgnoreCase("RECI"))
						{
							String mthdName = "setLoanInt"+data[0].toString();
							method = paybillClass.getMethod(mthdName, double.class);
							method.invoke(payBillVO, new BigDecimal(loanValue).doubleValue());
						}
						else if (data[1].toString().equalsIgnoreCase("RECA"))
						{
							String mthdName = "setAdv"+data[0].toString();
							method = paybillClass.getMethod(mthdName, double.class);
							method.invoke(payBillVO, new BigDecimal(loanValue).doubleValue());
						}
					}*/
				}

				double oldGrossAmtFromDB = payBillVO.getGrossAmt();
				double oldNetTotal = payBillVO.getNetTotal();
				double oldDeductTotalDB = payBillVO.getTotalDed();

				double newGrossAmt = (oldGrossAmtFromDB - oldAllowanceTotal) + allownaceTotal;

				double FaTaPaValue = oldAllowanceTotal - oldGrossAmtFromDB;

				payBillVO.setGrossAmt(newGrossAmt);
				double finalDedcuTotal = (oldDeductTotalDB - Double.valueOf(oldDeductTotal)) + Double.valueOf(newDeductTotal);
				payBillVO.setTotalDed(finalDedcuTotal);
				double newNetTotal = newGrossAmt - finalDedcuTotal;
				payBillVO.setNetTotal(newNetTotal);

				//double oldGrossTotal = payBillVO.getGrossAmt();

				List paybillHeadObj = paybillUpdataDAO.getPaybillHeadObj(billNo, month, year);
				PaybillHeadMpg headMpg = null;

				if (!paybillHeadObj.isEmpty())
					headMpg = (PaybillHeadMpg) paybillHeadObj.get(0);

				double headGrossAmt = headMpg.getBillGrossAmt();
				double headNetAmt = headMpg.getBillNetAmt();
				double newHeadGrossAmt = (headGrossAmt - oldGrossAmtFromDB) + (allownaceTotal - FaTaPaValue);
				double newHeadNetAmt = (headNetAmt - oldNetTotal) + newNetTotal;
				headMpg.setBillGrossAmt(new Double(newHeadGrossAmt).longValue());
				headMpg.setBillNetAmt(new Double(newHeadNetAmt).longValue());
				Object[] dataPut = { "NET_TOTAL", newNetTotal };
				payslipDataList.add(dataPut);

				if ((month == 4 || month == 5) && year == 2012)
					paybillUpdataDAO.updatePaySlipData(payslipDataList, billNo, month, year, employeeId);

				headMpgDAOImpl.update(headMpg);
				paybillUpdataDAO.update(payBillVO);

				GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayPaybillUpdateLog.class);
				genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());

				HrPayPaybillUpdateLog hrPayPaybillUpdateLog = new HrPayPaybillUpdateLog();
				IdGenerator idGen = new IdGenerator();
				Long updateLogId = idGen.PKGenerator("HR_PAY_PAYBILL_UPDATE_LOG", objectArgs);
				logger.info("Id Generator for HR_PAY_PAYBILL_UPDATE_LOG generated id is -->" + updateLogId);

				hrPayPaybillUpdateLog.setUpdateLogId(updateLogId);
				hrPayPaybillUpdateLog.setEmpId(employeeId);
				hrPayPaybillUpdateLog.setHrPayPaybillId(payBillVO.getId());
				hrPayPaybillUpdateLog.setOldNetAmt(oldNetTotal);
				hrPayPaybillUpdateLog.setOldGrossAmt(oldGrossAmtFromDB);
				hrPayPaybillUpdateLog.setOldTotalDed(oldDeductTotalDB);
				hrPayPaybillUpdateLog.setNewGrossAmt(newGrossAmt);
				hrPayPaybillUpdateLog.setNewNetAmt(newNetTotal);
				hrPayPaybillUpdateLog.setNewTotalDed(finalDedcuTotal);
				hrPayPaybillUpdateLog.setCreatedDate(new Date());
				hrPayPaybillUpdateLog.setCreatedBy(userId);
				hrPayPaybillUpdateLog.setCreatedPost(postId);
				hrPayPaybillUpdateLog.setRemarks(remarks);
				genericDaoHibernateImpl.create(hrPayPaybillUpdateLog);

				paybillUpdataDAO.getSession().flush();
				genericDaoHibernateImpl.getSession().flush();
			}

			fillPaybillData(objectArgs);
			objectArgs.put("msg", "Record Updated Successfully.");

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("************Successful Exit from fillPaybillData*****************");
			resultObject.setViewName("updatePaybill");
		}
		catch (Exception ex)
		{
			logger.info("There is some problem in fillPaybillData service******" + ex.toString());
			logger.error("Error is: " + ex);
		}
		return resultObject;
	}

	//Ended By Kishan

	public synchronized ResultObject getApprovedBill(Map objectArgs)
	{
		logger.info("IN getApprovedBill Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		UpdatePaybillDAOImpl paybillDAOImpl = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		try
		{
			long month = voToService.get("month") != null ? Long.valueOf(voToService.get("month").toString()) : 0;
			long year = voToService.get("year") != null ? Long.valueOf(voToService.get("year").toString()) : 0;
			long slectedAction = voToService.get("slectedAction") != null ? Long.valueOf(voToService.get("slectedAction").toString()) : 0;
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			List approvedBillList = paybillDAOImpl.getApprovedBills(month, year, locId, slectedAction);
			int size = approvedBillList.size();
			Object[] data = null;
			StringBuffer billData = new StringBuffer();
			for (int i = 0; i < size; i++)
			{
				data = (Object[]) approvedBillList.get(i);
				long billNo = data[0] != null ? Long.valueOf(data[0].toString()) : 0;
				String billDesc = data[1] != null ? data[1].toString() : "";
				billData.append("<approved-bills>");
				billData.append("<bill-No>" + billNo + "</bill-No>");
				billData.append("<bill-Desc>" + billDesc.trim() + "</bill-Desc>");
				billData.append("</approved-bills>");

			}
			String classData = new AjaxXmlBuilder().addItem("ajax_key", billData.toString()).toString();
			logger.info("The Ajax Key is:-" + classData);
			objectArgs.put("ajaxKey", classData);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.info("Exception occurs...");
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}

	public synchronized ResultObject getEmployeeByBill(Map objectArgs)
	{
		logger.info("IN getEmployeeByBill Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		UpdatePaybillDAOImpl paybillDAOImpl = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		try
		{
			long month = voToService.get("month") != null ? Long.valueOf(voToService.get("month").toString()) : 0;
			long year = voToService.get("year") != null ? Long.valueOf(voToService.get("year").toString()) : 0;
			long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;
			long locId = StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			List employeeList = paybillDAOImpl.getEmployeeList(month, year, billNo, locId);
			int size = employeeList.size();
			Object[] data = null;
			StringBuffer employeeData = new StringBuffer();
			for (int i = 0; i < size; i++)
			{
				data = (Object[]) employeeList.get(i);
				long empId = data[0] != null ? Long.valueOf(data[0].toString()) : 0;
				String empFName = data[1] != null ? data[1].toString().concat(" ") : "";
				String empMName = data[2] != null ? data[2].toString().concat(" ") : "";
				String empLName = data[3] != null ? data[3].toString() : "";
				String empName = empFName + empMName + empLName;
				employeeData.append("<employee-list>");
				employeeData.append("<emp-Id>" + empId + "</emp-Id>");
				employeeData.append("<emp-Name>" + empName + "</emp-Name>");
				employeeData.append("</employee-list>");
			}
			String classData = new AjaxXmlBuilder().addItem("ajax_key", employeeData.toString()).toString();
			logger.info("The Ajax Key is:-" + classData);
			objectArgs.put("ajaxKey", classData);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.info("Exception occurs...");
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}

		return resultObject;
	}

	public synchronized ResultObject getVoucherEntryByBill(Map objectArgs)
	{
		logger.info("IN getEmployeeByBill Data");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map voToService = (Map) objectArgs.get("voToServiceMap");
		UpdatePaybillDAOImpl paybillDAOImpl = new UpdatePaybillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
		try
		{
			long month = voToService.get("month") != null ? Long.valueOf(voToService.get("month").toString()) : 0;
			long year = voToService.get("year") != null ? Long.valueOf(voToService.get("year").toString()) : 0;
			long billNo = voToService.get("billNo") != null ? Long.valueOf(voToService.get("billNo").toString()) : 0;

			List voucherDtlsList = paybillDAOImpl.getVoucherEntry(month, year, billNo);
			StringBuffer voucherData = new StringBuffer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

			if (!voucherDtlsList.isEmpty())
			{
				Object[] data = (Object[]) voucherDtlsList.get(0);
				Date voucherDate = sdf.parse(data[1].toString());
				voucherData.append("<voucher-dtls>");
				voucherData.append("<approved-crated>approved</approved-crated>");
				voucherData.append("<voucher-No>" + data[0] + "</voucher-No>");
				voucherData.append("<voucher-Date>" + sdf1.format(voucherDate) + "</voucher-Date>");
				voucherData.append("</voucher-dtls>");
			}
			else
			{
				voucherData.append("<voucher-dtls>");
				voucherData.append("<approved-crated>created</approved-crated>");
				voucherData.append("</voucher-dtls>");
			}
			String classData = new AjaxXmlBuilder().addItem("ajax_key", voucherData.toString()).toString();
			logger.info("The Ajax Key is:-" + classData);
			objectArgs.put("ajaxKey", classData);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
		}
		catch (Exception e)
		{
			logger.info("Exception occurs...");
			logger.error("Error is: " + e.getMessage());
			resultObject.setResultValue(objectArgs);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		return resultObject;
	}
}
