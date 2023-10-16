package com.tcs.sgv.eis.dao;
//Comment By Maruthi For import Organisation.
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrInvestEmpDtls;

public class EmpInvestmentDtlsDAOImpl extends GenericDaoHibernateImpl<HrInvestEmpDtls, Long> implements EmpInvestmentDtlsDAO {
	Log logger = LogFactory.getLog(getClass());
	
 	public EmpInvestmentDtlsDAOImpl(Class<HrInvestEmpDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	
 	public List getAllEmpInvestmentData(long locId) {
        Criteria objCrt = null;        
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrInvestEmpDtls.class);
        
        objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);        
    	objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
    	
    	objCrt.setFetchMode("hrEisEmpMst.orgEmpMst", FetchMode.JOIN);
    	objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
    	
        objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
        objCrt.addOrder(Order.asc("orgEmpMst.empMname"));
        objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
        if(locId==300022)
        objCrt.add(Restrictions.eq("orgUserMstByCreatedBy.userId", new Long(302333)));
        else if(locId==300024)
        	objCrt.add(Restrictions.eq("orgUserMstByCreatedBy.userId", new Long(320000)));
        
        return objCrt.list();
    }
 	
 	public HrInvestEmpDtls getEmpInvestDataByInvestId(String investDtlsId)
    {
 		HrInvestEmpDtls hrInvestEmpDtls = new HrInvestEmpDtls();       
            Session hibSession = getSession();
            String query1 = "from HrInvestEmpDtls as a where a.investDtlsId = "
                    + investDtlsId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrInvestEmpDtls = (HrInvestEmpDtls)sqlQuery1.uniqueResult();
            logger.info("The query is----->"+sqlQuery1);        
        return hrInvestEmpDtls;
    }
 	public List getEmpInvestmentData(long empId,long langId,long investmentCode,String startDate,String endDate) {
        
        Session hibSession = getSession();
        String sqlQuery = "";
        
        sqlQuery += "select sum(empDtls.amount) from HrInvestEmpDtls empDtls where empDtls.hrEisEmpMst.empId = "+empId;
        sqlQuery += " and empDtls.hrInvestTypeMst.investTypeCode = "+investmentCode;
        sqlQuery += " and empDtls.hrInvestTypeMst.cmnLanguageMst.langId = "+langId;
        sqlQuery += " and empDtls.investDate between '"+startDate +"' and '"+endDate+"'";
        
        Query resultQuery = hibSession.createQuery(sqlQuery);
        return resultQuery.list();
    }

}
