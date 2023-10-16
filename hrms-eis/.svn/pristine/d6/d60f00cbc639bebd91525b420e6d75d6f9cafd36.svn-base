package com.tcs.sgv.eis.dao;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.dao.LedgerReportDAOImpl;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAO;
import com.tcs.sgv.eis.zp.ZpReportingDDO.dao.ZpReportingDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;



public class MahaPayBillInnerDao extends DefaultReportDataFinder implements
ReportDataFinder, ReportEventListener {

	@SuppressWarnings({"unchecked"})
	public Collection findReportData(ReportVO report, Object criteria)
	throws ReportException {

		Log logger = LogFactory.getLog(getClass());
		// logger.info("called 2");

		// logger.info("We are inside findReportData function of MahaPayBillInnerDao");

		final int displayPostsInRow = 7;
		ReportColumnVO[] newReportColumns = new ReportColumnVO[displayPostsInRow + 4];
		ArrayList DataList = new ArrayList();
		long billNo = 0, month = 0, year = 0;
		final Map requestAttributes = (Map) ((Map) criteria)
		.get(IReportConstants.REQUEST_ATTRIBUTES);
		final Map serviceMap = (Map) requestAttributes.get("serviceMap");
		final Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");

		long locId = 0;
		newReportColumns[0] = new ReportColumnVO();

		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap
		.get("locationVO");
		long locationId = locationVO.getLocId();
		int colorCount=0;
		int colorPostInRow=0;
		HrPayPaybill colorPaybillVO=null;
		Map requestKeys = (Map) ((Map) criteria)
		.get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv = (ServiceLocator) requestKeys
		.get("serviceLocator");

		PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,
				serv.getSessionFactory());
		String DeptName = payBillDAOImpl.getOffice(locationId);

		// configure column vo by setting different column properties
		newReportColumns[0].setColumnId(1);
		newReportColumns[0].setColumnHeader("");
		newReportColumns[0].setDataType(10);
		// newReportColumns[i].setAlignment();
		newReportColumns[0].setTableName("a");
		newReportColumns[0].setColumnName("asd0");
		newReportColumns[0].setDisplayTotal(0);
		newReportColumns[0].setColumnLevel(1);
		newReportColumns[0].setColumnWidth(2);

		for (int i = 1; i < displayPostsInRow + 3; i++) {
			// initialize column vo
			newReportColumns[i] = new ReportColumnVO();

			// configure column vo by setting different column properties
			newReportColumns[i].setColumnId(i + 1);
			newReportColumns[i].setColumnHeader("");
			newReportColumns[i].setDataType(10);
			// newReportColumns[i].setAlignment();
			newReportColumns[i].setTableName("a");
			newReportColumns[i].setColumnName("asd" + i);
			newReportColumns[i].setDisplayTotal(0);
			newReportColumns[i].setColumnLevel(1);
			newReportColumns[i].setColumnWidth(11);

			// logger.info("in for "+i);

		}

		newReportColumns[10] = new ReportColumnVO();

		newReportColumns[10].setColumnId(10);
		newReportColumns[10].setColumnHeader("");
		newReportColumns[10].setDataType(10);
		// newReportColumns[i].setAlignment();
		newReportColumns[10].setTableName("a");
		newReportColumns[10].setColumnName("asd0");
		newReportColumns[10].setDisplayTotal(0);
		newReportColumns[10].setColumnLevel(1);
		newReportColumns[10].setColumnWidth(2);

		report.setReportColumns(newReportColumns);
		report.initializeDynamicTreeModel();
		report.initializeTreeModel();

		// long locationId=locationVO.getLocId();
		// ServiceLocator serv = (ServiceLocator)
		// requestAttributes.get("serviceLocator");
		try {
			//logger.info("Month in param is "
			//+ report.getParameterValue("Month"));
			// billNo =
			// StringUtility.convertToLong(report.getParameterValue("Bill No")!=null?report.getParameterValue("Bill No").toString().trim():"0");
			month = StringUtility.convertToLong(report
					.getParameterValue("Month") != null ? report
							.getParameterValue("Month").toString() : "-1");
			year = StringUtility
			.convertToLong(report.getParameterValue("Year") != null ? report
					.getParameterValue("Year").toString()
					: "0");

			//added by vaibhav tyagi: start

			logger.info("bill no in my function...."+StringUtility.convertToLong(report
					.getParameterValue("billNo").toString()));
			if((StringUtility.convertToLong(report.getParameterValue("Flag").toString()) != null) && !report.getParameterValue("Flag").toString().equals("") && StringUtility.convertToLong(report.getParameterValue("Flag").toString())!=StringUtility.convertToLong(report.getParameterValue("billNo").toString())&& (StringUtility.convertToLong(report.getParameterValue("Flag").toString())!=1)){
				logger.info("------My Function----");
				String ddoCode= report.getParameterValue("Flag").toString();
				logger.info("ddoCode...."+ddoCode);
				//StringUtility.convertToLong(report.getParameterValue("ddocode")!=null ? report
				//.getParameterValue("ddocode").toString() : "-1");
				ZpReportingDAO zpDepartmentDAO =new ZpReportingDAOImpl(ZpReportingDAOImpl.class,serv.getSessionFactory());
				locId= StringUtility.convertToLong(zpDepartmentDAO .getLocID(ddoCode).toString());
			}

			else if(StringUtility.convertToLong(report.getParameterValue("Flag").toString())==1){
				logger.info("------My Function Lvl1 forward----");
				locId = StringUtility.convertToLong(baseLoginMap.get("locationId").toString());
			}
			else{
				logger.info("------Not My Function----");
				locId = StringUtility.convertToLong(baseLoginMap.get("locationId").toString());
			}
			//added by vaibhav tyagi : end

			//commented by vaibhav tyagi
			/*locId = StringUtility.convertToLong(baseLoginMap.get("locationId")
					.toString());*/
			// billNo=991000028;
			/*logger.info("bill no is" + billNo);
			logger.info("bill no is" + month);
			logger.info("bill no is" + year);*/

			String BillNoinner = "";
			int l = 0;
			String subheadCode = "";
			String classIds = "";
			String desgnIds = "";
			String portType = "";
			String BillNo = "";

			BillNoinner = CheckIfNull(report.getParameterValue("Bill No"));

			logger.info(BillNoinner);
			StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
			while (st1.hasMoreTokens()) {
				if (l == 0)
					subheadCode = st1.nextToken();
				else if (l == 1)
					classIds = st1.nextToken();
				else if (l == 2)
					desgnIds = st1.nextToken();
				else if (l == 3) {
					portType = st1.nextToken();

					//logger.info("adfgadfgh" + portType);
				} else if (l == 4) {

					billNo = StringUtility.convertToLong(st1.nextToken());
				}
				// logger.info("Actual value for bill no  "+BillNo);
				l++;
			}

			if (BillNoinner.trim().equals("") || billNo == 0)
				billNo = StringUtility.convertToLong(report
						.getParameterValue("billNo") != null ? report
								.getParameterValue("billNo").toString().trim() : "0");

		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			logger.error("Exception occur in Paybill Inner " + e);
		}

		String ddocode ="";
		PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
		//ADDED BY ROSHAN FOR REPORTS AT ALL LEVEL
		
		if (billNo !=0){
			locId=payBillDAO.getLocationCode(billNo);
			logger.info("hii i m in mahapaybillinnerdao for inner.");
			
		} 
		//ended BY ROSHAN FOR REPORTS AT ALL LEVEL
		if(ddoCodeList!=null)
			logger.info("After query execution for DDO Code " + ddoCodeList.size());

		OrgDdoMst ddoMst = null; 
		if(ddoCodeList!=null && !ddoCodeList.isEmpty()){
			ddoMst = ddoCodeList.get(0);
		}

		if(ddoMst!=null) {
			ddocode=ddoMst.getDdoCode();
		}
		logger.info("DDO Code is " + ddocode);


		String[] monthName = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
		"December" };

		int MONTH = (int) month;
		String MONTHNAME = monthName[MONTH - 1];

		// Ended by Abhilash


		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO();

		report.setReportAttributes(null);
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER, baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT, baseFont);
		report.setReportTemplate(rt);

		Calendar calGiven = Calendar.getInstance();
		calGiven.set(Calendar.YEAR, Integer.valueOf(Long.valueOf(year).toString()));
		calGiven.set(Calendar.MONTH, (Integer.valueOf(Long.valueOf(month).toString()) - 1));
		calGiven.set(Calendar.DAY_OF_MONTH, 1);
		Date givenDate = calGiven.getTime();
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		String givenStrDate = sdf.format(givenDate);



		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
		"dd-MM-yyyy HH:mm:ss.SSS");
		//logger.info("10. " + format.format(now));

		/*raavo.setAttributeType(IReportConstants.ATTRIB_OTHER);
		raavo.setLocation(IReportConstants.LOCATION_HEADER);
		raavo.setAlignment(IReportConstants.ALIGN_LEFT);*/

		// Value can be from a property file

		// add by khushal
		logger.info("------Bill number is --" + billNo);

		List lStrGrpName = payBillDAOImpl.getDcpsClassGroup(billNo);

		String officeName = payBillDAOImpl.getOffice(locId);
		//logger.info("-----Groupclass----" + lStrGrpName);
		// end by khushal

		/*raavo.setAttributeValue("Bill Id (" + billNo + ") ");
		raavo.setAttributeValue("Bill Id (" + billNo	+ ") MTR 19 - PERMANENT POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");

		report.addReportAttributeItem(raavo);*/

		ReportAttributeVO ravo = new ReportAttributeVO();

		ravo = new ReportAttributeVO();
		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
		ravo.setAlignment(IReportConstants.ALIGN_LEFT);
		ravo.setAttributeValue("SALARY FOR THE MONTH & YEAR :" + MONTHNAME
				+ " " + year + "<br>");
		report.addReportAttributeItem(ravo);

		ravo = new ReportAttributeVO();
		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
		ravo.setAlignment(IReportConstants.ALIGN_LEFT);
		ravo.setAttributeValue(ddocode + " -OFFICE NAME :" + DeptName);
		report.addReportAttributeItem(ravo);

		ravo = new ReportAttributeVO();
		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
		ravo
		.setAttributeValue("BILL GENERATION TIME : " + format.format(now)
				+ "<br>");
		report.addReportAttributeItem(ravo);

		/*ravo = new ReportAttributeVO();
		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
		ravo.setAttributeValue("  Treasury Code  -" + TresuryCode);
		report.addReportAttributeItem(ravo);*/
		// add the attribute to the report instance

		// logger.info("completed");

		StyleVO[] URLStyleVO = new StyleVO[3];

		// page size defined here

		URLStyleVO[0] = new StyleVO();
		URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
		URLStyleVO[0].setStyleValue(23 + "");

		URLStyleVO[1] = new StyleVO();
		URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
		URLStyleVO[1].setStyleValue("yes");

		URLStyleVO[2] = new StyleVO();
		URLStyleVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		URLStyleVO[2].setStyleValue("8");
		report.setStyleList(URLStyleVO);

		StyleVO[] numberDispalyVO = new StyleVO[3];
		numberDispalyVO[0] = new StyleVO();
		numberDispalyVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		numberDispalyVO[0]
		                .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
		numberDispalyVO[1] = new StyleVO();
		numberDispalyVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		numberDispalyVO[1].setStyleValue("Right");
		numberDispalyVO[2] = new StyleVO();
		numberDispalyVO[2].setStyleId(IReportConstants.BORDER);
		numberDispalyVO[2].setStyleValue("NO");

		StyleVO[] nameVo = new StyleVO[2];
		nameVo[0] = new StyleVO();
		nameVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		nameVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_200);
		nameVo[1] = new StyleVO();
		nameVo[1].setStyleId(IReportConstants.BORDER);
		nameVo[1].setStyleValue("NO");

		StyleVO[] boldStyleVO = new StyleVO[2];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_500);
		boldStyleVO[1] = new StyleVO();
		boldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		boldStyleVO[1].setStyleValue("Right");

		//added by manish khunt

		StyleVO[] colorVO = new StyleVO[2];
		colorVO[0] = new StyleVO();
		colorVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
		colorVO[0].setStyleValue(IReportConstants.VALUE_FONT_COLOR_RED);
		colorVO[1] = new StyleVO();
		colorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		colorVO[1].setStyleValue("Right");




		ResourceBundle  resourceBundle=ResourceBundle.getBundle("resources.Payroll");
		String hbaLandPrinId= resourceBundle.getString("hbaForLandPrincipal");
		String hbaHousePrinId= resourceBundle.getString("hbaForHousePrincipal");
		String hbaLandIntId=resourceBundle.getString("hbaForLandInterest");
		String hbaHouseIntId=resourceBundle.getString("hbaForHouseInterest");
		logger.info("hbaLandPrinId ="+hbaLandPrinId+"hbaHousePrinId="+hbaHousePrinId+"hbaLandIntId="+hbaLandIntId+"hbaHouseIntId="+hbaHouseIntId);
		try {
			HrEdpComponentMpgDAOImpl edpDao = new HrEdpComponentMpgDAOImpl(
					HrPayEdpCompoMpg.class, serv.getSessionFactory());
			DeptCompMPGDAOImpl compDao = new DeptCompMPGDAOImpl(
					HrPayLocComMpg.class, serv.getSessionFactory());
			DeducTypeMasterDAOImpl deduDao = new DeducTypeMasterDAOImpl(
					HrPayDeducTypeMst.class, serv.getSessionFactory());
			LoanAdvMstDAOImpl loanDao = new LoanAdvMstDAOImpl(
					HrLoanAdvMst.class, serv.getSessionFactory());
			List<HrPayEdpCompoMpg> allowEdpList = new ArrayList<HrPayEdpCompoMpg>();// edpDao.getAllowCompoMpgData(locId);
			List<HrPayEdpCompoMpg> deducAgEdpList = new ArrayList<HrPayEdpCompoMpg>();// edpDao.getAGDeducCompoMpgData(locId);
			List<HrPayEdpCompoMpg> deducTyEdpList = new ArrayList<HrPayEdpCompoMpg>();// edpDao.getTRDeducCompoMpgData(locId);
			List<HrPayEdpCompoMpg> loanList = new ArrayList<HrPayEdpCompoMpg>();
			List<HrPayEdpCompoMpg> loanListNew = new ArrayList<HrPayEdpCompoMpg>();
			List<HrPayEdpCompoMpg> loanListGross = new ArrayList<HrPayEdpCompoMpg>();

			List<HrPayEdpCompoMpg> allEdpList = edpDao.getAllData();

			HashMap deducEdpNameMap  =edpDao.getDeducEdpMap();
			HashMap allwEdpNameMap  =edpDao.getAllowEdpMap();
			HashMap loanEdpNameMap  =edpDao.getLoanAdvEdpMap();

			List<HrPayLocComMpg> locAllow = compDao
			.getDataAllowChckedForMonthYear(locId, month, year);
			List<HrPayLocComMpg> locDeduc = compDao
			.getDataDeductChckedForMonthYear(locId, month, year);
			List<HrPayLocComMpg> loanListMapped = compDao
			.getDataLoanChckedForMonthYear(locId, month, year);

			//added by vaibhav tyagi: start
			// Added by Ankit for Inner Comps
			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv
					.getSessionFactory());
			GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(
					CmnLocationMst.class);
			gen.setSessionFactory(serv.getSessionFactory());
			CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locId);
			long parentLocId = cmnLocMst.getParentLocId();
			Map innerMap  = payDao.getBillDataForInner(locId, billNo, month, year, parentLocId);
			ArrayList<HrPayPaybill> paybillVoList  = (ArrayList<HrPayPaybill>)innerMap.get("paybillVoLst");
			HrPayPaybill paybillVoTmp = null;

			//added by vaibhav tyagi: end
			for (int i = 0; i < allEdpList.size(); i++) {
				if (allEdpList.get(i).getTypeId() != null) {
					if (allEdpList.get(i).getCmnLookupId() == 2500134) {
						/*for (int j = 0; j < locAllow.size(); j++) {
							if (allEdpList.get(i).getTypeId().equals(
									"" + locAllow.get(j).getCompId())) {
								// logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name "
								// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
								allowEdpList.add(allEdpList.get(i));
								break;
							}
						}*/
						// Added by Ankit for Inner Comps
						double value = 0;
						for (int p = 0; p < paybillVoList.size(); p++) {

							paybillVoTmp = (HrPayPaybill) paybillVoList.get(p);

							String method = "getAllow"
								+ allwEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
							Class pay = paybillVoTmp.getClass();
							Method payMthd = pay.getMethod(method, null);

							value  = value  + (Double) payMthd.invoke(
									paybillVoTmp, null);

						}
						if(value > 0)
							allowEdpList.add(allEdpList.get(i));


					} else if (allEdpList.get(i).getCmnLookupId() == 2500135) {
						logger.info("locDeduc size gayathri:"+locDeduc.size());
						for (int j = 0; j < locDeduc.size(); j++) {
							if (allEdpList.get(i).getTypeId().equals(
									"" + locDeduc.get(j).getCompId())) {
								HrPayDeducTypeMst ded = deduDao.read(locDeduc
										.get(j).getCompId());
								logger.info("tocheck AG or TRY gayathri:"+ded.getDeductionBy().getLookupId());
								if (ded.getDeductionBy().getLookupId() == 2901424) {
									logger.info("locDeduc size gayathri11:");
									// logger.info("deduc by AG "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name "
									// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									//deducAgEdpList.add(allEdpList.get(i));

									// Added by Ankit for Inner Comps
									double value = 0;
									for (int p = 0; p < paybillVoList.size(); p++) {

										paybillVoTmp = (HrPayPaybill) paybillVoList.get(p);

										String method = "getDeduc"
											+ deducEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
										Class pay = paybillVoTmp.getClass();
										Method payMthd = pay.getMethod(method, null);
										logger.info("method gayathri in 2901424:"+method);
										value  = value  + (Double) payMthd.invoke(
												paybillVoTmp, null);
										logger.info("value gayathri in 2901424:"+value);

									}
									if(value > 0)
										deducAgEdpList.add(allEdpList.get(i));
								} else if (ded.getDeductionBy().getLookupId() == 2901425) {
									logger.info("locDeduc size gayathri22:");
									// logger.info("deduc by TRA "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name "
									// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									//deducAgEdpList.add(allEdpList.get(i));

									//added by vaibhav tyagi: start
									// Added by Ankit for Inner Comps
									double value = 0;
									logger.info("paybillVoList size gayathri:"+paybillVoList.size());
									for (int p = 0; p < paybillVoList.size(); p++) {

										paybillVoTmp = (HrPayPaybill) paybillVoList.get(p);

										String method = "getDeduc"
											+ deducEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
										logger.info("method gayathri:"+method);
										Class pay = paybillVoTmp.getClass();
										Method payMthd = pay.getMethod(method, null);

										value  = value  + (Double) payMthd.invoke(
												paybillVoTmp, null);
										logger.info("value gayathri:"+value);
									}
									if(value > 0)
										deducTyEdpList.add(allEdpList.get(i));
									//added by vaibhav tyagi: end
								} else {

								}
								break;
							}
						}

					} else if (allEdpList.get(i).getCmnLookupId() == 2500136) {
						for (int j = 0; j < loanListMapped.size(); j++) {
							if (allEdpList.get(i).getTypeId().equals(
									"" + loanListMapped.get(j).getCompId())) {
								HrLoanAdvMst hrLoanAdvMst = loanDao
								.read(loanListMapped.get(j).getCompId());
								long dispType = hrLoanAdvMst.getDisplayGroup()
								.getLookupId();
								/*logger.info("Loan is "
										+ hrLoanAdvMst.getLoanAdvName()
										+ " display place " + dispType);*/

								//added by vaibhav tyagi : start
								// Added by Ankit for Inner Comps
								double value = 0;
								for (int p = 0; p < paybillVoList.size(); p++) {

									paybillVoTmp = (HrPayPaybill) paybillVoList.get(p);

									String method = "getAdv"+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
									Class pay = paybillVoTmp.getClass();
									Method payMthd = pay.getMethod(method, null);

									value  = value  + (Double) payMthd.invoke(
											paybillVoTmp, null);

									/*String method = "getLoan"+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
									Class pay = paybillVoTmp.getClass();
									Method payMthd = pay.getMethod(method, null);

									value  = value  + (Double) payMthd.invoke(
											paybillVoTmp, null);

									method = "getAdv"+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
									payMthd = pay.getMethod(method, null);
									value  = value  + (Double) payMthd.invoke(
											paybillVoTmp, null);*/


								}
								if(value > 0)
								{

									if (dispType == 2500381) {
										loanListNew.add(allEdpList.get(i));
									} else if (dispType == 2500382) {

										loanList.add(allEdpList.get(i));
									} else if (dispType == 2500383) {
										loanListGross.add(allEdpList.get(i));
									}
								}
								//added by vaibhav tyagi : end
								/*if (dispType == 2500381) {
									loanListNew.add(allEdpList.get(i));
								} else if (dispType == 2500382) {

									loanList.add(allEdpList.get(i));
								} else if (dispType == 2500383) {
									loanListGross.add(allEdpList.get(i));
								}*/
								// logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name "
								// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
								// loanList.add(allEdpList.get(i));
								break;
							}
						}

					} else if (allEdpList.get(i).getCmnLookupId() == 2500137) {
						for (int j = 0; j < loanListMapped.size(); j++) {
							if (allEdpList.get(i).getTypeId().equals(
									"" + loanListMapped.get(j).getCompId())) {

								HrLoanAdvMst hrLoanAdvMst = loanDao
								.read(loanListMapped.get(j).getCompId());
								long dispType = hrLoanAdvMst.getDisplayGroup()
								.getLookupId();
								/*logger.info("Loan is "
										+ hrLoanAdvMst.getLoanAdvName()
										+ " display place " + dispType);*/
								/*	if (dispType == 2500381) {
									loanListNew.add(allEdpList.get(i));
								} else if (dispType == 2500382) {
									if(hrLoanAdvMst.getLoanAdvId()==67 || hrLoanAdvMst.getLoanAdvId()==56)
									{
										loanListNew.add(allEdpList.get(i));
									}
									loanList.add(allEdpList.get(i));
								} else if (dispType == 2500383) {
									loanListGross.add(allEdpList.get(i));
								}
								// logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name "
								// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
								// loanList.add(allEdpList.get(i));
								break;*/
								//added by vaibhav tyagi : start

								// Added by Ankit for Inner Comps
								double value = 0;
								for (int p = 0; p < paybillVoList.size(); p++) {

									paybillVoTmp = (HrPayPaybill) paybillVoList.get(p);

									String method = "getLoan"+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
									/*String method = null;
								if(allEdpList.get(i).getTypeId().equals("61"))
									method = "getLoan"
										+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];

								else
									method = "getLoanInt"
										+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
									 */
									Class pay = paybillVoTmp.getClass();
									Method payMthd = pay.getMethod(method, null);

									value  = value  + (Double) payMthd.invoke(
											paybillVoTmp, null);

									/*String method = "getLoan"+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
								Class pay = paybillVoTmp.getClass();
								Method payMthd = pay.getMethod(method, null);

								value  = value  + (Double) payMthd.invoke(
										paybillVoTmp, null);

								method = "getAdv"+ loanEdpNameMap.get(String.valueOf(allEdpList.get(i).getTypeId())).toString().split(",")[0];
								payMthd = pay.getMethod(method, null);
								value  = value  + (Double) payMthd.invoke(
										paybillVoTmp, null);*/

								}
								if (dispType == 2500381) {
									logger.info("Value is: "+ value);
									loanListNew.add(allEdpList.get(i));
								}
								if(value > 0)
								{
									/*if (dispType == 2500381) {
									logger.info("Value is: "+ value);
									loanListNew.add(allEdpList.get(i));
								}*/ //else 
									if (dispType == 2500382) {
										/*if(hrLoanAdvMst.getLoanAdvId()==67 || hrLoanAdvMst.getLoanAdvId()==56)
									{
										loanListNew.add(allEdpList.get(i));
									}*/
										loanList.add(allEdpList.get(i));
									} else if (dispType == 2500383) {
										loanListGross.add(allEdpList.get(i));
									}
								}
								// logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name "
								// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
								// loanList.add(allEdpList.get(i));
								break;

								//added by vaibhav tyagi : end
							}
						}

					}

				}

			}

			logger.info("The size of loanList is " + loanList.size());
			logger.info("The size of loanListNew is " + loanListNew.size());
			logger.info("Loan list gross is " + loanListGross.size());
			/*PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv
					.getSessionFactory());
			GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(
					CmnLocationMst.class);*/
			ScaleMasterDAOImpl scaleMasterDAOImpl = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
			gen.setSessionFactory(serv.getSessionFactory());
			/*CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locId);

			long parentLocId = cmnLocMst.getParentLocId();

			Map innerMap  = payDao.getBillDataForInner(locId, billNo, month, year, parentLocId);
			ArrayList<HrPayPaybill> paybillVoList  = (ArrayList<HrPayPaybill>)innerMap.get("paybillVoLst");*/
			boolean tempPostFlag=  Boolean.parseBoolean(String.valueOf(innerMap.get("flag")));
			Map postGroupMap = (Map)innerMap.get("postGroupMap");
			Map postDesigMap = (Map)innerMap.get("postDesigMap");
			//Map postGroupMap = payBillDAO.getpPostGroupMpg(locId, billNo, month, year, parentLocId);

			logger.info("postGroupMap is "+postGroupMap);
			// manish started here
			long tempVacant = 0;
			List nonVacantList = new ArrayList<OrgPostDetailsRlt>();
			Map empDesigMap = new HashMap();
			long tempVar = 0;

			logger.info("size of paybilvo list is " + paybillVoList.size());
			if (paybillVoList != null && !paybillVoList.isEmpty())
				logger.info(" paybillgrpId is "
						+ paybillVoList.get(0).getPaybillGrpId());
			int prmCounter = 0;
			int tmpCounter=0;
			int statCounter=0;

			int total = 0;

			List tempMap = new ArrayList();

			List checkList = new ArrayList();

			long permCounter=0;


			GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(
					OrgPostDetailsRlt.class);
			genDao.setSessionFactory(serv.getSessionFactory());

			//added by vaibhav for inner after detach:start
			Calendar calGiven1 = Calendar.getInstance();
			calGiven1.set(Calendar.YEAR, Integer.valueOf(Long.valueOf(year).toString()));
			calGiven1.set(Calendar.MONTH, (Integer.valueOf(Long.valueOf(month).toString()) - 1));
			calGiven1.set(Calendar.DAY_OF_MONTH, 1);
			Date givenDate1 = calGiven1.getTime();
			SimpleDateFormat sdf1  = new SimpleDateFormat("yyyy-MM-dd");
			String givenStrDate1 = sdf.format(givenDate1);
			//added by vaibhav for inner after detach:end

			for (int p = 0; p < paybillVoList.size(); p++) {

				OrgPostMst orgPostMst = paybillVoList.get(p).getOrgPostMst();
				long postId= orgPostMst.getPostId();
				CmnLookupMst cmnLookupMst  = orgPostMst.getPostTypeLookupId();
				long orgDesignationId= 0;
				String dsgnName="";
				if(cmnLookupMst.getLookupName().contains("Per") && paybillVoList.get(p).getHrEisEmpMst()!= null)
					permCounter=permCounter+1;

				if (tempVacant == 0) {
					if (tempVar == 0) {
						tempVacant = 0;
						orgDesignationId= Long.valueOf(String.valueOf(postDesigMap.get(String.valueOf(postId))).split(",")[0]);
						dsgnName = String.valueOf(postDesigMap.get(String.valueOf(postId))).split(",")[1];

						logger.info("designation id is "+orgDesignationId + "dsgn name is "+dsgnName);
						tempMap.add(orgDesignationId);

						String group="";
						String  postGroupString=""+orgDesignationId;

						if(postGroupMap.get(postId+"".trim()) != null)
						{	

							//postGroupString+=","+postGroupMap.get(""+postId).toString();;
							group+=postGroupMap.get(""+postId).toString();
						}
						logger.info("postGroupString is "+postGroupString + "group is "+group);

						/*if(checkList.contains(postGroupString))
						{
							nonVacantList = payBillDAOImpl.getTotalPostNew(orgDesignationId, billNo,locId,true,group,givenStrDate);
							//nonVacantList = payBillDAOImpl.getTotalPostNew1(orgDesignationId, billNo,locId,true,group,givenStrDate,month,year);
						}
						else
						{
							nonVacantList = payBillDAOImpl.getTotalPostNew(orgDesignationId, billNo,locId,false,group,givenStrDate);
							//nonVacantList = payBillDAOImpl.getTotalPostNew1(orgDesignationId, billNo,locId,false,group,givenStrDate,month,year);
						}
						if(nonVacantList.size() == 0)
						{
							nonVacantList = payBillDAOImpl.getTotalPostNew(orgDesignationId, billNo,locId,true,group,givenStrDate);
							//nonVacantList = payBillDAOImpl.getTotalPostNew1(orgDesignationId, billNo,locId,true,group,givenStrDate,month,year);
						}*/

						if(checkList.contains(postGroupString))
						{
							//commented by vaibhav tyagi for inner after detach
							//nonVacantList = payBillDAOImpl.getTotalPostNew(orgDesignationId, billNo,locId,true,group,givenStrDate);

							//added by vaibhav tyagi for inner after detach:start
							nonVacantList = payBillDAOImpl.getTotalPostNew1(orgDesignationId, billNo, locId, true, group, givenStrDate1,month, year);
							//added by vaibhav tyagi for inner after detach:end
						}
						else
						{
							//commented by vaibhav tyagi for inner after detach
							//nonVacantList = payBillDAOImpl.getTotalPostNew(orgDesignationId, billNo,locId,false,group,givenStrDate);

							//added by vaibhav tyagi for inner after detach:start
							nonVacantList = payBillDAOImpl.getTotalPostNew1(orgDesignationId, billNo, locId, false, group, givenStrDate1,month, year);
							//added by vaibhav tyagi for inner after detach:end
						}
						if(nonVacantList.size() == 0)
						{
							//commented by vaibhav tyagi for inner after detach
							//nonVacantList = payBillDAOImpl.getTotalPostNew(orgDesignationId, billNo,locId,true,group,givenStrDate);

							//added by vaibhav tyagi for inner after detach:start
							logger.info("Inside the nonvacantList.size=0");
							nonVacantList = payBillDAOImpl.getTotalPostNew1(orgDesignationId, billNo, locId, true, group, givenStrDate1,month, year);
							logger.info("after the  the nonvacantList.size=0");
							
							//added by vaibhav tyagi for inner after detach:end
						}

						checkList.add(postGroupString);

						logger.info("nonVacantList" + nonVacantList.size()+ "tempVacant  is  manis   "+ nonVacantList.size());
						logger.info("Before executing the get vacant post");
						logger.info("nonVacantList"+nonVacantList);
						logger.info("month"+month);
						logger.info("year"+year);
						logger.info("locId"+locId);
						logger.info("paybillVoList.get(p).getPaybillGrpId()"+paybillVoList.get(p).getPaybillGrpId());
						if(nonVacantList.size()>0) {
						tempVacant = payBillDAOImpl.getVacantPost(nonVacantList, month, year, locId,paybillVoList.get(p).getPaybillGrpId());
						}else {
							tempVacant=0;
						}
						logger.info("after executing the get vacant post");

						//tempVacant=0;
						logger.info("vacant count is " + tempVacant);

						logger.info("size of nonVacantList is "	+ nonVacantList.size()+"tempVacant  is " + tempVacant);

						StringBuffer sb = new StringBuffer();
						sb.append(nonVacantList.size());
						sb.append(",");
						sb.append(tempVacant);
						sb.append(",");
						sb.append(dsgnName);


						Map desigEmp = new HashMap();
						desigEmp.put("filledPosts",nonVacantList.size());
						desigEmp.put("vacantPosts",tempVacant);
						desigEmp.put("designation",dsgnName);

						if(paybillVoList.get(p).getHrEisEmpMst() == null && (nonVacantList.size() != tempVacant) && p!=paybillVoList.size()-1)
						{
							empDesigMap.put(paybillVoList.get(p+1).getOrgPostMst().getPostId(),desigEmp);

						}
						else
						{
							empDesigMap.put(postId, desigEmp);

						}



						if ((nonVacantList.size() == tempVacant)) {
							if(cmnLookupMst.getLookupName().contains("Temp"))
								tmpCounter++;
							else
								prmCounter++;
							tempVacant = 0;

						}
						
						
						tempVar = nonVacantList.size() - tempVacant;
						total += nonVacantList.size() + tempVacant;
						logger.info("temp var is " + tempVar + " string is "
								+ sb.toString());
						if (tempVar != 0)
							tempVar -= 1;
					} else {
						tempVar -= 1;
						// elseCOunter+=1;

					}
				} else {
					tempVacant -= 1;
				}

			}
			logger.info("temp Map List is " + tempMap+"total value is " + total+" empDesigMap is " + empDesigMap + " size is "+ empDesigMap.size());
			logger.info("size of paybillVoList is " + paybillVoList.size());
			// manish ended


			int countVacant = payDao.countVacantData(locId, billNo, month, year);
			logger.info("value of countVacant" + countVacant);

			logger.info("vaccent posts are "+ countVacant);
			logger.info("edp List size is"+allowEdpList.size()+" deduc Ag "+deducAgEdpList.size()+" deduc Ty "+deducTyEdpList.size());


			// ///display logic starts here
			int totalnetcount = 0;
			int cntFirst = 1;
			int cntLast = 1;
			int dataCount=0;
			int permNullColumn=0;
			int perPostInRow=0;
			logger.info("prmCoubnter is "+prmCounter);
			logger.info("tmpCoubnter is "+tmpCounter);
		
			permCounter+=prmCounter;
			if(tempPostFlag==true && permCounter%displayPostsInRow > 0)
				dataCount = paybillVoList.size() - countVacant +Integer.parseInt(new Long(prmCounter).toString()) + (displayPostsInRow- Integer.parseInt(new Long(permCounter%displayPostsInRow).toString()));
			else
				dataCount = paybillVoList.size() - countVacant +Integer.parseInt(new Long(prmCounter).toString());
			dataCount+=tmpCounter;
			int pageCount = dataCount / (displayPostsInRow);
			if (dataCount % displayPostsInRow > 0) {
				pageCount++;
			}
			if(permCounter%displayPostsInRow > 0)
				permNullColumn=(displayPostsInRow- Integer.parseInt(new Long(permCounter%displayPostsInRow).toString()));

			perPostInRow = displayPostsInRow-permNullColumn;

			logger.info("permCounter is "+permCounter+"pageCount is "+pageCount);

			int postCount = 0;
			ArrayList row = null;
			ArrayList rowForPost = null;
			List innerData;
			List innerRow;
			TabularData td;

			double endReportTotal = 0, pageTotal = 0;
			double onScreenTotal = 0;


			int tempPostCount=0;
			int pageNumberToBeDisplayed=0;
			int tempStartFlag= 0;
			int ga=0;
			int gaCount=0;
			int tempHeaderStartFlag = 0;

			//started by manish


			boolean hbaLandPrin=false;
			boolean hbaHousePrin=false;
			int rempveObjectPrin=0;
			String edpCodeForHbaLandPri="5051";

			for(int i=0;i<loanList.size();i++)
			{
				if(loanList.get(i).getTypeId().equals(hbaLandPrinId))
				{
					hbaLandPrin=true;
					rempveObjectPrin=i;
					logger.info("going to remove object with index value from loanList"+i);
				}
				if(loanList.get(i).getTypeId().equals(hbaHousePrinId))
					hbaHousePrin=true;

			}
			if(hbaHousePrin==true && hbaLandPrin == true)
			{
				loanList.remove(rempveObjectPrin);
			}
			logger.info("size of loanList after removing hbaLandObject is "+loanList.size());
			boolean hbaLandInt=false;
			boolean hbaHouseInt=false;
			int rempveObjectInt=0;
			String edpCodeForHbaLandInt="5051";

			for(int i=0;i<loanListNew.size();i++)
			{
				if(loanListNew.get(i).getTypeId().equals(hbaLandIntId))
				{
					hbaLandInt=true;
					rempveObjectInt=i;
					logger.info("going to remove object with index value"+i);
				}
				if(loanListNew.get(i).getTypeId().equals(hbaHouseIntId))
					hbaHouseInt=true;

			}
			if(hbaLandInt==true && hbaHouseInt == true)
			{
				loanListNew.remove(rempveObjectInt);
			}
			logger.info("size of loanListNew after removing hbaLandObject is "+loanListNew.size());
			//ended by manish 

			//pageBreak:
			for (int page = 0; page < pageCount; page++) {

				/*if(permCounter==(page+1)*)*/

				//added by manish

				logger.info(" check this "+ permCounter/displayPostsInRow);



				ArrayList headerRow= new ArrayList();
				StyleVO[] headerStyleVo = new StyleVO[2];


				headerStyleVo[0] = new StyleVO();
				headerStyleVo[0].setStyleId(IReportConstants.BORDER);
				headerStyleVo[0].setStyleValue("NO");
				headerStyleVo[1] = new StyleVO();
				headerStyleVo[1]
				              .setStyleId(IReportConstants.BORDER_RIGHT);
				headerStyleVo[1].setStyleValue("NO");

				StyledData dataStyle = new StyledData();
				dataStyle.setStyles(headerStyleVo);
				dataStyle.setColspan(11);
				if(((page > (permCounter/displayPostsInRow) && ( (permCounter%displayPostsInRow) != 0) ) )
						|| (page == 0 && permCounter==0) || ((permCounter%displayPostsInRow == 0) &&  page == (permCounter/displayPostsInRow) )) 
				{
					dataStyle.setData("Bill Name : " + "" +"("+ + billNo	+ ") Detail Bill - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName );
					tempHeaderStartFlag = 1;
				
				}
				else
					if(tempHeaderStartFlag==0){
						dataStyle.setData("Bill Name : " + "" +"("+ + billNo	+ ") Detail Bill - PERMANENT POSTS- Bill for "+officeName+" -"+ lStrGrpName );
										
					}
				
					else 
						dataStyle.setData("Bill Name : " + "" +"("+ + billNo	+ ") Detail Bill - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName );

				headerRow.add(dataStyle);



				//ended by manish

				//HrPayPaybill pageHrPaybill = new HrPayPaybill();

				// /logic for post display

				ArrayList orderdataList = new ArrayList();
				StyleVO[] centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0]
				                  .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0]
				                  .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1]
				                  .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left");

				// started by manish

				rowForPost = new ArrayList();
				rowForPost.add("");
				rowForPost.add("SECTION OF ESTABLISH.");

				// ended by manish

				// for 2nd row name
				row = new ArrayList();

				//if(breakflag== false )
				row.add(" ");

				innerData = new ArrayList();
				innerRow = new ArrayList();

				innerRow.add("NAME");
				innerData.add(innerRow);

				innerRow = new ArrayList();
				innerRow.add("CODE");
				innerData.add(innerRow);


				innerRow = new ArrayList();
				innerRow.add("&nbsp;");
				innerData.add(innerRow);

				innerRow = new ArrayList();
				innerRow.add("PayInPB+GP");
				innerData.add(innerRow);

				innerRow = new ArrayList();
				innerRow.add("BASIC PAY");
				innerData.add(innerRow);

				td = new TabularData(innerData);
				td.setStyles(nameVo);// initialize tabular data
				// td.addStyle(IReportConstants.STYLE_FONT_FAMILY,
				// IReportConstants.VALUE_FONT_FAMLIY_TIMES);
				// td.addStyle(IReportConstants.BACKGROUNDCOLOR,
				// IReportConstants.VALUE_FONT_COLOR_YELLOW);

				//if(breakflag== false )
				row.add(td);

				int postsInRow = 0;
				int nullColumns = 0;


				logger.info("data count "+dataCount );	
				if (dataCount % displayPostsInRow > 0) {
					// logger.info("in if 1");
					// logger.info("page count "+page+" total pageCount"+(pageCount-1));
					if (page < (pageCount - 1)) {
						postsInRow = displayPostsInRow;
						nullColumns = 0;
					} else {
						postsInRow = dataCount % displayPostsInRow;
						nullColumns = displayPostsInRow - postsInRow;

					}
				} else { 
					postsInRow = displayPostsInRow;
					nullColumns = 0;
				}

				if(permNullColumn>0 && (permCounter/displayPostsInRow)==page)
				{
					nullColumns=permNullColumn;
					postsInRow = perPostInRow;
				}

				colorPostInRow+=postsInRow;
				// for row 2--name

				HrPayPaybill paybillVo = null;

				// logger.info("for page "+page+" postsInRow "+postsInRow);
				int rollBackCount = postCount;
				// code for single page row 2 print

				// String postDesigString="";

				boolean[] postFlag = new boolean[9];
				for (int o = 0; o < 9; o++) {
					postFlag[o] = false;
				}


				StyleVO[] postStyleVo=new StyleVO[2];
				postCount = tempPostCount;
				//added by kishan
				Double[] EmpNetTotal = new Double[postsInRow];
				//end
				HrEisScaleMst eisScaleMst = null;
				for (int i = 0; i < postsInRow; i++, postCount++) {

					logger.info("post count is "+postCount);
					paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
					if(paybillVo.getHrEisEmpMst()!= null)

						innerData = new ArrayList();
					innerRow = new ArrayList();
					String fname = "", mname = "", lname = "";
					// started by manish
					Map displayMap = null;

					if(empDesigMap.get(paybillVo.getOrgPostMst()
							.getPostId()) != null){
						displayMap =(HashMap)empDesigMap.get(paybillVo.getOrgPostMst().getPostId());
					}

					if (paybillVo.getHrEisEmpMst() != null

							|| displayMap!= null && displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString()))
					{
						if (displayMap!= null &&  displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString())) {

						}else {

							EmpNetTotal[i] = paybillVo.getNetTotal();
							OrgEmpMst orgEmpMst =  paybillVo.getHrEisEmpMst().getOrgEmpMst();
							if(paybillVo.getScaleId() != 0)
								eisScaleMst = scaleMasterDAOImpl.read(paybillVo.getScaleId());
							else
								eisScaleMst = paybillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisScaleMst();
							HrEisEmpMst eisEmpMst = paybillVo.getHrEisEmpMst();

							if (orgEmpMst.getEmpFname().trim().length() > 0) {
								fname = ""
									+ orgEmpMst.getEmpFname()
									.trim().charAt(0);
							}
							if (orgEmpMst.getEmpLname().trim().length() > 0) {
								lname = ""
									+ orgEmpMst.getEmpLname()
									.trim();
							}
							if (orgEmpMst.getEmpMname() != null) {
								if (orgEmpMst.getEmpMname().trim().length() > 0) {
									mname = ""
										+ orgEmpMst.getEmpMname().trim()
										.charAt(0);
								}
							}

							//commented for * mark in paybill
							/*innerRow.add(fname + ". " +mname+". "+ lname);
							innerData.add(innerRow);*/
							
							//added for * mark in paybill :start
							
							String percentageOfBasic =  "100";
							
							if(percentageOfBasic!=null && Long.parseLong(percentageOfBasic)<100){
								innerRow.add(fname + ". " +mname+". "+ lname+"*");
								innerData.add(innerRow);
							}

							else{
								innerRow.add(fname + ". " +mname+". "+ lname );
								innerData.add(innerRow);
							}
							//added for * mark in paybill :end
							String s=null; // Added by Tejashree
							innerRow = new ArrayList();
							if(eisEmpMst.getSevarthEmpCode()!= null && eisEmpMst.getSevarthEmpCode()!= "")
							{
								innerRow.add("["+eisEmpMst.getSevarthEmpCode()+"]");
								s=eisEmpMst.getSevarthEmpCode();// Added by Tejashree
							}
							else 
							{
								innerRow.add("");
							}
							innerData.add(innerRow);

							innerRow = new ArrayList();
							//innerRow.add("[");
							//Added by Mani for Seven Pc
							Long basicForSvnPC = Long.valueOf(0L);
				             LedgerReportDAOImpl dao = new LedgerReportDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				             basicForSvnPC = Long.valueOf(paybillVo.getHrEisOtherDtls().getOtherCurrentSevenBasic());
				             logger.info("month=" + month);
				             logger.info("year=" + year);
				             logger.info("basicForSvnPC in inner report:" + basicForSvnPC);
				             if ((basicForSvnPC.longValue() > 0) && (((month >= 10) && (year == 2016)) || (year >= 2017))) {
				               //innerRow.add("-");
				            	 innerRow.add("7 PayComission");// Added by Tejashree
				             } else {
							innerRow.add("["+eisScaleMst.getScaleStartAmt()
									+ "-"
									
									+ eisScaleMst.getScaleEndAmt()+"]");
				             }
							//innerRow.add("]");
							innerData.add(innerRow);

							innerRow = new ArrayList();
							  if ((basicForSvnPC.longValue() > 0) && (((month >= 10) && (year == 2016)) || (year >= 2017))) {
					               //innerRow.add("-");
					               List pl=payBillDAOImpl.get7pclevel(s);  // Added by Tejashree
									innerRow.add("Level- "+pl.get(0));// Added by Tejashree
					             } else {
							innerRow.add((new Double(paybillVo.getBasic0101()).longValue())
									- eisScaleMst.getScaleGradePay()
									+ " + "
									+ eisScaleMst.getScaleGradePay());
					             }
							innerData.add(innerRow);

							innerRow = new ArrayList();
							innerRow.add((new Double(paybillVo.getBasic0101()).longValue()));
							innerData.add(innerRow);

							td = new TabularData(innerData);
							td.setStyles(nameVo);

							row.add(td);
						}
						if (displayMap!= null) {
							// String postDesigString =
							// empDesigMap.get(paybillVo.getHrEisEmpMst().getEmpId()).toString();
							// String postDesigString =
							// empDesigMap.get(paybillVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId()).toString();
							/*postDesigString = empDesigMap.get(
									paybillVo.getOrgPostMst().getPostId())
									.toString();
							 */
							/*logger.info("postDesig Stering is "
									+ postDesigString + " for post id "
									+ paybillVo.getOrgPostMst().getPostId());*/
							/*	StringTokenizer st = new StringTokenizer(
									postDesigString, ",");
							int l = 0;
							long totalPost = 0;
							long nonVacantPost = 0;
							String designation = "";

							while (st.hasMoreTokens()) {
								if (l == 0)
									totalPost = Long.parseLong(st.nextToken());
								else if (l == 1)
									nonVacantPost = Long.parseLong(st
											.nextToken());
								else if (l == 2)
									designation = st.nextToken();

								l++;
							}
							 */							
							String firstLine = Long.valueOf(displayMap.get("filledPosts").toString())+ " Post ";
							if (Long.valueOf(displayMap.get("vacantPosts").toString()) != 0)
								firstLine += Long.valueOf(displayMap.get("vacantPosts").toString()) + " Vacant ";
							firstLine += "of ";

							if ( displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString())) {
								postFlag[i + 2] = true;
								row.add("");
								//manish 

								postStyleVo = new StyleVO[2];
								postStyleVo[0] = new StyleVO();
								postStyleVo[0]
								            .setStyleId(IReportConstants.BORDER_LEFT);
								postStyleVo[0]
								            .setStyleValue("YES");
								postStyleVo[1] = new StyleVO();
								postStyleVo[1]
								            .setStyleId(IReportConstants.BORDER_RIGHT);
								postStyleVo[1].setStyleValue("NO");
								dataStyle = new StyledData();
								dataStyle.setStyles(postStyleVo);
								dataStyle.setColspan(1);
								dataStyle.setData(Long.valueOf(displayMap.get("filledPosts").toString())+ " Post "+Long.valueOf(displayMap.get("vacantPosts").toString())+" Vacant of "
										+ displayMap.get("designation").toString());
								rowForPost.add(dataStyle);

								//manish
								// flag=true;
							} else {
								//rowForPost.add(firstLine + designation);
								//manish 

								postStyleVo = new StyleVO[2];
								postStyleVo[0] = new StyleVO();
								postStyleVo[0]
								            .setStyleId(IReportConstants.BORDER_LEFT);
								postStyleVo[0]
								            .setStyleValue("YES");
								postStyleVo[1] = new StyleVO();
								postStyleVo[1]
								            .setStyleId(IReportConstants.BORDER_RIGHT);
								postStyleVo[1].setStyleValue("NO");
								dataStyle = new StyledData();
								dataStyle.setStyles(postStyleVo);
								dataStyle.setColspan(1);
								dataStyle.setData(firstLine + displayMap.get("designation").toString());
								rowForPost.add(dataStyle);

								//manish

							}

						} else {

							//rowForPost.add("&nbsp;");
							postStyleVo = new StyleVO[2];
							postStyleVo[0] = new StyleVO();
							postStyleVo[0]
							            .setStyleId(IReportConstants.BORDER_LEFT);
							postStyleVo[0]
							            .setStyleValue("NO");
							postStyleVo[1] = new StyleVO();
							postStyleVo[1]
							            .setStyleId(IReportConstants.BORDER_RIGHT);
							postStyleVo[1].setStyleValue("NO");
							dataStyle = new StyledData();
							dataStyle.setStyles(postStyleVo);
							dataStyle.setColspan(1);
							dataStyle.setData("&nbsp;");
							rowForPost.add(dataStyle);

						}

					} else {
						i--;
					}
					// }

				}

				tempPostCount=postCount;
				/*if(breakflag==true)
					break;
				 */

				innerData = new ArrayList();
				innerRow = new ArrayList();

				if( ((permCounter % displayPostsInRow ==0) && (page ==(permCounter / displayPostsInRow))) || ( (permCounter % displayPostsInRow != 0) && (page == ((permCounter / displayPostsInRow) + 1))))
				{
					pageNumberToBeDisplayed = 0;
					tempStartFlag = 1;
				}

				if(tempStartFlag != 1)
				{
					pageNumberToBeDisplayed = page + 1;
					innerRow.add("PAGE " + pageNumberToBeDisplayed);
				}
				else
				{
					pageNumberToBeDisplayed = pageNumberToBeDisplayed + 1;
					innerRow.add("PAGE " + pageNumberToBeDisplayed);

				}
				innerData.add(innerRow);
				innerRow = new ArrayList();
				innerRow.add("Total");
				innerData.add(innerRow);
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				row.add(td);

				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
					row.add("");
					rowForPost.add("");
				}

				row.add(" ");
				/*
				 * logger.info("rao is "+row );
				 * logger.info("manish -3 size is "+row.size());
				 */
				rowForPost.add("");
				rowForPost.add("");
				logger.info("length for rowForPost is  " + rowForPost.size());
				DataList.add(headerRow);
				DataList.add(rowForPost);
				DataList.add(row);

				// to display row 3 remarks

				row = new ArrayList();
				row.add("SL NO");
				row.add("REMARKS");
				for (int i = 0; i < postsInRow; i++) {
					row.add(" ");// //add remark information

				}

				row.add(" ");
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
					row.add(" ");
				}
				row.add("SL NO");
				logger.info("manish -2 length " + row.size());

				DataList.add(row);

				// 

				// for row 4

				row = new ArrayList();
				// row.add(cntFirst++);
				double emptotAllow[] = new double[postsInRow + 1];
				double totalAllow[] = new double[allowEdpList.size() + 1];
				postCount = rollBackCount;

				// logger.info("postId String is "+postDesigString);

				for (int i = 0; i < postsInRow + 3; i++) {

					long postId= paybillVo.getOrgPostMst().getPostId();
					if(empDesigMap.get(postId) != null){
						/*
					Long.valueOf(displayMap.get("filledPosts").toString());
					Long.valueOf(displayMap.get("vacantPosts").toString());
					displayMap.get("designation").toString();*/
					}

					/*
					 * if(postFlag[i]==true) { row.add(0); } else {
					 */
					// manish started
					if (i == 0) {
						innerData = new ArrayList();

						for (int k = 0; k <= allowEdpList.size()+1; k++) {
							innerRow = new ArrayList();
							innerRow.add(cntFirst++);
							innerData.add(innerRow);
						}
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
					}

					// manish ended
					else if (i == 1) {

						innerData = new ArrayList();

						innerRow = new ArrayList();
						innerRow.add("OFFICI. PAY");
						innerData.add(innerRow);	


						innerRow = new ArrayList();
						innerRow.add("LEAVE SAL.");
						innerData.add(innerRow);

						for (int k = 0; k < allowEdpList.size(); k++) {
							innerRow = new ArrayList();

							innerRow.add(allwEdpNameMap.get(String.valueOf(allowEdpList.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							innerData.add(innerRow);
						}
						// innerRow = new ArrayList();
						// innerRow.add("Total Allow");
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						// td.setStyles(numberDispalyVO);
						row.add(td);
					} else if (i == postsInRow + 2)// total logic
					{
						double gt = 0;

						// row.add("a");
						innerData = new ArrayList();

						for (int p = 0; p < allowEdpList.size() + 1; p++) {

							if(p==1)
							{
								innerRow = new ArrayList();
								innerRow.add(0);
								innerData.add(innerRow);
							}
							innerRow = new ArrayList();
							innerRow.add(totalAllow[p]);

							innerData.add(innerRow);
							gt += totalAllow[p];
						}
						// innerRow = new ArrayList();
						// innerRow.add(gt);
						emptotAllow[postsInRow] = gt;
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						row.add(td);
						// row.add(cntLast++);

					}

					else {
						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);

						/*if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false)
						{
							break;
						}*/

						/*logger
								.info("paybillVo.getHrEisEmpMst()==null &&  postFlag[i]==true "
										+ paybillVo.getHrEisEmpMst()
										+ "  check flag" + postFlag[i]);*/

						if (paybillVo.getHrEisEmpMst() != null
						) {
							// logger.info("column no = "+(i-1)+" post count "+postCount);
							// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							innerData = new ArrayList();
							emptotAllow[i - 2] = paybillVo.getBasic0101();
							totalAllow[0] += paybillVo.getBasic0101();
							// logger.info("total is ::::: "+emptotAllow[i-1]);
							double empTotal = 0;
							innerRow = new ArrayList();
							innerRow.add(paybillVo.getBasic0101());
							innerData.add(innerRow);

							innerRow = new ArrayList();
							innerRow.add(0);
							innerData.add(innerRow);


							for (int p = 0; p < allowEdpList.size(); p++) {
								innerRow = new ArrayList();

								String method = "getAllow"
									+ allwEdpNameMap.get(String.valueOf(allowEdpList.get(p).getTypeId())).toString().split(",")[0];
								Class pay = paybillVo.getClass();
								Method payMthd = pay.getMethod(method, null);
								double value = (Double) payMthd.invoke(
										paybillVo, null);
//                               if(method.equals("getAllow0103") || method.equals("getAllow0110") || method.equals("Allow0110")){
//                            	   innerRow.add("--");
//								}else{
								innerRow.add(value);
//								}
								// logger.info("allowance value ::"+ value);
								
								innerData.add(innerRow);
								empTotal += value;
								// String str
								// ="total of allow"+allowEdpList.get(p).getRltBillTypeEdp().getEdpCode();

								totalAllow[p + 1] += value;
								// logger.info(str+" "+totalAllow[p]);
							}
							// innerRow = new ArrayList();

							// logger.info("emp allow total "+emptotAllow[i-1]);
							emptotAllow[i - 2] += empTotal;
							// logger.info("value is "+empTotal+" after addition ::"+emptotAllow[i-1]);
							// innerRow.add(emptotAllow[i-1]);
							// innerData.add(innerRow);
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
							// logger.info("postId String is "+postDesigString);
							/*
							 * if(postDesigString.contains("1,1")) { row.add(0);
							 * }
							 */

						}

						else if (paybillVo.getHrEisEmpMst() == null
								&& postFlag[i] == true) {

							innerData = new ArrayList();
							innerRow = new ArrayList();
							innerRow.add("");
							innerData.add(innerRow);
							for (int p = 0; p < allowEdpList.size(); p++) {
								innerRow = new ArrayList();
								innerRow.add("");
								innerData.add(innerRow);

							}
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							i--;
						}
						postCount++;
					}
					// }

				}

				logger.info("nulcoli=umn is " + nullColumns);
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
					row.add(" ");

				}
				// logger.info("length of row is "+row.size());

				// manish
				innerData = new ArrayList();

				for (int k = 0; k <= allowEdpList.size(); k++) {
					innerRow = new ArrayList();
					innerRow.add(cntLast++);
					innerData.add(innerRow);
				}
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				row.add(td);
				// ended
				logger.info("manish -1 length " + row.size());
				DataList.add(row);

				// ///new line added to display total allowance

				row = new ArrayList();
				row.add(cntFirst++);
				row.add("TOTAL");
				for (int i = 0; i < postsInRow + 1; i++) {
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(emptotAllow[i]);
					if (emptotAllow[i] == 0) {
						row.add("");
					} else
						row.add(dataStyle1);

					// row.add(emptotAllow[i]);
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				/*
				 * logger.info("manish length is "+row.size());
				 * 
				 * logger.info("dataList before adding allowance is "+DataList);
				 */
				DataList.add(row);
				// logger.info("dataList after adding allowance is "+DataList);

				// new Line added to display Gross Sal 


				row = new ArrayList();
				row.add(cntFirst++);
				row.add("GROSS SAL.");
				for (int i = 0; i < postsInRow + 1; i++) {
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(emptotAllow[i]);
					if (emptotAllow[i] == 0) {
						row.add("");
					} else
						row.add(dataStyle1);

					// row.add(emptotAllow[i]);
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				/*
				 * logger.info("manish length is "+row.size());
				 * 
				 * logger.info("dataList before adding allowance is "+DataList);
				 */
				DataList.add(row);

				//ended  gross sal line

				// //for row 5
				// /advances logic lies here

				/*
				 * row = new ArrayList(); row.add(cntFirst++); row.add("FA");
				 * for(int i=0;i<postsInRow+1;i++) { StyledData dataStyle1 = new
				 * StyledData(); dataStyle1.setStyles(boldStyleVO);
				 * dataStyle1.setData(paybillVo.getAdv5059());
				 * row.add(dataStyle1);
				 * 
				 * //started manish
				 * pageHrPaybill.setAdv5059(paybillVo.getAdv5059()); //ended
				 * manish
				 * 
				 * // row.add(paybillVo.getAdv5059()+paybillVo.getAdvInt5059());
				 * } for(int nulcol=0;nulcol<nullColumns;nulcol++) {
				 * 
				 * row.add(" "); } //
				 * logger.info("length of row is "+row.size());
				 * row.add(cntLast++); //
				 * logger.info("manish 2 size is "+row.size());
				 * DataList.add(row);
				 */

				row = new ArrayList();
				double emptotGrossLoan[] = new double[postsInRow + 1];
				double totalGrossLoan[] = new double[loanListGross.size()];
				postCount = rollBackCount;
				for (int i = 0; i < postsInRow + 3; i++) {

					long postId= paybillVo.getOrgPostMst().getPostId();

					if(empDesigMap.get(postId) != null){
					}


					if (i == 0) {
						innerData = new ArrayList();

						for (int k = 0; k < loanListGross.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(cntFirst++);
							innerData.add(innerRow);
						}
						if (innerData.size() > 0) {
							td = new TabularData(innerData);
							td.setStyles(nameVo);
							row.add(td);
						} else {
							row.add("");
						}
					}

					// manish ended

					else if (i == 1) {
						innerData = new ArrayList();
						for (int k = 0; k < loanListGross.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(loanEdpNameMap.get(String.valueOf(loanListGross.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							innerData.add(innerRow);
						}
						// innerRow = new ArrayList();
						// innerRow.add("Total AG");
						// innerData.add(innerRow);
						if (innerData.size() > 0) {
							td = new TabularData(innerData);
							td.setStyles(nameVo); // td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							row.add(" ");
						}
					} else if (i == postsInRow + 2)// total logic
					{
						double gt = 0;

						// row.add("a");
						innerData = new ArrayList();
						for (int p = 0; p < loanListGross.size(); p++) {
							innerRow = new ArrayList();
							innerRow.add(totalGrossLoan[p]);
							// started manish

							// ended manish

							innerData.add(innerRow);
							gt += totalGrossLoan[p];
						}
						// innerRow = new ArrayList();
						// innerRow.add(gt);
						emptotGrossLoan[postsInRow] = gt;
						// innerData.add(innerRow);
						if (innerData.size() > 0) {
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							row.add(" ");
						}

					}

					else {

						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
						/*if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false)
						{
							break;
						}*/
						if (paybillVo.getHrEisEmpMst() != null
						) {
							// logger.info("column no = "+(i-1)+" post count "+postCount);
							// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							innerData = new ArrayList();
							double empTotal = 0;
							for (int p = 0; p < loanListGross.size(); p++) {
								innerRow = new ArrayList();
								HrPayEdpCompoMpg edpCompoMpg = loanListGross.get(p);
								String method = "";
								if (edpCompoMpg.getCmnLookupId() == 2500137) {
									method = "getLoan"
										+ loanEdpNameMap.get(String.valueOf(edpCompoMpg.getTypeId())).toString().split(",")[0];
								} else {
									method = "getAdv"
										+ loanEdpNameMap.get(String.valueOf(edpCompoMpg.getTypeId())).toString().split(",")[0];
								}
								Class pay = paybillVo.getClass();
								Method payMthd = pay.getMethod(method, null);

								double value = (Double) payMthd.invoke(
										paybillVo, null);
								/*	if (edpCompoMpg.getCmnLookupId() == 2500137) {
									method = "getLoanInt"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
									pay = paybillVo.getClass();
									payMthd = pay.getMethod(method, null);
									value += (Double) payMthd.invoke(paybillVo,
											null);
								}*/
								if (value > 0) {
									HrPayPaybillLoanDtls hrPayPaybillLoanDtls = payBillDAOImpl
									.getLoanDtlsFromPaybill(paybillVo
											.getId(), edpCompoMpg
											.getTypeId());
									if (hrPayPaybillLoanDtls != null)
										innerRow.add(hrPayPaybillLoanDtls
												.getRecoveredInst()
												+ "/"
												+ hrPayPaybillLoanDtls
												.getTotalInst()
												+ " "
												+ Math.round(value));
									else {
										/*logger
												.info("hrpaypaybillloandtls object null for "
														+ paybillVo.getId()
														+ " loan id "
														+ edpCompoMpg
																.getTypeId());*/
										innerRow.add(Math.round(value));
									}
								} else {
									innerRow.add(Math.round(value));
								}
								// logger.info("allowance value ::"+ value);
								innerData.add(innerRow);
								empTotal += value;

								totalGrossLoan[p] += value;

							}
							// innerRow = new ArrayList();
							// innerRow.add(empTotal);
							emptotGrossLoan[i - 2] = empTotal;
							// innerData.add(innerRow);


							if (innerData.size() > 0) {
								td = new TabularData(innerData);
								td.setStyles(numberDispalyVO);
								row.add(td);

							} else
							{
								row.add("");
								logger.info("Inner else  "+innerData.size());
							}

						} else if (paybillVo.getHrEisEmpMst() == null
								&& postFlag[i] == true) {

							innerData = new ArrayList();
							/*
							 * innerRow = new ArrayList(); innerRow.add("");
							 * innerData.add(innerRow);
							 */
							for (int p = 0; p < loanListGross.size(); p++) {
								innerRow = new ArrayList();
								innerRow.add("");
								innerData.add(innerRow);

							}
							if(loanListGross.size()>0)
							{td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
							}
							else
							{
								row.add("");
							}
						} else {
							i--;
						}
						postCount++;
					}

					// }
				}

				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());

				// manish started

				innerData = new ArrayList();

				for (int k = 0; k < loanListGross.size(); k++) {
					innerRow = new ArrayList();
					innerRow.add(cntLast++);
					innerData.add(innerRow);
				}
				if (innerData.size() > 0) {
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				} else {
					row.add(" ");
				}

				// manish ended

				/*
				 * logger.info("manish 4 size is"+row.size());
				 * logger.info("datalist  before  manish 4" +DataList);
				 */
				DataList.add(row);

				// /// before Gross Logic Ends

				// ///gross Logic

				row = new ArrayList();
				row.add(cntFirst++);
				row.add("GROSS TOT");
				for (int i = 0; i < postsInRow + 1; i++) {
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(emptotAllow[i] - (emptotGrossLoan[i]));

					if (emptotAllow[i] - (emptotGrossLoan[i]) == 0)
						row.add("");
					else
						row.add(dataStyle1);
					// row.add(emptotAllow[i]-(paybillVo.getAdv5059()+paybillVo.getAdvInt5059()));
					emptotAllow[i] = emptotAllow[i] - (emptotGrossLoan[i]);
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				// logger.info("Manish 3 size is "+row.size());
				DataList.add(row);

				// for row 6 For Audit office |S|L|O|
				/* ArrayList */orderdataList = new ArrayList();
				/* StyleVO[] */centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0]
				                  .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0]
				                  .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1]
				                  .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left");
				dataStyle = new StyledData();
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setColspan(displayPostsInRow + 4);
				dataStyle.setData("FOR AUDIT OFFICE |S|L|O| ");
				orderdataList.add(dataStyle);
				DataList.add(orderdataList);

				// row 7 GPF AC No
				row = new ArrayList();
				row.add(cntFirst++);
				row.add("GPF/DCPS A.C.Number");

				ArrayList secondRow = new ArrayList();
				secondRow.add(cntFirst++);
				secondRow.add("");


				if(dataCount%displayPostsInRow!=0 && page==dataCount/displayPostsInRow)
					ga=postsInRow;
				else
					ga=displayPostsInRow;
				if(page==permCounter/displayPostsInRow)
					ga=postsInRow;

				for(int k=0;k<ga;k++)
				{

					HrPayPaybill hrPayPaybill = paybillVoList.get(gaCount);
					Map displayMap = null;
					long postId= hrPayPaybill.getOrgPostMst().getPostId();

					if(empDesigMap.get(postId) != null){

						displayMap =(HashMap)empDesigMap.get(postId);

						Long.valueOf(displayMap.get("filledPosts").toString());
						Long.valueOf(displayMap.get("vacantPosts").toString());
						displayMap.get("designation").toString();
					}

					if(displayMap != null && displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString())){

						row.add("");
						secondRow.add("");
					}
					else if(hrPayPaybill.getHrEisEmpMst() == null){
						gaCount++;
						k--;
						if(gaCount!=paybillVoList.size())
							continue;
						else
							break;

					}
					else if (hrPayPaybill.getHrEisEmpMst() != null
					) {
						long userId = hrPayPaybill.getHrEisEmpMst()
						.getOrgEmpMst().getOrgUserMst().getUserId();
						genDao = new GenericDaoHibernateImpl(
								HrPayGpfBalanceDtls.class);
						genDao.setSessionFactory(serv.getSessionFactory());
						HrPayGpfBalanceDtls gpfDtl = (HrPayGpfBalanceDtls) genDao
						.read(userId);
						/*	if (gpfDtl != null) {
							if(gpfDtl.getGpfAccNo().contains("null"))
							{	String gpfNew =gpfDtl.getGpfAccNo().replace("null", "");
								row.add(gpfNew);
								secondRow.add("");
							}
							else
							{
								row.add(gpfDtl.getGpfAccNo());
								secondRow.add("");
							}
						} */
						if (gpfDtl != null) {
							if(gpfDtl.getGpfAccNo()!= null && gpfDtl.getPfSeries()!= null)
							{
								String gpfAccNo =gpfDtl.getGpfAccNo().replace("null", "");
								String pfSeries =gpfDtl.getPfSeries().replace("null", "");
								row.add(pfSeries+"/"+gpfAccNo);
								secondRow.add("");

							}
							else
							{
								if(gpfDtl.getPfSeries()==null)
								{		
									row.add(gpfDtl.getGpfAccNo());
									secondRow.add("");
								}
								else
								{
									if(!gpfDtl.getPfSeries().contains("DCPS"))
									{
										row.add(gpfDtl.getPfSeries()+"/"+gpfDtl.getGpfAccNo());
										secondRow.add("");

									}

									else
									{
										if (gpfDtl.getGpfAccNo().length() > 4)
										{
											innerData = new ArrayList();

											innerRow = new ArrayList();
											innerRow.add(gpfDtl.getGpfAccNo().subSequence(0, gpfDtl.getGpfAccNo().length() - 5));
											innerData.add(innerRow);

											innerRow = new ArrayList();
											innerRow.add(gpfDtl.getGpfAccNo().substring(gpfDtl.getGpfAccNo().length() - 5, gpfDtl.getGpfAccNo().length()));
											innerData.add(innerRow);

											td = new TabularData(innerData);
											td.setStyles(nameVo);
											//row.add(gpfDtl.getPfSeries()+"/"+gpfDtl.getGpfAccNo());
											row.add(gpfDtl.getGpfAccNo().subSequence(0, gpfDtl.getGpfAccNo().length() - 5));
											//row.add(gpfDtl.getGpfAccNo().subSequence(0, gpfDtl.getGpfAccNo().length()-5));
											secondRow.add(gpfDtl.getGpfAccNo().substring(gpfDtl.getGpfAccNo().length() - 5, gpfDtl.getGpfAccNo().length()));
										}
										else
										{
											row.add("" + gpfDtl.getGpfAccNo());
											secondRow.add("");
										}


									}
								}
							} 
						}


					}
					else{


						row.add("");
						secondRow.add("");


					}

					gaCount++;
				}


				row.add(" ");
				secondRow.add(" ");		




				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
					secondRow.add(" ");
				}



				row.add(cntLast++);
				secondRow.add(cntLast++);

				// logger.info("manish 4 size is"+row.size());
				DataList.add(row);
				DataList.add(secondRow);
				// ///for deduction by Ag
				orderdataList = new ArrayList();
				centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0]
				                  .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0]
				                  .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1]
				                  .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left");
				dataStyle = new StyledData();
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setColspan(displayPostsInRow + 4);
				dataStyle.setData("DEDUCTIONS ADJUSTABLE BY AG");
				orderdataList.add(dataStyle);
				DataList.add(orderdataList);

				// /for row 9
				row = new ArrayList();
				double emptotDeducAg[] = new double[postsInRow + 1];
				double totalDeducAg[] = new double[deducAgEdpList.size()];
				postCount = rollBackCount;
				for (int i = 0; i < postsInRow + 3; i++) {
					/*
					 * if(postFlag[i]==true) { row.add(0); } else {
					 */
					// manish started
					if (i == 0) {
						innerData = new ArrayList();

						for (int k = 0; k < deducAgEdpList.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(cntFirst++);
							innerData.add(innerRow);
						}
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
					}

					// manish ended

					else if (i == 1) {
						innerData = new ArrayList();
						for (int k = 0; k < deducAgEdpList.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(deducEdpNameMap.get(String.valueOf(deducAgEdpList.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							innerData.add(innerRow);
						}
						// innerRow = new ArrayList();
						// innerRow.add("Total AG");
						// innerData.add(innerRow);
						if (innerData.size() > 0) {
							td = new TabularData(innerData);
							td.setStyles(nameVo); // td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							row.add(" ");
						}
					} else if (i == postsInRow + 2)// total logic
					{
						double gt = 0;

						// row.add("a");
						innerData = new ArrayList();
						for (int p = 0; p < deducAgEdpList.size(); p++) {
							innerRow = new ArrayList();
							innerRow.add(totalDeducAg[p]);

							innerData.add(innerRow);
							gt += totalDeducAg[p];
						}
						// innerRow = new ArrayList();
						// innerRow.add(gt);
						emptotDeducAg[postsInRow] = gt;
						// innerData.add(innerRow);
						if (innerData.size() > 0) {
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							row.add(" ");
						}

					}

					else {

						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);


						/*if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false)
						{
							break;
						}*/


						if (paybillVo.getHrEisEmpMst() != null
						) {
							// logger.info("column no = "+(i-1)+" post count "+postCount);
							// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							innerData = new ArrayList();
							double empTotal = 0;
							for (int p = 0; p < deducAgEdpList.size(); p++) {
								innerRow = new ArrayList();

								String method = "getDeduc"
									+deducEdpNameMap.get(String.valueOf(deducAgEdpList.get(p).getTypeId())).toString().split(",")[0];
								logger.info("deducEdpNameMap-------------"+deducEdpNameMap.get(String.valueOf(deducAgEdpList.get(p).getTypeId())).toString().split(",")[0]);
								Class pay = paybillVo.getClass();
								Method payMthd = pay.getMethod(method, null);
								double value = (Double) payMthd.invoke(
										paybillVo, null);
								innerRow.add(value);
								 logger.info("Deductionnn value ::"+ value);
								innerData.add(innerRow);
								empTotal += value;

								totalDeducAg[p] += value;

							}
							// innerRow = new ArrayList();
							// innerRow.add(empTotal);
							emptotDeducAg[i - 2] = empTotal;
							// innerData.add(innerRow);
							if (innerData.size() > 0) {
								td = new TabularData(innerData);
								td.setStyles(numberDispalyVO);
								row.add(td);
							} else {

								row.add(" ");
							}

						} else if (paybillVo.getHrEisEmpMst() == null
								&& postFlag[i] == true) {

							innerData = new ArrayList();
							/*
							 * innerRow = new ArrayList(); innerRow.add("");
							 * innerData.add(innerRow);
							 */
							for (int p = 0; p < deducAgEdpList.size(); p++) {
								innerRow = new ArrayList();
								innerRow.add("");
								innerData.add(innerRow);

							}
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							i--;
						}
						postCount++;
					}
					// }
				}

				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());

				// manish started

				innerData = new ArrayList();

				for (int k = 0; k < deducAgEdpList.size(); k++) {
					innerRow = new ArrayList();
					innerRow.add(cntLast++);
					innerData.add(innerRow);
				}
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				row.add(td);

				// manish ended

				/*
				 * logger.info("manish 4 size is"+row.size());
				 * logger.info("datalist  before  manish 4" +DataList);
				 */
				DataList.add(row);
				// logger.info("datalist  after  manish 4" +DataList);

				// //Loans List

				row = new ArrayList();
				double emptotLoan[] = new double[postsInRow + 1];
				double totalLoan[] = new double[loanList.size()];
				postCount = rollBackCount;


				for (int i = 0; i < postsInRow + 3; i++) {

					/*
					 * if(postFlag[i]==true) { row.add(0); } else {
					 */

					// manish started
					if (i == 0) {
						innerData = new ArrayList();

						for (int k = 0; k < loanList.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(cntFirst++);
							innerData.add(innerRow);
						}
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");
					}
					// ended

					else if (i == 1) {
						innerData = new ArrayList();
						for (int k = 0; k < loanList.size(); k++) {
							innerRow = new ArrayList();
							if(loanList.get(k).getTypeId().equals(hbaLandPrinId)||loanList.get(k).getTypeId().equals(hbaHousePrinId))
							{
								innerRow.add("HBA PRINCIPAL");
							}
							else
							{
								innerRow.add(loanEdpNameMap.get(String.valueOf(loanList.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							}
							innerData.add(innerRow);
						}
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(nameVo); // td.setStyles(numberDispalyVO);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");
					} else if (i == postsInRow + 2)// total logic
					{
						double gt = 0;

						// row.add("a");
						innerData = new ArrayList();
						for (int p = 0; p < loanList.size(); p++) {
							innerRow = new ArrayList();
							innerRow.add(totalLoan[p]);

							innerData.add(innerRow);
							gt += totalLoan[p];
						}
						// innerRow = new ArrayList();
						// innerRow.add(gt);
						emptotLoan[postsInRow] = gt;
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");

					}

					else {

						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);


						/*if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false)
						{
							break;
						}*/
						if (paybillVo.getHrEisEmpMst() != null
						) {
							// logger.info("column no = "+(i-1)+" post count "+postCount);
							// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							innerData = new ArrayList();
							double empTotal = 0;
							for (int p = 0; p < loanList.size(); p++) {
								innerRow = new ArrayList();
								String method = "";
								/*logger.info("type is "
										+ loanList.get(p).getCmnLookupId()
										+ " edp code is "
										+ loanList.get(p).getRltBillTypeEdp()
												.getEdpCode());*/
								if (loanList.get(p).getCmnLookupId() == 2500136)
									method = "getAdv"
										+ loanEdpNameMap.get(String.valueOf(loanList.get(p).getTypeId())).toString().split(",")[0];
								else
									method = "getLoan"
										+ loanEdpNameMap.get(String.valueOf(loanList.get(p).getTypeId())).toString().split(",")[0];
								Class pay = paybillVo.getClass();
								Method payMthd = pay.getMethod(method, null);
								double value = (Double) payMthd.invoke(
										paybillVo, null);

								/*		if (loanList.get(p).getCmnLookupId() == 2500137) {

									method = "getLoanInt"
											+ loanList.get(p)
													.getRltBillTypeEdp()
													.getEdpCode();
									pay = paybillVo.getClass();
									payMthd = pay.getMethod(method, null);
									value += (Double) payMthd.invoke(paybillVo,
											null);

								}*/



								double hbaHouseForPrincipal=0;
								double hbaLandForPrincipal=0;

								if(hbaHousePrin==true && hbaLandPrin == true && loanList.get(p).getTypeId().equals(hbaHousePrinId))
								{
									method="getLoan"+edpCodeForHbaLandPri;
									payMthd = pay.getMethod(method, null);
									hbaLandForPrincipal= (Double) payMthd.invoke(paybillVo, null);
									hbaHouseForPrincipal=value;
									value+= hbaLandForPrincipal;
									/*logger.info("hbaLand Principal value is "+hbaLandForPrincipal + " for employee name "+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());*/

								}
								if (value > 0) {
									HrPayPaybillLoanDtls hrPayPaybillLoanDtls = payBillDAOImpl
									.getLoanDtlsFromPaybill(paybillVo
											.getId(), loanList.get(p)
											.getTypeId());
									if(hbaLandForPrincipal>0)
										hrPayPaybillLoanDtls=payBillDAOImpl.getLoanDtlsFromPaybill(paybillVo.getId(),hbaLandPrinId);



									if (hrPayPaybillLoanDtls != null  
											&& !(loanList.get(p).getTypeId().equals(hbaHousePrinId) || loanList.get(p).getTypeId().equals(hbaLandPrinId)))
										innerRow.add(hrPayPaybillLoanDtls
												.getRecoveredInst()
												+ "/"
												+ hrPayPaybillLoanDtls
												.getTotalInst()
												+ " "
												+ Math.round(value));
									else {
										if(hrPayPaybillLoanDtls != null && !(hbaHouseForPrincipal>0 && hbaLandForPrincipal>0))
										{
											innerRow.add(hrPayPaybillLoanDtls
													.getRecoveredInst()
													+ "/"
													+ hrPayPaybillLoanDtls
													.getTotalInst()
													+ " "
													+ Math.round(value));
										}
										else
											innerRow.add(Math.round(value));
									}
								} else {
									innerRow.add(Math.round(value));
								}

								// logger.info("allowance value ::"+ value);
								innerData.add(innerRow);
								empTotal += value;

								totalLoan[p] += value;

							}
							// innerRow = new ArrayList();
							// innerRow.add(empTotal);
							emptotLoan[i - 2] = empTotal;
							// innerData.add(innerRow);
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							if (innerData.size() > 0)
								row.add(td);
							else
								row.add("");

						} else if (paybillVo.getHrEisEmpMst() == null
								&& postFlag[i] == true) {

							innerData = new ArrayList();
							/*
							 * innerRow = new ArrayList(); innerRow.add("");
							 * innerData.add(innerRow);
							 */
							for (int p = 0; p < loanList.size(); p++) {
								innerRow = new ArrayList();
								innerRow.add("");
								innerData.add(innerRow);

							}
							if(loanList.size()>0)
							{td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
							}
							else
							{
								row.add("");
							}
						} else {
							i--;
						}
						postCount++;
					}

					// }
				}

				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(" ");
				innerData = new ArrayList();

				for (int k = 0; k < loanList.size(); k++) {
					innerRow = new ArrayList();
					innerRow.add(cntLast++);
					innerData.add(innerRow);
				}
				td = new TabularData(innerData);
				td.setStyles(nameVo);

				if (innerData.size() > 0)
					row.add(td);
				else
					row.add("");

				DataList.add(row);
				// logger.info("datalist  before  manish 5" +DataList);

				// to display total AG + Loan deductions

				row = new ArrayList();
				row.add(cntFirst++);
				row.add("Total AG Ded.");
				for (int i = 0; i < postsInRow + 1; i++) {
					StyledData dataStyle1 = new StyledData();
					dataStyle1.setStyles(boldStyleVO);
					dataStyle1.setData(emptotDeducAg[i] + emptotLoan[i]);
					if (emptotDeducAg[i] + emptotLoan[i] == 0)
						row.add("");
					else
						row.add(dataStyle1);

					// row.add();
					emptotDeducAg[i] = emptotDeducAg[i] + emptotLoan[i];
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				// logger.info("manish 6 size is"+row.size());
				DataList.add(row);

				// / for row 10 deductions by TRY
				orderdataList = new ArrayList();
				centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0]
				                  .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0]
				                  .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1]
				                  .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left");
				dataStyle = new StyledData();
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setColspan(displayPostsInRow + 4);
				dataStyle.setData("DEDUCTIONS ADJUSTABLE BY TRY");
				orderdataList.add(dataStyle);
				DataList.add(orderdataList);

				// for row 11
				row = new ArrayList();
				double emptotDeducTRY[] = new double[postsInRow + 1];
				double totalDeducTRY[] = new double[deducTyEdpList.size()];
				postCount = rollBackCount;
				for (int i = 0; i < postsInRow + 3; i++) {

					/*
					 * if(postFlag[i]==true) { row.add(0); } else {
					 */

					// manish started
					if (i == 0) {
						innerData = new ArrayList();

						for (int k = 0; k < deducTyEdpList.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(cntFirst++);
							innerData.add(innerRow);
						}
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");
					}
					// ended
					
					else if (i == 1) {
						logger.info("deducTyEdpList size"+deducTyEdpList.size());
						innerData = new ArrayList();
						for (int k = 0; k < deducTyEdpList.size(); k++) {
							innerRow = new ArrayList();
							logger.info("Names of Deduc"+deducEdpNameMap.get(String.valueOf(deducTyEdpList.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							innerRow.add(deducEdpNameMap.get(String.valueOf(deducTyEdpList.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							innerData.add(innerRow);
						}

						td = new TabularData(innerData);
						td.setStyles(nameVo);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");
					} else if (i == postsInRow + 2)// total logic
					{
						double gt = 0;

						// row.add("a");
						innerData = new ArrayList();
						for (int p = 0; p < deducTyEdpList.size(); p++) {
							innerRow = new ArrayList();
							innerRow.add(totalDeducTRY[p]);

							innerData.add(innerRow);
							gt += totalDeducTRY[p];
						}
						// innerRow = new ArrayList();
						// innerRow.add(gt);
						emptotDeducTRY[postsInRow] += gt;
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");

					}

					else {

						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);

						/*if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false)
						{
							break;
						}*/

						if (paybillVo.getHrEisEmpMst() != null
						) {
							// logger.info("column no = "+(i-1)+" post count "+postCount);
							// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							innerData = new ArrayList();
							double empTotal = 0;
							for (int p = 0; p < deducTyEdpList.size(); p++) {
								innerRow = new ArrayList();

								String method = "getDeduc"
									+ deducEdpNameMap.get(String.valueOf(deducTyEdpList.get(p).getTypeId())).toString().split(",")[0];
								Class pay = paybillVo.getClass();
								Method payMthd = pay.getMethod(method, null);
								double value = (Double) payMthd.invoke(
										paybillVo, null);
								/*logger.info("Method Name is " + method
										+ " value is " + value);*/
								innerRow.add(value);
								// logger.info("allowance value ::"+ value);
								innerData.add(innerRow);
								empTotal += value;

								totalDeducTRY[p] += value;

							}
							// innerRow = new ArrayList();
							// innerRow.add(empTotal);
							emptotDeducTRY[i - 2] = empTotal;
							// innerData.add(innerRow);
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							if (innerData.size() > 0)
								row.add(td);
							else
								row.add("");

						} else if (paybillVo.getHrEisEmpMst() == null
								&& postFlag[i] == true) {

							innerData = new ArrayList();
							/*
							 * innerRow = new ArrayList(); innerRow.add("");
							 * innerData.add(innerRow);
							 */
							for (int p = 0; p < deducTyEdpList.size(); p++) {
								innerRow = new ArrayList();
								innerRow.add("");
								innerData.add(innerRow);

							}
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
						} else {
							i--;
						}
						postCount++;
						// }

					}
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				innerData = new ArrayList();

				for (int k = 0; k < deducTyEdpList.size(); k++) {
					innerRow = new ArrayList();
					innerRow.add(cntLast++);
					innerData.add(innerRow);
				}
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				if (innerData.size() > 0)
					row.add(td);
				else
					row.add("");

				DataList.add(row);

				// total sdeduction TRY
				// manish started for Loan In inner

				row = new ArrayList();
				double totalAdv[] = new double[loanListNew.size()];
				postCount = rollBackCount;


				for (int i = 0; i < postsInRow + 3; i++) {


					/*
					 * f(postFlag[i]==true) { row.add(0); } else {
					 */
					// manish started
					if (i == 0) {
						innerData = new ArrayList();

						for (int k = 0; k < loanListNew.size(); k++) {
							innerRow = new ArrayList();
							innerRow.add(cntFirst++);
							innerData.add(innerRow);
						}
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");
					}
					// ended

					else if (i == 1) {
						innerData = new ArrayList();
						for (int k = 0; k < loanListNew.size(); k++) {
							innerRow = new ArrayList();
							if(loanListNew.get(k).getTypeId().equals(hbaLandIntId)||loanListNew.get(k).getTypeId().equals(hbaHouseIntId))
							{
								innerRow.add("HBA INTEREST");
							}
							else
							{innerRow.add(loanEdpNameMap.get(String.valueOf(loanListNew.get(k).getTypeId())).toString().split(",")[1].toUpperCase());
							}
							innerData.add(innerRow);
						}
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(nameVo); // td.setStyles(numberDispalyVO);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");
					} else if (i == postsInRow + 2)// total logic
					{
						double gt = 0;

						// row.add("a");
						innerData = new ArrayList();
						for (int p = 0; p < loanListNew.size(); p++) {
							innerRow = new ArrayList();
							innerRow.add(totalAdv[p]);



							innerData.add(innerRow);
							gt += totalAdv[p];
						}
						// innerRow = new ArrayList();
						// innerRow.add(gt);
						emptotDeducTRY[postsInRow] += gt;
						// innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						if (innerData.size() > 0)
							row.add(td);
						else
							row.add("");

					}

					else {

						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);

						/*if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false)
						{
							breakflag=true;
							break;
						}*/

						if (paybillVo.getHrEisEmpMst() != null
						) {
							// logger.info("column no = "+(i-1)+" post count "+postCount);
							// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							innerData = new ArrayList();
							double empTotal = 0;
							for (int p = 0; p < loanListNew.size(); p++) {
								innerRow = new ArrayList();

								String method = "";
								if (loanListNew.get(p).getCmnLookupId() == 2500137)
								{

									/*method = "getLoan"
											+ loanListNew.get(p)
													.getRltBillTypeEdp()
													.getEdpCode();*/

									if(loanListNew.get(p).getTypeId().equals("61"))
										method = "getLoan"
											+ loanEdpNameMap.get(String.valueOf(loanListNew.get(p).getTypeId())).toString().split(",")[0];

									else
										method = "getLoanInt"
											+ loanEdpNameMap.get(String.valueOf(loanListNew.get(p).getTypeId())).toString().split(",")[0];



								}
								else
									method = "getAdv"
										+ loanEdpNameMap.get(String.valueOf(loanListNew.get(p).getTypeId())).toString().split(",")[0];

								Class pay = paybillVo.getClass();
								Method payMthd = pay.getMethod(method, null);
								double value = (Double) payMthd.invoke(




										paybillVo, null);

								/*		if (loanListNew.get(p).getCmnLookupId() == 2500137) {
									method = "getLoanInt"
											+ loanListNew.get(p)
													.getRltBillTypeEdp()
													.getEdpCode();
									pay = paybillVo.getClass();
									payMthd = pay.getMethod(method, null);
									value += (Double) payMthd.invoke(paybillVo,
											null);
								}*/

								double hbaHouseForInt=0;
								double hbaLandForInt=0;
								if(hbaHouseInt==true && hbaLandInt == true && loanListNew.get(p).getTypeId().equals(hbaHouseIntId))
								{
									logger.info(" "+loanListNew.get(p).getTypeId());
									method="getLoanInt"+edpCodeForHbaLandInt;
									payMthd = pay.getMethod(method, null);
									hbaLandForInt= (Double) payMthd.invoke(paybillVo, null);
									hbaHouseForInt =value;
									value+= hbaLandForInt;
									logger.info("hbaLand interest value is "+hbaLandForInt + "method is "+method);
								}

								if (value > 0) {
									HrPayPaybillLoanDtls hrPayPaybillLoanDtls = payBillDAOImpl
									.getLoanDtlsFromPaybill(paybillVo
											.getId(), loanListNew
											.get(p).getTypeId());
									if(hbaLandForInt > 0)
										hrPayPaybillLoanDtls = payBillDAOImpl
										.getLoanDtlsFromPaybill(paybillVo
												.getId(),hbaLandIntId);
									if (hrPayPaybillLoanDtls != null && !(loanListNew.get(p).getTypeId().equals(hbaLandIntId) || loanListNew.get(p).getTypeId().equals(hbaHouseIntId)))
										innerRow.add(hrPayPaybillLoanDtls
												.getRecoveredInst()
												+ "/"
												+ hrPayPaybillLoanDtls
												.getTotalInst()
												+ " "
												+ Math.round(value));
									else {
										logger.info("values for hbaHouseForInt = "+hbaHouseForInt + " and for hbaLandForInt = "+hbaLandForInt);
										if(hrPayPaybillLoanDtls != null && !(hbaHouseForInt>0 && hbaLandForInt>0))
										{
											logger.info("inside cubb installments "+hrPayPaybillLoanDtls.getRecoveredInst()+ "/"+ hrPayPaybillLoanDtls.getTotalInst());
											innerRow.add(hrPayPaybillLoanDtls.getRecoveredInst()+ "/"+ hrPayPaybillLoanDtls.getTotalInst()+ " "
													+ Math.round(value));
										}
										else
										{
											logger.info("inside else part "+Math.round(value));
											innerRow.add(Math.round(value));
										}
									}
								} else {
									innerRow.add(Math.round(value));
								}
								// innerRow.add(value);
								// logger.info("allowance value ::"+ value);
								innerData.add(innerRow);
								empTotal += value;

								totalAdv[p] += value;

							}
							// innerRow = new ArrayList();
							// innerRow.add(empTotal);
							// emptotAdv[i-2]=empTotal;
							emptotDeducTRY[i - 2] += empTotal;
							// innerData.add(innerRow);
							td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							if (innerData.size() > 0)
								row.add(td);
							else
								row.add("");

						} else if (paybillVo.getHrEisEmpMst() == null
								&& postFlag[i] == true) {

							innerData = new ArrayList();
							/*
							 * innerRow = new ArrayList(); innerRow.add("");
							 * innerData.add(innerRow);
							 */
							for (int p = 0; p < loanListNew.size(); p++) {
								innerRow = new ArrayList();
								innerRow.add("");
								innerData.add(innerRow);

							}
							if(loanListNew.size()>0)
							{td = new TabularData(innerData);
							td.setStyles(numberDispalyVO);
							row.add(td);
							}
							else
							{
								row.add("");
							}
						} else {
							i--;
						}
						postCount++;
					}

					// }
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}

				row.add(" ");
				innerData = new ArrayList();

				for (int k = 0; k < loanListNew.size(); k++) {
					innerRow = new ArrayList();
					innerRow.add(cntLast++);
					innerData.add(innerRow);
				}
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				if (innerData.size() > 0)
					row.add(td);
				else
					row.add("");
				DataList.add(row);

				// manish ended for loans in inner
				row = new ArrayList();
				row.add(cntFirst++);
				row.add("Tot.TRY.Ded.");

				for (int h = 0; h < postsInRow + 1; h++) {
					dataStyle = new StyledData();

					dataStyle.setData(emptotDeducTRY[h]);
					// dataStyle.setData( emptotDeducTRY[h]+emptotAdv[h]);
					dataStyle.setStyles(numberDispalyVO);
					if (emptotDeducTRY[h] == 0)
						row.add("");
					else
						row.add(dataStyle);
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				// logger.info("manish 8 size is"+row.size());
				DataList.add(row);

				// for row 12 total deductions AG+TRY

				row = new ArrayList();
				row.add(cntFirst++);
				row.add("Tot. Ded.");


				for (int h = 0; h < postsInRow + 1; h++) {
					dataStyle = new StyledData();

					dataStyle.setData((emptotDeducAg[h] + emptotDeducTRY[h]));

					dataStyle.setStyles(numberDispalyVO);
					if ((emptotDeducAg[h] + emptotDeducTRY[h]) == 0)
						row.add("");
					else
						row.add(dataStyle);
				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				logger.info("manish 9 size is" + row.size());
				DataList.add(row);
				// for row 13 NET

				row = new ArrayList();
				row.add(cntFirst++);
				row.add("NET");
				pageTotal = 0;
				onScreenTotal = 0;


				long tot=0;
				//postCount=rollBackCount;
				for (int h = 0; h < postsInRow + 1; h++) {
					//Kishan
					if(h < postsInRow)
					{
						if(EmpNetTotal[h] != null)
						{
							pageTotal = EmpNetTotal[h];
							tot += pageTotal;
						}
					}
					else
					{
						pageTotal = tot;
					}
					//end by kishan
					colorPaybillVO = null;
					dataStyle = new StyledData();
					onScreenTotal = emptotAllow[h]- (emptotDeducAg[h] + emptotDeducTRY[h]);
					if(colorCount<colorPostInRow){


						colorPaybillVO = (HrPayPaybill) paybillVoList.get(colorCount);
						if(colorPaybillVO.getHrEisEmpMst() == null && colorPaybillVO.getNetTotal() ==0){
							colorPaybillVO = null;}
						else
							colorCount++;

					}
					dataStyle.setData(pageTotal);
					if(pageTotal != onScreenTotal)
					{
						dataStyle.setStyles(colorVO);
					}
					else
						dataStyle.setStyles(boldStyleVO);

					if (emptotAllow[h] - (emptotDeducAg[h] + emptotDeducTRY[h]) == 0)
						row.add("");
					else{
						row.add(dataStyle);
						//postCount++;
					}

				}
				for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

					row.add(" ");
				}
				// logger.info("length of row is "+row.size());
				row.add(cntLast++);
				// logger.info("manish 10 size is"+row.size());
				DataList.add(row);
				//logger.info("cntLast***abba******" + cntLast);
				totalnetcount += ++cntLast;
				//logger.info("totalnetcount*********" + totalnetcount);
				cntLast = 1;
				cntFirst = 1;
				endReportTotal += pageTotal;

				if (page != pageCount - 1) {
					row = new ArrayList();
					centerboldStyleVO = new StyleVO[2];
					centerboldStyleVO[0] = new StyleVO();
					centerboldStyleVO[0]
					                  .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					centerboldStyleVO[0]
					                  .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
					centerboldStyleVO[1] = new StyleVO();
					centerboldStyleVO[1]
					                  .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					centerboldStyleVO[1].setStyleValue("Right");
					dataStyle = new StyledData();
					dataStyle.setStyles(centerboldStyleVO);
					dataStyle.setColspan(displayPostsInRow + 4);
					dataStyle.setData(" PAGE " + (page + 1));
					row.add(dataStyle);
					// logger.info("manish 11 size is"+row.size());
					DataList.add(row);

					row = new ArrayList();
					row.add(new PageBreak());
					DataList.add(row);
				} else {
					row = new ArrayList();
					row.add(" ");

					row.add("Net Payable");
					for (int n = 0; n < postsInRow; n++)
						row.add(" - ");

					dataStyle = new StyledData();
					dataStyle.setStyles(boldStyleVO);
					dataStyle.setData(endReportTotal);
					row.add(dataStyle);

					for (int nulcol = 0; nulcol < nullColumns; nulcol++) {

						row.add(" ");
					}
					row.add(" ");
					// logger.info("manish 11 size is"+row.size());

					DataList.add(row);
					row = new ArrayList();
					row.add(new PageBreak());
					DataList.add(row);
					// logger.info("datalist  final "+DataList);

				}


				// all pages ends here
			}

			// /manish ends here

		} catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
			logger.error("Exception occur in Paybill Inner " + e);
		}

		// logger.info("returning Data List is "+DataList);

		return DataList;

	}

	public ReportVO exportReport(ReportVO reportVO, Object arg1,
			ReportEvent event) {

		// ReportColumnVO[] rptCol = reportVO.getReportColumns();
		// String Print=CheckIfNull(reportVO.getParameterValue( "Print" ));

		// reportVO.setParameterValue("Department", locId);
		reportVO.setReportName("");

		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}

	public ReportVO getUserReportVO(ReportVO report, Object criteria) {
		ResourceBundle resourceBundle = ResourceBundle
		.getBundle("resources.Payroll");
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 = new SimpleDateFormat("MM");
		Map requestKeys = (Map) ((Map) criteria)
		.get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap
		.get("locationVO");
		long locationId = locationVO.getLocId();
		String month = fmt1.format(today);

		if (month.charAt(0) == '0') {
			month = month.substring(1, 2);
		}
		if (report.getReportCode().equals("5000008"))

		{
			report.setParameterValue("Year", yr);
			report.setParameterValue("Month", month);
			report.setParameterValue("Department", locationId + "");
			// added by ravysh
			// report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
			report.setParameterValue("billTypePara", resourceBundle
					.getString("OnlyPaybill"));
		}

		if (report.getReportCode().equals("5000008"))

		{
			report.setParameterValue("Department", locationId + "");
		}

		return report;
	}

	final String CheckIfNull(Object lStra) {
		String lStrb = "";
		if (lStra != null) {
			lStrb = (((String) lStra).trim());

		}
		return lStrb;
	}

}
