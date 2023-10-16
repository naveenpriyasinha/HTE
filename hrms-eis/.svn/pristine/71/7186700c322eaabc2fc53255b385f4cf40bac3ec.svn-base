package com.tcs.sgv.eis.service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.RltBillTypeEdp;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.MiscRecoverDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.util.MiscRecoverServiceHelper;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class MiscRecoverService extends ServiceImpl {
	Log logger = LogFactory.getLog(getClass());
	int msg=0;
public ResultObject getMiscData(Map objServiceArgs)
{
	logger.info("************inside getMiscData***************");
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	try
	{			
        ServiceLocator serv = (ServiceLocator)objServiceArgs.get("serviceLocator");
              
        
        //MiscRecoverDAOImpl miscRecoverDAO = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
        MiscRecoverDAOImpl miscRecoverDAO = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
        HttpServletRequest request = (HttpServletRequest) objServiceArgs.get("requestObj");
        
        HttpSession session=request.getSession();		
		//LoginDetails loginDetails=(LoginDetails)session.getAttribute("loginDetails");
        Map loginDetails=(Map)objServiceArgs.get("baseLoginMap");
		//long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
        //long locationId = Long.parseLong(loginDetails.get("locationId").toString());
        CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetails.get("locationVO");
		String locationCode = (cmnLocationMst.getLocationCode()!=null && !cmnLocationMst.getLocationCode().trim().equals(""))?cmnLocationMst.getLocationCode():"";
		Map voToService = (Map)objServiceArgs.get("voToServiceMap");
		
		String empName="";
		long otherId = 0;
		String FromBasicDtlsNew=voToService.get("FromBasicDtlsNew")!=null?(String)voToService.get("FromBasicDtlsNew"):"";
		if((voToService.get("Employee_srchNameText_miscSearch")!=null&&!voToService.get("Employee_srchNameText_miscSearch").equals("")))
		 empName=(String)voToService.get("Employee_srchNameText_miscSearch").toString();
		if(FromBasicDtlsNew !=  null && !FromBasicDtlsNew.equals(""))
			otherId=(voToService.get("otherId")!=null&&!voToService.get("otherId").equals(""))?Long.parseLong((String)voToService.get("otherId")):0;

		////////// for update pay bill
		String updatePaybillFlg="";
		int paybillMonth=0;
		int paybillYear=0;

		if (voToService.get("updatePaybillFlg") != null)
			updatePaybillFlg = voToService.get("updatePaybillFlg").toString();
	    if(voToService.get("paybillMonth")!=null)
	    	paybillMonth=Integer.parseInt(voToService.get("paybillMonth").toString());
	    if(voToService.get("paybillYear")!=null)
	    	paybillYear=Integer.parseInt(voToService.get("paybillYear").toString());
		
	    objServiceArgs.put("updatePaybillFlg",updatePaybillFlg);
	    objServiceArgs.put("paybillMonth", paybillMonth);
	    objServiceArgs.put("paybillYear", paybillYear);
	    objServiceArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);
	    objServiceArgs.put("otherId",otherId);
		
		logger.info("***************************updatePaybillFlg******************"+updatePaybillFlg);
		
	    
	    
		String editFlag = voToService.get("edit")!=null?(String)voToService.get("edit"):"";
		String empIdStr = ""; 
		if(voToService.get("Employee_ID_miscSearch")!=null && !voToService.get("Employee_ID_miscSearch").equals(""))
		{
			empIdStr=voToService.get("Employee_ID_miscSearch").toString();
		}
		if(voToService.get("empId")!=null && !voToService.get("empId").equals(""))
		{
			empIdStr=voToService.get("empId").toString();
		}
		if(voToService.get("Employee_ID_EmpInfoSearch")!=null && !voToService.get("Employee_ID_EmpInfoSearch").equals(""))
		{
			empIdStr=voToService.get("Employee_ID_EmpInfoSearch").toString();
		}
		String chk="";
		if(voToService.get("chk")!=null && !voToService.get("chk").equals(""))
		{
			chk=voToService.get("chk").toString();
		}
		Date date = new Date();
		if(voToService.get("date")!=null && !voToService.get("date").equals(""))
		{
			String tempDate=voToService.get("date").toString();
			SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
			date=sdf.parse(tempDate);	
			logger.info("the entered date is "+date);
		}
		logger.info("::::::::::::::::::::::::::::: the emp id is "+empIdStr);
		if(chk.equals("1"))
		{
			logger.info("------>inside chk for emp information entry ");
			StringBuffer loanMst = new StringBuffer();
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			EmpInfoDAOImpl hrEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			//List<HrEisEmpMst> hrEmpList = hrEmpDao.getListByColumnAndValue("orgEmpMst.empId", empIdStr);
			long empId=0;
			long empTyp=0;
			if(empIdStr!=null && !empIdStr.equals(""))
			{
				empId=Long.parseLong(empIdStr);
			}
			List<HrEisEmpMst> hrEmpList = hrEmpDao.getListByColumnAndValue("orgEmpMst.empId", empId);
			logger.info(" the list is "+hrEmpList);
			if(hrEmpList!=null && hrEmpList.size()>0)
			{
				hrEisEmpMst=hrEmpList.get(0);
				empTyp=hrEisEmpMst.getEmpType();
				OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
				
				HrEisOtherDtls otherVONew = otherDao.getOtherData(hrEisEmpMst.getOrgEmpMst().getEmpId());
				logger.info("the other vo is "+otherVONew);
				if(otherVONew.getOtherId()!=0)
				{
					logger.info("employee type is=============>"+empTyp);
					
					MiscRecoverDAOImpl recovDao = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
					HrMiscRecoverDtls hrMiscRecoveryVO = new HrMiscRecoverDtls();
					List<HrMiscRecoverDtls> hrMiscRecoveryList=recovDao.getAllMiscDataByEmpId(otherVONew.getHrEisEmpMst());
					
					if(hrMiscRecoveryList!=null && hrMiscRecoveryList.size()>0)
					{
						Date recovDate = hrMiscRecoveryList.get(0).getRecoverDate();
						logger.info("entered date "+date.getMonth()+" recover date "+recovDate.getMonth());
						if(date.getMonth()==recovDate.getMonth()) 
						{
							loanMst.append("<eisEmpMapping>");
							loanMst.append("<eisEmpNO>").append("y").append("</eisEmpNO>");
							loanMst.append("<empTyp>").append(empTyp).append("</empTyp>");
							loanMst.append("</eisEmpMapping>");
						}
						else
						{
							loanMst.append("<eisEmpMapping>");
							loanMst.append("<eisEmpNO>").append("n").append("</eisEmpNO>");
							loanMst.append("<empTyp>").append(empTyp).append("</empTyp>");
							loanMst.append("</eisEmpMapping>");
						}
					}
				}
				else
				{
					logger.info("the other detail info is not entered");
					loanMst.append("<eisEmpMapping>");
					loanMst.append("<eisEmpNO>").append("1").append("</eisEmpNO>");
					loanMst.append("<empTyp>").append(empTyp).append("</empTyp>");
					loanMst.append("</eisEmpMapping>");
				}
				
			}
			else
			{
				logger.info("the emp info is not entered");
				loanMst.append("<eisEmpMapping>");
				loanMst.append("<eisEmpNO>").append("0").append("</eisEmpNO>");
				loanMst.append("<empTyp>").append(empTyp).append("</empTyp>");
				loanMst.append("</eisEmpMapping>");
			}
			String eisEmpMapping = new AjaxXmlBuilder().addItem("ajax_key", loanMst.toString()).toString();
	         
            logger.info(" the string buffer is :"+eisEmpMapping);   

            objServiceArgs.put("ajaxKey", eisEmpMapping);
	       	        
	        resultObject.setResultCode(ErrorConstants.SUCCESS);
	        resultObject.setResultValue(objServiceArgs);
	        resultObject.setViewName("ajaxData");
		}
		else
		{
			logger.info("Teh value of edit flag in getMiscData is------>"+editFlag);
			HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
			List <HrMiscRecoverDtls> miscList = new ArrayList();
		
			/*if(empIdStr!=null && !empIdStr.equals(""))
			{
				long empId = Long.parseLong(empIdStr);
				
				EmpInfoDAOImpl hrEmpDao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
				List<HrEisEmpMst> hrEmpList = hrEmpDao.getListByColumnAndValue("orgEmpMst.empId", empId);
				
				if(hrEmpList!=null && hrEmpList.size()>0)
				{
					hrEisEmpMst = hrEmpList.get(0);
				}
				miscList = miscRecoverDAO.getAllMiscDataByEmpId(hrEisEmpMst);
			}
			else
			{
				miscList = miscRecoverDAO.getAllMiscData();
			}*/
			
			long empId = 0;
			if(empIdStr!=null&&!empIdStr.equals(""))
			empId=Long.parseLong(empIdStr);
			EmpInfoDAOImpl empInfoDAOImpl = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
			
			long langId=StringUtility.convertToLong(loginDetails.get("langId").toString());
			/*long userId=Long.parseLong(loginDetails.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);*/
			
	      	/*List userList =empInfoDAOImpl.getUserListByLogin(langId,userId);
			List <OrgUserMst> orgUserList = new ArrayList();
	      	for (Iterator iter = userList.iterator(); iter.hasNext();)
	        {			 
		    Object[] row = (Object[])iter.next();	
	   		OrgUserMst userMst = new OrgUserMst();
	  		String id=row[0].toString();
	  		userMst.setUserId(Long.parseLong(id));
	  		orgUserList.add(userMst);
	        }*/
	      	
	      	miscList=miscRecoverDAO.getAllMiscData(locationCode,empId,langId);
	        //if(request.getParameter("edit")!= null && request.getParameter("edit").equals("Y"))
			if(editFlag != null && editFlag.equals("Y"))
	        {
				logger.info("****************1");
	        	logger.info("Inside getMiscData update");
	        	String miscid="";
	        	if(voToService.get("miscId")!=null && !voToService.get("miscId").equals(""))
	        	{
	        		miscid=voToService.get("miscId").toString();
	        	}
	        		
	        	HrMiscRecoverDtls actionList = miscRecoverDAO.getMiscDataByMiscId(miscid);
	            Long attach_id = actionList.getAttachment_Id();
	            logger.info("Attach_id : " + attach_id);
				if(attach_id!=null)
				{
					CmnAttachmentMstDAO mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
					
					CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attach_id);
								
					objServiceArgs.put("orderId",cmnAttachmentMst);
					logger.info("cmnattachmentmst : " + cmnAttachmentMst);
				}
				
	            
				List <RltBillTypeEdp> rltBillList = miscRecoverDAO.getRltBillTypeEdpData();	
				objServiceArgs.put("rltBillList", rltBillList);
	        	objServiceArgs.put("actionList", actionList);
	        	
	        	logger.info("the emp name is "+actionList.getHrEisEmpMst().getOrgEmpMst().getEmpFname());
		        resultObject.setResultCode(ErrorConstants.SUCCESS);
		        resultObject.setResultValue(objServiceArgs);
		        resultObject.setViewName("miscRecoverEdit");
	        	
	        }
	        
			else
			{
	        	logger.info("list size in getMiscData is------------>"+miscList.size());
	        	for(int i=0;i<miscList.size();i++)
	        	{
	        		HrMiscRecoverDtls hrMiscRecoverDtls=miscList.get(i);
            		logger.info("the value of the actionList.get(i) is ::"+miscList.get(i));
            		logger.info("the value of the start amount is ::"+hrMiscRecoverDtls.getRecoverAmt());
            		String tempBuffer="";
            		if(hrMiscRecoverDtls.getRecoverAmt()!=0)
            		{
            		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US );
                    logger.info( "Formatted as currency: " +currencyFormatter.format(hrMiscRecoverDtls.getRecoverAmt()) );
                    tempBuffer=currencyFormatter.format(hrMiscRecoverDtls.getRecoverAmt()).replace("$", "");
                    hrMiscRecoverDtls.setCurrencyrecoverAmt(tempBuffer.replace(".00", ""));
            		}
	        		
	        		
	        		
	        		
	        		
	        		logger.info("the emp name is "+miscList.get(i).getHrEisEmpMst().getOrgEmpMst().getEmpFname());
	        	}
	            //Map map = new HashMap();
	            objServiceArgs.put("miscList", miscList);
	            objServiceArgs.put("empName",empName);
	            resultObject.setResultCode(ErrorConstants.SUCCESS);
	            resultObject.setResultValue(objServiceArgs);
	            resultObject.setViewName("miscRecoverView");	   
			}
		}
		
		
    }
	/*catch(ClassCastException c)
	{
		logger.info("Exception in getScaleData");
		logger.error("Exception in getScaleData");
        resultObject.setResultValue(objServiceArgs);
        resultObject.setViewName("ScaleViewList");		
	}*/
	catch(Exception e)
	{		
		logger.error("Error is: "+ e.getMessage());
		logger.error("Exception in getMiscData");
        resultObject.setResultValue(objServiceArgs);

	}

	return resultObject;
}

public ResultObject insertMiscData(Map objectArgs) {
	// TODO Auto-generated method stub
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	try
	{
		logger.info("************Inside insertMiscData*****************");
		HrMiscRecoverDtls hrMiscRecvDtls = (HrMiscRecoverDtls)objectArgs.get("miscRecovery");
		logger.info( "The HrMiscRecoverDtls VO is " + hrMiscRecvDtls);

		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ResultObject resultObj = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);
		Map resultMap = (Map) resultObj.getResultValue();
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");

		String editFromVO = objectArgs.get("edit").toString();
		String FromBasicDtlsNew = objectArgs.get("FromBasicDtlsNew").toString();
		long otherId = Long.parseLong(objectArgs.get("otherId").toString());
		String empIdStr="";
		empIdStr=objectArgs.get("empId").toString();

		long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
		long userId=StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
		long dbId=StringUtility.convertToLong(loginDetailsMap.get("dbId").toString());
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginDetailsMap.get("locationVO");
		langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
		long postId=StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());

		objectArgs.put("FromBasicDtlsNew",FromBasicDtlsNew);
		objectArgs.put("otherId",otherId);
		logger.info("editFromVO insertBankMasterDtls" + editFromVO);

		long attachment_Id=0;
		if(resultMap.get("AttachmentId_orderId")!=null)
			attachment_Id = (Long) resultMap.get("AttachmentId_orderId"); 
		logger.info("after inserting attachment! attach_id : " + attachment_Id);		

		MiscRecoverServiceHelper helper = new MiscRecoverServiceHelper(serv);

		if(editFromVO!=null && editFromVO.equalsIgnoreCase("Y"))            	
		{

			helper.updateMiscellaneousDtls(hrMiscRecvDtls, postId, userId);
			msg=1;
			logger.info("Updated successfully................");
		}
		else if(editFromVO!=null && editFromVO.equalsIgnoreCase("N"))
		{
			logger.info("INSIDE CREATE insertMiscData");
			helper.insertMiscellaneousDtls(attachment_Id, userId, langId, locId, dbId, postId, hrMiscRecvDtls, empIdStr);

		}

		if(msg==1)
			objectArgs.put("MESSAGECODE",300006);
		else
			objectArgs.put("MESSAGECODE",300005);

		resultObject.setResultCode(ErrorConstants.SUCCESS);

		resultObject.setResultValue(objectArgs);
		if(msg==1)
			resultObject.setViewName("miscRecoverEdit");
		else
			resultObject.setViewName("miscRecoverMaster");
		logger.info("INSERTED SUCCESSFULLY insertMiscData");
	}
	catch(NullPointerException ne)
	{
		logger.info("Null Pointer Exception Ocuures...insertMiscData");
		logger.error("Error is: "+ ne.getMessage());
		objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("errorInsert");			
	}
	
	catch(Exception e){
		
		logger.info("Exception Ocuures...insertMiscData");
		 logger.error("Error is: "+ e.getMessage());
		 objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
		 resultObject.setResultValue(objectArgs);
		 resultObject.setViewName("errorInsert");
	}
	return resultObject;
}


public ResultObject fillMiscRecoverCombo(Map objectArgs)
{
	ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	
	try{
		logger.info("************Inside fillMiscRecoverCombo*****************");
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		MiscRecoverDAOImpl miscRecoverDAO = new MiscRecoverDAOImpl(HrMiscRecoverDtls.class,serv.getSessionFactory());
		List <RltBillTypeEdp> rltBillList = miscRecoverDAO.getRltBillTypeEdpData();
		logger.info("The size of rltBillList from fillMiscRecoverCombo service is------>>>"+rltBillList.size());
		String empname = "";
		if((request.getParameter("empName")!=null&&!request.getParameter("empName").equals("")))
			empname=request.getParameter("empName").toString();
		objectArgs.put("rltBillList", rltBillList);
		objectArgs.put("empName", empname);
		resultObject.setResultCode(ErrorConstants.SUCCESS);
		resultObject.setResultValue(objectArgs);
		resultObject.setViewName("miscRecoverMaster");
	}
	catch(Exception ex)
	{
		logger.info("There is some problem in fillMiscRecoverCombo service******");
		ex.printStackTrace();
	}
	return resultObject;
}
}