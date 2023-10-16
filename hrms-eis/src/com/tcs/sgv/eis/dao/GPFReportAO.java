package com.tcs.sgv.eis.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class GPFReportAO extends DefaultReportDataFinder implements ReportDataFinder
{
	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(GPFReportAO.class);

	private StyleVO[] selfCloseVO = null;
	private ResultSet lRs1 = null;

	final String CheckIfNull(Object lStra)
	{
		String lStrb = "";
		if (lStra != null)
		{
			lStrb = (((String) lStra).trim());

		}
		return lStrb;
	}

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException
	{

		String langName = report.getLangId();
		int finalpagesize = 20;
		String locId = report.getLocId();
		/*Connection lCon = null;
		Statement lStmt = null;*/

		Map requestKeys = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap.get("locationVO");
		String locationName = locationVO.getLocName();
		long locationId = locationVO.getLocId();
		String locationNameincaps = locationName.toUpperCase();
		/*ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");*/

		ArrayList DataList = new ArrayList();

		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO();
		baseFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		baseFont[0].setStyleValue("13");

		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER, baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT, baseFont);
		report.setReportTemplate(rt);

		ReportAttributeVO ravo1 = new ReportAttributeVO();
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
		report.addReportAttributeItem(ravo1);
		report.setAdditionalHeader("");

		StyleVO[] styleVOPgBrk = null;
		styleVOPgBrk = new StyleVO[2];

		styleVOPgBrk[0] = new StyleVO();
		styleVOPgBrk[0].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		styleVOPgBrk[0].setStyleValue("yes");
		styleVOPgBrk[0] = new StyleVO();

		styleVOPgBrk[1] = new StyleVO();
		styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
		styleVOPgBrk[1].setStyleValue("yes");

		report.addReportStyles(styleVOPgBrk);
		logger.info("EmployeeLoanHistroyDAO111");

		try
		{

			//Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			//ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			//SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			/*Session session = sessionFactory.getCurrentSession();
			Session hibSession = sessionFactory.getCurrentSession();*/
			ServiceLocator serv = (ServiceLocator) requestKeys.get("serviceLocator");

			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			StyledData dataStyle = null;

			StyleVO[] colorStyleVO = new StyleVO[1];
			colorStyleVO[0] = new StyleVO();
			colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			colorStyleVO[0].setStyleValue("blue");
			selfCloseVO = new StyleVO[1];
			selfCloseVO[0] = new StyleVO();
			selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
			selfCloseVO[0].setStyleValue("javascript:self.close()");

			/*lCon = DBConnection.getConnection(  );
			lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);*/

			StyleVO[] leftHeader = new StyleVO[3];
			leftHeader[0] = new StyleVO();
			leftHeader[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			leftHeader[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			leftHeader[1] = new StyleVO();
			leftHeader[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			leftHeader[1].setStyleValue("10");
			leftHeader[2] = new StyleVO();
			leftHeader[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			leftHeader[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);

			StyleVO[] rightHead = new StyleVO[3];
			rightHead[0] = new StyleVO();
			rightHead[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			rightHead[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			rightHead[1] = new StyleVO();
			rightHead[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			rightHead[1].setStyleValue("10");
			rightHead[2] = new StyleVO();
			rightHead[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rightHead[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);

			StyleVO[] headerStyleVo = new StyleVO[4];
			headerStyleVo[0] = new StyleVO();
			headerStyleVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			headerStyleVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			headerStyleVo[1] = new StyleVO();
			headerStyleVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			headerStyleVo[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			headerStyleVo[2] = new StyleVO();
			headerStyleVo[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			headerStyleVo[2].setStyleValue("14");
			headerStyleVo[3] = new StyleVO();
			headerStyleVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
			headerStyleVo[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);

			StyleVO[] centerboldStyleVO1 = new StyleVO[3];
			centerboldStyleVO1[0] = new StyleVO();
			centerboldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			centerboldStyleVO1[1] = new StyleVO();
			centerboldStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO1[1].setStyleValue("Center");
			centerboldStyleVO1[2] = new StyleVO();
			centerboldStyleVO1[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			centerboldStyleVO1[2].setStyleValue("10");

			if (report.getReportCode().equals("5000010"))
			{

				StringBuffer lsb = new StringBuffer();

				String portType = "";
				String BillNo = "";
				String Department = "";
				String month = "";
				String year = "";
				String ReportType = "";

				String PFSeries = CheckIfNull(report.getParameterValue("Series"));

				logger.info("PFSeries***********************" + PFSeries);

				Department = CheckIfNull(report.getParameterValue("Department"));
				if (Department.equals("") || Department.equals("-1"))
					Department = locationId + "";
				else
					locationId = Long.parseLong(Department);

				String BillNoinner = "";//GAD specific
				BillNoinner = CheckIfNull(report.getParameterValue("BillNo"));
				StringTokenizer st1 = new StringTokenizer(BillNoinner, "~");
				int l = 0;
				String subheadCode = "";
				String classIds = "";
				String desgnIds = "";

				while (st1.hasMoreTokens())
				{
					if (l == 0)
						subheadCode = st1.nextToken();
					else if (l == 1)
						classIds = st1.nextToken();
					else if (l == 2)
						desgnIds = st1.nextToken();
					else if (l == 3)
						portType = st1.nextToken();
					else if (l == 4)
						BillNo = st1.nextToken();
					l++;
				}

				//Added by abhilash	

				logger.info("bill no is ***********" + BillNo);
				logger.info("bill no is ***********" + BillNoinner);
				logger.info("bill no is ***********" + report.getParameterValue("BillNo"));

				if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase(""))
				{
					logger.info("inside if condition bill no is ***********" + report.getParameterValue("BillNo"));
					BillNo = report.getParameterValue("BillNo") != null ? report.getParameterValue("BillNo").toString().trim() : "";
				}
				//Ended

				String DeptName = locationNameincaps;

				PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				DeptName = payBillDAOImpl.getOffice(locationId);

				GPFReportDAO reportDAO = new GPFReportDAOImpl(HrPayPaybill.class);
				reportDAO.setSessionFactory(serv.getSessionFactory());

				String billType = "2500337";

				month = CheckIfNull(report.getParameterValue("Month"));
				year = CheckIfNull(report.getParameterValue("Year"));

				/*Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				java.util.Date startMonthDate = cal.getTime();
				String startDate = sdfObj.format(startMonthDate);
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				cal.set(Calendar.DAY_OF_MONTH, totalDays);
				java.util.Date endMonthDate = cal.getTime();
				String endDate = sdfObj.format(endMonthDate);
				sdfObj = new SimpleDateFormat("MMM");
				String Month = sdfObj.format(startMonthDate);*/

				//java.util.Calendar calendar = java.util.Calendar.getInstance();
				//int curYear = calendar.get(calendar.YEAR);
				//int curMonth = calendar.get(Calendar.MONTH);
				String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
				Calendar cale = Calendar.getInstance();
				//String MONTH = monthName[cale.get(Calendar.MONTH)];

				Integer in = Integer.parseInt(month);
				String monthname = monthName[in - 1];
				String ClassType = CheckIfNull(report.getParameterValue("Class Type"));

				long ClassIVGradeId = 100067;

				long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
				//List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);

				/*String ddocode ="";
				if(ddoList.size()>0)
					ddocode = ddoList.get(0).getDdoCode();*/
				String ddocode = "";
				String TanNo = "";
				long locactionId = Long.parseLong(baseLoginMap.get("locationId").toString());
				PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);

				OrgDdoMst ddoMst = null;
				if (ddoCodeList != null && ddoCodeList.size() > 0)
				{
					logger.info("After query execution for DDO Code " + ddoCodeList.size());
					ddoMst = ddoCodeList.get(0);
					if (ddoMst != null)
					{
						ddocode = ddoMst.getDdoCode();
						TanNo = ddoMst.getTanNo();
					}
				}

				logger.info("DDO Code is " + ddocode);
				
				//added by roshan
				logger.info("hhii the bill number"+BillNo);
				ddocode=payBillDAOImpl.getddoCodeForBillId(BillNo);
				logger.info("ddocode is for bill number "+ddocode);
				locationId=payBillDAOImpl.getLocationCode(Long.parseLong(BillNo));
				logger.info("hhi the location cxod eis "+locationId);
				//ended by roshan
				
				String TresuryName = "";
				String TresuryCode = "";
				List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);

				if (TreasuryList != null && TreasuryList.size() != 0)
				{
					for (Iterator itr = TreasuryList.iterator(); itr.hasNext();)
					{

						Object[] rowList = (Object[]) itr.next();

						if (rowList[0] != null)
						{
							TresuryName = rowList[0].toString().trim();
						}
						if (rowList[1] != null)
						{
							TresuryCode = rowList[1].toString().trim();
						}

					}
				}

				String deptHeader = "";

				String Title = "";

				if (ClassType.equals("ClassIV"))
				{
					Title = "Schedule showing the Subscriptions and Refund of the GPF(CLASS IV) for following Government Servants";
				}
				else
				{
					Title = "Schedule showing the Subscriptions and Refund of the GPF for following Government Servants";
				}
				String MajorHead = "8009";

				if (ClassType.equals("ClassIV"))
				{

					deptHeader = Title + System.getProperty("line.separator") + "From Major Head " + MajorHead;

				}
				else
				{

					deptHeader = Title + System.getProperty("line.separator") + "From Major Head " + MajorHead;

				}

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				StyledData styledHeader = new StyledData(deptHeader, headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				String Treasury = TresuryCode;
				styleList.add(stData);
				ArrayList r = new ArrayList();
				r.add(new StyledData("For the Month of " + monthname + " " + year, leftHeader));
				r.add(new StyledData("Treasury : " + TresuryName + "(" + Treasury + ")", rightHead));
				styleList.add(r);

				if (!ClassType.equals("ClassIV"))
				{
					ArrayList r2 = new ArrayList();
					r2.add(new StyledData("AG Office : " + "A. G. Mumbai", leftHeader));
					r2.add(new StyledData(" ", rightHead));
					styleList.add(r2);

				}

				ArrayList r3 = new ArrayList();
				r3.add(new StyledData("Name of the Office : " + DeptName + "(" + ddocode + ")	", leftHeader));
				r3.add(new StyledData(" ", leftHeader));

				styleList.add(r3);

				TabularData tData = new TabularData(styleList);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);

				List RowList = null;
				List RowListOne =null;

				//List RowListTwo = new ArrayList();

				long totalIncomeTax = 0;
				if (ClassType.equals("ClassIV"))
				{

					logger.info("Inside ClassIV**********");

					//RowList = reportDAO.getGpfForClassFourTotalDtls(BillNo, month, year, locationId,PFSeries);
					RowList = reportDAO.getGpfTotalDtls(BillNo, month, year, locationId, PFSeries, 55);
					if (RowList != null && RowList.size() > 0)
					{
						ArrayList dataList = new ArrayList();
						int cnt = 1;

						Iterator itr = RowList.iterator();
						String GPFActNo;
						String employeeName;
						long PayDAArrMerge = 0;
						long GPFArr = 0;
						long SVNPCGPFArr = 0;
						long SVNPCGPFREC = 0 ;
						long CurrIn = 0;
						double RefundAmount = 0;
						double Total = 0;
						double Pay = 0;
						double PE = 0;
						double PO = 0;
						double paydp = 0;
						double GrandTotal = 0;
						double SubScribe = 0;
						double GrandSubScribe = 0;
						double GrandRefundAmount = 0;
						int pageCnt = 1;

						long TotalInst = 0;
						long CurrInst = 0;
						long empUserIdFor = 0;

						String sevaarthCode = "";

						String empDateOfJoining = "2005-11-01";

						while (itr.hasNext())
						{
							GPFArr = 0;
							Object[] rowList = (Object[]) itr.next();
							GPFActNo = (rowList[0] != null ? rowList[0].toString() : "").toString();
							employeeName = (rowList[1] != null ? rowList[1] : "").toString();
							PO = Double.parseDouble((rowList[2] != null ? rowList[2] : "0").toString());
							PE = Double.parseDouble((rowList[3] != null ? rowList[3] : "0").toString());

							double DP = Double.parseDouble((rowList[4] != null ? rowList[4] : "0").toString());
							SubScribe = Double.parseDouble((rowList[5] != null ? rowList[5] : "0").toString());
							RefundAmount = Double.parseDouble((rowList[7] != null ? rowList[7] : "0").toString());

							ArrayList row = new ArrayList();

							//logger.info("PE**********" + (rowList[2] != null ? rowList[2] : "0").toString());
							//logger.info("P0**********" + (rowList[3] != null ? rowList[3] : "").toString());

							String empdoj = rowList[8].toString();
							//logger.info("empdoj***before split***" + rowList[8]);

							String str[] = empdoj.split(" ");
							String doj = str[0];
							//logger.info("employeeDateOfJoining** after split****" + doj);

							if (empDateOfJoining.equals(doj))
							{
								GPFActNo = "";

							//	logger.info("INSIDE IF");
							//	logger.info("GPFActNo********* " + GPFActNo);
							}
							else
							{
								GPFActNo = PFSeries + "/" + GPFActNo;

								//logger.info("ELSE PART");
								//logger.info("GPFActNo********* " + GPFActNo);
							}

							long empUserId = Long.parseLong(rowList[9].toString());
							//logger.info("empUserIdempUserId****" + empUserId);

							if (rowList[10] != null)
							{
								sevaarthCode = rowList[10].toString();
							}
							else
							{
								sevaarthCode = " ";
							}

							employeeName = employeeName + "(" + sevaarthCode + ")";

							TotalInst = rowList[12] != null ? Long.parseLong(rowList[12].toString()) : 0;
							CurrInst = rowList[13] != null ? Long.parseLong(rowList[13].toString()) : 0;

							/*RowList = reportDAO.getGpfForClassFour(BillNo, month, year, locationId,empUserId);
							Iterator itronelist = RowList.iterator();
							
							while(itronelist.hasNext())
							{

								Object[] rowListTwo = (Object[])itronelist.next();

								if(rowListTwo[8] != null)
								{
									TotalInst =Long.parseLong(rowListTwo[8].toString().trim());
									logger.info("TotalInst***********"+TotalInst);
								}
								if(rowListTwo[9] != null)
								{
									CurrInst = Long.parseLong(rowListTwo[9].toString().trim());
									logger.info("CurrInst***********"+CurrInst);
								}
								if(rowListTwo[1] != null)
								{
									empUserIdFor = Long.parseLong(rowListTwo[11].toString().trim());
									logger.info("empUserIdFor***********"+empUserIdFor);
								}

							}
							
							
							
							if(empUserId==empUserIdFor)
							{
								TotalInst=TotalInst;
								CurrInst=CurrInst;
								
							}
								
							else
							{
								TotalInst=0;
								CurrInst=0;
							}*/
							GPFArr = rowList[15] != null ? Long.parseLong(rowList[15].toString()) : 0;
							Pay = PE + PO;
							
							SVNPCGPFArr = rowList[16] != null ? Long.parseLong(rowList[16].toString()) : 0;
							SVNPCGPFREC = rowList[17] != null ? Long.parseLong(rowList[17].toString()) : 0;
							long PAY = (new Double(Pay)).longValue();
							long dp = (new Double(DP)).longValue();

							Total = SubScribe + DP + PayDAArrMerge + GPFArr + RefundAmount+SVNPCGPFArr+SVNPCGPFREC;
							String Remarks = "";

							String space = "    ";

							StringBuffer stringBuffer = new StringBuffer();

							stringBuffer.append(PAY);
							stringBuffer.append(space);
							stringBuffer.append(dp);

							GrandSubScribe += SubScribe;
							GrandRefundAmount += RefundAmount;
							GrandTotal += Total;

							row.add(cnt);
							row.add(GPFActNo);
							row.add(employeeName);
							row.add(stringBuffer);
							row.add(SubScribe);
							row.add(PayDAArrMerge);
							row.add(GPFArr);
							row.add(SVNPCGPFArr);
							row.add(SVNPCGPFREC);
							row.add(RefundAmount);
							row.add("" + CurrInst + "/" + TotalInst + "");
							row.add(Total);

							StyledData dataStyle11 = new StyledData();
							if (Remarks.equals(""))
							{
								dataStyle11 = new StyledData();
								dataStyle11.setStyles(colorStyleVO);
								dataStyle11.setData(" ");
								row.add(dataStyle11);
							}

							String noOfRec = CheckIfNull(report.getParameterValue("No of Records"));
							if (!noOfRec.equalsIgnoreCase("") && !noOfRec.equalsIgnoreCase("-1"))
							{
								//logger.info("No Of Rec is*****************====>"+noOfRec);
								finalpagesize = Integer.parseInt(noOfRec);
							}

							DataList.add(row);
							if ((cnt % finalpagesize) == 0)
							{

								pageCnt++;
								row = new ArrayList();
								row.add(new PageBreak());
								row.add("Data");
								DataList.add(row);

								StyledData dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(GrandTotal);
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(pageCnt);
								row.add(dataStyle1);//2

							}
							cnt++;
						}
						ArrayList row2 = new ArrayList();

						StyledData dataStyle1 = new StyledData();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData("TOTAL (`):");
						dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						row2.add(dataStyle1);
						row2.add("");
						row2.add("");
						row2.add("");
						
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(GrandSubScribe);
						row2.add(dataStyle1);
						row2.add("");
						row2.add("");
						row2.add("");
						row2.add("");
						StyledData dataStyle22 = new StyledData();
						dataStyle22 = new StyledData();
						dataStyle22.setStyles(boldStyleVO);
						dataStyle22.setData(GrandRefundAmount);
						row2.add(dataStyle22);
						row2.add("");

						StyledData dataStyle3 = new StyledData();
						dataStyle3 = new StyledData();
						dataStyle3.setStyles(boldStyleVO);
						dataStyle3.setData(GrandTotal);
						row2.add(dataStyle3);
						row2.add("");

						DataList.add(row2);
						ArrayList row1 = new ArrayList();

						logger.info("TOTALLLLLLLLLLLLLLL");

						StyleVO[] centerboldStyleVO = new StyleVO[2];
						centerboldStyleVO[0] = new StyleVO();
						centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
						centerboldStyleVO[1] = new StyleVO();
						centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						centerboldStyleVO[1].setStyleValue("Left");

						StyleVO[] leftBoldStyleVO = new StyleVO[2];
						leftBoldStyleVO[0] = new StyleVO();
						leftBoldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						leftBoldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
						leftBoldStyleVO[1] = new StyleVO();
						leftBoldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						leftBoldStyleVO[1].setStyleValue("Left");

						StyledData dataStyle2 = new StyledData();
						dataStyle2.setStyles(leftBoldStyleVO);
						dataStyle2.setData("Total Deduction In Words (`) :  " + ConvertNumbersToWord.convert(Math.round(GrandTotal)) + " Only.");
						dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						dataStyle2.setColspan(11);
						row1.add(dataStyle2);

						for (int c = 0; c < 13; c++)
							row1.add("");

						DataList.add(row1);

					}

				}
				else
				{

					logger.info("Otherthan ClassIV**********");

					//RowListOne = reportDAO.getGpfForOtherThanClassFourTotalDtls(BillNo, month, year, locationId,PFSeries);
					RowListOne = reportDAO.getGpfTotalDtls(BillNo, month, year, locationId, PFSeries, 54);

					if (RowListOne != null && RowListOne.size() > 0)
					{

						ArrayList dataList = new ArrayList();
						int cnt = 1;

						Iterator itrOne = RowListOne.iterator();

						String GPFActNo;
						String employeeName;
						long PayDAArrMerge = 0;
						long GPFArr = 0;
						long SVNPCGPFARR =0 ;
						long SVNPCGPFREC =0 ;
						long CurrIn = 0;
						double RefundAmount = 0;
						double Total = 0;
						double Pay = 0;
						double PE = 0;
						double PO = 0;
						double paydp = 0;
						double GrandTotal = 0;
						double SubScribe = 0;
						double GrandSubScribe = 0;
						double GrandRefundAmount = 0;
						String sevaarthCode = "";
						int pageCnt = 1;

						long TotalInst = 0;
						long CurrInst = 0;
						long empUserIdFor = 0;

						String empDateOfJoining = "2005-11-01";

						while (itrOne.hasNext())
						{
							GPFArr = 0;
							Object[] rowList = (Object[]) itrOne.next();
							GPFActNo = (rowList[0] != null ? rowList[0].toString() : "").toString();
							employeeName = (rowList[1] != null ? rowList[1] : "").toString();
							PO = Double.parseDouble((rowList[2] != null ? rowList[2] : "0").toString());
							PE = Double.parseDouble((rowList[3] != null ? rowList[3] : "0").toString());

							double DP = Double.parseDouble((rowList[4] != null ? rowList[4] : "0").toString());
							SubScribe = Double.parseDouble((rowList[5] != null ? rowList[5] : "0").toString());
							RefundAmount = Double.parseDouble((rowList[7] != null ? rowList[7] : "0").toString());

							//String TotalInst = (rowList[8]!=null?rowList[8]:"").toString();
							//String CurrInst = (rowList[9]!=null?rowList[9]:"").toString();

							ArrayList row = new ArrayList();

							//logger.info("PE**********" + (rowList[2] != null ? rowList[2] : "0").toString());
							//logger.info("P0**********" + (rowList[3] != null ? rowList[3] : "").toString());

							String empdoj = rowList[8].toString();
							//logger.info("empdoj***before split***" + rowList[8]);

							String str[] = empdoj.split(" ");
							String doj = str[0];
							//logger.info("employeeDateOfJoining** after split****" + doj);

							if (empDateOfJoining.equals(doj))
							{
								GPFActNo = "";

								//logger.info("INSIDE IF");
								//logger.info("GPFActNo********* " + GPFActNo);
							}
							else
							{
								GPFActNo = PFSeries + "/" + GPFActNo;

								//logger.info("ELSE PART");
								//logger.info("GPFActNo********* " + GPFActNo);
							}

							long empUserId = Long.parseLong(rowList[9].toString());
							//logger.info("empUserIdempUserId****" + empUserId);

							if (rowList[10] != null)
							{
								sevaarthCode = rowList[10].toString();
							}
							else
							{
								sevaarthCode = " ";
							}

							employeeName = employeeName + "(" + sevaarthCode + ")";

							TotalInst = rowList[12] != null ? Long.parseLong(rowList[12].toString()) : 0;
							CurrInst = rowList[13] != null ? Long.parseLong(rowList[13].toString()) : 0;
							
							GPFArr = rowList[15] != null ? Long.parseLong(rowList[15].toString()) : 0;
							SVNPCGPFARR = rowList[16] != null ? Long.parseLong(rowList[16].toString()) : 0;
							SVNPCGPFREC = rowList[17] != null ? Long.parseLong(rowList[17].toString()) : 0;

							/*while (itr.hasNext())
							{
								Object[] rowListone = (Object[]) itr.next();
								String TotalInst = (rowListone[8]!=null?rowListone[8]:"").toString();
								String CurrInst = (rowListone[9]!=null?rowListone[9]:"").toString();
								
								String empUserIdFor= rowListone[9].toString();
								System.out.println("empUserIdempUserIdFor ayyyoooooooo****" +empUserIdFor);
								
							}*/

							//List employeeIdUserList = new ArrayList();
							//RowList = payBillDAOImpl.getGpfForOtherThanClassFour(BillNo, month, year, locationId);
							//for(Iterator itronelist=RowList.iterator();itronelist.hasNext();)
							/*RowList = reportDAO.getGpfForOtherThanClassFour(BillNo, month, year, locationId,empUserId);
							Iterator itronelist = RowList.iterator();
							
							while(itronelist.hasNext())
							{

								Object[] rowListTwo = (Object[])itronelist.next();

								if(rowListTwo[8] != null)
								{
									TotalInst = Long.parseLong(rowListTwo[8].toString().trim());
									logger.info("TotalInst***********"+TotalInst);
								}
								if(rowListTwo[9] != null)
								{
									CurrInst = Long.parseLong(rowListTwo[9].toString().trim());
									logger.info("CurrInst***********"+CurrInst);
								}
								if(rowListTwo[1] != null)
								{
									empUserIdFor = Long.parseLong(rowListTwo[11].toString().trim());
									logger.info("empUserIdFor***********"+empUserIdFor);
								}

							}
							
							
							
							if(empUserId==empUserIdFor)
							{
								TotalInst=TotalInst;
								CurrInst=CurrInst;
								
							}
								
							else
							{
								TotalInst=0;
								CurrInst=0;
							}*/

							//SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms");

							Pay = PE + PO;

							long PAY = (new Double(Pay)).longValue();
							long dp = (new Double(DP)).longValue();

							Total = SubScribe + DP + PayDAArrMerge + GPFArr + RefundAmount+SVNPCGPFARR+SVNPCGPFREC;;
							String Remarks = "";

							String space = "    ";

							StringBuffer stringBuffer = new StringBuffer();

							stringBuffer.append(PAY);
							stringBuffer.append(space);
							stringBuffer.append(dp);

							GrandSubScribe += SubScribe;
							GrandRefundAmount += RefundAmount;
							GrandTotal += Total;

							row.add(cnt);
							row.add(GPFActNo);
							row.add(employeeName);
							row.add(stringBuffer);
							row.add(SubScribe);
							row.add(PayDAArrMerge);
							row.add(GPFArr);
							row.add(SVNPCGPFARR);
							row.add(SVNPCGPFREC);
							
							row.add(RefundAmount);
							row.add("" + CurrInst + "/" + TotalInst + "");
							row.add(Total);

							StyledData dataStyle11 = new StyledData();
							if (Remarks.equals(""))
							{
								dataStyle11 = new StyledData();
								dataStyle11.setStyles(colorStyleVO);
								dataStyle11.setData(" ");
								row.add(dataStyle11);
							}

							String noOfRec = CheckIfNull(report.getParameterValue("No of Records"));
							if (!noOfRec.equalsIgnoreCase("") && !noOfRec.equalsIgnoreCase("-1"))
							{
								//logger.info("No Of Rec is*****************====>"+noOfRec);
								finalpagesize = Integer.parseInt(noOfRec);
							}

							DataList.add(row);
							if ((cnt % finalpagesize) == 0)
							{

								pageCnt++;
								row = new ArrayList();
								row.add(new PageBreak());
								row.add("Data");
								DataList.add(row);

								StyledData dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(GrandTotal);
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(pageCnt);
								row.add(dataStyle1);//2

							}
							cnt++;
						}
						ArrayList row2 = new ArrayList();

						StyledData dataStyle1 = new StyledData();
						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData("TOTAL (`)");
						dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						row2.add(dataStyle1);
						row2.add("");
						row2.add("");
						row2.add("");
						

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(GrandSubScribe);
						row2.add(dataStyle1);
						row2.add("");
						row2.add("");
						row2.add("");
						row2.add("");
						StyledData dataStyle22 = new StyledData();
						dataStyle22 = new StyledData();
						dataStyle22.setStyles(boldStyleVO);
						dataStyle22.setData(GrandRefundAmount);
						row2.add(dataStyle22);
						row2.add("");

						StyledData dataStyle3 = new StyledData();
						dataStyle3 = new StyledData();
						dataStyle3.setStyles(boldStyleVO);
						dataStyle3.setData(GrandTotal);
						row2.add(dataStyle3);
						row2.add("");

						DataList.add(row2);
						ArrayList row1 = new ArrayList();

						logger.info("TOTALLLLLLLLLLLLLLL");

						StyleVO[] centerboldStyleVO = new StyleVO[2];
						centerboldStyleVO[0] = new StyleVO();
						centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
						centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
						centerboldStyleVO[1] = new StyleVO();
						centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
						centerboldStyleVO[1].setStyleValue("Left");
						StyledData dataStyle2 = new StyledData();
						dataStyle2.setStyles(centerboldStyleVO);
						dataStyle2.setData("Total Deduction In Words (`):  " + ConvertNumbersToWord.convert(Math.round(GrandTotal)) + " Only.");
						dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
						dataStyle2.setColspan(11);
						row1.add(dataStyle2);

						for (int c = 0; c < 13; c++)
							row1.add("");
						DataList.add(row1);

					}

				}

				return DataList;
			}
		}
		catch (Exception e)
		{
			logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			logger.error("Printing StackTrace");
			e.printStackTrace();
		}

		return DataList;

	}

	public ReportVO getUserReportVO(ReportVO report, Object criteria) throws ReportException
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		logger.info("***********Inside User Report VO *********************");
		Hashtable sessionKeys = (Hashtable) ((Hashtable) criteria).get(IReportConstants.SESSION_KEYS);
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String date = fmt.format(today);
		SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 = new SimpleDateFormat("MM");
		Map requestKeys = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map) requestKeys.get("serviceMap");
		Map baseLoginMap = (Map) serviceMap.get("baseLoginMap");
		CmnLocationMst locationVO = (CmnLocationMst) baseLoginMap.get("locationVO");
		String locationName = locationVO.getLocName();
		long locationId = locationVO.getLocId();
		String month = fmt1.format(today);

		if (month.charAt(0) == '0')
		{
			month = month.substring(1, 2);
		}
		if (report.getReportCode().equals("5000010"))

		{
			report.setParameterValue("Year", yr);
			report.setParameterValue("Month", month);
			report.setParameterValue("Department", locationId + "");
			report.setParameterValue("billTypePara", resourceBundle.getString("paybillTypeId"));
		}

		if (report.getReportCode().equals("5000010"))

		{
			report.setParameterValue("Department", locationId + "");
		}
		return report;
	}
}
