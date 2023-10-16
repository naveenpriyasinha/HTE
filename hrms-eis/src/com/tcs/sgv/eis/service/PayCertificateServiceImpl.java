package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAO;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaySlipDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPayslip;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;

public class PayCertificateServiceImpl extends ServiceImpl {

	Log logger = LogFactory.getLog( getClass() );
	
	public ResultObject getPayCertificatePara(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPayCertificateParaPage");		
			 ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			 
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
            long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
            
			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
    		List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
    		List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
			List<HrEisOtherDtls> empList = otherDtlsDao.getAllOtherData();
    		
			
			for(int i = 0; i < empList.size(); i++)
			{
				logger.info("to avoid lazy initialization orgFname is "+empList.get(i).getHrEisEmpMst().getOrgEmpMst().getEmpFname());
			}
			
			objectArgs.put("monthList", monthList);
			objectArgs.put("yearList", yearList);
			objectArgs.put("empList", empList);
			
			//System.out.println("**************************"+monthList.size());
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("PayCertificatePara");
			logger.info("getPayCertificateParaPage");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...getPayCertificateParaPage");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...getPayCertificateParaPage");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...getPayCertificateParaPage");
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
	
	public ResultObject generatePayCertificate(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			int monthGiven=-1; //-1 is default for current month 
			int yearGiven=-1; //-1 is default for current year 
			long empId=-1;
			List PayCertificatedata=new ArrayList();
			List getPayCertificateLoan=new ArrayList();
			
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			if(objectArgs.get("month")!=null && objectArgs.get("year").toString()!="")
			{
			  monthGiven = Integer.parseInt(objectArgs.get("month").toString());
			  yearGiven = Integer.parseInt(objectArgs.get("year").toString());
			 logger.info("Given month and year for approve payslip is " + monthGiven + "--" + yearGiven);
			}
			if(objectArgs.get("selectedEmpId")!=null)
			{
				long orgEmpId = Long.parseLong(objectArgs.get("selectedEmpId").toString());
				
				EmpInfoDAOImpl hrEisEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				EmpDAOImpl orgEmpDao = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				
				OrgEmpMst orgEmpMst = orgEmpDao.read(orgEmpId);
				
				List<HrEisEmpMst> hrEisEmpList = hrEisEmpDao.getHrEmpFromOrgEmp(orgEmpMst);
				
				if(hrEisEmpList!=null && hrEisEmpList.size()>0)
					empId=hrEisEmpList.get(0).getEmpId();
				logger.info("selectedEmpId is " + empId);
			}
			
			PaySlipDAOImpl paySlipDAO = new PaySlipDAOImpl(HrPayPayslip.class,serv.getSessionFactory());
			int currMonth=0;
			 int currYear=0;
			 

             if(monthGiven!=-1 && yearGiven!=-1)
			 {
				 currMonth=monthGiven;
				 currYear=yearGiven;
			 }
			 else
			 {
				 Date DBDate = DBUtility.getCurrentDateFromDB();
				 
				 SimpleDateFormat sdfMM = new SimpleDateFormat("mm");
				 currMonth = Integer.parseInt(sdfMM.format(DBDate));
				 
				 SimpleDateFormat sdfYY = new SimpleDateFormat("yyyy");
				 currYear=Integer.parseInt(sdfYY.format(DBDate));
				 
				 //System.out.println("DB Month is " + currMonth + "\nCurrent year is " + currYear);				  				  	
			 }
             PayCertificatedata = paySlipDAO.getPayCertificate(currMonth,currYear,empId);
             getPayCertificateLoan = paySlipDAO.getPayCertificateLoanData(currMonth,currYear,empId);
             logger.info("PayCertificatedata***************"+PayCertificatedata);
             
             
            String BillNo="";	       
 			String desigName="";
 			String deptName="";
 			String cityName="";
 			String cardexCode="";
 			OrgDdoMst orgDdo = new OrgDdoMst();
 			PayBillDAO billDAO = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
 			
 			Object[] objArry = null;
 			List lstSignInfo = new ArrayList(); 	
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
 			CmnLocationMst locationVO=(CmnLocationMst)loginDetailsMap.get("locationVO");
 			long locationId=locationVO.getLocId();
 			lstSignInfo = billDAO.getReportSignature(locationId);
 			
 			if(lstSignInfo!=null && lstSignInfo.size()>0)
 			{
 				objArry =(Object[]) lstSignInfo.get(0);
 				desigName=objArry[0].toString();
 				deptName=objArry[1].toString();
 				cardexCode=objArry[2].toString();
 				cityName=objArry[3].toString();            		
 			}
 			             
            objectArgs.put("signDesigName", desigName);
            objectArgs.put("signDeptName", deptName);
            objectArgs.put("signCardexCode", cardexCode);
            objectArgs.put("signCityName", cityName);
            
            objectArgs.put("PayCertificatedata", PayCertificatedata);
            objectArgs.put("getPayCertificateLoan", getPayCertificateLoan);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("PayCertificatePage");
			logger.info("PayCertificatePage");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...generatePayCertificate");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...generatePayCertificate");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){
			
			logger.info("Exception Ocuures...generatePayCertificate");
			 logger.error("Error is: "+ e.getMessage());
			 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			 resultObject.setResultValue(objectArgs);
			 resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
}
