package com.tcs.sgv.eis.dao;
//Comment By Maruthi for import Organisation.
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;

public class BillHeadDao extends GenericDaoHibernateImpl<HrPayBillHeadCustomVO, Long>  {

	
ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
	
	public final int FIN_YEAR_ID=Integer.parseInt(constantsBundle.getString("FIN_YEAR_ID")); //Financial Year for Budget Heads.

	public BillHeadDao(Class<HrPayBillHeadCustomVO> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
	
	public List getAllDatabybhId(long bhId,int finYrId)
	{
		List list = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		
		
		sb.append(" SELECT a.budsubhdId,a.budsubhdCode,a.demandCode,a.budsubhdDescLong,a.budmjrhdCode,a.budsubmjrhdCode,a.budminhdCode, bh.billId, bh.subheadId, bh.cmnLocationMst.locId, bh.finYearId, bh.dsgn_Id, bh.class_Id,shm.element_code ");
		sb.append(" FROM SgvaBudsubhdMst a, HrPayBillHeadMpg bh,HrPayOrderSubHeadMpg shm ");
		//sb.append(" WHERE a.langId = 'en_US' and a.finYrId = '"+FIN_YEAR_ID+"' and  a.budsubhdId = bh.subheadId and bh.billHeadId ="+bhId );
		sb.append(" WHERE a.langId = 'en_US'  and  bh.subheadId = shm.element_code and shm.finYearId = "+finYrId+" and shm.sgvaBudsubhdMst.budsubhdId = a.budsubhdId and bh.billHeadId ="+bhId );
		Query query = hibSession.createQuery(sb.toString());
		list = query.list();
		return list;
	}
	
	public List getAllData(String locationCode,int finYrId)
	{
		List billheadList = null;
		Session hibSession = getSession();
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT a.budsubhdDescLong,a.budsubhdId,a.demandCode,a.budmjrhdCode,a.budsubmjrhdCode,a.budminhdCode,b.billId,b.billHeadId ");
		sb.append(" FROM SgvaBudsubhdMst a,HrPayBillHeadMpg b,CmnLocationMst c,HrPayOrderSubHeadMpg shm ");
		//sb.append(" WHERE a.budsubhdId=b.subheadId and a.langId='en_US' and b.cmnLocationMst.locId=c.locId and c.locationCode='"+locationCode+"' ORDER BY b.billId");//Lang_Id hardCoded
		sb.append(" WHERE b.subheadId = shm.element_code and shm.finYearId = "+finYrId+" and shm.sgvaBudsubhdMst.budsubhdId = a.budsubhdId and a.langId='en_US' and b.cmnLocationMst.locId=c.locId and c.locationCode='"+locationCode+"' ORDER BY b.billId");//Lang_Id hardCoded
        
        Query query = hibSession.createQuery(sb.toString());
        billheadList = query.list();
		
		return billheadList;
	}
	
	public List getAllDsgnFromLocation()
	{
		List billHeadList = null;
		Session hibSession = getSession();
		
		
		
	    String strQuery = "SELECT j.dsgnName,j.dsgnId FROM OrgDesignationMst j WHERE j.activateFlag=1 ORDER BY j.dsgnName";
	    Query query = hibSession.createQuery(strQuery);
	    billHeadList = query.list();
		
		return billHeadList;
	}	
	
	
	public List getHeadStructure(long subHeadId)
	{
		List headList = null;
		Session hibSession = getSession();
		
		
		
	    String strQuery = "select  h.demandCode,h.budmjrhdCode,h.budsubmjrhdCode,h.budminhdCode,h.budsubhdDescLong from SgvaBudsubhdMst h WHERE h.budsubhdId="+subHeadId;
	    Query query = hibSession.createQuery(strQuery);
	    headList = query.list();
		
		return headList;
	}	

	
	public List getdsgnName(String dsgnId)
	{
		List headList = null;
		Session hibSession = getSession();
		
		
		
	    String strQuery = " SELECT r.dsgnId,r.dsgnName FROM OrgDesignationMst r WHERE r.dsgnId in("+dsgnId+")";
	    Query query = hibSession.createQuery(strQuery);
	    logger.info("query is:--->>>"+query.toString());
	    headList = query.list();
		
		return headList;
	}	

	public List getgradeName(String gradeId)
	{
		List headList = null;
		Session hibSession = getSession();
		
		
		
	    String strQuery = "  SELECT s.gradeId,s.gradeName FROM OrgGradeMst s WHERE s.gradeId in("+gradeId+")";
	    Query query = hibSession.createQuery(strQuery);
	    headList = query.list();
		
		return headList;
	}	
	
	
	
	public List restdsgnName(String dsgnId)
	{
		List headList = null;
		Session hibSession = getSession();
		
		
		
	    String strQuery = "  SELECT r.dsgnId,r.dsgnName FROM OrgDesignationMst r WHERE r.dsgnId NOT IN ("+dsgnId+") AND r.activateFlag=1 " ;
	    Query query = hibSession.createQuery(strQuery);
	    logger.info("query is:--->>>"+query.toString());
	    headList = query.list();
		
		return headList;
	}	

	public List restgradeName(String gradeId)
	{
		List headList = null;
		Session hibSession = getSession();
		
		
		
	    String strQuery = "SELECT s.gradeId, s.gradeName FROM OrgGradeMst s WHERE s.gradeId NOT IN ("+gradeId+")  and s.cmnLanguageMst.langId=1 ";
	    Query query = hibSession.createQuery(strQuery);
	    headList = query.list();
		
		return headList;
	}	
	
	
	
	public List gradeList(String gradeId)
	{
		List gradeList = null;
		Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    

		sb.append(" SELECT dsgn.dsgnName, dsgn.dsgnId , grade.gradeId, grade.gradeName  " );
		sb.append(" FROM HrEisGdMpg gd, OrgDesignationMst dsgn, OrgGradeMst grade " );
		sb.append(" WHERE gd.orgGradeMst.gradeId = grade.gradeId AND gd.orgDesignationMst.dsgnId = dsgn.dsgnId AND gd.orgGradeMst.gradeId in("+gradeId+") ORDER BY dsgn.dsgnName ");	   
	    Query query = hibSession.createQuery(sb.toString());
	    gradeList = query.list();
	    logger.info("query is----"+query);
		
		return gradeList;
	}	
	
	
	public List removegradeList(String gradeId ,String restGradeId)
	{
		List gradeList = null;
		Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
	    
	    
		sb.append(" SELECT dsgn.dsgnName, dsgn.dsgnId , grade.gradeId,grade.gradeName  " );
		sb.append(" FROM HrEisGdMpg gd, OrgDesignationMst dsgn, OrgGradeMst grade " );
		sb.append(" WHERE gd.orgGradeMst.gradeId = grade.gradeId and gd.orgDesignationMst.dsgnId = dsgn.dsgnId and  gd.orgGradeMst.gradeId NOT IN("+gradeId+") AND gd.orgGradeMst.gradeId IN ("+restGradeId+")  ORDER BY dsgn.dsgnName " );
	   
	    Query query = hibSession.createQuery(sb.toString());
	    gradeList = query.list();
	    logger.info("query is----"+query);
		
		return gradeList;
	}	
	
	//this hr_pay_location_homepageimage table vo not generated
	public List getImagePathForHomePage(long locId)
	{ 
		List imagePathList = null;
		Session hibSession = getSession();
	    StringBuffer sb = new StringBuffer();
	    
		sb.append("SELECT h.image_path FROM hr_pay_location_homepageimage h WHERE h.loc_id="+locId);
		
	    Query query = hibSession.createSQLQuery(sb.toString());
	    imagePathList = query.list();
	    logger.info("query is----"+query);
		
		return imagePathList;
	}	
	
	
	
	//added by Abhilash For Scheme
	public List getAllDatabybudSubHeadId(long bhId,long locId)
	{
		List list = null;
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		
		
		
		sb.append(" SELECT a.budsubhdCode,a.budsubhdDescLong,a.demandCode,a.budmjrhdCode,a.budsubmjrhdCode,a.budminhdCode, bh.cmnLocationMst.locId, a.budsubhdId ");
		sb.append(" FROM SgvaBudsubhdMst a, HrPayBillHeadMpg bh,HrPayOrderSubHeadMpg shm ");
		//sb.append(" WHERE a.langId = 'en_US' and a.finYrId = '"+FIN_YEAR_ID+"' and  a.budsubhdId = bh.subheadId and bh.billHeadId ="+bhId );
		sb.append(" WHERE a.langId = 'en_US'   and bh.cmnLocationMst.locId= "+locId+" and a.budsubhdId="+bhId );
		Query query = hibSession.createQuery(sb.toString());
		list = query.list();
		return list;
	}
	//ended by abhilash
	
}
