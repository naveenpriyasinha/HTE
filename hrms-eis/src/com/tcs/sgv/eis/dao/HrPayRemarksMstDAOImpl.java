package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayRemarksMst;

public class HrPayRemarksMstDAOImpl extends GenericDaoHibernateImpl<HrPayRemarksMst, Long> implements HrPayRemarksMstDAO
{

	
Log logger = LogFactory.getLog( getClass() );
	
	public HrPayRemarksMstDAOImpl(Class<HrPayRemarksMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	
	
	public long getRemarkIdFromGrpId(long grpId)
	{
		logger.info("in getGpfAcctNuFromEmpId...........");
		List list = new ArrayList();

		long remarkId = 0;		
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append("select group.hrPayRemarksMst.remarkId from HrPayCompGrpMst group where group.compoGrpId="+grpId);

		Query sqlQuery = hibSession.createQuery(query.toString());


		list= sqlQuery.list();	 
		if(list.size()>0)
		{
			if(list.get(0) !=null)
			{
				remarkId = Long.parseLong(list.get(0).toString());
			}
		}

		else
		{
			remarkId=0;
		}
		logger.info("returning value of remarkId is"+remarkId); 
		return remarkId;
	}
	
	public long getPostIdFromEmpId(long hdnEmpId)
	{
		logger.info("in getPostIdFromEmpId...........");
		List list = new ArrayList();

		long PostId = 0;		
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append("select rlt.orgPostMst.postId from OrgPostDetailsRlt rlt where rlt.orgPostMst.postId in(select userpost.orgPostMstByPostId.postId from OrgUserpostRlt userpost ");

		
		query.append(" where userpost.orgUserMst.userId in (select emp.orgUserMst.userId from OrgEmpMst emp where emp.empId  " ); 
		query.append(" in (select eis.orgEmpMst.empId from HrEisEmpMst eis where eis.empId="+hdnEmpId+"))) ");  
		
		
		Query sqlQuery = hibSession.createQuery(query.toString());

		logger.info("queryy  of getPostIdFromEmpId" + sqlQuery.toString());
		logger.info("queryy  of getPostIdFromEmpId" + sqlQuery);

		list= sqlQuery.list();	 
		if(list.size()>0)
		{
			if(list.get(0) !=null)
			{
				PostId = Long.parseLong(list.get(0).toString());
			}
		}

		else
		{
			PostId=0;
		}
		logger.info("returning value of remarkId is"+PostId); 
		return PostId;
	}
	
	
	public long getddoIdFromDdoCode(String ddocode,long langId,long locId)
	{
		logger.info("in ddoIdFromDdoCode...........");
		List list = new ArrayList();

		long ddoId = 0;		
		Session hibSession = getSession();

		StringBuffer query = new StringBuffer();
		query.append("select ddo.ddoId from OrgDdoMst ddo where ddo.ddoCode='"+ddocode+"' and ddo.langId="+langId+" and ddo.locationCode="+locId+" ");
		
		Query sqlQuery = hibSession.createQuery(query.toString());


		list= sqlQuery.list();	 
		if(list.size()>0)
		{
			if(list.get(0) !=null)
			{
				ddoId = Long.parseLong(list.get(0).toString());
			}
		}

		else
		{
			ddoId=0;
		}
		logger.info("returning value of ddoId is"+ddoId); 
		return ddoId;
	}
}
