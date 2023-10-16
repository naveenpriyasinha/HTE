package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.HrForm16BankMstDaoImpl;
import com.tcs.sgv.eis.valueobject.Form16CustomVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrForm16BankMst;
import com.tcs.sgv.eis.valueobject.HrForm16Dtls;
import com.tcs.sgv.eis.valueobject.HrForm16TaxDeducDtls;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class Form16VOGEN extends ServiceImpl {
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject getForm16Data(Map objectArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		
		
		
		try{
		logger.info("DesigMasterVOGEN getDesigData Called");
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpSession session=request.getSession();
		
		String EmpId="";
		long year=0;
		long deptId=0;
		long office_add=0;
		String strTanNum="";
		String strITO="";
		String strQuarter1="";
		String strQuarter2="";
		String strQuarter3="";
		String strQuarter4=" ";
		String isInserted="";
		String isUpdate="";
		long headerId = 0;
		
		if(StringUtility.getParameter("txtEmpId", request)!=null && !StringUtility.getParameter("txtEmpId", request).equals(""))
		{
			EmpId=StringUtility.getParameter("txtEmpId", request).toString();
		}
		if(StringUtility.getParameter("selDept", request)!=null && !StringUtility.getParameter("selDept", request).equals(""))
		{
			deptId=Long.parseLong(StringUtility.getParameter("selDept", request).toString());
		}
		if(StringUtility.getParameter("selYear", request)!=null && !StringUtility.getParameter("selYear", request).equals(""))
		{
			year=Long.parseLong(StringUtility.getParameter("selYear", request).toString());
		}
		if(StringUtility.getParameter("office_address", request)!=null && !StringUtility.getParameter("office_address", request).equals(""))
		{
			office_add=Long.parseLong(StringUtility.getParameter("office_address", request).toString());
	
		}
		if(StringUtility.getParameter("txtTan", request)!=null && !StringUtility.getParameter("txtTan", request).equals(""))
		{
			strTanNum=StringUtility.getParameter("txtTan", request).toString();
		}
		if(StringUtility.getParameter("txtITO", request)!=null && !StringUtility.getParameter("txtITO", request).equals(""))
		{
			strITO=StringUtility.getParameter("txtITO", request).toString();
		}
		if(StringUtility.getParameter("txtquarter1", request)!=null && !StringUtility.getParameter("txtquarter1", request).equals(""))
		{
			strQuarter1=StringUtility.getParameter("txtquarter1", request).toString();
		}
		if(StringUtility.getParameter("txtquarter2", request)!=null && !StringUtility.getParameter("txtquarter2", request).equals(""))
		{
			strQuarter2=StringUtility.getParameter("txtquarter2", request).toString();
		}
		if(StringUtility.getParameter("txtquarter3", request)!=null && !StringUtility.getParameter("txtquarter3", request).equals(""))
		{
			strQuarter3=StringUtility.getParameter("txtquarter3", request).toString();
		}
		if(StringUtility.getParameter("txtquarter4", request)!=null && !StringUtility.getParameter("txtquarter4", request).equals(""))
		{
			strQuarter4=StringUtility.getParameter("txtquarter4", request).toString();
		}
		
		if(StringUtility.getParameter("isInserted", request)!=null && !StringUtility.getParameter("isInserted", request).equals(""))
		{
			isInserted=StringUtility.getParameter("isInserted", request).toString();
		}  
		if(StringUtility.getParameter("isUpdate", request)!=null && !StringUtility.getParameter("isUpdate", request).equals(""))
		{
			isUpdate=StringUtility.getParameter("isUpdate", request).toString();
		} 
		if(StringUtility.getParameter("headerId", request)!=null && !StringUtility.getParameter("headerId", request).equals(""))
		{
			headerId=Long.parseLong(StringUtility.getParameter("headerId", request).toString());
		}
		
		
		objectArgs.put("EmpId",EmpId);
		objectArgs.put("year",year);
		objectArgs.put("office_add",office_add);
		objectArgs.put("strTanNum",strTanNum);
		objectArgs.put("strITO",strITO);
		objectArgs.put("strQuarter1",strQuarter1);
		objectArgs.put("strQuarter2",strQuarter2);
		objectArgs.put("strQuarter3",strQuarter3);
		objectArgs.put("strQuarter4",strQuarter4);
		objectArgs.put("isInserted",isInserted);
		objectArgs.put("isUpdate",isUpdate);
		objectArgs.put("deptId",deptId);
		objectArgs.put("headerId",headerId);
		
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		
		}
		catch(Exception e){
			retObj.setResultCode(ErrorConstants.ERROR);
			logger.error("Error is: "+ e.getMessage());
			return retObj;
			
		}
		return retObj;
		
	}
	public ResultObject addForm16Details(Map objectArgs) 
	{
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		//Map loginMap = (Map) objectArgs.get("baseLoginMap");
		try{
			logger.info("Form16VOGEN addForm16Details Called");
			//logger.info("Form16VOGEN addForm16Details Called");
			
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
			
	        LoginDetails objLoginDetails = (LoginDetails) request.getSession().getAttribute("loginDetails");
	        
	        
	        OrgEmpMst orgEmpMst = new OrgEmpMst();
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serviceLocator.getSessionFactory());
	        EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serviceLocator.getSessionFactory());
	        HrEisEmpMst setHrEisEmpMst = new HrEisEmpMst();
	        HrForm16Dtls form16Dtls = new HrForm16Dtls();
	        Form16CustomVO form16CustomVO = new Form16CustomVO(); 
	        List<HrForm16TaxDeducDtls> list = new ArrayList<HrForm16TaxDeducDtls>();
	        
	        String empOfficerID = StringUtility.getParameter("Employee_ID_ITDetails", request);
            logger.info("Emp ID Employee_ID_empOfficer::::" + empOfficerID);
            //logger.info("Emp ID Employee_ID_empOfficer::::" + empOfficerID);
            
            /*String empNameOff = StringUtility.getParameter("Employee_Name_ITDetails", request);
            String gpfNoOff = StringUtility.getParameter("GPF_NM_ITDetails", request);
            String degnOff = StringUtility.getParameter("Dsgn_NM_ITDetails", request);
            String policeStationOff = StringUtility.getParameter("Police_ST_NM_ITDetails", request);
            String districtOff = StringUtility.getParameter("DISTRICT_NM_ITDetails", request);*/
            
            String strOtherAllowance = StringUtility.getParameter("OtherAllowance", request);
            String strForeignAllow = StringUtility.getParameter("foreignAllow", request);
            String strTaxPaidChallan = StringUtility.getParameter("TaxPaidChallan", request);
            String strTaxDedArrear = StringUtility.getParameter("TaxDedArrear", request);
            String strFinYear = StringUtility.getParameter("FinYear", request);
            String hbaIntrestClaimed = StringUtility.getParameter("hbaIntrestClaimed", request);
            String hbaRepayClaimed = StringUtility.getParameter("hbaRepayClaimed", request);
            String challanNumber = StringUtility.getParameter("challanNumber", request);
            
            String travelAllow = StringUtility.getParameter("travelAllow", request);
            String profTax = StringUtility.getParameter("profTax", request);
            String hbaIntrest = StringUtility.getParameter("hbaIntrest", request);
            String gpfCpf = StringUtility.getParameter("gpfCpf", request);
            String govtInsurance = StringUtility.getParameter("govtInsurance", request);
            String repayHba = StringUtility.getParameter("repayHba", request);
          
            
            logger.info("strOtherAllowance  " + strOtherAllowance);
            logger.info("strForeignAllow  " + strForeignAllow);
            logger.info("strTaxPaidChallan  " + strTaxPaidChallan);
            logger.info("strTaxDedArrear  " + strTaxDedArrear);
            logger.info("strFinYear  " + strFinYear);
            
            if(empOfficerID != null && !"".equals(empOfficerID) ){
            	
            	orgEmpMst = empDAOImpl.read(Long.parseLong(empOfficerID));
				List<HrEisEmpMst> eisList  = empInfoDAOImpl.getHrEmpFromOrgEmp(orgEmpMst);
				form16Dtls.setHrEisEmpMst((HrEisEmpMst)eisList.get(0));
            	
				/*setHrEisEmpMst = empInfoDAOImpl.read(Long.parseLong(empOfficerID));
            	form16Dtls.setHrEisEmpMst(setHrEisEmpMst);*/
            }
            if(strOtherAllowance != null && !"".equals(strOtherAllowance)){
            	form16Dtls.setOtherAllow(Double.parseDouble(strOtherAllowance));
            }
            if(strForeignAllow != null && !"".equals(strForeignAllow)){
            	form16Dtls.setForeignAllow(Double.parseDouble(strForeignAllow));
            }
            if(strTaxPaidChallan != null && !"".equals(strTaxPaidChallan))
            {
            	form16Dtls.setChallanTax(Double.parseDouble(strTaxPaidChallan));
            }
            if(strTaxDedArrear != null && !"".equals(strTaxDedArrear))
            {
            	form16Dtls.setArrearTax(Double.parseDouble(strTaxDedArrear));
            }
            if(strFinYear != null && !"".equals(strFinYear) && !"Select".equals(strFinYear))
            {
            	CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class,serviceLocator.getSessionFactory());
            	CmnLookupMst lookupMst = cmnLookupMstDAOImpl.getLookUpVOByLookUpNameAndLang(strFinYear, objLoginDetails.getLangId());
            	//List <CmnLookupMst> lookupMstList = cmnLookupMstDAOImpl.getListByColumnAndValue("lookupShortName", strFinYear);
            	
            	//CmnLookupMst lookupMst = lookupMstList.get(0);
            	logger.info("Double lookupMst  " +lookupMst.getLookupId());
            	form16Dtls.setFinYrId(lookupMst.getLookupShortName());
            	
            	//form16Dtls.setFinYrId(strFinYear);
            }
            if(travelAllow!= null && !"".equals(travelAllow)){ 
            	form16Dtls.setTravelAllow(Double.parseDouble(travelAllow));
            }
            if(profTax!= null && !"".equals(profTax)){
            	form16Dtls.setProfTax(Double.parseDouble(profTax));
            }
            if(hbaIntrest!= null && !"".equals(hbaIntrest)) {
            	form16Dtls.setHbaIntrest(Double.parseDouble(hbaIntrest));
            }
            if(gpfCpf!= null && !"".equals(gpfCpf)){
            	form16Dtls.setGpfCpf(Double.parseDouble(gpfCpf));
            }
            if(govtInsurance!= null && !"".equals(govtInsurance)){
            	form16Dtls.setGovtInsurance(Double.parseDouble(govtInsurance));
            }
            if(repayHba!= null && !"".equals(repayHba)){
            	form16Dtls.setRepayHba(Double.parseDouble(repayHba));
            }
            if(challanNumber!=null && !"".equals(challanNumber)){
            	form16Dtls.setChallanNumber(challanNumber);
            }
           
            if(hbaIntrestClaimed.equals(""))
            	{
            		form16Dtls.setHbaInterestClaimed(0);			
            	}
			else
				{
					form16Dtls.setHbaInterestClaimed(1);	
				}
            
            if(hbaRepayClaimed.equals(""))
            	{
            		form16Dtls.setHbaRepayClaimed(0);				
            	}
			else
				{
					form16Dtls.setHbaRepayClaimed(1);	
				}
            
            /*long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serviceLocator.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/
			
			form16Dtls.setCreatedDate(new Date());
			form16Dtls.setCreatedBy(objLoginDetails.getUser());
			form16CustomVO.setForm16Dtls(form16Dtls);
			
			String mDate;
        	String bankDtl;
        	String incomeTax;
        	int month = 4; // Starts From April
	        HrForm16TaxDeducDtls deducDtls ;
	        HrForm16BankMstDaoImpl bankMstDaoImpl = new HrForm16BankMstDaoImpl(HrForm16BankMst.class,serviceLocator.getSessionFactory());
	        
	        for(int iterate = 0; iterate < 14 ; iterate++){
	        	
	        	deducDtls = new HrForm16TaxDeducDtls();
	        	mDate = StringUtility.getParameter("M"+(iterate+1)+"Date", request);
	        	bankDtl = StringUtility.getParameter("cmbBankName"+(iterate+1), request);
	        	if(iterate < 12)
	        	{
	        		incomeTax = StringUtility.getParameter("it"+(iterate+1), request);
	        	
	        		if(incomeTax != null && !"".equals(incomeTax)){
	        			deducDtls.setIncomeTax(Double.parseDouble(incomeTax));
	        		}
	        	}
	        	//logger.info("Date "+(iterate+1) + " is " + mDate);
	        	//logger.info("bankDtl "+(iterate+1) + " is " + bankDtl);
	        	
	        		deducDtls.setDeducDtlsMonth(month);
	        	if(mDate != null && !"".equals(mDate)){
	        		Date date = StringUtility.convertStringToDate(mDate);
	        		deducDtls.setDeducDtlsDate(date);
	        	}
	        	if(bankDtl != null && !"Select".equals(bankDtl) && !"".equals(bankDtl)){
	        		HrForm16BankMst bankMst = (HrForm16BankMst) bankMstDaoImpl.read(Long.parseLong(bankDtl));
	        		deducDtls.setDeducDtlsBankId(bankMst);
	        	}
	        	
	        		deducDtls.setCreatedDate(new Date());
	        		deducDtls.setCreatedBy(objLoginDetails.getUser());
	        		list.add(deducDtls);

	        		
	        		if(month == 12){
	        			month = 0;
	        		}
	        		else if(month == 3){
	        			month = 12;
	        		}
	        		month++;
	        }
	        form16CustomVO.setList(list);
	        
	        String hrForm16Data = FileUtility.voToXmlFileByXStream(form16CustomVO);
            logger.debug("XML :" + hrForm16Data);
            //logger.info("XML :" + hrForm16Data);
            
            objectArgs.put("ajaxKey", hrForm16Data);
            logger.debug("its here 6");
            //logger.info("its here 6");
            
            retObj.setViewName("ajaxData");
            retObj.setResultValue(objectArgs);
            logger.info(" BY end of Form16VOGEN addForm16Details");
            //logger.info(" BY end of Form16VOGEN addForm16Details");
            
		}catch (Exception e) {
			logger.error("Error is: "+ e.getMessage());
		}
		return retObj;
	}
}
