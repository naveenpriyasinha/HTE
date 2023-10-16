package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
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
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;


public class BankDetailDAOImpl extends GenericDaoHibernateImpl<HrEisBankDtls, Long> implements BankDetailDAO {
	Log logger = LogFactory.getLog( getClass() );
 	public BankDetailDAOImpl(Class<HrEisBankDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();
	public List getAllBankDetailData()
    {
        List  bankDtlsList = null;
        Session hibSession = getSession();
        /*Criteria objCrt = null;
            
            objCrt = hibSession.createCriteria(HrEisBankDtls.class);
                        
            objCrt.setFetchMode("hrEisEmpMst",FetchMode.JOIN);
            objCrt.createAlias("hrEisEmpMst","hrEisEmpMst");
            
            objCrt.setFetchMode("hrEisEmpMst.orgEmpMst",FetchMode.JOIN);
            objCrt.createAlias("hrEisEmpMst.orgEmpMst","orgEmp");
             
            objCrt.setFetchMode("hrEisBankMst",FetchMode.JOIN);
            objCrt.createAlias("hrEisBankMst","HrEisBankMst");
            
                       
            objCrt.addOrder(Order.asc("orgEmp.empFname"));
            objCrt.addOrder(Order.asc("orgEmp.empMname"));
            objCrt.addOrder(Order.asc("orgEmp.empLname"));
            objCrt.addOrder(Order.asc("HrEisBankMst.bankName"));
            objCrt.addOrder(Order.asc("bankAcctNo"));
            bankDtlsList = objCrt.list();*/            
	        
            String queryString = " from  HrEisBankDtls as bd ";
            queryString+=" join fetch  bd.hrEisEmpMst";            
            queryString+=" join fetch  bd.hrEisEmpMst.orgEmpMst ";    
            queryString+=" join fetch  bd.hrEisBankMst "; 
            queryString+=" join fetch  bd.hrEisBranchMst ";
                                   	            
            queryString+=" order by bd.hrEisEmpMst.orgEmpMst.empFname,bd.hrEisEmpMst.orgEmpMst.empMname,bd.hrEisEmpMst.orgEmpMst.empLname,bd.hrEisBankMst.bankName,bd.bankAcctNo";
                
            Query sqlQuery = hibSession.createQuery(queryString);
            
            
            bankDtlsList = sqlQuery.list();
            logger.info("the value of size is "+bankDtlsList.size());
        
        return bankDtlsList;
    }
	public List getAllBankEmpId()
    {
        List  bankDtlsList = null;
        
            Session hibSession = getSession();
            String strQuery = "select bd.hrEisEmpMst from HrEisBankDtls as bd join fetch bd.hrEisEmpMst.orgEmpMst";
            Query bankDtlsQuery = hibSession.createQuery(strQuery);
            bankDtlsList = bankDtlsQuery.list();
        
        return bankDtlsList;
    }
	
	public List getAllBankDetailDataByEmpId(long empId)
    {
        List  bankDtlsList = null;
        
            Session hibSession = getSession();
            String strQuery = "from HrEisBankDtls as bankDtls where bankDtls.hrEisEmpMst.empId = " + empId;
            Query bankDtlsQuery = hibSession.createQuery(strQuery);
            bankDtlsList = bankDtlsQuery.list();
        
        return bankDtlsList;
    }
	
	public HrEisBankDtls getBankDtlsIdData(long BankDtlsId)
    {
 		HrEisBankDtls hrBankDtlsInfo = new HrEisBankDtls();
        
            Session hibSession = getSession();
            String query1 = "from HrEisBankDtls as bankLookup where bankLookup.bankDtlId = "
                    + BankDtlsId;
            Query bankDtlsIdQuery = hibSession.createQuery(query1);
            hrBankDtlsInfo = (HrEisBankDtls)bankDtlsIdQuery.uniqueResult();

        
        return hrBankDtlsInfo;
    }
	
	public List getAllBankDetailData(List userList ,long EmpId) {        
 		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrEisBankDtls.class);
            
        objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
        objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
        objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
        
        if(userList.size()>0)
        objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));
        if(EmpId!=0)
        objCrt.add(Restrictions.eq("hrEisEmpMst.empId", EmpId));

        objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
        objCrt.addOrder(Order.asc("orgEmpMst.empMname"));
        objCrt.addOrder(Order.asc("orgEmpMst.empLname"));

        
        return objCrt.list();
    }
	
	public List getAllBankDetailData(String locationCode ,long EmpId,long langId)
	{        
        List empList=new ArrayList();
 		Session hibSession = getSession();
 		
        String query = " from  HrEisBankDtls e  ";
        query+=" join fetch  e.hrEisEmpMst";            
        query+=" join fetch  e.hrEisEmpMst.orgEmpMst ";    
        query+=" join fetch  e.hrEisBankMst "; 
        query+=" join fetch  e.hrEisBranchMst where ";
        if(!locationCode.equals(""))//location specific
        {
        	query+=" e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId  and ";
        	query+="pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"+locationCode+"' and c.cmnLanguageMst.langId="+langId+"))";
        /*query1+="  e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in ";
        query1+=" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "+userId+" ) ) ";*/	
        }
        if(EmpId!=0)
        query+=" and e.hrEisEmpMst.orgEmpMst.empId ="+EmpId;
  
        query+=" order by e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname,e.hrEisEmpMst.orgEmpMst.empLname ";	
        Query sqlQuery = hibSession.createQuery(query);
        empList = sqlQuery.list();
        logger.info("The Size of the Bank Details Data is:-"+empList.size());
        return empList;
    }
}
