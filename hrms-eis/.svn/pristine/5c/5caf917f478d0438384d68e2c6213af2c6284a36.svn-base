package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMstHst;
import com.tcs.sgv.eis.valueobject.HrEisEmrgcycntcDtl;
import com.tcs.sgv.eis.valueobject.HrEisLangPrfcncyDtl;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMst;
import com.tcs.sgv.ess.valueobject.OrgEmpcontactMstHst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.hod.common.utils.BiometricsUtils;


public class EmpInfoDAOImpl_WF extends GenericDaoHibernateImpl implements EmpInfoDAO_WF 
{
	
	private static final Log logger = LogFactory.getLog(EmpInfoDAOImpl_WF.class);
	public final static String PROPERTY_FILE_PATH= "resources/hod/common/BiometricImgLbl" ;
    
	private static ResourceBundle rsEng = ResourceBundle.getBundle(PROPERTY_FILE_PATH, new Locale("en_US"));
    private static ResourceBundle rsGuj = ResourceBundle.getBundle(PROPERTY_FILE_PATH, new Locale("gu"));
	
    public EmpInfoDAOImpl_WF(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getAllEmpData(CmnLanguageMst cmnLanguageMst)
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class); 	        	        
		return objCrt.list();
	}
	public List getAllEmpData()
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		return objCrt.list();
	}
	public List getAllEmpData(List empIdList)
	{
		Criteria objCrt = null;
		Session hibSession = getSession();
		objCrt = hibSession.createCriteria(HrEisEmpMst.class);
		if(!empIdList.isEmpty())
		{
			objCrt.add(Restrictions.not(Restrictions.in("empId", empIdList)));
		}
		return objCrt.list();
	}

	public List getEmpIdData(long EmpId,CmnLanguageMst cmnLanguageMst)
	{
		List list =new ArrayList();

		Session hibSession = getSession();

		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.like("orgEmpMst.empId", EmpId)); 
		//crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		list= crit.list();

		return list;
	}
	public List getEmpIdData(long empId)
	{
		List list =new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.like("orgEmpMst.empId", empId)); 
		list= crit.list();
		logger.info("list==============="+list.size());
		return list;
	}

	public List getOrgEmpIdData(long EmpId,CmnLanguageMst cmnLanguageMst)
	{
		List list =new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("empId", EmpId)); 
		crit.add(Restrictions.like("cmnLanguageMst", cmnLanguageMst));
		list= crit.list();
		return list;
	}
	/*
	 * Function:- This method Collect only those employees who are in org_emp_mst but not in hr_eis_emp_mst with specific Language.
	 *  
	 */
	public List getOrgEmpData(long langId)
	{
		List list =new ArrayList();
		Session hibSession = getSession();
		String query = "from OrgEmpMst as orgEmpMst where orgEmpMst.cmnLanguageMst.langId = "+langId +" and orgEmpMst.empId not in(select hrEisEmpMst.empId from HrEisEmpMst as hrEisEmpMst)";
		Query hsqlQuery = hibSession.createQuery(query);	      	
		list= hsqlQuery.list();	 	    
		return list;
	}	 
	
	public List findEmpName(String empName,long langId)
	{

		List empNames = new ArrayList();
		Session hibSession = getSession();	      		        	
		String query1 = "from HrEisEmpMst as empLookup where (lower(empLookup.orgEmpMst.empFname) like" +
		" lower('" + empName + "%') or lower(empLookup.orgEmpMst.empMname) like" +
		" lower('" + empName + "%') or lower(empLookup.orgEmpMst.empLname) like" +
		" lower('" + empName + "%')) and empLookup.empId in " +
		"(select distinct(dtls.hrEisEmpMst.empId) from HrEisOtherDtls as dtls) " +
		"order by empLookup.orgEmpMst.empFname";

		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		return empNames;
	}

	public List getEmpNamesFromOtherDtls()
	{

		List empNames = new ArrayList();
		Session hibSession = getSession();	      		        	
		String query1 = "from HrEisEmpMst as empLookup where empLookup.empId in " +
		" (select distinct(eisOtherdtls.hrEisEmpMst.empId) from HrEisOtherDtls as eisOtherdtls)" +
		"order by empLookup.orgEmpMst.empFname"; 

		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		return empNames;
	}

	public List getOrgData(OrgUserMst elementCode,long langId)
	{
		Criteria objCrt = null;
		try
		{
			Session hibSession = getSession();
			CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl=new CmnLanguageMstDaoImpl(CmnLanguageMst.class,getSessionFactory());
			CmnLanguageMst cmnLanguageMst=cmnLanguageMstDaoImpl.read(langId);
			objCrt = hibSession.createCriteria(OrgEmpMst.class);	
			objCrt.add(Restrictions.eq("orgUserMst",elementCode));
			objCrt.add(Restrictions.eq("cmnLanguageMst",cmnLanguageMst));
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return objCrt.list();
	}
	public OrgEmpMst getEmployee(OrgUserMst orgUserMst,long langId){
		OrgEmpMst orgEmpMst = new OrgEmpMst();
		Session hibSession = getSession();
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);
		logger.info("The langId is:-"+langId);
		Criteria crtEmpMst = hibSession.createCriteria(OrgEmpMst.class);
		crtEmpMst.add(Restrictions.like("orgUserMst",orgUserMst));
		crtEmpMst.add(Restrictions.like("cmnLanguageMst",cmnLanguageMst));
		orgEmpMst = (OrgEmpMst)crtEmpMst.uniqueResult();
		return orgEmpMst;
	}

	public List findorgEmpName(String empName,long langId)
	{

		List empNames = new ArrayList();
		Session hibSession = getSession();	      		        	
		String query1 = "from OrgEmpMst as empLookup where (lower(empLookup.empFname) like lower('%" + empName + "%') "+
		" or lower(empLookup.empMname) like lower('%" + empName + "%') " +
		" or lower(empLookup.empLname) like lower('%" + empName + "%') )" +
		" and empLookup.cmnLanguageMst.langId='"+langId+"' " +
		" and empLookup.empId not in " +
		"(select distinct(dtls.orgEmpMst.empId) from HrEisEmpMst as dtls)";
		Query sqlQuery1 = hibSession.createQuery(query1);
		empNames = sqlQuery1.list();
		return empNames;
	}

	public List<HrEisEmpMst> getHrEmpFromOrgEmp(OrgEmpMst orgEmpInfoMst)
	{
		List list = new ArrayList();
		Session hibSession = getSession();
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.like("orgEmpMst", orgEmpInfoMst)); 
		list= crit.list();	 	   	    	 			 			 		
		return list;
	} 	

	public OrgEmpMst getOrgEmpMstVO(long empid) {
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("empId", empid)); 	 	    
		list= crit.list();
		OrgEmpMst orgEmpMst= (OrgEmpMst)list.get(0);
		return orgEmpMst;
	}
	public List<CmnLanguageMst> getAllLanguageDtls() {
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(CmnLanguageMst.class);	 	    	 	    
		list= crit.list();	 				
		return list;
	}
	public List getAllEmpKnownLanguage(long userId) {
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(HrEisLangPrfcncyDtl.class);
		crit.add(Restrictions.like("orgUserMstByUserId.userId",userId));
		list= crit.list();	
		return list;
	}
	public OrgEmpcontactMst getOrgEmpContactMstDtls(long empid, CmnLookupMst cmnLookupMst) {
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(OrgEmpcontactMst.class);
		crit.add(Restrictions.like("orgEmpMstByEmpId.empId", empid));	 	    	 	 	    	 	   
		list= crit.list();	 	    
		if(list.isEmpty()==false)
		{	 	    		
			ListIterator li = list.listIterator();	 	    	
			while(li.hasNext())
			{
				OrgEmpcontactMst orgEmpcontactMst = (OrgEmpcontactMst)li.next();

				logger.info("==========orgEmpcontactMst======"+ orgEmpcontactMst);
				if(orgEmpcontactMst!=null)
				{
					if(orgEmpcontactMst.getCmnLookupMst()!=null)
					{
						if(cmnLookupMst.getLookupName().equals(orgEmpcontactMst.getCmnLookupMst().getLookupName()))
						{
							return orgEmpcontactMst;
						}
					}
				}
			}
		}			
		return null;
	}
	public HrEisEmpMst getHrEisEmpMstDtls(long empid) {
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(HrEisEmpMst.class);
		crit.add(Restrictions.like("orgEmpMst.empId", empid));
		list= crit.list();
		if(list.isEmpty()==false)
		{
			HrEisEmpMst hrEisEmpMst = (HrEisEmpMst)list.get(0);
			return hrEisEmpMst;	
		}			
		return null;
	}
	public HrEisEmrgcycntcDtl getHrEisEmpEmergencyContactData(long userId) {
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(HrEisEmrgcycntcDtl.class);
		crit.add(Restrictions.like("userId", userId));
		list= crit.list();
		if(list.isEmpty()==false)
		{
			HrEisEmrgcycntcDtl hrEisEmpEmergencyContact = (HrEisEmrgcycntcDtl)list.get(0);
			return hrEisEmpEmergencyContact;	
		}			
		return null;
	}
	public long getEngLishEmpId(long userId) {
		List list =new ArrayList();
		long langId=1;
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("orgUserMst.userId", userId))
		.add(Restrictions.like("cmnLanguageMst.langId", langId));
		list= crit.list();
		if(list.isEmpty()==false)
		{
			OrgEmpMst hrEisEmpEmergencyContact = (OrgEmpMst)list.get(0);
			return hrEisEmpEmergencyContact.getEmpId();	
		}			
		return 0;
	}
	public HrEisEmpMst getOtherHrEisEmpMstVO(long userId,HrEisEmpMst updateHrEisEmpMst) 
	{
		logger.info("getOtherHrEisEmpMstVO  , user Id : " + userId +"  HrEisEmpMst :  " + updateHrEisEmpMst.getEmpId());
		List list =new ArrayList();
		Session hibSession = getSession();	    
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("orgUserMst.userId", userId));
		list= crit.list();
		if(list.isEmpty()==false)
		{
			ListIterator li = list.listIterator();
			while(li.hasNext())
			{
				OrgEmpMst orgEmpMst =  (OrgEmpMst)li.next();		 	    	
				if(orgEmpMst.getEmpId()!=updateHrEisEmpMst.getOrgEmpMst().getEmpId())
				{
					Criteria crit1 = hibSession.createCriteria(HrEisEmpMst.class);
					crit1.add(Restrictions.like("orgEmpMst.empId", orgEmpMst.getEmpId()));
					List list_OtherVO= crit1.list();
					if(list_OtherVO.isEmpty()==false)
					{
						HrEisEmpMst hrEisEmpMst = (HrEisEmpMst)list_OtherVO.get(0);
						return hrEisEmpMst;	
					}			 	    	
				}
			}
		}
		return null;
	}
	public HrEisEmpMst getHrEmpVOOnUserId(long userId,long langId)
	{
		Session hibSession = getSession();	 		
		HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.eq("orgUserMst.userId", userId))
		.add(Restrictions.eq("cmnLanguageMst.langId", langId));
		List list = crit.list();

		if(list.isEmpty()==false)
		{
			OrgEmpMst empMst=(OrgEmpMst) list.get(0);
			Criteria crt_hr_Mst = hibSession.createCriteria(HrEisEmpMst.class);	 		
			crt_hr_Mst.add(Restrictions.like("orgEmpMst.empId",empMst.getEmpId()));
			List hrEisEmpVoList = crt_hr_Mst.list();
			if(hrEisEmpVoList.isEmpty()==false)
			{
				hrEisEmpMst = (HrEisEmpMst)hrEisEmpVoList.get(0);	 			
				return hrEisEmpMst;
			}
		}
		return null;	 		
	}
	public long getAttachmentSrNo(long attachmentId,boolean blnIsFace) 
	{
		Session hibSession = getSession();	
		CmnAttachmentMpg cmn_attachment_mpg = new CmnAttachmentMpg();
		Criteria crt_hr_Mst = hibSession.createCriteria(CmnAttachmentMpg.class);	 		
		crt_hr_Mst.add(Restrictions.like("cmnAttachmentMst.attachmentId",attachmentId));
		
		if (blnIsFace)
		{
			crt_hr_Mst.add(Restrictions.or(Restrictions.like("attachmentDesc", rsEng.getString(BiometricsUtils.FACE)), Restrictions.like("attachmentDesc", rsGuj.getString(BiometricsUtils.FACE))));
		}
		else
		{
			crt_hr_Mst.add(Restrictions.and(Restrictions.ne("attachmentDesc", rsEng.getString(BiometricsUtils.FACE)), Restrictions.ne("attachmentDesc", rsGuj.getString(BiometricsUtils.FACE))));
		}
		
		List list = crt_hr_Mst.list();
		if(!list.isEmpty())
		{
			cmn_attachment_mpg=(CmnAttachmentMpg) list.get(0);
			return cmn_attachment_mpg.getSrNo();
		}
		return 0;
	}
	public List getOrgEmpMstVOList(long userId, long langId) {
		List objList = new ArrayList();	
		Session hibSession =getSession();
		Criteria crit = hibSession.createCriteria(OrgEmpMst.class);
		crit.add(Restrictions.like("orgUserMst.userId", userId))
		.add(Restrictions.like("cmnLanguageMst.langId", langId));
		objList=crit.list();			
		return objList;
	}

	public HrEisEmpMstHst getHrEisEmpHstDataByEmpIdandDate(long empId, Date createdDate) 
	{
		HrEisEmpMstHst eisEmpMstHst=null;
		List objList = new ArrayList();
		try
		{
			Session hibSession =getSession();
			String strQuery = " from HrEisEmpMstHst a where a.orgEmpMst.empId = "+empId+" and a.createdDate <= '"+createdDate+"' and (a.updatedDate <= '"+createdDate+"' or a.updatedDate is null ) and a.id.trnCounter = (select max(b.id.trnCounter) from HrEisEmpMstHst b where b.orgEmpMst.empId = "+empId+" and (b.updatedDate <= '"+createdDate+"' or b.updatedDate is null) and b.createdDate <= '"+createdDate+"' )";
			Query sqlQuery = hibSession.createQuery(strQuery.toString());
			objList = sqlQuery.list();
			if(objList != null && !objList.isEmpty())
			{
				eisEmpMstHst = (HrEisEmpMstHst)objList.get(0);
			}
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return eisEmpMstHst;
	}

	public OrgEmpcontactMstHst getEmpContactDtlsByEmpIdandCrtdDate(long empId, Date createdDate, CmnLookupMst cmnLookupMst) 
	{
		OrgEmpcontactMstHst empcontactMstHst=null;
		List objList = new ArrayList();
		try
		{
			Session hibSession =getSession();
			String strQuery = " from OrgEmpcontactMstHst a where a.orgEmpMstByEmpId.empId = "+empId+" and a.cmnLookupMst.lookupName= '"+cmnLookupMst.getLookupName()+"' and (a.updatedDate is null or a.updatedDate <= '"+createdDate+"') and a.createdDate <= '"+createdDate+"' and a.id.trnCounter = (select max(b.id.trnCounter) from OrgEmpcontactMstHst b where b.orgEmpMstByEmpId.empId = "+empId+" and b.cmnLookupMst.lookupName= '"+cmnLookupMst.getLookupName()+"' and (b.updatedDate <= '"+createdDate+"' or b.updatedDate is null) and b.createdDate <= '"+createdDate+"')";
			Query sqlQuery = hibSession.createQuery(strQuery.toString());
			objList = sqlQuery.list();

			if(objList != null && !objList.isEmpty())
			{
				empcontactMstHst = (OrgEmpcontactMstHst)objList.get(0);
			}
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		return empcontactMstHst;
	}
}
