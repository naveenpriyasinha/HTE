package com.tcs.sgv.hr.payincrement.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDao;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.idgenerator.delegate.IDGenerateDelegate;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.FileUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.BatchITGpfDetailsUpdateDaoImpl;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.ScaleMasterDAO;
import com.tcs.sgv.eis.dao.ScaleMasterDAOImpl;
import com.tcs.sgv.eis.employeeInfo.dao.GeneralEmpInfoDaoImpl;
import com.tcs.sgv.eis.employeeInfo.valueobject.GeneralEmpInfoVO;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.service.OtherDetailService;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.PayIncrementCustomVO;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.hr.payfixation.dao.PayFixationDao;
import com.tcs.sgv.hr.payfixation.dao.PayFixationDaoImpl;
import com.tcs.sgv.hr.payincrement.dao.EmpPayIncrDAO;
import com.tcs.sgv.hr.payincrement.dao.EmpPayIncrDAOImpl;
import com.tcs.sgv.hr.payincrement.valueobject.EmpPayIncrVOCus;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

public class EmpPayIncServiceImpl extends ServiceImpl implements EmpPayIncService{
	Log logger = LogFactory.getLog(getClass());
	long billId = 0;
	String monthStr = null;
	String yearStr = null;
	String incrtype=null;
	int flag=0;
	
	
	public ResultObject showMonthAndYear(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		try
		{
			logger.info("===INSIDE IN showMonthAndYear===");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
		    CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
	   		String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
		    long locationId = cmnLocationMst.getLocId();
		   
			Calendar currntMonDate = Calendar.getInstance();
				int currentMonth = currntMonDate.get(Calendar.MONTH);
				int currentYear = currntMonDate.get(Calendar.YEAR);
				

			int month = objectArgs.get("selMonth") != null ? Integer.parseInt(objectArgs.get("selMonth").toString()) : 13;
			int year = objectArgs.get("selYear") != null ?  Integer.parseInt(objectArgs.get("selYear").toString()) : 0;
			logger.info("Current Month is:-"+currentMonth);
			 
			if(month!=-1)
				currentMonth=month;
			if(year!=0)
				currentYear=year;
			
			Calendar currntMonStartDate = Calendar.getInstance();
			Calendar currntMonEndDate = Calendar.getInstance();
			
			int lastDate=30;
			int firstDate=1;
			
			logger.info("currentMonth: "+currentMonth);
			logger.info("currentYear: "+currentYear);
			
				currntMonStartDate.set(Calendar.YEAR, currentYear);
					logger.info("currntMonStartDate.year" +currntMonStartDate.get(Calendar.YEAR));
				currntMonStartDate.set(Calendar.MONTH, currentMonth);
					logger.info("currntMonStartDate.MONTH" +currntMonStartDate.get(Calendar.MONTH));
				lastDate = currntMonStartDate.getActualMaximum(Calendar.DAY_OF_MONTH);
					logger.info("lastDate: " +lastDate);
				
				currntMonStartDate.set(currentYear, currentMonth, firstDate);
				currntMonEndDate.set(currentYear, currentMonth, lastDate);
			
			objectArgs.put("month", currentMonth);
			objectArgs.put("year", currentYear);

			
			logger.info("=============currntMonStartDate=========="+ currntMonStartDate);
			logger.info("=============currntMonEndDate=========="+ currntMonEndDate);
			
			EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			EmpPayIncrDAO empPayIncrDAO = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
			ScaleMasterDAO scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
			
			List EmpPayIncrList = new ArrayList();
			List EmpPayIncrList1;

			 logger.info("Bill no is: " +billId +" month is: " +month);
			if(billId!=0 && month!=13)
			{
				 logger.info("in if loop____________________________________________________>");
				Integer mnthStr= Integer.parseInt(monthStr.toString());
				mnthStr++;
				EmpPayIncrList1 = empPayIncrDAO.getEmpPayIncrListByMonthAndYearAndBillNo(mnthStr.toString()+'/'+yearStr,mnthStr.toString()+'/'+yearStr, billId,locationCode,langId);
				logger.info("The size of list is---->"+EmpPayIncrList1.size());
				Date payIncDate = null;
				Date nextIncrDate = null;
				Long userId = 0l;
				//synchronized(EmpPayIncrList){
				String billNo = empPayIncrDAO.getBillNoFromBillId(billId);
	   		    objectArgs.put("billNo",billNo);
				for(int s=0; s < EmpPayIncrList1.size(); s++)
	            {			 
	   		   
	   		  	 GenericDaoHibernateImpl payFix = new GenericDaoHibernateImpl(HrPayfixMst.class);
	   		     payFix.setSessionFactory(serv.getSessionFactory());
	   		     
	   		     HrPayfixMst hrpay  =  (HrPayfixMst)EmpPayIncrList1.get(s);;	 
	   		     
	   		    EmpPayIncrList.add(hrpay);
	   		    
	            }
				
				}
			else if(month!=-1){
				 logger.info("in else if loop____________________________________________________>");
				EmpPayIncrList = empPayIncrDAO.getEmpPayIncrListByMonthAndYear(currntMonStartDate.getTime(), currntMonEndDate.getTime(),locationId,langId);
			}
			else
			{
				 logger.info("in else loop____________________________________________________>");
				EmpPayIncrList = empPayIncrDAO.getEmpPayIncrListByMonthAndYear(currntMonStartDate.getTime(),currntMonEndDate.getTime(),locationId,langId);
			}
			 logger.info("EmpPayIncrList____________________________________________________>"+EmpPayIncrList);
			List<EmpPayIncrVOCus> AllEmpPayIncrDtlsList = new ArrayList<EmpPayIncrVOCus>();
			Iterator iterator = EmpPayIncrList.iterator();
			
			HrPayfixMst hrPayfixMst=new HrPayfixMst();
			OrgEmpMst orgEmpMst=new OrgEmpMst();
			HrEisScaleMst hrEisScaleMst=new HrEisScaleMst(); 
			
			while(iterator.hasNext())
			{
				
				EmpPayIncrVOCus empPayIncrVOCus = new EmpPayIncrVOCus();
				hrPayfixMst=null;
				orgEmpMst=null;
				hrEisScaleMst=null;
				
				String EmpName="";
				String PayScale="";
				
				hrPayfixMst = (HrPayfixMst)iterator.next();
				if(hrPayfixMst!=null && hrPayfixMst.getUserId()!=null)
					orgEmpMst=empDAO.getEmpFromUser(hrPayfixMst.getUserId(), langId);
				
				if(orgEmpMst!=null)
					EmpName=orgEmpMst.getEmpFname()+" "+orgEmpMst.getEmpMname()+" "+orgEmpMst.getEmpLname();
				
				
				if(hrEisScaleMst!=null)
					PayScale = String.valueOf(hrEisScaleMst.getScaleStartAmt())+"-"+String.valueOf(hrEisScaleMst.getScaleIncrAmt())+"-"+String.valueOf(hrEisScaleMst.getScaleEndAmt());
				
				
				if(hrPayfixMst!=null)
				{
					logger.info("hrPayfixMst____________________________________________________>"+hrPayfixMst);
					empPayIncrVOCus.setLWP(0);
					
					if(hrPayfixMst.getUserId()!=null && hrPayfixMst.getUserId().getUserId()!=0)
						empPayIncrVOCus.setUserId(hrPayfixMst.getUserId().getUserId());
					if(hrPayfixMst.getPayfixId()!=0)
						empPayIncrVOCus.setPayFixId(hrPayfixMst.getPayfixId());
					else
					if(hrPayfixMst.getPayFixDate()!=null)
						empPayIncrVOCus.setPayFixDate(hrPayfixMst.getPayFixDate());
					if(hrPayfixMst.getNxtIncrDate()!=null)
						empPayIncrVOCus.setNextIncrDate(hrPayfixMst.getNxtIncrDate());
					
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(hrPayfixMst.getNxtIncrDate());
					calendar.add(Calendar.DAY_OF_MONTH,new Integer(""+empPayIncrVOCus.getLWP()));
					
					if (calendar.get(Calendar.DAY_OF_MONTH) >= 15)
						calendar.add(Calendar.MONTH,1);
					
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					
					
					logger.info("Effective Date==="+calendar.getTime());
					
					empPayIncrVOCus.setEffectiveDate(calendar.getTime());
					
				}
				if(!EmpName.equals(""))
					empPayIncrVOCus.setEmpName(EmpName);
				if(!PayScale.equals(""))
					empPayIncrVOCus.setCurrentPayScale(PayScale);
				empPayIncrVOCus.setASE("null");
				
				Date lastIncDate = empPayIncrDAO.getLastIncrDateByUserId(hrPayfixMst.getUserId().getUserId());
				if(lastIncDate!=null)
					empPayIncrVOCus.setLastIncrDate(lastIncDate);
				logger.info("last Date==="+lastIncDate);
				AllEmpPayIncrDtlsList.add(empPayIncrVOCus);
			}
			objectArgs.put("AllEmpPayIncrDtlsList", AllEmpPayIncrDtlsList);
			
			List<CmnLookupMst> arMonth = this.getLookupData(serv, langId, "Month");
			
			Calendar calendar = Calendar.getInstance();
			int endYr = calendar.get(Calendar.YEAR);			
			logger.info("The End Year is:-"+endYr);
			List arYear = new ArrayList();
			calendar.set(Calendar.YEAR, 1950);			
			arYear.add(calendar.get(Calendar.YEAR));
			for(int startYr = calendar.get(Calendar.YEAR); startYr < endYr; startYr++)
			{
				calendar.add(calendar.YEAR, 1);
				arYear.add(calendar.get(calendar.YEAR));
			}
			
			logger.info("Current Month is:-"+currentMonth);
			objectArgs.put("arYear", arYear);
			objectArgs.put("arMonth", arMonth);
			
			 HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
			
			 
	            BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
	           List<HrPayBillHeadMpg> hrPayBillHeadMpgList = billHeadMpgDAOImpl.getAllData(locationCode,langId);
	           //logger.info("The size of bill list from pay increment is---->"+hrPayBillHeadMpgList.size());
	           objectArgs.put("billNoList", hrPayBillHeadMpgList);
	           List<HrPayfixMst> fixList = billHeadMpgDAOImpl.getAllDataForPayFixation(locationId,langId);
	          // logger.info("The size of bill list from hr pay fix MST---->"+fixList.size()+"Location"+locationId+"LangId"+langId);
	           String incrementOrderNo="";
	           String incrementOrderDate="";
	           String status ="";
	           long countOfEmployees=0;
	           List empCustomVOList = new ArrayList();
	           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	           SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

	           for(Iterator itr=fixList.iterator();itr.hasNext();)
	           {
	        	   Object[] row = (Object[])itr.next();

	        	   if(row[0]!=null)
	        	   {
	        		   incrementOrderNo = row[0].toString();
	        	   }

	        	   if(row[1]!=null)
	        	   {
	        		   incrementOrderDate= row[1].toString();
	        	   }
	        	   if(row[2]!=null)
	        	   {
	        		   status = row[2].toString();
	        	   }
	        	   if(row[3]!=null)
	        	   {
	        		   countOfEmployees = Long.parseLong(row[3].toString());
	        	   }

	        	   PayIncrementCustomVO customVo = new PayIncrementCustomVO();

	        	   customVo.setIncrementOrderNo(incrementOrderNo);
	        	   if(!incrementOrderDate.equals("") && !incrementOrderDate.equals(null))
	        		   customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
	        	   customVo.setStatus(status);
	        	   customVo.setCountOfEmployees(countOfEmployees);
	        	   empCustomVOList.add(customVo);


	           }


	           objectArgs.put("fixList", empCustomVOList);
	           objectArgs.put("fixListsize", empCustomVOList.size());
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmpPayIncrList");
		}
		catch (Exception e) {
			logger.info("There is some Error:-");
			e.printStackTrace();
		}
		
		
		return resultObject;
	}
	//Added by jigna on 2-7-2007 here not considered hr_payfix_mst table
	public ResultObject showMonthAndYearWithoutPayfix(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		
		try
		{
			logger.info("===INSIDE IN showMonthAndYear===");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
			//Modify By Urvin Shah
		    CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
	   		String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
		    long locationId = cmnLocationMst.getLocId();
		    //End.
		       
		       
			/* 
			 * By default the data should be only for current month and year 
			 */
			Calendar currntMonDate = Calendar.getInstance();
				int currentMonth = currntMonDate.get(Calendar.MONTH);
				int currentYear = currntMonDate.get(Calendar.YEAR);
				

			int month = objectArgs.get("selMonth") != null ? Integer.parseInt(objectArgs.get("selMonth").toString()) : 13;
			int year = objectArgs.get("selYear") != null ?  Integer.parseInt(objectArgs.get("selYear").toString()) : 0;
			logger.info("Current Month is:-"+currentMonth);
			 
			//Added by Urvin shah
			if(month!=-1)
				currentMonth=month;
			if(year!=0)
				currentYear=year;
			// End.
			
			Calendar currntMonStartDate = Calendar.getInstance();
			Calendar currntMonEndDate = Calendar.getInstance();
			
			int lastDate=30;
			int firstDate=1;
			
			logger.info("currentMonth: "+currentMonth);
			logger.info("currentYear: "+currentYear);
			
				currntMonStartDate.set(Calendar.YEAR, currentYear);
					logger.info("currntMonStartDate.year" +currntMonStartDate.get(Calendar.YEAR));
				currntMonStartDate.set(Calendar.MONTH, currentMonth);
					logger.info("currntMonStartDate.MONTH" +currntMonStartDate.get(Calendar.MONTH));
				lastDate = currntMonStartDate.getActualMaximum(Calendar.DAY_OF_MONTH);
					logger.info("lastDate: " +lastDate);
				
				currntMonStartDate.set(currentYear, currentMonth, firstDate);
				currntMonEndDate.set(currentYear, currentMonth, lastDate);
			
			objectArgs.put("month", currentMonth);
			objectArgs.put("year", currentYear);

			
			logger.info("=============currntMonStartDate=========="+ currntMonStartDate);
			logger.info("=============currntMonEndDate=========="+ currntMonEndDate);
			
			EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			EmpPayIncrDAO empPayIncrDAO = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
			ScaleMasterDAO scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
			
			List EmpPayIncrList = new ArrayList();
			List EmpPayIncrList1;
			
			 logger.info("Bill no is: " +billId +" month is: " +month);
			if(billId!=0 && month!=13)
			{
				 logger.info("in if loop____________________________________________________>");
   				Integer mnthStr= Integer.parseInt(monthStr.toString());
				mnthStr++;
				EmpPayIncrList = empPayIncrDAO.getEmpPayIncrListByMonthAndYearAndBillNoWithoutPayfix(mnthStr.toString()+'/'+yearStr,mnthStr.toString()+'/'+yearStr, billId,locationCode,langId);
				logger.info("The size of list is---->"+EmpPayIncrList.size());
				Date payIncDate = null;
				Date nextIncrDate = null;
				Long userId = 0l;
				String billNo = empPayIncrDAO.getBillNoFromBillId(billId);
	   		    objectArgs.put("billNo",billNo);
				
				
				}
			else if(month!=-1){
				 logger.info("in else if loop____________________________________________________>");
				EmpPayIncrList = empPayIncrDAO.getEmpPayIncrListByMonthAndYear(currntMonStartDate.getTime(), currntMonEndDate.getTime(),locationId,langId);
			}
			else
			{
				 logger.info("in else loop____________________________________________________>");
				EmpPayIncrList = empPayIncrDAO.getEmpPayIncrListByMonthAndYear(currntMonStartDate.getTime(),currntMonEndDate.getTime(),locationId,langId);
			}
			 logger.info("EmpPayIncrList____________________________________________________>"+EmpPayIncrList);
			List<EmpPayIncrVOCus> AllEmpPayIncrDtlsList = new ArrayList<EmpPayIncrVOCus>();
			Iterator iterator = EmpPayIncrList.iterator();
			
			HrPayfixMst hrPayfixMst=new HrPayfixMst();
			OrgEmpMst orgEmpMst=new OrgEmpMst();
			HrEisScaleMst hrEisScaleMst=new HrEisScaleMst(); 
			
			while(iterator.hasNext())
			{
				
				EmpPayIncrVOCus empPayIncrVOCus = new EmpPayIncrVOCus();
			
   				 Object[] row = (Object[])iterator.next();	
		   		if(EmpPayIncrList.size()>0) //hrPayfixMst!=null
				{
					logger.info("EmpPayIncrList____________________________________________________>"+EmpPayIncrList);
					empPayIncrVOCus.setLWP(0);
					logger.info("class *********************"+row[1]);
					if(row[1].toString()!=null && Long.parseLong(row[1].toString())!=0)
						empPayIncrVOCus.setUserId(Long.parseLong(row[1].toString()));
						empPayIncrVOCus.setPayFixId(0);
					if(row[2].toString()!=null)
						empPayIncrVOCus.setCurrentPay(Long.parseLong(row[2].toString()));
					else
						empPayIncrVOCus.setCurrentPay(((Long)(row[3])).longValue());
					 Date DBDate = DBUtility.getCurrentDateFromDB();
						empPayIncrVOCus.setPayFixDate(DBDate);
					
					if(row[3].toString()!=null)
            						empPayIncrVOCus.setRevPay((long)((Double)(row[3])).doubleValue());
					
						empPayIncrVOCus.setNextIncrDate(DBDate);
					
					Calendar calendar = new GregorianCalendar();
    					calendar.setTime(DBDate);
					calendar.add(Calendar.DAY_OF_MONTH,new Integer(""+empPayIncrVOCus.getLWP()));
					
				
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					
					
					logger.info("Effective Date==="+calendar.getTime());
					
					empPayIncrVOCus.setEffectiveDate(calendar.getTime());
				}
				if(!row[0].toString().equals(""))
					empPayIncrVOCus.setEmpName(row[0].toString());
				if(!row[5].toString().equals(""))
					empPayIncrVOCus.setCurrentPayScale(row[5].toString());
				empPayIncrVOCus.setScaleId(Long.parseLong(row[4].toString()));
				empPayIncrVOCus.setASE("null");
				
				Date lastIncDate = empPayIncrDAO.getLastIncrDateByUserId(((Long)(row[1])).longValue());
				if(lastIncDate!=null)
					empPayIncrVOCus.setLastIncrDate(lastIncDate);
				logger.info("last Date==="+lastIncDate);
				AllEmpPayIncrDtlsList.add(empPayIncrVOCus);
			}
			objectArgs.put("AllEmpPayIncrDtlsList", AllEmpPayIncrDtlsList);
			
			List<CmnLookupMst> arMonth = this.getLookupData(serv, langId, "Month");
			
			
			
			
			Calendar calendar = Calendar.getInstance();
			int endYr = calendar.get(Calendar.YEAR);			
			logger.info("The End Year is:-"+endYr);
			List arYear = new ArrayList();
			calendar.set(Calendar.YEAR, 1950);			
			arYear.add(calendar.get(Calendar.YEAR));
			for(int startYr = calendar.get(Calendar.YEAR); startYr < endYr; startYr++)
			{
				calendar.add(calendar.YEAR, 1);
				arYear.add(calendar.get(calendar.YEAR));
			}
			
			logger.info("Current Month is:-"+currentMonth);
			objectArgs.put("arYear", arYear);
			objectArgs.put("arMonth", arMonth);
			 HrPayBillHeadMpg billHeadMpg = new HrPayBillHeadMpg();
	            BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
	           List<HrPayBillHeadMpg> hrPayBillHeadMpgList = billHeadMpgDAOImpl.getAllData(locationCode,langId);
	           logger.info("The size of bill list from pay increment is---->"+hrPayBillHeadMpgList.size());
	           objectArgs.put("billNoList", hrPayBillHeadMpgList);
	           
	          /* List<HrPayfixMst> fixList = billHeadMpgDAOImpl.getAllDataForPayFixation();
	           objectArgs.put("fixList", fixList);
	           objectArgs.put("fixListsize", fixList.size());
*/	           
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmpPayIncrList");
		}
		catch (Exception e) {
			logger.info("There is some Error:-");
			e.printStackTrace();
		}
		
		
		return resultObject;
	}
	public List<CmnLookupMst> getLookupData(ServiceLocator serv, long langId, String strLookupName)
	{
		CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());

		List<CmnLookupMst> arLookupData = cmnLookupMstDAO.getAllChildrenByLookUpNameAndLang(strLookupName,langId);
		
		
		return arLookupData;
	}
	
	public ResultObject getEmpIncrList(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		if(StringUtility.getParameter("billId",request)!= null && !StringUtility.getParameter("billId",request).equals(""))
		{
			billId = Long.parseLong(StringUtility.getParameter("billId",request));
		}
		
		monthStr = StringUtility.getParameter("month",request)!= null && !StringUtility.getParameter("month",request).equals("")?StringUtility.getParameter("month",request):" ";
		yearStr =  StringUtility.getParameter("year",request)!= null && !StringUtility.getParameter("year",request).equals("")?StringUtility.getParameter("year",request):" ";
		incrtype=StringUtility.getParameter("selIncrtype",request)!= null && !StringUtility.getParameter("selIncrtype",request).equals("")?StringUtility.getParameter("year",request):" ";
		logger.info("The billId is----->"+billId + "=====" +monthStr +"====" + yearStr +"====" + incrtype);
		
		
		if(billId!=0 && !incrtype.equals("") && !incrtype.equals("1"))
		{
			objectArgs.put("incrtype", incrtype);
		    resultObject = this.showMonthAndYearWithoutPayfix(objectArgs);
		}
		else
		{
			resultObject = this.showMonthAndYear(objectArgs);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultObject;
	}
	
	public ResultObject showEmpCurrentPayDtls(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try
		{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
			long SelectedUserId = objectArgs.get("UserId") != null && !objectArgs.get("UserId").equals("") ? Long.parseLong(objectArgs.get("UserId").toString()) : 0;
			long PayFixId = objectArgs.get("PayFixId") != null && !objectArgs.get("PayFixId").equals("") ? Long.parseLong(objectArgs.get("PayFixId").toString()) : 0;
			long lwp = objectArgs.get("lwp") != null && !objectArgs.get("lwp").equals("") ? Long.parseLong(objectArgs.get("lwp").toString()) : 0;
			Date dueDate = StringUtility.convertStringToDate(objectArgs.get("duedate").toString());
			Date effDate = StringUtility.convertStringToDate(objectArgs.get("effDate").toString());
			
			EmpPayIncrVOCus empPayIncrVOCus = new EmpPayIncrVOCus();
			EmpPayIncrDAO empPayIncrDAO = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
			ScaleMasterDAO scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
			
			HrPayfixMst hrPayfixMstForCurrent = empPayIncrDAO.getEmpCurrentPayDtlsByUserId(SelectedUserId);
			
			String hrPayfixMstPayScale="";
			if(hrPayfixMstForCurrent!=null)
			{
				HrEisScaleMst hrEisScaleMst = null;
				
				if(hrEisScaleMst!=null)
				{
					hrPayfixMstPayScale=String.valueOf(hrEisScaleMst.getScaleStartAmt())+"-"+String.valueOf(hrEisScaleMst.getScaleIncrAmt())+"-"+String.valueOf(hrEisScaleMst.getScaleEndAmt());
						empPayIncrVOCus.setCurrentPay(hrEisScaleMst.getScaleEndAmt());
					logger.info("hrPayfixMstPayScale==="+hrPayfixMstPayScale);
				}
				logger.info("hrPayfixMstForCurrent===="+hrPayfixMstForCurrent);
			}
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dueDate);
			calendar.add(Calendar.YEAR, 1);
			empPayIncrVOCus.setLWP(lwp);
			empPayIncrVOCus.setEffectiveDate(effDate);
			empPayIncrVOCus.setNextIncrDate(calendar.getTime());
			
			objectArgs.put("hrPayfixMstPayScale", hrPayfixMstPayScale);
			objectArgs.put("hrPayfixMstForCurrent", hrPayfixMstForCurrent);
			objectArgs.put("empPayIncrVOCus", empPayIncrVOCus);

			
			List EmpAllPayDtlsLst = empPayIncrDAO.getEmployeeAllPayDtlsByUserId(SelectedUserId);
			List<String> PayFixXMLFileLst = new ArrayList<String>();
			
			if(EmpAllPayDtlsLst!=null && !EmpAllPayDtlsLst.isEmpty())
			{
				HrPayfixMst objPayfixMst = new HrPayfixMst();
				Iterator iterator = EmpAllPayDtlsLst.iterator();
				while(iterator.hasNext())
				{
					objPayfixMst = (HrPayfixMst)iterator.next();
					
					logger.info("3===="+objPayfixMst.getNxtIncrDate());
					logger.info("4===="+objPayfixMst.getPayfixId());
					logger.info("5===="+objPayfixMst.getRemarks());
					
					String tempPay = FileUtility.voToXmlFileByXStream(objPayfixMst);
					PayFixXMLFileLst.add(tempPay);
				}
				
			}
			logger.info("PayFixXMLFileLst====="+PayFixXMLFileLst.size());
			logger.info("EmpAllPayDtlsLst===="+EmpAllPayDtlsLst.size());
			
			/**   For Employee Data That is Shown At Top Of Header   **/
			if (SelectedUserId != 0)
			{	
				GeneralEmpInfoDaoImpl GemEmpinfo = new GeneralEmpInfoDaoImpl(OrgEmpMst.class,serv.getSessionFactory());
	        	GeneralEmpInfoVO EmpDetailsVO= GemEmpinfo.findByEmpIDOtherDetail(SelectedUserId,langId);
                objectArgs.put("EmpDet", EmpDetailsVO);
			}
			/**End OF Employee Data **/
			
			Date lastIncrDate = empPayIncrDAO.getLastIncrDateByUserId(SelectedUserId);
			
			objectArgs.put("lastIncrDate", lastIncrDate);
			objectArgs.put("PayFixXMLFileLst", PayFixXMLFileLst);
			objectArgs.put("EmpAllPayDtlsLst", EmpAllPayDtlsLst);
			objectArgs.put("SelectedUserId", SelectedUserId);
			
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmpNextPayIncr");
		}
		catch(Exception e){}
		return resultObject;
	}
	
	
	public ResultObject savePayIncrDtlsInDB(Map objectArgs)
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");						
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
				long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());	
				
		
				
				
				/*  Get Login user id */
				OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);	    	 
				/*End of the geting user id Code*/
				
				/*  Get The Person Post */			 			 	    	 
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
				/*End of the geting Person Post Code*/	    	 	    		    	

				/* Getting a DB ID*/								 
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/
			
				/* Getting a Loc ID Code */
				long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
				/* End of Loc ID */
				
				/* Getting a Loc ID Code */
				CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);
				/* End of Loc ID */
				
				PayFixationDao payFixationDao = new PayFixationDaoImpl(HrPayfixMst.class,serv.getSessionFactory());
				EmpPayIncrDAO empPayIncrDAO = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
				
				CmnLookupMstDAO cmnLookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
				CmnLookupMst cmnLookupMst = cmnLookupMstDAO.getLookUpVOByLookUpNameAndLang("emp_incr", langId);
				
				ScaleMasterDAO scaleMasterDAO = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
				
				List<HrPayfixMst> addedPayIncrVOList=(List<HrPayfixMst>) objectArgs.get("addedPayIncrVOList");
				List<HrPayfixMst> updatedPayIncrVOList=(List<HrPayfixMst>) objectArgs.get("updatedPayIncrVOList");
				List<HrPayfixMst> deletedPayIncrVOList=(List<HrPayfixMst>) objectArgs.get("deletedPayIncrVOList");
				List<HrPayfixMst> notModifiedPayIncrVOList=(List<HrPayfixMst>) objectArgs.get("notModifiedPayIncrVOList");
				
				long selectedUserId = !objectArgs.get("userId").toString().equals("") ? Long.parseLong(objectArgs.get("userId").toString()) : 0;
				logger.info("selectedUserId===="+selectedUserId);
				
				/** Code START For INSERT*/
				
				HrPayfixMst hrPayfixMstFromDB = new HrPayfixMst();
				HrPayfixMst hrPayfixMstToDB = new HrPayfixMst();
				HrPayfixMst hrPayfixMstFromVOGen = new HrPayfixMst();
				
				
				Date NxtIncrDate=null;
				long basicPay=0;
				long scaleId = 0;
				
				Iterator iterator = addedPayIncrVOList.iterator();
				
				while(iterator.hasNext())
				{
					hrPayfixMstFromVOGen = (HrPayfixMst)iterator.next();
					
					hrPayfixMstFromDB=payFixationDao.getPayFixListByUserIdandActiveFlag(selectedUserId, 'Y');
					
					
					if(hrPayfixMstFromDB!=null && hrPayfixMstFromDB.getNxtIncrDate()!=null)
					{
						NxtIncrDate = hrPayfixMstFromDB.getNxtIncrDate();
						Calendar calendar = new GregorianCalendar();
						calendar.setTime(NxtIncrDate);
						calendar.add(Calendar.YEAR, 1);
						NxtIncrDate = calendar.getTime();
						logger.info("NxtIncrDate======"+NxtIncrDate);
					}
					
					if(hrPayfixMstFromDB!=null && hrPayfixMstFromDB.getPayfixId()!=0)
						hrPayfixMstFromDB=(HrPayfixMst)payFixationDao.read(hrPayfixMstFromDB.getPayfixId());
					
					long preBasicPay = 0;
					HrEisScaleMst preEisScaleMst = new HrEisScaleMst();
					Date payFixDaye = null;
					if(hrPayfixMstFromDB!=null)
					{
						hrPayfixMstFromDB.setOrgPostMstUpdatedByPost(orgPostMst);
						hrPayfixMstFromDB.setOrgUserMstUpdatedBy(orgUserMst);
						hrPayfixMstFromDB.setUpdatedDate(new Date());
						payFixDaye = hrPayfixMstFromDB.getNxtIncrDate();
						payFixationDao.update(hrPayfixMstFromDB);
					}
					
					
					
					if(hrPayfixMstFromVOGen!=null)
					{
						if(selectedUserId!=0)
						{
							OrgUserMst orgUserMstSelectedUser = orgUserMstDaoImpl.read(selectedUserId);
							if(orgUserMstSelectedUser!=null)
								hrPayfixMstToDB.setUserId(orgUserMstSelectedUser);
						}
						
						long payFixId=0;
						payFixId = IDGenerateDelegate.getNextId("hr_payfix_mst", objectArgs);
						
						if(payFixId!=0)
							hrPayfixMstToDB.setPayfixId(payFixId);
						
					
						if(!hrPayfixMstFromVOGen.getRemarks().equals(""))
							hrPayfixMstToDB.setRemarks(hrPayfixMstFromVOGen.getRemarks());
						
					
						if(hrPayfixMstFromVOGen.getNxtIncrDate()!=null)
							hrPayfixMstToDB.setNxtIncrDate(hrPayfixMstFromVOGen.getNxtIncrDate());
						
						hrPayfixMstToDB.setPayFixDate(payFixDaye);
						
						hrPayfixMstToDB.setCmnDatabaseMst(cmnDatabaseMst);
						hrPayfixMstToDB.setCmnLanguageMst(cmnLanguageMst);
						hrPayfixMstToDB.setCmnLocationMst(cmnLocationMst);
						hrPayfixMstToDB.setCreatedDate(new Date());
						hrPayfixMstToDB.setOrgPostMstCreatedByPost(orgPostMst);
						hrPayfixMstToDB.setOrgUserMstCreatedBy(orgUserMst);
						empPayIncrDAO.create(hrPayfixMstToDB);
					}
					
				}
				
				/** Code END For INSERT*/
				
				/** Code START For DELETE */
				if(deletedPayIncrVOList!=null)
				{
					 Iterator litr = deletedPayIncrVOList.iterator();
					 HrPayfixMst objHrPayfixMst = null;
					 while(litr.hasNext())
					 {
						 objHrPayfixMst =(HrPayfixMst)litr.next();
						 if(objHrPayfixMst != null && objHrPayfixMst.getPayfixId()!=0)
						 {
							 HrPayfixMst readHrPayfixMst=(HrPayfixMst)payFixationDao.read(objHrPayfixMst.getPayfixId());
							 payFixationDao.delete(readHrPayfixMst);
						 }
					 }
				 }
				/** Code END For DELETE */
				
				/** Code START For UPDATE */
				
				if(updatedPayIncrVOList!=null && !updatedPayIncrVOList.isEmpty())
				{
					Iterator iterator2 = updatedPayIncrVOList.iterator();
					HrPayfixMst hrPayfixMstForUpdateFromVOGen = null;
					HrPayfixMst hrPayfixMstForUpdateToDB = null;
					while(iterator2.hasNext())
					{
						hrPayfixMstForUpdateFromVOGen = (HrPayfixMst)iterator2.next();
						
						if(hrPayfixMstForUpdateFromVOGen!=null)
						{
							if(hrPayfixMstForUpdateFromVOGen.getPayfixId()!=0)
								hrPayfixMstForUpdateToDB = (HrPayfixMst)payFixationDao.read(hrPayfixMstForUpdateFromVOGen.getPayfixId());
							if(hrPayfixMstForUpdateToDB!=null)
							{
								
								hrPayfixMstForUpdateToDB.setNxtIncrDate(hrPayfixMstForUpdateFromVOGen.getNxtIncrDate());
								hrPayfixMstForUpdateToDB.setOrgPostMstUpdatedByPost(orgPostMst);
								hrPayfixMstForUpdateToDB.setOrgUserMstUpdatedBy(orgUserMst);
								hrPayfixMstForUpdateToDB.setRemarks(hrPayfixMstForUpdateFromVOGen.getRemarks());
								hrPayfixMstForUpdateToDB.setUpdatedDate(new Date());
								
								payFixationDao.update(hrPayfixMstForUpdateToDB);
							}
						}
						
					}
				}
				
				/** Code END For UPDATE */
			
				//added by manish khunt
				
				//long empId=Long.valueOf((objectArgs.get(objectArgs).toString()));
				//logger.info("empId ::::::::::"+empId);
				long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
				OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
				
				
				
				Set set=orgUserMstNew.getOrgUserpostRlts();
				OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();
				
				Map mapForChangedRecords=objectArgs;
			
				mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
				//mapForChangedRecords.put("changedEmpId",empID);
				mapForChangedRecords.put("serviceLocator",serv);
				mapForChangedRecords.put("locId", locId);
				mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
				mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
				mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
				GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
				long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
				logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
			//ended by manish khunt
			objRes.setViewName("successfulMessage"); 	
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			}
		}	
			
		catch (Exception e) 
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			e.printStackTrace();
		}
		return objRes;
	}
	
	/**
	 * Author:-   Urvin Shah.
	 * Date:-     05-05-2008
	 * Function:- This method is for Approving or Rejecting the Increment of employees of current month. 
	 * 			  It's affecting both the hr_payfix_mst as well as updating the Basic as well as all allwances and Dedcutions.  	 
	 * @param objectArgs
	 * @return
	 */
	
	
	public ResultObject empInsertIncrement(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		int maxCounter=0;
		List lstPayfixId = new ArrayList();
		List lstEmpIncrStatus = new ArrayList();
		List lstIncrAmount = new ArrayList();
		String strIncrStatus="";
		long newPayfixId;
		HrEisScaleMst hrEisScaleMst;
		long revPayAmount;
		long prevPay;
		
		// Collection Objects which are going to pass in the OtherDetails
		List lstUser = new ArrayList();			// List of Users who's Basic Amount get revised.
		List lstRvsdPayAmt = new ArrayList();	// List of PayAmount.
		List lstNextIncrDate = new ArrayList();	// List of Next Increment Date for the Other Details.
		List lstRevisedPayScale = new ArrayList();	// List of Revised Pay Scales for the Other Details.
		//Added by Jigna Raval
		List lstCurrentPay=new ArrayList();
		List lstCurrentPayScale=new ArrayList();
		List lstUserId=new ArrayList();
		
		String strIncrTypeFlag=null;
		long billId = 0;
		String monthStr = null;
		String yearStr = null;
		String incrtype=null;
		//End by Jigna Raval
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			
			/*  Get Login user id */
			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);	    	 
			/*End of the geting user id Code*/
			
			/*  Get The Person Post */			 			 	    	 
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    
			/*End of the geting Person Post Code*/	    	 	    		    	

			/* Getting a DB ID*/								 
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			/* End og DB Id code*/
		
			/* Getting a Loc ID Code */
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);
			/* End of Loc ID */
			
			/* Getting a Loc ID Code */
			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);
			/* End of Loc ID */
			
			maxCounter = Integer.parseInt(objectArgs.get("empIncrCntr").toString());
			logger.info("The Counter Value is:-"+maxCounter);
			//hrPayfixMst = new HrPayfixMst();
			PayFixationDao payFixationDao = new PayFixationDaoImpl(HrPayfixMst.class,serv.getSessionFactory());
			
			lstPayfixId = (List)objectArgs.get("lstPayfixId");
			lstEmpIncrStatus = (List)objectArgs.get("lstEmpIncrStatus");
			lstIncrAmount = (List)objectArgs.get("lstIncrAmount");
			
			//Added by Jigna Raval
			 lstCurrentPay= (List)objectArgs.get("lstCurrentPay");
			 lstCurrentPayScale= (List)objectArgs.get("lstCurrentPayScale");
			 strIncrTypeFlag=(String.valueOf(objectArgs.get("strIncrTypeFlag")));
			 lstUserId= (List)objectArgs.get("lstUserId");
			if(objectArgs.get("billId")!=null)
				billId=(Long.parseLong(objectArgs.get("billId").toString()));
			if(objectArgs.get("monthStr")!=null)
				monthStr=objectArgs.get("monthStr").toString();
			if(objectArgs.get("yearStr")!=null)
				yearStr= objectArgs.get("yearStr").toString();
			if(objectArgs.get("incrtype")!=null)
				incrtype= objectArgs.get("incrtype").toString();
			 
			 logger.info("=================flag==strIncrTypeFlag===>"+strIncrTypeFlag);
			//End by Jigna Raval
			
			//lstRevPayScale = (List)objectArgs.get("lstRevPayScale");
			Date sysDate = new Date();
			//Date nextIncrDate = new  Date();
			//Date newIncrDate;
			IdGenerator idGen = new IdGenerator();
			int counter=0;
		if(strIncrTypeFlag!=null && !strIncrTypeFlag.equals("") && strIncrTypeFlag.equals("1"))
		{
			logger.info("_______________________in if_________strIncrTypeFlag________________>"+strIncrTypeFlag);
			for(int i=0;i<maxCounter;i++){
				logger.info("The PayFixId in service is:-"+Long.parseLong(lstPayfixId.get(i).toString()));
				HrPayfixMst hrPayfixMst = new HrPayfixMst();
				hrPayfixMst = (HrPayfixMst) payFixationDao.read(Long.parseLong(lstPayfixId.get(i).toString()));
				logger.info("The Current Status is:-"+lstEmpIncrStatus.get(i).toString());
				strIncrStatus = lstEmpIncrStatus.get(i).toString();
				logger.info("The Next Increment Date is :-"+hrPayfixMst.getNxtIncrDate());
				//logger.info("The Revised Pay is:-"+lstRevPayScale.get(0));
				
				if(strIncrStatus.equals("true")) {
					
					// Updating the Older Record by setting the status = 'N'.
					hrPayfixMst.setUpdatedDate(sysDate);
					payFixationDao.update(hrPayfixMst);
					logger.info("First Old Increment Date is:-"+hrPayfixMst.getNxtIncrDate());
					logger.info("The Record has been Updated");
					// Inserting new Record in the hr_payfix_mst.
					HrPayfixMst objHrPayfixMst = new HrPayfixMst();
					
					newPayfixId = idGen.PKGenerator("hr_payfix_mst",objectArgs);
					
					logger.info("The Newer Payfix Id is:-"+newPayfixId);
					prevPay=Long.parseLong(lstIncrAmount.get(i).toString());
					
					objHrPayfixMst.setPayfixId(newPayfixId);
					objHrPayfixMst.setCmnDatabaseMst(hrPayfixMst.getCmnDatabaseMst());
					objHrPayfixMst.setCmnLanguageMst(hrPayfixMst.getCmnLanguageMst());
					objHrPayfixMst.setPayFixDate(sysDate);
					
					// Assignment of the Revised Pay.
					
					objHrPayfixMst.setUserId(hrPayfixMst.getUserId());
					
					Date newIncrDate = new Date();
					
					logger.info("1 Old Increment Date is:-"+hrPayfixMst.getNxtIncrDate());
					newIncrDate.setDate(hrPayfixMst.getNxtIncrDate().getDate());
					newIncrDate.setMonth(hrPayfixMst.getNxtIncrDate().getMonth());
					newIncrDate.setYear(hrPayfixMst.getNxtIncrDate().getYear()+1);
					logger.info("2 Old Increment Date is:-"+hrPayfixMst.getNxtIncrDate());
					
					objHrPayfixMst.setNxtIncrDate(newIncrDate);
					logger.info("Middle Old Increment Date is:-"+hrPayfixMst.getNxtIncrDate());
					logger.info("Middle New Increment Date is:-"+objHrPayfixMst.getNxtIncrDate());
					objHrPayfixMst.setCreatedDate(sysDate);
					objHrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
					objHrPayfixMst.setOrgUserMstCreatedBy(orgUserMst);
					objHrPayfixMst.setCmnLocationMst(hrPayfixMst.getCmnLocationMst());
					payFixationDao.create(objHrPayfixMst);
					
					// Inserting in the Newer List for updating the Other Details.
					lstUser.add(counter,hrPayfixMst.getUserId());
					lstRvsdPayAmt.add(counter,prevPay);
					lstNextIncrDate.add(counter,newIncrDate);	
					counter+=1;
					logger.info("The Record has been inserted:-"+counter);	
					logger.info("Last Old Increment Date is:-"+hrPayfixMst.getNxtIncrDate());
					logger.info(" Last New Increment Date is:-"+objHrPayfixMst.getNxtIncrDate());
				
					//added by manish khunt
        			//long empId=Long.valueOf((objectArgs.get(objectArgs).toString()));
        			//logger.info("empId ::::::::::"+empId);
        				
        				//OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
        		//	long userIdFromVogen=Long.valueOf(objectArgs.get("UserId").toString());
					long userIdFromVogen=Long.valueOf(lstUser.get(i).toString());
        				OrgUserMst orgUserMstNew = orgUserMstDaoImpl.read(userIdFromVogen);	    
        				
        				Set set=orgUserMstNew.getOrgUserpostRlts();
        				OrgUserpostRlt tempObj = (OrgUserpostRlt)set.iterator().next();
        				
        			Map mapForChangedRecords=objectArgs;
        			logger.info("Setting map to objectArgs");
        			mapForChangedRecords.put("changedPostId",tempObj.getOrgPostMstByPostId().getPostId());
        			mapForChangedRecords.put("locId", locId);
        			mapForChangedRecords.put("serviceLocator",serv);
        			mapForChangedRecords.put("OrgPostMst",tempObj.getOrgPostMstByPostId());
        			mapForChangedRecords.put("OrgUserMst",orgUserMstNew);
        			mapForChangedRecords.put("cmnDatabaseMst",cmnDatabaseMst);
        			GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
        			long changedRecordPK=generateBillServiceHelper.insertChangedRecord(mapForChangedRecords);
        			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
        			logger.info("GenerateBillServiceHelper:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
        		//ended by manish khunt
					
					
				}
				else {
					Date nextIncrDate =hrPayfixMst.getNxtIncrDate(); 
					nextIncrDate.setMonth(nextIncrDate.getMonth()+1);
					hrPayfixMst.setNxtIncrDate(nextIncrDate);
					logger.info("the Next Increment Date is:-"+nextIncrDate);
					hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
					hrPayfixMst.setOrgUserMstUpdatedBy(orgUserMst);
					hrPayfixMst.setUpdatedDate(sysDate);
					payFixationDao.update(hrPayfixMst);
				}
			}
		}
		else
		{
			
			for(int i=0;i<maxCounter;i++){
			HrPayfixMst objHrPayfixMst = new HrPayfixMst();
			HrEisScaleMst hrLEisScaleMst=new HrEisScaleMst();
			
			OrgUserMst lOrgUserMst=new OrgUserMst();
			newPayfixId = idGen.PKGenerator("hr_payfix_mst",objectArgs);
			objHrPayfixMst.setPayfixId(newPayfixId);
			objHrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
			objHrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
			objHrPayfixMst.setPayFixDate(sysDate);
   			ScaleMasterDAOImpl scalemstDao = new ScaleMasterDAOImpl(HrEisScaleMst.class,serv.getSessionFactory());
   			hrLEisScaleMst=scalemstDao.read(Long.parseLong((lstCurrentPayScale.get(i).toString())));
			objHrPayfixMst.setCreatedDate(sysDate);
			objHrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
			
			lOrgUserMst.setUserId(Long.parseLong((String.valueOf(lstUserId.get(i)))));
			objHrPayfixMst.setUserId(lOrgUserMst);//this is a seeter 
			objHrPayfixMst.setOrgUserMstCreatedBy(orgUserMst);
			
			objHrPayfixMst.setCmnLocationMst(cmnLocationMst);
			
			payFixationDao.create(objHrPayfixMst);
			
			
			// Inserting in the Newer List for updating the Other Details.
			lstUser.add(counter,lOrgUserMst);
			lstRvsdPayAmt.add(counter,Long.parseLong(lstIncrAmount.get(i).toString()));
			
			
			logger.info("=============basic==in service==>"+lstIncrAmount.get(i).toString());
			logger.info("=============basic==in==>"+lstCurrentPay.get(i).toString());
			
			lstNextIncrDate.add(counter,sysDate);	
			lstRevisedPayScale.add(counter,(lstCurrentPayScale.get(i).toString()));
			counter+=1;
			}
		}
			//For Updating the Other Details and Employee's Allowances and Deductions.
			objectArgs.put("lstUser", lstUser);
			objectArgs.put("lstRvsdPayAmt", lstRvsdPayAmt);
			objectArgs.put("lstNextIncrDate", lstNextIncrDate);
			objectArgs.put("lstRevisedPayScale", lstRevisedPayScale);
			objectArgs.put("counter", counter);
			OtherDetailService otherDetailService = new OtherDetailService();					// Calling the Other Details.
        			resultObject = otherDetailService.editOtherDtlsOnPayIncrPayFixation(objectArgs);	
			//resultObject = serv.executeService("ADDRESS_VOGENERATOR", objectArgs);
        			objectArgs.put("billId", billId);
        			objectArgs.put("monthStr", monthStr);
        			objectArgs.put("yearStr", yearStr);
        			objectArgs.put("incrtype", incrtype);
        
			resultObject.setViewName("IncrementsuccessfulMessage"); 	
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
		}
		catch(Exception e){
			e.printStackTrace();
			Map result = new HashMap();
			result.put("MESSAGECODE",1007);
			resultObject.setResultValue(result);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			resultObject.setViewName("errorPage");
		}
		
		return resultObject;
	}
	
	//Added by Amish
	public ResultObject showDataForNewOrderNo(Map objectArgs)
	{

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		logger.info("getDataFromIncreamentOrderNo****enter");
		try
		{

			logger.info("showDataForNewOrderNo order number");
			int a=1;
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			//temp code
			Enumeration enum1 = request.getParameterNames();
			while(enum1.hasMoreElements()){
				String name = enum1.nextElement().toString();
				String value = request.getParameter(name);
				logger.info("Request Name and value " + name + " " + value);
			}
			//ended

			long payfixId =0;
			String incrementOrderNo="";
			String incrementorderDate="";
			String save="";

			String status="";
			String nxtIncrDate="";
			String msg="";
			String disableFlag="No";


			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());

			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);

			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);	    	 

			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	    

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");

			Date sysdate= new Date();


			if(StringUtility.getParameter("save",request)!= null && !StringUtility.getParameter("save",request).equals(""))
			{
				save = StringUtility.getParameter("save",request).toString();
			}

			logger.info("save****************"+ save);


			if(StringUtility.getParameter("orderNo",request)!= null && !StringUtility.getParameter("orderNo",request).equals(""))
			{
				incrementOrderNo = StringUtility.getParameter("orderNo",request).toString();
			}

			if(StringUtility.getParameter("orderdate",request)!= null && !StringUtility.getParameter("orderdate",request).equals(""))
			{
				incrementorderDate = StringUtility.getParameter("orderdate",request).toString();
			}

			logger.info("incrementOrderNo***with goo*************"+ incrementOrderNo);
			logger.info("incrementorderDate********with goo********"+ incrementorderDate);

			BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());

			List duplicateList = billHeadMpgDAOImpl.getDataForDuplicateMessage(incrementOrderNo, locId, langId);

			if(duplicateList.size()>0 && save.equalsIgnoreCase("N"))
			{

				objectArgs.put("msg","This Increment Certificate Order No already exists !!!");


			}
			else
			{
				if(save.equalsIgnoreCase("N"))
				{
					String nextincrementorderDate="";
					String empIncrementOrderDate="";
				
					List<HrPayfixMst> listoftable = billHeadMpgDAOImpl.getAllDataForPayFixation(locId,langId);

					long size = listoftable.size();
					
					List empBasicList = billHeadMpgDAOImpl.getEmpNamesAndBasicDtlss(locId,size);
					List empCustomVOList = new ArrayList();
					
					logger.info("listoftable*********"+listoftable.size());
					logger.info("empBasicList*********"+empBasicList.size());

					String empName ="";
					long basic=0;
					long userid =0;
					long scaleEndAmount=0;
					String nextincrementDate="";

					if(empBasicList!=null && empBasicList.size()!=0)
					{
						for(Iterator itr=empBasicList.iterator();itr.hasNext();)
						{
							empIncrementOrderDate="";
							nextincrementDate="";
							
							Object[] rowList = (Object[])itr.next();

							if(rowList[0] != null)
							{
								empName = rowList[0].toString().trim();
							}
							if(rowList[1] != null)
							{
								basic = Long.parseLong(rowList[1].toString().trim());
							}

							if(rowList[2] != null)
							{
								userid = Long.parseLong(rowList[2].toString().trim());
							}

							if(rowList[3] != null)
							{
								scaleEndAmount = Long.parseLong(rowList[3].toString().trim());
							}
							
							if(rowList[4] != null)
							{
								empIncrementOrderDate=rowList[4].toString();
								logger.info("incrementorderDate from HrEisOtherDtls*********"+empIncrementOrderDate);
							}
							
							if(empIncrementOrderDate!=null && empIncrementOrderDate!="" && empIncrementOrderDate.length()>0)
							{
								Date currDate=sysdate;
								empIncrementOrderDate=sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempIncOrderDateArr[]=empIncrementOrderDate.split("/");
								
								String tempCurrDate=sdf.format(currDate);
								String currDateArr[]=tempCurrDate.split("/");
								Long currYear=Long.parseLong(currDateArr[2]);
								currYear=currYear+1;
								currDateArr[2]=currYear.toString();
								
								nextincrementDate="01"+"/"+"07"+"/"+currYear;
							}
							
							
							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							logger.info("nextincrementDate*********"+nextincrementDate);
							logger.info("empIncrementOrderDate*********"+empIncrementOrderDate);
		
							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							
							/*if(!nextincrementDate.equals("") && !nextincrementDate.equals(null))
								customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));*/
							customVo.setNextincrementDate(nextincrementDate);
							customVo.setUserid(userid);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							//customVo.setNextincrementDate(nextincrementorderDate);
							empCustomVOList.add(customVo);

							logger.info("empCustomVOList size ****************"+ empCustomVOList.size());
						}

						objectArgs.put("save",save);
						objectArgs.put("fixListWithGo", empCustomVOList);
						objectArgs.put("fixListsizeWithGo", empCustomVOList.size());
					}	

				}
				else if(save.equalsIgnoreCase("view"))
				{
					disableFlag="No";

					String empName ="";
					long basic=0;
					long userid =0;
					String incrementOrderDate="";
					long scaleEndAmount=0;
					String nextincrementDate="";
					String withEffectiveDate="";
					String remarks="";
					long newBasic=0;
					String status1="";
					
					String orderDate = StringUtility.getParameter("orderdate",request).toString();
					String IncrementOrderNo = StringUtility.getParameter("orderNo",request).toString();
					logger.info("view orderDate******** is " +orderDate);
					logger.info("view IncrementOrderNo******** is " +IncrementOrderNo);
					
					List<HrPayfixMst> fixList = billHeadMpgDAOImpl.getAllDataFromIncrementOrderNo(locId,langId,IncrementOrderNo);
					List empCustomVOList = new ArrayList();
					OrgUserMst userMst = null;

					logger.info("Size of HrPayFixMst " +fixList.size());
								
					for(int i=0;i<fixList.size();i++ )
					{
						incrementOrderDate="";
						nextincrementDate="";
						withEffectiveDate="";
						status1="";
							
							userMst=fixList.get(i).getUserId();
							userId=userMst.getUserId();
							nextincrementDate=fixList.get(i).getNxtIncrDate().toString();
							incrementOrderDate=fixList.get(i).getIncrementOrderDate().toString();
							newBasic=fixList.get(i).getNewBasic();
							basic=fixList.get(i).getOldBasic();
							//basic=newBasic;
							withEffectiveDate=fixList.get(i).getPayFixDate().toString();
							remarks=fixList.get(i).getRemarks();
							status1=fixList.get(i).getStatus();
							
							List empBasicList = billHeadMpgDAOImpl.getEmpNamesAndBasicDtls(locId, userId);
							logger.info("Emp Basic List size is ---"+empBasicList.size()+" for UserId---"+userId);
							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
							for(Iterator itr=empBasicList.iterator();itr.hasNext();)
							{
								Object[] rowList = (Object[])itr.next();
								if(rowList[0] != null)
								{
									empName = rowList[0].toString().trim();
								}
								if(rowList[3] != null)
								{
									scaleEndAmount = Long.parseLong(rowList[3].toString().trim());
								}
								if(rowList[8] != null)
								{
									incrementOrderDate=rowList[8].toString().trim();
								}
								break;
							}
							
							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							customVo.setUserid(userId);
							if(!nextincrementDate.equals("") && !nextincrementDate.equals(null))
								customVo.setNextincrementDate(sdf.format(sdfParse.parse(nextincrementDate)));
							customVo.setScaleEndAmount(scaleEndAmount);
							
							if(!withEffectiveDate.equals("") && withEffectiveDate!=null)
								customVo.setWithEffectiveDate(sdf.format(sdfParse.parse(withEffectiveDate)).toString());
							if(!incrementOrderDate.equals("") && incrementOrderDate!=null)
							{
								logger.info("Increment Order Date in View Scrren"+incrementOrderDate);
								customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)).toString());
							}
							customVo.setRemarks(remarks);
							customVo.setNewBasic(newBasic);
							customVo.setStatus(status1);
							
							empCustomVOList.add(customVo);

							logger.info("empCustomVOList size ****************"+ empCustomVOList.size());
							if(i==1 && status1.equalsIgnoreCase("Verified"))
								disableFlag="Yes";
						
					}
					

					String update="update";
					objectArgs.put("fixListWithGoUpdate", empCustomVOList);
					objectArgs.put("fixListsizeWithGoUpdate", empCustomVOList.size());
					objectArgs.put("save",save);

									
					//for left side table
					
					List<HrPayfixMst> listoftable = billHeadMpgDAOImpl.getAllDataForPayFixation(locId,langId);

					long size = listoftable.size();

					List empBasicList = billHeadMpgDAOImpl.getEmpNamesAndBasicDtlss(locId,size);
					logger.info("empBasicList size ****************"+ empBasicList.size());
					List empCustomVOList1 = new ArrayList();

					String nextincrementorderDate="";
					String empIncrementOrderDate="";
					
					if(empBasicList!=null && empBasicList.size()!=0)
					{
						for(Iterator itr=empBasicList.iterator();itr.hasNext();)
						{
							empIncrementOrderDate="";
							nextincrementorderDate="";

							Object[] rowList = (Object[])itr.next();

							if(rowList[0] != null)
							{
								empName = rowList[0].toString().trim();
							}
							if(rowList[1] != null)
							{
								basic = Long.parseLong(rowList[1].toString().trim());
							}

							if(rowList[2] != null)
							{
								userid = Long.parseLong(rowList[2].toString().trim());
							}

							if(rowList[3] != null)
							{
								scaleEndAmount = Long.parseLong(rowList[3].toString().trim());
							}
							if(rowList[4] != null)
							{
								empIncrementOrderDate=rowList[4].toString();
								logger.info("incrementorderDate from HrEisOtherDtls*********"+empIncrementOrderDate);
								
							}
							if(empIncrementOrderDate!=null && empIncrementOrderDate!="")
							{
								Date currDate=sysdate;
								
								empIncrementOrderDate=sdf.format(sdfParse.parse(empIncrementOrderDate));
								String tempIncOrderDateArr[]=empIncrementOrderDate.split("/");
								
								String tempCurrDate=sdf.format(currDate);
								String currDateArr[]=tempCurrDate.split("/");
								Long currYear=Long.parseLong(currDateArr[2]);
								currYear=currYear+1;
								currDateArr[2]=currYear.toString();
								
								nextincrementorderDate=tempIncOrderDateArr[0]+"/"+tempIncOrderDateArr[1]+"/"+currDateArr[2];
							}
							
						
							PayIncrementCustomVO customVo = new PayIncrementCustomVO();
						
							customVo.setEmpName(empName);
							customVo.setBasic(basic);
							
							customVo.setUserid(userid);
							customVo.setIncrementOrderDate(empIncrementOrderDate);
							customVo.setScaleEndAmount(scaleEndAmount);
							customVo.setNextincrementDate(nextincrementorderDate);
							empCustomVOList1.add(customVo);

							logger.info("empCustomVOList size ****************"+ empCustomVOList1.size());
						}
					}
						objectArgs.put("fixListWithGo", empCustomVOList1);
						objectArgs.put("fixListsizeWithGo", empCustomVOList1.size());
						objectArgs.put("updateOrderNo", IncrementOrderNo);
						objectArgs.put("updateOrderDate", orderDate);
					//ended

				}
				else if (save.equals("Yes"))
				{
					
					String empIdstoBeAttached = StringUtility.getParameter("empIdstoBeAttached",request).toString();
					logger.info("empIdstoBeAttached are ****************"+ empIdstoBeAttached);
					String empIdsStr[]=empIdstoBeAttached.split("~");
					logger.info("empIdstoBeAttached**********"+empIdsStr);
					
					String empIdstoBeDetached = StringUtility.getParameter("empIdstoBeDetached",request).toString();
					logger.info("empIdstoBeDetached are ****************"+ empIdstoBeDetached);
					String empIdsDetachStr[]=empIdstoBeDetached.split("~");
					logger.info("empIdstoBeDetached**********"+empIdsDetachStr);
					
					String empBasicSalarytoBeAttached = StringUtility.getParameter("empBasicSalarytoBeAttached",request).toString();
					logger.info("empBasicSalarytoBeAttached are ****************"+ empBasicSalarytoBeAttached);
					String empBasicStr[]=empBasicSalarytoBeAttached.split("~");
					logger.info("empBasicSalarytoBeAttached**********"+empBasicStr);
					
					String empWEFtoBeAttached = StringUtility.getParameter("empWEFtoBeAttached",request).toString();
					logger.info("empWEFtoBeAttached are ****************"+ empWEFtoBeAttached);
					String empWEFStr[]=empWEFtoBeAttached.split("~");
					logger.info("empWEFtoBeAttached**********"+empWEFStr);
					
					String inputTagElements = StringUtility.getParameter("inputTagElements",request).toString();
					logger.info("inputTagElements are ****************"+ inputTagElements);
					String inputElementsUpdate[]=inputTagElements.split("~");
					logger.info("inputElementsUpdate**********"+inputElementsUpdate);
					
					int j=0;
					String empWEFStrUpdate[]=new String[inputElementsUpdate.length/3];
					String empIdsUpdate[]=new String[inputElementsUpdate.length/3];
					String empRemarksUpdate[]=new String[inputElementsUpdate.length/3];
					logger.info("Length of inputElementsUpdate--"+inputElementsUpdate.length);
					for(int i=0;i<inputElementsUpdate.length;i+=3)
					{
						logger.info("Value of i and j are"+i+"---"+j);
						empIdsUpdate[j]=inputElementsUpdate[i].toString();
						empWEFStrUpdate[j]=inputElementsUpdate[i+1].toString();
						if(inputElementsUpdate[i+2].toString()!=null && inputElementsUpdate[i+2].toString()!="")
							empRemarksUpdate[j]=inputElementsUpdate[i+2].toString();
						else
							empRemarksUpdate[j]="";
						
						logger.info("empRemarksUpdate"+empRemarksUpdate[j]);
						j++;
					}
					
					String empNextIncrDatetoBeAttached = StringUtility.getParameter("empNextIncrDatetoBeAttached",request).toString();
					logger.info("empNextIncrDatetoBeAttached are ****************"+ empNextIncrDatetoBeAttached);
					String empNextIncrStr[]=empNextIncrDatetoBeAttached.split("~");
					logger.info("empNextIncrDatetoBeAttached**********"+empNextIncrStr);
					
					String empRemarkstoBeAttached = StringUtility.getParameter("empRemarkstoBeAttached",request).toString();
					logger.info("empRemarkstoBeAttached are ****************"+ empRemarkstoBeAttached);
					String empRemarksStr[]=empRemarkstoBeAttached.split("~");
					logger.info("empRemarkstoBeAttached**********"+empRemarksStr);		
					
					String empIncOrderDatetoBeAttached = StringUtility.getParameter("empIncOrderDatetoBeAttached",request).toString();
					logger.info("empIncOrderDatetoBeAttached are ****************"+ empIncOrderDatetoBeAttached);
					String empIncOrderDateStr[]=empIncOrderDatetoBeAttached.split("~");
					logger.info("empIncOrderDatetoBeAttached**********"+empIncOrderDateStr);	
					
					String empOrigBasictoBeAttached = StringUtility.getParameter("empOrigBasictoBeAttached",request).toString();
					logger.info("empOrigBasictoBeAttached are ****************"+ empOrigBasictoBeAttached);
					String empOrigBasicStr[]=empOrigBasictoBeAttached.split("~");
					logger.info("empIncOrderDatetoBeAttached**********"+empOrigBasicStr);	

					EmpPayIncrDAOImpl empPayIncrDAOImpl = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
					logger.info("Length of detach IDs variable array--"+empIdsDetachStr.length+"and String is--"+empIdsDetachStr.toString());
					
					//This loop will deactivate employees in detached
					if(empIdsDetachStr.toString()!="")
					{
						for(int i=0;i<empIdsDetachStr.length;i++)
						{
							logger.info("Inside Loop from where detach dao is  called"+empIdsDetachStr[i]);
							long userIdDetach=0;
							boolean flag=false;
							if(empIdsDetachStr[i]!=null && empIdsDetachStr.length>0 && empIdsDetachStr[i]!="")
							{
								userIdDetach=Long.parseLong(empIdsDetachStr[i]);
								logger.info("Inside if condition from where detach dao is  called"+userIdDetach);
								flag=empPayIncrDAOImpl.deactivateExistingEmployees(incrementOrderNo,userIdDetach);
							}
							
						}
					}
					
					//This two loops will deactivate employees first who are attached and then reactivate them
					if(empIdsStr != null && empIdsStr.length>0 &&  empIdsStr[0]!="")
					{
						logger.info("showDataForNewOrderNo Deactivating all the attached employees ");
						
						long userIdDeactivate=0;
						boolean flag=false;
						for(int i=0;i<empIdsStr.length;i++)
						{
							if(empIdsStr[i]!=null && empIdsStr.length>0 && empIdsStr[i]!="")
							{
								userIdDeactivate=Long.parseLong(empIdsStr[i]);
								logger.info("Inside Loop from where deactivation of attached Employee DAO is called"+userIdDeactivate);
								flag=empPayIncrDAOImpl.deactivateExistingEmployees(incrementOrderNo,userIdDeactivate);
							}
						}
						logger.info("showDataForNewOrderNo saving data into database means reActivating attached employees");
						for(int i=0;i<empIdsStr.length;i++)
						{
							long oldBasicVO=0;
							long newBasicVO=0;
							Date wefDateVO=null;
							Date nextIncrDtVO=null;
							String remarksVO=null;
							long userIdVO=0;
							Timestamp incrOrderDtVO=null;
							
							HrPayfixMst hrPayfixMst =  new HrPayfixMst();
							if(empIdsStr[i]!=null && empIdsStr.length>0 && empIdsStr[i]!="")
							{
								userIdVO=Long.parseLong(empIdsStr[i]);
							}
							if(empBasicStr[i]!=null && empBasicStr.length>0 && empBasicStr[i]!="")
							{
								newBasicVO=Long.parseLong(empBasicStr[i]);
								/*logger.info("New Basic--"+newBasicVO);
								long percent3=(long)(newBasicVO*0.03);
								
								if(percent3%10!=0)
								{
									percent3/=10;
									percent3+=1;
									percent3*=10;
								}
								oldBasicVO=newBasicVO-percent3;
								logger.info("Old Basic--"+oldBasicVO);*/
							}
							if(empOrigBasicStr[i]!=null && empOrigBasicStr.length>0 && empOrigBasicStr[i]!="")
							{
								oldBasicVO=Long.parseLong(empOrigBasicStr[i]);
								logger.info("Old Basic from empOrigBasicStr--"+oldBasicVO);
							}
							if(empWEFStr[i]!=null && empWEFStr.length>0 && empWEFStr[i]!="")
							{
								wefDateVO=sdf.parse(empWEFStr[i]);
							}
							String incrOrderNoVo=incrementOrderNo;
														
							status="Entered";
							OrgUserMst orgUserMstVO = orgUserMstDaoImpl.read(userIdVO);
							if(empNextIncrStr[i]!=null && empNextIncrStr.length>0 && empNextIncrStr[i]!="")
							{
								nextIncrDtVO=sdf.parse(empNextIncrStr[i]);
							}
							
							long payFixIdVO = IDGenerateDelegate.getNextId("HR_PAYFIX_MST", objectArgs);
							if(empRemarksStr!=null && empRemarksStr.length>0 && empRemarksStr[i]!="")
							{
								remarksVO=empRemarksStr[i].toString();
							}
							if(incrementorderDate.toString()!=null && incrementorderDate.length()>0 && incrementorderDate.toString()!="")
							{
								Date incrementorderDateTemp=sdf.parse(incrementorderDate);
								incrOrderDtVO=new Timestamp(incrementorderDateTemp.getTime());
							}
							
							
							hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
							hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
							hrPayfixMst.setCmnLocationMst(cmnLocationMst);
							hrPayfixMst.setCreatedDate(sysdate);
							hrPayfixMst.setIncrementOrderDate(incrOrderDtVO);
							
							hrPayfixMst.setIncrementOrderNo(incrOrderNoVo);
							hrPayfixMst.setNewBasic(newBasicVO);
							hrPayfixMst.setNxtIncrDate(nextIncrDtVO);
							hrPayfixMst.setOldBasic(oldBasicVO);
							hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
							hrPayfixMst.setOrgPostMstUpdatedByPost(orgPostMst);
							hrPayfixMst.setOrgUserMstCreatedBy(orgUserMstVO);
							hrPayfixMst.setOrgUserMstUpdatedBy(orgUserMstVO);
							hrPayfixMst.setPayFixDate(wefDateVO);
							hrPayfixMst.setPayfixId(payFixIdVO);
							hrPayfixMst.setRemarks(remarksVO);
							hrPayfixMst.setStatus(status);
							hrPayfixMst.setUpdatedDate(sysdate);
							hrPayfixMst.setUserId(orgUserMstVO);
							hrPayfixMst.setActivateFlag(1);
							
							empPayIncrDAOImpl.create(hrPayfixMst);
							objectArgs.put("msg","Inserted Successfully");
							objectArgs.put("save",save);
						
						}
					}
					
					//This Loop will update remarks and WEF if any changes are made
					logger.info("empIdsUpdate's length"+empIdsUpdate.length);
					if(empIdsUpdate.length>0)
					{
						long tempEmpId=0;
						String tempRemarksUpdate="";
						String tempWEFUpdate="";
						
						for(int i=0;i<empIdsUpdate.length;i++)
						{
							tempEmpId=Long.parseLong(empIdsUpdate[i].toString());
							tempRemarksUpdate=empRemarksUpdate[i].toString();
							tempWEFUpdate=empWEFStrUpdate[i].toString();
							
							tempWEFUpdate=sdfParse.format(sdf.parse(tempWEFUpdate)).toString();
							logger.info("tempWEFUpdate with value of i"+tempWEFUpdate+"--"+i);
							empPayIncrDAOImpl.updateRemarksWEF(tempEmpId,tempWEFUpdate.toString(),tempRemarksUpdate);
						}
					}
						
					
				}
				else
				{
					logger.info("with new page is coming");

				}
			}
			
			logger.info("While Ending showIncrement Date is"+incrementorderDate);
			objectArgs.put("incrementOrderNo", incrementOrderNo);
			objectArgs.put("incrementorderDate", incrementorderDate);

			String incrementOrderDate="";
			long countOfEmployees=0;
			List empCustomVOList = new ArrayList();
			List<HrPayfixMst> fixList = billHeadMpgDAOImpl.getAllDataForPayFixation(locId,langId);
			
			logger.info("fixList size----"+fixList.size());

			for(Iterator itr=fixList.iterator();itr.hasNext();)
			{

				Object[] row = (Object[])itr.next();

				if(row[0]!=null)
				{
					incrementOrderNo = row[0].toString();
				}

				if(row[1]!=null)
				{
					incrementOrderDate= row[1].toString();
					logger.info("Increment Orde Date while getting fixList"+incrementOrderDate);
				}
				if(row[2]!=null)
				{
					status = row[2].toString();
				}
				if(row[3]!=null)
				{
					countOfEmployees = Long.parseLong(row[3].toString());
				}

				PayIncrementCustomVO customVo = new PayIncrementCustomVO();

				customVo.setIncrementOrderNo(incrementOrderNo);
				if(!incrementOrderDate.equals("") && !incrementOrderDate.equals(null))
					customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
				customVo.setStatus(status);
				customVo.setCountOfEmployees(countOfEmployees);
				empCustomVOList.add(customVo);


			}


			objectArgs.put("fixList", empCustomVOList);
			objectArgs.put("fixListsize", empCustomVOList.size());
			objectArgs.put("disableFlag", disableFlag);

			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmpPayIncrList"); 


		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

		return resultObject;

	}
	
	public ResultObject verifyOrderDetails(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		logger.info("verifyOrderDetails******* method for verify order*****");
		
		try
		{
		
			Map loginMap = (Map) objectArgs.get("baseLoginMap");
		
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			
			long postId=Long.parseLong(loginMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			
			long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			long dbId=Long.parseLong(loginMap.get("dbId").toString());
	        CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			
			BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
			EmpPayIncrDAOImpl empPayIncrDAOImplUpdateDao = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
			OtherDetailDAOImpl otherDetailDAOImpl=new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
			
			String orderNo="";
			if(StringUtility.getParameter("orderNo",request)!= null && !StringUtility.getParameter("orderNo",request).equals(""))
			{
				orderNo=(StringUtility.getParameter("orderNo",request)).toString();
			}
			logger.info("OrderNo******"+orderNo);
			
			boolean successFlag=false;
			int statusFlag=0;
			List incrList=null;
			long basicSalary=0;
			long userIdInc=0;
			long changeEmpId =0;
			long changedPostId =0;
			long changedUserId=0;
			long empId=0;
			long otherId=0;
			HrEisOtherDtls hrEisOtherDtls=new HrEisOtherDtls();
			
			Map newMap=null;
			OrgUserMst orgUserMstNew=null;
			
			if(orderNo!=null && orderNo.length()>0)
			{
				successFlag=empPayIncrDAOImplUpdateDao.verifyOrder(orderNo);
				if(successFlag)
				{
					incrList=empPayIncrDAOImplUpdateDao.getEmployeesForParticularOrder(orderNo);
				}
				
				if(incrList!=null && incrList.size()>0)
				{
					String tempBasic=null;
					String tempUser=null;
					String tempWEF=null;
					for(Iterator itr=incrList.iterator();itr.hasNext();)
						{
							Object[] rowList = (Object[])itr.next();
							logger.info("UserId--"+rowList[0].toString());
							logger.info("Basic--"+rowList[1].toString());
							logger.info("WEF--"+rowList[2].toString());
							tempUser=rowList[0].toString();
							tempBasic=rowList[1].toString();
							tempWEF=rowList[2].toString();
							
							basicSalary=Long.parseLong(tempBasic);
							userIdInc=Long.parseLong(tempUser);
							if(userIdInc!=0)
							{
								empId=empPayIncrDAOImplUpdateDao.getEmpIdFromUserId(userIdInc);
								logger.info("empId--"+empId);
							}
							if(empId!=0)
							{
								otherId=empPayIncrDAOImplUpdateDao.getOtherIdFromEmpId(empId);
								logger.info("otherId--"+otherId);
							}
							if(otherId!=0)
							{
									hrEisOtherDtls=otherDetailDAOImpl.read(otherId);
									logger.info("hrEisOtherDtls--"+hrEisOtherDtls.getOtherId());
							}
							if(hrEisOtherDtls!=null)
							{
								hrEisOtherDtls.setotherCurrentBasic(basicSalary);
								otherDetailDAOImpl.update(hrEisOtherDtls);
								logger.info("Sucessfull Updated");
							}
							
							/**
							 * Change Record Entry Begins
							 */
							orgUserMstNew=orgUserMstDaoImpl.read(userIdInc);
							
							
							changedUserId=userIdInc;
							OrgUserMst tempUserMst = orgUserMstDaoImpl.read(changedUserId);
							logger.info("tempUserMst  "+tempUserMst);
							
							GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(OrgUserpostRlt.class);
							genDao.setSessionFactory(serv.getSessionFactory());
							List postList = genDao.getListByColumnAndValue("orgUserMst.userId",tempUserMst.getUserId());
							if(postList!=null && postList.size()>0)
								changedPostId=( (OrgUserpostRlt)postList.get(0)).getOrgPostMstByPostId().getPostId();
							
							BatchITGpfDetailsUpdateDaoImpl dao = new BatchITGpfDetailsUpdateDaoImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
							OrgPostMst orgPostMstNew = orgPostMstDaoImpl.read(postId);
							OrgPostMst changePostMst = orgPostMstDaoImpl.read(postId);
							
							newMap=new HashMap();
							newMap=objectArgs;
							if(orgPostMstNew!=null) 
							{
								
								newMap.put("changedEmpId",empId);
								newMap.put("changedPostId",changedPostId);
								newMap.put("locId",locId);
								newMap.put("serviceLocator",serv);
								//newMap.put("changedPostId",postIdNew);
								//newMap.put("OrgPostMst",orgPostMst);
								newMap.put("OrgUserMst",orgUserMst);
								newMap.put("OrgPostMst",changePostMst);
								newMap.put("cmnDatabaseMst",cmnDatabaseMst);
								GenerateBillServiceHelper generateBillServiceHelper=new GenerateBillServiceHelper();
								long changedRecordPK=generateBillServiceHelper.insertChangedRecord(newMap);
								logger.info("VerifyOrderDetails:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								logger.info("VerifyOrderDetails:::::::insertChangedRecord method called;;;;;changedRecordPK is"+changedRecordPK);
								objectArgs.put("valueUpdated", "Y");
							}
							/**
							 * Change Record Entry Ends
							 */
						}
				}
			}
			
			if(successFlag)
				statusFlag=1;
			else
				statusFlag=-1;
			
			StringBuffer paybillSBf=new StringBuffer();
			paybillSBf.append("<paybillMapping>");
			paybillSBf.append("<status>").append(statusFlag).append("</status>");
			paybillSBf.append("</paybillMapping>");  
			
			String orderStatus = new AjaxXmlBuilder().addItem("ajax_key", paybillSBf.toString()).toString();
	         
			Map map = objectArgs ;
	        logger.info(" the string buffer is :"+orderStatus);
	        map.put("ajaxKey", orderStatus);
			
			
			String incrementOrderDate="";
			long countOfEmployees=0;
			String incrementOrderNo="";
			String status="";
			List empCustomVOList = new ArrayList();
			List<HrPayfixMst> fixList = billHeadMpgDAOImpl.getAllDataForPayFixation(locId,langId);
			
			logger.info("fixList size----"+fixList.size());
			logger.info("fixList size----"+fixList.size());

			for(Iterator itr=fixList.iterator();itr.hasNext();)
			{

				Object[] row = (Object[])itr.next();

				if(row[0]!=null)
				{
					incrementOrderNo = row[0].toString();
				}

				if(row[1]!=null)
				{
					incrementOrderDate= row[1].toString();
					logger.info("Increment Orde Date while getting fixList"+incrementOrderDate);
				}
				if(row[2]!=null)
				{
					status = row[2].toString();
				}
				if(row[3]!=null)
				{
					countOfEmployees = Long.parseLong(row[3].toString());
				}

				PayIncrementCustomVO customVo = new PayIncrementCustomVO();

				customVo.setIncrementOrderNo(incrementOrderNo);
				if(!incrementOrderDate.equals("") && !incrementOrderDate.equals(null))
					customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
				customVo.setStatus(status);
				customVo.setCountOfEmployees(countOfEmployees);
				empCustomVOList.add(customVo);

			}

			logger.info("********Successfully Exit from Verify Order Details***********");
			objectArgs.put("fixList", empCustomVOList);
			objectArgs.put("fixListsize", empCustomVOList.size());
			
			
			resultObject.setResultValue(map);
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("ajaxData"); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultObject;
	}
	//Ended by Amish

	public ResultObject updateIncrementDtls(Map objectArgs)
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		logger.info("updateIncrementDtls******* method for update details*****");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			
			CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv.getSessionFactory());
			CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

			CmnLanguageMstDao cmnLanguageMstDao = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serv.getSessionFactory());
			CmnLanguageMst cmnLanguageMst = cmnLanguageMstDao.read(langId);

			OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);	    	 

			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);	  
			
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
			
			Date sysdate= new Date();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
			
			BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
			
			String incrementOrderNo="";
			String incrementorderDate="";
			String nxtIncrDate="";
			String save="";
			String status="";
			int insertMsg=0;
			int updateMsg=0;
		
			
			
			
			//For Insert
			
			Long len=0L;
			String length = StringUtility.getParameter("length",request).toString();
			if(!length.equals("") && length!=null && !length.equals("undefined"))
			len=Long.parseLong(length);
			logger.info("updateIncrementDtls insert len is **********"+len);
			
			Date orderDate=null;
			Date nxtIncrementDate=null;
			EmpPayIncrDAOImpl empPayIncrDAOImplUpdateDao = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
			
			
			for(int x=1;x<=len;x++)
			{

				logger.info("in updateIncrementDtls for insert x**********"+x);


				EmpPayIncrDAOImpl empPayIncrDAOImpl = new EmpPayIncrDAOImpl(HrPayfixMst.class,serv.getSessionFactory());
				HrPayfixMst hrPayfixMst =  new HrPayfixMst();

				String empName = StringUtility.getParameter("employeeName"+x,request).toString();
				String empBasic = StringUtility.getParameter("basicInsert"+x,request).toString();
				String empWEF = StringUtility.getParameter("wefInsert"+x,request).toString();
				String withDt = StringUtility.getParameter("withDt"+x,request).toString();
				String userid = StringUtility.getParameter("userIdInsert"+x,request).toString();
				String remarksName = StringUtility.getParameter("remarksInsert"+x,request).toString();

				if(StringUtility.getParameter("updateOrderNo",request)!= null && !StringUtility.getParameter("updateOrderNo",request).equals(""))
				{
					incrementOrderNo = StringUtility.getParameter("updateOrderNo",request).toString();
				}

				if(StringUtility.getParameter("updateOrderDate",request)!= null && !StringUtility.getParameter("updateOrderDate",request).equals(""))
				{
					incrementorderDate = StringUtility.getParameter("updateOrderDate",request).toString();
				}


				if(StringUtility.getParameter("nxtIncrDate",request)!= null && !StringUtility.getParameter("nxtIncrDate",request).equals(""))
				{
					nxtIncrDate = StringUtility.getParameter("nxtIncrDate",request).toString();
				}


				logger.info("in updateIncrementDtls for insert empName**********"+empName);
				logger.info("in updateIncrementDtls for insert basic**********"+empBasic);
				logger.info("in updateIncrementDtls for insert withDt**********"+empWEF);
				logger.info("in updateIncrementDtls for insert userid**********"+userid);
				logger.info("in updateIncrementDtls for insert remarks**********"+remarksName);
				logger.info("in updateIncrementDtls for insert incrementOrderNo**********"+incrementOrderNo);
				logger.info("in updateIncrementDtls for insert incrementorderDate**********"+incrementorderDate);
				logger.info("in updateIncrementDtls for insert nxtIncrDate**********"+nxtIncrDate);



				Date withEffictiveDate=null;
				long empOldBasic = 0;
				long empNewBasic = 0;

				if(!empBasic.equals("") && empBasic!=null)
					empOldBasic = Long.parseLong(empBasic);
				empNewBasic = empOldBasic+ (((empOldBasic) / 100) * 3);

				logger.info("in updateIncrementDtls for insert empNewBasic**********" +empNewBasic);


				if(!empWEF.equals(""))
					withEffictiveDate=sdf.parse(empWEF);
				status="Entered";

				if(!incrementorderDate.equals(""))
					orderDate = sdf.parse(incrementorderDate);

				
				
				
				OrgUserMst orgUserMstt = orgUserMstDaoImpl.read(Long.parseLong(userid));

				if(!empWEF.equals(""))
				{
				String b[] = empWEF.split("/");
				long year = Long.parseLong(b[2]);
				year+=1;
				String month = b[1];
				String date= b[0];
				logger.info("year**********" +year);
				logger.info("month**********" +month);
				logger.info("date**********" +date);
				nxtIncrDate=date+"/"+month+"/"+year;
				logger.info("nextincrementDate*********" +nxtIncrDate);
				}

				Long payFixId=0L;
				payFixId = IDGenerateDelegate.getNextId("hr_payfix_mst", objectArgs);
				logger.info("in updateIncrementDtls for insert primary key***********"+ payFixId);
				hrPayfixMst.setPayfixId(payFixId);
				hrPayfixMst.setUserId(orgUserMstt);

				if(withEffictiveDate!=null)
					hrPayfixMst.setPayFixDate(withEffictiveDate);

				if(!remarksName.equals("") && remarksName!=null)
					hrPayfixMst.setRemarks(remarksName);
				hrPayfixMst.setCmnLanguageMst(cmnLanguageMst);
				hrPayfixMst.setCmnDatabaseMst(cmnDatabaseMst);
				hrPayfixMst.setCmnLocationMst(cmnLocationMst);
				hrPayfixMst.setOrgUserMstCreatedBy(orgUserMst);
				hrPayfixMst.setOrgPostMstCreatedByPost(orgPostMst);
				hrPayfixMst.setCreatedDate(sysdate);
				hrPayfixMst.setStatus(status);

				if(!empBasic.equals("") && empBasic!=null)
					hrPayfixMst.setOldBasic(Long.parseLong(empBasic));

				hrPayfixMst.setNewBasic(empNewBasic);
				hrPayfixMst.setIncrementOrderNo(incrementOrderNo);
				if(orderDate!=null)
					hrPayfixMst.setIncrementOrderDate(orderDate);
				if(nxtIncrDate!=null && !nxtIncrDate.equals(""))
				hrPayfixMst.setNxtIncrDate(sdf.parse(nxtIncrDate));

				empPayIncrDAOImpl.create(hrPayfixMst);

				insertMsg=1;

			}

			
			//For Update

			String lengthupdate = StringUtility.getParameter("fixListsizeWithGoUpdate",request).toString();
			logger.info("updateIncrementDtls update  lengthupdate is**********"+lengthupdate);
			
			if(!lengthupdate.equals("") && lengthupdate!=null && !lengthupdate.equals("undefined"))
			for(int i=1;i<=Long.parseLong(lengthupdate);i++)
			{


				String  userIdUpdate = StringUtility.getParameter("userIdUpdate"+i, request).toString();
				String employeeNameUpdate = StringUtility.getParameter("employeeNameUpdate"+i,request).toString();
				String newBasicUpdate = StringUtility.getParameter("newBasicUpdate"+i,request).toString();
				String withDtUpdate = StringUtility.getParameter("withDtUpdatename"+i,request).toString();
				String remarksUpdate = StringUtility.getParameter("remarksUpdatename"+i,request).toString();



				logger.info("in updateIncrementDtls for update userIdUpdate**********"+userIdUpdate);
				logger.info("in updateIncrementDtls for update employeeNameUpdate**********"+employeeNameUpdate);
				logger.info("in updateIncrementDtls for update newBasicUpdate**********"+newBasicUpdate);
				logger.info("in updateIncrementDtls for update withDtUpdate**********"+withDtUpdate);
				logger.info("in updateIncrementDtls for update remarksUpdate**********"+remarksUpdate);


				List<HrPayfixMst>  pkList = billHeadMpgDAOImpl.getAllDataForPayFixationPk(Long.parseLong(userIdUpdate), locId);

				long payFixId =0;

				for(int j=0;j<pkList.size();j++)
				{

					payFixId = pkList.get(j).getPayfixId();
					logger.info("in updateIncrementDtls for update payFixId**********"+payFixId);

					List<HrPayfixMst> gettingUpdateList = billHeadMpgDAOImpl.getAllDataForPayFixationFromPk(payFixId);
					for(int k=0;k<gettingUpdateList.size();k++)
					{
						incrementOrderNo=gettingUpdateList.get(k).getIncrementOrderNo();
						orderDate= gettingUpdateList.get(k).getIncrementOrderDate();
						nxtIncrementDate =gettingUpdateList.get(k).getNxtIncrDate();

					}
				}

				logger.info("in updateIncrementDtls for update incrementOrderNo**********"+incrementOrderNo);
				logger.info("in updateIncrementDtls for update orderDate****************"+ orderDate);
				logger.info("in updateIncrementDtls for update nxtIncrementDate****************"+ nxtIncrementDate);
				logger.info("in updateIncrementDtls for update With Date****************"+ withDtUpdate);

				String b[] = withDtUpdate.split("/");
				long year = Long.parseLong(b[2]);
				year+=1;
				String month = b[1];
				String date= b[0];
				
				logger.info("year**********" +year);
				logger.info("month**********" +month);
				logger.info("date**********" +date);
				
				String nextincrementDate=date+"/"+month+"/"+year;
				
				
				OrgUserMst orgUserMsttUpdate = orgUserMstDaoImpl.read(Long.parseLong(userIdUpdate));
				HrPayfixMst hrPayfixMstUpdate = (HrPayfixMst) empPayIncrDAOImplUpdateDao.read(payFixId);

				if(sdf.parse(withDtUpdate)!=null)
					hrPayfixMstUpdate.setPayFixDate(sdf.parse(withDtUpdate));
				hrPayfixMstUpdate.setNxtIncrDate(sdf.parse(nextincrementDate));
				hrPayfixMstUpdate.setRemarks(remarksUpdate);
				hrPayfixMstUpdate.setUpdatedDate(sysdate);
				hrPayfixMstUpdate.setOrgUserMstUpdatedBy(orgUserMst);
				hrPayfixMstUpdate.setOrgPostMstUpdatedByPost(orgPostMst);
				empPayIncrDAOImplUpdateDao.update(hrPayfixMstUpdate);

				updateMsg=1;
				
			}
			
			if(insertMsg==1 && updateMsg==1)
			{
				objectArgs.put("msg","Data Saved Successfully");
			}
			else if(updateMsg==1)
			{
				objectArgs.put("msg","Updated Successfully");
			}
			else if(insertMsg==1)
			{
				objectArgs.put("msg","Insert Successfully");
			}
			else
			{
				objectArgs.put("msg","No data Inserted and Updated");
			}
			
			String incrementOrderDate="";
			long countOfEmployees=0;
			List empCustomVOList = new ArrayList();
			List<HrPayfixMst> fixList = billHeadMpgDAOImpl.getAllDataForPayFixation(locId,langId);

			for(Iterator itr=fixList.iterator();itr.hasNext();)
			{

				Object[] row = (Object[])itr.next();

				if(row[0]!=null)
				{
					incrementOrderNo = row[0].toString();
				}

				if(row[1]!=null)
				{
					incrementOrderDate= row[1].toString();
				}
				if(row[2]!=null)
				{
					status = row[2].toString();
				}
				if(row[3]!=null)
				{
					countOfEmployees = Long.parseLong(row[3].toString());
				}

				PayIncrementCustomVO customVo = new PayIncrementCustomVO();

				customVo.setIncrementOrderNo(incrementOrderNo);
				if(!incrementOrderDate.equals("") && !incrementOrderDate.equals(null))
					customVo.setIncrementOrderDate(sdf.format(sdfParse.parse(incrementOrderDate)));
				customVo.setStatus(status);
				customVo.setCountOfEmployees(countOfEmployees);
				empCustomVOList.add(customVo);
				


			}


			objectArgs.put("fixList", empCustomVOList);
			objectArgs.put("fixListsize", empCustomVOList.size());
			

			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("EmpPayIncrList"); 
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultObject;
	}
	
	
	public ResultObject showIncrementPrintReport(Map objectArgs)
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		logger.info("showIncrementPrintReport******* method for print report*****");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			
			long langId = StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			long dbId = StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			
			
			String  incrementOrderNo = StringUtility.getParameter("incrementOrderNo", request);
			
			logger.info("incrementOrderNo**********"+incrementOrderNo);
			
			BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
			List printReportList = billHeadMpgDAOImpl.getPrintReportDataFromIncrementOrderNo(locId,incrementOrderNo);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			
			String employeeName="";
			String dsgnName="";
			String withEffectiveDate="";
			long oldBasic=0;
			long newBasic=0;
			String nextincrementDate="";
			String empDsgnName="";
			String incrementOrderDate="";
		
			List printReportCustomList = new ArrayList();
			
			for(Iterator iterator = printReportList.iterator();iterator.hasNext();)
			{
				
				PayIncrementCustomVO customVO = new PayIncrementCustomVO();
				Object[] row = (Object[])iterator.next();
				
				if(row[0]!=null)
				{
					employeeName = row[0].toString();
					customVO.setEmployeeName(employeeName);
				}
				
				if(row[1]!=null)
				{
					dsgnName=row[1].toString();
					customVO.setDsgnName(dsgnName);
				}
				if(row[2]!=null)
				{
					withEffectiveDate=row[2].toString();
					customVO.setWithEffectiveDate(dateFormat.format(sdf.parse((withEffectiveDate))));
					
				}
				if(row[3]!=null)
				{
					oldBasic=Long.parseLong(row[3].toString());
					customVO.setBasic(oldBasic);;
				}
				if(row[4]!=null)
				{
					newBasic=Long.parseLong(row[4].toString());
					customVO.setNewBasic(newBasic);
				}
				if(row[5]!=null)
				{	
					nextincrementDate=row[5].toString();
					customVO.setNextincrementDate(dateFormat.format(sdf.parse((nextincrementDate))));
				}
				if(row[6]!=null)
				{	
					incrementOrderDate=row[6].toString();
					
					customVO.setIncrementOrderDate(dateFormat.format(sdf.parse((incrementOrderDate))));
				}
				
				
				empDsgnName=employeeName+"("+dsgnName+")";
				customVO.setEmpDsgnName(empDsgnName);
				
				long newbasic =customVO.getNewBasic();
				long basic =customVO.getBasic();
				long percentageAmount = newbasic-basic;
				
				logger.info("newbasic**********"+newbasic);
				logger.info("basic**********"+basic);
				logger.info("percentageAmount**********"+percentageAmount);
				
				customVO.setPercentageAmount(percentageAmount);
				
				
				printReportCustomList.add(customVO);
				
				
			}
			long reportlistsize = printReportCustomList.size();
			
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			logger.info("10. "+format.format(now));
			  
		
			objectArgs.put("printReportCustomList",printReportCustomList);
			objectArgs.put("reportlistsize",reportlistsize);
			objectArgs.put("verificationTime",format.format(now));
			
			resultObject.setResultValue(objectArgs);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setViewName("incrementPrintReport"); 
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultObject;
	}


	public ResultObject deleteUpdateRecordsFromTable(Map objectArgs)
	{
		
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		logger.info("deleteUpdateRecordsFromTable************");
		try
		{
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			long locId = Long.parseLong(loginDetailsMap.get("locationId").toString());
			
			long userId = 0;
			if (StringUtility.getParameter("userId", request) != null && !StringUtility.getParameter("userId",request).equals(""))
				userId = Long.valueOf(StringUtility.getParameter("userId", request).toString());
			
			logger.info("in deleteUpdateRecordsFromTable userId*********" + userId);
			
			
			String orderNo = "";
			if (StringUtility.getParameter("orderNo", request) != null && !StringUtility.getParameter("orderNo",request).equals(""))
				orderNo = StringUtility.getParameter("orderNo", request).toString();
			logger.info("in deleteUpdateRecordsFromTable orderNo*********" + orderNo);
			
			
			
			BillHeadMpgDAOImpl billHeadMpgDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());
		
			int dletedRowSize =	0;
			
			if (StringUtility.getParameter("userId", request) != null && !StringUtility.getParameter("userId",request).equals(""))
			{
			dletedRowSize =	billHeadMpgDAOImpl.deleteRecordsInPayIncremetTable(userId, locId);
			logger.info("int userId dletedRowSize***"+dletedRowSize);
			
			}
			else
			{
				if (StringUtility.getParameter("orderNo", request) != null && !StringUtility.getParameter("orderNo",request).equals(""))
				dletedRowSize =	billHeadMpgDAOImpl.deleteOrderRecordsInPayIncremetTable(orderNo, locId);
				logger.info("int orderNo dletedRowSize***"+dletedRowSize);
			}
			
			
			
			StringBuffer billNoStr = new StringBuffer();
			
			if (dletedRowSize>0) 
			{
				    String success="success";
					billNoStr.append("<Result>");
					billNoStr.append("<message>").append(success).append("</message>");
					billNoStr.append("</Result>");
			}
			else
			{
				String failed="failed";
				billNoStr.append("<Result>");
				billNoStr.append("<message>").append(failed).append("</message>");
				billNoStr.append("</Result>");
			}
			
			
			String searchBillNoList = new AjaxXmlBuilder().addItem("ajax_key",billNoStr.toString()).toString();
			objectArgs.put("ajaxKey", searchBillNoList);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultObject;
	}

	
	
}






