// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 12/6/2008 3:06:44 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AdminOrgPostDtlDaoImpl.java

package com.tcs.sgv.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.admin.valueobject.OrgPostDtl;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstDcpsDesignation;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;
import com.tcs.sgv.eis.valueobject.OrgSchemeMstVO;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.user.valueobject.FrmSubPostidMpg;
// Referenced classes of package com.tcs.sgv.ess.dao:
//            AdminOrgPostDtlDao

public class AdminOrgPostDtlDaoImpl extends GenericDaoHibernateImpl
    implements AdminOrgPostDtlDao
{

    public AdminOrgPostDtlDaoImpl(Class type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }

    public List getAllLocationList(long langId)
    {
        Session session = getSession();
        Criteria crit = session.createCriteria(CmnLocationMst.class);
        crit.add(Restrictions.eq("cmnLanguageMst.langId", Long.valueOf(langId)));
        return crit.list();
    }

    public List getAllBranchList(long langId)
    {
        Session session = getSession();
        Criteria crit = session.createCriteria(CmnBranchMst.class);
        crit.add(Restrictions.eq("cmnLanguageMst.langId", Long.valueOf(langId)));
        return crit.list();
    }

    public List getOrgPostList()
    {
        List listOrgPostDetailsRlt = null;
        List listOrgPostDtl = new ArrayList();
        Session session = getSession();
        Criteria crit = session.createCriteria(OrgPostDetailsRlt.class);
        listOrgPostDetailsRlt = crit.list();
        logger.info((new StringBuilder("========listOrgPostDetailsRlt====")).append(listOrgPostDetailsRlt.size()).toString());
        if(listOrgPostDetailsRlt != null && listOrgPostDetailsRlt.size() > 0)
        {
            logger.info("====inside iterator====listOrgPostDetailsRlt====");
            OrgPostDtl orgPostDtl = null;
            for(Iterator iterator = listOrgPostDetailsRlt.iterator(); iterator.hasNext(); listOrgPostDtl.add(orgPostDtl))
            {
                OrgPostDetailsRlt rlt = (OrgPostDetailsRlt)iterator.next();
                orgPostDtl = new OrgPostDtl();
                orgPostDtl.setPostId(rlt.getOrgPostMst().getPostId());
                orgPostDtl.setCmnLanguageMst(rlt.getCmnLanguageMst());
                orgPostDtl.setPostName(rlt.getPostName());
            }

        }
        logger.info((new StringBuilder("========listOrgPostDtl====")).append(listOrgPostDtl.size()).toString());
        return listOrgPostDtl;
    }

    public List getMaximumPsr(long locId)
    {
        List dataList = new ArrayList();
        Session session = getSession();
        String qry = (new StringBuilder(" select max(k.psr_no)+1 from hr_pay_post_psr_mpg k where k.loc_id=")).append(locId).toString();
        Query sqlQry = session.createSQLQuery(qry);
        logger.info((new StringBuilder("The query from hr_pay_post_psr_mpg is---->>>")).append(sqlQry).toString());
        dataList = sqlQry.list();
        return dataList;
    }

    public List getAllOfficeList()
    {
    	 Session session = getSession();
         String HQL_QUERY= "select distinct cmnbranchmst from CmnBranchMst cmnbranchmst, CmnBranchlocMpg cmnlocmpg,OrgPostDetailsRlt orgpostdtls where"
         + " cmnbranchmst.branchId=orgpostdtls.cmnBranchMst.branchId and cmnlocmpg.locationCode=orgpostdtls.cmnLocationMst.locId";
         Query query = session.createQuery(HQL_QUERY);
         List resultList=query.list();
         return resultList;
    	/*List dataList = new ArrayList();
        Session session = getSession();
        String qry = (new StringBuilder(" select distinct cmnbramst.BRANCH_NAME,cmnbramst.BRANCH_ID from CMN_BRANCH_MST cmnbramst, CMN_BRANCHLOC_MPG cmnmpg, ORG_POST_DETAILS_RLT orgpostrlt where cmnbramst.BRANCH_ID=orgpostrlt.BRANCH_ID and cmnmpg.LOC_ID=orgpostrlt.LOC_ID")).toString();
        Query sqlQry = session.createSQLQuery(qry);
        logger.info((new StringBuilder("The query from getAllOfficeList is---->>>")).append(sqlQry).toString());
        dataList = sqlQry.list();
        return dataList;*/
    }
    public List getAllSchemeListMapped()
    {
    	 Session session = getSession();
         String HQL_QUERY= "select distinct orgschememst from OrgSchemeMstVO orgschememst, OrgDdoMst orgddomst, HrPayDDOSchemeMpgVO hrpayddoschemempg where"
         + " orgddomst.ddoId=hrpayddoschemempg.orgDdoMst.ddoId and orgschememst.cmnLocationMst.locId=hrpayddoschemempg.cmnLocationMst.locId";
         Query query = session.createQuery(HQL_QUERY);
         List resultList=query.list();
         return resultList;
    	/*List dataList = new ArrayList();
        Session session = getSession();
        String qry = (new StringBuilder(" select distinct cmnbramst.BRANCH_NAME,cmnbramst.BRANCH_ID from CMN_BRANCH_MST cmnbramst, CMN_BRANCHLOC_MPG cmnmpg, ORG_POST_DETAILS_RLT orgpostrlt where cmnbramst.BRANCH_ID=orgpostrlt.BRANCH_ID and cmnmpg.LOC_ID=orgpostrlt.LOC_ID")).toString();
        Query sqlQry = session.createSQLQuery(qry);
        logger.info((new StringBuilder("The query from getAllOfficeList is---->>>")).append(sqlQry).toString());
        dataList = sqlQry.list();
        return dataList;*/
    }
    
    public List getAllPostList()
    {
        Session session = getSession();
       String HQL_QUERY= "select postRlt from OrgPostDetailsRlt postRlt, HrPayOfficepostMpg officeMpg where postRlt.orgPostMst.postId=officeMpg.orgPostMstByPostId.postId";
       Query query = session.createQuery(HQL_QUERY);
       List resultList=query.list();
       return resultList;
   
    }
    
    public List getAllBillsFromLocation(long locId)
    {
       Session session = getSession();
       String HQL_QUERY= " from MstDcpsBillGroup where LocId= "+locId+" and (billDeleted is null or billDeleted <> 'Y') and (billDcps is null or billDcps <> 'Y')";
       logger.info("getAllBillsFromLocation...........");
       Query query = session.createQuery(HQL_QUERY);
       List resultList=query.list();
       logger.info("AfterrrrrrrrgetAllBillsFromLocation...........");
       return resultList;
   
    }
    
    public List getAllOfficesFromLocation(long locId)
    {
        Session session = getSession();
       String HQL_QUERY= " from DdoOffice where LocId= "+locId;
       Query query = session.createQuery(HQL_QUERY);
       List resultList=query.list();
       return resultList;
   
    }
    public List getAllOfficesFromDDO(long ddoCode)
    {
        Session session = getSession();
       String HQL_QUERY= " from DdoOffice where dcpsDdoCode='"+ddoCode +"'";
      
       Query query =session.createQuery(HQL_QUERY);
       logger.info("getAllOfficesFromDDO..........."+query.toString());
       List resultList=query.list();
       return resultList;
   
    }
    public List getAllOfficesFromDDO(String ddoCode)
    {
    	 Session session = getSession();
         String HQL_QUERY= " from DdoOffice where dcpsDdoCode='"+ddoCode +"'";
        
         Query query =session.createQuery(HQL_QUERY);
         logger.info("getAllOfficesFromDDO..........."+query.toString());
         List resultList=query.list();
         return resultList;
   
    }
    public List getActiveDesig(long locId)
    {
    	 
    	 
    	  Session session = getSession();
          String HQL_QUERY= "select mst from MstDcpsDesignation dcpsDesig, OrgDesignationMst mst  where mst.dsgnId=dcpsDesig.orgDesignationId and mst.activateFlag=1 and  dcpsDesig.fieldDeptId =  "+locId;
          Query query = session.createQuery(HQL_QUERY);
          List resultList=query.list();
          return resultList;
    }
    
//added by abhilash
    
    
    public List getEmployeeDetailsListFromDsgnIdForPostDtls(long designationId,long locId,long BillGroupId)
	{
		logger.info("in getEmployeeDetailsListFromDsgnIdForPostDtls...........");
		List list = new ArrayList();
		Session hibSession = getSession();
		
	
			StringBuffer query = new StringBuffer();
			query.append(" select  distinct det.postName,p.postId,concat(orgEmpMst.empFname,' ',orgEmpMst.empMname,' ',orgEmpMst.empLname), " ); 
			query.append(" od.dsgnName,psr.psrId,bill.dcpsDdoBillDescription,look.lookupName"); 
			query.append(" from OrgEmpMst orgEmpMst, OrgUserMst ou,OrgPostMst p,OrgUserpostRlt up, OrgPostDetailsRlt det,HrEisEmpMst eis,OrgDesignationMst od,");  
			query.append(" HrPayPsrPostMpg psr,MstDcpsBillGroup bill,CmnLookupMst look "); 
			query.append(" where orgEmpMst.empId=eis.orgEmpMst.empId and orgEmpMst.orgUserMst.userId=ou.userId and ou.userId=up.orgUserMst.userId "); 
			query.append(" and p.postId=up.orgPostMstByPostId.postId "); 
			query.append(" and up.orgPostMstByPostId.postId=det.orgPostMst.postId "); 
			query.append(" and det.orgPostMst.postId=psr.postId"); 
			query.append(" and psr.billNo=bill.dcpsDdoBillGroupId ");
			query.append(" and p.postTypeLookupId=look.lookupId ");
			query.append(" and det.orgDesignationMst.dsgnId=od.dsgnId and p.activateFlag=1 ");
			
			if(designationId>0)
			{
			query.append(" and det.orgDesignationMst.dsgnId="+designationId+" ");
			}
			if(BillGroupId>0)
			{
				query.append(" and bill.dcpsDdoBillGroupId="+BillGroupId+" ");
			}
			
			query.append(" and det.cmnLocationMst.locId="+locId+"  ");
			
			Query sqlQuery = hibSession.createQuery(query.toString());
			list= sqlQuery.list();
	        logger.info("==> getEmployeeDetailsList query :: "+sqlQuery);
	 
		
		   	    	 			 			 		
        
 		return list;
	}
    
    public List getPostNameForDisplayWithSearch(long locId,String lPostName,String PsrNo,String BillNo,String Dsgn,long designationId,long BillGroupId)
	{
		List postNameList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("  select pd.post_name,pd.post_id,(select concat(concat(concat(o.emp_fname, ' '), concat(o.emp_mname, ' ')),concat( o.emp_lname,' ')) from org_emp_mst o, org_userpost_rlt up ");
		sb.append("  where  o.lang_id = 1 and o.user_id = up.user_id and up.post_id = pd.post_id and up.end_date is null and up.activate_flag = 1),ds.dsgn_name , P.PSR_NO ");
		sb.append("  , (select mp.DESCRIPTION  from mst_dcps_bill_group mp where  p.bill_no = mp.BILL_GROUP_ID) BillNo,org.post_type_lookup_id,cmn.lookup_name from org_post_details_rlt pd, org_designation_mst ds,org_post_mst org, cmn_lookup_mst cmn" );
		sb.append("  , HR_PAY_POST_PSR_MPG p,org_post_mst postMst ");
		
		if(BillGroupId>0)
		{
			sb.append(" ,mst_dcps_bill_group bill");
		}
		
		
		sb.append("  where pd.loc_id = '" + locId +"' and P.POST_ID = PD.POST_ID and  pd.dsgn_id = ds.dsgn_id and ds.lang_id = 1  " );
		sb.append(" and postMst.post_id = pd.post_id and postMst.location_code = pd.loc_id and postMst.activate_flag = 1 ");
		sb.append("  and  upper(pd.post_name) like  upper('%" + lPostName+ "%') ");   
		
		
		
		if(designationId>0)
		{
			sb.append(" and ds.dsgn_id="+designationId+" ");
		}
		if(BillGroupId>0)
		{
			sb.append(" and bill.BILL_GROUP_ID="+BillGroupId+" and bill.BILL_GROUP_ID=p.bill_no");
		}
		
		sb.append(" and pd.post_id=org.post_id ");
		sb.append(" and cmn.lookup_id=org.post_type_lookup_id ");
		sb.append("   order by pd.post_name  ");
		
		logger.info("The Query String is:-"+sb.toString());
		logger.info("The Query lPostName is:-"+lPostName);
		
		
	    Query query = hibSession.createSQLQuery(sb.toString());
	    postNameList = query.list();
	    logger.info("Parent Post List size is:-"+postNameList.size());
	    logger.info("Parent Post List size is:-"+postNameList.listIterator());
		return postNameList;
	}
    
    public List getActiveDsgnList(long locId)
    {
    	 
    	 
    	  Session session = getSession();
          String HQL_QUERY= "select distinct mst from OrgPostDetailsRlt rlt, OrgDesignationMst mst  where mst.dsgnId=rlt.orgDesignationMst.dsgnId and mst.activateFlag=1 and mst.endDate is null and rlt.cmnLocationMst.locId="+locId+" order by mst.dsgnName ";
          
          Query query = session.createQuery(HQL_QUERY);
          logger.info("The Query getActiveDsgnList String is:-"+query.toString());
          List resultList=query.list();
          return resultList;
    }
    public List getActiveDsgnListOfFieldDept(long locId)
    {
    	  Session session = getSession();
    	  StringBuffer sb = new StringBuffer();
    	  sb.append("SELECT desig FROM MstPayrollDesignationMst  mst,CmnLocationMst loc , OrgDesignationMst desig where ");
    	  sb.append("  mst.fieldDeptId = loc.locId and loc.locId = ");
    	  sb.append(locId);
    	  sb.append(" and desig.dsgnId = mst.orgDesignationId ");
    	  //added by samadhan for oder by  DSGN_NAME
    	  sb.append(" order by upper(desig.dsgnName) ");
    	  String HQL_QUERY=sb.toString();
          Query query = session.createQuery(HQL_QUERY);
          logger.info("The Query getActiveDsgnList String Hql is:-"+query.toString());
          List resultList=query.list();
          return resultList;
    }
    //ended by abhilash
    //added by manish 
    public List<MstPayrollDesignationMst> getMstDcpsDsgnObject(long parentLocId,long orgDesignationId)
    {
    	 
    	 
    	  Session session = getSession();
          String HQL_QUERY= "select distinct mst from MstPayrollDesignationMst mst where mst.fieldDeptId="+parentLocId+ " and mst.orgDesignationId="+orgDesignationId;
          
          Query query = session.createQuery(HQL_QUERY);
          logger.info("The Query getMstDcpsDsgnObject String is:-"+query.toString());
          List<MstPayrollDesignationMst> resultList=query.list();
          return resultList;
    }
    
    
    public List fetchAllPostsUnderOrder(long oldOrderId , long locId){
    	List postList = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append("select postRlt from HrPayOrderHeadMpg ordHead,HrPayOrderHeadPostMpg postOrder, com.tcs.sgv.ess.valueobject.OrgPostMst postMst, OrgPostDetailsRlt postRlt where");
    	sb.append(" ordHead.orderHeadId = postOrder.orderHeadId and postOrder.postId = postMst.postId and");
    	sb.append(" postMst.postTypeLookupId = 10001198130 and postMst.postId = postRlt.orgPostMst.postId and ordHead.orderId =");
    	sb.append(oldOrderId);
    	   	 
    	Query query = session.createQuery(sb.toString());
    	postList = query.list();
    	
    	return postList;
    	
    }
    
    public void updateOrderOfPost(long postId , long orderHeadId){
    	List postList = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append("update HR_PAY_ORDER_HEAD_POST_MPG set ORDER_HEAD_ID = ");
    	sb.append(orderHeadId);
    	sb.append(" where POST_ID = ");
    	sb.append(postId);
       	   	 
    	Query query = session.createSQLQuery(sb.toString());
    	int noOfRows = query.executeUpdate();
    	logger.info("Rows updated are-->"+noOfRows+" with Query"+query.toString());
    }    
    public List getDDODtls(String DDOCode)
    {
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- vivek DAo---");
    	sb.append("SELECT LOCATION_CODE,HOD_LOC_CODE,post_ID FROM ORG_DDO_MST where DDO_CODE='"+DDOCode+"'");
    	logger.info("---- vivek DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    	
    }
    
    public List getSubDDOs(Long locId){
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	sb.append("SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE=(select ddo_code from org_ddo_mst where LOCATION_CODE="+locId+")");
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    }
    
    public List getSchemeCode(String ddoCode){
    	List bill = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getBilId DAO---");
    	sb.append("select scheme_code,Loc_iD from mst_dcps_bill_group where ddo_code='"+ddoCode+"'");
    	logger.info("---- getBilId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	bill = query.list();
    	return bill;    	
    }
    
    
    
    public List getGross(String locId,String month,String year,String schemeCode)
    {
    	List temp = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getGross---");
    	sb.append("SELECT bill_no,bill_net_amount,Bill_gross_amt FROM PAYBILL_HEAD_MPG where LOC_ID=");
    	sb.append(locId);
    	sb.append("and APPROVE_FLAG=0 and paybill_month=");
    	sb.append(month);
    	sb.append("and paybill_year=");
    	sb.append(year);
    	sb.append("and SUBHEAD_ID=(SELECT scheme_id FROM MST_SCHEME where SCHEME_CODE='");
    	sb.append(schemeCode);
    	sb.append("')");
    	logger.info("---- getBilId DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	logger.info("---- query---"+sb);
    	temp = query.list();
    	return temp;    	
    }
    
    public List getSubDDOsOffc(Long postId, String talukaId, String ddoSelected){
    	List ddoDtls = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	
    	//Added by roshan 
    	sb.append("SELECT ddo.DDO_CODE ,ddo.DDO_office FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, mst_dcps_ddo_office office where lower(office.ddo_office)= 'yes' and ddo.DDO_OFFICE !='null' and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.ddo_code=ddo.ddo_code and rep.status =1 and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
    	if((talukaId!=null)&&(talukaId!="")&&(Long.parseLong(talukaId)!=-1)){
    		sb.append(" and office.taluka="+talukaId);
    	}
    	if((ddoSelected!=null)&&(ddoSelected!="")){
    		sb.append(" and (office.ddo_code like '%"+ddoSelected+"%' or ddo.ddo_office like '%"+ddoSelected+"%')");
    	}
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoDtls = query.list();
    	return ddoDtls;
    	
    }
    
    public List getStastics(String query1){
    	
    	Session session = getSession();
    	
    	logger.info("---- getSubDDOs DAO---"+query1);
    	   	
    	Query query = session.createSQLQuery(query1);
    	List result = query.list();
    	logger.info("result size .. "+result.get(0).toString());
    	return result;
    }
    
    //added by vaibhav tyagi: start
    public List getSubjectList()
    {
    	logger.info("Inside getSubList......");
    	List subList = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	sb.append("SELECT * FROM FRM_SUBJECT_MST");
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	subList = query.list();
    	logger.info("Subject List : "+query.toString());
    	logger.info("Subject List size : "+subList.size());
    	return subList;
    }

	public void submitSubject(FrmSubPostidMpg subjectPostMpg) {
		logger.info("Inside submitSubject......");
		Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- submitSubject DAO---");
    	logger.info("subject name "+subjectPostMpg.getSubjectName());
    	//sb.append("insert into frm_sub_postid_mpg values("+subjectPostMpg.getPostID()+","+subjectPostMpg.getPostID()+" "+subjectPostMpg.getSubjectName())")");
    	sb.append("insert into frm_sub_postid_mpg values("+subjectPostMpg.getPostID()+",'"+subjectPostMpg.getSubjectName()+"')");
    	logger.info("---- submitSubject DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	query.executeUpdate();
	}
	//added by vaibhav tyagi: end
	
	
	// Mayuresh
	public List submitSubject0707(long postId) 
	{
		logger.info("Inside submitSubject0707......");
		List submitSubject07 = null;
    	Session session = getSession();
    	String strQuery = "SELECT SUBJECT_NAME FROM frm_sub_postid_mpg where POST_ID="+postId+"";
    	logger.info(strQuery);
    	Query query = session.createSQLQuery(strQuery);
    	submitSubject07 = query.list();
    	return submitSubject07;
	}
	
	
	
	public List getSubOfficesFromDDO(long postId)
	{
		List SubOffices = null;
    	Session session = getSession();
    	String strQuery = "SELECT LOC_ID,LOC_NAME FROM CMN_LOCATION_MST where PARENT_LOC_ID in (SELECT PARENT_LOC_ID FROM CMN_LOCATION_MST where LOC_ID in (SELECT LOC_ID FROM Mst_Dcps_Ddo_Office where DDO_CODE in (SELECT REPT_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_POST_ID="+postId+")))";
    	logger.info(strQuery);
    	Query query = session.createSQLQuery(strQuery);
    	SubOffices = query.list();
    	return SubOffices;
	}
	
	
	public List getSubOfficesFromDDONew(long postId)
	{
		List SubOffices = null;
    	Session session = getSession();
    	String strQuery = "SELECT FIELD_ID,SUB_FIELD_NAME FROM SUB_FIELD_DEPT";
    	logger.info(strQuery);
    	Query query = session.createSQLQuery(strQuery);
    	SubOffices = query.list();
    	return SubOffices;
	}
   
	
	//added by roshan kumar
	public List getAllddoList(String ddoCode) {
		logger.info("Inside getDDO list.....");
    	List ddoList = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	sb.append("SELECT zp.zp_ddo_code,ddo.ddo_office FROM RLT_zp_ddo_map zp inner join org_ddo_mst ddo on ddo.ddo_code=zp.zp_ddo_code where ((rept_ddo_code='"+ddoCode+"') or (final_ddo_code='"+ddoCode+"'))");
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	ddoList = query.list();
    	logger.info("ddo List size : "+ddoList.size());
    	return ddoList;
	}
	
	
	public String districtName(String ddoCode) {
		String districtId="";
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get district---");
		//sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
		//added by roshan
		sb.append("SELECT distinct DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code='"+ddoCode+"' and lower(ddo_office)='yes'");
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		districtId = (String) query.uniqueResult();
		logger.info("district name is "+districtId);
		return districtId;
		
	}
	
	
	public List allTaluka(String districtID) 
	{
		List talukaList=null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get Taluka list---");
		
		sb.append("SELECT TALUKA_ID,TALUKA_NAME FROM CMN_TALUKA_MST where DISTRICT_ID="+districtID);
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		talukaList = query.list();
		logger.info("taluka size is "+talukaList.size());
		return talukaList;
	}

	public List getSubjectListForMapping() {
		logger.info("Inside getSubList......");
    	List subList = null;
    	Session session = getSession();
    	StringBuffer sb = new StringBuffer();
    	logger.info("---- getSubDDOs DAO---");
    	sb.append("SELECT SUBJECT_ID, SUBJECT_NAME FROM FRM_SUBJECT_MST");
    	logger.info("---- getSubDDOs DAo---"+sb);
    	Query query = session.createSQLQuery(sb.toString());
    	subList = query.list();
    	logger.info("Subject List : "+query.toString());
    	logger.info("Subject List size : "+subList.size());
    	return subList;
	}
}