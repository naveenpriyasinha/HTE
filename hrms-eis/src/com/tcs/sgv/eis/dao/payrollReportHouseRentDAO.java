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

public class payrollReportHouseRentDAO extends DefaultReportDataFinder 
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
	              if(report.getReportCode().equals("10"))
	              {

//	             	 Added by Akshay
	             	  String fname = CheckIfNull(report.getParameterValue("FName"));
	             	  String lname = CheckIfNull(report.getParameterValue("LName"));
//	             	 Ended by Akshay
	             	  
	             	  
	             	  String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
	     	        	//logger.info(" nof rec out====>"+noOfRec);
	     	            if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
	     	            {
	     	                //logger.info("No Of Rec is********====>"+noOfRec);
	     	            	finalpagesize=Integer.parseInt(noOfRec);
	     	            }

	                     // hardcoded
	           	        long AISGradeCode=Long.parseLong(constantsBundle.getString("AISGradeCode"));
	     	  			           
	     	  			StringBuffer lsb = new StringBuffer(  );      

	     	            String empid=CheckIfNull(report.getParameterValue( "employeeName" ));
	     	            String City=CheckIfNull(report.getParameterValue( "City" ));
	     	            String Department="";
	     	            String Grade="";
	     	            String Scale="";
	     	            String Designation="";
	     	            String month="";
	     	            String year="";
	     	            String GroupBy="";

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
	     	 	        
	     	 	        //ended by samir joshi/////
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
	     	            String Quarter=CheckIfNull(report.getParameterValue( "Quarter" ));

	     	            	            
	     	            Calendar cal = Calendar.getInstance();
	     	            SimpleDateFormat sdfObj = new SimpleDateFormat("dd-MMM-yyyy");
	     	            
	     	            cal.set(Calendar.YEAR, Integer.parseInt(year));
	     	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
	     	            cal.set(Calendar.DAY_OF_MONTH, 1);
	     	            
	     	            java.util.Date startMonthDate = cal.getTime();
	     	            String startDate  = sdfObj.format(startMonthDate);	
	     	            
	     	            cal.set(Calendar.YEAR, Integer.parseInt(year));
	     	            cal.set(Calendar.MONTH,Integer.parseInt(month)-1);
	     	            cal.set(Calendar.DAY_OF_MONTH, 1);
	     	            
	     	            int totalDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	     	            
	     	            cal.set(Calendar.DAY_OF_MONTH, totalDays);
	     	            
	     	            java.util.Date endMonthDate = cal.getTime();
	     	            
	     	            String endDate  = sdfObj.format(endMonthDate);
	     	            
	     	            boolean flag=true;
	                     Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
	                    	                
	                     Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	                     ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
	                   
	                     SessionFactory sessionFactory = serviceLocator.getSessionFactory();
	                     Session session= sessionFactory.getCurrentSession();	           
	     	  	 		
	     			    String demandNo = "";//CheckIfNull(report.getParameterValue("demandNo")); 
	     		    	//(String)requestAttributes.get("demandNo");
	     		    String mjrHead = "";//CheckIfNull(report.getParameterValue("mjrHead"));
	     		    String subMjrHead = "";//CheckIfNull(report.getParameterValue("subMjrHead"));
	     		    String mnrHead = "";//CheckIfNull(report.getParameterValue("mnrHead"));
	     		    String subHead = CheckIfNull(report.getParameterValue("subHead"));
	     		    //String dtlHead = CheckIfNull(report.getParameterValue("dtlHead"));
	     		    String subheadflag=subHead;
	     		    logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = " + subHead);
	                 SimpleDateFormat sdfObj1 = new SimpleDateFormat("MMM");
	     		    String Month = sdfObj1.format(startMonthDate);
	     	        
	         		report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of GANDHINAGAR HOUSE RENT Deductions for the month of  "+Month+". "+year+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);  

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
	     	  	 		
	                     
	     	  	 		Session hibSession = sessionFactory.getCurrentSession();
	                     String query = " select dtl.hrEisEmpMst.empId ,";
	     	            query+=" pst.orgDesignationMst.dsgnShrtName ,";
	     	            query+=" pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId, pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName , ";
	     	            query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empMname || ' ' || dtl.hrEisEmpMst.orgEmpMst.empLname , ";
	     	            //query+=" dtl.hrQuaterTypeMst.quaType ,";
	     	            query+=" qtrdtls.hrQuaterTypeMst.quaType,";
	     	            query+=" scale.scaleStartAmt || '-' || scale.scaleIncrAmt || '-' ||scale.scaleEndAmt|| '-' ||scale.scaleHigherIncrAmt || '-' ||scale.scaleHigherEndAmt,";
	     	            query+=" sum(pay.deduc9550) ,";// rentB
	     	            query+=" sum(pay.deduc9560),  ";//hrr	            
	     	            query+=" (select cmnAddress.street from CmnAddressMst cmnAddress where  cmnAddress.addressId=qtrdtls.cmnAddressMst.addressId ) ,";
	     	            query+="  (select cmnAddress.area from CmnAddressMst cmnAddress where cmnAddress.addressId=qtrdtls.cmnAddressMst.addressId ) ";
	     	            
	                 	if(isBillDefined)//this will always be at last so that no need to change the sequence if new column comes
	                 	{
	                 		query+="  ,psrmpg.psrId ";
	                 	}
	     	            
	     	            query+=" from HrPayPaybill pay, ";
	     	            query+=" HrEssQtrMst qtrdtls, ";
	                 	if(isBillDefined)
	                 	{
	                 		query+="  HrPayPsrPostMpg psrmpg, ";
	                 	}
	     	            query+=" HrEisOtherDtls dtl";
	     	            query+= " left outer join dtl.HrEisSgdMpg as sgd ";
	     	            query += " left outer join sgd.HrEisGdMpg as gd ";
	     	            query += "left outer join sgd.HrEisScaleMst scale, ";
	     	            
	     	            query+=" OrgUserpostRlt           USRPST, ";
	                 	query+=" HrPayOrderHeadMpg ORDHD, ";
	                 	query+=" HrPayOrderHeadPostMpg ORDPST, ";
	                 	query+=" OrgPostDetailsRlt pst ";
	     	            
	     	            query+=" where  ";
	     	            
	                 	if(isBillDefined)
	                 	{
	                 		query+="  psrmpg.postId=pay.orgPostMst.postId and ";
	                 	}
	     	            
	     	            query += "  ORDHD.subheadId in (select distinct pbhd.sgvaBudsubhdMst.budsubhdId from PaybillHeadMpg pbhd where pay.paybillGrpId = pbhd.hrPayPaybill.paybillGrpId )";
	     	            query += " and ORDPST.orderHeadId = ORDHD.orderHeadId ";
	     	            query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
	     	            query += " and USRPST.orgUserMst.userId = pay.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
	     	            query += " and pst.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
	                     query+=" and (USRPST.endDate is null or ";
	                     query+="  (USRPST.endDate>='"+startDate+"' and USRPST.startDate<='"+endDate+"'    ))";
	                     query+="  and pay.approveFlag in (0,1) ";

	     	                        
	     	            query+=" and pay.hrEisEmpMst.orgEmpMst.empId=dtl.hrEisEmpMst.orgEmpMst.empId and (pay.deduc9550>0 or pay.deduc9560>0) ";
	     	            query+=" and qtrdtls.orgUserMstByAllocatedTo = dtl.hrEisEmpMst.orgEmpMst.orgUserMst.userId and ((qtrdtls.allocationEndDate >='"+startDate+"' and qtrdtls.allocationEndDate <='"+endDate+"') or qtrdtls.allocationEndDate is null)  ";
	     	            
	     	            if(!empid.equals("")&&!empid.equals("-1"))            	
	     	        		query+=" and dtl.hrEisEmpMst.empId = '"+empid+"'";
	     		            
	                     //Added by Akshay 
	     	            if(!lname.equals("") && !lname.equals(" "))
	     	            {
	     	            	query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empLname) Like '"+lname.toLowerCase()+"%'");
	     	            }
	     	            
	     	            if(!fname.equals("") && !fname.equals(" "))
	     	            {
	     	            	query+=("  and lower(dtl.hrEisEmpMst.orgEmpMst.empFname) Like '"+fname.toLowerCase()+"%'");
	     	            }
	                    //Ended by Akshay 
	     	            
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
	     		        if(!Grade.equals("")&&!Grade.equals("-1"))            	
	     	        		query+=" and pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId    = '"+Grade+"'";
	     		            
	     		            if(!Scale.equals("")&&!Scale.equals("-1"))            	
	     	        		query+=" and pst.orgDesignationMst.dsgnShrtName.scaleId = '"+Scale+"'";
	     		            
	     		            if(!Designation.equals("")&&!Designation.equals("-1"))            	
	     	        		query+=" and pst.orgDesignationMst.dsgnId = '"+Designation+"'";
	     		            
	     		            if(!month.equals("")&&!month.equals("-1"))            	
	     		          		query+=" and pay.month='"+month+"'";
	     		            
	     		            if(!year.equals("")&&!year.equals("-1"))            	
	     		          		query+=" and pay.year= '"+year+"'";
	     		            
	     		            if(!Quarter.equals("")&&!Quarter.equals("-1"))            	
	     		          		query+=" and qtrdtls.hrQuaterTypeMst.quaId= '"+Quarter+"'";//HrQuaterTypeMst
	     	            
	     		            if(!City.equals("")&&!City.equals("-1"))            	
	     		          		query+=" and dtl.city= '"+City+"'";
	     	            	            
	     	            
	     		            query+=" group by  ";
	     	            	if(isBillDefined)
	     	            	{
	     	            		query+="  psrmpg.psrId, ";
	     	            	}
	     		            query+=" dtl.hrEisEmpMst.empId,dtl.hrQuaterTypeMst.quaId,  ";
	     		            query+="  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,pst.orgDesignationMst.dsgnId,  ";
	     		            query+="  pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeName,  ";
	     		            query+=" pst.orgDesignationMst.dsgnShrtName,  ";
	     		            query+="qtrdtls.hrQuaterTypeMst.quaType, ";
	     		            //query+=" dtl.hrQuaterTypeMst.quaType,  ";
	     		            query+="dtl.hrEisEmpMst.orgEmpMst.empPrefix||' '|| dtl.hrEisEmpMst.orgEmpMst.empFname,  ";
	     		            query+=" dtl.hrEisEmpMst.orgEmpMst.empMname,  ";
	     		            query+=" dtl.hrEisEmpMst.orgEmpMst.empLname,  ";
	     		            query+=" scale.scaleStartAmt,  ";
	     		            query+=" scale.scaleIncrAmt, scale.scaleHigherIncrAmt, scale.scaleHigherEndAmt,  ";
	     		            query+=" scale.scaleEndAmt,qtrdtls.cmnAddressMst.addressId  ";
	     	            	if(isBillDefined)
	     	            	{
	     	            		query+="  order by  psrmpg.psrId ";
	     	            	}
	     	            	else
	     		            query+=" order by pay.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId,dtl.hrEisEmpMst.empId ";
	     	            
	     	           
	     	            logger.info("***Query for Rent Deduction details**" +query);
	     	            
	     	            
	     	  	 		Query sqlQuery = hibSession.createQuery(query);	      	
	     	            ArrayList dataList=new ArrayList();
	     	            List RowList=sqlQuery.list();
	     	            logger.info("*************************"+RowList.size());
	     	            int cnt=1;
	     	            long empId=0;
	     	            long total=0;
	     	            Iterator itr = RowList.iterator();  
	                     while (itr.hasNext())
	                     {
	     		            Object[] rowList = (Object[]) itr.next();
	     		            long EmpId = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
	     		            String designation = (String)(rowList[1]!=null?rowList[1]:"");
	     		            long gradeId = Long.parseLong((rowList[2]!=null?rowList[2].toString():"").toString());
	     		            String Class = (String)(rowList[3]!=null?rowList[3]:"");
	     		            String Name = (String)(rowList[4]!=null?rowList[4]:"");
	     		            String QuarterType = (rowList[5]!=null?rowList[5]:"").toString();		            
	     		            String  scale = (rowList[6]!=null?rowList[6]:"").toString();		            
	     		            double rentB = Math.round(Double.parseDouble((rowList[7]!=null?rowList[7]:"").toString()));		            
	     		            double hrr = Math.round(Double.parseDouble((rowList[8]!=null?rowList[8]:"").toString()));		            
	     		            String sector = (rowList[10]!=null?rowList[10]:"").toString();		            
	     		            String block = (rowList[9]!=null?rowList[9]:"").toString();		            
	     		            total+=Math.round(rentB)+Math.round(hrr);
	     		            ArrayList row = new ArrayList();
	             			/*if( cnt%20==0)
	             			{
	             				row.add(new PageBreak());
	             				row.add("Data");
	             			}*/
	             			//else
	             			{
	             				
	             			
	     		            row.add(cnt);
	     		            row.add(sector);
	     		            row.add(block);
	     		            row.add(QuarterType);
	     		            row.add(Name);
	     		            row.add(designation);
	     		            if(gradeId==AISGradeCode)
	     		            row.add(Math.round(hrr+rentB));
	     		            else
	     		            row.add(Math.round(hrr+rentB));	
	             			}
	     		            DataList.add(row);
	     		            cnt++; 
	     		            
	     	  	            if( cnt%finalpagesize==0)
	     	    			{
	     	  	            	row=new ArrayList();
	     	  	            	row.add(new PageBreak());
	     	      				row.add("Data");
	     	    	            DataList.add(row); 
	     	    			}

	     		            
	                     }
	                     //String TotalInWords=ConvertNumbersToWord.convert(Math.round(total));
	                     ArrayList row = new ArrayList();
	     	            row.add("");
	     	            row.add("");
	     	            row.add("");
	     	            row.add("");
	     	            row.add("");
	     	            row.add("Total");
	     	            row.add(total/*+System.getProperty("line.separator")+TotalInWords*/);	
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
	         	        
	                     ReportColumnVO[] rptCol2 = report.getColumnsToDisplay();
	                     ReportColumnVO[] rptCol = report.getColumnsToDisplay();
	                     int totallength=rptCol.length;
	                     int colspan=rptCol2.length;                    	        
	         	        
	                     dataStyle2.setData("Rupees  "+ConvertNumbersToWord.convert(Math.round(total))+" only.");
	                     dataStyle2.setColspan(colspan);
	                     row1.add(dataStyle2);
	                     
	                     for(int c=0;c<(totallength-colspan);c++)
	                     	row1.add("");
	         	        
	     	            
	     	            DataList.add(row1);
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
