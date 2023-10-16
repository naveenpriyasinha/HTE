package com.tcs.sgv.common.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.time.Year;

import com.tcs.sgv.common.dao.CmnBranchMstDao;
import com.tcs.sgv.common.dao.CmnBranchMstDaoImpl;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.fms.dao.CmnProjectMstDao;
import com.tcs.sgv.fms.dao.CmnProjectMstDaoImpl;
import com.tcs.sgv.fms.dao.WfDocMstDao;
import com.tcs.sgv.fms.dao.WfDocMstDaoImpl;
import com.tcs.sgv.fms.dao.WfOrgLocMpgMstDao;
import com.tcs.sgv.fms.dao.WfOrgLocMpgMstDaoImpl;
import com.tcs.sgv.fms.dao.WfOrgPostMpgMstDao;
import com.tcs.sgv.fms.dao.WfOrgPostMpgMstDaoImpl;
import com.tcs.sgv.fms.dao.WfOrgUsrMpgMstDao;
import com.tcs.sgv.fms.dao.WfOrgUsrMpgMstDaoImpl;
import com.tcs.sgv.fms.valueobject.CmnProjectMst;
import com.tcs.sgv.fms.valueobject.FmsFileMst;
import com.tcs.sgv.fms.valueobject.WfDocMst;
import com.tcs.sgv.fms.valueobject.WfOrgLocMpgMst;
import com.tcs.sgv.fms.valueobject.WfOrgPostMpgMst;
import com.tcs.sgv.fms.valueobject.WfOrgUsrMpgMst;

/**
 *  @author 217977 (Rachit Mehta)
 * 
 Pass Parameter :-
    objectArgs.put("Subject",DOC_ID);----From wf_doc_mst
 	objectArgs.put("Description",Application Name);
	objectArgs.put("section",Branch ID );//"100000"-----From org_postdtls_rlt For logged in post
	objectArgs.put("letterno",Tri_Code);//Tri code----- From wf_doc_mst
	objectArgs.put("SubCode",Sub_Code);----> Hardcoded "10" OR "11". 
 
 Note :-
 	This File is created in the inbox of loggedin User.
 	
 Return :-
 	This return you ResultObject from that get Map and Get the File ID.
 	Ex :-
 	 ResultObject objres = serv.executeService("CREATEFMSFILEVO", objectArgs);
 		Map resultMap = (Map) objres.getResultValue();
 		long l = (Long)resultMap.get("fileId");
 		
 If file needs to be forwarded at the time of creation also pass the following parameter.
 		objectArgs.put("postIdForwardTo",post Id);
 		
 */


public class CreateFileUtilImpl extends ServiceImpl 
{
	Log logger = LogFactory.getLog(getClass());
	public ResultObject CreateFmsFileVO(Map objectArgs) 
	{
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}

			//Coded By Rachit (217977)
			ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");
			Map loginMap = (Map)objectArgs.get("baseLoginMap");

			String subject=(String)objectArgs.get("Subject");
			
			FmsFileMst fmsFileMst=new FmsFileMst();
			long lookupId;String confidentiality=null,fileStage=null,fileStatus=null,filePrority=null,approveStatus=null;
			
			 String Description=(String)objectArgs.get("Description");
			 
			 ResourceBundle lResourceBundle =  ResourceBundle.getBundle("resources/wf/WfConstants");	
			 String projectId=lResourceBundle.getString("ProjectId");
			 fileStatus=lResourceBundle.getString("fileStatus");
			 fileStage=lResourceBundle.getString("fileStage");
			 filePrority=lResourceBundle.getString("filePrority");
			 approveStatus=lResourceBundle.getString("approveStatus");
			 confidentiality=lResourceBundle.getString("confidentiality");
			 
			 
			 /*    file no  */
			 
			 long lbranchId = Long.parseLong((String)objectArgs.get("section")) ;
			 CmnBranchMstDao lcmnBranchMstDao = new CmnBranchMstDaoImpl(CmnBranchMst.class,servLoc.getSessionFactory());
			 CmnBranchMst lcmnBranchMst = lcmnBranchMstDao.read(lbranchId);
			 
			 String letterno=(String)objectArgs.get("letterno");
			 String subCode=(String)objectArgs.get("SubCode");
			 
			 objectArgs.put("BranchCode",lcmnBranchMst.getBranchCode());
			 objectArgs.put("Year",new Year().toString());
			 objectArgs.put("SubCode",subCode);
			 objectArgs.put("TriLetter",letterno);
			 objectArgs.put("BranchMst",lcmnBranchMst);
			 objectArgs.put("DocumentFlag","Y");
			 
			 /* end of file no  */
			 
			 
			 fmsFileMst.setCmnBranchMst(lcmnBranchMst);
			 
			 long lUserId = Long.parseLong(loginMap.get("userId").toString());
			 WfOrgUsrMpgMstDao lwfOrgUsrMpgMstDao = new WfOrgUsrMpgMstDaoImpl(WfOrgUsrMpgMst.class,servLoc.getSessionFactory());
			 WfOrgUsrMpgMst wfOrgUsrMpgMstByLstUpdUsr = lwfOrgUsrMpgMstDao.read(String.valueOf(lUserId));
			 fmsFileMst.setWfOrgUsrMpgMstByCrtUsr(wfOrgUsrMpgMstByLstUpdUsr);
			 
			 long lpostId = Long.parseLong(loginMap.get("loggedInPost").toString());
			// long lpostId =Long.parseLong((String)objectArgs.get("Post"));
			 //System.out.println("lpostId in FmsFileVogenImpl"+lpostId);
			 WfOrgPostMpgMstDao lwfOrgPostMpgMstDao = new WfOrgPostMpgMstDaoImpl(WfOrgPostMpgMst.class,servLoc.getSessionFactory());
			 WfOrgPostMpgMst lwfOrgPostMpgMstByCrtPost = lwfOrgPostMpgMstDao.read(String.valueOf(lpostId));
			 fmsFileMst.setWfOrgPostMpgMstByCrtPost(lwfOrgPostMpgMstByCrtPost);	
		//	 fmsFileMst.setWfOrgPostMpgMstByOwnerPostId(lwfOrgPostMpgMstByCrtPost);	
			 
			 long llocId =  Long.parseLong(loginMap.get("locationId").toString());
			 WfOrgLocMpgMstDao lwfOrgLocMpgMstDao = new WfOrgLocMpgMstDaoImpl(WfOrgLocMpgMst.class,servLoc.getSessionFactory());
			 WfOrgLocMpgMst lwfOrgLocMpgMstByCrtLoc = lwfOrgLocMpgMstDao.read(String.valueOf(llocId));
			 fmsFileMst.setWfOrgLocMpgMstByCrtLoc(lwfOrgLocMpgMstByCrtLoc);		
			 
			 
			 long ldbId = Long.parseLong(loginMap.get("dbId").toString());
			 CmnDatabaseMstDaoImpl lcmnDatabaseMstDaoImpl=new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class,servLoc.getSessionFactory());
			 CmnDatabaseMst lcmnDatabaseMst=lcmnDatabaseMstDaoImpl.read(ldbId);
			 fmsFileMst.setCmnDatabaseMst(lcmnDatabaseMst);
			
			 lwfOrgLocMpgMstByCrtLoc = lwfOrgLocMpgMstDao.read(String.valueOf(llocId));
			 fmsFileMst.setWfOrgLocMpgMstByLocId(lwfOrgLocMpgMstByCrtLoc);
			 
			 CmnProjectMstDao lcmnProjectMstDao = new CmnProjectMstDaoImpl(CmnProjectMst.class,servLoc.getSessionFactory());
			 CmnProjectMst lcmnProjectMst = lcmnProjectMstDao.read(Integer.parseInt(projectId));
			 fmsFileMst.setCmnProjectMst(lcmnProjectMst);			 
			 
			 CmnLookupMst objCmnLookupMst=new CmnLookupMst();
			 CmnLookupMstDAO cmnLookupMstDAO=new CmnLookupMstDAOImpl(CmnLookupMst.class,servLoc.getSessionFactory());
			 List<CmnLookupMst> listCmnLookupMst=new ArrayList<CmnLookupMst>();
			 listCmnLookupMst=cmnLookupMstDAO.getListByColumnAndValue("lookupName", confidentiality);
			 if(listCmnLookupMst!=null){
				Iterator itrCmnLookup=listCmnLookupMst.iterator();										
				lookupId=((CmnLookupMst)itrCmnLookup.next()).getLookupId();			 
				
				objCmnLookupMst.setLookupId(lookupId);
				fmsFileMst.setCmnLookupMstByConfidentiality(objCmnLookupMst);
			 }
			 listCmnLookupMst=cmnLookupMstDAO.getListByColumnAndValue("lookupName",fileStage ); 
			 if(listCmnLookupMst!=null){
					Iterator itrCmnLookup=listCmnLookupMst.iterator();										
					lookupId=((CmnLookupMst)itrCmnLookup.next()).getLookupId();			 
					objCmnLookupMst=new CmnLookupMst();
					objCmnLookupMst.setLookupId(lookupId);
					fmsFileMst.setCmnLookupMstByFileStage(objCmnLookupMst);
			 }
			 listCmnLookupMst=cmnLookupMstDAO.getListByColumnAndValue("lookupName", fileStatus); 
			 if(listCmnLookupMst!=null){
					Iterator itrCmnLookup=listCmnLookupMst.iterator();										
					lookupId=((CmnLookupMst)itrCmnLookup.next()).getLookupId();			 
					objCmnLookupMst=new CmnLookupMst();
					objCmnLookupMst.setLookupId(lookupId);
					fmsFileMst.setCmnLookupMstByFileStatus(objCmnLookupMst);
			 }
			
			 listCmnLookupMst=cmnLookupMstDAO.getListByColumnAndValue("lookupName", filePrority); 
			 if(listCmnLookupMst!=null){
					Iterator itrCmnLookup=listCmnLookupMst.iterator();										
					lookupId=((CmnLookupMst)itrCmnLookup.next()).getLookupId();			 
					objCmnLookupMst=new CmnLookupMst();
					objCmnLookupMst.setLookupId(lookupId);
					fmsFileMst.setCmnLookupMstByPriority(objCmnLookupMst);
			 }
			 
			 WfDocMstDao wfDocMstDao=new WfDocMstDaoImpl(WfDocMst.class,servLoc.getSessionFactory());
			 WfDocMst wfDocMst=wfDocMstDao.read(Long.parseLong(subject));
			 fmsFileMst.setWfDocMst(wfDocMst);
			 fmsFileMst.setFileDesc(Description);
			 fmsFileMst.setCrtDt(DBUtility.getCurrentDateFromDB());	
			 Date newdate=new Date();
			 Calendar dateFormat=Calendar.getInstance();
			 dateFormat.setTime(newdate);
			 dateFormat.add(Calendar.DAY_OF_MONTH,5);
			
			 newdate=dateFormat.getTime();
			 fmsFileMst.setDueDate(newdate);	
			// fmsFileMst.setFileRefAppid(corrId);
			 
			 objectArgs.put("FmsFileMst",fmsFileMst);
			 ResultObject objres1 = servLoc.executeService("CREATEFILE", objectArgs);
			 Map resultMap = (Map) objres1.getResultValue();
			 String fileId=resultMap.get("fileId").toString();
			 
			 
			 objRes.setResultValue(resultMap);
			
			
			
		} 
		
		catch (Exception e) {
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setResultCode(-1);
			objRes.setResultValue(objectArgs);
			logger.error("Exception while creating file",e);
		}
		return objRes;
	}
}
