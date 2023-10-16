package com.tcs.sgv.pensionproc.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pensionproc.dao.Form5DAOImpl;
import com.tcs.sgv.pensionproc.dao.PensionCaseReportDAOImpl;

public class PensionCaseReportServiceImpl extends ServiceImpl{

	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for LangId */
	Long gLngLangId = null;

	/* Global Variable for EmpId */
	Long gLngEmpId = null;

	/* Global Variable for Location Id */
	String gStrLocId = null;

	Long gLngLocId = null;
	/* Global Variable for DB Id */
	Long gLngDBId = null;

	/* Global Variable for Current Date */
	Date gCurDate = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	private Locale gLclLocale = null; /* LOCALE */
	private String gStrPostId = null; /* STRING POST ID */
	private String gStrUserId = null; /* STRING USER ID */
	private HttpServletRequest request = null; /* REQUEST OBJECT */
	private ServiceLocator serv = null; /* SERVICE LOCATOR */
	private HttpSession session = null; /* SESSION */
	private String gStrLocale = null; /* STRING LOCALE */
	private Date gDtCurDate = null; /* CURRENT DATE */

	private static final Log logger = LogFactory.getLog(PensionCaseServiceImpl.class); /* LOGGER */

	private void setSessionInfo(Map inputMap) throws Exception {

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
			gStrLocId = SessionHelper.getLocationId(inputMap).toString();
			gLngLocId = SessionHelper.getLocationId(inputMap);
		} catch (Exception e) {
			logger.error("Error in setSessionInfo of PensionCaseServiceImpl ", e);
			throw e;
		}
	}

	public ResultObject loadPensionCaseForm(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {

			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			PensionCaseReportDAOImpl lObjPensionCaseReportDAO = new PensionCaseReportDAOImpl(null, serv.getSessionFactory());
			String status="no";
			inputMap.put("status", status);
			resObj.setViewName("loadPensionCaseForm");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	
	public ResultObject getPensionerCaseDtls(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			String status="no";
			String flag="false";
			Object[] pnsrDtls=null;
			String sevaarthId=null;
			String name="";
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			PensionCaseReportDAOImpl lObjPensionCaseReportDAO = new PensionCaseReportDAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request).trim();
			
			//String EmpName=StringUtility.getParameter("EmpName", request);
			logger.info("gLngPostId&&&&&&&&&"+gLngPostId);
			String chckSevarth=lObjPensionCaseReportDAO.checkSevarthId(sevaarthID,gLngPostId);
			logger.info("chckSevarth&&&&&&&&&"+chckSevarth);
			if(chckSevarth.equals("valid")){
			pnsrDtls=lObjPensionCaseReportDAO.getPensionerDtls(sevaarthID);
			int result =lObjPensionCaseReportDAO.updateDdoCodeForNull(sevaarthID);
			if(pnsrDtls!=null){
				status="yes";
				sevaarthId=pnsrDtls[1].toString();
				name=pnsrDtls[0].toString();
			}
			}
			inputMap.put("sevaarthId", sevaarthId);
			inputMap.put("name", name);
			inputMap.put("status", status);
			inputMap.put("flag", flag);
			inputMap.put("pnsrDtls", pnsrDtls);
			inputMap.put("chckSevarth", chckSevarth);
			resObj.setViewName("loadPensionCaseForm");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	public ResultObject checkPensionCaseStatus(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			Object[] pnsrDtls=null;
			String flag="NA";
			String status="yes";
			String sevaarthId="";
			String name="";
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			PensionCaseReportDAOImpl lObjPensionCaseReportDAO = new PensionCaseReportDAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request).trim();
			//String EmpName=StringUtility.getParameter("EmpName", request);
			String caseStatus=lObjPensionCaseReportDAO.checkCaseStatus(sevaarthID);
			if(caseStatus.equals("APRVDBYDDO") || caseStatus.equals("APRVDBYAG") || caseStatus.equals("APRVDBYHO") || caseStatus.equals("FRWDTOAG") )
			{
				flag="true";
			}
			
			int result=lObjPensionCaseReportDAO.updateDdoCodeForNull(sevaarthID);			
			pnsrDtls=lObjPensionCaseReportDAO.getPensionerDtls(sevaarthID);
			if(pnsrDtls!=null){
				status="yes";
				sevaarthId=pnsrDtls[1].toString();
				name=pnsrDtls[0].toString();
			}
			inputMap.put("sevID",sevaarthID);
			inputMap.put("name", name);
			inputMap.put("status",status);
			inputMap.put("flag",flag);
			inputMap.put("pnsrDtls", pnsrDtls);
			resObj.setViewName("loadPensionCaseForm");
			resObj.setResultValue(inputMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	
	public ResultObject viewPensionCaseReport(Map inputMap){

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			Object[] AddrAfterRetirement=null;
			int famDtlsSize=0;
			int NomDtlsSize=0;
			int AvgPaySize=0;
			String NonineeName="";
			String relation="";
			int AdvanceSize=0;
			int ServiceBrkSize=0;
			int ProvGratuitySize=0;
			int ProvPensionSize=0;
			String fromDate=null;
			String[] fromDateArray;
			String toDateArray[];
			int year[]=new int[50];
			int month[]=new int[50];
			int days[]=new int[50];
			String toDate=null;
			setSessionInfo(inputMap);
			Map loginDetailsMap = (Map) inputMap.get("baseLoginMap");
			PensionCaseReportDAOImpl lObjPensionCaseReportDAO = new PensionCaseReportDAOImpl(null, serv.getSessionFactory());
			String sevaarthID=StringUtility.getParameter("sevaarthID", request).trim();
			//Added by harsh
			Object[] getDDODtls=lObjPensionCaseReportDAO.getDDODtls(sevaarthID);
			String pnsnrName=getDDODtls[6].toString();
		    logger.info("pnsnrName is "+pnsnrName);
		    String[] fullName= null;
		   String EmpFname="";
		   String EmpMname="";
		   String EmpLname="";
		   String EmpFathrname="";
		   String EmpFathrFname="";
		   String EmpFathrLname="";
            if(!pnsnrName.equals(""))
                 fullName = pnsnrName.split(" ");
            
            if(fullName!=null && fullName.length>0)
            {
                if(fullName.length==1)
                {
                	EmpFname=fullName[0];    
                	   
                }
                else if(fullName.length==2)
                {
                 EmpFname=(fullName[0]);
                 EmpLname=(fullName[1]);         
         		EmpFathrLname=fullName[1];    
        
                }
                else if(fullName.length==3)
                {
                  EmpFname=(fullName[0]);
                  EmpMname=(fullName[1]);
                  EmpLname=(fullName[2]);
                EmpFathrFname=fullName[1];    
          		EmpFathrLname=fullName[2];    
                }
                else
                {
                  EmpFname=(fullName[0]);
                  EmpMname=(fullName[1]);
                    String lName ="";
                     for(int i=2;i<fullName.length;i++)
                     {
                         lName += fullName[i];
                     }
                          EmpLname=(lName);
                   EmpFathrFname=fullName[1];    
                   EmpFathrLname=lName;       
                }
                EmpFathrname=EmpFathrFname+" "+EmpFathrLname;
            }
			//Object[] getDDODtls=lObjPensionCaseReportDAO.getDDODtls(sevaarthID);
			String gpfAccNo=lObjPensionCaseReportDAO.getGPFAccNo(sevaarthID);
			Object[] NomineeDtls=lObjPensionCaseReportDAO.getCaseNomineeDtls(sevaarthID);
			Object[] PensionerAddr=lObjPensionCaseReportDAO.getPensionerAddr(sevaarthID);
			if(PensionerAddr[0].toString().equals("Y")){
				AddrAfterRetirement=PensionerAddr;
			}
			else{
			AddrAfterRetirement=lObjPensionCaseReportDAO.getPensionerAddrRetirement(sevaarthID);
			}
			List FamilyDtls=lObjPensionCaseReportDAO.getFamilyCaseDtls(sevaarthID);
			if(FamilyDtls!=null){
			famDtlsSize=FamilyDtls.size();
			}
			/*Iterator it=FamilyDtls.iterator();
			while(it.hasNext()){
				Object rowlist[]=(Object[]) it.next();
				String abc=rowlist[0].toString();
				System.out.println("abc" +abc);
				System.out.println("rowlist" +rowlist[1].toString());
				
			}*/
			List PensionerNomDtls=lObjPensionCaseReportDAO.PensionerNomineeDtls(sevaarthID);
			if(PensionerNomDtls!=null){
				Iterator it=PensionerNomDtls.iterator();
				while(it.hasNext()){
					Object rowlist[]=(Object[]) it.next();
					NonineeName=rowlist[0].toString();
					relation=rowlist[2].toString();
				}
				NomDtlsSize=PensionerNomDtls.size();
			}
			String Avgtotal="";
			String avgPay="";
			List AvgPayDtls=lObjPensionCaseReportDAO.getAvgPayDtls(sevaarthID);
			if(AvgPayDtls!=null){
				AvgPaySize=AvgPayDtls.size();
				Iterator it1=AvgPayDtls.iterator();
				while(it1.hasNext()){
					Object rowlist1[]=(Object[]) it1.next();
					 Avgtotal=rowlist1[5].toString();
					 avgPay=rowlist1[6].toString();
				}
					
				}
			List AdvanceDtls=lObjPensionCaseReportDAO.getAdvanceDtls(sevaarthID);
			if(AdvanceDtls!=null){
				AdvanceSize=AdvanceDtls.size();
			}
			List ServiceBrkDtls=lObjPensionCaseReportDAO.getServiceBrkDtls(sevaarthID);
			
			if(ServiceBrkDtls!=null && ServiceBrkDtls.size()>0){
				ServiceBrkSize=ServiceBrkDtls.size();
				Iterator it2=ServiceBrkDtls.iterator();
				while(it2.hasNext()){
					int i=0;
					Object rowlist1[]=(Object[]) it2.next();
					 fromDate=rowlist1[0].toString();
					 
					 toDate=rowlist1[1].toString();
					 fromDateArray=fromDate.split("/");
					 toDateArray=toDate.split("/");
					  year[i]=Integer.parseInt(toDateArray[2])-Integer.parseInt(fromDateArray[2]);
					  month[i]=Integer.parseInt(toDateArray[1])-Integer.parseInt(fromDateArray[1]);
					  days[i]=Integer.parseInt(toDateArray[0])-Integer.parseInt(fromDateArray[0]);
					  i++;
				}
				
			}
			
			Object[] PensionerPayDtls=lObjPensionCaseReportDAO.getPensionerPayDtls(sevaarthID);
			String[] payScale=PensionerPayDtls[0].toString().split("G");
			
			Object[] PensionCalcDtls=lObjPensionCaseReportDAO.getPensionCalcDtls(sevaarthID);
			Object[] ProvDtls=lObjPensionCaseReportDAO.getprovDtls(sevaarthID);
			Object[] gratDdoDtls=lObjPensionCaseReportDAO.getGratDdoDtls(sevaarthID);
			List pensionType1=lObjPensionCaseReportDAO.getPensionType(sevaarthID);
			Object[] familyPensioner=lObjPensionCaseReportDAO.getFamilyPensionerDtls(sevaarthID);
			//logger.info("familyPensioner[0]"+familyPensioner[0]);
			//Added by shraddha for Prov amount details
			List ProvPension=lObjPensionCaseReportDAO.getProvPension(sevaarthID);
			if(ProvPension!=null && ProvPension.size()>0){
				ProvPensionSize=ProvPension.size();}
			List ProvGratuity=lObjPensionCaseReportDAO.getProvGrat(sevaarthID);
			if(ProvGratuity!=null && ProvGratuity.size()>0){
				ProvGratuitySize=ProvGratuity.size();}
			
		String pensionType=	pensionType1.get(0).toString();
			System.out.println("pensionType****"+pensionType);
			String typeName="";
			if(pensionType.equals("ABSORPTION")){
				typeName="Absorption Pension (Rule 67)";
			}
			
			else if(pensionType.equals("COMPASSIONATE")){
				typeName="Compassionate  Pension (Rule 101)";
			}
			else if(pensionType.equals("COMPENSATION")){
				typeName="Compensation Pension";
			}
			else if(pensionType.equals("COMPULSORY")){
				typeName="Compulsory Retirement (Rule 100)";
			}
			
			else if(pensionType.equals("EXTRAORDINARY")){
				typeName="Extra Ordinary Pension (As per Rule 62(8) And Rule 23";
			}
			else if(pensionType.equals("FAMILYPNSN")){
				typeName="Family Pension";
			}
			else if(pensionType.equals("INVALID")){
				typeName="Invalid Pension (Rule 68)";
			}
			
			else if(pensionType.equals("RETIRING105")){
				typeName="Retiring Pension (Vol) (Rule 66) And 65(1)(a), 10(5)";
			}
			
			else if(pensionType.equals("RETIRING104")){
				typeName="Retiring Pension 65(1) (b) And 10(4)";
			}
			
			else if(pensionType.equals("SUPERANNU")){
				typeName="Superannuation Pension (Rule 63)";
			}
			else if(pensionType.equals("VOLUNTARY64")){
				typeName="Voluntary Pension Rule 64 (55 Yrs Service)";
			}
			
			else if(pensionType.equals("VOLUNTARY65")){
				typeName="Voluntary Pension Rule 65 (30 Yrs Service)";
			}
			
			else if(pensionType.equals("INJURY")){
				typeName="Wound or Injury Pension (Rule 85)";
			}
			else if(pensionType.equals("GALLANTRY")){
				typeName="Gallantry Pension";
			}
			
			inputMap.put("NomineeDtls", NomineeDtls);
			inputMap.put("gpfAccNo", gpfAccNo);
			inputMap.put("getDDODtls", getDDODtls);
			inputMap.put("PensionerAddr", PensionerAddr);
			inputMap.put("AddrAfterRetirement", AddrAfterRetirement);
			inputMap.put("FamilyDtls", FamilyDtls);
			inputMap.put("famDtlsSize", famDtlsSize);
			inputMap.put("PensionerNomDtls", PensionerNomDtls);
			inputMap.put("NonineeName", NonineeName);
			inputMap.put("relation", relation);
			inputMap.put("NomDtlsSize", NomDtlsSize);
			inputMap.put("AvgPayDtls", AvgPayDtls);
			inputMap.put("AvgPaySize", AvgPaySize);
			inputMap.put("Avgtotal", Avgtotal);
			inputMap.put("avgPay", avgPay);
			inputMap.put("AdvanceDtls", AdvanceDtls);
			inputMap.put("AdvanceSize", AdvanceSize);
			inputMap.put("ServiceBrkDtls", ServiceBrkDtls);
			inputMap.put("ServiceBrkSize", ServiceBrkSize);
			inputMap.put("PensionerPayDtls", PensionerPayDtls);
			inputMap.put("payScale", payScale[0]);
			inputMap.put("PensionCalcDtls", PensionCalcDtls);
			inputMap.put("ProvDtls", ProvDtls);
			inputMap.put("year", year);
			inputMap.put("month", month);
			inputMap.put("days", days);
			inputMap.put("typeName", typeName);
			inputMap.put("gratDdoDtls", gratDdoDtls);
			inputMap.put("familyPensioner", familyPensioner);
			inputMap.put("ProvPension", ProvPension);
			inputMap.put("ProvGratuity", ProvGratuity);
			inputMap.put("ProvPensionSize", ProvPensionSize);
			inputMap.put("ProvGratuitySize", ProvGratuitySize);
			//Added by harsh
			inputMap.put("EmpFname", EmpFname);
			inputMap.put("EmpMname", EmpMname);
			inputMap.put("EmpLname", EmpLname);
			inputMap.put("EmpFathrname", EmpFathrname);
			//added by ankita
			Object[] QSerDtls=lObjPensionCaseReportDAO.getGrossQualifyingService(sevaarthID);
			inputMap.put("QSerDtls", QSerDtls);
			Object[] AddtnCVPDtls=lObjPensionCaseReportDAO.getAddtnCVPDtls(sevaarthID);
			inputMap.put("AddtnCVPDtls", AddtnCVPDtls);
			
			resObj.setViewName("viewPensionCaseReport");
			resObj.setResultValue(inputMap);
			
	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}
	


}