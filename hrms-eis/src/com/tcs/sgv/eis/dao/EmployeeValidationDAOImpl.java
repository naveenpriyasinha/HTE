package com.tcs.sgv.eis.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class EmployeeValidationDAOImpl extends GenericDaoHibernateImpl implements EmployeeValidationDAO {

	private static final String All = null;
	Logger logger = Logger.getLogger(EmployeeValidationDAOImpl.class);
	Session hibSession = null;

	public EmployeeValidationDAOImpl(Class<EmployeeValidationDAOImpl> class1, SessionFactory sessionFactory) {
		super(class1);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	@Override
	public List getTotalEmpConfig() {

		List totalEmpConf = null;
		StringBuffer str = new StringBuffer();
		str.append(
				"select temp.district,dist.district_name, zp.admin_code, zp.admin_name, temp.off_name, count(emp.ddo_code) as Total_Emp_Configured, temp.ddo_code from ");
		str.append(" (select distinct ddo_code, district, off_name from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(
				" where emp.reg_status in(-1,0,1,2) and emp.form_status in (0,1,-1) and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(
				" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name, temp.off_name, temp.ddo_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getTotalEmpConfig DAO------" + str.toString());
		Query query = hibSession.createSQLQuery(str.toString());

		if ((query.list() != null)) {
			totalEmpConf = query.list();
		}

		logger.info("totalEmpConf size: " + totalEmpConf.size());
		return totalEmpConf;
	}

	@Override
	public List getDraftForms() {
		List saveAsDraft = null;
		StringBuffer str = new StringBuffer();
		str.append("select temp.district, zp.admin_code, count(emp.ddo_code) as Total_Emp_Draft, temp.ddo_code from ");
		str.append(" (select distinct ddo_code, district, off_name from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(
				" where emp.reg_status=0 and emp.form_status=0 and dist.lang_id=1 and emp.created_date>'2012-08-15'");
		str.append(" group by temp.district, zp.admin_code, temp.ddo_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getDraftForms DAO------" + str.toString());
		Query query = hibSession.createSQLQuery(str.toString());

		if ((query.list() != null)) {
			saveAsDraft = query.list();
		}

		logger.info("saveAsDraft size: " + saveAsDraft.size());
		return saveAsDraft;
	}

	@Override
	public List getForwardedForms() {
		List forwardForms = null;
		StringBuffer str = new StringBuffer();
		str.append("select temp.district,zp.admin_code,count(emp.ddo_code) as Total_Emp_Approved, temp.ddo_code from ");
		str.append(
				" (select distinct ddo_code, district, off_name, loc_id from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(
				" where emp.reg_status=0 and emp.form_status=1 and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(
				" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name, temp.off_name, temp.ddo_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getForwardedForms DAO------" + str.toString());
		Query query = hibSession.createSQLQuery(str.toString());

		if ((query.list() != null)) {
			forwardForms = query.list();
		}

		logger.info("forwardForms size: " + forwardForms.size());
		return forwardForms;
	}

	@Override
	public List getApprovedForms() {
		List approvedForms = null;
		StringBuffer str = new StringBuffer();
		str.append("select temp.district,zp.admin_code,count(emp.ddo_code) as Total_Emp_Approved, temp.ddo_code from ");
		str.append(
				" (select distinct ddo_code, district, off_name, loc_id from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(
				" where emp.reg_status>0 and emp.form_status=1 and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(
				" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name, temp.off_name, temp.ddo_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getApprovedForms DAO------" + str.toString());
		Query query = hibSession.createSQLQuery(str.toString());

		if ((query.list() != null)) {
			approvedForms = query.list();
		}

		logger.info("getApprovedForms size: " + approvedForms.size());
		return approvedForms;
	}

	public List getRejectedForms() {
		List rejectedForms = null;
		StringBuffer str = new StringBuffer();
		str.append("select temp.district,zp.admin_code,count(emp.ddo_code) as Total_Emp_Rejected, temp.ddo_code from ");
		str.append(
				" (select distinct ddo_code, district, off_name, loc_id from MST_DCPS_DDO_OFFICE where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join CMN_DISTRICT_MST dist on temp.district = dist.district_id ");
		str.append(" left outer join mst_dcps_emp emp on temp.ddo_code=emp.ddo_code ");
		str.append(" where emp.reg_status=-1 and dist.lang_id=1 and emp.created_date>'2012-08-15' ");
		str.append(
				" group by temp.district, dist.district_name, zp.admin_code, zp.admin_name, temp.off_name, temp.ddo_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getRejectedForms DAO------" + str.toString());
		Query query = hibSession.createSQLQuery(str.toString());

		if ((query.list() != null)) {
			rejectedForms = query.list();
		}

		logger.info("getRejectedForms size: " + rejectedForms.size());
		return rejectedForms;
	}

}
