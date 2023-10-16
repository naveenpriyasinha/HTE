package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.valueobject.HrCustodianTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class HRRReportDAOImpl extends DefaultReportDataFinder implements ReportDataFinder, ReportEventListener{

	private Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{
		logger.info("==> in HRRReportDAOImpl findReportData...");
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
	
		int finalpagesize=27;
		
		Map serviceMap = (Map)requestAttributes.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		long locId =0;
		
		StyleVO[] boldStyleVO = new StyleVO[1];
		boldStyleVO[0] = new StyleVO();
		boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
		
		StyleVO[] noBorderVO = new StyleVO[1];
		noBorderVO[0] = new StyleVO();
		noBorderVO[0].setStyleId(IReportConstants.BORDER);
		noBorderVO[0].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE); 
		
		StyleVO[] styleVOPgBrk=null;
		styleVOPgBrk = new StyleVO[2];

		styleVOPgBrk[0] = new StyleVO();
		styleVOPgBrk[0].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
		styleVOPgBrk[0].setStyleValue("yes");
		styleVOPgBrk[0] = new StyleVO();


		styleVOPgBrk[1] = new StyleVO();
		styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
		styleVOPgBrk[1].setStyleValue("yes");
		
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
		
		
		StyleVO[] leftHeader = new StyleVO[3];
		leftHeader[0] = new StyleVO();
		leftHeader[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		leftHeader[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
		leftHeader[1] = new StyleVO();
		leftHeader[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		leftHeader[1].setStyleValue("11"); 
		leftHeader[2] = new StyleVO();
		leftHeader[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		leftHeader[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
		
		StyleVO[] rightHead = new StyleVO[3];
		rightHead[0] = new StyleVO();
		rightHead[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
		rightHead[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD); 
		rightHead[1] = new StyleVO();
		rightHead[1].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rightHead[1].setStyleValue("11"); 
		rightHead[2] = new StyleVO();
		rightHead[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		rightHead[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
		
		ArrayList DataList = new ArrayList();
		
		try
		{

			long month=0;
			long year=0;
			long TotalAmt=0;
			long CustodianId=0;
			
			logger.info("==> in try...");
			month = StringUtility.convertToLong(report.getParameterValue("Month")!=null?report.getParameterValue("Month").toString():"-1");
			year = StringUtility.convertToLong(report.getParameterValue("Year")!=null?report.getParameterValue("Year").toString():"0");
			locId= StringUtility.convertToLong(baseLoginMap.get("locationId").toString());
			CustodianId = StringUtility.convertToLong(report.getParameterValue("Custodian")!=null?report.getParameterValue("Custodian").toString():"-1");
			logger.info("CustodianId is ***********"+CustodianId);
			
			QuaterMstDAOImpl quaterMstDAOImpl = new QuaterMstDAOImpl(HrEisQtrMst.class,serv.getSessionFactory());
			List<HrCustodianTypeMst> custodianList= quaterMstDAOImpl.getCustodianDivisionCodeAndDesc(CustodianId);
			String custodianDivisionCode="";
			String custodianDesc="";
			for(int i=0;i<custodianList.size();i++)
			{
				custodianDivisionCode=custodianList.get(i).getDivisionCode();
				custodianDesc=custodianList.get(i).getCustodianDesc();

			}
			
			logger.info("custodianDivisionCode is ******"+custodianDivisionCode);
			logger.info("custodianDesc is ******"+custodianDesc);
			String BillNoinner="";
			int l=0;
			String subheadCode="";
			String classIds="";
			String desgnIds="";
			String portType="";
			String BillNo="";	    

			BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
			logger.info(BillNoinner);
			StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
			while(st1.hasMoreTokens())
			{
				if(l==0)
					subheadCode=st1.nextToken();
				else if(l==1)
					classIds=st1.nextToken();
				else if(l==2)
					desgnIds=st1.nextToken();
				else if(l==3)
					portType=st1.nextToken();
				else if(l==4)
					BillNo=st1.nextToken();
				//logger.info("Actual value for bill no  "+BillNo);
				l++;
			} 
			
			//Added by abhilash				
			if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase("") )
			{
				logger.info("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
				BillNo = report	.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
			}
			//Ended
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy-MM-dd");


			
			cal.set(Calendar.YEAR, (int)year);
			cal.set(Calendar.MONTH, (int)month-1);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
			java.util.Date endMonthDate = cal.getTime();
			String endDate  = sdfObj.format(endMonthDate);
			

			cal.set(Calendar.YEAR, (int)year);
			cal.set(Calendar.MONTH,(int)month-1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			
			java.util.Date date = cal.getTime();
			String selDate  = sdfObj.format(date);
			
			logger.info("selDate is ************"+selDate);
			logger.info("endDate is ************"+endDate);
			
			//lsb.append("   (USRPST.endDate>='"+selDate+"' and USRPST.startDate<='"+endDate+"'    ))");
			
			List actionList = new ArrayList();
			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			if(CustodianId>0)
			{
			actionList = payDao.getHRRecoveryList(Long.valueOf(BillNo),month,year,locId,CustodianId,selDate,endDate);
			}
			else
			{
			actionList = payDao.getHRRecoveryList(Long.valueOf(BillNo),month,year,locId,selDate,endDate);
			}

			//added by abhilash
			long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
			String DeptName=payDao.getOffice(locId);
			/*List<OrgDdoMst> ddoList = payDao.getDDOCodeByLoggedInPost(loggedInpostId);
			String ddocode ="";
			if(ddoList.size()>0)
				ddocode = ddoList.get(0).getDdoCode();*/
			String ddocode ="";
			String TanNo="";
			long locactionId=0;
			locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locactionId);
    		if(ddoCodeList!=null)
    		 logger.info("After query execution for DDO Code " + ddoCodeList.size());
    		
    		OrgDdoMst ddoMst = null; 
    		if(ddoCodeList!=null && ddoCodeList.size()>0){
    			ddoMst = ddoCodeList.get(0);
    		}
    		
    		if(ddoMst!=null) {
    			ddocode=ddoMst.getDdoCode();
    			TanNo=ddoMst.getTanNo();
    		}
    		logger.info("DDO Code is " + ddocode);
    		//added by roshan
			logger.info("hhii the bill number"+BillNo);
			ddocode=payBillDAO.getddoCodeForBillId(BillNo);
			logger.info("ddocode is for bill number "+ddocode);
			locactionId=payBillDAO.getLocationCode(Long.parseLong(BillNo));
			logger.info("hhi the location cxod eis "+locactionId);
			//ended by roshan
    		
			String TresuryName ="";
			String TresuryCode ="";
			List TreasuryList = payDao.getTreasuryCodeAndTreasuryName(ddocode);

			if(TreasuryList!=null && TreasuryList.size()!=0)
			{
				for(Iterator itr=TreasuryList.iterator();itr.hasNext();)
				{

					Object[] rowList = (Object[])itr.next();

					if(rowList[0] != null)
					{
						TresuryName = rowList[0].toString().trim();
					}
					if(rowList[1] != null)
					{
						TresuryCode = rowList[1].toString().trim();
					}

				}
			}

			String Treasury =TresuryCode;

			
			String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
			Calendar cale = Calendar.getInstance(); 

			String lstrmonth = String.valueOf(month);
			Integer in = Integer.parseInt(lstrmonth);

			String monthname = monthName[in-1];
			
			String deptHeader ="";
			if(month>0)
			{

				deptHeader="Schedule for House Rent Recovery"+System.getProperty("line.separator")+"From Major Head 0216";;
			//	String deptHeader=DeptName+System.getProperty("line.separator")+"Schedule for the Recovery of Profession Tax of the Month of  "+MONTH+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo;
			}
			else
			{
				deptHeader="Schedule for House Rent Recovery"+System.getProperty("line.separator")+"From Major Head 0216";;

			}
			
			
			

			ArrayList styleList = new ArrayList();
			ArrayList stData = new ArrayList();
			
			StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
			styledHeader.setColspan(2);
			stData.add(styledHeader);
			styleList.add(stData);  
			
			ArrayList r = new ArrayList();
			r.add(new StyledData("For the Month of "+monthname +" "+year,leftHeader));
			//r.add(new StyledData(" ",rightHead));
			styleList.add(r);  
			
			
			ArrayList r3= new ArrayList();
			r3.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));
			r3.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
			styleList.add(r3);
			
			if(CustodianId>0)
			{
			ArrayList r4= new ArrayList();
			r4.add(new StyledData("Custodian Division Code :"+custodianDivisionCode +" - "+custodianDesc,leftHeader));	
			//r4.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
			styleList.add(r4);
			}
		/*	ArrayList r44= new ArrayList();
			r44.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
			styleList.add(r44);*/
			
			TabularData tData  = new TabularData(styleList);
			tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			tData.addStyle(IReportConstants.BORDER, "No");
			tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
			report.setAdditionalHeader(tData);
			
			
			
			
			//ended by abhilash

			ArrayList rowList = null;

			String row0="";
			String row1="";
			String row2="";
			long row3=0;
			String NameDesgId="";
			String Address="";
			long PO=0;
			long PE=0;
			long serCharge=0;
			long hrrArrear=0;
			long serChargeArr=0;
			long totalDeduction=0;
			long finaltotalDeduction=0;
			long finalserCharge=0;
			long finalhrrArrear=0;
			long addressId=0;
			int cnt=1;
			int pageCnt=1;
			
			if(actionList.size()>0)
			{
			for (int i = 0; i <actionList.size(); i++) 
			{
				
				rowList = new ArrayList();
				Object[] row = (Object[])actionList.get(i);


				row0 = row[0].toString();
				//logger.info("row0::::::::::::::: "+ row0);
				
				row1 = row[1].toString();
				//logger.info("row1::::::::::::::: "+ row1);
				
				row2 = row[2].toString();
				//logger.info("row2::::::::::::::: "+ row2);
				
				if(row[3].toString()!=null)
				row3 = (Math.round(Double.parseDouble((row[3]).toString())));
				//logger.info("row3::::::::::::::: "+ row3);
				
				if((row[3]).toString()!=null)
				//logger.info("HRR::::::::::::::: "+ HRR);
				
				if(row[5].toString()!=null)
				PO = (Math.round(Double.parseDouble((row[5]).toString())));
				//logger.info("poooo********" + PO);
				
				if(row[6].toString()!=null)
				PE = (Math.round(Double.parseDouble((row[6]).toString())));
				//logger.info("peeeeee********" + PE);
				
				if(row[7].toString()!=null)
				serCharge = (Math.round(Double.parseDouble((row[7]).toString())));
				//logger.info("serCharge::::::::::::::::::::::::::::"+serCharge); 
				
				if(row[8].toString()!=null)
				hrrArrear = (Math.round(Double.parseDouble((row[8]).toString())));
				logger.info("hrrArrear::::::::::::::::::::::::::::"+hrrArrear);
				
				long payRate =PO+PE;
				
				if(row[4] == null)
				{
					Address = " ";
					//logger.info("Address::::::::::::::: " + Address); 
				}
				 
				else
				{
					List<CmnAddressMst> addList = new ArrayList();
					addressId = (Math.round(Double.parseDouble((row[4]).toString())));
					
				//	addList = payDao.getAddressList(addressId, locId);
					addList = payDao.getAddressList(addressId);
					//logger.info("addList:::::::::::::::::"+addList.size());
					Address = (addList.get(0).getHouseName() != null ? addList.get(0).getHouseName() : "" )+ ", " + (addList.get(0).getSocBuildName() != null ? addList.get(0).getSocBuildName() : "") + ", " + (addList.get(0).getStreet() != null ? addList.get(0).getStreet() : "") + ", " + (addList.get(0).getArea() != null ? addList.get(0).getArea() : "") ;
					//logger.info("Address:::::::::::::::::::: " + Address );
				}
				
				NameDesgId = row0 +" ("+row1+") "+"("+row2+")";
				//Address = ((row[4] != null)?row[4].toString():"")+", "+((row[5] != null)?row[5].toString():"")+", "+((row[6] != null )?row[6].toString():"")+", "+((row[7] != null)?row[7].toString():"");
				
				logger.info("Address::::::::::::::::::::: "+Address);

				String totalAddress = payRate + " " + Address;
				logger.info("totalAddress::::::::::::::::::::: "+totalAddress);
				
				totalDeduction=row3+serCharge+hrrArrear+serChargeArr;
				logger.info("totalDeduction::::::::::::::::::::: "+totalDeduction);
				
				TotalAmt+= row3;
				finaltotalDeduction+=totalDeduction;
				finalserCharge+=serCharge;
				finalhrrArrear+=hrrArrear;
				
				logger.info("TotalAmt********" + TotalAmt);
				logger.info("finaltotalDeduction********" + finaltotalDeduction);
				logger.info("finalserCharge********" + finalserCharge);
				logger.info("finalhrrArrear********" + finalhrrArrear);
				
			
				rowList.add(String.valueOf(i+1));//1
				rowList.add(NameDesgId);//2
				rowList.add(totalAddress);//3
				rowList.add(row3);//4
				rowList.add(serCharge);//5
				rowList.add(hrrArrear);//6
				rowList.add(serChargeArr);//7
				rowList.add(totalDeduction);//8
				rowList.add("");//9
				rowList.add("");//10
				rowList.add("");//11

				logger.info("length is "+rowList.size());
				
				String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
				logger.info(" nof rec out====>"+noOfRec);
				if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
				{
					logger.info("No Of Rec is*****************====>"+noOfRec);
					finalpagesize=Integer.parseInt(noOfRec);
				}
				
				
				DataList.add(rowList);
				
				if((cnt%finalpagesize)==0) 
				{

					pageCnt++;
					rowList = new ArrayList();
					rowList.add(new PageBreak());
					rowList.add("Data");
					DataList.add(rowList); 
					
				}
				cnt++; 

				
			}

			ArrayList rw = new ArrayList();
			StyledData dataStyle1 = new StyledData();
			dataStyle1 = new StyledData();
			dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData("Total (`):");  
			dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			rw.add(dataStyle1);
			rw.add("");
			rw.add("");

			dataStyle1 = new StyledData();
			//dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(TotalAmt);                  
			rw.add(dataStyle1);

			rw.add(finalserCharge);
			rw.add(finalhrrArrear);
			rw.add("");
			dataStyle1 = new StyledData();
			//dataStyle1.setStyles(boldStyleVO);
			dataStyle1.setData(finaltotalDeduction);                  
			rw.add(dataStyle1);

			rw.add("");
			rw.add("");
			rw.add("");
			rw.add("");
			DataList.add(rw); 
			
			
			ArrayList row11= new ArrayList();
			StyledData sd = new StyledData();
			sd.addStyle(IReportConstants.BORDER,"NO");
			sd.addStyle(IReportConstants.WITHOUT_BORDER_STYLES_STRING,"YES");
			sd.setColspan(11);
			sd.setData("Total Deduction in words (`) :"+ConvertNumbersToWord.convert(Math.round(finaltotalDeduction))+" Only.");
			sd.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
			row11.add(sd);
			for(int c=0;c<11;c++)
				row11.add("");
			DataList.add(row11);

			}


		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		
		return DataList;
	}

	final String CheckIfNull(Object lStra)
	{
		String lStrb="";
		if( lStra != null )
		{
			lStrb = (((String)lStra).trim());

		}
		return lStrb;
	}
	
	public ReportVO exportReport(ReportVO reportVO, Object arg1, ReportEvent event) {
		
		//ReportColumnVO[] rptCol = reportVO.getReportColumns();  
		//String Print=CheckIfNull(reportVO.getParameterValue( "Print" ));
		
		
		reportVO.setReportName("");
		
		 

		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}
	
	public ReportVO getUserReportVO( ReportVO report, Object criteria )throws ReportException
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		logger.info ("***********Inside User Report VO *********************");
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

		//if(  report.getReportCode().equals("3") || report.getReportCode().equals("2500003")||report.getReportCode().equals("6") || report.getReportCode().equals("2500006")|| report.getReportCode().equals("7") || report.getReportCode().equals("2500007")|| report.getReportCode().equals("8") || report.getReportCode().equals("2500008") || report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011")|| report.getReportCode().equals("13") || report.getReportCode().equals("2500013")|| report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("9") || report.getReportCode().equals("2500009")|| report.getReportCode().equals("15") || report.getReportCode().equals("2500015"))
			if(  report.getReportCode().equals("3") || report.getReportCode().equals("5000011")||report.getReportCode().equals("6") || report.getReportCode().equals("2500006")|| report.getReportCode().equals("7") || report.getReportCode().equals("2500007")|| report.getReportCode().equals("8") || report.getReportCode().equals("2500008") || report.getReportCode().equals("10") || report.getReportCode().equals("2500010")|| report.getReportCode().equals("11") || report.getReportCode().equals("2500011")|| report.getReportCode().equals("13") || report.getReportCode().equals("2500013")|| report.getReportCode().equals("4") || report.getReportCode().equals("2500004") || report.getReportCode().equals("5") || report.getReportCode().equals("2500005") || report.getReportCode().equals("5000009") || report.getReportCode().equals("2500009")|| report.getReportCode().equals("15") || report.getReportCode().equals("2500015"))
			
		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			//added by ravysh
			report.setParameterValue("billTypePara",resourceBundle.getString("OnlyPaybill"));
		}

		if( report.getReportCode().equals("3r") || report.getReportCode().equals("5000011") || report.getReportCode().equals("5") || report.getReportCode().equals("6") || report.getReportCode().equals("7") || report.getReportCode().equals("8") || report.getReportCode().equals("9") || report.getReportCode().equals("10")|| report.getReportCode().equals("11")|| report.getReportCode().equals("13"))
	     {            
	          report.setParameterValue("Department",locationId+"");
	     } 


		return report;
	}
}