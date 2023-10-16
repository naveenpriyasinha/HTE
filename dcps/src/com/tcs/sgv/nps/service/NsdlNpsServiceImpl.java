
package com.tcs.sgv.nps.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.ibm.icu.util.StringTokenizer;

import javax.net.ssl.HostnameVerifier;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.util.EnglishDecimalFormat;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.nps.dao.FormS1UpdateDAO;
import com.tcs.sgv.nps.dao.FormS1UpdateDAOImpl;
import com.tcs.sgv.nps.dao.NsdlNpsDAOImpl;
import com.tcs.sgv.nps.dao.NsdlNpsFileRegularSendImpl;
import com.tcs.sgv.nps.dao.NsdlSrkaNewFileGeneDAOImpl;
import com.tcs.sgv.nps.valueobject.EmpDtlsCustomVO;

import cra.standalone.paosubcontr.PAOFvu;
import cra.standalone.subsreg.RunSubsRegFvu;

//import cra.standalone.paosubcontr.PAOFvu;

public class NsdlNpsServiceImpl extends ServiceImpl {

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrLocationCode = null;

	private Long gLngPostId = null;

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private String gStrUserId = null; /* STRING USER ID */

	private HttpServletResponse response = null;/* RESPONSE OBJECT */

	/* Global Variable for UserId */
	Long gLngUserId = null;

	private Long gLngDBId = null; /* DB ID */

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");
	private static String OUTPUT_ZIP_FILE;
	private static String OUTPUT_ZIP_Contri_FILE;
	private static ResourceBundle gObjRsrcBndles = null;
	static {
		NsdlNpsServiceImpl.OUTPUT_ZIP_FILE = "E:/outputnps";
		NsdlNpsServiceImpl.OUTPUT_ZIP_Contri_FILE = "E:/outputnps/";
		NsdlNpsServiceImpl.gObjRsrcBndles = ResourceBundle.getBundle("resources/nps/NPSConstants");
	}

	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap) {

		try {
			response = (HttpServletResponse) inputMap.get("responseObj");
			request = (HttpServletRequest) inputMap.get("requestObj");
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			session = request.getSession();
			gStrPostId = SessionHelper.getPostId(inputMap).toString();
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngUserId = SessionHelper.getUserId(inputMap);
			gLogger.info(" gLngUserId ssssss : " + gLngUserId);
			gStrUserId = gLngUserId.toString();
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gLogger.info("gStrLocationCode++++++++++++++" + gStrLocationCode);

		} catch (Exception e) {
			gLogger.error(" Error is : " + e, e);
		}
	}

	public NsdlNpsServiceImpl() {
		this.gStrPostId = null;
		this.gStrLocationCode = null;
		this.gLngPostId = null;
		this.request = null;
		this.serv = null;
		this.session = null;
		this.gDtCurDate = null;
		this.gStrUserId = null;
		this.response = null;
		this.gLngUserId = null;
		this.gLngDBId = null;
		this.gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");
	}

	public ResultObject loadNPSNSDLForm(Map inputMap) {

		ResultObject resObj = new ResultObject(0, "FAIL");
		String lStrTempFromDate = null;
		String lStrTempToDate = null;
		String lStrFromDate = null;
		String lStrToDate = null;
		Date lDateFromDate = null;
		String ddoCode = null;
		Date lDateToDate = null;
		List lListTotalDdowiseEntries = null;
		List lListTotalDdowiseEntriesFinal = null;
		Long yearId = null;
		Long monthId = null;
		Long finYearId = Long.valueOf(7340579277617758208L);
		Double lLongEmployeeAmt = Double.valueOf(0D);
		Double lLongEmployerAmt = Double.valueOf(0D);

		Double TotalAmt = Double.valueOf(0D);
		String finYearCode = "";
		try {
			setSessionInfo(inputMap);
			Map loginMap = (Map) inputMap.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, this.serv.getSessionFactory());

			List lLstYears = lObjNsdlDAO.getFinyear();

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, this.serv.getSessionFactory());
			ddoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			List lLstMonths = lObjNsdlDAO.getMonths();
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String subTrCode = "";
			String ifDeputation = "2";
			String ddoCodeTr = "";
			// String gLocId = "";

			ddoCodeTr = ddoCode;
			List subTr = lObjNsdlDAO.getAllSubTreasury(this.gStrLocationCode);
			gLogger.info("in subTr---------------------- " + this.gStrLocationCode);
			if ((!(StringUtility.getParameter("yearId", this.request).equalsIgnoreCase("")))
					&& (StringUtility.getParameter("yearId", this.request) != null)
					&& (!(StringUtility.getParameter("monthId", this.request).equalsIgnoreCase("")))
					&& (StringUtility.getParameter("monthId", this.request) != null))
			// && (!(StringUtility.getParameter("cmbTr",
			// this.request).equalsIgnoreCase(""))) && (StringUtility.getParameter("cmbTr",
			// this.request) != null)
			{
				yearId = Long.valueOf(StringUtility.getParameter("yearId", this.request));
				monthId = Long.valueOf(StringUtility.getParameter("monthId", this.request));
				subTrCode = ddoCode.substring(0, 4); // StringUtility.getParameter("cmbTr", this.request);
				if ((StringUtility.getParameter("ifDeputation", this.request) != null)
						&& (!(StringUtility.getParameter("ifDeputation", this.request).equalsIgnoreCase("")))) {
					ifDeputation = StringUtility.getParameter("ifDeputation", this.request);
				}
				List DDOList = null;
				if (ddoCodeTr.equals("1111222222")) {
					ddoCodeTr = "9101005555";
				}
				gLogger.info("in gStrLocationCode---------------------- " + gStrLocationCode);
				gLogger.info("subTrCode---------------------- " + subTrCode);

				if ((ifDeputation != null) && (!("".equalsIgnoreCase(ifDeputation)))
						&& ("2".equalsIgnoreCase(ifDeputation))) {
					List totalListForEmployeeRetired = lObjNsdlDAO
							.getDdoWiseTotalAmt_totalListForEmployeeRetired(yearId, monthId, ddoCode);
					if (!totalListForEmployeeRetired.isEmpty()) {

						List<EmpDtlsCustomVO> empList = new ArrayList();

						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

						for (int i = 0; i < totalListForEmployeeRetired.size(); i++) {
							EmpDtlsCustomVO empdtlsCustom = new EmpDtlsCustomVO();

							Object[] row = (Object[]) totalListForEmployeeRetired.get(i);
							empdtlsCustom.setEmpSevaarthId(row[0].toString());
							empdtlsCustom.setEmpEmployeeName(row[1].toString());
							empdtlsCustom.setEmpPRANNO(row[2] != null ? row[2].toString() : "");
							empdtlsCustom.setEmpServiceEndDate(row[3].toString());
							empList.add(empdtlsCustom);
						}
						inputMap.put("empListServiceEndDateEmp", empList);
					} else {
						List totalList = lObjNsdlDAO.getDdoWiseTotalAmt(yearId, monthId, ddoCode);
						List nsdlList = lObjNsdlDAO.getDdoWiseTotalAmtSentToNSDL(yearId, monthId, ddoCode);
						if ((totalList != null) && (totalList.size() > 0)) {
							lListTotalDdowiseEntries = new ArrayList();
							for (int j = 0; j < totalList.size(); ++j) {
								if ((totalList.get(j) != null)
										&& (!("".equalsIgnoreCase(totalList.get(j).toString())))) {
									Object[] obj1 = (Object[]) totalList.get(j);
									if ((nsdlList != null) && (nsdlList.size() > 0)) {
										for (int k = 0; k < nsdlList.size(); ++k) {
											if ((nsdlList.get(k) != null)
													&& (!("".equalsIgnoreCase(nsdlList.get(k).toString())))) {
												Object[] obj2 = (Object[]) nsdlList.get(k);
												if ((obj2[4].toString().equalsIgnoreCase(obj1[6].toString()))
														&& (obj2[0].toString().equalsIgnoreCase(obj1[1].toString()))) {

													obj1[5] = nosci(Double.parseDouble(obj1[5].toString())
															- Double.parseDouble(obj2[1].toString()));
													obj1[4] = nosci(Double.parseDouble(obj1[4].toString())
															- Double.parseDouble(obj2[1].toString()));
												}
											}
										}
									}

									if (obj1[5] != null && Double.parseDouble(obj1[5].toString()) >= 0
											&& obj1[4] != null && Double.parseDouble(obj1[4].toString()) > 0) {
										lListTotalDdowiseEntries.add(obj1);
									}

								}
							}
						}
						if ((lListTotalDdowiseEntries != null) && (lListTotalDdowiseEntries.size() > 0)
								&& (lListTotalDdowiseEntries.get(0) != null)
								&& (!("".equalsIgnoreCase(lListTotalDdowiseEntries.get(0).toString())))) {
							List prvDDo = new ArrayList();
							lListTotalDdowiseEntriesFinal = new ArrayList();
							for (int i = 0; i < lListTotalDdowiseEntries.size(); ++i) {
								Object[] obj1 = (Object[]) lListTotalDdowiseEntries.get(i);
								for (int m = 0; m < lListTotalDdowiseEntries.size(); ++m) {
									if (m == i) {
										continue;
									}

									Object[] obj2 = (Object[]) lListTotalDdowiseEntries.get(m);
									if ((obj1[0].toString().equalsIgnoreCase(obj2[0].toString()))
											&& (!(prvDDo.contains(obj1[0].toString())))) {
										obj1[5] = nosci(Double.parseDouble(obj1[5].toString())
												+ Double.parseDouble(obj2[5].toString()));
										obj1[4] = nosci(Double.parseDouble(obj1[4].toString())
												+ Double.parseDouble(obj2[4].toString()));
										obj1[2] = nosci(Double.parseDouble(obj1[2].toString())
												+ Double.parseDouble(obj2[2].toString()));
										obj1[3] = nosci(Double.parseDouble(obj1[3].toString())
												+ Double.parseDouble(obj2[3].toString()));
									}

								}

								if (!(prvDDo.contains(obj1[0].toString()))) {
									lListTotalDdowiseEntriesFinal.add(obj1);
								}

								prvDDo.add(obj1[0].toString());
							}
						}
					}
				} else {
					if (monthId.longValue() < 4L) {
						finYearCode = String.valueOf(yearId.longValue() - 1);
					} else {
						finYearCode = String.valueOf(yearId);
					}
					finYearId = lObjNsdlDAO.getFinYearId(finYearCode);
					lListTotalDdowiseEntriesFinal = lObjNsdlDAO.getDdoWiseTotalAmtForDeputation(finYearId, monthId,
							subTrCode, yearId.toString(), ddoCode);
				}

				// System.out.println("FINSL SIZE " + lListTotalDdowiseEntriesFinal.size());
				if (lListTotalDdowiseEntriesFinal != null) {
					Iterator it = lListTotalDdowiseEntriesFinal.iterator();

					Object[] tupleNps = (Object[]) null;
					while (it.hasNext()) {
						tupleNps = (Object[]) it.next();
						lLongEmployeeAmt = Double.valueOf(tupleNps[3].toString());
						lLongEmployerAmt = Double.valueOf(tupleNps[4].toString());
						TotalAmt = Double.valueOf(lLongEmployeeAmt.doubleValue() + lLongEmployerAmt.doubleValue());

						inputMap.put("TotalAmt", TotalAmt);
					}
					String lLongEmployeeTotalAmt = "0";
					String lLongEmployerTotalAmt = "0";
					String lLongEmployeeGrossAmnt = "0";
					String lLongEmployeeNetAmnt = "0";
					Object[] obj = (Object[]) null;
					if ((ifDeputation != null) && (!("".equalsIgnoreCase(ifDeputation)))
							&& ("2".equalsIgnoreCase(ifDeputation))) {
						Iterator it1 = lListTotalDdowiseEntriesFinal.iterator();
						while (it1.hasNext()) {
							tupleNps = (Object[]) it1.next();
							lLongEmployeeTotalAmt = nosci(Double.parseDouble(lLongEmployeeTotalAmt)
									+ Double.parseDouble(tupleNps[4].toString()));
							lLongEmployerTotalAmt = nosci(Double.parseDouble(lLongEmployerTotalAmt)
									+ Double.parseDouble(tupleNps[5].toString()));
							lLongEmployeeGrossAmnt = nosci(Double.parseDouble(lLongEmployeeGrossAmnt)
									+ Double.parseDouble(tupleNps[2].toString()));
							lLongEmployeeNetAmnt = nosci(Double.parseDouble(lLongEmployeeNetAmnt)
									+ Double.parseDouble(tupleNps[3].toString()));
							obj = new Object[7];
							obj[0] = "A";
							obj[1] = "A";
							obj[2] = lLongEmployeeGrossAmnt;
							obj[3] = lLongEmployeeNetAmnt;
							obj[4] = new BigDecimal(lLongEmployeeTotalAmt);
							obj[5] = new BigDecimal(lLongEmployerTotalAmt);
							obj[6] = "A";
						}

						if (obj != null) {
							lListTotalDdowiseEntriesFinal.add(obj);
						}
					}
				}

			}
			// gStrLocationCode
			inputMap.put("selectedYear", yearId);
			inputMap.put("selectedMonth", monthId);
			inputMap.put("subTrCode", subTrCode);
			inputMap.put("subTr", subTr);
			inputMap.put("YEARS", lLstYears);
			inputMap.put("MONTHS", lLstMonths);
			inputMap.put("lListTotalDdowiseEntries", lListTotalDdowiseEntriesFinal);
			inputMap.put("idDeputation", ifDeputation);

			resObj.setResultValue(inputMap);
			resObj.setViewName("NPSNSDL");
		} catch (Exception e) {
			e.printStackTrace();
			// this.gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(-1);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject genNsdlTxtFile(Map<String, Object> inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		gLogger.info("in genNsdlTxtFile---------------------- ");

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

		Object obj1[];
		String fromDate = null;
		String toDate = null;
		String dtoRegNo = null;
		String ddoRegNo = null;
		String sdAmnt = null;
		String prvDdoReg = "AAA";
		String treasuryyno = null;
		List lstemployee = null;
		String yrCode = null;
		String month = null;
		List nsdlData = null;
		String BatchId = null;
		Long nwbatchId = null;
		String nwTranBatchId = null;
		String[] fyYrsplit = null;
		String tranId = null;
		String subTrCode = "";
		String ddoCode = "";
		String gLocId = "";
		List FinalBillList = null;
		List lBillListFinal = null;
		List lLstYears = null;
		List lLstMonths = null;
		try {

			setSessionInfo(inputMap);
			treasuryyno = gStrLocationCode;
			ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			String strDDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
			if (StringUtility.getParameter("yearId", request) != null
					&& !StringUtility.getParameter("yearId", request).equals("")
					&& Long.parseLong(StringUtility.getParameter("yearId", request)) != -1
					&& StringUtility.getParameter("monthId", request) != null
					&& !StringUtility.getParameter("monthId", request).equals("")
					&& Long.parseLong(StringUtility.getParameter("monthId", request)) != -1)
			// && StringUtility.getParameter("subTr", request) != null &&
			// !StringUtility.getParameter("subTr", request).equals("") &&
			// Long.parseLong(StringUtility.getParameter("subTr", request))!=-1)

			{
				extn = "txt";

				extnFlag = StringUtility.getParameter("flagFile", request).trim();
				yrCode = StringUtility.getParameter("yearId", request);
				month = StringUtility.getParameter("monthId", request);
				// treasuryyno=StringUtility.getParameter("subTr", request) ;
				System.out.println("TRES=>" + gLngPostId + " " + strDDOCode);
				treasuryyno = strDDOCode.substring(0, 4);
				System.out.println("TRES=>" + treasuryyno);
			}

			NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
			List subTr1 = lObjNsdlNps.getAllSubTreasury(this.gStrLocationCode);
			ComboValuesVO lObjComboValuesVO = null;
			if ((subTr1 != null) && (subTr1.size() > 0) && (subTr1.get(0) != null)) {
				lObjComboValuesVO = (ComboValuesVO) subTr1.get(0);
				if (lObjComboValuesVO != null) {
					gLocId = lObjComboValuesVO.getId();
				}
			}
			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
			lLstYears = lObjNsdlDAO.getFinyear();
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lLstMonths = lObjNsdlDAO.getMonths();
			gLogger.info("gStrLocationCode++++++++++++++" + gStrLocationCode);
			List subTr = lObjNsdlDAO.getAllSubTreasury(gStrLocationCode);

			// code for batch Id.
			String midFix = "";
			String finalFix = "";
			if (month.length() == 1) {
				midFix = "0";
			}
			/// String batchIdPrefix=treasuryyno+yrCode+midFix+month;
			String batchIdPrefix = treasuryyno + midFix + month + yrCode;

			int checkNPSContriList = lObjNsdlNps.getNPSFileContriSeq(strDDOCode);

			// String BatchIdCount = checkBatchIdList.get(0).toString();
			if (checkNPSContriList > 0) {
				lObjNsdlNps.updateNPSFileContriSeq(strDDOCode);
				checkNPSContriList = lObjNsdlNps.getNPSFileContriSeq(strDDOCode);
				gLogger.info("updated Acknowledgement generation is:" + checkNPSContriList);
			} else {
				gLogger.info("Acknowledgement generation is:" + checkNPSContriList);
				lObjNsdlNps.insertNPSFileContriSeq(strDDOCode);
				checkNPSContriList = lObjNsdlNps.getNPSFileContriSeq(strDDOCode);
				gLogger.info("new Acknowledgement is:" + checkNPSContriList);
			}

			/* String countBatchId=lObjNsdlNps.getbatchIdCount(batchIdPrefix); */
			if (checkNPSContriList < 10) {
				finalFix = "00";
			} else {
				finalFix = "0";
			}
			/*
			 * if(countBatchId.length()==2){ finalFix="0"; }
			 */

			BatchId = batchIdPrefix + finalFix + checkNPSContriList;

			DcpsCommonDAO objDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			Object[] lyrObj = null;
			lstemployee = lObjNsdlNps.getEmployeeListNsdl(yrCode, month, treasuryyno, strDDOCode);
			int countReg = lObjNsdlNps.getDDoRegCount(yrCode, month, treasuryyno, strDDOCode);
			// ---------- Added by Naveen--------
			gLogger.info("BH DDO  REG count is " + countReg);
			if (lstemployee != null && !lstemployee.isEmpty()) // for bill Id
			{
				Long billNo = 0L;
				FinalBillList = new ArrayList();
				for (int i = 0; i < lstemployee.size(); i++) {

					Object[] tuple = (Object[]) lstemployee.get(i);
					billNo = Long.parseLong(tuple[9].toString());
					gLogger.info("billNo************ " + billNo);
					if (i == 0) {
						FinalBillList.add(billNo);
					}
					int flag = 0;
					int count = 0;
					for (int j = 0; j < FinalBillList.size(); j++) {
						Long billNoFinal = 0L;

						billNoFinal = (Long) FinalBillList.get(j);
						gLogger.info("billNoFinal************ " + billNoFinal);
						if (billNo.equals(billNoFinal)) {
							gLogger.info("in elseee************ ");
							gLogger.info("billNo in else************ " + billNo);
							flag = 1;
						} else {
							count++;
							flag = 2;
						}
					}
					if ((flag == 2) && (count == FinalBillList.size())) {
						FinalBillList.add(billNo);
					}
				}
			}
			gLogger.info("lBillList final************ " + FinalBillList);

			/*
			 * if (FinalBillList != null && !FinalBillList.isEmpty()) { try {
			 * 
			 * 
			 * lObjNsdlNps.updateFileName(BatchId,FinalBillList); } catch (Exception e) {
			 * System.out.println("try======="+e.getMessage()); }
			 * 
			 * }
			 */

			// BH begin
			int totalsize = lstemployee.size();
			gLogger.info("BH  total employee size is " + totalsize);
			if (lstemployee != null && !lstemployee.isEmpty()) {

				int count = 0;
				int i = 2;
				int j = 0;
				int empCount = 1;

				//List lEmpTotalContri = lObjNsdlNps.getEmployeeContriTotalList(yrCode, month, treasuryyno);
				List lEmpTotalContri = lObjNsdlNps.getEmployeeContriTotalList(yrCode, month, treasuryyno,strDDOCode);
				String totalEmplyContri = null;
				Double EmpleeContri = null;
				Double EmplrContri = null;
				Double EmpleeDHContri = null;
				Double EmplrDHContri = null;
				String totalEmplyerContri = null;
				String totalEmplyDHContri = null;
				String totalEmplyerDHContri = null;
				Double totalEContri = null;
				Double totalEDHContri = null;
				String intEmpl = null;
				String intEmplr = null;
				String[] intEmp = null;
				String[] intEmployee = null;
				String[] intEmployer = null;
				String[] contriEmployee = null;
				String[] emplrContriintEmployer = null;
				String[] totalDhsumsplit = null;
				Double totalContribution = null;
				Double GovContributionSum = null;
				Double subcontributionSum = null;
				Double TotalContributionsum = null;
				Double totalEmpContribution = 0.0;
				Double totalEmplrContribution = 0.0;
				String[] overallAmt = null;
				String[] overallDHAmt = null;
				String[] totalempoverallAmt = null;
				String[] totalempoverallDHAmt = null;
				String[] totalemplroverallAmt = null;
				String[] totalemplroverallDHAmt = null;
				String monthId = null;
				Long lLngPkIdForDh = null;
				Long lLngPkIdForBh = null;
				Long lLngPkIdForSd = null;

				// Map<String, Long> lMapEmpeeCountDtls = null;
				if (lEmpTotalContri != null && lEmpTotalContri.size() > 0) {

					gLogger.info("In Loop  " + lEmpTotalContri.get(0).toString());

					String[] totalAmtBH = lEmpTotalContri.get(0).toString().split("#");
					gLogger.info("In Loop1  " + lEmpTotalContri.get(0).toString());
					EmpleeContri = Double.parseDouble(totalAmtBH[0]);
					totalEmplyContri = nosci(EmpleeContri);
					gLogger.info("In Loop 2 " + totalEmplyContri);
					EmplrContri = Double.parseDouble(totalAmtBH[1]);
					totalEmplyerContri = nosci(EmplrContri);
					gLogger.info("In Loop3  " + EmplrContri);
					totalEContri = Double.parseDouble(totalEmplyContri) + Double.parseDouble(totalEmplyerContri);
					gLogger.info("In Loop 4 " + totalEContri);
				}

				// total
				String totaloverallAmt = nosci(totalEContri);
				gLogger.info("Out Loop totaloverallAmt" + totaloverallAmt);

				overallAmt = totaloverallAmt.toString().split("\\.");
				gLogger.info("Out Loop overallAmt" + overallAmt[0]);
				if (overallAmt.length != 1) {
					if (totaloverallAmt.equals("0")) {
						totaloverallAmt = "0.00";
						gLogger.info("Out Loop in if 1 " + totaloverallAmt);
					}

					else if (overallAmt[1].length() == 1) {
						gLogger.info("Out Loop in if 2 " + totaloverallAmt);
						totaloverallAmt = totaloverallAmt + "0";
						gLogger.info("Out Loop in if 2 " + totaloverallAmt);
					}

					else if (overallAmt[1].length() > 2) {
						gLogger.info("Out Loop in if 3 " + totaloverallAmt);
						totaloverallAmt = decRoundOff(totaloverallAmt);
						gLogger.info("Out Loop in if 3 " + totaloverallAmt);

					}
					gLogger.info("Out Loop totaloverallAmt" + totaloverallAmt);
				} else {
					totaloverallAmt = totaloverallAmt + ".00";

				}
				// emp

				totalempoverallAmt = totalEmplyContri.toString().split("\\.");

				gLogger.info("Out Loop totalempoverallAmt" + totalempoverallAmt[0]);

				if (totalempoverallAmt.length != 1) {
					if (totalEmplyContri.equals("0")) {
						totalEmplyContri = "0.00";
					}

					else if (totalempoverallAmt[1].length() == 1) {
						totalEmplyContri = totalEmplyContri + "0";
					}

					else if (overallAmt[1].length() > 2) {

						totalEmplyContri = decRoundOff(totalEmplyContri);

					}

					gLogger.info("Out Loop totalempoverallAmt" + totalempoverallAmt[0]);
				}

				else {
					totalEmplyContri = totalEmplyContri + ".00";

				}
				// emplr
				gLogger.info("Amount is **********  " + totalEmplyerContri.toString());
				totalemplroverallAmt = totalEmplyerContri.toString().split("\\.");
				gLogger.info("Out Loop totalempoverallAmt" + totalemplroverallAmt[0]);
				if (totalemplroverallAmt.length != 1) {
					if (totalEmplyerContri.equals("0")) {
						totalEmplyerContri = "0.00";
					}

					else if (totalemplroverallAmt[1].length() == 1) {
						totalEmplyerContri = totalEmplyerContri + "0";
					} else if (overallAmt[1].length() > 2) {

						totalEmplyerContri = decRoundOff(totalEmplyerContri);
					}
				} else {

					totalEmplyerContri = totalEmplyerContri + ".00";
				}

				gLogger.info("BH Employee amount is " + totalEmplyContri);
				gLogger.info("BH Employer amount is " + totalEmplyerContri);
				gLogger.info("BH total  amount is " + totaloverallAmt);
				int temp = 0;
				int emprecCount = 0;

				// query to insert the data in bh details table
				// BH end
				/* String[] lEmpCountContrDdo = lObjNsdlNps.getEmployeeCountDdoregNsdl(yrCode, month, treasuryyno,
						strDDOCode); */
				Map<String, String>  lEmpCountContrDdoMap=new HashMap();
			  lEmpCountContrDdoMap = lObjNsdlNps.getEmployeeCountDdoregNsdlMap(yrCode, month, treasuryyno,
						strDDOCode);
				/*String[] lEmpTotalContrDdo = lObjNsdlNps.getEmployeeListDdoregNsdl(yrCode, month, treasuryyno,
						strDDOCode);*/
				Map<String, String>  lEmpTotalContrDdoMap=new HashMap();
				lEmpTotalContrDdoMap = lObjNsdlNps.getEmployeeListDdoregNsdlMap(yrCode, month, treasuryyno,strDDOCode);

				gLogger.info("lEmpCountContrDdo length" + lEmpCountContrDdoMap.size());
				gLogger.info("lEmpTotalContrDdo length" + lEmpTotalContrDdoMap.size());

				PrintWriter outputfile = response.getWriter();
				for (Iterator it = lstemployee.iterator(); it.hasNext();) {
					count++;
					Object[] lObj = (Object[]) it.next();

					empname = (lObj[0] != null) ? lObj[0].toString() : "NA";

					dcpsid = ((lObj[1] != null) ? lObj[1].toString() : "");

					gLogger.info("dcps Id ********" + dcpsid);
					pranno = ((lObj[2] != null) ? lObj[2].toString() : "");

					dtoRegNo = (lObj[6] != null) ? lObj[6].toString() : "";

					ddoRegNo = (lObj[7] != null) ? lObj[7].toString() : "";

					govEmpContri = (lObj[3] != null) ? lObj[3].toString() : "";

					subempContri = (lObj[4] != null) ? lObj[4].toString() : "";

					sdAmnt = (lObj[8] != null) ? lObj[8].toString() : "";

					contriEmployee = govEmpContri.toString().split("\\.");
					if (contriEmployee.length != 1) {
						if (govEmpContri.equals("0")) {
							govEmpContri = "0.00";
						} else if (contriEmployee.length > 1) {
							if (contriEmployee[1].length() == 1)
								govEmpContri = govEmpContri + "0";
						}

						else if (contriEmployee.length == 1) {
							govEmpContri = govEmpContri + ".00";
						}
					}

					else {
						govEmpContri = govEmpContri + ".00";

					}

					emplrContriintEmployer = subempContri.toString().split("\\.");
					if (emplrContriintEmployer.length != 1) {
						if (subempContri.equals("0")) {
							subempContri = "0.00";
						} else if (emplrContriintEmployer.length > 1) {
							if (emplrContriintEmployer[1].length() == 1)
								subempContri = subempContri + "0";
						}

						else if (emplrContriintEmployer.length == 1) {
							subempContri = subempContri + ".00";
						}
					} else {
						subempContri = subempContri + ".00";
					}

					totalContribution = (govEmpContri != null)
							? Double.parseDouble(govEmpContri) + Double.parseDouble(subempContri)
							: 0.00 + Double.parseDouble(subempContri);
					TotalContri = totalContribution.toString();
					countsum = countsum + count;
					GovContributionSum = (govcontiSum != null)
							? Double.parseDouble(govcontiSum) + Double.parseDouble(govEmpContri)
							: 0.00 + Double.parseDouble(govEmpContri);
					govcontiSum = GovContributionSum.toString();
					subcontributionSum = (subcontiSum != null)
							? Double.parseDouble(subcontiSum) + Double.parseDouble(subempContri)
							: 0.00 + Double.parseDouble(subempContri);
					subcontiSum = subcontributionSum.toString();
					TotalContributionsum = (govcontiSum != null)
							? Double.parseDouble(govcontiSum) + Double.parseDouble(subcontiSum)
							: 0.00 + Double.parseDouble(subcontiSum);
					TotalContrisum = TotalContributionsum.toString();
					gLogger.info("i " + i);
					gLogger.info("totalsize" + totalsize);
					gLogger.info("j " + j);
					gLogger.info("lEmpCountContrDdo.length" + lEmpCountContrDdoMap.size());

					if (!prvDdoReg.equals(ddoRegNo) && j <= lEmpCountContrDdoMap.size()) {

						if ( lEmpTotalContrDdoMap.size() > 0) {
							gLogger.info("j is *******************" + j);
									//ddoRegNo
							String[] totalAmtDH = lEmpTotalContrDdoMap.get(ddoRegNo).split("#");

							EmpleeDHContri = Double.parseDouble(totalAmtDH[0]);
							gLogger.info("EmpleeDHContri is *********" + EmpleeDHContri);

							totalEmplyDHContri = nosci(EmpleeDHContri);
							gLogger.info("totalEmplyDHContri is *********" + totalEmplyDHContri);
							EmplrDHContri = Double.parseDouble(totalAmtDH[1]);
							totalEmplyerDHContri = nosci(EmplrDHContri);
							totalEDHContri = Double.parseDouble(totalEmplyDHContri)
									+ Double.parseDouble(totalEmplyerDHContri);
							gLogger.info("totalEDHContri is *********" + totalEDHContri);

						}
						String totaloverDHallAmt = nosci(totalEDHContri);

						// total
						gLogger.info("totaloverDHallAmt is *********" + totaloverDHallAmt);

						overallDHAmt = totaloverDHallAmt.toString().split("\\.");
						gLogger.info("totaloverDHallAmt is *********" + totaloverDHallAmt);
						if (overallDHAmt.length != 1) {
							if (totaloverDHallAmt.equals("0")) {
								totaloverDHallAmt = "0.00";
							}

							else if (overallDHAmt[1].length() == 1) {
								totaloverDHallAmt = totaloverDHallAmt + "0";
							}

							else if (overallDHAmt[1].length() > 2) {
								totaloverDHallAmt = decRoundOff(totaloverDHallAmt);

							}
						}

						else {
							totaloverDHallAmt = totaloverDHallAmt + ".00";

						}

						// emp

						totalempoverallDHAmt = totalEmplyDHContri.toString().split("\\.");
						gLogger.info("totalEmplyDHContri is *********" + totalEmplyDHContri);
						if (totalempoverallDHAmt.length != 1) {
							if (totalEmplyDHContri.equals("0")) {
								totalEmplyDHContri = "0.00";
							}

							else if (totalempoverallDHAmt[1].length() == 1) {
								totalEmplyDHContri = totalEmplyDHContri + "0";
							}

							else if (totalempoverallDHAmt[1].length() > 2) {

								totalEmplyDHContri = decRoundOff(totalEmplyDHContri);

							}
						} else {
							totalEmplyDHContri = totalEmplyDHContri + ".00";

						}

						// emplr

						totalemplroverallDHAmt = totalEmplyerDHContri.toString().split("\\.");
						gLogger.info("totalemplroverallDHAmt is *********" + totalemplroverallDHAmt);
						if (totalemplroverallDHAmt.length != 1) {
							if (totalEmplyerDHContri.equals("0")) {
								totalEmplyerDHContri = "0.00";
							}

							else if (totalemplroverallDHAmt[1].length() == 1) {
								totalEmplyerDHContri = totalEmplyerDHContri + "0";
							} else if (totalemplroverallDHAmt[1].length() > 2) {
								totalEmplyerDHContri = decRoundOff(totalEmplyerDHContri);
							}
						} else {

							totalEmplyerDHContri = totalEmplyerDHContri + ".00";
						}
						gLogger.info("All is well");

						i = i + 1;
						gLogger.info("All is well i" + i);
						j = j + 1;
						empCount = 1;
						gLogger.info("All is well j" + j);
						if (j <= lEmpCountContrDdoMap.size()) {
							Strbr.append(i + "^");
							Strbr.append("DH" + "^");
							Strbr.append("1" + "^");
							Strbr.append(j + "^");
							Strbr.append(ddoRegNo + "^");
							gLogger.info("All is well ddoRegNo" + ddoRegNo);
							gLogger.info("All is well emprecCount" + emprecCount);
							gLogger.info("All is well lEmpCountContrDdo length" + lEmpCountContrDdoMap.size());
							gLogger.info("All is well lEmpCountContrDdo" + lEmpCountContrDdoMap.size());
							gLogger.info("All is well lEmpCountContrDdo length" + lEmpCountContrDdoMap.size());

							Long EmpCount = Long.parseLong(lEmpCountContrDdoMap.get(ddoRegNo));

							gLogger.info("All is well lEmpCountContrDdo length" + lEmpCountContrDdoMap.size());
							Strbr.append(EmpCount + "^");
							gLogger.info("All is well EmpCount " + EmpCount);
							Strbr.append(totalEmplyDHContri + "^");
							gLogger.info("All is well totalEmplyDHContri " + totalEmplyDHContri);
							Strbr.append(totalEmplyerDHContri + "^");
							gLogger.info("All is well totalEmplyerDHContri " + totalEmplyerDHContri);
							Strbr.append("^");
							lLngPkIdForDh = IFMSCommonServiceImpl.getNextSeqNum("NSDL_DH_DTLS", inputMap);
							gLogger.info("lLngPkIdForDh++++++++++++++" + lLngPkIdForDh);

							lObjNsdlNps.insertDHDetails(lLngPkIdForDh, i, "DH", "1", j, ddoRegNo, EmpCount,
									totalEmplyDHContri, totalEmplyerDHContri, BatchId);
							// Strbr.append("\n");
							Strbr.append("\r\n");
							temp++;
							emprecCount++;
						}
						gLogger.info("Strbr for DH is" + Strbr.toString());

					}
					prvDdoReg = ddoRegNo;
					++i;
					// Start Employee contribution data
					if (lstemployee != null && !lstemployee.equals("") && j <= lEmpCountContrDdoMap.size()) {

						Strbr.append(i + "^");
						Strbr.append("SD" + "^");
						Strbr.append("1" + "^");
						Strbr.append(j + "^");
						Strbr.append(empCount + "^");
						Strbr.append(pranno + "^");
						Strbr.append(govEmpContri + "^");
						Strbr.append(subempContri + "^");
						Strbr.append("^");
						Strbr.append(TotalContri + "0" + "^");

						// Changes done by Naveen on 13 aug 2015 for Changes in SD "A" changes to "C"
						// Strbr.append("A"+"^^^");
						// String contribType=lObjNsdlNps.getContribType(dcpsid);
						String contribType = "C";
						if (sdAmnt != null && !"".equalsIgnoreCase(sdAmnt)) {
							Strbr.append("A" + "^" + month + "^" + yrCode + "^");
						} else {
							Strbr.append(contribType + "^" + month + "^" + yrCode + "^");
						}
						// end
						if (Long.parseLong(month) < 10) {
							monthId = "0" + month;
						} else if (Long.parseLong(month) >= 10) {
							monthId = month;
						}

						String finyear = "";
						if (Long.parseLong(month) > 2)
							finyear = yrCode + "-" + (Long.parseLong(yrCode) + 1);
						else
							finyear = (Long.parseLong(yrCode) - 1) + "-" + yrCode;

						Strbr.append("Contribution for " + month + "/" + yrCode + "^");
						// Changes done by Naveen on 13 aug 2015 for Changes in SD "A" changes to "C"
						lLngPkIdForSd = IFMSCommonServiceImpl.getNextSeqNum("NSDL_SD_DTLS", inputMap);
						gLogger.info("lLngPkIdForSd++++++++++++++" + lLngPkIdForSd);

						if (sdAmnt != null && !"".equalsIgnoreCase(sdAmnt)) {
							lObjNsdlNps.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount, pranno, govEmpContri,
									subempContri, TotalContri + "0", "A" + "^" + monthId + "^" + yrCode,
									"Contribution for " + month + "/" + yrCode + "", BatchId, ddoRegNo);
						} else {
							lObjNsdlNps.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount, pranno, govEmpContri,
									subempContri, TotalContri + "0", contribType + "^" + monthId + "^" + yrCode,
									"Contribution for " + month + "/" + yrCode + "", BatchId, ddoRegNo);
						}

						// end
						Strbr.append("\r\n");

					}

					++empCount;

				}
				gLogger.info("All fine till nmow");
				// new line
				String lineSeperator = "\r\n";
				// System.getProperty("line.separator");.....not working
				String os = System.getProperty("os.name");
				gLogger.info("All ok till now is ************os*******" + os);
				if (os.toLowerCase().indexOf("unix") > 0) {
					lineSeperator = "\n";
				} else if (os.toLowerCase().indexOf("windows") > 0) {
					lineSeperator = "\r\n";
				}

				gLogger.info("All ok till now is *******************");
				HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");

				System.out.println("treasury code is " + treasuryyno.substring(0, 4));
				//dtoRegNo = lObjNsdlDAO.getDtoRegNo(treasuryyno.substring(0, 4));
				dtoRegNo = lObjNsdlDAO.getDtoRegNo(strDDOCode);
				getFileHeader(outputfile, dtoRegNo);
				gLogger.info("outputfile is *******************" + outputfile);
				gLogger.info("totalsize is *******************" + totalsize);
				gLogger.info("countReg is *******************" + countReg);
				gLogger.info("totalEmplyContri is *******************" + totalEmplyContri);
				gLogger.info("totalEmplyerContri is *******************" + totalEmplyerContri);
				gLogger.info("totaloverallAmt is *******************" + totaloverallAmt);
				gLogger.info("BatchId is *******************" + BatchId);
				gLogger.info("yrCode is *******************" + yrCode);
				gLogger.info("month is *******************" + month);
				lLngPkIdForBh = IFMSCommonServiceImpl.getNextSeqNum("NSDL_BH_DTLS", inputMap);
				gLogger.info("lLngPkIdForBh++++++++++++++" + lLngPkIdForBh);
				getBatchHeader(lLngPkIdForBh, outputfile, totalsize, countReg, totalEmplyContri, totalEmplyerContri,
						totaloverallAmt, BatchId, yrCode, month, dtoRegNo, strDDOCode);
				List nsdlDeatils = lObjNsdlNps.getAllData(yrCode, month, treasuryyno);

				inputMap.put("size", nsdlDeatils.size());
				inputMap.put("nsdlDeatils", nsdlDeatils);
				inputMap.put("selMonth", month);
				inputMap.put("selYear", yrCode);

				inputMap.put("subTr", subTr);
				inputMap.put("FileStatus", "HTE200");
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			resObj.setResultCode(ErrorConstants.ERROR);
			inputMap.put("FileStatus", "HTE202");

		}
		inputMap.put("lLstYears", lLstYears);
		inputMap.put("lLstMonths", lLstMonths);
		resObj.setResultCode(ErrorConstants.SUCCESS);
		resObj.setResultValue(inputMap);
		resObj.setViewName("NPSVALIDATE");
		return resObj;

	}

	private void getFileHeader(PrintWriter br, String dtoRegNo) throws IOException {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String date = "ddMMyyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(date);
		sdf.setTimeZone(TimeZone.getDefault());
		String currentdate = sdf.format(cal.getTime());
		br.write("1" + "^");
		br.write("FH" + "^");
		br.write("P" + "^");
		br.write(dtoRegNo + "^");
		br.write("1" + "^");
		br.write(currentdate);
		br.write("^A");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("\r\n"); // br.write("\n");
		gLogger.info(br);
	}

	private void getBatchHeader(Long lLngPkIdForBh, PrintWriter br, int count, long ddoCount, String govContri,
			String SubContri, String Total, String batchId, String yrCode, String month, String dtoRegNo,
			String strDDOCode) throws IOException {

		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String date = "ddMMyyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(date);
		sdf.setTimeZone(TimeZone.getDefault());
		String currentdate = sdf.format(cal.getTime());

		br.write("2" + "^");
		br.write("BH" + "^");
		br.write("1" + "^");
		br.write("R" + "^");
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
		NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
		lObjNsdlNps.insertBatchHeader(lLngPkIdForBh, "2", "BH", "1", "R", dtoRegNo, currentdate, dtoRegNo + batchId,
				ddoCount, count, govContri, SubContri, Total, batchId, yrCode, month, strDDOCode);

		// br.write("\n");
	}

	private void getFileHeaderforfpu(PrintWriter br) throws IOException {

		br.write("1" + "^");
		br.write("FH" + "^");
		br.write("O" + "^");
		br.write("3100812" + "^");
		br.write("1" + "^");
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

		br.write("2" + "^");
		br.write("BH" + "^");
		br.write("1" + "^");
		br.write("R" + "^");
		br.write("3100812" + "^");
		br.write(currentdate + "^");
		br.write(1111 + "^");
		br.write("^");
		br.write("1" + "^");
		br.write(count + "^");
		br.write(govContri + "^");
		br.write(SubContri + "^");
		br.write("^");
		br.write(Total + "^");
		br.write("\n");
	}

	private void getDTOHeaderforfpu(PrintWriter br, int count, String govContri, String SubContri) throws IOException {

		br.write("3" + "^");
		br.write("DH" + "^");
		br.write("1" + "^");
		br.write("1" + "^");
		br.write("1" + "^");
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
		if (d > 1) {// big number
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
		} else {
			// for little numbers use the default or you will
			// loose accuracy

			return javaString;
		}

	}

	public static String decRoundOff(String number) {
		String s = String.format(("%.2f"), Double.parseDouble(number));
		return s;
	}

	public ResultObject loadNPSNSDLGenFiles(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		String cmbDep = "2";
		List nsdlDeatils = null;
		String gLocId = "";
		Object[] obj = null;
		try {
			setSessionInfo(inputMap);
			String month = StringUtility.getParameter("cmbMonth", request);
			String year = StringUtility.getParameter("cmbYear", request);
			Calendar cal = Calendar.getInstance();
			String currmonth = (new Integer((cal.get(Calendar.MONTH) + 1))).toString();
			String curryear = (new Integer(cal.get(Calendar.YEAR))).toString();
			Long currentyear = null;
			Long currentmonth = null;
			if (currmonth.equals("1")) {
				currentmonth = 12L;
				currentyear = Long.parseLong(curryear) - 1;

			} else {
				currentmonth = Long.parseLong(currmonth);
				currentyear = Long.parseLong(curryear);

			}

			gLogger.info("currentmonth is *******************" + currentmonth);
			gLogger.info("currentyear is *******************" + currentyear);

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			String ddoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			String trCode = ddoCode.substring(0, 4);
			gLogger.info("trCode is *******************" + trCode);

			if (StringUtility.getParameter("cmbMonth", request) != null
					&& !(StringUtility.getParameter("cmbMonth", request).equals(""))
					&& Long.parseLong(StringUtility.getParameter("cmbMonth", request)) != -1
					&& StringUtility.getParameter("cmbYear", request) != null
					&& !(StringUtility.getParameter("cmbYear", request).equals(""))
					&& Long.parseLong(StringUtility.getParameter("cmbYear", request)) != -1
			// && StringUtility.getParameter("trCode", request)!=null &&
			// !(StringUtility.getParameter("trCode", request).equals("")) &&
			// Long.parseLong(StringUtility.getParameter("trCode", request))!=-1
			) {

				currentmonth = Long.parseLong(StringUtility.getParameter("cmbMonth", request));
				currentyear = Long.parseLong(StringUtility.getParameter("cmbYear", request));
				trCode = ddoCode.substring(0, 4);// StringUtility.getParameter("trCode", request);
				gLogger.info("trCode is *************11111111111111******" + trCode);

				if (StringUtility.getParameter("cmbDep", request) != null
						&& !(StringUtility.getParameter("cmbDep", request).equals(""))
						&& Long.parseLong(StringUtility.getParameter("cmbDep", request)) != -1) {
					cmbDep = StringUtility.getParameter("cmbDep", request);
				}

			}

			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

			List subTr = lObjNsdlDAO.getAllSubTreasury(gStrLocationCode);

			if (cmbDep != null && !"".equalsIgnoreCase(cmbDep) && "2".equalsIgnoreCase(cmbDep)) {
				gLogger.info("in if condn^^^^^^^^^^^^^^^^^^^^^^^^");
				nsdlDeatils = lObjNsdlDAO.getAllData(currentyear.toString(), currentmonth.toString(), trCode);
				gLogger.info("in if condn^^^^^^^^^^^^^^^^^^^^^^^^" + nsdlDeatils.size());
			} else {
				nsdlDeatils = lObjNsdlDAO.getAllDataDeputation(currentyear.toString(), currentmonth.toString(), trCode);
			}
			String filestatusss = "";
			String filenames = "";

			if (nsdlDeatils.size() > 0) {

				Object[] tuple5 = (Object[]) nsdlDeatils.get(0);
				filestatusss = tuple5[4].toString();
				filenames = tuple5[0].toString();
			}

			List lLstYears = lObjNsdlDAO.getFinyear();
			inputMap.put("selMonth", currentmonth);
			inputMap.put("subTr", subTr);
			inputMap.put("selYear", currentyear);

			List lLstMonths = lObjNsdlDAO.getMonths();
			inputMap.put("size", nsdlDeatils.size());
			inputMap.put("nsdlDeatils", nsdlDeatils);

			gLogger.info("Month and year is " + lLstMonths.size());
			inputMap.put("lLstYears", lLstYears);
			inputMap.put("lLstMonths", lLstMonths);
			inputMap.put("idDeputation", cmbDep);
			resObj.setResultValue(inputMap);
			resObj.setViewName("NPSVALIDATE");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject createTextFilesForNSDL(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		String BatchId = null;
		String dhDtls = "";
		String ddoRegNo = "";

		try {
			setSessionInfo(inputMap);
			if (StringUtility.getParameter("fileNumber", request) != null
					&& !StringUtility.getParameter("fileNumber", request).equals("")) {
				String fileNumber = StringUtility.getParameter("fileNumber", request);
				NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

				BatchId = fileNumber;
				String dtoRegNo = "";
				DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
				String strDDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
				//dtoRegNo = lObjNsdlNps.getDtoRegNo(fileNumber.substring(0, 4));
				dtoRegNo = lObjNsdlNps.getDtoRegNo(strDDOCode);

				StringBuffer sb = new StringBuffer();
				PrintWriter outputfile = response.getWriter();
				// getFileHeader(outputfile);
				sb.append("1^FH^P^" + dtoRegNo + "^1^^A^^^^^");
				gLogger.info("All fine 1 is *********");
				String bhData = lObjNsdlNps.getBatchData(fileNumber);
				gLogger.info("All fine 2 is *********" + bhData);
				// outputfile.write(bhData);
				// outputfile.write("\r\n");
				sb.append("\r\n");
				sb.append(bhData);
				sb.append("\r\n");
				gLogger.info("All fine 3 is *********");
				List dhData = lObjNsdlNps.getDHData(fileNumber);
				if (dhData != null && dhData.size() > 0) {
					Iterator IT = dhData.iterator();

					Object[] lObj = null;
					while (IT.hasNext()) {
						lObj = (Object[]) IT.next();
						dhDtls = lObj[0].toString();
						ddoRegNo = lObj[1].toString();
						// outputfile.write(dhDtls);

						// outputfile.write("\r\n");

						sb.append(dhDtls);
						sb.append("\r\n");

						List sdDtls = lObjNsdlNps.getSDDtls(fileNumber, ddoRegNo);
						for (int i = 0; i < sdDtls.size(); i++) {
							// outputfile.write(sdDtls.get(i).toString());
							// outputfile.write("\r\n");
							sb.append(sdDtls.get(i).toString());
							sb.append("\r\n");

						}

					}

				}

				gLogger.info("All fine 4 is *********");
				// gLogger.info("stringBuifefer is *********"+sb.toString());

				// PasswordEncryption objPasswordEncryption = new PasswordEncryption();
				// gLogger.info("password encryption is
				// ****************"+objPasswordEncryption.crypt(sb.toString()));
				// lObjNsdlNps.updateMD5hash(objPasswordEncryption.crypt(sb.toString()),fileNumber);

				String lStrFileName = BatchId;

				try {
					String fileName = lStrFileName + ".txt";
					response.setContentType("text/plain;charset=UTF-8");

					response.addHeader("Content-disposition", "attachment; filename=" + fileName);
					response.setCharacterEncoding("UTF-8");

					outputfile.write(sb.toString());
					outputfile.flush();

					// File ftemp=new File(fileName);
					// ftemp.delete();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					gLogger.info("All fine 7 is *********");
					if (outputfile != null)
						outputfile.close();
				}

			}

			gLogger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("ExportReportPage");
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject deleteTextFilesForNSDL(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		boolean flag = false;

		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);
			Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			if (StringUtility.getParameter("fileNumber", request) != null
					&& !StringUtility.getParameter("fileNumber", request).equals("")) {
				String fileNumber = StringUtility.getParameter("fileNumber", request);
				NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
				lObjNsdlNps.deleteNsdlFile(fileNumber);
				flag = true;
			}

			String lSBStatus = getResponseXMLDoc(flag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	public ResultObject generateNSDLPaybill(Map inputMap) {
		gLogger.info("inside generateNSDLPaybill");
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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		Long nsdl_paybill_pk = 0L;
		Long billNo = 0l;
		List billDetails = null;
		Object obj[];
		String billCreationDate = null;
		String billGeneratedMonth = null;

		try {
			setSessionInfo(inputMap);
			String month = StringUtility.getParameter("cmbMonth", request);

			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			List lLstMonths = lObjNsdlDAO.getMonths();
			String Month = StringUtility.getParameter("Month", request);
			String Year = StringUtility.getParameter("Year", request);
			String fileNumber = StringUtility.getParameter("fileNumber", request);
			double employeeContribution = Double
					.parseDouble(StringUtility.getParameter("employeeContribution", request).toString());
			String empContri = nosci(employeeContribution);
			double employerContribution = Double
					.parseDouble(StringUtility.getParameter("employerContribution", request).toString());
			String emplrContri = nosci(employerContribution);
			double totalContribution = employeeContribution + employerContribution;
			String totalContri = nosci(totalContribution);
			String totalContributionAmountInWords = EnglishDecimalFormat
					.convertWithSpace(new BigDecimal(totalContribution));
			String employeeContributionInWords = EnglishDecimalFormat
					.convertWithSpace(new BigDecimal(employeeContribution));
			String employerContributionInWords = EnglishDecimalFormat
					.convertWithSpace(new BigDecimal(employerContribution));
			String stringMonth = "";
			int paybillMonth = 0;
			gLogger.info("totalContributionAmountInWords" + totalContributionAmountInWords);
			gLogger.info("Month" + Month);
			gLogger.info("Year" + Year);
			gLogger.info("fileNumber" + fileNumber);
			gLogger.info("employeeContribution" + employeeContribution);
			gLogger.info("employerContribution" + employerContribution);
			inputMap.put("totalContributionAmountInWords", totalContributionAmountInWords);
			inputMap.put("totalContribution", totalContri);
			// added for bill rejected by beams changes
			String billId = "";
			String appendBillid = "";
			String fileNameTODelete = "";
			billId = StringUtility.getParameter("billId", request).toString();
			gLogger.info("billId" + billId);
			if (billId != null && !billId.equals("") && !billId.equals("NA")) {
				appendBillid = billId.substring(9);
				gLogger.info("appendBillid" + appendBillid);
				fileNameTODelete = fileNumber + "_" + appendBillid;
				lObjNsdlDAO.updateBillStatus(billId, fileNameTODelete);
			}
			IdGenerator idGen = new IdGenerator();
			nsdl_paybill_pk = idGen.PKGenerator("NSDL_BILL_dtls", inputMap);
			String status = lObjNsdlDAO.getBillStatus(fileNumber);

			gLogger.info("status" + status);
			if (status != null && Long.parseLong(status) == 0) {

				lObjNsdlDAO.createNSDLBillGenration(nsdl_paybill_pk, Year, Month, employeeContribution,
						employerContribution, totalContribution, fileNumber);
				billDetails = lObjNsdlDAO.getBillNoDate(fileNumber);
				if (billDetails != null && !billDetails.isEmpty()) {
					Iterator it = billDetails.iterator();
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						billNo = Long.parseLong(obj[0].toString());
						gLogger.info("billNo" + billNo);
						billCreationDate = obj[1].toString();
						gLogger.info("billCreationDate" + billCreationDate);
						billGeneratedMonth = obj[2].toString();
						paybillMonth = Integer.parseInt(obj[2].toString());
					}

				}
				Map monthMap = new HashMap();
				monthMap.put(1, "January");
				monthMap.put(2, "February");
				monthMap.put(3, "March");
				monthMap.put(4, "April");
				monthMap.put(5, "May");
				monthMap.put(6, "June");
				monthMap.put(7, "July");
				monthMap.put(8, "August");
				monthMap.put(9, "September");
				monthMap.put(10, "October");
				monthMap.put(11, "November");
				monthMap.put(12, "December");
				stringMonth = (String) monthMap.get(paybillMonth);
				inputMap.put("billNo", billNo);
				inputMap.put("billCreationDate", billCreationDate);
				inputMap.put("billGeneratedMonth", billGeneratedMonth);
				inputMap.put("employeeContribution", empContri);
				inputMap.put("employerContribution", emplrContri);
				inputMap.put("employeeContributionInWords", employeeContributionInWords);
				inputMap.put("employerContributionInWords", employerContributionInWords);
				inputMap.put("selectedMonth", stringMonth);
				inputMap.put("successmsg", "Bill  Generated Successfully");
				resObj.setResultValue(inputMap);
				resObj.setViewName("NPSVALIDATE");
			} else {
				inputMap.put("msg", "Bill is Already Generated");
				inputMap.put("billNo", billNo);
				inputMap.put("billCreationDate", billCreationDate);
				inputMap.put("billGeneratedMonth", billGeneratedMonth);
				resObj.setResultValue(inputMap);
				resObj.setViewName("NPSVALIDATE");
			}

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewGenerateNSDLPaybill(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		Long nsdl_paybill_pk = 0L;
		String transactionId = "";
		Long billNo = 0l;
		List billDetails = null;
		Object obj[];
		String billCreationDate = null;
		String billGeneratedMonth = null;
		String locId = SessionHelper.getLocationCode(inputMap);

		try {
			setSessionInfo(inputMap);
			String month = StringUtility.getParameter("cmbMonth", request);
			gLogger.info("month from jsp" + month);

			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			List lLstMonths = lObjNsdlDAO.getMonths();
			String Month = StringUtility.getParameter("Month", request);
			String Year = StringUtility.getParameter("Year", request);
			String fileNumber = StringUtility.getParameter("fileNumber", request);
			String stringMonth = "";
			double employeeContribution = Double
					.parseDouble(StringUtility.getParameter("employeeContribution", request).toString());
			String empContri = nosci(employeeContribution);
			double employerContribution = Double
					.parseDouble(StringUtility.getParameter("employerContribution", request).toString());
			String emplrContri = nosci(employerContribution);
			double totalContribution = employeeContribution + employerContribution;
			String totalContri = nosci(totalContribution);
			String totalContributionAmountInWords = EnglishDecimalFormat
					.convertWithSpace(new BigDecimal(totalContribution));
			String employeeContributionInWords = EnglishDecimalFormat
					.convertWithSpace(new BigDecimal(employeeContribution));
			String employerContributionInWords = EnglishDecimalFormat
					.convertWithSpace(new BigDecimal(employerContribution));
			gLogger.info("totalContributionAmountInWords" + totalContributionAmountInWords);
			int paybillMonth = lObjNsdlDAO.getMonthId(fileNumber);
			gLogger.info("paybillMonth" + paybillMonth);
			gLogger.info("Year" + Year);
			inputMap.put("fileNumber", fileNumber);
			gLogger.info("fileNumber" + fileNumber);
			Map monthMap = new HashMap();
			monthMap.put(1, "January");
			monthMap.put(2, "February");
			monthMap.put(3, "March");
			monthMap.put(4, "April");
			monthMap.put(5, "May");
			monthMap.put(6, "June");
			monthMap.put(7, "July");
			monthMap.put(8, "August");
			monthMap.put(9, "September");
			monthMap.put(10, "October");
			monthMap.put(11, "November");
			monthMap.put(12, "December");
			gLogger.info("stringMonth****" + monthMap.get(1));
			gLogger.info("stringMonth" + monthMap.get(paybillMonth));

			stringMonth = (String) monthMap.get(paybillMonth);
			gLogger.info("stringMonth" + stringMonth);
			gLogger.info("employeeContribution" + employeeContribution);
			gLogger.info("employerContribution" + employerContribution);
			inputMap.put("totalContributionAmountInWords", totalContributionAmountInWords);
			inputMap.put("totalContribution", totalContri);
			String treasuryName = lObjNsdlDAO.getTreasuryName(locId);
			transactionId = lObjNsdlDAO.getTransactionId(fileNumber);
			inputMap.put("transactionId", transactionId);
			inputMap.put("treasuryName", treasuryName);
			IdGenerator idGen = new IdGenerator();
			nsdl_paybill_pk = idGen.PKGenerator("NSDL_BILL_dtls", inputMap);
			String status = lObjNsdlDAO.getBillStatus(fileNumber);
			if (status != null && Long.parseLong(status) == 0) {
				// lObjNsdlDAO.createNSDLBillGenration(nsdl_paybill_pk,Year,Month,employeeContribution,employerContribution,totalContribution,fileNumber);
				billDetails = lObjNsdlDAO.getBillNoDate(fileNumber);

				inputMap.put("viewmsg", "bill is not yet generated");
				inputMap.put("billNo", billNo);
				inputMap.put("billCreationDate", billCreationDate);
				inputMap.put("billGeneratedMonth", billGeneratedMonth);

				resObj.setResultValue(inputMap);
				resObj.setViewName("NPSVALIDATE");
			} else {
				billDetails = lObjNsdlDAO.getBillNoDate(fileNumber);

				if (billDetails != null && !billDetails.isEmpty()) {
					Iterator it = billDetails.iterator();
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						billNo = Long.parseLong(obj[0].toString());
						gLogger.info("billNo" + billNo);
						billCreationDate = obj[1].toString();
						gLogger.info("billCreationDate" + billCreationDate);
						billGeneratedMonth = obj[2].toString();
					}

				}
				inputMap.put("billNo", billNo);
				inputMap.put("billCreationDate", billCreationDate);
				inputMap.put("billGeneratedMonth", billGeneratedMonth);
				inputMap.put("employeeContribution", empContri);
				inputMap.put("employerContribution", emplrContri);
				inputMap.put("employeeContributionInWords", employeeContributionInWords);
				inputMap.put("employerContributionInWords", employerContributionInWords);
				inputMap.put("selectedMonth", stringMonth);
				resObj.setResultValue(inputMap);
				resObj.setViewName("mtr45inner");
			}

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject voucherEntry(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;

		try {
			setSessionInfo(inputMap);
			String Month = StringUtility.getParameter("cmbMonth", request);
			String Year = StringUtility.getParameter("cmbYear", request);
			String fileNumber = StringUtility.getParameter("fileNumber", request);
			gLogger.info("Month" + Month);
			gLogger.info("Year" + Year);
			gLogger.info("fileNumber" + fileNumber);
			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			List lLstMonths = lObjNsdlDAO.getMonths();
			inputMap.put("Month", Month);
			inputMap.put("Year", Year);
			inputMap.put("fileNumber", fileNumber);
			resObj.setResultValue(inputMap);
			resObj.setViewName("approveNSDLBill");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject approveNSDLPayBill(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;

		try {
			setSessionInfo(inputMap);
			String month = StringUtility.getParameter("Month", request);
			String year = StringUtility.getParameter("Year", request);
			String voucherNo = StringUtility.getParameter("voucherNo", request);
			String vouchedate = StringUtility.getParameter("voucherDate", request);
			gLogger.info("year" + year);
			gLogger.info("month" + month);
			gLogger.info("voucherNo" + voucherNo);
			gLogger.info("vouchedate" + vouchedate);
			String fileNumber = StringUtility.getParameter("fileNumber", request);
			gLogger.info("fileNumber" + fileNumber);
			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			List lLstMonths = lObjNsdlDAO.getMonths();
			inputMap.put("month", month);
			inputMap.put("year", year);
			inputMap.put("fileNumber", fileNumber);
			if (fileNumber != null) {
				lObjNsdlDAO.updateVoucherEntry(month, year, fileNumber, voucherNo, vouchedate);
				inputMap.put("msg", "Details Apporved Successfully");
			}

			resObj.setResultValue(inputMap);
			resObj.setViewName("approveNSDLBill");

		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}


	/* reference to File vlidate from NPSFILEgeneration validateFileNSdl */
	public ResultObject validateFileNSdl(Map inputMap) throws Exception {
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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		String dhDtls = "";
		String ddoRegNo = "";
		StringBuilder sb11 = new StringBuilder();
		String errorData = " ";
		String ext = "";
		String ddoCode = "";
		String lSBStatus = null;
		String lStrResult = null;
		String validSatatus = null;
		String msgStr = null;
		String fileVerifySatatus = null;
		try {
			setSessionInfo(inputMap);
			String Fileno = StringUtility.getParameter("Fileno", request);
			int currentmonth = Integer.parseInt(StringUtility.getParameter("Month", this.request));
			int currentyear = Integer.parseInt(StringUtility.getParameter("Year", this.request));
			gLogger.info("Fileno****************" + Fileno);
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
			// String filePath=request.getSession().getServletContext().getRealPath("/") +
			// Fileno.concat(".txt");
			// added By Naveen
			String strDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			int count = lObjNsdlDAO.getCountOfNpsBillNotLocked(strDDOCode, currentmonth, currentyear);
	         if (count > 0) {
	        	 	validSatatus = "wrong";
					fileVerifySatatus = "N";
					msgStr = "You cannot Validate the file as previous Months NPS regular data file is not locked" ;
	         } else {
	         
	      
			// String Fileno =nsdlDetailsModel.getFileId();
			File f4 = null;
			f4 = new File(Fileno.concat(".txt"));
			f4.delete();
			f4.createNewFile();
			ddoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			gLogger.info("New file created");
			String key = "";
			String rootPath = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			String OUTPUT_ZIP_FILE = null;
			String OUTPUT_ZIP_Contri_FILE = null;
			if (System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {

				OUTPUT_ZIP_Contri_FILE = gObjRsrcBndles.getString("NPS.OUTPUT_FILE");
			} else {
				OUTPUT_ZIP_Contri_FILE = gObjRsrcBndles.getString("NPS.OUTPUT_FILE_SERVER");
			}

			String Path = OUTPUT_ZIP_Contri_FILE;
			String directoryName = Path.concat(ddoCode);
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

			// ended By Naveen

			NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
			//String dtoRegNo = lObjNsdlNps.getDtoRegNo(Fileno.substring(0, 4));
			 String dtoRegNo = lObjNsdlNps.getDtoRegNo(ddoCode);

			FileWriter fw = new FileWriter(filePath);
			BufferedWriter br = new BufferedWriter(fw);

			br.write("1" + "^");
			br.write("FH" + "^");
			br.write("P" + "^");
			br.write(dtoRegNo + "^");
			br.write("1" + "^");
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
			if (dhData != null && dhData.size() > 0) {
				Iterator IT = dhData.iterator();

				Object[] lObj = null;
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

			gLogger.info("filePath is***********" + filePath.toString());
			gLogger.info("path is  is***********" + fw.toString());
			gLogger.info("fw is***********" + br.toString());

			String fvuFilePtah = filePath.replace("txt", "fvu");
			String errFilePtah = filePath.replace("txt", "err");
			String[] args = { filePath, errFilePtah, fvuFilePtah, "0", "1.44" };
			gLogger.info("inputParametersis " + args[0]);
			gLogger.info("inputParametersis " + args[1]);
			gLogger.info("inputParametersis " + args[2]);

			int fileStatus = 0;
			PAOFvu.main(args);

			File f5 = null;
			f5 = new File(Fileno.concat(".txt"));
			System.out.println("File to be deleted" + f5);
			f5.deleteOnExit();

			File f = new File(new File(fvuFilePtah).getAbsolutePath());
			File f1 = new File(new File(errFilePtah).getAbsolutePath());
		
			if (f.exists() && !f.isDirectory()) {
				BufferedReader br1 = new BufferedReader(new FileReader(new File(fvuFilePtah).getAbsolutePath()));

				ext = ".fvu";
				String line = br1.readLine();
				fileStatus = 1;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					gLogger.info(sb11.toString());
					line = br1.readLine();
				}
				validSatatus = "correct";
				fileVerifySatatus = "Y";
				msgStr = " File validated successfully.";
			} else if (f1.exists() && !f1.isDirectory()) {
				BufferedReader br1 = new BufferedReader(new FileReader(new File(errFilePtah).getAbsolutePath()));

				ext = ".err";
				String line = br1.readLine();
				fileStatus = 2;
				while (line != null) {
					sb11.append(line);
					sb11.append("\r\n");
					gLogger.info(sb11.toString());
					line = br1.readLine();
				}

				errorData = sb11.toString();
				validSatatus = "wrong";
				fileVerifySatatus = "N";
				msgStr = "File is rejected.\n" + errorData;

			}
			lObjNsdlDAO.updateFileStatus(fileStatus, Fileno, errorData);
			}
			if (!fileVerifySatatus.equals("")) {
				lSBStatus = getResponseXMLDocMsg(validSatatus, msgStr).toString();
				lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
				inputMap.put("ajaxKey", lStrResult);
				resObj.setResultValue(inputMap);
				resObj.setViewName("ajaxData");
			}

			 
		} catch (Exception e) {
			validSatatus = "wrong";
			msgStr = "File Validation exception occurs .";
			lSBStatus = getResponseXMLDocMsg(validSatatus, msgStr).toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			gLogger.info("File Validation exception occur .");
		} finally {

			gLogger.info("Outer finally block validateTextFile");
		}

		return resObj;
	}

	/* reference to File vlidate from NPSFILEgeneration */

	private StringBuffer getRAWData(String samScmString, String string) {
		StringBuilder rawDataBuffer = new StringBuilder();
		StringTokenizer rawData = new StringTokenizer(samScmString, string);

		int counter = 0;
		while (rawData.hasMoreTokens()) {
			String token = rawData.nextToken();
			if (counter == 0) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 1) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 5) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 6) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 7) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 8) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 9) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 10) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 11) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 12) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 13) {
				rawDataBuffer.append(token.trim() + "^");
			} else if (counter == 14) {
				rawDataBuffer.append(token.trim() + "^");
			}

			++counter;
		}
		return new StringBuffer(rawDataBuffer.toString());
	}

	public ResultObject createErrorFilesForNSDL(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		String BatchId = null;
		String dtoRegNo = null;
		try {
			setSessionInfo(inputMap);
			if (StringUtility.getParameter("fileNumber", request) != null
					&& !StringUtility.getParameter("fileNumber", request).equals("")) {
				String fileNumber = StringUtility.getParameter("fileNumber", request);
				NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());

				//dtoRegNo = lObjNsdlNps.getDtoRegNo(fileNumber.substring(0, 4));
				dtoRegNo = lObjNsdlNps.getDtoRegNo(fileNumber.substring(0, 4));
				BatchId = fileNumber;
				PrintWriter outputfile = response.getWriter();
				getFileHeader(outputfile, dtoRegNo);
				String bhData = lObjNsdlNps.getErrorData(fileNumber);
				outputfile.write(bhData);
				String lStrFileName = BatchId;
				try {
					String fileName = lStrFileName + ".err";
					response.setContentType("text/plain;charset=UTF-8");
					response.addHeader("Content-disposition", "attachment; filename=" + fileName);
					response.setCharacterEncoding("UTF-8");
					gLogger.info("File is" + outputfile);
					outputfile.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (outputfile != null)
						outputfile.close();
				}
			}
			resObj.setResultValue(inputMap);
			resObj.setViewName("ExportReportPage");
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject generateFVUorErrorFile(Map inputMap) {

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
		Long lLongEmployeeAmt = 0L;
		Long lLongEmployerAmt = 0L;
		Long TotalAmt = 0L;
		String BatchId = null;
		String dhDtls = "";
		String ddoRegNo = "";

		try {
			setSessionInfo(inputMap);
			PrintWriter outputfile = response.getWriter();
			if (StringUtility.getParameter("filePath", request) != null
					&& !StringUtility.getParameter("filePath", request).equals("")) {
				String filePath = StringUtility.getParameter("filePath", request);

				String filename = StringUtility.getParameter("filename", request);

				String ext = StringUtility.getParameter("ext", request);

				BufferedReader br1 = new BufferedReader(new FileReader(new File(filePath).getAbsolutePath()));

				StringBuilder sb1 = new StringBuilder();
				String line = br1.readLine();

				while (line != null) {
					sb1.append(line);
					sb1.append("\r\n");
					line = br1.readLine();
				}

				gLogger.info("All fine 4 is *********");
				// gLogger.info("stringBuifefer is *********"+sb.toString());

				// PasswordEncryption objPasswordEncryption = new PasswordEncryption();
				// gLogger.info("password encryption is
				// ****************"+objPasswordEncryption.crypt(sb.toString()));
				// lObjNsdlNps.updateMD5hash(objPasswordEncryption.crypt(sb.toString()),fileNumber);

				String lStrFileName = BatchId;

				try {
					String fileName = filename.concat(ext);
					response.setContentType("text/plain;charset=UTF-8");

					response.addHeader("Content-disposition", "attachment; filename=" + fileName);
					response.setCharacterEncoding("UTF-8");

					outputfile.write(sb1.toString());
					outputfile.flush();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					gLogger.info("All fine 7 is *********");
					if (outputfile != null)
						outputfile.close();
				}

			}

			gLogger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("ExportReportPage");
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public void zipFiles(List<String> files, String zipFilePath) {

		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(zipFilePath);
			zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
			for (String filePath : files) {
				File input = new File(filePath);
				fis = new FileInputStream(input);
				ZipEntry ze = new ZipEntry(input.getName());
				System.out.println("Zipping the file: " + input.getName());
				zipOut.putNextEntry(ze);
				byte[] tmp = new byte[4 * 1024];
				int size = 0;
				while ((size = fis.read(tmp)) != -1) {
					zipOut.write(tmp, 0, size);
				}
				zipOut.flush();
				fis.close();
			}
			zipOut.close();
			System.out.println("Done... Zipped the files...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ex) {

			}
		}
	}

	private String getFinYear(int month, int year) {
		gLogger.info("month:" + month + "year:" + year);
		if (month >= 4 && month <= 12) {

			long nxtYear = year + 1;
			gLogger.info("year:" + year + "nxtYear:" + nxtYear);
			return year + "," + nxtYear;
		} else if (month >= 1 && month < 4) {
			long prevYear = year - 1;
			return prevYear + "," + year;
		}
		return "";
	}

	public ResultObject sendRegularContributionFile(final Map inputMap) {
		final ResultObject resObj = new ResultObject(0, (Object) "FAIL");
		String msgStr = null;
		String sendStatus = null;
		String lSBStatus = null;
		try {
			this.gLogger.info((Object) "inside send contribution file");
			this.setSessionInfo(inputMap);
			final String Fileno = StringUtility.getParameter("Fileno", this.request);
			final String BhID = StringUtility.getParameter("bhid", this.request);
			this.gLogger.info((Object) ("Fileno****************" + Fileno));
			final NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl((Class) null,
					this.serv.getSessionFactory());
			final NsdlNpsFileRegularSendImpl nsdlService = new NsdlNpsFileRegularSendImpl((Class) null,
					this.serv.getSessionFactory());
			final String ddocode = nsdlService.getDdoCode(BhID, Fileno);
			final String dtoUserId = nsdlService.getDtouserId(ddocode);
			final String dtoRegNo = nsdlService.getDtoReg(ddocode);
			this.gLogger.info((Object) ("ddo_code" + ddocode));
			disableSslVerification();

			final String refCode = nsdlService.sendContriFile(this.response, ddocode, Fileno, dtoUserId, dtoRegNo);
			if (refCode.split("~")[0] != null && !refCode.split("~")[0].isEmpty()) {
				nsdlService.updatebatchdetaisls(refCode.split("~")[0], BhID);
				// inputMap.put("checkMsg", "File Uploaded Successfully !!! having Status Code "
				// + refCode);
				sendStatus = "correct";
				msgStr = "File Uploaded Successfully !!! having Status Code " + refCode.split("~")[0];
			} else if (refCode == null) {
				// inputMap.put("checkMsg", "File not uploaded Successfully");
				sendStatus = "wrong";
				msgStr = "File not uploaded Successfully \n" + refCode.split("~")[1];
			} else {
				sendStatus = "wrong";
				inputMap.put("checkMsg", "File not uploaded  " + refCode.split("~")[1]);
				msgStr = "File not uploaded  \n Error Code : \t " + refCode.split("~")[1]+" \n error-description "+refCode.split("~")[2];
			}
			this.gLogger.info((Object) "All fine 8 is *********");
			// resObj.setResultValue((Object)inputMap);
			// resObj.setViewName("NPSVALIDATE");
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			sendStatus = "wrong";
			msgStr = "File not uploaded Successfully .Exception Occure";
			e2.printStackTrace();
			/*
			 * this.gLogger.error((Object)(" Error is : " + e2), (Throwable)e2);
			 * resObj.setResultValue((Object)null); resObj.setThrowable((Throwable)e2);
			 * resObj.setResultCode(-1); resObj.setViewName("errorPage");
			 */
		} finally {

			gLogger.info("Outer finally block validateTextFile");
		}
		/*
		 * sendMsg="wrong"; msgStr="File Validation exception occurs ."; lSBStatus =
		 * getResponseXMLDocMsg(validSatatus ,msgStr).toString(); lStrResult = new
		 * AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		 * inputMap.put("ajaxKey", lStrResult); resObj.setResultValue(inputMap);
		 * resObj.setViewName("ajaxData");
		 * gLogger.info("File Validation exception occur ."); } finally {
		 * 
		 * gLogger.info("Outer finally block validateTextFile"); }
		 */
		lSBStatus = getResponseXMLDocMsg(sendStatus, msgStr).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject getRegularContriButionFile(final Map inputMap) {
		final ResultObject resObj = new ResultObject(0, (Object) "FAIL");
		String msgStr = null;
		String sendStatus = null;
		String lSBStatus = null;
		try {
			this.gLogger.info((Object) "inside send contribution file");
			this.setSessionInfo(inputMap);
			final String Fileno = StringUtility.getParameter("Fileno", this.request);
			final String BhID = StringUtility.getParameter("bhid", this.request);
			this.gLogger.info((Object) ("Fileno****************" + Fileno));
			//final NsdlSrkaNewFileGeneDAOImpl lObjNsdlDAO = new NsdlSrkaNewFileGeneDAOImpl((Class) null, this.serv.getSessionFactory());
			final DcpsCommonDAO lObjDcpsCommonDao = (DcpsCommonDAO) new DcpsCommonDAOImpl((Class) null, this.serv.getSessionFactory());
			final NsdlNpsFileRegularSendImpl nsdlService = new NsdlNpsFileRegularSendImpl((Class) null,this.serv.getSessionFactory());
			final String ddocode = nsdlService.getDdoCode(BhID, Fileno);
			this.gLogger.info((Object) ("ddo_code" + ddocode));
			this.gLogger.info((Object) ("ddo_code" + ddocode));
			final String dtoUserId = nsdlService.getDtouserId(ddocode);
			final String dtoRegNo = nsdlService.getDtoReg(ddocode);
			disableSslVerification();
			String SOURCE_FOLDER = null;
			System.out.println("hello ddo=" + ddocode);
			final String nsdlStatusCode = nsdlService.getNsdlStatusCode(BhID);
			System.out.println("status code>>>" + nsdlStatusCode);
			final String transactioId = nsdlService.getContriStatus(this.response, ddocode, nsdlStatusCode, dtoUserId,Fileno, dtoRegNo);
			NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
			if (transactioId != null && !transactioId.isEmpty() && !transactioId.contains("testfile.html")) {
				if (transactioId != "0" && !transactioId.equalsIgnoreCase("0")) {
					lObjNsdlNps.updateTransactionId(transactioId, BhID);
					inputMap.put("checkMsg", "Transaction ID updated successfully.NSDL Transaction No " + transactioId);
					sendStatus = "correct";
					msgStr = "Transaction ID updated successfully.NSDL Transaction No " + transactioId;
				} else if (transactioId == "1" || transactioId.equalsIgnoreCase("1")) {
					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again late");
					sendStatus = "wrong";
					msgStr = "TRAN ID not Generated, Please wait and try again late";
				} else if (transactioId == "0" || transactioId.equalsIgnoreCase("0")) {
					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again late");
					sendStatus = "wrong";
					msgStr = "TRAN ID not Generated, Please wait and try again late";
				}
				if (transactioId.isEmpty()) {
					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again later");
					sendStatus = "wrong";
					msgStr = "TRAN ID not Generated, Please wait and try again later";
				}
			} else {
				if (transactioId.contains("testfile.html")) {
					inputMap.put("checkMsg", "TRAN ID not Generated, Please wait and try again later");
					sendStatus = "wrong";
					msgStr = "TRAN ID not Generated, Error File generated. Please clik on 'File Validation Result'";
				}
				final String strOSName = System.getProperty("os.name");
				final boolean test = strOSName.contains("Windows");
				if (!strOSName.contains("Windows")) {
					String key = "";
					final String rootPath = "";
					key = "npsfilepathinLinusOS";
					final String filepath = NsdlNpsServiceImpl.OUTPUT_ZIP_FILE = "E:\\Nsdldata";
				}
				SOURCE_FOLDER = String.valueOf(NsdlNpsServiceImpl.OUTPUT_ZIP_FILE) + ddocode + "/testfile.html";
				// final File downloadFile = new File(SOURCE_FOLDER);
				// resObj.setResultValue((Object)inputMap);
				// resObj.setViewName("NPSNSDL");
			}
			// inputMap.put("transactioId", transactioId);
			this.gLogger.info((Object) "All fine 8 is *********");
			/*
			 * resObj.setResultValue((Object)inputMap); resObj.setViewName("NPSVALIDATE");
			 */
		} catch (Exception e) {
			sendStatus = "wrong";
			msgStr = "TRAN ID not Generated.Exception occur";
			e.printStackTrace();
			this.gLogger.error((Object) (" Error is : " + e), (Throwable) e);
			/*
			 * resObj.setResultValue((Object)null); resObj.setThrowable((Throwable)e);
			 * resObj.setResultCode(-1); resObj.setViewName("errorPage");
			 */
		} finally {

		}

		lSBStatus = getResponseXMLDocMsg(sendStatus, msgStr).toString();
		String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		inputMap.put("ajaxKey", lStrResult);
		resObj.setResultValue(inputMap);
		resObj.setViewName("ajaxData");
		return resObj;
	}

	public ResultObject downloadRegularChallanFile(final Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
		try {
			this.gLogger.info("inside send contribution file");
			setSessionInfo(inputMap);
			String transactionid = StringUtility.getParameter("transactionid", request);
			String fileNo = StringUtility.getParameter("fileNo", request);
			String BhID = StringUtility.getParameter("batchid", request);
			this.gLogger.info("transactionid****************" + transactionid);
			NsdlNpsFileRegularSendImpl lObjNsdlDAO = new NsdlNpsFileRegularSendImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			String ddocode = lObjNsdlDAO.getDdoCode(BhID, fileNo);

			ReadFile(response, ddocode, transactionid, fileNo);

			this.gLogger.info("All fine 8 is *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("legacyFileValidate");

		} catch (Exception e) {
			e.printStackTrace();
			this.gLogger.error(" Error is : " + e, e);
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
		String temporaryFile = null;
		String filepath = null;
		if (transactionid.equals("-")) {
			temporaryFile = "backup_testfile.html";
			filepath = OUTPUT_ZIP_Contri_FILE + ddocode + "/" + fileNo + "/" + temporaryFile;
		} else {
			temporaryFile = transactionid + "_Challan.html";
			filepath = OUTPUT_ZIP_Contri_FILE + ddocode + "/" + fileNo + "/backup_" + transactionid + "_Challan.html";
		}

		FileInputStream fileInputStream = null;

		try {
			File challanFilePath = new File(filepath);
			if (challanFilePath.exists()) {
				fileInputStream = new FileInputStream(filepath);
				response.setContentType("text/html");
				response.setHeader("Content-Disposition", "attachment; filename=" + temporaryFile);
			} else {
				filepath = OUTPUT_ZIP_Contri_FILE + ddocode + "/" + fileNo + "//" + fileNo + ".err";
				temporaryFile = fileNo + ".err";
				fileInputStream = new FileInputStream(filepath);
				response.setContentType("text/plain;charset=UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=" + temporaryFile);
				response.setCharacterEncoding("UTF-8");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} finally {
			PrintWriter out = response.getWriter();
			IOUtils.copy(fileInputStream, out);
			response.flushBuffer();
			fileInputStream.close();
			out.flush();
			out.close();
			return;
		}

		// TODO Auto-generated method stub

	}

	public ResultObject deleteRegularContributionFile(final Map inputMap) {
		final ResultObject resObj = new ResultObject(0, (Object) "FAIL");
		boolean flag = false;
		try {

			/* Sets the Session Information */
			setSessionInfo(inputMap);
			Map loginMap = (Map) (Map) inputMap.get("baseLoginMap");
			long langId = Long.parseLong(loginMap.get("langId").toString());
			if (StringUtility.getParameter("fileNumber", request) != null
					&& !StringUtility.getParameter("fileNumber", request).equals("")) {
				String fileNumber = StringUtility.getParameter("fileNumber", request);
				NsdlNpsDAOImpl lObjNsdlNps = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
				lObjNsdlNps.deleteNsdlFile(fileNumber);
				flag = true;
			}

			String lSBStatus = getResponseXMLDoc(flag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setViewName("ajaxData");

			resObj.setResultValue(inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject approveNPSRegular(Map inputMap) {
		ResultObject resObj = new ResultObject(0, "FAIL");
	
		try {
			gLogger.info("inside approveNPSLegacy");
			setSessionInfo(inputMap);
			String fileNumber = StringUtility.getParameter("fileNumber", request);
			String bhid = StringUtility.getParameter("bhid", request);
			String bankRefNo = StringUtility.getParameter("bankRefNo", request);
			String month = StringUtility.getParameter("month", request);
			String year = StringUtility.getParameter("year", request);
			
			gLogger.info("Fileno****************" + fileNumber);
			gLogger.info("bhid****************" + bhid);
			NsdlNpsDAOImpl lObjNsdlDAO = new NsdlNpsDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			
			
			String batch_id = bhid.substring(7,20);
			gLogger.info("batch id"+ batch_id);
			int count = 0;
			String contriType="N";
			count = lObjNsdlDAO.checkNPSRegularFileExistsOrNot(fileNumber,bhid,contriType);
			
			
			if(count!=0)
			{
				lObjNsdlDAO.updatebankReftls(fileNumber,bhid,month,year,bankRefNo,batch_id,contriType);
				inputMap.put("checkMsg","Bank reference updated Successfully");
			}
			else
			{
				inputMap.put("checkMsg","Bank reference   caanot be updateded.");
			}
			gLogger.info("Bank reference page *********");
			resObj.setResultValue(inputMap);
			resObj.setViewName("RegBankref");
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error(" Error is : " + e, e);
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

	private StringBuilder getResponseXMLDocMsg(String status, String msgStr) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(status);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(msgStr);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");
		System.out.println(lStrBldXML);
		return lStrBldXML;
	}
	
}
