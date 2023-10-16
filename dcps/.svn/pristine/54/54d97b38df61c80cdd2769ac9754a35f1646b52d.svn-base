package com.tcs.sgv.dcps.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class MapDiseCodeToMultiDDODAOImpl extends GenericDaoHibernateImpl implements MapDiseCodeToMultiDDODAO{


	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;


	public MapDiseCodeToMultiDDODAOImpl(Class<MapDiseCodeToMultiDDODAOImpl> class1,SessionFactory sessionFactory) {
		super(class1);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public List getDiseForAutoComplete(String searchKey) {
		ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO;
		Object[] obj;

		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;

		sb.append("select office_name, DICE_CODE from ZP_ADMIN_DICE_MPG where DICE_CODE LIKE '"+searchKey+"%'");
		selectQuery = ghibSession.createSQLQuery(sb.toString());
		gLogger.info("searchKey: "+searchKey);
		gLogger.info("Query to get dise code through ajax: "+selectQuery);
		//selectQuery.setParameter("searchKey", searchKey + '%');

		List resultList = selectQuery.list();

		cmbVO = new ComboValuesVO();

		if (resultList != null && resultList.size() > 0) {
			cmbVO = new ComboValuesVO();
			cmbVO.setId("-1");
			cmbVO.setDesc("-- Select --");
			finalList.add(cmbVO);
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				cmbVO = new ComboValuesVO();
				obj = (Object[]) it.next();
				cmbVO.setId(obj[1].toString());
				cmbVO.setDesc(obj[1].toString());
				finalList.add(cmbVO);
			}
		}

		return finalList;
	}

	@Override
	public List getOfficeName(String diseCode) {
		List finalOfficeList = null;


		StringBuilder sb = new StringBuilder();
		Query selectQuery = null;

		sb.append("select DICE_CODE, office_name from ZP_ADMIN_DICE_MPG where DICE_CODE LIKE :diseCode ");
		selectQuery = ghibSession.createSQLQuery(sb.toString());
		gLogger.info("Query to get Office name: "+selectQuery);
		selectQuery.setParameter("diseCode", diseCode);
		finalOfficeList = selectQuery.list();

		return finalOfficeList;
	}

	@Override
	public void updateMapDise(String diseCode) {


		try {
			logger.info("Inside updateMapDise....");
			logger.info("dise code is "+diseCode);

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update ZP_ADMIN_DICE_MPG set used='2' where DICE_CODE = '"+diseCode+"'");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}


	}

	//Added by roshan
	public List getSchoolList(String adminType, String district, String reptDDo) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List schoolList = null;
		Session session = getSession();
		logger.info("hi i Finding school");
		lSBQuery.append(" select zp.zp_ddo_code,org.ddo_office,zp.rept_ddo_code,zp.final_ddo_code,zp.status from rlt_zp_ddo_map zp  ");
		lSBQuery.append(" ,org_ddo_mst org,mst_dcps_ddo_office mst where org.ddo_code=zp.zp_ddo_code and mst.LOC_ID=org.LOCATION_CODE   ");
		if((district!=null)&&(district!="")&&(Long.parseLong(district)!=-1)){
			lSBQuery.append(" and mst.DISTRICT='"+district+"' ");
		}
		if((adminType!=null)&&(adminType!="")&&(Long.parseLong(adminType)!=-1)){
			lSBQuery.append(" and org.DDO_TYPE="+adminType+"  ");
		}
		if((reptDDo!=null)&&(reptDDo!="")&&(Long.parseLong(reptDDo)!=-1)){
			lSBQuery.append(" and zp.REPT_DDO_CODE='"+reptDDo+"'  ");
		}
		lSBQuery.append(" and zp.status =1 order by zp.ZP_DDO_CODE  ");

		lQuery = session.createSQLQuery(lSBQuery.toString());
		schoolList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return schoolList;
	}

	public List getAllDistrict() {
		List distList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT District_id, district_name FROM cmn_district_mst where lang_id=1 and STATE_ID=15 order by district_name");
		logger.info("District List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		distList = query.list();	
		return distList;
	}

	public List getAllAdminType() {
		List adminTypeList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT admin_code, admin_name FROM zp_admin_name_mst where admin_code in (02,03,04,05,12,13,21,22) or admin_code >20 order by admin_code");
		logger.info("Admin Type List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		adminTypeList = query.list();	
		return adminTypeList;
	}

	public List getAllRegion() {
		List adminTypeList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT REGION_CODE,REGION_NAME FROM ZP_REGION_NAME_MST order by REGION_CODE");
		logger.info("Admin Type List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		adminTypeList = query.list();	
		return adminTypeList;
	}




	public List getReptDDO(String adminType,String districtSelected) {
		List ddoList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT ddo_code,DDO_DESIGNATION_DETAILS FROM CMN_DDO_MST where USED_AS_LEVEL2=1  ");
		if((adminType!=null)&&(adminType!="")&&(Long.parseLong(adminType)!=-1)){
			strQuery.append(" and DDO_OFFICE_TYPE="+adminType+"");
		}
		
		if((districtSelected!=null)&&(districtSelected!="")&&(Long.parseLong(districtSelected)!=-1)){
		//	strQuery.append(" and DISTRICT_ID="+districtSelected+"");
		}
		logger.info("Admin Type List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		ddoList = query.list();	
		return ddoList;
	}
	public List getFinalDDo(String adminTypeSelected, String districtSelected) {
		List ddoList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT ddo_code,DDO_DESIGNATION_DETAILS FROM CMN_DDO_MST where USED_AS_LEVEL3=1  ");
		if((adminTypeSelected!=null)&&(adminTypeSelected!="")&&(Long.parseLong(adminTypeSelected)!=-1)){
			strQuery.append(" and DDO_OFFICE_TYPE="+adminTypeSelected+"");
		}
		
		if((districtSelected!=null)&&(districtSelected!="")&&(Long.parseLong(districtSelected)!=-1)){
			//strQuery.append(" and DISTRICT_ID="+districtSelected+"");
		}

		logger.info("Admin Type List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		ddoList = query.list();	
		return ddoList;
	}


	public void updateDetails(String astDDo, String reptDDO	, String finalDDO) {


		/*	try {
			logger.info("Inside updateDetails....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("insert into RLT_ZP_DDO_MAP_BACKUP values (select * from rlt_zp_ddo_map where zp_ddo_code="+astDDo+")");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}*/

		try {
			logger.info("Inside updateDetails....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update rlt_zp_ddo_map set rept_ddo_code='"+reptDDO+"',");
			SBQuery.append("REPT_DDO_POST_ID=(select post_id from ORG_DDO_MST where ddo_code="+reptDDO+"),");
			SBQuery.append("final_ddo_code='"+finalDDO+"',");
			SBQuery.append("Final_DDO_POST_ID=(select post_id from ORG_DDO_MST where ddo_code= "+finalDDO+"),updated_date=sysdate ");
			SBQuery.append("where zp_ddo_code="+astDDo+"");

			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}

		try {
			logger.info("Inside updateDetails....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update WF_HIERACHY_POST_MPG set POST_ID=(select post_id from org_ddo_mst where ddo_code="+reptDDO+") where LEVEL_id=30 ");
			SBQuery.append("and loc_id=(select LOCATION_CODE from org_ddo_mst where ddo_code="+astDDo+")");


			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}

		try {
			logger.info("Inside updateDetails....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update WF_HIERACHY_POST_MPG set POST_ID=(select post_id from org_ddo_mst where ddo_code="+finalDDO+") where LEVEL_id=40  ");
			SBQuery.append("and loc_id=(select LOCATION_CODE from org_ddo_mst where ddo_code="+astDDo+")");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
	}

	public List getDistOfcList(String adminTypeSelected, String districtSelected,String region) {
		StringBuilder lSBQuery = new StringBuilder();
		Query lQuery = null;
		List schoolList = null;
		Session session = getSession();
		lSBQuery.append(" SELECT zp.DIST_ID,admin.OFFICE_NAME,cmn.DISTRICT_NAME,zp.DIST_MST_OFC_NAME,zp.CURRENT_STATUS,admin.OFFICE_CODE,cmn.DISTRICT_ID FROM ZP_ADMIN_OFFICE_DISTRICT_MPG zp, CMN_DISTRICT_MST cmn , ");
		lSBQuery.append(" ZP_ADMIN_OFFICE_MST  admin where admin.OFFICE_CODE=zp.ADMIN_OFFICE_CODE and cmn.DISTRICT_ID=zp.DIST_CODE ");
		if((districtSelected!=null)&&(districtSelected!="")&&(Long.parseLong(districtSelected)!=-1)){
			lSBQuery.append(" and cmn.DISTRICT_ID="+districtSelected+" ");
		}
		if((adminTypeSelected!=null)&&(adminTypeSelected!="")&&(Long.parseLong(adminTypeSelected)!=-1)){
			lSBQuery.append(" and admin.OFFICE_CODE="+adminTypeSelected+"  ");
		}

		if((region!=null)&&(region!="")&&(Long.parseLong(region)!=-1)){
			lSBQuery.append(" and cmn.REGION_CODE="+region+"  ");
		}

		lSBQuery.append(" order by admin.OFFICE_CODE,cmn.DISTRICT_CODE ");

		lQuery = session.createSQLQuery(lSBQuery.toString());
		schoolList = lQuery.list();
		logger.info("query is"+lQuery.toString());
		return schoolList;
	}
	public void allowDistrictOffice(String allowedOffice, String allOffice){

		try {
			logger.info("Inside updateDetails....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update ZP_ADMIN_OFFICE_DISTRICT_MPG set current_status=1 where dist_id in ("+allowedOffice+") ");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			if(allowedOffice!=null && !allowedOffice.equals("")){
				lQuery.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}

		try {
			logger.info("Inside updateDetails....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update ZP_ADMIN_OFFICE_DISTRICT_MPG set current_status=0 where dist_id in ("+allOffice+")  ");
			if(allowedOffice!=null && !allowedOffice.equals("")){
				SBQuery.append(" and dist_id not in ("+allowedOffice+")");
			}
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);
		}


	}

	public void actDeactMDC(String typeOfAction) {


		try {
			logger.info("Inside actDeactMDC....");
			logger.info("typeOfAction  is "+typeOfAction);

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update org_user_mst set activate_flag="+typeOfAction+" where user_name='SCHOOL_CONFIG' ");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}

		try {
			logger.info("Inside actDeactMDC....");
			logger.info("typeOfAction  is "+typeOfAction);

			StringBuilder SBQuery1 = new StringBuilder();
			SBQuery1.append("update ZP_ADMIN_OFFICE_DISTRICT_MPG set current_status=0 ");
			Query lQuery1 = ghibSession.createSQLQuery(SBQuery1.toString());
			logger.info("the query is ********"+lQuery1.toString());
			if(typeOfAction!=null && !typeOfAction.equals("") && Long.parseLong(typeOfAction)==0){
				lQuery1.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}


	}


	@Override
	public void insertActPeriodForMDC(String typeOfAction, String mdcFromDate,
			String mdcToDate) {
		Session session=getSession();

		StringBuilder SBQuery = new StringBuilder();
		SBQuery.append("update MDC_ACT_TIME_MST set status=0");
		Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
		logger.info("the query is ********"+lQuery.toString());
		lQuery.executeUpdate();


		logger.info("---- insert insertActPeriodForMDC DAo---");
		StringBuffer sb = new StringBuffer();
		sb.append("insert into MDC_ACT_TIME_MST (from_date,to_date,created_date,status) values(:startingDate,:endDate,sysdate,1)");
		logger.info("---- insert insertActPeriodForMDC DAo---"+sb);
		Query query = session.createSQLQuery(sb.toString());
		query.setParameter("startingDate", mdcFromDate);
		query.setParameter("endDate", mdcToDate);
		logger.info("---- query---"+sb);
		query.executeUpdate();

	}

	@Override
	public String updateMDCStatus() {
		try {
			logger.info("Inside updateMDCStatus....");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("select count(1) from MDC_ACT_TIME_MST where status=1 and from_date<sysdate ");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query is ********"+lQuery.toString());
			String frmDateCondition=lQuery.uniqueResult().toString();
			logger.info("the result is ********"+frmDateCondition.toString());

			logger.info("Inside updateMDCStatus....");
			StringBuilder SBQuery1 = new StringBuilder();
			SBQuery1.append("select count(1) from MDC_ACT_TIME_MST where status=1 and to_date>sysdate ");
			Query lQuery1 = ghibSession.createSQLQuery(SBQuery1.toString());
			logger.info("the query is ********"+lQuery1.toString());
			String toDateCondition=lQuery1.uniqueResult().toString();
			logger.info("the result is ********"+toDateCondition.toString());

			StringBuilder SBQuery2 = new StringBuilder();
			SBQuery2.append("update org_user_mst set  ");

			if(frmDateCondition=="1" &&  toDateCondition=="1"){
				SBQuery2.append(" activate_flag=1 where user_name='SCHOOL_CONFIG'");
			}
			else {
				SBQuery2.append(" activate_flag=0 where user_name='SCHOOL_CONFIG'");
			}
			Query lQuery2 = ghibSession.createSQLQuery(SBQuery2.toString());
			logger.info("the query is ********"+lQuery2.toString());
			lQuery2.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);


		}
		return null;
	}

	@Override
	public List getTotalSchoolsConfigReagonWise(String fromDate, String toDate) {    
		List totalSchoolsConf=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.Region_code,reg.Region_NAME,zp.admin_code, zp.admin_name ,count(temp.ddo_code) Total ");
		str.append(" from (select distinct ddo_code, Region_code from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1)");
		if(fromDate !=null && !fromDate.equals("") ){
			str.append(" and created_date>'"+fromDate+"' ");
		}

		if(toDate!=null &&!toDate.equals("")){
			str.append(" and created_date<='"+toDate+"' ");
		}


		str.append(" )) temp inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join ZP_REGION_NAME_MST reg on temp.Region_code=reg.REgion_CODE  ");
		str.append(" where temp.Region_code is not null ");

		str.append(" group by temp.Region_code,reg.Region_NAME,zp.admin_code, zp.admin_name ");
		str.append(" order by temp.Region_code, zp.admin_code");
		logger.info("getTotalSchoolsConfig DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			totalSchoolsConf= query.list();
		}

		logger.info("totalSchoolsConf size: "+totalSchoolsConf.size());
		return totalSchoolsConf;
	}

	@Override
	public List getApprovedSchoolsReagonWise(String fromDate,     
			String toDate) {
		List noOfApprovedSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.Region_code,zp.admin_code ,count(temp.ddo_code) Approved ");
		str.append(" from (select distinct ddo_code, Region_code from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=1 ");
		if(fromDate !=null && !fromDate.equals("") ){
			str.append(" and created_date>'"+fromDate+"' ");
		}

		if(toDate!=null &&!toDate.equals("")){
			str.append(" and created_date<='"+toDate+"' ");
		}

		str.append(" )) temp inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join ZP_REGION_NAME_MST reg on reg.Region_code=temp.Region_code ");
		str.append(" where temp.Region_code is not null ");
		str.append(" group by temp.Region_code,zp.admin_code ");
		str.append(" order by temp.Region_code, zp.admin_code");
		logger.info("getApprovedSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			noOfApprovedSchools= query.list();
		}

		logger.info("noOfApprovedSchools size: "+noOfApprovedSchools.size());
		return noOfApprovedSchools;
	}

	@Override
	public List getPendingSchoolsReagonWise(String fromDate, String toDate) {
		List noOfpendingSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.Region_code,zp.admin_code ,count(temp.ddo_code) Pending ");
		str.append(" from (select distinct ddo_code, Region_code from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=0 ");
		if(fromDate !=null && !fromDate.equals("") ){
			str.append(" and created_date>'"+fromDate+"' ");
		}

		if(toDate!=null &&!toDate.equals("")){
			str.append(" and created_date<='"+toDate+"' ");
		}

		str.append(" )) temp inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join ZP_REGION_NAME_MST reg on temp.Region_code=reg.Region_code ");
		str.append(" where temp.Region_code is not null ");
		str.append(" group by temp.Region_code,zp.admin_code ");
		str.append(" order by temp.Region_code, zp.admin_code");
		logger.info("getPendingSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			noOfpendingSchools= query.list();
		}

		logger.info("noOfpendingSchools size: "+noOfpendingSchools.size());
		return noOfpendingSchools;
	}

	@Override
	public List getRejectedSchoolsReagonWise(String fromDate, String toDate) {
		List noOfRejectedSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.Region_code,zp.admin_code ,count(temp.ddo_code) Rejected ");
		str.append(" from (select distinct ddo_code, Region_code from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=-1 ");
		if(fromDate !=null && !fromDate.equals("") ){
			str.append(" and created_date>'"+fromDate+"' ");
		}

		if(toDate!=null &&!toDate.equals("")){
			str.append(" and created_date<='"+toDate+"' ");
		}
		str.append(" )) temp inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join ZP_REGION_NAME_MST reg on reg.Region_code=temp.Region_code ");
		str.append(" where temp.Region_code is not null ");
		str.append(" group by temp.Region_code,zp.admin_code ");
		str.append(" order by temp.Region_code, zp.admin_code");
		logger.info("getRejectedSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			noOfRejectedSchools= query.list();
		}

		logger.info("noOfRejectedSchools size: "+noOfRejectedSchools.size());
		return noOfRejectedSchools;
	}

	public List getDataEntryInitiatedSchoolsReagonWise(String fromDate, String toDate) {     
		List dataEntryInitiated=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.Region_code,zp.admin_code ,count(distinct(emp.ddo_code)) schools_initiated ");
		str.append(" from (select distinct ddo_code, Region_code from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1) ");
		if(fromDate !=null && !fromDate.equals("") ){
			str.append(" and created_date>'"+fromDate+"' ");
		}

		if(toDate!=null &&!toDate.equals("")){
			str.append(" and created_date<='"+toDate+"' ");
		}

		str.append(" )) temp inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join mst_dcps_emp emp on emp.ddo_code=temp.ddo_code ");
		str.append(" left outer join ZP_REGION_NAME_MST reg on reg.Region_code=temp.Region_code ");
		str.append(" where  temp.Region_code is not null and emp.form_status in(0,1,-1) and emp.reg_status in (0,1,2) and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.Region_code,zp.admin_code ");
		str.append(" order by temp.Region_code, zp.admin_code");
		logger.info("getDataEntryInitiatedSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			dataEntryInitiated= query.list();
		}


		logger.info("dataEntryInitiated size: "+dataEntryInitiated.size());
		return dataEntryInitiated;
	}



	@Override
	public List definedLevel2DDOList(String adminTypeSelected, String districtSelected) {
		List ddoList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT distinct rlt.rept_ddo_code,mst.OFF_NAME FROM mst_dcps_ddo_office mst,RLT_ZP_DDO_MAP rlt,ORG_DDO_MST org  ");
		strQuery.append("where mst.ddo_code=rlt.rept_DDO_CODE and org.DDO_CODE=rlt.ZP_DDO_CODE and org.DDO_TYPE="+adminTypeSelected+" and mst.district='"+districtSelected+"' ");
		logger.info("Level 2 DDO List: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		ddoList = query.list();	
		return ddoList;
	}
	public List getTotalSchoolsConfig() {

		List totalSchoolsConf=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,dist.district_name,zp.admin_code, zp.admin_name ,count(temp.ddo_code) Total ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,dist.district_name,zp.admin_code, zp.admin_name ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getTotalSchoolsConfig DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			totalSchoolsConf= query.list();
		}

		logger.info("totalSchoolsConf size: "+totalSchoolsConf.size());
		return totalSchoolsConf;
	}

	@Override
	public List getApprovedSchools() {
		List noOfApprovedSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(temp.ddo_code) Approved ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getApprovedSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			noOfApprovedSchools= query.list();
		}

		logger.info("noOfApprovedSchools size: "+noOfApprovedSchools.size());
		return noOfApprovedSchools;
	}

	@Override
	public List getPendingSchools() {
		List noOfpendingSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(temp.ddo_code) Pending ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=0)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getPendingSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			noOfpendingSchools= query.list();
		}

		logger.info("noOfpendingSchools size: "+noOfpendingSchools.size());
		return noOfpendingSchools;
	}

	@Override
	public List getRejectedSchools() {
		List noOfRejectedSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(temp.ddo_code) Rejected ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=-1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getRejectedSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			noOfRejectedSchools= query.list();
		}

		logger.info("noOfRejectedSchools size: "+noOfRejectedSchools.size());
		return noOfRejectedSchools;
	}

	public List getDataEntryInitiatedSchools() {
		List dataEntryInitiated=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(distinct(emp.ddo_code)) schools_initiated ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join mst_dcps_emp emp on emp.ddo_code=temp.ddo_code ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 and emp.form_status in(0,1,-1) and emp.reg_status in (0,1,2) and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getDataEntryInitiatedSchools DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			dataEntryInitiated= query.list();
		}


		logger.info("dataEntryInitiated size: "+dataEntryInitiated.size());
		return dataEntryInitiated;
	}

	public List getTotalEmpConfig() {

		List totalEmpConf=null;
		StringBuffer str= new StringBuffer();
		str.append(" select temp.district,dist.district_name, zp.admin_code, zp.admin_name, count(emp.ddo_code) as Total_Emp_Configured from ");
		str.append(" (select distinct ddo_code, district, off_name, taluka from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status =1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" inner join CMN_TALUKA_MST takl on temp.taluka= takl.taluka_id ");
		str.append(" inner join ifms.RLT_ZP_DDO_MAP map on map.zp_ddo_code= temp.ddo_code ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(" where emp.zp_status in (-1,0,1,2,3,4,10) and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name ");
		str.append(" order by temp.district, zp.admin_code ");
		logger.info("getTotalEmpConfig DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			totalEmpConf= query.list();
		}

		logger.info("totalEmpConf size: "+totalEmpConf.size());
		return totalEmpConf;
	}

	@Override
	public List getDraftForms() {
		List saveAsDraft=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district, zp.admin_code, count(emp.ddo_code) as Total_Emp_Draft from ");
		str.append(" (select distinct ddo_code, district, off_name from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status =1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(" where  emp.zp_status=0 and dist.lang_id=1 and emp.created_date>'2012-08-15'");
		str.append(" group by temp.district, zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getDraftForms DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			saveAsDraft= query.list();
		}

		logger.info("saveAsDraft size: "+saveAsDraft.size());
		return saveAsDraft;
	}

	@Override
	public List getForwardedForms() {
		List forwardForms=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code,count(emp.ddo_code) as Total_Emp_Approved from ");
		str.append(" (select distinct ddo_code, district, off_name, loc_id from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status =1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		//added by roshan
		str.append(" inner join Org_Designation_Mst DM on emp.designation=DM.dsgn_Id ");
		//end by roshan
		str.append(" where emp.zp_status in (2,3,4) and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getForwardedForms DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			forwardForms= query.list();
		}

		logger.info("forwardForms size: "+forwardForms.size());
		return forwardForms;
	}

	@Override
	public List getApprovedForms() {
		List approvedForms=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code,count(emp.ddo_code) as Total_Emp_Approved from ");
		str.append(" (select distinct ddo_code, district, off_name, loc_id from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status =1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(" where emp.zp_status=10 and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getApprovedForms DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			approvedForms= query.list();
		}

		logger.info("getApprovedForms size: "+approvedForms.size());
		return approvedForms;
	}

	public List getRejectedForms() {
		List rejectedForms=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code,count(emp.ddo_code) as Total_Emp_Rejected from ");
		str.append(" (select distinct ddo_code, district, off_name, loc_id from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status =1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(" where emp.zp_status=-1 and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getRejectedForms DAO------"+str.toString());
		Query query= ghibSession.createSQLQuery(str.toString());

		if((query.list()!=null)){
			rejectedForms= query.list();
		}


		logger.info("getRejectedForms size: "+rejectedForms.size());
		return rejectedForms;
	}


	public String getSchoolNameForDiseCode(String diseCode,String ddoCode) {
		Session hibSession = getSession();

		String schoolName=null;
		List schoolList=null;
		StringBuffer sb= new StringBuffer();
		gLogger.info("diseCode: "+diseCode);

		sb.append("SELECT OFFICE_NAME FROM ZP_ADMIN_DICE_MPG where DICE_CODE = '"+diseCode+"'");
		if(ddoCode!=null && !ddoCode.equals("")){
		sb.append(" and DISTRICT_ID in (select district from mst_dcps_ddo_office where ddo_code='"+ddoCode+"')");	
		}
		gLogger.info("Query to get school name using dice code:  " + sb.toString());
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());
		schoolList=sqlQuery.list();
		if(schoolList.size()==0)
		{
			schoolName="not_available#0";
		}
		else
		{
			schoolName=(schoolList.get(0).toString())+"#"+schoolList.size();
		}
		gLogger.info("schoolName: "+schoolName);

		return schoolName;
	}


	public void insertDiseCodeDetails(String schoolName, String diseCode, String officeCode, String districtId) {
		try {
			logger.info("Inside insertDiseCodeDetails");
			logger.info("dise code: "+diseCode+" schoolName"+schoolName+" officeCode: "+officeCode+" districtId: "+districtId);

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("INSERT INTO ZP_ADMIN_DICE_MPG (DICE_ID,ADMIN_TYPE,DICE_CODE,OFFICE_NAME,USED,DISTRICT_ID) VALUES  ");
			SBQuery.append("((select max(DICE_ID) from ZP_ADMIN_DICE_MPG)+1,'"+officeCode+"','"+diseCode+"','"+schoolName+"','0','"+districtId+"')");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query to insert in to ZP_ADMIN_DICE_MPG is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
	}


	public void updateDiseCodeDetails(String schoolName, String diseCode) {
		try {
			logger.info("Inside updateDiseCodeDetails");
			logger.info("dise code: "+diseCode+" schoolName"+schoolName);

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update ZP_ADMIN_DICE_MPG set OFFICE_NAME = '"+schoolName+"' where DICE_CODE= '"+diseCode+"'");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query to update school name is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
	}

	@Override
	public List getSchoolList(String ddoCode) {
		List schoolList=null;
		StringBuffer str= new StringBuffer();
		str.append(" select rlt.zp_ddo_code, mst.off_name,mst.dice_code,org.ddo_name,mst.TEL_NO2 from rlt_zp_ddo_map rlt inner join mst_dcps_ddo_office mst  ");
		str.append(" on rlt.zp_ddo_code=mst.ddo_code inner join org_ddo_mst org on org.ddo_code=mst.ddo_code where upper(mst.ddo_office)='YES' and rlt.rept_ddo_code="+ddoCode+" or rlt.final_ddo_code="+ddoCode+" ");
		str.append(" order  by rlt.zp_ddo_code");
		Query query= ghibSession.createSQLQuery(str.toString());
		logger.info("schoolList query.........: "+query.toString());
		return query.list();
	}

	@Override
	public void updateDiseDetails(String ddoCode, String dise, String prefix,String mobNo) {
		try {
			logger.info("Inside updateDiseCodeDetails");
			logger.info("dise code: "+dise+" schoolName"+prefix);

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("update mst_dcps_ddo_office set dice_code = '"+prefix+""+dise+"',tel_no2='"+mobNo+"' where ddo_code= '"+ddoCode+"'");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query to update school name is ********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
		
	}

	@Override
	public int getCOunt(String dise, String ddoCode) {
		int res=0;
		try {
			logger.info("Inside getCOunt");
			logger.info("dise code: "+dise+" schoolName"+ddoCode);

			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("select ddo_code from mst_dcps_ddo_office where dice_code='"+dise+"' and ddo_code <> '"+ddoCode+"'");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query to getCOunt is ********"+lQuery.toString());
			res=lQuery.list().size();
			logger.info("Res size is**********"+res);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
		return res;
	}
	


	public List getGRlList() {
		List grList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT GR_ID,GR_NAME,GR_DATE,ATTACHEMENT_ID FROM GR_DTLS order by GR_ID  desc");
		logger.info("gr Type List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		grList = query.list();	
		return grList;
	}



	//added by samadhan for region filter in allow office
	public List getDistListForRegion(String strRegion) {

		List lstDist = null;
		gLogger.info("strRegion in dao: "+strRegion);
		List lLstReturnList=null;

		try {
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT DISTRICT_NAME,DISTRICT_ID FROM CMN_DISTRICT_MST where REGION_CODE = '"+strRegion+"' ");
			
			Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			gLogger.info("query to get dist List: "+lSBQuery.toString());



			if(lQuery.list()!=null){
				lstDist= lQuery.list();
				gLogger.info("lstDist.size(): "+lstDist.size());
			}

			ComboValuesVO lObjComboValuesVO = null;
			if (lstDist != null && lstDist.size() != 0) {
				lLstReturnList = new ArrayList<Object>();
				Object obj[];
				for (int liCtr = 0; liCtr < lstDist.size(); liCtr++) {

					obj = (Object[]) lstDist.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[1].toString());

					//String desc=obj[0].toString()+"-"+obj[1].toString();

					lObjComboValuesVO.setDesc(obj[0].toString());
					lLstReturnList.add(lObjComboValuesVO);
				}
			} else {
				lLstReturnList = new ArrayList<Object>();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-1");
				lObjComboValuesVO.setDesc("--Select--");
				lLstReturnList.add(lObjComboValuesVO);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			gLogger.error("Error is :" + e, e);

		}
		return lLstReturnList;
	}

	@Override
	public void createNewGR(String grName, String grCreatedDate,
			long attachment_Id_order) {
		// TODO Auto-generated method stub
		try {
			logger.info("Inside createNewGR");
			StringBuilder SBQuery = new StringBuilder();
			SBQuery.append("INSERT INTO GR_DTLS (GR_NAME,CREATED_DATE,ATTACHEMENT_ID,GR_DATE) VALUES ('"+grName+"',sysdate,"+attachment_Id_order+",'"+grCreatedDate+"')");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("the query to insert gr is********"+lQuery.toString());
			lQuery.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
		
		
		
		
	}


}
