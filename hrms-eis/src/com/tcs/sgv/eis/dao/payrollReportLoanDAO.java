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
import com.tcs.sgv.common.valuebeans.reports.ReportParameterVO;
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

public class payrollReportLoanDAO extends DefaultReportDataFinder 
implements ReportDataFinder
{
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
//	    	Added by Mrugesh/Samir
  	        
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
          

//        by manoj for HBA, Scooter,Car loan and advance report
          if(report.getReportCode().equals("4")  || report.getReportCode().equals("20") || report.getReportCode().equals("21") || report.getReportCode().equals("22") || report.getReportCode().equals("23") || report.getReportCode().equals("24") || report.getReportCode().equals("25") || report.getReportCode().equals("26") || report.getReportCode().equals("27") || report.getReportCode().equals("28") || report.getReportCode().equals("29"))
          {          

		           
	  			StringBuffer lsb = new StringBuffer(  );      

	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
	            String Designation="";
	            String Grade="";
	            String Scale="";
	            int month=0;
	            int year=0;
	            String loan_type="";
	            String report_type="";
	            
	            String loanCol="loan9591"; // for HBA
	            
	            Grade=CheckIfNull(report.getParameterValue( "Grade" ));
	            Scale=CheckIfNull(report.getParameterValue( "Scale" ));
	            Designation=CheckIfNull(report.getParameterValue( "Designation" ));
	            
	            String department="";
	            department=CheckIfNull(report.getParameterValue( "Department" ));
              //added by samir joshi for bill no wise report
	            if(department.equals("")||department.equals("-1"))
	            	department=	locationId+"";	
	            
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
//	    		 Seetting additional header using tabular data ...
	    		  ArrayList d = new ArrayList();
	    		  ArrayList r = new ArrayList();
	    		  
	    		  r= new ArrayList();
	    		  r.add("To be Filled in by Drawing Officer");
	    		  r.add("To be filled in by Treasury Officer");
	    		  d.add(r);
	    		  r= new ArrayList();
	    		  r.add("District         :7  1");
	    		  r.add("Voucher No       :");
	    		  d.add(r);
	    		  r= new ArrayList();
	    		  r.add("Drawing Officer  : 012");
	    		  r.add("Month & Year     :");
	    		  d.add(r);
	    		  r= new ArrayList();
	    		    		  r.add("                      ");
	    		  r.add("Major Head       :");
	    		  d.add(r);
	    		  
	    		  TabularData td  = new TabularData(d);
	    		  td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
	    		  td.addStyle(IReportConstants.BORDER, "No"); 
	    		  report.setAdditionalHeader(td);
	    		
			    String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
		    	//(String)requestAttributes.get("demandNo");
		    String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
		    String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
		    String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
		    String subHead = CheckIfNull(report.getParameterValue("subHead"));
		    //String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
		    String subheadflag=subHead;
		    logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
		    if(!CheckIfNull(report.getParameterValue( "Month" )).equals(""))
          {
          	month=Integer.parseInt(CheckIfNull(report.getParameterValue( "Month" )));
          }
          if(!CheckIfNull(report.getParameterValue( "Year" )).equals(""))
          {
          	year=Integer.parseInt(CheckIfNull(report.getParameterValue( "Year" )));
          }
		    String subHeadId = "";
		    if(subHead!=null && !subHead.equals("")&& !subHead.equals("-1"))
		    {
			    
		        StringTokenizer st=new StringTokenizer(subHead,"~");
		        int i=0;
		        while(st.hasMoreTokens())
		        {
		        	if(i==0)
		        	demandNo=st.nextToken();
		        	else if(i==1)
		        	mjrHead=st.nextToken();
		        	else if(i==2)
		        	subMjrHead=st.nextToken();
		        	else if(i==3)
		        	mnrHead=st.nextToken();
		        	else
		        	subHead=st.nextToken();	
		         i++;
		        } 
		    	if(subheadflag.indexOf("~")<=0)
		    	{
				   demandNo = CheckIfNull(report.getParameterValue("demandNo")); 
			       mjrHead = CheckIfNull(report.getParameterValue("mjrHead"));
			       subMjrHead =  CheckIfNull(report.getParameterValue("subMjrHead"));
			       mnrHead = CheckIfNull(report.getParameterValue("mnrHead"));
			       logger.info("  sdfhdfgthfr Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
		    	}
		    	logger.info("        sdgdfhgfHead Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
		    	GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);

	            Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	            ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
	            
	            SessionFactory sessionFactory = serv.getSessionFactory();
	            Session session = sessionFactory.getCurrentSession();
		    	genDAO.setSessionFactory(serv.getSessionFactory());
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
	            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
	            long langId = cmnLanguageMst.getLangId();
			    PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			    
	            Calendar cal = Calendar.getInstance();
	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	            
	            	            
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH,month-1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            
	            java.util.Date startMonthDate = cal.getTime();
	            String startDate  = sdfObj.format(startMonthDate);
	            cal.set(Calendar.YEAR,( year));
	            cal.set(Calendar.MONTH,( month)-1);
	            
	            java.util.Date finYrDate = cal.getTime();			    
	            SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(finYrDate, langId);

	            String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
			    Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
			    List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);

			    SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
			    subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
			    logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
		        }
		    
              Calendar cal = Calendar.getInstance();
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH,month-1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            loan_type=CheckIfNull(report.getParameterValue( "LoanType" ));
	            java.util.Date date = cal.getTime();
	            SimpleDateFormat sdfObj = new SimpleDateFormat("MMM");
			    
		        String Month = sdfObj.format(date);
		        
		        report_type=CheckIfNull(report.getParameterValue( "ReportFor" ));
		        
//		      Modified By Mrugesh
		        if(report.getReportCode().equals("4"))
		        {
			        if(report_type.equals("principal"))
			        {
			        	if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
			        		loanCol="loan9591";
			        	else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
			        		loanCol="loan9592";
			        	else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
			        		loanCol="loan9592";
			        	else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
			        		loanCol="loan9592";
			        	else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
			        		loanCol="loan9592";
	
			        }	
			        	else
			        	{
				        	if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
				        		loanCol="loanInt9591";
				        	else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
				        		loanCol="loanInt9592";
				        	else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
				        		loanCol="loanInt9592";
				        	else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
				        		loanCol="loanInt9592";
				        	else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
				        		loanCol="loanInt9592";
			        	}
		        }
		        else if(report.getReportCode().equals("20"))
		        {
		        	loan_type=constantsBundle.getString("hba");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loan9591";
		        }
		        else if(report.getReportCode().equals("21"))
		        {
		        	loan_type=constantsBundle.getString("mca");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loan9592";
		        }
		        else if(report.getReportCode().equals("22"))
		        {
		        	loan_type=constantsBundle.getString("car");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loan9592";
		        }
		        else if(report.getReportCode().equals("23"))
		        {
		        	loan_type=constantsBundle.getString("scooter");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loan9592";
		        }
		        else if(report.getReportCode().equals("24"))
		        {
		        	loan_type=constantsBundle.getString("moped");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loan9592";
		        }
		        else if(report.getReportCode().equals("25"))
		        {
		        	loan_type=constantsBundle.getString("hba");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loanInt9591";
		        }
		        else if(report.getReportCode().equals("26"))
		        {
		        	loan_type=constantsBundle.getString("mca");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loanInt9592";
		        }
		        else if(report.getReportCode().equals("27"))
		        {
		        	loan_type=constantsBundle.getString("car");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loanInt9592";
		        }
		        else if(report.getReportCode().equals("28"))
		        {
		        	loan_type=constantsBundle.getString("scooter");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loanInt9592";
		        }
		        else if(report.getReportCode().equals("29"))
		        {
		        	loan_type=constantsBundle.getString("moped");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	loanCol="loanInt9592";
		        }
		        
		        //Ended
	           
		        
		        //Modified By Mrugesh
		        if(report.getReportCode().equals("4"))
		        {
			        if(report_type.equals("principal"))
			        {
			        	if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
			        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
			        	else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
			        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
			        	else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
			        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
			        	else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
			        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
			        	else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
			        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
	
			        }	
			        	else
			        	{
				        	if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
				        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
				        	else if(constantsBundle.getString("mca").equalsIgnoreCase(loan_type))
				        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
				        	else if(constantsBundle.getString("car").equalsIgnoreCase(loan_type))
				        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
				        	else if(constantsBundle.getString("scooter").equalsIgnoreCase(loan_type))
				        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
				        	else if(constantsBundle.getString("moped").equalsIgnoreCase(loan_type))
				        		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
			        	}
		        }
		        else if(report.getReportCode().equals("20"))
		        {
		        	loan_type=constantsBundle.getString("hba");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("21"))
		        {
		        	loan_type=constantsBundle.getString("mca");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("22"))
		        {
		        	loan_type=constantsBundle.getString("car");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("23"))
		        {
		        	loan_type=constantsBundle.getString("scooter");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER ADVANCE for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("24"))
		        {
		        	loan_type=constantsBundle.getString("moped");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }

		        else if(report.getReportCode().equals("25"))
		        {
		        	loan_type=constantsBundle.getString("hba");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of HBA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("26"))
		        {
		        	loan_type=constantsBundle.getString("mca");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MCA INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("27"))
		        {
		        	loan_type=constantsBundle.getString("car");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of CAR INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("28"))
		        {
		        	loan_type=constantsBundle.getString("scooter");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of SCOOTER INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        else if(report.getReportCode().equals("29"))
		        {
		        	loan_type=constantsBundle.getString("moped");
		        	//if(constantsBundle.getString("hba").equalsIgnoreCase(loan_type))
		        	report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of MOPED INTEREST for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
		        }
		        //Ended
		        cal = Calendar.getInstance();
	            sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	            
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH,month-1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            
	            java.util.Date startMonthDate = cal.getTime();
	            String startDate  = sdfObj.format(startMonthDate);
	            
	            String IsApproved = CheckIfNull(report.getParameterValue("Is Approved")); 
	            String approveDate="";
	            String approveEndDate="";
	            if(IsApproved.equals("no"))
	            {
	            
	            int yearBeforeApprove=year;
	            int monthBeforeApprove=month;
	            
	            if(month==1)
	            {	
	            	yearBeforeApprove--;	
	            	monthBeforeApprove=12;
	            }	
	            else
	            	monthBeforeApprove--;
	            	
	            cal.set(Calendar.YEAR, yearBeforeApprove);
	            cal.set(Calendar.MONTH,monthBeforeApprove-1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            

	            approveDate  = sdfObj.format(cal.getTime());
	            
	            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	            approveEndDate  = sdfObj.format(cal.getTime());

              }
	            
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH,month);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            
	            int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	            
	            cal.set(Calendar.DAY_OF_MONTH, totalDays);
	            
	            java.util.Date endMonthDate = cal.getTime();
	            
	            String endDate  = sdfObj.format(endMonthDate);
	            
	            
	            Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	            ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
	            
	            SessionFactory sessionFactory = serv.getSessionFactory();
	            Session session = sessionFactory.getCurrentSession();
	            
	            
	            lsb.append("select distinct emp.hrLoanAdvMst.loanAdvName,");
	            lsb.append(" emp.hrEisEmpMst.orgEmpMst.empPrefix||' '||emp.hrEisEmpMst.orgEmpMst.empFname||' '||emp.hrEisEmpMst.orgEmpMst.empMname||' '||emp.hrEisEmpMst.orgEmpMst.empLname,");
	            lsb.append("emp.loanAccountNo,");
	            lsb.append("emp.loanDate,");
	            //Modified By Mrugesh
		        if(report.getReportCode().equals("4"))
		        {
		            if(report_type.equals("principal"))
		            {
			            lsb.append("emp.loanPrinAmt,");
			            lsb.append("emp.loanPrinInstNo,");
			            lsb.append("emp.loanPrinEmiAmt,");
			            
			            if(IsApproved.equals("no"))
			            {
				            lsb.append("( select phst.totalRecoveredInst+1 from HrLoanEmpPrinRecoverHst phst ");
				            lsb.append(" where phst.updatedDate >='"+approveDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
				            		" and phst1.updatedDate >= '"+approveDate+"' and phst1.updatedDate <= '"+approveEndDate+"') and ");
				            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
				            
				            lsb.append(" phst.totalRecoveredAmt !=0 and ");
				            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
		
				            lsb.append("( select phst.totalRecoveredAmt from HrLoanEmpPrinRecoverHst phst ");
				            lsb.append(" where phst.updatedDate >='"+approveDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
				            		" and phst1.updatedDate >= '"+approveDate+"' and phst1.updatedDate <= '"+approveEndDate+"') and ");
				            lsb.append(" phst.updatedDate <='"+approveEndDate+"' and ");
				            
				            lsb.append(" phst.totalRecoveredAmt !=0 and ");
				            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
			            }
			            else
			            {	
			            lsb.append("( select phst.totalRecoveredInst from HrLoanEmpPrinRecoverHst phst ");
			            lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
			            		" and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
			            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
			            
			            lsb.append(" phst.totalRecoveredAmt !=0 and ");
			            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
	
			            lsb.append("( select phst.totalRecoveredAmt from HrLoanEmpPrinRecoverHst phst ");
			            lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
			            		" and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
			            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
			            
			            lsb.append(" phst.totalRecoveredAmt !=0 and ");
			            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
			            }
			            
			            lsb.append(" bill."+loanCol+" ");
			            
			            
			            /*lsb.append("phst.totalRecoveredInst,");
			            lsb.append("phst.totalRecoveredAmt,");
			            lsb.append("emp.loanPrinAmt-emp.loanPrinAmt");
			            lsb.append(" ,( ");
			            lsb.append(" select phst.totalRecoveredAmt -hstprevPrin.totalRecoveredAmt ");
			            lsb.append(" from HrLoanEmpPrinRecoverHst hstprevPrin  ");
			            lsb.append(" where hstprevPrin.id.trnCounter = phst.id.trnCounter-1 ");
			            lsb.append(" and hstprevPrin.hrLoanEmpDtls.empLoanId = phst.hrLoanEmpDtls.empLoanId ");
			            lsb.append(" ) ");*/
		            }
		            else if(report_type.equals("interest"))
		            {
		            	lsb.append(" emp.loanInterestAmt,");
			            lsb.append(" emp.loanIntInstNo,");
			            lsb.append(" emp.loanIntEmiAmt,");
			            
			            if(IsApproved.equals("no"))
			            {
				            lsb.append("( select ihst.totalRecoveredIntInst+1 from HrLoanEmpIntRecoverHst ihst ");
				            lsb.append(" where ihst.updatedDate >='"+approveDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
				            		" and ihst1.updatedDate >= '"+approveDate+"' and ihst1.updatedDate <= '"+approveEndDate+"') and  ");
				            lsb.append(" ihst.updatedDate <='"+approveEndDate+"' and ");
				            
				            lsb.append(" ihst.totalRecoveredInt !=0 and ");
				            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
				            
				            lsb.append("( select ihst.totalRecoveredInt  from HrLoanEmpIntRecoverHst ihst ");
				            lsb.append(" where ihst.updatedDate >='"+approveDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
				            		" and ihst1.updatedDate >= '"+approveDate+"' and ihst1.updatedDate <= '"+approveEndDate+"') and  ");
				            lsb.append(" ihst.updatedDate <='"+approveEndDate+"' and ");
				            
				            lsb.append(" ihst.totalRecoveredInt !=0 and ");
				            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
			            }
			            else	
			            {	
			            lsb.append("( select ihst.totalRecoveredIntInst from HrLoanEmpIntRecoverHst ihst ");
			            lsb.append(" where ihst.updatedDate >='"+startDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
			            		" and ihst1.updatedDate >= '"+startDate+"' and ihst1.updatedDate <= '"+endDate+"') and  ");
			            lsb.append(" ihst.updatedDate <='"+endDate+"' and ");
			            
			            lsb.append(" ihst.totalRecoveredInt !=0 and ");
			            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
			            
			            lsb.append("( select ihst.totalRecoveredInt from HrLoanEmpIntRecoverHst ihst ");
			            lsb.append(" where ihst.updatedDate >='"+startDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
			            		" and ihst1.updatedDate >= '"+startDate+"' and ihst1.updatedDate <= '"+endDate+"') and  ");
			            lsb.append(" ihst.updatedDate <='"+endDate+"' and ");
			            
			            lsb.append(" ihst.totalRecoveredInt !=0 and ");
			            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
			            }
			            lsb.append(" bill."+loanCol+" ");
			            
			            /*lsb.append("ihst.totalRecoveredIntInst,");
			            lsb.append("ihst.totalRecoveredInt,");
			            lsb.append("emp.loanInterestAmt-ihst.totalRecoveredInt");
			            lsb.append(" ,( ");
			            lsb.append(" select ihst.totalRecoveredInt -hstprevInt.totalRecoveredInt ");
			            lsb.append(" from HrLoanEmpIntRecoverHst hstprevInt  ");
			            lsb.append(" where hstprevInt.id.trnCounter = ihst.id.trnCounter-1 ");
			            lsb.append(" and hstprevInt.hrLoanEmpDtls.empLoanId = ihst.hrLoanEmpDtls.empLoanId ");
			            lsb.append(" ) ");*/
		            }  
		        }
		        else if(report.getReportCode().equals("20")|| report.getReportCode().equals("21") || report.getReportCode().equals("22") || report.getReportCode().equals("23") || report.getReportCode().equals("24"))
		        {
		        	report_type=constantsBundle.getString("principal");
		        	
		        	lsb.append("emp.loanPrinAmt,");
		            lsb.append("emp.loanPrinInstNo,");
		            lsb.append("emp.loanPrinEmiAmt,");
		            
		            if(IsApproved.equals("no"))
		            {
			            lsb.append("( select phst.totalRecoveredInst+1 from HrLoanEmpPrinRecoverHst phst ");
			            lsb.append(" where phst.updatedDate >='"+approveDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
			            		" and phst1.updatedDate >= '"+approveDate+"' and phst1.updatedDate <= '"+approveEndDate+"') and ");
			            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
			            
			            lsb.append(" phst.totalRecoveredAmt !=0 and ");
			            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
	
			            lsb.append("( select phst.totalRecoveredAmt from HrLoanEmpPrinRecoverHst phst ");
			            lsb.append(" where phst.updatedDate >='"+approveDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
			            		" and phst1.updatedDate >= '"+approveDate+"' and phst1.updatedDate <= '"+approveEndDate+"') and ");
			            lsb.append(" phst.updatedDate <='"+approveEndDate+"' and ");
			            
			            lsb.append(" phst.totalRecoveredAmt !=0 and ");
			            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
		            }
		            else
		            {	
		            lsb.append("( select phst.totalRecoveredInst from HrLoanEmpPrinRecoverHst phst ");
		            lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
		            		" and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
		            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
		            
		            lsb.append(" phst.totalRecoveredAmt !=0 and ");
		            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");

		            lsb.append("( select phst.totalRecoveredAmt from HrLoanEmpPrinRecoverHst phst ");
		            lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
		            		" and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
		            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
		            
		            lsb.append(" phst.totalRecoveredAmt !=0 and ");
		            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
		            }
		            
		            lsb.append(" bill."+loanCol+" ");

		        }
		        else if(report.getReportCode().equals("25")|| report.getReportCode().equals("26")|| report.getReportCode().equals("27")|| report.getReportCode().equals("28")|| report.getReportCode().equals("29"))
		        {
		        	report_type=constantsBundle.getString("interest");
		        	
		        	lsb.append(" emp.loanInterestAmt,");
		            lsb.append(" emp.loanIntInstNo,");
		            lsb.append(" emp.loanIntEmiAmt,");
		            
		            if(IsApproved.equals("no"))
		            {
			            lsb.append("( select ihst.totalRecoveredIntInst+1 from HrLoanEmpIntRecoverHst ihst ");
			            lsb.append(" where ihst.updatedDate >='"+approveDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
			            		" and ihst1.updatedDate >= '"+approveDate+"' and ihst1.updatedDate <= '"+approveEndDate+"') and  ");
			            lsb.append(" ihst.updatedDate <='"+approveEndDate+"' and ");
			            
			            lsb.append(" ihst.totalRecoveredInt !=0 and ");
			            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
			            
			            lsb.append("( select ihst.totalRecoveredInt  from HrLoanEmpIntRecoverHst ihst ");
			            lsb.append(" where ihst.updatedDate >='"+approveDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
			            		" and ihst1.updatedDate >= '"+approveDate+"' and ihst1.updatedDate <= '"+approveEndDate+"') and  ");
			            lsb.append(" ihst.updatedDate <='"+approveEndDate+"' and ");
			            
			            lsb.append(" ihst.totalRecoveredInt !=0 and ");
			            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
		            }
		            else	
		            {	
		            lsb.append("( select ihst.totalRecoveredIntInst from HrLoanEmpIntRecoverHst ihst ");
		            lsb.append(" where ihst.updatedDate >='"+startDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
		            		" and ihst1.updatedDate >= '"+startDate+"' and ihst1.updatedDate <= '"+endDate+"') and  ");
		            lsb.append(" ihst.updatedDate <='"+endDate+"' and ");
		            
		            lsb.append(" ihst.totalRecoveredInt !=0 and ");
		            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
		            
		            lsb.append("( select ihst.totalRecoveredInt from HrLoanEmpIntRecoverHst ihst ");
		            lsb.append(" where ihst.updatedDate >='"+startDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
		            		" and ihst1.updatedDate >= '"+startDate+"' and ihst1.updatedDate <= '"+endDate+"') and  ");
		            lsb.append(" ihst.updatedDate <='"+endDate+"' and ");
		            
		            lsb.append(" ihst.totalRecoveredInt !=0 and ");
		            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId ), ");
		            }

		            lsb.append(" bill."+loanCol+" ");
		        }
	            lsb.append(" from ");
	            lsb.append(" HrPayPaybill bill, ");
	            lsb.append(" HrLoanEmpDtls emp, ");
	            lsb.append(" HrEisOtherDtls dtl, ");
	            lsb.append(" HrLoanEmpPrinRecoverDtls pdtls, ");
	            lsb.append(" HrLoanEmpIntRecoverDtls idtls, ");
	            
	            lsb.append(" OrgUserpostRlt           USRPST, ");
	            lsb.append(" HrPayOrderHeadMpg ORDHD, ");
	            lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");
	            lsb.append(" OrgPostDetailsRlt pst ");
	            
	            /*if(report_type.equals("principal"))
	            {
	            	lsb.append("HrLoanEmpPrinRecoverHst phst");
	            }
	            else if(report_type.equals("interest"))
	            {
	            	lsb.append(" HrLoanEmpIntRecoverHst ihst ");
	            }*/  
	            
	            lsb.append(" where  bill.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and ");
	            lsb.append(" emp.hrEisEmpMst.empId = dtl.hrEisEmpMst.empId and ");
	            lsb.append(" pdtls.hrLoanEmpDtls.empLoanId =  emp.empLoanId and ");
	            lsb.append(" idtls.hrLoanEmpDtls.empLoanId =  emp.empLoanId and ");
	            
	            
	            lsb.append(" ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where bill.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )");
	            lsb.append(" and ORDPST.orderHeadId = ORDHD.orderHeadId ");
	            lsb.append(" and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
	            lsb.append(" and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
	            lsb.append(" and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and ");
	            lsb.append("   (USRPST.endDate is null or ");
	            lsb.append("   (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'   )) ");
	            lsb.append("   and bill.approveFlag in (0,1) and ");

	            lsb.append("  bill."+loanCol+"!=0 and ");            
	            lsb.append("  emp.empLoanId in (select max(l.empLoanId) from HrLoanEmpDtls l where l.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and l.hrLoanAdvMst.loanAdvId = emp.hrLoanAdvMst.loanAdvId and l.loanDate<='"+startDate+"' )  and ");            
	            
	            if(report_type.equals("principal"))
	            {
	             	/*lsb.append(" pdtls.totalRecoveredAmt != 0 and ");// for checking recoverd principal if adv emi started or not
		            //checked for whether the emi is the last one or not by two statement below
		            lsb.append("  (emp.loanPrinAmt >= pdtls.totalRecoveredAmt or ");//for recover principal should be less than total adv
		            lsb.append("(emp.loanPrinAmt =0 or (emp.loanPrinAmt - pdtls.totalRecoveredAmt) != 0)) and ");
		            //for checking whether emi is last one or not
		            lsb.append(" ((emp.loanInterestAmt - idtls.totalRecoveredInt)!=0 or ");// outstanding amount not = 0
		            lsb.append(" idtls.totalRecoveredInt=0) and  ");
		            
		            /*lsb.append(" phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId" +
		            		" and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
		            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
		            
		            lsb.append(" phst.totalRecoveredAmt !=0 and ");
		            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId and ");*/
		        }
	            else if(report_type.equals("interest"))
	            {
	            	/*//interest recovery will be done only after principal recovery
	            	lsb.append(" (emp.loanPrinAmt - pdtls.totalRecoveredAmt)=0 and ");//so if principal outstanding is 0 then recovery will be started
		            lsb.append(" idtls.totalRecoveredInt!=0 and  "); //if principal outstanding is 0 then whether the principal and interest recovery is started or not
		            lsb.append(" idtls.totalRecoveredInt<emp.loanInterestAmt and ");//if yes then interest recovered should be less than loan interest amount and
		            lsb.append(" (emp.loanInterestAmt=0 or (emp.loanInterestAmt - idtls.totalRecoveredInt)!=0) and ");// interest outstanding should not be 0
		            */
		            /*lsb.append(" ihst.updatedDate >='"+startDate+"' and ihst.id.trnCounter = (select max(ihst1.id.trnCounter) from HrLoanEmpIntRecoverHst ihst1 where ihst1.id.intRecoverId=idtls.intRecoverId" +
		            		" and ihst1.updatedDate >= '"+startDate+"' and ihst1.updatedDate <= '"+endDate+"') and  ");
		            lsb.append(" ihst.updatedDate <='"+endDate+"' and ");
		            
		            lsb.append(" ihst.totalRecoveredInt !=0 and ");
		            lsb.append(" emp.empLoanId = ihst.hrLoanEmpDtls.empLoanId and ");*/
		        }  
	            lsb.append("   dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.cmnLanguageMst.langId = 1 and ");
	            lsb.append(" emp.hrLoanAdvMst.cmnLanguageMst.langId = 1 and ");
	            lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+loan_type+") ");
	            
	            if(!empid.equals("")&&!empid.equals("-1"))            	
	            	lsb.append(" and emp.hrEisEmpMst.empId = '"+empid+"'");
	            
	            if(!Grade.equals("")&&!Grade.equals("-1"))          	
	            	lsb.append(" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId = '"+Grade+"'");
	            
	            if(!Scale.equals("")&&!Scale.equals("-1"))            	
	            	lsb.append(" and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"+Scale+"'");
	            
	            if(!Designation.equals("")&&!Designation.equals("-1"))            	
	            	lsb.append(" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'");
	            
	            lsb.append(" and bill.month ='"+month+"'");
	            
	            lsb.append(" and bill.year = '"+year+"'");
	            
              if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
              {
              	lsb.append(" and ORDHD.subheadId  = '"+subHeadId+"'");
              }
	            
	            if(!department.equals("")&&!department.equals("-1"))
	            	lsb.append(" and pst.cmnLocationMst.locId="+department+"  ");
	            if(isBillDefined&&!BillNo.equals(""))
              {
             	 
	            	lsb.append("  and  bill.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "); 
	            	lsb.append("   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pst.orgPostMst.postId = bill.orgPostMst.postId  "); 
              }
              else
              {
		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
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
		        }
              }
          	lsb.append("  order by emp.loanAccountNo ");

          	String strQuery = lsb.toString(); 
        	
        	logger.info("the query is "+strQuery);
        	Query query = session.createQuery(strQuery);
	            
	            int cnt=1;
	            long empId=0;
        	
        	ArrayList rowList=new ArrayList();
        	long emiTotal=0;
        	
        	
        	for(Iterator it=query.iterate();it.hasNext();)
			{
			    Object[] row = (Object[]) it.next();
				long zero = 0;
			    String loanName 		= (String)(row[0]!=null?row[0]:"");//lmst.loanAdvName
				String empName 			= (String)(row[1]!=null?row[1]:"");//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
				String loanAccountNo 	= (String)(row[2]!=null?row[2]:"");//loanAccountNo
				
				//String loanMonth 		= (String)(row[3]!=null?row[3]:"");//Month
				String loanMonth = "";
				if(row[3]!=null)
				{
					sdfObj = new SimpleDateFormat("MMMM-yyyy");
					Timestamp loanTime = (Timestamp)row[3];
					long time = loanTime.getTime();
		            Date loanDate = new Date();
		            loanDate.setTime(time);
					 loanMonth = sdfObj.format(loanDate);
				}
				
				Long loanAmt 			= (Long)(row[4]!=null?row[4]:zero);//loanPrinAmt/loanInterestAmt
				Long loanInstNo 		= (Long)(row[5]!=null?row[5]:zero);//loanPrinInstNo/loanIntInstNo
				//Long loanEmiAmt 		= (Long)(row[10]!=null?row[10]:(row[6]!=null?row[6]:zero));//loanPrinEmiAmt/loanIntEmiAmt

				Long loanEmiAmt 		= Math.round((Double)(row[9]!=null?row[9]:(zero)));//loanPrinEmiAmt/loanIntEmiAmt
				
				
				Long totalRecoveredInst = (Long)(row[7]!=null?row[7]:zero);//totalRecoveredInst/totalRecoveredIntInst
				Long totalRecoveredAmt 	= (Long)(row[8]!=null?row[8]:zero);//totalRecoveredAmt/totalRecoveredInt
				
				if(IsApproved.equals("no"))
				totalRecoveredAmt+=loanEmiAmt;
				
				Long balOutStanding 	= new Long(0);//(Long)(row[9]!=null?row[9]:zero);//emp.loanPrinAmt-pdtls.totalRecoveredAmt/emp.loanInterestAmt-idtls.totalRecoveredInt
				
				balOutStanding=loanAmt-totalRecoveredAmt;
				  rowList = new ArrayList();
      			/*if( cnt%20==0)
    			{
      				rowList.add(new PageBreak());
      				rowList.add("Data");
    			}*/
    			//else
    			{
				  rowList.add(new Integer(cnt));
		              rowList.add(empName);
		              rowList.add(loanAccountNo);
		              rowList.add("");
		              rowList.add(loanAmt);
		              rowList.add(loanInstNo);
		              rowList.add(loanEmiAmt);
		              rowList.add(totalRecoveredInst);
		              rowList.add(totalRecoveredAmt);
		              rowList.add(balOutStanding);
		              emiTotal+=loanEmiAmt;
    			}
	              	  DataList.add(rowList); 
	              	  cnt++;
	              	  
	    	            if( cnt%finalpagesize==0)
	        			{
	      	            	rowList=new ArrayList();
	      	            	rowList.add(new PageBreak());
	          				rowList.add("Data");
	        	            DataList.add(rowList); 
	        			}

	              	  
			} 
	              
			      rowList = new ArrayList();
				  
				  rowList.add("");
	              rowList.add("");
	              rowList.add("");
	              rowList.add("");
	              rowList.add("");
	              StyledData dataStyle1 = new StyledData();
	              dataStyle1.setStyles(boldStyleVO);
	              dataStyle1.setData("TOTAL");                  
	              rowList.add(dataStyle1);
	             // String emiTotalInWords=ConvertNumbersToWord.convert(Math.round(emiTotal));
	              dataStyle1 = new StyledData();
	              dataStyle1.setStyles(boldStyleVO);
	              dataStyle1.setData(emiTotal/*+System.getProperty("line.separator")+emiTotalInWords*/);                  
	              rowList.add(dataStyle1);	              
	              rowList.add("");
	              rowList.add("");
	              rowList.add("");
	              DataList.add(rowList); 
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
	                dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(emiTotal))+" only.");
	                dataStyle2.setColspan(10);
	                row1.add(dataStyle2);
	                
	                for(int c=0;c<10;c++)
	                	row1.add("");
	    	        
		            
		            DataList.add(row1);
		            ReportColumnVO[] rptCol = report.getReportColumns();                  
		            SortingHelper Helper = new SortingHelper(DataList);
		            
	              report.setReportColumns(rptCol);
	              report.initializeDynamicTreeModel();
	              report.initializeTreeModel(); 

 
              return DataList;
	    } 
          //end by manoj for loan and advance report
//        by manoj for Festival and FoodGrain advance report
          if(report.getReportCode().equals("5") || report.getReportCode().equals("18") || report.getReportCode().equals("19"))
          {      

	  			           
	  			StringBuffer lsb = new StringBuffer(  );      

	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
	            String Designation="";
	            String Grade="";
	            String Scale="";
	            int month=0;
	            int year=0;
	            String loan_type="";
	            String department="";
	            
	            String loanCol="adv7057"; // for fest
	            
	            department=CheckIfNull(report.getParameterValue( "Department" ));
                //added by samir joshi for bill no wise report
	            if(department.equals("")||department.equals("-1"))
	            	department=	locationId+"";	
	            
	            String BillNoinner="";//GAD specific
	            BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
	 	        StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
		        int l=0;
		        String subheadCode="";
		        String classIds="";
		        String desgnIds="";
		        String festivalVal = constantsBundle.getString("festival");
		        String foodVal = constantsBundle.getString("foodGrain");
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

	    		  ArrayList d = new ArrayList();
	    		  ArrayList r = new ArrayList();
	    		  
	    		  r= new ArrayList();
	    		  r.add("To be Filled in by Drawing Officer");
	    		  r.add("To be filled in by Treasury Officer");
	    		  d.add(r);
	    		  r= new ArrayList();
	    		  r.add("District         :7  1");
	    		  r.add("Voucher No       :");
	    		  d.add(r);
	    		  r= new ArrayList();
	    		  r.add("Drawing Officer  : 012");
	    		  r.add("Month & Year     :");
	    		  d.add(r);
	    		  r= new ArrayList();
	    		    		  r.add("                      ");
	    		  r.add("Major Head       :");
	    		  d.add(r);
	    		  
	    		  TabularData td  = new TabularData(d);
	    		  td.addStyle(IReportConstants.ADDL_HEADER_LOCATION, IReportConstants.VALUE_ADDL_HEADER_LOCATION_BELOW);
	    		  td.addStyle(IReportConstants.BORDER, "No"); 
	    		  report.setAdditionalHeader(td);
	            Grade=CheckIfNull(report.getParameterValue( "Grade" ));
	            Scale=CheckIfNull(report.getParameterValue( "Scale" ));
	            Designation=CheckIfNull(report.getParameterValue( "Designation" ));
	            if(!CheckIfNull(report.getParameterValue( "Month" )).equals(""))
	            {
	            	month=Integer.parseInt(CheckIfNull(report.getParameterValue( "Month" )));
	            }
	            if(!CheckIfNull(report.getParameterValue( "Year" )).equals(""))
	            {
	            	year=Integer.parseInt(CheckIfNull(report.getParameterValue( "Year" )));
	            }
	            loan_type=CheckIfNull(report.getParameterValue( "AdvanceType" ));
	            
		        logger.info(constantsBundle.getString("Food_Advance")+"  loan_type      "+loan_type);
	            
	            if(constantsBundle.getString("festival").equalsIgnoreCase(loan_type))
		        		loanCol="adv7057";
		        	else if(constantsBundle.getString("foodGrain").equalsIgnoreCase(loan_type))
		        		loanCol="adv7058";
	            
	            Calendar cal = Calendar.getInstance();
	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	            SimpleDateFormat sdfObj1 = new SimpleDateFormat("MMM");
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH,month-1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            
	            java.util.Date startMonthDate = cal.getTime();
	            String startDate  = sdfObj.format(startMonthDate);
	            String Month  = sdfObj1.format(startMonthDate);
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH,month-1);
	            cal.set(Calendar.DAY_OF_MONTH, 1);
	            
	            int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	            
	            cal.set(Calendar.DAY_OF_MONTH, totalDays);
	            
	            java.util.Date endMonthDate = cal.getTime();
	            
	            String endDate  = sdfObj.format(endMonthDate);
	            
	            Map  requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	            ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
	            
	            SessionFactory sessionFactory = serv.getSessionFactory();
	            Session session = sessionFactory.getCurrentSession();
	            
	            
			    String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
		    	//(String)requestAttributes.get("demandNo");
		    String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
		    String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
		    String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
		    String subHead = CheckIfNull(report.getParameterValue("subHead"));
		    //String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
		    String subheadflag=subHead;
		    logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
	        report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of recovery of Food/Festival Advance  for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  

		    String subHeadId = "";
		    if(subHead!=null && !subHead.equals("")&& !subHead.equals("-1"))
		    {
			    
		        StringTokenizer st=new StringTokenizer(subHead,"~");
		        int i=0;
		        while(st.hasMoreTokens())
		        {
		        	if(i==0)
		        	demandNo=st.nextToken();
		        	else if(i==1)
		        	mjrHead=st.nextToken();
		        	else if(i==2)
		        	subMjrHead=st.nextToken();
		        	else if(i==3)
		        	mnrHead=st.nextToken();
		        	else
		        	subHead=st.nextToken();	
		         i++;
		        } 
		    	if(subheadflag.indexOf("~")<=0)
		    	{
				   demandNo = CheckIfNull(report.getParameterValue("demandNo")); 
			       mjrHead = CheckIfNull(report.getParameterValue("mjrHead"));
			       subMjrHead =  CheckIfNull(report.getParameterValue("subMjrHead"));
			       mnrHead = CheckIfNull(report.getParameterValue("mnrHead"));
			       logger.info("  sdfhdfgthfr Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
		    	}
		    	logger.info("        sdgdfhgfHead Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
		    	GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(SgvaBudsubhdMst.class);

		    	genDAO.setSessionFactory(serv.getSessionFactory());
		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
	            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
	            long langId = cmnLanguageMst.getLangId();
			    PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	            SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(startMonthDate, langId);

	            String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
			    Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
			    List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);

			    logger.info("*******************finYr*************"+finYrMst.getFinYearId());
			    SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
			    subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
			    logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
		        }
	  	 		
	            
	            lsb.append("select distinct emp.hrLoanAdvMst.loanAdvName,");
	            lsb.append(" emp.hrEisEmpMst.orgEmpMst.empPrefix||' '||emp.hrEisEmpMst.orgEmpMst.empFname||' '||emp.hrEisEmpMst.orgEmpMst.empMname||' '||emp.hrEisEmpMst.orgEmpMst.empLname,");
	            lsb.append("emp.loanAccountNo,");
	            lsb.append("emp.loanDate,");
	            
	            lsb.append("emp.loanPrinAmt,");
	            lsb.append("emp.loanPrinInstNo,");
	            lsb.append("emp.loanPrinEmiAmt,");
	            lsb.append("(select phst.totalRecoveredInst from ");
	            lsb.append(" HrLoanEmpPrinRecoverHst phst ");
	            
	            lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId 	 and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
	            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
	            lsb.append(" phst.totalRecoveredAmt !=0 and ");
	            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
	            
	            lsb.append(" ( select phst.totalRecoveredAmt from ");
	            lsb.append(" HrLoanEmpPrinRecoverHst phst ");
	            lsb.append(" where phst.updatedDate >='"+startDate+"'  and phst.id.trnCounter = (select max(phst1.id.trnCounter) from HrLoanEmpPrinRecoverHst phst1 where phst1.id.prinRecoverId=pdtls.prinRecoverId 	 and phst1.updatedDate >= '"+startDate+"' and phst1.updatedDate <= '"+endDate+"') and ");
	            lsb.append(" phst.updatedDate <='"+endDate+"' and ");
	            lsb.append(" phst.totalRecoveredAmt !=0 and ");
	            lsb.append(" emp.empLoanId = phst.hrLoanEmpDtls.empLoanId ), ");
	            lsb.append(" bill."+loanCol+" ");
	            /*lsb.append("emp.loanPrinAmt-phst.totalRecoveredAmt ");
	            
	            lsb.append(" ,( ");
	            lsb.append(" select phst.totalRecoveredAmt -hstprevPrin.totalRecoveredAmt ");
	            lsb.append(" from HrLoanEmpPrinRecoverHst hstprevPrin  ");
	            lsb.append(" where hstprevPrin.id.trnCounter = phst.id.trnCounter-1 ");
	            lsb.append(" and hstprevPrin.hrLoanEmpDtls.empLoanId = phst.hrLoanEmpDtls.empLoanId ");
	            lsb.append(" ) ");*/
	            
	            lsb.append(" from ");
	            lsb.append(" HrPayPaybill bill, ");
	            lsb.append(" HrLoanEmpDtls emp, ");
	            lsb.append(" HrEisOtherDtls dtl, ");
	            lsb.append(" HrLoanEmpPrinRecoverDtls pdtls, ");
	            
	            lsb.append(" OrgUserpostRlt           USRPST, ");
	            lsb.append(" HrPayOrderHeadMpg ORDHD, ");
	            lsb.append(" HrPayOrderHeadPostMpg ORDPST, ");
	            lsb.append(" OrgPostDetailsRlt pst ");
	            
	            	            
	            lsb.append(" where bill.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and ");

	            lsb.append("  bill."+loanCol+"!=0 and ");            
	            lsb.append("  emp.empLoanId in (select max(l.empLoanId) from HrLoanEmpDtls l where l.hrEisEmpMst.empId = emp.hrEisEmpMst.empId and l.hrLoanAdvMst.loanAdvId = emp.hrLoanAdvMst.loanAdvId  and l.loanDate<='"+startDate+"' ) and ");            
	            
	            lsb.append(" ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where bill.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )");
	            lsb.append(" and ORDPST.orderHeadId = ORDHD.orderHeadId ");
	            lsb.append(" and USRPST.orgPostMstByPostId.postId = ORDPST.postId ");
	            lsb.append(" and USRPST.orgUserMst.userId = bill.hrEisEmpMst.orgEmpMst.orgUserMst.userId ");
	            lsb.append(" and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId and ");
	            lsb.append("   (USRPST.endDate is null or ");
	            lsb.append("   (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'    )) ");
	            lsb.append("   and bill.approveFlag in (0,1) and ");	            
	            lsb.append(" emp.hrEisEmpMst.empId = dtl.hrEisEmpMst.empId and ");
	            lsb.append(" pdtls.hrLoanEmpDtls.empLoanId =  emp.empLoanId and ");
	            
	            /*lsb.append(" pdtls.totalRecoveredAmt != 0 and ");// for checking recoverd principal if adv emi started or not
	            lsb.append("  (emp.loanPrinAmt >= pdtls.totalRecoveredAmt or ");//for recover principal should be less than total adv
	            lsb.append("(emp.loanPrinAmt - pdtls.totalRecoveredAmt) != 0) and ");*/
	            
	            
		        
	            lsb.append("   dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.cmnLanguageMst.langId = 1 and ");
	            lsb.append(" emp.hrLoanAdvMst.cmnLanguageMst.langId = 1 and ");
	            //modified by mrugesh
	            //lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+loan_type+") ");

	            if(loan_type==null || loan_type=="")
	            {
	            	//logger.info("inside if*****");
	            	
	            	if(report.getReportCode().equals("18"))
	            		lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+foodVal+")");
	            	if(report.getReportCode().equals("19"))
	            		lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+festivalVal+")");
	            }
	            else
	            {
	            	//logger.info("inside else*****");
	            	lsb.append(" emp.hrLoanAdvMst.loanAdvId in ( "+loan_type+") ");
	            }
	            //ended
	            
	            if(!empid.equals("")&&!empid.equals("-1"))            	
	            	lsb.append(" and emp.hrEisEmpMst.empId = '"+empid+"'");
	            
	            
	            if(!Grade.equals("")&&!Grade.equals("-1"))          	
	            	lsb.append(" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId = '"+Grade+"'");
	            
	            if(!Scale.equals("")&&!Scale.equals("-1"))            	
	            	lsb.append(" and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"+Scale+"'");
	            
	            if(!Designation.equals("")&&!Designation.equals("-1"))            	
	            	lsb.append(" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'");
	            
	            lsb.append(" and bill.month ='"+month+"'");
	            
	            lsb.append(" and bill.year = '"+year+"'");
	            
                if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
                {
                	lsb.append(" and ORDHD.subheadId  = '"+subHeadId+"'");
                }
	            
	            if(!department.equals("")&&!department.equals("-1"))
	            	lsb.append(" and pst.cmnLocationMst.locId="+department+"  ");
	            if(isBillDefined&&!BillNo.equals(""))
                {
               	 
	            	lsb.append("  and  bill.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "); 
	            	lsb.append("   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pst.orgPostMst.postId = bill.orgPostMst.postId  "); 
                }
                else
                {
		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
	            {
		        	lsb.append(" and   ORDHD.subheadId ="+subheadCode+" ");
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
            	lsb.append("  order by emp.loanAccountNo ");
	            
	            
	            String strQuery = lsb.toString(); 
          	
          	logger.info("the query is "+strQuery);
          	Query query = session.createQuery(strQuery);
	            
	            int cnt=1;
	            long empId=0;
          	
          	ArrayList rowList=new ArrayList();
          	int emiTotal=0;
          	for(Iterator it=query.iterate();it.hasNext();)
  			{
  			    
          		Object[] row = (Object[]) it.next();
          		long zero = 0;
  			    String loanName 		= (String)(row[0]!=null?row[0]:"");//lmst.loanAdvName
  				String empName 			= (String)(row[1]!=null?row[1]:"");//orgEmp.empPrefix||' '||orgEmp.empFname||' '||orgEmp.empMname||' '||orgEmp.empLname
  				String loanAccountNo 	= (String)(row[2]!=null?row[2]:"");//loanAccountNo
  				//String loanMonth 		= (String)(row[3]!=null?row[3]:"");//Month
  				if(row[3]!=null)
  				{
  					sdfObj = new SimpleDateFormat("MMMM-yyyy");
  					Timestamp loanTime = (Timestamp)row[3];
  					long time = loanTime.getTime();
  		            Date loanDate = new Date();
  		            loanDate.setTime(time);
  					String loanMonth = sdfObj.format(loanDate);
  				}
  				
  				Long loanAmt 			= (Long)(row[4]!=null?row[4]:zero);//loanPrinAmt
  				Long loanInstNo 		= (Long)(row[5]!=null?row[5]:zero);//loanPrinInstNo
  				Long loanEmiAmt 		= Math.round((Double)(row[9]!=null?row[9]:(zero)));//loanPrinEmiAmt
  				Long totalRecoveredInst = (Long)(row[7]!=null?row[7]:zero);//totalRecoveredInst
  				Long totalRecoveredAmt 	= (Long)(row[8]!=null?row[8]:zero);//totalRecoveredAmt
  				Long balOutStanding 	= new Long(0);//(Long)(row[9]!=null?row[9]:zero);//emp.loanPrinAmt-pdtls.totalRecoveredAmt
  				balOutStanding=loanAmt-totalRecoveredAmt;
  	            rowList = new ArrayList();
      			/*if( cnt%20==0)
    			{
      				rowList.add(new PageBreak());
      				rowList.add("Data");
    			}*/
    			//else
    			{
    			  rowList.add(new Integer(cnt));
  	              rowList.add(empName);
  	              rowList.add(loanEmiAmt);
  	              rowList.add(totalRecoveredInst+"/"+loanInstNo);
  	              rowList.add(loanAmt);
  	              rowList.add(totalRecoveredAmt);
  	              rowList.add(balOutStanding);
  	              emiTotal+=loanEmiAmt;
    			}
  	              DataList.add(rowList); 
  	              cnt++;
  	              
  	            if( cnt%finalpagesize==0)
    			{
  	            	rowList=new ArrayList();
  	            	rowList.add(new PageBreak());
      				rowList.add("Data");
    	            DataList.add(rowList); 
    			}
  	              
  			} 
	            	
			      rowList = new ArrayList();
			      rowList.add("");
	              StyledData dataStyle1 = new StyledData();
	              dataStyle1.setStyles(boldStyleVO);
	              dataStyle1.setData("TOTAL");                  
	              rowList.add(dataStyle1);
	             //  String emiTotalInWords=ConvertNumbersToWord.convert(Math.round(emiTotal));
	              dataStyle1 = new StyledData();
	              dataStyle1.setStyles(boldStyleVO);
	              
	              dataStyle1.setData(emiTotal/*+System.getProperty("line.separator")+emiTotalInWords*/);                  
	              rowList.add(dataStyle1);	              

				  
	              rowList.add("");
	              rowList.add("");
	              rowList.add("");
	              rowList.add("");
	              
	              DataList.add(rowList); 
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
	                dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(emiTotal))+" only.");
	                dataStyle2.setColspan(7);
	                row1.add(dataStyle2);
	                
	                for(int c=0;c<6;c++)
	                	row1.add("");
	    	        
		            
		            DataList.add(row1);
          	
          	
	                ReportColumnVO[] rptCol = report.getReportColumns();                  
		            SortingHelper Helper = new SortingHelper(DataList);
		            
	              report.setReportColumns(rptCol);
	              report.initializeDynamicTreeModel();
	              report.initializeTreeModel(); 

          
                return DataList;
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
//        by manoj for Non Govt Deduction report
	        
	 
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





 


