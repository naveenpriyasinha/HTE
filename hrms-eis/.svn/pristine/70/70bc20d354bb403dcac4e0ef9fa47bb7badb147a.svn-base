package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.util.DBsysdateConfiguration;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;


public class EmpGpfDtlsDAOImpl extends GenericDaoHibernateImpl<HrPayGpfBalanceDtls, Long> implements EmpGpfDtlsDAO {
	Log logger = LogFactory.getLog( getClass() );
	DBsysdateConfiguration sbConf=new DBsysdateConfiguration();
	String sdate=sbConf.getSysdate();
 	public EmpGpfDtlsDAOImpl(Class<HrPayGpfBalanceDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	public List getAllGpfDetails(long empID)
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
            String query = " select gpf.gpfAccNo,gpf.userId,e.empFname|| ' ' ||e.empMname|| ' ' ||e.empLname from HrPayGpfBalanceDtls gpf,OrgEmpMst e where gpf.userId  = e.orgUserMst.userId and e.cmnLanguageMst.langId = 1 ";
            if(empID!=0)
            	query+=" and e.empId="+empID;	
                query+=" order by e.empFname,e.empMname,e.empLname ";	
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        
        return GpfDetailsList;
    }
	
	public List getAllGpfDetailsbyUserId(long userID)
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
            String query = " select gpf.gpfAccNo,gpf.userId,e.empFname|| ' ' ||e.empMname|| ' ' ||e.empLname,gpf.orgGradeMst.gradeId from HrPayGpfBalanceDtls gpf,OrgEmpMst e where gpf.userId  = e.orgUserMst.userId and e.cmnLanguageMst.langId = 1 ";
            	query+=" and e.orgUserMst.userId="+userID;	
                query+=" order by e.empFname,e.empMname,e.empLname ";	
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        return GpfDetailsList;
    }
	
	public List getAllGpfDetails( )
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
            String query = " select gpf.gpfAccNo,e.empId from HrPayGpfBalanceDtls gpf,OrgEmpMst e where gpf.userId  = e.orgUserMst.userId and e.cmnLanguageMst.langId = 1 ";
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        return GpfDetailsList;
    }
	
	public List chkUniqueGPFAcct(long userGpfId,String gpfAcctNo )
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
            String query = " select gpf.gpfAccNo from HrPayGpfBalanceDtls gpf where gpf.userId  != '"+userGpfId+"' and gpf.gpfAccNo='"+gpfAcctNo+"'";
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        return GpfDetailsList;
    }
	public List chkUniqueGPFAcct(long userGpfId)
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
            String query = " select gpf.gpfAccNo from HrPayGpfBalanceDtls gpf where gpf.userId  = '"+userGpfId+"'";
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        return GpfDetailsList;
    }
	
	public List chkUniqueGPFAcct(String gpfAcctNo , String chk,long userId) //Changed By Varun For GPF A/C Form OrgEmpMst Dt.02-08-2008
    {
        List  GpfDetailsList = null;
        String query = "";
        Criteria objCrt = null;
            Session hibSession = getSession();
            if(chk.equals("2")){
            	 query = " select gpf.gpfAccNo from HrPayGpfBalanceDtls gpf where lower(gpf.gpfAccNo)  = lower('"+gpfAcctNo+"') and gpf.userId!=" + userId;	
            }
            else
            {
            	logger.info("Inside else chk"+chk);
            	query = " select gpf.empGPFnumber from OrgEmpMst gpf where lower(gpf.empGPFnumber)  = lower('"+gpfAcctNo+"')";
            }
            
           logger.info("the query is "+query);
            
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        return GpfDetailsList;
    }

	public List getAllGpfDetailsbyUserId(long userId,long locationId,long langId)
    {
        List  GpfDetailsList = null;
        Criteria objCrt = null;
            Session hibSession = getSession();
            String query = " select gpf.gpfAccNo,gpf.userId,e.empFname|| ' ' ||e.empMname|| ' ' ||e.empLname,gpf.orgGradeMst.gradeId from HrPayGpfBalanceDtls gpf,OrgEmpMst e where gpf.userId  = e.orgUserMst.userId and e.cmnLanguageMst.langId = "+langId + " and e.empSrvcExp >= "+ sdate + " ";
            if(locationId!=0)//location specific
            {  
            	query+="and gpf.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId=up.orgPostMstByPostId.postId and ";
            	query+="pd.cmnLocationMst.locId in (select c.locId from CmnLocationMst c where c.locId="+locationId+" or c.parentLocId="+locationId+"))";
            /*query+=" and gpf.userId in (select up.orgUserMst.userId from OrgUserpostRlt up,OrgPostDetailsRlt pd where pd.orgPostMst.postId = up.orgPostMstByPostId.postId  and pd.cmnLocationMst.locId in ";
            query+=" ( select  pd1.cmnLocationMst.locId  from OrgUserpostRlt up1,OrgPostDetailsRlt pd1 where pd1.orgPostMst.postId = up1.orgPostMstByPostId.postId  and up1.orgUserMst.userId = "+userId+" ) ) ";	
*/            }
            if(userId!=0)
            	query+=" and gpf.userId="+userId;	
                query+=" order by e.empFname,e.empMname,e.empLname ";	
                logger.info("**************the query is "+query);
                logger.info("**************the query is "+query);
            Query sqlQuery = hibSession.createQuery(query);
            GpfDetailsList = sqlQuery.list();
        
        return GpfDetailsList;
    }
}
