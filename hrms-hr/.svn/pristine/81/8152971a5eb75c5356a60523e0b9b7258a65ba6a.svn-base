package com.tcs.sgv.hr.payincrement.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.fms.valueobject.WfDocMst;
import com.tcs.sgv.fms.valueobject.WfHierachyPostMpg;
import com.tcs.sgv.fms.valueobject.WfHierarchyReferenceMst;
import com.tcs.sgv.fms.valueobject.WfOrgPostMpgMst;
import com.tcs.sgv.hr.payfixation.valueobject.HrPayFixTxn;
import com.tcs.sgv.hr.payincrement.valueobject.HrEisPayInc;
import com.tcs.sgv.hr.payincrement.valueobject.HrPayincTxn;
import com.tcs.sgv.hr.payincrement.valueobject.HstHrEisPayInc;

/**
 * @author 218914  pranav
 * @author 218914 pranav
 *dao has been modified to be used for the current application
 *all the maethods have been created to search through the tables.
 *
 */
public class PayIncDAOImpl extends GenericDaoHibernateImpl implements PayIncDAO {

	
	public PayIncDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/*method have been modified to be used with orgdesigmst table*/
	

	/*method to search with date as the input from user jsp*/
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#displaydetails4(java.lang.String, java.lang.String)
	 */
	public List displaydetails4(String startdate, String Enddate) {
		Date Date1 = null;
		Date Date2 = null;
		String Datee1 = startdate;
		String Datee2 = Enddate;
		
		try {
			Date1 = StringUtility.convertStringToDate(Datee1);
			Date2 = StringUtility.convertStringToDate(Datee2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Criteria crit = null;
		Criteria critpending = null;

		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisPayInc.class);
		critpending = hibSession.createCriteria(HrPayincTxn.class);
		
	    critpending.add(Restrictions.ne("approvalFlag", "A"));
	    critpending.add(Restrictions.ne("approvalFlag", "D"));
	
		List Search_restrcn = critpending.list();

		
		for (Iterator it = Search_restrcn.iterator(); it.hasNext();) {
			HrPayincTxn Search_restrcnLst = (HrPayincTxn) it.next();
			
			crit.add(Restrictions.ne("userId", Search_restrcnLst.getOrgUserMst().getUserId()));
			
		}
		crit.add(Restrictions.between("nextIncDt", Date1, Date2));
		List Increment = crit.list();
		

		for (Iterator it = Increment.iterator(); it.hasNext();)

		{
			HrEisPayInc eisPayInc = (HrEisPayInc) it.next();
		
			//List Increment =crit.list();
		   
		    		List payfix = new ArrayList();
			//Session hibSession = getSession();
			Date nxtdate = eisPayInc.getNextIncDt();
			String[] month = { "January", "February", "March", "April", "May",
					"June", "July", "August", "September", "October",
					"November", "December" };
			String date = nxtdate.getDate() + "/" + month[nxtdate.getMonth()]
					+ "/" + (nxtdate.getYear() + 1900);
			String hql = "from  HrPayFixTxn where userId.userId="
					+ eisPayInc.getUserId() + "and incrDate='" + date + "'";

			Query myQuery = hibSession.createQuery(hql);
			payfix = myQuery.list();
		
			if (payfix.size() != 0) {
				HrPayFixTxn trans = (HrPayFixTxn) payfix.get(0);

				long payfixempid = trans.getUserId().getUserId();
				crit.add(Restrictions.ne("userId", payfixempid));
			}
		}

		//crit.add(Restrictions.ne("empId", empid));
		return crit.list();
	}

	/*method to search with date as the input from user jsp along with designation*/
	/*public List displaydetails5(String startdate, String Enddate,
			String Designation) {
		// GeneralEmpInfoVO generalEmpInfoVO = new GeneralEmpInfoVO();
		Date Date1 = null;
		Date Date2 = null;
		String Datee1 = startdate;
		String Datee2 = Enddate;
		long Desigid = Long.parseLong(Designation);

		try {
			Date1 = StringUtility.convertStringToDate(Datee1);
			Date2 = StringUtility.convertStringToDate(Datee2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisPayInc.class);
		crit.add(Restrictions.between("nextIncDt", Date1, Date2));
		crit.add(Restrictions.eq("orgDesignationMst.dsgnId", Desigid));

		List increments = crit.list();

		System.out.println("size of list " + increments.size());
		for (Iterator it = increments.iterator(); it.hasNext();) {
			HrEisPayInc increment = (HrEisPayInc) it.next();
			System.out.println("LWP: " + increment.getLwp());
			System.out.println("DiffFlag: "
					+ increment.getOrgEmpMst().getEmpId());
			System.out.println("LastIncDt: " + increment.getLastIncDt());

		}
		return crit.list();

	}*/

	/*method to search payscale of corresponding employee */
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#Payscale(long)
	 */
	public List Payscale(long scaleid) {

		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisScaleMst.class);
		crit.add(Restrictions.eq("scaleId", scaleid));

		List transaction = crit.list();
		
		for (Iterator it = transaction.iterator(); it.hasNext();) {
			HrEisScaleMst transactiondtls = (HrEisScaleMst) it.next();

		}
		return crit.list();

	}

	/*to get data from transaction table with given request id*/
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#displaydetails6(long)
	 */
	public List displaydetails6(long Userid) {
		
		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisPayInc.class);
		crit.add(Restrictions.eq("userId", Userid));

		List transaction = crit.list();
		
		for (Iterator it = transaction.iterator(); it.hasNext();) {
			HrEisPayInc transactiondtls = (HrEisPayInc) it.next();
        
		}
		return crit.list();

	}

	/*method to search basic salary*/
	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#displaydetails8(long)
	 */
	public List displaydetails8(long empid) {

		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisOtherDtls.class);
		crit.add(Restrictions.eq("hrEisEmpMst.empId", empid));

		List transaction = crit.list();
		
		for (Iterator it = transaction.iterator(); it.hasNext();) {
			HrEisOtherDtls transactiondtls = (HrEisOtherDtls) it.next();

		}

		return crit.list();
	}

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#displaydetails11(long)
	 */
	public List displaydetails11(long empid) {

		Criteria crit = null;
		Session hibSession = getSession();
		crit = hibSession.createCriteria(HrEisOtherDtls.class);
		crit.add(Restrictions.eq("hrEisEmpMst.empId", empid));

		List transaction = crit.list();
	
		for (Iterator it = transaction.iterator(); it.hasNext();) {
			HrEisOtherDtls transactiondtls = (HrEisOtherDtls) it.next();

		}

		return crit.list();
	}

	/*public void UpdateMst(HrEisPayInc eisPayInc)

	{

		long incamt = 0;
		Session hibSession = getSession();
		if ((eisPayInc.getDeffdFlag()).equalsIgnoreCase("D")) {
			String hql = "update HrEisPayInc  set nextIncDt =:newName,remarks=:new,deffdFlag =:flag,lwp =:newlwp,trnCounter=:trnc where userId =:userId";
			Query query = hibSession.createQuery(hql);
			query.setDate("newName", eisPayInc.getNextIncDt());
			query.setString("new", eisPayInc.getRemarks());
			query.setString("flag", eisPayInc.getDeffdFlag());
			query.setLong("userId", eisPayInc.getUserId());
			query.setLong("newlwp", eisPayInc.getLwp());
			query.setInteger("trnc", 1);
			try {
				System.out.println("row updated ::: " + query.executeUpdate());

			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {

			if (eisPayInc.getDeffdFlag().equalsIgnoreCase("N")) {

				String hql = "update HrEisPayInc set nextIncDt =:newName,lastIncDt=:lastincdate,remarks=:new,basicSal=:newbasic,lwp =:newlwp,incAmt=:incamt,trnCounter=:trnc where userId =:userId ";
				Query query = hibSession.createQuery(hql);
				query.setDate("newName", eisPayInc.getNextIncDt());
				query.setString("new", eisPayInc.getRemarks());
				query.setLong("newbasic", eisPayInc.getBasicSal());
				query.setDate("lastincdate", eisPayInc.getLastIncDt());
				System.out.println("lwp    --------------->>>usercomp "
						+ eisPayInc.getLwp());
				query.setLong("newlwp", eisPayInc.getLwp());
				query.setLong("userId", eisPayInc.getUserId());
				query.setInteger("trnc", 1);
				String hql1 = "from  HrModEmpRlt where empId=('"
						+ eisPayInc.getUserId() + "')";
				List payfix = new ArrayList();
				Query myQuery = hibSession.createQuery(hql1);
				payfix = myQuery.list();
				if (payfix.size() != 0) {
					HrModEmpRlt modrlt = (HrModEmpRlt) payfix.get(0);
					incamt = modrlt.getScaleId().getScaleIncrAmt();

				}

				query.setLong("incamt", incamt);

				int rowCount = query.executeUpdate();

			}

			else {

				String hql = "update HrEisPayInc set nextIncDt =:newName,lastIncDt=:lastincdate,basicSal =:newbasic,incAmt=:incamt,trnCounter=:trnc where userId =:userId";
				Query query = hibSession.createQuery(hql);
				query.setDate("newName", eisPayInc.getNextIncDt());
				query.setLong("newbasic", eisPayInc.getBasicSal());
				query.setLong("userId", eisPayInc.getUserId());
				query.setDate("lastincdate", eisPayInc.getLastIncDt());
				query.setInteger("trnc", 1);
				String hql1 = "from  HrModEmpRlt where empId=('"
						+ eisPayInc.getUserId() + "')";
				List payfix = new ArrayList();
				Query myQuery = hibSession.createQuery(hql1);
				payfix = myQuery.list();
				if (payfix.size() != 0) {
					HrModEmpRlt modrlt = (HrModEmpRlt) payfix.get(0);
					incamt = modrlt.getScaleId().getScaleIncrAmt();

				}

				query.setLong("incamt", incamt);
				int rowCount = query.executeUpdate();

			}
		}

	}*/

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#UpdateTran(com.tcs.sgv.hr.payincrement.valueobject.HrEisPayincTran)
	 */
	public void UpdateTran(HrPayincTxn eispayintran)

	{

		Session hibSession = getSession();

		if ((eispayintran.getCompFlag()).equalsIgnoreCase("Deferred")) {
			
			String hql = "update HrPayincTxn set defferedDate =:newName,remarks=:new,lwp=:newlwp,approvalFlag=:aflag,compFlag=:cflag where reqTranId =:reqid ";
			Query query = hibSession.createQuery(hql);
			query.setDate("newName", eispayintran.getDefferedDate());
			query.setString("new", eispayintran.getRemarks());
			query.setString("aflag", eispayintran.getApprovalFlag());
			query.setString("cflag", eispayintran.getCompFlag());
			
			query.setLong("newlwp", eispayintran.getLwp());
			query.setLong("reqid", eispayintran.getReqTranId());
			query.executeUpdate();
			
		} 
		else {

			if ((eispayintran.getCompFlag()).equalsIgnoreCase("UserComp")) {

				String hql = "update HrPayincTxn set userComNxtincDate =:newName,lastIncDate=:lstincdate,remarks=:new,lwp=:newlwp,approvalFlag=:aflag,compFlag=:cflag,userComSalary=:sal where reqTranId =:reqid ";
				Query query = hibSession.createQuery(hql);
				query.setDate("newName", eispayintran.getUserComNxtincDate());
				query.setString("new", eispayintran.getRemarks());
				query.setLong("newlwp", eispayintran.getLwp());
				query.setString("aflag", eispayintran.getApprovalFlag());
				query.setString("cflag", eispayintran.getCompFlag());
				query.setDate("lstincdate", eispayintran.getLastIncDate());
				query.setLong("sal", eispayintran.getUserComSalary());
				query.setLong("reqid", eispayintran.getReqTranId());
				query.executeUpdate();

			}

			else {
				String hql = "update HrPayincTxn set sysComNxtincDate =:newName,lastIncDate=:lstincdate,sysComSalary =:newbasic,approvalFlag=:aflag,compFlag=:cflag where reqTranId =:reqid";
				Query query = hibSession.createQuery(hql);
				query.setDate("newName", eispayintran.getSysComNxtincDate());
				query.setLong("newbasic", eispayintran.getSysComSalary());
				query.setLong("reqid", eispayintran.getReqTranId());
				query.setString("aflag", eispayintran.getApprovalFlag());
				query.setDate("lstincdate", eispayintran.getLastIncDate());
				query.setString("cflag", eispayintran.getCompFlag());
			    query.executeUpdate();

			}

		}

	}

	/*public long incamt(Long reqid) {
		long incamt = 0;
		Session hibSession = getSession();
		String hql1 = "from  HrModEmpRlt where reqId=('" + reqid
				+ "')";
		List payfix = new ArrayList();
		Query myQuery = hibSession.createQuery(hql1);
		payfix = myQuery.list();
		if (payfix.size() != 0) {
			HrModEmpRlt modrlt = (HrModEmpRlt) payfix.get(0);
			incamt = modrlt.getScaleId().getScaleIncrAmt();

		}

		return incamt;
	}*/
	 /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#incamt(long)
	 */
	/*public long incamt(long reqId) {
		long incamt = 0;
		List incamtdtl = new ArrayList();
		Session hibSession = getSession();

		Criteria hrmainCrit = hibSession
				.createCriteria(HrModEmpRlt.class);
		hrmainCrit.add(Restrictions.eq("reqId", reqId));
		hrmainCrit.add(Restrictions.eq("hrModMst.modId",8730l));
		incamtdtl = hrmainCrit.list();
		HrModEmpRlt HrModEmpRlt =(HrModEmpRlt)incamtdtl.get(0);
	    incamt=	basic-HrModEmpRlt.getBasicSal();

		return incamt;

	}*/

	/* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#getdata(long)
	 */
	public HrEisPayInc getdata(long empid) {
		HrEisPayInc eisPayInc = new HrEisPayInc();
		Session hibSession = getSession();
		String query1 = "from HrEisPayInc where empId = " + empid + " ) ";
		Query sqlQuery1 = hibSession.createQuery(query1);
		eisPayInc = (HrEisPayInc) sqlQuery1.uniqueResult();

		return eisPayInc;
	}
      /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#status(long)
	 */
    public HrPayincTxn status(long reqid){
	
    	List result = new ArrayList();
		//HrEisPayincTran hrEisPayincTran =new HrEisPayincTran();
		Session hibSession = getSession();
		String query1 = "from HrPayincTxn where reqTranId = " + reqid + " ) ";
		Query sqlQuery1 = hibSession.createQuery(query1);
		result =sqlQuery1.list();
		HrPayincTxn payincTran =(HrPayincTxn)result.get(0);
	    String  flag=payincTran.getApprovalFlag();
	  
	   return payincTran;
     }
      
      /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#modstatus(long)
	 */
    public String modstatus(long reqid){
    		
      	List result = new ArrayList();
  		//HrEisPayincTran hrEisPayincTran =new HrEisPayincTran();
  		Session hibSession = getSession();
  		String query1 = "from HrPayincTxn where reqTranId = " + reqid + " ) ";
  		Query sqlQuery1 = hibSession.createQuery(query1);
  		result =sqlQuery1.list();
  		HrPayincTxn payincTran =(HrPayincTxn)result.get(0);
  	    String  comflag=payincTran.getCompFlag();
  	 
  	   return comflag;
       }
     
      /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayIncDAO#history(long)
	 */
    public List history(long empid) {
  		
  		

  		Criteria crit = null;
  		
  		Session hibSession = getSession();
  		crit = hibSession.createCriteria(HstHrEisPayInc.class);
  		

  		crit.add(Restrictions.eq("id.empId", empid));
  		
  		List Search_restrcn = crit.list();

  		
  		for (Iterator it = Search_restrcn.iterator(); it.hasNext();) {
  			HstHrEisPayInc Search_restrcnLst = (HstHrEisPayInc) it.next();
  		
  		}
  		
  		
  		return Search_restrcn;

}

	public long incamt(long reqid) {
		long incamt = 0;
		Criteria crit = null;
		long modid=8730;
		Session hibSession = getSession();
        crit = hibSession.createCriteria(HrModEmpRlt.class);
  		crit.add(Restrictions.eq("reqId", reqid));
  		crit.add(Restrictions.eq("hrModMst.modId", modid));
  		List Search_incamt = crit.list();
  		HrModEmpRlt HrModEmpRlt=(HrModEmpRlt)Search_incamt.get(0);
  	    incamt=	HrModEmpRlt.getScaleId().getScaleIncrAmt();

		return incamt;
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
	
	}
  


