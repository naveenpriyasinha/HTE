package com.tcs.sgv.eis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.event.reports.ReportEvent;
import com.tcs.sgv.common.event.reports.ReportEventListener;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.RltBillTypeEdp;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import java.sql.Types; 

public class ChangeStatementDAO  extends DefaultReportDataFinder 
implements ReportDataFinder,ReportEventListener
{
	private  StyleVO[] selfCloseVO=null; 
	private HttpServletRequest request = null;
	ResourceBundle rb = ResourceBundle.getBundle("resources.eis.ChangeStatementLabels_en_US");
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{
		Log logger = LogFactory.getLog(getClass());
		//Connection lCon = null;
		//Statement lStmt = null;

		ArrayList dataList = new ArrayList();
		try{
			Date startTime = new Date();
			logger.error("startTime "+startTime.toString());
			long month=0,year=0,billNo=0;
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			Map serviceMap = (Map)requestAttributes.get("serviceMap");						
			Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
			ServiceLocator serv = (ServiceLocator) requestAttributes.get("serviceLocator");

			CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
			String locationName=locationVO.getLocName();
			long locationId=locationVO.getLocId();

			GenericDaoHibernateImpl gen = new GenericDaoHibernateImpl(
					CmnLocationMst.class);
			gen.setSessionFactory(serv.getSessionFactory());

			CallableStatement cs = null;

			/*Connection conn = serv.getSessionFactory().getCurrentSession().connection();

			System.out.println("Connection = "+conn);
			if (cs == null)
			{
				cs = 
					conn.prepareCall("{? = call SEVARTH_DATA (?) }");
			}
			//cs.clearParameters();
			cs.setBigDecimal(2,new BigDecimal("7101000036"));
			cs.registerOutParameter(1,Types.VARCHAR);
			// cs.setInt(3,2);
			//cs.execute();
		//	boolean result = cs.execute();
			logger.info("out loop..");
			//ResultSet rs = cs.executeQuery();
			cs.execute();
		//	if(cs.getUpdateCount() > 0){
				ResultSet rs = cs.getResultSet();

			logger.info("rs..."+rs);
			while(rs.next()){
			logger.info("%d %d %d %s%n"+  rs.getString("P_EMP_NAME"));

				logger.info("in loop..");
			//	ResultSet rs1 = cs.getResultSet();
				//while(rs1.next())
				logger.info("%d %d %d %s%n"+  rs.getCursorName());
	        }*/
			//}
			//String obj1 = cs.getString(2);
			//logger.info("proc "+obj1);
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
			//selfCloseVO[0].setStyleValue("javascript:self.close()");
			selfCloseVO[0].setStyleValue("ifms.htm?actionFlag=getHomePage");
			//logger.info("loan recovery report");

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
			headerStyleVo[2].setStyleValue("16");
			headerStyleVo[3] = new StyleVO();
			headerStyleVo[3].setStyleId(IReportConstants.STYLE_FONT_FAMILY);
			headerStyleVo[3].setStyleValue(IReportConstants.VALUE_FONT_FAMILY_ARIAL);

			PayBillDAOImpl paybillDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());


			request = (HttpServletRequest) serviceMap.get("requestObj");

			billNo = StringUtility.convertToLong(request.getParameter("billNo")!=null?request.getParameter("billNo").toString().trim():"0");
			month = StringUtility.convertToLong(request.getParameter("Month")!=null?request.getParameter("Month").toString():"-1");
			year = StringUtility.convertToLong(request.getParameter("Year")!=null?request.getParameter("Year").toString():"0");

			logger.info("billNo "+billNo+" month "+month+" year "+year);

			String locationNameincaps=locationName.toUpperCase();
			String DeptName=locationNameincaps;
			long loggedInpostId = Long.parseLong(baseLoginMap.get("loggedInPost").toString());
			PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());

			String ddocode ="";
			String TanNo="";
			//long locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
			locationId=payBillDAOImpl.getLocationCode(billNo);
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locationId);
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

			int diffCount = 1;
			Class cls = HrPayPaybill.class;
			long temp=340000;

			CmnlookupMstDAOImpl lookupDao = new CmnlookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List<CmnLookupMst> list = lookupDao.getListByColumnAndValue(new String[]{"lookupShortName","parentLookupId"},new Object[]{String.valueOf(month),temp} );
			String monthName = list!=null&&list.size()>0?list.get(0).getLookupName():String.valueOf(month);
			list = lookupDao.getListByColumnAndValue(new String[]{"lookupShortName","parentLookupId"},new Object[]{String.valueOf(prevMonth),temp} );
			String prevMonthName = list!=null&&list.size()>0?list.get(0).getLookupName():String.valueOf(prevMonth);

			//logger.info("tresuryCode Tesing"+TresuryCode);
			String Title = "Schedule for Change Statement";
			//String MajorHead="8658";
			//String Tan="MUMD08599D";
			String deptHeader ="";
			deptHeader=Title;
			String Treasury=TresuryCode;
			List styleList = new ArrayList();
			List stData = new ArrayList();
			StyledData styledHeader = new StyledData (rb.getString("cs.month")+" "+rb.getString("cs."+monthName)+" "+year+" "+rb.getString("cs.heading"),headerStyleVo);
			styledHeader.setColspan(2);
			stData.add(styledHeader);
			stData.add("");
			styleList.add(stData);

			/*List header2 = new ArrayList();
				styledHeader = new StyledData (rb.getString("cs."+monthName)+" "+rb.getString("CS.ForTheMonth"),headerStyleVo);
				styledHeader.setColspan(2);
				header2.add(styledHeader);
				header2.add("");
				styleList.add(header2);*/

			List r= new ArrayList();
			r.add(new StyledData(rb.getString("cs.treasury")+" "+TresuryName+"("+Treasury+")	",rightHead));	
			styleList.add(r);

			List r2= new ArrayList();
			r2.add(new StyledData(rb.getString("cs.ddo")+ DeptName+"("+ddocode+") ",leftHeader));	
			styleList.add(r2);

			String billName = (String)payBillDAO.getBillName(billNo+"");
			r2= new ArrayList();
			r2.add(new StyledData(rb.getString("cs.billName")+billName,leftHeader));	
			styleList.add(r2);

			TabularData tData  = new TabularData(styleList);
			tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
			tData.addStyle(IReportConstants.BORDER, "No");
			tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
			report.setAdditionalHeader(tData);

			StyleVO[] style = new StyleVO[1];
			style[0] = new StyleVO();
			style[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
			style[0].setStyleValue("ifms.htm?actionFlag=getHomePage");

			report.setStyleList(style);
			//billNo =19;
			/*DeptCompMPGDAOImpl prDao = new DeptCompMPGDAOImpl(HrPayLocComMpg.class,serv.getSessionFactory());
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
			}*/

			String allow = "";
			String deduc = "";
			String loan = "";
			String allowCmnLookup= "2500134";
			String deducCmnLookup= "2500135";
			String loanCmnLookup = "2500136,2500137";

			HrEdpComponentMpgDAOImpl edpCompo = new HrEdpComponentMpgDAOImpl(HrPayEdpCompoMpg.class,serv.getSessionFactory());
			allow  = payBillDAOImpl.getActiveAllowDeptforCS( billNo,locationId,month,year, allowCmnLookup);
			deduc =  payBillDAOImpl.getActiveAllowDeptforCS(billNo,locationId,month,year, deducCmnLookup);
			loan  =  payBillDAOImpl.getActiveAllowDeptforCS( billNo,locationId,month,year, loanCmnLookup);

			List<HrPayEdpCompoMpg> edpAllowList = null;  
			List<HrPayEdpCompoMpg> edpDeducList = null;
			List<HrPayEdpCompoMpg> edpLoanList = null;
			if(allow != null && !allow.equals(""))
				edpAllowList =  edpCompo.getDataByEDP(  allow,  allowCmnLookup);
			if(deduc != null && !deduc.equals(""))
				edpDeducList  =  edpCompo.getDataByEDP(  deduc,  deducCmnLookup);
			if(loan != null && !loan.equals(""))
				edpLoanList  =  edpCompo.getDataByEDP(  loan,  loanCmnLookup);

			/*int reportColLength = edpAllowList.size()+edpDeducList.size()+edpLoanList.size()+5;
			logger.info("report column length "+reportColLength);
			logger.info("allow List size is "+edpAllowList.size());
			logger.info("deduc list size is "+edpDeducList.size());
			logger.info(" edpLoan list size "+edpLoanList.size());*/

			ReportColumnVO[] newReportColumns = new ReportColumnVO[8];

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
			differenceDisplayVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_NORMAL); 
			differenceDisplayVO[1] = new StyleVO();
			differenceDisplayVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			differenceDisplayVO[1].setStyleValue("Left");
			differenceDisplayVO[2] = new StyleVO();
			differenceDisplayVO[2].setStyleId(IReportConstants.BORDER);
			differenceDisplayVO[2].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);
			differenceDisplayVO[3] = new StyleVO();
			differenceDisplayVO[3].setStyleId(IReportConstants.COLOR);
			differenceDisplayVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK);

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
			newAddDisplayVO[3].setStyleId(IReportConstants.COLOR);
			newAddDisplayVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_GREEN);


			StyleVO[] redColorVO = new StyleVO[4];
			redColorVO[0] = new StyleVO();
			redColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			redColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			redColorVO[1] = new StyleVO();
			redColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			redColorVO[1].setStyleValue("Right");
			redColorVO[2] = new StyleVO();
			redColorVO[2].setStyleId(IReportConstants.BORDER);
			redColorVO[2].setStyleValue("NO");
			redColorVO[3] = new StyleVO();
			redColorVO[3].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			redColorVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK);

			StyleVO[] greenColorStyleVO = new StyleVO[4];
			greenColorStyleVO[0] = new StyleVO();
			greenColorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			greenColorStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_NORMAL); 
			greenColorStyleVO[1] = new StyleVO();
			greenColorStyleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			greenColorStyleVO[1].setStyleValue("Right");
			greenColorStyleVO[2] = new StyleVO();
			greenColorStyleVO[2].setStyleId(IReportConstants.BORDER_RIGHT);
			greenColorStyleVO[2].setStyleValue("NO");
			greenColorStyleVO[3] = new StyleVO();
			greenColorStyleVO[3].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			greenColorStyleVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK);

			StyleVO[] centerStyleVO  = new StyleVO[3];
			centerStyleVO[0] = new StyleVO();
			centerStyleVO[0]
			              .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerStyleVO[0]
			              .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_NORMAL);
			centerStyleVO[1] = new StyleVO();
			centerStyleVO[1]
			              .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerStyleVO[1].setStyleValue("Center");
			centerStyleVO[2] = new StyleVO();
			centerStyleVO[2].setStyleId(IReportConstants.BORDER_LEFT);
			centerStyleVO[2].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);

			StyleVO[] centerboldStyleVO  = new StyleVO[3];
			centerboldStyleVO[0] = new StyleVO();
			centerboldStyleVO[0]
			                  .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			centerboldStyleVO[0]
			                  .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
			centerboldStyleVO[1] = new StyleVO();
			centerboldStyleVO[1]
			                  .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			centerboldStyleVO[1].setStyleValue("Center");
			centerboldStyleVO[2] = new StyleVO();
			centerboldStyleVO[2].setStyleId(IReportConstants.BORDER_LEFT);
			centerboldStyleVO[2].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);



			StyleVO[] rightboldStyleVO  = new StyleVO[2];
			rightboldStyleVO[0] = new StyleVO();
			rightboldStyleVO[0]
			                 .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			rightboldStyleVO[0]
			                 .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER);
			rightboldStyleVO[1] = new StyleVO();
			rightboldStyleVO[1]
			                 .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rightboldStyleVO[1].setStyleValue("Right");


			StyleVO[] leftboldStyleVO  = new StyleVO[3];
			leftboldStyleVO[0] = new StyleVO();
			leftboldStyleVO[0]
			                .setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			leftboldStyleVO[0]
			                .setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_NORMAL);
			leftboldStyleVO[1] = new StyleVO();
			leftboldStyleVO[1]
			                .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			leftboldStyleVO[1].setStyleValue("Left");
			leftboldStyleVO[2] = new StyleVO();
			leftboldStyleVO[2]
			                .setStyleId(IReportConstants.BORDER);
			leftboldStyleVO[2].setStyleValue(IReportConstants.VALUE_STYLE_BORDER_NONE);

			StyleVO[] noRightBorder  = new StyleVO[1];
			noRightBorder[0] = new StyleVO();
			noRightBorder[0]
			              .setStyleId(IReportConstants.BORDER_RIGHT);
			noRightBorder[0]
			              .setStyleValue("NO");

			StyleVO[] noBottomBorder  = new StyleVO[2];
			noBottomBorder[0] = new StyleVO();
			noBottomBorder[0]
			               .setStyleId(IReportConstants.BORDER_BOTTOM);
			noBottomBorder[0]
			               .setStyleValue("NO");
			noBottomBorder[1] = new StyleVO();
			noBottomBorder[1]
			               .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			noBottomBorder[1]
			               .setStyleValue("Left");

			StyleVO[] rightAlign  = new StyleVO[1];
			rightAlign[0] = new StyleVO();
			rightAlign[0]
			           .setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			rightAlign[0]
			           .setStyleValue("Right");

			StyleVO[] leftAlign  = new StyleVO[1];
			leftAlign[0] = new StyleVO();
			leftAlign[0].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
			leftAlign[0].setStyleValue("Left");


			newReportColumns[0] = new ReportColumnVO();
			newReportColumns[0].setColumnId(1); 
			newReportColumns[0].setColumnName("Sr No"); // sr no 
			newReportColumns[0].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[0].setTableName("a");
			newReportColumns[0].setColumnHeader(rb.getString("cs.srNo")); // sr no.
			newReportColumns[0].setDisplayTotal(0); 
			newReportColumns[0].setColumnLevel(1);	
			newReportColumns[0].setColumnWidth(3);

			newReportColumns[1] = new ReportColumnVO();
			newReportColumns[1].setColumnId(2); 
			newReportColumns[1].setColumnName("Emp Name (Designation)");
			newReportColumns[1].setDataType(10);
			newReportColumns[1].setAlignment(2);
			newReportColumns[1].setTableName("a");
			newReportColumns[1].setColumnHeader(rb.getString("cs.empNameAndDesig"));  //emp name and designation
			newReportColumns[1].setDisplayTotal(0); 
			newReportColumns[1].setColumnLevel(1);	
			newReportColumns[1].setColumnWidth(25);

			newReportColumns[2] = new ReportColumnVO();
			newReportColumns[2].setColumnId(3); 
			newReportColumns[2].setColumnName("Type of Change");
			newReportColumns[2].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[2].setTableName("a");
			newReportColumns[2].setColumnHeader(rb.getString("cs.typeOfChange")); //type of change
			newReportColumns[2].setDisplayTotal(0); 
			newReportColumns[2].setColumnLevel(1);	
			newReportColumns[2].setColumnWidth(20);

			newReportColumns[3] = new ReportColumnVO();
			newReportColumns[3].setColumnId(4); 
			newReportColumns[3].setColumnName("Amount Difference");
			newReportColumns[3].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[3].setTableName("a");
			newReportColumns[3].setColumnHeader(rb.getString("cs.amountDifference"));  //amount difference
			newReportColumns[3].setDisplayTotal(0); 
			newReportColumns[3].setColumnLevel(1);	
			newReportColumns[3].setColumnWidth(20);

			newReportColumns[4] = new ReportColumnVO();
			newReportColumns[4].setColumnId(5); 
			newReportColumns[4].setColumnName(rb.getString("cs."+prevMonthName)+" Month Amount");
			newReportColumns[4].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[4].setTableName("a");
			newReportColumns[4].setColumnHeader(rb.getString("cs.prvs")+" "+rb.getString("cs.prevMontAmount")+"("+rb.getString("cs."+prevMonthName)+" "+year+")");   // prev months amount
			newReportColumns[4].setParentColumnId(4);
			newReportColumns[4].setDisplayTotal(0); 
			newReportColumns[4].setColumnLevel(2);	
			newReportColumns[4].setColumnWidth(10);

			newReportColumns[5] = new ReportColumnVO();
			newReportColumns[5].setColumnId(6); 
			newReportColumns[5].setColumnName(rb.getString("cs."+monthName)+" Month Amount");
			newReportColumns[5].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[5].setTableName("a");
			newReportColumns[5].setColumnHeader(rb.getString("cs.current")+" "+rb.getString("cs.currentMonthAmount")+"("+rb.getString("cs."+monthName)+" "+year+")");  //currenet months amount
			newReportColumns[5].setParentColumnId(4);
			newReportColumns[5].setDisplayTotal(0); 
			newReportColumns[5].setColumnLevel(2);	
			newReportColumns[5].setColumnWidth(10);

			newReportColumns[6] = new ReportColumnVO();
			newReportColumns[6].setColumnId(7); 
			newReportColumns[6].setColumnName("Diffrence");
			newReportColumns[6].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[6].setTableName("a");
			newReportColumns[6].setColumnHeader(rb.getString("cs.difference")); //difference
			newReportColumns[6].setDisplayTotal(0);  
			newReportColumns[6].setColumnLevel(1);	
			newReportColumns[6].setColumnWidth(10);

			newReportColumns[7] = new ReportColumnVO();
			newReportColumns[7].setColumnId(8); 
			newReportColumns[7].setColumnName("Authority Letter No. and Date");
			newReportColumns[7].setDataType(10);
			//newReportColumns[i].setAlignment();
			newReportColumns[7].setTableName("a");
			newReportColumns[7].setColumnHeader(rb.getString("cs.authorityNoAndDate"));		//authority letter no. and date	
			newReportColumns[7].setDisplayTotal(0);
			newReportColumns[7].setColumnLevel(1);	
			newReportColumns[7].setColumnWidth(15);

			report.setReportColumns(newReportColumns);
			report.initializeDynamicTreeModel();
			report.initializeTreeModel();
			report.setReportName("");



			ArrayList row = new ArrayList();
			HrPayPaybill prevMonthObj = null;
			HrPayPaybill monthObj = null;
			HrEisEmpMst hrEisEmpMstObj= new HrEisEmpMst();
			long empID = 0;
			ArrayList innerData = null;
			ArrayList innerRow =null;
			TabularData tdata = null;
			ArrayList orderdataList = null;

			Iterator it = null;
			Map currMonthHrEisEmpMstMap = null;
			Map prevMonthHrEisEmpMstMap = null;
			ArrayList newEmpList = null;	

			CmnLocationMst cmnLocMst = (CmnLocationMst) gen.read(locationId);

			long parentLocId = cmnLocMst.getParentLocId();

			PayBillDAOImpl pbDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
			Map monthList = pbDao.getBillData1(locationId, billNo, month, year,parentLocId);
			Map prevMonthList = pbDao.getBillData1(locationId, billNo, prevMonth, prevYear,parentLocId);

			long currMonthTotal = 0;
			long prevMonthTotal = 0;
			long totalDifference = 0;
			long currMonthNetAmount = 0;
			long prevMonthNetAmount = 0;
			long currMonthGrossAmount = 0;
			long prevMonthGrossAmount = 0;
			long currMonthTotalDedu = 0;
			long preMonthTotalDedu = 0;
			currMonthHrEisEmpMstMap = (Map)monthList.get("HrEisEmpMst");
			prevMonthHrEisEmpMstMap = (Map)prevMonthList.get("HrEisEmpMst");
			Map desigMap = (Map)monthList.get("desigMap");
			Map preDesigMap = (Map)prevMonthList.get("desigMap");
			boolean empAttchedOrNot = true;

			if(currMonthHrEisEmpMstMap != null && currMonthHrEisEmpMstMap.size() > 0 ){

				currMonthNetAmount = (Long)monthList.get("netAmount");
				logger.info(" currMonthNetAmountv  "+currMonthNetAmount);
				currMonthGrossAmount = (Long)monthList.get("grossAmount");
				logger.info("currMonthGrossAmount "+currMonthGrossAmount);
				currMonthTotalDedu =  currMonthGrossAmount - currMonthNetAmount;
				logger.info("currMonthTotalDedu "+currMonthTotalDedu);
				it = currMonthHrEisEmpMstMap.keySet().iterator();	
				newEmpList = new ArrayList();

				while(it.hasNext()){					
					Object obj = it.next();			
					empID = Long.parseLong(obj.toString());		
					if( prevMonthHrEisEmpMstMap != null && prevMonthHrEisEmpMstMap.size() > 0){

						prevMonthNetAmount = (Long) prevMonthList.get("netAmount");
						logger.info("prevMonthNetAmount  "+	prevMonthNetAmount);
						prevMonthGrossAmount = (Long) prevMonthList.get("grossAmount");
						logger.info("prevMonthGrossAmount  "+	prevMonthGrossAmount);
						preMonthTotalDedu =  prevMonthGrossAmount - prevMonthNetAmount;
						logger.info("preMonthTotalDedu  "+	preMonthTotalDedu);

						if(prevMonthHrEisEmpMstMap.containsKey(empID)){
							prevMonthObj = (HrPayPaybill)prevMonthHrEisEmpMstMap.get(empID);
							monthObj = (HrPayPaybill)currMonthHrEisEmpMstMap.get(empID);	

							//if(monthObj.getNetTotal() != prevMonthObj.getNetTotal())
							//{


							if(prevMonthObj.getBasic0101() != monthObj.getBasic0101()){
								row= new ArrayList();
								row.add(diffCount); // sr no.

								dataStyle = new StyledData();
								dataStyle.setStyles(leftAlign);
								dataStyle.setColspan(1);
								dataStyle.setData(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "
										+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "
										+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname()
										+"("+desigMap.get(monthObj.getOrgPostMst().getPostId())+")");
								row.add(dataStyle);
								dataStyle = null;


								dataStyle = new StyledData();
								dataStyle.setStyles(leftAlign);
								dataStyle.setColspan(1);
								dataStyle.setData("Basic Pay");
								row.add(dataStyle);
								dataStyle = null;
								/*innerData = new ArrayList();
									innerRow = new ArrayList();								
									innerRow.add(prevMonthObj.getBasic0101());
									innerData.add(innerRow);
									tdata = new TabularData(innerData);
									tdata.setStyles(rightAlign);
									row.add(tdata);

									innerData = new ArrayList();
									innerRow = new ArrayList();
									innerRow.add(monthObj.getBasic0101());
									innerData.add(innerRow);
									tdata = new TabularData(innerData);
									tdata.setStyles(rightAlign);	
									row.add(tdata);*/

								dataStyle = new StyledData();
								dataStyle.setStyles(rightAlign);
								dataStyle.setData(prevMonthObj.getBasic0101());
								row.add(dataStyle);
								dataStyle = null;

								dataStyle = new StyledData();
								dataStyle.setStyles(rightAlign);
								dataStyle.setData(monthObj.getBasic0101());
								row.add(dataStyle);
								dataStyle = null;

								dataStyle = new StyledData();
								dataStyle.setStyles(rightAlign);
								dataStyle.setData(new Double(monthObj.getBasic0101() - prevMonthObj.getBasic0101()));
								row.add(dataStyle);
								dataStyle = null;

								row.add("");
								dataList.add(row);
								row = null;
								currMonthTotal += monthObj.getBasic0101();
								prevMonthTotal += prevMonthObj.getBasic0101();
								totalDifference += (monthObj.getBasic0101() - prevMonthObj.getBasic0101());
								diffCount++;
							}


							if(edpAllowList!= null)
								for(int j=0;j<edpAllowList.size();j++)
								{


									row= new ArrayList();
									String methodName = "getAllow"+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode();
									//logger.info("edp code is "+edpAllowList.get(j).getRltBillTypeEdp().getEdpCode()+" allow name "+edpAllowList.get(j).getRltBillTypeEdp().getEdpDesc());

									try
									{	
										innerRow = new ArrayList();
										innerData = new ArrayList();
										Method method = cls.getMethod(methodName,null);
										double prevamount = (Double)method.invoke(prevMonthObj, null);
										double amount = (Double)method.invoke(monthObj, null);
										double difference = amount - prevamount ;


										method = null;
										//logger.info(" allow name "+edpAllowList.get(j).getRltBillTypeEdp().getEdpDesc()+" amount is "+amount);
										innerRow.add(prevamount);
										innerData.add(innerRow);
										innerRow = null;

										innerRow= new ArrayList();
										innerRow.add(amount);
										innerData.add(innerRow);
										innerRow = null;

										if(prevamount != amount){
											//row.add(diffCount); // sr no.

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(diffCount);
											row.add(dataStyle);
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(leftAlign);
											dataStyle.setData(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+
													monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+
													monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname()
													+"("+desigMap.get(monthObj.getOrgPostMst().getPostId())+")");
											dataStyle.setColspan(1);											

											row.add(dataStyle);//emp name
											dataStyle = null;


											dataStyle = new StyledData();
											dataStyle.setStyles(leftAlign);
											dataStyle.setData(edpAllowList.get(j).getRltBillTypeEdp().getEdpShortName());
											row.add(dataStyle); //allow name
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(prevamount));
											row.add(dataStyle);    //previous allow amount
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(amount));
											row.add(dataStyle);    // allow amount
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(amount) - new Double(prevamount));
											row.add(dataStyle);    // allow difference
											dataStyle = null;


											row.add("");


											dataList.add(row);
											currMonthTotal += amount;
											prevMonthTotal += prevamount;
											totalDifference += difference;
											diffCount++;
											row = null;
										}
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
							if(edpDeducList != null)
								for(int j=0;j<edpDeducList.size();j++)
								{
									innerData = new ArrayList();
									innerData = new ArrayList();
									row= new ArrayList();

									String methodName = "getDeduc"+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode();

									try
									{
										Method method = cls.getMethod(methodName,null);
										double prevamount = (Double)method.invoke(prevMonthObj, null);
										double amount = (Double)method.invoke(monthObj, null);
										double difference = prevamount -  amount  ;

										method = null;
										innerRow= new ArrayList();
										//logger.info("deduc name "+edpDeducList.get(j).getRltBillTypeEdp().getEdpCode()+" deduc value "+amount);
										innerRow.add(prevamount);
										innerData.add(innerRow);
										innerRow= new ArrayList();
										innerRow.add(amount);
										innerData.add(innerRow);

										if(prevamount != amount){

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(diffCount);
											row.add(dataStyle);     // sr no.
											dataStyle = null;
											row.add(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+
													monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+
													monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname()
													+"("+desigMap.get(monthObj.getOrgPostMst().getPostId())+")");//emp name

											row.add(edpDeducList.get(j).getRltBillTypeEdp().getEdpShortName()); //allow name

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(prevamount));
											row.add(dataStyle);    //previous allow amount
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(amount));
											row.add(dataStyle);      // allow amount
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(difference);
											row.add(dataStyle);    // allow difference
											dataStyle = null;

											row.add("");

											dataList.add(row);
											currMonthTotal += amount;
											prevMonthTotal += prevamount;
											totalDifference += difference;											
											diffCount++;
											row = null;
										}


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
							if(edpLoanList != null)
								for(int j=0;j<edpLoanList.size();j++)
								{
									try
									{
										String methodName ="";
										//= "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
										if(edpLoanList.get(j).getCmnLookupId()==2500136)
										{
											methodName = "getAdv"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
										}
										
										else if (edpLoanList.get(j).getCmnLookupId()==2500137 && edpLoanList.get(j).getRltBillTypeEdp().getExpRcpRec().equals("INT"))
										{
											methodName = "getLoanInt"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
										}
										else if(edpLoanList.get(j).getCmnLookupId()==2500137)
										{
											methodName = "getLoan"+edpLoanList.get(j).getRltBillTypeEdp().getEdpCode();
										}
										

										innerRow= new ArrayList();
										innerData = new ArrayList();

										row= new ArrayList();
										Method method = cls.getMethod(methodName,null);
										double prevamount = (Double)method.invoke(prevMonthObj, null);
										double amount = (Double)method.invoke(monthObj, null);
										double difference = prevamount - amount  ;
										method = null;

										innerRow.add(prevamount);
										innerData.add(innerRow);
										innerRow= new ArrayList();
										innerRow.add(amount);
										innerData.add(innerRow);

										if(prevamount != amount){

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(diffCount);
											row.add(dataStyle);     // sr no.
											dataStyle = null;

											row.add(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+
													monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+
													monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname()
													+"("+desigMap.get(monthObj.getOrgPostMst().getPostId())+")"); // emp name
											row.add(edpLoanList.get(j).getRltBillTypeEdp().getEdpShortName()); //allow name

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(prevamount));
											row.add(dataStyle);      //previous allow amount
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(new Double(amount));
											row.add(dataStyle);       // allow amount
											dataStyle = null;

											dataStyle = new StyledData();
											dataStyle.setStyles(rightAlign);
											dataStyle.setData(difference);
											row.add(dataStyle);     // allow difference
											dataStyle = null;

											row.add("");

											currMonthTotal += amount;
											prevMonthTotal += prevamount;
											totalDifference += difference;
											dataList.add(row);
											row = null;
											diffCount++;
										}

										methodName = null;
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
							//}	
						} 
						else {
							monthObj = (HrPayPaybill)currMonthHrEisEmpMstMap.get( empID);
							newEmpList.add(monthObj);
						}
					}
					else {
						monthObj = (HrPayPaybill)currMonthHrEisEmpMstMap.get(empID);
						newEmpList.add(monthObj);
					}

				}
			}				
			else 
				empAttchedOrNot = false;
			//new attached employees
			int i = 0;
			if(newEmpList != null && newEmpList.size() >0){
				for(int count = 0; count < newEmpList.size(); count++){
					Object obj = newEmpList.get(count);
					monthObj = (HrPayPaybill)obj;					
					if(i == 0){

						/*row= new ArrayList();								
						row.add( new PageBreak());
						dataList.add(row);*/

						row= new ArrayList();						
						dataStyle = new StyledData();
						dataStyle.setStyles(centerboldStyleVO);
						dataStyle.setColspan(7);
						dataStyle.setData("Employee Attached");
						row.add(dataStyle);
						dataList.add(row);
					}	

					row= new ArrayList();

					dataStyle = new StyledData();
					dataStyle.setStyles(rightAlign);	
					dataStyle.setData(diffCount);
					row.add(dataStyle); // sr no.					
					dataStyle = null;

					dataStyle = new StyledData();
					dataStyle.setStyles(leftAlign);					
					dataStyle.setData(monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "
							+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "
							+monthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname()
							+"("+desigMap.get(monthObj.getOrgPostMst().getPostId())+")");

					row.add(dataStyle);
					dataStyle = null;

					dataStyle = new StyledData();
					dataStyle.setStyles(leftAlign);
					dataStyle.setData("Net Pay");
					row.add(dataStyle);
					dataStyle = null;

					dataStyle = new StyledData();
					dataStyle.setStyles(rightAlign);
					dataStyle.setData("0");
					row.add(dataStyle);					
					dataStyle = null;

					/*innerData = new ArrayList();
					innerRow = new ArrayList();
					innerRow.add(monthObj.getNetTotal());
					innerData.add(innerRow);
					tdata = new TabularData(innerData);
					tdata.setStyles(redColorVO);	
					row.add(tdata);	*/

					dataStyle = new StyledData();
					dataStyle.setStyles(rightAlign);
					dataStyle.setData(monthObj.getNetTotal());
					row.add(dataStyle);
					dataStyle = null;

					dataStyle = new StyledData();
					dataStyle.setStyles(rightAlign);
					dataStyle.setData(monthObj.getNetTotal());
					row.add(dataStyle);
					dataStyle = null;

					row.add("");	

					dataList.add(row);
					currMonthTotal += monthObj.getNetTotal();
					prevMonthTotal += 0;
					totalDifference += monthObj.getNetTotal();
					diffCount++;
					i++;
				}
			}

			//for detached employee..
			i = 0;
			if(prevMonthHrEisEmpMstMap != null && prevMonthHrEisEmpMstMap.size() > 0){
				it = prevMonthHrEisEmpMstMap.keySet().iterator();			
				while(it.hasNext()){
					Object obj = it.next();	
					empID = Long.parseLong(obj.toString());	
					empAttchedOrNot = true;
					if(currMonthHrEisEmpMstMap != null)
						if(!currMonthHrEisEmpMstMap.containsKey(empID)){		
							//dettched employees	
							if(i == 0){
								row = new ArrayList();							
								dataStyle = new StyledData();
								dataStyle.setStyles(centerboldStyleVO);
								dataStyle.setColspan(7);
								dataStyle.setData("Employee Dettached");
								row.add(dataStyle);
								dataStyle = null;
								dataList.add(row);
								row =null;
								i++;

							}
							prevMonthObj = (HrPayPaybill)prevMonthHrEisEmpMstMap.get(empID);							
							row= new ArrayList();

							dataStyle = new StyledData();
							dataStyle.setStyles(rightAlign);	
							dataStyle.setData(diffCount);
							row.add(dataStyle); // sr no.	
							dataStyle = null;

							dataStyle = new StyledData();
							dataStyle.setStyles(leftAlign);		
							dataStyle.setData(prevMonthObj.getHrEisEmpMst().getOrgEmpMst().getEmpLname()+" "+
									prevMonthObj.getHrEisEmpMst().getOrgEmpMst().getEmpFname()+" "+
									prevMonthObj.getHrEisEmpMst().getOrgEmpMst().getEmpMname()
									+"("+preDesigMap.get(prevMonthObj.getOrgPostMst().getPostId())+")");
							row.add(dataStyle);
							dataStyle = null;

							dataStyle = new StyledData();
							dataStyle.setStyles(leftAlign);		
							dataStyle.setData("Net Pay");
							row.add(dataStyle);
							dataStyle = null;

							dataStyle = new StyledData();
							dataStyle.setStyles(rightAlign);		
							dataStyle.setData(prevMonthObj.getNetTotal());
							row.add(dataStyle);
							dataStyle = null;

							dataStyle = new StyledData();
							dataStyle.setStyles(rightAlign);		
							dataStyle.setData("0");
							row.add(dataStyle);					
							dataStyle = null;

							dataStyle = new StyledData();
							dataStyle.setStyles(rightAlign);		
							dataStyle.setData((0 - prevMonthObj.getNetTotal()));
							row.add(dataStyle);
							dataStyle = null;

							row.add("");

							currMonthTotal += 0;
							prevMonthTotal += prevMonthObj.getNetTotal();
							totalDifference += (0 - prevMonthObj.getNetTotal());
							dataList.add(row);
							diffCount++;

						}
				}
			}

			if(!empAttchedOrNot){
				row = new ArrayList();				
				dataStyle = new StyledData();
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setColspan(7);
				dataStyle.setData("Employees not found");
				row.add(dataStyle);
				dataStyle = null;
				dataList.add(row);
				row=null;
				return dataList;
			}
			if(diffCount == 1){
				row = new ArrayList();				
				dataStyle = new StyledData();
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setColspan(7);
				dataStyle.setData("No Change Found");
				row.add(dataStyle);
				dataStyle = null;
				dataList.add(row);
				row=null;
			}
			else {

				row= new ArrayList();
				row.add(new PageBreak());
				dataList.add(row);
				row = null;

				row= new ArrayList();
				row.add("");
				row.add("");
				row.add("");

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setData(rb.getString("CS.totalAmount")); //total amount
				row.add(dataStyle);
				dataStyle =null;

				row.add("");

				dataStyle = new StyledData();
				dataStyle.setStyles(rightAlign);	
				dataStyle.setData(totalDifference);
				row.add(dataStyle); 
				dataStyle =null;

				row.add("");
				row.add("");

				dataList.add(row);
				row = null;

				row= new ArrayList();
				row.add("");
				row.add("");
				row.add("");	

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setData(rb.getString("cs.month")+" "+rb.getString("cs."+monthName)+" "+year+" "+rb.getString("cs.currNetAMount")); // curr months net amount 
				row.add(dataStyle);	

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setStyles(rightAlign);	
				dataStyle.setData(currMonthNetAmount);
				row.add(dataStyle); 

				row.add("");
				row.add("");
				dataList.add(row);
				row = null; 

				row= new ArrayList();
				row.add("");
				row.add("");
				row.add("");	

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setData(rb.getString("cs.month")+" "+rb.getString("cs."+prevMonthName)+" "+year+" "+rb.getString("cs.preNetAmount")); // prev months net amount
				row.add(dataStyle);	
				dataStyle = null;

				row.add("");

				dataStyle = new StyledData();
				dataStyle.setStyles(rightAlign);	
				dataStyle.setData(prevMonthNetAmount);
				row.add(dataStyle);	
				dataStyle = null;

				row.add("");
				row.add("");
				dataList.add(row);
				row = null;

				row= new ArrayList();
				row.add("");
				row.add("");
				row.add("");	

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setData(rb.getString("cs.diffreneceAmount")); //amount difference  
				row.add(dataStyle);					
				dataStyle = null;
				row.add("");

				dataStyle = new StyledData();
				dataStyle.setStyles(rightAlign);	
				dataStyle.setData(currMonthNetAmount - prevMonthNetAmount);
				row.add(dataStyle);	
				dataStyle = null;			
				row.add("");
				row.add("");
				dataList.add(row);
				row = null;

				row= new ArrayList();

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.b"));
				row.add(dataStyle);				
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.totalGrossAmount"));   //total amount
				row.add(dataStyle);	
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.totalDeductions"));   // total deductions 
				row.add(dataStyle);			
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(1);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.totalNetAmount"));   //Total Net Amount
				row.add(dataStyle);			
				dataList.add(row);
				dataStyle = null;
				row = null;

				row= new ArrayList();

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs."+monthName)+" "+rb.getString("cs.prevMonthBill"));
				row.add(dataStyle);				
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+currMonthGrossAmount);
				row.add(dataStyle);	
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+currMonthTotalDedu);
				row.add(dataStyle);
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(1);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+currMonthNetAmount);
				row.add(dataStyle);
				dataStyle = null;

				dataList.add(row);
				row = null;

				row= new ArrayList();

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs."+prevMonthName)+rb.getString("cs.currMonthBill"));
				row.add(dataStyle);				
				dataStyle = null;

				row.add("");

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+prevMonthGrossAmount);
				row.add(dataStyle);	
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+preMonthTotalDedu);
				row.add(dataStyle);
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(1);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+prevMonthNetAmount);
				row.add(dataStyle);

				dataStyle = null;

				dataList.add(row);
				row = null;

				row= new ArrayList();

				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.billDiffernceAmount"));
				row.add(dataStyle);				
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+(currMonthGrossAmount - prevMonthGrossAmount));
				row.add(dataStyle);	
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(2);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+(currMonthTotalDedu - preMonthTotalDedu));
				row.add(dataStyle);
				dataStyle = null;

				row.add("");
				dataStyle = new StyledData();
				dataStyle.setColspan(1);
				dataStyle.setStyles(centerboldStyleVO);
				dataStyle.setData(rb.getString("cs.rs")+(currMonthNetAmount - prevMonthNetAmount));
				row.add(dataStyle);
				dataStyle = null;

				dataList.add(row);
				//row = null;

				innerData = new ArrayList();
				innerRow = new ArrayList();
				innerRow.add("0");
				innerData.add(innerRow);
				tdata = new TabularData(innerData);
				tdata.setStyles(redColorVO);	
				row.add(tdata);


				/*ReportAttributeVO ravo = new ReportAttributeVO();
				ravo = new ReportAttributeVO();
				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_LEFT); 
				ravo.setAttributeValue("(*) Required documetns have acttached.<br>");
				report.addReportAttributeItem(ravo); 

				ravo = new ReportAttributeVO();
				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_LEFT); 
				ravo.setAttributeValue("    I hereby state that, I have verified all changes and they is no discrepancy.<br>");
				report.addReportAttributeItem(ravo);

				ravo = new ReportAttributeVO();
				ravo.setAttributeType(IReportConstants.ATTRIB_OTHER);
				ravo.setLocation(IReportConstants.LOCATION_FOOTER);
				ravo.setAlignment(IReportConstants.ALIGN_RIGHT); 
				ravo.setAttributeValue("<br>Officer Sign");
				report.addReportAttributeItem(ravo);
				 */

				row= new ArrayList();
				dataStyle = new StyledData();
				dataStyle.setColspan(7);
				dataStyle.setStyles(leftboldStyleVO);
				dataStyle.setData(rb.getString("cs.note"));
				row.add(dataStyle);
				dataList.add(row);
				dataStyle = null;
				row = null;
				row= new ArrayList();
				dataStyle = new StyledData();
				dataStyle.setColspan(7);
				dataStyle.setStyles(leftboldStyleVO);
				dataStyle.setData(rb.getString("cs.certificate1")+rb.getString("cs."+prevMonthName)+" "+rb.getString("cs.certificate2"));
				row.add(dataStyle);
				dataList.add(row);
				dataStyle = null;
				row = null;

				row= new ArrayList();
				dataStyle = new StyledData();
				dataStyle.setColspan(7);
				dataStyle.setStyles(leftboldStyleVO);
				dataStyle.setData("");
				row.add(dataStyle);
				dataList.add(row);
				dataStyle = null;
				row = null;

				row= new ArrayList();
				dataStyle = new StyledData();
				dataStyle.setColspan(7);
				dataStyle.setStyles(rightboldStyleVO);
				dataStyle.setData(rb.getString("cs.ddoSign"));
				row.add(dataStyle);

				dataList.add(row);
				dataStyle = null;
				row = null;

			}
			startTime = new Date();
			logger.error("endTime "+startTime.toString());
		}

		catch(Exception e)
		{
			logger.error("Error is: "+ e);
			e.printStackTrace();
		}


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
		if(  report.getReportCode().equals("8000199") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("8000199") )

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
