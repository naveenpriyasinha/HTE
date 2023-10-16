package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.allowance.valueobject.HrPayAllowTypeMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.deduction.valueobject.HrPayDeducTypeMst;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisGrpMangMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybillScheduler;

public class GroupManagementDAOImpl extends GenericDaoHibernateImpl<HrEisGrpMangMst, Long>{
	Log logger = LogFactory.getLog(getClass());
	public GroupManagementDAOImpl(Class<HrEisGrpMangMst> type, SessionFactory sessionFactory)
     {
		super(type);
		setSessionFactory(sessionFactory);
	  }

	
	public List<HrPayAllowTypeMst> getAllowancesFromType(long Type)
	//public List getDuplicateData(long gradeId)
	
	{
		List<HrPayAllowTypeMst> listComps = null;
		
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer(	
				//"Select * from Hr_Pay_Allow_Type_Mst hpat where hpat.allow_code not in (Select allow_id from Hr_Pay_Expression_Mst)");
				"from HrPayAllowTypeMst hpat where hpat.allowCode not in (select  hpe.hrPayAllowTypeMst.allowCode from HrPayExpressionMst hpe)");
		logger.info("Query in forComponentCombo is------>>>>>> " + strQuery.toString());
		Query query1 = hibSession.createQuery(strQuery.toString());
		listComps = query1.list();
		
		return listComps;
	}
	public List<HrPayDeducTypeMst> getDeductionFromType(long Type)
		{
		    List<HrPayDeducTypeMst> listComps = null;
			Session hibSession = getSession();
			StringBuffer strQuery = new StringBuffer(
					//"Select * from hr_pay_deduc_type_mst hpd where hpd.deduc_code not in (Select hde.deduc_id from hr_deduc_exp_mst hde)");	
					
					"from HrPayDeducTypeMst hpd where hpd.deducCode not in (select hpe.hrPayDeducTypeMst.deducCode from HrDeducExpMst hpe )");
			logger.info("Query in getAllDataFromBillId is------>>>>>> " + strQuery.toString());
			Query query2 = hibSession.createQuery(strQuery.toString());
			listComps = query2.list();	
			
			return listComps;
		}
		
	
	public List<CmnLookupMst> forTypeCombo(String TypeId)
	{
		List typeList = new ArrayList();
		Session hibSession1 = getSession();
		String hql="from CmnLookupMst clm where clm.lookupId in (300135,300134)";
		//String hql="select * from Cmn_Lookup_Mst clm where clm.lookup_Id in (300135,300134)";
			
        //Query typeQuery=hibSession1.createSQLQuery(hql);
        Query query = hibSession1.createQuery(hql.toString());      
		//Query query = hibSession1.createSQLQuery(hql);
        typeList =query.list();
        logger.info("typeList size:::::::::::::::::::: " + typeList.listIterator());
        return typeList;                        
	}
	


public List getdesigsfromgrades(long gradeId)
//public List getDuplicateData(long gradeId)
{
	List<HrEisGdMpg> listDepts = null;
	Session hibSession = getSession();
	StringBuffer strQuery = new StringBuffer(
			"from HrEisGdMpg bhm where bhm.orgGradeMst.gradeId="
					+ gradeId + " Order by bhm.gdMapId" );
	logger.info("Query in getAllDataFromBillId is------>>>>>> "
			+ strQuery.toString());
	Query query = hibSession.createQuery(strQuery.toString());
	listDepts = query.list();
	return listDepts;
    
}

public List getGroupMangData(long locId)
{
	List<HrEisGrpMangMst> listAllData = null;
	Session hibSession = getSession();
	StringBuffer myQuery = new StringBuffer
	//("Select hgm.cmnLookupMst. lookupId, hgm.componentId, hgm.startDate, hgm.endDate, hgm.amount, hgm.orgGradeMst.gradeId, hgm.designationId from HrEisGrpMangMst hgm");
	(" from HrEisGrpMangMst where cmnLocationMst.locId= " + locId);
	logger.info("Query in getAllDataFromBillId is------>>>>>> "
			+ myQuery.toString());
	Query query = hibSession.createQuery(myQuery.toString());
	listAllData = query.list();
	return listAllData;
    
}

public List<HrEisGrpMangMst> getUpdatedDetails(long groupManagementId)
{
	Criteria objCrt = null;
    List  list = null;
        Session hibSession = getSession();
       
        String strQuery = "from HrEisGrpMangMst hgp where hgp.groupManagementId = " +groupManagementId+"  Order by hgp.cmnLocationMst.locId";
        
    
        
        Query query = hibSession.createQuery(strQuery);
        //Query query = hibSession.createQuery("from HrEisBranchMst");
        list = query.list();
        logger.info("getComponentDetails ###List size in DAO######################" +list.size());
    return list;

//" from HrPayPaybillScheduler where schedulerId = " + schedulerId;
}



public List validateMonthandYear(long year, long month,long gradeId)
{
	logger.info("Inside validateMonthandYear::::::::::::::::::::::::::::::");
	List list = null;
	 Session hibSession = getSession();
	 
	 StringBuffer que = new StringBuffer("Select * FROM HR_EIS_GRP_MANG_MST  WHERE  (extract(year from start_date) >=" +year+" and extract(month from start_date) >="+month+") or  ("+year+"<= extract(year from end_date) and "+month+"<= extract(month from end_date)) and CLASS_ID ="+gradeId);
	/* que.append(" WHERE ");
	 que.append(" (");
	 que.append("extract(year from start_date) >= " +year+ "  and extract(month from start_date) >= "+month+")");
	 que.append(" and ");
	 que.append(" (");
	 que.append(year+ "<= extract(year from end_date) and "+month +"<= extract(month from end_date)) and CLASS_ID =" + gradeId );
*/
	 Query query = hibSession.createSQLQuery(que.toString());
	 logger.info("query::::::::::::: " +query);
     list = query.list();
     logger.info("list size:::::::::::::::::::: " + list.size());
     return list;
	
}

}	    