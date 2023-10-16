package com.tcs.sgv.pensionproc.service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Style;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CommonDAO;
import com.tcs.sgv.common.dao.CommonDAOImpl;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.pensionpay.report.PensionpayQueryDAO;
import com.tcs.sgv.pensionpay.report.PensionpayQueryDAOImpl;
import com.tcs.sgv.pensionproc.dao.UploadReportDaoImpl;

public class UploadReportServiceImpl extends DefaultReportDataFinder implements ReportDataFinder {
	Long gLngPostId = null;
	private static final long serialVersionUID = 1L;
	static URI serverURI;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for LangId */
	Long gLngLangId = null;

	/* Global Variable for EmpId */
	Long gLngEmpId = null;

	/* Global Variable for Location Id */
	String gStrLocId = null;

	/* Global Variable for DB Id */
	Long gLngDBId = null;

	/* Global Variable for Current Date */
	Date gCurDate = null;
	Date gCurrDate = null;
	/* Global Variable for Location Code */
	Long gDbId = null;
	String gStrLocShortName = null;
	private Log gLogger = LogFactory.getLog(getClass());
	Long gLngLocId = null;
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	private UploadReportDaoImpl gObjRptQueryDAO = null;
	Long gPostId=null;
	Map lMapSeriesHeadCode = null;
	Session ghibSession = null;

	private Date gDate = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private Log logger = LogFactory.getLog(getClass()); /* LOGGER */

	private void setSessionInfo(Map<String, Object> inputMap) {
		request = (HttpServletRequest) inputMap.get("requestObj");
		request.getSession();
		gLngLangId = SessionHelper.getLangId(inputMap);
		gLngPostId = SessionHelper.getPostId(inputMap);
		gLngUserId = SessionHelper.getUserId(inputMap);
		gStrLocCode = SessionHelper.getLocationCode(inputMap);
		gDate = DBUtility.getCurrentDateFromDB();
		gDbId = SessionHelper.getDbId(inputMap);
		gStrLocShortName = SessionHelper.getLocationVO(inputMap).getLocShortName();
		serv = (ServiceLocator) inputMap.get("serviceLocator");
	}

	public Collection findReportData(ReportVO lObjReport, Object criteria) throws ReportException {

		List lLstDataList = new ArrayList();
		Map lMapRequestAttributes = null;
		Map lMapSessionAttributes = null;
		LoginDetails lObjLoginVO = null;
		try {
			lMapRequestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			lMapSessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			lObjLoginVO = (LoginDetails) lMapSessionAttributes.get("loginDetails");
			serviceLocator = (ServiceLocator) lMapRequestAttributes.get("serviceLocator");
			gObjSessionFactory = serviceLocator.getSessionFactorySlave();
			gStrLocCode = lObjLoginVO.getLocation().getLocationCode();
			gLngLangId = lObjLoginVO.getLangId();
			gPostId=lObjLoginVO.getLoggedInPost().getPostId();



			System.out.println("3::::::"+lObjLoginVO.getLoggedInPost().getPostId());
			ghibSession = gObjSessionFactory.getCurrentSession();
			gObjRptQueryDAO = new UploadReportDaoImpl(null, gObjSessionFactory);

			gObjRptQueryDAO.setReportHeaderAndFooter(lObjReport, lObjLoginVO);

			if (lObjReport.getReportCode().equals("365023")) {
				lLstDataList = getUploadReport(lObjReport, gStrLocCode);
			}
			if (lObjReport.getReportCode().equals("365024")) {
				lLstDataList = getUploadDtlsReport(lObjReport, gStrLocCode);
			}
		} catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		}
		return lLstDataList;
	}


	public List getUploadReport(ReportVO lObjReport, String lStrLocationCode) {


		List dataList = new ArrayList();
		List ListOfPPO = new ArrayList();
		List ppodetails = new ArrayList();
		List rowList=null;
		String DelvId;
		SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int lIntSrNoCnt=1;
		StringBuilder lSbHeaderVal = new StringBuilder();
		try {


			DelvId=(String)lObjReport.getParameterValue("DelvId");
			//DelvId=StringUtility.getParameter("DelvId", request);
			lSbHeaderVal.append("Uploaded File Report \r\n");
			CommonDAO lObjCommonDAO = new CommonDAOImpl(gObjSessionFactory);
			String lStrLocationName=null;
			try {
				lStrLocationName = lObjCommonDAO.getTreasuryName(gLngLangId, Long.parseLong(lStrLocationCode));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(lStrLocationName.contains (","))
			{
				String[] lStrLocationName1=lStrLocationName.split(",");
				lStrLocationName=lStrLocationName1[1]+","+lStrLocationName1[0];
				lSbHeaderVal.append(lStrLocationName1[1]+"\r\n");
				lSbHeaderVal.append(lStrLocationName1[0]+",Maharashtra \r\n");
			}
			else
				lSbHeaderVal.append(lStrLocationName+"\r\n");


			lObjReport.setReportName(lSbHeaderVal.toString());


			System.out.println("DelvId::::::::::::"+DelvId);


			String urlPrefix = "ifms.htm?actionFlag=reportService&reportCode=365024&action=generateReport&DelvId="+DelvId;

			ppodetails=gObjRptQueryDAO.getPPOList(DelvId);
			System.out.println("ppodetails size::::"+ppodetails.size());
			Iterator it1 = ppodetails.iterator();
			while (it1.hasNext()) {
				Object[] tuple = (Object[]) it1.next();
				rowList = new ArrayList();
				{
					//sr no
					rowList.add(lIntSrNoCnt);

					//Name of treasury 
					if (tuple[0] != null) {
						rowList.add(tuple[0].toString());
					} else {
						rowList.add("");
					}
					//  date of uploading PPO 
					if (tuple[1] != null) {
						rowList.add(tuple[1].toString());
					}
					else
					{
						rowList.add("");
					}

					//  No of PPO uploaded 
					/*	if (tuple[2] != null) {
					rowList.add(tuple[2].toString());
				}
				else
				{
					rowList.add("");
				}
					 */	
					String locId=tuple[2].toString();
					//generate emo
					urlPrefix=urlPrefix+"&LocId="+locId+"&DirectReport=TRUE&displayOK=TRUE";

					System.out.println("tuple[2].toString()"+locId);
					System.out.println("urlPrefix"+urlPrefix);
					List temp=gObjRptQueryDAO.getPPODtlsList(DelvId,locId);
					rowList.add(new URLData(temp.size(),urlPrefix ));


					urlPrefix="ifms.htm?actionFlag=reportService&reportCode=365024&action=generateReport&DelvId="+DelvId;

					dataList.add(rowList);
					++lIntSrNoCnt;
				}
			}
			/*	String url1="ifms.htm?actionFlag=loadAttchAgFile&elementId=365479";
				Style[] lObjStyleVO =  new StyleVO[lObjReport.getStyleList().length];
				lObjStyleVO = lObjReport.getStyleList();
				for (Integer lInt = 0; lInt < lObjReport.getStyleList().length; lInt++) {
					if ((lObjStyleVO[lInt]).getStyleId() == 26) {
						( lObjStyleVO[lInt]).setStyleValue(url1);
					}
				}

			 */

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		return dataList;
	}
	public List getUploadDtlsReport(ReportVO lObjReport, String lStrLocationCode) {


		List dataList = new ArrayList();
		List ListOfPPO = new ArrayList();
		List ppodetails = new ArrayList();
		List rowList=null;
		String DelvId="",LocId="";
		SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int lIntSrNoCnt=1;
		StringBuilder lSbHeaderVal = new StringBuilder();
		try {

			DelvId=(String)lObjReport.getParameterValue("DelvId");
			LocId=(String)lObjReport.getParameterValue("LocId");
			//DelvId=StringUtility.getParameter("DelvId", request);
			lSbHeaderVal.append("Uploaded PPO Details \r\n");
			CommonDAO lObjCommonDAO = new CommonDAOImpl(gObjSessionFactory);
			String lStrLocationName=null;
			try {
				lStrLocationName = lObjCommonDAO.getTreasuryName(gLngLangId, Long.parseLong(lStrLocationCode));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(lStrLocationName.contains (","))
			{
				String[] lStrLocationName1=lStrLocationName.split(",");
				lStrLocationName=lStrLocationName1[1]+","+lStrLocationName1[0];
				lSbHeaderVal.append(lStrLocationName1[1]+"\r\n");
				lSbHeaderVal.append(lStrLocationName1[0]+",Maharashtra \r\n");
			}
			else
				lSbHeaderVal.append(lStrLocationName+"\r\n");


			lObjReport.setReportName(lSbHeaderVal.toString());


			System.out.println("DelvId::::::::::::"+DelvId);
			System.out.println("LocId::::::::::::"+LocId);


			ppodetails=gObjRptQueryDAO.getPPODtlsList(DelvId, LocId);
			System.out.println("ppodetails size::::"+ppodetails.size());
			Iterator it1 = ppodetails.iterator();
			while (it1.hasNext()) {
				Object[] tuple = (Object[]) it1.next();
				rowList = new ArrayList();
				{
					//sr no
					rowList.add(lIntSrNoCnt);

					//PPO NO 
					if (tuple[0] != null) {
						rowList.add(tuple[0].toString());
					} else {
						rowList.add("");
					}
					//  Name of pensioner 
					if (tuple[1] != null) {
						rowList.add(tuple[1].toString());
					}
					else
					{
						rowList.add("");
					}

					//  Type of pension ||

					if (tuple[2] != null) {
						rowList.add(tuple[2].toString());
					}
					else
					{
						rowList.add("");
					}

					// Treasury name ||


					if (tuple[3] != null) {
						rowList.add(tuple[3].toString());
					}
					else
					{
						rowList.add("");
					}

					dataList.add(rowList);
					++lIntSrNoCnt;
				}
			}



		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}




		return dataList;
	}

}
