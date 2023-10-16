package com.tcs.sgv.eis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;

public class SchedulerDAOImpl extends GenericDaoHibernateImpl<HrPayPaybillScheduler, Long> implements  SchedulerDAO
{
	public SchedulerDAOImpl(Class<HrPayPaybillScheduler> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	

	public List getAllData(long langId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
            String strQuery = "from HrPayPaybillScheduler schedulr where schedulr.cmnLanguageMst.langId ="  + langId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	public List<HrPayPaybillScheduler> getBillDetails(long SchedulerId)
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
           
            String strQuery = "from HrPayPaybillScheduler hr where hr.schedulerId = " + SchedulerId + " Order by hr.cmnLocationMst.locId";
            
        
            
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("getBillDetails List size in DAO:-->" + list.size());
            logger.info("getBillDetails ###List size in DAO######################" +list.size());
        return list;
	
	//" from HrPayPaybillScheduler where schedulerId = " + schedulerId;
	}
	
	/**	
	 * Method to check duplicate record for combination of
	 *  particular department and bill number.
	 * @param hrPayPaybillScheduler
	 * @returns
	 */
 /*public int checkDuplicateRecord(HrPayPaybillScheduler hrPayPaybillScheduler) {
	 Criteria objCrt = null;
   //  List  list = null;
         Session hibSession = getSession();
         HrPayPaybillScheduler resultObject = null;
         logger.info("dupli132425######### ");
         String strQuery = "from HrPayPaybillScheduler hr where hr.cmnLocationMst.locId = " + hrPayPaybillScheduler.getCmnLocationMst().getLocId()  + " and hr.hrpaybillsubheadmpg.billId=" 
         +hrPayPaybillScheduler.getHrpaybillsubheadmpg().getBillId();                  
         logger.info("duplicate ########jk#562665######### " + strQuery);
         Query query = hibSession.createQuery(strQuery);
         //Query query = hibSession.createQuery("from HrEisBranchMst");  
         
         logger.info("duplicate ############## "+query.uniqueResult());
         if(query.uniqueResult()!=null)
         {
        	 logger.info(" ############## ");
           resultObject = (HrPayPaybillScheduler)query.uniqueResult();
           logger.info("du############# ");
         }
         logger.info("resultObject List size in DAO:-->" + resultObject);
         logger.info("resultObject ###List size in DAO######################" +resultObject);
         if(resultObject==null)
        	 return 0;
         else
        	 return 1;
         
 }*/
	
	public int checkDuplicateRecord(HrPayPaybillScheduler hrPayPaybillScheduler) {
		 Criteria objCrt = null;
	    List  list = null;
	         Session hibSession = getSession();
	         HrPayPaybillScheduler resultObject = null;
	         logger.info("dupli132425######### ");
	         String strQuery = "from HrPayPaybillScheduler hr where hr.cmnLocationMst.locId = " + hrPayPaybillScheduler.getLocId()  + " and hr.hrpaybillsubheadmpg.billId=" 
	         +hrPayPaybillScheduler.getBillNo() +  "and hr.day = "+hrPayPaybillScheduler.getDay();                  
	         logger.info("duplicate ########jk#562665######### " + strQuery);
	         Query query = hibSession.createQuery(strQuery);
	         
	         list = query.list();
	            logger.info("District List size in DAO is:::::::::" + list.size());
	        if(list.size()!=0)
	        return 1;
	        else
	        	return 0;
	         
	 }	
	
	/**
	 * 
	 * @param billId
	 * @param location_Id
	 * @param schedulerId
	 * @return
	 */
	
	public List chkBillData(long locationId, long billId)
	{	
		List billList = null;
		Session hibSession = getSession();
			// String strQuery = new StringBuffer();
			// strQuery.append(" FROM HrPayPaybillScheduler o WHERE o.getHrpaybillsubheadmpg().getBillId() = "+billId+" and o.hrPayPaybillScheduler.getCmnLocationMst().getLocId() = "+locationId);
			 String strQuery = "from HrPayPaybillScheduler o WHERE o.cmnLocationMst.locId = "+locationId+" and o.hrpaybillsubheadmpg.billHeadId ="+billId;

		     logger.info("In chkBillHeadDataById 1 --Query for uniqueness in bill head " + strQuery);
		     

		     Query query = hibSession.createQuery(strQuery);
			 billList = query.list();
			 logger.info("chkBillDataById   in schedulerdaoimpl "+billList.size());
		return billList;
	}
	
	
	/*public HrPayPaybillScheduler chkBillData(long locationId, long billId)
	{	
		List billList = null;
		Session hibSession = getSession();
			 StringBuffer strQuery = new StringBuffer();
			// strQuery.append(" FROM HrPayPaybillScheduler o WHERE o.getHrpaybillsubheadmpg().getBillId() = "+billId+" and o.hrPayPaybillScheduler.getCmnLocationMst().getLocId() = "+locationId);
			 strQuery.append(" FROM HrPayPaybillScheduler o WHERE o.cmnLocationMst.locId = "+locationId+" and o.hrpaybillsubheadmpg.billId ="+billId);

		     logger.info("In chkBillHeadDataById 1 --Query for uniqueness in bill head " + strQuery);
		     
		    
			 Query query = hibSession.createQuery(strQuery.toString());
			 HrPayPaybillScheduler hrPayPaybillScheduler = (HrPayPaybillScheduler)query.uniqueResult();
			 logger.info("chkBillDataById   in schedulerdaoimpl "+hrPayPaybillScheduler.getCmnLocationMst().getLocName());
		return hrPayPaybillScheduler;
	}
	
	*/

	
	
	// Added by Muni for Multiple Bill generation
	
	//Added for getting BillDetails Order By Day
	public List<HrPayPaybillScheduler> getBillDetailsForBillGen(long LOC_ID)
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            String strQuery = "from HrPayPaybillScheduler as sch where sch.cmnLocationMst.locId = " + LOC_ID + "order by sch.day" ;
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
            logger.info("get Scheduler list size in  SchedulerDAOImpl:::::::::::::" +list.size());
        return list;
	
	}
	//Added for getting BillDetails 
	
	public List getBillDetails()
	{
		Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            /*String strQuery = "from SgvaBudsubhdMst as sbm, HrPayOrderSubHeadMpg as hpm, HrPayPaybillScheduler as aps where hpm.element_code=aps.hrpaybillsubheadmpg.subheadId" +
            		"and hpm.finYearId=sbm.finYrId and sbm.budsubhdId=hpm.order_subhead_pk"  ;
*/            
            String strQuery = "Select a.demand_code,a.budmjrhd_code,a.budsubmjrhd_code,a.budminhd_code,b.budsubhd_id,b.element_code,c.bill_subhead_id,c.bill_no,c.dsgn_id,c.class_id from sgva_budsubhd_mst a, hr_pay_order_subhead_mpg b,hr_pay_bill_subhead_mpg c where a.budsubhd_id = b.budsubhd_id and a.fin_yr_id = b.fin_yr_id and b.element_code = c.subhead_code" ;
            logger.info("strQuery is********************* " +strQuery);
            Query query1 = hibSession.createSQLQuery(strQuery.toString());
            logger.info("After converting query*************** " + query1 );
            list = query1.list();
            logger.info("get Bill list size in  SchedulerDAOImpl ************" +list.size());
            logger.info("get Bill list size in  SchedulerDAOImpl ************" +list.size());
            logger.info(query1);
        return list;
	}
	
	
	
	// Ended by Muni for Multiple Bill generation
	
}

