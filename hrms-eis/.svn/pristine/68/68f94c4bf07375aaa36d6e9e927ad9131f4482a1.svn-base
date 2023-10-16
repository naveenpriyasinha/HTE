package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.util.reports.SortingHelper;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.ConvertNumbersToWord;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class payrollReportBakmdtlDAO extends DefaultReportDataFinder 
implements ReportDataFinder{

	   ResourceBundle locStrsBundle;
	   private static Logger logger = Logger.getLogger(PayrollReportsDAO.class );
	   
	  

	   private  StyleVO[] selfCloseVO=null;          
	   private ResultSet lRs1 = null; 
	   
	   final String CheckIfNull(Object lStra)
	   {
	                   String lStrb="";
	                   if( lStra != null )
	                   {
	                              lStrb = (((String)lStra).trim());
	    
	                   }
	                   return lStrb;
	    }
	  	
	   
	   
		 public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
		    {
		        String langName=report.getLangId();
		        int finalpagesize=20;
		        String locId=report.getLocId(); 
		        Connection lCon = null;
		        Statement lStmt = null;
		        
				Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
				Map serviceMap = (Map)requestKeys.get("serviceMap");						
				Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
				CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
				String locationName=locationVO.getLocName();
				long locationId=locationVO.getLocId();
		        String locationNameincaps=locationName.toUpperCase();
				
		        String upperfooter="UNDER SECRETARY TO GOVT.";
		        String lowerfooter="GANDHINAGAR"+System.getProperty("line.separator")+"Code No. PG3";
		        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		        ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
		        ArrayList DataList = new ArrayList(  );            
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
		        
		        //Added by Urvin for setting up font size
		        StyleVO[] baseFont = new StyleVO[1];
		        baseFont[0] = new StyleVO(  );
		        baseFont[0].setStyleId( IReportConstants.STYLE_FONT_SIZE );
		        baseFont[0].setStyleValue( "13" );
		        
		        
		        /*baseFont[1] = new StyleVO(  );
		        baseFont[1].setStyleId( IReportConstants.STYLE_FONT_COLOR );
		        baseFont[1].setStyleValue( IReportConstants.VALUE_FONT_COLOR_DARKGRAY );*/
		        //report.addReportStyles(baseFont);
		        ReportTemplate rt = new ReportTemplate();
		        rt.put(IReportConstants.TMPLT_COLUMN_HEADER,baseFont);
		        rt.put(IReportConstants.TMPLT_BASE_FONT,baseFont);
		        report.setReportTemplate( rt );
		        //Added by Ravish for setting up font size
		        
		        //added by Samir Joshi fro each page Header
	            ReportAttributeVO ravo1 = new ReportAttributeVO();
		  		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_LOCATION);
	    		ravo1.setAttributeType(IReportConstants.ADDL_HEADER_ON_EACH_PAGE);
	    		ravo1.setAlignment(IReportConstants.HEADER_ALIGN_CENTER);
	    		report.addReportAttributeItem(ravo1);
	    		report.setAdditionalHeader("");
		        //ended by samir joshi
		       // Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	            ServiceLocator serv1 = (ServiceLocator) requestKeys.get("serviceLocator");

		    	PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv1.getSessionFactory());
		    	boolean isBillDefined = paybillDAO.isBillsDefined(locationId);
//		    	Added by Mrugesh/Samir
	  	        
	 	         StyleVO[] styleVOPgBrk=null;
	 	         styleVOPgBrk = new StyleVO[2];
	 	         

	 	         
	 	         styleVOPgBrk[0] = new StyleVO();
	 	         styleVOPgBrk[0].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_SUBREPORT);
	 	         styleVOPgBrk[0].setStyleValue("yes");
	 	         styleVOPgBrk[0] = new StyleVO();
	 	         
	 	         
	 	         styleVOPgBrk[1] = new StyleVO();
	 	         styleVOPgBrk[1].setStyleId(IReportConstants.SHOW_REPORT_WHEN_NO_DATA);
	 	         styleVOPgBrk[1].setStyleValue("no");
	 	         
	 	         report.addReportStyles(styleVOPgBrk);
	 	         
	 	         
	 	        //Ended
		        try   
		        {
		          lCon = DBConnection.getConnection(  );
		          lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		          logger.info("getReportCode"+report.getReportCode());
	              dataStyle = new StyledData();
	              String portType="";
	              String BillNo="";	    
	              if(report.getReportCode().equals("6"))
	              {
	        			StringBuffer lsb = new StringBuffer(  );      

	        	          String month="";
	        	          String year="";
	        	          String bank="";
	        	          String Department="";
	        	          month=CheckIfNull(report.getParameterValue( "Month" ));
	        	          year=CheckIfNull(report.getParameterValue( "Year" ));
	        	          bank=CheckIfNull(report.getParameterValue( "bank" ));
	        	          Department=CheckIfNull(report.getParameterValue( "Department" ));
	        	          //added by samir joshi for bill no wise report
	        	          if(Department.equals("")||Department.equals("-1"))
	        	          	Department=	locationId+"";	
	        	          
	        	          String BillNoinner="";//GAD specific
	        	          BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
	        		        StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
	        		        int l=0;
	        		        String subheadCode="";
	        		        String classIds="";
	        		        String desgnIds="";
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
	        		        	//logger.info(BillNoinner+"*(((((6705680-9999999999999999999999999"+l);
	        		         l++;
	        		        } 
	        		  		String DeptName=locationNameincaps;
	        		        //ended by samir joshi/////
	        	          //for report footer
	        	/*          ReportAttributeVO ravo = new ReportAttributeVO();
	        	          
	        	  		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	        	  		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	        	  		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

	        	  		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

	        	          //add the attribute to the report instance
	        	  		report.addReportAttributeItem(ravo);*/
	        				ArrayList tblData = new ArrayList();

	        				ArrayList trow1 = new ArrayList();

	        				trow1.add(" ");
	        				trow1.add(" ");
	        				trow1.add(" "+System.getProperty("line.separator"));
	        				tblData.add(trow1);//added first row of the tabular data


	        				ArrayList trow2 = new ArrayList();

	        				trow2.add(" ");
	        				trow2.add(" ");
	        				trow2.add(" "+System.getProperty("line.separator"));
	        				tblData.add(trow2);//added second row of the tabular data


	        				ArrayList trow4 = new ArrayList();

	        				trow4.add(" ");
	        				trow4.add(" ");
	        				trow4.add("UNDER SECRRETARY TO GOVT. ");
	        				tblData.add(trow4);//added second row of the tabular data


	        				ArrayList trow5 = new ArrayList();

	        				trow5.add(" ");
	        				trow5.add(" ");
	        				trow5.add("GENERAL ADMIN. DEPTT.");
	        				tblData.add(trow5);//added second row of the tabular data

	        				ArrayList trow3 = new ArrayList();

	        				trow3.add(" ");
	        				trow3.add(" ");
	        				trow3.add("GANDHINAGAR");

	        				tblData.add(trow3);//added third row of the tabular data

	        				ArrayList trow6 = new ArrayList();
	        				trow6.add(" ");
	        				trow6.add(" ");
	        				trow6.add("Code No.PG3");

	        				tblData.add(trow6);//added sixth row of the tabular data

	        				TabularData tabularData = new TabularData(tblData);//initialize tabular data
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
	        				tabularData.addStyle(IReportConstants.BORDER, "No"); 

	        				report.setGrandTotalTemplate(tabularData);
	        				report.setGroupByTotalTemplate(tabularData);
	        		        //
	        	          SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	        	         // sdfObj = new SimpleDateFormat("MMM");
	        	          Calendar cal = Calendar.getInstance();	            
	        	          cal.set(Calendar.YEAR, Integer.parseInt(year));
	        	          cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
	        	          cal.set(Calendar.DAY_OF_MONTH, 1);	
	        	          java.util.Date date = cal.getTime();
	        	          String selDate  = sdfObj.format(date);
	        	          sdfObj = new SimpleDateFormat("MMM");
	        	          java.util.Date startMonthDate = cal.getTime();
	        	          String startDate  = sdfObj.format(startMonthDate);
	        	          sdfObj = new SimpleDateFormat("MMM");
	        		        String Month = sdfObj.format(startMonthDate);

	        		        report.setReportName(DeptName+System.getProperty("line.separator")+Month+". "+year+System.getProperty("line.separator")+"CROSSED CHEQUES MAY PLEASE BE ISSUED AS UNDER  "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
	        	          
	        	        boolean flag=true;
	        	        Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
	        	        	                
	        	        Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	        	        ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
	        	        
	        	        SessionFactory sessionFactory = serviceLocator.getSessionFactory();
	        	        Session session= sessionFactory.getCurrentSession();	           
	        		 		
	        		 		Session hibSession = sessionFactory.getCurrentSession();
	        		 		// comented by samir joshi for sql query in sum function
	        	          String query = " select b.hrEisBankMst.bankId, ";

	        	          query+=" concat(concat(b.hrEisBranchMst.branchName,','),b.hrEisBankMst.bankName), ";
	        	          query+="  sum(a.netTotal),sum(non.totalNonGovtDeduc) ";
	        	          query+=" from HrPayPaybill a,  ";


	        	          query+=" HrEisBankDtls b ,OrgPostDetailsRlt pt, ";
	        	          query+=" OrgUserpostRlt           USRPST, ";
	        	      	query+=" HrPayOrderHeadMpg ORDHD, ";
	        	      	query+=" HrPayOrderHeadPostMpg ORDPST,HrPayPayslipNonGovt non ";
	        	      
	        	          query+=" where ";
	        	          query+=" non.paybillID=a.id and a.hrEisEmpMst.empId = b.hrEisEmpMst.empId  ";
	        	          query += " and ORDPST.orderHeadId = ORDHD.orderHeadId  ";
	        	          query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
	        	          query += " and USRPST.orgUserMst.userId = a.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
	        	          query += " and pt.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
	        	          
	        	          if(!month.equals("")&&!month.equals("-1"))            	
	        	        		query+="and  a.month='"+month+"'";
	        	          
	        	          if(!year.equals("")&&!year.equals("-1"))            	
	        	        		query+="and a.year= '"+year+"'";
	        	          
	        	          if(!bank.equals("")&&!bank.equals("-1"))            	
	        	        		query+="and b.hrEisBankMst.bankId= '"+bank+"'";
	        	          
	        	          if(!Department.equals("")&&!Department.equals("-1"))            	
	        	        		query+="and pt.orgPostMst.postId= a.orgPostMst.postId and pt.cmnLocationMst.locationCode=" + Department;
	        	          
	        	          if(isBillDefined&&!BillNo.equals(""))
	        	          {
	        	         	 
	        	          	query+="  and  a.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "; 
	        	          	query+="   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pt.orgPostMst.postId = a.orgPostMst.postId  "; 
	        	          }
	        	          else
	        	          {	

	        		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	        	          {
	        	          	query+=" and   ORDHD.subheadId ="+subheadCode+" ";
	        		        }
	        		        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	        	          {
	        		        	query+="  and a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
	        		        }
	        		        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	        	          {
	        		        	query+="  and pt.orgDesignationMst.dsgnId in (" +desgnIds+ ")  ";
	        		        }
	        	          }
	        		        query+="   and a.approveFlag in(0,1) ";
	        	          query+=" group by b.hrEisBankMst.bankId,b.hrEisBankMst.bankName,b.hrEisBranchMst.branchId,b.hrEisBranchMst.branchName  ";

	        		 	 
	        		 		//sql query for in inner query sum not work proper in hql 
	        		 		
	        		 		/*String query="";
	        	          query = " select hreisbankd2_.BANK_ID as col_0_0_,       hreisbankm7_.BANK_NAME as col_1_0_,       sum(hrpaypaybi0_.NET_TOTAL) as col_2_0_,       sum((select sum(hrpaynongo8_.NON_GOV_DEDUC_AMOUNT)            from HR_PAY_NON_GOV_DEDUCTION hrpaynongo8_,  ";
	        	          query+="hr_eis_emp_mst           hreisempms9_,                  hr_eis_emp_mst           hreisempms10_            where hrpaynongo8_.EMP_ID = hreisempms9_.emp_id and  hrpaypaysl1_.EMP_ID = hreisempms10_.emp_id and               hreisempms9_.emp_mpg_id = hreisempms10_.emp_mpg_id and                  hrpaynongo8_.NON_GOV_DEDUC_EFFT_START_DT <= '01-Jul-2008' and                  hrpaynongo8_.NON_GOV_DEDUC_EFFT_END_DT >= '01-Jul-2008')) as col_3_0_ ";
	        	          query+="  from HR_PAY_PAYBILL             hrpaypaybi0_,       HR_PAY_PAYSLIP             hrpaypaysl1_,       HR_EIS_BANK_DTLS           hreisbankd2_,       hr_eis_bank_mst            hreisbankm7_,       org_post_details_rlt       orgpostdet3_,       org_userpost_rlt           orguserpos4_,      HR_PAY_ORDER_HEAD_MPG      hrpayorder5_,       HR_PAY_ORDER_HEAD_POST_MPG hrpayorder6_,       hr_eis_emp_mst             hreisempms11_,       org_emp_mst                orgempmst12_,       cmn_location_mst           cmnlocatio13_  ";
	        	          query+="where hreisbankd2_.BANK_ID = hreisbankm7_.BANK_ID and       hrpaypaybi0_.EMP_ID = hreisempms11_.emp_id and       hreisempms11_.emp_mpg_id = orgempmst12_.emp_id and       orgpostdet3_.loc_id = cmnlocatio13_.loc_id and       hrpaypaybi0_.EMP_ID = hreisbankd2_.BANK_EMP_ID and       hrpayorder6_.ORDER_HEAD_ID = hrpayorder5_.ORDER_HEAD_ID and       orguserpos4_.post_id = hrpayorder6_.POST_ID and       orguserpos4_.user_id = orgempmst12_.user_id and       orgpostdet3_.post_id = orguserpos4_.post_id and       hrpaypaybi0_.ID = hrpaypaysl1_.PAYBILL_ID ";

	        	          if(!month.equals("")&&!month.equals("-1"))            	
	        	        		query+="and  hrpaypaybi0_.PAYBILL_MONTH='"+month+"'";
	        	          
	        	          if(!year.equals("")&&!year.equals("-1"))            	
	        	        		query+="and hrpaypaybi0_.PAYBILL_YEAR = '"+year+"'";
	        	          
	        	          if(!bank.equals("")&&!bank.equals("-1"))            	
	        	        		query+="and hreisbankd2_.BANK_ID= '"+bank+"'";
	        	          
	        	          if(!Department.equals("")&&!Department.equals("-1"))            	
	        	        		query+="and orgpostdet3_.post_id= hrpaypaybi0_.post_id and cmnlocatio13_.LOCATION_CODE=" + Department + "  ";
	        	          
	        	          if(isBillDefined&&!BillNo.equals(""))
	        	          {
	        	         	 
	        	          	query+="  and  hrpaypaybi0_.post_id in (select p.post_id from hr_pay_post_psr_mpg p where p.bill_no in (select bill.bill_subhead_id from  hr_pay_bill_subhead_mpg bill where bill.bill_no = "+BillNo+" ) )  and "; 
	        	          	query+="   hrpayorder5_.subhead_id in (select bill.subhead_code from hr_pay_bill_subhead_mpg bill where bill.bill_no ="+BillNo+" )  and orgpostdet3_.post_id = hrpaypaybi0_.post_id  "; 
	        	          }
	        	          else
	        	          {
	        		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	        	          {
	        	          	query+="  and  hrpayorder5_.SUBHEAD_ID ="+subheadCode+" ";
	        		        }
	        		        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	        	          {
	        		        	query+="  and orgempmst12_.grade_id  in ("+classIds+")";
	        		        }
	        		        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	        	          {
	        		        	query+="  and orgpostdet3_.dsgn_id  in (" +desgnIds+ ")  ";
	        		        }
	        	          }
	        		        query+="   and hrpaypaybi0_.approve_flag in (0,1) ";
	        	          query+="  group by hreisbankd2_.BANK_ID, hreisbankm7_.BANK_NAME ";	    
	        	          */       
	        	          logger.info("***Query for bank details**" +query);
	        	          
	        	          
	        		 		Query sqlQuery = hibSession.createQuery(query);	      	
	        	          ArrayList dataList=new ArrayList();
	        	          List RowList=sqlQuery.list();
	        	          logger.info("*************************"+RowList.size());
	        	          int cnt=1;
	        	          long empId=0;
	        	          
	        	          Iterator itr = RowList.iterator(); 
	        	          double AmountTotal=0;
	        	          while (itr.hasNext())
	        	          {
	        		            Object[] rowList = (Object[]) itr.next();
	        		            long bankId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
	        		            String bankName = (String)(rowList[1]!=null?rowList[1]:"");
	        		            double Amount = Double.parseDouble((rowList[2]!=null?rowList[2].toString():0).toString());
	        		            double nongovdeduct = Double.parseDouble((rowList[3]!=null?rowList[3].toString():0).toString());
	        		            Amount=Amount-nongovdeduct;
	        		            AmountTotal+=Amount;
	        		            ArrayList row = new ArrayList();
	        		            row.add(cnt);
	        		            row.add(bankName);
	        		            row.add(Math.round(Amount));
	        		            DataList.add(row);
	        		            cnt++; 
	        	          }
	        	          StyleVO[] centerboldStyleVO1 = new StyleVO[2];
	        		        centerboldStyleVO1[0] = new StyleVO();
	        		        centerboldStyleVO1[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
	        		        centerboldStyleVO1[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	        		        centerboldStyleVO1[1] = new StyleVO();
	        		        centerboldStyleVO1[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
	        		        centerboldStyleVO1[1].setStyleValue("Center"); 
	        	          ArrayList rowdata = new ArrayList();
	        	          StyledData dataStyle1 = new StyledData();
	        	          dataStyle1.setStyles(centerboldStyleVO1);
	        	          //dataStyle1.set
	        	          dataStyle1.setData("*****Non government Deduction Cheques*****");
	        	          dataStyle1.setColspan(3);
	        	          rowdata.add(dataStyle1);
	        	          rowdata.add("");
	        	          rowdata.add("");
	        	          rowdata.add("");
	        	          DataList.add(rowdata);
	        	          
	        	          query = " select sum(n.lic) as lic,sum(n.society_old) as old ,sum(n.society_new) as  new_soc , ";
	        	          query+=" sum(n.karamchari_bank) as karam,sum(n.nagrik_bank) as nag, ";
	        	          query+=" sum(n.post_office_recurring_deposit) as por,sum(n.chief_minister_relief_fund) as cmrf , ";
	        	          query+=" sum(n.sal_saving_fund) as ssf ";
	        	          
	        	          query+=" from hr_pay_payslip_non_govt n ";
	        	          
	        	          query+=" where n.paybill_id in "; 
	        	          query+="  (select p.id from hr_pay_paybill p where ";
	        	          
	        	          if(!month.equals("")&&!month.equals("-1"))            	
	        	        		query+=" p.paybill_month='"+month+"' and ";
	        	          
	        	          if(!year.equals("")&&!year.equals("-1"))            	
	        	        		query+=" p.paybill_year= '"+year+"' and ";
	        	          
	        	          if(isBillDefined&&!BillNo.equals(""))
	        	          {
	        	          	query+="  p.post_id in (select p.post_id from hr_pay_post_psr_mpg p where p.bill_no in  ";
	        	          	query+="  (select bill.bill_subhead_id from hr_pay_bill_subhead_mpg bill  where ";   
	        	          	query+="  bill.bill_no = "+BillNo+")) and "; 
	        	          }
	        	          
	        	          query+="  p.approve_flag in (0, 1)) ";      
	        	          
	        	                                                
	        	           
	        	          
	        	          /*
	        	          
	        	          
	        	          query = " select  ";
	        	          query+=" b.hrPayDeducTypeMst.deducCode,sum(non.totalNonGovtDeduc) ";
	        	          query+=" from HrPayPaybill a, HrPayPayslipNonGovt non, ";
	        	          query+=" HrPayNonGovDeduction b,OrgPostDetailsRlt pt, ";
	        	          query+=" OrgUserpostRlt           USRPST, ";
	        	      	query+=" HrPayOrderHeadMpg ORDHD, ";
	        	      	query+=" HrPayOrderHeadPostMpg ORDPST ";
	        	          query+=" where ";
	        	          query+=" a.hrEisEmpMst.empId = b.hrEisEmpMst.empId and non.paybillID=a.id    ";
	        	          query+=" and  b.nonGovDeducEfftStartDt <= a.createdDate "+
	        				" and b.nonGovDeducEfftEndDt >=a.createdDate  ";
	        	          query += " and ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where a.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
	        	          query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
	        	          query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
	        	          query += " and USRPST.orgUserMst.userId = a.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
	        	          query += " and pt.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
	        	          
	        	          if(!month.equals("")&&!month.equals("-1"))            	
	        	        		query+=" and  a.month='"+month+"'";
	        	          
	        	          if(!year.equals("")&&!year.equals("-1"))            	
	        	        		query+=" and a.year= '"+year+"'";
	        	          
	        	          if(!bank.equals("")&&!bank.equals("-1"))            	
	        	          	query+=" and b.hrPayDeducTypeMst.deducCode= '"+bank+"'";
	        	        
	        	          if(!Department.equals("")&&!Department.equals("-1"))            	
	        	        		query+=" and pt.orgPostMst.postId= a.orgPostMst.postId and pt.cmnLocationMst.locationCode=" + Department +"  ";
	        	          if(isBillDefined&&!BillNo.equals(""))
	        	          {
	        	         	 
	        	          	query+="  and  a.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "; 
	        	          	query+="   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pt.orgPostMst.postId = a.orgPostMst.postId  "; 
	        	          }
	        	          else
	        	          {	

	        		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	        	          {
	        	          	query+=" and   ORDHD.subheadId ="+subheadCode+" ";
	        		        }
	        		        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	        	          {
	        		        	query+="  and a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
	        		        }
	        		        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	        	          {
	        		        	query+="  and pt.orgDesignationMst.dsgnId in (" +desgnIds+ ")  ";
	        		        }
	        	          }
	        		        query+="   and a.approveFlag in(0,1) ";
	        	          query+=" group by b.hrPayDeducTypeMst.deducCode  ";*/
	        	          
	        	         
	        	          logger.info("***Query for bank details**" +query);
	        	          
	        	          
	        		 		sqlQuery = hibSession.createSQLQuery(query);	      	
	        	          
	        	          RowList=sqlQuery.list();
	        	          logger.info("*************************"+RowList.size());
	        	          
	        	          
	        	          ArrayList row = new ArrayList();
	        	          itr = RowList.iterator();  
	        	          while (itr.hasNext())
	        	          {
	        		            Object[] rowList = (Object[]) itr.next();
	        		            //long bankId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
	        	            
	        		            long lic = (Long.parseLong((rowList[0]!=null?rowList[0]:0).toString()));
	        		            long old = (Long.parseLong((rowList[1]!=null?rowList[1]:0).toString()));
	        		            long new_soc = (Long.parseLong((rowList[2]!=null?rowList[2]:0).toString()));
	        		            long karam = (Long.parseLong((rowList[3]!=null?rowList[3]:0).toString()));
	        		            long nag = (Long.parseLong((rowList[4]!=null?rowList[4]:0).toString()));
	        		            long por = (Long.parseLong((rowList[5]!=null?rowList[5]:0).toString()));
	        		            long cmrf = (Long.parseLong((rowList[6]!=null?rowList[6]:0).toString()));
	        		            long ssf = (Long.parseLong((rowList[7]!=null?rowList[7]:0).toString()));
	        		            
	        		            if(new_soc!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("The Gujrat Sachivalaya Employee CO-Op. New Credit Society Ltd. ");	
	        		            	row.add(new_soc);	
	        		            	AmountTotal+=new_soc;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(old!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("The Gujrat Sachivalaya Employee CO-Op. Credit Society Ltd. ");	
	        		            	row.add(old);	
	        		            	AmountTotal+=old;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(lic!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("L.I.C. of India, Gandhinagar.");	
	        		            	row.add(lic);	
	        		            	AmountTotal+=lic;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(karam!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("Karamchari Bank. ");	
	        		            	row.add(karam);	
	        		            	AmountTotal+=karam;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(nag!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("Nagrik Bank. ");	
	        		            	row.add(nag);	
	        		            	AmountTotal+=nag;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(por!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("Post Office Recurring Deposit. ");	
	        		            	row.add(por);	
	        		            	AmountTotal+=por;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(cmrf!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("Chief Minister Relief Fund. ");	
	        		            	row.add(cmrf);	
	        		            	AmountTotal+=cmrf;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            if(ssf!=0)
	        		            {
	        		            	row = new ArrayList();
	        		            	row.add(cnt++);	
	        		            	row.add("Salary Saving Fund. ");	
	        		            	row.add(ssf);	
	        		            	AmountTotal+=ssf;
	        		            	DataList.add(row);
	        		            	
	        		            }
	        		            



	        	          }
	        	          
	        	          row = new ArrayList();
	        	          row.add("");
	        	            dataStyle1 = new StyledData();
	        	            dataStyle1.setStyles(boldStyleVO);
	        	            dataStyle1.setData("GRAND TOTAL");                  
	        	            row.add(dataStyle1);
	        	            String AmountTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(AmountTotal))+" only.";
	        	            dataStyle1 = new StyledData();
	        	            dataStyle1.setStyles(boldStyleVO);
	        	            dataStyle1.setData(Math.round(AmountTotal)/*+System.getProperty("line.separator")+AmountTotalInWords*/);                  
	        	            row.add(dataStyle1);	
	        	          DataList.add(row);
	        	          ArrayList row1 = new ArrayList();
	        	          

	        	          StyleVO[] centerboldStyleVO = new StyleVO[2];
	        		        centerboldStyleVO[0] = new StyleVO();
	        		        centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
	        		        centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	        		        centerboldStyleVO[1] = new StyleVO();
	        		        centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
	        		        centerboldStyleVO[1].setStyleValue("Left"); 
	        		        StyledData dataStyle2 = new StyledData();
	        		        dataStyle2.setStyles(centerboldStyleVO);
	        	          dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(AmountTotal))+" only.");
	        	          dataStyle2.setColspan(3);
	        	          row1.add(dataStyle2);
	        	          
	        	          for(int c=0;c<3;c++)
	        	          	row1.add("");
	        		        
	        	          
	        	          DataList.add(row1);
	        	          logger.info("**********************"+DataList.size());
	        	          return DataList;
	        	  

	              }
	              if(report.getReportCode().equals("7"))
	           	  {

	                  // Added by Akshay
	    	  			String fname = CheckIfNull(report.getParameterValue("FName"));
	    	            String lname = CheckIfNull(report.getParameterValue("LName"));
	                // Ended by Akshay

	                
	                // hardcoded
	        	    long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));
	    	  			           
	    	  			StringBuffer lsb = new StringBuffer(  );      

	    	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
	    	            String Department="";
	    	            String Grade="";
	    	            String Scale="";
	    	            String Designation="";
	    	            String month="";
	    	            String year="";
	    	            String GroupBy="";
	    	            String Branch="";

	    	            GroupBy=CheckIfNull(report.getParameterValue( "Group By" ));
	    	            Department=CheckIfNull(report.getParameterValue( "Department" ));
	                  //added by samir joshi for bill no wise report
	    	            if(Department.equals("")||Department.equals("-1"))
	    	            	Department=	locationId+"";	
	    	            
	    	            String BillNoinner="";//GAD specific
	    	            BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
	    	 	        StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
	    		        int l=0;
	    		        String subheadCode="";
	    		        String classIds="";
	    		        String desgnIds="";
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
	    	    		String DeptName=locationNameincaps;
	    	 	        //ended by samir joshi/////
	    	            //for report footer
	    /*	            ReportAttributeVO ravo = new ReportAttributeVO();
	    	            
	    	    		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	    	    		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	    	    		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

	    	    		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

	                  //add the attribute to the report instance
	    	    		report.addReportAttributeItem(ravo);*/
	    	    		
	    	    		
	    	    		String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
	    	        	//logger.info(" nof rec out====>"+noOfRec);
	    	            if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
	    	            {
	    	                //logger.info("No Of Rec is********====>"+noOfRec);
	    	            	finalpagesize=Integer.parseInt(noOfRec);
	    	            }
	    				ArrayList tblData = new ArrayList();

	    				ArrayList trow1 = new ArrayList();

	    				trow1.add(" ");
	    				trow1.add(" ");
	    				trow1.add(" "+System.getProperty("line.separator"));
	    				tblData.add(trow1);//added first row of the tabular data


	    				ArrayList trow2 = new ArrayList();

	    				trow2.add(" ");
	    				trow2.add(" ");
	    				trow2.add(" "+System.getProperty("line.separator"));
	    				tblData.add(trow2);//added second row of the tabular data


	    				ArrayList trow4 = new ArrayList();

	    				trow4.add(" ");
	    				trow4.add(" ");
	    				trow4.add("UNDER SECRRETARY TO GOVT. ");
	    				tblData.add(trow4);//added second row of the tabular data


	    				ArrayList trow5 = new ArrayList();

	    				trow5.add(" ");
	    				trow5.add(" ");
	    				trow5.add("GENERAL ADMIN. DEPTT.");
	    				tblData.add(trow5);//added second row of the tabular data

	    				ArrayList trow3 = new ArrayList();

	    				trow3.add(" ");
	    				trow3.add(" ");
	    				trow3.add("GANDHINAGAR");

	    				tblData.add(trow3);//added third row of the tabular data

	    				ArrayList trow6 = new ArrayList();
	    				trow6.add(" ");
	    				trow6.add(" ");
	    				trow6.add("Code No.PG3");

	    				tblData.add(trow6);//added sixth row of the tabular data

	    				TabularData tabularData = new TabularData(tblData);//initialize tabular data
	    				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
	    				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
	    				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
	    				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
	    				tabularData.addStyle(IReportConstants.BORDER, "No"); 

	    				report.setGrandTotalTemplate(tabularData);
	    				report.setGroupByTotalTemplate(tabularData);
	    		        //
	    	            Grade=CheckIfNull(report.getParameterValue( "Grade" ));
	    	            Scale=CheckIfNull(report.getParameterValue( "Scale" ));
	    	            Designation=CheckIfNull(report.getParameterValue( "Designation" ));
	    	            month=CheckIfNull(report.getParameterValue( "Month" ));
	    	            year=CheckIfNull(report.getParameterValue( "Year" ));
	    	            String bank=CheckIfNull(report.getParameterValue( "bank" ));
	    	            Branch=CheckIfNull(report.getParameterValue( "Branch" ));
	    		        ReportColumnVO[] rptCol = report.getReportColumns();    
	    		        if(!bank.equals("")&&!bank.equals("-1"))   
	    		        {
	    		        	rptCol[rptCol.length-1].setHidden("y");
	    		        	
	    		        }
	                    report.setReportColumns(rptCol);
	                    report.initializeDynamicTreeModel();
	                    report.initializeTreeModel();
	    	            String branchId="";
	    	            String micrCode="";
	    	            String branchName="";
	    	            st1=new StringTokenizer(Branch,"~");
	    		         l=0;

	    		        while(st1.hasMoreTokens())
	    		        {
	    		        	if(l==0)
	    		        		branchId=st1.nextToken();
	    		        	else if(l==1)
	    		        		micrCode=st1.nextToken();
	    		        	else if(l==2)
	    		        		branchName=st1.nextToken();

	    		         l++;
	    		        } 
	    	            boolean flag=true;
	                Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
	                	                
	                Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	                ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
	                
	                SessionFactory sessionFactory = serviceLocator.getSessionFactory();
	                Session session= sessionFactory.getCurrentSession();	           
	    	  	 		
	    	            Calendar cal = Calendar.getInstance();
	    	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	    	            
	    	            cal.set(Calendar.YEAR, Integer.parseInt(year));
	    	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
	    	            cal.set(Calendar.DAY_OF_MONTH, 1);
	    	            
	    	            java.util.Date date = cal.getTime();
	    	            String selDate  = sdfObj.format(date);
	    	            
	    	            cal.set(Calendar.YEAR, Integer.parseInt(year));
	    	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
	    	            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
	    	            
	    	            java.util.Date endDate = cal.getTime();
	    	            String endMonthDate  = sdfObj.format(endDate);	            
	    	            
	    	            
	    			    sdfObj = new SimpleDateFormat("MMM");
	    		        String Month = sdfObj.format(date);
	    		        String bSpace="                ";
	    		        String bSpace1="                                                                                                               ";
	    		        String bSpace2="                                                                 ";
	    		        String bSpace3="                             ";
	    		       
	    		        if(!micrCode.equals("")&&!micrCode.equals("-1"))
	    		        {
	    		        	  report.setReportName(DeptName+System.getProperty("line.separator")+System.getProperty("line.separator")+"Schedule Payment Statement for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
	    		    		  ArrayList d = new ArrayList();
	    		    		  ArrayList r = new ArrayList();
	    		    		  
	    		    		  r= new ArrayList();
	    		    		  r.add("Bank :" +branchName);
	    		    		  r.add("Bank code:"+micrCode);
	    		    		  d.add(r);
	    		    		  r= new ArrayList();
	    		    		  r.add("Treasury/P.A.D.Cheque No.");
	    		    		  r.add("Date: ");
	    		    		  d.add(r);
	    		    		  TabularData td  = new TabularData(d);
	    		    		  td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
	    		    		  td.addStyle(IReportConstants.BORDER, "No"); 
	    		    		  report.setAdditionalHeader(td);
	    		        }
	    		        else   if(!bank.equals("")&&!bank.equals("-1"))  
	    		        {
	    		        	  report.setReportName(DeptName+System.getProperty("line.separator")+System.getProperty("line.separator")+"Schedule Payment Statement for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
	    		    		  ArrayList d = new ArrayList();
	    		    		  ArrayList r = new ArrayList();
	    		    		  
	    		    		  r= new ArrayList();
	    		    		  r.add("Bank :" +branchName);
	    		    		  r.add("");
	    		    		  d.add(r);
	    		    		  r= new ArrayList();
	    		    		  r.add("Treasury/P.A.D.Cheque No.");
	    		    		  r.add("Date: ");
	    		    		  d.add(r);
	    		    		  TabularData td  = new TabularData(d);
	    		    		  td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
	    		    		  td.addStyle(IReportConstants.BORDER, "No"); 
	    		    		  report.setAdditionalHeader(td);
	    		        }
	    		        else
	    	            		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule Payment Statement for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
	    	  	 		Session hibSession = sessionFactory.getCurrentSession();
	                  String query = " select dtl.hrEisEmpMst.empId ,";
	    	            //query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName ,";
	    	            //query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";
	                  query+= " pst.orgDesignationMst.dsgnShrtName , ";
	                  query+= " pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, " ;
	                  query+=" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
	    	            query+=" dtl.otherCurrentBasic ,";
	    	            //query+="dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt || '-' || dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt ,";
	    	            query+="  scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt || '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt, ";
	    	            query+=" sum(pay.totalDed) ,";
	    	            query+=" sum(pay.netTotal) , (select sum(nongov.totalNonGovtDeduc)  from HrPayPayslipNonGovt nongov where ";
	    	            query+="   nongov.paybillID.id = pay.id ) , ";
	    	            //query+="  nongov.hrEisEmpMst.orgEmpMst.empId = payslip.hrEisEmpMst.orgEmpMst.empId and  nongov.nonGovDeducEfftStartDt <= '"+selDate+"' and ";
	    	            //query+="   nongov.nonGovDeducEfftEndDt >='"+selDate+"') , ";
	    	          
	    	            query+=" sum(pay.grossAmt) ,b.hrEisBankMst.bankName,b.bankAcctNo ";
	    	            query+="from HrPayPaybill pay, ";
	    	            //query+=" HrEisOtherDtls dtl,HrEisBankDtls b";
	    	            query+=" HrEisBankDtls b,";
	    	            query+=" HrEisOtherDtls as dtl ";
	    	            query+= " left outer join dtl.HrEisSgdMpg as sgd ";
	    	            query += " left outer join sgd.HrEisGdMpg as gd ";
	    	            query += "left outer join sgd.HrEisScaleMst scale, ";
	    	            query+=" OrgUserpostRlt           USRPST, ";
	              	query+=" HrPayOrderHeadMpg ORDHD, ";
	              	query+=" HrPayOrderHeadPostMpg ORDPST, ";
	              	query+=" OrgPostDetailsRlt pst ";



	    	            
	    	            query+=" where  ";
	    	            
	    	            query += "  ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
	    	            query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
	    	            query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
	    	            query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
	    	            query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
	    	            
	    	            query+=" and pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId and pay.hrEisEmpMst.empId = b.hrEisEmpMst.empId  ";
	                  query+=" and ( USRPST.endDate is null or ";
	                  query+="  (USRPST.endDate>='"+selDate+"'  ))";
	                  query+="  and pay.approveFlag in (0,1) ";
	                  //query+="  and pay.id=payslip.hrPayPaybill.id ";
	    	                        
	    	            if(!empid.equals("")&&!empid.equals("-1"))            	
	    	        		query+="and dtl.hrEisEmpMst.empId = '"+empid+"'";
	    		            
	                  // Added by Akshay 
	    	            if(!lname.equals("") || !lname.equals(" "))
	    	            {
	    	            	query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
	    	            }
	    	            
	    	            if(!fname.equals("") || !fname.equals(" "))
	    	            {
	    	            	query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
	    	            }
	    	            //Ended by Akshay

	    		            
	    		            if(!Grade.equals("")&&!Grade.equals("-1"))            	
	    			            query+="and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  = '"+Grade+"'"; 		            		
	    		            	//query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId  = '"+Grade+"'";
	    		            
	    		            if(!Scale.equals("")&&!Scale.equals("-1"))            	
	    	        		query+="and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"+Scale+"'";
	    		            
	    		            if(!Designation.equals("")&&!Designation.equals("-1"))            	
	    		        		query+="and pst.orgDesignationMst.dsgnId = '"+Designation+"'";
	    		            	//query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'";
	    		            
	    		            if(!month.equals("")&&!month.equals("-1"))            	
	    		          		query+="and pay.month='"+month+"'";
	    		            
	    		            if(!year.equals("")&&!year.equals("-1"))            	
	    		          		query+="and pay.year= '"+year+"'";
	    		            
	    		            if(!bank.equals("")&&!bank.equals("-1"))            	
	    		          		query+="and b.hrEisBankMst.bankId= '"+bank+"'";
	    		            
	    		            if(!branchId.equals("")&&!branchId.equals("-1"))            	
	    		          		query+="and b.hrEisBranchMst.branchId= '"+branchId+"'";
	    		            
	    		            if(!Department.equals("")&&!Department.equals("-1"))
	    		            	query+=" and pst.cmnLocationMst.locId="+Department+"   ";
	    		            if(isBillDefined&&!BillNo.equals(""))
	    	                {
	    	               	 
	    	                	query+="  and  pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "; 
	    	                	query+="   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pst.orgPostMst.postId = pay.orgPostMst.postId  "; 
	    	                }
	    	                else
	    	                {
	    			        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	    		            {
	    		            	query+="  and  ORDHD.subheadId ="+subheadCode+" ";
	    			        }
	    			        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	    		            {
	    			        	query+="  and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
	    			        }
	    			        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	    		            {
	    			        	query+="  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ";
	    			        }
	    			        if(portType != null && !portType.equals("") && !portType.equals("-1"))
	    		            {
	    		            	query+="  and pst.orgPostMst.postTypeLookupId in (" +portType+ ") ";
	    			        }
	    	                }
	    		            query+=" group by ";
	    		            query+=" dtl.hrEisEmpMst.empId,pay.hrEisEmpMst.empId, ";
	    		            //query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,  ";
	    		            //query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName, ";
	    		            //query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";
	    		            
	    		            query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, ";
	    		            query+=" pst.orgDesignationMst.dsgnShrtName, ";
	    		            query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName, ";
	    		            
	    		            query+=" dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
	    		            query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
	    		            query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
	    		            //query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt,  ";
	    		            //query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt,  ";
	    		            //query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,  ";
	    		            
	    		            query+=" scale.scaleStartAmt,scale.scaleHigherIncrAmt,scale.scaleHigherEndAmt,  ";
	    		            query+=" scale.scaleIncrAmt,  ";
	    		            query+=" scale.scaleEndAmt,  ";
	    		            
	    		            query+=" b.hrEisBankMst.bankName, ";
	    		            query+=" b.bankAcctNo,dtl.otherCurrentBasic,pay.id ";
	    		            
	    	            query+=" order by b.bankAcctNo,pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
	    	            
	    	           
	    	            logger.info("***Query for Bank details**" +query);
	    	            
	    	            
	    	  	 		Query sqlQuery = hibSession.createQuery(query);	      	
	                  /*query = " select dtl.hrEisEmpMst.empId ,";
	    	            query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName ,";
	    	            query+="dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";
	    	            query+="dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
	    	            query+=" dtl.otherCurrentBasic ,";
	    	            query+="dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt || '-' || dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt|| '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherEndAmt,";
	    	            query+=" sum(pay.totalDed) ,";

	    	            query+=" sum(pay.netTotal),  ";
	    	            query+=" sum(pay.grossAmt) ,b.hrPayDeducTypeMst.deducCode,b.nonGovDeducAmount,b.nonGovDeducAccNo ";
	    	            query+="from HrPayPaybill pay, ";
	    	            query+=" HrEisOtherDtls dtl,HrPayNonGovDeduction b";
	    	            
	              	query+=" ,OrgPostDetailsRlt pst, ";
	    	            query+=" OrgUserpostRlt           USRPST, ";
	              	query+=" HrPayOrderHeadMpg ORDHD, ";
	              	query+=" HrPayOrderHeadPostMpg ORDPST ";

	    	            query+=" where  ";
	    	            
	    	            query+=" pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId and pay.hrEisEmpMst.orgEmpMst.empId = b.hrEisEmpMst.orgEmpMst.empId  ";
	    	            query+=" and  b.nonGovDeducEfftStartDt <= pay.createdDate "+
	          				" and b.nonGovDeducEfftEndDt >=pay.createdDate  ";
	    	            
	    	            query+=" and pst.orgPostMst.postId=pay.orgPostMst.postId  ";
	    	            query += " and  ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
	    	            query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
	    	            query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
	    	            query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
	    	            query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
	    	            
	    	            query+=" and pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId and pay.hrEisEmpMst.empId = b.hrEisEmpMst.empId  ";
	                  query+=" and ( USRPST.endDate is null or ";
	                  query+="  (USRPST.endDate>='"+selDate+"'  ))";
	                  query+="  and pay.approveFlag in (0,1) ";

	    	            if(!empid.equals("")&&!empid.equals("-1"))            	
	    	        		query+="and dtl.hrEisEmpMst.empId = '"+empid+"'";
	    		            
	    		            
	    		            if(!Grade.equals("")&&!Grade.equals("-1"))            	
	    	        		query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId  = '"+Grade+"'";

	    		            
	    		            if(!Scale.equals("")&&!Scale.equals("-1"))            	
	    	        		query+="and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"+Scale+"'";
	    		            
	    		            if(!Designation.equals("")&&!Designation.equals("-1"))            	
	    	        		query+="and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'";

	    		            
	    		            if(!month.equals("")&&!month.equals("-1"))            	
	    		          		query+="and pay.month='"+month+"'";
	    		            
	    		            if(!year.equals("")&&!year.equals("-1"))            	
	    		          		query+="and pay.year= '"+year+"'";
	    		            
	    		            if(!bank.equals("")&&!bank.equals("-1"))            	
	    		          		query+="and b.hrPayDeducTypeMst.deducCode= '"+bank+"'";
	    		            
	    		            if(!Department.equals("")&&!Department.equals("-1"))
	    		            	query+=" and pst.cmnLocationMst.locId="+Department+" ";
	    			     
	    		            if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	    		            {
	    		            	query+="  and  ORDHD.subheadId ="+subheadCode+" ";
	    			        }
	    			        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	    		            {
	    			        	query+="  and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")";
	    			        }
	    			        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	    		            {
	    			        	query+="  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ";
	    			        }

	    		            query+=" group by ";
	    		            query+=" dtl.hrEisEmpMst.empId,pay.hrEisEmpMst.empId, ";
	    		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,  ";
	    		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName, ";
	    		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";





	    		            query+=" dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
	    		            query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
	    		            query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
	    		            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt,dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherIncrAmt,dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherEndAmt,   ";
	    		            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt,  ";
	    		            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,  ";





	    		            query+=" b.hrPayDeducTypeMst.deducCode,pay.createdDate,dtl.otherCurrentBasic,b.nonGovDeducAmount,b.nonGovDeducAccNo ";
	    		            
	    		            
	    		            query+=" order by dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
	    	            
	    	           
	    	            logger.info("***Query for non Deduction details**" +query);
	    	            
	    	            */
	    	  	 		
	    	  	 		Query SqlQuery = hibSession.createQuery(query);	      	
	    	            ArrayList dataList=new ArrayList();
	    	            List RowList=sqlQuery.list();
	    	            logger.info("*************************"+RowList.size());
	    	            int cnt=1;
	    	            long empId=0;
	    	            double AmountTotal=0;
	    	            Iterator itr = RowList.iterator();  
	                  while (itr.hasNext())
	                  {
	                  Object[] rowList = (Object[]) itr.next();
	    	            long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
	    	            String designation = (String)(rowList[1]!=null?rowList[1]:"");
	    	            long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"0").toString());
	    	            String Class = (String)(rowList[3]!=null?rowList[3]:"");
	    	            String Name = (String)(rowList[4]!=null?rowList[4]:"");
	    	            long basic = Long.parseLong((rowList[5]!=null?rowList[5]:"").toString());		            
	    	            String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
	    	            double totalded = Double.parseDouble((rowList[7]!=null?rowList[7]:"0").toString());		            
	    	            double nettotal = Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());	
	    	            double nongovtotal = Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());	
	    	            double grossAmt = Double.parseDouble((rowList[10]!=null?rowList[10]:"0").toString());		            
	    	            String bankName = (rowList[11]!=null?rowList[11]:"").toString();		            
	    	            long acct = Long.parseLong((rowList[12]!=null?rowList[12]:"0").toString());
	    	            nettotal=nettotal-nongovtotal;
	    	            AmountTotal+=nettotal;
	    	            ArrayList row = new ArrayList();
	      			/*if( cnt%20==0)
	      			{
	      				row.add(new PageBreak());
	      				row.add("Data");
	      			}*/
	      			//else
	      			{
	      				
	      		
	    	            row.add(cnt);
	    	            row.add(Name);
	    	            row.add(designation);
	    	            row.add(""+acct+"");
	    	            row.add(Math.round(nettotal));
	    	            row.add(bankName);
	      			}
	    	            cnt++; 
	    	            DataList.add(row);
	    	            
	      	            if( cnt%finalpagesize==0)
	        			{
	      	            	row=new ArrayList();
	      	            	row.add(new PageBreak());
	          				row.add("Data");
	        	            DataList.add(row); 
	        			}

	    	            
	                  }
	    	            ArrayList rowdata = new ArrayList();                
	    	            StyledData dataStyle1 = new StyledData(); 
	    	            /*rowdata.add("");
	                  dataStyle1.setData("*****Non government Deduction *****");                  
	                  rowdata.add(dataStyle1);
	                  rowdata.add("");
	                  rowdata.add("");
	                  rowdata.add("");
	                  rowdata.add("");
	    	            DataList.add(rowdata);
	    	            RowList=SqlQuery.list();
	    	            logger.info("*************************"+RowList.size());
	    	            cnt=1;
	    	            empId=0;
	    	            */
	    	            /*itr = RowList.iterator();  
	                  while (itr.hasNext())
	                  {
	    		            Object[] rowList = (Object[]) itr.next();
	    		            long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"0").toString());
	    		            String designation = (String)(rowList[1]!=null?rowList[1]:"");
	    		            long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"0").toString());
	    		            String Class = (String)(rowList[3]!=null?rowList[3]:"");
	    		            String Name = (String)(rowList[4]!=null?rowList[4]:"");
	    		            long basic = Long.parseLong((rowList[5]!=null?rowList[5]:"").toString());		            
	    		            String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
	    		            double totalded = Double.parseDouble((rowList[7]!=null?rowList[7]:"0").toString());		            
	    		            double nettotal = Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());		            
	    		            double grossAmt = Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());		            
	    		            String deducCode = ((rowList[10]!=null?rowList[10]:"").toString());		            
	    		            double DeducAmount = Double.parseDouble((rowList[11]!=null?rowList[11]:"0").toString());	
	    		            String acct = ((rowList[12]!=null?rowList[12]:"").toString());		            
	    		            AmountTotal+=DeducAmount;
	    		            ArrayList row = new ArrayList();
	    		            row.add(cnt);
	    		            row.add(Name);
	    		            row.add(designation);
	    		            row.add(acct);
	    		            row.add(Math.round(DeducAmount));
	    		            if(constantsBundle.getString("karmcharyBank").equals(deducCode))
	    		            	row.add("Karmchary Bank");	
	    		            else if(constantsBundle.getString("societyOld").equals(deducCode))
	    		            	row.add("Society Old");
	    		            else if(constantsBundle.getString("society").equals(deducCode))	
	    		            	row.add("Society New");
	    		            else if(constantsBundle.getString("nagrikBank").equals(deducCode))
	    		            	row.add("Nagrik Bank");
	    		            else if(constantsBundle.getString("LIC").equals(deducCode))
	    		            	row.add("LIC"); 
	    		            else			
	    		            	row.add("");
	    		            //row.add(bankName);
	    		            DataList.add(row);
	    		            cnt++; 
	                  }
	            */
	                  ArrayList row = new ArrayList();
	    	            row.add("");
	    	            row.add("");
	    	            row.add("");
	    	             dataStyle1 = new StyledData();
	    	              dataStyle1.setStyles(boldStyleVO);
	    	              dataStyle1.setData("TOTAL");                  
	    	              row.add(dataStyle1);
	    	              String AmountTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(AmountTotal))+" only.";
	    	              dataStyle1 = new StyledData();
	    	              dataStyle1.setStyles(boldStyleVO);
	    	              dataStyle1.setData(AmountTotal/*+System.getProperty("line.separator")+AmountTotalInWords*/);                  
	    	              row.add(dataStyle1);
	    	              row.add("");
	    	            DataList.add(row);  
	    	            ArrayList row1 = new ArrayList();
	    	            

	    	            StyleVO[] centerboldStyleVO = new StyleVO[2];
	      	        centerboldStyleVO[0] = new StyleVO();
	      	        centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
	      	        centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	      	        centerboldStyleVO[1] = new StyleVO();
	      	        centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
	      	        centerboldStyleVO[1].setStyleValue("Left"); 
	      	        StyledData dataStyle2 = new StyledData();
	      	        dataStyle2.setStyles(centerboldStyleVO);
	                  dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(AmountTotal))+" only.");
	                  dataStyle2.setColspan(6);
	                  row1.add(dataStyle2);
	                  
	                  for(int c=0;c<6;c++)
	                  	row1.add("");
	      	        
	    	            
	    	            DataList.add(row1);

	                  logger.info("**********************"+DataList.size());
	                  return DataList;
	          
	    	              
	                
	              }
	              if(report.getReportCode().equals("9") ||report.getReportCode().equals("15"))
	           	  {
	                  
	                  
	            	  	
	   	           
	  	  			StringBuffer lsb = new StringBuffer(  );      
	  	  			
	  	  			final int nonGovtReportSize=19;
	  	  			
	  	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
	  	            int month=0;
	  	            int year=0;
	  	            String Department="";
	  	            Department=CheckIfNull(report.getParameterValue( "Department" ));
	            //added by samir joshi for bill no wise report
	  	            if(Department.equals("")||Department.equals("-1"))
	  	            	Department=	locationId+"";	
	  	            
	  	            String BillNoinner="";//GAD specific
	  	            BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
	  	 	        StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
	  		        int l=0;
	  		        String subheadCode="";
	  		        String classIds="";
	  		        String desgnIds="";
	  		       // String billNo="";
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
	  	 	        
	  	 	        //ended by samir joshi/////
	  	            if(!CheckIfNull(report.getParameterValue( "Month" )).equals(""))
	  	            {
	  	            	month=Integer.parseInt(CheckIfNull(report.getParameterValue( "Month" )));
	  	            }
	  	            if(!CheckIfNull(report.getParameterValue( "Year" )).equals(""))
	  	            {
	  	            	year=Integer.parseInt(CheckIfNull(report.getParameterValue( "Year" )));
	  	            }
	  	    		String DeptName=locationNameincaps;   
	  	            
	  	            //for report footer
	  /*	            ReportAttributeVO ravo = new ReportAttributeVO();
	  	            
	  	    		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	  	    		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	  	    		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

	  	    		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

	            //add the attribute to the report instance
	  	    		report.addReportAttributeItem(ravo);*/
	  				ArrayList tblData = new ArrayList();

	  				ArrayList trow1 = new ArrayList();

	  				trow1.add(" ");
	  				trow1.add(" ");
	  				trow1.add(" "+System.getProperty("line.separator"));
	  				tblData.add(trow1);//added first row of the tabular data


	  				ArrayList trow2 = new ArrayList();

	  				trow2.add(" ");
	  				trow2.add(" ");
	  				trow2.add(" "+System.getProperty("line.separator"));
	  				tblData.add(trow2);//added second row of the tabular data


	  				ArrayList trow4 = new ArrayList();

	  				trow4.add(" ");
	  				trow4.add(" ");
	  				trow4.add("UNDER SECRRETARY TO GOVT. ");
	  				tblData.add(trow4);//added second row of the tabular data


	  				ArrayList trow5 = new ArrayList();

	  				trow5.add(" ");
	  				trow5.add(" ");
	  				trow5.add("GENERAL ADMIN. DEPTT.");
	  				tblData.add(trow5);//added second row of the tabular data

	  				ArrayList trow3 = new ArrayList();

	  				trow3.add(" ");
	  				trow3.add(" ");
	  				trow3.add("GANDHINAGAR");

	  				tblData.add(trow3);//added third row of the tabular data

	  				ArrayList trow6 = new ArrayList();
	  				trow6.add(" ");
	  				trow6.add(" ");
	  				trow6.add("Code No.PG3");

	  				tblData.add(trow6);//added sixth row of the tabular data

	  				TabularData tabularData = new TabularData(tblData);//initialize tabular data
	  				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
	  				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
	  				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
	  				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
	  				tabularData.addStyle(IReportConstants.BORDER, "No"); 

	  				report.setGrandTotalTemplate(tabularData);
	  				report.setGroupByTotalTemplate(tabularData);
	  		        //

	  	            Calendar cal = Calendar.getInstance();
	  	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	  	            
	  	            cal.set(Calendar.YEAR, year);
	  	            cal.set(Calendar.MONTH,month-1);
	  	            cal.set(Calendar.DAY_OF_MONTH, 1);
	  	            
	  	            java.util.Date date = cal.getTime();
	  	            String selDate  = sdfObj.format(date);
	  	            
	  	            cal.set(Calendar.YEAR, year);
	  	            cal.set(Calendar.MONTH, month-1);
	  	            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
	  	            java.util.Date endMonthDate = cal.getTime();
	  	            
	  	            String endDate  = sdfObj.format(endMonthDate);

	  	            
	  	            
	  	            Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	  	            ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
	  	            
	  	            SessionFactory sessionFactory = serv.getSessionFactory();
	  	            Session session = sessionFactory.getCurrentSession();
	  	            
	  			    sdfObj = new SimpleDateFormat("MMM");
	  		        String Month = sdfObj.format(date);
	  		        
	  		        report.setReportName(DeptName+System.getProperty("line.separator")+"Statement of NON GOVERNMENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
	  	            
	  	            lsb.append("select ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empPrefix||' '||bill.hrEisEmpMst.orgEmpMst.empFname||' '||bill.hrEisEmpMst.orgEmpMst.empMname||' '||bill.hrEisEmpMst.orgEmpMst.empLname,");
	  	            lsb.append(" sum(bill.netTotal), ");
	  	            
	  	            lsb.append(" max(non.societyNew) ");//societynew
	  	            
	  	            lsb.append(", max(non.nagrikBank) ");//nagrik
	  	            
	  	            lsb.append(", max(non.lic) ");//lic
	  	            
	  	            lsb.append(", max(non.karamChariBank) ");//karmachari
	  	           
	  	            lsb.append(", max(non.societyOld) ");//society old
	    
	  	            lsb.append(", max(non.postOfficeRecurringDeposit) ");//Rec Dep
	  	            
	  	            lsb.append(", max(non.chiefMinisterReliefFund) ");//CM RF
	  	            
	  	            lsb.append(" ,(select non.nonGovDeducAccNo " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("societyOld")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//society old
	  	            
	  	            lsb.append(", (select non.nonGovDeducAccNo " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("society")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//society New
	  	            
	  	            lsb.append(", max(non.salarySavingFund) ");//Salary saving Fund
	  	            
	  	            
	  	            lsb.append(" from ");
	  	            lsb.append(" HrPayPaybill bill,HrPayPayslipNonGovt non ");
	  	            
	  	            lsb.append("  ,OrgUserpostRlt           USRPST, ");
	        	lsb.append("  HrPayOrderHeadMpg ORDHD, ");
	        	lsb.append("  HrPayOrderHeadPostMpg ORDPST, ");
	        	lsb.append("  OrgPostDetailsRlt pst ");
	  	            
	  	            lsb.append("  where  ");
	  	            lsb.append("  non.paybillID=bill.id and non.totalNonGovtDeduc <> 0 and ");
	  	            lsb.append("   ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where bill.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )");
	  	            lsb.append("  and ORDPST.orderHeadId = ORDHD.orderHeadId ");
	  	            lsb.append("  and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
	  	            lsb.append("  and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
	  	            lsb.append("  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ");
	            lsb.append("  and (USRPST.endDate is null or ");
	            
	            
	            
	            
	            lsb.append("   (USRPST.endDate>='"+selDate+"' and USRPST.startDate<='"+endDate+"'    ))");
	            lsb.append("   and bill.approveFlag in (0,1) ");
	  	            
	  	            lsb.append(" and ");	            
	  	            if(!empid.equals("") && !empid.equals("-1"))
	  	            	lsb.append(" bill.hrEisEmpMst.empId = '"+empid+"' and ");
	  	    		
	  	            lsb.append(" bill.month ='"+month+"' and ");
	  	            
	  	            lsb.append(" bill.year = '"+year+"'");
	  	            
	  		          
	  	            if(!Department.equals("")&&!Department.equals("-1"))            	
	  	            lsb.append(" and pst.cmnLocationMst.locationCode=" + Department);
	  	            if(isBillDefined&&!BillNo.equals(""))
	                  {
	                 	 
	  	            	lsb.append("   and bill.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "); 
	  	            	lsb.append("   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pst.orgPostMst.postId = bill.orgPostMst.postId  "); 
	                  }
	                  else
	                  {
	  		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	  	            {
	  		        	lsb.append("   and ORDHD.subheadId ="+subheadCode+" ");
	  		        }
	  		        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	  	            {
	  		        	lsb.append("  and bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
	  		        }
	  		        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	  	            {
	  		        	lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
	  		        }
	                  }
	  /*		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	  	            {
	  		        	lsb.append("  and  ORDHD.subheadId ="+subheadCode+" ");
	  		        }
	  		        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	  	            {
	  		        	lsb.append("  and bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
	  		        }
	  		        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	  	            {
	  		        	lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
	  		        }*/
	  	            lsb.append(" group by  ");
	  	            lsb.append(" bill.hrEisEmpMst.empId,bill.hrEisEmpMst.orgEmpMst.empPrefix, ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empFname, ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empMname, ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empLname ");
	  	            
	  	            
	  	            String strQuery = lsb.toString(); 
	        	
	        	logger.info("the query is "+strQuery);
	        	Query query = session.createQuery(strQuery);
	  	            
	  	            int cnt=1;
	  	            long empId=0;
	        	
	        	ArrayList rowList=new ArrayList();
	        	
	        	long netAmttotal=0;
	        	long cSocOldtotal=0;
	        	long nagriktotal=0;
	        	long lictotal=0;
	        	long karamcharitotal=0;
	        	long RecDepototal=0;
	        	long CMRFtotal=0;
	        	long SSFtotal=0;
	        	long totalDeductotal=0;
	        	long netPaytotal=0;
	        	long cSocNewtotal=0;
	        	int pageCnt=1;
	        	for(Iterator it=query.iterate();it.hasNext();)
	  			{
	  			    Object[] row = (Object[]) it.next();
	  			    Double zero = new Double(0);
	  			    String empName 			= (String)(row[0]!=null?row[0]:zero);//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
	  			    Long netAmt 			= Math.round((Double)(row[1]!=null?row[1]:zero));//netAmt
	  			    Long cSocOld 		=  new Long(0);
	  			    Long cSocNew 		=  new Long(0);
	  			    Long nagrik = 	 new Long(0);
	  			    Long lic 	= 	 new Long(0);
	  			    Long karamchari =  new Long(0);
	  			    long RecDepo=0;
	  			    long CMRF=0;
	  			    long SSF=0;
	  			    String SocOldAcctNo="";
	  			    String SocNewAcctNo="";
	  			    RecDepo=Long.parseLong(row[7]!=null?row[7].toString():"0");
	   			    CMRF=Long.parseLong(row[8]!=null?row[8].toString():"0");
	   			    SSF=Long.parseLong(row[11]!=null?row[11].toString():"0");
	  			    SocOldAcctNo=row[9]!=null?("OLD:"+row[9].toString()):"";
	  			    SocNewAcctNo=row[10]!=null?("NEW:"+row[10].toString()):"";

	  			    
	  			    if(row[2]!=null)
	  			  {
	  				  cSocNew 		= (Long)(row[2]);//credit society new
	  			  }
	  			  if(row[3]!=null)
	  			  {
	  				  nagrik = 	(Long)(row[3]);//nagrik bank
	  			  }
	  			  if(row[4]!=null)
	  			  {
	  				  lic 	= 		(Long)(row[4]);//lic
	  			  }
	  			  if(row[5]!=null)
	  			  {
	  				  karamchari 	= (Long)(row[5]);//karamchari bank
	  			  }
	  			  if(row[6]!=null)
	  	   			   {
	  	   				   cSocOld 		= (Long)row[6];//credit society old
	  	   			   }
	  			  
	  			   
	  				
	  				  rowList = new ArrayList();

	  				  rowList.add(new Integer(cnt));
	  				  if(!SocNewAcctNo.equals(""))
	  		              rowList.add(empName+System.getProperty("line.separator")+SocNewAcctNo);
	  				  else
	  				  rowList.add(empName+System.getProperty("line.separator")+SocOldAcctNo);	  
	  		              rowList.add(Math.round(netAmt));
	  		              rowList.add(cSocOld);
	  		              rowList.add(cSocNew);
	  		              rowList.add(nagrik);
	  		              rowList.add(lic);
	  		              rowList.add(karamchari);
	  		              rowList.add(RecDepo);
	  		              rowList.add(CMRF);
	  		              rowList.add(SSF);
	  		              
	  		              long totalDeduc = cSocOld+cSocNew+nagrik+lic+karamchari;
	  		              double netPay = netAmt - totalDeduc;
	  		              rowList.add(totalDeduc);
	  		              rowList.add(Math.round(netPay));
	  		              rowList.add(pageCnt);

	  		                netAmttotal+=netAmt;
	  		            	cSocOldtotal+=cSocOld;
	  		            	nagriktotal+=nagrik;
	  		            	lictotal+=lic;
	  		            	karamcharitotal+=karamchari;
	  		            	RecDepototal+=RecDepo;
	  		            	CMRFtotal+=CMRF;
	  		            	SSFtotal+=SSF;
	  		            	totalDeductotal+=totalDeduc;
	  		            	netPaytotal+=netPay;
	  		            	cSocNewtotal+=cSocNew;
	  	              
	  		              DataList.add(rowList); 
	  		              
	  		              if(cnt==(nonGovtReportSize-1)||(cnt%(nonGovtReportSize-2)==1&&cnt>(nonGovtReportSize-1)))
	  		              {
	  		            	  rowList = new ArrayList();
	  						  
	  						  rowList.add("");
	  						  
	  						  rowList.add("B/F");	  
	  			              rowList.add(netAmttotal);
	  			              rowList.add(cSocOldtotal);
	  			              rowList.add(cSocNewtotal);
	  			              rowList.add(nagriktotal);
	  			              rowList.add(lictotal);
	  			              rowList.add(karamcharitotal);
	  			              rowList.add(RecDepototal);
	  			              rowList.add(CMRFtotal);
	  			              rowList.add(SSFtotal);
	  			              rowList.add(totalDeductotal);
	  			              rowList.add(Math.round(netPaytotal));
	  			              rowList.add(pageCnt);
	  			              DataList.add(rowList); 

	  			    		
	  			    			
	  			              //New Page Starts Here
	  			              pageCnt++;
	  			              
	  			              rowList = new ArrayList();
	  		            	  rowList.add(new PageBreak());
	  		            	  rowList.add("Data");
	  		            	  DataList.add(rowList); 
	  		            	  
	  			              rowList = new ArrayList();
	  			              
	  						  
	  						  rowList.add("");
	  						  
	  						  rowList.add("C/F");	  
	  			              rowList.add(netAmttotal);
	  			              rowList.add(cSocOldtotal);
	  			              rowList.add(cSocNewtotal);
	  			              rowList.add(nagriktotal);
	  			              rowList.add(lictotal);
	  			              rowList.add(karamcharitotal);
	  			              rowList.add(RecDepototal);
	  			              rowList.add(CMRFtotal);
	  			              rowList.add(SSFtotal);
	  			              rowList.add(totalDeductotal);
	  			              rowList.add(Math.round(netPaytotal));
	  			              rowList.add(pageCnt);
	  			              
	  			              DataList.add(rowList); 
	  			              
	  			              
	  			           }
	  		              
	  		              cnt++;
	  		              
	  			} 
	  	            if(netAmttotal!=0)
	  	            {
	  	            	
	  	           
//	        	 for last page total
	        			rowList = new ArrayList();
	  				  
	  				  rowList.add("");
	  				  
	  				  rowList.add("Total");	  
	  	              rowList.add(netAmttotal);
	  	              rowList.add(cSocOldtotal);
	  	              rowList.add(cSocNewtotal);
	  	              rowList.add(nagriktotal);
	  	              rowList.add(lictotal);
	  	              rowList.add(karamcharitotal);
	  	              rowList.add(RecDepototal);
	  	              rowList.add(CMRFtotal);
	  	              rowList.add(SSFtotal);
	  	              rowList.add(totalDeductotal);
	  	              rowList.add(Math.round(netPaytotal));
	  	              rowList.add(pageCnt);
	  	              
	  	              DataList.add(rowList); 
	  	              
//	  	            for last page total
	        	rowList = new ArrayList();
	        	StyleVO[] centerboldStyleVO = new StyleVO[2];
	  	        centerboldStyleVO[0] = new StyleVO();
	  	        centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
	  	        centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	  	        centerboldStyleVO[1] = new StyleVO();
	  	        centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
	  	        centerboldStyleVO[1].setStyleValue("Left"); 
	  	        StyledData dataStyle2 = new StyledData();
	  	        dataStyle2.setStyles(centerboldStyleVO);
	            dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(netPaytotal))+" only.");
	            
	            ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
	            ReportColumnVO[] rptCol = report.getColumnsToDisplay();
	            int totallength=rptCol.length;
	            int colspan=rptCol2.length;                
	            
	            dataStyle2.setColspan(colspan);
	            rowList.add(dataStyle2);
	              
	        	
	  	            				  
	            for(int c=0;c<(totallength-colspan);c++)
	            	rowList.add("");

	            rowList.add(pageCnt);
	  	              
	  	              DataList.add(rowList); 
	  	             
	  	              StyleVO[] URLStyleVO=null;
	  	            	URLStyleVO = new StyleVO[2];
	  	              
	  	              
	  	              URLStyleVO[0] = new StyleVO();
	  	              URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
	  	              URLStyleVO[0].setStyleValue(nonGovtReportSize+"");
	  	              
	  	              URLStyleVO[1] = new StyleVO();
	  	              URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
	  	              URLStyleVO[1].setStyleValue("yes");
	  	              
	  	              report.addReportStyles(URLStyleVO);
	  	              
	  	                      	             
	  	            SortingHelper Helper = new SortingHelper(DataList);
	  		            
	            report.setReportColumns(rptCol);
	            
	            report.initializeDynamicTreeModel();
	            report.initializeTreeModel(); 
	  	            } 

	              return DataList;
	  	    
	        
	          
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  
	          	  /*          
	                
	        	  	
	  		           
	  	  			StringBuffer lsb = new StringBuffer(  );      
	  	  			
	  	  			final int nonGovtReportSize=19;
	  	  			
	  	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
	  	            int month=0;
	  	            int year=0;
	  	            String Department="";
	  	            Department=CheckIfNull(report.getParameterValue( "Department" ));
	                //added by samir joshi for bill no wise report
	  	            if(Department.equals("")||Department.equals("-1"))
	  	            	Department=	locationId+"";	
	  	            
	  	            String BillNoinner="";//GAD specific
	  	            BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
	  	 	        StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
	  		        int l=0;
	  		        String subheadCode="";
	  		        String classIds="";
	  		        String desgnIds="";
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
	  	 	        
	  	 	        //ended by samir joshi/////
	  	            if(!CheckIfNull(report.getParameterValue( "Month" )).equals(""))
	  	            {
	  	            	month=Integer.parseInt(CheckIfNull(report.getParameterValue( "Month" )));
	  	            }
	  	            if(!CheckIfNull(report.getParameterValue( "Year" )).equals(""))
	  	            {
	  	            	year=Integer.parseInt(CheckIfNull(report.getParameterValue( "Year" )));
	  	            }
	  	             
	  	            
	  	            //for report footer
	  	            ReportAttributeVO ravo = new ReportAttributeVO();
	  	            
	  	    		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	  	    		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	  	    		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
	  	    		String DeptName=locationNameincaps;
	  	    		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

	                //add the attribute to the report instance
	  	    		report.addReportAttributeItem(ravo);

	  	            Calendar cal = Calendar.getInstance();
	  	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	  	            
	  	            cal.set(Calendar.YEAR, year);
	  	            cal.set(Calendar.MONTH,month-1);
	  	            cal.set(Calendar.DAY_OF_MONTH, 1);
	  	            
	  	            java.util.Date date = cal.getTime();
	  	            String selDate  = sdfObj.format(date);
	  	            
	  	            cal.set(Calendar.YEAR, year);
	  	            cal.set(Calendar.MONTH, month-1);
	  	            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(5));
	  	            java.util.Date endMonthDate = cal.getTime();
	  	            
	  	            String endDate  = sdfObj.format(endMonthDate);

	  	            
	  	            
	  	            Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	  	            ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
	  	            
	  	            SessionFactory sessionFactory = serv.getSessionFactory();
	  	            Session session = sessionFactory.getCurrentSession();
	  	            
	  			    sdfObj = new SimpleDateFormat("MMM");
	  		        String Month = sdfObj.format(date);
	  		        
	  		        report.setReportName(DeptName+System.getProperty("line.separator")+"Statement of NON GOVERNMENT Deductions for the month of  "+Month+". "+year);  
	  	            
	  	            lsb.append("select ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empPrefix||' '||bill.hrEisEmpMst.orgEmpMst.empFname||' '||bill.hrEisEmpMst.orgEmpMst.empMname||' '||bill.hrEisEmpMst.orgEmpMst.empLname,");
	  	            lsb.append(" sum(bill.netTotal), ");
	  	            //lsb.append("(select non.nonGovDeducAmount from hrPayNonGovDeduction non where non.nonGovDeducType = 48 and bill.hrEisEmpMst.emp_id = non.emp_id)");//society old
	  	            //lsb.append(" (select non.nonGovDeducAmount from HrPayNonGovDeduction non where non.hrPayDeducTypeMst.deducCode = "+resourceBundle.getString("society")+" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId and non.nonGovDeducEfftStartDt),");//society new
	  	            //lsb.append(" (select non.nonGovDeducAmount from HrPayNonGovDeduction non where non.hrPayDeducTypeMst.deducCode = "+resourceBundle.getString("nagrikBank")+" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId),");//nagrik
	  	            //lsb.append(" (select non.nonGovDeducAmount from HrPayNonGovDeduction non where non.hrPayDeducTypeMst.deducCode = "+resourceBundle.getString("LIC")+" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId),");//lic
	  	            //lsb.append(" (select non.nonGovDeducAmount from HrPayNonGovDeduction non where non.hrPayDeducTypeMst.deducCode = "+resourceBundle.getString("karmcharyBank")+" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId) ");//karmachari
	  	            
	  	            lsb.append(" (select non.nonGovDeducAmount " +
	        				" from HrPayNonGovDeduction non " +
	        				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("society")+
	        				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	        				" and non.nonGovDeducEfftStartDt <='"+selDate +
	        				"' and non.nonGovDeducEfftEndDt >='"+selDate +
	        				" and to_char(non.nonGovDeducEfftStartDt,'yyyy') <="+year +
	        				" and to_char(non.nonGovDeducEfftEndDt,'yyyy') >="+year +"' ), ");//societynew 
	  	            lsb.append(" (select non.nonGovDeducAmount " +
	        				" from HrPayNonGovDeduction non " +
	        				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("nagrikBank")+
	        				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	        				" and non.nonGovDeducEfftStartDt <='"+selDate +
	        				"' and non.nonGovDeducEfftEndDt >='"+selDate +
	        				" and to_char(non.nonGovDeducEfftStartDt,'yyyy') <="+year +
	        				" and to_char(non.nonGovDeducEfftEndDt,'yyyy') >="+year +"' ), ");//nagrik
	  	            lsb.append(" (select non.nonGovDeducAmount " +
	        				" from HrPayNonGovDeduction non " +
	        				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("LIC")+
	        				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	        				" and non.nonGovDeducEfftStartDt <='"+selDate +
	        				"' and non.nonGovDeducEfftEndDt >='"+selDate +
	        				" and to_char(non.nonGovDeducEfftStartDt,'yyyy') <="+year +
	        				" and to_char(non.nonGovDeducEfftEndDt,'yyyy') >="+year +"' ), ");//lic
	  	            lsb.append(" (select non.nonGovDeducAmount " +
	        				" from HrPayNonGovDeduction non " +
	        				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("karmcharyBank")+
	        				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	        				" and non.nonGovDeducEfftStartDt <='"+selDate +
	        				"' and non.nonGovDeducEfftEndDt >='"+selDate +
	        				" and to_char(non.nonGovDeducEfftStartDt,'yyyy') <="+year +
	        				" and to_char(non.nonGovDeducEfftEndDt,'yyyy') >="+year +"' ), ");//karmachari
	  	           
	  	            lsb.append(" (select non.nonGovDeducAmount " +
	  				" from HrPayNonGovDeduction non " +
	  				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("societyOld")+
	  				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  				"' and non.nonGovDeducEfftEndDt >='"+selDate +
	  				" and to_char(non.nonGovDeducEfftStartDt,'yyyy') <="+year +
	  				" and to_char(non.nonGovDeducEfftEndDt,'yyyy') >="+year +"' ) ");//society old
	        
	  	            lsb.append(", (select non.nonGovDeducAmount " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("POR")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//Rec Dep
	  	            
	  	            lsb.append(" ,(select non.nonGovDeducAmount " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("CMRF")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//CM RF
	  	            
	  	            lsb.append(" ,(select non.nonGovDeducAccNo " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("societyOld")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//society old
	  	            
	  	            lsb.append(", (select non.nonGovDeducAccNo " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("society")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//society New
	  	            
	  	            lsb.append(", (select non.nonGovDeducAmount " +
	  	    				" from HrPayNonGovDeduction non " +
	  	    				" where non.hrPayDeducTypeMst.deducCode = "+constantsBundle.getString("SSF")+
	  	    				" and bill.hrEisEmpMst.empId = non.hrEisEmpMst.empId " +
	  	    				" and non.nonGovDeducEfftStartDt <='"+selDate +
	  	    				"' and non.nonGovDeducEfftEndDt >='"+selDate +"' ) ");//Salary saving Fund
	  	            
	  	            
	  	            lsb.append(" from ");
	  	            lsb.append(" HrPayPaybill bill ");
	  	            
	  	            lsb.append("  ,OrgUserpostRlt           USRPST, ");
	            	lsb.append("  HrPayOrderHeadMpg ORDHD, ");
	            	lsb.append("  HrPayOrderHeadPostMpg ORDPST, ");
	            	lsb.append("  OrgPostDetailsRlt pst ");
	  	            
	  	            lsb.append("  where  ");
	  	            
	  	            lsb.append("   ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where bill.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )");
	  	            lsb.append("  and ORDPST.orderHeadId = ORDHD.orderHeadId ");
	  	            lsb.append("  and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
	  	            lsb.append("  and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
	  	            lsb.append("  and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ");
	                lsb.append("  and (USRPST.endDate is null or ");
	                
	                
	                
	                
	                lsb.append("   (USRPST.endDate>='"+selDate+"' and USRPST.startDate<='"+endDate+"'    ))");
	                lsb.append("   and bill.approveFlag in (0,1) ");
	  	            
	  	            lsb.append(" and ");	            
	  	            if(!empid.equals("") && !empid.equals("-1"))
	  	            	lsb.append(" bill.hrEisEmpMst.empId = '"+empid+"' and ");
	  	    		
	  	            lsb.append(" bill.month ='"+month+"' and ");
	  	            
	  	            lsb.append(" bill.year = '"+year+"'");
	  	            
	  		          
	  	            if(!Department.equals("")&&!Department.equals("-1"))            	
	  	            lsb.append(" and pst.cmnLocationMst.locationCode=" + Department + "  ");
	  	            if(isBillDefined&&!BillNo.equals(""))
	                  {
	                 	 
	  	            	lsb.append("   and bill.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "); 
	  	            	lsb.append("   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pst.orgPostMst.postId = bill.orgPostMst.postId  "); 
	                  }
	                  else
	                  {
	  		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	  	            {
	  		        	lsb.append("   and ORDHD.subheadId ="+subheadCode+" ");
	  		        }
	  		        if(classIds != null && !classIds.equals("") && !classIds.equals("-1"))
	  	            {
	  		        	lsb.append("  and bill.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId  in ("+classIds+")");
	  		        }
	  		        if(desgnIds != null && !desgnIds.equals("") && !desgnIds.equals("-1"))
	  	            {
	  		        	lsb.append("  and pst.orgDesignationMst.dsgnId in (" +desgnIds+ ") ");
	  		        }
	                  }
	  	            lsb.append(" group by  ");
	  	            lsb.append(" bill.hrEisEmpMst.empId,bill.hrEisEmpMst.orgEmpMst.empPrefix, ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empFname, ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empMname, ");
	  	            lsb.append(" bill.hrEisEmpMst.orgEmpMst.empLname ");
	  	            
	  	            
	  	            String strQuery = lsb.toString(); 
	            	
	            	logger.info("the query is "+strQuery);
	            	Query query = session.createQuery(strQuery);
	  	            
	  	            int cnt=1;
	  	            long empId=0;
	            	
	            	ArrayList rowList=new ArrayList();
	            	
	            	long netAmttotal=0;
	            	long cSocOldtotal=0;
	            	long nagriktotal=0;
	            	long lictotal=0;
	            	long karamcharitotal=0;
	            	long RecDepototal=0;
	            	long CMRFtotal=0;
	            	long SSFtotal=0;
	            	long totalDeductotal=0;
	            	long netPaytotal=0;
	            	long cSocNewtotal=0;
	            	int pageCnt=1;
	            	for(Iterator it=query.iterate();it.hasNext();)
	    			{
	    			    Object[] row = (Object[]) it.next();
	    			    Double zero = new Double(0);
	    			    String empName 			= (String)(row[0]!=null?row[0]:zero);//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
	    			    double netAmt 			= (Double)(row[1]!=null?row[1]:zero);//netAmt
	    			    Long cSocOld 		=  new Long(0);
	    			    Long cSocNew 		=  new Long(0);
	    			    Long nagrik = 	 new Long(0);
	    			    Long lic 	= 	 new Long(0);
	    			    Long karamchari =  new Long(0);
	    			    long RecDepo=0;
	    			    long CMRF=0;
	    			    long SSF=0;
	    			    String SocOldAcctNo="";
	    			    String SocNewAcctNo="";
	    			    RecDepo=Long.parseLong(row[7]!=null?row[7].toString():"0");
	       			    CMRF=Long.parseLong(row[8]!=null?row[8].toString():"0");
	       			    SSF=Long.parseLong(row[11]!=null?row[11].toString():"0");
	    			    SocOldAcctNo=row[9]!=null?("OLD:"+row[9].toString()):"";
	    			    SocNewAcctNo=row[10]!=null?("NEW:"+row[10].toString()):"";

	    			    
	    			    if(row[2]!=null)
	    			  {
	    				  cSocNew 		= (Long)(row[2]);//credit society new
	    			  }
	    			  if(row[3]!=null)
	    			  {
	    				  nagrik = 	(Long)(row[3]);//nagrik bank
	    			  }
	    			  if(row[4]!=null)
	    			  {
	    				  lic 	= 		(Long)(row[4]);//lic
	    			  }
	    			  if(row[5]!=null)
	    			  {
	    				  karamchari 	= (Long)(row[5]);//karamchari bank
	    			  }
	    			  if(row[6]!=null)
	  	   			   {
	  	   				   cSocOld 		= (Long)row[6];//credit society old
	  	   			   }
	    			  
	    			   
	    				
	    				  rowList = new ArrayList();
	    				  
	    				  rowList.add(new Integer(cnt));
	    				  if(!SocNewAcctNo.equals(""))
	  		              rowList.add(empName+System.getProperty("line.separator")+SocNewAcctNo);
	    				  else
	    				  rowList.add(empName+System.getProperty("line.separator")+SocOldAcctNo);	  
	  		              rowList.add(Math.round(netAmt));
	  		              rowList.add(cSocOld);
	  		              rowList.add(cSocNew);
	  		              rowList.add(nagrik);
	  		              rowList.add(lic);
	  		              rowList.add(karamchari);
	  		              rowList.add(RecDepo);
	  		              rowList.add(CMRF);
	  		              rowList.add(SSF);
	  		              
	  		              long totalDeduc = cSocOld+cSocNew+nagrik+lic+karamchari;
	  		              double netPay = netAmt - totalDeduc;
	  		              rowList.add(totalDeduc);
	  		              rowList.add(Math.round(netPay));
	  		              rowList.add(pageCnt);

	  		                netAmttotal+=netAmt;
	  		            	cSocOldtotal+=cSocOld;
	  		            	nagriktotal+=nagrik;
	  		            	lictotal+=lic;
	  		            	karamcharitotal+=karamchari;
	  		            	RecDepototal+=RecDepo;
	  		            	CMRFtotal+=CMRF;
	  		            	SSFtotal+=SSF;
	  		            	totalDeductotal+=totalDeduc;
	  		            	netPaytotal+=netPay;
	  		            	cSocNewtotal+=cSocNew;
	  		              
	  		              DataList.add(rowList); 
	  		              
	  		              if(cnt==(nonGovtReportSize-1)||(cnt%(nonGovtReportSize-2)==1&&cnt>(nonGovtReportSize-1)))
	  		              {
	  		            	  rowList = new ArrayList();
	  						  
	  						  rowList.add("");
	  						  
	  						  rowList.add("B/F");	  
	  			              rowList.add(netAmttotal);
	  			              rowList.add(cSocOldtotal);
	  			              rowList.add(cSocNewtotal);
	  			              rowList.add(nagriktotal);
	  			              rowList.add(lictotal);
	  			              rowList.add(karamcharitotal);
	  			              rowList.add(RecDepototal);
	  			              rowList.add(CMRFtotal);
	  			              rowList.add(SSFtotal);
	  			              rowList.add(totalDeductotal);
	  			              rowList.add(Math.round(netPaytotal));
	  			              rowList.add(pageCnt);
	  			              
	  			              DataList.add(rowList); 
	  			              
	  			              //New Page Starts Here
	  			              pageCnt++;

	  			              rowList = new ArrayList();
	  						  
	  						  rowList.add("");
	  						  
	  						  rowList.add("C/F");	  
	  			              rowList.add(netAmttotal);
	  			              rowList.add(cSocOldtotal);
	  			              rowList.add(cSocNewtotal);
	  			              rowList.add(nagriktotal);
	  			              rowList.add(lictotal);
	  			              rowList.add(karamcharitotal);
	  			              rowList.add(RecDepototal);
	  			              rowList.add(CMRFtotal);
	  			              rowList.add(SSFtotal);
	  			              rowList.add(totalDeductotal);
	  			              rowList.add(Math.round(netPaytotal));
	  			              rowList.add(pageCnt);
	  			              
	  			              DataList.add(rowList); 
	  			              
	  			              
	  			           }
	  		              
	  		              cnt++;
	  		              
	    			} 
	  	            
//	            	 for last page total
	            	rowList = new ArrayList();
	  				  
	  				  rowList.add("");
	  				  
	  				  rowList.add("Total");	  
	  	              rowList.add(netAmttotal);
	  	              rowList.add(cSocOldtotal);
	  	              rowList.add(cSocNewtotal);
	  	              rowList.add(nagriktotal);
	  	              rowList.add(lictotal);
	  	              rowList.add(karamcharitotal);
	  	              rowList.add(RecDepototal);
	  	              rowList.add(CMRFtotal);
	  	              rowList.add(SSFtotal);
	  	              rowList.add(totalDeductotal);
	  	              rowList.add(Math.round(netPaytotal));
	  	              rowList.add(pageCnt);
	  	              
	  	              DataList.add(rowList); 
	  	              
//	  	            for last page total
	            	rowList = new ArrayList();
	            	StyleVO[] centerboldStyleVO = new StyleVO[2];
	    	        centerboldStyleVO[0] = new StyleVO();
	    	        centerboldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
	    	        centerboldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
	    	        centerboldStyleVO[1] = new StyleVO();
	    	        centerboldStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
	    	        centerboldStyleVO[1].setStyleValue("Left"); 
	    	        StyledData dataStyle2 = new StyledData();
	    	        dataStyle2.setStyles(centerboldStyleVO);
	                dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(netPaytotal))+" only.");
	                
	                ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
	                ReportColumnVO[] rptCol = report.getColumnsToDisplay();
	                int totallength=rptCol.length;
	                int colspan=rptCol2.length;                
	                
	                dataStyle2.setColspan(colspan);
	                rowList.add(dataStyle2);
	                  
	            	
	  	            				  
	                for(int c=0;c<(totallength-colspan);c++)
	                	rowList.add("");

	                rowList.add(pageCnt);
	  	              
	  	              DataList.add(rowList); 
	  		              
	  	              StyleVO[] URLStyleVO=null;
	  	            	URLStyleVO = new StyleVO[2];
	  	              
	  	              
	  	              URLStyleVO[0] = new StyleVO();
	  	              URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
	  	              URLStyleVO[0].setStyleValue(nonGovtReportSize+"");
	  	              
	  	              URLStyleVO[1] = new StyleVO();
	  	              URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
	  	              URLStyleVO[1].setStyleValue("yes");
	  	              
	  	              report.addReportStyles(URLStyleVO);
	  	              
	  	              	              	             
	  	            SortingHelper Helper = new SortingHelper(DataList);
	  		            
	                report.setReportColumns(rptCol);
	                report.initializeDynamicTreeModel();
	                report.initializeTreeModel(); 

	     
	                  return DataList;
	  	    */  
	              }

		        }
			    catch(Exception e)
			    {
			    	logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			    	logger.error("Printing StackTrace");
			    	logger.error("Error is: "+ e.getMessage());
			    }
		        finally
		  		{

		  		}
		        return DataList;
		    }
	          //end by manoj for Festival and foodgrain advance report
//	        by manoj for Non Govt Deduction report
		        
		 
	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	     throws ReportException
	 {

	     logger.info ("***********Inside User Report VO *********************");
		 Hashtable sessionKeys = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.SESSION_KEYS );
	     Calendar cal = Calendar.getInstance();
	     Date today = cal.getTime();
	     SimpleDateFormat fmt =new SimpleDateFormat("dd/MM/yyyy");
	     String date = fmt.format(today);
	     SimpleDateFormat fmt1 =new SimpleDateFormat("yyyy");
	     String yr = fmt1.format(today);
	     fmt1 =new SimpleDateFormat("MM");
	     
		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
	     String month = fmt1.format(today);
	     
	     if(month.charAt(0)=='0')
	     {
	    	 
	    	 month=month.substring(1,2);
	    	 
	     }
	    	 
	     if(  report.getReportCode().equals("3")||report.getReportCode().equals("6")|| report.getReportCode().equals("7")|| report.getReportCode().equals("8")|| report.getReportCode().equals("10")|| report.getReportCode().equals("11")|| report.getReportCode().equals("13"))
	     {            
	          report.setParameterValue("Year",yr);
	          report.setParameterValue("Month",month);
	     }
	     if( report.getReportCode().equals("4") || report.getReportCode().equals("5") || report.getReportCode().equals("9"))
	     {            
	          report.setParameterValue("Year",yr);
	          report.setParameterValue("Month",month);
	     } 
	    /* if( report.getReportCode().equals("3") || report.getReportCode().equals("4") || report.getReportCode().equals("5") || report.getReportCode().equals("6") || report.getReportCode().equals("7") || report.getReportCode().equals("8") || report.getReportCode().equals("9") || report.getReportCode().equals("10")|| report.getReportCode().equals("11")|| report.getReportCode().equals("13"))
	     {            
	          report.setParameterValue("Department",locationId+"");
	     } */


	     return report;
	 }


}
