package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

//import net.sf.hibernate.Criteria;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisEmpCompMpg;
import com.tcs.sgv.eis.valueobject.HrPayCompGrpMst;
import com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;


public class DeptCompMPGDAOImpl extends GenericDaoHibernateImpl<HrPayLocComMpg, Long> implements DeptCompMpgDAO {
	
	Log logger = LogFactory.getLog( getClass() );
	
	public DeptCompMPGDAOImpl(Class<HrPayLocComMpg> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
/*	public List getLocIds(long postId)
    {
        Criteria objCrt = null;
        List  list = null;
            Session hibSession = getSession();
            logger.info("going to execute query....");

            String strQuery = "from OrgPostDetailsRlt rlt where rlt.orgPostMst.postId ="  + postId;
            Query query = hibSession.createQuery(strQuery);
            //Query query = hibSession.createQuery("from HrEisBranchMst");
            logger.info("OrgSchemeMstDAOImpl queryqueryquery  " + query);
            list = query.list();
            logger.info("List size in DAO:-->" + list.size());
        return list;
    }*/
/*	public HrPayLocComMpg getDataIDisPresent(long locId,long compId,long grpid)
	{
		 List  list = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");

         String strQuery = "select distinct mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.compId="+compId+" and mpg.hrpaycompgrpmst.compoGrpId="+grpid;
         Query query = hibSession.createQuery(strQuery);
         //Query query = hibSession.createQuery("from HrEisBranchMst");
         logger.info("HrPayLocComMpg queryqueryquery  " + query);
         list = query.list();
         HrPayLocComMpg hrPayLocComMpg=null;
         
         
         if(list!=null&&list.size()>0)
         {
        	 hrPayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
         }
        // HrPayLocComMpg hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
         return hrPayLocComMpg;
         //logger.info("List size in DAO:-->" + list.size());
         
	}*/

	public List getDataIDisPresent(String[] lArrallowList,String[] lArrDeductList,long hdncheckedvalueofAllow,long hdncheckedvalueofdeduct,long grpId) 
	{
		
		 List  list = null;
	     List HrPayLocMpgList = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");
         String AllowID="";
         String DeductID="";
       //  long lenth= lArrallowList.length;
        // logger.info("===> lenth :: "+lenth);
       //  logger.info("===> lenth :: "+(lenth-1));
       //  long len=(lenth-1);
         
         StringBuffer HrPayloc = new StringBuffer();
         HrPayloc.append("select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.compoGrpId="+grpId+" and mpg.compId not in(");
        // String strQuery = "from HrPayLocComMpg mpg where mpg.compId not in(";
         logger.info("==> hdncheckedvalueofAllow :: "+hdncheckedvalueofAllow);
        if(hdncheckedvalueofAllow!=0)
        {
         for(int i=0;i<hdncheckedvalueofAllow;i++)
         {
        	 AllowID =lArrallowList[i];
        	 logger.info("==> in dao AllowID :: "+AllowID);
        	 if(i==(hdncheckedvalueofAllow-1))
        	 {
        		 HrPayloc.append(AllowID);
        	 }
        	 else
        	 {
        		 logger.info("====> in ,,, ");
        		 HrPayloc.append(AllowID);
        		 HrPayloc.append(",");
        	 }
         }
        }
        
       
        
        logger.info("==> hdncheckedvalueofdeduct :: "+hdncheckedvalueofdeduct);
        if(hdncheckedvalueofdeduct!=0)
        {
         HrPayloc.append(",");
         
         for(int i=0;i<hdncheckedvalueofdeduct;i++)
         {
        	 DeductID =lArrDeductList[i];
        	 logger.info("==> in dao DeductID :: "+DeductID);
        	 if(i==(hdncheckedvalueofdeduct-1))
        	 {
        		 logger.info("====> in if ");
        		 HrPayloc.append(DeductID);
        	 }
        	 else
        	 {
        		 logger.info("====> in ,,, ");
        		 HrPayloc.append(DeductID);
        		 HrPayloc.append(",");
        	 }
         }
        }
         HrPayloc.append(")");
         HrPayloc.append("and mpg.hrpaycompgrpmst.isactive="+1);
         
         logger.info("====> HrPayloc :: "+HrPayloc);
         
         Query query = hibSession.createQuery(HrPayloc.toString());
         logger.info("====> in Dao query :: "+query);
         logger.info("==> getDataIDisPresent query :: "+query);
         //list = query.list();
         HrPayLocMpgList=query.list();
         HrPayLocComMpg hrpayLocComMpg=null;
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgList.size());
         
         if(HrPayLocMpgList.size()!=0)
         {
        	 logger.info("====> list of size :: "+HrPayLocMpgList.size());
         }
         if(list!=null&&list.size()>0)
         {
        	 hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
         }
		// TODO Auto-generated method stub
		return HrPayLocMpgList;
	}
	/*public List getDataAllowChcked(long locId) 
	{
		
		 List  list = null;
	     List HrPayLocMpgAllowList = null;
	     
		 Session hibSession = getSession();
         
         long AllowID=2500134;
         logger.info("in getDataAllowChcked :: "+locId+"AllowId :: "+AllowID);
        // long DeductID=300135;
         StringBuffer HrPayloc = new StringBuffer();
        // String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1;

         String strQuery = "select mpg from HrPayLocComMpg mpg, HrPayCompGrpMst mst where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1
         					+" and mpg.hrpaycompgrpmst.compoGrpId=mst.compoGrpId and mst.isactive=1";
         
         Query query = hibSession.createQuery(strQuery);
         logger.info("getDataAllowChcked queryqueryquery  ::" + query);
         HrPayLocMpgAllowList = query.list();
         
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}*/
/*	public List getDataDeductChcked(long locId) 
	{
		
		 List  list = null;
	     List HrPayLocMpgDeductList = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");
         //long AllowID=300134;
         long DeductID=2500135;
         
         StringBuffer HrPayloc = new StringBuffer();
         //String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+DeductID+"and mpg.isactive="+1;

         String strQuery = "select mpg from HrPayLocComMpg mpg, HrPayCompGrpMst mst where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+DeductID+" and mpg.isactive="+1
         					+" and mpg.hrpaycompgrpmst.compoGrpId=mst.compoGrpId and mst.isactive=1";
       
         
         Query query = hibSession.createQuery(strQuery);
         logger.info("getDataDeductChcked queryqueryquery  ::" + query);
         HrPayLocMpgDeductList = query.list();
         
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
       
		return HrPayLocMpgDeductList;
	}*/
	
	/*public List<HrPayLocComMpg> getAllActiveDataByLoc(long locId)
	{
	     List HrPayLocMpgAllowList = null;
		 Session hibSession = getSession();
         long AllowID=2500134;
         String strQuery = "from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and mpg.isactive="+1+" and mpg.hrpaycompgrpmst.isactive="+1;;
         Query query = hibSession.createQuery(strQuery);
         HrPayLocMpgAllowList = query.list();
         
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}*/
	/*public List<HrPayCompGrpMst> getMstDataIsPresent(long month,long year,long locid)
	{
		List HrPayMstData=null;
		Session hibsession = getSession();
		String HQL_QUERY = "select hrPayComMst from HrPayCompGrpMst hrPayComMst where hrPayComMst.month="+month+""
							+" and hrPayComMst.year="+year+""
							+" and hrPayComMst.cmnLocationMst.locId="+locid;
		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getMstDataIsPresent query :: "+query);
		
		HrPayMstData = query.list();
		logger.info("==> HrPayMstData :: "+HrPayMstData.size());
		return HrPayMstData;
	}*/
	
	/*public List<HrPayCompGrpMst> getMstDataFromLocID(long locid)
	{
		List HrPayMstData=null;
		Session hibsession = getSession();
		String HQL_QUERY = "select hrPayComMst from HrPayCompGrpMst hrPayComMst where hrPayComMst.cmnLocationMst.locId="+locid;
				
		Query query = hibsession.createQuery(HQL_QUERY);
		logger.info("==> getMstDataFromLocID query :: "+query);
		
		HrPayMstData = query.list();
		logger.info("==> getMstDataFromLocID :: "+HrPayMstData.size());
		return HrPayMstData;
	}*/
	/*public List getDataAllowChckedForMonthYear(long locId,long month,long year) 
	{
		
		 List  list = null;
	     List<HrPayLocComMpg> HrPayLocMpgAllowList = null;
	     
		 Session hibSession = getSession();
         
         long AllowID=2500134;
        // long DeductID=300135;
         StringBuffer HrPayloc = new StringBuffer();
         String strQuery = "select mpg from HrPayLocComMpg mpg,HrPayCompGrpMst hrpaycompgrpmst where hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1 ;
         
         StringBuffer query = new StringBuffer(" from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
         query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
         query.append("  or  ");
         query.append(" ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<"+year+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<2011))) and a.cmnLocationMst.locId =  "+locId);
         query.append(" ) and loc.isactive=1 and loc.cmnLookupMst.lookupId =   "+AllowID);
         
         
         
        StringBuffer query =  new StringBuffer("SELECT compo_id FROM HR_PAY_LOC_COMPONENT_MPG loc where loc.COMPO_GRP_ID =(");
        		 query.append("SELECT max(COMPO_GROUP_ID) FROM HR_PAY_COMPONENT_GRP_MST where (grp_month=(select max(a.grp_month) from HR_PAY_COMPONENT_GRP_MST a where a.GRP_MONTH<="+month+" and grp_year="+year+") and grp_year="+year+")" );
        		 query.append(" or   ( grp_year=(select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+" )"); 
        		 query.append(" and grp_month = (select max(c.grp_month) from HR_PAY_COMPONENT_GRP_MST c where c.grp_year= (select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+")))");
        		 query.append(" and loc_id = "+locId);
        		 query.append(" ) and loc.ISACTIVE =1 and loc.COMPO_TYPE =  "+AllowID);
        
        		 
         Query que = hibSession.createQuery(query.toString());
         //logger.info("getDataAllowChcked queryqueryquery  ::" + query);
         HrPayLocMpgAllowList = que.list();
         
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}*/
	/*public List getDataDeductChckedForMonthYear(long locId,long month,long year) 
	{
		
		 List  list = null;
	     List<HrPayLocComMpg> HrPayLocMpgDeductList = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");
         //long AllowID=300134;
         long DeductID=2500135;
         
         StringBuffer HrPayloc = new StringBuffer();
         
         
         
         StringBuffer query = new StringBuffer(" from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
         query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
         query.append("  or  ");
         query.append(" ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<"+year+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(e.year) from HrPayCompGrpMst as e where e.year<"+year+"))) and a.cmnLocationMst.locId =  "+locId);
         query.append(" ) and loc.isactive=1 and loc.cmnLookupMst.lookupId =   "+DeductID);
       
         StringBuffer query =  new StringBuffer("SELECT compo_id FROM HR_PAY_LOC_COMPONENT_MPG loc where loc.COMPO_GRP_ID =(");
		 query.append("SELECT max(COMPO_GROUP_ID) FROM HR_PAY_COMPONENT_GRP_MST where (grp_month=(select max(a.grp_month) from HR_PAY_COMPONENT_GRP_MST a where a.GRP_MONTH<="+month+" and grp_year="+year+") and grp_year="+year+")" );
		 query.append(" or   ( grp_year=(select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+" )"); 
		 query.append(" and grp_month = (select max(c.grp_month) from HR_PAY_COMPONENT_GRP_MST c where c.grp_year= (select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+")))");
		 query.append(" and loc_id = "+locId);
		 query.append(" ) and loc.ISACTIVE =1 and loc.COMPO_TYPE =  "+DeductID);
         
         
         Query que = hibSession.createQuery(query.toString());
         //logger.info("getDataDeductChcked queryqueryquery  ::" + query);
         HrPayLocMpgDeductList = que.list();
         
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
       
		return HrPayLocMpgDeductList;
	}*/
	
	

/*	public List getActiveDeducComponents(long locId)
	    {
	        Criteria objCrt = null;
	        List<Long>  list = null;
	            Session hibSession = getSession();
	            String strQuery = "select mpg.compId  from HrPayLocComMpg as mpg where mpg.isactive =1 and mpg.cmnLookupMst.lookupId=2500135  and mpg.hrpaycompgrpmst.isactive=1 and mpg.hrpaycompgrpmst.cmnLocationMst.locId=" + locId;
	            Query query = hibSession.createQuery(strQuery);
	            list = query.list();
	            
	        return list;
	    }*/
	
	/*public List getActiveAllComponents(long locId)
    {
        Criteria objCrt = null;
        List<Long>  list = null;
            Session hibSession = getSession();
            String strQuery = "select mpg.compId   from HrPayLocComMpg as mpg where mpg.isactive =1 and mpg.cmnLookupMst.lookupId=2500134 and mpg.hrpaycompgrpmst.isactive=1 and mpg.hrpaycompgrpmst.cmnLocationMst.locId=" + locId;
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
            
        return list;
    }*/
	
	
/*	public List getDataLoanChckedForMonthYear(long locId,long month,long year)
	{
		
		 List  list = null;
	     List<HrPayLocComMpg> HrPayLocMpgDeductList = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");
         //long AllowID=300134;
         long DeductID=2500135;
         
         StringBuffer HrPayloc = new StringBuffer();
         
		
		StringBuffer query = new StringBuffer(" from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
        query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
        query.append("  or  ");
        query.append(" ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<"+year+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(e.year) from HrPayCompGrpMst as e where e.year<"+year+"))) and a.cmnLocationMst.locId =  "+locId);
        query.append(" ) and loc.isactive=1 and loc.cmnLookupMst.lookupId =   "+2500136);
        
        Query que = hibSession.createQuery(query.toString());
        //logger.info("getDataDeductChcked queryqueryquery  ::" + query);
        HrPayLocMpgDeductList = que.list();
        
        logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
      
		return HrPayLocMpgDeductList;
		
	}*/
	
	
	
	public List<HrPayLocComMpg> getAllActiveEditableAllowByLoc(long locId,long commission)
	{
		 List<HrPayLocComMpg>  list = null;
	     List HrPayLocMpgAllowList = null;
		 Session hibSession = getSession();
        // logger.info("going to execute query....");
        // long AllowID=2500134;
        // long DeductID=300135;
         StringBuffer HrPayloc = new StringBuffer();
         //select *  from HR_PAY_LOC_COMPONENT_MPG mpg  where mpg.loc_Id=300022  and mpg.isactive=1  and mpg.COMPO_TYPE=2500134 AND COMPO_TYPE NOT IN (SELECT ALLOW_ID FROM HR_PAY_EXPRESSION_MST )
         
      //   String strQuery = "from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500134 and mpg.compId not in (select exp.hrPayAllowTypeMst.allowCode from HrPayExpressionMst as exp where exp.hrPayCommissionMst.id = "+commission+")"   ;
        
         //String strQuery = "from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500134 and mpg.compId not in (select exp.hrPayAllowTypeMst.allowCode from HrPayExpressionMst as exp where (exp.hrPayAllowTypeMst.payCommissionId ="+commission+" or (exp.hrPayAllowTypeMst.payCommissionId!= -1 and  exp.hrPayCommissionMst.id !="+commission+")  ))";
         String strQuery = "from HrPayLocComMpg as mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and  mpg.hrpaycompgrpmst.isactive=1  and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500134 and mpg.compId not in ( select exp.hrPayAllowTypeMst.allowCode from HrPayExpressionMst exp where (exp.hrPayCommissionMst.id ="+commission+" or (exp.hrPayAllowTypeMst.payCommissionId !=-1 And exp.hrPayCommissionMst.id != "+commission+")  ))";
       /*  select * from HR_PAY_LOC_COMPONENT_MPG mpg where mpg.LOC_ID = 300022
         and mpg.ISACTIVE = 1 and mpg.COMPO_TYPE = 2500134 and COMPO_ID not in 
         (

         	select exp.ALLOW_ID from HR_PAY_EXPRESSION_MST exp , HR_PAY_ALLOW_TYPE_MST mst where mst.ALLOW_CODE = exp.ALLOW_ID
         	and (exp.PAY_COMMISSION_ID= 2500340 or (mst.PAY_COMMISSION_ID != -1 and  exp.PAY_COMMISSION_ID!=2500340))

         )*/
         Query query = hibSession.createQuery(strQuery);
         logger.info("getDataAllowChcked queryqueryquery allowance " + query);
         HrPayLocMpgAllowList = query.list();
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
         logger.info("===> ind dao list.size() :: for allowances "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}
	
	
	public List<HrPayLocComMpg> getAllActiveEditableDeducByLoc(long locId,long commission)
	{
		 List<HrPayLocComMpg>  list = null;
	     List HrPayLocMpgAllowList = null;
		 Session hibSession = getSession();
        // logger.info("going to execute query....");
         //long AllowID=2500134;
        // long DeductID=300135;
         StringBuffer HrPayloc = new StringBuffer();
         //select *  from HR_PAY_LOC_COMPONENT_MPG mpg  where mpg.loc_Id=300022  and mpg.isactive=1  and mpg.COMPO_TYPE=2500134 AND COMPO_TYPE NOT IN (SELECT ALLOW_ID FROM HR_PAY_EXPRESSION_MST )
        // String strQuery = " from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst exp where exp.hrPayCommissionMst.id = "+commission+")"  ;
         //String strQuery = " from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst exp where exp.hrPayCommissionMst.id = "+commission+" and exp.hrPayDeducTypeMst.payCommissionId in("+commission+",-1)  )"  ;
         //String strQuery="select * from HR_PAY_LOC_COMPONENT_MPG mpg where mpg.LOC_ID = "+locId+" and mpg.ISACTIVE = 1 and mpg.COMPO_TYPE = 2500134 and COMPO_ID not in (select exp.ALLOW_ID from HR_PAY_EXPRESSION_MST exp , HR_PAY_ALLOW_TYPE_MST mst where mst.ALLOW_CODE = exp.ALLOW_ID and (exp.PAY_COMMISSION_ID= "+commission+" or (mst.PAY_COMMISSION_ID != -1 and  exp.PAY_COMMISSION_ID!="+commission+")) )";
         
         String strQuery = "from HrPayLocComMpg as mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and mpg.hrpaycompgrpmst.isactive=1 and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst as exp where exp.hrPayCommissionMst.id ="+commission+" or (exp.hrPayDeducTypeMst.payCommissionId!= -1 and  exp.hrPayCommissionMst.id !="+commission+"))";
         logger.info("query for editable deduction");
         Query query = hibSession.createQuery(strQuery);
         logger.info("getDataAllowChcked queryqueryquery  manish" + query);
         HrPayLocMpgAllowList = query.list();
         logger.info("===> ind dao list.size() ::  for deductions"+HrPayLocMpgAllowList.size());
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}

	
	
	public List<HrEisEmpCompMpg> getAllActiveEditableDeducByEmpId(long locId,long commission,long empId,int month,int year )
	{
		 List<HrEisEmpCompMpg>  list = null;
	     List<HrEisEmpCompMpg> HrPayLocMpgAllowList = null;
		 Session hibSession = getSession();
        // logger.info("going to execute query....");
         //long AllowID=2500134;
        // long DeductID=300135;
         StringBuffer HrPayloc = new StringBuffer();
         //select *  from HR_PAY_LOC_COMPONENT_MPG mpg  where mpg.loc_Id=300022  and mpg.isactive=1  and mpg.COMPO_TYPE=2500134 AND COMPO_TYPE NOT IN (SELECT ALLOW_ID FROM HR_PAY_EXPRESSION_MST )
        // String strQuery = " from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst exp where exp.hrPayCommissionMst.id = "+commission+")"  ;
         //String strQuery = " from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst exp where exp.hrPayCommissionMst.id = "+commission+" and exp.hrPayDeducTypeMst.payCommissionId in("+commission+",-1)  )"  ;
         //String strQuery="select * from HR_PAY_LOC_COMPONENT_MPG mpg where mpg.LOC_ID = "+locId+" and mpg.ISACTIVE = 1 and mpg.COMPO_TYPE = 2500134 and COMPO_ID not in (select exp.ALLOW_ID from HR_PAY_EXPRESSION_MST exp , HR_PAY_ALLOW_TYPE_MST mst where mst.ALLOW_CODE = exp.ALLOW_ID and (exp.PAY_COMMISSION_ID= "+commission+" or (mst.PAY_COMMISSION_ID != -1 and  exp.PAY_COMMISSION_ID!="+commission+")) )";
      
          
         //String strQuery = "from HrPayLocComMpg as mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and mpg.hrpaycompgrpmst.isactive=1 and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst as exp where exp.hrPayCommissionMst.id ="+commission+" or (exp.hrPayDeducTypeMst.payCommissionId!= -1 and  exp.hrPayCommissionMst.id !="+commission+"))";
         String strQuery = " from HrEisEmpCompMpg as compoMpg where compoMpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId =  "+empId+" and  compoMpg.isactive =1  and compoMpg.cmnLookupMst.lookupId=2500135 and compoMpg.compId not in "+
         					"(select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst as exp where exp.hrPayCommissionMst.id ="+commission+" or (exp.hrPayDeducTypeMst.payCommissionId!= -1 and  exp.hrPayCommissionMst.id !="+commission+"))"+
         					" and compoMpg.hrEisEmpCompGrpMst.EmpCompGrpId in ( "+
         					" select max(a.EmpCompGrpId) from HrEisEmpCompGrpMst as a where a.hrEisEmpMst.empId ="+empId+" and (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "+
         					" or  "+
         					" (a.hrEisEmpMst.empId ="+empId+" and  ( a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<="+year+" and c.hrEisEmpMst.empId ="+empId+" and c.isactive=1 " +
         							" ) and a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<="+year+" and e.hrEisEmpMst.empId ="+empId+" and e.isactive=1) and d.hrEisEmpMst.empId ="+empId+"))))";
         					//and "+empId+"   ) 
         logger.info("query for editable deduction");
         Query query = hibSession.createQuery(strQuery);
         logger.info("Manish here ::::::::: above query");
         HrPayLocMpgAllowList = query.list();
         logger.info("===> ind dao list.size() ::  for deductions"+HrPayLocMpgAllowList.size());
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}
	
	
	public List<HrEisEmpCompMpg> getAllActiveEditableAllowByEmpId(long locId,long commission,long empId,int month,int year )
	{
		 List<HrEisEmpCompMpg>  list = null;
	     List<HrEisEmpCompMpg> HrPayLocMpgAllowList = null;
		 Session hibSession = getSession();
        // logger.info("going to execute query....");
         //long AllowID=2500134;
        // long DeductID=300135;
         StringBuffer HrPayloc = new StringBuffer();
         //select *  from HR_PAY_LOC_COMPONENT_MPG mpg  where mpg.loc_Id=300022  and mpg.isactive=1  and mpg.COMPO_TYPE=2500134 AND COMPO_TYPE NOT IN (SELECT ALLOW_ID FROM HR_PAY_EXPRESSION_MST )
        // String strQuery = " from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst exp where exp.hrPayCommissionMst.id = "+commission+")"  ;
         //String strQuery = " from HrPayLocComMpg as mpg where mpg.cmnLocationMst.locId="+locId +" and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst exp where exp.hrPayCommissionMst.id = "+commission+" and exp.hrPayDeducTypeMst.payCommissionId in("+commission+",-1)  )"  ;
         //String strQuery="select * from HR_PAY_LOC_COMPONENT_MPG mpg where mpg.LOC_ID = "+locId+" and mpg.ISACTIVE = 1 and mpg.COMPO_TYPE = 2500134 and COMPO_ID not in (select exp.ALLOW_ID from HR_PAY_EXPRESSION_MST exp , HR_PAY_ALLOW_TYPE_MST mst where mst.ALLOW_CODE = exp.ALLOW_ID and (exp.PAY_COMMISSION_ID= "+commission+" or (mst.PAY_COMMISSION_ID != -1 and  exp.PAY_COMMISSION_ID!="+commission+")) )";
      
          
         //String strQuery = "from HrPayLocComMpg as mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and mpg.hrpaycompgrpmst.isactive=1 and mpg.isactive="+1 +" and mpg.cmnLookupMst.lookupId=2500135 and mpg.compId not in (select exp.hrPayDeducTypeMst.deducCode from HrDeducExpMst as exp where exp.hrPayCommissionMst.id ="+commission+" or (exp.hrPayDeducTypeMst.payCommissionId!= -1 and  exp.hrPayCommissionMst.id !="+commission+"))";
         String strQuery = " from HrEisEmpCompMpg as compoMpg where compoMpg.hrEisEmpCompGrpMst.hrEisEmpMst.empId =  "+empId+" and  compoMpg.isactive =1 and compoMpg.hrEisEmpCompGrpMst.isactive=1 and compoMpg.cmnLookupMst.lookupId=2500134 and compoMpg.compId not in "+
         					"(select exp.hrPayAllowTypeMst.allowCode from HrPayExpressionMst as exp where exp.hrPayCommissionMst.id ="+commission+" or (exp.hrPayAllowTypeMst.payCommissionId!= -1 and  exp.hrPayCommissionMst.id !="+commission+"))"+
         					" and compoMpg.hrEisEmpCompGrpMst.EmpCompGrpId in ( "+
         					" select max(a.EmpCompGrpId) from HrEisEmpCompGrpMst as a where a.hrEisEmpMst.empId ="+empId+" and  (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "+
         					" or  "+
         					"  (a.hrEisEmpMst.empId ="+empId+" and  a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<="+year+" and c.isactive=1 and c.hrEisEmpMst.empId ="+empId+" ) and " +
         							" a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<="+year+"  and e.isactive=1 and e.hrEisEmpMst.empId ="+empId+")and d.hrEisEmpMst.empId ="+empId+"))))";
         					 
         logger.info("query for editable getAllActiveEditableAllowByEmpId");
         
         logger.info("Manish here :::::::::getAllActiveEditableAllowByEmpId");
         Query query = hibSession.createQuery(strQuery);
          // and a.orgEmpMst.empId ="+empId+"   )
         HrPayLocMpgAllowList = query.list();
         logger.info("===> ind dao list.size() ::  for getAllActiveEditableAllowByEmpId "+HrPayLocMpgAllowList.size());
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
       
		return HrPayLocMpgAllowList;
	}

public HrPayLocComMpg getDataIDisPresent(long locId,long compId)
	{
		 List  list = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");

         String strQuery = "from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.compId="+compId;
         Query query = hibSession.createQuery(strQuery);
         //Query query = hibSession.createQuery("from HrEisBranchMst");
         logger.info("HrPayLocComMpg queryqueryquery  " + query);
         list = query.list();
         HrPayLocComMpg hrpayLocComMpg=null;
         
         
         if(list!=null&&list.size()>0)
         {
        	 hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
         }
        // HrPayLocComMpg hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
         return hrpayLocComMpg;
         //logger.info("List size in DAO:-->" + list.size());
         
	}


//20 jan 2012 
/*public List getAllActiveEditableAllowance(String empIdStr,long locId)
{
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	List allowanceList =new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append(rb.getString("sql_AllEditableAllowanc_1"));
    sb.append(rb.getString("sql_AllEditableAllowanc_2"));
    sb.append(empIdStr);
    sb.append(rb.getString("sql_AllEditableAllowanc_4"));
    sb.append(empIdStr);
    sb.append(rb.getString("sql_AllEditableAllowanc_6"));
    sb.append(rb.getString("sql_AllEditableAllowanc_7"));
    sb.append(empIdStr);
    sb.append(rb.getString("sql_AllEditableAllowanc_9"));
    
    logger.info("Query for getting all editiable allowance "+sb.toString());
    logger.info("Location Id is"+locId);
    Query query= hibSession.createSQLQuery(sb.toString());
    query.setParameter("locId", locId);
    allowanceList = query.list();
    return allowanceList;
}
*/
public List getAllActiveEditableAllowance(String empIdStr,long locId,long billNo,Date givenDate)
{
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	List allowanceList =new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    sb.append(rb.getString("sql_AllEditableAllowanc_1"));
    sb.append(rb.getString("getEmployeeList"));
    sb.append(rb.getString("sql_AllEditableAllowanc_2"));
    sb.append(rb.getString("sql_AllEditableAllowanc_3"));
    sb.append(rb.getString("sql_AllEditableAllowanc_4"));
    sb.append(rb.getString("sql_AllEditableAllowanc_5"));
    sb.append(rb.getString("sql_AllEditableAllowanc_6"));
    sb.append(rb.getString("sql_AllEditableAllowanc_7"));
    sb.append(rb.getString("sql_AllEditableAllowanc_8"));
    sb.append(rb.getString("sql_AllEditableAllowanc_9"));
    sb.append(rb.getString("sql_AllEditableAllowanc_10"));
    sb.append(rb.getString("sql_AllEditableAllowanc_11"));
    
    logger.info("Query for getting all editiable allowance "+sb.toString());
    logger.info("Location Id is"+locId);
    Query query= hibSession.createSQLQuery(sb.toString());
    query.setParameter("locId", locId);
    query.setParameter("billNo", billNo);
    query.setParameter("givenDate", givenDate);
    allowanceList = query.list();
    return allowanceList;
}/*
public List getAllActiveEditableDeduction(String empIdStr,long locId)
{
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	List deductionList =new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    
    sb.append(rb.getString("sql_AllEditaleDeduction_1"));
    sb.append(rb.getString("sql_AllEditaleDeduction_2"));
    sb.append(empIdStr);
    sb.append(rb.getString("sql_AllEditaleDeduction_4"));
    sb.append(empIdStr);
    sb.append(rb.getString("sql_AllEditaleDeduction_6"));
    sb.append(rb.getString("sql_AllEditaleDeduction_7"));
    sb.append(empIdStr);
    sb.append(rb.getString("sql_AllEditaleDeduction_9"));
    
    logger.info("Query for getting all editiable deduction "+sb);
    logger.info("Location Id is"+locId);
    Query query= hibSession.createSQLQuery(sb.toString());
    query.setParameter("locId", locId);
    deductionList = query.list();
    return deductionList;
}*/
public List getAllActiveEditableDeduction(String empIdStr,long locId,long billNo,Date givenDate)
{
	ResourceBundle rb = ResourceBundle.getBundle("resources.Payroll");
	List deductionList =new ArrayList();
    Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
    
    sb.append(rb.getString("sql_AllEditaleDeduction_1"));
    sb.append(rb.getString("getEmployeeList"));
    sb.append(rb.getString("sql_AllEditaleDeduction_2"));
    sb.append(rb.getString("sql_AllEditaleDeduction_3"));
    sb.append(rb.getString("sql_AllEditaleDeduction_4"));
    sb.append(rb.getString("sql_AllEditaleDeduction_5"));
    sb.append(rb.getString("sql_AllEditaleDeduction_6"));
    sb.append(rb.getString("sql_AllEditaleDeduction_7"));
    sb.append(rb.getString("sql_AllEditaleDeduction_8"));
    sb.append(rb.getString("sql_AllEditaleDeduction_9"));
    sb.append(rb.getString("sql_AllEditaleDeduction_10"));
    sb.append(rb.getString("sql_AllEditaleDeduction_11"));
    
    logger.info("Query for getting all editiable deduction "+sb);
    logger.info("Location Id is"+locId);
    Query query= hibSession.createSQLQuery(sb.toString());
    query.setParameter("locId", locId);
    query.setParameter("billNo", billNo);
    query.setParameter("givenDate", givenDate);
    deductionList = query.list();
    return deductionList;
}

//20 jan 2012 end



/*public List getDataIDisPresent(String[] lArrallowList,String[] lArrDeductList,long hdncheckedvalueofAllow,long hdncheckedvalueofdeduct) 
	{
		
		 List  list = null;
	     List HrPayLocMpgList = null;
		 Session hibSession = getSession();
         logger.info("going to execute query....");
         String AllowID="";
         String DeductID="";
       //  long lenth= lArrallowList.length;
        // logger.info("===> lenth :: "+lenth);
       //  logger.info("===> lenth :: "+(lenth-1));
       //  long len=(lenth-1);
         
         StringBuffer HrPayloc = new StringBuffer();
         HrPayloc.append("from HrPayLocComMpg mpg where mpg.compId not in(");
        // String strQuery = "from HrPayLocComMpg mpg where mpg.compId not in(";
         logger.info("==> hdncheckedvalueofAllow :: "+hdncheckedvalueofAllow);
        if(hdncheckedvalueofAllow!=0)
        {
         for(int i=0;i<hdncheckedvalueofAllow;i++)
         {
        	 AllowID =lArrallowList[i];
        	 logger.info("==> in dao AllowID :: "+AllowID);
        	 if(i==(hdncheckedvalueofAllow-1))
        	 {
        		 HrPayloc.append(AllowID);
        	 }
        	 else
        	 {
        		 logger.info("====> in ,,, ");
        		 HrPayloc.append(AllowID);
        		 HrPayloc.append(",");
        	 }
         }
        }
        
       
        
        logger.info("==> hdncheckedvalueofdeduct :: "+hdncheckedvalueofdeduct);
        if(hdncheckedvalueofdeduct!=0)
        {
         HrPayloc.append(",");
         
         for(int i=0;i<hdncheckedvalueofdeduct;i++)
         {
        	 DeductID =lArrDeductList[i];
        	 logger.info("==> in dao DeductID :: "+DeductID);
        	 if(i==(hdncheckedvalueofdeduct-1))
        	 {
        		 logger.info("====> in if ");
        		 HrPayloc.append(DeductID);
        	 }
        	 else
        	 {
        		 logger.info("====> in ,,, ");
        		 HrPayloc.append(DeductID);
        		 HrPayloc.append(",");
        	 }
         }
        }
         HrPayloc.append(")");
         HrPayloc.append("and mpg.isactive="+1);
         
         logger.info("====> HrPayloc :: "+HrPayloc);
         
         Query query = hibSession.createQuery(HrPayloc.toString());
         logger.info("====> in Dao query :: "+query);
         //list = query.list();
         HrPayLocMpgList=query.list();
         //HrPayLocComMpg hrpayLocComMpg=null;
         logger.info("===> ind dao list.size() :: "+HrPayLocMpgList.size());
         
         if(HrPayLocMpgList.size()!=0)
         {
        	 logger.info("====> list of size :: "+HrPayLocMpgList.size());
         }
         if(list!=null&&list.size()>0)
         {
        	 hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
         }
		// TODO Auto-generated method stub
		return HrPayLocMpgList;
	}*/
//*********************
public List getLocIds(long postId)
{
    //Criteria objCrt = null;
    List  list = null;
        Session hibSession = getSession();
        logger.info("going to execute query....");

        String strQuery = "from OrgPostDetailsRlt rlt where rlt.orgPostMst.postId ="  + postId;
        Query query = hibSession.createQuery(strQuery);
        //Query query = hibSession.createQuery("from HrEisBranchMst");
        logger.info("OrgSchemeMstDAOImpl queryqueryquery  " + query);
        list = query.list();
        logger.info("List size in DAO:-->" + list.size());
    return list;
}
public HrPayLocComMpg getDataIDisPresent(long locId,long compId,long grpid,long ID)
{
	 List  list = null;
	 Session hibSession = getSession();
     logger.info("going to execute query....");

     String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.compId="+compId+" and mpg.hrpaycompgrpmst.compoGrpId="+grpid+""
     +" and mpg.cmnLookupMst.lookupId="+ID;
     
     Query query = hibSession.createQuery(strQuery);
     //Query query = hibSession.createQuery("from HrEisBranchMst");
     logger.info("HrPayLocComMpg queryqueryquery  " + query);
     //list = query.list();
     HrPayLocComMpg hrPayLocComMpg=null;
     hrPayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
     
    /* if(list!=null&&list.size()>0)
     {
    	 hrPayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
     }*/
    // HrPayLocComMpg hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
     return hrPayLocComMpg;
     //logger.info("List size in DAO:-->" + list.size());
     
}

/*public List getDataIDisPresent(String[] lArrallowList,String[] lArrDeductList,String[] lArrLoanList,long hdncheckedvalueofAllow,long hdncheckedvalueofdeduct,long hdncheckedSizeofLoan,long grpId) 
{
	
	 List  list = null;
     List HrPayLocMpgList = null;
	 Session hibSession = getSession();
     logger.info("going to execute query....");
     logger.info("Query to deActivate Compos");
     String AllowID="";
     String DeductID="";
     String LoanID="";
     
   //  long lenth= lArrallowList.length;
    // logger.info("===> lenth :: "+lenth);
   //  logger.info("===> lenth :: "+(lenth-1));
   //  long len=(lenth-1);
     
     StringBuffer HrPayloc = new StringBuffer();
     HrPayloc.append("select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.compoGrpId="+grpId+" and mpg.compId not in(");
    // String strQuery = "from HrPayLocComMpg mpg where mpg.compId not in(";
     logger.info("==> hdncheckedvalueofAllow :: "+hdncheckedvalueofAllow);
    if(hdncheckedvalueofAllow!=0)
    {
     for(int i=0;i<hdncheckedvalueofAllow;i++)
     {
    	 AllowID =lArrallowList[i];
    	 logger.info("==> in dao AllowID :: "+AllowID);
    	 if(i==(hdncheckedvalueofAllow-1))
    	 {
    		 HrPayloc.append(AllowID);
    	 }
    	 else
    	 {
    		 logger.info("====> in ,,, ");
    		 HrPayloc.append(AllowID);
    		 HrPayloc.append(",");
    	 }
     }
    }
    
   
    
    logger.info("==> hdncheckedvalueofdeduct :: "+hdncheckedvalueofdeduct);
    if(hdncheckedvalueofdeduct!=0)
    {
     HrPayloc.append(",");
     
     for(int i=0;i<hdncheckedvalueofdeduct;i++)
     {
    	 DeductID =lArrDeductList[i];
    	 logger.info("==> in dao DeductID :: "+DeductID);
    	 if(i==(hdncheckedvalueofdeduct-1))
    	 {
    		 logger.info("====> in if ");
    		 HrPayloc.append(DeductID);
    	 }
    	 else
    	 {
    		 logger.info("====> in ,,, ");
    		 HrPayloc.append(DeductID);
    		 HrPayloc.append(",");
    	 }
     }
    }
    
    if(hdncheckedSizeofLoan!=0)
    {
     HrPayloc.append(",");
     
     for(int i=0;i<hdncheckedSizeofLoan;i++)
     {
    	 LoanID =lArrLoanList[i];
    	 logger.info("==> in dao LoanID :: "+LoanID);
    	 if(i==(hdncheckedSizeofLoan-1))
    	 {
    		 logger.info("====> in if ");
    		 HrPayloc.append(LoanID);
    	 }
    	 else
    	 {
    		 logger.info("====> in ,,, ");
    		 HrPayloc.append(LoanID);
    		 HrPayloc.append(",");
    	 }
     }
    }
    
     HrPayloc.append(")");
     HrPayloc.append("and mpg.hrpaycompgrpmst.isactive="+1);
     
     logger.info("====> HrPayloc :: "+HrPayloc);
     
     Query query = hibSession.createQuery(HrPayloc.toString());
     logger.info("====> in Dao query :: "+query);
     logger.info("==> getDataIDisPresent query :: "+query);
     //list = query.list();
     HrPayLocMpgList=query.list();
     //HrPayLocComMpg hrpayLocComMpg=null;
     logger.info("===> ind dao list.size() :: "+HrPayLocMpgList.size());
     
     if(HrPayLocMpgList.size()!=0)
     {
    	 logger.info("====> list of size :: "+HrPayLocMpgList.size());
     }
     if(list!=null&&list.size()>0)
     {
    	 hrpayLocComMpg = (HrPayLocComMpg)query.uniqueResult();
     }
	// TODO Auto-generated method stub
	return HrPayLocMpgList;
}*/
//Methods to deactivate dept components By Amish
public List getDataIDisPresentForDed(String[] lArrDeductList,long hdncheckedvalueofdeduct,long grpId)
{
	List  list = null;
    List HrPayLocMpgList = null;
	 Session hibSession = getSession();
    logger.info("going to execute deduc query....");
    logger.info("Query to deActivate Compos");
    
    String DeductID="";
    
    StringBuffer HrPayloc = new StringBuffer();
    HrPayloc.append("select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.compoGrpId="+grpId+" and mpg.compId not in(");
    
    logger.info("==> hdncheckedvalueofdeduct :: "+hdncheckedvalueofdeduct);
    if(hdncheckedvalueofdeduct!=0)
    {
    	for(int i=0;i<hdncheckedvalueofdeduct;i++)
    	{
    		DeductID =lArrDeductList[i];
    		logger.info("==> in dao DeductID :: "+DeductID);
    		if(i==(hdncheckedvalueofdeduct-1))
    		{
    			logger.info("====> in if ");
    			HrPayloc.append(DeductID);
    		}
    		else
    		{
    			logger.info("====> in ,,, ");
    			HrPayloc.append(DeductID);
    			HrPayloc.append(",");
    		}
    	}
    }
    
    HrPayloc.append(")");
    HrPayloc.append("and mpg.cmnLookupMst.lookupId = 2500135 and mpg.hrpaycompgrpmst.isactive="+1);
    
    logger.info("====> HrPayloc :: "+HrPayloc);
    
    Query query = hibSession.createQuery(HrPayloc.toString());
    logger.info("====> in Dao query :: "+query);
    logger.info("==> getDataIDisPresent query :: "+query);
    
    HrPayLocMpgList=query.list();
    //HrPayLocComMpg hrpayLocComMpg=null;
    logger.info("===> ind dao list.size() :: "+HrPayLocMpgList.size());
    
    if(HrPayLocMpgList.size()!=0)
    {
   	 logger.info("====> list of size :: "+HrPayLocMpgList.size());
    }
    
    return HrPayLocMpgList;
    
}

public List getDataIDisPresentForAllow(String[] lArrallowList,long hdncheckedvalueofAllow,long grpId)
{
	List  list = null;
    List HrPayLocMpgList = null;
	 Session hibSession = getSession();
    logger.info("going to execute allow query....");
    logger.info("Query to deActivate Compos");
    
    String allowId="";
    
    StringBuffer HrPayloc = new StringBuffer();
    HrPayloc.append("select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.compoGrpId="+grpId+" and mpg.compId not in(");
    
    logger.info("==> hdncheckedvalueofAllow :: "+hdncheckedvalueofAllow);
    if(hdncheckedvalueofAllow!=0)
    {
    	for(int i=0;i<hdncheckedvalueofAllow;i++)
    	{
    		allowId =lArrallowList[i];
    		logger.info("==> in dao allowId :: "+allowId);
    		if(i==(hdncheckedvalueofAllow-1))
    		{
    			logger.info("====> in if ");
    			HrPayloc.append(allowId);
    		}
    		else
    		{
    			logger.info("====> in ,,, ");
    			HrPayloc.append(allowId);
    			HrPayloc.append(",");
    		}
    	}
    }
    
    HrPayloc.append(")");
    HrPayloc.append("and mpg.cmnLookupMst.lookupId = 2500134 and mpg.hrpaycompgrpmst.isactive="+1);
    
    logger.info("====> HrPayloc :: "+HrPayloc);
    
    Query query = hibSession.createQuery(HrPayloc.toString());
    logger.info("====> in Dao query :: "+query);
    logger.info("==> getDataIDisPresent query :: "+query);
    
    HrPayLocMpgList=query.list();
    //HrPayLocComMpg hrpayLocComMpg=null;
    logger.info("===> ind dao list.size() :: "+HrPayLocMpgList.size());
    
    if(HrPayLocMpgList.size()!=0)
    {
   	 logger.info("====> list of size :: "+HrPayLocMpgList.size());
    }
    
    return HrPayLocMpgList;
    
}

public List getDataIDisPresentForLoan(String[] lArrLoanList,long hdncheckedSizeofLoan,long grpId)
{
	List  list = null;
    List HrPayLocMpgList = null;
	 Session hibSession = getSession();
    logger.info("going to execute loan query....");
    logger.info("Query to deActivate Compos");
    
    String loanid="";
    
    StringBuffer HrPayloc = new StringBuffer();
    HrPayloc.append("select mpg from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.compoGrpId="+grpId+" and mpg.compId not in(");
    
    logger.info("==> hdncheckedSizeofLoan :: "+hdncheckedSizeofLoan);
    if(hdncheckedSizeofLoan!=0)
    {
    	for(int i=0;i<hdncheckedSizeofLoan;i++)
    	{
    		loanid =lArrLoanList[i];
    		logger.info("==> in dao LoanId :: "+loanid);
    		if(i==(hdncheckedSizeofLoan-1))
    		{
    			logger.info("====> in if ");
    			HrPayloc.append(loanid);
    		}
    		else
    		{
    			logger.info("====> in ,,, ");
    			HrPayloc.append(loanid);
    			HrPayloc.append(",");
    		}
    	}
    }
    
    HrPayloc.append(")");
    HrPayloc.append("and mpg.cmnLookupMst.lookupId in (2500136,2500137) and mpg.hrpaycompgrpmst.isactive="+1);
    
    logger.info("====> HrPayloc :: "+HrPayloc);
    
    Query query = hibSession.createQuery(HrPayloc.toString());
    logger.info("====> in Dao query :: "+query);
    logger.info("==> getDataIDisPresent query :: "+query);
    
    HrPayLocMpgList=query.list();
    //HrPayLocComMpg hrpayLocComMpg=null;
    logger.info("===> ind dao list.size() :: "+HrPayLocMpgList.size());
    
    if(HrPayLocMpgList.size()!=0)
    {
   	 logger.info("====> list of size :: "+HrPayLocMpgList.size());
    }
    
    return HrPayLocMpgList;
    
}



//Methods End to deactivate dept components By Amish









public List getDataAllowChcked(long locId) 
{
	
	 List  list = null;
     List HrPayLocMpgAllowList = null;
     
	 Session hibSession = getSession();
     
     long AllowID=2500134;
     logger.info("in getDataAllowChcked :: "+locId+"AllowId :: "+AllowID);
    // long DeductID=300135;
     StringBuffer HrPayloc = new StringBuffer();
    // String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycomgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1;

     String strQuery = "select mpg from HrPayLocComMpg mpg, HrPayCompGrpMst mst where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1
     					+" and mpg.hrpaycompgrpmst.compoGrpId=mst.compoGrpId and mst.isactive=1";
     
     Query query = hibSession.createQuery(strQuery);
     logger.info("getDataAllowChcked queryqueryquery  ::" + query);
     HrPayLocMpgAllowList = query.list();
     
     logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
   
	return HrPayLocMpgAllowList;
}
public List getDataDeductChcked(long locId) 
{
	
	 List  list = null;
     List HrPayLocMpgDeductList = null;
	 Session hibSession = getSession();
     logger.info("going to execute query....");
     //long AllowID=300134;
     long DeductID=2500135;
     
     StringBuffer HrPayloc = new StringBuffer();
     //String strQuery = "select mpg from HrPayLocComMpg mpg where mpg.hrpaycomgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+DeductID+"and mpg.isactive="+1;

     String strQuery = "select mpg from HrPayLocComMpg mpg, HrPayCompGrpMst mst where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+DeductID+" and mpg.isactive="+1
     					+" and mpg.hrpaycompgrpmst.compoGrpId=mst.compoGrpId and mst.isactive=1";
   
     
     Query query = hibSession.createQuery(strQuery);
     logger.info("getDataDeductChcked queryqueryquery  ::" + query);
     HrPayLocMpgDeductList = query.list();
     
     logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
   
	return HrPayLocMpgDeductList;
}
public List getDataLoanChcked(long locId)
{
	 List  list = null;
     List HrPayLocMpgLoanList = null;
	 Session hibSession = getSession();
     long LoanID=2500136;
     long AdvID=2500137;
     StringBuffer HrPayloc = new StringBuffer();
     String strQuery = "select mpg from HrPayLocComMpg mpg, HrPayCompGrpMst mst where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId in ("+LoanID+","+AdvID+") and mpg.isactive="+1
     					+" and mpg.hrpaycompgrpmst.compoGrpId=mst.compoGrpId and mst.isactive=1";
   
     Query query = hibSession.createQuery(strQuery);
     logger.info("getDataLoanChcked queryqueryquery  ::" + query);
     HrPayLocMpgLoanList = query.list();
     
     logger.info("===> getDataLoanChcked ind dao list.size() :: "+HrPayLocMpgLoanList.size());
	return HrPayLocMpgLoanList;
}
public List<HrPayLocComMpg> getAllActiveDataByLoc(long locId)
{
     List HrPayLocMpgAllowList = null;
	 Session hibSession = getSession();
     long AllowID=2500134;
     String strQuery = "from HrPayLocComMpg mpg where mpg.hrpaycompgrpmst.cmnLocationMst.locId="+locId +" and mpg.isactive="+1+" and mpg.hrpaycompgrpmst.isactive="+1;;
     Query query = hibSession.createQuery(strQuery);
     HrPayLocMpgAllowList = query.list();
     
     logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
   
	return HrPayLocMpgAllowList;
}
public List<HrPayCompGrpMst> getMstDataIsPresent(long month,long year,long locid)
{
	List HrPayMstData=null;
	Session hibsession = getSession();
	String HQL_QUERY = "select hrPayComMst from HrPayCompGrpMst hrPayComMst where hrPayComMst.month="+month+""
						+" and hrPayComMst.year="+year+""
						+" and hrPayComMst.cmnLocationMst.locId="+locid;
	Query query = hibsession.createQuery(HQL_QUERY);
	logger.info("==> getMstDataIsPresent query :: "+query);
	
	HrPayMstData = query.list();
	logger.info("==> HrPayMstData :: "+HrPayMstData.size());
	return HrPayMstData;
}

public List<HrPayCompGrpMst> getMstDataFromLocID(long locid)
{
	List HrPayMstData=null;
	Session hibsession = getSession();
	String HQL_QUERY = "select hrPayComMst from HrPayCompGrpMst hrPayComMst where hrPayComMst.isactive=1  and hrPayComMst.cmnLocationMst.locId="+locid;
			
	Query query = hibsession.createQuery(HQL_QUERY);
	logger.info("==> getMstDataFromLocID query :: "+query);
	
	HrPayMstData = query.list();
	logger.info("==> getMstDataFromLocID :: "+HrPayMstData.size());
	return HrPayMstData;
}
public List getDataAllowChckedForMonthYear(long locId,long month,long year) 
{
	
	 List  list = null;
    List<HrPayLocComMpg> HrPayLocMpgAllowList = null;
    
	 Session hibSession = getSession();
    
    long AllowID=2500134;
   // long DeductID=300135;
    StringBuffer HrPayloc = new StringBuffer();
    /*String strQuery = "select mpg from HrPayLocComMpg mpg,HrPayCompGrpMst hrpaycompgrpmst where hrpaycompgrpmst.cmnLocationMst.locId="+locId+" and mpg.cmnLookupMst.lookupId="+AllowID+" and mpg.isactive="+1 ;*/
    
    StringBuffer query = new StringBuffer(" from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
    query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where a.isactive=1 and  a.cmnLocationMst.locId =  "+locId +" and (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
    query.append("  or  ");
    query.append(" ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+" and c.cmnLocationMst.locId =  "+locId +") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(r.year) from HrPayCompGrpMst r where r.year<="+year+" and r.cmnLocationMst.locId =  "+locId+") and d.cmnLocationMst.locId =  "+locId+")) ");
    query.append("  and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1 and loc.cmnLookupMst.lookupId =   "+AllowID);
    
    
    /*
   StringBuffer query =  new StringBuffer("SELECT compo_id FROM HR_PAY_LOC_COMPONENT_MPG loc where loc.COMPO_GRP_ID =(");
   		 query.append("SELECT max(COMPO_GROUP_ID) FROM HR_PAY_COMPONENT_GRP_MST where (grp_month=(select max(a.grp_month) from HR_PAY_COMPONENT_GRP_MST a where a.GRP_MONTH<="+month+" and grp_year="+year+") and grp_year="+year+")" );
   		 query.append(" or   ( grp_year=(select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+" )"); 
   		 query.append(" and grp_month = (select max(c.grp_month) from HR_PAY_COMPONENT_GRP_MST c where c.grp_year= (select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+")))");
   		 query.append(" and loc_id = "+locId);
   		 query.append(" ) and loc.ISACTIVE =1 and loc.COMPO_TYPE =  "+AllowID);
   */
   		 
    Query que = hibSession.createQuery(query.toString());
    //logger.info("getDataAllowChcked queryqueryquery  ::" + query);
    HrPayLocMpgAllowList = que.list();
    
    logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());
  
	return HrPayLocMpgAllowList;
}
public List getDataDeductChckedForMonthYear(long locId,long month,long year) 
{
	
	 List  list = null;
    List<HrPayLocComMpg> HrPayLocMpgDeductList = null;
	 Session hibSession = getSession();
    logger.info("going to execute query....");
    //long AllowID=300134;
    long DeductID=2500135;
    
    StringBuffer HrPayloc = new StringBuffer();
    
    
    
    StringBuffer query = new StringBuffer(" from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
    query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where a.isactive=1 and  a.cmnLocationMst.locId =  "+locId +" and (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
    query.append("  or  ");
    query.append(" ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+" and c.cmnLocationMst.locId =  "+locId +") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(r.year) from HrPayCompGrpMst r where r.year<="+year+" and r.cmnLocationMst.locId =  "+locId+") and d.cmnLocationMst.locId =  "+locId +")) ");
    query.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1 and loc.cmnLookupMst.lookupId =   "+DeductID);
  
    /*StringBuffer query =  new StringBuffer("SELECT compo_id FROM HR_PAY_LOC_COMPONENT_MPG loc where loc.COMPO_GRP_ID =(");
	 query.append("SELECT max(COMPO_GROUP_ID) FROM HR_PAY_COMPONENT_GRP_MST where (grp_month=(select max(a.grp_month) from HR_PAY_COMPONENT_GRP_MST a where a.GRP_MONTH<="+month+" and grp_year="+year+") and grp_year="+year+")" );
	 query.append(" or   ( grp_year=(select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+" )"); 
	 query.append(" and grp_month = (select max(c.grp_month) from HR_PAY_COMPONENT_GRP_MST c where c.grp_year= (select max(grp_year) from HR_PAY_COMPONENT_GRP_MST b where b.GRP_YEAR<"+year+")))");
	 query.append(" and loc_id = "+locId);
	 query.append(" ) and loc.ISACTIVE =1 and loc.COMPO_TYPE =  "+DeductID);*/
    
    
    Query que = hibSession.createQuery(query.toString());
    //logger.info("getDataDeductChcked queryqueryquery  ::" + query);
    HrPayLocMpgDeductList = que.list();
    
    logger.info("Going to execute the query for getDataDeductChckedForMonthYear"+que.toString());
    
    logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
  
	return HrPayLocMpgDeductList;
}



public List getActiveDeducComponents(long locId)
    {
       // Criteria objCrt = null;
        List<Long>  list = null;
            Session hibSession = getSession();
            String strQuery = "select mpg.compId  from HrPayLocComMpg as mpg where mpg.isactive =1 and mpg.cmnLookupMst.lookupId=2500135  and mpg.hrpaycompgrpmst.isactive=1 and mpg.hrpaycompgrpmst.cmnLocationMst.locId=" + locId;
            Query query = hibSession.createQuery(strQuery);
            list = query.list();
            
        return list;
    }
public List getActiveLoanComponents(long locId)
{
   // Criteria objCrt = null;
    List<Long>  list = null;
        Session hibSession = getSession();
        String strQuery = "select mpg.compId  from HrPayLocComMpg as mpg where mpg.isactive =1 and mpg.cmnLookupMst.lookupId=2500137  and mpg.hrpaycompgrpmst.isactive=1 and mpg.hrpaycompgrpmst.cmnLocationMst.locId=" + locId;
        Query query = hibSession.createQuery(strQuery);
        list = query.list();
        
    return list;
}

public List getActiveAdvanceComponents(long locId)
{
  //  Criteria objCrt = null;
    List<Long>  list = null;
        Session hibSession = getSession();
        String strQuery = "select mpg.compId  from HrPayLocComMpg as mpg where mpg.isactive =1 and mpg.cmnLookupMst.lookupId=2500136  and mpg.hrpaycompgrpmst.isactive=1 and mpg.hrpaycompgrpmst.cmnLocationMst.locId=" + locId;
        Query query = hibSession.createQuery(strQuery);
        list = query.list();
        
    return list;
}


public List getActiveAllComponents(long locId)
{
    //Criteria objCrt = null;
    List<Long>  list = null;
        Session hibSession = getSession();
        String strQuery = "select mpg.compId   from HrPayLocComMpg as mpg where mpg.isactive =1 and mpg.cmnLookupMst.lookupId=2500134 and mpg.hrpaycompgrpmst.isactive=1 and mpg.hrpaycompgrpmst.cmnLocationMst.locId=" + locId;
        Query query = hibSession.createQuery(strQuery);
        list = query.list();
        
    return list;
}


public List getDataLoanChckedForMonthYear(long locId,long month,long year)
{
	
	 List  list = null;
    List<HrPayLocComMpg> HrPayLocMpgDeductList = null;
	 Session hibSession = getSession();
    logger.info("going to execute query....");
    //long AllowID=300134;
    long DeductID=2500135;
    
    StringBuffer HrPayloc = new StringBuffer();
    logger.info("Check This Query...<>>>");
	
	StringBuffer query = new StringBuffer(" from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
   query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where a.isactive=1 and  a.cmnLocationMst.locId = "+locId+" and (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
   query.append("  or  ");
   query.append(" ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+" and c.cmnLocationMst.locId =  "+locId +") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(r.year) from HrPayCompGrpMst r where r.year<="+year+" and r.cmnLocationMst.locId =  "+locId+") and d.cmnLocationMst.locId = "+locId+")) " );
   //query.append(" ) and loc.isactive=1 and loc.cmnLookupMst.lookupId =   "+2500136);
   query.append("  and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1 and loc.cmnLookupMst.lookupId in(2500136,2500137)  order by loc.compId");
   
   Query que = hibSession.createQuery(query.toString());
   //logger.info("getDataDeductChcked queryqueryquery  ::" + query);
   HrPayLocMpgDeductList = que.list();
   
   logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
 
	return HrPayLocMpgDeductList;
	

	
}

/*public List getLoanAdvList()
{
	List LoanAdvList=new ArrayList();

    Session hibSession = getSession();
    String queryStr ="select * from HR_LOAN_ADV_MST mst where mst.LOAN_ADV_ID >= "+50;
    //String queryStr ="select mpg from HrPayEdpCompoMpg mpg ";

    Query query= hibSession.createSQLQuery(queryStr);
    LoanAdvList = query.list();
    logger.info("===> in getLoanAdvList :: "+query);
    
    return LoanAdvList;
}*/
public List getLoanAdvList()
{
	List LoanAdvList=new ArrayList();

    Session hibSession = getSession();
    String HQL_QUERY ="select mpg from HrPayEdpCompoMpg mpg where mpg.cmnLookupId in (2500136,2500137) order by mpg.rltBillTypeEdp.edpShortName";
    //String queryStr ="select mpg from HrPayEdpCompoMpg mpg ";

    Query query= hibSession.createQuery(HQL_QUERY);
    LoanAdvList = query.list();
    logger.info("===> in getLoanAdvList :: "+query);
    logger.info("===> in getLoanAdvList Size :: "+LoanAdvList.size());
    
    return LoanAdvList;
}
public long getCmnLookUpIdFromLoanId(String LoanId)
{
	long CmnlookupId=0;
	
	Session hibsession = getSession();
	String HQL_QUERY = "select mpg.cmnLookupId from HrPayEdpCompoMpg mpg where typeId="+LoanId+" and mpg.cmnLookupId in (2500136,2500137)";
	Query lQuery = hibsession.createQuery(HQL_QUERY);
	String res=lQuery.uniqueResult().toString();
    if(res!=null)
    	CmnlookupId=Long.parseLong(res);
    logger.info("===> in getCmnLookUpIdFromLoanId :: "+CmnlookupId);
		return CmnlookupId;
	
}




//japen
public List getActiveAllowDeptforSalaryDiff(long locId, long month,long year)
{
	Session hibSession = getSession();
	long prevMonth,prevYear;
	if(month!=1)
	{
		prevMonth = month-1;
		prevYear = year;
	}
	else
	{
		prevMonth=12;
		prevYear= year-1;
	}
	
	
	
     
	
	StringBuffer strQuery = new  StringBuffer(" select compId from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
	strQuery.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where   a.cmnLocationMst.locId =  "+locId +" and  (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
	strQuery.append("   or   ");
	strQuery.append("  ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<=2011) and d.cmnLocationMst.locId =  "+locId+"))");
	strQuery.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1  and loc.cmnLookupMst.lookupId = 2500134 ");
	strQuery.append(" UNION ");
	strQuery.append(" select compId from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
	strQuery.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where   a.cmnLocationMst.locId =  "+locId +" and  (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+prevMonth+" and b.year="+prevYear+") and a.year="+prevYear+") ");
	strQuery.append("   or   ");
	strQuery.append("  ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+prevYear+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<=2011) and d.cmnLocationMst.locId =  "+locId+"))");
	strQuery.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1  and loc.cmnLookupMst.lookupId = 2500134 ");
	Query query = hibSession.createQuery(strQuery.toString());
	logger.info("==> DeptComp getActiveAllowDeptforSalaryDiff :: "+query);
	
	return query.list();
}
public List getActiveDeducDeptforSalaryDiff(long locId, long month,long year)
{
	Session hibSession = getSession();
	long prevMonth,prevYear;
	if(month!=1)
	{
		prevMonth = month-1;
		prevYear = year;
	}
	else
	{
		prevMonth=12;
		prevYear= year-1;
	}
	
	StringBuffer strQuery = new  StringBuffer(" select compId from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
	strQuery.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where   a.cmnLocationMst.locId =  "+locId +" and  (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
	strQuery.append("   or   ");
	strQuery.append("  ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<=2011) and d.cmnLocationMst.locId =  "+locId+"))");
	strQuery.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1  and loc.cmnLookupMst.lookupId = 2500135 ");
	strQuery.append(" UNION ");
	strQuery.append(" select compId from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
	strQuery.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where   a.cmnLocationMst.locId =  "+locId +" and  (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+prevMonth+" and b.year="+prevYear+") and a.year="+prevYear+") ");
	strQuery.append("   or   ");
	strQuery.append("  ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+prevYear+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<=2011) and d.cmnLocationMst.locId =  "+locId+"))");
	strQuery.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1  and loc.cmnLookupMst.lookupId = 2500135");
	
	Query query = hibSession.createQuery(strQuery.toString());
	logger.info("==> DeptComp getActiveAllowDeptforSalaryDiff :: "+query);
	
	
	return query.list();
}


public List getActiveLoanDeptforSalaryDiff(long locId, long month,long year)
{
	Session hibSession = getSession();
	long prevMonth,prevYear;
	if(month!=1)
	{
		prevMonth = month-1;
		prevYear = year;
	}
	else
	{
		prevMonth=12;
		prevYear= year-1;
	}
	
	StringBuffer strQuery = new  StringBuffer(" select compId from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
	strQuery.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where   a.cmnLocationMst.locId =  "+locId +" and  (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+") ");
	strQuery.append("   or   ");
	strQuery.append("  ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<=2011) and d.cmnLocationMst.locId =  "+locId+"))");
	strQuery.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1  and loc.cmnLookupMst.lookupId in(2500136,2500137) ");
	strQuery.append(" UNION ");
	strQuery.append(" select compId from HrPayLocComMpg as loc where loc.hrpaycompgrpmst.compoGrpId = ( ");
	strQuery.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where   a.cmnLocationMst.locId =  "+locId +" and  (a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+prevMonth+" and b.year="+prevYear+") and a.year="+prevYear+") ");
	strQuery.append("   or   ");
	strQuery.append("  ( a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+prevYear+") and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(year) from HrPayCompGrpMst where year<=2011) and d.cmnLocationMst.locId =  "+locId+"))");
	strQuery.append(" and a.isactive=1 and a.cmnLocationMst.locId =  "+locId +") and loc.isactive=1  and loc.cmnLookupMst.lookupId in(2500136,2500137)");
	Query query = hibSession.createQuery(strQuery.toString());
	return query.list();
	
	
}

public List getWefDate(long locId) {
	Session hibSession = getSession();
	String query=" select a.wefDate from  HrPayCompGrpMst a where a.isactive= 1 and a.cmnLocationMst.locId="+locId+"";
	Query sqlQuery = hibSession.createQuery(query.toString());
	logger.info("--khshual Date Select----"+sqlQuery.list());
	
	if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
	return  sqlQuery.list();
}
	else
	{
		return null;
	}
	// TODO Auto-generated method stub
	
}


public String getRemarks(long locId) {
	// TODO Auto-generated method stub
	List list = new ArrayList();

	String remarks="";	
	Session hibSession = getSession();

	
	String HQL_QUERY = "select a.remarks from HrPayCompGrpMst a where a.isactive= 1 and a.cmnLocationMst.locId="+locId+" ";
	

	Query sqlQuery = hibSession.createQuery(HQL_QUERY.toString());
	list= sqlQuery.list();	 
	if(list.size()>0)
	{
		if(list.get(0) !=null)
		{
			remarks =list.get(0).toString();
		}
	}

	else
	{
		remarks="";
	}
	logger.info("returning value of cmnLookupId is"+remarks); 
	return remarks;
	
}

public List getCompGrpIDisPresent(long cmbDept, long month, long year, long i) {
	Session hibSession = getSession();
	String query=" from HrPayCompGrpMst hpcgm where hpcgm.cmnLocationMst.locId="+cmbDept+" and hpcgm.month = "+month+" and hpcgm.year="+year+" and hpcgm.isactive = "+i+" ";
	Query sqlQuery = hibSession.createQuery(query.toString());
    //logger.info("getCompGrpIDisPresent-"+sqlQuery.list());
	
	List rtrn = sqlQuery.list();
	/*if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
	return  sqlQuery.list();
	}
	else
	{
		return null;
	}*/
		return rtrn;
		// TO
	}
	public List getLocAllwEdpListForInner(long locId,long month,long year) 
	{
		List<HrPayEdpCompoMpg> HrPayLocMpgAllowList = null;
		Session hibSession = getSession();
		long AllowID=2500134;
		StringBuffer query = new StringBuffer("select edp from HrPayLocComMpg as loc,HrPayEdpCompoMpg as edp where loc.hrpaycompgrpmst.compoGrpId = ( ");
		query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where  a.cmnLocationMst.locId =  "+locId +" and ");
		query.append(" a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+" and b.cmnLocationMst.locId="+locId+" and b.isactive=1) and a.year="+year);
		query.append("  or  ");
		query.append("  a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+" and c.cmnLocationMst.locId =  "+locId +")");
		query.append(" and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(r.year) from HrPayCompGrpMst r where r.year<="+year+" and r.cmnLocationMst.locId =  "+locId+") and d.cmnLocationMst.locId =  "+locId);
		query.append(" and d.month<="+month+")  and a.cmnLocationMst.locId =  "+locId +")  and loc.cmnLookupMst.lookupId = "+AllowID);
		query.append(" and loc.cmnLookupMst.lookupId= edp.cmnLookupId and edp.typeId = loc.compId ");


		Query que = hibSession.createQuery(query.toString());
		HrPayLocMpgAllowList = que.list();

		logger.info("===> ind dao list.size() :: "+HrPayLocMpgAllowList.size());

		return HrPayLocMpgAllowList;
	}
	public List getLocDeducEdpListForInner(long locId,long month,long year,long deductionBy) 
	{
		List  list = null;
		List<HrPayEdpCompoMpg> HrPayLocMpgDeductList = null;
		Session hibSession = getSession();
		logger.info("going to execute query....");
		long DeductID=2500135;

		StringBuffer HrPayloc = new StringBuffer();
		StringBuffer query = new StringBuffer("select edp from HrPayLocComMpg as loc,HrPayEdpCompoMpg as edp,HrPayDeducTypeMst as deduc where loc.hrpaycompgrpmst.compoGrpId = ( ");
		query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where  a.cmnLocationMst.locId =  "+locId +" and ");
		query.append(" a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+" and b.cmnLocationMst.locId="+locId+" and b.isactive=1) and a.year="+year);
		query.append("  or  ");
		query.append("  a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+" and c.cmnLocationMst.locId =  "+locId +")");
		query.append(" and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(r.year) from HrPayCompGrpMst r where r.year<="+year+" and r.cmnLocationMst.locId =  "+locId+") and d.cmnLocationMst.locId =  "+locId);
		query.append(" and d.month<="+month+")  and a.cmnLocationMst.locId =  "+locId +")  and loc.cmnLookupMst.lookupId = "+DeductID);
		query.append(" and loc.cmnLookupMst.lookupId= edp.cmnLookupId and edp.typeId = loc.compId and  edp.typeId=deduc.deducCode and deduc.deducCode = loc.compId");
		query.append(" and deduc.deductionBy.lookupId="+deductionBy);
		Query que = hibSession.createQuery(query.toString());
		HrPayLocMpgDeductList = que.list();
		logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
		return HrPayLocMpgDeductList;
	}
	public List getLocLoanEdpListForInner(long locId,long month,long year,long displayGroup)
	{
		List<HrPayEdpCompoMpg> HrPayLocMpgDeductList = null;
		Session hibSession = getSession();
		logger.info("going to execute query....");
		long DeductID=2500135;
		StringBuffer HrPayloc = new StringBuffer();
		logger.info("Check This Query...<>>>");
		StringBuffer query = new StringBuffer("select edp  from HrPayLocComMpg as loc,HrPayEdpCompoMpg as edp,HrLoanAdvMst as loan where loc.hrpaycompgrpmst.compoGrpId = ( ");
		query.append(" select max(a.compoGrpId) from HrPayCompGrpMst as a where  a.cmnLocationMst.locId =  "+locId +" and ");
		query.append(" a.month = (select max(b.month) from HrPayCompGrpMst as b where b.month<="+month+" and b.year="+year+" and b.cmnLocationMst.locId="+locId+" and b.isactive=1) and a.year="+year);
		query.append("  or  ");
		query.append("  a.year = (select max(c.year) from HrPayCompGrpMst as c where c.year<="+year+" and c.cmnLocationMst.locId =  "+locId +")");
		query.append(" and a.month =(select max(d.month) from HrPayCompGrpMst as d where d.year = (select max(r.year) from HrPayCompGrpMst r where r.year<="+year+" and r.cmnLocationMst.locId =  "+locId+") and d.cmnLocationMst.locId =  "+locId);
		query.append(" and d.month<="+month+")  and a.cmnLocationMst.locId =  "+locId +")  and loc.cmnLookupMst.lookupId in (2500136,2500137) ");
		query.append(" and loc.cmnLookupMst.lookupId= edp.cmnLookupId and edp.typeId = loc.compId and edp.typeId=loan.loanAdvId ");
		query.append(" and loan.loanAdvId=loc.compId and loan.displayGroup.lookupId=:displayGroup  order by loc.compId");
		Query que = hibSession.createQuery(query.toString());
		que.setParameter("displayGroup", displayGroup);
		HrPayLocMpgDeductList = que.list();
		logger.info("===> ind dao list.size() :: "+HrPayLocMpgDeductList.size());
		return HrPayLocMpgDeductList;

	}
	
	//by shailesh:start
	 public List getDDODtls(String DDOCode){
	    	List ddoDtls = null;
	    	Session session = getSession();
	    	StringBuffer sb = new StringBuffer();
	    	logger.info("---- vivek DAo---");
	    	//commented by roshan
	    	//sb.append("SELECT LOCATION_CODE,post_ID FROM ORG_DDO_MST where DDO_Code='"+DDOCode+"'");
	    	//added by roshan
	    	sb.append("SELECT LOCATION_CODE,post_ID FROM ORG_DDO_MST where (ddo_code='"+DDOCode+"' or location_code='"+DDOCode+"')");
	    	logger.info("---- vivek DAo---"+sb);
	    	Query query = session.createSQLQuery(sb.toString());
	    	ddoDtls = query.list();
	    	return ddoDtls;
	    	
	    }
	 public List getSubDDOs(Long postID, String talukaId, String ddoSelected){
	    	List ddoDtls = null;
	    	Session session = getSession();
	    	StringBuffer sb = new StringBuffer();
	    	logger.info("hiii i m dddo selected"+ddoSelected);
	    	logger.info("---- getSubDDOs DAO---");
	    	logger.info("postID : "+postID);
	    	//sb.append("SELECT ddo.DDO_CODE , ddo.DDO_NAME FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and rep.REPT_DDO_POST_ID="+postID);
	    	sb.append("SELECT ddo.LOCATION_CODE,ddo.DDO_office,ddo.ddo_code FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo, MST_DCPS_DDO_OFFICE office where ddo.DDO_OFFICE is not null and office.status_flag=1 and ddo.DDO_CODE = rep.ZP_DDO_CODE and office.DDO_CODE=ddo.DDO_CODE and rep.status =1 and ((rep.REPT_DDO_POST_ID='" + postID + "') or (rep.FINAL_DDO_POST_ID='" + postID + "')) ");
	    	if((talukaId!=null)&&(talukaId!="")&&(Long.parseLong(talukaId)!=-1)){
	    		sb.append(" and office.taluka="+talukaId);
	    	}
	    	if((ddoSelected!=null)&&(ddoSelected!="")){
	    		sb.append(" and (office.ddo_code like '%"+ddoSelected+"%' or ddo.ddo_office like '%"+ddoSelected+"%')");
	    	}
	    	sb.append(" order by ddo.DDO_CODE asc");
	    	//end by roshan
	    	
	    	logger.info("---- getSubDDOs DAo---"+sb);
	    	Query query = session.createSQLQuery(sb.toString());
	    	logger.info("output: "+query.list().get(0));
	    	ddoDtls = query.list();
	    	logger.info("list size :"+ddoDtls.size());
	    	return ddoDtls;
	    }


	    //start by roshan
	    public String districtName(String ddoCode) {
	    	String districtId="";
	    	Session session = getSession();
	    	StringBuffer sb = new StringBuffer();
	    	logger.info("---- get district---");
	    	//sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
	    	//added by roshan
	    	sb.append("SELECT DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code='"+ddoCode+"' and upper(ddo_office)=upper('Yes')");
	    	//end by roshan
	    	logger.info("---- get district---"+sb);
	    	Query query = session.createSQLQuery(sb.toString());
	    	districtId = (String) query.uniqueResult();
	    	logger.info("district name is "+districtId);
	    	return districtId;
	    	
	    }
	    //end by roshan

	    public List allTaluka(String districtID) {
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


	    //added by poonam for SanctionLeave
	    
	    public List getDDOLeavDtls(String ddoCode, String talukaId,String ddoSelected,int currYear, String sanctionLeaveType) {
			List ddoDtls = null;
	    	Session session = getSession();
	    	StringBuffer sb = new StringBuffer();
	    	logger.info("hiii i m dddo selected"+ddoSelected);
	    	logger.info("---- getSubDDOs DAO---");
	    	sb.append(" SELECT ddo.LOCATION_CODE,ddo.DDO_office,ddo.ddo_code,leave.LEAVE_FROM_DATE,leave.LEAVE_TO_DATE FROM RLT_ZP_DDO_MAP rep  ");
	    	sb.append(" inner join ORG_DDO_MST ddo on ddo.DDO_CODE=rep.ZP_DDO_CODE inner join mst_dcps_ddo_office off on off.DDO_CODE=ddo.DDO_CODE  ");
	    	sb.append(" left outer join HR_PAY_DDO_LEAVE_SANCTION_DTLS leave on leave.DDO_CODE=rep.ZP_DDO_CODE and leave.year= '"+currYear+"'  and leave.type_of_vacation='"+sanctionLeaveType+"'");
	    	sb.append(" where off.status_flag=1 and rep.status =1 and ((rep.rept_ddo_code='"+ddoCode+"') or (rep.FINAL_DDO_code='"+ddoCode+"'))  ");
	    	
	    	if((talukaId!=null)&&(talukaId!="")&&(Long.parseLong(talukaId)!=-1)){
	    		sb.append(" and off.taluka="+talukaId);
	    	}
	    	//end by roshan
	    	sb.append(" order by ddo.ddo_code");
	    	logger.info("---- getSubDDOs DAo---"+sb);
	    	Query query = session.createSQLQuery(sb.toString());
	    	logger.info("output: "+query.list().get(0));
	    	ddoDtls = query.list();
	    	logger.info("list size :"+ddoDtls.size());
	    	return ddoDtls;
		}

		public void updateLeaveDetails(String locId, String frmDt,
				String toDt,int year, String sanctionLeaveType) {
		
	    	Session session = getSession();
	    	
	    	StringBuffer sb1 = new StringBuffer();
		    
	    	logger.info("---- insert update 1---");
	    	sb1.append(" select count(1) from HR_PAY_DDO_LEAVE_SANCTION_DTLS where loc_id="+locId+" and year="+year+" and type_of_vacation='"+sanctionLeaveType+"'");
	    
	    	logger.info("----  insert update 1---"+sb1);
	    	Query query1 = session.createSQLQuery(sb1.toString());
	    
	    	if (query1.uniqueResult().toString()!=null && Long.parseLong(query1.uniqueResult().toString())!=0){
	    		StringBuffer sb2 = new StringBuffer();
			    
		    	logger.info("---- insert update 2---");
		    	sb2.append(" update HR_PAY_DDO_LEAVE_SANCTION_DTLS set leave_from_date='"+frmDt+"',leave_to_date='"+toDt+"',type_of_vacation='"+sanctionLeaveType+"' where loc_id="+locId+" and year="+year+" ");
		    
		    	logger.info("----  insert update 2---"+sb2);
		    	Query query2 = session.createSQLQuery(sb2.toString());
		    	query2.executeUpdate();
	    	}
	    	
	    	else 
	    	{
	    	StringBuffer sb = new StringBuffer();
	    
	    	logger.info("---- insert update ---");
	    	sb.append(" Insert into HR_PAY_DDO_LEAVE_SANCTION_DTLS (LOC_ID,DDO_CODE,YEAR,LEAVE_FROM_DATE,LEAVE_TO_DATE,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,type_of_vacation) values("+locId+",(select ddo_code from org_ddo_mst where location_code="+locId+"),"+year+", '"+frmDt+"','"+toDt+"',1,sysdate,1,null,'"+sanctionLeaveType+"') ");
	    
	    	logger.info("----  insert update---"+sb);
	    	Query query = session.createSQLQuery(sb.toString());
	    
	    	query.executeUpdate();
	    	}
		}
    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}

