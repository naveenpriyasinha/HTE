package com.tcs.sgv.nps.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.dao.reports.ReportsDAOImpl;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.TabularData;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.TokenNumberDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;


public class DTORegistrationDetails extends DefaultReportDataFinder implements ReportDataFinder
{
	

	ResourceBundle locStrsBundle;
	private static Logger logger = Logger.getLogger(DTORegistrationDetails.class );


	
	
	private  StyleVO[] selfCloseVO=null;          


	final String CheckIfNull(Object lStra)
	{
		String lStrb="";
		if( lStra != null )
		{
			lStrb = (((String)lStra).trim());

		}
		return lStrb;
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Collection findReportData( ReportVO report, Object criteria ) throws ReportException
	{

		
		int finalpagesize=20;
	

		Map requestKeys = (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap = (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap = (Map)serviceMap.get("baseLoginMap");	
		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");
		String locationName=locationVO.getLocName();
		long locationId=locationVO.getLocId();
		String locationNameincaps=locationName.toUpperCase();
		


		ArrayList DataList = new ArrayList();   

		try   
		{

			logger.info("inside report----");
			Map requestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			ServiceLocator serviceLocator = (ServiceLocator) requestAttributes.get("serviceLocator");
			SessionFactory sessionFactory = serviceLocator.getSessionFactory();
			Session hibSession = sessionFactory.getCurrentSession();
			
		    ServiceLocator serv = (ServiceLocator)requestKeys.get("serviceLocator");


			StyleVO[] boldStyleVO = new StyleVO[1];
			boldStyleVO[0] = new StyleVO();
			boldStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
			boldStyleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLDER); 
			//StyledData dataStyle = null;

			StyleVO[] colorStyleVO = new StyleVO[1];
			colorStyleVO[0] = new StyleVO();
			colorStyleVO[0].setStyleId(IReportConstants.STYLE_FONT_COLOR);
			colorStyleVO[0].setStyleValue("blue");
			selfCloseVO = new StyleVO[1];
			selfCloseVO[0] = new StyleVO();
			selfCloseVO[0].setStyleId(IReportConstants.REPORT_PAGE_OK_BTN_URL);
			selfCloseVO[0].setStyleValue("javascript:self.close()"); 


			


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


			if (report.getReportCode().equals("9000173"))
		      {

				String portType="";

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
			
					l++;
				} 

				String DeptName=locationNameincaps;
				PayBillDAOImpl payBillDAOImpl =new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				
				TokenNumberDAOImpl objtoken = new TokenNumberDAOImpl(null,serv.getSessionFactory()); 
				String ddocode ="";
				String TanNo="";
				String billTypeCheck="";
				long locactionId=Long.parseLong(baseLoginMap.get("locationId").toString());
				logger.info("locactionId**********" + locactionId);
				
				long locIdForBill=locactionId;
				
				logger.info("locIdForBill**********" + locIdForBill);
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
				logger.info("locactionId**********" + locactionId);

				DeptName=payBillDAOImpl.getOffice(locationId);
				String TresuryName ="";
				String TresuryCode ="";
				List TreasuryList = payBillDAOImpl.getTreasuryCodeAndTreasuryName(ddocode);
				logger.info("locactionId**********" + locactionId);
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



				logger.info("locactionId**********" + locactionId);

				String Title = "DTO Registration Report";
			

				String Tan=TanNo;
				String deptHeader ="";

				ArrayList styleList = new ArrayList();
				ArrayList stData = new ArrayList();

				StyledData styledHeader = new StyledData (deptHeader,headerStyleVo);
				styledHeader.setColspan(2);
				stData.add(styledHeader);
				stData.add("");
				String Treasury =TresuryCode;
				styleList.add(stData);
				ArrayList r = new ArrayList();
				r.add(new StyledData("Treasury : "+TresuryName+"("+Treasury+")",rightHead));
				styleList.add(r);  

				ArrayList r2= new ArrayList();
				r2.add(new StyledData("Name of the Office : "+DeptName+"("+ddocode+")	",leftHeader));	
				r2.add(new StyledData(" ",rightHead));
				styleList.add(r2);

				
			
				
				/*TabularData tData  = new TabularData(styleList);				
				tData.addStyle(IReportConstants.STYLE_FONT_ALIGNMENT, IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
				tData.addStyle(IReportConstants.BORDER, "No");
				tData.addStyle(IReportConstants.SHOW_REPORT_NAME, IReportConstants.VALUE_NO);
				report.setAdditionalHeader(tData);
*/

			

			
				Calendar cal2 = Calendar.getInstance();
				

				logger.info("locactionId**********" + locactionId);
				cal2 = null;
				Calendar calSupplBill = Calendar.getInstance();
				PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
				//SgvcFinYearMst finYrMst = payDao.getFinYrInfo(finYrDate,1l);
				ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
				
				Map brokenMap=new HashMap();
				StringBuffer sb = new StringBuffer();
				
//				sb.append("SELECT ddo2.DDO_CODE,reg.DTO_OFFICE,reg.ASSOCIATED_DTO_REG_NO,reg.dto_user_id from RLT_ZP_DDO_MAP zp");
//				sb.append("inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.FINAL_DDO_CODE");
//				sb.append("inner join mst_ddo_reg reg on reg.ddo_code = zp.ZP_DDO_CODE");
//				sb.append("inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)");
//				sb.append("inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)");
//				sb.append("where  substr(ddo2.ddo_code,1,4)= 3201");
//				
				sb.append("select ddo.DDO_CODE,ddo.DDO_NAME,ddo_office.OFF_NAME,reg.DTO_REG_NO,reg.DDO_REG_NO , reg.DTA_OFFICE_NAME  from ifms.ORG_DDO_MST as ddo ");
				sb.append("inner join  ifms.MST_DCPS_DDO_OFFICE as ddo_office on ddo_office.DDO_CODE=ddo.DDO_CODE ");
				sb.append("inner join ifms.RLT_ZP_DDO_MAP zp on ddo.DDO_CODE = zp.zp_DDO_CODE ");
				sb.append("left join ifms.MST_DTO_REG reg on  reg.ddo_code = zp.zp_DDO_CODE WHERE zp.STATUS=1 and zp.REPT_DDO_CODE  ='"+ddocode+"' ");					
				
				
				logger.info("***Query for DTO Registration details**" +sb);
				 
			   Query sqlQuery = hibSession.createSQLQuery(sb.toString());
				List RowList=sqlQuery.list();
				
				if(RowList.size()!=0)
				{
					int cnt=1;

					Iterator itr = RowList.iterator();
					String ddoCode = "";
					String ddoName="";
					String ddoOfficeName="";
					String dtoRegNumber ="";
					String ddoRegNumber = "";
					String dtaOfficeName="";
					
					/*double Installment3diff = 0;
					double Installment4diff = 0;
					double Installment5diff = 0;*/
					int pageCnt=1;
				

					while (itr.hasNext())
					{
						Object[] rowList = (Object[]) itr.next();
						
					    ddoCode = (rowList[0]!=null?rowList[0]:"").toString();
					    ddoName = (rowList[1]!=null?rowList[1]:"").toString();
					    ddoOfficeName = (rowList[2]!=null?rowList[2]:"").toString();
					    dtoRegNumber = (rowList[3]!=null?rowList[3]:"").toString();
					    ddoRegNumber = (rowList[4]!=null?rowList[4]:"").toString();
					    dtaOfficeName = (rowList[5]!=null?rowList[5]:"").toString();
					  

					   
					    logger.info("***DDO Code**" +ddoCode );
					    logger.info("***DDO Name**" +ddoName );
					    logger.info("***DDO Office Name**" +ddoOfficeName );
					    logger.info("***DTO Reg Number**" +dtoRegNumber );
					    logger.info("***DDO Reg Number**" +ddoRegNumber );
					    logger.info("***DTA Office Name**" +dtaOfficeName );
					   

						if(!brokenMap.containsKey(ddoCode)){
							
							 logger.info(" inside if loop " );
							ArrayList row = new ArrayList();


							row.add(cnt);
							row.add(ddoCode);
						    row.add(ddoName);
							row.add(ddoOfficeName);
							row.add(dtoRegNumber);
							row.add(ddoRegNumber);
							row.add(dtaOfficeName);
							
							/*row.add(Installment3diff);
							row.add(Installment4diff);
							row.add(Installment5diff);
							*/
							logger.info(" inside if loop "  + row );
							String noOfRec=CheckIfNull(report.getParameterValue("No of Records"));
							if(!noOfRec.equalsIgnoreCase("")&&!noOfRec.equalsIgnoreCase("-1"))
							{
								
								finalpagesize=Integer.parseInt(noOfRec);
							}

							DataList.add(row);
							if((cnt%finalpagesize)==0) 
							{
								pageCnt++;
								row = new ArrayList();
								row.add(new PageBreak());
								row.add("Data");
								DataList.add(row); 

								StyledData dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								//dataStyle1.setData(totalSevenPcbasic);
								row.add(dataStyle1);

								dataStyle1 = new StyledData();
								dataStyle1.setStyles(boldStyleVO);
								dataStyle1.setData(pageCnt);                   
								row.add(dataStyle1);//2


							}
							cnt++;
						}
					}
				}
			
				
				
				return DataList;
			}
		}
		catch(Exception e)
		{
			logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
			logger.error("Printing StackTrace");
			logger.error("Error is: "+ e.getMessage());
		}
			
		return DataList;

	}

	public ReportVO getUserReportVO( ReportVO report, Object criteria )
	throws ReportException
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
		if(  report.getReportCode().equals("9000173") )

		{            
			report.setParameterValue("Year",yr);
			report.setParameterValue("Month",month);
			report.setParameterValue("Department",locationId+"");
			report.setParameterValue("billTypePara",resourceBundle.getString("paybillTypeId"));
		}

		if(  report.getReportCode().equals("9000173") )

		{            
			report.setParameterValue("Department",locationId+"");
		}
		return report;
	}
}