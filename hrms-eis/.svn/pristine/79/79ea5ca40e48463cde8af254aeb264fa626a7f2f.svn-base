package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.lowagie.tools.plugins.Txt2Pdf;
import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;

public class EmpAllwMapVOGenImpl extends ServiceImpl{
	
	Log logger = LogFactory.getLog(getClass());
	public ResultObject generateMapForInsertEmpAllwMapMaster(Map objectArgs) 
	{
		try{
		logger.info("EmpAllwMapVOGenImpl generateMapForInsertEmpAllwMapMaster Called");
		ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		
		ResourceBundle rs = ResourceBundle.getBundle("resources.Payroll");
		long PF_ID = Long.parseLong(rs.getString("PF"));
		long vpfId = Long.parseLong(rs.getString("vpfId"));
		long jeepRentId = Long.parseLong(rs.getString("JeepRent"));
		long itId =  Long.parseLong(rs.getString("itId"));
		
		long techAllowId = Long.parseLong(rs.getString("TechAllowId"));
		long GpfGrpAbcId=Long.parseLong(rs.getString("GpfGrpAbcId"));
		long armId=Long.parseLong(rs.getString("ArmId"));
		long conveyanceId=Long.parseLong(rs.getString("conveyanceId"));
		long cidId=Long.parseLong(rs.getString("cidId"));
		long bmiId=Long.parseLong(rs.getString("bmiId"));
		long cashId=Long.parseLong(rs.getString("cashId"));
		long armourerId=Long.parseLong(rs.getString("armourerId"));

		logger.info("conveyanceId "+conveyanceId);
		logger.info("cidId"+cidId);
		logger.info("bmiId"+bmiId);
		logger.info("cashId"+cashId);
		logger.info("armourerId"+armourerId);
		
		//manish
		long emergencyId=Long.parseLong(rs.getString("emergencyId").trim());
		long ESISId=Long.parseLong(rs.getString("ESISId").trim());
		long extraLectureId=Long.parseLong(rs.getString("extraLectureId").trim());
		long fitnessId=Long.parseLong(rs.getString("fitnessId").trim());
		long gallantryId=Long.parseLong(rs.getString("gallantryId").trim());
		
		long kitId=Long.parseLong(rs.getString("kitId").trim());
		long licenseId=Long.parseLong(rs.getString("licenseId").trim());
		long mechanicalId=Long.parseLong(rs.getString("mechanicalId").trim());
		long medicalEducationId=Long.parseLong(rs.getString("medicalEducationId").trim());
		long messId=Long.parseLong(rs.getString("messId").trim());
		
		long naxelAreaId=Long.parseLong(rs.getString("naxelAreaId").trim());
		long nonPracticingId=Long.parseLong(rs.getString("nonPracticingId").trim());
		
		long sumptuaryId=Long.parseLong(rs.getString("sumptuaryId").trim());
		long projectId=Long.parseLong(rs.getString("projectId").trim());
		
		long specialDutyId=Long.parseLong(rs.getString("specialDutyId").trim());
		long additionalPayId=Long.parseLong(rs.getString("additionalPayId").trim());
		long uniformId=Long.parseLong(rs.getString("uniformId").trim());
		//manish
		
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		
		//// parameters for paybill update
		String updatePaybillFlg="n";
		int paybillMonth=0;
		int paybillYear=0;
		
		//added by ravysh
		String PreviewBill="";
		String MergedScreen="";
		
		if(StringUtility.getParameter("PreviewBill",request)!= null && !StringUtility.getParameter("PreviewBill",request).equals(""))
		{
			PreviewBill=StringUtility.getParameter("PreviewBill",request);
			
		}
		
		if(StringUtility.getParameter("MergedScreen",request)!= null && !StringUtility.getParameter("MergedScreen",request).equals(""))
		{
			MergedScreen=StringUtility.getParameter("MergedScreen",request);
			
		}
		
		
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
		
		objectArgs.put("updatePaybillFlg",updatePaybillFlg);
		objectArgs.put("paybillMonth",paybillMonth);
		objectArgs.put("paybillYear",paybillYear);
		
		objectArgs.put("PreviewBill",PreviewBill);
		objectArgs.put("MergedScreen",MergedScreen);
		
		if(StringUtility.getParameter("batchupdate", request)!=null&&!StringUtility.getParameter("batchupdate", request).equals("")&&StringUtility.getParameter("batchupdate", request).equals("true"))
		{

			long empId = 0;
			if(!StringUtility.getParameter("txtEmpId", request).equals(""))
			{			
	    		empId = Long.parseLong(StringUtility.getParameter("txtEmpId", request));
			}
			int size=0;
			if(StringUtility.getParameter("size", request)!=null&&!StringUtility.getParameter("size", request).equals(""))
			{			
				size = Integer.parseInt(StringUtility.getParameter("size", request));
			}
			String editMode = StringUtility.getParameter("edit",request);
			logger.info("Edit Mode is  " + editMode);

			logger.info("SIZE IN VOGEN ALLOW IS "+size);
			HrPayEmpallowMpg empAllwMpg = new HrPayEmpallowMpg();
			for(int j=1;j<=size;j++)
			{
				long empAllowId = 0;

				if(!StringUtility.getParameter("Allowcode"+j, request).equals(""))
	     		{     			
	        		empAllowId = Long.parseLong(StringUtility.getParameter("Allowcode"+j, request));
	     		}	
				logger.info("**************empAllowId*********"+empAllowId+" in full size of "+size);
				if(editMode.equalsIgnoreCase("N")) 
				{						
					empAllwMpg = new HrPayEmpallowMpg();
					objectArgs.put("edit"+j,"N");
				}
				else
				{ 
					long allowCode = 0; 
					String allowId = "";
	        	
	           
					if(!StringUtility.getParameter("txtAllowCode"+empAllowId, request).equals(""))
					{     			
						allowId = StringUtility.getParameter("txtAllowCode"+empAllowId, request);
					}	
					logger.info("**************allowId*********"+allowId);

					if( allowId!=null&& !allowId.equals("")&&!allowId.equals("txtAllowCode")){
						allowCode = Long.parseLong(allowId);
						objectArgs.put("edit"+j,"Y");
					}
					else
						objectArgs.put("edit"+j,"N");
					
					empAllwMpg = new HrPayEmpallowMpg();		
			 
					logger.info("Allow code in vogen is "+allowCode);
					empAllwMpg.setAllowCode(allowCode);
					// empAllwMpg.setAllowCode(empAllowId);
					//objectArgs.put("edit"+j,"Y");
				}
			
				double allowAmount=0;		
			     							     		
			/*if(!StringUtility.getParameter("txtEmpAllowId", request).equals(""))
			{		
				empAllowId = Long.parseLong(StringUtility.getParameter("txtEmpAllowId", request));
			}*/
			
			
				if(StringUtility.getParameter("txthidden"+empAllowId, request)!=null&&!StringUtility.getParameter("txthidden"+empAllowId, request).equals(""))
				{			
					 allowAmount = Double.parseDouble(StringUtility.getParameter("txthidden"+empAllowId, request).trim());
				}
	    	
				logger.info("**************allowAmount*********"+allowAmount);
				
				 empAllwMpg.setEmpCurrentStatus(1); //status = active
			 
						
			 HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
			 GenericDaoHibernateImpl<HrPayAllowTypeMst, Long> genDao = new GenericDaoHibernateImpl<HrPayAllowTypeMst, Long>(HrPayAllowTypeMst.class);
			 genDao.setSessionFactory(serv.getSessionFactory());
			 hrPayAllowTypeMst = genDao.read(empAllowId);
			 
			 logger.info( " allowance id object for "+empAllowId + " is "+hrPayAllowTypeMst );
			 //hrPayAllowTypeMst.setAllowCode(empAllowId);
			 
			 Date dt = new Date();
			 int month =dt.getMonth()+1;
			 int year = dt.getYear()+1900;
			 empAllwMpg.setMonth(month);
			 empAllwMpg.setYear(year);
			 empAllwMpg.setEmpAllowAmount(allowAmount);
			 empAllwMpg.setHrPayAllowTypeMst(hrPayAllowTypeMst);
			 		 		 		 		 
			 Date sysdate = new Date();															
			 empAllwMpg.setUpdatedDate(sysdate);
			 						
			objectArgs.put("empAllwMpg"+j,empAllwMpg);
			logger.info("empAllwMpg"+j+"  object in vogen is   "+objectArgs.get("empAllwMpg"+j));
		}			
			objectArgs.put("size",size);
			objectArgs.put("batchupdate","true");
			
			
			//by manoj for loan merging 
			int loanSize = 0;
			if(StringUtility.getParameter("loanSize", request)!=null&&!StringUtility.getParameter("loanSize", request).equals(""))
			{			
				loanSize = Integer.parseInt(StringUtility.getParameter("loanSize", request).trim());
			}
			
			objectArgs.put("loanSize",loanSize);
			
			for(int j=1;j<=loanSize;j++)
			{
				long empLoanId = 0;
				String loanAmtType ="";
				long emiAmount=0;

				if(!StringUtility.getParameter("loanTxtId"+j, request).equals(""))
	     		{     			
					empLoanId = Long.parseLong(StringUtility.getParameter("loanTxtId"+j, request));
	     		}
				if(!StringUtility.getParameter("loanAmtType"+j, request).equals(""))
	     		{     			
					loanAmtType =StringUtility.getParameter("loanAmtType"+j, request);
	     		}
				if(!StringUtility.getParameter("loanTxt"+j, request).equals(""))
	     		{     			
					emiAmount = Long.parseLong(StringUtility.getParameter("loanTxt"+j, request));
	     		}
				
				logger.info("empLoanId "+empLoanId+" emiAmount "+emiAmount+"loanAmtType "+loanAmtType);
				
				objectArgs.put("empLoanId"+j,empLoanId);
				objectArgs.put("emiAmount"+j,emiAmount);
				objectArgs.put("loanAmtType"+j,loanAmtType);
			}
			//end by manoj for loan merging
			
			//by manoj for entering the deduction detail values from the screen
			//started by japen
			//ResourceBundle payroll = ResourceBundle.getBundle("../resources/Payroll");
			
			
			String pfType =""; 
			long gpfAmt =0;
			long pfId=0;
			long gpfTextAmt =0;
			long jeepRent = 0;
			long It_Val=0;
			long gpfGrpDVal=0;
			int gpfGrpDId=36;
			int hrrId=28;
			long hrrVal=0;
			int ltaId=34;
			long ltaVal=5;
			long GpfGrpAbcval=0;
			
			
			long tech_allow_value = 0;
			
			long armVal=0;
			long armourerVal=0;
			long bmiVal=0;
			long cidVal=0;
			long cashVal=0;
			long conveyanceVal=0;
			
			//manish
			long emergencyVal=0;
			long ESISVal=0;
			long extraLectureVal=0;
			long fitnessVal=0;
			long gallantryVal=0;
			long kitVal=0;
			long licenseVal=0;
			long mechanicalVal=0;
			long medicalEducationVal=0;
			long messVal=0;
			long naxelAreaVal=0;
			long nonPracticingVal=0;
			long sumptuaryVal=0;
			long projectVal=0;
			long specialDutyVal=0;
			long additionalPayVal=0;
			long uniformVal=0;
		
			
			long gpfIASOtherState=0;
			long gpfIAS=0;
			long gpfIPS=0;
			long gpfIFS=0;
			long serviceCharge=0;
			long otherdeduction=0;
			long MahaStateLifeInsurance=0;
			
			if(!StringUtility.getParameter("txtt75", request).equals(""))
     		{     			
				gpfIASOtherState = Long.parseLong(StringUtility.getParameter("txtt75", request));
     		}
			
			if(!StringUtility.getParameter("txtt76", request).equals(""))
     		{     			
				gpfIAS = Long.parseLong(StringUtility.getParameter("txtt76", request));
     		}
			if(!StringUtility.getParameter("txtt77", request).equals(""))
			{     			
				gpfIPS = Long.parseLong(StringUtility.getParameter("txtt77", request));
     		}
			
			if(!StringUtility.getParameter("txtt78", request).equals(""))
     		{     			
				gpfIFS = Long.parseLong(StringUtility.getParameter("txtt78", request));
     		}
			
			if(!StringUtility.getParameter("txtt80", request).equals(""))
     		{     			
				serviceCharge = Long.parseLong(StringUtility.getParameter("txtt80", request));
     		}
			
			if(!StringUtility.getParameter("txtt81", request).equals(""))
     		{     			
				otherdeduction = Long.parseLong(StringUtility.getParameter("txtt81", request));
     		}
			
			if(!StringUtility.getParameter("txtt87", request).equals(""))
     		{     			
				MahaStateLifeInsurance = Long.parseLong(StringUtility.getParameter("txtt87", request));
     		}
			
			//manish
			
			
			if(!StringUtility.getParameter("pfType", request).equals(""))
     		{     			
				pfType = StringUtility.getParameter("pfType", request);
     		}
			if(!StringUtility.getParameter("gpfAmt", request).equals(""))
     		{     			
				gpfAmt = Long.parseLong(StringUtility.getParameter("gpfAmt", request));
     		}
			if(!StringUtility.getParameter("pfId", request).equals(""))
     		{     			
				pfId = Long.parseLong(StringUtility.getParameter("pfId", request));
     		}
			if(!StringUtility.getParameter("txtt"+PF_ID, request).equals(""))
     		{     			
				gpfTextAmt = Long.parseLong(StringUtility.getParameter("txtt"+PF_ID, request));
     		}
			if(!StringUtility.getParameter("txtt"+jeepRentId, request).equals(""))
     		{     			
				jeepRent = Long.parseLong(StringUtility.getParameter("txtt"+jeepRentId, request));
     		}
			//Added by Mrugesh
			if(!StringUtility.getParameter("txtt"+itId, request).equals(""))
     		{     			
				It_Val = Long.parseLong(StringUtility.getParameter("txtt"+itId, request));
     		}
			
			//added by ankit bhatt - Maha - Technical Allowance value
			
			if(!StringUtility.getParameter("txt"+techAllowId, request).equals(""))
     		{     			
				tech_allow_value = Long.parseLong(StringUtility.getParameter("txt"+techAllowId, request));
     		}
			
			
			//temp code-need to be removed-By Ankit Bhatt
			if(!StringUtility.getParameter("txtt"+gpfGrpDId, request).equals(""))
     		{     			
				gpfGrpDVal = Long.parseLong(StringUtility.getParameter("txtt"+gpfGrpDId, request));
     		}
			//added by manish
			if(!StringUtility.getParameter("txtt"+hrrId, request).equals(""))
     		{     			
				hrrVal = Long.parseLong(StringUtility.getParameter("txtt"+hrrId, request));
     		}
			if(!StringUtility.getParameter("txt"+ltaId, request).equals(""))
     		{     			
				ltaVal = Long.parseLong(StringUtility.getParameter("txt"+ltaId, request));
				logger.info("lta in vogen"+ltaVal);
     		}
			
			if(!StringUtility.getParameter("txtt"+GpfGrpAbcId, request).equals(""))
     		{     			
				GpfGrpAbcval = Long.parseLong(StringUtility.getParameter("txtt"+GpfGrpAbcId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+armId, request).equals(""))
     		{     			
				armVal = Long.parseLong(StringUtility.getParameter("txt"+armId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+armourerId, request).equals(""))
     		{     			
				armourerVal = Long.parseLong(StringUtility.getParameter("txt"+armourerId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+bmiId, request).equals(""))
     		{     			
				bmiVal = Long.parseLong(StringUtility.getParameter("txt"+bmiId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+cashId, request).equals(""))
     		{     			
				cashVal = Long.parseLong(StringUtility.getParameter("txt"+cashId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+cidId, request).equals(""))
     		{     			
				cidVal = Long.parseLong(StringUtility.getParameter("txt"+cidId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+conveyanceId, request).equals(""))
     		{     			
				conveyanceVal = Long.parseLong(StringUtility.getParameter("txt"+conveyanceId, request));
				
     		}

			
			//manish
			
			if(!StringUtility.getParameter("txt"+emergencyId, request).equals(""))
     		{     			
				emergencyVal = Long.parseLong(StringUtility.getParameter("txt"+emergencyId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+extraLectureId, request).equals(""))
     		{     			
				extraLectureVal = Long.parseLong(StringUtility.getParameter("txt"+extraLectureId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+ESISId, request).equals(""))
     		{     			
				ESISVal = Long.parseLong(StringUtility.getParameter("txt"+ESISId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+fitnessId, request).equals(""))
     		{     			
				fitnessVal = Long.parseLong(StringUtility.getParameter("txt"+fitnessId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+kitId, request).equals(""))
     		{     			
				kitVal = Long.parseLong(StringUtility.getParameter("txt"+kitId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+licenseId, request).equals(""))

     		{     			
				licenseVal = Long.parseLong(StringUtility.getParameter("txt"+licenseId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+mechanicalId, request).equals(""))
     		{     			
				mechanicalVal = Long.parseLong(StringUtility.getParameter("txt"+mechanicalId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+medicalEducationId, request).equals(""))
     		{     			
				medicalEducationVal = Long.parseLong(StringUtility.getParameter("txt"+medicalEducationId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+messId, request).equals(""))
     		{     			
				messVal = Long.parseLong(StringUtility.getParameter("txt"+messId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+naxelAreaId, request).equals(""))
     		{     			
				naxelAreaVal = Long.parseLong(StringUtility.getParameter("txt"+naxelAreaId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+nonPracticingId, request).equals(""))
     		{     			
				nonPracticingVal = Long.parseLong(StringUtility.getParameter("txt"+nonPracticingId, request));
				
     		}
			if(!StringUtility.getParameter("txt"+sumptuaryId, request).equals(""))
     		{     			
				sumptuaryVal = Long.parseLong(StringUtility.getParameter("txt"+sumptuaryId, request));
				
     		}
			
			if(!StringUtility.getParameter("txt"+projectId, request).equals(""))
     		{     			
				projectVal = Long.parseLong(StringUtility.getParameter("txt"+projectId, request));
				
     		}
			
			if(!StringUtility.getParameter("txt"+specialDutyId, request).equals(""))
     		{     			
				specialDutyVal = Long.parseLong(StringUtility.getParameter("txt"+specialDutyId, request));
				
     		}
			
			if(!StringUtility.getParameter("txt"+additionalPayId, request).equals(""))
     		{     			
				additionalPayVal = Long.parseLong(StringUtility.getParameter("txt"+additionalPayId, request));
				
     		}
			
			if(!StringUtility.getParameter("txt"+uniformId, request).equals(""))
     		{     			
				uniformVal = Long.parseLong(StringUtility.getParameter("txt"+uniformId, request));
				
     		}
			
			if(!StringUtility.getParameter("txt"+gallantryId, request).equals(""))
     		{     			
				gallantryVal = Long.parseLong(StringUtility.getParameter("txt"+gallantryId, request));
				
     		}
			
			//ended by manish
			
			//ended by Ankit Bhatt
			//Ended
		
			
			
			objectArgs.put("gallantryId", gallantryId);
			objectArgs.put("gallantryVal", gallantryVal);
		

			
			
			objectArgs.put("pfType",pfType);
			objectArgs.put("gpfAmt",gpfAmt);
			objectArgs.put("pfId",pfId);
			objectArgs.put("gpfTextAmt",gpfTextAmt);
			objectArgs.put("PF_ID",PF_ID);
			objectArgs.put("vpfId",	vpfId);
			objectArgs.put("jeepRent", jeepRent);
			objectArgs.put("jeepRentId", jeepRentId);
			//Added by Mrugesh
			objectArgs.put("itId",itId);


			//manish
			
			objectArgs.put("gpfIASOtherState",gpfIASOtherState);
			objectArgs.put("gpfIAS",gpfIAS);
			objectArgs.put("gpfIPS",gpfIPS);
			objectArgs.put("gpfIFS",gpfIFS);
			objectArgs.put("serviceCharge",serviceCharge);
			objectArgs.put("otherdeduction",otherdeduction);
			logger.info("MahaStateLifeInsurance in vogen "+MahaStateLifeInsurance);
			logger.info("gpfIAS in vogen  "+gpfIAS);
			logger.info("gpfIPS in vogen  "+gpfIPS);
			logger.info("gpfIFS in vogen  "+gpfIFS);
			logger.info("gpfIASOtherState in vogen  "+gpfIASOtherState);
			logger.info("serviceCharge in vogen  "+serviceCharge);
			logger.info("otherdeduction in vogen  "+otherdeduction);
			logger.info("MahaStateLifeInsurance in vogen  "+MahaStateLifeInsurance);
			objectArgs.put("MahaStateLifeInsurance",MahaStateLifeInsurance);
			//manish
			


			objectArgs.put("It_Val", It_Val);
			
			//added by Ankit bhatt  - Temp code
			objectArgs.put("GPF_GRP_D__Val", gpfGrpDVal);
			objectArgs.put("HRR_Val", hrrVal);
			objectArgs.put("ltaId", ltaId);
			objectArgs.put("LTA_Val", ltaVal); 
			objectArgs.put("techAllowId", techAllowId);
			objectArgs.put("tech_allow_value", tech_allow_value);
			objectArgs.put("armVal", armVal);
			objectArgs.put("armId", armId);
			objectArgs.put("GpfGrpAbcId", GpfGrpAbcId);
			objectArgs.put("GpfGrpAbcval", GpfGrpAbcval);
			
		
			
			objectArgs.put("bmiId",bmiId);
			objectArgs.put("bmiVal",bmiVal);
			
			objectArgs.put("cashId", cashId);
			objectArgs.put("cashVal", cashVal);
			
			objectArgs.put("cidId", cidId);
			objectArgs.put("cidVal", cidVal);
			
			objectArgs.put("conveyanceId", conveyanceId);
			objectArgs.put("conveyanceVal", conveyanceVal);
			
			
			
			objectArgs.put("armourerId", armourerId);
			objectArgs.put("armourerVal", armourerVal);
			
			objectArgs.put("emergencyId", emergencyId);
			objectArgs.put("emergencyVal",emergencyVal);
			objectArgs.put("ESISId", ESISId);
			objectArgs.put("ESISVal", ESISVal);
			objectArgs.put("extraLectureId", extraLectureId);
			objectArgs.put("extraLectureVal", extraLectureVal);
			objectArgs.put("fitnessId", fitnessId);
			objectArgs.put("fitnessVal", fitnessVal);
			objectArgs.put("kitId", kitId);
			objectArgs.put("kitVal", kitVal);
			objectArgs.put("licenseId", licenseId);
			objectArgs.put("licenseVal", licenseVal);
			objectArgs.put("mechanicalId", mechanicalId);
			objectArgs.put("mechanicalVal", mechanicalVal);
			objectArgs.put("medicalEducationId", medicalEducationId);
			objectArgs.put("medicalEducationVal", medicalEducationVal);
			objectArgs.put("messId", messId);
			objectArgs.put("messVal", messVal);
			objectArgs.put("naxelAreaId", naxelAreaId);
			objectArgs.put("naxelAreaVal", naxelAreaVal);
			objectArgs.put("nonPracticingId", nonPracticingId);
			objectArgs.put("nonPracticingVal", nonPracticingVal);
			objectArgs.put("sumptuaryId", sumptuaryId);
			objectArgs.put("sumptuaryVal", sumptuaryVal);
			objectArgs.put("projectId", projectId);
			objectArgs.put("projectVal", projectVal);
			objectArgs.put("specialDutyId", specialDutyId);
			objectArgs.put("specialDutyVal", specialDutyVal);
			objectArgs.put("additionalPayId",additionalPayId);
			objectArgs.put("additionalPayVal",additionalPayVal);
			objectArgs.put("uniformId",uniformId);
			objectArgs.put("uniformVal",uniformVal);

			logger.info("armourerId "+armourerId);
			
			logger.info("armourerVal "+armourerVal);
			
			//ended
			//Ended
			
			//end by manoj for entering the deduction detail values from the screen
			
			objectArgs.put("empid",empId);
		}
		else		
		{
		String editMode = StringUtility.getParameter("edit",request);
		
		
		String pfType =""; 
		long gpfAmt =0;
		long pfId=0;
		long gpfTextAmt =0;
		long jeepRent = 0;
		long It_Val=0;
		int sp=0,ltc=0,ir=0,bonus=0,oc=0,con=0,ppay=0, vpf=0;
		if(!StringUtility.getParameter("txt"+15, request).equals(""))
 		{     			
			sp = Integer.parseInt(StringUtility.getParameter("txt"+15, request));
 		}
		if(!StringUtility.getParameter("txt"+34, request).equals(""))
 		{     			
			ltc = Integer.parseInt(StringUtility.getParameter("txt"+34, request));
 		}
		if(!StringUtility.getParameter("txt"+35, request).equals(""))
 		{     			
			ir = Integer.parseInt(StringUtility.getParameter("txt"+35, request));
 		}
		if(!StringUtility.getParameter("txt"+36, request).equals(""))
 		{     			
			bonus = Integer.parseInt(StringUtility.getParameter("txt"+36, request));
 		}
		if(!StringUtility.getParameter("txt"+37, request).equals(""))
 		{     			
			oc = Integer.parseInt(StringUtility.getParameter("txt"+37, request));
 		}
		if(!StringUtility.getParameter("txt"+38, request).equals(""))
 		{     			
			con = Integer.parseInt(StringUtility.getParameter("txt"+38, request));
 		}
		if(!StringUtility.getParameter("txt"+49, request).equals(""))
 		{     			
			ppay = Integer.parseInt(StringUtility.getParameter("txt"+49, request));
 		}
		if(!StringUtility.getParameter("txtt"+45, request).equals(""))
 		{     			
			vpf = Integer.parseInt(StringUtility.getParameter("txtt"+45, request));
 		}
		

		objectArgs.put("itId", itId);
		objectArgs.put("sp", sp);
		objectArgs.put("ltc", ltc);
		objectArgs.put("ir", ir);
		objectArgs.put("bonus", bonus);
		objectArgs.put("oc", oc);
		objectArgs.put("It_Val", It_Val);
		objectArgs.put("con", con);
		objectArgs.put("ppay", ppay);
		objectArgs.put("vpf", vpf);
		
		
		HrPayEmpallowMpg empAllwMpg = null;
		long isChecked = 1;
		
		if(editMode.equalsIgnoreCase("N")) 
		{						
			empAllwMpg = new HrPayEmpallowMpg();
			objectArgs.put("edit","N");
		}
        else
        { 
        	long allowCode = 0; 
        	String allowId = "";
           
        	if(!StringUtility.getParameter("txtAllowCode", request).equals(""))
     		{     			
         		allowId = StringUtility.getParameter("txtAllowCode", request);
     		}	
        	
        	logger.info("txtIsChecked is " + StringUtility.getParameter("txtIsChecked", request));
            logger.info("txtIsChecked is " + StringUtility.getParameter("txtIsChecked", request));
    		if(!StringUtility.getParameter("txtIsChecked", request).equals(""))
    		{    			
    			isChecked = Long.parseLong(StringUtility.getParameter("txtIsChecked", request));
    		}
    		        			 
		 logger.info("AllowID in VOGen " + allowId);		 
		 if( allowId!=null ){
			 allowCode = Long.parseLong(allowId);
		  }
		 empAllwMpg = new HrPayEmpallowMpg();		 		 
		 empAllwMpg.setAllowCode(allowCode);
		 objectArgs.put("edit","Y");
        }
		
		long empAllowId = 0;
		double allowAmount=0;		
		long empId = 0;
		
		     							     		
		if(!StringUtility.getParameter("txtEmpAllowId", request).equals(""))
		{		
			empAllowId = Long.parseLong(StringUtility.getParameter("txtEmpAllowId", request));
		}
		
		if(!StringUtility.getParameter("txtAllowAmount", request).equals(""))
		{			
			 allowAmount = Double.parseDouble(StringUtility.getParameter("txtAllowAmount", request).trim());
			//double amount = Double.parseDouble(StringUtility.getParameter("txtAllowAmount", request).trim());
			/*long decimal = (long)amount;
			
	        logger.info("Allow - Amount " + (amount-decimal));		
			if((amount-decimal) < 0.50)
			allowAmount = (long)Math.floor(amount);
			else
				allowAmount = (long)Math.ceil(amount);
			logger.info("allow Amount " + allowAmount + "\ndecimal " + decimal + "\namount " + amount);*/
		}
		if(!StringUtility.getParameter("txtEmpId", request).equals(""))
		{			
    		empId = Long.parseLong(StringUtility.getParameter("txtEmpId", request));
		}
    	
		 if(isChecked==0)	
			 empAllwMpg.setEmpCurrentStatus(0);	//status = inactive	 
		 else
			 empAllwMpg.setEmpCurrentStatus(1); //status = active
		 
		 logger.info("isChecked in service is " + isChecked + "\nemp id in service is " + empId);
		 HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		 hrEisEmpMst.setEmpId(empId);
		
		 HrPayAllowTypeMst hrPayAllowTypeMst = new HrPayAllowTypeMst();
		 hrPayAllowTypeMst.setAllowCode(empAllowId);
		 
		 empAllwMpg.setEmpAllowAmount(allowAmount);
		 empAllwMpg.setHrPayAllowTypeMst(hrPayAllowTypeMst);
		 empAllwMpg.setHrEisEmpMst(hrEisEmpMst);
		 		 		 		 
		 Date sysdate = new Date();															
		 empAllwMpg.setUpdatedDate(sysdate);
		 						
		logger.info(" ****************************empAllwMpg " + empAllwMpg);
		objectArgs.put("empAllwMpg",empAllwMpg);
		}
		retObj.setResultValue(objectArgs);
		retObj.setResultCode(ErrorConstants.SUCCESS);
		return retObj;
		}
		catch(Exception e){
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			Map errorMap = new HashMap();
			errorMap.put("msg", "There is some problem. Please Try Again Later.");
			retObj.setResultCode(-1);
			retObj.setResultValue(errorMap);
			retObj.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return retObj;			
		}		
	}
}
