package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.OrderMstDAO;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.TokenNumberCustomVO;
import com.tcs.sgv.eis.valueobject.printAllReportCustomVO;

public class printAllReportServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());

	public ResultObject viewAllReports(Map objectArgs) {
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");

		try {

			long langId = Long.parseLong(loginMap.get("langId").toString());
			long locId = StringUtility.convertToLong(
					loginMap.get("locationId").toString()).longValue();

			Map voToService = (Map) objectArgs.get("voToServiceMap");

			TokenNumberDAOImpl TokenDAO = new TokenNumberDAOImpl(
					TrnBillRegister.class, serv.getSessionFactory());
			printAllReportCustomVO customVO = new printAllReportCustomVO();

			List listedData = new ArrayList();

			int Month = 0;
			String Year = "";
			String billNo = "";

			SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

			Date dt = new Date();
			Year = (sdfObj.format(dt)).toString();
			sdfObj = new SimpleDateFormat("MM");
			Month = Integer.parseInt((sdfObj.format(dt)).toString());

			logger.info("Month is**********" + Month);
			logger.info("Year is**********" + Year);
			// ADDED BY ROSHAN
			long locationCodeOfLoggedInDDO = locId;
			HttpServletRequest request = (HttpServletRequest) objectArgs
					.get("requestObj");
			HttpSession session = request.getSession();
			List billList = new ArrayList();
			List<MstDcpsBillGroup> BillList = new ArrayList();
			String selectedLocCode = null;
			if ((StringUtility.getParameter("selectedLocCode", request) != null)
					&& (StringUtility.getParameter("selectedLocCode", request) != "")
					&& (Long.parseLong(StringUtility.getParameter(
							"selectedLocCode", request)) != -1)) {
				selectedLocCode = StringUtility.getParameter("selectedLocCode",
						request);
				logger.info("selectedLocCode is " + selectedLocCode);
			}
			String selectedDDOCodeForLevelTwo = null;
			if ((StringUtility.getParameter("selectedDDOCodeOfLevel2DDO",
					request) != null)
					&& (StringUtility.getParameter(
							"selectedDDOCodeOfLevel2DDO", request) != "")
					&& (Long.parseLong(StringUtility.getParameter(
							"selectedDDOCodeOfLevel2DDO", request)) != -1)) {
				selectedDDOCodeForLevelTwo = StringUtility.getParameter(
						"selectedDDOCodeOfLevel2DDO", request);
				logger.info("selectedDDOCodeOfLevel2DDO is "
						+ selectedDDOCodeForLevelTwo);
			}
			billList = TokenDAO.getBillListForAllLevelReport(locId,
					selectedLocCode);
			for (Iterator itr = billList.iterator(); itr.hasNext();) {
				Object[] row = (Object[]) itr.next();
				MstDcpsBillGroup mstDcps = new MstDcpsBillGroup();
				mstDcps.setDcpsDdoBillGroupId(Long.parseLong(row[0].toString()));
				mstDcps.setDcpsDdoCode((row[1].toString()));
				mstDcps.setDcpsDdoBillDescription((row[2].toString()));
				BillList.add(mstDcps);
			}
			// ENDED BY ROSHAN
			if (voToService != null) {
				if (voToService.get("Month") != null)
					Month = Integer.parseInt(voToService.get("Month")
							.toString());
				if (voToService.get("Year") != null)
					Year = (String) voToService.get("Year").toString();
				if (voToService.get("billNo") != null)
					billNo = (String) voToService.get("billNo").toString();
			}
			if ((billNo != "") && (billNo != null)
					&& (Long.parseLong(billNo) != -1)) {
				logger.info("hii bill Nio is not null");
				locId = TokenDAO.getLocationCode(billNo);
				logger.info("hii bill Nio is not null and loc id is" + locId);
			}
			

			List DataList = new ArrayList();
			List DataListForpaybillgroupId = new ArrayList();

			if (!billNo.equalsIgnoreCase("") && Month > 0
					&& !Year.equalsIgnoreCase(""))
				DataListForpaybillgroupId = TokenDAO.getPaybillgroupIdFromPayBill(locId, billNo, Month,Year);

			logger.info("In viewAllReports DataListForpaybillgroupId size is************"
					+ DataListForpaybillgroupId.size());

			double netTotalAmountForInner = 0; // For Inner
			double netTotalAmountForOuter = 0; // For Outer
			double netTotalAmountForPageWise = 0; // For Pagewise
			double netTotalAmountForGroupWise = 0; // For Groupwise

			double incomeTax = 0;
			double professionTax = 0;
			double HRR = 0;
			double coHsgSoc = 0;
			double coHsgSocInterest = 0;

			String billDescip = "";
			double gpfAbstract = 0;
			double gpfOtherThanClassIV = 0;
			double gpfClassIV = 0;
			double formXII = 0;
			double for0049 = 0;
			double for7610 = 0;
			double gis = 0;
			double centralGis = 0;
			double gisIps = 0;
			double gisIas = 0;
			double gisIfs = 0;

			double formB = 0;
			double mcaPrinciple = 0;

			// for7610
			double computerAdv = 0;
			double hbaHousePrin = 0;
			double mcaLand = 0;
			double hbaLand = 0;
			double hbaAIS = 0;
			double mcaAIS = 0;
			double othVehAdv = 0;

			// For FormB
			double FA = 0;
			double PA = 0;
			double TA = 0;

			// for 0049
			double excesPayRecInt = 0;
			double hbaHouseInt = 0;
			double mcaLandInt = 0;
			double hbaLandInt = 0;
			double hbaAisInt = 0;
			double mcaAisInt = 0;
			double othVehAdvInt = 0;

			// for PLI
			double pli = 0;
			double accPolicy = 0;
			double revenueStamp = 0;

			long paybillGroupId = 0;
			if (DataListForpaybillgroupId != null
					&& !DataListForpaybillgroupId.isEmpty()) {
				for (int i = 0; i < DataListForpaybillgroupId.size(); i++) {
					Object rowList[] = (Object[]) DataListForpaybillgroupId
							.get(i);

					if (rowList[0] != null
							&& !(rowList[0].toString().trim()).equals("")) {
						paybillGroupId = Long.parseLong(rowList[0].toString());
					}
					if (rowList[2] != null
							&& !(rowList[2].toString().trim()).equals("")) {
						billDescip = rowList[2].toString();
					}
				}
			}
			logger.info("paybillGroupId is*********" + paybillGroupId);
			logger.info("billDescip is*********" + billDescip);

			List seriesList = new ArrayList();

			List<HrPayGpfBalanceDtls> SeriesList = new ArrayList();

			if (!billNo.equalsIgnoreCase("") && Month > 0
					&& !Year.equalsIgnoreCase("") && paybillGroupId > 0)
				seriesList = TokenDAO.getSeriesListFromBillNo(locId, Month,
						Year, paybillGroupId);
			logger.info("seriesList size is*********" + seriesList.size());
			if (seriesList != null && seriesList.size() > 0) {
				for (Iterator itr = seriesList.iterator(); itr.hasNext();) {
					Object[] row = (Object[]) itr.next();
					HrPayGpfBalanceDtls gpfBalanceDtls = new HrPayGpfBalanceDtls();
					gpfBalanceDtls.setPfSeries((row[0].toString()));
					SeriesList.add(gpfBalanceDtls);
				}
			}

			List seriesOtharThanList = new ArrayList();

			List<HrPayGpfBalanceDtls> SeriesOtherThanList = new ArrayList();

			if (!billNo.equalsIgnoreCase("") && Month > 0
					&& !Year.equalsIgnoreCase("") && paybillGroupId > 0)
				seriesOtharThanList = TokenDAO
						.getSeriesListForOtherThanFromBillNo(locId, Month,
								Year, paybillGroupId);
			if (seriesOtharThanList != null && seriesOtharThanList.size() > 0) {
				for (Iterator itr = seriesOtharThanList.iterator(); itr
						.hasNext();) {
					Object[] row = (Object[]) itr.next();
					HrPayGpfBalanceDtls gpfBalanceDtls = new HrPayGpfBalanceDtls();
					gpfBalanceDtls.setPfSeries((row[0].toString()));
					SeriesOtherThanList.add(gpfBalanceDtls);
				}
			}

			objectArgs.put("SeriesList", SeriesOtherThanList);
			objectArgs.put("SeriesListForClassIV", SeriesList);
			objectArgs.put("SeriesListForClassIVSize", SeriesList.size());
			logger.info("SeriesOtherThanList size is***********"
					+ SeriesOtherThanList.size());
			logger.info("SeriesList size is***********" + SeriesList.size());

			if (!billNo.equalsIgnoreCase("") && Month > 0
					&& !Year.equalsIgnoreCase(""))
				DataList = TokenDAO.getDataForPrintAllReports(locId, billNo,
						Month, Year);

			logger.info("In viewAllReports DataList size is************"
					+ DataList.size());

			if (DataList != null && !DataList.isEmpty()) { 
				for (int i = 0; i < DataList.size(); i++) {
					customVO = new printAllReportCustomVO();
					Object rowList[] = (Object[]) DataList.get(i);

					if (rowList[0] != null
							&& !(rowList[0].toString().trim()).equals("")) {
						netTotalAmountForInner = Double.parseDouble(rowList[0]
								.toString());
						customVO.setNetTotalAmountForInner(netTotalAmountForInner);
					}
					// objectArgs.put("netTotalAmountForInner",
					// netTotalAmountForInner);
					if (rowList[0] != null
							&& !(rowList[0].toString().trim()).equals("")) {
						netTotalAmountForOuter = Double.parseDouble(rowList[0]
								.toString());
						customVO.setNetTotalAmountForOuter(netTotalAmountForOuter);
					}

					if (rowList[0] != null
							&& !(rowList[0].toString().trim()).equals("")) {
						netTotalAmountForPageWise = Double
								.parseDouble(rowList[0].toString());
						customVO.setNetTotalAmountForPageWise(netTotalAmountForPageWise);
					}

					if (rowList[0] != null
							&& !(rowList[0].toString().trim()).equals("")) {
						netTotalAmountForGroupWise = Double
								.parseDouble(rowList[0].toString());
						customVO.setNetTotalAmountForGroupWise(netTotalAmountForGroupWise);
					}

					if (rowList[1] != null
							&& !(rowList[1].toString().trim()).equals("")) {
						gpfAbstract = Double.parseDouble(rowList[1].toString());
						customVO.setGpfAbstract(gpfAbstract);
					}
					if (rowList[1] != null
							&& !(rowList[1].toString().trim()).equals("")) {
						gpfOtherThanClassIV = Double.parseDouble(rowList[1]
								.toString());
						customVO.setGpfOtherThanClassIV(gpfOtherThanClassIV);
					}
					if (rowList[2] != null
							&& !(rowList[2].toString().trim()).equals("")) {
						gpfClassIV = Double.parseDouble(rowList[2].toString());
						customVO.setGpfClassIV(gpfClassIV);
					}

					if (rowList[2] != null
							&& !(rowList[2].toString().trim()).equals("")) {
						formXII = Double.parseDouble(rowList[2].toString());
						customVO.setFormXII(formXII);
					}

					if (rowList[3] != null
							&& !(rowList[3].toString().trim()).equals("")) {
						incomeTax = Double.parseDouble(rowList[3].toString());
						customVO.setIncomeTax(incomeTax);
					}

					if (rowList[4] != null
							&& !(rowList[4].toString().trim()).equals("")) {
						professionTax = Double.parseDouble(rowList[4]
								.toString());
						customVO.setProfessionTax(professionTax);
					}

					if (rowList[5] != null
							&& !(rowList[5].toString().trim()).equals("")) {
						HRR = Double.parseDouble(rowList[5].toString());
						customVO.setHRR(HRR);
					}

					if (rowList[6] != null
							&& !(rowList[6].toString().trim()).equals("")) {
						coHsgSoc = Double.parseDouble(rowList[6].toString());
						customVO.setCoHsgSoc(coHsgSoc);
					}

					if (rowList[7] != null
							&& !(rowList[7].toString().trim()).equals("")) {
						coHsgSocInterest = Double.parseDouble(rowList[7]
								.toString());
						customVO.setCoHsgSocInterest(coHsgSocInterest);
					}

					if (rowList[8] != null
							&& !(rowList[8].toString().trim()).equals("")) {
						gis = Double.parseDouble(rowList[8].toString());
						customVO.setGis(gis);
					}
					if (rowList[9] != null
							&& !(rowList[9].toString().trim()).equals("")) {
						centralGis = Double.parseDouble(rowList[9].toString());
						customVO.setCentralGis(centralGis);
					}
					if (rowList[10] != null
							&& !(rowList[10].toString().trim()).equals("")) {
						gisIps = Double.parseDouble(rowList[10].toString());
						customVO.setGisIps(gisIps);
					}
					if (rowList[11] != null
							&& !(rowList[11].toString().trim()).equals("")) {
						gisIas = Double.parseDouble(rowList[11].toString());
						customVO.setGisIas(gisIas);
					}
					if (rowList[12] != null
							&& !(rowList[12].toString().trim()).equals("")) {
						gisIfs = Double.parseDouble(rowList[12].toString());
						customVO.setGisIfs(gisIfs);
					}

					// For 7610(Principle)

					if (rowList[13] != null
							&& !(rowList[13].toString().trim()).equals("")) {
						computerAdv = Double
								.parseDouble(rowList[13].toString());
						customVO.setComputerAdv(computerAdv);
					}

					if (rowList[14] != null
							&& !(rowList[14].toString().trim()).equals("")) {
						hbaHousePrin = Double.parseDouble(rowList[14]
								.toString());
						customVO.setHbaHousePrin(hbaHousePrin);

					}

					if (rowList[15] != null
							&& !(rowList[15].toString().trim()).equals("")) {
						mcaLand = Double.parseDouble(rowList[15].toString());
						customVO.setMcaLand(mcaLand);

					}
					if (rowList[16] != null
							&& !(rowList[16].toString().trim()).equals("")) {
						hbaLand = Double.parseDouble(rowList[16].toString());
						customVO.setHbaLand(hbaLand);

					}

					if (rowList[24] != null
							&& !(rowList[24].toString().trim()).equals("")) {
						pli = Double.parseDouble(rowList[24].toString());
						customVO.setPli(pli);

					}

					if (rowList[25] != null
							&& !(rowList[25].toString().trim()).equals("")) {
						hbaAIS = Double.parseDouble(rowList[25].toString());
						customVO.setHbaAIS(hbaAIS);

					}
					if (rowList[27] != null
							&& !(rowList[27].toString().trim()).equals("")) {
						mcaAIS = Double.parseDouble(rowList[27].toString());
						customVO.setMcaAIS(mcaAIS);

					}
					if (rowList[29] != null
							&& !(rowList[29].toString().trim()).equals("")) {
						othVehAdv = Double.parseDouble(rowList[29].toString());
						customVO.setVehAdv(othVehAdv);

					}

					// For FormB
					if (rowList[17] != null
							&& !(rowList[17].toString().trim()).equals("")) {
						FA = Double.parseDouble(rowList[17].toString());
						customVO.setFA(FA);

					}
					if (rowList[18] != null
							&& !(rowList[18].toString().trim()).equals("")) {
						PA = Double.parseDouble(rowList[18].toString());
						customVO.setPA(PA);

					}
					if (rowList[19] != null
							&& !(rowList[19].toString().trim()).equals("")) {
						TA = Double.parseDouble(rowList[19].toString());
						customVO.setTA(TA);

					}

					// For 0049(Interest)
					if (rowList[20] != null
							&& !(rowList[20].toString().trim()).equals("")) {
						hbaLandInt = Double.parseDouble(rowList[20].toString());
						customVO.setHbaLandInt(hbaLandInt);

					}
					if (rowList[21] != null
							&& !(rowList[21].toString().trim()).equals("")) {
						mcaLandInt = Double.parseDouble(rowList[21].toString());
						customVO.setMcaLandInt(mcaLandInt);

					}
					if (rowList[22] != null
							&& !(rowList[22].toString().trim()).equals("")) {
						hbaHouseInt = Double
								.parseDouble(rowList[22].toString());
						customVO.setHbaHouseInt(hbaHouseInt);

					}
					if (rowList[23] != null
							&& !(rowList[23].toString().trim()).equals("")) {
						excesPayRecInt = Double.parseDouble(rowList[23]
								.toString());
						customVO.setExcesPayRecInt(excesPayRecInt);

					}
					if (rowList[26] != null
							&& !(rowList[26].toString().trim()).equals("")) {
						hbaAisInt = Double.parseDouble(rowList[26].toString());
						customVO.setHbaAisInt(hbaAisInt);

					}
					if (rowList[28] != null
							&& !(rowList[28].toString().trim()).equals("")) {
						mcaAisInt = Double.parseDouble(rowList[28].toString());
						customVO.setMcaAisInt(mcaAisInt);

					}
					if (rowList[30] != null
							&& !(rowList[30].toString().trim()).equals("")) {
						othVehAdvInt = Double.parseDouble(rowList[30]
								.toString());
						customVO.setVehAdvInt(othVehAdvInt);

					}

					if (rowList[31] != null
							&& !(rowList[31].toString().trim()).equals("")) {
						accPolicy = Double.parseDouble(rowList[31].toString());
						customVO.setAccPolicy(accPolicy);
					}

					/*if (rowList[32] != null
							&& !(rowList[32].toString().trim()).equals("")) {
						revenueStamp = Double.parseDouble(rowList[32]
								.toString());
						customVO.setRevenueStamp(revenueStamp);

					}
*/
					customVO.setBillDescip(billDescip);

					listedData.add(customVO);
				}// end for
			}// end if

			objectArgs.put("netTotalAmountForInner", netTotalAmountForInner);
			objectArgs.put("gpfAbstract", gpfAbstract);
			objectArgs.put("gpfOtherThanClassIV", gpfOtherThanClassIV);
			objectArgs.put("gpfClassIV", gpfClassIV);
			objectArgs.put("formXII", formXII);
			objectArgs.put("incomeTax", incomeTax);
			objectArgs.put("revenueStamp", revenueStamp);
			objectArgs.put("professionTax", professionTax);
			objectArgs.put("HRR", HRR);
			objectArgs.put("coHsgSoc", coHsgSoc);
			objectArgs.put("gis", gis);
			objectArgs.put("for0049", for0049);
			objectArgs.put("for7610", for7610);
			objectArgs.put("formB", formB);
			objectArgs.put("mcaPrinciple", mcaPrinciple);
			objectArgs.put("locId", locId);

			logger.info("In viewAllReports listedData size is **************"
					+ listedData.size());
			objectArgs.put("DataList", listedData);

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang(
					"Month", langId);
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year",
					langId);

			/*
			 * COMMENTED BY ROSHAN
			 * 
			 * List billList = new ArrayList(); List<MstDcpsBillGroup> BillList
			 * = new ArrayList();
			 * 
			 * //added by roshan
			 * billList=TokenDAO.getBillListForAllLevelReport(locId);
			 * //commented by roshan //billList =
			 * TokenDAO.getBillListForReport(locId);
			 * 
			 * 
			 * for(Iterator itr=billList.iterator();itr.hasNext();) { Object[]
			 * row = (Object[])itr.next(); MstDcpsBillGroup mstDcps = new
			 * MstDcpsBillGroup();
			 * mstDcps.setDcpsDdoBillGroupId(Long.parseLong(row[0].toString()));
			 * mstDcps.setDcpsDdoCode((row[1].toString()));
			 * mstDcps.setDcpsDdoBillDescription((row[2].toString()));
			 * BillList.add(mstDcps); //billDescip =
			 * mstDcps.getDcpsDdoBillDescription(); }
			 */

			CmnLookupMstDAOImpl cmnLookupMstObj = new CmnLookupMstDAOImpl(
					CmnLookupMst.class, serv.getSessionFactory());
			ResourceBundle resourceBundle = ResourceBundle
					.getBundle("resources.Payroll");
			long gisTypes = Long
					.parseLong(resourceBundle.getString("gisTypes"));
			List<CmnLookupMst> GisList = (List) cmnLookupMstObj
					.getAllChildren("GIS Type");
			logger.info("GIS List size is" + GisList.size());
			GisList = (List) cmnLookupMstObj.getListByColumnAndValue(
					"parentLookupId", gisTypes);
			logger.info("GIS List size after is" + GisList.size());

			objectArgs.put("gisList", GisList);
			objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			objectArgs.put("BillList", BillList);

			objectArgs.put("Month", Month);
			objectArgs.put("Year", Year);
			objectArgs.put("billNo", billNo);

			if (!billNo.equalsIgnoreCase("") && Month > 0
					&& !Year.equalsIgnoreCase("")) {
				List duplicateList = TokenDAO.checkDuplicateRecords(locId,
						billNo, Month, Year);
				if (duplicateList.size() == 0) {
					objectArgs.put("msg", "Bill has not been generated");
				}
			}
			// CODE CHANGE BY ROSHAN TO FIND ALL THE REPORT AT 2ND LEVEL.
			PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,
					serv.getSessionFactory());
			List<OrgDdoMst> ddoList = payDAO
					.getDDOCodeByLoggedInlocId(locationCodeOfLoggedInDDO);
			OrgDdoMst ddoMst = null;
			if (ddoList != null && ddoList.size() > 0) {
				ddoMst = ddoList.get(0);
			}

			String loggedInddoCode = null;
			if (ddoMst != null)
				loggedInddoCode = ddoMst.getDdoCode();
			OrderMstDAO orderMasterDAO = new OrderMstDAOImpl(
					HrPayOrderMst.class, serv.getSessionFactory());
			logger.info("logged in ddo code is " + loggedInddoCode);
			long countOfDDOCode = orderMasterDAO.checkUser(loggedInddoCode);
			long countOfSameDDO = orderMasterDAO.findUsertype(loggedInddoCode);
			long countOfSameDDOToCheckLevel3 = orderMasterDAO
					.findUsertypeToCheckLevel3(loggedInddoCode);
			long countOfSameDDOToCheckLevel4 = orderMasterDAO
					.findUsertypeToCheckLevel4(loggedInddoCode);
			String userType = null;
			if (countOfDDOCode >= 1) {
				userType = "asstDDO";
				resultObject.setViewName("printAllReports");
			} else if (countOfSameDDO >= 1) {
				userType = "reptDDO";
				List asstDDO = TokenDAO.getAllAsstDDOList(loggedInddoCode,
						selectedDDOCodeForLevelTwo);
				logger.info("hii to find the asst ddo list for"
						+ asstDDO.size());
				objectArgs.put("asstDDO", asstDDO);
				objectArgs.put("selectedLocCode", selectedLocCode);
				resultObject.setViewName("printAllReportsByLevelTwo");
			} else if (countOfSameDDOToCheckLevel3 >= 1) {
				userType = "finalDDO";
				resultObject.setViewName("printAllReportsByLevelThree");
				List reptDDO = TokenDAO.getAllReptDDOList(loggedInddoCode);
				logger.info("hii to find the asst ddo list for"
						+ reptDDO.size());
				List asstDDO = null;
				asstDDO = TokenDAO.getAllAsstDDOList(loggedInddoCode,
						selectedDDOCodeForLevelTwo);
				objectArgs.put("reptDDO", reptDDO);
				objectArgs.put("selectedDDOCodeForLevelTwo",
						selectedDDOCodeForLevelTwo);
				objectArgs.put("asstDDO", asstDDO);
				objectArgs.put("selectedLocCode", selectedLocCode);

			} else if (countOfSameDDOToCheckLevel4 >= 1) {
				userType = "finalDDO";
				resultObject.setViewName("printAllReportsByLevelThree");
				List reptDDO = TokenDAO.getAllReptDDOList(loggedInddoCode);
				logger.info("hii to find the asst ddo list for"
						+ reptDDO.size());
				List asstDDO = null;
				asstDDO = TokenDAO.getAllAsstDDOList(loggedInddoCode,
						selectedDDOCodeForLevelTwo);
				objectArgs.put("reptDDO", reptDDO);
				objectArgs.put("selectedDDOCodeForLevelTwo",
						selectedDDOCodeForLevelTwo);
				objectArgs.put("asstDDO", asstDDO);
				objectArgs.put("selectedLocCode", selectedLocCode);
			}
			logger.info("the user type is*********************" + userType);

			// List reptDDO=
			// lObjNewRegDdoDAO.getAllReptDDOListByFinalDDO(countOfSameDDOToCheckLevel3);

			// CODE CHANGE BY ROSHAN TO FIND ALL THE REPORT AT 2ND LEVEL.
			/*
			 * commented by roshan resultObject.setViewName("printAllReports");
			 */
			resultObject.setResultCode(0);
			resultObject.setResultValue(objectArgs);

		}

		catch (Exception e) {
			logger.error("Error is: " + e.getMessage());
		}

		return resultObject;
	}

}
