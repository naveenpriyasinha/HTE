package com.tcs.sgv.common.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.util.WorkFlowUtility;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/*
 * Created by Sathya on 21-07-2007, for WorkList population
 */
public class WorkListServImpl extends ServiceImpl implements WorkListServ {

	Log logger = LogFactory.getLog(getClass());

	//logger.info("Sathya, the getClass() is : " + getClass());

	public ResultObject getDocList(Map objectArgs) {
		logger
				.info("Sathya, inside the getDocList() method of WorkListServImpl ");

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		Connection connection=null;
		
        HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session = request.getSession();
		LoginDetails objLoginDetails = (LoginDetails) session.getAttribute("loginDetails");
		
		
		
		
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}

			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			if (objectArgs != null) {
				List lstDocs = null;
				
				//Modified by Sathya for using the core method for getDocList() - Start
				List lstPosts = new ArrayList();
				List lstPosts1 = new ArrayList();
				lstPosts1 = objLoginDetails.getAllPost();
				logger.info("Sathya, the lstPosts list size is : "+lstPosts1.size());
				OrgUserpostRlt orgUserpostRlt = null;
				if(lstPosts1!=null && lstPosts1.size()>0)
				{
					for(int i=0; i<lstPosts1.size(); i++) {
						orgUserpostRlt = (OrgUserpostRlt)lstPosts1.get(i);
						lstPosts.add((((Long)orgUserpostRlt.getEmpPostId()).toString()));
					}
				}
				logger.info("Sathya, the lstPosts list is : "+lstPosts);
				//Long sPostId = (Long)orgUserpostRlt.getEmpPostId();
				//logger.info("Sathya, the sPostId is : "+sPostId);
				//lstPosts.add("IOPost");
				//lstPosts.add(sPostId);
				
				WorkFlowVO workFlowVO = new WorkFlowVO();
				
				connection = serv.getSessionFactory().getCurrentSession().connection();
				workFlowVO.setConnection(connection);
				
				OrgUtilityImpl orgUtil = new OrgUtilityImpl(workFlowVO);
				lstDocs = orgUtil.getDocList(lstPosts);
				//Modified by Sathya for using the core method for getDocList() - End		
				
				//Satya - For using the own method from WorkListDAOImpl - WfDocMst - Start
				/*WorkListDAOImpl workListDAOImpl = new WorkListDAOImpl(
						WfDocMst.class, serv.getSessionFactory());

				lstDocs = (List) workListDAOImpl.getDocList("IOPost");*/
				//Satya - For using the own method from WorkListDAOImpl - WfDocMst - End

				objectArgs.put("lstDocs", lstDocs);

				objRes.setResultValue(objectArgs);
				objRes.setViewName("WorkListPage");
			} else {
				logger.info("arguments are null");
			}
			objRes.setResultCode(ErrorConstants.SUCCESS);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}

		return objRes;
	}
	
	public ResultObject processWorkflow(Map objectArgs) {
		
		logger
		.info("Sathya, inside the processWorkflow() method of WorkListServImpl ");
		
		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS);
		Connection connection=null;
		
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		HttpSession session = request.getSession();
		LoginDetails objLoginDetails = (LoginDetails) session.getAttribute("loginDetails");
		
		try {
			if (objRes == null || objectArgs == null) {
				objRes.setResultCode(-1);
				return objRes;
			}

			ServiceLocator serv = (ServiceLocator) objectArgs
					.get("serviceLocator");

			if (objectArgs != null) {
				
				HttpServletRequest grequest = (HttpServletRequest) objectArgs.get("requestObj");
				String actionId = grequest.getParameter("actionId");
				int actId=0;
				if(actionId.equals("2"))
				{
					actId=2;
				}
				int docId = Integer.parseInt(grequest.getParameter("docId"));
				String jobRefId = grequest.getParameter("jobRefId");
				
				OrgEmpMst empMst = objLoginDetails.getEmployee();
				String sEmpId = ((Long)empMst.getEmpId()).toString();
				String sLangId = ((Long)objLoginDetails.getLangId()).toString();
				OrgDesignationMst dsgMst = objLoginDetails.getDesignation();
				String sRoleId = dsgMst.getDsgnName();
                List lsUsrPost = objLoginDetails.getAllPost();
                OrgUserpostRlt usrPost = null;
				if(lsUsrPost!=null && lsUsrPost.size()>0)
				{
                  usrPost = (OrgUserpostRlt)lsUsrPost.get(0); 
				}
				
				String sUsrPost = usrPost!=null?((Long)usrPost.getEmpPostId()).toString():"";
				
				logger.info("Sathya, the actionId in processWorkflow() method of WorkListServImpl is : "+actionId);
				logger.info("Sathya, the docId in processWorkflow() method of WorkListServImpl is : "+docId);
				logger.info("Sathya, the jobRefId in processWorkflow() method of WorkListServImpl is : "+jobRefId);
				
				connection = serv.getSessionFactory().getCurrentSession().connection();
				
				WorkFlowVO workFlowVO = new WorkFlowVO();
				workFlowVO.setConnection(connection);
				workFlowVO.setAppMap(objectArgs);
				workFlowVO.setJobRefId(jobRefId);
				workFlowVO.setDocId(docId);
				workFlowVO.setActId(actId);
				workFlowVO.setCurrentJobStatus("Active");
				long bd = 1;
				workFlowVO.setDbId(bd);
				workFlowVO.setCrtEmpId(sEmpId);
			//workFlowVO.setLocID();
				workFlowVO.setLangID(sLangId);
				//workFlowVO.setParentJobStatus(WFConstants.JOB_SUSPENDED);
				workFlowVO.setHierarchyFlag(1);// 1--Normal or 2--Marked
				workFlowVO.setHirRefId(1);
				workFlowVO.setCrtRole(sRoleId);
				workFlowVO.setCrtPost(sUsrPost);
				workFlowVO.setCrtUsr(sEmpId);
				workFlowVO.setFromPost(sUsrPost);
				//workFlowVO.setToPost(gstrtoPost);
				//workFlowVO.setMarkHierarchy(markHirList);
				
				WorkFlowUtility wfutil = new WorkFlowUtility();
				try {
					wfutil.invokeWorkFlow(workFlowVO);
				} catch (Exception e) {
					throw e;
				}
				
				objRes.setResultValue(objectArgs);
				objRes.setViewName("WorkListPage");
			} else {
				logger.info("arguments are null");
			}
			objRes.setResultCode(ErrorConstants.SUCCESS);
		} catch (Exception e) {
			logger.info(e);
			e.printStackTrace();
		}
		
		return objRes; 
	}
	
	

}
