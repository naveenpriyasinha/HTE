package com.tcs.sgv.eis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class payrollReporPFfDAO extends DefaultReportDataFinder 
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
	              if(report.getReportCode().equals("11"))
	              {


                      // Added by Akshay
                	  String fname = CheckIfNull(report.getParameterValue("FName"));
                	  String lname = CheckIfNull(report.getParameterValue("LName"));
                      // Ended by Akshay
                	  long GradeCode4=Long.parseLong(constantsBundle.getString("GradeCode4"));

                        // hardcoded
            	        long GradeCodeiii=Long.parseLong(constantsBundle.getString("GradeCode3"));
            	        long GPF_ADV=Long.parseLong(constantsBundle.getString("GPF_ADV"));
              	        long GradeCodeiv=Long.parseLong(constantsBundle.getString("GradeCode4"));
                        //added by samir joshi for bill no wise report
        	           
        	            
        	            String BillNoinner="";//GAD specific
        	            BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
        	            String accountType=CheckIfNull(report.getParameterValue( "Account Type" ));
        	            
        	            
        	            
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
        	  			StringBuffer lsb = new StringBuffer(  );      

        	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
        	            String Department="";
        	            String Grade="";
        	            String Scale="";
        	            String Designation="";
        	            String month="";
        	            String year="";
        	            String GroupBy="";
                        String ClassType="";
                        ClassType=CheckIfNull(report.getParameterValue( "Class Type" ));
        	            GroupBy=CheckIfNull(report.getParameterValue( "Group By" ));
        	            Department=CheckIfNull(report.getParameterValue( "Department" ));
        	            if(Department.equals("")||Department.equals("-1"))
        	            	Department=	locationId+"";	
        	            //for report footer
        	            ReportAttributeVO ravo = new ReportAttributeVO();
        	            
        	    		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
        	    		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
        	    		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);
        	    		String DeptName=locationNameincaps;
        	    		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

                        //add the attribute to the report instance
        	    		//report.addReportAttributeItem(ravo);
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
        	            
        	          if(ClassType.equals("IAS"))
        	        	Grade=	constantsBundle.getString("AISGradeCode");  
                        
        			    String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
        		    	//(String)requestAttributes.get("demandNo");
        		    String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
        		    String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
        		    String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
        		    String subHead = CheckIfNull(report.getParameterValue("subHead"));
        		    //String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
        		    String subheadflag=subHead;
        		    logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);

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
        	            Calendar cal = Calendar.getInstance();
        	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
        	            
        	            cal.set(Calendar.YEAR, Integer.parseInt(year));
        	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
        	            cal.set(Calendar.DAY_OF_MONTH, 1);
        	            
        	            java.util.Date startMonthDate = cal.getTime();
        	            String startDate  = sdfObj.format(startMonthDate);
                        Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
        	            ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
        		    	genDAO.setSessionFactory(serv.getSessionFactory());
        		        CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
        	            CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
        	            long langId = cmnLanguageMst.getLangId();
        			    PayBillDAOImpl payDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
        	            SgvcFinYearMst finYrMst = payDAO.getFinYrInfo(startMonthDate, langId);

        	            String cols[] = {"demandCode","budmjrhdCode","budsubmjrhdCode","budminhdCode","budsubhdCode","langId","finYrId"};//,"finYrId"
        			    Object vals[] = {demandNo,mjrHead,subMjrHead,mnrHead,subHead,langName,finYrMst.getFinYearId()};//,String.valueOf(finYrMst.getFinYearId())
        			    List<SgvaBudsubhdMst> subHeadList = genDAO.getListByColumnAndValue(cols, vals);
        	            month=CheckIfNull(report.getParameterValue( "Month" ));
        	            year=CheckIfNull(report.getParameterValue( "Year" ));

        			    logger.info("*******************finYr*************"+finYrMst.getFinYearId());
        			    SgvaBudsubhdMst subHeadObj = subHeadList.get(0);
        			    subHeadId = String.valueOf(subHeadObj.getBudsubhdId()); 
        			    logger.info("Paybill Subhead ID from following details is " + subHeadId + " from size " + subHeadList.size());
        		        }
        	  	 		
        	            Calendar cal = Calendar.getInstance();
        	            cal.set(Calendar.YEAR, Integer.parseInt(year));
        	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
        	            java.util.Date date = cal.getTime();
        	            SimpleDateFormat sdfObj = new SimpleDateFormat("MMM");
        			    
        		        String Month = sdfObj.format(date);
        		        sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
        	            cal.set(Calendar.YEAR, Integer.parseInt(year));
        	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
        	            cal.set(Calendar.DAY_OF_MONTH, 1);
        	            
        	            int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        	            
        	            java.util.Date startdate = cal.getTime();
        	            
        	            cal.set(Calendar.DAY_OF_MONTH, totalDays);
        	            
        	            java.util.Date endMonthDate = cal.getTime();
        	            String startDate= sdfObj.format(startdate);
        	            String endDate  = sdfObj.format(endMonthDate);
        	    	

        		        if(ClassType.equals("ClassIV"))
        	            report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GENERAL PROVIDENT FUND Deductions:-  -GPF of Class- IV"+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+"Name of the office maintaining accounts: DIR.OF PENSION & P.F.AHMEDABAD  "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
        		        else if(ClassType.equals("otherThanIV"))
            		        report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GENERAL PROVIDENT FUND Deductions:GPF of other than Class- IV"+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+"Name of the office maintaining accounts: Accountant General RAJKOT/AHMEDABAD"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
        		        else if(ClassType.equals("IAS"))
            		        report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GENERAL PROVIDENT FUND Deductions:GPF of I.A.S OFFICERS "+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+"Name of the office maintaining accounts: Accountant General RAJKOT/AHMEDABAD"+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  
        		        else
        		        report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GENERAL PROVIDENT FUND Deductions:-"+System.getProperty("line.separator")+"Amount deducted from the salary for the month of  "+Month+". "+year+System.getProperty("line.separator")+"Name of the office maintaining accounts: Accountant General RAJKOT/AHMEDABAD  "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);	
        	            
        		    
        	            boolean flag=true;
                        Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
                       	                
                        Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
                        ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
                      
                        SessionFactory sessionFactory = serviceLocator.getSessionFactory();
                        Session session= sessionFactory.getCurrentSession();	
                        
                        cal = Calendar.getInstance();cal.set(Calendar.YEAR, 2005);
                        cal.set(Calendar.MONTH,(4-1));cal.set(Calendar.DAY_OF_MONTH, 1);
                        String CPFDate  = sdfObj.format(cal.getTime());		

        	            String IsApproved = CheckIfNull(report.getParameterValue("Is Approved")); 
      	            String approveDate="";
      	            String approveEndDate="";
      	            if(IsApproved.equals("no"))
      	            {
      	            
      	            int yearBeforeApprove= Integer.parseInt(year);
      	            int monthBeforeApprove=Integer.parseInt(month);
      	            
      	            if(Integer.parseInt(month)==1)
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
        	  	 		Session hibSession = sessionFactory.getCurrentSession();
                        String query = " select dtl.hrEisEmpMst.orgEmpMst.empId ,";
        	            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName ,";
        	            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId ,dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";
        	            query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
        	            query+=" sum(pay.adv9670+pay.advIV9670), ";//adv rev
        	            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt || '-' || dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt|| '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherIncrAmt || '-' ||dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherEndAmt,";
        	            query+=" sum(pay.basic0101) ,";
        	            query+=" sum(pay.deduc9670+pay.deduc9620),  ";//gpf  
        	            query+=" sum(pay.deduc9531),  ";//gpf  iv
        	            // hardcoded
			            if(IsApproved.equals("no"))
			            {
	          	          	query+="  ( ";
	          	          	query+="  select p.hrLoanEmpDtls.loanPrinInstNo from  HrLoanEmpPrinRecoverDtls p ";
	          	          	query+="  where p.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
	          	          	query+="  and p.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and p.updatedDate >='"+approveDate+"' and p.updatedDate <='" +approveEndDate+"' ";	          	
	          	      		query+="  ), ";
			            }
			            else
			            {
        	          	query+="  ( ";
        	          	query+="  select p.hrLoanEmpDtls.loanPrinInstNo from  HrLoanEmpPrinRecoverDtls p ";
        	          	query+="  where p.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
        	          	query+="  and p.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and p.updatedDate >='"+startDate+"' and p.updatedDate <='" +endDate+"' ";	          	
        	      		query+="  ), ";
			            }
        	          	/*query+="  ( ";
        	          	query+="  select p.hrLoanEmpDtls.loanIntInstNo from  HrLoanEmpPrinRecoverDtls p ";
        	          	query+="  where p.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
        	          	query+="  and p.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and p.updatedDate >='"+startDate+"' and p.updatedDate <='" +endDate+"' ";	          	
        	      		query+="  ), ";	          	
        	            */
			            if(IsApproved.equals("no"))
			            {
	          	            query+="  ( ";
	          	          	query+="  select p.totalRecoveredInst+1 from  HrLoanEmpPrinRecoverDtls p ";
	          	          	query+="  where p.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
	          	          	query+="  and p.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and p.updatedDate >='"+approveDate+"' and p.updatedDate <='" +approveEndDate+"' ";	          	
	          	      		query+="  ), ";	  
			            }
			            else
			            {
	          	            query+="  ( ";
	          	          	query+="  select p.totalRecoveredInst from  HrLoanEmpPrinRecoverDtls p ";
	          	          	query+="  where p.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
	          	          	query+="  and p.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId and p.updatedDate >='"+startDate+"' and p.updatedDate <='" +endDate+"' ";	          	
	          	      		query+="  ), ";	  
			            }
      	
        	          	
        	      		/*query+="  ( ";
        	          	query+="  select i.totalRecoveredIntInst from  HrLoanEmpIntRecoverDtls i ";
        	          	query+="  where i.hrLoanEmpDtls.hrLoanAdvMst.loanAdvId = '"+GPF_ADV+"'  ";
        	          	query+="  and i.hrLoanEmpDtls.hrEisEmpMst.orgEmpMst.empId=pay.hrEisEmpMst.orgEmpMst.empId  and i.updatedDate >='"+startDate+"' and i.updatedDate <='" +endDate+ "' ";
        	          	query+="  ) ";
        	          	*/
        	          	
        	            query+=" sum(pay.basic0102+pay.basic0101) ";
        	            query+=" ,trim(pay.hrEisEmpMst.orgEmpMst.empGPFnumber) ";
        	            
        	            query+=" from HrPayPaybill pay, ";
        	            query+=" HrEisOtherDtls dtl,";
        	            
        	            query+=" OrgUserpostRlt           USRPST, ";
                    	query+=" HrPayOrderHeadMpg ORDHD, ";
                    	query+=" HrPayOrderHeadPostMpg ORDPST, ";
                    	query+=" OrgPostDetailsRlt pst ";
        	            
        	            query+=" where  ";
        	            
        	            query+="   pay.hrEisEmpMst.orgEmpMst.empDoj < '"+CPFDate+"' and ";
        	            query += "  ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
        	            query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
        	            query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
        	            query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
        	            query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
                        query+=" and (USRPST.endDate is null or ";
                        query+="  (USRPST.endDate<='"+startDate+"' and USRPST.endDate>='"+endDate+"' ))";
                        query+="  and pay.approveFlag in (0,1) ";

        	                        
        	            query+=" and pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId  ";
        	                        
        	            if(!empid.equals("")&&!empid.equals("-1"))            	
        	        		query+=" and dtl.hrEisEmpMst.orgEmpMst.empId = '"+empid+"'";
        		        
        	            
        	            if(!accountType.equals("") && !accountType.equals(" "))
        	            {
        	        		query+=" and lower(trim(dtl.hrEisEmpMst.orgEmpMst.empGPFnumber)) like '"+accountType.toLowerCase()+"%'";
        	            }
                        // Added by Akshay 
        	            if(!lname.equals("") && !lname.equals(" "))
        	            {
        	            	query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
        	            }
        	            
        	            if(!fname.equals("") && !fname.equals(" "))
        	            {
        	            	query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
        	            }
                        // Ended by Akshay

                        if(subHeadId!=null&&!subHeadId.equals("")&&!subHeadId.equals("-1"))
                        {
        	            	query+=("  and ORDHD.subheadId  = '"+subHeadId+"'");
                        }
                        
        	            if(!Department.equals("")&&!Department.equals("-1"))
        	            	query+=" and pst.cmnLocationMst.locId="+Department+"   ";
      	            if(isBillDefined&&!BillNo.equals(""))
                      {
                     	 
      	            	query+=" and   pay.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo in (select bill.billHeadId from  HrPayBillHeadMpg bill where bill.billId = "+BillNo+" ) )  and "; 
      	            	query+="   ORDHD.subheadId in (select bill.subheadId from  HrPayBillHeadMpg bill where bill.billId ="+BillNo+" )  and pst.orgPostMst.postId = pay.orgPostMst.postId  "; 
                      }
                      else
                      {
        		        if(subheadCode != null && !subheadCode.equals("") && !subheadCode.equals("-1"))
        	            {
        	            	query+=" and   ORDHD.subheadId ="+subheadCode+" ";
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
      	            	if(ClassType.equals("ClassIV"))
        	            	query+=" and pay.deduc9531!=0 ";   
        	                else if(ClassType.equals("otherThanIV"))
        	                query+=" and pay.deduc9670!=0 ";
        	            	   
        		            if(!Grade.equals("")&&!Grade.equals("-1"))            	
        	        		query+=" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId  = '"+Grade+"'";
        		            
        		            if(!Scale.equals("")&&!Scale.equals("-1"))            	
        	        		query+=" and dtl.HrEisSgdMpg.HrEisScaleMst.scaleId = '"+Scale+"'";
        		            
        		            if(!Designation.equals("")&&!Designation.equals("-1"))            	
        	        		query+=" and dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId = '"+Designation+"'";
        		            
        		            if(!month.equals("")&&!month.equals("-1"))            	
        		          		query+=" and pay.month='"+month+"'";
        		            
        		            if(!year.equals("")&&!year.equals("-1"))            	
        		          		query+=" and pay.year= '"+year+"'";
        		            
        		            	            
        		            query+=" group by ";
        		            query+=" dtl.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.orgEmpMst.empId,pay.hrEisEmpMst.empId,trim(pay.hrEisEmpMst.orgEmpMst.empGPFnumber), ";
        		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId, ";
        		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnId, ";
        		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeName, ";
        		            query+=" dtl.HrEisSgdMpg.HrEisGdMpg.OrgDesignationMst.dsgnShrtName, ";
        		            query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
        		            query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
        		            query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
        		            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleStartAmt, dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherIncrAmt, dtl.HrEisSgdMpg.HrEisScaleMst.scaleHigherEndAmt,  ";
        		            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleIncrAmt,  ";
        		            query+=" dtl.HrEisSgdMpg.HrEisScaleMst.scaleEndAmt,USRPST.orgUserMst.userId  ";
        		            
        	            query+=" order by trim(pay.hrEisEmpMst.orgEmpMst.empGPFnumber),dtl.HrEisSgdMpg.HrEisGdMpg.OrgGradeMst.gradeId,dtl.hrEisEmpMst.orgEmpMst.empId ";
        	            
        	           
        	            logger.info("***Query for PF Deduction details**" +query);
        	            
        	            
        	  	 		Query sqlQuery = hibSession.createQuery(query);	      	
        	            ArrayList dataList=new ArrayList();
        	            List RowList=sqlQuery.list();
        	            logger.info("*************************"+RowList.size());
        	            
        	            int cnt=1;
        	            long empId=0;
        	            
        	            Iterator itr = RowList.iterator(); 
        	            double payTotal=0;
        	            double monthsubscrTotal=0;
        	            double advrecTotal=0;
        	            double totalAmt=0;
        	            double grandPayTotal=0;
        	            double grandMonthsubscrTotal=0;
        	            double grandAdvrecTotal=0;

        	            String gpfPrefixPrev="";
        	            String gpfPrefixNext="";
        	            int grpByGpfAcc=1;
        	            int grpByGpfAccPrev=1;
        	            int grpbyCountno=0;
        	            boolean grpchange=false;
        	            String monthsubscrTotalInWords="";
        	            //String
        	            final int pagesizePF=15;
        	            long grandTotal=0;

        	           // int grouplistsize=1;
        	            while (itr.hasNext())
                        {
        		            Object[] rowList = (Object[]) itr.next();
        		            long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
        		            String designation = (String)(rowList[1]!=null?rowList[1]:"");
        		            long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"").toString());
        		            String Class = (String)(rowList[3]!=null?rowList[3]:"");
        		            String Name = (String)(rowList[4]!=null?rowList[4]:"");
        		            if(ClassType.equals("IAS"))
        		            	Name=Name+"(I.A.S.)";	
        		            double adv = Double.parseDouble((rowList[5]!=null?rowList[5]:"").toString());		            
        		            String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
        		            double pay = Double.parseDouble((rowList[7]!=null?rowList[7]:"").toString());		            
        		            double gpf = Double.parseDouble((rowList[8]!=null?rowList[8]:"0").toString());
        		            double gpfiv = Double.parseDouble((rowList[9]!=null?rowList[9]:"0").toString());
        		            long totalPrinInst = Long.parseLong((rowList[10]!=null?rowList[10].toString():"0").toString());
        		            //long totalIntInst = Long.parseLong((rowList[11]!=null?rowList[11].toString():"0").toString());
        		            long currentPrinInst = Long.parseLong((rowList[11]!=null?rowList[11].toString():"0").toString());
        		            //long currentIntInst = Long.parseLong((rowList[13]!=null?rowList[13].toString():"0").toString());
        		            double PE = Double.parseDouble((rowList[12]!=null?rowList[12]:"").toString());	
        		            

        		            
        			        
        		            
        		            String gpfAccNo=(String)(rowList[13]!=null?rowList[13]:"");
        		            if(gpfAccNo.length()>0&&gpfAccNo.lastIndexOf("/")>0)
        		            gpfPrefixPrev= gpfAccNo.substring(0,gpfAccNo.lastIndexOf("/"));
        		            
        		            if(!gpfPrefixNext.equals(gpfPrefixPrev)&&cnt>1)
        		            {	

        		              if(grpbyCountno<pagesizePF)
        		              {
        		            monthsubscrTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(monthsubscrTotal))+" only.";
        	                logger.info("***********monthsubscrTotalInWords*************"+monthsubscrTotalInWords);
        	                ArrayList row = new ArrayList();
        		            row.add("");
        		            row.add("");
        	             	StyledData dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData("TOTAL");                  
        	                row.add(dataStyle1);
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(payTotal));                  
        	                row.add(dataStyle1);
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(monthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
        	                row.add(dataStyle1);
        	                
        		            row.add("");                
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(advrecTotal));                  
        	                row.add(dataStyle1);
        	                
        		            row.add("");                
        	                
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(totalAmt));                  
        	                row.add(dataStyle1);
        	                
        	                logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);
        	                
        		            
        	                
        		            row.add(grpByGpfAcc);
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
                            dataStyle2.setData(monthsubscrTotalInWords);
                            ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
                            int totallength=rptCol.length;
            	            ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
                            
                            int colspan=rptCol2.length;
                            
                            dataStyle2.setColspan(colspan);
                            row1.add(dataStyle2);
                            logger.info(totallength+"**************************"+colspan);
                            for(int c=0;c<(8);c++)
                           	row1.add("");
                        		row1.add(grpByGpfAcc);
                            //row.add(new PageBreak());
                            //row.add("Data");
                        	
                        	
            	            DataList.add(row1);
        		         logger.info("11111grpbyCountno=====>"+grpbyCountno+"grpByGpfAcc=====>"+grpByGpfAcc);
        		            }
        	                //row.add(new PageBreak());
        	                //row.add("Data");	
        		          //  ("grpbyCountno=====>"+grpbyCountno+"grpByGpfAcc=====>"+grpByGpfAcc);
        		            else  if(grpbyCountno>=pagesizePF)
        		            {
        		            	logger.info("grpbyCountno=====>"+grpbyCountno+"grpByGpfAcc=====>"+grpByGpfAcc);
        		              ArrayList row = new ArrayList();
        		              StyledData dataStyle1 = new StyledData();
           	             row = new ArrayList();
              	            row.add("");
              	            row.add("");
                           	 dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData("GRAND TOTAL");                  
                              row.add(dataStyle1);
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandPayTotal));                  
                              row.add(dataStyle1);
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandMonthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
                              row.add(dataStyle1);
                              
              	            row.add("");                
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandAdvrecTotal));                  
                              row.add(dataStyle1);
                              
              	            row.add("");                
                              
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandMonthsubscrTotal+grandAdvrecTotal));                  
                              row.add(dataStyle1);
                              
                              logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);
                              
              	            
                              
              	            row.add(grpByGpfAcc);
                              //row.add(new PageBreak());
                              //row.add("Data");
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
                                dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(grandMonthsubscrTotal+grandAdvrecTotal))+" only.");
                                ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
                                int totallength=rptCol.length;
                	            ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
                                
                                int colspan=rptCol2.length;
                                
                                dataStyle2.setColspan(colspan);
                                row1.add(dataStyle2);
                                logger.info(totallength+"**************************"+colspan);
                                for(int c=0;c<(8);c++)
                               	row1.add("");
                            		row1.add(grpByGpfAcc);
                                //row.add(new PageBreak());
                                //row.add("Data");
                            	
                            	
                	            DataList.add(row1);
                            	
   
        		            }

        		            
        		            
        		          //if(grpByGpfAccPrev!=grpByGpfAcc)
	    		            {
	    		                ArrayList pagebreakdata= new ArrayList();
	    		            	pagebreakdata.add(new PageBreak());
	    		                pagebreakdata.add("Data");
	    		            	DataList.add(pagebreakdata);
	
	    		            }
/*          		            grandMonthsubscrTotal+=monthsubscrTotal;
        		            grandAdvrecTotal+=advrecTotal;
        		            grandPayTotal+=payTotal;*/
        		            monthsubscrTotal=0;
        		            advrecTotal=0;
        		            totalAmt=0;
        		            payTotal=0;
        		            grandMonthsubscrTotal=0;
        		            grandAdvrecTotal=0;
        		            grandPayTotal=0; 
        		            }

        		            
        		            if(!gpfPrefixNext.equals(gpfPrefixPrev))
        		            {
        		            	grpByGpfAcc++;	
        		            	grpbyCountno=0;
        		            }
        		            
        		            grpbyCountno++;
        		            
        		            ArrayList row = new ArrayList();
        		           
        		        	  
        		            row.add(cnt);
        		            row.add(gpfAccNo);
        		            row.add(Name);
        		            if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
        		            row.add(Math.round(pay));
        		            else
        		            row.add(Math.round(PE));
        		            if(gradeId!=GradeCodeiv)
        		            row.add(Math.round(gpf+gpfiv));//one of them will be zero
        		            else
        			            row.add(Math.round(gpfiv+gpf));//one of them will be zero
        		            row.add("0");
        		            row.add(adv);
        		            //if(currentIntInst!=0)		            	
        		            //row.add(""+currentIntInst+"/"+totalIntInst+"");
        		            //else
        		            row.add(""+currentPrinInst+"/"+totalPrinInst+"");	
        		            row.add(Math.round(adv+gpf+gpfiv));
        		            
        		            row.add(grpByGpfAcc);
        		            //row.add(new PageBreak());
        		            //row.add("Data");
        		            
        		            DataList.add(row);
        		            /*if(grpByGpfAccPrev!=grpByGpfAcc)
        		            {
        		                ArrayList pagebreakdata= new ArrayList();
        		            	pagebreakdata.add(new PageBreak());
        		                pagebreakdata.add("Data");
        		            	DataList.add(pagebreakdata);

        		            }*/
        		            if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
        		            	payTotal+=pay;
        			            else
        			            	payTotal+=PE;
        			            
        		            
        		            monthsubscrTotal+=gpf+gpfiv;
        		            advrecTotal+=adv;
        		            totalAmt+=gpf+gpfiv+adv;
        		            grandMonthsubscrTotal+=gpf+gpfiv;
        		            grandAdvrecTotal+=adv;
        		            if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
        		            	grandPayTotal+=pay;
        			            else
        			            	grandPayTotal+=PE;
        		           // grandPayTotal+=payTotal;


        		            logger.info("the counter value is=====>"+cnt+"grpbyCountno===>"+grpbyCountno+"groyup no===>"+grpByGpfAcc);
        		            if(grpbyCountno%pagesizePF==0 || cnt==RowList.size() )
        		            {

        		            //monthsubscrTotalInWords=ConvertNumbersToWord.convert(Math.round(monthsubscrTotal));
        		            monthsubscrTotalInWords="Rupees  "+ConvertNumbersToWord.convert(Math.round(monthsubscrTotal))+" only.";
        	                logger.info("***********monthsubscrTotalInWords*************"+monthsubscrTotalInWords);
        	                row = new ArrayList();
        		            row.add("");
        		            row.add("");
        	             	StyledData dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData("TOTAL");                  
        	                row.add(dataStyle1);
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(payTotal));                  
        	                row.add(dataStyle1);
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(monthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
        	                row.add(dataStyle1);
        	                
        		            row.add("");                
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(advrecTotal));                  
        	                row.add(dataStyle1);
        	                
        		            row.add("");                
        	                
        	                
        	             	dataStyle1 = new StyledData();
        	                dataStyle1.setStyles(boldStyleVO);
        	                dataStyle1.setData(Math.round(monthsubscrTotal+advrecTotal));                  
        	                row.add(dataStyle1);
        	                
        	                logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);
        	                
        		            
        	                
        		            row.add(grpByGpfAcc);

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
                            dataStyle2.setData(monthsubscrTotalInWords);
                            ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
                            int totallength=rptCol.length;
            	            ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
                            
                            int colspan=rptCol2.length;
                            
                            dataStyle2.setColspan(colspan);
                            row1.add(dataStyle2);
                            logger.info(totallength+"**************************"+colspan);
                            for(int c=0;c<(7);c++)
                           	row1.add("");
                        		row1.add(grpByGpfAcc);
                            //row.add(new PageBreak());
                            //row.add("Data");
                        	
                        	
            	            DataList.add(row1);
        		            if( cnt!=(RowList.size()))
        		            {
        		                ArrayList pagebreakdata= new ArrayList();
        		            	pagebreakdata.add(new PageBreak());
        		                pagebreakdata.add("Data");
        		            	DataList.add(pagebreakdata);

        		            }
/*         		          if(!accountType.equals("") && !accountType.equals(" "))
        		          {
          		            grandMonthsubscrTotal+=gpf+gpfiv;
            		            grandAdvrecTotal+=adv;
            		            if(gradeId!=GradeCodeiv&&gradeId!=GradeCodeiii)
            		            	grandPayTotal+=pay;
            			            else
            			            	grandPayTotal+=PE;
        		          }
        		            grandMonthsubscrTotal+=monthsubscrTotal;
        		            grandAdvrecTotal+=advrecTotal;
        		            grandPayTotal+=payTotal;*/
        		            monthsubscrTotal=0;
        		            advrecTotal=0;
        		            totalAmt=0;
        		            
        		            payTotal=0;
 
        		            }
        		            
        		            if(grpbyCountno%pagesizePF==0)
        		            {
        		            	grpByGpfAcc++;
        		            }
        		          if(!accountType.equals("") && !accountType.equals(" "))
        		          {
        		        	  if(cnt==RowList.size())	
        		        	  {
        		        		  row = new ArrayList();
        		               StyledData dataStyle1 = new StyledData();
        		               	row = new ArrayList();
              	            row.add("");
              	            row.add("");
                           	 dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData("GRAND TOTAL");                  
                              row.add(dataStyle1);
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandPayTotal));                  
                              row.add(dataStyle1);
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandMonthsubscrTotal)/*+System.getProperty("line.separator")+monthsubscrTotalInWords+" "*/);                  
                              row.add(dataStyle1);
                              
              	            row.add("");                
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandAdvrecTotal));                  
                              row.add(dataStyle1);
                              
              	            row.add("");                
                              
                              
                           	dataStyle1 = new StyledData();
                              dataStyle1.setStyles(boldStyleVO);
                              dataStyle1.setData(Math.round(grandMonthsubscrTotal+grandAdvrecTotal));                  
                              row.add(dataStyle1);
                              
                              logger.info(gpfPrefixNext+"    grpByGpfAcc"+gpfPrefixPrev+"       "+grpByGpfAcc+"                             cnt  "+cnt);
                              
              	            
                              
              	            row.add(grpByGpfAcc);
                              //row.add(new PageBreak());
                              //row.add("Data");
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
                                dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(grandMonthsubscrTotal+grandAdvrecTotal))+" only.");
                                ReportColumnVO[] rptCol = report.getColumnsToDisplay(); 
                                int totallength=rptCol.length;
                	            ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
                                
                                int colspan=rptCol2.length;
                                
                                dataStyle2.setColspan(colspan);
                                row1.add(dataStyle2);
                                logger.info(totallength+"**************************"+colspan);
                                for(int c=0;c<(8);c++)
                               	row1.add("");
                            		row1.add(grpByGpfAcc);
                                //row.add(new PageBreak());
                                //row.add("Data");
                            	
                            	
                	            DataList.add(row1);
        		          }
        		          }
        		            cnt++; 
        		            gpfPrefixNext=gpfPrefixPrev;
        		            
                        }
                        

        	            /*if(grpByGpfAccPrev!=grpByGpfAcc)
        	            {
        	                ArrayList pagebreakdata= new ArrayList();
        	            	pagebreakdata.add(new PageBreak());
        	                pagebreakdata.add("Data");
        	            	DataList.add(pagebreakdata);

        	            }*/
        	            
        	            
        	            

        	            /*if(grpByGpfAccPrev!=grpByGpfAcc)
        	            {
        	                ArrayList pagebreakdata= new ArrayList();
        	            	pagebreakdata.add(new PageBreak());
        	                pagebreakdata.add("Data");
        	            	DataList.add(pagebreakdata);

        	            }*/
        	            
        	            
        	            
        	            /*StyleVO[] URLStyleVO=null;
        	            	URLStyleVO = new StyleVO[1];
        	              
        	              
        	              URLStyleVO[0] = new StyleVO();
        	              URLStyleVO[0].setStyleId(IReportConstants.ROWS_PER_PAGE);
        	              URLStyleVO[0].setStyleValue(20+"");
        	              
        	              URLStyleVO[1] = new StyleVO();
        	              URLStyleVO[1].setStyleId(IReportConstants.PAGE_BREAK_BRFORE_GROUPBY);
        	              URLStyleVO[1].setStyleValue("yes");
        	              
        	              report.addReportStyles(URLStyleVO);*/

        	            grpByGpfAccPrev=grpByGpfAcc;
        	            
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
