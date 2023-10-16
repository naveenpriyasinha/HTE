package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisGISDtls;

@SuppressWarnings("unchecked")
public class ChangeGISDetailsDAOImpl extends GenericDaoHibernateImpl<HrEisGISDtls, Long> implements ChangeGISDetailsDAO{

	//private ResourceBundle constantBundle = ResourceBundle.getBundle("../resources/Payroll");
	
	//private String payBandParentLookupId = constantBundle.getString("payBandParentLookupId");
	
	
	public ChangeGISDetailsDAOImpl(Class<HrEisGISDtls> type, SessionFactory sessionFactory)
    {
        super(type);
        setSessionFactory(sessionFactory);
    }
 	
	
	public List getGISDeducData(long empId){
 		
		List GISDeducDataList = null;
		Session session = getSession();
		
		StringBuffer strQuery = new StringBuffer();
		logger.info("empId:::::::::::::::: "+ empId);
		
 		//strQuery.append("FROM HrPayDeducTypeMst hdm WHERE hdm.deducCode in (82,83,84,85,86,101,114) ");
		//SELECT * FROM hr_pay_deduc_type_mst where DEDUC_CODE in (82,83,84,85,86,101)
		strQuery.append("select distinct hec.compId FROM HrEisEmpCompMpg hec where hec.compId in (82, 83, 84, 85, 86, 101,114) and hec.hrEisEmpCompGrpMst.EmpCompGrpId in " +
				"(select heg.EmpCompGrpId from HrEisEmpCompGrpMst heg where heg.hrEisEmpMst.orgEmpMst.empId = " + empId+")");
 		/*SELECT DISTINCT COMPO_ID FROM hr_eis_emp_component_mpg g where g.COMPO_ID in (82, 83, 84, 85, 86, 101) and g.COMPO_GROUP_ID in(SELECT j.EMP_COMPO_GRP_ID 
		FROM hr_eis_emp_component_grp_mst j where j.emp_id = 301406) */
 		
 		Query query = session.createQuery(strQuery.toString());
 		logger.info("query:::::::::::::::::: " + query);
 		GISDeducDataList = query.list(); 
 		logger.info("GISDeducDataList:::::::::::::::::::::::"+GISDeducDataList.size());

 		return GISDeducDataList;
		
 	}
 	
 	public List getGISGradeData(long langId){
 		
		List GISGradeDataList = null;
		Session session = getSession();
		
		StringBuffer strQuery = new StringBuffer();
		
 		strQuery.append("FROM OrgGradeMst ogm WHERE ogm.cmnLanguageMst.langId  = " + langId);
 		//SELECT * FROM hr_pay_deduc_type_mst where DEDUC_CODE in (82,83,84,85,86,101)
 		Query query = session.createQuery(strQuery.toString());
 		GISGradeDataList = query.list(); 
 		logger.info("GISGradeDataList:::::::::::::::::::::::"+GISGradeDataList.size());

 		return GISGradeDataList;
		
 	}
 	
 	public List getGISOrderData(long locId){
 		
		List GISOrderDataList = null;
		Session session = getSession();
		
		StringBuffer strQuery = new StringBuffer();
		
 		strQuery.append("FROM HrPayOrderMst hpm where hpm.locationCode='");
 		strQuery.append(locId);
 		strQuery.append("'");
 		//SELECT * FROM hr_pay_deduc_type_mst where DEDUC_CODE in (82,83,84,85,86,101)
 		Query query = session.createQuery(strQuery.toString());
 		GISOrderDataList = query.list(); 
 		logger.info("GISOrderDataList:::::::::::::::::::::::"+GISOrderDataList.size());

 		return GISOrderDataList;
		
 	}
 	
 	public List getEmpGISData(long empId){
 		
		List GISempData = new ArrayList();
		Session session = getSession();
		
		StringBuffer strQuery = new StringBuffer();
		
 		//strQuery.append("FROM HrEisGISDtls hgd where hgd.hrEisEmpMst.orgEmpMst.empId = " +empId);
		
		strQuery.append("select concat(e.empFname,' ',e.empMname,' ',e.empLname) ,e.empId from OrgEmpMst e where e.empId = " +empId);
 		//SELECT * FROM hr_pay_deduc_type_mst where DEDUC_CODE in (82,83,84,85,86,101)
 		Query query = session.createQuery(strQuery.toString());
 		GISempData = query.list(); 
 		logger.info("GISOrderDataList:::::::::::::::::::::::"+GISempData.size());

 		return GISempData;
		
 	}
 	
public List getEmpData(long empId){
 		
		List empData = new ArrayList();
		Session session = getSession();
		
		StringBuffer strQuery = new StringBuffer();
		
 		//strQuery.append("FROM HrEisGISDtls hgd where hgd.hrEisEmpMst.orgEmpMst.empId = " +empId);
		
		strQuery.append("from HrEisGISDtls hem where hem.hrEisEmpMst.orgEmpMst.empId = " +empId);
 		//SELECT * FROM hr_pay_deduc_type_mst where DEDUC_CODE in (82,83,84,85,86,101)
 		Query query = session.createQuery(strQuery.toString());
 		empData = query.list(); 
 		logger.info("empData:::::::::::::::::::::::"+empData.size());

 		return empData;
		
 	}
 	


public List getGISOrderDate(long orderId) {

	Session hibSession =getSession();
	String query=" select a.orderDate from  HrPayOrderMst a where order_id= "+orderId;
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
  }

public List getGISDataFromDCPS(long dcpsEmpId) {

	Session hibSession =getSession();
	String query=" select a.gisApplicable, a.gisGroup, a.membershipDate, b.orgEmpMstId from RltDcpsPayrollEmp a , " +
			"MstEmp b where a.dcpsEmpId=b.dcpsEmpId and b.dcpsEmpId = "+dcpsEmpId;
	Query sqlQuery = hibSession.createQuery(query.toString());
	logger.info("getGISDataFromDCPS::::::::::::::::::::::::: "+sqlQuery.list());
	if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
	{
		return  sqlQuery.list();
	}
	else
	{
		return null;
	}
  }

public List getEmpDOJ(long empId){
		
	List empDOJ = new ArrayList();
	Session session = getSession();
	
	StringBuffer strQuery = new StringBuffer();
	
		strQuery.append("Select e.empDoj from OrgEmpMst e where e.empId = " +empId);
		//SELECT * FROM hr_pay_deduc_type_mst where DEDUC_CODE in (82,83,84,85,86,101)
		Query sqlQuery = session.createQuery(strQuery.toString());
		logger.info("--khshual Date Select----"+sqlQuery.list());
		if(sqlQuery.list()!=null && sqlQuery.list().size()>0)
		{
			empDOJ = sqlQuery.list();
			logger.info("GISOrderDataList:::::::::::::::::::::::"+empDOJ.size());

			return  empDOJ;
		}
		else
		{
			return null;
		}
	}

}
