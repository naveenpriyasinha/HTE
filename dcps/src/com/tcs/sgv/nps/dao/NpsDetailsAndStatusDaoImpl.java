package com.tcs.sgv.nps.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.DateUtility;
import com.tcs.sgv.common.valuebeans.reports.PageBreak;
import com.tcs.sgv.common.valuebeans.reports.ReportAttributeVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.nps.dao.NpsDetailsAndStatusDaoImpl;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class NpsDetailsAndStatusDaoImpl extends GenericDaoHibernateImpl{  
	SessionFactory gObjSessionFactory = null;

	Session ghibSession = ServiceLocator.getServiceLocator().getSessionFactorySlave().getCurrentSession();

	private static final Logger gLogger = Logger.getLogger("PensionpayReports");

	/** Global Variable for Resource Bundle */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");

	private ResourceBundle gObjRsrcBndleCaseLables = ResourceBundle.getBundle("resources/pensionpay/PensionLabels");

	/** Global Variable for Simple Date Format */
	private SimpleDateFormat gObjDtFormat = new SimpleDateFormat("dd/MM/yyyy");

	private static ResourceBundle bundleConst = ResourceBundle.getBundle("resources/pensionpay/PensionConstants");

	public NpsDetailsAndStatusDaoImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		gObjSessionFactory = sessionFactory;
		setSessionFactory(sessionFactory);
	}

	public void setReportHeaderAndFooter(ReportVO lObjReport,
			LoginDetails lObjLoginVO) {
		StringBuilder lSbHeaderVal = null;
		try {
			String lStrLocCode = lObjLoginVO.getLocation().getLocationCode().trim();
			String lStrDdoCode=  lObjLoginVO.getLocation().getLocationCode().trim();
			lSbHeaderVal = getReportHeader(lStrLocCode);

			ReportAttributeVO[] lArrrReportAttributeVO = new ReportAttributeVO[2];
			lArrrReportAttributeVO[0] = new ReportAttributeVO();
			lArrrReportAttributeVO[0].setAttributeType(IReportConstants.ATTRIB_OTHER);
			lArrrReportAttributeVO[0].setLocation(IReportConstants.LOCATION_HEADER);
			lArrrReportAttributeVO[0].setAlignment(IReportConstants.ALIGN_RIGHT);
			lArrrReportAttributeVO[0].setAttributeValue("Date : " + DateUtility.getCurrentDateTime());

			lArrrReportAttributeVO[1] = new ReportAttributeVO();
			lArrrReportAttributeVO[1].setAttributeType(IReportConstants.ATTRIB_OTHER);
			lArrrReportAttributeVO[1].setLocation(IReportConstants.LOCATION_FOOTER);
			lArrrReportAttributeVO[1].setAlignment(IReportConstants.ALIGN_RIGHT);

			String lStrEmpName = ((lObjLoginVO.getEmployee().getEmpPrefix() != null) ? lObjLoginVO.getEmployee().getEmpPrefix() + " " : "")
					+ ((lObjLoginVO.getEmployee().getEmpFname() != null) ? lObjLoginVO.getEmployee().getEmpFname() + " " : "")
					+ ((lObjLoginVO.getEmployee().getEmpMname() != null) ? lObjLoginVO.getEmployee().getEmpMname() + " " : "")
					+ ((lObjLoginVO.getEmployee().getEmpLname() != null) ? lObjLoginVO.getEmployee().getEmpLname() + " " : "");

			lArrrReportAttributeVO[1].setAttributeValue("Prepared By : " + lStrEmpName);

			String lStrRptName = lObjReport.getReportName();
			lObjReport.setReportAttributes(lArrrReportAttributeVO);
			// for drill down report
			if (lStrRptName.indexOf(lSbHeaderVal.toString()) == -1) {

				lSbHeaderVal.append(lObjReport.getReportName() + "\r\n" + "\r\n"); // get
																					// original
				lObjReport.setReportName(lSbHeaderVal.toString());
			} 

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}

	private StringBuilder getReportHeader(String lStrLocCode) {
		StringBuilder lSBQuery = new StringBuilder();
		StringBuilder lSbHeaderVal = new StringBuilder();
		try {
			lSBQuery.append(" SELECT loc.loc_name,concat(concat(concat(concat(concat(concat(concat(concat(loc.loc_addr_1,' '),loc.loc_addr_2),' '),city.city_name),' '),dist.district_name),' '),state.state_name) , cntc.office_phone,cntc.email ");
			lSBQuery.append(" FROM cmn_city_mst city,cmn_district_mst dist,cmn_state_mst state  ,cmn_location_mst loc left outer join cmn_contact_mst cntc  on cntc.loc_id = loc.loc_id  ");
			lSBQuery.append(" WHERE city.city_id = loc.loc_city_id AND dist.district_id = city.district_id AND state.state_id = dist.state_id  ");
			lSBQuery.append(" AND loc.department_id =  100003 AND loc.location_code = :locationCode ");
			SQLQuery Query = ghibSession.createSQLQuery(lSBQuery.toString());
			Query.setString("locationCode", lStrLocCode);
			List lLstFinal = Query.list();
			if (lLstFinal != null && !lLstFinal.isEmpty()) {
				Iterator it = lLstFinal.iterator();
				while (it.hasNext()) {
					Object[] tuple = (Object[]) it.next();
					if (tuple[0] != null) {
						lSbHeaderVal.append(tuple[0].toString() + "\r\n"); // Office
																			// Name
					}
					if (tuple[1] != null) {
						lSbHeaderVal.append(tuple[1] + "\r\n"); // Office
																// Address
					}
					if (tuple[2] != null) {
						lSbHeaderVal.append("Ph: " + tuple[2].toString() + "\r\n"); // Office
																					// Phone
																					// no.
					}
					if (tuple[3] != null) {
						lSbHeaderVal.append("E-mail: " + tuple[3].toString() + "\r\n" + "\r\n" + "\r\n"); // Office
																											// E-mail
					}
				}
			}

		} catch (Exception e) {

			logger.error("Error is : " + e, e);
			throw (e);
		}
		return lSbHeaderVal;
	}

	public List getNPSStatusReport(String lStrLocCode) {
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		String urlPrefix = "";
		
        Map brokenMap=new HashMap();
		
		ArrayList DataList = new ArrayList();
		
		try{
		StringBuilder SBQuery = new StringBuilder();
		
		
		SBQuery.append("select distinct(reg.dto_reg_no),reg.ASSOCIATED_DTA_REG_NO,reg.dto_office,reg.dta_office_name from org_ddo_mst ddo ");
		SBQuery.append("inner join RLT_ZP_DDO_MAP map on map.REPT_DDO_CODE = ddo.DDO_CODE ");
		SBQuery.append("inner join mst_dto_reg reg on reg.ddo_code = map.ZP_DDO_CODE ");
		SBQuery.append("where ddo.LOCATION_CODE = '"+lStrLocCode+"'");
		
		Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			
			lLstResult = lQuery.list();
			logger.info("Query1 is *********"+SBQuery.toString());
			int i=1;
			lObjIterator = lLstResult.iterator();
			List RowList=lQuery.list();
		
			if(RowList.size()!=0)
			{
				int cnt=1;

				Iterator itr = RowList.iterator();
				String locationCode="";
				String dtoRegNo = "";
				String dtaRegNo ="";
				String dtoOfficeName = "";
				String dtaOfficeName="";
				
				
				int pageCnt=1;
			

				while (itr.hasNext())
				{
					Object[] rowList = (Object[]) itr.next();
					
				    dtoRegNo = (rowList[0]!=null?rowList[0]:"").toString();
				    dtaRegNo = (rowList[1]!=null?rowList[1]:"").toString();
				    dtoOfficeName = (rowList[2]!=null?rowList[2]:"").toString();
				    dtaOfficeName = (rowList[3]!=null?rowList[3]:"").toString();
				  

				   
				    logger.info("***DTO Reg No**" +dtoRegNo );
				    logger.info("***DTA Reg No**" +dtaRegNo );
				    logger.info("***DTO Office Name**" +dtoOfficeName );
				    logger.info("***DTA Office Name**" +dtaOfficeName );
				   

					if(!brokenMap.containsKey(locationCode)){
						
						 logger.info(" inside if loop " );
						ArrayList row = new ArrayList();


						row.add(cnt);
						row.add(dtoRegNo);
					    row.add(dtaRegNo);
						row.add(dtoOfficeName);
						
						logger.info(" inside if loop "  + row );
						
						DataList.add(row);
						
						cnt++;
					}
				}
				return DataList;
				
			}

		}
		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		       } 
			
			return lArrListOuter1;
	}

	
	public List getCMPUTRReport(String forYear, String forMonth, Long gPostId) {
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		String urlPrefix = "";
		Map brokenMap=new HashMap();
		
		ArrayList DataList = new ArrayList();   

		
		try{

			
		urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=80000943&action=generateReport&DirectReport=TRUE&displayOK=FALSE" ;
		StringBuffer lSBQuery = new StringBuffer();
		lSBQuery.append("SELECT temp.DDO_code,sum(temp.emp_count),sum(temp.net_amt),sum(temp.gross_amt) FROM (SELECT ddo.DDO_CODE,"); 
		lSBQuery.append("case when pay.EMP_ID is not null then count(*) end as emp_count,sum(MST.NET_AMT) as net_amt,"); 
	    lSBQuery.append("sum(MST.GROSS_AMT)as gross_amt, SUM(PAY.DCPS+PAY.DCPS_DA+PAY.DCPS_DELAY+PAY.DCPS_PAY+PAY.NPS_EMPLR_CONTRI_DED+PAY.JANJULGISARR) as NPS_Amt "); 
	    lSBQuery.append("FROM HR_PAY_PAYBILL PAY INNER JOIN CONSOLIDATED_BILL_MPG MPG ON MPG.PAYBILL_ID = PAY.PAYBILL_GRP_ID "); 
	    lSBQuery.append("INNER JOIN CONSOLIDATED_BILL_MST MST ON MST.CONS_BILL_ID = MPG.CONS_BILL_ID "); 
	    lSBQuery.append("INNER JOIN ORG_DDO_MST DDO ON DDO.POST_ID = MST.POST_ID "); 
	    lSBQuery.append("INNER JOIN (SELECT REPT_DDO_CODE,RLT.REPT_DDO_POST_ID FROM RLT_ZP_DDO_MAP RLT WHERE REPT_DDO_POST_ID = '"+gPostId+"' "); 
	    lSBQuery.append("GROUP BY REPT_DDO_CODE, RLT.REPT_DDO_POST_ID ) TMP ON TMP.REPT_DDO_POST_ID = DDO.POST_ID "); 
	    lSBQuery.append("where MST.MONTH = '"+forMonth+"' AND MST.YEAR = '"+forYear+"' AND MST.STATUS in (1, 5) "); 
	    lSBQuery.append("GROUP BY ddo.DDO_CODE,pay.EMP_ID) as temp group by  temp.ddo_code "); 
	
		Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
		List RowList=lQuery.list();

		if(RowList.size()!=0)
		{
			int cnt=1;

			Iterator itr = RowList.iterator();
			String ddoCode = "";
			String netAmt ="";
			String grossAmt = "";
			String npsAmt="";
			
			
			int pageCnt=1;
		

			while (itr.hasNext())
			{
				Object[] rowList = (Object[]) itr.next();
				
			    ddoCode = (rowList[0]!=null?rowList[0]:"").toString();
			    netAmt = (rowList[1]!=null?rowList[1]:"").toString();
			    grossAmt = (rowList[2]!=null?rowList[2]:"").toString();
			    npsAmt = (rowList[3]!=null?rowList[3]:"").toString();
			  

			   
			    logger.info("***DDO Code**" +ddoCode );
			    logger.info("***Net Amount**" +netAmt );
			    logger.info("***Gross Amount**" +grossAmt );
			    logger.info("***NPS Amount**" +npsAmt );
			   

				if(!brokenMap.containsKey(ddoCode)){
					
					 logger.info(" inside if loop " );
					ArrayList row = new ArrayList();


					row.add(cnt);
					row.add(ddoCode);
				    row.add(netAmt);
					row.add(grossAmt);
					row.add(npsAmt);
					
					
					logger.info(" inside if loop "  + row );
					

					DataList.add(row);
					
					cnt++;
				}
			}
		}
	

		
		return DataList;
	
}
catch(Exception e)
{
	logger.error("Error in ResourceMoniteringDAO" + e.getMessage());
	logger.error("Printing StackTrace");
	logger.error("Error is: "+ e.getMessage());
}
	
return DataList;
}

	public List getEmpDtls(String lStrLocCode, String flag,String ddocode) {
		  
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		
		try{
		StringBuilder SBQuery = new StringBuilder();
		
			SBQuery.append("SELECT EMP.EMP_NAME,odm.DDO_NAME,DTO.DDO_REG_NO, EMP.DCPS_ID,EMP.SEVARTH_ID,DTO.DTO_OFFICE,EMP.PPAN,EMP.PRAN_NO, ");
			SBQuery.append("to_char(EMP.DOB,'dd/mm/yyyy') as DOB,to_char(EMP.DOJ,'dd/mm/yyyy') as DOJ  FROM MST_DCPS_EMP EMP  ");
			SBQuery.append("INNER JOIN RLT_ZP_DDO_MAP RLT ON RLT.ZP_DDO_CODE = EMP.DDO_CODE ");
			SBQuery.append("INNER JOIN ORG_DDO_MST odm on rlt.REPT_DDO_CODE=odm.DDO_CODE  ");
			SBQuery.append("INNER JOIN MST_DTO_REG DTO ON RLT.ZP_DDO_CODE=DTO.DDO_CODE ");
			SBQuery.append("WHERE RLT.STATUS IN (0,1) AND ZP_STATUS = 10 AND EMP.EMP_SERVEND_DT > SYSDATE AND EMP.DCPS_OR_GPF = 'Y' and RLT.REPT_DDO_CODE='"+ddocode+"'");
			
			if(flag.equals("Pran")){
				SBQuery.append("AND EMP.PRAN_NO IS NOT NULL ");
			}
			if(flag.equals("noPran")){
				SBQuery.append("AND EMP.PRAN_NO IS  NULL ");
			}
			
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			
			lLstResult = lQuery.list();
			logger.info("Query1 is *********"+SBQuery.toString());
			int i=1;
			lObjIterator = lLstResult.iterator();
			
		
			 
			while (lObjIterator.hasNext()) {
				Object[] lArrObj = (Object[]) lObjIterator.next();
				List lArrListInner=new ArrayList();
				
				//Sr No
				if (lArrObj[0] != null) {
					lArrListInner.add( i);
				} else {
					lArrListInner.add("");
				}
				//Name of Employee 
				if (lArrObj[0] != null) {
					lArrListInner.add(lArrObj[0].toString());
				} else {
					lArrListInner.add("");
				}
				//DDO Name
				if (lArrObj[1] != null) {
					lArrListInner.add(lArrObj[1].toString());
				} else {
					lArrListInner.add("");
				}
				//DDO Reg. No
				if (lArrObj[2] != null) {
					lArrListInner.add(lArrObj[2].toString());
				} else {
					lArrListInner.add("");
				}
				//DCPS Id
				if (lArrObj[3] != null) {
					lArrListInner.add(lArrObj[3].toString());
				} else {
					lArrListInner.add("");
				}
				//Sevaarth Id.
				if (lArrObj[4] != null) {
					lArrListInner.add(lArrObj[4].toString());
				} else {
					lArrListInner.add("");
				}
				
				//DTO Name 
				if (lArrObj[5] != null) {
					lArrListInner.add(lArrObj[5].toString());
				} else {
					lArrListInner.add("");
				}
				
				//PPAN
				if (lArrObj[6] != null) {
					lArrListInner.add(lArrObj[6].toString());
				} else {
					lArrListInner.add("");
				}
				
				//PRAN
				if (lArrObj[7] != null) {
					lArrListInner.add(lArrObj[7].toString());
				} else {
					lArrListInner.add("");
				}
				
				//DOB
				if (lArrObj[8] != null) {
					lArrListInner.add(lArrObj[8].toString());
				} else {
					lArrListInner.add("");
				}
				
				//DOJ
				if (lArrObj[9] != null) {
					lArrListInner.add(lArrObj[9].toString());
				} else {
					lArrListInner.add("");
				}
				
				lArrListOuter1.add(lArrListInner);
				lArrListInner=null;
				lArrObj=null;
				i++;
		}

		}
		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		       } 
			
			return lArrListOuter1;
		
	}

	public List getDdoRegDtls(String lStrLocCode, String ddocode) {
		
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		
        Map brokenMap=new HashMap();
		
		ArrayList DataList = new ArrayList();

		try{
		StringBuilder lSBQuery = new StringBuilder();
		
		lSBQuery.append("SELECT DTO_REG_NO,DTO_OFFICE ,count(*) as totalEmp FROM ifms.MST_DTO_REG as dtoDDO inner join ifms.RLT_ZP_DDO_MAP as rltddo on dtoDDO.DDO_CODE=rltddo.ZP_DDO_CODE ");
		lSBQuery.append("where  rltddo.REPT_DDO_CODE='"+ddocode+"' group by DTO_REG_NO,DTO_OFFICE");

			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			List RowList=lQuery.list();
			
			lLstResult = lQuery.list();
			logger.info("Query1 is *********"+lSBQuery.toString());
			int i=1;
			lObjIterator = lLstResult.iterator();
			String urlPrefix = "hrms.htm?actionFlag=reportService&reportCode=80000944&action=generateReport&DirectReport=TRUE&displayOK=FALSE";
			

			while (lObjIterator.hasNext()) {
				Object[] lArrObj = (Object[]) lObjIterator.next();
				List lArrListInner=new ArrayList();
				
				//Sr No
				if (lArrObj[0] != null) {
					lArrListInner.add( i);
				} else {
					lArrListInner.add("");
				}
				//DTO Reg No
				if (lArrObj[0] != null) {
					lArrListInner.add(lArrObj[0].toString());
				} else {
					lArrListInner.add("");
				}
				//DTO Reg oFFICE
				if (lArrObj[1] != null) {
					lArrListInner.add(lArrObj[1].toString());
				} else {
					lArrListInner.add("");
				}
				//No. of Level 2 DDO
				if (lArrObj[2] != null) {
					lArrListInner.add(new URLData(lArrObj[2].toString(),urlPrefix + "&DdoCode="+ddocode+"&flag=NoReg"));
				} else {
					lArrListInner.add("");
				}
				
				lArrListOuter1.add(lArrListInner);
				lArrListInner=null;
				lArrObj=null;
				i++;
		}

		}

		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		       } 
			
			return lArrListOuter1;
			
	}

	public List getPranRegDtls(String lStrLocCode,String ddocode) {
		
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		
		try{
		StringBuilder SBQuery = new StringBuilder();
		
			SBQuery.append("SELECT DTO.ASSOCIATED_DTA_REG_NO, DTO.DTA_OFFICE_NAME, COUNT(EMP.DCPS_EMP_ID) AS TOTAL_DCPS, COUNT(EMP.PRAN_NO) AS HAVING_PRAN, ");
			SBQuery.append("COUNT(CASE WHEN EMP.PRAN_NO IS NULL THEN 1 ELSE NULL END) AS NO_PRAN FROM MST_DCPS_EMP EMP  ");
			SBQuery.append("INNER JOIN RLT_ZP_DDO_MAP RLT ON RLT.ZP_DDO_CODE = EMP.DDO_CODE ");
			SBQuery.append("INNER JOIN MST_DTO_REG DTO on rlt.ZP_DDO_CODE=dto.DDO_CODE  ");
			SBQuery.append("WHERE RLT.STATUS IN (0,1) AND ZP_STATUS = 10 AND EMP.EMP_SERVEND_DT > SYSDATE AND EMP.DCPS_OR_GPF = 'Y' and rlt.REPT_DDO_CODE='"+ddocode+"'  ");
			SBQuery.append("GROUP BY DTO.ASSOCIATED_DTA_REG_NO, DTO.DTA_OFFICE_NAME");
			
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			
			lLstResult = lQuery.list();
			logger.info("Query1 is *********"+SBQuery.toString());
			int i=1;
			lObjIterator = lLstResult.iterator();
			String urlPrefix = "hrms.htm?actionFlag=reportService&reportCode=80000945&action=generateReport&DirectReport=TRUE&displayOK=FALSE";
			
			 
			while (lObjIterator.hasNext()) {
				Object[] lArrObj = (Object[]) lObjIterator.next();
				List lArrListInner=new ArrayList();
				
				//Sr No
				if (lArrObj[0] != null) {
					lArrListInner.add( i);
				} else {
					lArrListInner.add("");
				}
				//Associate DTA Reg No
				if (lArrObj[0] != null) {
					lArrListInner.add(lArrObj[0].toString());
				} else {
					lArrListInner.add("");
				}
				//DTA Office Name
				if (lArrObj[1] != null) {
					lArrListInner.add(lArrObj[1].toString());
				} else {
					lArrListInner.add("");
				}
				//Total DCPS Employee
				if (lArrObj[2] != null) {
					
					lArrListInner.add(new URLData(lArrObj[2].toString(),urlPrefix + "&LocCode="+ddocode+"&flag=Tot"));
				} else {
					lArrListInner.add("");
				}
				//No of DCPS Employee having PRAn
				if (lArrObj[3] != null) {
					lArrListInner.add(new URLData(lArrObj[3].toString(),urlPrefix + "&LocCode="+ddocode+"&flag=Pran"));
				} else {
					lArrListInner.add("");
				}
			//No of DCPS Employee not having PRAN
				if (lArrObj[4] != null) {
					lArrListInner.add(new URLData(lArrObj[4].toString(),urlPrefix + "&LocCode="+ddocode+"&flag=noPran"));
				} else {
					lArrListInner.add("");
				}
				lArrListOuter1.add(lArrListInner);
				//logger.info("list size is "+lArrListOuter1.size());
				lArrListInner=null;
				lArrObj=null;
				i++;
		}

		}
		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		       } 
			
			return lArrListOuter1;
		
	}


	public String getDdoCode(String gStrLocCode) {
		// TODO Auto-generated method stub
		Session hibSession = getSession();
		
		StringBuilder newQuery = new StringBuilder();


         String ddoCode="";
		
         newQuery.append("SELECT DDO_CODE FROM ORG_DDO_MST where LOCATION_CODE= '"+gStrLocCode+"' ");
         Query query=hibSession.createSQLQuery(newQuery.toString());
         ddoCode=query.uniqueResult().toString();
		
		return ddoCode;
	}

	public List getLevel2DdoDtls(String lStrLocCode, String ddocode) {
		
		ArrayList lArrListOuter1 = new ArrayList();
		List lLstResult= new ArrayList();
		Iterator lObjIterator = null;
		
        Map brokenMap=new HashMap();
		
		ArrayList DataList = new ArrayList(); 
		
		try{
		StringBuilder SBQuery = new StringBuilder();
		
			SBQuery.append("SELECT DTO_REG_NO,DTO_OFFICE,DDO_REG_NO,DDO_OFF_NAME ");
			SBQuery.append("FROM ifms.MST_DTO_REG as dtoDDO inner join ifms.RLT_ZP_DDO_MAP as rltddo on dtoDDO.DDO_CODE=rltddo.ZP_DDO_CODE ");
			SBQuery.append("where rltddo.REPT_DDO_CODE='"+ddocode+"'  group by DTO_REG_NO,DTO_OFFICE,DDO_REG_NO,DDO_OFF_NAME");
		
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());

			lLstResult = lQuery.list();
			logger.info("Query1 is *********"+SBQuery.toString());
			int i=1;
			lObjIterator = lLstResult.iterator();
			
			 
			while (lObjIterator.hasNext()) {
				Object[] lArrObj = (Object[]) lObjIterator.next();
				List lArrListInner=new ArrayList();
				
				//Sr No
				if (lArrObj[0] != null) {
					lArrListInner.add( i);
				} else {
					lArrListInner.add("");
				}
				//DTA Reg No
				if (lArrObj[0] != null) {
					lArrListInner.add(lArrObj[0].toString());
				} else {
					lArrListInner.add("");
				}
				//DTA oFFICE Name
				if (lArrObj[1] != null) {
					lArrListInner.add(lArrObj[1].toString());
				} else {
					lArrListInner.add("");
				}
				//Level2 DDO Code
				if (lArrObj[2] != null) {
					lArrListInner.add(lArrObj[2].toString());
				} else {
					lArrListInner.add("");
				}
				//DDO Name
				if (lArrObj[3] != null) {
					lArrListInner.add(lArrObj[3].toString());
				} else {
					lArrListInner.add("");
				}
				
				lArrListOuter1.add(lArrListInner);
				//logger.info("list size is "+lArrListOuter1.size());
				lArrListInner=null;
				lArrObj=null;
				i++;
		}
			
		}
		catch (Exception e) {
			gLogger.error("error is :" + e, e);
		       } 
			
			return lArrListOuter1;
		
	}
}

