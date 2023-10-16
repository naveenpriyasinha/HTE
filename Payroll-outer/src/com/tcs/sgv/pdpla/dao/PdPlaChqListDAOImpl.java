package com.tcs.sgv.pdpla.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChq;

public class PdPlaChqListDAOImpl extends GenericDaoHibernateImpl<TrnPdChq,Long> implements PdPlaChqListDAO{

	public PdPlaChqListDAOImpl(Class<TrnPdChq>type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public ArrayList ChqListShow() 
	{
		try
		
		{
			//Connection conn = DBConnection.getConnection();
			//System.out.println("u r in showList daoimpl" );
			Session hibSession = getSession();
			Query q = hibSession.createSQLQuery("SELECT PAYEE_NM,CHQ_NO,CHQ_ISSUE_DATE,AMOUNT FROM TRN_PD_CHQ WHERE STATUS=0");
			ArrayList queryList = (ArrayList)q.list();
			//System.out.println("Size: " + queryList.size());
			
			
				

			//System.out.println("aftr createSQLQuery"+queryList);
		
		
			return queryList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}


	
	
	
}
