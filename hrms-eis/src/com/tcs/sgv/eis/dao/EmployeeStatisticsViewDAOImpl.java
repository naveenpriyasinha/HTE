package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.EmployeeStatisticsViewVO;
import com.tcs.sgv.eis.zp.zpDdoOffice.service.ZpDDOOfficeServiceImpl;

public class EmployeeStatisticsViewDAOImpl extends GenericDaoHibernateImpl {
	private static final String All = null;
	Logger logger = Logger.getLogger( EmployeeStatisticsViewDAOImpl.class );
	Session hibSession=null;
	public EmployeeStatisticsViewDAOImpl(Class type,SessionFactory sessionFactory) {
		super(type);
		// TODO Auto-generated constructor stub
		
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}
	public int getmaxsnoid()
	{
		List list = null;
		
		int sno = 0;
		String strQuery=null;
		strQuery = "SELECT max(sno) FROM EMPLOYEESTATISTICS";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 sno = Integer.parseInt(list.get(0).toString());
			 sno = sno+1;
		 }
		
		return sno;
		
	}
	public int getmaxsnoidOfInstitute()
	{
		List list = null;
		
		int sno = 0;
		String strQuery=null;
		strQuery = "SELECT max(sno) FROM INSTITUTE_CONFIGURATION";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 sno = Integer.parseInt(list.get(0).toString());
			 sno = sno+1;
		 }
		
		return sno;
		
	}
	
	
	public int countsaveasdraft(long district_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		//strQuery = "select count(1) from MST_DCPS_EMP where FORM_STATUS = 1 and reg_status = 0 and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT="+district_id+")))";
		strQuery = "select count(1) from MST_DCPS_EMP where FORM_STATUS = 0 and reg_status in (0, -1) and CREATED_DATE > '2012-08-15' and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP " 
					+" where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT="+district_id+")))";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	public int noOfschoolsApproved(long district_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG=1 and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	 public int countforwadedfromdraft(long district_id)
	  {
	    List list = null;

	    int forwadedfromdraft = 0;
	    String strQuery = null;
	    //strQuery = "select count(1) from MST_DCPS_EMP where  FORM_STATUS = 1 and REG_STATUS = 0 and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT=" + district_id + ")))";
	    strQuery = "select count(1) from MST_DCPS_EMP where FORM_STATUS = 1 and reg_status = 0 and CREATED_DATE > '2012-08-15' and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP"+ 
	    			" where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT="+district_id +")))";
	    this.logger.info(strQuery);
	    Query query = this.hibSession.createSQLQuery(strQuery);
	    list = query.list();

	    if ((list != null) && (list.size() > 0))
	    {
	      forwadedfromdraft = Integer.parseInt(list.get(0).toString());
	    }

	    return forwadedfromdraft;
	  }
	
	
	  public int totalNoOfEmployee(long district_id)
	  {
	    List list = null;

	    int formapproved = 0;
	    String strQuery = null;
	    //strQuery = "select count(1) from MST_DCPS_EMP where   ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT=" + district_id + ")))";
	    strQuery = "select count(1) from MST_DCPS_EMP where CREATED_DATE > '2012-08-15' and  ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP" 
	    		+" where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT="+district_id+")))";
	    this.logger.info(strQuery);
	    Query query = this.hibSession.createSQLQuery(strQuery);
	    list = query.list();

	    if ((list != null) && (list.size() > 0))
	    {
	      formapproved = Integer.parseInt(list.get(0).toString());
	    }

	    return formapproved;
	  }
	  
	  public int countformapproved(long district_id)
	  {
	    List list = null;

	    int formapproved = 0;
	    String strQuery = null;
	    //strQuery = "select count(1) from MST_DCPS_EMP where  FORM_STATUS = 1 and REG_STATUS >0 and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT=" + district_id + ")))";
	    strQuery = "select count(1) from MST_DCPS_EMP where FORM_STATUS = 1 and reg_status > 0 and CREATED_DATE > '2012-08-15'and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP"+ 
	    			" where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT="+district_id+")))";
	    this.logger.info(strQuery);
	    Query query = this.hibSession.createSQLQuery(strQuery);
	    list = query.list();

	    if ((list != null) && (list.size() > 0))
	    {
	      formapproved = Integer.parseInt(list.get(0).toString());
	    }

	    return formapproved;
	  }
	
	  public List getDemoDisplayValues()
	  {
	    this.logger.info("Within the DemoViewDAOImpl");
	    List list = null;

	    String strquery = "SELECT DISTRICT_ID,DISTRICT_NAME FROM CMN_DISTRICT_MST where STATE_ID=15 AND LANG_ID=1";

	    Session hibsession = getSession();
	    Query query = hibsession.createSQLQuery(strquery);

	    this.logger.info("The SQL query :" + query);
	    list = query.list();

	    return list;
	  }
	
	
	  public List viewEmployeeStatisticsReport()
	  {
	    List list = null;

	    String strQuery = null;
	    strQuery = "SELECT * FROM EMPLOYEESTATISTICS where SNO=(SELECT MAX(SNO) FROM EMPLOYEESTATISTICS)";
	    this.logger.info(strQuery);
	    Query query = this.hibSession.createSQLQuery(strQuery);
	    list = query.list();

	    return list;
	  }
	  
	//added by sunitha
	  public List getAdminOfficeValues()
	  {
	    this.logger.info("Within the getAdminOfficeValues");
	    List adminlist = null;

	    String strquery = " SELECT ADMIN_CODE,ADMIN_NAME from ZP_ADMIN_NAME_MST";

	    Session hibsession = getSession();
	    Query query = hibsession.createSQLQuery(strquery);

	    this.logger.info("The SQL query :" + query);
	    adminlist = query.list();

	    return adminlist;
	  }
	  
	  public List viewInstituteReport()
	  {
	    List list = null;

	    String strQuery = null;
	    strQuery = "SELECT * FROM INSTITUTE_CONFIGURATION where SNO=(SELECT MAX(SNO) FROM INSTITUTE_CONFIGURATION)";
	    this.logger.info(strQuery);
	    Query query = this.hibSession.createSQLQuery(strQuery);
	    list = query.list();

	    return list;
	  }
	
	//have to work
	public int totalApproveSchhols(long district_id)
	{
		List list = null;
		
		int formapproved = 0;
		String strQuery=null;
		//strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG=1 and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id;
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT ddo_code FROM mst_dcps_ddo_office where DISTRICT="+district_id+") and status=1";
		if(district_id==1)			
			 strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)) and STATUS = 1";
	    else
	      strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)  and  District=" + district_id + " ) and STATUS = 1";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 formapproved = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return formapproved;
		
	}
	public int noOfschoolsPending(long district_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		//strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG =0 OR STATUS_FLAG is null and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT ddo_code FROM mst_dcps_ddo_office where DISTRICT="+district_id+") and status=0";
		if(district_id==1)
			 strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)) and STATUS = 0 OR STATUS IS NULL";
	    else
	      strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)  and  District=" + district_id + " ) and STATUS = 0";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	
	//have to work.
	public int noOfschoolsConfigured(long district_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		//strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT DDO_CODE FROM mst_dcps_ddo_office where DISTRICT="+district_id+")";
		if(district_id==1)
		    strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map))";
	    else
	      strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)  and  District=" + district_id + " )";

		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	public int noOfschoolsRejected(long district_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		//strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT ddo_code FROM mst_dcps_ddo_office where DISTRICT="+district_id+") and status=-1";
		if(district_id==1)
		      strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)) and STATUS = -1";
	    else
	      strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)  and  District=" + district_id + " ) and STATUS = -1";

		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	public int schoolvalidationapprovedbyDDO(long district_id)
	{
		List list = null;
		
		int formapproved = 0;
		String strQuery=null;
		if(district_id==1)
		      strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG=1 and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map)";
	    else {

	    	 strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG =1 and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district=" + district_id;

	    }
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 formapproved = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return formapproved;
		
	}
	public int schoolvalidationpendingbyDDO(long district_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		if(district_id==1)
		      strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG =0  and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map)";
	    else {
	      strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG =0 OR STATUS_FLAG is null and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district=" + district_id;
	    }
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	
	
	
	
	public long getNextSeqNum() {
			long seqId=0;
			StringBuffer sb= new StringBuffer();
			sb.append("select GENERATED_ID from CMN_TABLE_SEQ_MST where upper(table_name)='INSTITUTE_CONFIGURATION'");
			Query query = hibSession.createSQLQuery(sb.toString());
			seqId=Long.parseLong(query.uniqueResult().toString());		
			logger.info("seqId............"+seqId);
			long seqNo= seqId+1;
			logger.info("seqNo............"+seqId);
			StringBuffer sb2= new StringBuffer();
			sb2.append("update CMN_TABLE_SEQ_MST set GENERATED_ID="+seqNo+" where upper(TABLE_NAME)= 'INSTITUTE_CONFIGURATION'");
			Query query2 = hibSession.createSQLQuery(sb2.toString());
			query2.executeUpdate();
			return seqId;
		}
	
	
	public int totalApproveSchholsShalarth(long district_id,long adminoffice_id)
	{
		logger.info("inside totalApproveSchholsShalarth");
		List list = null;
		
		int formapproved = 0;
		String strQuery=null;
		//strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG=1 and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id;
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT ddo_code FROM mst_dcps_ddo_office where DISTRICT="+district_id+") and status=1";
		if(district_id==1)			
			 //strQuery = "SELECT count(1) FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (select ddo_code from  mst_dcps_ddo_office where  ddo_code in(SELECT  REPT_DDO_CODE FROM rlt_zp_ddo_map)) and STATUS = 1";
		 strQuery = "SELECT  count(ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on a.ddo_code=ddo.zp_ddo_code inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE="+adminoffice_id+" and admin.ADMIN_CODE=a.ddo_type INNER JOIN MST_DCPS_DDO_OFFICE ofc on ofc.ddo_code = ddo.ZP_DDO_CODE  and ddo.STATUS= 1";
	    else
	    	strQuery = "SELECT  count(ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on a.ddo_code=ddo.zp_ddo_code inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE="+adminoffice_id+" and admin.ADMIN_CODE=a.ddo_type INNER JOIN MST_DCPS_DDO_OFFICE ofc on ofc.ddo_code = ddo.ZP_DDO_CODE INNER JOIN CMN_DISTRICT_MST dist on dist.district_id ="+district_id+" and dist.lang_id=1 and ddo.STATUS=1 group by admin.admin_code,admin.ADMIN_NAME,dist.district_name";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 formapproved = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return formapproved;
		
	}
	public int noOfschoolsPendingShalarth(long district_id,long adminoffice_id)
	{
		logger.info("inside noOfschoolsPendingShalarth");
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		//strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG =0 OR STATUS_FLAG is null and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT ddo_code FROM mst_dcps_ddo_office where DISTRICT="+district_id+") and status=0";
		if(district_id==1)
			 strQuery = "SELECT  count(ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on a.ddo_code=ddo.zp_ddo_code inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE="+adminoffice_id+" and admin.ADMIN_CODE=a.ddo_type INNER JOIN MST_DCPS_DDO_OFFICE ofc on ofc.ddo_code = ddo.ZP_DDO_CODE  and (ddo.STATUS is  null or ddo.STATUS = 0)";
	    else
	      strQuery = "SELECT  count(ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on a.ddo_code=ddo.zp_ddo_code inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE="+adminoffice_id+" and admin.ADMIN_CODE=a.ddo_type INNER JOIN MST_DCPS_DDO_OFFICE ofc on ofc.ddo_code = ddo.ZP_DDO_CODE INNER JOIN CMN_DISTRICT_MST dist on dist.district_id ="+district_id+" and dist.lang_id=1 and (ddo.STATUS is  null or ddo.STATUS = 0) group by admin.admin_code,admin.ADMIN_NAME,dist.district_name";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	
	//have to work.
	public int noOfschoolsConfiguredShalarth(long district_id,Long adminoffice_id)
	{
		logger.info("inside noOfschoolsConfiguredShalarth");
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		//strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT DDO_CODE FROM mst_dcps_ddo_office where DISTRICT="+district_id+")";
		if(district_id==1)
			 strQuery = "SELECT   count( distinct ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on ddo.zp_ddo_code = a.ddo_code INNER JOIN MST_DCPS_DDO_OFFICE ofc on ddo.ZP_DDO_CODE  = ofc.ddo_code";
	    else
	      strQuery = "SELECT   count( distinct ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on ddo.zp_ddo_code = a.ddo_code INNER JOIN MST_DCPS_DDO_OFFICE ofc on ddo.ZP_DDO_CODE  = ofc.ddo_code and a.DDO_TYPE = "+adminoffice_id+" and ofc.DISTRICT = "+district_id;



		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	public int noOfschoolsRejectedShalarth(long district_id,Long adminoffice_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		
		//strQuery="SELECT count(1) FROM MST_DCPS_DDO_OFFICE where DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map) and district="+district_id+"";
		//strQuery="select count(1) from rlt_zp_ddo_map where zp_ddo_code in (SELECT ddo_code FROM mst_dcps_ddo_office where DISTRICT="+district_id+") and status=-1";
		if(district_id==1)
		      strQuery = "SELECT  count(ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on a.ddo_code=ddo.zp_ddo_code inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE="+adminoffice_id+" and admin.ADMIN_CODE=a.ddo_type INNER JOIN MST_DCPS_DDO_OFFICE ofc on ofc.ddo_code = ddo.ZP_DDO_CODE  and ddo.STATUS= -1";
	    else
	      strQuery = "SELECT  count(ddo.ZP_DDO_CODE) FROM RLT_ZP_DDO_MAP ddo INNER JOIN org_ddo_mst a on a.ddo_code=ddo.zp_ddo_code inner join ZP_ADMIN_NAME_MST admin on admin.ADMIN_CODE="+adminoffice_id+" and admin.ADMIN_CODE=a.ddo_type INNER JOIN MST_DCPS_DDO_OFFICE ofc on ofc.ddo_code = ddo.ZP_DDO_CODE INNER JOIN CMN_DISTRICT_MST dist on dist.district_id ="+district_id+" and dist.lang_id=1 and ddo.STATUS=-1 group by admin.admin_code,admin.ADMIN_NAME,dist.district_name";

		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
	public String findDistrictName(long district_id) {
		// TODO Auto-generated method stub
		logger.info("inside Find District Name");
		String districtName= All;
		String strQuery=null;
		
		if(district_id !=1)
		{
	      strQuery = "SELECT DISTRICT_NAME FROM CMN_DISTRICT_MST where DISTRICT_ID="+district_id+"";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		districtName = (String) query.uniqueResult();
		}
		return districtName;
	}
	public String typeOfSchool(long adminoffice_id) {
		// TODO Auto-generated method stub
		logger.info("inside Find Admin Office Name");
		String typeOfSchool= All;
		String strQuery=null;
		
		
	    strQuery = "SELECT ADMIN_NAME FROM ZP_ADMIN_NAME_MST where ADMIN_CODE="+adminoffice_id+"";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		typeOfSchool = (String) query.uniqueResult();
		
		return typeOfSchool;
	}
	public long noOfEmployeeSavedOrForwarded(long district_id,
			long adminoffice_id) {
		List list = null;
		int noOfEmployeeSavedOrForwarded = 0;
		String strQuery=null;
		//strQuery = "select count(1) from MST_DCPS_EMP where FORM_STATUS = 1 and reg_status = 0 and ddo_code in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_CODE in (SELECT DDO_CODE FROM ORG_DDO_MST where DDO_CODE in (SELECT DDO_CODE FROM MST_DCPS_DDO_OFFICE where DISTRICT="+district_id+")))";
		if(district_id==1)
			strQuery = "SELECT  count(distinct(emp.DCPS_EMP_ID)) FROM mst_dcps_emp emp inner join rlt_zp_ddo_map ddo on emp.ddo_code = ddo.zp_ddo_code inner join org_ddo_mst ddomst on  ddomst.ddo_code = ddo.zp_ddo_code inner join MST_DCPS_DDO_OFFICE ofc on ddomst.ddo_code = ofc.ddo_code	where emp.FORM_STATUS in (0,1,-1) and emp.reg_status in (0,1,2) and emp.CREATED_DATE > '2012-08-15'";
		else
		strQuery = "SELECT  count(distinct(emp.DCPS_EMP_ID)) FROM mst_dcps_emp emp inner join rlt_zp_ddo_map ddo on emp.ddo_code = ddo.zp_ddo_code inner join org_ddo_mst ddomst on  ddomst.ddo_code = ddo.zp_ddo_code inner join MST_DCPS_DDO_OFFICE ofc on ddomst.ddo_code = ofc.ddo_code	inner join ZP_ADMIN_NAME_MST admin on  admin.ADMIN_CODE= "+adminoffice_id+" inner join CMN_DISTRICT_MST dist on ofc.DISTRICT= "+district_id+"	where emp.FORM_STATUS in (0,1,-1) and emp.reg_status in (0,1,2) and emp.CREATED_DATE > '2012-08-15'";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 noOfEmployeeSavedOrForwarded = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return noOfEmployeeSavedOrForwarded;
	}
	public long NoOfSchoolDataEntryInitiated(long district_id,
			long adminoffice_id) {
		List list = null;
		
		int dataEntryInitiated = 0;
		String strQuery=null;
		
		if(district_id==1)
		{
			strQuery ="SELECT  count(distinct(emp.DDO_CODE)) FROM mst_dcps_emp emp inner join rlt_zp_ddo_map ddo on emp.ddo_code = ddo.zp_ddo_code inner join org_ddo_mst ddomst on  ddomst.ddo_code = ddo.zp_ddo_code inner join MST_DCPS_DDO_OFFICE ofc on ddomst.ddo_code = ofc.ddo_code	where emp.FORM_STATUS in (0,1,-1) and emp.reg_status in (0,1,2) and emp.CREATED_DATE > '2012-08-15'";
		}
		else 
		strQuery ="SELECT  count(distinct(emp.DDO_CODE)) FROM mst_dcps_emp emp inner join rlt_zp_ddo_map ddo on emp.ddo_code = ddo.zp_ddo_code inner join org_ddo_mst ddomst on  ddomst.ddo_code = ddo.zp_ddo_code inner join MST_DCPS_DDO_OFFICE ofc on ddomst.ddo_code = ofc.ddo_code	inner join ZP_ADMIN_NAME_MST admin on  admin.ADMIN_CODE= "+adminoffice_id+" inner join CMN_DISTRICT_MST dist on ofc.DISTRICT= "+district_id+"	where emp.FORM_STATUS in (0,1,-1) and emp.reg_status in (0,1,2) and emp.CREATED_DATE > '2012-08-15'";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 dataEntryInitiated = Integer.parseInt(list.get(0).toString()); 
		 }
		return dataEntryInitiated;
	}
	
	
	public int noOfschoolsValidatedbyLvl2ddoShalarth(long district_id,long adminoffice_id)
	{
		List list = null;
		
		int saveasdraft = 0;
		String strQuery=null;
		if(district_id==1)
		      strQuery = "SELECT count(1) FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG =1  and DDO_CODE in(select zp_ddo_code from rlt_zp_ddo_map)";
	    else {
	      strQuery = "select count(1) from org_ddo_mst ddo inner join MST_DCPS_DDO_OFFICE mst on ddo.DDO_CODE = mst.DDO_CODE inner join rlt_zp_ddo_map z on z.ZP_DDO_CODE=mst.DDO_CODE where mst.STATUS_FLAG=1 and mst.district="+district_id+" and ddo.DDO_TYPE="+adminoffice_id;


	    }
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		
		 if(list!= null && list.size()>0)
		 {
			 saveasdraft = Integer.parseInt(list.get(0).toString());
			 
		 }
		
		return saveasdraft;
		
	}
}
