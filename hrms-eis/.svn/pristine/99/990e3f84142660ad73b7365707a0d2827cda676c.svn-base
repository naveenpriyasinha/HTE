package com.tcs.sgv.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.service.ServiceLocator;


/**
 * Class Description -
 * 
 * 
 * @author Vaibhav Tyagi
 * @version 0.1
 * @since JDK 7.0 April 11,2014
 */
public class AdminwiseDdoReportDAO extends DefaultReportDataFinder{

	private static final Logger gLogger = Logger.getLogger("AdminwiseDdoReportDAO");
	public static String newline = System.getProperty("line.separator");
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	Long gLngLangId = null;
	Long gLngPostId = null;
	Map lMapSeriesHeadCode = null;
	Map requestAttributes = null;
	Session ghibSession = null;

	public Collection findReportData(ReportVO report, Object criteria) throws ReportException 
	{		
		ArrayList dataList = new ArrayList();
		requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		gObjSessionFactory = serviceLocator.getSessionFactorySlave();
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		/*ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
		SessionFactory lObjSessionFactory = serviceLocator.getSessionFactorySlave();*/
		StyleVO[] rowsFontsVO = new StyleVO[3];
		rowsFontsVO[0] = new StyleVO();
		rowsFontsVO[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rowsFontsVO[0].setStyleValue("10");
		rowsFontsVO[1] = new StyleVO();
		rowsFontsVO[1].setStyleId(IReportConstants.BACKGROUNDCOLOR);
		rowsFontsVO[1].setStyleValue("white");
		rowsFontsVO[2] = new StyleVO();
		rowsFontsVO[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		rowsFontsVO[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		report.setStyleList(rowsFontsVO);

		StyleVO[] rowsVO = new StyleVO[3];
		rowsVO[0] = new StyleVO();
		rowsVO[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		rowsVO[0].setStyleValue("12");
		rowsVO[1] = new StyleVO();
		rowsVO[1].setStyleId(IReportConstants.BACKGROUNDCOLOR);
		rowsVO[1].setStyleValue("#B5BABA");
		rowsVO[2] = new StyleVO();
		rowsVO[2].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
		rowsVO[2].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
		report.setStyleList(rowsVO);
	
		String month=null;
		String year=null;
		String regionCode=null;
		String adminId=null;
		String districtId=null;

		List row = null;
		StyledData rowData = null;
		Object[] obj = null;
		Object[] obj1 = null;
		Object[] obj2 = null;

		try{
			
			String prvRegionName=null;
			Long prvRegionCode=0l;			
			//Long regionUDiceTotal=0l;		
			Long regionTotalSchoolConfig=0l;			
			Long regionSchoolApproved=0l;					
			Long regionSancPost=0l;			
			Long regionTotalEmpWorking=0l;			
			Long regionTotalEmpConfig=0l;			
			Long regionDraftEmp=0l;			
			Long regionPendingEmp=0l;			
			Long regionApprovedEmp=0l;			
			Long regionPaybillGenerated=0l;			
			Long regionPaybillConsolidated=0l;		
			Long regionEmpCovered=0l;
			Long regionPaybillForwarded=0l;		
			Long regionEmpPayLocked=0l;			
			Long regionPaybillLocked=0l;		
			Long regionEmpPayGenerated=0l;
			Long regionEmpPayForwarded=0l;
			
			


			month = (String)report.getParameterValue("month");
			year = (String)report.getParameterValue("year");
			regionCode = (String)report.getParameterValue("regionCode");
			adminId = (String)report.getParameterValue("adminTpye");
			districtId = (String)report.getParameterValue("districtId");
			
			
			gLogger.info("adminId:::"+adminId);
			gLogger.info("districtId:::"+districtId);
			gLogger.info("month87:::"+month);
			gLogger.info("year:88::"+year);
			gLogger.info("regionCode:89::"+regionCode);
			gLogger.info("adminId:90::"+adminId);
			gLogger.info("districtId:91::"+districtId);
			
			List instReport=getInstData(regionCode,adminId,districtId);
			List empReport=getEmpData(regionCode,adminId,districtId);
			List schoolReport= getSchoolData(month,year,regionCode,adminId,districtId);

			gLogger.info("schoolReport:::"+schoolReport);
			gLogger.info("schoolReport::size:"+schoolReport.size());
			gLogger.info("empReport:::"+empReport);
			gLogger.info("empReport::size:"+empReport.size());
			gLogger.info("instReport:::"+instReport);
			gLogger.info("instReport::size:"+instReport.size());
			/*commented by Saurabh @
			Map empReport = getEmpData(districtId, adminType);
			Map postReport = getPostList(districtId,adminType);
			*/

			Iterator itr =  instReport.iterator();
			Iterator itr1 = empReport.iterator();
			Iterator itr2 = schoolReport.iterator();
			

			
			/*Object[] obj = null;
			Object[] obj1 = null;
			Object[] obj2 = null;*/

			String ddoCode=null;
			String offName=null;
			
			String totalSchoolConf= null;
			String totalSchoolApproved=null;
			//String uDiceData=null;
			String totalSancPost=null;
			String totalEmpWorking=null;
			
		    String totalEmpConfig=null;
			String draftEmp=null;
			String pendingEmp=null;
			String approvedEmp=null;
			
			
			Long noOfPaybillGenerated=0l;
			Long noOfPaybillConsolidated=0l;
			Long noOfEmployeesCovered=0l;
			Long noOfPaybillForwarded=0l;
			//String[] paybillGenerationDetails=null;
			Long noOfEmpWhosePaybillLocked=0l;
			Long noOfPaybillsLocked=0l;
			Long noOfEmpWhosePaybillGenerated=0l;
			Long noOfEmpWhosePaybillForwarded=0l;
			
			String[] empConfigDetails=null;
			//String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=8000067&action=generateReport&DirectReport=TRUE&displayOK=FALSE";
			//added for EmployeeWiseReportDAO
			String urlPrefix3 = "ifms.htm?actionFlag=reportService&reportCode=8000068&action=generateReport&DirectReport=TRUE&displayOK=FALSE";

			
			gLogger.info("schoolReport------ " + schoolReport);
			gLogger.info("schoolReport--size---- " + schoolReport.size());
			gLogger.info("empReport:::"+empReport);
			gLogger.info("empReport::size:"+empReport.size());
			gLogger.info("instReport:::"+instReport);
			gLogger.info("instReport::size:"+instReport.size());
			String adminIdd=null;
			int count=0;
			//int count1=0;
			//int count2=0;
			
			if( instReport.size()>0 && instReport.size()>=empReport.size()){
			while(itr.hasNext()){
				count=count+1;
				gLogger.info("count is "+count);
				
				
				//getInstData
				obj= (Object[]) itr.next();
				if(obj!=null){
					ddoCode = obj[0].toString();
					offName = obj[1].toString();
					regionCode = obj[2].toString();
					districtId = obj[3].toString();
					adminIdd= obj[4].toString();
					totalSchoolConf= obj[5].toString();
					totalSchoolApproved = obj[6].toString();
					//uDiceData = obj[9].toString();
					if(obj[7]!=null){
						totalSancPost= obj[7].toString();
							}
							else{
								totalSancPost = "0";
							}
					//totalSancPost= obj[7].toString();
					if(obj[8]!=null){
						totalEmpWorking= obj[8].toString();
							}
							else{
								totalEmpWorking = "0";
							}
					//totalEmpWorking= obj[8].toString();
					
					}
				else{
						totalSchoolConf= "0";
						totalSchoolApproved = "0";
						//uDiceData = "0";
						totalSancPost= "0";
						totalEmpWorking= "0";
						}
				
				
				
				//getEmpData
				//Iterator itr1 = empReport.iterator();
				//Object[] obj1 = null;
				
				while(itr1.hasNext()){
				obj1= (Object[]) itr1.next();
				if(obj1!=null){
			    totalEmpConfig= obj1[5].toString();
				draftEmp = obj1[6].toString();
				pendingEmp = obj1[7].toString();
				approvedEmp= obj1[8].toString();
				break;
				}
				else{
					totalEmpConfig="0";
					draftEmp="0";
					pendingEmp="0";
					approvedEmp="0";
				}
				}	
				
				//getSchoolData
				//Iterator itr2 = instReport.iterator();
				
				while(itr2.hasNext()){
					
				obj2= (Object[]) itr2.next();
				
				if(obj2!=null){
					
					if(obj2[5]!=null){
				noOfPaybillGenerated = Long.parseLong(obj2[5].toString());
					}
					else{
						noOfPaybillGenerated = 0l;
					}
					if(obj2[6]!=null){
				noOfPaybillConsolidated= Long.parseLong(obj2[6].toString());
					}
					else{
						noOfPaybillConsolidated = 0l;
					}
					if(obj2[7]!=null){
				noOfEmployeesCovered = Long.parseLong(obj2[7].toString());
					}
					else{
						noOfEmployeesCovered = 0l;
					}
					if(obj2[8]!=null){
				noOfPaybillForwarded= Long.parseLong(obj2[8].toString());
					}else{
						noOfPaybillForwarded = 0l;
					}
					if(obj2[9]!=null){
				noOfEmpWhosePaybillLocked = Long.parseLong(obj2[9].toString());
					}
					else{
						noOfEmpWhosePaybillLocked = 0l;
					}
					if(obj2[10]!=null){
				noOfPaybillsLocked= Long.parseLong(obj2[10].toString());
					}
					else{
						noOfPaybillsLocked = 0l;
					}
					if(obj2[11]!=null){
				noOfEmpWhosePaybillGenerated = Long.parseLong(obj2[11].toString());
					}
					else{
						noOfEmpWhosePaybillGenerated = 0l;
					}
					if(obj2[12]!=null){
				noOfEmpWhosePaybillForwarded= Long.parseLong(obj2[12].toString());
					}
					else{
						noOfEmpWhosePaybillForwarded = 0l;
					}
					break;
					
					
					
				}
				
				else{
					noOfPaybillGenerated=0l;
					noOfPaybillConsolidated=0l;
					noOfEmployeesCovered=0l;
					noOfPaybillForwarded=0l;
					noOfEmpWhosePaybillLocked=0l;
					noOfPaybillsLocked=0l;
					noOfEmpWhosePaybillGenerated=0l;
					noOfEmpWhosePaybillForwarded=0l;
				}
				
			}
				
				
				
				
				
				
				
			    //1Region Name
				gLogger.info("regionCode11111------" + regionCode);
				row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(regionCode);
				row.add(rowData);
				
				//2District Name
				gLogger.info("districtId------" + districtId);
				
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(districtId);
				row.add(rowData);
				
				//3Admin Office name
				gLogger.info("adminId------" + adminIdd);
				//row.add(new URLData(adminId,urlPrefix+"&adminTpye="+adminId+"&districtId="+districtId+"&month="+month+"&year="+year));
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(adminIdd); 
				row.add(rowData);
				
				//4 DDO Code
				gLogger.info("ddoCode------" + ddoCode);
				//row = new ArrayList();
				//row.add(new URLData(ddoCode,urlPrefix+"&adminType="+adminType+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year));
				//row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(ddoCode);
				row.add(rowData);
				//row.add(ddoCode);
				//gLogger.info("complete URL ------ " + urlPrefix+"&adminType="+adminId+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year);
				gLogger.info("adminType ----" + adminId);
				gLogger.info("districtId------" + districtId);
				gLogger.info("year------" + year);
				gLogger.info("month------" + month);
				gLogger.info("ddoCode------" + ddoCode);
				
				
				//5 Office Name
				gLogger.info("offName------" + offName);
				
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(offName);
				row.add(rowData);
				gLogger.info("offName------" + offName);
				
				
				/*//6
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(uDiceData);
				row.add(rowData);*/

				//7
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(totalSchoolConf);
				row.add(rowData);

				//8
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(totalSchoolApproved);
				row.add(rowData);

			    //9
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(totalSancPost);
				row.add(rowData);

				//10
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(totalEmpWorking);
				row.add(rowData);

				//11
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(totalEmpConfig);
				row.add(rowData);

				//12
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(draftEmp);
				row.add(rowData);

				//13
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(pendingEmp);
				row.add(rowData);

				//14
				rowData = new StyledData();
				rowData.setStyles(rowsFontsVO);
				rowData.setData(approvedEmp);
				row.add(rowData);
				
				
				if(count<=schoolReport.size()){
					gLogger.info("count------" + count);
					
					
					//15 Paybill generated 
					gLogger.info("noOfPaybillGenerated------" + noOfPaybillGenerated);
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillGenerated);
					row.add(rowData);
					
					//16 Paybill Forwarded
					gLogger.info("noOfEmployeesCovered------" + noOfPaybillForwarded);
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillForwarded);
					row.add(rowData);
					
					//17 Paybill Consolidated
					gLogger.info("noOfPaybillConsolidated------" + noOfPaybillConsolidated);
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillConsolidated);
					row.add(rowData);
					
					//18 Paybill Locked
					
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillsLocked);
					row.add(rowData);
					
					//19 Employee Paybill Generated
					
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpWhosePaybillGenerated);*/
	                //row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
					//row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Master"));
					
					row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

				
					gLogger.info("month------" + month);
					gLogger.info("year------" + year);
					gLogger.info("regionCode------" + regionCode);
					gLogger.info("districtId------" + districtId);
					gLogger.info("adminId------" + adminId);
					gLogger.info("adminId------" + adminId);
					//ReportType
					
					//20 Employee Paybill forwarded
					
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpWhosePaybillForwarded);
	*/				
					//row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
					row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
					//21 Employee Paybill consolidated
					gLogger.info("noOfEmployeesCovered------" + noOfEmployeesCovered);
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmployeesCovered);*/
					//row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
					row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
					//22 Employee Paybill locked
					gLogger.info("noOfEmpWhosePaybillLocked------" + noOfEmpWhosePaybillLocked);
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpWhosePaybillLocked);*/
				//	row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"));
					row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
				}
				
				else{
					noOfPaybillGenerated=0l;
					noOfPaybillConsolidated=0l;
					noOfEmployeesCovered=0l;
					noOfPaybillForwarded=0l;
					noOfEmpWhosePaybillLocked=0l;
					noOfPaybillsLocked=0l;
					noOfEmpWhosePaybillGenerated=0l;
					noOfEmpWhosePaybillForwarded=0l;
					
					//15 Paybill generated 
					gLogger.info("noOfPaybillGenerated------" + noOfPaybillGenerated);
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillGenerated);
					row.add(rowData);
					
					//16 Paybill Forwarded
					gLogger.info("noOfEmployeesCovered------" + noOfPaybillForwarded);
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillForwarded);
					row.add(rowData);
					
					//17 Paybill Consolidated
					gLogger.info("noOfPaybillConsolidated------" + noOfPaybillConsolidated);
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillConsolidated);
					row.add(rowData);
					
					//18 Paybill Locked
					
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfPaybillsLocked);
					row.add(rowData);
					
					//19 Employee Paybill Generated
					
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpWhosePaybillGenerated);*/
	                //row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
					//row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Master"));
					
					row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

				
					gLogger.info("month------" + month);
					gLogger.info("year------" + year);
					gLogger.info("regionCode------" + regionCode);
					gLogger.info("districtId------" + districtId);
					gLogger.info("adminId------" + adminId);
					gLogger.info("adminId------" + adminId);
					//ReportType
					
					//20 Employee Paybill forwarded
					
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpWhosePaybillForwarded);
	*/				
					//row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
					row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
					//21 Employee Paybill consolidated
					gLogger.info("noOfEmployeesCovered------" + noOfEmployeesCovered);
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmployeesCovered);*/
					//row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
					row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
					//22 Employee Paybill locked
					gLogger.info("noOfEmpWhosePaybillLocked------" + noOfEmpWhosePaybillLocked);
					/*rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(noOfEmpWhosePaybillLocked);*/
				//	row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"));
					row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
				}

							
				
				
				prvRegionName=regionCode;
				//regionUDiceTotal+=Long.parseLong(uDiceData);
				regionTotalSchoolConfig+=Long.parseLong(totalSchoolConf);
				regionSchoolApproved+=Long.parseLong(totalSchoolApproved);	
				regionSancPost+=Long.parseLong(totalSancPost);
				regionTotalEmpWorking+=Long.parseLong(totalEmpWorking);
				regionTotalEmpConfig+=Long.parseLong(totalEmpConfig);
				regionDraftEmp+=Long.parseLong(draftEmp);
				regionPendingEmp+=Long.parseLong(pendingEmp);
				regionApprovedEmp+=Long.parseLong(approvedEmp);
				regionPaybillGenerated+=noOfPaybillGenerated;
				regionPaybillConsolidated+=noOfPaybillConsolidated;
				regionEmpCovered+=noOfEmployeesCovered;
				regionPaybillForwarded+=noOfPaybillForwarded;				
				regionEmpPayLocked+=noOfEmpWhosePaybillLocked;				
				regionPaybillLocked+=noOfPaybillsLocked;				
				regionEmpPayGenerated+=noOfEmpWhosePaybillGenerated;	
				regionEmpPayForwarded=regionEmpPayForwarded+noOfEmpWhosePaybillForwarded;

					
								
				gLogger.info("regionEmpPayForwarded-Before-----" + regionEmpPayForwarded);
				gLogger.info("noOfEmpWhosePaybillForwarded------" + noOfEmpWhosePaybillForwarded);
				gLogger.info("regionEmpPayForwarded--After----" + regionEmpPayForwarded);
				gLogger.info("regionEmpCovered------" + regionEmpCovered);
				gLogger.info("regionPaybillForwarded------" + regionPaybillForwarded);
				gLogger.info("regionEmpPayLocked------" + regionEmpPayLocked);
				gLogger.info("regionPaybillLocked------" + regionPaybillLocked);
				gLogger.info("regionEmpPayGenerated------" + regionEmpPayGenerated);
				
				
				dataList.add(row);
			}
				
				
				
			
			 //1Region Name
			gLogger.info("regionCode11111------" + prvRegionName);
			row = new ArrayList();
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData("");
			row.add(rowData);
			
			//2District Name
			gLogger.info("districtId------" + districtId);
			
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(prvRegionName+" DIVISON TOTAL ");
			row.add(rowData);
			
			//3Admin Office name
			gLogger.info("adminId------" + adminId);
			//row.add(new URLData(adminId,urlPrefix+"&adminTpye="+adminId+"&districtId="+districtId+"&month="+month+"&year="+year));
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData("");
			row.add(rowData);
			
			//4 DDO Code
			gLogger.info("ddoCode------" + ddoCode);
			//row = new ArrayList();
			//row.add(new URLData(ddoCode,urlPrefix+"&adminType="+adminType+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year));
			//row = new ArrayList();
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData("");
			row.add(rowData);
			//row.add(ddoCode);
			//gLogger.info("complete URL ------ " + urlPrefix+"&adminType="+adminId+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year);
			gLogger.info("adminType ----" + adminId);
			gLogger.info("districtId------" + districtId);
			gLogger.info("year------" + year);
			gLogger.info("month------" + month);
			gLogger.info("ddoCode------" + ddoCode);
			
			
			//5 Office Name
			gLogger.info("offName------" + offName);
			
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData("");
			row.add(rowData);
			gLogger.info("offName------" + offName);
			
			
			/*//6
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionUDiceTotal);
			row.add(rowData);*/

			//7
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionTotalSchoolConfig);
			row.add(rowData);

			//8
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionSchoolApproved);
			row.add(rowData);

		    //9
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionSancPost);
			row.add(rowData);

			//10
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionTotalEmpWorking);
			row.add(rowData);

			//11
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionTotalEmpConfig);
			row.add(rowData);

			//12
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionDraftEmp);
			row.add(rowData);

			//13
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionPendingEmp);
			row.add(rowData);

			//14
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionApprovedEmp);
			row.add(rowData);

			//15 Paybill generated 
			gLogger.info("noOfPaybillGenerated------" + regionPaybillGenerated);
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionPaybillGenerated);
			row.add(rowData);
			
			//16 Paybill Forwarded
			gLogger.info("noOfEmployeesCovered------" + regionPaybillForwarded);
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionPaybillForwarded);
			row.add(rowData);
			
			//17 Paybill Consolidated
			gLogger.info("noOfPaybillConsolidated------" + regionPaybillConsolidated);
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionPaybillConsolidated);
			row.add(rowData);
			
			//18 Paybill Locked
			
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionPaybillLocked);
			row.add(rowData);
			
			//19 Employee Paybill Generated
			
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionEmpPayGenerated);
			row.add(rowData);
			
			
			//20 Employee Paybill forwarded
			
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionEmpPayForwarded);
			row.add(rowData);
			
			
			//21 Employee Paybill consolidated
			gLogger.info("noOfEmployeesCovered------" + regionEmpCovered);
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionEmpCovered);
			row.add(rowData);
			
			
			//22 Employee Paybill locked
			gLogger.info("noOfEmpWhosePaybillLocked------" + regionEmpPayLocked);
			rowData = new StyledData();
			rowData.setStyles(rowsVO);
			rowData.setData(regionEmpPayLocked);
			row.add(rowData);
			
			dataList.add(row);
			
			}
				
			
			
			//
			/*else{

				while(itr1.hasNext()){
					count1=count1+1;
					gLogger.info("count1 is "+count1);
					count2=count2+1;
					gLogger.info("count2 is "+count2);
					
					
					obj1= (Object[]) itr1.next();
					if(obj1!=null){
				    totalEmpConfig= obj1[5].toString();
					draftEmp = obj1[6].toString();
					pendingEmp = obj1[7].toString();
					approvedEmp= obj1[8].toString();
					
					}
					else{
						totalEmpConfig="0";
						draftEmp="0";
						pendingEmp="0";
						approvedEmp="0";
					}
					
					//getEmpData
					//Iterator itr1 = empReport.iterator();
					//Object[] obj1 = null;
					
					while(itr.hasNext()){
						//getInstData
						obj= (Object[]) itr.next();
						if(obj!=null){
							ddoCode = obj[0].toString();
							offName = obj[1].toString();
							regionCode = obj[2].toString();
							districtId = obj[3].toString();
							adminIdd= obj[4].toString();
							totalSchoolConf= obj[5].toString();
							totalSchoolApproved = obj[6].toString();
							//uDiceData = obj[9].toString();
							totalSancPost= obj[7].toString();
							totalEmpWorking= obj[8].toString();
							break;
							}
						else{
								totalSchoolConf= "0";
								totalSchoolApproved = "0";
								//uDiceData = "0";
								totalSancPost= "0";
								totalEmpWorking= "0";
							}
						}	
					
					//getSchoolData
					//Iterator itr2 = instReport.iterator();
					while(itr2.hasNext()){
					obj2= (Object[]) itr2.next();
				   
				    
					if(obj2!=null){
						
						if(obj2[5]!=null){
					noOfPaybillGenerated = Long.parseLong(obj2[5].toString());
						}
						else{
							noOfPaybillGenerated = 0l;
						}
						if(obj2[6]!=null){
					noOfPaybillConsolidated= Long.parseLong(obj2[6].toString());
						}
						else{
							noOfPaybillConsolidated = 0l;
						}
						if(obj2[7]!=null){
					noOfEmployeesCovered = Long.parseLong(obj2[7].toString());
						}
						else{
							noOfEmployeesCovered = 0l;
						}
						if(obj2[8]!=null){
					noOfPaybillForwarded= Long.parseLong(obj2[8].toString());
						}else{
							noOfPaybillForwarded = 0l;
						}
						if(obj2[9]!=null){
					noOfEmpWhosePaybillLocked = Long.parseLong(obj2[9].toString());
						}
						else{
							noOfEmpWhosePaybillLocked = 0l;
						}
						if(obj2[10]!=null){
					noOfPaybillsLocked= Long.parseLong(obj2[10].toString());
						}
						else{
							noOfPaybillsLocked = 0l;
						}
						if(obj2[11]!=null){
					noOfEmpWhosePaybillGenerated = Long.parseLong(obj2[11].toString());
						}
						else{
							noOfEmpWhosePaybillGenerated = 0l;
						}
						if(obj2[12]!=null){
					noOfEmpWhosePaybillForwarded= Long.parseLong(obj2[12].toString());
						}
						else{
							noOfEmpWhosePaybillForwarded = 0l;
						}
						break;
					}
					else{
						noOfPaybillGenerated=0l;
						noOfPaybillConsolidated=0l;
						noOfEmployeesCovered=0l;
						noOfPaybillForwarded=0l;
						noOfEmpWhosePaybillLocked=0l;
						noOfPaybillsLocked=0l;
						noOfEmpWhosePaybillGenerated=0l;
						noOfEmpWhosePaybillForwarded=0l;
					}
					
					}
					
					
					
					
					
					
					
				    //1Region Name
					gLogger.info("regionCode11111------" + regionCode);
					row = new ArrayList();
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(regionCode);
					row.add(rowData);
					
					//2District Name
					gLogger.info("districtId------" + districtId);
					
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(districtId);
					row.add(rowData);
					
					//3Admin Office name
					gLogger.info("adminId------" + adminIdd);
					//row.add(new URLData(adminId,urlPrefix+"&adminTpye="+adminId+"&districtId="+districtId+"&month="+month+"&year="+year));
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(adminIdd); 
					row.add(rowData);
					
					//4 DDO Code
					gLogger.info("ddoCode------" + ddoCode);
					//row = new ArrayList();
					//row.add(new URLData(ddoCode,urlPrefix+"&adminType="+adminType+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year));
					//row = new ArrayList();
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(ddoCode);
					row.add(rowData);
					//row.add(ddoCode);
					//gLogger.info("complete URL ------ " + urlPrefix+"&adminType="+adminId+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year);
					gLogger.info("adminType ----" + adminId);
					gLogger.info("districtId------" + districtId);
					gLogger.info("year------" + year);
					gLogger.info("month------" + month);
					gLogger.info("ddoCode------" + ddoCode);
					
					
					//5 Office Name
					gLogger.info("offName------" + offName);
					
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(offName);
					row.add(rowData);
					gLogger.info("offName------" + offName);
					
					
					//6
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(uDiceData);
					row.add(rowData);

					//7
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(totalSchoolConf);
					row.add(rowData);

					//8
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(totalSchoolApproved);
					row.add(rowData);

				    //9
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(totalSancPost);
					row.add(rowData);

					//10
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(totalEmpWorking);
					row.add(rowData);

					//11
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(totalEmpConfig);
					row.add(rowData);

					//12
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(draftEmp);
					row.add(rowData);

					//13
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(pendingEmp);
					row.add(rowData);

					//14
					rowData = new StyledData();
					rowData.setStyles(rowsFontsVO);
					rowData.setData(approvedEmp);
					row.add(rowData);

					if(count1<=schoolReport.size()){
						gLogger.info("count1------" + count1);
						
						
						//15 Paybill generated 
						gLogger.info("noOfPaybillGenerated------" + noOfPaybillGenerated);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillGenerated);
						row.add(rowData);
						
						//16 Paybill Forwarded
						gLogger.info("noOfEmployeesCovered------" + noOfPaybillForwarded);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillForwarded);
						row.add(rowData);
						
						//17 Paybill Consolidated
						gLogger.info("noOfPaybillConsolidated------" + noOfPaybillConsolidated);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillConsolidated);
						row.add(rowData);
						
						//18 Paybill Locked
						
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillsLocked);
						row.add(rowData);
						
						//19 Employee Paybill Generated
						
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmpWhosePaybillGenerated);
		                //row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
						//row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Master"));
						
						row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
						gLogger.info("month------" + month);
						gLogger.info("year------" + year);
						gLogger.info("regionCode------" + regionCode);
						gLogger.info("districtId------" + districtId);
						gLogger.info("adminId------" + adminId);
						gLogger.info("adminId------" + adminId);
						//ReportType
						
						//20 Employee Paybill forwarded
						
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmpWhosePaybillForwarded);
						
						//row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
						row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

						
						//21 Employee Paybill consolidated
						gLogger.info("noOfEmployeesCovered------" + noOfEmployeesCovered);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmployeesCovered);
						//row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
						row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

						
						//22 Employee Paybill locked
						gLogger.info("noOfEmpWhosePaybillLocked------" + noOfEmpWhosePaybillLocked);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmpWhosePaybillLocked);
					//	row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"));
						row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

						
					}
					
					else{
						noOfPaybillGenerated=0l;
						noOfPaybillConsolidated=0l;
						noOfEmployeesCovered=0l;
						noOfPaybillForwarded=0l;
						noOfEmpWhosePaybillLocked=0l;
						noOfPaybillsLocked=0l;
						noOfEmpWhosePaybillGenerated=0l;
						noOfEmpWhosePaybillForwarded=0l;
						
						//15 Paybill generated 
						gLogger.info("noOfPaybillGenerated------" + noOfPaybillGenerated);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillGenerated);
						row.add(rowData);
						
						//16 Paybill Forwarded
						gLogger.info("noOfEmployeesCovered------" + noOfPaybillForwarded);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillForwarded);
						row.add(rowData);
						
						//17 Paybill Consolidated
						gLogger.info("noOfPaybillConsolidated------" + noOfPaybillConsolidated);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillConsolidated);
						row.add(rowData);
						
						//18 Paybill Locked
						
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfPaybillsLocked);
						row.add(rowData);
						
						//19 Employee Paybill Generated
						
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmpWhosePaybillGenerated);
		                //row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
						//row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Master"));
						
						row.add(new URLData(noOfEmpWhosePaybillGenerated,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"1"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

					
						gLogger.info("month------" + month);
						gLogger.info("year------" + year);
						gLogger.info("regionCode------" + regionCode);
						gLogger.info("districtId------" + districtId);
						gLogger.info("adminId------" + adminId);
						gLogger.info("adminId------" + adminId);
						//ReportType
						
						//20 Employee Paybill forwarded
						
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmpWhosePaybillForwarded);
						
						//row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
						row.add(new URLData(noOfEmpWhosePaybillForwarded,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"2"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

						
						//21 Employee Paybill consolidated
						gLogger.info("noOfEmployeesCovered------" + noOfEmployeesCovered);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmployeesCovered);
						//row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));
						row.add(new URLData(noOfEmployeesCovered,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"3"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

						
						//22 Employee Paybill locked
						gLogger.info("noOfEmpWhosePaybillLocked------" + noOfEmpWhosePaybillLocked);
						rowData = new StyledData();
						rowData.setStyles(rowsFontsVO);
						rowData.setData(noOfEmpWhosePaybillLocked);
					//	row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"));
						row.add(new URLData(noOfEmpWhosePaybillLocked,urlPrefix3+"&month="+month+"&year="+year+"&regionCode="+regionCode+"&districtId="+districtId+"&adminType="+adminId+"&statusFlag="+"4"+"&ReportType="+"Admin"+"&ddoCode="+ddoCode));

						
					}				
					
					
					prvRegionName=regionCode;
					//regionUDiceTotal+=Long.parseLong(uDiceData);
					regionTotalSchoolConfig+=Long.parseLong(totalSchoolConf);
					regionSchoolApproved+=Long.parseLong(totalSchoolApproved);	
					regionSancPost+=Long.parseLong(totalSancPost);
					regionTotalEmpWorking+=Long.parseLong(totalEmpWorking);
					regionTotalEmpConfig+=Long.parseLong(totalEmpConfig);
					regionDraftEmp+=Long.parseLong(draftEmp);
					regionPendingEmp+=Long.parseLong(pendingEmp);
					regionApprovedEmp+=Long.parseLong(approvedEmp);
					regionPaybillGenerated+=noOfPaybillGenerated;
					regionPaybillConsolidated+=noOfPaybillConsolidated;
					regionEmpCovered+=noOfEmployeesCovered;
					regionPaybillForwarded+=noOfPaybillForwarded;				
					regionEmpPayLocked+=noOfEmpWhosePaybillLocked;				
					regionPaybillLocked+=noOfPaybillsLocked;				
					regionEmpPayGenerated+=noOfEmpWhosePaybillGenerated;	
					regionEmpPayForwarded=regionEmpPayForwarded+noOfEmpWhosePaybillForwarded;

						
									
					gLogger.info("regionEmpPayForwarded-Before-----" + regionEmpPayForwarded);
					gLogger.info("noOfEmpWhosePaybillForwarded------" + noOfEmpWhosePaybillForwarded);
					gLogger.info("regionEmpPayForwarded--After----" + regionEmpPayForwarded);
					gLogger.info("regionEmpCovered------" + regionEmpCovered);
					gLogger.info("regionPaybillForwarded------" + regionPaybillForwarded);
					gLogger.info("regionEmpPayLocked------" + regionEmpPayLocked);
					gLogger.info("regionPaybillLocked------" + regionPaybillLocked);
					gLogger.info("regionEmpPayGenerated------" + regionEmpPayGenerated);
					
					
					dataList.add(row);
				}
					
					
					
				
				 //1Region Name
				gLogger.info("regionCode11111------" + prvRegionName);
				row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData("");
				row.add(rowData);
				
				//2District Name
				gLogger.info("districtId------" + districtId);
				
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(prvRegionName+" DIVISON TOTAL ");
				row.add(rowData);
				
				//3Admin Office name
				gLogger.info("adminId------" + adminId);
				//row.add(new URLData(adminId,urlPrefix+"&adminTpye="+adminId+"&districtId="+districtId+"&month="+month+"&year="+year));
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData("");
				row.add(rowData);
				
				//4 DDO Code
				gLogger.info("ddoCode------" + ddoCode);
				//row = new ArrayList();
				//row.add(new URLData(ddoCode,urlPrefix+"&adminType="+adminType+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year));
				//row = new ArrayList();
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData("");
				row.add(rowData);
				//row.add(ddoCode);
				//gLogger.info("complete URL ------ " + urlPrefix+"&adminType="+adminId+"&ddoCode="+ddoCode+"&districtId="+districtId+"&month="+month+"&year="+year);
				gLogger.info("adminType ----" + adminId);
				gLogger.info("districtId------" + districtId);
				gLogger.info("year------" + year);
				gLogger.info("month------" + month);
				gLogger.info("ddoCode------" + ddoCode);
				
				
				//5 Office Name
				gLogger.info("offName------" + offName);
				
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData("");
				row.add(rowData);
				gLogger.info("offName------" + offName);
				
				
				//6
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionUDiceTotal);
				row.add(rowData);

				//7
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionTotalSchoolConfig);
				row.add(rowData);

				//8
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionSchoolApproved);
				row.add(rowData);

			    //9
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionSancPost);
				row.add(rowData);

				//10
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionTotalEmpWorking);
				row.add(rowData);

				//11
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionTotalEmpConfig);
				row.add(rowData);

				//12
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionDraftEmp);
				row.add(rowData);

				//13
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPendingEmp);
				row.add(rowData);

				//14
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionApprovedEmp);
				row.add(rowData);

				//15 Paybill generated 
				gLogger.info("noOfPaybillGenerated------" + regionPaybillGenerated);
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillGenerated);
				row.add(rowData);
				
				//16 Paybill Forwarded
				gLogger.info("noOfEmployeesCovered------" + regionPaybillForwarded);
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillForwarded);
				row.add(rowData);
				
				//17 Paybill Consolidated
				gLogger.info("noOfPaybillConsolidated------" + regionPaybillConsolidated);
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillConsolidated);
				row.add(rowData);
				
				//18 Paybill Locked
				
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionPaybillLocked);
				row.add(rowData);
				
				//19 Employee Paybill Generated
				
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpPayGenerated);
				row.add(rowData);
				
				
				//20 Employee Paybill forwarded
				
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpPayForwarded);
				row.add(rowData);
				
				
				//21 Employee Paybill consolidated
				gLogger.info("noOfEmployeesCovered------" + regionEmpCovered);
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpCovered);
				row.add(rowData);
				
				
				//22 Employee Paybill locked
				gLogger.info("noOfEmpWhosePaybillLocked------" + regionEmpPayLocked);
				rowData = new StyledData();
				rowData.setStyles(rowsVO);
				rowData.setData(regionEmpPayLocked);
				row.add(rowData);
				
				dataList.add(row);
				
				
				
			}*/
			
	}	
			 
					catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		} 
	    adminId = null;
		districtId=null;
		regionCode=null;
	    row = null;
		rowData = null;
		return dataList;
	}


	
	private List getInstData(String regionCode,String adminId,String districtId){

		List instList=null;
		StringBuilder lSBQuery = new StringBuilder();
		gLogger.info("regionCode is "+regionCode);
		gLogger.info("adminId is "+adminId);
		gLogger.info("districtId is "+districtId);
		
		
		lSBQuery.append(" SELECT OFF.DDO_CODE, off.off_name, REGION.REGION_NAME,DIST.DISTRICT_NAME,ADMIN.ADMIN_NAME, ");
	    lSBQuery.append(" COUNT(CASE WHEN RLT.STATUS IN(-1,0,1) THEN 1 ELSE NULL END) AS TOTAL_SCHOOL_CONF,");
		lSBQuery.append(" COUNT(CASE WHEN RLT.STATUS = 1 THEN 1 ELSE NULL END) AS APPROVED,");
		lSBQuery.append(" SUM(TMP.SANC_POST_COUNT) AS TOTAL_SANCTIONED_POST,");
		lSBQuery.append(" SUM(TMP.EMP_WORK) AS EMPLOYEE_WORKING");
		lSBQuery.append(" FROM RLT_ZP_DDO_MAP RLT");
		lSBQuery.append(" INNER JOIN ORG_DDO_MST DDO ON RLT.ZP_DDO_CODE = DDO.DDO_CODE ");
		lSBQuery.append(" LEFT JOIN (   SELECT   POST.LOCATION_CODE AS LOC,   COUNT(POST.POST_ID) AS SANC_POST_COUNT,   COUNT(CASE WHEN USER.ACTIVATE_FLAG = 1 THEN 1 ELSE NULL END) AS EMP_WORK   FROM ORG_POST_MST POST   LEFT JOIN ORG_USERPOST_RLT USER ON USER.POST_ID = POST.POST_ID   WHERE POST.ACTIVATE_FLAG = 1   AND (POST.END_DATE IS NULL OR POST.END_DATE > SYSDATE)   AND POST.POST_TYPE_LOOKUP_ID IS NOT NULL   GROUP BY POST.LOCATION_CODE)TMP ON TMP.LOC = DDO.LOCATION_CODE");
		lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE = RLT.ZP_DDO_CODE");
		lSBQuery.append(" AND UPPER(OFF.DDO_OFFICE) = 'YES'");
		lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT = DIST.DISTRICT_ID");
		lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE = REGION.REGION_CODE");
		lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE = ADMIN.ID");
		lSBQuery.append(" WHERE RLT.STATUS <> -2");
		lSBQuery.append(" AND REGION.REGION_CODE = "+regionCode+" AND DDO.DDO_TYPE ="+adminId+" and DIST.DISTRICT_ID="+districtId+" ");
		lSBQuery.append(" GROUP BY OFF.DDO_CODE,off.off_name,REGION.REGION_NAME,DIST.DISTRICT_NAME,ADMIN.ADMIN_NAME,DIST.REGION_ORDER ");
		lSBQuery.append(" ORDER BY REGION.REGION_NAME,DIST.REGION_ORDER,ADMIN.ADMIN_NAME  ");
		
		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is is is"+lSBQuery.toString());
		/*Query.setParameter("admin", adminId);
		Query.setParameter("district", district);*/

		instList = Query.list();


		return instList;
	}

	//added by Saurabh S
	
	private List getEmpData(String regionCode,String adminId,String districtId){

		List empList=null;
		StringBuilder lSBQuery = new StringBuilder();
		gLogger.info("regionCode is "+regionCode);
		gLogger.info("adminId is "+adminId);
		gLogger.info("districtId is "+districtId);
		
	
		lSBQuery.append(" SELECT OFF.DDO_CODE,off.off_name, REGION.REGION_NAME,DIST.DISTRICT_NAME,ADMIN.ADMIN_NAME, ");
	    lSBQuery.append(" count(case when emp.ZP_STATUS in (-1,0,2,3,4,10) then 1 else null end ) as Total_EMployee,");
		lSBQuery.append(" count(case when emp.ZP_STATUS in (-1,0) then 1 else null end ) as draft,");
		lSBQuery.append(" count(case when emp.ZP_STATUS in (2,3,4) then 1 else null end ) as pending,");
		lSBQuery.append(" count(case when emp.ZP_STATUS =10 then 1 else null end ) as approved");
		lSBQuery.append(" FROM rlt_zp_ddo_map rlt ");
		lSBQuery.append(" inner join ORG_DDO_MST ddo on rlt.zp_ddo_code=ddo.ddo_code");
		lSBQuery.append(" inner join mst_dcps_ddo_office off on off.ddo_code=rlt.zp_ddo_code");
		lSBQuery.append(" and upper(off.ddo_office)='YES'");
		lSBQuery.append(" left join mst_dcps_emp emp on emp.ddo_code=rlt.zp_ddo_code");
		lSBQuery.append(" inner join CMN_DISTRICT_MST dist on off.DISTRICT=dist.DISTRICT_ID");
		lSBQuery.append(" inner join ZP_REGION_NAME_MST region on dist.REGION_CODE=region.REGION_CODE");
		lSBQuery.append(" INNER join ZP_ADMIN_NAME_MST admin on ddo.DDO_TYPE=admin.ID");
		lSBQuery.append(" where rlt.STATUS <>-2");
		lSBQuery.append(" and region.REGION_CODE="+regionCode+" AND DDO.DDO_TYPE ="+adminId+" and DIST.DISTRICT_ID="+districtId+" ");
		lSBQuery.append(" group by OFF.DDO_CODE,off.off_name,REGION.REGION_NAME,DIST.DISTRICT_NAME,ADMIN.ADMIN_NAME,dist.REGION_ORDER ");
		lSBQuery.append(" order by REGION.REGION_NAME,dist.REGION_ORDER,ADMIN.ADMIN_NAME");
	
		

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is is "+lSBQuery.toString());
		/*Query.setParameter("admin", adminId);
		Query.setParameter("district", district);*/

		empList = Query.list();


		return empList;
	}

//added by Saurabh S
	
		
	private List getSchoolData(String month,String year,String regionCode,String adminId,String districtId){

		List schoolList=null;
		StringBuilder lSBQuery = new StringBuilder();
		gLogger.info("month"+month);
		gLogger.info("year is "+year);
		gLogger.info("regionCode is "+regionCode);
		gLogger.info("adminId is "+adminId);
		gLogger.info("districtId is "+districtId);
		
		

		lSBQuery.append(" SELECT OFF.DDO_CODE, off.off_name, REGION.REGION_NAME,DIST.DISTRICT_NAME,ADMIN.ADMIN_NAME, ");
		lSBQuery.append(" COUNT(CASE WHEN PAY.APPROVE_FLAG IN(0,1) THEN 1 ELSE NULL END) AS BILLS_GENERATED,");
		lSBQuery.append(" COUNT(CASE WHEN PAY.APPROVE_FLAG IN (0,1) AND PAY.STATUS = 4 THEN 1 ELSE NULL END) AS BILLS_CONSOLIDATED,");
		lSBQuery.append(" NVL(SUM(CASE WHEN PAY.APPROVE_FLAG IN (0,1) AND PAY.STATUS = 4 THEN PAY.NO_OF_EMP ELSE NULL END), 0) AS EMP_IN_CONSOLIDATED_BILL,");
		lSBQuery.append(" COUNT(CASE WHEN PAY.APPROVE_FLAG = 0 AND PAY.STATUS IN (2,3) THEN 1 ELSE NULL END) AS BILLS_FORWARDED,");
		lSBQuery.append(" NVL(SUM(CASE WHEN PAY.APPROVE_FLAG IN (1) THEN PAY.NO_OF_EMP ELSE NULL END), 0) AS EMP_IN_LOCKED_BILLS,");
		lSBQuery.append(" COUNT(CASE WHEN PAY.APPROVE_FLAG = 1 AND PAY.STATUS = 4 THEN 1 ELSE NULL END) AS BILLS_APPROVED,");
		lSBQuery.append(" NVL(SUM(CASE WHEN PAY.APPROVE_FLAG = 0 AND PAY.STATUS = 0 THEN PAY.NO_OF_EMP ELSE NULL END), 0) AS EMP_IN_GENERATED_BILL,");
		lSBQuery.append(" NVL(SUM(CASE WHEN PAY.APPROVE_FLAG = 0 AND PAY.STATUS = 2 THEN PAY.NO_OF_EMP ELSE NULL END),0)AS EMP_IN_FORWARDED_BILL");
		lSBQuery.append(" FROM RLT_ZP_DDO_MAP RLT ");
		lSBQuery.append(" INNER JOIN ORG_DDO_MST DDO ON RLT.ZP_DDO_CODE=DDO.DDO_CODE");
		lSBQuery.append(" INNER JOIN MST_DCPS_DDO_OFFICE OFF ON OFF.DDO_CODE=RLT.ZP_DDO_CODE AND UPPER(OFF.DDO_OFFICE)='YES'");
		lSBQuery.append(" LEFT JOIN PAYBILL_HEAD_MPG PAY ON DDO.LOCATION_CODE=PAY.LOC_ID AND PAY.APPROVE_FLAG IN(0,1) AND PAY.PAYBILL_MONTH="+month+" AND PAY.PAYBILL_YEAR="+year+" ");
		lSBQuery.append(" INNER JOIN CMN_DISTRICT_MST DIST ON OFF.DISTRICT=DIST.DISTRICT_ID");
		lSBQuery.append(" INNER JOIN ZP_REGION_NAME_MST REGION ON DIST.REGION_CODE=REGION.REGION_CODE");
		lSBQuery.append(" INNER JOIN ZP_ADMIN_NAME_MST ADMIN ON DDO.DDO_TYPE=ADMIN.ID");
		lSBQuery.append(" WHERE RLT.STATUS <> -2");
		lSBQuery.append(" AND REGION.REGION_CODE="+regionCode+"");
		lSBQuery.append(" AND DDO.DDO_TYPE ="+adminId+" and DIST.DISTRICT_ID="+districtId+" ");
		lSBQuery.append(" GROUP BY OFF.DDO_CODE,off.off_name,REGION.REGION_NAME,DIST.DISTRICT_NAME,ADMIN.ADMIN_NAME,DIST.REGION_ORDER");
		lSBQuery.append(" ORDER BY REGION.REGION_NAME,DIST.REGION_ORDER,ADMIN.ADMIN_NAME");
		//lSBQuery.append(" and mst.DDO_TYPE ="+Long.parseLong(adminId)+" and dist.DISTRICT_ID="+Long.parseLong(district)+" ");
		

		Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();
		SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
		gLogger.info("Query is "+lSBQuery.toString());
		/*Query.setParameter("admin", adminId);
		Query.setParameter("district", district);*/

		schoolList = Query.list();


		return schoolList;
	}



	
}
