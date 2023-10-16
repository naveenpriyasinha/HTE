package com.tcs.sgv.loan.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.loan.valueobject.HrLoanAdvMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;


public class LoanAdvMstDAOImpl extends GenericDaoHibernateImpl<HrLoanAdvMst, Long> implements LoanAdvMstDAO {
	
 	public LoanAdvMstDAOImpl(Class<HrLoanAdvMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllLoanAdvMasterData()
    {        
        List  list = null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrLoanAdvMst";
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	public List getAllLoanAdvMasterData(long LangId)
    {        
        List  list = null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrLoanAdvMst as a where a.cmnLanguageMst.langId="+LangId+" order by a.loanAdvName";
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	public List<HrLoanAdvMst> getLoanAdvMasterData(long elementCode,long LangId)
    {        
        List  list = null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrLoanAdvMst as a where a.cmnLanguageMst.langId="+LangId + " and a.elementCode="+elementCode;
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	
	public HrLoanAdvMst getLoanIdData(long loanId)
    {
		HrLoanAdvMst hrLoanInfo = new HrLoanAdvMst();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrLoanAdvMst as loanLookup where loanLookup.loanAdvId = "
                    + loanId;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrLoanInfo = (HrLoanAdvMst)sqlQuery1.uniqueResult();
            //System.out.println("Bank size is:::::::::" );

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hrLoanInfo;
    }
	
	public List checkLoanName(String loanName)
	{
		List loanNameList=null;
        
        Session hibSession = getSession();
        String query1 = "from HrLoanAdvMst as loanLookup where lower(loanLookup.loanAdvName) = '"
                + loanName + "'";
        
        Query sqlQuery1 = hibSession.createQuery(query1);
        
        //sqlQuery1.setParameter("param",langId);
        loanNameList = sqlQuery1.list();
        //System.out.println("Loan Name size is:::::::::" );
    
    return loanNameList;
	}
	
	
	public List getAllLoanAdvMasterDataByLoanId(long loanId)
    {        
        List  list = null;
        try
        {
            Session hibSession = getSession();
            String strQuery = "from HrLoanAdvMst as a where a.loanAdvId="+loanId;
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
	public List getLoanAdvList()
	{
		List LoanAdvList=new ArrayList();

        Session hibSession = getSession();
        String HQL_QUERY ="select mpg from HrPayEdpCompoMpg mpg where mpg.cmnLookupId in (2500136,2500137)";
        //String queryStr ="select mpg from HrPayEdpCompoMpg mpg ";sas

        Query query= hibSession.createQuery(HQL_QUERY);
        LoanAdvList = query.list();
        
        return LoanAdvList;
	}

	public List getMappedLoanAdvList(long locId)
	{
		List LoanAdvList=new ArrayList();

        Session hibSession = getSession();
        //String HQL_QUERY ="select mpg from HrPayEdpCompoMpg mpg where mpg.cmnLookupId in (2500136,2500137)";
        //String queryStr ="select mpg from HrPayEdpCompoMpg mpg ";sas
        String HQL_QUERY ="select mpg from HrPayEdpCompoMpg mpg where mpg.cmnLookupId in (2500136,2500137) and mpg.typeId in (" +
        		"select compId from  HrPayLocComMpg locMpg where  locMpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and locMpg.hrpaycompgrpmst.isactive=1 and locMpg.cmnLookupMst.lookupId in (2500136,2500137))";

        Query query= hibSession.createQuery(HQL_QUERY);
        LoanAdvList = query.list();
        
        return LoanAdvList;
	}
}
