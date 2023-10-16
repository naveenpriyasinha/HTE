package com.tcs.sgv.eis.service;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.dao.DeductionDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpAllwMapDAOImpl;
import com.tcs.sgv.eis.dao.EmpGpfDtlsDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.VoluntryPFDAOImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrPayDeductionDtls;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class VoluntryPFDtlsServiceImpl extends ServiceImpl {
	Log logger = LogFactory.getLog( getClass() );

	
	
	int msg=0;
	public ResultObject getVoluntryProvidandFundDtls(Map objectArgs){

		logger.info("IN getVoluntryProvidandFundDtls");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		
		ServiceLocator serv = null;
		Map loginDetailsMap = null;
		Map voToService = null;
		CmnLocationMst cmnLocationMst = null;
		String locationCode = null;
		
		
		
		try{
			serv = (ServiceLocator)objectArgs.get("serviceLocator");
			loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			voToService = (Map)objectArgs.get("voToServiceMap");
			cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
			locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());

			String chk="";
			if(voToService!=null&&voToService.get("chk")!=null)
				chk=(String) voToService.get("chk");
			
			if(chk!=null && chk.equals("1"))
			{
				String strEmpId = voToService.get("empId").toString();
				StringBuffer empNameBf=new StringBuffer();
				long empId=0;		
				logger.info("::::::::::::::::::::::::::::::::::    inside if loop ::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				if(!strEmpId.equals(null) && !strEmpId.equals(""))
					empId=Long.parseLong(strEmpId);		
				logger.info("The Employee Id is:-"+empId);
				
				long id=0;
				
				List<HrEisEmpMst> empList = null;
				if(empId!=0)
				{
					EmpInfoDAOImpl empinfoDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
					empList = empinfoDao.getEmpIdData(empId);
				}
				
				OtherDetailDAOImpl otherDtlsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				List<HrEisOtherDtls> eisOtherDtlsList = null;


				if(empList!=null && empList.size()>0)
				{
					eisOtherDtlsList = otherDtlsDao.getEmpAvailable(empId);

					if(eisOtherDtlsList==null || eisOtherDtlsList.size()==0){
						empNameBf.append("<empNameMapping>");
						empNameBf.append("<availability>").append("-1").append("</availability>");
						empNameBf.append("</empNameMapping>");   
					}else{
						
						VoluntryPFDAOImpl voluntryPFDAO = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());
						
						List<HrPayVpfDtls> hrPayVpfDtlsList = voluntryPFDAO.getHrPayVpfByOrgEmpId(empId);
						
						if(hrPayVpfDtlsList!=null && hrPayVpfDtlsList.size()>0)
						{
							empNameBf.append("<empNameMapping>");
							empNameBf.append("<availability>").append(hrPayVpfDtlsList.size()).append("</availability>");
							empNameBf.append("<id>").append(hrPayVpfDtlsList.get(0).getPayVpfId()).append("</id>");
							empNameBf.append("</empNameMapping>");
						}
						else
						{ 
							empNameBf.append("<empNameMapping>");
							empNameBf.append("<availability>").append(id).append("</availability>");
							empNameBf.append("</empNameMapping>");
						}
					}
					logger.info("Element size ="+eisOtherDtlsList.size());
				}
				else
				{
					empNameBf.append("<empNameMapping>");
					empNameBf.append("<availability>").append(-2).append("</availability>");
					empNameBf.append("</empNameMapping>");
				}

				
				String empNameMapping = new AjaxXmlBuilder().addItem("ajax_key", empNameBf.toString()).toString();

				logger.info(" the string buffer is :"+empNameMapping);
				objectArgs.put("ajaxKey", empNameMapping);
				resultObject.setResultCode(ErrorConstants.SUCCESS);
				resultObject.setResultValue(objectArgs);
				resultObject.setViewName("ajaxData");
				logger.info(" SERVICE COMPLETE :");
			}
			else
			{
				
				VoluntryPFDAOImpl voluntryPFDAO = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());
				logger.info("::::::::::::::::::::::::::::::::::    inside else loop ::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				long empID = 0;
				String empName = "";
				long otherId = 0;
				empID=(voToService.get("Employee_ID_EmpLoanSearch")!=null&&!voToService.get("Employee_ID_EmpLoanSearch").equals(""))?Long.parseLong((String)voToService.get("Employee_ID_EmpLoanSearch")):0;
				
				String FromBasicDtlsNew=voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
				
				if(FromBasicDtlsNew !=  null && !FromBasicDtlsNew.equals(""))
					empID=(voToService.get("Employee_ID_EmpInfoSearch")!=null&&!voToService.get("Employee_ID_EmpInfoSearch").equals(""))?Long.parseLong((String)voToService.get("Employee_ID_EmpInfoSearch")):0;
				if(FromBasicDtlsNew !=  null && !FromBasicDtlsNew.equals(""))
					otherId=(voToService.get("otherId")!=null&&!voToService.get("otherId").equals(""))?Long.parseLong((String)voToService.get("otherId")):0;
					logger.info(" otherId---------- 1 ------->>>>>>>>> " + otherId);
				if((voToService.get("Employee_srchNameText_EmpLoanSearch")!=null&&!voToService.get("Employee_srchNameText_EmpLoanSearch").equals("")))
					empName=(String)voToService.get("Employee_srchNameText_EmpLoanSearch").toString();
				
				logger.info("empID : *****"+empID + "emp Name : *****"+empName);
				List<HrPayVpfDtls> actionList = new ArrayList();
					 actionList = voluntryPFDAO.getVoluntryDtls(locationCode, empID, langId);
					 
					 for(int i=0;i<actionList.size();i++)
		            	{
						 HrPayVpfDtls hrPayVpfDtls=actionList.get(i);
						 String tempBuffer="";
						 /// below line is for lazy initialization do not delete it
						 logger.info(""+hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
						 if (hrPayVpfDtls.getVpfAmt()!=0)
						 {
							 NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
			                    //System.out.println( "Formatted as currency: " +currencyFormatter.format(hrPayVpfDtls.getVpfAmt()) );
			                    tempBuffer=currencyFormatter.format(hrPayVpfDtls.getVpfAmt()).replace("$", "");
			                    hrPayVpfDtls.setCurrencyvpfAmt(tempBuffer.replace(".00", ""));
						 }
						 
						 
						 
						 
						 
		            	}
				objectArgs.put("actionList", actionList);
				objectArgs.put("langId",langId);            

				resultObject.setResultCode(ErrorConstants.SUCCESS);


				//added by Ankit Bhatt for merging the Screens

				String empAllRec = ""; 
				objectArgs.put("otherId", otherId);
				objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
				objectArgs.put("empName", empName);
				if(voToService.get("empAllRec")!=null)
					empAllRec = voToService.get("empAllRec").toString();

				logger.info("EmpId in Loan Insert " + empID);
				if(!empAllRec.equals("") && empAllRec.equalsIgnoreCase("y"))
				{
					resultObject.setViewName("viewVpfDtlsEmpAllRec");
					objectArgs.put("empId", empID);
					objectArgs.put("empAllRec", "true");
				}
				else
					//ended by Ankit Bhatt
					resultObject.setViewName("viewVpfDtls");
					resultObject.setResultValue(objectArgs);
			}
		}
		catch(Exception e){
			logger.info("There is some error in fetching VPF Data");
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		return resultObject;
	}

	public ResultObject insertUpdateVpfDtls(Map objectArgs) {
		// TODO Auto-generated method stub
		logger.info("IN insertUpdateVpfDtls");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		try{
			
			
			
			String locationCode = null;
			
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			
			
			long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

			long dbId=Long.parseLong(loginDetailsMap.get("dbId").toString());
			CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
			CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);

			CmnLocationMst cmnLocationMst = (CmnLocationMst)loginDetailsMap.get("locationVO");
			long location_id=cmnLocationMst.getLocId();
			
			long postId=Long.parseLong(loginDetailsMap.get("primaryPostId").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl=new OrgPostMstDaoImpl(OrgPostMst.class,serv.getSessionFactory());
			OrgPostMst orgPostMst=orgPostMstDaoImpl.read(postId);	

			HrPayVpfDtls objHrPayVpfDtls = (HrPayVpfDtls)objectArgs.get("hrPayVpfDtls");
			long VpfAmount=objHrPayVpfDtls.getVpfAmt();
			if (VpfAmount%10!=0 && location_id==300022)
			{
				VpfAmount=VpfAmount+(10-(VpfAmount%10));
			}
			objHrPayVpfDtls.setVpfAmt(VpfAmount);
			
			DeductionDtlsDAOImpl deducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			EmpInfoDAOImpl empInfoDAOImpl = new  EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());

			// Setter of HrPayVpfDtls
			Date sysdate = new Date();			
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");		
			HrPayDeductionDtls hrPayDeductionDtls = null;
			HrPayDeducTypeMst hrPayDeducTypeMst = new HrPayDeducTypeMst();
			VoluntryPFDAOImpl voluntryPFDAO = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());		
			long deducCode;

			long empId;
			long empID=0;
			long otherId=0;
			String FromBasicDtlsNew = "";
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst(); 
			IdGenerator idGen = new IdGenerator();
			String editMode = objectArgs.get("editMode").toString();
			logger.info("The editMode Is :-"+editMode);
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			if(request.getParameter("FromBasicDtlsNew")!=null&&!request.getParameter("FromBasicDtlsNew").toString().equals(""))
				FromBasicDtlsNew=(String) request.getParameter("FromBasicDtlsNew").toString();
			if(FromBasicDtlsNew !=  null && !FromBasicDtlsNew.equals(""))
				otherId=(request.getParameter("otherId")!=null&&!request.getParameter("otherId").equals(""))?Long.parseLong((String) request.getParameter("otherId")):0;
			objectArgs.put("otherId", otherId);
			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
		logger.info(" FromBasicDtlsNew----------------->>>>>>>>> " + FromBasicDtlsNew);
		logger.info(" otherId-------  2   ---------->>>>>>>>> " + otherId);
			
			if(editMode.equalsIgnoreCase("Y")) {				
				HrPayVpfDtls hrPayVpfDtls = voluntryPFDAO.read(objHrPayVpfDtls.getPayVpfId());				
				hrPayVpfDtls.setVpfAmt(objHrPayVpfDtls.getVpfAmt());
				hrPayVpfDtls.setZerovpfMonths(objHrPayVpfDtls.getZerovpfMonths());
				hrPayVpfDtls.setOrgUserMstByUpdatedBy(orgUserMst);
				hrPayVpfDtls.setOrgPostMstByUpdatedByPost(orgPostMst);				
				hrPayVpfDtls.setUpdatedDate(sysdate);
				
				resultObject = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
	            Map attachmentMap = (Map) resultObject.getResultValue();
	            logger.info("in service for attachment and the  " + attachmentMap.get("AttachmentId_vpfAttachment"));
	            logger.info("in service for attachment and the attachmentMap is : " + attachmentMap);
	            if (attachmentMap != null && attachmentMap.get("AttachmentId_vpfAttachment") != null)
	            {
	                logger.info("attachmentMap is not Null");
	                long vpfAttachmentId = Long.parseLong(attachmentMap.get("AttachmentId_vpfAttachment").toString());
	                CmnAttachmentMstDAOImpl cmnAttachmentMstDAOImpl = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
	                CmnAttachmentMst statementAttachmentVO = cmnAttachmentMstDAOImpl.read(vpfAttachmentId);
	                logger.info(" Attachment Id" + vpfAttachmentId);
	                CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();
	                cmnAttachmentMst.setAttachmentId((Long.valueOf(vpfAttachmentId)));
	                hrPayVpfDtls.setCmnAttachmentMst(statementAttachmentVO);
	            }

				voluntryPFDAO.update(hrPayVpfDtls);		
				msg=1;
				empID=hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId();
				if(langId!=1){
					long languageId=1;
					OrgEmpMst orgEmpMst = new OrgEmpMst();
					orgEmpMst = empDAOImpl.read(empID);
					orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), languageId);
					empID = orgEmpMst.getEmpId();
				}
				hrEisEmpMst = hrPayVpfDtls.getHrEisEmpMst();
				deducCode=Long.parseLong(resourceBundle.getString("VPF"));
				logger.info("VPF Code is:-"+deducCode);
				logger.info("Employee IDddddddddddd is:-"+empID);
				hrPayDeductionDtls = deducDtlsDAO.getHrPayDeductionDtls(empID, deducCode);
				hrPayDeductionDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrPayDeductionDtls.setOrgUserMstByUpdatedBy(orgUserMst);
				hrPayDeductionDtls.setUpdatedDate(sysdate);
				hrPayDeductionDtls.setEmpDeducAmount(objHrPayVpfDtls.getVpfAmt());
				
				deducDtlsDAO.update(hrPayDeductionDtls);				
				logger.info("The Record of Deduction Dtls is been updated");
			}
			else {										

				Long id= idGen.PKGenerator("hr_pay_vpf_dtls",objectArgs);							

				// Setter Which Required at Only Insert Time.
				objHrPayVpfDtls.setPayVpfId(id);
				
				objHrPayVpfDtls.setCmnDatabaseMst(cmnDatabaseMst);
				objHrPayVpfDtls.setCmnLocationMst(cmnLocationMst);
				objHrPayVpfDtls.setCreatedDate(sysdate);
				objHrPayVpfDtls.setOrgUserMstByCreatedBy(orgUserMst);
				objHrPayVpfDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				objHrPayVpfDtls.setTrnCounter(new Integer(1));
				
				// done by gole  for attachment
				resultObject = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
	            Map attachmentMap = (Map) resultObject.getResultValue();
	            logger.info("in service for attachment and the  " + attachmentMap.get("AttachmentId_vpfAttachment"));
	            logger.info("in service for attachment and the attachmentMap is : " + attachmentMap);
	            if (attachmentMap != null && attachmentMap.get("AttachmentId_vpfAttachment") != null)
	            {
	                logger.info("attachmentMap is not Null");
	                long vpfAttachmentId = Long.parseLong(attachmentMap.get("AttachmentId_vpfAttachment").toString());
	                CmnAttachmentMstDAOImpl cmnAttachmentMstDAOImpl = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
	                CmnAttachmentMst statementAttachmentVO = cmnAttachmentMstDAOImpl.read(vpfAttachmentId);
	                logger.info(" Attachment Id" + vpfAttachmentId);
	                CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();
	                cmnAttachmentMst.setAttachmentId((Long.valueOf(vpfAttachmentId)));
	                objHrPayVpfDtls.setCmnAttachmentMst(statementAttachmentVO);
	            }
	         // done by gole  for attachment end
				
				
				empId= objHrPayVpfDtls.getHrEisEmpMst().getEmpId();
				List lstEmp = new ArrayList();
				lstEmp = empInfoDAOImpl.getListByColumnAndValue("orgEmpMst.empId",empId);
				if(lstEmp!=null && !lstEmp.isEmpty()){
					hrEisEmpMst = (HrEisEmpMst)lstEmp.get(0); 
					objHrPayVpfDtls.setHrEisEmpMst(hrEisEmpMst);
					empID=objHrPayVpfDtls.getHrEisEmpMst().getEmpId();
				}

				logger.info(empId+"inside trial else service----------------------"+empID);
				if(langId!=1){
					long languageId = 1;

					OrgEmpMst orgEmpMst = new OrgEmpMst();
					orgEmpMst = empDAOImpl.read(empId);
					orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), languageId); 
					hrEisEmpMst = empInfoDAOImpl.read(orgEmpMst.getEmpId());
					objHrPayVpfDtls.setHrEisEmpMst(hrEisEmpMst);
					empId = orgEmpMst.getEmpId();
				}

				else{
					hrEisEmpMst = empInfoDAOImpl.read(hrEisEmpMst.getEmpId());
					objHrPayVpfDtls.setHrEisEmpMst(hrEisEmpMst);
				}		

				deducCode = Long.parseLong(resourceBundle.getString("PF"));		

				voluntryPFDAO.create(objHrPayVpfDtls);					
				logger.info("There is some error after insert");

				hrPayDeductionDtls = deducDtlsDAO.getHrPayDeductionDtls(empId, deducCode);
				hrPayDeductionDtls.setEmpCurrentStatus(0); 	// Update the PF with current Status 0.
				hrPayDeductionDtls.setOrgPostMstByUpdatedByPost(orgPostMst);
				hrPayDeductionDtls.setOrgUserMstByUpdatedBy(orgUserMst);
				hrPayDeductionDtls.setUpdatedDate(sysdate);
				deducDtlsDAO.update(hrPayDeductionDtls);				

				// Insert in to Deduction Dtls.
				id= idGen.PKGenerator("hr_pay_deduction_dtls",objectArgs);
				hrPayDeductionDtls = new HrPayDeductionDtls();
				hrPayDeductionDtls.setCmnDatabaseMst(cmnDatabaseMst);
				hrPayDeductionDtls.setCmnLocationMst(cmnLocationMst);			
				hrPayDeductionDtls.setDeducDtlsCode(id);
				hrPayDeductionDtls.setEmpCurrentStatus(1);
				hrPayDeductionDtls.setEmpDeducAmount(objHrPayVpfDtls.getVpfAmt());
				hrPayDeductionDtls.setHrEisEmpMst(hrEisEmpMst);
				deducCode=Long.parseLong(resourceBundle.getString("VPF"));
				hrPayDeducTypeMst.setDeducCode(deducCode);			
				hrPayDeductionDtls.setHrPayDeducTypeMst(hrPayDeducTypeMst);
				hrPayDeductionDtls.setOrgPostMstByCreatedByPost(orgPostMst);
				hrPayDeductionDtls.setOrgUserMstByCreatedBy(orgUserMst);
				hrPayDeductionDtls.setCreatedDate(sysdate);
				hrPayDeductionDtls.setTrnCounter(new Integer(1));
				deducDtlsDAO.create(hrPayDeductionDtls);
				logger.info("The New  Record is been inserted");

			}	


			objectArgs.put("empId", empID);
		
			
			if(msg==1)
				objectArgs.put("MESSAGECODE",300006);
			else
				objectArgs.put("MESSAGECODE",300005);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			//resultObject.setViewName("viewVpfDtls");


			String empAllRec="";
			if(request.getParameter("empAllRec")!=null)
				empAllRec=request.getParameter("empAllRec").toString();

			if(msg==1 && empAllRec.equalsIgnoreCase("true")==true)
			{
				resultObject.setViewName("updateVpfDtlsEmpAllRec");
				objectArgs.put("empId", empID);
				objectArgs.put("empAllRec", "added");
			}

			else if (msg==1 && empAllRec.equals("false")==true)
			{
				resultObject.setViewName("updateVpfDtls");	
				objectArgs.put("empAllRec", "false");
			}

			else if(!(msg==1) && empAllRec.equalsIgnoreCase("true")==true)
			{
				resultObject.setViewName("insertVpfDtlsEmpAllRec");
				objectArgs.put("empId", empID);
				objectArgs.put("empAllRec", "added");

			}
			else
			{
				resultObject.setViewName("insertVpfDtls");
				objectArgs.put("empAllRec", "false");
			}

		    //// parameters for update paybill
            String updatePaybillFlg = "n";
		    int paybillMonth=0;
		    int paybillYear=0;
		    //// parameters for update paybill
		    if(request.getParameter("updatePaybillFlg")!=null)
		    {	
		    	updatePaybillFlg=request.getParameter("updatePaybillFlg").toString();
		    }
 		    if(request.getParameter("paybillMonth")!=null && !request.getParameter("paybillMonth").equals(""))
		    {	
  		    	paybillMonth=Integer.parseInt(request.getParameter("paybillMonth").toString());
		    }	
 		    if(request.getParameter("paybillYear")!=null && !request.getParameter("paybillYear").equals(""))
		    {	
 		    	paybillYear=Integer.parseInt(request.getParameter("paybillYear").toString());
		    }	
		    
		    logger.info("test update paybill parameters in emp VPF service"+updatePaybillFlg+" "+paybillMonth+" "+paybillYear);		    

			if(updatePaybillFlg.equalsIgnoreCase("y"))
			{
		    objectArgs.put("updatePaybillEmpId",empID);
		    objectArgs.put("paybillMonth", paybillMonth);
		    objectArgs.put("paybillYear", paybillYear);
		    objectArgs.put("VPFAmt", VpfAmount);
		    objectArgs.put("searchData", "y");
			
			ResultObject resultObj1 = serv.executeService("fillPaybillData", objectArgs);
			objectArgs =(Map) resultObj1.getResultValue();
			resultObject.setViewName("updatePaybill");
			}
		    
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			logger.info("INSERTED SUCCESSFULLY");
		}
		catch(ConstraintViolationException ex)
		{
			resultObject.setThrowable(ex);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			logger.info("TransactionSystemException occurs...");
		}
		catch(Exception e){
			//System.out.println("The error is:-");
			logger.info("There is some error at editting or inserting time");
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			logger.error("Error is: "+ e.getMessage());
			return resultObject;
		}
		return resultObject;
	}

	public ResultObject getVPFEmployees(Map objectArgs){

		logger.info("IN getVPFEmployees");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");
			//added by Ankit Bhatt - Must be removed later on, use voToService instead of HttpServeletRequest
			HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
			//ennded by Ankit Bhatt
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			
			
			VoluntryPFDAOImpl voluntryPFDAO = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());	        
			List actionList = voluntryPFDAO.getVpfEmployees();
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			List newActionList = new ArrayList();
			OrgEmpMst orgEmpMst = null;
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			
			String empname = "";
			if((request.getParameter("empName")!=null&&!request.getParameter("empName").equals("")))
				empname=request.getParameter("empName").toString();
		
			for (Iterator iter = actionList.iterator(); iter.hasNext();) {			 
				HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
				hrEisOtherDtls= (HrEisOtherDtls) iter.next();              	
				long empId = hrEisOtherDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId(); // Tempaorary
				orgEmpMst = new OrgEmpMst();
				orgEmpMst = empDAOImpl.read(empId);
				if(langId!=1)           		  
					orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), langId);
				newActionList.add(orgEmpMst);
				logger.info("EmpId is :-"+empId);				 	
			}

			logger.info("The Size of List is:-"+actionList.size());	  
			objectArgs.put("actionList", newActionList);
			resultObject.setResultCode(ErrorConstants.SUCCESS);


			//added by Ankit Bhatt for merging the Screens
			long empId=0;
			String empAllRec = ""; 
			if(request.getParameter("empAllRec")!=null && request.getParameter("empAllRec").equalsIgnoreCase("Y"))
			{
				empAllRec = request.getParameter("empAllRec").toString();
				//System.out.println("EmpId in Loan Insert dffddfdsftrtre" +empId+"emprecis=======>"+empAllRec);
			}
			if(request.getParameter("empId")!=null  && empAllRec.equalsIgnoreCase("y")==true)
				empId = Long.valueOf(request.getParameter("empId").toString());

			logger.info("EmpId in Loan Insert dffddfdsftrtre " + empId);
			//System.out.println("EmpId in Loan Insert dffddfdsftrtre" +empId+"emprecis=======>"+empAllRec);
			if(!empAllRec.equals("") && empAllRec.equalsIgnoreCase("y")==true)
			{
				//System.out.println("EmpId in Loan Insert dffddfdsftrtre " + empId);
				objectArgs.put("empId", empId);
				objectArgs.put("empAllRec", "true");
				resultObject.setViewName("insertVpfDtlsEmpAllRec");

			}
			else
			{ 
				resultObject.setViewName("insertVpfDtls");
				objectArgs.put("empAllRec", "false");
			}
			objectArgs.put("empName", empname);
			resultObject.setResultValue(objectArgs);
		}
		catch(Exception e){
			logger.info("There is some error in fetching Emplyee Data");
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			return resultObject;
		}
		return resultObject;
	}

	public ResultObject getVpfDtlsByEmpId(Map objectArgs){

		
		logger.info("IN getVpfDtlsByEmpId");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map voToService = (Map)objectArgs.get("voToServiceMap");
			String checkValue = voToService.get("check").toString();
			VoluntryPFDAOImpl voluntryPFDAO = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");		
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());
			long empId = Long.parseLong(voToService.get("empId").toString());
			logger.info("Employee Id is in VPF:-"+empId);			
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");

			StringBuffer vpfDtls = new StringBuffer(); // String Buffer for storing the return String.
			vpfDtls.append("<vpfDetailsMapping>");

			long deducCode = Long.parseLong(resourceBundle.getString("PF"));
			long daCode = Long.parseLong(resourceBundle.getString("daCode"));
			long dpCode = Long.parseLong(resourceBundle.getString("dpCode"));
			//EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory()); // Temp
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			List lstEmpDtls = empInfoDAOImpl.getEmpIdData(empId);
			//HrEisEmpMst hrEisEmpMst = empInfoDAOImpl.read(empId);//Temp
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			if(lstEmpDtls!=null && !lstEmpDtls.isEmpty())
				hrEisEmpMst = (HrEisEmpMst)lstEmpDtls.get(0);
			OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
			hrEisOtherDtls = otherDetailDAOImpl.getOtherData(hrEisEmpMst.getOrgEmpMst().getEmpId());
			int isEmpAvail=0;
			if(langId!=1){
				long languageId=1;
				OrgEmpMst orgEmpMst = new OrgEmpMst();
				orgEmpMst = empDAOImpl.read(empId);
				orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), languageId);
				empId = orgEmpMst.getEmpId();
			}
			empId = hrEisEmpMst.getOrgEmpMst().getEmpId();
			long emptype=0;
			long gpfAcAvailability = 0;
			emptype=hrEisEmpMst.getEmpType();

			// Urvin Shah adding the Employee Type.
			vpfDtls.append("<emptype>").append(emptype).append("</emptype>");
			//End By Urvin shah.

			//System.out.println("employee type is=====>"+emptype);				

			if(hrEisOtherDtls.getOtherId()!=0 && checkValue.equals("2")){				
				EmpAllwMapDAOImpl empAllwMapDAOImpl = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
				EmpGpfDtlsDAOImpl empGpfDtlsDAOImpl = new EmpGpfDtlsDAOImpl(HrPayGpfBalanceDtls.class,serv.getSessionFactory());
				List gpfList = empGpfDtlsDAOImpl.getAllGpfDetails(empId);
				//List gpfList = empGpfDtlsDAOImpl.getListByColumnAndValue("", arg1)
				isEmpAvail = 1;
				vpfDtls.append("<empAvail>").append(isEmpAvail).append("</empAvail>");

				if(gpfList!= null && gpfList.size() > 0) {	
					
					for (Iterator i = gpfList.iterator(); i.hasNext();)
	                {
	                    logger.debug("getting objects one by one from list");
	                    Object[] RelationVO = (Object[])i.next();
	                    String gpfAccountNumber = (String)RelationVO[0];
	                    
	                    logger.info("::::::::gpfAccountNumber:::::"+gpfAccountNumber+":::::::::");
	                	
	                	if(!gpfAccountNumber.equalsIgnoreCase("")||!gpfAccountNumber.equalsIgnoreCase(" "))
	                		{
	                		logger.info("::::::::in if loop::::::");
	                		gpfAcAvailability=1;
	                		}
	                }    
					logger.info(":::::::gpfAcAvailability::::::"+gpfAcAvailability);
								
					logger.info(deducCode+"The Employee Id is :-"+empId);	     
					HrPayEmpallowMpg hrPayEmpallowMpg = empAllwMapDAOImpl.getHrPayEmpallowMpg(empId, daCode);			
					DeductionDtlsDAOImpl deducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());		        
					HrPayDeductionDtls hrPayDeductionDtls = null;
					logger.info(deducCode+"The Employee Id is :-"+empId);           

					double intialBasic = Math.round(hrEisOtherDtls.getotherCurrentBasic());       
					double daAmount = Math.round(hrPayEmpallowMpg.getEmpAllowAmount());
					hrPayEmpallowMpg = empAllwMapDAOImpl.getHrPayEmpallowMpg(empId, dpCode);

					hrPayDeductionDtls = deducDtlsDAO.getHrPayDeductionDtls(empId, deducCode);

					double pfAmount = Math.round(hrPayDeductionDtls.getEmpDeducAmount());
					Calendar cal = Calendar.getInstance();
					cal.set(Integer.parseInt(resourceBundle.getString("CPFyyyy")),Integer.parseInt(resourceBundle.getString("CPFMM"))-1,Integer.parseInt(resourceBundle.getString("CPFdd")),23,59,59);
					Date CPFDate = cal.getTime();

					if(hrEisEmpMst.getOrgEmpMst().getEmpDoj().after(CPFDate))
					{
						long CPFCode = Long.parseLong(resourceBundle.getString("CPF"));
						hrPayDeductionDtls = deducDtlsDAO.getHrPayDeductionDtls(empId, CPFCode);					       
						pfAmount = Math.round(hrPayDeductionDtls.getEmpDeducAmount());				        	
					}
					logger.info("The PF Amount is :-"+pfAmount);
					//double dpAmount = Math.round(intialBasic / 2);
					double dpAmount = Math.round(hrPayEmpallowMpg.getEmpAllowAmount());
					double maxAmountforVPF = intialBasic+dpAmount+daAmount; 
					logger.info("The Employee Id is :-"+empId);	       
					logger.info("The Employee Max Amount for VPF is :-"+maxAmountforVPF);
					vpfDtls.append("<gpfAccAvail>").append(gpfAcAvailability).append("</gpfAccAvail>");
					vpfDtls.append("<maxAmount>").append(maxAmountforVPF).append("</maxAmount>");
					vpfDtls.append("<pfAmount>").append(pfAmount).append("</pfAmount>");
				}
				else{					
					vpfDtls.append("<gpfAccAvail>").append(gpfAcAvailability).append("</gpfAccAvail>");
				}
			} 
			else {
				vpfDtls.append("<empAvail>").append(isEmpAvail).append("</empAvail>");	        
			}
			vpfDtls.append("</vpfDetailsMapping>");
			String vpfDetailsMapping = new AjaxXmlBuilder().addItem("ajax_key", vpfDtls.toString()).toString();
			//System.out.println(" the string buffer is :"+vpfDetailsMapping);
			objectArgs.put("ajaxKey", vpfDetailsMapping);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ajaxData");
		}
		catch(Exception e){
			logger.error("Exception while generating VO to XML ", e);
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);
			retObj.setViewName("ajaxError");
			objectArgs.put("ajaxKey", "ERROR");
			retObj.setResultValue(objectArgs);
			retObj.setThrowable(e);
			return retObj;
		}
		return resultObject;
	}

	public ResultObject getVpfDtlsById(Map objectArgs){

		logger.info("IN getVpfDtlsById");
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);		
		try{
			ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
			Map voToService = (Map)objectArgs.get("voToServiceMap");			
			//ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);

			long vpfId= Long.parseLong(voToService.get("vpfId").toString());
			
			long updatePaybillEmpId=0;
			if(voToService.get("updatePaybillEmpId")!=null&&!voToService.get("updatePaybillEmpId").toString().equals(""))
				updatePaybillEmpId=Long.parseLong(voToService.get("updatePaybillEmpId").toString());
			
			
		    //// parameters for update paybill
            String updatePaybillFlg = "n";
		    int paybillMonth=0;
		    int paybillYear=0;
		    String FromBasicDtlsNew = "";
		    long otherId=0;
		    //// parameters for update paybill

		    if(voToService.get("updatePaybillFlg")!=null)
		    {	
		    	updatePaybillFlg=voToService.get("updatePaybillFlg").toString();
		    }
 		    if(voToService.get("paybillMonth")!=null)
		    {	
 		    	paybillMonth=Integer.parseInt(voToService.get("paybillMonth").toString());
		    }	
 		    if(voToService.get("paybillYear")!=null)
		    {	
 		    	paybillYear=Integer.parseInt(voToService.get("paybillYear").toString());
		    }	

 		    objectArgs.put("updatePaybillFlg", updatePaybillFlg);
		    objectArgs.put("paybillMonth", paybillMonth);
		    objectArgs.put("paybillYear", paybillYear);
		    
		    logger.info("test update paybill parameters in VPF details service"+updatePaybillFlg+" "+paybillMonth+" "+paybillYear);
		    if(voToService.get("FromBasicDtlsNew")!=null&&!voToService.get("FromBasicDtlsNew").toString().equals(""))
				FromBasicDtlsNew=(String) voToService.get("FromBasicDtlsNew").toString();
		    if(FromBasicDtlsNew !=  null && !FromBasicDtlsNew.equals(""))
				otherId=(voToService.get("otherId")!=null&&!voToService.get("otherId").equals(""))?Long.parseLong((String)voToService.get("otherId")):0;
			objectArgs.put("otherId", otherId);
			logger.info(" otherId-----  3  ------------>>>>>>>>> " + otherId);
			objectArgs.put("FromBasicDtlsNew", FromBasicDtlsNew);
			OtherDetailDAOImpl otherDetailsDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
			DeductionDtlsDAOImpl deducDtlsDAO = new DeductionDtlsDAOImpl(HrPayDeductionDtls.class,serv.getSessionFactory());
			VoluntryPFDAOImpl voluntryPFDAO = new VoluntryPFDAOImpl(HrPayVpfDtls.class,serv.getSessionFactory());
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
			HrPayVpfDtls hrPayVpfDtls = new HrPayVpfDtls();
			if(vpfId==0)
			{
				List<HrPayVpfDtls> hrPayVpfDtlsList=(voluntryPFDAO.getHrPayVpfByOrgEmpId(updatePaybillEmpId));
				if(hrPayVpfDtlsList!=null && hrPayVpfDtlsList.size()>0)
				vpfId=hrPayVpfDtlsList.get(0).getPayVpfId();
			}
			long deducCode = Long.parseLong(resourceBundle.getString("PF"));
			hrPayVpfDtls = (HrPayVpfDtls) voluntryPFDAO.read(vpfId);
			HrPayDeductionDtls hrPayDeductionDtls = null;
			EmpDAOImpl empDAOImpl = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			Map loginDetailsMap = (Map)objectArgs.get("baseLoginMap");		
			long langId = Long.parseLong(loginDetailsMap.get("langId").toString());	         	        
			long empId = hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst().getEmpId();
			OrgEmpMst orgEmpMst = empDAOImpl.read(empId);	        
			if(langId!=1){				
				orgEmpMst = empInfoDAOImpl.getEmployee(orgEmpMst.getOrgUserMst(), langId);				
			}
			long daCode = Math.round(Long.parseLong(resourceBundle.getString("daCode")));
			long dpCode = Math.round(Long.parseLong(resourceBundle.getString("dpCode")));
			EmpAllwMapDAOImpl empAllwMapDAOImpl = new EmpAllwMapDAOImpl(HrPayEmpallowMpg.class,serv.getSessionFactory());
			HrPayEmpallowMpg hrPayEmpallowMpg = empAllwMapDAOImpl.getHrPayEmpallowMpg(empId, daCode);

			//empId = hrPayVpfDtls.getHrEisEmpMst().getEmpId();
			orgEmpMst = hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst();
			hrPayDeductionDtls = deducDtlsDAO.getHrPayDeductionDtls(empId, deducCode);
			double pfAmount = Math.round(hrPayDeductionDtls.getEmpDeducAmount());

			Calendar cal = Calendar.getInstance();

			cal.set(Integer.parseInt(resourceBundle.getString("CPFyyyy")),Integer.parseInt(resourceBundle.getString("CPFMM"))-1,Integer.parseInt(resourceBundle.getString("CPFdd")),23,59,59);
			Date CPFDate = cal.getTime();
			logger.info(hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst().getEmpDoj()+"***********"+pfAmount+"*******CPFDate***********"+CPFDate);

			if(hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst().getEmpDoj().after(CPFDate))
			{
				long CPFCode = Long.parseLong(resourceBundle.getString("CPF"));
				hrPayDeductionDtls = deducDtlsDAO.getHrPayDeductionDtls(empId, CPFCode);
				pfAmount = Math.round(hrPayDeductionDtls.getEmpDeducAmount());
				logger.info(hrPayVpfDtls.getHrEisEmpMst().getOrgEmpMst().getEmpDoj()+"****"+CPFCode+"*******"+pfAmount+"*******CPFDate***********"+CPFDate);
			}

			double daAmount = Math.round(hrPayEmpallowMpg.getEmpAllowAmount());
			hrPayEmpallowMpg = empAllwMapDAOImpl.getHrPayEmpallowMpg(empId, dpCode);
			double dpAmount = Math.round(hrPayEmpallowMpg.getEmpAllowAmount());
			HrEisOtherDtls hrEisOtherDtls = new HrEisOtherDtls();
			List<HrEisOtherDtls> otherDtlsList = otherDetailsDao.getEmpAvailable(empId);
			Iterator itr = otherDtlsList.iterator();
			hrEisOtherDtls = (HrEisOtherDtls)itr.next();
			double intialBasic = Math.round(hrEisOtherDtls.getotherCurrentBasic());     
			String empName = hrPayVpfDtls.getHrEisEmpMst().toString();   
			double maxAmount = intialBasic+ daAmount + dpAmount;

			logger.info("The Employee Id is :-"+empId);        
			logger.info("The Employee initial Basic is :-"+intialBasic);
			logger.info("The Employee Name is :-"+empName);
			logger.info("The PF Amount is :-"+pfAmount);
			objectArgs.put("orgEmpMst", orgEmpMst);
			objectArgs.put("pfAmount", pfAmount);
			objectArgs.put("maxPfAmount", maxAmount);
			//objectArgs.put("daAmount", daAmount);
			//objectArgs.put("daAmount", dpAmount);
			//objectArgs.put("intialBasic", intialBasic);
			objectArgs.put("hrPayVpfDtls", hrPayVpfDtls);
			resultObject.setResultCode(ErrorConstants.SUCCESS);
			resultObject.setResultValue(objectArgs);
			
			CmnAttachmentMstDAOImpl attachmentMstDAOImpl = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class, serv.getSessionFactory());
            if (hrPayVpfDtls.getCmnAttachmentMst() != null)
            {
                CmnAttachmentMst cmnAttachmentMstByPhoto = hrPayVpfDtls.getCmnAttachmentMst();
                CmnAttachmentMst photoattachmentMst = attachmentMstDAOImpl.findByAttachmentId(cmnAttachmentMstByPhoto.getAttachmentId());
               objectArgs.put("vpfAttachment", photoattachmentMst);

            }

			//			added by Ankit Bhatt for merging the Screens
			String empAllRec = ""; 
			if(voToService.get("empAllRec")!=null)
				empAllRec = voToService.get("empAllRec").toString();
			long empID=0;
			if(voToService.get("empId")!=null && !voToService.get("empId").equals("") && !voToService.get("empId").equals(" "))
				empID = Long.valueOf(voToService.get("empId").toString());
			logger.info("EmpId in Loan Insert " + empID);
			if(!empAllRec.equals("") && empAllRec.equalsIgnoreCase("y"))
			{
				resultObject.setViewName("updateVpfDtlsEmpAllRec");
				objectArgs.put("empId", empID);
				objectArgs.put("empAllRec", "true");
			}
			else
			{
				resultObject.setViewName("updateVpfDtls");
				objectArgs.put("empAllRec", "false");
			}
			//ended by Ankit Bhatt          

		}
		catch(Exception e){
			logger.info("There is some error in fetching VPF Data");
			logger.error("Error is: "+ e.getMessage());
			logger.error("EXCEPTION THROWN WHILE GETTING VALUES FROM REQUEST",e);
			resultObject.setThrowable(e);
			resultObject.setResultCode(-1);
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("errorInsert");
			return resultObject;
		}
		return resultObject;
	}

}
