package com.tcs.sgv.nps.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;

//import com.tcs.sgv.admin.service.CardexServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.parser.ParserInitializationException;
import org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation;
import org.springframework.core.io.InputStreamResource;

import java.text.SimpleDateFormat;
import com.lowagie.text.pdf.codec.Base64.InputStream;
//import com.sun.xml.internal.stream.Entity;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.nps.dao.FormS1UpdateDAO;
import com.tcs.sgv.nps.dao.FormS1UpdateDAOImpl;
import com.tcs.sgv.nps.valueobject.FrmFormS1Dtls;

import edu.emory.mathcs.backport.java.util.Arrays;
import cra.standalone.subsreg.RunSubsRegFvu;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Watchable;
import java.text.DateFormat;
import com.tcs.sgv.nps.service.NSDLIntegration;
public class NpsEmployeeFileGenerate extends ServiceImpl {

	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */
	//private static String OUTPUT_FILE = "/opt/servers/HTE/jboss-eap-6.2/standalone/deployments/ROOT.war/outputnps/";
	//private static String OUTPUT_FILE = "D:/outputnps/";
	//IMAGE file COPY file path
	//private static String OUTPUT_FILE_PHOTO="/opt/servers/HTE/jboss-eap-6.2/standalone/deployments/ROOT.war/outputnps/images/";
	//private static String OUTPUT_FILE_PHOTO="D:/outputnps/images/"; 
	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	/* Resource bundle for the constants */
	// ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");
	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");
	/*
	 * Function to save the session specific details
	 */
	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
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
		} catch (Exception e) {

		}
	}

	public ResultObject retriveEmployeeNpsList(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lstEmpForFileGenerate = null;
		List empDesigList = null;
		String strDDOCode = null;
		String txtSearch = null;
		String flag = null;
		String dcpsId = null;
		String sevaarthId = null;

		try {
			setSessionInfo(inputMap);
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
			flag = StringUtility.getParameter("flag", request);
			
			if (flag != null && flag.equals("sevarthId")) {
				gLogger.info("Search Value is ***" + txtSearch);
				txtSearch = StringUtility.getParameter("searchTxt", request);
			}else {
				txtSearch = "";
			}
			strDDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
			gLogger.info("logged in ddo code for file generate employee List: " + strDDOCode);

			lstEmpForFileGenerate = lObjFormS1UpdateDAO.getEmpListForFileGenerate(strDDOCode, txtSearch,flag);
			gLogger.info("lstEmpForFileGenerate is ***" + lstEmpForFileGenerate.size());
			/*
			 * empDesigList=lObjFormS1UpdateDAO.getEmpDesigList(strDDOCode);
			 * gLogger.info("empDesigList is ***"+empDesigList.size());
			 */
			inputMap.put("empList", lstEmpForFileGenerate);

			inputMap.put("DDOCode", strDDOCode);
			// inputMap.put("empDesigList", empDesigList);
			resObj.setResultValue(inputMap);
			resObj.setViewName("empListForFileGenerate"); 
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject generateTexFile(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String ddoCode = null;
		String ddOName = null;
		String OffName = null;
		String empSevarthIdStr = null;
		String empSevarthIds = null;
		List empListFileGenerate = null;
		String flag = null;
		String ackNo = null;
		String dto_reg_no = null;
		String gStrUserId = null;
		String BatchId= null;
		List BatchIdList = null;
		Integer records=0;
		try {

			setSessionInfo(inputMap);

			empSevarthIdStr = StringUtility.getParameter("empSevarthId", request);
			empSevarthIdStr = StringUtility.getParameter("empSevarthId", request);
			empSevarthIds = StringUtils.join(empSevarthIdStr.split("_"), "','");
			records = Integer.parseInt(StringUtility.getParameter("records", request));
			
			System.out.println(empSevarthIds);
			// StringUtils.join(empSevarthIds, "','");
			FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			/*
			 * StringUtils
			 */
			ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
			gStrUserId = this.gStrUserId;
			gLogger.info("logged in ddo code for file generate process: " + ddoCode);

			empListFileGenerate = lObjFormS1UpdateDAO.getEmpForFileGenerateText(empSevarthIds, flag);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			String ymd =new SimpleDateFormat("yyyyMMdd").format(date); 
			 
			//BatchIdList=lObjFormS1UpdateDAO.getBachId(ymd); 20220419
			 
			String Year=new SimpleDateFormat("yyyy").format(date); 
			String Month=new SimpleDateFormat("MM").format(date); 
			String Day=new SimpleDateFormat("dd").format(date); 
			
			
			List checkBatchIdList=lObjFormS1UpdateDAO.checkBachId(ymd);
			String BatchIdCount = checkBatchIdList.get(0).toString();
			gLogger.info("checkBatchIdList is:"+checkBatchIdList.size()+"count "+BatchIdCount+" YMD"+ymd);
				if(BatchIdCount.equals("1")) {
					lObjFormS1UpdateDAO.updateBachSeq(ymd);
					BatchIdList=lObjFormS1UpdateDAO.getBachId(ymd);
				} else
				{
					gLogger.info("lstEmpForFileGenerate is:"+checkBatchIdList.size());
					
					System.out.println(Year+","+Month+","+Day);
			
					lObjFormS1UpdateDAO.insertBachSeq(Year,Month,Day);
					gLogger.info("lstEmpForFileGenerate is:"+checkBatchIdList.size());
					BatchIdList=lObjFormS1UpdateDAO.getBachId(ymd);
					gLogger.info("lstEmpForFileGenerate is:"+BatchIdList.size());
				}
			
			if(BatchIdList.size()==1) {
				BatchId = (String) BatchIdList.get(0);
			 	
			
			gLogger.info("lstEmpForFileGenerate is ***" + empListFileGenerate.size());

			//ResourceBundle labels = ResourceBundle.getBundle("LabelsBundle", currentLocale);
			gLogger.info("OS type is ***" + System.getProperty("os.name").toLowerCase().split(" ")[0]);
			String Path =  null;
			if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
				Path=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
			}else 
			{
				Path=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
			}
			
			String directoryName = Path.concat(ddoCode);
			String fileName = "subscriberRegNsdl.txt";

			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}

			directoryName = directoryName.concat("/" + BatchId); // directoryName.concat("/"+lLngEmpId.toString());
			directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
			}
			File statText = new File(directoryName + "/" + fileName);

			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			int regSeq =0;
			Writer w = new BufferedWriter(osw);

			Integer empListSize = empListFileGenerate.size();
			
			if (records != 0) {
				if (empListSize > records) {
					empListSize = records;
				}

			}
			//Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			//System.out.println(strDate);
			//daily sequence update need
		//	Integer trnRegSeq = 0;
			/******* FOR daily File Sequence number creation *********/
			List npsFileSeqList=lObjFormS1UpdateDAO.checkFileSeqId(ymd);
			String npsFileSeqCount = checkBatchIdList.get(0).toString();
			if(npsFileSeqCount.equals("1")) {
				lObjFormS1UpdateDAO.updateFileSeq(ymd);
				npsFileSeqList=lObjFormS1UpdateDAO.getFileSeqId(ymd);
				gLogger.info("updated File Sequence generation is:"+npsFileSeqList.size());
			} else
			{
				gLogger.info("File Sequence generation is:"+npsFileSeqList.size());
				lObjFormS1UpdateDAO.insertFileSeq(Year,Month,Day);
				npsFileSeqList=lObjFormS1UpdateDAO.getFileSeqId(ymd);
				gLogger.info("new File Sequence is:"+npsFileSeqList.size());
			} 
			
			if(npsFileSeqList.size()==1) {
				regSeq = (int) npsFileSeqList.get(0);
			}
			/******* FOR daily File Sequence number creation  *********/
			
			/*if (trnRegSeq != null) {
				regSeq = trnRegSeq + 1L;
			}*/

			w.write("000001^FH^PRAN^R^" + new SimpleDateFormat("MMddyyyy").format(new Date()) + "^"
					+ String.format("%03d", regSeq) + "^" + String.format("%06d", empListSize) + "^NCRA");
			int count = 1;
			gLogger.info("empListFileGenerate is ***" + empListFileGenerate.size());
			gLogger.info("records is ***" + records);
			int empCount = 0;
			String errorStr="";
			for (int listCnt = 0; listCnt < empListFileGenerate.size(); listCnt++) {
				gLogger.info("listCnt is ***" + listCnt);
				empCount = listCnt + 1;
				Object[] obj = (Object[]) empListFileGenerate.get(listCnt);
				String empCountStr = String.format("%06d", empCount);
				if (empListFileGenerate.size() > 0 && listCnt < records) {
					count ++;
					dto_reg_no = (String) obj[8]; // mstNpsEmp.getDtoRegNo();
					inputMap.put("SingleTran","Y");
						/******* FOR Aknowledgemtnt number creation *********/
						List checkAckList=lObjFormS1UpdateDAO.getAkNoId(dto_reg_no);
						//String BatchIdCount = checkBatchIdList.get(0).toString();
						if(checkAckList.size()==1) {
							lObjFormS1UpdateDAO.updateAckNoSeq(dto_reg_no);
							checkAckList=lObjFormS1UpdateDAO.getAkNoId(dto_reg_no);
							gLogger.info("updated Acknowledgement generation is:"+checkAckList.size());
						} else
						{
							gLogger.info("Acknowledgement generation is:"+checkAckList.size());
							lObjFormS1UpdateDAO.insertAckNoSeq(dto_reg_no);
							checkAckList=lObjFormS1UpdateDAO.getAkNoId(dto_reg_no);
							gLogger.info("new Acknowledgement is:"+checkAckList.size());
						}
						
						/******* FOR Aknowledgemtnt number creation *********/
					//String ackNoSufix = (Object[]) checkAckList.get(0).toString();
					//Object[] obj1 =  checkAckList.get(0);
				
					int ackNoSufix= (int) checkAckList.get(0);
					//IFMSCommonServiceImpl.getNextSeqNum("NPS_ACKNO_SEQ", inputMap);
					gLogger.info("new ackNoSufix value is:"+ackNoSufix);
					ackNo = dto_reg_no + "100" + String.format("%07d", ackNoSufix);
					gLogger.info("Acknowledgement is ***" + ackNo);
				
					/*
					 * for (Entity empDetail : empListFileGenerate) { empDetail }
					 */
					System.out.println(obj[34] + " NA " + obj[35]);
					w.write("\r\n" + String.format("%06d", count) + "^" + "FD" // mstNpsEmp.getFileSecType() //FD/ND/DD
							+ "^" + "N" + "^" + empCountStr + "^^" + ackNo // mstNpsEmp.getAckNo()
							+ "^" + obj[1] // MS//
							+ "^" + obj[2] // mstNpsEmp.getSubcName()
							+ "^" + obj[4] // mstNpsEmp.getSubcName()
							+ "^" + obj[3] + "^" + obj[6] + " " + obj[7] + "^^^" + obj[8] + "^" + obj[61] + "^"
							+ obj[10] + "^"
							+ new SimpleDateFormat("MMddyyyy")
									.format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[11]))
							+ "^" + obj[12] + "^" + obj[13] // getCoAdd1()
							+ "^" + obj[14] // getCoAdd2()
							+ "^" + obj[15] // + mstNpsEmp.getLocality() //getCoAdd3()
							+ "^" + obj[17] // getCoCity()
							+ "^"
							// + mstNpsEmp.getFlatUnitNo() // getCoRoad
							+ "^" + obj[16] // getCoLandmark
							+ "^" + "19"// empListFileGenerate.get(16) //getCoState
							+ "^" + "IN" // empListFileGenerate.get(17) //getCoCountry
							+ "^" + obj[20] // getCoPin
							+ "^" + obj[21] // getPerAdd1()
							+ "^" + obj[22] // getPerAdd2()
							+ "^" + obj[23] // + mstNpsEmp.getEmpPermanentBuildingName() //getPerAdd3()
							+ "^" + obj[25] // getPerCity
							+ "^"
							// + mstNpsEmp.getEmpPermanentFlatUnitNo() //getPerRoad
							+ "^" + obj[24] // getPerLandmark
							+ "^" + "19" // empListFileGenerate.get(26) //getPerState
							+ "^" + "IN"// empListFileGenerate.get(27) //getPerCountry
							+ "^" + obj[28] // getPerPin
							+ "^"
							// +telNo empListFileGenerate.get(29)
							+ "^" + obj[30] + "^^" + obj[31] // EmailID
							+ "^" + obj[32] // "Y" //mstNpsEmp.getSmsSFlag()
							+ "^" + obj[33] // "Y" //mstNpsEmp.getEmailSFlag()
							+ "^" + new SimpleDateFormat("MMddyyyy")
									.format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[34]))
							// new SimpleDateFormat("ddMMyyyy") .format(new
							// SimpleDateFormat("dd/MM/yyyy").parse((String) obj[11]))
							+ "^"
							+ new SimpleDateFormat("MMddyyyy")
									.format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[35]))
							+ "^" + obj[36] + "^" + obj[37] + "^" + obj[38] // mstNpsEmp.getMinistry()
							+ "^" + obj[38] // mstNpsEmp.getDdoOffName()
							+ "^" + obj[39] // mstNpsEmp.getPayScaleDesc()
							+ "^" + obj[56] // basicPay
							+ "^" + obj[41] // mstNpsEmp.getEmployeeId() or ppan
							+ "^" + "N" // mstNpsEmp.getInBankFlag()
							+ "^" + "Savings" // mstNpsEmp.getBankType()
							+ "^" + obj[42] // mstNpsEmp.getEmployeeBankAccountNo()
							+ "^" + obj[43] // mstNpsEmp.getEmployeeBankName()
							+ "^" + obj[44] // mstNpsEmp.getEmployeeBankBranchName()
							+ "^" + ((String) obj[45]).replaceAll("[^a-zA-Z0-9]", " ")// mstNpsEmp.getEmployeeBankBankAddress().replaceAll("[^a-zA-Z0-9]",
																						// " ")
							+ "^" + "19" // mstNpsEmp.getBankState()
							+ "^" + "IN" // mstNpsEmp.getBankCountry()
							+ "^" + obj[46] // mstNpsEmp.getEmployeeBankPinCode()
							+ "^^" + obj[47] // mstNpsEmp.getNumNominess()
							+ "^" + "000" // mstNpsEmp.getNumSchemes()
							+ "^^^^^^" + obj[58] // mstNpsEmp.getEmployeeMotherName()
							+ "^^^" + "N" // mstNpsEmp.getHindiFlag()
							+ "^^^^^^^" + obj[59] // mstNpsEmp.getIFSCCode()
							+ "^" + "N" // mstNpsEmp.getComFromFlag()
							+ "^" + "^^^^^^^^" // mstNpsEmp.getAadhar()
							+ "O" // mstNpsEmp.getSecTypeFlag()
							+ "^" + "S" // mstNpsEmp.getFundRatio() getting error Scheme TYPE
							+ "^^^" + "N"// mstNpsEmp.getPpanNo()
							+ "^" + "4" // mstNpsEmp.getMinUploadIndicator()
							+ "^" + "F" // mstNpsEmp.getDisNameFlag()
							+ "^" + "00" // mstNpsEmp.getSotLanguageCode()
							+ "^^^" + "Y" // +mstNpsEmp.getDobProDoc()
							+ "^^^^" + "119" + "^^^^^^^^^^^^^" + "N" + "^" + "RI" 
							+ "^^" 
							+ obj[60] // mstNpsEmp.getEmployeeMaritalStatus()
							+ "^^" + "Y" // mstNpsEmp.getAntiLaun() + "^"
							+ "^" + "N" // + mstNpsEmp.getDobProof()
							+ "^^^" + "6" // mstNpsEmp.getEmployeeBirthPlace()
							+ "^" + "IN" + "^" // mstNpsEmp .getIsoCountry()
							+ "R" + "^" // mstNpsEmp.getPerAddreddType() + "^"
							+ "R" + "^^" // + telNo + "^" // mstNpsEmp.getCorAddressType() + "^" + telNo + "^"
							+ "Y" // mstNpsEmp.getCancelledCheque() + "^"
							+ "^" + "RI" // mstNpsEmp.getNationality() + "^"
							+ "^" + "1" + "^" // mstNpsEmp.getFatcaDecCount() + "^"
							+ "Y" + "^" // mstNpsEmp.getFatcaDec() + "^N^"
							+ "N" + "^" + "N" + "\r\n"); // mstNpsEmp.getNodalConsent()

					int nomineeCount = Integer.parseInt(obj[47].toString());

					System.out.println("NO of nominee: " + nomineeCount);
					
					 for(int i=1; i<= nomineeCount;i++) {
						 count ++;
								  System.out.println("NO of nominee: counter" );
								  if(i == 1) {
								  w.write(String.format("%06d", count) + "^ND^N^01^01^" + obj[48] // mstNpsEmp.getEmpNominee1Name()
																									// +
									+ "^^^"
									+ new SimpleDateFormat("MMddyyyy")
											.format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[49])) // nom1Date +
									+ "^" + obj[50] // mstNpsEmp.getEmpNominee1relationship()
									+ "^" + obj[57] // mstNpsEmp.getNominee1MajorMinor() + "^"
									+ "^" + obj[51] // gardian
									+ "^^^" + obj[52] // mstNpsEmp.getEmpNominee1Share()
									+ "^^" + ""// mstNpsEmp.getAddressOfTax()
									+ obj[79]
									+ "^^^^^^\r\n");
								  }
							if(i==2) {
									w.write(String.format("%06d", count) + "^ND^N^"+String.format("%02d", i)+"^"+String.format("%02d", i)+"^" + obj[65] // mstNpsEmp.getEmpNominee1Name()
																// +
									+ "^^^"
									+ new SimpleDateFormat("MMddyyyy")
									.format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[66])) // nom1Date +
									+ "^" + obj[67] // mstNpsEmp.getEmpNominee1relationship()
									+ "^" + obj[68] // mstNpsEmp.getNominee1MajorMinor() + "^"
									+ "^" + obj[69] // gardian
									+ "^^^" + obj[70] // mstNpsEmp.getEmpNominee1Share()
									+ "^^" + ""// mstNpsEmp.getAddressOfTax()
									+ obj[80]
									+ "^^^^^^\r\n");
							}
							if( i==3 ) {
								w.write(String.format("%06d", count) + "^ND^N^"+String.format("%02d", i)+"^"+String.format("%02d", i)+"^" + obj[72] // mstNpsEmp.getEmpNominee1Name()
										// +
								+ "^^^"
								+ new SimpleDateFormat("MMddyyyy")
								.format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[73])) // nom1Date +
								+ "^" + obj[74] // mstNpsEmp.getEmpNominee1relationship()
								+ "^" + obj[75] // mstNpsEmp.getNominee1MajorMinor() + "^"
								+ "^" + obj[76] // gardian
								+ "^^^" + obj[77] // mstNpsEmp.getEmpNominee1Share()
								+ "^^" + ""// mstNpsEmp.getAddressOfTax()
								+ obj[81]
								+ "^^^^^^\r\n");
						}
					
					 }
					count ++;
					w.write(String.format("%06d", count)+"^DD^N^01^IN^1^" + obj[12] + "^IN^^19^" + obj[17] + "^" + obj[20] + "\r\n");
					
					String directoryImagePhotoPath = directoryName.concat("/subscriberRegNsdl_photo" );
					File  directoryImagePhoto = new File(directoryImagePhotoPath);
					if (!directoryImagePhoto.exists()) {
						directoryImagePhoto.mkdir();
					}
					/*
					 * Path FROM = Paths.get(Your Source file complete path);
					Path TO = Paths.get(Destination complete path);
					CopyOption[] options = new CopyOption[]{
					  StandardCopyOption.REPLACE_EXISTING,
					  StandardCopyOption.COPY_ATTRIBUTES
					}; 
					java.nio.file.Files.copy(FROM, TO, options);
					 * */
					//File filePhoto = new File(obj[64]+"/"+obj[62]);
					String imagePath =  null;
					if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
						imagePath=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_PHOTO");
					}else 
					{
						imagePath=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_PHOTO_SERVER");
					}
					
					File filePhoto = new File(imagePath+"/"+obj[62]);
					File destFilePhoto =new File(directoryImagePhotoPath+"/"+ackNo+"_photo.jpg");
					gLogger.info("photo File images:" +imagePath+"/"+obj[62]); 
					CopyOption[] options = new CopyOption[]{
							  StandardCopyOption.REPLACE_EXISTING,
							  StandardCopyOption.COPY_ATTRIBUTES
							}; 
					
					if(filePhoto.exists()) {
						//Watchable copyStatus = java.nio.file.Files.copy(Paths.get(obj[64]+"/"+obj[62]), Paths.get(directoryImagePhotoPath+"/"+ackNo+"_photo.jpg"), options);
						FileUtils.copyFile(filePhoto,destFilePhoto);
						if(destFilePhoto.exists())
						//if(file.renameTo(new File(directoryImagePhotoPath+"/"+ackNo+"_photo.jpg")))
				        {
							gLogger.info("photo File  copied successfully:" +ackNo+"_photo.jpg"); 
							
						}
				        else
				        {
				        	gLogger.info("photo File already exists :" +obj[63]+"/"+obj[62]);
				        	errorStr +="photo images not copied";
				        }
					}else { 
						gLogger.info("Photo image not found:" +imagePath+"/"+obj[62]);
						errorStr +="photo images not exist at source ";
					}
					
					String directoryImageSignPath = directoryName.concat("/subscriberRegNsdl_sig" );
					File  directoryImageSign = new File(directoryImageSignPath);
					if (!directoryImageSign.exists()) {
						directoryImageSign.mkdir();
					}
					//File srcFileSign = new File(obj[64]+"/"+obj[63]);
					File srcFileSign = new File(imagePath+"/"+obj[63]);
					File destFileSign = new File(directoryImageSignPath+"/"+ackNo+"_sig.jpg");
					gLogger.info("Signature File images:" +imagePath+"/"+obj[63]);
					if(srcFileSign.exists()) {
					
						FileUtils.copyFile(srcFileSign,destFileSign);
						if(destFileSign.exists())
						//if(file.renameTo(new File(directoryImageSignPath+"/"+ackNo+"_sig.jpg")))
					        {
								gLogger.info("Signature File not copied successfully:" +ackNo+"_sig.jpg"); 
							}
					        else
					        {
					        	gLogger.info("Signature File already exists:" +obj[63]+"/"+obj[62]);
					        	errorStr +="Signatur images not copied";
					        }
					}else {
						gLogger.info("signature image not found:" +imagePath+"/"+obj[62]);
						errorStr +="Signature images not exist at source";
					}	
					gLogger.info("empListFileGenerate count:" +empListFileGenerate.get(listCnt)); 
					gLogger.info("date of birth:    :" +new SimpleDateFormat("ddMMyyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse((String) obj[11]))); 
					 if(errorStr.equals("")) {
						 lObjFormS1UpdateDAO.updateFrmFormS1DTLS1((String) obj[0],ackNo,BatchId);
					 }
				}

			}
			w.close();
		
				OutputStream outStream = null;
				
				String SOURCE_FOLDER=null;
				if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
				//if(System.getProperty("os.name")=="win") {
					 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") + ddoCode + "/" + BatchId;
				}else 
				{
					 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") + ddoCode + "/" + BatchId;
				}
				
				  //SOURCE_FOLDER = OUTPUT_FILE + ddoCode + "/" + BatchId;
					try {
				
						File processFile = new File(SOURCE_FOLDER+"/"+fileName);
						String validSatatus=null;
						String successMsg=null;
						String lSBStatus = null;
						if(processFile.exists() && !(errorStr!=null && !errorStr.isEmpty())) {
							//lObjFormS1UpdateDAO.updateFrmFormS1DTLS1(empSevarthIds,ackNo,BatchId);
							validSatatus="correct";
							successMsg="Text file generated successfully ";
							 lSBStatus = getResponseXMLDocMsg(validSatatus,successMsg).toString();
						}else
						{
							validSatatus="wrong";
							 lSBStatus = getResponseXMLDocMsg(validSatatus,errorStr).toString();
						}	
						String lStrResult = null;
						lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
						HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
						inputMap.put("ajaxKey", lStrResult);
						
						resObj.setResultValue(inputMap);
						resObj.setViewName("ajaxData");
						gLogger.info("inside generateTextFile"+SOURCE_FOLDER+"/"+fileName);
				} finally {
					
					if (outStream != null)
						outStream.close();
				}
			}

		} catch (Exception ioe) {
			
			System.out.println("Error while Creating File in Java" + ioe);
		}
		 
		return resObj;

	}
	
public ResultObject validateTextFile(Map inputMap) throws Exception{
		gLogger.info("inside validateTextFile");
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		
		
		String fileName=null;
		String empSevarthIdStr = null;
		String lStrResult = null;
	 	String ddoCode=null;
	 	String lSBStatus=null;
	 	String validSatatus=null;
	 	String gStrUserId=null;
	 	List AckNoBatchList=null;
	 	String BatchId=null;
	 	String ACKNO=null;
	 	String flag=null;
	 	String msgStr=null;
	 	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String ymd =new SimpleDateFormat("yyyyMMdd").format(date); 
		FileInputStream inputStream=null;
		ServletOutputStream outStream=null;
		
		try {
			 	setSessionInfo(inputMap);
			 	
			 	empSevarthIdStr = StringUtility.getParameter("empSevarthId", request);
			 	BatchId = StringUtility.getParameter("batchNo", request);
			 	
				String empSevarthIds = StringUtils.join(empSevarthIdStr.split("_"), "','");
				FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
				AckNoBatchList = lObjFormS1UpdateDAO.getAckNoBatchForValidateText(BatchId, flag);
			 	
				if(AckNoBatchList.size()>0) {
					
					Object[] AckNoBatchObj = (Object[]) AckNoBatchList.get(0);
					ACKNO= (String) AckNoBatchObj[0];
					BatchId = (String) AckNoBatchObj[1];
					DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
					ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
					//gStrUserId = this.gStrUserId;
					/*empSevarthIdStr = StringUtility.getParameter("empSevarthId", request);
					String empSevarthIds = StringUtils.join(empSevarthIdStr.split("_"), "','");*/
					String SOURCE_FOLDER =null;
					String OUTPUT_FILE=null;
					if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
				//	if(System.getProperty("os.name")=="win") {
						 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") + ddoCode + "/" + BatchId;
						 OUTPUT_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
					}else 
					{
						 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") + ddoCode + "/" + BatchId;
						 OUTPUT_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
					}
					//String SOURCE_FOLDER = OUTPUT_FILE + ddoCode + "/" + BatchId;
					String[] inputParamaeters = { OUTPUT_FILE + ddoCode + "/" + BatchId };
					fileName = "subscriberRegNsdl.txt";
					try {
						setSessionInfo(inputMap);
						RunSubsRegFvu craFVUpaosubcontr = new RunSubsRegFvu();
						craFVUpaosubcontr.main(inputParamaeters); 
						 File processFile = new File(SOURCE_FOLDER+"/Processed/"+fileName);
						
						gLogger.info("inside validateTextFile"+SOURCE_FOLDER+"/Processed/"+fileName);
						String fileVerifySatatus=null;
						 if(processFile.exists()) {
								validSatatus="correct";
								fileVerifySatatus="Y";
								lObjFormS1UpdateDAO.updateNsdlFileVeryStatus(BatchId,fileVerifySatatus);
								msgStr=" File validated successfully.";	
							}else
							{
								 msgStr="File not validated.So, Kindly check error. click on donwload link and check.";
								 fileVerifySatatus="N";
								 lObjFormS1UpdateDAO.updateNsdlFileVeryStatus(BatchId,fileVerifySatatus);
								validSatatus="wrong";
								
							}
						if(!fileVerifySatatus.equals("")) {
								lSBStatus = getResponseXMLDocMsg(validSatatus,msgStr).toString();
								lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
								inputMap.put("ajaxKey", lStrResult);
								resObj.setResultValue(inputMap);
								resObj.setViewName("ajaxData");
						}
				 
						} catch(Exception e) {
							validSatatus="wrong";
							msgStr="File Validation exception occurs .";
							lSBStatus = getResponseXMLDocMsg(validSatatus ,msgStr).toString();
							lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
							inputMap.put("ajaxKey", lStrResult);
							resObj.setResultValue(inputMap);
							resObj.setViewName("ajaxData");
							gLogger.info("File Validation exception occur .");
						} finally {
							gLogger.info("inner finally block validateTextFile");
						
						} 
				} else 
				{
					msgStr="  Data not found !!!";
					validSatatus="wrong";
					lSBStatus = getResponseXMLDocMsg(validSatatus ,msgStr).toString();
					lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
					inputMap.put("ajaxKey", lStrResult);
					resObj.setResultValue(inputMap);
					resObj.setViewName("ajaxData");
				}
		} finally {
			
			gLogger.info("Outer finally block validateTextFile");
		}
		
		return resObj; 
	}
public ResultObject sendTextFile(Map inputMap) throws Exception {
	gLogger.info("inside validateTextFile");
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
	String empSevarthIdStr=null;
	String empSevarthIs=null;
	String ddoCode=null;
	String batchId=null;
	String dtoUserId=null;
	String empDdo=null;
	String dtoRegNo=null;
	String ACkno=null;
	String nsdlStatusCode=null;
	String validSatatus=null;
	String lSBStatus=null;
	String lStrResult=null;
	String msgStr=null;
	String BatchId=null;
	List DtoCodeList=null;
	List dataList=null;
	String sudoUser=null;
	try {
		//String nsdlStatusCode=nSDLIntegration.sendFile(response,  ddoCode, batchId, dtoUserId, dtoRegNo);
		setSessionInfo(inputMap);
		DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
		ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
		empSevarthIdStr = StringUtility.getParameter("empSevarthId", request);
		String empSevarthIds = StringUtils.join(empSevarthIdStr.split("_"), "','");
		FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
		BatchId = StringUtility.getParameter("batchNo", request);
		/*BatchId = StringUtility.getParameter("batchNo", request);
			 	
				String empSevarthIds = StringUtils.join(empSevarthIdStr.split("_"), "','");
				FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
				AckNoBatchList = lObjFormS1UpdateDAO.getAckNoBatchForValidateText(BatchId, flag);
			 	*/
		/*dataList = lObjFormS1UpdateDAO.getDataFromFrmS1(BatchId, "Batch_ID,DDO_REG_NO,emp.DDO_code,ACkno");*/
		
		dataList = lObjFormS1UpdateDAO.getDataFromFrmS1(BatchId, "Batch_ID,DTO_CODE,emp.DDO_code,ACkno");
		HttpServletResponse response=null;
		
		NSDLIntegration nsdlIntegration=new NSDLIntegration();
		gLogger.info("inside validateTextFile lObjFormS1UpdateDAO-----"+dataList.size());	
		 	if(dataList.size() > 0L) {
		 		    Object[] dataObj = (Object[]) dataList.get(0);
		 			batchId=dataObj[0].toString();
		 			dtoUserId=dataObj[1].toString();
		 			empDdo=dataObj[2].toString();
		 			ACkno=dataObj[3].toString();
		 			DtoCodeList = lObjFormS1UpdateDAO.getDtoDetails(ddoCode, empDdo);
		 			gLogger.info("outside if conditions DtoCodeList "+DtoCodeList.size());
			 			if(DtoCodeList.size() == 1){
			 					try {
					 				 Object[] dtoRegNo1= (Object[]) DtoCodeList.get(0);
					 				gLogger.info("inside if conditions sendTextFile"+dtoRegNo1[0]);
					 				gLogger.info("value for send "+ddoCode+","+dataObj[0].toString()+","+dtoRegNo1[1].toString()+","+dtoRegNo1[0]);
					 				//String nsdlStatusCode=nSDLIntegration.sendFile(response, ddoCode, batchId, dtoUserId, dtoRegNo)
						 			//DtoCodeList = lObjFormS1UpdateDAO.getDtoDetails(strDDOCode, EmpDDOCode);
					 				
						 			//nsdlStatusCode=nsdlIntegration.sendFile(response,ddoCode,dataObj[0].toString(),dataObj[1].toString(),dtoRegNo1[0].toString());
					 				//sudoUser=gObjRsrcBndle.getString("NPS."+dtoUserId);
					 				if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
					 					gLogger.info("Environment Name :"+System.getProperty("os.name").toLowerCase());
					 					nsdlStatusCode=nsdlIntegration.sendFile(response,ddoCode,dataObj[0].toString(),"1014005131",dtoRegNo1[0].toString());
										}else 
										{
											gLogger.info("Environment Name :"+System.getProperty("os.name").toLowerCase());
											nsdlStatusCode=nsdlIntegration.sendFile(response,ddoCode,dataObj[0].toString(),dtoRegNo1[0].toString(),dtoRegNo1[1].toString());
										}
					 				 //nsdlStatusCode=nsdlIntegration.sendFile(response,ddoCode,dataObj[0].toString(),sudoUser,dtoRegNo1[0].toString());
						 			if(nsdlStatusCode!=null && !nsdlStatusCode.isEmpty() ) {
						 				validSatatus="correct";
						 				lObjFormS1UpdateDAO.updateNsdlStatusCode(batchId,nsdlStatusCode);
						 				msgStr="  File uploaded  successfully !!!";
									}else {
										validSatatus="wrong";
										msgStr="  Something went wrong Please try again !!!";
									}
				 				} catch(Exception e)
				 				{
				 					gLogger.info("Send file to NSDL exception occurr here: \n" +e.getMessage().toString());
				 					e.printStackTrace();
				 					validSatatus="wrong";
				 					msgStr="Webservice calling exception occur.";
				 				}
			 			}else 
			 			{
			 				validSatatus="wrong";
			 				msgStr=" File send Successfully to nsdl having status code "+nsdlStatusCode;
			 			}
		 	
		 		
			 	}else {
				 		validSatatus="wrong";
				 		msgStr="  NO dto attached with these employee ";
			 		}
		 	//System.out.println("send text file successfully");	 	
	
			} catch (Exception e) {
				
				gLogger.error("Exception occur at call webserice "+e.getMessage().toString());
				
			} finally {
				gLogger.info("Outer finally block sendTextFile");
				
			}
	 
			lSBStatus = getResponseXMLDocMsg(validSatatus,msgStr).toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			return resObj;
	
}



public ResultObject getPran(Map inputMap) throws Exception {
 
	//throws ServletException, KeyManagementException, NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException 
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
	//String nsdlStatus=null;
	String ddoCode=null;
	String batchId=null;
	String dtoUserId=null;
	String empDdo=null;
	//String dtoRegNo=null;
	//String ACkno=null;
	String nsdlStatusCode=null;
	String validSatatus=null;
	String lSBStatus=null;
	String lStrResult=null;
	String msgStr=null;
	List DtoCodeList=null;
	List dataList=null;
			try {
					//nsdlIntegration.sendFile(response,ddoCode,dataObj[0].toString(),dataObj[1].toString(),dtoRegNo1[0].toString());
					setSessionInfo(inputMap);
					DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
					ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
					nsdlStatusCode = StringUtility.getParameter("StatusCode", request);
					
					String nsdlStatuscode=null;
					FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
					//dataList = lObjFormS1UpdateDAO.getDataForGetPran(nsdlStatusCode, "DDO_REG_NO,emp.DDO_code,NSDL_STATUS,NPS.BATCH_ID");
					dataList = lObjFormS1UpdateDAO.getDataForGetPran(nsdlStatusCode, "DTO_CODE,emp.DDO_code,NSDL_STATUS,NPS.BATCH_ID");
					HttpServletResponse response=null;
					
					NSDLIntegration nsdlIntegration=new NSDLIntegration();
					gLogger.info("inside getPran method  -----"+dataList.size());	
					if(dataList.size() > 0L) {
			 		    Object[] dataObj = (Object[]) dataList.get(0);
			 			batchId=dataObj[3].toString();
			 			dtoUserId=dataObj[0].toString();
			 			empDdo=dataObj[1].toString();
			 			//ACkno=dataObj[2].toString();
			 			nsdlStatuscode=dataObj[2].toString();
			 			gLogger.info("infor data ddoCode, empDdo"+ddoCode+","+ empDdo);
			 			DtoCodeList = lObjFormS1UpdateDAO.getDtoDetails(ddoCode, empDdo);
			 			gLogger.info("outside if conditions DtoCodeList "+DtoCodeList.size());
			 			if(DtoCodeList.size() == 1){
				 			 Object[] dtoRegNo1= (Object[]) DtoCodeList.get(0);
			 				gLogger.info("inside if conditions getPran"+dtoRegNo1[0]);
			 				gLogger.info("value for getPran to send "+ddoCode+","+dataObj[0].toString()+","+dataObj[1].toString()+","+dtoRegNo1[0]);
			 				
			 				//String sudoUser=gObjRsrcBndle.getString("NPS."+dtoUserId);	
			 				//gLogger.info("value for getPran to send "+ddoCode+","+nsdlStatuscode+",112448630"+","+sudoUser); 
			 				String transactionno=null;
			 					if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
			 						//transactionno=nsdlIntegration.getStatus(response, ddoCode, nsdlStatuscode, "1014005131",inputMap,batchId);
			 						transactionno=nsdlIntegration.getStatus(response, ddoCode, nsdlStatuscode, dtoRegNo1[0].toString(),inputMap,batchId);
								}else 
								{
									transactionno=nsdlIntegration.getStatus(response, ddoCode, nsdlStatuscode, dtoRegNo1[0].toString(),inputMap,batchId);
									//transactionno=nsdlIntegration.getStatus(response, ddoCode, nsdlStatuscode, "1014005131",inputMap,batchId);
								}
			 				 
			 			 
				 			if(transactionno!=null && !transactionno.isEmpty() ) {
				 				
				 				String splitChr="\\#";
				 				String[] transStatus = transactionno.split(splitChr); 
				 				if(transStatus.length==3 && transStatus[0].equals("Success")) {
		 							validSatatus="correct";
		 							msgStr= transStatus[1]+" Pran Number get  Successfully from nsdl having status code "+nsdlStatuscode+".\n"
		 							+"Pran number as below: \n"+transStatus[2];
		 						} else if(transStatus.length==2 && transStatus[0].equals("error"))
			 						{
				 						validSatatus="wrong";
				 						msgStr=" Status code of Nsdl as error code "+transStatus[1];
			 						}  
				 			 	
							 }else {
								
								msgStr="  Something went wrong.So, Please try again !!!";
							}
			 			}else {
			 				validSatatus="wrong";
							msgStr="  Something went wrong Please try again Dto code not found !!!";
			 			}
					}else {
				 		validSatatus="wrong";
				 		msgStr="  NO dto attached with these nsdl status code ";
			 		}
				
				 		System.out.println("send text file successfully");	 	
			
				} catch (Exception e) {
					System.out.println("Exception occur at call webserice at get pran "+e.getMessage().toString());
				
				} finally {
					gLogger.info("Outer finally block getPran");
					
				}
	 
			lSBStatus = getResponseXMLDocMsg(validSatatus,msgStr).toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");
			return resObj;
	
	
}
	public ResultObject downloadNpsFile(Map inputMap) throws Exception{
		
		
		gLogger.info("inside downloadNpsFile");
			
		
		List dataList=null;
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		
		
		String strSevarthId = null;
		Long finalCheckFlag = null;
		String lStrResult = null;
	 	String ddoCode=null;
	 	String nsdlStatus=null;
	 	String verifystatus=null;
		String ACkno=null;
		String pranStatus=null;
		FileInputStream inputStream=null;
		ServletOutputStream outStream=null;
		
		
		setSessionInfo(inputMap);
		//HttpServletRequest request = (HttpServletRequest) inputMap.get("inputMap");
		DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
		ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
		gStrUserId = this.gStrUserId;
		
		System.out.println("DDO CONDE:   "+ddoCode);
		//BATCH_ID=202200000115202210044 npsDownloadBatchId request
		String BatchId= StringUtility.getParameter("npsDownloadBatchId", request);
		System.out.println("DDO CONDE:   "+ddoCode + BatchId);
		FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
		dataList = lObjFormS1UpdateDAO.getDataFromFrmS1(BatchId, "Batch_ID,NPS.DDO_CODE,NPS.NSDL_STATUS,NPS.FILE_VERIFY_STATUS,COALESCE(NPS.PRAN_STATUS,0)");
		gLogger.info("logged in ddo code for file generate process: " + ddoCode);
		
		if(dataList.size() > 0L) {
			Object[] dataObj = (Object[]) dataList.get(0);
			BatchId=dataObj[0].toString();
			ddoCode=dataObj[1].toString();
			if(dataObj[2]!=null) {
				nsdlStatus=  dataObj[2].toString();
			}else {
				nsdlStatus=  null;
			}
			
			if(dataObj[3]!=null) {
				verifystatus=  dataObj[3].toString();
			}else {
				verifystatus=  null;
			}
			
			/*nsdlStatus= dataObj[2].toString();
			verifystatus=dataObj[3].toString();*/
			pranStatus=dataObj[4].toString();
			//gLogger.info("Download File Name: " + SOURCE_FOLDER + "/Error/subscriberRegNsdl_Resp.html");
				try {
						String successMsg =null;
						successMsg ="File donwloaded";
						//String OUTPUT_FILE =null;
						String SOURCE_FOLDER =null;
						if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
						//if(System.getProperty("os.name")=="win") {
							 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") + ddoCode + "/" + BatchId;
							 //OUTPUT_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
						}else 
						{
							 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") + ddoCode + "/" + BatchId;
							// OUTPUT_FILE = gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
						}
					 	//String SOURCE_FOLDER = OUTPUT_FILE + ddoCode + "/" + gStrUserId;
						File downloadFile;
						
						gLogger.info("Download File Name: " + SOURCE_FOLDER + "/Error/subscriberRegNsdl_Resp.html");
						downloadFile = new File(SOURCE_FOLDER + "/Error/subscriberRegNsdl_Resp.html");
						if (downloadFile.exists() && verifystatus.equals("N")) {
								// Used to name the download file and its format
								HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");
								response.setContentType("text/html");
								response.setHeader("Content-disposition", "attachment; filename=subscriberRegNsdl_Resp.html");
								inputMap.put("sucessMsg", successMsg);
								System.out.println(SOURCE_FOLDER +"/Error/subscriberRegNsdl_Resp.html");
								inputStream = new FileInputStream(downloadFile);
								outStream = response.getOutputStream();
								byte[] buffer = new byte[4096];
								int length;
								while ((length = inputStream.read(buffer)) > 0) {
									outStream.write(buffer, 0, length);
								}
								inputStream.close();
								outStream.close();
						}else if (verifystatus.equals("Y") && pranStatus.equals("1")) {
									downloadFile = new File(SOURCE_FOLDER + "/Processed/subscriberRegNsdl.txt");									
									if(downloadFile.exists()) {
										// Used to name the download file and its format
										HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");
										response.setContentType("text/html");
										response.setHeader("Content-disposition", "attachment; filename=subscriberRegNsdl.txt");
										inputMap.put("sucessMsg", successMsg);
										System.out.println(SOURCE_FOLDER + "/subscriberRegNsdl.txt");
										inputStream = new FileInputStream(downloadFile);
										outStream = response.getOutputStream();
										byte[] buffer = new byte[4096];
										int length;
										while ((length = inputStream.read(buffer)) > 0) {
											outStream.write(buffer, 0, length);
										}
										inputStream.close();
										outStream.close();
									}
								}else if (verifystatus.equals("Y") && nsdlStatus==null) {
										downloadFile = new File(SOURCE_FOLDER + "/Upload/subreg_after_fuv/SubReg_Verified_After_FUV.sub");
										if(downloadFile.exists()) {
										// Used to name the download file and its format
										HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");
										response.setContentType("text/sub");
										response.setHeader("Content-disposition", "attachment; filename=SubReg_Verified_After_FUV.sub");
										inputMap.put("sucessMsg", successMsg);
										System.out.println(SOURCE_FOLDER + "/Upload/subreg_after_fuv/SubReg_Verified_After_FUV.sub");
										inputStream = new FileInputStream(downloadFile);
										outStream = response.getOutputStream();
										byte[] buffer = new byte[4096];
										int length;
										while ((length = inputStream.read(buffer)) > 0) {
											outStream.write(buffer, 0, length);
										}
										inputStream.close();
										outStream.close();
									}else {
										downloadFile = new File(SOURCE_FOLDER + "/Error/subscriberRegNsdl_Resp.html");
										// Used to name the download file and its format
										HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");
										response.setContentType("text/sub");
										response.setHeader("Content-disposition", "attachment; filename=subscriberRegNsdl_Resp.html");
										inputMap.put("sucessMsg", successMsg);
										inputStream = new FileInputStream(downloadFile);
										outStream = response.getOutputStream();
										byte[] buffer = new byte[4096];
										int length;
										while ((length = inputStream.read(buffer)) > 0) {
											outStream.write(buffer, 0, length);
										}
										inputStream.close();
										outStream.close();
									}
								}else if(verifystatus.equals("Y") && pranStatus!="1" && nsdlStatus!=null) {
									
									downloadFile = new File(SOURCE_FOLDER + "/Response_getpran.html");
									if(downloadFile.exists()) {
									// Used to name the download file and its format
									HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");
									response.setContentType("text/sub");
									response.setHeader("Content-disposition", "attachment; filename=Response_getpran.html");
									inputMap.put("sucessMsg", successMsg);
									System.out.println(SOURCE_FOLDER + "/Response_getpran.html");
									inputStream = new FileInputStream(downloadFile);
									outStream = response.getOutputStream();
									byte[] buffer = new byte[4096];
									int length;
									while ((length = inputStream.read(buffer)) > 0) {
										outStream.write(buffer, 0, length);
									}
									inputStream.close();
									outStream.close();
								}
									
								
							
						}
						
			} finally {
			 
				if (inputStream != null)
					inputStream.close();
		
				if (outStream != null)
					outStream.close();
			}
		}
		
		
		return objRes;
		
		
		 
		
	}
 
	private StringBuilder getResponseXMLDoc(String status) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(status);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}
	
	private StringBuilder getResponseXMLDocMsg(String status,String msgStr) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(status);
			lStrBldXML.append("</Flag>");
			lStrBldXML.append("<Flag>");
			lStrBldXML.append(msgStr);
			lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");
		System.out.println("return"+lStrBldXML);
		return lStrBldXML;
	}

	public ResultObject deleteRecord(Map inputMap) throws Exception {
		gLogger.info("inside deleteRecord");
		 
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		
		String strSevarthId = null;
		Long finalCheckFlag = null;
		String lStrResult = null;
	 	String ddoCode=null;
	    String validSatatus=null;
	    String msgStr=null;
		String SevarthId=null;
		String lSBStatus=null;
		
		
		 
		try {
			setSessionInfo(inputMap);
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			ddoCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
		//	gStrUserId = this.gStrUserId;
			SevarthId = StringUtility.getParameter("empId", request);
			System.out.println("SevarthId:   "+SevarthId);
		 
			FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(null, serv.getSessionFactory());
			finalCheckFlag = lObjFormS1UpdateDAO.checkFormS1(SevarthId);
			
			gLogger.info("logged in ddo code for inside deleteRecord: " + ddoCode);
				if(finalCheckFlag>0 && finalCheckFlag==1 ) {
					lObjFormS1UpdateDAO.deleteUpdateCsrfEmpdata(SevarthId,ddoCode);
					if(lObjFormS1UpdateDAO.deletedUpdateCsrfEmpdatamove(SevarthId,ddoCode,3)>0)
					{
						lObjFormS1UpdateDAO.deletedCsrfEmpdatamove(SevarthId,ddoCode,3);
						validSatatus="correct";
						msgStr="Sevaarth " + SevarthId+" employee data delete from csrf form records!";
					}
					
					
					}else {
							validSatatus="wrong";
							msgStr="Please select only one records for delete!!!";
						}
				 
		}catch(Exception e){
			gLogger.error("Exception occur at call delete Records "+e.getMessage().toString());
		} finally {
			gLogger.info("Outer finally block delete Record");
			
		}
		
		lSBStatus = getResponseXMLDocMsg(validSatatus,msgStr).toString();
		lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();
		inputMap.put("ajaxKey", lStrResult);
		objRes.setResultValue(inputMap);
		objRes.setViewName("ajaxData");
		return objRes;
	} 
	
	public void downloadNpsFile1(Map inputMap) throws Exception {
	
		String ddoCode=null;
		String gStrUserId=null;
		FileInputStream inputStream = null;
		OutputStream outStream = null;
		try {
				String successMsg =null;
				successMsg ="File donwloaded";
				String SOURCE_FOLDER =null;
				if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
				//if(System.getProperty("os.name")=="win") {
					 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE") + ddoCode + "/" + gStrUserId;
				}else 
				{
					 SOURCE_FOLDER = gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER") + ddoCode + "/" + gStrUserId;
				}
				//String SOURCE_FOLDER = OUTPUT_FILE + ddoCode + "/" + gStrUserId;
				File downloadFile;
				
				HttpServletResponse response = (HttpServletResponse) inputMap.get("responseObj");
				response.setContentType("text/html");
				downloadFile = new File(SOURCE_FOLDER + "/Error/subscriberRegNsdl_Resp.html");
				// Used to name the download file and its format
				response.setHeader("Content-disposition", "attachment; filename=subscriberRegNsdl_Resp.html");
				inputMap.put("sucessMsg", successMsg);
		
				inputStream = new FileInputStream(downloadFile);
				outStream = response.getOutputStream();
				byte[] buffer = new byte[4096];
				int length;
				while ((length = inputStream.read(buffer)) > 0) {
					outStream.write(buffer, 0, length);
				}
				inputStream.close();
				outStream.close();
		} finally {
			if (inputStream != null)
				inputStream.close();

			if (outStream != null)
				outStream.close();
		}
	}
	
	
	
}
