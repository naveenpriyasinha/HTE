package com.tcs.sgv.common.service;

import java.sql.Connection;
import java.util.Map;

import com.tcs.sgv.wf.exception.MultipleHierarchyFoundException;
import com.tcs.sgv.wf.interfaces.AppWorkFlowUtil;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * This class is an implementation of AppWorkFlowUtil
 */
public class AppWorkFlowUtilImpl implements AppWorkFlowUtil {

	WorkFlowVO lObjWfVO=null;
	public AppWorkFlowUtilImpl()
	{
						
	}

	public AppWorkFlowUtilImpl(WorkFlowVO lObjWfVO)
	{
			this.lObjWfVO=lObjWfVO;
			Connection conn=this.lObjWfVO.getConnection();
			
	}
	
	/***
	 * This Method will return EmpNo based on lStrPostId and jobRefId.
	 * @param lStrPostId (String)
	 * @param jobRefId (String)
	 * @return String EmpNo
	 * @throws Exception
	 *  
	 */
	public String getEmpNoByPost(String lStrPostId,String lStrJobRefID) throws Exception
	{
		//System.out.println("<<<<<<<<<<<<<<<Inside WorkFlowInterfaceImpl.getEmpNoByPost method >>>>>>>>>>>>>>");
		//System.out.println("Check::::::::::" + lObjWfVO.getActId());
		String lstrtoEmpNo=lStrPostId+"01";
		return lstrtoEmpNo;
	}

	/**
	 * This method is used to get post_id based on given role_id. 
	 * @param roleId (String)
	 * @param jobRefId (String)
	 * @return string lStrPostId
	 * @throws Exception
	 */
	public String getPostIdByRoleId(String lStrRoleId,String lStrJobRefID) throws Exception
	{
		//System.out.println("<<<<<<<<<<<<<<<Inside WorkFlowInterfaceImpl.getPostIdByRoleId method >>>>>>>>>>>>>>");
		//System.out.println("Check::::::::::" + lObjWfVO.getActId());
		String lStrPostId=lStrRoleId + "Post";
		return lStrPostId;
	}

	/**
	 * This method will return one element (based on user selection) from supplied list.
	 * When multiple Upper Roles are found then control goes to this function and decision
	 * on Upper role to be considered for forward action is taken based on this method.
	 * @param lResultMap ( 	Key = Result , Value = List Containning all ToNodes(String) ;Key = WorkFlowVO , Value = Object of WorkFlowVO ;Kye = WfJobMstVO , Value = Object of WfJobMstVO )	
	 * @return Map (Which Contains Key = Result ; Value = toNode(String) 
	 * @throws Exception
	 */
	public Map getToNode(Map lResultMap) throws Exception {
		
		throw new MultipleHierarchyFoundException();
	}

	public String getEmpNoByPost(String arg0, String arg1, WorkFlowVO arg2)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
