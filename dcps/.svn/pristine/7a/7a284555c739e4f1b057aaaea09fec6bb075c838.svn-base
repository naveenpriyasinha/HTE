package com.tcs.sgv.nps.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.nps.valueobject.FrmFormS1Dtls;
import com.tcs.sgv.nps.valueobject.FrmFormS1NpsDtls;

import javassist.bytecode.Descriptor.Iterator;
//import net.sf.hibernate.Criteria;
//import net.sf.hibernate.expression.Criterion;

import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttdocMst;

public class FormS1UpdateDAOImpl extends GenericDaoHibernateImpl implements FormS1UpdateDAO{
	private final Log gLogger = LogFactory.getLog(getClass());
	Session ghibSession = null;

	public FormS1UpdateDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		ghibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List getEmpListForFrmS1Edit(String strDDOCode, String flag, String txtSearch, String isDeputation) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		strQuery.append(" select empmst.SEVARTH_ID,empmst.EMP_NAME,VARCHAR_FORMAT(org.EMP_DOJ, 'dd/MM/yyyy') as DOJ, ");
		strQuery.append(" desig.DSGN_NAME,  zp.ZP_DDO_CODE, mstddo.OFF_NAME, ");
		strQuery.append(" empmst.DCPS_ID, empmst.DCPS_EMP_ID ");
		strQuery.append(" from MST_DCPS_EMP empmst ");
		strQuery.append(" inner join ifms.org_emp_mst org on org.EMP_ID = empmst.ORG_EMP_MST_ID ");
		strQuery.append(" inner join ifms.ORG_USERPOST_RLT userpost on userpost.USER_ID=org.USER_ID  ");
		strQuery.append(" inner join ifms.ORG_POST_MST post on post.POST_ID=userpost.POST_ID  and post.ACTIVATE_FLAG = 1 ");
        strQuery.append("  inner join ifms.RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE = empmst.DDO_CODE  ");
		strQuery.append(" inner join ifms.MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID ");
		strQuery.append(" inner join ifms.ORG_DESIGNATION_MST desig on empmst.DESIGNATION=desig.DSGN_ID ");
		strQuery.append(" where (empmst.EMP_SERVEND_DT > sysdate OR empmst.EMP_SERVEND_DT is null) ");
		strQuery.append(" and zp.REPT_DDO_CODE='"+strDDOCode+"' ");
		strQuery.append(" and empmst.DCPS_OR_GPF='Y' ");
		//PRAN_NO ,		PRAN_ACTIVE
		strQuery.append(" and empmst.PRAN_NO is null ");
		strQuery.append(" and not exists (select null FROM ifms.FRM_FORM_S1_DTLS_1 frms1 where empmst.SEVARTH_ID=frms1.sevarth_id and STATUS_FLAG=1) "
				+ "and userpost.ACTIVATE_FLAG = 1 and post.ACTIVATE_FLAG = 1 ");
		if(flag.equals("sevarthId"))
		{
			strQuery.append(" and empmst.SEVARTH_ID='"+txtSearch.trim().toUpperCase()+"' ");
		}
        if (flag.equals("dcpsId")) {
            strQuery.append(" and empmst.DCPS_ID='" + txtSearch.trim().toUpperCase() + "' ");
        }
		if(flag.equals("dsgn"))
		{
			strQuery.append(" and desig.DSGN_ID  = '"+txtSearch.trim().toUpperCase()+"' ");
		}
		strQuery.append(" group by empmst.SEVARTH_ID,empmst.EMP_NAME,org.EMP_DOJ,desig.DSGN_NAME ,  zp.ZP_DDO_CODE,mstddo.OFF_NAME,  ");
		strQuery.append(" empmst.DCPS_ID ,empmst.DCPS_EMP_ID ");
		strQuery.append(" order by zp.ZP_DDO_CODE, empmst.EMP_NAME  ");

		logger.info("Query to get Emp List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		empList = query.list();
		logger.info("empList is " + empList.size());
		return empList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getDtoDetails(String strDDOCode,String EmpDDOCode) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List DtoList = null;
		strQuery.append(" select dto.USER_ID,dto.DDO_REG_NO from ifms.MST_DTO_REG dto  ");
		strQuery.append("inner join  ifms.RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE = dto.DDO_CODE  "
				+ " where  zp.REPT_DDO_CODE='"+strDDOCode+"' and  zp.ZP_DDO_CODE='"+EmpDDOCode+"'   and zp.status=1 ");

		logger.info("Query to get Emp details For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		DtoList = query.list();
		logger.info("DtoList is " + DtoList.size());
		return DtoList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List getDtoDetailsCSRF(String strDDOCode,String EmpDDOCode) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List DtoList = null;
		strQuery.append(" select dto.DTO_REG_NO,dto.DDO_REG_NO from ifms.MST_DTO_REG dto  ");
		String condStr="";
		if(strDDOCode.equals(EmpDDOCode)) {
			condStr=" or ";
		}else
		{
			condStr=" and ";
		}
		strQuery.append("inner join  ifms.RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE = dto.DDO_CODE  "
				+ " where  zp.REPT_DDO_CODE='"+strDDOCode+"' "+condStr+"  zp.ZP_DDO_CODE='"+EmpDDOCode+"'   and zp.status=1 ");  

		logger.info("Query to get Emp details For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		DtoList = query.list();
		logger.info("DtoList is " + DtoList.size());
		return DtoList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List getEmpForFrmS1Edit(String strDDOCode,String sevaarthId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		 
		strQuery.append(" select empmst.SEVARTH_ID,empmst.DCPS_ID,desig.DSGN_NAME,empmst.SALUTATION,empmst.EMP_NAME,zp.ZP_DDO_CODE,mstddo.OFF_NAME, "
				+ "empmst.FATHER_OR_HUSBAND,VARCHAR_FORMAT(org.EMP_DOB, 'dd/MM/yyyy') as DOB, ");
		strQuery.append(" VARCHAR_FORMAT(empmst.GENDER),'' as ,empmst.PAN_NO,empmst.UID_NO,VARCHAR_FORMAT(org.EMP_DOJ, 'dd/MM/yyyy') as DOJ,");
		strQuery.append(" VARCHAR_FORMAT(empmst.SUPER_ANN_DATE, 'dd/MM/yyyy') as DOR,trim(empmst.emp_group),loc_dept.LOC_NAME as department,'' as department_ministry,");
		strQuery.append(" empmst.BASIC_PAY,pscal.SCALE_DESC,empmst.ADDRESS_BUILDING, ");
		strQuery.append("empmst.ADDRESS_STREET, empmst.ADDRESS_VTC,'' as landmark,empmst.DISTRICT,empmst.STATE,empmst.PINCODE,empmst.CELL_NO,empmst.EMAIL_ID,"
				+ "empmst.BANK_ACNT_NO,bnk.BANK_NAME,");
		strQuery.append("bnkbr.BRANCH_NAME,'Bank_brach_add' as Bank_brach_add,loc.LOC_PIN,loc.LOC_STATE_ID,empmst.IFSC_CODE,empmst.DCPS_EMP_ID,org.cadre,");
		strQuery.append("empmst.LOC_ID ,desig.DSGN_NAME,loc_dept.LOC_NAME from ifms.MST_DCPS_EMP empmst  ");
		strQuery.append("inner join ifms.org_emp_mst org on org.EMP_ID = empmst.ORG_EMP_MST_ID  ");
		strQuery.append("inner join ifms.ORG_USERPOST_RLT userpost on userpost.USER_ID=org.USER_ID ");
		strQuery.append("inner join ifms.ORG_POST_MST post on post.POST_ID=userpost.POST_ID  and post.ACTIVATE_FLAG = 1 "); 
		strQuery.append("inner join ifms.RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE = empmst.DDO_CODE   ");
		strQuery.append("inner join ifms.MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID  ");
		strQuery.append("inner join ifms.ORG_DESIGNATION_MST desig on empmst.DESIGNATION=desig.DSGN_ID  ");
		strQuery.append("inner join ifms.MST_BANK_PAY as bnk on empmst.BANK_NAME=bnk.BANK_CODE ");
		strQuery.append("left join ifms.RLT_BANK_BRANCH_PAY as bnkbr on  bnkbr.BRANCH_CODE=empmst.BRANCH_NAME ");
		strQuery.append("left join ifms.CMN_LOCATION_MST as loc on loc.LOC_ID= bnkbr.LOCATION_CODE ");
		strQuery.append("inner join ifms.CMN_LOCATION_MST as loc_dept on loc_dept.LOC_ID= empmst.PARENT_DEPT ");
		strQuery.append("inner join ifms.HR_EIS_SCALE_MST as pscal on pscal.SCALE_ID=empmst.PAYSCALE ");
		strQuery.append(" where (empmst.EMP_SERVEND_DT > sysdate OR empmst.EMP_SERVEND_DT is null) ");
		strQuery.append(" and zp.REPT_DDO_CODE='"+strDDOCode+"' and empmst.DCPS_OR_GPF='Y'  and ");
		strQuery.append(" not exists (select null FROM FRM_FORM_S1_DTLS_1 frms1 where empmst.SEVARTH_ID=frms1.sevarth_id  ) "
				+ "and userpost.ACTIVATE_FLAG = 1 and post.ACTIVATE_FLAG = 1 ");
		strQuery.append(" and empmst.SEVARTH_ID='"+sevaarthId.trim().toUpperCase()+"' ");

		logger.info("Query to get Emp details For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		empList = query.list();
		logger.info("empList is " + empList.size());
		return empList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List getEmpNomineeList(String sevaarthId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empNomineeList=null;
		strQuery.append(" select a.DCPS_NMN_NAME,VARCHAR_FORMAT(a.DCPS_NMN_DOB, 'dd/mm/YYYY'),trim(a.DCPS_NMN_RLT),a.DCPS_NMN_SHARE,year(current_date)- year(a.DCPS_NMN_DOB ) as age FROM ifms.MST_DCPS_EMP_NMN_DETAILS as a left join ifms.mst_dcps_emp as b on a.DCPS_EMP_ID=b.DCPS_EMP_ID where b.sevarth_id='"+sevaarthId+"' ");

		logger.info("Query to get nominee List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		
		empNomineeList=query.list();

		return empNomineeList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List getRelationList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(" SELECT  LOOKUP_ID,trim(LOOKUP_SHORT_NAME) FROM CMN_LOOKUP_MST where PARENT_LOOKUP_ID = 230045 and LOOKUP_ID <>230052 ");

		logger.info("Query to get relations List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List getTitleList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("select LOOKUP_ID,LOOKUP_NAME,LOOKUP_DESC from  ifms.CMN_LOOKUP_MST  where PARENT_LOOKUP_ID='700074'");

		logger.info("Query to get relations List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getClassList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(" select LOOKUP_ID,LOOKUP_NAME  from ifms.CMn_lookup_mst where PARENT_LOOKUP_ID= 700055 ");

		logger.info("Query to get relations List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getBankList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//strQuery.append(" SELECT  LOOKUP_ID,LOOKUP_SHORT_NAME FROM CMN_LOOKUP_MST where PARENT_LOOKUP_ID = 230045 and LOOKUP_ID <>230052 ");
		strQuery.append(" select BANK_CODE,BANK_NAME from ifms.MST_BANK_PAY where LANG_ID=1");
		

		logger.info("Query to get Bank List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List getStateList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
//		strQuery.append(" select STATE_CODE,STATE_NAME from ifms.CMN_STATE_MST where LANG_ID=1");
		strQuery.append(" select state_nps_code ,STATE_NAME,APPLICATION_CODE from ifms.nps_state_mst_map ");
		logger.info("Query to get State List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List getPayScaleList() {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//strQuery.append(" SELECT  LOOKUP_ID,LOOKUP_SHORT_NAME FROM CMN_LOOKUP_MST where PARENT_LOOKUP_ID = 230045 and LOOKUP_ID <>230052 ");
		strQuery.append(" SELECT SCALE_DESC,SCALE_START_AMT,SCALE_END_AMT FROM ifms.HR_EIS_SCALE_MST where SCALE_ID in "
				+ "(SELECT PAYSCALE FROM ifms.MST_DCPS_EMP where DDO_CODE in (select zp_DDO_CODE from ifms.RLT_ZP_DDO_MAP where status=1 ))");
		logger.info("Query to get Pay Scale List For Frm S1 Edit is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		return query.list();
	}
	

	@Override
	public void insertRecordToS1(FrmFormS1Dtls ffs,String doj, String nominee1DOB, String nominee2DOB, String nominee3DOB,String strDDOCode, Long lLngPkIdForFormS1) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		String ddoc=strDDOCode.substring(0,2);
		gLogger.info(" ddoc "+ddoc);
		gLogger.info(" doj "+doj);
		gLogger.info("nominee1DOB "+nominee1DOB);
		gLogger.info("nominee2DOB "+nominee2DOB);
		gLogger.info("nominee3DOB "+nominee3DOB);

		strQuery.append(" insert into FRM_FORM_S1_DTLS ");
		strQuery.append(" (FORM_S1_ID,SEVARTH_ID,EMP_NAME,DCPS_ID,DESIGNATION, ");
		strQuery.append(" DOJ,DDO_CODE,FATHER_NAME, ");
		strQuery.append(" FLAT_UNIT_NO,BUILDING_NAME, ");
		strQuery.append(" LOCALITY,DISTRICT, ");
		strQuery.append(" STATE,COUNTRY, ");
		strQuery.append(" PIN_CODE, ");
		strQuery.append(" PFLAT_UNIT_NO,PBUILDING_NAME, ");
		strQuery.append(" PLOCALITY,PDISTRICT, ");
		strQuery.append(" PSTATE,PCOUNTRY, ");
		strQuery.append(" PPIN_CODE, ");
		strQuery.append(" STD_CODE,PHONE_NO, ");
		strQuery.append(" MOBILE_NO,EMAIL_ID, ");
		strQuery.append(" NMN_1_NAME,NMN_1_DOB,NMN_1_RELATIONSHIP, ");
		strQuery.append(" NMN_1_SHARE,NMN_1_GUARD_NAME,NMN_1_INVALID_CONDN, ");
		strQuery.append(" NMN_2_NAME,NMN_2_DOB,NMN_2_RELATIONSHIP, ");
		strQuery.append(" NMN_2_SHARE,NMN_2_GUARD_NAME,NMN_2_INVALID_CONDN, ");
		strQuery.append(" NMN_3_NAME,NMN_3_DOB,NMN_3_RELATIONSHIP, ");
		strQuery.append(" NMN_3_SHARE,NMN_3_GUARD_NAME,NMN_3_INVALID_CONDN, ");
		strQuery.append(" CREATED_DATE,TREASURY_EMP_COUNT,NSDL_STATUS,DEP_UPDATED_DDO_CD,NOMINEE_ADDRESS_1,NOMINEE_ADDRESS_2,NOMINEE_ADDRESS_3) ");
		strQuery.append(" values(:FORM_S1_ID,:SEVARTH_ID,:EMP_NAME,:DCPS_ID,:DESIGNATION,");
		strQuery.append(" :DOJ,:DDO_CODE,:FATHER_NAME, ");
		strQuery.append(" :PRESENT_ADD_FLAT_UNIT_NO_BLOCK_NO,:PRESENT_ADDRESS_NAME_OF_PREMISE_BUILDING_VILLAGE, ");
		strQuery.append(" :PRESENT_ADDRESS_AREA_LOCALITY_TALUKA,:PRESENT_ADDRESS_DISTRICT_TOWN_CITY, ");
		strQuery.append(" :PRESENT_ADDRESS_STATE_UNION_TERRITORY,:PRESENT_ADDRESS_COUNTRY, ");
		strQuery.append(" :PRESENT_ADDRESS_PIN_CODE,  ");
		strQuery.append(" :PERMANENT_ADD_FLAT_UNIT_NO_BLOCK_NO,:PERMANENT_ADDRESS_NAME_OF_PREMISE_BUILDING_VILLAGE, ");
		strQuery.append(" :PERMANENT_ADDRESS_AREA_LOCALITY_TALUKA,:PERMANENT_ADDRESS_DISTRICT_TOWN_CITY,   ");
		strQuery.append(" :PERMANENT_ADDRESS_STATE_UNION_TERRITORY,:PERMANENT_ADDRESS_COUNTRY, ");
		strQuery.append(" :PERMANENT_ADDRESS_PIN_CODE, ");
		strQuery.append(" :PHONE_NO_STD_CODE,:PHONE_NO_PHONE_NO, ");
		strQuery.append(" :MOBILE_NO,:EMAIL_ID, ");
		strQuery.append(" :NOMINEE_1_NAME,:NOMINEE_1_DOB,:NOMINEE_1_RELATIONSHIP, ");
		strQuery.append(" :NOMINEE_1_PERCENT_SHARE,:NOMINEE_1_GUARDIAN_NAME,:NOMINEE_1_NOMINATION_INVALID_CONDITION, ");
		strQuery.append(" :NOMINEE_2_NAME,:NOMINEE_2_DOB,:NOMINEE_2_RELATIONSHIP, ");
		strQuery.append(" :NOMINEE_2_PERCENT_SHARE,:NOMINEE_2_GUARDIAN_NAME,:NOMINEE_2_NOMINATION_INVALID_CONDITION, ");
		strQuery.append(" :NOMINEE_3_NAME,:NOMINEE_3_DOB,:NOMINEE_3_RELATIONSHIP, ");
		strQuery.append(" :NOMINEE_3_PERCENT_SHARE,:NOMINEE_3_GUARDIAN_NAME,:NOMINEE_3_NOMINATION_INVALID_CONDITION, ");
		strQuery.append(" sysdate,(SELECT max(treasury_emp_count)+1 FROM frm_form_s1_dtls where substr(ddo_code,1,4) like '"+ddoc+"%'),null,null)");

		logger.info("Query to save Frm S1 is: " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		
		
		gLogger.info("FORM_S1_ID: "+lLngPkIdForFormS1);
		gLogger.info("EMP_NAME: "+ffs.getEmpName());
		gLogger.info("DCPS_ID: "+ffs.getDcpsId());
		gLogger.info("DESIGNATION: "+ffs.getDesignation());
		gLogger.info("DOJ: "+doj);
		gLogger.info("DDO_CODE: "+strDDOCode);//get ddo loacaly
		gLogger.info("FATHER_NAME: "+ffs.getEmpFatherName());
		gLogger.info("PRESENT_ADD_FLAT_UNIT_NO_BLOCK_NO: "+ffs.getPresentAddFlatNo());
		gLogger.info("PRESENT_ADDRESS_NAME_OF_PREMISE_BUILDING_VILLAGE: "+ffs.getPresentAddBuilding());
		gLogger.info("PRESENT_ADDRESS_AREA_LOCALITY_TALUKA: "+ffs.getPresentAddTaluka());
		gLogger.info("PRESENT_ADDRESS_DISTRICT_TOWN_CITY: "+ffs.getPresentAddDist());
		gLogger.info("PRESENT_ADDRESS_STATE_UNION_TERRITORY: "+ffs.getPresentAddState());
		gLogger.info("PRESENT_ADDRESS_COUNTRY: "+"India");
		gLogger.info("PRESENT_ADDRESS_PIN_CODE: "+ffs.getPresentAddPin());
		gLogger.info("PERMANENT_ADD_FLAT_UNIT_NO_BLOCK_NO: "+ffs.getPermanentAddFlatNo());
		gLogger.info("PERMANENT_ADDRESS_NAME_OF_PREMISE_BUILDING_VILLAGE: "+ffs.getPermanentAddBuilding());
		gLogger.info("PERMANENT_ADDRESS_AREA_LOCALITY_TALUKA: "+ffs.getPermanentAddTaluka());
		gLogger.info("PERMANENT_ADDRESS_DISTRICT_TOWN_CITY: "+ffs.getPermanentAddDist());
		gLogger.info("PERMANENT_ADDRESS_STATE_UNION_TERRITORY: "+ffs.getPermanentAddState());
		gLogger.info("PERMANENT_ADDRESS_COUNTRY: "+"India");
		gLogger.info("PERMANENT_ADDRESS_PIN_CODE: "+ffs.getPermanentAddPin());
		gLogger.info("PHONE_NO_STD_CODE: "+ffs.getPhoneSTDCode());
		gLogger.info("PHONE_NO_PHONE_NO: "+ffs.getPhoneNo());
		gLogger.info("MOBILE_NO: "+"+91"+ffs.getMobileNo());
		gLogger.info("EMAIL_ID: "+ffs.getEmailId());
		gLogger.info("NOMINEE_1_NAME: "+ffs.getNominee1Name());
		gLogger.info("NOMINEE_1_DOB: "+nominee1DOB);
		gLogger.info("NOMINEE_1_RELATIONSHIP: "+ffs.getNominee1Relation());
		gLogger.info("NOMINEE_1_PERCENT_SHARE: "+ffs.getNominee1Percent());
		gLogger.info("NOMINEE_1_GUARDIAN_NAME: "+ffs.getNominee1Guardian());
		gLogger.info("NOMINEE_1_NOMINATION_INVALID_CONDITION: "+ffs.getNominee1InvalidCondition());
		gLogger.info("NOMINEE_2_NAME: "+ffs.getNominee2Name());
		gLogger.info("NOMINEE_2_DOB: "+nominee2DOB);
		gLogger.info("NOMINEE_2_RELATIONSHIP: "+ffs.getNominee2Relation());
		gLogger.info("NOMINEE_2_PERCENT_SHARE: "+ffs.getNominee2Percent());
		gLogger.info("NOMINEE_2_GUARDIAN_NAME: "+ffs.getNominee2Guardian());
		gLogger.info("NOMINEE_2_NOMINATION_INVALID_CONDITION: "+ffs.getNominee2InvalidCondition());
		gLogger.info("NOMINEE_3_NAME: "+ffs.getNominee3Name());
		gLogger.info("NOMINEE_3_DOB: "+nominee3DOB);
		gLogger.info("NOMINEE_3_RELATIONSHIP: "+ffs.getNominee3Relation());
		gLogger.info("NOMINEE_3_PERCENT_SHARE: "+ffs.getNominee3Percent());
		gLogger.info("NOMINEE_3_GUARDIAN_NAME: "+ffs.getNominee3Guardian());
		gLogger.info("NOMINEE_3_NOMINATION_INVALID_CONDITION: "+ffs.getNominee3InvalidCondition());
		
		query.setParameter("FORM_S1_ID",lLngPkIdForFormS1);	
		query.setParameter("SEVARTH_ID",ffs.getSevarthId());
		query.setParameter("EMP_NAME",ffs.getEmpName());
		query.setParameter("DCPS_ID",ffs.getDcpsId());
		query.setParameter("DESIGNATION",ffs.getDesignation());
		query.setParameter("DOJ",doj);
		query.setParameter("DDO_CODE",strDDOCode);
		query.setParameter("FATHER_NAME",ffs.getEmpFatherName());
		query.setParameter("PRESENT_ADD_FLAT_UNIT_NO_BLOCK_NO",ffs.getPresentAddFlatNo());
		query.setParameter("PRESENT_ADDRESS_NAME_OF_PREMISE_BUILDING_VILLAGE",ffs.getPresentAddBuilding());
		query.setParameter("PRESENT_ADDRESS_AREA_LOCALITY_TALUKA",ffs.getPresentAddTaluka());
		query.setParameter("PRESENT_ADDRESS_DISTRICT_TOWN_CITY",ffs.getPresentAddDist());
		query.setParameter("PRESENT_ADDRESS_STATE_UNION_TERRITORY",ffs.getPresentAddState());
		query.setParameter("PRESENT_ADDRESS_COUNTRY","India");
		query.setParameter("PRESENT_ADDRESS_PIN_CODE",ffs.getPresentAddPin());
		query.setParameter("PERMANENT_ADD_FLAT_UNIT_NO_BLOCK_NO",ffs.getPermanentAddFlatNo());
		query.setParameter("PERMANENT_ADDRESS_NAME_OF_PREMISE_BUILDING_VILLAGE",ffs.getPermanentAddBuilding());
		query.setParameter("PERMANENT_ADDRESS_AREA_LOCALITY_TALUKA",ffs.getPermanentAddTaluka());
		query.setParameter("PERMANENT_ADDRESS_DISTRICT_TOWN_CITY",ffs.getPermanentAddDist());
		query.setParameter("PERMANENT_ADDRESS_STATE_UNION_TERRITORY",ffs.getPermanentAddState());
		query.setParameter("PERMANENT_ADDRESS_COUNTRY","India");
		query.setParameter("PERMANENT_ADDRESS_PIN_CODE",ffs.getPermanentAddPin());
		query.setParameter("PHONE_NO_STD_CODE",ffs.getPhoneSTDCode());
		query.setParameter("PHONE_NO_PHONE_NO",ffs.getPhoneNo());
		query.setParameter("MOBILE_NO","+91"+ffs.getMobileNo());
		query.setParameter("EMAIL_ID",ffs.getEmailId());
		query.setParameter("NOMINEE_1_NAME",ffs.getNominee1Name());
		query.setParameter("NOMINEE_1_DOB",nominee1DOB);
		query.setParameter("NOMINEE_1_RELATIONSHIP",ffs.getNominee1Relation());
		query.setParameter("NOMINEE_1_PERCENT_SHARE",ffs.getNominee1Percent());
		query.setParameter("NOMINEE_1_GUARDIAN_NAME",ffs.getNominee1Guardian());
		query.setParameter("NOMINEE_1_NOMINATION_INVALID_CONDITION",ffs.getNominee1InvalidCondition());
		query.setParameter("NOMINEE_2_NAME",ffs.getNominee2Name());
		query.setParameter("NOMINEE_2_DOB",nominee2DOB);
		query.setParameter("NOMINEE_2_RELATIONSHIP",ffs.getNominee2Relation());
		query.setParameter("NOMINEE_2_PERCENT_SHARE",ffs.getNominee2Percent());
		query.setParameter("NOMINEE_2_GUARDIAN_NAME",ffs.getNominee2Guardian());
		query.setParameter("NOMINEE_2_NOMINATION_INVALID_CONDITION",ffs.getNominee2InvalidCondition());
		query.setParameter("NOMINEE_3_NAME",ffs.getNominee3Name());
		query.setParameter("NOMINEE_3_DOB",nominee3DOB);
		query.setParameter("NOMINEE_3_RELATIONSHIP",ffs.getNominee3Relation());
		query.setParameter("NOMINEE_3_PERCENT_SHARE",ffs.getNominee3Percent());
		query.setParameter("NOMINEE_3_GUARDIAN_NAME",ffs.getNominee3Guardian());
		query.setParameter("NOMINEE_3_NOMINATION_INVALID_CONDITION",ffs.getNominee3InvalidCondition());
		
	
		query.executeUpdate();	
	}
	
	@Override
	public int insertRecordToNps(FrmFormS1NpsDtls FrmFormS1NpsDtlsInsert,String doj, String nominee1DOB, String nominee2DOB, String nominee3DOB,String strDDOCode, Long lLngPkIdForFormS1) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		String ddoc=strDDOCode.substring(0,2);
		gLogger.info(" ddoc "+ddoc);
		gLogger.info(" ddoc "+lLngPkIdForFormS1);
		gLogger.info(" doj "+doj);
		gLogger.info("nominee1DOB "+nominee1DOB);
		gLogger.info("nominee2DOB "+nominee2DOB);
		gLogger.info("nominee3DOB "+nominee3DOB);
		gLogger.info("nominee3DOB "+FrmFormS1NpsDtlsInsert.getDDORegNo());

		strQuery.append("insert into ifms.FRM_FORM_S1_DTLS_1 ");
		strQuery.append("(FORM_S1_ID,SEVARTH_ID,DCPS_ID,DESIGNATION ,title,EMP_NAME, EMP_FIRST_NAME,EMP_MIDDLE_NAME,EMP_LAST_NAME ,"
				+ "FATHER_NAME_FIRST,FATHER_NAME_MIDDLE,FATHER_NAME_LAST,Mother_NAME_First, EMP_DOB,GENDER,DTO_CODE,DDO_CODE  ,"
				+ "PAN_NO,Aadhar_NO,present_address_FlatNo,present_address_Building, present_address_Taluka,present_address_LandMark, "
				+ "present_address_district, present_address_state, present_address_country, present_address_pincode,permanent_address_FlatNo,"
				+ "permanent_address_Building, permanent_address_Taluka,permanent_address_LandMark,permanent_address_district, permanent_address_state,"
				+ "permanent_address_country, permanent_address_pincode,PHONE_NO,MOBILE_NO,EMAIL_ID,SMS_Sub_flag , EMAIL_Sub_flag ,Hindi_Sub_flag,"
				+ "EMP_DOJ,EMP_DOR,EMP_class,EMP_dept,EMP_ministry,EMP_DDO,Pay_scale,basic_salary,PPAN,Bank_details_flag,Bank_details_type,"
				+ "Bank_Account_no,Bank_name, Bank_branch,Bank_address,Bank_state,Bank_country,Bank_pin,EMP_number_nominee,nominee_name_1,nominee_dob_1,"
				+ "nominee_realtion_1,nominee_percent_share_1,nominee_gardian_name_1,nominee_render_condition_1,nominee_name_2,nominee_dob_2,"
				+ "nominee_realtion_2,nominee_percent_share_2,nominee_gardian_name_2,nominee_render_condition_2,nominee_name_3,nominee_dob_3,"
				+ "nominee_realtion_3,nominee_percent_share_3,nominee_gardian_name_3,nominee_render_condition_3,Created_Date,bankIfscCode,empMaritalStatus,"
				+ "ddo_reg_no,photo_attachment_name,sign_attachment_name,images_location_path,NOMINEE_ADDRESS_1,NOMINEE_ADDRESS_2,NOMINEE_ADDRESS_3) ");
		
		strQuery.append("values(:FORM_S1_ID,:SEVARTH_ID,:DCPS_ID,:DESIGNATION,:TITLE,:EMP_NAME,:EMP_FIRST_NAME,"
				+ ":EMP_MIDDLE_NAME,:EMP_LAST_NAME,"
				+ ":FATHER_NAME_FIRST,:FATHER_NAME_MIDDLE,:FATHER_NAME_LAST,:MOTHER_NAME_FIRST,:EMP_DOB,:GENDER,:DTO_CODE,:DDO_CODE,:PAN_NO,:AADHAR_NO,"
				+ ":PRESENT_ADDRESS_FLATNO,:PRESENT_ADDRESS_BUILDING,:PRESENT_ADDRESS_TALUKA,:PRESENT_ADDRESS_LANDMARK,:PRESENT_ADDRESS_DISTRICT,"
				+ ":PRESENT_ADDRESS_STATE,"
				+ ":PRESENT_ADDRESS_COUNTRY,:PRESENT_ADDRESS_PINCODE,:PERMANENT_ADDRESS_FLATNO,:PERMANENT_ADDRESS_BUILDING,:PERMANENT_ADDRESS_TALUKA,"
				+ ":PERMANENT_ADDRESS_LANDMARK,:PERMANENT_ADDRESS_DISTRICT,:PERMANENT_ADDRESS_STATE,:PERMANENT_ADDRESS_COUNTRY,:PERMANENT_ADDRESS_PINCODE,"
				+ ":PHONE_NO,:MOBILE_NO,:EMAIL_ID,:SMS_SUB_FLAG,:EMAIL_SUB_FLAG,:HINDI_SUB_FLAG,:EMP_DOJ,:EMP_DOR,:EMP_CLASS,:EMP_DEPT,"
				+ ":EMP_MINISTRY,:EMP_DDO,:PAY_SCALE,:BASIC_SALARY,:PPAN,:BANK_DETAILS_FLAG,:BANK_DETAILS_TYPE,:BANK_ACCOUNT_NO,:BANK_NAME,"
				+ ":BANK_BRANCH,:BANK_ADDRESS,:BANK_STATE,:BANK_COUNTRY,:BANK_PIN,:EMP_NUMBER_NOMINEE,:NOMINEE_NAME_1,:NOMINEE_DOB_1,:NOMINEE_REALTION_1,"
				+ ":NOMINEE_PERCENT_SHARE_1,:NOMINEE_GARDIAN_NAME_1,:NOMINEE_RENDER_CONDITION_1,:NOMINEE_NAME_2,:NOMINEE_DOB_2,:NOMINEE_REALTION_2,"
				+ ":NOMINEE_PERCENT_SHARE_2,:NOMINEE_GARDIAN_NAME_2,:NOMINEE_RENDER_CONDITION_2,:NOMINEE_NAME_3,"
				+ ":NOMINEE_DOB_3,:NOMINEE_REALTION_3,:NOMINEE_PERCENT_SHARE_3,:NOMINEE_GARDIAN_NAME_3,:NOMINEE_RENDER_CONDITION_3,sysdate,:bankIfscCode,"
				+ ":empMaritalStatus,:DDO_REG_NO,:PHOTO_ATTACHMENT_NAME,:SIGN_ATTACHMENT_NAME,:IMAGES_LOCATION_PATH,:NOMINEE_ADDRESS_1,:NOMINEE_ADDRESS_2,:NOMINEE_ADDRESS_3)");
		Query query = hibSession.createSQLQuery(strQuery.toString());
		logger.info("Query to save Frm S1 is: " + strQuery.toString());
		logger.info("Query to save Frm S1 is: EMP_DOJ " + FrmFormS1NpsDtlsInsert.getDateOfJoining());
		logger.info("Query to save Frm S1 is: NOMINEE_DOB_1" + FrmFormS1NpsDtlsInsert.getNominee1DOB());
		logger.info("Query to save Frm S1 is: NOMINEE_DOB_2" + FrmFormS1NpsDtlsInsert.getNominee2DOB());
		logger.info("Query to save Frm S1 is: NOMINEE_DOB_3" + FrmFormS1NpsDtlsInsert.getNominee3DOB());
		logger.info("Query to save Frm S1 is: CREATED_DATE" + FrmFormS1NpsDtlsInsert.getCreatedDate());
		logger.info("Query to save Frm S1 is: DDO_REG_NO" + FrmFormS1NpsDtlsInsert.getDDORegNo());
		
		
		/*
		 * PRESENT_ADDRESS_PINCODE         SYSIBM    INTEGER                      4     0 Yes
PERMANENT_ADDRESS_PINCODE       SYSIBM    INTEGER                      4     0 Yes
BASIC_SALARY                    SYSIBM    INTEGER                      4     0 Yes
BANK_PIN                        SYSIBM    INTEGER                      4     0 Yes
EMP_NUMBER_NOMINEE              SYSIBM    INTEGER                      4     0 Yes
NOMINEE_PERCENT_SHARE_1         SYSIBM    INTEGER                      4     0 Yes
NOMINEE_PERCENT_SHARE_2         SYSIBM    INTEGER                      4     0 Yes
NOMINEE_PERCENT_SHARE_3  

		 * */
		
		
		query.setParameter("FORM_S1_ID",lLngPkIdForFormS1);	
		query.setParameter("SEVARTH_ID",FrmFormS1NpsDtlsInsert.getSevarthId());
		query.setParameter("TITLE",FrmFormS1NpsDtlsInsert.getSaluation());
		query.setParameter("EMP_NAME",FrmFormS1NpsDtlsInsert.getEmpName());
		query.setParameter("DCPS_ID",FrmFormS1NpsDtlsInsert.getDcpsId());
		query.setParameter("DESIGNATION",FrmFormS1NpsDtlsInsert.getDesignation());
		
		query.setParameter("EMP_FIRST_NAME",FrmFormS1NpsDtlsInsert.getEmpFName());
		query.setParameter("EMP_MIDDLE_NAME",FrmFormS1NpsDtlsInsert.getEmpMName());
		query.setParameter("EMP_LAST_NAME",FrmFormS1NpsDtlsInsert.getEmpLName());
		query.setParameter("FATHER_NAME_FIRST",FrmFormS1NpsDtlsInsert.getEmpFatherFName());
		query.setParameter("FATHER_NAME_MIDDLE",FrmFormS1NpsDtlsInsert.getEmpFatherMName());
		query.setParameter("FATHER_NAME_LAST",FrmFormS1NpsDtlsInsert.getEmpFatherLName());
		query.setParameter("MOTHER_NAME_FIRST",FrmFormS1NpsDtlsInsert.getEmpMotherFName());
		query.setParameter("EMP_DOB",FrmFormS1NpsDtlsInsert.getEmpDOB());
		query.setParameter("GENDER",FrmFormS1NpsDtlsInsert.getEmpGender());
		query.setParameter("DTO_CODE",FrmFormS1NpsDtlsInsert.getDtoCode());
		query.setParameter("DDO_CODE",FrmFormS1NpsDtlsInsert.getDdoCode());
		//query.setParameter("DDO_CODE",FrmFormS1NpsDtlsInsert.getDdoCode());
		query.setParameter("PAN_NO",FrmFormS1NpsDtlsInsert.getPanNo());
		query.setParameter("AADHAR_NO",FrmFormS1NpsDtlsInsert.getAadharNo());
		query.setParameter("DDO_REG_NO",FrmFormS1NpsDtlsInsert.getDDORegNo());
		query.setParameter("PRESENT_ADDRESS_FLATNO",FrmFormS1NpsDtlsInsert.getPresentAddFlatNo());
		query.setParameter("PRESENT_ADDRESS_BUILDING",FrmFormS1NpsDtlsInsert.getPresentAddBuilding());
		query.setParameter("PRESENT_ADDRESS_TALUKA",FrmFormS1NpsDtlsInsert.getPresentAddTaluka());
		query.setParameter("PRESENT_ADDRESS_LANDMARK",FrmFormS1NpsDtlsInsert.getPresentAddLandMark());
		query.setParameter("PRESENT_ADDRESS_DISTRICT",FrmFormS1NpsDtlsInsert.getPresentAddDist());
//		query.setParameter("PRESENT_ADDRESS_STATE",FrmFormS1NpsDtlsInsert.getPresentAddState());
		query.setParameter("PRESENT_ADDRESS_STATE",FrmFormS1NpsDtlsInsert.getPresentAddState());
		query.setParameter("PRESENT_ADDRESS_COUNTRY","IN");
		query.setParameter("PRESENT_ADDRESS_PINCODE",FrmFormS1NpsDtlsInsert.getPresentAddPin());
		query.setParameter("PERMANENT_ADDRESS_FLATNO",FrmFormS1NpsDtlsInsert.getPermanentAddFlatNo());
		query.setParameter("PERMANENT_ADDRESS_BUILDING",FrmFormS1NpsDtlsInsert.getPermanentAddBuilding());
		query.setParameter("PERMANENT_ADDRESS_TALUKA",FrmFormS1NpsDtlsInsert.getPermanentAddTaluka());
		query.setParameter("PERMANENT_ADDRESS_LANDMARK",FrmFormS1NpsDtlsInsert.getPermanentAddLandMark());
		query.setParameter("PERMANENT_ADDRESS_DISTRICT",FrmFormS1NpsDtlsInsert.getPresentAddDist());
		query.setParameter("PERMANENT_ADDRESS_STATE",FrmFormS1NpsDtlsInsert.getPermanentAddState());
		query.setParameter("PERMANENT_ADDRESS_COUNTRY","IN");
		query.setParameter("PERMANENT_ADDRESS_PINCODE",FrmFormS1NpsDtlsInsert.getPermanentAddPin());
		
		//query.setParameter("PHONE_NO_STD_CODE",FrmFormS1NpsDtlsInsert.getPhoneSTDCode());
		query.setParameter("PHONE_NO","+91"+FrmFormS1NpsDtlsInsert.getPhoneNo());
		query.setParameter("MOBILE_NO","+91"+FrmFormS1NpsDtlsInsert.getMobileNo());
		query.setParameter("EMAIL_ID",FrmFormS1NpsDtlsInsert.getEmailId());
		query.setParameter("SMS_SUB_FLAG",FrmFormS1NpsDtlsInsert.getSmsSubFlag());
		query.setParameter("EMAIL_SUB_FLAG",FrmFormS1NpsDtlsInsert.getEmailSubFlag());
		query.setParameter("HINDI_SUB_FLAG",FrmFormS1NpsDtlsInsert.getHindiSubFlag());

		query.setParameter("EMP_DOJ",FrmFormS1NpsDtlsInsert.getDateOfJoining());
		query.setParameter("EMP_DOR",FrmFormS1NpsDtlsInsert.getDateOfRetire());
		query.setParameter("EMP_CLASS",FrmFormS1NpsDtlsInsert.getEmpClass());
		query.setParameter("EMP_DEPT",FrmFormS1NpsDtlsInsert.getEmpDept());
		query.setParameter("EMP_MINISTRY",FrmFormS1NpsDtlsInsert.getEmpDeptMinistry());
		query.setParameter("EMP_DDO",FrmFormS1NpsDtlsInsert.getEmpDdoCode());
		
		query.setParameter("PAY_SCALE",FrmFormS1NpsDtlsInsert.getEmpPayScale());
		query.setParameter("BASIC_SALARY",FrmFormS1NpsDtlsInsert.getEmpBasicSalary());
		query.setParameter("PPAN",FrmFormS1NpsDtlsInsert.getpPan());
		query.setParameter("BANK_DETAILS_FLAG",FrmFormS1NpsDtlsInsert.getBankDetailsFlag());
		
		query.setParameter("BANK_DETAILS_TYPE",FrmFormS1NpsDtlsInsert.getBankDetailsType());
		query.setParameter("BANK_ACCOUNT_NO",FrmFormS1NpsDtlsInsert.getBankAccountNo());
		String bankName="";
		bankName=FrmFormS1NpsDtlsInsert.getBankName();
		 if (bankName.length() > 30) {    
			 bankName=bankName.substring(0,30);    
	       } 
		 query.setParameter("BANK_NAME",bankName);
		 String bankBranchName="";
		 bankBranchName=FrmFormS1NpsDtlsInsert.getBankBranchName();
			 if (bankBranchName.length() > 30) {    
				 bankBranchName=bankBranchName.substring(0,30);    
		       } 
			 
		
		query.setParameter("BANK_BRANCH",bankBranchName);
		query.setParameter("BANK_ADDRESS",FrmFormS1NpsDtlsInsert.getBankAddress());
		query.setParameter("BANK_STATE",FrmFormS1NpsDtlsInsert.getBankState());
		query.setParameter("BANK_COUNTRY","IN");
		query.setParameter("BANK_PIN",FrmFormS1NpsDtlsInsert.getBankPinCode());
		query.setParameter("EMP_NUMBER_NOMINEE",FrmFormS1NpsDtlsInsert.getEmpNumberNominee());
		query.setParameter("NOMINEE_NAME_1",FrmFormS1NpsDtlsInsert.getNominee1Name());
		query.setParameter("NOMINEE_DOB_1",nominee1DOB);
		query.setParameter("NOMINEE_REALTION_1",FrmFormS1NpsDtlsInsert.getNominee1Relation());
		query.setParameter("NOMINEE_PERCENT_SHARE_1",FrmFormS1NpsDtlsInsert.getNominee1Percent());
		query.setParameter("NOMINEE_GARDIAN_NAME_1",FrmFormS1NpsDtlsInsert.getNominee1Guardian());
		query.setParameter("NOMINEE_RENDER_CONDITION_1",FrmFormS1NpsDtlsInsert.getNominee1InvalidCondition());
		
		query.setParameter("NOMINEE_NAME_2",FrmFormS1NpsDtlsInsert.getNominee2Name());
		query.setParameter("NOMINEE_DOB_2",nominee2DOB);
		query.setParameter("NOMINEE_REALTION_2",FrmFormS1NpsDtlsInsert.getNominee2Relation());
		query.setParameter("NOMINEE_PERCENT_SHARE_2",FrmFormS1NpsDtlsInsert.getNominee2Percent());
		query.setParameter("NOMINEE_GARDIAN_NAME_2",FrmFormS1NpsDtlsInsert.getNominee2Guardian());
		query.setParameter("NOMINEE_RENDER_CONDITION_2",FrmFormS1NpsDtlsInsert.getNominee2InvalidCondition());
		
		
		query.setParameter("NOMINEE_NAME_3",FrmFormS1NpsDtlsInsert.getNominee3Name());
		query.setParameter("NOMINEE_DOB_3",nominee3DOB);
		query.setParameter("NOMINEE_REALTION_3",FrmFormS1NpsDtlsInsert.getNominee3Relation());
		query.setParameter("NOMINEE_PERCENT_SHARE_3",FrmFormS1NpsDtlsInsert.getNominee3Percent());
		query.setParameter("NOMINEE_GARDIAN_NAME_3",FrmFormS1NpsDtlsInsert.getNominee3Guardian());
		query.setParameter("NOMINEE_RENDER_CONDITION_3",FrmFormS1NpsDtlsInsert.getNominee3InvalidCondition());
		
		query.setParameter("NOMINEE_ADDRESS_1",FrmFormS1NpsDtlsInsert.getNominee1Address());
		query.setParameter("NOMINEE_ADDRESS_2",FrmFormS1NpsDtlsInsert.getNominee2Address());
		query.setParameter("NOMINEE_ADDRESS_3",FrmFormS1NpsDtlsInsert.getNominee2Address());
		
		query.setParameter("bankIfscCode",FrmFormS1NpsDtlsInsert.getBankIfscCode());
		query.setParameter("empMaritalStatus",FrmFormS1NpsDtlsInsert.getEmpMaritalStatus());
		/*PHOTO_ATTACHMENT_NAME,:SIGN_ATTACHMENT_NAME,:IMAGES_LOCATION_PATH*/
		query.setParameter("PHOTO_ATTACHMENT_NAME",FrmFormS1NpsDtlsInsert.getPhoto_attachment_name());
		query.setParameter("SIGN_ATTACHMENT_NAME",FrmFormS1NpsDtlsInsert.getSign_attachment_name());
		query.setParameter("IMAGES_LOCATION_PATH",FrmFormS1NpsDtlsInsert.getImages_location_path());
		//query.setParameter("PRAN_NO",FrmFormS1NpsDtlsInsert.getNominee3InvalidCondition());
		//query.setParameter("CREATED_DATE",FrmFormS1NpsDtlsInsert.getCreatedDate());
		return query.executeUpdate(); 
	}

	@Override
	public Long checkFormS1(String strSevarthId) {

		Session hibSession = getSession();

		Long finalCheckFlag=null;
		StringBuffer sb= new StringBuffer();
		gLogger.info("strSevarthId: "+strSevarthId);
		sb.append("select count (1) from FRM_FORM_S1_DTLS_1 where upper(SEVARTH_ID) = '"+strSevarthId.toUpperCase()+"'");

		gLogger.info("Query to sevarth id in form s1:  " + sb.toString());
		Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
		finalCheckFlag=Long.parseLong(sqlQuery1.uniqueResult().toString());

		return finalCheckFlag;
	}

	@Override
    public List getEmpDesigList(String strDDOCode) {
        org.hibernate.Session hibSession = this.getSession();
        StringBuffer strQuery = new StringBuffer();
        strQuery.append(" select desig.DSGN_NAME,desig.DSGN_ID,zp.ZP_DDO_CODE ");
        strQuery.append(" from MST_DCPS_EMP empmst ");
        strQuery.append(" inner join org_emp_mst org on org.EMP_ID = empmst.ORG_EMP_MST_ID ");
        strQuery.append(" inner join ORG_USERPOST_RLT userpost on userpost.USER_ID=org.USER_ID ");
        strQuery.append(" inner join ORG_POST_MST post on post.POST_ID=userpost.POST_ID   ");
        strQuery.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE = empmst.DDO_CODE  ");
        strQuery.append(" inner join MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID ");
        strQuery.append(" inner join ORG_DESIGNATION_MST desig on empmst.DESIGNATION=desig.DSGN_ID ");
        strQuery.append(" where zp.REPT_DDO_CODE='" + strDDOCode + "' and  (empmst.EMP_SERVEND_DT > sysdate OR empmst.EMP_SERVEND_DT is null) ");
        strQuery.append(" and empmst.DCPS_OR_GPF='Y' and not exists (select null FROM FRM_FORM_S1_DTLS_1 frms1 where empmst.SEVARTH_ID=frms1.sevarth_id ) and userpost.ACTIVATE_FLAG = 1 and post.ACTIVATE_FLAG = 1 ");
        strQuery.append(" group by desig.DSGN_NAME,desig.DSGN_ID,zp.ZP_DDO_CODE ");
        strQuery.append(" order by desig.DSGN_NAME ");
        this.logger.info((Object)("Query to get Emp desig List For Frm S1 Edit is " + strQuery.toString()));
        SQLQuery query = hibSession.createSQLQuery(strQuery.toString());
        return query.list();
    }
	
	
	public void insertDepFlagDdo(String strDDOCode, String strEmpSevarthId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
	//	String ddoc=strDDOCode.substring(0,2);
		gLogger.info(" strDDOCode "+strDDOCode);
		
		gLogger.info("strEmpSevarthId "+strEmpSevarthId);
		
		strQuery.append(" update FRM_FORM_S1_DTLS set dep_updated_ddo_cd='"+strDDOCode+"' where sevarth_id='"+strEmpSevarthId+"' ");
			logger.info("Query to ######updateDepFlagDdo: " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.executeUpdate();	
	}

	@Override
	public List getEmpListForFileGenerate(String strDDOCode, String txtSearch,String flag) {
	 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
//		strQuery.append(" select  a.SEVArth_id,a.EMP_Name,a.emp_doj,a.Designation,a.DDO_CODE,mstddo.OFF_NAME,a.DCPS_ID,a.ACKNO,a.BATCH_ID,case when FILE_VERIFY_STATUS='Y' then 'Verified' else '' end,case when a.NSDL_STATUS is not null then 'File uploaded ' else '' end,a.NSDL_STATUS,a.PRAN_NO  FROM ifms.FRM_FORM_S1_DTLS_1 as a " 
				strQuery.append(" select  a.SEVArth_id,a.EMP_Name,a.emp_doj,a.Designation,empmst.DDO_CODE,mstddo.OFF_NAME,a.DCPS_ID,a.ACKNO,a.BATCH_ID,case when FILE_VERIFY_STATUS='Y' then 'Verified' else '' end,case when a.NSDL_STATUS is not null then 'File uploaded ' else '' end,a.NSDL_STATUS,a.PRAN_NO,cast(FILE_VERIFY_STATUS as varchar(2)),a.DTO_CODE,a.DDO_REG_NO  FROM ifms.FRM_FORM_S1_DTLS_1 as a " 
				+" inner join MST_DCPS_emp empmst on  empmst.SEVArth_id=a.SEVArth_id " 
				+" inner join MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID  "
				+ "where a.DDO_CODE='"+strDDOCode.trim().toUpperCase()+"'");// and (empmst.PRAN_NO is null or empmst.PRAN_ACTIVE is null or a.PAN_NO is null or a.pran_status is null)  ");
				if(flag.equals("sevarthId"))
				{
					strQuery.append("and  a.SEVArth_id='"+txtSearch.trim()+"'");
				}
				strQuery.append("order by mstddo.OFF_NAME");
				
				/*strQuery.append(" select  a.SEVArth_id,a.EMP_Name,a.emp_doj,a.Designation,a.DDO_CODE,mstddo.OFF_NAME,a.DCPS_ID,a.ACKNO,a.BATCH_ID, FILE_VERIFY_STATUS,a.NSDL_STATUS,a.PRAN_STATUS  FROM ifms.FRM_FORM_S1_DTLS_1 as a " 
				+" inner join MST_DCPS_emp empmst on  empmst.SEVArth_id=a.SEVArth_id " 
				+" inner join MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID  "
				+ "where a.DDO_CODE='"+strDDOCode.trim().toUpperCase()+"' and (empmst.PRAN_NO is null or empmst.PRAN_ACTIVE is null or a.PAN_NO is null or a.pran_status is null)  ");*/
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		empList = query.list();
		logger.info("empList is " + empList.size());
		return empList;
	 
	}

	@Override
	public List getEmpForFileGenerateText(String SevaarthIds, String flag) {
	 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append(" select  a.SEVArth_id,a.TITLE,a.EMP_FIRST_NAME,a.EMP_MIDDLE_NAME,a.EMP_LAST_NAME,a.EMP_Name,a.FATHER_NAME_FIRST,a.FATHER_NAME_LAST,a.DTO_CODE,a.DDO_CODE,a.GENDER,\r\n" + 
				"a.EMP_DOB,a.PAN_NO,a.PRESENT_ADDRESS_FLATNO,a.PRESENT_ADDRESS_BUILDING,a.PRESENT_ADDRESS_TALUKA,a.PRESENT_ADDRESS_LANDMARK,a.PRESENT_ADDRESS_DISTRICT,a.PRESENT_ADDRESS_STATE,\r\n" + 
				"a.PRESENT_ADDRESS_COUNTRY,a.PRESENT_ADDRESS_PINCODE,a.PERMANENT_ADDRESS_FLATNO,a.PERMANENT_ADDRESS_BUILDING,a.PERMANENT_ADDRESS_TALUKA,a.PERMANENT_ADDRESS_LANDMARK,a.PERMANENT_ADDRESS_DISTRICT,a.PERMANENT_ADDRESS_STATE,\r\n" + 
				"a.PERMANENT_ADDRESS_COUNTRY,a.PERMANENT_ADDRESS_PINCODE\r\n" + 
				",a.PHONE_NO,a.MOBILE_NO,a.EMAIL_ID,a.SMS_SUB_FLAG,a.EMAIL_SUB_FLAG,a.emp_doj,a.emp_dor,a.EMP_CLASS, a.EMP_DEPT,a.EMP_MINISTRY,a.PAY_SCALE,a.Designation,a.EMP_DDO,A.BANK_ACCOUNT_NO,A.BANK_NAME,a.BANK_BRANCH,a.BANK_ADDRESS,a.BANK_PIN,a.EMP_NUMBER_NOMINEE,a.NOMINEE_NAME_1,a.NOMINEE_DOB_1,a.NOMINEE_REALTION_1,\r\n" + 
				"a.NOMINEE_GARDIAN_NAME_1,a.NOMINEE_PERCENT_SHARE_1,a.NOMINEE_RENDER_CONDITION_1,mstddo.OFF_NAME,a.DCPS_ID,a.BASIC_SALARY,case when  NOMINEE_DOB_1 is not null and NOMINEE_DOB_1!='' then case when date(to_date(NOMINEE_DOB_1,'DD-MM-YYYY'))<(current_date- 18 year) then 'N' else 'Y' end else null end ,MOTHER_NAME_FIRST,a.bankIfscCode ,a.empMaritalStatus,a.DDO_REG_NO, "
				+"a.photo_attachment_name,a.sign_attachment_name,a.images_location_path "+
				",a.NOMINEE_NAME_2,a.NOMINEE_DOB_2,a.NOMINEE_REALTION_2, case when  NOMINEE_DOB_2 is not null and NOMINEE_DOB_2!='' then case when date(to_date(NOMINEE_DOB_2,'DD-MM-YYYY'))<(current_date- 18 year) then 'N' else 'Y' end else null end as NOMINEE2_MAJORMINOR,"+
				"a.NOMINEE_GARDIAN_NAME_2,a.NOMINEE_PERCENT_SHARE_2,a.NOMINEE_RENDER_CONDITION_2,a.NOMINEE_NAME_3,a.NOMINEE_DOB_3,a.NOMINEE_REALTION_3,  case when  NOMINEE_DOB_3 is not null and NOMINEE_DOB_3!='' then case when date(to_date(NOMINEE_DOB_3,'DD-MM-YYYY'))<(current_date- 18 year) then 'N' else 'Y' end else null end as NOMINEE3_MAJORMINOR," + 
				"a.NOMINEE_GARDIAN_NAME_3,a.NOMINEE_PERCENT_SHARE_3,a.NOMINEE_RENDER_CONDITION_3,a.NOMINEE_ADDRESS_1,a.NOMINEE_ADDRESS_2,a.NOMINEE_ADDRESS_3 "
				+ "FROM ifms.FRM_FORM_S1_DTLS_1 as a inner join MST_DCPS_emp empmst on  empmst.SEVArth_id=a.SEVArth_id inner join MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID\r\n" +
				" where a.SEVArth_id in('"+SevaarthIds.trim().toUpperCase()+"') ");
		 
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString()); 
		Query query = hibSession.createSQLQuery(strQuery.toString());
		empList = query.list();
		logger.info("empList is " + empList.size());
		return empList;
	 
	}
	
	/*@Override
	public List getAckNoBatchForValidateText(String SevaarthIds, String flag) {
	 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List ackNoList = null;
		
		strQuery.append(" select ACKNO,Batch_id  FROM ifms.FRM_FORM_S1_DTLS_1   where SEVArth_id in('"+SevaarthIds.trim().toUpperCase()+"') ");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		ackNoList = query.list();
		logger.info("ackNoList is " + ackNoList.size());
		return ackNoList;
	 
	}*/
	
	
	@Override
	public List getAckNoBatchForValidateText(String batchID, String flag) {
	 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List ackNoList = null;
		
		strQuery.append(" select ACKNO,Batch_id  FROM ifms.FRM_FORM_S1_DTLS_1   where BATCH_ID='"+batchID.trim()+"' ");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		ackNoList = query.list();
		logger.info("ackNoList is " + ackNoList.size());
		return ackNoList;
	 
	}
	@Override
	public List getDataFromFrmS1(String batchID,String ColumnStr) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List dataList = null; 
/*		strQuery.append(" select "+ColumnStr+" FROM ifms.FRM_FORM_S1_DTLS_1 as NPS left join ifms.MST_DCPS_EMP as emp on NPS.SEVARTH_ID=emp.SEVARTH_ID   where NPS.sevarth_id in('"+SevaarthIds.trim().toUpperCase()+"') ");*/
		strQuery.append(" select "+ColumnStr+" FROM ifms.FRM_FORM_S1_DTLS_1 as NPS left join ifms.MST_DCPS_EMP as emp on NPS.SEVARTH_ID=emp.SEVARTH_ID   where NPS.BATCH_ID in('"+batchID.trim()+"') ");
		logger.info("Query to get Emp data details From FRM_FORM_S1_DTLS_1 is getDataFromFrmS1 " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		dataList = query.list();
		return dataList;
		
	}

	@Override
	public List getDataForGetPran(String nsdlStatusCode,String ColumnStr) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List dataList = null;
		strQuery.append(" select "+ColumnStr+" FROM ifms.FRM_FORM_S1_DTLS_1 as NPS left join ifms.MST_DCPS_EMP as emp on NPS.SEVARTH_ID=emp.SEVARTH_ID   where NPS.NSDL_STATUS='"+nsdlStatusCode.trim().toUpperCase()+"' ");
		
		logger.info("Query to get Emp data details From FRM_FORM_S1_DTLS_1 is getDataForGetPran " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		dataList = query.list();
		return dataList;
		
	}
	
	@Override
	public List getEmpForFileGenerate(String SevaarthIds, String flag) {
	 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append(" select  a.SEVArth_id,a.TITLE,a.EMP_Name,a.emp_doj,a.Designation,a.DDO_CODE,mstddo.OFF_NAME,a.DCPS_ID  FROM ifms.FRM_FORM_S1_DTLS_1 as a " 
				+" inner join MST_DCPS_emp empmst on  empmst.SEVArth_id=a.SEVArth_id " 
				+" inner join MST_DCPS_DDO_OFFICE mstddo on  empmst.CURR_OFF=mstddo.DCPS_DDO_OFFICE_MST_ID  "
				+ "where a.SEVArth_id in('"+SevaarthIds.trim().toUpperCase()+"')  and a.STATUS_FLAG=1");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		empList = query.list();
		logger.info("empList is " + empList.size());
		return empList;
	 
	}
	
	@Override
	public List getEmpDcpsIdByAcknow(String AckN) {
	 
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		strQuery.append(" select DCPS_ID FROM ifms.FRM_FORM_S1_DTLS_1  where ACKNO ='"+AckN+"' and  STATUS_FLAG=1");
		logger.info("Query to get Emp dcpsID after file uploaded " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		empList = query.list();
		logger.info("dcpsId is size" + empList.size());
		return empList;
	 
	}
	
	@Override
	public List checkBachId(String ymd) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List BatchIdList = null;
		strQuery.append(" select  count(*) from  ifms.BATCH_SEQ where year||month||day in('"+ymd.trim()+"') ");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		BatchIdList = query.list();
		logger.info("BatchIdList is " + BatchIdList.size());
		return BatchIdList;
		
	}
	
	
	@Override
	public List getBachId(String ymd) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List BatchIdList = null;
		strQuery.append(" select  BATCH_ID||year||month||day||sr_no from  ifms.BATCH_SEQ where year||month||day in('"+ymd.trim()+"') ");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		BatchIdList = query.list();
		logger.info("BatchIdList is " + BatchIdList.size());
		return BatchIdList;
		
	}
	public void updateBachSeq(String ymd) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
				synchronized(this) {
				
		strQuery.append(" update ifms.BATCH_SEQ set sr_no=(select sr_no+1 from ifms.BATCH_SEQ where year||month||day in('"+ymd.trim()+"'))");
		strQuery.append(" where year||month||day in('"+ymd.trim()+"')");
		Query query = hibSession.createSQLQuery(strQuery.toString());
	 	query.executeUpdate();
				}
	 	//hibSession.close();
	}
	public void insertBachSeq(String Year,String Mon, String Day) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
				synchronized(this) {
				
		strQuery.append(" insert into ifms.BATCH_SEQ ");
		strQuery.append(" (year,month,day,SR_NO) ");
		strQuery.append(" values(:YEAR,:MONTH,:DAY,1)");
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.setParameter("YEAR",Year);
		Integer tempDay=Integer.parseInt(Mon);
		if(tempDay<10) {
			query.setParameter("MONTH","0"+tempDay.toString());
		}else {
			query.setParameter("MONTH",Mon);	
		}
		
		query.setParameter("DAY",Day);
		//query.setParameter("SR_NO","1");
		query.executeUpdate();
				}
		//query.executeUpdate(); 
	}

	@Override
	public List getAkNoId(String dtoRegNo) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List AckNoList = null;
		strQuery.append(" select  AK_SEQ from  ifms.NPS_ACKNO_SEQ where DTO_REG_NO='"+dtoRegNo.trim()+"'");
		logger.info("Query to get NPS_ACKNO_SEQ ---- " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		AckNoList = query.list();
		logger.info("NPS_ACKNO_NO is " + AckNoList.size());
		return AckNoList;
		
	}
	public void updateAckNoSeq(String dtoRegNo) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
				synchronized(this) {
				
		strQuery.append(" update ifms.NPS_ACKNO_SEQ set AK_SEQ=(select AK_SEQ+1 from ifms.NPS_ACKNO_SEQ where DTO_REG_NO='"+dtoRegNo.trim()+"')");  
		strQuery.append(" where DTO_REG_NO='"+dtoRegNo.trim()+"'"); 
		Query query = hibSession.createSQLQuery(strQuery.toString());
	 	query.executeUpdate();
				}
	 	//hibSession.close();
	}
	
	@Override
	public void insertAckNoSeq(String dtoRegNo) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
				synchronized(this) {
				
		strQuery.append(" insert into ifms.NPS_ACKNO_SEQ ");
		strQuery.append(" (DTO_REG_NO,AK_SEQ) ");
		strQuery.append(" values(:dtoRegNo,:AK_SEQ)");
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.setParameter("dtoRegNo",dtoRegNo);	
		query.setParameter("AK_SEQ",1); 
		query.executeUpdate();
				}

	}
	
	@Override
	public void updateFrmFormS1DTLS1(String SevaarthIds,String ackNo, String BatchId) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append("update ifms.FRM_FORM_S1_DTLS_1 set ackNo='"+ackNo.trim()+"', Batch_Id='"+BatchId.trim()+"' where SEVArth_id='"+SevaarthIds.trim().toUpperCase()+"' and STATUS_FLAG=1");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.executeUpdate();
		 
		//hibSession.close();	 
	 
		
	}
	
	@Override
	public void updateFrmDtlsPranNo(String dcpsId,String PranNo,String AckNo) 
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append("update ifms.FRM_FORM_S1_DTLS_1 set Pran_NO='"+PranNo.trim()+"', pran_status='1' where DCPS_ID='"+dcpsId.trim()+"' and ackNo='"+AckNo.trim()+"' and STATUS_FLAG=1");
		
		logger.info("Query to update FRM_FORM_S1_DTLS_1 For Pran no is " + strQuery.toString());
		
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.executeUpdate();
	}
	
	@Override
	public void updateMstDcpsEmpPranNo(String dcpsId,String PranNo,int pranActive) 
	{
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append("update ifms.MST_DCPS_EMP set Pran_NO='"+PranNo.trim()+"', pran_Active="+pranActive+" where DCPS_ID='"+dcpsId.trim()+"' ");
		
		logger.info("Query to update MST_DCPS_EMP For Pran no and pran_status is " + strQuery.toString());
		
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.executeUpdate();
	}
	
	@Override
	public void updateNsdlStatusCode(String BATCH_ID, String nsdlStatusCode) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append("update ifms.FRM_FORM_S1_DTLS_1 set  NSDL_STATUS='"+nsdlStatusCode.trim()+"' where BATCH_ID in('"+BATCH_ID.trim().toUpperCase()+"')  and STATUS_FLAG=1");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.executeUpdate();
		 
		//hibSession.close();	 
	 
		
	}
	@Override
	public void updateNsdlFileVeryStatus(String BATCH_ID, String fileVerifySatatus) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List empList = null;
		
		strQuery.append("update ifms.FRM_FORM_S1_DTLS_1 set  FILE_VERIFY_STATUS='"+fileVerifySatatus.trim()+"' where BATCH_ID in('"+BATCH_ID.trim().toUpperCase()+"') and STATUS_FLAG=1");
		
		logger.info("Query to get Emp details For File Generate is " + strQuery.toString());
		
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.executeUpdate();
		
	}

	//For Trasactional sequence 
	
	@Override
	public List checkFileSeqId(String ymd) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List npsFileSeqIdList = null;
		strQuery.append(" select  count(*) from  ifms.NPS_File_SEQ where FILE_Year||FILE_month||FILE_day in('"+ymd.trim()+"') ");
		logger.info("Query to File seqence daily basis is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		npsFileSeqIdList = query.list();
		return npsFileSeqIdList;
		
	}
	
	
	@Override
	public List getFileSeqId(String ymd) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		List npsFileSeqIdList = null;
		strQuery.append(" select  FILE_SEQ from  ifms.NPS_File_SEQ where FILE_Year||FILE_month||FILE_day in('"+ymd.trim()+"') ");
		logger.info("Query to get File seqence is " + strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		npsFileSeqIdList = query.list();
		return npsFileSeqIdList;
		
	}
	public void updateFileSeq(String ymd) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
				synchronized(this) {
				
		strQuery.append(" update ifms.NPS_File_SEQ set FILE_SEQ=(select FILE_SEQ+1 from ifms.NPS_File_SEQ where FILE_Year||FILE_month||FILE_day in('"+ymd.trim()+"'))");
		strQuery.append(" where FILE_Year||FILE_month||FILE_day in('"+ymd.trim()+"')");
		Query query = hibSession.createSQLQuery(strQuery.toString());
	 	query.executeUpdate();
				}
	}
	public void insertFileSeq(String Year,String Mon, String Day) {
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		//synchronized due to overlapping sequence
				synchronized(this) {
				
		strQuery.append(" insert into ifms.NPS_File_SEQ ");
		strQuery.append(" (FILE_Year,FILE_month,FILE_Day,FILE_SEQ) ");
		strQuery.append(" values(:YEAR,:MONTH,:DAY,1)");
		Query query = hibSession.createSQLQuery(strQuery.toString());
		query.setParameter("YEAR",Year);	
		query.setParameter("MONTH",Mon);
		query.setParameter("DAY",Day);
		
		query.executeUpdate();
				}
	}

		 
	@Override
	public CmnAttdocMst findByAttdocId(Long paramString)
	  {
	    Criteria localCriteria = null;
	    
	    try
	    {
	      Long localLong = Long.valueOf(paramString);
	      
	      Session localSession = getSession();
	      localCriteria = (Criteria) localSession.createCriteria(CmnAttdocMst.class);
	      localCriteria.add((Criterion) Restrictions.eq("SR_NO", localLong));
	      
	      
	      CmnAttdocMst localCmnAttdocMst = (CmnAttdocMst)localCriteria.uniqueResult();
	     
	      return localCmnAttdocMst;
	    }
	    catch (Exception localException)
	    {
	      this.logger.error("Exception occured in " + getClass().getName() + " exception is " + localException);
	    }
	    return null;
	  }

	 
	@Override
	public void deleteUpdateCsrfEmpdata(String strSevarthId,String ddoCode) {

		Session hibSession = getSession();

		Long finalCheckFlag=null;
		StringBuffer sb= new StringBuffer();
		gLogger.info("strSevarthId: "+strSevarthId);
		sb.append("update ifms.FRM_FORM_S1_DTLS_1 set status_flag=3 where upper(SEVARTH_ID) = '"+strSevarthId.toUpperCase()+"' and ddo_code='"+ddoCode+"'  and (FILE_VERIFY_STATUS is null or FILE_VERIFY_STATUS!='Y')");
		gLogger.info("Query to sevarth id in form s1 delete records:  " + sb.toString());
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());
		sqlQuery.executeUpdate();
	 
	}
	@Override
	public int deletedUpdateCsrfEmpdatamove(String strSevarthId,String ddoCode,int Status) {

		Session hibSession = getSession();

		Long finalCheckFlag=null;
		StringBuffer sb= new StringBuffer();
		gLogger.info("strSevarthId: "+strSevarthId);
		sb.append("insert into ifms.FRM_FORM_S1_DTLS_1_HST select FORM_S1_ID  ,SEVARTH_ID  ,DCPS_ID ,DESIGNATION ,TITLE  ,EMP_NAME  ,EMP_FIRST_NAME  ,EMP_MIDDLE_NAME  ,EMP_LAST_NAME  ,FATHER_NAME_FIRST "
				+ " ,FATHER_NAME_MIDDLE  ,FATHER_NAME_LAST  ,MOTHER_NAME_FIRST  ,EMP_DOB  ,GENDER  ,DTO_CODE ,DDO_CODE  ,PAN_NO  ,AADHAR_NO ,PRESENT_ADDRESS_FLATNO  "
				+ ",PRESENT_ADDRESS_BUILDING  ,PRESENT_ADDRESS_TALUKA  ,PRESENT_ADDRESS_LANDMARK  ,PRESENT_ADDRESS_DISTRICT  ,PRESENT_ADDRESS_STATE  ,PRESENT_ADDRESS_COUNTRY "
				+ " ,PRESENT_ADDRESS_PINCODE  ,PERMANENT_ADDRESS_FLATNO  ,PERMANENT_ADDRESS_BUILDING  ,PERMANENT_ADDRESS_TALUKA  ,PERMANENT_ADDRESS_LANDMARK  ,"
				+ "PERMANENT_ADDRESS_DISTRICT  ,PERMANENT_ADDRESS_STATE  ,PERMANENT_ADDRESS_COUNTRY  ,PERMANENT_ADDRESS_PINCODE  ,PHONE_NO  ,MOBILE_NO  ,EMAIL_ID  "
				+ ",SMS_SUB_FLAG  ,EMAIL_SUB_FLAG  ,HINDI_SUB_FLAG ,EMP_DOJ  ,EMP_DOR  ,EMP_CLASS  ,EMP_DEPT  ,EMP_MINISTRY  ,EMP_DDO  ,PAY_SCALE  ,BASIC_SALARY  ,"
				+ "PPAN  ,BANK_DETAILS_FLAG  ,BANK_DETAILS_TYPE  ,BANK_ACCOUNT_NO  ,BANK_NAME  ,BANK_BRANCH  ,BANK_ADDRESS  ,BANK_STATE ,BANK_COUNTRY  ,BANK_PIN  ,"
				+ "EMP_NUMBER_NOMINEE  ,NOMINEE_NAME_1  ,NOMINEE_DOB_1 ,NOMINEE_REALTION_1  ,NOMINEE_PERCENT_SHARE_1 ,NOMINEE_GARDIAN_NAME_1  ,NOMINEE_RENDER_CONDITION_1  ,"
				+ "NOMINEE_NAME_2  ,NOMINEE_DOB_2  ,NOMINEE_REALTION_2  ,NOMINEE_PERCENT_SHARE_2 ,NOMINEE_GARDIAN_NAME_2  ,NOMINEE_RENDER_CONDITION_2  ,NOMINEE_NAME_3  ,"
				+ "NOMINEE_DOB_3  ,NOMINEE_REALTION_3  ,NOMINEE_PERCENT_SHARE_3 ,NOMINEE_GARDIAN_NAME_3  ,NOMINEE_RENDER_CONDITION_3  ,PRAN_NO  ,CREATED_DATE TIMESTAMP ,"
				+ "BANKIFSCCODE ,EMPMARITALSTATUS  ,DDO_REG_NO ,ACKNO  ,FILE_VERIFY_STATUS  ,NSDL_STATUS INTEGER ,BATCH_ID  ,PHOTO_ATTACHMENT_NAME  ,SIGN_ATTACHMENT_NAME  ,"
				+ "IMAGES_LOCATION_PATH  "
				+ ",PRAN_STATUS INTEGER ,NOMINEE_ADDRESS_1  ,NOMINEE_ADDRESS_2  ,NOMINEE_ADDRESS_3  ,STATUS_FLAG,current_date  from ifms.FRM_FORM_S1_DTLS_1 "
				+ "where upper(SEVARTH_ID) = '"+strSevarthId.toUpperCase()+"' and ddo_code='"+ddoCode+"' and (FILE_VERIFY_STATUS is null or FILE_VERIFY_STATUS!='Y') and status_flag="+Status);
		gLogger.info("Query to sevarth id in form s1 delete records:  " + sb.toString());
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());
		return sqlQuery.executeUpdate();
		 
	}
	
	@Override
	public void deletedCsrfEmpdatamove(String strSevarthId,String ddoCode,int Status) { 
		Session hibSession = getSession();
 
		Long finalCheckFlag=null;
		StringBuffer sb= new StringBuffer();
		gLogger.info("strSevarthId: "+strSevarthId);
		sb.append("delete from  ifms.FRM_FORM_S1_DTLS_1  where upper(SEVARTH_ID) = '"+strSevarthId.toUpperCase()+"' and ddo_code='"+ddoCode+"'  and status_flag=3 and (FILE_VERIFY_STATUS is null or FILE_VERIFY_STATUS!='Y')");
		gLogger.info("Query to sevarth id in form s1 delete records:  " + sb.toString());
		Query sqlQuery = hibSession.createSQLQuery(sb.toString());
		sqlQuery.executeUpdate();
	}
	 
	public List getDDOOfcAddDesgName(String locId) {
		Session hibeSession = getSession();
		String query = "select  dcps.ADDRESS1,dcps.DDO_CODE,  ddo.DDO_OFFICE, ddo.DSGN_NAME, dist.DISTRICT_NAME,tal.TALUKA_NAME,stte.STATE_NAME  from"
				+ " MST_DCPS_DDO_OFFICE dcps, ORG_DDO_MST ddo, CMN_DISTRICT_MST dist,  CMN_TALUKA_MST tal, CMN_STATE_MST stte where ddo.DDO_CODE = "
				+ "dcps.DDO_CODE and dcps.DISTRICT = dist.DISTRICT_ID and dcps.TALUKA = tal.TALUKA_ID and dcps.STATE = stte.STATE_ID and dist.LANG_ID=1 "
				+ "and ddo.LOCATION_CODE=" + locId;
		Query lQuery = hibeSession.createSQLQuery(query);
		List resultList = lQuery.list();
		logger.info("size of list is .." + resultList.size());
		return resultList;
	}
}
