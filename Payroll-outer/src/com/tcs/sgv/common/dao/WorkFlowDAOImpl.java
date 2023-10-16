package com.tcs.sgv.common.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * @see This class is used to get users in heirarchy of workflow when bill is moving
 * @author 157045
 * @version 1.0
 */
public class WorkFlowDAOImpl 
{
	/**
	 * This method is used to get same level of users for send to peer functionality
	 * @param hrchyRefId
	 * @param levelId
	 * @param postId
	 * @param session
	 * @return List
	 */
	public List getSameLevelUsers(String hrchyRefId, long levelId,long postId, SessionFactory session)
	{	  
		String str = "select post_id, level_id from wf_hierachy_post_mpg where hierachy_ref_id ="+hrchyRefId+" and level_id ="+levelId+" and post_id != '"+postId+"'";
		Session ses  = session.openSession();
		Query query  = ses.createSQLQuery(str);
		//System.out.println(" Executing query............. "  + str);
		return query.list();
	}
	/**
	 *  This is to get Heirarchy reference if from document Id	  
	 * @param locId
	 * @param docId
	 * @param session
	 * @return Long
	 */
	
	public Long getHierarchyRefIdByDocAndLoc(String locId, Long docId, SessionFactory session) {
		Long hrchyRefId = null;
		String sql = "select hierachy_ref_id from wf_hierarchy_reference_mst where loc_Id='"+locId+"' and doc_Id = " + docId;
		Query query  = session.openSession().createSQLQuery(sql);
		hrchyRefId = new Long(query.list().iterator().next().toString());
		return hrchyRefId;
	}
}
