package com.tcs.sgv.eis.zp.zpDistrictOffice.dao;

import com.tcs.sgv.common.valueobject.CmnDistrictMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class ZpDistrictOfficeDAOImpl extends GenericDaoHibernateImpl
implements ZpDistrictOfficeDAO
{
	public ZpDistrictOfficeDAOImpl(Class type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getAdminOffice() {
		List adminOfficelst = null;
		this.logger.info("adminOfficelst called");
		try
		{
			Session hibSession = getSession();
			Criteria objCrt = hibSession.createCriteria(ZpAdminOfficeMst.class);
			adminOfficelst = objCrt.list();
		}
		catch (Exception e)
		{
			this.logger.error("error: ", e);
		}
		return adminOfficelst;
	}

	public List<ZpAdminOfficeMst> getAdminOfficePK(String adminofficeCode) {
		List adminOfficelst = new ArrayList();
		this.logger.info("adminOfficelst called:::");
		try
		{
			Session hibSession = getSession();
			Criteria objCrt = hibSession.createCriteria(ZpAdminOfficeMst.class);
			objCrt.add(Restrictions.eq("officeCode", (adminofficeCode)));
			adminOfficelst = objCrt.list();
		}
		catch (Exception e)
		{
			this.logger.error("error: ", e);
		}
		return adminOfficelst; }

	public List getAllDistrictOfficeDtlsData(long langId) {
		Criteria objCrt = null;
		List list = null;

		Session hibSession = getSession();
		String strQuery = "from com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst where langId =" + langId + 
		" order by distMstOfficeName";
		Query query = hibSession.createQuery(strQuery);
		list = query.list();

		return list;
	}

	public ZpDistrictOfficeMst getDistOfficeDtls(long distId) {
		ZpDistrictOfficeMst ZpDistrictOfficeDtls = new ZpDistrictOfficeMst();

		Session hibSession = getSession();
		String query1 = "from com.tcs.sgv.eis.zp.zpDistrictOffice.valueobject.ZpDistrictOfficeMst where distId =" + distId;
		Query sqlQuery1 = hibSession.createQuery(query1);
		ZpDistrictOfficeDtls = (ZpDistrictOfficeMst)sqlQuery1.uniqueResult();

		return ZpDistrictOfficeDtls;
	}

	public List<CmnDistrictMst> getDistrict(long stateId)
	{
		List temp = null;
		try {
			Session hibSession = getSession();
			String branchQuery = "SELECT DISTRICT_ID,DISTRICT_NAME FROM CMN_DISTRICT_MST where STATE_ID = " + stateId + " and LANG_ID=1 and ACTIVATE_FLAG=1";
			Query sqlQuery = hibSession.createSQLQuery(branchQuery);
			this.logger.error("---------------------getDistrict Size" + sqlQuery.toString());
			temp = sqlQuery.list();
			this.logger.error("---------------------getDistrict Size" + temp.size());
		}
		catch (Exception e)
		{
			this.logger.error("Error in ReportingDDODaoImpl \n " + e);
			e.printStackTrace();
		}
		return temp;
	}
	//added by samdahan
	@Override
	public Long checkDistOfficeCount(String distCode, String adminOfficeCode) 
	{

		Session hibSession = getSession();
		
		Long distOfficeCount=null;
		StringBuffer sb= new StringBuffer();
		logger.info("distCode: "+distCode);
		logger.info("adminOfficeCode: "+adminOfficeCode);
		sb.append(" SELECT count(1) FROM ZP_ADMIN_OFFICE_DISTRICT_MPG WHERE  ");
		sb.append(" DIST_CODE = '"+distCode+"' and ADMIN_OFFICE_CODE = '"+adminOfficeCode+"' ");
		
		logger.info("Query to get dist office count:  " + sb.toString());
		Query sqlQuery1 = hibSession.createSQLQuery(sb.toString());
		distOfficeCount = Long.parseLong(sqlQuery1.uniqueResult().toString());
		
		return distOfficeCount;
	}
}