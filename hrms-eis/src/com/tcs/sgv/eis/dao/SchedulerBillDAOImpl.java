
package com.tcs.sgv.eis.dao;

import java.util.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBranchMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;

public class SchedulerBillDAOImpl extends GenericDaoHibernateImpl<HrPayPaybillScheduler, Long> implements SchedulerBillDAO {

	public SchedulerBillDAOImpl(Class<HrPayPaybillScheduler> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getPayBillNo(long locId,long billNo)
	{  
		 Session hibSession = getSession();
		String strQuery="select mpg.BILL_SUBHEAD_ID from  hr_pay_bill_subhead_mpg mpg where mpg.BILL_NO="+billNo+" and mpg.LOC_ID="+locId;
		logger.info("query in getPayBillNo"+strQuery);
		 Query query = hibSession.createSQLQuery(strQuery);
		 List list=query.list();
		 return list;
	}
	
	/**
	 * To get All the Bills of a ddo 
	 * @param ddoCode
	 * @return
	 */
	public List getPayBillNo(long  ddoCode)
	{  
		 Session hibSession = getSession();
		String strQuery="SELECT BILL_GROUP_ID FROM MST_DCPS_BILL_GROUP where DDO_CODE= " +ddoCode;
		 Query query = hibSession.createSQLQuery(strQuery);
		 List list=query.list();
		 return list;
	}
	
	public List getAllData()
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");
           
          //  String strQuery = "from HrPayPaybillScheduler schedulr where schedulr.cmnLanguageMst.langId ="  + langId;
           String strQuery="SELECT PSM.BILL_NO ,DAY,ps.LOC_ID,ps.BILL_SUBHEAD_ID FROM HR_PAY_PAYBILL_SCHEDULER PS,HR_PAY_BILL_SUBHEAD_MPG PSM WHERE PS.BILL_SUBHEAD_ID=PSM.BILL_SUBHEAD_ID";
           logger.info("schedulerBillDAOImpl query in getAllData"+strQuery);
           logger.info("schedulerBillDAOImpl query in getAllData"+strQuery);
           Query query = hibSession.createSQLQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }
	
	
	/**
	 * @param day
	 * @return
	 */
	public List<HrPayPaybillScheduler> getAllData(int day,String threadId)
    {
        	Criteria objCrt = null;
        	List  list = null;
            Session hibSession = getSession();
          //  String strQuery="select  ddo_code ,BILL_NO,loc_id,month,year from HR_PAY_PAYBILL_SCHEDULER where DAY ="+day + " and THREAD_ID = " + threadId+" and ACTIVATE_FLAG = 1 ";
            //Query query = hibSession.createSQLQuery(strQuery);
            
            String strQuery = "from HrPayPaybillScheduler  where  threadId="+threadId+" and  day = "+day +" and  activateflag in (1,2)";
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
            return list;
    }
	
	
	public List getAllData2(long locId,long billNo,long day)
	{	
		List list=null;
		Session hibSession = getSession();
        logger.info("going to execute query....");
        String strQuery= "select sgvabudsub0_.BUDSUBHD_ID as col_0_0_, sgvabudsub0_.BUDSUBHD_CODE as col_1_0_, sgvabudsub0_.DEMAND_CODE as col_2_0_, sgvabudsub0_.BUDSUBHD_DESC_LONG"
        +" as col_3_0_, sgvabudsub0_.BUDMJRHD_CODE as col_4_0_, sgvabudsub0_.BUDSUBMJRHD_CODE as col_5_0_, sgvabudsub0_.BUDMINHD_CODE as col_6_0_, hrpaybillh1_.BILL_NO"
        +"  as col_7_0_, hrpaybillh1_.SUBHEAD_CODE as col_8_0_, hrpaybillh1_.loc_id as col_9_0_, hrpaybillh1_.FIN_YEAR_ID as col_10_0_, hrpaybillh1_.DSGN_ID as col_11_0_, hrpaybillh1_.CLASS_ID" 
        +" as col_12_0_, hrpayorder2_.ELEMENT_CODE as col_13_0_,hrpaybillh1_.CLASS_ID as column14,hrpaybillh1_.DSGN_ID as column15 from SGVA_BUDSUBHD_MST sgvabudsub0_, hr_pay_bill_subhead_mpg hrpaybillh1_, hr_pay_order_subhead_mpg hrpayorder2_" 
        +" where sgvabudsub0_.LANG_ID='en_US' and hrpaybillh1_.SUBHEAD_CODE=hrpayorder2_.ELEMENT_CODE" 
        +" and hrpayorder2_.FIN_YR_ID=23 and hrpayorder2_.BUDSUBHD_ID=sgvabudsub0_.BUDSUBHD_ID and"
        +" hrpaybillh1_.BILL_SUBHEAD_ID in (select mpg.BILL_SUBHEAD_ID from  hr_pay_bill_subhead_mpg mpg where mpg.BILL_NO="+billNo
        +" and mpg.LOC_ID="+locId+")";
        logger.info("schedulerBillDAOImpl query in getAllData2"+strQuery);
        logger.info("schedulerBillDAOImpl query getAllData2"+strQuery);
        Query query = hibSession.createSQLQuery(strQuery);
        list = query.list();
        logger.info("List size in DAO:-->" + list.size());
        logger.info("List size in DAO:-->" + list.size());
       	return list;
	}
	
	public List getBillStructure(long billNo)
	{	
		List list=null;
		Session hibSession = getSession();
       
        String strQuery= "SELECT scheme.DEMAND_CODE,scheme.MAJOR_HEAD,scheme.SUB_MAJOR_HEAD,scheme.MINOR_HEAD,scheme.SUB_HEAD,scheme.scheme_code FROM MST_SCHEME scheme , MST_DCPS_BILL_GROUP bill" 
        	+" where bill.SCHEME_CODE = scheme.SCHEME_CODE and bill.BILL_GROUP_ID = "+billNo;
        Query query = hibSession.createSQLQuery(strQuery);
        list = query.list();
       	return list;
	}
	
	public boolean checkPaybill(int month, int year, long billno)
	{

		List paybillData = new ArrayList();
		int size = 0;
		Session hibSession = getSession();

		// this is the Check For Bill generated or not
		StringBuffer strQuery = new StringBuffer("select hm.PAYBILL_ID from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH= " + month + " and hm.PAYBILL_YEAR= " + year + " and hm.APPROVE_FLAG in (0,1) and hm.BILL_NO= " + billno);
		Query query = hibSession.createSQLQuery(strQuery.toString());
		paybillData = query.list();
		size = paybillData.size();
		if (size > 0)
			return true;
		else
			return false;

	}
	public boolean checkPaybillApprovedOrNotGenerated(int month, int year, long billno)
	{
		List paybillData = new ArrayList();
		int size = 0;
		Session hibSession = getSession();

		// this is the Check For Bill generated or not
		StringBuffer strQuery = new StringBuffer("select hm.approve_flag from PAYBILL_HEAD_MPG hm where hm.PAYBILL_MONTH= " + month + " and hm.PAYBILL_YEAR= " + year + " and hm.APPROVE_FLAG in (0,1) and hm.BILL_NO= " + billno);
		Query query = hibSession.createSQLQuery(strQuery.toString());
		paybillData = query.list();
		size = paybillData.size();
		if (size > 0){
			if(Integer.parseInt(paybillData.get(0).toString()) == 1)
				return false;//Already Approved so cannot Approve
			else
				return true;//Generated and Not Approved so can be Approved
		}
		else{
			return false;//Neither Generated Nor Approved so cannot Approve
		}
	}
	

}
