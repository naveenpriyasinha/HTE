package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisBankMst;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrPayEmpallowMpg;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;

public class BulkAllowanceDAOImpl extends GenericDaoHibernateImpl<HrPayEmpallowMpg, Long>
implements BulkAllowanceDAO {

	public BulkAllowanceDAOImpl(Class<HrPayEmpallowMpg> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}



	// added by manish for bulk allowances

	public List getDesignationList(long langId, long locId) {
		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer(
		"from OrgDesignationMst as orgDegnMst where orgDegnMst.cmnLanguageMst.langId=");
		query1.append(langId);
		query1
		.append(" and dsgnId in (select pd.orgDesignationMst.dsgnId from OrgPostDetailsRlt as pd where pd.cmnLocationMst.locId=");
		query1.append(locId);
		query1.append(")");
		logger.info("Query for Designation in Bulk Allowances "
				+ query1.toString());
		Query sqlQuery1 = hibSession.createQuery(query1.toString());
		List<OrgDesignationMst> dsgnList = sqlQuery1.list();
		return dsgnList;
	}

	/*
	 * public List getPComponentCombo() { HrEisBankMst hrEisBankMst = null;
	 * Session hibSession = getSession(); String query1 =
	 * "from OrgDesignationMst as orgDegnMst where orgDegnMst.cmnLanguageMst.langId="
	 * +langId; Query sqlQuery1 = hibSession.createQuery(query1);
	 * List<OrgDesignationMst> dsgnList = sqlQuery1.list();
	 * 
	 * return new ArrayList(); }
	 */
	public List getPayItemComboForAllowances(long payCommissionId, long locId) {
		List allowanceLst = new ArrayList();
		Session hibSession = getSession();
		/*
		 * String queryStr =" from HrPayAllowTypeMst as hrEmpAllMap " +
		 * " where hrEmpAllMap.payCommissionId in ("+ payCommissionId +
		 * ",-1) and hrEmpAllMap.allowCode in (" +
		 * "select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and "
		 * + "deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
		 * " and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)"
		 * + " order by hrEmpAllMap.sequence_no ";
		 */

		String queryStr = "from HrPayAllowTypeMst as mst where mst.allowCode not in (select expMst.hrPayAllowTypeMst.allowCode from HrPayExpressionMst as expMst) order by mst.allowDisplayName";
		Query query = hibSession.createQuery(queryStr);

		logger.info("check this query in Bulk Allowances::::::::::::::"+ queryStr);

		allowanceLst = query.list();
		logger.info("size in daoimpl is " + allowanceLst.size());
		return allowanceLst;
	}

	public List getPayItemComboForDeductions(long payCommissionId, long locId) {
		List allowanceLst = new ArrayList();
		Session hibSession = getSession();
		/*
		 * String queryStr =" from HrPayAllowTypeMst as hrEmpAllMap " +
		 * " where hrEmpAllMap.payCommissionId in ("+ payCommissionId +
		 * ",-1) and hrEmpAllMap.allowCode in (" +
		 * "select deptCompo.compId from HrPayLocComMpg as deptCompo,HrEisEmpCompMpg as empCompo where deptCompo.hrpaycompgrpmst.isactive=1 and  deptCompo.cmnLookupMst.lookupId=2500134 and "
		 * + "deptCompo.hrpaycompgrpmst.cmnLocationMst.locId=" + locId +
		 * " and isactive=1 and deptCompo.hrpaycompgrpmst.compoGrpId = empCompo.hrEisEmpCompGrpMst.hrPayCompGrpMst.compoGrpId)"
		 * + " order by hrEmpAllMap.sequence_no ";
		 */

		String queryStr = "from HrPayDeducTypeMst as mst where mst.deducCode not in (select expMst.hrPayDeducTypeMst.deducCode from HrDeducExpMst as expMst) order by mst.deducDisplayName";
		Query query = hibSession.createQuery(queryStr);


		allowanceLst = query.list();
		logger.info("size in daoimpl is "+allowanceLst.size());
		return allowanceLst;
	}

	public List getEmployeeListFromDsgn(long dsgn_id, long langId, long locId,
			long compoType, long compoId,long billGroupid) {
		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer();
		query1
		.append("select distinct emst.emp_mpg_id,mst.EMP_FNAME||' '|| mst.EMP_LNAME as name, emst.emp_id  ");
		if (compoType == 2500134)
			query1.append(" ,ALLWMST.ALLOW_CODE, empAllow.emp_allow_amount ");
		else
			query1.append(" ,DEDUCMST.DEDUC_CODE, DEDDTLS.emp_deduc_amount ");
		
		query1.append(" from ORG_EMP_MST mst,"
				+ " ORG_POST_DETAILS_RLT  pd,ORG_POST_MST pm,ORG_USERPOST_RLT up,ORG_DESIGNATION_MST dsm ");

		if (billGroupid>0) 
		{
			query1
			.append(",MST_DCPS_BILL_GROUP bill,HR_PAY_POST_PSR_MPG psr ");
		}

		if (compoType == 2500134) {
			query1
			.append(" ,HR_EIS_EMP_MST EMST ")
			.append(" inner join HR_EIS_EMP_COMPONENT_GRP_MST empGrpMst  on EMPGRPMST.EMP_ID = EMST.EMP_ID ")
			.append(" inner join HR_EIS_EMP_COMPONENT_MPG EMPCOMP on EMPCOMP.COMPO_GROUP_ID=EMPGRPMST.EMP_COMPO_GRP_ID ")
			.append(" inner join HR_PAY_ALLOW_TYPE_MST ALLWMST on empComp.compo_id      =allwMst.allow_code ")
			.append(" left outer join HR_PAY_EMPALLOW_MPG EMPALLOW on EMPALLOW.EMP_ALLOW_ID = ALLWMST.ALLOW_CODE ") 
			.append(" and EMPALLOW.EMP_ID = EMST.EMP_ID and EMPALLOW.month=-1 and EMPALLOW.year=-1 ")
			.append(" where empGrpMst.is_active=1 and empComp.is_active=1 and empComp.compo_type=2500134 ")
			.append(" and  empComp.compo_id=").append(compoId)
			.append(" and allwMst.allow_code=").append(compoId);
			
		}

		else {
			query1
			.append(" ,HR_EIS_EMP_MST EMST ")
			.append(" inner join HR_EIS_EMP_COMPONENT_GRP_MST empGrpMst  on EMPGRPMST.EMP_ID = EMST.EMP_ID ")
			.append(" inner join HR_EIS_EMP_COMPONENT_MPG EMPCOMP on EMPCOMP.COMPO_GROUP_ID=EMPGRPMST.EMP_COMPO_GRP_ID ")
			.append(" inner join HR_PAY_DEDUC_TYPE_MST DEDUCMST on EMPCOMP.COMPO_ID= DEDUCMST.DEDUC_CODE ")
			.append(" left outer join HR_PAY_DEDUCTION_DTLS DEDDTLS on DEDDTLS.EMP_DEDUC_ID = DEDUCMST.DEDUC_CODE ")
			.append(" and DEDDTLS.emp_id = EMST.EMP_ID and DEDDTLS.month=-1 and DEDDTLS.year = -1 ")
			.append(" where empGrpMst.is_active=1 and empComp.is_active=1 and empComp.compo_type=2500135 ")
			.append(" and  empComp.compo_id=").append(compoId)
			.append(" and DEDUCMST.deduc_code=").append(compoId);
		}

		query1
		.append(" and  emst.EMP_MPG_ID=mst.EMP_ID and mst.USER_ID=up.USER_ID and pm.POST_ID = up.POST_ID and pd.POST_ID=pm.POST_ID and pd.LOC_ID ="+ locId);
		
		if(dsgn_id != 1)
		{
			query1.append(" and dsm.DSGN_ID=pd.DSGN_ID ");
			query1.append(" and dsm.DSGN_ID ="+ dsgn_id);

		}
		query1.append(" and up.activate_flag = 1 and  dsm.LANG_ID=" + langId);
		if (billGroupid>0) 
		{
			query1
			.append(" and bill.BILL_GROUP_ID=psr.BILL_NO and psr.POST_ID=pd.POST_ID and psr.LOC_ID="+locId+" and bill.BILL_GROUP_ID="+billGroupid+" ");
		}

		logger.info("Query is ++++++++++" + query1.toString());
		Query sqlQuery1 = hibSession.createSQLQuery(query1.toString());
		List dsgnList = sqlQuery1.list();
		logger.info("Employee List size is  " + dsgnList.size());
		return dsgnList;
	}

	public List getAllw(long empId, long compoId, int month, int year) {
		Session hibSession = getSession();
		String query1 = "from HrPayEmpallowMpg as mpg where mpg.hrPayAllowTypeMst.allowCode="
			+ compoId
			+ " and mpg.hrEisEmpMst.orgEmpMst.empId="
			+ empId
			+ " and  mpg.month =" + month + "  and mpg.year=" + year;


		/*String queryStr ="select mpg from HrPayEmpallowMpg as mpg " +
  		" where  mpg.allowCode in (" +
  		//"select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.EmpCompGrpId = ( "+
  		"select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.cmnLookupMst.lookupId=2500134 and empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.isactive=1"+
  		" select max(a.EmpCompGrpId) from HrEisEmpCompGrpMst as a where (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "+
					" or  "+
					"  ( a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<"+year+" ) and a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<"+year+"))) and a.hrEisEmpMst.empId ="+empId+
  		")"+
  		"  and mpg.hrPayAllowTypeMst.allowCode="+compoId +" and mpg.hrEisEmpMst.orgEmpMst.empId="+empId+")";

		 */
		/*StringBuffer queryStr =new StringBuffer();
		queryStr.append("select mpg hr_pay_empallow_mpg as mpg,hr_pay_allow_type_mst allwMst,hr_eis_emp_mst eMst,hr_eis_emp_component_grp_mst empGrpMst,hr_eis_emp_component_mpg empComp where empGrpMst.emp_id=emst.emp_id and  allwMst.allow_code="
				+ compoId)
				.append(" and empGrpMst.is_active=1 and empComp.compo_group_id=empGrpMst.emp_compo_grp_id and empComp.is_active=1 and empComp.compo_id=allwMst.allow_code and  empComp.compo_id="
							+ compoId);
		queryStr.append(" and mpg.emp_id=eMst.emp_id and mpg.emp_id="+empId);
		 */
		/*	String query1 = "select mpg from HrPayEmpallowMpg as mpg,HrEisEmpCompMpg as empCompo where mpg.hrPayAllowTypeMst.allowCode="
			+ compoId
			+" and mpg.hrPayAllowTypeMst.allowCode=empCompo.compId"
			+ " and mpg.hrEisEmpMst.orgEmpMst.empId="
			+ empId
			+" and mpg.hrEisEmpMst.orgEmpMst.empId=empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId "
			+ " and  mpg.month =" + month + "  and mpg.year=" + year
			+" and empCompo.isactive=1 and empCompo.hrEisEmpCompGrpMst.isactive=1 and empCompo.cmnLookupMst.lookupId=2500134 ";*/
		Query sqlQuery1 = hibSession.createQuery(query1.toString());
		List allwList = sqlQuery1.list();
		logger.info("The special size for allowances is "+allwList.size());

		return allwList;

		// from HrEisEmpCompMpg compo where compo.hrEisEmpCompGrpMst.isactive = 1 and compo.compId = 9 and compo.cmnLookupMst.lookupId=2500134  and compo.isactive = 1
	}


	public List getAllwMaped(long empId, long compoId, int month, int year) {
		Session hibSession = getSession();
		String query1 = "from HrEisEmpCompMpg compo where compo.hrEisEmpCompGrpMst.isactive = 1 and compo.compId = "+compoId+" and compo.cmnLookupMst.lookupId=2500134  and compo.isactive = 1 and compo.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId="+empId;
		Query sqlQuery1= hibSession.createQuery(query1.toString());
		List allwList = sqlQuery1.list();
		logger.info("getMappeed allowances "+allwList.size());

		return allwList;


	}
	public List getDeducMaped(long empId, long compoId, int month, int year) {
		Session hibSession = getSession();
		String query1 = "from HrEisEmpCompMpg compo where compo.hrEisEmpCompGrpMst.isactive = 1 and compo.compId = "+compoId+" and compo.cmnLookupMst.lookupId=2500135  and compo.isactive = 1 and compo.hrEisEmpCompGrpMst.hrEisEmpMst.orgEmpMst.empId="+empId;
		Query sqlQuery1= hibSession.createQuery(query1.toString());
		List allwList = sqlQuery1.list();
		logger.info("getMappeed allowances "+allwList.size());

		return allwList;


	}
	public List getDeduc(long empId, long compoId, int month, int year) {
		Session hibSession = getSession();
		String query1 = "from HrPayDeductionDtls as mpg where mpg.hrPayDeducTypeMst.deducCode="
			+ compoId
			+ " and mpg.hrEisEmpMst.orgEmpMst.empId="
			+ empId
			+ " and  mpg.month =" + month + "  and mpg.year=" + year;

		/*String queryStr ="select deducMst from HrPayDeducTypeMst as deducMst " +
  		"where deducMst.deducType not in (300160) and "
		  +"  deducMst.deducCode in (" 
		  +" select empCompo.compId from HrEisEmpCompMpg as empCompo where empCompo.isactive = 1 and empCompo.hrEisEmpCompGrpMst.hrEisEmpMst.empId = "+empId +"   and empCompo.cmnLookupMst.lookupId = 2500135  and  empCompo.hrEisEmpCompGrpMst.EmpCompGrpId in ( "
		  +"  select max(a.EmpCompGrpId) from HrEisEmpCompGrpMst as a where (a.month = (select max(b.month) from HrEisEmpCompGrpMst as b where b.month<="+month+" and b.year="+year+") and a.year="+year+")  "
		  +" or   "
		  +"  ( a.year = (select max(c.year) from HrEisEmpCompGrpMst as c where c.year<"+year+" ) and a.month =( select max(d.month) from HrEisEmpCompGrpMst as d where d.year = (select max(e.year) from HrEisEmpCompGrpMst as e where e.year<"+year+"))) and a.hrEisEmpMst.empId =   "+empId
		  +"))  and deducMst.deducCode="+compoId ;*/
		Query sqlQuery1 = hibSession.createQuery(query1);
		List allwList = sqlQuery1.list();
		logger.info("the special size is "+allwList.size());

		return allwList;
	}

	public List getDesignationFromEmp(long empId) {
		Session hibSession = getSession();

		String query1 = "SELECT DSGN_CODE FROM ORG_DESIGNATION_MST  where DSGN_ID in "
			+ "(select dsgn_id from ORG_POST_DETAILS_RLT where post_id in"
			+ "(select post_id from ORG_USERPOST_RLT where activate_flag=1 and user_id in"
			+ "(select user_id from ORG_EMP_MST where EMP_ID = "
			+ empId
			+ " )))";

		Query sqlQuery1 = hibSession.createSQLQuery(query1);
		List allwList = sqlQuery1.list();

		return allwList;
	}
	public List getBillgroupDescFromEmp(long empId) {
		Session hibSession = getSession();

		String query1 = "SELECT psr.billNo FROM HrPayPsrPostMpg psr where psr.postId =(select oupr.orgPostMstByPostId from OrgUserpostRlt oupr where oupr.activateFlag=1 and oupr.orgUserMst.userId=(select oem.orgUserMst.userId from OrgEmpMst oem where oem.empId="+empId+"))";
			

		Query sqlQuery1 = hibSession.createQuery(query1);
		List allwList1 = sqlQuery1.list();

		return allwList1;
	}

	public List getEmployeeName(long empId, long locId) {
		Session hibSession = getSession();

		String query1 = "select eisMst.orgEmpMst from HrEisEmpMst as eisMst where eisMst.orgEmpMst.empId="
			+ empId + " and eisMst.cmnLocationMst.locId=" + locId;

		Query sqlQuery1 = hibSession.createQuery(query1);
		List allwList = sqlQuery1.list();

		return allwList;
	}
	/**
	 * 379674
	 * to handle transfer case as location will be changed after tranfer
	 * @param empId
	 * @return
	 */
	public List getEmployeeName(long empId) {
		Session hibSession = getSession();

		String query1 = "select eisMst.orgEmpMst from HrEisEmpMst as eisMst where eisMst.orgEmpMst.empId="
			+ empId;

		Query sqlQuery1 = hibSession.createQuery(query1);
		List allwList = sqlQuery1.list();

		return allwList;
	}

	public HrEisEmpMst getEisEmpId(long empId, long locId) {

		Session hibSession = getSession();

		HrEisEmpMst eisEmpMst = null;
		String query1 = "select eisMst from HrEisEmpMst as eisMst where eisMst.orgEmpMst.empId="
			+ empId;

		Query sqlQuery1 = hibSession.createQuery(query1);
		List list = sqlQuery1.list();

		if (list != null && list.size() > 0)
			eisEmpMst = (HrEisEmpMst) list.get(0);
		return eisEmpMst;

	}

	public long checkDeductionDuplicate(long empId, long compoId) {

		Session hibSession = getSession();

		long count = 0;
		String query1 = "select count(deducDtlsCode) from HrPayDeductionDtls empMpg where empMpg.hrEisEmpMst.orgEmpMst.empId = "
			+ empId 
			+ " and empMpg.hrPayDeducTypeMst.deducCode = "
			+ compoId
			+ " and empMpg.month = -1 and empMpg.year = -1";

		Query sqlQuery1 = hibSession.createQuery(query1);
		List list = sqlQuery1.list();

		if (list != null && list.size() > 0)
			count = Long.parseLong(list.get(0).toString());
		return count;

	}

	public long checkAllowanceDuplicate(long empId, long compoId) {

		Session hibSession = getSession();

		long count = 0;
		String query1 = "select count(allowCode) from HrPayEmpallowMpg empMpg where empMpg.hrEisEmpMst.orgEmpMst.empId = "
			+ empId
			+ " and empMpg.hrPayAllowTypeMst.allowCode ="
			+ compoId
			+ " and empMpg.month = -1 and empMpg.year = -1";

		Query sqlQuery1 = hibSession.createQuery(query1);
		List list = sqlQuery1.list();

		if (list != null && list.size() > 0)
			count = Long.parseLong(list.get(0).toString());
		return count;

	}

	// ended by manish

	//added by abhilash

	public List getEmployeeListFromBillgroup(long billGroupid, long langId, long locId,
			long compoType, long compoId,long dsgn) {
		Session hibSession = getSession();
		StringBuffer query1 = new StringBuffer();
		query1
		.append("select emst.emp_mpg_id,mst.EMP_LNAME||' '||mst.EMP_FNAME||' '||COALESCE(mst.EMP_MNAME,' ')||' ( '||dsm.DSGN_SHRT_NAME||' )'  as name, emst.emp_id ");
		
		if (compoType == 2500134)
			query1.append(" ,ALLWMST.ALLOW_CODE, empAllow.emp_allow_amount ");
		else
			query1.append(" ,DEDUCMST.DEDUC_CODE, DEDDTLS.emp_deduc_amount ");
		
		query1.append(" from ORG_EMP_MST mst,"
				+ " ORG_POST_DETAILS_RLT  pd,ORG_POST_MST pm,ORG_USERPOST_RLT up,MST_DCPS_BILL_GROUP bill,HR_PAY_POST_PSR_MPG psr ");

			query1
			.append(",ORG_DESIGNATION_MST dsm ");

		if (compoType == 2500134) {
			query1
			.append(" ,HR_EIS_EMP_MST EMST ")
			.append(" inner join HR_EIS_EMP_COMPONENT_GRP_MST empGrpMst  on EMPGRPMST.EMP_ID = EMST.EMP_ID ")
			.append(" inner join HR_EIS_EMP_COMPONENT_MPG EMPCOMP on EMPCOMP.COMPO_GROUP_ID=EMPGRPMST.EMP_COMPO_GRP_ID ")
			.append(" inner join HR_PAY_ALLOW_TYPE_MST ALLWMST on empComp.compo_id      =allwMst.allow_code ")
			.append(" left outer join HR_PAY_EMPALLOW_MPG EMPALLOW on EMPALLOW.EMP_ALLOW_ID = ALLWMST.ALLOW_CODE ") 
			.append(" and EMPALLOW.EMP_ID = EMST.EMP_ID and EMPALLOW.month = -1 and EMPALLOW.year= -1 ")
			.append(" where empGrpMst.is_active=1 and empComp.is_active=1 and empComp.compo_type=2500134 ")
			.append(" and  empComp.compo_id=").append(compoId)
			.append(" and allwMst.allow_code=").append(compoId);
		}

		else {
			
			query1
			.append(" ,HR_EIS_EMP_MST EMST ")
			.append(" inner join HR_EIS_EMP_COMPONENT_GRP_MST empGrpMst  on EMPGRPMST.EMP_ID = EMST.EMP_ID ")
			.append(" inner join HR_EIS_EMP_COMPONENT_MPG EMPCOMP on EMPCOMP.COMPO_GROUP_ID=EMPGRPMST.EMP_COMPO_GRP_ID ")
			.append(" inner join HR_PAY_DEDUC_TYPE_MST DEDUCMST on EMPCOMP.COMPO_ID= DEDUCMST.DEDUC_CODE ")
			.append(" left outer join HR_PAY_DEDUCTION_DTLS DEDDTLS on DEDDTLS.EMP_DEDUC_ID = DEDUCMST.DEDUC_CODE ")
			.append(" and DEDDTLS.emp_id = EMST.EMP_ID and DEDDTLS.month = -1 and DEDDTLS.year= -1")
			.append(" where empGrpMst.is_active=1 and empComp.is_active=1 and empComp.compo_type=2500135 ")
			.append(" and  empComp.compo_id=").append(compoId)
			.append(" and DEDUCMST.deduc_code=").append(compoId);
		}

		query1
		.append(" and  emst.EMP_MPG_ID=mst.EMP_ID and mst.USER_ID=up.USER_ID and pm.POST_ID = up.POST_ID and pd.POST_ID=pm.POST_ID and pd.LOC_ID="
				+ locId

				+ " and bill.BILL_GROUP_ID=psr.BILL_NO and psr.POST_ID=pd.POST_ID and psr.LOC_ID="+locId+" and bill.BILL_GROUP_ID=" + billGroupid);

		if (dsgn>0 && dsgn!=1) 
		{
			query1
			.append(" and dsm.DSGN_ID=pd.DSGN_ID and dsm.DSGN_ID="+dsgn+" and dsm.LANG_ID="+langId);

		}
		else
		{
			query1
			.append(" and dsm.DSGN_ID=pd.DSGN_ID and dsm.LANG_ID = 1");
		}
		query1.append(" and up.activate_flag= 1 and pm.activate_flag = 1 ");
		//06 jan 2011 change for order of employee 
		query1.append(" order by name");
		logger.info("Query getEmployeeListFromBillgroup  ++++++++++" + query1.toString());
		Query sqlQuery1 = hibSession.createSQLQuery(query1.toString());
		List dsgnList = sqlQuery1.list();
		logger.info("getEmployeeListFromBillgroup Employee List size is  " + dsgnList.size());
		return dsgnList;
	}

	//ended by abhilash
	public long getEmpPostIdFromEisEmpID(long hdnEmpId) {
		
		Session hibSession = getSession();

		long empPostId = 0;
		String query1 = "select oupr.empPostId from OrgUserpostRlt oupr where orgUserMst.userId = (select oem.orgUserMst.userId from OrgEmpMst oem where oem.empId = (select heem.orgEmpMst.empId from  HrEisEmpMst heem where heem.empId = "+hdnEmpId+"))";

		Query sqlQuery1 = hibSession.createQuery(query1);
		List list = sqlQuery1.list();

		if (list != null && list.size() > 0)
			empPostId = Long.parseLong(list.get(0).toString());
		return empPostId;
		
	}



	public List checkBankName(String bankName, long langId) {
		// TODO Auto-generated method stub
		return null;
	}



	public List getAllBankMasterData(long langId) {
		// TODO Auto-generated method stub
		return null;
	}



	public MstBankPay getBankIdData(long BankId, long langId) {
		// TODO Auto-generated method stub
		return null;
	}



	public HrEisBankMst getBankMstVOByBranchCodeAndLang(String strBankCode,
			long langId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
