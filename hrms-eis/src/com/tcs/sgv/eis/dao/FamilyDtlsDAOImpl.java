package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtl;
import com.tcs.sgv.eis.valueobject.HrEisFamilyDtlTxn;
import com.tcs.sgv.ess.valueobject.OrgDepartmentMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class FamilyDtlsDAOImpl extends GenericDaoHibernateImpl implements  FamilyDtlsDAO
{

	private static final Log logger = LogFactory.getLog(FamilyDtlsDAOImpl.class);
	@SuppressWarnings("unchecked")
	public FamilyDtlsDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getEmpFamilyDtls(OrgUserMst orgUserMst)
	{							
		Session hibSession = getSession();																									
		logger.info("getEmpFamilyDtls DAOImpl  called   " +orgUserMst);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("deleteFlag","N"))
			.addOrder(Order.desc("cmnLookupMstByFmDeadOrAlive.lookupId"))
			.addOrder(Order.asc("fmRelationOther"))
			.addOrder(Order.desc("fmDateOfBirth"))
			;				
		empFamilyDtlsListObj = crit.list();					
		return empFamilyDtlsListObj;				
	}
	public List getAliveEmpFamilyDtls(OrgUserMst orgUserMstObj)
	{							
		Session hibSession = getSession();																									
		logger.info("getAliveEmpFamilyDtls DAOImpl  called   " +orgUserMstObj);	
		List empFamilyDtlsLstObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMstObj))
			.add(Restrictions.like("deleteFlag","N"))
			.add(Restrictions.isNull("dateOfDemise"))
			.addOrder(Order.desc("fmDateOfBirth"));				
		empFamilyDtlsLstObj = crit.list();
		return empFamilyDtlsLstObj;				
	}

	public List getEmpMaxMemberID(OrgUserMst empID) {						
		Session hibSession = getSession();				
		logger.info("get Max Memeber ID  called  "+empID);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
		.setProjection(Projections.projectionList()
				.add(Projections.max("id.memberId"))); 
		empFamilyDtlsListObj = crit.list();		
		return empFamilyDtlsListObj;											
	}

	public List findRequestForthisRecord(long memberId, OrgUserMst empID) {		
		logger.info("findReuestForthisRecord  getEmpFamilyDtls  called  empid:  "+empID + "  member Id : " + memberId);	
		List familyListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))								
			.add(Restrictions.eq("rowNumber",memberId))
			//.add(Restrictions.ne("draftFlag","Y"))    // Remove this Field For Save As Drafr Link Show/Hide
			.add(Restrictions.like("actionFlag", "P"));
		familyListObj = crit.list();				
		return familyListObj;
	}

	public List getDrftDtlsLst(OrgUserMst empID) {
		logger.info("getEmpDraft Lst getEmpFamilyDtls  called   "+empID);	
		List draftDtlsLst=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
			.add(Restrictions.eq("draftFlag","Y"))
			.setProjection(Projections.projectionList()					
				.add(Projections.groupProperty("id.reqId"))
				.add(Projections.property("createdDate")))
				.addOrder(Order.asc("createdDate"));
		draftDtlsLst = crit.list();			
		return draftDtlsLst;
	}
	public List getEmpRequest(OrgUserMst empID) {
		logger.info("get Created Data and Req ID for Emp Family, Pending Request getEmpFamilyDtls  called   "+empID);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
			.add(Restrictions.like("actionFlag", "P"))
			.add(Restrictions.like("draftFlag", "N"))			
			.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.reqId"))
				.add(Projections.property("createdDate"))
				.add(Projections.groupProperty("corrsId")))
				.addOrder(Order.asc("createdDate"));
		empRequestListObj = crit.list();							
		return empRequestListObj;			
	}

	public List getEmpDraftRequest(OrgUserMst empID) {
		logger.info("get Created Data and Req ID for Emp Family, Pending Request getEmpFamilyDtls  called   "+empID);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
			.add(Restrictions.like("actionFlag", "P"))
			.add(Restrictions.like("draftFlag", "Y"))			
			.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("id.reqId"))
				.add(Projections.property("createdDate")))	
				.addOrder(Order.asc("createdDate"));
		empRequestListObj = crit.list();	
		logger.info("empRequestListObj :::::  "+ empRequestListObj.size());
		return empRequestListObj;
	}

	public List getFamilyPendingRecordForView(long reqId,OrgUserMst userId) {
		logger.info("get Created Data and Req ID for Emp Family, Pending Request getEmpFamilyDtls  called   "+userId);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", userId))
			.add(Restrictions.like("id.reqId", reqId))
			.add(Restrictions.like("actionFlag", "P"))
			.add(Restrictions.like("draftFlag", "N"))									
			.addOrder(Order.desc("createdDate"));
		empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List getFamilyDraftRecordForView(long reqId, OrgUserMst empID) {
		logger.info("get Created Data and Req ID for Emp Family, Draft Request getEmpFamilyDtls  called   "+empID);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", empID))
			.add(Restrictions.like("id.reqId", reqId))
			.add(Restrictions.like("actionFlag", "P"))
			.add(Restrictions.like("draftFlag", "Y"))									
			.addOrder(Order.desc("createdDate"));
		empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List getFamilyDtlsRecordForDelete(long delId, OrgUserMst orgEmpMst) {
		Session hibSession = getSession();				
		logger.info("get Max Memeber ID  called  "+delId);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.eq("id.empId", orgEmpMst))
			.add(Restrictions.ne("deleteFlag", "N"))
			.add(Restrictions.eq("id.memberId",delId));
		empFamilyDtlsListObj = crit.list();		
		return empFamilyDtlsListObj;
	}

	public List addDraftDtls(OrgUserMst orgUserMst, long request_ID) {
		logger.info("get Created Data and Req ID for Emp Family, Pending Request getEmpFamilyDtls  called   "+orgUserMst);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("id.reqId", request_ID))
			.add(Restrictions.like("actionFlag", "P"));																	
		empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List deletRequest(long memberId) {
		logger.info("sending a approved data for delete request ,FamilyDaoimpl Called  "+memberId);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("memberId", memberId));																	
		empRequestListObj = crit.list();							
		return empRequestListObj;
	}

	public List getTheFamilyMember(long userId,long familyMemberId) {
		Session hibSession = getSession();				
		logger.info("getTheFamilyMember, Memeber ID  :::   "+familyMemberId + "  user ID " + userId);	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId",userId))
			.add(Restrictions.eq("memberId",familyMemberId)); 
		empFamilyDtlsListObj = crit.list();		
		return empFamilyDtlsListObj;
	}

	public boolean chekDeleteRequest(long memberId, OrgUserMst orgUserMst) {
		logger.info("Family member is delete and set as save draft,chekDeleteRequest getEmpFamilyDtls DAO called   "+orgUserMst);			
		List empRequestListObj=new ArrayList();				
		Session hibSession = getSession();				
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtlTxn.class);
		crit.add(Restrictions.eq("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("id.memberId", memberId))
			.add(Restrictions.like("actionFlag", "P"))
			.add(Restrictions.like("requestFlag","D"));																	
		empRequestListObj = crit.list();
		return empRequestListObj.isEmpty();
	}

	public List getChild(OrgUserMst orgUserMst) {
		Session hibSession = getSession();	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId", orgUserMst))
			.add(Restrictions.like("deleteFlag","N"));
		empFamilyDtlsListObj = crit.list();			
		empFamilyDtlsListObj = crit.list();		
		return empFamilyDtlsListObj;
	}

	public List getAllData(OrgUserMst orgUserMst, CmnLookupMst cmnLookupMstSon, CmnLookupMst cmnLookupMstDaughter) 
	{
		Session hibSession = getSession();	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId", orgUserMst.getUserId()))
		.add(Restrictions.like("deleteFlag","N"))
		.add(Restrictions.or(Restrictions.like("cmnLookupMstByFmRelation",cmnLookupMstSon), (Restrictions.like("cmnLookupMstByFmRelation",cmnLookupMstDaughter))));
		empFamilyDtlsListObj = crit.list();							
		return empFamilyDtlsListObj;
	}

	public List getDependantRecordCheckWithDate(long userId, long memberId) 
	{
		Session hibSession = getSession();																									
		logger.info("getDependantRecordCheckWithDate  , FamilyDao called  : " + userId);	
		List empFamilyDtlsListObj=new ArrayList();
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId", userId))
			.add(Restrictions.like("memberId",memberId))
			.add(Restrictions.like("dependentOrNot", "Y"))
			.add(Restrictions.like("deleteFlag","N"))
			.add(Restrictions.isNull("endDate"));
		empFamilyDtlsListObj = crit.list();
		return empFamilyDtlsListObj;
	}
	public List getDependantRecordOnUserId(long userId) 
	{
		Session hibSession = getSession();																									
		logger.info("getDependantRecordCheckWithDate  , FamilyDao called  : " + userId);	
		List empFamilyDtlsListObj=new ArrayList();
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId", userId))
			.add(Restrictions.like("dependentOrNot", "Y"))
			.add(Restrictions.like("deleteFlag","N"))
			.add(Restrictions.isNull("endDate"));
		empFamilyDtlsListObj = crit.list();	
		return empFamilyDtlsListObj;
	}
	public List getDependantRecordOnUserIdForWelFare(long userId) 
	{
		Session hibSession = getSession();															
		logger.info("getDependantRecordCheckWithDate  , FamilyDao called  : " + userId);	
		List empFamilyDtlsListObj=new ArrayList();
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId", userId))
			.add(Restrictions.like("dependentOrNot", "Y"))
			.add(Restrictions.ne("cmnLookupMstByFmDeadOrAlive.lookupName", "fm_Dead"))				
			.add(Restrictions.like("deleteFlag","N"))
			.add(Restrictions.isNull("endDate"));
		empFamilyDtlsListObj = crit.list();	
		return empFamilyDtlsListObj;
	}
//	IFMS
	public List getEmpFamilyMaxMemberID(OrgUserMst orgUserMst) {						
		Session hibSession = getSession();				
		logger.info("get Max Memeber ID  called  "+orgUserMst.getUserId());	
		List empFamilyDtlsListObj=new ArrayList();													
		Criteria crit = hibSession.createCriteria(HrEisFamilyDtl.class);
		crit.add(Restrictions.eq("orgUserMstByUserId.userId", orgUserMst.getUserId()))
			.add(Restrictions.like("deleteFlag","N"))
			.addOrder(Order.desc("cmnLookupMstByFmDeadOrAlive.lookupId"))
			.addOrder(Order.asc("fmRelationOther"))
			.addOrder(Order.desc("fmDateOfBirth"));
		empFamilyDtlsListObj = crit.list();		
		return empFamilyDtlsListObj;											
	}
	public List getAllDepartmentByLangId(long langId)// Change By sunil on 03/06/08 for Employment Dtls 
	{
		Session hibSession = getSession();				
		List orgDeptList=new ArrayList();													
		Criteria crit = hibSession.createCriteria(OrgDepartmentMst.class);
		crit.add(Restrictions.eq("cmnLanguageMst.langId",langId))
			.addOrder(Order.desc("depName"));
		orgDeptList = crit.list();		
		return orgDeptList;
	}
	public List<CmnLookupMst> getAllRelationByLookUpNameAndLang(String aStrLookUpName,long langID,String restrictedLookupName)
	{
		CmnLookupMst cmnLookupMst = null;
		List<CmnLookupMst> dataList = new ArrayList<CmnLookupMst>();    
		try 
		{
			Session hibSession = getSession(); 
			Criteria objCrt = hibSession.createCriteria(CmnLookupMst.class);		
			objCrt.add(Restrictions.eq("lookupName",aStrLookUpName ));
			objCrt.add(Restrictions.eq("cmnLanguageMst.langId",langID ));
			objCrt.setCacheable(true).setCacheRegion("ecache_lookup");
			cmnLookupMst = (CmnLookupMst)objCrt.uniqueResult();
			//got lookup object
			objCrt = hibSession.createCriteria(CmnLookupMst.class);
			objCrt.add(Restrictions.eq("parentLookupId",cmnLookupMst.getLookupId()));
			objCrt.add(Restrictions.ne("lookupName",restrictedLookupName));
			objCrt.addOrder(Order.asc("orderNo"));
			objCrt.addOrder(Order.asc("lookupDesc"));
			objCrt.setCacheable(true).setCacheRegion("ecache_lookup");
			dataList = (ArrayList)objCrt.list();			 
		}
		catch(Exception e)
		{
			logger.error("Error while getting LookUpVo by getAllRelationByLookUpNameAndLang ",e);
		}		
		return dataList;
	}
}
