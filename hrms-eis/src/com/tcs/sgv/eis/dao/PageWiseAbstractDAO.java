package com.tcs.sgv.eis.dao;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
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
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.ess.dao.OrgDesignationMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;

public class PageWiseAbstractDAO extends DefaultReportDataFinder implements
		ReportDataFinder, ReportEventListener {
	@SuppressWarnings( { "unchecked"})
		public Collection findReportData(ReportVO report, Object criteria)
				throws ReportException {
	
	
	
			Log logger = LogFactory.getLog(getClass());
			// logger.info("called 2");
	
			// logger.info("We are inside findReportData function of MahaPayBillInnerDao");
	
			int displayPostsInRow = 7;
			ReportColumnVO[] newReportColumns = new ReportColumnVO[displayPostsInRow + 4];
			ArrayList DataList = new ArrayList();
			long billNo = 0, month = 0, year = 0;
			Map requestAttributes = (Map) ((Map) criteria)
					.get(IReportConstants.REQUEST_ATTRIBUTES);
			Map serviceMap = (Map) requestAttributes.get("serviceMap");
			Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
	
			long locId = 0;
			newReportColumns[0] = new ReportColumnVO();
	
			CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap
					.get("locationVO");
			long locationId = locationVO.getLocId();
	
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
				locId = StringUtility.convertToLong(baseLoginMap.get("locationId")
						.toString());
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
	
						logger.info("adfgadfgh" + portType);
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
			
			
			/*logger.info("In maha paybill Generating Paybill Report for month = "
					+ month + " Year " + year + " BillNo " + billNo);
			logger.info("In maha paybill Generating  Paybill Report for month = "
					+ month + " Year " + year + " BillNo " + billNo);*/
	
			// Added by Abhilash
			Calendar cal = new GregorianCalendar();
			int Month = cal.get(Calendar.MONTH);
			int Year = cal.get(Calendar.YEAR);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			int millysecond = cal.get(Calendar.MILLISECOND);
	
			/*List<OrgDdoMst> ddoList = payBillDAOImpl
					.getDDOCodeByLoggedInPost(loggedInpostId);
	
			String ddocode = "";
			if (ddoList.size() > 0)
				ddocode = ddoList.get(0).getDdoCode();
	
			logger.info("ddocode is *******" + ddocode);*/
			
			String ddocode ="";
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			//ADDED BY ROSHAN FOR REPORTS AT ALL LEVEL
			
			if (billNo !=0){
				locId=payBillDAO.getLocationCode(billNo);
				logger.info("hii i m in pagewiseabstractDao for inner.");
				
			} 
			//ENDED BY ROSHAN FOR REPORTS AT ALL LEVEL
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
			if(ddoCodeList!=null)
			 logger.info("After query execution for DDO Code " + ddoCodeList.size());
			
			OrgDdoMst ddoMst = null; 
			if(ddoCodeList!=null && ddoCodeList.size()>0){
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
	
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss.SSS");
			//logger.info("10. " + format.format(now));
	

			Calendar calGiven = Calendar.getInstance();
			calGiven.set(Calendar.YEAR, Integer.valueOf(Long.valueOf(year).toString()));
			calGiven.set(Calendar.MONTH, (Integer.valueOf(Long.valueOf(month).toString()) - 1));
			calGiven.set(Calendar.DAY_OF_MONTH, 1);
			Date givenDate = calGiven.getTime();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
			String givenStrDate = sdf.format(givenDate);
			
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
			URLStyleVO[0].setStyleValue(21 + "");
	
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
	
				//logger.info("going to call getAll Data in MahaPaybillInnerDAO");
				List<HrPayEdpCompoMpg> allEdpList = edpDao.getAllData();
				List<HrPayLocComMpg> locAllow = compDao
						.getDataAllowChckedForMonthYear(locId, month, year);
				List<HrPayLocComMpg> locDeduc = compDao
						.getDataDeductChckedForMonthYear(locId, month, year);
				List<HrPayLocComMpg> loanListMapped = compDao
						.getDataLoanChckedForMonthYear(locId, month, year);
			//	logger.info("The size of loanList Mapped is "
						//+ loanListMapped.size());
	
				for (int i = 0; i < allEdpList.size(); i++) {
					if (allEdpList.get(i).getTypeId() != null) {
						if (allEdpList.get(i).getCmnLookupId() == 2500134) {
							for (int j = 0; j < locAllow.size(); j++) {
								if (allEdpList.get(i).getTypeId().equals(
										"" + locAllow.get(j).getCompId())) {
									// logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name "
									// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									allowEdpList.add(allEdpList.get(i));
									break;
								}
							}
						} else if (allEdpList.get(i).getCmnLookupId() == 2500135) {
							for (int j = 0; j < locDeduc.size(); j++) {
								if (allEdpList.get(i).getTypeId().equals(
										"" + locDeduc.get(j).getCompId())) {
									HrPayDeducTypeMst ded = deduDao.read(locDeduc
											.get(j).getCompId());
									if (ded.getDeductionBy().getLookupId() == 2901424) {
										// logger.info("deduc by AG "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name "
										// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
										deducAgEdpList.add(allEdpList.get(i));
									} else if (ded.getDeductionBy().getLookupId() == 2901425) {
										// logger.info("deduc by TRA "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name "
										// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
										deducTyEdpList.add(allEdpList.get(i));
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
									if (dispType == 2500381) {
										loanListNew.add(allEdpList.get(i));
									} else if (dispType == 2500382) {
										
										loanList.add(allEdpList.get(i));
									} else if (dispType == 2500383) {
										loanListGross.add(allEdpList.get(i));
									}
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
									if (dispType == 2500381) {
										loanListNew.add(allEdpList.get(i));
									} else if (dispType == 2500382) {
										/*if(hrLoanAdvMst.getLoanAdvId()==67 || hrLoanAdvMst.getLoanAdvId()==56)
										{
											loanListNew.add(allEdpList.get(i));
										}*/
										loanList.add(allEdpList.get(i));
									} else if (dispType == 2500383) {
										loanListGross.add(allEdpList.get(i));
									}
									// logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name "
									// +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									// loanList.add(allEdpList.get(i));
									break;
	
								}
							}
	
						}
	
					}
	
				}
	
				logger.info("The size of loanList is " + loanList.size());
				logger.info("The size of loanListNew is " + loanListNew.size());
				logger.info("Loan list gross is " + loanListGross.size());
				PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv
						.getSessionFactory());
				GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(
						CmnLocationMst.class);
				gen.setSessionFactory(serv.getSessionFactory());
				CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locId);
	
				long parentLocId = cmnLocMst.getParentLocId();
				
				Map innerMap  = payDao.getBillDataForInner(locId, billNo, month, year, parentLocId);
				ArrayList<HrPayPaybill> paybillVoList  = (ArrayList<HrPayPaybill>)innerMap.get("paybillVoLst");
				boolean tempPostFlag=  Boolean.parseBoolean(String.valueOf(innerMap.get("flag")));
				Map postGroupMap = (Map)innerMap.get("postGroupMap");
				//Map postGroupMap = payBillDAO.getpPostGroupMpg(locId, billNo, month, year, parentLocId);
				logger.info("postGroupMap is "+postGroupMap);
				// manish started here
				long tempVacant = 0;
				//List<OrgPostDetailsRlt> nonVacantList = new ArrayList<OrgPostDetailsRlt>();
				List nonVacantList = new ArrayList();
				Map empDesigMap = new HashMap();
				long tempVar = 0;
	
				logger.info("size of paybilvo list is " + paybillVoList.size());
				if (paybillVoList != null && paybillVoList.size() > 0)
					logger.info(" paybillgrpId is "
							+ paybillVoList.get(0).getPaybillGrpId());
				/*
				 * for(int i =0 ; i<paybillVoList.size();i++)
				 * logger.info(" hu mmmmmmmm "
				 * +paybillVoList.get(i).getOrgPostMst().getPostId());
				 */
				int prmCounter = 0;
				int tmpCounter=0;
	
				int total = 0;
	
				List tempMap = new ArrayList();
				
				List checkList = new ArrayList();
	
				long permCounter=0;
				// int elseCOunter=0;
				for (int p = 0; p < paybillVoList.size(); p++) {
					logger.info("post id is "
							+ paybillVoList.get(p).getOrgPostMst().getPostId());
	
					if(paybillVoList.get(p).getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Per") && paybillVoList.get(p).getHrEisEmpMst()!= null)
						permCounter=permCounter+1;
					
					
					if (tempVacant == 0) {
						if (tempVar == 0) {
	
							// logger.info("else part counter is "+elseCOunter);
							// elseCOunter=0;
							tempVacant = 0;
							GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(
									OrgPostDetailsRlt.class);
							genDao.setSessionFactory(serv.getSessionFactory());
	
							OrgPostDetailsRlt detailsRlt = payBillDAOImpl
									.getOrgPostDtlsObj(paybillVoList.get(p)
											.getOrgPostMst().getPostId());
	
							tempMap.add(detailsRlt.getOrgDesignationMst()
									.getDsgnId());
							
	
							logger
									.info("desig  id is "
											+ detailsRlt.getOrgDesignationMst()
													.getDsgnId());
							
							
							//
							
							
							String group="";
							String  postGroupString=""+detailsRlt.getOrgDesignationMst().getDsgnId();
							logger.info("postid is "+paybillVoList.get(p).getOrgPostMst().getPostId());
							//logger.info("null condition is "+postGroupMap.get(""+paybillVoList.get(p).getOrgPostMst().getPostId())!= null);
							logger.info("before group is "+postGroupMap.get( paybillVoList.get(p).getOrgPostMst().getPostId()+"".trim() ) );
							Object compareVar =postGroupMap.get( paybillVoList.get(p).getOrgPostMst().getPostId()+"".trim() ) ; 
						//	if(postGroupMap.get(paybillVoList.get(p).getOrgPostMst().getPostId()+"".trim())!= null );
							if(compareVar != null)
							{	
								//logger.info("Bajarang  "+postGroupMap.get(""+paybillVoList.get(p).getOrgPostMst().getPostId()).toString());
								postGroupString+=postGroupMap.get(""+paybillVoList.get(p).getOrgPostMst().getPostId()).toString();;
								group+=postGroupMap.get(""+paybillVoList.get(p).getOrgPostMst().getPostId()).toString();
							}
							logger.info("postGroupString is "+postGroupString);
							logger.info("group is "+group);
							//
							if(checkList.contains(postGroupString))
							{
								nonVacantList = payBillDAOImpl.getTotalPostNew(detailsRlt.getOrgDesignationMst().getDsgnId(), billNo,locId,true,group,givenStrDate);
								//vacantPostList=payBillDAOImpl.getTotalPost(detailsRlt.getOrgDesignationMst().getDsgnId(), billNo,locId,true,null);
							}
							else
							{
								nonVacantList = payBillDAOImpl.getTotalPostNew(detailsRlt.getOrgDesignationMst().getDsgnId(), billNo,locId,false,group,givenStrDate);
								//vacantPostList=payBillDAOImpl.getTotalPost(detailsRlt.getOrgDesignationMst().getDsgnId(), billNo,locId,false,null);
							}
							if(nonVacantList.size() == 0)
							{
								nonVacantList = payBillDAOImpl.getTotalPostNew(detailsRlt.getOrgDesignationMst().getDsgnId(), billNo,locId,true,group,givenStrDate);
								//vacantPostList=payBillDAOImpl.getTotalPost(detailsRlt.getOrgDesignationMst().getDsgnId(), billNo,locId,true,null);
							}
							
							checkList.add(postGroupString);
							//if(vacantPostList != null && vacantPostList.size()>0)
						/*	for(int y=0;y<vacantPostList.size();y++)
								{
								nonVacantList.add(vacantPostList.get(y));
								}*/
								
	
							logger.info("nonVacantList" + nonVacantList.size());
	
							logger.info("tempVacant  is  manis   "
									+ nonVacantList.size());
							if(nonVacantList.size()>0) {
								tempVacant = payBillDAOImpl.getVacantPost(
										nonVacantList, month, year, locId,
										paybillVoList.get(p).getPaybillGrpId());
								}else {
									tempVacant=0;
								}
							
							
	
							logger.info("vacant count is " + tempVacant);
	
							OrgDesignationMstDaoImpl orgDesignationMstDaoImpl = new OrgDesignationMstDaoImpl(
									OrgDesignationMst.class, serv
											.getSessionFactory());
	
							logger.info("size of nonVacantList is  "
									+ nonVacantList.size());
							logger.info("tempVacant  is " + tempVacant);
							StringBuffer sb = new StringBuffer();
							sb.append(nonVacantList.size());
							sb.append(",");
							sb.append(tempVacant);
							sb.append(",");
							sb.append(orgDesignationMstDaoImpl.read(
									detailsRlt.getOrgDesignationMst().getDsgnId())
									.getDsgnName());
	
							/*empDesigMap.put(paybillVoList.get(p).getOrgPostMst()
									.getPostId(), sb.toString());
									
	*/
							Map desigEmp = new HashMap();
							desigEmp.put("filledPosts",nonVacantList.size());
							desigEmp.put("vacantPosts",tempVacant);
							desigEmp.put("designation",orgDesignationMstDaoImpl.read(
									detailsRlt.getOrgDesignationMst().getDsgnId())
									.getDsgnName());
							/*logger.info("filledPosts "+Long.valueOf(desigEmp.get("filledPosts").toString()) + "vacantPosts " +Long.valueOf(desigEmp.get("vacantPosts").toString()) + "Designation "+orgDesignationMstDaoImpl.read(
									detailsRlt.getOrgDesignationMst().getDsgnId())
									.getDsgnName());
							
							logger.info(Long.valueOf(desigEmp.get("filledPosts").toString())==Long.valueOf(desigEmp.get("vacantPosts").toString()));
							logger.info("String "+desigEmp.get("filledPosts").toString()==desigEmp.get("vacantPosts").toString());*/
							
							//logger.info(" Manish String "+desigEmp.get("filledPosts").toString().equals(desigEmp.get("vacantPosts").toString()));
							
							if(paybillVoList.get(p).getHrEisEmpMst() == null && (nonVacantList.size() != tempVacant) && p!=paybillVoList.size()-1)
							{
								empDesigMap.put(paybillVoList.get(p+1).getOrgPostMst().getPostId(),desigEmp);
								/*if(paybillVoList.get(p+1).getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Per"))
									permCounter=permCounter+nonVacantList.size()-tempVacant;
								if ((nonVacantList.size() == tempVacant)) 
									permCounter=permCounter+1;*/
							}
							else
							{
								empDesigMap.put(paybillVoList.get(p).getOrgPostMst().getPostId(), desigEmp);
								/*if(paybillVoList.get(p).getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Per"))
									permCounter=permCounter+nonVacantList.size()-tempVacant;
								if ((nonVacantList.size() == tempVacant)) 
									permCounter=permCounter+1;*/
							}
	
							
							
							logger.info(" post _id is "
									+ paybillVoList.get(p).getOrgPostMst()
											.getPostId() + " String is "
									+ sb.toString());
							if ((nonVacantList.size() == tempVacant)) {
								if(paybillVoList.get(p).getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp"))
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
				logger.info("temp Map List is " + tempMap);
				logger.info("total value is " + total);
				logger.info(" empDesigMap is " + empDesigMap + " size is "
						+ empDesigMap.size());
				logger.info("size of paybillVoList is " + paybillVoList.size());
				// manish ended
	
				int countVacant = payDao
						.countVacantData(locId, billNo, month, year);
				logger.info("value of countVacant" + countVacant);
	
				logger.info("vaccent posts are "+ countVacant);
				logger.info("edp List size is"+allowEdpList.size()+" deduc Ag "+deducAgEdpList.size()+" deduc Ty "+deducTyEdpList.size());
				 logger.info("paybill vo size "+paybillVoList.size() );
	
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
				
				if(permCounter%displayPostsInRow > 0 && tempPostFlag==true)
					dataCount = paybillVoList.size() - countVacant+Integer.parseInt(new Long(prmCounter).toString()) + (displayPostsInRow- Integer.parseInt(new Long(permCounter%displayPostsInRow).toString()));
				else
					dataCount = paybillVoList.size() - countVacant +Integer.parseInt(new Long(prmCounter).toString()) ;
				dataCount+=tmpCounter;
				int pageCount = dataCount / (displayPostsInRow);
				if (dataCount % displayPostsInRow > 0) {
					pageCount++;
				}
				if(permCounter%displayPostsInRow > 0)
					permNullColumn=(displayPostsInRow- Integer.parseInt(new Long(permCounter%displayPostsInRow).toString()));
				
				perPostInRow = displayPostsInRow-permNullColumn;
						
				
				/*if(permCounter%displayPostsInRow > 0)
					pageCount++;
				*/
				
				logger.info("permCounter is "+permCounter);
				logger.info("pageCount is "+pageCount);
				
				/*logger.info("data count is " + dataCount);
				logger.info("pageCount is " + pageCount);
	*/
				// logger.info("page count "+ pageCount);
				int postCount = 0;
				ArrayList row = null;
				ArrayList rowForPost = null;
				List innerData;
				List innerRow;
				TabularData td;
	
				double endReportTotal = 0, pageTotal = 0;
	
				// Page wise
				ArrayList pageAbstrat = new ArrayList();
	
				// page wise ended
				int tempPostCount=0;
				int pageNumberToBeDisplayed=0;
				int tempStartFlag= 0;
				int ga=0;
				int gaCount=0;
				//pageBreak:
				boolean[] postFlag = new boolean[9];
				boolean[] postPageFlag = new boolean[pageCount+2];
				for (int page = 0; page < pageCount; page++) {
					
					int postPageCounter=0;
					
					/*if(permCounter==(page+1)*)*/
						
				//added by manish
	
				
					logger.info(" check this "+ permCounter/displayPostsInRow);
					
					
					/*if(page > (permCounter/displayPostsInRow) && postTypeFlag == false)
					{
						logger.info("inside special if "+page);
					report.deleteReportAttributeItem(raavo);
					postTypeFlag = true;
					ReportAttributeVO	rravo = new ReportAttributeVO();
					rravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
					rravo.setLocation(IReportConstants.LOCATION_HEADER);
					rravo.setAlignment(IReportConstants.ALIGN_LEFT);
					rravo.setAttributeValue("Bill Id (" + billNo + ") ");
					rravo.setAttributeValue("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");
					report.addReportAttributeItem(rravo);
					}*/				
					
					
					ArrayList headerRow= new ArrayList();
			StyleVO[] headerStyleVo = new StyleVO[2];
					
			/*		StyleVO[]	headerStyleVo = new StyleVO[4];
				headerStyleVo[0] = new StyleVO();
				headerStyleVo[0]
							.setStyleId(IReportConstants.BORDER_LEFT);
				headerStyleVo[0]
							.setStyleValue("NO");
				headerStyleVo[1] = new StyleVO();
				headerStyleVo[1]
							.setStyleId(IReportConstants.BORDER_RIGHT);
				headerStyleVo[1].setStyleValue("NO");
				
				headerStyleVo[2] = new StyleVO();
				headerStyleVo[2]
							.setStyleId(IReportConstants.BORDER_BOTTOM);
				headerStyleVo[2].setStyleValue("NO");
				headerStyleVo[3] = new StyleVO();
				headerStyleVo[3]
							.setStyleId(IReportConstants.BORDER_TOP);
				headerStyleVo[3].setStyleValue("NO");*/
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
					if((page > (permCounter/displayPostsInRow) && ( (permCounter%displayPostsInRow) != 0) ) 
							|| (page == 0 && permCounter==0) || ((permCounter%displayPostsInRow == 0) &&  page == (permCounter/displayPostsInRow) ) )
					{
						dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName );
					}
					else
						dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - PERMANENT POSTS- Bill for "+officeName+" -"+ lStrGrpName );
					
					headerRow.add(dataStyle);
					
					
					
					//ended by manish
						
					HrPayPaybill pageHrPaybill = new HrPayPaybill();
	
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
							logger.info("inside if "+postsInRow);
						} else {
							postsInRow = dataCount % displayPostsInRow;
							nullColumns = displayPostsInRow - postsInRow;
							logger.info("inside else "+postsInRow);
						}
					} else { 
						postsInRow = displayPostsInRow;
						nullColumns = 0;
						logger.info("inside brode else  "+postsInRow);
					}
	
					logger.info("post in row is "+postsInRow);
					
					if(permNullColumn>0 && (permCounter/displayPostsInRow)==page)
					{
						nullColumns=permNullColumn;
						postsInRow = perPostInRow;
					}
					// for row 2--name
	
					HrPayPaybill paybillVo = null;
					// logger.info("for page "+page+" postsInRow "+postsInRow);
					int rollBackCount = postCount;
					// code for single page row 2 print
	
					// String postDesigString="";
	
					
					for (int o = 0; o < displayPostsInRow+2; o++) {
						postFlag[o] = false;
					}
					
					
					StyleVO[] postStyleVo=new StyleVO[2];
					
					postCount = tempPostCount;
					
					for (int i = 0; i < postsInRow; i++, postCount++) {
						// logger.info("column number: "+i);
						// logger.info("object number " +postCount);
						
						logger.info("post count is "+postCount + "postInRow is "+postsInRow);
						paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
						if(paybillVo.getHrEisEmpMst()!= null)
							logger.info("name "+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
						
					/*	if(paybillVo.getOrgPostMst().getPostTypeLookupId().getLookupName().contains("Temp")&& breakflag==false && permCounter%displayPostsInRow!=0)
						{
							breakflag=true;
							
							//statusFlag=false;
							
						//	dataCount = dataCount- Integer.parseInt(new Long(permCounter).toString());
							
							//postCount-=Integer.parseInt(new Long(permCounter).toString());
							logger.info("post count is "+postCount);
							//break pageBreak;
							//continue pageBreak;
							logger.info("permNullColumn "+permNullColumn +" and perPostInRow "+perPostInRow +"post id is "+paybillVo.getOrgPostMst().getPostId());
							nullColumns=permNullColumn;
							postsInRow = perPostInRow;
							break;
							
						}*/
						logger.info("postInrow is is "+postsInRow+ "post_id is "+paybillVo.getOrgPostMst().getPostId());
						innerData = new ArrayList();
						innerRow = new ArrayList();
						String fname = "", mname = "", lname = "";
						// started by manish
	
						// ended by manish
	
						/*
						 * if(flag==false) {
						 */
						Map displayMap = null;
						
						if(empDesigMap.get(paybillVo.getOrgPostMst()
								.getPostId()) != null){
						displayMap =(HashMap)empDesigMap.get(paybillVo.getOrgPostMst().getPostId());
						/*
						Long.valueOf(displayMap.get("filledPosts").toString());
						Long.valueOf(displayMap.get("vacantPosts").toString());
						displayMap.get("designation").toString();*/
						}
						
						if (paybillVo.getHrEisEmpMst() != null
								
								|| displayMap!= null && displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString()))
										 {
							// logger.info("name ::: "+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
							// started manish
							if (displayMap!= null &&  displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString())) {
	
							}else {
	
								pageHrPaybill.setHrEisEmpMst(paybillVo
										.getHrEisEmpMst());
								// pageHrPaybill.setBasic0101(paybillVo.getBasic0101());
								// ended manish
								if (paybillVo.getHrEisEmpMst().getOrgEmpMst()
										.getEmpFname().trim().length() > 0) {
									fname = ""
											+ paybillVo.getHrEisEmpMst()
													.getOrgEmpMst().getEmpFname()
													.trim().charAt(0);
								}
								if (paybillVo.getHrEisEmpMst().getOrgEmpMst()
										.getEmpLname().trim().length() > 0) {
									lname = ""
											+ paybillVo.getHrEisEmpMst()
													.getOrgEmpMst().getEmpLname()
													.trim();
								}
								if (paybillVo.getHrEisEmpMst().getOrgEmpMst()
										.getEmpMname() != null) {
									if (paybillVo.getHrEisEmpMst().getOrgEmpMst()
											.getEmpMname().trim().length() > 0) {
										mname = ""
												+ paybillVo.getHrEisEmpMst()
														.getOrgEmpMst()
														.getEmpMname().trim()
														.charAt(0);
									}
								}
								innerRow.add(fname + ". " +mname+". "+ lname);
								innerData.add(innerRow);
	
								innerRow = new ArrayList();
								//innerRow.add("[");
								//innerRow.add("["+paybillVo.getHrEisEmpMst().getSevarthEmpCode()+"]");
								if(paybillVo.getHrEisEmpMst().getSevarthEmpCode()!= null && paybillVo.getHrEisEmpMst().getSevarthEmpCode()!= "")
								{
									innerRow.add("["+paybillVo.getHrEisEmpMst().getSevarthEmpCode()+"]");
								}
								else 
								{
									innerRow.add("");
								}
								
								//innerRow.add("["++ paybillVo.getHrEisEmpMst().getEmpId()+"]");
								//innerRow.add("]");
								innerData.add(innerRow);
	
								innerRow = new ArrayList();
								//innerRow.add("[");
								innerRow.add("["+paybillVo.getHrEisOtherDtls()
										.getHrEisSgdMpg().getHrEisScaleMst()
										.getScaleStartAmt()
										+ "-"
										+ paybillVo.getHrEisOtherDtls()
												.getHrEisSgdMpg()
												.getHrEisScaleMst()
												.getScaleEndAmt()+"]");
								//innerRow.add("]");
								innerData.add(innerRow);
	
								innerRow = new ArrayList();
								innerRow.add(paybillVo.getHrEisOtherDtls()
										.getOtherCurrentBasic()
										- paybillVo.getHrEisOtherDtls()
												.getHrEisSgdMpg()
												.getHrEisScaleMst()
												.getScaleGradePay()
										+ " + "
										+ paybillVo.getHrEisOtherDtls()
												.getHrEisSgdMpg()
												.getHrEisScaleMst()
												.getScaleGradePay());
								innerData.add(innerRow);
	
								innerRow = new ArrayList();
								innerRow.add(paybillVo.getHrEisOtherDtls()
										.getOtherCurrentBasic());
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
									if( displayMap.get("filledPosts").toString().equals(displayMap.get("vacantPosts").toString()))
									{
										postPageCounter++;
										if(postPageCounter==postsInRow)
											postPageFlag[page+2]=true;
									}
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
					//DataList.add(headerRow);
					//DataList.add(rowForPost);
					//DataList.add(row);
	
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
	
					//DataList.add(row);
	
					// 
	
					// for row 4
	
					row = new ArrayList();
					// row.add(cntFirst++);
					double emptotAllow[] = new double[postsInRow + 1];
					double totalAllow[] = new double[allowEdpList.size() + 1];
					postCount = rollBackCount;
	
					// logger.info("postId String is "+postDesigString);
	
					for (int i = 0; i < postsInRow + 3; i++) {
	
	
						
						
						if(empDesigMap.get(paybillVo.getOrgPostMst()
								.getPostId()) != null){
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
								innerRow.add(allowEdpList.get(k)
										.getRltBillTypeEdp().getEdpShortName().toUpperCase());
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
							pageHrPaybill.setBasic0101(totalAllow[0]);
							for (int p = 0; p < allowEdpList.size() + 1; p++) {
								
								if(p==1)
								{
									innerRow = new ArrayList();
									innerRow.add(0);
									innerData.add(innerRow);
								}
								innerRow = new ArrayList();
								innerRow.add(totalAllow[p]);
								// started manish
								if (p != allowEdpList.size()) {
									Method method = null;
									Class paybillClass = pageHrPaybill.getClass();
									HrPayEdpCompoMpg edpCompoMpg = allowEdpList
											.get(p);
	
									String mthdName = "setAllow"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
	
									method = paybillClass.getMethod(mthdName,
											double.class);
									method.invoke(pageHrPaybill, totalAllow[p + 1]);
									// ended manish
								}
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
											+ allowEdpList.get(p)
													.getRltBillTypeEdp()
													.getEdpCode().trim();
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(
											paybillVo, null);
									innerRow.add(value);
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
					//DataList.add(row);
	
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
					//DataList.add(row);
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
					//DataList.add(row);
					
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
						
						/*
						 * if(postFlag[i]==true) { row.add(0); } else {
						 */
						// manish started
						
	
						
						if(empDesigMap.get(paybillVo.getOrgPostMst()
								.getPostId()) != null){
						/*
						Long.valueOf(displayMap.get("filledPosts").toString());
						Long.valueOf(displayMap.get("vacantPosts").toString());
						displayMap.get("designation").toString();*/
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
								innerRow.add(loanListGross.get(k)
										.getRltBillTypeEdp().getEdpShortName().toUpperCase());
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
								if (p != loanListGross.size()) {
									Method method = null;
									Class paybillClass = pageHrPaybill.getClass();
									HrPayEdpCompoMpg edpCompoMpg = loanListGross
											.get(p);
	
									String mthdName = "";
									if (edpCompoMpg.getCmnLookupId() == 2500137) {
										mthdName = "setLoan"
												+ edpCompoMpg.getRltBillTypeEdp()
														.getEdpCode().trim();
									} else {
										mthdName = "setAdv"
												+ edpCompoMpg.getRltBillTypeEdp()
														.getEdpCode().trim();
									}
	
									method = paybillClass.getMethod(mthdName,
											double.class);
									method.invoke(pageHrPaybill, totalGrossLoan[p]);
								}
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
									HrPayEdpCompoMpg edpCompoMpg = loanListGross
											.get(p);
									String method = "";
									if (edpCompoMpg.getCmnLookupId() == 2500137) {
										method = "getLoan"
												+ edpCompoMpg.getRltBillTypeEdp()
														.getEdpCode().trim();
									} else {
										method = "getAdv"
												+ edpCompoMpg.getRltBillTypeEdp()
														.getEdpCode().trim();
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
					//DataList.add(row);
	
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
					//DataList.add(row);
	
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
					//DataList.add(orderdataList);
	
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
						
						if(empDesigMap.get(hrPayPaybill.getOrgPostMst().getPostId()) != null){
							
						displayMap =(HashMap)empDesigMap.get(hrPayPaybill.getOrgPostMst().getPostId());
						
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
							GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(
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
							if (gpfDtl != null) 
							{
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
//							}
							
						
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
					//DataList.add(row);
					//DataList.add(secondRow);
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
					//DataList.add(orderdataList);
	
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
								innerRow.add(deducAgEdpList.get(k)
										.getRltBillTypeEdp().getEdpShortName().toUpperCase());
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
								// started manish
								if (p != deducAgEdpList.size()) {
									Method method = null;
									Class paybillClass = pageHrPaybill.getClass();
									HrPayEdpCompoMpg edpCompoMpg = deducAgEdpList
											.get(p);
	
									String mthdName = "setDeduc"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
	
									method = paybillClass.getMethod(mthdName,
											double.class);
									method.invoke(pageHrPaybill, totalDeducAg[p]);
								}
								// ended manish
	
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
											+ deducAgEdpList.get(p)
													.getRltBillTypeEdp()
													.getEdpCode();
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(
											paybillVo, null);
									innerRow.add(value);
									// logger.info("allowance value ::"+ value);
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
					//DataList.add(row);
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
								innerRow.add(loanList.get(k).getRltBillTypeEdp()
										.getEdpShortName().toUpperCase());
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
								// started manish
								String mthdName = "";
								Method method = null;
								Class paybillClass = pageHrPaybill.getClass();
								HrPayEdpCompoMpg edpCompoMpg = loanList.get(p);
								if (edpCompoMpg.getCmnLookupId() == 2500136)
									mthdName = "setAdv"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
								else
									mthdName = "setLoan"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
								method = paybillClass.getMethod(mthdName,
										double.class);
								method.invoke(pageHrPaybill, totalLoan[p]);
								// ended manish
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
												+ loanList.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
									else
										method = "getLoan"
												+ loanList.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
	
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
									if (value > 0) {
										HrPayPaybillLoanDtls hrPayPaybillLoanDtls = payBillDAOImpl
												.getLoanDtlsFromPaybill(paybillVo
														.getId(), loanList.get(p)
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
															+ loanList.get(p)
																	.getTypeId());*/
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
	
					//DataList.add(row);
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
					//DataList.add(row);
	
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
					//DataList.add(orderdataList);
	
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
							innerData = new ArrayList();
							for (int k = 0; k < deducTyEdpList.size(); k++) {
								innerRow = new ArrayList();
								innerRow.add(deducTyEdpList.get(k)
										.getRltBillTypeEdp().getEdpShortName().toUpperCase());
								innerData.add(innerRow);
							}
							// innerRow = new ArrayList();
							// innerRow.add("Total TRY");
							// innerData.add(innerRow);
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
								// started manish
								Method method = null;
								Class paybillClass = pageHrPaybill.getClass();
								HrPayEdpCompoMpg edpCompoMpg = deducTyEdpList
										.get(p);
	
								String mthdName = "setDeduc"
										+ edpCompoMpg.getRltBillTypeEdp()
												.getEdpCode().trim();
	
								method = paybillClass.getMethod(mthdName,
										double.class);
								method.invoke(pageHrPaybill, totalDeducTRY[p]);
								// ended manish
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
											+ deducTyEdpList.get(p)
													.getRltBillTypeEdp()
													.getEdpCode().trim();
									// logger.info("Method Name is "+method+" value is "+value);
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
	
					//DataList.add(row);
	
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
								innerRow.add(loanListNew.get(k).getRltBillTypeEdp()
										.getEdpShortName().toUpperCase());
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
	
								// started manish
								String mthdName = "";
								Method method = null;
								Class paybillClass = pageHrPaybill.getClass();
								HrPayEdpCompoMpg edpCompoMpg = loanListNew.get(p);
								if (edpCompoMpg.getCmnLookupId() == 2500137)
									if(loanListNew.get(p).getTypeId().equals("61"))
									mthdName = "setLoan"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
									else
										mthdName = "setLoanInt"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
								else
									mthdName = "setAdv"
											+ edpCompoMpg.getRltBillTypeEdp()
													.getEdpCode().trim();
								method = paybillClass.getMethod(mthdName,
										double.class);
								method.invoke(pageHrPaybill, totalAdv[p]);
								// ended manish
	
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
											+ loanListNew.get(p)
													.getRltBillTypeEdp()
													.getEdpCode();
										else
											method = "getLoanInt"
												+ loanListNew.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
											
									
									}
									else
										method = "getAdv"
												+ loanListNew.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
	
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
	
									if (value > 0) {
										HrPayPaybillLoanDtls hrPayPaybillLoanDtls = payBillDAOImpl
												.getLoanDtlsFromPaybill(paybillVo
														.getId(), loanListNew
														.get(p).getTypeId());
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
													.info("hrpaypaybill loan is null..."
															+ paybillVo.getId()
															+ " loan type is "
															+ loanList.get(p)
																	.getTypeId());*/
	
											innerRow.add(Math.round(value));
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
					//DataList.add(row);
	
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
					//DataList.add(row);
	
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
					//DataList.add(row);
					// for row 13 NET
	
					row = new ArrayList();
					row.add(cntFirst++);
					row.add("NET");
					pageTotal = 0;
					for (int h = 0; h < postsInRow + 1; h++) {
						dataStyle = new StyledData();
						pageTotal = emptotAllow[h]
								- (emptotDeducAg[h] + emptotDeducTRY[h]); // /+emptotGrossLoan[h]
																			// ----
																			// add
																			// here
																			// by
																			// japen
						dataStyle.setData(pageTotal);
						dataStyle.setStyles(boldStyleVO);
						if (emptotAllow[h] - (emptotDeducAg[h] + emptotDeducTRY[h]) == 0)
							row.add("");
						else
							row.add(dataStyle);
					}
					for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
	
						row.add(" ");
					}
					// logger.info("length of row is "+row.size());
					row.add(cntLast++);
					// logger.info("manish 10 size is"+row.size());
					//DataList.add(row);
					 ++cntLast;
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
						//DataList.add(row);
	
						row = new ArrayList();
						row.add(new PageBreak());
						//DataList.add(row);
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
	
						//DataList.add(row);
						row = new ArrayList();
						row.add(new PageBreak());
						//DataList.add(row);
						// logger.info("datalist  final "+DataList);
	
					}
	
					pageAbstrat.add(pageHrPaybill);
					// all pages ends here
				}
	
			
			
	
			
	
					// ///code for last page
				 int permPages= Integer.parseInt(new Long(permCounter).toString())/displayPostsInRow;
				 if(Integer.parseInt(new Long(permCounter).toString())%displayPostsInRow > 0)
					 permPages++;
				 	int flagCounter=2;
					endReportTotal = 0;
					pageTotal = 0;
					postCount = 0;
					cntFirst = 1;
					cntLast = 1;
					dataCount=0;
					permNullColumn=0;
					perPostInRow=0;
					pageNumberToBeDisplayed = 0;
					tempStartFlag = 0;
					int tempHeaderStartFlag = 0;
					int pageNumberToBeDisplayedTemp = 0;
					
					
					if(permPages%displayPostsInRow > 0 && tempPostFlag==true)
						dataCount = pageAbstrat.size() + (displayPostsInRow- Integer.parseInt(new Long(permPages%displayPostsInRow).toString()));
					else
						dataCount =pageAbstrat.size();
					if(permPages%displayPostsInRow > 0)
						permNullColumn=(displayPostsInRow- Integer.parseInt(new Long(permPages%displayPostsInRow).toString()));
					
					perPostInRow = displayPostsInRow-permNullColumn;
					
					pageCount = dataCount / (displayPostsInRow);
					if (dataCount % displayPostsInRow > 0) {
						pageCount++;
					}
					
					logger.info("permPages---"+permPages);
	
					paybillVoList = pageAbstrat;
					for (int page = 0; page < pageCount; page++) {
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
						StyleVO[] headerStyleVo = new StyleVO[2];
						headerStyleVo[0] = new StyleVO();
						headerStyleVo[0].setStyleId(IReportConstants.BORDER);
						headerStyleVo[0].setStyleValue("NO");
						headerStyleVo[1] = new StyleVO();
						headerStyleVo[1]
									.setStyleId(IReportConstants.BORDER_RIGHT);
						headerStyleVo[1].setStyleValue("NO");
							ArrayList headerRow = new ArrayList();
							StyledData dataStyle = new StyledData();
								dataStyle.setStyles(headerStyleVo);
								dataStyle.setColspan(11);
								/*if(page > (permPages/displayPostsInRow) )  
								{
									dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");
								}
								else
									dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - PERMANENT POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");
								*/
								/* if(permPages % displayPostsInRow==0 && permPages/displayPostsInRow>page)
									 dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - PERMANENT POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");
								 else if(permPages/displayPostsInRow==page)
									 dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");
								else
									dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName +"-Both Permanent");*/
									
								if(((page > (permPages/displayPostsInRow) && ( (permPages%displayPostsInRow) != 0) ) )
										|| (page == 0 && permPages==0) || ((permPages%displayPostsInRow == 0) &&  page == (permPages/displayPostsInRow) )) 
								{
									dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName );
									tempHeaderStartFlag = 1;
								}
								else
									if(tempHeaderStartFlag==0)
										dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - PERMANENT POSTS- Bill for "+officeName+" -"+ lStrGrpName );
									else
										dataStyle.setData("Bill Id (" + billNo	+ ") MTR 19 - TEMPORARY POSTS- Bill for "+officeName+" -"+ lStrGrpName );
								
								headerRow.add(dataStyle);
								
								
								
						// for 2nd row name
						row = new ArrayList();
	
						row.add(" ");
	
						innerData = new ArrayList();
						innerRow = new ArrayList();
	
						innerRow.add("Name");
						innerData.add(innerRow);
	
						td = new TabularData(innerData);
						td.setStyles(nameVo);// initialize tabular data
						// td.addStyle(IReportConstants.STYLE_FONT_FAMILY,
						// IReportConstants.VALUE_FONT_FAMLIY_TIMES);
						// td.addStyle(IReportConstants.BACKGROUNDCOLOR,
						// IReportConstants.VALUE_FONT_COLOR_YELLOW);
	
						row.add(td);
	
						int postsInRow = 0;
						int nullColumns = 0;
						
						logger.info("For Page Loop No Before Manipulation;;;"+page);
						logger.info("postsInRow-->"+postsInRow+" {dataCount}---> "+dataCount);
	
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
	
						
						if(permNullColumn>0 && (permPages/displayPostsInRow)==page)
						{
							nullColumns=permNullColumn;
							postsInRow = perPostInRow;
						}
						
						logger.info("For Page Loop No After Manipulation;;;"+page);
						logger.info("postsInRow-->"+postsInRow+" {dataCount}---> "+dataCount);
	
						
						// for row 2--name
	
						HrPayPaybill paybillVo = null;
						
						int rollBackCount = postCount;
						//int rollBackCount =0;
						//postCount = tempPostCount;
						logger.info("PostCount---"+postCount);
						if(tempStartFlag ==1)
							pageNumberToBeDisplayedTemp++;
						for (int i = 0; i < postsInRow; i++, postCount++) {
							paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
							innerData = new ArrayList();
							innerRow = new ArrayList();
							
							
					//		innerRow.add("PAGE " + ((page*postsInRow)+i + 1));
	
						/* if(permPages % displayPostsInRow==0 && permPages/displayPostsInRow>page)
								innerRow.add("PAGE " + ((page*postsInRow)+i + 1));
						 else if(permPages/displayPostsInRow==page)
								innerRow.add("PAGE " + ((page*postsInRow)+i + 1));
						else
								innerRow.add("PAGE " + ++temporaryPageNumber);*/
							
							if( ((permPages % displayPostsInRow ==0) && (page ==(permPages / displayPostsInRow))) || ( (permPages % displayPostsInRow != 0) && (page == ((permPages / displayPostsInRow) + 1))))
							{
								pageNumberToBeDisplayed = 0;
								tempStartFlag = 1;
							}
							
							if(tempStartFlag != 1)
							{
								pageNumberToBeDisplayed = ((page*displayPostsInRow)+i + 1);
								innerRow.add("PAGE " + pageNumberToBeDisplayed);
							}
							else
							{
								pageNumberToBeDisplayed = ((pageNumberToBeDisplayedTemp*displayPostsInRow)+i + 1);
								//pageNumberToBeDisplayed = pageNumberToBeDisplayed + 1;
								innerRow.add("PAGE " + pageNumberToBeDisplayed);
								
							}
							
							innerData.add(innerRow);
							td = new TabularData(innerData);
							td.setStyles(nameVo);
							row.add(td);
						}
	
						innerData = new ArrayList();
	
						innerRow = new ArrayList();
						innerRow.add("Total");
						innerData.add(innerRow);
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
	
						for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
							row.add(" ");
						}
	
						row.add(" ");
						/*
						 * logger.info("rao is "+row );
						 * logger.info("manish -3 size is "+row.size());
						 */
	
						DataList.add(headerRow);
						DataList.add(row);
	
						// to display row 3 remarks
	
						row = new ArrayList();
						row.add("SL NO");
						row.add("Remarks");
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
	
						//logger.info("POST COUNT HERE IS " + postCount);
	
						for (int i = 0; i < postsInRow + 3; i++) {
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
									innerRow.add(allowEdpList.get(k)
											.getRltBillTypeEdp().getEdpShortName().toUpperCase());
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
									// started manish
	
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
								//logger.info("size of paybill vo list is "+ paybillVoList.size());
								//logger.info("post count asdfasdfa uis " + postCount);
								paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
	
								innerData = new ArrayList();
								emptotAllow[i - 2] = paybillVo.getBasic0101();
								totalAllow[0] += paybillVo.getBasic0101();
	
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
											+ allowEdpList.get(p).getRltBillTypeEdp()
													.getEdpCode().trim();
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(paybillVo,
											null);
									innerRow.add(value);
	
									innerData.add(innerRow);
									empTotal += value;
	
									totalAllow[p + 1] += value;
	
								}
								emptotAllow[i - 2] += empTotal;
								td = new TabularData(innerData);
								td.setStyles(numberDispalyVO);
								row.add(td);
	
								postCount++;
							}
	
						}
	
						//logger.info("nulcoli=umn is " + nullColumns);
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
	
						// //for row 5
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
						DataList.add(row);
				
						// /before Gross Logic Starts
						row = new ArrayList();
						double emptotGrossLoan[] = new double[postsInRow + 1];
						double totalGrossLoan[] = new double[loanListGross.size()];
						
						postCount = rollBackCount;
						
						for (int i = 0; i < postsInRow + 3; i++) {
	
							
	
							// manish started
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
									innerRow.add(loanListGross.get(k)
											.getRltBillTypeEdp().getEdpShortName().toUpperCase());
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
								
	
								if (paybillVo.getHrEisEmpMst() != null
										) {
									// logger.info("column no = "+(i-1)+" post count "+postCount);
									// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
									innerData = new ArrayList();
									double empTotal = 0;
									for (int p = 0; p < loanListGross.size(); p++) {
										innerRow = new ArrayList();
										HrPayEdpCompoMpg edpCompoMpg = loanListGross
												.get(p);
										String method = "";
										if (edpCompoMpg.getCmnLookupId() == 2500137) {
											method = "getLoan"
													+ edpCompoMpg.getRltBillTypeEdp()
															.getEdpCode().trim();
										} else {
											method = "getAdv"
													+ edpCompoMpg.getRltBillTypeEdp()
															.getEdpCode().trim();
										}
										Class pay = paybillVo.getClass();
										Method payMthd = pay.getMethod(method, null);
										double value = (Double) payMthd.invoke(
												paybillVo, null);
	/*
										if (edpCompoMpg.getCmnLookupId() == 2500137) {
											method = "getLoanInt"
													+ edpCompoMpg.getRltBillTypeEdp()
															.getEdpCode().trim();
											pay = paybillVo.getClass();
											payMthd = pay.getMethod(method, null);
											value += (Double) payMthd.invoke(paybillVo,
													null);
										}*/
										innerRow.add(value);
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
									} else {
	
										row.add(" ");
									}
	
								}
								else if(postPageFlag[flagCounter] == true)
								{
									row.add("");
								}
								else {
									i--;
								}
								flagCounter++;
								postCount++;
							}
	
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
							row.add("");
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
							dataStyle1.setData(emptotAllow[i] - emptotGrossLoan[i]);
							row.add(dataStyle1);
	
							// row.add(emptotAllow[i]-(paybillVo.getAdv5059()+paybillVo.getAdvInt5059()));
							// emptotAllow[i]= emptotAllow[i]-(paybillVo.getAdv5059());
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
						row.add("GPF/DCPS A/c No.");
						for (int gaa = 0; gaa < postsInRow; gaa++) {
	
							HrPayGpfBalanceDtls gpfDtl =null;
							if(paybillVo.getHrEisEmpMst() != null)
							{
							long userId = paybillVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
							GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(
									HrPayGpfBalanceDtls.class);
							genDao.setSessionFactory(serv.getSessionFactory());
							gpfDtl =  (HrPayGpfBalanceDtls) genDao
									.read(userId);
							}
							if (gpfDtl != null && gpfDtl.getGpfAccNo() != null
									&& !gpfDtl.getGpfAccNo().trim().equals("123")) {
								if(gpfDtl.getGpfAccNo().contains("null"))
								{	
									//row.add(gpfNew);
									row.add("");
								}
								else
								{
									//row.add(gpfDtl.getGpfAccNo());
									row.add("");
								}
								
							} else {
								row.add(" ");
							}
	
							// paybillVo =(HrPayPaybill) paybillVoList.get(postCount);
							// long userId =
							// paybillVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
							// fatch gpf from userId
							// //adde remark information
	
						}
	
						row.add(" ");
						for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
							row.add(" ");
						}
						row.add(cntLast++);
						// logger.info("manish 4 size is"+row.size());
						DataList.add(row);
	
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
						dataStyle.setData("DEDUCTIONS ADJUSTABLE BY AG ");
						orderdataList.add(dataStyle);
						DataList.add(orderdataList);
	
						// /for row 9
						row = new ArrayList();
						double emptotDeducAg[] = new double[postsInRow + 1];
						double totalDeducAg[] = new double[deducAgEdpList.size()];
						postCount = rollBackCount;
						for (int i = 0; i < postsInRow + 3; i++) {
	
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
									innerRow.add(deducAgEdpList.get(k)
											.getRltBillTypeEdp().getEdpShortName().toUpperCase());
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
	
								innerData = new ArrayList();
								double empTotal = 0;
								for (int p = 0; p < deducAgEdpList.size(); p++) {
									innerRow = new ArrayList();
	
									String method = "getDeduc"
											+ deducAgEdpList.get(p).getRltBillTypeEdp()
													.getEdpCode();
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(paybillVo,
											null);
									innerRow.add(value);
									// logger.info("allowance value ::"+ value);
									innerData.add(innerRow);
									empTotal += value;
	
									totalDeducAg[p] += value;
	
								}
								// innerRow = new ArrayList();
								// innerRow.add(empTotal);
								emptotDeducAg[i - 2] = empTotal;
								// innerData.add(innerRow);
	
								td = new TabularData(innerData);
								td.setStyles(numberDispalyVO);
								row.add(td);
	
								postCount++;
							}
	
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
								row.add(td);
							}
							// ended
	
							else if (i == 1) {
								innerData = new ArrayList();
								for (int k = 0; k < loanList.size(); k++) {
									innerRow = new ArrayList();
									innerRow.add(loanList.get(k).getRltBillTypeEdp()
											.getEdpShortName().toUpperCase());
									innerData.add(innerRow);
								}
								// innerData.add(innerRow);
								td = new TabularData(innerData);
								td.setStyles(nameVo); // td.setStyles(numberDispalyVO);
								row.add(td);
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
								row.add(td);
	
							}
	
							else {
	
								paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
								innerData = new ArrayList();
								double empTotal = 0;
								for (int p = 0; p < loanList.size(); p++) {
									innerRow = new ArrayList();
									String method = "";
									if (loanList.get(p).getCmnLookupId() == 2500136)
										method = "getAdv"
												+ loanList.get(p).getRltBillTypeEdp()
														.getEdpCode();
									else
										method = "getLoan"
												+ loanList.get(p).getRltBillTypeEdp()
														.getEdpCode();
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(paybillVo,
											null);
	
								/*	if (loanList.get(p).getCmnLookupId() == 2500137) {
										method = "getLoanInt"
												+ loanList.get(p).getRltBillTypeEdp()
														.getEdpCode();
										pay = paybillVo.getClass();
										payMthd = pay.getMethod(method, null);
										value += (Double) payMthd.invoke(paybillVo,
												null);
									}*/
	
									innerRow.add(value);
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
								row.add(td);
	
								postCount++;
							}
	
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
						row.add(td);
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
								row.add(td);
							}
							// ended
	
							else if (i == 1) {
								innerData = new ArrayList();
								for (int k = 0; k < deducTyEdpList.size(); k++) {
									innerRow = new ArrayList();
									innerRow.add(deducTyEdpList.get(k)
											.getRltBillTypeEdp().getEdpShortName().toUpperCase());
									innerData.add(innerRow);
								}
								// innerRow = new ArrayList();
								// innerRow.add("Total TRY");
								// innerData.add(innerRow);
								td = new TabularData(innerData);
								td.setStyles(nameVo);
								row.add(td);
							} else if (i == postsInRow + 2)// total logic
							{
								double gt = 0;
	
								// row.add("a");
								innerData = new ArrayList();
								for (int p = 0; p < deducTyEdpList.size(); p++) {
									innerRow = new ArrayList();
									innerRow.add(totalDeducTRY[p]);
									// started manish
	
									// ended manish
									innerData.add(innerRow);
									gt += totalDeducTRY[p];
								}
								// innerRow = new ArrayList();
								// innerRow.add(gt);
								emptotDeducTRY[postsInRow] = gt;
								// innerData.add(innerRow);
								td = new TabularData(innerData);
								td.setStyles(numberDispalyVO);
								row.add(td);
	
							}
	
							else {
	
								paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
	
								innerData = new ArrayList();
								double empTotal = 0;
								for (int p = 0; p < deducTyEdpList.size(); p++) {
									innerRow = new ArrayList();
	
									String method = "getDeduc"
											+ deducTyEdpList.get(p).getRltBillTypeEdp()
													.getEdpCode().trim();
									// logger.info("Method Name is "+method+" value is "+value);
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(paybillVo,
											null);
									//logger.info("Method Name is " + method+ " value is " + value);
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
								row.add(td);
	
								postCount++;
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
						row.add(td);
	
						DataList.add(row);
	
						// total sdeduction TRY
						// manish started for Loan In inner
	
						row = new ArrayList();
						double totalAdv[] = new double[loanListNew.size()];
						postCount = rollBackCount;
						for (int i = 0; i < postsInRow + 3; i++) {
	
							// manish started
							if (i == 0) {
								innerData = new ArrayList();
	
								for (int k = 0; k < loanListNew.size(); k++) {
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
							// ended
	
							else if (i == 1) {
								innerData = new ArrayList();
								for (int k = 0; k < loanListNew.size(); k++) {
									innerRow = new ArrayList();
									innerRow.add(loanListNew.get(k).getRltBillTypeEdp()
											.getEdpShortName().toUpperCase());
									innerData.add(innerRow);
								}
								// innerData.add(innerRow);
									if (innerData.size() > 0) {
									td = new TabularData(innerData);
									td.setStyles(nameVo);
									row.add(td);
								} else {
									row.add("");
								}
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
								if (innerData.size() > 0) {
									td = new TabularData(innerData);
									td.setStyles(numberDispalyVO);
									row.add(td);
								} else {
									row.add("");
								}
	
							}
	
							else {
	
								paybillVo = (HrPayPaybill) paybillVoList.get(postCount);
	
								innerData = new ArrayList();
								double empTotal = 0;
								for (int p = 0; p < loanListNew.size(); p++) {
									innerRow = new ArrayList();
									String method = "";
									if (loanListNew.get(p).getCmnLookupId() == 2500137)
									/*	method = "getLoan"
												+ loanListNew.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();*/
										if(loanListNew.get(p).getTypeId().equals("61"))
										method = "getLoan"
											+ loanListNew.get(p)
													.getRltBillTypeEdp()
													.getEdpCode();
										else
											method = "getLoanInt"
												+ loanListNew.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
												
									else
										method = "getAdv"
												+ loanListNew.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
									Class pay = paybillVo.getClass();
									Method payMthd = pay.getMethod(method, null);
									double value = (Double) payMthd.invoke(paybillVo,
											null);
	
							/*		if (loanListNew.get(p).getCmnLookupId() == 2500137) {
										method = "getLoanInt"
												+ loanListNew.get(p)
														.getRltBillTypeEdp()
														.getEdpCode();
										pay = paybillVo.getClass();
										payMthd = pay.getMethod(method, null);
										value += (Double) payMthd.invoke(paybillVo,
												null);
									}
	*/
									innerRow.add(value);
									// logger.info("allowance value ::"+ value);
									innerData.add(innerRow);
									empTotal += value;
	
									totalAdv[p] += value;
	
								}
								emptotDeducTRY[i - 2] += empTotal;
								// innerData.add(innerRow);
									if (innerData.size() > 0) {
									td = new TabularData(innerData);
									td.setStyles(numberDispalyVO);
									row.add(td);
								} else {
									row.add("");
								}
								postCount++;
							}
	
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
						if (innerData.size() > 0) {
							td = new TabularData(innerData);
							td.setStyles(nameVo);
							row.add(td);
						} else {
							row.add("");
						}
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
							row.add(dataStyle);
						}
						for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
	
							row.add(" ");
						}
						// logger.info("length of row is "+row.size());
						row.add(cntLast++);
						// logger.info("manish 9 size is"+row.size());
						DataList.add(row);
						// for row 13 NET
	
						row = new ArrayList();
						row.add(cntFirst++);
						row.add("NET");
						pageTotal = 0;
						for (int h = 0; h < postsInRow + 1; h++) {
							dataStyle = new StyledData();
							//logger.info("emp allow total at Page = " + emptotAllow[h]);
							pageTotal = emptotAllow[h]
									- (emptotDeducAg[h] + emptotDeducTRY[h] + emptotGrossLoan[h]);
							dataStyle.setData(pageTotal);
							dataStyle.setStyles(boldStyleVO);
							row.add(dataStyle);
						}
						for (int nulcol = 0; nulcol < nullColumns; nulcol++) {
	
							row.add(" ");
						}
						// logger.info("length of row is "+row.size());
						row.add(cntLast++);
						// logger.info("manish 10 size is"+row.size());
						DataList.add(row);
	
						endReportTotal += pageTotal;
						
						cntLast = 1;
						cntFirst = 1;
						
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
							row.add("");
	
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
							row.add("");
							// logger.info("manish 11 size is"+row.size());
	
							DataList.add(row);
							// logger.info("datalist  final "+DataList);
	
						}
	
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
			if (report.getReportCode().equals("5000033"))

			{
				report.setParameterValue("Year", yr);
				report.setParameterValue("Month", month);
				report.setParameterValue("Department", locationId + "");
				// added by ravysh
				// report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
				report.setParameterValue("billTypePara", resourceBundle
						.getString("OnlyPaybill"));
			}

			if (report.getReportCode().equals("5000033"))

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

