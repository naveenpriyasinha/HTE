package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.PropertyValueException;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.valueobject.BillNoCustomVO;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrPayArrearPaybill;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.PaybillBillregMpg;
import com.tcs.sgv.eis.valueobject.PaybillCustomVO;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgGradeMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;





public class PaybillParaServicempl extends ServiceImpl{
	private static final int ApproveFlag = 0;

	Log logger = LogFactory.getLog( getClass() );

	ResourceBundle payrollBundle = ResourceBundle.getBundle("resources.Payroll");
	public final int arrearbillTypeId=Integer.parseInt(payrollBundle.getString("arrearbillTypeId"));//arrear bill type id is 2500338
	public final int bill_type_id=Integer.parseInt(payrollBundle.getString("paybillTypeId"));// Paybill type id is 2500337
	public final int supplBill_type_First=Integer.parseInt(payrollBundle.getString("supplPaybillFirst"));// Supplementary Paybill-1 type id is 2500339
	public final int supplBill_type_Second=Integer.parseInt(payrollBundle.getString("supplPaybillSecond"));// Supplementary Paybill-2 type id is 2500340
	public final int supplBill_type_Third=Integer.parseInt(payrollBundle.getString("supplPaybillThird"));// Supplementary Paybill-3 type id is 2500341
	//added by ravysh for multiple month suppl. bill
	public final int supplBill_Multiple_Month=Integer.parseInt(payrollBundle.getString("multipleMonthSupplBill"));// Supplementary Paybill-3 type id is 2500341

	private long month;


	public ResultObject getPaybillParaPage(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{ 
			logger.info("Coming into the Service method getPaybillParaPage**********");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			logger.info("lng id id "+langId);

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());    		
			List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
			Collections.reverse(yearList);

			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());

			List locationIdList = payBillDAO.getconcernedDept(langId,userId);//locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);

			//List locationList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
			CmnLocationMst locMst = null;


			//added by Ankit Bhatt on 19th June 2011 for Maha Payroll
			String ddo_code=null;
			long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());

			//List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInPost(postId);
			List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
			if(ddoCodeList!=null)
			logger.info("After query execution for DDO Code " + ddoCodeList.size());

			OrgDdoMst ddoMst = null; 
			if(ddoCodeList!=null && ddoCodeList.size()>0){
				ddoMst = ddoCodeList.get(0);
			}

			if(ddoMst!=null) {
				ddo_code=ddoMst.getDdoCode();
			}
			logger.info("DDO Code is " + ddo_code);
			//ended by Ankit Bhatt


			//addedor commented by poonam for admin office
			
		/*	String flag=null;
			flag=payBillDAO.CheckDdoFieldDept(ddo_code);
			logger.info("Value of flag after Execution is"+flag);
			if(flag.equals("19"))
			{
	
				logger.info("Inside the if of admin office ");
				logger.info("Inside the if of admin office of get"+objectArgs.get("msg"));
			}*/

			//ended by poonam for admin office

		
			//Added by Mrugesh
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			long finYearId=Long.parseLong(resourceBundle.getString("finYearId"));
			//long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			List billNoList  = new ArrayList();
			if(ddo_code!=null) {
				billNoList = payBillDAO.getBillNo(finYearId,locId,ddo_code);
			}
			//List arrearList = payBillDAO.getArrearList(locId);
			List arrearList = new ArrayList();
			logger.info("The size of billNoList is---->"+billNoList.size());
			ArrayList billList = new ArrayList();    		
			for(Iterator itr=billNoList.iterator();itr.hasNext();)
			{
				HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
				Object[] row = (Object[])itr.next();
				String billNo = row[1].toString(); //dcpsDdoBillDescription = Bill Number of GAD
				String billHeadId = row[0].toString(); ////dcpsDdoBillGroupId = Bill Value (PK)
				billNoCustomVO.setBillId(billNo); 
				billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId)); 
				billList.add(billNoCustomVO);
			}
			//Ended by Mrugesh
			/*if(locationList!=null && locationList.size()!=0)
	        {
			 for(Iterator it=locationList.iterator();it.hasNext();)
			 {
				 locMst = (CmnLocationMst)it.next();	            
	             logger.info("To Avoid LazyInitialization in getPaybillParaPage" + locMst.getLocationCode() + " / " + locMst.getLocName());           
			 }
	        }*/
			java.util.Calendar calendar = java.util.Calendar.getInstance(); 
			int curYear=calendar.get(java.util.Calendar.YEAR); 
			int curMonth=calendar.get(java.util.Calendar.MONTH);
			logger.info("curYear====>"+curYear+"and month===>"+curMonth);
			long locationid=0;    		                		
			for (Iterator iter = locationIdList.iterator(); iter.hasNext();)
			{			 
				ArrayList rowList = new ArrayList();
				Object[] row = (Object[])iter.next();	
				locationid= Long.parseLong(row[0].toString());
			} 
			objectArgs.put("locationid", locationid);			
			objectArgs.put("yearList", yearList);			
			//objectArgs.put("deptList", locationList);
			objectArgs.put("billList", billList);
			objectArgs.put("curYear", curYear);
			objectArgs.put("curMonth", curMonth);
			objectArgs.put("arrearList", arrearList);
			//objectArgs.put("flag", flag);commented by poonam for vocational admin office
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("paybillPara");
			logger.info("INSERTED SUCCESSFULLY insertBankMasterDtls");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}	

	public ResultObject paybillview(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());


			CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			List locationList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
			CmnLocationMst locMst = null;



			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List billlist = payBillDAO.getBillDtls();

			ArrayList dataList = new ArrayList();
			for (Iterator iter = billlist.iterator(); iter.hasNext();)
			{			 
				ArrayList rowList = new ArrayList();
				Object[] row = (Object[])iter.next();	
				PaybillCustomVO paybillCustomVO = new PaybillCustomVO();
				String id = row[0].toString();
				String month = row[1].toString();
				String year = row[2].toString();
				String demandCode = row[3].toString();
				String budmjrhdCode = row[4].toString();
				String budsubmjrhdCode = row[5].toString();
				String budminhdCode = row[6].toString();
				String budsubhdCode = row[7].toString();
				logger.info("Bill dtls"+ rowList);
				paybillCustomVO.setBillId(Long.parseLong(id));
				paybillCustomVO.setMonth(month);
				paybillCustomVO.setYear(year);
				paybillCustomVO.setDemandCode(demandCode);
				paybillCustomVO.setBudmjrhdCode(budmjrhdCode);
				paybillCustomVO.setBudsubmjrhdCode(budsubmjrhdCode);
				paybillCustomVO.setBudminhdCode(budminhdCode);
				paybillCustomVO.setBudsubhdCode(budsubhdCode);
				dataList.add(paybillCustomVO);   
				logger.info("Bill dtls"+ dataList);
			} 
			objectArgs.put("dataList", dataList);			

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("PaybillViewList");
			logger.info("INSERTED SUCCESSFULLY insertBankMasterDtls");
		}
		catch(NullPointerException ne)
		{
			logger.info("Null Pointer Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ ne.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		catch(PropertyValueException pe)
		{
			logger.info("PropertyValueException Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ pe.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");					
		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}	

	public ResultObject getPaybillParaDemandNo(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String location_code = voToService.get("loc_code").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));
			List demandNoList =new ArrayList();
			int month = 0;
			int Year=0;
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				logger.info("****************finYrDate*************"+finYrDate);
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				demandNoList=payBillDAO.getDemandNoByLocId(location_code, cmnLanguageMst.getLangShortName(),finYrMst.getFinYearId());
			}
			else
				demandNoList=payBillDAO.getDemandNoByLocId(location_code, cmnLanguageMst.getLangShortName(),finYrId);


			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = demandNoList.iterator(); iter.hasNext();)
			{			 
				Object[] row = (Object[])iter.next();
				//String demand_code = sgvaBuddemandMst.getDemandCode();		  
				String demand_code = row[1].toString();	  
				//logger.info("demand_code Name in service iterator " + demand_code);
				propertyList.append("<demand-mapping>");   	
				propertyList.append("<demand-code>").append("<![CDATA[").append(demand_code).append("]]>").append("</demand-code>");                            
				propertyList.append("</demand-mapping>");
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Demand no list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	public ResultObject getPaybillParaGrades(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaGrades");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			OrgGradeMstDaoImpl gradeDAO = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());            
			List gradeList = gradeDAO.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);

			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = gradeList.iterator(); iter.hasNext();)
			{			 
				OrgGradeMst orgGradeMst = (OrgGradeMst)iter.next();		  
				//logger.info("Grade Id in service iterator " + orgGradeMst.getGradeId());
				propertyList.append("<grade-mapping>");   	
				propertyList.append("<grade-id>").append(orgGradeMst.getGradeId()).append("</grade-id>");
				propertyList.append("<grade-desc>").append("<![CDATA[").append(orgGradeMst.getGradeDesc()).append("]]>").append("</grade-desc>");
				propertyList.append("</grade-mapping>");
			}
			Map result = new HashMap();
			String gradeNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Grade list key in service is "  + gradeNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", gradeNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	public ResultObject getPaybillParaMonths(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			long year_id = Long.parseLong(voToService.get("year_id").toString());

			CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List monthList = lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);


			StringBuffer propertyList = new StringBuffer();   		       		       	   		    	
			logger.info("Year Id in service iterator " + year_id);
			int currMonth = new Date().getMonth()+1;
			logger.info("currMonthcurrMonthcurrMonthcurrMonthcurrMonthcurrMonth"+currMonth);
			if(year_id == new Date().getYear()+1900)
			{
				if(monthList!=null && monthList.size()>0)
				{
					for(Iterator monthIt=monthList.iterator();monthIt.hasNext();)
					{
						CmnLookupMst lookupMst = (CmnLookupMst)monthIt.next();
						if(Integer.parseInt(lookupMst.getLookupShortName())<= currMonth)
						{
							propertyList.append("<month-mapping>");
							propertyList.append("<month-value>").append("<![CDATA[").append(lookupMst.getLookupShortName()).append("]]>").append("</month-value>");
							propertyList.append("<month-desc>").append("<![CDATA[").append(lookupMst.getLookupDesc()).append("]]>").append("</month-desc>");
							propertyList.append("</month-mapping>");
						}
					}
				}
			}
			else if(year_id < new Date().getYear()+1900)
			{
				if(monthList!=null && monthList.size()>0)
				{
					for(Iterator monthIt=monthList.iterator();monthIt.hasNext();)
					{
						CmnLookupMst lookupMst = (CmnLookupMst)monthIt.next();    			
						propertyList.append("<month-mapping>");
						propertyList.append("<month-value>").append("<![CDATA[").append(lookupMst.getLookupShortName()).append("]]>").append("</month-value>");
						propertyList.append("<month-desc>").append("<![CDATA[").append(lookupMst.getLookupDesc()).append("]]>").append("</month-desc>");
						propertyList.append("</month-mapping>");		
					}
				}
			}

			String monthNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Year Id list key in service is "  + monthNameIdStr);            
			objectArgs.put("ajaxKey", monthNameIdStr);               
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");                          
		}
		catch(Exception e){

			logger.info("Exception Ocuures...getPaybillParaMonths");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	public ResultObject getPaybillParaMajorHead(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String demand_no = voToService.get("demand_no").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));
			int month=0;
			int Year=0;
			List mjrHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				logger.info("the value of the finYRId is ::"+finYrMst.getFinYearId());
				mjrHeadList = payBillDAO.getMjrHeadByDemandNo(demand_no, cmnLanguageMst.getLangShortName(),finYrMst.getFinYearId());
			}
			else
			{
				logger.info("in else in 468");
				mjrHeadList = payBillDAO.getMjrHeadByDemandNoandFin(demand_no, cmnLanguageMst.getLangShortName(),finYrId);
			}

			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = mjrHeadList.iterator(); iter.hasNext();)
			{			 
				//Object[] row = (Object[])iter.next();		 
				String mjrHead_code = iter.next().toString();
				//logger.info("Major Head Name in service iterator " + mjrHead_code);
				propertyList.append("<mjrHead-mapping>");   	
				propertyList.append("<mjrHead-code>").append("<![CDATA[").append(mjrHead_code).append("]]>").append("</mjrHead-code>");                            
				propertyList.append("</mjrHead-mapping>");
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Major Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	public ResultObject getPaybillParaSubMajorHead(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String demand_no = voToService.get("demand_no").toString();
			String mjrHeadCode = voToService.get("mjrHead_Code").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));
			int month=0;
			int Year=0;
			List subMjrHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				subMjrHeadList = payBillDAO.getSubMjrHeadByMjrHead(demand_no, cmnLanguageMst.getLangShortName(),mjrHeadCode,finYrMst.getFinYearId());
			}
			else
				subMjrHeadList = payBillDAO.getSubMjrHeadByMjrHeadandFin(demand_no, cmnLanguageMst.getLangShortName(),mjrHeadCode,finYrId);



			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = subMjrHeadList.iterator(); iter.hasNext();)
			{			 
				//Object[] row = (Object[])iter.next();		 
				String subMjrHead_code = iter.next().toString();
				//logger.info("Major Head Name in service iterator " + subMjrHead_code);
				propertyList.append("<subMjrHead-mapping>");   	
				propertyList.append("<subMjrHead-code>").append("<![CDATA[").append(subMjrHead_code).append("]]>").append("</subMjrHead-code>");                            
				propertyList.append("</subMjrHead-mapping>");
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Sub Major Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	public ResultObject getPaybillParaMinorHead(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String demand_no = voToService.get("demand_no").toString();
			String mjrHeadCode = voToService.get("mjrHead_Code").toString();
			String subMjrHeadCode = voToService.get("subMjrHead_Code").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));
			int month=0;
			int Year=0;
			List minorHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				minorHeadList = payBillDAO.getMnrHeadByMjrHead(demand_no,mjrHeadCode,subMjrHeadCode,cmnLanguageMst.getLangShortName(),finYrMst.getFinYearId());
			}
			else
				minorHeadList = payBillDAO.getMnrHeadByMjrHeadandFin(demand_no,mjrHeadCode,subMjrHeadCode,cmnLanguageMst.getLangShortName(),finYrId);




			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = minorHeadList.iterator(); iter.hasNext();)
			{			 
				//Object[] row = (Object[])iter.next();		 
				String mnrHead_code = iter.next().toString();
				//logger.info("minorHeadList Head Name in service iterator " + mnrHead_code);
				propertyList.append("<mnrHead-mapping>");   	
				propertyList.append("<mnrHead-code>").append("<![CDATA[").append(mnrHead_code).append("]]>").append("</mnrHead-code>");                            
				propertyList.append("</mnrHead-mapping>");
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Minor Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	public ResultObject getPaybillParaSubHead(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String demand_no = voToService.get("demand_no").toString();
			String mjrHeadCode = voToService.get("mjrHead_Code").toString();
			String subMjrHeadCode = voToService.get("subMjrHead_Code").toString(); 
			String subMnrHeadCode = voToService.get("mnrHead_code").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));

			int month=0;
			int Year=0;
			List subHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				subHeadList = payBillDAO.getSubHeadByMnrHead(demand_no,mjrHeadCode,subMjrHeadCode,subMnrHeadCode,cmnLanguageMst.getLangShortName

						(),finYrMst.getFinYearId());
			}
			else
				subHeadList = payBillDAO.getSubHeadByMnrHeadandFin(demand_no,mjrHeadCode,subMjrHeadCode,subMnrHeadCode,cmnLanguageMst.getLangShortName(),finYrId);



			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = subHeadList.iterator(); iter.hasNext();)
			{				 
				Object[] row = (Object[])iter.next();		 
				String subHead_id = row[0].toString();
				String subHead_code = row[1].toString();
				String subHead_name = row[2].toString();

				//logger.info("minorHeadList Head Name in service iterator " + subHead_id);
				//logger.info("minorHeadList Head Name in service iterator " + subHead_code);
				//logger.info("minorHeadList Head Name in service iterator " + subHead_name);

				propertyList.append("<subHead-mapping>");   	
				propertyList.append("<subHead-Id>").append("<![CDATA[").append(subHead_id).append("]]>").append("</subHead-Id>");
				propertyList.append("<subHead-code>").append("<![CDATA[").append(subHead_code).append("]]>").append("</subHead-code>");                            
				propertyList.append("<subHead-name>").append("<![CDATA[").append(subHead_name).append("]]>").append("</subHead-name>");
				propertyList.append("</subHead-mapping>");

			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Sub Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	public ResultObject getPaybillParaDetailHead(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");

			String mjrHeadCode = voToService.get("mjrHead_Code").toString();
			String subMjrHeadCode = voToService.get("subMjrHead_Code").toString(); 
			String mnrHeadCode = voToService.get("mnrHead_code").toString();
			String subHeadCode = voToService.get("subHead_code").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));
			logger.info("the finYrID is ::"+finYrId);
			int month=0;int Year=0;
			List detailHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				detailHeadList = payBillDAO.getDetailHeadByMnrHead(mjrHeadCode,subMjrHeadCode,mnrHeadCode,subHeadCode,cmnLanguageMst.getLangShortName

						(),finYrMst.getFinYearId());
			}
			else
				detailHeadList = payBillDAO.getDetailHeadByMnrHeadandFin(mjrHeadCode,subMjrHeadCode,mnrHeadCode,subHeadCode,cmnLanguageMst.getLangShortName(),finYrId);




			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = detailHeadList.iterator(); iter.hasNext();)
			{			 
				//Object[] row = (Object[])iter.next();		 
				String detailHead_code =iter.next().toString();
				//logger.info("minorHeadList Head Name in service iterator " + detailHead_code);
				propertyList.append("<DtlHead-mapping>");   	
				propertyList.append("<DtlHead-code>").append("<![CDATA[").append(detailHead_code).append("]]>").append("</DtlHead-code>");                            
				propertyList.append("</DtlHead-mapping>");
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Detail Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	//Added by Paurav to get designations on the bases of grades selected
	public ResultObject getPaybillParaSelectedValues(Map objectArgs) 
	{
		String months[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			long trnBillNo = Long.parseLong(objectArgs.get("trnBillNo").toString());




			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());

			//changed by manoj for arrear outer intergration
			//List PaybillBillregMpgList = payBillDAO.getBillNoFromTrnNo(trnBillNo);
			List PaybillBillregMpgList = payBillDAO.getBillFromTrnNo(trnBillNo);
			//end by manoj for arrear outer integration

			PaybillBillregMpg PaybillBillregMpgObj=null;
			long payBillNo=0;
			long arrearBillNo = 0;
			if(PaybillBillregMpgList.size() > 0)
			{
				PaybillBillregMpgObj  = (PaybillBillregMpg)PaybillBillregMpgList.get(0);
				if(PaybillBillregMpgObj.getHrPayPaybill()!=0 && 
						(PaybillBillregMpgObj.getBillTypeId().getLookupId() == bill_type_id || 
								PaybillBillregMpgObj.getBillTypeId().getLookupId() == supplBill_type_First || 
								PaybillBillregMpgObj.getBillTypeId().getLookupId() == supplBill_type_Second || 
								PaybillBillregMpgObj.getBillTypeId().getLookupId() == supplBill_type_Third ||
								PaybillBillregMpgObj.getBillTypeId().getLookupId() == supplBill_Multiple_Month ))//For paybill
								payBillNo = PaybillBillregMpgObj.getHrPayPaybill();

				if(PaybillBillregMpgObj.getHrPayPaybill()!=0 && PaybillBillregMpgObj.getBillTypeId().getLookupId() == arrearbillTypeId)//For arrear bill
				{
					arrearBillNo=PaybillBillregMpgObj.getHrPayPaybill();
					objectArgs.put("arrearBillNo", arrearBillNo);
				}

			}
			logger.info("Paybill ID from TRNBILLNO is " + payBillNo +" arrearBillNo "+arrearBillNo);              

			List headList = payBillDAO.getSelectedHead(payBillNo,arrearBillNo);

			List monthList = payBillDAO.getSelectedMonthYear(payBillNo,arrearBillNo);
			logger.info("Mionth List size is "+monthList.size());




			String monthSel = "";
			int monthNo = 0;
			int yearSel = 0;
			if(monthList!=null && monthList.size()>0)
			{
				HrPayPaybill Paybill = new HrPayPaybill();
				PaybillHeadMpg phm = new PaybillHeadMpg();
				HrPayArrearPaybill arrearBill = new HrPayArrearPaybill();

				if(arrearBillNo!=0)
				{
					//arrearBill = (HrPayArrearPaybill)monthList.get(0);
					//monthNo = (int)arrearBill.getMonth();
					//monthSel = months[(int)arrearBill.getMonth()-1];
					//yearSel = (int)arrearBill.getYear();
					phm = (PaybillHeadMpg)monthList.get(0);
					monthNo = (int)phm.getMonth();
					monthSel = months[(int)phm.getMonth()-1];
					yearSel = (int)phm.getYear();
				}
				else
				{
					/*Paybill = (HrPayPaybill)monthList.get(0);
            		monthNo = (int)Paybill.getMonth();
            		monthSel = months[(int)Paybill.getMonth()-1];
            		yearSel = (int)Paybill.getYear();*/
					// TODO 
					//above code commented by rahul Date:6-11-08
					//added by rahul
					phm = (PaybillHeadMpg)monthList.get(0);
					monthNo = (int)phm.getMonth();
					monthSel = months[(int)phm.getMonth()-1];
					yearSel = (int)phm.getYear();
					//ended by rahul

				}





			}

			String bpnCode="";
			String demandNo = null;
			String mjrHead = null;
			String subMjrHead = null;
			String mnrHead = null;
			String subHdCode=null;
			//Added by Mrugesh
			String subHeadDesc="";
			//Ended
			for(int k=0;k<headList.size();k++)		    		     
			{
				//logger.info("Selected HeadList " + headList.get(0));
				Object[] row = (Object[])headList.get(k);

				bpnCode = row[2].toString();
				demandNo = row[3].toString();
				mjrHead = row[4].toString();
				subMjrHead = row[5].toString();
				mnrHead = row[6].toString();
				subHdCode = row[0].toString();
				//Added by Mrugesh
				subHeadDesc = row[1].toString();
				//Ended by Mrugesh
			}

			//Added by Mrugesh
			List mjrHeadDescList = payBillDAO.getSelectedMjrHeadDesc(mjrHead);
			List subMjrHeadDescList = payBillDAO.getSelectedSubMjrHeadDesc(subMjrHead);
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Calendar.YEAR, yearSel);
			cal2.set(Calendar.MONTH, monthNo-1);
			java.util.Date finYrDate = cal2.getTime();
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
			logger.info("mnrHead"+mnrHead);
			logger.info("demandNo"+demandNo);
			logger.info("mjrHead"+mjrHead);
			logger.info("finYrMst.getFinYearId()"+finYrMst.getFinYearId());

			List mnrHeadDescList = payBillDAO.getSelectedMinHeadDesc(mnrHead,demandNo,mjrHead,finYrMst.getFinYearId());
			List demandDescList = payBillDAO.getSelectedDemandDesc(demandNo);
			logger.info("The size of headDescList is--->"+mjrHeadDescList.size());
			String mjrHeadDesc="";
			String subMjrHeadDesc="";
			String mnrHeadDesc="";
			String demandDesc="";
			String billCtrlNo="";
			String blCtrlNo="";

			for(int s=0;s<mjrHeadDescList.size();s++)
			{
				Object descObj = (Object)mjrHeadDescList.get(s);
				mjrHeadDesc= descObj.toString();
			}

			for(int x=0;x<subMjrHeadDescList.size();x++)
			{
				Object descObj = (Object)subMjrHeadDescList.get(x);
				subMjrHeadDesc= descObj.toString();
			}

			for(int s=0;s<mnrHeadDescList.size();s++)
			{
				Object descObj = (Object)mnrHeadDescList.get(s);
				mnrHeadDesc= descObj.toString();
			}

			for(int s=0;s<demandDescList.size();s++)
			{
				Object descObj = (Object)demandDescList.get(s);
				demandDesc= descObj.toString();
			}
			if(payBillDAO.getBillCtrlNoFromTrnNo(trnBillNo)!=null || payBillDAO.getBillCtrlNoFromTrnNo(trnBillNo)!="")
			{
				billCtrlNo = payBillDAO.getBillCtrlNoFromTrnNo(trnBillNo);
				logger.info("The bill contral no is----->"+billCtrlNo);
				StringTokenizer st=new StringTokenizer(billCtrlNo,"()");
				while(st.hasMoreTokens())
					blCtrlNo=st.nextToken();
			}
			//Ended by Mrugesh
			String deptName = "";
			List selDeptList = payBillDAO.getSelectedDept(bpnCode);
			for(int k=0;k<selDeptList.size();k++)		    		     
			{
				//logger.info("Selected HeadList " + selDeptList.get(0));
				deptName= selDeptList.get(k).toString();

			}

			logger.info("All selected heads are " + bpnCode + " " + demandNo + " " + mjrHead + " " + subHdCode);
			logger.info("All selected department " + deptName);

			objectArgs.put("demandNo", demandNo);
			objectArgs.put("mjrHead",mjrHead);
			objectArgs.put("subMjrHead",subMjrHead);
			objectArgs.put("mnrHead",mnrHead);
			objectArgs.put("subHeadCode",subHdCode);
			objectArgs.put("deptName",deptName);
			//Added by Mrugesh
			objectArgs.put("subHeadDesc", subHeadDesc);
			objectArgs.put("mjrHeadDesc",mjrHeadDesc);
			objectArgs.put("subMjrHeadDesc", subMjrHeadDesc);
			objectArgs.put("mnrHeadDesc", mnrHeadDesc);
			objectArgs.put("demandDesc", demandDesc);
			objectArgs.put("billCtrlNo", blCtrlNo);
			//Ended by Mrugesh

			objectArgs.put("monthSel", monthSel); 
			objectArgs.put("monthNo", monthNo);
			objectArgs.put("yearSel", yearSel);

			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);

		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}
		return resultObject;
	}


	public ResultObject getDesignationsByGrade(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			//this method shud be nalyzed again as designation can be in more than on ecklassesw
			logger.info("Inside PaybillParaServiceImpl in getDesignationsByGrade method");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String gradeId = (String)voToService.get("gradeId");

			//logger.info("Grade Id from Request is " + gradeId);
			OrgGradeMstDaoImpl gradeDAO = new OrgGradeMstDaoImpl(OrgGradeMst.class,serv.getSessionFactory());            
			//List gradeList = gradeDAO.getListByColumnAndValue("cmnLanguageMst", cmnLanguageMst);
			OrgGradeMst gradeObj = gradeDAO.read(new Long(gradeId));

			//logger.info("We have to fetch Designations for " + gradeObj.getGradeDesc());
			GenericDaoHibernateImpl genDAO = new GenericDaoHibernateImpl(HrEisGdMpg.class);
			genDAO.setSessionFactory(serv.getSessionFactory());

			List<HrEisGdMpg> mappingList = genDAO.getListByColumnAndValue("OrgGradeMst", gradeObj);
			//logger.info("No of Designations for selected grades are " + mappingList.size());

			StringBuffer desigBuffer = new StringBuffer();
			if(mappingList!=null)
			{	
				for(int i =0; i<mappingList.size();i++)
				{
					HrEisGdMpg gdMpg = mappingList.get(i);
					OrgDesignationMst desig = gdMpg.getOrgDesignationMst();

					String desigCode = desig.getDsgnCode()+"";
					String desigShrtName = desig.getDsgnShrtName();

					desigBuffer.append("<designation>");
					desigBuffer.append("<desig-code>").append("<![CDATA[").append(desigCode).append("]]>").append("</desig-code>");
					desigBuffer.append("<desig-name>").append("<![CDATA[").append(desigShrtName).append("( ").append(gdMpg.getOrgGradeMst().getGradeName()).append(")").append("]]>").append("</desig-name>");
					desigBuffer.append("</designation>");
				}
			}
			String designationXML  = desigBuffer.toString();

			String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", designationXML).toString();
			objectArgs.put("ajaxKey", resultAjaxXml);

			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception in PaybillParaServiceImpl in getDesignationsByGrade method " + e);
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	//Ended By Paurav


	//Added by Mrugesh

	public ResultObject getAllDataFromSubHeadId(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{

			logger.info("Inside PaybillParaServiceImpl in getAllDataFromSubHeadId method");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String billHeadId = (String)voToService.get("subHeadCode");
			logger.info("the billHeadId is------------>>>>>***"+billHeadId);
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			List subHeadCodeList=payBillDAO.getAllDataFromBillSubHeadId(Long.parseLong(billHeadId));

			logger.info("subHeadCodeList"+subHeadCodeList);
			logger.info("subHeadCodeList"+subHeadCodeList.toString());

			String dsgnId="";
			String classId="";
			List dsgnShrtName=new ArrayList();
			List classShrtName=new ArrayList();
			String finYrId = null;


			int month = Integer.parseInt(voToService.get("month").toString());
			int year = Integer.parseInt(voToService.get("year").toString());
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());

			logger.info("the month is------------>>>>>***"+month);
			logger.info("the year is------------>>>>>***"+year);
			logger.info("the locId is------------>>>>>***"+locId);

			//
			// To check if the previous month paybill is generated or yet not , and if generated then is it approved or not.
			int previousMonth = month - 1;
			int  previousYear  = year ; 
			if(previousMonth == 0){
				previousMonth = 12;
				previousYear = year -1; 
			}
			/*To check previous month bill generation*/
			String[] monthinWord={"","January","February","March","April","May","June","July","August","September","October","November","December"};
			int[] fiscalMonth={0,9,10,11,0,1,2,3,4,5,6,7,8};
			int totalBillMonth=payBillDAO.calculateTotalBill(month,year,Long.parseLong(billHeadId),locId);
			logger.info(totalBillMonth+"=total bill generated && Bill generated should be= " + fiscalMonth[month]);
			boolean ifContinue=true;
			Calendar cal = Calendar.getInstance(); 
			int Fmonth = cal.get(Calendar.MONTH) + 1;        
			int fiscalYear = cal.get(Calendar.YEAR);
			if(Fmonth<4)
				fiscalYear=fiscalYear-1;
			int tYear=year;
			if(month<4)
				tYear=tYear-1;

			logger.info(tYear+"tYear fiscalYear" + fiscalYear);
			if(fiscalMonth[month]>totalBillMonth && fiscalYear==tYear && false)
			{
				int[] createdDate=payBillDAO.getBillCreationDate(Long.parseLong(billHeadId),locId);
				//logger.info("first month of bill generation " + createdDate[2]);

				if(month<4)
					fiscalYear=fiscalYear-1;
				if( createdDate[0]<4)
					createdDate[1]= createdDate[1]-1;
				String msg="April";
				if(fiscalYear== createdDate[1])
				{
					msg=(createdDate[2]==0)?monthinWord[createdDate[0]]:monthinWord[createdDate[2]];
					if(createdDate[2]<=0)
						totalBillMonth+=fiscalMonth[(createdDate[0])];
					else if(createdDate[2]>0)
						totalBillMonth+=fiscalMonth[(createdDate[2])];
					else
						totalBillMonth=createdDate[2];

				}

				logger.info(fiscalMonth[month]+" *********vivek total month is******* " +totalBillMonth);


				logger.info(createdDate[1]+" *********vivek total month is******* " +fiscalYear);
				if(fiscalMonth[month]>totalBillMonth ){
					ifContinue=false;
					StringBuffer sb = new StringBuffer();
					logger.info(totalBillMonth+"=====vivek total month is " + fiscalMonth[month]);
					sb.append("<approveFlagParent>");
					sb.append("<approveFlagMessage>").append("<![CDATA[").append(String.valueOf("Please Generate Paybill from "+msg+" to current Month")).append("]]>").append("</approveFlagMessage>");
					sb.append("</approveFlagParent>");
					String subHeadXML  = sb.toString();
					String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
					objectArgs.put("ajaxKey", resultAjaxXml);
				}

			}
			if(ifContinue){
				//Long approveFlag = payBillDAO.checkPreviousPaybill(previousMonth,previousYear,Long.parseLong(billHeadId),locId);//commented by poonam for 1 paybill validation
				Long approveFlag = payBillDAO.checkPreviousPaybill(month,year,Long.parseLong(billHeadId),locId);
				//-1 = bill not generated
				//0  = bill generated but not yet approved
				//1  = bill has been approved
				//
				if(approveFlag.longValue() == 1 || approveFlag.longValue() == -1){
					Boolean isGenerated = payBillDAO.checkPaybill(month,year,Long.parseLong(billHeadId),locId);




					logger.info("the isGenerated is------------>>>>>***"+isGenerated);


					if(isGenerated.equals(false))
					{
						logger.info("the isGenerated is------------>>>>>***"+isGenerated);

						logger.info("the else part is------------>>>>>***");
						ArrayList allSubHeadList = new ArrayList();   
						StringBuffer sb = new StringBuffer();
						for(Iterator itr=subHeadCodeList.iterator();itr.hasNext();)
						{
							BillNoCustomVO billNoCustomVO = new BillNoCustomVO();
							Object[] row = (Object[])itr.next();
							//Object[] row = (Object[])allSubHeadList.get(0);    			 
							long schemeId = row[0]!=null ? Long.valueOf(row[0].toString()):0;
							String schemeCode = row[1].toString();
							String schemeName = row[2].toString();    	                		
							String demandNo = row[3]!=null ?row[3].toString():"0";
							String mjrHead = row[4].toString();
							String subMjrHead = row[5].toString();
							String mnrHead = row[6].toString();
							String subHead = row[8].toString();
							String charged = row[9].toString();
							String planned = row[10].toString();
							String subSchemeCode = row[11]!=null ?row[11].toString():"-";//Added by saurabh
							
							billNoCustomVO.setSchemeId(schemeId);
							billNoCustomVO.setPlanned(planned);
							billNoCustomVO.setCharged(charged);
							billNoCustomVO.setDemandNo(demandNo); 
							billNoCustomVO.setMjrHead(Long.parseLong(mjrHead));
							billNoCustomVO.setMnrHead(Long.parseLong(mnrHead));
							billNoCustomVO.setSubHead(Long.parseLong(subHead));
							billNoCustomVO.setSubMjrHead(Long.parseLong(subMjrHead));
							allSubHeadList.add(billNoCustomVO);




							sb.append("<subHeadCode>");
							sb.append("<subhead>").append("<![CDATA[").append(subHead).append("]]>").append("</subhead>");
							sb.append("<demandNo>").append("<![CDATA[").append(demandNo).append("]]>").append("</demandNo>");
							sb.append("<mjrHead>").append("<![CDATA[").append(mjrHead).append("]]>").append("</mjrHead>");
							sb.append("<subMjrHead>").append("<![CDATA[").append(subMjrHead).append("]]>").append("</subMjrHead>");
							sb.append("<mnrHead>").append("<![CDATA[").append(mnrHead).append("]]>").append("</mnrHead>");
							sb.append("<dsgnId>").append("<![CDATA[").append(dsgnId).append("]]>").append("</dsgnId>");
							sb.append("<classId>").append("<![CDATA[").append(classId).append("]]>").append("</classId>");
							sb.append("<dsgnShrtName>").append("<![CDATA[").append(dsgnShrtName).append("]]>").append("</dsgnShrtName>");
							sb.append("<classShrtName>").append("<![CDATA[").append(classShrtName).append("]]>").append("</classShrtName>");
							sb.append("<schemeId>").append("<![CDATA[").append(schemeId).append("]]>").append("</schemeId>");
							sb.append("<schemeCode>").append("<![CDATA[").append(schemeCode).append("]]>").append("</schemeCode>");
							sb.append("<schemeName>").append("<![CDATA[").append(schemeName).append("]]>").append("</schemeName>"); 
							
							sb.append("<charged>").append("<![CDATA[").append(charged).append("]]>").append("</charged>");
							sb.append("<planned>").append("<![CDATA[").append(planned).append("]]>").append("</planned>");
							sb.append("<subSchemeCode>").append("<![CDATA[").append(subSchemeCode).append("]]>").append("</subSchemeCode>");  //Added by saurabh
							sb.append("</subHeadCode>");
						}
						String subHeadXML  = sb.toString();

						logger.info("at last of  PaybillParaServiceImpl in getAllDataFromSubHeadId method " + subHeadXML.toString());		

						String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
						objectArgs.put("ajaxKey", resultAjaxXml);


					}

					else
					{
						StringBuffer sb = new StringBuffer();
						sb.append("<isGenerated>");
						sb.append("<subhead>").append("<![CDATA[").append(isGenerated).append("]]>").append("</subhead>");
						sb.append("</isGenerated>");

						String subHeadXML  = sb.toString();
						logger.info("at last of  PaybillParaServiceImpl in getAllDataFromSubHeadId method " + subHeadXML.toString());		

						String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
						objectArgs.put("ajaxKey", resultAjaxXml);
					}
				}else if(approveFlag.longValue() == 0)//added by poonam for 1
					{
						//commented by poonam for 1 paybill validation
					/*StringBuffer sb = new StringBuffer();
					sb.append("<approveFlagParent>");
					sb.append("<approveFlagMessage>").append("<![CDATA[").append(String.valueOf("Previous month bill has not been approved yet. Please approve previous month bill first. ")).append("]]>").append("</approveFlagMessage>");
					sb.append("</approveFlagParent>");

					String subHeadXML  = sb.toString();

					String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
					objectArgs.put("ajaxKey", resultAjaxXml);*/
					//added by poonam for 1 paybill validation
					  String monthDtls= payBillDAO.checkOtherPaybill(month,year,Long.parseLong(billHeadId),locId);
	       	          String[] dtlsArr=monthDtls.split("#");
	       		  StringBuffer sb = new StringBuffer();
	       		  sb.append("<approveFlagParent>");
	       		  sb.append("<approveFlagMessage>").append("<![CDATA[").append(String.valueOf("Paybill for year "+dtlsArr[0]+" and month "+dtlsArr[1]+" has not been approved yet. Please approve the bill first. ")).append("]]>").append("</approveFlagMessage>");
	       		  sb.append("</approveFlagParent>");

	       		  String subHeadXML  = sb.toString();

	       		  String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
	       		  objectArgs.put("ajaxKey", resultAjaxXml);
					
					
					
					
					

				}
			}






			//objectArgs.put("ajaxKey", idNumberStrAjax);      
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");  

		}
		catch(Exception e){

			logger.info("Exception in PaybillParaServiceImpl in getAllDataFromSubHeadId method " + e);
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
	//Ended by Mrugesh

	public ResultObject checkPriviewPaybillStatus(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{

			logger.info("Inside PaybillParaServiceImpl in getAllDataFromSubHeadId method");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String billHeadId = (String)voToService.get("subHeadCode");
			int month = Integer.parseInt(voToService.get("month").toString());
			int year = Integer.parseInt(voToService.get("year").toString());
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());

			String billTypeLookupId = (String)voToService.get("billTypeLookupId");

			logger.info("the billHeadId is------------>>>>>***"+billHeadId+"month and year==>"+month+year);
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Boolean isGenerated = payBillDAO.checkPaybill(month,year,Long.parseLong(billHeadId),locId,billTypeLookupId);
			logger.info("isGenerated===="+isGenerated);

			//added by ravysh to find number of supplimentary bills generated
			long lSupplBillNo = 0;
			if(isGenerated)
			{
				lSupplBillNo=payBillDAO.getSupplBillNo(month,year,Long.parseLong(billHeadId),locId);

			}

			ArrayList allSubHeadList = new ArrayList();   
			StringBuffer sb = new StringBuffer();



			sb.append("<subHeadCode>");
			sb.append("<subhead>").append(isGenerated).append("</subhead>");
			sb.append("<lSupplBillNo>").append(lSupplBillNo).append("</lSupplBillNo>");
			sb.append("</subHeadCode>");


			String subHeadXML  = sb.toString();



			String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
			objectArgs.put("ajaxKey", resultAjaxXml);
			//objectArgs.put("ajaxKey", idNumberStrAjax);      
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");  
			logger.info(resultAjaxXml);


		}
		catch(Exception e){

			logger.info("Exception in PaybillParaServiceImpl in getAllDataFromSubHeadId method " + e);
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
	//Ended by Mrugesh

	//Added by Abhilash

	public ResultObject checkOuterPaybillStatus(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{

			logger.info("Inside PaybillParaServiceImpl in getAllDataFromSubHeadId method");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			logger.info("voToServicevoToService" + voToService);
			String billHeadId = (String)voToService.get("subHeadCode");
			logger.info("billHeadIdbillHeadId" + billHeadId);

			int month = Integer.parseInt(voToService.get("month").toString());
			logger.info("monthmonth" + month);
			int year = Integer.parseInt(voToService.get("year").toString());
			logger.info("yearyearyear" + year);
			long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
			logger.info("locIdlocId" + locId);

			String billTypeLookupId = (String)voToService.get("billTypeLookupId");
			logger.info("the billHeadId is------------>>>>>***"+billHeadId+"month and year==>"+month+year);
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());

			// Boolean notGenerated = payBillDAO.checkOuterPaybill(month,year,Long.parseLong(billHeadId),locId,billTypeLookupId);
			Boolean GeneratedInPabill = payBillDAO.checkPaybill(month,year,Long.parseLong(billHeadId),locId,billTypeLookupId);
			logger.info("notGenerated===="+GeneratedInPabill);

			//  Boolean isGenerated = payBillDAO.checkPaybill(month,year,Long.parseLong(billHeadId),locId,billTypeLookupId);
			//  logger.info("isGenerated===="+isGenerated);

			Boolean IsGenerated = payBillDAO.checkPaybillForOuter(month, year, Long.parseLong(billHeadId), locId);
			logger.info("IsGenerated===="+IsGenerated);

			long lSupplBillNo = 0;
			long checkdup=0;
			if(GeneratedInPabill)
			{
				lSupplBillNo=payBillDAO.getSupplBillNoForOuter(month,year,Long.parseLong(billHeadId),locId);
				logger.info("lSupplBillNolSupplBillNo" + lSupplBillNo);
			}
			/*    else if(IsGenerated)
         {
        	 lSupplBillNo=payBillDAO.getSupplBillNo(month,year,Long.parseLong(billHeadId),locId);
         }*/
			else
			{
				lSupplBillNo=payBillDAO.getSupplBillNo(month,year,Long.parseLong(billHeadId),locId);
				logger.info("lSupplBillNolSupplBillNolSupplBillNo" + lSupplBillNo);
			}

			ArrayList allSubHeadList = new ArrayList();
			StringBuffer sb = new StringBuffer();



			sb.append("<subHeadCode>");
			sb.append("<subhead>").append(GeneratedInPabill).append("</subhead>");
			sb.append("<subhead>").append(IsGenerated).append("</subhead>");
			//sb.append("<subhead>").append(IsGenerated).append("</subhead>");
			sb.append("<lSupplBillNo>").append(lSupplBillNo).append("</lSupplBillNo>");
			sb.append("</subHeadCode>");


			String subHeadXML  = sb.toString();



			String resultAjaxXml = new AjaxXmlBuilder().addItem("ajax_key", subHeadXML).toString();
			objectArgs.put("ajaxKey", resultAjaxXml);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");  
			logger.info(resultAjaxXml);

		}
		catch(Exception e)
		{

			logger.info("Exception in PaybillParaServiceImpl in getAllDataFromSubHeadId method " + e);
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}
	//Ended by Abhilash


	//Added by Abhilash For Scheme

	public ResultObject getPaybillParaSubHeadForScheme(Map objectArgs) 
	{
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String demand_no = voToService.get("demand_no").toString();
			String mjrHeadCode = voToService.get("mjrHead_Code").toString();
			String subMjrHeadCode = voToService.get("subMjrHead_Code").toString(); 
			String subMnrHeadCode = voToService.get("mnrHead_code").toString();
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));

			int month=0;
			int Year=0;
			List subHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				subHeadList = payBillDAO.getSubHeadByMnrHead(demand_no,mjrHeadCode,subMjrHeadCode,subMnrHeadCode,cmnLanguageMst.getLangShortName

						(),finYrMst.getFinYearId());
			}
			else
				subHeadList = payBillDAO.getSubHeadByMnrHeadandFin1(demand_no,mjrHeadCode,subMjrHeadCode,subMnrHeadCode,cmnLanguageMst.getLangShortName(),finYrId);



			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = subHeadList.iterator(); iter.hasNext();)
			{				 
				Object[] row = (Object[])iter.next();		 
				String subHead_id = row[0].toString();
				String subHead_code = row[1].toString();
				String subHead_name = row[2].toString();

				//logger.info("minorHeadList Head Name in service iterator " + subHead_id);
				//logger.info("minorHeadList Head Name in service iterator " + subHead_code);
				//logger.info("minorHeadList Head Name in service iterator " + subHead_name);

				propertyList.append("<subHead-mapping>");   	
				propertyList.append("<subHead-Id>").append("<![CDATA[").append(subHead_id).append("]]>").append("</subHead-Id>");
				propertyList.append("<subHead-code>").append("<![CDATA[").append(subHead_code).append("]]>").append("</subHead-code>");                            
				propertyList.append("<subHead-name>").append("<![CDATA[").append(subHead_name).append("]]>").append("</subHead-name>");
				propertyList.append("</subHead-mapping>");

			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Sub Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}


	//Added by Abhilash For Scheme 
	public ResultObject getHeadStructureFromSubHead(Map objectArgs) {
		// TODO Auto-generated method stub
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			logger.info("Coming into the Service method getPaybillParaPage");		
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");

			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory()); 
			CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.read(langId);   

			Map voToService = (Map)objectArgs.get("voToServiceMap");

			String subHead = voToService.get("sub_Head").toString();
			logger.info("sub_Headsub_Head" + subHead);
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			FinancialYearDAOImpl finYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class,serv.getSessionFactory());
			Date currDate = Calendar.getInstance().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  	      
			int finYrId = finYearDAOImpl.getFinYearId(sdf.format(currDate));
			logger.info("the finYrID is ::"+finYrId);
			int month=0;int Year=0;
			List detailHeadList=new ArrayList();
			if(voToService.get("month")!=null&&voToService.get("Year")!=null&&!voToService.get("month").toString().equals("")&&!voToService.get("Year").toString

					().equals("")&&!voToService.get("month").toString().equals("-1")&&!voToService.get("Year").toString().equals("-1"))
			{
				month = Integer.parseInt((voToService.get("month")!=null?voToService.get("month"):"0").toString());
				Year = Integer.parseInt((voToService.get("Year")!=null?voToService.get("Year"):"0").toString());
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, Year);
				cal2.set(Calendar.MONTH, month-1);

				java.util.Date finYrDate = cal2.getTime();
				SgvcFinYearMst finYrMst = payBillDAO.getFinYrInfo(finYrDate, langId);
				detailHeadList = payBillDAO.getHeadChargable(subHead,cmnLanguageMst.getLangShortName(),finYrId);
			}
			else
				detailHeadList = payBillDAO.getHeadChargable(subHead,cmnLanguageMst.getLangShortName(),finYrId);

			//logger.info("detailHeadListdetailHeadList" + detailHeadList.size());


			StringBuffer propertyList = new StringBuffer();
			for (Iterator iter = detailHeadList.iterator(); iter.hasNext();)
			{			 
				//Object[] row = (Object[])iter.next();		 
				String detailHead_code =iter.next().toString();
				//logger.info("minorHeadList Head Name in service iterator " + detailHead_code);
				propertyList.append("<DtlHead-mapping>");   	
				propertyList.append("<DtlHead-code>").append("<![CDATA[").append(detailHead_code).append("]]>").append("</DtlHead-code>");                            
				propertyList.append("</DtlHead-mapping>");
			}
			Map result = new HashMap();
			String stateNameIdStr = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
			logger.info("Detail Head list key in service is "  + stateNameIdStr);
			result=objectArgs;
			result.put("ajaxKey", stateNameIdStr);

			resultObject.setResultValue(result);
			resultObject.setViewName("ajaxData");  


		}
		catch(Exception e){

			logger.info("Exception Ocuures...insertBankMasterDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");			
		}
		return resultObject;
	}

	//Ended by Abhilash For Scheme
	/*public ResultObject checkEmpCount(Map objectArgs) throws Exception
	{

		logger.info("Entering into checkEmpCount");
		ResultObject objRes = new ResultObject(-1);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serviceLocator.getSessionFactory());

		//----:DDO Code Generation :Start:----\\
		
		String  billNo=(StringUtility.getParameter("billNo", request));
		logger.info("billNo*******"+billNo);
		Long count=payBillDAO.checkEmpCount(billNo);
		logger.info("count.toString()*******"+count.toString());
		String lStrCodeResult=new AjaxXmlBuilder().addItem("count", count.toString()).toString();
		logger.info("Entering into retirveLevel3DDOCodeList of ZpAdminOfficeServiceImpl" + lStrCodeResult);
		objectArgs.put("ajaxKey", lStrCodeResult);
		//objectArgs.put("ajaxKey1", AjaxResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;
	}*/
	
	public ResultObject getalreadybill(Map objectArgs)
	{
		logger.info("in service of the getalreadybill");
		long billNo=0l,billmonth=0l,billyear=0l;
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");	
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			
			PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			billNo=(Long.parseLong(StringUtility.getParameter("billid",request)));
			billmonth=(Long.parseLong(StringUtility.getParameter("billmonth",request)));
			billyear=(Long.parseLong(StringUtility.getParameter("billyear",request)));
			logger.info("billNo: "+ billNo);
			String counte=payBillDAO.getalreadybillsforemployee(billNo,billmonth,billyear);
			logger.info("the count is"+counte);
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",counte).toString();
			logger.info("the string after ajax builder"+lStrResult);
			objectArgs.put("ajaxKey", lStrResult);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
		}
		catch(Exception e)
		{
			logger.info("Error in the function");
		}
		return resultObject;
	}
	
	
	public ResultObject checkEmpCount(Map objectArgs) throws Exception
	{

		logger.info("Entering into checkEmpCount");
		ResultObject objRes = new ResultObject(-1);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serviceLocator.getSessionFactory());

		//----:DDO Code Generation :Start:----\\
		
		String  billNo=(StringUtility.getParameter("billNo", request));
		Long banKdeatilsFlag=payBillDAO.checkbankDetails(billNo);
		
		Long countEmp=payBillDAO.checkEmpCount(billNo);
		String count=countEmp.toString();
		if(banKdeatilsFlag==0){
			count="NA";
		}
		logger.info("count.toString()*******"+count.toString());
		String lStrCodeResult=new AjaxXmlBuilder().addItem("count", count.toString()).toString();
		logger.info("Entering into retirveLevel3DDOCodeList of ZpAdminOfficeServiceImpl" + lStrCodeResult);
		objectArgs.put("ajaxKey", lStrCodeResult);
		//objectArgs.put("ajaxKey1", AjaxResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;
	}
	
	
	//added by saurabh for validation of head of account code
	public ResultObject checkHeadCode(Map objectArgs) throws Exception
	{

		logger.info("Entering into checkHeadCode");
		ResultObject objRes = new ResultObject(-1);
		ServiceLocator serviceLocator = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		PayBillDAOImpl payBillDAO= new PayBillDAOImpl(HrPayPaybill.class,serviceLocator.getSessionFactory());

		//----:DDO Code Generation :Start:----\\
		
		String  billNo=(StringUtility.getParameter("billNo", request));
		Long banKdeatilsFlag1=payBillDAO.checkHeadAccount(billNo);
		
		Long countEmp=payBillDAO.checkEmpCount(billNo);
		String count1=countEmp.toString();
		if(banKdeatilsFlag1==0){
			count1="NA";
		}
		logger.info("count.toString()*******"+count1.toString());
		String lStrCodeResult1=new AjaxXmlBuilder().addItem("count", count1.toString()).toString();
		logger.info("Entering into checkHeadCode" + lStrCodeResult1);
		objectArgs.put("ajaxKey", lStrCodeResult1);
		//objectArgs.put("ajaxKey1", AjaxResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;
	}
	
	
	
	

}

