package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrEisQtrMst;
import com.tcs.sgv.eis.valueobject.HrEisQtrMstHst;

public class QuaterMstDAOImpl extends GenericDaoHibernateImpl<HrEisQtrMst, Long> implements QuaterMstDAO {
	public QuaterMstDAOImpl(Class<HrEisQtrMst> type, SessionFactory sessionFactory) {
        super(type);
        setSessionFactory(sessionFactory);
    }
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();
	public List getQuarterDtls(long userid)
	{
		List list = new ArrayList();
		
		Session hibSession = getSession();            
        Criteria crit = hibSession.createCriteria(HrEisQtrMst.class);
        crit.add(Restrictions.eq("orgUserMstByAllocatedTo.userId", userid));
        logger.info("the going to execute master query");
        list = crit.list();
		
		
		return list;
	}
	
	public List getQuarterDtlsFromHst(long userid,long quater_id)
	{
		List list = new ArrayList();
		
		Session hibSession = getSession();            
        Criteria crit = hibSession.createCriteria(HrEisQtrMstHst.class);
        crit.add(Restrictions.eq("orgUserMstByAllocatedTo.userId", userid));
        crit.add(Restrictions.ne("hrQuaterTypeMst.quaId",quater_id));
        crit.add(Restrictions.isNotNull("allocationEndDate"));
        logger.info("the going to execute hst query");
        list = crit.list();
		
		
		return list;
	}
	
	public List getAllQuarterDetailsByLocationId(long userId,long locationId,long langId)
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
           /* String queryString = "select hrQtrMst,empMst from HrEisQtrMst hrQtrMst,OrgEmpMst empMst where hrQtrMst.orgUserMstByAllocatedTo.userId=empMst.orgUserMst.userId and empMst.cmnLanguageMst.langId = "+langId+" and hrQtrMst.orgUserMstByAllocatedTo.userId in ";
            queryString+="(select userPostRlt.orgUserMst.userId from OrgUserpostRlt userPostRlt,OrgPostDetailsRlt postDetailsRlt where userPostRlt.orgPostMstByPostId =postDetailsRlt.orgPostMst.postId and postDetailsRlt.cmnLocationMst.locId in ";
            queryString+="(select cmnLocMst.locId from CmnLocationMst cmnLocMst where cmnLocMst.locId="+locationId+" or cmnLocMst.parentLocId="+locationId+" )) and empMst.empSrvcExp >= "+ sdate + " ";
            if(userId!=0){
            	queryString+=" and hrQtrMst.orgUserMstByAllocatedTo.userId="+userId;
            }
            queryString+=" order by empMst.empLname,empMst.empFname,empMst.empMname ";*/
            
            String queryStringNew = "select hrQtrMst,empMst from HrEisQtrMst hrQtrMst,OrgEmpMst empMst,OrgUserpostRlt userPostRlt,OrgPostDetailsRlt postDetailsRlt  ";
            queryStringNew+= " where hrQtrMst.orgUserMstByAllocatedTo.userId=empMst.orgUserMst.userId and empMst.cmnLanguageMst.langId = "+langId+" and hrQtrMst.orgUserMstByAllocatedTo.userId=userPostRlt.orgUserMst.userId ";
            queryStringNew+=" and userPostRlt.orgPostMstByPostId =postDetailsRlt.orgPostMst.postId and postDetailsRlt.cmnLocationMst.locId="+locationId;
            queryStringNew+=" and userPostRlt.activateFlag=1 and empMst.empSrvcExp >= "+ sdate + " ";
            if(userId!=0){
            	queryStringNew+=" and hrQtrMst.orgUserMstByAllocatedTo.userId="+userId;
            }
            queryStringNew+=" order by empMst.empLname,empMst.empFname,empMst.empMname ";
            /*String query = " select gpf.gpfAccNo,gpf.userId,e.empFname|| ' ' ||e.empMname|| ' ' ||e.empLname from HrGpfBalanceDtls gpf,OrgEmpMst e where gpf.userId  = e.orgUserMst.userId and e.cmnLanguageMst.langId = 1 ";
            if(locationId!=0)//location specific
            {  
            	query+=" gpf.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and ";
            	query+="pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locId="+locationId+" or c.parentLocId="+locationId+"))";
            /*query+=" and gpf.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in ";
            query+=" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "+userId+" ) ) ";	
            }
            if(empId!=0)
            	query+=" and gpf.empId="+empId;	
                query+=" order by e.empFname,e.empMname,e.empLname ";*/	
            Query sqlQuery = hibSession.createQuery(queryStringNew);
            GpfDetailsList = sqlQuery.list();
        
        return GpfDetailsList;
    }
	
	public List getQuarterDtlsFromQuarterId(long quarter_id)
	{
		List list = new ArrayList();
		
		Session hibSession = getSession();            
		String querystring ="from HrEisQtrMst where quarterId = '" + quarter_id + "'";
			
		Query query = hibSession.createQuery(querystring);
        logger.info("the going to execute hst query");
        list =query.list();
		logger.info("the size of the quarter list is : " + list.size());
		
		
		return list;
	}
	
	/**
	 * Method will return the list of Emp Id and post Id who has 
	 * availed the Quarter by given location id and bill number.
	 * If bill number is 0, then it will return list of all the employees
	 * location wise.
	 * @param billNo
	 * @param locId
	 * @return
	 */
	public List getQuarterRecordsByLoc(long billNo, long locId,long langId){		
		List quarterList = null;
		Session hibernateSession = getSession();
		StringBuffer queryString = new StringBuffer();
		queryString.append("select distinct (orgemp.EMP_ID),up.post_id from HR_EIS_QTR_EMP_MPG le ");
		queryString.append(" inner join org_emp_mst orgemp on orgemp.user_id = le.ALLOCATED_TO ");
		queryString.append(" inner join org_userpost_rlt up on up.user_id = orgemp.user_id ");
		queryString.append(" inner join hr_pay_post_psr_mpg psr on psr.post_id = up.post_id ");
		queryString.append(" inner join mst_dcps_bill_group mpg on mpg.bill_group_id = psr.bill_no ");
		queryString.append(" where up.activate_flag=1 ");
		
		if(billNo!=0) {
			queryString.append(" and mpg.bill_group_id=");
			queryString.append(billNo);
		}
		
		queryString.append(" and mpg.loc_id=");
		queryString.append(locId);
		queryString.append(" and le.loc_id=");
		queryString.append(locId);	
		queryString.append(" and orgemp.LANG_ID=");
		queryString.append(langId);
		Query query = hibernateSession.createSQLQuery(queryString.toString());
        logger.info("the going to execute hst query");
        quarterList =query.list();
        logger.info("the size of the quarter list is : " + quarterList.size());				
		return quarterList;
	}
	
	public long getEmpIdFromUserId(long userId)
	{
		Session hibSession = getSession();
		StringBuffer qry = new StringBuffer(); 
		
		qry.append("select empmst.empId from HrEisEmpMst empmst where empmst.orgEmpMst.orgUserMst.userId="+userId);
		
		Query query=hibSession.createQuery(qry.toString());
		long empId=Long.parseLong(query.uniqueResult().toString());
		
		return empId;
	}
	
	public boolean checkQuarterExistence(long userId,long locId)
	{
		Session hibSession = getSession();
		StringBuffer qry = new StringBuffer(); 
		
		qry.append("from HrEisQtrMst qtrmst where qtrmst.orgUserMstByAllocatedTo.userId="+userId);
		qry.append(" and qtrmst.cmnLocationMstByLocId.locId="+locId);
		Query query=hibSession.createQuery(qry.toString());
		
		List empQtr=query.list();
		
		if(empQtr != null && empQtr.size() > 0)
		{	//06 jan 2011
			HrEisQtrMst hrEisQtrMst = (HrEisQtrMst) empQtr.iterator().next();
			Date vaccantDate = hrEisQtrMst.getAllocationEndDate();
			if(hrEisQtrMst.getAllocationEndDate()==null)
				return true;
			Calendar calGiven = Calendar.getInstance();
			calGiven.set(Calendar.DAY_OF_MONTH, 1);
			calGiven.set(Calendar.HOUR_OF_DAY, 0);
			calGiven.set(Calendar.MINUTE, 0);
			calGiven.set(Calendar.SECOND, 0);
			calGiven.set(Calendar.MILLISECOND, 0);
			
			Date billStartDate =  calGiven.getTime();
			int maxDay=calGiven.getActualMaximum(Calendar.DAY_OF_MONTH);
			calGiven.set(Calendar.HOUR_OF_DAY, 23);
			calGiven.set(Calendar.MINUTE, 59);
			calGiven.set(Calendar.SECOND, 59);
			calGiven.set(Calendar.MILLISECOND, 999);
			calGiven.set(Calendar.DAY_OF_MONTH,maxDay);
			Date billEndDate = calGiven.getTime();
			logger.info("vaccantDate"+vaccantDate);
			logger.info("BillStartDate"+billStartDate);
			if(vaccantDate.before(billStartDate))
				return false;
			logger.info("vaccantDate.after(billStartDate) "+vaccantDate.after(billStartDate)+" vaccantDate.before(billEndDate) "+vaccantDate.before(billEndDate)+" vaccantDate.after(billStartDate) && vaccantDate.before(billEndDate) "+(vaccantDate.after(billStartDate) && vaccantDate.before(billEndDate)));
			if(vaccantDate.after(billStartDate) && vaccantDate.before(billEndDate))
				return false;
				
			return true;
		}
		else 
			return false;
		
	}
//Added by Abhilash for custodian combo
	
	public List getCustodianList(String TresuryCode)
	{
		List list = new ArrayList();
		
		Session session = getSession();
		
		StringBuffer buffer = new StringBuffer();
		if(TresuryCode != null)
			buffer.append("from HrCustodianTypeMst custodian where custodian.divisionCode='"+TresuryCode+"' " );
		else
			buffer.append("from HrCustodianTypeMst custodian ");
		
		Query query = session.createQuery(buffer.toString());
		//System.out.println("getCustodianList query is ***************"+query);
		list = query.list();
		
		return list;
	}
	
	public List getCustodianDivisionCodeAndDesc(long CustodianId)
	{
		List list = new ArrayList();
		String divisionCode="";
		Session session = getSession();
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("from HrCustodianTypeMst custodian where custodian.custodianId="+CustodianId );
		
		Query query = session.createQuery(buffer.toString());
		//System.out.println("getCustodianDivisionCode query is ***************"+query);
		list = query.list();
		
		return list;
	}
	
	//Ended by Abhilash for custodian combo
	/**
	 * autho@manish khunt
	 * @param userId
	 * @return
	 */
	public HrEisQtrMst getHrEisQtrMst(long userId)
	{
		List list = new ArrayList();
		Session hibSession = getSession();            
        StringBuffer sb = new StringBuffer();
        HrEisQtrMst hrEisQtrMst = null;
        sb.append("select  qtr  from HrEisQtrMst qtr where qtr.orgUserMstByAllocatedTo.userId=");
        sb.append(userId);
        sb.append(" order by qtr.quarterId");
        Query query = hibSession.createQuery(sb.toString());
        list = query.list();
        if(list!= null && list.size()>0)
        {
        	hrEisQtrMst= (HrEisQtrMst)list.get(0);
        }
        return hrEisQtrMst;
		
		
	}
}
