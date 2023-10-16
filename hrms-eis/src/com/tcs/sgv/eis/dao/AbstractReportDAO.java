package com.tcs.sgv.eis.dao;
//Comment By  Maruthi for  import Organisation;
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
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.util.DBConnection;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class AbstractReportDAO extends DefaultReportDataFinder implements ReportDataFinder
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
				long arrearbillTypeId=Integer.parseInt(resourceBundle.getString("arrearbillTypeId"));
				
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
	              String desigName="";
	              String deptName="";
	              String cityName="";
	              String cardexCode="";
	              OrgDdoMst orgDdo = new OrgDdoMst();
	              ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");	
	              PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
	              Object[] objArry = null;
	              List lstSignInfo = new ArrayList();

	              if(report.getReportCode().equals("36")||report.getReportCode().equals("2500036"))
	              {

	        			StringBuffer lsb = new StringBuffer(  );      

	        	          String month="";
	        	          String year="";
	        	          String orderBy="";
	        	          String Department="";
	        	          month=CheckIfNull(report.getParameterValue( "Month" ));
	        	          year=CheckIfNull(report.getParameterValue( "Year" ));
	        	          orderBy=CheckIfNull(report.getParameterValue( "Order By" ));
	        	          Department=CheckIfNull(report.getParameterValue( "Department" ));
	        	          String ShowSignatureFlg=CheckIfNull(report.getParameterValue( "Show Signature" ));
                          String billType=CheckIfNull(report.getParameterValue( "billTypePara" ));
                          boolean arrearFlg = billType.equals(String.valueOf(arrearbillTypeId));
	        	          
	        	          //added by samir joshi for bill no wise report
	        	          if(Department.equals("")||Department.equals("-1"))
	        	          	Department=	locationId+"";	
		    	            else
		    	            	locationId=Long.parseLong(Department);
		  	              	lstSignInfo = billDAO.getReportSignature(locationId);
		  	              	
		  	              	boolean A3Flg=locationId==Long.parseLong(resourceBundle.getString("cash2Admin"));
		  	              	
			          		if(lstSignInfo.get(0)!=null)
			          		{
			          			
				          		objArry =(Object[]) lstSignInfo.get(0);
				          		desigName=objArry[0].toString();
				          		deptName=objArry[1].toString();
				          		cardexCode=objArry[2].toString();
				          		cityName=objArry[3].toString();            		
			          		}
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
	        	/*          ReportAttributeVO ravo = new ReportAttributeVO();
	        	          
	        	  		ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
	        	  		ravo.setLocation(IReportConstants.LOCATION_FOOTER);
	        	  		ravo.setAlignment(IReportConstants.ALIGN_RIGHT);

	        	  		ravo.setAttributeValue(System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator")+upperfooter+System.getProperty("line.separator")+DeptName+System.getProperty("line.separator")+lowerfooter);

	        	          //add the attribute to the report instance
	        	  		report.addReportAttributeItem(ravo);*/
	        		  		
	        		  		
	        		  		if(!ShowSignatureFlg.equals("No"))
	        		  		{

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
	        				trow4.add(new StyledData(desigName,centerboldStyleVO1));
	        				tblData.add(trow4);//added second row of the tabular data


	        				ArrayList trow5 = new ArrayList();

	        				trow5.add(" ");
	        				trow5.add(" ");
	        				trow5.add(new StyledData(deptName,centerboldStyleVO1));
	        				tblData.add(trow5);//added second row of the tabular data

	        				ArrayList trow3 = new ArrayList();

	        				//trow3.add(" ");
	        				//trow3.add(" ");
	        				//trow3.add(new StyledData(cityName,centerboldStyleVO1));

	        				//tblData.add(trow3);//added third row of the tabular data

	        				ArrayList trow6 = new ArrayList();
	        				trow6.add(" ");
	        				trow6.add(" ");
	        				trow6.add(new StyledData(cardexCode,centerboldStyleVO1));

	        				tblData.add(trow6);//added sixth row of the tabular data

	        				TabularData tabularData = new TabularData(tblData);//initialize tabular data
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
	        				tabularData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
	        				tabularData.addStyle(IReportConstants.BORDER, "No"); 

	        				report.setGrandTotalTemplate(tabularData);
	        				report.setGroupByTotalTemplate(tabularData);
	        				
	                       }
	        				
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
	        		        
	        		        //Added By Maruthi for Back button issue.
	 	        		   ArrayList styleList = new ArrayList();
	 		  	           ArrayList stData = new ArrayList();
	 			             if( BillNo != null && BillNo != "" )
	 			             {
	 			            //report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator")+"Bill No:"+BillNo);
	 			             String deptHeader=DeptName+System.getProperty("line.separator")+"Abstract Of all Bills for the Month of "+Month+". "+year;
	 			             stData.add(new StyledData (deptHeader,centerboldStyleVO1));
	 			             styleList.add(stData);
	 						 TabularData tData  = new TabularData(styleList);
	 						 tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
	 						 tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
	 						 tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
	 						 tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
	 						 tData.addStyle(IReportConstants.BORDER, "No");
	 						 tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
	 						 tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
	 						 report.setAdditionalHeader(tData);
	 			             }
	 			            else
	 			            {
	 			            	//report.setReportName(DeptName+System.getProperty("line.separator")+"Schedule of Scale Details "+System.getProperty("line.separator")+System.getProperty("line.separator"));
	 			            	 String deptHeader=DeptName+System.getProperty("line.separator")+"Abstract Of all Bills for the Month of "+Month+". "+year;
	 			            	 stData.add(new StyledData (deptHeader,centerboldStyleVO1));
	 		    				 styleList.add(stData);
	 		    				 TabularData tData  = new TabularData(styleList);
	 		    				 tData.addStyle(IReportConstants.STYLE_FONT_FAMILY, IReportConstants.VALUE_FONT_FAMILY_ARIAL);
	 		    				 tData.addStyle(IReportConstants.STYLE_FONT_STYLE, IReportConstants.VALUE_FONT_STYLE_NORMAL);
	 		    				 tData.addStyle(IReportConstants.STYLE_FONT_SIZE, IReportConstants.VALUE_FONT_SIZE_LARGER);
	 		    				 tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
	 		    				 tData.addStyle(IReportConstants.BORDER, "No");
	 		    				 tData.addStyle(IReportConstants.STYLE_FONT_WEIGHT,IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
	 		    				 tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
	 		    				 report.setAdditionalHeader(tData);
	 			            } 
	 	        		//Ended By Maruthi.
	        		        //report.setReportName(DeptName+System.getProperty("line.separator")+"Abstract Of all Bills for the Month of "+Month+". "+year);  
	        	          
	        	        boolean flag=true;
	        	        Map sessionKeys = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
	        	        	                
	        	        Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
	        	        ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
	        	        
	        	        SessionFactory sessionFactory = serviceLocator.getSessionFactory();
	        	        Session session= sessionFactory.getCurrentSession();	           
	        		 		
	        		 		Session hibSession = sessionFactory.getCurrentSession();
	        		 		// comented by samir joshi for sql query in sum function
	        	          String query = " select hpbsm.billId,b.hrEisBranchMst.branchId ,";

	        	          query+=" concat(concat(b.hrEisBranchMst.branchName,','),b.hrEisBankMst.bankName), ";
	        	          query+="  sum(a.netTotal),";
	        	          
		      			  if(arrearFlg)
		      			  {	  
	        	          query+="0,0,0,0,0,0,0,0,0,0";
		      			  }
		      			  else
		      			  {
	        	          query+="sum(non.totalNonGovtDeduc), ";
	        	          query+=" sum(non.lic) as lic,sum(non.societyOld) as old ,sum(non.societyNew) as  new_soc ,sum(non.karamChariBank) as karam,sum(non.nagrikBank) as nag, ";
	        	          query+=" sum(non.postOfficeRecurringDeposit) as por,sum(non.chiefMinisterReliefFund) as cmrf , ";
	        	          query+=" sum(non.salarySavingFund) as ssf , sum(non.underSecGAD)";
		      			  }
	        	          
		      			  if(arrearFlg)
		    			  {
		        	            query+=" from HrPayArrearPaybill a,  ";
		    			  }
		      			  else
		      			  {
	        	            query+=" from HrPayPaybill a,  ";
		      			  }

	        	          query+=" HrEisBankDtls b ,OrgPostDetailsRlt pt, ";
	        	          query+=" OrgUserpostRlt           USRPST, ";
	        	      	query+=" HrPayOrderHeadMpg ORDHD, ";
	        	      	//query+=" HrPayOrderHeadPostMpg ORDPST,HrPayPayslipNonGovt non,HrPayPsrPostMpg p,PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm , HrPayOrderSubHeadMpg hposm";
	        	      	query+=" HrPayOrderHeadPostMpg ORDPST,";
	        	      	
		      			if(!arrearFlg)
	        	      	query+="HrPayPayslipNonGovt non,";
	        	      			
	        	      	query+="PaybillHeadMpg bhm,HrPayBillHeadMpg hpbsm ";
	        	      
	        	          query+=" where ";
	        	          
		      			  if(!arrearFlg)
	        	          query+=" non.paybillID=a.id and";
		      			  else // check if any arrear type is selected (DA,CLA etc.) as bill selected is arrear
		      			  {
		    				String arrearType="";
		    				arrearType=CheckIfNull(report.getParameterValue( "Arrear List" ));
		    				if(!arrearType.equals("")&&!arrearType.equals("-1"))
		    				{
		    					query+="  a.salRevId.salRevId="+arrearType+" and ";
		    				}

		      			  }
		      			  
		      			  query += " a.hrEisEmpMst.empId = b.hrEisEmpMst.empId  ";
	        	          query += " and ORDPST.orderHeadId = ORDHD.orderHeadId  ";
	        	          query += " and USRPST.orgPostMstByPostId.postId = ORDPST.postId ";
	        	          query += " and USRPST.orgUserMst.userId = a.hrEisEmpMst.orgEmpMst.orgUserMst.userId ";
	        	          query += " and pt.orgPostMst.postId = USRPST.orgPostMstByPostId.postId ";
	        	          
	        	          if(!month.equals("")&&!month.equals("-1"))            	
	        	        		query+="and  bhm.month='"+month+"'";
	        	          
	        	          if(!year.equals("")&&!year.equals("-1"))            	
	        	        		query+="and bhm.year= '"+year+"'";
	        	          
	        	          if(!Department.equals("")&&!Department.equals("-1"))            	
	        	        		query+="and pt.orgPostMst.postId= a.orgPostMst.postId and pt.cmnLocationMst.locId=" + Department;
	      				if(isBillDefined&&!BillNo.equals(""))
	    				{

	    					//query+=" and   a.orgPostMst.postId in (select p.postId from HrPayPsrPostMpg p where p.billNo = bhm.billNo.billHeadId ) and " ;	 
	    					query+=" and pt.orgPostMst.postId = a.orgPostMst.postId  "; 
	    					query+=" and hpbsm.billId="+BillNo+"  ";
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
	    						query+="  and pt.orgDesignationMst.dsgnId in (" +desgnIds+ ") ";
	    					}    
	    				}
	      				 //query+="   and a.orgPostMst.postId=ORDPST.postId and a.orgPostMst.postId=a.postId ";
	      				query+="   and a.orgPostMst.postId=ORDPST.postId and a.orgPostMst.postId=a.orgPostMst.postId ";
	        	          query+="   and bhm.approveFlag in(0,1) ";
	        	          
	      				query+="  and bhm.hrPayPaybill = a.paybillGrpId  and hpbsm.billHeadId = bhm.billNo.billHeadId  ";
	    				query+= " and bhm.orgGradeMst.gradeId=a.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId "; //Date 5-Nov-08 by rahul

	        	          query+=" group by  bhm.hrPayPaybill,hpbsm.billId,b.hrEisBankMst.bankName,b.hrEisBranchMst.branchId,b.hrEisBranchMst.branchName,hpbsm.createdDate  ";
	        	          
	        	          if(orderBy.equals("Created Date"))
	        	          query+="order by bhm.hrPayPaybill,hpbsm.billId";
	        	          else
		        	          query+="order by hpbsm.billId";
   
	        	          logger.info("***Query for Abstract details**" +query);
	        	          
	        	          
	        		 		Query sqlQuery = hibSession.createQuery(query);	      	
	        		 		logger.info("***Sql Query for Abstract Report details**" +sqlQuery);
	        		 		ArrayList dataList=new ArrayList();
	        	          List RowList=sqlQuery.list();
	        	          logger.info("*************************"+RowList.size());
	        	          String blankspace = " ";
    		              String postSpace = "";
    		              
    		              if(A3Flg)
    		              {	  
    		              postSpace = System.getProperty("line.separator")+blankspace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+blankspace.replaceAll(" ",constantsBundle.getString("blankSpace"))+System.getProperty("line.separator")+blankspace.replaceAll(" ",constantsBundle.getString("blankSpace"));    	 
    		              }
    		              
	        	          int cnt=1;
	        	          long empId=0;
	        	          long privBillno=0;
	        	          long nextBillno=0;
	        	          long sbiMain=0;
	        	          long sbinsc=0;
	        	          long icici=0;
	        	          long otherBank=0;
	        	          long lic = 0;
	        	          long sosold =0;
	        	          long sosnew = 0;
	        	          long karmBnak =0;
	        	          long nagarBank = 0;
	        	          long rd = 0;
	        	          long cmf = 0;
	        	          long ss = 0;
	        	          long underSecGAD = 0;
	        	          long Amount=0;
	        	          long nongovdeduct=0;
	        	          long netAmount=0;
	    	              
	        	          long  totalsbiMain=0;
	        	          long  totalsbinsc=0;
	        	          long  totalicici=0;
	        	          long  totalotherBank=0;
	        	          long  totallic = 0;
	        	          long  totalsosold =0;
	        	          long  totalsosnew = 0;
	        	          long  totalkarmBnak =0;
	        	          long  totalnagarBank = 0;
	        	          long  totalrd = 0;
	        	          long  totalcmf = 0;
	        	          long  totalss = 0;
	        	          long  totalUnderSecGAD = 0;
	        	          long  totalAmount=0;
	        	          long  totalnongovdeduct=0;
	        	          long totalnetAmount=0;
	    	              
	        	          long tnetAmount=0;
	        	          long lstnetAmount=0;
	        	          long tsbiMain=0;
	        	          long tsbinsc=0;
	        	          long ticici=0;
	        	          long totherBank=0;
	        	          long tlic = 0;
	        	          long tsosold =0;
	        	          long tsosnew = 0;
	        	          long tkarmBnak =0;
	        	          long tnagarBank = 0;
	        	          long trd = 0;
	        	          long tcmf = 0;
	        	          long tss = 0;
	        	          long tUnderSecGAD = 0;
	        	          long tAmount=0;
	        	          long tnongovdeduct=0;
	        	          Iterator itr = RowList.iterator(); 
	        	          long AmountTotal=0;
	        	          while (itr.hasNext())
	        	          {
	        		            Object[] rowList = (Object[]) itr.next();

	        		            long billno = Long.parseLong((rowList[0]!=null?rowList[0].toString():"").toString());
	        		            privBillno=billno;
	        		            long branchId = Long.parseLong((rowList[1]!=null?rowList[1].toString():"").toString());	        		            
	        		            String bankName = (String)(rowList[2]!=null?rowList[2]:"");

	        		            if(nextBillno!=privBillno)
	        		            {	

	        		            	tlic=lic;
	        		            	tsosold=sosold;
	        		            	tsosnew=sosnew;
	        		            	tkarmBnak=karmBnak;
	        		            	tnagarBank=nagarBank;
	        		            	trd=rd;
	        		            	tcmf=cmf;
	        		            	tss=ss;
	        		            	tUnderSecGAD=underSecGAD;
		        		            tsbiMain =sbiMain;
		        		            tsbinsc = sbinsc;
		        		            ticici = icici;
		        		            totherBank +=  otherBank;
		        		            netAmount=tlic+tsosold+tsosnew+tkarmBnak+tnagarBank+trd+tcmf+tss+tsbiMain+tsbinsc+ticici+totherBank+tUnderSecGAD;
		        		            tnetAmount=netAmount;
		        		            lic=0;
		        		            sosold=0;
		        		            sosnew=0;
		        		            karmBnak=0;
		        		            nagarBank = 0;
		        		            rd = 0;
		        		            cmf =0;
		        		            ss =0;
		        		            underSecGAD = 0;
		  	        	            sbiMain=0;
			        	            sbinsc=0;
			        	            icici=0;
			        	            otherBank=0;
		        		            Amount=0;
		        		            netAmount=0;
		        		            nongovdeduct=0;


		        		       
	        		            }
	        		            lic+=Double.parseDouble((rowList[5]!=null?rowList[5].toString():0).toString());
	        		            sosold+=Double.parseDouble((rowList[6]!=null?rowList[6].toString():0).toString());
	        		            sosnew+=Double.parseDouble((rowList[7]!=null?rowList[7].toString():0).toString());
	        		            karmBnak+=Double.parseDouble((rowList[8]!=null?rowList[8].toString():0).toString());
	        		            nagarBank += Double.parseDouble((rowList[9]!=null?rowList[9].toString():0).toString());
	        		            rd += Double.parseDouble((rowList[10]!=null?rowList[10].toString():0).toString());
	        		            cmf += Double.parseDouble((rowList[11]!=null?rowList[11].toString():0).toString());
	        		            ss += Double.parseDouble((rowList[12]!=null?rowList[12].toString():0).toString());
	        		            underSecGAD += Double.parseDouble((rowList[13]!=null?rowList[13].toString():0).toString());
	        		            
	        		            totallic+=Double.parseDouble((rowList[5]!=null?rowList[5].toString():0).toString());
	        		            totalsosold+=Double.parseDouble((rowList[6]!=null?rowList[6].toString():0).toString());
	        		            totalsosnew+= Double.parseDouble((rowList[7]!=null?rowList[7].toString():0).toString());
	        		            totalkarmBnak+=Double.parseDouble((rowList[8]!=null?rowList[8].toString():0).toString());
	        		            totalnagarBank+=Double.parseDouble((rowList[9]!=null?rowList[9].toString():0).toString());
	        		            totalrd+=Double.parseDouble((rowList[10]!=null?rowList[10].toString():0).toString());
	        		            totalcmf+=Double.parseDouble((rowList[11]!=null?rowList[11].toString():0).toString());
	        		            totalss+=Double.parseDouble((rowList[12]!=null?rowList[12].toString():0).toString());
	        		            totalUnderSecGAD+=Double.parseDouble((rowList[13]!=null?rowList[13].toString():0).toString());
	        		            
	        		            totalnetAmount+=lic+sosold+sosnew+karmBnak+nagarBank+rd+cmf+ss;
	        		            
	        		            Amount =Math.round(Double.parseDouble((rowList[3]!=null?rowList[3].toString():0).toString()));
	        		            
	        		            nongovdeduct = Math.round(Double.parseDouble((rowList[4]!=null?rowList[4].toString():0).toString()));
	        		            //netAmount=lic+sosold+sosnew+karmBnak+nagarBank+rd+cmf+ss+sbiMain+sbinsc+icici+otherBank;




		        		            if(branchId==4)
		        		            {
		            		             sbiMain = Amount-nongovdeduct;
		            		             totalsbiMain+=sbiMain;
		            		             totalnetAmount+=sbiMain;
		        		            }
		
		        		            else if(branchId==2)
		        		            {
		        		            	 sbinsc =  Amount-nongovdeduct;
		        		            	 totalsbinsc+=sbinsc;
		        		            	 totalnetAmount+=sbinsc;
		        		            }
		        		            else if(branchId==5)
		        		            {
		        		            	 icici =  Amount-nongovdeduct;
		        		            	 totalicici+=icici;
		        		            	 totalnetAmount+=icici;
		        		            }
		        		            else 
		        		            {
		        		            	 otherBank +=  Amount-nongovdeduct;
		        		            	 totalotherBank+=Amount-nongovdeduct;
		        		            	 totalnetAmount+=otherBank;
		        		            }
		        		            netAmount+=lic+sosold+sosnew+karmBnak+nagarBank+rd+cmf+ss+sbiMain+sbinsc+icici+otherBank;
	        		         
	        		             //Amount=Amount-nongovdeduct;
	        		            AmountTotal+=Amount;
	        		           
	        		            if(nextBillno!=privBillno && cnt!=1 )
	        		            {
	        		                 	 ArrayList row = new ArrayList();
	        		            row.add(nextBillno+postSpace);
	        		            row.add(tsosnew+postSpace);
	        		            row.add(tsosold+postSpace);
	        		            row.add(tkarmBnak+postSpace);
	        		            row.add(tnagarBank+postSpace);
	        		            row.add(tlic+postSpace);
	        		            row.add(trd+postSpace);
	        		            row.add(tUnderSecGAD+postSpace);
	        		            row.add(tsbiMain+postSpace);
	        		            row.add(tsbinsc+postSpace);
	        		            row.add(ticici+postSpace);
	        		            row.add(totherBank+postSpace);
	        		            row.add(tnetAmount+postSpace);
	        		            DataList.add(row);

	        		               tsbiMain=0;
			        	           tsbinsc=0;
			        	           ticici=0;
			        	           totherBank=0;
			      		           tlic = 0;
			    		           tsosold =0;
			    		           tsosnew = 0;
			    		           tkarmBnak =0;
			    		           tnagarBank = 0;
			    		           trd = 0;
			    		           tUnderSecGAD = 0;
			    		           tnetAmount=0;
	        		            }
/*	        		            if(cnt==RowList.size())
	        		            {
	        		            	 ArrayList row = new ArrayList();
	        		            	 row.add(privBillno);
		        		            row.add(""+sosnew);
		        		            row.add(""+sosold);
		        		            row.add(""+karmBnak);
		        		            row.add(""+nagarBank);
		        		            row.add(""+lic);
		        		            row.add(""+rd);
		        		            row.add(""+sbiMain);
		        		            row.add(""+sbinsc);
		        		            row.add(""+icici);
		        		            row.add(""+otherBank);
		        		            DataList.add(row);
	        		            }*/
	        		            cnt++; 
	        		            nextBillno=privBillno;
	        	          }

	    		            	 ArrayList row1 = new ArrayList();
	    		            	 row1.add(privBillno+postSpace);
	        		            row1.add(sosnew+postSpace);
	        		            row1.add(sosold+postSpace);
	        		            row1.add(karmBnak+postSpace);
	        		            row1.add(nagarBank+postSpace);
	        		            row1.add(lic+postSpace);
	        		            row1.add(rd+postSpace);
	        		            row1.add(underSecGAD+postSpace);
	        		            row1.add(sbiMain+postSpace);
	        		            row1.add(sbinsc+postSpace);
	        		            row1.add(icici+postSpace);
	        		            row1.add(otherBank+postSpace);
	        		            lstnetAmount=lic+sosold+sosnew+karmBnak+nagarBank+rd+cmf+ss+underSecGAD+sbiMain+sbinsc+icici+otherBank;

	        		            row1.add(lstnetAmount+postSpace);
	        		            DataList.add(row1);
    		            
	        		            if(A3Flg)
	        		            {	
	        		            	
        		            		row1 = new ArrayList();
	        		            	for(int Cnt=0;Cnt<13;Cnt++)
	        		            	{	
	        		            		row1.add(postSpace+System.getProperty("line.separator")+blankspace.replaceAll(" ",constantsBundle.getString("blankSpace")));
	        		            	}
	        		            	
        		            		DataList.add(row1);
	        		            	
	        		            	ReportColumnVO[] rptCol = report.getReportColumns();  
	        		            	rptCol[0].setAlignment(1);
	        		            	report.setReportColumns(rptCol);
	        		            	report.initializeDynamicTreeModel();
	        		            	report.initializeTreeModel(); 
	        		            }
	        		            totalnetAmount=0;
	        		            totalnetAmount=totalsosnew+totalsosold+totalkarmBnak+totalnagarBank+totallic+totalrd+totalUnderSecGAD+totalsbiMain+totalsbinsc+totalicici+totalotherBank;
		      		            ArrayList row = new ArrayList();
	        	             	StyledData dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData("TOTAL");                  
	        	                row.add(dataStyle1);
	        	    
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalsosnew);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalsosold);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalkarmBnak);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalnagarBank);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totallic);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalrd);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalUnderSecGAD);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalsbiMain);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalsbinsc);                  
	        	                row.add(dataStyle1);
	        	               
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalicici);                  
	        	                row.add(dataStyle1);
	        	                
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalotherBank);                  
	        	                row.add(dataStyle1);
	        	              
	        	                dataStyle1 = new StyledData();
	        	                dataStyle1.setStyles(boldStyleVO);
	        	                dataStyle1.setData(""+totalnetAmount);                  
	        	                row.add(dataStyle1);
	        	                
	        	                
/*		    		            row.add(""+totalsosnew);
		    		            row.add(""+totalsosold);
		    		            row.add(""+totalkarmBnak);
		    		            row.add(""+totalnagarBank);
		    		            row.add(""+totallic);
		    		            row.add(""+totalrd);
		    		            row.add(""+totalsbiMain);
		    		            row.add(""+totalsbinsc);
		    		            row.add(""+totalicici);
		    		            row.add(""+totalotherBank);*/
		    		            DataList.add(row);
		    		            logger.info("**********************"+DataList.size());
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
		  		   DBConnection.closeResultSet(lRs1);
		  		   DBConnection.closeStatement(lStmt);           
		  		   DBConnection.closeConnection(lCon);
		  		}
		        return DataList;
		     }

			public ReportVO getUserReportVO( ReportVO report, Object criteria )
		     throws ReportException
		 {

		     logger.info ("***********Inside User Report VO *********************");
	        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
		     
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
			String locid= locationId+"";
		     String month = fmt1.format(today);
		     
		     if(month.charAt(0)=='0')
		     {
		    	 
		    	 month=month.substring(1,2);
		    	 
		     }
		    	 
		     if( report.getReportCode().equals("36")||report.getReportCode().equals("2500036"))
		     {            
		          report.setParameterValue("Year",yr);
		          report.setParameterValue("Month",month);
		          report.setParameterValue("Department",locid);
                  report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		     }
		     if( report.getReportCode().equals("4") || report.getReportCode().equals("5") || report.getReportCode().equals("9"))
		     {            
		          report.setParameterValue("Year",yr);
		          report.setParameterValue("Month",month);
		     } 
				report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));


		     return report;
		 }
}
