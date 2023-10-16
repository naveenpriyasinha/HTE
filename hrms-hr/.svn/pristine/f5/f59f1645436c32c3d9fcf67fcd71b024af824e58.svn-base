package com.tcs.sgv.hr.payfixation.dao;

//package com.tcs.sgv.ess.hr.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.fms.valueobject.WfDocMst;
import com.tcs.sgv.fms.valueobject.WfHierachyPostMpg;
import com.tcs.sgv.fms.valueobject.WfHierarchyReferenceMst;
import com.tcs.sgv.fms.valueobject.WfOrgPostMpgMst;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayFixTxn;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayfixReqDtl;
import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;
/**
 * @author 217977
 *
 */
public class PayFixationDaoImpl extends GenericDaoHibernateImpl implements PayFixationDao 
{
	public PayFixationDaoImpl (Class type, SessionFactory sessionFactory)
	{
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Scale(long, long)
	 */
	public List Scale(long empId,long langId)
    {
 			 			 		
 		  List emppay = new ArrayList();
 		 List emppay1 = new ArrayList();
 		 List emppay2 = new ArrayList();
 		Session hibSession = getSession();	      		        	
        String query1="from HrEisOtherDtls as emp where emp.hrEisEmpMst like "+empId;
        				
        Query sqlQuery1 = hibSession.createQuery(query1);
    
        emppay = sqlQuery1.list();
        HrEisOtherDtls EmpMaster=new HrEisOtherDtls();
        EmpMaster=(HrEisOtherDtls)emppay.get(0);
        HrEisSgdMpg sgd= EmpMaster.getHrEisSgdMpg();
        long sgdMapId=sgd.getSgdMapId();
        String query2="from HrEisSgdMpg as cus where cus.sgdMapId like "+sgdMapId;
        Query sqlQuery2 = hibSession.createQuery(query2);
        emppay1 = sqlQuery2.list();
        HrEisSgdMpg EmpMaster1=new HrEisSgdMpg();
        EmpMaster1=(HrEisSgdMpg)emppay1.get(0);
        HrEisScaleMst sgd1= EmpMaster1.getHrEisScaleMst();
        long scaleId=sgd1.getScaleId();
        String query3="from HrEisScaleMst as cust where cust.scaleId like "+scaleId;
        Query sqlQuery3 = hibSession.createQuery(query3);
        emppay2 = sqlQuery3.list();
        return emppay2;
       
        
    }
	/*public List Designation(long empId,long langId)
    {
 			 			 		
 		  List emppay = new ArrayList();
 		 List emppay1 = new ArrayList();
 		 List emppay2 = new ArrayList();
 		Session hibSession = getSession();	      		        	
        String query1="from HrEisOtherDtls as emp where emp.hrEisEmpMst like "+empId;
        				
        Query sqlQuery1 = hibSession.createQuery(query1);
    
        emppay = sqlQuery1.list();
        HrEisOtherDtls EmpMaster=new HrEisOtherDtls();
        EmpMaster=(HrEisOtherDtls)emppay.get(0);
        HrEisSgdMpg sgd= EmpMaster.getHrEisSgdMpg();
        long sgdMapId=sgd.getSgdMapId();
        String query2="from HrEisSgdMpg as cus where cus.sgdMapId like "+sgdMapId;
        Query sqlQuery2 = hibSession.createQuery(query2);
        emppay1 = sqlQuery2.list(); 
        HrEisSgdMpg EmpMaster1=new HrEisSgdMpg();
        EmpMaster1=(HrEisSgdMpg)emppay1.get(0);
        HrEisGdMpg mpg=EmpMaster1.getHrEisGdMpg();
        HrEisDesigMst desi=mpg.getHrEisDesigMst();
        long desigId=desi.getDesigId();
        String query3="from HrEisDesigMst as des where des.desigId like "+desigId;
        Query sqlQuery3 = hibSession.createQuery(query3);
        
        emppay2 = sqlQuery3.list();
        return emppay2;
}
	*/
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#SearchByPayFixDate(java.lang.String, java.lang.String)
	 */
	
	public List SearchByPayFixDate(String startdate,String enddate)
    {
 			 		 		
 		  List payfixsearch = new ArrayList();
 		Session hibSession = getSession();	      		        	
       String query1="from HrPayFixTxn as tran where (tran.payFixDate between '"+startdate+"' and '"+enddate+"') and (tran.emplResp='y') and (tran.userId.userId not in (select userId.userId from HrPayfixReqDtl where approve='p')) and (tran.payFixId not in (select hrPayFixTxn.payFixId from HrPayfixReqDtl))"; 
       Query sqlQuery1 = hibSession.createQuery(query1);			
       payfixsearch = sqlQuery1.list();
       return payfixsearch;  
    }
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#SearchByIncrementDate(java.lang.String, java.lang.String)
	 */
	public List SearchByIncrementDate(String startdate,String enddate)
    {
 		 		
 		List payfixsearch = new ArrayList();
 		Session hibSession = getSession();	      		        	
 		String query1="from HrPayFixTxn as tran where (tran.incrDate between '"+startdate+"' and '"+enddate+"') and (tran.emplResp='y') and (tran.payFixationAtIncr='y') and (tran.userId.userId not in (select userId.userId from HrPayfixReqDtl where approve='p')) and (tran.payFixId not in (select hrPayFixTxn.payFixId from HrPayfixReqDtl where reqType='2'))";  
 		Query sqlQuery1 = hibSession.createQuery(query1);
 		payfixsearch = sqlQuery1.list();
 		 return payfixsearch;	
         		
    }
	/**to obtain details from HrEssTranPayFix where payfixdate between startdate and enddate
	 * @param startdate
	 * @param enddate
	 * @return
	 */
/*	public List SearchByPayFixDatewithoutdesig(String startdate,String enddate)
    {
 			 		System.out.println("into dao+++");	 		
 		  List payfixsearch = new ArrayList();
 		Session hibSession = getSession();	      		        	
       String query1="from HrEssTranPayFix as tran where (tran.payFixDate between '"+startdate+"' and '"+enddate+"')"; 
        				
        Query sqlQuery1 = hibSession.createQuery(query1);
    
        payfixsearch = sqlQuery1.list();
        
        return payfixsearch;
       
    }
	/**
	 * to obtain details from HrEssTranPayFix where incrementdate between startdate and enddate
	 * @param startdate
	 * @param enddate
	 * @return
	 */
/*	public List SearchByIncrementDatewithoutdesignation(String startdate,String enddate)
    {
 			 		System.out.println("into dao+++");	 		
 		  List payfixsearch = new ArrayList();
 		Session hibSession = getSession();	      		        	
       String query1="from HrEssTranPayFix as tran where tran.payFixationAtIncr='y'and tran.orgEmpMst in(select empId from HrEisPayInc where nextIncDt between '"+startdate+"' and '"+enddate+"'))"; 
        				
        Query sqlQuery1 = hibSession.createQuery(query1);
    
        payfixsearch = sqlQuery1.list();
        
        return payfixsearch;
      
    }*/
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Fixation(long, long)
	 */
	public List Fixation(long userID, long langId) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(HrPayFixTxn.class);
		hrmainCrit.add(Restrictions.eq("userId.userId", userID));
		hrmainCrit.add(Restrictions.eq("emplResp", "n"));
		hrmainCrit.addOrder(Order.asc("createdDate"));
		fixdetail = hrmainCrit.list();
		return fixdetail;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#ReasonPayFix(long)
	 */
	public List ReasonPayFix(long reaspayfixid) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(CmnLookupMst.class);
		hrmainCrit.add(Restrictions.eq("lookupId", reaspayfixid));

		fixdetail = hrmainCrit.list();
		return fixdetail;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Amount(long)
	 */
	public List Amount(long revisedscaleid) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(HrEisScaleMst.class);
		hrmainCrit.add(Restrictions.eq("scaleId", revisedscaleid));

		fixdetail = hrmainCrit.list();
		return fixdetail;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#UpdateRulepayincrdate(long)
	 */
	public void UpdateRulepayincrdate(long payfixid) {
		Session hibSession = getSession();

		
		String hql = "update HrPayFixTxn set payFixation =:newName,payFixationAtIncr=:new,emplResp=:new1 where payFixId =:name";
		Query query = hibSession.createQuery(hql);
		query.setLong("name", payfixid);
		query.setString("new", "y");
		query.setString("newName", "n");
		query.setString("new1", "y");
		int rowCount = query.executeUpdate();
		

	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#UpdateRulepayfixdate(long)
	 */
	public void UpdateRulepayfixdate(long payfixid) {

		Session hibSession = getSession();

		
		String hql = "update HrPayFixTxn set payFixation =:newName,payFixationAtIncr=:new,emplResp=:new1 where payFixId =:name";
		Query query = hibSession.createQuery(hql);
		query.setLong("name", payfixid);
		query.setString("new", "n");
		query.setString("newName", "y");
		query.setString("new1", "y");
		int rowCount = query.executeUpdate();
		

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#getAllDesgMasterData()
	 */
	public List getAllDesgMasterData() {
		List desig_mst = new ArrayList();
		Session hibSession = getSession();
		String hql = "from OrgDesignationMst";
		Query myQuery = hibSession.createQuery(hql);
		desig_mst = myQuery.list();
		return desig_mst;
	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getalldatafromhrtrans(long)
	 */
	public List Getalldatafromhrtrans(long payFixId) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(HrPayFixTxn.class);
		hrmainCrit.add(Restrictions.eq("payFixId", payFixId));

		fixdetail = hrmainCrit.list();
		return fixdetail;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getalldatafromhrpayreqdtls(long)
	 */
	public List Getalldatafromhrpayreqdtls(long reqId) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession
				.createCriteria(HrPayfixReqDtl.class);
		hrmainCrit.add(Restrictions.eq("reqId", reqId));

		fixdetail = hrmainCrit.list();
		return fixdetail;

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getalldatafromhrpayincr(long)
	 */
	public List Getalldatafromhrpayincr(long userid) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(HrEisPayInc.class);
		hrmainCrit.add(Restrictions.eq("userId", userid));

		fixdetail = hrmainCrit.list();
		return fixdetail;

	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Updateapprovalsyscomp(long)
	 */

	public void Updateapprovalcomp(long reqid) {

		Session hibSession = getSession();

	
		String hql = "update HrPayfixReqDtl set approve =:newName where reqId =:name";
		Query query = hibSession.createQuery(hql);
		query.setLong("name", reqid);
		
		query.setString("newName", "a");
	
		int rowCount = query.executeUpdate();
		

	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Updateapprovalusercomp(long, java.util.Date, java.lang.String, long)
	 */
	public void Updateapprovalusercomp(long payfixed, Date nxtdate,
			String expl, long reqid) {

		Session hibSession = getSession();

		
		String hql = "update HrPayfixReqDtl set userCompNewBasicSal =:newName,userCompNxtIncrDate =:name1,userRemarks =:name2,compFlag= :name3,approve =:name4 where reqId =:name";
		Query query = hibSession.createQuery(hql);
		query.setLong("newName", payfixed);
		//query.setString("new","y");
		query.setLong("name", reqid);
		query.setDate("name1", nxtdate);
		query.setString("name2", expl);
		query.setString("name3", "u");
		query.setString("name4", "a");
		//query.setString("new1","y");
		int rowCount = query.executeUpdate();
		

	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#UpdateIncrdateinHrPayIncr(long, java.util.Date)
	 */
	
	public void UpdateIncrdateinHrPayIncr(long userId, Date incrementdate) {

		Session hibSession = getSession();

		
		String hql = "update HrEisPayInc set nextIncDt =:incrdate where userId =:userid";
		Query query = hibSession.createQuery(hql);
		query.setLong("userid", userId);
		query.setDate("incrdate", incrementdate);

		int rowCount = query.executeUpdate();
		

	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Resultwhetherempexists(long)
	 */
	
	public List Resultwhetherempexists(long userid)
	{
		
		
	 			 		 		
	 		  List payfixsearch = new ArrayList();
	 		Session hibSession = getSession();	  
	 		OrgUserMst userId=new OrgUserMst();
	 		userId.setUserId(userid);
	 		Criteria hrmainCrit = hibSession.createCriteria(HrPayfixMst.class);
	 			hrmainCrit.add(Restrictions.eq("userId", userId));
	hrmainCrit.add(Restrictions.eq("flagType","Y"));	
	       payfixsearch = hrmainCrit.list();
	       
	       return payfixsearch;
		
	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Updatereject(long)
	 */
	public void Updatereject(long reqid) {

		Session hibSession = getSession();

		
		String hql = "update HrPayfixReqDtl set approve =:newName where reqId =:name";
		Query query = hibSession.createQuery(hql);
		query.setLong("name", reqid);
		//query.setString("new","y");
		query.setString("newName", "r");
		//query.setString("new1","y");
		int rowCount = query.executeUpdate();
		

	}
	
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Updateforwardusercomp(long, java.util.Date, java.lang.String, long)
	 */
	public void Updateforwardusercomp(long payfixed, Date nxtdate,
			String expl, long reqid) {

		Session hibSession = getSession();

		
		String hql = "update HrPayfixReqDtl set userCompNewBasicSal =:newName,userCompNxtIncrDate =:name1,userRemarks =:name2,compFlag= :name3 where reqId =:name";
		Query query = hibSession.createQuery(hql);
		query.setLong("newName", payfixed);
		
		query.setLong("name", reqid);
		query.setDate("name1", nxtdate);
		query.setString("name2", expl);
		query.setString("name3", "u");
		
		int rowCount = query.executeUpdate();
		

	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#getScaleName(long)
	 */
	public List getScaleName(long scalestrtamt)
    {
 		List hrscaleInfo = null;
        try
        {
           
        	Session hibSession = getSession();
            String query1 = "from HrEisScaleMst where scaleStartAmt>"+scalestrtamt;
            Query sqlQuery1 = hibSession.createQuery(query1);
            hrscaleInfo = sqlQuery1.list();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hrscaleInfo;
    }
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getalldatafromorgemp(long)
	 */
	public long Getalldatafromorgemp(long empId) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(OrgEmpMst.class);
		hrmainCrit.add(Restrictions.eq("empId", empId));

		fixdetail = hrmainCrit.list();
		
		OrgEmpMst org=(OrgEmpMst)fixdetail.get(0);
		long userid=org.getOrgUserMst().getUserId();
		long langid=org.getCmnLanguageMst().getLangId();
		if(langid==2)
		{List fixdetail1 = new ArrayList();
		
			Criteria hrmainCrit1 = hibSession.createCriteria(OrgEmpMst.class);
			hrmainCrit1.add(Restrictions.eq("orgUserMst.userId", userid));
			hrmainCrit1.add(Restrictions.eq("cmnLanguageMst.langId",1l));
			fixdetail1=hrmainCrit1.list();
			OrgEmpMst org1=(OrgEmpMst)fixdetail1.get(0);
			long empid=org1.getEmpId();
			
			return empid;
		}
		else
		{
			long empid=org.getEmpId();
			return empid;
		}
		}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getorgempbyuserid(long, long)
	 */
	public List Getorgempbyuserid(long userId, long langId) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(OrgEmpMst.class);
		hrmainCrit.add(Restrictions.eq("orgUserMst.userId", userId));
		hrmainCrit.add(Restrictions.eq("cmnLanguageMst.langId",langId ));
		fixdetail = hrmainCrit.list();
		return fixdetail;

	}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getgujemp(long)
	 */
	public long Getgujemp(long userid) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession.createCriteria(OrgEmpMst.class);
		hrmainCrit.add(Restrictions.eq("orgUserMst.userId", userid));
		hrmainCrit.add(Restrictions.eq("cmnLanguageMst.langId", 2l));
		fixdetail = hrmainCrit.list();
		
		OrgEmpMst org=(OrgEmpMst)fixdetail.get(0);
		
		 return org.getEmpId();
		
		
	
		}
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payfixation.dao.PayFixationDao#Getalldatafromhrmodemprlt(long)
	 */
	public List Getalldatafromhrmodemprlt(long reqId) {

		List fixdetail = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession
				.createCriteria(HrModEmpRlt.class);
		hrmainCrit.add(Restrictions.eq("reqId", reqId));
		hrmainCrit.add(Restrictions.eq("hrModMst.modId",1000l));
		fixdetail = hrmainCrit.list();
		return fixdetail;

	}
	public long getHigherHierachyPostId(String postId, long docId)
	{
		long levelUpPostId=0;
			Session hibsession = getSession();	
			WfDocMst wfDocMst = new WfDocMst();
			wfDocMst.setDocId(docId);
			
			WfOrgPostMpgMst wfOrgPostMpgMst = new WfOrgPostMpgMst();
			wfOrgPostMpgMst.setPostId(postId);
			
							
			Criteria criteria1 = hibsession.createCriteria(WfHierarchyReferenceMst.class);
			criteria1.add(Restrictions.eq("wfDocMst",wfDocMst));
			
			List HierachyRefDtls = criteria1.list();
			if(HierachyRefDtls.size()!=0)
			{
			WfHierarchyReferenceMst wfHierarchyReferenceMst = (WfHierarchyReferenceMst) HierachyRefDtls.get(0);
			
			long wfHierachyRefId = wfHierarchyReferenceMst.getHierachyRefId();
			
			
			
			Criteria criteria =  hibsession.createCriteria(WfHierachyPostMpg.class);
			criteria.add(Restrictions.eq("wfOrgPostMpgMst",wfOrgPostMpgMst));
			criteria.add(Restrictions.eq("hierachyRefId",wfHierachyRefId));
			
			
			List hierachyList = criteria.list();
			if(hierachyList.size()!=0)
			{
			WfHierachyPostMpg wfHierachyPostMpg = (WfHierachyPostMpg)hierachyList.get(0);
			int levelId = wfHierachyPostMpg.getLevelId();
			
			
			Criteria criteria2 = hibsession.createCriteria(WfHierachyPostMpg.class);
			criteria2.add(Restrictions.eq("hierachyRefId",wfHierachyRefId));
			criteria2.add(Restrictions.gt("levelId", levelId));
			
			List hierachyList1 = criteria2.list();
			if(hierachyList1.size()!=0)
			{
			WfHierachyPostMpg wfHierachyPostMpg1 = (WfHierachyPostMpg)hierachyList1.get(0);
			 levelUpPostId = Long.parseLong(wfHierachyPostMpg1.getWfOrgPostMpgMst().getPostId());
			}
			}
			}
			return levelUpPostId;
		}	
	
	/* Added by Sandip - Starts. */
	public HrPayfixMst getPayFixListByUserIdandActiveFlag(long usreId,char flag)
	{
		Session hibSession = getSession();
		
		List lstPayFix = new ArrayList();
		HrPayfixMst hrPayfixMst = null;
		try
		{
			Criteria crit = hibSession.createCriteria(HrPayfixMst.class);
			crit.add(Restrictions.eq("userId.userId",usreId))
				.add(Restrictions.eq("flagType",flag));
			
			lstPayFix = crit.list();
			logger.info("payFix List Size======="+lstPayFix.size());
			
			if (lstPayFix != null && lstPayFix.size() > 0)
				hrPayfixMst = (HrPayfixMst)lstPayFix.get(0);
		}
		catch(Exception e){}
		return hrPayfixMst;
	}
	
	public List<HrPayfixMst> getPayFixDtlVOList(long userId) 
	{
		Session hibSession = getSession();
		List<HrPayfixMst> ExamDtlVOList = new ArrayList<HrPayfixMst>();
		try
		{
			logger.info("========= userId========="+ userId);
			Criteria crit = hibSession.createCriteria(HrPayfixMst.class);
			crit.add(Restrictions.eq("userId.userId", userId))
				.addOrder(Order.asc("payFixDate"));
			ExamDtlVOList = crit.list();
			logger.info("list in dao========="+ExamDtlVOList.size());
		}
		catch(Exception e){}
		return ExamDtlVOList;
	}
	
	public List<HrEisScaleMst> getPayScaleListByCrntScale(long startAmt) 
	{
		Session hibSession = getSession();
		List<HrEisScaleMst> payScaleList = new ArrayList<HrEisScaleMst>();
		try
		{
			logger.info("========= startAmt========="+ startAmt);
			Criteria crit = hibSession.createCriteria(HrEisScaleMst.class);
			crit.add(Restrictions.ge("scaleStartAmt", startAmt));
			payScaleList = crit.list();
			logger.info("list in dao========="+payScaleList.size());
		}
		catch(Exception e){}
		return payScaleList;
	}
	/* Added by Sandip - Ends. */
}
