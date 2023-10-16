package com.tcs.sgv.eis.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtls;
import com.tcs.sgv.eis.valueobject.HrPayVpfDtlsHst;

public class VoluntryPFDAOImpl extends GenericDaoHibernateImpl<HrPayVpfDtls, Long> implements EmpVoluntryPFDAO{
	Log logger = LogFactory.getLog( getClass() );
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();
	public VoluntryPFDAOImpl(Class<HrPayVpfDtls> type, SessionFactory sessionFactory) {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getVoluntryDtls() {        
        List  list = null;        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayVpfDtls hrPayVpfDtls order by hrPayVpfDtls.hrEisEmpMst.orgEmpMst.empFname,hrPayVpfDtls.hrEisEmpMst.orgEmpMst.empMname,hrPayVpfDtls.hrEisEmpMst.orgEmpMst.empLname");            
            list = query.list();       
        return list;
    }
	public HrPayVpfDtls getHrPayVpfByCode(long vpfId)  {
		HrPayVpfDtls hrPayVpfDtls = new HrPayVpfDtls();        
        List  list = null;        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery("from HrPayVpfDtls where payVpfId="+vpfId);
            hrPayVpfDtls = (HrPayVpfDtls)query.uniqueResult();        
        return hrPayVpfDtls;
    }
	
	public List getVpfEmployees() {   
        List  list = null;        
            Session hibSession = getSession();            
            Query query = hibSession.createQuery(" from HrEisOtherDtls hrEisOtherDtls where hrEisOtherDtls.hrEisEmpMst.orgEmpMst.empSrvcExp >= "+ sdate +" and  hrEisOtherDtls.hrEisEmpMst.empId not in (select hrPayVpfDtls.hrEisEmpMst.empId from HrPayVpfDtls hrPayVpfDtls) ");            
            list = query.list();       
        return list;
    }
	
	public HrEisEmpMst getEmployeeById(long empId) {   
        List  list = null; 
        HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
            Session hibSession = getSession();            
            Query query = hibSession.createQuery(" from HrEisEmpMst hrEisEmpMst where hrEisEmpMst.orgEmpMst.empId ="+empId +" order by hrEisEmpMst.orgEmpMst.empFname,hrEisEmpMst.orgEmpMst.empMname,hrEisEmpMst.orgEmpMst.empLname");            
            hrEisEmpMst = (HrEisEmpMst)query.uniqueResult();       
        return hrEisEmpMst;
    }
	public List getVoluntryDtls(List userList ,long EmpId) {        
 		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrPayVpfDtls.class);
            
        objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
        objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
        objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
        if(userList.size()>0)
        objCrt.add(Restrictions.in("orgEmpMst.orgUserMst", userList));
        if(EmpId!=0)
        objCrt.add(Restrictions.like("hrEisEmpMst.empId", EmpId));
        objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
        objCrt.addOrder(Order.asc("orgEmpMst.empMname"));
        objCrt.addOrder(Order.asc("orgEmpMst.empLname"));

        return objCrt.list();
    }

	public List getVoluntryDtls(String locationCode ,long EmpId,long langId) {        
        List dataList=new ArrayList();
 		Session hibSession = getSession();
 		
        String queryString = " FROM HrPayVpfDtls e WHERE";  

        	if( !locationCode.equals("") )//location specific
        	{
        		queryString+=" e.hrEisEmpMst.orgEmpMst.empSrvcExp >= " +sdate+
        				" and e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in " +
        				"			( select up.orgUserMst.userId " +
        				"				from OrgUserpostRlt up, OrgPostDetailsRlt pd " +
        				"					where pd.orgPostMst.postId = up.orgPostMstByPostId.postId AND " +
        				"      pd.cmnLocationMst.locId in " +
        				"			(select c.locId " +
        				"				from CmnLocationMst c " +
        				"					where c.locationCode='"+locationCode+"' and c.cmnLanguageMst.langId="+langId+"" +
        				"	   		)" +
        				"			)";
        		/*query1+="  e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in ";
        	query1+=" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "+userId+" ) ) ";*/	
        	}

        	if(EmpId!=0)
        		queryString+=" and e.hrEisEmpMst.orgEmpMst.empId  ="+EmpId;

        	queryString+=" order by e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname,e.hrEisEmpMst.orgEmpMst.empLname ";	


        	Query sqlQuery = hibSession.createQuery(queryString);
        
        dataList = sqlQuery.list();
        logger.info("VPF employee's datalist size is: " +dataList.size());
        return dataList;
    }

	public HrPayVpfDtls getHrPayVpfByEmpId(long empId)  {
		HrPayVpfDtls hrPayVpfDtls = new HrPayVpfDtls();        
        List  list = null;        
            Session hibSession = getSession();            
            String queryString = " from  HrPayVpfDtls e where  e.hrEisEmpMst.empId="+empId;
            /*Criteria crit = hibSession.createCriteria(HrPayVpfDtls.class);
            
            crit.add(Restrictions.eq("hrEisEmpMst.orgEmpMst.empId", empId));
            
            hrPayVpfDtls = (HrPayVpfDtls) crit.uniqueResult();*/
            Query query = hibSession.createQuery(queryString);
            hrPayVpfDtls = (HrPayVpfDtls) query.uniqueResult();
        return hrPayVpfDtls;
    }
	
	public List getHrPayVpfByOrgEmpId(long orgEmpId)  {
		HrPayVpfDtls hrPayVpfDtls = new HrPayVpfDtls();        
        List  list = null;        
            Session hibSession = getSession();            
            String queryString = " from  HrPayVpfDtls e where  e.hrEisEmpMst.orgEmpMst.empId="+orgEmpId;
            /*Criteria crit = hibSession.createCriteria(HrPayVpfDtls.class);
            
            crit.add(Restrictions.eq("hrEisEmpMst.orgEmpMst.empId", empId));
            
            hrPayVpfDtls = (HrPayVpfDtls) crit.uniqueResult();*/
            Query query = hibSession.createQuery(queryString);
            logger.info("the query from org function is "+query);
            list =  query.list();
        return list;
    }
	
	public List getHrPayVpfByCode(long empId,int month,int year)  {
		HrPayVpfDtlsHst hrPayVpfDtlsHst = new HrPayVpfDtlsHst();        
        List  list = null;        
            Session hibSession = getSession(); 
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.YEAR, year);
            cal2.set(Calendar.MONTH, month);
            
            int maxDays = cal2.getActualMaximum(5);
            
            cal2.set(Calendar.DATE, maxDays);
            
            java.util.Date currDate1 = cal2.getTime();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            
            String currDate = sdf.format(currDate1);
            
            logger.info("the date is "+currDate);
            Query query = hibSession.createQuery("from HrPayVpfDtlsHst where hrEisEmpMst.empId="+empId+
            		" and updatedDate <= '"+currDate+"' order by updatedDate desc ");
            list = query.list();
            
            logger.info("the query is "+query);
        return list;
    }
	
}
