package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;

public class PsrPostMpgDAOImpl extends GenericDaoHibernateImpl<HrPayPsrPostMpg, Long>  {

	public PsrPostMpgDAOImpl(Class<HrPayPsrPostMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	
	public List chkpsrLocationunique(long psrId,long location_Id,long psrPost_Id)
	{	
		List ppList = null;
		Session hibSession = getSession();
			 StringBuffer strQuery = new StringBuffer();
			 strQuery.append(" select *  from hr_pay_post_psr_mpg o where o.psr_no= "+psrId+" and o.loc_id= "+location_Id+" and  o.id != "+psrPost_Id);
		     logger.info("In chkpsrLocationunique--Query for uniqueness in psr post " + strQuery);
			 Query query = hibSession.createSQLQuery(strQuery.toString());
			 ppList = query.list();
		return ppList;
	}	
	
	
	public List getPsrIdbyLocId(long Post_Id)
	{	
		List ppList = null;
		Session hibSession = getSession();
			 StringBuffer strQuery = new StringBuffer();
			 strQuery.append(" select h.psr_no,h.id  from hr_pay_post_psr_mpg h where h.post_id="+Post_Id);
		     logger.info("In chkpsrLocationunique--Query for uniqueness in psr post " + strQuery);
			 Query query = hibSession.createSQLQuery(strQuery.toString());
			 ppList = query.list();
		return ppList;
	}	
	
	public List getMaxPsrForLoc(long loc_id)
	{
		 List psrList = null;
		 Session hibSession = getSession();
		 
		 StringBuffer strQuery = new StringBuffer();
		// strQuery.append("Select max(psrId),max(loc_id) from HrPayPsrPostMpg psr where psr.loc_id = "+loc_id);
		 strQuery.append("Select max(psr_no),max(loc_id) from HR_PAY_POST_PSR_MPG psr where psr.loc_id  = " + loc_id);
		 
		 Query query = hibSession.createSQLQuery(strQuery.toString());
		 psrList =query.list();
		 
		 return psrList;
	}
	//Added by Abhilash for getting max psr number
	public long getMaxPsrNo(long loc_id)
	{
		 List psrList = null;
		 Session hibSession = getSession();
		 
		 StringBuffer strQuery = new StringBuffer();
		 strQuery.append("Select max(psrId) from HrPayPsrPostMpg psr where psr.loc_id = "+loc_id);
		 
		 psrList = hibSession.createQuery(strQuery.toString()).list();
		 long psrNo = Long.parseLong(psrList.get(0).toString());
		 return psrNo;
	}
	public long getNextPsrNo()
	{
		 long nextPsr = 0;
		 
		 Session hibSession = getSession();
		 String nextPsrlstr="";
		 StringBuffer strQuery = new StringBuffer();
		 strQuery.append("Select max(psrId) from HrPayPsrPostMpg psr ");
		 
		 List psrList = hibSession.createQuery(strQuery.toString()).list();
		  nextPsr = Long.parseLong(psrList.get(0).toString())+1;
		 
		 return nextPsr;
	}
	
	public long getBillNoByPost(long postId)
	{
		 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("Select billNo from HrPayPsrPostMpg psr where postId = "+postId);
		Query query = hibSession.createQuery(strQuery.toString());
		if(query.list() != null){
		 List psrList = query.list();
		 if(psrList.size() > 0)
			 if(psrList.get(0)!=null)
				 return Long.parseLong(psrList.get(0).toString());
		}
		return 0;
	}
	
	/**
	 * author@manish khunt
	 * This method is used to get the HrPayPostPsrMpg object from postId
	 * @param postId
	 * @return
	 */
	public HrPayPsrPostMpg getHrPayPostPsrMpg(long postId)
	{
		 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("Select psr from HrPayPsrPostMpg psr where postId = "+postId);
		return  (HrPayPsrPostMpg)(hibSession.createQuery(strQuery.toString()).uniqueResult());
		
		
	}
	
	}
