package com.tcs.sgv.nps.service;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import com.ibm.db2.jcc.DB2Blob;
import com.lowagie.text.Image;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.axis.Part;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hhc.MultipartRequest;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMpgDAOImpl;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttdocMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttdocMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnAttdocMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.nps.dao.FormS1UpdateDAO;
import com.tcs.sgv.nps.dao.FormS1UpdateDAOImpl;
import com.tcs.sgv.dcps.dao.ChangesFormDAO;
import com.tcs.sgv.dcps.dao.ChangesFormDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.HstDcpsPersonalChanges;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.nps.valueobject.FrmFormS1Dtls;
import com.tcs.sgv.nps.valueobject.FrmFormS1NpsDtls;
import com.thoughtworks.xstream.io.path.Path;

import au.id.jericho.lib.html.Logger;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FormS1Update extends ServiceImpl {

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
	//IMAGE file creation file path
	
	private static String OUTPUT_FILE_PHOTO;
	private static String OUTPUT_FILE_PHOTO_SERVER;
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");
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
	// private ResourceBundle gObjRsrcBndle =
	// ResourceBundle.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

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

	public ResultObject getEmpListForFormS1Edit(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lstEmpForFrmS1Edit = null;
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

			strDDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
			gLogger.info("logged in ddo code: " + strDDOCode);
			txtSearch = StringUtility.getParameter("searchTxt", request);
			sevaarthId = StringUtility.getParameter("txtSevaarthId1", request);
			dcpsId = StringUtility.getParameter("dcpsId", request);
			gLogger.info("txtSearch is ***" + txtSearch);
			gLogger.info("sevaarthId is ***" + sevaarthId);
			flag = StringUtility.getParameter("flag", request);
			if (sevaarthId != null && !sevaarthId.equals("")) {
				gLogger.info("sevaarthId is ***" + sevaarthId);
				txtSearch = sevaarthId;
			}
			if (dcpsId != null && !dcpsId.equals("")) {
				txtSearch = dcpsId;
			}
			lstEmpForFrmS1Edit = lObjFormS1UpdateDAO.getEmpListForFrmS1Edit(strDDOCode, flag, txtSearch, "");
			gLogger.info("lstEmpForFrmS1Edit is ***" + lstEmpForFrmS1Edit.size());
			empDesigList = lObjFormS1UpdateDAO.getEmpDesigList(strDDOCode);
			gLogger.info("empDesigList is ***" + empDesigList.size());
			inputMap.put("empList", lstEmpForFrmS1Edit);

			inputMap.put("DDOCode", strDDOCode);
			inputMap.put("empDesigList", empDesigList);
			inputMap.put("msg", "");
			resObj.setResultValue(inputMap);
			resObj.setViewName("empListForFormS1Update");
		//	System.out.println("test"+org.hibernate.Version.getVersionString());

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	public ResultObject getFormS1EditForEmp(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String strEmpSevarthId = null;
		String strEmpName = null;
		String strDDOCode = null;
		String DOJ = null;
		String dsgnName = null;
		String DcpsId = null;
		String DdoRegNO = null;
		String DtoCode = null;
		String EmpDDOCode = null;
		String DcpsEmpId = null;
		MstEmp MstEmpObj = null;
		List lstRelationList = null;
		List empDetails = null;
		List classList = null;
		List empNomineeDetails = null;
		List RelationList = null;
		List StateList = null;
		List BankList = null;
		List payScaleList = null;
		List DtoCodeList = null;
		List titleList = null;
		Set<CmnAttachmentMpg> cmnAttachmentMpgs = null;
		Set<CmnAttdocMst> cmnAttdocMsts =null;
		Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = null;
		Iterator<CmnAttdocMst> cmnAttdocMstIterator = null;
		CmnAttachmentMpg cmnAttachmentMpg = null;
		CmnAttdocMst  cmnAttdocMst=null;
		Long lLngPhotoAttachmentId = null;
		Long lLngSignAttachmentId = null;
		byte[] arrayOfByteSign=null;
		byte[] arrayOfBytePhoto=null;
		try {
			
			setSessionInfo(inputMap);
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(MstEmp.class, serv.getSessionFactory());
			strDDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
			gLogger.info("logged in ddo code: " + strDDOCode);
			strEmpSevarthId = StringUtility.getParameter("empSevarthId", request);
			strEmpName = StringUtility.getParameter("empName", request);
			DOJ = StringUtility.getParameter("DOJ", request);
			dsgnName = StringUtility.getParameter("dsgnName", request);
			DcpsId = StringUtility.getParameter("DcpsId", request);
			EmpDDOCode = StringUtility.getParameter("DDOCode", request);
			DcpsEmpId = StringUtility.getParameter("DcpsEmpId", request);
			gLogger.info("EmpSevarthId is: " + strEmpSevarthId);
			gLogger.info("strEmpName is: " + strEmpName);
			gLogger.info("DOJ is: " + DOJ);
			gLogger.info("dsgnName is: " + dsgnName);
			gLogger.info("DcpsId is: " + DcpsId);
			gLogger.info("DcpsId is: " + DcpsEmpId);

			if (request != null) {

				if (!DcpsEmpId.equals("")) {
					Long lDcpsEmpId = Long.parseLong(DcpsEmpId);
					MstEmpObj = (MstEmp) lObjFormS1UpdateDAO.read(lDcpsEmpId);
				}
				empDetails = lObjFormS1UpdateDAO.getEmpForFrmS1Edit(strDDOCode, strEmpSevarthId);
				DtoCodeList = lObjFormS1UpdateDAO.getDtoDetailsCSRF(strDDOCode, EmpDDOCode);
				if (DtoCodeList.size() == 1) {
					for (int listCnt = 0; listCnt < DtoCodeList.size(); listCnt++) {
						Object[] DtoObj = (Object[]) DtoCodeList.get(0);
						DtoCode = (String) DtoObj[0];
						DdoRegNO = (String) DtoObj[1];

					}

				}
				gLogger.info("DdoRegNO is: " + DdoRegNO);
				gLogger.info("DtoCode is: " + DtoCode);

				empNomineeDetails = lObjFormS1UpdateDAO.getEmpNomineeList(strEmpSevarthId);
				classList = lObjFormS1UpdateDAO.getClassList();
				titleList = lObjFormS1UpdateDAO.getTitleList();
				lstRelationList = lObjFormS1UpdateDAO.getRelationList();
				StateList = lObjFormS1UpdateDAO.getStateList();
				BankList = lObjFormS1UpdateDAO.getBankList();
				payScaleList = lObjFormS1UpdateDAO.getPayScaleList();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				gLogger.info("Current date: " + dateFormat.format(date));
				gLogger.info("DcpsId is: " + DcpsEmpId);

				// Added for viewing Photo and signature
				CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,
						serv.getSessionFactory());
				CmnAttachmentMst lObjCmnAttachmentMst = null;
				/*
				CmnAttdocMstDAO lobjCmnAttdocDAO=new CmnAttdocMstDAOImpl(CmnAttdocMst.class,serv.getSessionFactory());*/
				Set localSet;
				CmnAttdocMst lobjCmnAttdocMst=null;
				if (MstEmpObj.getPhotoAttachmentID() != null && MstEmpObj.getPhotoAttachmentID().doubleValue() > 0) {
					lObjCmnAttachmentMst = new CmnAttachmentMst();
					lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
							.findByAttachmentId(Long.parseLong(MstEmpObj.getPhotoAttachmentID().toString()));

					cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();

					if (lObjCmnAttachmentMst != null) {
						lLngPhotoAttachmentId = lObjCmnAttachmentMst.getAttachmentId();
					}
					if (lObjCmnAttachmentMst != null && lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null) {
						cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();
					}
					
					cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
					Long srNo = 0L;
					for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++) {
						cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
						//for signature size
						 
						cmnAttdocMsts = new HashSet<CmnAttdocMst>();
			            cmnAttdocMsts = cmnAttachmentMpg.getCmnAttdocMsts(); 
						cmnAttdocMstIterator = cmnAttdocMsts.iterator();// getFinalAttachmentBlob 
						cmnAttdocMst =  cmnAttdocMstIterator.next();
						
						if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("photo")) {
							srNo =  cmnAttachmentMpg.getSrNo();
							
						  CmnAttachmentMpgDAOImpl localCmnAttachmentMpgDAOImpl = new CmnAttachmentMpgDAOImpl(CmnAttachmentMpg.class, serv.getSessionFactory());
					      CmnAttachmentMpg localCmnAttachmentMpg = localCmnAttachmentMpgDAOImpl.findByAttachmentSerialNumber(srNo);
					      arrayOfBytePhoto = cmnAttdocMst.getFinalAttachment();
					      localSet = localCmnAttachmentMpg.getCmnAttdocMsts();
				          if (localSet.size() >= 1)
				          {
				        	  inputMap.put("PhotoFlag", 1L); 
				        	  if((int) arrayOfBytePhoto.length > 0) {
				        		  inputMap.put("photoSize", arrayOfBytePhoto.length/1024); 
				          		}else {
				          			inputMap.put("photoSize", 0L); 
				          		}
				        	  
				        	  
				          }else
				          {
				        	  inputMap.put("PhotoFlag", 0L); 
				        	  inputMap.put("photoSize", 0L); 
				          }
							gLogger.info("PhotoFlag : "+localSet.size());
							inputMap.put("Photo", lObjCmnAttachmentMst);
							inputMap.put("PhotoId", lLngPhotoAttachmentId);
							inputMap.put("PhotosrNo", srNo);

						}
					}
				}

				if (MstEmpObj.getSignatureAttachmentID() != null
						&& MstEmpObj.getSignatureAttachmentID().doubleValue() > 0) {
					lObjCmnAttachmentMst = new CmnAttachmentMst();
					lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
							.findByAttachmentId(Long.parseLong(MstEmpObj.getSignatureAttachmentID().toString()));

					cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();

					if (lObjCmnAttachmentMst != null) {
						lLngSignAttachmentId = lObjCmnAttachmentMst.getAttachmentId();
					}
					if (lObjCmnAttachmentMst != null && lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null) {
						cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();
					}

					cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
					Long srNo = 0L;
					for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++) {
						cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
						//for signature size
						cmnAttdocMsts = new HashSet<CmnAttdocMst>();
			            cmnAttdocMsts = cmnAttachmentMpg.getCmnAttdocMsts(); 
						cmnAttdocMstIterator = cmnAttdocMsts.iterator();// getFinalAttachmentBlob 
						cmnAttdocMst = cmnAttdocMstIterator.next();
						
						if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("signature")) {
							  srNo = cmnAttachmentMpg.getSrNo();
							  CmnAttachmentMpgDAOImpl localCmnAttachmentMpgDAOImpl = new CmnAttachmentMpgDAOImpl(CmnAttachmentMpg.class, serv.getSessionFactory());
						      CmnAttachmentMpg localCmnAttachmentMpg = localCmnAttachmentMpgDAOImpl.findByAttachmentSerialNumber(srNo);
						     
						      
						      localSet = localCmnAttachmentMpg.getCmnAttdocMsts();
					          if (localSet.size() >= 1)
					          {
					        	  arrayOfByteSign =  cmnAttdocMst.getFinalAttachment();
					        	  if((int) arrayOfByteSign.length > 0) {
					        		  inputMap.put("signSize", arrayOfByteSign.length/1024); 
					          		}else {
					          			inputMap.put("signSize", 0); 
					          		}
					        	  inputMap.put("SignFlag", 1L);
					          }else
					          {
					        	  inputMap.put("SignFlag", 0L); 
					        	  inputMap.put("signSize", 0L); 
					        	  
					          }
					          gLogger.info("SignFlag : "+localSet.size());
							inputMap.put("Sign", lObjCmnAttachmentMst);
							inputMap.put("SignId", lLngSignAttachmentId);
							inputMap.put("SignsrNo", srNo);
						}
					}
				}

				// Added for viewing photo and signature overs

				inputMap.put("curretDate", dateFormat.format(date).toString());
				inputMap.put("EmpSevarthId", strEmpSevarthId);
				String[] empNameArr=strEmpName.split(" ");
				if(empNameArr.length==3) {
					inputMap.put("EmpFName", strEmpName.split(" ")[0]);
					inputMap.put("EmpMName", strEmpName.split(" ")[1]);
					inputMap.put("EmpLName", strEmpName.split(" ")[2]);
				}else if(empNameArr.length==2) {
					inputMap.put("EmpFName", strEmpName.split(" ")[0]);
					inputMap.put("EmpMName", "");
					inputMap.put("EmpLName", strEmpName.split(" ")[1]);
				}else {
					inputMap.put("EmpFName", strEmpName.split(" ")[0]);
					inputMap.put("EmpMName", "");
					inputMap.put("EmpLName", "");
				}
			
				inputMap.put("DDOCode", strDDOCode);
				inputMap.put("DtoCode", DtoCode);
				inputMap.put("DdoRegNO", DdoRegNO);
				inputMap.put("DOJ", DOJ);
				inputMap.put("dsgnName", dsgnName);
				inputMap.put("DcpsId", DcpsId);
				inputMap.put("empDetails", empDetails);
				inputMap.put("classList", classList);
				inputMap.put("empNomineeDetails", empNomineeDetails);
				inputMap.put("empNomineesize", empNomineeDetails.size());
				inputMap.put("titleList", titleList);
				inputMap.put("RelationList", lstRelationList);
				inputMap.put("StateLists", StateList);
				inputMap.put("BankLists", BankList);
				inputMap.put("payScaleLists", payScaleList);
				resObj.setResultValue(inputMap);
				resObj.setViewName("empFormS1Update");

				System.out.print("HTENEW::" + inputMap);
			//	Long lLngPkIdForFormS1Nps = IFMSCommonServiceImpl.getNextSeqNum("FRM_FORM_S1_DTLS_1", inputMap);
				//System.out.print("HTENEW" + lLngPkIdForFormS1Nps);

				//Long lLngPkIdForFormS1 = IFMSCommonServiceImpl.getNextSeqNum("FRM_FORM_S1_DTLS", inputMap);
				//gLogger.info("lLngPkIdForFormS1NPS    " + lLngPkIdForFormS1Nps);
			//	gLogger.info("lLngPkIdForFormS1    " + lLngPkIdForFormS1);
			}
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}


	public ResultObject saveFormS1Dtls(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List lstEmpForFrmS1Edit = null;
		List empDesigList = null;
		String sevarthId;
		String empName;
		String empFName;
		String empMName;
		String empLName;
		String empFatherFName;
		String empFatherMName;
		String empFatherLName;
		String empMotherFName;
		String empDOB;
		String empGender;
		String empMaritalStatus;
		String DtoCode;
		String DDOCode;
		String empDdoCode;
		String PanNo;
		String dateOfJoining;
		String dateOfRetire;
		String empClass;
		String empDept;
		String empDeptMinistry;
		String empPayScale;
		String empBasicSalary;
		String bankDetailsFlag;
		String bankDetailsType;
		String bankAccountNo;
		String bankName;
		String bankBranchName;
		String bankAddress;
		String bankState;
		String bankCountry;
		String bankPinCode;
		String bankIfscCode;
		String empNumberNominee;
		String hindiSubFlag;
		String presentAddFlatNo;
		String presentAddBuilding;
		String presentAddTaluka;
		String presentAddLandMark;
		String presentAddDist;
		String presentAddState;
		String presentAddCountry;
		String presentAddPin;
		String permanentAddFlatNo;
		String permanentAddBuilding;
		String permanentAddTaluka;
		String permanentAddLandMark;
		String permanentAddDist;
		String permanentAddState;
		String permanentAddCountry;
		String permanentAddPin;
		// String phoneSTDCode;
		String phoneNo;
		String mobileNo;
		String emailId;
		String smsSubFlag;
		String emailSubFlag;
		String nominee1Name;
		String nominee1DOB = null;
		String nominee1Relation;
		String nominee1Percent;
		String nominee1Guardian;
		String nominee1InvalidCondition;
		String nominee1Address;
		String nominee2Name;
		String nominee2DOB = null;
		String nominee2Relation;
		String nominee2Percent;
		String nominee2Guardian;
		String nominee2InvalidCondition;
		String nominee2Address;
		String nominee3Name;
		String nominee3DOB = null;
		String nominee3Relation;
		String nominee3Percent;
		String nominee3Guardian;
		String nominee3InvalidCondition;
		String nominee3Address;
		String dispNameFlag;
		String dobProofFlag;
		String eduQualFlag;
		String incomeRangeFlag;
		String DcpsId;
		String dsgnName;
		String salutation;
		String createdDate;
		String DDORegNo;
		// for photo and signature
		Long photoAttachementID = null;
		Long photoAttachementSrNo = null;
		Long signAttachementID = null;
		Long signAttachementSrNo = null;
		Set<CmnAttachmentMpg> cmnAttachmentMpgs = null;
		Set<CmnAttdocMst> cmnAttdocMsts = null;
		Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = null;
		Iterator<CmnAttdocMst> cmnAttdocMstsIterator = null;
		CmnAttachmentMpg cmnAttachmentMpg = null;
		CmnAttdocMst cmnAttdocMst = null;
		Long lLngPhotoAttachmentId = null;
		Long lLngSignAttachmentId = null;
		BufferedImage bufferedImage = null;
		 
		try {
			setSessionInfo(inputMap);
			DcpsCommonDAO lObjDcpsCommonDao = new DcpsCommonDAOImpl(null, serv.getSessionFactory());
			FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(FrmFormS1NpsDtls.class,
					serv.getSessionFactory());
			FrmFormS1NpsDtls frmNpsformUpdate = new FrmFormS1NpsDtls();
			DDOCode = lObjDcpsCommonDao.getDdoCodeForDDO(gLngPostId);
			sevarthId = StringUtility.getParameter("sevarthId", request);
			DcpsId = StringUtility.getParameter("DcpsId", request);
			dsgnName = StringUtility.getParameter("dsgnName", request);
			salutation = StringUtility.getParameter("salutation", request);
			empFName = StringUtility.getParameter("empFName", request);
			empMName = StringUtility.getParameter("empMName", request);
			empLName = StringUtility.getParameter("empLName", request);
			empName = empFName + " " + empMName + " " + empLName;
			empFatherFName = StringUtility.getParameter("empFatherFName", request);
			empFatherMName = null;
			empFatherLName = StringUtility.getParameter("empFatherLName", request);
			empMotherFName = StringUtility.getParameter("empMotherName", request);
			empDOB = StringUtility.getParameter("empDob", request);
			empGender = StringUtility.getParameter("empGender", request);
			empMaritalStatus = StringUtility.getParameter("empMaritalStatus", request);
			DtoCode = StringUtility.getParameter("DtoCode", request);
			empDdoCode = StringUtility.getParameter("hdnDDOCode", request);
			PanNo = StringUtility.getParameter("panNo", request);
			String uidNo = StringUtility.getParameter("aadharNo", request);
			dateOfJoining = StringUtility.getParameter("DOJ", request);
			dateOfRetire = StringUtility.getParameter("superAnnDate", request);
			empClass = StringUtility.getParameter("empClass", request);
			empDept = StringUtility.getParameter("empDept", request);
			String empMinistry = StringUtility.getParameter("empMinistry", request);
			String ddoNAME = StringUtility.getParameter("ddoNAME", request);
			empDeptMinistry = StringUtility.getParameter("empMinistry", request);
			empPayScale = StringUtility.getParameter("payScale", request);
			empBasicSalary = StringUtility.getParameter("basicSalary", request);

			bankDetailsFlag = "N";
			bankDetailsType = "SAVING";// StringUtility.getParameter("bankDetailsType", request);
			bankAccountNo = StringUtility.getParameter("bankAcntNo", request);
			bankName = StringUtility.getParameter("empBankName", request);
			bankBranchName = StringUtility.getParameter("empBankBranchName", request);
			bankAddress = StringUtility.getParameter("empBankBranchAddress", request);
			bankState = StringUtility.getParameter("empBankstate", request);
			bankCountry = StringUtility.getParameter("empBankCountry", request);
			bankPinCode = StringUtility.getParameter("empBankPinCode", request);
			bankIfscCode = StringUtility.getParameter("IfscCode", request);
			empNumberNominee = StringUtility.getParameter("noOfNominee", request);
			hindiSubFlag = "N";

			presentAddFlatNo = StringUtility.getParameter("presentAddFlatNo", request);
			presentAddBuilding = StringUtility.getParameter("presentAddBuilding", request);
			presentAddTaluka = StringUtility.getParameter("presentAddTaluka", request);
			presentAddLandMark = StringUtility.getParameter("presentAddLandMark", request);
			presentAddDist = StringUtility.getParameter("presentAddDist", request);
			presentAddState = StringUtility.getParameter("presentAddState", request);
			presentAddCountry = StringUtility.getParameter("presentAddCountry", request);
			presentAddPin = StringUtility.getParameter("presentAddPin", request);

			permanentAddFlatNo = StringUtility.getParameter("permanentAddFlatNo", request);
			permanentAddBuilding = StringUtility.getParameter("permanentAddBuilding", request);
			permanentAddTaluka = StringUtility.getParameter("permanentAddTaluka", request);
			permanentAddLandMark = StringUtility.getParameter("permanentAddLandMark", request);
			permanentAddDist = StringUtility.getParameter("permanentAddDist", request);
			permanentAddState = StringUtility.getParameter("permanentAddState", request);
			permanentAddCountry = StringUtility.getParameter("permanentAddCountry", request);
			permanentAddPin = StringUtility.getParameter("permanentAddPin", request);
			// phoneSTDCode=StringUtility.getParameter("phoneNo", request);
			phoneNo = StringUtility.getParameter("phoneNo", request);
			mobileNo = StringUtility.getParameter("mobileNo", request);
			emailId = StringUtility.getParameter("emailId", request);
			smsSubFlag = StringUtility.getParameter("smsSubFlag", request);
			emailSubFlag = StringUtility.getParameter("emailSubFlag", request);

			nominee1Name = StringUtility.getParameter("nominee1Name", request);
			nominee1DOB = StringUtility.getParameter("nominee1DOB", request);
			nominee1Relation = StringUtility.getParameter("nominee1Relation", request);
			nominee1Percent = StringUtility.getParameter("nominee1Percent", request);
			nominee1Guardian = StringUtility.getParameter("nominee1Guardian", request);
			nominee1InvalidCondition = StringUtility.getParameter("nominee1InvalidCondition", request);
			
			nominee1Address = StringUtility.getParameter("nominee1Address", request);  
			nominee2Address = StringUtility.getParameter("nominee2Address", request);  
			nominee3Address = StringUtility.getParameter("nominee3Address", request);  
			
			nominee2Name = StringUtility.getParameter("nominee2Name", request);
			nominee2DOB = StringUtility.getParameter("nominee2DOB", request);
			nominee2Relation = StringUtility.getParameter("nominee2Relation", request);
			nominee2Percent = StringUtility.getParameter("nominee2Percent", request);
			nominee2Guardian = StringUtility.getParameter("nominee2Guardian", request);
			nominee2InvalidCondition = StringUtility.getParameter("nominee2InvalidCondition", request);
			nominee3Name = StringUtility.getParameter("nominee3Name", request);
			nominee3DOB = StringUtility.getParameter("nominee3DOB", request);
			nominee3Relation = StringUtility.getParameter("nominee3Relation", request);
			nominee3Percent = StringUtility.getParameter("nominee3Percent", request);
			nominee3Guardian = StringUtility.getParameter("nominee3Guardian", request);
			nominee3InvalidCondition = StringUtility.getParameter("nominee3InvalidCondition", request);
			createdDate = StringUtility.getParameter("currentDate", request);
			dispNameFlag = StringUtility.getParameter("dispNameFlag", request);
			dobProofFlag = StringUtility.getParameter("dobProofFlag", request);
			eduQualFlag = StringUtility.getParameter("eduQualFlag", request);
			incomeRangeFlag = StringUtility.getParameter("incomeRangeFlag", request);
			DDORegNo = StringUtility.getParameter("DDORegNo", request);
			// FOR photo and signature
			photoAttachementID = Long.parseLong(StringUtility.getParameter("photoAttachementID", request));
			photoAttachementSrNo = Long.parseLong(StringUtility.getParameter("photoAttachementSrNo", request));
			signAttachementID = Long.parseLong(StringUtility.getParameter("signAttachementID", request));
			signAttachementSrNo = Long.parseLong(StringUtility.getParameter("signAttachementSrNo", request));
			
			Date date = new Date();
			frmNpsformUpdate.setCreatedDate(createdDate);
		 	frmNpsformUpdate.setEmpDdoCode(empDdoCode); // .setDdoCode(DDOCode);
		 	frmNpsformUpdate.setDdoCode(DDOCode);  
			frmNpsformUpdate.setDDORegNo(DDORegNo);
			frmNpsformUpdate.setDtoCode(DtoCode);
			frmNpsformUpdate.setSevarthId(sevarthId);
			frmNpsformUpdate.setDcpsId(DcpsId);
			frmNpsformUpdate.setDesignation(dsgnName);
			frmNpsformUpdate.setSaluation(salutation);
			frmNpsformUpdate.setEmpFName(empFName);
			frmNpsformUpdate.setEmpMName(empMName);
			frmNpsformUpdate.setEmpLName(empLName);
			frmNpsformUpdate.setEmpName(empName);
			frmNpsformUpdate.setEmpFatherFName(empFatherFName);
			frmNpsformUpdate.setEmpFatherMName(empFatherMName);
			frmNpsformUpdate.setEmpFatherLName(empFatherLName);
			frmNpsformUpdate.setEmpMotherFName(empMotherFName);
			frmNpsformUpdate.setEmpDOB(empDOB);
			frmNpsformUpdate.setEmpGender(empGender);
			frmNpsformUpdate.setEmpMaritalStatus(empMaritalStatus);
			// Indentity Details
			frmNpsformUpdate.setPanNo(PanNo);
			frmNpsformUpdate.setAadharNo(uidNo);
			frmNpsformUpdate.setDateOfJoining(dateOfJoining);
			frmNpsformUpdate.setDateOfRetire(dateOfRetire);
			frmNpsformUpdate.setEmpClass(empClass);
			frmNpsformUpdate.setEmpDept(empDept);
			frmNpsformUpdate.setEmpDeptMinistry(empDeptMinistry);
			frmNpsformUpdate.setEmpBasicSalary(empBasicSalary);
			frmNpsformUpdate.setEmpPayScale(empPayScale);
			// Present Address
			frmNpsformUpdate.setPresentAddFlatNo(presentAddFlatNo);
			frmNpsformUpdate.setPresentAddBuilding(presentAddBuilding);
			frmNpsformUpdate.setPresentAddTaluka(presentAddTaluka);
			frmNpsformUpdate.setPresentAddLandMark(presentAddLandMark);
			frmNpsformUpdate.setPresentAddDist(presentAddDist);
			frmNpsformUpdate.setPresentAddState(presentAddState);
			frmNpsformUpdate.setPresentAddCountry(presentAddCountry);
			frmNpsformUpdate.setPresentAddPin(presentAddPin);
			// Permanent Address
			frmNpsformUpdate.setPermanentAddFlatNo(permanentAddFlatNo);
			frmNpsformUpdate.setPermanentAddBuilding(permanentAddBuilding);
			frmNpsformUpdate.setPermanentAddTaluka(permanentAddTaluka);
			frmNpsformUpdate.setPermanentAddLandMark(permanentAddLandMark);
			frmNpsformUpdate.setPermanentAddDist(permanentAddDist);
			frmNpsformUpdate.setPermanentAddState(permanentAddState);
			frmNpsformUpdate.setPermanentAddCountry(permanentAddCountry);
			frmNpsformUpdate.setPermanentAddPin(permanentAddPin);
			// Contacts
			frmNpsformUpdate.setPhoneNo(phoneNo);
			frmNpsformUpdate.setMobileNo(mobileNo);
			frmNpsformUpdate.setEmailId(emailId);
			frmNpsformUpdate.setSmsSubFlag(smsSubFlag);
			frmNpsformUpdate.setEmailSubFlag(emailSubFlag);
			frmNpsformUpdate.setHindiSubFlag(hindiSubFlag);

			// nominees details
			frmNpsformUpdate.setEmpNumberNominee(empNumberNominee);

			frmNpsformUpdate.setNominee1Name(nominee1Name);
			frmNpsformUpdate.setNominee1DOB(nominee1DOB);
			frmNpsformUpdate.setNominee1Relation(nominee1Relation);
			frmNpsformUpdate.setNominee1Percent(nominee1Percent);
			if (nominee1Guardian.equals("NA")) {
				frmNpsformUpdate.setNominee1Guardian("");
			} else {
				frmNpsformUpdate.setNominee1Guardian(nominee1Guardian);
			}

			frmNpsformUpdate.setNominee1InvalidCondition(nominee1InvalidCondition);
			frmNpsformUpdate.setNominee1Address(nominee1Address);
			frmNpsformUpdate.setNominee2Name(nominee2Name);
			frmNpsformUpdate.setNominee2DOB(nominee2DOB);
			frmNpsformUpdate.setNominee2Relation(nominee2Relation);
			frmNpsformUpdate.setNominee2Percent(nominee2Percent);
			if (nominee2Guardian.equals("NA")) {
				frmNpsformUpdate.setNominee2Guardian("");
			} else {
				frmNpsformUpdate.setNominee2Guardian(nominee2Guardian);
			}
			
			frmNpsformUpdate.setNominee2InvalidCondition(nominee2InvalidCondition);
			frmNpsformUpdate.setNominee2Address(nominee2Address);	
			
			frmNpsformUpdate.setNominee3Name(nominee3Name);
			frmNpsformUpdate.setNominee3DOB(nominee3DOB);
			frmNpsformUpdate.setNominee3Relation(nominee3Relation);
			frmNpsformUpdate.setNominee3Percent(nominee3Percent);
			//frmNpsformUpdate.setNominee3Guardian(nominee3Guardian);
			if (nominee3Guardian.equals("NA")) {
				frmNpsformUpdate.setNominee3Guardian("");
			} else {
				frmNpsformUpdate.setNominee3Guardian(nominee2Guardian);
			}
			frmNpsformUpdate.setNominee3InvalidCondition(nominee3InvalidCondition);
			frmNpsformUpdate.setNominee3Address(nominee3Address);	
			;	
			// Bank details

			frmNpsformUpdate.setBankDetailsFlag(bankDetailsFlag);
			frmNpsformUpdate.setBankDetailsType(bankDetailsType);
			frmNpsformUpdate.setBankAccountNo(bankAccountNo);
			frmNpsformUpdate.setBankName(bankName);
			frmNpsformUpdate.setBankBranchName(bankBranchName);
			frmNpsformUpdate.setBankAddress(bankAddress);
			frmNpsformUpdate.setBankState(bankState);
			frmNpsformUpdate.setBankCountry(bankCountry);
			frmNpsformUpdate.setBankPinCode(bankPinCode);
			frmNpsformUpdate.setBankIfscCode(bankIfscCode);

			// Other details
			frmNpsformUpdate.setDispNameFlag(dispNameFlag);
			frmNpsformUpdate.setDobProofFlag(dobProofFlag);
			frmNpsformUpdate.setEduQualFlag(eduQualFlag);
			frmNpsformUpdate.setIncomeRangeFlag(incomeRangeFlag);
			// Added for viewing Photo and signature
			
			String signImageName= null;
			String photoImageName= null;
			String photo_attachment_name=null;
			String sign_attachment_name =null;	
			String images_location_path=null;
			
			 if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
				 OUTPUT_FILE_PHOTO=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_PHOTO");
				 
			}else 
			{
				OUTPUT_FILE_PHOTO=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_PHOTO_SERVER");
				
			}
			if((photoAttachementID != null && photoAttachementID.doubleValue() > 0) &&
					(signAttachementID != null && signAttachementID.doubleValue() > 0)) {
						
						try {
							
							/* Added for photo images saving */
							byte[] arrayOfByte=null;
							byte[] arrayOfByteSign=null;
							Blob  blob=null;
							CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,
									serv.getSessionFactory());
							CmnAttachmentMst lObjCmnAttachmentMst = null;
							lObjCmnAttachmentMst = new CmnAttachmentMst();
							lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
									.findByAttachmentId(Long.parseLong(photoAttachementID.toString()));
		
							cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();
		
							if (lObjCmnAttachmentMst != null && lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null) {
								cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();
							}
							cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
							Long srNo = 0L;
							for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++) {
								cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
								 
								if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("photo")) {
									srNo = cmnAttachmentMpg.getSrNo();
									cmnAttachmentMpg.getOrgFileName();
									cmnAttdocMsts = new HashSet<CmnAttdocMst>();
									cmnAttdocMsts = cmnAttachmentMpg.getCmnAttdocMsts();
									cmnAttdocMstsIterator = cmnAttdocMsts.iterator();// getFinalAttachmentBlob
									 
									Object localObject = null;
									for (Integer j = 0; j < cmnAttdocMsts.size(); j++) {
										cmnAttdocMst = cmnAttdocMstsIterator.next();
										arrayOfByte = cmnAttdocMst.getFinalAttachment();
										
										gLogger.info("photo for++ SIZE:" +cmnAttdocMsts.size()); 
									}
		
								}
							}
							//arrayOfByte.length();
							 System.out.println("File SIZe of Photo : "+arrayOfByte.length);
					  ByteArrayInputStream bisPhoto = new ByteArrayInputStream(arrayOfByte);
				      BufferedImage bImagePhoto = ImageIO.read(bisPhoto);
				      photoImageName= sevarthId+"_o_photo.jpg";
				      ImageIO.write(bImagePhoto, "jpg", new File(OUTPUT_FILE_PHOTO+"/"+photoImageName) );
				      bisPhoto.close();
				      ByteArrayOutputStream baos = new ByteArrayOutputStream();
				      ImageIO.write(bImagePhoto, "jpg", baos);
				      byte[] imageSize = baos.toByteArray();
				      System.out.println("File SIZe of Photo : "+imageSize.toString()+" "+imageSize.length) ;
				     
				      
				      				    
				      /*Compress file photo*/
				      if(imageSize.length>=12288) {
					        File img = new File(OUTPUT_FILE_PHOTO+"/"+photoImageName);
							BufferedImage image = ImageIO.read(img);
					      /* int THUMB_SIDE=385;
					      Graphics2D g2d = image.createGraphics();
					      g2d.drawImage(image.getScaledInstance(385, 413, image.SCALE_SMOOTH), 0, 0, 385, 413, null);
					      g2d.dispose();*/
					      String tempPhotoImageName= sevarthId+"_photo.jpg";
					      OutputStream out = new FileOutputStream(OUTPUT_FILE_PHOTO+"/"+tempPhotoImageName);
					      ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
					      ImageOutputStream ios = ImageIO.createImageOutputStream(out);
					      writer.setOutput(ios);
	
					      ImageWriteParam param = writer.getDefaultWriteParam();
	
					      /*if (param.canWriteCompressed()) {*/
						      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
						      param.setCompressionQuality(0.08f);
					     // }
					      writer.write(null, new IIOImage(image, null, null),param); 
					      out.close();
					      ios.close();
					      writer.dispose();
					      photoImageName=tempPhotoImageName;
				      }
				      baos.close();
				      
				      System.out.println("Photo image created");	
				   
				      /* photo images saving ended*/
				   /*  Signature images saving*/ 
				    lObjCmnAttachmentMst = new CmnAttachmentMst();
					lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO.findByAttachmentId(Long.parseLong(signAttachementID.toString()));
					cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();
	
						if (lObjCmnAttachmentMst != null && lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null) {
							cmnAttachmentMpgs = lObjCmnAttachmentMst.getCmnAttachmentMpgs();
						}
						cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
						Long srNoSign = 0L;
						for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++) {
							cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
							 
							if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase("signature")) {
								srNoSign = cmnAttachmentMpg.getSrNo();
								cmnAttachmentMpg.getOrgFileName();
								cmnAttdocMsts = new HashSet<CmnAttdocMst>();
								cmnAttdocMsts = cmnAttachmentMpg.getCmnAttdocMsts();
								cmnAttdocMstsIterator = cmnAttdocMsts.iterator(); 
								Object localObject = null;
								for (Integer j = 0; j < cmnAttdocMsts.size(); j++) {
									cmnAttdocMst = cmnAttdocMstsIterator.next();
									arrayOfByteSign = cmnAttdocMst.getFinalAttachment();
									gLogger.info("Signatureoutfor++ SIZE:" +cmnAttdocMsts.size()); 
								}
	
							}
						}
						System.out.println("File SIZe of Signature : "+arrayOfByteSign.length); 
				      signImageName= sevarthId+"_o_sign.jpg"; 
				      ByteArrayInputStream bisSign = new ByteArrayInputStream(arrayOfByteSign);
				      BufferedImage bImageSign = ImageIO.read(bisSign);
				      ImageIO.write(bImageSign, "jpg", new File(OUTPUT_FILE_PHOTO+"/"+signImageName) );
				      bisSign.close();
				      /*********************************/
				      		/*Compress sign images */
				    
				      ByteArrayOutputStream baosSign = new ByteArrayOutputStream();
				      ImageIO.write(bImageSign, "jpg", baosSign);
				      byte[] imageSignSize = baosSign.toByteArray();
				      System.out.println("File Size of Sign : "+imageSignSize.toString()+" "+imageSignSize.length) ;
				      
				      if(imageSignSize.length>=12228) {
				    	  System.out.println("File Size of Sign : "+imageSignSize.toString()+" "+imageSignSize.length) ;
							String tempSignImageName = sevarthId + "_sign.jpg";
							File imgS = new File(OUTPUT_FILE_PHOTO + "/" + signImageName);
							BufferedImage images = ImageIO.read(imgS);
							/*
							 * int width = images.getWidth(); int height = images.getHeight(); int
							 * signSize=360; Graphics2D g2dSign = images.createGraphics();
							 * g2d.drawImage(images.getScaledInstance(width, height, images.SCALE_SMOOTH),
							 * 0, 0, width, height, null); g2d.dispose();
							 */
							OutputStream outSign = new FileOutputStream(OUTPUT_FILE_PHOTO + "/" + tempSignImageName);
							ImageWriter writerSign = ImageIO.getImageWritersByFormatName("jpg").next();
							ImageOutputStream iosSign = ImageIO.createImageOutputStream(outSign);
							writerSign.setOutput(iosSign);
							ImageWriteParam paramSign = writerSign.getDefaultWriteParam();
							// if (paramSign.canWriteCompressed()) {
							paramSign.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
							paramSign.setCompressionQuality(0.08f);
							// }
							writerSign.write(null, new IIOImage(images, null, null), paramSign);
							outSign.close();
							iosSign.close();
							writerSign.dispose();
							signImageName=tempSignImageName;
						}
				      baosSign.close();
				     
				     // ByteArrayOutputStream outputStreamSign = new ByteArrayOutputStream();
				      /*********************************/
				     
				      System.out.println("Sign image created");
				      /*  Signature images saving Ended */
						} catch (Exception e) {
							//gLogger.info("lLngPkIdForFormS1NPS  Photo  " + e.getMessage());
							gLogger.info("lLngPkIdForFormS1NPS  Signature  " +e.getCause());
							resObj.setViewName("errorPage");
						}
				}else {
				
			}
			
			frmNpsformUpdate.setPhoto_attachment_name(photoImageName);
			frmNpsformUpdate.setSign_attachment_name(signImageName);
			frmNpsformUpdate.setImages_location_path(OUTPUT_FILE_PHOTO);
			
			//photo_attachment_name
			
			// Ended for viewing Photo and signature
			System.out.print(inputMap);
			Long lLngPkIdForFormS1Nps = IFMSCommonServiceImpl.getNextSeqNum("FRM_FORM_S1_DTLS_1", inputMap);
			frmNpsformUpdate.setFormS1Id(lLngPkIdForFormS1Nps);
			gLogger.info("lLngPkIdForFormS1NPS " + lLngPkIdForFormS1Nps);
			System.out.print(lLngPkIdForFormS1Nps);
			//int ins=1;
			int ins=lObjFormS1UpdateDAO.insertRecordToNps(frmNpsformUpdate, dateOfJoining, nominee1DOB, nominee2DOB, nominee3DOB, DDOCode, lLngPkIdForFormS1Nps);
			gLogger.info("insert Status    " + ins);
			if(ins>0) {
				inputMap.put("msg", "Data Save succuessfully");
			}else {
				inputMap.put("msg", "");
			}
			lstEmpForFrmS1Edit = lObjFormS1UpdateDAO.getEmpListForFrmS1Edit(DDOCode, "0", "0", "");
			empDesigList = lObjFormS1UpdateDAO.getEmpDesigList(DDOCode);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			// Date date = new Date();
			gLogger.info("Current date: " + dateFormat.format(date));

			inputMap.put("curretDate", dateFormat.format(date).toString());
			inputMap.put("EmpSevarthId", sevarthId);
			inputMap.put("EmpName", empFName + " " + empMName + " " + empLName);
			inputMap.put("DDOCode", DDOCode);
			inputMap.put("empList", lstEmpForFrmS1Edit);
			
			inputMap.put("empDesigList", empDesigList);
			resObj.setResultValue(inputMap);
			resObj.setViewName("empListForFormS1Update");
		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	 
	private String parseDate(String strDate) {
		// TODO Auto-generated method stub
		if (strDate != "") {
			String tmp[] = strDate.split("/");
			return (tmp[2] + "-" + tmp[1] + "-" + tmp[0]);
		} else {
			return null;
		}

	}

	public ResultObject validateFormS1ForEdit(Map objectArgs) {
		gLogger.info("inside validateUIDUniqeness");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		FormS1UpdateDAO lObjFormS1UpdateDAO = new FormS1UpdateDAOImpl(FrmFormS1Dtls.class, serv.getSessionFactory());
		String strSevarthId = null;
		Long finalCheckFlag = null;
		String lStrResult = null;
		try {

			strSevarthId = StringUtility.getParameter("empSevarthId", request).trim();
			gLogger.info("--------empSevarthId--------:" + strSevarthId);

			finalCheckFlag = lObjFormS1UpdateDAO.checkFormS1(strSevarthId);

			String status = null;
			if (finalCheckFlag > 0) {
				status = "wrong";
			}

			else {
				status = "correct";
			}
			String lSBStatus = getResponseXMLDoc(status).toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			objectArgs.put("ajaxKey", lStrResult);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
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

	/*public ResultObject getEmpListForFormS1Edit111(Map inputMap) throws Exception {
		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		resObj.setResultValue(inputMap);
		resObj.setViewName("empFormS1Update2");
		return resObj;

	}*/

	public ResultObject fileUpload(Map objectArgs) {
		gLogger.info("inside validateUIDUniqeness");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		Long finalCheckFlag = null;
		String lStrResult = null;
		try {
			String status = null;
			if (finalCheckFlag > 0) {
				status = "wrong";
			}

			else {
				status = "correct";
			}
			String lSBStatus = getResponseXMLDoc(status).toString();
			lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus).toString();

			objectArgs.put("ajaxKey", lStrResult);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("ajaxData");

		} catch (Exception e) {
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;

		
	}

	/*
	 * @PostMapping("/upload") public ResponseEntity<?>
	 * handleFileUpload( @RequestParam("file") MultipartFile file ) {
	 * 
	 * String fileName = file.getOriginalFilename(); try { file.transferTo( new
	 * File("C:\\upload\\" + fileName)); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } return
	 * ResponseEntity.ok("File uploaded successfully."); }
	 */
}