
package com.tcs.sgv.user.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

/**
 * @author 212951
 *
 */

public class UserPostMappingDaoImpl extends GenericDaoHibernateImpl implements UserPostMappingDao {

	Log logger = LogFactory.getLog(getClass());
	public UserPostMappingDaoImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getUserPostMappingData(long langId)
	{
		List arUserPostMappingData =  new ArrayList();
		Session session= getSession();				
		String queryHrEisEmpTrnString="from OrgUserpostRlt up,OrgPostDetailsRlt pd where up.orgPostMstByPostId=pd.orgPostMst and pd.cmnLanguageMst.langId=:langId";
		Query queryHrEisEmpTrn=session.createQuery(queryHrEisEmpTrnString).setParameter("langId",langId);
		arUserPostMappingData=queryHrEisEmpTrn.list();
		logger.info("===========arUserPostMappingData========" + arUserPostMappingData);
		
		
		for (Iterator iter = arUserPostMappingData.iterator(); iter.hasNext();) 
		{
			Object[] objUserPostMappingData = (Object[]) iter.next();
			
			OrgUserpostRlt orgUserpostRlt = (OrgUserpostRlt) objUserPostMappingData[0];
			OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) objUserPostMappingData[1];
			
			orgUserpostRlt.getOrgUserMst().getUserName();
			orgPostDetailsRlt.getPostName();
		}
		
		return arUserPostMappingData;
	}
	



	
}