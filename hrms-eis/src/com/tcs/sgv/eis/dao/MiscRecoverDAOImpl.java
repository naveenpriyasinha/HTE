package com.tcs.sgv.eis.dao;
//comment By Maruthi For  import Organisation.
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
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrMiscRecoverDtls;


public class MiscRecoverDAOImpl extends GenericDaoHibernateImpl<HrMiscRecoverDtls, Long> implements MiscRecoverDAO {

	Log logger = LogFactory.getLog(getClass());
 	public MiscRecoverDAOImpl(Class<HrMiscRecoverDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();
 	public List getAllMiscData()
    {
        Criteria objCrt = null;
        try
        {
        	Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrMiscRecoverDtls.class);
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
 	public List getAllMiscDataByEmpId(HrEisEmpMst hrEisEmpMst)
    {
        Criteria objCrt = null;
        try
        {
        	Session hibSession = getSession();
            objCrt = hibSession.createCriteria(HrMiscRecoverDtls.class);
            objCrt.add(Restrictions.like("hrEisEmpMst",hrEisEmpMst));
            objCrt.add(Restrictions.eq("miscActivateFlag",1));
            logger.info(objCrt.list().size());
        }
        catch(Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return objCrt.list();
    }
 	public HrMiscRecoverDtls getMiscDataByMiscId(String miscid)
    {
 		HrMiscRecoverDtls hrMiscRecoverDtls = new HrMiscRecoverDtls();
        try
        {
            Session hibSession = getSession();
            String query1 = "from HrMiscRecoverDtls as a where a.miscId = "
                    + miscid;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrMiscRecoverDtls = (HrMiscRecoverDtls)sqlQuery1.uniqueResult();
            logger.info("The query is----->"+sqlQuery1);
            
        }
        catch (Exception e)
        {
            logger.error("Error is: "+ e.getMessage());
        }
        return hrMiscRecoverDtls;
    }
 	
 	public List getAllMiscData(List userList ,long EmpId) {        
 		Criteria objCrt = null;
        Session hibSession = getSession();
        objCrt = hibSession.createCriteria(HrMiscRecoverDtls.class);
            
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
 	
 	public List getAllMiscData(String locationCode ,long EmpId,long langId) {        
        List dataList=new ArrayList();
 		Session hibSession = getSession();
 		
        String query = " from  HrMiscRecoverDtls e where  ";
        if(!locationCode.equals(""))//location specific
        {
        	query+=" e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and  e.hrEisEmpMst.orgEmpMst.empSrvcExp >= "+ sdate + " and ";
        	query+="pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locationCode='"+locationCode+"' and c.cmnLanguageMst.langId="+langId+"))";
        /*query1+="  e.hrEisEmpMst.orgEmpMst.orgUserMst.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in ";
        query1+=" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "+userId+" ) ) ";*/	
        }
        
        if(EmpId!=0)
        query+=" and e.hrEisEmpMst.orgEmpMst.empId  ="+EmpId;
  
        query+=" order by e.hrEisEmpMst.orgEmpMst.empFname,e.hrEisEmpMst.orgEmpMst.empMname,e.hrEisEmpMst.orgEmpMst.empLname ";	
        Query sqlQuery = hibSession.createQuery(query);
        
        dataList = sqlQuery.list();
        return dataList;
    }

 	public List getRltBillTypeEdpData()
 	{
 		List dataList = new ArrayList();
 		Session session = getSession();
 		String qry=" from RltBillTypeEdp r where r.subjectId = 3 and r.addDedFlag = 'A' or r.addDedFlag='-(d)' or r.expRcpRec = 'REC'";
 		Query sqlQry = session.createQuery(qry);
 		logger.info("The query from getRltBillTypeEdpData is---->>>"+sqlQry);
 		dataList =  sqlQry.list();
 		return dataList;
 	}
 	
 	public List getPaybillMiscDtls(long empId,long miscId,int monthGiven,int yearGiven){
 		List miscDtlsList = new ArrayList();
 		Session hibSession = getSession();
 		//String strQuery = " from HrPayPaybillMiscDtls hrPayPaybillMiscDtls where hrPayPaybillMiscDtls.miscId="+miscId+" and  hrPayPaybillMiscDtls.paybillId.month="+(double)monthGiven+" and hrPayPaybillMiscDtls.paybillId.year="+(double)yearGiven +" and hrPayPaybillMiscDtls.paybillId.hrEisEmpMst.empId="+empId;
 		String strQuery = " from HrPayPaybillMiscDtls hrPayPaybillMiscDtls where hrPayPaybillMiscDtls.miscId="+miscId+" and  hrPayPaybillMiscDtls.paybillId.paybillGrpId in (select distinct(hrPayPaybill) from PaybillHeadMpg headMpg where headMpg.month ="+(double)monthGiven+" and headMpg.year="+(double)yearGiven +"  and headMpg.approveFlag in (0,1)) and hrPayPaybillMiscDtls.paybillId.hrEisEmpMst.empId="+empId;
 		Query sqlQuery = hibSession.createQuery(strQuery);
 		miscDtlsList = sqlQuery.list();
 		return miscDtlsList;
 	}

}