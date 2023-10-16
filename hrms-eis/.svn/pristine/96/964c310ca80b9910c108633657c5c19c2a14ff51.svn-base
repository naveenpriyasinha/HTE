package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
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
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class HBACALoanDAO extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener
{
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		Logger logger = Logger.getLogger(PayrollReportsDAO.class );
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		
		int finalpagesize=20;
		
		//long billNo=0;
		long month=0;
		long year=0;
		long hbaca=0;
		long locId=0;
		
		ArrayList DataList = new ArrayList();
		
		try
		{


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
			
			/*StyleVO[] centerboldStyleVO = new StyleVO[2];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Left");*/

			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 

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


			month = StringUtility.convertToLong(report.getParameterValue("Month")!=null?report.getParameterValue("Month").toString():"-1");
			year = StringUtility.convertToLong(report.getParameterValue("Year")!=null?report.getParameterValue("Year").toString():"0");
			hbaca = StringUtility.convertToLong(report.getParameterValue("HBA/CA")!=null?report.getParameterValue("HBA/CA").toString():"0");
			locId = StringUtility.convertToLong(report.getParameterValue("Department")!=null?report.getParameterValue("Department").toString():"0");

			logger.info("hbaca in excess payment******"+hbaca);
			String BillNoinner="";
			int l=0;
			String subheadCode="";
			String classIds="";
			String desgnIds="";
			String portType="";
			String BillNo="";	    

			BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
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
				l++;
			} 

			//Added by abhilash	
			
			logger.info("bill no is ***********"+BillNo);
			logger.info("bill no is ***********"+BillNoinner);
			logger.info("bill no is ***********"+report.getParameterValue( "Bill No" ));
			
			if (BillNoinner.trim().equals("") || BillNo.equalsIgnoreCase("") )
			{
				logger.info("inside if condition bill no is ***********"+report.getParameterValue( "Bill No" ));
				BillNo = report	.getParameterValue("Bill No") != null ? report.getParameterValue("Bill No").toString().trim() : "";
			}
			//Ended
			
			
			PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			
			//added by roshan
			logger.info("hhii the bill number"+BillNo);
		
			locId=payDao.getLocationCode(Long.parseLong(BillNo));
			logger.info("hhi the location cxod eis "+locId);
			//ended by roshan
			
			
			String DeptName=payDao.getOffice(locId);
			//String DDOCode = payDao.getDdoCode(locId, DeptName);


			List RowList = new ArrayList();
			
			GPFReportDAO reportDAO = new GPFReportDAOImpl(HrPayPaybill.class);
			reportDAO.setSessionFactory(serv.getSessionFactory());
			

			RowList = reportDAO.getLoanDetailsForHBACA(hbaca,Long.valueOf(BillNo),month,year,locId);
			String row0="";
			long row1=0;
			long row2=0;
			long row3=0;
			long row4=0;
			String row5="";
			String row6="";
			long row7=0;
			String row8="";
			String row9="";

			long Outstand=0;
			//double AmountTotal=0;
			String NameDesgId="";
			String MajorHead="";

			if(hbaca!=73)
				MajorHead ="0049";
			else
				MajorHead ="0049";


			double totalAmtDedun=0;

			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			//long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
			//List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);

			/*String ddocode ="";
			if(ddoList.size()>0)
				ddocode = ddoList.get(0).getDdoCode();*/
			String ddocode ="";
			//String TanNo="";
			long locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
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
    			//TanNo=ddoMst.getTanNo();
    		}
    		logger.info("DDO Code is " + ddocode);
			logger.info("Hum Office is "+DeptName);		

			String TresuryName ="";
			String TresuryCode ="";
			List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);

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

			if(RowList.size()!=0)
			{
				//ArrayList dataList=new ArrayList();
				int cnt=1;

				Iterator itr = RowList.iterator();
				/*String employeeName;
				String designationName;
				String proofNumber;
				String grossAmount;*/
				int pageCnt=1;

				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();

					row0 = (rowList[0].toString());
					row1 = ((Long)(rowList[1])).longValue();
					row2 = ((Long)(rowList[2])).longValue();
					row3 = ((Long)(rowList[3])).longValue();
					row4 = ((Long)(rowList[4])).longValue();
					row5 = (rowList[5].toString());
					if(rowList[6]!=null)
					row6 = (rowList[6].toString());
					row7 = ((Double)(rowList[7])).longValue();
					
					//row8=(rowList[8]!=null?rowList[8].toString():"").toString();
					
					if(rowList[8]!=null)
					{
					row8 = (rowList[8].toString());
					}
					else
					{
					row8="";
					}
					
					if(rowList[9]!=null)
					row9 = (rowList[9].toString());
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
					
					if(rowList[9]!=null)
					{
						row9=sdf.format(sdformat.parse(row9));
					}
					else
					{
						row9="";
					}
					
					Outstand = row1 - row2;
					if(rowList[6]!=null)
					{
					NameDesgId = row0 +" ("+row5+") "+"("+row6+")";
					}
					else
					{
						NameDesgId = row0 +" ("+row5+") ";
					}

					String tryVounoandDate = row8+" "+row9;

					ArrayList row = new ArrayList();


					logger.info("row9************" + row9);
					logger.info("tryVounoandDate************" + tryVounoandDate);


					row.add(cnt);//1
					row.add(NameDesgId);//2
					row.add(tryVounoandDate);//3
					row.add(String.valueOf(row1));//4
					row.add(ddocode);//5
					row.add(MajorHead);//6
					row.add(String.valueOf(row4+"/"+row3));//7
					row.add(String.valueOf(row7));//8
					row.add(String.valueOf(row2));//9
					row.add(String.valueOf(Outstand));//10
					
					String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
					if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
					{
						//logger.info("No Of Rec is*****************====>"+noOfRec);
						finalpagesize=Integer.parseInt(noOfRec);
					}	
					
					DataList.add(row);

						totalAmtDedun+=row7;

					logger.info("totalIncomeTax********" + totalAmtDedun);

					if((cnt%finalpagesize)==0) 
					{

						pageCnt++;
						row = new ArrayList();
						row.add(new PageBreak());
						row.add("Data");
						DataList.add(row); 

						StyledData dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(totalAmtDedun);
						row.add(dataStyle1);

						dataStyle1 = new StyledData();
						dataStyle1.setStyles(boldStyleVO);
						dataStyle1.setData(pageCnt);                   
						row.add(dataStyle1);//2



					}
					cnt++;
				}
				ArrayList row22 = new ArrayList();
				row22.add("");
				row22.add("");
				row22.add("");
				StyledData dataStyle1 = new StyledData();
				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData("Total (`):");
				dataStyle1.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
				row22.add(dataStyle1);
				row22.add("");
				row22.add("");
				row22.add("");
				dataStyle1 = new StyledData();
				dataStyle1.setStyles(boldStyleVO);
				dataStyle1.setData(totalAmtDedun);                  
				row22.add(dataStyle1);
				row22.add("");
				DataList.add(row22);  


				ArrayList row12 = new ArrayList();

				logger.info("TOTALLLLLLLLLLLLLLL");


			/*	StyledData dataStyle2 = new StyledData();
				dataStyle2.setStyles(centerboldStyleVO);
				dataStyle2.setData("Total Rupees:  "+ConvertNumbersToWord.convert(Math.round(totalAmtDedun))+" only.");
				dataStyle2.setColspan(10);
				row12.add(dataStyle2);*/
				
				StyleVO[] centerboldStyleVO = new StyleVO[2];
				centerboldStyleVO[0] = new StyleVO();
				centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
				centerboldStyleVO[1] = new StyleVO();
				centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				centerboldStyleVO[1].setStyleValue("Left"); 
				StyledData dataStyle2 = new StyledData();
				dataStyle2.setStyles(centerboldStyleVO);
				dataStyle2.setData("Total Deduction In Words (`):  "+ConvertNumbersToWord.convert(Math.round(totalAmtDedun))+" Only.");
				dataStyle2.addStyle(IReportConstants.STYLE_FONT_FAMILY, "Rupee Foradian");
				dataStyle2.setColspan(10);
				row12.add(dataStyle2);


				for(int c=0;c<10;c++)
					row12.add("");
				row22.add("");





				DataList.add(row12);

			}

			String deptHeader ="";
			
			if(hbaca==70)
			{
				
					deptHeader="Schedule for the Recovery of M.C.A."+System.getProperty("line.separator")+"From Major Head 0049";
			}
			else if (hbaca==73)
			{
				
				deptHeader="Schedule for the Recovery of H.B.A for Construction "+System.getProperty("line.separator")+"From Major Head 0049";;
			}
			else if (hbaca==52)
			{
				deptHeader="Schedule for the Recovery of PA"+System.getProperty("line.separator")+"PA";
			}
			else if (hbaca==53)
			{
				deptHeader="Schedule for the Recovery of TA"+System.getProperty("line.separator")+"TA";
			}

			else if (hbaca==68)
			{
				deptHeader="Schedule for the Recovery of Excess Payment Recovery"+System.getProperty("line.separator")+"Excess Payment Recovery";
			}
			 else if (hbaca==72)
			 {
				 deptHeader="Schedule for the Recovery of H.B.A for Land "+System.getProperty("line.separator")+"From Major Head 0049";
			 }
			 else if (hbaca==78)
			 {
				 deptHeader="Schedule for the Recovery of Oth. Veh.Adv "+System.getProperty("line.separator")+"From Major Head 0049";
			 }
			else
			{
				deptHeader="Schedule for the Recovery of FA"+System.getProperty("line.separator")+"FA";;
			}

			
			ArrayList stData = new ArrayList();
			ArrayList styleList = new ArrayList();
			
			StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
			styledHeader.setColspan(2);
			stData.add(styledHeader);
			stData.add("");
			styleList.add(stData);
			
			String[] monthName = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};        
			//Calendar cale = Calendar.getInstance(); 
			//String MONTH = monthName[cale.get(Calendar.MONTH)]; 	          

			String lstrmonth = String.valueOf(month);
			Integer in = Integer.parseInt(lstrmonth);

			String monthname = monthName[in-1];
			

			String Space=" ";
			
			ArrayList r = new ArrayList();
			r.add(new StyledData("For the Month of "+monthname +Space+year,leftHeader));
			r.add(new StyledData(" ",rightHead));
			styleList.add(r);  
			
			ArrayList r2= new ArrayList();
			r2.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));
			r2.add(new StyledData("Treasury : "+TresuryName+"("+TresuryCode+")",rightHead));
			styleList.add(r2);
			
			
			TabularData tData  = new TabularData(styleList);
			
			tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			tData.addStyle(IReportConstants.BORDER, "No");
			
			tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
			report.setAdditionalHeader(tData);
			


			StyleVO[] centerboldStyleVO1 = new StyleVO[3];
			centerboldStyleVO1[0] = new StyleVO();
			centerboldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			centerboldStyleVO1[1] = new StyleVO();
			centerboldStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO1[1].setStyleValue("Center"); 
			centerboldStyleVO1[2] = new StyleVO();
			centerboldStyleVO1[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
			centerboldStyleVO1[2].setStyleValue("14"); 
			//String ShowSignatureFlg=CheckIfNull(report.getParameterValue( "Show Signature" ));


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
		
		
		reportVO.setReportName("");

		
		
		 

		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}
	
	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	{
		
		/*ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		Hashtable sessionKeys = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.SESSION_KEYS );
		*/Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
		//String date = fmt.format(today);
		SimpleDateFormat fmt1 =new SimpleDateFormat("yyyy");
		String yr = fmt1.format(today);
		fmt1 =new SimpleDateFormat("MM");
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		//String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String month = fmt1.format(today);

		if(month.charAt(0)=='0')
		{
			month=month.substring(1,2);
		}
		if(  report.getReportCode().equals("5000006") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
		}

		if(  report.getReportCode().equals("5000006") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}

	
}
