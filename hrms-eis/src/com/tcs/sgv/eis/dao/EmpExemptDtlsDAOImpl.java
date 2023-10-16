package com.tcs.sgv.eis.dao;

/**
 * Class Name:- EmpExemptDtlsDAOImpl
 * Author:- Urvin shah.
 * Date:-7-11-2007 
 */

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrItExemptEmpDtls;

public class EmpExemptDtlsDAOImpl extends GenericDaoHibernateImpl<HrItExemptEmpDtls, Long> implements EmpExemptDtlsDAO {
	Log logger = LogFactory.getLog(getClass());
	
 	public EmpExemptDtlsDAOImpl(Class<HrItExemptEmpDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	/**
 	 * This method fetch All Employees' Exemption Dtls in list.
 	 * return @ list
 	 */
 	public List getAllEmpExemptionData(long locId) {
        Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrItExemptEmpDtls.class);    
        if(locId==300022)
            objCrt.add(Restrictions.eq("orgUserMstByCreatedBy.userId", new Long(302333)));
            else if(locId==300024)
            	objCrt.add(Restrictions.eq("orgUserMstByCreatedBy.userId", new Long(320000)));
        return objCrt.list();
    }
 	
 	public List getEmpExemptionData(long empId,long langId,long exemptionCode) {
        
        Session hibSession = getSession();
        String sqlQuery = "";
        
        sqlQuery += "select sum(empDtls.amount) from HrItExemptEmpDtls empDtls where empDtls.hrEisEmpMst.empId = "+empId;
        sqlQuery += " and empDtls.hrItExemptTypeMst.itexemptTypeCode = "+exemptionCode;
        sqlQuery += " and empDtls.hrItExemptTypeMst.cmnLanguageMst.langId = "+langId;       
        Query resultQuery = hibSession.createQuery(sqlQuery);
        return resultQuery.list();
    }
}
