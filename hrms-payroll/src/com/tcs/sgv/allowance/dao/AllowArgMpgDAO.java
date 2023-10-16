package com.tcs.sgv.allowance.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.allowance.valueobject.HrAllowArgumentMpg;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class AllowArgMpgDAO extends GenericDaoHibernateImpl<HrAllowArgumentMpg, Long>{

	public AllowArgMpgDAO(Class<HrAllowArgumentMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List<HrAllowArgumentMpg> getAllowArgData(long allowCode) {
		
        Criteria objCrt = null;
        List  list = null;        
        Session session = getSession();
        Criteria criteria = session.createCriteria(HrAllowArgumentMpg.class);
        criteria.add(Restrictions.like("hrPayAllowTypeMst.allowCode", allowCode));
        criteria.addOrder( Property.forName("arguOrder").asc() );


        list = criteria.list();
    	  
	     //System.out.println("from allow arg mpg dao the allowcode is "+allowCode);
	                   
//          Query query = hibSession.createQuery("from HrPayAllowArgMpg where HrPayAllowTypeMst.allowCode =" + allowCode);            
  //        list = query.list();     */
	      //System.out.println("from allow arg mpg dao the list size is "+list.size());
	             
        return list;
    }
	
public List<HrAllowArgumentMpg> getAllowArgData(long allowCode,long payCommissionId) {
		
        Criteria objCrt = null;
        List  list = null;        
        Session session = getSession();
        Criteria criteria = session.createCriteria(HrAllowArgumentMpg.class);
        criteria.add(Restrictions.like("hrPayAllowTypeMst.allowCode", allowCode));
        criteria.add(Restrictions.like("hrPayCommissionMst.id", payCommissionId));
        criteria.addOrder( Property.forName("arguOrder").asc() );


        list = criteria.list();
    	  
	     //System.out.println("from allow arg mpg dao the allowcode is "+allowCode);
	                   
//          Query query = hibSession.createQuery("from HrPayAllowArgMpg where HrPayAllowTypeMst.allowCode =" + allowCode);            
  //        list = query.list();     */
	      //System.out.println("from allow arg mpg dao the list size is "+list.size());
	             
        return list;
    }
	
		
}
