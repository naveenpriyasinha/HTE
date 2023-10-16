package com.tcs.sgv.common.helper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.wf.appwfinterface.OrgUtilityImpl;
import com.tcs.sgv.wf.valueobject.WfDocMvmntMstVO;
import com.tcs.sgv.wf.valueobject.WorkFlowVO;

/**
 * A class for work flow related interactions 
 *  
 * @author 206819
 */
public class WorkFlowHelper {
	private static Log logger = LogFactory.getLog(WorkFlowHelper.class);
	
	/**
	 * This method returns all documents from workflow by postId(s) 
	 * @param map
	 * @return List
	 */
	public static List getDocsFromWorkFlow(Map map)
	{			
		List docLst  = null;
		try
		{
			ServiceLocator serv = (ServiceLocator) map.get("serviceLocator");
			Connection conn = serv.getSessionFactory().getCurrentSession().connection();
			List postLst = (List) map.get("postLst");
			
			/* create work flow object .. */
			WorkFlowVO workFlowVO = new WorkFlowVO();
			workFlowVO.setConnection(conn);			
			OrgUtilityImpl utilImpl = new OrgUtilityImpl(workFlowVO);
			
			/* get document list from work flow .. */
			docLst = utilImpl.getDocList(postLst);							
		} catch(Exception ex) {
			logger.error("Exception occurred #\n"+ex);
		}		
		return docLst;
	}
	
	/**
	 * This method returns all bills from workflow by postId
	 * @param map
	 * @return
	 */
	public static List getBillsFromWorkFlow(Map map)
	{			
		List billLst  = null;
		try
		{
			List docList = getDocsFromWorkFlow(map);
			if (docList!=null && docList.size()>0) {
				billLst  = new ArrayList();
				for(int i=0;i< docList.size();i++)
				{
					WfDocMvmntMstVO mvmntVO = (WfDocMvmntMstVO)docList.get(i);
					billLst.add(mvmntVO.getJobRefId());
				}
			}							
		} catch(Exception ex) {
			logger.error("Exception occurred #\n"+ex);
		}		
		return billLst;
	}
	
	/**
	 * This method returns all challans from workflow by postId
	 * @param map
	 * @return
	 */
	public static List getChallansFromWorkFlow(Map map)
	{			
		List billLst  = null;
		try
		{
			List docList = getDocsFromWorkFlow(map);
			if (docList!=null && docList.size()>0) {
				billLst  = new ArrayList();
				for(int i=0;i< docList.size();i++)
				{
					WfDocMvmntMstVO mvmntVO = (WfDocMvmntMstVO)docList.get(i);
					billLst.add(mvmntVO.getJobRefId());
				}
			}							
		} catch(Exception ex) {
			logger.error("Exception occurred #\n"+ex);
		}		
		return billLst;
	}
}
