package com.tcs.sgv.eis.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;




public class OtherDetailVOGen extends ServiceImpl 
	implements VOGeneratorService{
	
	Log logger = LogFactory.getLog(getClass());
	//static int i;
	
	
	public ResultObject generateOtherDetail(Map objServiceArgs) 
	{
		Calendar calTime = Calendar.getInstance();
		logger.info("The Inintial time for the Other Details' VOGEN is:-"+calTime.getTimeInMillis());
		logger.info("Inside OtherDetailVOGen--------->");		
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
		
		try{
			HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
			HrEisOtherDtls otherDtlsVO = new HrEisOtherDtls();
			
			
			long empId=0;
			long sgdMapId=0;			
			long initialBasic=0;
			long cityId=0;
			String ishandicapped="";
			long otherId=0;
			long quaterId=0;
			long incomeTax=0;
			long medAllowance = 0;
			//added by Ankit Bhatt for checking the AIS emp has availed SIS or not
			int isSis = 0;
			//ended
			int isAvailedHRA=0; 
			long uniformAvailed = 0;
			String sis1979Flag="n";
			String FamilyPlannig="n";
			String edit="";
			Date incrementDate = null;
			Date effectiveDate = null;
			int FamilyPlnAmt=0;
			
			String PreviewBill="";
			String MergedScreen="";
			
			//// parameters for paybill update
			String updatePaybillFlg="n";
			int paybillMonth=0;
			int paybillYear=0;
			
			//added by japen
			int  ATS30=0;
			int  ATS50=0;
			int  force100=0;
			int  force25=0;
			//ended by japen
			
			if(StringUtility.getParameter("updatePaybillFlg",request)!= null && !StringUtility.getParameter("updatePaybillFlg",request).equals(""))
			{
				updatePaybillFlg=StringUtility.getParameter("updatePaybillFlg",request);
			}
			if(StringUtility.getParameter("paybillMonth",request)!= null && !StringUtility.getParameter("paybillMonth",request).equals(""))
			{
				paybillMonth=Integer.parseInt(StringUtility.getParameter("paybillMonth",request));
			}
			if(StringUtility.getParameter("paybillYear",request)!= null && !StringUtility.getParameter("paybillYear",request).equals(""))
			{
				paybillYear=Integer.parseInt(StringUtility.getParameter("paybillYear",request));
			}
			logger.info("=================== "+StringUtility.getParameter("chkBxIsAvailedHRA",request));
			
			//added by ravysh
			
			if(StringUtility.getParameter("PreviewBill",request)!= null && !StringUtility.getParameter("PreviewBill",request).equals(""))
			{
				PreviewBill=StringUtility.getParameter("PreviewBill",request);
				
			}
			
			if(StringUtility.getParameter("MergedScreen",request)!= null && !StringUtility.getParameter("MergedScreen",request).equals(""))
			{
				MergedScreen=StringUtility.getParameter("MergedScreen",request);
				
			}
			
			
			if(StringUtility.getParameter("chkBxIsAvailedHRA",request)!= null && !StringUtility.getParameter("chkBxIsAvailedHRA",request).equals(""))
			{
				isAvailedHRA=Integer.parseInt(StringUtility.getParameter("chkBxIsAvailedHRA",request));
				
			}
			
			
			objServiceArgs.put("updatePaybillFlg",updatePaybillFlg);
			objServiceArgs.put("paybillMonth",paybillMonth);
			objServiceArgs.put("paybillYear",paybillYear);
			
			objServiceArgs.put("PreviewBill",PreviewBill);
			
			objServiceArgs.put("MergedScreen",MergedScreen);
			
			
			if(StringUtility.getParameter("empCity",request)!= null && !StringUtility.getParameter("empCity",request).equals(""))
			{
				cityId=Long.parseLong(StringUtility.getParameter("empCity",request));
			}
			if(StringUtility.getParameter("incometax",request)!= null && !StringUtility.getParameter("incometax",request).equals(""))
			{
				incomeTax=Long.parseLong(StringUtility.getParameter("incometax",request));
			}
			if(StringUtility.getParameter("is_handicapped",request)!= null)
			{
				ishandicapped=StringUtility.getParameter("is_handicapped",request);
			}
			
			//added by manish
			/*long payCommissionCombo=0;
			if(StringUtility.getParameter("payCommissionCombo",request)!= null && !StringUtility.getParameter("payCommissionCombo",request).equals(""))
			{
				payCommissionCombo=Long.valueOf(StringUtility.getParameter("payCommissionCombo",request));
			}
			
			logger.info("Pay Commission Combo Value is"+payCommissionCombo);*/
			//ended by manish 
			if(StringUtility.getParameter("sgdMapId",request)!= null && !StringUtility.getParameter("sgdMapId",request).equals(""))
			{
			sgdMapId=Long.parseLong(StringUtility.getParameter("sgdMapId",request));
			logger.info("sgdMapId " + sgdMapId);
			}
		/*	if(payCommissionCombo==5)
			{
				sgdMapId=Long.parseLong(StringUtility.getParameter("sgdMapId_fifth",request));
				logger.info("sgdMapId -------------in vogen is "+sgdMapId);
			}
		*/	
			if(StringUtility.getParameter("Employee_ID_EmpOtherDtls",request)!= null && !StringUtility.getParameter("Employee_ID_EmpOtherDtls",request).equals(""))
			{
				empId=Long.parseLong(StringUtility.getParameter("Employee_ID_EmpOtherDtls",request));
			}
			else if(StringUtility.getParameter("emp_id",request)!= null && !StringUtility.getParameter("emp_id",request).equals(""))
			{
				empId=Long.parseLong(StringUtility.getParameter("emp_id",request));
			}	
			
			
			
			if(StringUtility.getParameter("initialBasic",request)!= null)
			{
				initialBasic=Long.parseLong(StringUtility.getParameter("initialBasic",request));
			}
			/*if(payCommissionCombo==5)
			{
				initialBasic=Long.parseLong(StringUtility.getParameter("BasicPay",request));
				logger.info("in vogen basic is "+initialBasic);
			}*/
			if(StringUtility.getParameter("IncrementDate", request)!=null && !StringUtility.getParameter("IncrementDate", request).equals(""))
			{
				incrementDate = StringUtility.convertStringToDate(StringUtility.getParameter("IncrementDate", request));
			}
			logger.info("increment date is from vogen=====>"+incrementDate);
			if(StringUtility.getParameter("effctivedate", request)!=null && !StringUtility.getParameter("effctivedate", request).equals(""))
			{
				effectiveDate = StringUtility.convertStringToDate(StringUtility.getParameter("effctivedate", request));
			}
			logger.info("effectiveDate date is from vogen=====>"+effectiveDate);

			if(StringUtility.getParameter("edit", request)!=null && !StringUtility.getParameter("edit", request).equals(""))
			{	
				edit= StringUtility.getParameter("edit", request);
			}
			/*if(StringUtility.getParameter("quaterType", request)!= null && !StringUtility.getParameter("quaterType", request).equals(""))
			{
				quaterId=Long.parseLong(StringUtility.getParameter("quaterType", request));
			}*/
			//Added by Paurav for Medical Allowance
			if(StringUtility.getParameter("medAllowance",request)!= null && !StringUtility.getParameter("medAllowance",request).equals(""))
			{
				logger.info("Pay Medical Allowance " + StringUtility.getParameter("medAllowance",request));
				medAllowance = Long.parseLong(StringUtility.getParameter("medAllowance",request));
			}
			if(StringUtility.getParameter("uniformAvailed",request)!= null && !StringUtility.getParameter("uniformAvailed",request).equals(""))
			{
				logger.info("Uniform Availed " + StringUtility.getParameter("uniformAvailed",request));
				uniformAvailed = Long.parseLong(StringUtility.getParameter("uniformAvailed",request));
			}
			if(StringUtility.getParameter("sis1979Flag",request)!= null && !StringUtility.getParameter("sis1979Flag",request).equals(""))
			{
				sis1979Flag = "y";
			}
			if(StringUtility.getParameter("FamilyPlannig", request)!=null && !StringUtility.getParameter("FamilyPlannig", request).equals(""))
			{
				FamilyPlannig="y";
			}
			if(StringUtility.getParameter("FamilyPlnAmt",request)!= null && !StringUtility.getParameter("FamilyPlnAmt",request).equals(""))
			{
				FamilyPlnAmt=Integer.parseInt(StringUtility.getParameter("FamilyPlnAmt",request));
			}
			
			//Ended by Paurav
			
			//added by japen pathak  
			if(StringUtility.getParameter("ATS30",request)!= null && !StringUtility.getParameter("ATS30",request).equals(""))
			{
				logger.info("ATS30 for Emp " + StringUtility.getParameter("ATS30",request));
				ATS30 =Integer.parseInt(StringUtility.getParameter("ATS30",request));
			}
			
			if(StringUtility.getParameter("ATS50",request)!= null && !StringUtility.getParameter("ATS50",request).equals(""))
			{
				logger.info("ATS50 Emp " + StringUtility.getParameter("ATS50",request));
				ATS50 =Integer.parseInt(StringUtility.getParameter("ATS50",request));
			}
			
			if(StringUtility.getParameter("force100",request)!= null && !StringUtility.getParameter("force100",request).equals(""))
			{
				logger.info("force100 for   Emp " + StringUtility.getParameter("force100",request));
				force100 =Integer.parseInt(StringUtility.getParameter("force100",request));
			}
			
			if(StringUtility.getParameter("force25",request)!= null && !StringUtility.getParameter("force25",request).equals(""))
			{
				logger.info("force25 for   Emp " + StringUtility.getParameter("force25",request));
				force25 =Integer.parseInt(StringUtility.getParameter("force25",request));
			}
			
			if(StringUtility.getParameter("isSis",request)!= null && !StringUtility.getParameter("isSis",request).equals(""))
			{
				logger.info("Sis for AIS Emp " + StringUtility.getParameter("isSis",request));
				isSis =Integer.parseInt(StringUtility.getParameter("isSis",request));
			}
			
			String orderDataFlag="",remarks="",orderNo="";
			long reason=0;
			Date orderDate=null;
			if(StringUtility.getParameter("orderDataFlag",request)!= null && !StringUtility.getParameter("orderDataFlag",request).equals(""))
			{
				orderDataFlag = StringUtility.getParameter("orderDataFlag",request);
				if(orderDataFlag.equals("Y"))
				{
					if(StringUtility.getParameter("orderNo",request)!= null && !StringUtility.getParameter("orderNo",request).equals(""))
					{
						orderNo=StringUtility.getParameter("orderNo",request);
					}
					if(StringUtility.getParameter("Reason",request)!= null && !StringUtility.getParameter("Reason",request).equals(""))
					{
						reason=Long.parseLong(StringUtility.getParameter("Reason",request));
					}
					if(StringUtility.getParameter("Remarks",request)!= null && !StringUtility.getParameter("Remarks",request).equals(""))
					{
						remarks=StringUtility.getParameter("Remarks",request);
					}
					if(StringUtility.getParameter("orderDate",request)!= null && !StringUtility.getParameter("orderDate",request).equals(""))
					{
						String orderDateStr= StringUtility.getParameter("orderDate",request);
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						orderDate= sdf.parse(orderDateStr);
					}
				}
				
			}
			
			objServiceArgs.put("orderNo",orderNo);
			objServiceArgs.put("reason",reason);
			objServiceArgs.put("remarks",remarks);
			objServiceArgs.put("orderDate",orderDate);
			objServiceArgs.put("orderDataFlag",orderDataFlag);
			
			//ended by Japen pathak
			objServiceArgs.put("empId",empId);
			objServiceArgs.put("sgdMapId",sgdMapId);
			objServiceArgs.put("FamilyPlnAmt",FamilyPlnAmt);
			//objServiceArgs.put("quaterId",quaterId);
			
			if(!edit.equals(null) && !edit.equalsIgnoreCase("Y"))
			{
				logger.info(" insert mode of other detail from vogen ");
				otherDtlsVO.setCity(cityId);
				otherDtlsVO.setPhyChallenged(ishandicapped);
				otherDtlsVO.setotherCurrentBasic(initialBasic);
				otherDtlsVO.setIncomeTax(incomeTax);
				otherDtlsVO.setMedAllowance(medAllowance);
				otherDtlsVO.setUniformAvailed(uniformAvailed);
				//otherDtlsVO.setSis1979Flag(sis1979Flag);
				//otherDtlsVO.setOthFamilyPlanning(FamilyPlannig);
				otherDtlsVO.setIncrementDate(incrementDate);
				otherDtlsVO.setCommissionAcceptanceDate(effectiveDate);
				//otherDtlsVO.setFamilyPlnAmt(FamilyPlnAmt);
				otherDtlsVO.setIsAvailedHRA(isAvailedHRA);
				//otherDtlsVO.setAtsIncentive30(ATS30);
				//otherDtlsVO.setAtsIncentive50(ATS50);
				//otherDtlsVO.setForce1Incentive100(force100);
				//otherDtlsVO.setAtsIncentive30(force25);
				
				//added by Ankit Bhatt
				//otherDtlsVO.setIsSis(isSis);
				logger.info(">>>>>>>>>>>>>>>>>>"+isAvailedHRA);
				//ended
				objServiceArgs.put("edit","N");
			}
			else {
				otherId=Long.parseLong(StringUtility.getParameter("otherId",request));
				objServiceArgs.put("otherId",otherId);
				objServiceArgs.put("ishandicapped",ishandicapped);
				objServiceArgs.put("initialBasic",initialBasic);
				objServiceArgs.put("cityId",cityId);
				objServiceArgs.put("incomeTax",incomeTax);
	//			Added by Paurav for Medical Allowance
				objServiceArgs.put("medAllowance",medAllowance);
				objServiceArgs.put("uniformAvailed",uniformAvailed);
				objServiceArgs.put("force100",force100);
				objServiceArgs.put("force25",force25);
				objServiceArgs.put("ATS30",ATS30);
				objServiceArgs.put("ATS50",ATS50);
				//Ended by Paurav
                //added by Ankit Bhatt
				objServiceArgs.put("isSis",isSis);
				//ended
				objServiceArgs.put("sis1979Flag",sis1979Flag);
				objServiceArgs.put("FamilyPlannig",FamilyPlannig);
				objServiceArgs.put("incrementDate",incrementDate);
				objServiceArgs.put("effectiveDate",effectiveDate);
				otherDtlsVO.setIsAvailedHRA(isAvailedHRA);
				objServiceArgs.put("edit","Y");
			logger.info("++++++++++++++++++"+isAvailedHRA);
			}
				
			/*Date createdDate = new Date();*/
			/*otherDtlsVO.setCity(cityId);
			otherDtlsVO.setPhyChallenged(ishandicapped);
			otherDtlsVO.setotherCurrentBasic(initialBasic);
			otherDtlsVO.setHrEisEmpMst(hrEisObj);
			otherDtlsVO.setHrEisSgdMpg(hrEisSgdMpg);
	
			otherDtlsVO.setHrEisDeptMst(deptObj);
			otherDtlsVO.setUpdatedDate(createdDate);
			otherDtlsVO.setCreatedDate(createdDate);
			otherDtlsVO.setCmnDatabaseMst(cmnDatabaseMst);
			otherDtlsVO.setOrgPostMstByUpdatedByPost(orgPostMst);
			otherDtlsVO.setOrgPostMstByCreatedByPost(orgPostMst);
			otherDtlsVO.setOrgUserMstByUpdatedBy(orgUserMst);
			otherDtlsVO.setOrgUserMstByCreatedBy(orgUserMst);	*/
			
			//objServiceArgs.put("editMode",editMode);
			objServiceArgs.put("incrementDate",incrementDate);		
			objServiceArgs.put("effectiveDate",effectiveDate);		
			objServiceArgs.put("otherDtlsVO",otherDtlsVO);
			//objServiceArgs.put("incrementDate",incrementDate);
			retObj.setResultValue(objServiceArgs);
			retObj.setResultCode(ErrorConstants.SUCCESS);
			Calendar endCalTime = Calendar.getInstance();
			logger.info("The End time for the Other Details' VOGEN is:-"+endCalTime.getTimeInMillis());
		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			retObj.setThrowable(e);
			retObj.setResultCode(-1);
			retObj.setViewName("errorPage");
			return retObj;
		}
		return retObj;
	}
	
	public ResultObject generateMap(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	//public ResultObject 
}
