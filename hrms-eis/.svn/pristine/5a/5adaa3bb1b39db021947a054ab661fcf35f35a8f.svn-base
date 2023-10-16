package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.SgvaBudsubhdMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class SgvaBudSubHeadMpgDaoImpl extends GenericDaoHibernateImpl<SgvaBudsubhdMst, Long>{
	
	public SgvaBudSubHeadMpgDaoImpl(Class<SgvaBudsubhdMst> type, SessionFactory sessionFactory)
	{
		 super(type);
	     setSessionFactory(sessionFactory);
	}
	
	public List getheadStructure(long schemeId)
	{
		 Session hibSession = getSession();
		 
		StringBuffer query = new StringBuffer();
		query.append("select  bud.DEMAND_CODE, bud.BUDMJRHD_CODE,bud.BUDSUBMJRHD_CODE,bud.BUDMINHD_CODE,bud.BUDSUBHD_CODE,bud.BUDSUBHD_ID,pos.ELEMENT_CODE from SGVA_BUDSUBHD_MST bud , Org_scheme_mst mst, HR_PAY_ORDER_SUBHEAD_MPG pos where bud.BUDSUBHD_Id = mst.BUDSUBHD_ID and pos.BUDSUBHD_ID=bud.BUDSUBHD_ID and mst.SCHEME_ID= ");
		
		query.append(schemeId);
		
		Query que = hibSession.createSQLQuery(query.toString());
		
		List list = que.list();
		
		return list;
		
	}

	public List getSchemeListByPostId(long postId)
	{
		
		 Session hibSession = getSession();
		 
			StringBuffer query = new StringBuffer();
			query.append("select msta.scheme_id from org_scheme_mst msta, HR_PAY_DDO_SCHEME_MPG mpg , org_ddo_mst mst where mst.DDO_ID= mpg.DDO_ID and mpg.SCHEME_ID= msta.SCHEME_ID and mst.POST_ID= ");
			query.append(postId);
			
			Query que = hibSession.createSQLQuery(query.toString());
			
			List list = que.list();
			
			return list;
	}
}
