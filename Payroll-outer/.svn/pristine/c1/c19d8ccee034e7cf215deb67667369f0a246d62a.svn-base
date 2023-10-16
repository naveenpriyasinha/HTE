
package com.tcs.sgv.billproc.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.dao.AclMstRoleDaoImpl;
import com.tcs.sgv.acl.dao.AclPostRoleRltDaoImpl;
import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.acl.valueobject.AclPostroleRlt;
import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.billproc.common.dao.ShowBillDAOImpl;
import com.tcs.sgv.billproc.common.valueobject.TrnShowBill;
import com.tcs.sgv.billproc.counter.valueobject.NewBillVO;
import com.tcs.sgv.common.dao.BptmCommonServicesDAOImpl;
import com.tcs.sgv.common.dao.PostConfigurationDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.service.DDOInfoServiceImpl;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;

/**
 * ShowBillServiceImpl 
 * This class opens the bill in editable mode to end-user
 * with all values related values of that respective bill. It also loads the set
 * of initial values required in bill. It also shows the attachments attached
 * with that bill.
 * 
 * Date of Creation : 13th July 2007 
 * Author : Hiral Shah
 * 
 * Revision History 
 * =====================
 * Hiral Shah 25-Oct-2007   Changes for Auditor filteration by bill type(Subject Id : 12,23,24)
 * Hiral Shah 26-Oct-2007	Changes for getting Bill Type also from NewBillVO(in case of 'Save And New')
 * Hiral Shah 26-Oct-2007	Changes for Bill Type Code and its validation
 *  
 *  
 */
public class ShowBillServiceImpl
	extends ServiceImpl 
	implements ShowBillService
{

	Log logger = LogFactory.getLog(getClass());
	TrnShowBill billListVO = null;
	ResourceBundle gObjRsrcBndle =ResourceBundle.getBundle("resources/billproc/BillprocConstants");
	
	/**
	 * Method to get the values of particular Bill
	 * @param  Map : objectArgs
	 * 
	 * @return ResultObject
	 */
	public ResultObject showBillDetails(Map objectArgs)
	{				
		logger.info("Inside showBillDetailsssssssss, map is : " +objectArgs.toString());
		
		ResultObject objRes = new ResultObject();
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		logger.info("vvvvvvvvvvvValue of Objection Code for NET AMOUNT MISMATCH : " +request.getParameter("audAmtFlag"));
		Long lLngUserId = SessionHelper.getUserId(request);
		Long lLngLangId = SessionHelper.getLangId(objectArgs);		
		Long lLngDbId = SessionHelper.getDbId(objectArgs);
		Long lLngPostId = SessionHelper.getPostId(objectArgs);
		List lLstTemp = new ArrayList();
		int i=0;
		int lAudAmtFlag = 0;
		try
		{
			if(objRes != null)
			{				
				ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
				// getting ROLE for approving authority
			 	LoginDetails loginDetails = (LoginDetails)objectArgs.get("baseLoginVO");
			 	AclMstRoleDaoImpl aclRoleMstDAO = new  AclMstRoleDaoImpl(AclRoleMst.class,serv.getSessionFactory());
			 	AclRoleMst aclRoleMst =  aclRoleMstDAO.read((long)100050);			 	
			 
			 	
			 	
			 	AclPostRoleRltDaoImpl postRoleDAO = new AclPostRoleRltDaoImpl(AclPostroleRlt.class,serv.getSessionFactory());
			 	List roleList = postRoleDAO.getRolesMappedToPost(lLngPostId);
				if(roleList != null && roleList.size() >0)
				{
					for(int k=0;k<roleList.size();k++)
					{
						AclPostroleRlt postRoleRlt = (AclPostroleRlt)roleList.get(k);
						if(postRoleRlt.getAclRoleMst().getRoleId() ==  (long)100050)
						{
							objectArgs.put("ApprovingAuth", "YES");
						}
					}
				}
					 
			 	
				ShowBillDAOImpl lObjShowBillDAOImpl = new ShowBillDAOImpl(TrnShowBill.class,serv.getSessionFactory());				
				
				Long lLngBillNo = Long.parseLong(request.getParameter("BillNo"));
				objectArgs.put("billNo", lLngBillNo);
				billListVO = lObjShowBillDAOImpl.getBilldetails(objectArgs);
				
				List lLstDept = null;
/*		Changes for auditor Filteration : Hiral	 */ 				
				objectArgs.put("ShowBillVO", billListVO);
				objRes = loadValues(objectArgs);
/*	Ends : Changes for auditor Filteration : Hiral	 */				
				DDOInfoServiceImpl lObjDDOInfoSrvcImpl = new DDOInfoServiceImpl();
				lLstDept =  lObjDDOInfoSrvcImpl.getBillOfficeForDDO(serv, billListVO.getDdoCode(), lLngLangId, lLngDbId);
				if(lLstDept!=null && lLstDept.size() > 0)
				{
					logger.info("Value of List Department before add All : " +lLstDept.toString() +" and SIZE is : " +lLstDept.size());
					lLstTemp.addAll(lLstDept);
					logger.info("Value of Temp List in DDO after Add All : " +lLstTemp.toString() +" and SIZE IS : " +lLstTemp.size());
					
					CommonVO lCmnVo = new CommonVO();
					lCmnVo.setCommonDesc("--Select--");
					lCmnVo.setCommonId("-1");
					
					lLstDept.add(0, lCmnVo);
					for(i=1;i<lLstTemp.size()-1;i++)
					{
						logger.info("Inside loop : " +i +" from List : " +lLstTemp.get(i-1));
						lLstDept.add(i,lLstTemp.get(i-1));
					}
				}
				else
				{
					CommonVO lCmnVo = new CommonVO();
					lCmnVo.setCommonDesc("--Select--");
					lCmnVo.setCommonId("-1");
					lLstDept.add(0, lCmnVo);
				}
				logger.info("Department List in ShowBillServiceImpl  :: " +lLstDept.toString());
				objectArgs.put("showDeptList",lLstDept);
				if(billListVO.getAttachId()!=null)
				{
					Long attachId = billListVO.getAttachId(); 
					CmnAttachmentMstDAOImpl mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
					CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(attachId);					
					objectArgs.put("attachment",cmnAttachmentMst);					
				}				
				objectArgs.put("ShowBillVO",billListVO);
				objectArgs.put("currRemarks", lObjShowBillDAOImpl.setLastRemarks(lLngBillNo.toString(), lLngUserId));
				objectArgs.put("billStatus", billListVO.getStatus());
				logger.info("Value of Integer Flag : " +objectArgs.get("intFlag"));
				
				Integer lIntFlag = null;
				if(objectArgs.get("intFlag")!=null && objectArgs.get("intFlag").toString().length()>0)
				lIntFlag = (Integer)objectArgs.get("intFlag");
				
				logger.info("Status from Update PhyBill : " +lIntFlag);
				if(lIntFlag!=null)
				{					
					if(lIntFlag == 0)
					{
						logger.info("-----------------Inside Invalid Head Structure");
						objectArgs.put("MESSAGECODE",(long)1044);
						objectArgs.put("StateMsg", "Invalid Head Structure");
					}
					if(lIntFlag == -2)
					{
						logger.info("---------------Inside Invalid Challan Major Head");
						objectArgs.put("StateMsg","Invalid Challan Major Head");
						objectArgs.put("MESSAGECODE",(long)1049);
					}
					if(lIntFlag == -1)
					{
						logger.info("-----------------Inside Invalid Previous Bill No.");
//						objectArgs.put("$S$",billListVO.getPrevBillNo());
						objectArgs.put("MESSAGECODE",(long)1042);
						objectArgs.put("StateMsg", "Invalid Previous Bill No.");
					}
/*		Validating bill Type Code   	*/					
					if(lIntFlag == -3)
					{
						logger.info("-----------------Inside Invalid code for Bill Type");
//						objectArgs.put("$S$",billListVO.getPrevBillNo());
						objectArgs.put("MESSAGECODE",(long)2048);
						objectArgs.put("StateMsg", "Invalid Code For Bill Type");
					}
/* 	Ends :	Validating bill Type Code   */					
					if(lIntFlag > 0)
					{
						if(request.getParameter("audAmtFlag")!=null && request.getParameter("audAmtFlag").length()>0)
						{
							lAudAmtFlag = Integer.parseInt(request.getParameter("audAmtFlag"));
							if(lAudAmtFlag==1)
							{
								BptmCommonServiceImpl.raiseObjection(gObjRsrcBndle.getString("CMN.AudNetAmtObj"), lLngBillNo, objectArgs);		
							}
						}
						
						logger.info("---------------Inside save success");
						objectArgs.put("StateMsg","Bill Saved Successfully");
						objectArgs.put("MESSAGECODE",(long)1045);
						
					}
					objRes.setViewName("ajaxData");
				}
				else
				{
					objRes.setViewName("showBillDetails");	
				}
				objectArgs.put("StatusMsg", objectArgs.get("StateMsg"));
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				logger.info("Value of StatusMessage : " +objectArgs.get("StateMsg"));
			}					
		}
		catch(Exception e)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error(" Error in ShowBillDetails " + e,e);
		}
		return objRes;
	}
	
	/**
	 * Method to get the attached files for particular Bill.
	 * @param  Map : objectArgs
	 * 
	 * @return ResultObject
	 */
	public ResultObject openAttachments(Map objectArgs)
	{
		ResultObject objRes = new ResultObject();
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session = request.getSession();
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
		ShowBillDAOImpl lObjShowBillDAOImpl = new ShowBillDAOImpl(TrnShowBill.class,serv.getSessionFactory());
		CmnAttachmentMstDAOImpl mnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
		
		Long AttachmentId = null;
		try
		{
			Long billNo = request.getParameter("BillNo")!=null?Long.parseLong(request.getParameter("BillNo")):null;
			if(billNo!=null)
				AttachmentId = lObjShowBillDAOImpl.getAttachId(billNo);
			Long lLngSesAttchId = null;
			
			if(session.getAttribute("AttachID")!=null && session.getAttribute("AttachID").toString().length() > 0)
				lLngSesAttchId = Long.parseLong(session.getAttribute("AttachID").toString());		
	
			if(AttachmentId!=null)
			{
				CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(AttachmentId);
				objectArgs.put("attachment",cmnAttachmentMst);					
			}
			else
			if(lLngSesAttchId!=null)
			{
				CmnAttachmentMst cmnAttachmentMst = mnAttachmentMstDAO.findByAttachmentId(lLngSesAttchId);
				objectArgs.put("attachment",cmnAttachmentMst);					
			}
			
			objRes.setResultValue(objectArgs);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setViewName("cmnAttachment");			
		}
		catch(Exception e)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error(" Error in openAttachments " + e,e);
		}
		return objRes;
	}
	
	/**
	 * Method to load initial values from lookup and Database in selection
	 * @param : Map
	 * 
	 * @return : ResultObject 
	 */
	
	public ResultObject loadValues(Map inputMap)
	{
		logger.info("Inside Load Values");
/*	Changes for auditor Filteration 	*/		
		TrnShowBill billListVO = null;
		NewBillVO lObjNewBillVO = null;
		String lStrBillType = null;
		if(inputMap.get("ShowBillVO")!=null)
		{
			billListVO = (TrnShowBill)inputMap.get("ShowBillVO");
			logger.info("Inside if map not nll, VO  is : " +billListVO.getBillType());
			lStrBillType = String.valueOf(billListVO.getBillType());
		}
		if(inputMap.get("NewBillVO")!=null)
		{
			lObjNewBillVO = (NewBillVO)inputMap.get("NewBillVO");
			logger.info("Inside if NEW BILL VO map not nll, VO  is : " +lObjNewBillVO.getBillType());
			lStrBillType = String.valueOf(lObjNewBillVO.getBillType());
		}
		logger.info("Value in loadValues form ShowBill VO : " +lStrBillType);
/*	Ends : Changes for auditor Filteration 	*/		
		ResultObject objRes = new ResultObject();
		ServiceLocator serv = (ServiceLocator)inputMap.get("serviceLocator");
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		String lLngOfficeCode= SessionHelper.getLocationCode(inputMap);
		ResourceBundle lObjRsrcBndle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
		String lStrBranchCode = null;
		
		PostConfigurationDAOImpl lObjPostConfDaoImpl = new PostConfigurationDAOImpl(ConfigAudSelection.class,serv.getSessionFactory());
		BptmCommonServicesDAOImpl lObjCmnSrvcDAOImpl = new BptmCommonServicesDAOImpl(TrnBillRegister.class,serv.getSessionFactory()); 		
						
		try
		{
			List lLstBillCode = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getObject("CMN.BillCode").toString(), lLngLangId, inputMap);
			inputMap.put("BillCodeList",lLstBillCode);
		
			List lLstTcBill = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getObject("CMN.TcBillType").toString(), lLngLangId, inputMap);
			inputMap.put("TcBillList",lLstTcBill);
			
			List lLstGoNgo = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getObject("CMN.GoNgo").toString(), lLngLangId, inputMap);
			inputMap.put("goNgoList",lLstGoNgo);	
			
			List lLstBudType = BptmCommonServiceImpl.getLookupValues(lObjRsrcBndle.getObject("CMN.BudgetType").toString(), lLngLangId, inputMap);
			Object[] lObjTemp = lLstBudType.toArray();
            Object[] lObjBudjetType = new Object[lObjTemp.length];
            for (Object lObj : lObjTemp)
            {
                CmnLookupMst lObjComVO = (CmnLookupMst) lObj;
                lObjBudjetType[Integer.parseInt(String.valueOf(lObjComVO.getLookupShortName())) - 1] = (Object) lObjComVO;
            }
            lObjTemp = null;

			inputMap.put("planList",lObjBudjetType);
			
			List lLstBillType = lObjCmnSrvcDAOImpl.getBillType(lLngLangId);			
			inputMap.put("BillType", lLstBillType);
		
			List lLstDept = BptmCommonServiceImpl.getDeptList(inputMap); 
			inputMap.put("DeptList",lLstDept);

/*	Changes for auditor filteration on bill Type.   - Hiral */			
			if(lStrBillType!=null)
			{
				if(lStrBillType.equals("12") || lStrBillType.equals("23") || lStrBillType.equals("24"))
					lStrBranchCode = gObjRsrcBndle.getString("CMN.BillTypeBranchId");						
				else
					lStrBranchCode = gObjRsrcBndle.getString("CMN.BranchId");
			}
/*	Ends : Changes for auditor filteration on bill Type.   - Hiral */			
			
			logger.info("Value of Branch ID in loadValues ---------->>>>> in showBillsErvice : " +lStrBranchCode);
			inputMap.put("audList", lObjPostConfDaoImpl.getEmpsList(lLngOfficeCode, lStrBranchCode));
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(inputMap);
		}
		catch(Exception e)
		{
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			logger.error(" Error in loadValues " + e,e);
		}
		return objRes;
	}
}
