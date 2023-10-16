package com.tcs.sgv.eis.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.HrEisEmpTrnDaoImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpChgnameTxn;
import com.tcs.sgv.eis.valueobject.HrEisEmpChgnameTxnId;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgEmpInfo;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class EmpChangeNameServiceImpl  extends ServiceImpl implements EmpChangeNameService{
	
	private static final Log logger = LogFactory.getLog(EmpChangeNameServiceImpl.class);
	public ResultObject showEmpOldName(Map objectArgs)
	{
		logger.info("In side showEmpOldName=======");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
				long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				
				EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				OrgEmpMst empMstEng = empDAO.getEmployee(userId, 1l);
				OrgEmpMst empMstGuj = empDAO.getEmployee(userId, 2l);
				
				objectArgs.put("empMstEng", empMstEng);
				objectArgs.put("empMstGuj", empMstGuj);
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
				objRes.setViewName("EmpChangeName");
			}
		}
		catch (Exception e) 
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			logger.error(e);
		}
		return objRes;
	}
	
	
	public ResultObject submitEmpChangeNameReqService(Map objectArgs)
	{
		logger.info("In side submitEmpChangeNameReqService=======");
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try 
		{
			if (objRes != null && objectArgs != null) 
			{
				ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
				Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
				
				/* Getting a DB ID*/								 
				long dbId = Long.parseLong(loginDetailsMap.get("dbId").toString());
				CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,serv.getSessionFactory());
				CmnDatabaseMst cmnDatabaseMst=cmnDatabaseMstDaoImpl.read(dbId);
				/* End og DB Id code*/
				

				/* Getting a Loc ID Code*/
				long locId =  Long.parseLong(loginDetailsMap.get("locationId").toString());
				CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
				CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);				 
				/* End of Loc ID*/	
				
				//Getting code for created By
				long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
				OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
				OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
				
				/* Get The Person Post */
				long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
				OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
				OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
				/* End of the geting Person Post Code */
				
				CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv.getSessionFactory());
				CmnLanguageMst cmnLanguageMstEng = cmnLanguageMstDaoImpl.read(1l);
				CmnLanguageMst cmnLanguageMstGuj = cmnLanguageMstDaoImpl.read(1l);
				
				HrEisEmpTrnDaoImpl hrEisEmpTrnDaoImpl =new HrEisEmpTrnDaoImpl(HrEisEmpChgnameTxn.class,serv.getSessionFactory());
				
				EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
				OrgEmpMst orgEmpMstEng = empDAO.getEmployee(userId, 1l);
				OrgEmpMst orgEmpMstGuj = empDAO.getEmployee(userId, 2l);
				
				HrEisEmpChgnameTxn hrEisEmpChgnameTxnEng = (HrEisEmpChgnameTxn)objectArgs.get("hrEisEmpChgnameTxnEng");
				HrEisEmpChgnameTxn hrEisEmpChgnameTxnGujFromVOGen = (HrEisEmpChgnameTxn)objectArgs.get("hrEisEmpChgnameTxnGuj");
				
				HrmsCommonMessageServImpl msgServImpl =HrmsCommonMessageServImpl.getInstance();
				
				if(hrEisEmpChgnameTxnEng!=null)
				{
					if(!hrEisEmpChgnameTxnEng.getEmpPrefix().equals(""))
					{
						hrEisEmpChgnameTxnEng.setEmpPrefix(hrEisEmpChgnameTxnEng.getEmpPrefix());
					}
					
					hrEisEmpChgnameTxnEng.setActionFlag("P");
					
					if(hrEisEmpChgnameTxnEng.getGazetteDate()!=null)
					{
						hrEisEmpChgnameTxnEng.setGazetteDate(hrEisEmpChgnameTxnEng.getGazetteDate());
					}
					
					if(hrEisEmpChgnameTxnEng.getGazetteEstartDate()!=null)
					{
						hrEisEmpChgnameTxnEng.setGazetteEstartDate(hrEisEmpChgnameTxnEng.getGazetteEstartDate());
					}
					
					if(!hrEisEmpChgnameTxnEng.getGazetteNo().equals(""))
					{
						hrEisEmpChgnameTxnEng.setGazetteNo(hrEisEmpChgnameTxnEng.getGazetteNo());
					}
					
					if(!hrEisEmpChgnameTxnEng.getIssuingAutho().equals(""))
					{
						hrEisEmpChgnameTxnEng.setIssuingAutho(hrEisEmpChgnameTxnEng.getIssuingAutho());
					}
				}
				hrEisEmpChgnameTxnEng.setCmnDatabaseMst(cmnDatabaseMst);
				hrEisEmpChgnameTxnEng.setCmnLocationMst(cmnLocationMst);
				hrEisEmpChgnameTxnEng.setOrgPostMst(orgPostMst);
				hrEisEmpChgnameTxnEng.setOrgUserMst(orgUserMst);
				hrEisEmpChgnameTxnEng.setCreatedDate(new Date());
				
				HrEisEmpChgnameTxn hrEisEmpChgnameTxnGuj = hrEisEmpChgnameTxnEng.cloneTake();
				
				if(hrEisEmpChgnameTxnGuj!=null)
				{	
					if(!hrEisEmpChgnameTxnGuj.getEmpFname().equals(""))
					{
						hrEisEmpChgnameTxnGuj.setEmpFname(hrEisEmpChgnameTxnGujFromVOGen.getEmpFname());
					}
					
					if(!hrEisEmpChgnameTxnGuj.getEmpMname().equals(""))
					{
						hrEisEmpChgnameTxnGuj.setEmpMname(hrEisEmpChgnameTxnGujFromVOGen.getEmpMname());
					}
					
					if(!hrEisEmpChgnameTxnGuj.getEmpLname().equals(""))
					{
						hrEisEmpChgnameTxnGuj.setEmpLname(hrEisEmpChgnameTxnGujFromVOGen.getEmpLname());
					}
				}
				
				if(hrEisEmpChgnameTxnEng!=null)
				{
					if(!hrEisEmpChgnameTxnEng.getEmpFname().equals(""))
					{
						hrEisEmpChgnameTxnEng.setEmpFname(hrEisEmpChgnameTxnEng.getEmpFname());
					}
					
					if(!hrEisEmpChgnameTxnEng.getEmpMname().equals(""))
					{
						hrEisEmpChgnameTxnEng.setEmpMname(hrEisEmpChgnameTxnEng.getEmpMname());
					}
					if(!hrEisEmpChgnameTxnEng.getEmpLname().equals(""))
					{
						hrEisEmpChgnameTxnEng.setEmpLname(hrEisEmpChgnameTxnEng.getEmpLname());
					}
				}
				
				if(orgEmpMstEng!=null)
				{
					hrEisEmpChgnameTxnEng.setOrgEmpMst(orgEmpMstEng);
				}
				
				if(orgEmpMstGuj!=null)
				{
					hrEisEmpChgnameTxnGuj.setOrgEmpMst(orgEmpMstGuj);
				}
				
				if(cmnLanguageMstEng!=null)
				{
					hrEisEmpChgnameTxnEng.setCmnLanguageMst(cmnLanguageMstEng);
				}
				if(cmnLanguageMstGuj!=null)
				{
					hrEisEmpChgnameTxnGuj.setCmnLanguageMst(cmnLanguageMstGuj);
				}
				
				ResultObject resultObj = serv.executeService("FILE_UPLOAD_VOGEN", objectArgs);
				objectArgs = (Map) resultObj.getResultValue();
	    	    resultObj = serv.executeService("FILE_UPLOAD_SRVC", objectArgs);

	    	    if(objectArgs.get("AttachmentId_attachmentLink")!=null)
				{	 
					long cmnAttachmentMstId= Long.parseLong(objectArgs.get("AttachmentId_attachmentLink").toString());
					CmnAttachmentMst cmnAttachmentMst = new CmnAttachmentMst();
					cmnAttachmentMst.setAttachmentId(cmnAttachmentMstId);
 
					hrEisEmpChgnameTxnEng.setCmnAttachmentMst(cmnAttachmentMst);
					hrEisEmpChgnameTxnGuj.setCmnAttachmentMst(cmnAttachmentMst);						
				}
				long reqId=0;
				reqId = msgServImpl.createCorr(objectArgs, serv, "300841", "Change Employee Name Correspondence");
				
				logger.info("reqId==="+reqId);
				HrEisEmpChgnameTxnId eisEmpChgnameTxnIdEng = new HrEisEmpChgnameTxnId();
				HrEisEmpChgnameTxnId eisEmpChgnameTxnIdGuj = new HrEisEmpChgnameTxnId();
				
				if(reqId!=0)
				{
					eisEmpChgnameTxnIdEng.setReqId(reqId);
					eisEmpChgnameTxnIdGuj.setReqId(reqId);
					eisEmpChgnameTxnIdEng.setLangId(1l);
					eisEmpChgnameTxnIdGuj.setLangId(2l);
				}
				
				if(eisEmpChgnameTxnIdEng!=null)
				{
					hrEisEmpChgnameTxnEng.setId(eisEmpChgnameTxnIdEng);
				}
				
				if(eisEmpChgnameTxnIdGuj!=null)
				{
					hrEisEmpChgnameTxnGuj.setId(eisEmpChgnameTxnIdGuj);
				}
				
				hrEisEmpTrnDaoImpl.create(hrEisEmpChgnameTxnEng);
				hrEisEmpTrnDaoImpl.create(hrEisEmpChgnameTxnGuj);
				
				
				objectArgs.put("reqId", reqId);
				
				final long MODID_ADV=300841;
				objectArgs.put("modId", MODID_ADV);
				logger.info("reqId==="+reqId);
				objectArgs.put("appReqId", reqId);
				msgServImpl.createModEmpRltTuple(serv, objectArgs, userId);
				objRes=msgServImpl.getForwardToDetail(serv, objectArgs, "300841", reqId);   
				 
				String msg="EMP_CHANGE_NAME_REQUEST";
				objectArgs.put("status", "PENDING"); 
				objectArgs.put("msg", msg);
				
				objRes.setResultValue(objectArgs);
				objRes.setResultCode(ErrorConstants.SUCCESS);
			}
		}
		catch (Exception e) 
		{
			objRes.setResultCode(ErrorConstants.ERROR);
			logger.error(e);
		}
		return objRes;
	}
	
	public ResultObject getReqIdForChangeNameService(Map objectArgs)
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
			
		try
		{
			long req_Id=Long.parseLong(objectArgs.get("ReqId").toString());  
			   
			ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");	
			
			CmnAttachmentMstDAO cmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(CmnAttachmentMst.class,serv.getSessionFactory());
			HrEisEmpTrnDaoImpl hrEisEmpTrnDaoImpl = new HrEisEmpTrnDaoImpl(HrEisEmpChgnameTxn.class,serv.getSessionFactory());
			
			List empChangeTxnLst = hrEisEmpTrnDaoImpl.getListByColumnAndValue("id.reqId", req_Id);
			
			long userId=0;
			Date crtdDate=null;
			
			HrEisEmpChgnameTxn eisEmpChgnameTxn = null;
			HrEisEmpChgnameTxn eisEmpChgnameTxnEng = new HrEisEmpChgnameTxn();
			HrEisEmpChgnameTxn eisEmpChgnameTxnGuj = new HrEisEmpChgnameTxn();
			
			Iterator iterator = empChangeTxnLst.iterator();
			while(iterator.hasNext())
			{
				eisEmpChgnameTxn=(HrEisEmpChgnameTxn)iterator.next();
				
				if(eisEmpChgnameTxn.getId().getLangId()==1)
				{
					eisEmpChgnameTxnEng = eisEmpChgnameTxn;
					crtdDate = eisEmpChgnameTxn.getCreatedDate();
				}
				else if(eisEmpChgnameTxn.getId().getLangId()==2)
				{
					eisEmpChgnameTxnGuj = eisEmpChgnameTxn;
				}
			}
			
			if(eisEmpChgnameTxn!=null && eisEmpChgnameTxn.getCmnAttachmentMst()!=null && eisEmpChgnameTxn.getCmnAttachmentMst().getAttachmentId()!=0)
			{
				CmnAttachmentMst attachmentMst = cmnAttachmentMstDAO.findByAttachmentId(eisEmpChgnameTxn.getCmnAttachmentMst().getAttachmentId());
				objectArgs.put("attachmentLink", attachmentMst);
			}
			
			EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());
			OrgEmpMst orgEmpMst = null;			
			if(eisEmpChgnameTxnEng.getOrgEmpMst()!=null && eisEmpChgnameTxnEng.getOrgEmpMst().getEmpId()!=0)
			{
				orgEmpMst = empDAO.getEmployeeVO(eisEmpChgnameTxnEng.getOrgEmpMst().getEmpId());
			}
			
			if(orgEmpMst != null && orgEmpMst.getOrgUserMst() != null)
			{
				userId = orgEmpMst.getOrgUserMst().getUserId();
			}
			
			OrgEmpInfo orgEmpInfoForEng = empDAO.getEmpMstRecordByUserIdandLangIdandCrtdDate(userId, 1l, crtdDate);
			OrgEmpInfo orgEmpInfoForGuj = empDAO.getEmpMstRecordByUserIdandLangIdandCrtdDate(userId, 2l, crtdDate);
			
			objectArgs.put("empMstEng", orgEmpInfoForEng);
			objectArgs.put("empMstGuj", orgEmpInfoForGuj);
			objectArgs.put("eisEmpChgnameTxnEng", eisEmpChgnameTxnEng);
			objectArgs.put("eisEmpChgnameTxnGuj", eisEmpChgnameTxnGuj);
			objectArgs.put("flag",true);
			
			resultObject.setResultCode(ErrorConstants.SUCCESS);	
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("ApproveEmpChangeName");
		}
		catch (Exception e) 
		{
			logger.error(e);
		}
		return resultObject;
	}
	
	
	public ResultObject approveEmpChangeNameRequest(Map objectArgs) 
	{
		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
	    try 
	    {	  
	    	ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
	    	Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
	    	
	    	//Getting code for created By
			long userId = StringUtility.convertToLong(loginDetailsMap.get("userId").toString());
			OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
			OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);
			
			/* Get The Person Post */
			long postId = Long.parseLong(loginDetailsMap.get("loggedInPost").toString());
			OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
			OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
			/* End of the geting Person Post Code */
	    	
	    	long reqId=0;
	    	reqId =Long.parseLong(objectArgs.get("corrId").toString());
			logger.info("corrs_Id======reqId======"+reqId);
			EmpDAO empDAO = new EmpDAOImpl(OrgEmpMst.class,serv.getSessionFactory());  
			OrgEmpMst orgEmpMst = new OrgEmpMst();
			HrEisEmpTrnDaoImpl hrEisEmpTrnDaoImpl = new HrEisEmpTrnDaoImpl(HrEisEmpChgnameTxn.class,serv.getSessionFactory());
				
			List empChangeTxnLst = hrEisEmpTrnDaoImpl.getListByColumnAndValue("id.reqId", reqId);
				
			HrEisEmpChgnameTxn eisEmpChgnameTxn = null;
			Iterator iterator = empChangeTxnLst.iterator();
			Date currDate = new Date();
			while(iterator.hasNext())
			{
				eisEmpChgnameTxn=(HrEisEmpChgnameTxn)iterator.next();
				
				if(eisEmpChgnameTxn!=null && eisEmpChgnameTxn.getId()!=null)
				{
					eisEmpChgnameTxn=(HrEisEmpChgnameTxn) hrEisEmpTrnDaoImpl.read(eisEmpChgnameTxn.getId());
					eisEmpChgnameTxn.setActionFlag("A");
					
					if(eisEmpChgnameTxn.getOrgEmpMst()!=null && eisEmpChgnameTxn.getOrgEmpMst().getEmpId()!=0)
					{
						orgEmpMst=empDAO.read(eisEmpChgnameTxn.getOrgEmpMst().getEmpId());
						orgEmpMst.setEmpFname(eisEmpChgnameTxn.getEmpFname());
						orgEmpMst.setEmpLname(eisEmpChgnameTxn.getEmpLname());
						orgEmpMst.setEmpMname(eisEmpChgnameTxn.getEmpMname());
						orgEmpMst.setEmpPrefix(eisEmpChgnameTxn.getEmpPrefix());
						orgEmpMst.setUpdatedDate(currDate);
						orgEmpMst.setOrgPostMstByUpdatedByPost(orgPostMst);
						orgEmpMst.setOrgUserMstByUpdatedBy(orgUserMst);
						empDAO.update(orgEmpMst);
					}
					hrEisEmpTrnDaoImpl.update(eisEmpChgnameTxn);
					
				}
			}  
	    	resultObject.setResultCode(ErrorConstants.SUCCESS);
	    	resultObject.setResultValue(objectArgs);
		} 
	    catch (Exception e)
	    {
	    	resultObject.setResultCode(ErrorConstants.ERROR);
			resultObject.setResultValue(objectArgs);
			logger.error("Error While Doing Approval of User Information ",e);
		}
	    return resultObject;
	}
}
