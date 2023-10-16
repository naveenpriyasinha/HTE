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
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.StringTokenizer;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.RltBillTypeEdp;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;

public class SalaryDifferenceDAOImpl  extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener
{
	private  StyleVO[] selfCloseVO=null; 
	
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		Log logger = LogFactory.getLog(getClass());
		Connection lCon = null;
		Statement lStmt = null;
		ArrayList dataList = new ArrayList();
		try{
		long month=0,year=0,billNo=0;
		Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestAttributes.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");
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
		//logger.info("loan recovery report");
		lCon = DBConnection.getConnection(  );
		lStmt = lCon.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		
		try{
			logger.info("Month in param is "+report.getParameterValue("Month"));
			 billNo = StringUtility.convertToLong(report.getParameterValue("billNo")!=null?report.getParameterValue("billNo").toString().trim():"0");
			 month = StringUtility.convertToLong(report.getParameterValue("Month")!=null?report.getParameterValue("Month").toString():"-1");
			 year = StringUtility.convertToLong(report.getParameterValue("Year")!=null?report.getParameterValue("Year").toString():"0");
			 
			// logger.info("Bill Number is "+billNo);
			 
			 String BillNoinner="";
			 int l=0;
			 String subheadCode="";
			 String classIds="";
			 String desgnIds="";
			 String portType="";
			 String BillNo="";	    

			 BillNoinner=CheckIfNull(report.getParameterValue( "Bill No" ));
			// logger.info(BillNoinner);
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
					{
						portType=st1.nextToken();
						
						//logger.info("adfgadfgh"+portType);
					}
					else if(l==4)
					{
						 
						 billNo = StringUtility.convertToLong(st1.nextToken());
					}
					//logger.info("Actual value for bill no  "+BillNo);
					l++;
				} 
			 
			    //logger.info("bill number after tokeni............"+billNo);
			    String locationNameincaps=locationName.toUpperCase();
				String DeptName=locationNameincaps;
				long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				/*List<OrgDdoMst> ddoList = payBillDAOImpl.getDDOCodeByLoggedInPost(loggedInpostId);
				String ddocode ="";
				if(ddoList.size()>0)
					ddocode = ddoList.get(0).getDdoCode();*/
				String ddocode ="";
				String TanNo="";
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
	    			TanNo=ddoMst.getTanNo();
	    		}
	    		logger.info("DDO Code is " + ddocode);
				DeptName=payBillDAOImpl.getOffice(locationId);
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
				//logger.info("tresuryCode Tesing"+TresuryCode);
				String Title = "Schedule for Change Statement";
				//String MajorHead="8658";
				//String Tan="MUMD08599D";
				String deptHeader ="";
				deptHeader=Title;
				String Treasury=TresuryCode;
				List styleList = new ArrayList();
				List stData = new ArrayList();
				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				styleList.add(stData);

				 
				List r2= new ArrayList();
				r2.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));	
				styleList.add(r2);
				List r= new ArrayList();
				r.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")	",rightHead));	
				styleList.add(r);
				
				TabularData tData  = new TabularData(styleList);
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);
				
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		//billNo =19;
		DeptCompMPGDAOImpl prDao = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,serv.getSessionFactory());
			List allowList = prDao.getActiveAllowDeptforSalaryDiff(locationId,month,year);
			List deducList = prDao.getActiveDeducDeptforSalaryDiff(locationId,month,year);
			List loanList = prDao.getActiveLoanDeptforSalaryDiff(locationId,month,year);
			StringBuffer allow = new StringBuffer("");
			StringBuffer deduc = new StringBuffer("");
			StringBuffer loan = new StringBuffer("");
			for(int i=0;i<allowList.size();i++)
			{
			 
				
				allow.append(allowList.get(i).toString());
				if(i!=allowList.size()-1)
				{
					allow.append(",");
					 
				}
				 
			}
			 
			
			for(int i=0;i<deducList.size();i++)
			{
				deduc.append(deducList.get(i).toString());
				if(i!=deducList.size()-1)
				{
					deduc.append(",");
				}
			}
			 
			for(int i=0;i<loanList.size();i++)
			{
				loan.append(loanList.get(i).toString());
				if(i!=loanList.size()-1)
				{
					loan.append(",");
				}
			}
			 
			String allowCmnLookup= "2500134";
			String deducCmnLookup= "2500135";
			String loanCmnLookup = "2500136,2500137";
			
			
		 
			HrEdpComponentMpgDAOImpl edpCompo = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
			List<HrPayEdpCompoMpg> edpAllowList  =  edpCompo.getDataByEDP(  allow.toString(),  allowCmnLookup);
			List<HrPayEdpCompoMpg> edpDeducList  =  edpCompo.getDataByEDP(  deduc.toString(),  deducCmnLookup);
			List<HrPayEdpCompoMpg> edpLoanList  =  edpCompo.getDataByEDP(  loan.toString(),  loanCmnLookup);
			//logger.info("loan string is "+loan.toString());
		
			int reportColLength = edpAllowList.size()+edpDeducList.size()+edpLoanList.size()+5;
			logger.info("report column length "+reportColLength);
			logger.info("allow List size is "+edpAllowList.size());
			logger.info("deduc list size is "+edpDeducList.size());
			logger.info(" edpLoan list size "+edpLoanList.size());
			ReportColumnVO[] newReportColumns = new ReportColumnVO[reportColLength];
		 
			newReportColumns[0] = new ReportColumnVO();
			newReportColumns[0].setColumnId(1); 
			newReportColumns[0].setColumnHeader("Sr.No");
			newReportColumns[0].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[0].setTableName("a");
			newReportColumns[0].setColumnName("Sr No.");
			newReportColumns[0].setDisplayTotal(0); 
			newReportColumns[0].setColumnLevel(1);	
			newReportColumns[0].setColumnWidth(5);
			
			newReportColumns[1] = new ReportColumnVO();
			newReportColumns[1].setColumnId(2); 
			newReportColumns[1].setColumnHeader("Emp Name");
			newReportColumns[1].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[1].setTableName("a");
			newReportColumns[1].setColumnName("Emp Name");
			newReportColumns[1].setDisplayTotal(0); 
			newReportColumns[1].setColumnLevel(1);	
			newReportColumns[1].setColumnWidth(15);
			
			newReportColumns[2] = new ReportColumnVO();
			newReportColumns[2].setColumnId(3); 
			newReportColumns[2].setColumnHeader("Month");
			newReportColumns[2].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[2].setTableName("a");
			newReportColumns[2].setColumnName("Month");
			newReportColumns[2].setDisplayTotal(0); 
			newReportColumns[2].setColumnLevel(1);	
			newReportColumns[2].setColumnWidth(15);
			
			newReportColumns[3] = new ReportColumnVO();
			newReportColumns[3].setColumnId(4); 
			newReportColumns[3].setColumnHeader("Offici");
			newReportColumns[3].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[3].setTableName("a");
			newReportColumns[3].setColumnName("Offici");
			newReportColumns[3].setDisplayTotal(0); 
			newReportColumns[3].setColumnLevel(1);	
			newReportColumns[3].setColumnWidth(15);
			
			
			
			for(int i=4,j=0;i<edpAllowList.size()+4;i++,j++)
			{						 
				
				//logger.info("FIRST FOR CONDITION");
			//initialize column vo
				newReportColumns[i] = new ReportColumnVO();

			//configure column vo by setting different column properties
				
				newReportColumns[i].setColumnId(i+1); 
				newReportColumns[i].setColumnHeader(edpAllowList.get(j).getRltBillTypeEdp().getEdpShortName());
				//newReportColumns[i].setDataType(10);
			//newReportColumns[i].setAlignment();
				newReportColumns[i].setTableName("a");
				newReportColumns[i].setColumnName("asd"+i);
				newReportColumns[i].setDisplayTotal(0); 
				newReportColumns[i].setColumnLevel(1);	
				newReportColumns[i].setColumnWidth(15);
				
			//logger.info("in for "+i);
			 
			}
			
			for(int i=edpAllowList.size()+4,j=0;i<edpAllowList.size()+edpDeducList.size()+4;i++,j++)
			{
				//logger.info("SECOND FOR CONDITION");
				//initialize column vo
				newReportColumns[i] = new ReportColumnVO();

			//configure column vo by setting different column properties
				newReportColumns[i].setColumnId(i+1); 
				newReportColumns[i].setColumnHeader(edpDeducList.get(j).getRltBillTypeEdp().getEdpShortName());
				//newReportColumns[i].setDataType(10);
			//newReportColumns[i].setAlignment();
				newReportColumns[i].setTableName("a");
				newReportColumns[i].setColumnName("asd"+i);
				newReportColumns[i].setDisplayTotal(0); 
				newReportColumns[i].setColumnLevel(1);	
				newReportColumns[i].setColumnWidth(15);
			 
			//logger.info("in for "+i);
			}
			
			for(int i=edpAllowList.size()+edpDeducList.size()+4,j=0;i<edpAllowList.size()+edpDeducList.size()+edpLoanList.size()+4;i++,j++)
			{
				//logger.info("THIRD FOR CONDITION");
				//initialize column vo
				newReportColumns[i] = new ReportColumnVO();

			//configure column vo by setting different column properties
				newReportColumns[i].setColumnId(i+1); 
				newReportColumns[i].setColumnHeader(edpLoanList.get(j).getRltBillTypeEdp().getEdpShortName());
				//newReportColumns[i].setDataType(10);
			//newReportColumns[i].setAlignment();
				newReportColumns[i].setTableName("a");
				newReportColumns[i].setColumnName("asd"+i);
				newReportColumns[i].setDisplayTotal(0); 
				newReportColumns[i].setColumnLevel(1);	
				newReportColumns[i].setColumnWidth(15);
			 
			//logger.info("in for "+i);
			}
			
			newReportColumns[reportColLength-1] = new ReportColumnVO();
			newReportColumns[reportColLength-1].setColumnId(reportColLength); 
			newReportColumns[reportColLength-1].setColumnHeader(" Net ");
			newReportColumns[reportColLength-1].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[reportColLength-1].setTableName("a");
			newReportColumns[reportColLength-1].setColumnName(" Net ");
			newReportColumns[reportColLength-1].setDisplayTotal(0); 
			newReportColumns[reportColLength-1].setColumnLevel(1);	
			newReportColumns[reportColLength-1].setColumnWidth(15);
			
			long prevMonth , prevYear ;
			if(month==1)
			{
				prevMonth=12;
				prevYear =year-1;
			}
			else
			{
				prevMonth=month-1;
				prevYear =year;
			}
			
			
			//getBillData(long locId,long billNo,long month,long year)
			PayBillDAOImpl pbDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			 List<HrPayPaybill> monthList = pbDao.getBillData(locationId, billNo, month, year);
			 List<HrPayPaybill> prevMonthList = pbDao.getBillData(locationId, billNo, prevMonth, prevYear);
			 
			 int diffCount = 1;
			 Class cls = HrPayPaybill.class;
			
			 
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
			 
			 
				StyleVO[] differenceDisplayVO = new StyleVO[4];
				differenceDisplayVO[0] = new StyleVO();
				differenceDisplayVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				differenceDisplayVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
				differenceDisplayVO[1] = new StyleVO();
				differenceDisplayVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				differenceDisplayVO[1].setStyleValue("Right");
				differenceDisplayVO[2] = new StyleVO();
				differenceDisplayVO[2].setStyleId(IReportConstants.BORDER);
				differenceDisplayVO[2].setStyleValue("NO");
				differenceDisplayVO[3] = new StyleVO();
				differenceDisplayVO[3].setStyleId(IReportConstants.BACKGROUNDCOLOR);
				differenceDisplayVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_PINK);
				
				StyleVO[] newAddDisplayVO = new StyleVO[4];
				newAddDisplayVO[0] = new StyleVO();
				newAddDisplayVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				newAddDisplayVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
				newAddDisplayVO[1] = new StyleVO();
				newAddDisplayVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				newAddDisplayVO[1].setStyleValue("Right");
				newAddDisplayVO[2] = new StyleVO();
				newAddDisplayVO[2].setStyleId(IReportConstants.BORDER);
				newAddDisplayVO[2].setStyleValue("NO");
				newAddDisplayVO[3] = new StyleVO();
				newAddDisplayVO[3].setStyleId(IReportConstants.BACKGROUNDCOLOR);
				newAddDisplayVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_GREEN);
				
				
				StyleVO[] removalDisplayVO = new StyleVO[4];
				removalDisplayVO[0] = new StyleVO();
				removalDisplayVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
				removalDisplayVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
				removalDisplayVO[1] = new StyleVO();
				removalDisplayVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
				removalDisplayVO[1].setStyleValue("Right");
				removalDisplayVO[2] = new StyleVO();
				removalDisplayVO[2].setStyleId(IReportConstants.BORDER);
				removalDisplayVO[2].setStyleValue("NO");
				removalDisplayVO[3] = new StyleVO();
				removalDisplayVO[3].setStyleId(IReportConstants.BACKGROUNDCOLOR);
				removalDisplayVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_YELLOW);
				
				long temp=340000;
				CmnlookupMstDAOImpl lookupDao = new CmnlookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				 List<CmnLookupMst> list = lookupDao.getListByColumnAndValue(new String[]{"lookupShortName","parentLookupId"},new Object[]{String.valueOf(month),temp} );
				String monthName = list!=null&&list.size()>0?list.get(0).getLookupName():String.valueOf(month);
				list = lookupDao.getListByColumnAndValue(new String[]{"lookupShortName","parentLookupId"},new Object[]{String.valueOf(prevMonth),temp} );
				String prevMonthName = list!=null&&list.size()>0?list.get(0).getLookupName():String.valueOf(prevMonth);
			 
				//logger.info("monthName "+monthName+" prevMonthName "+prevMonthName);
				int reportSize =0;
				if(monthList != null && monthList.size() >0 && prevMonthList != null && prevMonthList.size()>0)
					reportSize = Math.max(monthList.size(), prevMonthList.size());
				else if(monthList != null && monthList.size() > 0)
					reportSize= monthList.size();
			ArrayList row = new ArrayList();
			
			
			for(int monthcount=0,prevMonthCount=0,i=0;i<reportSize;i++ )
			{
				
				HrPayPaybill monthObj = null;
				HrPayPaybill prevMonthObj =null; 
				if(monthcount<monthList.size())
				{
					monthObj =  monthList.get(monthcount);
				}
				if(prevMonthList != null && prevMonthList.size()>0)
				{
				if(prevMonthCount<prevMonthList.size())
				{
					prevMonthObj = prevMonthList.get(prevMonthCount);
				}
				}
					
				if(monthObj!=null && prevMonthObj!=null)
				{
					
					
					if(monthObj.getHrEisEmpMst().getEmpId() == prevMonthObj.getHrEisEmpMst().getEmpId())
					{
						
						if(monthObj.getNetTotal() != prevMonthObj.getNetTotal())
						{
							
							row= new ArrayList();
							row.add(diffCount);
							diffCount++;
							if(monthObj.getHrEisEmpMst() != null)
								row.add(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname());
							else
								row.add("Vacant Post");
							
							ArrayList inerData = new ArrayList();
							ArrayList inerRow = new ArrayList();
							 
							inerRow.add(prevMonthName);
							inerData.add(inerRow);
							inerRow = new ArrayList();
							inerRow.add(monthName);
							inerData.add(inerRow);
							TabularData tdata = new TabularData(inerData);
							tdata.setStyles(numberDispalyVO);
							row.add(tdata);
							
							
							inerData = new ArrayList();
							inerRow = new ArrayList();
							inerRow.add(prevMonthObj.getBasic0101());
							inerData.add(inerRow);
							inerRow = new ArrayList();
							inerRow.add(monthObj.getBasic0101());
							inerData.add(inerRow);
							
							  tdata = new TabularData(inerData);
							if(prevMonthObj.getBasic0101()!=monthObj.getBasic0101())
							{
								tdata.setStyles(differenceDisplayVO);
							}
							else
							{
								tdata.setStyles(numberDispalyVO);
							}
							row.add(tdata);
							
							for(int j=0;j<edpAllowList.size();j++)
							{
								ArrayList innerData = new ArrayList();
								ArrayList innerRow = new ArrayList();
								
							 
								String methodName = "getAllow"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
								//logger.info("edp code is "+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode()+" allow name "+edpAllowList.get(j).getRltBillTypeEdp().getEdpDesc());
								
								try
								{
									Method method = cls.getMethod(methodName,null);
									double prevamount = (Double)method.invoke(prevMonthObj, null);
									double amount = (Double)method.invoke(monthObj, null);
									//logger.info(" allow name "+edpAllowList.get(j).getRltBillTypeEdp().getEdpDesc()+" amount is "+amount);
									innerRow.add(prevamount);
									innerData.add(innerRow);
									innerRow= new ArrayList();
									innerRow.add(amount);
									innerData.add(innerRow);
									
									TabularData td = new TabularData(innerData);
									if(prevamount==amount)
									{
										td.setStyles(numberDispalyVO);
									}
									else
									{
										td.setStyles(differenceDisplayVO);
									}
									row.add(td);
									
								}
								catch(Exception exe)
								{
									logger.error("Error is: "+ exe.getMessage());
									innerRow.add("0");
									innerData.add(innerRow);
									innerRow.add("0");
									innerData.add(innerRow);
									
									TabularData td = new TabularData(innerData);
									td.setStyles(differenceDisplayVO);
									row.add(td);
								}
								
								
							}
							
							///for deductions
							
							for(int j=0;j<edpDeducList.size();j++)
							{
								ArrayList innerData = new ArrayList();
								ArrayList innerRow = new ArrayList();
								
							 
								String methodName = "getDeduc"+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode();
								
								try
								{
									Method method = cls.getMethod(methodName,null);
									double prevamount = (Double)method.invoke(prevMonthObj, null);
									double amount = (Double)method.invoke(monthObj, null);
									//logger.info("deduc name "+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode()+" deduc value "+amount);
									innerRow.add(prevamount);
									innerData.add(innerRow);
									innerRow= new ArrayList();
									innerRow.add(amount);
									innerData.add(innerRow);
									
									TabularData td = new TabularData(innerData);
									 
									if(prevamount==amount)
									{
										td.setStyles(numberDispalyVO);
									}
									else
									{
										td.setStyles(differenceDisplayVO);
									}
									row.add(td);
									 
									
								}
								catch(Exception exe)
								{
									logger.error("Error is: "+ exe.getMessage());
									innerRow.add("0");
									innerData.add(innerRow);
									innerRow= new ArrayList();
									innerRow.add("0");
									innerData.add(innerRow);
									
									TabularData td = new TabularData(innerData);
									td.setStyles(numberDispalyVO);
									row.add(td);
								}
								
								
							}
							
							///for loans
							
							for(int j=0;j<edpLoanList.size();j++)
							{
								ArrayList innerData = new ArrayList();
								ArrayList innerRow = new ArrayList();
								
								
								String methodName ="";
								//= "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
								if(edpLoanList.get(j).getCmnLookupId()==2500136)
								{
									 methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
								}
								else if(edpLoanList.get(j).getCmnLookupId()==2500137)
								{
									methodName = "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
								}
									
								try
								{
									Method method = cls.getMethod(methodName,null);
									double prevamount = (Double)method.invoke(prevMonthObj, null);
									double amount = (Double)method.invoke(monthObj, null);
									innerRow.add(prevamount);
									innerData.add(innerRow);
									innerRow= new ArrayList();
									innerRow.add(amount);
									innerData.add(innerRow);
									
									TabularData td = new TabularData(innerData);
									 
									if(prevamount==amount)
									{
										td.setStyles(numberDispalyVO);
									}
									else
									{
										td.setStyles(differenceDisplayVO);
									}
									row.add(td);
									 
									
								}
								catch(Exception exe)
								{
									logger.info("==> in catch loan....... " + exe);
									innerRow.add("0");
									innerData.add(innerRow);
									innerRow= new ArrayList();
									innerRow.add("0");
									innerData.add(innerRow);
									
									TabularData td = new TabularData(innerData);
									td.setStyles(differenceDisplayVO);
									row.add(td);
								}
								
								
							}
							///for net
							
							
							
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName = "getNetTotal" ;
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = (Double)method.invoke(prevMonthObj, null);
								double amount = (Double)method.invoke(monthObj, null);
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(differenceDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(differenceDisplayVO);
								row.add(td);
							}
							
							logger.info("size of row is "+row.size());
							//logger.info("japen checking row is "+row);
							dataList.add(row);
						}
						
						monthcount++;
						prevMonthCount++;
					}
					else if(monthObj.getHrEisEmpMst().getEmpId() < prevMonthObj.getHrEisEmpMst().getEmpId())
					{
						///add monthObj in salary diff
						//logger.info("INSIDE ELSE IF CONDITION");

						row= new ArrayList();
						row.add(diffCount);
						diffCount++;
						if(monthObj.getHrEisEmpMst() != null)
							row.add(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname());
						else
							row.add("Vacant Post");
						
						ArrayList inerData = new ArrayList();
						ArrayList inerRow = new ArrayList();
						
						/*inerRow.add(prevMonthName);
						inerRow = new ArrayList();
						inerRow.add(monthName);
						inerData.add(inerRow);
						*/
						//manish here
						//ArrayList inerData = new ArrayList();
						//ArrayList inerRow = new ArrayList();
						 
						inerRow.add(prevMonthName);
						inerData.add(inerRow);
						inerRow = new ArrayList();
						inerRow.add(monthName);
						inerData.add(inerRow);
						TabularData ttdata = new TabularData(inerData);
						ttdata.setStyles(numberDispalyVO);
						row.add(ttdata);
						//ends
						
						inerData = new ArrayList();
						inerRow = new ArrayList();
						inerRow.add(0);
						inerRow = new ArrayList();
						inerRow.add(monthObj.getBasic0101());
						inerData.add(inerRow);
						
						TabularData tdata = new TabularData(inerData);
						tdata.setStyles(newAddDisplayVO);
						row.add(tdata);
						
						for(int j=0;j<edpAllowList.size();j++)
						{
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName = "getAllow"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = 0;
								double amount = (Double)method.invoke(monthObj, null);
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
							}
							
							
						}
						
						///for deductions
						
						for(int j=0;j<edpDeducList.size();j++)
						{
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName = "getDeduc"+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode();
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = 0;
								double amount = (Double)method.invoke(monthObj, null);
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
							}
							
							
						}
						
						///for loans
						
						for(int j=0;j<edpLoanList.size();j++)
						{
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName ="";
							//= "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							if(edpLoanList.get(j).getCmnLookupId()==2500136)
							{
								 methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							}
							else if(edpLoanList.get(j).getCmnLookupId()==2500137)
							{
								methodName = "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							}
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = 0;
								double amount = (Double)method.invoke(monthObj, null);
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
							}
							
							
						}
						///for net
						
						
						
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName = "getNetTotal" ;
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = 0;
							double amount = (Double)method.invoke(monthObj, null);
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(differenceDisplayVO);
							row.add(td);
						}
						
						
						dataList.add(row);
						monthcount++;
						
						
					}
					else if(monthObj.getHrEisEmpMst().getEmpId() < prevMonthObj.getHrEisEmpMst().getEmpId())
					{
						//add prevMonthObj in salary difference
						//logger.info("INSIDE IF ONE CONDITION");
						row= new ArrayList();
						row.add(diffCount);
						diffCount++;
						if(monthObj.getHrEisEmpMst() != null)
							row.add(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname());
						else
							row.add("Vacant Post");
						
						ArrayList inerData = new ArrayList();
						ArrayList inerRow = new ArrayList();
						
						inerRow.add(prevMonthName);
						inerRow = new ArrayList();
						inerRow.add(monthName);
						inerData.add(inerRow);
						
						
						inerData = new ArrayList();
						inerRow = new ArrayList();
						inerRow.add(prevMonthObj.getBasic0101());
						inerRow = new ArrayList();
						inerRow.add(0);
						inerData.add(inerRow);
						
						TabularData tdata = new TabularData(inerData);
						tdata.setStyles(removalDisplayVO);
						row.add(tdata);
						for(int j=0;j<edpAllowList.size();j++)
						{
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName = "getAllow"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = (Double)method.invoke(prevMonthObj, null);
								double amount = 0;
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(removalDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
							}
							
							
						}
						
						///for deductions
						
						for(int j=0;j<edpDeducList.size();j++)
						{
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName = "getDeduc"+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode();
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = (Double)method.invoke(prevMonthObj, null);
								double amount = 0;
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(newAddDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(removalDisplayVO);
								row.add(td);
							}
							
							
						}
						
						///for loans
						
						for(int j=0;j<edpLoanList.size();j++)
						{
							ArrayList innerData = new ArrayList();
							ArrayList innerRow = new ArrayList();
							
						 
							String methodName ="";
							//= "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							if(edpLoanList.get(j).getCmnLookupId()==2500136)
							{
								 methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							}
							else if(edpLoanList.get(j).getCmnLookupId()==2500137)
							{
								methodName = "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							}
							try
							{
								Method method = cls.getMethod(methodName,null);
								double prevamount = (Double)method.invoke(prevMonthObj, null);
								double amount = 0;
								innerRow.add(prevamount);
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add(amount);
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(differenceDisplayVO);
								row.add(td);
								
							}
							catch(Exception exe)
							{
								innerRow.add("0");
								innerData.add(innerRow);
								innerRow= new ArrayList();
								innerRow.add("0");
								innerData.add(innerRow);
								
								TabularData td = new TabularData(innerData);
								td.setStyles(removalDisplayVO);
								row.add(td);
							}
							
							
						}
						///for net
						
						
						
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName = "getNetTotal" ;
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = (Double)method.invoke(prevMonthObj, null);
							double amount = 0;
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
						}
						
						prevMonthCount++;
						dataList.add(row);
					
						
					}
					else
					{
						////will never come here
						
					}
						
						
				}
				else if(monthObj!=null  && prevMonthObj==null)
				{
					//add MonthObj in salary difference
					//logger.info("INSIDE IF TWO CONDITION");

					///add monthObj in salary diff
					
					//logger.info("ELSE IF CONDITION");
					
					row= new ArrayList();
					row.add(diffCount);
					diffCount++;
					
					if(monthObj.getHrEisEmpMst() != null)
						row.add(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname());
					else
						row.add("Vacant Post");
					
					
					ArrayList inerData = new ArrayList();
					ArrayList inerRow = new ArrayList();
					 
					inerRow.add(prevMonthName);
					inerData.add(inerRow);
					inerRow = new ArrayList();
					inerRow.add(monthName);
					inerData.add(inerRow);
					TabularData tdata = new TabularData(inerData);
					tdata.setStyles(numberDispalyVO);
					row.add(tdata);
					
					
					inerData = new ArrayList();
					inerRow = new ArrayList();
					inerRow.add(0);
					inerData.add(inerRow);
					inerRow = new ArrayList();
					inerRow.add(monthObj.getBasic0101());
					inerData.add(inerRow);
					
					  tdata = new TabularData(inerData);
					  tdata.setStyles(newAddDisplayVO);
					row.add(tdata);
					
					
					
					for(int j=0;j<edpAllowList.size();j++)
					{
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName = "getAllow"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = 0;
							double amount = (Double)method.invoke(monthObj, null);
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
						}
						
						
					}
					
					///for deductions
					
					for(int j=0;j<edpDeducList.size();j++)
					{
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName = "getDeduc"+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode();
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = 0;
							double amount = (Double)method.invoke(monthObj, null);
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
						}
						
						
					}
					
					///for loans
					
					for(int j=0;j<edpLoanList.size();j++)
					{
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName ="";
						//= "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
						if(edpLoanList.get(j).getCmnLookupId()==2500136)
						{
							 methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							 //logger.info("methodName*********"+methodName);
						}
						else if(edpLoanList.get(j).getCmnLookupId()==2500137)
						{
							methodName = "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							//logger.info("methodName*********"+methodName);
						}
						
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = 0;
							
							//logger.info("monthObj*********"+monthObj);
							
							double amount = (Double)method.invoke(monthObj, null);
							//logger.info("methodName*********"+methodName);
						//	logger.info("amount*********"+amount);
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(newAddDisplayVO);
							row.add(td);
						}
						
						
					}
					///for net
					
					
					
					ArrayList innerData = new ArrayList();
					ArrayList innerRow = new ArrayList();
					
				 
					String methodName = "getNetTotal" ;
					try
					{
						Method method = cls.getMethod(methodName,null);
						double prevamount = 0;
						double amount = (Double)method.invoke(monthObj, null);
						innerRow.add(prevamount);
						innerData.add(innerRow);
						innerRow= new ArrayList();
						innerRow.add(amount);
						innerData.add(innerRow);
						
						TabularData td = new TabularData(innerData);
						td.setStyles(newAddDisplayVO);
						row.add(td);
						
					}
					catch(Exception exe)
					{
						innerRow.add("0");
						innerData.add(innerRow);
						innerRow= new ArrayList();
						innerRow.add("0");
						innerData.add(innerRow);
						
						TabularData td = new TabularData(innerData);
						td.setStyles(newAddDisplayVO);
						row.add(td);
					}
					//logger.info("japen checking row "+diffCount+" is "+row );
					monthcount++;
					dataList.add(row);
				
					
					
				
					
					
				}
				else if(monthObj==null  && prevMonthObj!=null)
				{
					// add prevMonthObj in salary difference

					//add prevMonthObj in salary difference
					
					//logger.info("INSIDE IF THREE CONDITION");
					

					row= new ArrayList();
					row.add(diffCount);
					if(prevMonthObj.getHrEisEmpMst() != null)
						row.add(prevMonthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+prevMonthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+prevMonthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname());
					else
						row.add("Vacant Post");
					ArrayList inerData = new ArrayList();
					ArrayList inerRow = new ArrayList();
					 
					inerRow.add(prevMonthName);
					inerData.add(inerRow);
					inerRow = new ArrayList();
					inerRow.add(monthName);
					inerData.add(inerRow);
					TabularData tdata = new TabularData(inerData);
					tdata.setStyles(numberDispalyVO);
					row.add(tdata);
					
					
					inerData = new ArrayList();
					inerRow = new ArrayList();
					inerRow.add(prevMonthObj.getBasic0101());
					inerData.add(inerRow);
					inerRow = new ArrayList();
					inerRow.add(0);
					inerData.add(inerRow);
					
					  tdata = new TabularData(inerData);
					  tdata.setStyles(removalDisplayVO);
					row.add(tdata);
					
					
					for(int j=0;j<edpAllowList.size();j++)
					{
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName = "getAllow"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = (Double)method.invoke(prevMonthObj, null);
							double amount = 0;
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
						}
						
						
					}
					
					///for deductions
					
					for(int j=0;j<edpDeducList.size();j++)
					{
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						String methodName = "getDeduc"+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode();
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = (Double)method.invoke(prevMonthObj, null);
							double amount = 0;
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
						}
						
						
					}
					
					///for loans
					
					for(int j=0;j<edpLoanList.size();j++)
					{
						ArrayList innerData = new ArrayList();
						ArrayList innerRow = new ArrayList();
						
					 
						/*String methodName = "getLoan"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
						if(edpLoanList.get(j).getCmnLookupId()==2500136)
							 methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();*/
						
						String methodName ="";
						//= "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
						if(edpLoanList.get(j).getCmnLookupId()==2500136)
						{
							 methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							// logger.info("methodName*********"+methodName);
						}
						else if(edpLoanList.get(j).getCmnLookupId()==2500137)
						{
							methodName = "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
							//logger.info("methodName*********"+methodName);
						}
						try
						{
							Method method = cls.getMethod(methodName,null);
							double prevamount = (Double)method.invoke(prevMonthObj, null);
							double amount = 0;
							innerRow.add(prevamount);
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add(amount);
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
							
						}
						catch(Exception exe)
						{
							innerRow.add("0");
							innerData.add(innerRow);
							innerRow= new ArrayList();
							innerRow.add("0");
							innerData.add(innerRow);
							
							TabularData td = new TabularData(innerData);
							td.setStyles(removalDisplayVO);
							row.add(td);
						}
						
						
					}
					///for net
					
					
					
					ArrayList innerData = new ArrayList();
					ArrayList innerRow = new ArrayList();
					
				 
					String methodName = "getNetTotal" ;
					try
					{
						Method method = cls.getMethod(methodName,null);
						double prevamount = (Double)method.invoke(prevMonthObj, null);
						double amount = 0;
						innerRow.add(prevamount);
						innerData.add(innerRow);
						innerRow= new ArrayList();
						innerRow.add(amount);
						innerData.add(innerRow);
						
						TabularData td = new TabularData(innerData);
						td.setStyles(removalDisplayVO);
						row.add(td);
						
					}
					catch(Exception exe)
					{
						innerRow.add("0");
						innerData.add(innerRow);
						innerRow= new ArrayList();
						innerRow.add("0");
						innerData.add(innerRow);
						
						TabularData td = new TabularData(innerData);
						td.setStyles(removalDisplayVO);
						row.add(td);
					}
					
					prevMonthCount++;
					dataList.add(row);
				
					
				
					
					
				}
				else
				{
					
					
				}
					
			}
				
			
			
			report.setReportColumns(newReportColumns);
			report.initializeDynamicTreeModel();
			report.initializeTreeModel();
			
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		//logger.info("japen checking DataList  is "+dataList );
		logger.info("datalist size is "+dataList.size());
		return dataList;
		
	}
	public ReportVO exportReport(ReportVO reportVO, Object arg1, ReportEvent event) {
		
		
		reportVO.setReportName("");
		
		 
		reportVO.setExportFormat(event.BEFORE_PRINT);
		return reportVO;
	}
	
	public ReportVO getUserReportVO( ReportVO report, Object criteria )throws ReportException
	{
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
		String month = fmt1.format(today);

		if(month.charAt(0)=='0')
		{
			month=month.substring(1,2);
		}
		if(  report.getReportCode().equals("5000001") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("5000001") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		
		
		return report;
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

}
