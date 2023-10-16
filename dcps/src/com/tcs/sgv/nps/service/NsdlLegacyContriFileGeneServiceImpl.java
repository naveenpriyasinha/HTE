package com.tcs.sgv.nps.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.InputStreamResource;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.nps.dao.NsdlSrkaNewFileGeneDAOImpl;
import cra.standalone.paosubcontr.PAOFvu;

public class NsdlLegacyContriFileGeneServiceImpl extends ServiceImpl {
	private final Log logger = LogFactory.getLog(getClass());

	private String gStrPostId = null;

	private String gStrUserId = null;

	private String gStrLocale = null;

	private Locale gLclLocale = null;

	private Long gLngLangId = null;

	private Long gLngDBId = null;

	private Date gDtCurDate = null;

	private HttpServletRequest request = null;

	private HttpServletResponse response = null;
	private HttpServletResponse response1 = null;

	private ServiceLocator serv = null;

	private HttpSession session = null;

	Long gLngPostId = null;

	Long gLngUserId = null;

	Date gDtCurrDt = null;

	String gStrLocationCode = null;

	static HashMap sMapUserLoc = new HashMap();

	String gStrUserLocation = null;
	List lstemployee = null;
	private static String OUTPUT_ZIP_FILE = "E:/Akanksha/Agri/";
	private static String OUTPUT_ZIP_Contri_FILE = "E:/Akanksha/Agri/Contribution/";

	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");
	private static ResourceBundle gObjRsrcBndles = ResourceBundle.getBundle("resources/nps/NPSConstants");

	public NsdlLegacyContriFileGeneServiceImpl() {
	}

	private void setSessionInfo(Map inputMap) {
		try {
			response = ((HttpServletResponse) inputMap.get("responseObj"));
			response1 = ((HttpServletResponse) inputMap.get("responseObj"));
			request = ((HttpServletRequest) inputMap.get("requestObj"));
			session = request.getSession();
			serv = ((ServiceLocator) inputMap.get("serviceLocator"));
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		} catch (Exception localException) {
		}
	}

	public ResultObject getLegactFileValidation(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		Long yearId = null;
		Long monthId = null;
		Long lLongEmployeeAmt = Long.valueOf(0L);
		Long lLongEmployerAmt = Long.valueOf(0L);
		Long TotalAmt = Long.valueOf(0L);
		List nsdlDeatils = null;
		String gLocId = "";
		Object[] obj = (Object[]) null;
		try {
			setSessionInfo(inputMap);
			String month = StringUtility.getParameter("cmbMonth", request);
			String year = StringUtility.getParameter("cmbYear", request);
			Calendar cal = Calendar.getInstance();
			String currmonth = new Integer(cal.get(2) + 1).toString();
			logger.info("currmonth*******" + currmonth);
			String curryear = new Integer(cal.get(1)).toString();
			logger.info("curr yeasrrrr***" + curryear);
			Long currentyear = null;
			Long currentmonth = null;
			if (currmonth.equals("1")) {
				// currentmonth = Long.valueOf(12L);
				// currentyear = Long.valueOf(Long.parseLong(curryear) - 1L);
				currentmonth = Long.valueOf(currmonth);
				currentyear = Long.valueOf(curryear);
			} else {
				currentmonth = Long.valueOf(Long.parseLong(currmonth));
				currentyear = Long.valueOf(Long.parseLong(curryear));
			}

			logger.info("currentmonth is *******************" + currentmonth);
			logger.info("currentyear is *******************" + currentyear);

			if ((StringUtility.getParameter("cmbMonth", request) != null)
					&& (!StringUtility.getParameter("cmbMonth", request).equals(""))
					&& (Long.parseLong(StringUtility.getParameter("cmbMonth", request)) != -1L)
					&& (StringUtility.getParameter("cmbYear", request) != null)
					&& (!StringUtility.getParameter("cmbYear", request).equals(""))
					&& (Long.parseLong(StringUtility.getParameter("cmbYear", request)) != -1L)) {

				currentmonth = Long.valueOf(Long.parseLong(StringUtility.getParameter("cmbMonth", request)));
				currentyear = Long.valueOf(Long.parseLong(StringUtility.getParameter("cmbYear", request)));
			}

			NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());

			String trCode = lObjNsdlDAO.getLocationId(gStrLocationCode);
			logger.info("trCode is *******************" + trCode);
			if ((currentyear != null) && (currentyear != null)) {
				nsdlDeatils = lObjNsdlDAO.getAllData(currentyear.toString(), currentmonth.toString(), trCode);
			}
			String filestatusss = "";
			String filenames = "";

			if (nsdlDeatils.size() > 0) {
				Object[] tuple5 = (Object[]) nsdlDeatils.get(0);
				filestatusss = tuple5[4].toString();
				filenames = tuple5[0].toString();
			}

			List lLstYears = lObjNsdlDAO.getFinyear();
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			inputMap.put("selMonth", currentmonth);

			inputMap.put("selYear", currentyear);

			List lLstMonths = lObjDcpsCommonDAO.getMonths();
			inputMap.put("size", Integer.valueOf(nsdlDeatils.size()));
			inputMap.put("nsdlDeatils", nsdlDeatils);

			logger.info("Month and year is " + lLstMonths.size());
			inputMap.put("lLstYears", lLstYears);
			inputMap.put("lLstMonths", lLstMonths);
			resObj.setResultValue(inputMap);
			resObj.setViewName("legacyFileValidate");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject getNewEmpsContri(Map inputMap) throws Exception {
		logger.info("Inside Get getAISEmpsContri--------------------");
		ResultObject resultObject = new ResultObject(0);

		List lstAisType = null;
		String aisType = null;
		String finType = null;
		String billno = null;
		List lstYear = null;
		String treasuryyno = null;
		String createdfile = null;
		List lstAlIndiaSerEmp = null;
		List lstbillNo = null;
		String aisTypeSelected = null;
		String finTypeSelected = null;

		String fromDate = null;
		String toDate = "";
		String locId = "";
		try {
			setSessionInfo(inputMap);
			NsdlSrkaNewFileGeneDAOImpl lObjAlIndSer = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			logger.info("gStrLocationCode-------------------" + gStrLocationCode);

			lstYear = lObjAlIndSer.getFinyeardesc();
			List treasury = lObjAlIndSer.getAllTreasuries();

			inputMap.put("lstYear", lstYear);
			inputMap.put("treasury", treasury);
			Boolean check = Boolean.valueOf(false);
			System.out.println("check" + check);
			String treasuryno = null;
			if ((StringUtility.getParameter("treasno", request) != null)
					&& (StringUtility.getParameter("treasno", request) != "")) {
				treasuryno = StringUtility.getParameter("treasno", request);
				inputMap.put("treasuryno", treasuryno);
			}
			locId = lObjAlIndSer.getLocationId(gStrLocationCode);

			if ((StringUtility.getParameter("treasno", request) != null)
					&& (StringUtility.getParameter("treasno", request) != "")) {
				check = Boolean.valueOf(true);
				treasuryyno = StringUtility.getParameter("treasno", request).trim();
				String extn = StringUtility.getParameter("flag", request).trim();
				logger.info("treasuryyno-------------------" + treasuryyno);
				if (((locId != null) || (locId != "")) && ((treasuryyno != null) || (treasuryyno != ""))) {
					lstAlIndiaSerEmp = lObjAlIndSer.getEmployeeList(locId, treasuryyno);
				}
				Boolean flagg = Boolean.valueOf(true);
				if ((lstAlIndiaSerEmp == null) || (lstAlIndiaSerEmp.size() == 0)) {
					inputMap.put("totalRecordsMstContri", Integer.valueOf(0));
					logger.info("flagg*********" + flagg);
					inputMap.put("flagg", flagg);
				} else {
					inputMap.put("totalRecordsMstContri", Integer.valueOf(lstAlIndiaSerEmp.size()));
					flagg = Boolean.valueOf(false);
					logger.info("flagg" + flagg);
					inputMap.put("flagg", flagg);
				}
				logger.info("lstAlIndiaSerEmp+++++++++++" + lstAlIndiaSerEmp);

				if (lstAlIndiaSerEmp.size() != 0) {
					inputMap.put("VIEWSELECTEMPLIST", lstAlIndiaSerEmp);
					inputMap.put("Empsize", Integer.valueOf(lstAlIndiaSerEmp.size()));
				}
			}

			inputMap.put("check", check);

			resultObject.setViewName("verifyLegacyData");
			resultObject.setResultCode(0);
			resultObject.setResultValue(inputMap);
		} catch (Exception e) {
			resultObject = new ResultObject(-1);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error in load employee lists of SRKA on getSRKAEmpsContri () " + e);
		}
		return resultObject;
	}

	public ResultObject getLegactFileGeneration(Map inputMap) throws Exception {
		logger.info("Inside Get getSRKANewEmpsContriForFile--------------------");
		ResultObject resultObject = new ResultObject(0);

		List lstAisType = null;
		String aisType = null;
		String finType = null;
		String billno = null;
		List lstYear = null;
		String treasuryyno = null;
		String createdfile = null;
		List lstAlIndiaSerEmp = null;
		List lstbillNo = null;
		String aisTypeSelected = null;
		String finTypeSelected = null;

		String fromDate = null;
		String toDate = "";
		String locId = "";
		try {
			setSessionInfo(inputMap);
			NsdlSrkaNewFileGeneDAOImpl lObjAlIndSer = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			logger.info("gStrLocationCode-------------------" + gStrLocationCode);

			lstYear = lObjAlIndSer.getFinyeardesc();
			List treasury = lObjAlIndSer.getAllTreasuries();

			inputMap.put("lstYear", lstYear);
			inputMap.put("treasury", treasury);
			Boolean check = Boolean.valueOf(false);
			System.out.println("check" + check);
			String treasuryno = null;
			if ((StringUtility.getParameter("treasno", request) != null)
					&& (StringUtility.getParameter("treasno", request) != "")) {
				treasuryno = StringUtility.getParameter("treasno", request);
				inputMap.put("treasuryno", treasuryno);
			}
			locId = lObjAlIndSer.getLocationId(gStrLocationCode);

			if ((StringUtility.getParameter("treasno", request) != null)
					&& (StringUtility.getParameter("treasno", request) != "")) {
				check = Boolean.valueOf(true);
				treasuryyno = StringUtility.getParameter("treasno", request).trim();
				String extn = StringUtility.getParameter("flag", request).trim();
				logger.info("treasuryyno-------------------" + treasuryyno);

				if (((locId != null) || (locId != "")) && ((treasuryyno != null) || (treasuryyno != ""))) {
					lstAlIndiaSerEmp = lObjAlIndSer.getEmployeeListNsdl(locId, treasuryyno, gStrLocationCode);
				}
				Boolean flagg = Boolean.valueOf(true);
				if ((lstAlIndiaSerEmp == null) || (lstAlIndiaSerEmp.size() == 0)) {
					inputMap.put("totalRecordsMstContri", Integer.valueOf(0));
					logger.info("flagg*********" + flagg);
					inputMap.put("flagg", flagg);
				} else {
					inputMap.put("totalRecordsMstContri", Integer.valueOf(lstAlIndiaSerEmp.size()));
					flagg = Boolean.valueOf(false);
					logger.info("flagg" + flagg);
					inputMap.put("flagg", flagg);
				}

				logger.info("lstAlIndiaSerEmp+++++++++++" + lstAlIndiaSerEmp);

				if (lstAlIndiaSerEmp.size() != 0) {
					inputMap.put("lstAlIndiaSerEmp", lstAlIndiaSerEmp);
				}
			}

			inputMap.put("check", check);

			resultObject.setViewName("ContributionListNew");
			resultObject.setResultCode(0);
			resultObject.setResultValue(inputMap);
		} catch (Exception e) {
			resultObject = new ResultObject(-1);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
			logger.error("Error in load employee lists of SRKA on getSRKAEmpsContri () " + e);
		}
		return resultObject;
	}

	public ResultObject createNewFilesForSRKANSDL(Map<String, Object> inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
		logger.info("in createTxtFile---------------------- ");

		Long Num = null;
		BufferedReader br = null;

		String empname = null;
		String dcpsid = null;
		String pranno = null;
		String govEmpContri = null;
		String subempContri = null;
		String Contritype = null;

		int countsum = 0;
		String govcontiSum = null;
		String subcontiSum = null;
		String TotalContri = null;
		String TotalContrisum = null;
		String extn = null;
		String aisType = null;

		StringBuilder Strbr = new StringBuilder();
		StringBuilder Strbr1 = new StringBuilder();
		String extnFlag = null;
		String strDDOCode = null;
		String fromDate = null;
		String toDate = null;
		String dtoRegNo = null;
		String ddoRegNo = null;
		String prvDdoReg = "AAA";
		String treasuryyno = null;
		String locId = null;
		String successFlag = "success";
		try {
			setSessionInfo(inputMap);

			treasuryyno = StringUtility.getParameter("treasno", request).trim();
			logger.info("in create method treasuryyno" + treasuryyno);

			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");

			if ((StringUtility.getParameter("treasno", request) != null)
					&& (StringUtility.getParameter("treasno", request) != null)) {
				extn = StringUtility.getParameter("flag", request).trim();
				extnFlag = StringUtility.getParameter("flagFile", request).trim();
			}
			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			strDDOCode = objDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			treasuryyno = StringUtility.getParameter("treasno", request).trim();
			NsdlSrkaNewFileGeneDAOImpl lObjAlIndSer = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			locId = lObjAlIndSer.getLocationId(gStrLocationCode);

			Object[] lyrObj = (Object[]) null;

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year1 = cal.get(1);
			int month = cal.get(2) + 1;
			int day1 = cal.get(5);

			DecimalFormat df = new DecimalFormat("00");
			String month1 = df.format(month);
			System.out.println(month1);
			logger.info("year1   " + year1);
			logger.info("month1   " + month1);

			logger.info("treasuryyno$$$$$$$$$$$$$" + treasuryyno);
			lstemployee = lObjAlIndSer.getEmployeeListNsdl(locId, treasuryyno, gStrLocationCode);

			List nsdlData = null;
			String BatchId = null;
			Long nwbatchId = null;
			String nwTranBatchId = null;
			String[] fyYrsplit = (String[]) null;
			String tranId = null;
			List finalNpdId = null;
			String ddoCode = null;
			String lStrFileName = "";

			if ((lstemployee != null) && (!lstemployee.isEmpty())) {
				String Id = null;
				finalNpdId = new ArrayList();
				for (int k = 0; k < lstemployee.size(); k++) {
					String dcpsContriIdPks = lstemployee.get(k).toString();

					Object[] tuple = (Object[]) lstemployee.get(k);
					Id = tuple[10].toString();
					finalNpdId.add(Id);
				}
			}
			if (lstemployee.size() != 0) {
				BatchId = lObjAlIndSer.getBatchId(locId);
				if ((BatchId == null) || (BatchId.equalsIgnoreCase("0"))) {
					BatchId = locId + month1 + year1 + "001";
					lStrFileName = "R" + locId + month1 + year1 + "001";
				} else {
					lStrFileName = "R".concat(BatchId);
				}

				logger.info("BatchId Is **********" + BatchId);

				logger.info("lStrFileName Is **********" + lStrFileName);

				int totalsize = lstemployee.size();

				if ((lstemployee != null) && (!lstemployee.isEmpty())) {

					int count = 0;
					int i = 2;
					int j = 0;
					int empCount = 1;

					String lEmpTotalContri = lObjAlIndSer.getEmployeeContriTotalList(locId, treasuryyno, strDDOCode);
					String lEmpTotalContriInt = lObjAlIndSer.getEmployeeContriTotalListInterest(locId, treasuryyno,
							strDDOCode);
					// added By Naveen
					String lEmplrTotalContri = lObjAlIndSer.getEmploylerContriTotalList(locId, treasuryyno, strDDOCode);
					String lEmplrTotalContriInt = lObjAlIndSer.getEmploylerContriTotalListInterest(locId, treasuryyno,strDDOCode);
					// ende By Naveen
					if (lEmpTotalContri == null) {
						lEmpTotalContri = "0.0";
					}
					if (lEmpTotalContriInt == null) {
						lEmpTotalContriInt = "0.0";
					}
					String totalEmplyContri = null;
					Double EmpleeContri = null;
					Double EmployeerContri = null; // added By Akanksha

					String totalEmplyerContri = null;
					Double totalEContri = null;
					String intEmpl = null;
					String intEmplr = null;
					String[] intEmp = (String[]) null;
					String[] intEmployee = (String[]) null;
					String[] intEmployer = (String[]) null;
					String[] contriEmployee = (String[]) null;
					String[] emplrContriintEmployer = (String[]) null;
					String[] totalDhsumsplit = (String[]) null;
					String[] totalDhemplrsumsplit = (String[]) null; // added By Akanksha
					Double totalContribution = null;
					Double GovContributionSum = null;
					Double subcontributionSum = null;
					Double TotalContributionsum = null;
					Double totalEmpContribution = Double.valueOf(0);
					Double totalEmplrContribution = Double.valueOf(0);
					String[] overallAmt = (String[]) null;
					String[] totalempoverallAmt = (String[]) null;
					String[] totalemplroverallAmt = (String[]) null;
					Long lLngPkIdForDh = null;
					Long lLngPkIdForBh = null;
					Long lLngPkIdForSd = null;

					if ((lEmpTotalContri != null) && (lEmpTotalContriInt != null)) {

						EmpleeContri = Double
								.valueOf(Double.parseDouble(lEmpTotalContri) + Double.parseDouble(lEmpTotalContriInt));
						// added By Akanksha

						EmployeerContri = Double.valueOf(
								Double.parseDouble(lEmplrTotalContri) + Double.parseDouble(lEmplrTotalContriInt));

						// ended By Akanksha

						totalEmplyContri = nosci(EmpleeContri.doubleValue());
						logger.info("totalEmplyContri*********" + totalEmplyContri);
						// totalEmplyerContri = totalEmplyContri;// commented By AKnskha

						totalEmplyerContri = nosci(EmployeerContri.doubleValue());
						logger.info("totalEmplyContri*********" + totalEmplyerContri);

						// totalEContri = Double.valueOf(Double
						// .parseDouble(totalEmplyerContri) * 2); // commented By Akanksha
						// added By Akanksha
						totalEContri = Double.valueOf(Double.parseDouble(totalEmplyerContri))
								+ Double.valueOf(Double.parseDouble(totalEmplyContri));

						// ended By Akanksha
					}

					String totaloverallAmt = nosci(totalEContri.doubleValue());

					overallAmt = totaloverallAmt.toString().split("\\.");

					if (totaloverallAmt.equals("0")) {
						totaloverallAmt = "0.00";

					}

					else if (overallAmt[0].length() == 1) {
						totaloverallAmt = totaloverallAmt + "0";
						logger.info("totalEmplyContri*********1 " + totalEmplyContri);
					} else if (overallAmt[0].length() > 2) {

						totaloverallAmt = decRoundOff(totaloverallAmt);
					}
					/* END */
					else if (overallAmt[1].length() == 1) {
						totaloverallAmt = totaloverallAmt + "0";

					} else if (overallAmt[1].length() > 2) {
						totaloverallAmt = decRoundOff(totaloverallAmt);
					}

					totalempoverallAmt = totalEmplyContri.toString().split("\\.");

					if (totalEmplyContri.equals("0")) {
						totalEmplyContri = "0.00";

					}

					else if (totalempoverallAmt[0].length() == 1) {
						totalEmplyContri = totalEmplyContri + "0";

					} else if (overallAmt[0].length() > 2) {

						totalEmplyContri = decRoundOff(totalEmplyContri);
					}
					/* END */
					else if (totalempoverallAmt[1].length() == 1) {
						totalEmplyContri = totalEmplyContri + "0";

					} else if (overallAmt[1].length() > 2) {

						totalEmplyContri = decRoundOff(totalEmplyContri);
					}

					totalemplroverallAmt = totalEmplyerContri.toString().split("\\.");

					if (totalEmplyerContri.equals("0")) {
						totalEmplyerContri = "0.00";

					}
					 
					else if (totalemplroverallAmt[0].length() == 1) {
						totalEmplyerContri = totalEmplyerContri + "0";
						logger.info("totalEmplyContri********* 3 " + totalEmplyContri);
					} else if (overallAmt[0].length() > 2) {

						totalEmplyerContri = decRoundOff(totalEmplyerContri);
					}
					/* END */
					else if (totalemplroverallAmt[1].length() == 1) {
						totalEmplyerContri = totalEmplyerContri + "0";
					} else if (overallAmt[1].length() > 2) {

						totalEmplyerContri = decRoundOff(totalEmplyerContri);
					}

					int temp = 0;
					int emprecCount = 0;

					PrintWriter outputfile = this.response.getWriter();
					boolean isContriZero = false;
					boolean isInterestZero = false;
					int totalContriCount = 0;
					int ddoCount = 0;
					for (Iterator it = lstemployee.iterator(); it.hasNext();) {
						count++;
						Object[] lObj = (Object[]) it.next();

						empname = lObj[0] != null ? lObj[0].toString() : "NA";

						dcpsid = lObj[1] != null ? lObj[1].toString() : "";

						pranno = lObj[2] != null ? lObj[2].toString() : "";

						dtoRegNo = lObj[8] != null ? lObj[8].toString() : "";

						ddoRegNo = lObj[9] != null ? lObj[9].toString() : "";

						ddoCode = lObj[11] != null ? lObj[11].toString() : "";

						govEmpContri = lObj[4] != null ? lObj[4].toString() : "";

						logger.info("govEmpContri*****************" + govEmpContri);

						subempContri = lObj[3] != null ? lObj[3].toString() : "";

						intEmpl = lObj[5] != null ? lObj[5].toString() : "";

						intEmplr = lObj[6] != null ? lObj[6].toString() : "";

						String TotalIntContri = null;

						Double totalIntContribution = Double
								.valueOf(Double.parseDouble(intEmpl) + Double.parseDouble(intEmplr));

						TotalIntContri = totalIntContribution.toString();

						intEmp = TotalIntContri.toString().split("\\.");

						if (TotalIntContri.equals("0")) {
							TotalIntContri = "0.00";

						} else if (intEmp[1].length() == 1) {
							TotalIntContri = TotalIntContri + "0";
						}

						intEmployee = intEmpl.toString().split("\\.");
						if ((intEmpl != null) && (!"".equalsIgnoreCase(intEmpl))) {
							if (Double.parseDouble(intEmpl) > 0) {
								isInterestZero = false;
							} else {
								isInterestZero = true;
							}
						}

						if (intEmpl.equals("0")) {
							intEmpl = "0.00";

						} else if (intEmployee.length > 1) {
							if (intEmployee[1].length() == 1) {
								intEmpl = intEmpl + "0";
							}
						} else if (intEmployee.length == 1) {
							intEmpl = intEmpl + ".00";
						}

						intEmployer = intEmplr.toString().split("\\.");

						if ((intEmplr != null) && (!"".equalsIgnoreCase(intEmplr))) {
							if (Double.parseDouble(intEmplr) > 0) {
								isInterestZero = false;
							} else {
								isInterestZero = true;
							}
						}

						if (intEmplr.equals("0")) {
							intEmplr = "0.00";

						} else if (intEmployer.length > 1) {
							if (intEmployer[1].length() == 1) {
								intEmplr = intEmplr + "0";
							}
						} else if (intEmployer.length == 1) {
							intEmplr = intEmplr + ".00";
						}

						contriEmployee = govEmpContri.toString().split("\\.");
						if ((govEmpContri != null) && (!"".equalsIgnoreCase(govEmpContri))) {
							if ((Double.parseDouble(govEmpContri) > 0) || (Double.parseDouble(govEmpContri) < 0)) {
								isContriZero = false;
							} else {
								isContriZero = true;
							}
						}

						if (govEmpContri.equals("0")) {
							govEmpContri = "0.00";

						} else if (contriEmployee.length > 1) {
							if (contriEmployee[1].length() == 1) {
								govEmpContri = govEmpContri + "0";
							}
						} else if (contriEmployee.length == 1) {
							govEmpContri = govEmpContri + ".00";
						}

						emplrContriintEmployer = subempContri.toString().split("\\.");
						if ((subempContri != null) && (!"".equalsIgnoreCase(subempContri))) {
							if ((Double.parseDouble(subempContri) > 0) || (Double.parseDouble(subempContri) < 0)) {
								isContriZero = false;
							} else {
								isContriZero = true;
							}
						}

						if (subempContri.equals("0")) {
							subempContri = "0.00";

						} else if (emplrContriintEmployer.length > 1) {
							if (emplrContriintEmployer[1].length() == 1) {
								subempContri = subempContri + "0";
							}
						} else if (emplrContriintEmployer.length == 1) {
							subempContri = subempContri + ".00";
						}
						DecimalFormat df1 = new DecimalFormat("0.00");
						totalContribution = Double.valueOf(govEmpContri != null
								? Double.parseDouble(govEmpContri) + Double.parseDouble(subempContri)
								: +Double.parseDouble(subempContri));

						// TotalContri = totalContribution.toString();
						TotalContri = String.format("%.2f", totalContribution);
						logger.info("TotalContri" + TotalContri);

						countsum += count;

						GovContributionSum = Double.valueOf(
								govcontiSum != null ? Double.parseDouble(govcontiSum) + Double.parseDouble(govEmpContri)
										: +Double.parseDouble(govEmpContri));

						govcontiSum = GovContributionSum.toString();

						subcontributionSum = Double.valueOf(
								subcontiSum != null ? Double.parseDouble(subcontiSum) + Double.parseDouble(subempContri)
										: +Double.parseDouble(subempContri));

						subcontiSum = subcontributionSum.toString();

						TotalContributionsum = Double.valueOf(
								govcontiSum != null ? Double.parseDouble(govcontiSum) + Double.parseDouble(subcontiSum)
										: +Double.parseDouble(subcontiSum));

						// TotalContrisum = TotalContributionsum.toString();
						TotalContrisum = String.format("%.2f", TotalContributionsum);

						if (((!prvDdoReg.equals(ddoRegNo)) && (!isContriZero))
								|| ((!prvDdoReg.equals(ddoRegNo)) && (!isInterestZero))) {
							i++;
							j++;
							empCount = 1;
							ddoCount++;
							Strbr.append(i + "^");
							Strbr.append("DH^");
							Strbr.append("1^");
							Strbr.append(j + "^");
							Strbr.append(ddoRegNo + "^");

							String lEmpRowContrDdo = lObjAlIndSer.getEmployeeRecordCountDdoregNsdl(locId, treasuryyno,
									ddoRegNo);
							long rowCnt = Long.valueOf(0L);
							if (lEmpRowContrDdo != null) {
								rowCnt = Long.valueOf(Long.parseLong(lEmpRowContrDdo));
							}

							Strbr.append(rowCnt + "^");

							String lEmpTotalContrDdo = lObjAlIndSer.getEmployeeListDdoregNsdl(locId, treasuryyno,
									ddoRegNo);
							String lEmpTotalContrDdoInt = lObjAlIndSer.getEmployeeListDdoregNsdlInt(locId, treasuryyno,
									ddoRegNo);

							// added By Akanksha
							String lEmplrTotalContrDdo = lObjAlIndSer.getEmploylerContriTotalList(locId, treasuryyno,strDDOCode);
							String lEmplrTotalContrDdoInt = lObjAlIndSer.getEmploylerContriTotalListInterest(locId,
									treasuryyno,strDDOCode);
							// ende By Akanksha
							String totalDhsum = "";
							String totalEmplrhsum = "";
							if (lEmpTotalContrDdo == null) {
								lEmpTotalContrDdo = "0.0";
							}
							if (lEmpTotalContrDdoInt == null) {
								lEmpTotalContrDdoInt = "0.0";
							}

							// added By Akanksha

							if (lEmplrTotalContrDdo == null) {
								lEmplrTotalContrDdo = "0.0";
							}
							if (lEmplrTotalContrDdoInt == null) {
								lEmplrTotalContrDdoInt = "0.0";
							}

							// ended By Akanksha

							if ((lEmpTotalContrDdo != null) && (lEmpTotalContrDdoInt != null)) {
								Double total = Double.valueOf(Double.parseDouble(lEmpTotalContrDdo)
										+ Double.parseDouble(lEmpTotalContrDdoInt));
								lEmpTotalContrDdo = total.toString();
								totalDhsum = lEmpTotalContrDdo;

								Double totalemplr = Double.valueOf(Double.parseDouble(lEmplrTotalContrDdo)
										+ Double.parseDouble(lEmplrTotalContrDdoInt));
								lEmplrTotalContrDdo = totalemplr.toString();
								totalEmplrhsum = lEmplrTotalContrDdo;
							}

							totalDhsumsplit = totalDhsum.toString().split("\\.");

							if (totalDhsum.equals("0")) {
								totalDhsum = "0.00";
							} else if (totalDhsumsplit.length > 1) {
								if (totalDhsumsplit[1].length() == 1) {
									totalDhsum = totalDhsum + "0";
								}
							} else if (totalDhsumsplit.length == 1) {
								totalDhsum = totalDhsum + ".00";
							}

							// added By Akanksha

							totalDhemplrsumsplit = totalEmplrhsum.toString().split("\\.");

							if (totalEmplrhsum.equals("0")) {
								totalEmplrhsum = "0.00";
							} else if (totalDhemplrsumsplit.length > 1) {
								if (totalDhemplrsumsplit[1].length() == 1) {
									totalEmplrhsum = totalEmplrhsum + "0";
								}
							} else if (totalDhemplrsumsplit.length == 1) {
								totalEmplrhsum = totalEmplrhsum + ".00";
							}

							// ended By Akanksha
							String temp2 = nosci(Double.parseDouble(totalEmplrhsum));
							String temp1 = nosci(Double.parseDouble(totalDhsum));

							Strbr.append(decRoundOff(temp2) + "^");
							Strbr.append(decRoundOff(temp1) + "^");
							Strbr.append("^");

							lLngPkIdForDh = IFMSCommonServiceImpl.getNextSeqNum("NSDL_DH_DTLS", inputMap);
							logger.info("lLngPkIdForDh++++++++++++++" + lLngPkIdForDh);

							lObjAlIndSer.insertDHDetails(lLngPkIdForDh, i, "DH", "1", j, ddoRegNo, rowCnt,
									decRoundOff(temp1), decRoundOff(temp2), lStrFileName);

							Strbr.append("\r\n");
							temp++;
							emprecCount++;
							prvDdoReg = ddoRegNo;
						} else if ((!prvDdoReg.equals(ddoRegNo)) && (isContriZero) && (isInterestZero)) {
							temp++;
							emprecCount++;
						} else {
							prvDdoReg = ddoRegNo;
						}

						if ((lstemployee != null) && (!lstemployee.equals(""))) {
							if (!isContriZero) {
								i++;
								Strbr.append(i + "^");
								Strbr.append("SD^");
								Strbr.append("1^");
								Strbr.append(j + "^");
								Strbr.append(empCount + "^");
								Strbr.append(pranno + "^");
								Strbr.append(govEmpContri + "^");
								Strbr.append(subempContri + "^");
								Strbr.append("^");
								Strbr.append(TotalContri + "0" + "^");
								Strbr.append("A^^^");

								if (treasuryyno.equalsIgnoreCase("10001198212")) {
									Strbr.append("Dcps Contribution Phase - 1^");
								} else {
									Strbr.append("Dcps Contribution Phase - 2^");
								}
								Strbr.append("\r\n");

								lLngPkIdForSd = IFMSCommonServiceImpl.getNextSeqNum("NSDL_SD_DTLS", inputMap);
								logger.info("lLngPkIdForSd++++++++++++++" + lLngPkIdForSd);
								lObjAlIndSer.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount, pranno,
										subempContri, govEmpContri, TotalContri
										// + "0", "A^" + month1 + "^"
										, "A^" + month1 + "^" + year1,
										"Dcps Contribution Phase - 1 for " + month1 + "/" + year1, lStrFileName,
										ddoRegNo);

								empCount++;
								totalContriCount++;
							}
							if (!isInterestZero) {
								i++;
								Strbr.append(i + "^");
								Strbr.append("SD^");
								Strbr.append("1^");
								Strbr.append(j + "^");
								Strbr.append(empCount + "^");
								Strbr.append(pranno + "^");
								intEmpl = nosci(Double.parseDouble(intEmpl));
								intEmplr = nosci(Double.parseDouble(intEmplr));
								Strbr.append(decRoundOff(intEmplr) + "^");
								Strbr.append(decRoundOff(intEmpl) + "^");

								Strbr.append("^");
								TotalIntContri = nosci(Double.parseDouble(TotalIntContri));
								Strbr.append(decRoundOff(TotalIntContri) + "^");

								Strbr.append("A^^^");

								if (treasuryyno.equalsIgnoreCase("10001198212")) {
									Strbr.append("Dcps Interest Phase - 1^");
								} else {
									Strbr.append("Dcps Interest Phase - 2^");
								}
								Strbr.append("\r\n");
								totalContriCount++;

								lLngPkIdForSd = IFMSCommonServiceImpl.getNextSeqNum("NSDL_SD_DTLS", inputMap);
								logger.info("lLngPkIdForSd++++++++++++++" + lLngPkIdForSd);
								String array1[] = TotalIntContri.toString().split("\\.");

								/* Added by brijoy 26032019 */
								if (array1[1].length() == 2) {
									lObjAlIndSer.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount, pranno,
											decRoundOff(intEmpl), decRoundOff(intEmplr), TotalIntContri,
											"A^" + month1 + "^" + year1,
											"Dcps Interest Phase - 1 for " + month1 + "/" + year1, lStrFileName,
											ddoRegNo);
								} else {
									lObjAlIndSer.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount, pranno,
											decRoundOff(intEmpl), decRoundOff(intEmplr), TotalIntContri + "0",
											"A^" + month1
											// TotalIntContri , "A^" + month1
													+ "^" + year1,
											"Dcps Interest Phase - 1 for " + month1 + "/" + year1, lStrFileName,
											ddoRegNo);
								} /* Added by brijoy 26032019 */
								empCount++;
							}
						}

						lLngPkIdForBh = IFMSCommonServiceImpl.getNextSeqNum("NSDL_BH_DTLS", inputMap);
						logger.info("lLngPkIdForBh++++++++++++++" + lLngPkIdForBh);
					}

					if (finalNpdId.size() > 0) {
						logger.info("finalNpdId^^^^^^^^^^^^^" + finalNpdId);

						lObjAlIndSer.updateBatchId(BatchId, finalNpdId);
					}

					String lineSeperator = "\r\n";

					String os = System.getProperty("os.name");

					if (os.toLowerCase().indexOf("unix") > 0) {
						lineSeperator = "\n";
					} else if (os.toLowerCase().indexOf("windows") > 0) {
						lineSeperator = "\r\n";
					}

					HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");

					PrintWriter outputfile1 = response.getWriter();

					if ((extnFlag != null) && (!extnFlag.equals("")) && (Long.parseLong(extnFlag) == 1L)) {

						getFileHeader(outputfile, dtoRegNo);

						getBatchHeader(lLngPkIdForBh, outputfile, totalContriCount, ddoCount, totalEmplyContri,
								totalEmplyerContri, totaloverallAmt, BatchId, dtoRegNo, month1, year1, lStrFileName,
								ddoCode);
					}

					if (extn.equals("txt")) {
						try {
							String fileName = lStrFileName + ".txt";
							response.setContentType("text/plain;charset=UTF-8");

							response.addHeader("Content-disposition", "attachment; filename=" + fileName);
							response.setCharacterEncoding("UTF-8");

							outputfile.write(Strbr.toString());
							outputfile.flush();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (outputfile != null) {
								outputfile.close();
							}

						}

					} else if (extn.equals("fpu")) {

						try {

							String fileName = lStrFileName + ".fpu";
							response.setContentType("text/plain;charset=UTF-8");

							response.addHeader("Content-disposition", "attachment; filename=" + fileName);
							response.setCharacterEncoding("UTF-8");

							outputfile1.write(Strbr1.toString());
							outputfile1.flush();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (outputfile1 != null) {
								outputfile1.close();
							}
						}
					}
				}
				successFlag = "success";
				inputMap.put("successflag", successFlag);
				resObj.setResultValue(inputMap);
				resObj.setViewName("ContributionListNew");
			}
		} catch (Exception e) {
			logger.info("Error occure in createTxtFile()" + e);
			e.printStackTrace();
			resObj.setResultCode(-1);
		}

		resObj.setResultCode(0);
		resObj.setResultValue(inputMap);

		resObj.setViewName("ContributionListNewSRKA");
		return resObj;
	}

	public ResultObject validateLegacyFile(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		Long yearId = null;
		Long monthId = null;
		Long lLongEmployeeAmt = Long.valueOf(0L);
		Long lLongEmployerAmt = Long.valueOf(0L);
		Long TotalAmt = Long.valueOf(0L);
		String dhDtls = "";
		String ddoRegNo = "";
		StringBuilder sb11 = new StringBuilder();
		String errorData = " ";
		String ext = "";
		try {
			logger.info(" inside if loggerss");
			setSessionInfo(inputMap);
			String Fileno = StringUtility.getParameter("Fileno", request);
			Long currentmonth = Long.parseLong(StringUtility.getParameter("Month", this.request));
			Long currentyear = Long.parseLong(StringUtility.getParameter("Year", this.request));
			logger.info("Fileno****************" + Fileno);
			NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			String ddocode = lObjNsdlDAO.getDdoCode(Fileno);
			//bill lock validation dated on 01-09-2023
			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			String strDDOCode = objDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			int count = lObjNsdlDAO.getCountOfNpsBillNotLocked(strDDOCode, currentmonth, currentyear);
	         if (count > 1) {
	            inputMap.put("checkMsg", "You cannot Validate the file as previous Months Legacy data file is not locked");
	         } else {
	          
	         

			// String Fileno =nsdlDetailsModel.getFileId();
			File f4 = null;
			f4 = new File(Fileno.concat(".txt"));
			f4.delete();

			f4.createNewFile();

			String key = "";
			String rootPath = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			// if (strOSName.contains("Windows")) {
			// // key = "serverempconfigimagepath";
			// } else {
			// key = "serverContributionFolderPath";
			// OUTPUT_ZIP_Contri_FILE = environment.getRequiredProperty(key);
			//
			// File directory = new File(OUTPUT_ZIP_Contri_FILE);
			// if (!directory.exists()) {
			// directory.mkdir();
			// }
			// }

			String OUTPUT_ZIP_FILE = null;
			String OUTPUT_ZIP_Contri_FILE = null;
			if (System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {

				OUTPUT_ZIP_Contri_FILE = gObjRsrcBndles.getString("NPS.OUTPUT_FILE");
			} else {
				OUTPUT_ZIP_Contri_FILE = gObjRsrcBndles.getString("NPS.OUTPUT_FILE_SERVER");
			}

			String Path = OUTPUT_ZIP_Contri_FILE;
			String directoryName = Path.concat(ddocode);
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}
			directoryName = directoryName.concat("/" + Fileno.toString());
			directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			String filePath = directoryName + "/" + Fileno.concat(".txt");

			// ended By Akanksha

			NsdlSrkaNewFileGeneDAOImpl lObjNsdlNps = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());

			// String dtoRegNo = lObjNsdlNps.getDtoRegNo(Fileno.substring(1, 5));
			String dtoRegNo = lObjNsdlNps.getDtoRegNo(Fileno);

			FileWriter fw = new FileWriter(filePath);
			BufferedWriter br = new BufferedWriter(fw);

			br.write("1^");
			br.write("FH^");
			br.write("P^");
			br.write(dtoRegNo + "^");
			br.write("1^");
			br.write("^");
			br.write("A");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("^");
			br.write("\r\n");

			String bhData = lObjNsdlDAO.getBatchData(Fileno);
			br.write(bhData);
			br.write("\r\n");

			List dhData = lObjNsdlDAO.getDHData(Fileno);
			if ((dhData != null) && (dhData.size() > 0)) {
				Iterator IT = dhData.iterator();

				Object[] lObj = (Object[]) null;
				while (IT.hasNext()) {
					lObj = (Object[]) IT.next();
					dhDtls = lObj[0].toString();
					ddoRegNo = lObj[1].toString();
					br.write(dhDtls);

					br.write("\r\n");

					List sdDtls = lObjNsdlDAO.getSDDtls(Fileno, ddoRegNo);
					for (int i = 0; i < sdDtls.size(); i++) {
						br.write(sdDtls.get(i).toString());
						br.write("\r\n");

					}

				}
			}

			br.close();

			logger.info("filePath is***********" + filePath.toString());
			logger.info("path is  is***********" + fw.toString());
			logger.info("fw is***********" + br.toString());

			String fvuFilePtah = filePath.replace("txt", "fvu");
			String errFilePtah = filePath.replace("txt", "err");
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String[] args = { filePath, errFilePtah, fvuFilePtah, "0", "1.44" };
			logger.info("inputParametersis " + args[0]);
			logger.info("inputParametersis " + args[1]);
			logger.info("inputParametersis " + args[2]);

			int fileStatus = 0;
			PAOFvu.main(args);

			File f5 = null;
			f5 = new File(Fileno.concat(".txt"));
			System.out.println("File to be deleted" + f5);
			f5.deleteOnExit();

			File f = new File(new File(fvuFilePtah).getAbsolutePath());
			File f1 = new File(new File(errFilePtah).getAbsolutePath());

			if ((f.exists()) && (!f.isDirectory())) {
				BufferedReader br1 = new BufferedReader(new FileReader(new File(fvuFilePtah).getAbsolutePath()));

				ext = ".fvu";
				String line = br1.readLine();
				fileStatus = 1;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					logger.info(sb11.toString());
					line = br1.readLine();
				}

			} else if ((f1.exists()) && (!f1.isDirectory())) {
				BufferedReader br1 = new BufferedReader(new FileReader(new File(errFilePtah).getAbsolutePath()));

				ext = ".err";
				String line = br1.readLine();
				fileStatus = 2;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					logger.info(sb11.toString());
					line = br1.readLine();
				}

				errorData = sb11.toString();
			}

			lObjNsdlDAO.updateFileStatus(fileStatus, Fileno, errorData);
			PrintWriter outputfile = response.getWriter();
			try {
				String fileName = Fileno.concat(ext);
				response.setContentType("text/plain;charset=UTF-8");

				response.addHeader("Content-disposition", "attachment; filename=" + fileName);
				response.setCharacterEncoding("UTF-8");

				outputfile.write(sb11.toString());
				outputfile.flush();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				logger.info("All fine 7 is *********");
				if (outputfile != null)
					outputfile.close();
			}
		}
			logger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("legacyFileValidate");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject createLegacyTextFiles(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		Long yearId = null;
		Long monthId = null;
		Long lLongEmployeeAmt = Long.valueOf(0L);
		Long lLongEmployerAmt = Long.valueOf(0L);
		Long TotalAmt = Long.valueOf(0L);
		String BatchId = null;
		String dhDtls = "";
		String ddoRegNo = "";
		try {
			setSessionInfo(inputMap);
			if ((StringUtility.getParameter("fileNumber", request) != null)
					&& (!StringUtility.getParameter("fileNumber", request).equals(""))) {
				String fileNumber = StringUtility.getParameter("fileNumber", request);
				NsdlSrkaNewFileGeneDAOImpl lObjNsdlNps = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());

				BatchId = fileNumber;
				String dtoRegNo = "";
				// dtoRegNo = lObjNsdlNps.getDtoRegNo(fileNumber.substring(1, 5));
				dtoRegNo = lObjNsdlNps.getDtoRegNo(fileNumber);
				StringBuffer sb = new StringBuffer();
				PrintWriter outputfile = response.getWriter();

				sb.append("1^FH^P^" + dtoRegNo + "^1^^^^^^^");
				logger.info("All fine 1 is *********");
				String bhData = lObjNsdlNps.getBatchData(fileNumber);
				logger.info("All fine 2 is *********" + bhData);

				sb.append("\r\n");
				sb.append(bhData);
				sb.append("\r\n");
				logger.info("All fine 3 is *********");
				List dhData = lObjNsdlNps.getDHData(fileNumber);
				if ((dhData != null) && (dhData.size() > 0)) {
					Iterator IT = dhData.iterator();

					Object[] lObj = (Object[]) null;
					while (IT.hasNext()) {
						lObj = (Object[]) IT.next();
						dhDtls = lObj[0].toString();
						ddoRegNo = lObj[1].toString();

						sb.append(dhDtls);
						sb.append("\r\n");

						List sdDtls = lObjNsdlNps.getSDDtls(fileNumber, ddoRegNo);

						for (int i = 0; i < sdDtls.size(); i++) {

							sb.append(sdDtls.get(i).toString());
							sb.append("\r\n");
						}
					}

				}

				logger.info("All fine 4 is *********");

				String lStrFileName = BatchId;
				try {
					String fileName = lStrFileName + ".txt";
					response.setContentType("text/plain;charset=UTF-8");

					response.addHeader("Content-disposition", "attachment; filename=" + fileName);
					response.setCharacterEncoding("UTF-8");

					outputfile.write(sb.toString());
					outputfile.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					logger.info("All fine 7 is *********");
					if (outputfile != null) {
						outputfile.close();
					}
				}
			}

			logger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("ExportReportPage");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	private void getFileHeader(PrintWriter br, String dtoRegNo) throws IOException {
		br.write("1^");
		br.write("FH^");
		br.write("P^");
		br.write(dtoRegNo + "^");
		br.write("1^");
		br.write("^");
		br.write("A");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("\r\n");
	}

	private void getBatchHeader(Long lLngPkIdForBh, PrintWriter br, int count, long ddoCount, String govContri,
			String SubContri, String Total, String batchId, String dtoRegNo, String month1, int year1,
			String lStrFileName, String ddoCode) throws IOException {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String date = "ddMMyyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(date);
		sdf.setTimeZone(TimeZone.getDefault());
		String currentdate = sdf.format(cal.getTime());

		br.write("2^");
		br.write("BH^");
		br.write("1^");
		br.write("R^");
		br.write(dtoRegNo + "^");
		br.write(currentdate + "^");
		br.write(dtoRegNo + batchId);
		br.write("^^");
		br.write(ddoCount + "^");
		br.write(count + "^");
		br.write(govContri + "^");
		br.write(SubContri + "^");
		br.write("^");
		br.write(Total + "^");
		br.write("\r\n");
		NsdlSrkaNewFileGeneDAOImpl lObjAlIndSer = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
		lObjAlIndSer.insertBatchHeader(lLngPkIdForBh, "2", "BH", "1", "R", dtoRegNo, currentdate, dtoRegNo + batchId,
				ddoCount, count, govContri, SubContri, Total, lStrFileName, year1, month1, ddoCode);
	}

	private void getFileHeaderforfpu(PrintWriter br) throws IOException {
		br.write("1^");
		br.write("FH^");
		br.write("O^");
		br.write("3100812^");
		br.write("1^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("\n");
	}

	private void getBatchHeaderforfpu(PrintWriter br, int count, String govContri, String SubContri, String Total)
			throws IOException {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String date = "ddMMyyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(date);
		sdf.setTimeZone(TimeZone.getDefault());
		String currentdate = sdf.format(cal.getTime());

		br.write("2^");
		br.write("BH^");
		br.write("1^");
		br.write("R^");
		br.write("3100812^");
		br.write(currentdate + "^");
		br.write("1111^");
		br.write("^");
		br.write("1^");
		br.write(count + "^");
		br.write(govContri + "^");
		br.write(SubContri + "^");
		br.write("^");
		br.write(Total + "^");
		br.write("\n");
	}

	private void getDTOHeaderforfpu(PrintWriter br, int count, String govContri, String SubContri) throws IOException {
		br.write("3^");
		br.write("DH^");
		br.write("1^");
		br.write("1^");
		br.write("1^");
		br.write(count + "^");
		br.write(govContri + "^");
		br.write(SubContri + "^");
		br.write("^");
		br.write("\n");
	}

	private static String nosci(double d) {
		if (d < 0) {
			return "-" + nosci(-d);
		}
		String javaString = String.valueOf(d);
		int indexOfE = javaString.indexOf("E");
		if (indexOfE == -1) {
			return javaString;
		}
		StringBuffer sb = new StringBuffer();
		if (d > 1.0D) {
			int exp = Integer.parseInt(javaString.substring(indexOfE + 1));
			String sciDecimal = javaString.substring(2, indexOfE);
			int sciDecimalLength = sciDecimal.length();
			if (exp == sciDecimalLength) {
				sb.append(javaString.charAt(0));
				sb.append(sciDecimal);
			} else if (exp > sciDecimalLength) {
				sb.append(javaString.charAt(0));
				sb.append(sciDecimal);
				for (int i = 0; i < exp - sciDecimalLength; i++) {
					sb.append('0');
				}
			} else if (exp < sciDecimalLength) {
				sb.append(javaString.charAt(0));
				sb.append(sciDecimal.substring(0, exp));
				sb.append('.');
				for (int i = exp; i < sciDecimalLength; i++) {
					sb.append(sciDecimal.charAt(i));
				}
			}

			return sb.toString();
		}

		return javaString;
	}

	public static String decRoundOff(String number) {
		String s = String.format("%.2f", new Object[] { Double.valueOf(Double.parseDouble(number)) });
		return s;
	}

	// added by Akanksha to send file to nsdl

	/*
	 * public ResultObject sendContributionFile(Map inputMap) { ResultObject resObj
	 * = new ResultObject(0, "FAIL");
	 * 
	 * try { logger.info("inside send contribution file"); setSessionInfo(inputMap);
	 * String Fileno = StringUtility.getParameter("Fileno", request); String BhID =
	 * StringUtility.getParameter("bhid", request);
	 * logger.info("Fileno****************" + Fileno); NsdlSrkaNewFileGeneDAOImpl
	 * lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl( null,
	 * serv.getSessionFactory());
	 * 
	 * 
	 * String ddocode = lObjNsdlDAO.getDdoCode(BhID,Fileno); String dtoUserId=
	 * lObjNsdlDAO.getDtouserId(ddocode); String dtoRegNo =
	 * lObjNsdlDAO.getDtoReg(ddocode); logger.info("ddo_code"+ddocode);
	 * 
	 * disableSslVerification();
	 * 
	 * String
	 * refCode=lObjNsdlDAO.sendContriFile(response,ddocode,Fileno,dtoUserId,dtoRegNo
	 * );
	 * 
	 * 
	 * 
	 * if(refCode!=null && !refCode.isEmpty()) {
	 * 
	 * lObjNsdlDAO.updatebatchdetaisls(refCode,BhID);
	 * inputMap.put("checkMsg","File Uploaded Successfully !!! having Status Code "
	 * +refCode); }
	 * 
	 * else if(refCode==null) {
	 * inputMap.put("checkMsg","File not uploaded Successfully");
	 * 
	 * }
	 * 
	 * else { inputMap.put("checkMsg","File not uploaded Successfully");
	 * 
	 * }
	 * 
	 * logger.info("All fine 8 is *********"); resObj.setResultValue(inputMap);
	 * resObj.setViewName("legacyFileValidate"); } catch (ConnectException e) { //
	 * host and port combination not valid e.printStackTrace(); } catch (Exception
	 * e) { e.printStackTrace(); logger.error(" Error is : " + e, e);
	 * resObj.setResultValue(null); resObj.setThrowable(e);
	 * resObj.setResultCode(-1); resObj.setViewName("errorPage"); }
	 * 
	 * return resObj; }
	 */
	public ResultObject getContriButionFile(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");

		try {
			logger.info("inside send contribution file");
			setSessionInfo(inputMap);
			String Fileno = StringUtility.getParameter("Fileno", request);
			String BhID = StringUtility.getParameter("bhid", request);
			logger.info("Fileno****************" + Fileno);
			NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			// String ddocode1= lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);

			String ddocode = lObjNsdlDAO.getDdoCode(BhID, Fileno);
			logger.info("ddo_code" + ddocode);
			logger.info("ddo_code" + ddocode);

			// String ddocode = lObjNsdlDAO.getDdoCode(BhID,Fileno);
			String dtoUserId = lObjNsdlDAO.getDtouserId(ddocode);
			String dtoRegNo = lObjNsdlDAO.getDtoReg(ddocode);
			// String dtoUserId="1014000230";
			//
			// String dtoUserId = lObjNsdlDAO.getDtouserId(ddocode); commented By Akanksha

			// String dtoRegNo = "1014000230";

			// String dtoRegNo = lObjNsdlDAO.getDtoReg(ddocode); // commented By Akanksha
			// String ddocode = "10710100004";

			// dtoUserId="1014000230";
			disableSslVerification();

			String SOURCE_FOLDER = null;
			System.out.println("hello ddo=" + ddocode);

			String nsdlStatusCode = lObjNsdlDAO.getNsdlStatusCode(BhID);

			System.out.println("status code>>>" + nsdlStatusCode);

			// String dtoRegNo = mstEmployeeService.getDtoRegNumber(messages.getUserName());

			String transactioId = lObjNsdlDAO.getContriStatus(response, ddocode, nsdlStatusCode, dtoUserId, Fileno,
					dtoRegNo);

			if (transactioId != null && !transactioId.isEmpty() && !transactioId.contains("testfile.html")) {
				if (transactioId != "0" && !transactioId.equalsIgnoreCase("0")) {
					{
						lObjNsdlDAO.updateTransactionId(transactioId, BhID);
						inputMap.put("checkMsg",
								"Transaction ID updated successfully.NSDL Transaction No " + transactioId);
					}

				}

				else if (transactioId == "1" || transactioId.equalsIgnoreCase("1")) {
					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again late");

				} else if (transactioId == "0" || transactioId.equalsIgnoreCase("0")) {
					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again late");

				}
				if (transactioId.isEmpty()) {

					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again later");

					// lObjNsdlDAO.updateTransactionIdError(transactioId,BhID);

				}
			} else {

				if (transactioId.isEmpty()) {

					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again later");

					// lObjNsdlDAO.updateTransactionIdError(transactioId,BhID);

				}

				String strOSName = System.getProperty("os.name");
				boolean test = strOSName.contains("Windows");
				if (strOSName.contains("Windows")) {
					// key = "serverempconfigimagepath";
				} else {

					String key = "";
					String rootPath = "";

					key = "npsfilepathinLinusOS";
					String filepath = "E:\\Nsdldata";
					// OUTPUT_ZIP_FILE = environment.getRequiredProperty(key);
					OUTPUT_ZIP_FILE = filepath;
				}

				SOURCE_FOLDER = OUTPUT_ZIP_FILE + ddocode + "/testfile.html";// OUTPUT_ZIP_FILE+ddoCode+"/"+ackNo+"/Error";
				File downloadFile = new File(SOURCE_FOLDER);
				// return SOURCE_FOLDER;
				resObj.setResultValue(inputMap);
				resObj.setViewName("legacyFileValidate");
			}

			inputMap.put("transactioId", transactioId);

			logger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("legacyFileValidate");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	// added By Akanksha to view Challan File

	public ResultObject downloadChallanFile(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
		try {
			logger.info("inside send contribution file");
			setSessionInfo(inputMap);
			String transactionid = StringUtility.getParameter("transactionid", request);
			String fileNo = StringUtility.getParameter("fileNo", request);
			String BhID = StringUtility.getParameter("batchid", request);
			logger.info("transactionid****************" + transactionid);
			NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String ddocode = lObjNsdlDAO.getDdoCode(BhID, fileNo);

			ReadFile(response, ddocode, transactionid, fileNo);

			logger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("legacyFileValidate");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	private void ReadFile(HttpServletResponse response, String ddocode, String transactionid, String fileNo)
			throws IOException {

		String OUTPUT_ZIP_FILE = null;
		String OUTPUT_ZIP_Contri_FILE = null;
		if (System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
			OUTPUT_ZIP_FILE = gObjRsrcBndles.getString("NPS.DTO_SIGN");
			OUTPUT_ZIP_Contri_FILE = gObjRsrcBndles.getString("NPS.OUTPUT_FILE");
		} else {
			OUTPUT_ZIP_FILE = gObjRsrcBndles.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE = gObjRsrcBndles.getString("NPS.OUTPUT_FILE_SERVER");
		}
		String temporaryFile = transactionid + "_Challan.html";
		String filepath = OUTPUT_ZIP_Contri_FILE + ddocode + "/" + fileNo + "//" + transactionid + "_Challan.html";

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		InputStream inputStream = new FileInputStream(filepath);
		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "attachment; filename=" + temporaryFile);
		// ServletOutputStream out = response.getOutputStream();

		PrintWriter out = response.getWriter();
		IOUtils.copy(inputStream, out);
		response.flushBuffer();
		inputStream.close();
		out.flush();
		out.close();
		return;

	}

	public ResultObject deleteContributionFile(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");

		try {
			logger.info("inside send contribution file");
			setSessionInfo(inputMap);
			String Fileno = StringUtility.getParameter("Fileno", request);
			String BhID = StringUtility.getParameter("bhid", request);
			logger.info("Fileno****************" + Fileno);
			NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String ddocode = lObjNsdlDAO.getDdoCode(BhID, Fileno);
			logger.info("ddo_code" + ddocode);

			String filestatus = lObjNsdlDAO.getFileStatus(ddocode, Fileno, BhID);

			String batch_id = BhID.substring(7, 20);
			logger.info("batch id" + batch_id);

			String DDO_CODE = null;
			String SEVARTH_ID = null;
			String DCPS_ID = null;
			String REMARKS = null;
			String PERIOD = null;
			String BATCH_ID;
			BigInteger NPS_ID;
			BigInteger DCPS_EMP_ID;
			Short YEAR;
			Short MONTH;
			Double EMP_CONTRI = 0d;
			Double EMPLOYER_CONTRI = 0d;
			Double EMP_INT = 0d;
			Double EMPLOYER_INT = 0d;
			Double TOTAL;
			Date CREATED_DATE;
			Date UPDATED_DATE;
			Date APPROVAL_DATE;
			Date REJECTION_DATE;
			Date CONTRI_END_DATE;
			Date CONTRI_START_DATE;
			String STATUS;
			BigInteger CREATED_POST_ID;
			BigInteger UPDATED_POST_ID;

			if (filestatus.equalsIgnoreCase("2") || filestatus.equalsIgnoreCase("0")) {
				List Legacydata = lObjNsdlDAO.getlegacyDataDetailsHis(Fileno, ddocode, batch_id);

				if ((Legacydata != null) && (!Legacydata.isEmpty())) {
					logger.info("Inside if " + Legacydata);
					Iterator iterator = Legacydata.iterator();
					while (iterator.hasNext()) {
						Object[] tuple = (Object[]) iterator.next();

						NPS_ID = (BigInteger) tuple[0];
						DDO_CODE = (String) tuple[1];
						SEVARTH_ID = (String) tuple[2];
						DCPS_ID = (String) tuple[3];
						DCPS_EMP_ID = (BigInteger) tuple[4];
						EMP_CONTRI = (Double) tuple[5];
						EMPLOYER_CONTRI = (Double) tuple[6];
						EMP_INT = (Double) tuple[7];
						EMPLOYER_INT = (Double) tuple[8];
						TOTAL = (Double) tuple[9];
						YEAR = (Short) tuple[10];
						MONTH = (Short) tuple[11];
						STATUS = (String) tuple[12];
						CREATED_DATE = (Date) tuple[13];
						CREATED_POST_ID = (BigInteger) tuple[14];
						UPDATED_DATE = (Date) tuple[15];
						UPDATED_POST_ID = (BigInteger) tuple[16];
						APPROVAL_DATE = (Date) tuple[17];
						REMARKS = (String) tuple[18];
						PERIOD = (String) tuple[19];
						CONTRI_START_DATE = (Date) tuple[20];
						CONTRI_END_DATE = (Date) tuple[21];
						BATCH_ID = (String) tuple[22];
						REJECTION_DATE = (Date) tuple[23];

						// VOU_DATE = (Date)tuple[6];
						// REMARK = (String)tuple[7];
						// CREATED_USER_ID = (BigInteger)tuple[8];
						// CREATED_POST_ID = (BigInteger)tuple[9];
						// UPDATED_USER_ID = (BigInteger)tuple[10];
						// UPDATED_POST_ID = (BigInteger)tuple[11];
						// CREATED_DATE = (Date)tuple[12];
						// UPDATED_DATE = (Date)tuple[13];
						// NET_TOTAL = (BigDecimal)tuple[14];
						// APPROVE_FLAG =(BigInteger)tuple[15];
						// NPS_ARR_AMT = (BigDecimal)tuple[16];
						// PAYBILL_YEAR = (BigInteger)tuple[17];
						// PAYBILL_MONTH = (BigInteger)tuple[18];
						// DDO_CODE = (String)tuple[19];
						// DIFFERENCE_AMT =(BigDecimal)tuple[20];

						lObjNsdlDAO.insertlegacyDataHistorytdetails(NPS_ID, DDO_CODE, SEVARTH_ID, DCPS_ID, DCPS_EMP_ID,
								EMP_CONTRI, EMPLOYER_CONTRI, EMP_INT, EMPLOYER_INT, TOTAL, YEAR, MONTH, STATUS,
								CREATED_DATE, CREATED_POST_ID, UPDATED_DATE, UPDATED_POST_ID, APPROVAL_DATE, REMARKS,
								PERIOD, CONTRI_START_DATE, CONTRI_END_DATE, BATCH_ID, REJECTION_DATE);

					}

				}

				lObjNsdlDAO.deleteFileDetails(Fileno, ddocode, batch_id);
				inputMap.put("checkMsg", "File deleted successfully");
			} else {

				inputMap.put("checkMsg", "File caanot be deleted.");
			}
			logger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("legacyFileValidate");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	private static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

}