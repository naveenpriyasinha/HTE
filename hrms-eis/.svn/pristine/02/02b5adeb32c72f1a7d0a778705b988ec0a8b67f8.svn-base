package com.tcs.sgv.eis.dao;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.deduction.dao.DeducTypeMasterDAOImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.loan.dao.LoanAdvMstDAOImpl;

public class AbstarctReportDAO extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener
{
	Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		ArrayList DataList = new ArrayList();
		ArrayList row = new ArrayList();
		try{

			int displayPostsInRow = 5;
			ReportColumnVO[] newReportColumns = new ReportColumnVO[displayPostsInRow+6];
			
/*			
			newReportColumns[0] = new ReportColumnVO();

			//configure column vo by setting different column properties
			newReportColumns[0].setColumnId(1); 
			newReportColumns[0].setColumnHeader("");
			newReportColumns[0].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[0].setTableName("a");
			newReportColumns[0].setColumnName("asd"+1);
			newReportColumns[0].setDisplayTotal(0); 
			newReportColumns[0].setColumnLevel(1);	
			newReportColumns[0].setColumnWidth(2);
*/
			
			for(int i=0;i<displayPostsInRow+6;i++)
			{						  
				//initialize column vo
				newReportColumns[i] = new ReportColumnVO();

				//configure column vo by setting different column properties
				newReportColumns[i].setColumnId(i+1); 
				newReportColumns[i].setColumnHeader("");
				newReportColumns[i].setDataType(10);
				//newReportColumns[i].setAlignment();
				newReportColumns[i].setTableName("a");
				newReportColumns[i].setColumnName("asd"+i);
				newReportColumns[i].setDisplayTotal(0); 
				newReportColumns[i].setColumnLevel(1);	
				newReportColumns[i].setColumnWidth(11);

				//logger.info("in for "+i);

			}

			report.setReportColumns(newReportColumns);
			report.initializeDynamicTreeModel();
			report.initializeTreeModel();

			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			Map serviceMap = (Map)requestAttributes.get("serviceMap");						
			Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
			long locId = StringUtility.convertToLong(baseLoginMap.get("locationId").toString());
			ServiceLocator serv = (ServiceLocator)requestAttributes.get("serviceLocator");

			long billNo=0; 
			long  month = StringUtility.convertToLong(report.getParameterValue("Month")!=null?report.getParameterValue("Month").toString():"-1");
			int Month = (int) month;
			//logger.info("Muni is checking for month:::::::::::::: "+ month);
			long year = StringUtility.convertToLong(report.getParameterValue("Year")!=null?report.getParameterValue("Year").toString():"0");
			String BillNoinner = report.getParameterValue( "Bill No" )!=null?report.getParameterValue("Bill No").toString():"0";

			logger.info("before while condition BillNoinner no is ***********"+BillNoinner);
			
			int l=0;
			logger.info(BillNoinner);
			StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
			while(st1.hasMoreTokens())
			{
				if(l==4)
				{
					billNo = StringUtility.convertToLong(st1.nextToken());
					logger.info("inside while condition bill no is ***********"+billNo);
				}
				else
				{
					st1.nextToken();
				}
				l++;
			} 


			//Added by abhilash				
			if (BillNoinner.trim().equals("") || billNo == 0 )
			{
				logger.info("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
				billNo = report	.getParameterValue("Bill No") != null ?Long.parseLong(report.getParameterValue("Bill No").toString().trim()) :0;
			}
			//Ended
			//added by roshan
			logger.info("hhii the bill number"+billNo);
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			locId=payBillDAO.getLocationCode(billNo);
			logger.info("hhi the location cxod is "+locId);
			//ended by roshan
			ReportAttributeVO ravo = new ReportAttributeVO();


			//Added by Abhilash

			StyleVO[] styleVOPgBrk=null;
			styleVOPgBrk = new StyleVO[2];
			styleVOPgBrk[0] = new StyleVO();
			styleVOPgBrk[0].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
			styleVOPgBrk[0].setStyleValue("yes");
			styleVOPgBrk[0] = new StyleVO();

			styleVOPgBrk[1] = new StyleVO();
			styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
			styleVOPgBrk[1].setStyleValue("yes");
			report.addReportStyles(styleVOPgBrk);

			StyleVO[] headerStyleVo = new StyleVO[4];
			headerStyleVo[0] = new StyleVO();
			headerStyleVo[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			headerStyleVo[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			headerStyleVo[1] = new StyleVO();
			headerStyleVo[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			headerStyleVo[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			headerStyleVo[2] = new StyleVO();
			headerStyleVo[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			headerStyleVo[2].setStyleValue("18");
			headerStyleVo[3] = new StyleVO();
			headerStyleVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
			headerStyleVo[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);
			String deptHeader ="";
			deptHeader="GROUP ABSTRACT";
			List headerRowList=new ArrayList();
			List headerColList=new ArrayList();
			StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
			styledHeader.setColspan(2);
			headerColList=new ArrayList();
			headerColList.add(styledHeader);
			headerRowList.add(headerColList);
			TabularData headerTable=new TabularData(headerRowList);
			headerTable.addStyle(IReportConstants.BORDER, IReportConstants.VALUE_NO);
			headerTable.addStyle(IReportConstants.ALIGN_CENTER, "YES");
			report.setAdditionalHeader(headerTable);

			//int Month = cal.get(Calendar.MONTH);
			String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
			Integer in = (Month-1);
			String monthname = monthName[in];

			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			String DeptName=payBillDAOImpl.getOffice(locId);
			long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
			String dsgnName =payBillDAOImpl.getDsgnNameForFormB(locId,loggedInpostId);

			  Date now = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
			  //System.out.println("10. "+format.format(now));
			  
			ravo = new ReportAttributeVO();
			ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
			ravo.setLocation(IReportConstants.LOCATION_FOOTER);
			ravo.setAlignment(IReportConstants.ALIGN_LEFT);
			ravo.setAttributeValue("SALARY FOR THE MONTH & YEAR :"+monthname +" "+year+"<br>" );
			report.addReportAttributeItem(ravo);

			ravo = new ReportAttributeVO();
			ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
			ravo.setLocation(IReportConstants.LOCATION_FOOTER);
			ravo.setAlignment(IReportConstants.ALIGN_LEFT);
			ravo.setAttributeValue("OFFICE NAME :"+dsgnName+","+DeptName);
			report.addReportAttributeItem(ravo);

			//Ended by Abhilash
			ravo = new ReportAttributeVO();
			ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
			ravo.setLocation(IReportConstants.LOCATION_FOOTER);
			ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
			ravo.setAttributeValue("VERIFICATION TIME :"+ format.format(now)+"<br>" );
			report.addReportAttributeItem(ravo);

			///logic for post display
			ArrayList orderdataList = new ArrayList();
			StyleVO[] centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Center"); 

			StyleVO[] numberDispalyVO = new StyleVO[3];
			numberDispalyVO[0] = new StyleVO();
			numberDispalyVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			numberDispalyVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
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

			HrPayPaybill   paybillVo = null;

			StyleVO[] boldStyleVO = new StyleVO[2];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_500); 
			boldStyleVO[1] = new StyleVO();
			boldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			boldStyleVO[1].setStyleValue("Right");

			///creatin g lists///
			HrEdpComponentMpgDAOImpl edpDao = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class, serv.getSessionFactory());
			DeptCompMPGDAOImpl compDao = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,serv.getSessionFactory());
			DeducTypeMasterDAOImpl deduDao = new DeducTypeMasterDAOImpl(HrPayDeducTypeMst.class,serv.getSessionFactory());
			List<HrPayEdpCompoMpg> allowEdpList = new ArrayList<HrPayEdpCompoMpg>();//edpDao.getAllowCompoMpgData(locId);
			List<HrPayEdpCompoMpg> deducAgEdpList = new ArrayList<HrPayEdpCompoMpg>();//edpDao.getAGDeducCompoMpgData(locId);
			List<HrPayEdpCompoMpg> deducTyEdpList = new ArrayList<HrPayEdpCompoMpg>();//edpDao.getTRDeducCompoMpgData(locId);
			List<HrPayEdpCompoMpg> loanList = new ArrayList<HrPayEdpCompoMpg>();
			List<HrPayEdpCompoMpg> loanListNew = new ArrayList<HrPayEdpCompoMpg>();
			List<HrPayEdpCompoMpg> loanListGross = new ArrayList<HrPayEdpCompoMpg>();

			LoanAdvMstDAOImpl loanDao = new LoanAdvMstDAOImpl(
					HrLoanAdvMst.class, serv.getSessionFactory());
			
			List<HrPayEdpCompoMpg> allEdpList = edpDao.getAllData();

			///get paybillvolist here
			List<HrPayPaybill> paybillVoList = new ArrayList<HrPayPaybill>();
			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<HrPayPaybill>  paybillVoListAll = payDao.getBillData(locId,billNo,month,year);
			for(int i=0;i<5;i++)
			{
				HrPayPaybill pbVo = new HrPayPaybill();
				paybillVoList.add(pbVo);

			}
			for(HrPayPaybill payBillVo: paybillVoListAll)
			{
				int index =0;
				if(payBillVo.getHrEisOtherDtls()!=null && payBillVo.getHrEisOtherDtls().getHrEisSgdMpg()!=null &&payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg()!=null && payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst()!=null)
				{	index = Integer.parseInt(payBillVo.getHrEisOtherDtls().getHrEisSgdMpg().getHrEisGdMpg().getOrgGradeMst().getGradeCode());

				HrPayPaybill paybillVoGrp = paybillVoList.get(index-1);
				Class cls = payBillVo.getClass();
				Method method = null;
				String methodName;
				paybillVoGrp.setBasic0101(paybillVoGrp.getBasic0101()+payBillVo.getBasic0101());
				for(HrPayEdpCompoMpg hrPayEdpCompoMpg:allEdpList)
				{
					if(hrPayEdpCompoMpg.getCmnLookupId()==2500134)
					{
						try{
							methodName = "getAllow"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, null);
							double amt = (Double)method.invoke(payBillVo, null);
							double totAmt = (Double)method.invoke(paybillVoGrp, null);
							methodName = "setAllow"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, double.class);
							method.invoke(paybillVoGrp, (amt+totAmt));
						}
						catch(NoSuchMethodException ne)
						{

						}
					}
					else if(hrPayEdpCompoMpg.getCmnLookupId()==2500135)
					{
						try{
							methodName = "getDeduc"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, null);
							double amt = (Double)method.invoke(payBillVo, null);
							double totAmt = (Double)method.invoke(paybillVoGrp, null);
							methodName = "setDeduc"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, double.class);
							method.invoke(paybillVoGrp, (amt+totAmt));
						}
						catch(NoSuchMethodException ne)
						{

						}
					}
					else if(hrPayEdpCompoMpg.getCmnLookupId()==2500136)
					{
						try{
							methodName = "getAdv"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, null);
							double amt = (Double)method.invoke(payBillVo, null);
							double totAmt = (Double)method.invoke(paybillVoGrp, null);
							methodName = "setAdv"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, double.class);
							method.invoke(paybillVoGrp, (amt+totAmt));
						}
						catch(NoSuchMethodException ne)
						{

						}
					}
					else if (hrPayEdpCompoMpg.getCmnLookupId()==2500137)
					{
						try{
							if(hrPayEdpCompoMpg.getRltBillTypeEdp().getExpRcpRec().equals("INT"))
							{
								methodName = "getLoanInt"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
								method = cls.getMethod(methodName, null);
								double amtInt = (Double)method.invoke(payBillVo, null);
								double totAmtInt = (Double)method.invoke(paybillVoGrp, null);
								totAmtInt+=amtInt;
								
								
								methodName = "setLoanInt"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
								method = cls.getMethod(methodName, double.class);
								method.invoke(paybillVoGrp, totAmtInt);
							}
							else
							{
							methodName = "getLoan"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, null);
							double amt = (Double)method.invoke(payBillVo, null);
							double totAmt = (Double)method.invoke(paybillVoGrp, null);
							totAmt += amt;
							
							
							methodName = "setLoan"+hrPayEdpCompoMpg.getRltBillTypeEdp().getEdpCode();
							method = cls.getMethod(methodName, double.class);
							method.invoke(paybillVoGrp, totAmt);
							
							}
						
						}
						catch(NoSuchMethodException ne)
						{

						}

					}
					else
					{

					}
				}
				
				}

			}


			List<HrPayLocComMpg> locAllow = compDao.getDataAllowChckedForMonthYear(  locId,  month,  year) ;
			List<HrPayLocComMpg> locDeduc = compDao.getDataDeductChckedForMonthYear(  locId,  month,  year) ;
			List<HrPayLocComMpg> loanListMapped = compDao.getDataLoanChckedForMonthYear(locId,month,year);
			logger.info("The size of loanList Mapped is "+loanListMapped.size());

			for(int i=0;i<allEdpList.size();i++)
			{
				if(allEdpList.get(i).getTypeId()!=null)
				{
					if(allEdpList.get(i).getCmnLookupId()==2500134)
					{
						for(int j=0;j<locAllow.size();j++)
						{
							if(allEdpList.get(i).getTypeId().equals(""+locAllow.get(j).getCompId()))
							{
								//logger.info("allow "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> allow name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
								allowEdpList.add(allEdpList.get(i));
								break;
							}
						}
					}
					else if(allEdpList.get(i).getCmnLookupId()==2500135)
					{
						for(int j=0;j<locDeduc.size();j++)
						{
							if(allEdpList.get(i).getTypeId().equals(""+locDeduc.get(j).getCompId()))
							{
								HrPayDeducTypeMst ded =deduDao.read(locDeduc.get(j).getCompId());
								if(ded.getDeductionBy().getLookupId()==2901424)
								{
									//logger.info("deduc by AG "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									deducAgEdpList.add(allEdpList.get(i));
								}
								else if (ded.getDeductionBy().getLookupId()==2901425)
								{
									//logger.info("deduc by TRA "+allEdpList.get(i).getRltBillTypeEdp().getEdpCode()+" ---> deduc name " +allEdpList.get(i).getRltBillTypeEdp().getEdpShortName());
									deducTyEdpList.add(allEdpList.get(i));
								}
								else
								{

								}
								break;
							}
						}

					}
					else if (allEdpList.get(i).getCmnLookupId() == 2500136) {
						for (int j = 0; j < loanListMapped.size(); j++) {
							if (allEdpList.get(i).getTypeId().equals(
									"" + loanListMapped.get(j).getCompId())) {
								HrLoanAdvMst hrLoanAdvMst = loanDao
										.read(loanListMapped.get(j).getCompId());
								long dispType = hrLoanAdvMst.getDisplayGroup()
										.getLookupId();
								logger.info("Loan is "
										+ hrLoanAdvMst.getLoanAdvName()
										+ " display place " + dispType);
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
								logger.info("Loan is "
										+ hrLoanAdvMst.getLoanAdvName()
										+ " display place " + dispType);
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

			//creating list//

			StyledData dataStyle = new StyledData();


			row = new ArrayList();

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("SL No.");
			row.add(dataStyle);



			//row.add("SL No");

			ArrayList innerData = new ArrayList();
			ArrayList innerRow = new ArrayList();

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("Name");
			row.add(dataStyle);
			
			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("GROUP -N.A.-");
			row.add(dataStyle);


			//row.add("Name");


			int postsInRow =5;
			int postCount=0;

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("GROUP A");
			row.add(dataStyle);

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("GROUP B");
			row.add(dataStyle);

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("GROUP B N Gz");
			row.add(dataStyle);

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("GROUP C");
			row.add(dataStyle);

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("GROUP D");
			row.add(dataStyle);
			
			
			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("");
			row.add(dataStyle);
			
			
			
			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("TOTAL");
			row.add(dataStyle);


			//row.add("Total");

			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setData("SL No.");
			row.add(dataStyle);

			//row.add("SL No.");
			DataList.add(row);

			//for row 4
			int iniCount =0;
			int cntFirst =0;
			row= new ArrayList();
			// row.add(cntFirst++);
			double emptotAllow[] = new double[postsInRow+2];
			double totalAllow[] = new double[allowEdpList.size()+1]; 
			TabularData td = null;


			for( int i=0;i<postsInRow+4;i++)
			{
				//manish started
				if(i==0)
				{
					innerData = new ArrayList();
					iniCount = cntFirst;
					for(int k=0;k<=allowEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(++cntFirst);
						innerData.add(innerRow);
					}
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}

				//manish ended
				else if(i==1)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("OFFICI. PAY");
					innerData.add(innerRow);
					for(int k=0;k<allowEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(allowEdpList.get(k).getRltBillTypeEdp().getEdpShortName().toUpperCase());
						innerData.add(innerRow);
					}
					// innerRow = new ArrayList();
					// innerRow.add("Total Allow");
					// innerData.add(innerRow);
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					//  td.setStyles(numberDispalyVO);
					row.add(td);
				}
				
				else if(i==2)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<allowEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}
				else if(i==postsInRow+3)//total logic
				{ 
					
					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<allowEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					
					//System.out.println("in else condition is coming********8");
					double gt=0;

					//row.add("a");
					innerData = new ArrayList();
					for(int p=0;p<allowEdpList.size()+1;p++)
					{
						innerRow = new ArrayList();
						innerRow.add(totalAllow[p]);

						innerData.add(innerRow);
						gt+=totalAllow[p];
					}
					// innerRow = new ArrayList();
					// innerRow.add(gt);
					emptotAllow[postsInRow]=gt;
					//innerData.add(innerRow);
					td = new TabularData(innerData);
					td.setStyles(numberDispalyVO);
					row.add(td);
					// row.add(cntLast++);

				}

				else
				{

					paybillVo =(HrPayPaybill) paybillVoList.get( postCount);


					// logger.info("column no = "+(i-1)+" post count "+postCount);
					// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
					innerData = new ArrayList();
					emptotAllow[i-3] = paybillVo.getBasic0101();
					totalAllow[0]+=paybillVo.getBasic0101();
					//logger.info("total is ::::: "+emptotAllow[i-1]);
					double empTotal =0;
					innerRow = new ArrayList();
					innerRow.add(paybillVo.getBasic0101());
					innerData.add(innerRow);

					for(int p=0;p<allowEdpList.size();p++)
					{
						innerRow = new ArrayList();

						String method = "getAllow"+allowEdpList.get(p).getRltBillTypeEdp().getEdpCode().trim();
						Class pay = paybillVo.getClass();			
						Method payMthd = pay.getMethod(method, null);
						double value = (Double)payMthd.invoke(paybillVo, null);
						innerRow.add(value);
						//logger.info("allowance value ::"+ value);
						innerData.add(innerRow);
						empTotal+=value;
						//String str ="total of allow"+allowEdpList.get(p).getRltBillTypeEdp().getEdpCode();

						totalAllow[p+1]+=value;
						//logger.info(str+" "+totalAllow[p]);
					}
					//innerRow = new ArrayList();

					//logger.info("emp allow total "+emptotAllow[i-1]);
					emptotAllow[i-3]+=empTotal;
					//logger.info("value is "+empTotal+" after addition ::"+emptotAllow[i-1]);
					//innerRow.add(emptotAllow[i-1]);
					//innerData.add(innerRow);
					td = new TabularData(innerData);
					td.setStyles(numberDispalyVO);
					row.add(td);




					postCount++;
				}

			}


			//logger.info("length of row is "+row.size());

			//manish
			innerData = new ArrayList();
			postCount=0;
			for(int k=0;k<=allowEdpList.size();k++)
			{
				innerRow = new ArrayList();
				innerRow.add(++iniCount);
				innerData.add(innerRow);
			}
			td = new TabularData(innerData);
			td.setStyles(nameVo);
			row.add(td);
			//ended

			DataList.add(row);

			/////new line added to display total allowance

			row = new ArrayList();
			row.add(++cntFirst);
			row.add("TOTAL");
			
			StyledData dataStyle111112 = new StyledData();
			dataStyle111112.setStyles(boldStyleVO);
			dataStyle111112.setData("0");
			row.add(dataStyle111112);
			//System.out.println("Total Allow*********"+postsInRow);
			for(int i=0;i<postsInRow;i++)
			{
				//System.out.println("inside i value*******"+i);
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(emptotAllow[i]);
				row.add(dataStyle1);
				//row.add(emptotAllow[i]);
			}
			row.add("");
			if(true)
			{
			StyledData dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(emptotAllow[postsInRow]);
			row.add(dataStyle1);
			}
			
			// logger.info("length of row is "+row.size());
			row.add(cntFirst);
			/* logger.info("manish length is "+row.size());

		  logger.info("dataList before adding allowance is "+DataList);*/
			DataList.add(row);
			//logger.info("dataList after adding allowance is "+DataList);



			////for row 5
			///advances logic lies here

			/* row = new ArrayList();
		  row.add(++cntFirst);
		  row.add("");
		  double totFa =0;
		  for(int i=0;i<postsInRow;i++)
		  {
			    dataStyle = new StyledData();
			  dataStyle.setStyles(boldStyleVO);
			  dataStyle.setData(paybillVoList.get( i).getAdv5059());
			  totFa+= paybillVoList.get(i).getAdv5059();
			  row.add("");

		  }
		  dataStyle = new StyledData();
		  dataStyle.setStyles(boldStyleVO);
		  dataStyle.setData(totFa);
		  row.add("");
		 // logger.info("length of row is "+row.size());
		  row.add(cntFirst);
		 // logger.info("manish 2 size is "+row.size());
		  DataList.add(row);*/



			/////gross Logic 

			row = new ArrayList();
			row.add(++cntFirst);
			row.add("GROSS SAL.");
			row.add("");
			for(int i=0;i<postsInRow;i++)
			{
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				// dataStyle1.setData(emptotAllow[i]-(paybillVo.getAdv5059()));
				dataStyle1.setData(emptotAllow[i]);
				row.add(dataStyle1);


				//row.add(emptotAllow[i]-(paybillVo.getAdv5059()+paybillVo.getAdvInt5059()));
				//emptotAllow[i]= emptotAllow[i]-(paybillVo.getAdv5059());
				emptotAllow[i]= emptotAllow[i];
			}

			
			row.add("");
			if(true)
			{
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				// dataStyle1.setData(emptotAllow[i]-(paybillVo.getAdv5059()));
				dataStyle1.setData(emptotAllow[postsInRow]);
				row.add(dataStyle1);
				//row.add(emptotAllow[i]-(paybillVo.getAdv5059()+paybillVo.getAdvInt5059()));
				//emptotAllow[i]= emptotAllow[i]-(paybillVo.getAdv5059());
				//emptotAllow[i]= emptotAllow[i];
			}
			// logger.info("length of row is "+row.size());
			row.add(cntFirst);
			// logger.info("Manish 3 size is "+row.size());
			DataList.add(row);

			//manish  gggggggggggggggg
			
			row = new ArrayList();
			double emptotGrossLoan[] = new double[postsInRow + 1];
			double totalGrossLoan[] = new double[loanListGross.size()];
			//postCount = rollBackCount;
			
			if(loanListGross.size()>0){
				row = new ArrayList();
			for( int i=0;i<postsInRow+4;i++)
			{


				//manish started
				if(i==0)
				{
					innerData = new ArrayList();
					iniCount = cntFirst;
					for(int k=0;k<loanListGross.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(++cntFirst);
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
				//ended	

				else if(i==1)
				{
					innerData = new ArrayList();
					for(int k=0;k<loanListGross.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(loanListGross.get(k).getRltBillTypeEdp().getEdpShortName().toUpperCase());
						innerData.add(innerRow);
					}
					//innerData.add(innerRow);
					if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
					} else {
						row.add("");
					}
				}
				else if(i==2)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<loanListGross.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
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
				else if(i==postsInRow+3)//total logic
				{ 
					
					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<loanListGross.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					double gt=0;

					//row.add("a");
					innerData = new ArrayList();
					for(int p=0;p<loanListGross.size();p++)
					{
						innerRow = new ArrayList();
						innerRow.add(totalGrossLoan[p]);

						innerData.add(innerRow);
						gt+=totalGrossLoan[p];
					}
					//innerRow = new ArrayList();
					//innerRow.add(gt);
					emptotGrossLoan[postsInRow]=gt;
					//innerData.add(innerRow);
					if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						row.add(td);
					} else {
						row.add("");
					}
				}

				else
				{

					paybillVo =(HrPayPaybill) paybillVoList.get( postCount);


					//logger.info("column no = "+(i-1)+" post count "+postCount);
					//logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
					innerData = new ArrayList();
					double empTotal =0;
					for(int p=0;p<loanListGross.size();p++)
					{
						innerRow = new ArrayList();

						HrPayEdpCompoMpg edpCompoMpg = loanListGross
						.get(p);

						String mthdName = "";
						if (edpCompoMpg.getCmnLookupId() == 2500137) {
					mthdName = "getLoan"
							+ edpCompoMpg.getRltBillTypeEdp()
									.getEdpCode().trim();
						} else {
					mthdName = "getAdv"
							+ edpCompoMpg.getRltBillTypeEdp()
									.getEdpCode().trim();
						}

						
						Class pay = paybillVo.getClass();			
						Method payMthd = pay.getMethod(mthdName, null);
						double value = (Double)payMthd.invoke(paybillVo, null);
						innerRow.add(value);
						//logger.info("allowance value ::"+ value);
						innerData.add(innerRow);
						empTotal+=value;


						totalGrossLoan[p]+=value;

					}
					//innerRow = new ArrayList();
					//innerRow.add(empTotal);
					emptotGrossLoan[i-3]=empTotal;
					//innerData.add(innerRow);
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
			
			postCount =0;
			// logger.info("length of row is "+row.size());
			
			
			innerData = new ArrayList();

			for(int k=0;k<loanListGross.size();k++)
			{
				innerRow = new ArrayList();
				innerRow.add(++iniCount);
				innerData.add(innerRow);
			}
			if (innerData.size() > 0) {
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				row.add(td);
			} else {
				row.add("");
			}
			//System.out.println("length of row  before totaol ag, ded is "+row.size());
			DataList.add(row);
			
			}
			//manish gggggggggggggggggggg

			
			row = new ArrayList();
			row.add(++cntFirst);
			row.add("GROSS TOT.");
			row.add("");
			for(int i=0;i<postsInRow;i++)
			{
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(emptotAllow[i] - (emptotGrossLoan[i]));
				if (emptotAllow[i] - (emptotGrossLoan[i]) == 0)
					row.add("");
				else
					row.add(dataStyle1);
				emptotAllow[i] = emptotAllow[i] - (emptotGrossLoan[i]);
			}
			row.add("");
			if(true)
			{
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(emptotAllow[postsInRow] - emptotGrossLoan[postsInRow]);
				row.add(dataStyle1);
				emptotAllow[postsInRow]=emptotAllow[postsInRow] - emptotGrossLoan[postsInRow];
			}
			row.add(cntFirst);
			DataList.add(row);
			
			

			//for row 6 For Audit office |S|L|O|
			orderdataList = new ArrayList();
			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setColspan(displayPostsInRow+6);
			dataStyle.setData("FOR AUDITO");
			orderdataList.add(dataStyle);
			DataList.add(orderdataList);


			/*	//row 7 GPF AC No
			  row = new ArrayList();
			  row.add(cntFirst++);
			  row.add("GPF/DCPS A/c No.");
			  for(int ga=0;ga<postsInRow;ga++)
				{
				  	long userId = paybillVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
				  	GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
				  	genDao.setSessionFactory(serv.getSessionFactory());
				  	HrPayGpfBalanceDtls gpfDtl = (HrPayGpfBalanceDtls)genDao.read(userId);
				  	if(gpfDtl!=null && !gpfDtl.getGpfAccNo().trim().equals("123"))
				  	{
				  		row.add(gpfDtl.getGpfAccNo());
				  	}
				  	else
				  	{
				  		row.add(" ");
				  	}
				  	//paybillVo =(HrPayPaybill) paybillVoList.get(postCount);
				  	//long userId = paybillVo.getHrEisEmpMst().getOrgEmpMst().getOrgUserMst().getUserId();
				  	//fatch gpf from userId
				  ////adde remark information

				}

			  row.add(" ");
			  for(int nulcol=0;nulcol<nullColumns;nulcol++)
			  {
					 row.add(" " );
			  }
			  row.add(cntLast++);
			//  logger.info("manish 4 size is"+row.size());
			  DataList.add(row);*/

			/////for deduction by Ag
			orderdataList = new ArrayList();
			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setColspan(displayPostsInRow+6);
			dataStyle.setData("DEDUCTIONS ADJUSTABLE BY AG");
			orderdataList.add(dataStyle);
			DataList.add(orderdataList);








			///for row 9
			row= new ArrayList();
			double emptotDeducAg[] = new double[postsInRow+2];
			double totalDeducAg[] = new double[deducAgEdpList.size()+1]; 

			for( int i=0;i<postsInRow+4;i++)
			{


				//manish started
				if(i==0)
				{
					innerData = new ArrayList();
					iniCount = cntFirst;
					for(int k=0;k<deducAgEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(++cntFirst);
						innerData.add(innerRow);
					}
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}

				//manish ended

				else if(i==1)
				{
					innerData = new ArrayList();
					for(int k=0;k<deducAgEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(deducAgEdpList.get(k).getRltBillTypeEdp().getEdpShortName().toUpperCase());
						innerData.add(innerRow);
					}
					// innerRow = new ArrayList();
					// innerRow.add("Total AG");
					//innerData.add(innerRow);
					if(innerData.size()>0)
					{
						td = new TabularData(innerData);
						td.setStyles(nameVo);								  //td.setStyles(numberDispalyVO);
						row.add(td);
					}
					else
					{
						row.add(" ");
					}
				}
				
				else if(i==2)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<deducAgEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}
				
				else if(i==postsInRow+3)//total logic
				{ 
					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<deducAgEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					
					double gt=0;

					//row.add("a");
					innerData = new ArrayList();
					for(int p=0;p<deducAgEdpList.size();p++)
					{
						innerRow = new ArrayList();
						innerRow.add(totalDeducAg[p]);


						innerData.add(innerRow);
						gt+=totalDeducAg[p];
					}
					//innerRow = new ArrayList();
					//innerRow.add(gt);
					emptotDeducAg[postsInRow]=gt;
					//innerData.add(innerRow);
					if(innerData.size()>0){
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						row.add(td);
					}
					else
					{
						row.add(" ");
					}

				}

				else
				{

					paybillVo =(HrPayPaybill) paybillVoList.get( postCount);


					//logger.info("column no = "+(i-1)+" post count "+postCount);
					//logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
					innerData = new ArrayList();
					double empTotal =0;
					for(int p=0;p<deducAgEdpList.size();p++)
					{
						innerRow = new ArrayList();

						String method = "getDeduc"+deducAgEdpList.get(p).getRltBillTypeEdp().getEdpCode();
						Class pay = paybillVo.getClass();			
						Method payMthd = pay.getMethod(method, null);
						double value = (Double)payMthd.invoke(paybillVo, null);
						innerRow.add(value);
						//logger.info("allowance value ::"+ value);
						innerData.add(innerRow);
						empTotal+=value;


						totalDeducAg[p]+=value;

					}
					//innerRow = new ArrayList();
					// innerRow.add(empTotal);
					emptotDeducAg[i-3]=empTotal;
					//innerData.add(innerRow);
					if(innerData.size()>0)
					{
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						row.add(td);
					}
					else
					{

						row.add(" ");
					}




					postCount++;
				}

			}

			postCount =0;
			// logger.info("length of row is "+row.size());

			//manish started

			innerData = new ArrayList();

			for(int k=0;k<deducAgEdpList.size();k++)
			{
				innerRow = new ArrayList();
				innerRow.add(++iniCount);
				innerData.add(innerRow);
			}
			td = new TabularData(innerData);
			td.setStyles(nameVo);
			row.add(td);




			//manish ended


			/*  logger.info("manish 4 size is"+row.size());
				  logger.info("datalist  before  manish 4" +DataList);*/
			DataList.add(row);
			//logger.info("datalist  after  manish 4" +DataList);

			////Loans List 


			double emptotLoan[] = new double[postsInRow+2];
			double totalLoan[] = new double[loanList.size()+1]; 
			if(loanList.size()>0)
{			row= new ArrayList();
			for( int i=0;i<postsInRow+4;i++)
			{


				//manish started
				if(i==0)
				{
					innerData = new ArrayList();
					iniCount = cntFirst;
					for(int k=0;k<loanList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(++cntFirst);
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
				//ended	

				else if(i==1)
				{
					innerData = new ArrayList();
					for(int k=0;k<loanList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(loanList.get(k).getRltBillTypeEdp().getEdpShortName().toUpperCase());
						innerData.add(innerRow);
					}
					//innerData.add(innerRow);
					if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
					} else {
						row.add("");
					}
				}
				else if(i==2)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<loanList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
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
				else if(i==postsInRow+3)//total logic
				{ 
					
					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<loanList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					double gt=0;

					//row.add("a");
					innerData = new ArrayList();
					for(int p=0;p<loanList.size();p++)
					{
						innerRow = new ArrayList();
						innerRow.add(totalLoan[p]);

						innerData.add(innerRow);
						gt+=totalLoan[p];
					}
					//innerRow = new ArrayList();
					//innerRow.add(gt);
					emptotLoan[postsInRow]=gt;
					//innerData.add(innerRow);
					if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						row.add(td);
					} else {
						row.add("");
					}

				}

				else
				{

					paybillVo =(HrPayPaybill) paybillVoList.get( postCount);


					//logger.info("column no = "+(i-1)+" post count "+postCount);
					//logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
					innerData = new ArrayList();
					double empTotal =0;
					for(int p=0;p<loanList.size();p++)
					{
						innerRow = new ArrayList();

						String method ="";
									
										
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
						double value = (Double)payMthd.invoke(paybillVo, null);
						innerRow.add(value);
						//logger.info("allowance value ::"+ value);
						innerData.add(innerRow);
						empTotal+=value;


						totalLoan[p]+=value;

					}
					//innerRow = new ArrayList();
					//innerRow.add(empTotal);
					emptotLoan[i-3]=empTotal;
					//innerData.add(innerRow);
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

			postCount =0;
			// logger.info("length of row is "+row.size());
			
			
			innerData = new ArrayList();

			for(int k=0;k<loanList.size();k++)
			{
				innerRow = new ArrayList();
				innerRow.add(++iniCount);
				innerData.add(innerRow);
			}
			if (innerData.size() > 0) {
				td = new TabularData(innerData);
				td.setStyles(nameVo);
				row.add(td);
			} else {
				row.add("");
			}
			logger.info("length of row  before totaol ag, ded is "+row.size());
			DataList.add(row);
}
			//logger.info("datalist  before  manish 5" +DataList);




			//to display total AG + Loan deductions 

			row = new ArrayList();
			row.add(++cntFirst);
			row.add("TOT.AG.DED");
			StyledData dataStyle1111122 = new StyledData();
			dataStyle1111122.setStyles(boldStyleVO);
			dataStyle1111122.setData("0");
			row.add(dataStyle1111122);
			
			for(int i=0;i<postsInRow;i++)
			{
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(emptotDeducAg[i]+emptotLoan[i]);
				row.add(dataStyle1);
				emptotDeducAg[i]= emptotDeducAg[i]+emptotLoan[i];
			}
			row.add("");
			if(true)
			{
				StyledData dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(emptotDeducAg[postsInRow]+emptotLoan[postsInRow]);
				row.add(dataStyle1);
				emptotDeducAg[postsInRow]= emptotDeducAg[postsInRow]+emptotLoan[postsInRow];
				
			}
			
			row.add(cntFirst);
			//System.out.println("length of row  prior  totaol ag, ded is "+row.size());
			DataList.add(row);



			/// for row 10 deductions by TRY
			orderdataList = new ArrayList();
			centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left"); 
			dataStyle = new StyledData();
			dataStyle.setStyles(centerboldStyleVO);
			dataStyle.setColspan(displayPostsInRow+6);
			dataStyle.setData("DEDUCTIONS ADJUSTABLE BY TRY");
			orderdataList.add(dataStyle);
			DataList.add(orderdataList);


			//for row 11

			row= new ArrayList();
			double emptotDeducTRY[] = new double[postsInRow+2];
			double totalDeducTRY[] = new double[deducTyEdpList.size()+1]; 

			for( int i=0;i<postsInRow+4;i++)
			{


				//manish started
				if(i==0)
				{
					innerData = new ArrayList();
					iniCount = cntFirst;
					for(int k=0;k<deducTyEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(++cntFirst);
						innerData.add(innerRow);
					}
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}
				//ended	

				else if(i==1)
				{
					innerData = new ArrayList();
					for(int k=0;k<deducTyEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(deducTyEdpList.get(k).getRltBillTypeEdp().getEdpShortName().toUpperCase());
						innerData.add(innerRow);
					}
					//innerRow = new ArrayList();
					//innerRow.add("Total TRY");
					//innerData.add(innerRow);
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}
				else if(i==2)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<deducTyEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
				}
				else if(i==postsInRow+3)//total logic
				{ 
					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<deducTyEdpList.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					double gt=0;

					//row.add("a");
					innerData = new ArrayList();
					for(int p=0;p<deducTyEdpList.size();p++)
					{
						innerRow = new ArrayList();
						innerRow.add(totalDeducTRY[p]);

						innerData.add(innerRow);
						gt+=totalDeducTRY[p];
					}
					//innerRow = new ArrayList();
					//innerRow.add(gt);
					emptotDeducTRY[postsInRow]=gt;
					//innerData.add(innerRow);
					td = new TabularData(innerData);
					td.setStyles(numberDispalyVO);
					row.add(td);

				}

				else
				{

					paybillVo =(HrPayPaybill) paybillVoList.get( postCount);


					// logger.info("column no = "+(i-1)+" post count "+postCount);
					// logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
					innerData = new ArrayList();
					double empTotal =0;
					for(int p=0;p<deducTyEdpList.size();p++)
					{
						innerRow = new ArrayList();

						String method = "getDeduc"+deducTyEdpList.get(p).getRltBillTypeEdp().getEdpCode().trim();
						//logger.info("Method Name is "+method+" value is "+value);
						Class pay = paybillVo.getClass();			
						Method payMthd = pay.getMethod(method, null);
						double value = (Double)payMthd.invoke(paybillVo, null);
						logger.info("Method Name is "+method+" value is "+value);
						innerRow.add(value);
						//logger.info("allowance value ::"+ value);
						innerData.add(innerRow);
						empTotal+=value;


						totalDeducTRY[p]+=value;

					}
					//innerRow = new ArrayList();
					//innerRow.add(empTotal);
					emptotDeducTRY[i-3]=empTotal;
					//innerData.add(innerRow);
					td = new TabularData(innerData);
					td.setStyles(numberDispalyVO);
					row.add(td);



					postCount++;
				}

			}

			postCount =0;
			innerData = new ArrayList();

			for(int k=0;k<deducTyEdpList.size();k++)
			{
				innerRow = new ArrayList();
				innerRow.add(++iniCount);
				innerData.add(innerRow);
			}
			td = new TabularData(innerData);
			td.setStyles(nameVo);
			row.add(td);
			//System.out.println("length of row  after  totaol ag, ded is "+row.size());
			DataList.add(row);



			// total sdeduction TRY
			//manish started for Loan In inner


			double totalAdv[] = new double[loanListNew.size()+1]; 
if(loanListNew.size()>0)
{
	row= new ArrayList();

			for( int i=0;i<postsInRow+4;i++)
			{


				//manish started
				if(i==0)
				{
					innerData = new ArrayList();
					iniCount = cntFirst;
					for(int k=0;k<loanListNew.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(++cntFirst);
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
				//ended	

				else if(i==1)
				{
					innerData = new ArrayList();
					for(int k=0;k<loanListNew.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add(loanListNew.get(k).getRltBillTypeEdp().getEdpShortName().toUpperCase());
						innerData.add(innerRow);
					}
					//innerData.add(innerRow);
					if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
					} else {
						row.add("");
					}
				}
				else if(i==2)
				{


					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<loanListNew.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					
					/*	if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(nameVo);
						row.add(td);
					} else {
						row.add("");
					}*/
				}
				else if(i==postsInRow+3)//total logic
				{ 
					innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add("");
					innerData.add(innerRow);
					for(int k=0;k<loanListNew.size();k++)
					{
						innerRow = new ArrayList();
						innerRow.add("");
						innerData.add(innerRow);
					}
					
					td = new TabularData(innerData);
					td.setStyles(nameVo);
					row.add(td);
					
					double gt=0;

					//row.add("a");
					innerData = new ArrayList();
					for(int p=0;p<loanListNew.size();p++)
					{
						innerRow = new ArrayList();
						innerRow.add(totalAdv[p]);




						innerData.add(innerRow);
						gt+=totalAdv[p];
					}
					//innerRow = new ArrayList();
					//innerRow.add(gt);
					emptotDeducTRY[postsInRow]+=gt;
					//innerData.add(innerRow);
					if (innerData.size() > 0) {
						td = new TabularData(innerData);
						td.setStyles(numberDispalyVO);
						row.add(td);
					} else {
						row.add("");
					}
				}

				else
				{

					paybillVo =(HrPayPaybill) paybillVoList.get( postCount);


					//logger.info("column no = "+(i-1)+" post count "+postCount);
					//logger.info("allowance printing ::"+paybillVo.getHrEisEmpMst().getOrgEmpMst().getEmpLname());
					innerData = new ArrayList();
					double empTotal =0;
					for(int p=0;p<loanListNew.size();p++)
					{
						innerRow = new ArrayList();

						String method ="";
						if (loanListNew.get(p).getCmnLookupId() == 2500137)
						{
							
							/*method = "getLoan"
									+ loanListNew.get(p)
											.getRltBillTypeEdp()
											.getEdpCode();*/
							
//							method = "getLoanInt"
//								+ loanListNew.get(p)
//										.getRltBillTypeEdp()
//										.getEdpCode();
							
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
						double value = (Double)payMthd.invoke(paybillVo, null);
						innerRow.add(value);
						//logger.info("allowance value ::"+ value);
						innerData.add(innerRow);
						empTotal+=value;


						totalAdv[p]+=value;

					}
					//innerRow = new ArrayList();
					//innerRow.add(empTotal);
					//emptotAdv[i-2]=empTotal;
					emptotDeducTRY[i-3]+=empTotal;
					//innerData.add(innerRow);
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


			postCount =0;
			//row.add(" ");
			innerData = new ArrayList();

			for(int k=0;k<loanListNew.size();k++)
			{
				innerRow = new ArrayList();
				innerRow.add(++iniCount);
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

}


			//manish ended for loans in inner
			row = new ArrayList();
			row.add(++cntFirst);
			row.add("TOT.TR.DED");
			StyledData dataStyle111111 = new StyledData();
			dataStyle111111.setStyles(boldStyleVO);
			dataStyle111111.setData("0");
			row.add(dataStyle111111);
			for(int h=0;h<postsInRow;h++)
			{
				dataStyle = new StyledData();

				dataStyle.setData( emptotDeducTRY[h] );
				// dataStyle.setData( emptotDeducTRY[h]+emptotAdv[h]);
				dataStyle.setStyles(numberDispalyVO);
				row.add(dataStyle);
			}

			row.add("");
			if(true)
			{
				dataStyle = new StyledData();

				dataStyle.setData( emptotDeducTRY[postsInRow] );
				// dataStyle.setData( emptotDeducTRY[h]+emptotAdv[h]);
				dataStyle.setStyles(numberDispalyVO);
				row.add(dataStyle);
			}
			//logger.info("length of row is "+row.size());
			row.add(cntFirst);
			//  logger.info("manish 8 size is"+row.size());
			DataList.add(row);


			//for row 12 total deductions AG+TRY

			row = new ArrayList();
			row.add(++cntFirst);
			row.add("TOTAL DEDS");
			StyledData dataStyle11111 = new StyledData();
			dataStyle11111.setStyles(boldStyleVO);
			dataStyle11111.setData("0");
			row.add(dataStyle11111);
			for(int h=0;h<postsInRow;h++)
			{
				dataStyle = new StyledData();

				dataStyle.setData((emptotDeducAg[h]+emptotDeducTRY[h]));


				dataStyle.setStyles(numberDispalyVO);
				row.add(dataStyle);
			}

			row.add("");
			if(true)
			{
			
				dataStyle = new StyledData();

				dataStyle.setData((emptotDeducAg[postsInRow]+emptotDeducTRY[postsInRow]));


				dataStyle.setStyles(numberDispalyVO);
				row.add(dataStyle);
			}
			//logger.info("length of row is "+row.size());
			row.add(cntFirst);
			logger.info("manish 9 size is"+row.size());
			DataList.add(row);
			// for row 13  NET


			row = new ArrayList();
			row.add(++cntFirst);
			row.add("NET AMT.");
			row.add("");
			double pageTotal=0;
			for(int h=0;h<postsInRow;h++)
			{
				dataStyle = new StyledData();
				pageTotal = emptotAllow[h]-(emptotDeducAg[h]+emptotDeducTRY[h]);
				dataStyle.setData(pageTotal);
				dataStyle.setStyles(boldStyleVO);
				row.add(dataStyle);
			}
			row.add("");
			if(true)
			{
				dataStyle = new StyledData();
				pageTotal = emptotAllow[postsInRow]-(emptotDeducAg[postsInRow]+emptotDeducTRY[postsInRow]);
				dataStyle.setData(pageTotal);
				dataStyle.setStyles(boldStyleVO);
				row.add(dataStyle);
			}
			//logger.info("length of row is "+row.size());
			row.add(cntFirst);
			// logger.info("manish 10 size is"+row.size());
			DataList.add(row);



		}


		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}

		return DataList;		  





	}



	public ReportVO exportReport(ReportVO reportVO, Object arg1, ReportEvent event) {

		//ReportColumnVO[] rptCol = reportVO.getReportColumns();  
		//String Print=CheckIfNull(reportVO.getParameterValue( "Print" ));

		//reportVO.setParameterValue("Department", locId);
		reportVO.setReportName("");



		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}

	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt1 =new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 =new SimpleDateFormat("MM");
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		long locationId=locationVO.getLocId();
		String month = fmt1.format(today);

		if(month.charAt(0)=='0')
		{
			month=month.substring(1,2);
		}
		if(  report.getReportCode().equals("5000012") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			//report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
			report.setParameterValue("billTypePara",resourceBundle.getString("OnlyPaybill"));
		}

		if(  report.getReportCode().equals("5000012") )

		{            
			report.setParameterValue("Department",locationId+"");
		}

		return report;
	}

}
