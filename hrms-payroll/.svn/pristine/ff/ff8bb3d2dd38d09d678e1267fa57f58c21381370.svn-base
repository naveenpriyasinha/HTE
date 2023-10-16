package com.tcs.sgv.deduction.dao;
//Comment By Maruthi For import Organisation.

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.deduction.valueobject.HrDeducArgumentMpg;



public class DeducArgMpgDAOImpl extends GenericDaoHibernateImpl<HrDeducArgumentMpg, Long> implements DeducArgMpgDAO{

	public DeducArgMpgDAOImpl(Class<HrDeducArgumentMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	
public List<HrDeducArgumentMpg> getDeducArgData(long deducCode) {
		
        Criteria objCrt = null;
        List  list = null;        
        Session session = getSession();
        Criteria criteria = session.createCriteria(HrDeducArgumentMpg.class);
        criteria.add(Restrictions.like("hrPayDeducTypeMst.deducCode", deducCode));
        criteria.addOrder( Property.forName("arguOrder").asc() );
        list = criteria.list();
    	            
	     //System.out.println("ffrom deduc arg mpg dao list size is "+list.size());
	             
        return list;
    }
	
public List<HrDeducArgumentMpg> getDeducArgData(long deducCode,long payCommissionId) {
	
    Criteria objCrt = null;
    List  list = null;        
    Session session = getSession();
    Criteria criteria = session.createCriteria(HrDeducArgumentMpg.class);
    criteria.add(Restrictions.like("hrPayDeducTypeMst.deducCode", deducCode));
    criteria.add(Restrictions.like("hrPayCommissionMst.id", payCommissionId));
    criteria.addOrder( Property.forName("arguOrder").asc() );
    list = criteria.list();
	            
    // System.out.println("ffrom deduc arg mpg dao list size is "+list.size());
             
    return list;
}

}
