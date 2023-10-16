/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 26, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.DDOInformationDetail;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAOImpl;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 26, 2011
 */
public class DdoOfficeDAOImpl extends GenericDaoHibernateImpl implements
DdoOfficeDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger
	.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);

	public DdoOfficeDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public List getAllOffices(String lStrDdoCode) {

		List listSavedOffices = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery
		.append(" SELECT dcpsDdoOfficeIdPk,dcpsDdoOfficeName,dcpsDdoOfficeDdoFlag,dcpsDdoOfficeAddress1,dcpsDdoOfficeAddress2,statusFlag ");
		lSBQuery.append(" FROM DdoOffice");
		lSBQuery.append(" WHERE dcpsDdoCode = :ddoCode ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		listSavedOffices = lQuery.list();

		return listSavedOffices;
	}

	//added by ketan
	public List getApproveDdoOffice(Long PostId, String talukaId, String ddoSelected)
	{ 
		Session hibSession = getSession();
		//String query=" SELECT DCPS_DDO_OFFICE_MST_ID,OFF_NAME,ADDRESS1,ADDRESS2,STATUS_FLAG FROM MST_DCPS_DDO_OFFICE where DCPS_DDO_OFFICE_MST_ID in (SELECT OFFICE_ID FROM SH_DDO_OFFICE_RQT_MPG where REP_POST_ID=:PostId)";
		//added by roshan
		StringBuffer sb = new StringBuffer();
		logger.info("taluka id is"+talukaId);
		logger.info("ddo id is"+ddoSelected);
		sb.append ("SELECT DCPS_DDO_OFFICE_MST_ID,OFF_NAME,ADDRESS1,ADDRESS2,STATUS_FLAG,ddo_code FROM MST_DCPS_DDO_OFFICE where STATUS_FLAG=0 and DCPS_DDO_OFFICE_MST_ID in (SELECT OFFICE_ID FROM SH_DDO_OFFICE_RQT_MPG where REP_POST_ID="+PostId+")");
		//added by roshan
		if((talukaId!=null)&&(talukaId!="")&&(Long.parseLong(talukaId)!=-1)){
			sb.append(" and taluka="+talukaId);
		}
		if((ddoSelected!=null)&&(ddoSelected!="")){
			sb.append(" and (ddo_code like '%"+ddoSelected+"%' or off_name like '%"+ddoSelected+"%')");
		}
		//end by roshan
		//end by roshan
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());
		//sqlQuery.setParameter("PostId", PostId);
		logger.info("Query for Approve DDO Office Data---->>>>"+sqlQuery);
		logger.info("--ApproveDDO----"+sqlQuery.list());
		if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
		{
			return sqlQuery.list();
		}
		else
		{
			return null;
		}
	}


	public DDOInformationDetail getDdoInfo(String lStrDdoCode) {

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("FROM DDOInformationDetail");
		lSBQuery.append(" WHERE ddoCode = :ddoCode ");
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		DDOInformationDetail lObjDdoInformation = (DDOInformationDetail) lQuery
		.uniqueResult();

		return lObjDdoInformation;
	}

	public DdoOffice getDdoOfficeDtls(Long ddoOfficeId) {

		List<DdoOffice> resultList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();
			SBQuery
			.append("from DdoOffice where dcpsDdoOfficeIdPk = :ddoOfficeId");
			Query lQuery = ghibSession.createQuery(SBQuery.toString());
			lQuery.setParameter("ddoOfficeId", ddoOfficeId);
			logger.info("ddoOfficeId****************"+ddoOfficeId);
			logger.info("SBQuery****************"+SBQuery);

			resultList = lQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
		return resultList.get(0);
	}

	public void updateDdoOffice(String lStrDdoOffice, String lStrDdoCode) {

		try {
			//added by vaibhav tyagi: start
			logger.info("lStrDdoOffice "+lStrDdoOffice);
			logger.info("lStrDdoCode"+lStrDdoCode);
			logger.info("Inside updateDdoOffice....");

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("UPDATE ORG_DDO_MST SET DDO_OFFICE = '"+lStrDdoOffice+"' WHERE DDO_CODE ="+lStrDdoCode);
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			lQuery.executeUpdate();
			logger.info("SBQuery****************"+SBQuery);

			//added by vaibhav tyagi:start
			StringBuilder SBQuery1 = new StringBuilder();
			SBQuery1.append("UPDATE MST_DCPS_DDO_OFFICE SET OFF_NAME = '"+lStrDdoOffice+"' WHERE DDO_CODE ="+lStrDdoCode);
			logger.info("SBQuery1 ***********"+SBQuery1);
			Query lQuery1 = ghibSession.createSQLQuery(SBQuery.toString());
			lQuery1.executeUpdate();
			//added by vaibhav tyagi:end


		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}

	}

	public String getDefaultDdoOffice(String lStrDdoCode) 
	{
		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append("Select ddoOffice FROM OrgDdoMst ");
		lSBQuery.append(" WHERE ddoCode = :ddoCode ");
		logger.info(lSBQuery);
		lQuery = ghibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);

		String lStrDdoOffice = lQuery.list().get(0).toString();

		return lStrDdoOffice;
	}


	public Long getrltZpDDOPostId(Long locId) 
	{   Long postid=0l;
	Session hibSession = getSession();
	String query=" SELECT REPT_DDO_POST_ID FROM  RLT_ZP_DDO_MAP where zp_ddo_code=(SELECT DDO_CODE FROM org_ddo_mst where LOCATION_CODE=:locId)";
	Query sqlQuery = hibSession.createSQLQuery(query.toString());
	sqlQuery.setParameter("locId", locId);
	logger.info("Query for rlt_zp_DDO_map Data---->>>>"+sqlQuery.toString());
	Object o=sqlQuery.uniqueResult();
	String temp="";
	temp=(o!=null)?o.toString():"0";
	postid =Long.parseLong(temp);
	return postid;
	}

	public void updateLocationMst(Long officeId, String officeName){
		logger.info("officeId "+officeId);
		logger.info("officeName "+officeName);
		Session hibSession = getSession();
		String query="update CMN_LOCATION_MST set LOC_NAME='"+officeName+"' where LOC_ID=(select location_code from ORG_DDO_MST where DDO_CODE=(SELECT ddo_code FROM MST_DCPS_DDO_OFFICE where DCPS_DDO_OFFICE_MST_ID="+officeId+"))";
		logger.info("queryy.."+query);
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		sqlQuery.executeUpdate();
	}


	// Mayuresh
	public void updateDdoOffice777(String lStrDdoOffice, String lStrDdoCode, Long lstrLocationCode) {

		try {

			logger.info("lStrDdoOffice "+lStrDdoOffice);
			logger.info("lStrDdoCode"+lStrDdoCode);
			logger.info("lStrDdoCode"+lstrLocationCode);
			logger.info("Inside updateDdoOffice....");

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("UPDATE ORG_DDO_MST SET DDO_OFFICE = '"+lStrDdoOffice+"' WHERE DDO_CODE ='"+lStrDdoCode+"'");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			lQuery.executeUpdate();

			StringBuilder SBQuery7 = new StringBuilder();
			SBQuery7.append("update CMN_LOCATION_MST set loc_name = '"+lStrDdoOffice+"' where LOCATION_CODE in (SELECT LOCATION_CODE FROM ORG_DDO_MST where LOCATION_CODE = '"+lstrLocationCode+"')");
			Query lQuery7 = ghibSession.createSQLQuery(SBQuery7.toString());
			lQuery7.executeUpdate();

			StringBuilder SBQuery1 = new StringBuilder();
			SBQuery1.append("UPDATE MST_DCPS_DDO_OFFICE SET OFF_NAME = '"+lStrDdoOffice+"' WHERE DDO_CODE ='"+lStrDdoCode+"'");
			Query lQuery1 = ghibSession.createSQLQuery(SBQuery1.toString()); 
			lQuery1.executeUpdate();



		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}

	}
	//added nby roshan
	public String districtName(String ddoCode) {
		String districtId="";
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		logger.info("---- get district---");
		//sb.append("SELECT ddo.LOCATION_CODE FROM RLT_ZP_DDO_MAP rep, ORG_DDO_MST ddo where ddo.DDO_CODE = rep.ZP_DDO_CODE and ((rep.REPT_DDO_POST_ID='" + postId + "') or (rep.FINAL_DDO_POST_ID='" + postId + "'))");
		//added by roshan
		sb.append("SELECT DIstrict FROM MST_DCPS_DDO_OFFICE where ddo_code="+ddoCode+" and DDO_OFFICE='Yes'");
		//end by roshan
		logger.info("---- get district---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		districtId = (String) query.uniqueResult();
		logger.info("district name is "+districtId);
		return districtId;

	}
	//end by roshan

	@Override
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
	//end by roshan
	// End by Mayuresh


	//added by samadhan for display city class on organisation/office tab
	public String getCityClass(String city) {
		String cityClass=null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();

		sb.append("select CITY_CLASS from CMN_CITY_MST where CITY_ID = '"+city+"'");
		logger.info("---- get city class query---"+sb.toString());
		Query query = session.createSQLQuery(sb.toString());
		cityClass = (String) query.uniqueResult();


		logger.info("city class is: "+cityClass );
		return cityClass;
	}
	//added by roshan on 19 april 
	public void updateName(Long longDdoOfficeId) {
		logger.info("longDdoOfficeId is for update the name is by roshan "+longDdoOfficeId);
		Session hibSession = getSession();
		String query="update org_emp_mst set emp_fname=" +
				"(SELECT ddo_personal_name from org_ddo_mst where ddo_code=" +
				"(SELECT ddo_code FROM mst_Dcps_ddo_office where upper(ddo_office)='YES' and dcps_ddo_office_mst_id="+longDdoOfficeId+")) " +
				" where user_id=(select user_id from org_userpost_rlt where activate_flag=1 and post_id=" +
				"(select post_id from org_ddo_mst where ddo_code=" +
				"(SELECT ddo_code FROM mst_Dcps_ddo_office where upper(ddo_office)='YES' " +
				" and dcps_ddo_office_mst_id="+longDdoOfficeId+")))";
		
		Query sqlQuery = hibSession.createSQLQuery(query.toString());
		logger.info("query for update name is "+query.toString());
		sqlQuery.executeUpdate();
		
	}
}
