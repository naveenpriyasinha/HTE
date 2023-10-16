package com.tcs.sgv.pensionproc.dao;

import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class ProvisionalDaoImpl  extends GenericDaoHibernateImpl  {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(ApprovedFilesToAGDaoImpl.class);

	private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");

	public ProvisionalDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public int insertIntoProvTable(long gLngUserId,long inwardPensionId,String judCasePendingReason,String deptInqPendingReason) {
		
		Calendar calendar = Calendar.getInstance();
		ghibSession = getSession();
		int insertFlag = 0;
		String JUDCASEPENDING="No",DEPTINQPENDING="No";	
		try {
		if(judCasePendingReason!=null && !judCasePendingReason.equals("")){
			JUDCASEPENDING="Yes";
		}	
		if(deptInqPendingReason!=null && !deptInqPendingReason.equals("")){
			DEPTINQPENDING="Yes";
		}	
			StringBuilder sb = new StringBuilder();
			sb.append("insert into TRN_PROV_DTLS values(:INWARD_PENSION_ID,:JUDCASEPENDING, ");
			sb.append(":DEPTINQPENDING,:JUDCASEPENDINGREASON, ");
			sb.append(":DEPTINQPENDINGREASON,:CREATED_USER_ID,:CREATED_DATE)  ");

			Query selectQuery = this.ghibSession.createSQLQuery(sb.toString());
			selectQuery.setLong("INWARD_PENSION_ID", inwardPensionId);
			selectQuery.setParameter("JUDCASEPENDINGREASON", judCasePendingReason);
			selectQuery.setParameter("DEPTINQPENDINGREASON", deptInqPendingReason);
			selectQuery.setParameter("JUDCASEPENDING", JUDCASEPENDING);
			selectQuery.setParameter("DEPTINQPENDING", DEPTINQPENDING);
			selectQuery.setLong("CREATED_USER_ID", gLngUserId);
			selectQuery.setDate("CREATED_DATE", calendar.getTime());
			

			insertFlag = selectQuery.executeUpdate();
			
			
			
		} catch (Exception e) {
			this.logger.error("Error is :" + e);
			e.printStackTrace();
		}
		return insertFlag;

	}
	
	
}
