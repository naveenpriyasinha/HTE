package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;


public class EmpLeaveDAOImpl extends GenericDaoHibernateImpl<HrLeaveEmpDtls, Long> implements EmpLeaveDAO {

 	public EmpLeaveDAOImpl(Class<HrLeaveEmpDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();
 	
	public List<HrLeaveEmpDtls> getAllEmpLeaveData(long empid)
    {
 		
       // Criteria objCrt = null;
        Query sqlQuery=null;
        
        try
        {
        	
        	Session hibSession = getSession();
       	//    objCrt = hibSession.createCriteria(HrLeaveEmpDtls.class);

       	    
//        	objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
//            objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
//            
//            objCrt.add(Restrictions.eq("hrEisEmpMst.empId", empid));
//            objCrt.add(Restrictions.eq("isDeleted", "1"));
//        	
        	
          //  objCrt = hibSession.createCriteria(HrLeaveEmpDtls.class);
           // objCrt.add(Restrictions.eq("hrEisEmpMst.empId",empid));
            
            
            String query1 = "from HrLeaveEmpDtls as empLookup where empLookup.hrEisEmpMst.empId = "
                    + empid+" and empLookup.isDeleted!=1 order by empLookup.hrEisEmpMst.orgEmpMst.empFname,empLookup.hrEisEmpMst.orgEmpMst.empMname,empLookup.hrEisEmpMst.orgEmpMst.empLname";
            sqlQuery= hibSession.createQuery(query1);
            
        }
        catch(Exception e)
        {
        	logger.error("Error is: "+ e.getMessage());
        }
        //return objCrt.list();
        return sqlQuery.list();
    }
 	
 	public List getAllEmpLeaveData()
    {
 		logger.info("==========================");
        Criteria objCrt = null;
        try
        {
            Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrLeaveEmpDtls.class);
            
            objCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
            objCrt.createAlias("hrEisEmpMst", "hrEisEmpMst");
            
            objCrt.setFetchMode("hrEisEmpMst.orgEmpMst", FetchMode.JOIN);
            objCrt.createAlias("hrEisEmpMst.orgEmpMst", "orgEmpMst");
            
            objCrt.addOrder(Order.asc("orgEmpMst.empFname"));
            objCrt.addOrder(Order.asc("orgEmpMst.empMname"));
            objCrt.addOrder(Order.asc("orgEmpMst.empLname"));
        }
        catch(Exception e)
        {
        	logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
 	
 	
 	public HrLeaveEmpDtls getEmpLeaveInfo(Long leaveid)
    {
 		HrLeaveEmpDtls hrLeaveEmpDtls = new HrLeaveEmpDtls();
        try
        {
        	Session hibSession = getSession();
            String query1 = "from HrLeaveEmpDtls as empLookup where empLookup.empLeaveId = "
                    + leaveid+" order by empLookup.hrEisEmpMst.orgEmpMst.empFname,empLookup.hrEisEmpMst.orgEmpMst.empMname,empLookup.hrEisEmpMst.orgEmpMst.empLname";
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrLeaveEmpDtls = (HrLeaveEmpDtls)sqlQuery1.uniqueResult();

        }
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrLeaveEmpDtls;
    }
 	
 	public List getAllEmpLeaveData(List userList ,long EmpId) {        
 		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrLeaveEmpDtls.class);

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

 	public List getAllEmpLeaveData(String locationCode ,long EmpId,long langId,long notAdminUser,long loggedInUser) {     
 		
        List dataList = new ArrayList();
 		Session hibSession = getSession();
 		
        String query = " from  HrLeaveEmpDtls e where  ";
        if(!locationCode.equals(""))//location specific
        {
        	query+=" e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and  e.hrEisEmpMst.orgEmpMst.empSrvcExp >= "+ sdate +" and ";
        	query+="pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"+locationCode+"' and c.cmnLanguageMst.langId="+langId+")) and ";
        /*query1+="  e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in ";
        query1+=" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "+userId+" ) ) ";*/	
        	
        }
        
        if(EmpId!=0)
	        query+=" e.hrEisEmpMst.orgEmpMst.empId  ="+EmpId+" and ";
        if(notAdminUser==1)
        {
        	logger.info("notAdminUser so getting list only for logged in user ");
        	query+=" e.hrEisEmpMst.orgEmpMst.orgUserMst.userId = "+loggedInUser+" and ";
        }
	        query+=" e.isDeleted = 0  order by e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname,e.hrEisEmpMst.orgEmpMst.empLname ";	
	        
	        Query sqlQuery = hibSession.createQuery(query);
	        
	        dataList = sqlQuery.list();
        return dataList;
    }

 	//for HOME Dept purpose
 	public HrLeaveEmpDtls getEmpLeaveInfoFromEmpId(Long empId, Long leaveTypeId, Date fromDate)
    {
 		logger.info("varun" +empId + leaveTypeId + fromDate);
 		HrLeaveEmpDtls hrLeaveEmpDtls = new HrLeaveEmpDtls();
       List list = new ArrayList();
 		try
        {

 			Session hibSession = getSession();
        	logger.info("going to test the function "+fromDate);
        	
        	Criteria orbCrt = hibSession.createCriteria(HrLeaveEmpDtls.class);
        	
        	orbCrt.setFetchMode("hrEisEmpMst", FetchMode.JOIN);
        	
        	orbCrt.add(Restrictions.eq("leaveFromDate", fromDate));
        	orbCrt.add(Restrictions.eq("hrEssLeaveMst.leavecode", leaveTypeId));
        	orbCrt.add(Restrictions.eq("hrEisEmpMst.empId", empId));
        	orbCrt.add(Restrictions.eq("isDeleted", 0));

        	logger.info("Size: " + orbCrt.list().size() );
        	logger.info("data is : " + orbCrt.list());
        	
        	list= (List)orbCrt.list();

        	logger.info("the list is "+list);
        	if(list!=null && list.size()>0)
        		hrLeaveEmpDtls = (HrLeaveEmpDtls)list.get(0);
        }
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrLeaveEmpDtls;
    }
 	
 	/**
 	 * Method will give all the employees list who has taken leave
 	 * for particular Location. It will also check for the post
 	 * activation flag on which employee is assigned.
 	 * @param locId
 	 * @return List of active employees who has taken Leave
 	 */
 	public List getLeaveDataByDept(long locId,long billNo,long langId) {
 		List<Object> lstLoanDtls = new ArrayList<Object>();
 		Session hibSession = getSession();
 		StringBuffer leaveQryBuffer = new StringBuffer();
 		leaveQryBuffer.append("select le.emp_id,up.post_id from hr_leave_emp_dtls le ");
 		leaveQryBuffer.append("inner join hr_eis_other_dtls od on od.emp_id = le.emp_id ");
 		leaveQryBuffer.append("inner join hr_eis_emp_mst eisemp on eisemp.emp_id = od.emp_id ");
 		leaveQryBuffer.append("inner join org_emp_mst orgemp on orgemp.emp_id = eisemp.emp_mpg_id ");
 		leaveQryBuffer.append("inner join org_userpost_rlt up on up.user_id = orgemp.user_id ");
 		if(billNo!=0) {
 			leaveQryBuffer.append(" inner join hr_pay_post_psr_mpg psr on psr.post_id = up.post_id ");
 			leaveQryBuffer.append(" inner join mst_dcps_bill_group mpg on mpg.bill_group_id = psr.bill_no ");
 			leaveQryBuffer.append(" where up.activate_flag=1 and mpg.bill_group_id=");
 			leaveQryBuffer.append(billNo);
 			leaveQryBuffer.append(" and mpg.loc_id=");
 			leaveQryBuffer.append(locId); 
 			leaveQryBuffer.append(" and le.loc_id=");
 			leaveQryBuffer.append(locId);
 	 		}
 	 		else {
 	 			leaveQryBuffer.append(" where up.activate_flag=1  ");
 	 			leaveQryBuffer.append(" and le.loc_id=");
 	 			leaveQryBuffer.append(locId);
 	 		}
 		leaveQryBuffer.append(" and orgemp.LANG_ID=");
 		leaveQryBuffer.append(langId);	
 		Query sqlQuery = hibSession.createSQLQuery(leaveQryBuffer.toString());
 		lstLoanDtls = sqlQuery.list();
 		return lstLoanDtls;
 	}
 	
}//end class
