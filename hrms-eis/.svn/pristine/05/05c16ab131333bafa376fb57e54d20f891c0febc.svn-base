package com.tcs.sgv.eis.dao;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPaySalRevMst;

public class SalRevMstDAOImpl extends GenericDaoHibernateImpl<HrPaySalRevMst, Long> implements SalRevMstDAO {
	Log logger = LogFactory.getLog( getClass() );
 	public SalRevMstDAOImpl(Class<HrPaySalRevMst> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
 	
 	public List getAllSalRevMstData( long locId)
    {
 		List hrPaySalRevMst = null;
        Session hibSession = getSession();
        String query1 = "from HrPaySalRevMst as salRevLookup where salRevLookup.cmnLocationMst.locId = "+ locId ;
        logger.info("Query is +++"+query1.toString());
        Query sqlQuery1 = hibSession.createQuery(query1);
        hrPaySalRevMst =   sqlQuery1.list();
        
        logger.info("query is----"+sqlQuery1);
        return hrPaySalRevMst;
    }
 	
 	public List getRltBillTypeEdpData()
 	{
 		List dataList = new ArrayList();
 		Session session = getSession();
 		//String qry=" from RltBillTypeEdp r where r.subjectId = 3 and r.activateFlag = '1' and  r.typeEdpId in (select s.rltBillTypeEdp.typeEdpId  from HrPayEdpCompoMpg s where s.cmnLookupId in (300134,300135))";//HardCode For EDP fetching only Allowances and dedcutions.
 		String qry=" from com.tcs.sgv.eis.valueobject.RltBillTypeEdp r  where r.edpCode is not null and r.edpCode !=' ' and (r.edpMethodName is not null and r.edpMethodName!= ' ') and ( r.expRcpRec!='REC' or  r.expRcpRec is null ) or (r.edpCode='0101 ' ) order by r.addDedFlag desc,r.typeEdpId asc ";
 		Query sqlQry = session.createQuery(qry);
 		logger.info("The query from getRltBillTypeEdpData is---->>>"+sqlQry);
 		dataList =  sqlQry.list();
 		return dataList;
 	}

 	public HrPaySalRevMst getSalRevMst(long salrRevId)
 	
 	{
 	HrPaySalRevMst hrPaySalRevMst =new HrPaySalRevMst();
 	Session session = getSession();
 	String query = "from HrPaySalRevMst where salRevId="+salrRevId;
 	Query sqlQuery = session.createQuery(query);
 	hrPaySalRevMst = (HrPaySalRevMst) sqlQuery.uniqueResult();
 	return hrPaySalRevMst;
 	
 	}
 	
 	

 	public List getOrderDataFromPara(String newOrderName,String date , long revStatus) {
 		logger.info("*****************inside getOrderDataFromPara************");
 		List  list = null;        
 	    Session hibSession = getSession();   
 	   
   	
 	    
 	    Query query = hibSession.createSQLQuery(" select * from  hr_pay_sal_rev_mst  a where  lower(a.rev_order_no)='"+newOrderName+"' and a.rev_order_date='"+date+"' and a.rev_status="+revStatus);        
 	    list = query.list();
 	    logger.info("The query is----------->>>>>>"+query.toString());
 	    logger.info("The Size od list is:-"+list.size());
 	return list;
}
 	
 	public List<HrPaySalRevMst> getActiveArrears(int monthGiven, int yearGiven,CmnLocationMst cmnLocationMst,String salRevId)
 	{
 		List dataList = new ArrayList();
 		Session session = getSession();
 		
		DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	    SimpleDateFormat sdf = sbConf.GetDateFormat(); 		
 		
 		Calendar cal = Calendar.getInstance();
 		cal.set(Calendar.DATE, 1);
 		cal.set(Calendar.YEAR, yearGiven);
        cal.set(Calendar.MONTH, monthGiven-1);
        
        Date startDate = cal.getTime();
        String StartDt = sdf.format(startDate);
        
        int maxDays = cal.getActualMaximum(5);        
        cal.set(Calendar.DATE, maxDays); 	
        Date endDate = cal.getTime();
        String endDt = sdf.format(endDate);
 		
 		String qry=" from HrPaySalRevMst as salRevLookup where salRevLookup.revStatus = 1 and salRevLookup.revPayOutDate <= '"+endDt+"' and salRevLookup.revPayOutDate >= '"+StartDt+"'  and " +
 				"  salRevLookup.cmnLocationMst.locId = "+cmnLocationMst.getLocId();
 		
 		if(!salRevId.equals("0"))
 			qry+=" and salRevLookup.salRevId= "+salRevId;	
 			
 		Query sqlQry = session.createQuery(qry);
 		logger.info("The query from getRltBillTypeEdpData is---->>>"+sqlQry);
 		dataList =  sqlQry.list();
 		return dataList;
 	}
 	
}
