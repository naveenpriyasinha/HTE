package com.tcs.sgv.eis.zp.zpDdoOffice.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//import net.sf.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.valueobject.AclPostroleRlt;
import com.tcs.sgv.acl.valueobject.AclRoleMst;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.DdoOutsideSevaarthDAOImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDdoOffice.valueobject.ZpRltDdoMap;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDao;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.mstscr.util.MstScrnUtility;
public class ZpDDOOfficeMstDAOImpl extends GenericDaoHibernateImpl<ZpAdminOfficeMst,Long> implements ZpDDOOfficeMstDAO
{
	MstScrnUtility mstScrnUtility = new MstScrnUtility();
	Session hibSession=null;
	private final ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/dcps/DCPSConstants");
	public ZpDDOOfficeMstDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	public void insertRltZpDdoMap(Long ZP_DDO_POST_ID,Long REPT_DDO_POST_ID, Long FINAL_DDO_POST_ID, Long SPECIAL_DDO_POST_ID, String ZP_DDO_CODE,String REPT_DDO_CODE,String FINAL_DDO_CODE,String SPECIAL_DDO_CODE,String lstrLevel,Long CreatedUser,Long CreatedPost,Map inputMap) throws Exception
	{
		logger.info(">>>>>>>>>>>>>>>>insertRltZpDdoMap>>>>>>>>>>>>>>>>>>DAOIMPL");
		Long ZP_MAP_ID = null;
		try{
			ZpRltDdoMap objZpRltDdoMap=new ZpRltDdoMap();
			//ZP_MAP_ID = IFMSCommonServiceImpl.getNextSeqNum("RLT_ZP_DDO_MAP", inputMap);
			ZP_MAP_ID = getNextSeqNoLocForRltZpDdoMap();
			objZpRltDdoMap.setZP_DDO_POST_ID(ZP_DDO_POST_ID);
			objZpRltDdoMap.setZP_MAP_ID(ZP_MAP_ID);
			objZpRltDdoMap.setREPT_DDO_POST_ID(REPT_DDO_POST_ID);
			objZpRltDdoMap.setFINAL_DDO_POST_ID(FINAL_DDO_POST_ID);
			objZpRltDdoMap.setSPECIAL_DDO_POST_ID(SPECIAL_DDO_POST_ID);
			objZpRltDdoMap.setZP_DDO_CODE(ZP_DDO_CODE);
			objZpRltDdoMap.setREPT_DDO_CODE(REPT_DDO_CODE);
			objZpRltDdoMap.setFINAL_DDO_CODE(FINAL_DDO_CODE);
			objZpRltDdoMap.setSPECIAL_DDO_CODE(SPECIAL_DDO_CODE);
			objZpRltDdoMap.setLANG_ID(1l);
			objZpRltDdoMap.setCREATED_DATE(DBUtility.getCurrentDateFromDB());
			objZpRltDdoMap.setCREATED_USER_ID(CreatedPost);
			objZpRltDdoMap.setCREATED_POST_ID(CreatedUser);
			objZpRltDdoMap.setZPLEVEL(Long.valueOf(lstrLevel));
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>"+hibSession);
			hibSession.save(objZpRltDdoMap);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public List retirveDdoType(String adminOfcId)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT OFFICE_CODE FROM ZP_ADMIN_OFFICE_MST where OFC_ID like '%"+adminOfcId+"%'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpDDOOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}

	public List retirveFinalDDOPostId(Long DDOCode)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT POST_ID FROM ORG_DDO_MST where DDO_CODE="+DDOCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List retirveSpecialDDOPostId(Long DDOCode)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT POST_ID FROM ORG_DDO_MST where DDO_CODE="+DDOCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List retirveRepoDDOPostId(String DDOCode)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT POST_ID FROM ORG_DDO_MST where DDO_CODE="+DDOCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}

	public List getCountofDDOCode(String CountCode)
	{
		List temp=null;
		try
		{		
			String branchQuery = "select count(qq.DDO_CODE) from ORG_DDO_MST qq where DDO_CODE like '"+ CountCode +"%'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();

			logger.error("branchQuery"+branchQuery);
			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List getAdminOfcCode(String AdminOfcID)
	{
		List temp=null;
		try
		{		
			String branchQuery = "select aa.OFFICE_CODE from ZP_ADMIN_OFFICE_MST aa where OFC_ID="+AdminOfcID;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List getTreasuryName(Long TOCode){
		List temp=null;
		try
		{		
			String branchQuery = "select CM.loc_id,CM.loc_name from rlt_ddo_org RL join cmn_location_mst CM on RL.location_code = CM.loc_id where RL.ddo_code = "+TOCode;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List checkRepoDDO(Long DDOCODE)
	{
		List temp=null;
		try
		{		
			String branchQuery = "select * from ORG_DDO_MST where DDO_CODE like '%"+ DDOCODE +"%'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}

	public List retriveDisctOfcList(Long OfcId) 
	{
		List temp=null;

		//hibSession = getSession();
		try
		{		
			String branchQuery = "select DIST_MST_OFC_NAME,DIST_CODE from  ZP_ADMIN_OFFICE_DISTRICT_MPG where admin_OFFICE_CODE=(select OFFICE_CODE from ZP_ADMIN_OFFICE_MST where OFC_ID="+OfcId+")";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			logger.error("sqlQuery Size"+sqlQuery.toString());
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}

	public List retriveMaxOfcCode() 
	{
		ZpAdminOfficeMst objZpAdminOfficeMst = null;
		List temp=null;
		Long Ofc_Code;
		Session hibSession = getSession();
		try
		{		
			String branchQuery = "select OFC_ID,max(OFFICE_CODE)as Office_Code from ZP_ADMIN_OFFICE_MST group by OFC_ID order by OFFICE_CODE desc";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			logger.error("List Size"+temp.size());
		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;

	}
	@Override
	public List searchZpAdminOfficeDetailsList(ZpAdminOfficeMst zpAdminOfficeMstVO, int startIndex, int pageSize) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void insertOrgDdoMst(String lStrDdoCode,String lStrDdoName,String lStrDdoPrsnlName,Long lLngPostId,Long lLngUserIdCrtd,
			String lStrLocationCode,Long lLngPostIdCrtd,String lstrDeptLocCode,String lstrDdoType,String lstrDept_Code,String lstrHOD_Code,String lstrDeptType,Map inputMap)throws Exception
			{
		Long lLndDdoId = null;
		try{
			OrgDdoMst lObjOrgDdoMst = new OrgDdoMst();
			//lLndDdoId = IFMSCommonServiceImpl.getNextSeqNum("org_ddo_mst", inputMap);
			lLndDdoId = getNextSeqNoLocForOrgDdoMst();
			lObjOrgDdoMst.setDdoId(lLndDdoId);
			lObjOrgDdoMst.setDDO_TYPE(Long.parseLong(lstrDdoType));
			lObjOrgDdoMst.setDeptLocCode(lstrDept_Code);
			lObjOrgDdoMst.setHodLocCode(lstrHOD_Code);
			if(lstrDeptType!=null)
				lObjOrgDdoMst.setADMIN_DEPT_TYPE(Long.parseLong(lstrDeptType));
			lObjOrgDdoMst.setDdoCode(lStrDdoCode);
			lObjOrgDdoMst.setDdoName(lStrDdoName);
			lObjOrgDdoMst.setDdoPersonalName(lStrDdoPrsnlName);
			lObjOrgDdoMst.setPostId(lLngPostId);
			lObjOrgDdoMst.setLangId(1l);
			logger.info("---------Before Setting DDO Office-----------");
			lObjOrgDdoMst.setDdoOffice(lStrDdoName);
			logger.info("---------After Setting DDO Office-----------");
			//lObjOrgDdoMst.setDeptLocCode(lstrDeptLocCode);
			lObjOrgDdoMst.setCreatedBy(lLngUserIdCrtd);
			lObjOrgDdoMst.setCreatedByPost(lLngPostIdCrtd);
			lObjOrgDdoMst.setCreatedDate(DBUtility.getCurrentDateFromDB());
			lObjOrgDdoMst.setDbId(99l);
			lObjOrgDdoMst.setLocationCode(lStrLocationCode);
			hibSession.save(lObjOrgDdoMst);
			//ghibSession.flush();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
			}

	public void insertWorkFlow(Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,Long lLongSpecialPostId,Long lLongCreatedByUserId,String lStrLocCode,Map inputMap,String lstrLevel) throws Exception {


		Long lLongHierarchyRefId = null;
		Query lQuery = null;
		StringBuilder lSBQuery = null;

		String lStrDocNameArr[] = {gObjRsrcBndle.getString("DCPS.RegistrationForm").trim(), 
				gObjRsrcBndle.getString("DCPS.ChangesForm").trim(),
				gObjRsrcBndle.getString("DCPS.SixthPCArrears").trim(),
				gObjRsrcBndle.getString("DCPS.OnlineContribution").trim()};

		String lStrDocIdArr[] = {gObjRsrcBndle.getString("DCPS.RegistrationFormID").trim(), 
				gObjRsrcBndle.getString("DCPS.ChangesFormID").trim(),
				gObjRsrcBndle.getString("DCPS.SixthPCArrearsID").trim(),
				gObjRsrcBndle.getString("DCPS.OnlineContributionID").trim()};

		try {

			for(Integer lInt=0;lInt<4;lInt++)
			{
				logger.info("inside for!");
				logger.info("lstrLevel: "+lstrLevel);
				//lLongHierarchyRefId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierarchy_reference_mst", inputMap);
				lLongHierarchyRefId = getNextSeqNoLocForHierarchyRefId();
				lSBQuery = new StringBuilder();
				lSBQuery.append("INSERT INTO WF_HIERARCHY_REFERENCE_MST VALUES \n");
				lSBQuery.append("(:hierachyRefId,:refName,:description,:docId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:parentId,:dbId,:locCode,:langId,:parentHierarchyRefId,:branchcode,:hierarchySeqId) \n"); 
				lQuery = hibSession.createSQLQuery(lSBQuery.toString());

				lQuery.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQuery.setParameter("refName", lStrDocNameArr[lInt]);
				lQuery.setParameter("description", lStrDocNameArr[lInt]);
				lQuery.setParameter("docId", Long.valueOf(lStrDocIdArr[lInt]));
				lQuery.setParameter("crtUser","1");
				lQuery.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQuery.setParameter("lstUpdUser",null );
				lQuery.setParameter("lstUpdDate",null );
				lQuery.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQuery.setParameter("endDate",null );
				lQuery.setParameter("activeFlag", 1);
				lQuery.setParameter("parentId", null);
				lQuery.setParameter("dbId", 99);
				lQuery.setParameter("locCode",lStrLocCode);
				lQuery.setParameter("langId","1" );
				lQuery.setParameter("parentHierarchyRefId",null );
				lQuery.setParameter("branchcode",null );
				lQuery.setParameter("hierarchySeqId",lLongHierarchyRefId );
				lQuery.executeUpdate();
				hibSession.flush();
				logger.info("lStrDocIdArr[lInt]: "+lStrDocIdArr[lInt]);
				if(lStrDocIdArr[lInt].equals("700001"))
				{
					logger.info(".......................in If Condtition...................");
					if(lstrLevel.equalsIgnoreCase("2"))
					{
						logger.info(".......................in If Level 2 Condtition...................");
						insertWfHierachyPostMpgNewRegFormLevel2(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongTOAsstPostId,lStrLocCode,inputMap);
					}
					if(lstrLevel.equalsIgnoreCase("3")){
						logger.info(".......................in If Level 3 Condtition...................");
						insertWfHierachyPostMpgNewRegFormLevel3(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongTOAsstPostId,lLongTOPostId,lStrLocCode,inputMap);
					}
					if(lstrLevel.equalsIgnoreCase("4")){
						logger.info(".......................in If Level 4 Condtition...................");
						insertWfHierachyPostMpgNewRegFormLevel4(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongSpecialPostId,lLongTOAsstPostId,lLongTOPostId,lStrLocCode,inputMap);
					}
				}
				if(lStrDocIdArr[lInt].equals("700005"))
				{
					insertWfHierachyPostMpgChanges(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lStrLocCode,inputMap);
				}
				if(lStrDocIdArr[lInt].equals("700002"))
				{
					if(lstrLevel.equalsIgnoreCase("2"))
						insertWfHierachyPostMpgSixPCArrearsLevel2(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongTOAsstPostId,lStrLocCode,inputMap);
					if(lstrLevel.equalsIgnoreCase("3"))
						insertWfHierachyPostMpgSixPCArrearsLevel3(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongTOAsstPostId,lLongTOPostId,lStrLocCode,inputMap);
					if(lstrLevel.equalsIgnoreCase("4"))
						insertWfHierachyPostMpgSixPCArrearsLevel4(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongSpecialPostId,lLongTOAsstPostId,lLongTOPostId,lStrLocCode,inputMap);
				}
				if(lStrDocIdArr[lInt].equals("700006"))
				{
					if(lstrLevel.equalsIgnoreCase("2"))
						insertWfHierachyPostMpgOnlineContriLevel2(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongTOAsstPostId,lStrLocCode,inputMap);
					if(lstrLevel.equalsIgnoreCase("3"))
						insertWfHierachyPostMpgOnlineContriLevel3(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongTOAsstPostId,lLongTOPostId,lStrLocCode,inputMap);
					if(lstrLevel.equalsIgnoreCase("4"))
						insertWfHierachyPostMpgOnlineContriLevel4(lLongHierarchyRefId,lLngPostIdAsst,lLongDDOPostId,lLongSpecialPostId,lLongTOAsstPostId,lLongTOPostId,lStrLocCode,inputMap);
				}
			}

			insertWfHierarchyTableSeqMst(lStrLocCode,inputMap);

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}

	public void insertWfHierachyPostMpgNewRegFormLevel4(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,Long lLongSpecial,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30,40,50};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOAsstPostId,lLongTOPostId,lLongSpecial};

		try {

			for(Integer lInt=0;lInt<4;lInt++)
			{
				lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgNewRegFormLevel3(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30,40};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOAsstPostId,lLongTOPostId};

		try {

			for(Integer lInt=0;lInt<3;lInt++)
			{
				lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgNewRegFormLevel2(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOAsstPostId};

		try {

			for(Integer lInt=0;lInt<2;lInt++)
			{
				//lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);
				lLongHierarchySeqId = getNextSeqNoLocForHierarchySeqId();
				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgSixPCArrearsLevel4(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,Long lLongSpecial,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30,40,50};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOPostId,lLongTOAsstPostId,lLongSpecial};

		try {

			for(Integer lInt=0;lInt<4;lInt++)
			{
				lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgSixPCArrearsLevel3(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30,40};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOAsstPostId,lLongTOPostId};

		try {

			for(Integer lInt=0;lInt<3;lInt++)
			{
				lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgSixPCArrearsLevel2(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOPostId,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOPostId};

		try {

			for(Integer lInt=0;lInt<2;lInt++)
			{
				//lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);
				lLongHierarchySeqId = getNextSeqNoLocForHierarchySeqId();
				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgOnlineContriLevel4(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,Long lLongSpecial,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30,40,50};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOPostId,lLongTOAsstPostId,lLongSpecial};

		try {

			for(Integer lInt=0;lInt<4;lInt++)
			{
				lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgOnlineContriLevel3(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30,40};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOAsstPostId,lLongTOPostId};

		try {

			for(Integer lInt=0;lInt<3;lInt++)
			{
				lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierachyPostMpgOnlineContriLevel2(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOPostId,String lStrLocCode,Map inputMap ) throws Exception {
		logger.info("inside insertWfHierachyPostMpgOnlineContriLevel2");
		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongTOPostId};

		try {

			for(Integer lInt=0;lInt<2;lInt++)
			{

				//lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);
				lLongHierarchySeqId =getNextSeqNoLocForHierarchySeqId();
				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public void insertWfHierarchyTableSeqMst(String lStrLocCode,Map inputMap)throws Exception
	{
		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongTableSeqMstId = null;

		String[] lArrStrTableName = {"wf_job_mst","wf_job_mvmnt_mst","wf_audit_trail_mst","wf_marked_hierachy_mst","wf_notification","wf_job_attribute","wf_job_grp_mst","wf_table_seq_mst","wf_doc_mvmnt_mst"};
		Long[] lArrLongPrivMaxId = {1l,1l,1l,1l,54004l,909l,927196l,89l,1l};

		try {

			for(Integer lInt=0;lInt<9;lInt++)
			{
				//lLongTableSeqMstId = IFMSCommonServiceImpl.getNextSeqNum("wf_table_seq_mst", inputMap);
				lLongTableSeqMstId = getNextSeqNoLocForTableSeqMstId();
				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_TABLE_SEQ_MST VALUES \n");
				lSBQueryInner.append("(:seqMstId,:tableName,:privMaxId,:crtdUser,:crtdPost,:createdDate,:lstUpdUser,:lstUpdPost,:lstUpdDate,:dbId,:locId,:pkLength) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("seqMstId", lLongTableSeqMstId);
				lQueryInner.setParameter("tableName", lArrStrTableName[lInt] );
				lQueryInner.setParameter("privMaxId", lArrLongPrivMaxId[lInt]);
				lQueryInner.setParameter("crtdUser","1");
				lQueryInner.setParameter("crtdPost","1");
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null);
				lQueryInner.setParameter("lstUpdPost",null);
				lQueryInner.setParameter("lstUpdDate",null);
				lQueryInner.setParameter("dbId", 99);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("pkLength",20 );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw e;
		}
	}	

	public void insertWfHierachyPostMpgChanges(Long lLongHierarchyRefId ,Long lLngPostIdAsst,Long lLongDDOPostId,String lStrLocCode,Map inputMap ) throws Exception {

		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongHierarchySeqId = null;

		Integer[] lArrIntLevelId = {10,30};
		Long lLongArrPostId[] = {lLngPostIdAsst,lLongDDOPostId};

		try {

			for(Integer lInt=0;lInt<2;lInt++)
			{
				//lLongHierarchySeqId = IFMSCommonServiceImpl.getNextSeqNum("wf_hierachy_post_mpg", inputMap);
				lLongHierarchySeqId = getNextSeqNoLocForHierarchySeqId();
				lSBQueryInner = new StringBuilder();

				logger.info("lLongHierarchySeqId: "+lLongHierarchySeqId);
				lSBQueryInner.append("INSERT INTO WF_HIERACHY_POST_MPG VALUES \n");
				lSBQueryInner.append("(:hierachySeqId,:parentHierachy,:postId,:levelId,:hierachyRefId,:crtUser,:createdDate,:lstUpdUser,:lstUpdDate,:startDate,:endDate,:activeFlag,:locId,:langId,:dueDays) \n"); 

				lQueryInner = hibSession.createSQLQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("hierachySeqId", lLongHierarchySeqId);
				lQueryInner.setParameter("parentHierachy", null );
				lQueryInner.setParameter("postId", lLongArrPostId[lInt]);
				lQueryInner.setParameter("levelId", lArrIntLevelId[lInt]);
				lQueryInner.setParameter("hierachyRefId", lLongHierarchyRefId);
				lQueryInner.setParameter("crtUser","1" );
				lQueryInner.setParameter("createdDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("lstUpdUser",null );
				lQueryInner.setParameter("lstUpdDate",null );
				lQueryInner.setParameter("startDate",DBUtility.getCurrentDateFromDB() );
				lQueryInner.setParameter("endDate",null );
				lQueryInner.setParameter("activeFlag", 1);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("langId",1 );
				lQueryInner.setParameter("dueDays",null );

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			logger.error("Error is : " + e, e);
			throw (e);
		}
	}
	public List RetirveParentdtlfrmReptCode(String RptDDOCode)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT DEPT_LOC_CODE||'##'||HOD_LOC_CODE FROM ORG_DDO_MST where DDO_CODE like '%"+RptDDOCode+"%'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List RetirveAdminDeptType(String lstrAdminDeptType)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT b.DEPT_ID FROM MST_ZP_DEPT b where DEPT_ID like '%"+lstrAdminDeptType+"%'";
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public void insertPostRoleRlt(Long lLngPostId, Long lLngUserIdCrtd, Long lLngPostIdCrtd, Map inputMap,String lStrUserType)throws Exception
	{
		Long lLngPostRoleId = null;
		AclRoleMst lObjAclRoleMst = null;

		try{
			OrgPostMstDaoImpl orgPostId = new OrgPostMstDaoImpl(OrgPostMst.class,this.getSessionFactory());
			OrgPostMst postId = orgPostId.read(lLngPostId);
			OrgPostMst postIdCrtd = orgPostId.read(lLngPostIdCrtd);

			DdoOutsideSevaarthDAOImpl lObjDdoOutSideSevaarth = new DdoOutsideSevaarthDAOImpl(AclRoleMst.class,this.getSessionFactory());

			if(lStrUserType.trim().equals("DDO"))
			{
				logger.info("inside RoleMapping.........DDO...........");
				lObjAclRoleMst = (AclRoleMst) lObjDdoOutSideSevaarth.read(700017l);
			}
			else if(lStrUserType.trim().equals("ASST"))
			{
				logger.info("inside RoleMapping.........ASST...........");
				lObjAclRoleMst = (AclRoleMst) lObjDdoOutSideSevaarth.read(700018l);
			}
			else if(lStrUserType.trim().equals("ReportingDDO"))
			{
				logger.info("inside RoleMapping.........ASST...........");
				lObjAclRoleMst = (AclRoleMst) lObjDdoOutSideSevaarth.read(700019l);
			}
			else if(lStrUserType.trim().equals("FinalDDO"))
			{
				logger.info("inside RoleMapping.........ASST...........");
				lObjAclRoleMst = (AclRoleMst) lObjDdoOutSideSevaarth.read(700020l);
			}
			else if(lStrUserType.trim().equals("SpecialDDO"))
			{
				logger.info("inside RoleMapping.........ASST...........");
				lObjAclRoleMst = (AclRoleMst) lObjDdoOutSideSevaarth.read(700021l);
			}
			logger.info("outside if else!");
			CmnLookupMstDAOImpl actFlag = new CmnLookupMstDAOImpl(CmnLookupMst.class,this.getSessionFactory()); 
			CmnLookupMst activeFlag = actFlag.read(Long.parseLong("1"));

			OrgUserMstDao lObjOrgUserMstDao = new OrgUserMstDaoImpl(OrgUserMst.class,this.getSessionFactory());
			OrgUserMst lObjOrgUserMstCrtd = lObjOrgUserMstDao.read(lLngUserIdCrtd);

			AclPostroleRlt lObjAclPostRoleRlt = new AclPostroleRlt();
			logger.info("before get next seq num");
			//lLngPostRoleId = IFMSCommonServiceImpl.getNextSeqNum("acl_postrole_rlt", inputMap);
			lLngPostRoleId = getNextSeqNoLocForAclPostRoleRlt();
			logger.info("after get next seq num");
			lObjAclPostRoleRlt.setPostRoleId(lLngPostRoleId);
			lObjAclPostRoleRlt.setOrgPostMst(postId);
			lObjAclPostRoleRlt.setAclRoleMst(lObjAclRoleMst);
			lObjAclPostRoleRlt.setStartDate(DBUtility.getCurrentDateFromDB());
			lObjAclPostRoleRlt.setCmnLookupMstByActivate(activeFlag);
			lObjAclPostRoleRlt.setOrgPostMstByCreatedByPost(postIdCrtd);
			lObjAclPostRoleRlt.setOrgUserMstByCreatedBy(lObjOrgUserMstCrtd);
			lObjAclPostRoleRlt.setCreatedDate(DBUtility.getCurrentDateFromDB());
			hibSession.save(lObjAclPostRoleRlt);
			//ghibSession.flush();
		}catch(Exception e){
			logger.error(" Error is : " + e, e);
			throw e;
		}
	}
	public List getDistOfcByCode(Long OfcCode, String treasuryId) 
	{
		List temp=null;

		//hibSession = getSession();
		try
		{		
			String branchQuery = " select DIST_MST_OFC_NAME,DIST_CODE from ZP_ADMIN_OFFICE_DISTRICT_MPG where admin_OFFICE_CODE='"+OfcCode+"' and DIST_CODE =(SELECT LOC_DISTRICT_ID FROM CMN_LOCATION_MST where LOC_ID = '"+treasuryId+"') ";
			logger.error("#################query="+branchQuery);
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();

			
			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpAdminOfficeMstDAOImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}


	public List getAllDDOOfficeDtlsData() {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM  RLT_ZP_DDO_MAP where LANG_ID =1 order by ZP_MAP_ID desc";

		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}



	public List getLocationCodeForward(String EmpID)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT OD.LOCATION_CODE FROM mst_dcps_emp EM, org_ddo_mst OD where OD.ddo_code = EM.ddo_code and EM.dcps_emp_id ="+EmpID;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in ZpDDOOfficeMstDAOImpl + getLocationCodeForward\n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List checkRepopostrole(Long postID,Long roleID)
	{
		List temp=null;
		try
		{		
			logger.info(".....................checkRepopostrole...................");
			String branchQuery = "SELECT POST_ROLE_ID FROM ACL_POSTROLE_RLT where post_id="+postID+" and role_id="+roleID;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();
			logger.info(".....................checkRepopostrole..........DONE........."+temp.size());

			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in checkpostrole + checkpostrole\n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List checkSpecialpostrole(Long postID,Long roleID)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT POST_ROLE_ID FROM ACL_POSTROLE_RLT where post_id="+postID+" and role_id="+roleID;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in checkpostrole + checkpostrole\n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	public List checkFinalpostrole(Long postID,Long roleID)
	{
		List temp=null;
		try
		{		
			String branchQuery = "SELECT POST_ROLE_ID FROM ACL_POSTROLE_RLT where post_id="+postID+" and role_id="+roleID;
			Query sqlQuery= hibSession.createSQLQuery(branchQuery);
			temp= sqlQuery.list();


			logger.error("List Size"+temp.size());

		}
		catch(Exception e){
			logger.error("Error in checkpostrole + checkpostrole\n " + e);
			e.printStackTrace();
		}
		return temp;
	}

	//added by Demolisher
	public List getAllDDOOfficeDtlsDataByPostID(long postId) {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM  RLT_ZP_DDO_MAP where LANG_ID =1 and REPT_DDO_POST_ID="+postId+" and STATUS=0 order by ZP_MAP_ID desc";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}

	public long getAllDDOOfficeType(long postId) {
		//Criteria objCrt = null;
		List list = null;
		long ddoType;

		Session hibSession = getSession();
		String strQuery = "SELECT DDO_TYPE FROM ORG_DDO_MST where POST_ID="+postId;
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		ddoType=Long.parseLong(list.toString());

		return ddoType;
	}

	public void approveEmployeeLogin(long ddocode,int flag)
	{
		//Criteria objCrt = null;
		int records = -1;
		String strQuery1=null;
		String strQuery2=null;
		Session hibSession = getSession();
		if(flag==1){

			strQuery1 = "update ORG_USER_MST set ACTIVATE_FLAG=1 where USER_NAME = '0"+ddocode+"_AST'";
			strQuery2 = "update RLT_ZP_DDO_MAP set STATUS=1 where ZP_DDO_CODE="+ddocode;
		}else
		{
			strQuery1 = "update ORG_USER_MST set ACTIVATE_FLAG=1 where USER_NAME = '"+ddocode+"_AST'";
			strQuery2 = "update RLT_ZP_DDO_MAP set STATUS=1 where ZP_DDO_CODE="+ddocode;
		}
		logger.info(strQuery1);
		Query query1 = hibSession.createSQLQuery(strQuery1);
		query1.executeUpdate();
		logger.info(strQuery2);
		Query query2 = hibSession.createSQLQuery(strQuery2);
		query2.executeUpdate();
	}

	public void rejectEmployeeLogin(long ddocode,int flag)
	{
		//Criteria objCrt = null;
		int records = -1;
		String strQuery1=null;
		String strQuery2=null;
		Session hibSession = getSession();
		if(flag==1)
		{	
			strQuery1 = "update ORG_USER_MST set ACTIVATE_FLAG=0 where USER_ID in (SELECT USER_ID FROM ORG_USERPOST_RLT where POST_ID in (SELECT ZP_DDO_POST_ID FROM RLT_ZP_DDO_MAP where ZP_DDO_CODE="+ddocode+")) OR USER_NAME = '0"+ddocode+"_AST'";
			strQuery2 = "update RLT_ZP_DDO_MAP set STATUS=-1 where ZP_DDO_CODE="+ddocode;
		}else
		{
			strQuery1 = "update ORG_USER_MST set ACTIVATE_FLAG=0 where USER_ID in (SELECT USER_ID FROM ORG_USERPOST_RLT where POST_ID in (SELECT ZP_DDO_POST_ID FROM RLT_ZP_DDO_MAP where ZP_DDO_CODE="+ddocode+")) OR USER_NAME = '"+ddocode+"_AST'";
			strQuery2 = "update RLT_ZP_DDO_MAP set STATUS=-1 where ZP_DDO_CODE="+ddocode;
		}
		logger.info(strQuery1);
		Query query1 = hibSession.createSQLQuery(strQuery1);
		logger.info(strQuery1);
		query1.executeUpdate();
		logger.info(strQuery2);
		Query query2 = hibSession.createSQLQuery(strQuery2);
		query2.executeUpdate();
	}

	public List viewAllDDOApproveOfficeDtlsData(long postId) {
	//	Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM RLT_ZP_DDO_MAP where REPT_DDO_POST_ID="+postId+" AND status=1";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}

	public List viewAllDDORejectOfficeDtlsData(long postId) {
	//	Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM RLT_ZP_DDO_MAP where REPT_DDO_POST_ID="+postId+" AND status=-1";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}

	public List viewAllDDOApproveOfficeDtlsDataExcel(long postId) {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM MST_DCPS_DDO_OFFICE where DDO_CODE in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_POST_ID="+postId+" AND ZP_DDO_POST_ID in (SELECT POST_ID FROM ORG_USERPOST_RLT where USER_ID in (SELECT USER_ID FROM ORG_USER_MST where ACTIVATE_FLAG=1)))";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}

	public List viewAllDDORejectOfficeDtlsDataExcel(long postId) {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM MST_DCPS_DDO_OFFICE where DDO_CODE in (SELECT ZP_DDO_CODE FROM RLT_ZP_DDO_MAP where REPT_DDO_POST_ID="+postId+" AND ZP_DDO_POST_ID in (SELECT POST_ID FROM ORG_USERPOST_RLT where USER_ID in (SELECT USER_ID FROM ORG_USER_MST where ACTIVATE_FLAG=0)))";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}
	public List getsplDDOOfficelvlDtlsDataExcel(long postId) {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM MST_DCPS_DDO_OFFICE where DDO_OFFICE='Yes' and DDO_CODE in (SELECT final_ddo_code FROM RLT_ZP_DDO_MAP where rept_ddo_post_id="+postId+")";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}
	public List getDDOOfficelvlDtlsDataExcel(long postId) {
		//Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "SELECT * FROM MST_DCPS_DDO_OFFICE where DDO_CODE in (SELECT rept_ddo_code FROM RLT_ZP_DDO_MAP where rept_ddo_post_id="+postId+")";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();

		return list;
	}

	public String getDDOOfficelvlDtlsTestDataExcel(String ddocode) {
		//Criteria objCrt = null;
		List list = null;
		String ddoOffice;
		Session hibSession = getSession();
		String strQuery = "SELECT OFF_NAME FROM MST_DCPS_DDO_OFFICE where DDO_CODE ='"+ddocode+"'";
		logger.info(strQuery);
		Query query = hibSession.createSQLQuery(strQuery);
		list = query.list();
		ddoOffice = list.toString();

		return ddoOffice;
	}



	//added by vaibhav tyagi: start
	//added by vaibhav tyagi: start
	public List getAllDDOOfficeDtlsData(String districtSelected,String talukaSelected,String adminTypeSelected) {
		List list = null;

		Session hibSession = getSession();

		StringBuffer strQuery=new StringBuffer();
		strQuery.append("SELECT zp.ZP_DDO_CODE,zp.REPT_DDO_CODE,zp.FINAL_DDO_CODE,zp.SPECIAL_DDO_CODE,zp.ZPLEVEL,zp.STATUS FROM  RLT_ZP_DDO_MAP zp ");
		strQuery.append(" inner join mst_dcps_ddo_office office on zp.zp_ddo_code=office.ddo_code ");
		strQuery.append(" where zp.LANG_ID =1 and zp.status is not null ");
		if((districtSelected!=null)&&(districtSelected!="")&&(Long.parseLong(districtSelected)!=-1)){
			strQuery.append(" and office.district='"+districtSelected+"'");
		}

		if((talukaSelected!=null)&&(talukaSelected!="")&&(Long.parseLong(talukaSelected)!=-1)){
			strQuery.append(" and office.taluka='"+talukaSelected+"'");
		}

		if((adminTypeSelected!=null)&&(adminTypeSelected!="")&&(Long.parseLong(adminTypeSelected)!=-1)){
			strQuery.append(" and zp.ZP_DDO_CODE like '"+adminTypeSelected+"%'");
		}
		strQuery.append(" order by ZP_MAP_ID desc");

		logger.info("Details Query :"+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		list = query.list();

		return list;
	}
	//added by vaibhav tyagi :end

	public List getAllDistrict() 
	{
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
		strQuery.append("SELECT admin_code, admin_name FROM zp_admin_name_mst where admin_code in (06,07,19,20,31) order by admin_code");
		logger.info("Admin Type List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		adminTypeList = query.list();	
		return adminTypeList;
	}

	public List getAllTalukaByDistrict(String distId) 
	{
		List talukaList = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT taluka_id, taluka_name FROM cmn_taluka_mst where DISTRICT_ID='"+distId+"' order by taluka_name");
		logger.info("Taluka List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		talukaList = query.list();	
		return talukaList;
	}



	//added by vaibhav tyagi: end
	/*//added by roshan kumar on 03 april
public List getDiceListForAutoComplete(String searchKey) {
	ArrayList<ComboValuesVO> finalList = new ArrayList<ComboValuesVO>();
	ComboValuesVO cmbVO;
	Object[] obj;
	Session hibSession = getSession();
	StringBuilder sb = new StringBuilder();
	Query selectQuery = null;

	sb.append("select DICE_ID,DICE_CODE from ZP_ADMIN_DICE_MPG where used='0' and DICE_CODE LIKE '"+searchKey+"%' ");

	selectQuery = hibSession.createSQLQuery(sb.toString());
	//selectQuery.setParameter("searchKey", searchKey + '%');
	logger.info("Query is"+selectQuery.toString());
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
}*/

	public String getOPfficeName(String diceCode) {
		List listOfficeName = null;
		String officeName = null;
		Session hibSession = getSession();
		try {
			getSession();
			StringBuilder lSBQuery = new StringBuilder();
			lSBQuery.append("SELECT OFFICE_NAME FROM ZP_ADMIN_DICE_MPG WHERE used='0' and DICE_CODE="+diceCode);
			Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			if(lQuery.list() != null)
			{
				if(lQuery.list().get(0) != null)
				{
					listOfficeName =  lQuery.list();
					officeName = listOfficeName.get(0).toString();
				}
			}

		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Error is :" + e, e);
		}
		return officeName;
	}
	public synchronized Long getNextSeqNoLocForAclPostRoleRlt() {

		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'ACL_POSTROLE_RLT'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'ACL_POSTROLE_RLT'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;	
	}

	public synchronized Long getNextSeqNoLocForOrgDdoMst() {

		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'ORG_DDO_MST'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'ORG_DDO_MST'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;	
	}
	public synchronized Long getNextSeqNoLocForRltZpDdoMap() {

		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'RLT_ZP_DDO_MAP'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'RLT_ZP_DDO_MAP'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;	
	}
	public synchronized Long getNextSeqNoLocForHierarchyRefId() {

		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'WF_HIERARCHY_REFERENCE_MST'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'WF_HIERARCHY_REFERENCE_MST'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;	
	}
	public synchronized Long getNextSeqNoLocForHierarchySeqId() {

		
		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'WF_HIERACHY_POST_MPG'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		logger.info("seqId: "+seqId+" nextSeqId:"+nextSeqId);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'WF_HIERACHY_POST_MPG'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;	
	}
	
	public synchronized Long getNextSeqNoLocForTableSeqMstId() {

		
		Long seqId=0l;
		Long nextSeqId=0l;
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("select GENERATED_ID from  CMN_TABLE_SEQ_MST where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'WF_TABLE_SEQ_MST'");
		Query lQuery = hibSession.createSQLQuery(lSBQuery.toString());
		seqId = Long.valueOf(lQuery.uniqueResult().toString());
		nextSeqId=seqId+1;
		logger.info("seqId: "+seqId+" nextSeqId:"+nextSeqId);
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE CMN_TABLE_SEQ_MST SET GENERATED_ID="+nextSeqId+" where LOCATION_CODE='10000' and upper(TABLE_NAME)= 'WF_TABLE_SEQ_MST'");
		Query query = hibSession.createSQLQuery(sb.toString());
		query.executeUpdate();
		hibSession.flush();
		return seqId;	
	}
	
	//added by samdahan
	public int getUniqeInstituteIdCount(String strDdoCode) 
	{
		int uniqeInstituteIdCount=0;
		StringBuilder sb = new StringBuilder();

		sb.append(" select count(1) from MST_DCPS_DDO_OFFICE where UNIQUE_INSTITUTE_NO is not null ");
		
		logger.info(" Query to get count of getUniqeInstituteIdCount: "+sb.toString());
		
		Query selectQuery = hibSession.createSQLQuery(sb.toString());

		
		uniqeInstituteIdCount = Integer.parseInt(selectQuery.uniqueResult().toString());
		return uniqeInstituteIdCount;
	
	}
	//added by samdahan
	public String getDistName(String districtCode) {
		String distName="";
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT upper(DISTRICT_NAME) FROM CMN_DISTRICT_MST where DISTRICT_ID = '"+districtCode+"' ");
		
		logger.info(" Query to get dist name: "+sb.toString());
		
		Query selectQuery = hibSession.createSQLQuery(sb.toString());

		
		distName = selectQuery.uniqueResult().toString();
		return distName;
	
	}

	public List getDDOinfo(String ddocode) {
		
		List ddoInfo = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		
		
		//strQuery.append("SELECT ZP_DDO_CODE,REPT_DDO_CODE,FINAL_DDO_CODE,SPECIAL_DDO_CODE FROM RLT_ZP_DDO_MAP where ZP_DDO_CODE='"+ddocode+"'");
		
		strQuery.append("select zp.ZP_DDO_CODE,zp.REPT_DDO_CODE,zp.FINAL_DDO_CODE,zp.SPECIAL_DDO_CODE,org1.DDO_PERSONAL_NAME||'',");
		strQuery.append(" org2.DDO_PERSONAL_NAME||'',org3.DDO_PERSONAL_NAME||'',org4.DDO_PERSONAL_NAME||'' from RLT_ZP_DDO_MAP zp ");
		strQuery.append(" left OUTER join ORG_DDO_MST org1 on org1.DDO_CODE=zp.ZP_DDO_CODE");
		strQuery.append(" left OUTER join ORG_DDO_MST org2 on org2.DDO_CODE=zp.REPT_DDO_CODE");
		strQuery.append(" left OUTER join ORG_DDO_MST org3 on org3.DDO_CODE=zp.FINAL_DDO_CODE");
		strQuery.append(" left OUTER join ORG_DDO_MST org4 on org4.DDO_CODE=zp.SPECIAL_DDO_CODE where zp.ZP_DDO_CODE='"+ddocode+"'");
		
		
		logger.info("ddoInfo List Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		ddoInfo = query.list();	
		return ddoInfo;
		
}

	public String getOfficeName(String ddocode) {
		String officeName = null;
		List list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT OFF_NAME FROM mst_dcps_ddo_office  where DDO_CODE='"+ddocode+"'");
		logger.info("office name Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		list = query.list();
		officeName=list.toString();
		logger.info("ddo code: "+ddocode+" office name: "+officeName);
		return officeName;
}

	public String getHeadMasterName(String ddocode) {
		String headMastername = null;
		List list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT DDO_PERSONAL_NAME FROM org_ddo_mst where DDO_CODE='"+ddocode+"'");
		logger.info("head master name Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		list = query.list();
		headMastername=list.toString();
		logger.info("ddo code: "+ddocode+" headMaster name: "+headMastername);
		return headMastername;
}

	public String getMobileNo(String ddocode) {
		String mobileNo = null;
		List list = null;
		Session hibSession = getSession();
		StringBuffer strQuery = new StringBuffer();
		strQuery.append("SELECT TEL_NO2 FROM mst_dcps_ddo_office  where DDO_CODE='"+ddocode+"'");
		logger.info("office name Query: "+strQuery.toString());
		Query query = hibSession.createSQLQuery(strQuery.toString());
		list = query.list();
		mobileNo=list.toString();
		logger.info("ddo code: "+ddocode+" mobileNo: "+mobileNo);
		return mobileNo;
}
}