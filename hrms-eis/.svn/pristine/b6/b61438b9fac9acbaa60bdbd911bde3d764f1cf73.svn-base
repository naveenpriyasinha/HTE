/**
 *  Class Name:- QuaterDtlsVOGen
 *  Author:-     Urvin Shah.
 *  Date:-		 28-01-2008
 *  Use:-		 This class fetch values from QuaterAllocation.jsp and set in the VO and return this VO to Service.  
 */



package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.hod.common.valueobject.CmnAddressMst;

public class QuaterDtlsVOGen extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
	
	/**
	 * Method Name:-	getQuarterDtlsVOGen
	 * Author:-			Urvin Shah.
	 * Date:-			28-01-2008
	 * Use:-			This methhod prepare the VO by getting the Data from the QuarterAllocation.jsp and after setting the Values send it to the Service.
	 * @param objectArgs
	 * @return
	 */
	
	public ResultObject getQuarterDtlsVOGen(Map objectArgs) 
	{
		try
		{
			logger.info("You are in the Quater Details VOGen");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			long userId=0;			// The person who is availing the quater.
			long quarterType;		// Type of Quarter.
			long custodianType;
			String quarterName="";		// Name of the Quater.
			String allocStartDate; 	// Starting date of the Quater Allocation.
			String possessionDate; 	// Possession Date String.
			String allocEndDate;	// Ending Date if the Quater Allocation.
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dtAllocStartDate=null;	// Starting Date.
			Date dtPossessionDate=null;	// This is the Date on which the employee get the possission of the Quarter.
			Date dtAllocEndDate=null;	// End Date.
			String editMode;		// Edit Mode.
			String quarterRateType;		// Quarter Rate Type.
			HrEisQtrMst hrEssQtrMst = new HrEisQtrMst();
			//added by Ankit bhatt
			long qtrRent = 0;
			long srvcCharge=0;
			long garageCharge=0;
			String vacantLetterNo="";
			String strLetterDate = "";
			String strVacantDate = "";
			Date vacantDate = null;
			Date letterDate = null;
			
			String rentFree="Rent Free";
			String rentFree1="Rented";
			
			String Self="Self";
			String SpouseRelativeOther="Spouse/Relative/Other";
			
			String rdotypeOfAccom ="";
			String quarterAllocatedToType ="";
			
			rdotypeOfAccom = (request.getParameter("rdotypeOfAccom")!=null && !request.getParameter("rdotypeOfAccom").equals(""))?request.getParameter("rdotypeOfAccom"):null;
			quarterAllocatedToType = (request.getParameter("rdoQuarterAlloted")!=null && !request.getParameter("rdoQuarterAlloted").equals(""))?request.getParameter("rdoQuarterAlloted"):null;
			
			editMode = (request.getParameter("edit")!=null || !request.getParameter("edit").equals(""))?request.getParameter("edit"):"";
			
			
			if(rdotypeOfAccom.equals("true") && rdotypeOfAccom!=null && !rdotypeOfAccom.equals(""))
			{
				rdotypeOfAccom=rentFree;
			}
			else
			{
				rdotypeOfAccom=rentFree1;
			}
			
			if(quarterAllocatedToType.equals("true") && quarterAllocatedToType!=null && !quarterAllocatedToType.equals(""))
			{
				quarterAllocatedToType=Self;
			}
			else
			{
				quarterAllocatedToType=SpouseRelativeOther;
			}
			
			
			logger.info("rdotypeOfAccom***********"+rdotypeOfAccom);
			logger.info("rdoQuarterAlloted***********"+quarterAllocatedToType);
			
			objectArgs.put("rdotypeOfAccom", rdotypeOfAccom);
			objectArgs.put("quarterAllocatedToType", quarterAllocatedToType);
			
			
			qtrRent = (request.getParameter("qtrRentAmt")!=null && !request.getParameter("qtrRentAmt").equals(""))?Long.parseLong(request.getParameter("qtrRentAmt")):0;
			srvcCharge = (request.getParameter("qtrSrvcChrgeAmt")!=null && !request.getParameter("qtrSrvcChrgeAmt").equals(""))?Long.parseLong(request.getParameter("qtrSrvcChrgeAmt")):0;
			garageCharge = (request.getParameter("qtrGarageAmt")!=null && !request.getParameter("qtrGarageAmt").equals(""))?Long.parseLong(request.getParameter("qtrGarageAmt")):0;
			
			vacantLetterNo = request.getParameter("vacantLetterNo")!=null && !request.getParameter("vacantLetterNo").equals("")?request.getParameter("vacantLetterNo"):"";
			strLetterDate = (request.getParameter("letterDate")!=null && !request.getParameter("letterDate").equals(""))?request.getParameter("letterDate"):null;
			strVacantDate = (request.getParameter("vacantDate")!=null && !request.getParameter("vacantDate").equals(""))?request.getParameter("vacantDate"):null;
			
			logger.info("strLetterDate***********"+strLetterDate);
			
			
			if(strLetterDate!=null && !strLetterDate.equals("")) 
			{
				letterDate = sdf.parse(strLetterDate);
				
				hrEssQtrMst.setVacantOrderDate(letterDate);
			}
			
			if(strVacantDate!=null)
			{
				if(strVacantDate.equals(""))
					vacantDate = null;
				else
					vacantDate = sdf.parse(strVacantDate);
			}
			
			
			logger.info("vacantLetterNo***********"+vacantLetterNo);
			logger.info("letterDate***********"+letterDate);
			logger.info("vacantDate***********"+vacantDate);
			
			
			
			hrEssQtrMst.setQuarterRent(qtrRent);
			hrEssQtrMst.setServiceCharge(srvcCharge);
			hrEssQtrMst.setGarageCharge(garageCharge);
			hrEssQtrMst.setVacantLetterNo(vacantLetterNo);
			
			hrEssQtrMst.setAllocationEndDate(vacantDate);
			//ended
			String qtrCmnAddress = "quaterAddress";
			//Added By Varun 
			String ownHouse="0";
			if(StringUtility.getParameter("OwnHouse",request)!= null && !StringUtility.getParameter("OwnHouse",request).equals(""))
			{
				ownHouse = "1";
			}
			
			logger.info("Own House"+ownHouse);
			//Ended By Varun
			
			if (request.getParameter("USER_ID_empQuater")!=null && request.getParameter("USER_ID_empQuater")!="")
			    userId = Long.parseLong(request.getParameter("USER_ID_empQuater"));
			logger.info("in vogen userid id=====>"+userId);
			quarterType = (request.getParameter("quarterType")!=null && !request.getParameter("quarterType").equals(""))?Long.parseLong(request.getParameter("quarterType")):0;
			custodianType = (request.getParameter("custodianType")!=null && !request.getParameter("custodianType").equals(""))?Long.parseLong(request.getParameter("custodianType")):0;
			logger.info("custodianType is**********"+custodianType);
			
			//quaterType = Long.parseLong(request.getParameter("quaterType"));
			//quarterName = (request.getParameter("quarterName")!=null || !request.getParameter("quarterName").equals(""))?request.getParameter("quarterName"):"";
		//	quarterRateType = (request.getParameter("quarterRateType")!=null || !request.getParameter("quarterRateType").equals(""))?request.getParameter("quarterRateType"):"";
			
			allocStartDate = (request.getParameter("allocStartDate")!=null && !request.getParameter("allocStartDate").equals(""))?request.getParameter("allocStartDate"):"";
			allocEndDate = (request.getParameter("allocEndDate")!=null && !request.getParameter("allocEndDate").equals(""))?request.getParameter("allocEndDate"):"";
			possessionDate = (request.getParameter("possessionDate")!=null && !request.getParameter("possessionDate").equals(""))?request.getParameter("possessionDate"):"";
			
			
			
			//added by ravysh
			String FromBasicDtlsNew = (request.getParameter("FromBasicDtlsNew")!=null?(String)request.getParameter("FromBasicDtlsNew"):"");
			String otherId = (request.getParameter("otherId")!=null?(String)request.getParameter("otherId"):"");
			
			objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);
	        objectArgs.put("otherId",otherId);
			if(!allocStartDate.equals("") && allocStartDate!=null)
			dtAllocStartDate = sdf.parse(allocStartDate);
			if(!possessionDate.equals("") && possessionDate!=null)
			dtPossessionDate = sdf.parse(possessionDate);
			if(!allocEndDate.equals("") && allocEndDate!=null) {
				dtAllocEndDate = sdf.parse(allocEndDate);
			}
			else 
				dtAllocEndDate=null;
			if(editMode!=null&&editMode.equalsIgnoreCase("Y"))
			{
				logger.info("The Value of QuaterId is:-"+request.getParameter("quarterId"));
				long quarterId = (request.getParameter("quarterId")!=null && !request.getParameter("quarterId").equals(""))?Long.parseLong(request.getParameter("quarterId")):0;
				objectArgs.put("quarterId", quarterId);
			
			}
			
			if(objectArgs.containsKey("quaterAddress"))
			{
				logger.info("Inside Birth Addresss VoGen");
				CmnAddressMst cmnAddressMst= (CmnAddressMst) objectArgs.get("quaterAddress");
				hrEssQtrMst.setCmnAddressMst(cmnAddressMst);
				logger.info("Quater Id IN VOGEN :::: "+cmnAddressMst.getArea());
			}
			// For AddressMst
			retObj = serv.executeService("ADDRESS_VOGENERATOR", objectArgs);
			objectArgs = (Map) retObj.getResultValue();
			
			//hrEssQtrMst.setQuarterName(quarterName);
			hrEssQtrMst.setAllocationStartDate(dtAllocStartDate);
			//hrEssQtrMst.setPossessionDate(dtPossessionDate);
			
			
			objectArgs.put("editMode", editMode);
			objectArgs.put("userId", userId);
			objectArgs.put("quarterType", quarterType);
			objectArgs.put("custodianType", custodianType);
			//objectArgs.put("quarterRateType", quarterRateType);
			objectArgs.put("ownHouse", ownHouse);
			objectArgs.put("dtAllocStartDate", dtAllocStartDate);
			
			
			
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			
			
			
			editMode = StringUtility.getParameter("edit",request);
			long quarterId1 = 0; 
			if(editMode.equalsIgnoreCase("N")) 
			{		
				objectArgs.put("edit","N");
			}
	        else if(editMode.equalsIgnoreCase("Y")) 
	        {         
			 String quarterId = StringUtility.getParameter("quarterId", request);
			 if( quarterId!=null && !quarterId.equals("")){
				 quarterId1 = Long.parseLong(quarterId);
			  }
//			 Address
			 retObj = serv.executeService("EDIT_ADDRESS_VOGENERATOR", objectArgs);
			 hrEssQtrMst.setQuarterId(quarterId1);
			 objectArgs.put("edit","Y");
	        }
			objectArgs.put("hrEssQtrMst", hrEssQtrMst);
		}
		
		
		
		catch (Exception e) {									
			logger.info("Exception Ocuures...Quater Details");
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			retObj.setResultValue(objectArgs);
			retObj.setResultCode(-1);
			retObj.setViewName("errorInsert");		
			logger.error("Error is: "+ e.getMessage());	
		}
		return retObj;
	}
}
