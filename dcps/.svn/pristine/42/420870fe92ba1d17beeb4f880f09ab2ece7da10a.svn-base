package com.tcs.sgv.dcps.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmpDetails;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;
import com.tcs.sgv.ess.dao.UserPostDao;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;



public class ChangeDetailsDaoImpl extends GenericDaoHibernateImpl implements ChangeDetailsDao{

		Logger logger = Logger.getLogger(ChangeDetailsDaoImpl.class );
		Session hibSession=null;
		public ChangeDetailsDaoImpl(Class<ChangeDetailsDaoImpl> class1,
				SessionFactory sessionFactory) {	
			super(class1);
			hibSession = sessionFactory.getCurrentSession();
			setSessionFactory(sessionFactory);
		}
		@Override
		public List getApprovedFormsForDDO(String strDdoCode,String status) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			List<MstEmp> EmpList = null;
			Session session = getSession();
			logger.info("hi i Finding Approve  Employee");
			lSBQuery.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.sevarth_id,EM.DOB,EM.gender,em.dcps_Or_Gpf,em.SENTBACK_REMARKS,em.change_status");
			lSBQuery.append(" FROM Mst_dcps_emp_Details EM,Rlt_zp_Ddo_Map ZP,mst_dcps_emp EMP ");
			lSBQuery.append(" WHERE EM.ddo_Code = ZP.ZP_DDO_CODE AND EMP.DCPS_EMP_ID=em.dcps_emp_id and emp.ddo_code is not null and (emp.emp_servend_dt is null or emp.emp_servend_dt>sysdate) and EM.Change_status in ("+status+") and EM.zp_Status =10");
			lSBQuery.append(" AND (ZP.zp_DDO_code ='"+strDdoCode+"' or ZP.rept_DDO_code ='"+strDdoCode+"' or ZP.final_DDO_code ='"+strDdoCode+"' ) order by em.emp_name");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			EmpList = lQuery.list();
			logger.info("query is"+lQuery.toString());
			return EmpList;
		}
		@Override
		public List<MstEmpNmn> getNomineesFromBackup(String strEmpId) {
			getSession();
			StringBuilder lSBQuery = new StringBuilder();
			Session session = getSession();
			List<MstEmpNmn> NomineesList = null;
			lSBQuery.append(" FROM MstEmpNmnDetails WHERE dcpsEmpId.dcpsEmpId = :empId");
			Query lQuery = session.createQuery(lSBQuery.toString());
			lQuery.setParameter("empId", Long.parseLong(strEmpId));
			NomineesList = lQuery.list();
			return NomineesList;
		
		}
			
		public Long getOtherId(Long orgEmpMstId) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			String otherId=null;
			Session session = getSession();
			logger.info("for finding the other Id from HR_EIS_OTHER_DETAILS");
			lSBQuery.append(" Select other_id from hr_eis_other_dtls where emp_id=(select emp_id from hr_eis_emp_mst where emp_mpg_id="+orgEmpMstId+")");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			otherId = lQuery.uniqueResult().toString();
			
			return Long.parseLong(otherId);
		}
		@Override
		public void insertOrgUserpostRltForChanges(
				OrgUserpostRlt orgUserpostRlt, RltDcpsPayrollEmp empData,
				MstEmp objDcpsEmpMst) {
			
			
		}

		@Override
		public List<MstPayrollDesignationMst> getMstDcpsDsgnObject(
				long parentLocId, long dsgnId) {
			  Session session = getSession();
	          String HQL_QUERY= "select distinct mst from MstPayrollDesignationMst mst where mst.fieldDeptId="+parentLocId+ " and mst.orgDesignationId="+dsgnId;
	          
	          Query query = session.createQuery(HQL_QUERY);
	          logger.info("The Query getMstDcpsDsgnObject String is:-"+query.toString());
	          List<MstPayrollDesignationMst> resultList=query.list();
	          return resultList;
		}
		@Override
		public Long getPostId(String ddoCode) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			String postId=null;
			Session session = getSession();
			logger.info("for finding the Post Id from ddo_code");
			lSBQuery.append(" select post_id from org_ddo_mst where ddo_code='"+ddoCode+"'");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			postId = lQuery.uniqueResult().toString();
			return Long.parseLong(postId);
		}
		@Override
		public void updateGisData(long empGISId, Date orderDate,long gisGroupRevId, Date revMemshipdate, String ordername,
				long gisRevApplicableId, String remarks, long userId,Long postIdOfAsstDDO) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			String postId=null;
			Session session = getSession();
			logger.info("for updateing GIS data");
			lSBQuery.append(" Update hr_eis_gis_dtls set  ");
			if(orderDate!=null){
				lSBQuery.append(" GIS_ORDER_DATE='"+orderDate+"' , ");
			}
			if(gisGroupRevId!=0){
				lSBQuery.append(" GIS_Group_Grade_Id= ( select grade_id from org_grade_mst where GRADE_GROUP_ID="+gisGroupRevId+" ), ");
			}
			if(revMemshipdate!=null){
				lSBQuery.append("MEMBERSHIP_DATE='"+revMemshipdate+"'  , ");
			}
			if(ordername!=null && !ordername.equals("")){
				lSBQuery.append("GIS_Order_Id=1234   , ");
			}
			if(gisRevApplicableId==101 || gisRevApplicableId==86){
				lSBQuery.append("GIS_DEDUC_TYPE_ID= "+gisRevApplicableId+" , ");//--------Need to add.
			}
			if(remarks!=null){
				lSBQuery.append("REMARKS='"+remarks+"'  , ");
			}
	
			lSBQuery.append(" UPDATED_BY_USER="+userId+",UPDATED_BY_POST="+postIdOfAsstDDO+", UPDATED_DATE=sysdate where emp_id="+empGISId+" ");
			
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			lQuery.executeUpdate();

		}
		public List searchEmps(String lStrSevarthId, String lStrName,
				String lStrDdoCode,String changeStatus) {

			StringBuilder lSBQuery = new StringBuilder();
			List EmployeeList = null;
			Session session = getSession();
			
			Date lDtCurrDate = SessionHelper.getCurDate();

			lStrSevarthId = lStrSevarthId.toUpperCase().trim();
			lStrName = lStrName.toUpperCase().trim();

			lSBQuery.append(" SELECT EM.dcps_emp_Id, EM.emp_name,EM.dcps_Id, EM.gender, EM.dob, nvl(DO.off_name,''),nvl(OD.DSGN_NAME,''),EM.PFD_CHANGED_BY_SRKA,EM.sevarth_Id,EM.SENTBACK_REMARKS,EM.Change_status ");
			lSBQuery.append(" from mst_dcps_Emp_Details EM ");
			lSBQuery.append(" left join mst_dcps_ddo_office DO on DO.DCPS_DDO_OFFICE_MST_ID = EM.CURR_OFF ");
			lSBQuery.append(" left join org_designation_mst OD on OD.DSGN_ID = EM.DESIGNATION");
			lSBQuery.append(" inner join rlt_zp_ddo_map rlt on rlt.zp_ddo_code = EM.ddo_code");
			lSBQuery.append(" where EM.Change_STATUS in (" + changeStatus + ")");
			lSBQuery.append(" and ((rlt.zp_DDO_CODE  = '" + lStrDdoCode + "') or (rlt.rept_DDO_CODE  = '" + lStrDdoCode + "'))");
			
			
			if (lStrSevarthId != null && !"".equals(lStrSevarthId)) {
				
				lSBQuery.append(" and UPPER(SEVARTH_ID) = '" + lStrSevarthId + "'");
			}
			if (lStrName != null && !"".equals(lStrName)) {
				
				lSBQuery.append(" and UPPER(EM.emp_name) = '" + lStrName + "'");
			}
			
			lSBQuery.append(" and ( EM.EMP_SERVEND_DT is null or EM.EMP_SERVEND_DT  >= :currentDate ) ");

			Query lQuery = session.createSQLQuery(lSBQuery.toString());
			lQuery.setDate("currentDate", lDtCurrDate);
			
			logger.info("hii i m roshan"+lSBQuery.toString());
			EmployeeList = lQuery.list();

			return EmployeeList;
		}
		@Override
		public void updateGrade(String cadreTypeId, long empId,long userId) {
			StringBuilder lSBQuery1 = new StringBuilder();
			Query lQuery1 = null;
			Session session = getSession();
			logger.info("for updating ");
			lSBQuery1.append(" update org_emp_mst set GRADE_ID=(select LOOKUP_SHORT_NAME from CMN_LOOKUP_MST where LOOKUP_ID=(select GROUP_id from mst_dcps_cadre where cadre_id="+cadreTypeId+")) where emp_id="+empId+" ");
			lQuery1 = session.createSQLQuery(lSBQuery1.toString());
			logger.info("query is"+lQuery1.toString());
			lQuery1.executeUpdate();
			
			StringBuilder lSBQuery2 = new StringBuilder();
			Query lQuery2 = null;
			logger.info("for updateing GIS data");
			lSBQuery2.append(" update HR_PAY_GPF_DETAILS set GRADE_ID=(select LOOKUP_SHORT_NAME from CMN_LOOKUP_MST where LOOKUP_ID=(select GROUP_id from mst_dcps_cadre where cadre_id="+cadreTypeId+")) where user_id="+userId+" ");
			lQuery2 = session.createSQLQuery(lSBQuery2.toString());
			logger.info("query is"+lQuery2.toString());
			lQuery2.executeUpdate();
			
		}

		public RltDcpsPayrollEmpDetails getPayrollVOForEmpId(Long dcpsEmpId) {

			StringBuilder lSBQuery = new StringBuilder();
			RltDcpsPayrollEmpDetails lObjPayrollVO = null;
			Query lQuery = null;

			lSBQuery.append("FROM RltDcpsPayrollEmpDetails");
			lSBQuery.append(" WHERE dcpsEmpId = :dcpsEmpId ");
			Session session = getSession();
			lQuery = session.createQuery(lSBQuery.toString());
			lQuery.setParameter("dcpsEmpId", dcpsEmpId);

			lObjPayrollVO = (RltDcpsPayrollEmpDetails) lQuery.uniqueResult();

			return lObjPayrollVO;
		}
		@Override
		public void updateChangeStaus(Long dcpsEmpId) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			String postId=null;
			Session session = getSession();
			logger.info("for updateing GIS data");
			lSBQuery.append(" Update mst_dcps_emp_details set change_status=-1 where dcps_emp_id="+dcpsEmpId+"  ");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			lQuery.executeUpdate();
		}
		public void upDateGpfAcNo(String gpfNo, long userId,String pfDesc) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			String postId=null;
			Session session = getSession();
			logger.info("for updateing gpf account number data");
			lSBQuery.append(" Update hr_pay_gpf_details set GPF_ACC_NO=:pfNo,PF_SERIES=:pfSeries where user_id="+userId+"  ");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			lQuery.setParameter("pfNo", gpfNo);
			lQuery.setParameter("pfSeries", pfDesc);
			logger.info("query is"+lQuery.toString());
			lQuery.executeUpdate();
			try {
				session.connection().commit();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public List getApprovedFormsForDDO(String ddoCode) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			List<MstEmp> EmpList = null;
			Session session = getSession();
			logger.info("hi i Finding Approved  Employee");
			lSBQuery.append(" Select EM.dcps_Emp_Id,EM.EMP_NAME,EM.sevarth_id,EM.DOB,EM.gender,EM.EMP_NAME ");
			lSBQuery.append(" FROM Mst_dcps_emp EM");
			lSBQuery.append(" WHERE EM.reg_status in (1,2)");
			lSBQuery.append(" order by em.emp_name");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			EmpList = lQuery.list();
			logger.info("query is"+lQuery.toString());
			return EmpList;
		}

	
		public String getBillGrpId(long parseLong) {
			StringBuilder lSBQuery = new StringBuilder();
			Query lQuery = null;
			String billGrpId=null;
			Session session = getSession();
			lSBQuery.append(" select bill_no from hr_pay_post_psr_mpg where post_id='"+parseLong+"'");
			lQuery = session.createSQLQuery(lSBQuery.toString());
			logger.info("query is"+lQuery.toString());
			
			
			if(lQuery.list()!=null && lQuery.list().size()>0 && lQuery.list().get(0)!=null)
			{
				billGrpId = lQuery.uniqueResult().toString();
			}
			return billGrpId;
		}
}