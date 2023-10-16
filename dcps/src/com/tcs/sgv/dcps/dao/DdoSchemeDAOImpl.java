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
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAOImpl;


/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 26, 2011
 */
public class DdoSchemeDAOImpl extends GenericDaoHibernateImpl implements DdoSchemeDAO {

	private Session ghibSession = null;
	private static final Logger gLogger = Logger.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);

	public DdoSchemeDAOImpl(Class type, SessionFactory sessionFactory) {

		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	public List getSchemeListForDDO(String lStrDDOCode,int flag, String lStrSchemeCode) {

		List lDcpsDdoSchemList = null;
		try {
			StringBuilder SBQuery = new StringBuilder();

			//SBQuery.append("select rlt.dcpsSchemeCode,mst.schemeName FROM RltDcpsDdoScheme rlt,MstScheme mst where mst.schemeCode=rlt.dcpsSchemeCode and rlt.dcpsDdoCode = :ddoCode order by rlt.dcpsSchemeCode,mst.schemeName ");
			/*SBQuery.append("select rlt.dcpsSchemeCode,mst.schemeName FROM RltDcpsDdoScheme rlt,MstScheme mst where mst.schemeCode=rlt.dcpsSchemeCode and rlt.dcpsDdoCode in(select ZP_DDO_CODE from ZpRltDdoMap where REPT_DDO_CODE= :ddoCode) order by rlt.dcpsSchemeCode,mst.schemeName ");
			Query lQuery = ghibSession.createQuery(SBQuery.toString());*/
			SBQuery.append("select rlt.Scheme_Code,mst.scheme_Name,rlt.sub_scheme_code FROM RLT_DCPS_DDO_SCHEMES rlt,Mst_Scheme mst where mst.scheme_Code=rlt.Scheme_Code and rlt.Ddo_Code ='"+lStrDDOCode+"'");
			if(flag==1)
			{
			        SBQuery.append(" and mst.demand_Code in ('T-09','T-10') and mst.scheme_Code='"+lStrSchemeCode+"'");
			}
		else
			{
			        SBQuery.append(" and mst.demand_Code not in  ('T-09','T-10') ");
			}
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			logger.info("Query.."+SBQuery.toString());
			//lQuery.setParameter("ddoCode", lStrDDOCode);
			lDcpsDdoSchemList = lQuery.list();

		} catch (Exception e) {
			logger.error("Error is :" + e, e);

		}
		return lDcpsDdoSchemList;
	}

	public List getSchemeNamesFromCode(String schemeCode, String lStrDdoCode, String asstDdoCode) {

		List resultList = null;

		try {
			StringBuilder SBQuery = new StringBuilder();

			//SBQuery.append("SELECT MS.scheme_code, MS.scheme_name FROM Mst_Scheme MS WHERE scheme_Code LIKE '" + schemeCode + "%' and scheme_Code not in(select scheme_code from RLT_DCPS_DDO_SCHEMES where DDO_CODE="+asstDdoCode+")");
			SBQuery.append("SELECT MS.scheme_code, MS.scheme_name FROM Mst_Scheme MS WHERE scheme_Code LIKE '" + schemeCode + "%' ");
			Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
			
			logger.info("Query is:::"+SBQuery.toString());

			resultList = lQuery.list();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
		return resultList;
	}

	public String getSchemeNameFromCode(String schemeCode) {

		String schemeName = null;

		try {
			StringBuilder SBQuery = new StringBuilder();

			SBQuery.append("select schemeName from MstScheme where schemeCode = :schemeCode");
			Query lQuery = ghibSession.createQuery(SBQuery.toString());
			lQuery.setParameter("schemeCode", schemeCode);

			schemeName = lQuery.list().get(0).toString();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error is :" + e, e);

		}
		return schemeName;
	}
	
	
	
	
	
	
	//Code started by saurabh  for addition of subScheme code
		public String getSubSchemeNameFromCode(String schemeCode) {

			String schemeName = null;

			try {
				StringBuilder SBQuery = new StringBuilder();

				SBQuery.append("SELECT DISCRIPTION FROM IFMS.SUBSCHEME_MASTER where SUBSCHEME_CD= :schemeCode");
				Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
				lQuery.setParameter("schemeCode", schemeCode);

				schemeName = lQuery.list().get(0).toString();

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error is :" + e, e);

			}
			return schemeName;
		}

		public List getSubSchemeNamesFromCode(String schemeCode) {
			List resultList = null;
			try {
				StringBuilder SBQuery = new StringBuilder();

				SBQuery.append(" SELECT SUBSCHEME_CD,DISCRIPTION FROM  SUBSCHEME_MASTER where SUBSCHEME_CD LIKE '"+schemeCode+"%'");
				Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
				resultList = lQuery.list();
				logger.error("getSubSchemeNamesFromCode"+lQuery.toString());
				logger.error("size of getSubSchemeNamesFromCode"+resultList.size());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error is :" + e, e);

			}
			return resultList;

		}
		public Long getDeptCode(String lStrDdoCode) {
			logger.info("hii i m in finding checkEmpCountForMore");
			Session session = getSession();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT DEPT_LOC_CODE FROM ORG_DDO_MST where DDO_CODE ='"+lStrDdoCode+"' ");
			
			Query query = session.createSQLQuery(sb.toString());
			logger.info("query is *************"+query.toString());
			String count = query.uniqueResult().toString();
			Long totalEmp = Long.valueOf(Long.parseLong(count));
			logger.info("totalEmp" + totalEmp);
			return totalEmp.longValue();

		}
		
		public int CheckSubSchemeExistOrNot(String strSchemeCode,
				String strSubSchemeCode) {
			Session hibSession = getSession();
			StringBuffer sb = new StringBuffer();
			int count=0;
			logger.error(" Inside CheckEmpInOffice");
			//sb.append("SELECT  count(1) FROM   SCHEME_SUBSCHEME where SCHEME_CD='"+strSchemeCode+"' and SUBSCHEME_CD='"+strSubSchemeCode+"'  ");
			sb.append(" SELECT  count(1) FROM   SCHEME_SUBSCHEME submst inner join MST_SCHEME mstsch ");
			sb.append(" on  mstsch.SCHEME_CODE=submst.SCHEME_CD "); 
			sb.append(" where submst.SCHEME_CD='"+strSchemeCode+"' and submst.SUBSCHEME_CD='"+strSubSchemeCode+"' ");
			sb.append(" and submst.DH_CD='36' and mstsch.DEMAND_CODE in ('T-09','T-10') ");
			Query sql1query=hibSession.createSQLQuery(sb.toString());
			//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
			logger.error("CheckSubSchemeExistOrNot"+sql1query.toString());
			logger.error("CheckSubSchemeExistOrNot.list()"+Integer.parseInt(sql1query.list().get(0).toString()));
			count = Integer.parseInt(sql1query.list().get(0).toString());
			return count;
		}
		
		public int CheckSubSchemeExist(String strSchemeCode) {
			Session hibSession = getSession();
			StringBuffer sb = new StringBuffer();
			int count=0;
			logger.error(" Inside CheckEmpInOffice");
			//sb.append("SELECT  count(1) FROM   SCHEME_SUBSCHEME where SCHEME_CD='"+strSchemeCode+"' ");
			sb.append(" SELECT  count(1) FROM   SCHEME_SUBSCHEME submst inner join MST_SCHEME mstsch ");
			sb.append("on  mstsch.SCHEME_CODE=submst.SCHEME_CD  ");
			sb.append(" WHERE submst.SCHEME_CD='"+strSchemeCode+"' and mstsch.DEMAND_CODE in ('T-09','T-10') AND submst.DH_CD='36' ");
		
			
			Query sql1query=hibSession.createSQLQuery(sb.toString());
			//logger.error("Query in getEmpLastMnthBroken is " + query.toString());
			logger.error("CheckSubSchemeExistOrNot"+sql1query.toString());
			logger.error("CheckSubSchemeExistOrNot.list()"+Integer.parseInt(sql1query.list().get(0).toString()));
			count = Integer.parseInt(sql1query.list().get(0).toString());
			return count;
		}
		public List getSchemeListForDDO1(String lStrDDOCode) {

			List lDcpsDdoSchemList = null;
			try {
				StringBuilder SBQuery = new StringBuilder();

				SBQuery.append("select rlt.dcpsSchemeCode,mst.schemeName, rlt.dcpsSubSchemeCode FROM RltDcpsDdoScheme rlt,MstScheme mst where mst.schemeCode=rlt.dcpsSchemeCode and rlt.dcpsDdoCode = :ddoCode order by rlt.dcpsSchemeCode,mst.schemeName ");
				Query lQuery = ghibSession.createQuery(SBQuery.toString());
				lQuery.setParameter("ddoCode", lStrDDOCode);
				lDcpsDdoSchemList = lQuery.list();
				logger.error("size of getSchemeListForDDO1"+lDcpsDdoSchemList);

			} catch (Exception e) {
				logger.error("Error is :" + e, e);

			}
			return lDcpsDdoSchemList;
		}
		
		public List getAllSchemesForDDO(String lStrDDOCode) {

			List lDcpsDdoSchemList = null;
			try {
				StringBuilder SBQuery = new StringBuilder();

				//SBQuery.append("select rlt.Scheme_Code,mst.scheme_Name,rlt.sub_scheme_code,rlt.Ddo_Code FROM RLT_DCPS_DDO_SCHEMES rlt,Mst_Scheme mst where mst.scheme_Code=rlt.Scheme_Code and rlt.Ddo_Code ='"+lStrDDOCode+"'");
				SBQuery.append("select rlt.Scheme_Code,mst.scheme_Name,rlt.sub_scheme_code,rlt.Ddo_Code FROM RLT_DCPS_DDO_SCHEMES rlt,Mst_Scheme mst where mst.scheme_Code=rlt.Scheme_Code and rlt.Ddo_Code in (select zp_ddo_code from rlt_zp_ddo_map where REPT_DDO_CODE="+lStrDDOCode+")");
				Query lQuery = ghibSession.createSQLQuery(SBQuery.toString());
				logger.info("Query.."+SBQuery.toString());
				//lQuery.setParameter("ddoCode", lStrDDOCode);
				lDcpsDdoSchemList = lQuery.list();

			} catch (Exception e) {
				logger.error("Error is :" + e, e);

			}
			return lDcpsDdoSchemList;
		}



		//Code ended by saurabh
		
	
	//added by roshan
	public long findUsertype(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select count(1) from rlt_Zp_ddo_map where rept_ddo_code="+ddoCode);

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String count = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(count));
		  logger.info("sameCount" + count);
		  return seqId.longValue();
	}
	
	public long findLevel(String ddoCode) {
		Session session = getSession();
		  StringBuffer sb = new StringBuffer();
		  logger.info("---- retrieve usertype------");

		  sb.append("select max(zplevel) from rlt_Zp_ddo_map where (Final_ddo_code="+ddoCode+") or (rept_ddo_code="+ddoCode+")");

		  logger.info("---- retrieve usertype---" + sb.toString());
		  Query query = session.createSQLQuery(sb.toString());

		  String level = query.uniqueResult().toString();
		  Long seqId = Long.valueOf(Long.parseLong(level));
		  logger.info("sameCount" + level);
		  return seqId.longValue();



	}


}
